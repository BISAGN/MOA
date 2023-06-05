package com.AyushEdu.controller.Exp_Excel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
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
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import com.twilio.rest.monitor.v1.Alert;

public class DownloadPraViewPrint extends AbstractPdfView{
	
	 String Type = "";
	    List<String> TH;
	    ArrayList<String> TH1;
	    String Heading = "";
	    String username = "";
	    int total = 0;

	    List<ArrayList<String>>listS;
	    ArrayList<ArrayList<String>> alists;
	    final static String USER_PASSWORD = "user";
	    final static String OWNER_PASSWORD = "owner";
	    public static final String ENCRYPTED_PDF = "C:\\Users\\BISAG\\Desktop\\Beehive Screen\\beehive_reset_pwd_form.pdf";
	    public DownloadPraViewPrint(String Heading,List<String> TH, String username, String foot, List<ArrayList<String>> listS, ArrayList<ArrayList<String>> alists){
	           
	    	

	            this.TH = TH;
	            this.Heading = Heading;
	            this.username = username;        
	            this.total = total;
	            this.listS=listS;
	            this.alists=alists;
	    }
	  
	    
	    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
	        document.open();
	        Font fontTableHeading1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 1);
	        Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 14, 1);
	    
	        Image logo = null;
	        try {
	                @SuppressWarnings("deprecation")
	                String dgis_logo =  request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCHLogo.png";
	                logo = Image.getInstance(dgis_logo);
	        } catch (BadElementException e) {
	                e.printStackTrace();
	        } catch (MalformedURLException e) {
	                e.printStackTrace();
	        } catch (IOException e) {
	                e.printStackTrace();
	        }
	        logo.setAlignment(Image.MIDDLE);
	        logo.scaleAbsoluteHeight(6);
	        logo.scaleAbsoluteWidth(6);
	        logo.scalePercent(28);
	        Chunk chunk = new Chunk(logo, 0, -4);

