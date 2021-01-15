package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pagopa.gov.it.toolkit.reader.bean.CsvOutputLine;
import pagopa.gov.it.toolkit.reader.constants.InputOutputFileConstants;

public class OutputFileBusiness {

    static String initializeOutputFile(String outputFolder, Date currentDate) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(InputOutputFileConstants.OUTPUT_FILE_NAME_DATE_FORMAT);
        String outputFilePath = outputFolder + File.separator + InputOutputFileConstants.OUTPUT_FILE_NAME
                .replace(InputOutputFileConstants.OUTPUT_FILE_NAME_DATE_PLACEHOLDER, dateFormat.format(currentDate));
        FileOutputStream outputStream = new FileOutputStream(outputFilePath);
        outputStream.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath, true));
        bufferedWriter.append(InputOutputFileConstants.OUTPUT_FILE_HEADER);
        bufferedWriter.append("\n");
        bufferedWriter.close();

        return outputFilePath;
    }

    static void updateOutputFile(String outputFilePath, List<CsvOutputLine> csvOutputLineList) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath, true));
        for (CsvOutputLine csvOutputLine : csvOutputLineList) {
            String outputLine = csvOutputLine.getTextInputLine() + InputOutputFileConstants.CSV_SEPARATOR
                    + InputOutputFileConstants.CSV_VALUE_DELIMITER + csvOutputLine.getIuv()
                    + InputOutputFileConstants.CSV_VALUE_DELIMITER + InputOutputFileConstants.CSV_SEPARATOR
                    + InputOutputFileConstants.CSV_VALUE_DELIMITER + csvOutputLine.getNoticeNumber()
                    + InputOutputFileConstants.CSV_VALUE_DELIMITER;
            outputLine = outputLine.replace("null", "");
            bufferedWriter.append(
                    outputLine + InputOutputFileConstants.CSV_SEPARATOR + InputOutputFileConstants.CSV_VALUE_DELIMITER
                            + csvOutputLine.getResult() + InputOutputFileConstants.CSV_VALUE_DELIMITER
                            + InputOutputFileConstants.CSV_SEPARATOR + InputOutputFileConstants.CSV_VALUE_DELIMITER
                            + csvOutputLine.getResultDescription() + InputOutputFileConstants.CSV_VALUE_DELIMITER);
            outputLine = "";
            bufferedWriter.append("\n");
        }
        bufferedWriter.close();
    }
}
