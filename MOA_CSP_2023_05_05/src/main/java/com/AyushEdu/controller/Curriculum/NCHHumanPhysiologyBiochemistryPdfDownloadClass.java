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

public class NCHHumanPhysiologyBiochemistryPdfDownloadClass extends AbstractPdfView {
	String Type = "";
	List<Map<String, Object>> sysdegprofcorsnamecode;
	List<String> TH;
	List<ArrayList<String>> Program_Outcomes_list;
	List<ArrayList<String>> Course_Outcomes_list;
	List<ArrayList<String>> Teaching_hour;
	List<String> TH2;
	List<ArrayList<String>> TheoryWiseTeachingHoursDistributionH;
	List<String> THP;
	List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH;
	List<String> THPC;
	List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH1;
	List<ArrayList<String>> Teaching_Method_list;
	List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list;
	List<String> TH2loc;
	List<ArrayList<String>> table4_Practical_Learning_Objectives_list;
	List<String> TH4loc;
	List<ArrayList<String>> TablePhysio_IDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> TablePhysio_IIDistribution_of_Theory_Exam_List;
	List<ArrayList<String>> Ptable61_list;
	List<ArrayList<String>> Ptable62_list;
	List<ArrayList<String>> Ptable63_list;
	List<ArrayList<String>> Ptable64_list;
	List<ArrayList<String>> Ptable65_list;
	List<ArrayList<String>> Ptable66_list;
	List<String> THQ6;
	List<ArrayList<String>> Physio_Distri_Pract_Exam;
	List<ArrayList<String>> table6d_term1list;
	List<ArrayList<String>> table6d_term2list;
	List<ArrayList<String>> table6d_term3list;
	List<ArrayList<String>> Physio_Reference_Resourses_listA;

	public NCHHumanPhysiologyBiochemistryPdfDownloadClass(List<Map<String, Object>> sysdegprofcorsnamecode,List<ArrayList<String>> Program_Outcomes_list,
			List<ArrayList<String>> Course_Outcomes_list, List<String> TH, List<ArrayList<String>> Teaching_hour,
			List<String> TH2,List<ArrayList<String>> TheoryWiseTeachingHoursDistributionH,
			List<String> THP,List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH,
			List<String> THPC,List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH1,
			List<ArrayList<String>> Teaching_Method_list,List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list,
			List<String> TH2loc,List<ArrayList<String>> table4_Practical_Learning_Objectives_list,
			List<String> TH4loc,List<ArrayList<String>> TablePhysio_IDistribution_of_Theory_Exam_List,List<ArrayList<String>> TablePhysio_IIDistribution_of_Theory_Exam_List
			,List<ArrayList<String>> Ptable61_list,List<ArrayList<String>> Ptable62_list,List<ArrayList<String>> Ptable63_list,List<ArrayList<String>> Ptable64_list,
	    	List<ArrayList<String>> Ptable65_list,List<ArrayList<String>> Ptable66_list,List<String> THQ6,List<ArrayList<String>> Physio_Distri_Pract_Exam,List<ArrayList<String>> table6d_term1list, List<ArrayList<String>> table6d_term2list,
			List<ArrayList<String>> table6d_term3list,List<ArrayList<String>> Physio_Reference_Resourses_listA) {

		this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
		this.Program_Outcomes_list = Program_Outcomes_list;
		this.Course_Outcomes_list = Course_Outcomes_list;
		this.TH = TH;
		this.Teaching_hour = Teaching_hour;
		this.TH2 = TH2;
		this.TheoryWiseTeachingHoursDistributionH = TheoryWiseTeachingHoursDistributionH;
		this.THP = THP;
		this.getPopupNch_Practical_ChildDatalistH = getPopupNch_Practical_ChildDatalistH;
		this.THPC = THPC;
		this.getPopupNch_Practical_ChildDatalistH1 = getPopupNch_Practical_ChildDatalistH1;
		this.Teaching_Method_list = Teaching_Method_list;
		this.table2_Learning_Objectives_of_Course_HomUG_list = table2_Learning_Objectives_of_Course_HomUG_list;
		this.TH2loc = TH2loc;
		this.table4_Practical_Learning_Objectives_list = table4_Practical_Learning_Objectives_list;
		this.TH4loc = TH4loc;
		this.TablePhysio_IDistribution_of_Theory_Exam_List = TablePhysio_IDistribution_of_Theory_Exam_List;	
		this.TablePhysio_IIDistribution_of_Theory_Exam_List = TablePhysio_IIDistribution_of_Theory_Exam_List;	
		this.Ptable61_list = Ptable61_list;
		this.Ptable62_list = Ptable62_list;
		this.Ptable63_list = Ptable63_list;
		this.Ptable64_list = Ptable64_list;
		this.Ptable65_list = Ptable65_list;
		this.Ptable66_list = Ptable66_list;
		this.THQ6 = THQ6;	
		this.Physio_Distri_Pract_Exam = Physio_Distri_Pract_Exam;	
		this.table6d_term1list = table6d_term1list;
		this.table6d_term2list = table6d_term2list;
		this.table6d_term3list = table6d_term3list;
		this.Physio_Reference_Resourses_listA = Physio_Reference_Resourses_listA;	
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

		// For System Degree Prof Course Name and Code ============

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

// For System Degree Prof Course Name and Code ===================================
				
// Program Outcomes======================================================================

				PdfPTable table3 = new PdfPTable(1);
				table3.setWidthPercentage(100);
				table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk poheading = new Chunk("PROGRAMME OUTCOMES :- ", fontTableHeading);
				table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase po1 = new Phrase(poheading);

				Paragraph cellr7 = new Paragraph(po1);
				cellr7.setAlignment(Element.ALIGN_CENTER);

				Font fontTableHeadingNonBoldDataref7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

				Phrase polist = new Phrase();
				String pos = "";
				int ser = 1;
				ArrayList<ArrayList<String>> resList = (ArrayList<ArrayList<String>>) Program_Outcomes_list;
				for (int i = 0; i < resList.size(); i++) {
					if(i == 0) {
						pos += ser + ". " + resList.get(i).get(0);
						ser++;
					}else {
						pos += "\n\n" + " " + ser + ". " + resList.get(i).get(0);
						ser++;
					}
					
				}
				polist = new Phrase(pos, fontTableHeadingNonBoldDataref7);

// Program Outcomes=======================================================================

// COURSE OUTCOMES==============================================================================

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

// COURSE OUTCOMES==========================================================================================
		
//==============Start================== Teaching hour=====================================================

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
		table32.setWidths(new int[] { 15, 150, 50, 50 });
		table32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int h = 0; h < TH.size(); h++) {
			py = new Paragraph(TH.get(h), fontTableHeading);
			py.setAlignment(Element.ALIGN_CENTER);
			table32.addCell(py);
		}
		for (int i = 0; i < aList23.size(); i++) {
			List<String> l = aList23.get(i);
			for (int j = 0; j < l.size(); j++) {
				py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
				table32.addCell(py);

			}
		}

//==============END================== Teaching hour=================================================
		
		
// Theory Wise Teaching Hours Distribution=============================================================

