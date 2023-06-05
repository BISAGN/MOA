package com.AyushEdu.controller.Curriculum;

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
import org.apache.poi.ss.formula.functions.Sumif;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.AyushEdu.controller.DateWithTimestamp.DateWithTimeStampController;
import com.AyushEdu.dao.Curriculum.NCH_Curriculum_Pdf_Dao;
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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class NCH_Download_Curriculum_Pdf extends AbstractPdfView {

	@Autowired
	NCH_Curriculum_Pdf_Dao PARDAO1;

	String Type = "";
	List<String> TH;
	List<String> TH1;
	List<String> TH2;
	List<String> TH3;
	List<String> TH4;
	List<String> TH5;
	List<String> TH6;
	List<String> TH2loc;
	List<String> TH4loc;
	String username = "";
	String role = "";

	String Heading = "";
	String cars1 = null;
	int total = 0;

	List<Map<String, Object>> cars = null;
	List<Map<String, Object>> nonlecact1;
	List<ArrayList<String>> Program_Outcomes_list;
	List<ArrayList<String>> Course_Outcomes_list;
	List<ArrayList<String>> Teaching_hour;
	List<ArrayList<String>> Teaching_Method_list;
	List<ArrayList<String>> TableNon_Lecture_list;
	List<ArrayList<String>> TableEvaluation_Methods_list;
	List<ArrayList<String>> List_of_Topic_list;
	List<ArrayList<String>> List_of_Pratical_list;
	List<ArrayList<String>> table6_number_of_papers_list;
	List<ArrayList<String>> Reference_Resourses_list;
	List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list;
	List<ArrayList<String>> table4_Practical_Learning_Objectives_list;
	List<ArrayList<String>> table6b_term1list;
	List<ArrayList<String>> table6b_term2list;
	List<ArrayList<String>> table6b_term3list;
	List<ArrayList<String>> Paper_Layout;
	List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Distri_Pract_Exam;

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\NSG\\admission.pdf";

	public NCH_Download_Curriculum_Pdf(String Type, List<String> TH, List<String> TH1, List<String> TH2,
			List<String> TH3, List<String> TH4, List<String> TH5, List<String> TH6, List<String> TH2loc,
			List<String> TH4loc, String Heading, String role, List<Map<String, Object>> nonlecact1,
			List<ArrayList<String>> Program_Outcomes_list, List<ArrayList<String>> Course_Outcomes_list,
			List<ArrayList<String>> Teaching_hour, List<ArrayList<String>> Teaching_Method_list,
			List<ArrayList<String>> TableNon_Lecture_list, List<ArrayList<String>> TableEvaluation_Methods_list,
			List<ArrayList<String>> List_of_Topic_list, List<ArrayList<String>> List_of_Pratical_list,
			List<ArrayList<String>> table6_number_of_papers_list,
			List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list,
			List<ArrayList<String>> table4_Practical_Learning_Objectives_list,
			List<ArrayList<String>> Reference_Resourses_list, List<ArrayList<String>> table6b_term1list,
			List<ArrayList<String>> table6b_term2list, List<ArrayList<String>> table6b_term3list,
			List<ArrayList<String>> Paper_Layout,List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List,List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List, List<ArrayList<String>> Distri_Pract_Exam) {

		this.Type = Type;
		this.TH = TH;
		this.TH1 = TH1;
		this.TH2 = TH2;
		this.TH3 = TH3;
		this.TH4 = TH4;
		this.TH5 = TH5;
		this.TH6 = TH6;
		this.TH2loc = TH2loc;
		this.TH4loc = TH4loc;
		this.Heading = Heading;
		this.role = role;
		this.nonlecact1 = nonlecact1;
		this.Program_Outcomes_list = Program_Outcomes_list;
		this.Course_Outcomes_list = Course_Outcomes_list;
		this.Teaching_hour = Teaching_hour;
		this.Teaching_Method_list = Teaching_Method_list;
		this.TableNon_Lecture_list = TableNon_Lecture_list;
		this.TableEvaluation_Methods_list = TableEvaluation_Methods_list;
		this.List_of_Topic_list = List_of_Topic_list;
		this.List_of_Pratical_list = List_of_Pratical_list;
		this.table6_number_of_papers_list = table6_number_of_papers_list;
		this.table2_Learning_Objectives_of_Course_HomUG_list = table2_Learning_Objectives_of_Course_HomUG_list;
		this.table4_Practical_Learning_Objectives_list = table4_Practical_Learning_Objectives_list;
		this.Reference_Resourses_list = Reference_Resourses_list;
		this.table6b_term1list = table6b_term1list;
		this.table6b_term2list = table6b_term2list;
		this.table6b_term3list = table6b_term3list;
		this.Paper_Layout = Paper_Layout;
		this.Table6F_IDistribution_of_Theory_Exam_List = Table6F_IDistribution_of_Theory_Exam_List;
		this.Table6F_IIDistribution_of_Theory_Exam_List = Table6F_IIDistribution_of_Theory_Exam_List;
		this.Distri_Pract_Exam = Distri_Pract_Exam;

	}

//	
//	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request,
//			Object system_name) {
//		
//		document.open();
//	}
//	

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		document.open();
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 25, 1);
		Font fontTableHeadingSubMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
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
//		Chunk underline2 = new Chunk("E - Form Student Report");
//		underline2.setUnderline(0.1f, -2f);

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

//		p.add(underline2);
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
//		for (int h = 0; h < TH.size(); h++) {
//			p = new Paragraph(TH.get(h), fontTableHeadingNonBoldData);
//			p.setAlignment(Element.ALIGN_CENTER);
//			table3.addCell(p);
//		}

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
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);
//		document.setPageCount();
		document.setPageSize(PageSize.A4); // set document landscape
		super.buildPdfMetadata(model, document, request);

//		if (Type.equals("L")) {
//			 document.setPageSize(PageSize.A3.rotate()); // set document landscape
//		}
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
//		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
//		List<Map<String, Object>> nonlecact1 = (List<Map<String, Object>>) model.get("userList");

		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
		List<Map<String, Object>> aList2 = nonlecact1;
//		List<String> l1 = aList.get(0).get("course_code").toString();

//		ArrayList<ArrayList<String>> aList2 = new_list2;
		String l1 = aList2.get(0).get("course_code").toString();
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(110);
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
		table3 = new PdfPTable(18);

		table3.setWidthPercentage(100);
