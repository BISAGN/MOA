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
import com.google.zxing.common.detector.MathUtils;
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

public class NCHHomoeopathicMateriaMedicaPdfDownload extends AbstractPdfView {
	String Type = "";
	String username = "";
	String role = "";
	String Heading = "";
	int total = 0;
	int total1 = 0;
	
	List<Map<String, Object>> sysdegprofcorsnamecode;
	List<ArrayList<String>> Program_Outcomes_list;
	List<ArrayList<String>> Course_Outcomes_list;
	List<ArrayList<String>> Specific_Objective_list;
	List<String> TH;
	List<ArrayList<String>> Teaching_hour;
	List<String> TH1;
	List<ArrayList<String>> List_of_Topic_listHOM;
	List<String> TH2;
	List<ArrayList<String>> Non_Lecture_Activities_List_HOM;
	List<ArrayList<String>> Teaching_Method_list;
	List<String> TH2loc;
	List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list;
	List<String> TH6;
	List<ArrayList<String>> table6_number_of_papers_listP;
	List<ArrayList<String>> table6d_term1list;
	List<ArrayList<String>> table6d_term2list;
	List<ArrayList<String>> table6d_term3list;
	List<String> TH8;
	List<ArrayList<String>> TableEvaluation_Methods_list;
	List<String> THM50;
	List<ArrayList<String>> Paper_LayoutHOM;
	List<ArrayList<String>> Distri_Pract_Exam;
	List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> table61_list;
	List<ArrayList<String>> table62_list;
	List<ArrayList<String>> table63_list;
	List<String> THQ6;
	List<ArrayList<String>> Reference_Resourses_list;
	
