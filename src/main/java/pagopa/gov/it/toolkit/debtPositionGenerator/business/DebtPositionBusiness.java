package pagopa.gov.it.toolkit.debtPositionGenerator.business;

import pagopa.gov.it.toolkit.common.Constants;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPUpdater;
import pagopa.gov.it.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;
import pagopa.gov.it.toolkit.debtPositionGenerator.validation.DebtPositionValidation;
import pagopa.gov.it.toolkit.debtPositionGenerator.validation.DebtPositionValidationImpl;
import pagopa.gov.it.toolkit.iuvGenerator.IuvCodeGeneration;

/**
 * Business logic class
 */
public class DebtPositionBusiness {

    /**
     * Validates a debt position
     * 
     * @param debtPosition
     * @see DebtPosition
     */
    public static void validate(DebtPosition debtPosition) {
        DebtPositionValidation debtPositionValidation = new DebtPositionValidationImpl();
        debtPositionValidation.validate(debtPosition);
    }

    /**
     * Constraints validation by annotation of the debt position or one of its
     * components
     * 
     * @param objectToValidate
     *            the bean to validate. It can be the debt position or one of
     *            its components.
     * @see DebtPositionValidationImpl
     * @see DebtPosition
     */
    public static <T> void validateConstraints(T objectToValidate) {
        DebtPositionValidation debtPositionValidation = new DebtPositionValidationImpl();
        debtPositionValidation.checkConstraints(objectToValidate);
    }

    /**
     * Generates the <code>iuv</code> and updates the <code>debtPosition</code>
     * 
     * @param debtPosition
     * @throws Exception
     * @see DebtPosition
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     * @see pagopa.gov.it.toolkit.iuvGenerator.IuvCodeGeneration
     */
    public static void generateIUV(DebtPosition debtPosition) throws Exception {
        String iuv = debtPosition.getPaymentDetail().getIuv();
        if (iuv == null || iuv.trim().isEmpty()) {
            DPUpdater.setIuv(debtPosition.getPaymentDetail(),
                    IuvCodeGeneration.generate(debtPosition.getPaymentDetail().getAuxDigit(),
                            debtPosition.getPaymentDetail().getSegregationCode(),
                            debtPosition.getPaymentDetail().getApplicationCode()));
        }
    }

    /**
     * Generates the <code>noticeNumber</code> by <code>iuv</code> and updates
     * the <code>debtPosition</code>
     * 
     * @param debtPosition
     * @see DebtPosition
     * @see pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail
     */
    public static void generateNoticeNumber(DebtPosition debtPosition) {
        int auxDigit = debtPosition.getPaymentDetail().getAuxDigit();
        if (auxDigit == Constants.AUX_DIGIT_0) {
            DPUpdater.setNoticeNumber(debtPosition.getPaymentDetail(), Constants.AUX_DIGIT_0
                    + debtPosition.getPaymentDetail().getApplicationCode() + debtPosition.getPaymentDetail().getIuv());
        } else if (auxDigit == Constants.AUX_DIGIT_3) {
            DPUpdater.setNoticeNumber(debtPosition.getPaymentDetail(),
                    "" + Constants.AUX_DIGIT_3 + debtPosition.getPaymentDetail().getIuv());
        }
    }

    /**
     * Updates <code>paymentStatus</code> in <code>debtPosition</code>
     * 
     * @param debtPosition
     * @param paymentStatusEnum
     * @see DebtPosition
     * @see PaymentStatusEnum
     */
    public static void changePaymentStatus(DebtPosition debtPosition, PaymentStatusEnum paymentStatusEnum) {
        DPUpdater.setPaymentStatus(debtPosition.getPaymentDetail(), paymentStatusEnum);
    }
}
