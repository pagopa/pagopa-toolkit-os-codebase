package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.common.bean.DatiMarcaBolloDigitale;
import pagopa.gov.it.toolkit.debtPositionGenerator.DebtPositionGeneration;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPayer;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPPaymentDetail;
import pagopa.gov.it.toolkit.debtPositionGenerator.bean.debtPosition.DPSinglePaymentDetail;
import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.reader.constants.InputOutputFileConstants;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

public class InputFileBusiness {

    static CsvInputLine generateCsvInputLine(String line, byte[] domainLogo) throws Exception {
        if (!isValid(line)) {
            return null;
        }

        CsvInputLine csvInputLine = new CsvInputLine();
        String[] arrayDebtPosition = line.split(InputOutputFileConstants.CSV_SEPARATOR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(InputOutputFileConstants.CSV_INPUT_DATE_FORMAT);

        csvInputLine.setLine(line);

        csvInputLine.setPayerUniqueIdentificationCode(getValue(arrayDebtPosition[0]));
        csvInputLine.setPayerUniqueIdentificationType(
                transformStTipoIdentificativoUnivocoPersFG(getValue(arrayDebtPosition[1])));
        csvInputLine.setPayerRegistry(getValue(arrayDebtPosition[2]));
        csvInputLine.setPayerAddress(getValue(arrayDebtPosition[3]));
        csvInputLine.setPayerNumberStreet(getValue(arrayDebtPosition[4]));
        csvInputLine.setPayerLocality(getValue(arrayDebtPosition[5]));
        csvInputLine.setPayerProvince(StringUtils.upperCase(getValue(arrayDebtPosition[6])));
        csvInputLine.setPayerNation(StringUtils.upperCase(getValue(arrayDebtPosition[7])));
        csvInputLine.setPayerPostalCode(getValue(arrayDebtPosition[8]));
        csvInputLine.setPayerEmail(getValue(arrayDebtPosition[9]));
        csvInputLine.setPayerMobile(getValue(arrayDebtPosition[10]));

        csvInputLine.setDomainIdentifier(getValue(arrayDebtPosition[11]));
        csvInputLine.setDomainFiscalCode(getValue(arrayDebtPosition[12]));
        csvInputLine.setDomainName(getValue(arrayDebtPosition[13]));
        csvInputLine.setDomainOperationalUnitCode(getValue(arrayDebtPosition[14]));
        csvInputLine.setDomainOperationalUnitName(getValue(arrayDebtPosition[15]));
        csvInputLine.setDomainAddress(getValue(arrayDebtPosition[16]));
        csvInputLine.setDomainNumberStreet(getValue(arrayDebtPosition[17]));
        csvInputLine.setDomainPostalCode(getValue(arrayDebtPosition[18]));
        csvInputLine.setDomainLocality(getValue(arrayDebtPosition[19]));
        csvInputLine.setDomainProvince(getValue(arrayDebtPosition[20]));
        csvInputLine.setDomainNation(getValue(arrayDebtPosition[21]));
        csvInputLine.setDomainSector(getValue(arrayDebtPosition[22]));
        csvInputLine.setDomainCbillCode(getValue(arrayDebtPosition[23]));
        csvInputLine.setDomainPostalAccountHolder(getValue(arrayDebtPosition[24]));
        csvInputLine.setDomainPostalAccountNumber(getValue(arrayDebtPosition[25]));
        csvInputLine.setDomainPostalAuthorizationCode(getValue(arrayDebtPosition[26]));
        csvInputLine.setDomainChargeCommission(new BigDecimal(getValue(arrayDebtPosition[27])));
        csvInputLine.setDomainAuxDigit(
                getValue(arrayDebtPosition[28]) != null ? Integer.parseInt(getValue(arrayDebtPosition[28])) : null);
        csvInputLine.setDomainSegregationCode(
                getValue(arrayDebtPosition[29]) != null ? Integer.parseInt(getValue(arrayDebtPosition[29])) : null);
        csvInputLine.setDomainApplicationCode(
                getValue(arrayDebtPosition[30]) != null ? Integer.parseInt(getValue(arrayDebtPosition[30])) : null);
        csvInputLine.setDomainInfo(getValue(arrayDebtPosition[31]));
        csvInputLine.setDomainWebsite(getValue(arrayDebtPosition[32]));
        csvInputLine.setDomainLogo(domainLogo);

        csvInputLine.setTenantId(getValue(arrayDebtPosition[33]));
        csvInputLine.setTotalAmountPayment(new BigDecimal(getValue(arrayDebtPosition[34])));
        csvInputLine.setCausal(getValue(arrayDebtPosition[35]));
        Date expirationDate = dateFormat.parse(getValue(arrayDebtPosition[36]));
        csvInputLine.setExpirationDate(expirationDate);
        csvInputLine.setSpecificCollectionData(getValue(arrayDebtPosition[37]));
        csvInputLine.setDocumentNumber(getValue(arrayDebtPosition[38]));
        csvInputLine.setInstallmentNumber(
                getValue(arrayDebtPosition[39]) != null ? Integer.parseInt(getValue(arrayDebtPosition[39])) : null);
        csvInputLine.setDebitIban(StringUtils.upperCase(getValue(arrayDebtPosition[40])));
        csvInputLine.setDebitBic(StringUtils.upperCase(getValue(arrayDebtPosition[41])));

        csvInputLine.setAmountSinglePayment(new BigDecimal(getValue(arrayDebtPosition[34])));
        csvInputLine.setOrderSinglePayment(
                getValue(arrayDebtPosition[42]) != null ? Integer.parseInt(getValue(arrayDebtPosition[42])) : null);
        csvInputLine.setCausalDescriptionSinglePayment(getValue(arrayDebtPosition[43]));
        csvInputLine.setCreditIban(StringUtils.upperCase(getValue(arrayDebtPosition[44])));
        csvInputLine.setCreditBic(StringUtils.upperCase(getValue(arrayDebtPosition[45])));
        csvInputLine.setSupportIban(StringUtils.upperCase(getValue(arrayDebtPosition[46])));
        csvInputLine.setSupportBic(StringUtils.upperCase(getValue(arrayDebtPosition[47])));
        TipoBolloEnum tipoBollo = transformTipoBolloEnum(getValue(arrayDebtPosition[48]));
        if (tipoBollo != null) {
            csvInputLine.setTipoBollo(tipoBollo);
            csvInputLine.setDocumentHash(getValue(arrayDebtPosition[49]));
            csvInputLine.setResidenceProvince(getValue(arrayDebtPosition[50]));
        }

        Boolean isModello1 = InputOutputFileConstants.CSV_INPUT_MODELLO_1_ACCEPTED_VALUE
                .equalsIgnoreCase(getValue(arrayDebtPosition[51])) ? Boolean.TRUE : Boolean.FALSE;
        csvInputLine.setIsModello1Or2(isModello1);

        return csvInputLine;
    }

    static DebtPosition generateDebtPosition(CsvInputLine csvInputLine) throws Exception {
        String uniqueIdentificationCode = csvInputLine.getPayerUniqueIdentificationCode();
        StTipoIdentificativoUnivocoPersFG uniqueIdentificationType = csvInputLine.getPayerUniqueIdentificationType();
        String registry = csvInputLine.getPayerRegistry();
        String address = csvInputLine.getPayerAddress();
        String numberStreet = csvInputLine.getPayerNumberStreet();
        String locality = csvInputLine.getPayerLocality();
        String province = csvInputLine.getPayerProvince();
        String nation = csvInputLine.getPayerNation();
        String postalCode = csvInputLine.getPayerPostalCode();
        String email = csvInputLine.getPayerEmail();
        String mobile = csvInputLine.getPayerMobile();
        DPPayer payer = DebtPositionGeneration.generatePayer(uniqueIdentificationCode, uniqueIdentificationType,
                registry, address, numberStreet, locality, province, nation, postalCode, email, mobile);

        String domainIdentifier = csvInputLine.getDomainIdentifier();
        int auxDigit = csvInputLine.getDomainAuxDigit();
        Integer segregationCode = csvInputLine.getDomainSegregationCode();
        Integer applicationCode = csvInputLine.getDomainApplicationCode();
        String idTenant = csvInputLine.getTenantId();
        BigDecimal totalAmountPayment = csvInputLine.getTotalAmountPayment();
        String causal = csvInputLine.getCausal();
        Date expirationDate = csvInputLine.getExpirationDate();
        String specificCollectionData = csvInputLine.getSpecificCollectionData();
        String documentNumber = csvInputLine.getDocumentNumber();
        Integer installmentNumber = csvInputLine.getInstallmentNumber();
        String debitIban = csvInputLine.getDebitIban();
        String debitBic = csvInputLine.getDebitBic();
        DPPaymentDetail paymentDetail = DebtPositionGeneration.generatePaymentDetail(domainIdentifier, auxDigit,
                segregationCode, applicationCode, null, idTenant, totalAmountPayment, causal, expirationDate,
                specificCollectionData, documentNumber, installmentNumber, debitIban, debitBic);

        BigDecimal amountSinglePayment = csvInputLine.getAmountSinglePayment();
        Integer orderSinglePayment = csvInputLine.getOrderSinglePayment();
        String causalDescriptionSinglePayment = csvInputLine.getCausalDescriptionSinglePayment();
        String creditIban = csvInputLine.getCreditIban();
        String creditBic = csvInputLine.getCreditBic();
        String supportIban = csvInputLine.getSupportIban();
        String supportBic = csvInputLine.getSupportBic();
        TipoBolloEnum tipoBollo = csvInputLine.getTipoBollo();
        String hashDocumento = csvInputLine.getDocumentHash();
        String provinciaResidenza = csvInputLine.getResidenceProvince();
        DatiMarcaBolloDigitale datiMarcaBolloDigitale = null;
        if (tipoBollo != null && hashDocumento != null && provinciaResidenza != null) {
            datiMarcaBolloDigitale = DebtPositionGeneration.generateDatiMarcaBolloDigitale(tipoBollo, hashDocumento,
                    provinciaResidenza);
        }
        DPSinglePaymentDetail singlePaymentDetail = DebtPositionGeneration.generateSinglePaymentDetail(
                amountSinglePayment, orderSinglePayment, causalDescriptionSinglePayment, creditIban, creditBic,
                supportIban, supportBic, datiMarcaBolloDigitale);
        List<DPSinglePaymentDetail> singlePaymentsDetailList = new ArrayList<DPSinglePaymentDetail>();
        singlePaymentsDetailList.add(singlePaymentDetail);

        return DebtPositionGeneration.generate(payer, paymentDetail, singlePaymentsDetailList);
    }

    private static boolean isValid(String line) {
        String[] arrayDebtPosition = line.split(InputOutputFileConstants.CSV_SEPARATOR);
        if (arrayDebtPosition.length != InputOutputFileConstants.CSV_INPUT_FIELDS_NUMBER) {
            return false;
        }
        for (String string : arrayDebtPosition) {
            string = string.trim();
            if (string.length() < 2 || !string.trim().startsWith(InputOutputFileConstants.CSV_VALUE_DELIMITER)
                    || !string.trim().endsWith(InputOutputFileConstants.CSV_VALUE_DELIMITER)) {
                return false;
            }
        }
        return true;
    }

    private static String getValue(String string) {
        String s = string.trim();
        if (s.equals(InputOutputFileConstants.CSV_NULL_VALUE)) {
            return null;
        }
        return s.substring(1, s.length() - 1).trim();
    }

    private static StTipoIdentificativoUnivocoPersFG transformStTipoIdentificativoUnivocoPersFG(
            String uniqueIdentificationTypeString) {
        StTipoIdentificativoUnivocoPersFG uniqueIdentificationType = null;
        if (uniqueIdentificationTypeString != null) {
            try {
                uniqueIdentificationType = StTipoIdentificativoUnivocoPersFG.fromValue(uniqueIdentificationTypeString);
            } catch (Exception e) {
                return null;
            }
        }
        return uniqueIdentificationType;
    }

    private static TipoBolloEnum transformTipoBolloEnum(String tipoBolloString) {
        TipoBolloEnum tipoBolloEnum = null;
        if (tipoBolloString != null) {
            try {
                tipoBolloEnum = TipoBolloEnum.fromValue(tipoBolloString);
            } catch (Exception e) {
                return null;
            }
        }
        return tipoBolloEnum;
    }
}
