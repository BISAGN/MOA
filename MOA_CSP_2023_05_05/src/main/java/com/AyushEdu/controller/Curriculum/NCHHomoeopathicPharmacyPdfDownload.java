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

public class NCHHomoeopathicPharmacyPdfDownload extends AbstractPdfView {
	
	String Type = "";
	String username = "";
	String role = "";
	String Heading = "";
	int total = 0;
	int total1 = 0;
	
	List<Map<String, Object>> sysdegprofcorsnamecode;
	List<ArrayList<String>> Program_Outcomes_list;
	List<ArrayList<String>> Course_Outcomes_list;
	List<String> TH;
	List<ArrayList<String>> Teaching_hour;
	List<String> TH2;
	List<String> TH3;
	List<ArrayList<String>> topic_subtopic;
	List<ArrayList<String>> Practical_List;
	List<ArrayList<String>> Teaching_Method_list;
	List<String> TH2loc;
	List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list;
	List<ArrayList<String>> Non_Lecture_Activities_List;
	List<String> TH6;
	List<ArrayList<String>> table6_number_of_papers_listP;
	List<ArrayList<String>> table6b_term1list;
	List<ArrayList<String>> table6b_term2list;
	List<ArrayList<String>> table6b_term3list;
	List<ArrayList<String>> table6d_term1list;
	List<ArrayList<String>> table6d_term2list;
	List<ArrayList<String>> table6d_term3list;
	List<String> TH8;
	List<ArrayList<String>> TableEvaluation_Methods_list;
	List<String> THPh50;
	List<ArrayList<String>> Paper_LayoutHOM;
	List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Theme;
	List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_II_Theme;
	List<ArrayList<String>> table61_list;
	List<ArrayList<String>> table62_list;
	List<ArrayList<String>> table63_list;
	List<String> THQ6;
	List<ArrayList<String>> Distri_Pract_Exam;
	List<ArrayList<String>> Reference_Resourses_list;
	
	public NCHHomoeopathicPharmacyPdfDownload(String Type,String Heading, String role,
			List<Map<String, Object>> sysdegprofcorsnamecode,
			List<ArrayList<String>> Program_Outcomes_list,
			List<ArrayList<String>> Course_Outcomes_list,
			List<String> TH,
			List<ArrayList<String>> Teaching_hour,
			List<String> TH2,
			List<String> TH3,
			List<ArrayList<String>> topic_subtopic,
			List<ArrayList<String>> Practical_List,
			List<ArrayList<String>> Teaching_Method_list,
			List<String> TH2loc,List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list,
			List<ArrayList<String>> Non_Lecture_Activities_List,
			List<String> TH6, List<ArrayList<String>> table6_number_of_papers_listP,
			List<ArrayList<String>> table6b_term1list, List<ArrayList<String>> table6b_term2list,
			List<ArrayList<String>> table6b_term3list,
			List<ArrayList<String>> table6d_term1list, List<ArrayList<String>> table6d_term2list,
			List<ArrayList<String>> table6d_term3list,
			List<String> TH8,List<ArrayList<String>> TableEvaluation_Methods_list,
			List<String> THPh50,List<ArrayList<String>> Paper_LayoutHOM,
			List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Theme,
			List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_II_Theme,
			List<ArrayList<String>> table61_list,
	    	List<ArrayList<String>> table62_list,
	    	List<ArrayList<String>> table63_list,
	    	List<String> THQ6,
			List<ArrayList<String>> Distri_Pract_Exam,
			List<ArrayList<String>> Reference_Resourses_list) {

		this.Type = Type;
		this.Heading = Heading;
		this.role = role;
		this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
		this.Program_Outcomes_list = Program_Outcomes_list;
		this.Course_Outcomes_list = Course_Outcomes_list;
		this.TH = TH;
		this.Teaching_hour = Teaching_hour;
		this.TH3 = TH3;
		this.topic_subtopic = topic_subtopic;
		this.TH2 = TH2;
		this.Practical_List = Practical_List;
		this.Teaching_Method_list = Teaching_Method_list;
		this.TH2loc = TH2loc;
		this.table2_Learning_Objectives_of_Course_HomUG_list = table2_Learning_Objectives_of_Course_HomUG_list;
		this.Non_Lecture_Activities_List = Non_Lecture_Activities_List;
		this.TH6 = TH6;
		this.table6_number_of_papers_listP = table6_number_of_papers_listP;
		this.table6b_term1list = table6b_term1list;
		this.table6b_term2list = table6b_term2list;
		this.table6b_term3list = table6b_term3list;
		this.table6d_term1list = table6d_term1list;
		this.table6d_term2list = table6d_term2list;
		this.table6d_term3list = table6d_term3list;
		this.TH8 = TH8;
		this.TableEvaluation_Methods_list = TableEvaluation_Methods_list;
		this.Paper_LayoutHOM = Paper_LayoutHOM;
		this.THPh50 = THPh50;
		this.Table6F_IDistribution_of_Theory_Exam_List_Theme = Table6F_IDistribution_of_Theory_Exam_List_Theme;	
		this.Table6F_IDistribution_of_Theory_Exam_List_II_Theme = Table6F_IDistribution_of_Theory_Exam_List_II_Theme;
		this.table61_list = table61_list;
		this.table62_list = table62_list;
		this.table63_list = table63_list;
		this.THQ6 = THQ6;
		this.Distri_Pract_Exam = Distri_Pract_Exam;
		this.Reference_Resourses_list = Reference_Resourses_list;
	}

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
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		
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

		Font fontTablemainHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
		Font fontTablemain2Heading = FontFactory.getFont(FontFactory.defaultEncoding, 18);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 12, 0);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(110);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

// For System Degree Prof Course Name and Code =======================================

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

// For System Degree Prof Course Name and Code ==========================================================

// Strt Program_Outcomes=================================================================================

				PdfPTable tablerefadd8 = new PdfPTable(1);
				tablerefadd8.setWidthPercentage(100);
				tablerefadd8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underliners2 = new Chunk("PROGRAM OUTCOMES ", fontTableHeading);
				tablerefadd8.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase add2 = new Phrase(underliners2);

				Paragraph celladd8 = new Paragraph(add2);
				celladd8.setAlignment(Element.ALIGN_CENTER);

				Font fontTableHeadingNonBoldDataadd8 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

				Phrase add_program_course = new Phrase();
				String add_program_course2 = "";
				int ser2 = 1;
				ArrayList<ArrayList<String>> addList1 = (ArrayList<ArrayList<String>>) Program_Outcomes_list;
				for (int i = 0; i < addList1.size(); i++) {
					if (i == 0) {
						add_program_course2 += ser2 + ". " + addList1.get(i).get(0);
						ser2++;
					} else {
						add_program_course2 += "\n\n" + " " + ser2 + ". " + addList1.get(i).get(0);
						ser2++;
					}
				}
				add_program_course = new Phrase(add_program_course2, fontTableHeadingNonBoldDataadd8);

// End Program_Outcomes===========================================================================
				
