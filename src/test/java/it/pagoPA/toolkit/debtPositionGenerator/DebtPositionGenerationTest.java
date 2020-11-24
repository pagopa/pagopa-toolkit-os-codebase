/**
 * 
 */
package it.pagoPA.toolkit.debtPositionGenerator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.pagoPA.toolkit.common.bean.DatiMarcaBolloDigitale;
import it.pagoPA.toolkit.debtPositionGenerator.DebtPositionGeneration;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import it.pagoPA.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import it.pagoPA.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import it.pagoPA.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

public class DebtPositionGenerationTest {

    String uniqueIdentificationCode = "RSSMRA80A01F205X";
    StTipoIdentificativoUnivocoPersFG typeUniqueIdentifier = StTipoIdentificativoUnivocoPersFG.F;
    String registry = "ROSSI MARIO";
    String address = "Via Roma";
    String numberStreet = "223";
    String locality = "Firenze";
    String province = "FI";
    String nation = "IT";
    String postalCode = "50127";
    String email = "mario.rossi@gmail.it";
    String mobile = "3381234567";
    String domainIdentifier = "01234567890";
    int auxDigit = 3;
    Integer segregationCode = 01;
    Integer applicationCode = 01;
    String iuv = "01202000000003130";
    String idTenant = "asfkjsd3";
    BigDecimal totalAmountPayment = BigDecimal.valueOf(11.23);
    String causal = "causal test";
    Date expirationDate = new GregorianCalendar(2222, Calendar.OCTOBER, 03).getTime();
    String specificCollectionData = "9/test";
    String documentNumber = "00998877";
    Integer installmentNumber = 0;
    String debitIban = "IT58C0306914512000000046601";
    String debitBic = "BCITITMMXXX";
    BigDecimal amountSinglePayment = BigDecimal.valueOf(11.23);
    Integer orderSinglePayment = 1;
    String causalDescriptionSinglePayment = "single payment causal";
    String creditIban = "IT73W0306953740100000300003";
    String creditBic = "BCITITMMXXX";
    String supportIban = "IT33I0760114500000041940305";
    String supportBic = "BPPIITRRXXX";
    TipoBolloEnum tipoBollo = TipoBolloEnum.IMPOSTA_DI_BOLLO;
    String hashDocumento = "aGFzaENvZGUgRG9jdW1lbnRvIE1hcmRhIGRhIEJvbGxv";
    String provinciaResidenza = "FI";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerate() throws Exception {
        DPPayer payer = DebtPositionGeneration.generatePayer(uniqueIdentificationCode, typeUniqueIdentifier, registry,
                address, numberStreet, locality, province, nation, postalCode, email, mobile);
        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,
                segregationCode, applicationCode, iuv, idTenant, totalAmountPayment, causal, expirationDate,
                specificCollectionData, documentNumber, installmentNumber, debitIban, debitBic);
        DatiMarcaBolloDigitale datiMarcaBolloDigitale = DebtPositionGeneration.generateDatiMarcaBolloDigitale(tipoBollo,
                hashDocumento, provinciaResidenza);
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentsDetail(
                amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, creditIban, creditBic,
                supportIban, supportBic, datiMarcaBolloDigitale);
        List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
        singlePaymentDetailList.add(singlePaymentDetail);
        DebtPosition debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);

        assertNotNull(debtPosition);
        assertNotNull(debtPosition.getPaymentDetail().getNoticeNumber());
    }

    /**
     * 
     */
    @Test
    public void testUniqueIdentificationCodeNotNull() {
        assertNotNull(uniqueIdentificationCode);
    }

    /**
     * 
     */
    @Test
    public void testUniqueIdentificationCodeLength() {
        if (uniqueIdentificationCode != null) {
            assertTrue(uniqueIdentificationCode.length() <= 35 && uniqueIdentificationCode.length() > 0);
        }
    }

    /**
     * 
     */
    @Test
    public void testTypeUniqueIdentifierNotNull() {
        assertNotNull(typeUniqueIdentifier);
    }

    /**
     * 
     */
    @Test
    public void testPayerRegistryNotNull() {
        assertNotNull(registry);
    }

    /**
     * 
     */
    @Test
    public void testPayerRegistryLength() {
        if (registry != null)
            assertTrue(registry.length() <= 70 && registry.length() > 0);
    }

    /**
     * 
     */
    @Test
    public void testAddressLength() {
        if (address != null)
            assertTrue(address.length() <= 70);
    }

    /**
     * 
     */
    @Test
    public void testNumberStreetLength() {
        if (numberStreet != null)
            assertTrue(numberStreet.length() <= 16);
    }

    /**
     * 
     */
    @Test
    public void testLocalityLength() {
        if (locality != null)
            assertTrue(locality.length() <= 35);
    }

    /**
     * 
     */
    @Test
    public void testProvinceLength() {
        if (province != null)
            assertTrue(province.length() <= 35);
    }

    /**
     * 
     */
    @Test
    public void testNationLength() {
        if (nation != null)
            assertTrue(nation.length() <= 2);
    }

    /**
     * 
     */
    @Test
    public void testPostalCodeLength() {
        if (postalCode != null)
            assertTrue(postalCode.length() <= 70);
    }

    /**
     * 
     */
    @Test
    public void testEmailLength() {
        if (email != null)
            assertTrue(email.length() <= 256);
    }

    /**
     * 
     */
    @Test
    public void testMobileLength() {
        if (mobile != null)
            assertTrue(mobile.length() <= 70);
    }

    /**
     * 
     */
    @Test
    public void testDomainIdentifierNotNull() {
        assertNotNull(domainIdentifier);
    }

    /**
     * 
     */
    @Test
    public void testDomainIdentifierLength() {
        if (domainIdentifier != null)
            assertTrue(domainIdentifier.length() <= 16 && domainIdentifier.length() > 0);
    }

    /**
     * 
     */
    @Test
    public void testIuvNotNull() {
        assertNotNull(iuv);
    }

    /**
     * 
     */
    @Test
    public void testIuvLength() {
        if (iuv != null)
            assertTrue(iuv.length() <= 35 && iuv.length() > 0);
    }

    /**
     * 
     */
    @Test
    public void testIdTenantNotNull() {
        assertNotNull(idTenant);
    }

    /**
     * 
     */
    @Test
    public void testIdTenantLength() {
        if (idTenant != null)
            assertTrue(idTenant.length() <= 50);
    }

    /**
     * 
     */
    @Test
    public void testTotalAmountPaymentNotNull() {
        assertNotNull(totalAmountPayment);
    }

    /**
     * 
     */
    @Test
    public void testCausalNotNull() {
        assertNotNull(causal);
    }

    /**
     * 
     */
    @Test
    public void testCausalLength() {
        if (causal != null)
            assertTrue(causal.length() <= 140 && causal.length() > 0);
    }

    /**
     * 
     */
    @Test
    public void testSpecificCollectionDataLength() {
        if (specificCollectionData != null)
            assertTrue(specificCollectionData.length() <= 256);
    }

    /**
     * 
     */
    @Test
    public void testDocumentNumberLength() {
        if (documentNumber != null)
            assertTrue(documentNumber.length() <= 35);
    }

    /**
     * 
     */
    @Test
    public void testAmountSinglePaymentNotNull() {
        assertNotNull(amountSinglePayment);
    }

    /**
     * 
     */
    @Test
    public void testOrderSinglePaymentNotNull() {
        assertNotNull(orderSinglePayment);
    }

    /**
     * 
     */
    @Test
    public void testCausalDescriptionSinglePaymentLength() {
        if (causalDescriptionSinglePayment != null)
            assertTrue(causalDescriptionSinglePayment.length() <= 140);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testGenerateNoIuv() throws Exception {
        String uniqueIdentificationCode = "RSSMRA80A01F205X";
        StTipoIdentificativoUnivocoPersFG typeUniqueIdentifier = StTipoIdentificativoUnivocoPersFG.F;
        String registry = "ROSSI MARIO";
        String domainIdentifier = "01234567890";
        int auxDigit = 3;
        Integer segregationCode = 01;
        String iuv = null;
        BigDecimal totalAmountPayment = BigDecimal.valueOf(11.23);
        String causal = "causal test";
        String debitIban = "IT58C0306914512000000046601";
        BigDecimal amountSinglePayment = BigDecimal.valueOf(11.23);
        Integer orderSinglePayment = 1;
        String causalDescriptionSinglePayment = "single payment causal";

        DPPayer payer = DebtPositionGeneration.generatePayer(uniqueIdentificationCode, typeUniqueIdentifier, registry,
                null, null, null, null, null, null, null, null);
        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,
                segregationCode, null, iuv, null, totalAmountPayment, causal, null, null, null, null, debitIban, null);
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentsDetail(
                amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, null, null, null, null, null);
        List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
        singlePaymentDetailList.add(singlePaymentDetail);
        DebtPosition debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);

        assertNotNull(debtPosition);
        assertNotNull(debtPosition.getPaymentDetail().getIuv());
        assertNotNull(debtPosition.getPaymentDetail().getNoticeNumber());
    }
}
