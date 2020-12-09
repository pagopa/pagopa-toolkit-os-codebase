package pagopa.gov.it.toolkit.iuvGenerator.business;

import pagopa.gov.it.toolkit.common.ErrorMessages;
import pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException;

/**
 * IUV code generator with IUV alghoritm interface
 */
public interface IuvAlghoritmGenerator {

    /**
     * Initialization of <code>IuvAlghoritmGenerator</code> class
     */
    public static class Builder {

        /**
         * Build the IuvAlghoritmGenerator based on <code>auxDigit</code>
         * 
         * @return a new instance of <code>IuvAlghoritmGenerator</code>
         */
        public IuvAlghoritmGenerator build(Integer auxDigit) throws ValidationException {
            switch (auxDigit) {
            case 0:
                return new IuvAlghoritmAuxDigit0();
            case 1:
            case 2:
                throw new ValidationException(ErrorMessages.VALIDATION_ALGORITHM_NOT_IMPLEMENTED_ERROR + auxDigit);
            case 3:
                return new IuvAlghoritmAuxDigit3();
            default:
                throw new ValidationException(ErrorMessages.VALIDATION_AUXDIGIT_NOT_ALLOWED_ERROR + auxDigit);
            }
        }
    }

    /**
     * Generates the IUV Code
     * 
     * @param segregationCode
     *            the segregation code
     * @param applicationCode
     *            the application code
     * @return the IUV Code
     */
    String generate(Integer segregationCode, Integer applicationCode);
}