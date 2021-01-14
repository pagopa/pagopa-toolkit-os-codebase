package pagopa.gov.it.toolkit.reader.constants;

public class InputOutputFileConstants {

    public static final String CSV_SEPARATOR = ";";
    public static final String CSV_VALUE_DELIMITER = "\"";
    public static final String CSV_NULL_VALUE = "\"\"";

    public static final String CSV_INPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String CSV_INPUT_MODELLO_1_ACCEPTED_VALUE = "SI";
    public static final String CSV_INPUT_MODELLO_1_REJECTED_VALUE = "NO";
    public static final int CSV_INPUT_FIELDS_NUMBER = 52;

    public static final String OUTPUT_FILE_NAME_DATE_FORMAT = "yyyyMMddhhmmss";
    public static final String OUTPUT_FILE_NAME_DATE_PLACEHOLDER = "<date>";
    public static final String OUTPUT_FILE_NAME_EXTENSION = "csv";
    public static final String OUTPUT_FILE_NAME = "Esito_" + OUTPUT_FILE_NAME_DATE_PLACEHOLDER + "."
            + OUTPUT_FILE_NAME_EXTENSION;
    public static final String OUTPUT_FILE_HEADER = "\"CodiceFiscale/P.IVA\";\"Tipo\";\"Anagrafica Pagatore\";\"Indirizzo\";\"Civico\";\"Localita\";\"Provincia\";\"Nazione\";"
            + "\"CAP\";\"Email\";\"Mobile\";\"Identificativo Ente Beneficiario\";\"CodiceFiscale/P.IVA Ente Beneficiario\";\"Denominazione Ente Beneficiario\";"
            + "\"Codice Unita Operativa\";\"Denominazione Unita Operativa\";\"Indirizzo Ente Beneficiario\";\"Civico Ente Beneficiario\";\"CAP Ente Beneficiario\";"
            + "\"Localita Ente Beneficiario\";\"Provincia Ente Beneficiario\";\"Nazione Ente Beneficiario\";\"Settore\";\"Codice CBILL\";\"Proprietario Account Postale\";"
            + "\"Numero Account Postale\";\"Codice Autorizzazione Postale\";\"Commissione Carico\";\"Aux Digit\";\"Segregation Code\";\"Application Code\";"
            + "\"Informazioni Aggiuntive\";\"Sito Web\";\"ID Tenant\";\"Importo Versamento\";\"Causale Avviso\";\"Data Scadenza Pagamento ("
            + InputOutputFileConstants.CSV_INPUT_DATE_FORMAT + ")\";"
            + "\"Dati Specifici Riscossione\";\"Numero Documento\";\"Numero Rata\";\"IBAN Addebito\";\"BIC Addebito\";\"Ordine Versamento\";\"Descrizione Causale RPT\";"
            + "\"IBAN Accredito\";\"BIC Accredito\";\"IBAN Supporto\";\"BIC Supporto\";\"Tipo Bollo\";\"Hash Documento\";\"Provincia Residenza\";\"Modello 1\";\"IUV\";"
            + "\"Numero Avviso\";\"Esito Operazione\";\"Descrizione Esito\"";
}
