package com.AyushEdu.controller.LMS_Institute;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_SYSTEM_LINK;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Link_Institute_SystemDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_Institute_System_Controller {
	@Autowired
	//@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	CommonController common = new CommonController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Link_Institute_SystemDAO LISD;
	
	@GetMapping(value = "/link_institute_system_registration_url")
	public ModelAndView link_institute_system_registration_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		
		try {	
			
		//SECURITY RAHUL
			
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_institute_system_registration_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}	
		
		 model.addAttribute("msg", msg);
		 model.put("getInstituteList", common.getInstituteList(sessionFactory));
		 model.put("getsysList", common.getsysList(sessionFactory));
		 //model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("link_institute_system_Tiles");
}
	
	@PostMapping(value = "/Link_institute_System_Action")
	public ModelAndView Link_institute_System_Action(
			@Validated @ModelAttribute("Link_institute_System_CMD") EDU_LMS_INSTITUTE_SYSTEM_LINK rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {

		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_institute_system_registration_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}	
			
		int id = rd.getId() > 0 ? rd.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
	//	DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String add_system_name = request.getParameter("add_system_name");
			String remove_system_name = request.getParameter("remove_system_name");
			String old_system_name = request.getParameter("old_system_name");
			String new_system_name = request.getParameter("new_system_name");
			
			String institute_id =request.getParameter("institute_id");
			String system_id = request.getParameter("system_id");
			
			if (institute_id.equals("0") ) {
					ra.addAttribute("msg", "Please Select College");
					return new ModelAndView("redirect:link_institute_system_registration_url");
			}
			
			try {

				if (old_system_name != null && old_system_name.equals("")) {
					
					String hqlUpdate = "update EDU_LMS_INSTITUTE_SYSTEM_LINK set system_id=:system_id where institute_id=:institute_id";
						int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("institute_id",institute_id)
								.setParameter("system_id",system_id).executeUpdate();
						
						sessionHQL.flush();
						sessionHQL.clear();
				}
				
				List<String> newList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_system_name.split(",")));
				if (new_system_name != null && !new_system_name.equals("")) {
					newList = Arrays.asList(new_system_name.split(","));
				}
				
				List<String> addList = new ArrayList<String>();
				List<String> removeList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_system_name.split(",")));
				if (add_system_name != null && !add_system_name.equals("")) {
					addList = Arrays.asList(add_system_name.split(","));
				}
				if (remove_system_name != null && !remove_system_name.equals("")) {
					removeList = Arrays.asList(remove_system_name.split(","));
				}

				if (removeList.size() > 0) {
					for (int i = 0; i < removeList.size(); i++) {
						String hqlUpdate = "delete from EDU_LMS_INSTITUTE_SYSTEM_LINK where system_id=:system_id and institute_id=:institute_id ";
						int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("institute_id",institute_id)
								.setParameter("system_id",(removeList.get(i))).executeUpdate();
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				
				if (addList.size() > 0) {
					for (int i = 0; i < addList.size(); i++) {
						
						EDU_LMS_INSTITUTE_SYSTEM_LINK obj = new EDU_LMS_INSTITUTE_SYSTEM_LINK();
						
						obj.setCreated_by(username);
						obj.setCreated_date(date);
						obj.setInstitute_id(institute_id);
						obj.setSystem_id(addList.get(i));

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
		return new ModelAndView("redirect:link_institute_system_registration_url");
	}

	
//	@RequestMapping(value = "/admin/getsystemlist", method = RequestMethod.POST)
//	public @ResponseBody List<String> getsystemlist() {
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		String hqlUpdate = "select id,system_name from EDU_LMS_SYSTEM_MSTR";
//		Query query = sessionHQL.createQuery(hqlUpdate);
//		List<String> list = (List<String>) query.list();
//		tx.commit();
//		sessionHQL.close();
//		System.err.println("list of sys ---"+list);
//		return list;
//	}

	
	@RequestMapping(value = "/admin/getInstituteFromSystem", method = RequestMethod.POST)
	public @ResponseBody List<String> getInstituteFromSystem(String institute_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "select system_id from EDU_LMS_INSTITUTE_SYSTEM_LINK where institute_id=:institute_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setParameter("institute_id", institute_id);
		List<String> list = (List<String>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	
	}
	
	@RequestMapping(value = "/getFilterLink_Institute_System_data", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getFilterLink_Institute_System_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,HttpSession sessionUserId, Principal principal, String institute_id) {
		System.out.println("hiiiiiii================");
		return LISD.DataTableLink_Institute_SystemDataList(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,institute_id);

	}

	@RequestMapping(value = "/getTotalLink_Institute_System_dataCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalLink_Institute_System_dataCount(HttpSession sessionUserId, String Search,String institute_id) {
		return LISD.DataTableLink_Institute_SystemDataTotalCount(Search,sessionUserId,institute_id);
	}

	
	
}
