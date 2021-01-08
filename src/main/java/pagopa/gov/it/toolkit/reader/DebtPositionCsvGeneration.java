package pagopa.gov.it.toolkit.reader;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.DebtPositionGeneration;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

public class DebtPositionCsvGeneration {
	private static final int HEADER_LENGTH = 52;
	
	public static DebtPosition generateDebtPosition(DebtPositionCsv debtPositionCsv) throws Exception {		
		// generating payer
		String uniqueIdentificationCode = debtPositionCsv.getPayerUniqueIdentificationCode();
		StTipoIdentificativoUnivocoPersFG uniqueIdentificationType = debtPositionCsv.getPayerUniqueIdentificationType();
		String registry = debtPositionCsv.getPayerRegistry();
		String address = debtPositionCsv.getPayerAddress();
		String numberStreet = debtPositionCsv.getPayerNumberStreet();
		String locality = debtPositionCsv.getPayerLocality();
		String province = debtPositionCsv.getPayerProvince();
		String nation = debtPositionCsv.getPayerNation();
		String postalCode = debtPositionCsv.getPayerPostalCode();
		String email = debtPositionCsv.getPayerEmail();
		String mobile = debtPositionCsv.getPayerMobile();		
		DPPayer payer = DebtPositionGeneration.generatePayer(uniqueIdentificationCode, uniqueIdentificationType, registry, address, numberStreet, locality, 
				province, nation, postalCode, email, mobile);
		
		// generating payment detail
		String domainIdentifier = debtPositionCsv.getDomainIdentifier();
		int auxDigit = debtPositionCsv.getDomainAuxDigit();
		Integer segregationCode = debtPositionCsv.getDomainSegregationCode();
		Integer applicationCode = debtPositionCsv.getDomainApplicationCode();
		String idTenant = debtPositionCsv.getTenantId();
		BigDecimal totalAmountPayment = debtPositionCsv.getTotalAmountPayment();
		String causal = debtPositionCsv.getCausal();
		Date expirationDate = debtPositionCsv.getExpirationDate();		
		String specificCollectionData = debtPositionCsv.getSpecificCollectionData();
		String documentNumber = debtPositionCsv.getDocumentNumber();
		Integer installmentNumber = debtPositionCsv.getInstallmentNumber();
		String debitIban = debtPositionCsv.getDebitIban();
		String debitBic = debtPositionCsv.getDebitBic();
		DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit, segregationCode, applicationCode, null, idTenant, 
				totalAmountPayment, causal,	expirationDate, specificCollectionData, documentNumber, installmentNumber, debitIban, debitBic);
		
