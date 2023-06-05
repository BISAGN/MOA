package com.AyushEdu.controller.LMS_Attendance;
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
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.AyushEdu.controller.LMS_Attendance.DownloadPdf2.ImageBackgroundEvent;
import com.AyushEdu.validation.DateWithTimeStampController;
import com.lowagie.text.BadElementException;
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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;

public class DownloadPdfStaff extends AbstractPdfView {
	
	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String month = "";
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";

	public DownloadPdfStaff(String Type, List<String> TH, String Heading, String username, String month) {
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		this.month = month;
	}
	
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		/*
		 * PdfWriter writer = null; try { writer = PdfWriter.getInstance(document, new
		 * FileOutputStream(ENCRYPTED_PDF)); } catch (FileNotFoundException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } catch
		 * (DocumentException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } try { writer.setEncryption(USER_PASSWORD.getBytes(),
		 * OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING,
		 * PdfWriter.ENCRYPTION_AES_128); } catch (DocumentException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
		document.open();
		/*
		 * Image logo = null; try {
		 * 
		 * @SuppressWarnings("deprecation") String dgis_logo =
		 * request.getRealPath("/")+"admin"+File.separator+"login"+File.separator+
		 * "images"+File.separator+"beehive.png"; logo = Image.getInstance(dgis_logo); }
		 * catch (BadElementException e) { e.printStackTrace(); } catch
		 * (MalformedURLException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); } logo.setAlignment(Image.MIDDLE);
		 * logo.scaleAbsoluteHeight(20); logo.scaleAbsoluteWidth(20);
		 * logo.scalePercent(30); Chunk chunk = new Chunk(logo, 0, -4);
		 * 
		 * Image logo2 = null; try {
		 * 
		 * @SuppressWarnings("deprecation") String indian_Army =
		 * request.getRealPath("/")+"admin"+File.separator+"login"+File.separator+
		 * "images"+File.separator+"emeLogo.png"; logo2 =
		 * Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg" } catch
		 * (BadElementException e) { e.printStackTrace(); } catch (MalformedURLException
		 * e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 * logo2.setAlignment(Image.RIGHT); logo2.scaleAbsoluteHeight(20);
		 * logo2.scaleAbsoluteWidth(20); logo2.scalePercent(22);
		 */
		/* Chunk chunk2 = new Chunk(logo2, 10, -4); */

		Phrase p = new Phrase();
		/*
		 * p.add(chunk); p.add("                   BEEHIVE                    ");
		 * p.add(chunk2);
		 */
//		p.add("Restricted");
		Font fontTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		p.setFont(fontTable);
		p.add(Heading);

		HeaderFooter header = new HeaderFooter(p, false);
		// header.setAlignment(Element.ALIGN_CENTER);
		header.setBorder(Rectangle.BOTTOM);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		Phrase p1 = new Phrase();
		// p1.add("Approved By");
//		p1.add("Restricted");
		// p1.add("Verified By");

		HeaderFooter footer = new HeaderFooter(p1, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorder(Rectangle.TOP);
		document.setFooter(footer);

		document.setPageCount(1);

		if (Type.equals("L")) {
			document.setPageSize(PageSize.A4.rotate()); // set document landscape
		}
		// document.setPageSize(PageSize.A4.rotate()); // set document landscape
		super.buildPdfMetadata(model, document, request);
	}

	@SuppressWarnings("null")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("innnnnnnnnnnnnnn");

		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		String file_name = datetimestamp.currentDateWithTimeStampString();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");

		@SuppressWarnings("unchecked")
		ArrayList<List<String>> aList = (ArrayList<List<String>>) model.get("userList");
		List<String> l1 = aList.get(0);
		System.out.println("innnnnnnnnnnnnnn"+l1);

		int counter = 0;
		int[] temp = new int[l1.size()];
//		if(l1.size() > 30) {
			if (month.contains("January") || month.contains("March") || month.contains("May") || month.contains("July")
					|| month.contains("August") || month.contains("October") || month.contains("December")) {
				counter = 33;
				temp = new int[]{10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
				System.out.println("innnnnnnnnnnnnnn"+temp);

			}else if (month.contains("February")) {
				counter = 30;
				temp = new int[]{10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
				System.out.println("innnnnnnnnnnnnnn"+temp);

			}else {
				counter = 32;
				temp = new int[]{10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
				System.out.println("innnnnnnnnnnnnnn"+temp);

			}
//		}
		System.out.println("innnnnnnnnnnnnnn"+l1.size());

		
//		if(l1.size() < 31) {
//			counter = l1.size()-1;
//		}
//		if(l1.size() < 30){
//			counter = l1.size();
//			for(int i=0;i < l1.size();i++) {
//				
//				if(i > 0) {
//					temp[i] = 1;
//				}else {
//					temp[i] = 12;
//				}
//				
//
//			}
//			
//		}

		PdfPTable table = new PdfPTable(counter);
		Paragraph p;
		table.setWidthPercentage(100);
		 table.setWidths(temp);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		Font fontTableCell = FontFactory.getFont(FontFactory.HELVETICA, 7);

		for (int h = 0; h < TH.size(); h++) {
			p = new Paragraph(TH.get(h), fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table.addCell(p);

		}

		table.setHeaderRows(1); // table first row will be repeated in all pages

		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			for (int j = 0; j < l.size(); j++) {

//				if (counter == 30) {
//					if (j == 29 || j == 30 || j == 31) {
//						table.addCell(new Phrase(l.get(j),fontTableCell));
//
//					} else if (j == 32) {
//						table.addCell(new Phrase(l.get(j),fontTableCell));
//					} else {
//						table.addCell(new Phrase(l.get(j),fontTableCell));
//					}
					table.addCell(new Phrase(l.get(j),fontTableCell));

//				}

//				else if (counter == 32) {
//					if (j == 31) {
//
//					} else {
//						table.addCell(new Phrase(l.get(j),fontTableCell));
//					}
//				} else {
//					table.addCell(new Phrase(l.get(j),fontTableCell));
//				}

			}
		}
		

		PdfPTable table1 = new PdfPTable(1);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
//		cell1.addElement(table);
		table1.setTableEvent(new ImageBackgroundEvent(request));
		table1.addCell(cell1);

		document.add(table);

		super.buildPdfMetadata(model, document, request);
	}

	class ImageBackgroundEvent implements PdfPTableEvent {
		protected Image image;

		HttpServletRequest request;

		ImageBackgroundEvent(HttpServletRequest request) {
			this.request = request;
		}

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
			watermark="";
			Image img = null;
			BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
					BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.getGraphics();
			graphics.setColor(Color.lightGray);
			graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));
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
