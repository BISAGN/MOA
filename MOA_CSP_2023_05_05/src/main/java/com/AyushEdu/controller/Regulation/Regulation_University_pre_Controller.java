package com.AyushEdu.controller.Regulation;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_SUB_CH;
import com.AyushEdu.controller.Regulation.ExcelUserListReportView;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Regulation_University_pre_DAO;
import com.AyushEdu.dao.Regulation.Regulation_previewDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Regulation_University_pre_Controller {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Regulation_University_pre_DAO UPdao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
//	-----------------preview

 
		@RequestMapping(value = "admin/pract_University_previewUrl" )
		public ModelAndView pract_University_previewUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, 	@RequestParam(value = "u_id", required = false) String id, HttpServletRequest request){
		
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("edu_search_reg_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Calendar calendar = Calendar.getInstance();
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("u_id", id);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
 		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();

		int d  = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
				.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
		Mmap.put("ud", d);
		try {
			 
			
 			int data = (int) sessionHQ.createQuery("select user_id from REG_NCH_FORM_A_P where id=:id")
 					         .setParameter("id",Integer.parseInt(id)).setMaxResults(1).uniqueResult();
			Mmap.put("userId", data);
			if (data != 0) {
				Mmap.put("setDataCMD", UPdao.getDataByUserNameForDraftPreview(data));
				Mmap.put("setAddMoreMedicalCMD", UPdao.medicalDataPreview(data , d));
				Mmap.put("setAddMoreHospCMD", UPdao.HospitalDataPreview(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", UPdao.medicalDataChildAttachmentPreview(data));
 				Mmap.put("setRegAuth", UPdao.RegAuthPreview(data));
				String pid = UPdao.getUserIdPreview(data);
				Mmap.put("p_id", pid);
 				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
					Mmap.put("parentid", data2);
					Mmap.put("valid_dt", INF.getValid_up_to());

					if (INF.getStatus() == 0) {
						Mmap.put("hid", "2");

					}
					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
						Mmap.put("hid", "1");
					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1 && INF.getNrh_status() == 1) {
						Mmap.put("hid", "3");
 					} else {
						Mmap.put("hid", "0");
					}
				} else {
					Mmap.put("p_id", "0");
				}

			} else {
				Mmap.put("setData", "0");
				Mmap.put("hid", "0");
				Mmap.put("p_id", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
  	return new ModelAndView("pract_university_preview_Tiles");

	}

		@RequestMapping(value = "/University_Approve_Degree" , method = RequestMethod.POST)
		public @ResponseBody List<String> University_Approve_Degree(String a,String status , String u_id ,HttpSession session) throws ParseException {	
			SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
			String username = session.getAttribute("username").toString();
			String role = session.getAttribute("role").toString();
			
			
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction  tx= sessionHQL.beginTransaction();
			 	
			Session sessionHQL1 = this.sessionFactory.openSession();
			Transaction  tx1= sessionHQL1.beginTransaction();
			
			
 			String[] id_list = a.split(",");
 			List<String> list2 = new ArrayList<String>();
			int id = 0;
			
			 
 			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
//				Session sessionHQL = this.sessionFactory.openSession();
//				Session sessionHQL1 = this.sessionFactory.openSession();
//				 
				int data = (int) sessionHQL.createQuery("select role from REG_NCH_FORM_A_P where id=:id")
				         .setParameter("id",Integer.parseInt(u_id)).setMaxResults(1).uniqueResult();
				
				
				long count_p = (long) sessionHQL1.createQuery("select count(id) from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where parent_id=:id "
						+ "and (name_of_attachment='Final Year Marksheet' or name_of_attachment='Provisional Registration Certificate'"
						+ " or name_of_attachment='Internship Completion Certificate' or name_of_attachment='Provisional Degree Certificate') ")
				         .setParameter("id", (id)).setMaxResults(1).uniqueResult();
 				System.err.println("vount---"+count_p);
 				
 				String cp = String.valueOf(count_p);
 
 				
 		  	list2.add(UPdao.University_Approve_DegreeData(a ,status ,u_id,data ,   cp));
				//end
			}
			return list2;
		}
		 
	 //Reject 
		 
		@RequestMapping(value = "/University_Reject_Degree" , method = RequestMethod.POST)
		public @ResponseBody List<String> University_Reject_Degree(String a, String status,String tempSt,HttpSession session) throws ParseException {	
			System.err.println("dao------------cont");
			String username = session.getAttribute("username").toString();
			String[] id_list = a.split(":");
		 
			String[] tempSt2 = tempSt.split(",");
			List<String> list2 = new ArrayList<String>();
			int id = 0;
 			String date;
 			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
 				 list2.add(UPdao.University_Reject_DegreeData(Integer.toString(id),status,tempSt2[i]));
			}
			return list2;
		}

		
		
		 @RequestMapping(value = "/get_degrrename_Reject_id", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_degrrename_Reject_id(String id) {
		    	ArrayList<ArrayList<String>> data = UPdao.get_degrrename_Reject_idata(id);
		    	System.err.println("hiiiiiii");
		    	return data;
		 	}
		 
		 
		 @SuppressWarnings("null")
			@RequestMapping(value = "/attachmentfiledownload")
			public void attachmentfiledownload(@ModelAttribute("kmlId2") String id, ModelMap model, HttpServletRequest request,
					HttpServletResponse response, HttpSession session) throws IOException {

				final int BUFFER_SIZE = 4096;

				String filePath = UPdao.getFilePathQueryForDoc(id);

				model.put("filePath", filePath);

				ServletContext context = request.getSession().getServletContext();
				try {
					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
						@SuppressWarnings("deprecation")
						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
						File downloadFile = new File(fullPath);
						FileInputStream inputStream = new FileInputStream(downloadFile);
						String mimeType = context.getMimeType(fullPath);
						if (mimeType == null) {
							mimeType = "application/octet-stream";
						}
						response.setContentType(mimeType);
						response.setContentLength((int) downloadFile.length());
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						response.setHeader(headerKey, headerValue);
						ServletOutputStream outStream = response.getOutputStream();
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
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						response.setHeader(headerKey, headerValue);
						ServletOutputStream outStream = response.getOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int bytesRead = -1;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, bytesRead);
						}
						inputStream.close();
						outStream.close();
					}
				} catch (Exception ex) {
					@SuppressWarnings("deprecation")
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
					File downloadFile = new File(fullPath);
					FileInputStream inputStream = new FileInputStream(downloadFile);
					String mimeType = context.getMimeType(fullPath);
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
					response.setHeader(headerKey, headerValue);
					ServletOutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outStream.close();
				}

			}
//		 ----------Multiple Preview for Attachment
		 @RequestMapping(value = "/getattfilesToPreview", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> getattfilesToPreview(String id,HttpSession session) {
			 	String userId = session.getAttribute("userId_for_jnlp").toString();
		    	ArrayList<ArrayList<String>> data = UPdao.getattfilesToPreviewD(userId,id);
		    	return data;
		 	}


}