// Strt COURSE OUTCOMES===========================================================================

		PdfPTable tablerefadd7 = new PdfPTable(1);
		tablerefadd7.setWidthPercentage(100);
		tablerefadd7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underliners1 = new Chunk("COURSE OUTCOMES ", fontTableHeading);
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
			if (i == 0) {
				add_course1 += ser1 + ". " + addList.get(i).get(0);
				ser1++;
			} else {
				add_course1 += "\n\n" + " " + ser1 + ". " + addList.get(i).get(0);
				ser1++;
			}
		}
		add_course = new Phrase(add_course1, fontTableHeadingNonBoldDataadd7);

// End COURSE OUTCOMES=============================================================================

//==============Start================== Teaching hour============================================

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

		PdfPTable tablex = new PdfPTable(1);
		tablex.setWidthPercentage(100);
		tablex.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatax = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Paragraph py = new Paragraph();
		Paragraph py1 = new Paragraph();
		Paragraph py2 = new Paragraph();

		PdfPTable table32 = new PdfPTable(4);
		table32.setWidthPercentage(100);
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
//==============END================== Teaching hour==================================================
		
		
// Start Table : Contents of Course =====================================================================

		PdfPTable table2MHeader = new PdfPTable(1);
		table2MHeader.setWidthPercentage(100);
		table2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline51 = new Chunk("Teaching Hours (Theory) ", fontTableHeading);
		table2MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t2a = new Phrase(underline51);
		
		Paragraph cell2a = new Paragraph(t2a);
		cell2a.setAlignment(Element.ALIGN_CENTER);
		
		table2MHeader.addCell(t2a);
		
		ArrayList<ArrayList<String>> t2List = (ArrayList<ArrayList<String>>) topic_subtopic;
		
		PdfPTable table2link = new PdfPTable(1);
		table2link.setWidthPercentage(190);
		table2link.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2link.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldData2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		
		Paragraph pt2 = new Paragraph();
		PdfPTable table2Content_Course_AyUGRS = new PdfPTable(4);
		table2Content_Course_AyUGRS.setWidthPercentage(100);
		table2Content_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH3.size(); h++) {
			pt2 = new Paragraph(TH3.get(h), fontTableHeading);
			pt2.setAlignment(Element.ALIGN_CENTER);
			table2Content_Course_AyUGRS.addCell(pt2);
		}
		
		PdfPTable table2a = new PdfPTable(4);
		table2a.setWidthPercentage(100);
		table2a.setWidths(new int[] { 25, 250, 50, 50 });
		table2a.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
		Paragraph py2y = new Paragraph();
		Paragraph py21 = new Paragraph();
		Paragraph py22 = new Paragraph();
		Paragraph py23 = new Paragraph();

		py2y = new Paragraph(TH3.get(0), fontTableHeading);
		py2y.setAlignment(Element.ALIGN_CENTER);
		table2a.addCell(py2y);
		py21 = new Paragraph(TH3.get(1), fontTableHeading);
		py21.setAlignment(Element.ALIGN_CENTER);
		table2a.addCell(py21);
		py22 = new Paragraph(TH3.get(2), fontTableHeading);
		py22.setAlignment(Element.ALIGN_CENTER);
		table2a.addCell(py22);
		py23 = new Paragraph(TH3.get(3), fontTableHeading);
		py23.setAlignment(Element.ALIGN_CENTER);
		table2a.addCell(py23);
		
		String topic = "";
		int thc = 0;
		
		table2link.setWidthPercentage(150);
		for (int i = 0; i < t2List.size(); i++) {
			List<String> l = t2List.get(i);
			for (int j = 0; j < l.size(); j++) {
				
				if (i == 0 && j == 0) {
					topic = l.get(1);
					Paragraph pt3 = new Paragraph(topic, fontTableHeading);
					table2a.getDefaultCell().setColspan(4);
					table2a.addCell(pt3);
					table2a.getDefaultCell().setColspan(1);
					
					py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table2a.addCell(py2);
				}
				else if(j == 0 && topic.equals(l.get(1))) {
					py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table2a.addCell(py2);
				}else if(j == 0 && !topic.equals(l.get(1))) {
					
					topic = l.get(1);
					Paragraph pt3 = new Paragraph(topic, fontTableHeading);
					table2a.getDefaultCell().setColspan(4);
					table2a.addCell(pt3);
					table2a.getDefaultCell().setColspan(1);
					
					py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table2a.addCell(py2);
					
				}else if (j == 1) {
					if (!topic.equals(l.get(j))) {
						topic = l.get(j);
						Paragraph pt3 = new Paragraph(topic, fontTableHeading);
						table2a.getDefaultCell().setColspan(4);
						table2a.addCell(pt3);
						table2a.getDefaultCell().setColspan(1);
					}
				}
				else if(i == (t2List.size()-1) && j == (l.size()-1)) {
					
					py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table2a.addCell(py2);
				}else {
					py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table2a.addCell(py2);
				}
		
			}
		}
		table2link.addCell(table2Content_Course_AyUGRS);

//End Table : Contents of Course =====================================================================				

// Start Practical_List ===============================================================================

		ArrayList<ArrayList<String>> tpList = (ArrayList<ArrayList<String>>) Practical_List;

		PdfPTable tablePlink = new PdfPTable(1);
		tablePlink.setWidthPercentage(190);
		tablePlink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablePlink.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDataP = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph ptp = new Paragraph();
		PdfPTable tablePContent_Course_AyUGRS = new PdfPTable(4);
		tablePContent_Course_AyUGRS.setWidthPercentage(100);
		tablePContent_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH2.size(); h++) {
			ptp = new Paragraph(TH2.get(h), fontTableHeading);
			ptp.setAlignment(Element.ALIGN_CENTER);
			tablePContent_Course_AyUGRS.addCell(ptp);
		}

		PdfPTable table23P = new PdfPTable(4);
		table23P.setWidthPercentage(100);
		table23P.setWidths(new int[] { 25, 250, 100, 50 });
		table23P.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph pypy = new Paragraph();
		Paragraph pyp1 = new Paragraph();
		Paragraph pyp2 = new Paragraph();
		Paragraph pyp3 = new Paragraph();
		Paragraph pyp4 = new Paragraph();
		Paragraph pyp5 = new Paragraph();
		pypy = new Paragraph(TH2.get(0), fontTableHeading);
		pypy.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pypy);
		pyp1 = new Paragraph(TH2.get(1), fontTableHeading);
		pyp1.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp1);
		pyp2 = new Paragraph(TH2.get(2), fontTableHeading);
		pyp2.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp2);
		pyp3 = new Paragraph(TH2.get(3), fontTableHeading);
		pyp3.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp3);

		tablePlink.setWidthPercentage(150);
		
		String topictype = "";
		
		for (int i = 0; i < tpList.size(); i++) {
			List<String> l = tpList.get(i);
			for (int j = 0; j < l.size(); j++) {

				if (j == 1) {
					String topicsubtopic = l.get(j);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23P.addCell(py2);
				}
				if (j == 2) {
					String topicsubtopic = l.get(j);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23P.addCell(py2);
				}
				if (j == 3) {
					String topicsubtopic = l.get(j);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23P.addCell(py2);
				}
				
				if (j != 1 && j != 2 && j != 3) {
					if (i == 0 && j == 0) {
						topictype = l.get(j);
						Paragraph pt3 = new Paragraph(topictype, fontTableHeading);
						table23P.getDefaultCell().setColspan(4);
						table23P.addCell(pt3);
						table23P.getDefaultCell().setColspan(1);
					}
					if (j != 0) {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table23P.addCell(py2);
					}
					if (j == 0) {
						if (!topictype.equals(l.get(j))) {
							topictype = l.get(j);
							Paragraph pt3 = new Paragraph(topictype, fontTableHeading);
							table23P.getDefaultCell().setColspan(4);
							table23P.addCell(pt3);
							table23P.getDefaultCell().setColspan(1);
						}
					}
				}
			}
		}
		
		PdfPTable tablePMHeader = new PdfPTable(1);
		tablePMHeader.setWidthPercentage(100);
		tablePMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlineP = new Chunk("Teaching Hours (Practical)", fontTableHeading);
		tablePMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase tp = new Phrase(underlineP);

		Paragraph cellp = new Paragraph(tp);
		cellp.setAlignment(Element.ALIGN_CENTER);

		tablePMHeader.addCell(tp);
		tablePlink.addCell(tablePContent_Course_AyUGRS);


