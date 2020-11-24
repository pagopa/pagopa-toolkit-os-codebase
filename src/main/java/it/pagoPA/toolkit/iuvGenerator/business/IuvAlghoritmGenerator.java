package it.pagoPA.toolkit.iuvGenerator.business;

import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.iuvGenerator.exception.ValidationException;

/**
 * IUV code generator with IUV alghoritm interface
 */
public interface IuvAlghoritmGenerator {

    /**
     * IuvAlghoritmGenerator builder class
     */
    public static class Builder {

        /**
         * Build the IIuvAlghoritmGenerator
         * 
         * @return the IuvAlghoritmGenerator
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
     * Generate the IUV Code <br>
     * 
     * @param segregationCode
     *            the segregation code
     * @param applicationCode
     *            the application code
     * @return the IUV Code
     */
    String generate(Integer segregationCode, Integer applicationCode);
}