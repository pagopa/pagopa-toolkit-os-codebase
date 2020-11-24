package it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.common.bean.DatiMarcaBolloDigitale;

/**
 * Debt Position Single Payment Detail class
 */
public class DPSinglePaymentDetail {

    /**
     * Debt Position Single Payment Detail builder class
     */
    public static class Builder {

        private BigDecimal amountSinglePayment;
        private Integer orderSinglePayment;
        private String causalDescriptionSinglePayment;
        private String creditIban;
        private String creditBic;
        private String supportIban;
        private String supportBic;
        private DatiMarcaBolloDigitale datiMarcaBolloDigitale;

        /**
         * Build the Debt Position Single Payment Detail
         * 
         * @return the Debt Position Single Payment Detail
         */
        public DPSinglePaymentDetail build() {
            return new DPSinglePaymentDetail(this);
        }

        /**
         * @param amountSinglePayment
         *            the amountSinglePayment to set
         */
        public Builder setAmountSinglePayment(BigDecimal amountSinglePayment) {
            this.amountSinglePayment = amountSinglePayment;
            return this;
        }

        /**
         * @param causalDescriptionSinglePayment
         *            the causalDescriptionSinglePayment to set
         */
        public Builder setCausalDescriptionSinglePayment(String causalDescriptionSinglePayment) {
            this.causalDescriptionSinglePayment = causalDescriptionSinglePayment;
            return this;
        }

        /**
         * @param orderSinglePayment
         *            the orderSinglePayment to set
         */
        public Builder setOrderSinglePayment(Integer orderSinglePayment) {
            this.orderSinglePayment = orderSinglePayment;
            return this;
        }

        /**
         * @param creditIban
         *            the creditIban to set
         */
        public Builder setCreditIban(String creditIban) {
            this.creditIban = creditIban;
            return this;
        }

        /**
         * @param creditBic
         *            the creditBic to set
         */
        public Builder setCreditBic(String creditBic) {
            this.creditBic = creditBic;
            return this;
        }

        /**
         * @param supportIban
         *            the supportIban to set
         */
        public Builder setSupportIban(String supportIban) {
            this.supportIban = supportIban;
            return this;
        }

        /**
         * @param supportBic
         *            the supportBic to set
         */
        public Builder setSupportBic(String supportBic) {
            this.supportBic = supportBic;
            return this;
        }

        /**
         * @param datiMarcaBolloDigitale
         *            the datiMarcaBolloDigitale to set
         */
        public Builder setDatiMarcaBolloDigitale(DatiMarcaBolloDigitale datiMarcaBolloDigitale) {
            this.datiMarcaBolloDigitale = datiMarcaBolloDigitale;
            return this;
        }

    }

    @NotNull
    @Digits(integer = 9, fraction = 2)
    @DecimalMin(value = "0.01", message = ErrorMessages.VALIDATION_AMOUNT_MIN)
    private BigDecimal amountSinglePayment;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Integer orderSinglePayment;

    @NotEmpty
    @Size(max = 140)
    private String causalDescriptionSinglePayment;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String creditIban;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String creditBic;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String supportIban;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String supportBic;

    @Valid
    private DatiMarcaBolloDigitale datiMarcaBolloDigitale;

    /**
     * Private constructor
     */
    private DPSinglePaymentDetail() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     */
    private DPSinglePaymentDetail(Builder builder) {
        this.amountSinglePayment = builder.amountSinglePayment;
        this.orderSinglePayment = builder.orderSinglePayment;
        this.causalDescriptionSinglePayment = builder.causalDescriptionSinglePayment;
        this.creditIban = builder.creditIban;
        this.creditBic = builder.creditBic;
        this.supportIban = builder.supportIban;
        this.supportBic = builder.supportBic;
        this.datiMarcaBolloDigitale = builder.datiMarcaBolloDigitale;
    }

    /**
     * @return the amountSinglePayment
     */
    public BigDecimal getAmountSinglePayment() {
        return amountSinglePayment;
    }

    /**
     * @return the causalDescriptionSinglePayment
     */
    public String getCausalDescriptionSinglePayment() {
        return causalDescriptionSinglePayment;
    }

    /**
     * @return the orderSinglePayment
     */
    public Integer getOrderSinglePayment() {
        return orderSinglePayment;
    }

    /**
     * @return the creditIban
     */
    public String getCreditIban() {
        return creditIban;
    }

    /**
     * @return the creditBic
     */
    public String getCreditBic() {
        return creditBic;
    }

    /**
     * @return the supportIban
     */
    public String getSupportIban() {
        return supportIban;
    }

    /**
     * @return the supportBic
     */
    public String getSupportBic() {
        return supportBic;
    }

    /**
     * @return the datiMarcaBolloDigitale
     */
    public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale() {
        return datiMarcaBolloDigitale;
    }
}
