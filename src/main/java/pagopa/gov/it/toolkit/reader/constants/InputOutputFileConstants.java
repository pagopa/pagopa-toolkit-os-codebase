package pagopa.gov.it.toolkit.reader.constants;

/**
 * All the constants related to csv input and csv output files
 */
public class InputOutputFileConstants {

    public static final String CSV_SEPARATOR = ";";
    public static final String CSV_VALUE_DELIMITER = "\"";
    public static final String CSV_NULL_VALUE = "\"\"";

    public static final String CSV_INPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String CSV_INPUT_MODELLO_1_ACCEPTED_VALUE = "SI";
    public static final String CSV_INPUT_MODELLO_1_REJECTED_VALUE = "NO";
    public static final int CSV_INPUT_FIELDS_TOTAL_NUMBER = 49;

    public static final String OUTPUT_FILE_NAME_DATE_FORMAT = "yyyyMMddhhmmss";
    public static final String OUTPUT_FILE_NAME_DATE_PLACEHOLDER = "<date>";
    public static final String OUTPUT_FILE_NAME_EXTENSION = "csv";
    public static final String OUTPUT_FILE_NAME = "Esito_" + OUTPUT_FILE_NAME_DATE_PLACEHOLDER + "."
            + OUTPUT_FILE_NAME_EXTENSION;
    
    //TODO to move in properties file for internationalization
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
