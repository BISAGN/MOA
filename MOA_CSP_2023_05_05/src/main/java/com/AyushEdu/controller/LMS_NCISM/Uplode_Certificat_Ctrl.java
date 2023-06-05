package com.AyushEdu.controller.LMS_NCISM;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_UPLODE_CERTIFICATE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_NCISM.Student_FreeCourseRepot_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Uplode_Certificat_Ctrl {
	
	 @Autowired
	 private SessionFactory sessionFactory;
	
	 @Autowired
	 Student_FreeCourseRepot_DAO std;
	 
	 @Autowired
	 private RoleBaseMenuDAO roledao;
	 
     @Autowired
     CommonController common;
	
  @RequestMapping(value = "/uplode_certificate")
	public ModelAndView Edit_Online_Coures_Url(@ModelAttribute("id3") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, 
			HttpSession sessionEdit, HttpServletRequest request, ServletRequest session) {
	  
	//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("uplode_certificate", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	  
	  

	    EDU_LMS_FREE_COURSE Onlinecourese = std.getolinecourseByid(Integer.parseInt(updateid));
	    Mmap.put("Onlinecourese", Onlinecourese);
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("uplode_Certificate_Tiles");
	}
  

  
  
	@RequestMapping(value = "/uploadcertificatePopup", method = RequestMethod.POST)
	public @ResponseBody EDU_LMS_FREE_COURSE uploadcertificatePopup(
			String updateid) {
		   EDU_LMS_FREE_COURSE Onlinecourese = std.getolinecourseByid(Integer.parseInt(updateid));
		 
		 return Onlinecourese;
	}
  
  
  
	
	    @PostMapping(value = "/uplode_certificateUrl")
		public ModelAndView uplode_certificateUrl(@Validated @ModelAttribute("UplodeCertificate_CMD") EDU_LMS_UPLODE_CERTIFICATE td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,RedirectAttributes ra,
				MultipartHttpServletRequest mul)throws IOException, ParseException {

			String uplode_certificate = gd(request, mul, session, "uplode_certificate");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String coursename = request.getParameter("coursename");

			if (uplode_certificate == null || uplode_certificate.trim().equals("")) {
				ra.addAttribute("msg", "Please Uplode Certificate");
				return new ModelAndView("redirect:Free_Course_ViewUrl");
			}
			if (start_date == null || start_date.trim().equals("") || start_date.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Select Start Date");
				return new ModelAndView("redirect:Free_Course_ViewUrl");
			}
			if (end_date == null || end_date.trim().equals("") || end_date.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Select End Date");
				return new ModelAndView("redirect:Free_Course_ViewUrl");
			}

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				
				if (id == 0) {
					td.setUsername(username);
					td.setCreated_date(date);
					td.setUplode_certificate(uplode_certificate);
					td.setStart_date(start_date);
					td.setEnd_date(end_date);
					td.setCoursename(coursename);
				
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				tx.commit();

			} catch (RuntimeException e) {
				try {

					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:Free_Course_ViewUrl");
		}
	
	 
	 //====================================file uplode=================================================//
	   
	   public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
				throws IOException {

			String extension = ""; // add line
			String fname = ""; // add line

			request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

			MultipartFile file = mul.getFile(id);
			if (!file.getOriginalFilename().isEmpty()) {

				byte[] bytes = file.getBytes();
				String mnhFilePath = session.getAttribute(id).toString();

				File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();

				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j + 1);
				}
				java.util.Date date1 = new java.util.Date();
				fname = dir.getAbsolutePath()
						+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
								.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
						+ "." + extension;

				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			} else {
			}
			return fname;

		}
	   
	   //===========================================================================================//
	  
	   
		@RequestMapping(value = "/Student_Coures_Dtl", method = RequestMethod.GET)
		public ModelAndView Student_Coures_Dtl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			Mmap.put("getfreecoursename", common.getfreecoursename(sessionFactory));
			Mmap.put("getStudentlist", common.getStudentlist(sessionFactory));
			Mmap.addAttribute("msg", msg);
			return new ModelAndView("Student_couresTiles");
		}

		@RequestMapping(value = "/getFilterstu_dtl_data", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getFilterstu_dtl_data(int startPage, int pageLength, String Search,
				String start_date, String orderColunm, String orderType, String end_date, String username,
				String coursename, HttpSession sessionUserId) {
			return std.getFilterstu_dtl_data( startPage,  pageLength,
					 Search,  start_date, orderColunm,  orderType,  end_date, username,
					 coursename, sessionUserId);
		}

		@RequestMapping(value = "/getTotalstu_dtl_dataCount", method = RequestMethod.POST)
		public @ResponseBody long getTotalstu_dtl_dataCount(String Search, String start_date, String username, String end_date,
				String coursename, HttpSession sessionUserId) {
			return std.getTotalstu_dtl_dataCount(Search, start_date, username, end_date, coursename, sessionUserId);

		}
	   
	 //============================================================================================================//  
	   
	   @SuppressWarnings("null")
		@RequestMapping(value = "/getstudentDocument")
		public ModelAndView getstudentDocument(@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
				
				ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
				throws IOException {


			String url = pageUrl;
			String EXTERNAL_FILE_PATH = "";

			EXTERNAL_FILE_PATH = std.getFilePathQueryForDocFile(Integer.parseInt(doc_id1));
			
			if (EXTERNAL_FILE_PATH != "") {
				File file = null;
				file = new File(EXTERNAL_FILE_PATH);
				try {
					if (!file.exists()) {
						
						model.put("msg", "Sorry.The file you are looking for does not exist!");
						return new ModelAndView(url);
					}
				} catch (Exception exception) {
					
				}

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
				response.setContentLength((int) file.length());
				InputStream inputStream = null;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
					model.put("msg", "Downloaded Successfully");
					return new ModelAndView(url);
				} catch (FileNotFoundException e) {
					//e.printStackTrace();
				}
			} 
			return new ModelAndView(url);
		}
}