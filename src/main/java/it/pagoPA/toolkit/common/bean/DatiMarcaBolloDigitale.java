package it.pagoPA.toolkit.common.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;

/**
 * DatiMarcaBolloDigitale Bean class
 */
public class DatiMarcaBolloDigitale {

    /**
     * DatiMarcaBolloDigitale Bean builder class
     */
    public static class Builder {

        private TipoBolloEnum tipoBollo;
        private String hashDocumento;
        private String provinciaResidenza;

        /**
         * Build the DatiMarcaBolloDigitale Bean
         * 
         * @return the DatiMarcaBolloDigitale Bean
         */
        public DatiMarcaBolloDigitale build() {
            return new DatiMarcaBolloDigitale(this);
        }

        /**
         * Set the tipoBollo
         * 
         * @param tipoBollo
         * @return
         */
        public Builder setTipoBollo(TipoBolloEnum tipoBollo) {
            this.tipoBollo = tipoBollo;
            return this;
        }

        /**
         * 
         * Set the hashDocumento
         * 
         * @param hashDocumento
         * @return
         */
        public Builder setHashDocumento(String hashDocumento) {
            this.hashDocumento = hashDocumento;
            return this;
        }

        /**
         * Set the provinciaResidenza
         * 
         * @param provinciaResidenza
         * @return
         */
        public Builder setProvinciaResidenza(String provinciaResidenza) {
            this.provinciaResidenza = provinciaResidenza;
            return this;
        }
    }

    @NotNull
    private TipoBolloEnum tipoBollo;

    @NotEmpty
    @Size(max = 70)
    private String hashDocumento;

    @NotEmpty
    @Size(max = 2)
    @Pattern(regexp = Constants.REGEX_BOLLO_PROVINCIA, message = ErrorMessages.VALIDATION_INVALID_PROVINCIA)
    private String provinciaResidenza;

    /**
     * Private constructor
     */
    private DatiMarcaBolloDigitale() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private DatiMarcaBolloDigitale(Builder builder) {
        this.tipoBollo = builder.tipoBollo;
        this.hashDocumento = builder.hashDocumento;
        this.provinciaResidenza = builder.provinciaResidenza;
    }

    /**
     * Get the tipoBollo
     * 
     * @return tipoBollo
     */
    public TipoBolloEnum getTipoBollo() {
        return tipoBollo;
    }

    /**
     * Get the hashDocumento
     * 
     * @return hashDocumento
     */
    public String getHashDocumento() {
        return hashDocumento;
    }

    /**
     * Get the provinciaResidenza
     * 
     * @return provinciaResidenza
     */
    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }
}
