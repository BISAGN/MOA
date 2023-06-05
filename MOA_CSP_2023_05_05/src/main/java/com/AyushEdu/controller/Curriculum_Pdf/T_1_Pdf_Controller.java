package com.AyushEdu.controller.Curriculum_Pdf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.AyushEdu.controller.DateWithTimestamp.DateWithTimeStampController;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class T_1_Pdf_Controller extends AbstractPdfView {

	String Type = "";
	List<String> TH;
	List<String> TH2;
	String Heading = "";
	String username = "";
	int total = 0;
	List<ArrayList<String>> new_list;
	ArrayList<ArrayList<String>> new_list2;
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	// public static final String ENCRYPTED_PDF =
	// "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	public T_1_Pdf_Controller(String Type, List<String> TH, List<String> TH2, String Heading, String username,
			List<ArrayList<String>> new_list,ArrayList<ArrayList<String>> new_list2, int total) {
		System.err.println("new_list2=============="+new_list2);
		this.Type = Type;
		this.TH = TH;
		this.TH2 = TH2;
		this.Heading = Heading;
		this.username = username;
		this.total = total;
		this.new_list = new_list;
		this.new_list2 = new_list2;
	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 25, 1);
		Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Image logo = null;
		try {
			@SuppressWarnings("deprecation")
			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "login_file" + File.separator
					+ "favicon.png";
			logo = Image.getInstance(dgis_logo);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(25);
		logo.scaleAbsoluteWidth(35);
		logo.scalePercent(0);
		Chunk chunk = new Chunk(logo, 250, 30);

		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
			String indian_Army = request.getRealPath("/") + "admin" + File.separator + "login_file" + File.separator
					+ "favicon.png";
			logo2 = Image.getInstance(indian_Army);// "d://indianarmy_smrm5aaa.jpg"
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo2.setAlignment(Image.RIGHT);
		logo2.scaleAbsoluteHeight(6);
		logo2.scaleAbsoluteWidth(6);
		logo2.scalePercent(0);
		Chunk chunk2 = new Chunk(logo2, 30, -7);
		Chunk underline = new Chunk("", fontTableHeading1);
		underline.setUnderline(0.1f, -2f);
		Chunk underline2 = new Chunk("List of Practicals AyUG-RS");
		underline2.setUnderline(0.1f, -2f);

		/*
		 * Chunk underline3 = new Chunk("DIGHI CAMP, PUNE-15",
		 * fontTableHeadingMainHead); underline3.setUnderline(0.1f, -2f); Chunk
		 * underline4 = new Chunk("TELE NO 02027170099 AND FAX NO 0194 2300137",
		 * fontTableHeadingSubMainHead); underline4.setUnderline(0.1f, -2f);
		 */
		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);
		
		p.add(underline2);
		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add("\n");
//		p.setFont(fontTableHeading1);
		Paragraph cell = new Paragraph(p);
		cell.setAlignment(Element.ALIGN_CENTER);
		float[] relativeWidths;
		int colunmSize = 3;

		relativeWidths = new float[colunmSize];
		Arrays.fill(relativeWidths, 0, colunmSize, 1);
		relativeWidths[1] = (float) 3.50;
		table3.addCell(new Phrase(chunk));
		table3.addCell(cell);
		table3.addCell(new Phrase(chunk2));
		Phrase p2 = new Phrase();
		try {
			table3.setWidths(relativeWidths);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table3.setWidthPercentage(120);
		try {
			table3.setWidths(new int[] {2,4,4});
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		p2.add(table3);
		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		Chunk underline1 = new Chunk("1", fontTableHeading1);
//		underline1.setUnderline(0.1f, -2f);
		Phrase p1 = new Phrase("" + glue);
		p1.add(underline1);
		p.add(glue);
		p1.setFont(fontTableHeading1);
		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);
		 document.setPageCount(1);
		document.setPageSize(PageSize.A4); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		// TODO Auto-generated method stub
//		response.setContentType("application/pdf");
//	response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
		ArrayList<ArrayList<String>> aList2 = new_list2;
		List<String> l1 = aList.get(0);
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(110);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		/*
		 * Chunk glue = new Chunk(new VerticalPositionMark()); Phrase p1 = new
		 * Phrase("-" + glue); p1.add(glue); table.addCell(p1);
		 * 
		 * LineSeparator ls = new LineSeparator(); document.add(new Chunk(ls));
		 */

		Paragraph p = new Paragraph();
		PdfPTable table3 = new PdfPTable(3);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH.size(); h++) {
			p = new Paragraph(TH.get(h), fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}

		table3.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			for (int j = 0; j < l.size(); j++) {
				p = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table3.addCell(p);
			}
		}
		table.addCell(table3);
		
