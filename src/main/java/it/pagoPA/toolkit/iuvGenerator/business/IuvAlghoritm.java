package it.pagoPA.toolkit.iuvGenerator.business;

import java.math.BigDecimal;

import it.pagoPA.toolkit.iuvGenerator.common.IuvSequenceReaderImpl;

/**
 * IUV alghoritm abstract class.
 */
public abstract class IuvAlghoritm implements IuvAlghoritmGenerator {

	private static final String DIGIT_OF_2 = "%02d";
	
	/**
	 * Generate the check digit
	 * 
	 * @param checkDigitComponent
	 *            check digit component
	 * @return the generated check digit
	 */
	protected String generateCheckDigit(String checkDigitComponent) {
		return String.format(DIGIT_OF_2, (new BigDecimal(checkDigitComponent).remainder(new BigDecimal(93))).intValue());
	}

	/**
	 * Generate the IU base 13 digits
	 * 
	 * @return the IU base 13 digits
	 */
	protected String generateIuBase13Digits() {
		IuvSequenceReaderImpl iuvSequenceReaderImpl = new IuvSequenceReaderImpl();
		return iuvSequenceReaderImpl.next();
	}
}