package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Component of <code>RptSoggetto</code> in RPT containing the fiscal
 * identification information.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
 */
public class RptIdentificativoUnivocoFG {

    /**
     * Initialization of <code>RptIdentificativoUnivocoFG</code> Bean class
     */
    public static class Builder {

        private StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco;
        private String codiceIdentificativoUnivoco;

        /**
         * Build a new <code>RptIdentificativoUnivocoFG</code> Bean
         * 
         * @return a new instance of <code>RptIdentificativoUnivocoFG</code>
         */
        public RptIdentificativoUnivocoFG build() {
            return new RptIdentificativoUnivocoFG(this);
        }

        /**
         * Set the tipoIdentificativoUnivoco
         * 
         * @param tipoIdentificativoUnivoco
         *            type of Identification based on its enumeration and based
         *            on uniqueIdentificationCode.<br/>
         *            Enumeration:
         *            <ul>
         *            <li>F - Persona Fisica
         *            <li>G - Persona Giuridica
         *            </ul>
         * @return the <code>tipoIdentificativoUnivoco</code> field is set in
         *         <code>RptIdentificativoUnivocoFG</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco) {
            this.tipoIdentificativoUnivoco = tipoIdentificativoUnivoco;
            return this;
        }

        /**
         * Set the codiceIdentificativoUnivoco
         * 
         * @param codiceIdentificativoUnivoco
         *            fiscal code or VAT number.<br/>
         *            Max 35 chars.
         * @return the <code>codiceIdentificativoUnivoco</code> field is set in
         *         <code>RptIdentificativoUnivocoFG</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setCodiceIdentificativoUnivoco(String codiceIdentificativoUnivoco) {
            this.codiceIdentificativoUnivoco = codiceIdentificativoUnivoco;
            return this;
        }
    }

    private StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco;

    @Size(max = 35)
    private String codiceIdentificativoUnivoco;

    /**
     * Private constructor
     */
    private RptIdentificativoUnivocoFG() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptIdentificativoUnivocoFG(Builder builder) {
        this.tipoIdentificativoUnivoco = builder.tipoIdentificativoUnivoco != null ? builder.tipoIdentificativoUnivoco
                : StTipoIdentificativoUnivocoPersFG.fromValue(Constants.UNIQUE_IDENTIFICATION_TYPE_DEFAULT);
        this.codiceIdentificativoUnivoco = builder.codiceIdentificativoUnivoco != null
                ? builder.codiceIdentificativoUnivoco : Constants.UNIQUE_IDENTIFICATION_CODE_DEFAULT;
    }

    /**
     * Get the tipoIdentificativoUnivoco
     * 
     * @return the tipoIdentificativoUnivoco
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoFG#tipoIdentificativoUnivoco
     */
    public StTipoIdentificativoUnivocoPersFG getTipoIdentificativoUnivoco() {
        return tipoIdentificativoUnivoco;
    }

    /**
     * Get the codiceIdentificativoUnivoco
     * 
     * @return the codiceIdentificativoUnivoco
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoFG#codiceIdentificativoUnivoco
     */
    public String getCodiceIdentificativoUnivoco() {
        return codiceIdentificativoUnivoco;
    }
}
