package com.AyushEdu.controller.Placement_Mgmt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.config.MailHTML;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Placement_Mgmt.Placement_mang_Ent_Signup_DAO;



@Controller
@RequestMapping(value = {"admin","/","user"})
public class Placement_Management_Enterprise_signup_report {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	Placement_mang_Ent_Signup_DAO  ARdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Enterprise_Report_Url", method = RequestMethod.GET)
	 public ModelAndView Enterprise_Report_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request,RedirectAttributes ra) {
		
		Session sessionHQ = this.sessionFactory.openSession();
 
		
		try {
			
			//SECURITY - POOJA
			
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Enterprise_Report_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}							 
			Calendar calendar = Calendar.getInstance();
			 Mmap.put("msg", msg);
			 
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			 Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
			 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		 
		} catch (Exception e) {
			e.printStackTrace();
	    }
		 return new ModelAndView("Enterprise_signup_report_Tiles");
	 }
	
	@PostMapping("/getFilter_Enterprise_signup_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Edu_Enterprise_signup_data(int startPage, int pageLength,
	String Search,String orderColunm,String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
	String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, HttpSession httpsession) throws ParseException {
		String userid="";
		try { 
			 userid = httpsession.getAttribute("userId_for_jnlp").toString();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
		return ARdao.DataTableEdu_Reg_Report_placement_mang_enterprise(startPage, pageLength, Search, orderColunm, orderType,company_name,name,email_id,mobile_no,ph_no,address ,
				state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,userid);
	}

	@PostMapping("/getTotalEdu_Enterprise_signup_dataCount")

	public @ResponseBody long getTotalEdu_Reg_Enterprise_signup_dataCount(HttpSession sessionUserId,String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state,
			String per_district,String pincode,String hours_from ,String hours_to, String web_url,String photo_path,String photo_path_pic, HttpSession httpsession) throws ParseException {
	
		String userid = "";
		try { 
			 userid = httpsession.getAttribute("userId_for_jnlp").toString();
	
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return ARdao.DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise(Search,company_name,name,email_id,mobile_no,ph_no,address,state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,userid);

	}
	
	@RequestMapping(value = "/getDistrictOnstate_placement_report", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstate_placement_report(String selval) {
		return common.district(sessionFactory, selval);
	}
	
	 
	

	//--------------------------------------------------Image_View------------------------------------------------------

		@RequestMapping(value = "/MedicalImagePathplace1", method = RequestMethod.GET)
		public void MedicalImagePathplace1(@ModelAttribute("i_id") String id, @ModelAttribute("id5") String myImg,
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;
			String i_id = id;
			String filePath = ARdao.getImagePath(i_id);
			model.put("filePath", filePath);
			ServletContext context = request.getSession().getServletContext();
			try {
				

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//				request.getRealPath("/") + "/srv/Document/No_Image.jpg";

					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = context.getMimeType(fullPath);

					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}

					response.setContentType(mimeType);

					response.setContentLength((int) downloadFile.length());

					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {

						outStream.write(buffer, 0, bytesRead);

					}

					inputStream.close();

					outStream.close();

				} else {

					String fullPath = filePath;

					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = context.getMimeType(fullPath);

					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}

					response.setContentType(mimeType);

					response.setContentLength((int) downloadFile.length());

					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {

						outStream.write(buffer, 0, bytesRead);

					}

					inputStream.close();

					outStream.close();

				}

			} catch (Exception ex) {



				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();
			}
		}
		
		
