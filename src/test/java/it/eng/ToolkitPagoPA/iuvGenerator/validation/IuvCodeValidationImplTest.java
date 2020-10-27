/**
 * 
 */
package it.eng.ToolkitPagoPA.iuvGenerator.validation;

import org.junit.Before;
import org.junit.Test;

import it.eng.ToolkitPagoPA.iuvGenerator.bean.IuvCodeGenerator;

/**
 *
 */
public class IuvCodeValidationImplTest {

	private IuvCodeValidation iuvCodeValidation;

	private IuvCodeGenerator iuvCodeGenerator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		iuvCodeValidation = new IuvCodeValidationImpl();
		
		iuvCodeGenerator = new IuvCodeGenerator.Builder()
				.setAuxDigit(3)
				.setApplicationCode(new Integer(5))
				.setSegregationCode(new Integer(25))
				.build();
	}

	/**
	 * Test method for {@link it.eng.ToolkitPagoPA.iuvGenerator.validation.IuvCodeValidationImpl#validate(it.eng.ToolkitPagoPA.iuvGenerator.bean.IuvCodeGenerator)}.
	 */
	@Test
	public void testValidate() {
		iuvCodeValidation.validate(iuvCodeGenerator);
	}

}
