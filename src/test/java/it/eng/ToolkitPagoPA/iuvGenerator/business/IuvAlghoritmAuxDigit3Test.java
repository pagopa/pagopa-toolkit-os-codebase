/**
 * 
 */
package it.eng.ToolkitPagoPA.iuvGenerator.business;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class IuvAlghoritmAuxDigit3Test {

	private IuvAlghoritmAuxDigit3 iuvAlghoritmGenerator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		iuvAlghoritmGenerator = new IuvAlghoritmAuxDigit3();
	}

	@Test
	public void test() {
		assertNotNull(iuvAlghoritmGenerator.generate(00, 01));
	}
}