//		table3.setWidths(new int[] { 15, 15, 15, 40, 30, 20 });
//		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//		for (int h = 0; h < TH1.size(); h++) {
//			p = new Paragraph(TH1.get(h), fontTableHeadingNonBoldData);
//			p.setAlignment(Element.ALIGN_CENTER);
//			table3.addCell(p);
//		}

		Font fontTablemainHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
		Font fontTablemain2Heading = FontFactory.getFont(FontFactory.defaultEncoding, 18);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);

		PdfPTable TestTable = new PdfPTable(1);
		TestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		TestTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		TestTable.addCell("");
		TestTable.addCell("");

		PdfPTable tableleft3 = new PdfPTable(1);
		tableleft3.setWidthPercentage(100);
		tableleft3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Chunk underline = new Chunk(" " + nonlecact1.get(0).get("professional").toString() + "" + " "
				+ nonlecact1.get(0).get("system_name").toString() + "", fontTablemainHeading);
		Chunk underline1 = new Chunk(" (" + nonlecact1.get(0).get("degree_name").toString() + ")",
				fontTablemainHeading);
		Chunk underline2 = new Chunk("Subject Code: " + nonlecact1.get(0).get("course_code").toString() + "",
				fontTablemain2Heading);
		Chunk underline3 = new Chunk(" " + nonlecact1.get(0).get("course_name").toString() + "", fontTablemainHeading);
		Paragraph p12 = new Paragraph(underline);
		Paragraph p13 = new Paragraph(underline1);
		Paragraph p14 = new Paragraph(underline2);
		Paragraph p15 = new Paragraph(underline3);
		p12.setAlignment(Element.ALIGN_CENTER);
		TestTable.addCell(p12);
		TestTable.addCell("");
		TestTable.addCell(p13);
		TestTable.addCell("");
		TestTable.addCell(p14);
		TestTable.addCell("");
		TestTable.addCell("");
		TestTable.addCell("");
		TestTable.addCell("");
		TestTable.addCell("");
		TestTable.addCell(p15);
		TestTable.addCell("");
		TestTable.addCell("");
		tableleft3.addCell("");

//		table3.setHeaderRows(1); // table first row will be repeated in all pages

//		for (int i = 0; i < aList.size(); i++) {
//			List<String> l = aList.get(i);
//			for (int j = 0; j < l.size(); j++) {
//				p = new Paragraph(l.get(j),fontTableHeadingNonBoldDataSize);
//				table3.addCell(p);
//			}
//		}

// Strt Program Outcomes========================================

		PdfPTable tableref7 = new PdfPTable(1);
		tableref7.setWidthPercentage(100);
		tableref7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underliners = new Chunk("PROGRAMME OUTCOMES :- ", fontTableHeading);
		tableref7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase po1 = new Phrase(underliners);

		Paragraph cellr7 = new Paragraph(po1);
		cellr7.setAlignment(Element.ALIGN_CENTER);

		Font fontTableHeadingNonBoldDataref7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

		Phrase ref = new Phrase();
		String refrences = "";
		int ser = 1;
		ArrayList<ArrayList<String>> resList = (ArrayList<ArrayList<String>>) Program_Outcomes_list;
		for (int i = 0; i < resList.size(); i++) {
			refrences += "\n" + " " + ser + ". " + resList.get(i).get(0);
			ser++;
		}
		ref = new Phrase(refrences, fontTableHeadingNonBoldDataref7);

// End Program Outcomes========================================

// Strt COURSE OUTCOMES========================================

		PdfPTable tablerefadd7 = new PdfPTable(1);
		tablerefadd7.setWidthPercentage(100);
		tablerefadd7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underliners1 = new Chunk("COURSE OUTCOMES :- ", fontTableHeading);
		tablerefadd7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase add1 = new Phrase(underliners1);

		Paragraph celladd7 = new Paragraph(add1);
		celladd7.setAlignment(Element.ALIGN_CENTER);

		Font fontTableHeadingNonBoldDataadd7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

		Phrase add_course = new Phrase();
		String add_course1 = "";
		int ser1 = 1;
		ArrayList<ArrayList<String>> addList = (ArrayList<ArrayList<String>>) Course_Outcomes_list;
		for (int i = 0; i < addList.size(); i++) {
			add_course1 += "\n" + " " + ser1 + ". " + addList.get(i).get(0);
			ser1++;
		}
		add_course = new Phrase(add_course1, fontTableHeadingNonBoldDataadd7);

// End COURSE OUTCOMES========================================

//		==============Start================== Teaching hour

		PdfPTable tablexx = new PdfPTable(1);
		tablexx.setWidthPercentage(100);
		tablexx.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline4 = new Chunk(" TEACHING HOURS ", fontTableHeading);
		tablexx.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t1 = new Phrase(underline4);

		Paragraph cell1 = new Paragraph(t1);
		cell1.setAlignment(Element.ALIGN_CENTER);

		tablexx.addCell(t1);

		ArrayList<ArrayList<String>> aList23 = (ArrayList<ArrayList<String>>) Teaching_hour;

//		System.err.println("aList2"+aList2);
		PdfPTable tablex = new PdfPTable(1);
		tablex.setWidthPercentage(100);
		tablex.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatax = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Paragraph py = new Paragraph();
		Paragraph py1 = new Paragraph();
		Paragraph py2 = new Paragraph();

		PdfPTable table32 = new PdfPTable(4);
//		table32.setWidthPercentage(100);
//		table32.setWidths(new int[] { 15, 150, 50 });
		table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH.size(); h++) {
			py = new Paragraph(TH.get(h), fontTableHeading);
			py.setAlignment(Element.ALIGN_CENTER);
			table32.addCell(py);
			System.err.println("-------" + py);
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

//		==============END================== Teaching hour

// Strt TEACHING LEARNING METHODS========================================

		PdfPTable tableTec_Learning = new PdfPTable(1);
		tableTec_Learning.setWidthPercentage(100);
		tableTec_Learning.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlinerTec = new Chunk("TEACHING LEARNING METHODS :- ", fontTableHeading);
		tableTec_Learning.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase TEC1 = new Phrase(underlinerTec);

		Paragraph cellTec7 = new Paragraph(TEC1);
		cellTec7.setAlignment(Element.ALIGN_CENTER);

		Font fontTableHeadingNonBoldDataTEC7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

		Phrase Tec_Learning = new Phrase();
		String Tec_Learning1 = "";
		int ser5 = 1;
		ArrayList<ArrayList<String>> TecList = (ArrayList<ArrayList<String>>) Teaching_Method_list;
		for (int i = 0; i < TecList.size(); i++) {
			Tec_Learning1 += "\n" + " " + ser5 + ". " + TecList.get(i).get(0);
			ser5++;
		}
		Tec_Learning = new Phrase(Tec_Learning1, fontTableHeadingNonBoldDataTEC7);

//End TEACHING LEARNING METHODS=======================================		

// Start Non Lecture Teaching Learning methods===============================

		PdfPTable tableNon_Lecture = new PdfPTable(1);
		tableNon_Lecture.setWidthPercentage(100);
		tableNon_Lecture.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline5 = new Chunk("Non-Lecture Activities", fontTableHeading);
		tableNon_Lecture.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase tNon = new Phrase(underline5);

		Paragraph cell = new Paragraph(tNon);
		cell.setAlignment(Element.ALIGN_CENTER);

		tableNon_Lecture.addCell(tNon);

		ArrayList<ArrayList<String>> aListNon = (ArrayList<ArrayList<String>>) TableNon_Lecture_list;

//		System.err.println("aList2"+aList2);
		PdfPTable tableNon_Lecture1 = new PdfPTable(1);
		tableNon_Lecture1.setWidthPercentage(100);
		tableNon_Lecture1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableNon_Lecture.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatax1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph nt = new Paragraph();
		Paragraph nt1 = new Paragraph();
		Paragraph nt2 = new Paragraph();

		PdfPTable tablent1 = new PdfPTable(3);
		tablent1.setWidthPercentage(100);
		tablent1.setWidths(new int[] { 15, 150, 50 });
		tablent1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		nt = new Paragraph(TH3.get(0), fontTableHeading);
		nt.setAlignment(Element.ALIGN_CENTER);
		tablent1.addCell(nt);
		nt1 = new Paragraph(TH3.get(1), fontTableHeading);
		nt1.setAlignment(Element.ALIGN_CENTER);
		tablent1.addCell(nt1);
		nt2 = new Paragraph(TH3.get(2), fontTableHeading);
		nt2.setAlignment(Element.ALIGN_CENTER);
		tablent1.addCell(nt2);

		tablent1.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aListNon.size(); i++) {
			List<String> l = aListNon.get(i);
			for (int j = 0; j < l.size(); j++) {
				nt = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tablent1.addCell(nt);

			}
		}

		tableNon_Lecture1.addCell(tablent1);

