package it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.sections;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import it.pagoPA.toolkit.paymentNoticeGenerator.bean.PaymentNotice;
import it.pagoPA.toolkit.paymentNoticeGenerator.business.printer.PdfPaymentNoticeManagement;
import it.pagoPA.toolkit.paymentNoticeGenerator.constants.PaymentNoticeConstants;

/**
 * 
 */
public class HeaderSection {

	private PaymentNotice paymentNotice = null;
	private boolean isInstallmentPage = false;
	private String installmentPageType = "";
	private int initialInstallmentNumber = 0;

	/**
	 * 
	 * @param paymentNotice
	 */
	public HeaderSection(PaymentNotice paymentNotice) {
		super();
		this.paymentNotice = paymentNotice;
	}

	/**
	 * 
	 * @param paymentNotice
	 * @param isPaginaRata
	 * @param tipoPaginaRata
	 * @param numRataIniziale
	 */
	public HeaderSection(PaymentNotice paymentNotice, boolean isPaginaRata, String tipoPaginaRata,
			int numRataIniziale) {
		super();
		this.paymentNotice = paymentNotice;
		this.isInstallmentPage = isPaginaRata;
		this.installmentPageType = tipoPaginaRata;
		this.initialInstallmentNumber = numRataIniziale;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Table createHeaderSection() throws Exception {
		float[] colWidths = { 400, 110 };
		Table table = new Table(colWidths);
		table.setHeight(105).setMarginLeft(20).setPadding(0);

		Cell cell1 = new Cell();
		cell1.setHeight(28).setBorder(new SolidBorder(1)).setBorder(Border.NO_BORDER).setPadding(0).setMargin(0)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setWidthPercent(100).setPadding(0).setMargin(0).setPaddingTop(5).setPaddingLeft(45)
				.setFixedLeading(11).setVerticalAlignment(VerticalAlignment.BOTTOM);
		Text text1 = new Text(PaymentNoticeConstants.PDF_TEXT_NOTICE_PAYMENT);
		text1.setFont(PdfPaymentNoticeManagement.getTitiilliumWebBold()).setFontSize(11);
		paragraph1.add(text1);
		cell1.add(paragraph1);
		table.addCell(cell1);

		Cell cell2 = new Cell(3, 1);
		cell2.setBorder(Border.NO_BORDER).setWidth(110);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.setWidthPercent(100).setTextAlignment(TextAlignment.RIGHT)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		Image img = PdfPaymentNoticeManagement
				.getScaleOfGrayLogoImageFromByte(paymentNotice.getCreditorInstitution().getLogo(), true);
		img.setMarginRight(5);
		paragraph2.add(img);
		cell2.add(paragraph2);
		table.addCell(cell2);

		Cell cell3 = new Cell();
		cell3.setHeight(50).setPadding(0).setMargin(0).setPaddingTop(10).setBorder(Border.NO_BORDER);
		Paragraph paragraph3 = new Paragraph();
		paragraph3.setWidthPercent(100).setPadding(0).setMargin(0).setFixedLeading(14).setHeightPercent(100);
		Text text3 = createPaymentSubject(PdfPaymentNoticeManagement.getTitiilliumWebBold());
		text3.setHeightPercent(100);
		paragraph3.add(text3);
		cell3.add(paragraph3);
		table.addCell(cell3);

		Cell cell4 = new Cell();
		cell4.setBorder(Border.NO_BORDER).setMargin(0).setPadding(0);
		Paragraph paragraph4 = new Paragraph();
		paragraph4.setWidthPercent(100).setMargin(0).setPadding(0).setFixedLeading(8).setHeightPercent(100);
		Text text4 = new Text(isInstallmentPage ? PaymentNoticeConstants.PDF_TEXT_PAYMENT_CHANNEL : " ");
		text4.setFont(PdfPaymentNoticeManagement.getTitilliumWebRegular()).setFontSize(8);
		paragraph4.add(text4);
		cell4.add(paragraph4);
		table.addCell(cell4);

		return table;
	}

	/**
	 * 
	 * @param font
	 * @return
	 */
	private Text createPaymentSubject(PdfFont font) {
		String paymentDescription = "";
		if (isInstallmentPage) {
			paymentDescription += PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_START;
			if (installmentPageType.equals(PaymentNoticeConstants.PAGE_TYPE_TWO_INSTALLMENTS)) {
				paymentDescription += PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_TWO_INSTALLMENTS
						.replace(PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_PLACEHOLDER_ONE,
								String.valueOf(initialInstallmentNumber + 1))
						.replace(PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_PLACEHOLDER_TWO,
								String.valueOf(initialInstallmentNumber + 2));
			} else {
				paymentDescription += PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_THREE_INSTALLMENTS
						.replace(PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_PLACEHOLDER_ONE,
								String.valueOf(initialInstallmentNumber + 1))
						.replace(PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_PLACEHOLDER_TWO,
								String.valueOf(initialInstallmentNumber + 2))
						.replace(PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_PLACEHOLDER_THREE,
								String.valueOf(initialInstallmentNumber + 3));
			}
			paymentDescription += PaymentNoticeConstants.PDF_TEXT_DESCRIPTION_SEPARATOR;
		}
		paymentDescription += paymentNotice.getDebtPositionList().get(0).getPaymentDetail().getCausal();
		Text text = new Text(paymentDescription);
		text.setFont(font).setFontSize(14);
		return text;
	}
}
