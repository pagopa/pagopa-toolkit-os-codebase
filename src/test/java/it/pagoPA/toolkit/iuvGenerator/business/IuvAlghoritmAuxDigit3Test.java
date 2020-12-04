/**
 * 
 */
package it.pagoPA.toolkit.iuvGenerator.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit3;

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

  /**
   * Test method for
   * {@link it.pagoPA.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit3#generate( java.lang.Integer, java.lang.Integer)}
   */
  @Test
  public void testGenerate() {
    String iuvGenerated = iuvAlghoritmGenerator.generate(00, 01);
    assertNotNull(iuvGenerated);
    assertEquals(17, iuvGenerated.length());
  }
}