// End  Non Lecture Teaching Learning methods==============================

// Start Evaluation Methods for Periodical// Assessment===============================

		ArrayList<ArrayList<String>> T6DList = (ArrayList<ArrayList<String>>) TableEvaluation_Methods_list;
		PdfPTable table6Dnlaclink = new PdfPTable(1);
		table6Dnlaclink.setWidthPercentage(100);
		table6Dnlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6Dnlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable table6Dnlac = new PdfPTable(1);
		table6Dnlac.setWidthPercentage(100);
		table6Dnlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6D = new Chunk("Evaluation Methods for Assessment", fontTableHeading);
		table6Dnlac.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6D = new Phrase(underline6D);

		Paragraph cell6D = new Paragraph(t6D);
		cell6D.setAlignment(Element.ALIGN_CENTER);

		table6Dnlac.addCell(t6D);

		Paragraph pt6D1 = new Paragraph();
		Paragraph pt6D = new Paragraph();
		Paragraph pt6D2 = new Paragraph();
//			Paragraph pt53 = new Paragraph();
		PdfPTable table6Dnlacdata = new PdfPTable(2);
//			table32.setWidthPercentage(100);
		table6Dnlacdata.setWidths(new int[] { 15, 150 });
		table6Dnlacdata.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			for (int h = 0; h < TH1.size(); h++) {
		pt6D1 = new Paragraph(TH2.get(0), fontTableHeading);
		pt6D1.setAlignment(Element.ALIGN_CENTER);
		table6Dnlacdata.addCell(pt6D1);
		pt6D2 = new Paragraph(TH2.get(1), fontTableHeading);
		pt6D2.setAlignment(Element.ALIGN_CENTER);
		table6Dnlacdata.addCell(pt6D2);
//				pt53 = new Paragraph(TH5.get(2), fontTableHeading);
//				pt53.setAlignment(Element.ALIGN_CENTER);
//				table5nlacdata.addCell(pt53);
//			}

		table6Dnlacdata.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < T6DList.size(); i++) {
			List<String> l = T6DList.get(i);
			for (int j = 0; j < l.size(); j++) {
				pt6D = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table6Dnlacdata.addCell(pt6D);
			}
		}

//		table6Dnlaclink.addCell(table6Dnlac);
		table6Dnlaclink.addCell(table6Dnlacdata);

// End  Evaluation Methods for Periodical// Assessment===============================

		// Table 2: Learning objectives (Theory) of Course
		// AyUG-RS============================

//						ArrayList<List<String>> t3List = (ArrayList<List<String>>) model.get("userList");
		ArrayList<ArrayList<String>> t3HOMList = (ArrayList<ArrayList<String>>) table2_Learning_Objectives_of_Course_HomUG_list;
//						List<String> l1 = aList.get(0);
		PdfPTable table3HOMHeader = new PdfPTable(1);
		table3HOMHeader.setWidthPercentage(100);
		table3HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlineHOM = new Chunk("Table 2-Learning Objectives (Theory) of Course HomUG-OM-I ", fontTableHeading);
		table3HOMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t3HOM = new Phrase(underlineHOM);

		Paragraph cell3HOM = new Paragraph(t1);
		cell3HOM.setAlignment(Element.ALIGN_CENTER);

		table3HOMHeader.addCell(t3HOM);

		Paragraph pt3HOM = new Paragraph();
		PdfPTable table3HOM = new PdfPTable(12);
		table3HOM.setWidthPercentage(100);
		table3HOM.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH2loc.size(); h++) {
			pt3HOM = new Paragraph(TH2loc.get(h), fontTableHeading);
			pt3HOM.setAlignment(Element.ALIGN_CENTER);
			table3HOM.addCell(pt3HOM);
		}

		String papertype11 = "";
		String topictype11 = "";

//						table3.setHeaderRows(1); // table first row will be repeated in all pages

		System.err.println("t3HOMList===========================" + t3HOMList);

		for (int i = 0; i < t3HOMList.size(); i++) {
			List<String> l = t3HOMList.get(i);
			for (int j = 0; j < l.size(); j++) {

				if (i == 0 && j == 0) {
					papertype11 = l.get(j);
					Paragraph ptt32HOM = new Paragraph(papertype11, fontTableHeading);
					ptt32HOM.setAlignment(Element.ALIGN_CENTER);
					table3HOM.getDefaultCell().setColspan(12);
					table3HOM.addCell(ptt32HOM);
					table3HOM.getDefaultCell().setColspan(1);

				}
				if (j == 0) {
					if (!papertype11.equals(l.get(j))) {
						papertype11 = l.get(j);
						System.err.println("papertype11===================" + papertype11);
						Paragraph ptt32HOM = new Paragraph(papertype11, fontTableHeading);
						ptt32HOM.setAlignment(Element.ALIGN_CENTER);
						table3HOM.getDefaultCell().setColspan(13);
						table3HOM.addCell(ptt32HOM);
						table3HOM.getDefaultCell().setColspan(1);
					}
				}
				if (j != 0) {
					Paragraph ptt32HOM = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table3HOM.addCell(ptt32HOM);
				}
			}
		}

		table.addCell(table3HOM);

		// end of Table 2: Learning objectives (Theory) of Course

