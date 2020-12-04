package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * RPT IdentificativoUnivoco Bean class
 */
public class RptIdentificativoUnivocoFG {

    /**
     * RPT IdentificativoUnivoco Bean builder class
     */
    public static class Builder {

        private StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco;
        private String codiceIdentificativoUnivoco;

        /**
         * Build the Rpt IdentificativoUnivoco Bean
         * 
         * @return the Rpt IdentificativoUnivoco Bean
         */
        public RptIdentificativoUnivocoFG build() {
            return new RptIdentificativoUnivocoFG(this);
        }

        /**
         * Set the tipoIdentificativoUnivoco
         * 
         * @param tipoIdentificativoUnivoco
         * @return
         */
        public Builder setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersFG tipoIdentificativoUnivoco) {
            this.tipoIdentificativoUnivoco = tipoIdentificativoUnivoco;
            return this;
        }

        /**
         * Set the codiceIdentificativoUnivoco
         * 
         * @param codiceIdentificativoUnivoco
         * @return
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
     */
    public StTipoIdentificativoUnivocoPersFG getTipoIdentificativoUnivoco() {
        return tipoIdentificativoUnivoco;
    }

    /**
     * Get the codiceIdentificativoUnivoco
     * 
     * @return the codiceIdentificativoUnivoco
     */
    public String getCodiceIdentificativoUnivoco() {
        return codiceIdentificativoUnivoco;
    }
}