//End  Practical_List   ============================================================================

////==============END================== Non-Lecture Activities (Practical)==========================
		
// Strt TEACHING LEARNING METHODS=====================================================================
			PdfPTable tableTec_Learning = new PdfPTable(1);
			tableTec_Learning.setWidthPercentage(100);
			tableTec_Learning.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underlinerTec = new Chunk("TEACHING LEARNING METHODS ", fontTableHeading);
			tableTec_Learning.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase TEC1 = new Phrase(underlinerTec);

			Paragraph cellTec7 = new Paragraph(TEC1);
			cellTec7.setAlignment(Element.ALIGN_CENTER);

			Font fontTableHeadingNonBoldDataTEC7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

			Phrase Tec_Learning = new Phrase();
			String Tec_Learning1 = "";
			int ser5 = 1;
			ArrayList<ArrayList<String>> TecList = (ArrayList<ArrayList<String>>) Teaching_Method_list;
			for (int i = 0; i < TecList.size(); i++) {
				Tec_Learning1 += "\n" + " " + ser5 + ". " + TecList.get(i).get(0);
				ser5++;
			}
			Tec_Learning = new Phrase(Tec_Learning1, fontTableHeadingNonBoldDataTEC7);

// End TEACHING LEARNING METHODS=========================================================================
			
// Table 2: Learning objectives (Theory) of Course=======================================================
			
						ArrayList<ArrayList<String>> t3HOMList = (ArrayList<ArrayList<String>>) table2_Learning_Objectives_of_Course_HomUG_list;
						PdfPTable table3HOMHeader = new PdfPTable(1);
						table3HOMHeader.setWidthPercentage(100);
						table3HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						Chunk underlineHOM = new Chunk("Table 2-Learning Objectives (Theory) ", fontTableHeading);
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
							
							String los = "";
							
							for(int x=0;x<l.get(13).split("=").length;x++) {
								if(x==0) {
									los = "Learning Outcomes(LO):\n" + (x+1)+". "+l.get(13).split("=")[x]+"\n";
								}else {
									los += (x+1)+". "+l.get(13).split("=")[x]+"\n";
								}
							}
							
							Paragraph paralos = new Paragraph(los, fontTableHeading);
							paralos.setAlignment(Element.ALIGN_CENTER);
							table3HOM.getDefaultCell().setColspan(12);
							table3HOM.addCell(paralos);
							table3HOM.getDefaultCell().setColspan(1);
					
						}
						if (j == 0) {
							if (!papertype11.equals(l.get(j))) {
								papertype11 = l.get(j);
								Paragraph ptt32HOM = new Paragraph(papertype11, fontTableHeading);
								ptt32HOM.setAlignment(Element.ALIGN_CENTER);
								table3HOM.getDefaultCell().setColspan(13);
								table3HOM.addCell(ptt32HOM);
								table3HOM.getDefaultCell().setColspan(1);
								
								String los = "";
								
								for(int x=0;x<l.get(13).split("=").length;x++) {
									if(x==0) {
										los = "Learning Outcomes(LO): \n" + (x+1)+". "+l.get(13).split("=")[x]+"\n";
									}else {
										los += (x+1)+". "+l.get(13).split("=")[x]+"\n";
									}
								}
								
								Paragraph paralos = new Paragraph(los, fontTableHeading);
								paralos.setAlignment(Element.ALIGN_CENTER);
								table3HOM.getDefaultCell().setColspan(12);
								table3HOM.addCell(paralos);
								table3HOM.getDefaultCell().setColspan(1);
							}
						}
						if (j != 0 && j!= 13) {
							Paragraph ptt32HOM = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
							table3HOM.addCell(ptt32HOM);
						}
					}
					}
					
					table.addCell(table3HOM);

// end of Table 2: Learning objectives (Theory)=======================================================
	
//Strt Non-Lecture Activities==========================================================================
			
			PdfPTable tablerefadd = new PdfPTable(1);
			tablerefadd.setWidthPercentage(100);
			tablerefadd.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underliners = new Chunk("Non-Lecture Activities ", fontTableHeading);
			tablerefadd.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase add = new Phrase(underliners);
			
			Paragraph celladd = new Paragraph(add);
			celladd.setAlignment(Element.ALIGN_CENTER);
			
			Font fontTableHeadingNonBoldDataadd = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);
			
			Phrase Non_Lecture_Activities = new Phrase();
			String Non_Lecture_Activities1 = "";
			int ser = 1;
			ArrayList<ArrayList<String>> addListNon = (ArrayList<ArrayList<String>>) Non_Lecture_Activities_List;
			for (int i = 0; i < addListNon.size(); i++) {
				if (i == 0) {
					Non_Lecture_Activities1 += ser + ". " + addListNon.get(i).get(0);
					ser++;
				} else {
					Non_Lecture_Activities1 += "\n\n" + " " + ser + ". " + addListNon.get(i).get(0);
					ser++;
				}
			}
			Non_Lecture_Activities = new Phrase(Non_Lecture_Activities1, fontTableHeadingNonBoldDataadd);

//End Non-Lecture Activities======================================================================================

