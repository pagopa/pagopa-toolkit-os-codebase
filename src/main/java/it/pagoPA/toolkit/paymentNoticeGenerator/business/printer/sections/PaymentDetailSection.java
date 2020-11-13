package it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.PaymentNoticeBusiness;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.PdfPaymentNoticeManagement;
import it.pagoPA.toolkit.paymentNoticeGenerator.constants.PaymentNoticeConstants;

/**
 * 
 */
public class PaymentDetailSection {

	PaymentNotice paymentNotice = null;

	/**
	 * 
	 * @param paymentNotice
	 */
	public PaymentDetailSection(PaymentNotice paymentNotice) {
		super();
		this.paymentNotice = paymentNotice;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createFirstRow() throws Exception {
		float[] colWidths = { 273f, 4f, 273f };
		Table table = new Table(colWidths);
		table.setHeight(25).setMarginLeft(10).setMarginTop(2);

		Cell cell1 = new Cell();
		cell1.setBorder(Border.NO_BORDER);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setWidthPercent(100).setMarginTop(0).setMarginLeft(10).setFixedLeading(10);
		Text text1_1 = new Text(PaymentNoticeConstants.PDF_TEXT_HOWMUCH_WHEN_TOPAY);
		text1_1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(9);
		paragraph1.add(text1_1);
		if (paymentNotice.getDebtPositionList().size() > 1
				&& PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
			Text text1_2 = new Text(PaymentNoticeConstants.PDF_TEXT_OPTIONAL_INSTALLMENT_PAYMENT);
			text1_2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8f);
			paragraph1.add(text1_2);
		}
		cell1.add(paragraph1);
		table.addCell(cell1);

		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(4));

