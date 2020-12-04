package it.pagoPA.toolkit.rptGenerator.bean.rpt;

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
 * RPT DatiSingoloVersamento Bean class
 */
public class RptDatiSingoloVersamento {

    /**
     * RPT DatiSingoloVersamento Bean builder class
     */
    public static class Builder {

        private BigDecimal importoSingoloVersamento;
        private BigDecimal commissioneCaricoPA;
        private String ibanAccredito;
        private String bicAccredito;
        private String ibanAppoggio;
        private String bicAppoggio;
        private String credenzialiPagatore;
        private String iuv;
        private String descrizioneCausaleVersamento;
        private String datiSpecificiRiscossione;
        private DatiMarcaBolloDigitale datiMarcaBolloDigitale;
        private Integer ordineVersamento;

        /**
         * Build the Rpt DatiSingoloVersamento Bean
         * 
         * @return the Rpt DatiSingoloVersamento Bean
         */
        public RptDatiSingoloVersamento build() {
            return new RptDatiSingoloVersamento(this);
        }

        /**
         * Set the importoSingoloVersamento
         * 
         * @param importoSingoloVersamento
         * @return
         */
        public Builder setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
            this.importoSingoloVersamento = importoSingoloVersamento;
            return this;
        }

        /**
         * Set the commissioneCaricoPA
         * 
         * @param commissioneCaricoPA
         * @return
         */
        public Builder setCommissioneCaricoPA(BigDecimal commissioneCaricoPA) {
            this.commissioneCaricoPA = commissioneCaricoPA;
            return this;
        }

        /**
         * Set the ibanAccredito
         * 
         * @param ibanAccredito
         * @return
         */
        public Builder setIbanAccredito(String ibanAccredito) {
            this.ibanAccredito = ibanAccredito;
            return this;
        }

        /**
         * Set the bicAccredito
         * 
         * @param bicAccredito
         * @return
         */
        public Builder setBicAccredito(String bicAccredito) {
            this.bicAccredito = bicAccredito;
            return this;
        }

        /**
         * Set the ibanAppoggio
         * 
         * @param ibanAppoggio
         * @return
         */
        public Builder setIbanAppoggio(String ibanAppoggio) {
            this.ibanAppoggio = ibanAppoggio;
            return this;
        }

        /**
         * Set the bicAppoggio
         * 
         * @param bicAppoggio
         * @return
         */
        public Builder setBicAppoggio(String bicAppoggio) {
            this.bicAppoggio = bicAppoggio;
            return this;
        }

        /**
         * Set the credenzialiPagatore
         * 
         * @param credenzialiPagatore
         * @return
         */
        public Builder setCredenzialiPagatore(String credenzialiPagatore) {
            this.credenzialiPagatore = credenzialiPagatore;
            return this;
        }

        /**
         * Set the iuv
         * 
         * @param iuv
         * @return
         */
        public Builder setIuv(String iuv) {
            this.iuv = iuv;
            return this;
        }

        /**
         * Set the descrizioneCausaleVersamento
         * 
         * @param descrizioneCausaleVersamento
         * @return
         */
        public Builder setDescrizioneCausaleVersamento(String descrizioneCausaleVersamento) {
            this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
            return this;
        }

