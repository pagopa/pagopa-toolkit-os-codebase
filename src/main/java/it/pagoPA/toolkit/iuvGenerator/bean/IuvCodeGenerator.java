package it.pagoPA.toolkit.iuvGenerator.bean;

import javax.validation.constraints.Digits;

/**
 * IUV Code generator class
 */
public class IuvCodeGenerator {

    /**
     * IUV Code generator builder class
     */
    public static class Builder {

        private int auxDigit;
        private Integer segregationCode;
        private Integer applicationCode;

        /**
         * Build the IUV Code generator
         * 
         * @return the IUV Code generator
         */
        public IuvCodeGenerator build() {
            return new IuvCodeGenerator(this);
        }

        /**
         * Set the application code
         * 
         * @param applicationCode
         *            the application code to set
         * @return the builder instance
         */
        public Builder setApplicationCode(Integer applicationCode) {
            this.applicationCode = applicationCode;
            return this;
        }

        /**
         * Set the aux digit
         * 
         * @param auxDigit
         *            the aux digit
         * @return the builder instance
         */
        public Builder setAuxDigit(int auxDigit) {
            this.auxDigit = auxDigit;
            return this;
        }

        /**
         * Set the segregation code
         * 
         * @param segregationCode
         *            the segregation code
         * @return the builder instance
         */
        public Builder setSegregationCode(Integer segregationCode) {
            this.segregationCode = segregationCode;
            return this;
        }
    }

    @Digits(integer = 1, fraction = 0)
    private int auxDigit;

    @Digits(integer = 2, fraction = 0)
    private Integer segregationCode;

    @Digits(integer = 2, fraction = 0)
    private Integer applicationCode;

    /**
     * Private constructor
     */
    private IuvCodeGenerator() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private IuvCodeGenerator(Builder builder) {
        this.auxDigit = builder.auxDigit;
        this.segregationCode = builder.segregationCode;
        this.applicationCode = builder.applicationCode;
    }

    /**
     * Get the application code
     * 
     * @return the application code
     */
    public Integer getApplicationCode() {
        return applicationCode;
    }

    /**
     * Get the aux digit
     * 
     * @return the aux digit
     */
    public int getAuxDigit() {
        return auxDigit;
    }

    /**
     * Get the segregation code
     * 
     * @return the segregation code
     */
    public Integer getSegregationCode() {
        return segregationCode;
    }
}
