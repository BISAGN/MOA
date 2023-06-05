package com.AyushEdu.controller.LMS_Practitioner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.LMS_Practitioner.TB_ADD_LECTURES;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Practitioner.Add_Lecture_DAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Practitioner_Add_Lecture_Controller {
	
	@Autowired
	Add_Lecture_DAO ALdao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@RequestMapping(value = "/admin/AddLecturesUrl", method = RequestMethod.GET)
	public ModelAndView AddLecturesUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("AddLecturesUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String role = session.getAttribute("role").toString();
		
		
		
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		 Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));

		return new ModelAndView("AddLecturesTiles");
	}
	
	@PostMapping(value = "/AddLecturesAction")
	public ModelAndView AddLecturesAction(@Validated @ModelAttribute("addlecCMD") TB_ADD_LECTURES td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String system = request.getParameter("system");
		String degree = request.getParameter("degree");
//		String role = session.getAttribute("role").toString();
		
		Date from_date = null;
		if (!request.getParameter("from_date").equals("")) {
			String l[] = request.getParameter("from_date").split("/");
			String from_date_i = l[2]+"-"+l[1]+"-"+l[0];
			from_date = format.parse(from_date_i);
		} else {
			from_date = null;
		}
		Date to_date = null;
		if (!request.getParameter("to_date").equals("")) {
			String l[] = request.getParameter("to_date").split("/");
			String to_date_i = l[2]+"-"+l[1]+"-"+l[0];
			to_date = format.parse(to_date_i);
		} else {
			to_date = null;
		}
		
		try {
			
			int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
			
			for(int i=1; i <= count_hidden_att; i++) {
				
				String lec_video = gd(request, mul, session, "ref_video"+i);
				String lec_pdf = doc(request, mul, session, "document_upload"+i);
				
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_ADD_LECTURES where  system=:system and degree=:degree and from_date=:from_date and to_date=:to_date and lec_video=:lec_video and lec_pdf=:lec_pdf and id !=:id")
					.setParameter("system", Integer.parseInt(system))
					.setParameter("degree", Integer.parseInt(degree))
					.setParameter("from_date", from_date)
					.setParameter("to_date", to_date)
					.setParameter("lec_video",lec_video)
					.setParameter("lec_pdf", lec_pdf)
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				
				td.setCreated_by(username);
				td.setCreated_date(date);
				
				if (c == 0) {
					
					td.setSystem(Integer.parseInt(system));
					td.setDegree(Integer.parseInt(degree));
					td.setFrom_date(from_date);
					td.setTo_date(to_date);
					td.setLec_video(lec_video);
					td.setLec_pdf(lec_pdf);
					
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");

				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
		}
			tx.commit();

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:AddLecturesUrl");
	}
	
	
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
	
public String doc(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
		
		System.err.println("id----"+id);
	
	String extension=""; //add line
	String fname = ""; //add line
	
	request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
	
	MultipartFile file = mul.getFile(id);
	if (!file.getOriginalFilename().isEmpty()) {
		
		byte[] bytes = file.getBytes();
		String  mnhFilePath = session.getAttribute(id).toString();
		
        File dir = new File(mnhFilePath);
		if (!dir.exists())
			dir.mkdirs();
		String filename = file.getOriginalFilename();
				
		int j = filename.lastIndexOf('.');
		if (j >= 0) {
			extension = filename.substring(j+1);
		}
		java.util.Date date1= new java.util.Date();
		fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
		
		File serverFile = new File(fname);	               
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);	                
		stream.close();

	}else {

		
	}
	return fname;
	
	}

	@PostMapping("/getFilterLecture_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterLecture_data(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system, String degree,String from_date, String to_date) {
		return (ArrayList<Map<String, Object>>) ALdao.ADDLecDataList(startPage, pageLength, Search, orderColunm,orderType, 
				system, degree,from_date,to_date);
	}

	@PostMapping("/getTotalLecture_dataCount")
	public @ResponseBody long getTotalLecture_dataCount(HttpSession sessionUserId, String Search,
			String system,String degree,String from_date, String to_date) {
		return ALdao.DataTotalCount(Search, system,degree,from_date,to_date);
	}
	
	@RequestMapping(value = "admin/kmlLOFileDownloadLEC")
	public void kmlLOFileDownloadLEC(@ModelAttribute("kmlFileIdLEC") String id, @ModelAttribute("fildname") String fildname,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = ALdao.getLecVideoPath(Integer.parseInt(id));
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
	
	@RequestMapping(value = "/admin/ViewLecturesUrl", method = RequestMethod.GET)
	public ModelAndView ViewLecturesUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("ViewLecturesUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		
		Mmap.put("msg", msg);
		return new ModelAndView("ViewLecturesTiles");
	}
	
	@PostMapping("/getFilterLecture_dataVIEW")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterLecture_dataVIEW(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType) {
		return (ArrayList<Map<String, Object>>) ALdao.ViewLecDataList(startPage, pageLength, Search, orderColunm,orderType);
	}

	@PostMapping("/getTotalLecture_dataCountVIEW")
	public @ResponseBody long getTotalLecture_dataCountVIEW(HttpSession sessionUserId, String Search) {
		return ALdao.DataTotalCountView(Search);
	}
	
	@PostMapping(value = "/DeleteLectureUrl")
	public @ResponseBody ModelAndView DeleteLectureUrl(@ModelAttribute("delLecid") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		//SECURITY RAHUL
		
				if(request.getHeader("Referer") == null ) { 
					// session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("DeleteLectureUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		
		
		List<String> liststr = new ArrayList<String>();
		
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "delete from  TB_ADD_LECTURES where id=:id";
			 
		@SuppressWarnings({ "rawtypes", "deprecation" })
			int app = sessionHQL.createQuery(hqlUpdate)
			.setParameter("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete Unsuccessfull");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:AddLecturesUrl");
	}


}
