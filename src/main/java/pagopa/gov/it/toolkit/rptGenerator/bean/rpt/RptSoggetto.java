package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.common.ErrorMessages;

/**
 * Component of a RPT containing the information concerning a subject.
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt
 * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
 */
public class RptSoggetto {

    /**
     * Initialization of <code>RptSoggetto</code> Bean class
     */
    public static class Builder {

        private RptIdentificativoUnivocoFG identificativoUnivoco;
        private String anagrafica;
        private RptIndirizzo indirizzo;
        private String email;

        /**
         * Build a new <code>RptSoggetto</code> Bean
         * 
         * @return a new instance of <code>RptSoggetto</code>
         */
        public RptSoggetto build() {
            return new RptSoggetto(this);
        }

        /**
         * Set the identificativoUnivoco
         * 
         * @param identificativoUnivoco
         *            structure containing the information concerning the fiscal
         *            identification of the subject.<br/>
         *            Not null
         * @return the <code>identificativoUnivoco</code> field is set in
         *         <code>RptSoggetto</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoFG
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         * @see pagopa.gov.it.toolkit.rptGenerator.validation.RptValidationImpl
         */
        public Builder setIdentificativoUnivoco(RptIdentificativoUnivocoFG identificativoUnivoco) {
            this.identificativoUnivoco = identificativoUnivoco;
            return this;
        }

        /**
         * Set the anagrafica
         * 
         * @param anagrafica
         *            name of the subject.<br/>
         *            Not null nor empty.<br/>
         *            Max 70 chars.
         * @return the <code>anagrafica</code> field is set in
         *         <code>RptSoggetto</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setAnagrafica(String anagrafica) {
            this.anagrafica = anagrafica;
            return this;
        }

        /**
         * Set the indirizzo
         * 
         * @param indirizzo
         *            structure containing the information concerning the
         *            address of the subject
         * @return the <code>indirizzo</code> field is set in
         *         <code>RptSoggetto</code> builder
         * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
         */
        public Builder setIndirizzo(RptIndirizzo indirizzo) {
            this.indirizzo = indirizzo;
            return this;
        }

        /**
         * Set the email
         * 
         * @param email
         *            email address of the subject.<br/>
         *            Max 256 chars.<br/>
         *            Must respect the following regExp:
         *            "[a-zA-Z0-9_\.\+\-]+@[a-zA-Z0-9\-]+(\.[a-zA-Z0-9\-]+)*"
         * @return the <code>email</code> field is set in
         *         <code>RptSoggetto</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer
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
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto#identificativoUnivoco
     */
    public RptIdentificativoUnivocoFG getIdentificativoUnivoco() {
        return identificativoUnivoco;
    }

    /**
     * Get the anagrafica
     * 
     * @return the anagrafica
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto#anagrafica
     */
    public String getAnagrafica() {
        return anagrafica;
    }

    /**
     * Get the indirizzo
     * 
     * @return the indirizzo
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto#indirizzo
     */
    public RptIndirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Get the email
     * 
     * @return the email
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptSoggetto#email
     */
    public String getEmail() {
        return email;
    }
}
