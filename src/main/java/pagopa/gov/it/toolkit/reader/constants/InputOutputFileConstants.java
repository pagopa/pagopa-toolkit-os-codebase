package pagopa.gov.it.toolkit.reader.constants;

public class InputOutputFileConstants {

    public static final String CSV_SEPARATOR = ";";
    public static final String CSV_VALUE_DELIMITER = "\"";
    public static final String CSV_NULL_VALUE = "\"\"";

    public static final String CSV_INPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String CSV_INPUT_MODELLO_1_ACCEPTED_VALUE = "SI";
    public static final String CSV_INPUT_MODELLO_1_REJECTED_VALUE = "NO";
    public static final int CSV_INPUT_FIELDS_TOTAL_NUMBER = 49;
    public static final int CSV_INPUT_DOMAIN_AUX_DIGIT_INDEX = 0;
    public static final int CSV_INPUT_DOMAIN_SEGREGATION_CODE_INDEX = 1;
    public static final int CSV_INPUT_DOMAIN_APPLICATION_CODE_INDEX = 2;
    public static final int CSV_INPUT_DOMAIN_FISCAL_CODE_INDEX = 3;
    public static final int CSV_INPUT_DOMAIN_NAME_INDEX = 4;
    public static final int CSV_INPUT_DOMAIN_OPER_UNIT_CODE_INDEX = 5;
    public static final int CSV_INPUT_DOMAIN_OPER_UNIT_NAME_INDEX = 6;
    public static final int CSV_INPUT_DOMAIN_ADDRESS_INDEX = 7;
    public static final int CSV_INPUT_DOMAIN_NUMBER_STREET_INDEX = 8;
    public static final int CSV_INPUT_DOMAIN_LOCALITY_INDEX = 9;
    public static final int CSV_INPUT_DOMAIN_PROVINCE_INDEX = 10;
    public static final int CSV_INPUT_DOMAIN_POSTAL_CODE_INDEX = 11;
    public static final int CSV_INPUT_DOMAIN_NATION_INDEX = 12;
    public static final int CSV_INPUT_DOMAIN_SECTOR_INDEX = 13;
    public static final int CSV_INPUT_DOMAIN_CBILL_CODE_INDEX = 14;
    public static final int CSV_INPUT_DOMAIN_POSTAL_ACCOUNT_HOLDER_INDEX = 15;
    public static final int CSV_INPUT_DOMAIN_POSTAL_ACCOUNT_NUMBER_INDEX = 16;
    public static final int CSV_INPUT_DOMAIN_POSTAL_AUTH_CODE_INDEX = 17;
    public static final int CSV_INPUT_DOMAIN_INFO_INDEX = 18;
    public static final int CSV_INPUT_DOMAIN_WEBSITE_INDEX = 19;
    public static final int CSV_INPUT_PAYER_UNIQUE_IDENT_CODE_INDEX = 20;
    public static final int CSV_INPUT_PAYER_UNIQUE_IDENT_TYPE_INDEX = 21;
    public static final int CSV_INPUT_PAYER_REGISTRY_INDEX = 22;
    public static final int CSV_INPUT_PAYER_ADDRESS_INDEX = 23;
    public static final int CSV_INPUT_PAYER_NUMBER_STREET_INDEX = 24;
    public static final int CSV_INPUT_PAYER_LOCALITY_INDEX = 25;
    public static final int CSV_INPUT_PAYER_PROVINCE_INDEX = 26;
    public static final int CSV_INPUT_PAYER_POSTAL_CODE_INDEX = 27;
    public static final int CSV_INPUT_PAYER_NATION_INDEX = 28;
    public static final int CSV_INPUT_PAYER_EMAIL_INDEX = 29;
    public static final int CSV_INPUT_PAYER_MOBILE_INDEX = 30;
    public static final int CSV_INPUT_TENANT_ID_INDEX = 31;
    public static final int CSV_INPUT_TOTAL_AMOUNT_PAYMENT_INDEX = 32;
    public static final int CSV_INPUT_CAUSAL_INDEX = 33;
    public static final int CSV_INPUT_EXPIRATION_DATE_INDEX = 34;
    public static final int CSV_INPUT_SPECIFIC_COLLECTION_DATA_INDEX = 35;
    public static final int CSV_INPUT_DOCUMENT_NUMBER_INDEX = 36;
    public static final int CSV_INPUT_INSTALLMENT_NUMBER_INDEX = 37;
    public static final int CSV_INPUT_DEBIT_IBAN_INDEX = 38;
    public static final int CSV_INPUT_DEBIT_BIC_INDEX = 39;
    public static final int CSV_INPUT_CAUSAL_DESCRIPTION_INDEX = 40;
    public static final int CSV_INPUT_CREDIT_IBAN_INDEX = 41;
    public static final int CSV_INPUT_CREDIT_BIC_INDEX = 42;
    public static final int CSV_INPUT_SUPPORT_IBAN_INDEX = 43;
    public static final int CSV_INPUT_SUPPORT_BIC_INDEX = 44;
    public static final int CSV_INPUT_TIPO_BOLLO_INDEX = 45;
    public static final int CSV_INPUT_DOCUMENT_HASH_INDEX = 46;
    public static final int CSV_INPUT_RESIDENCE_PROVINCE_INDEX = 47;
    public static final int CSV_INPUT_MODELLO_1_INDEX = 48;

    public static final String OUTPUT_FILE_NAME_DATE_FORMAT = "yyyyMMddhhmmss";
    public static final String OUTPUT_FILE_NAME_DATE_PLACEHOLDER = "<date>";
    public static final String OUTPUT_FILE_NAME_EXTENSION = "csv";
    public static final String OUTPUT_FILE_NAME = "Esito_" + OUTPUT_FILE_NAME_DATE_PLACEHOLDER + "."
            + OUTPUT_FILE_NAME_EXTENSION;
    public static final String OUTPUT_FILE_HEADER = "\"Aux Digit\";\"Segregation Code\";\"Application Code\";\"EC CodiceFiscale/P.IVA\";"
            + "\"EC Denominazione\";\"EC Codice Unita Operativa\";\"EC Denominazione Unita Operativa\";\"EC Indirizzo\";\"EC Civico\";"
            + "\"EC Localita\";\"EC Provincia\";\"EC CAP\";\"EC Nazione\";\"EC Settore\";\"EC Codice CBILL\";"
            + "\"EC Proprietario cc postale\";\"EC Numero cc postale\";\"EC Codice Autorizzazione postale\";"
            + "\"EC Informazioni Aggiuntive\";\"EC Sito Web\";\"SP CodiceFiscale/P.IVA\";\"SP Tipo\";\"SP Anagrafica\";\"SP Indirizzo\";"
            + "\"SP Civico\";\"SP Localita\";\"SP Provincia\";\"SP CAP\";\"SP Nazione\";\"SP Email\";\"SP Telefono\";\"PD ID Tenant\";"
            + "\"PD Importo\";\"PD Causale\";\"PD Data Scadenza (" + InputOutputFileConstants.CSV_INPUT_DATE_FORMAT
            + ")\";\"PD Dati Specifici Riscossione\";\"PD Numero Documento\";\"PD Numero Rata\";\"PD IBAN Addebito\";"
            + "\"PD BIC Addebito\";\"PD Descrizione Causale RPT\";\"PD IBAN Accredito\";\"PD BIC Accredito\";\"PD IBAN Supporto\";"
            + "\"PD BIC Supporto\";\"PD Tipo Bollo\";\"PD Hash Documento Bollo\";\"PD Provincia Residenza Bollo\";\"PD Modello 1\"";
}
