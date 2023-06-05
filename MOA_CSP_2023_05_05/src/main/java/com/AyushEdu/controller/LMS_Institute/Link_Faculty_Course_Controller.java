package com.AyushEdu.controller.LMS_Institute;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Institute.Link_faculty_Course_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_Faculty_Course_Controller {

	@Autowired
	// @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private Link_faculty_Course_DAO FCD;
	@Autowired
	private Commondao coomonDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@GetMapping(value = "/link_Faculty_Elective_Course_url")
	public ModelAndView link_Faculty_Elective_Course_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY RAHUL

			if (request.getHeader("Referer") == null) {
			//	session.invalidate();
				model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("link_Faculty_Elective_Course_url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
			String userId = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = common.getInstIdfromUserid(sessionFactory, userId).get(0);
			model.addAttribute("msg", msg);
			model.put("get_faculty_name_list_Fetch",FCD.get_faculty_name_list_Fetch(sessionFactory, userId, institute_id));
			model.put("getcoursenameList", coomonDAO.getCourseForfaculty(userid));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("400");
		}
		return new ModelAndView("link_Faculty_Elective_Course_Tiles");

	}
	
	@PostMapping(value = "/link_Faculty_Elective_Course_Action")
	public ModelAndView link_Faculty_Elective_Course_Action(
			@Validated @ModelAttribute("Link_institute_System_CMD") EDU_LMS_FACULTY_ELE_COURSE_LINK rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {
		
		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("link_Faculty_Elective_Course_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		int id = rd.getId() > 0 ? rd.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
	//	DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String add_ele_course_name = request.getParameter("add_ele_course_name");
			String remove_ele_course_name = request.getParameter("remove_ele_course_name");
			String old_ele_course_name = request.getParameter("old_ele_course_name");
			String new_ele_course_name = request.getParameter("new_ele_course_name");
			
			Integer faculty_id = rd.getFaculty_id();
			
			if (faculty_id.equals(0) ) {
					ra.addAttribute("msg", "Please Select Faculty Name");
					return new ModelAndView("redirect:link_Faculty_Elective_Course_url");
			}
			
			try {

				
				List<String> newList = new ArrayList<String>();
				//System.out.println("symp list=="+ Arrays.asList(add_ele_course_name.split(",")));
				if (new_ele_course_name != null && !new_ele_course_name.equals("")) {
					newList = Arrays.asList(new_ele_course_name.split(","));
				}
				
				List<String> addList = new ArrayList<String>();
				List<String> removeList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_ele_course_name.split(",")));
				if (add_ele_course_name != null && !add_ele_course_name.equals("")) {
					addList = Arrays.asList(add_ele_course_name.split(","));
				}
				if (remove_ele_course_name != null && !remove_ele_course_name.equals("")) {
					removeList = Arrays.asList(remove_ele_course_name.split(","));
				}

				if (removeList.size() > 0) {
					for (int i = 0; i < removeList.size(); i++) {
						String hqlUpdate = "delete from EDU_LMS_FACULTY_ELE_COURSE_LINK where ele_course_id=:ele_course_id and faculty_id=:faculty_id ";
						int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("faculty_id",faculty_id)
								.setParameter("ele_course_id",(Integer.parseInt(removeList.get(i)) )).executeUpdate();
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				
				if (addList.size() > 0) {
					for (int i = 0; i < addList.size(); i++) {
						
						EDU_LMS_FACULTY_ELE_COURSE_LINK obj = new EDU_LMS_FACULTY_ELE_COURSE_LINK();
						
						obj.setCreated_by(username);
						obj.setCreated_date(date);
						obj.setFaculty_id(faculty_id);
						obj.setEle_course_id(Integer.parseInt(addList.get(i)));

						int s_id2 = (int) sessionHQL.save(obj);
						model.put("s_id",s_id2);
						sessionHQL.flush();
						sessionHQL.clear();

					}
				}

					tx.commit();
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
		return new ModelAndView("redirect:link_Faculty_Elective_Course_url");
	}

	
	@RequestMapping(value = "/getFacultyFromCourse", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_FACULTY_ELE_COURSE_LINK> getFacultyFromCourse(Integer faculty_id) {
		List<EDU_LMS_FACULTY_ELE_COURSE_LINK> list =null;
		try {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "select distinct ele_course_id from EDU_LMS_FACULTY_ELE_COURSE_LINK where faculty_id=:faculty_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("faculty_id", faculty_id);
	   list = (List<EDU_LMS_FACULTY_ELE_COURSE_LINK>) query.list();
		
		tx.commit();
		sessionHQL.close();
		} catch (Exception e) {
		e.printStackTrace();
		
		}
		return list;
	}


}
