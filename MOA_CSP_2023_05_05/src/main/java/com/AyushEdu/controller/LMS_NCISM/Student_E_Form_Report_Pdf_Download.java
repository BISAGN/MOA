package com.AyushEdu.controller.LMS_NCISM;




import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.AyushEdu.controller.DateWithTimestamp.DateWithTimeStampController;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Student_E_Form_Report_Pdf_Download extends AbstractPdfView{
	

	String Type = "";
	List<String> TH1;
//	List<String> TH2;
	String Heading = "";
	String username = "";
	String role = "";
	int total = 0;

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	public Student_E_Form_Report_Pdf_Download(String Type, List<String> TH1, String Heading, int total,String role) {
//		System.err.println("new_list2=============="+new_list2);
		this.Type = Type;
		this.TH1 = TH1;
		this.Heading = Heading;
		this.total = total;
		this.role = role;

	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingBig = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 18, 1);
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
		logo2.setAlignment(Image.MIDDLE);
		logo2.scaleAbsoluteHeight(6);
		logo2.scaleAbsoluteWidth(6);
		logo2.scalePercent(0);
		Chunk chunk2 = new Chunk(logo2, 30, -7);
		Chunk underline = new Chunk("", fontTableHeading1);
		underline.setUnderline(0.1f, -70f);
		Chunk underline2 = new Chunk("Admission Details");
		underline2.setUnderline(0.1f, -2f);
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
		Chunk underline3 = new Chunk("NCISM", fontTableHeadingBig);
		underline3.setUnderline(0.1f, -2f);
		
		
		
		Chunk underline4 = new Chunk("Academic Year: 2022-23");
		underline4.setUnderline(0.1f, -2f);

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
		
		
		p.add(underline3);
		p.add("\n");
		p.add("\n");
		p.add(underline2);
		p.add("\n");
		p.add("\n");
		
//		p.add(underline3);
//		p.add("\n");
//		p.add("\n");
		
		p.add(underline4);
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
		p2.add(table3);
		HeaderFooter header = new HeaderFooter(p2, false);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		Chunk underline1 = new Chunk("", fontTableHeading1);
//		underline1.setUnderline(0.1f, -2f);
		Phrase p1 = new Phrase("" + glue);
		p1.add(underline1);
		p.add(glue);
		p1.setFont(fontTableHeading1);
		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);
//		document.setPageCount();
		document.setPageSize(PageSize.A4); // set document landscape
		super.buildPdfMetadata(model, document, request);
		
		
		if (Type.equals("L")) {
			 document.setPageSize(PageSize.A3.rotate());// set document portrait
		}
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		// TODO Auto-generated method stub
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
//		ArrayList<ArrayList<String>> aList2 = new_list2;
		List<String> l1 = aList.get(0);
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Font fontTableHeadingNonBoldDataSize = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 10, 0);
		/*
		 * Chunk glue = new Chunk(new VerticalPositionMark()); Phrase p1 = new
		 * Phrase("-" + glue); p1.add(glue); table.addCell(p1);
		 * 
		 * LineSeparator ls = new LineSeparator(); document.add(new Chunk(ls));
		 */

//		Paragraph p44 = new Paragraph("E - Form Student Report", fontTableHeadingNonBoldData);
//		p44.setAlignment(Element.ALIGN_CENTER);
		
	
		Paragraph p = new Paragraph();
		
		PdfPTable table3 = new PdfPTable(1);
			 table3 = new PdfPTable(6);

		table3.setWidthPercentage(100);
		table3.setWidths(new int[] { 35, 30, 25, 25, 25, 25 });
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH1.size(); h++) {
			p = new Paragraph(TH1.get(h), fontTableHeadingNonBoldData);
			p.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p);
		}

//		table3.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			for (int j = 0; j < l.size(); j++) {
				p = new Paragraph(l.get(j),fontTableHeadingNonBoldDataSize);
				table3.addCell(p);
			}
		}
		table.addCell(table3);
		
		document.add(table);
		super.buildPdfMetadata(model, document, request);
	}

}
