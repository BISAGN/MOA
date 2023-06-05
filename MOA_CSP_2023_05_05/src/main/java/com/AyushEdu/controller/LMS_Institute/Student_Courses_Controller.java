package com.AyushEdu.controller.LMS_Institute;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Student_Course_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Student_Courses_Controller {
	@Autowired
	
	private SessionFactory sessionFactory;
	CommonController common = new CommonController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Student_Course_Dao scdao;


	@GetMapping(value = "/student_courses_url")
	public ModelAndView student_courses_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("student_courses_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String role = session.getAttribute("role").toString();
		model.addAttribute("msg", msg);
		model.put("getSystemList", common.getSystemListbyrole(sessionFactory,role));
		model.put("getcoursenameList", common.getcoursenameList(sessionFactory));
		model.put("getSetList", common.getSetList(sessionFactory));
		return new ModelAndView("Student_Courses_Tiles");

	}

	@PostMapping(value = "/student_courses_Action")
	public ModelAndView student_courses_Action(
			@Validated @ModelAttribute("student_courses_CMD") EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT PD2,
			EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD CH2, BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra) throws IOException {
		
		if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("student_courses_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String system_id = request.getParameter("system_id");
		String p_id = request.getParameter("p_id");
		String ele_course_id = "";
		String set_id = "";
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
	
		String hqlUpdate = "delete from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT where  system_id=:system_id and user_id=:user_id";
		 sessionHQL.createQuery(hqlUpdate).setParameter("system_id", system_id).setParameter("user_id", userid)
				.executeUpdate();

		String hqlUpdate2 = "delete from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD where  system_id=:system_id and user_id=:user_id";
		 sessionHQL.createQuery(hqlUpdate2).setParameter("system_id", system_id).setParameter("user_id", userid)
				.executeUpdate();


		List<EDU_LMS_ELECTIVE_COURSE_MASTER> newListcourse = common.getcoursenameListALL(sessionFactory);
		List<TB_SET_MASTER> newListset = common.getSetListALL(sessionFactory);
		try {
			for (int i = 0; i < newListcourse.size(); i++) {

				if (request.getParameter("multisub" + String.valueOf(newListcourse.get(i).getId())) != null) {
					
					EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT PD = new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT();
					Session sessionHQL1 = this.sessionFactory.openSession();
					Transaction tx1 = sessionHQL1.beginTransaction();
					//PD.setEle_course_id(Integer.parseInt(newListcourse.get(i).getId()));
					PD.setSystem_id(Integer.parseInt(system_id));
					PD.setUser_id(Integer.parseInt(userid));
					PD.setCreated_by(username);
					PD.setCreated_date(date);

					if (role.equals("ADMIN")) {
						PD.setStatus("1");
					} else {
						PD.setStatus("0");
					}

					int id = (int) sessionHQL1.save(PD);
					model.put("id", id);
					sessionHQL.flush();
					sessionHQL.clear();
					tx1.commit();

//					for (int j = 1; j <= newListset.size(); j++) {

					System.err.println("SHIVALI--------"+"multisub_sub" + String.valueOf(newListcourse.get(i).getId()));

						if (request.getParameter("multisub" + String.valueOf(newListcourse.get(i).getId())) != null) {
							Session sessionHQL2 = this.sessionFactory.openSession();
							Transaction tx2 = sessionHQL2.beginTransaction();
							EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD CH =new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD();
							System.err.println("SHIVALI--------"+request.getParameter("multisub_sub" + String.valueOf(newListcourse.get(i).getId())));
							CH.setSet_id(Integer.parseInt("multisub_sub" + String.valueOf(newListcourse.get(i).getId())));
							//CH.setP_id(request.getParameter(id));
							CH.setCreated_by(username);
							CH.setCreated_date(date);
							CH.setSystem_id(Integer.parseInt(system_id));
							CH.setUser_id(Integer.parseInt(userid));

							if (role.equals("ADMIN")) {
								CH.setStatus("1");
							} else {
								CH.setStatus("0");
							}
							int p_id1 = (int) sessionHQL2.save(CH);
							model.put("id", p_id1);
							sessionHQL2.flush();
							sessionHQL2.clear();
							tx2.commit();
					}
					//}
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
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:student_courses_url");
	}

	@RequestMapping(value = "/admin/getSystemFromElec_Course_Set", method = RequestMethod.POST)
	public @ResponseBody List<Map> getSystemFromElec_Course_Set(String system_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "select id,ele_course_id from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT where system_id=:system_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setString("system_id", system_id);
		List<Map> list = (List<Map>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;

	}

	@RequestMapping(value = "/admin/getElec_CourseFrom_Set", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getElec_CourseFrom_Set(String p_id,String course,HttpSession session) {
		
		String user_id = session.getAttribute("userId_for_jnlp").toString();
		
		return scdao.studentSelectElective(user_id, p_id, course, session);

	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/admin/getcoursebysystem_student")
	public @ResponseBody List<Map> getcoursebysystem_student( String system_id) {
	
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				
				Query q1=sessionHQL.createQuery("SELECT elc.elec_course_id,ec.course_name FROM EDU_LMS_SYSTEM_ELE_COURSE_LINK elc ,EDU_LMS_ELECTIVE_COURSE_MASTER ec\n"
						+ "where cast(ec.id as text)= cast(elc.elec_course_id as text) and system_id =:id");
				
				q1.setParameter("id",system_id);
				@SuppressWarnings("unchecked")
				List<Map> list = (List<Map>) q1.list();
				
				tx.commit();
				
				return list;

			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		}
	
	
		@GetMapping(value = "/Search_student_report_url")
		public ModelAndView Search_student_report_url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_student_report_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			model.put("msg", msg);
			model.put("getsysList", common.getsysList(sessionFactory));
			model.put("getSetList", common.getSetList(sessionFactory));
			model.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
			return new ModelAndView("Search_Student_Report_Tiles");

		}

@PostMapping("/getFilterstudent_reg_data")
public @ResponseBody List<Map<String, Object>> getFilterstudent_reg_data(int startPage, int pageLength,

		String Search, String orderColunm, String orderType, String user_name, String system_name, String set_name,
		String course_name, String app_status) {

	return scdao.DataTablestudent_regDataList(startPage, pageLength, Search, orderColunm, orderType, user_name,
			system_name, set_name, course_name, app_status);

}

@PostMapping("/getTotalstudent_reg_dataCount")
public @ResponseBody long getTotalstudent_reg_dataCount(HttpSession sessionUserId, String Search, String user_name,
		String system_name, String set_name, String course_name, String app_status) {
	return scdao.DataTablestudent_regDataTotalCount(Search, user_name, system_name, set_name, course_name, app_status);
}

@RequestMapping(value = "Search_student_Approve_url", method = RequestMethod.POST)
public ModelAndView Search_student_Approve_url(@ModelAttribute("Acceptid") int id,@RequestParam("name1") String name, HttpSession session, ModelMap model,
		EDU_LMS_INSTITUTE_REGISTRATION obj) {
	
	String maxAID = scdao.getMaxAID();
	int newn = Integer.parseInt(maxAID);
	newn++;
	String abc = String.format("%6s", newn).replace(' ', '0');
	abc = "AU"+abc;

	List<String> liststr = new ArrayList<String>();

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	Date date = new Date();

	String username = session.getAttribute("username").toString();
	String hqlUpdate2 = "update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT set status='1',modified_by=:modified_by,modified_date=:modified_date"
			+ " where id=:id  ";
	int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
			.setDate("modified_date", new Date()).executeUpdate();
	
	String hqlUpdate3 = "update from EDU_LMS_STUDENT_DETAILS set ayush_id=:ayush_id,modified_by=:modified_by,modified_date=:modified_date"
			+ " where name=:name  ";
	int app3 = sessionHQL.createQuery(hqlUpdate3).setString("ayush_id", abc).setString("modified_by", username).setString("name", name)
			.setDate("modified_date", new Date()).executeUpdate();

	tx.commit();
	sessionHQL.close();
	if (app2 > 0) {
		liststr.add("Approved Successfully.");
	} else {
		liststr.add("Not Approved.");
	}
	model.put("msg", liststr.get(0));

	return new ModelAndView("Search_Student_Report_Tiles");
}

@RequestMapping(value = "Search_student_Reject_url", method = RequestMethod.POST)
public ModelAndView Search_student_Reject_url(@ModelAttribute("Rejectid") int id, HttpSession session, ModelMap model,
		EDU_LMS_INSTITUTE_REGISTRATION obj) {

	List<String> liststr = new ArrayList<String>();
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	Date date = new Date();

	String username = session.getAttribute("username").toString();
	String hqlUpdate2 = "update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT set status='2',modified_by=:modified_by,modified_date=:modified_date"
			+ " where id=:id  ";
	int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
			.setDate("modified_date", new Date()).executeUpdate();

	tx.commit();
	sessionHQL.close();
	if (app2 > 0) {
		liststr.add("Reject Successfully.");
	} else {
		liststr.add("Not Reject.");
	}
	model.put("msg", liststr.get(0));

	return new ModelAndView("Search_Student_Report_Tiles");

}
	

}
