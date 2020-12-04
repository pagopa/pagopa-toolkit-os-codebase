package it.pagoPA.toolkit.iuvGenerator.business;

import java.text.DecimalFormat;

/**
 * IUV code generator with IUV alghoritm aux digit 0
 */
public class IuvAlghoritmAuxDigit0 extends IuvAlghoritm implements IuvAlghoritmGenerator {

    private int auxDigit = 0;

    /**
     * Protected constructor
     */
    protected IuvAlghoritmAuxDigit0() {
        // NOPE
    }

    /**
     * Generate the IUV Code <br>
     * IUV (15 digits) = <IUV base (max 13n)><IUV check digit (2n)>
     */
    @Override
    public String generate(Integer segregationCode, Integer applicationCode) {
        String applicationCodeString = new DecimalFormat("00").format(applicationCode);
        String iuvBase13Digits = generateIuBase13Digits();
        String checkDigit = generateCheckDigit(String.valueOf(auxDigit) + applicationCodeString + iuvBase13Digits);
        return iuvBase13Digits + checkDigit;
    }
}