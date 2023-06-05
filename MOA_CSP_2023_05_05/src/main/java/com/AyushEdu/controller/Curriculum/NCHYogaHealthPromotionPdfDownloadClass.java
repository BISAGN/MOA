package com.AyushEdu.controller.Curriculum;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

public class NCHYogaHealthPromotionPdfDownloadClass  extends AbstractPdfView{
	String Type = "";
	List<Map<String, Object>> sysdegprofcorsnamecode;
	List<String> TH;
	List<ArrayList<String>> List_of_Topic_listYOGA;
	
	public NCHYogaHealthPromotionPdfDownloadClass(List<Map<String, Object>> sysdegprofcorsnamecode,
			List<String> TH,List<ArrayList<String>> List_of_Topic_listYOGA) {

		this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
		this.TH = TH;
		this.List_of_Topic_listYOGA = List_of_Topic_listYOGA;

	}
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
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

		Chunk glue = new Chunk(new VerticalPositionMark());
		PdfPTable table3 = new PdfPTable(3);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p = new Phrase(underline);

		p.add("\n");
		p.add("\n");
		p.add("\n");
		p.add("\n");
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
		Phrase p1 = new Phrase("" + glue);
		p1.add(underline1);
		p.add(glue);
		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);
		document.setPageSize(PageSize.A4); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");
		
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		
		Font fontTablemainHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
		Font fontTablemain2Heading = FontFactory.getFont(FontFactory.defaultEncoding, 18);
		Font fontTablemain2Heading1 = FontFactory.getFont(FontFactory.defaultEncoding, 15);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 12, 0);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(110);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

// For System Degree Prof Course Name and Code ===========================================================

		PdfPTable Table1 = new PdfPTable(1);
		Table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		Table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Table1.addCell("");
		Table1.addCell("");

		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Chunk profsys = new Chunk(" " + sysdegprofcorsnamecode.get(0).get("professional").toString() + "" + " "
				+ sysdegprofcorsnamecode.get(0).get("system_name").toString() + "", fontTablemainHeading);
		Chunk degree = new Chunk(" (" + sysdegprofcorsnamecode.get(0).get("degree_name").toString() + ")",
				fontTablemainHeading);
		Chunk coursecode = new Chunk(
				"Subject Code: " + sysdegprofcorsnamecode.get(0).get("course_code").toString() + "",
				fontTablemain2Heading);
		Chunk course = new Chunk(" " + sysdegprofcorsnamecode.get(0).get("course_name").toString() + "",
				fontTablemainHeading);

		Paragraph p12 = new Paragraph(profsys);
		Paragraph p13 = new Paragraph(degree);
		Paragraph p14 = new Paragraph(coursecode);
		Paragraph p15 = new Paragraph(course);

		p12.setAlignment(Element.ALIGN_CENTER);
		Table1.addCell(p12);
		Table1.addCell(p13);
		Table1.addCell(p14);
		Table1.addCell(p15);
		table2.addCell("");

// For System Degree Prof Course Name and Code ===============================================

////==============Start================== Teaching hour========================================

	PdfPTable tablexx = new PdfPTable(1);
	tablexx.setWidthPercentage(100);
	tablexx.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	tablexx.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	Paragraph p921 = new Paragraph("The syllabus of Yoga for the 1st BHMS students should include the basic concept of Yoga and its philosophy, with a clear idea of the different "
			+ "section of asana, pranayama, kriya and meditation. Total 30 hours of class will include practical training. The students will be trained in "
			+ "understanding the relationship between Yoga and Homoeopathy in a wholistic approach, and the point of application of yoga in part of "
			+ "treatment.", fontTablemain2Heading1);
	tablexx.addCell(p921);
	
	Paragraph p10y = new Paragraph("\nThe topic and respective allotted hours are as follows -", fontTablemain2Heading1);
	tablexx.addCell(p10y);
	

	ArrayList<ArrayList<String>> aList23 = (ArrayList<ArrayList<String>>) List_of_Topic_listYOGA;

	PdfPTable tablex = new PdfPTable(1);
	tablex.setWidthPercentage(100);
	tablex.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	Font fontTableHeadingNonBoldDatax = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
	Paragraph py = new Paragraph();
	Paragraph py1 = new Paragraph();
	Paragraph py2 = new Paragraph();

	PdfPTable table32 = new PdfPTable(3);
	table32.setWidths(new int[] { 50, 250,100 });
	table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

	
	for (int h = 0; h < TH.size(); h++) {
		py = new Paragraph(TH.get(h), fontTableHeading);
		py.setAlignment(Element.ALIGN_CENTER);
		table32.addCell(py);
	}
	table32.setHeaderRows(1); // table first row will be repeated in all pages
	
	for (int i = 0; i < aList23.size(); i++) {
		List<String> l = aList23.get(i);
		 
		for (int j = 0; j < l.size(); j++) {
			py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
			table32.addCell(py);

		}
	}
	
	
	tablex.addCell(table32);

////==============END================== Teaching hour=====================================================
	
// footer========================================================================================================

	Chunk underlinef = new Chunk("RESTRICTED", fontTableHeading);

	underlinef.setUnderline(0.1f, -2f);

	PdfPTable tablefoot = new PdfPTable(3);

	tablefoot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

	tablefoot.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	Phrase pf = new Phrase(underlinef);

	String foot = sysdegprofcorsnamecode.get(0).get("course_name").toString() + "," + sysdegprofcorsnamecode.get(0).get("professional").toString() + " "
			+ sysdegprofcorsnamecode.get(0).get("degree_name").toString() + " (" + sysdegprofcorsnamecode.get(0).get("system_name").toString() + ")";
	tablefoot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

	tablefoot.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

	tablefoot.setWidthPercentage(120);

	pf.add(tablefoot);


	Phrase pf1 = new Phrase();

	Chunk p891 = new Chunk(foot, fontTableHeadingNonBoldData);

	pf1.add(p891);

	pf1.add("\n");


	HeaderFooter footer = new HeaderFooter(pf1, false);

	footer.setAlignment(Element.ALIGN_CENTER);

	footer.setBorder(Rectangle.TOP);

	document.setFooter(footer);

	document.setPageCount(1);

//		System.out.println("Type==" + Type);

	if (Type.equals("L")) {

		document.setPageSize(PageSize.A4); // set document landscape

	}

// end of footer=================================================================================

		// Sys Deg Prof Course code and name
		document.add(Table1);

		// PO
		Phrase singleSN = new Phrase();
		Phrase doubleSN = new Phrase();
		singleSN = new Phrase("\n");
		doubleSN = new Phrase("\n\n");

		// CO

		//Teaching hours
//		document.add(singleSN);
		document.add(tablexx);
//		document.add(singleSN);
		document.add(tablex);
//		document.add(table32);


	}
}
