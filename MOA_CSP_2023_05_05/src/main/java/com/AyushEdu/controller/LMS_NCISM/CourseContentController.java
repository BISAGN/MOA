package com.AyushEdu.controller.LMS_NCISM;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_COURSE_CONTENT_CHILD;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_COURSE_CONTENT_VIEW_HISTORY;
import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_NCISM.Course_Content_Dao;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class CourseContentController {
	
	@Autowired
	Course_Content_Dao Cdao;

	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/CoursecontentUrl", method = RequestMethod.GET)
	public ModelAndView CoursecontentUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		try {	
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("CoursecontentUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 String role = session.getAttribute("role").toString();
		 Mmap.put("msg", msg);
		 Mmap.put("system_name_list", common.getSystemListbyrole(sessionFactory,role));
		 Mmap.put("course_name_list", common.getCourseNamelist(sessionFactory));
		 Mmap.put("module_name_list", common.getModuleName(sessionFactory));
		 Mmap.put("toc_list", common.getTypeOfcontent(sessionFactory));
		 Mmap.put("level_list", common.getlevel_Ofcontentlist(sessionFactory));
		 Mmap.put("d_name", common.getDegreeList(sessionFactory));
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CoursecontentTiles");
	}
	
	
	@PostMapping(value = "/CourseAction")
	public ModelAndView Module_action(@Validated @ModelAttribute("courseCMD") TB_COURSE_CONTENT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {
		
		 if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		  }
		  String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CoursecontentUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		  }
			
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		
		String username = principal.getName();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String type_of_content = request.getParameter("type_of_content");
		String system_name = request.getParameter("system_name");
		String course_name = request.getParameter("course_name");
		String degree_name = request.getParameter("degree");

		if (type_of_content.equals("0")) {
			ra.addAttribute("msg", "Please Select Type Of Lecture.");
			return new ModelAndView("redirect:CoursecontentUrl");
		}
		if (system_name.equals("0")) {
			ra.addAttribute("msg", "Please Select System Name.");
			return new ModelAndView("redirect:CoursecontentUrl");
		}
		if (course_name.equals("0")) {
			ra.addAttribute("msg", "Please Select Course Name.");
			return new ModelAndView("redirect:CoursecontentUrl");
		}
		if (degree_name.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree Name.");
			return new ModelAndView("redirect:CoursecontentUrl");
		}


		try {

			Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  TB_COURSE_CONTENT_MSTR where course_name=:course_name and type_of_content=:type_of_content \n"
							+ " and system_name=:system_name and degree_name=:degree_name \n"
							+ " and id!=:id")
					.setParameter("type_of_content", Integer.parseInt(type_of_content))
					.setParameter("course_name", Integer.parseInt(course_name))
					.setParameter("degree_name", Integer.parseInt(degree_name))
					.setParameter("system_name", Integer.parseInt(system_name))
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				if (c == 0) {
					
//					td.setType_of_content(Integer.parseInt(type_of_content));
					td.setSystem_name(Integer.parseInt(system_name));
					td.setCourse_name(Integer.parseInt(course_name));
					td.setDegree_name(Integer.parseInt(degree_name));
					td.setApp_status(1);
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					EDU_LMS_COURSE_CONTENT_CHILD od = new EDU_LMS_COURSE_CONTENT_CHILD();
					
					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for(int i=1; i <= count_hidden_att; i++) {
						
						String module_name = request.getParameter("module_name"+i);
						String level_of_module = request.getParameter("level_of_module"+i);
						
						String ref_video = gd(request, mul, session, "ref_video"+i);
						String upload_file = dg(request, mul, session, "upload_file"+i);
						String other_note = dg(request, mul, session, "other_note"+i);
						String upload_ppt = dg(request, mul, session, "upload_ppt"+i);
						String video_duration = request.getParameter("video_duration"+i);
					
							if (module_name.equals("0")) {
								ra.addAttribute("msg", "Please Select Module Name.");
								return new ModelAndView("redirect:CoursecontentUrl");
							}
							if (level_of_module.equals("0")) {
								ra.addAttribute("msg", "Please Select Level of Module.");
								return new ModelAndView("redirect:CoursecontentUrl");
							}
							if (upload_ppt.isEmpty() && other_note.isEmpty() && ref_video == "" && upload_file == "") {
								ra.addAttribute("msg", "Please Upload at Least One File.");
								return new ModelAndView("redirect:CoursecontentUrl");
							}

							od.setOther_note(other_note);
							od.setUpload_ppt(upload_ppt);
						 	od.setModule(Integer.parseInt(module_name));
						 	od.setLevel_of_module(Integer.parseInt(level_of_module));
						 	od.setRef_video(ref_video);
						 	od.setUpload_file(upload_file);
						 	od.setVideo_duration(video_duration);
						 	od.setP_id(parent_id);
						 	od.setStatus(1);
						 	od.setCreated_by(username);
						 	od.setCreated_date(date);
							
							sessionHQL.save(od);
							sessionHQL.flush();
							sessionHQL.clear();
						
					}
					
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
					
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

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
		return new ModelAndView("redirect:CoursecontentUrl");
	}
	
	public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "VideoPath");

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
	
	//==========================================PDF VIEW FOR POLICY========================================== 	
	
		public String dg(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
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

	@PostMapping("/getFiltercourse_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterRegistration_data(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String course_name, String module_name,String level_of_module,String type_of_content,String system_name,String degree) {
		String role = session.getAttribute("role").toString();

		return (ArrayList<Map<String, Object>>) Cdao.Course_nameDataList(startPage, pageLength, Search, orderColunm,
				orderType, course_name, module_name,level_of_module,type_of_content,system_name,degree, role);
	}

	@PostMapping("/getTotalcourse_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String course_name,String module_name ,String level_of_module, String type_of_content,String system_name,String degree) {
		
		String role = sessionUserId.getAttribute("role").toString();
		
		return Cdao.DataTotalCount(Search, course_name,module_name,level_of_module,type_of_content,system_name,degree,role);
	}
	
	@PostMapping(value = "/deletecourse_Url")
	public ModelAndView deletecourse_Url(@ModelAttribute("id2") int id, String username,RedirectAttributes ra) {
		int rowCount = 0;
		
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "delete from EDU_LMS_COURSE_CONTENT_CHILD where p_id=:p_id";
			@SuppressWarnings("deprecation")
			Query q = session.createQuery(hql)
					.setInteger("p_id", id);
			
			String hql1 = "delete from TB_COURSE_CONTENT_MSTR where id=:id";
			@SuppressWarnings("deprecation")
			Query q1 = session.createQuery(hql1)
					.setInteger("id", id);

			rowCount = q.executeUpdate();

			tx.commit();
			session.close();
		} catch (Exception e) {
		}
		if (rowCount > 0) {
			ra.addAttribute("msg","Delete Successfully");
			return new ModelAndView("redirect:CoursecontentUrl");
		} else {
			ra.addAttribute("msg","Delete UnSuccessfully");
			return new ModelAndView("redirect:CoursecontentUrl");
		}
	}
	
	
//	course approve 
	
	@RequestMapping(value = "Course_Approve_url", method = RequestMethod.POST)
	public ModelAndView Course_Approve_url(@ModelAttribute("Acceptid") int id,HttpSession session,ModelMap model,
			TB_COURSE_CONTENT_MSTR obj) {
		

	List<String> liststr = new ArrayList<String>();

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	 Date date = new Date();

		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from TB_COURSE_CONTENT_MSTR set app_status='1',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();
	 
		tx.commit();
		sessionHQL.close();
		if (app2 > 0 ) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Not Approved.");
		}
		model.put("msg", liststr.get(0));

	 return new ModelAndView("redirect:CoursecontentUrl");
	}
	
	@RequestMapping(value = "Course_Reject_url", method = RequestMethod.POST)
	public ModelAndView Course_Reject_url(@ModelAttribute("Rejectid") int id,HttpSession session,ModelMap model,
			TB_COURSE_CONTENT_MSTR obj) {

	List<String> liststr = new ArrayList<String>();
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	 Date date = new Date();

	 	String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from TB_COURSE_CONTENT_MSTR set app_status='2',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();
	 
		tx.commit();
		sessionHQL.close();
		if (app2 > 0 ) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Not Reject.");
		}
		model.put("msg", liststr.get(0));
	 
		return new ModelAndView("redirect:CoursecontentUrl");
	}
	
 	@SuppressWarnings("null")
		@RequestMapping(value = "/kmlFileDownload444")
		public void kmlFileDownload444(@ModelAttribute("kmlFileId455") String id, 
				@ModelAttribute("fildname1") String fildname, ModelMap model, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws IOException {
	
			final int BUFFER_SIZE = 4096;
	System.err.println("INSIDE");
	
			String filePath = Cdao.getFilePathQueryForDocFile(id,fildname);
	
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
	
 	
 	@RequestMapping(value = "getVideoviewhistoryrecords", method = RequestMethod.POST)
	public @ResponseBody String getVideoviewhistoryrecords(String user_id, String video_id,String username, String id)
		{
 		
 		ArrayList<ArrayList<String>> lom = Cdao.getlevelofmodule(video_id);
 		String level_of_module =lom.get(0).get(0);
 	
		String output="";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date now = new Date();
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_COURSE_CONTENT_VIEW_HISTORY where  video_id=:video_id  and level_of_module=:level_of_module and user_id=:user_id")
					.setParameter("user_id", user_id)
					.setParameter("video_id", video_id)
			 		.setParameter("level_of_module", level_of_module).uniqueResult();
			
			
			if (c == 0) {
				EDU_LMS_COURSE_CONTENT_VIEW_HISTORY vh = new EDU_LMS_COURSE_CONTENT_VIEW_HISTORY();
				vh.setUser_id(user_id);
				vh.setVideo_id(video_id);
				vh.setLevel_of_module(level_of_module);
				vh.setCreated_by(username);
				vh.setCreated_date(now);
				sessionHQL.save(vh);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				output ="recrd saved";
			}
			
		}catch(Exception e){
			output="Record Not Updated Successfully";
		}

		return null;

		}

/////////////////////////////////////file upload
 	
 	public String fileupload(byte[] b, String name, int id, String type) {
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			//String rootPath = System.getProperty("catalina.home");
//			File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
			 File dir = new File("/srv/Document/"+id);
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}
			// Create the file on server
			// java.util.Date date= new java.util.Date();
			// fname = dir.getAbsolutePath() + File.separator+""+type+"__" + (new
			// Timestamp(date.getTime())).toString().replace(":",
			// "").toString().replace(".", ".").toString().replace("
			// ","").toString().replace("-","").toString()+ "."+extension;
			fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
					+ extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.err.println("fname --- file upload -----     "+fname);
		return fname;
	}
 	public String currentDateWithTimeStampString() {
		java.util.Date date = new java.util.Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
	}
 	
	@RequestMapping(value = "/getmodulebycourse", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getmodulebycourse(String course_id)  {
		List<EDU_LMS_MODULE_MSTR> list =  common.getModulnameList_course(sessionFactory,course_id);
		return list;
	}
	

	
	
}
