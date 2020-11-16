/**
 * 
 */
package it.pagoPA.toolkit.iuvGenerator.common;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.iuvGenerator.common.IuvSequenceReader;
import it.pagoPA.toolkit.iuvGenerator.common.IuvSequenceReaderImpl;

/**
 *
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
	 * Test method for
	 * {@link it.pagoPA.toolkit.iuvGenerator.common.IuvSequenceReaderImpl#next()}
	 * .
	 */
	@Test
	public void testNext() {
		String value = iuvSequenceReader.next();
		String next = iuvSequenceReader.next();

		assertEquals(new BigInteger(value).add(BigInteger.ONE), new BigInteger(next));
	}
}
