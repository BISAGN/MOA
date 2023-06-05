package com.AyushEdu.config;

import java.io.File;
import java.text.ParseException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
@Controller
public class EmailConfig {
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean SendMail(HttpServletRequest request, String email,String role, String subject, String main, String followUp,
			String attachment_name, String attachment) throws ParseException {
		MailHTML html = new MailHTML();
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			

			String text = main;
			String note = followUp;

			// SET MAIN CONTENT IN MAIN ALONG WITH FOLLOWUP LINE
			html.setHTML(role,text, note);

			String htmlMsg = html.getHTML();

			// SET EMAIL WHOM TO SEND
			helper.setTo(email);

			// SET SUBJECT TO EMAIL
			helper.setSubject(subject);

			// SET ALL CONTENT TO MAIN EMAIL
			helper.setText(htmlMsg, true);

			// SET IMAGES AND ICONS TO MAIL FORMATE
			String comman_image = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "email"
					+ File.separator + "images" + File.separator;

			String[] fileToAttach = { "grid-logo.png", "email-template-img.png", "ayush-grid.png", "efb-icon.png",
					"etweet-icon.png", "einsta-icon.png", "epinit-icon.png", "elinkdin-icon.png", "ewp-icon.png",
					"ayushgridleaf.png" };

			for (int i = 0; i < fileToAttach.length; i++) {
				FileSystemResource file = new FileSystemResource(new File(comman_image + fileToAttach[i]));
				helper.addInline("logo" + (i + 1), file);
			}

			// SET ATTACHMENTS TO SEND WITH EMAIL IF REQUIRED
			if (!attachment.equals("")) {
				FileSystemResource file = new FileSystemResource(new File(attachment));
				helper.addAttachment(attachment_name, file);
			}

			// SEND EMAIL
//			mailSender.send(mimeMessage);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean SendMailNew(HttpServletRequest request,DynamicMailFormatGen DE) throws ParseException {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			String text = DE.getMainBody();
			String note = DE.getFooter();
			String role = DE.getRole();
			String name = DE.getName();

			// SET MAIN CONTENT IN MAIN ALONG WITH FOLLOWUP LINE
			
			if(role.equals("NCISM")) {
			MailHTMLNCISM htmlNCISM = new MailHTMLNCISM();
			String htmlMsg = "";

			htmlNCISM.setHTML(name,text, note);
//			System.err.print("HTML CONTENT FOR DESIGN---------------->"+htmlNCISM.getHTML());
			htmlMsg = htmlNCISM.getHTML();

			

			// SET EMAIL WHOM TO SEND
			helper.setTo(DE.getEmail());

			// SET SUBJECT TO EMAIL
			helper.setSubject(DE.getSubject());

			// SET ALL CONTENT TO MAIN EMAIL
			helper.setText(htmlMsg, true);

			// SET IMAGES AND ICONS TO MAIL FORMATE
			String comman_image = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "email"
					+ File.separator + "images" + File.separator;

			String[] fileToAttachNCISM = { "ncism_logo.png", "email-template-img.png", "grid-logo.png", "efb-icon.png",
					"etweet-icon.png", "einsta-icon.png", "epinit-icon.png", "elinkdin-icon.png", "ewp-icon.png",
					"ayushgridleaf.png" };

			for (int i = 0; i < fileToAttachNCISM.length; i++) {
				if(i != 6 && i != 7 && i != 8) {
				FileSystemResource file = new FileSystemResource(new File(comman_image + fileToAttachNCISM[i]));
				helper.addInline("logo" + (i + 1), file);
				}
			}

			// SET ATTACHMENTS TO SEND WITH EMAIL IF REQUIRED
			if (!DE.getAttachment().equals("")) {
				FileSystemResource file = new FileSystemResource(new File(DE.getAttachment()));
				helper.addAttachment(DE.getAttachmentName(), file);
			}
			}
			else {
				MailHTMLNCH htmlNCH = new MailHTMLNCH();
				String htmlMsg = "";

				htmlNCH.setHTML(name,text, note);
//				System.err.print("HTML CONTENT FOR DESIGN---------------->"+htmlNCH.getHTML());
				htmlMsg = htmlNCH.getHTML();
				
				// SET EMAIL WHOM TO SEND
				helper.setTo(DE.getEmail());

				// SET SUBJECT TO EMAIL
				helper.setSubject(DE.getSubject());

				// SET ALL CONTENT TO MAIN EMAIL
				helper.setText(htmlMsg, true);

				// SET IMAGES AND ICONS TO MAIL FORMATE
				String comman_image = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator + "email"
						+ File.separator + "images" + File.separator;

				String[] fileToAttachNCH = { "nchlogo.png", "email-template-img.png", "grid-logo.png", "efb-icon.png",
						"etweet-icon.png", "einsta-icon.png", "epinit-icon.png", "elinkdin-icon.png", "ewp-icon.png",
						"ayushgridleaf.png" };

				for (int i = 0; i < fileToAttachNCH.length; i++) {
					if(i != 6 && i != 7 && i != 8) {
					FileSystemResource file = new FileSystemResource(new File(comman_image + fileToAttachNCH[i]));
					helper.addInline("logo" + (i + 1), file);
					}
				}

				// SET ATTACHMENTS TO SEND WITH EMAIL IF REQUIRED
				if (!DE.getAttachment().equals("")) {
					FileSystemResource file = new FileSystemResource(new File(DE.getAttachment()));
					helper.addAttachment(DE.getAttachmentName(), file);
				}
				
				htmlNCH.setHTML(role,text, note);
				htmlMsg = htmlNCH.getHTML();

			}
			// SEND EMAIL
//			mailSender.send(mimeMessage);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
////////////////////new mail for AYUSH EDU /////
//	public boolean SendMailForAyushEdu(HttpServletRequest request, DynamicMailFormatGen DE) throws ParseException {
//
//		try {
////MimeMessage mimeMessage = mailSender.createMimeMessage();
////MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//			String ema_from = "ncog@digitalindia.gov.in";
//			String pwd_from = "Ncog@1948";
//
////String ema_from = "ayushgrid04@gmail.com";
////String pwd_from = "qpdubsdkjtelddyd";
//
//			Properties properties = new Properties();
//			properties.put("mail.smtp.host", "10.194.81.69");
//			properties.put("mail.smtp.port", "25");
////properties.put("mail.smtp.host", "smtp.gmail.com");
////properties.put("mail.smtp.port", "587");
//
////properties.put("mail.smtp.proxySet", "true");
////properties.put("mail.smtp.proxy.host", "10.]=194.81.69");
////properties.put("mail.smtp.proxy.port", "8080");
//			properties.put("mail.smtp.auth", "true");
//			properties.put("mail.smtp.starttls.enable", true);
//			properties.put("mail.user", ema_from);
//			properties.put("mail.password", pwd_from);
//
//			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//			properties.put("mail.debug", true);
//			properties.put("proxySet", true);
//			properties.put("socksProxyHost", "10.194.81.69");
//			properties.put("socksProxyPort", "8080");
//
//			Authenticator auth = new Authenticator() {
//				public PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(ema_from, pwd_from);
//				}
//			};
//
//			Session session = Session.getInstance(properties, auth);
//			Message msg = new MimeMessage(session);
//
//			try {
//				msg.setFrom(new InternetAddress(ema_from));
//			} catch (AddressException e1) {
//				e1.getMessage();
//			} catch (MessagingException e1) {
//				e1.getMessage();
//			}
//
//			String text = DE.getMainBody();
//			String note = DE.getFooter();
//			String role = DE.getRole();
//			String name = DE.getName();
//
////SET MAIN CONTENT IN MAIN ALONG WITH FOLLOWUP LINE
//
//			if (role.equals("NCISM")) {
//				MailHTMLNCISM htmlNCISM = new MailHTMLNCISM();
//				String htmlMsg = "";
//
//				htmlNCISM.setHTML(name, text, note);
////System.err.print("HTML CONTENT FOR DESIGN---------------->"+htmlNCISM.getHTML());
//				htmlMsg = htmlNCISM.getHTML();
//
////SET EMAIL WHOM TO SEND
////helper.setTo(DE.getEmail());
//
////SET SUBJECT TO EMAIL
////helper.setSubject(DE.getSubject());
//
////SET ALL CONTENT TO MAIN EMAIL
////helper.setText(htmlMsg, true);
//
////SET IMAGES AND ICONS TO MAIL FORMATE
//				String comman_image = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator
//						+ "email" + File.separator + "images" + File.separator;
//
//				String[] fileToAttachNCISM = { "ncism_logo.png", "email-template-img.png", "grid-logo.png",
//						"efb-icon.png", "etweet-icon.png", "einsta-icon.png", "epinit-icon.png", "elinkdin-icon.png",
//						"ewp-icon.png", "ayushgridleaf.png" };
//
////creates message part
//				MimeBodyPart messageBodyPart = new MimeBodyPart();
//				messageBodyPart.setContent(htmlMsg, "text/html");
//
//				for (int i = 0; i < fileToAttachNCISM.length; i++) {
//					if (i != 6 && i != 7 && i != 8) {
//						FileSystemResource file = new FileSystemResource(new File(comman_image + fileToAttachNCISM[i]));
////helper.addInline("logo" + (i + 1), file);
//						messageBodyPart.attachFile(new File(comman_image + fileToAttachNCISM[i]));
//					}
//				}
//
////SET ATTACHMENTS TO SEND WITH EMAIL IF REQUIRED
//				if (!DE.getAttachment().equals("")) {
//					FileSystemResource file = new FileSystemResource(new File(DE.getAttachment()));
////helper.addAttachment(DE.getAttachmentName(), file);
//					messageBodyPart.attachFile(new File(DE.getAttachment()));
//				}
//
//				try {
//					InternetAddress[] toAddresses1 = { new InternetAddress(DE.getEmail()) };
//					msg.setRecipients(Message.RecipientType.TO, toAddresses1);
//					msg.setSubject(DE.getSubject());
//					msg.setSentDate(new Date());
//
//					// creates multi-part
//					Multipart multipart = new MimeMultipart();
//					multipart.addBodyPart(messageBodyPart);
//
//					// sets the multi-part as e-mail's content
//					msg.setContent(multipart);
//				} catch (AddressException e2) {
//					e2.getMessage();
//				} catch (MessagingException e1) {
//					e1.getMessage();
//				}
//			} else {
//				MailHTMLNCH htmlNCH = new MailHTMLNCH();
//				String htmlMsg = "";
//
//				htmlNCH.setHTML(name, text, note);
////System.err.print("HTML CONTENT FOR DESIGN---------------->"+htmlNCH.getHTML());
//				htmlMsg = htmlNCH.getHTML();
//
////SET EMAIL WHOM TO SEND
////helper.setTo(DE.getEmail());
//
////SET SUBJECT TO EMAIL
////helper.setSubject(DE.getSubject());
//
////SET ALL CONTENT TO MAIN EMAIL
////helper.setText(htmlMsg, true);
//
////SET IMAGES AND ICONS TO MAIL FORMATE
//				String comman_image = request.getRealPath("/") + "admin" + File.separator + "js" + File.separator
//						+ "email" + File.separator + "images" + File.separator;
//
//				String[] fileToAttachNCH = { "nchlogo.png", "email-template-img.png", "grid-logo.png", "efb-icon.png",
//						"etweet-icon.png", "einsta-icon.png", "epinit-icon.png", "elinkdin-icon.png", "ewp-icon.png",
//						"ayushgridleaf.png" };
//
////creates message part
//				MimeBodyPart messageBodyPart = new MimeBodyPart();
//				messageBodyPart.setContent(text + note, "text/html");
//
//				for (int i = 0; i < fileToAttachNCH.length; i++) {
//					if (i != 6 && i != 7 && i != 8) {
//						FileSystemResource file = new FileSystemResource(new File(comman_image + fileToAttachNCH[i]));
////helper.addInline("logo" + (i + 1), file);
////		messageBodyPart.attachFile(new File(comman_image + fileToAttachNCH[i]));
//					}
//				}
//
////SET ATTACHMENTS TO SEND WITH EMAIL IF REQUIRED
//				if (!DE.getAttachment().equals("")) {
//					FileSystemResource file = new FileSystemResource(new File(DE.getAttachment()));
////helper.addAttachment(DE.getAttachmentName(), file);
////	messageBodyPart.attachFile(new File(DE.getAttachment()));
//				}
//
//				htmlNCH.setHTML(role, text, note);
//				htmlMsg = htmlNCH.getHTML();
//
//				try {
//					InternetAddress[] toAddresses1 = { new InternetAddress(DE.getEmail()) };
//					msg.setRecipients(Message.RecipientType.TO, toAddresses1);
//					msg.setSubject(DE.getSubject());
//					msg.setSentDate(new Date());
//
//					// creates multi-part
//					Multipart multipart = new MimeMultipart();
//					multipart.addBodyPart(messageBodyPart);
//
//					// sets the multi-part as e-mail's content
//					msg.setContent(multipart);
//				} catch (AddressException e2) {
//					e2.getMessage();
//				} catch (MessagingException e1) {
//					e1.getMessage();
//				}
//
//			}
////SEND EMAIL
////mailSender.send(mimeMessage);
//			try {
//				Transport.send(msg);
//				return true;
//			} catch (Exception e) {
//				return false;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
}
