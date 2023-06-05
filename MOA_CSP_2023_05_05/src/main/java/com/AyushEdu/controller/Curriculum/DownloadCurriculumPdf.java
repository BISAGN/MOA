package com.AyushEdu.controller.Curriculum;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.Sumif;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class DownloadCurriculumPdf extends AbstractPdfView {

	String Type = "";
	List<String> TH;
	List<String> TH1;
	List<String> TH2;
	List<String> TH3;
	List<String> THLPA;
	List<String> TH4;
	List<String> TH5;
	List<String> TH6A;
	List<String> TH6D;
	List<String> TH6E;
	List<String> TH6E2;
	List<String> TH6G;
	List<String> TH6H1;

	String Heading = "";
	String cars1 = null;
	int total = 0;
	List<Map<String, Object>> cars = null;
	List<Map<String, Object>> examination_list;
	List<ArrayList<String>> t1copolink_list;
	List<ArrayList<String>> t2Content_Course_AyUGRS_list;
	List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list;
	List<ArrayList<String>> tableList_of_practical_list;
	List<ArrayList<String>> t4Learning_Objectives_Practical_of_AyUGRS_list;
	List<ArrayList<String>> table5nlac_list;
	List<ArrayList<String>> practhours;
	List<ArrayList<String>> table6A_number_of_papers_list;
	List<ArrayList<String>> table6b_term1list;
	List<ArrayList<String>> table6b_term2list;
	List<ArrayList<String>> table6b_term3list;
	List<ArrayList<String>> Table6Dnlac_list;
	List<ArrayList<String>> table_6E1;
	List<ArrayList<String>> table_6E2;
	List<ArrayList<String>> table6g1_list;
	List<ArrayList<String>> table6g2_list;
	List<ArrayList<String>> table6g3_list;
	List<ArrayList<String>> table6g4_list;
	List<ArrayList<String>> table6g5_list;
	List<ArrayList<String>> table6g6_list;
	List<ArrayList<String>> table_6H1;
	List<ArrayList<String>> reference_resourses_list;
	List<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List;
	String course_id;

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\NSG\\admission.pdf";

	public DownloadCurriculumPdf(String Type, List<String> TH, List<String> TH1, List<String> TH2, List<String> TH3,
			List<String> THLPA, List<String> TH4, List<String> TH5, List<String> TH6A, List<String> TH6D,
			List<String> TH6E, List<String> TH6E2, List<String> TH6G, List<String> TH6H1, String Heading,
			List<Map<String, Object>> list, int total, List<Map<String, Object>> examination_list,
			ArrayList<ArrayList<String>> t1copolink_list, List<ArrayList<String>> t2Content_Course_AyUGRS_list,
			List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list,
			List<ArrayList<String>> tableList_of_practical_list, List<ArrayList<String>> t4Learning_Objectiveslist,
			List<ArrayList<String>> table5nlac_list, List<ArrayList<String>> practhours,
			List<ArrayList<String>> table6A_number_of_papers_list, ArrayList<ArrayList<String>> table6b_term1list,
			ArrayList<ArrayList<String>> table6b_term2list, ArrayList<ArrayList<String>> table6b_term3list,
			List<ArrayList<String>> Table6Dnlac_list, List<ArrayList<String>> table_6E1,
			List<ArrayList<String>> table_6E2, ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List,
			ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List,
			List<ArrayList<String>> table6g1_list, List<ArrayList<String>> table6g2_list,
			List<ArrayList<String>> table6g3_list, List<ArrayList<String>> table6g4_list,
			List<ArrayList<String>> table6g5_list, List<ArrayList<String>> table6g6_list,
			List<ArrayList<String>> table_6H1, List<ArrayList<String>> reference_resourses_list,String course_id) {

		this.Type = Type;
		this.TH = TH;
		this.TH1 = TH1;
		this.TH2 = TH2;
		this.TH3 = TH3;
		this.THLPA = THLPA;
		this.TH4 = TH4;
		this.TH5 = TH5;
		this.TH6A = TH6A;
		this.TH6D = TH6D;
		this.TH6E = TH6E;
		this.TH6E = TH6E2;
		this.TH6G = TH6G;
		this.TH6H1 = TH6H1;
		this.Heading = Heading;
		this.cars = list;
		this.examination_list = examination_list;
		this.total = total;
		this.t1copolink_list = t1copolink_list;
		this.t2Content_Course_AyUGRS_list = t2Content_Course_AyUGRS_list;
		this.t3LearningObject_Course_AyUGRS_list = t3LearningObject_Course_AyUGRS_list;
		this.tableList_of_practical_list = tableList_of_practical_list;
		this.t4Learning_Objectives_Practical_of_AyUGRS_list = t4Learning_Objectiveslist;
		this.table5nlac_list = table5nlac_list;
		this.practhours = practhours;
		this.table6A_number_of_papers_list = table6A_number_of_papers_list;
		this.table6b_term1list = table6b_term1list;
		this.table6b_term2list = table6b_term2list;
		this.table6b_term3list = table6b_term3list;
		this.Table6Dnlac_list = Table6Dnlac_list;
		this.table_6E1 = table_6E1;
		this.table_6E2 = table_6E2;
		this.Table6F_IDistribution_of_Theory_Exam_List = Table6F_IDistribution_of_Theory_Exam_List;
		this.Table6F_IIDistribution_of_Theory_Exam_List = Table6F_IIDistribution_of_Theory_Exam_List;
		this.table6g1_list = table6g1_list;
		this.table6g2_list = table6g2_list;
		this.table6g3_list = table6g3_list;
		this.table6g4_list = table6g4_list;
		this.table6g5_list = table6g5_list;
		this.table6g6_list = table6g6_list;
		this.table_6H1 = table_6H1;
		this.reference_resourses_list = reference_resourses_list;
		this.course_id = course_id;
	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request,
			Object system_name) {

		document.open();

		ArrayList<String> data1 = new ArrayList<>();
		for (int i = 0; i < cars.size(); i++) {
			data1.add((String) cars.get(i).get(system_name));
		}
		Phrase p = new Phrase();
		PdfPTable headtable = new PdfPTable(2);
		headtable.setWidthPercentage(100);

		headtable.setSpacingAfter(33f);
		PdfPCell cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		PdfPCell cell3 = new PdfPCell();
		cell3.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablerigerht1 = new PdfPTable(1);
		tablerigerht1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablerigerht1.setWidthPercentage(100);
		tablerigerht1.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		PdfPTable tableright1 = new PdfPTable(1);
		tableright1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableright1.setWidthPercentage(100);
		tableright1.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		headtable.addCell(cell1);
		headtable.addCell(cell3);
		p.add(headtable);

		HeaderFooter header = new HeaderFooter(p, false);
		header.setBorder(Rectangle.NO_BORDER);
		document.setHeader(header);

		document.setPageCount(1);

		if (Type.equals("L")) {
			document.setPageSize(PageSize.A4);
		}
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<String> data1 = new ArrayList<>();
		for (int i = 0; i < cars.size(); i++) {
			data1.add((String) cars.get(i).get(0));
		}
		Font fontTablemainHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
		Font fontTablemain2Heading = FontFactory.getFont(FontFactory.defaultEncoding, 18);

		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);

		PdfPTable table = new PdfPTable(TH.size());
		Paragraph p;
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tab132le = new PdfPTable(4);
		tab132le.setWidthPercentage(100);
		tab132le.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab132le.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable TestTable = new PdfPTable(1);
		TestTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		TestTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		TestTable.addCell("");
		TestTable.addCell("");

		PdfPTable tableleft3 = new PdfPTable(1);
		tableleft3.setWidthPercentage(100);
		tableleft3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Chunk underline = new Chunk(" " + cars.get(0).get("professional").toString() + "" + " "
				+ cars.get(0).get("system_name").toString() + "", fontTablemainHeading);
		Chunk underline1 = new Chunk(" (" + cars.get(0).get("degree_name").toString() + ")", fontTablemainHeading);
		Chunk underline2 = new Chunk("Subject Code: " + cars.get(0).get("course_code").toString() + "",
				fontTablemain2Heading);
		Chunk underline3 = new Chunk(" " + cars.get(0).get("course_name").toString() + "", fontTablemainHeading);
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
		PdfPTable tableref = new PdfPTable(1);
		tableref.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref.setWidthPercentage(100);
		tableref.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		Paragraph pr1;
		pr1 = new Paragraph("Summary", fontTablemain2Heading);
		tableref.addCell(pr1);

		PdfPTable tablesadasref = new PdfPTable(1);
		tablesadasref.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablesadasref.setWidthPercentage(100);
		tablesadasref.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
		tablesadasref.addCell("");
		tablesadasref.addCell("");
		tablesadasref.addCell("");
		tablesadasref.addCell("");

		PdfPTable tablesadasref45 = new PdfPTable(1);
		tablesadasref45.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablesadasref45.setWidthPercentage(100);
		tablesadasref45.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		ArrayList<String> data = new ArrayList<>();
		for (int i = 0; i < cars.size(); i++) {
			data1.add((String) cars.get(i).get(0));
		}

		if (TH.size() != 0) {
			for (int h = 0; h < TH.size(); h++) {
				p = new Paragraph(TH.get(h), fontTableHeading);
				p.setAlignment(Element.ALIGN_CENTER);
				table.addCell(p);
			}

			table.setHeaderRows(1);
			for (int i = 0; i < cars.size(); i++) {
				data1.add((String) cars.get(i).get(0));
			}
		}

//------------------------------------------------------------------------------------------------------------------------------------------------------

		int a = (int) cars.get(0).get("no_of_hours");
		int b = (int) cars.get(1).get("no_of_hours");
		int c = a + b;
		int a1 = (int) cars.get(3).get("no_of_hours");
		int b1 = (int) cars.get(4).get("no_of_hours");
		int c1 = a1 + b1;
		int a2 = (int) cars.get(0).get("no_of_hours");
		int b2 = (int) cars.get(1).get("no_of_hours");
		int c2 = a2 + b2;
		int a3 = c1;
		int b3 = (int) cars.get(2).get("no_of_hours");
		int c3 = a3 + b3;
		int c4 = c2 + c3;

		p = new Paragraph(cars.get(0).get("system_name").toString(), fontTableHeading);
		p.setAlignment(Element.ALIGN_CENTER);
		Paragraph p2 = new Paragraph("Total number of Teaching hours: " + c4, fontTableHeading);
		p2.setAlignment(Element.ALIGN_CENTER);
		Paragraph p3 = new Paragraph("Lecture hours(LH)-Theory", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p4 = new Paragraph(cars.get(0).get("paper").toString());
		Paragraph p41 = new Paragraph(cars.get(1).get("paper").toString());
		Paragraph p42 = new Paragraph(cars.get(3).get("paper").toString());
		Paragraph p43 = new Paragraph(cars.get(4).get("paper").toString());
		if (cars.get(0).get("type_of_hours").toString().equals("LH")
				&& cars.get(0).get("type_of_teaching").toString().equals("THEORY")) {
			p4.setAlignment(Element.ALIGN_CENTER);
		} else {
			p41.setAlignment(Element.ALIGN_CENTER);
		}
		if (cars.get(3).get("type_of_hours").toString().equals("NLH")
				&& cars.get(3).get("type_of_teaching").toString().equals("PRACTICAL")) {
			p42.setAlignment(Element.ALIGN_CENTER);
		} else {
			p43.setAlignment(Element.ALIGN_CENTER);
		}
		Paragraph p131 = new Paragraph(String.valueOf(c + " Hours"), fontTableHeading);
		p131.setAlignment(Element.ALIGN_CENTER);
		Paragraph p132 = new Paragraph(String.valueOf(c1 + " Hours"), fontTableHeading);
		p132.setAlignment(Element.ALIGN_CENTER);
		Paragraph p133 = new Paragraph(
				String.valueOf(c2 + " Hours" + "(" + cars.get(0).get("type_of_hours").toString() + ")"),
				fontTableHeading);
		p133.setAlignment(Element.ALIGN_CENTER);
		Paragraph p134 = new Paragraph(
				String.valueOf(c3 + " Hours" + "(" + cars.get(3).get("type_of_hours").toString() + ")"),
				fontTableHeading);
		p134.setAlignment(Element.ALIGN_CENTER);
		Paragraph p6 = new Paragraph(cars.get(0).get("no_of_hours").toString() + " Hours");
		Paragraph p61 = new Paragraph(cars.get(1).get("no_of_hours").toString() + " Hours");
		if (cars.get(0).get("type_of_hours").toString().equals("LH")
				&& cars.get(0).get("type_of_teaching").toString().equals("THEORY")
				&& cars.get(0).get("paper").toString().equals("PAPER I")) {
			p6.setAlignment(Element.ALIGN_CENTER);
		} else {
			p61.setAlignment(Element.ALIGN_CENTER);
		}
		Paragraph p7 = new Paragraph("Non-Lecture hours(NLH)-Theory", fontTableHeading);
		p7.setAlignment(Element.ALIGN_CENTER);
		Paragraph p8 = new Paragraph(cars.get(3).get("no_of_hours").toString() + " Hours");
		Paragraph p81 = new Paragraph(cars.get(4).get("no_of_hours").toString() + " Hours");
		if (cars.get(3).get("type_of_hours").toString().equals("NLH")
				&& cars.get(3).get("type_of_teaching").toString().equals("PRACTICAL")
				&& cars.get(3).get("paper").toString() == "PAPER I") {
			p8.setAlignment(Element.ALIGN_CENTER);
		} else {
			p81.setAlignment(Element.ALIGN_CENTER);
		}
		Paragraph p9 = new Paragraph("Non-Lecture hours(NLH)-Practical", fontTableHeading);
		p9.setAlignment(Element.ALIGN_CENTER);
		Paragraph p10 = new Paragraph("80", fontTableHeading);
		p10.setAlignment(Element.ALIGN_CENTER);
		Paragraph p11 = new Paragraph("320 NLH", fontTableHeading);
		p11.setAlignment(Element.ALIGN_CENTER);
		Paragraph p121 = new Paragraph(cars.get(2).get("no_of_hours").toString() + " Hours", fontTableHeading);
		p121.setAlignment(Element.ALIGN_CENTER);

		tab132le.getDefaultCell().setColspan(4);
		tab132le.addCell(p);
		tab132le.addCell(p2);
		tab132le.getDefaultCell().setColspan(2);
		tab132le.addCell(p3);
		tab132le.getDefaultCell().setColspan(1);
		tab132le.getDefaultCell().setRowspan(3);
		tab132le.addCell(p131);
		tab132le.getDefaultCell().setRowspan(1);
		tab132le.getDefaultCell().setColspan(1);
		tab132le.getDefaultCell().setRowspan(3);
		tab132le.addCell(p133);
		tab132le.getDefaultCell().setRowspan(1);
		tab132le.addCell(p4);
		tab132le.addCell(p6);
		tab132le.addCell(p41);
		tab132le.addCell(p61);
		tab132le.getDefaultCell().setColspan(2);
		tab132le.addCell(p7);
		tab132le.getDefaultCell().setColspan(1);
		tab132le.getDefaultCell().setRowspan(3);
		tab132le.addCell(p132);
		tab132le.getDefaultCell().setRowspan(1);
		tab132le.getDefaultCell().setColspan(1);
		tab132le.getDefaultCell().setRowspan(4);
		tab132le.addCell(p134);
		tab132le.getDefaultCell().setRowspan(1);
		tab132le.addCell(p42);
		tab132le.addCell(p8);
		tab132le.addCell(p43);
		tab132le.addCell(p81);
		tab132le.getDefaultCell().setColspan(2);
		tab132le.addCell(p9);
		tab132le.getDefaultCell().setColspan(1);
		tab132le.addCell(p121);

		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);

		Paragraph pv;
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 10);

		PdfPTable table1 = new PdfPTable(1);

		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

