package pagopa.gov.it.toolkit.reader.constants;

/**
 * All the constants related to Reader Interface
 */
public class ReaderInterfaceConstants {

    public static final String RI_FRAME_TITLE = "Caricamento posizioni debitorie da file CSV";
    public static final int RI_FRAME_WIDTH = 705;
    public static final int RI_FRAME_HEIGHT = 400;

    public static final String RI_CSV_FILE_PICKER_LABEL = "File posizioni debitorie (.csv):";
    public static final int RI_CSV_FILE_PICKER_LABEL_X_COORDINATE = 10;
    public static final int RI_CSV_FILE_PICKER_LABEL_Y_COORDINATE = 10;
    public static final int RI_CSV_FILE_PICKER_LABEL_WIDTH = 350;
    public static final int RI_CSV_FILE_PICKER_LABEL_HEIGHT = 50;
    public static final int RI_CSV_FILE_PICKER_TEXT_X_COORDINATE = 10;
    public static final int RI_CSV_FILE_PICKER_TEXT_Y_COORDINATE = 50;
    public static final int RI_CSV_FILE_PICKER_TEXT_WIDTH = 550;
    public static final int RI_CSV_FILE_PICKER_TEXT_HEIGHT = 30;
    public static final String RI_CSV_FILE_PICKER_BUTTON = "Scegli file";
    public static final int RI_CSV_FILE_PICKER_BUTTON_X_COORDINATE = 570;
    public static final int RI_CSV_FILE_PICKER_BUTTON_Y_COORDINATE = 50;
    public static final int RI_CSV_FILE_PICKER_BUTTON_WIDTH = 115;
    public static final int RI_CSV_FILE_PICKER_BUTTON_HEIGHT = 30;
    public static final String RI_CSV_FILE_PICKER_BUTTON_DESCRIPTION = "File CSV (*.csv)";
    public static final String RI_CSV_FILE_PICKER_ACCEPTED_EXT = ".csv";

    public static final String RI_OUTPUT_FOLDER_LABEL = "Cartella di destinazione:";
    public static final int RI_OUTPUT_FOLDER_LABEL_X_COORDINATE = 10;
    public static final int RI_OUTPUT_FOLDER_LABEL_Y_COORDINATE = 100;
    public static final int RI_OUTPUT_FOLDER_LABEL_WIDTH = 350;
    public static final int RI_OUTPUT_FOLDER_LABEL_HEIGHT = 50;
    public static final int RI_OUTPUT_FOLDER_TEXT_X_COORDINATE = 10;
    public static final int RI_OUTPUT_FOLDER_TEXT_Y_COORDINATE = 140;
    public static final int RI_OUTPUT_FOLDER_TEXT_WIDTH = 550;
    public static final int RI_OUTPUT_FOLDER_TEXT_HEIGHT = 30;
    public static final String RI_OUTPUT_FOLDER_BUTTON = "Scegli cartella";
    public static final int RI_OUTPUT_FOLDER_BUTTON_X_COORDINATE = 570;
    public static final int RI_OUTPUT_FOLDER_BUTTON_Y_COORDINATE = 140;
    public static final int RI_OUTPUT_FOLDER_BUTTON_WIDTH = 115;
    public static final int RI_OUTPUT_FOLDER_BUTTON_HEIGHT = 30;

    public static final String RI_LOGO_PICKER_LABEL = "File logo ente (.png, dimensione consigliata: 30x30mm):";
    public static final int RI_LOGO_PICKER_LABEL_X_COORDINATE = 10;
    public static final int RI_LOGO_PICKER_LABEL_Y_COORDINATE = 190;
    public static final int RI_LOGO_PICKER_LABEL_WIDTH = 350;
    public static final int RI_LOGO_PICKER_LABEL_HEIGHT = 50;
    public static final int RI_LOGO_PICKER_TEXT_X_COORDINATE = 10;
    public static final int RI_LOGO_PICKER_TEXT_Y_COORDINATE = 230;
    public static final int RI_LOGO_PICKER_TEXT_WIDTH = 550;
    public static final int RI_LOGO_PICKER_TEXT_HEIGHT = 30;
    public static final String RI_LOGO_PICKER_BUTTON = "Scegli file";
    public static final int RI_LOGO_PICKER_BUTTON_X_COORDINATE = 570;
    public static final int RI_LOGO_PICKER_BUTTON_Y_COORDINATE = 230;
    public static final int RI_LOGO_PICKER_BUTTON_WIDTH = 115;
    public static final int RI_LOGO_PICKER_BUTTON_HEIGHT = 30;
    public static final String RI_LOGO_PICKER_BUTTON_DESCRIPTION = "File PNG (*.png)";
    public static final String RI_LOGO_PICKER_ACCEPTED_EXT = ".png";

