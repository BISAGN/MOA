package com.AyushEdu.controller.Registration;


import java.io.IOException;
import java.net.Authenticator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendMailController {
	
	///----------- generate random integer ----------------------
		static final String AB = "123456789";
		static SecureRandom rnd = new SecureRandom();

		public static String randomString(int len) {
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++)
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			return sb.toString();
		}

//		@RequestMapping(value = "/getotpEmail", method = RequestMethod.POST)
//		@ResponseBody
//		public static String getotpEmail(HttpServletRequest request,HttpServletResponse response,@RequestParam String email,@RequestParam String username) {
//			String userName =username.toUpperCase(); //request.getParameter("recr_name");
//			String emailto =email; 
//			String temp = randomString(4);
//			System.out.println("----otp---0---"+temp);
//			String msg1 = "   Dear "+userName+",<br><br> \r\n" + 
//					"   Your OTP for registration with Central Reserve Police Force [CRPF] is "+temp+". "+
//					"	<br>Please enter it in the box provided and continue with the process of registration and application.<br>\r\n" + 
//					"	<br><br><br><br><br><br>Thanks,<br>\r\n" + 
//					"	Central Reserve Police Force [CRPF]";
//			
//			HttpSession session1 = request.getSession();
//		    session1.setAttribute("cotpEmail", temp);
//		    String cotpSession =  request.getSession().getAttribute("cotpEmail").toString();
//			System.out.println("cotpSession==" + cotpSession);
//			System.out.println(" "+msg1);
//			Properties properties = new Properties();
//		    properties.put("mail.smtp.host", "10.247.102.51");
//		    properties.put("mail.smtp.port",  "25");
//		    properties.put("mail.smtp.auth", "true");
//		    properties.put("mail.smtp.starttls.enable", "false");
//		    properties.put("mail.user", "bro-rtgcen@nic.in");
//		    properties.put("mail.password", "Rtg@cen#2020");
//		      
//		    Authenticator auth = new Authenticator() {
//		    	public PasswordAuthentication getPasswordAuthentication() {
//		    		return new PasswordAuthentication("bro-rtgcen@nic.in", "Rtg@cen#2020");
//		    	}
//		    };	
//	      
//	      Session session = Session.getInstance(properties, auth);
//
//	      // creates a new e-mail message
//	      Message msg = new MimeMessage(session);				
//	      try {
//			msg.setFrom(new InternetAddress("bro-rtgcen@nic.in"));
//		} catch (AddressException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (MessagingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	      
//	     try {
//	    	 InternetAddress[] toAddresses1={ new InternetAddress(emailto)};
//			 msg.setRecipients(Message.RecipientType.TO, toAddresses1);
//			 msg.setSubject("Verification Code for CRPF Registration");
//			 msg.setSentDate(new Date());
//
//		      // creates message part
//		      MimeBodyPart messageBodyPart = new MimeBodyPart();
//		      messageBodyPart.setContent("<h4>"+msg1+"</h4>", "text/html");
//		   		        	        
//		      // creates multi-part
//		      Multipart multipart = new MimeMultipart();
//		      multipart.addBodyPart(messageBodyPart);
//		    	        
//		      // sets the multi-part as e-mail's content
//		      msg.setContent(multipart);
//		} catch (AddressException e2){// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//	     catch (MessagingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	    try {
//				//Transport.send(msg);
//			} catch (Exception e) {
//				e.printStackTrace();			
//				return e.getMessage();
//			}
//	      return temp;
//		}

}
