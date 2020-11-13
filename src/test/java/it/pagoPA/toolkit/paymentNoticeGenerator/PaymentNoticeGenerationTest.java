package it.pagoPA.toolkit.paymentNoticeGenerator;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution.Builder;

public class PaymentNoticeGenerationTest {

	Builder creditorInstitutionBuilder = new PNCreditorInstitution.Builder();
	String path = "src/test/resources/";
	Path logoPath = Paths.get(path + "logoTest.png");

	String ci_name = "nome Ente Creditore";
	String ci_sector = "settore Ente Creditore";
	String ci_info = "info Ente Creditore";
	String ci_fiscalCode = "01234567890";
	String ci_cbillCode = "98765";
	String ci_postalAccountHolder = "Giuseppe Verdi";
	String ci_postalAccountNumber = "001122334455";
	String ci_postalAuthorizationCode = "codice autorizzazione Postale";
	String ci_website = "www.enteCreditore.it";

	String payer_uniqueIdentificationCode = "RSSMRA80A01F205X";
	String payer_uniqueIdentificationType = "F";
	String payer_registry = "ROSSI MARIO";
	String payer_address = "Via Roma";
	String payer_numberStreet = "1";
	String payer_locality = "Firenze";
	String payer_province = "FI";
	String payer_postalCode = "13579";

	int auxDigit = 3;
	Integer segregationCode = 01;
	String pd_domainIdentifier = "01234567890";
	String pd_causal = "Causale pagamento";

	Integer spd_orderSinglePayment = 1;
	String spd_causalDescriptionSinglePayment = "Causale pagamento SinglePayment";

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		byte[] logoData = Files.readAllBytes(logoPath);
		creditorInstitutionBuilder.setPostalAuthorizationCode(ci_postalAuthorizationCode).setCbillCode(ci_cbillCode)
				.setFiscalCode(ci_fiscalCode).setInfo(ci_info).setPostalAccountHolder(ci_postalAccountHolder)
				.setName(ci_name).setPostalAccountNumber(ci_postalAccountNumber).setSector(ci_sector).setLogo(logoData)
				.setWebsite(ci_website);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_NoInstallments_AllData() throws Exception {
		BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11);
		Date pd_expirationDate = addDays(new Date(), 10);
		DebtPosition referenceDebtPosition = createDebtPositionAllData(pd_totalAmountPayment, pd_expirationDate, null,
				null);

		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		debtPositionList.add(referenceDebtPosition);