	public NCHHomoeopathicMateriaMedicaPdfDownload(String Type,String Heading, String role,
			List<Map<String, Object>> sysdegprofcorsnamecode,
			List<ArrayList<String>> Program_Outcomes_list,
			List<ArrayList<String>> Course_Outcomes_list,
			List<ArrayList<String>> Specific_Objective_list,
			List<String> TH,
			List<ArrayList<String>> Teaching_hour,
			List<String> TH1,
			List<ArrayList<String>> List_of_Topic_listHOM,
			List<String> TH2,
			List<ArrayList<String>> Non_Lecture_Activities_List_HOM,
			List<ArrayList<String>> Teaching_Method_list,
			List<String> TH2loc,List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list,
			List<String> TH6, List<ArrayList<String>> table6_number_of_papers_listP,
			List<ArrayList<String>> table6d_term1list, List<ArrayList<String>> table6d_term2list,
			List<ArrayList<String>> table6d_term3list,
			List<String> TH8,List<ArrayList<String>> TableEvaluation_Methods_list,
			List<String> THM50, List<ArrayList<String>> Paper_LayoutHOM,List<ArrayList<String>> Distri_Pract_Exam,
			List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List,
			List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List,
			List<ArrayList<String>> table61_list,
	    	List<ArrayList<String>> table62_list,
	    	List<ArrayList<String>> table63_list,
	    	List<String> THQ6,
			List<ArrayList<String>> Reference_Resourses_list) {

		this.Type = Type;
		this.Heading = Heading;
		this.role = role;
		this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
		this.Program_Outcomes_list = Program_Outcomes_list;
		this.Course_Outcomes_list = Course_Outcomes_list;
		this.Specific_Objective_list = Specific_Objective_list;
		this.TH = TH;
		this.Teaching_hour = Teaching_hour;
		this.TH1 = TH1;
		this.List_of_Topic_listHOM = List_of_Topic_listHOM;
		this.TH2 = TH2;
		this.Non_Lecture_Activities_List_HOM = Non_Lecture_Activities_List_HOM;
		this.Teaching_Method_list = Teaching_Method_list;
		this.TH2loc = TH2loc;
		this.table2_Learning_Objectives_of_Course_HomUG_list = table2_Learning_Objectives_of_Course_HomUG_list;
		this.TH6 = TH6;
		this.table6_number_of_papers_listP = table6_number_of_papers_listP;
		this.table6d_term1list = table6d_term1list;
		this.table6d_term2list = table6d_term2list;
		this.table6d_term3list = table6d_term3list;
		this.TH8 = TH8;
		this.TableEvaluation_Methods_list = TableEvaluation_Methods_list;
		this.THM50 = THM50;
		this.Paper_LayoutHOM = Paper_LayoutHOM;
		this.Distri_Pract_Exam = Distri_Pract_Exam;
		this.Table6F_IDistribution_of_Theory_Exam_List = Table6F_IDistribution_of_Theory_Exam_List;	
		this.Table6F_IIDistribution_of_Theory_Exam_List = Table6F_IIDistribution_of_Theory_Exam_List;
		this.table61_list = table61_list;
		this.table62_list = table62_list;
		this.table63_list = table63_list;
		this.THQ6 = THQ6;
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

// For System Degree Prof Course Name and Code =====================================

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

// For System Degree Prof Course Name and Code ====================================

// Strt Program_Outcomes========================================================

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

// End Program_Outcomes=======================================================================
				
// Strt COURSE OUTCOMES=========================================================================

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

// End COURSE OUTCOMES=====================================================================

// Strt SPECIFIC_OBJECTIVE================================================================

				PdfPTable tablerefadd = new PdfPTable(1);
				tablerefadd.setWidthPercentage(100);
				tablerefadd.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underliners = new Chunk("LEARNING OBJECTIVES ", fontTableHeading);
				tablerefadd.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase add = new Phrase(underliners);

				Paragraph celladd = new Paragraph(add);
				celladd.setAlignment(Element.ALIGN_CENTER);

				Font fontTableHeadingNonBoldDataadd = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

				Phrase Specific_course = new Phrase();
				String Specific_course1 = "";
				int ser = 1;
				ArrayList<ArrayList<String>> addListS = (ArrayList<ArrayList<String>>) Specific_Objective_list;
				for (int i = 0; i < addListS.size(); i++) {
					if (i == 0) {
						Specific_course1 += ser + ". " + addListS.get(i).get(0);
						ser++;
					} else {
						Specific_course1 += "\n\n" + " " + ser + ". " + addListS.get(i).get(0);
						ser++;
					}
				}
				Specific_course = new Phrase(Specific_course1, fontTableHeadingNonBoldDataadd);

// End SPECIFIC_OBJECTIVE=====================================================================
				
//==============Start================== Teaching hour========================================

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

		PdfPTable table32 = new PdfPTable(3);
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
//==============END================== Teaching hour========================================
		
////==============Start================== List of Topic=======================================

		PdfPTable tableHOM = new PdfPTable(1);
		tableHOM.setWidthPercentage(100);
		tableHOM.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline5 = new Chunk(" TEACHING HOURS THEORY ", fontTableHeading);
		tableHOM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t2 = new Phrase(underline5);

		Paragraph cell2 = new Paragraph(t2);
		cell2.setAlignment(Element.ALIGN_CENTER);
		tableHOM.addCell(t2);

		ArrayList<ArrayList<String>> aListho = (ArrayList<ArrayList<String>>) List_of_Topic_listHOM;
		PdfPTable tableHO1 = new PdfPTable(1);
		tableHO1.setWidthPercentage(100);
		tableHO1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableHO1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDataho = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Paragraph pyh = new Paragraph();

		PdfPTable tableho = new PdfPTable(3);
		tableho.setWidthPercentage(100);
		tableho.setWidths(new int[] { 15, 150, 50});
		tableho.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH1.size(); h++) {
			pyh = new Paragraph(TH1.get(h), fontTableHeading);
			pyh.setAlignment(Element.ALIGN_CENTER);
			tableho.addCell(pyh);
		}
		tableho.setHeaderRows(1); // table first row will be repeated in all pages
		int a0 = 0;

		for (int i = 0; i < aListho.size(); i++) {
			List<String> l = aListho.get(i);

			a0 += Integer.parseInt(aListho.get(i).get(2));

			for (int j = 0; j < l.size(); j++) {
				pyh = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tableho.addCell(pyh);
			}
		}
		int sum = a0;
		Paragraph pt551 = new Paragraph("", fontTableHeadingNonBoldData);
		tableho.addCell(pt551);
		Paragraph pt552 = new Paragraph("Total", fontTableHeading);
		tableho.addCell(pt552);
		Paragraph pt553 = new Paragraph(String.valueOf(sum), fontTableHeading);
		tableho.addCell(pt553);
		tableHO1.addCell(tableho);

////==============END================== List of Topic======================================================

////==============Start================== Non-Lecture Activities (Practical)==================================

