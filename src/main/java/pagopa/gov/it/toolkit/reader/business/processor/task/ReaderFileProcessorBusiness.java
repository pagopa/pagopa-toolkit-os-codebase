package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pagopa.gov.it.toolkit.debtPositionGenerator.bean.DebtPosition;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.PaymentNoticeGeneration;
import pagopa.gov.it.toolkit.paymentNoticeGenerator.bean.PNCreditorInstitution;
import pagopa.gov.it.toolkit.reader.bean.CsvInputLine;
import pagopa.gov.it.toolkit.reader.bean.CsvOutputLine;
import pagopa.gov.it.toolkit.reader.constants.ReaderInterfaceConstants;
import pagopa.gov.it.toolkit.reader.enumeration.ReaderStatus;
import pagopa.gov.it.toolkit.reader.exception.ReaderException;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;

public class ReaderFileProcessorBusiness {

    public static void checkInputData(String filePath, String outputFolder, String logoPath) throws ReaderException {
        if (filePath.isEmpty()) {
            throw new ReaderException(ReaderInterfaceConstants.CSV_FILE_PICKER_EMPTY_MSG);
        }
        if (!filePath.toLowerCase().endsWith(ReaderInterfaceConstants.RI_CSV_FILE_PICKER_ACCEPTED_EXT)) {
            throw new ReaderException(ReaderInterfaceConstants.CSV_FILE_PICKER_INVALID_FORMAT_MSG);
        }
        File debtPositionFile = new File(filePath);
        if (!debtPositionFile.exists()) {
            throw new ReaderException(ReaderInterfaceConstants.CSV_FILE_PICKER_NOT_FOUND_MSG);
        }

        if (outputFolder.isEmpty()) {
            throw new ReaderException(ReaderInterfaceConstants.OUTPUT_FOLDER_EMPTY_MSG);
        }
        Path outputFolderPath = Paths.get(outputFolder);
        if (!Files.exists(outputFolderPath)) {
            throw new ReaderException(ReaderInterfaceConstants.OUTPUT_FOLDER_NOT_FOUND_MSG);
        }

        if (logoPath.isEmpty()) {
            throw new ReaderException(ReaderInterfaceConstants.LOGO_PICKER_EMPTY_MSG);
        }
        if (!logoPath.toLowerCase().endsWith(ReaderInterfaceConstants.RI_LOGO_PICKER_ACCEPTED_EXT)) {
            throw new ReaderException(ReaderInterfaceConstants.LOGO_PICKER_INVALID_FORMAT_MSG);
        }
        File logoFile = new File(logoPath);
        if (!logoFile.exists()) {
            throw new ReaderException(ReaderInterfaceConstants.LOGO_PICKER_NOT_FOUND_MSG);
        }
    }

