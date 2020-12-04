package pagopa.gov.it.toolkit.rptGenerator.bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.Rpt;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.RptStatusEnum;

/**
 * Main class of RPT Bean
 */
public class RptContainer {

    /**
     * Initialization of <code>RptContainer</code> Bean class
     */
    public static class Builder {

        private String idTenant;
        private Rpt rpt;

        /**
         * Build a new <code>RptContainer</code> Bean
         * 
         * @return a new instance of <code>RptContainer</code>
         */
        public RptContainer build() {
            return new RptContainer(this);
        }

        /**
         * Set the idTenant
         * 
         * @param idTenant
         *            unique identifier Internal payment of the institution.
         *            <br/>
         *            Not null nor empty<br/>
         *            Max 50 chars.
         * @return the <code>idTenant</code> field is set in
         *         <code>RptContainer</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
         */
        public Builder setIdTenant(String idTenant) {
            this.idTenant = idTenant;
            return this;
        }

        /**
         * Set the rpt
         * 
         * @param rpt
         *            IT object sent by the Creditor to the PSP through the
         *            "Nodo dei Pagamenti-SPC" in order to request the execution
         *            of a payment.<br/>
         *            Not null nor empty<br/>
         * @return the <code>rpt</code> field is set in
         *         <code>RptContainer</code> builder
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
    @Valid
    private Rpt rpt;

    @NotNull
    private RptStatusEnum rptStatus;

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
        this.rpt = builder.rpt;
        this.rptStatus = RptStatusEnum.CREATED;
    }

    /**
     * Get the idTenant
     * 
     * @return the idTenant
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer.Builder#idTenant
     */
    public String getIdTenant() {
        return idTenant;
    }

    /**
     * Get the rpt
     * 
     * @return the rpt
     * @see pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer.Builder#rpt
     */
    public Rpt getRpt() {
        return rpt;
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
     * Get the rptXml
     * 
     * @return the rptXml
     */
    public byte[] getRptXml() {
        return rptXml;
    }

    /**
     * Set the rptStatus
     * 
     * @param rptStatus
     *            status of RPT based on its enumeration.<br/>
     *            Enumeration:
     *            <ul>
     *            <li>CREATED - 1
     *            <li>SENT - 2
     *            <li>RECEIVED_RT - 3
     *            </ul>
     */
    protected void setRptStatus(RptStatusEnum rptStatus) {
        this.rptStatus = rptStatus;
    }

    /**
     * Set the rptXml
     * 
     * @param rptXml
     *            the rpt in xml format, ready to be sent to "Nodo dei
     *            Pagamenti-SPC"
     */
    protected void setRptXml(byte[] rptXml) {
        this.rptXml = rptXml;
    }
}
