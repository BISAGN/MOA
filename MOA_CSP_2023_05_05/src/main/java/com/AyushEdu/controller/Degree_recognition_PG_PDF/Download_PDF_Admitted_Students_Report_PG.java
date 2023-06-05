package com.AyushEdu.controller.Degree_recognition_PG_PDF;
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

import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc.Role;
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
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class Download_PDF_Admitted_Students_Report_PG extends AbstractPdfView  {


	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	private ArrayList<ArrayList<String>> list;

	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	public Download_PDF_Admitted_Students_Report_PG(String string, List<String> tH2, String heading2, String username2,
			ArrayList<ArrayList<String>> list2){
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		this.list=list;
	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

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

		logo2.scalePercent(8);

		Chunk chunk3 = new Chunk(logo2, -5, 4);
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList"); 
		List<String> l1 = aList.get(0);
	
		
		Chunk u1 = new Chunk("Number Of Student Admitted As Regular Candidate For M.D. Course \n", myFont);
//		Chunk u2 = new Chunk("Postgraduate Course In Homoeopathy In India  \n", font007);


		PdfPTable table3 = new PdfPTable(3);

		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase p = new Phrase(chunk3);

		p.add("\n");
		
		p.add(u1);
//		p.add(u2);

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
		Chunk glue = new Chunk(new VerticalPositionMark());
		Chunk p89 = new Chunk("1", fontTableHeading1);
		Phrase p1 = new Phrase("" + glue);
		p1.add(p89);
		p.add(glue);
		p1.setFont(fontTableHeading1);


		HeaderFooter footer = new HeaderFooter(p1, false);

		footer.setAlignment(Element.ALIGN_CENTER);

		footer.setBorder(Rectangle.TOP);

		document.setFooter(footer);

		document.setPageCount(1);

		System.out.println("Type==" + Type);

		if (Type.equals("L")) {

			document.setPageSize(PageSize.A4); 
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
		
		ArrayList<List<String>> aList2 = (ArrayList<List<String>>) model.get("userList"); 
		List<String> l3 = aList2.get(0);
		int colunmSize2 = l3.size(); // get Colunm Size
		
		System.out.println("colunmSize== "+colunmSize1);
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

		
		PdfPTable tableleftFM = new PdfPTable(2);
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		Paragraph pl;
		
		
		tableleftFM.setWidthPercentage(100);
		tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		
		pl = new Paragraph("Name Of Student : " + l1.get(1) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Admission : " + l1.get(2), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Registration : " + l1.get(3), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Course Name : " + l1.get(4), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Rank : " + l1.get(5) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Marks : " + l1.get(6), font);
		tableleftFM.addCell(pl);
		
		
		pl = new Paragraph("All India : " + l1.get(7) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("State  : " + l1.get(8), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Admission Authority  : " + l1.get(9) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Court Order & Others : " + l1.get(10), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Qualification Name : " + l1.get(11), font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Year Of Award Admission  : " + l1.get(12) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Registration State : " + l1.get(13) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Completion MD Part1 : " + l1.get(14) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Completion MD Part2 : " + l1.get(15) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Completion MD : " + l1.get(16) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Date Of Completion Internship : " + l1.get(17) + "", font);
		tableleftFM.addCell(pl);
		
		pl = new Paragraph("Remarks   : " + l1.get(18), font);
		tableleftFM.addCell(pl);

		tableleftFM.addCell("\n\n");
		
		
		
		
		document.add(tableleftFM);

		
		Paragraph p5 = new Paragraph("\n"+"CONFIRMATION: "+"\n\n");
		
		Paragraph p7 = new Paragraph(""+"I hereby confirm that the information provided herein is accurate, correct and complete and that the documents submitted along with this application form are genuine.\n"
				+ "I understand and agree that this declaration is final and irrevocable, and that it is not subject to cancellation ."+"\n\n");

		
		
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
	

	//class PageNumeration extends PdfPageEventHelper {
	PdfTemplate total;
	PdfTemplate total1;

	public void PageNumeration(PdfWriter writer) {
		try {
//			System.err.println("------ON PAGE NUMARATION-----");
			total = writer.getDirectContent().createTemplate(30, 16);
			total1 = writer.getDirectContent().createTemplate(30, 16);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(1);

		try {
//			System.err.println("------ON END PAGE-----"+writer.getPageNumber());

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
//		System.err.println("------ON CLOSE PAGE-----");
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2,
				2, 0);
		char[] totalRecords = null;
		// ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new
		// Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
		ColumnText.showTextAligned(total1, Element.ALIGN_LEFT, new Phrase(String.valueOf(totalRecords)), 2, 2, 0);

	}


}



//}