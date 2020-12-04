package it.pagoPA.toolkit.rptGenerator.bean.rpt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.common.Constants;
import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.rptGenerator.enumeration.FirmaRicevutaEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoVersamento;

/**
 * RPT DatiVersamento Bean class
 */
public class RptDatiVersamento {

    /**
     * RPT DatiVersamento Bean builder class
     */
    public static class Builder {

        private BigDecimal importoTotaleDaVersare;
        private StTipoVersamento tipoVersamento;
        private String identificativoUnivocoVersamento;
        private String ibanAddebito;
        private String bicAddebito;
        private FirmaRicevutaEnum firmaRicevuta;

        /**
         * Build the Rpt DatiVersamento Bean
         * 
         * @return the Rpt DatiVersamento Bean
         */
        public RptDatiVersamento build() {
            return new RptDatiVersamento(this);
        }

        /**
         * Set the importoTotaleDaVersare
         * 
         * @param importoTotaleDaVersare
         * @return
         */
        public Builder setImportoTotaleDaVersare(BigDecimal importoTotaleDaVersare) {
            this.importoTotaleDaVersare = importoTotaleDaVersare;
            return this;
        }

        /**
         * Set the tipoVersamento
         * 
         * @param tipoVersamento
         * @return
         */
        public Builder setTipoVersamento(StTipoVersamento tipoVersamento) {
            this.tipoVersamento = tipoVersamento;
            return this;
        }

        /**
         * Set the identificativoUnivocoVersamento
         * 
         * @param identificativoUnivocoVersamento
         * @return
         */
        public Builder setIdentificativoUnivocoVersamento(String identificativoUnivocoVersamento) {
            this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
            return this;
        }

        /**
         * Set the ibanAddebito
         * 
         * @param ibanAddebito
         * @return
         */
        public Builder setIbanAddebito(String ibanAddebito) {
            this.ibanAddebito = ibanAddebito;
            return this;
        }

        /**
         * Set the bicAddebito
         * 
         * @param bicAddebito
         * @return
         */
        public Builder setBicAddebito(String bicAddebito) {
            this.bicAddebito = bicAddebito;
            return this;
        }

        /**
         * Set the firmaRicevuta
         * 
         * @param firmaRicevuta
         * @return
         */
        public Builder setFirmaRicevuta(FirmaRicevutaEnum firmaRicevuta) {
            this.firmaRicevuta = firmaRicevuta;
            return this;
        }
    }

    @NotNull
    private Date dataEsecuzionePagamento;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.01", message = ErrorMessages.VALIDATION_AMOUNT_MIN)
    private BigDecimal importoTotaleDaVersare;

    @NotNull
    private StTipoVersamento tipoVersamento;

    @NotEmpty
    @Size(max = 35)
    private String identificativoUnivocoVersamento;

    @NotEmpty
    @Size(max = 35)
    private String codiceContestoPagamento;

    @Size(max = 35)
    @Pattern(regexp = Constants.REGEX_IBAN, message = ErrorMessages.VALIDATION_INVALID_IBAN)
    private String ibanAddebito;

    @Size(max = 11)
    @Pattern(regexp = Constants.REGEX_BIC, message = ErrorMessages.VALIDATION_INVALID_BIC)
    private String bicAddebito;

    @NotNull
    private FirmaRicevutaEnum firmaRicevuta;

    /**
     * Private constructor
     */
    private RptDatiVersamento() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptDatiVersamento(Builder builder) {
        this.dataEsecuzionePagamento = new Date();
        this.importoTotaleDaVersare = builder.importoTotaleDaVersare;
        this.tipoVersamento = builder.tipoVersamento;
        this.identificativoUnivocoVersamento = builder.identificativoUnivocoVersamento;
        this.codiceContestoPagamento = UUID.randomUUID().toString().substring(0, 35);
        this.ibanAddebito = builder.ibanAddebito;
        this.bicAddebito = builder.bicAddebito;
        this.firmaRicevuta = builder.firmaRicevuta;
    }

    /**
     * Get the dataEsecuzionePagamento
     * 
     * @return the dataEsecuzionePagamento
     */
    public Date getDataEsecuzionePagamento() {
        return dataEsecuzionePagamento;
    }

    /**
     * Get the importoTotaleDaVersare
     * 
     * @return the importoTotaleDaVersare
     */
    public BigDecimal getImportoTotaleDaVersare() {
        return importoTotaleDaVersare;
    }

    /**
     * Get the tipoVersamento
     * 
     * @return the tipoVersamento
     */
    public StTipoVersamento getTipoVersamento() {
        return tipoVersamento;
    }

    /**
     * Get the identificativoUnivocoVersamento
     * 
     * @return the identificativoUnivocoVersamento
     */
    public String getIdentificativoUnivocoVersamento() {
        return identificativoUnivocoVersamento;
    }

    /**
     * Get the codiceContestoPagamento
     * 
     * @return the codiceContestoPagamento
     */
    public String getCodiceContestoPagamento() {
        return codiceContestoPagamento;
    }

    /**
     * Get the ibanAddebito
     * 
     * @return the ibanAddebito
     */
    public String getIbanAddebito() {
        return ibanAddebito;
    }

    /**
     * Get the bicAddebito
     * 
     * @return the bicAddebito
     */
    public String getBicAddebito() {
        return bicAddebito;
    }

    /**
     * Get the firmaRicevuta
     * 
     * @return the firmaRicevuta
     */
    public FirmaRicevutaEnum getFirmaRicevuta() {
        return firmaRicevuta;
    }
}
