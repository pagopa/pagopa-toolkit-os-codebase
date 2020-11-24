package it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Debt Position Payer class
 */
public class DPPayer {

    /**
     * Debt Position Payer builder class
     */
    public static class Builder {

        private String uniqueIdentificationCode;
        private StTipoIdentificativoUnivocoPersFG uniqueIdentificationType;
        private String registry;
        private String address;
        private String numberStreet;
        private String locality;
        private String province;
        private String nation;
        private String postalCode;
        private String email;
        private String mobile;

        /**
         * Build the Debt Position Payer
         * 
         * @return the Debt Position Payer
         */
        public DPPayer build() {
            return new DPPayer(this);
        }

        /**
         * 
         * @param address
         *            the address
         */
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * 
         * @param email
         *            the email
         */
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * 
         * @param locality
         *            the locality
         */
        public Builder setLocality(String locality) {
            this.locality = locality;
            return this;
        }

        /**
         * 
         * @param mobile
         *            the mobile
         */
        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        /**
         * 
         * @param nation
         *            the nation
         */
        public Builder setNation(String nation) {
            this.nation = nation;
            return this;
        }

        /**
         * 
         * @param postalCode
         *            the postalCode
         */
        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        /**
         * 
         * @param province
         *            the province
         */
        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        /**
         * 
         * @param registry
         *            the registry
         */
        public Builder setRegistry(String registry) {
            this.registry = registry;
            return this;
        }

        /**
         * 
         * @param numberStreet
         *            the numberStreet
         */
        public Builder setNumberStreet(String numberStreet) {
            this.numberStreet = numberStreet;
            return this;
        }

        /**
         * 
         * @param uniqueIdentificationType
         *            the uniqueIdentificationType
         */
        public Builder setUniqueIdentificationType(StTipoIdentificativoUnivocoPersFG uniqueIdentificationType) {
            this.uniqueIdentificationType = uniqueIdentificationType;
            return this;
        }

        /**
         * 
         * @param uniqueIdentificationCode
         *            the uniqueIdentificationCode
         */
        public Builder setUniqueIdentificationCode(String uniqueIdentificationCode) {
            this.uniqueIdentificationCode = uniqueIdentificationCode;
            return this;
        }
    }

    @NotEmpty
    @Size(max = 35)
    private String uniqueIdentificationCode;

    @NotNull
    private StTipoIdentificativoUnivocoPersFG uniqueIdentificationType;

    @NotEmpty
    @Size(max = 70)
    private String registry;

    @Size(max = 70)
    private String address;

    @Size(max = 16)
    private String numberStreet;

    @Size(max = 35)
    private String locality;

    @Size(max = 35)
    private String province;

    @Size(max = 2)
    @Pattern(regexp = Constants.REGEX_NATION, message = ErrorMessages.VALIDATION_UPPERCASE_FIELD)
    private String nation;

    @Size(max = 16)
    private String postalCode;

    @Size(max = 256)
    @Pattern(regexp = Constants.REGEX_EMAIL, message = ErrorMessages.VALIDATION_INVALID_EMAIL)
    private String email;

    @Size(max = 19)
    private String mobile;

    /**
     * Private constructor
     */
    private DPPayer() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     */
    private DPPayer(Builder builder) {
        this.uniqueIdentificationCode = builder.uniqueIdentificationCode;
        this.uniqueIdentificationType = builder.uniqueIdentificationType;
        this.registry = builder.registry;
        this.address = builder.address;
        this.numberStreet = builder.numberStreet;
        this.locality = builder.locality;
        this.province = builder.province;
        this.nation = builder.nation;
        this.postalCode = builder.postalCode;
        this.email = builder.email;
        this.mobile = builder.mobile;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @return the nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @return the registry
     */
    public String getRegistry() {
        return registry;
    }

    /**
     * @return the numberStreet
     */
    public String getNumberStreet() {
        return numberStreet;
    }

    /**
     * @return the uniqueIdentificationType
     */
    public StTipoIdentificativoUnivocoPersFG getUniqueIdentificationType() {
        return uniqueIdentificationType;
    }

    /**
     * @return the uniqueIdentificationCode
     */
    public String getUniqueIdentificationCode() {
        return uniqueIdentificationCode;
    }
}
