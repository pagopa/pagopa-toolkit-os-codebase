package pagopa.gov.it.toolkit.iuvGenerator.validation;

import pagopa.gov.it.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException;

/**
 * Validation of IUV interface
 */
public interface IuvCodeValidation {

    /**
     * Validate the iuvCodeGenerator
     * 
     * @param iuvCodeGenerator
     *            the IUV code generator
     * @throws ValidationException
     */
    void validate(IuvCodeGenerator iuvCodeGenerator) throws ValidationException;

    /**
     * Constraints validation by annotation of the single input of the
     * iuvCodeGenerator
     * 
     * @param objectToValidate
     * @throws ValidationException
     */
    <T> void checkConstraints(T objectToValidate) throws ValidationException;
}
