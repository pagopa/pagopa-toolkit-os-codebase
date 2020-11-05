package it.pagoPA.toolkit.debtPositionGenerator.validation;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.exception.ValidationException;

/**
 * DebtPositionValidation with DebtPosition
 */
public interface DebtPositionValidation {

	/**
	 * Validate the debtPosition
	 * 
	 * @param debtPosition
	 *            the Debt Position Bean
	 * @throws ValidationException
	 */
	void validate(DebtPosition debtPosition) throws ValidationException;
}
