package it.pagoPA.toolkit.paymentNoticeGenerator.exception;

import org.junit.Test;

/**
 *
 */
public class ValidationExceptionTest {

	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testInvalidDocumentNumber() throws Exception {
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testInvalidInstallmentNumber() throws Exception {
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testMissingInstallmentNumber() throws Exception {
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testMissingDocumentNumber() throws Exception {
		
	}
}
