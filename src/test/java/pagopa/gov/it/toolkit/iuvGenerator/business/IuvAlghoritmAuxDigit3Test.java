package pagopa.gov.it.toolkit.iuvGenerator.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests on the IUV code generation algorithm based on <code>auxDigit</code> = 3
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
     * Test method on IUV code generation when <code>auxDigit</code> = 3
     * 
     * @see IuvAlghoritmAuxDigit3#generate(Integer, Integer)
     */
    @Test
    public void testGenerate() {
        String iuvGenerated = iuvAlghoritmGenerator.generate(00, 01);
        assertNotNull(iuvGenerated);
        assertEquals(17, iuvGenerated.length());
    }
}
