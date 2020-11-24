package it.pagoPA.toolkit.debtPositionGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.pagoPA.toolkit.common.bean.DatiMarcaBolloDigitale;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * DebtPositionGeneration
 */
public class DebtPositionGeneration {

    /**
     * Generate the DebtPositionPayer
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
     * @return DPPayer
     */
    public static DPPayer generatePayer(String uniqueIdentificationCode,
            StTipoIdentificativoUnivocoPersFG uniqueIdentificationType, String registry, String address,
            String numberStreet, String locality, String province, String nation, String postalCode, String email,
            String mobile) {

        return new DPPayer.Builder().setUniqueIdentificationCode(uniqueIdentificationCode)
                .setUniqueIdentificationType(uniqueIdentificationType).setRegistry(registry).setAddress(address)
                .setNumberStreet(numberStreet).setLocality(locality).setProvince(province).setNation(nation)
                .setPostalCode(postalCode).setEmail(email).setMobile(mobile).build();
    }

    /**
     * Generate the DebtPositionPaymentDetail
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
     * @param debitIban
     * @param debitBic
     * @return DPPaymentDetail
     */
    public static DPPaymentDetail generatePaymentDetail(String domainIdentifier, int auxDigit, Integer segregationCode,
            Integer applicationCode, String iuv, String idTenant, BigDecimal totalAmountPayment, String causal,
            Date expirationDate, String specificCollectionData, String documentNumber, Integer installmentNumber,
            String debitIban, String debitBic) {

        return new DPPaymentDetail.Builder().setDomainIdentifier(domainIdentifier).setAuxDigit(auxDigit)
                .setSegregationCode(segregationCode).setApplicationCode(applicationCode).setIuv(iuv)
                .setIdTenant(idTenant).setTotalAmountPayment(totalAmountPayment).setCausal(causal)
                .setExpirationDate(expirationDate).setSpecificCollectionData(specificCollectionData)
                .setDocumentNumber(documentNumber).setInstallmentNumber(installmentNumber).setDebitIban(debitIban)
                .setDebitBic(debitBic).build();
    }

    /**
     * Generate the DatiMarcaBolloDigitale
     * 
     * @param tipoBollo
     * @param hashDocumento
     * @param provinciaResidenza
     * @return RptDatiMarcaBolloDigitale
     */
    public static DatiMarcaBolloDigitale generateDatiMarcaBolloDigitale(TipoBolloEnum tipoBollo, String hashDocumento,
            String provinciaResidenza) {

        return new DatiMarcaBolloDigitale.Builder().setTipoBollo(tipoBollo).setHashDocumento(hashDocumento)
                .setProvinciaResidenza(provinciaResidenza).build();
    }

    /**
     * Generate the DebtPositionSinglePaymentDetail
     * 
     * @param amountSinglePayment
     * @param orderSinglePayment
     * @param causalDescriptionSinglePayment
     * @param creditIban
     * @param creditBic
     * @param supportIban
     * @param supportBic
     * @param datiMarcaBolloDigitale
     * @return
     */
    public static DPSinglePaymentDetail generateSinglePaymentsDetail(BigDecimal amountSinglePayment,
            Integer orderSinglePayment, String causalDescriptionSinglePayment, String creditIban, String creditBic,
            String supportIban, String supportBic, DatiMarcaBolloDigitale datiMarcaBolloDigitale) {

        return new DPSinglePaymentDetail.Builder().setAmountSinglePayment(amountSinglePayment)
                .setOrderSinglePayment(orderSinglePayment)
                .setCausalDescriptionSinglePayment(causalDescriptionSinglePayment).setCreditIban(creditIban)
                .setCreditBic(creditBic).setSupportIban(supportIban).setSupportBic(supportBic)
                .setDatiMarcaBolloDigitale(datiMarcaBolloDigitale).build();
    }

    /**
     * Generate the DebtPosition
     * 
     * @param payer
     * @param paymentDetail
     * @param singlePaymentsDetailList
     * @return DebtPosition
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