		PdfPTable tablenon = new PdfPTable(1);
		tablenon.setWidthPercentage(100);
		tablenon.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6 = new Chunk("NON-LECTURE ACTIVITIES (PRACTICAL) ", fontTableHeading);
		tablenon.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t3 = new Phrase(underline6);

		Paragraph cell3 = new Paragraph(t3);
		cell3.setAlignment(Element.ALIGN_CENTER);
		tablenon.addCell(t3);

		ArrayList<ArrayList<String>> aListNON = (ArrayList<ArrayList<String>>) Non_Lecture_Activities_List_HOM;
		PdfPTable tableNon1 = new PdfPTable(1);
		tableNon1.setWidthPercentage(100);
		tableNon1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableNon1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatanon = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Paragraph pynon = new Paragraph();

		PdfPTable tableNon2 = new PdfPTable(3);
		tableNon2.setWidthPercentage(100);
		tableNon2.setWidths(new int[] { 15, 150, 50});
		tableNon2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH2.size(); h++) {
			pynon = new Paragraph(TH2.get(h), fontTableHeading);
			pynon.setAlignment(Element.ALIGN_CENTER);
			tableNon2.addCell(pynon);
		}
		tableNon2.setHeaderRows(1); // table first row will be repeated in all pages
		int a1 = 0;

		for (int i = 0; i < aListNON.size(); i++) {
			List<String> l = aListNON.get(i);

			a1 += Integer.parseInt(aListNON.get(i).get(2));

			for (int j = 0; j < l.size(); j++) {
				pynon = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tableNon2.addCell(pynon);
			}
		}
		int sum1 = a1;
		Paragraph ptnon1 = new Paragraph("", fontTableHeadingNonBoldData);
		tableNon2.addCell(ptnon1);
		Paragraph ptnon2 = new Paragraph("Total", fontTableHeading);
		tableNon2.addCell(ptnon2);
		Paragraph ptnon3 = new Paragraph(String.valueOf(sum1), fontTableHeading);
		tableNon2.addCell(ptnon3);
		tableNon1.addCell(tableNon2);

////==============END================== Non-Lecture Activities (Practical)=============================================
		
// Strt TEACHING LEARNING METHODS=============================================================================
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

// End TEACHING LEARNING METHODS===============================================================
			
// Table 2-Learning Objectives (Theory) of Course HomUG-OM-I============================================
			
			ArrayList<ArrayList<String>> t2HOMList = (ArrayList<ArrayList<String>>) table2_Learning_Objectives_of_Course_HomUG_list;
			PdfPTable table2HOMHeader = new PdfPTable(1);
			table2HOMHeader.setWidthPercentage(100);
			table2HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underlineHOM = new Chunk("Table 2-Learning Objectives (Theory)", fontTableHeading);
			table2HOMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t2HOM = new Phrase(underlineHOM);

			Paragraph cell2HOM = new Paragraph(underlineHOM);
			cell2HOM.setAlignment(Element.ALIGN_CENTER);

			table2HOMHeader.addCell(t2HOM);

			Paragraph pt32HOM = new Paragraph();
			PdfPTable table2HOM = new PdfPTable(12);
			table2HOM.setWidthPercentage(100);
			table2HOM.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			for (int h = 0; h < TH2loc.size(); h++) {
				pt32HOM = new Paragraph(TH2loc.get(h), fontTableHeading);
				pt32HOM.setAlignment(Element.ALIGN_CENTER);
				table2HOM.addCell(pt32HOM);
			}

			String papertype11 = "";
			String topictype11 = "";

			for (int i = 0; i < t2HOMList.size(); i++) {
				List<String> l = t2HOMList.get(i);
				for (int j = 0; j < l.size(); j++) {
					if (i == 0 && j == 0) {
						papertype11 = l.get(j);
						Paragraph ptt32HOM = new Paragraph(papertype11, fontTableHeading);
						ptt32HOM.setAlignment(Element.ALIGN_CENTER);
						table2HOM.getDefaultCell().setColspan(12);
						table2HOM.addCell(ptt32HOM);
						table2HOM.getDefaultCell().setColspan(1);

					}
					if (j == 0) {
						if (!papertype11.equals(l.get(j))) {
							papertype11 = l.get(j);
							Paragraph ptt32HOM = new Paragraph(papertype11, fontTableHeading);
							ptt32HOM.setAlignment(Element.ALIGN_CENTER);
							table2HOM.getDefaultCell().setColspan(13);
							table2HOM.addCell(ptt32HOM);
							table2HOM.getDefaultCell().setColspan(1);
						}
					}
					if (j != 0) {
						Paragraph ptt32HOM = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table2HOM.addCell(ptt32HOM);
					}
				}
			}
			table.addCell(table2HOM);

