package it.pagoPA.toolkit.paymentNoticeGenerator.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Payment Notice Creditor Institution class
 */
public class PNCreditorInstitution {

    /**
     * Payment Notice Creditor Institution builder class
     */
    public static class Builder {

        private byte[] logo;
        private String name;
        private String sector;
        private String info;
        private String fiscalCode;
        private String cbillCode;
        private String postalAccountHolder;
        private String postalAccountNumber;
        private String postalAuthorizationCode;
        private String website;

        /**
         * Build the Payment Notice Creditor Institution
         * 
         * @return the Payment Notice Creditor Institution
         */
        public PNCreditorInstitution build() {
            return new PNCreditorInstitution(this);
        }

        /**
         * @param logo
         *            the logo to set
         */
        public Builder setLogo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        /**
         * @param name
         *            the name to set
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * @param sector
         *            the sector to set
         */
        public Builder setSector(String sector) {
            this.sector = sector;
            return this;
        }

        /**
         * @param info
         *            the info to set
         */
        public Builder setInfo(String info) {
            this.info = info;
            return this;
        }

        /**
         * @param fiscalCode
         *            the fiscalCode to set
         */
        public Builder setFiscalCode(String fiscalCode) {
            this.fiscalCode = fiscalCode;
            return this;
        }

        /**
         * @param cbillCode
         *            the cbillCode to set
         */
        public Builder setCbillCode(String cbillCode) {
            this.cbillCode = cbillCode;
            return this;
        }

        /**
         * @param postalAccountHolder
         *            the postalAccountHolder to set
         */
        public Builder setPostalAccountHolder(String postalAccountHolder) {
            this.postalAccountHolder = postalAccountHolder;
            return this;
        }

        /**
         * @param postalAccountNumber
         *            the postalAccountNumber to set
         */
        public Builder setPostalAccountNumber(String postalAccountNumber) {
            this.postalAccountNumber = postalAccountNumber;
            return this;
        }

        /**
         * @param postalAuthorizationCode
         *            the postalAuthorizationCode to set
         */
        public Builder setPostalAuthorizationCode(String postalAuthorizationCode) {
            this.postalAuthorizationCode = postalAuthorizationCode;
            return this;
        }

        /**
         * @param website
         *            the website to set
         */
        public Builder setWebsite(String website) {
            this.website = website;
            return this;
        }

    }

    @NotEmpty
    private byte[] logo;

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 50)
    private String sector;

    @NotEmpty
    @Size(max = 100)
    private String info;

    @NotEmpty
    @Size(max = 16)
    private String fiscalCode;

    @NotEmpty
    @Size(min = 5, max = 5)
    private String cbillCode;

    @Size(max = 50)
    private String postalAccountHolder;

    @Size(min = 12, max = 12)
    private String postalAccountNumber;

    @Size(max = 50)
    private String postalAuthorizationCode;

    @NotEmpty
    @Size(max = 100)
    private String website;

    /**
     * Private constructor
     */
    private PNCreditorInstitution() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     */
    private PNCreditorInstitution(Builder builder) {
        this.cbillCode = builder.cbillCode;
        this.fiscalCode = builder.fiscalCode;
        this.info = builder.info;
        this.logo = builder.logo;
        this.name = builder.name;
        this.postalAccountHolder = builder.postalAccountHolder;
        this.postalAccountNumber = builder.postalAccountNumber;
        this.postalAuthorizationCode = builder.postalAuthorizationCode;
        this.sector = builder.sector;
        this.website = builder.website;
    }

    /**
     * @return the logo
     */
    public byte[] getLogo() {
        return logo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @return the fiscalCode
     */
    public String getFiscalCode() {
        return fiscalCode.toUpperCase();
    }

    /**
     * @return the cbillCode
     */
    public String getCbillCode() {
        return cbillCode;
    }

    /**
     * @return the postalAccountHolder
     */
    public String getPostalAccountHolder() {
        return postalAccountHolder;
    }

    /**
     * @return the postalAccountNumber
     */
    public String getPostalAccountNumber() {
        return postalAccountNumber;
    }

    /**
     * @return the postalAuthorizationCode
     */
    public String getPostalAuthorizationCode() {
        return postalAuthorizationCode;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }
}
