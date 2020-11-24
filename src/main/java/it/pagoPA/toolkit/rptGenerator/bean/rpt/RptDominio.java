package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * RPT Dominio Bean class
 */
public class RptDominio {

    /**
     * RPT Dominio Bean builder class
     */
    public static class Builder {

        private String identificativoDominio;
        private String identificativoStazioneRichiedente;

        /**
         * Build the Rpt Dominio Bean
         * 
         * @return the Rpt Dominio Bean
         */
        public RptDominio build() {
            return new RptDominio(this);
        }

        /**
         * Set the identificativoDominio
         * 
         * @param identificativoDominio
         * @return
         */
        public Builder setIdentificativoDominio(String identificativoDominio) {
            this.identificativoDominio = identificativoDominio;
            return this;
        }

        /**
         * Set the identificativoStazioneRichiedente
         * 
         * @param identificativoStazioneRichiedente
         * @return
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
     */
    public String getIdentificativoDominio() {
        return identificativoDominio;
    }

    /**
     * Get the identificativoStazioneRichiedente
     * 
     * @return the identificativoStazioneRichiedente
     */
    public String getIdentificativoStazioneRichiedente() {
        return identificativoStazioneRichiedente;
    }
}
