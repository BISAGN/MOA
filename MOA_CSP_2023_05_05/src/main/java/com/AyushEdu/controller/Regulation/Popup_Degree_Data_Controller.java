package com.AyushEdu.controller.Regulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.AyushEdu.dao.Regulation.PopupDAO;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Popup_Degree_Data_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	PopupDAO  Rdao;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/uni_DegreePopupUrl", method = RequestMethod.POST)
	public ModelAndView uni_DegreePopupUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "popid", required = false) String id, @RequestParam(value = "name_of_institute", required = false) String name_of_institute,   HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("uni_DegreePopupUrl", roleid1);		
			if(val == false && !roleid1.equals("30") && !roleid1.equals("17")) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("list", Rdao.getuni_DegreeDataforPopup(id,name_of_institute));

		return new ModelAndView("Degree_Popup_Tiles");
	}
	@RequestMapping(value = "admin/DegreePopupUrl", method = RequestMethod.POST)
	public ModelAndView DegreePopupUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "popid", required = false) String popid,
			@RequestParam(value = "institute_name_hid", required = false) String institute_name_hid,  
			String Search, String orderColunm, String orderType,
			
			
			HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("intern_Search_State_pracUrl", roleid1);		
			if(val == false  && !roleid1.equals("30") && !roleid1.equals("17")) {
				return new ModelAndView("AccessTiles");
		}
		
		System.err.println("popid----"+popid);
		System.err.println("institute_name_hid----"+institute_name_hid);
		
		Mmap.put("msg", msg);
		Mmap.put("list", Rdao.getDegreeDataforPopup(  Search, orderColunm, orderType, popid,institute_name_hid));

		return new ModelAndView("Degree_Popup_Tiles");
	}
	
	
	
	///////////////////////////
	
//	@PostMapping("/DegreePopupUrl")
//	public @ResponseBody List<Map<String, Object>> DegreePopupUrl( ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,
//			String Search, String orderColunm, String orderType,String popid, String institute_name_hid) {
//		Mmap.put("msg", msg);
//		 
//		return Rdao.getDegreeDataforPopup(  Search, orderColunm, orderType, popid,institute_name_hid);
//	}
	
 
	
	
	
//////////////////////////////////
 
	@RequestMapping(value = "admin/IOCHPopupUrl", method = RequestMethod.POST)
	public ModelAndView IOCHPopupUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "popidIOCH", required = false) String id,HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("IOCHPopupUrl", roleid1);		
			if(val == false && !roleid1.equals("30") && !roleid1.equals("17")) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("list", Rdao.getIOCHDataforPopup(id));
		return new ModelAndView("IOCH_Popup_Tiles");
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownload69")
	public void kmlFileDownload44(@ModelAttribute("kmlId2") int id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;


		String filePath = Rdao.getFilePathQuery_popup(id);

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
	
	@RequestMapping(value = "/getFilePath", method = RequestMethod.POST)
	public @ResponseBody String getFilePath(HttpSession session,HttpServletRequest request,String id) {

		System.err.println("getFilePathid--------"+id);
		String filePath = Rdao.getFilePathQuery_popup(Integer.parseInt(id));

		System.err.println("FilePath "+filePath);

		return filePath;
	}
	
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownloadDemo")
	public void kmlFileDownloadDemo(@ModelAttribute("path") String filePath, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;


System.err.println("================="+filePath);
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


}