// start Assessment Summary========================================================================================

			PdfPTable table60Dnlach1 = new PdfPTable(1);
			table60Dnlach1.setWidthPercentage(100);
			table60Dnlach1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6DH = new Chunk("9.Assessment", fontTableHeading);
			Chunk underline6DH2 = new Chunk("9A - Number of papers and Mark Distribution ", fontTableHeading);
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
			Chunk underline6A = new Chunk("9A - Number of Papers and Marks Distribution", fontTableHeading);
			table6nop1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t6A = new Phrase(underline6A);

			Paragraph cell6A = new Paragraph(t6A);
			cell6A.setAlignment(Element.ALIGN_CENTER);

			table6nop1.addCell(t6A);

			ArrayList<ArrayList<String>> nplist = (ArrayList<ArrayList<String>>) table6_number_of_papers_listP;
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
			Paragraph pt6a10 = new Paragraph();

			PdfPTable table6anop1 = new PdfPTable(8);
			table6anop1.setWidthPercentage(100);
			table6anop1.setWidths(new int[] { 8, 24, 12, 12, 15, 8, 20, 6 });
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

			if (nplist.size() > 0) {

				table6anop1.addCell(pt6a);
				table6anop1.addCell(pt6a1);
				table6anop1.addCell(pt6a2);
				table6anop1.addCell(pt6a3);
				table6anop1.addCell(pt6a4);
				table6anop1.addCell(pt6a5);
				table6anop1.addCell(pt6a6);
				table6anop1.addCell(pt6a7);

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
				pt6a = new Paragraph(String.valueOf(grand_total), fontTableHeadingNonBoldData);
				table6anop1.addCell(pt6a);

				table6A.addCell(table6anop1);
			}
// End Assessment Summary======================================================================================
			
// 6-B================ Start Scheme of Assessment (formative and Summative)======================================
					PdfPTable table6b = new PdfPTable(1);
					table6b.setWidthPercentage(100);
					table6b.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6B = new Chunk("PA: Periodical Assessment; TT: Term Test; UE: University Examinations",
							fontTableHeading);
					table6b.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					Phrase t6B = new Phrase(underline6B);
		
					Paragraph cell6B = new Paragraph(t6B);
					cell6A.setAlignment(Element.ALIGN_CENTER);
		
					table6b.addCell(t6B);
		
					PdfPTable table6b1 = new PdfPTable(1);
					table6b1.setWidthPercentage(100);
					table6b1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6B1 = new Chunk("Scheme of Assessment (formative and Summative) ", fontTableHeading);
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
					Paragraph p6b5 = new Paragraph("First Term\n" + "(1-6 Months)", fontTableHeading);
					p6b5.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6b6 = new Paragraph("Second Term\n" + "(7-12 Months)", fontTableHeading);
					p6b6.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6b7 = new Paragraph("Third Term\n" + "(13-18 Months)", fontTableHeading);
					p6b7.setAlignment(Element.ALIGN_CENTER);
		
					Paragraph p6b8 = new Paragraph("1");
		
					Paragraph p6b9 = new Paragraph(table6b_term1list.get(0).get(1));
					Paragraph p6b10 = null;
					Paragraph p6b11 = null;
					Paragraph p6b12 = null;
					
					
					if(table6b_term1list.get(0).size() > 0) {
					p6b10 = new Paragraph(table6b_term1list.get(0).get(2));
					}
					
					if(table6b_term1list.get(0).size() > 0) {
						p6b11 = new Paragraph(table6b_term2list.get(0).get(2));
					}
					
					if(table6b_term1list.get(0).size() > 0) {
						p6b12 = new Paragraph(table6b_term3list.get(0).get(2));
					}
					table6bsa.addCell(p6b2);
					table6bsa.addCell(p6b3);
					table6bsa.addCell(p6b5);
					table6bsa.addCell(p6b6);
					table6bsa.addCell(p6b7);
					table6bsa.addCell(p6b8);
					table6bsa.addCell(p6b9);
					table6bsa.addCell(p6b10);
					table6bsa.addCell(p6b11);
					table6bsa.addCell(p6b12);