//////////////Start Table : Contents of Course =======================================

	PdfPTable table2MHeader = new PdfPTable(1);
	table2MHeader.setWidthPercentage(100);
	table2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	Chunk underline51 = new Chunk("THEORY ", fontTableHeading);
	table2MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	Phrase t2 = new Phrase(underline51);

	Paragraph cell2 = new Paragraph(t2);
	cell2.setAlignment(Element.ALIGN_CENTER);

	table2MHeader.addCell(t2);

	ArrayList<ArrayList<String>> t2List = (ArrayList<ArrayList<String>>) List_of_Topic_list;

	System.err.println("fgfdbg fd bg--------" + t2List);
	PdfPTable table2link = new PdfPTable(1);
	table2link.setWidthPercentage(190);
	table2link.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	table2link.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	Font fontTableHeadingNonBoldData2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

	Paragraph pt2 = new Paragraph();
	PdfPTable table2Content_Course_AyUGRS = new PdfPTable(4);
	table2Content_Course_AyUGRS.setWidthPercentage(100);
	table2Content_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	for (int h = 0; h < TH4.size(); h++) {
		pt2 = new Paragraph(TH4.get(h), fontTableHeading);
		pt2.setAlignment(Element.ALIGN_CENTER);
		table2Content_Course_AyUGRS.addCell(pt2);

	}

	// add paper I in table 1st row
//		String papertype = t2List.get(0).get(6);
//		Paragraph pt3 = new Paragraph(papertype,fontTableHeading);

	PdfPTable table2 = new PdfPTable(4);
	table2.setWidthPercentage(100);
	table2.setWidths(new int[] { 25, 250, 50, 50 });
	table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//		for (int h = 0; h < TH1.size(); h++) {

	Paragraph py2y = new Paragraph();
	Paragraph py21 = new Paragraph();
	Paragraph py22 = new Paragraph();
	Paragraph py23 = new Paragraph();
	Paragraph py24 = new Paragraph();
//		Paragraph py25 = new Paragraph();
	py2y = new Paragraph(TH4.get(0), fontTableHeading);
	py2y.setAlignment(Element.ALIGN_CENTER);
	table2.addCell(py2y);
	py21 = new Paragraph(TH4.get(1), fontTableHeading);
	py21.setAlignment(Element.ALIGN_CENTER);
	table2.addCell(py21);
	py22 = new Paragraph(TH4.get(2), fontTableHeading);
	py22.setAlignment(Element.ALIGN_CENTER);
	table2.addCell(py22);
	py23 = new Paragraph(TH4.get(3), fontTableHeading);
	py23.setAlignment(Element.ALIGN_CENTER);
	table2.addCell(py23);

	String papertype = "";

//			table2.setHeaderRows(1); // table first row will be repeated in all pages
	table2link.setWidthPercentage(150);
	for (int i = 0; i < t2List.size(); i++) {
		List<String> l = t2List.get(i);
		System.err.println("\n*****"+l);
		for (int j = 0; j < l.size(); j++) {
			
			if (i == 0 && j == 0) {
				papertype = l.get(1);
				Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
				table2.getDefaultCell().setColspan(4);
				table2.addCell(pt3);
				table2.getDefaultCell().setColspan(1);
				
				py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table2.addCell(py2);
			}else if(j == 0 && papertype.equals(l.get(1))) {
				py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table2.addCell(py2);
			}else if(j == 0 && !papertype.equals(l.get(1))) {
				
				papertype = l.get(1);
				Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
				table2.getDefaultCell().setColspan(4);
				table2.addCell(pt3);
				table2.getDefaultCell().setColspan(1);
				
				py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table2.addCell(py2);
				
			}else if (j == 1) {
				if (!papertype.equals(l.get(j))) {
					papertype = l.get(j);
					Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
					table2.getDefaultCell().setColspan(4);
					table2.addCell(pt3);
					table2.getDefaultCell().setColspan(1);
				}
			}else {
				py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table2.addCell(py2);
			}

		}
	}

	table2link.addCell(table2Content_Course_AyUGRS);



//Start Table : Contents of Course =======================================				

		// Start Table 3: Contents of Course
	
		
		
	// Start Table 3: Contents of Course
			// pratical=======================================

			PdfPTable table21MHeader1 = new PdfPTable(1);
			table21MHeader1.setWidthPercentage(100);
			table21MHeader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline511 = new Chunk("PRACTICAL ", fontTableHeading);
			table21MHeader1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t21 = new Phrase(underline511);

			Paragraph cell21 = new Paragraph(t21);
			cell21.setAlignment(Element.ALIGN_CENTER);

			table21MHeader1.addCell(t21);

			ArrayList<ArrayList<String>> t2List2 = (ArrayList<ArrayList<String>>) List_of_Pratical_list;

//				System.err.println(""+t2List);
			PdfPTable table21link1 = new PdfPTable(1);
			table21link1.setWidthPercentage(190);
			table21link1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table21link1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Font fontTableHeadingNonBoldData21 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

			Paragraph pt21 = new Paragraph();
			PdfPTable table21Content_Course_AyUGRS1 = new PdfPTable(6);
			table21Content_Course_AyUGRS1.setWidthPercentage(100);
			table21Content_Course_AyUGRS1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			for (int h = 0; h < TH5.size(); h++) {
				pt21 = new Paragraph(TH5.get(h), fontTableHeading);
				pt21.setAlignment(Element.ALIGN_CENTER);
				table21Content_Course_AyUGRS1.addCell(pt21);

			}

			// add paper I in table 1st row
//				String papertype = t2List.get(0).get(6);
//				Paragraph pt3 = new Paragraph(papertype,fontTableHeading);

			PdfPTable table21 = new PdfPTable(4);
//				table2.getDefaultCell().setColspan(6);
//				table2.addCell(pt3);
//				table2.getDefaultCell().setColspan(1);
			table21.setWidthPercentage(100);
			table21.setWidths(new int[] { 25, 250, 50, 50 });
			table21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//				for (int h = 0; h < TH1.size(); h++) {

			Paragraph py3y = new Paragraph();
			Paragraph py31 = new Paragraph();
			Paragraph py32 = new Paragraph();
			Paragraph py33 = new Paragraph();
			Paragraph py34 = new Paragraph();
			Paragraph py35 = new Paragraph();
			py2y = new Paragraph(TH5.get(0), fontTableHeading);
			py2y.setAlignment(Element.ALIGN_CENTER);
			table21.addCell(py2y);
			py21 = new Paragraph(TH5.get(1), fontTableHeading);
			py21.setAlignment(Element.ALIGN_CENTER);
			table21.addCell(py21);
			py22 = new Paragraph(TH5.get(2), fontTableHeading);
			py22.setAlignment(Element.ALIGN_CENTER);
			table21.addCell(py22);
			py23 = new Paragraph(TH5.get(3), fontTableHeading);
			py23.setAlignment(Element.ALIGN_CENTER);
			table21.addCell(py23);
