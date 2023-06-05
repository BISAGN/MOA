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
import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_TEACHING_LEARNING_METHOD_PARENT;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Nch_Teaching_Learning_Method_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_Nch_Teaching_Learning_Method_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Nch_Teaching_Learning_Method_Dao TLDAO;
	
	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	EmailConfig email;
	
	@Autowired
	private RoleBaseMenuDAO roledao;


	@RequestMapping(value = "admin/nch_teaching_learning_url", method = RequestMethod.GET)
	public ModelAndView nch_teaching_learning_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
////			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("nch_teaching_learning_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();		
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("NCH_Teaching_Learning_Method_Tiles");
	}
	
	@PostMapping(value = "/Teaching_Learning_MethodAction")
	public ModelAndView Teaching_Learning_MethodAction(@Validated @ModelAttribute("Teaching_Learning_MethodCMD") CC_TB_NCH_TEACHING_LEARNING_METHOD_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("nch_teaching_learning_url", roleid1);		
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
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		try {

//			Long c = (Long) sessionHQL.createQuery(
//						"select count(id) from  EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_PARENT where system_id=:system_id and degree_id=:degree_id \n"
//						+ " and professional_id=:professional_id and course_id=:course_id and status=0\n"
//						+ " and id!=:id")
//					.setParameter("system_id", Integer.parseInt(system_id))
//					.setParameter("degree_id", Integer.parseInt(degree_id))
//					.setParameter("professional_id", Integer.parseInt(professional_id))
//					.setParameter("course_id", Integer.parseInt(course_id))
//					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
//				if (c == 0) {
					td.setStatus(1);
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD od = new CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD();
					
					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for(int i=1; i <= count_hidden_att; i++) {
						
						String teaching_learning_method = request.getParameter("teaching_learning_method"+i);
						
//						if (teaching_learning_method == null || teaching_learning_method.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Teaching Learning Method.");
//							return new ModelAndView("redirect:nch_teaching_learning_url");
//						}
//						if (validation.maxlengthcheck500(teaching_learning_method) == false) {
//							ra.addAttribute("msg","Teaching Learning Method "+ validation.MaxlengthcheckMSG500);
//							return new ModelAndView("redirect:nch_teaching_learning_url");
//						}
					 	od.setTeaching_learning_method(teaching_learning_method);
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
//				} else {
//					ra.addAttribute("msg", "Data already Exist.");
//				}
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
		return new ModelAndView("redirect:nch_teaching_learning_url");
	}
	
	@PostMapping("/getFilterTeaching_Learning_Methoddata")
	public @ResponseBody List<Map<String, Object>> getFilterTeaching_Learning_Methoddata(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return TLDAO.DataTableTeaching_Learning_MethodDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,professional_id,course_id,role);
	}
	
	@PostMapping("/getTotalTeaching_Learning_MethoddataCount")
	public @ResponseBody long getTotalTeaching_Learning_MethoddataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,String professional_id,String course_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return TLDAO.DataTableTeaching_Learning_MethodDataTotalCount(Search, system_id, degree_id,professional_id,course_id,role);
	}
	
	@RequestMapping(value = "/getTeachingLearningMethodviewdata", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getTeachingLearningMethodviewdata(String course_id) {
	 ArrayList<ArrayList<String>> list = TLDAO.getPopup_TeachingChildDatalist(course_id);
		return list;
	}
	
	@RequestMapping(value = "/Teaching_Learning_Method_ChilddUrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> Teaching_Learning_Method_ChilddUrl(String hid) {
	 ArrayList<ArrayList<String>> list = TLDAO.getPopup_TeachingChildDatalist1(hid);
		return list;
	}
	
	@RequestMapping(value = "/Edit_Teaching_Learning_MethodUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Teaching_Learning_MethodUrl(@ModelAttribute("id1") int id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			HttpSession session,HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("nch_teaching_learning_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {	
		ArrayList<ArrayList<String>> GetTeaching_Learning_Method_ParentData = TLDAO.GetTeaching_Learning_Method_ParentData(id);
		List<ArrayList<String>> litechildlist = TLDAO.getTeaching_Learning_Method_Child_By_id(id);
		String role = session.getAttribute("role").toString();		
		model.put("getSystemList", common.getSystemList(sessionFactory,role));	
		model.put("edit_Add_Non_Lecture_ActivitiesCMD", TLDAO.GetTeaching_Learning_Method_ParentData(id));
		model.put("list", GetTeaching_Learning_Method_ParentData);
		model.put("litechildlist", litechildlist);
		model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		model.put("msg", msg);
	  } catch (Exception e) {
			e.printStackTrace();
	  }
		return new ModelAndView("Edit_Teaching_Learning_Method_Tiles");
	}
	
	@RequestMapping(value = "/edit_Teaching_Learning_MethodAction", method = RequestMethod.POST)
	public ModelAndView edit_Teaching_Learning_MethodAction(@ModelAttribute("edit_Teaching_Learning_MethodCMD") CC_TB_NCH_TEACHING_LEARNING_METHOD_PARENT rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("nch_teaching_learning_url", roleid1);		
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
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:nch_teaching_learning_url");
		}
		
		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
		
		for(int i=1; i <= new_count_hidden; i++) {
			
			String teaching_learning_method = request.getParameter("teaching_learning_method"+i);
			
			if (teaching_learning_method == null || teaching_learning_method.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Teaching Learning Method.");
				return new ModelAndView("redirect:nch_teaching_learning_url");
			}
//			if (validation.isOnlyAlphabet(teaching_learning_method) == false) {
//				ra.addAttribute("msg", "Non Lecture T-L Method " + validation.isOnlyAlphabetMSG);
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
//			if (validation.maxlengthcheck(teaching_learning_method) == false) {
//				ra.addAttribute("msg","Non Lecture T-L Method "+ validation.MaxlengthcheckMSG);
//				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
		}
		try {
			
				String hql = "update CC_TB_NCH_TEACHING_LEARNING_METHOD_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
						msg = query.executeUpdate() > 0 ? "1" : "0";
						
				if ( old_hidden_att <= new_count_hidden) {
					for (int j = 1; j <= old_hidden_att; j++) {
						
						String editid = request.getParameter("eid"+j);
						
						CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD add = (CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD) session1
								.get(CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD.class, Integer.parseInt(editid));
						
						String teaching_learning_method = request.getParameter("teaching_learning_method"+j);

						add.setTeaching_learning_method(teaching_learning_method);
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setId(Integer.parseInt(editid));
						session1.update(add);
						session1.flush();
						session1.clear();
					}
				}
				
				if ( old_hidden_att < new_count_hidden) {
					CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD xray = new CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD();
					
						for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
							
							String teaching_learning_method = request.getParameter("teaching_learning_method"+j);

							xray.setTeaching_learning_method(teaching_learning_method);
							xray.setCreated_by(username);
							xray.setCreated_date(date);
							xray.setP_id(Integer.parseInt(id));
							session1.save(xray);
							session1.flush();
							session1.clear();
						}
				}
				
				if ( old_hidden_att > new_count_hidden) {
					
					for (int j = new_count_hidden + 1; j <= old_hidden_att; j++) {
						
						String editid = request.getParameter("eid"+j);
						CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD del = (CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD) session1
								.get(CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD.class, Integer.parseInt(editid));
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
		return new ModelAndView("redirect:nch_teaching_learning_url");
	}
	
	@PostMapping(value = "/deleteTeaching_Learning_MethodUrl")
	public ModelAndView deleteTeaching_Learning_MethodUrl(@ModelAttribute("id2") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("nch_teaching_learning_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();
		
		try {
			int app = session.createQuery(
					"update CC_TB_NCH_TEACHING_LEARNING_METHOD_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
			
			int app1 = session.createQuery(
					"update CC_TB_NCH_TEACHING_LEARNING_METHOD_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
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
		return new ModelAndView("redirect:nch_teaching_learning_url");
	}
		
}



