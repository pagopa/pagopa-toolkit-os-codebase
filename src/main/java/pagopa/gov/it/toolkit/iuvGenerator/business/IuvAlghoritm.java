package pagopa.gov.it.toolkit.iuvGenerator.business;

import java.math.BigDecimal;

import pagopa.gov.it.toolkit.iuvGenerator.common.IuvSequenceReaderImpl;

/**
 * IUV alghoritm abstract class
 */
public abstract class IuvAlghoritm implements IuvAlghoritmGenerator {

    private static final String DIGIT_OF_2 = "%02d";

    /**
     * Calculates the check digit of IUV code
     * 
     * @param checkDigitComponent
     *            check digit component
     * @return the generated check digit
     */
    protected String generateCheckDigit(String checkDigitComponent) {
        return String.format(DIGIT_OF_2,
                (new BigDecimal(checkDigitComponent).remainder(new BigDecimal(93))).intValue());
    }

    /**
     * Generates the IUV base 13 digits
     * 
     * @return the IUV base
     */
    protected String generateIuBase13Digits() {
        IuvSequenceReaderImpl iuvSequenceReaderImpl = new IuvSequenceReaderImpl();
        return iuvSequenceReaderImpl.next();
    }
}