package pagopa.gov.it.toolkit.debtPositionGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.business.DebtPositionBusiness;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Main class for the generation of the <code>DebtPosition</code>
 */
public class DebtPositionGeneration {

    /**
     * Generates the component <code>DPPayer</code> of <code>DebtPosition</code>
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
     * @see DPPayer
     */
    public static DPPayer generatePayer(String uniqueIdentificationCode,
            StTipoIdentificativoUnivocoPersFG uniqueIdentificationType, String registry, String address,
            String numberStreet, String locality, String province, String nation, String postalCode, String email,
            String mobile) {

        DPPayer payer = new DPPayer.Builder().setUniqueIdentificationCode(uniqueIdentificationCode)
                .setUniqueIdentificationType(uniqueIdentificationType).setRegistry(registry).setAddress(address)
                .setNumberStreet(numberStreet).setLocality(locality).setProvince(province).setNation(nation)
                .setPostalCode(postalCode).setEmail(email).setMobile(mobile).build();

        DebtPositionBusiness.validateConstraints(payer);

        return payer;
    }

    /**
     * Generates the component <code>DPPaymentDetail</code> of
     * <code>DebtPosition</code>
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
     * @see DPPaymentDetail
     */
    public static DPPaymentDetail generatePaymentDetail(String domainIdentifier, int auxDigit, Integer segregationCode,
            Integer applicationCode, String iuv, String idTenant, BigDecimal totalAmountPayment, String causal,
            Date expirationDate, String specificCollectionData, String documentNumber, Integer installmentNumber,
            String debitIban, String debitBic) {

        DPPaymentDetail paymentDetail = new DPPaymentDetail.Builder().setDomainIdentifier(domainIdentifier)
                .setAuxDigit(auxDigit).setSegregationCode(segregationCode).setApplicationCode(applicationCode)
                .setIuv(iuv).setIdTenant(idTenant).setTotalAmountPayment(totalAmountPayment).setCausal(causal)
                .setExpirationDate(expirationDate).setSpecificCollectionData(specificCollectionData)
                .setDocumentNumber(documentNumber).setInstallmentNumber(installmentNumber).setDebitIban(debitIban)
                .setDebitBic(debitBic).build();

        DebtPositionBusiness.validateConstraints(paymentDetail);

        return paymentDetail;
    }

    /**
     * Generates the component <code>DatiMarcaBolloDigitale</code> of
     * <code>DebtPosition</code>
     * 
     * @param tipoBollo
     * @param hashDocumento
     * @param provinciaResidenza
     * @return RptDatiMarcaBolloDigitale
     * @see DatiMarcaBolloDigitale
     */
    public static DatiMarcaBolloDigitale generateDatiMarcaBolloDigitale(TipoBolloEnum tipoBollo, String hashDocumento,
            String provinciaResidenza) {

        DatiMarcaBolloDigitale datiMarcaBolloDigitale = new DatiMarcaBolloDigitale.Builder().setTipoBollo(tipoBollo)
                .setHashDocumento(hashDocumento).setProvinciaResidenza(provinciaResidenza).build();

        DebtPositionBusiness.validateConstraints(datiMarcaBolloDigitale);

        return datiMarcaBolloDigitale;
    }

    /**
     * Generates the component <code>DPSinglePaymentDetail</code> of
     * <code>DebtPosition</code>
     * 
     * @param amountSinglePayment
     * @param orderSinglePayment
     * @param causalDescriptionSinglePayment
     * @param creditIban
     * @param creditBic
     * @param supportIban
     * @param supportBic
     * @param datiMarcaBolloDigitale
     * @return DPSinglePaymentDetail
     * @see DPSinglePaymentDetail
     */
    public static DPSinglePaymentDetail generateSinglePaymentDetail(BigDecimal amountSinglePayment,
            Integer orderSinglePayment, String causalDescriptionSinglePayment, String creditIban, String creditBic,
            String supportIban, String supportBic, DatiMarcaBolloDigitale datiMarcaBolloDigitale) {

        DPSinglePaymentDetail singlePaymentDetail = new DPSinglePaymentDetail.Builder()
                .setAmountSinglePayment(amountSinglePayment).setOrderSinglePayment(orderSinglePayment)
                .setCausalDescriptionSinglePayment(causalDescriptionSinglePayment).setCreditIban(creditIban)
                .setCreditBic(creditBic).setSupportIban(supportIban).setSupportBic(supportBic)
                .setDatiMarcaBolloDigitale(datiMarcaBolloDigitale).build();

        DebtPositionBusiness.validateConstraints(singlePaymentDetail);

        return singlePaymentDetail;
    }

    /**
     * Generates the <code>DebtPosition</code> based on its components
     * 
     * @param payer
     * @param paymentDetail
     * @param singlePaymentsDetailList
     * @return DebtPosition
     * @throws Exception
     * @see DPPayer
     * @see DPPaymentDetail
     * @see DPSinglePaymentDetail
     * @see DebtPosition
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