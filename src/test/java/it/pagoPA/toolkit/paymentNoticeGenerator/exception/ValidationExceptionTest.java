package it.pagoPA.toolkit.paymentNoticeGenerator.exception;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.paymentNoticeGenerator.PaymentNoticeGeneration;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution.Builder;

/**
 *
 */
public class ValidationExceptionTest {

	Builder creditorInstitutionBuilder = new PNCreditorInstitution.Builder();
	String path = "src/test/resources/";
	Path logoPath = Paths.get(path + "logoTest.png");

	String ci_name = "nome Ente Creditore";
	String ci_sector = "settore Ente Creditore";
	String ci_info = "info Ente Creditore";
	String ci_fiscalCode = "01234567890";
	String ci_cbillCode = "98765";
	String ci_website = "www.enteCreditore.it";

	String payer_uniqueIdentificationCode = "RSSMRA80A01F205X";
	String payer_uniqueIdentificationType = "F";
	String payer_registry = "ROSSI MARIO";

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
		creditorInstitutionBuilder.setCbillCode(ci_cbillCode).setFiscalCode(ci_fiscalCode).setInfo(ci_info)
				.setName(ci_name).setSector(ci_sector).setLogo(logoData).setWebsite(ci_website);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testValidate() throws Exception {
		BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11);
		DebtPosition debtPosition = createDebtPosition(pd_totalAmountPayment, null, null);
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		debtPositionList.add(debtPosition);
		PaymentNoticeGeneration.generate(debtPositionList, null);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testInvalidDocumentNumber() throws Exception {
		BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11);
		String documentNumber = "docNumber0001";
		DebtPosition debtPosition = createDebtPosition(pd_totalAmountPayment, documentNumber, null);
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		debtPositionList.add(debtPosition);
		PaymentNoticeGeneration.generate(debtPositionList, creditorInstitutionBuilder.build());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testInvalidInstallmentNumber() throws Exception {
		BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11);
		Integer installmentNumber = 1;
		DebtPosition debtPosition = createDebtPosition(pd_totalAmountPayment, null, installmentNumber);
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		debtPositionList.add(debtPosition);
		PaymentNoticeGeneration.generate(debtPositionList, creditorInstitutionBuilder.build());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testMissingInstallmentNumber() throws Exception {
		int outputListSize = 3;// deve essere maggiore di 2
		Boolean hasSingleInstallment = false;
		List<DebtPosition> debtPositionList = createDebtPositionListWithoutInstallmentNumberOne(hasSingleInstallment,
				outputListSize);
		// debtPositionList.add(insertErrorDebtPosition(debtPositionList));
		PaymentNoticeGeneration.generate(debtPositionList, creditorInstitutionBuilder.build());
	}

	/**
	 * 
	 * @param pd_totalAmountPayment
	 * @param documentNumber
	 * @param installmentNumber
	 * @return
	 * @throws Exception
	 */
	private DebtPosition createDebtPosition(BigDecimal pd_totalAmountPayment, String documentNumber,
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
	public List<DebtPosition> createDebtPositionListWithoutInstallmentNumberOne(Boolean hasSingleInstallment,
			int outputListSize) throws Exception {
		List<DebtPosition> debtPositionList = new LinkedList<DebtPosition>();
		BigDecimal totalAmountPayment = BigDecimal.ZERO;
		String documentNumber = "docNumber0001";
		for (int i = 2; i <= outputListSize; i++) {
			BigDecimal pd_totalAmountPayment = BigDecimal.valueOf(11.11 * i);
			totalAmountPayment = totalAmountPayment.add(pd_totalAmountPayment);
			Integer installmentNumber = i;
			debtPositionList.add(createDebtPosition(pd_totalAmountPayment, documentNumber, installmentNumber));
		}

		if (hasSingleInstallment) {
			Integer installmentNumber = 0;
			debtPositionList.add(createDebtPosition(totalAmountPayment, documentNumber, installmentNumber));
		}

		return debtPositionList;
	}
}