//
		Paragraph py = new Paragraph();
		PdfPTable table3y = new PdfPTable(3);
		table3y.setWidthPercentage(100);
		table3y.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH2.size(); h++) {
			py = new Paragraph(TH2.get(h), fontTableHeadingNonBoldData);
			py.setAlignment(Element.ALIGN_CENTER);
			table3y.addCell(py);
		}

		table3y.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList2.size(); i++) {
			List<String> l = aList2.get(i);
			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table3y.addCell(py);
			}
		}
		table.addCell(table3y);	
//
		
		/////////////////////////////////
		
		PdfPTable tablex = new PdfPTable(1);
		tablex.setWidthPercentage(110);
		tablex.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatax = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		
		Paragraph p2 = new Paragraph();
		PdfPTable table32 = new PdfPTable(3);
		table32.setWidthPercentage(100);
		table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH2.size(); h++) {
			p2 = new Paragraph(TH2.get(h), fontTableHeadingNonBoldDatax);
			p2.setAlignment(Element.ALIGN_CENTER);
			table32.addCell(p2);
		}

		table32.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList2.size(); i++) {
			List<String> l = aList2.get(i);
			for (int j = 0; j < l.size(); j++) {
				p2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table32.addCell(p2);
			}
		}
		tablex.addCell(table32);
/////////////////////////////////
		
		
		document.add(table);
		document.add(tablex);
//		document.add(table22);
		super.buildPdfMetadata(model, document, request);
	}

//	class ImageBackgroundEvent implements PdfPTableEvent {
//		protected Image image;
//
//		HttpServletRequest request;
//
//		ImageBackgroundEvent(HttpServletRequest request) {
//			this.request = request;
//		}
//
//		int page = 1;
//
//		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
//				PdfContentByte[] canvases) {
//			String ip = "";
//			if (request != null) {
//				ip = request.getHeader("X-FORWARDED-FOR");
//				if (ip == null || "".equals(ip)) {
//					ip = request.getRemoteAddr();
//				}
//			}
//			System.out.println("IP==" + ip);
//			Date now = new Date();
//			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);
//			String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;
//
//			Image img = null;
//			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
//					BufferedImage.TYPE_INT_ARGB);
//			Graphics graphics = bufferedImage.getGraphics();
//			graphics.setColor(Color.lightGray);
//			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
//			graphics.drawString(watermark + watermark, 0, 20);
//
//			try {
//				try {
//					img = com.lowagie.text.Image.getInstance(bufferedImage, null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (BadElementException e) {
//				e.printStackTrace();
//			}
//			this.image = img;
//
//			try {
//
//				System.out.println("width == " + widths.length);
//				System.out.println("heights == " + heights.length);
//				System.out.println("headerRows == " + headerRows);
//				System.out.println("rowStart == " + rowStart);
//				System.out.println("canvases == " + canvases.length);
//				System.out.println("table height == " + table.getRowHeight(0));
//				System.out.println("table width == " + table.getTotalWidth());
//
//				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
//				 //Portrait Page size 700 * 523
//				// Landscape page size 453 * 770
//
//				int tableWidth = (int) table.getTotalWidth();
//				int first = 0;
//				if (tableWidth == 523) {
//					first = 750;
//				}
//				if (tableWidth == 770) {
//					first = 500;
//				}
//
//				int loop = (int) table.getRowHeight(0);
//				int last = first - (int) table.getRowHeight(0);
//				System.out.println(first + "==" + loop + "==" + last);
//				System.out.println(" cb.getPdfDocument( )==" + cb.getPdfDocument().getPageNumber());
//				Phrase p = new Phrase();
//				p.add(String.valueOf(page));
//				float width = ColumnText.getWidth(p);
//				ColumnText.showTextAligned(cb, PdfContentByte.ALIGN_RIGHT, p, cb.getPdfDocument().right() - width,
//						cb.getPdfDocument().top() + 9, 0);
//				page += 1;
//				while (first > last) {
//					image.setAbsolutePosition(30, first);
//					cb.addImage(image, false);
//					first -= 30;
//				}
//			} catch (DocumentException e) {
//				throw new ExceptionConverter(e);
//			}
//		}
//	}
}
