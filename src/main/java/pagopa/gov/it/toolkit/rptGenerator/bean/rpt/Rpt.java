package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;

/**
 * Bean containing all the components of the rpt
 */
public class Rpt {

    private static final String IMR_STARTING_PART_FORMAT = "yyyyMMddhhmmss";

    /**
     * Initialization of <code>Rpt</code> Bean class
     */
    public static class Builder {

        private String versioneOggetto;
        private RptDominio dominio;
        private StAutenticazioneSoggetto autenticazioneSoggetto;
        private RptSoggetto soggettoVersante;
        private RptSoggetto soggettoPagatore;
        private RptEnteBeneficiario enteBeneficiario;
        private RptDatiVersamento datiVersamento;
        private List<RptDatiSingoloVersamento> datiSingoloVersamentoList;

        /**
         * Build a new <code>Rpt</code> Bean
         * 
         * @return a new instance of <code>Rpt</code>
         */
        public Rpt build() {
            return new Rpt(this);
        }

        /**
         * Set the versioneOggetto
         * 
         * @param versioneOggetto
         *            version of the rpt.<br/>
         *            Not null nor empty.<br/>
         *            Max 16 chars.
         * @return the <code>versioneOggetto</code> field is set in
         *         <code>Rpt</code> builder
         */
        public Builder setVersioneOggetto(String versioneOggetto) {
            this.versioneOggetto = versioneOggetto;
            return this;
        }

        /**
         * Set the dominio
         * 
         * @param dominio
         *            structure containing the information that allows uniquely
         *            identify the scope of application of request.<br/>
         *            Not null.
         * @return the <code>dominio</code> field is set in <code>Rpt</code>
         *         builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDominio
         */
        public Builder setDominio(RptDominio dominio) {
            this.dominio = dominio;
            return this;
        }

        /**
         * Set the autenticazioneSoggetto
         * 
         * @param autenticazioneSoggetto
         *            mode of identification applied to subject that must be
         *            charged for payment based on its enumeration.<br/>
         *            Not null.<br/>
         *            Enumeration:
         *            <ul>
         *            <li>CNS - "CNS"
         *            <li>USR - "USR"
         *            <li>OTH - "OTH"
         *            <li>N_A - "N/A"
         *            </ul>
         * @return the <code>autenticazioneSoggetto</code> field is set in
         *         <code>Rpt</code> builder
         */
        public Builder setAutenticazioneSoggetto(StAutenticazioneSoggetto autenticazioneSoggetto) {
            this.autenticazioneSoggetto = autenticazioneSoggetto;
            return this;
        }

        /**
         * Set the soggettoVersante
         * 
         * @param soggettoVersante
         *            structure containing the the information concerning the
         *            subject that make the payment on behalf of the
         *            <code>soggettoPagatore</code>.
         * @return the <code>soggettoVersante</code> field is set in
         *         <code>Rpt</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
         */
        public Builder setSoggettoVersante(RptSoggetto soggettoVersante) {
            this.soggettoVersante = soggettoVersante;
            return this;
        }

        /**
         * Set the soggettoPagatore
         * 
         * @param soggettoPagatore
         *            structure containing the information concerning the
         *            subject debtor of sums of money in against the Creditor
         *            Institution.<br/>
         *            Not null.
         * @return the <code>soggettoPagatore</code> field is set in
         *         <code>Rpt</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
         */
        public Builder setSoggettoPagatore(RptSoggetto soggettoPagatore) {
            this.soggettoPagatore = soggettoPagatore;
            return this;
        }

        /**
         * Set the enteBeneficiario
         * 
         * @param enteBeneficiario
         *            structure containing the information concerning the
         *            creditor of sums owed of the <code>soggettoPagatore</code>
         *            .<br/>
         *            Not null.
         * @return the <code>enteBeneficiario</code> field is set in
         *         <code>Rpt</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario
         */
        public Builder setEnteBeneficiario(RptEnteBeneficiario enteBeneficiario) {
            this.enteBeneficiario = enteBeneficiario;
            return this;
        }

        /**
         * Set the datiVersamento
         * 
         * @param datiVersamento
         *            structure containing the information concerning the
         *            payment to be made.<br/>
         *            Not null.
         * @return the <code>datiVersamento</code> field is set in
         *         <code>Rpt</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiVersamento
         */
        public Builder setDatiVersamento(RptDatiVersamento datiVersamento) {
            this.datiVersamento = datiVersamento;
            return this;
        }

