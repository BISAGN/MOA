package com.AyushEdu.controller.Regulation;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
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
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class CertificateDownloadPdf_old extends AbstractPdfView {


	String Type = "";
	List<String> TH;
	String Heading = "";
	String username = "";
	String	course_name ="";
	String	set_name ="";
	String	logo_img_path ="";
	
	ArrayList<ArrayList<String>> list=null;
	
	final static String USER_PASSWORD = "user";
	final static String OWNER_PASSWORD = "owner";
	public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	

	public CertificateDownloadPdf_old(String Type,ArrayList<ArrayList<String>> list, List<String> TH, String Heading, String username) {
		this.Type = Type;
		this.TH = TH; 	
		this.Heading = Heading;
		this.username = username;
		this.list = list;
		
		this.logo_img_path = logo_img_path;
	}
	
	
	private byte[] createQRImage( String qrCodeText) {
		int size = 300;
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		} catch (WriterException e) {
			return null;
		}
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		BufferedImage bImage = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write( bImage, "jpg", baos );
			baos.flush();
			baos.close();
		} catch (IOException e) {
			return null;
		}
		byte[] imageInByteArray = baos.toByteArray();
		//String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		return imageInByteArray;
	}

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		 document.open();
		//if (Type.equals("L")) {
			 document.setPageSize(PageSize.A3.rotate()); // set document landscape
		//}
	 
//		document.setPageSize(PageSize.A3.rotate()); // set document landscape
//		super.buildPdfMetadata(model, document, request);
		
		super.buildPdfMetadata(model, document, request);
	}
 
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

//		DateWithTimeStampController datetimestamp = new DateWithTimeStampController();
//		String file_name = datetimestamp.currentDateWithTimeStampString();

		
		response.setContentType("application/pdf");
	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Practitioner certificate" + ".pdf\"");// file download path

		
		BaseFont unicode = null;
		try {
		String devfont = request.getRealPath("/") + "admin" + File.separator   + "js" + File.separator + "font" + File.separator 
			+ "dev.ttf";
			unicode = BaseFont.createFont(devfont,BaseFont.IDENTITY_H,false);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Font font = new Font(unicode,18,Font.NORMAL);
		Font font007 = new Font(unicode,12,Font.NORMAL);
		Font font1 = new Font(unicode,12,Font.NORMAL);
		Font font5 = new Font(unicode,12,Font.NORMAL);
		
		Chunk fonttest = new Chunk("jk\"Vªh; gksE;ksiSFkh vk;ksx", font);
		Chunk fonttest1 = new Chunk("tokgj yky usg: Hkkjrh; fpfdRlk vkSj gksE;ksiSFkh vuqla/kku Hkou] la[;k 61&65] laLFkkxr {ks=] ds lkeusA 'Mh' Cy‚d tudiqjh] ubZ fnYyh&58", font1);
		Chunk fonttest3 = new Chunk("iathdj.k çek.k i=  ", font);
		fonttest3.setUnderline(1.1f, -1f);
		
		
		
		Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 20, 1);
		Font  fontTableHeadingdata = FontFactory.getFont(FontFactory.TIMES, 9);
		Font fontTableHeadingSubMainHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
		
		Font fontTableHeading2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 10, 1);
		Font fontTableHeading3 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 15, 1);
