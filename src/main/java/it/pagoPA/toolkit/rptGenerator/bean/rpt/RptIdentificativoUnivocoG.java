package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;

/**
 * RPT IdentificativoUnivoco Bean class
 */
public class RptIdentificativoUnivocoG {

    /**
     * RPT IdentificativoUnivoco Bean builder class
     */
    public static class Builder {

        private StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco;
        private String codiceIdentificativoUnivoco;

        /**
         * Build the Rpt IdentificativoUnivoco Bean
         * 
         * @return the Rpt IdentificativoUnivoco Bean
         */
        public RptIdentificativoUnivocoG build() {
            return new RptIdentificativoUnivocoG(this);
        }

        /**
         * Set the tipoIdentificativoUnivoco
         * 
         * @param tipoIdentificativoUnivoco
         * @return
         */
        public Builder setTipoIdentificativoUnivoco(StTipoIdentificativoUnivocoPersG tipoIdentificativoUnivoco) {
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
     */
    public StTipoIdentificativoUnivocoPersG getTipoIdentificativoUnivoco() {
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
