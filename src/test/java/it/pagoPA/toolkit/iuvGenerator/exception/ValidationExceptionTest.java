/**
 * 
 */
package it.pagoPA.toolkit.iuvGenerator.exception;

import org.junit.Test;

import it.pagoPA.toolkit.iuvGenerator.IuvCodeGeneration;
import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.exception.ValidationException;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidation;
import it.pagoPA.toolkit.iuvGenerator.validation.IuvCodeValidationImpl;

/**
 *
 */
public class ValidationExceptionTest {

	/**
	 * @throws Exception
	 * 
	 */
	@Test(expected = ValidationException.class)
	public void testAuxDigit() throws Exception {
		IuvCodeGeneration.generate(1, 01, 02);
	}

	/**
	 * 
	 */
	@Test(expected = ValidationException.class)
	public void testAuxDigit0Validate() {
		IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();

		IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(0).setApplicationCode(null)
				.setSegregationCode(new Integer(05)).build();

		iuvCodeValidation.validate(iuvCodeGenerator);
	}

	/**
	 * 
	 */
	@Test(expected = ValidationException.class)
	public void testAuxDigit3Validate() {
		IuvCodeValidation iuvCodeValidation = new IuvCodeValidationImpl();

		IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(3)
				.setApplicationCode(new Integer(5)).setSegregationCode(null).build();

		iuvCodeValidation.validate(iuvCodeGenerator);
	}
}