//		Font  fontTableHeadingdata1 = FontFactory.getFont(FontFactory.TIMES, 9);
//		Font fontTableHeadingSubMainHead1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);;
//		
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.setWidthPercentage(100);
		
	//	Chunk chunk = new Chunk();
		Image logo = null;
		Image logo2 = null;
		String s_phto = list.get(0).get(5);
	
		try {
			@SuppressWarnings("deprecation")
//			String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCISM.jpeg";
//			String dgis_logo2 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCHLogo.png";
//			logo = Image.getInstance(dgis_logo2);
			
			String [] s_phto1 = list.get(0).get(7).split("/");
			File dir = new File("/srv/Document/");
			String fname = dir.getAbsolutePath() + File.separator + "" + s_phto1[3]; // type+"_" +
			System.err.println("fnae----"+fname);
			
			if(fname == "/srv/Document/") {
				System.err.println("if--"+fname);
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
				logo = Image.getInstance(fullPath);
				
			}
			else {
				 System.err.println("else--"+fname);
				 logo = Image.getInstance(fname);
				 System.err.println("else--logo2--"+logo2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
//		logo.setAlignment(Image.MIDDLE);
//		logo.scaleAbsoluteHeight(100);
//		logo.scaleAbsoluteWidth(80);
//		logo.scalePercent(100);
		logo.scaleToFit(125f, 125f);
		
		Chunk chunkimage = new Chunk(logo, 50, 2);
		
		String [] s_phto1 = s_phto.split("/");
		System.err.println("s_phto1[2]"+s_phto1[3]);
		//logo2 = Image.getInstance(s_phto1[3]);
		//try {
			@SuppressWarnings("deprecation")
			// String filePath =
			// request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"img"+File.separator+"brahma1.png";
			
			File dir = new File("/srv/Document/");
			String fname = dir.getAbsolutePath() + File.separator + "" + s_phto1[3]; // type+"_" +
			System.err.println("fnae----"+fname);
			
			if(fname == "/srv/Document/") {
				System.err.println("if--"+fname);
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
				logo2 = Image.getInstance(fullPath);
				
			}
			else {
				 System.err.println("else--"+fname);
				logo2 = Image.getInstance(fname);
				 System.err.println("else--logo2--"+logo2);
			}
			

//		} catch (BadElementException e) {
//			e.getMessage();
//			System.err.println("e 1 ------"+e);
//		} catch (MalformedURLException e) {
//			e.getMessage();
//			System.err.println("e 2 ------"+e);
//		} 
//		catch (IOException e) {
//			e.getMessage();
//			System.err.println("e 3------"+e);
//		}
		logo2.setAlignment(Image.MIDDLE);
		logo2.scaleAbsoluteHeight(16);
		logo2.scaleAbsoluteWidth(19);
		logo2.scalePercent(33);
		
	    Chunk chunk1 = new Chunk(logo2,420, 2);

		Chunk underline = new Chunk("", fontTableHeading1);//main heading
		Chunk underline1 = new Chunk("NATIONAL COMMISSION FOR HOMEOPATHY ", fontTableHeading1);
		Chunk underline2 = new Chunk("Jawahar Lal Nehru Bhartiya Chikitsa Avum Homeopathy Anusandhan Bhavan,No.61-65,Institutional Area, Opp. 'D' Block.Janakpuri,New Delhi-58", fontTableHeading2);
		Chunk underline44 = new Chunk("/ Registration Certificate ", fontTableHeading3);
		underline44.setUnderline(1.1f, -1f);
		
		
		

		  

//		Chunk underline1 = new Chunk("Refers to Para 1(a) of IHQ MoD(Army)" , fontTableHeadingSubMainHead);
	
//		Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 19);
//		Paragraph pr = new Paragraph(" Ministry Of Ayush ",fontTableHeading);
//		pr.setAlignment(Element.ALIGN_CENTER);
		
		Chunk glue = new Chunk(new VerticalPositionMark());
		
		Phrase ph = new Phrase(underline);
		ph.add(chunkimage);
		ph.add(chunk1);
		ph.add("\n");
		ph.add("\n");
		ph.add(fonttest);
		ph.add("\n");
		ph.add(underline1);
		ph.add("\n");
		ph.add(fonttest1);
		ph.add("\n");
		ph.add(underline2);
		ph.add("\n");
		ph.add("\n");
		ph.add(fonttest3);
		ph.add(underline44);
		
		
		
		
//		----
		
		
		ph.setFont(fontTableHeading1);
		ph.setFont(fontTableHeading2);
		ph.setFont(fontTableHeading3);
//		phh2.setFont(fontTableHeadingSubMainHead);
		
		
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
		 
		Paragraph b = new Paragraph("",fontTableHeading1 );//heading
		
		System.err.println("========"+list.get(0).get(5));
//		Paragraph c = new Paragraph(list.get(0).get(5),fontTableHeadingSubMainHead);//logo2
			
			 tabledata.addCell(new Phrase(""));
			 tabledata.addCell(b);
//			 tabledata.addCell(cccc);
//			 tabledata.addCell(new Phrase(chunk1));
//			 tabledata.addCell(c);
			 Chunk underline3 = new Chunk("जवाहर लाल नेहरू भारतीय चिक्तिशा  एवं " , fontTableHeadingSubMainHead);
			 //underline3.setUnderline(0.1f, -2f);
			 
			 Chunk underline4 = new Chunk("" , fontTableHeadingSubMainHead);
			// underline4.setUnderline(0.1f, -2f);
			 
			 Phrase phdd = new Phrase(underline3);
			 phdd.setFont(fontTableHeadingSubMainHead);
				Paragraph cell1 = new Paragraph(phdd);
				cell1.setAlignment(Element.ALIGN_CENTER);
				
				 Phrase phdd2 = new Phrase(underline4);
				 phdd.setFont(fontTableHeadingSubMainHead);
					Paragraph cell2 = new Paragraph(phdd2);
					cell1.setAlignment(Element.ALIGN_CENTER);
				
		//Chunk abc = new Chunk("	NCH No."+list.get(0).get(2)+" Candidate's Name  "+"\n"+list.get(0).get(0)+ "Father's Name ____________________________ "+" "+set_name+" Qualification ____________________________ "+" "+set_name+"\n   Address _______________________________________________________________________ \"+\" \"+set_name+\""  , fontTableHeadingSubMainHead);
					
					
					
 Font Heading = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
	Font fontTableHeadingNonBoldData = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);

	Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 
	Font fontTableHeading89 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15); 
	Font fontTableHeading007 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12); 
	Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
	
	Paragraph p=new Paragraph();
	
	Paragraph pl;
	Paragraph plv;
	Paragraph pr;
	Paragraph prv;
	
	
	PdfPTable tablecenterFMn = new PdfPTable(1);
	tablecenterFMn.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tablecenterFMn.setWidthPercentage(100);
	 
	Chunk nch_hin = new Chunk("çek.k i= la-  & jk- gks - vk-"+" ", font);
	Chunk font_eng1 = new Chunk("/NCH No."+" ", fontTableHeading89);
	Chunk nch_eng = new Chunk(list.get(0).get(2) +" ", fontTableHeading);
 	Paragraph p_n=new Paragraph();
	p_n.add(nch_hin);
	p_n.add(font_eng1);
	p_n.add(nch_eng);
	//blank 
	Chunk bln = new Chunk("                                                                                                                                            " +" ", fontTableHeading);
 	p_n.add(bln);
	
	 
	
	//date
	Chunk dt_hin = new Chunk("fnukad "+" ", font);
	Chunk dt_eng = new Chunk("/ Date :- "+new Date() +"\n ", fontTableHeading89);
 	p_n.add(dt_hin);
	p_n.add(dt_eng);
	tablecenterFMn.addCell(p_n);
 
	
	///////////////
	
	
   	PdfPTable tableleftFM = new PdfPTable(3);
	tableleftFM.setWidthPercentage(100);
	tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 
	Chunk font_hin = new Chunk("vHk;kFkhZ dk uke"+"", font);
	Chunk font_eng = new Chunk(" / Candidate's Name "+" ", fontTableHeading89);
 
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	pl.add(font_hin);
	pl.add(font_eng);
	tableleftFM.addCell(pl);
	
	
	//janki 
	 
