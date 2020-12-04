package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;

/**
 * RPT Indirizzo Bean class
 */
public class RptIndirizzo {

    /**
     * RPT Indirizzo Bean builder class
     */
    public static class Builder {

        private String indirizzo;
        private String civico;
        private String cap;
        private String localita;
        private String provincia;
        private String nazione;

        /**
         * Build the Rpt Indirizzo Bean
         * 
         * @return the Rpt Indirizzo Bean
         */
        public RptIndirizzo build() {
            return new RptIndirizzo(this);
        }

        /**
         * Set the indirizzo
         * 
         * @param indirizzo
         * @return
         */
        public Builder setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
            return this;
        }

        /**
         * Set the civico
         * 
         * @param civico
         * @return
         */
        public Builder setCivico(String civico) {
            this.civico = civico;
            return this;
        }

        /**
         * Set the cap
         * 
         * @param cap
         * @return
         */
        public Builder setCap(String cap) {
            this.cap = cap;
            return this;
        }

        /**
         * Set the localita
         * 
         * @param localita
         * @return
         */
        public Builder setLocalita(String localita) {
            this.localita = localita;
            return this;
        }

        /**
         * Set the provincia
         * 
         * @param provincia
         * @return
         */
        public Builder setProvincia(String provincia) {
            this.provincia = provincia;
            return this;
        }

        /**
         * Set the nazione
         * 
         * @param nazione
         * @return
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
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Get the civico
     * 
     * @return the civico
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Get the cap
     * 
     * @return the cap
     */
    public String getCap() {
        return cap;
    }

    /**
     * Get the localita
     * 
     * @return the localita
     */
    public String getLocalita() {
        return localita;
    }

    /**
     * Get the provincia
     * 
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Get the nazione
     * 
     * @return the nazione
     */
    public String getNazione() {
        return nazione;
    }
}
