package com.AyushEdu.controller.LMS_Teacher;
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
import com.lowagie.text.pdf.PdfWriter;

public class DownloadPdfprintForm extends AbstractPdfView {
	
	
	String Type = "";
	List<String> TH;
	List<String> TH2;
	List<String> TH3;
	List<String> TH4;
	List<String> TH5;
	List<String> TH6;
	String foot = "";
	String Heading = "";
	String username = "";
	private ArrayList<ArrayList<String>> list1;
	private ArrayList<ArrayList<String>> list3;
	private ArrayList<ArrayList<String>> list4;
	private ArrayList<ArrayList<String>> list5;
	private ArrayList<ArrayList<String>> list6;
	private ArrayList<ArrayList<String>> list7;
//	List<ArrayList<String>>listS;
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	//public static final String ENCRYPTED_PDF = "usr/local/nginx/html/doc/beehive_reset_pwd_form.pdf";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public DownloadPdfprintForm(String Type,List<String> TH2,String Heading,String username,String foot, ArrayList<ArrayList<String>> list1, List<String> TH3,  List<String> TH4,List<String> TH5,List<String> TH6,ArrayList<ArrayList<String>> list3,ArrayList<ArrayList<String>> list4,ArrayList<ArrayList<String>> list5,ArrayList<ArrayList<String>> list6,ArrayList<ArrayList<String>> list7){//,List<ArrayList<String>> listS
		this.Type = Type;
		this.TH = TH;
		this.TH2 = TH2;
		this.TH3 = TH3;
		this.TH4 = TH4;
		this.TH5 = TH5;
		this.TH6 = TH6;
		this.foot = foot;
		this.Heading = Heading;
		this.username = username;
		this.list1=list1;
		this.list3=list3;
		this.list4=list4;
		this.list5=list5;
		this.list6=list6;
		this.list7=list7;
	}
	
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

//		document.open();
//
//		Phrase p = new Phrase();
//		p.add(Heading);
//		document.setMargins(document.leftMargin(), document.rightMargin(), 29, document.bottomMargin());
//		
//		document.setPageCount(1);
//	
//		document.setPageSize(PageSize.A4); // set document landscape
//		super.buildPdfMetadata(model, document, request);
		
		document.open();

		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);

		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);

		BaseFont unicode = null;
//		Font font = new Font(unicode,18,Font.NORMAL);
		Font font007 = new Font(unicode,10,Font.NORMAL);
		Font fontTableHeadingSubMainHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
		Font myFont = new Font(unicode,18, Font.ITALIC);
		

		Image logo2 = null;

		try {

			@SuppressWarnings("deprecation")

			String indian_Army = request.getRealPath("/") + "admin" + File.separator + "js" 
					+ File.separator + "images" + File.separator + "NCHLogo.png";

			logo2 = Image.getInstance(indian_Army);// "d://indianarmy_smrm5aaa.jpg"

		} catch (BadElementException e) {

			e.printStackTrace();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		logo2.setAlignment(Image.RIGHT);

		logo2.scaleAbsoluteHeight(100);

		logo2.scaleAbsoluteWidth(100);

		logo2.scalePercent(40);

		Chunk chunk3 = new Chunk(logo2, -5, 4);

		Chunk u1 = new Chunk("Central Council Of Homeopathy \n", myFont);
		Chunk u2 = new Chunk("A Statutory Body under the Ministry Of AYUSH, Govt of India  \n", font007);
		Chunk u3 = new Chunk("Welcome, DEMO Homoeopathic Medical College and Hospital\n", fontTableHeadingSubMainHead);

		for(int i=0;i< list6.size();i++) {
			System.err.println("---univerrrrr----------"+list6);
			List<String> l = list6.get(i);
			for(int j = 0;j<l.size();j++) {
				u3 = new Chunk("Welcome ," +l.get(j)+" \n",fontTableHeadingSubMainHead);
				
			}
		}
	
//		underline2.setUnderline(0.1f, -2f);

		// Chunk underline4 = new Chunk(MainHead, fontTableHeadingSubMainHead);

		PdfPTable table3 = new PdfPTable(3);

		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase p = new Phrase(chunk3);

//		p.add("\n");

//		p.add(underline2);

		p.add("\n");
		
		p.add(u1);
		p.add(u2);
		p.add(u3);

		// p.add(underline4);

		p.add("\n");
		p.add("\n");
		p.add("\n");
		

		p.setFont(fontTableHeading1);

		Paragraph cell = new Paragraph(p);

		cell.setAlignment(Element.ALIGN_CENTER);

		float[] relativeWidths;

		int colunmSize = 3;

		relativeWidths = new float[colunmSize];

		Arrays.fill(relativeWidths, 0, colunmSize, 1);

		relativeWidths[1] = (float) 4.50;

		table3.addCell(new Phrase());

		table3.addCell(cell);

		table3.addCell(new Phrase());

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

		Phrase p1 = new Phrase();

		Chunk p89 = new Chunk(foot, fontTableHeadingMainHead);

		p89.setUnderline(0.1f, -2f);

		p1.add(p89);

		p1.add("\n");

		Chunk p90 = new Chunk("RESTRICTED", fontTableHeading1);

		p90.setUnderline(0.1f, -2f);

		p1.add(p90);

		HeaderFooter footer = new HeaderFooter(p1, false);

		footer.setAlignment(Element.ALIGN_CENTER);

		footer.setBorder(Rectangle.TOP);

		document.setFooter(footer);

		document.setPageCount(1);

		System.out.println("Type==" + Type);

		if (Type.equals("L")) {

			document.setPageSize(PageSize.A4); // set document landscape

		}

		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\""); //2 lines uncomment in last
		
		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		List<String> l1 = aList.get(0);
		int colunmSize = l1.size(); // get Colunm Size
		
		ArrayList<List<String>> aList1 = (ArrayList<List<String>>) model.get("userList"); 
		List<String> l2 = aList1.get(0);
		int colunmSize1 = l2.size(); // get Colunm Size
		
		System.out.println("colunmSize== "+colunmSize1);
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
//		Paragraph p1 = new Paragraph("PERSONAL DETAILS: "+"\n\n",font1);
////		p1.setAlignment(Element.ALIGN_CENTER);
//		document.add(p1);
	
		PdfPTable table = new PdfPTable(colunmSize1);
		Paragraph p;

		table.setWidthPercentage(100);
		table.setWidths(new int[] {6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5});//37
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10); 
	
