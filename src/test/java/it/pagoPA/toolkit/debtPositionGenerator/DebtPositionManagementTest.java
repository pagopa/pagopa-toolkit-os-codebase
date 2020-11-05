/**
 * 
 */
package it.pagoPA.toolkit.debtPositionGenerator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionManagement;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;

public class DebtPositionManagementTest {

	DebtPosition debtPosition;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String uniqueIdentificationCode = "RSSMRA80A01F205X";
		String typeUniqueIdentifier = "F";
		String registry = "ROSSI MARIO";
		String domainIdentifier = "01234567890";
		int auxDigit = 3;
		Integer segregationCode = 01;
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

		debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidate() throws Exception {
		DebtPositionManagement.validate(debtPosition);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMakePayable() throws Exception {
		assertEquals(DebtPositionManagement.makePayable(debtPosition).getPaymentDetail().getPaymentStatus(),
				PaymentStatusEnum.PAYABLE);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMakeNotPayable() throws Exception {
		assertEquals(DebtPositionManagement.makeNotPayable(debtPosition).getPaymentDetail().getPaymentStatus(),
				PaymentStatusEnum.NOT_PAYABLE);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMakeCancel() throws Exception {
		assertEquals(DebtPositionManagement.makeCancel(debtPosition).getPaymentDetail().getPaymentStatus(),
				PaymentStatusEnum.CANCELED);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMakePaid() throws Exception {
		assertEquals(DebtPositionManagement.makePaid(debtPosition).getPaymentDetail().getPaymentStatus(),
				PaymentStatusEnum.PAID);
	}
}
