package it.eng.ToolkitPagoPA.iuvGenerator.validation;

import it.eng.ToolkitPagoPA.iuvGenerator.bean.IuvCodeGenerator;
import it.eng.ToolkitPagoPA.iuvGenerator.exception.ValidationException;

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