//	pl = new Paragraph("Father's Name", fontTableHeading);
//	tableleftFM.addCell(pl);
	Chunk f_hin = new Chunk("firk dk uke"+"", font);
	Chunk f_eng = new Chunk(" / Father's Name "+" ", fontTableHeading89);
 
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	pl.add(f_hin);
	pl.add(f_eng);
	tableleftFM.addCell(pl);
	 
	//qualification
//	pl = new Paragraph("Qualification", fontTableHeading);
//	tableleftFM.addCell(pl);
	Chunk q_hin = new Chunk(";ksX;rk"+"", font);
	Chunk q_eng = new Chunk(" / Qualification "+" ", fontTableHeading89);
 
	
	
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	pl.add(q_hin);
	pl.add(q_eng);
	tableleftFM.addCell(pl);
	
	
	
	PdfPTable tablecenterFM = new PdfPTable(1);
	tablecenterFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tablecenterFM.setWidthPercentage(100);
	tablecenterFM.addCell(tableleftFM);
	//tablecenterFM.addCell(tablerightFM);
//	tablecenterFM.setTableEvent(new ImageBackgroundEvent(request));
	PdfPTable tableleftFM1 = new PdfPTable(3);
	tableleftFM1.setWidthPercentage(100);
	tableleftFM1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
	pl = new Paragraph(list.get(0).get(0), fontTableHeading);
	tableleftFM1.addCell(pl);
	pl = new Paragraph(list.get(0).get(1), fontTableHeading);
	tableleftFM1.addCell(pl);
	pl = new Paragraph(list.get(0).get(3), fontTableHeading);
	tableleftFM1.addCell(pl);

	PdfPTable tablecenterFM1 = new PdfPTable(1);
	tablecenterFM1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tablecenterFM1.setWidthPercentage(100);
	tablecenterFM1.addCell(tableleftFM1);

	//tablecenterFM1.addCell(tablerightFM1);

