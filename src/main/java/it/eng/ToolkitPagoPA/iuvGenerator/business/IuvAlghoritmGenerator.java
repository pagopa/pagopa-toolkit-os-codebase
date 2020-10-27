package it.eng.ToolkitPagoPA.iuvGenerator.business;

import it.eng.ToolkitPagoPA.iuvGenerator.exception.ValidationException;

/**
 * IUV code generator with IUV alghoritm interface
 */
public interface IuvAlghoritmGenerator {

	/**
	 * IuvAlghoritmGenerator builder class
	 */
	public static class Builder {

		/**
		 * Build the IIuvAlghoritmGenerator
		 * 
		 * @return the IuvAlghoritmGenerator
		 */
		public IuvAlghoritmGenerator build(Integer auxDigit) throws ValidationException {
			switch (auxDigit) {
			case 0:
				return new IuvAlghoritmAuxDigit0();
			case 1:
			case 2:
				throw new ValidationException("Algorithm not implemented for AuxDigit [" + auxDigit + "]");
			case 3:
				return new IuvAlghoritmAuxDigit3();
			default:
				throw new ValidationException("AuxDigit [" + auxDigit + "] not allowed");
			}
		}
	}

	/**
	 * Generate the IUV Code <br>
	 * 
	 * @param segregationCode
	 *            the segregation code
	 * @param applicationCode
	 *            the application code
	 * @return the IUV Code
	 */
	String generate(Integer segregationCode, Integer applicationCode);
}