		PdfPTable table2MHeader = new PdfPTable(1);
		table2MHeader.setWidthPercentage(100);
		table2MHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underline5 = new Chunk("Theory Wise Teaching Hours Distribution – 325 Hours ", fontTableHeading);
		table2MHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase t2 = new Phrase(underline5);

		Paragraph cell2 = new Paragraph(t2);
		cell2.setAlignment(Element.ALIGN_CENTER);

		table2MHeader.addCell(t2);

		ArrayList<ArrayList<String>> t2List = (ArrayList<ArrayList<String>>) TheoryWiseTeachingHoursDistributionH;

		PdfPTable table2link = new PdfPTable(1);
		table2link.setWidthPercentage(190);
		table2link.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2link.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldData2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph pt2 = new Paragraph();
		PdfPTable table2Content_Course_AyUGRS = new PdfPTable(3);
		table2Content_Course_AyUGRS.setWidthPercentage(100);
		table2Content_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < TH2.size(); h++) {
			pt2 = new Paragraph(TH2.get(h), fontTableHeading);
			pt2.setAlignment(Element.ALIGN_CENTER);
			table2Content_Course_AyUGRS.addCell(pt2);
		}


		PdfPTable table23 = new PdfPTable(3);
		table23.setWidthPercentage(100);
		table23.setWidths(new int[] { 25, 250, 50 });
		table23.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph py2y = new Paragraph();
		Paragraph py21 = new Paragraph();
		Paragraph py22 = new Paragraph();
		Paragraph py23 = new Paragraph();
		Paragraph py24 = new Paragraph();
		Paragraph py25 = new Paragraph();
		py2y = new Paragraph(TH2.get(0), fontTableHeading);
		py2y.setAlignment(Element.ALIGN_CENTER);
		table23.addCell(py2y);
		py21 = new Paragraph(TH2.get(1), fontTableHeading);
		py21.setAlignment(Element.ALIGN_CENTER);
		table23.addCell(py21);
		py22 = new Paragraph(TH2.get(2), fontTableHeading);
		py22.setAlignment(Element.ALIGN_CENTER);
		table23.addCell(py22);

		String papertype = "";
		
		table2link.setWidthPercentage(150);
		int grand_total = 0;
		int serno1 = 1;
		int total2 = 0;
		
		for (int i = 0; i < t2List.size(); i++) {
			List<String> l = t2List.get(i);
			for (int j = 0; j < l.size(); j++) {
				
				if (j == 3) {
					total2 += Integer.parseInt(l.get(j));
				}
				if (j == 1) {
					String topicsubtopic = String.valueOf(serno1);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23.addCell(py2);
					serno1++;
				}
				if (j == 2) {
					String topicsubtopic = l.get(j);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23.addCell(py2);
				}
				if (j != 1 && j != 2) {
					if (i == 0 && j == 0) {
						papertype = l.get(0);
						Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
						table23.getDefaultCell().setColspan(3);
						table23.addCell(pt3);
						table23.getDefaultCell().setColspan(1);
					}
					if (j != 0) {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table23.addCell(py2);
					}
					if (j == 0) {
						if (!papertype.equals(l.get(j))) {
							serno1=1;
							Paragraph pt6b = new Paragraph(String.valueOf(total2), fontTableHeadingNonBoldData);
							Paragraph pt6bt = new Paragraph("TOTAL", fontTableHeading);
							table23.addCell("");
							table23.addCell(pt6bt);
							table23.addCell(pt6b);
							total2=0;
							papertype = l.get(j);
							Paragraph pt3 = new Paragraph(papertype, fontTableHeading);
							table23.getDefaultCell().setColspan(3);
							table23.addCell(pt3);
							table23.getDefaultCell().setColspan(1);
						
						}
					}

				}
				if(i == (t2List.size()-1) && j == (l.size()-1)) {
					Paragraph pt6b = new Paragraph(String.valueOf(total2), fontTableHeadingNonBoldData);
					Paragraph pt6bt = new Paragraph("TOTAL", fontTableHeading);
					table23.addCell("");
					table23.addCell(pt6bt);
					table23.addCell(pt6b);
					total2=0;
				}
				
			}
		}

		table2link.addCell(table2Content_Course_AyUGRS);

