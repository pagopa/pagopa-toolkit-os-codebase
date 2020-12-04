package it.pagoPA.toolkit.iuvGenerator;

import it.pagoPA.toolkit.iuvGenerator.bean.IuvCodeGenerator;
import it.pagoPA.toolkit.iuvGenerator.business.IuvCodeBusiness;

/**
 * IuvCodeGeneration
 */
public class IuvCodeGeneration {

	/**
	 * Generate the IUV code
	 * 
	 * @param auxDigit
	 *            the aux digit
	 * @param segregationCode
	 *            the segregation code
	 * @param applicationCode
	 *            the application code
	 * @return String notification code
	 * @throws Exception
	 */
	public static String generate(int auxDigit, Integer segregationCode, Integer applicationCode) throws Exception {

		IuvCodeGenerator iuvCodeGenerator = new IuvCodeGenerator.Builder().setAuxDigit(auxDigit)
				.setSegregationCode(segregationCode).setApplicationCode(applicationCode).build();

		IuvCodeBusiness.validate(iuvCodeGenerator);

		return IuvCodeBusiness.generateIUV(auxDigit, segregationCode, applicationCode);
	}
}