// Table 2-Learning Objectives (Theory) of Course HomUG-OM-I=========================
			
// start Assessment Summary=============================================================

			PdfPTable table60Dnlach1 = new PdfPTable(1);
			table60Dnlach1.setWidthPercentage(100);
			table60Dnlach1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6DH = new Chunk("8.Assessment", fontTableHeading);
			Chunk underline6DH2 = new Chunk("8A - Number of papers and Mark Distribution ", fontTableHeading);
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
			Chunk underline6A = new Chunk("8A - Number of Papers and Marks Distribution", fontTableHeading);
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
			table6anop1.setWidths(new int[] { 8, 24, 12, 12, 13, 8, 20, 8 });
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
// End Assessment Summary=====================================================================================
			
			//6-D================ Start Scheme of Assessment (formative)=========================

			PdfPTable table6D = new PdfPTable(1);
			table6D.setWidthPercentage(100);
			table6D.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6D1 = new Chunk("PA: Periodical Assessment to be done only through practical/viva; TT: Term Test shall include only viva\n"
					+ "UE: University Examinations shall include both theory and viva as per table 8A)",
					fontTableHeading);
			table6D.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t6D1 = new Phrase(underline6D1);

			Paragraph cell6D1 = new Paragraph(t6D1);
			cell6A.setAlignment(Element.ALIGN_CENTER);

			table6D.addCell(t6D1);

			PdfPTable table6D1 = new PdfPTable(1);
			table6D1.setWidthPercentage(100);
			table6D1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underline6D11 = new Chunk("8B-I - Scheme of Assessment (Formative) ", fontTableHeading);
			table6D1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t6D11 = new Phrase(underline6D11);

			Paragraph cell6D11 = new Paragraph(t6D11);
			cell6D11.setAlignment(Element.ALIGN_CENTER);

			table6D1.addCell(t6D11);

			PdfPTable table6Dformative = new PdfPTable(8);
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
			
			if (table6d_term3list.get(0).size() > 0) {
				p6D15 = new Paragraph(table6d_term3list.get(0).get(2).split("\\+")[1]);
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
			table6Dformative.addCell(p6D15);
			table6Dformative.getDefaultCell().setRowspan(1);
			table6Dformative.addCell("10 Marks Practical/Viva");
			table6Dformative.addCell("50 Marks Practical/ Viva");
			table6Dformative.addCell("10 Marks Practical/Viva");
			table6Dformative.addCell("50 Marks Practical/ Viva");
			table6Dformative.addCell("10 Marks Practical/Viva");
			
//6-D===================== End Scheme of Assessment (formative)====================================
			
//// Start Evaluation Methods for Periodical//====================================================================
//
//			ArrayList<ArrayList<String>> T6DList = (ArrayList<ArrayList<String>>) TableEvaluation_Methods_list;
//			PdfPTable table6Dnlaclink = new PdfPTable(1);
//			table6Dnlaclink.setWidthPercentage(100);
//			table6Dnlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			table6Dnlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//
//			PdfPTable table6Dnlac = new PdfPTable(1);
//			table6Dnlac.setWidthPercentage(100);
//			table6Dnlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//			Chunk underline6D = new Chunk("8C -Evaluation Methods for Periodical Assessment ", fontTableHeading);
//			table6Dnlac.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			Phrase t6D = new Phrase(underline6D);
//
//			Paragraph cell6D = new Paragraph(t6D);
//			cell6D.setAlignment(Element.ALIGN_CENTER);
//
//			table6Dnlac.addCell(t6D);
//
//			Paragraph pt6D1 = new Paragraph();
//			Paragraph pt6D = new Paragraph();
//			Paragraph pt6D2 = new Paragraph();
//			PdfPTable table6Dnlacdata = new PdfPTable(2);
//			table6Dnlacdata.setWidths(new int[] { 15, 150 });
//			table6Dnlacdata.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			pt6D1 = new Paragraph(TH8.get(0), fontTableHeading);
//			pt6D1.setAlignment(Element.ALIGN_CENTER);
//			table6Dnlacdata.addCell(pt6D1);
//			pt6D2 = new Paragraph(TH8.get(1), fontTableHeading);
//			pt6D2.setAlignment(Element.ALIGN_CENTER);
//			table6Dnlacdata.addCell(pt6D2);
//
//
//			table6Dnlacdata.setHeaderRows(1); // table first row will be repeated in all pages
//
//			for (int i = 0; i < T6DList.size(); i++) {
//				List<String> l = T6DList.get(i);
//				for (int j = 0; j < l.size(); j++) {
//					pt6D = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
//					table6Dnlacdata.addCell(pt6D);
//				}
//			}
//
//			table6Dnlaclink.addCell(table6Dnlacdata);
//
//// End Evaluation Methods for Periodical//==========================================
			
			
			//8 B-II - Calculation Method for Internal assessment Marks  ==========================

			PdfPTable tabl6C = new PdfPTable(8);
			tabl6C.setWidthPercentage(100);
			tabl6C.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			tabl6C.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			PdfPTable tableref33 = new PdfPTable(1);
			tableref33.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tableref33.setWidthPercentage(100);
			tableref33.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

			Paragraph pa6C = new Paragraph("8B-II - Method of Calculation of Internal Assessment Marks for Final University Examination: ",
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
					+ "10"), fontTableHeadingNonBoldData);
			pg66.setAlignment(Element.ALIGN_CENTER);
			
			pg67 = new Paragraph(("Final Internal Assessment Marks"), fontTableHeadingNonBoldData);
			pg67.setAlignment(Element.ALIGN_CENTER);
			
			pg620 = new Paragraph(("A"), fontTableHeading);
			pg620.setAlignment(Element.ALIGN_CENTER);
			
			pg621 = new Paragraph(("B"), fontTableHeading);
			pg621.setAlignment(Element.ALIGN_CENTER);
			pg622 = new Paragraph(("C"), fontTableHeading);
			pg622.setAlignment(Element.ALIGN_CENTER);
			pg623 = new Paragraph(("D=A+B+C/3"), fontTableHeading);
			pg623.setAlignment(Element.ALIGN_CENTER);
			pg624 = new Paragraph(("E"), fontTableHeading);
			pg624.setAlignment(Element.ALIGN_CENTER);
			pg625 = new Paragraph(("F"), fontTableHeading);
			pg625.setAlignment(Element.ALIGN_CENTER);
			pg626 = new Paragraph(("G=E+F/10"), fontTableHeading);
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

			// end of 8 B-II- Calculation Method for Internal assessment Marks
			