// End of Theory Wise Teaching Hours Distribution==============================================================
		
		
// Practical / Clinical Physiology / OPD Wise Teaching Hours Distribution=======================================

		ArrayList<ArrayList<String>> tpList = (ArrayList<ArrayList<String>>) getPopupNch_Practical_ChildDatalistH;

		PdfPTable tablePlink = new PdfPTable(1);
		tablePlink.setWidthPercentage(190);
		tablePlink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablePlink.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDataP = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph ptp = new Paragraph();
		PdfPTable tablePContent_Course_AyUGRS = new PdfPTable(4);
		tablePContent_Course_AyUGRS.setWidthPercentage(100);
		tablePContent_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < THP.size(); h++) {
			ptp = new Paragraph(THP.get(h), fontTableHeading);
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
		pypy = new Paragraph(THP.get(0), fontTableHeading);
		pypy.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pypy);
		pyp1 = new Paragraph(THP.get(1), fontTableHeading);
		pyp1.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp1);
		pyp2 = new Paragraph(THP.get(2), fontTableHeading);
		pyp2.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp2);
		pyp3 = new Paragraph(THP.get(3), fontTableHeading);
		pyp3.setAlignment(Element.ALIGN_CENTER);
		table23P.addCell(pyp3);

		tablePlink.setWidthPercentage(150);
		
		String topictype = "";
		int grand_total1=0;
		int total = 0;
		
		int serno = 1;
		
		for (int i = 0; i < tpList.size(); i++) {
			List<String> l = tpList.get(i);
			grand_total1 += Integer.parseInt(l.get(4));
			for (int j = 0; j < l.size(); j++) {

				if (j == 1) {
					String topicsubtopic = String.valueOf(serno);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23P.addCell(py2);
					serno++;
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
				if (j == 4) {
					total += Integer.parseInt(l.get(j));
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
							serno =1;
							topictype = l.get(j);
							Paragraph pt3 = new Paragraph(topictype, fontTableHeading);
							Paragraph pt6b = new Paragraph(String.valueOf(total), fontTableHeadingNonBoldData);
							Paragraph pt6bt = new Paragraph("TOTAL", fontTableHeading);
							table23P.addCell("");
							table23P.getDefaultCell().setColspan(2);
							table23P.addCell(pt6bt);
							table23P.getDefaultCell().setColspan(1);
							table23P.addCell(pt6b);
							table23P.getDefaultCell().setColspan(4);
							table23P.addCell(pt3);
							table23P.getDefaultCell().setColspan(1);
							total = 0;
						}
					}
				}
			}
		}
		Paragraph pt6b = new Paragraph(String.valueOf(total), fontTableHeadingNonBoldData);
		Paragraph pt6bt = new Paragraph("TOTAL", fontTableHeading);
		table23P.addCell("");
		table23P.getDefaultCell().setColspan(2);
		table23P.addCell(pt6bt);
		table23P.getDefaultCell().setColspan(1);
		table23P.addCell(pt6b);
		int grand_total1_sum=grand_total1;
		
		PdfPTable tablePMHeader = new PdfPTable(1);
		tablePMHeader.setWidthPercentage(100);
		tablePMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlineP = new Chunk("Practical / Clinical Physiology / OPD Wise Teaching Hours Distribution – "+grand_total1_sum+" Hours ", fontTableHeading);
		tablePMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase tp = new Phrase(underlineP);

		Paragraph cellp = new Paragraph(tp);
		cellp.setAlignment(Element.ALIGN_CENTER);

		tablePMHeader.addCell(tp);

		tablePlink.addCell(tablePContent_Course_AyUGRS);

