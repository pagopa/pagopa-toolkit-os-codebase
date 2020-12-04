package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * RPT EnteBeneficiario Bean class
 */
public class RptEnteBeneficiario {

    /**
     * RPT EnteBeneficiario Bean builder class
     */
    public static class Builder {

        private RptIdentificativoUnivocoG identificativoUnivoco;
        private String denominazione;
        private String codiceUnitOper;
        private String denomUnitOper;
        private RptIndirizzo indirizzo;

        /**
         * Build the Rpt EnteBeneficiario Bean
         * 
         * @return the Rpt EnteBeneficiario Bean
         */
        public RptEnteBeneficiario build() {
            return new RptEnteBeneficiario(this);
        }

        /**
         * Set the identificativoUnivoco
         * 
         * @param identificativoUnivoco
         * @return
         */
        public Builder setIdentificativoUnivoco(RptIdentificativoUnivocoG identificativoUnivoco) {
            this.identificativoUnivoco = identificativoUnivoco;
            return this;
        }

        /**
         * Set the denominazione
         * 
         * @param denominazione
         * @return
         */
        public Builder setDenominazione(String denominazione) {
            this.denominazione = denominazione;
            return this;
        }

        /**
         * Set the codiceUnitOper
         * 
         * @param codiceUnitOper
         * @return
         */
        public Builder setCodiceUnitOper(String codiceUnitOper) {
            this.codiceUnitOper = codiceUnitOper;
            return this;
        }

        /**
         * Set the denomUnitOper
         * 
         * @param denomUnitOper
         * @return
         */
        public Builder setDenomUnitOper(String denomUnitOper) {
            this.denomUnitOper = denomUnitOper;
            return this;
        }

        /**
         * Set the indirizzo
         * 
         * @param indirizzo
         * @return
         */
        public Builder setIndirizzo(RptIndirizzo indirizzo) {
            this.indirizzo = indirizzo;
            return this;
        }
    }

    @NotNull
    @Valid
    private RptIdentificativoUnivocoG identificativoUnivoco;

    @NotEmpty
    @Size(max = 70)
    private String denominazione;

    @Size(max = 35)
    private String codiceUnitOper;

    @Size(max = 70)
    private String denomUnitOper;

    @Valid
    private RptIndirizzo indirizzo;

    /**
     * Private constructor
     */
    private RptEnteBeneficiario() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptEnteBeneficiario(Builder builder) {
        this.identificativoUnivoco = builder.identificativoUnivoco;
        this.denominazione = builder.denominazione;
        this.codiceUnitOper = builder.codiceUnitOper;
        this.denomUnitOper = builder.denomUnitOper;
        this.indirizzo = builder.indirizzo;
    }

    /**
     * Get the identificativoUnivoco
     * 
     * @return the identificativoUnivoco
     */
    public RptIdentificativoUnivocoG getIdentificativoUnivoco() {
        return identificativoUnivoco;
    }

    /**
     * Get the denominazione
     * 
     * @return the denominazione
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Get the codiceUnitOper
     * 
     * @return the codiceUnitOper
     */
    public String getCodiceUnitOper() {
        return codiceUnitOper;
    }

    /**
     * Get the denomUnitOper
     * 
     * @return the denomUnitOper
     */
    public String getDenomUnitOper() {
        return denomUnitOper;
    }

    /**
     * Get the indirizzo
     * 
     * @return the indirizzo
     */
    public RptIndirizzo getIndirizzo() {
        return indirizzo;
    }
}