///////////////////  Start Paper Layout////////////////===============================
		ArrayList<ArrayList<String>> TPList = (ArrayList<ArrayList<String>>) Paper_LayoutHOM;
		PdfPTable tab92 = new PdfPTable(2);
		tab92.setWidthPercentage(100);
		tab92.setWidths(new int[] { 50, 10 });
		tab92.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		tab92.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref2 = new PdfPTable(1);
		tableref2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref2.setWidthPercentage(100);
		tableref2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

		Paragraph p921 = new Paragraph("8D-Paper Layout", fontTableHeading);
		tableref2.addCell(p921);
		Paragraph p9211 = new Paragraph("Summative assessment:", fontTableHeading);
		tableref2.addCell(p9211);
		Paragraph p9212 = new Paragraph("Theory- 100 marks", fontTableHeading);
		tableref2.addCell(p9212);

		Paragraph p922 = new Paragraph();
		Paragraph p923 = new Paragraph();
		Paragraph p924 = new Paragraph();
		Paragraph pq602 = new Paragraph();
		Paragraph pq6103 = new Paragraph();
		
		Paragraph pm32HOM = new Paragraph();
		
		pq602 = new Paragraph(THM50.get(0), fontTableHeading);
		pq602.setAlignment(Element.ALIGN_CENTER);

		pq6103 = new Paragraph(THM50.get(1), fontTableHeading);
		pq6103.setAlignment(Element.ALIGN_CENTER);
		
		tab92.addCell(pq602);
		tab92.addCell(pq6103);

		if (TPList.size() > 0) {

			for (int i = 0; i < TPList.size(); i++) {
				List<String> l = TPList.get(i);
				for (int j = 0; j < l.size(); j++) {
					py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					tab92.addCell(py);

				}
			}
		}
	
