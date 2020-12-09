package pagopa.gov.it.toolkit.iuvGenerator.common;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.iuvGenerator.common.IuvSequenceReader;
import pagopa.gov.it.toolkit.iuvGenerator.common.IuvSequenceReaderImpl;

/**
 * Tests on generation of base iuv
 */
public class IuvSequenceReaderImplTest {

    private IuvSequenceReader iuvSequenceReader;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        iuvSequenceReader = new IuvSequenceReaderImpl();
    }

    /**
     * Test method on generation of base iuv
     * 
     * @see IuvSequenceReaderImpl#next()
     */
    @Test
    public void testNext() {
        String value = iuvSequenceReader.next();
        String next = iuvSequenceReader.next();

        assertEquals(new BigInteger(value).add(BigInteger.ONE), new BigInteger(next));
    }
}
