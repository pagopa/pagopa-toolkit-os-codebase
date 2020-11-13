package it.pagoPA.toolkit.debtPositionGenerator.bean;

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
}
