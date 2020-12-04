package it.pagoPA.toolkit.paymentNoticeGenerator.business;

import java.io.IOException;
import java.util.HashMap;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.property.AreaBreakType;

import it.pagoPA.toolkit.common.ErrorMessages;
import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.PdfPaymentNoticeManagement;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.BankingSection;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.BankingSectionThreeInstallment;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.BankingSectionTwoInstallment;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.HeaderSection;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.PaymentDetailSection;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.PaymentInfoSection;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections.PostalSection;
import it.pagoPA.toolkit.paymentNoticeGenerator.constants.PaymentNoticeConstants;

/**
 *
 */
public class PdfPaymentNoticeCreator {

    private PdfWriter pdfWriter = null;
    private PdfDocument pdfDocument = null;
    private Document document = null;
    private PageSize pageSize = null;
    private ByteArrayOutputStream stream = null;
    private PaymentNotice paymentNotice = null;
    private HashMap<Integer, DebtPosition> sortedDebtPositionHashMap = null;

    /**
     * Public constructor
     * 
     * @param paymentNotice
     * @throws Exception
     */
    public PdfPaymentNoticeCreator(PaymentNotice paymentNotice) throws Exception {
        super();
        this.paymentNotice = paymentNotice;
        initialize();
    }

    /**
     * 
     */
    private void initialize() {
        stream = new ByteArrayOutputStream();
        pdfWriter = new PdfWriter(stream);
        pdfDocument = new PdfDocument(pdfWriter);
        pageSize = new PageSize(PageSize.A4);
        document = new Document(pdfDocument, pageSize);
        document.setMargins(20, 15, 15, 10);
    }

    /**
     * 
     * @throws Exception
     */
    public void createDocument() throws Exception {
        createTemplate();

        createHeadingSection();

        createPaymentInfoSection();

        createAmountAndExpirySection();

        if (paymentNotice.getDebtPositionList().size() == 1
                || PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
            createBankingSection();

            if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null) {
                createPostalSection();
            }
        }