    public static ReaderStatus readFile(String outputFolder, List<CsvInputLine> debtPositionNoInstallmentList,
            Map<String, List<CsvInputLine>> debtPositionWithInstallmentMap, List<CsvOutputLine> csvOutputLineList,
            ReaderStatus readerStatus, File logoFile, BufferedReader bufferedReader, Date currentDate)
            throws IOException {
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            try {
                byte[] logo = Files.readAllBytes(logoFile.toPath());
                CsvInputLine csvInputLine = InputFileBusiness.generateCsvInputLine(line, logo);
                if (csvInputLine == null) {
                    readerStatus = ReaderStatus.WARNING;
                    csvOutputLineList.add(new CsvOutputLine(line, null, "", "",
                            ReaderInterfaceConstants.UPLOAD_RESULT_KO, ReaderInterfaceConstants.INVALID_LINE_MSG));
                    continue;
                }
                if (csvInputLine.getDocumentNumber() == null) {
                    debtPositionNoInstallmentList.add(csvInputLine);
                } else {
                    addCsvInputLineToMap(debtPositionWithInstallmentMap, csvInputLine);
                }
            } catch (Exception exception) {
                readerStatus = ReaderStatus.WARNING;
                csvOutputLineList.add(new CsvOutputLine(line, null, "", "", ReaderInterfaceConstants.UPLOAD_RESULT_KO,
                        exception.getMessage()));
            }
        }
        return readerStatus;
    }

    public static ReaderStatus noInstallmentManagement(String outputFolder,
            List<CsvInputLine> debtPositionNoInstallmentList, List<CsvOutputLine> csvOutputLineList,
            ReaderStatus readerStatus, Date currentDate) {
        DebtPosition debtPosition = null;
        for (CsvInputLine csvInputLine : debtPositionNoInstallmentList) {
            try {
                debtPosition = DataGenerationBusiness.generateDebtPosition(csvInputLine);
                RptContainer rptContainer = DataGenerationBusiness.generateRptContainer(csvInputLine, debtPosition);
                FileCreationBusiness.createRptXmlFile(rptContainer, outputFolder, currentDate);
                PNCreditorInstitution creditorInstitution = DataGenerationBusiness
                        .generateCreditorInstitution(csvInputLine);
                List<DebtPosition> debtPositionList = new ArrayList<DebtPosition>();
                debtPositionList.add(debtPosition);
                byte[] pdfPaymentNotice = PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution,
                        csvInputLine.getIsModello1Or2());
                FileCreationBusiness.createNoticePaymentPdfFile(pdfPaymentNotice,
                        debtPosition.getPaymentDetail().getNoticeNumber(), outputFolder, currentDate);
                csvOutputLineList.add(new CsvOutputLine(csvInputLine.getTextInputLine(), csvInputLine,
                        debtPosition.getPaymentDetail().getIuv(), debtPosition.getPaymentDetail().getNoticeNumber(),
                        ReaderInterfaceConstants.UPLOAD_RESULT_OK, ""));
            } catch (Exception exception) {
                readerStatus = ReaderStatus.WARNING;
                String iuv = debtPosition != null ? debtPosition.getPaymentDetail().getIuv() : "";
                String noticeNumber = debtPosition != null ? debtPosition.getPaymentDetail().getNoticeNumber() : "";
                csvOutputLineList.add(new CsvOutputLine(csvInputLine.getTextInputLine(), csvInputLine, iuv,
                        noticeNumber, ReaderInterfaceConstants.UPLOAD_RESULT_KO, exception.getMessage()));
            }
        }
        return readerStatus;
    }

    public static ReaderStatus installmentManagement(String outputFolder,
            Map<String, List<CsvInputLine>> debtPositionWithInstallmentMap, List<CsvOutputLine> csvOutputLineList,
            ReaderStatus readerStatus, Date currentDate) {
        for (Map.Entry<String, List<CsvInputLine>> entry : debtPositionWithInstallmentMap.entrySet()) {
            List<CsvInputLine> csvInputLineList = entry.getValue();
            List<DebtPosition> debtPositionList = new ArrayList<DebtPosition>();
            for (CsvInputLine csvInputLine : csvInputLineList) {
                DebtPosition debtPosition = null;
                try {
                    debtPosition = DataGenerationBusiness.generateDebtPosition(csvInputLine);
                    csvInputLine.setIuv(debtPosition.getPaymentDetail().getIuv());
                    csvInputLine.setNoticeNumber(debtPosition.getPaymentDetail().getNoticeNumber());
                    RptContainer rptContainer = DataGenerationBusiness.generateRptContainer(csvInputLine, debtPosition);
                    FileCreationBusiness.createRptXmlFile(rptContainer, outputFolder, currentDate);
                    debtPositionList.add(debtPosition);
                } catch (Exception exception) {
                    readerStatus = ReaderStatus.WARNING;
                    String iuv = debtPosition != null ? debtPosition.getPaymentDetail().getIuv() : "";
                    String noticeNumber = debtPosition != null ? debtPosition.getPaymentDetail().getNoticeNumber() : "";
                    csvOutputLineList.add(new CsvOutputLine(csvInputLine.getTextInputLine(), csvInputLine, iuv,
                            noticeNumber, ReaderInterfaceConstants.UPLOAD_RESULT_KO, exception.getMessage()));
                }
            }
            try {
                PNCreditorInstitution creditorInstitution = DataGenerationBusiness
                        .generateCreditorInstitution(csvInputLineList.get(0));
                Boolean isModello1or2 = csvInputLineList.get(0).getIsModello1Or2();
                byte[] pdfPaymentNotice = PaymentNoticeGeneration.generate(debtPositionList, creditorInstitution,
                        isModello1or2);
                String documentNumber = entry.getKey();
                FileCreationBusiness.createNoticePaymentPdfFile(pdfPaymentNotice, documentNumber, outputFolder,
                        currentDate);
                for (CsvInputLine csvInputLine : csvInputLineList) {
                    csvOutputLineList
                            .add(new CsvOutputLine(csvInputLine.getTextInputLine(), csvInputLine, csvInputLine.getIuv(),
                                    csvInputLine.getNoticeNumber(), ReaderInterfaceConstants.UPLOAD_RESULT_OK, ""));
                }
            } catch (Exception exception) {
                readerStatus = ReaderStatus.WARNING;
                for (CsvInputLine csvInputLine : csvInputLineList) {
                    csvOutputLineList.add(new CsvOutputLine(csvInputLine.getTextInputLine(), csvInputLine,
                            csvInputLine.getIuv(), csvInputLine.getNoticeNumber(),
                            ReaderInterfaceConstants.UPLOAD_RESULT_KO, exception.getMessage()));
                }
            }
        }
        return readerStatus;
    }

    public static void createOutputFile(String outputFolder, List<CsvOutputLine> csvOutputLineList, Date currentDate)
            throws IOException {
        String outputFilePath = OutputFileBusiness.initializeOutputFile(outputFolder, currentDate);
        OutputFileBusiness.updateOutputFile(outputFilePath, csvOutputLineList);
    }

    private static void addCsvInputLineToMap(Map<String, List<CsvInputLine>> debtPositionWithInstallmentMap,
            CsvInputLine csvInputLine) {
        String documentNumber = csvInputLine.getDocumentNumber();
        List<CsvInputLine> csvInputLineList = debtPositionWithInstallmentMap.get(documentNumber);
        if (csvInputLineList == null) {
            csvInputLineList = new ArrayList<CsvInputLine>();
        }
        csvInputLineList.add(csvInputLine);
        debtPositionWithInstallmentMap.put(documentNumber, csvInputLineList);
    }
}