//				py24 = new Paragraph(TH5.get(4), fontTableHeading);
//				py24.setAlignment(Element.ALIGN_CENTER);
//				table2.addCell(py24);
//				py25 = new Paragraph(TH5.get(5), fontTableHeading);
//				py25.setAlignment(Element.ALIGN_CENTER);
//				table2.addCell(py25);

			String papertype1 = "";

//					table2.setHeaderRows(1); // table first row will be repeated in all pages
			table2link.setWidthPercentage(150);
			
			
			for (int i = 0; i < t2List2.size(); i++) {
				List<String> l = t2List2.get(i);
				System.err.println("\n*****"+l);
				for (int j = 0; j < l.size(); j++) {
					
					if (i == 0 && j == 0) {
						papertype = l.get(1);
						Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
						table21.getDefaultCell().setColspan(4);
						table21.addCell(pt3);
						table21.getDefaultCell().setColspan(1);
						
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table21.addCell(py2);
					}else if(j == 0 && papertype.equals(l.get(1))) {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table21.addCell(py2);
					}else if(j == 0 && !papertype.equals(l.get(1))) {
						
						papertype = l.get(1);
						Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
						table21.getDefaultCell().setColspan(4);
						table21.addCell(pt3);
						table21.getDefaultCell().setColspan(1);
						
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table21.addCell(py2);
						
					}else if (j == 1) {
						if (!papertype.equals(l.get(j))) {
							papertype = l.get(j);
							Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
							table21.getDefaultCell().setColspan(4);
							table21.addCell(pt3);
							table21.getDefaultCell().setColspan(1);
						}
					}else {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table21.addCell(py2);
					}

				}
			}
			

			table21link1.addCell(table21Content_Course_AyUGRS1);

//Start Table 3: Contents of Course pratical=======================================		

		// Practical learning objectives ==================================

//				ArrayList<List<String>> t3List = (ArrayList<List<String>>) model.get("userList");
		ArrayList<ArrayList<String>> t4HOMList = (ArrayList<ArrayList<String>>) table4_Practical_Learning_Objectives_list;
//				List<String> l1 = aList.get(0);
		PdfPTable table4HOMHeader = new PdfPTable(1);
		table4HOMHeader.setWidthPercentage(100);
		table4HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlineHOM1 = new Chunk("Table 4-Practical Learning Objectives ", fontTableHeading);
		table4HOMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t4HOM = new Phrase(underlineHOM1);

		Paragraph cell4HOM = new Paragraph(t1);
		cell4HOM.setAlignment(Element.ALIGN_CENTER);

		table4HOMHeader.addCell(t4HOM);

		Paragraph pt4HOM = new Paragraph();
		PdfPTable table4HOM = new PdfPTable(12);
		table4HOM.setWidthPercentage(100);
		table4HOM.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
			
			
		for (int h = 0; h < TH4loc.size(); h++) {
			pt4HOM = new Paragraph(TH4loc.get(h), fontTableHeading);
			pt4HOM.setAlignment(Element.ALIGN_CENTER);
			table4HOM.addCell(pt4HOM);
		}

		String papertype111 = "";
		String topictype111 = "";

//				table3.setHeaderRows(1); // table first row will be repeated in all pages

		System.err.println("t4HOMList===========================" + t4HOMList);

		for (int i = 0; i < t4HOMList.size(); i++) {
			List<String> l = t4HOMList.get(i);
			for (int j = 0; j < l.size(); j++) {

//				if (i == 0 && j == 0) {
//					papertype111 = l.get(j);
//					Paragraph ptt32HOM = new Paragraph(papertype111, fontTableHeading);
//					ptt32HOM.setAlignment(Element.ALIGN_CENTER);
//					table4HOM.getDefaultCell().setColspan(12);
//					table4HOM.addCell(ptt32HOM);
//					table4HOM.getDefaultCell().setColspan(1);
//
//				}
//				if (j == 0) {
//					if (!papertype111.equals(l.get(j))) {
//						papertype111 = l.get(j);
//						System.err.println("papertype11===================" + papertype111);
//						Paragraph ptt32HOM = new Paragraph(papertype111, fontTableHeading);
//						ptt32HOM.setAlignment(Element.ALIGN_CENTER);
//						table4HOM.getDefaultCell().setColspan(13);
//						table4HOM.addCell(ptt32HOM);
//						table4HOM.getDefaultCell().setColspan(1);
//					}
//				}
				if (j != 0) {
					Paragraph ptt32HOM = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table4HOM.addCell(ptt32HOM);
				}
			}
		}

		table.addCell(table4HOM);
		
		// end of Practical Learning objectives ===============================

//start of  Number of Papers and Marks Distribution============================

		PdfPTable table60Dnlach1 = new PdfPTable(1);
		table60Dnlach1.setWidthPercentage(100);
		table60Dnlach1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6DH = new Chunk("Table- Assessment Summary", fontTableHeading);
		Chunk underline6DH2 = new Chunk("Number of papers and Mark Distribution", fontTableHeading);
		table60Dnlach1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase t6DH = new Phrase(underline6DH);
		Phrase t6DH2 = new Phrase(underline6DH2);
		Paragraph cell6DH = new Paragraph(t6DH);
		cell6DH.setAlignment(Element.ALIGN_CENTER);
		Paragraph cell6DH2 = new Paragraph(t6DH2);
		cell6DH2.setAlignment(Element.ALIGN_CENTER);

		table60Dnlach1.addCell(t6DH);
		table60Dnlach1.addCell(t6DH2);

		PdfPTable table6nop1 = new PdfPTable(1);
		table6nop1.setWidthPercentage(100);
		table6nop1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6A = new Chunk("6 A - Number of Papers and Marks Distribution", fontTableHeading);
		table6nop1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6A = new Phrase(underline6A);

		Paragraph cell6A = new Paragraph(t6A);
		cell6A.setAlignment(Element.ALIGN_CENTER);

		table6nop1.addCell(t6A);

		ArrayList<ArrayList<String>> nplist = (ArrayList<ArrayList<String>>) table6_number_of_papers_list;
		System.err.println("nplist==========================" + nplist);
		PdfPTable table6A = new PdfPTable(1);
		table6A.setWidthPercentage(100);
		table6A.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6A.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pt6a = new Paragraph();
		Paragraph pt6a1 = new Paragraph();
		Paragraph pt6a2 = new Paragraph();
		Paragraph pt6a3 = new Paragraph();
		Paragraph pt6a4 = new Paragraph();
		Paragraph pt6a5 = new Paragraph();
		Paragraph pt6a6 = new Paragraph();
		Paragraph pt6a7 = new Paragraph();
		Paragraph pt6a8 = new Paragraph();
		Paragraph pt6a9 = new Paragraph();
		Paragraph pt6a10 = new Paragraph();

		PdfPTable table6anop1 = new PdfPTable(10);
