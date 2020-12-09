package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * Component of a RPT containing the payment details.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt
 * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
 */
public class RptDatiVersamento {

    /**
     * Initialization of <code>RptDatiVersamento</code> Bean class
     */
    public static class Builder {

        private BigDecimal importoTotaleDaVersare;
        private StTipoVersamento tipoVersamento;
        private String identificativoUnivocoVersamento;
        private String ibanAddebito;
        private String bicAddebito;
        private FirmaRicevutaEnum firmaRicevuta;

        /**
         * Build a new <code>RptDatiVersamento</code> Bean
         * 
         * @return a new instance of <code>RptDatiVersamento</code>
         */
        public RptDatiVersamento build() {
            return new RptDatiVersamento(this);
        }

        /**
         * Set the importoTotaleDaVersare
         * 
         * @param importoTotaleDaVersare
         *            the total amount to be paid.<br/>
         *            Not null.<br/>
         *            Max 12 digits, 2 decimals.<br/>
         *            Min value = 0.01
         * @return the <code>importoTotaleDaVersare</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setImportoTotaleDaVersare(BigDecimal importoTotaleDaVersare) {
            this.importoTotaleDaVersare = importoTotaleDaVersare;
            return this;
        }

        /**
         * Set the tipoVersamento
         * 
         * @param tipoVersamento
         *            technical form of payment through which it is carried out
         *            funding from the PSP based on its enumeration.<br/>
         *            Not null.<br/>
         *            Enumeration:
         *            <ul>
         *            <li>BBT
         *            <li>BP
         *            <li>AD
         *            <li>CP
         *            <li>PO
         *            <li>OBEP
         *            <li>OTH
         *            <li>JIF
         *            <li>MYBK
         *            </ul>
         * @return the <code>tipoVersamento</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setTipoVersamento(StTipoVersamento tipoVersamento) {
            this.tipoVersamento = tipoVersamento;
            return this;
        }

        /**
         * Set the identificativoUnivocoVersamento
         * 
         * @param identificativoUnivocoVersamento
         *            unique reference assigned to the payment by the Creditor.
         *            <br/>
         *            Not null nor empty.<br/>
         *            Max 35 chars.
         * @return the <code>identificativoUnivocoVersamento</code> field is set
         *         in <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.iuvGenerator.IuvCodeGeneration
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setIdentificativoUnivocoVersamento(String identificativoUnivocoVersamento) {
            this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
            return this;
        }

        /**
         * Set the ibanAddebito
         * 
         * @param ibanAddebito
         *            iban of the account from charge.<br/>
         *            Max 35 chars.<br/>
         *            Must respect the following regExp:
         *            "[a-zA-Z]{2,2}[0-9]{2,2}[a-zA-Z0-9]{1,30}"
         * @return the <code>ibanAddebito</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setIbanAddebito(String ibanAddebito) {
            this.ibanAddebito = ibanAddebito;
            return this;
        }

        /**
         * Set the bicAddebito
         * 
         * @param bicAddebito
         *            bic of the debit bank.<br/>
         *            Max 11 chars.<br/>
         *            Must respect the following regExp:
         *            "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}"
         * @return the <code>bicAddebito</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setBicAddebito(String bicAddebito) {
            this.bicAddebito = bicAddebito;
            return this;
        }

        /**
         * Set the firmaRicevuta
         * 
         * @param firmaRicevuta
         *            Digital signature type code o qualified electronics it
         *            must be submitted the message of RT.<br/>
         *            Not null.<br/>
         *            Enumeration:
         *            <ul>
         *            <li>NON_RICHIESTA - 0
         *            </ul>
         * @return the <code>firmaRicevuta</code> field is set in
         *         <code>RptDatiSingoloVersamento</code> builder
         */
        public Builder setFirmaRicevuta(FirmaRicevutaEnum firmaRicevuta) {
            this.firmaRicevuta = firmaRicevuta;
            return this;
        }
    }

    @NotNull
    private Date dataEsecuzionePagamento;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.01", message = ErrorMessages.VALIDATION_AMOUNT_MIN)
    private BigDecimal importoTotaleDaVersare;

    @NotNull
    private StTipoVersamento tipoVersamento;

    @NotEmpty
    @Size(max = 35)
    private String identificativoUnivocoVersamento;

    @NotEmpty
    @Size(max = 35)
    private String codiceContestoPagamento;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String ibanAddebito;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String bicAddebito;

    @NotNull
    private FirmaRicevutaEnum firmaRicevuta;

    /**
     * Private constructor
     */
    private RptDatiVersamento() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptDatiVersamento(Builder builder) {
        this.dataEsecuzionePagamento = new Date();
        this.importoTotaleDaVersare = builder.importoTotaleDaVersare;
        this.tipoVersamento = builder.tipoVersamento;
        this.identificativoUnivocoVersamento = builder.identificativoUnivocoVersamento;
        this.codiceContestoPagamento = UUID.randomUUID().toString().substring(0, 35);
        this.ibanAddebito = builder.ibanAddebito;
        this.bicAddebito = builder.bicAddebito;
        this.firmaRicevuta = builder.firmaRicevuta;
    }

    /**
     * Get the dataEsecuzionePagamento
     * 
     * @return the dataEsecuzionePagamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#dataEsecuzionePagamento
     */
    public Date getDataEsecuzionePagamento() {
        return dataEsecuzionePagamento;
    }

    /**
     * Get the importoTotaleDaVersare
     * 
     * @return the importoTotaleDaVersare
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#importoTotaleDaVersare
     */
    public BigDecimal getImportoTotaleDaVersare() {
        return importoTotaleDaVersare;
    }

    /**
     * Get the tipoVersamento
     * 
     * @return the tipoVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#tipoVersamento
     */
    public StTipoVersamento getTipoVersamento() {
        return tipoVersamento;
    }

    /**
     * Get the identificativoUnivocoVersamento
     * 
     * @return the identificativoUnivocoVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#identificativoUnivocoVersamento
     */
    public String getIdentificativoUnivocoVersamento() {
        return identificativoUnivocoVersamento;
    }

    /**
     * Get the codiceContestoPagamento
     * 
     * @return the codiceContestoPagamento, unique code required a define the
     *         context in which it comes made the payment.
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#codiceContestoPagamento
     */
    public String getCodiceContestoPagamento() {
        return codiceContestoPagamento;
    }

    /**
     * Get the ibanAddebito
     * 
     * @return the ibanAddebito
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#ibanAddebito
     */
    public String getIbanAddebito() {
        return ibanAddebito;
    }

    /**
     * Get the bicAddebito
     * 
     * @return the bicAddebito
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#bicAddebito
     */
    public String getBicAddebito() {
        return bicAddebito;
    }

    /**
     * Get the firmaRicevuta
     * 
     * @return the firmaRicevuta
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento#firmaRicevuta
     */
    public FirmaRicevutaEnum getFirmaRicevuta() {
        return firmaRicevuta;
    }
}
