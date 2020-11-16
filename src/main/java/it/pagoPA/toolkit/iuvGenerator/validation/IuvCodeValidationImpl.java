package it.pagoPA.toolkit.iuvGenerator.validation;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.exception.ValidationException;

/**
 * IuvCodeValidation with iuvCodeGenerationBean
 */
public class IuvCodeValidationImpl implements IuvCodeValidation {

	/**
	 * @param iuvCodeGenerator
	 * @throws ValidationException
	 */
	@Override
	public void validate(IuvCodeGenerator iuvCodeGenerator) throws ValidationException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<IuvCodeGenerator>> validationInputResults = validator.validate(iuvCodeGenerator);
		if (!validationInputResults.isEmpty()) {
			throw new ValidationException(validationInputResults);
		}

		checkAuxDigit0(iuvCodeGenerator);

		checkAuxDigit3(iuvCodeGenerator);
	}

	/**
	 * 
	 * @param iuvCodeGenerator
	 */
	private void checkAuxDigit0(IuvCodeGenerator iuvCodeGenerator) {
		if (iuvCodeGenerator.getAuxDigit() == Constants.AUX_DIGIT_0) {
			if (Optional.ofNullable(iuvCodeGenerator.getApplicationCode()).orElse(0) == 0) {
				throw new ValidationException(ErrorMessages.VALIDATION_APPLICATION_CODE_ERROR);

			}
		}
	}

	/**
	 * 
	 * @param iuvCodeGenerator
	 */
	private void checkAuxDigit3(IuvCodeGenerator iuvCodeGenerator) {
		if (iuvCodeGenerator.getAuxDigit() == Constants.AUX_DIGIT_3) {
			if (Optional.ofNullable(iuvCodeGenerator.getSegregationCode()).orElse(0) == 0) {
				throw new ValidationException(ErrorMessages.VALIDATION_SEGREGATION_CODE_ERROR);
			}
		}
	}
}