//		=====2
		
		@RequestMapping(value = "/MedicalImagePathplace3", method = RequestMethod.GET)
		public void MedicalImagePathplace3(@ModelAttribute("i_id6") String id, @ModelAttribute("id6") String myImg4,
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;
			String i_id6 = id;
			String filePath1 = ARdao.getImagePath3(i_id6);
			model.put("filePath1", filePath1);
			ServletContext context = request.getSession().getServletContext();
			try {

				if (filePath1 == null && filePath1.isEmpty() && filePath1 == "" && filePath1 == "null") {

					String fullPath1 = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//				request.getRealPath("/") + "/srv/Document/No_Image.jpg";

					File downloadFile1 = new File(fullPath1);

					FileInputStream inputStream1 = new FileInputStream(downloadFile1);

					String mimeType1 = context.getMimeType(fullPath1);

					if (mimeType1 == null) {

						mimeType1 = "application/octet-stream";

					}

					response.setContentType(mimeType1);

					response.setContentLength((int) downloadFile1.length());

					OutputStream outStream1 = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream1.read(buffer)) != -1) {

						outStream1.write(buffer, 0, bytesRead);

					}

					inputStream1.close();

					outStream1.close();

				} else {

					String fullPath1 = filePath1;

					File downloadFile1 = new File(fullPath1);

					FileInputStream inputStream1 = new FileInputStream(downloadFile1);

					String mimeType1 = context.getMimeType(fullPath1);

					if (mimeType1 == null) {

						mimeType1 = "application/octet-stream";

					}

					response.setContentType(mimeType1);

					response.setContentLength((int) downloadFile1.length());

					OutputStream outStream1 = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream1.read(buffer)) != -1) {
						outStream1.write(buffer, 0, bytesRead);
					}
					inputStream1.close();

					outStream1.close();

				}

			} catch (Exception ex) {

				// System.out.println(ex);

				// admin//js//img//No_Image.jpg

				String fullPath1 = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
				File downloadFile1 = new File(fullPath1);

				FileInputStream inputStream1 = new FileInputStream(downloadFile1);

				String mimeType1 = context.getMimeType(fullPath1);

				if (mimeType1 == null) {

					mimeType1 = "application/octet-stream";

				}

				response.setContentType(mimeType1);

				response.setContentLength((int) downloadFile1.length());

				OutputStream outStream1 = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream1.read(buffer)) != -1) {

					outStream1.write(buffer, 0, bytesRead);

				}

				inputStream1.close();

				outStream1.close();
			}
		}
	//end
	
	@RequestMapping(value = "/Approve_FromStudent_to_enterprise_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_FromStudent_to_enterprise_Data(String a, String email_id ,String notified,HttpSession session) throws ParseException {	
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();
		
		
		String userId = session.getAttribute("userId").toString();
		String[] id_list = a.split(":");
		String[] email_list = email_id.split(":");
		String[] notif_list = notified.split(":");

		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String email = "" ;
		String notif = "" ;
		String date;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
			email  =  (email_list[i]);
			notif  =  (notif_list[i]);
			 
			list2.add(ARdao.approve_StudentPracData(Integer.toString(id),username,userId));
			
			if(notified.equals("1")) {
				//SendRegMail(email);
			}
			
			//end
		}
		
		
		return list2;
	}
	@RequestMapping(value = "/Reject_FromStudent_to_enterprise_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_FromStudent_to_enterprise_Data(String a,HttpSession session) throws ParseException {	
		String username = session.getAttribute("username").toString();
		String userId_reject = session.getAttribute("userId").toString();
		
		String[] id_list = a.split(":");
//		String[] id_list2 = upto2.split(":");
//		String[] tempSt2 = tempSt.split(",");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
//			date = id_list2[i];
//			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(ARdao.reject_StudentPracData(Integer.toString(id),username,userId_reject));
		}
		return list2;
	}
	

