package pagopa.gov.it.toolkit.reader.business.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pagopa.gov.it.toolkit.reader.constants.ReaderInterfaceConstants;

public class ReaderTemplateFileHandler {

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
