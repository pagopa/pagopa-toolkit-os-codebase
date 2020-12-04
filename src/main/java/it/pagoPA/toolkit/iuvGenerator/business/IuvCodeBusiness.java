package it.pagoPA.toolkit.iuvGenerator.business;

import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidation;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 * 
 */
public class IuvCodeBusiness {

	/**
	 * 
	 * @param auxDigit
	 * @param segregationCode
	 * @param applicationCode
	 * @return
	 */
	public static String generateIUV(int auxDigit, Integer segregationCode, Integer applicationCode) {
		IuvAlghoritmGenerator iuvGenerator = new IuvAlghoritmGenerator.Builder().build(auxDigit);
		return iuvGenerator.generate(segregationCode, applicationCode);
	}

	/**
	 * 
	 * @param iuvCodeGenerator
	 */
	public static void validate(IuvCodeGenerator iuvCodeGenerator) {
		IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();
		iuvCodeValidation.validate(iuvCodeGenerator);
	}
}
