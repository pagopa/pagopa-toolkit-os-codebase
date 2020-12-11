package pagopa.gov.it.toolkit.debtPositionGenerator.exception;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pagopa.gov.it.toolkit.debtPositionGenerator.DebtPositionGeneration;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.exception.ValidationException;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Tests on thrown exceptions
 */
public class DebtPositionExceptionTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Test method on <code>ValidationException</code>
     * 
     * @throws Exception
     * @see ValidationException
     * @see DebtPositionGeneration#generate(DPPayer, DPPaymentDetail, List)
     */
    @Test(expected = ValidationException.class)
    public void testValidate() throws Exception {
        DPPayer payer = DebtPositionGeneration.generatePayer(null, null, null, null, null, null, null, null, null, null,
                null);
        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(null, 0, null, null, null, null,
                null, null, null, null, null, null, null, null);
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentDetail(null, null,
                null, null, null, null, null, null);
        List<DPSinglePaymentDetail> singlePaymentDetailList = new LinkedList<DPSinglePaymentDetail>();
        singlePaymentDetailList.add(singlePaymentDetail);
        DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
    }

    /**
     * Test method on <code>ValidationException</code> related to received IUV
     * code
     * 
     * @throws Exception
     * @see pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException
     * @see pagopa.gov.it.toolkit.iuvGenerator.IuvCodeGeneration
     * @see DebtPositionGeneration#generate(DPPayer, DPPaymentDetail, List)
     */
    @Test(expected = pagopa.gov.it.toolkit.iuvGenerator.exception.ValidationException.class)
    public void testReceivedIuv() throws Exception {
        String uniqueIdentificationCode = "RSSMRA80A01F205X";
        StTipoIdentificativoUnivocoPersFG typeUniqueIdentifier = StTipoIdentificativoUnivocoPersFG.F;
        String registry = "ROSSI MARIO";
        String domainIdentifier = "01234567890";
        int auxDigit = 3;
        Integer segregationCode = null;
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
        DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentDetailList);
    }
}