        /**
         * Set the datiSpecificiRiscossione
         * 
         * @param datiSpecificiRiscossione
         * @return
         */
        public Builder setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
            this.datiSpecificiRiscossione = datiSpecificiRiscossione;
            return this;
        }

        /**
         * Set the datiMarcaBolloDigitale
         * 
         * @param datiMarcaBolloDigitale
         * @return
         */
        public Builder setDatiMarcaBolloDigitale(DatiMarcaBolloDigitale datiMarcaBolloDigitale) {
            this.datiMarcaBolloDigitale = datiMarcaBolloDigitale;
            return this;
        }

        /**
         * Set the ordineVersamento
         * 
         * @param ordineVersamento
         * @return
         */
        public Builder setOrdineVersamento(Integer ordineVersamento) {
            this.ordineVersamento = ordineVersamento;
            return this;
        }
    }

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.01", message = ErrorMessages.VALIDATION_AMOUNT_MIN)
    private BigDecimal importoSingoloVersamento;

    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.01", message = ErrorMessages.VALIDATION_AMOUNT_MIN)
    private BigDecimal commissioneCaricoPA;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String ibanAccredito;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String bicAccredito;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String ibanAppoggio;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String bicAppoggio;

    @Size(max = 35)
    private String credenzialiPagatore;

    @NotEmpty
    @Size(max = 35)
    private String iuv;

    @Size(max = 100)
    private String descrizioneCausaleVersamento;

    @NotEmpty
    @Size(max = 140)
    private String causaleVersamento;

    @NotEmpty
    @Size(max = 140)
    @Pattern(regexp = Constants.REGEX_DATI_SPECIFICI_RISCOSSIONE, message = ErrorMessages.VALIDATION_INVALID_DATI_SPECIFICI_RISCOSSIONE)
    private String datiSpecificiRiscossione;

    @Valid
    private DatiMarcaBolloDigitale datiMarcaBolloDigitale;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private Integer ordineVersamento;

    /**
     * Private constructor
     */
    private RptDatiSingoloVersamento() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptDatiSingoloVersamento(Builder builder) {
        this.importoSingoloVersamento = builder.importoSingoloVersamento;
        this.commissioneCaricoPA = builder.commissioneCaricoPA;
        this.ibanAccredito = builder.ibanAccredito;
        this.bicAccredito = builder.bicAccredito;
        this.ibanAppoggio = builder.ibanAppoggio;
        this.bicAppoggio = builder.bicAppoggio;
        this.credenzialiPagatore = builder.credenzialiPagatore;
        this.descrizioneCausaleVersamento = builder.descrizioneCausaleVersamento;
        this.iuv = builder.iuv;
        this.datiSpecificiRiscossione = builder.datiSpecificiRiscossione;
        this.datiMarcaBolloDigitale = builder.datiMarcaBolloDigitale;
        this.ordineVersamento = builder.ordineVersamento;
    }

    /**
     * Get the importoSingoloVersamento
     * 
     * @return importoSingoloVersamento
     */
    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Get the commissioneCaricoPA
     * 
     * @return commissioneCaricoPA
     */
    public BigDecimal getCommissioneCaricoPA() {
        return commissioneCaricoPA;
    }

    /**
     * Get the ibanAccredito
     * 
     * @return ibanAccredito
     */
    public String getIbanAccredito() {
        return ibanAccredito;
    }

    /**
     * Get the bicAccredito
     * 
     * @return bicAccredito
     */
    public String getBicAccredito() {
        return bicAccredito;
    }

    /**
     * Get the ibanAppoggio
     * 
     * @return ibanAppoggio
     */
    public String getIbanAppoggio() {
        return ibanAppoggio;
    }

    /**
     * Get the bicAppoggio
     * 
     * @return bicAppoggio
     */
    public String getBicAppoggio() {
        return bicAppoggio;
    }

    /**
     * Get the credenzialiPagatore
     * 
     * @return credenzialiPagatore
     */
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }

    /**
     * Get the iuv
     * 
     * @return iuv
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Get the descrizioneCausaleVersamento
     * 
     * @return descrizioneCausaleVersamento
     */
    public String getDescrizioneCausaleVersamento() {
        return descrizioneCausaleVersamento;
    }

    /**
     * Get the causaleVersamento
     * 
     * @return causaleVersamento
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * 
     * @param causaleVersamento
     */
    public void setCausaleVersamento(String causaleVersamento) {
        this.causaleVersamento = causaleVersamento;
    }

    /**
     * Get the datiSpecificiRiscossione
     * 
     * @return datiSpecificiRiscossione
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Get the datiMarcaBolloDigitale
     * 
     * @return datiMarcaBolloDigitale
     */
    public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale() {
        return datiMarcaBolloDigitale;
    }

    /**
     * Get the ordineVersamento
     * 
     * @return ordineVersamento
     */
    public Integer getOrdineVersamento() {
        return ordineVersamento;
    }
}
