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

public class NCHPsychologyPdfDownloadClass extends AbstractPdfView {
	String Type = "";
	List<Map<String, Object>> sysdegprofcorsnamecode;
	List<ArrayList<String>> Course_Outcomes_list;
	List<String> TH2loc;
	List<ArrayList<String>> table2_Learning_Objectives_of_Psychology;
	List<ArrayList<String>> Teaching_Method_list1;
	List<String> TH;
	List<ArrayList<String>> List_of_Topic_listP;
	List<String> TH6;
	List<ArrayList<String>> table6_number_of_papers_listP;
	List<ArrayList<String>> table6b_term1list;
	List<ArrayList<String>> table6b_term2list;
	List<ArrayList<String>> table6b_term3list;
	List<String> TH2;
	List<ArrayList<String>> TableEvaluation_Methods_list;
	List<ArrayList<String>> table6d_term1list;
	List<ArrayList<String>> table6d_term2list;
	List<ArrayList<String>> table6d_term3list;
	List<ArrayList<String>> Paper_LayoutP;
	List<String> THQ50;
	List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Distri_Pract_Exam;
	List<ArrayList<String>> table61_list;
	List<ArrayList<String>> table62_list;
	List<ArrayList<String>> table63_list;
	List<String> THQ60;
	List<ArrayList<String>> Reference_Resourses_list;

	public NCHPsychologyPdfDownloadClass(List<Map<String, Object>> sysdegprofcorsnamecode,
			List<ArrayList<String>> Course_Outcomes_list, List<String> TH2loc,
			List<ArrayList<String>> table2_Learning_Objectives_of_Psychology,
			List<ArrayList<String>> Teaching_Method_list1, List<String> TH, List<ArrayList<String>> List_of_Topic_listP,
			List<String> TH6, List<ArrayList<String>> table6_number_of_papers_listP,
			List<ArrayList<String>> table6b_term1list, List<ArrayList<String>> table6b_term2list,
			List<ArrayList<String>> table6b_term3list, List<String> TH2,
			List<ArrayList<String>> TableEvaluation_Methods_list,
			List<ArrayList<String>> table6d_term1list, List<ArrayList<String>> table6d_term2list,
			List<ArrayList<String>> table6d_term3list,List<ArrayList<String>> Paper_LayoutP,
			List<String> THQ50,
			List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List,
			List<ArrayList<String>> table61_list,
			List<ArrayList<String>> table62_list,
			List<ArrayList<String>> table63_list,
			List<ArrayList<String>> Distri_Pract_Exam,
			List<String> THQ60,List<ArrayList<String>> Reference_Resourses_list) {

			this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
			this.Course_Outcomes_list = Course_Outcomes_list;
			this.TH2loc = TH2loc;
			this.table2_Learning_Objectives_of_Psychology = table2_Learning_Objectives_of_Psychology;
			this.Teaching_Method_list1 = Teaching_Method_list1;
			this.TH = TH;
			this.List_of_Topic_listP = List_of_Topic_listP;
			this.TH6 = TH6;
			this.table6_number_of_papers_listP = table6_number_of_papers_listP;
			this.table6b_term1list = table6b_term1list;
			this.table6b_term2list = table6b_term2list;
			this.table6b_term3list = table6b_term3list;
			this.TH2 = TH2;
			this.TableEvaluation_Methods_list = TableEvaluation_Methods_list;
			this.table6d_term1list = table6d_term1list;
			this.table6d_term2list = table6d_term2list;
			this.table6d_term3list = table6d_term3list;
			this.Paper_LayoutP = Paper_LayoutP;
			this.THQ50 = THQ50;
			this.Table6F_IIDistribution_of_Theory_Exam_List = Table6F_IIDistribution_of_Theory_Exam_List;
			this.Distri_Pract_Exam = Distri_Pract_Exam;
			this.table61_list = table61_list;
			this.table62_list = table62_list;
			this.table63_list = table63_list;
			this.THQ60 = THQ60;
			this.Reference_Resourses_list = Reference_Resourses_list;

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

		Font fontTablemainHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
		Font fontTablemain2Heading = FontFactory.getFont(FontFactory.defaultEncoding, 18);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, 12, 0);

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(110);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

// For System Degree Prof Course Name and Code =================================================================

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

// For System Degree Prof Course Name and Code ================================================================

// COURSE OUTCOMES================================================================================================

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
			if (i == 0) {
				add_course1 += ser1 + ". " + addList.get(i).get(0);
				ser1++;
			} else {
				add_course1 += "\n\n" + " " + ser1 + ". " + addList.get(i).get(0);
				ser1++;
			}
		}
		add_course = new Phrase(add_course1, fontTableHeadingNonBoldDataadd7);

