package it.pagoPA.toolkit.rptGenerator.bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.rptGenerator.bean.rpt.Rpt;
import it.pagoPA.toolkit.rptGenerator.enumeration.RptStatusEnum;

/**
 * RPT Container Bean class
 */
public class RptContainer {

    /**
     * RPT Container Bean builder class
     */
    public static class Builder {

        private String idTenant;
        private Rpt rpt;

        /**
         * Build the Rpt Container Bean
         * 
         * @return the Rpt Container Bean
         */
        public RptContainer build() {
            return new RptContainer(this);
        }

        /**
         * Set the idTenant
         * 
         * @param idTenant
         * @return
         */
        public Builder setIdTenant(String idTenant) {
            this.idTenant = idTenant;
            return this;
        }

        /**
         * Set the rpt
         * 
         * @param rpt
         * @return
         */
        public Builder setRpt(Rpt rpt) {
            this.rpt = rpt;
            return this;
        }
    }

    @NotEmpty
    @Size(max = 50)
    private String idTenant;

    @NotNull
    private RptStatusEnum rptStatus;

    @NotNull
    @Valid
    private Rpt rpt;

    private byte[] rptXml;

    /**
     * Private constructor
     */
    private RptContainer() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private RptContainer(Builder builder) {
        this.idTenant = builder.idTenant;
        this.rptStatus = RptStatusEnum.CREATED;
        this.rpt = builder.rpt;
    }

    /**
     * Get the idTenant
     * 
     * @return the idTenant
     */
    public String getIdTenant() {
        return idTenant;
    }

    /**
     * Get the rptStatus
     * 
     * @return the rptStatus
     */
    public RptStatusEnum getRptStatus() {
        return rptStatus;
    }

    /**
     * 
     * @param rptStatus
     */
    public void setRptStatus(RptStatusEnum rptStatus) {
        this.rptStatus = rptStatus;
    }

    /**
     * Get the rpt
     * 
     * @return the rpt
     */
    public Rpt getRpt() {
        return rpt;
    }

    /**
     * Get the rptXml
     * 
     * @return the rptXml
     */
    public byte[] getRptXml() {
        return rptXml;
    }

    /**
     * 
     * @param rptXml
     */
    public void setRptXml(byte[] rptXml) {
        this.rptXml = rptXml;
    }
}