// End of Practical / Clinical Physiology / OPD Wise Teaching Hours Distribution===========================
		
		
// PRACTICAL & CLINICAL PHYSIOLOGY============================================================================
		
		PdfPTable tablePCMHeader = new PdfPTable(1);
		tablePCMHeader.setWidthPercentage(100);
		tablePCMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		Chunk underlinePC = new Chunk("PRACTICAL & CLINICAL PHYSIOLOGY:- ", fontTableHeading);
		tablePCMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Phrase tpc = new Phrase(underlinePC);
		Paragraph cellpc = new Paragraph(tpc);
		cellpc.setAlignment(Element.ALIGN_CENTER);
		tablePCMHeader.addCell(tpc);

		ArrayList<ArrayList<String>> tpcList = (ArrayList<ArrayList<String>>) getPopupNch_Practical_ChildDatalistH1;
		PdfPTable tablePClink = new PdfPTable(1);
		tablePClink.setWidthPercentage(190);
		tablePClink.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tablePClink.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Font fontTableHeadingNonBoldDataPC = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

		Paragraph ptpc = new Paragraph();
		PdfPTable tablePCContent_Course_AyUGRS = new PdfPTable(3);
		tablePCContent_Course_AyUGRS.setWidthPercentage(100);
		tablePCContent_Course_AyUGRS.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for (int h = 0; h < THPC.size(); h++) {
			ptpc = new Paragraph(THPC.get(h), fontTableHeading);
			ptpc.setAlignment(Element.ALIGN_CENTER);
			tablePCContent_Course_AyUGRS.addCell(ptpc);
		}

		PdfPTable table23PC = new PdfPTable(3);
		table23PC.setWidthPercentage(100);
		table23PC.setWidths(new int[] { 20, 250,80 });
		table23PC.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph pypcy = new Paragraph();
		Paragraph pypc1 = new Paragraph();
		Paragraph pypc2 = new Paragraph();
		Paragraph pypc3 = new Paragraph();
		Paragraph pypc4 = new Paragraph();
		Paragraph pypc5 = new Paragraph();
		
		pypcy = new Paragraph(THPC.get(0), fontTableHeading);
		pypcy.setAlignment(Element.ALIGN_CENTER);
		table23PC.addCell(pypcy);
		pypc1 = new Paragraph(THPC.get(1), fontTableHeading);
		pypc1.setAlignment(Element.ALIGN_CENTER);
		table23PC.addCell(pypc1);
		pypc2 = new Paragraph(THPC.get(2), fontTableHeading);
		pypc2.setAlignment(Element.ALIGN_CENTER);
		table23PC.addCell(pypc2);
		tablePClink.setWidthPercentage(150);
		
		String topictype1 = "";
		int sernoc = 1;
		
		for (int i = 0; i < tpcList.size(); i++) {
			List<String> l = tpcList.get(i);
			for (int j = 0; j < l.size()-1; j++) {
				if (j == 1) {
					String topicsubtopic = String.valueOf(sernoc);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23PC.addCell(py2);
					sernoc++;
				}

				if (j == 2) {
					String topicsubtopic = l.get(j);
					py2 = new Paragraph(topicsubtopic, fontTableHeadingNonBoldData);
					table23PC.addCell(py2);
				}
				
				if (j != 1 && j != 2) {
					if (i == 0 && j == 0) {
						topictype1 = l.get(j);
						Paragraph pt3 = new Paragraph(topictype1, fontTableHeading);
						table23PC.getDefaultCell().setColspan(3);
						table23PC.addCell(pt3);
						table23PC.getDefaultCell().setColspan(1);
					}

					if (j != 0) {
						py2 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						table23PC.addCell(py2);
					}

					if (j == 0) {
						if (!topictype1.equals(l.get(j))) {
							sernoc =1;
							topictype1 = l.get(j);
							Paragraph pt3 = new Paragraph(topictype1, fontTableHeading);
							table23PC.getDefaultCell().setColspan(3);
							table23PC.addCell(pt3);
							table23PC.getDefaultCell().setColspan(1);
						}
					}
				}
			}
		}

		tablePClink.addCell(tablePCContent_Course_AyUGRS);

// End of PRACTICAL & CLINICAL PHYSIOLOGY============================================
		
// Start TEACHING LEARNING METHODS====================================================

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

//End TEACHING LEARNING METHODS===================================================================
				
// Table 2: Learning objectives (Theory) of Course=================================================
				

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
				String topictype11 = "";
				
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

// end of Table 2: Learning objectives (Theory)========================================================================
				