    public static final String RI_TEMPLATE_DOWNLOADER_BUTTON = "<html>Scarica<br/>template</html>";
    public static final int RI_TEMPLATE_DOWNLOADER_BUTTON_X_COORDINATE = 245;
    public static final int RI_TEMPLATE_DOWNLOADER_BUTTON_Y_COORDINATE = 300;
    public static final int RI_TEMPLATE_DOWNLOADER_BUTTON_WIDTH = 100;
    public static final int RI_TEMPLATE_DOWNLOADER_BUTTON_HEIGHT = 50;
    public static final String RI_TEMPLATE_DOWNLOADER_SUCCESS_MSG = "Il template è stato stato scaricato e posizionato con successo nella seguente cartella:\n";
    public static final String RI_TEMPLATE_DOWNLOADER_ERROR_MSG = "Si è verificato un errore durante il download del template";
    public static final String TEMPLATE_APA_CSV_FILE_PATH = "/resources/templates/reader/";
    public static final String TEMPLATE_APA_CSV_FILE_NAME = "templateAPA.csv";
    public static final String GLOSSARY_APA_CSV_FILE_PATH = "/resources/templates/reader/";
    public static final String GLOSSARY_APA_CSV_FILE_NAME = "glossarioAPA.xls";

    public static final String RI_CONFIRM_BUTTON = "Conferma";
    public static final int RI_CONFIRM_BUTTON_X_COORDINATE = 355;
    public static final int RI_CONFIRM_BUTTON_Y_COORDINATE = 300;
    public static final int RI_CONFIRM_BUTTON_WIDTH = 100;
    public static final int RI_CONFIRM_BUTTON_HEIGHT = 50;
    public static final String RI_CONFIRM_SUCCESS_MSG = "Il caricamento è stato completato con successo";
    public static final String RI_CONFIRM_WARNING_MSG = "Il caricamento è stato completato con successo, ma alcune posizioni non sono state caricate.\nConsultare l'esito per ulteriori informazioni";
    public static final String RI_CONFIRM_ERROR_MSG = "Il caricamento è terminato in errore. Ripetere nuovamente l'operazione";

    public static final String MESSAGE_TITLE = "Esito operazione";

    public static final String CSV_FILE_PICKER_EMPTY_MSG = "Selezionare un file CSV per procedere";
    public static final String CSV_FILE_PICKER_INVALID_FORMAT_MSG = "Il file delle posizioni debitorie ha un formato non corretto. Selezionare un file CSV";
    public static final String CSV_FILE_PICKER_NOT_FOUND_MSG = "File delle posizioni debitorie non trovato";
    public static final String OUTPUT_FOLDER_EMPTY_MSG = "Selezionare una cartella di destinazione per procedere";
    public static final String OUTPUT_FOLDER_NOT_FOUND_MSG = "Cartella di destinazione non trovata";
    public static final String LOGO_PICKER_EMPTY_MSG = "Selezionare un logo in formato PNG per procedere";
    public static final String LOGO_PICKER_INVALID_FORMAT_MSG = "Il logo ha un formato non corretto. Selezionare un file PNG";
    public static final String LOGO_PICKER_NOT_FOUND_MSG = "Logo non trovato";
    public static final String INVALID_LINE_MSG = "Formattazione riga non corretta";

    public static final String UPLOAD_RESULT_OK = "OK";
    public static final String UPLOAD_RESULT_KO = "KO";

    public static final String FILE_NAME_DATE_FORMAT = "yyyyMMddhhmmss";
    public static final String RPT_FILE_NAME_IUV_PLACEHOLDER = "<iuv>";
    public static final String RPT_FILE_NAME_DATE_PLACEHOLDER = "<date>";
    public static final String RPT_FILE_NAME_EXTENSION = "xml";
    public static final String RPT_FILE_NAME = "RPT_" + RPT_FILE_NAME_IUV_PLACEHOLDER + "_"
            + RPT_FILE_NAME_DATE_PLACEHOLDER + "." + RPT_FILE_NAME_EXTENSION;
    public static final String NOTICE_FILE_NAME_PAYMENT_DATA_PLACEHOLDER = "<paymentData>";
    public static final String NOTICE_FILE_NAME_DATE_PLACEHOLDER = "<date>";
    public static final String NOTICE_FILE_NAME_EXTENSION = "pdf";
    public static final String NOTICE_FILE_NAME = "Avviso_" + NOTICE_FILE_NAME_PAYMENT_DATA_PLACEHOLDER + "_"
            + NOTICE_FILE_NAME_DATE_PLACEHOLDER + "." + NOTICE_FILE_NAME_EXTENSION;

}
