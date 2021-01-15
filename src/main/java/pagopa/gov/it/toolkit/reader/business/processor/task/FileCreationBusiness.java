package pagopa.gov.it.toolkit.reader.business.processor.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import pagopa.gov.it.toolkit.reader.constants.ReaderInterfaceConstants;
import pagopa.gov.it.toolkit.rptGenerator.bean.RptContainer;

/**
 * Business logic class for files creation
 */
public class FileCreationBusiness {

    /**
     * Generates RPT files in XML format
     * 
     * @param rptContainer
     * @param outputFolder
     *            destination folder of the files to be saved
     * @param currentDate
     *            date of the operation
     * @throws FileNotFoundException
     * @throws IOException
     */
    static void createRptXmlFile(RptContainer rptContainer, String outputFolder, Date currentDate)
            throws FileNotFoundException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ReaderInterfaceConstants.FILE_NAME_DATE_FORMAT);
        String iuv = rptContainer.getRpt().getDatiVersamento().getIdentificativoUnivocoVersamento();
        String filePath = outputFolder + File.separator + ReaderInterfaceConstants.RPT_FILE_NAME
                .replace(ReaderInterfaceConstants.RPT_FILE_NAME_IUV_PLACEHOLDER, iuv)
                .replace(ReaderInterfaceConstants.RPT_FILE_NAME_DATE_PLACEHOLDER, dateFormat.format(currentDate));
        createFile(rptContainer.getRptXml(), filePath);
    }

    /**
     * Generates PaymentNotice files in PDF format
     * 
     * @param pdfPaymentNotice
     *            file content in bytes
     * @param paymentData
     *            noticeNumber or documentNumber
     * @param outputFolder
     *            destination folder of the files to be saved
     * @param currentDate
     *            date of the operation
     * @throws FileNotFoundException
     * @throws IOException
     */
    static void createNoticePaymentPdfFile(byte[] pdfPaymentNotice, String paymentData, String outputFolder,
            Date currentDate) throws FileNotFoundException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ReaderInterfaceConstants.FILE_NAME_DATE_FORMAT);
        String filePath = outputFolder + File.separator + ReaderInterfaceConstants.NOTICE_FILE_NAME
                .replace(ReaderInterfaceConstants.NOTICE_FILE_NAME_PAYMENT_DATA_PLACEHOLDER, paymentData)
                .replace(ReaderInterfaceConstants.NOTICE_FILE_NAME_DATE_PLACEHOLDER, dateFormat.format(currentDate));
        createFile(pdfPaymentNotice, filePath);
    }

    private static void createFile(byte[] byteArray, String filePath) throws FileNotFoundException, IOException {
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(byteArray);
        outputStream.close();
    }
}
