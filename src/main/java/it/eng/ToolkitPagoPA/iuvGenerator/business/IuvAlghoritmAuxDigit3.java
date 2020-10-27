package it.eng.ToolkitPagoPA.iuvGenerator.business;

import java.text.DecimalFormat;

/**
 * IUV code generator with IUV alghoritm aux digit 3
 */
public class IuvAlghoritmAuxDigit3 extends IuvAlghoritm implements IuvAlghoritmGenerator {

	private int auxDigit = 3;

	/**
	 * Protected constructor
	 */
	protected IuvAlghoritmAuxDigit3() {
		// NOPE
	}

	/**
	 * Generate the IUV Code <br>
	 * IUV (17 digits) = <codice segregazione (2n)><IUV base (max 13n)><IUV check
	 * digit (2n)>
	 */
	@Override
	public String generate(Integer segregationCode, Integer applicationCode) {
		String segregationCodeString = new DecimalFormat("00").format(segregationCode);
		String iuvBase13Digits = generateIuBase13Digits();
		String checkDigit = generateCheckDigit(String.valueOf(auxDigit) + segregationCodeString + iuvBase13Digits);
		return segregationCodeString + iuvBase13Digits + checkDigit;
	}
}