//		for(int h=0;h<TH.size();h++) {
//			p = new Paragraph(TH.get(h),fontTableHeading);
//			p.setAlignment(Element.ALIGN_CENTER);
//			table.addCell(p);
//		}
//		
//		table.setHeaderRows(1); // table first row will be repeated in all pages
//		Font fontTableText = FontFactory.getFont(FontFactory.TIMES, 10);
//		for(int i=0;i< aList.size();i++) {
//			List<String> l = aList.get(i);
//			for(int j = 0;j<l.size();j++) {
//				p = new Paragraph(l.get(j),fontTableText);
//				p.setAlignment(Element.ALIGN_CENTER);
//				table.addCell(p);
//			}
//		}
		
		Paragraph p6 = new Paragraph("\n"+"MEDICAL QUALIFICATION "+"\n\n");
		
		PdfPTable table5 = new PdfPTable(6);
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Paragraph p20 = new Paragraph("");
		for(int h=0;h<TH4.size();h++) {
			p20 = new Paragraph(TH4.get(h),fontTableHeading);
			p20.setAlignment(Element.ALIGN_CENTER);
			table5.addCell(p20);
		}
		
		for(int i=0;i< list4.size();i++) {
			System.err.println("---medicalpdf----------"+list4);
			List<String> l = list4.get(i);
			for(int j = 0;j<l.size();j++) {
				p20 = new Paragraph(l.get(j),fontTableHeading);
				p20.setAlignment(Element.ALIGN_CENTER);
				table5.addCell(p20);
			}
		}
		
		
		PdfPTable tableleftFM = new PdfPTable(2);
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Paragraph pl;
		
		
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		tableleftFM.addCell("PERSONAL DETAILS:");
		tableleftFM.addCell("\n\n");
		
		pl = new Paragraph("First Name : " + l1.get(0) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Middle Name : " + l1.get(1), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Last Name : " + l1.get(2) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Gender : " + l1.get(3), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Birth : " + l1.get(4) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Father's Name : " + l1.get(5), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Mother's Name : " + l1.get(6) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Spouse Name : " + l1.get(7), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Mobile No. : " + l1.get(8) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Email : " + l1.get(9), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Aadhar No. : " + l1.get(10) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Pan No. : " + l1.get(11), font);
		tableleftFM.addCell(pl);
		
		tableleftFM.addCell("\n\n");
		
