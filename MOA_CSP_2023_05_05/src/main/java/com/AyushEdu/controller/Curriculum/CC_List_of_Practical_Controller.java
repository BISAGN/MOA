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
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_PRACTICAL_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_PRACTICAL_PARENT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.List_of_Practical_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_List_of_Practical_Controller {

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
	private List_of_Practical_Dao LOPDAO;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	

	@RequestMapping(value = "admin/List_of_practical_Url", method = RequestMethod.GET)
	public ModelAndView List_of_practical_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("List_of_Practical_Tiles");
	}

//	@RequestMapping(value = "/getdegreelistby_system", method = RequestMethod.POST)
//	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegreelistby_system(String system_name)  {
//		List<EDU_LMS_SYS_DEG_MAP_MASTER> list =  common.getDegreeListbysystem(sessionFactory,system_name);
//		return list;
//	}

	@PostMapping(value = "/list_practicalAction")
	public ModelAndView list_practicalAction(
			@Validated @ModelAttribute("list_practicalCMD") CC_TB_LIST_OF_PRACTICAL_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul) throws IOException, ParseException {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
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

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_LIST_OF_PRACTICAL_PARENT where system_id=:system_id and degree_id=:degree_id \n"
							+ " and professional_id=:professional_id and course_id=:course_id  and status=0 \n"
							+ " and id!=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id)).setParameter("id", id).uniqueResult();

			if (id == 0) {
				if (c == 0) {
//					td.setApp_status(1);
					td.setCreated_by(username);
					td.setCreated_date(date);

					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();

					CC_TB_LIST_OF_PRACTICAL_CHILD od = new CC_TB_LIST_OF_PRACTICAL_CHILD();

					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for (int i = 1; i <= count_hidden_att; i++) {

						String practical = request.getParameter("practical" + i);
						String term_id = request.getParameter("term_id" + i);
						String activity_practical_description = request
								.getParameter("activity_practical_description" + i);

						if (practical == null || practical.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Practical.");
							return new ModelAndView("redirect:List_of_practical_Url");
						}
//						if (validation.isOnlyAlphabet(practical) == false) {
//							ra.addAttribute("msg", "Practical " + validation.isOnlyAlphabetMSG);
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}
//						if (validation.maxlengthcheckP(practical) == false) {
//							ra.addAttribute("msg","Practical "+ validation.MaxlengthcheckMSGP);
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}
//						if (term_id.equals("0")) {
//							ra.addAttribute("msg", "Please Select Term.");
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}
//						if (activity_practical_description == null || activity_practical_description.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Activity Practical Description.");
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}
//						if (validation.isOnlyAlphabet(activity_practical_description) == false) {
//							ra.addAttribute("msg", "Activity Practical Description " + validation.isOnlyAlphabetMSG);
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}
//						if (validation.maxlengthcheck(activity_practical_description) == false) {
//							ra.addAttribute("msg","Activity Practical Description "+ validation.MaxlengthcheckMSG);
//							return new ModelAndView("redirect:List_of_practical_Url");
//						}

						od.setPractical(practical);
						od.setTerm_id(Integer.parseInt(term_id));
						od.setActivity_practical_description(activity_practical_description);
						od.setP_id(parent_id);
