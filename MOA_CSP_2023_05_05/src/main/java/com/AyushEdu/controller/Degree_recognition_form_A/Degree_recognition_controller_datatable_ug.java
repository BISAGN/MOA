package com.AyushEdu.controller.Degree_recognition_form_A;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_EXAMINERS_APPOINTED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_TRACK_STATUS;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_recognition_controller_datatable_ug {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Form_a_ug_Dao UDdao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	Form_b_pg_Dao PDdao;

	// ==========================================OPEN PAGE Student DataTable And FianlSubmit==========================================

	@RequestMapping(value = "/UG_Datatable_url", method = RequestMethod.GET)
	public ModelAndView UG_Datatable_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);

		return new ModelAndView("Degree_rec_Datatable_Tiles");
	}
	

	//DOWNLOAD DOCUMENT PDF
		@RequestMapping(value = "/getDownloadUrlDocFormA")
		public void getDownloadUrlDocFormA(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
				String upload_docformA, ModelMap model, HttpServletRequest request, HttpSession session,
				HttpServletResponse response) throws IOException {
			
//			System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

			final int BUFFER_SIZE = 4096;

//			String i_id = id;
			System.err.println("upload_docformA==========="+upload_docformA);
			String filePath = UDdao.getFilePathQueryForDocAttFormA(upload_docformA);
//			String filePath = Pro_forma.getAttachmentImagePath(i_id,myImg);

//			System.out.println("chgukhdfguhkdfhgkj---" + filePath);

			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";

					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());
					
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

					String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());

					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}
					
					response.setContentType(mimeType);
					response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + downloadFile.getName() + "\""));
					response.setContentLength((int) downloadFile.length());

//					response.setContentType(mimeType);

//					response.setContentLength((int) downloadFile.length());

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

				System.out.println(ex);
				
			//	admin//js//img//No_Image.jpg
				
				
				
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//						request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
		//DOWNLOAD DOCUMENT PDF
				@RequestMapping(value = "/getDownloadUrlDocFormB")
				public void getDownloadUrlDocFormB(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
						String upload_docformB, ModelMap model, HttpServletRequest request, HttpSession session,
						HttpServletResponse response) throws IOException {

					final int BUFFER_SIZE = 4096;

					String filePath = UDdao.getFilePathQueryForDocAttFormB(upload_docformB);

					model.put("filePath", filePath);

					ServletContext context = request.getSession().getServletContext();

					try {

						if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

							String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

							File downloadFile = new File(fullPath);

							FileInputStream inputStream = new FileInputStream(downloadFile);

							String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());
							
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

							String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());

							if (mimeType == null) {

								mimeType = "application/octet-stream";

							}
							
							response.setContentType(mimeType);
							response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + downloadFile.getName() + "\""));
							response.setContentLength((int) downloadFile.length());

//							response.setContentType(mimeType);

//							response.setContentLength((int) downloadFile.length());

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

						System.out.println(ex);
						
					//	admin//js//img//No_Image.jpg
						
						
						
						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//								request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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

	// -------------------------------------------View table UG Form A-------------------------------------------