//	-----  address
	
	PdfPTable tableleftFM2 = new PdfPTable(1);
	tableleftFM2.setWidthPercentage(100);
	tableleftFM2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
//	pl = new Paragraph("Address", fontTableHeading);
//	tableleftFM2.addCell(pl);
	
	Chunk a_hin = new Chunk("irk"+"", font);
	Chunk a_eng = new Chunk(" / Address "+" ", fontTableHeading89);
 
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	pl.add(a_hin);
	pl.add(a_eng);
	tableleftFM2.addCell(pl);
	
//	tableleftFM2.setTableEvent(new ImageBackgroundEvent(request));
//	super.buildPdfMetadata(model, document, request);
	

	
	
	PdfPTable tablecenterFM2 = new PdfPTable(1);
	tablecenterFM2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tablecenterFM2.setWidthPercentage(100);
	tablecenterFM2.addCell(tableleftFM2);
	
	

	PdfPTable tablerightFM2 = new PdfPTable(1);
	tablerightFM2.setWidthPercentage(100);
	tablerightFM2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

	pl = new Paragraph(list.get(0).get(4), fontTableHeading);
	tablerightFM2.addCell(pl);
	PdfPTable tablecenterFM3 = new PdfPTable(1);
	tablecenterFM3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tablecenterFM3.setWidthPercentage(100);
	tablecenterFM3.addCell(tablerightFM2);
	
	
	
	
//	----------address last row
	
	PdfPTable tableleftFM66 = new PdfPTable(1);
	tableleftFM66.setWidthPercentage(100);
	tableleftFM66.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 
	Chunk a_hin2 = new Chunk("                                                ¼ tkjh gksus dh rkjh[k ls 5 lky rd oS/k "+"", font007);
	Chunk a_eng2 = new Chunk(" / valid upto 5 years from the date of issuance ) "+"  ", fontTableHeading007);
 
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	pl.add(a_hin2);
	pl.add(a_eng2);
	tableleftFM66.addCell(pl);
	tablecenterFM3.addCell(tableleftFM66);
	
