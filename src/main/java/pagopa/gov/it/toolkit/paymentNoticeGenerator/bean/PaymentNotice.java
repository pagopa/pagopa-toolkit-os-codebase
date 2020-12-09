package pagopa.gov.it.toolkit.paymentNoticeGenerator.bean;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;

/**
 * Main class of Payment Notice Bean
 */
public class PaymentNotice {

    /**
     * Initialization of <code>PaymentNotice</code> Bean class
     */
    public static class Builder {

        private List<DebtPosition> debtPositionList;
        private PNCreditorInstitution creditorInstitution;

        /**
         * Build a new <code>PaymentNotice</code> Bean
         * 
         * @return a new instance of <code>PaymentNotice</code>
         */
        public PaymentNotice build() {
            return new PaymentNotice(this);
        }

        /**
         * Set the debtPositionList
         * 
         * @param debtPositionList
         *            a list of DebtPosition.<br/>
         *            If <code>debtPositionList</code> size > 1 then there are
         *            installments.<br/>
         *            Not null nor empty.
         * @return the <code>debtPositionList</code> field is set in
         *         <code>PaymentNotice</code> builder
         * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition
         */
        public Builder setDebtPositionList(List<DebtPosition> debtPositionList) {
            this.debtPositionList = debtPositionList;
            return this;
        }

        /**
         * Set the creditorInstitution
         * 
         * @param creditorInstitution
         *            structure containing the Creditor Institution data.<br/>
         *            Not null.
         * @return the <code>creditorInstitution</code> field is set in
         *         <code>PaymentNotice</code> builder
         * @see pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution
         */
        public Builder setCreditorInstitution(PNCreditorInstitution creditorInstitution) {
            this.creditorInstitution = creditorInstitution;
            return this;
        }
    }

    @NotEmpty
    @Valid
    private List<DebtPosition> debtPositionList;

    @NotNull
    @Valid
    private PNCreditorInstitution creditorInstitution;

    /**
     * Private constructor
     */
    private PaymentNotice() {
        // NOPE
    }

    /**
     * Private constructor
     * 
     * @param builder
     *            builder for instance generation
     */
    private PaymentNotice(Builder builder) {
        this.debtPositionList = builder.debtPositionList;
        this.creditorInstitution = builder.creditorInstitution;
    }

    /**
     * Get the debtPositionList
     * 
     * @return the debtPositionList
     * @see pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PaymentNotice.Builder#debtPositionList
     */
    public List<DebtPosition> getDebtPositionList() {
        return debtPositionList;
    }

    /**
     * Get the creditorInstitution
     * 
     * @return the creditorInstitution
     * @see pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PaymentNotice.Builder#creditorInstitution
     */
    public PNCreditorInstitution getCreditorInstitution() {
        return creditorInstitution;
    }
}
