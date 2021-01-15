package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.reader.constants.InputOutputFileConstants;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

public class InputFileBusiness {

    static CsvInputLine generateCsvInputLine(String textInputLine, byte[] domainLogo) throws Exception {
        if (!isValid(textInputLine)) {
            return null;
        }

        CsvInputLine csvInputLine = new CsvInputLine();
        String[] arrayInputLine = textInputLine.split(InputOutputFileConstants.CSV_SEPARATOR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(InputOutputFileConstants.CSV_INPUT_DATE_FORMAT);

        csvInputLine.setTextInputLine(textInputLine);

        csvInputLine.setDomainAuxDigit(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_AUX_DIGIT_INDEX]) != null
                        ? Integer.parseInt(
                                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_AUX_DIGIT_INDEX]))
                        : null);
        csvInputLine.setDomainSegregationCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_SEGREGATION_CODE_INDEX]) != null
                        ? Integer.parseInt(getValue(
                                arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_SEGREGATION_CODE_INDEX]))
                        : null);
        csvInputLine.setDomainApplicationCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_APPLICATION_CODE_INDEX]) != null
                        ? Integer.parseInt(getValue(
                                arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_APPLICATION_CODE_INDEX]))
                        : null);

        csvInputLine.setDomainFiscalCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_FISCAL_CODE_INDEX]));
        csvInputLine.setDomainName(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_NAME_INDEX]));
        csvInputLine.setDomainOperationalUnitCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_OPER_UNIT_CODE_INDEX]));
        csvInputLine.setDomainOperationalUnitName(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_OPER_UNIT_NAME_INDEX]));
        csvInputLine
                .setDomainAddress(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_ADDRESS_INDEX]));
        csvInputLine.setDomainNumberStreet(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_NUMBER_STREET_INDEX]));
        csvInputLine
                .setDomainLocality(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_LOCALITY_INDEX]));
        csvInputLine
                .setDomainProvince(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_PROVINCE_INDEX]));
        csvInputLine.setDomainPostalCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_POSTAL_CODE_INDEX]));
        csvInputLine.setDomainNation(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_NATION_INDEX]));
        csvInputLine.setDomainSector(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_SECTOR_INDEX]));
        csvInputLine.setDomainCbillCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_CBILL_CODE_INDEX]));
        csvInputLine.setDomainPostalAccountHolder(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_POSTAL_ACCOUNT_HOLDER_INDEX]));
        csvInputLine.setDomainPostalAccountNumber(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_POSTAL_ACCOUNT_NUMBER_INDEX]));
        csvInputLine.setDomainPostalAuthorizationCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_POSTAL_AUTH_CODE_INDEX]));
        csvInputLine.setDomainInfo(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_INFO_INDEX]));
        csvInputLine
                .setDomainWebsite(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOMAIN_WEBSITE_INDEX]));
        csvInputLine.setDomainLogo(domainLogo);

        csvInputLine.setPayerUniqueIdentificationCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_UNIQUE_IDENT_CODE_INDEX]));
        csvInputLine.setPayerUniqueIdentificationType(transformStTipoIdentificativoUnivocoPersFG(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_UNIQUE_IDENT_TYPE_INDEX])));
        csvInputLine
                .setPayerRegistry(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_REGISTRY_INDEX]));
        csvInputLine.setPayerAddress(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_ADDRESS_INDEX]));
        csvInputLine.setPayerNumberStreet(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_NUMBER_STREET_INDEX]));
        csvInputLine
                .setPayerLocality(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_LOCALITY_INDEX]));
        csvInputLine.setPayerProvince(StringUtils
                .upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_PROVINCE_INDEX])));
        csvInputLine.setPayerPostalCode(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_POSTAL_CODE_INDEX]));
        csvInputLine.setPayerNation(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_NATION_INDEX])));
        csvInputLine.setPayerEmail(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_EMAIL_INDEX]));
        csvInputLine.setPayerMobile(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_PAYER_MOBILE_INDEX]));

        csvInputLine.setTenantId(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_TENANT_ID_INDEX]));
        csvInputLine.setTotalAmountPayment(
                new BigDecimal(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_TOTAL_AMOUNT_PAYMENT_INDEX]))
                        .divide((BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP)));
        csvInputLine.setCausal(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_CAUSAL_INDEX]));
        Date expirationDate = dateFormat
                .parse(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_EXPIRATION_DATE_INDEX]));
        csvInputLine.setExpirationDate(expirationDate);
        csvInputLine.setSpecificCollectionData(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_SPECIFIC_COLLECTION_DATA_INDEX]));
        csvInputLine
                .setDocumentNumber(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOCUMENT_NUMBER_INDEX]));
        csvInputLine.setInstallmentNumber(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_INSTALLMENT_NUMBER_INDEX]) != null
                        ? Integer.parseInt(
                                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_INSTALLMENT_NUMBER_INDEX]))
                        : null);
        csvInputLine.setDebitIban(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DEBIT_IBAN_INDEX])));
        csvInputLine.setDebitBic(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DEBIT_BIC_INDEX])));
        csvInputLine.setCausalDescriptionSinglePayment(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_CAUSAL_DESCRIPTION_INDEX]));
        csvInputLine.setCreditIban(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_CREDIT_IBAN_INDEX])));
        csvInputLine.setCreditBic(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_CREDIT_BIC_INDEX])));
        csvInputLine.setSupportIban(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_SUPPORT_IBAN_INDEX])));
        csvInputLine.setSupportBic(
                StringUtils.upperCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_SUPPORT_BIC_INDEX])));
        TipoBolloEnum tipoBollo = transformTipoBolloEnum(
                getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_TIPO_BOLLO_INDEX]));
        if (tipoBollo != null) {
            csvInputLine.setTipoBollo(tipoBollo);
            csvInputLine
                    .setDocumentHash(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_DOCUMENT_HASH_INDEX]));
            csvInputLine.setResidenceProvince(
                    getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_RESIDENCE_PROVINCE_INDEX]));
        }
        Boolean isModello1 = InputOutputFileConstants.CSV_INPUT_MODELLO_1_ACCEPTED_VALUE
                .equalsIgnoreCase(getValue(arrayInputLine[InputOutputFileConstants.CSV_INPUT_MODELLO_1_INDEX]))
                        ? Boolean.TRUE : Boolean.FALSE;
        csvInputLine.setIsModello1Or2(isModello1);

        return csvInputLine;
    }

    private static boolean isValid(String line) {
        String[] arrayDebtPosition = line.split(InputOutputFileConstants.CSV_SEPARATOR);
        if (arrayDebtPosition.length != InputOutputFileConstants.CSV_INPUT_FIELDS_TOTAL_NUMBER) {
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
