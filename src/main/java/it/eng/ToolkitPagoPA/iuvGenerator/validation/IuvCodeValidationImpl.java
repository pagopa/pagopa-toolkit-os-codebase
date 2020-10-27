package it.eng.ToolkitPagoPA.iuvGenerator.validation;

import java.util.Optional;

import it.eng.ToolkitPagoPA.iuvGenerator.bean.IuvCodeGenerator;
import it.eng.ToolkitPagoPA.iuvGenerator.exception.ValidationException;

/**
 * IuvCodeValidation with iuvCodeGenerationBean
 */
public class IuvCodeValidationImpl implements IuvCodeValidation {

	@Override
	public void validate(IuvCodeGenerator iuvCodeGenerator) throws ValidationException {
		if (iuvCodeGenerator.getAuxDigit() == 0) {
			if (Optional.ofNullable(iuvCodeGenerator.getApplicationCode()).orElse(0) == 0) {
				throw new ValidationException("ApplicationCode cannot be null for AuxDigit 0");

			}
		}

		if (iuvCodeGenerator.getAuxDigit() == 3) {
			if (Optional.ofNullable(iuvCodeGenerator.getSegregationCode()).orElse(0) == 0) {
				throw new ValidationException("SegregationCode cannot be null for AuxDigit 3");

			}
		}
	}
}