//	----for watermark
//	finaltable.addCell(table);
//	tablecenterFM3.setTableEvent(new ImageBackgroundEvent(request));
//	
////	document.add(tablecenterFM3);
//	super.buildPdfMetadata(model, document, request);
  
	
	
	
	
	
	
	///////////// QR CODE
	 
	Image logo313 = null;
	try {
 		String ip = "";
		if (request != null) {
	        ip = request.getHeader("X-FORWARDED-FOR");
	        System.err.println("ip------fdfdsfdfd---------s"+ip);
	        if (ip == null || "".equals(ip)) {
	            ip = request.getRemoteAddr();
	            System.err.println("ip---------------s"+ip);
	        }
	    }
		Chunk nrh = new Chunk(list.get(0).get(2) +" ", fontTableHeading);
		System.err.println("check the nrh"+nrh);
		Chunk state_reg_no = new Chunk(list.get(0).get(6) +" ", fontTableHeading);
		System.err.println("check the state_reg_no"+state_reg_no);
		
		String qrCodeText ="NRH NUMBER: "+nrh+", STATE REGISTRATION NUMBER: "+state_reg_no;
		System.out.println("QR : "+ qrCodeText);		
		
		logo313 = com.lowagie.text.Image.getInstance(createQRImage(qrCodeText));
	} catch (BadElementException e) {
		
		e.getMessage();
	} catch (MalformedURLException e) {
		e.getMessage();
	} catch (IOException e) {
		e.getMessage();
	}
	
	logo313.setAlignment(Image.LEFT);
	logo313.scaleAbsoluteHeight(20);
	logo313.scaleAbsoluteWidth(20);
	logo313.scalePercent(35);  //logo2.scalePercent(22);
	//Chunk abc_h = new Chunk(logo313, 20, -1);
	
	
	
	///
	//Chunk abc_h = new Chunk(logo313, 20, -1);
	PdfPTable tableleftFM7 = new PdfPTable(1);
	tableleftFM7.setWidthPercentage(100);
	tableleftFM7.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	 
	//Chunk a_hin7 = new Chunk(" ¼ tkjh gksus dh rkjh[k ls 5 lky rd oS/k "+"", font);
	//Chunk a_eng7 = new Chunk("                                                                                           /President "+"  ", fontTableHeading);
 
	pl = new Paragraph("", fontTableHeading);
 	pl = new Paragraph("", fontTableHeading);
 	//pl.add(abc_h);
	//pl.add(a_eng7);
	tableleftFM7.addCell(pl);
	tablecenterFM3.addCell(tableleftFM7);
	
	//try
	PdfPTable tableleftFMj = new PdfPTable(3);
	tableleftFMj.setWidthPercentage(100);
	tableleftFMj.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
	
	
	
	tableleftFMj.addCell(pl);

	Paragraph prv4;
	
	
	
	 Chunk abc_h = new Chunk(logo313, 20, -1);
	 Chunk a_hin7 = new Chunk("                                                                                                                  v/;{k"+"", font);
	 Chunk a_eng7 = new Chunk(" /President \n                                                                                                                                                                                                              Board Of Ethics And Registration For Homoeopathy "+"  ", fontTableHeading89);
 	 Chunk a_hin9 = new Chunk("\n                                                                                                                          gksE;ksiSFkh vkpkj vkSj iaftdj.k cksMZ "+"", font);

 	 
 	 pl = new Paragraph("", fontTableHeading);
		pl = new Paragraph("", fontTableHeading);
		 
		prv4 = new Paragraph("", fontTableHeading);
		prv4.setIndentationRight(0);
		//pl = new Paragraph("", fontTableHeading);
	 
		pl.add(abc_h);
	 	pl.add(a_hin7);
		 pl.add(a_eng7);
		 pl.add(a_hin9);
		 tablecenterFM3.addCell(pl);
	
	tableleftFMj.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	tableleftFMj.setWidthPercentage(100);
	tablecenterFM3.addCell(tableleftFMj);
	
	
	
	
	
	
	pl = new Paragraph("", font);
 	pl = new Paragraph("", fontTableHeading);
// 	pl.add(abc_h);
//	pl.add(abc_s);
//	tableleftFM2.addCell(pl);
	
//	------for president
 	
	
	
	Font font2 = new Font(unicode,18,Font.NORMAL);
	Font fontTableHeading11 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 15, 1);
