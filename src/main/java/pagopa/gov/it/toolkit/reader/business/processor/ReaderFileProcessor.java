package pagopa.gov.it.toolkit.reader.business.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.reader.bean.CsvOutputLine;
import pagopa.gov.it.toolkit.reader.business.processor.task.ReaderFileProcessorBusiness;
import pagopa.gov.it.toolkit.reader.enumeration.ReaderStatusEnum;
import pagopa.gov.it.toolkit.reader.exception.ReaderException;

/**
 * Main class for input file processing operations
 */
public class ReaderFileProcessor {

    /**
     * Processing of the input data from GUI
     * 
     * @param filePath
     *            path of input file picker selected by user via GUI
     * @param outputFolder
     *            path of output folder selected by user via GUI
     * @param logoPath
     *            path of logo selected by user via GUI
     * @return ReaderStatus
     * @throws ReaderException
     * @throws IOException
     * @see ReaderStatusEnum
     */
    public ReaderStatusEnum processCsvFile(String filePath, String outputFolder, String logoPath)
            throws ReaderException, IOException {
        List<CsvInputLine> debtPositionNoInstallmentList = new ArrayList<CsvInputLine>();
        Map<String, List<CsvInputLine>> debtPositionWithInstallmentMap = new HashMap<String, List<CsvInputLine>>();
        List<CsvOutputLine> csvOutputLineList = new ArrayList<CsvOutputLine>();
        ReaderStatusEnum readerStatus = ReaderStatusEnum.OK;

        ReaderFileProcessorBusiness.checkInputData(filePath, outputFolder, logoPath);

        Date currentDate = new Date();
        File logoFile = new File(logoPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        bufferedReader.readLine();
        readerStatus = ReaderFileProcessorBusiness.readFile(outputFolder, debtPositionNoInstallmentList,
                debtPositionWithInstallmentMap, csvOutputLineList, readerStatus, logoFile, bufferedReader, currentDate);
        bufferedReader.close();

        readerStatus = ReaderFileProcessorBusiness.noInstallmentManagement(outputFolder, debtPositionNoInstallmentList,
                csvOutputLineList, readerStatus, currentDate);

        readerStatus = ReaderFileProcessorBusiness.installmentManagement(outputFolder, debtPositionWithInstallmentMap,
                csvOutputLineList, readerStatus, currentDate);

        ReaderFileProcessorBusiness.createOutputFile(outputFolder, csvOutputLineList, currentDate);

        return readerStatus;
    }
}