// Table 2: Learning objectives (Practical) =============================================================================


			ArrayList<ArrayList<String>> t4HOMList = (ArrayList<ArrayList<String>>) table4_Practical_Learning_Objectives_list;
			PdfPTable table4HOMHeader = new PdfPTable(1);
			table4HOMHeader.setWidthPercentage(100);
			table4HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Chunk underlineP1 = new Chunk("Practical ", fontTableHeading);
			table4HOMHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Phrase t4HOM = new Phrase(underlineP1);
			
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
			
			String papertype41 = "";
			String topictype41 = "";

					for (int i = 0; i < t4HOMList.size(); i++) {
					List<String> l = t4HOMList.get(i);
					for (int j = 0; j < l.size(); j++) {
					
						if (i == 0 && j == 0) {
							papertype41 = l.get(j);
							Paragraph ptt42HOM = new Paragraph(papertype41, fontTableHeading);
							ptt42HOM.setAlignment(Element.ALIGN_CENTER);
							table4HOM.getDefaultCell().setColspan(12);
							table4HOM.addCell(ptt42HOM);
							table4HOM.getDefaultCell().setColspan(1);
							
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
							table4HOM.getDefaultCell().setColspan(12);
							table4HOM.addCell(paralos);
							table4HOM.getDefaultCell().setColspan(1);
					
						}
						if (j == 0) {
							if (!papertype41.equals(l.get(j))) {
								papertype41 = l.get(j);
								Paragraph ptt42HOM = new Paragraph(papertype41, fontTableHeading);
								ptt42HOM.setAlignment(Element.ALIGN_CENTER);
								table4HOM.getDefaultCell().setColspan(13);
								table4HOM.addCell(ptt42HOM);
								table4HOM.getDefaultCell().setColspan(1);
								
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
								table4HOM.getDefaultCell().setColspan(12);
								table4HOM.addCell(paralos);
								table4HOM.getDefaultCell().setColspan(1);
							}
						}
						if (j != 0 && j!=13) {
							Paragraph ptt42HOM = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
							table4HOM.addCell(ptt42HOM);
						}
					}
					}
                             table.addCell(table4HOM);

// end of Table 2: Learning objectives (Practical) of Course============================================

//Start table 6FI- PaperII===start==========Kavita===========Theme======================================

				PdfPTable table6FMHeader1 = new PdfPTable(1);
				table6FMHeader1.setWidthPercentage(100);
				table6FMHeader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline6F1 = new Chunk("PHYSIOLOGY THEME TABLE", fontTableHeading);
				table6FMHeader1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase t6f1 = new Phrase(underline6F1);
				
				Paragraph cell6f1 = new Paragraph(t6f1);
				cell6f1.setAlignment(Element.ALIGN_CENTER);
				
				table6FMHeader1.addCell(t6f1);
				
				PdfPTable table6FI1 = new PdfPTable(7);
				table6FI1.setWidths(new int[] { 50, 250,50,50,50,50,50 });
				Paragraph p981;
				table6FI1.setWidthPercentage(100);
				table6FI1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table6FI1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				ArrayList<ArrayList<String>> sixf11 = (ArrayList<ArrayList<String>>) TablePhysio_IDistribution_of_Theory_Exam_List;
				Paragraph p6F11 = new Paragraph("Theme*", fontTableHeading);
				Paragraph p6F21 = new Paragraph(sixf11.get(0).get(6), fontTableHeading);
				Paragraph p6F41 = new Paragraph("Topics", fontTableHeading);
				Paragraph p6F51 = new Paragraph("Term", fontTableHeading);
				Paragraph p6F61 = new Paragraph("Marks", fontTableHeading);
				Paragraph p6F71 = new Paragraph("MCQ", fontTableHeading);
				Paragraph p6F81 = new Paragraph("SAQ", fontTableHeading);
				Paragraph p6F91 = new Paragraph("LAQ", fontTableHeading);
				Paragraph sixftb1px1 = new Paragraph();
				
					table6FI1.getDefaultCell().setColspan(7);
					table6FI1.addCell(p6F21);
					table6FI1.getDefaultCell().setColspan(1);
					table6FI1.addCell(p6F11);
					table6FI1.addCell(p6F41);
					table6FI1.addCell(p6F51);
					table6FI1.addCell(p6F61);
					table6FI1.addCell(p6F71);
					table6FI1.addCell(p6F81);
					table6FI1.addCell(p6F91);
						
						String yesNoITheme12 = "";
						for (int i = 0; i < sixf11.size()-1; i++) {
							List<String> l = sixf11.get(i);
							for (int j = 0; j < l.size()-1; j++) {
								if (j == 3 || j == 4 || j == 5) {
									if (l.get(j).equals("0")) {
										yesNoITheme12 = "NO";
									}
									if (l.get(j).equals("1")) {
										yesNoITheme12 = "YES";
									}
									sixftb1px1 = new Paragraph(yesNoITheme12, fontTableHeadingNonBoldData);
									table6FI1.addCell(sixftb1px1);
								}
								else if(j == 0){
									sixftb1px1 = new Paragraph(String.valueOf(i+1), fontTableHeadingNonBoldData);
									table6FI1.addCell(sixftb1px1);
									sixftb1px1 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
									table6FI1.addCell(sixftb1px1);
								}else {
									sixftb1px1 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
									table6FI1.addCell(sixftb1px1);
								}
							}
						}
		

//end table 6FI- PaperII=================Theme=========================================================

