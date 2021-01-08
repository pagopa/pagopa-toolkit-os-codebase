package pagopa.gov.it.toolkit.reader;
/**
 * Reader exception thrown in case of an error during the processing of
 * the debt positions file
 */
public class ReaderException extends IllegalArgumentException {

	private static final long serialVersionUID = 853604672222209259L;
	
	/**
     * 
     */
	public ReaderException() {
		
	}
	
	/**
     * @param s
     */
	public ReaderException(String s) {
		super(s);
	}
	
	/**
     * @param cause
     */
	public ReaderException(Throwable cause) {
		super(cause);
	}
	
	/**
     * @param message
     * @param cause
     */
	public ReaderException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
