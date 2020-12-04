package pagopa.gov.it.toolkit.debtPositionGenerator;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import pagopa.gov.it.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;

/**
 * Contains the methods for managing the <code>DebtPosition</code>
 */
public class DebtPositionManagement {

    /**
     * Validates a debt position
     * 
     * @param debtPosition
     * @throws Exception
     * @see DebtPosition
     */
    public static void validate(DebtPosition debtPosition) throws Exception {
        DebtPositionBusiness.validate(debtPosition);
    }

    /**
     * Updates <code>paymentStatus</code> in <code>debtPosition</code> making it
     * payable
     * 
     * @param debtPosition
     * @return DebtPosition with the changed status
     * @throws Exception
     * @see DebtPosition
     */
    public static DebtPosition makePayable(DebtPosition debtPosition) throws Exception {
        DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.PAYABLE);
        return debtPosition;
    }

    /**
     * Updates <code>paymentStatus</code> in <code>debtPosition</code> making it
     * not payable
     * 
     * @param debtPosition
     * @return DebtPosition with the changed status
     * @throws Exception
     * @see DebtPosition
     */
    public static DebtPosition makeNotPayable(DebtPosition debtPosition) throws Exception {
        DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.NOT_PAYABLE);
        return debtPosition;
    }

    /**
     * Updates <code>paymentStatus</code> in <code>debtPosition</code> making it
     * canceled
     * 
     * @param debtPosition
     * @return DebtPosition with the changed status
     * @throws Exception
     * @see DebtPosition
     */
    public static DebtPosition makeCancel(DebtPosition debtPosition) throws Exception {
        DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.CANCELED);
        return debtPosition;
    }

    /**
     * Updates <code>paymentStatus</code> in <code>debtPosition</code> making it
     * paid
     * 
     * @param debtPosition
     * @return DebtPosition with the changed status
     * @throws Exception
     * @see DebtPosition
     */
    public static DebtPosition makePaid(DebtPosition debtPosition) throws Exception {
        DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.PAID);
        return debtPosition;
    }
}