//Start table 6FI- PaperII===start==========Kavita===========Theme============================================
		
				PdfPTable table6FMHeader11 = new PdfPTable(1);
				table6FMHeader11.setWidthPercentage(100);
				table6FMHeader11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline6F11 = new Chunk(" ", fontTableHeading);
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
				ArrayList<ArrayList<String>> sixf111 = (ArrayList<ArrayList<String>>) TablePhysio_IIDistribution_of_Theory_Exam_List;
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

//end table 6FI- PaperII=================Theme========================================================
						
					
// 6 G Question paper Blue print for ===========================Kavita======================================

						PdfPTable tableQ6 = new PdfPTable(1);
						tableQ6.setWidthPercentage(100);
						tableQ6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						Chunk underlineQ6 = new Chunk("Question paper Blue print\n"
								+ "\n"
								+ "UNIVERSITY EXAM PAPER-I – 100 MARKS\n"
								+ "\n"
								+ "MCQs – 10 Marks.\n"
								+ "SAQs – 50 Marks.\n"
								+ "FAQs – 40 Marks ", fontTableHeading);
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
						Paragraph pq612 = new Paragraph();
						Paragraph pq613 = new Paragraph();
						Paragraph pq614 = new Paragraph();
						Paragraph pq615 = new Paragraph();
						Paragraph pq616 = new Paragraph();
						Paragraph pq617 = new Paragraph();
						Paragraph pq618 = new Paragraph();
						Paragraph pq619 = new Paragraph();
						Paragraph pq620 = new Paragraph();
						Paragraph pq621 = new Paragraph();
						Paragraph pq622 = new Paragraph();
						Paragraph pq623 = new Paragraph();
						Paragraph pq625 = new Paragraph();
						Paragraph pq626 = new Paragraph();

						PdfPTable tableqp6 = new PdfPTable(3);
						tableqp6.setWidths(new int[] { 3, 13, 12 });
						tableqp6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

						PdfPTable tableqp61 = new PdfPTable(3);
						tableqp61.setWidths(new int[] { 3, 13, 12 });
						tableqp61.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

						pq621 = new Paragraph("PAPER I", fontTableHeading);
						pq621.setAlignment(Element.ALIGN_CENTER);

						pq622 = new Paragraph("PAPER II", fontTableHeading);
						pq622.setAlignment(Element.ALIGN_CENTER);

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

						pq612 = new Paragraph(THQ6.get(9), fontTableHeadingNonBoldData);
						pq612.setAlignment(Element.ALIGN_LEFT);

						pq613 = new Paragraph(THQ6.get(10), fontTableHeadingNonBoldData);
						pq613.setAlignment(Element.ALIGN_LEFT);

						pq615 = new Paragraph(THQ6.get(11), fontTableHeadingNonBoldData);
						pq615.setAlignment(Element.ALIGN_LEFT);

						pq616 = new Paragraph(THQ6.get(12), fontTableHeadingNonBoldData);
						pq616.setAlignment(Element.ALIGN_LEFT);

						pq618 = new Paragraph(THQ6.get(13), fontTableHeadingNonBoldData);
						pq618.setAlignment(Element.ALIGN_LEFT);

						pq619 = new Paragraph(THQ6.get(14), fontTableHeadingNonBoldData);
						pq619.setAlignment(Element.ALIGN_LEFT);

						pq623 = new Paragraph(THQ6.get(15), fontTableHeading);
						pq623.setAlignment(Element.ALIGN_LEFT);

						pq625 = new Paragraph(THQ6.get(16), fontTableHeading);
						pq625.setAlignment(Element.ALIGN_LEFT);
						
						pq626 = new Paragraph(THQ6.get(17), fontTableHeading);
						pq626.setAlignment(Element.ALIGN_LEFT);

						String topics = "";
						for (int e = 0; e < Ptable61_list.size(); e++) {
							List<String> l = Ptable61_list.get(e);
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
						for (int e = 0; e < Ptable62_list.size(); e++) {
							List<String> l = Ptable62_list.get(e);
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
						for (int e = 0; e < Ptable63_list.size(); e++) {
							List<String> l = Ptable63_list.get(e);
							for (int j = 0; j < l.size(); j++) {
								if (j == 0) {
									topics2 += "\n" + l.get(j);
								}
								if (j == 1) {
								}
							}
						}

						pq611 = new Paragraph(topics2, fontTableHeadingNonBoldData);

						String topics3 = "";
						for (int e = 0; e < Ptable64_list.size(); e++) {
							List<String> l = Ptable64_list.get(e);
							for (int j = 0; j < l.size(); j++) {
								if (j == 0) {
									topics3 += "\n" + l.get(j);
								}
								if (j == 1) {
								}
							}
						}

						pq614 = new Paragraph(topics3, fontTableHeadingNonBoldData);

						String topics4 = "";
						for (int e = 0; e < Ptable65_list.size(); e++) {
							List<String> l = Ptable65_list.get(e);
							for (int j = 0; j < l.size(); j++) {
								if (j == 0) {
									topics4 += "\n" + l.get(j);
								}
								if (j == 1) {
								}
							}
						}

						pq617 = new Paragraph(topics4, fontTableHeadingNonBoldData);

						String topics5 = "";
						for (int e = 0; e < Ptable66_list.size(); e++) {
							List<String> l = Ptable66_list.get(e);
							for (int j = 0; j < l.size(); j++) {
								if (j == 0) {
									topics5 += "\n" + l.get(j);
								}
								if (j == 1) {
								}
							}
						}
						pq620 = new Paragraph(topics5, fontTableHeadingNonBoldData);

						tableqp6.getDefaultCell().setColspan(3);
						tableqp6.addCell(pq621);
						tableqp6.getDefaultCell().setColspan(1);
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

						tableqp61.getDefaultCell().setColspan(3);
						tableqp61.addCell(pq622);
						tableqp61.getDefaultCell().setColspan(1);
						tableqp61.addCell(pq623);
						tableqp61.addCell(pq625);
						tableqp61.addCell(pq626);
						tableqp61.addCell(pq612);
						tableqp61.addCell(pq613);
						tableqp61.addCell(pq614);
						tableqp61.addCell(pq615);
						tableqp61.addCell(pq616);
						tableqp61.addCell(pq617);
						tableqp61.addCell(pq618);
						tableqp61.addCell(pq619);
						tableqp61.addCell(pq620);
						tableQP6.addCell(tableqp6);
						tableQP6.addCell(tableqp61);
						
//end of 6 G Question paper Blue print =====================================================================


////////// Distribution of Practical Exam====================Kavita==============================================

						ArrayList<ArrayList<String>> Physio_Distri_Pract_List = (ArrayList<ArrayList<String>>) Physio_Distri_Pract_Exam;
						PdfPTable Ptab923 = new PdfPTable(2);

						Ptab923.setWidthPercentage(100);
						Ptab923.setWidths(new int[] { 50, 20 });
						Ptab923.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
						Ptab923.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

						PdfPTable Ptable2111 = new PdfPTable(1);
						Ptable2111.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						Ptable2111.setWidthPercentage(100);
						Ptable2111.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

						Paragraph pag2 = new Paragraph("Distribution of Practical Exam", fontTableHeading);
						Ptable2111.addCell(pag2);

						int a0 = 0;
						
						for (int i = 0; i < Physio_Distri_Pract_List.size(); i++) {
							List<String> l = Physio_Distri_Pract_List.get(i);
							a0 += Integer.parseInt(Physio_Distri_Pract_List.get(i).get(1));
							for (int j = 0; j < l.size(); j++) {
								py = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
								Ptab923.addCell(py);

							}
						}
						int sum = a0;
						Paragraph pt552T = new Paragraph("Total", fontTableHeading);
						Ptab923.addCell(pt552T);
						Paragraph pt553T = new Paragraph(String.valueOf(sum), fontTableHeading);
						Ptab923.addCell(pt553T);

// end of Distribution of Practical==================================================================
						//6-D================ Start Scheme of Assessment (formative)=========================

						PdfPTable table6D = new PdfPTable(1);
						table6D.setWidthPercentage(100);
						table6D.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						Chunk underline6D1 = new Chunk("For Internal assessment, Only Practical/Viva marks will be considered. Theory marks will not be counted)",
								fontTableHeading);
						table6D.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						Phrase t6D1 = new Phrase(underline6D1);

						Paragraph cell6D1 = new Paragraph(t6D1);
						cell6D1.setAlignment(Element.ALIGN_CENTER);

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
						table6Dformative.addCell("20 Marks Practical/Viva");
						table6Dformative.addCell("100 Marks Practical/ Viva");
						table6Dformative.addCell("20 Marks Practical/Viva");
						table6Dformative.addCell("100 Marks Practical/ Viva");
						table6Dformative.addCell("20 Marks Practical/Viva");
						
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
								+ "(20 Marks)"), fontTableHeadingNonBoldData);
						pg6.setAlignment(Element.ALIGN_CENTER);
						
						pg61 = new Paragraph(("PA2\n"
								+ "Practical/Viva\n"
								+ "(20 Marks)"), fontTableHeadingNonBoldData);
						pg61.setAlignment(Element.ALIGN_CENTER);
						
						pg62 = new Paragraph(("PA3\n"
								+ "Practical/Viva\n"
								+ "(20 Marks)"), fontTableHeadingNonBoldData);
						pg62.setAlignment(Element.ALIGN_CENTER);
						
						pg63 = new Paragraph(("Periodical Assessment Average\n"
								+ "PA1+PA2+PA3/3"), fontTableHeadingNonBoldData);
						pg63.setAlignment(Element.ALIGN_CENTER);
						
						pg64 = new Paragraph(("TT1 Practical/Viva\n"
								+ "(100 Marks)"), fontTableHeadingNonBoldData);
						pg64.setAlignment(Element.ALIGN_CENTER);
						
						pg65 = new Paragraph(("TT2 Practical/Viva\n"
								+ "(100 Marks)"), fontTableHeadingNonBoldData);
						pg65.setAlignment(Element.ALIGN_CENTER);
						
						pg66 = new Paragraph(("Terminal Test Average\n"
								+ "TT1+TT2/\n"
								+ "200*20"), fontTableHeadingNonBoldData);
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
						
