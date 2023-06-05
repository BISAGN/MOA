package com.AyushEdu.controller.LMS_Institute;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_FACULTY_ELE_COURSE_LINK;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_LINK;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Link_System_Elective_CourseDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_System_Elective_Course_Controller {
	@Autowired
	//@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	CommonController common = new CommonController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Link_System_Elective_CourseDAO  SED;
	@Autowired
	ValidationController validation = new ValidationController();
	
	@GetMapping(value = "/link_system_Elective_Course_url")
	public ModelAndView link_system_Elective_Course_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		 try {
			 
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_system_Elective_Course_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String role = session.getAttribute("role").toString();
		 model.addAttribute("msg", msg);
		 model.put("getSystemList", common.getsysList(sessionFactory));
		 model.put("getcoursenameList", common.getcoursenameList(sessionFactory));
		 //model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("link_system_Elective_Course_Tiles");
	
}
	
	@PostMapping(value = "/link_system_Elective_Course_Action")
	public ModelAndView link_system_Elective_Course_Action(
			@Validated @ModelAttribute("link_system_Elective_Course_CMD") EDU_LMS_SYSTEM_ELE_COURSE_LINK rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_system_Elective_Course_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		int id = rd.getId() > 0 ? rd.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
	//	DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String add_elective_name = request.getParameter("add_elective_name");
			String remove_elective_name = request.getParameter("remove_elective_name");
			String old_elective_name = request.getParameter("old_elective_name");
			String new_elective_name = request.getParameter("new_elective_name");
			
		
			String system_id = request.getParameter("system_id");
			String elec_course_id = request.getParameter("elec_course_id");
			String degree_id = request.getParameter("degree_id");
			
			if (system_id == "0" || system_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select System.");
				return new ModelAndView("redirect:link_system_Elective_Course_url");
			}
			if (degree_id == "0" || degree_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Degree.");
				return new ModelAndView("redirect:link_system_Elective_Course_url");
			}
			if (new_elective_name == "" || new_elective_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Select atleast One Elective Course.");
				return new ModelAndView("redirect:link_system_Elective_Course_url");
			}
			
			try {

				List<String> newList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_elective_name.split(",")));
				if (new_elective_name != null && !new_elective_name.equals("")) {
					newList = Arrays.asList(new_elective_name.split(","));
				}
				
				List<String> addList = new ArrayList<String>();
				List<String> removeList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_elective_name.split(",")));
				if (add_elective_name != null && !add_elective_name.equals("")) {
					addList = Arrays.asList(add_elective_name.split(","));
				}
				if (remove_elective_name != null && !remove_elective_name.equals("")) {
					removeList = Arrays.asList(remove_elective_name.split(","));
				}

				if (removeList.size() > 0) {
					for (int i = 0; i < removeList.size(); i++) {
						System.err.println("------remove===>>  "+removeList.get(i));
						String hqlUpdate = "delete from EDU_LMS_SYSTEM_ELE_COURSE_LINK where elec_course_id=:elec_course_id and system_id=:system_id and degree_id=:degree_id ";
						int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("system_id",Integer.parseInt(system_id))
								.setParameter("degree_id",Integer.parseInt(degree_id))
//								.setParameter("elec_course_id",(removeList.get(i))).executeUpdate();
								.setParameter("elec_course_id",(Integer.parseInt(removeList.get(i)))).executeUpdate();
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				
				if (addList.size() > 0) {
					for (int i = 0; i < addList.size(); i++) {
						
						EDU_LMS_SYSTEM_ELE_COURSE_LINK obj = new EDU_LMS_SYSTEM_ELE_COURSE_LINK();
						
						obj.setCreated_by(username);
						obj.setCreated_date(date);
						obj.setSystem_id(Integer.parseInt(system_id));
						obj.setDegree_id(Integer.parseInt(degree_id));
						obj.setElec_course_id(Integer.parseInt(addList.get(i)));
//						obj.setElec_course_id(addList.get(i));

						int s_id2 = (int) sessionHQL.save(obj);
						model.put("s_id",s_id2);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");

					}
				}else {
					ra.addAttribute("msg", "Data Already Exists.");
				}

					tx.commit();
			
			
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
		return new ModelAndView("redirect:link_system_Elective_Course_url");
	}

	
	 @RequestMapping(value = "/getDegreeFromCourse_Checked", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getDegreeFromCourse_Checked(String degree_id,String system_id) {
	    	
		 List<ArrayList<String>> list = SED.getDegreeFromCourse_Checked(degree_id,system_id);	 
			return list;
		}
	
	
	    @RequestMapping(value = "/getSystemFromElec_Course", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getSystemFromElec_Course(String system_id,String degree_id) {
	    	
		 List<ArrayList<String>> list = SED.GetSystemCourse_Degree(system_id,degree_id);	 
			return list;
		}
	    
	    
	    @SuppressWarnings("deprecation")
		@PostMapping(value = "/admin/getSystemlistFromDegree")
		public @ResponseBody List<Map> getSystemlistFromDegree( String system_id) {
		
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				try {
					
					Query q1=sessionHQL.createQuery("select d.id,d.degree_name from EDU_LMS_SYS_DEG_MAP_MASTER s, EDU_LMS_DEGREE_MASTER d \n"
							+ "where d.id=cast(s.degree_name as integer) and system_name=:system_name and s.status='1'  ");
					
					q1.setInteger("system_name",Integer.parseInt(system_id));
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
	    
	
		
		@RequestMapping(value = "/getFilterLink_System_Elective_Course_data", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getFilterLink_System_Elective_Course_data(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,HttpSession sessionUserId, Principal principal,String system_id,
				String degree_id) {
			
			return SED.DataTableLink_System_Elective_CourseDataList(startPage, pageLength, Search, orderColunm, orderType,sessionUserId, system_id, degree_id);

		}

		@RequestMapping(value = "/getTotalLink_System_Elective_Course_dataCount", method = RequestMethod.POST)
		public @ResponseBody long getTotalLink_System_Elective_Course_dataCount(HttpSession sessionUserId, String Search,String system_id,
				String degree_id) {
			return SED.DataTableLink_System_Elective_CourseDataTotalCount(Search,sessionUserId,system_id,degree_id);
		}

}
