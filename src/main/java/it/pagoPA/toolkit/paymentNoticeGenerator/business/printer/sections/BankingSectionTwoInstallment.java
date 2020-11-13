package it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections;

import java.util.HashMap;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

import it.pagoPA.toolkit.debtPositionGenerator.bean.DebtPosition;
import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.BankingSectionInstallmentsBusiness;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.PdfPaymentNoticeManagement;
import it.pagoPA.toolkit.paymentNoticeGenerator.constants.PaymentNoticeConstants;

/**
 *
 */
public class BankingSectionTwoInstallment {

	PaymentNotice paymentNotice = null;
	PdfDocument pdfDocument = null;
	int startingInstallmentNumber = 0;
	HashMap<Integer, DebtPosition> sortedDebtPositionHashMap = null;

	/**
	 * 
	 * @param paymentNotice
	 * @param pdfDocument
	 * @param startingInstallmentNumber
	 * @param sortedDebtPositionHashMap
	 */
	public BankingSectionTwoInstallment(PaymentNotice paymentNotice, PdfDocument pdfDocument,
			int startingInstallmentNumber, HashMap<Integer, DebtPosition> sortedDebtPositionHashMap) {
		super();
		this.paymentNotice = paymentNotice;
		this.pdfDocument = pdfDocument;
		this.startingInstallmentNumber = startingInstallmentNumber;
		this.sortedDebtPositionHashMap = sortedDebtPositionHashMap;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createFirstRow() throws Exception {
		float[] colWidths = { 258, 8, 258 };
		Table table = new Table(colWidths);
		table.setHeight(20).setMargin(0).setPadding(0).setMarginLeft(20).setMarginTop(2);
		table.addCell(BankingSectionInstallmentsBusiness.createExpirationDateCell(sortedDebtPositionHashMap,
				startingInstallmentNumber + 1));
		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(8));
		table.addCell(BankingSectionInstallmentsBusiness.createExpirationDateCell(sortedDebtPositionHashMap,
				startingInstallmentNumber + 2));

		return table;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createSecondRow() throws Exception {
		float[] colWidths = { 258, 16, 258 };
		Table table = new Table(colWidths);
		table.setHeight(228).setMargin(0).setPadding(0).setMarginLeft(20);
		table.addCell(createPaymentInfoCell(startingInstallmentNumber + 1));
		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(16));
		table.addCell(createPaymentInfoCell(startingInstallmentNumber + 2));

		return table;
	}

	/**
	 * 
	 * @param installmentNumber
	 * @return
	 * @throws Exception
	 */
	public Cell createPaymentInfoCell(int installmentNumber) throws Exception {
		float[] colWidths = { 60, 64, 134 };
		Table table = new Table(colWidths);
		table.setHeight(200).setMargin(0).setPadding(0);
		Cell cell = new Cell();
		cell.setBorder(Border.NO_BORDER).setPadding(0).setMargin(0);
		DebtPosition debtPosition = sortedDebtPositionHashMap.get(installmentNumber);
		if (debtPosition != null) {
			table.addCell(BankingSectionInstallmentsBusiness.createQrCodeCell(debtPosition, pdfDocument,
					paymentNotice.getCreditorInstitution().getFiscalCode()));
			table.addCell(BankingSectionInstallmentsBusiness.createAmountCell(debtPosition));
			table.addCell(BankingSectionInstallmentsBusiness
					.createCreditorInstitutionCell(paymentNotice.getCreditorInstitution().getName()));
			table.addCell(BankingSectionInstallmentsBusiness.createPaymentDetailsCell(debtPosition));
			table.addCell(BankingSectionInstallmentsBusiness
					.createCbillCell(paymentNotice.getCreditorInstitution().getCbillCode()));
			table.addCell(BankingSectionInstallmentsBusiness
					.createFiscalCodeCell(paymentNotice.getCreditorInstitution().getFiscalCode()));
			table.addCell(BankingSectionInstallmentsBusiness.createNoticeNumberCell(debtPosition));
			table.addCell(createBankingInfoCell());
		}
		return cell.add(table);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private Cell createBankingInfoCell() throws Exception {
		Cell cell = new Cell(1, 3);
		cell.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
		Paragraph paragraph = new Paragraph();
		paragraph.setWidthPercent(100).setMargin(0).setPadding(0).setFixedLeading(7);
		Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_2_BANK_INFO_PART1);
		text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(7);
		paragraph.add(text1);
		Text text2 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_2_BANK_INFO_PART2);
		text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(7);
		paragraph.add(text2);
		Text text3 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_2_BANK_INFO_PART3);
		text3.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(7);
		paragraph.add(text3);
		Text text4 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_2_BANK_INFO_PART4);
		text4.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(7);
		paragraph.add(text4);
		Text text5 = new Text(PaymentNoticeConstants.PDF_TEXT_BANK_INST_2_BANK_INFO_PART5);
		text5.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(7);
		paragraph.add(text5);
		return cell.add(paragraph);
	}
}