//		@PostMapping("/getFilter_UG")
//
//		public @ResponseBody List<Map<String, Object>> getFilter_UG_list(HttpSession session, int startPage, int pageLength,
//				String Search, String orderColunm, String orderType, String id, String university_approved_status,
//				String council_approved_status, String university_status,String institute_id,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
//				String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) throws ParseException {
//
////			String country,String state_id,String district,String city,String postal_address,String email,String website,
//			
//			String userId = session.getAttribute("userId").toString();
//			String university_id = session.getAttribute("university_id").toString();
//			String user_id = (userId);
//
//			return UDdao.getFilter_UG_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
//					university_approved_status, council_approved_status, university_status, institute_id,name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
//					academic_year_applied_for, permission_from_central_gov,
//					admission_intake,num_of_student_admitted,remarks);
//
//		}
//
//		@PostMapping("/getFilter_UGListCount")
//		public @ResponseBody long getFilter_UGListCount(HttpSession session, String Search, int id,
//				String university_status,String institute_id,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,String academic_year_applied_for, String permission_from_central_gov,
//				String admission_intake,String num_of_student_admitted,String remarks) throws ParseException {
//
//			String userId = session.getAttribute("userId").toString();
//			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
//			int user_id = Integer.parseInt(userId);
//
//			return UDdao.getFilter_UGListCount(Search, university_id, user_id, university_status, institute_id,name_of_applicant_university,name_ug_course,abbre_undergraduate_course,academic_year_applied_for, permission_from_central_gov,
//					admission_intake,num_of_student_admitted,remarks);
//		}
		
		@PostMapping("/getFilter_UG")

		public @ResponseBody List<Map<String, Object>> getFilter_UG_list(HttpSession session, int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String id, String university_approved_status,
				String council_approved_status, String university_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
				String institute_name,String academic_year_applied_for, String academic_file_upload,
				String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
				String remarks) throws ParseException {

//			String country,String state_id,String district,String city,String postal_address,String email,String website,
			
			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return UDdao.getFilter_UG_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
					university_approved_status, council_approved_status, university_status,name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
					institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
					admission_intake,num_of_student_admitted,postal_address,email,website,remarks);

		}

		@PostMapping("/getFilter_UGListCount")
		public @ResponseBody long getFilter_UGListCount(HttpSession session, String Search, int id,
				String university_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
				String institute_name,String academic_year_applied_for, String academic_file_upload,
				String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String postal_address,String email,String website,
				String remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return UDdao.getFilter_UGListCount(Search, university_id, user_id, university_status, name_of_applicant_university,name_ug_course,abbre_undergraduate_course,
					institute_name,academic_year_applied_for, academic_file_upload,permission_from_central_gov,
					admission_intake,num_of_student_admitted,postal_address,email,website,remarks);
		}
		// -------------------------------------------View table UG Form E-------------------------------------------

		@PostMapping("/getFilter_Examiners_appointed")

		public @ResponseBody List<Map<String, Object>> getFilter_Examiners_appointed_list(HttpSession session,
				int startPage, int pageLength, String Search, String orderColunm, String orderType, String id,
				String university_approved_status, String council_approved_status, String university_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
				String teaching_experience,String teacher_code, String reg_number,String d_university_appointment)
				throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return UDdao.getFilter_Examiners_appointed_list(startPage, pageLength, Search, orderColunm, orderType,
					university_id, university_approved_status, council_approved_status, university_status,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
					teaching_experience,teacher_code,reg_number,d_university_appointment);

		}

		@PostMapping("/getFilter_Examiners_appointedListCount")
		public @ResponseBody long getFilter_Examiners_appointedListCount(HttpSession session, String Search, int id,
				String university_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
				String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return UDdao.getFilter_Examiners_appointedListCount(Search, university_id, user_id, university_status,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
					teaching_experience,teacher_code,reg_number,d_university_appointment);
		}
		// --------------------------------------------Submit for approval-----University------------------------------------

		@RequestMapping(value = "/Submit_Approval_Data_university", method = RequestMethod.POST)
		public @ResponseBody String Submit_Approval_Data_university(ModelMap Mmap, HttpSession session,
				HttpServletRequest request,String current_month_year,
				@RequestParam(value = "ssug_hid", required = false) int ssug_hid) throws ParseException {
System.out.println("----ssug_hid"+ssug_hid);

			String msg = "";
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String user_id = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			
			System.out.println("----university_id"+university_id);

		    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		    
		    String month_year = request.getParameter("Calendar");
			try {
				
				Query q2 = sessionHQL.createQuery("from DG_REC_TRACK_STATUS");
		         @SuppressWarnings("unchecked")
		         
		         List<DG_REC_TRACK_STATUS> clist_t = (List<DG_REC_TRACK_STATUS>) q2.list();
		         String count_data = "";
		         for(int i = 0 ; i < clist_t.size(); i++) {
		        	 count_data += String.valueOf(clist_t.get(i).getId()) +",";
		        	 }
		         System.err.println("COUNT ++++++++"+count_data);
		         
				if(ssug_hid > 0) 
				{
					String hqlUpdate = "update from DG_REC_UG_FORM_A set university_approved_status=:university_approved_status where user_id=:user_id and university_approved_status=0";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setInteger("university_approved_status", 1)
							.setInteger("user_id", Integer.parseInt(user_id))
							.executeUpdate();
//			
//				Query q = sessionHQL.createQuery("from DG_REC_UG_FORM_A where user_id=:user_id and university_approved_status=:university_approved_status")
//						.setInteger("university_approved_status", 1)
//				        .setInteger("user_id", Integer.parseInt(user_id));
//				         @SuppressWarnings("unchecked")
				 
			   Query q = sessionHQL.createQuery("from DG_REC_UG_FORM_A where user_id=:user_id and university_approved_status=:university_approved_status and cast(id as string) not in (:data)")
							.setInteger("university_approved_status", 1)
					        .setInteger("user_id", Integer.parseInt(user_id))
					        .setParameter("data", count_data.substring(0,count_data.length()-1));
					         @SuppressWarnings("unchecked")
					         
				         List<DG_REC_UG_FORM_A> clist = (List<DG_REC_UG_FORM_A>) q.list();
				
							clist.forEach((n) -> {
								System.err.println("FIRST ==========="+n.toString());
								DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
								trx.setTbl_id(n.getId());
								trx.setCommi_status(0);
								trx.setUni_status(1);
								trx.setInstitute_id(n.getUser_id());
								trx.setUniversity_id(n.getUniversity_id());
								trx.setMonth_year(n.getCurrent_month_year());
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
							});
			}
					
//					if(ssex_hid > 0) 
//					{
//						String hqlUpdate = "update from DG_REC_EXAMINERS_APPOINTED set university_approved_status=:university_approved_status where user_id=:user_id and university_approved_status=0";
//						int app = sessionHQL.createQuery(hqlUpdate).setInteger("university_approved_status", 1)
//								.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
//				
////					Query q = sessionHQL.createQuery("from DG_REC_EXAMINERS_APPOINTED where user_id=:user_id and university_approved_status=:university_approved_status")
////							.setInteger("university_approved_status", 1)
////					        .setInteger("user_id", Integer.parseInt(user_id));
////					         @SuppressWarnings("unchecked")
//					  
//					         Query q = sessionHQL.createQuery("from DG_REC_EXAMINERS_APPOINTED where user_id=:user_id and university_approved_status=:university_approved_status and cast(id as string) not in (:data)")
//								.setInteger("university_approved_status", 1)
//						        .setInteger("user_id", Integer.parseInt(user_id))
//						        .setParameter("data", count_data.substring(0,count_data.length()-1));
//						         @SuppressWarnings("unchecked")
//						         
//					         List<DG_REC_EXAMINERS_APPOINTED> clist = (List<DG_REC_EXAMINERS_APPOINTED>) q.list();
//					
//								clist.forEach((n) -> {
//									System.err.println("Sec ==========="+n.toString());
//									DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
//									trx.setTbl_id(n.getId());
//									trx.setCommi_status(0);
//									trx.setUni_status(1);
//									trx.setInstitute_id(n.getUser_id());
//									trx.setUniversity_id(n.getUniversity_id());
//									trx.setMonth_year(n.getCurrent_month_year());
//									sessionHQL.save(trx);		
//									sessionHQL.flush();
//									sessionHQL.clear();
//								});
//				}

					if ((ssug_hid > 0) ) {
						msg = "Data Submitted Successfully";
						tx.commit();
					} else {
						msg = "Something Went Wrong !!!";
					}
					sessionHQL.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ------email----------

//									String subject = "YourRegistrationForAlumniisSuccessfull";
//									String main_txt = " You have received this valid data from the Institute. "
//											         + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//									String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//									e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");

				// ------end email----------

				
			//----notification---------
			
//			String uni_id = String.valueOf(common.getAllInfoLogin(sessionFactory,user_id).get(0).getUniversity_id());
//			System.err.println("UNIVERSITY ID PRANAV----------------------"+uni_id); 
//			String userid = UDdao.getNCH_user_id(uni_id).get(0).get(0);
//			String notimsg=" You have received this valid data from the University. "
//				       + "This Details has been Successfully Approved by University.So ,Check it and give the approval.";
//		     common.Notification(notimsg, "419",sessionFactory, session);
//		     System.err.println(notimsg+"-------notimsg");
		     
		   //----end notification---------
			return msg;
		}
	// ==========================================OPEN PAGE UG COUSES Edit Data And Update==========================================

	@RequestMapping(value = "/UG_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView UG_Edit_Update_Url(@ModelAttribute("eid") String eid,ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		System.err.println("EID------------" + eid);
		Mmap.put("eid", eid);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.out.println("-----userid---"+userid);
		Mmap.put("ug_detail", UDdao.getugByid(Integer.parseInt(eid)));
		Mmap.put("msg", msg);
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		 Mmap.put("getuni_dtl", UDdao.getUni_detail(Integer.parseInt(userid)));
		return new ModelAndView("ug_edit_update_Tiles");
	}

	/////////// edit UG COUSES //////////////

	@RequestMapping(value = "/edit_ug_Action", method = RequestMethod.POST)
	public ModelAndView edit_ug_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/login");
		}

		String username = session.getAttribute("username").toString();
		String id = request.getParameter("eid");
		
		String institute_id=request.getParameter("institute_id");
		
		System.out.println("-------institute_id----"+institute_id);
		
		String academic_year_applied_for =request.getParameter("academic_year_applied_for");
		String permission_from_central_gov = request.getParameter("filePath");
		String admission_intake = request.getParameter("admission_intake");
		String num_of_student_admitted = request.getParameter("num_of_student_admitted");
		String country = request.getParameter("country");
		String state_id = request.getParameter("state_id");
		String district_id= request.getParameter("district_id");
		String city = request.getParameter("city");
		String postal_address = request.getParameter("postal_address");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String remarks = request.getParameter("remarks");

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_UG_FORM_A set " 
							+ "modified_by=:modified_by, "
							+ "modified_date=:modified_date, " 
							+ "institute_id=:institute_id, "
							+ "academic_year_applied_for=:academic_year_applied_for, "
							+ "permission_from_central_gov=:permission_from_central_gov, "
							+ "admission_intake=:admission_intake, " 
							+ "num_of_student_admitted=:num_of_student_admitted, "
							+ "country=:country, " 
							+ "state_id=:state_id, " 
							+ "district_id=:district_id, "
							+ "city=:city, "
							+ "postal_address=:postal_address, " 
							+ "email=:email, "
							+ "website=:website, "
							+ "remarks=:remarks " 
							+ "where id=:id ";

			Query query = session1.createQuery(hql)
					.setParameter("institute_id", institute_id)
					.setParameter("academic_year_applied_for", academic_year_applied_for)
					.setParameter("permission_from_central_gov", permission_from_central_gov)
					.setParameter("admission_intake", admission_intake)
					.setParameter("num_of_student_admitted", num_of_student_admitted)
					.setParameter("country", country)
					.setParameter("state_id", state_id)
					.setParameter("district_id", district_id)
					.setParameter("city", city)
					.setParameter("postal_address", postal_address)
					.setParameter("email", email)
					.setParameter("website", website)
					.setParameter("remarks", remarks)
					.setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:UG_Datatable_url");
	}

	// ==========================================OPEN PAGE UG Examiners Edit Data And Update==========================================

	@RequestMapping(value = "/UG_Examiners_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView UG_Examiners_Edit_Update_Url(@ModelAttribute("exid") String exid, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		System.err.println("hospiId------------" + exid);
	
		Mmap.put("exid", exid);
		Mmap.put("ug_examiners_detail", UDdao.getugexamByid(Integer.parseInt(exid)));
		Mmap.put("msg", msg);
		
		return new ModelAndView("ug_examiners_edit_update_Tiles");
	}

	/////////// edit UG Examiners //////////////

	@RequestMapping(value = "/edit_ug_examiners_Action", method = RequestMethod.POST)
	public ModelAndView edit_ug_examiners_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/login");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("exid");

		String name_of_examiners = request.getParameter("name_of_examiners");
		String subject_examiners = request.getParameter("subject_examiners");
		String year_examiners = request.getParameter("year_examiners");
		String qualification_examiners = request.getParameter("qualification_examiners");
		String teaching_experience = request.getParameter("teaching_experience");
		String teacher_code = request.getParameter("teacher_code");
		String reg_number = request.getParameter("reg_number");
		String d_university_appointment = request.getParameter("d_university_appointment");

		Date dt_uni = null;

		if (!d_university_appointment.trim().equals("") && !d_university_appointment.equals("DD/MM/YYYY")) {
			dt_uni = format.parse(d_university_appointment);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_EXAMINERS_APPOINTED set " + "modified_by=:modified_by, "
					+ "modified_date=:modified_date, " + "name_of_examiners=:name_of_examiners, "
					+ "subject_examiners=:subject_examiners, " + "year_examiners=:year_examiners, "
					+ "qualification_examiners=:qualification_examiners, "
					+ "teaching_experience=:teaching_experience, " + "teacher_code=:teacher_code, "
					+ "reg_number=:reg_number, " + "d_university_appointment=:d_university_appointment "
					+ "where id=:id ";

			Query query = session1.createQuery(hql).setParameter("name_of_examiners", name_of_examiners)
					.setParameter("subject_examiners", subject_examiners).setParameter("year_examiners", year_examiners)
					.setParameter("qualification_examiners", qualification_examiners)
					.setParameter("teaching_experience", teaching_experience)
					.setParameter("teacher_code", Integer.parseInt(teacher_code))
					.setParameter("reg_number", Integer.parseInt(reg_number))
					.setParameter("d_university_appointment", dt_uni).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:UG_Datatable_url");
	}
	

	// --------------------------------------------Submit for approval-----institute data university side------------------------------------

		@RequestMapping(value = "/Submit_Approval_Data_ins_university", method = RequestMethod.POST)
		public @ResponseBody String Submit_Approval_Data_ins_university(ModelMap Mmap, HttpSession session,
				HttpServletRequest request,
				@RequestParam(value = "ssa_hid", required = false) int ssa_hid,
				@RequestParam(value = "ssmt_hid", required = false) int ssmt_hid,
				@RequestParam(value = "ssmf_hid", required = false) int ssmf_hid,
				@RequestParam(value = "ssi_hid", required = false) int ssi_hid) throws ParseException {

			String msg = "";

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String university_id = session.getAttribute("university_id").toString();
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		    String user_id = session.getAttribute("userId").toString();

			String cmoyr = (month + 1) + "-" + year;

			System.err.println("university_id---" + university_id + "---co mo yr---" + cmoyr);

			try {
					if(ssa_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_ADMITTED_STUDENT set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
//					if(ssh_hid > 0) 
//					{
//						String hqlUpdate = "update DG_REC_HOSPITAL_ATTACHED set \n"
//											+ "university_approved_status=:university_approved_status \n"
//											+ "where university_id=:university_id";
//					
//						int app = sessionHQL.createQuery(hqlUpdate)
//								.setInteger("university_approved_status", 1)
//								.setInteger("university_id", Integer.parseInt(university_id))
//								.executeUpdate();
//				
//						String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
//											+ "commi_status=:commi_status where university_id=:university_id";
//				
//						int app1 = sessionHQL.createQuery(hqlUpdate1)
//								.setInteger("inst_status", 1)
//								.setInteger("uni_status", 1)
//								.setInteger("commi_status", 0)
//								.setInteger("university_id", Integer.parseInt(university_id))
//								.executeUpdate();
//					}
					if(ssmt_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_MIGRATED_STUDENT_SUB_CHILD set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(ssmf_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_MIGRATED_STUDENT_FROM set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(ssi_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_INTERN_STUDENT_COURSE set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
				
				if ((ssa_hid > 0) || (ssmt_hid > 0) || (ssmf_hid > 0) || (ssi_hid > 0) ) {

					msg = "Data Approved Successfully";
					
					//------email----------
					
//					String subject = "YourRegistrationForAlumniisSuccessfull";
//					String main_txt = " You have received this valid data from the Institute. "
//							         + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//					String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//					e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");
					
					//------end email----------
					
					tx.commit();
				} else {
					msg = "Something Went Wrong !!!";
				}
				sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			//----notification---------
//			

//			 String notimsg=" You have received this valid data from the University. "
//				       + "This Details has been Successfully Approved by University.So ,Check it and give the approval.";
//		     common.Notification(notimsg, "419",sessionFactory, session);
//		     System.err.println(notimsg+"-------notimsg");
		     
		   //----end notification---------
		     
			return msg;
		}
}