//		//table 0(2)==============================================

		PdfPTable tab132ls = new PdfPTable(6);
		Paragraph p89;
		tab132ls.setWidthPercentage(100);
		tab132ls.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab132ls.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

////		subtotal theory start
		int a0 = (int) examination_list.get(0).get("theory_comp_marks");
		int b01 = (int) examination_list.get(0).get("theory_comp_marks");
		int c01 = a0 + b01;

////		subtotal Practical start
		int p01 = (int) examination_list.get(0).get("practical_marks");
		int q1 = (int) examination_list.get(0).get("viva_marks");
		int r1 = (int) examination_list.get(0).get("elective_marks");
		int s1 = (int) examination_list.get(0).get("ia_marks");
		int t01 = p01 + q1 + r1 + s1;

		int total_m = c01 + t01;

		Paragraph p44 = new Paragraph("Examination (Papers & Mark Distribution) ", fontTableHeading);
		p2.setAlignment(Element.ALIGN_CENTER);
		Paragraph p45 = new Paragraph("Item", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p46 = new Paragraph("Theory Component Marks", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p47 = new Paragraph("Practical Component Marks", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p48 = new Paragraph("Practical", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p49 = new Paragraph("Viva", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p50 = new Paragraph("Elective", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p51 = new Paragraph("IA", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		Paragraph p52 = new Paragraph("Paper I", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);
		Paragraph p53 = new Paragraph("Paper II", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		String paper1_marks = "---";
		if (!examination_list.get(0).get("theory_comp_marks").equals("")) {
			paper1_marks = examination_list.get(0).get("theory_comp_marks").toString();
		}
		Paragraph p54 = new Paragraph(paper1_marks, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		String paper2_marks = "---";
		if (!examination_list.get(0).get("theory_comp_marks").equals("")) {
			paper2_marks = examination_list.get(0).get("theory_comp_marks").toString();
		}
		Paragraph p55 = new Paragraph(paper2_marks, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		// practical viva
		String practical_viva = "---";
		if (!examination_list.get(0).get("viva_marks").equals("")) {
			practical_viva = examination_list.get(0).get("viva_marks").toString();
		}
		Paragraph p56 = new Paragraph(practical_viva, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		// practical marks
		String practical_m = "---";
		if (!examination_list.get(0).get("practical_marks").equals("")) {
			practical_m = examination_list.get(0).get("practical_marks").toString();
		}
		Paragraph p57 = new Paragraph(practical_m, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

//		//practical Elective
		String practical_elective = "---";
		if (!examination_list.get(0).get("elective_marks").equals("")) {
			practical_elective = examination_list.get(0).get("elective_marks").toString();
		}
		Paragraph p58 = new Paragraph(practical_elective, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		// practical IA
		String practical_IA = "---";
		if (!examination_list.get(0).get("ia_marks").equals("")) {
			practical_IA = examination_list.get(0).get("ia_marks").toString();
		}

		Paragraph p59 = new Paragraph(practical_IA, fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

//		///heading subtotal
		Paragraph p60 = new Paragraph("Sub-Total", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

//		//theory subtotal1
		Paragraph p62 = new Paragraph(String.valueOf(c01), fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

//		//practical subtotal2
		Paragraph p63 = new Paragraph(String.valueOf(t01), fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

//		///total_marks heading
		Paragraph p64 = new Paragraph("Total Marks", fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		Paragraph p65 = new Paragraph(String.valueOf(total_m), fontTableHeading);
		p3.setAlignment(Element.ALIGN_CENTER);

		tab132ls.getDefaultCell().setColspan(6);
		tab132ls.addCell(p);
		tab132ls.addCell(p44);
		tab132ls.getDefaultCell().setColspan(1);

		tab132ls.getDefaultCell().setRowspan(2);
		tab132ls.addCell(p45);
		tab132ls.addCell(p46);
		tab132ls.getDefaultCell().setRowspan(1);
		tab132ls.getDefaultCell().setColspan(4);
		tab132ls.addCell(p47);
		tab132ls.getDefaultCell().setColspan(1);

		tab132ls.addCell(p48);
		tab132ls.addCell(p49);
		tab132ls.addCell(p50);
		tab132ls.addCell(p51);

		tab132ls.addCell(p52);
		tab132ls.addCell(p54);

		tab132ls.getDefaultCell().setRowspan(2);
		tab132ls.addCell(p57);
		tab132ls.addCell(p56);
		tab132ls.addCell(p58);
		tab132ls.addCell(p59);
		tab132ls.getDefaultCell().setRowspan(1);

		tab132ls.addCell(p53);
		tab132ls.addCell(p55);

		tab132ls.addCell(p60);
		tab132ls.addCell(p62);
		tab132ls.getDefaultCell().setColspan(4);
		tab132ls.addCell(p63);
		tab132ls.getDefaultCell().setColspan(1);
		tab132ls.addCell(p64);
		tab132ls.getDefaultCell().setColspan(5);
		tab132ls.addCell(p65);
		tab132ls.getDefaultCell().setColspan(1);

		// end of table 0(2)==============================================

		// Table 1- Course learning outcomes and matched  PO========================================

		PdfPTable tablexx = new PdfPTable(1);
		tablexx.setWidthPercentage(100);
		tablexx.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline4 = new Chunk("Table 1- Course learning outcomes and matched PO.", fontTableHeading);
		tablexx.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t1 = new Phrase(underline4);

		Paragraph cell = new Paragraph(t1);
		cell.setAlignment(Element.ALIGN_CENTER);

		tablexx.addCell(t1);
		ArrayList<ArrayList<String>> aList2 = (ArrayList<ArrayList<String>>) t1copolink_list;

		PdfPTable tablex = new PdfPTable(1);
		tablex.setWidthPercentage(100);
		tablex.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatax = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph py = new Paragraph();
		Paragraph py1 = new Paragraph();
		Paragraph py2 = new Paragraph();

		PdfPTable table32 = new PdfPTable(3);
		table32.setWidths(new int[] { 15, 150, 50 });
		table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		py = new Paragraph(TH1.get(0), fontTableHeading);
		py.setAlignment(Element.ALIGN_CENTER);
		table32.addCell(py);
		py1 = new Paragraph(TH1.get(1), fontTableHeading);
		py1.setAlignment(Element.ALIGN_CENTER);
		table32.addCell(py1);
		py2 = new Paragraph(TH1.get(2), fontTableHeading);
		py2.setAlignment(Element.ALIGN_CENTER);
		table32.addCell(py2);

		table32.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList2.size(); i++) {
			List<String> l = aList2.get(i);
			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table32.addCell(py);
			}
		}
		tablex.addCell(table32);

		// end of Table 1- Course learning outcomes and matched PO==========================================================

		// Table 2: Contents of Course AyUG-RS=======================================

		PdfPTable table2MHeader = new PdfPTable(1);
		table2MHeader.setWidthPercentage(100);
		table2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline5 = new Chunk("Table 2- Contents of Subject ", fontTableHeading);
		table2MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t2 = new Phrase(underline5);

		Paragraph cell2 = new Paragraph(t2);
		cell2.setAlignment(Element.ALIGN_CENTER);

		table2MHeader.addCell(t2);

		ArrayList<ArrayList<String>> t2List = (ArrayList<ArrayList<String>>) t2Content_Course_AyUGRS_list;

		PdfPTable table2link = new PdfPTable(1);
		table2link.setWidthPercentage(190);
		table2link.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2link.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldData2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph pt2 = new Paragraph();
		PdfPTable table2Content_Course_AyUGRS = new PdfPTable(6);
		table2Content_Course_AyUGRS.setWidthPercentage(100);
		table2Content_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH2.size(); h++) {
			pt2 = new Paragraph(TH2.get(h), fontTableHeading);
			pt2.setAlignment(Element.ALIGN_CENTER);
			table2Content_Course_AyUGRS.addCell(pt2);
		}
		PdfPTable table2 = new PdfPTable(6);
		table2.setWidthPercentage(100);
		table2.setWidths(new int[] { 25, 250, 50, 50, 50, 50 });
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph py2y = new Paragraph();
		Paragraph py21 = new Paragraph();
		Paragraph py22 = new Paragraph();
		Paragraph py23 = new Paragraph();
		Paragraph py24 = new Paragraph();
		Paragraph py25 = new Paragraph();
		py2y = new Paragraph(TH2.get(0), fontTableHeading);
		py2y.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py2y);
		py21 = new Paragraph(TH2.get(1), fontTableHeading);
		py21.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py21);
		py22 = new Paragraph(TH2.get(2), fontTableHeading);
		py22.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py22);
		py23 = new Paragraph(TH2.get(3), fontTableHeading);
		py23.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py23);
		py24 = new Paragraph(TH2.get(4), fontTableHeading);
		py24.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py24);
		py25 = new Paragraph(TH2.get(5), fontTableHeading);
		py25.setAlignment(Element.ALIGN_CENTER);
		table2.addCell(py25);

		String papertype = "";
		table2link.setWidthPercentage(150);
		for (int i = 0; i < t2List.size(); i++) {
			List<String> l = t2List.get(i);
			for (int j = 0; j < l.size(); j++) {

				if (j == 1) {
					String sta[] = l.get(2).split("/");
					String topicsubtopic = l.get(j);
					for (int s = 0; s < sta.length; s++) {
						if (s == 0) {
							topicsubtopic += "\n\n" + " * " + sta[s];
						} else {
							topicsubtopic += "\n" + " * " + sta[s];
						}
					}
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table2.addCell(py2);
				}
				if (j == 2) {
				}
				if (j != 1 && j != 2) {
					if (i == 0 && j == 0) {
						papertype = l.get(7);
						Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
						table2.getDefaultCell().setColspan(6);
						table2.addCell(pt3);
						table2.getDefaultCell().setColspan(1);
					}

					if (j != 7) {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table2.addCell(py2);
					}

					if (j == 7) {
						if (!papertype.equals(l.get(j))) {
							papertype = l.get(j);
							Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
							table2.getDefaultCell().setColspan(6);
							table2.addCell(pt3);
							table2.getDefaultCell().setColspan(1);
						}
					}
				}
			}
		}

		table2link.addCell(table2Content_Course_AyUGRS);

		// end of Table 2: Contents of Course =======================================

		// Table 3: Learning objectives (Theory) of Course

		ArrayList<ArrayList<String>> t3List = (ArrayList<ArrayList<String>>) t3LearningObject_Course_AyUGRS_list;
		PdfPTable table3MHeader = new PdfPTable(1);
		table3MHeader.setWidthPercentage(100);
		table3MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6 = new Chunk("Table 3: Learning objectives (Theory) of Subject ", fontTableHeading);
		table3MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t3 = new Phrase(underline6);

		Paragraph cell3 = new Paragraph(t1);
		cell3.setAlignment(Element.ALIGN_CENTER);

		table3MHeader.addCell(t3);

		Paragraph pt3 = new Paragraph();
		PdfPTable table3 = new PdfPTable(10);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH3.size(); h++) {
			pt3 = new Paragraph(TH3.get(h), fontTableHeadingNonBoldData);
			pt3.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(pt3);
		}

		String papertype1 = "";
		String topictype = "";

		for (int i = 0; i < t3List.size(); i++) {
			List<String> l = t3List.get(i);
			for (int j = 0; j < l.size(); j++) {

				if (i == 0 && j == 0) {
					papertype1 = l.get(j);
					Paragraph ptt32 = new Paragraph(papertype1, fontTableHeading);
					ptt32.setAlignment(Element.ALIGN_CENTER);
					table3.getDefaultCell().setColspan(10);
					table3.addCell(ptt32);
					table3.getDefaultCell().setColspan(1);

				}
				if (j == 0) {
					if (!papertype1.equals(l.get(j))) {
						papertype1 = l.get(j);
						Paragraph ptt32 = new Paragraph(papertype1, fontTableHeading);
						ptt32.setAlignment(Element.ALIGN_CENTER);
						table3.getDefaultCell().setColspan(10);
						table3.addCell(ptt32);
						table3.getDefaultCell().setColspan(1);
					}
				}
				if (i == 0 && j == 1) {
					topictype = l.get(j);
					Paragraph ptt3 = new Paragraph("Topic - " + topictype, fontTableHeading);
					table3.getDefaultCell().setColspan(10);
					table3.addCell(ptt3);
					table3.getDefaultCell().setColspan(1);
				}
				if (j != 0 && j != 1) {
					Paragraph ptt32 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					table3.addCell(ptt32);
				}
				if (j == 1) {
					if (!topictype.equals(l.get(j))) {
						topictype = l.get(j);
						Paragraph ptt3 = new Paragraph("Topic - " + topictype, fontTableHeading);
						table3.getDefaultCell().setColspan(10);
						table3.addCell(ptt3);
						table3.getDefaultCell().setColspan(1);
					}
				}
			}
		}
		table.addCell(table3);

		// end of Table 3: Learning objectives (Theory) of Course

		// ---------------------------practical list table start------------------------------

		PdfPTable tablepla = new PdfPTable(1);
		tablepla.setWidthPercentage(100);
		tablepla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline22 = new Chunk("List of Practicals ", fontTableHeading);
		tablepla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase p1t = new Phrase(underline22);

		Paragraph cellp = new Paragraph(p1t);
		cellp.setAlignment(Element.ALIGN_CENTER);

		tablepla.addCell(p1t);

		ArrayList<ArrayList<String>> PracList = (ArrayList<ArrayList<String>>) tableList_of_practical_list;

		tablepla.setWidthPercentage(100);
		tablepla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablepla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDatapla1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		PdfPTable tablepractical = new PdfPTable(4);
		tablepractical.setWidthPercentage(100);
		tablepractical.setWidths(new int[] { 15, 150, 50, 150 });
		tablepractical.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph pl1 = new Paragraph();
		Paragraph pl2 = new Paragraph();
		Paragraph pl3 = new Paragraph();
		Paragraph pl4 = new Paragraph();

		pl1 = new Paragraph(THLPA.get(0), fontTableHeading);
		pl1.setAlignment(Element.ALIGN_CENTER);
		tablepractical.addCell(pl1);
		pl2 = new Paragraph(THLPA.get(1), fontTableHeading);
		pl2.setAlignment(Element.ALIGN_CENTER);
		tablepractical.addCell(pl2);
		pl3 = new Paragraph(THLPA.get(2), fontTableHeading);
		pl3.setAlignment(Element.ALIGN_CENTER);
		tablepractical.addCell(pl3);
		pl4 = new Paragraph(THLPA.get(3), fontTableHeading);
		pl4.setAlignment(Element.ALIGN_CENTER);
		tablepractical.addCell(pl4);

		for (int i = 0; i < PracList.size(); i++) {
			List<String> l = PracList.get(i);
			for (int j = 0; j < l.size(); j++) {
				Paragraph pl1t = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tablepractical.addCell(pl1t);
			}
		}

		// -----------------------------practical list table end---------------------------

		// Table 4: Learning objectives (Practical) of AyUG-

		ArrayList<ArrayList<String>> ta4List = (ArrayList<ArrayList<String>>) t4Learning_Objectives_Practical_of_AyUGRS_list;
		PdfPTable table4MHeader = new PdfPTable(1);
		table4MHeader.setWidthPercentage(100);
		table4MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6C = new Chunk("Table 4: Learning objectives (Practical) ", fontTableHeading);
		table4MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t4 = new Phrase(underline6C);

		Paragraph cell4 = new Paragraph(t1);
		cell4.setAlignment(Element.ALIGN_CENTER);

		table4MHeader.addCell(t4);

		Paragraph pt4 = new Paragraph();
		PdfPTable tablepraclo4 = new PdfPTable(10);
		tablepraclo4.setWidthPercentage(100);
		tablepraclo4.setWidths(new int[] { 15, 40, 15, 15, 15, 15, 15, 15, 15, 15 });
		tablepraclo4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH4.size(); h++) {
			pt4 = new Paragraph(TH4.get(h), fontTableHeading);
			pt4.setAlignment(Element.ALIGN_CENTER);
			tablepraclo4.addCell(pt4);
		}

		for (int i = 0; i < ta4List.size(); i++) {
			List<String> l = ta4List.get(i);
			for (int j = 0; j < l.size(); j++) {

				if (i == 0 && j == 0) {
					topictype = l.get(j);
					Paragraph ptt3 = new Paragraph("Practical - " + topictype, fontTableHeading);
					tablepraclo4.getDefaultCell().setColspan(10);
					tablepraclo4.addCell(ptt3);
					tablepraclo4.getDefaultCell().setColspan(1);
				}
				if (j != 0 && j != 0) {
					Paragraph ptt32 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
					tablepraclo4.addCell(ptt32);
				}
				if (j == 0) {
					if (!topictype.equals(l.get(j))) {
						topictype = l.get(j);
						Paragraph ptt3 = new Paragraph("Practical - " + topictype, fontTableHeading);
						tablepraclo4.getDefaultCell().setColspan(10);
						tablepraclo4.addCell(ptt3);
						tablepraclo4.getDefaultCell().setColspan(1);
					}
				}
			}
		}
		table.addCell(tablepraclo4);

		// end of Table 4: Learning objectives (Practical) of AyUG-

		// Table 5: Non-Lecture Activities Course ===============================

				ArrayList<ArrayList<String>> T5List = (ArrayList<ArrayList<String>>) table5nlac_list;
				ArrayList<ArrayList<String>> practhrs = (ArrayList<ArrayList<String>>) practhours;
				PdfPTable table5nlaclink = new PdfPTable(1);
				table5nlaclink.setWidthPercentage(100);
				table5nlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table5nlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);

				PdfPTable table5nlac = new PdfPTable(1);
				table5nlac.setWidthPercentage(100);
				table5nlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline7 = new Chunk("Table5: Non-Lecture Activities Subject ", fontTableHeading);
				table5nlac.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase t5 = new Phrase(underline7);

				Paragraph cell5 = new Paragraph(t5);
				cell5.setAlignment(Element.ALIGN_CENTER);

				table5nlac.addCell(t5);

				Paragraph pt51 = new Paragraph();
				Paragraph pt5 = new Paragraph();
				Paragraph pt52 = new Paragraph();
				Paragraph pt53 = new Paragraph();

				PdfPTable table5nlacdata = new PdfPTable(3);
				table5nlacdata.setWidths(new int[] { 15, 150, 40 });
				table5nlacdata.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				pt51 = new Paragraph(TH5.get(0), fontTableHeading);
				pt51.setAlignment(Element.ALIGN_CENTER);
				table5nlacdata.addCell(pt51);
				pt52 = new Paragraph(TH5.get(1), fontTableHeading);
				pt52.setAlignment(Element.ALIGN_CENTER);
				table5nlacdata.addCell(pt52);
				pt53 = new Paragraph(TH5.get(2), fontTableHeading);
				pt53.setAlignment(Element.ALIGN_CENTER);
				table5nlacdata.addCell(pt53);

				int sub_total1 = 0;
				int grand_total1 = 0;
				for (int i = 0; i < T5List.size(); i++) {
					List<String> l = T5List.get(i);
					for (int j = 0; j < l.size(); j++) {
						if (j == 2) {
							sub_total1 = sub_total1 + Integer.parseInt(l.get(j));
						}
						Paragraph pt54 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table5nlacdata.addCell(pt54);
					}
				}

				Paragraph pt54 = new Paragraph("", fontTableHeadingNonBoldData);
				table5nlacdata.addCell(pt54);
				Paragraph pt55 = new Paragraph("Total", fontTableHeading);
				table5nlacdata.addCell(pt55);
				Paragraph pt56 = new Paragraph(String.valueOf(sub_total1), fontTableHeading);
				table5nlacdata.addCell(pt56);

				Paragraph pt57 = new Paragraph("", fontTableHeadingNonBoldData);
				table5nlacdata.addCell(pt57);

				Paragraph pt551 = new Paragraph("", fontTableHeadingNonBoldData);
				table5nlacdata.addCell(pt551);

				table5nlaclink.addCell(table5nlac);

				table5nlaclink.addCell(table5nlacdata);

				// end of Table 5: Non-Lecture Activities Course

		// 6 A - Number of Papers and Marks Distribution============================

		PdfPTable tablenop = new PdfPTable(1);
		tablenop.setWidthPercentage(100);
		tablenop.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6A = new Chunk("6 A - Number of Papers and Marks Distribution", fontTableHeading);
		tablenop.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6A = new Phrase(underline6A);

		Paragraph cell6A = new Paragraph(t6A);
		cell6A.setAlignment(Element.ALIGN_CENTER);

		tablenop.addCell(t6A);

		ArrayList<ArrayList<String>> nplist = (ArrayList<ArrayList<String>>) table6A_number_of_papers_list;
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

		PdfPTable table6anop = new PdfPTable(10);
		table6anop.setWidths(new int[] { 8, 24, 12, 12, 15, 8, 20, 6, 8, 10 });
		table6anop.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		pt6a = new Paragraph(TH6A.get(0), fontTableHeading);
		pt6a.setAlignment(Element.ALIGN_CENTER);

		pt6a1 = new Paragraph(TH6A.get(1), fontTableHeading);
		pt6a1.setAlignment(Element.ALIGN_CENTER);

		pt6a2 = new Paragraph(TH6A.get(2), fontTableHeading);
		pt6a2.setAlignment(Element.ALIGN_CENTER);

		pt6a3 = new Paragraph(TH6A.get(3), fontTableHeading);
		pt6a3.setAlignment(Element.ALIGN_CENTER);

		pt6a4 = new Paragraph(TH6A.get(4), fontTableHeading);
		pt6a4.setAlignment(Element.ALIGN_CENTER);

		pt6a5 = new Paragraph(TH6A.get(5), fontTableHeading);
		pt6a5.setAlignment(Element.ALIGN_CENTER);

		pt6a6 = new Paragraph(TH6A.get(6), fontTableHeading);
		pt6a6.setAlignment(Element.ALIGN_CENTER);

		pt6a7 = new Paragraph(TH6A.get(7), fontTableHeading);
		pt6a7.setAlignment(Element.ALIGN_CENTER);

		pt6a8 = new Paragraph(TH6A.get(8), fontTableHeading);
		pt6a8.setAlignment(Element.ALIGN_CENTER);

		pt6a9 = new Paragraph(TH6A.get(9), fontTableHeading);
		pt6a9.setAlignment(Element.ALIGN_CENTER);

		pt6a10 = new Paragraph(TH6A.get(10), fontTableHeading);
		pt6a10.setAlignment(Element.ALIGN_CENTER);

		table6anop.getDefaultCell().setRowspan(2);
		table6anop.addCell(pt6a);
		table6anop.addCell(pt6a1);
		table6anop.addCell(pt6a2);
		table6anop.addCell(pt6a3);
		table6anop.getDefaultCell().setRowspan(1);
		table6anop.getDefaultCell().setColspan(5);
		table6anop.addCell(pt6a10);
		table6anop.getDefaultCell().setColspan(1);
		table6anop.getDefaultCell().setRowspan(2);
		table6anop.addCell(pt6a9);
		table6anop.getDefaultCell().setRowspan(1);
		table6anop.addCell(pt6a4);
		table6anop.addCell(pt6a5);
		table6anop.addCell(pt6a6);
		table6anop.addCell(pt6a7);
		table6anop.addCell(pt6a8);

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
				table6anop.addCell(pt6a);
			}
		}

		pt6a = new Paragraph(String.valueOf(sub_total), fontTableHeadingNonBoldData);
		table6anop.addCell(pt6a);
		pt6a = new Paragraph(String.valueOf(grand_total), fontTableHeadingNonBoldData);
		table6anop.addCell(pt6a);

		table6A.addCell(table6anop);

		// end of 6 A - Number of Papers and Marks Distribution============================

		// 6-B ============================

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
		Chunk underline6B1 = new Chunk("6 B - Scheme of Assessment (formative and Summative) ",
				fontTableHeading);
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

		Paragraph p6b8 = new Paragraph("1");

		Paragraph p6b9 = new Paragraph("First");

		Paragraph p6b10 = new Paragraph(table6b_term1list.get(0).get(1));
		Paragraph p6b11 = new Paragraph(table6b_term2list.get(0).get(1));
		Paragraph p6b12 = new Paragraph(table6b_term3list.get(0).get(1));

		table6bsa.getDefaultCell().setRowspan(2);
		table6bsa.getDefaultCell().setRowspan(1);
		table6bsa.getDefaultCell().setColspan(4);
		table6bsa.getDefaultCell().setColspan(1);
		table6bsa.addCell(p6b8);
		table6bsa.addCell(p6b9);
		table6bsa.addCell(p6b10);
		table6bsa.addCell(p6b11);
		table6bsa.addCell(p6b12);

		// end 6-B ===========================

		// 6 C - Calculation Method for Internal assessment Marks (30 Marks)

		PdfPTable tab6C = new PdfPTable(9);
		Paragraph p7567;
		tab6C.setWidthPercentage(100);
		tab6C.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab6C.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref1 = new PdfPTable(1);
		tableref1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref1.setWidthPercentage(100);
		tableref1.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		Paragraph p6C = new Paragraph("6 C - Calculation Method for Internal assessment Marks (30 Marks) ",
				fontTableHeading);
		tableref1.addCell(p6C);

		Paragraph py6 = new Paragraph();
		Paragraph py61 = new Paragraph();
		Paragraph py62 = new Paragraph();
		Paragraph py63 = new Paragraph();
		Paragraph py64 = new Paragraph();
		Paragraph py65 = new Paragraph();
		Paragraph py66 = new Paragraph();
		Paragraph py67 = new Paragraph();
		Paragraph py68 = new Paragraph();
		Paragraph py69 = new Paragraph();
		Paragraph py610 = new Paragraph();
		Paragraph py611 = new Paragraph();
		Paragraph py612 = new Paragraph();
		Paragraph py613 = new Paragraph();
		Paragraph py614 = new Paragraph();
		Paragraph py615 = new Paragraph();
		Paragraph py616 = new Paragraph();
		Paragraph py617 = new Paragraph();
		Paragraph py618 = new Paragraph();
		Paragraph py619 = new Paragraph();
		Paragraph py620 = new Paragraph();
		Paragraph py621 = new Paragraph();
		Paragraph py622 = new Paragraph();
		Paragraph py623 = new Paragraph();
		Paragraph py624 = new Paragraph();
		Paragraph py625 = new Paragraph();
		Paragraph py626 = new Paragraph();
		Paragraph py627 = new Paragraph();
		Paragraph py628 = new Paragraph();
		Paragraph py629 = new Paragraph();
		Paragraph py630 = new Paragraph();
		Paragraph py631 = new Paragraph();
		Paragraph py632 = new Paragraph();
		Paragraph py633 = new Paragraph();
		Paragraph py634 = new Paragraph();
		Paragraph py635 = new Paragraph();
		Paragraph py636 = new Paragraph();
		Paragraph py637 = new Paragraph();
		Paragraph py638 = new Paragraph();
		Paragraph py639 = new Paragraph();
		Paragraph py640 = new Paragraph();
		Paragraph py641 = new Paragraph();
		Paragraph py642 = new Paragraph();
		Paragraph py643 = new Paragraph();
		Paragraph py644 = new Paragraph();
		Paragraph py645 = new Paragraph();
		Paragraph py646 = new Paragraph();
		Paragraph py647 = new Paragraph();
		Paragraph py648 = new Paragraph();
		Paragraph py649 = new Paragraph();
		Paragraph py650 = new Paragraph();

		py6 = new Paragraph(("TERM"), fontTableHeading);
		py6.setAlignment(Element.ALIGN_CENTER);
		py61 = new Paragraph(("PERIODICAL ASSESSMENT*"), fontTableHeading);
		py61.setAlignment(Element.ALIGN_CENTER);
		py62 = new Paragraph(("TERM TEST**"), fontTableHeading);
		py62.setAlignment(Element.ALIGN_CENTER);
		py63 = new Paragraph(("TERM ASSESSMENT"), fontTableHeading);
		py63.setAlignment(Element.ALIGN_CENTER);
		py64 = new Paragraph(("A"), fontTableHeading);
		py64.setAlignment(Element.ALIGN_CENTER);
		py65 = new Paragraph(("B"), fontTableHeading);
		py65.setAlignment(Element.ALIGN_CENTER);
		py66 = new Paragraph(("C"), fontTableHeading);
		py66.setAlignment(Element.ALIGN_CENTER);
		py67 = new Paragraph(("D"), fontTableHeading);
		py67.setAlignment(Element.ALIGN_CENTER);
		py68 = new Paragraph(("E"), fontTableHeading);
		py68.setAlignment(Element.ALIGN_CENTER);
		py69 = new Paragraph(("F"), fontTableHeading);
		py69.setAlignment(Element.ALIGN_CENTER);
		py610 = new Paragraph(("G"), fontTableHeading);
		py610.setAlignment(Element.ALIGN_CENTER);
		py611 = new Paragraph(("H"), fontTableHeading);
		py611.setAlignment(Element.ALIGN_CENTER);
		py612 = new Paragraph(("1 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		py612.setAlignment(Element.ALIGN_CENTER);
		py613 = new Paragraph(("2 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		py613.setAlignment(Element.ALIGN_CENTER);
		py614 = new Paragraph(("3 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		py614.setAlignment(Element.ALIGN_CENTER);
		py615 = new Paragraph(("Average\n" + "(A+B+C/3)"), fontTableHeadingNonBoldData);
		py615.setAlignment(Element.ALIGN_CENTER);
		py616 = new Paragraph(("Converted to\n" + "30\n" + "Marks\n" + "(D/15*30)"), fontTableHeadingNonBoldData);
		py616.setAlignment(Element.ALIGN_CENTER);
		py617 = new Paragraph(("Term Test\n" + "(Marks\n" + "converted\n" + "to 30)"), fontTableHeadingNonBoldData);
		py617.setAlignment(Element.ALIGN_CENTER);
		py618 = new Paragraph(("Sub\n" + "Total\n" + "_/60\n" + "Marks"), fontTableHeadingNonBoldData);
		py618.setAlignment(Element.ALIGN_CENTER);
		py619 = new Paragraph(("Term\n" + "Assessment\n" + "(…./30)"), fontTableHeadingNonBoldData);
		py619.setAlignment(Element.ALIGN_CENTER);
		py620 = new Paragraph(("FIRST"), fontTableHeadingNonBoldData);
		py620.setAlignment(Element.ALIGN_CENTER);
		py621 = new Paragraph((""), fontTableHeading);
		py621.setAlignment(Element.ALIGN_CENTER);
		py622 = new Paragraph((""), fontTableHeading);
		py622.setAlignment(Element.ALIGN_CENTER);
		py623 = new Paragraph((""), fontTableHeading);
		py623.setAlignment(Element.ALIGN_CENTER);
		py624 = new Paragraph((""), fontTableHeading);
		py624.setAlignment(Element.ALIGN_CENTER);
		py625 = new Paragraph((""), fontTableHeading);
		py625.setAlignment(Element.ALIGN_CENTER);
		py626 = new Paragraph((""), fontTableHeading);
		py626.setAlignment(Element.ALIGN_CENTER);
		py627 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		py627.setAlignment(Element.ALIGN_CENTER);
		py628 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		py628.setAlignment(Element.ALIGN_CENTER);
		py629 = new Paragraph(("SECOND"), fontTableHeadingNonBoldData);
		py629.setAlignment(Element.ALIGN_CENTER);
		py630 = new Paragraph((""), fontTableHeading);
		py630.setAlignment(Element.ALIGN_CENTER);
		py631 = new Paragraph((""), fontTableHeading);
		py631.setAlignment(Element.ALIGN_CENTER);
		py632 = new Paragraph((""), fontTableHeading);
		py632.setAlignment(Element.ALIGN_CENTER);
		py633 = new Paragraph((""), fontTableHeading);
		py633.setAlignment(Element.ALIGN_CENTER);
		py634 = new Paragraph((""), fontTableHeading);
		py634.setAlignment(Element.ALIGN_CENTER);
		py635 = new Paragraph((""), fontTableHeading);
		py635.setAlignment(Element.ALIGN_CENTER);
		py636 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		py636.setAlignment(Element.ALIGN_CENTER);
		py637 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		py637.setAlignment(Element.ALIGN_CENTER);
		py638 = new Paragraph(("THIRD "), fontTableHeadingNonBoldData);
		py638.setAlignment(Element.ALIGN_CENTER);
		py639 = new Paragraph((""), fontTableHeading);
		py639.setAlignment(Element.ALIGN_CENTER);
		py640 = new Paragraph((""), fontTableHeading);
		py640.setAlignment(Element.ALIGN_CENTER);
		py641 = new Paragraph((""), fontTableHeading);
		py641.setAlignment(Element.ALIGN_CENTER);
		py642 = new Paragraph((""), fontTableHeading);
		py642.setAlignment(Element.ALIGN_CENTER);
		py643 = new Paragraph((""), fontTableHeading);
		py643.setAlignment(Element.ALIGN_CENTER);
		py644 = new Paragraph(("NIL "), fontTableHeadingNonBoldData);
		py644.setAlignment(Element.ALIGN_CENTER);
		py645 = new Paragraph((""), fontTableHeading);
		py645.setAlignment(Element.ALIGN_CENTER);
		py646 = new Paragraph(("E"), fontTableHeadingNonBoldData);
		py646.setAlignment(Element.ALIGN_CENTER);
		py647 = new Paragraph(("Final IA"), fontTableHeading);
		py647.setAlignment(Element.ALIGN_CENTER);
		py648 = new Paragraph(("Average of Three Term Assessment Marks as Shown in ‘H’ Column"),
				fontTableHeadingNonBoldData);
		py648.setAlignment(Element.ALIGN_CENTER);
		py649 = new Paragraph((""), fontTableHeading);
		py649.setAlignment(Element.ALIGN_CENTER);
		py650 = new Paragraph(("Maximum Marks in Parentheses\n"
				+ "*Select an Evaluation Method which is appropriate for the objectives of Topics from the Table 6 D for\n"
				+ "Periodic assessment. Conduct 15 marks assessment and enter marks in A, B, and C.\n"
				+ "** Conduct Theory (100 Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ (4*10)] and Practical (100\n"
				+ "Marks)\n" + "Then convert to 30 marks"), fontTableHeadingNonBoldData);
		py650.setAlignment(Element.ALIGN_CENTER);

		tab6C.getDefaultCell().setRowspan(3);
		tab6C.addCell(py6);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.getDefaultCell().setColspan(5);
		tab6C.addCell(py61);
		tab6C.getDefaultCell().setColspan(1);
		tab6C.addCell(py62);
		tab6C.getDefaultCell().setColspan(2);
		tab6C.addCell(py63);
		tab6C.getDefaultCell().setColspan(1);
		tab6C.addCell(py64);
		tab6C.addCell(py65);
		tab6C.addCell(py66);
		tab6C.addCell(py67);
		tab6C.addCell(py68);
		tab6C.addCell(py69);
		tab6C.addCell(py610);
		tab6C.addCell(py611);
		tab6C.addCell(py612);
		tab6C.addCell(py613);
		tab6C.addCell(py614);
		tab6C.addCell(py615);
		tab6C.addCell(py616);
		tab6C.addCell(py617);
		tab6C.addCell(py618);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py619);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py620);
		tab6C.addCell(py621);
		tab6C.addCell(py622);
		tab6C.addCell(py623);
		tab6C.addCell(py624);
		tab6C.addCell(py625);
		tab6C.addCell(py626);
		tab6C.addCell(py627);
		tab6C.addCell(py628);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py629);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py630);
		tab6C.addCell(py631);
		tab6C.addCell(py632);
		tab6C.addCell(py633);
		tab6C.addCell(py634);
		tab6C.addCell(py635);
		tab6C.addCell(py636);
		tab6C.addCell(py637);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py638);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py639);
		tab6C.addCell(py640);
		tab6C.addCell(py641);
		tab6C.addCell(py642);
		tab6C.addCell(py643);
		tab6C.addCell(py644);
		tab6C.addCell(py645);
		tab6C.addCell(py646);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py647);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.getDefaultCell().setColspan(8);
		tab6C.addCell(py648);
		tab6C.getDefaultCell().setColspan(1);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.addCell(py649);
		tab6C.getDefaultCell().setRowspan(1);
		tab6C.getDefaultCell().setColspan(8);
		tab6C.addCell(py650);
		tab6C.getDefaultCell().setColspan(1);

		PdfPTable tab6C2 = new PdfPTable(8);
		tab6C2.setWidthPercentage(100);
		tab6C2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab6C2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref62 = new PdfPTable(1);
		tableref62.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref62.setWidthPercentage(100);
		tableref62.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		Paragraph p6C2 = new Paragraph("6 C - Calculation Method for Internal assessment Marks(20 marks)]",
				fontTableHeading);
		tableref62.addCell(p6C2);

		Paragraph pt6 = new Paragraph();
		Paragraph pt61 = new Paragraph();
		Paragraph pt62 = new Paragraph();
		Paragraph pt63 = new Paragraph();
		Paragraph pt64 = new Paragraph();
		Paragraph pt65 = new Paragraph();
		Paragraph pt66 = new Paragraph();
		Paragraph pt67 = new Paragraph();
		Paragraph pt68 = new Paragraph();
		Paragraph pt69 = new Paragraph();
		Paragraph pt610 = new Paragraph();
		Paragraph pt612 = new Paragraph();
		Paragraph pt613 = new Paragraph();
		Paragraph pt614 = new Paragraph();
		Paragraph pt615 = new Paragraph();
		Paragraph pt616 = new Paragraph();
		Paragraph pt617 = new Paragraph();
		Paragraph pt618 = new Paragraph();
		Paragraph pt620 = new Paragraph();
		Paragraph pt621 = new Paragraph();
		Paragraph pt622 = new Paragraph();
		Paragraph pt623 = new Paragraph();
		Paragraph pt624 = new Paragraph();
		Paragraph pt625 = new Paragraph();
		Paragraph pt626 = new Paragraph();
		Paragraph pt627 = new Paragraph();
		Paragraph pt628 = new Paragraph();
		Paragraph pt629 = new Paragraph();
		Paragraph pt630 = new Paragraph();
		Paragraph pt631 = new Paragraph();
		Paragraph pt632 = new Paragraph();
		Paragraph pt633 = new Paragraph();
		Paragraph pt634 = new Paragraph();
		Paragraph pt635 = new Paragraph();
		Paragraph pt636 = new Paragraph();
		Paragraph pt637 = new Paragraph();
		Paragraph pt638 = new Paragraph();
		Paragraph pt639 = new Paragraph();
		Paragraph pt640 = new Paragraph();
		Paragraph pt641 = new Paragraph();
		Paragraph pt642 = new Paragraph();
		Paragraph pt643 = new Paragraph();
		Paragraph pt644 = new Paragraph();
		Paragraph pt645 = new Paragraph();
		pt6 = new Paragraph(("TERM"), fontTableHeading);
		pt6.setAlignment(Element.ALIGN_CENTER);
		pt61 = new Paragraph(("PERIODICAL ASSESSMENT*"), fontTableHeading);
		pt61.setAlignment(Element.ALIGN_CENTER);
		pt62 = new Paragraph(("TERM TEST**"), fontTableHeading);
		pt62.setAlignment(Element.ALIGN_CENTER);
		pt63 = new Paragraph(("TERM ASSESSMENT"), fontTableHeading);
		pt63.setAlignment(Element.ALIGN_CENTER);
		pt64 = new Paragraph(("A"), fontTableHeading);
		pt64.setAlignment(Element.ALIGN_CENTER);
		pt65 = new Paragraph(("B"), fontTableHeading);
		pt65.setAlignment(Element.ALIGN_CENTER);
		pt66 = new Paragraph(("C"), fontTableHeading);
		pt66.setAlignment(Element.ALIGN_CENTER);
		pt67 = new Paragraph(("D"), fontTableHeading);
		pt67.setAlignment(Element.ALIGN_CENTER);
		pt68 = new Paragraph(("E"), fontTableHeading);
		pt68.setAlignment(Element.ALIGN_CENTER);
		pt69 = new Paragraph(("F"), fontTableHeading);
		pt69.setAlignment(Element.ALIGN_CENTER);
		pt610 = new Paragraph(("G"), fontTableHeading);
		pt610.setAlignment(Element.ALIGN_CENTER);
		pt612 = new Paragraph(("1\n" + "(20)"), fontTableHeadingNonBoldData);
		pt612.setAlignment(Element.ALIGN_CENTER);
		pt613 = new Paragraph(("2\n" + "(20)"), fontTableHeadingNonBoldData);
		pt613.setAlignment(Element.ALIGN_CENTER);
		pt614 = new Paragraph(("3\n" + "(20)"), fontTableHeadingNonBoldData);
		pt614.setAlignment(Element.ALIGN_CENTER);
		pt615 = new Paragraph(("Average\n" + "(A+B+C/3)\n" + " 20 "), fontTableHeadingNonBoldData);
		pt615.setAlignment(Element.ALIGN_CENTER);
		pt616 = new Paragraph(("Term Test\n" + "(MCQ+SAQ+LAQ\n" + "Marks\n" + "and Practical\n" + "(Converted to 20)"),
				fontTableHeadingNonBoldData);
		pt616.setAlignment(Element.ALIGN_CENTER);
		pt617 = new Paragraph(("Sub\n" + "Total"), fontTableHeadingNonBoldData);
		pt617.setAlignment(Element.ALIGN_CENTER);
		pt618 = new Paragraph(("Term\n" + "Assessment"), fontTableHeadingNonBoldData);
		pt618.setAlignment(Element.ALIGN_CENTER);
		pt620 = new Paragraph(("FIRST"), fontTableHeadingNonBoldData);
		pt620.setAlignment(Element.ALIGN_CENTER);
		pt621 = new Paragraph((""), fontTableHeading);
		pt621.setAlignment(Element.ALIGN_CENTER);
		pt622 = new Paragraph((""), fontTableHeading);
		pt622.setAlignment(Element.ALIGN_CENTER);
		pt623 = new Paragraph((""), fontTableHeading);
		pt623.setAlignment(Element.ALIGN_CENTER);
		pt624 = new Paragraph((""), fontTableHeading);
		pt624.setAlignment(Element.ALIGN_CENTER);
		pt625 = new Paragraph((""), fontTableHeading);
		pt625.setAlignment(Element.ALIGN_CENTER);
		pt626 = new Paragraph(("D+E"), fontTableHeadingNonBoldData);
		pt626.setAlignment(Element.ALIGN_CENTER);
		pt627 = new Paragraph(("D+E /2"), fontTableHeadingNonBoldData);
		pt627.setAlignment(Element.ALIGN_CENTER);
		pt628 = new Paragraph(("SECOND"), fontTableHeadingNonBoldData);
		pt628.setAlignment(Element.ALIGN_CENTER);
		pt629 = new Paragraph((""), fontTableHeadingNonBoldData);
		pt629.setAlignment(Element.ALIGN_CENTER);
		pt630 = new Paragraph((""), fontTableHeading);
		pt630.setAlignment(Element.ALIGN_CENTER);
		pt631 = new Paragraph((""), fontTableHeading);
		pt631.setAlignment(Element.ALIGN_CENTER);
		pt632 = new Paragraph((""), fontTableHeading);
		pt632.setAlignment(Element.ALIGN_CENTER);
		pt633 = new Paragraph((""), fontTableHeading);
		pt633.setAlignment(Element.ALIGN_CENTER);
		pt634 = new Paragraph(("D+E"), fontTableHeadingNonBoldData);
		pt634.setAlignment(Element.ALIGN_CENTER);
		pt635 = new Paragraph(("D+E /2"), fontTableHeadingNonBoldData);
		pt635.setAlignment(Element.ALIGN_CENTER);
		pt636 = new Paragraph(("THIRD"), fontTableHeadingNonBoldData);
		pt636.setAlignment(Element.ALIGN_CENTER);
		pt637 = new Paragraph((""), fontTableHeadingNonBoldData);
		pt637.setAlignment(Element.ALIGN_CENTER);
		pt638 = new Paragraph((""), fontTableHeadingNonBoldData);
		pt638.setAlignment(Element.ALIGN_CENTER);
		pt639 = new Paragraph((""), fontTableHeading);
		pt639.setAlignment(Element.ALIGN_CENTER);
		pt640 = new Paragraph((""), fontTableHeading);
		pt640.setAlignment(Element.ALIGN_CENTER);
		pt641 = new Paragraph(("NIL"), fontTableHeadingNonBoldData);
		pt641.setAlignment(Element.ALIGN_CENTER);
		pt642 = new Paragraph(("D"), fontTableHeadingNonBoldData);
		pt642.setAlignment(Element.ALIGN_CENTER);
		pt643 = new Paragraph(("D"), fontTableHeadingNonBoldData);
		pt643.setAlignment(Element.ALIGN_CENTER);
		pt644 = new Paragraph(("Final IA"), fontTableHeadingNonBoldData);
		pt644.setAlignment(Element.ALIGN_CENTER);
		pt645 = new Paragraph(("Average of Three Term Assessment Marks as Shown in ‘G’ Column"),
				fontTableHeadingNonBoldData);
		pt645.setAlignment(Element.ALIGN_CENTER);

		tab6C2.getDefaultCell().setRowspan(3);
		tab6C2.addCell(pt6);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.getDefaultCell().setColspan(4);
		tab6C2.addCell(pt61);
		tab6C2.getDefaultCell().setColspan(1);
		tab6C2.addCell(pt62);
		tab6C2.getDefaultCell().setColspan(2);
		tab6C2.addCell(pt63);
		tab6C2.getDefaultCell().setColspan(1);
		tab6C2.addCell(pt64);
		tab6C2.addCell(pt65);
		tab6C2.addCell(pt66);
		tab6C2.addCell(pt67);
		tab6C2.addCell(pt68);
		tab6C2.addCell(pt69);
		tab6C2.addCell(pt610);
		tab6C2.addCell(pt612);
		tab6C2.addCell(pt613);
		tab6C2.addCell(pt614);
		tab6C2.addCell(pt615);
		tab6C2.addCell(pt616);
		tab6C2.addCell(pt617);
		tab6C2.addCell(pt618);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.addCell(pt620);
		tab6C2.addCell(pt621);
		tab6C2.addCell(pt622);
		tab6C2.addCell(pt623);
		tab6C2.addCell(pt624);
		tab6C2.addCell(pt625);
		tab6C2.addCell(pt626);
		tab6C2.addCell(pt627);
		tab6C2.addCell(pt628);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.addCell(pt629);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.addCell(pt630);
		tab6C2.addCell(pt631);
		tab6C2.addCell(pt632);
		tab6C2.addCell(pt633);
		tab6C2.addCell(pt634);
		tab6C2.addCell(pt635);
		tab6C2.addCell(pt636);
		tab6C2.addCell(pt637);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.addCell(pt638);
		tab6C2.getDefaultCell().setRowspan(1);
		tab6C2.addCell(pt639);
		tab6C2.addCell(pt640);
		tab6C2.addCell(pt641);
		tab6C2.addCell(pt642);
		tab6C2.addCell(pt643);
		tab6C2.addCell(pt644);
		tab6C2.getDefaultCell().setColspan(7);
		tab6C2.addCell(pt645);
		tab6C2.getDefaultCell().setColspan(1);

		PdfPTable tab6C3 = new PdfPTable(9);
		tab6C3.setWidthPercentage(100);
		tab6C3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab6C3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref63 = new PdfPTable(1);
		tableref63.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref63.setWidthPercentage(100);
		tableref63.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		Paragraph p6C3 = new Paragraph("6 C - Calculation Method for Internal assessment Marks (15 Marks) ",
				fontTableHeading);
		tableref63.addCell(p6C3);

		Paragraph pa6 = new Paragraph();
		Paragraph pa61 = new Paragraph();
		Paragraph pa62 = new Paragraph();
		Paragraph pa63 = new Paragraph();
		Paragraph pa64 = new Paragraph();
		Paragraph pa65 = new Paragraph();
		Paragraph pa66 = new Paragraph();
		Paragraph pa67 = new Paragraph();
		Paragraph pa68 = new Paragraph();
		Paragraph pa69 = new Paragraph();
		Paragraph pa610 = new Paragraph();
		Paragraph pa611 = new Paragraph();
		Paragraph pa612 = new Paragraph();
		Paragraph pa613 = new Paragraph();
		Paragraph pa614 = new Paragraph();
		Paragraph pa615 = new Paragraph();
		Paragraph pa616 = new Paragraph();
		Paragraph pa617 = new Paragraph();
		Paragraph pa618 = new Paragraph();
		Paragraph pa619 = new Paragraph();
		Paragraph pa620 = new Paragraph();
		Paragraph pa621 = new Paragraph();
		Paragraph pa622 = new Paragraph();
		Paragraph pa623 = new Paragraph();
		Paragraph pa624 = new Paragraph();
		Paragraph pa625 = new Paragraph();
		Paragraph pa626 = new Paragraph();
		Paragraph pa627 = new Paragraph();
		Paragraph pa628 = new Paragraph();
		Paragraph pa629 = new Paragraph();
		Paragraph pa630 = new Paragraph();
		Paragraph pa631 = new Paragraph();
		Paragraph pa632 = new Paragraph();
		Paragraph pa633 = new Paragraph();
		Paragraph pa634 = new Paragraph();
		Paragraph pa635 = new Paragraph();
		Paragraph pa636 = new Paragraph();
		Paragraph pa637 = new Paragraph();
		Paragraph pa638 = new Paragraph();
		Paragraph pa639 = new Paragraph();
		Paragraph pa640 = new Paragraph();
		Paragraph pa641 = new Paragraph();
		Paragraph pa642 = new Paragraph();
		Paragraph pa643 = new Paragraph();
		Paragraph pa644 = new Paragraph();
		Paragraph pa645 = new Paragraph();
		Paragraph pa646 = new Paragraph();
		Paragraph pa647 = new Paragraph();
		Paragraph pa648 = new Paragraph();
		Paragraph pa649 = new Paragraph();
		Paragraph pa650 = new Paragraph();

		pa6 = new Paragraph(("TERM"), fontTableHeading);
		pa6.setAlignment(Element.ALIGN_CENTER);
		pa61 = new Paragraph(("PERIODICAL ASSESSMENT*"), fontTableHeading);
		pa61.setAlignment(Element.ALIGN_CENTER);
		pa62 = new Paragraph(("TERM TEST**"), fontTableHeading);
		pa62.setAlignment(Element.ALIGN_CENTER);
		pa63 = new Paragraph(("TERM ASSESSMENT"), fontTableHeading);
		pa63.setAlignment(Element.ALIGN_CENTER);
		pa64 = new Paragraph(("A"), fontTableHeading);
		pa64.setAlignment(Element.ALIGN_CENTER);
		pa65 = new Paragraph(("B"), fontTableHeading);
		pa65.setAlignment(Element.ALIGN_CENTER);
		pa66 = new Paragraph(("C"), fontTableHeading);
		pa66.setAlignment(Element.ALIGN_CENTER);
		pa67 = new Paragraph(("D"), fontTableHeading);
		pa67.setAlignment(Element.ALIGN_CENTER);
		pa68 = new Paragraph(("E"), fontTableHeading);
		pa68.setAlignment(Element.ALIGN_CENTER);
		pa69 = new Paragraph(("F"), fontTableHeading);
		pa69.setAlignment(Element.ALIGN_CENTER);
		pa610 = new Paragraph(("G"), fontTableHeading);
		pa610.setAlignment(Element.ALIGN_CENTER);
		pa611 = new Paragraph(("H"), fontTableHeading);
		pa611.setAlignment(Element.ALIGN_CENTER);
		pa612 = new Paragraph(("1 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pa612.setAlignment(Element.ALIGN_CENTER);
		pa613 = new Paragraph(("2 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pa613.setAlignment(Element.ALIGN_CENTER);
		pa614 = new Paragraph(("3 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pa614.setAlignment(Element.ALIGN_CENTER);
		pa615 = new Paragraph(("Average\n" + "(A+B+C/3)"), fontTableHeadingNonBoldData);
		pa615.setAlignment(Element.ALIGN_CENTER);
		pa616 = new Paragraph(("Converted to\n" + "30\n" + "Marks\n" + "(D/15*15)"), fontTableHeadingNonBoldData);
		pa616.setAlignment(Element.ALIGN_CENTER);
		pa617 = new Paragraph(("Term Test\n" + "(Marks\n" + "converted\n" + "to 15)" + "(15 Marks)"),
				fontTableHeadingNonBoldData);
		pa617.setAlignment(Element.ALIGN_CENTER);
		pa618 = new Paragraph(("Sub\n" + "Total\n" + "_/30\n" + "Marks"), fontTableHeadingNonBoldData);
		pa618.setAlignment(Element.ALIGN_CENTER);
		pa619 = new Paragraph(("Term\n" + "Assessment\n" + "(…./15)"), fontTableHeadingNonBoldData);
		pa619.setAlignment(Element.ALIGN_CENTER);
		pa620 = new Paragraph(("FIRST"), fontTableHeadingNonBoldData);
		pa620.setAlignment(Element.ALIGN_CENTER);
		pa621 = new Paragraph((""), fontTableHeading);
		pa621.setAlignment(Element.ALIGN_CENTER);
		pa622 = new Paragraph((""), fontTableHeading);
		pa622.setAlignment(Element.ALIGN_CENTER);
		pa623 = new Paragraph((""), fontTableHeading);
		pa623.setAlignment(Element.ALIGN_CENTER);
		pa624 = new Paragraph((""), fontTableHeading);
		pa624.setAlignment(Element.ALIGN_CENTER);
		pa625 = new Paragraph((""), fontTableHeading);
		pa625.setAlignment(Element.ALIGN_CENTER);
		pa626 = new Paragraph((""), fontTableHeading);
		pa626.setAlignment(Element.ALIGN_CENTER);
		pa627 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		pa627.setAlignment(Element.ALIGN_CENTER);
		pa628 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		pa628.setAlignment(Element.ALIGN_CENTER);
		pa629 = new Paragraph(("SECOND"), fontTableHeadingNonBoldData);
		pa629.setAlignment(Element.ALIGN_CENTER);
		pa630 = new Paragraph((""), fontTableHeading);
		pa630.setAlignment(Element.ALIGN_CENTER);
		pa631 = new Paragraph((""), fontTableHeading);
		pa631.setAlignment(Element.ALIGN_CENTER);
		pa632 = new Paragraph((""), fontTableHeading);
		pa632.setAlignment(Element.ALIGN_CENTER);
		pa633 = new Paragraph((""), fontTableHeading);
		pa633.setAlignment(Element.ALIGN_CENTER);
		pa634 = new Paragraph((""), fontTableHeading);
		pa634.setAlignment(Element.ALIGN_CENTER);
		pa635 = new Paragraph((""), fontTableHeading);
		pa635.setAlignment(Element.ALIGN_CENTER);
		pa636 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		pa636.setAlignment(Element.ALIGN_CENTER);
		pa637 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		pa637.setAlignment(Element.ALIGN_CENTER);
		pa638 = new Paragraph(("THIRD "), fontTableHeadingNonBoldData);
		pa638.setAlignment(Element.ALIGN_CENTER);
		pa639 = new Paragraph((""), fontTableHeading);
		pa639.setAlignment(Element.ALIGN_CENTER);
		pa640 = new Paragraph((""), fontTableHeading);
		pa640.setAlignment(Element.ALIGN_CENTER);
		pa641 = new Paragraph((""), fontTableHeading);
		pa641.setAlignment(Element.ALIGN_CENTER);
		pa642 = new Paragraph((""), fontTableHeading);
		pa642.setAlignment(Element.ALIGN_CENTER);
		pa643 = new Paragraph((""), fontTableHeading);
		pa643.setAlignment(Element.ALIGN_CENTER);
		pa644 = new Paragraph(("NIL "), fontTableHeadingNonBoldData);
		pa644.setAlignment(Element.ALIGN_CENTER);
		pa645 = new Paragraph((""), fontTableHeading);
		pa645.setAlignment(Element.ALIGN_CENTER);
		pa646 = new Paragraph(("E"), fontTableHeadingNonBoldData);
		pa646.setAlignment(Element.ALIGN_CENTER);
		pa647 = new Paragraph(("Final IA"), fontTableHeading);
		pa647.setAlignment(Element.ALIGN_CENTER);
		pa648 = new Paragraph(("Average of Three Term Assessment Marks as Shown in ‘H’ Column"),
				fontTableHeadingNonBoldData);
		pa648.setAlignment(Element.ALIGN_CENTER);
		pa649 = new Paragraph((""), fontTableHeading);
		pa649.setAlignment(Element.ALIGN_CENTER);
		pa650 = new Paragraph(("Maximum Marks in Parentheses\n"
				+ "*Select an Evaluation Method which is appropriate for the objectives of Topics from the Table 6 D\n"
				+ "for Periodic assessment. Conduct 15 marks assessment and enter marks in A, B, and C.\n"
				+ "** Conduct Theory (100 Marks)(MCQ(20*1 Marks), SAQ(8*5), LAQ(4*10)) and Practical (100\n"
				+ "Marks)\n" + "Then convert to 15 marks."), fontTableHeadingNonBoldData);
		pa650.setAlignment(Element.ALIGN_CENTER);

		tab6C3.getDefaultCell().setRowspan(3);
		tab6C3.addCell(pa6);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.getDefaultCell().setColspan(5);
		tab6C3.addCell(pa61);
		tab6C3.getDefaultCell().setColspan(1);
		tab6C3.addCell(pa62);
		tab6C3.getDefaultCell().setColspan(2);
		tab6C3.addCell(pa63);
		tab6C3.getDefaultCell().setColspan(1);
		tab6C3.addCell(pa64);
		tab6C3.addCell(pa65);
		tab6C3.addCell(pa66);
		tab6C3.addCell(pa67);
		tab6C3.addCell(pa68);
		tab6C3.addCell(pa69);
		tab6C3.addCell(pa610);
		tab6C3.addCell(pa611);
		tab6C3.addCell(pa612);
		tab6C3.addCell(pa613);
		tab6C3.addCell(pa614);
		tab6C3.addCell(pa615);
		tab6C3.addCell(pa616);
		tab6C3.addCell(pa617);
		tab6C3.addCell(pa618);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa619);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa620);
		tab6C3.addCell(pa621);
		tab6C3.addCell(pa622);
		tab6C3.addCell(pa623);
		tab6C3.addCell(pa624);
		tab6C3.addCell(pa625);
		tab6C3.addCell(pa626);
		tab6C3.addCell(pa627);
		tab6C3.addCell(pa628);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa629);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa630);
		tab6C3.addCell(pa631);
		tab6C3.addCell(pa632);
		tab6C3.addCell(pa633);
		tab6C3.addCell(pa634);
		tab6C3.addCell(pa635);
		tab6C3.addCell(pa636);
		tab6C3.addCell(pa637);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa638);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa639);
		tab6C3.addCell(pa640);
		tab6C3.addCell(pa641);
		tab6C3.addCell(pa642);
		tab6C3.addCell(pa643);
		tab6C3.addCell(pa644);
		tab6C3.addCell(pa645);
		tab6C3.addCell(pa646);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa647);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.getDefaultCell().setColspan(8);
		tab6C3.addCell(pa648);
		tab6C3.getDefaultCell().setColspan(1);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.addCell(pa649);
		tab6C3.getDefaultCell().setRowspan(1);
		tab6C3.getDefaultCell().setColspan(8);
		tab6C3.addCell(pa650);
		tab6C3.getDefaultCell().setColspan(1);

//6 C - Calculation Method for Internal assessment Marks (40 Marks) ==========================

		PdfPTable tabl6C = new PdfPTable(9);
		tabl6C.setWidthPercentage(100);
		tabl6C.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tabl6C.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref33 = new PdfPTable(1);
		tableref33.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref33.setWidthPercentage(100);
		tableref33.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);

		Paragraph pa6C = new Paragraph("6 C - Calculation Method for Internal assessment Marks (40 Marks) ",
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
		Paragraph pg68 = new Paragraph();
		Paragraph pg69 = new Paragraph();
		Paragraph pg610 = new Paragraph();
		Paragraph pg611 = new Paragraph();
		Paragraph pg612 = new Paragraph();
		Paragraph pg613 = new Paragraph();
		Paragraph pg614 = new Paragraph();
		Paragraph pg615 = new Paragraph();
		Paragraph pg616 = new Paragraph();
		Paragraph pg617 = new Paragraph();
		Paragraph pg618 = new Paragraph();
		Paragraph pg619 = new Paragraph();
		Paragraph pg620 = new Paragraph();
		Paragraph pg621 = new Paragraph();
		Paragraph pg622 = new Paragraph();
		Paragraph pg623 = new Paragraph();
		Paragraph pg624 = new Paragraph();
		Paragraph pg625 = new Paragraph();
		Paragraph pg626 = new Paragraph();
		Paragraph pg627 = new Paragraph();
		Paragraph pg628 = new Paragraph();
		Paragraph pg629 = new Paragraph();
		Paragraph pg630 = new Paragraph();
		Paragraph pg631 = new Paragraph();
		Paragraph pg632 = new Paragraph();
		Paragraph pg633 = new Paragraph();
		Paragraph pg634 = new Paragraph();
		Paragraph pg635 = new Paragraph();
		Paragraph pg636 = new Paragraph();
		Paragraph pg637 = new Paragraph();
		Paragraph pg638 = new Paragraph();
		Paragraph pg639 = new Paragraph();
		Paragraph pg640 = new Paragraph();
		Paragraph pg641 = new Paragraph();
		Paragraph pg642 = new Paragraph();
		Paragraph pg643 = new Paragraph();
		Paragraph pg644 = new Paragraph();
		Paragraph pg645 = new Paragraph();
		Paragraph pg646 = new Paragraph();
		Paragraph pg647 = new Paragraph();
		Paragraph pg648 = new Paragraph();
		Paragraph pg649 = new Paragraph();
		Paragraph pg650 = new Paragraph();

		pg6 = new Paragraph(("TERM"), fontTableHeading);
		pg6.setAlignment(Element.ALIGN_CENTER);
		pg61 = new Paragraph(("PERIODICAL ASSESSMENT*"), fontTableHeading);
		pg61.setAlignment(Element.ALIGN_CENTER);
		pg62 = new Paragraph(("TERM TEST**"), fontTableHeading);
		pg62.setAlignment(Element.ALIGN_CENTER);
		pg63 = new Paragraph(("TERM ASSESSMENT"), fontTableHeading);
		pg63.setAlignment(Element.ALIGN_CENTER);
		pg64 = new Paragraph(("A"), fontTableHeading);
		pg64.setAlignment(Element.ALIGN_CENTER);
		pg65 = new Paragraph(("B"), fontTableHeading);
		pg65.setAlignment(Element.ALIGN_CENTER);
		pg66 = new Paragraph(("C"), fontTableHeading);
		pg66.setAlignment(Element.ALIGN_CENTER);
		pg67 = new Paragraph(("D"), fontTableHeading);
		pg67.setAlignment(Element.ALIGN_CENTER);
		pg68 = new Paragraph(("E"), fontTableHeading);
		pg68.setAlignment(Element.ALIGN_CENTER);
		pg69 = new Paragraph(("F"), fontTableHeading);
		pg69.setAlignment(Element.ALIGN_CENTER);
		pg610 = new Paragraph(("G"), fontTableHeading);
		pg610.setAlignment(Element.ALIGN_CENTER);
		pg611 = new Paragraph(("H"), fontTableHeading);
		pg611.setAlignment(Element.ALIGN_CENTER);
		pg612 = new Paragraph(("1 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pg612.setAlignment(Element.ALIGN_CENTER);
		pg613 = new Paragraph(("2 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pg613.setAlignment(Element.ALIGN_CENTER);
		pg614 = new Paragraph(("3 (15\n" + "Marks)"), fontTableHeadingNonBoldData);
		pg614.setAlignment(Element.ALIGN_CENTER);
		pg615 = new Paragraph(("Average\n" + "(A+B+C/3)"), fontTableHeadingNonBoldData);
		pg615.setAlignment(Element.ALIGN_CENTER);
		pg616 = new Paragraph(("Converted to\n" + "40\n" + "Marks\n" + "(D/20*40)"), fontTableHeadingNonBoldData);
		pg616.setAlignment(Element.ALIGN_CENTER);
		pg617 = new Paragraph(("Term Test\n" + "(Marks\n" + "converted\n" + "to 40)"), fontTableHeadingNonBoldData);
		pg617.setAlignment(Element.ALIGN_CENTER);
		pg618 = new Paragraph(("Sub\n" + "Total\n" + "_/80\n" + "Marks"), fontTableHeadingNonBoldData);
		pg618.setAlignment(Element.ALIGN_CENTER);
		pg619 = new Paragraph(("Term\n" + "Assessment\n" + "(…./40)"), fontTableHeadingNonBoldData);
		pg619.setAlignment(Element.ALIGN_CENTER);
		pg620 = new Paragraph(("FIRST"), fontTableHeadingNonBoldData);
		pg620.setAlignment(Element.ALIGN_CENTER);
		pg621 = new Paragraph((""), fontTableHeading);
		pg621.setAlignment(Element.ALIGN_CENTER);
		pg622 = new Paragraph((""), fontTableHeading);
		pg622.setAlignment(Element.ALIGN_CENTER);
		pg623 = new Paragraph((""), fontTableHeading);
		pg623.setAlignment(Element.ALIGN_CENTER);
		pg624 = new Paragraph((""), fontTableHeading);
		pg624.setAlignment(Element.ALIGN_CENTER);
		pg625 = new Paragraph((""), fontTableHeading);
		pg625.setAlignment(Element.ALIGN_CENTER);
		pg626 = new Paragraph((""), fontTableHeading);
		pg626.setAlignment(Element.ALIGN_CENTER);
		pg627 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		pg627.setAlignment(Element.ALIGN_CENTER);
		pg628 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		pg628.setAlignment(Element.ALIGN_CENTER);
		pg629 = new Paragraph(("SECOND"), fontTableHeadingNonBoldData);
		pg629.setAlignment(Element.ALIGN_CENTER);
		pg630 = new Paragraph((""), fontTableHeading);
		pg630.setAlignment(Element.ALIGN_CENTER);
		pg631 = new Paragraph((""), fontTableHeading);
		pg631.setAlignment(Element.ALIGN_CENTER);
		pg632 = new Paragraph((""), fontTableHeading);
		pg632.setAlignment(Element.ALIGN_CENTER);
		pg633 = new Paragraph((""), fontTableHeading);
		pg633.setAlignment(Element.ALIGN_CENTER);
		pg634 = new Paragraph((""), fontTableHeading);
		pg634.setAlignment(Element.ALIGN_CENTER);
		pg635 = new Paragraph((""), fontTableHeading);
		pg635.setAlignment(Element.ALIGN_CENTER);
		pg636 = new Paragraph(("E+F"), fontTableHeadingNonBoldData);
		pg636.setAlignment(Element.ALIGN_CENTER);
		pg637 = new Paragraph(("(E+F)/2"), fontTableHeadingNonBoldData);
		pg637.setAlignment(Element.ALIGN_CENTER);
		pg638 = new Paragraph(("THIRD "), fontTableHeadingNonBoldData);
		pg638.setAlignment(Element.ALIGN_CENTER);
		pg639 = new Paragraph((""), fontTableHeading);
		pg639.setAlignment(Element.ALIGN_CENTER);
		pg640 = new Paragraph((""), fontTableHeading);
		pg640.setAlignment(Element.ALIGN_CENTER);
		pg641 = new Paragraph((""), fontTableHeading);
		pg641.setAlignment(Element.ALIGN_CENTER);
		pg642 = new Paragraph((""), fontTableHeading);
		pg642.setAlignment(Element.ALIGN_CENTER);
		pg643 = new Paragraph((""), fontTableHeading);
		pg643.setAlignment(Element.ALIGN_CENTER);
		pg644 = new Paragraph(("NIL "), fontTableHeadingNonBoldData);
		pg644.setAlignment(Element.ALIGN_CENTER);
		pg645 = new Paragraph((""), fontTableHeading);
		pg645.setAlignment(Element.ALIGN_CENTER);
		pg646 = new Paragraph(("E"), fontTableHeadingNonBoldData);
		pg646.setAlignment(Element.ALIGN_CENTER);
		pg647 = new Paragraph(("Final IA"), fontTableHeading);
		pg647.setAlignment(Element.ALIGN_CENTER);
		pg648 = new Paragraph(("Average of Three Term Assessment Marks as Shown in ‘H’ Column"),
				fontTableHeadingNonBoldData);
		pg648.setAlignment(Element.ALIGN_CENTER);
		pg649 = new Paragraph((""), fontTableHeading);
		pg649.setAlignment(Element.ALIGN_CENTER);
		pg650 = new Paragraph(("Maximum Marks in Parentheses\n"
				+ "*Select an Evaluation Method which is appropriate for the objectives of Topics from the Table 6 D for\n"
				+ "Periodic assessment. Conduct 15 marks assessment and enter marks in A, B, and C.\n"
				+ "** Conduct Theory (100 Marks) [MCQ (20*1 Marks), SAQ (8*5), LAQ (4*10)] and Practical (100\n"
				+ "Marks)\n" + "Then convert to 40 marks"), fontTableHeadingNonBoldData);
		pg650.setAlignment(Element.ALIGN_CENTER);

		tabl6C.getDefaultCell().setRowspan(3);
		tabl6C.addCell(pg6);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.getDefaultCell().setColspan(5);
		tabl6C.addCell(pg61);
		tabl6C.getDefaultCell().setColspan(1);
		tabl6C.addCell(pg62);
		tabl6C.getDefaultCell().setColspan(2);
		tabl6C.addCell(pg63);
		tabl6C.getDefaultCell().setColspan(1);
		tabl6C.addCell(pg64);
		tabl6C.addCell(pg65);
		tabl6C.addCell(pg66);
		tabl6C.addCell(pg67);
		tabl6C.addCell(pg68);
		tabl6C.addCell(pg69);
		tabl6C.addCell(pg610);
		tabl6C.addCell(pg611);
		tabl6C.addCell(pg612);
		tabl6C.addCell(pg613);
		tabl6C.addCell(pg614);
		tabl6C.addCell(pg615);
		tabl6C.addCell(pg616);
		tabl6C.addCell(pg617);
		tabl6C.addCell(pg618);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg619);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg620);
		tabl6C.addCell(pg621);
		tabl6C.addCell(pg622);
		tabl6C.addCell(pg623);
		tabl6C.addCell(pg624);
		tabl6C.addCell(pg625);
		tabl6C.addCell(pg626);
		tabl6C.addCell(pg627);
		tabl6C.addCell(pg628);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg629);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg630);
		tabl6C.addCell(pg631);
		tabl6C.addCell(pg632);
		tabl6C.addCell(pg633);
		tabl6C.addCell(pg634);
		tabl6C.addCell(pg635);
		tabl6C.addCell(pg636);
		tabl6C.addCell(pg637);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg638);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg639);
		tabl6C.addCell(pg640);
		tabl6C.addCell(pg641);
		tabl6C.addCell(pg642);
		tabl6C.addCell(pg643);
		tabl6C.addCell(pg644);
		tabl6C.addCell(pg645);
		tabl6C.addCell(pg646);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg647);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.getDefaultCell().setColspan(8);
		tabl6C.addCell(pg648);
		tabl6C.getDefaultCell().setColspan(1);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.addCell(pg649);
		tabl6C.getDefaultCell().setRowspan(1);
		tabl6C.getDefaultCell().setColspan(8);
		tabl6C.addCell(pg650);
		tabl6C.getDefaultCell().setColspan(1);

		// end of 6 C - Calculation Method for Internal assessment Marks

		// 6 D - Evaluation Methods for Periodical Assessment===============================

		ArrayList<ArrayList<String>> T6DList = (ArrayList<ArrayList<String>>) Table6Dnlac_list;
		PdfPTable table6Dnlaclink = new PdfPTable(1);
		table6Dnlaclink.setWidthPercentage(100);
		table6Dnlaclink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6Dnlaclink.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable table6Dnlac = new PdfPTable(1);
		table6Dnlac.setWidthPercentage(100);
		table6Dnlac.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6D = new Chunk("6 D - Evaluation Methods for Periodical Assessment", fontTableHeading);
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
		pt6D1 = new Paragraph(TH6D.get(0), fontTableHeading);
		pt6D1.setAlignment(Element.ALIGN_CENTER);
		table6Dnlacdata.addCell(pt6D1);
		pt6D2 = new Paragraph(TH6D.get(1), fontTableHeading);
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

		table6Dnlaclink.addCell(table6Dnlac);
		table6Dnlaclink.addCell(table6Dnlacdata);

		// End 6 D - Evaluation Methods for Periodical Assessment===============================

