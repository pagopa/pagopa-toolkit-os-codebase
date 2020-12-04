package pagopa.gov.it.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Component of a RPT containing the information that allows uniquely identify
 * the scope of application of request
 * 
 * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt
 */
public class RptDominio {

    /**
     * Initialization of <code>RptDominio</code> Bean class
     */
    public static class Builder {

        private String identificativoDominio;
        private String identificativoStazioneRichiedente;

        /**
         * Build a new <code>RptDominio</code> Bean
         * 
         * @return a new instance of <code>RptDominio</code>
         */
        public RptDominio build() {
            return new RptDominio(this);
        }

        /**
         * Set the identificativoDominio
         * 
         * @param identificativoDominio
         *            fiscal code of the structure that send the payment
         *            request.<br/>
         *            Not null nor empty.<br/>
         *            Max 35 chars.
         * @return the <code>identificativoDominio</code> field is set in
         *         <code>RptDominio</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setIdentificativoDominio(String identificativoDominio) {
            this.identificativoDominio = identificativoDominio;
            return this;
        }

        /**
         * Set the identificativoStazioneRichiedente
         * 
         * @param identificativoStazioneRichiedente
         *            the station requesting the payment according to an
         *            encoding default by the sender.<br/>
         *            Max 35 chars.
         * @return the <code>identificativoStazioneRichiedente</code> field is
         *         set in <code>RptDominio</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setIdentificativoStazioneRichiedente(String identificativoStazioneRichiedente) {
            this.identificativoStazioneRichiedente = identificativoStazioneRichiedente;
            return this;
        }
    }

    @NotEmpty
    @Size(max = 35)
    private String identificativoDominio;

    @Size(max = 35)
    private String identificativoStazioneRichiedente;

    /**
     * Private constructor
     */
    private RptDominio() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptDominio(Builder builder) {
        this.identificativoDominio = builder.identificativoDominio;
        this.identificativoStazioneRichiedente = builder.identificativoStazioneRichiedente;
    }

    /**
     * Get the identificativoDominio
     * 
     * @return the identificativoDominio
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDominio#identificativoDominio
     */
    public String getIdentificativoDominio() {
        return identificativoDominio;
    }

    /**
     * Get the identificativoStazioneRichiedente
     * 
     * @return the identificativoStazioneRichiedente
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptDominio#identificativoStazioneRichiedente
     */
    public String getIdentificativoStazioneRichiedente() {
        return identificativoStazioneRichiedente;
    }
}
