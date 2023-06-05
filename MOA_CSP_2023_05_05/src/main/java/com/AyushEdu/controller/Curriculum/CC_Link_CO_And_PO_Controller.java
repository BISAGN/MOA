package com.AyushEdu.controller.Curriculum;

import java.security.Principal;
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
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Curriculum.Link_Co_and_Po_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Link_CO_And_PO_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Link_Co_and_Po_Dao PDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;

	@Autowired
	Commondao comdao;

	@Autowired
	Link_Co_and_Po_Dao Pdao;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	
	@GetMapping(value = "/Link_Course_Outcome_And_Program_Outcome_Url")
	public ModelAndView Link_Course_Outcome_And_Program_Outcome_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Course_Outcome_And_Program_Outcome_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String role = session.getAttribute("role").toString();
		model.addAttribute("msg", msg);
		model.put("getSystemList", common.getSystemList(sessionFactory, role));
		model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		return new ModelAndView("Link_Course_Outcome_And_Program_Outcome_Tiles");
	}

	@RequestMapping(value = "/getcourselistby_professional1", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getcourselistby_professional1(String professional_id) {
		List<CC_TB_LIST_OF_TOPICS_PARENT> list = common.getcourselistby_professional(sessionFactory, professional_id);
		return list;
	}

	@PostMapping(value = "/link_co_and_po_action")
	public ModelAndView link_co_and_po_action(
			@Validated @ModelAttribute("link_co_and_po_CMD") CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Course_Outcome_And_Program_Outcome_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String course_outcome_id = request.getParameter("course_outcome_id");

		String add_programoutcome = request.getParameter("add_programoutcome");
		String remove_programoutcome = request.getParameter("remove_programoutcome");
		String old_programoutcome = request.getParameter("old_programoutcome");
		String new_programoutcome = request.getParameter("new_programoutcome");

		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (degree_id == null || degree_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
		}
		if (professional_id == null || professional_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Professional.");
			return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject.");
			return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
		}

		if (course_outcome_id == null || course_outcome_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Course Outcome.");
			return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
		}
//		if (programoutcome_id == null || programoutcome_id.trim().equals("")) {
//			ra.addAttribute("msg", "Please Select Program Outcome.");
//			return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
//		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id and course_id=:course_id and  course_outcome_id=:course_outcome_id and status=0 and id !=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("course_outcome_id", Integer.parseInt(course_outcome_id)).setParameter("id", id)
					.uniqueResult();
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
					if (new_programoutcome != null && !new_programoutcome.equals("")) {
						newList = Arrays.asList(new_programoutcome.split(","));
					}
					List<String> addList = new ArrayList<String>();
					List<String> removeList = new ArrayList<String>();
					if (add_programoutcome != null && !add_programoutcome.equals("")) {
						addList = Arrays.asList(add_programoutcome.split(","));
					}
					if (remove_programoutcome != null && !remove_programoutcome.equals("")) {
						removeList = Arrays.asList(remove_programoutcome.split(","));
					}
					if (removeList.size() > 0) {
						for (int i = 0; i < removeList.size(); i++) {
							String hqlUpdate = "delete from CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD where programoutcome_id=:programoutcome_id ";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setParameter("programoutcome_id", (Integer.parseInt(removeList.get(i))))
									.executeUpdate();
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
					if (addList.size() > 0) {
						for (int i = 0; i < addList.size(); i++) {

							CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD obj = new CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD();

							obj.setProgramoutcome_id(Integer.parseInt(addList.get(i)));
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
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
	}

	@RequestMapping(value = "/update_Co_Po_Action", method = RequestMethod.POST)
	public @ResponseBody String update_Co_Po_Action(String id, String system_id, String degree_id,
			String professional_id, String course_id, String course_outcome_id, String sd_old, String sd_new,
			HttpSession session1, Principal principal, RedirectAttributes ra, HttpServletRequest request) {
		
//		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Link_Course_Outcome_And_Program_Outcome_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String add_programoutcome = request.getParameter("add_programoutcome");
			String remove_programoutcome = request.getParameter("remove_programoutcome");
			String old_programoutcome = request.getParameter("old_programoutcome");
			String new_programoutcome = request.getParameter("new_programoutcome");

			CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT prt = (CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT) sessionHQL
					.get(CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT.class, Integer.parseInt(id));

			prt.setSystem_id(Integer.parseInt(system_id));
			prt.setDegree_id(Integer.parseInt(degree_id));
			prt.setProfessional_id(Integer.parseInt(professional_id));
			prt.setCourse_id(Integer.parseInt(course_id));
			prt.setCourse_outcome_id(Integer.parseInt(course_outcome_id));

			sessionHQL.update(prt);
			sessionHQL.flush();
			sessionHQL.clear();

			List<String> newList = new ArrayList<String>();
			if (new_programoutcome != null && !new_programoutcome.equals("")) {
				newList = Arrays.asList(new_programoutcome.split(","));
			}
			List<String> addList = new ArrayList<String>();
			List<String> removeList = new ArrayList<String>();
			if (add_programoutcome != null && !add_programoutcome.equals("")) {
				addList = Arrays.asList(add_programoutcome.split(","));
			}
			if (remove_programoutcome != null && !remove_programoutcome.equals("")) {
				removeList = Arrays.asList(remove_programoutcome.split(","));
			}
			if (removeList.size() > 0) {
				for (int i = 0; i < removeList.size(); i++) {
					System.err.println("poid--" + removeList.get(i));
					System.err.println("id--" + id);

					String hqlUpdate = "update CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD set status=:status where programoutcome_id=:programoutcome_id and p_id=:id ";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setParameter("programoutcome_id", (Integer.parseInt(removeList.get(i))))
							.setParameter("id", Integer.parseInt(id)).setParameter("status", 2).executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			if (addList.size() > 0) {
				for (int i = 0; i < addList.size(); i++) {

					CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD obj = new CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD();

					obj.setProgramoutcome_id(Integer.parseInt(addList.get(i)));
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

	@PostMapping("/getFilterCo_Po_Data_data")
	public @ResponseBody List<Map<String, Object>> getFilterGa_Po_Data_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String course_outcome_id, String programoutcome_name,
			HttpSession session) {

		String role = session.getAttribute("role").toString();
		return PDao.DataTableCo_PoDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,
				professional_id, course_id, course_outcome_id, programoutcome_name, role);

	}

	@PostMapping("/getTotalCo_Po_Data_dataCount")
	public @ResponseBody long getTotalCo_Po_Data_dataCount(HttpSession sessionUserId, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String course_outcome_id,
			String programoutcome_name, HttpSession session) {

		String role = session.getAttribute("role").toString();
		return PDao.DataTableCo_Po_DataTotalCount(Search, system_id, degree_id, professional_id, course_id,
				course_outcome_id, programoutcome_name, role);
	}

//	@RequestMapping(value = "/Po_Data_Url", method = RequestMethod.POST)
//	public @ResponseBody   ArrayList<ArrayList<String>> Po_Data_Url(String hid) {
//	 ArrayList<ArrayList<String>> list = Pdao.getPopup_Po_Datalist(hid);
//		return list;
//	}

	@RequestMapping(value = "/Co_Po_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Co_Po_Delete_Url(@ModelAttribute("id1") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Course_Outcome_And_Program_Outcome_Url", roleid1);		
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
				int app = session.createQuery(
						"update CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(dids[k])).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				int app2 = session.createQuery(
						"update CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:id")
						.setParameter("id", Integer.parseInt(dids[k])).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				if (app > 0) {
					System.err.println("check delete" + (app > 0));
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
		return new ModelAndView("redirect:Link_Course_Outcome_And_Program_Outcome_Url");
	}

	@RequestMapping(value = "/getView_CO_PO_data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getView_CO_PO_data(String course_id) {
		ArrayList<ArrayList<String>> t1copolink_list = PARDAO.table1_co_po_link(course_id);
		return t1copolink_list;
	}
}
