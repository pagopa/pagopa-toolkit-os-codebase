package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;

/**
 * RPT Soggetto Bean class
 */
public class RptSoggetto {

    /**
     * RPT Soggetto Bean builder class
     */
    public static class Builder {

        private RptIdentificativoUnivocoFG identificativoUnivoco;
        private String anagrafica;
        private RptIndirizzo indirizzo;
        private String email;

        /**
         * Build the Rpt Soggetto Bean
         * 
         * @return the Rpt Soggetto Bean
         */
        public RptSoggetto build() {
            return new RptSoggetto(this);
        }

        /**
         * Set the identificativoUnivoco
         * 
         * @param identificativoUnivoco
         * @return
         */
        public Builder setIdentificativoUnivoco(RptIdentificativoUnivocoFG identificativoUnivoco) {
            this.identificativoUnivoco = identificativoUnivoco;
            return this;
        }

        /**
         * Set the anagrafica
         * 
         * @param anagrafica
         * @return
         */
        public Builder setAnagrafica(String anagrafica) {
            this.anagrafica = anagrafica;
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

        /**
         * Set the email
         * 
         * @param email
         * @return
         */
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
    }

    @NotNull
    @Valid
    private RptIdentificativoUnivocoFG identificativoUnivoco;

    @NotEmpty
    @Size(max = 70)
    private String anagrafica;

    @Valid
    private RptIndirizzo indirizzo;

    @Size(max = 256)
    @Pattern(regexp = Constants.REGEX_EMAIL, message = ErrorMessages.VALIDATION_INVALID_EMAIL)
    private String email;

    /**
     * Private constructor
     */
    private RptSoggetto() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptSoggetto(Builder builder) {
        this.identificativoUnivoco = builder.identificativoUnivoco;
        this.anagrafica = builder.anagrafica;
        this.indirizzo = builder.indirizzo;
        this.email = builder.email;
    }

    /**
     * Get the identificativoUnivoco
     * 
     * @return the identificativoUnivoco
     */
    public RptIdentificativoUnivocoFG getIdentificativoUnivoco() {
        return identificativoUnivoco;
    }

    /**
     * Get the anagrafica
     * 
     * @return the anagrafica
     */
    public String getAnagrafica() {
        return anagrafica;
    }

    /**
     * Get the indirizzo
     * 
     * @return the indirizzo
     */
    public RptIndirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Get the email
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
