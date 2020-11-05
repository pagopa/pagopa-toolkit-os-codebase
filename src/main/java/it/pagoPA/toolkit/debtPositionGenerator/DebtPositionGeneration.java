package it.pagoPA.toolkit.debtPositionGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.business.DebtPositionBusiness;

/**
 * DebtPositionGeneration
 */
public class DebtPositionGeneration {

	/**
	 * 
	 * @param uniqueIdentificationCode
	 * @param uniqueIdentificationType
	 * @param registry
	 * @param address
	 * @param numberStreet
	 * @param locality
	 * @param province
	 * @param nation
	 * @param postalCode
	 * @param email
	 * @param mobile
	 * @return
	 */
	public static DPPayer generatePayer(String uniqueIdentificationCode, String uniqueIdentificationType,
			String registry, String address, String numberStreet, String locality, String province, String nation,
			String postalCode, String email, String mobile) {

		return new DPPayer.Builder().setUniqueIdentificationCode(uniqueIdentificationCode)
				.setUniqueIdentificationType(uniqueIdentificationType).setPayerRegistry(registry).setAddress(address)
				.setNumberStreet(numberStreet).setLocality(locality).setProvince(province).setNation(nation)
				.setPostalCode(postalCode).setEmail(email).setMobile(mobile).build();
	}

	/**
	 * 
	 * @param domainIdentifier
	 * @param auxDigit
	 * @param segregationCode
	 * @param applicationCode
	 * @param iuv
	 * @param idTenant
	 * @param totalAmountPayment
	 * @param causal
	 * @param expirationDate
	 * @param specificCollectionData
	 * @param documentNumber
	 * @param installmentNumber
	 * @return
	 */
	public static DPPaymentDetail generatePaymentDetail(String domainIdentifier, int auxDigit, Integer segregationCode,
			Integer applicationCode, String iuv, String idTenant, BigDecimal totalAmountPayment, String causal,
			Date expirationDate, String specificCollectionData, String documentNumber, Integer installmentNumber) {

		return new DPPaymentDetail.Builder().setDomainIdentifier(domainIdentifier).setAuxDigit(auxDigit)
				.setSegregationCode(segregationCode).setApplicationCode(applicationCode).setIuv(iuv)
				.setIdTenant(idTenant).setTotalAmountPayment(totalAmountPayment).setCausal(causal)
				.setExpirationDate(expirationDate).setSpecificCollectionData(specificCollectionData)
				.setDocumentNumber(documentNumber).setInstallmentNumber(installmentNumber).build();
	}

	/**
	 * 
	 * @param amountSinglePayment
	 * @param orderSinglePayment
	 * @param causalDescriptionSinglePayment
	 * @return
	 */
	public static DPSinglePaymentDetail generateSinglePaymentsDetail(BigDecimal amountSinglePayment,
			Integer orderSinglePayment, String causalDescriptionSinglePayment) {

		return new DPSinglePaymentDetail.Builder().setAmountSinglePayment(amountSinglePayment)
				.setOrderSinglePayment(orderSinglePayment)
				.setCausalDescriptionSinglePayment(causalDescriptionSinglePayment).build();
	}

	/**
	 * 
	 * @param payer
	 * @param paymentDetail
	 * @param singlePaymentsDetailList
	 * @return
	 * @throws Exception
	 */
	public static DebtPosition generate(DPPayer payer, DPPaymentDetail paymentDetail,
			List<DPSinglePaymentDetail> singlePaymentsDetailList) throws Exception {

		DebtPosition debtPosition = new DebtPosition.Builder().setPayer(payer).setPaymentDetail(paymentDetail)
				.setSinglePaymentsDetail(singlePaymentsDetailList).build();

		DebtPositionBusiness.validate(debtPosition);

		DebtPositionBusiness.generateIUV(debtPosition);

		DebtPositionBusiness.generateNoticeNumber(debtPosition);

		return debtPosition;
	}
}