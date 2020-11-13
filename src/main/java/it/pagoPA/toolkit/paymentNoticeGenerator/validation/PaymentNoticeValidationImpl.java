package it.pagoPA.toolkit.paymentNoticeGenerator.validation;

import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.PaymentNoticeBusiness;
import it.pagoPA.toolkit.paymentNoticeGenerator.exception.ValidationException;

public class PaymentNoticeValidationImpl implements PaymentNoticeValidation {

	/**
	 * @param paymentNotice
	 * @throws ValidationException
	 */
	@Override
	public void validate(PaymentNotice paymentNotice) throws ValidationException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<PaymentNotice>> validationInputResults = validator.validate(paymentNotice);
		if (!validationInputResults.isEmpty()) {
			throw new ValidationException(validationInputResults);
		}

		checkInstallmentsDebtPosition(paymentNotice);
	}

	/**
	 * 
	 * @param paymentNotice
	 */
	private void checkInstallmentsDebtPosition(PaymentNotice paymentNotice) {
		if (paymentNotice.getDebtPositionList().size() == 1) {
			if (paymentNotice.getDebtPositionList().get(0).getPaymentDetail().getDocumentNumber() != null) {
				throw new ValidationException(ErrorMessages.VALIDATION_INVALID_DOCUMENT_NUMBER);
			}
			if (paymentNotice.getDebtPositionList().get(0).getPaymentDetail().getInstallmentNumber() != null) {
				throw new ValidationException(ErrorMessages.VALIDATION_INVALID_INSTALLMENT_NUMBER);
			}
		} else {
			HashMap<Integer, DebtPosition> sortedDebtPositionHashMap = PaymentNoticeBusiness
					.sortDebtPositionListByInstallmentNumberExcludingSingleInstallment(paymentNotice.getDebtPositionList());
			for (int i = 1; i <= sortedDebtPositionHashMap.size(); i++) {
				if (sortedDebtPositionHashMap.get(i) == null) {
					throw new ValidationException(ErrorMessages.VALIDATION_INSTALLMENTS_MISSING_INSTALLMENT_NUMBER + i);
				}
				if (sortedDebtPositionHashMap.get(i).getPaymentDetail().getDocumentNumber() == null) {
					throw new ValidationException(ErrorMessages.VALIDATION_INSTALLMENTS_MISSING_DOCUMENT_NUMBER);
				}
			}
		}
	}
}
