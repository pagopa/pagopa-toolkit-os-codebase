package it.pagoPA.toolkit.iuvGenerator.validation;

import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.exception.ValidationException;

/**
 * IuvCodeValidation with iuvCodeGenerationBean
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
}