		Cell cell2 = new Cell();
		cell2.setBorder(Border.NO_BORDER);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.setWidthPercent(100).setMarginTop(0).setMarginLeft(10).setFixedLeading(10);
		Text text2_1 = new Text(PaymentNoticeConstants.PDF_TEXT_WHERE_TOPAY);
		text2_1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(9);
		paragraph2.add(text2_1);
		Text text2_2 = new Text(PaymentNoticeConstants.PDF_TEXT_PAYMENT_CHANNELS_LIST);
		text2_2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8f);
		paragraph2.add(text2_2);
		Text text2_3 = new Text(PaymentNoticeConstants.PDF_TEXT_PAGOPA_WEBSITE);
		text2_3.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8f);
		paragraph2.add(text2_3);
		cell2.add(paragraph2);
		table.addCell(cell2);

		return table;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createSecondRow() throws Exception {
		float[] colWidths = { 260, 4, 280 };
		Table table = new Table(colWidths);
		table.setHeight(115).setMarginLeft(0).setMarginTop(6);

		Cell cell1 = new Cell();
		cell1.setBorder(Border.NO_BORDER);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setWidthPercent(100).setMarginTop(8).setMarginBottom(0).setWidth(260).setMarginLeft(20)
				.setFixedLeading(10).setTextAlignment(TextAlignment.LEFT);
		createAmountExpirationRow(paragraph1);
		createAmountInfoPart1(paragraph1);
		cell1.add(paragraph1);
		table.addCell(cell1);

		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(4));

		Cell cell2 = new Cell();
		cell2.setBorder(Border.NO_BORDER);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.setWidth(165).setMarginTop(5).setMarginLeft(10).setFixedLeading(10);
		createWhereToPayInfoPart1(paragraph2);
		cell2.add(paragraph2);
		table.addCell(cell2);

		Cell cell3 = new Cell();
		cell3.setBorder(Border.NO_BORDER);
		Paragraph paragraph3 = new Paragraph();
		paragraph3.setWidth(260).setMarginTop(2).setMarginLeft(20).setFixedLeading(8);
		createAmountInfoPart2(paragraph3);
		cell3.add(paragraph3);
		table.addCell(cell3);

		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(4));

		Cell cell4 = new Cell();
		cell4.setBorder(Border.NO_BORDER);
		Paragraph paragraph4 = new Paragraph();
		paragraph4.setWidth(180).setMarginTop(2).setMarginLeft(10).setFixedLeading(10);
		createWhereToPayInfoPart2(paragraph4);
		cell4.add(paragraph4);
		table.addCell(cell4);

		return table;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createThirdRow() throws Exception {
		float[] colWidths = { 500, 50 };
		Table table = new Table(colWidths);
		table.setHeight(22).setMarginLeft(0).setMarginTop(5).setBorder(Border.NO_BORDER);

		Cell cell1 = new Cell();
		cell1.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0).setVerticalAlignment(VerticalAlignment.BOTTOM);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setTextAlignment(TextAlignment.LEFT).setFixedLeading(11).setMargin(0).setPadding(0).setMarginLeft(20)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		createHowToPayInfo(paragraph1);
		cell1.add(paragraph1);
		table.addCell(cell1);

		table.addCell(PdfPaymentNoticeManagement.getEmptyCell(50));

		return table;
	}

	/**
	 * 
	 * @param paragraph
	 * @return
	 * @throws Exception
	 */
	private void createAmountExpirationRow(Paragraph paragraph) throws Exception {
		Text text1 = new Text(String.format(Locale.ITALY, PaymentNoticeConstants.PDF_TEXT_AMOUNT_FORMAT,
				PaymentNoticeBusiness.getPaymentTotaleAmount(paymentNotice.getDebtPositionList())));
		text1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(14f);
		paragraph.add(text1);

		Text text2 = new Text(PaymentNoticeConstants.PDF_TEXT_EURO);
		text2.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(14f);
		paragraph.add(text2);

		Date expirationDate = PaymentNoticeBusiness.getExpirationDate(paymentNotice.getDebtPositionList());
		if (expirationDate != null) {
			Text text3 = new Text(PaymentNoticeConstants.PDF_TEXT_WITHIN);
			text3.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(10f);
			paragraph.add(text3);

			DateFormat formatter = new SimpleDateFormat(PaymentNoticeConstants.PDF_TEXT_EXPIRATION_DATE_FORMAT);
			Text text4 = new Text(formatter.format(expirationDate));
			text4.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(14f);
			paragraph.add(text4);
		}
	}

	/**
	 * 
	 * @param paragraph
	 * @throws Exception
	 */
	private void createAmountInfoPart1(Paragraph paragraph) throws Exception {
		Text text1_1 = new Text(PaymentNoticeConstants.PDF_TEXT_YOU_CAN_PAY);
		text1_1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
		paragraph.add(text1_1);
		if (paymentNotice.getDebtPositionList().size() == 1
				|| PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
			Text text1_2 = new Text(PaymentNoticeConstants.PDF_TEXT_WITH);
			text1_2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
			paragraph.add(text1_2);
			Text text1_3 = new Text(PaymentNoticeConstants.PDF_TEXT_SINGLE_INSTALLMENT);
			text1_3.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(8);
			paragraph.add(text1_3);
		}
		if (paymentNotice.getDebtPositionList().size() == 1) {
			Text text1_4 = new Text(PaymentNoticeConstants.PDF_TEXT_POINT);
			text1_4.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
			paragraph.add(text1_4);
		} else {
			if (PaymentNoticeBusiness.hasSingleInstallment(paymentNotice)) {
				Text text1_5 = new Text(PaymentNoticeConstants.PDF_TEXT_OR);
				text1_5.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
				paragraph.add(text1_5);
			}
			Text text1_6 = new Text(PaymentNoticeConstants.PDF_TEXT_INSTALLMENT_PAYMENT_INFO.replace(
					PaymentNoticeConstants.PDF_TEXT_DEBT_POSITION_LIST_SIZE_PLACEHOLDER,
					String.valueOf(paymentNotice.getDebtPositionList().size())));
			text1_6.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
			paragraph.add(text1_6);
		}
	}

	/**
	 * 
	 * @param paragraph
	 * @throws Exception
	 */
	private void createAmountInfoPart2(Paragraph paragraph) throws Exception {
		StringBuilder sb = new StringBuilder(PaymentNoticeConstants.PDF_TEXT_AMOUNT_INFO_PART1);
		sb.append(PaymentNoticeConstants.PDF_TEXT_AMOUNT_INFO_PART2);
		sb.append(PaymentNoticeConstants.PDF_TEXT_AMOUNT_INFO_PART3);
		sb.append(PaymentNoticeConstants.PDF_TEXT_AMOUNT_INFO_PART4);
		Text text = new Text(sb.toString());
		text.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
		paragraph.add(text);
	}

	/**
	 * 
	 * @param paragraph
	 * @throws Exception
	 */
	private void createWhereToPayInfoPart1(Paragraph paragraph) throws Exception {
		Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_WEBSITE_TOPAY);
		text1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(10f);
		paragraph.add(text1);
		StringBuilder sb = new StringBuilder("\r");
		sb.append(PaymentNoticeConstants.PDF_TEXT_YOUR_CREDITOR_INSTITUTION);
		if (paymentNotice.getCreditorInstitution().getWebsite() != null)
			sb.append(PaymentNoticeConstants.PDF_TEXT_CI_WEBSITE.replace(
					PaymentNoticeConstants.PDF_TEXT_CI_WEBSITE_PLACEHOLDER,
					paymentNotice.getCreditorInstitution().getWebsite()));
		sb.append(PaymentNoticeConstants.PDF_TEXT_COMMA);
		if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null)
			sb.append(PaymentNoticeConstants.PDF_TEXT_POSTE_ITALIANE);
		sb.append(PaymentNoticeConstants.PDF_TEXT_PAYMENT_INFO_PART1);
		sb.append(PaymentNoticeConstants.PDF_TEXT_PAYMENT_INFO_PART2);
		if (paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null)
			sb.append("\r");
		Text text2 = new Text(sb.toString());
		text2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(6);
		paragraph.add(text2);
	}

	/**
	 * 
	 * @param paragraph
	 * @throws Exception
	 */
	private void createWhereToPayInfoPart2(Paragraph paragraph) throws Exception {
		Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_PAY_ON_TERRITORY);
		text1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(11f);
		paragraph.add(text1);
		String sbText = ((paymentNotice.getCreditorInstitution().getPostalAuthorizationCode() != null)
				? PaymentNoticeConstants.PDF_TEXT_WHERE_TOPAY_POSTAL
				: PaymentNoticeConstants.PDF_TEXT_WHERE_TOPAY_BANKING);
		StringBuilder sb = new StringBuilder(sbText);
		sb.append(PaymentNoticeConstants.PDF_TEXT_WHERE_TOPAY_PART1);
		sb.append(PaymentNoticeConstants.PDF_TEXT_WHERE_TOPAY_PART2);
		Text text2 = new Text(sb.toString());
		text2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(6);
		paragraph.add(text2);
	}

	/**
	 * 
	 * @param paragraph
	 * @throws Exception
	 */
	private void createHowToPayInfo(Paragraph paragraph) throws Exception {
		Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_HOW_TOPAY);
		text1.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(11);
		paragraph.add(text1);
		String text2_1 = ((paymentNotice.getDebtPositionList().size() > 1
				&& PaymentNoticeBusiness.hasSingleInstallment(paymentNotice))
						? PaymentNoticeConstants.PDF_TEXT_TO_THE_INSTALLMENT : "")
				+ PaymentNoticeConstants.PDF_TEXT_PREFERRED_PAYMENT_CHANNEL;
		Text text2_2 = new Text(text2_1);
		text2_2.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(11);
		paragraph.add(text2_2);
	}
}
