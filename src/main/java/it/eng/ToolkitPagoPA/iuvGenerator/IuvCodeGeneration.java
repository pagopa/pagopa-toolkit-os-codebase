package it.eng.ToolkitPagoPA.iuvGenerator;

import it.eng.ToolkitPagoPA.iuvGenerator.bean.IuvCodeGenerator;
import it.eng.ToolkitPagoPA.iuvGenerator.business.IuvAlghoritmGenerator;
import it.eng.ToolkitPagoPA.iuvGenerator.exception.ValidationException;
import it.eng.ToolkitPagoPA.iuvGenerator.validation.IuvCodeValidation;
import it.eng.ToolkitPagoPA.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * IuvCodeGeneration
 */
public class IuvCodeGeneration {

	/**
	 * Generate the IUV code
	 * 
	 * @param auxDigit
	 *            the aux digit
	 * @param segregationCode
	 *            the segregation code
	 * @param applicationCode
	 *            the application code
	 * @return String notification code
	 * @throws ValidationException
	 */
	public static String generate(int auxDigit, Integer segregationCode, Integer applicationCode)
			throws ValidationException {

		IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder()
				.setAuxDigit(auxDigit)
				.setSegregationCode(segregationCode)
				.setApplicationCode(applicationCode)
				.build();

		IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();
		iuvCodeValidation.validate(iuvCodeGenerator);

		IuvAlghoritmGenerator iuvGenerator = new IuvAlghoritmGenerator.Builder().build(auxDigit);
		return iuvGenerator.generate(segregationCode, applicationCode);
	}
}