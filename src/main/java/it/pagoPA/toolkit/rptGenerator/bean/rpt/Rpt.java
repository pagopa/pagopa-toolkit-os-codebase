package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.rptGenerator.xsd.StAutenticazioneSoggetto;

/**
 * RPT Bean class
 */
public class Rpt {

    private static final String IMR_STARTING_PART_FORMAT = "yyyyMMddhhmmss";

    /**
     * RPT Bean builder class
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
         * Build the Rpt Container Bean
         * 
         * @return the Rpt Container Bean
         */
        public Rpt build() {
            return new Rpt(this);
        }

        /**
         * Set the versioneOggetto
         * 
         * @param versioneOggetto
         * @return
         */
        public Builder setVersioneOggetto(String versioneOggetto) {
            this.versioneOggetto = versioneOggetto;
            return this;
        }

        /**
         * Set the dominio
         * 
         * @param dominio
         * @return
         */
        public Builder setDominio(RptDominio dominio) {
            this.dominio = dominio;
            return this;
        }

        /**
         * Set the autenticazioneSoggetto
         * 
         * @param autenticazioneSoggetto
         * @return
         */
        public Builder setAutenticazioneSoggetto(StAutenticazioneSoggetto autenticazioneSoggetto) {
            this.autenticazioneSoggetto = autenticazioneSoggetto;
            return this;
        }

        /**
         * Set the soggettoVersante
         * 
         * @param soggettoVersante
         * @return
         */
        public Builder setSoggettoVersante(RptSoggetto soggettoVersante) {
            this.soggettoVersante = soggettoVersante;
            return this;
        }

        /**
         * Set the soggettoPagatore
         * 
         * @param soggettoPagatore
         * @return
         */
        public Builder setSoggettoPagatore(RptSoggetto soggettoPagatore) {
            this.soggettoPagatore = soggettoPagatore;
            return this;
        }

        /**
         * Set the enteBeneficiario
         * 
         * @param enteBeneficiario
         * @return
         */
        public Builder setEnteBeneficiario(RptEnteBeneficiario enteBeneficiario) {
            this.enteBeneficiario = enteBeneficiario;
            return this;
        }

        /**
         * Set the datiVersamento
         * 
         * @param datiVersamento
         * @return
         */
        public Builder setDatiVersamento(RptDatiVersamento datiVersamento) {
            this.datiVersamento = datiVersamento;
            return this;
        }

        /**
         * Set the datiSingoloVersamentoList
         * 
         * @param datiSingoloVersamentoList
         *            List
         * @return
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
     */
    public String getVersioneOggetto() {
        return versioneOggetto;
    }

    /**
     * Get the dominio
     * 
     * @return the dominio
     */
    public RptDominio getDominio() {
        return dominio;
    }

    /**
     * Get the identificativoMessaggioRichiesta
     * 
     * @return the identificativoMessaggioRichiesta
     */
    public String getIdentificativoMessaggioRichiesta() {
        return identificativoMessaggioRichiesta;
    }

    /**
     * Get the dataOraMessaggioRichiesta
     * 
     * @return the dataOraMessaggioRichiesta
     */
    public Date getDataOraMessaggioRichiesta() {
        return dataOraMessaggioRichiesta;
    }

    /**
     * Get the autenticazioneSoggetto
     * 
     * @return the autenticazioneSoggetto
     */
    public StAutenticazioneSoggetto getAutenticazioneSoggetto() {
        return autenticazioneSoggetto;
    }

    /**
     * Get the soggettoVersante
     * 
     * @return the soggettoVersante
     */
    public RptSoggetto getSoggettoVersante() {
        return soggettoVersante;
    }

    /**
     * Get the soggettoPagatore
     * 
     * @return the soggettoPagatore
     */
    public RptSoggetto getSoggettoPagatore() {
        return soggettoPagatore;
    }

    /**
     * Get the enteBeneficiario
     * 
     * @return the enteBeneficiario
     */
    public RptEnteBeneficiario getEnteBeneficiario() {
        return enteBeneficiario;
    }

    /**
     * Get the datiVersamento
     * 
     * @return the datiVersamento
     */
    public RptDatiVersamento getDatiVersamento() {
        return datiVersamento;
    }

    /**
     * Get the datiSingoloVersamentoList
     * 
     * @return the datiSingoloVersamentoList
     */
    public List<RptDatiSingoloVersamento> getDatiSingoloVersamentoList() {
        return datiSingoloVersamentoList;
    }
}