//	
	

//	tableleftFM6.add()
	
	
	PdfPTable tableleftFMff = new PdfPTable(1);
	tableleftFM.setWidthPercentage(100);
	tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);	
		PdfPCell cell123;
		cell123 = new PdfPCell();
		cell123.addElement(tableheader);
		cell123.addElement(tabledata);
//		cell123.addElement(new Paragraph("\n"));  //line use-for space
//		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(new Paragraph("\n"));
		cell123.addElement(cell1);
		cell123.addElement(tablecenterFMn);
		//cell123.addElement(tablecenterFMn1);
		cell123.addElement(tablecenterFM);
		cell123.addElement(tablecenterFM1);
		cell123.addElement(tablecenterFM2);
		cell123.addElement(tablecenterFM3);
		

//		cell123.addElement(abc1);
//		cell123.addElement(abc_h);
//		cell123.addElement(pre_hin);
//		cell123.addElement(abc_s);
		
		
		
		ph.setFont(fontTableHeading1);
		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(abc);
		
		cell123.addElement(cell2);
		cell123.addElement(new Paragraph("\n"));
//		cell123.addElement(abc);
		
//		cell123.addElement(new Paragraph("\n"));
		table.addCell(cell123);
		table.setTableEvent(new ImageBackgroundEvent(request));
	
	document.add(table);
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
			
			
			/////////////
//			Image logo = null;
		
//			try {
//				@SuppressWarnings("deprecation")
////				String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCISM.jpeg";
//				String dgis_logo2 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCH_WATERMARK_IMG.png";
//				this.image = Image.getInstance(dgis_logo2);
//				System.err.println("check dgis_logo "+dgis_logo2);
//				System.err.println("check dgis_logo this.image "+this.image);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			String s_phto = list.get(0).get(5);
			
			try {
				@SuppressWarnings("deprecation")
//				String dgis_logo = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCISM.jpeg";
//				String dgis_logo2 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCHLogo.png";
//				logo = Image.getInstance(dgis_logo2);
				
				String [] s_phto1 = list.get(0).get(7).split("/");
				File dir = new File("/srv/Document/");
				String fname = dir.getAbsolutePath() + File.separator + "" + s_phto1[3]; // type+"_" +
				System.err.println("fnae----"+fname);
				
				if(fname == "/srv/Document/") {
					System.err.println("if--"+fname);
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
					this.image = Image.getInstance(fullPath);
					
				}
				else {
					 System.err.println("else--"+fname);
					 this.image = Image.getInstance(fname);
					 System.err.println("else--logo2--"+this.image);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
//			logo.setAlignment(Image.MIDDLE);
//			logo.scaleAbsoluteHeight(100);
//			logo.scaleAbsoluteWidth(80);
//			logo.scalePercent(100);
			
//			Chunk chunkimage = new Chunk(logo, 50, 2);
			
//			Phrase ph = new Phrase();
//			ph.add(chunkimage);
			
			
			//////

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

//			this.image = img;

			try {

				
				PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
//				--for resize image
				image.setAbsolutePosition(210,170);
				image.scaleToFit(760f,760f);
				image.scaleAbsolute(790, 640);
				cb.addImage(image, false);

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

				int last =  (int) table.getRowHeight(0);

				Phrase p = new Phrase();

				//p.add(String.valueOf(page));

				float width = ColumnText.getWidth(p);

				ColumnText.showTextAligned(cb, PdfContentByte.ALIGN_RIGHT, p, cb.getPdfDocument().right() - width,
						cb.getPdfDocument().top() + 9, 0);

				page += 1;
				System.err.println("first - "+first);
				while (last > 0) {
					System.err.println("first - "+first+" last - "+last);
//					image.setAbsolutePosition(30, last);
//
//					cb.addImage(image, false);

					last -= 30;
				}

			} catch (DocumentException e) {

				throw new ExceptionConverter(e);

			}
		}
	}
	
	

	
}
