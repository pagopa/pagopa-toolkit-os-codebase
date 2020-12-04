/**
 * 
 */
package it.pagoPA.toolkit.debtPositionGenerator.exception;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.exception.ValidationException;

public class DebtPositionExceptionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testValidate() throws Exception {
		DPPayer payer = DebtPositionGeneration.generatePayer(null, null, null, null, null, null, null, null, null, null,
				null);
		DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(null, 0, null, null, null, null,
				null, null, null, null, null, null);
		DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentsDetail(null, null,
				null);
		List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
		singlePaymentDetailList.add(singlePaymentDetail);
		DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = it.pagoPA.toolkit.iuvGenerator.exception.ValidationException.class)
	public void testReceivedIuv() throws Exception {
		String uniqueIdentificationCode = "RSSMRA80A01F205X";
		String typeUniqueIdentifier = "F";
		String registry = "ROSSI MARIO";
		String domainIdentifier = "01234567890";
		int auxDigit = 3;
		Integer segregationCode = null;
		String iuv = "01202000000003130";
		BigDecimal totalAmountPayment = BigDecimal.valueOf(11.23);
		String causal = "causal test";
		BigDecimal amountSinglePayment = BigDecimal.valueOf(11.23);
		Integer orderSinglePayment = 1;
		String causalDescriptionSinglePayment = "single payment causal";

		DPPayer payer = DebtPositionGeneration.generatePayer(uniqueIdentificationCode, typeUniqueIdentifier, registry,
				null, null, null, null, null, null, null, null);
		DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,
				segregationCode, null, iuv, null, totalAmountPayment, causal, null, null, null, null);
		DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration
				.generateSinglePaymentsDetail(amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment);
		List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
		singlePaymentDetailList.add(singlePaymentDetail);
		DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
	}
}
