package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;
import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;

/**
 * Component of a RPT containing the details of the individual payments.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt
 * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
 * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
 */
public class RptDatiSingoloVersamento {

    /**
     * Initialization of <code>RptDatiSingoloVersamento</code> Bean class
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
         * Build a new <code>RptDatiSingoloVersamento</code> Bean
         * 
         * @return a new instance of <code>RptDatiSingoloVersamento</code>
         */
        public RptDatiSingoloVersamento build() {
            return new RptDatiSingoloVersamento(this);
        }

        /**
         * Set the importoSingoloVersamento
         * 
         * @param importoSingoloVersamento
         *            the amount to be paid.<br/>
         *            Not null.<br/>
         *            Max 12 digits, 2 decimals.<br/>
         *            Min value = 0.01
         * @return the <code>importoSingoloVersamento</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setImportoSingoloVersamento(BigDecimal importoSingoloVersamento) {
            this.importoSingoloVersamento = importoSingoloVersamento;
            return this;
        }

        /**
         * Set the commissioneCaricoPA
         * 
         * @param commissioneCaricoPA
         *            the amount of any commission due to the PSP of which is
         *            the responsibility of the Creditor.<br/>
         *            Max 12 digits, 2 decimals.<br/>
         *            Min value = 0.01
         * @return the <code>commissioneCaricoPA</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         */
        public Builder setCommissioneCaricoPA(BigDecimal commissioneCaricoPA) {
            this.commissioneCaricoPA = commissioneCaricoPA;
            return this;
        }

        /**
         * Set the ibanAccredito
         * 
         * @param ibanAccredito
         *            iban of the account to be credited.<br/>
         *            Max 35 chars.<br/>
         *            Must respect the following regExp:
         *            "[a-zA-Z]{2,2}[0-9]{2,2}[a-zA-Z0-9]{1,30}"
         * @return the <code>ibanAccredito</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setIbanAccredito(String ibanAccredito) {
            this.ibanAccredito = ibanAccredito;
            return this;
        }

        /**
         * Set the bicAccredito
         * 
         * @param bicAccredito
         *            bic of the credit bank.<br/>
         *            Max 11 chars.
         * @return the <code>bicAccredito</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         */
        public Builder setBicAccredito(String bicAccredito) {
            this.bicAccredito = bicAccredito;
            return this;
        }

        /**
         * Set the ibanAppoggio
         * 
         * @param ibanAppoggio
         *            iban of the account to be credited to a PSP that will
         *            transfer the funds collected on the indicated account in
         *            the <code>creditIban</code>.<br/>
         *            Max 35 chars.
         * @return the <code>ibanAppoggio</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         */
        public Builder setIbanAppoggio(String ibanAppoggio) {
            this.ibanAppoggio = ibanAppoggio;
            return this;
        }

        /**
         * Set the bicAppoggio
         * 
         * @param bicAppoggio
         *            bic of the support bank.<br/>
         *            Max 11 chars.
         * @return the <code>bicAppoggio</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         */
        public Builder setBicAppoggio(String bicAppoggio) {
            this.bicAppoggio = bicAppoggio;
            return this;
        }

        /**
         * Set the credenzialiPagatore
         * 
         * @param credenzialiPagatore
         *            credentials required by PSP needed to complete the
         *            operation.<br/>
         *            Max 35 chars.
         * @return the <code>credenzialiPagatore</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         */
        public Builder setCredenzialiPagatore(String credenzialiPagatore) {
            this.credenzialiPagatore = credenzialiPagatore;
            return this;
        }

        /**
         * Set the iuv
         * 
         * @param iuv
         *            unique reference assigned to the payment by the Creditor.
         *            <br/>
         *            used for the calculation of the
         *            <code>causaleVersamento</code>.<br/>
         *            Not null nor empty.<br/>
         *            Max 35 chars.
         * @return the <code>iuv</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setIuv(String iuv) {
            this.iuv = iuv;
            return this;
        }

        /**
         * Set the descrizioneCausaleVersamento
         * 
         * @param descrizioneCausaleVersamento
         *            the reason for the single payment.<br/>
         *            the textual part provided in the
         *            <code>causaleVersamento</code> after the placeholder
         *            "/TXT".<br/>
         *            Not null nor empty.<br/>
         *            Max 100 chars.
         * @return the <code>descrizioneCausaleVersamento</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         */
        public Builder setDescrizioneCausaleVersamento(String descrizioneCausaleVersamento) {
            this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
            return this;
        }

