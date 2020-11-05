package it.pagoPA.toolkit.debtPositionGenerator.business;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;
import it.pagoPA.toolkit.debtPositionGenerator.validation.DebtPositionValidation;
import it.pagoPA.toolkit.debtPositionGenerator.validation.DebtPositionValidationImpl;
import it.pagoPA.toolkit.iuvGenerator.IuvCodeGeneration;

public class DebtPositionBusiness {

	/**
	 * 
	 * @param debtPosition
	 */
	public static void validate(DebtPosition debtPosition) {
		DebtPositionValidation debtPositionValidation = new DebtPositionValidationImpl();
		debtPositionValidation.validate(debtPosition);
	}

	/**
	 * 
	 * @param debtPosition
	 * @throws Exception
	 */
	public static void generateIUV(DebtPosition debtPosition) throws Exception {
		String iuv = debtPosition.getPaymentDetail().getIuv();
		if (iuv == null || iuv.trim().isEmpty()) {
			debtPosition.getPaymentDetail()
					.setIuv(IuvCodeGeneration.generate(debtPosition.getPaymentDetail().getAuxDigit(),
							debtPosition.getPaymentDetail().getSegregationCode(),
							debtPosition.getPaymentDetail().getApplicationCode()));
		}
	}

	/**
	 * 
	 * @param debtPosition
	 */
	public static void generateNoticeNumber(DebtPosition debtPosition) {
		int auxDigit = debtPosition.getPaymentDetail().getAuxDigit();
		if (auxDigit == Constants.AUX_DIGIT_0) {
			debtPosition.getPaymentDetail().setNoticeNumber(Constants.AUX_DIGIT_0
					+ debtPosition.getPaymentDetail().getApplicationCode() + debtPosition.getPaymentDetail().getIuv());
		} else if (auxDigit == Constants.AUX_DIGIT_3) {
			debtPosition.getPaymentDetail()
					.setNoticeNumber("" + Constants.AUX_DIGIT_3 + debtPosition.getPaymentDetail().getIuv());
		}
	}

	/**
	 * 
	 * @param debtPosition
	 * @param paymentStatusEnum
	 */
	public static void changePaymentStatus(DebtPosition debtPosition, PaymentStatusEnum paymentStatusEnum) {
		debtPosition.getPaymentDetail().setPaymentStatus(paymentStatusEnum);
	}
}
