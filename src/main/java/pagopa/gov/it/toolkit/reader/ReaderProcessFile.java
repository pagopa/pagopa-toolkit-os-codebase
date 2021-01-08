package pagopa.gov.it.toolkit.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.PaymentNoticeGeneration;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import pagopa.gov.it.toolkit.rptGenerator.RptGeneration;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;

public class ReaderProcessFile {
	
	public ReaderStatus processCsvFile(String filePath, String outputFolder, String logoPath) throws ReaderException, IOException {
		ReaderStatus readerStatus = ReaderStatus.OK;
		
		checkInputData(filePath, outputFolder, logoPath);
		BufferedReader bufferedReader = null;
		File logoFile = new File(logoPath);
		
		String outputFilePath = createOutputFile(outputFolder);  // creating the output file		
		
		bufferedReader = new BufferedReader(new FileReader(filePath));  // opening the CSV file
		bufferedReader.readLine();  // skipping the first line since it contains the header
		
		Map<String, List<DebtPositionCsv>> documentNumberMap = new HashMap<String, List<DebtPositionCsv>>();
		List<DebtPositionCsvOutput> debtPositionCsvOutputList = new ArrayList<DebtPositionCsvOutput>();
		
		// reading CSV file line by line
		String line = "";
		while((line = bufferedReader.readLine()) != null) {
			if(line.isEmpty()) {
				continue;
			}
			
			DebtPosition debtPosition = null;
			try {
				byte[] logo = Files.readAllBytes(logoFile.toPath());
				DebtPositionCsv debtPositionCsv = DebtPositionCsvGeneration.generate(line, logo);
				if(debtPositionCsv == null) {
					readerStatus = ReaderStatus.WARNING;
					debtPositionCsvOutputList.add(new DebtPositionCsvOutput(line, "", "", "KO", "Formattazione riga non corretta"));
					continue;
				}					
				if(debtPositionCsv.getDocumentNumber() == null) {  // debt position without installments
					debtPosition = DebtPositionCsvGeneration.generateDebtPosition(debtPositionCsv);
					RptContainer rptContainer = generateRptContainer(debtPositionCsv, debtPosition);
					createRptXmlFile(rptContainer, outputFolder);
					PNCreditorInstitution creditorInstitution = generateCreditorInstitution(debtPositionCsv);
					Boolean isModello1or2 = debtPositionCsv.getIsModello1Or2();
					List<DebtPosition> debtPositionList = new ArrayList<DebtPosition>();
					debtPositionList.add(debtPosition);
					byte[] pdfPaymentNotice = PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution, isModello1or2);
					createNoticePaymentPdfFile(pdfPaymentNotice, debtPosition.getPaymentDetail().getNoticeNumber(), outputFolder);
					debtPositionCsvOutputList.add(new DebtPositionCsvOutput(line, debtPosition.getPaymentDetail().getIuv(), debtPosition.getPaymentDetail().getNoticeNumber(), 
							"OK", ""));
				}
				else {  // debt position with installments
					addDebtPositionCsvToMap(documentNumberMap, debtPositionCsv);  // populating documentNumbersMap (key: document number, value: list of debt positions)
				}
			} catch(Exception exception) {
				readerStatus = ReaderStatus.WARNING;
				String iuv = debtPosition != null ? debtPosition.getPaymentDetail().getIuv() : "";
				String noticeNumber = debtPosition != null ? debtPosition.getPaymentDetail().getNoticeNumber() : "";
				debtPositionCsvOutputList.add(new DebtPositionCsvOutput(line, iuv, noticeNumber, "KO", exception.getMessage()));
			}
		}
		
		bufferedReader.close();  // releasing the CSV file

