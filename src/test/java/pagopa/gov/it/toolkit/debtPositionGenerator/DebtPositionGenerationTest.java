package pagopa.gov.it.toolkit.debtPositionGenerator;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Tests on generation of <code>DebtPosition</code>
 */
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
     * Test method on generation of <code>DebtPosition</code> when
     * <code>iuv</code> is present
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionGeneration
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
     * Test on generation of <code>DebtPosition</code> when <code>iuv</code> is
     * absent
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionGeneration
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
