package it.pagoPA.toolkit.paymentNoticeGenerator;

import java.util.List;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.PaymentNoticeBusiness;

/**
 * PaymentNoticeGeneration
 */
public class PaymentNoticeGeneration {

	/**
	 * Generate the pdf payment notice N.B.: debtPositionList is recommended it
	 * has same Payer informations and same PaymentDetail causal
	 * 
	 * @param debtPositionList
	 * @param creditorInstitution
	 * @return paymentNotice (ByteArray)
	 * @throws Exception
	 */
	public static byte[] generate(List<DebtPosition> debtPositionList, PNCreditorInstitution creditorInstitution)
			throws Exception {

		PaymentNotice paymentNotice = new PaymentNotice.Builder().setDebtPositionList(debtPositionList)
				.setCreditorInstitution(creditorInstitution).build();

		PaymentNoticeBusiness.validate(paymentNotice);

		return PaymentNoticeBusiness.generatePaymentNotice(paymentNotice);
	}
}
