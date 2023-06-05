package com.AyushEdu.controller.Exp_Excel;

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
import com.lowagie.text.pdf.ColumnText;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;


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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;

public class DownloadInventoryPdf extends AbstractPdfView {

	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String foot = "";
	String	logo_img_path ="";
	
	
	
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\NSG\\admission.pdf";
	
	
	public DownloadInventoryPdf(String Type, List<String> TH, String Heading, String username,String foot) {
		this.Type = Type;
		this.TH = TH;
		this.Heading = Heading;
		this.username = username;
		this.foot = foot;
		this.logo_img_path = logo_img_path;

	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

		document.open();

		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);

		Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);



		

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

		Chunk underline2 = new Chunk("NCH REGULATION REPORT (Ministry of Ayush)", fontTableHeadingMainHead);

		underline2.setUnderline(0.1f, -2f);

		// Chunk underline4 = new Chunk(MainHead, fontTableHeadingSubMainHead);

		PdfPTable table3 = new PdfPTable(3);

		table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		Phrase p = new Phrase(chunk3);

		p.add("\n");

		p.add(underline2);

		p.add("\n");

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

		//DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
		

	 


		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<String>> aList = (ArrayList<ArrayList<String>>) model.get("userList");
		List<String> l1 = aList.get(0);
		int colunmSize = l1.size()-1;
		
		
		 PdfPTable table111 = new PdfPTable(1);
		 table111.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		 table111.setWidthPercentage(100);
		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 
			 
		
		PdfPTable table = new PdfPTable(colunmSize);
		Paragraph p;
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		Font fontTableValue = FontFactory.getFont(FontFactory.TIMES, 8);  //10
		
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
		Font  fontTableHeadingdata = FontFactory.getFont(FontFactory.TIMES, 9);
		Font fontTableHeadingSubMainHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		
		
		
		/*
		 * Chunk underline3 = new Chunk("NCH REGULATION REPORT" ,
		 * fontTableHeadingSubMainHead); underline3.setUnderline(0.1f, -2f);
		 */
		  
			Image logo_static = null;
			try {
				@SuppressWarnings("deprecation")
//				String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCISM.jpeg";
				String dgis_logo4 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCHLogo.png";
				logo_static = Image.getInstance(dgis_logo4);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			logo_static.setAlignment(Image.MIDDLE);
			logo_static.scaleAbsoluteHeight(10);
			logo_static.scaleAbsoluteWidth(5);
			logo_static.scalePercent(60);
			
			String file_name = "02:00";
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");
		    Chunk chunk1 = new Chunk(logo_static,-470,-20);
		    
//		   Phrase ph = new Phrase(underline3);
//		   ph.add(chunk1);
		 
		 Phrase phdd = new Phrase();
		 phdd.add(chunk1);
		 phdd.setFont(fontTableHeadingSubMainHead);
			Paragraph cell4 = new Paragraph(phdd);
			cell4.setAlignment(Element.ALIGN_CENTER);

		
		for (int h = 0; h < TH.size(); h++) {

			p = new Paragraph(TH.get(h), fontTableHeading);
			p.setAlignment(Element.ALIGN_CENTER);
			table.addCell(p);

		}

		//table.setHeaderRows(1);

		for (int i = 0; i < aList.size(); i++) {
			List<String> l = aList.get(i);
			for (int j = 0; j < l.size()-1; j++) {
				if(j==1) {
				p = new Paragraph(l.get(j),fontTableValue);
				try {
//					--04-03-2023
					  Image idimage = Image.getInstance(l.get(11).toString()); 
					 
					  idimage.scaleAbsoluteHeight(5); 
					  idimage.scaleAbsoluteWidth(50); 
					  idimage.scalePercent(20);
					  
					  	PdfPCell blank_cell1;
						blank_cell1 = new PdfPCell();
						blank_cell1.addElement(p);
						blank_cell1.addElement(idimage);
						blank_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(blank_cell1);
					  
					  }
					  catch (Exception e) {
							e.printStackTrace();
							
							String no_image = request.getRealPath("/") + "admin" + File.separator  + "js" + File.separator 	+ "images" + File.separator	+ "No_Image.jpg";
							Image no_img = Image.getInstance(no_image);
							no_img.scaleAbsoluteHeight(5); 
							no_img.scaleAbsoluteWidth(25); 
							no_img.scalePercent(25);
							 PdfPCell blank_cell1;
								blank_cell1 = new PdfPCell();
								blank_cell1.addElement(p);
								blank_cell1.addElement(no_img);
								table.addCell(blank_cell1);
						}
			}else {
				
				table.addCell(l.get(j));
			}
				}
		}
		
		
		
		
		PdfPTable table112 = new PdfPTable(1);
		table112.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table112.setWidthPercentage(100);
		 
		
		PdfPTable tablefooter= new PdfPTable(3);
		tablefooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		tablefooter.setWidthPercentage(100);
		
		table112.addCell(tablefooter);


		PdfPTable table1 = new PdfPTable(1);
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell1;
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		
		cell1.addElement(cell4);
		
		/*
		 * cell1.addElement(new Phrase("\n")); // cell1.addElement(table111);
		 * cell1.addElement(new Phrase("\n"));
		 */


		cell1.addElement(table);
		
		cell1.addElement(new Phrase("\n"));
		cell1.addElement(table112);
	//	cell1.addElement(new Phrase("\n"));
		
		
		
	//	table1.setTableEvent(new ImageBackgroundEvent(request));
		table1.addCell(cell1);

		table1.setTableEvent(new ImageBackgroundEvent(request));

		
		
		
		
		document.add(table1);
			
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

				p.add(String.valueOf(page));

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