//	        Image logo2 = null;
//	        try {
//	                @SuppressWarnings("deprecation")
////	                Image idimage = Image.getInstance(listS.get(0).get(13).toString());
////	                String indian_Army =  request.getRealPath("/")+"admin"+File.separator+"js"+File.separator+"miso"+File.separator+"images"+File.separator+"golden_katar.png";
////	                logo2 = Image.getInstance(indian_Army);//"d://indianarmy_smrm5aaa.jpg"
////	                String dgis_logo2 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "NCHLogo.png";
//	                logo2 = Image.getInstance(listS.get(0).get(13).toString());
//	        } catch (BadElementException e) {
//	                e.printStackTrace();
//	        } catch (MalformedURLException e) {
//	                e.printStackTrace();
//	        } catch (IOException e) {
//	                e.printStackTrace();
//	        }
	        
	        Image logo2;
	       
	        Chunk chunk2 = null;
	      
	         String imgg = listS.get(0).get(13) ;
	         
	        System.err.println("imageeeeeeeeeeeeeeee----------"+listS.get(0).get(13));
	        
	        try {
	        if(imgg!=null && imgg!="null" && imgg!="") {
 				System.err.println("innnnnn");
				logo2 = Image.getInstance(listS.get(0).get(13));
				logo2.setBorder(Rectangle.NO_BORDER);
				logo2.setAlignment(20);
				logo2.scaleAbsoluteHeight(2); 
				logo2.scaleAbsoluteWidth(2); 
				logo2.scalePercent(20);
//				--18-04-2023 URMIK for fix image size in view
				logo2.scaleToFit(70f, 70f);
				chunk2 = new Chunk(logo2, 10, -4);
       } 
	        else {
	        	System.err.println("outtttt");
	        	imgg = request.getRealPath("/") + "admin" + File.separator  + "js" + File.separator 	+ "images" + File.separator	+ "No_Image.jpg";
	        	System.err.println("img----------"+imgg);
	        	logo2 = Image.getInstance(imgg);
				logo2.setBorder(Rectangle.NO_BORDER);
				logo2.setAlignment(20);
				logo2.scaleAbsoluteHeight(2); 
				logo2.scaleAbsoluteWidth(2); 
				logo2.scalePercent(20);
//				--18-04-2023 URMIK for fix image size in view
				logo2.scaleToFit(70f, 70f);
				chunk2 = new Chunk(logo2, 10, -4);
	        }
				//else {
//	        	System.err.println("outtttt");
//				String no_image = request.getRealPath("/") + "admin" + File.separator  + "js" + File.separator 	+ "images" + File.separator	+ "No_Image.jpg";
//				//Image no_img = Image.getInstance(null);
//				 String urlOfImage = no_image; 
//             //Add Image from some URL
// 	        }

	        } catch (BadElementException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        
	        
	        
	        //parth
//	        Image logo3 = null;
//	        String s_phto = listS.get(0).get(1);
//	        String [] s_phto1 = s_phto.split("/");
//			System.err.println("s_phto1[2]"+s_phto1[3]);
//		
//				@SuppressWarnings("deprecation")
//				File dir = new File("/srv/Document/");
//				String fname = dir.getAbsolutePath() + File.separator + "" + s_phto1[3]; // type+"_" +
//				System.err.println("fnae----"+fname);
//				
//				if(fname == "/srv/Document/") {
//					System.err.println("if--"+fname);
//					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					try {
//						logo2 = Image.getInstance(fullPath);
//					} catch (BadElementException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (MalformedURLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//				else {
//					 System.err.println("else--"+fname);
//					try {
//						logo2 = Image.getInstance(fname);
//					} catch (BadElementException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (MalformedURLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					 System.err.println("else--logo2--"+logo2);
//				}
	        
	        //
			
			
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
			Chunk fonttest1 = new Chunk("", font1);
			Chunk fonttest3 = new Chunk("", font);
			fonttest3.setUnderline(1.1f, -1f);
	        
	        
	        
	        Chunk underline = new Chunk("", fontTableHeading1);
	        underline.setUnderline(0.1f, -2f);
	        
	        Chunk underline3 = new Chunk("NATIONAL COMMISSION FOR HOMOEOPATHY", fontTableHeadingMainHead);
	        underline3.setUnderline(0.1f, -2f);
	        
	        Chunk underline2 = new Chunk("NATIONAL REGISTER FOR HOMOEOPATHY", fontTableHeadingMainHead);
	        underline2.setUnderline(0.1f, -2f);
	     
	        Chunk glue = new Chunk(new VerticalPositionMark());
	        PdfPTable table3 = new PdfPTable(3);
	        table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        Phrase p = new Phrase(underline);
	        p.add("\n");
	    	p.add(fonttest);
			p.add("\n");
	        p.add("\n");
	        p.add(underline3);
	        p.add("\n");
	        p.add("\n");
	        p.add(underline2);
	        p.add("\n");
	        p.add("\n");
	
			
			p.setFont(fontTableHeading1);
			Font fontTableHeading2 = null;
			p.setFont(fontTableHeading2);
			Font fontTableHeading3 = null;
			p.setFont(fontTableHeading3);

	        
//			Chunk underline11 = new Chunk("जवाहर लाल नेहरू भारतीय चिक्तिशा  एवं " , fontTableHeading1);

	        p.setFont(fontTableHeading1);
	        Paragraph cell = new Paragraph(p);
	        cell.setAlignment(Element.ALIGN_CENTER);
	        float[] relativeWidths;
	        int colunmSize = 3;
	        relativeWidths = new float[colunmSize];
	        Arrays.fill(relativeWidths, 0, colunmSize, 1);
	        relativeWidths[1] = (float) 2.50;
	        table3.addCell(new Phrase(chunk));
	        table3.addCell(cell);
	       
	       // if(imgg!=null && imgg!="null" && imgg!="") {
	        	 table3.addCell(new Phrase(chunk2));
	      //  }
	        
	        
	       
	        
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
//	        Chunk underline1 = new Chunk("The Enrollment in National Register for\n"
//	        		+ "Homoeopathy(NRH) shall remain valid till the\n"
//	        		+ "name/registration number of the applicant\n"
//	        		+ "remains live in the State Register.", fontTableHeading1);
//	        underline1.setUnderline(0.1f, -2f);
//	        Phrase p1 = new Phrase(""+glue);
//	        p1.add(underline1);
//	        p.add(glue);
//	        p1.setFont(fontTableHeading1);        
//	        HeaderFooter footer = new HeaderFooter(p1, false);
//	        footer.setAlignment(Element.ALIGN_CENTER);
//	        footer.setBorder(Rectangle.TOP);
//	        document.setFooter(footer);
	        ///document.setPageCount(1);
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
			
			// TODO Auto-generated method stub
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""+file_name+".pdf\"");
			
			
			PdfPTable table = new PdfPTable(1);
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Font fontTableHeadingMainHead = FontFactory.getFont("Arial", BaseFont.IDENTITY_H, false, 12, 0);
		
			
			