        /**
         * Set the datiSpecificiRiscossione
         * 
         * @param datiSpecificiRiscossione
         *            the indication of the attribution of the specification
         *            entry.<br/>
         *            Not null nor empty.<br/>
         *            Max 140 chars.<br/>
         *            Must have this format: "tipoContabilità/codiceContabilità"
         *            which must respect the following regExp:
         *            "[0129]{1}/\S{3,138}"
         * @return the <code>datiSpecificiRiscossione</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
            this.datiSpecificiRiscossione = datiSpecificiRiscossione;
            return this;
        }

        /**
         * Set the datiMarcaBolloDigitale
         * 
         * @param datiMarcaBolloDigitale
         *            "Marca da Bollo Digitale" data.<br/>
         * @return the <code>datiMarcaBolloDigitale</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setDatiMarcaBolloDigitale(DatiMarcaBolloDigitale datiMarcaBolloDigitale) {
            this.datiMarcaBolloDigitale = datiMarcaBolloDigitale;
            return this;
        }

        /**
         * Set the ordineVersamento
         * 
         * @param ordineVersamento
         *            single payment order in RPT.<br/>
         *            Not null.<br/>
         *            Max 1 digit.
         * @return the <code>ordineVersamento</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail
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
     * @return the importoSingoloVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#importoSingoloVersamento
     */
    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Get the commissioneCaricoPA
     * 
     * @return the commissioneCaricoPA
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#commissioneCaricoPA
     */
    public BigDecimal getCommissioneCaricoPA() {
        return commissioneCaricoPA;
    }

    /**
     * Get the ibanAccredito
     * 
     * @return the ibanAccredito
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#ibanAccredito
     */
    public String getIbanAccredito() {
        return ibanAccredito;
    }

    /**
     * Get the bicAccredito
     * 
     * @return the bicAccredito
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#bicAccredito
     */
    public String getBicAccredito() {
        return bicAccredito;
    }

    /**
     * Get the ibanAppoggio
     * 
     * @return the ibanAppoggio
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#ibanAppoggio
     */
    public String getIbanAppoggio() {
        return ibanAppoggio;
    }

    /**
     * Get the bicAppoggio
     * 
     * @return the bicAppoggio
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#bicAppoggio
     */
    public String getBicAppoggio() {
        return bicAppoggio;
    }

    /**
     * Get the credenzialiPagatore
     * 
     * @return the credenzialiPagatore
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#credenzialiPagatore
     */
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }

    /**
     * Get the iuv
     * 
     * @return the iuv
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#iuv
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Get the descrizioneCausaleVersamento
     * 
     * @return the descrizioneCausaleVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#descrizioneCausaleVersamento
     */
    public String getDescrizioneCausaleVersamento() {
        return descrizioneCausaleVersamento;
    }

    /**
     * Get the causaleVersamento
     * 
     * @return the causaleVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#causaleVersamento
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * Get the datiSpecificiRiscossione
     * 
     * @return the datiSpecificiRiscossione
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#datiSpecificiRiscossione
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Get the datiMarcaBolloDigitale
     * 
     * @return the datiMarcaBolloDigitale
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#datiMarcaBolloDigitale
     */
    public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale() {
        return datiMarcaBolloDigitale;
    }

    /**
     * Get the ordineVersamento
     * 
     * @return the ordineVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#ordineVersamento
     */
    public Integer getOrdineVersamento() {
        return ordineVersamento;
    }

    /**
     * Set the causaleVersamento
     * 
     * @param the causaleVersamento
     *            the reason for the payment in PagoPA format:
     *            "/RFB/<code>iuv</code>[/<code>importoSingoloVersamento</code>][/TXT/<code>descrizioneCausaleVersamento</code>]"
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento#causaleVersamento
     */
    protected void setCausaleVersamento(String causaleVersamento) {
        this.causaleVersamento = causaleVersamento;
    }
}
