package it.pagoPA.toolkit.paymentNoticeGenerator.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import it.pagoPA.toolkit.common.ErrorMessages;

/**
 * Validation exception
 */
public class ValidationException extends IllegalArgumentException {

	private static final long serialVersionUID = 7340114022568985737L;

	/**
	 * 
	 */
	public <T> ValidationException(Set<ConstraintViolation<T>> validationInputResults) {
		super(generateErrorMessage(validationInputResults));
	}

	/**
	 * 
	 */
	public ValidationException() {
	}

	/**
	 * @param s
	 */
	public ValidationException(String s) {
		super(s);
	}

	/**
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param validationInputResults
	 * @return
	 */
	private static <T> String generateErrorMessage(Set<ConstraintViolation<T>> validationInputResults) {
		String errorMsg = ErrorMessages.VALIDATION_ERROR;

		for (ConstraintViolation<T> validationError : validationInputResults) {
			String validationErrorMsg = validationError.getPropertyPath() + "[" + validationError.getMessage() + "]; ";
			errorMsg = errorMsg + validationErrorMsg;
		}

		return errorMsg;
	}
}
