package it.pagoPA.toolkit.paymentNoticeGenerator.business.printer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.PaymentNoticeBusiness;
import it.pagoPA.toolkit.paymentNoticeGenerator.constants.PaymentNoticeConstants;

public class BankingSectionInstallmentsBusiness {

    /**
     * 
     * @param sortedDebtPositionHashMap
     * @param installmentNumber
     * @return
     * @throws Exception
     */
    public static Cell createExpirationDateCell(HashMap<Integer, DebtPosition> sortedDebtPositionHashMap,
            int installmentNumber) throws Exception {
        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setTextAlignment(TextAlignment.LEFT)
                .setFixedLeading(10);
        Text text1 = new Text(installmentNumber + PaymentNoticeConstants.PDF_TEXT_BANK_INST_INSTALLMENT_NUMBER);
        text1.setFont(PdfPaymentNoticeManagement.getTrilliumWebBlack()).setFontSize(9);
        paragraph.add(text1);
        Date expirationDate = sortedDebtPositionHashMap.get(installmentNumber).getPaymentDetail().getExpirationDate();
        if (expirationDate != null) {
            Text text2 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_WITHIN);
            text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(9);
            paragraph.add(text2);
            DateFormat formatter = new SimpleDateFormat(PaymentNoticeConstants.PDF_TEXT_EXPIRATION_DATE_FORMAT);
            Text text3 = new Text(" " + formatter.format(expirationDate));
            text3.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(9);
            paragraph.add(text3);
        }
        return cell.add(paragraph);
    }

    /**
     * 
     * @param debtPosition
     * @param pdfDocument
     * @param fiscalCode
     * @return
     */
    public static Cell createQrCodeCell(DebtPosition debtPosition, PdfDocument pdfDocument, String fiscalCode) {
        Cell cell = new Cell(2, 2);
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100);
        BarcodeQRCode barcode = new BarcodeQRCode(QrCodeBusiness.createQrCode(debtPosition, fiscalCode));
        Image img = new Image(barcode.createFormXObject(pdfDocument));
        img.setMarginTop(4).setWidth(75).setHeight(75).setAutoScale(false);
        paragraph.add(img);
        return cell.add(paragraph);
    }

    /**
     * 
     * @param debtPosition
     * @return
     * @throws Exception
     */
    public static Cell createAmountCell(DebtPosition debtPosition) throws Exception {
        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setFixedLeading(12)
                .setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.TOP);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_EURO);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(11);
        paragraph.add(text1);
        Text text2 = new Text(String.format(Locale.ITALY, PaymentNoticeConstants.PDF_TEXT_AMOUNT_FORMAT,
                debtPosition.getPaymentDetail().getTotalAmountPayment()));
        text2.setFont(PdfPaymentNoticeManagement.getRobotoFontBold()).setFontSize(11);
        paragraph.add(text2);
        return cell.add(paragraph);
    }

    /**
     * 
     * @param creditorInstitutionName
     * @return
     * @throws Exception
     */
    public static Cell createCreditorInstitutionCell(String creditorInstitutionName) throws Exception {
        Cell cell1 = new Cell();
        cell1.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setMarginLeft(1).setPadding(0).setFixedLeading(10);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_CREDITOR_INSTITUTION);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
        paragraph.add(text1);
        Text text2 = new Text(creditorInstitutionName);
        text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
        paragraph.add(text2);
        return cell1.add(paragraph);
    }

    /**
     * 
     * @param debtPosition
     * @return
     * @throws Exception
     */
    public static Cell createPaymentDetailsCell(DebtPosition debtPosition) throws Exception {
        Cell cell = new Cell(1, 3);
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setMarginLeft(1).setPadding(0).setFixedLeading(10);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_PAYMENT_SUBJECT);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
        paragraph.add(text1);
        String casual = debtPosition.getPaymentDetail().getCausal();
        if (casual.length() > 30)
            casual = casual.substring(0, 30) + "\r" + casual.substring(30);
        Text text2 = new Text(casual);
        text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
        paragraph.add(text2);
        return cell.add(paragraph);
    }

    /**
     * 
     * @param cbillCode
     * @return
     * @throws Exception
     */
    public static Cell createCbillCell(String cbillCode) throws Exception {
        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setFixedLeading(8);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_CBILL_CODE);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
        paragraph.add(text1);
        Text text2 = new Text(cbillCode != null ? cbillCode : " ");
        text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
        paragraph.add(text2);
        return cell.add(paragraph);
    }

    /**
     * 
     * @param fiscalCode
     * @return
     * @throws Exception
     */
    public static Cell createFiscalCodeCell(String fiscalCode) throws Exception {
        Cell cell = new Cell(1, 2);
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setPaddingRight(5).setFixedLeading(8)
                .setTextAlignment(TextAlignment.RIGHT);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_PCI_FISCAL_CODE);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
        paragraph.add(text1);
        Text text2 = new Text(fiscalCode);
        text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
        paragraph.add(text2);
        return cell.add(paragraph);
    }

    /**
     * 
     * @param debtPosition
     * @return
     * @throws Exception
     */
    public static Cell createNoticeNumberCell(DebtPosition debtPosition) throws Exception {
        Cell cell = new Cell(1, 3);
        cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
        Paragraph paragraph = new Paragraph();
        paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setFixedLeading(8);
        Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_NOTICE_NUMBER);
        text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
        paragraph.add(text1);
        Text text2 = new Text(
                PaymentNoticeBusiness.getFormattedNoticeNumber(debtPosition.getPaymentDetail().getNoticeNumber()));
        text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
        paragraph.add(text2);
        return cell.add(paragraph);
    }
}