// 6-B===================== End Scheme of Assessment (formative and Summative)==========================
					//6-D================ Start Scheme of Assessment (formative)=========================

					PdfPTable table6D = new PdfPTable(1);
					table6D.setWidthPercentage(100);
					table6D.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6D1 = new Chunk("For Internal assessment, Only Practical/Viva marks will be considered. Theory marks will not be counted)",
							fontTableHeading);
					table6D.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					Phrase t6D1 = new Phrase(underline6D1);

					Paragraph cell6D1 = new Paragraph(t6D1);
					cell6A.setAlignment(Element.ALIGN_CENTER);

					table6D.addCell(t6D1);

					PdfPTable table6D1 = new PdfPTable(1);
					table6D1.setWidthPercentage(100);
					table6D1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6D11 = new Chunk("9B - Scheme of Assessment (Formative ) ", fontTableHeading);
					table6D1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					Phrase t6D11 = new Phrase(underline6D11);

					Paragraph cell6D11 = new Paragraph(t6D11);
					cell6D11.setAlignment(Element.ALIGN_CENTER);

					table6D1.addCell(t6D11);

					PdfPTable table6Dformative = new PdfPTable(7);
					Paragraph p6d;
					table6Dformative.setWidthPercentage(100);
					table6Dformative.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					table6Dformative.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

					Paragraph p6D2 = new Paragraph("SR.NO.", fontTableHeading);
					p6D2.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6D3 = new Paragraph("PROFESSIONAL\n" + "COURSE", fontTableHeading);
					p6D3.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6D5 = new Paragraph("First Term\n" + "(1-6 Months)", fontTableHeading);
					p6D5.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6D6 = new Paragraph("Second Term\n" + "(7-12 Months)", fontTableHeading);
					p6D6.setAlignment(Element.ALIGN_CENTER);
					Paragraph p6D7 = new Paragraph("Third Term\n" + "(13-18 Months)", fontTableHeading);
					p6D7.setAlignment(Element.ALIGN_CENTER);

					Paragraph p6D8 = new Paragraph("1");
					Paragraph p6D9 = new Paragraph(table6d_term1list.get(0).get(1));
					Paragraph p6D10 = null;
					Paragraph p6D11 = null;
					Paragraph p6D12 = null;
					Paragraph p6D13 = null;
					Paragraph p6D14 = null;
					Paragraph p6D15 = null;

					if (table6d_term1list.get(0).size() > 0) {
						p6D10 = new Paragraph(table6d_term1list.get(0).get(2).split("\\+")[0]);
					}
					
					if (table6d_term1list.get(0).size() > 0) {
						p6D11 = new Paragraph(table6d_term1list.get(0).get(2).split("\\+")[1]);
					}
					
					if (table6d_term2list.get(0).size() > 0) {
						p6D12 = new Paragraph(table6d_term2list.get(0).get(2).split("\\+")[0]);
					}
					
					if (table6d_term2list.get(0).size() > 0) {
						p6D13 = new Paragraph(table6d_term2list.get(0).get(2).split("\\+")[1]);
					}
					
					if (table6d_term3list.get(0).size() > 0) {
						p6D14 = new Paragraph(table6d_term3list.get(0).get(2).split("\\+")[0]);
					}
					
					table6Dformative.addCell(p6D2);
					table6Dformative.addCell(p6D3);
					table6Dformative.getDefaultCell().setColspan(2);
					table6Dformative.addCell(p6D5);
					table6Dformative.getDefaultCell().setColspan(1);
					table6Dformative.getDefaultCell().setColspan(2);
					table6Dformative.addCell(p6D6);
					table6Dformative.getDefaultCell().setColspan(1);
					table6Dformative.getDefaultCell().setColspan(2);
					table6Dformative.addCell(p6D7);
					table6Dformative.getDefaultCell().setColspan(1);
					table6Dformative.getDefaultCell().setRowspan(2);
					table6Dformative.addCell(p6D8);
					table6Dformative.addCell(p6D9);
					table6Dformative.getDefaultCell().setRowspan(1);
					table6Dformative.addCell(p6D10);
					table6Dformative.addCell(p6D11);
					table6Dformative.addCell(p6D12);
					table6Dformative.addCell(p6D13);
					table6Dformative.addCell(p6D14);
					table6Dformative.getDefaultCell().setRowspan(2);
					table6Dformative.getDefaultCell().setRowspan(1);
					table6Dformative.addCell("10 Marks Practical/Viva");
					table6Dformative.addCell("50 Marks Practical/ Viva");
					table6Dformative.addCell("10 Marks Practical/Viva");
					table6Dformative.addCell("50 Marks Practical/ Viva");
					table6Dformative.addCell("10 Marks Practical/Viva");
					
		//6-D===================== End Scheme of Assessment (formative)====================================
					//8 E - Calculation Method for Internal assessment Marks  ==========================

					PdfPTable tabl6C = new PdfPTable(8);
					tabl6C.setWidthPercentage(100);
					tabl6C.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					tabl6C.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					PdfPTable tabl6C1 = new PdfPTable(1);
					tabl6C1.setWidthPercentage(100);
					tabl6C1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6C11 = new Chunk("PA: Periodical Assessment; TT: Term Test; UE: University Examinations",
							fontTableHeading);
					tabl6C1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					Phrase t6C11 = new Phrase(underline6C11);

					Paragraph cell6C1 = new Paragraph(t6C11);
					cell6C1.setAlignment(Element.ALIGN_CENTER);

					tabl6C1.addCell(t6C11);
					
					PdfPTable tableref33 = new PdfPTable(1);
					tableref33.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					tableref33.setWidthPercentage(100);
					tableref33.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

					Paragraph pa6C = new Paragraph("Method of Calculation of Internal Assessment Marks for Final University Examination: ",
							fontTableHeading);
					tableref33.addCell(pa6C);

					Paragraph pg6 = new Paragraph();
					Paragraph pg61 = new Paragraph();
					Paragraph pg62 = new Paragraph();
					Paragraph pg63 = new Paragraph();
					Paragraph pg64 = new Paragraph();
					Paragraph pg65 = new Paragraph();
					Paragraph pg66 = new Paragraph();
					Paragraph pg67 = new Paragraph();
					Paragraph pg620 = new Paragraph();
					Paragraph pg621 = new Paragraph();
					Paragraph pg622 = new Paragraph();
					Paragraph pg623 = new Paragraph();
					Paragraph pg624 = new Paragraph();
					Paragraph pg625 = new Paragraph();
					Paragraph pg626 = new Paragraph();
					Paragraph pg627 = new Paragraph();

					pg6 = new Paragraph(("PA1 Practical/Viva\n"
							+ "(10 Marks)"), fontTableHeadingNonBoldData);
					pg6.setAlignment(Element.ALIGN_CENTER);
					
					pg61 = new Paragraph(("PA2\n"
							+ "Practical/Viva\n"
							+ "(10 Marks)"), fontTableHeadingNonBoldData);
					pg61.setAlignment(Element.ALIGN_CENTER);
					
					pg62 = new Paragraph(("PA3\n"
							+ "Practical/Viva\n"
							+ "(10 Marks)"), fontTableHeadingNonBoldData);
					pg62.setAlignment(Element.ALIGN_CENTER);
					
					pg63 = new Paragraph(("Periodical Assessment Average\n"
							+ "PA1+PA2+PA3/3"), fontTableHeadingNonBoldData);
					pg63.setAlignment(Element.ALIGN_CENTER);
					
					pg64 = new Paragraph(("TT1 Practical/Viva\n"
							+ "(50 Marks)"), fontTableHeadingNonBoldData);
					pg64.setAlignment(Element.ALIGN_CENTER);
					
					pg65 = new Paragraph(("TT2 Practical/Viva\n"
							+ "(50 Marks)"), fontTableHeadingNonBoldData);
					pg65.setAlignment(Element.ALIGN_CENTER);
					
					pg66 = new Paragraph(("Terminal Test Average\n"
							+ "TT1+TT2/\n"
							+ "100*10"), fontTableHeadingNonBoldData);
					pg66.setAlignment(Element.ALIGN_CENTER);
					
					pg67 = new Paragraph(("Final Internal Assessment Marks"), fontTableHeadingNonBoldData);
					pg67.setAlignment(Element.ALIGN_CENTER);
					
					pg620 = new Paragraph(("A"), fontTableHeading);
					pg620.setAlignment(Element.ALIGN_CENTER);
					
					pg621 = new Paragraph(("B"), fontTableHeading);
					pg621.setAlignment(Element.ALIGN_CENTER);
					pg622 = new Paragraph(("C"), fontTableHeading);
					pg622.setAlignment(Element.ALIGN_CENTER);
					pg623 = new Paragraph(("D"), fontTableHeading);
					pg623.setAlignment(Element.ALIGN_CENTER);
					pg624 = new Paragraph(("E"), fontTableHeading);
					pg624.setAlignment(Element.ALIGN_CENTER);
					pg625 = new Paragraph(("F"), fontTableHeading);
					pg625.setAlignment(Element.ALIGN_CENTER);
					pg626 = new Paragraph(("G"), fontTableHeading);
					pg626.setAlignment(Element.ALIGN_CENTER);
					pg627 = new Paragraph(("D+G/2"), fontTableHeading);
					pg627.setAlignment(Element.ALIGN_CENTER);
					
					tabl6C.addCell(pg6);
					tabl6C.addCell(pg61);
					tabl6C.addCell(pg62);
					tabl6C.addCell(pg63);
					tabl6C.addCell(pg64);
					tabl6C.addCell(pg65);
					tabl6C.addCell(pg66);
					tabl6C.addCell(pg67);
					tabl6C.addCell(pg620);
					tabl6C.addCell(pg621);
					tabl6C.addCell(pg622);
					tabl6C.addCell(pg623);
					tabl6C.addCell(pg624);
					tabl6C.addCell(pg625);
					tabl6C.addCell(pg626);
					tabl6C.addCell(pg627);
// end of 8 E - Calculation Method for Internal assessment Marks
			
