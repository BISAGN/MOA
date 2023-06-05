package com.AyushEdu.controller.Registration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
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
import com.AyushEdu.controller.Examination.Student_Excel_Report;
import com.AyushEdu.controller.Examination.Student_PDF_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Search_Student_RegistrationController {
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Search_Student_RegistrationDao SSRDao;
	@Autowired
	Search_Ncism_Student_RegistrationDao  SSNCISM;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/SearchStudents_RegistrationUrl", method = RequestMethod.GET)
	public ModelAndView SearchStudents_RegistrationUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SearchStudents_RegistrationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		Mmap.put("msg", msg);
		Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
		Mmap.put("getinstitutelist", SSRDao.getinstitutelist(userid));
		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		
		
		String role = session.getAttribute("roleid").toString();
		if (role.equals("19")) {//uni nch
			String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
			System.err.println("uni_id------>   "+uni_id);
			Mmap.put("uni_id",uni_id);
			}
			
			if (role.equals("21")) {//inst nch
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id------>   "+inst_id);
				Mmap.put("inst_id",inst_id);
			}
		
		return new ModelAndView("SearchStudents_Registration_Tiles");
	}
	
	@PostMapping("/getFilterSearch_Stu_Reg_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_Stu_Reg_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		return SSRDao.DataTableSearch_Stu_RegDataList(startPage, pageLength, Search, orderColunm, orderType, university_id, institute_id,name,ayush_id,gender,date_of_birth);

	}
	@PostMapping("/getTotalSearch_Stu_Reg_dataCount")
	public @ResponseBody long getTotalSearch_Stu_Reg_dataCount(HttpSession sessionUserId, String Search, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		return SSRDao.DataTableSearch_Stu_RegDataTotalCount(Search, university_id, institute_id,name,ayush_id,gender,date_of_birth);
	}
	
	@RequestMapping(value = "admin/Search_Stu_RegPopupUrl", method = RequestMethod.POST)
	public ModelAndView Search_Stu_RegPopupUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "popid", required = false) String id,HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Stu_RegPopupUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		Mmap.put("msg", msg);
		Mmap.put("list", SSRDao.getSearch_Stu_RegDataforPopup(id));

		return new ModelAndView("SearchStudent_Reg_Popup_Tiles");
	}
	
	@RequestMapping(value = "/getFilePath1", method = RequestMethod.POST)
	public @ResponseBody String getFilePath1(HttpSession session,HttpServletRequest request,String id) {

		String filePath = SSRDao.getFilePathQuery_popup1(Integer.parseInt(id));
		return filePath;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownloadDemo1")
	public void kmlFileDownloadDemo1(@ModelAttribute("path") String filePath, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;

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
	
	
	
	 @RequestMapping(value = "/get_inst_by_uni_nch_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_inst_by_uni_nch_ctrl(String university_id) {
	    	ArrayList<ArrayList<String>> data = SSRDao.get_inst_by_uni_nch_data(university_id);
	    	return data;
	 	}
	 
	 
	 @RequestMapping(value = "/Student_Registration_Report_Excel", method = RequestMethod.POST)
		public ModelAndView Student_Registration_Report_Excel(HttpSession session, HttpServletRequest request)
				throws ParseException {

		 //			String role = session.getAttribute("role").toString();
			String university_id = request.getParameter("university_id2");
			System.out.println("========university_id========="+university_id);
			String institute_id = request.getParameter("institute_id2");
			String name = request.getParameter("name2");
			String ayush_id = request.getParameter("ayush_id2");
			String gender = request.getParameter("gender2");
			String date_of_birth = request.getParameter("date_of_birth2");
			String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
			
			List<ArrayList<String>> non = new ArrayList<ArrayList<String>>(); ;

//			ArrayList<ArrayList<String>> listofdata = SSRDao.getStudent_Registration_Report_Excel(university_id, institute_id,
//					name, ayush_id, gender,date_of_birth);
			
			
			 if(stafflevel1.toUpperCase().equals("NCH")) {
				 non = SSRDao.getStudent_Registration_Report_Excel(university_id, institute_id,
						name, ayush_id, gender, date_of_birth);}
	            
	            if(stafflevel1.toUpperCase().equals("NCISM")) {
	            	non = SSNCISM.getStudent_Registration_Report_Excel_NCISM(university_id, institute_id,
	    					name, ayush_id, gender, date_of_birth);}

//			ArrayList<ArrayList<String>> non = new ArrayList<ArrayList<String>>();

			List<String> TH = new ArrayList<String>();

			TH.add("Ser No.");
			TH.add("Ayush Id");
			TH.add("University");
			TH.add("Institute");
			TH.add("Name");
			TH.add("Sur Name");
			TH.add("Father Name");
			TH.add("Mother Name");
			TH.add("Gender");
			TH.add("Date Of Birth");
			TH.add("Category");
			TH.add("Religion");
			TH.add("Marital Status");
			TH.add("Nationality");
			TH.add("State/UT Domicile");
			TH.add("District");
			TH.add("Neet Rank");
			TH.add("Neet Marks");
			TH.add("Neet Percentile");
			TH.add("University Enrollment No.");
			
			String Heading = "\nCope Code";
			
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Student_Registration_Excel("L", TH, non, Heading, username), "userList",
					non);
		}

		@RequestMapping(value = "/Student_Registration_Report_PDF", method = RequestMethod.POST)
		public ModelAndView Student_Registration_Report_PDF(HttpSession session, HttpServletRequest request,RedirectAttributes ra)
				throws ParseException {

			String username = session.getAttribute("username").toString();

//			String role1 = session.getAttribute("role").toString();
			String university_id = request.getParameter("university_id1");
			System.out.println("========university_id========="+university_id);
			String institute_id = request.getParameter("institute_id1");
			String name = request.getParameter("name1");
			String ayush_id = request.getParameter("ayush_id1");
			String gender = request.getParameter("gender1");
			String date_of_birth = request.getParameter("date_of_birth1");
			String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
//			String userId = session.getAttribute("userId_for_jnlp").toString();
//			String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
			List<ArrayList<String>> nonljhjh = new ArrayList<ArrayList<String>>(); ;
			
			
            if(stafflevel1.toUpperCase().equals("NCH")) {
			 nonljhjh = SSRDao.getStudent_Registration_Report_Excel(university_id, institute_id,
					name, ayush_id, gender, date_of_birth);}
            
            if(stafflevel1.toUpperCase().equals("NCISM")) {
    			 nonljhjh = SSNCISM.getStudent_Registration_Report_Excel_NCISM(university_id, institute_id,
    					name, ayush_id, gender, date_of_birth);}
                
            

//			ArrayList<ArrayList<String>> t1copolink2 = Edao.table1_co_po_link(course_id);
//					System.err.println("new_list2-------------"+t1copolink2);

			int total = nonljhjh.size();
			List<String> TH = new ArrayList<String>();

			TH.add("Ser No."); //0
			TH.add("Ayush Id");//1
			TH.add("University");//2
			TH.add("Institute");//3
			TH.add("Name");//4
			TH.add("Sur Name");//5
			TH.add("Father Name");//6
			TH.add("Mother Name");//7
			TH.add("Gender");//8
			TH.add("Date Of Birth");//9
			TH.add("Category");//10
			TH.add("Religion");//11
			TH.add("Marital Status");//12
			TH.add("Nationality");//13
			TH.add("State/UT Domicile");//14
			TH.add("District");//15
			TH.add("Neet Rank");//16
			TH.add("Neet Marks");//17
			TH.add("Neet Percentile");//18
			TH.add("University Enrollment No.");//19
			
			String Heading = "\nIn Inspection";
			
				  return new ModelAndView(new Student_Registration_PDF("L", TH, Heading, username, total ), "userList", nonljhjh);
					
			
			
			

		}
	
}