// Reference and Resourses====================================Kavita========================================

						PdfPTable tblPref7 = new PdfPTable(1);
						tblPref7.setWidthPercentage(100);
						tblPref7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						Chunk underlinersRef = new Chunk("Reference and Resourses :- ", fontTableHeading);
						tblPref7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						Phrase Presourses7 = new Phrase(underlinersRef);

						Paragraph cellrefre7 = new Paragraph(Presourses7);
						cellrefre7.setAlignment(Element.ALIGN_CENTER);

						Font fontTableHeadingNonBoldDataresour7 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 9, 0);

						Phrase PResour = new Phrase();
						String Resourses = "";
						int ser2 = 1;
						ArrayList<ArrayList<String>> tblresList = (ArrayList<ArrayList<String>>) Physio_Reference_Resourses_listA;
						for (int i = 0; i < tblresList.size(); i++) {
							Resourses += "\n" + " " + ser2 + ". " + tblresList.get(i).get(0);
							ser2++;
						}
						PResour = new Phrase(Resourses, fontTableHeadingNonBoldDataresour7);

//End Reference and Resourses========================================================================
						

// footer============================================================================================

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

				
// end of footer============================================================================================


		Phrase singleSN = new Phrase();
		Phrase doubleSN = new Phrase();
		singleSN = new Phrase("\n");
		doubleSN = new Phrase("\n\n");
		
		// Sys Deg Prof Course code and name
		document.add(Table1);

		// PO
		document.add(doubleSN);
		document.add(po1);
		document.add(singleSN);
		document.add(polist);

		// CO
		document.add(doubleSN);
		document.add(add1);
		document.add(singleSN);
		document.add(add_course);
		
		//Start Teaching hour
		document.add(singleSN);
		document.add(t1);
		document.add(table32);
		document.add(singleSN);