// Start Evaluation Methods for Periodical================================================================

			ArrayList<ArrayList<String>> T6DList = (ArrayList<ArrayList<String>>) TableEvaluation_Methods_list;
			PdfPTable table6Dnlaclink = new PdfPTable(1);
			table6Dnlaclink.setWidthPercentage(100);
			table6Dnlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table6Dnlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			PdfPTable table6Dnlac = new PdfPTable(1);
			table6Dnlac.setWidthPercentage(100);
			table6Dnlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6D = new Chunk("9C - Evaluation Methods for Periodical Assessment ", fontTableHeading);
			table6Dnlac.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t6D = new Phrase(underline6D);

			Paragraph cell6D = new Paragraph(t6D);
			cell6D.setAlignment(Element.ALIGN_CENTER);

			table6Dnlac.addCell(t6D);

			Paragraph pt6D1 = new Paragraph();
			Paragraph pt6D = new Paragraph();
			Paragraph pt6D2 = new Paragraph();
			PdfPTable table6Dnlacdata = new PdfPTable(2);
			table6Dnlacdata.setWidths(new int[] { 15, 150 });
			table6Dnlacdata.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pt6D1 = new Paragraph(TH8.get(0), fontTableHeading);
			pt6D1.setAlignment(Element.ALIGN_CENTER);
			table6Dnlacdata.addCell(pt6D1);
			pt6D2 = new Paragraph(TH8.get(1), fontTableHeading);
			pt6D2.setAlignment(Element.ALIGN_CENTER);
			table6Dnlacdata.addCell(pt6D2);

			table6Dnlacdata.setHeaderRows(1); // table first row will be repeated in all pages

			for (int i = 0; i < T6DList.size(); i++) {
				List<String> l = T6DList.get(i);
				for (int j = 0; j < l.size(); j++) {
					pt6D = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table6Dnlacdata.addCell(pt6D);
				}
			}
			table6Dnlaclink.addCell(table6Dnlacdata);

// End Evaluation Methods for Periodical//=========================================================================
			
			// Start Paper Layout=============================================================================================
						ArrayList<ArrayList<String>> TPList = (ArrayList<ArrayList<String>>) Paper_LayoutHOM;
						PdfPTable tab92 = new PdfPTable(3);
						tab92.setWidthPercentage(100);
						tab92.setWidths(new int[] { 50, 10,10 });
						tab92.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						tab92.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

						PdfPTable tableref2 = new PdfPTable(1);
						tableref2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						tableref2.setWidthPercentage(100);
						tableref2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

						Paragraph p921 = new Paragraph("9D-Paper Layout", fontTableHeading);
						tableref2.addCell(p921);

						Paragraph p922 = new Paragraph();
						Paragraph p923 = new Paragraph();
						Paragraph p924 = new Paragraph();
						Paragraph pq602 = new Paragraph();
						Paragraph pq6103 = new Paragraph();
						Paragraph pq6204 = new Paragraph();
						
						pq602 = new Paragraph(THPh50.get(0), fontTableHeading);
						pq602.setAlignment(Element.ALIGN_CENTER);

						pq6103 = new Paragraph(THPh50.get(1), fontTableHeading);
						pq6103.setAlignment(Element.ALIGN_CENTER);

						pq6204 = new Paragraph(THPh50.get(2), fontTableHeading);
						pq6204.setAlignment(Element.ALIGN_CENTER);
						
						tab92.addCell(pq602);
						tab92.addCell(pq6103);
						tab92.addCell(pq6204);
						
						tableref2.addCell(tab92);
						if (TPList.size() > 0) {

							for (int i = 0; i < TPList.size(); i++) {
								List<String> l = TPList.get(i);
								for (int j = 0; j < l.size(); j++) {
									py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
									tab92.addCell(py);

								}
							}
						}
					
			// End Paper Layout============================================================================================
						
			
// Start table 6FI-PaperI==========Riddhi=================================================================================
			
			PdfPTable table6FMHeader = new PdfPTable(1);
			table6FMHeader.setWidthPercentage(100);
			table6FMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6F = new Chunk("9 E– I - Distribution of Theory exam", fontTableHeading);
			table6FMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t6f = new Phrase(underline6F);

			Paragraph cell6f = new Paragraph(t6f);
			cell6f.setAlignment(Element.ALIGN_CENTER);

			table6FMHeader.addCell(t6f);

			PdfPTable table6FI = new PdfPTable(7);
			table6FI.setWidths(new int[] { 50, 250,50,50,50,50,50 });
			Paragraph p98;
			table6FI.setWidthPercentage(100);
			table6FI.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table6FI.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			Paragraph p6F1 = new Paragraph("", fontTableHeading);
			Paragraph p6F2 = new Paragraph("Paper ", fontTableHeading);
			Paragraph p6F3 = new Paragraph(
					"D\n" + "Type of Questions\n" + "“Yes” can be asked.\n" + "“No” should not be asked.",
					fontTableHeading);

			Paragraph p6F4 = new Paragraph("A\n" + "List of Topics", fontTableHeading);
			Paragraph p6F5 = new Paragraph("B\n" + "Term", fontTableHeading);
			Paragraph p6F6 = new Paragraph("C\n" + "Marks", fontTableHeading);
			Paragraph p6F7 = new Paragraph("MCQ\n" + "(1 Mark)", fontTableHeading);
			Paragraph p6F8 = new Paragraph("SAQ\n" + "(5 Mark)", fontTableHeading);
			Paragraph p6F9 = new Paragraph("LAQ\n" + "(10 Mark)", fontTableHeading);
			Paragraph sixftb1px = new Paragraph();

			ArrayList<ArrayList<String>> sixf1 = (ArrayList<ArrayList<String>>) Table6F_IDistribution_of_Theory_Exam_List_Theme;
			String yesNo = "";
				if(sixf1.size() > 0) {
					table6FI.addCell(p6F1);
					table6FI.addCell(p6F2);
					table6FI.addCell(p6F1);
					table6FI.addCell(p6F1);
					table6FI.getDefaultCell().setColspan(3);
					table6FI.addCell(p6F3);
					table6FI.getDefaultCell().setColspan(1);
					table6FI.addCell(p6F1);
					table6FI.addCell(p6F4);
					table6FI.addCell(p6F5);
					table6FI.addCell(p6F6);
					table6FI.addCell(p6F7);
					table6FI.addCell(p6F8);
					table6FI.addCell(p6F9);
			for (int i = 0; i < sixf1.size(); i++) {
				List<String> l = sixf1.get(i);
				for (int j = 0; j < l.size()-1; j++) {
					if (j == 3 || j == 4 || j == 5) {
						if (l.get(j).equals("0")) {
							yesNo = "NO";
						}
						if (l.get(j).equals("1")) {
							yesNo = "YES";
						}
						sixftb1px = new Paragraph(yesNo, fontTableHeadingNonBoldData);
						table6FI.addCell(sixftb1px);
					} else if(j == 0){
						sixftb1px = new Paragraph(String.valueOf(i+1), fontTableHeadingNonBoldData);
						table6FI.addCell(sixftb1px);
						sixftb1px = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table6FI.addCell(sixftb1px);
					}else {
						sixftb1px = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table6FI.addCell(sixftb1px);
					}
				}
			}
		}

// end 6FI- PaperI===========================================================================================
				