// COURSE OUTCOMES================================================================================================

	//	-----------------------------------------------------------------
		ArrayList<ArrayList<String>> t2HOMList = (ArrayList<ArrayList<String>>) table2_Learning_Objectives_of_Psychology;
		PdfPTable table2HOMHeader = new PdfPTable(1);
		table2HOMHeader.setWidthPercentage(100);
		table2HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlineHOM = new Chunk("Table 2-Learning Objectives (Theory) of Course", fontTableHeading);
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
		

// Table 2-Learning Objectives (Theory) of Course HomUG-OM-I=============================================================
		
// Strt TEACHING LEARNING METHODS=======================================================================================

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
		ArrayList<ArrayList<String>> TecList = (ArrayList<ArrayList<String>>) Teaching_Method_list1;
		for (int i = 0; i < TecList.size(); i++) {
			Tec_Learning1 += "\n" + " " + ser5 + ". " + TecList.get(i).get(0);
			ser5++;
		}
		Tec_Learning = new Phrase(Tec_Learning1, fontTableHeadingNonBoldDataTEC7);

// End TEACHING LEARNING METHODS========================================================================

//==============Start================== Teaching hour====================================================

		PdfPTable tablexx = new PdfPTable(1);
		tablexx.setWidthPercentage(100);
		tablexx.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline4 = new Chunk(" TEACHING HOURS ", fontTableHeading);
		tablexx.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t1 = new Phrase(underline4);

		Paragraph cell1 = new Paragraph(t1);
		cell1.setAlignment(Element.ALIGN_CENTER);
		tablexx.addCell(t1);

		ArrayList<ArrayList<String>> aList23 = (ArrayList<ArrayList<String>>) List_of_Topic_listP;
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
		table32.setWidths(new int[] { 15, 150, 50, 50 });
		table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH.size(); h++) {
			py = new Paragraph(TH.get(h), fontTableHeading);
			py.setAlignment(Element.ALIGN_CENTER);
			table32.addCell(py);
		}
		int a0 = 0;
		int b0 = 0;

		for (int i = 0; i < aList23.size(); i++) {
			List<String> l = aList23.get(i);

			a0 += Integer.parseInt(aList23.get(i).get(2));
			b0 += Integer.parseInt(aList23.get(i).get(3));

			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table32.addCell(py);
			}
		}
		int sum = a0;
		int sum1 = b0;
		Paragraph pt551 = new Paragraph("", fontTableHeadingNonBoldData);
		table32.addCell(pt551);
		Paragraph pt552 = new Paragraph("Total", fontTableHeading);
		table32.addCell(pt552);
		Paragraph pt553 = new Paragraph(String.valueOf(sum), fontTableHeading);
		table32.addCell(pt553);
		Paragraph pt554 = new Paragraph(String.valueOf(sum1), fontTableHeading);
		table32.addCell(pt554);

//==============END================== Teaching hour========================================================

// start of 8(A) Number of Papers and Marks Distribution===============================================

		PdfPTable table60Dnlach1 = new PdfPTable(1);
		table60Dnlach1.setWidthPercentage(100);
		table60Dnlach1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6DH = new Chunk("8.Assessment", fontTableHeading);
		Chunk underline6DH2 = new Chunk("8A - Number of papers and Mark Distribution", fontTableHeading);
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
// End of 8(A)Number of Papers and Marks Distribution===========================================================

