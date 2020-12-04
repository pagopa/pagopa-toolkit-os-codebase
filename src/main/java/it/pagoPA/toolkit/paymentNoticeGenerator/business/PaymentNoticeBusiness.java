package it.pagoPA.toolkit.paymentNoticeGenerator.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.validation.PaymentNoticeValidation;
import it.pagoPA.toolkit.paymentNoticeGenerator.validation.PaymentNoticeValidationImpl;

/**
 *
 */
public class PaymentNoticeBusiness {

	/**
	 * 
	 * @param paymentNotice
	 */
	public static void validate(PaymentNotice paymentNotice) {
		PaymentNoticeValidation paymentNoticeValidation = new PaymentNoticeValidationImpl();
		paymentNoticeValidation.validate(paymentNotice);
	}

	/**
	 * 
	 * @param paymentNotice
	 * @return
	 * @throws Exception
	 */
	public static byte[] generatePaymentNotice(PaymentNotice paymentNotice) throws Exception {
		PdfPaymentNoticeCreator pdfPaymentNoticeCreator = new PdfPaymentNoticeCreator(paymentNotice);
		pdfPaymentNoticeCreator.createDocument();
		pdfPaymentNoticeCreator.closeStreams();
		return pdfPaymentNoticeCreator.getDocumentInBytes();
	}

	/**
	 * @param paymentNotice
	 * @return
	 */
	public static boolean hasSingleInstallment(PaymentNotice paymentNotice) {
		for (DebtPosition debtPosition : paymentNotice.getDebtPositionList()) {
			if (debtPosition.getPaymentDetail().getInstallmentNumber() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param debtPositionList
	 * @return
	 */
	public static BigDecimal getPaymentTotaleAmount(List<DebtPosition> debtPositionList) {
		BigDecimal totalAmountByInstallments = new BigDecimal(0);
		if (debtPositionList.size() == 1) {
			return debtPositionList.get(0).getPaymentDetail().getTotalAmountPayment();
		} else {
			for (DebtPosition paymentDataBean : debtPositionList) {
				if (paymentDataBean.getPaymentDetail().getInstallmentNumber() == 0) {
					return paymentDataBean.getPaymentDetail().getTotalAmountPayment();
				}
				totalAmountByInstallments = totalAmountByInstallments.add(paymentDataBean.getPaymentDetail().getTotalAmountPayment());
			}
		}
		return totalAmountByInstallments;
	}

	/**
	 * 
	 * @param debtPositionList
	 * @return
	 */
	public static Date getExpirationDate(List<DebtPosition> debtPositionList) {
		if (debtPositionList.size() == 1) {
			return debtPositionList.get(0).getPaymentDetail().getExpirationDate();
		} else {
			for (DebtPosition debtPosition : debtPositionList) {
				if (debtPosition.getPaymentDetail().getInstallmentNumber() == 0) {
					return debtPosition.getPaymentDetail().getExpirationDate();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param debtPositionList
	 * @return
	 */
	public static DebtPosition getReferenceDebtPosition(List<DebtPosition> debtPositionList) {
		if (debtPositionList.size() == 1) {
			return debtPositionList.get(0);
		} else {
			for (DebtPosition debtPosition : debtPositionList) {
				if (debtPosition.getPaymentDetail().getInstallmentNumber() == 0) {
					return debtPosition;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param noticeNumber
	 * @return
	 */
	public static String getFormattedNoticeNumber(String noticeNumber) {
		return noticeNumber.substring(0, 4) + " " + noticeNumber.substring(4, 8) + " " + noticeNumber.substring(8, 12)
				+ " " + noticeNumber.substring(12, 16) + " " + noticeNumber.substring(16, 18);
	}

	/**
	 * 
	 * @param debtPositionList
	 * @return
	 */
	public static HashMap<Integer, DebtPosition> sortDebtPositionListByInstallmentNumberExcludingSingleInstallment(
			List<DebtPosition> debtPositionList) {
		HashMap<Integer, DebtPosition> map = new HashMap<Integer, DebtPosition>();
		for (DebtPosition debtPosition : debtPositionList) {
			if (debtPosition.getPaymentDetail().getInstallmentNumber() == 0) {
				continue;
			}
			map.put(debtPosition.getPaymentDetail().getInstallmentNumber(), debtPosition);
		}
		return map;
	}
}