        if (paymentNotice.getDebtPositionList().size() > 1) {
            creaInstallmentSections();
        }
    }

    /**
     * @throws Exception
     */
    private void createTemplate() throws Exception {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.addNewPage());
        String template = "";
        if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null) {
            if (paymentNotice.getDebtPositionList().size() == 1) {
                template = PaymentNoticeConstants.baseTemplate;
            } else {
                if (PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
                    template = PaymentNoticeConstants.baseTemplate;
                } else {
                    template = PaymentNoticeConstants.baseTemplate_NoPostalSection_NoSingleInstallment;
                }
            }
        } else {
            if (paymentNotice.getDebtPositionList().size() == 1) {
                template = PaymentNoticeConstants.baseTemplate_NoPostalSection;
            } else {
                if (PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
                    template = PaymentNoticeConstants.baseTemplate_NoPostalSection;
                } else {
                    template = PaymentNoticeConstants.baseTemplate_NoPostalSection_NoSingleInstallment;
                }
            }
        }
        canvas.addImage(PdfPaymentNoticeManagement.creaImgData(template), pageSize, false);
    }

    /**
     * 
     * @throws Exception
     */
    private void createHeadingSection() throws Exception {
        HeaderSection headerSection = new HeaderSection(paymentNotice);
        document.add(headerSection.createHeaderSection());
    }

    /**
     * 
     * @throws Exception
     */
    private void createPaymentInfoSection() throws Exception {
        PaymentInfoSection paymentInfoSection = new PaymentInfoSection(paymentNotice);
        document.add(paymentInfoSection.createFirstRow());
        document.add(paymentInfoSection.createSecondRow());
        document.add(paymentInfoSection.createThirdRow());
        document.add(paymentInfoSection.createFourthRow());
    }

    /**
     * 
     * @throws Exception
     */
    private void createAmountAndExpirySection() throws Exception {
        PaymentDetailSection paymentDetailSection = new PaymentDetailSection(paymentNotice);
        document.add(paymentDetailSection.createFirstRow());
        document.add(paymentDetailSection.createSecondRow());
        document.add(paymentDetailSection.createThirdRow());
    }

    /**
     * 
     * @throws Exception
     */
    private void createBankingSection() throws Exception {
        BankingSection bankingSection = new BankingSection(paymentNotice, pdfDocument);
        document.add(bankingSection.createFirstRow());
        document.add(bankingSection.createSecondRow());
    }

    /**
     * 
     * @throws Exception
     */
    private void createPostalSection() throws Exception {
        PostalSection postalSection = new PostalSection(paymentNotice, pdfDocument);
        document.add(postalSection.createFirstRow());
        document.add(postalSection.createSecondRow());
    }

    /**
     * 
     * @throws Exception
     */
    private void creaInstallmentSections() throws Exception {
        sortedDebtPositionHashMap = PaymentNoticeBusiness
                .sortDebtPositionListByInstallmentNumberExcludingSingleInstallment(paymentNotice.getDebtPositionList());
        int installmentsNumber = sortedDebtPositionHashMap.size();
        int modulo3 = installmentsNumber % 3;
        int divisionBy2 = installmentsNumber / 2;
        int divisionBy3 = installmentsNumber / 3;

        if (modulo3 == 0) {
            // CASI 3-6-9... RATE
            // 3 rate a pagina
            for (int i = 0; i < divisionBy3; i++) {
                createThreeInstallmentsPage(i * 3);
            }
        } else {
            if (modulo3 == 2) {
                // e' presente almeno 1 pagina da 2 rate
                if (divisionBy3 > 0) {
                    // CASI 5-8-11... RATE
                    // x pagine da 3 rate e l'ultima pagina da 2 rate
                    int i = 0;
                    for (i = 0; i < divisionBy3; i++) {
                        createThreeInstallmentsPage(i * 3);
                    }
                    createTwoInstallmentsPage(i * 3);
                } else {
                    // CASO 2 RATE
                    for (int i = 0; i < divisionBy2; i++) {
                        createTwoInstallmentsPage(i * 2);
                    }
                }
            } else {
                // sono presenti almeno 2 pagine da 2 rate
                if (divisionBy3 > 0) {
                    // CASI 4-7-10... RATE
                    // x pagine da 3 rate e le ultime 2 pagine da 2 rate
                    int i = 0;
                    for (i = 0; i < divisionBy3 - 1; i++) {
                        createThreeInstallmentsPage(i * 3);
                    }
                    createTwoInstallmentsPage(i * 3);
                    createTwoInstallmentsPage(i * 3 + 2);
                } else {
                    // CASO 1 RATA
                    throw new Exception(ErrorMessages.VALIDATION_ONLY_1_INSTALLMENT);
                }
            }
        }
    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createThreeInstallmentsPage(int startingInstallmentNumber) throws Exception {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.addNewPage());
        canvas.addImage(
                PdfPaymentNoticeManagement
                        .creaImgData(paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null
                                ? PaymentNoticeConstants.threeInstallmentsTemplate
                                : PaymentNoticeConstants.threeInstallmentsTemplate_noPostalSection),
                pageSize, false);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        createInstallmentHeaderSection(PaymentNoticeConstants.PAGE_TYPE_THREE_INSTALLMENTS, startingInstallmentNumber);
        createThreeInstallmentsBankingSection(startingInstallmentNumber);
        if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null)
            createThreeInstallmentsPostalSection(startingInstallmentNumber);

    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createThreeInstallmentsBankingSection(int startingInstallmentNumber) throws Exception {
        BankingSectionThreeInstallment bankingSectionThreeInstallment = new BankingSectionThreeInstallment(
                paymentNotice, pdfDocument, startingInstallmentNumber, sortedDebtPositionHashMap);
        document.add(bankingSectionThreeInstallment.createFirstRow());
        document.add(bankingSectionThreeInstallment.createSecondRow());
    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createThreeInstallmentsPostalSection(int startingInstallmentNumber) throws Exception {
        for (int i = 0; i < 3; i++) {
            int installmentNumber = startingInstallmentNumber + i + 1;
            DebtPosition debtPosition = sortedDebtPositionHashMap.get(installmentNumber);
            PostalSection postalSection = new PostalSection(paymentNotice, pdfDocument, debtPosition, installmentNumber,
                    true);
            document.add(postalSection.createFirstRow());
            document.add(postalSection.createSecondRow());
        }
    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createTwoInstallmentsPage(int startingInstallmentNumber) throws Exception {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.addNewPage());
        canvas.addImage(
                PdfPaymentNoticeManagement
                        .creaImgData(paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null
                                ? PaymentNoticeConstants.twoInstallmentsTemplate
                                : PaymentNoticeConstants.twoInstallmentsTemplate_noPostalSection),
                pageSize, false);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        createInstallmentHeaderSection(PaymentNoticeConstants.PAGE_TYPE_TWO_INSTALLMENTS, startingInstallmentNumber);
        createTwoInstallmentsBankingSection(startingInstallmentNumber);
        if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null)
            createTwoInstallmentsPostalSection(startingInstallmentNumber);
    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createTwoInstallmentsBankingSection(int startingInstallmentNumber) throws Exception {
        BankingSectionTwoInstallment bankingSectionTwoInstallment = new BankingSectionTwoInstallment(paymentNotice,
                pdfDocument, startingInstallmentNumber, sortedDebtPositionHashMap);
        document.add(bankingSectionTwoInstallment.createFirstRow());
        document.add(bankingSectionTwoInstallment.createSecondRow());
    }

    /**
     * 
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createTwoInstallmentsPostalSection(int startingInstallmentNumber) throws Exception {
        for (int i = 0; i < 2; i++) {
            int installmentNumber = startingInstallmentNumber + i + 1;
            DebtPosition debtPosition = sortedDebtPositionHashMap.get(installmentNumber);
            PostalSection postalSection = new PostalSection(paymentNotice, pdfDocument, debtPosition, installmentNumber,
                    false);
            document.add(postalSection.createFirstRow());
            document.add(postalSection.createSecondRow());
        }
    }

    /**
     * 
     * @param installmentPageType
     * @param startingInstallmentNumber
     * @throws Exception
     */
    private void createInstallmentHeaderSection(String installmentPageType, int startingInstallmentNumber)
            throws Exception {
        HeaderSection headerSection = new HeaderSection(paymentNotice, true, installmentPageType,
                startingInstallmentNumber);
        document.add(headerSection.createHeaderSection());
    }

    /**
     * @throws Exception
     */
    public void closeStreams() throws Exception {
        try {
            if (pdfWriter != null && !pdfWriter.isCloseStream())
                pdfWriter.close();
        } catch (IOException ioe) {
            throw ioe;
        }

        if (pdfDocument != null && !pdfDocument.isClosed())
            pdfDocument.close();

        if (document != null)
            document.close();

        try {
            if (stream != null)
                stream.close();
        } catch (IOException ioe) {
            throw ioe;
        }
    }

    /**
     * @return byteArray notice payment
     */
    public byte[] getDocumentInBytes() {
        return stream.toByteArray();
    }
}