///////////////////  End Paper Layout////////////////===============================
			
////////// Start Distribution of Practical Exam========================================

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

				Paragraph pag2 = new Paragraph("8 G -Distribution of Practical Exam", fontTableHeading);
				table2111.addCell(pag2);

				Paragraph pag21 = new Paragraph("Practical & Viva-100 marks", fontTableHeading);
				table2111.addCell(pag21);

				for (int i = 0; i < Distri_Pract_List.size(); i++) {
					List<String> l = Distri_Pract_List.get(i);
					for (int j = 0; j < l.size(); j++) {
						py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						tab923.addCell(py);

					}
				}

// End of Distribution of Practical Exam=========================================================
				
// Start table 6FI-PaperI==========Riddhi===========================================================
				PdfPTable table6FMHeader = new PdfPTable(1);
				table6FMHeader.setWidthPercentage(100);
				table6FMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline6F = new Chunk("8E - I - Distribution of Theory exam ", fontTableHeading);
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

				Paragraph p6F1 = new Paragraph(" ", fontTableHeading);
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

				ArrayList<ArrayList<String>> sixf1 = (ArrayList<ArrayList<String>>) Table6F_IDistribution_of_Theory_Exam_List;
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
					for (int j = 0; j < l.size(); j++) {
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
// end 6FI- PaperI=====================================================================
					
// Start table 6FI- PaperII===start==========Riddhi==========================================
					
					PdfPTable table6F2MHeader = new PdfPTable(1);
					table6F2MHeader.setWidthPercentage(100);
					table6F2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underline6F2 = new Chunk("8E– II - Theme table", fontTableHeading);
					table6F2MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					Phrase t6f2 = new Phrase(underline6F2);
					Paragraph cell6f2 = new Paragraph(t6f2);
					cell6f2.setAlignment(Element.ALIGN_CENTER);

					table6F2MHeader.addCell(t6f2);
					
					PdfPTable table6FII = new PdfPTable(7);
					table6FII.setWidths(new int[] { 50, 250,50,50,50,50,50 });
					Paragraph p99;
					table6FII.setWidthPercentage(100);
					table6FII.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					table6FII.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

					Paragraph p6FI3 = new Paragraph("Theme*", fontTableHeading);
					Paragraph p6FI4 = new Paragraph("Topics", fontTableHeading);
					Paragraph p6FI5 = new Paragraph("Term", fontTableHeading);
					Paragraph p6FI6 = new Paragraph("Marks", fontTableHeading);
					Paragraph p6FI7 = new Paragraph("MCQ", fontTableHeading);
					Paragraph p6FI8 = new Paragraph("SAQ", fontTableHeading);
					Paragraph p6FI9 = new Paragraph("LAQ", fontTableHeading);

					table6FII.addCell(p6FI3);
					table6FII.addCell(p6FI4);
					table6FII.addCell(p6FI5);
					table6FII.addCell(p6FI6);
					table6FII.addCell(p6FI7);
					table6FII.addCell(p6FI8);
					table6FII.addCell(p6FI9);

					Paragraph sixftbII = new Paragraph();

					ArrayList<ArrayList<String>> sixfII = (ArrayList<ArrayList<String>>) Table6F_IIDistribution_of_Theory_Exam_List;
					String yesNoII = "";
					for (int i = 0; i < sixfII.size(); i++) {
						List<String> l = sixfII.get(i);
						for (int j = 0; j < l.size(); j++) {
							if (j == 3 || j == 4 || j == 5) {
								if (l.get(j).equals("0")) {
									yesNoII = "NO";
								}
								if (l.get(j).equals("1")) {
									yesNoII = "YES";
								}
								sixftbII = new Paragraph(yesNoII, fontTableHeadingNonBoldData);
								table6FII.addCell(sixftbII);
							}
							else if(j == 0){
								sixftbII = new Paragraph(String.valueOf(i+1), fontTableHeadingNonBoldData);
								table6FII.addCell(sixftbII);
								sixftbII = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
								table6FII.addCell(sixftbII);
							}else {
								sixftbII = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
								table6FII.addCell(sixftbII);
							}
						}
					}

// end table 6FI- PaperII=========================================================
					
// Start 6 G Question paper Blue print for ========================================

					PdfPTable tableQ6 = new PdfPTable(1);
					tableQ6.setWidthPercentage(100);
					tableQ6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					Chunk underlineQ6 = new Chunk("8F- Question paper Blue print", fontTableHeading);
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

// end of 6 G Question paper Blue print ==================================================
				
// Start Reference and Resourses==========================================================

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
				int ser3 = 1;
				ArrayList<ArrayList<String>> tblresList = (ArrayList<ArrayList<String>>) Reference_Resourses_list;
				for (int i = 0; i < tblresList.size(); i++) {
					Resourses += "\n" + " " + ser3 + ". " + tblresList.get(i).get(0);
					ser3++;
				}
				Resour = new Phrase(Resourses, fontTableHeadingNonBoldDataresour7);

// End Reference and Resourses===========================================================

			
// START footer============================================================================

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

// end of footer======================================================================
		
		
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
		
// LEARNING OBJECTIVES
		document.add(doubleSN);
		document.add(add);
		document.add(singleSN);
		document.add(Specific_course);
		
//		Start Teaching hour
		document.add(doubleSN);
		document.add(t1);
		table.addCell(tablexx);
		table.addCell(tablex);
		document.add(table32);
		document.add(singleSN);
//		end of Teaching hour
		
//  Start List of Topic
		document.add(singleSN);
		document.add(tableHOM);
		document.add(tableHO1);
//  end List of Topic
		
//  Start Non-Lecture Activities (Practical)
		document.add(doubleSN);
		document.add(tablenon);
		document.add(tableNon1);
//  end Non-Lecture Activities (Practical)
		
// Start TEACHING LEARNING METHODS
		document.add(singleSN);
		document.add(TEC1);
		document.add(Tec_Learning);
//  end	TEACHING LEARNING METHODS	
		
// Start Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
		document.add(singleSN);
		document.add(table2HOMHeader);
		document.add(singleSN);
		document.add(table2HOM);
// end Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
				
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
		
		//Start  8(B-II) Calculation of Internal Assessment

				document.add(tableref33);
				document.add(tabl6C);
				document.add(singleSN);
		//End  8(B-II) Calculation of Internal Assessment
		
// Start Paper Layout ////

		document.add(tableref2);
		document.add(tab92);
		document.add(singleSN);

// end Paper Layout //
		
////Start Distribution of Theory Exam ///////
		document.add(table6FMHeader);
		document.add(singleSN);
		document.add(table6FI);
		document.add(singleSN);
//		document.newPage();
		document.add(table6F2MHeader);
		document.add(singleSN);
		document.add(table6FII);
		document.add(singleSN);
//// end Distribution of Theory Exam ///////
		
///// Start Question Blue Print ===============
		
		document.add(singleSN);
		document.add(tableQ6);
		document.add(tableQP6);
		document.add(singleSN);
/////End Question Blue Print ===============
			
//// Distribution of Practical Exam ///////
		document.newPage();
		document.add(table2111);
		document.add(tab923);
		document.add(singleSN);

//// Distribution of Practical Exam ///////
		
//		Start Reference and Resourses
		document.add(singleSN);
		document.add(resourses7);
		document.add(singleSN);
		document.add(Resour);

//		End Reference and Resourses	
		super.buildPdfMetadata(model, document, request);
	}
}