//						od.setStatus(1);
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
		return new ModelAndView("redirect:List_of_practical_Url");
	}

	@RequestMapping(value = "admin/Search_List_of_practical_Url", method = RequestMethod.GET)
	public ModelAndView Search_List_of_practical_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Search_List_of_Practical_Tiles");
	}

	@PostMapping("/getFilterPractical_data")
	public @ResponseBody List<Map<String, Object>> getFilterPractical_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return LOPDAO.DataTablePracticalDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, role);
	}

	@PostMapping("/getTotalPractical_dataCount")
	public @ResponseBody long getTotalPractical_dataCount(HttpSession sessionUserId, String Search, String system_id,
			String degree_id, String professional_id, String course_id, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return LOPDAO.DataTablePracticalDataTotalCount(Search, system_id, degree_id, professional_id, course_id, role);
	}

	@RequestMapping(value = "/Practical_ChildUrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Practical_ChildUrl(String hid) {
		ArrayList<ArrayList<String>> list = LOPDAO.getPopup_Practical_ChildDatalist(hid);
		return list;
	}

	@PostMapping(value = "/deletePractical_Url")
	public ModelAndView deletePractical_Url(@ModelAttribute("id2") String id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

//		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();

		try {
			int app = session.createQuery(
					"update CC_TB_LIST_OF_PRACTICAL_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			int app1 = session.createQuery(
					"update CC_TB_LIST_OF_PRACTICAL_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
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
		return new ModelAndView("redirect:Search_List_of_practical_Url");
	}

	@RequestMapping(value = "/Edit_Practical_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Practical_Url(@ModelAttribute("id1") String id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession session, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		String role = session.getAttribute("role").toString();

		try {
			if (!id.equals("")) {
				ArrayList<ArrayList<String>> GetPractical_Parent_Data = LOPDAO
						.GetPractical_Parent_Data(Integer.parseInt(id));
				List<ArrayList<String>> litechildlist = LOPDAO.getPractical_Child_By_id(Integer.parseInt(id));
				model.put("edit_list_practicalCMD", LOPDAO.GetPractical_Parent_Data(Integer.parseInt(id)));
				model.put("list", GetPractical_Parent_Data);
				model.put("litechildlist", litechildlist);
			}

			model.put("getSystemList", common.getSystemList(sessionFactory, role));
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			model.put("geti3_termList", common.geti3_termList(sessionFactory));

			model.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Edit_List_of_Practical_Tiles");
	}

	@RequestMapping(value = "/edit_list_practicalAction", method = RequestMethod.POST)
	public ModelAndView edit_list_practicalAction(
			@ModelAttribute("edit_list_practicalCMD") CC_TB_LIST_OF_PRACTICAL_PARENT rs, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			RedirectAttributes ra) throws ParseException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("List_of_practical_Url", roleid1);		
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

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:List_of_practical_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:List_of_practical_Url");
		}

		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));

		for (int i = 1; i <= new_count_hidden; i++) {

			String practical = request.getParameter("practical" + i);
			String term_id = request.getParameter("term_id" + i);
			String activity_practical_description = request.getParameter("activity_practical_description" + i);

			if (practical == null || practical.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Practical.");
				return new ModelAndView("redirect:List_of_practical_Url");
			}
			if (validation.maxlengthcheck500(practical) == false) {
				ra.addAttribute("msg","Practical "+ validation.MaxlengthcheckMSG500);
				return new ModelAndView("redirect:List_of_practical_Url");
			}
			if (term_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Term.");
				return new ModelAndView("redirect:List_of_practical_Url");
			}
			if (activity_practical_description == null || activity_practical_description.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Activity/Practical Description.");
				return new ModelAndView("redirect:List_of_practical_Url");
			}
//			if (validation.isOnlyAlphabet(activity_practical_description) == false) {
//				ra.addAttribute("msg", "Activity/Practical Description " + validation.isOnlyAlphabetMSG);
//				return new ModelAndView("redirect:List_of_practical_Url");
//			}
			if (validation.checkDescriptionLengthHelpdeskLength(activity_practical_description) == false) {
				ra.addAttribute("msg","Activity Practical Description "+ validation.DescriptionLengthHelpdeskMSG);
				return new ModelAndView("redirect:List_of_practical_Url");
			}
		}
		try {

			String hql = "update CC_TB_LIST_OF_PRACTICAL_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			if (old_hidden_att <= new_count_hidden) {
				for (int j = 1; j <= old_hidden_att; j++) {

					String editid = request.getParameter("eid" + j);

					CC_TB_LIST_OF_PRACTICAL_CHILD add = (CC_TB_LIST_OF_PRACTICAL_CHILD) session1
							.get(CC_TB_LIST_OF_PRACTICAL_CHILD.class, Integer.parseInt(editid));

					String practical = request.getParameter("practical" + j);
					String term_id = request.getParameter("term_id" + j);
					String activity_practical_description = request.getParameter("activity_practical_description" + j);

					add.setPractical(practical);
					add.setTerm_id(Integer.parseInt(term_id));
					add.setActivity_practical_description(activity_practical_description);
					add.setCreated_by(username);
					add.setCreated_date(date);
					add.setId(Integer.parseInt(editid));
					session1.update(add);
					session1.flush();
					session1.clear();
				}
			}
			if (old_hidden_att < new_count_hidden) {
				CC_TB_LIST_OF_PRACTICAL_CHILD xray = new CC_TB_LIST_OF_PRACTICAL_CHILD();

				for (int j = old_hidden_att + 1; j <= new_count_hidden; j++) {

					String practical = request.getParameter("practical" + j);
					String term_id = request.getParameter("term_id" + j);
					String activity_practical_description = request.getParameter("activity_practical_description" + j);

					xray.setPractical(practical);
					xray.setTerm_id(Integer.parseInt(term_id));
					xray.setActivity_practical_description(activity_practical_description);
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

					CC_TB_LIST_OF_PRACTICAL_CHILD del = (CC_TB_LIST_OF_PRACTICAL_CHILD) session1
							.get(CC_TB_LIST_OF_PRACTICAL_CHILD.class, Integer.parseInt(editid));
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
		return new ModelAndView("redirect:Search_List_of_practical_Url");
	}

	@RequestMapping(value = "/getListOfPracticalsviewdata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getListOfPracticalsviewdata(String course_id) {
		List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list = PARDAO
				.TableList_of_practicalDataTotalCount(course_id);
		return t3LearningObject_Course_AyUGRS_list;
	}

	@RequestMapping(value = "/getListOfPracticals2viewdata", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getListOfPracticals2viewdata(String course_id) {
		List<ArrayList<String>> practhours = PARDAO.practhours(course_id);
		return practhours;
	}

	@RequestMapping(value = "/getDegreeListbysystem1", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem1(String system_name) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = common.getDegreeListbysystem(sessionFactory, system_name);
		return list;
	}
}