		// iterating through documentNumberMap
		for(Map.Entry<String, List<DebtPositionCsv>> entry : documentNumberMap.entrySet()) {
			List<DebtPositionCsv> debtPositionCsvList = entry.getValue();
			List<DebtPosition> debtPositionList = new ArrayList<DebtPosition>();
			
			for(DebtPositionCsv debtPositionCsv : debtPositionCsvList) {
				DebtPosition debtPosition = null;
				try {
					debtPosition = DebtPositionCsvGeneration.generateDebtPosition(debtPositionCsv);
					debtPositionCsv.setIuv(debtPosition.getPaymentDetail().getIuv());
					debtPositionCsv.setNoticeNumber(debtPosition.getPaymentDetail().getNoticeNumber());
					RptContainer rptContainer = generateRptContainer(debtPositionCsv, debtPosition);
					createRptXmlFile(rptContainer, outputFolder);
					debtPositionList.add(debtPosition);
				} catch(Exception exception) {
					readerStatus = ReaderStatus.WARNING;
					String iuv = debtPosition != null ? debtPosition.getPaymentDetail().getIuv() : "";
					String noticeNumber = debtPosition != null ? debtPosition.getPaymentDetail().getNoticeNumber() : "";
					debtPositionCsvOutputList.add(new DebtPositionCsvOutput(debtPositionCsv, iuv, noticeNumber, "KO", exception.getMessage()));
				}
			}
			
			try {
				PNCreditorInstitution creditorInstitution = generateCreditorInstitution(debtPositionCsvList.get(0));
				Boolean isModello1or2 = debtPositionCsvList.get(0).getIsModello1Or2();
				byte[] pdfPaymentNotice = PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution, isModello1or2);
				String documentNumber = entry.getKey();
				createNoticePaymentPdfFile(pdfPaymentNotice, documentNumber, outputFolder);
				for(DebtPositionCsv debtPositionCsv : debtPositionCsvList) {
					debtPositionCsvOutputList.add(new DebtPositionCsvOutput(debtPositionCsv, debtPositionCsv.getIuv(), debtPositionCsv.getNoticeNumber(), "OK", ""));
				}
			} catch(Exception exception) {
				readerStatus = ReaderStatus.WARNING;
				for(DebtPositionCsv debtPositionCsv : debtPositionCsvList) {
					debtPositionCsvOutputList.add(new DebtPositionCsvOutput(debtPositionCsv, debtPositionCsv.getIuv(), debtPositionCsv.getNoticeNumber(), "KO", 
							exception.getMessage()));
				}
			}
		}
		
		updateOutputFile(outputFilePath, debtPositionCsvOutputList);  // writing the result in the output file
		
		return readerStatus;
	}
	
	private String createOutputFile(String outputFolder) throws IOException {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String outputFilePath = outputFolder + File.separator + "Esito_" + dateFormat.format(currentDate) + ".csv";
		FileOutputStream outputStream = new FileOutputStream(outputFilePath);
		outputStream.close();
		String header = "\"CodiceFiscale/P.IVA\";\"Tipo\";\"Anagrafica Pagatore\";\"Indirizzo\";\"Civico\";\"Localita\";\"Provincia\";\"Nazione\";"
				+ "\"CAP\";\"Email\";\"Mobile\";\"Identificativo Ente Beneficiario\";\"CodiceFiscale/P.IVA Ente Beneficiario\";\"Denominazione Ente Beneficiario\";"
				+ "\"Codice Unita Operativa\";\"Denominazione Unita Operativa\";\"Indirizzo Ente Beneficiario\";\"Civico Ente Beneficiario\";\"CAP Ente Beneficiario\";"
				+ "\"Localita Ente Beneficiario\";\"Provincia Ente Beneficiario\";\"Nazione Ente Beneficiario\";\"Settore\";\"Codice CBILL\";\"Proprietario Account Postale\";"
				+ "\"Numero Account Postale\";\"Codice Autorizzazione Postale\";\"Commissione Carico\";\"Aux Digit\";\"Segregation Code\";\"Application Code\";"
				+ "\"Informazioni Aggiuntive\";\"Sito Web\";\"ID Tenant\";\"Importo Versamento\";\"Causale Avviso\";\"Data Scadenza Pagamento (dd-mm-yyyy)\";"
				+ "\"Dati Specifici Riscossione\";\"Numero Documento\";\"Numero Rata\";\"IBAN Addebito\";\"BIC Addebito\";\"Ordine Versamento\";\"Descrizione Causale RPT\";"
				+ "\"IBAN Accredito\";\"BIC Accredito\";\"IBAN Supporto\";\"BIC Supporto\";\"Tipo Bollo\";\"Hash Documento\";\"Provincia Residenza\";\"Modello 1\";\"IUV\";"
				+ "\"Numero Avviso\";\"Esito Operazione\";\"Descrizione Esito\"";
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath, true));
		bufferedWriter.append(header);
		bufferedWriter.append("\n");
		bufferedWriter.close();
		
		return outputFilePath;
	}
	
	private void updateOutputFile(String outputFilePath, List<DebtPositionCsvOutput> debtPositionCsvOutputList) throws IOException  {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath, true));
		
		for(DebtPositionCsvOutput debtPositionCsvOutput : debtPositionCsvOutputList) {
			String iuv = debtPositionCsvOutput.getIuv();
			String noticeNumber = debtPositionCsvOutput.getNoticeNumber();
			String result = debtPositionCsvOutput.getResult();
			String resultDescription = debtPositionCsvOutput.getResultDescription();
			String line = "";
			
			if(debtPositionCsvOutput.getDebtPositionCsv() == null) {
				line = debtPositionCsvOutput.getLine() + ";\"" + iuv + "\";\"" + noticeNumber + "\"";
			}
			else {
				DebtPositionCsv debtPositionCsv = debtPositionCsvOutput.getDebtPositionCsv();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				line = "\"" + debtPositionCsv.getPayerUniqueIdentificationCode() + "\";";
				StTipoIdentificativoUnivocoPersFG stTipoIdentificativoUnivocoPersFG  = debtPositionCsv.getPayerUniqueIdentificationType();
				line += "\"" + (stTipoIdentificativoUnivocoPersFG != null ? stTipoIdentificativoUnivocoPersFG.value() : "") + "\";";
				line += "\"" + debtPositionCsv.getPayerRegistry() + "\";";
				line += "\"" + debtPositionCsv.getPayerAddress() + "\";";
				line += "\"" + debtPositionCsv.getPayerNumberStreet() + "\";";
				line += "\"" + debtPositionCsv.getPayerLocality() + "\";";
				line += "\"" + debtPositionCsv.getPayerProvince() + "\";";
				line += "\"" + debtPositionCsv.getPayerNation() + "\";";
				line += "\"" + debtPositionCsv.getPayerPostalCode() + "\";";
				line += "\"" + debtPositionCsv.getPayerEmail() + "\";";
				line += "\"" + debtPositionCsv.getPayerMobile() + "\";";
				line += "\"" + debtPositionCsv.getDomainIdentifier() + "\";";
				
				line += "\"" + debtPositionCsv.getDomainFiscalCode() + "\";";
				line += "\"" + debtPositionCsv.getDomainName() + "\";";
				line += "\"" + debtPositionCsv.getDomainOperationalUnitCode() + "\";";
				line += "\"" + debtPositionCsv.getDomainOperationalUnitName() + "\";";
				line += "\"" + debtPositionCsv.getDomainAddress() + "\";";
				line += "\"" + debtPositionCsv.getDomainNumberStreet() + "\";";
				line += "\"" + debtPositionCsv.getDomainPostalCode() + "\";";
				line += "\"" + debtPositionCsv.getDomainLocality() + "\";";
				line += "\"" + debtPositionCsv.getDomainProvince() + "\";";
				line += "\"" + debtPositionCsv.getDomainNation() + "\";";
				line += "\"" + debtPositionCsv.getDomainSector() + "\";";
				line += "\"" + debtPositionCsv.getDomainCbillCode() + "\";";
				line += "\"" + debtPositionCsv.getDomainPostalAccountHolder() + "\";";
				line += "\"" + debtPositionCsv.getDomainPostalAccountNumber() + "\";";
				line += "\"" + debtPositionCsv.getDomainPostalAuthorizationCode() + "\";";
				BigDecimal domainChargeCommission = debtPositionCsv.getDomainChargeCommission();
				line += "\"" + (domainChargeCommission != null ? domainChargeCommission.setScale(2).toString() : "") + "\";";
				line += "\"" + debtPositionCsv.getDomainAuxDigit() + "\";";
				line += "\"" + debtPositionCsv.getDomainSegregationCode() + "\";";
				Integer domainApplicationCode = debtPositionCsv.getDomainApplicationCode();
				String domainApplicationCodeString = "";
				if(domainApplicationCode != null && domainApplicationCode > 0) {
					domainApplicationCodeString = StringUtils.leftPad(String.valueOf(domainApplicationCode), 2, "0");
				}
				else {
					domainApplicationCodeString = String.valueOf(domainApplicationCode);
				}					
				line += "\"" + domainApplicationCodeString + "\";";
				line += "\"" + debtPositionCsv.getDomainInfo() + "\";";
				line += "\"" + debtPositionCsv.getDomainWebsite() + "\";";
				
				line += "\"" + debtPositionCsv.getTenantId() + "\";";
				line += "\"" + debtPositionCsv.getTotalAmountPayment() + "\";";
				line += "\"" + debtPositionCsv.getCausal() + "\";";				
				Date expirationDate = debtPositionCsv.getExpirationDate();
				line += "\"" + (expirationDate != null ? dateFormat.format(debtPositionCsv.getExpirationDate()) : "") + "\";";
				line += "\"" + debtPositionCsv.getSpecificCollectionData() + "\";";
				line += "\"" + debtPositionCsv.getDocumentNumber() + "\";";
				line += "\"" + debtPositionCsv.getInstallmentNumber() + "\";";
				line += "\"" + debtPositionCsv.getDebitIban() + "\";";
				line += "\"" + debtPositionCsv.getDebitBic() + "\";";
				line += "\"" + debtPositionCsv.getOrderSinglePayment() + "\";";
				line += "\"" + debtPositionCsv.getCausalDescriptionSinglePayment() + "\";";
				line += "\"" + debtPositionCsv.getCreditIban() + "\";";
				line += "\"" + debtPositionCsv.getCreditBic() + "\";";
				line += "\"" + debtPositionCsv.getSupportIban() + "\";";
				line += "\"" + debtPositionCsv.getSupportBic() + "\";";
				TipoBolloEnum tipoBolloEnum = debtPositionCsv.getTipoBollo();
				line += "\"" + (tipoBolloEnum != null ? tipoBolloEnum.value() : "") + "\";";
				line += "\"" + debtPositionCsv.getDocumentHash() + "\";";
				line += "\"" + debtPositionCsv.getResidenceProvince() + "\";";
				String isModello1Or2 = "";
				if(debtPositionCsv.getIsModello1Or2() != null && debtPositionCsv.getIsModello1Or2()) {
					isModello1Or2 = "SI";
				}
				else {
					isModello1Or2 = "NO";
				}
				line += "\"" + isModello1Or2 + "\";";
				line += "\"" + iuv + "\";";
				line += "\"" + noticeNumber + "\"";
			}
			
			line = line.replace("null", "");
			bufferedWriter.append(line + ";\"" + result + "\";\"" + resultDescription + "\"");
			line = "";
			bufferedWriter.append("\n");
		}		
		bufferedWriter.close();
	}
	
	private PNCreditorInstitution generateCreditorInstitution(DebtPositionCsv debtPositionCsv) {
		String name = debtPositionCsv.getDomainName();
		String sector = debtPositionCsv.getDomainSector();
		String info = debtPositionCsv.getDomainInfo();
		String fiscalCode = debtPositionCsv.getDomainFiscalCode();
		String cbillCode = debtPositionCsv.getDomainCbillCode();
		String postalAccountHolder = debtPositionCsv.getDomainPostalAccountHolder();
		String postalAccountNumber = debtPositionCsv.getDomainPostalAccountNumber();
		String postalAuthorizationCode = debtPositionCsv.getDomainPostalAuthorizationCode();
		String website = debtPositionCsv.getDomainWebsite();
		byte[] logo = debtPositionCsv.getDomainLogo();
		
		return PaymentNoticeGeneration.generateCreditorInstitution(logo, name, sector, info, fiscalCode, cbillCode, postalAccountHolder, postalAccountNumber, 
				postalAuthorizationCode, website);
	}
	
	private RptContainer generateRptContainer(DebtPositionCsv debtPositionCsv, DebtPosition debtPosition) throws Exception {
		String idTenant = debtPositionCsv.getTenantId();
		String identificativoUnivoco = debtPositionCsv.getDomainIdentifier();
		String denominazione = debtPositionCsv.getDomainName();
		String codiceUnitOper = debtPositionCsv.getDomainOperationalUnitCode();
		String denomUnitOper = debtPositionCsv.getDomainOperationalUnitName();
		String indirizzo = debtPositionCsv.getDomainAddress();
		String civico = debtPositionCsv.getDomainNumberStreet();
		String cap = debtPositionCsv.getDomainPostalCode();
		String localita = debtPositionCsv.getDomainLocality();
		String provincia = debtPositionCsv.getDomainProvince();
		String nazione = debtPositionCsv.getDomainNation();
		BigDecimal commissioneCaricoPA = debtPositionCsv.getDomainChargeCommission();
		RptIdentificativoUnivocoG rptIdentificativoUnivocoG = RptGeneration.generateIdentificativoUnivocoG(StTipoIdentificativoUnivocoPersG.G, identificativoUnivoco);
		RptIndirizzo rptIndirizzo = RptGeneration.generateIndirizzo(indirizzo, civico, cap, localita, provincia, nazione);
		RptEnteBeneficiario enteBeneficiario = RptGeneration.generateEnteBeneficiario(rptIdentificativoUnivocoG, denominazione, codiceUnitOper, denomUnitOper, rptIndirizzo);
		
		return RptGeneration.generate(idTenant, debtPosition, enteBeneficiario, commissioneCaricoPA);
	}
	
	private void createRptXmlFile(RptContainer rptContainer, String outputFolder) throws FileNotFoundException, IOException {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String iuv = rptContainer.getRpt().getDatiVersamento().getIdentificativoUnivocoVersamento();
		String filePath = outputFolder + File.separator + "RPT_" + iuv + "_" + dateFormat.format(currentDate) + ".xml";
		OutputStream outputStream = new FileOutputStream(filePath);
		outputStream.write(rptContainer.getRptXml());
		outputStream.close();
	}
	
	private void createNoticePaymentPdfFile(byte[] pdfPaymentNotice, String paymentData, String outputFolder) throws FileNotFoundException, IOException {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String filePath = outputFolder + File.separator + "Avviso_" + paymentData + "_" + dateFormat.format(currentDate) + ".pdf";
		OutputStream outputStream = new FileOutputStream(filePath);
		outputStream.write(pdfPaymentNotice);
		outputStream.close();
	}
	
	private void addDebtPositionCsvToMap(Map<String, List<DebtPositionCsv>> documentNumbersMap, DebtPositionCsv debtPositionCsv) {
		String documentNumber = debtPositionCsv.getDocumentNumber();
		List<DebtPositionCsv> debtPositionsList = documentNumbersMap.get(documentNumber);
		if(debtPositionsList == null) {			
			debtPositionsList = new ArrayList<DebtPositionCsv>();
		}
		debtPositionsList.add(debtPositionCsv);
		documentNumbersMap.put(documentNumber, debtPositionsList);
	}
	
	private void checkInputData(String filePath, String outputFolder, String logoPath) throws ReaderException {		
		// checking debt positions file
		if(filePath.isEmpty()) {
			throw new ReaderException("Selezionare un file CSV per procedere");
		}
		if(!filePath.toLowerCase().endsWith(".csv")) {
			throw new ReaderException("Il file delle posizioni debitorie ha un formato non corretto. Selezionare un file CSV");
		}
		File debtPositionFile = new File(filePath);
		if(!debtPositionFile.exists()) {
			throw new ReaderException("File delle posizioni debitorie non trovato");
		}
		
		// checking output folder
		if(outputFolder.isEmpty()) {
			throw new ReaderException("Selezionare una cartella di destinazione per procedere");
		}
		Path outputFolderPath = Paths.get(outputFolder);
		if(!Files.exists(outputFolderPath)) {
			throw new ReaderException("Cartella di destinazione non trovata");
		}
		
		// checking domain logo
		if(logoPath.isEmpty()) {
			throw new ReaderException("Selezionare un logo in formato PNG per procedere");
		}
		if(!logoPath.toLowerCase().endsWith(".png")) {
			throw new ReaderException("Il logo ha un formato non corretto. Selezionare un file PNG");
		}
		File logoFile = new File(logoPath);
		if(!logoFile.exists()) {
			throw new ReaderException("Logo non trovato");
		}
	}
}
