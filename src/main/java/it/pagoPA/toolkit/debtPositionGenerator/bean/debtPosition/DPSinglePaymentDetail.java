package it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Debt Position Single Payment Detail class
 */
public class DPSinglePaymentDetail {

	/**
	 * Debt Position Single Payment Detail builder class
	 */
	public static class Builder {

		private BigDecimal amountSinglePayment;
		private Integer orderSinglePayment;
		private String causalDescriptionSinglePayment;

		/**
		 * Build the Debt Position Single Payment Detail
		 * 
		 * @return the Debt Position Single Payment Detail
		 */
		public DPSinglePaymentDetail build() {
			return new DPSinglePaymentDetail(this);
		}

		/**
		 * @param amountSinglePayment
		 *            the amountSinglePayment to set
		 */
		public Builder setAmountSinglePayment(BigDecimal amountSinglePayment) {
			this.amountSinglePayment = amountSinglePayment;
			return this;
		}

		/**
		 * @param causalDescriptionSinglePayment
		 *            the causalDescriptionSinglePayment to set
		 */
		public Builder setCausalDescriptionSinglePayment(String causalDescriptionSinglePayment) {
			this.causalDescriptionSinglePayment = causalDescriptionSinglePayment;
			return this;
		}

		/**
		 * @param orderSinglePayment
		 *            the orderSinglePayment to set
		 */
		public Builder setOrderSinglePayment(Integer orderSinglePayment) {
			this.orderSinglePayment = orderSinglePayment;
			return this;
		}
	}

	@NotNull
	@Digits(integer = 9, fraction = 2)
	private BigDecimal amountSinglePayment;

	@NotNull
	@Digits(integer = 1, fraction = 0)
	private Integer orderSinglePayment;

	@NotNull
	@Size(max = 140)
	private String causalDescriptionSinglePayment;

	/**
	 * Private constructor
	 */
	private DPSinglePaymentDetail() {
		// NOPE
	}

	/**
	 * Private constructor
	 * 
	 * @param builder
	 */
	private DPSinglePaymentDetail(Builder builder) {
		this.amountSinglePayment = builder.amountSinglePayment;
		this.orderSinglePayment = builder.orderSinglePayment;
		this.causalDescriptionSinglePayment = builder.causalDescriptionSinglePayment;
	}

	/**
	 * @return the amountSinglePayment
	 */
	public BigDecimal getAmountSinglePayment() {
		return amountSinglePayment;
	}

	/**
	 * @return the causalDescriptionSinglePayment
	 */
	public String getCausalDescriptionSinglePayment() {
		return causalDescriptionSinglePayment;
	}

	/**
	 * @return the orderSinglePayment
	 */
	public Integer getOrderSinglePayment() {
		return orderSinglePayment;
	}
}