//			Paragraph p=new Paragraph();
		
			Chunk underline3 = new Chunk("CTPART I", fontTableHeadingMainHead);
			underline3.setUnderline(0.1f, -2f);
			

			
			
			
			Font fontTableHeading = FontFactory.getFont(FontFactory.TIMES_BOLD, 12); 

			Font fontTableValue = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
			
			
			
		Paragraph p=new Paragraph();
			
			Paragraph pl;
			Paragraph plv;
			Paragraph pr;
			Paragraph pr1;
			Paragraph pr2;
			Paragraph pr3;
			Paragraph pr4 = null;
			Paragraph prv;
			
			
			PdfPTable tablemainFM = new PdfPTable(2);
			tablemainFM.setWidthPercentage(100);
			tablemainFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			PdfPTable tableleftFM = new PdfPTable(2);
			tableleftFM.setWidthPercentage(100);
			tableleftFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			
//			PdfPTable tablerightFM = new PdfPTable(1);
//			tablerightFM.setWidthPercentage(100);
//			tablerightFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			tablerightFM.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			
//			for (int i = 0; i < listS.size(); i++) {
//				List<String> l = listS.get(i);
//				System.err.println("eeeeeeeee"+listS.size());
//				for (int j = 0; j < l.size()-1; j++) {
//					if(j==2) {
//					p = new Paragraph(l.get(j),fontTableValue);
//			try {
//				
//			
//			 Image idimage = Image.getInstance(listS.get(0).get(13));
//			 idimage.setBorder(Rectangle.NO_BORDER);
//			 idimage.setAlignment(20);
//			  idimage.scaleAbsoluteHeight(2); 
//			  idimage.scaleAbsoluteWidth(2); 
//			  idimage.scalePercent(1);
//			  
//			  	PdfPCell blank_cell1 = new PdfPCell();
//			  	blank_cell1.setBorder(Rectangle.NO_BORDER);
//				blank_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//				blank_cell1.addElement(idimage);
//				
//				new Chunk(idimage, 1000, 2);
//				
//			  }
//			  catch (Exception e) {
//					e.printStackTrace();
//					
//					String no_image = request.getRealPath("/") + "admin" + File.separator  + "js" + File.separator 	+ "images" + File.separator	+ "No_Image.jpg";
//					Image no_img = Image.getInstance(no_image);
//					no_img.scaleAbsoluteHeight(5); 
//					no_img.scaleAbsoluteWidth(50); 
//					no_img.scalePercent(20);
//					 PdfPCell blank_cell1;
//						blank_cell1 = new PdfPCell();
//						blank_cell1.addElement(p);
//						blank_cell1.addElement(no_img);
//						table.addCell(blank_cell1);
//				}
//					}else {
//						
//						table.addCell(l.get(j));
//					}
//						}
//				}
				
//				PdfPCell blank_cell2 = new PdfPCell();
//			  	blank_cell2.setBorder(Rectangle.NO_BORDER);
//				blank_cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				blank_cell2.addElement(idimage);
			
			
			
			
//			Image logo3 = null;
//			
//			try {
//				@SuppressWarnings("deprecation")
//				
//				String dgis_logo3 = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "images"+ File.separator + "Bronze-Medal.png";
//				logo3 = Image.getInstance(dgis_logo3);
//				
//			} catch (BadElementException e) {
//				e.printStackTrace();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			
//			logo3.setAlignment(Image.MIDDLE);
//			logo3.scaleAbsoluteHeight(13);
//			logo3.scaleAbsoluteWidth(15);
//			logo3.scalePercent(12);
			
//			Chunk chunk = new Chunk(logo3, 0, 2);
			
		
			
			
			
