package pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition;

import pagopa.gov.it.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;

/**
 * Allows the set of bean protected fields
 */
public class DPUpdater {

    /**
     * Allows the set of the iuv in DPPaymentDetail
     * 
     * @param paymentDetail
     * @param iuv
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     */
    public static void setIuv(DPPaymentDetail paymentDetail, String iuv) {
        paymentDetail.setIuv(iuv);
    }

    /**
     * Allows the set of the noticeNumber in DPPaymentDetail
     * 
     * @param paymentDetail
     * @param noticeNumber
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     */
    public static void setNoticeNumber(DPPaymentDetail paymentDetail, String noticeNumber) {
        paymentDetail.setNoticeNumber(noticeNumber);
    }

    /**
     * Allows the set of the paymentStatus in DPPaymentDetail
     * 
     * @param paymentDetail
     * @param paymentStatus
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     */
    public static void setPaymentStatus(DPPaymentDetail paymentDetail, PaymentStatusEnum paymentStatus) {
        paymentDetail.setPaymentStatus(paymentStatus);
    }
}
