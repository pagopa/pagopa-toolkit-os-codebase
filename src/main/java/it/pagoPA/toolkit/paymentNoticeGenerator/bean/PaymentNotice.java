package it.pagoPA.toolkit.paymentNoticeGenerator.bean;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;

/**
 * Payment Notice Bean class
 */
public class PaymentNotice {

	/**
	 * Payment Notice Bean builder class
	 */
	public static class Builder {

		private List<DebtPosition> debtPositionList;
		private PNCreditorInstitution creditorInstitution;

		/**
		 * Build the Payment Notice Bean
		 * 
		 * @return the Payment Notice Bean
		 */
		public PaymentNotice build() {
			return new PaymentNotice(this);
		}

		/**
		 * Set the debt position list
		 * 
		 * @param debtPositionList
		 * @return
		 */
		public Builder setDebtPositionList(List<DebtPosition> debtPositionList) {
			this.debtPositionList = debtPositionList;
			return this;
		}

		/**
		 * Set the creditor institution
		 * 
		 * @param creditorInstitution
		 * @return
		 */
		public Builder setCreditorInstitution(PNCreditorInstitution creditorInstitution) {
			this.creditorInstitution = creditorInstitution;
			return this;
		}

	}

	@NotNull
	@NotEmpty
	@Valid
	private List<DebtPosition> debtPositionList;

	@NotNull
	@Valid
	private PNCreditorInstitution creditorInstitution;

	/**
	 * Private constructor
	 */
	private PaymentNotice() {
		// NOPE
	}

	/**
	 * Private constructor
	 * 
	 * @param builder
	 *            builder for instance generation
	 */
	private PaymentNotice(Builder builder) {
		this.debtPositionList = builder.debtPositionList;
		this.creditorInstitution = builder.creditorInstitution;
	}

	/**
	 * Get the debtPositionList
	 * 
	 * @return the debtPositionList
	 */
	public List<DebtPosition> getDebtPositionList() {
		return debtPositionList;
	}

	/**
	 * Get the creditorInstitution
	 * 
	 * @return the creditorInstitution
	 */
	public PNCreditorInstitution getCreditorInstitution() {
		return creditorInstitution;
	}
}
