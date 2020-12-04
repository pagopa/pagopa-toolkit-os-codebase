package it.pagoPA.toolkit.debtPositionGenerator.bean;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;

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
		private List<DPSinglePaymentDetail> singlePaymentDetailList;

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
		 * Set the single payments detail List
		 * 
		 * @param singlePaymentDetailList
		 * @return
		 */
		public Builder setSinglePaymentsDetail(List<DPSinglePaymentDetail> singlePaymentDetailList) {
			this.singlePaymentDetailList = singlePaymentDetailList;
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
	private List<DPSinglePaymentDetail> singlePaymentDetailList;

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
		this.singlePaymentDetailList = builder.singlePaymentDetailList;
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
	public List<DPSinglePaymentDetail> getSinglePaymentDetailList() {
		return singlePaymentDetailList;
	}
}
