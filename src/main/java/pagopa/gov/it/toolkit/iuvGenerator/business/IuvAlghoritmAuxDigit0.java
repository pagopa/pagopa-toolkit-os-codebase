package pagopa.gov.it.toolkit.iuvGenerator.business;

import java.text.DecimalFormat;

/**
 * IUV code generation algorithm based on <code>auxDigit</code> = 0
 */
public class IuvAlghoritmAuxDigit0 extends IuvAlghoritm {

    private int auxDigit = 0;

    /**
     * Protected constructor
     */
    protected IuvAlghoritmAuxDigit0() {
        // NOPE
    }

    /**
     * Generates the IUV Code.<br/>
     * IUV (15 digits) = &lt;IUV base (max 13n)&gt;&lt;IUV check digit (2n)&gt;
     */
    @Override
    public String generate(Integer segregationCode, Integer applicationCode) {
        String applicationCodeString = new DecimalFormat("00").format(applicationCode);
        String iuvBase13Digits = generateIuBase13Digits();
        String checkDigit = generateCheckDigit(String.valueOf(auxDigit) + applicationCodeString + iuvBase13Digits);
        return iuvBase13Digits + checkDigit;
    }
}