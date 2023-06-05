
package com.AyushEdu.PDF;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class AdmitCardDownloadPdf1 extends AbstractPdfView {
	
	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String course_name = "";
	String logo_img_path = "";
	String exam_id = "";
	String count = "";
	String credit = "";
	String ayush_id = "";
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	private static final String BaseColor = null;
	

	public AdmitCardDownloadPdf1(String Type, List<String> TH, String Heading,String logo_img_path,String count,String credit) {
		this.Type = Type;
		this.TH = TH; 	
		this.Heading = Heading;
		this.username = username;
		this.course_name = course_name;
//		this.set_name = set_name;
		this.logo_img_path = logo_img_path;
		this.count = count;
		this.credit = credit;
		this.ayush_id = ayush_id;
	}



	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		 document.open();
		if (Type.equals("L")) {
			 document.setPageSize(PageSize.A4); // set document landscape
		}
	}

	
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {



		
		response.setContentType("application/pdf");

		
		
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Font  fontTableHeadingdata = FontFactory.getFont(FontFactory.TIMES, 9);
		Font fontTableHeadingSubMainHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		
		
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.setWidthPercentage(100);
		

		

		Chunk underline = new Chunk("CERTIFICATE ", fontTableHeading1);//main heading
		
		
		Chunk underline1 = new Chunk("Refers to Para 1(a) of IHQ MoD(Army)" , fontTableHeadingSubMainHead);
	
//		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 19);
//		Paragraph pr = new Paragraph(" Ministry Of Ayush ",fontTableHeading);
//		pr.setAlignment(Element.ALIGN_CENTER);
		
		Chunk glue = new Chunk(new VerticalPositionMark());
		
		Phrase ph = new Phrase(underline);
		Phrase phh2 = new Phrase(underline1);
		ph.add("\n");
		ph.add("\n");
		ph.add("\n");
		phh2.add("\n");
		phh2.add("\n");
		
		
		ph.setFont(fontTableHeading1);
		phh2.setFont(fontTableHeadingSubMainHead);
		
		
//		Paragraph cell = new Paragraph(ph);
//		cell.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph cell = new Paragraph(ph);
		cell.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph cellh2 = new Paragraph(ph);
		cellh2.setAlignment(Element.ALIGN_LEFT);
		PdfPCell cellh2h =new PdfPCell(cellh2);
		cellh2h.setBorder(Rectangle.NO_BORDER);
		cellh2h.setPaddingLeft(20f);
		
		
		PdfPTable tableheader = new PdfPTable(1);
		tableheader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableheader.setWidthPercentage(100);
		tableheader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableheader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableheader.addCell(cell);
			
		
		PdfPTable tabledata = new PdfPTable(3);//3 table 
		tabledata.setWidths(new int[] {10,15,10});
		tabledata.setWidthPercentage(100);
		tabledata.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tabledata.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabledata.getDefaultCell().setBorder(Rectangle.BOTTOM);
		tabledata.setWidthPercentage(100);
		
		
		//Paragraph a = new Paragraph("ARM/SERVICE",fontTableHeadingSubMainHead);//logo1
		
		Paragraph b = new Paragraph("NCISM,MOA",fontTableHeading1);//heading
		
		Paragraph c = new Paragraph("LAW",fontTableHeadingSubMainHead);//logo2
		
		
//		PdfPTable tableheader1 = new PdfPTable(1);
//		tableheader1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		tableheader1.setWidthPercentage(100);
//		tableheader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//		tableheader1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//		tableheader1.addCell(cell);
//		
//		Paragraph abcd = new Paragraph("MINISTRY OF AYUSH",fontTableHeading1);
		
		
		
//		 PdfPCell blank_cella;
//		 blank_cella = new PdfPCell();
//		 blank_cella.setRowspan(2);
//		 blank_cella.addElement(a);
//		 blank_cella.setHorizontalAlignment(Element.ALIGN_CENTER);
			
		
		Image logo = null;
		Image logo2 = null;
		try {
			@SuppressWarnings("deprecation")
//			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCISM.jpeg";
			String dgis_logo2 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "logo3.png";
			//logo = Image.getInstance(logo_img_path);
			logo = Image.getInstance(request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "LJ.png"); 
			logo2 = Image.getInstance(dgis_logo2);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(50);
		logo.scaleAbsoluteWidth(50);
		logo.scalePercent(37);
		
		logo2.setAlignment(Image.MIDDLE);
		logo2.scaleAbsoluteHeight(16);
		logo2.scaleAbsoluteWidth(19);
		logo2.scalePercent(19);
		

		
	Chunk chunk = new Chunk(logo, 0, 2);
	
	Chunk chunk1 = new Chunk(logo2, 0, 2);
	
			
			 tabledata.addCell(new Phrase(chunk));
			 tabledata.addCell(b);
			 tabledata.addCell(new Phrase(chunk1));
			 tabledata.addCell(c);
			 
			 Chunk underline3 = new Chunk("Course Completion Certificate" , fontTableHeadingSubMainHead);
			 underline3.setUnderline(0.1f, -2f);
			 
			 
			 Phrase phdd = new Phrase(underline3);
			 phdd.setFont(fontTableHeadingSubMainHead);
				Paragraph cell1 = new Paragraph(phdd);
				cell1.setAlignment(Element.ALIGN_CENTER);
			 
			
				
			
				
		Chunk abc = new Chunk("       	"+" It is Certified that Ayush No. "+ayush_id+"  Name: "+username+ 
				" has been examine for the Course Name:"+course_name+" "
//				+ " and SET NAME:"+" "+set_name+"  "
						+ "has obtained "+count+"% in Exam and"
				+ " get the points of the Credit is "  +credit+" ." , fontTableHeading1);
		

		

		Paragraph cellt = new Paragraph(abc);
		
		cellt.setAlignment(getViewerPreferences());			//set the paragraph 
//		cellt.setAlignment(Element.ALIGN_JUSTIFIED);

		
		
		
		
		//new 1 image-->>for gs::
		
		
		PdfPTable tablenew = new PdfPTable(1);
		tablenew.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablenew.setWidthPercentage(100);
		

	//	Chunk chunk = new Chunk();
		

		Chunk f1 = new Chunk("f ", fontTableHeading1);// heading
	

		
		PdfPTable tableheader1 = new PdfPTable(4);
		tableheader1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tableheader1.setWidthPercentage(100);
		tableheader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tableheader1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		tableheader1.addCell(cell);
	
		
		Image logo3 = null;
		
		Image logo4 = null;
		Image logo5 = null;
		Image logo6 = null;
		
		
		
		try {
			
		//	@SuppressWarnings("deprecation")
			
			if (Integer.parseInt(count)<= 25) {
				
				String dgis_logo5 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "Bronze-Medal.png";
				logo3 = Image.getInstance(dgis_logo5);
				
			}
	
			
			
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logo3.setAlignment(Image.ALIGN_CENTER);
		logo3.scaleAbsoluteHeight(25);
		logo3.scaleAbsoluteWidth(25);
		logo3.scalePercent(35);
		logo3.setAlignment(Element.ALIGN_CENTER);
		

		
		Chunk img = new Chunk(logo3, 205, 5);

		
		
		
		
		//table -- elective course
		
		
//		PdfPTable tablea1 = new PdfPTable(1);
//		tablea1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		tablea1.setWidthPercentage(100);
//		
//		Chunk a1 = new Chunk("a ", fontTableHeading1);		
		
		
//		 private void writeTableHeader(PdfPTable tableN1) {
//			        PdfPCell cell = new PdfPCell();
//			        cell.setBackgroundColor(Color.BLUE);
//			        cell.setPadding(5);
//			         
//			        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//			        font.setColor(Color.WHITE);
//			         
//			        cell.setPhrase(new Phrase("Elective Course", font));
//			         
//			        table.addCell(cell);
//			         
//			        cell.setPhrase(new Phrase("E-mail", font));
//			        table.addCell(cell);
//			         
//			        cell.setPhrase(new Phrase("Full Name", font));
//			        table.addCell(cell);
//			         
//			        cell.setPhrase(new Phrase("Roles", font));
//			        table.addCell(cell);
//			         
//			        cell.setPhrase(new Phrase("Enabled", font));
//			        table.addCell(cell);       
//			    }
		
//		private void addTableHeader1(PdfPTable tableA1) {
//		    Stream.of("column header 1", "column header 2", "column header 3")
//		      .forEach(columnTitle -> {
//		        PdfPCell header = new PdfPCell();
//		        //header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//		        header.setBorderWidth(2);
//		        header.setPhrase(new Phrase(columnTitle));
//		        tableA1.addCell(header);
//		    });
//		}
		
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		
		System.err.println("aList"+aList);
		
		List<String> l1 = aList.get(0);
		int colunmSize = l1.size(); // get Colunm Size
		System.out.println("colunmSize== "+colunmSize);
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		
		
		PdfPTable table1 = new PdfPTable(colunmSize);
		Paragraph p;

		table1.setWidthPercentage(90);
		table1.setWidths(new int[] {3,3,3});
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10); 
	
		for(int h=0;h<TH.size();h++) {
			p = new Paragraph(TH.get(h),fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table1.addCell(p);
		}
		
		table1.setHeaderRows(1); // table first row will be repeated in all pages
		Font fontTableText = FontFactory.getFont(FontFactory.TIMES, 10);
		for(int i=0;i< aList.size();i++) {
			List<String> l = aList.get(i);
			for(int j = 0;j<l.size();j++) {
				p = new Paragraph(l.get(j),fontTableText);
				p.setAlignment(Element.ALIGN_CENTER);
				table1.addCell(p);
			}
		}
//		table.addCell(table1);
		
		
		
		
	
		PdfPCell cell123;
		cell123 = new PdfPCell();
		cell123.addElement(tableheader);
		cell123.addElement(tabledata);
//		cell123.addElement(new Paragraph("\n"));  //line use-for space
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(cell1);
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(cellt);
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(table1);
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(new Paragraph("\n"));
		
		
		cell123.addElement(img);				//logo3-gs-gold

//		cell123.addElement(img1);				//logo4-silver
//		
//		cell123.addElement(img2);				//logo5-bronze
//		
//		cell123.addElement(img3);				//logo6-platinum
		
		
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
		table.addCell(cell123);
		
		
		
		
		
		
//		PdfPTable table2 = new PdfPTable(1);
//		table2.setWidthPercentage(100);
//		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//		PdfPCell cell2;
//		cell2 = new PdfPCell();
//		cell2.setBorder(Rectangle.NO_BORDER);
//		cell2.addElement(table);
//		table1.addCell(cell1);

//		document.add(table2);
	
	document.add(table);
	super.buildPdfMetadata(model, document, request);
	}

//	public static void main(String[] args) {
//		
//	}

	
}
