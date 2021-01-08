package pagopa.gov.it.toolkit.reader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ReaderTemplateCsvFileHandler {
	private static final String TEMPLATE_APA_CSV_FILE_PATH = "/templates/reader/templateApaCsv.csv";
	
	public void moveFileToFolder(String outputFolder) throws Exception {
		InputStream inputStream = getClass().getResourceAsStream(TEMPLATE_APA_CSV_FILE_PATH);
		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		
		File targetFile = new File(outputFolder + File.separator + getFileName(TEMPLATE_APA_CSV_FILE_PATH));
		OutputStream outputStream = new FileOutputStream(targetFile);
		outputStream.write(buffer);
		outputStream.close();
	}
	
	private String getFileName(String filePath) {
		return filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
	}
}
