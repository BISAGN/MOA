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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Nch_Add_Non_Lecture_Activities_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Nch_Add_Non_Lacture_Activities_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Nch_Add_Non_Lecture_Activities_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/CC_Nch_Add_Non_Lecture_Activities_url", method = RequestMethod.GET)
	public ModelAndView CC_Nch_Add_Non_Lecture_Activities_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("CC_Nch_Add_Non_Lecture_Activities_url", roleid1);		
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
		return new ModelAndView("CC_Nch_Add_Non_Lecture_Activities_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
	@PostMapping(value = "/Nch_Add_Non_Lecture_ActivitiesAction")
	public ModelAndView Nch_Add_Non_Lecture_ActivitiesAction(@Validated @ModelAttribute("Nch_Add_Non_Lecture_ActivitiesCMD") EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Nch_Add_Non_Lecture_Activities_url", roleid1);		
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
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		try {

//			Long c = (Long) sessionHQL.createQuery(
//						"select count(id) from  EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT where system_id=:system_id and degree_id=:degree_id \n"
//						+ " and professional_id=:professional_id and course_id=:course_id and status=0\n"
//						+ " and id!=:id")
//					.setParameter("system_id", Integer.parseInt(system_id))
//					.setParameter("degree_id", Integer.parseInt(degree_id))
//					.setParameter("professional_id", Integer.parseInt(professional_id))
//					.setParameter("course_id", Integer.parseInt(course_id))
//					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
//				if (c == 0) {
//					td.setApp_status(1);
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD od = new EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD();
					
					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for(int i=1; i <= count_hidden_att; i++) {
						
						String non_lecture_tl_method = request.getParameter("non_lecture_tl_method"+i);
						String no_of_hours = request.getParameter("no_of_hours"+i);
						
						if (non_lecture_tl_method == null || non_lecture_tl_method.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Non Lecture T-L Method.");
							return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
						}
						if (validation.maxlengthcheck500(non_lecture_tl_method) == false) {
							ra.addAttribute("msg","Non Lecture T-L Method "+ validation.MaxlengthcheckMSG500);
							return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
						}
//						if (no_of_hours == null || no_of_hours.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter No. Of Activities.");
//							return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//						}
//						if (validation.isOnlyNumer(no_of_hours) == true) {
//							ra.addAttribute("msg", "No. Of Activities " + validation.isOnlyNumerMSG);
//							return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//						}
//						if (validation.maxlengthcheckC(no_of_hours) == false) {
//							ra.addAttribute("msg", "No. Of Activities " + validation.MaxlengthcheckMSGC);
//							return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//						}
					 	od.setNon_lecture_tl_method(non_lecture_tl_method);
					 	od.setNo_of_hours(no_of_hours);
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
		return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
	}
		
	@PostMapping("/getFilterNch_Add_Non_Lecture_Activities_data")
	public @ResponseBody List<Map<String, Object>> getFilterNch_Add_Non_Lecture_Activities_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTableNch_Add_Non_Lecture_ActivitiesDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,professional_id,course_id,role);
	}
	
	@PostMapping("/getTotalNch_Add_Non_Lecture_Activities_dataCount")
	public @ResponseBody long getTotalNch_Add_Non_Lecture_Activities_dataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,String professional_id,String course_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Pdao.DataTableNch_Add_Non_Lecture_ActivitiesDataTotalCount(Search, system_id, degree_id,professional_id,course_id,role);
	}
		
	@RequestMapping(value = "/Nch_Add_Non_Lecture_Activities_ChildUrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> Nch_Add_Non_Lecture_Activities_ChildUrl(String hid) {
	 ArrayList<ArrayList<String>> list = Pdao.getPopup_ChildDatalist(hid);
		return list;
	}
	@RequestMapping(value = "/getNch_AddNonLectureActivitiesviewdata", method = RequestMethod.POST)
	public @ResponseBody   List<ArrayList<String>> getNch_AddNonLectureActivitiesviewdata(String course_id) {
		List<ArrayList<String>> table5nlac_list = Pdao.Nch_Non_Lecture_Activities_Course(course_id);
		return table5nlac_list;
	}
	
	@RequestMapping(value = "/Edit_Nch_Add_Non_Lecture_Activities_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Nch_Add_Non_Lecture_Activities_Url(@ModelAttribute("id1") int id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			HttpSession session,HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Nch_Add_Non_Lecture_Activities_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {	
		ArrayList<ArrayList<String>> GetAdd_Non_Lecture_Activities_Parent_Data = Pdao.GetAdd_Non_Lecture_Activities_Parent_Data(id);
		List<ArrayList<String>> litechildlist = Pdao.getAdd_Non_Lecture_Activities_Child_By_id(id);
		String role = session.getAttribute("role").toString();		
		model.put("getSystemList", common.getSystemList(sessionFactory,role));	
		model.put("edit_Add_Non_Lecture_ActivitiesCMD", Pdao.GetAdd_Non_Lecture_Activities_Parent_Data(id));
		model.put("list", GetAdd_Non_Lecture_Activities_Parent_Data);
		model.put("litechildlist", litechildlist);
		model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		model.put("msg", msg);
	  } catch (Exception e) {
			e.printStackTrace();
	  }
		return new ModelAndView("Edit_Nch_Add_Non_Lecture_Activities_Tiles");
	}
	
	@RequestMapping(value = "/edit_Nch_Add_Non_Lecture_ActivitiesAction", method = RequestMethod.POST)
	public ModelAndView edit_Nch_Add_Non_Lecture_ActivitiesAction(@ModelAttribute("edit_Nch_Add_Non_Lecture_ActivitiesCMD") EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Nch_Add_Non_Lecture_Activities_url", roleid1);		
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
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
		}
		
		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
		
		for(int i=1; i <= new_count_hidden; i++) {
			
			String non_lecture_tl_method = request.getParameter("non_lecture_tl_method"+i);
			String no_of_hours = request.getParameter("no_of_hours"+i);
			
			if (non_lecture_tl_method == null || non_lecture_tl_method.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Non Lecture Activities.");
				return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
			}
			if (validation.maxlengthcheck500(non_lecture_tl_method) == false) {
				ra.addAttribute("msg","Non Lecture T-L Method "+ validation.MaxlengthcheckMSG500);
				return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
			}
//			if (no_of_hours == null || no_of_hours.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter No. Of Hours.");
//				return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
//			}
//			if (validation.isOnlyNumer(no_of_hours) == true) {
//			ra.addAttribute("msg", "No. Of Activities " + validation.isOnlyNumerMSG);
//			return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//		    }
//		    if (validation.maxlengthcheckC(no_of_hours) == false) {
//			ra.addAttribute("msg", "No. Of Activities " + validation.MaxlengthcheckMSGC);
//			return new ModelAndView("redirect:CC_Add_Non_Lecture_Activities_url");
//			}
		}
		
		try {
				String hql = "update  EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
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
						
						EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD add = (EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD) session1
								.get(EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD.class, Integer.parseInt(editid));
						
						String non_lecture_tl_method = request.getParameter("non_lecture_tl_method"+j);
						String no_of_hours = request.getParameter("no_of_hours"+j);

						add.setNon_lecture_tl_method(non_lecture_tl_method);
						add.setNo_of_hours(no_of_hours);
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setId(Integer.parseInt(editid));
						session1.update(add);
						session1.flush();
						session1.clear();
					}
				}
				
				if ( old_hidden_att < new_count_hidden) {
					EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD xray = new EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD();
					
						for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
							
							String non_lecture_tl_method = request.getParameter("non_lecture_tl_method"+j);
							String no_of_hours = request.getParameter("no_of_hours"+j);

							xray.setNon_lecture_tl_method(non_lecture_tl_method);
							xray.setNo_of_hours(no_of_hours);
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
						EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD del = (EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD) session1
								.get(EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD.class, Integer.parseInt(editid));
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
		return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
	}
	
	@PostMapping(value = "/deleteNCH_Non_Lecture_ActivitiesUrl")
	public ModelAndView deleteNCH_Non_Lecture_ActivitiesUrl(@ModelAttribute("id2") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Nch_Add_Non_Lecture_Activities_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();
		
		try {
			int app = session.createQuery(
					"update EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
			
			int app1 = session.createQuery(
					"update EDU_CC_TB_NCH_ADD_NON_LECTURE_ACTIVITIES_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
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
		return new ModelAndView("redirect:CC_Nch_Add_Non_Lecture_Activities_url");
	}
		
//	@RequestMapping(value = "/getTeachingLearningMethodviewdata", method = RequestMethod.POST)
//	public @ResponseBody   List<ArrayList<String>> getTeachingLearningMethodviewdata(String course_id) {
//		List<ArrayList<String>> table5nlac_list = PARDAO.table5_Non_Lecture_Activities_Course_AyUGRS(course_id);
//		return table5nlac_list;
//	}
//	
//	@RequestMapping(value = "/getTeachingLearningMethod2viewdata", method = RequestMethod.POST)
//	public @ResponseBody   List<ArrayList<String>> getTeachingLearningMethod2viewdata(String course_id) {
//		 List<ArrayList<String>> practhours = PARDAO.practhours(course_id);
//		return practhours;
//	}
}
