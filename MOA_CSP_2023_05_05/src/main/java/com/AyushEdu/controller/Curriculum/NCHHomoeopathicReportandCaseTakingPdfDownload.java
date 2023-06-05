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

public class NCHHomoeopathicReportandCaseTakingPdfDownload extends AbstractPdfView {
	
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
	List<String> TH1;
	List<ArrayList<String>> List_of_Topic_listHOM;
	List<ArrayList<String>> Teaching_Method_list;
	List<String> TH2loc;
	List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list;
	List<String> TH2;
	List<ArrayList<String>> list_of_practical;
	List<ArrayList<String>> Reference_Resourses_list;
	
	public NCHHomoeopathicReportandCaseTakingPdfDownload(String Type,String Heading, String role,
			List<Map<String, Object>> sysdegprofcorsnamecode,
			List<ArrayList<String>> Program_Outcomes_list,
			List<ArrayList<String>> Course_Outcomes_list,
			List<String> TH,
			List<ArrayList<String>> Teaching_hour,
			List<String> TH1,
			List<ArrayList<String>> List_of_Topic_listHOM,
			List<ArrayList<String>> Teaching_Method_list,
			List<String> TH2loc,List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list,
			List<String> TH2,List<ArrayList<String>> list_of_practical,
			List<ArrayList<String>> Reference_Resourses_list) {
		
		this.Type = Type;
		this.Heading = Heading;
		this.role = role;
		this.sysdegprofcorsnamecode = sysdegprofcorsnamecode;
		this.Program_Outcomes_list = Program_Outcomes_list;
		this.Course_Outcomes_list = Course_Outcomes_list;
		this.TH = TH;
		this.Teaching_hour = Teaching_hour;
		this.TH1 = TH1;
		this.List_of_Topic_listHOM = List_of_Topic_listHOM;
		this.Teaching_Method_list = Teaching_Method_list;
		this.TH2loc = TH2loc;
		this.table2_Learning_Objectives_of_Course_HomUG_list = table2_Learning_Objectives_of_Course_HomUG_list;
		this.TH2 = TH2;
		this.list_of_practical = list_of_practical;
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
//		document.setPageCount();
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

// For System Degree Prof Course Name and Code =========================================

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

// For System Degree Prof Course Name and Code ============

// Strt Program_Outcomes========================================

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

//End Program_Outcomes======================================================
		
// Strt COURSE OUTCOMES======================================================

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

// End COURSE OUTCOMES===============================================================
				
//==============Start================== Teaching hour=================================

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
//==============END================== Teaching hour
				
//==============Start================== List of Topic

				PdfPTable tableHOM = new PdfPTable(1);
				tableHOM.setWidthPercentage(100);
				tableHOM.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline5 = new Chunk("COURSE CONTENT (Hom - UG-R-I)", fontTableHeading);
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

				for (int i = 0; i < aListho.size(); i++) {
					List<String> l = aListho.get(i);
						String stopics = l.get(2);
						String sta[] = stopics.split("<==>");
						for(int st=0;st<sta.length;st++) {
							if(st == 0) {
								Paragraph kv1 = new Paragraph(l.get(1), fontTableHeading);
								tableho.getDefaultCell().setColspan(3);
								tableho.addCell(kv1);
								tableho.getDefaultCell().setColspan(1);
								
								Paragraph kv0 = new Paragraph(String.valueOf(st+1), fontTableHeadingNonBoldData);
								tableho.addCell(kv0);
								
								Paragraph kv2 = new Paragraph(sta[st], fontTableHeadingNonBoldData);
								tableho.addCell(kv2);
								
								Paragraph kv3 = new Paragraph(l.get(3), fontTableHeading);
								tableho.getDefaultCell().setRowspan(sta.length);
								tableho.addCell(kv3);
								tableho.getDefaultCell().setRowspan(1);
								
							}else {
								Paragraph kv0 = new Paragraph(String.valueOf(st+1), fontTableHeadingNonBoldData);
								tableho.addCell(kv0);
								
								Paragraph kv2 = new Paragraph(sta[st], fontTableHeadingNonBoldData);
								tableho.addCell(kv2);
							}
						}
				}

//==============END================== List of Topic==================================
				
// Strt TEACHING LEARNING METHODS========================================
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

// End TEACHING LEARNING METHODS=======================================
				
				
// Table 2-Learning Objectives (Theory) of Course HomUG-OM-I===============
				
				ArrayList<ArrayList<String>> t2HOMList = (ArrayList<ArrayList<String>>) table2_Learning_Objectives_of_Course_HomUG_list;
				PdfPTable table2HOMHeader = new PdfPTable(1);
				table2HOMHeader.setWidthPercentage(100);
				table2HOMHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underlineHOM = new Chunk("Content Mapping (Theory) of Course Hom UG-R-I", fontTableHeading);
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
				
				
//==============Start================== List Of Practical=========================================


				PdfPTable tablex1 = new PdfPTable(1);
				tablex1.setWidthPercentage(100);
				tablex1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Chunk underline = new Chunk("List of Practical Topics", fontTableHeading);
				tablex1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Phrase t6 = new Phrase(underline);

				Paragraph cell6 = new Paragraph(t6);
				cell6.setAlignment(Element.ALIGN_CENTER);

				tablex1.addCell(t6);

				ArrayList<ArrayList<String>> aList = (ArrayList<ArrayList<String>>) list_of_practical;

				PdfPTable tablehom = new PdfPTable(1);
				tablehom.setWidthPercentage(100);
				tablehom.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				tablehom.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				Font fontTableHeadingNonBoldDatax1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
				Paragraph pyh1 = new Paragraph();
				Paragraph pyh2 = new Paragraph();
				Paragraph pyh3 = new Paragraph();

				PdfPTable tablehom32 = new PdfPTable(4);
				tablehom32.setWidthPercentage(100);
				tablehom32.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

				for (int h = 0; h < TH2.size(); h++) {
					pyh1 = new Paragraph(TH2.get(h), fontTableHeading);
					pyh1.setAlignment(Element.ALIGN_CENTER);
					tablehom32.addCell(pyh1);
				}
				tablehom32.setHeaderRows(1); // table first row will be repeated in all pages

				for (int i = 0; i < aList.size(); i++) {
					List<String> l = aList.get(i);
					for (int j = 0; j < l.size(); j++) {
						pyh1 = new Paragraph(l.get(j), fontTableHeadingNonBoldData);
						tablehom32.addCell(pyh1);

					}
				}
//==============END================== List Of Practical==========================================
				
// Start Reference and Resourses===============================================================

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

// End Reference and Resourses========================================

			
// START footer============================

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
				
//				Start Teaching hour
				document.add(doubleSN);
				document.add(t1);
				table.addCell(tablexx);
				table.addCell(tablex);
				document.add(table32);
//				end of Teaching hour
				
			//  Start List of Topic
				document.add(singleSN);
				document.add(tableHOM);
				document.add(singleSN);
				document.add(tableho);
			//  end List of Topic
							
				// Start TEACHING LEARNING METHODS
				document.add(singleSN);
				document.add(TEC1);
				document.add(Tec_Learning);
		//  end	TEACHING LEARNING METHODS	
				
				
		// Start Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
				document.newPage(); 
				document.add(singleSN);
				document.add(table2HOMHeader);
				document.add(singleSN);
				document.add(table2HOM);
		// end Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
				
//				Start List of practical
				document.add(doubleSN);
				document.add(t6);
				table.addCell(tablex1);
				table.addCell(tablehom);
				document.add(tablehom32);
				document.add(singleSN);
//				end of List of practical

//				Start Reference and Resourses
				document.add(singleSN);
				document.add(resourses7);
				document.add(singleSN);
				document.add(Resour);
//				End Reference and Resourses	
		super.buildPdfMetadata(model, document, request);
	}
}