//			Paragraph a10b = new Paragraph("(b)");
//			Paragraph a11b = new Paragraph("Aadhar Number");
//			PdfPCell cella11b =new PdfPCell(a11b);
//			cella11b.setHorizontalAlignment(Element.ALIGN_LEFT);
//			cella11b.setBorder(Rectangle.NO_BORDER);
//			cella11b.setPaddingBottom(10f);
//			Paragraph a12b = new Paragraph(":");
//			Paragraph a13b = new Paragraph(l.get(20));
//			PdfPCell cella13b =new PdfPCell(a13b);
//			cella13b.setHorizontalAlignment(Element.ALIGN_LEFT);
//			cella13b.setBorder(Rectangle.NO_BORDER);
//			cella13b.setPaddingBottom(10f);
//			tabledata.addCell(a10b);
//			tabledata.addCell(cella11b);
//			tabledata.addCell(a12b);
//			tabledata.addCell(cella13b);
		



			
			pl = new Paragraph("NRH Enrollment Number", fontTableHeading);

		//	tablemainFM.addCell(pl);
			PdfPCell cella0 =new PdfPCell(pl);
			cella0.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella0.setBorder(Rectangle.NO_BORDER);
			cella0.setPaddingBottom(10f);

//			if(listS.get(0).get(0) == null ) {
//				plv= new Paragraph("null", fontTableValue);
//			} else {
			plv = new Paragraph(listS.get(0).get(0), fontTableValue);
//			}
		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb0 =new PdfPCell(plv);
			cellb0.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb0.setBorder(Rectangle.NO_BORDER);
			cellb0.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);
			

		//	tablerightFM.addCell(blank_cell1);
			
			pl = new Paragraph("Name of Professional", fontTableHeading);

			tablemainFM.addCell(pl);
			
			PdfPCell cella1 =new PdfPCell(pl);
			cella1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella1.setBorder(Rectangle.NO_BORDER);
			cella1.setPaddingBottom(10f);
			