//Table 6E: Paper Layout===============================

		ArrayList<ArrayList<String>> T6E1List = (ArrayList<ArrayList<String>>) table_6E1;
		PdfPTable tab6E = new PdfPTable(5);
		Paragraph p7568;
		tab6E.setWidthPercentage(100);
		tab6E.setWidths(new int[] { 15, 50, 20, 14, 15 });
		tab6E.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab6E.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref2 = new PdfPTable(1);
		tableref2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref2.setWidthPercentage(100);
		tableref2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

		Paragraph p6E = new Paragraph("6 E - Paper Layout", fontTableHeading);
		tableref2.addCell(p6E);
		Paragraph p6E2 = new Paragraph(cars.get(0).get("course_code").toString(), fontTableHeading);
		tableref2.addCell(p6E2);
		Paragraph p6E3 = new Paragraph("PAPER-I", fontTableHeading);
		tableref2.addCell(p6E3);
		Paragraph p6E4 = new Paragraph("Time: 3 Hours               Maximum Marks: 100", fontTableHeadingNonBoldData);
		tableref2.addCell(p6E4);
		Paragraph p6E5 = new Paragraph("INSTRUCTIONS: All questions compulsory", fontTableHeadingNonBoldData);
		tableref2.addCell(p6E5);
		Paragraph p6E6 = new Paragraph("TOTAL MARKS : 100 ", fontTableHeadingNonBoldData);
		tableref2.addCell(p6E6);

		Paragraph pe61 = new Paragraph();
		Paragraph pe62 = new Paragraph();
		Paragraph pe63 = new Paragraph();
		Paragraph pe64 = new Paragraph();
		Paragraph pe65 = new Paragraph();

		pe61 = new Paragraph((""), fontTableHeading);
		pe61.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe61);
		pe62 = new Paragraph((""), fontTableHeading);
		pe62.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe62);
		pe63 = new Paragraph(("Number of\n" + "Questions"), fontTableHeading);
		pe63.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe63);
		pe64 = new Paragraph(("Marks per\n" + "question"), fontTableHeading);
		pe64.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe64);
		pe65 = new Paragraph(("Total\n" + "Marks "), fontTableHeading);
		pe65.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe65);

		int lasttotal = 0;

		for (int i = 0; i < T6E1List.size(); i++) {
			List<String> l = T6E1List.get(i);
			for (int j = 0; j < l.size(); j++) {
				if (j == 4) {
					lasttotal = lasttotal + Integer.parseInt(l.get(j));
				}
				Paragraph pt6E11 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tab6E.addCell(pt6E11);
			}
		}
		Paragraph pe61y = new Paragraph((""), fontTableHeading);
		pe61y.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe61y);
		Paragraph pe62y = new Paragraph((""), fontTableHeading);
		pe62y.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe62y);
		Paragraph pe63y = new Paragraph((""), fontTableHeading);
		pe63y.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe63y);
		Paragraph pe64y = new Paragraph((""), fontTableHeading);
		pe64y.setAlignment(Element.ALIGN_CENTER);
		tab6E.addCell(pe64y);
		Paragraph pt6E11 = new Paragraph(String.valueOf(lasttotal), fontTableHeadingNonBoldData);
		tab6E.addCell(pt6E11);

		ArrayList<ArrayList<String>> T6E2List = (ArrayList<ArrayList<String>>) table_6E2;
		PdfPTable tab6E2 = new PdfPTable(5);
		tab6E2.setWidthPercentage(100);
		tab6E2.setWidths(new int[] { 15, 50, 20, 14, 15 });
		tab6E2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tab6E2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPTable tableref3 = new PdfPTable(1);
		tableref3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableref3.setWidthPercentage(100);
		tableref3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

		Paragraph p26E2 = new Paragraph(cars.get(0).get("course_code").toString(), fontTableHeading);
		tableref3.addCell(p26E2);
		Paragraph p26E3 = new Paragraph("PAPER-II", fontTableHeading);
		tableref3.addCell(p26E3);
		Paragraph p26E4 = new Paragraph("Time: 3 Hours               Maximum Marks: 100", fontTableHeadingNonBoldData);
		tableref3.addCell(p26E4);
		Paragraph p26E5 = new Paragraph("INSTRUCTIONS: All questions compulsory", fontTableHeadingNonBoldData);
		tableref3.addCell(p26E5);
		Paragraph p26E6 = new Paragraph("TOTAL MARKS : 100 ", fontTableHeadingNonBoldData);
		tableref3.addCell(p26E6);

		Paragraph p2e61 = new Paragraph();
		Paragraph p2e62 = new Paragraph();
		Paragraph p2e63 = new Paragraph();
		Paragraph p2e64 = new Paragraph();
		Paragraph p2e65 = new Paragraph();

		p2e61 = new Paragraph((""), fontTableHeading);
		p2e61.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(p2e61);
		p2e62 = new Paragraph((""), fontTableHeading);
		p2e62.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(p2e62);
		p2e63 = new Paragraph(("Number of\n" + "Questions"), fontTableHeading);
		p2e63.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(p2e63);
		p2e64 = new Paragraph(("Marks per\n" + "question"), fontTableHeading);
		p2e64.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(p2e64);
		p2e65 = new Paragraph(("Total\n" + "Marks "), fontTableHeading);
		p2e65.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(p2e65);

		int lasttotal1 = 0;

		for (int i = 0; i < T6E2List.size(); i++) {
			List<String> l = T6E2List.get(i);
			for (int j = 0; j < l.size(); j++) {
				if (j == 4) {
					lasttotal1 = lasttotal1 + Integer.parseInt(l.get(j));
				}
				Paragraph pt6E112 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				tab6E2.addCell(pt6E112);
			}
		}
		Paragraph pe61x = new Paragraph((""), fontTableHeading);
		pe61x.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(pe61x);
		Paragraph pe62x = new Paragraph((""), fontTableHeading);
		pe62x.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(pe62x);
		Paragraph pe63x = new Paragraph((""), fontTableHeading);
		pe63x.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(pe63x);
		Paragraph pe64x = new Paragraph((""), fontTableHeading);
		pe64x.setAlignment(Element.ALIGN_CENTER);
		tab6E2.addCell(pe64x);
		Paragraph pt6E112 = new Paragraph(String.valueOf(lasttotal1), fontTableHeadingNonBoldData);
		tab6E2.addCell(pt6E112);

		// end of Table 6E-Paper Layout=========================================================

		// table 6FI-PaperI===========================================
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
		Paragraph p6F2 = new Paragraph("Paper I", fontTableHeading);
		Paragraph p6F3 = new Paragraph(
				"D\n" + "Type of Questions\n" + "“Yes” can be asked.\n" + "“No” should not be asked.",
				fontTableHeading);

		Paragraph p6F4 = new Paragraph("A\n" + "List of Topics", fontTableHeading);
		Paragraph p6F5 = new Paragraph("B\n" + "Term", fontTableHeading);
		Paragraph p6F6 = new Paragraph("C\n" + "Marks", fontTableHeading);
		Paragraph p6F7 = new Paragraph("MCQ\n" + "(1 Mark)", fontTableHeading);
		Paragraph p6F8 = new Paragraph("SAQ\n" + "(5 Mark)", fontTableHeading);
		Paragraph p6F9 = new Paragraph("LAQ\n" + "(10 Mark)", fontTableHeading);

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

		Paragraph sixftb1px = new Paragraph();

		ArrayList<ArrayList<String>> sixf1 = (ArrayList<ArrayList<String>>) Table6F_IDistribution_of_Theory_Exam_List;
		String yesNo = "";
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

		// end 6FI- PaperII==============================================

		// table 6FI- PaperII===start===========================================
		PdfPTable table6FII = new PdfPTable(7);
		Paragraph p99;
		table6FII.setWidthPercentage(100);
		table6FII.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6FII.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		Paragraph p6FI1 = new Paragraph(" ", fontTableHeading);
		Paragraph p6FI2 = new Paragraph("Paper II", fontTableHeading);
		Paragraph p6FI3 = new Paragraph(
				"D\n" + "Type of Questions\n" + "“Yes” can be asked.\n" + "“No” should not be asked.",
				fontTableHeading);

		Paragraph p6FI4 = new Paragraph("A\n" + "List of Topics", fontTableHeading);
		Paragraph p6FI5 = new Paragraph("B\n" + "Term", fontTableHeading);
		Paragraph p6FI6 = new Paragraph("C\n" + "Marks", fontTableHeading);
		Paragraph p6FI7 = new Paragraph("MCQ\n" + "(1 Mark)", fontTableHeading);
		Paragraph p6FI8 = new Paragraph("SAQ\n" + "(5 Mark)", fontTableHeading);
		Paragraph p6FI9 = new Paragraph("LAQ\n" + "(10 Mark)", fontTableHeading);

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
		Paragraph sixftbII = new Paragraph();

		ArrayList<ArrayList<String>> sixfII = (ArrayList<ArrayList<String>>) Table6F_IIDistribution_of_Theory_Exam_List;
		String yesNoII = "";
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

		// 6 G Question paper Blue print for AyU-RS====================================

		PdfPTable table6G = new PdfPTable(1);
		table6G.setWidthPercentage(100);
		table6G.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6G = new Chunk("6 G Question paper Blue print ", fontTableHeading);
		table6G.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6g = new Phrase(underline6G);

		Paragraph cell6G = new Paragraph(t6g);
		cell6G.setAlignment(Element.ALIGN_CENTER);

		table6G.addCell(t6g);

		PdfPTable table6GQP = new PdfPTable(1);
		table6GQP.setWidthPercentage(100);
		table6GQP.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6GQP.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Paragraph pt6g = new Paragraph();
		Paragraph pt6g1 = new Paragraph();
		Paragraph pt6g2 = new Paragraph();
		Paragraph pt6g3 = new Paragraph();
		Paragraph pt6g4 = new Paragraph();
		Paragraph pt6g5 = new Paragraph();
		Paragraph pt6g6 = new Paragraph();
		Paragraph pt6g7 = new Paragraph();
		Paragraph pt6g8 = new Paragraph();
		Paragraph pt6g9 = new Paragraph();
		Paragraph pt6g10 = new Paragraph();
		Paragraph pt6g11 = new Paragraph();
		Paragraph pt6g12 = new Paragraph();
		Paragraph pt6g13 = new Paragraph();
		Paragraph pt6g14 = new Paragraph();
		Paragraph pt6g15 = new Paragraph();
		Paragraph pt6g16 = new Paragraph();
		Paragraph pt6g17 = new Paragraph();
		Paragraph pt6g18 = new Paragraph();
		Paragraph pt6g19 = new Paragraph();
		Paragraph pt6g20 = new Paragraph();
		Paragraph pt6g21 = new Paragraph();
		Paragraph pt6g22 = new Paragraph();
		Paragraph pt6g23 = new Paragraph();
		Paragraph pt6g25 = new Paragraph();
		Paragraph pt6g26 = new Paragraph();

		PdfPTable table6gqp = new PdfPTable(3);
		table6gqp.setWidths(new int[] { 3, 13, 12 });
		table6gqp.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable table6gqp1 = new PdfPTable(3);
		table6gqp1.setWidths(new int[] { 3, 13, 12 });
		table6gqp1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		pt6g21 = new Paragraph("PAPER I", fontTableHeading);
		pt6g21.setAlignment(Element.ALIGN_CENTER);

		pt6g22 = new Paragraph("PAPER II", fontTableHeading);
		pt6g22.setAlignment(Element.ALIGN_CENTER);

		pt6g = new Paragraph(TH6G.get(0), fontTableHeading);
		pt6g.setAlignment(Element.ALIGN_CENTER);

		pt6g1 = new Paragraph(TH6G.get(1), fontTableHeading);
		pt6g1.setAlignment(Element.ALIGN_CENTER);

		pt6g2 = new Paragraph(TH6G.get(2), fontTableHeading);
		pt6g2.setAlignment(Element.ALIGN_CENTER);

		pt6g3 = new Paragraph(TH6G.get(3), fontTableHeadingNonBoldData);
		pt6g3.setAlignment(Element.ALIGN_CENTER);

		pt6g4 = new Paragraph(TH6G.get(4), fontTableHeadingNonBoldData);
		pt6g4.setAlignment(Element.ALIGN_LEFT);

		pt6g6 = new Paragraph(TH6G.get(5), fontTableHeadingNonBoldData);
		pt6g6.setAlignment(Element.ALIGN_LEFT);

		pt6g7 = new Paragraph(TH6G.get(6), fontTableHeadingNonBoldData);
		pt6g7.setAlignment(Element.ALIGN_LEFT);

		pt6g9 = new Paragraph(TH6G.get(7), fontTableHeadingNonBoldData);
		pt6g9.setAlignment(Element.ALIGN_LEFT);

		pt6g10 = new Paragraph(TH6G.get(8), fontTableHeadingNonBoldData);
		pt6g10.setAlignment(Element.ALIGN_LEFT);

		pt6g12 = new Paragraph(TH6G.get(9), fontTableHeadingNonBoldData);
		pt6g12.setAlignment(Element.ALIGN_LEFT);

		pt6g13 = new Paragraph(TH6G.get(10), fontTableHeadingNonBoldData);
		pt6g13.setAlignment(Element.ALIGN_LEFT);

		pt6g15 = new Paragraph(TH6G.get(11), fontTableHeadingNonBoldData);
		pt6g15.setAlignment(Element.ALIGN_LEFT);

		pt6g16 = new Paragraph(TH6G.get(12), fontTableHeadingNonBoldData);
		pt6g16.setAlignment(Element.ALIGN_LEFT);

		pt6g18 = new Paragraph(TH6G.get(13), fontTableHeadingNonBoldData);
		pt6g18.setAlignment(Element.ALIGN_LEFT);

		pt6g19 = new Paragraph(TH6G.get(14), fontTableHeadingNonBoldData);
		pt6g19.setAlignment(Element.ALIGN_LEFT);

		pt6g23 = new Paragraph(TH6G.get(15), fontTableHeading);
		pt6g23.setAlignment(Element.ALIGN_LEFT);

		pt6g25 = new Paragraph(TH6G.get(16), fontTableHeading);
		pt6g25.setAlignment(Element.ALIGN_LEFT);
		
		pt6g26 = new Paragraph(TH6G.get(17), fontTableHeading);
		pt6g26.setAlignment(Element.ALIGN_LEFT);

		String topics = "";
		for (int e = 0; e < table6g1_list.size(); e++) {
			List<String> l = table6g1_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}
		pt6g5 = new Paragraph(topics, fontTableHeadingNonBoldData);

		String topics1 = "";
		for (int e = 0; e < table6g2_list.size(); e++) {
			List<String> l = table6g2_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics1 += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}

		pt6g8 = new Paragraph(topics1, fontTableHeadingNonBoldData);

		String topics2 = "";
		for (int e = 0; e < table6g3_list.size(); e++) {
			List<String> l = table6g3_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics2 += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}

		pt6g11 = new Paragraph(topics2, fontTableHeadingNonBoldData);

		String topics3 = "";
		for (int e = 0; e < table6g4_list.size(); e++) {
			List<String> l = table6g4_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics3 += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}

		pt6g14 = new Paragraph(topics3, fontTableHeadingNonBoldData);

		String topics4 = "";
		for (int e = 0; e < table6g5_list.size(); e++) {
			List<String> l = table6g5_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics4 += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}

		pt6g17 = new Paragraph(topics4, fontTableHeadingNonBoldData);

		String topics5 = "";
		for (int e = 0; e < table6g6_list.size(); e++) {
			List<String> l = table6g6_list.get(e);
			for (int j = 0; j < l.size(); j++) {
				if (j == 0) {
					topics5 += "\n" + l.get(j);
				}
				if (j == 1) {
				}
			}
		}

		pt6g20 = new Paragraph(topics5, fontTableHeadingNonBoldData);

		table6gqp.getDefaultCell().setColspan(3);
		table6gqp.addCell(pt6g21);
		table6gqp.getDefaultCell().setColspan(1);
		table6gqp.addCell(pt6g);
		table6gqp.addCell(pt6g1);
		table6gqp.addCell(pt6g2);
		table6gqp.addCell(pt6g3);
		table6gqp.addCell(pt6g4);
		table6gqp.addCell(pt6g5);
		table6gqp.addCell(pt6g6);
		table6gqp.addCell(pt6g7);
		table6gqp.addCell(pt6g8);
		table6gqp.addCell(pt6g9);
		table6gqp.addCell(pt6g10);
		table6gqp.addCell(pt6g11);

		table6gqp1.getDefaultCell().setColspan(3);
		table6gqp1.addCell(pt6g22);
		table6gqp1.getDefaultCell().setColspan(1);
		table6gqp1.addCell(pt6g23);
		table6gqp1.addCell(pt6g25);
		table6gqp1.addCell(pt6g26);
		table6gqp1.addCell(pt6g12);
		table6gqp1.addCell(pt6g13);
		table6gqp1.addCell(pt6g14);
		table6gqp1.addCell(pt6g15);
		table6gqp1.addCell(pt6g16);
		table6gqp1.addCell(pt6g17);
		table6gqp1.addCell(pt6g18);
		table6gqp1.addCell(pt6g19);
		table6gqp1.addCell(pt6g20);

		table6GQP.addCell(table6gqp);
		if(!course_id.equals("51")) {
			table6GQP.addCell(table6gqp1);
		}
		

		// end of 6 G Question paper Blue print for

