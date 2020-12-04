package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;

/**
 * Component of a <code>RptEnteBeneficiario</code> in RPT containing the fiscal
 * identification information.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario
 */
public class RptIdentificativoUnivocoG {

    /**
     * Initialization of <code>RptIdentificativoUnivocoG</code> Bean class
     */
    public static class Builder {

        private StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco;
        private String codiceIdentificativoUnivoco;

        /**
         * Build a new <code>RptIdentificativoUnivocoG</code> Bean
         * 
         * @return a new instance of <code>RptIdentificativoUnivocoG</code>
         */
        public RptIdentificativoUnivocoG build() {
            return new RptIdentificativoUnivocoG(this);
        }

        /**
         * Set the tipoIdentificativoUnivoco
         * 
         * @param tipoIdentificativoUnivoco
         *            type of Identification based on its enumeration.<br/>
         *            Not null.<br/>
         *            Enumeration:
         *            <ul>
         *            <li>G - Persona Giuridica
         *            </ul>
         * @return the <code>tipoIdentificativoUnivoco</code> field is set in
         *         <code>RptIdentificativoUnivocoG</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario
         */
        public Builder setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco) {
            this.tipoIdentificativoUnivoco = tipoIdentificativoUnivoco;
            return this;
        }

        /**
         * Set the codiceIdentificativoUnivoco
         * 
         * @param codiceIdentificativoUnivoco
         *            VAT number.<br/>
         *            Not null nor empty.<br/>
         *            Max 35 chars.
         * @return the <code>codiceIdentificativoUnivoco</code> field is set in
         *         <code>RptIdentificativoUnivocoG</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario
         */
        public Builder setCodiceIdentificativoUnivoco(String codiceIdentificativoUnivoco) {
            this.codiceIdentificativoUnivoco = codiceIdentificativoUnivoco;
            return this;
        }
    }

    @NotNull
    private StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco;

    @NotEmpty
    @Size(max = 35)
    private String codiceIdentificativoUnivoco;

    /**
     * Private constructor
     */
    private RptIdentificativoUnivocoG() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptIdentificativoUnivocoG(Builder builder) {
        this.tipoIdentificativoUnivoco = builder.tipoIdentificativoUnivoco;
        this.codiceIdentificativoUnivoco = builder.codiceIdentificativoUnivoco;
    }

    /**
     * Get the tipoIdentificativoUnivoco
     * 
     * @return the tipoIdentificativoUnivoco
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG#tipoIdentificativoUnivoco
     */
    public StTipoIdentificativoUnivocoPersG getTipoIdentificativoUnivoco() {
        return tipoIdentificativoUnivoco;
    }

    /**
     * Get the codiceIdentificativoUnivoco
     * 
     * @return the codiceIdentificativoUnivoco
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG#codiceIdentificativoUnivoco
     */
    public String getCodiceIdentificativoUnivoco() {
        return codiceIdentificativoUnivoco;
    }
}
