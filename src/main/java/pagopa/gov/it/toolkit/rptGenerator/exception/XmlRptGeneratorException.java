package pagopa.gov.it.toolkit.rptGenerator.exception;

/**
 * XML RPT Generator exception thrown in case of an error during the creation of
 * the RPT in XML format
 */
public class XmlRptGeneratorException extends IllegalArgumentException {

    private static final long serialVersionUID = -8365313547010865355L;

    /**
     * 
     */
    public XmlRptGeneratorException() {
    }

    /**
     * @param s
     */
    public XmlRptGeneratorException(String s) {
        super(s);
    }

    /**
     * @param cause
     */
    public XmlRptGeneratorException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public XmlRptGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
