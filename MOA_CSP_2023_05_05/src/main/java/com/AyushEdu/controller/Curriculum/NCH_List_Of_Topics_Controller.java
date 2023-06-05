package com.AyushEdu.controller.Curriculum;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.validation.annotation.Validated;
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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.List_of_Topic_Dao;
import com.AyushEdu.dao.Curriculum.NCH_List_Of_TopicsDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class NCH_List_Of_Topics_Controller {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;

	@Autowired
	private NCH_List_Of_TopicsDAO NLTDAO;
//
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/NCH_list_of_topics_url", method = RequestMethod.GET)
	public ModelAndView NCH_list_of_topics_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//	SECURITY -- RIDDHI 		
		if (request.getHeader("Referer") == null) {
//		 session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {

			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getpaperList", common.getpaperList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("NCH_list_of_topics_Tiles");
	}

	@PostMapping(value = "/NCH_list_topicsAction")
	public ModelAndView NCH_list_topicsAction(
			@Validated @ModelAttribute("NCH_list_topicsCMD") EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {

//		SECURITY -- RIDDHI 		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);		
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
		String paper_id = request.getParameter("paper_id");
		String topic_id = request.getParameter("topic_id");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (paper_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Paper");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (topic_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Topic");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT where system_id=:system_id and degree_id=:degree_id \n"
							+ " and professional_id=:professional_id and course_id=:course_id and  paper_id=:paper_id and topic_id=:topic_id and status=0\n"
							+ " and id!=:id")
//					
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("paper_id", Integer.parseInt(paper_id))
					.setParameter("topic_id", Integer.parseInt(topic_id)).setParameter("id", id).uniqueResult();

			if (id == 0) {
				if (c == 0) {
					td.setCreated_by(username);
					td.setCreated_date(date);

					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();

					EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD od = new EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD();

					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for (int i = 1; i <= count_hidden_att; i++) {

						String sub_topic = request.getParameter("sub_topic" + i);
						String hours = request.getParameter("hours" + i);
						String term_id = request.getParameter("term_id" + i);

//						if (topic_id.equals("0")) {
//							ra.addAttribute("msg", "Please Select Topic.");
//							return new ModelAndView("redirect:NCH_list_of_topics_url");
//						}
//
//						if (hours == null || hours.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Lecture Hours.");
//							return new ModelAndView("redirect:NCH_list_of_topics_url");
//						}
//						if (validation.isOnlyNumer(hours) == true) {
//							ra.addAttribute("msg", "Lecture Hours " + validation.isOnlyNumerMSG);
//							return new ModelAndView("redirect:NCH_list_of_topics_url");
//						}
//						if (validation.maxlengthcheckC(hours) == false) {
//							ra.addAttribute("msg", "Lecture Hours " + validation.MaxlengthcheckMSGC);
//							return new ModelAndView("redirect:NCH_list_of_topics_url");
//						}
//						if (term_id.equals("0")) {
//							ra.addAttribute("msg", "Please Select Term.");
//							return new ModelAndView("redirect:NCH_list_of_topics_url");
//						}

						od.setSub_topic(sub_topic);
						od.setHours(Integer.parseInt(hours));
						od.setTerm_id(Integer.parseInt(term_id));
						od.setP_id(parent_id);
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
		return new ModelAndView("redirect:NCH_list_of_topics_url");
	}

	@RequestMapping(value = "admin/NCH_Search_list_of_topics_url", method = RequestMethod.GET)
	public ModelAndView NCH_Search_list_of_topics_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
////		SECURITY -- RIDDHI 			
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {

			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("getpaperList", common.getpaperList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("NCH_Search_list_of_topics_Tiles");
	}

	@PostMapping("/getFilterNCHTopic_data")
	public @ResponseBody List<Map<String, Object>> getFilterNCHTopic_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String paper_id, String topic_id, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return NLTDAO.DataTableNCHTopicDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, paper_id, topic_id, role);
	}

	@PostMapping("/getTotalNCH_Topic_dataCount")
	public @ResponseBody long getTotalNCH_Topic_dataCount(HttpSession sessionUserId, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String paper_id, String topic_id,
			HttpSession session) {
		String role = session.getAttribute("role").toString();
		return NLTDAO.DataTableNCHTopicDataTotalCount(Search, system_id, degree_id, professional_id, course_id,
				paper_id, topic_id, role);
	}

	@RequestMapping(value = "/nch_Topic_ChildUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> nch_Topic_ChildUrl(String hid) {
		ArrayList<ArrayList<String>> list = NLTDAO.getNCHPopup_ChildDatalist(hid);
		return list;
	}

	@RequestMapping(value = "/getNch_View_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getNch_View_data(String course_id) {
		List<ArrayList<String>> Nch_View_list = NLTDAO.Nch_View_list(course_id);
		return Nch_View_list;
	}

	@PostMapping(value = "/NCHdeleteTopic_Url")
	public ModelAndView NCHdeleteTopic_Url(@ModelAttribute("id2") String id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//			SECURITY -- RIDDHI 		
			if(request.getHeader("Referer") == null ) { 
//				 session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();

		try {
			int app = session.createQuery(
					"update EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			int app1 = session.createQuery(
					"update EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
					.setParameter("p_id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0 && app1 > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:NCH_Search_list_of_topics_url");
	}

	@RequestMapping(value = "/NCHEdit_Topic_Url", method = RequestMethod.POST)
	public ModelAndView NCHEdit_Topic_Url(@ModelAttribute("id1") int id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) {
////			SECURITY -- RIDDHI 		
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

		try {
			String role = session.getAttribute("role").toString();
			ArrayList<ArrayList<String>> GetTopic_Parent_Data1 = NLTDAO.GetTopic_Parent_Data1(id);
			List<ArrayList<String>> editchildlist = NLTDAO.getTopic_Child_By_id2(id);

			model.put("NCH_edit_list_topicsCMD", NLTDAO.GetTopic_Parent_Data1(id));
			model.put("list", GetTopic_Parent_Data1);
			model.put("editchildlist", editchildlist);
			model.put("getSystemList", common.getSystemList(sessionFactory, role));
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			model.put("getpaperList", common.getpaperList(sessionFactory));
			model.put("getTopicList", common.getTopicList(sessionFactory));
			model.put("geti3_termList", common.geti3_termList(sessionFactory));

			model.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Nch_edit_list_of_topics_Tiles");
	}

	@RequestMapping(value = "/Nchedit_list_topicsAction", method = RequestMethod.POST)
	public ModelAndView Nchedit_list_topicsAction(
			@ModelAttribute("Nch_edit_list_topicsCMD") EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//			SECURITY -- RIDDHI 		
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NCH_list_of_topics_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

		String username = session.getAttribute("username").toString();
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		Date date = new Date();

		String id = request.getParameter("pmid");
		String system_id = request.getParameter("system_id").trim();
		String degree_id = request.getParameter("degree_id").trim();
		String professional_id = request.getParameter("professional_id").trim();
		String course_id = request.getParameter("course_id");
		String paper_id = request.getParameter("paper_id").trim();
		String topic_id = request.getParameter("topic_id").trim();
//			
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:NCH_list_of_topics_url");
		}
		if (paper_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Paper");
			return new ModelAndView("redirect:List_of_Topics_Url");
		}
		if (topic_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Topics");
			return new ModelAndView("redirect:List_of_Topics_Url");
		}
//			
		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));

		for (int i = 1; i <= new_count_hidden; i++) {

//			String topic_id = request.getParameter("topic_id" + i);
			String sub_topic = request.getParameter("sub_topic" + i);
			String hours = request.getParameter("hours" + i);
			String term_id = request.getParameter("term_id" + i);

//			if (topic_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select Topic.");
//				return new ModelAndView("redirect:NCH_list_of_topics_url");
//			}
//
//			if (hours == null || hours.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Lecture Hours.");
//				return new ModelAndView("redirect:NCH_list_of_topics_url");
//			}
//			if (validation.isOnlyNumer(hours) == true) {
//				ra.addAttribute("msg", "Hours " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:NCH_list_of_topics_url");
//			}
//			if (validation.maxlengthcheckC(hours) == false) {
//				ra.addAttribute("msg", "Hours " + validation.MaxlengthcheckMSGC);
//				return new ModelAndView("redirect:NCH_list_of_topics_url");
//			}
//			if (term_id.equals("0")) {
//				ra.addAttribute("msg", "Please Select Term.");
//				return new ModelAndView("redirect:NCH_list_of_topics_url");
//			}

		}
//			
		try {

			String hql = "update EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,paper_id=:paper_id,topic_id=:topic_id,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("paper_id", Integer.parseInt(paper_id))
					.setParameter("topic_id", Integer.parseInt(topic_id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			if (old_hidden_att <= new_count_hidden) {
				for (int j = 1; j <= old_hidden_att; j++) {

					String editid = request.getParameter("eid" + j);

					EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD add = (EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD) session1
							.get(EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD.class, Integer.parseInt(editid));

//					String topic_id = request.getParameter("topic_id" + j);
					String sub_topic = request.getParameter("sub_topic" + j);
					String hours = request.getParameter("hours" + j);
					String term_id = request.getParameter("term_id" + j);

					add.setSub_topic(sub_topic);
					add.setHours(Integer.parseInt(hours));
					add.setTerm_id(Integer.parseInt(term_id));
					add.setCreated_by(username);
					add.setCreated_date(date);
					add.setId(Integer.parseInt(editid));
					session1.update(add);
					session1.flush();
					session1.clear();
				}
			}
//					
			if (old_hidden_att < new_count_hidden) {
				EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD xray = new EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD();

				for (int j = old_hidden_att + 1; j <= new_count_hidden; j++) {

//					String topic_id = request.getParameter("topic_id" + j);
					String sub_topic = request.getParameter("sub_topic" + j);
					String hours = request.getParameter("hours" + j);
					String term_id = request.getParameter("term_id" + j);

					xray.setSub_topic(sub_topic);
					xray.setHours(Integer.parseInt(hours));
					xray.setTerm_id(Integer.parseInt(term_id));
					xray.setCreated_by(username);
					xray.setCreated_date(date);
					xray.setP_id(Integer.parseInt(id));
					session1.save(xray);
					session1.flush();
					session1.clear();
				}
			}
			if (old_hidden_att > new_count_hidden) {

				for (int j = new_count_hidden + 1; j <= old_hidden_att; j++) {

					String editid = request.getParameter("eid" + j);

					EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD del = (EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD) session1
							.get(EDU_CC_TB_NCH_LIST_OF_TOPICS_CHILD.class, Integer.parseInt(editid));
					session1.delete(del);
					session1.flush();
					session1.clear();
				}
			}
			tx.commit();
			ra.addAttribute("msg", "Data Updated Successfully.");
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:NCH_Search_list_of_topics_url");
	}
}
