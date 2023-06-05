package com.AyushEdu.controller.LMS_NCISM;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_SYSTEM_COURSE_DURATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;
import com.AyushEdu.dao.LMS_NCISM.CourseDuration_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class CourseDuration_Ctrl {

//	@Autowired
//	Course_Content_Dao Cdao;

	
	@Autowired
	CommonController common;
	@Autowired
	private CommonController com;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	CourseDuration_Dao CD;
	
	@Autowired
	Stud_Elect_Courses_Dao sdc;
	
	@Autowired
	ValidationController validation;
	
	
	@RequestMapping(value = "/CourseDurationUrl", method = RequestMethod.GET)
	public ModelAndView CourseDurationUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 try {	
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("CourseDurationUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		 String role = session.getAttribute("role").toString();
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
		 Mmap.put("gettermList",common.NCISMgettermList(sessionFactory));
	//		Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
		}		 
		return new ModelAndView("CourseDurationTiles");
	}
	
	@PostMapping(value = "/CourseDurationAction")
	public ModelAndView CourseDurationAction(
			@Validated @ModelAttribute("CourseDurationCMD") EDU_LMS_SYSTEM_COURSE_DURATION CD,
			 BindingResult result, HttpServletRequest request,MultipartHttpServletRequest mul,
			ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra) throws IOException, ParseException {
		
		  if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		  }
		  String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CourseDurationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		  }
			
	     Date date = new Date();
	     String username = principal.getName();
	
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		String add_elective_name = request.getParameter("add_elective_name");
		String remove_elective_name = request.getParameter("remove_elective_name");
		String old_elective_name = request.getParameter("old_elective_name");
		String new_elective_name = request.getParameter("new_elective_name");
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String term_id = request.getParameter("term_id");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String course_fee = request.getParameter("course_fee");
		String course_switch_duration= request.getParameter("course_switch_duration");
								
		if (start_date.trim().equals("") || start_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select From Date");
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (end_date.trim().equals("") ||  end_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select To Date");
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (system_id.equals("0") ) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:CourseDurationUrl");
			}
		if (degree_id.equals("0") ) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:CourseDurationUrl");
			}
		if (term_id.equals("0") ) {
			ra.addAttribute("msg", "Please Select Term");
			return new ModelAndView("redirect:CourseDurationUrl");
			}
		if (course_switch_duration.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Course Switch Duration");
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (validation.isOnlyNumer(course_switch_duration) == true) {
			ra.addAttribute("msg", "Course Switch Duration " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (validation.maxlengthcheck2(course_switch_duration) == false) {
			ra.addAttribute("msg","Course Switch Duration "+ validation.MaxlengthcheckMSG2);
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (course_fee.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Course Fee");
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (validation.isOnlyNumer(course_fee) == true) {
			ra.addAttribute("msg","Course Fee "+ validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (validation.maxlengthcheck10(course_fee) == false) {
			ra.addAttribute("msg","Course Fee "+ validation.MaxlengthcheckMSG10);
			return new ModelAndView("redirect:CourseDurationUrl");
		}
		if (new_elective_name.equals("") ) {
			ra.addAttribute("msg", "Please Select Courses");
			return new ModelAndView("redirect:CourseDurationUrl");
			}
		
		
		try {
			
			Date s_to = null;
			s_to = format.parse(start_date);
			Date e_to = null;
			e_to = format.parse(end_date);
			
			List<String> newList = new ArrayList<String>();
			if (new_elective_name != null && !new_elective_name.equals("")) {
				newList = Arrays.asList(new_elective_name.split(","));
			}
			
			List<String> addList = new ArrayList<String>();
			List<String> removeList = new ArrayList<String>();
			
			if (add_elective_name != null && !add_elective_name.equals("")) {
				addList = Arrays.asList(add_elective_name.split(","));
			}
			if (remove_elective_name != null && !remove_elective_name.equals("")) {
				removeList = Arrays.asList(remove_elective_name.split(","));
			}
			
			if (removeList.size() > 0) {
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				for (int i = 0; i < removeList.size(); i++) {
					String hqlUpdate = "delete from EDU_LMS_SYSTEM_COURSE_DURATION where system_id=:system_id and course_id=:course_id and start_date=:start_date and end_date=:end_date";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setParameter("system_id",Integer.parseInt(system_id))
							.setParameter("course_id",Integer.parseInt((removeList.get(i))))
							.setParameter("start_date", s_to)
							.setParameter("end_date", e_to).executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
				}
			}
			
			if (addList.size() > 0) {
				String maxAID = getMaxAID();
				int newn=0;
				if(maxAID!=null) {
					 newn = Integer.parseInt(maxAID);
					
				}
				newn++;
				String abc = String.format("%6s", newn).replace(' ', '0');
				abc = "CD"+abc;
				for (int i = 0; i < addList.size(); i++) {
					Session sessionHQLadd = this.sessionFactory.openSession();
					Transaction txadd = sessionHQLadd.beginTransaction();
					
					
					
					EDU_LMS_SYSTEM_COURSE_DURATION obj = new EDU_LMS_SYSTEM_COURSE_DURATION();
					obj.setCreated_by(username);
					obj.setCreated_date(date);
					obj.setSystem_id(Integer.parseInt(system_id));
					obj.setCourse_id(Integer.parseInt(addList.get(i)));
					obj.setCourse_fee(course_fee);
					obj.setCourse_switch_duration(course_switch_duration);
					obj.setCd_uniq_id(abc);
					obj.setStart_date(s_to);
					obj.setEnd_date(e_to);
					obj.setDegree_id(Integer.parseInt(degree_id));
					obj.setTerm_id(Integer.parseInt(term_id));
					
					sessionHQLadd.save(obj);
					sessionHQLadd.flush();
					sessionHQLadd.clear();

					TB_NOTIFICATION noti = new TB_NOTIFICATION();
					
					//List<String> instList = com.getInstListFromSystem(String.valueOf(td.getDegree_id()));
//					String allinst = "";
////					System.err.println("INST LIST---1-"+instList);
//					for(int i=0;i<instList.size();i++) {
//						if(i==0) {
//							 allinst = instList.get(i);
//						}else {
//						 	allinst = allinst+","+instList.get(i);
//						}
//					}
//					System.err.println("INST LIST---2-"+allinst);
					noti.setCreated_by(username);
					noti.setCreated_date(date);
					noti.setStatus("0");
					noti.setMessage("New Course Added");
					noti.setFrom_name_send(username);
					noti.setTo_name_sent("-1");
					
					txadd.commit();
				}
			}
				ra.addAttribute("msg", "Data Saved Successfully.");
	} catch (RuntimeException e) {
		try {
			ra.addAttribute("msg", "roll back transaction");
		} catch (RuntimeException rbe) {
			ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
		}
		throw e;
	} finally {
//		if (sessionHQL != null) {
//			sessionHQL.close();
//		}
	}
		return new ModelAndView("redirect:CourseDurationUrl");
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
	
	@RequestMapping(value = "/getSystem_CourseDuration", method = RequestMethod.POST)
	public @ResponseBody List<Map> getSystem_CourseDuration(String system_id,String start_date,String end_date) throws ParseException {
	
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date s_to = null;
		s_to = format.parse(start_date);
		Date e_to = null;
		e_to = format.parse(end_date);
		String hqlUpdate = "select cast(course_id as text),course_fee,demo_video,course_switch_duration from EDU_LMS_SYSTEM_COURSE_DURATION where system_id=:system_id and start_date=:start_date and end_date=:end_date";
		Query query = sessionHQL.createQuery(hqlUpdate).setParameter("system_id",Integer.parseInt(system_id)).setParameter("start_date", s_to).setParameter("end_date", e_to);
		List<Map> list = (List<Map>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@Autowired
	private DataSource dataSource;	
	
	public String getMaxAID() {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			query = "select max(id) from edu_lms_system_course_duration";
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("max");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	System.err.println("MAXXAID----------"+reg_no);
		return reg_no;
	}
		

	
	@RequestMapping(value = "/getcoursewithcoursecontentbysystem", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getcoursewithcoursecontentbysystem(HttpSession session,String system_id,String degree_id,String term_id) {
		return CD.getCourses_System_degree_term_Fetch(system_id,degree_id,term_id);
	}
	

	@GetMapping(value = "/Search_Course_Duration_url")
	public ModelAndView Search_Course_Duration_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Course_Duration_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

			String role = session.getAttribute("role").toString();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		model.addAttribute("msg", msg);
		model.put("getCourse_Duration", CD.getCourse_Duration(sessionFactory,userId));
		model.put("getCourse_Start_Date", CD.getCourse_Start_Date(sessionFactory));
		model.put("getCourse_End_Date", CD.getCourse_End_Date(sessionFactory));
		model.put("gettermList",common.gettermList(sessionFactory));
		model.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
		
		return new ModelAndView("Search_Course_Duration_Tiles");
}
	

	
	//-------------------My Courses start code--------------------------//
	
	@RequestMapping(value = "/Image_Fetch_Path_Already_Applied_My_Courses_Search", method = RequestMethod.GET)
	public void Image_Fetch_Path_Already_Applied_My_Courses_Search(@ModelAttribute("i_id") String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		

		final int BUFFER_SIZE = 4096;

		String i_id = id;

		
		String filePath = CD.Already_Applied_Path_fetch_list_My_Courses_Search(i_id);
		

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

			System.out.println(ex);
			
			
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
	
	@RequestMapping(value = "/getDescriptionfetchAlreadyAppliedMyCourses_Search", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses_Search(HttpSession session,String course_duration,String course_start_date,
			String system_id,String degree_id,String term_id) {

		String role = session.getAttribute("role").toString();
		System.err.println("role=====" +role);
		return CD.getDescriptionfetchAlreadyAppliedMyCourses_List_Search(course_duration,course_start_date,system_id,degree_id,term_id,role);
	}	
	
	
	
		
}
