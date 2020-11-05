package it.pagoPA.toolkit.debtPositionGenerator;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import it.pagoPA.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;

/**
 * DebtPositionManagement
 */
public class DebtPositionManagement {
	
	/**
	 * 
	 * @param debtPosition
	 * @throws Exception
	 */
	public static void validate(DebtPosition debtPosition) throws Exception {
		DebtPositionBusiness.validate(debtPosition);
	}
	
	/**
	 * 
	 * @param debtPosition
	 * @return
	 * @throws Exception
	 */
	public static DebtPosition makePayable(DebtPosition debtPosition) throws Exception {
		DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.PAYABLE);
		return debtPosition;
	}
	
	/**
	 * 
	 * @param debtPosition
	 * @return
	 * @throws Exception
	 */
	public static DebtPosition makeNotPayable(DebtPosition debtPosition) throws Exception {
		DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.NOT_PAYABLE);
		return debtPosition;
	}
	
	/**
	 * 
	 * @param debtPosition
	 * @return
	 * @throws Exception
	 */
	public static DebtPosition makeCancel(DebtPosition debtPosition) throws Exception {
		DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.CANCELED);
		return debtPosition;
	}
	
	/**
	 * 
	 * @param debtPosition
	 * @return
	 * @throws Exception
	 */
	public static DebtPosition makePaid(DebtPosition debtPosition) throws Exception {
		DebtPositionBusiness.changePaymentStatus(debtPosition, PaymentStatusEnum.PAID);
		return debtPosition;
	}
}