//			table32.setWidthPercentage(100);
		table6anop1.setWidths(new int[] { 8, 24, 12, 12, 15, 8, 20, 6, 8, 10 });
		table6anop1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		pt6a = new Paragraph(TH6.get(0), fontTableHeading);
		pt6a.setAlignment(Element.ALIGN_CENTER);

		pt6a1 = new Paragraph(TH6.get(1), fontTableHeading);
		pt6a1.setAlignment(Element.ALIGN_CENTER);

		pt6a2 = new Paragraph(TH6.get(2), fontTableHeading);
		pt6a2.setAlignment(Element.ALIGN_CENTER);

		pt6a3 = new Paragraph(TH6.get(3), fontTableHeading);
		pt6a3.setAlignment(Element.ALIGN_CENTER);

		pt6a4 = new Paragraph(TH6.get(4), fontTableHeading);
		pt6a4.setAlignment(Element.ALIGN_CENTER);

		pt6a5 = new Paragraph(TH6.get(5), fontTableHeading);
		pt6a5.setAlignment(Element.ALIGN_CENTER);

		pt6a6 = new Paragraph(TH6.get(6), fontTableHeading);
		pt6a6.setAlignment(Element.ALIGN_CENTER);

		pt6a7 = new Paragraph(TH6.get(7), fontTableHeading);
		pt6a7.setAlignment(Element.ALIGN_CENTER);

		pt6a8 = new Paragraph(TH6.get(8), fontTableHeading);
		pt6a8.setAlignment(Element.ALIGN_CENTER);

		pt6a9 = new Paragraph(TH6.get(9), fontTableHeading);
		pt6a9.setAlignment(Element.ALIGN_CENTER);
//
//			pt6a10 = new Paragraph(TH6.get(10), fontTableHeading);
//			pt6a10.setAlignment(Element.ALIGN_CENTER);

		
		if(nplist.size() > 0) {
			
			table6anop1.getDefaultCell().setRowspan(2);
			table6anop1.addCell(pt6a);
			table6anop1.addCell(pt6a1);
			table6anop1.addCell(pt6a2);
			table6anop1.addCell(pt6a3);
			table6anop1.getDefaultCell().setRowspan(1);
			table6anop1.getDefaultCell().setColspan(5);
			table6anop1.addCell(pt6a10);
			table6anop1.getDefaultCell().setColspan(1);
			table6anop1.getDefaultCell().setRowspan(2);
			table6anop1.addCell(pt6a9);
			table6anop1.getDefaultCell().setRowspan(1);
			table6anop1.addCell(pt6a4);
			table6anop1.addCell(pt6a5);
			table6anop1.addCell(pt6a6);
			table6anop1.addCell(pt6a7);
			table6anop1.addCell(pt6a8);
			
		int sub_total = 0;
		int grand_total = 0;
		for (int i = 0; i < nplist.size(); i++) {
			List<String> l = nplist.get(i);
			for (int j = 0; j < l.size(); j++) {
				if (j > 3 && j < 8) {
					sub_total = sub_total + Integer.parseInt(l.get(j));
				}
				if (j == 3) {
					grand_total = grand_total + Integer.parseInt(l.get(j));
				}
				pt6a = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table6anop1.addCell(pt6a);
			}
		}
		
		grand_total = grand_total + sub_total;
		
		pt6a = new Paragraph(String.valueOf(sub_total), fontTableHeadingNonBoldData);
		table6anop1.addCell(pt6a);
		pt6a = new Paragraph(String.valueOf(grand_total), fontTableHeadingNonBoldData);
		table6anop1.addCell(pt6a);

		table6A.addCell(table6anop1);
		}
		// end of Number of Papers and Marks
		// Distribution============================

//6-B===================== Start  Scheme of Assessment (formative and Summative)===============================================================

		PdfPTable table6b = new PdfPTable(1);
		table6b.setWidthPercentage(100);
		table6b.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6B = new Chunk("PA: Periodical Assessment; TT: Term Test; UE: University Examinations",
				fontTableHeadingNonBoldData);
		table6b.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6B = new Phrase(underline6B);

		Paragraph cell6B = new Paragraph(t6B);
		cell6A.setAlignment(Element.ALIGN_CENTER);

		table6b.addCell(t6B);

		PdfPTable table6b1 = new PdfPTable(1);
		table6b1.setWidthPercentage(100);
		table6b1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6B1 = new Chunk("6 B - Scheme of Assessment (formative and Summative) ", fontTableHeading);
		table6b1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6B1 = new Phrase(underline6B1);

		Paragraph cell6B1 = new Paragraph(t6B1);
		cell6B1.setAlignment(Element.ALIGN_CENTER);

		table6b1.addCell(t6B1);

		PdfPTable table6bsa = new PdfPTable(5);
		Paragraph p6b;
		table6bsa.setWidthPercentage(100);
		table6bsa.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6bsa.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		Paragraph p6b2 = new Paragraph("SR.NO.", fontTableHeading);
		p6b2.setAlignment(Element.ALIGN_CENTER);
		Paragraph p6b3 = new Paragraph("PROFESSIONAL\n" + "COURSE", fontTableHeading);
		p6b3.setAlignment(Element.ALIGN_CENTER);
//			Paragraph p6b4 = new Paragraph("DURATION OF PROFESSIONAL COURSE", fontTableHeading);
//			p6b4.setAlignment(Element.ALIGN_CENTER);
		Paragraph p6b5 = new Paragraph("First Term\n" + "(1-6 Months)", fontTableHeading);
		p6b5.setAlignment(Element.ALIGN_CENTER);
		Paragraph p6b6 = new Paragraph("Second Term\n" + "(7-12 Months)", fontTableHeading);
		p6b6.setAlignment(Element.ALIGN_CENTER);
		Paragraph p6b7 = new Paragraph("Third Term\n" + "(13-18 Months)", fontTableHeading);
		p6b7.setAlignment(Element.ALIGN_CENTER);

		Paragraph p6b8 = new Paragraph("1");

		Paragraph p6b9 = new Paragraph("First");
		Paragraph p6b10 = null;
		Paragraph p6b11 = null;
		Paragraph p6b12 = null;
		
		if(table6b_term1list.get(0).size() > 0) {
		p6b10 = new Paragraph(table6b_term1list.get(0).get(1));
		}
		
		if(table6b_term1list.get(0).size() > 0) {
			p6b11 = new Paragraph(table6b_term2list.get(0).get(1));
			}
		
		if(table6b_term1list.get(0).size() > 0) {
			p6b12 = new Paragraph(table6b_term3list.get(0).get(1));
			}
		

