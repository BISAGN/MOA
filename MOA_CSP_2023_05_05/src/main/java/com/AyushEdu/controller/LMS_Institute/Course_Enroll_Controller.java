package com.AyushEdu.controller.LMS_Institute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_COURSE_CONTENT_CHILD;
import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Course_EnrollDao;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Course_Enroll_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	CommonController common;
	@Autowired
	private Course_EnrollDao CDDAO;
	
	@Autowired
	Stud_Elect_Courses_Dao sdc;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@GetMapping(value = "/Course_Enroll_Url")
	public ModelAndView Course_Enroll_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "set_id", required = false) String set_id,
			@RequestParam(value = "exit_id1", required = false) String exit_id1,
			@RequestParam(value = "exit_course_id", required = false) String exit_course_id,
			@RequestParam(value = "exit_id_Payment1", required = false) String exit_id_Payment1,
			@RequestParam(value = "termhidden1", required = false) String termhidden1,
			@ModelAttribute("id1") String id,
			HttpServletRequest request) {
		try {
			String userId = session.getAttribute("userId_for_jnlp").toString();
			String system_id1 = sdc.getsystem_list(userId).get(0).get(0);
			String degree_id1 = sdc.getdegree_list(userId).get(0).get(0);
			String term_id1 = sdc.getterm_list(userId).get(0).get(0);

//			if (request.getHeader("Referer") == null) {
//			//	session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("Course_Enroll_Url", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			
			
			model.addAttribute("p_id", id);
			model.addAttribute("msg", msg);
			model.addAttribute("cid", 0);
			model.addAttribute("sid", system_id1);
			model.addAttribute("tid", term_id1);
			model.addAttribute("did", degree_id1);
			model.addAttribute("set_id", set_id);
			model.addAttribute("exit_id1", exit_id1);
			model.addAttribute("exit_course_id", exit_course_id);
			model.addAttribute("exit_id_Payment1", exit_id_Payment1);
			model.addAttribute("termhidden1", termhidden1);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Course_Enroll_Tiles");
	}
	
	@RequestMapping(value = "/getCourse_Set", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getCourse_Set(String system_id,String term_id, String degree_id,
			HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<ArrayList<String>> list = CDDAO.GetCourse_Set(system_id, term_id, degree_id,userid);
		return list;
	}

	@RequestMapping(value = "/getModule_fetch", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getModule_fetch(String course_id) {

		List<ArrayList<String>> list = CDDAO.GetModule_fetch(course_id);
		return list;
	}

	// Summary
	@RequestMapping(value = "/getSummarydata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getSummarydata(String course_id) {

		List<ArrayList<String>> list = CDDAO.GetSummary(course_id);
		return list;
	}

	// Description shivali
	@RequestMapping(value = "/getCourse_Description", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getCourse_Description(HttpSession session,String system_id, String degree_id,String p_id) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<ArrayList<String>> list = CDDAO.GetCourse_Description(userid,system_id,degree_id,p_id);
		return list;
	}

	// Count
	@RequestMapping(value = "/getLearn_Count", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getLearn_Count(String system_id) {

		List<ArrayList<String>> list = CDDAO.GetLearn_Count(system_id);
		return list;
	}

	@PostMapping(value = "/course_enrollaction")
	public ModelAndView course_enrollaction(
			@Validated @ModelAttribute("course_enroll_CMD") EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT td,
			EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE ts, BindingResult result, HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra, MultipartHttpServletRequest mul)
			throws IOException, ParseException {

//		if (request.getHeader("Referer") == null) {
//		//	session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Course_Enroll_Url", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();

		String course_fee = request.getParameter("course_fee");
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String course_hid = "";
		String system_hid = request.getParameter("system_hid");
		String degree_hid = request.getParameter("degree_hid");
		String exit_id1 =request.getParameter("exit_id1");
		String exit_course_id =request.getParameter("exit_course_id");
		String exit_id_Payment1 =request.getParameter("exit_id_Payment1");
	
		 
		try {
			Long c = null;
			for (int k = 1; k < 4; k++) {

				String new_elective_name = request.getParameter("new_elective_name" + k);
				String new_elective_name1 = request.getParameter("new_elective_name1");
				String new_elective_name2 = request.getParameter("new_elective_name2");
				String new_elective_name3 = request.getParameter("new_elective_name3");
				String set_hid = request.getParameter("setid" + k);
				String multisub_d1 =request.getParameter("multisub_d1");
				String multisub_d2 =request.getParameter("multisub_d2");
				String multisub_d3 =request.getParameter("multisub_d3");
				if(exit_id1 == "1"){
					if (new_elective_name1 == null || new_elective_name1.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Courses of FA.");
						return new ModelAndView("redirect:Course_Enroll_Url");
					}
				}
		
				if(exit_id1 == "1"){
					if (new_elective_name2 == null || new_elective_name2.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Courses of FB.");
						return new ModelAndView("redirect:Course_Enroll_Url");
					}
				}
				if(exit_id1 == "1"){
					if (new_elective_name3 == null || new_elective_name3.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Courses of FC.");
						return new ModelAndView("redirect:Course_Enroll_Url");
					}
				}


				List<String> newList = new ArrayList<String>();
				if (new_elective_name != null && !new_elective_name.equals("")) {
					newList = Arrays.asList(new_elective_name.split(","));
				}
				//
				if (newList.size() > 0) {

					for (int i = 0; i < newList.size(); i++) {

//							 c = (Long) sessionHQL.createQuery(
//									"select count(id) from  EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD where   user_id=:userid  and set_id=:set_hid "
//									+ "and course_id=:course_id and id !=:id") //and status=:status
//							.setParameter("userid", Integer.parseInt(userid))
//							.setParameter("set_hid", Integer.parseInt(set_hid))
//							.setParameter("course_id" , Integer.parseInt(newList.get(i)))
//							.setParameter("id", id).uniqueResult();
					}
				}
			}
			if (id == 0) {
//					if (c == 0) {

				td.setSystem_id(Integer.parseInt(system_hid));
				td.setDegree_id(Integer.parseInt(degree_hid));
				td.setStatus("0");
				td.setEle_course_id(0);
				td.setUser_id(Integer.parseInt(userid));
				td.setSet_id(0);
				if(exit_id_Payment1.equals("1")) {
				td.setPayment_status(1);
				}else {
				td.setPayment_status(0);
				}
				td.setCreated_by(username);
				td.setCreated_date(date);

				int parent_id = (int) sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();

				EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD od = new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD();
//						Session sessionHQL2 = this.sessionFactory.openSession();
//						Transaction tx2 = sessionHQL2.beginTransaction();
//						String system_hid = request.getParameter("system_hid");

				for (int k = 1; k < 4; k++) {

					String new_elective_name = request.getParameter("new_elective_name" + k);
					String new_elective_name1 = request.getParameter("new_elective_name1");
					String new_elective_name2 = request.getParameter("new_elective_name2");
					String new_elective_name3 = request.getParameter("new_elective_name3");
					String multisub_d1 =request.getParameter("multisub_d1");
					String multisub_d2 =request.getParameter("multisub_d2");
					String multisub_d3 =request.getParameter("multisub_d3");
					//String exit_id1 =request.getParameter("exit_id1");
//					if(multisub_d1.equals("0")) {
					if(exit_id1 == "1"){
						if (new_elective_name1 == null || new_elective_name1.trim().equals("")) {
							ra.addAttribute("msg", "Please Select Courses of FA.");
							return new ModelAndView("redirect:Course_Enroll_Url");
						}
					}
//					}
//					if(multisub_d2.equals("0")) {
					if(exit_id1 == "1"){
						if (new_elective_name2 == null || new_elective_name2.trim().equals("")) {
							ra.addAttribute("msg", "Please Select Courses of FB.");
							return new ModelAndView("redirect:Course_Enroll_Url");
						}
					}
//					}
//					if(multisub_d3.equals("0")) {
					if(exit_id1 == "1"){
						if (new_elective_name3 == null || new_elective_name3.trim().equals("")) {
							ra.addAttribute("msg", "Please Select Courses of FC.");
							return new ModelAndView("redirect:Course_Enroll_Url");
						}
					}
//					}

					String set_hid = request.getParameter("setid" + k);

					List<String> newList = new ArrayList<String>();
					if (new_elective_name != null && !new_elective_name.equals("")) {
						newList = Arrays.asList(new_elective_name.split(","));
					}

					if (newList.size() > 0) {

						for (int i = 0; i < newList.size(); i++) {

							od.setCourse_id(Integer.parseInt(newList.get(i)));
							od.setSet_id(Integer.parseInt(set_hid));
							od.setSystem_id(Integer.parseInt(system_hid));
							od.setP_id(parent_id);
							od.setCreated_by(username);
							od.setUser_id(Integer.parseInt(userid));
							od.setCreated_date(date);
							od.setStatus("0");

							course_hid += newList.get(i) + ",";

							sessionHQL.save(od);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
				}
				String cids[] = course_hid.split(",");
				for (int x = 0; x < cids.length; x++) {

					List<ArrayList<String>> list = CDDAO.GetlevelofCoursese(cids[x]);

					for (int j = 0; j < list.size(); j++) {

						String lvl_module = list.get(j).get(1);
						System.err.println("---" + lvl_module);
						String module_id = list.get(j).get(2);
						int seq = Integer
								.parseInt(common.getsequenceList(sessionFactory, lvl_module).get(0).getSequence_name());
						String cc_id = list.get(j).get(3);

						ts.setCreated_by(username);
						ts.setCreated_date(date);
						ts.setCourse_id(Integer.parseInt(cids[x]));
						ts.setUser_id(Integer.parseInt(userid));
						ts.setLevel_of_module(lvl_module);
						ts.setModule_id(Integer.parseInt(module_id));
						ts.setSequence(seq);
						ts.setStatus(0);
						ts.setCc_id(Integer.parseInt(cc_id));
						sessionHQL.save(ts);
						sessionHQL.flush();
						sessionHQL.clear();

					}
				}
				
				String msg = "";
				msg="You Have Enroll Successfully.";
              
	      if(exit_id1.equals("1")) {
	    	 
	    	  String hqlUpdatechild ="update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD set status='2' where user_id=:userid and id=:id ";
				int appchild = sessionHQL.createQuery(hqlUpdatechild).setInteger("userid",Integer.parseInt(userid)).setInteger("id",Integer.parseInt(exit_course_id))
						.executeUpdate();
				
				
				
				if ( appchild > 0) {
					msg="Exit from Course Successfully";
				} else {
					msg="Exit from Course UnSuccessfully";
				}
          }
	  	
				tx.commit();
				ra.addAttribute("msg", msg);
				ra.addAttribute("id", parent_id);
				ra.addAttribute("course_fee", course_fee);
        
//					} 
//					else {
//						ra.addAttribute("msg", "YOU HAVE ENROLL ALREADY.");
//						return new ModelAndView("redirect:Stud_Elect_Courses_Url");
//					}
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
		
		if(exit_id_Payment1.equals("1")) {
		return new ModelAndView("redirect:Stud_Elect_Courses_Url");
		}
		return new ModelAndView("redirect:Payment_Url");
	}

	@RequestMapping(value = "admin/videocourseenroll")
	public void videocourseenroll(@ModelAttribute("kmlFileId65") String id, @ModelAttribute("fildname") String fildname,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;

		String filePath = CDDAO.getTopicVideoPath(id);
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

	@RequestMapping(value = "/ElectCoursevideoplay")
	public void ElectDemovideoplay(@ModelAttribute("Id") String id, @ModelAttribute("fildname") String fildname,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath = "";

		filePath = CDDAO.getCoursedemoVideoPath(Integer.parseInt(id));
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
	
	
	@RequestMapping(value = "/getIfExitCourseWiseSet", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getIfExitCourseWiseSet(HttpSession session, String set_id) {
		
	      String userId = session.getAttribute("userId_for_jnlp").toString();

		return CDDAO.getIfExitCourseWiseSet(userId, set_id);
	}
	
	
	 
} 


