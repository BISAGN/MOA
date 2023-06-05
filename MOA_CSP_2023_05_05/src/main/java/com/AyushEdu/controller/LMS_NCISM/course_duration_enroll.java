package com.AyushEdu.controller.LMS_NCISM;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Course_EnrollDao;
import com.AyushEdu.dao.LMS_NCISM.Course_Duration_EnrollDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class course_duration_enroll {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@Autowired
	private Course_Duration_EnrollDao CDEDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "/Course_Duration_Enroll_Url")
	public ModelAndView Course_Duration_Enroll_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,@ModelAttribute("id1") String id, HttpServletRequest request) {
		
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Department_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}


		model.addAttribute("msg", msg);
		model.addAttribute("cid", id);
		return new ModelAndView("Course_Duration_Enroll_Tiles");
	}
	
	 @RequestMapping(value = "/getCourse_Set1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getCourse_Set1(String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetCourse_Set(course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getModule_fetch1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getModule_fetch1(String set,String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetModule_fetch(set,course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getSummary1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getSummary1(String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetSummary(course_id);	 
			return list;
		}
	 
	 //Description
	 @RequestMapping(value = "/getCourse_Description1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getCourse_Description1(String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetCourse_Description(course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getCourseDuration_Title1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getCourseDuration_Title1(String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetCourse_Title(course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "/getLearn_Count1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getLearn_Count1(String course_id) {
	    	
		 List<ArrayList<String>> list = CDEDAO.GetLearn_Count(course_id);	 
			return list;
		}
	 
	 @RequestMapping(value = "admin/videocourseenroll1")
		public void videocourseenroll1(@ModelAttribute("kmlFileId65") String id, @ModelAttribute("fildname") String fildname,
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			final int BUFFER_SIZE = 4096;
			String filePath = CDEDAO.getTopicVideoPath1(id);
			System.out.println("filePath----------------"+filePath);
			model.put("filePath", filePath);
			ServletContext context = request.getSession().getServletContext();
			
			try {
				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

					String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
					response.setHeader(headerKey, headerValue);
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

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
	 
//	 @RequestMapping(value = "/getCredit_Point1", method = RequestMethod.POST)
//		public @ResponseBody List<ArrayList<String>> getCredit_Point1(String course_id) {
//	    	
//		 List<ArrayList<String>> list = CDEDAO.GetCredit_Point(course_id);	 
//			return list;
//		}
	 
	 @RequestMapping(value = "/getSetModule_Fetch1", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getSetModule_Fetch1(String course_id,HttpSession session) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 List<ArrayList<String>> list = CDEDAO.GetSetModule_Fetch(course_id,userid);	 
			return list;
		}
	 
}