		String pdfFileName = "np_NoInstallments_AllData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_NoInstallments_MinimumData() throws Exception {
		BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11);
		DebtPosition referenceDebtPosition = createDebtPositionMinimumData(pd_totalAmountPayment, null, null);

		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		debtPositionList.add(referenceDebtPosition);

		String pdfFileName = "np_NoInstallments_MinimumData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_3Installments_WithSingleInstallment_AllData() throws Exception {
		int outputListSize = 3;
		Boolean hasSingleInstallment = true;
		List<DebtPosition> debtPositionList = createDebtPositionListAllData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_3Installments_WithSingleInstallment_AllData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_3Installments_NoSingleInstallment_AllData() throws Exception {
		int outputListSize = 3;
		Boolean hasSingleInstallment = false;
		List<DebtPosition> debtPositionList = createDebtPositionListAllData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_3Installments_NoSingleInstallment_AllData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_3Installments_WithSingleInstallment_MinimumData() throws Exception {
		int outputListSize = 3;
		Boolean hasSingleInstallment = true;
		List<DebtPosition> debtPositionList = createDebtPositionListMinimumData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_3Installments_WithSingleInstallment_MinimumData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_3Installments_NoSingleInstallment_MinimumData() throws Exception {
		int outputListSize = 3;
		Boolean hasSingleInstallment = false;
		List<DebtPosition> debtPositionList = createDebtPositionListMinimumData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_3Installments_NoSingleInstallment_MinimumData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_2Installments_WithSingleInstallment_AllData() throws Exception {
		int outputListSize = 2;
		Boolean hasSingleInstallment = true;
		List<DebtPosition> debtPositionList = createDebtPositionListAllData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_2Installments_WithSingleInstallment_AllData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_2Installments_NoSingleInstallment_AllData() throws Exception {
		int outputListSize = 2;
		Boolean hasSingleInstallment = false;
		List<DebtPosition> debtPositionList = createDebtPositionListAllData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_2Installments_NoSingleInstallment_AllData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_2Installments_WithSingleInstallment_MinimumData() throws Exception {
		int outputListSize = 2;
		Boolean hasSingleInstallment = true;
		List<DebtPosition> debtPositionList = createDebtPositionListMinimumData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_2Installments_WithSingleInstallment_MinimumData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGenerate_2Installments_NoSingleInstallment_MinimumData() throws Exception {
		int outputListSize = 2;
		Boolean hasSingleInstallment = false;
		List<DebtPosition> debtPositionList = createDebtPositionListMinimumData(hasSingleInstallment, outputListSize);

		String pdfFileName = "np_2Installments_NoSingleInstallments_MinimumData.pdf";
		byte[] pdfNoticePayment = generatePdfNoticePaymentFile(debtPositionList, pdfFileName);

		assertNotNull(pdfNoticePayment);
	}

	/**
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 
	 * @param pd_totalAmountPayment
	 * @param pd_expirationDate
	 * @param documentNumber
	 * @param installmentNumber
	 * @return
	 * @throws Exception
	 */
	private DebtPosition createDebtPositionAllData(BigDecimal pd_totalAmountPayment, Date pd_expirationDate,
			String documentNumber, Integer installmentNumber) throws Exception {
		DPPayer payer = DebtPositionGeneration.generatePayer(payer_uniqueIdentificationCode,
				payer_uniqueIdentificationType, payer_registry, payer_address, payer_numberStreet, payer_locality,
				payer_province, null, payer_postalCode, null, null);
		DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(pd_domainIdentifier, auxDigit,
				segregationCode, null, null, null, pd_totalAmountPayment, pd_causal, pd_expirationDate, null,
				documentNumber, installmentNumber);
		DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentsDetail(
				pd_totalAmountPayment, spd_orderSinglePayment, spd_causalDescriptionSinglePayment);
		List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
		singlePaymentDetailList.add(singlePaymentDetail);

		DebtPosition debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);

		return debtPosition;
	}

	/**
	 * 
	 * @param pd_totalAmountPayment
	 * @param documentNumber
	 * @param installmentNumber
	 * @return
	 * @throws Exception
	 */
	private DebtPosition createDebtPositionMinimumData(BigDecimal pd_totalAmountPayment, String documentNumber,
			Integer installmentNumber) throws Exception {
		DPPayer payer = DebtPositionGeneration.generatePayer(payer_uniqueIdentificationCode,
				payer_uniqueIdentificationType, payer_registry, null, null, null, null, null, null, null, null);
		DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(pd_domainIdentifier, auxDigit,
				segregationCode, null, null, null, pd_totalAmountPayment, pd_causal, null, null, documentNumber,
				installmentNumber);
		DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentsDetail(
				pd_totalAmountPayment, spd_orderSinglePayment, spd_causalDescriptionSinglePayment);
		List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
		singlePaymentDetailList.add(singlePaymentDetail);

		DebtPosition debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);

		return debtPosition;
	}

	/**
	 * 
	 * @param hasSingleInstallment
	 * @param outputListSize
	 * @return
	 * @throws Exception
	 */
	public List<DebtPosition> createDebtPositionListAllData(Boolean hasSingleInstallment, int outputListSize)
			throws Exception {
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		BigDecimal totalAmountPayment = BigDecimal.ZERO;
		String documentNumber = "docNumber0001";
		for (int i = 1; i <= outputListSize; i++) {
			BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11 * i);
			totalAmountPayment = totalAmountPayment.add(pd_totalAmountPayment);
			Date pd_expirationDate = addDays(new Date(), 10 * i);
			Integer installmentNumber = i;
			debtPositionList.add(createDebtPositionAllData(pd_totalAmountPayment, pd_expirationDate, documentNumber,
					installmentNumber));
		}

		if (hasSingleInstallment) {
			Date si_expirationDate = addDays(new Date(), 100);
			Integer installmentNumber = 0;
			debtPositionList.add(createDebtPositionAllData(totalAmountPayment, si_expirationDate, documentNumber,
					installmentNumber));
		}

		return debtPositionList;
	}

	/**
	 * 
	 * @param hasSingleInstallment
	 * @param outputListSize
	 * @return
	 * @throws Exception
	 */
	public List<DebtPosition> createDebtPositionListMinimumData(Boolean hasSingleInstallment, int outputListSize)
			throws Exception {
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		BigDecimal totalAmountPayment = BigDecimal.ZERO;
		String documentNumber = "docNumber0002";
		for (int i = 1; i <= outputListSize; i++) {
			BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11 * i);
			totalAmountPayment = totalAmountPayment.add(pd_totalAmountPayment);
			Integer installmentNumber = i;
			debtPositionList
					.add(createDebtPositionMinimumData(pd_totalAmountPayment, documentNumber, installmentNumber));
		}

		if (hasSingleInstallment) {
			Integer installmentNumber = 0;
			debtPositionList.add(createDebtPositionMinimumData(totalAmountPayment, documentNumber, installmentNumber));
		}

		return debtPositionList;
	}

	/**
	 * 
	 * @param debtPositionsList
	 * @param pdfFileName
	 * @return
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private byte[] generatePdfNoticePaymentFile(List<DebtPosition> debtPositionsList, String pdfFileName)
			throws Exception, FileNotFoundException, IOException {
		byte[] printNoticeDebtPosition = PaymentNoticeGeneration.generate(debtPositionsList,
				creditorInstitutionBuilder.build());
		OutputStream out = new FileOutputStream(path + pdfFileName);
		out.write(printNoticeDebtPosition);
		out.close();
		return printNoticeDebtPosition;
	}
}
