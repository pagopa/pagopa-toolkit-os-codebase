package it.pagoPA.toolkit.iuvGenerator.exception;

/**
 * Unexpected value exception
 */
public class UnexpectedValueException extends IllegalArgumentException {

	private static final long serialVersionUID = -5073413451295089169L;

	/**
	 * 
	 */
	public UnexpectedValueException() {
	}

	/**
	 * @param s
	 */
	public UnexpectedValueException(String s) {
		super(s);
	}

	/**
	 * @param cause
	 */
	public UnexpectedValueException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnexpectedValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
