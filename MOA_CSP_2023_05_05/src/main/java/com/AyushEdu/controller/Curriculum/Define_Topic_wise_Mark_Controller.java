package com.AyushEdu.controller.Curriculum;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Define_Topic_Wise_Mark_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Define_Topic_wise_Mark_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	CommonController common;

	@Autowired
	Define_Topic_Wise_Mark_Dao vcdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/define_topic_wise_mark_url", method = RequestMethod.GET)
	public ModelAndView define_topic_wise_mark_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("define_topic_wise_mark_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {

			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getCourseList", common.getCourseList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Define_Topic_wise_Mark");
	}

	@PostMapping(value = "/define_topic_wise_marksAction")
	public ModelAndView define_topic_wise_marksAction(
			@Validated @ModelAttribute("define_topic_wise_marksCMD") EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {
//		SECURITY -- RIDDHI 		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("define_topic_wise_mark_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		int id = td.getId() > 0 ? td.getId() : 0;

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String marks = request.getParameter("marks");

		String add_course_topic = request.getParameter("add_course_topic");
		String remove_course_topic = request.getParameter("remove_course_topic");
		String old_course_topic = request.getParameter("old_course_topic");
		String new_course_topic = request.getParameter("new_course_topic");
		
		System.out.println("add_course_topic======" + add_course_topic);

		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (professional_id == null || professional_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Professional");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (marks == null || marks.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Marks");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (validation.maxlengthcheckC(marks) == false) {
			ra.addAttribute("msg", "Marks " + validation.MaxlengthcheckMSGC);
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (validation.isOnlyNumer(marks) == true) {
			ra.addAttribute("msg", "Marks " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		if (new_course_topic == null || new_course_topic.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Topic");
			return new ModelAndView("redirect:define_topic_wise_mark_url");
		}
		try {
			Long c = null;
			for (int j = 0; j < add_course_topic.split(",").length; j++) {
				c = (Long) sessionHQL.createQuery(
						"select count(p.id) from  EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT p,EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD c where p.id=c.p_id and p.system_id=:system_id and p.degree_id=:degree_id \n"
								+ " and professional_id=:professional_id and p.course_id=:course_id and p.marks=:marks and p.status=0 \n"
								+ " and p.id!=:id and c.topic_id=:topic_id")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("marks", Integer.parseInt(marks))
						.setParameter("topic_id", Integer.parseInt(add_course_topic.split(",")[j]))
						.setParameter("id", id).uniqueResult();
			}
			if (id == 0) {
				if (c == 0) {
					td.setSystem_id(Integer.parseInt(system_id));
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setStatus(0);

					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();

					List<String> newList = new ArrayList<String>();
					if (new_course_topic != null && !new_course_topic.equals("")) {
						newList = Arrays.asList(new_course_topic.split(","));
					}

					List<String> addList = new ArrayList<String>();
					List<String> removeList = new ArrayList<String>();
					if (add_course_topic != null && !add_course_topic.equals("")) {
						addList = Arrays.asList(add_course_topic.split(","));
					}
					if (remove_course_topic != null && !remove_course_topic.equals("")) {
						removeList = Arrays.asList(remove_course_topic.split(","));
					}
					if (removeList.size() > 0) {
						for (int i = 0; i < removeList.size(); i++) {
							String hqlUpdate = "delete from EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD where topic_id=:topic_id ";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setParameter("topic_id", (Integer.parseInt(removeList.get(i)))).executeUpdate();
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
					if (addList.size() > 0) {
						for (int i = 0; i < addList.size(); i++) {

							EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD obj = new EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD();

							obj.setTopic_id(Integer.parseInt(addList.get(i)));
							obj.setP_id(parent_id);
							obj.setStatus(0);
							obj.setCreated_by(username);
							obj.setCreated_date(date);
							int s_id2 = (int) sessionHQL.save(obj);
							model.put("s_id", s_id2);
							sessionHQL.flush();
							sessionHQL.clear();
						}
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
		return new ModelAndView("redirect:define_topic_wise_mark_url");
	}

	@PostMapping("/getFilterdefine_topic_wise_marks_data")
	public @ResponseBody List<Map<String, Object>> getFilterdefine_topic_wise_marks_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id, String course_id,
			String marks, String topic_name, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return vcdao.DataTabledefine_topic_wise_marksDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_id, degree_id, professional_id, course_id, marks, topic_name, role);
	}

	@PostMapping("/getTotaldefine_topic_wise_marks_dataCount")
	public @ResponseBody long getTotaldefine_topic_wise_marks_dataCount(HttpSession sessionUserId, String Search,
			String system_id, String degree_id,String professional_id, String course_id, String marks, String topic_name,
			HttpSession session) {
		String role = session.getAttribute("role").toString();
		return vcdao.DataTabledefine_topic_wise_marksDataTotalCount(Search, system_id, degree_id,professional_id, course_id, marks,
				topic_name, role);
	}

	@RequestMapping(value = "/updatedefine_topic_wise_marksAction", method = RequestMethod.POST)
	public @ResponseBody String updatedefine_topic_wise_marksAction(String id, String system_id, String degree_id,	String professional_id,
			String course_id, String marks, String st_old, String st_new, HttpSession session1, Principal principal,
			RedirectAttributes ra, HttpServletRequest request) {
		
//		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("define_topic_wise_mark_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {
			String add_system_id = request.getParameter("add_system_id");
			String add_course_topic = request.getParameter("add_course_topic");
			String remove_course_topic = request.getParameter("remove_course_topic");
			String old_course_topic = request.getParameter("old_course_topic");
			String new_course_topic = request.getParameter("new_course_topic");

			if (degree_id == null || degree_id.trim().equals("0")) {
				msg = "Please Select Degree";
				return msg;
			}
			if (course_id == null || course_id.trim().equals("0")) {
				msg = "Please Select Subject";
				return msg;
			}
			if (marks == null || marks.trim().equals("")) {
				msg = "Please Enter Marks";
				return msg;
			}
			if (validation.maxlengthcheckC(marks) == false) {
				ra.addAttribute("msg", "Marks " + validation.MaxlengthcheckMSGC);
				return msg;
			}
			if (validation.isOnlyNumer(marks) == true) {
				ra.addAttribute("msg", "Marks " + validation.isOnlyNumerMSG);
				return msg;
			}
			if (request.getParameter("old_course_topic") == null
					|| request.getParameter("old_course_topic").trim().equals("")) {
				msg = "Please Select Topic";
				return msg;
			}
			if (request.getParameter("new_course_topic") == null
					|| request.getParameter("new_course_topic").trim().equals("")) {
				msg = "Please Select Topic";
				return msg;
			}

			EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT prt = (EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT) sessionHQL
					.get(EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT.class, Integer.parseInt(id));

			
			prt.setDegree_id(Integer.parseInt(degree_id));
			prt.setProfessional_id(Integer.parseInt(professional_id));
			prt.setCourse_id(Integer.parseInt(course_id));
			prt.setMarks(Integer.parseInt(marks));

			sessionHQL.update(prt);
			sessionHQL.flush();
			sessionHQL.clear();

			List<String> newList = new ArrayList<String>();
			if (new_course_topic != null && !new_course_topic.equals("")) {
				newList = Arrays.asList(new_course_topic.split(","));
			}
			List<String> addList = new ArrayList<String>();
			List<String> removeList = new ArrayList<String>();
			if (add_course_topic != null && !add_course_topic.equals("")) {
				addList = Arrays.asList(add_course_topic.split(","));
			}
			if (remove_course_topic != null && !remove_course_topic.equals("")) {
				removeList = Arrays.asList(remove_course_topic.split(","));
			}
			if (removeList.size() > 0) {
				for (int i = 0; i < removeList.size(); i++) {
					String hqlUpdate = "update EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD set status=:status where topic_id=:topic_id and p_id=:id ";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setParameter("topic_id", (Integer.parseInt(removeList.get(i))))
							.setParameter("id", Integer.parseInt(id)).setParameter("status", 2).executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			if (addList.size() > 0) {
				for (int i = 0; i < addList.size(); i++) {

					EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD obj = new EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD();

					obj.setTopic_id(Integer.parseInt(addList.get(i)));
					obj.setP_id(Integer.parseInt(id));
					obj.setStatus(0);
					obj.setCreated_by(username);
					obj.setCreated_date(date);
					sessionHQL.save(obj);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			msg = "Data Updated Successfully";
			tx.commit();
			sessionHQL.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(value = "/delete_define_topic_wise_mark_Url", method = RequestMethod.POST)
	public ModelAndView delete_define_topic_wise_mark_Url(@ModelAttribute("id1") String id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("define_topic_wise_mark_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			String dids[] = id.split(":");
			for (int k = 0; k < dids.length; k++) {

				int app1 = session.createQuery(
						"update EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				int app = session.createQuery(
						"update EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:id")
						.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				if (app1 > 0 && app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
			}
			tx.commit();
			session.close();
			ra.addAttribute("msg", liststr.get(0));

		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:define_topic_wise_mark_url");
	}
}