package pagopa.gov.it.toolkit.reader.business.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pagopa.gov.it.toolkit.reader.constants.ReaderInterfaceConstants;

/**
 * Managing the download of the csv template file and the glossary
 */
public class ReaderTemplateFileHandler {

    /**
     * Download the csv template file and the glossary in user local folder
     * selected via GUI
     * 
     * @param outputFolder
     *            path of output folder selected by user via GUI where to
     *            download the files
     * @throws Exception
     */
    public void moveFilesToFolder(String outputFolder) throws Exception {
        saveFile(outputFolder, ReaderInterfaceConstants.TEMPLATE_APA_CSV_FILE_PATH,
                ReaderInterfaceConstants.TEMPLATE_APA_CSV_FILE_NAME);
        saveFile(outputFolder, ReaderInterfaceConstants.GLOSSARY_APA_CSV_FILE_PATH,
                ReaderInterfaceConstants.GLOSSARY_APA_CSV_FILE_NAME);
    }

    private void saveFile(String outputFolder, String filePath, String fileName)
            throws IOException, FileNotFoundException {
        InputStream inputStream = getClass().getResourceAsStream(filePath + fileName);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        File targetFile = new File(outputFolder + File.separator + fileName);
        OutputStream outputStream = new FileOutputStream(targetFile);
        outputStream.write(buffer);
        outputStream.close();
    }
}
