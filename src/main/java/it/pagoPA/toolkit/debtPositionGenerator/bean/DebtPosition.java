package it.pagoPA.toolkit.debtPositionGenerator.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Debt Position Bean class
 */
public class DebtPosition {

	/**
	 * Debt Position Bean builder class
	 */
	public static class Builder {

		private DPPayer payer;
		private DPPaymentDetail paymentDetail;
		private List<DPSinglePaymentDetail> singlePaymentsDetail;

		/**
		 * Build the Debt Position Bean
		 * 
		 * @return the Debt Position Bean
		 */
		public DebtPosition build() {
			return new DebtPosition(this);
		}

		/**
		 * Set the payer
		 * 
		 * @param payer
		 * @return 
		 */
		public Builder setPayer(DPPayer payer) {
			this.payer = payer;
			return this;
		}

		/**
		 * 
		 * Set the payment detail
		 * 
		 * @param paymentDetail
		 * @return 
		 */
		public Builder setPaymentDetail(DPPaymentDetail paymentDetail) {
			this.paymentDetail = paymentDetail;
			return this;
		}

		/**
		 * Set the single payments detail
		 * 
		 * @param singlePaymentsDetail
		 * @return 
		 */
		public Builder setSinglePaymentsDetail(List<DPSinglePaymentDetail> singlePaymentsDetail) {
			this.singlePaymentsDetail = singlePaymentsDetail;
			return this;
		}
	}

	@NotNull
	@Valid
	private DPPayer payer;
	
	@NotNull
	@Valid
	private DPPaymentDetail paymentDetail;
	
	@NotNull
	@NotEmpty
	@Valid
	private List<DPSinglePaymentDetail> singlePaymentsDetailList;

	/**
	 * Private constructor
	 */
	private DebtPosition() {
		// NOPE
	}

	/**
	 * Private constructor
	 * 
	 * @param builder
	 *            builder for instance generation
	 */
	private DebtPosition(Builder builder) {
		this.payer = builder.payer;
		this.paymentDetail = builder.paymentDetail;
		this.singlePaymentsDetailList = builder.singlePaymentsDetail;
	}

	/**
	 * Get the payer
	 * 
	 * @return the payer
	 */
	public DPPayer getPayer() {
		return payer;
	}

	/**
	 * Get the paymentDetail
	 * 
	 * @return the paymentDetail
	 */
	public DPPaymentDetail getPaymentDetail() {
		return paymentDetail;
	}

	/**
	 * Get the singlePaymentDetailList
	 * 
	 * @return the singlePaymentDetailList
	 */
	public List<DPSinglePaymentDetail> getSinglePaymentsDetailList() {
		return singlePaymentsDetailList;
	}
	
	//FIXME sezione per task Stampa Avviso - WORK IN PROGRESS - da rimuovere
	/**
	 * 
	 * @param noticeNumber
	 * @return
	 */
	public String getFormattedNoticeNumber(String noticeNumber) {
		if (noticeNumber != null && noticeNumber.length() == 18) {
			return noticeNumber.substring(0, 4) + " " + noticeNumber.substring(4, 8) + " "
					+ noticeNumber.substring(8, 12) + " " + noticeNumber.substring(12, 16) + " "
					+ noticeNumber.substring(16, 18);
		} else {
			return noticeNumber;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getRataFormattedNoticeNumber() {
		return paymentDetail.getNoticeNumber().substring(0, 4) + " " + paymentDetail.getNoticeNumber().substring(4, 8)
				+ " " + paymentDetail.getNoticeNumber().substring(8, 12) + " "
				+ paymentDetail.getNoticeNumber().substring(12, 16) + " "
				+ paymentDetail.getNoticeNumber().substring(16, 18);
	}

	/**
	 * 
	 * @param domainIdentifier
	 * @return
	 */
	public String createQrCode(String domainIdentifier) {
		String centsAmount = String
				.valueOf((paymentDetail.getTotalAmountPayment().multiply(new BigDecimal(100)).intValue()));
		String centsAmountPadQr = String.format("%02d", Integer.parseInt(centsAmount));
		String qrCode = String.format("PAGOPA|002|%1$s|%2$s|%3$s", paymentDetail.getNoticeNumber(),
				domainIdentifier, centsAmountPadQr);
		return qrCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getDataMatrixPaymentSubject() {
		if (paymentDetail.getCausal().length() > 60)
			return paymentDetail.getCausal().substring(0, 60);
		return paymentDetail.getCausal();
	}
}