// Start table 6FI- PaperII===start==========Riddhi===========Theme=========================================
				
				PdfPTable table6FMHeader11 = new PdfPTable(1);
				table6FMHeader11.setWidthPercentage(100);
				table6FMHeader11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline6F11 = new Chunk("9 E – II - Theme table ", fontTableHeading);
				table6FMHeader11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase t6f11 = new Phrase(underline6F11);

				Paragraph cell6f11 = new Paragraph(t6f11);
				cell6f11.setAlignment(Element.ALIGN_CENTER);

				table6FMHeader11.addCell(t6f11);

				PdfPTable table6FI11 = new PdfPTable(7);
				table6FI11.setWidths(new int[] { 50, 250,50,50,50,50,50 });
				Paragraph p9811;
				table6FI11.setWidthPercentage(100);
				table6FI11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table6FI11.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				ArrayList<ArrayList<String>> sixf111 = (ArrayList<ArrayList<String>>) Table6F_IDistribution_of_Theory_Exam_List_II_Theme;
				Paragraph p6F111 = new Paragraph("Theme*", fontTableHeading);
				Paragraph p6F211 = new Paragraph(sixf111.get(0).get(6), fontTableHeading);

				Paragraph p6F411 = new Paragraph("Topics", fontTableHeading);
				Paragraph p6F511 = new Paragraph("Term", fontTableHeading);
				Paragraph p6F611 = new Paragraph("Marks", fontTableHeading);
				Paragraph p6F711 = new Paragraph("MCQ", fontTableHeading);
				Paragraph p6F811 = new Paragraph("SAQ", fontTableHeading);
				Paragraph p6F911 = new Paragraph("LAQ", fontTableHeading);
				Paragraph sixftb1px11 = new Paragraph();

						table6FI11.getDefaultCell().setColspan(7);
						table6FI11.addCell(p6F211);
						table6FI11.getDefaultCell().setColspan(1);
						table6FI11.addCell(p6F111);
						table6FI11.addCell(p6F411);
						table6FI11.addCell(p6F511);
						table6FI11.addCell(p6F611);
						table6FI11.addCell(p6F711);
						table6FI11.addCell(p6F811);
						table6FI11.addCell(p6F911);
	
						String yesNoITheme121 = "";
						for (int i = 0; i < sixf111.size(); i++) {
							List<String> l = sixf111.get(i);
							for (int j = 0; j < l.size()-1; j++) {
								if (j == 3 || j == 4 || j == 5) {
									if (l.get(j).equals("0")) {
										yesNoITheme121 = "NO";
									}
									if (l.get(j).equals("1")) {
										yesNoITheme121 = "YES";
									}
									sixftb1px11 = new Paragraph(yesNoITheme121, fontTableHeadingNonBoldData);
									table6FI11.addCell(sixftb1px11);
								}
								else if(j == 0){
									sixftb1px11 = new Paragraph(String.valueOf(i+1), fontTableHeadingNonBoldData);
									table6FI11.addCell(sixftb1px11);
									sixftb1px11= new Paragraph(l.get(j), fontTableHeadingNonBoldData);
									table6FI11.addCell(sixftb1px11);
								}else {
									sixftb1px11 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
									table6FI11.addCell(sixftb1px11);
								}
							}
						}
						
//end table 6FI- PaperII==========================================================================================
				
// Start 6 G Question paper Blue print for =========================================================================

				PdfPTable tableQ6 = new PdfPTable(1);
				tableQ6.setWidthPercentage(100);
				tableQ6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underlineQ6 = new Chunk("9F Question paper Blue print :", fontTableHeading);
				tableQ6.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase tQ6 = new Phrase(underlineQ6);

				Paragraph cellQ6 = new Paragraph(tQ6);
				cellQ6.setAlignment(Element.ALIGN_CENTER);

				tableQ6.addCell(tQ6);
				PdfPTable tableQP6= new PdfPTable(1);
				tableQP6.setWidthPercentage(100);
				tableQP6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				tableQP6.getDefaultCell().setBorder(Rectangle.NO_BORDER);

				Paragraph pq6 = new Paragraph();
				Paragraph pq61 = new Paragraph();
				Paragraph pq62 = new Paragraph();
				Paragraph pq63 = new Paragraph();
				Paragraph pq64 = new Paragraph();
				Paragraph pq65 = new Paragraph();
				Paragraph pq66 = new Paragraph();
				Paragraph pq67 = new Paragraph();
				Paragraph pq68 = new Paragraph();
				Paragraph pq69 = new Paragraph();
				Paragraph pq610 = new Paragraph();
				Paragraph pq611 = new Paragraph();
				PdfPTable tableqp6 = new PdfPTable(3);
				tableqp6.setWidths(new int[] { 3, 13, 12 });
				tableqp6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

				pq6 = new Paragraph(THQ6.get(0), fontTableHeading);
				pq6.setAlignment(Element.ALIGN_CENTER);

				pq61 = new Paragraph(THQ6.get(1), fontTableHeading);
				pq61.setAlignment(Element.ALIGN_CENTER);

				pq62 = new Paragraph(THQ6.get(2), fontTableHeading);
				pq62.setAlignment(Element.ALIGN_CENTER);

				pq63 = new Paragraph(THQ6.get(3), fontTableHeadingNonBoldData);
				pq63.setAlignment(Element.ALIGN_CENTER);

				pq64 = new Paragraph(THQ6.get(4), fontTableHeadingNonBoldData);
				pq64.setAlignment(Element.ALIGN_LEFT);

				pq66 = new Paragraph(THQ6.get(5), fontTableHeadingNonBoldData);
				pq66.setAlignment(Element.ALIGN_LEFT);

				pq67 = new Paragraph(THQ6.get(6), fontTableHeadingNonBoldData);
				pq67.setAlignment(Element.ALIGN_LEFT);

				pq69 = new Paragraph(THQ6.get(7), fontTableHeadingNonBoldData);
				pq69.setAlignment(Element.ALIGN_LEFT);

				pq610 = new Paragraph(THQ6.get(8), fontTableHeadingNonBoldData);
				pq610.setAlignment(Element.ALIGN_LEFT);

				String topics = "";
				for (int e = 0; e < table61_list.size(); e++) {
					List<String> l = table61_list.get(e);
					for (int j = 0; j < l.size(); j++) {
						if (j == 0) {
							topics += "\n" + l.get(j);
						}
						if (j == 1) {
						}
					}
				}
				pq65 = new Paragraph(topics, fontTableHeadingNonBoldData);

				String topics1 = "";
				for (int e = 0; e < table62_list.size(); e++) {
					List<String> l = table62_list.get(e);
					for (int j = 0; j < l.size(); j++) {
						if (j == 0) {
							topics1 += "\n" + l.get(j);
						}
						if (j == 1) {
						}
					}
				}

				pq68 = new Paragraph(topics1, fontTableHeadingNonBoldData);

				String topics2 = "";
				for (int e = 0; e < table63_list.size(); e++) {
					List<String> l = table63_list.get(e);
					for (int j = 0; j < l.size(); j++) {
						if (j == 0) {
							topics2 += "\n" + l.get(j);
						}
						if (j == 1) {
						}
					}
				}

				pq611 = new Paragraph(topics2, fontTableHeadingNonBoldData);

				tableqp6.addCell(pq6);
				tableqp6.addCell(pq61);
				tableqp6.addCell(pq62);
				tableqp6.addCell(pq63);
				tableqp6.addCell(pq64);
				tableqp6.addCell(pq65);
				tableqp6.addCell(pq66);
				tableqp6.addCell(pq67);
				tableqp6.addCell(pq68);
				tableqp6.addCell(pq69);
				tableqp6.addCell(pq610);
				tableqp6.addCell(pq611);
				tableQP6.addCell(tableqp6);