        /**
         * Set the datiSingoloVersamentoList
         * 
         * @param datiSingoloVersamentoList
         *            structure containing the details of the individual
         *            payments.<br/>
         *            Not null nor empty.
         * @return the <code>datiSingoloVersamentoList</code> field is set in
         *         <code>Rpt</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDatiSingoloVersamento
         */
        public Builder setDatiSingoloVersamentoList(List<RptDatiSingoloVersamento> datiSingoloVersamentoList) {
            this.datiSingoloVersamentoList = datiSingoloVersamentoList;
            return this;
        }
    }

    @NotEmpty
    @Size(max = 16)
    private String versioneOggetto;

    @NotNull
    @Valid
    private RptDominio dominio;

    @NotEmpty
    @Size(max = 35)
    private String identificativoMessaggioRichiesta;

    @NotNull
    private Date dataOraMessaggioRichiesta;

    @NotNull
    private StAutenticazioneSoggetto autenticazioneSoggetto;

    @Valid
    private RptSoggetto soggettoVersante;

    @NotNull
    @Valid
    private RptSoggetto soggettoPagatore;

    @NotNull
    @Valid
    private RptEnteBeneficiario enteBeneficiario;

    @NotNull
    @Valid
    private RptDatiVersamento datiVersamento;

    @NotEmpty
    @Valid
    private List<RptDatiSingoloVersamento> datiSingoloVersamentoList;

    /**
     * Private constructor
     */
    private Rpt() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private Rpt(Builder builder) {
        this.versioneOggetto = builder.versioneOggetto;
        this.dominio = builder.dominio;
        this.identificativoMessaggioRichiesta = (new SimpleDateFormat(IMR_STARTING_PART_FORMAT).format(new Date())
                + UUID.randomUUID().toString()).substring(0, 35);
        this.dataOraMessaggioRichiesta = new Date();
        this.autenticazioneSoggetto = builder.autenticazioneSoggetto;
        this.soggettoVersante = builder.soggettoVersante;
        this.soggettoPagatore = builder.soggettoPagatore;
        this.enteBeneficiario = builder.enteBeneficiario;
        this.datiVersamento = builder.datiVersamento;
        this.datiSingoloVersamentoList = builder.datiSingoloVersamentoList;
    }

    /**
     * Get the versioneOggetto
     * 
     * @return the versioneOggetto
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#versioneOggetto
     */
    public String getVersioneOggetto() {
        return versioneOggetto;
    }

    /**
     * Get the dominio
     * 
     * @return the dominio
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#dominio
     */
    public RptDominio getDominio() {
        return dominio;
    }

    /**
     * Get the identificativoMessaggioRichiesta
     * 
     * @return the identificativoMessaggioRichiesta, the identifier linked to
     *         transmission of the request for payment. It must be unique within
     *         the scope of the same date reported to the element.
     *         <code>dateTimeMessageRequest</code>.
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#identificativoMessaggioRichiesta
     */
    public String getIdentificativoMessaggioRichiesta() {
        return identificativoMessaggioRichiesta;
    }

    /**
     * Get the dataOraMessaggioRichiesta
     * 
     * @return the dataOraMessaggioRichiesta, indicates the date and time of
     *         message generation of payment request.
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#dataOraMessaggioRichiesta
     */
    public Date getDataOraMessaggioRichiesta() {
        return dataOraMessaggioRichiesta;
    }

    /**
     * Get the autenticazioneSoggetto
     * 
     * @return the autenticazioneSoggetto
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#autenticazioneSoggetto
     */
    public StAutenticazioneSoggetto getAutenticazioneSoggetto() {
        return autenticazioneSoggetto;
    }

    /**
     * Get the soggettoVersante
     * 
     * @return the soggettoVersante
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#soggettoVersante
     */
    public RptSoggetto getSoggettoVersante() {
        return soggettoVersante;
    }

    /**
     * Get the soggettoPagatore
     * 
     * @return the soggettoPagatore
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#soggettoPagatore
     */
    public RptSoggetto getSoggettoPagatore() {
        return soggettoPagatore;
    }

    /**
     * Get the enteBeneficiario
     * 
     * @return the enteBeneficiario
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#enteBeneficiario
     */
    public RptEnteBeneficiario getEnteBeneficiario() {
        return enteBeneficiario;
    }

    /**
     * Get the datiVersamento
     * 
     * @return the datiVersamento
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#datiVersamento
     */
    public RptDatiVersamento getDatiVersamento() {
        return datiVersamento;
    }

    /**
     * Get the datiSingoloVersamentoList
     * 
     * @return the datiSingoloVersamentoList
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt#datiSingoloVersamentoList
     */
    public List<RptDatiSingoloVersamento> getDatiSingoloVersamentoList() {
        return datiSingoloVersamentoList;
    }
}
