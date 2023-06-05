package com.AyushEdu.controller.Registration.Graduation_NCISM;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Search_Ncism_Student_RegistrationController {
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Search_Ncism_Student_RegistrationDao SSRDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/Search_Ncism_Students_RegistrationUrl", method = RequestMethod.GET)
	public ModelAndView Search_Ncism_Students_RegistrationUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Ncism_Students_RegistrationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		Mmap.put("msg", msg);
		Mmap.put("getUniverCityList", commondao.getUniversityNcismlist());
		Mmap.put("getinstitutelist", SSRDao.getinstitute_Ncismlist(userid));
		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		
		String role = session.getAttribute("roleid").toString();
		if (role.equals("20")) {//uni ncism
			String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
			Mmap.put("uni_id",uni_id);
			}
			if (role.equals("22")) {//inst ncism
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				Mmap.put("inst_id",inst_id);
			}
		return new ModelAndView("Search_Ncism_Students_Registration_Tiles");
	}
	
	@PostMapping("/getFilterSearch_Ncism_Stu_Reg_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_Ncism_Stu_Reg_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		return SSRDao.DataTableSearch_Stu_RegData_NcismList(startPage, pageLength, Search, orderColunm, orderType, university_id, institute_id,name,ayush_id,gender,date_of_birth);
	}
	
	@PostMapping("/getTotalSearch_Ncism_Stu_Reg_dataCount")
	public @ResponseBody long getTotalSearch_Ncism_Stu_Reg_dataCount(HttpSession sessionUserId, String Search, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth) {
		return SSRDao.DataTableSearch_Stu_Reg_NcismDataTotalCount(Search, university_id, institute_id,name,ayush_id,gender,date_of_birth);
	}
	
	@RequestMapping(value = "/Search_Stu_Reg_NcismPopupUrl", method = RequestMethod.POST)
	public ModelAndView Search_Stu_Reg_NcismPopupUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "popid", required = false) String id,HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Ncism_Students_RegistrationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		Mmap.put("msg", msg);
		Mmap.put("list", SSRDao.getSearch_Stu_Reg_NcismDataforPopup(id));
		return new ModelAndView("SearchStudent_Reg_Popup_Tiles");
	}
	
	@RequestMapping(value = "/getFile_NcismPath1", method = RequestMethod.POST)
	public @ResponseBody String getFile_NcismPath1(HttpSession session,HttpServletRequest request,String id) {

		String filePath = SSRDao.getFilePathQuery_Ncism_popup1(Integer.parseInt(id));
		return filePath;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownloadNcismDemo1")
	public void kmlFileDownloadNcismDemo1(@ModelAttribute("path") String filePath, ModelMap model, HttpServletRequest request,
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
	
	 @RequestMapping(value = "/get_inst_by_uni_ncism_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_inst_by_uni_ncism_ctrl(String university_id) {
	    	ArrayList<ArrayList<String>> data = SSRDao.get_inst_by_uni_ncism_data(university_id);
	    	return data;
	 	}
	
}