//end of 6 G Question paper Blue print ==================================================================================
			
			
//Start Distribution of Practical Exam======================================================================================

				ArrayList<ArrayList<String>> Distri_Pract_List = (ArrayList<ArrayList<String>>) Distri_Pract_Exam;
				PdfPTable tab923 = new PdfPTable(2);

				tab923.setWidthPercentage(100);
				tab923.setWidths(new int[] { 50, 20 });
				tab923.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				tab923.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPTable table2111 = new PdfPTable(1);
				table2111.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table2111.setWidthPercentage(100);
				table2111.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

				Paragraph pag2 = new Paragraph("9 G -Distribution of Practical Exam", fontTableHeading);
				table2111.addCell(pag2);

				Paragraph pag21 = new Paragraph("Practical, Viva & Internal Assessment-100 marks", fontTableHeading);
				table2111.addCell(pag21);

				for (int i = 0; i < Distri_Pract_List.size(); i++) {
					List<String> l = Distri_Pract_List.get(i);
					for (int j = 0; j < l.size(); j++) {
						py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						tab923.addCell(py);

					}
				}

// End of Distribution of Practical Exam========================================================================
				
// Start Reference and Resourses===============================================================================

				PdfPTable tblref7 = new PdfPTable(1);
				tblref7.setWidthPercentage(100);
				tblref7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underlinersRef = new Chunk("LIST OF RECOMMENDED BOOKS :- ", fontTableHeading);
				tblref7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase resourses7 = new Phrase(underlinersRef);

				Paragraph cellrefre7 = new Paragraph(resourses7);
				cellrefre7.setAlignment(Element.ALIGN_CENTER);

				Font fontTableHeadingNonBoldDataresour7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

				Phrase Resour = new Phrase();
				String Resourses = "";
				int ser3 = 1;
				ArrayList<ArrayList<String>> tblresList = (ArrayList<ArrayList<String>>) Reference_Resourses_list;
				for (int i = 0; i < tblresList.size(); i++) {
					Resourses += "\n" + " " + ser3 + ". " + tblresList.get(i).get(0);
					ser3++;
				}
				Resour = new Phrase(Resourses, fontTableHeadingNonBoldDataresour7);

// End Reference and Resourses===========================================================================

			
// START footer=========================================================================================

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

		if (Type.equals("L")) {

			document.setPageSize(PageSize.A4); // set document landscape

		}

// end of footer============================
		
		
		// Sys Deg Prof Course code and name
		document.add(Table1);

		Phrase singleSN = new Phrase();
		Phrase doubleSN = new Phrase();
		singleSN = new Phrase("\n");
		doubleSN = new Phrase("\n\n");
		
// PO
		document.add(doubleSN);
		document.add(add2);
		document.add(singleSN);
		document.add(add_program_course);
		
// CO
		document.add(doubleSN);
		document.add(add1);
		document.add(singleSN);
		document.add(add_course);
		
//		Start Teaching hour
		document.add(doubleSN);
		document.add(t1);
		table.addCell(tablexx);
		table.addCell(tablex);
		document.add(table32);
		document.add(singleSN);
//		end of Teaching hour
		
		//TOPIC SUBTOPIC
		document.add(singleSN);
		document.add(table2MHeader);
		document.add(singleSN);
		document.add(table2a);
		document.add(singleSN);
		
//Start Practical_List
		document.add(singleSN);
		document.add(tablePMHeader);
		document.add(table23P);
				
//end Practical_List				
		
// Start TEACHING LEARNING METHODS
		document.add(singleSN);
		document.add(TEC1);
//		document.add(singleSN);
		document.add(Tec_Learning);
//  end	TEACHING LEARNING METHODS	
		
// Start of Table 2-Learning Objectives (Practical) 
		
		document.newPage();
		document.add(table3HOMHeader);
		document.add(singleSN);
		document.add(table3HOM);
		document.add(singleSN);
// End of Table 2-Learning Objectives (Practical) 
				
// Non-Lecture Activities
		document.add(add);
		document.add(singleSN);
		document.add(Non_Lecture_Activities);
				
//	Start Table- 8(A)Assessment Summary
				
		document.add(table60Dnlach1);
		document.add(table6A);
		document.add(singleSN);
				
//	end Table- 8(A)Assessment Summary
		
		//Start  8(D)Scheme of Assessment (formative)
				document.add(singleSN);
				document.add(table6D1);
				document.add(singleSN);
				document.add(table6Dformative);
				document.add(table6D);
				document.add(singleSN);

				//End  8(D)Scheme of Assessment (formative)
				
				//Start  8(E) Calculation of Internal Assessment
				document.newPage();
				 document.add(tableref33);
				 document.add(singleSN);
				 document.add(tabl6C);
				 document.add(tabl6C1);
				 document.add(singleSN);
				//End  8(E) Calculation of Internal Assessment
				 
//	Start Evaluation Methods

		document.add(t6D);
		document.add(singleSN);
		document.add(table6Dnlaclink);
//		document.add(singleSN);

//	end Evaluation Methods	
		
// Start Paper Layout ////
		document.add(singleSN);
		document.add(tableref2);
//		document.add(singleSN);
//		document.add(tab92);
		document.add(singleSN);

// end Paper Layout //
		
////Start Distribution of Theory Exam ///////
		document.newPage(); 
		document.add(table6FMHeader);
//		document.add(singleSN);
		document.add(table6FI);
		document.add(singleSN);
//		document.add(table6FII);
		document.add(singleSN);
		document.add(table6FMHeader11);
		document.add(table6FI11);
//// end Distribution of Theory Exam ///////
///// Start Question Blue Print ===============
			
			document.newPage();  // to add new page in pdf and add next stuff in that new page  
			document.add(singleSN);
			document.add(tableQ6);
			document.add(tableQP6);
			document.add(singleSN);
/////End Question Blue Print ===============
				
		
//// Distribution of Practical Exam ///////

		document.add(table2111);
		document.add(tab923);
		document.add(singleSN);

//// Distribution of Practical Exam ///////
		
//		Start Reference and Resourses
		document.newPage(); 
		document.add(resourses7);
		document.add(singleSN);
		document.add(Resour);

//		End Reference and Resourses	
		super.buildPdfMetadata(model, document, request);
	}

}
