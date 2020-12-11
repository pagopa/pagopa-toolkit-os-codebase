package pagopa.gov.it.toolkit.debtPositionGenerator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.enumeration.PaymentStatusEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Tests on management of <code>DebtPosition</code>
 */
public class DebtPositionManagementTest {

    DebtPosition debtPosition;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        String uniqueIdentificationCode = "RSSMRA80A01F205X";
        StTipoIdentificativoUnivocoPersFG typeUniqueIdentifier = StTipoIdentificativoUnivocoPersFG.F;
        String registry = "ROSSI MARIO";
        String domainIdentifier = "01234567890";
        int auxDigit = 3;
        Integer segregationCode = 01;
        String iuv = "01202000000003130";
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
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentDetail(
                amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, null, null, null, null, null);
        List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
        singlePaymentDetailList.add(singlePaymentDetail);

        debtPosition = DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
    }

    /**
     * Test on validity of <code>DebtPosition</code>
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionManagement
     */
    @Test
    public void testValidate() throws Exception {
        DebtPositionManagement.validate(debtPosition);
    }

    /**
     * Test on status of <code>DebtPosition</code><br/>
     * The status of <code>DebtPosition</code> must become payable
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionManagement
     */
    @Test
    public void testMakePayable() throws Exception {
        assertEquals(DebtPositionManagement.makePayable(debtPosition).getPaymentDetail().getPaymentStatus(),
                PaymentStatusEnum.PAYABLE);
    }

    /**
     * Test on status of <code>DebtPosition</code><br/>
     * The status of <code>DebtPosition</code> must become not payable
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionManagement
     */
    @Test
    public void testMakeNotPayable() throws Exception {
        assertEquals(DebtPositionManagement.makeNotPayable(debtPosition).getPaymentDetail().getPaymentStatus(),
                PaymentStatusEnum.NOT_PAYABLE);
    }

    /**
     * Test on status of <code>DebtPosition</code><br/>
     * The status of <code>DebtPosition</code> must become canceled
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionManagement
     */
    @Test
    public void testMakeCancel() throws Exception {
        assertEquals(DebtPositionManagement.makeCancel(debtPosition).getPaymentDetail().getPaymentStatus(),
                PaymentStatusEnum.CANCELED);
    }

    /**
     * Test on status of <code>DebtPosition</code><br/>
     * The status of <code>DebtPosition</code> must become paid
     * 
     * @throws Exception
     * @see DebtPosition
     * @see DebtPositionManagement
     */
    @Test
    public void testMakePaid() throws Exception {
        assertEquals(DebtPositionManagement.makePaid(debtPosition).getPaymentDetail().getPaymentStatus(),
                PaymentStatusEnum.PAID);
    }
}
