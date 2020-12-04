package it.pagoPA.toolkit.debtPositionGenerator.validation;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.exception.ValidationException;
import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidation;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * DebtPositionValidation with debtPositionValidationBean
 */
public class DebtPositionValidationImpl implements DebtPositionValidation {

	/**
	 * @param debtPosition
	 * @throws ValidationException
	 */
	@Override
	public void validate(DebtPosition debtPosition) throws ValidationException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<DebtPosition>> validationInputResults = validator.validate(debtPosition);
		if (!validationInputResults.isEmpty()) {
			throw new ValidationException(validationInputResults);
		}

		checkUniqueIdentificationType(debtPosition);

		checkReceivedIuv(debtPosition);

		checkSinglePaymentsDetailList(debtPosition);

		checkAmounts(debtPosition);
	}

	/**
	 * 
	 * @param debtPosition
	 */
	private void checkUniqueIdentificationType(DebtPosition debtPosition) {
		if (debtPosition.getPayer().getUniqueIdentificationType().toUpperCase()
				.equals(Constants.IDENTIFIER_TYPE_PERSONA_GIURIDICA)) {
			if (GenericValidation.checkVatNumber(debtPosition.getPayer().getUniqueIdentificationCode())) {
				throw new ValidationException(ErrorMessages.VALIDATION_VAT_NUMBER_ERROR);
			}
		} else if (debtPosition.getPayer().getUniqueIdentificationType().toUpperCase()
				.equals(Constants.IDENTIFIER_TYPE_PERSONA_FISICA)) {
			if (GenericValidation.checkFiscalCode(debtPosition.getPayer().getUniqueIdentificationCode()) > 0) {
				throw new ValidationException(ErrorMessages.VALIDATION_FISCAL_CODE_ERROR);
			}
		}
	}

	/**
	 * 
	 * @param debtPosition
	 */
	private void checkReceivedIuv(DebtPosition debtPosition) {
		String iuv = debtPosition.getPaymentDetail().getIuv();
		int auxDigit = debtPosition.getPaymentDetail().getAuxDigit();
		Integer applicationCode = debtPosition.getPaymentDetail().getApplicationCode();
		if (iuv != null && !iuv.trim().isEmpty()) {
			IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(auxDigit)
					.setApplicationCode(debtPosition.getPaymentDetail().getApplicationCode())
					.setSegregationCode(debtPosition.getPaymentDetail().getSegregationCode()).build();
			IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();
			iuvCodeValidation.validate(iuvCodeGenerator);

			BigDecimal checkDigitReceived;
			BigDecimal checkDigitCalculated;
			if (auxDigit == Constants.AUX_DIGIT_0) {
				if (Optional.ofNullable(applicationCode).orElse(0) == 0) {
					throw new ValidationException(ErrorMessages.VALIDATION_APPLICATION_CODE_ERROR);
				}
				checkDigitReceived = new BigDecimal(iuv.substring(13));
				checkDigitCalculated = new BigDecimal(auxDigit + applicationCode + iuv.substring(0, 13))
						.remainder(new BigDecimal(93));
			} else if (auxDigit == Constants.AUX_DIGIT_3) {
				checkDigitReceived = new BigDecimal(iuv.substring(15));
				checkDigitCalculated = new BigDecimal(auxDigit + iuv.substring(0, 15)).remainder(new BigDecimal(93));
			} else {
				throw new ValidationException(ErrorMessages.VALIDATION_AUXDIGIT_ERROR);
			}
			if (checkDigitReceived.compareTo(checkDigitCalculated) != 0) {
				throw new ValidationException(ErrorMessages.VALIDATION_CHECK_DIGIT_ERROR);
			}
		}
	}

	/**
	 * 
	 * @param debtPosition
	 */
	private void checkSinglePaymentsDetailList(DebtPosition debtPosition) {
		if (debtPosition.getSinglePaymentDetailList().size() > Constants.SINGLE_PAYMENT_LIST_MAX_SIZE) {
			throw new ValidationException(ErrorMessages.VALIDATION_SINGLE_PAYMENT_LIST_SIZE_ERROR);
		}
	}

	/**
	 * 
	 * @param debtPosition
	 */
	private void checkAmounts(DebtPosition debtPosition) {
		BigDecimal amountSinglePaymentSum = debtPosition.getSinglePaymentDetailList().stream()
				.map(DPSinglePaymentDetail::getAmountSinglePayment).reduce(BigDecimal.ZERO, BigDecimal::add);
		if (debtPosition.getPaymentDetail().getTotalAmountPayment().compareTo(amountSinglePaymentSum) != 0) {
			throw new ValidationException(ErrorMessages.VALIDATION_AMOUNTS_ERROR);
		}
	}
}
