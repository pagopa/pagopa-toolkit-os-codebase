package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.math.BigDecimal;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.PaymentNoticeGeneration;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.rptGenerator.RptGeneration;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptEnteBeneficiario;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIdentificativoUnivocoG;
import pagopa.gov.it.toolkit.rptGenerator.bean.rpt.RptIndirizzo;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersG;

public class DataGenerationBusiness {

    static RptContainer generateRptContainer(CsvInputLine csvInputLine, DebtPosition debtPosition) throws Exception {
        String idTenant = csvInputLine.getTenantId();
        String identificativoUnivoco = csvInputLine.getDomainIdentifier();
        String denominazione = csvInputLine.getDomainName();
        String codiceUnitOper = csvInputLine.getDomainOperationalUnitCode();
        String denomUnitOper = csvInputLine.getDomainOperationalUnitName();
        String indirizzo = csvInputLine.getDomainAddress();
        String civico = csvInputLine.getDomainNumberStreet();
        String cap = csvInputLine.getDomainPostalCode();
        String localita = csvInputLine.getDomainLocality();
        String provincia = csvInputLine.getDomainProvince();
        String nazione = csvInputLine.getDomainNation();
        BigDecimal commissioneCaricoPA = csvInputLine.getDomainChargeCommission();

        RptIdentificativoUnivocoG rptIdentificativoUnivocoG = RptGeneration
                .generateIdentificativoUnivocoG(StTipoIdentificativoUnivocoPersG.G, identificativoUnivoco);
        RptIndirizzo rptIndirizzo = RptGeneration.generateIndirizzo(indirizzo, civico, cap, localita, provincia,
                nazione);
        RptEnteBeneficiario enteBeneficiario = RptGeneration.generateEnteBeneficiario(rptIdentificativoUnivocoG,
                denominazione, codiceUnitOper, denomUnitOper, rptIndirizzo);

        return RptGeneration.generate(idTenant, debtPosition, enteBeneficiario, commissioneCaricoPA);
    }

    static PNCreditorInstitution generateCreditorInstitution(CsvInputLine csvInputLine) {
        String name = csvInputLine.getDomainName();
        String sector = csvInputLine.getDomainSector();
        String info = csvInputLine.getDomainInfo();
        String fiscalCode = csvInputLine.getDomainFiscalCode();
        String cbillCode = csvInputLine.getDomainCbillCode();
        String postalAccountHolder = csvInputLine.getDomainPostalAccountHolder();
        String postalAccountNumber = csvInputLine.getDomainPostalAccountNumber();
        String postalAuthorizationCode = csvInputLine.getDomainPostalAuthorizationCode();
        String website = csvInputLine.getDomainWebsite();
        byte[] logo = csvInputLine.getDomainLogo();

        return PaymentNoticeGeneration.generateCreditorInstitution(logo, name, sector, info, fiscalCode, cbillCode,
                postalAccountHolder, postalAccountNumber, postalAuthorizationCode, website);
    }
}
