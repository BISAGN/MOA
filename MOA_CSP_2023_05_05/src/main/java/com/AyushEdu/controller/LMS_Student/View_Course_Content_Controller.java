package com.AyushEdu.controller.LMS_Student;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_NCISM.Course_Content_Dao;
import com.AyushEdu.dao.LMS_Student.View_Course_Content_Dao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class View_Course_Content_Controller {
	@Autowired
	CommonController common;
	
	@Autowired
	View_Course_Content_Dao vcdao;
	
	@Autowired
	Course_Content_Dao ccd;
	
	@Autowired
	Commondao commonDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/ViewCourseContent_Url", method = RequestMethod.GET)
	public ModelAndView ViewCourseContent_Url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("ViewCourseContent_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Session sessionHQL = this.sessionFactory.openSession();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		Query q0 = sessionHQL.createQuery(
				"select count(*) from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT where user_id=:userId")
					.setParameter("userId", Integer.parseInt(userId));

		Long c = (Long) q0.uniqueResult();
		
		if(c<3) {
			Mmap.put("count", "Please Select Alteast 3 Elective Course");
		}else {
			Mmap.put("count", "");
		}
		 Mmap.put("msg", msg);
		 Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		 Mmap.put("module_name", common.getModuleName(sessionFactory));
		 Mmap.put("getTypeOfContent", common.getTypeOfcontent(sessionFactory));
		 Mmap.put("level_list", common.getlevel_Ofcontentlist(sessionFactory));
		 sessionHQL.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("View_course_contentTiles");
	}
	
	@PostMapping("/getFilterviewcourse__data")
	public @ResponseBody List<Map<String, Object>> getFilterviewcourse__data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String course_name,String module_name,String type_content,String level_of_module,HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return vcdao.DataTableviewcourse_DataList(startPage, pageLength, Search, orderColunm, orderType,course_name,module_name,type_content, level_of_module,userid, session);

	}
	@PostMapping("/getTotalviewcourse_dataCount")
	public @ResponseBody long getTotalviewcourse_dataCount(HttpSession sessionUserId, String Search,  String course_name,String module_name,String type_content,String level_of_module,HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return vcdao.DataTableviewcourse_DataTotalCount(Search,course_name,module_name,type_content,level_of_module,userid);
	}
	
	//HET CHANGES
		@RequestMapping(value = "/getdataForSeqVal", method = RequestMethod.POST)
		public @ResponseBody List<String> getdataForSeqVal(String courseid,String lm,String module_id,HttpSession session) {
			List<String> ls = new ArrayList<String>();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			ls = vcdao.getdataForSeqVal2(courseid,lm,userid,module_id).get(0);	 
			return ls;
		}
	
	@RequestMapping(value = "getVideoviewstatus", method = RequestMethod.POST)
	public ModelAndView getVideoviewstatus(HttpSession session,ModelMap model,
			EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE obj,String courseid,String lm,String ms,String module) {
		
		
		
	List<String> liststr = new ArrayList<String>();
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	String userid = session.getAttribute("userId_for_jnlp").toString();
	String username = session.getAttribute("username").toString();
	 		
	String hqlUpdate2 = "update from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE set status=1,modified_by=:modified_by,modified_date=:modified_date "
						+ " where course_id=:course_id   and user_id=:user_id  and sequence=:ms and module_id=:module";
	
	 		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("course_id", Integer.parseInt(courseid))
	 				.setInteger("user_id",Integer.parseInt(userid)).setInteger("ms",Integer.parseInt(ms))
	 				.setInteger("module",Integer.parseInt(module))
	 				.setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();
//	 		.setString("lm", lm)
	 
	 	tx.commit();
		sessionHQL.close();
//		System.err.println("-------APPPPPP-------"+app2);
		if (app2 > 0 ) {
			liststr.add("Video Complete Successfully.");
		} else {
			liststr.add("Video Not Complete");
		}
		model.put("msg", liststr.get(0));
		 return new ModelAndView("View_course_contentTiles");
	}

	@RequestMapping(value = "getTimeviewstatus", method = RequestMethod.POST)
	public @ResponseBody void  getTimeviewstatus(HttpSession session,String courseid,String v1,String module) {

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	String userid = session.getAttribute("userId_for_jnlp").toString();
	String username = session.getAttribute("username").toString();
	 		
	String hqlUpdate2 = "update from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE set time=:time,modified_by=:modified_by,modified_date=:modified_date "
						+ " where course_id=:course_id  and user_id=:user_id and module_id=:module_id ";
	
			
	
	 		int app2 = sessionHQL.createQuery(hqlUpdate2).setString("time", v1).setInteger("course_id", Integer.parseInt(courseid))
	 				.setInteger("user_id",Integer.parseInt(userid))
	 				.setInteger("module_id",Integer.parseInt(module))
	 				.setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();
	 		
	 		
	 	
	 
	 	tx.commit();
		sessionHQL.close();
	}
	
	 @RequestMapping(value = "/getcourseDatabytypeofcontent", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getcourseDatabytypeofcontent(String type_content, HttpSession session) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = vcdao.getcourselistFromtypeofcontent(type_content,userid);
			
			return list;
		}
	 
	 @RequestMapping(value = "/getmoduleDatabycourse", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getmoduleDatabycourse(String course_name, HttpSession session) {
		 
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = vcdao.getmodulelistFromtcourse(course_name,userid);
			
			return list;
		}
	 
	 //shivali
	 
	 @RequestMapping(value = "/getlevel_of_modulebyModule", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getlevel_of_modulebyModule(String module_name, HttpSession session) {
		 
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = vcdao.getlevel_of_modulebyModule(module_name,userid);
			
			return list;
		}
	
	@RequestMapping(value = "getAskQueryMethod", method = RequestMethod.POST)
	public @ResponseBody String getAskQueryMethod(ModelMap model, HttpSession session, HttpServletRequest request,RedirectAttributes ra,Principal principal,
		@RequestParam(value = "courseid", required = false) String courseid,
		@RequestParam(value = "moduleid", required = false) String moduleid,
		@RequestParam(value = "message", required = false) String message) {

		 	Date date = new Date();
		 	String username = principal.getName();
		 	String userId = session.getAttribute("userId_for_jnlp").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String msg = "";

			try {
				
				if (message == null || message.trim().equals("")) {
					//ra.addAttribute("msg", "Please Enter Message.");
					msg="1";
					return msg;
				}
				
				TB_NOTIFICATION nt = new TB_NOTIFICATION();

				nt.setCreated_by(username);
				nt.setCreated_date(date);
				nt.setMessage(message);
				nt.setFrom_name_send(userId);
				String cid = commonDao.getFacultyListForNotification(Integer.parseInt(userId), courseid).get(0).get(0);
				nt.setTo_name_sent(cid);
				nt.setStatus("1");
				
				sessionHQL.save(nt);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				
				msg="Query Submmitted Successfully";

			} catch (Exception e) {
				e.printStackTrace();
				msg="Something went wrong !!!";
				tx.rollback();
			}
			return msg;
	}
	
	 @RequestMapping(value = "/getExamPaper", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getExamPaper(String course_id,String module_id,HttpSession session) {
	    	
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 List<ArrayList<String>> list = vcdao.GetExamPaper(course_id,module_id,userid);	 
			return list;
		}
	 
	 
	///shivali
		 @RequestMapping(value = "/getExamPaperSequence", method = RequestMethod.POST)
			public @ResponseBody String getExamPaperSequence(@ModelAttribute("course_id") int course_id,@ModelAttribute("module_id") int module_id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
			 		System.err.println("---getExamPaperSequence=="+ course_id+" ==cont====  "+module_id+"======+");
			 		
				    String  roleid = session.getAttribute("roleid").toString();
					ArrayList<String> liststr = new ArrayList<String>();
					String userid = session.getAttribute("userId_for_jnlp").toString();
				    String username = session.getAttribute("username").toString();
				    String count="0";
				
				try {
					 Session sessionHQL = sessionFactory.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					 Query q0 = sessionHQL.createQuery(
								"select count(*) from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE ces,EDU_LMS_ELECTIVE_COURSE_MASTER cm where ces.course_id=cm.id and cm.course_name=:course_id and ces.module_id=:module_id and ces.user_id=:userid and ces.status=1")
							 .setParameter("course_id", String.valueOf(course_id))
							 .setParameter("module_id", module_id)
							 .setParameter("userid", Integer.parseInt(userid));

						Long app = (Long) q0.uniqueResult();
					 
					 Query q1 = sessionHQL.createQuery(
								"select count(*) from EDU_LMS_COURSE_CONTENT_CHILD where  module=:module_id and status=1")
							 .setParameter("module_id", module_id);

						Long app1 = (Long) q1.uniqueResult();
					
						
					if(app == app1) {
						count="1";
					}else {
						count="2";
					}

					tx.commit();
					sessionHQL.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return count;
			}
}