		// generating single payment detail
		BigDecimal amountSinglePayment = debtPositionCsv.getAmountSinglePayment();
		Integer orderSinglePayment = debtPositionCsv.getOrderSinglePayment();
		String causalDescriptionSinglePayment = debtPositionCsv.getCausalDescriptionSinglePayment();
		String creditIban = debtPositionCsv.getCreditIban();
		String creditBic = debtPositionCsv.getCreditBic();
		String supportIban = debtPositionCsv.getSupportIban();
		String supportBic = debtPositionCsv.getSupportBic();		
		TipoBolloEnum tipoBollo = debtPositionCsv.getTipoBollo();
		String hashDocumento = debtPositionCsv.getDocumentHash();
		String provinciaResidenza = debtPositionCsv.getResidenceProvince();
		DatiMarcaBolloDigitale datiMarcaBolloDigitale = null;
		if(tipoBollo != null && hashDocumento != null && provinciaResidenza != null) {
			datiMarcaBolloDigitale = DebtPositionGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento, provinciaResidenza);
		}		
		DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentDetail(amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, creditIban, creditBic, supportIban, 
				supportBic, datiMarcaBolloDigitale);
		List<DPSinglePaymentDetail> singlePaymentsDetailList = new ArrayList<DPSinglePaymentDetail>();
		singlePaymentsDetailList.add(singlePaymentDetail);
		
		return DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentsDetailList);
	}
	
	public static DebtPositionCsv generate(String line, byte[] domainLogo) throws Exception {
		if(!isValid(line)) {
			return null;
		}
		
		DebtPositionCsv debtPositionCsv = new DebtPositionCsv();
		String[] arrayDebtPosition = line.split(";");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		debtPositionCsv.setPayerUniqueIdentificationCode(getValue(arrayDebtPosition[0]));
		debtPositionCsv.setPayerUniqueIdentificationType(stringToStTipoIdentificativoUnivocoPersFG(getValue(arrayDebtPosition[1])));
		debtPositionCsv.setPayerRegistry(getValue(arrayDebtPosition[2]));
		debtPositionCsv.setPayerAddress(getValue(arrayDebtPosition[3]));
		debtPositionCsv.setPayerNumberStreet(getValue(arrayDebtPosition[4]));
		debtPositionCsv.setPayerLocality(getValue(arrayDebtPosition[5]));
		debtPositionCsv.setPayerProvince(StringUtils.upperCase(getValue(arrayDebtPosition[6])));
		debtPositionCsv.setPayerNation(StringUtils.upperCase(getValue(arrayDebtPosition[7])));
		debtPositionCsv.setPayerPostalCode(getValue(arrayDebtPosition[8]));
		debtPositionCsv.setPayerEmail(getValue(arrayDebtPosition[9]));
		debtPositionCsv.setPayerMobile(getValue(arrayDebtPosition[10]));
		
		debtPositionCsv.setDomainIdentifier(getValue(arrayDebtPosition[11]));
		debtPositionCsv.setDomainFiscalCode(getValue(arrayDebtPosition[12]));
		debtPositionCsv.setDomainName(getValue(arrayDebtPosition[13]));
		debtPositionCsv.setDomainOperationalUnitCode(getValue(arrayDebtPosition[14]));
		debtPositionCsv.setDomainOperationalUnitName(getValue(arrayDebtPosition[15]));
		debtPositionCsv.setDomainAddress(getValue(arrayDebtPosition[16]));
		debtPositionCsv.setDomainNumberStreet(getValue(arrayDebtPosition[17]));
		debtPositionCsv.setDomainPostalCode(getValue(arrayDebtPosition[18]));
		debtPositionCsv.setDomainLocality(getValue(arrayDebtPosition[19]));
		debtPositionCsv.setDomainProvince(getValue(arrayDebtPosition[20]));
		debtPositionCsv.setDomainNation(getValue(arrayDebtPosition[21]));
		debtPositionCsv.setDomainSector(getValue(arrayDebtPosition[22]));
		debtPositionCsv.setDomainCbillCode(getValue(arrayDebtPosition[23]));
		debtPositionCsv.setDomainPostalAccountHolder(getValue(arrayDebtPosition[24]));
		debtPositionCsv.setDomainPostalAccountNumber(getValue(arrayDebtPosition[25]));
		debtPositionCsv.setDomainPostalAuthorizationCode(getValue(arrayDebtPosition[26]));
		debtPositionCsv.setDomainChargeCommission(new BigDecimal(getValue(arrayDebtPosition[27])));
		debtPositionCsv.setDomainAuxDigit(getValue(arrayDebtPosition[28]) != null ? Integer.parseInt(getValue(arrayDebtPosition[28])) : null);
		debtPositionCsv.setDomainSegregationCode(getValue(arrayDebtPosition[29]) != null ? Integer.parseInt(getValue(arrayDebtPosition[29])) : null);
		debtPositionCsv.setDomainApplicationCode(getValue(arrayDebtPosition[30]) != null ? Integer.parseInt(getValue(arrayDebtPosition[30])) : null);
		debtPositionCsv.setDomainInfo(getValue(arrayDebtPosition[31]));
		debtPositionCsv.setDomainWebsite(getValue(arrayDebtPosition[32]));
		debtPositionCsv.setDomainLogo(domainLogo);
		
		debtPositionCsv.setTenantId(getValue(arrayDebtPosition[33]));
		debtPositionCsv.setTotalAmountPayment(new BigDecimal(getValue(arrayDebtPosition[34])));
		debtPositionCsv.setCausal(getValue(arrayDebtPosition[35]));		
		Date expirationDate = dateFormat.parse(getValue(arrayDebtPosition[36]));
		debtPositionCsv.setExpirationDate(expirationDate);
		debtPositionCsv.setSpecificCollectionData(getValue(arrayDebtPosition[37]));
		debtPositionCsv.setDocumentNumber(getValue(arrayDebtPosition[38]));
		debtPositionCsv.setInstallmentNumber(getValue(arrayDebtPosition[39]) != null ? Integer.parseInt(getValue(arrayDebtPosition[39])) : null);
		debtPositionCsv.setDebitIban(StringUtils.upperCase(getValue(arrayDebtPosition[40])));
		debtPositionCsv.setDebitBic(StringUtils.upperCase(getValue(arrayDebtPosition[41])));
		
		debtPositionCsv.setAmountSinglePayment(new BigDecimal(getValue(arrayDebtPosition[34])));
		debtPositionCsv.setOrderSinglePayment(getValue(arrayDebtPosition[42]) != null ? Integer.parseInt(getValue(arrayDebtPosition[42])) : null);
		debtPositionCsv.setCausalDescriptionSinglePayment(getValue(arrayDebtPosition[43]));
		debtPositionCsv.setCreditIban(StringUtils.upperCase(getValue(arrayDebtPosition[44])));
		debtPositionCsv.setCreditBic(StringUtils.upperCase(getValue(arrayDebtPosition[45])));
		debtPositionCsv.setSupportIban(StringUtils.upperCase(getValue(arrayDebtPosition[46])));
		debtPositionCsv.setSupportBic(StringUtils.upperCase(getValue(arrayDebtPosition[47])));
		TipoBolloEnum tipoBollo = stringToTipoBolloEnum(getValue(arrayDebtPosition[48]));
		debtPositionCsv.setTipoBollo(tipoBollo);
		debtPositionCsv.setDocumentHash(getValue(arrayDebtPosition[49]));
		debtPositionCsv.setResidenceProvince(getValue(arrayDebtPosition[50]));
		
		Boolean isModello1 = getValue(arrayDebtPosition[51]).equalsIgnoreCase("SI") ? Boolean.TRUE : Boolean.FALSE;
		debtPositionCsv.setIsModello1Or2(isModello1);
		
		return debtPositionCsv;
	}
	
	private static String getValue(String string) {
		String s = string.trim();
		if(s.equals("\"\"")) {
			return null;
		}
        return s.substring(1, s.length() - 1).trim();
	}
	
	private static boolean isValid(String line) {
		String[] arrayDebtPosition = line.split(";");
		if(arrayDebtPosition.length != HEADER_LENGTH) {
			return false;
		}
		for(String string : arrayDebtPosition) {
			string = string.trim();
			if(string.length() < 2 || !string.trim().startsWith("\"") || !string.trim().endsWith("\"")) {
				return false;
			}
		}
		return true;
	}
	
	private static StTipoIdentificativoUnivocoPersFG stringToStTipoIdentificativoUnivocoPersFG(String uniqueIdentificationTypeString) {
		StTipoIdentificativoUnivocoPersFG uniqueIdentificationType = null;
		if(uniqueIdentificationTypeString != null) {
			switch(StringUtils.upperCase(uniqueIdentificationTypeString)) {
			case Constants.UNIQUE_IDENTIFICATION_TYPE_DEFAULT:
				uniqueIdentificationType = StTipoIdentificativoUnivocoPersFG.F;
				break;
			case "G":
				uniqueIdentificationType = StTipoIdentificativoUnivocoPersFG.G;
				break;
			}			
		}
		return uniqueIdentificationType;
	}
	
	private static TipoBolloEnum stringToTipoBolloEnum(String tipoBolloString) {
		TipoBolloEnum tipoBolloEnum = null;
		if(tipoBolloString != null && tipoBolloString.equals("01")) {
			tipoBolloEnum = TipoBolloEnum.IMPOSTA_DI_BOLLO;
		}		
		return tipoBolloEnum;
	}

}
