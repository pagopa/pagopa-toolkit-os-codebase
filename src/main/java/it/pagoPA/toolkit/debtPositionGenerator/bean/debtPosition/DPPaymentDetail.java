package it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.pagoPA.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;

/**
 * Debt Position Payment Detail class
 * 
 * installmentNumber = 0 Rata unica
 * installmentNumber da 1 a n = numero di rate
 */
public class DPPaymentDetail {

	/**
	 * Debt Position Payment Detail builder class
	 */
	public static class Builder {

		private String domainIdentifier;
		private int auxDigit;
		private Integer applicationCode;
		private Integer segregationCode;
		private String iuv;
		private String idTenant;
		private BigDecimal totalAmountPayment;
		private String causal;
		private Date expirationDate;
		private String specificCollectionData;
		private String documentNumber;
		private Integer installmentNumber;

		/**
		 * Build the Debt Position Payment Detail
		 * 
		 * @return the Debt Position Payment Detail
		 */
		public DPPaymentDetail build() {
			return new DPPaymentDetail(this);
		}

		/**
		 * @param totalAmountPayment
		 *            the totalAmountPayment to set
		 */
		public Builder setTotalAmountPayment(BigDecimal totalAmountPayment) {
			this.totalAmountPayment = totalAmountPayment;
			return this;
		}

		/**
		 * @param causal
		 *            the causal to set
		 */
		public Builder setCausal(String causal) {
			this.causal = causal;
			return this;
		}

		/**
		 * @param documentNumber
		 *            the documentNumber to set
		 */
		public Builder setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
			return this;
		}

		/**
		 * @param domainIdentifier
		 *            the domainIdentifier to set
		 */
		public Builder setDomainIdentifier(String domainIdentifier) {
			this.domainIdentifier = domainIdentifier;
			return this;
		}

		/**
		 * @param expirationDate
		 *            the expirationDate to set
		 */
		public Builder setExpirationDate(Date expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		/**
		 * @param idTenant
		 *            the idTenant to set
		 */
		public Builder setIdTenant(String idTenant) {
			this.idTenant = idTenant;
			return this;
		}

		/**
		 * @param installmentNumber
		 *            the installmentNumber to set
		 */
		public Builder setInstallmentNumber(Integer installmentNumber) {
			this.installmentNumber = installmentNumber;
			return this;
		}

		/**
		 * @param iuv
		 *            the iuv to set
		 */
		public Builder setIuv(String iuv) {
			this.iuv = iuv;
			return this;
		}

		/**
		 * @param specificCollectionData
		 *            the specificCollectionData to set
		 */
		public Builder setSpecificCollectionData(String specificCollectionData) {
			this.specificCollectionData = specificCollectionData;
			return this;
		}

		/**
		 * @param auxDigit
		 *            the auxDigit to set
		 */
		public Builder setAuxDigit(int auxDigit) {
			this.auxDigit = auxDigit;
			return this;
		}

		/**
		 * 
		 * @param applicationCode
		 *            the applicationCode to set
		 */
		public Builder setApplicationCode(Integer applicationCode) {
			this.applicationCode = applicationCode;
			return this;
		}

		/**
		 * @param segregationCode
		 *            the segregationCode to set
		 */
		public Builder setSegregationCode(Integer segregationCode) {
			this.segregationCode = segregationCode;
			return this;
		}
	}

	@NotNull
	@NotEmpty
	@Size(max = 16)
	private String domainIdentifier;

	@Digits(integer = 1, fraction = 0)
	private int auxDigit;

	@Digits(integer = 2, fraction = 0)
	private Integer segregationCode;

	@Digits(integer = 2, fraction = 0)
	private Integer applicationCode;

	@Size(max = 35)
	private String iuv;

	@Size(max = 50)
	private String idTenant;

	@NotNull
	@Digits(integer = 9, fraction = 2)
	private BigDecimal totalAmountPayment;

	@NotNull
	@NotEmpty
	@Size(max = 60)
	private String causal;

	@Future
	private Date expirationDate;

	@Size(max = 256)
	private String specificCollectionData;

	@Size(max = 35)
	private String documentNumber;

	@Digits(integer = 2, fraction = 0)
	private Integer installmentNumber;

	private Date creationDate;
	private String noticeNumber;
	private PaymentStatusEnum paymentStatus;

	/**
	 * Private constructor
	 */
	private DPPaymentDetail() {
		// NOPE
	}

	/**
	 * Private constructor
	 * 
	 * @param builder
	 */
	private DPPaymentDetail(Builder builder) {
		this.creationDate = new Date();
		this.auxDigit = builder.auxDigit;
		this.segregationCode = builder.segregationCode;
		this.applicationCode = builder.applicationCode;
		this.domainIdentifier = builder.domainIdentifier;
		this.iuv = builder.iuv;
		this.idTenant = builder.idTenant;
		this.paymentStatus = PaymentStatusEnum.PAYABLE;
		this.totalAmountPayment = builder.totalAmountPayment;
		this.causal = builder.causal;
		this.expirationDate = builder.expirationDate;
		this.specificCollectionData = builder.specificCollectionData;
		this.documentNumber = builder.documentNumber;
		this.installmentNumber = builder.installmentNumber;
	}

	/**
	 * @return the totalAmountPayment
	 */
	public BigDecimal getTotalAmountPayment() {
		return totalAmountPayment;
	}

	/**
	 * @return the causal
	 */
	public String getCausal() {
		return causal;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the documentNumber
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @return the domainIdentifier
	 */
	public String getDomainIdentifier() {
		return domainIdentifier;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @return the idTenant
	 */
	public String getIdTenant() {
		return idTenant;
	}

	/**
	 * @return the installmentNumber
	 */
	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	/**
	 * @return the specificCollectionData
	 */
	public String getSpecificCollectionData() {
		return specificCollectionData;
	}

	/**
	 * @return the auxDigit
	 */
	public int getAuxDigit() {
		return auxDigit;
	}

	/**
	 * @return the segregationCode
	 */
	public Integer getSegregationCode() {
		return segregationCode;
	}

	/**
	 * @return the applicationCode
	 */
	public Integer getApplicationCode() {
		return applicationCode;
	}

	/**
	 * @return the paymentStatus
	 */
	public PaymentStatusEnum getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 
	 * @param paymentStatus
	 */
	public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the noticeNumber
	 */
	public String getNoticeNumber() {
		return noticeNumber;
	}

	/**
	 * 
	 * @param noticeNumber
	 */
	public void setNoticeNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}

	/**
	 * 
	 * @param iuv
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
}
