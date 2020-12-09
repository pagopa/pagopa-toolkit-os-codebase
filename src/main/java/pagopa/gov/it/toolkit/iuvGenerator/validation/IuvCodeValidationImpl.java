package pagopa.gov.it.toolkit.iuvGenerator.validation;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;
import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException;

/**
 * Implementation of IuvCodeValidation interface
 */
public class IuvCodeValidationImpl implements IuvCodeValidation {

    /**
     * Validate the debtPosition<br/>
     * The validation includes:
     * <ul>
     * <li>checkConstraints - validation by annotation
     * <li>checkAuxDigit0 - if <code>auxDigit</code> = 0
     * <code>applicationCode</code> must be present
     * <li>checkAuxDigit3 - if <code>auxDigit</code> = 3
     * <code>segregationCode</code> must be present
     * </ul>
     * 
     * @param iuvCodeGenerator
     *            the bean to validate
     * @throws ValidationException
     * @see IuvCodeGenerator
     * @see ValidationException
     */
    @Override
    public void validate(IuvCodeGenerator iuvCodeGenerator) throws ValidationException {
        checkConstraints(iuvCodeGenerator);

        checkAuxDigit0(iuvCodeGenerator);

        checkAuxDigit3(iuvCodeGenerator);
    }

    /**
     * @param objectToValidate
     *            the bean to validate.
     * @throws ValidationException
     * @see ValidationException
     */
    public <T> void checkConstraints(T objectToValidate) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> validationInputResults = validator.validate(objectToValidate);
        if (!validationInputResults.isEmpty()) {
            throw new ValidationException(validationInputResults);
        }
    }

    /**
     * @param iuvCodeGenerator
     * @see IuvCodeGenerator
     */
    private void checkAuxDigit0(IuvCodeGenerator iuvCodeGenerator) {
        if (iuvCodeGenerator.getAuxDigit() == Constants.AUX_DIGIT_0) {
            if (Optional.ofNullable(iuvCodeGenerator.getApplicationCode()).orElse(0) == 0) {
                throw new ValidationException(ErrorMessages.VALIDATION_APPLICATION_CODE_ERROR);
            }
        }
    }

    /**
     * @param iuvCodeGenerator
     * @see IuvCodeGenerator
     */
    private void checkAuxDigit3(IuvCodeGenerator iuvCodeGenerator) {
        if (iuvCodeGenerator.getAuxDigit() == Constants.AUX_DIGIT_3) {
            if (Optional.ofNullable(iuvCodeGenerator.getSegregationCode()).orElse(0) == 0) {
                throw new ValidationException(ErrorMessages.VALIDATION_SEGREGATION_CODE_ERROR);
            }
        }
    }
}
