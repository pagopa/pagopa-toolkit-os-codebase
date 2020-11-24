/**
 * 
 */
package it.pagoPA.toolkit.iuvGenerator.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class IuvAlghoritmAuxDigit0Test {

    private IuvAlghoritmAuxDigit0 iuvAlghoritmGenerator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        iuvAlghoritmGenerator = new IuvAlghoritmAuxDigit0();
    }

    /**
     * Test method for
     * {@link it.pagoPA.toolkit.iuvGenerator.business.IuvAlghoritmAuxDigit0#generate( java.lang.Integer, java.lang.Integer)}
     */
    @Test
    public void testGenerate() {
        String iuvGenerated = iuvAlghoritmGenerator.generate(0, 01);
        assertNotNull(iuvGenerated);
        assertEquals(15, iuvGenerated.length());
    }
}
