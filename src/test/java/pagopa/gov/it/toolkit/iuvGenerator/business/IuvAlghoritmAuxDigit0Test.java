package pagopa.gov.it.toolkit.iuvGenerator.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests on the IUV code generation algorithm based on <code>auxDigit</code> = 0
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
     * Test method on IUV code generation when <code>auxDigit</code> = 0
     * 
     * @see IuvAlghoritmAuxDigit0#generate(Integer, Integer)
     */
    @Test
    public void testGenerate() {
        String iuvGenerated = iuvAlghoritmGenerator.generate(0, 01);
        assertNotNull(iuvGenerated);
        assertEquals(15, iuvGenerated.length());
    }
}
