package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.util.ArrayList;
import java.util.List;

import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.DebtPositionGeneration;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.PaymentNoticeGeneration;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.rptGenerator.RptGeneration;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;

/**
 * Business logic class for data element generation
 * 
 * @see RptGeneration
 * @see PaymentNoticeGeneration
 * @see DebtPositionGeneration
 */
public class DataGenerationBusiness {

    /**
     * Generates RptContainer from csvInputLine data
     * 
     * @param csvInputLine
     * @param debtPosition
     * @return RptContainer
     * @throws Exception
     * @see CsvInputLine
     * @see RptGeneration
     * @see RptContainer
     */
    static RptContainer generateRptContainer(CsvInputLine csvInputLine, DebtPosition debtPosition) throws Exception {
        RptIdentificativoUnivocoG rptIdentificativoUnivocoG = RptGeneration
                .generateIdentificativoUnivocoG(StTipoIdentificativoUnivocoPersG.G, csvInputLine.getDomainFiscalCode());
        RptIndirizzo rptIndirizzo = RptGeneration.generateIndirizzo(csvInputLine.getDomainAddress(),
                csvInputLine.getDomainNumberStreet(), csvInputLine.getDomainPostalCode(),
                csvInputLine.getDomainLocality(), csvInputLine.getDomainProvince(), csvInputLine.getDomainNation());
        RptEnteBeneficiario enteBeneficiario = RptGeneration.generateEnteBeneficiario(rptIdentificativoUnivocoG,
                csvInputLine.getDomainName(), csvInputLine.getDomainOperationalUnitCode(),
                csvInputLine.getDomainOperationalUnitName(), rptIndirizzo);

        return RptGeneration.generate(csvInputLine.getTenantId(), debtPosition, enteBeneficiario, null);
    }

    /**
     * Generates PNCreditorInstitution from csvInputLine data
     * 
     * @param csvInputLine
     * @return PNCreditorInstitution
     * @see CsvInputLine
     * @see PaymentNoticeGeneration
     * @see PNCreditorInstitution
     */
    static PNCreditorInstitution generateCreditorInstitution(CsvInputLine csvInputLine) {
        return PaymentNoticeGeneration.generateCreditorInstitution(csvInputLine.getDomainLogo(),
                csvInputLine.getDomainName(), csvInputLine.getDomainSector(), csvInputLine.getDomainInfo(),
                csvInputLine.getDomainFiscalCode(), csvInputLine.getDomainCbillCode(),
                csvInputLine.getDomainPostalAccountHolder(), csvInputLine.getDomainPostalAccountNumber(),
                csvInputLine.getDomainPostalAuthorizationCode(), csvInputLine.getDomainWebsite());
    }

    /**
     * Generates DebtPosition from csvInputLine data
     * 
     * @param csvInputLine
     * @return DebtPosition
     * @throws Exception
     * @see CsvInputLine
     * @see DebtPositionGeneration
     * @see DebtPosition
     */
    static DebtPosition generateDebtPosition(CsvInputLine csvInputLine) throws Exception {
        DPPayer payer = DebtPositionGeneration.generatePayer(csvInputLine.getPayerUniqueIdentificationCode(),
                csvInputLine.getPayerUniqueIdentificationType(), csvInputLine.getPayerRegistry(),
                csvInputLine.getPayerAddress(), csvInputLine.getPayerNumberStreet(), csvInputLine.getPayerLocality(),
                csvInputLine.getPayerProvince(), csvInputLine.getPayerNation(), csvInputLine.getPayerPostalCode(),
                csvInputLine.getPayerEmail(), csvInputLine.getPayerMobile());

        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(csvInputLine.getDomainFiscalCode(),
                csvInputLine.getDomainAuxDigit(), csvInputLine.getDomainSegregationCode(),
                csvInputLine.getDomainApplicationCode(), null, csvInputLine.getTenantId(),
                csvInputLine.getTotalAmountPayment(), csvInputLine.getCausal(), csvInputLine.getExpirationDate(),
                csvInputLine.getSpecificCollectionData(), csvInputLine.getDocumentNumber(),
                csvInputLine.getInstallmentNumber(), csvInputLine.getDebitIban(), csvInputLine.getDebitBic());

        DatiMarcaBolloDigitale datiMarcaBolloDigitale = null;
        if (csvInputLine.getTipoBollo() != null) {
            datiMarcaBolloDigitale = DebtPositionGeneration.generateDatiMarcaBolloDigitale(csvInputLine.getTipoBollo(),
                    csvInputLine.getDocumentHash(), csvInputLine.getResidenceProvince());
        }
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentDetail(
                csvInputLine.getTotalAmountPayment(), 1, csvInputLine.getCausalDescriptionSinglePayment(),
                csvInputLine.getCreditIban(), csvInputLine.getCreditBic(), csvInputLine.getSupportIban(),
                csvInputLine.getSupportBic(), datiMarcaBolloDigitale);
        List<DPSinglePaymentDetail> singlePaymentsDetailList = new ArrayList<DPSinglePaymentDetail>();
        singlePaymentsDetailList.add(singlePaymentDetail);

        return DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentsDetailList);
    }
}
