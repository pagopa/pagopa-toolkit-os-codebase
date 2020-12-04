package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;

/**
 * Component of <code>RptSoggetto</code> in RPT containing the information
 * concerning the address of <code>RptSoggetto</code>.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
 * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
 */
public class RptIndirizzo {

    /**
     * Initialization of <code>RptIndirizzo</code> Bean class
     */
    public static class Builder {

        private String indirizzo;
        private String civico;
        private String cap;
        private String localita;
        private String provincia;
        private String nazione;

        /**
         * Build a new <code>RptIndirizzo</code> Bean
         * 
         * @return a new instance of <code>RptIndirizzo</code>
         */
        public RptIndirizzo build() {
            return new RptIndirizzo(this);
        }

        /**
         * Set the indirizzo
         * 
         * @param indirizzo
         *            the street of the address.<br/>
         *            Max 70 chars.
         * @return the <code>indirizzo</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
            return this;
        }

        /**
         * Set the civico
         * 
         * @param civico
         *            the number street of the address.<br/>
         *            Max 16 chars.
         * @return the <code>civico</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setCivico(String civico) {
            this.civico = civico;
            return this;
        }

        /**
         * Set the cap
         * 
         * @param cap
         *            the postalCode of the address.<br/>
         *            Max 16 chars.
         * @return the <code>cap</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setCap(String cap) {
            this.cap = cap;
            return this;
        }

        /**
         * Set the localita
         * 
         * @param localita
         *            the locality of the address.<br/>
         *            Max 35 chars.
         * @return the <code>localita</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setLocalita(String localita) {
            this.localita = localita;
            return this;
        }

        /**
         * Set the provincia
         * 
         * @param provincia
         *            the province of the address.<br/>
         *            Max 35 chars.
         * @return the <code>provincia</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setProvincia(String provincia) {
            this.provincia = provincia;
            return this;
        }

        /**
         * Set the nazione
         * 
         * @param nazione
         *            the nation of the address.<br/>
         *            Max 2 chars.<br/>
         *            Must respect the following regExp: "[A-Z]{2,2}"
         * @return the <code>nazione</code> field is set in
         *         <code>RptIndirizzo</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setNazione(String nazione) {
            this.nazione = nazione;
            return this;
        }
    }

    @Size(max = 70)
    private String indirizzo;

    @Size(max = 16)
    private String civico;

    @Size(max = 16)
    private String cap;

    @Size(max = 35)
    private String localita;

    @Size(max = 35)
    private String provincia;

    @Size(max = 2)
    @Pattern(regexp = Constants.REGEX_NATION, message = ErrorMessages.VALIDATION_UPPERCASE_FIELD)
    private String nazione;

    /**
     * Private constructor
     */
    private RptIndirizzo() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptIndirizzo(Builder builder) {
        this.indirizzo = builder.indirizzo;
        this.civico = builder.civico;
        this.cap = builder.cap;
        this.localita = builder.localita;
        this.provincia = builder.provincia;
        this.nazione = builder.nazione;
    }

    /**
     * Get the indirizzo
     * 
     * @return the indirizzo
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Get the civico
     * 
     * @return the civico
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#civico
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Get the cap
     * 
     * @return the cap
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#cap
     */
    public String getCap() {
        return cap;
    }

    /**
     * Get the localita
     * 
     * @return the localita
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#localita
     */
    public String getLocalita() {
        return localita;
    }

    /**
     * Get the provincia
     * 
     * @return the provincia
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Get the nazione
     * 
     * @return the nazione
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo#nazione
     */
    public String getNazione() {
        return nazione;
    }
}