// 6-B================ Start Scheme of Assessment (formative and Summative)=================================================

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
		Chunk underline6B1 = new Chunk("8B - Scheme of Assessment (formative and Summative) ", fontTableHeading);
		table6b1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6B1 = new Phrase(underline6B1);

		Paragraph cell6B1 = new Paragraph(t6B1);
		cell6B1.setAlignment(Element.ALIGN_CENTER);	

		table6b1.addCell(t6B1);

		PdfPTable table6bsa = new PdfPTable(5);
		Paragraph p6b;
		table6bsa.setWidthPercentage(100);
		table6bsa.setWidths(new int[] { 20, 50, 50, 50, 50 });
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
		Paragraph p6b9 = new Paragraph("First Professional BHMS");
		Paragraph p6b10 = new Paragraph();
		Paragraph p6b11 = new Paragraph();
		Paragraph p6b12 = new Paragraph();

		if (table6b_term1list.get(0).size() > 0) {
			p6b10 = new Paragraph(table6b_term1list.get(0).get(1));
		}

		if (table6b_term1list.get(0).size() > 0) {
			p6b11 = new Paragraph(table6b_term2list.get(0).get(1));
		}

		if (table6b_term1list.get(0).size() > 0) {
			p6b12 = new Paragraph(table6b_term3list.get(0).get(1));
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

// 6-B===================== End Scheme of Assessment (formative and Summative)==============================

//  Start Evaluation Methods for PeriodicalAssessment=============================================================================

		ArrayList<ArrayList<String>> T6DList = (ArrayList<ArrayList<String>>) TableEvaluation_Methods_list;
		PdfPTable table6Dnlaclink = new PdfPTable(1);
		table6Dnlaclink.setWidthPercentage(100);
		table6Dnlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6Dnlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable table6Dnlac = new PdfPTable(1);
		table6Dnlac.setWidthPercentage(100);
		table6Dnlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6D = new Chunk("8 C -Evaluation Methods for Assessment", fontTableHeading);
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
		pt6D1 = new Paragraph(TH2.get(0), fontTableHeading);
		pt6D1.setAlignment(Element.ALIGN_CENTER);
		table6Dnlacdata.addCell(pt6D1);
		pt6D2 = new Paragraph(TH2.get(1), fontTableHeading);
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

// End Evaluation Methods for Periodical===================================================================
		
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
		Chunk underline6D11 = new Chunk("8D - Scheme of Assessment (Formative ) ", fontTableHeading);
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

		PdfPTable tabl6C = new PdfPTable(7);
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

		Paragraph pa6C = new Paragraph("8E - Method of Calculation of Internal Assessment Marks for Final University Examination: ",
				fontTableHeading);
		tableref33.addCell(pa6C);

		Paragraph pg6 = new Paragraph();
		Paragraph pg61 = new Paragraph();
		Paragraph pg62 = new Paragraph();
		Paragraph pg63 = new Paragraph();
		Paragraph pg64 = new Paragraph();
		Paragraph pg65 = new Paragraph();
		Paragraph pg66 = new Paragraph();
		Paragraph pg620 = new Paragraph();
		Paragraph pg621 = new Paragraph();
		Paragraph pg622 = new Paragraph();
		Paragraph pg623 = new Paragraph();
		Paragraph pg624 = new Paragraph();
		Paragraph pg625 = new Paragraph();
		Paragraph pg626 = new Paragraph();

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
		
		tabl6C.addCell(pg6);
		tabl6C.addCell(pg61);
		tabl6C.addCell(pg62);
		tabl6C.addCell(pg63);
		tabl6C.addCell(pg64);
		tabl6C.addCell(pg65);
		tabl6C.addCell(pg66);
		tabl6C.addCell(pg620);
		tabl6C.addCell(pg621);
		tabl6C.addCell(pg622);
		tabl6C.addCell(pg623);
		tabl6C.addCell(pg624);
		tabl6C.addCell(pg625);
		tabl6C.addCell(pg626);
		// end of 8 E - Calculation Method for Internal assessment Marks
		
// Assessment==============================================================================================

// Paper Layout==============================================================================================
		
		ArrayList<ArrayList<String>> TPList = (ArrayList<ArrayList<String>>) Paper_LayoutP;
		PdfPTable tab92 = new PdfPTable(3);
		tab92.setWidthPercentage(100);
		tab92.setWidths(new int[] { 50, 10, 10 });
		tab92.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		tab92.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref2 = new PdfPTable(1);
		tableref2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref2.setWidthPercentage(100);
		tableref2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

		Paragraph p921 = new Paragraph("8F-Paper Layout", fontTableHeading);
		tableref2.addCell(p921);
		Paragraph p9211 = new Paragraph("Summative assessment:", fontTableHeading);
		tableref2.addCell(p9211);
		Paragraph p9212 = new Paragraph("Theory- 100 marks", fontTableHeading);
		tableref2.addCell(p9212);
		Paragraph p9213 = new Paragraph("Section –I-50 marks- psychology", fontTableHeading);
		tableref2.addCell(p9213);
		
		
		Paragraph p922 = new Paragraph();
		Paragraph p923 = new Paragraph();
		Paragraph p924 = new Paragraph();
		Paragraph pq602 = new Paragraph();
		Paragraph pq6103 = new Paragraph();
		Paragraph pq6204 = new Paragraph();
		
		pq602 = new Paragraph(THQ50.get(0), fontTableHeading);
		pq602.setAlignment(Element.ALIGN_CENTER);

		pq6103 = new Paragraph(THQ50.get(1), fontTableHeading);
		pq6103.setAlignment(Element.ALIGN_CENTER);

		pq6204 = new Paragraph(THQ50.get(2), fontTableHeading);
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

// Start table 6FI- PaperII===start==========Riddhi=================================
		
		PdfPTable table6F2MHeader = new PdfPTable(1);
		table6F2MHeader.setWidthPercentage(100);
		table6F2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6F2 = new Chunk("8 G – I – Distribution of Theory Exam", fontTableHeading);
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

//end table 6FI- PaperII================================================================================
			
// Start 6 G Question paper Blue print for ==============================================================

			PdfPTable tableQ60 = new PdfPTable(1);
			tableQ60.setWidthPercentage(100);
			tableQ60.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underlineQ6 = new Chunk("8 H Question paper Blue print -50 marks", fontTableHeading);
			tableQ60.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase tQ60 = new Phrase(underlineQ6);

			Paragraph cellQ60 = new Paragraph(tQ60);
			cellQ60.setAlignment(Element.ALIGN_CENTER);

			tableQ60.addCell(tQ60);
			PdfPTable tableQP60= new PdfPTable(1);
			tableQP60.setWidthPercentage(100);
			tableQP60.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			tableQP60.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			Paragraph pq60 = new Paragraph();
			Paragraph pq610 = new Paragraph();
			Paragraph pq620= new Paragraph();
			Paragraph pq630 = new Paragraph();
			Paragraph pq640 = new Paragraph();
			Paragraph pq650 = new Paragraph();
			Paragraph pq660 = new Paragraph();
			Paragraph pq670 = new Paragraph();
			Paragraph pq680 = new Paragraph();
			Paragraph pq690 = new Paragraph();
			Paragraph pq6100 = new Paragraph();
			Paragraph pq6110 = new Paragraph();
			Paragraph pq6220 = new Paragraph();
			PdfPTable tableqp60 = new PdfPTable(3);
			tableqp60.setWidths(new int[] { 3, 13, 12 });
			tableqp60.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPTable tableqp610 = new PdfPTable(3);
			tableqp610.setWidths(new int[] { 3, 13, 12 });
			tableqp610.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			pq6220 = new Paragraph("PAPER II", fontTableHeading);
			pq6220.setAlignment(Element.ALIGN_CENTER);

			pq60 = new Paragraph(THQ60.get(0), fontTableHeading);
			pq60.setAlignment(Element.ALIGN_CENTER);

			pq610 = new Paragraph(THQ60.get(1), fontTableHeading);
			pq610.setAlignment(Element.ALIGN_CENTER);

			pq620 = new Paragraph(THQ60.get(2), fontTableHeading);
			pq620.setAlignment(Element.ALIGN_CENTER);

			pq630 = new Paragraph(THQ60.get(3), fontTableHeadingNonBoldData);
			pq630.setAlignment(Element.ALIGN_CENTER);

			pq640 = new Paragraph(THQ60.get(4), fontTableHeadingNonBoldData);
			pq640.setAlignment(Element.ALIGN_LEFT);

			pq660 = new Paragraph(THQ60.get(5), fontTableHeadingNonBoldData);
			pq660.setAlignment(Element.ALIGN_LEFT);

			pq670 = new Paragraph(THQ60.get(6), fontTableHeadingNonBoldData);
			pq670.setAlignment(Element.ALIGN_LEFT);

			pq690 = new Paragraph(THQ60.get(7), fontTableHeadingNonBoldData);
			pq690.setAlignment(Element.ALIGN_LEFT);

			pq6100 = new Paragraph(THQ60.get(8), fontTableHeadingNonBoldData);
			pq6100.setAlignment(Element.ALIGN_LEFT);

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
			pq650 = new Paragraph(topics, fontTableHeadingNonBoldData);

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

			pq680 = new Paragraph(topics1, fontTableHeadingNonBoldData);

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

			pq6110 = new Paragraph(topics2, fontTableHeadingNonBoldData);

			tableqp60.getDefaultCell().setColspan(3);
			tableqp60.getDefaultCell().setColspan(1);
			tableqp60.addCell(pq60);
			tableqp60.addCell(pq610);
			tableqp60.addCell(pq620);
			tableqp60.addCell(pq630);
			tableqp60.addCell(pq640);
			tableqp60.addCell(pq650);
			tableqp60.addCell(pq660);
			tableqp60.addCell(pq670);
			tableqp60.addCell(pq680);
			tableqp60.addCell(pq690);
			tableqp60.addCell(pq6100);
			tableqp60.addCell(pq6110);
			tableQP60.addCell(tableqp60);

//end of 6 G Question paper Blue print ==========================================================================
			
//Start Distribution of Practical Exam============================================================================

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

			Paragraph pag2 = new Paragraph("8 I -Distribution of Practical Exam", fontTableHeading);
			table2111.addCell(pag2);

			Paragraph pag21 = new Paragraph("Practical - 50 marks ", fontTableHeading);
			table2111.addCell(pag21);
			Paragraph pag22 = new Paragraph("", fontTableHeading);
			table2111.addCell(pag22);

			for (int i = 0; i < Distri_Pract_List.size(); i++) {
				List<String> l = Distri_Pract_List.get(i);
				for (int j = 0; j < l.size(); j++) {
					py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					tab923.addCell(py);

				}
			}

// End of Distribution of Practical Exam================================================================
			
// Start Reference and Resourses====================================================================

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

//End Reference and Resourses=======================================================================
			
// footer===========================================================================================

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

//				System.out.println("Type==" + Type);

			if (Type.equals("L")) {

				document.setPageSize(PageSize.A4); // set document landscape

			}

// end of footer========================================================================

		// Sys Deg Prof Course code and name
		document.add(Table1);

		// PO
		Phrase singleSN = new Phrase();
		Phrase doubleSN = new Phrase();
		singleSN = new Phrase("\n");
		doubleSN = new Phrase("\n\n");

		// CO
		document.add(doubleSN);
		document.add(add1);
		document.add(singleSN);
		document.add(add_course);

		// Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
		document.newPage();
		document.add(table2HOMHeader);
		document.add(singleSN);
		document.add(table2HOM);

		// TEACHING LEARNING METHODS
		document.add(singleSN);
		document.add(TEC1);
		document.add(Tec_Learning);
		document.add(singleSN);
		// Teaching hours
		document.add(t1);
		table.addCell(tablexx);
		table.addCell(tablex);
		document.add(table32);
		document.add(singleSN);

//		Start Table- 8(A)Assessment Summary
		document.newPage();
		document.add(table60Dnlach1);
		document.add(table6A);
		document.add(singleSN);

//		End Table- 8(A)Assessment Summary	

//		Start  8(B)Scheme of Assessment (formative and Summative)
		
		document.add(table6b1);
		document.add(table6bsa);
		document.add(table6b);
		document.add(singleSN);

//		End  8(B)Scheme of Assessment (formative and Summative)

//		Start Evaluation Methods

		document.add(t6D);
		document.add(singleSN);
		document.add(table6Dnlaclink);
		document.add(singleSN);

//		end Evaluation Methods	
		//Start  8(D)Scheme of Assessment (formative)
		document.newPage(); 
		document.add(table6D1);
		document.add(singleSN);
		document.add(table6Dformative);
		document.add(table6D);
		document.add(singleSN);

		//End  8(D)Scheme of Assessment (formative)
		
		//Start  8(E) Calculation of Internal Assessment
//		document.newPage(); 
		 document.add(tableref33);
		 document.add(singleSN);
		 document.add(tabl6C);
		 document.add(tabl6C1);
		 document.add(singleSN);
		//End  8(E) Calculation of Internal Assessment
		// Paper Layout ////
			document.add(tableref2);
			document.add(singleSN);

		// Paper Layout //
		
		////Start Distribution of Theory Exam ///////
			 document.newPage();
			document.add(table6F2MHeader);
			document.add(table6FII);
			document.add(singleSN);
	//// end Distribution of Theory Exam ///////
	///// Start Question Blue Print ===============
		
				document.add(tableQ60);
				document.add(tableQP60);
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
	}
}