//		pl = new Paragraph("Acadamic Qualification : " + l1.get(12) + "", font);
//		tableleftFM.addCell(pl);
		
		
//		document.add(tableleftFM);
		
		tableleftFM.addCell("\n\n");
//		tableleftFM.addCell("\n\n");
		
		tableleftFM.addCell("PRESENT ADDRESS:");
		tableleftFM.addCell("\n\n");
		
//		Paragraph p7 = new Paragraph("Address "+"\n\n",font1);
////		p1.setAlignment(Element.ALIGN_CENTER);
//		document.add(p7);
		
		pl = new Paragraph("Address Line 1 : " + l1.get(12), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Address Line 2 : " + l1.get(13) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("state : " + l1.get(14), font);
		tableleftFM.addCell(pl);
	
		pl = new Paragraph("District : " + l1.get(15) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("City/Village : " + l1.get(16), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Pin Code : " + l1.get(17) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Phone no. : " + l1.get(18), font);
		tableleftFM.addCell(pl);
		
		tableleftFM.addCell("\n\n");
		tableleftFM.addCell("CORRESPONDENCE ADDRESS:");
		tableleftFM.addCell("\n\n");
		pl = new Paragraph("Permanent Address Line 1 : " + l1.get(19) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Permanent Address Line 2 : " + l1.get(20), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("state : " + l1.get(21) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("District : " + l1.get(22), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("City/Village: " + l1.get(23) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Pin Code : " + l1.get(24), font);
		tableleftFM.addCell(pl);
		
	
		pl = new Paragraph("Phone No. : " + l1.get(25) + "", font);
		tableleftFM.addCell(pl);
		tableleftFM.addCell("\n\n");
		
		
		

		Paragraph p8 = new Paragraph("\n"+"OTHER QUALIFICATION "+"\n\n");
		
		PdfPTable table6 = new PdfPTable(5);
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Paragraph p21 = new Paragraph("");
		for(int h=0;h<TH5.size();h++) {
			p21 = new Paragraph(TH5.get(h),fontTableHeading);
			p21.setAlignment(Element.ALIGN_CENTER);
			table6.addCell(p21);
		}
		
		for(int i=0;i< list5.size();i++) {
			System.err.println("---medicalpdf----------"+list4);
			List<String> l = list5.get(i);
			for(int j = 0;j<l.size();j++) {
				p21 = new Paragraph(l.get(j),fontTableHeading);
				p21.setAlignment(Element.ALIGN_CENTER);
				table6.addCell(p21);
			}
		}
		
	
		//tableleftFM.addCell("\n\n");
		
		
		tableleftFM.addCell("STATE REGISTRATION ");
		tableleftFM.addCell("\n\n");
		pl = new Paragraph("State Register no. : " + l1.get(26), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Name Of The State Board : " + l1.get(27) + "", font);
		tableleftFM.addCell(pl);
		pl = new Paragraph("Date Of The Register : " + l1.get(28), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Central Registration No.: " + l1.get(29) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Adjunct Registration No.: " + l1.get(30) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Adjunct State Name: " + l1.get(31) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Validity upto: " + l1.get(32) + "", font);
		tableleftFM.addCell(pl);
		tableleftFM.addCell("\n\n");
		
		
		tableleftFM.addCell("DIRECT REGISTRATION:");
		tableleftFM.addCell("\n\n");
		
		pl = new Paragraph("Direct Registration No . : " + l1.get(33), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date of Registration . : " + l1.get(34), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Name of Department/Organization . : " + l1.get(35), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Validity Upto. : " + l1.get(36), font);
		tableleftFM.addCell(pl);
		
		
		
		
		
		tableleftFM.addCell("\n\n");
		document.add(tableleftFM);
		document.add(p6);
		document.add(table5);
		
		document.add(p8);
		document.add(table6);
		
		
		
		Paragraph p4 = new Paragraph("\n"+"PROFESSION EXPERIANCE: "+"\n\n");
		document.add(p4);
			
		
		PdfPTable table2 = new PdfPTable(7);
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Paragraph p18 = new Paragraph("");
		for(int h=0;h<TH2.size();h++) {
			p18 = new Paragraph(TH2.get(h),fontTableHeading);
			p18.setAlignment(Element.ALIGN_CENTER);
			table2.addCell(p18);
		}
		
		for(int i=0;i< list1.size();i++) {
			List<String> l = list1.get(i);
			System.err.println("list -----------"+l);
			for(int j = 0;j<l.size();j++) {
				p18 = new Paragraph(l.get(j),fontTableHeading);
				p18.setAlignment(Element.ALIGN_CENTER);
				table2.addCell(p18);
			}
		}
		document.add(table2);
		
		
		Paragraph p9 = new Paragraph("\n"+"ACADEMIC EXPERIANCE: "+"\n\n");
		document.add(p9);
			
		
		PdfPTable table7 = new PdfPTable(6);
		table7.setWidthPercentage(100);
		table7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table7.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Paragraph p25 = new Paragraph("");
		for(int h=0;h<TH6.size();h++) {
			p25 = new Paragraph(TH6.get(h),fontTableHeading);
			p25.setAlignment(Element.ALIGN_CENTER);
			table7.addCell(p25);
		}
		
		for(int i=0;i< list7.size();i++) {
			List<String> l = list7.get(i);
			System.err.println("list -----------"+l);
			for(int j = 0;j<l.size();j++) {
				p25 = new Paragraph(l.get(j),fontTableHeading);
				p25.setAlignment(Element.ALIGN_CENTER);
				table7.addCell(p25);
			}
		}
		document.add(table7);
		
	
		
		Paragraph p3 = new Paragraph("\n"+"DEGREE AND SUPPORTING DOCUMENTS : "+"\n\n");
		document.add(p3);
		
		PdfPTable table3 = new PdfPTable(2);
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		Paragraph p188 = new Paragraph("\n");
		for(int h=0;h<TH3.size();h++) {
			p188 = new Paragraph(TH3.get(h),fontTableHeading);
			p188.setAlignment(Element.ALIGN_CENTER);
			table3.addCell(p188);
		}
		
		for(int i=0;i< list3.size();i++) {
			List<String> l = list3.get(i);
			for(int j = 0;j<l.size();j++) {
				p188 = new Paragraph(l.get(j),fontTableHeading);
				p188.setAlignment(Element.ALIGN_CENTER);
				table3.addCell(p188);
			}
		}
		document.add(table3);
		
	
		
		
		Paragraph p5 = new Paragraph("\n"+"CONFIRMATION: "+"\n\n");
//		document.add(p5);
		
		Paragraph p7 = new Paragraph("			"+"I hereby confirm that the information provided herein is accurate, correct and complete and that the documents submitted along with this application form are genuine.\n"
				+ "											  	  I understand and agree that this declaration is final and irrevocable, and that it is not subject to cancellation ."+"\n\n");
//		document.add(p7);

		
		
		super.buildPdfMetadata(model, document, request);
	}

	
	
	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {
			this.request = request;
		}
		
		int page = 1;

		public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
				PdfContentByte[] canvases) {

			String ip = "";

			if (request != null) {

				ip = request.getHeader("X-FORWARDED-FOR");

				if (ip == null || "".equals(ip)) {

					ip = request.getRemoteAddr();

				}

			}

			//// System.out.println("IP==" + ip);

			Date now = new Date();

			String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);

			String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;

			Image img = null;

			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
					BufferedImage.TYPE_INT_ARGB);

			Graphics graphics = bufferedImage.getGraphics();

			graphics.setColor(Color.white);

			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 8));

			graphics.drawString(watermark + watermark, 0, 20);
			try {

				try {

					img = com.lowagie.text.Image.getInstance(bufferedImage, null);

				} catch (IOException e) {

					e.printStackTrace();

				}

			} catch (BadElementException e) {

				e.printStackTrace();
			}

			this.image = img;

			try {

				
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];

				// Portrait Page size 700 * 523

				// Landscape page size 453 * 770

				int tableWidth = (int) table.getTotalWidth();

				int first = 0;

				if (tableWidth == 523) {

					first = 750;
				}

				if (tableWidth == 770) {

					first = 500;

				}

				int loop = (int) table.getRowHeight(0);

				int last = first - (int) table.getRowHeight(0);

				

				Phrase p = new Phrase();

				//p.add(String.valueOf(page));

				float width = ColumnText.getWidth(p);

				ColumnText.showTextAligned(cb, PdfContentByte.ALIGN_RIGHT, p, cb.getPdfDocument().right() - width,
						cb.getPdfDocument().top() + 9, 0);

				page += 1;

				while (first > last) {

					image.setAbsolutePosition(30, first);

					cb.addImage(image, false);

					first -= 30;
				}

			} catch (DocumentException e) {

				throw new ExceptionConverter(e);

			}
		}
	}

	
}