//-Table 6 H1 Distribution of Practical Exam ========================================

		ArrayList<ArrayList<String>> table_6H1List = (ArrayList<ArrayList<String>>) table_6H1;
		PdfPTable table6H1link = new PdfPTable(1);
		table6H1link.setWidthPercentage(100);
		table6H1link.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table6H1link.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		PdfPTable table6H1 = new PdfPTable(1);
		table6H1.setWidthPercentage(100);
		table6H1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline6h1 = new Chunk("6 H - I - Distribution of Practical Exam", fontTableHeading);
		table6H1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t6h = new Phrase(underline6h1);

		Paragraph table6H2 = new Paragraph();

		table6H2 = new Paragraph(("Practical " + examination_list.get(0).get("practical_marks").toString()
				+ " Marks + (Viva " + examination_list.get(0).get("viva_marks").toString() + " + IA "
				+ examination_list.get(0).get("ia_marks").toString() + ") Marks"));
		table6H2.setAlignment(Element.ALIGN_LEFT);

		Paragraph cell6h = new Paragraph(t6h);
		cell6h.setAlignment(Element.ALIGN_LEFT);

		table6H1.addCell(t6h);

		Paragraph pt6h1 = new Paragraph();
		Paragraph pt6h2 = new Paragraph();
		Paragraph pt6h3 = new Paragraph();
		Paragraph pt6h4 = new Paragraph();
		Paragraph pt6h5 = new Paragraph();
		Paragraph pt6h6 = new Paragraph();
		Paragraph pt6h7 = new Paragraph();
		Paragraph pt6h8 = new Paragraph();
		Paragraph pt6h9 = new Paragraph();

		PdfPTable table6H1data = new PdfPTable(3);
		table6H1data.setWidths(new int[] { 15, 150, 40 });
		table6H1data.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		pt6h1 = new Paragraph(TH6H1.get(0), fontTableHeading);
		pt6h1.setAlignment(Element.ALIGN_CENTER);
		table6H1data.addCell(pt6h1);
		pt6h3 = new Paragraph(TH6H1.get(1), fontTableHeading);
		pt6h3.setAlignment(Element.ALIGN_CENTER);
		table6H1data.addCell(pt6h3);
		pt6h4 = new Paragraph(TH6H1.get(2), fontTableHeading);
		pt6h4.setAlignment(Element.ALIGN_CENTER);
		table6H1data.addCell(pt6h4);

		int sub_total6h = 0;
		int grand_total6h = 0;
		for (int i = 0; i < table_6H1List.size(); i++) {
			List<String> l = table_6H1List.get(i);
			for (int j = 0; j < l.size(); j++) {
				if (j == 2) {
					sub_total6h = sub_total6h + Integer.parseInt(l.get(j));
				}
				Paragraph pt6DP = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table6H1data.addCell(pt6DP);
			}
		}

		Paragraph pt6611 = new Paragraph("", fontTableHeadingNonBoldData);
		table6H1data.addCell(pt6611);
		Paragraph pt6621 = new Paragraph("Total marks", fontTableHeading);
		table6H1data.addCell(pt6621);
		Paragraph pt6631 = new Paragraph(String.valueOf(sub_total6h), fontTableHeading);
		table6H1data.addCell(pt6631);

		table6H1link.addCell(table6H1);
		table6H1link.addCell(table6H2);
		table6H1link.addCell(table6H1data);

		// end of -Table 6 H1 Distribution of Practical Exam=========================================================

		// Reference and Resourses=================Riddhi=======================

		PdfPTable tableref7 = new PdfPTable(1);
		tableref7.setWidthPercentage(100);
		tableref7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underliners = new Chunk("7. Reference and Resourses", fontTableHeading);
		tableref7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase ref7 = new Phrase(underliners);

		Paragraph cellr7 = new Paragraph(ref7);
		cellr7.setAlignment(Element.ALIGN_CENTER);

		Font fontTableHeadingNonBoldDataref7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

		Phrase ref = new Phrase();
		String refrences = "";
		int ser = 1;
		ArrayList<ArrayList<String>> resList = (ArrayList<ArrayList<String>>) reference_resourses_list;
		for (int i = 0; i < resList.size(); i++) {
			refrences += "\n" + " " + ser + ". " + resList.get(i).get(0);
			ser++;
		}
		ref = new Phrase(refrences, fontTableHeadingNonBoldDataref7);

		// End Reference and Resourses=================Riddhi=======================

		// footer============================

		Chunk underlinef = new Chunk("RESTRICTED", fontTableHeading);

		underlinef.setUnderline(0.1f, -2f);

		PdfPTable tablefoot = new PdfPTable(3);

		tablefoot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		tablefoot.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase pf = new Phrase(underlinef);

		String foot = cars.get(0).get("course_name").toString() + "," + cars.get(0).get("professional").toString() + " "
				+ cars.get(0).get("degree_name").toString() + " (" + cars.get(0).get("system_name").toString() + ")";
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

		Phrase p1 = new Phrase();
		document.add(TestTable);
		document.add(tableleft3);
		p1 = new Phrase("\n");
		document.add(tableref);
		p1 = new Phrase("\n");
		document.add(p1);
		document.add(tab132le);
		document.add(tablesadasref);
		document.add(tablesadasref45);
		document.add(p1);