//		End of Teaching hour
		
		//Start Theory Wise Teaching Hours Distribution
		document.add(table2MHeader);
		document.add(table23);
		
		//Start Practical / Clinical Physiology / OPD Wise Teaching Hours Distribution
		document.add(singleSN);
		document.add(tablePMHeader);
		document.add(table23P);
		
		//Start PRACTICAL & CLINICAL PHYSIOLOGY
		document.add(singleSN);
		document.add(tablePCMHeader);
		document.add(table23PC);
		
		//Start TEACHING LEARNING METHODS
		document.add(singleSN);
		document.add(TEC1);
		document.add(singleSN);
		document.add(Tec_Learning);
		
		// Start of Table 2-Learning Objectives (Theory)-14march23

		document.add(table3HOMHeader);
		document.add(singleSN);
		document.add(table3HOM);
		document.add(singleSN);

		// End of Table 2-Learning Objectives (Theory) 
		
		// Start of Table 2-Learning Objectives (Practical) 
		
		document.add(table4HOMHeader);
		document.add(singleSN);
		document.add(table4HOM);

        // End of Table 2-Learning Objectives (Practical) 
		//Start PRACTICAL & CLINICAL PHYSIOLOGY
		document.add(tablePCMHeader);
		document.add(table23PC);
		
	     ////Start Distribution of Theory Exam ///////
		
		document.add(singleSN);
		document.add(table6FMHeader1);
		document.add(singleSN);
		document.add(table6FI1);
		document.add(singleSN);
		
	    ////End Distribution of Theory Exam ///////
			
		////Distribution of Theory Exam paper 2==Theme ///////

		document.add(table6FMHeader11);
		document.add(table6FI11);
		
		////Distribution of Theory Exam paper 2==Theme ///////
		
	     /////Question Blue Print ===============
		
		document.newPage();  // to add new page in pdf and add next stuff in that new page  
		document.add(singleSN);
		document.add(tableQ6);
		document.add(singleSN);
		document.add(tableQP6);
		
		/////Question Blue Print ===============

				
				
	    ////Distribution of Practical Exam ///////

	    document.add(singleSN);
		document.add(Ptable2111);
		document.add(singleSN);
		document.add(Ptab923);

	    //// Distribution of Practical Exam ///////
		
		//Start  8(D)Scheme of Assessment (formative)
				document.add(singleSN);
				document.add(table6D1);
				document.add(singleSN);
				document.add(table6Dformative);
				document.add(table6D);
				document.add(singleSN);

				//End  8(D)Scheme of Assessment (formative)
				
				//Start  8(E) Calculation of Internal Assessment
				 document.add(tableref33);
				 document.add(singleSN);
				 document.add(tabl6C);
				 document.add(tabl6C1);
				 document.add(singleSN);
				//End  8(E) Calculation of Internal Assessment
		 //strt Reference and Resourses
		document.add(singleSN);
		document.add(Presourses7);
		document.add(singleSN);
		document.add(PResour);
      //End Reference and Resourses	
	}
}
