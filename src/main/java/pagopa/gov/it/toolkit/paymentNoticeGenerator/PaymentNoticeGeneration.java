package pagopa.gov.it.toolkit.paymentNoticeGenerator;

import java.util.List;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.business.PaymentNoticeBusiness;

/**
 * Main class for the generation of the pdf of <code>PaymentNotice</code>
 */
public class PaymentNoticeGeneration {

    /**
     * Generates the component <code>PNCreditorInstitution</code>
     * 
     * @param logo
     * @param name
     * @param sector
     * @param info
     * @param fiscalCode
     * @param cbillCode
     * @param postalAccountHolder
     * @param postalAccountNumber
     * @param postalAuthorizationCode
     * @param website
     * @return PNCreditorInstitution
     * @see PNCreditorInstitution
     */
    public static PNCreditorInstitution generateCreditorInstitution(byte[] logo, String name, String sector,
            String info, String fiscalCode, String cbillCode, String postalAccountHolder, String postalAccountNumber,
            String postalAuthorizationCode, String website) {

        PNCreditorInstitution creditorInstitution = new PNCreditorInstitution.Builder().setLogo(logo).setName(name)
                .setSector(sector).setInfo(info).setFiscalCode(fiscalCode).setCbillCode(cbillCode)
                .setPostalAccountHolder(postalAccountHolder).setPostalAccountNumber(postalAccountNumber)
                .setPostalAuthorizationCode(postalAuthorizationCode).setWebsite(website).build();

        PaymentNoticeBusiness.validateConstraints(creditorInstitution);

        return creditorInstitution;
    }

    /**
     * Generate the pdf payment notice.<br/>
     * <code>debtPositionList</code> is recommended it has same payer
     * informations and same payment detail causal
     * 
     * @param debtPositionList
     * @param creditorInstitution
     * @return paymentNotice (ByteArray)
     * @throws Exception
     * @see DebtPosition
     * @see PNCreditorInstitution
     */
    public static byte[] generate(List<DebtPosition> debtPositionList, PNCreditorInstitution creditorInstitution,
            Boolean isModello1or2) throws Exception {

        PaymentNotice paymentNotice = new PaymentNotice.Builder().setDebtPositionList(debtPositionList)
                .setCreditorInstitution(creditorInstitution).setIsModello1or2(isModello1or2).build();

        PaymentNoticeBusiness.validate(paymentNotice);

        return PaymentNoticeBusiness.generatePaymentNotice(paymentNotice);
    }
}
