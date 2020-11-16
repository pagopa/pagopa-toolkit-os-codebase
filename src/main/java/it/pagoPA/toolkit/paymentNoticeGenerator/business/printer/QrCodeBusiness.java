package it.pagoPA.toolkit.paymentNoticeGenerator.business.printer;

import java.math.BigDecimal;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;

/**
 *
 */
public class QrCodeBusiness {

	private static final String PAGOPA_QR_CODE_STRING = "PAGOPA|002|%1$s|%2$s|%3$s";
	private static final String DIGIT_OF_2 = "%02d";

	/**
	 * 
	 * @param debtPosition
	 * @param creditorInstitutionFiscalCode
	 * @return
	 */
	public static String createQrCode(DebtPosition debtPosition, String creditorInstitutionFiscalCode) {
		BigDecimal amount = debtPosition.getPaymentDetail().getTotalAmountPayment();
		String noticeNumber = debtPosition.getPaymentDetail().getNoticeNumber();
		return createQrCode(amount, noticeNumber, creditorInstitutionFiscalCode);
	}

	/**
	 * 
	 * @param amount
	 * @param noticeNumber
	 * @param creditorInstitutionFiscalCode
	 * @return
	 */
	private static String createQrCode(BigDecimal amount, String noticeNumber, String creditorInstitutionFiscalCode) {
		String centsAmount = String.valueOf((amount.multiply(new BigDecimal(100)).intValue()));
		String centsAmountPadQr = String.format(DIGIT_OF_2, Integer.parseInt(centsAmount));
		String qrCode = String.format(PAGOPA_QR_CODE_STRING, noticeNumber, creditorInstitutionFiscalCode,
				centsAmountPadQr);
		return qrCode;
	}
}