//	// FOR EMAIL URMIK
//	public void SendRegMail(String email) throws ParseException {
//		
//		System.err.println("mail senttttttttttttttttt");
//		MailHTML html = new MailHTML();
//		try {
//			MimeMessage mimeMessage = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				String text = "Your Registration is Successfull ... Thank you for Registrating with us!";
//				String note = "";
//				html.setHTML(text, note);
//				String htmlMsg = html.getHTML();
//				helper.setText(htmlMsg, true);
//				helper.setTo(email);
//				helper.setSubject("MOA Registration is Successfull");
//				/* helper.setFrom("abc@gmail.com"); */
//				mailSender.send(mimeMessage);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//		}
//	}
	
	
	//////////// FOR HOD //////////////
	@RequestMapping(value = "admin/Enterprise_HODReport_Url", method = RequestMethod.GET)
	 public ModelAndView Enterprise_HODReport_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request,RedirectAttributes ra) {
		
		Session sessionHQ = this.sessionFactory.openSession();

		
		try {
			
			//SECURITY - POOJA
			
			if (request.getHeader("Referer") == null) {
				ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Enterprise_HODReport_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
				 
//			Calendar calendar = Calendar.getInstance();
			 Mmap.put("msg", msg);
			 Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
			 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
//			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//			 Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
//			 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
			
		 
		} catch (Exception e) {
			e.printStackTrace();
	    }
		 return new ModelAndView("Enterprise_HOD_report_Tiles");
	 }
	
	@RequestMapping(value = "/getDistrictOnstate_placementhod_report", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstate_placementhod_report(String selval) {
		return common.district(sessionFactory, selval);
	}
	
	@RequestMapping(value = "/Approve_enterprise_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_enterprise_Data(String a, String email_id ,String notified,HttpSession session) throws ParseException {	
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		String username = session.getAttribute("username").toString();
		
		String userId = session.getAttribute("userId").toString();
		String[] id_list = a.split(":");
		String[] email_list = email_id.split(":");
		String[] notif_list = notified.split(":");

		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String email = "" ;
		String notif = "" ;
		String date;
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
//			email  =  (email_list[i]);
//			notif  =  (notif_list[i]);
			 
			list2.add(ARdao.approve_EnterprisePracData(Integer.toString(id),username,userId));
			
			if(notified.equals("1")) {
				//SendRegMail(email);
			}
			
			//end
		}
		
		
		return list2;
	}
	@RequestMapping(value = "/Reject_enterprise_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Reject_enterprise_Data(String a,HttpSession session) throws ParseException {	
		String username = session.getAttribute("username").toString();
		String userId_reject = session.getAttribute("userId").toString();
		
		String[] id_list = a.split(":");
//		String[] id_list2 = upto2.split(":");
//		String[] tempSt2 = tempSt.split(",");
		List<String> list2 = new ArrayList<String>();
		int id = 0;
		String date;
		
		for (int i = 0; i < id_list.length; i++) {
			id = Integer.parseInt(id_list[i]);
//			date = id_list2[i];
//			System.out.println("remarks======================== "+tempSt2[i]);
			list2.add(ARdao.reject_EnterprisePracData(Integer.toString(id),username,userId_reject));
		}
		return list2;
	}
	
//	 @RequestMapping(value = "/getFilter_Enterprise_HOD_data", method = RequestMethod.POST)
//		public @ResponseBody List<Map<String, Object>>  getFilter_Enterprise_HOD_data(@RequestBody Map<String, String> data) throws ParseException {
//		
//		 int startPage = 0;
//		 int pageLength = 100;
//
//		 String Search = "";
//		 String orderColunm = "";	
//		 String orderType = "";
//		 String company_name = data.get("company_name");
//		 String name = data.get("name");
//		 String email_id = data.get("email_id");
//		 String mobile_no = data.get("mobile_no");
//		 String ph_no = data.get("ph_no");
//		 String address = data.get("address");
//		 String state = data.get("state");
//		 String per_district = data.get("per_district");
//		 String pincode = data.get("pincode");
//		 String hours_from = data.get("hours_from");
//		 String hours_to = data.get("hours_to");
//		 String web_url = data.get("web_url");
//		 String Status = data.get("status");
////		 String photo_path = data.get("photo_path");
////		 String photo_path_pic = data.get("photo_path_pic");
//		 String photo_path = "undefined";
//		 String photo_path_pic = "undefined";
//		 
//		 return ARdao.DataTableEdu_Reg_Report_placement_mang_enterprise_FOR_HOD(startPage, pageLength, Search, orderColunm,
//				 orderType,company_name,name,email_id,mobile_no,ph_no,address ,
//					state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status);
//
//
//		}
	 
	 
	 @PostMapping("/getFilter_Enterprise_HOD_data")
		public @ResponseBody List<Map<String, Object>> getFilter_Enterprise_HOD_data(int startPage, int pageLength,
		String Search,String orderColunm,String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
		String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String status) throws ParseException {
		  
			return ARdao.DataTableEdu_Reg_Report_placement_mang_enterprise_FOR_HOD(startPage, pageLength, Search, orderColunm, orderType,company_name,name,email_id,mobile_no,ph_no,address ,
					state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,status);

		}

		@PostMapping("/getTotalEdu_Reg_Enterprise_HOD_dataCount")

		public @ResponseBody long getTotalEdu_Reg_Enterprise_HOD_dataCount(HttpSession sessionUserId,String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state,
				String per_district,String pincode,String hours_from ,String hours_to, String web_url,String photo_path,String photo_path_pic, String status) throws ParseException {
			return ARdao.DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise_ForHOD(Search,company_name,name,email_id,mobile_no,ph_no,address,state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,status);

		}
		
		 @PostMapping("/GetIntrested_Students_Data")
			public @ResponseBody List<Map<String, Object>> GetIntrested_Students_Data(String id) throws ParseException {
			  
				return ARdao.GetIntrested_Students_Data(id);

			}
		 
		 @RequestMapping(value = "/ApproveIntrested_Students_Data" , method = RequestMethod.POST)
			public @ResponseBody List<String> ApproveIntrested_Students_Data(HttpServletRequest request,String studentsId,String studentsEmails,HttpSession session) throws ParseException {	
				
//				String[] id_list = studentsId.split(",");
				String[] email_list = studentsEmails.split(",");
//				String[] id_list2 = upto2.split(":");
//				String[] tempSt2 = tempSt.split(",");
				List<String> list2 = new ArrayList<String>();
				int id = 0;
				list2.add(ARdao.ApproveIntrested_Students_Data(studentsId));
				SendMultiRegMail(request,email_list);
//				for (int i = 0; i < id_list.length; i++) {
//					id = Integer.parseInt(id_list[i]);
////					date = id_list2[i];
////					System.out.println("remarks======================== "+tempSt2[i]);
//					
//				}
				return list2;
			}
		 
		 
//			public void SendMultiRegMail(String[] email) throws ParseException {
//				
//				
//				
//				System.err.println("mail senttttttttttttttttt- "+email[0]);
//				MailHTML html = new MailHTML();
//				try {
//					MimeMessage mimeMessage = mailSender.createMimeMessage();
//					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//					try {
//						String text = "Your Registration is Successfull ... Thank you for Registrating with us!";
//						String note = "";
//						html.setHTML("", text,"");
//						String htmlMsg = html.getHTML();
//						helper.setText(htmlMsg, true);
//						helper.setTo(email);
//						helper.setSubject("MOA Registration is Successfull");
//						/* helper.setFrom("abc@gmail.com"); */
//						mailSender.send(mimeMessage);
//					} catch (MessagingException e) {
//						e.printStackTrace();
//					}
//				} catch (Exception e) {
//				}
//			}
			
			
			public boolean SendMultiRegMail( HttpServletRequest request,String[] email) throws ParseException {
				MailHTML html = new MailHTML();
				try {
					MimeMessage mimeMessage = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

					String text = "Your Registration is Successfull ... Thank you for Registrating with us!";
//					String note = "";

					// SET MAIN CONTENT IN MAIN ALONG WITH FOLLOWUP LINE
					html.setHTML("", text,"");

					String htmlMsg = html.getHTML();

					// SET EMAIL WHOM TO SEND
					helper.setTo(email);

					// SET SUBJECT TO EMAIL
					helper.setSubject("MOA Registration Successfull");

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


					// SEND EMAIL
					mailSender.send(mimeMessage);
					return true;

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
	
	 
}