//			tablerightFM.addCell(pr4);

			plv = new Paragraph(listS.get(0).get(1), fontTableValue);

			//tablemainFM.addCell(plv);
			PdfPCell cellb1 =new PdfPCell(plv);
			cellb1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb1.setBorder(Rectangle.NO_BORDER);
			cellb1.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);
			

			pl = new Paragraph("Father's Name", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella2 =new PdfPCell(pl);
			cella2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella2.setBorder(Rectangle.NO_BORDER);
			cella2.setPaddingBottom(10f);

//			plv = new Paragraph(listS.get(0).get(2).toString() + " (" + listS.get(0).get(3).toString() +  ")", fontTableValue);
//			System.err.println("dddddddddddddddddddddddd"+listS.get(0).get(2));
//			if(listS.get(0).get(2) == null ) {
//				System.err.println("nulllllllllll");
//				plv= new Paragraph("null", fontTableValue);
//			} else {
			
			plv = new Paragraph(listS.get(0).get(2), fontTableValue);
//			}

	//		tablemainFM.addCell(plv);
			
			PdfPCell cellb2 =new PdfPCell(plv);
			cellb2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb2.setBorder(Rectangle.NO_BORDER);
			cellb2.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);

			
			
			pl = new Paragraph("Present Correspondence address", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella3 =new PdfPCell(pl);
			cella3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella3.setBorder(Rectangle.NO_BORDER);
			cella3.setPaddingBottom(10f);
			

			plv = new Paragraph(listS.get(0).get(3), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb3 =new PdfPCell(plv);
			cellb3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb3.setBorder(Rectangle.NO_BORDER);
			cellb3.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);


			
			
		
			
			pl = new Paragraph("Permanent Address", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella4 =new PdfPCell(pl);
			cella4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella4.setBorder(Rectangle.NO_BORDER);
			cella4.setPaddingBottom(10f);

			plv = new Paragraph(listS.get(0).get(4), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb4 =new PdfPCell(plv);
			cellb4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb4.setBorder(Rectangle.NO_BORDER);
			cellb4.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);

			
			
			pl = new Paragraph("E-mail Id", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella5 =new PdfPCell(pl);
			cella5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella5.setBorder(Rectangle.NO_BORDER);
			cella5.setPaddingBottom(10f);


			plv = new Paragraph(listS.get(0).get(5), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb5 =new PdfPCell(plv);
			cellb5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb5.setBorder(Rectangle.NO_BORDER);
			cellb5.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);

			
			
			pl = new Paragraph("Date of Birth", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella6 =new PdfPCell(pl);
			cella6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella6.setBorder(Rectangle.NO_BORDER);
			cella6.setPaddingBottom(10f);

			plv = new Paragraph(listS.get(0).get(6), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb6 =new PdfPCell(plv);
			cellb6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb6.setBorder(Rectangle.NO_BORDER);
			cellb6.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);
			
			
			pl = new Paragraph("Nationality", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella7 =new PdfPCell(pl);
			cella7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella7.setBorder(Rectangle.NO_BORDER);
			cella7.setPaddingBottom(10f);

			plv = new Paragraph(listS.get(0).get(7), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb7 =new PdfPCell(plv);
			cellb7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb7.setBorder(Rectangle.NO_BORDER);
			cellb7.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);
			
			pl = new Paragraph("Name of Medical Degree or Diploma Obtained and University With The Month And Year if Passing Qualification", fontTableHeading);

		//	tablemainFM.addCell(pl);
			
			PdfPCell cella8 =new PdfPCell(pl);
			cella8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella8.setBorder(Rectangle.NO_BORDER);
			cella8.setPaddingBottom(10f);

			plv = new Paragraph(alists.get(0).get(0), fontTableValue);

		//	tablemainFM.addCell(plv);
			
			PdfPCell cellb8 =new PdfPCell(plv);
			cellb8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb8.setBorder(Rectangle.NO_BORDER);
			cellb8.setPaddingBottom(10f);
			tableleftFM.addCell(pl);
			tableleftFM.addCell(plv);
			


		//	tablerightFM.addCell("\n");
			

			
			pr = new Paragraph("State Registration Number", fontTableHeading);

	//		tablemainFM.addCell(pr);
			
			PdfPCell cella9 =new PdfPCell(pr);
			cella9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella9.setBorder(Rectangle.NO_BORDER);
			cella9.setPaddingBottom(10f);

			prv = new Paragraph(listS.get(0).get(8), fontTableValue);

		//	tablemainFM.addCell(prv);
			
			PdfPCell cellb9 =new PdfPCell(prv);
			cellb9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb9.setBorder(Rectangle.NO_BORDER);
			cellb9.setPaddingBottom(10f);
			tableleftFM.addCell(pr);
			tableleftFM.addCell(prv);

			pr = new Paragraph("Names(s) of The Register(National/State) ", fontTableHeading);

		//	tablemainFM.addCell(pr);
			
			PdfPCell cella10 =new PdfPCell(pr);
			cella10.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella10.setBorder(Rectangle.NO_BORDER);
			cella10.setPaddingBottom(10f);

			
//			prv = new Paragraph("", fontTableValue);
//			tablemainFM.addCell(prv);

			prv = new Paragraph(listS.get(0).get(9), fontTableValue);

		//	tablemainFM.addCell(prv);
			
			PdfPCell cellb10 =new PdfPCell(prv);
			cellb10.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb10.setBorder(Rectangle.NO_BORDER);
			cellb10.setPaddingBottom(10f);
			tableleftFM.addCell(pr);
			tableleftFM.addCell(prv);
			

			pr = new Paragraph("Name of Hospital or University With Complete Address For Purposes of Teaching or Research or Practice, of Medicine", fontTableHeading);

		//	tablemainFM.addCell(pr);
			
			PdfPCell cella11 =new PdfPCell(pr);
			cella11.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella11.setBorder(Rectangle.NO_BORDER);
			cella11.setPaddingBottom(10f);

			
//			prv = new Paragraph("", fontTableValue);
//			tablemainFM.addCell(prv);

			prv = new Paragraph(listS.get(0).get(10), fontTableValue);

		//	tablemainFM.addCell(prv);
			
			PdfPCell cellb11 =new PdfPCell(prv);
			cellb11.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb11.setBorder(Rectangle.NO_BORDER);
			cellb11.setPaddingBottom(10f);
			tableleftFM.addCell(pr);
			tableleftFM.addCell(prv);

			
			
			pr = new Paragraph("Name of Person in University or Hospital Who Will be Responsible For Legal Issues Regarding Patient Care Provided by Doctor Concerned", fontTableHeading);

		//	tablemainFM.addCell(pr);
			
			PdfPCell cella12 =new PdfPCell(pr);
			cella12.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella12.setBorder(Rectangle.NO_BORDER);
			cella12.setPaddingBottom(10f);
			
//			prv = new Paragraph("", fontTableValue);
//			prv = new Paragraph("", fontTableValue);
//			prv = new Paragraph("", fontTableValue);
//			prv = new Paragraph("", fontTableValue);
//			tablemainFM.addCell(prv);

			prv = new Paragraph(listS.get(0).get(11) , fontTableValue);

		//	tablemainFM.addCell(prv);
			
			PdfPCell cellb12 =new PdfPCell(prv);
			cellb12.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb12.setBorder(Rectangle.NO_BORDER);
			cellb12.setPaddingBottom(10f);
			tableleftFM.addCell(pr);
			tableleftFM.addCell(prv);
			
//			tablerightFM.addCell(pr4);

			
			
			pr = new Paragraph("Valid Upto", fontTableHeading);

		//	tablemainFM.addCell(pr);
			
			PdfPCell cella13 =new PdfPCell(pr);
			cella13.setHorizontalAlignment(Element.ALIGN_LEFT);
			cella13.setBorder(Rectangle.NO_BORDER);
			cella13.setPaddingBottom(10f);

			prv = new Paragraph(listS.get(0).get(12), fontTableValue);

		//	tablemainFM.addCell(prv);
			
			PdfPCell cellb13 =new PdfPCell(prv);
			cellb13.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellb13.setBorder(Rectangle.NO_BORDER);
			cellb13.setPaddingBottom(10f);
			tableleftFM.addCell(pr);
			tableleftFM.addCell(prv);
			
			
//			pr = new Paragraph("The Enrollment in National Register for Homoeopathy(NRH) shall remain valid till the name/registration number of the applicant remains live in the State Register.");
//
//			tablerightFM.addCell(pr);
//			
//			pr = new Paragraph("This is a computer generated document, no signature required.");
//
//			tablerightFM.addCell(pr);
//			
//			pr = new Paragraph("Above information is subject to verification from the data available on NCH website:www.nch.org.in.");
//
//			tablerightFM.addCell(pr);
//
			Font zapfdingbats = new Font();
			Chunk bullet = new Chunk("\u2022", zapfdingbats);
			
			
			
			pr1 = new Paragraph(bullet+" The Enrollment in National Register for Homoeopathy(NRH) shall remain valid till the "+"\n   "+" name/registration number of the applicant remains live in the State Register.");
			pr2 = new Paragraph(bullet+" This is a computer generated document, no signature required.");
			pr3 = new Paragraph(bullet+" Above information is subject to verification from the data available on NCH "+"\n   "+" website:www.nch.org.in.");
			
			PdfPTable tablecenterFM = new PdfPTable(1);
			tablecenterFM.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tablecenterFM.setWidthPercentage(100);
			tablecenterFM.addCell(tableleftFM);
		//	tablecenterFM.addCell(tablerightFM);
//			tablecenterFM.addCell(tablemainFM);
			
			
			document.add(new Phrase("\n"));
			document.add(tablecenterFM);
	//		document.add(tablemainFM);
			document.add(pr1);
			document.add(pr2);
			document.add(pr3);
			
//			document.add(new Phrase("\n"));
			
		
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

				// System.out.println("IP==" + ip);

				Date now = new Date();

				String dateString = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss", Locale.ENGLISH).format(now);

				String watermark = " Generated by " + username + " on " + dateString + " with IP " + ip;

				Image img = null;

				BufferedImage bufferedImage = new BufferedImage((int) table.getTotalWidth(), 30,
						BufferedImage.TYPE_INT_ARGB);

				Graphics graphics = bufferedImage.getGraphics();

				graphics.setColor(Color.white);

				graphics.setFont(new java.awt.Font("Arial Black", Font.NORMAL, 12));

				graphics.drawString(watermark + watermark + watermark, 0, 20);
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

					System.out.println("width == " + widths.length);

					System.out.println("heights == " + heights.length);

					System.out.println("headerRows == " + headerRows);

					System.out.println("rowStart == " + rowStart);

					System.out.println("canvases == " + canvases.length);

					System.out.println("table height == " + table.getRowHeight(0));

					System.out.println("table width == " + table.getTotalWidth());

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
					if (tableWidth == 1119) {

						first = 1500;

					}

					int loop = (int) table.getRowHeight(0);

					int last = first - (int) table.getRowHeight(0);

					System.out.println(first + "==" + loop + "==" + last);

					System.out.println(" cb.getPdfDocument( )==" + cb.getPdfDocument().getPageNumber());

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