//------------------------------------------------------------------------------------------------------------------------------------------------------

		document.add(tab132ls);

//------------------------------------------------------------------------------------------------------------------------------------------------------			
		document.newPage(); 
		document.add(tablexx);
		document.add(tablex);
		document.add(p1);
		document.add(table2MHeader);
		document.add(p1);
		document.add(table2);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(table3MHeader);
		document.add(table3);

		document.add(p1);
		
		if(PracList.size() != 0) {
			document.add(tablepla);
			document.add(tablepractical);
		}
		document.add(p1);
		if(ta4List.size() != 0) {
		document.add(table4MHeader);
		document.add(tablepraclo4);
		}
		document.add(p1);
		document.add(table5nlaclink);
		document.add(p1);
		document.add(tablenop);
		document.add(table6A);
		document.add(p1);
		document.add(table6b1);
		document.add(table6bsa);
		document.add(table6b);
		document.add(p1);

		if (practical_IA.contains("30")) {

			document.add(tableref1);
			document.add(tab6C);
			document.add(p1);
		}

		if (practical_IA.contains("20")) {

			document.add(tableref62);
			document.add(tab6C2);
			document.add(p1);
		}

		if (practical_IA.contains("15")) {

			document.add(tableref63);
			document.add(tab6C3);
			document.add(p1);
		}

		if (practical_IA.contains("40")) {

			document.add(tableref33);
			document.add(tabl6C);
			document.add(p1);
		}
		document.add(table6Dnlaclink);
		document.add(p1);
		document.add(p1);
		document.add(tableref2);
		document.add(tab6E);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		
		if(T6E2List.size() != 0) {
		document.add(tableref3);
		document.add(tab6E2);
		}
		
		document.add(p1);
		document.add(table6FMHeader);
		document.add(table6FI);
		document.add(p1);
		if(sixfII.size() != 0) {
		document.add(table6FII);
		}
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(table6G);
		document.add(table6GQP);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(p1);
		document.add(table6H1link);
		document.add(p1);
		document.add(ref7);
		document.add(ref);
		document.add(tablefoot);
		super.buildPdfMetadata(model, document, request);
	}
}

class PageNumeration extends PdfPageEventHelper {
	PdfTemplate total;
	PdfTemplate total1;

	public PageNumeration(PdfWriter writer) {
		try {
			total = writer.getDirectContent().createTemplate(30, 16);
			total1 = writer.getDirectContent().createTemplate(30, 16);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(1);

		try {

			table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
			table.setLockedWidth(true);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			String pagNum = "";

			int page = 0;
			if (Integer.parseInt(String.valueOf(writer.getPageNumber())) > 2) {
				pagNum = String.valueOf(page);
			} else {
				pagNum = "";
			}

			PdfPCell cell1 = new PdfPCell(new Paragraph(pagNum));
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
			table.writeSelectedRows(0, -1, document.leftMargin() + 360, document.topMargin() - 3,
					writer.getDirectContent());
			if (Integer.parseInt(String.valueOf(writer.getPageNumber())) > 2) {
				page++;
			}
			document.add(table);
		} catch (Exception de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2,
				2, 0);
		char[] totalRecords = null;
		ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new Phrase(String.valueOf(totalRecords)), 2, 2, 0);

	}
}