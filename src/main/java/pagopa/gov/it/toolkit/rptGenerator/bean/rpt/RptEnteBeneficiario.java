package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Component of a RPT containing the information concerning the creditor of sums
 * owed of the payer.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt
 */
public class RptEnteBeneficiario {

    /**
     * Initialization of <code>RptEnteBeneficiario</code> Bean class
     */
    public static class Builder {

        private RptIdentificativoUnivocoG identificativoUnivoco;
        private String denominazione;
        private String codiceUnitOper;
        private String denomUnitOper;
        private RptIndirizzo indirizzo;

        /**
         * Build a new <code>RptEnteBeneficiario</code> Bean
         * 
         * @return a new instance of <code>RptEnteBeneficiario</code>
         */
        public RptEnteBeneficiario build() {
            return new RptEnteBeneficiario(this);
        }

        /**
         * Set the identificativoUnivoco
         * 
         * @param identificativoUnivoco
         *            structure containing the information concerning the fiscal
         *            identification of the entity beneficiary.<br/>
         *            Not null
         * @return the <code>identificativoUnivoco</code> field is set in
         *         <code>RptEnteBeneficiario</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG
         */
        public Builder setIdentificativoUnivoco(RptIdentificativoUnivocoG identificativoUnivoco) {
            this.identificativoUnivoco = identificativoUnivoco;
            return this;
        }

        /**
         * Set the denominazione
         * 
         * @param denominazione
         *            name of the Creditor.<br/>
         *            Not null nor empty.<br/>
         *            Max 70 chars.
         * @return the <code>denominazione</code> field is set in
         *         <code>RptEnteBeneficiario</code> builder
         */
        public Builder setDenominazione(String denominazione) {
            this.denominazione = denominazione;
            return this;
        }

        /**
         * Set the codiceUnitOper
         * 
         * @param codiceUnitOper
         *            the unit code operational recipient.<br/>
         *            Max 35 chars.
         * @return the <code>codiceUnitOper</code> field is set in
         *         <code>RptEnteBeneficiario</code> builder
         */
        public Builder setCodiceUnitOper(String codiceUnitOper) {
            this.codiceUnitOper = codiceUnitOper;
            return this;
        }

        /**
         * Set the denomUnitOper
         * 
         * @param denomUnitOper
         *            the denomination of the recipient operating unit.<br/>
         *            Max 70 chars.
         * @return the <code>denomUnitOper</code> field is set in
         *         <code>RptEnteBeneficiario</code> builder
         */
        public Builder setDenomUnitOper(String denomUnitOper) {
            this.denomUnitOper = denomUnitOper;
            return this;
        }

        /**
         * Set the indirizzo
         * 
         * @param indirizzo
         *            structure containing the information concerning the
         *            address of the Creditor.<br/>
         * @return the <code>indirizzo</code> field is set in
         *         <code>RptEnteBeneficiario</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo
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
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario#identificativoUnivoco
     */
    public RptIdentificativoUnivocoG getIdentificativoUnivoco() {
        return identificativoUnivoco;
    }

    /**
     * Get the denominazione
     * 
     * @return the denominazione
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario#denominazione
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Get the codiceUnitOper
     * 
     * @return the codiceUnitOper
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario#codiceUnitOper
     */
    public String getCodiceUnitOper() {
        return codiceUnitOper;
    }

    /**
     * Get the denomUnitOper
     * 
     * @return the denomUnitOper
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario#denomUnitOper
     */
    public String getDenomUnitOper() {
        return denomUnitOper;
    }

    /**
     * Get the indirizzo
     * 
     * @return the indirizzo
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario#indirizzo
     */
    public RptIndirizzo getIndirizzo() {
        return indirizzo;
    }
}
