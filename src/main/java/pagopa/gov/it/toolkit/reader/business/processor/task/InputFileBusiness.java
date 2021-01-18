package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.reader.constants.InputOutputFileConstants;
import pagopa.gov.it.toolkit.reader.enumeration.ReaderInputIndexEnum;
import pagopa.gov.it.toolkit.rptGenerator.enumeration.TipoBolloEnum;
import pagopa.gov.it.toolkit.rptGenerator.xsd.StTipoIdentificativoUnivocoPersFG;

/**
 * Business logic class for input data
 */
public class InputFileBusiness {

    /**
     * Generates bean instance form csv input line
     * 
     * @param textInputLine
     *            input line in text format
     * @param domainLogo
     *            logo image in byte
     * @return CsvInputLine
     * @throws Exception
     * @see CsvInputLine
     */
    static CsvInputLine generateCsvInputLine(String textInputLine, byte[] domainLogo) throws Exception {
        if (!isValid(textInputLine)) {
            return null;
        }

        CsvInputLine csvInputLine = new CsvInputLine();
        String[] arrayInputLine = textInputLine.split(InputOutputFileConstants.CSV_SEPARATOR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(InputOutputFileConstants.CSV_INPUT_DATE_FORMAT);

        csvInputLine.setTextInputLine(textInputLine);

        csvInputLine
                .setDomainAuxDigit(
                        getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_AUX_DIGIT_INDEX.value()]) != null
                                ? Integer.parseInt(
                                        getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_AUX_DIGIT_INDEX.value()]))
                                : null);
        csvInputLine.setDomainSegregationCode(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_SEGREGATION_CODE_INDEX.value()]) != null
                        ? Integer.parseInt(
                                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_SEGREGATION_CODE_INDEX.value()]))
                        : null);
        csvInputLine.setDomainApplicationCode(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_APPLICATION_CODE_INDEX.value()]) != null
                        ? Integer.parseInt(
                                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_APPLICATION_CODE_INDEX.value()]))
                        : null);

        csvInputLine
                .setDomainFiscalCode(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_FISCAL_CODE_INDEX.value()]));
        csvInputLine.setDomainName(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_NAME_INDEX.value()]));
        csvInputLine.setDomainOperationalUnitCode(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_OPER_UNIT_CODE_INDEX.value()]));
        csvInputLine.setDomainOperationalUnitName(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_OPER_UNIT_NAME_INDEX.value()]));
        csvInputLine.setDomainAddress(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_ADDRESS_INDEX.value()]));
        csvInputLine.setDomainNumberStreet(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_NUMBER_STREET_INDEX.value()]));
        csvInputLine.setDomainLocality(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_LOCALITY_INDEX.value()]));
        csvInputLine.setDomainProvince(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_PROVINCE_INDEX.value()]));
        csvInputLine
                .setDomainPostalCode(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_POSTAL_CODE_INDEX.value()]));
        csvInputLine.setDomainNation(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_NATION_INDEX.value()]));
        csvInputLine.setDomainSector(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_SECTOR_INDEX.value()]));
        csvInputLine.setDomainCbillCode(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_CBILL_CODE_INDEX.value()]));
        csvInputLine.setDomainPostalAccountHolder(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_POSTAL_ACCOUNT_HOLDER_INDEX.value()]));
        csvInputLine.setDomainPostalAccountNumber(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_POSTAL_ACCOUNT_NUMBER_INDEX.value()]));
        csvInputLine.setDomainPostalAuthorizationCode(
                getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_POSTAL_AUTH_CODE_INDEX.value()]));
        csvInputLine.setDomainInfo(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_INFO_INDEX.value()]));
        csvInputLine.setDomainWebsite(getValue(arrayInputLine[ReaderInputIndexEnum.DOMAIN_WEBSITE_INDEX.value()]));
        csvInputLine.setDomainLogo(domainLogo);

        csvInputLine.setPayerUniqueIdentificationCode(
                getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_UNIQUE_IDENT_CODE_INDEX.value()]));
        csvInputLine.setPayerUniqueIdentificationType(transformStTipoIdentificativoUnivocoPersFG(
                getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_UNIQUE_IDENT_TYPE_INDEX.value()])));
        csvInputLine.setPayerRegistry(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_REGISTRY_INDEX.value()]));
        csvInputLine.setPayerAddress(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_ADDRESS_INDEX.value()]));
        csvInputLine
                .setPayerNumberStreet(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_NUMBER_STREET_INDEX.value()]));
        csvInputLine.setPayerLocality(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_LOCALITY_INDEX.value()]));
        csvInputLine.setPayerProvince(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_PROVINCE_INDEX.value()])));
        csvInputLine.setPayerPostalCode(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_POSTAL_CODE_INDEX.value()]));
        csvInputLine.setPayerNation(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_NATION_INDEX.value()])));
        csvInputLine.setPayerEmail(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_EMAIL_INDEX.value()]));
        csvInputLine.setPayerMobile(getValue(arrayInputLine[ReaderInputIndexEnum.PAYER_MOBILE_INDEX.value()]));

        csvInputLine.setTenantId(getValue(arrayInputLine[ReaderInputIndexEnum.TENANT_ID_INDEX.value()]));
        csvInputLine.setTotalAmountPayment(
                new BigDecimal(getValue(arrayInputLine[ReaderInputIndexEnum.TOTAL_AMOUNT_PAYMENT_INDEX.value()]))
                        .divide((BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP)));
        csvInputLine.setCausal(getValue(arrayInputLine[ReaderInputIndexEnum.CAUSAL_INDEX.value()]));
        Date expirationDate = dateFormat
                .parse(getValue(arrayInputLine[ReaderInputIndexEnum.EXPIRATION_DATE_INDEX.value()]));
        csvInputLine.setExpirationDate(expirationDate);
        csvInputLine.setSpecificCollectionData(
                getValue(arrayInputLine[ReaderInputIndexEnum.SPECIFIC_COLLECTION_DATA_INDEX.value()]));
        csvInputLine.setDocumentNumber(getValue(arrayInputLine[ReaderInputIndexEnum.DOCUMENT_NUMBER_INDEX.value()]));
        csvInputLine.setInstallmentNumber(
                getValue(arrayInputLine[ReaderInputIndexEnum.INSTALLMENT_NUMBER_INDEX.value()]) != null
                        ? Integer.parseInt(
                                getValue(arrayInputLine[ReaderInputIndexEnum.INSTALLMENT_NUMBER_INDEX.value()]))
                        : null);
        csvInputLine.setDebitIban(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.DEBIT_IBAN_INDEX.value()])));
        csvInputLine.setDebitBic(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.DEBIT_BIC_INDEX.value()])));
        csvInputLine.setCausalDescriptionSinglePayment(
                getValue(arrayInputLine[ReaderInputIndexEnum.CAUSAL_DESCRIPTION_INDEX.value()]));
        csvInputLine.setCreditIban(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.CREDIT_IBAN_INDEX.value()])));
        csvInputLine.setCreditBic(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.CREDIT_BIC_INDEX.value()])));
        csvInputLine.setSupportIban(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.SUPPORT_IBAN_INDEX.value()])));
        csvInputLine.setSupportBic(
                StringUtils.upperCase(getValue(arrayInputLine[ReaderInputIndexEnum.SUPPORT_BIC_INDEX.value()])));
        TipoBolloEnum tipoBollo = transformTipoBolloEnum(
                getValue(arrayInputLine[ReaderInputIndexEnum.TIPO_BOLLO_INDEX.value()]));
        if (tipoBollo != null) {
            csvInputLine.setTipoBollo(tipoBollo);
            csvInputLine.setDocumentHash(getValue(arrayInputLine[ReaderInputIndexEnum.DOCUMENT_HASH_INDEX.value()]));
            csvInputLine.setResidenceProvince(
                    getValue(arrayInputLine[ReaderInputIndexEnum.RESIDENCE_PROVINCE_INDEX.value()]));
        }
        Boolean isModello1 = InputOutputFileConstants.CSV_INPUT_MODELLO_1_ACCEPTED_VALUE.equalsIgnoreCase(
                getValue(arrayInputLine[ReaderInputIndexEnum.MODELLO_1_INDEX.value()])) ? Boolean.TRUE : Boolean.FALSE;
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