//				table6b_term1list

		table6bsa.getDefaultCell().setRowspan(2);
//				table6bsa.addCell(p6b2);
//				table6bsa.addCell(p6b3);
		table6bsa.getDefaultCell().setRowspan(1);
		table6bsa.getDefaultCell().setColspan(4);
//				table6bsa.addCell(p6b4);
		table6bsa.getDefaultCell().setColspan(1);

//				table6bsa.addCell(p6b5);
//				table6bsa.addCell(p6b6);
//				table6bsa.addCell(p6b7);

	

		// 6-B===================== End Scheme of Assessment (formative and
		// Summative)===============================================================

		/////////////////// Paper Layout////////////////===============================
		ArrayList<ArrayList<String>> TPList = (ArrayList<ArrayList<String>>) Paper_Layout;
		PdfPTable tab92 = new PdfPTable(3);
		tab92.setWidthPercentage(100);
		tab92.setWidths(new int[] { 15, 50, 20 });
		tab92.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab92.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref2 = new PdfPTable(1);
		tableref2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref2.setWidthPercentage(100);
		tableref2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//				tableref2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph p921 = new Paragraph("Paper Layout", fontTableHeading);
		tableref2.addCell(p921);

		Paragraph p922 = new Paragraph();
		Paragraph p923 = new Paragraph();
		Paragraph p924 = new Paragraph();
		
		if(TPList.size() > 0) {
			
			table6bsa.addCell(p6b2);
			table6bsa.addCell(p6b3);
//				table6bsa.addCell(p6b4);
			table6bsa.addCell(p6b5);
			table6bsa.addCell(p6b6);
			table6bsa.addCell(p6b7);

			table6bsa.addCell(p6b8);
			table6bsa.addCell(p6b9);
			table6bsa.addCell(p6b10);
			table6bsa.addCell(p6b11);
			table6bsa.addCell(p6b12);
//			Paragraph p7568;
		

		for (int i = 0; i < TPList.size(); i++) {
			List<String> l = TPList.get(i);
			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tab92.addCell(py);

			}
		}
		}
		// table 6FI-PaperI==========zarna=================================
				PdfPTable table6FMHeader = new PdfPTable(1);
				table6FMHeader.setWidthPercentage(100);
				table6FMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline6F = new Chunk("6 F – I - Distribution of Theory exam ", fontTableHeading);
				table6FMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase t6f = new Phrase(underline6F);

				Paragraph cell6f = new Paragraph(t6f);
				cell6f.setAlignment(Element.ALIGN_CENTER);

				table6FMHeader.addCell(t6f);

				PdfPTable table6FI = new PdfPTable(7);
				Paragraph p98;
				table6FI.setWidthPercentage(100);
				table6FI.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table6FI.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

				Paragraph p6F1 = new Paragraph(" ", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F2 = new Paragraph("Paper I", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F3 = new Paragraph(
						"D\n" + "Type of Questions\n" + "“Yes” can be asked.\n" + "“No” should not be asked.",
						fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);

				Paragraph p6F4 = new Paragraph("A\n" + "List of Topics", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F5 = new Paragraph("B\n" + "Term", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F6 = new Paragraph("C\n" + "Marks", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F7 = new Paragraph("MCQ\n" + "(1 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F8 = new Paragraph("SAQ\n" + "(5 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6F9 = new Paragraph("LAQ\n" + "(10 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);

			

				Paragraph sixftb1px = new Paragraph();

				ArrayList<ArrayList<String>> sixf1 = (ArrayList<ArrayList<String>>) Table6F_IDistribution_of_Theory_Exam_List;
				String yesNo = "";
					System.err.println("=-=-=-=-=-=-=-=-=-=-=-="+sixf1);
					if(sixf1.size() > 0) {
						table6FI.addCell(p6F1);
						table6FI.addCell(p6F2);
						// table6FI.addCell(p6F1);
						table6FI.addCell(p6F1);
						table6FI.addCell(p6F1);
						table6FI.getDefaultCell().setColspan(3);
						table6FI.addCell(p6F3);
						table6FI.getDefaultCell().setColspan(1);
						table6FI.addCell(p6F1);
						table6FI.addCell(p6F4);
						// table6FI.addCell(p6F1);
						table6FI.addCell(p6F5);
						table6FI.addCell(p6F6);
						table6FI.addCell(p6F7);
						table6FI.addCell(p6F8);
						table6FI.addCell(p6F9);
				for (int i = 0; i < sixf1.size(); i++) {
					List<String> l = sixf1.get(i);
					for (int j = 0; j < l.size(); j++) {
						if (j == 4 || j == 5 || j == 6) {
							if (l.get(j).equals("0")) {
								yesNo = "NO";
							}
							if (l.get(j).equals("1")) {
								yesNo = "YES";
							}
							sixftb1px = new Paragraph(yesNo, fontTableHeadingNonBoldData);
							table6FI.addCell(sixftb1px);
						} else {
							sixftb1px = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
							table6FI.addCell(sixftb1px);
						}
					}
				}
				
					}

				// end 6FI- PaperII==============================================

				// table 6FI- PaperII===start==========zarna=================================
				PdfPTable table6FII = new PdfPTable(7);
				Paragraph p99;
				table6FII.setWidthPercentage(100);
				table6FII.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table6FII.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

				Paragraph p6FI1 = new Paragraph(" ", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI2 = new Paragraph("Paper II", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI3 = new Paragraph(
						"D\n" + "Type of Questions\n" + "“Yes” can be asked.\n" + "“No” should not be asked.",
						fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);

				Paragraph p6FI4 = new Paragraph("A\n" + "List of Topics", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI5 = new Paragraph("B\n" + "Term", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI6 = new Paragraph("C\n" + "Marks", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI7 = new Paragraph("MCQ\n" + "(1 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI8 = new Paragraph("SAQ\n" + "(5 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);
				Paragraph p6FI9 = new Paragraph("LAQ\n" + "(10 Mark)", fontTableHeading);
//					p2.setAlignment(Element.ALIGN_CENTER);

//			nt.ALIGN_CENTER);

				table6FII.addCell(p6FI1);
				table6FII.addCell(p6FI2);
				table6FII.addCell(p6FI1);
				table6FII.addCell(p6FI1);
				table6FII.getDefaultCell().setColspan(3);
				table6FII.addCell(p6FI3);
				table6FII.getDefaultCell().setColspan(1);
				table6FII.addCell(p6FI1);
				table6FII.addCell(p6FI4);
				table6FII.addCell(p6FI5);
				table6FII.addCell(p6FI6);
				table6FII.addCell(p6FI7);
				table6FII.addCell(p6FI8);
				table6FII.addCell(p6FI9);
//					data bind start1
//					table6FII.addCell(p6FI10);
//					table6FII.addCell(p6FI11);
//					
//					table6FII.addCell(p6FI12);
//					
//					table6FII.addCell(p6FI13);
//				
//					table6FII.addCell(p6FI14);
//					table6FII.addCell(p6FI15);
//					table6FII.addCell(p6FI16);

				Paragraph sixftbII = new Paragraph();

				ArrayList<ArrayList<String>> sixfII = (ArrayList<ArrayList<String>>) Table6F_IIDistribution_of_Theory_Exam_List;
				String yesNoII = "";
//					System.err.println("=-=-=-=-=-=ii-=-=-=-=-=-="+sixf1);
				for (int i = 0; i < sixfII.size(); i++) {
					List<String> l = sixfII.get(i);
					for (int j = 0; j < l.size(); j++) {
						if (j == 4 || j == 5 || j == 6) {
							if (l.get(j).equals("0")) {
								yesNoII = "NO";
							}
							if (l.get(j).equals("1")) {
								yesNoII = "YES";
							}
							sixftbII = new Paragraph(yesNoII, fontTableHeadingNonBoldData);
							table6FII.addCell(sixftbII);
						} else {
							sixftbII = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
							table6FII.addCell(sixftbII);
						}
					}
				}

				// end table 6FI- PaperII==============================================
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		////////// Distribution of Practical Exam
		////////// ========================================

		ArrayList<ArrayList<String>> Distri_Pract_List = (ArrayList<ArrayList<String>>) Distri_Pract_Exam;
//						ArrayList<ArrayList<String>> practhrs6 = (ArrayList<ArrayList<String>>) practhours6;
//						System.err.println("table_6H1List==========="+table_6H1List);
		PdfPTable tab923 = new PdfPTable(2);

		tab923.setWidthPercentage(100);
		tab923.setWidths(new int[] { 50, 20 });
		tab923.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab923.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable table2111 = new PdfPTable(1);
		table2111.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table2111.setWidthPercentage(100);
		table2111.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

		Paragraph pag2 = new Paragraph("Distribution of Practical Exam", fontTableHeading);
		table2111.addCell(pag2);

		Paragraph pag22 = new Paragraph();
		Paragraph pag23 = new Paragraph();

		for (int i = 0; i < Distri_Pract_List.size(); i++) {
			List<String> l = Distri_Pract_List.get(i);
			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tab923.addCell(py);

			}
		}

		// end of Distribution of Practical
		// Exam=========================================================

// Reference and Resourses========================================

		PdfPTable tblref7 = new PdfPTable(1);
		tblref7.setWidthPercentage(100);
		tblref7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlinersRef = new Chunk("Reference and Resourses :- ", fontTableHeading);
		tblref7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase resourses7 = new Phrase(underlinersRef);

		Paragraph cellrefre7 = new Paragraph(resourses7);
		cellrefre7.setAlignment(Element.ALIGN_CENTER);

		Font fontTableHeadingNonBoldDataresour7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

		Phrase Resour = new Phrase();
		String Resourses = "";
		int ser2 = 1;
		ArrayList<ArrayList<String>> tblresList = (ArrayList<ArrayList<String>>) Reference_Resourses_list;
		for (int i = 0; i < tblresList.size(); i++) {
			Resourses += "\n" + " " + ser2 + ". " + tblresList.get(i).get(0);
			ser2++;
		}
		Resour = new Phrase(Resourses, fontTableHeadingNonBoldDataresour7);

// End Reference and Resourses========================================

		Phrase p1 = new Phrase();
		table.addCell(table3);
		document.add(TestTable);
		p1 = new Phrase("\n");
		document.add(p1);

//		Start Program Outcomes
		document.add(p1);
		document.add(po1);
		document.add(p1);
		document.add(ref);
		document.add(p1);

//		end of Program Outcomes

//		Start COURSE OUTCOMES
		document.add(p1);
		document.add(p1);
		document.add(add1);
		document.add(p1);
		document.add(add_course);
		document.add(p1);
//		end of COURSE OUTCOMES

//		Start TEACHING LEARNING METHODS
		document.add(p1);
		document.add(TEC1);
		document.add(p1);
		document.add(Tec_Learning);

//		end TEACHING LEARNING METHODS

//		Start Teaching hour

		document.add(p1);
		document.add(p1);
		document.add(t1);
		document.add(p1);
		table.addCell(tablexx);
		document.add(p1);
		table.addCell(tablex);
		document.add(table32);
		document.add(p1);
//		end of Teaching hour

// start of topic  list //////////

		document.add(table2MHeader);
		document.add(p1);
		document.add(table2);
		document.add(p1);
		// end of topic list //////////

		// Start of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I

		document.add(table3HOMHeader);
		document.add(p1);
		document.add(table3HOM);
		document.add(p1);

		// End of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I

/////////////start  of topic and practical list //////////			

		document.add(table21MHeader1);
		document.add(p1);
		document.add(table21);
		document.add(p1);

/////////////end of topic and practical list //////////	

		// Practical Learning Objectives

		document.add(table4HOMHeader);
		document.add(p1);
		document.add(table4HOM);
		document.add(p1);

		// End of Practical Learning Objectives

//		Start TableNon_Lecture_list
		document.add(p1);
		document.add(tableNon_Lecture);
		document.add(p1);
		document.add(tableNon_Lecture1);
		document.add(p1);

//		Start Table- Assessment Summary
		document.add(p1);
		document.add(table60Dnlach1);
		document.add(p1);
		document.add(table6A);
		document.add(p1);

//		end Table- Assessment Summary	

//		start  Scheme of Assessment (formative and Summative)
		document.add(p1);
		document.add(table6b1);
		document.add(p1);
		document.add(table6bsa);
//		document.add(p1);
		document.add(table6b);
		document.add(p1);

//		end  Scheme of Assessment (formative and Summative)

// Paper Layout ////

		document.add(p1);
		document.add(tableref2);
		document.add(p1);
		document.add(tab92);
		document.add(p1);

// Paper Layout //
		
		
		
	//// Distribution of Theory Exam ///////
		
		document.add(table6FMHeader);
		document.add(table6FI);
		document.add(p1);
		document.add(table6FII);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		
		
		
	////End Distribution of Theory Exam ///////
		
		
		
		//// Distribution of Practical Exam ///////

		document.add(table2111);
		document.add(p1);
		document.add(tab923);
		document.add(p1);

		//// Distribution of Practical Exam ///////

//		Start Evaluation Methods

		document.add(t6D);
		document.add(p1);
		document.add(table6Dnlaclink);
		document.add(p1);

//		end Evaluation Methods	

//		strt Reference and Resourses
		document.add(p1);
		document.add(resourses7);
		document.add(p1);
		document.add(Resour);

//		End Reference and Resourses			

		super.buildPdfMetadata(model, document, request);
	}
}
