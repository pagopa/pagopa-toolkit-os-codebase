package it.pagoPA.toolkit.paymentNoticeGenerator.validation;

import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.exception.ValidationException;

/**
 * PaymentNoticeValidation with payment notice
 */
public interface PaymentNoticeValidation {

	/**
	 * Validate the payment notice
	 * 
	 * @param paymentNotice
	 *            the Payment Notice Bean
	 * @throws ValidationException
	 */
	void validate(PaymentNotice paymentNotice) throws ValidationException;
}
