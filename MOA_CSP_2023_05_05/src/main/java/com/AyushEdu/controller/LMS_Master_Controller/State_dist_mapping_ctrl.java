package com.AyushEdu.controller.LMS_Master_Controller;

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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_STATE_DIST_MAP;
import com.AyushEdu.dao.RoleBaseMenuDAO;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class State_dist_mapping_ctrl {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "/statedistmap_Url")
	public ModelAndView statedistmap_Url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request  ) {
		try {
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("statedistmap_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		 model.put("msg", msg);
		 model.put("MedStateName", common.getMedStateName(sessionFactory));
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("statedistmap_Tiles");
}
	
	
	
	
	
	@PostMapping(value = "/statedistmap_action")
	public ModelAndView statedistmap_action(@Validated @ModelAttribute("statedistmap_CMD") EDU_LMS_STATE_DIST_MAP rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("statedistmap_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}


		int id = rd.getId() > 0 ? rd.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
	//	DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String add_district_name = request.getParameter("add_district_name");
			String remove_district_name = request.getParameter("remove_district_name");
			String old_district_name = request.getParameter("old_district_name");
			String new_district_name = request.getParameter("new_district_name");
			
			Integer state_id = rd.getState_id();
			
			
			if (state_id.equals(0) ) {
					ra.addAttribute("msg", "Please Select State Name");
					return new ModelAndView("redirect:statedistmap_Url");
			}
			if (new_district_name.equals("") ) {
				ra.addAttribute("msg", "Please Select District Name");
				return new ModelAndView("redirect:statedistmap_Url");
				}
			
			try {

//				if (old_district_name != null && old_district_name.equals("")) {
//					
//					String hqlUpdate = "update EDU_LMS_STATE_DIST_MAP set system_id=:system_id where state_id=:state_id";
//						int app = sessionHQL.createQuery(hqlUpdate)
//								.setParameter("state_id",state_id)
//								.setParameter("system_id",system_id).executeUpdate();
//						
//						sessionHQL.flush();
//						sessionHQL.clear();
//				}
				
				List<String> newList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_district_name.split(",")));
				if (new_district_name != null && !new_district_name.equals("")) {
					newList = Arrays.asList(new_district_name.split(","));
				}
				
				List<String> addList = new ArrayList<String>();
				List<String> removeList = new ArrayList<String>();
				System.out.println("symp list=="+ Arrays.asList(add_district_name.split(",")));
				if (add_district_name != null && !add_district_name.equals("")) {
					addList = Arrays.asList(add_district_name.split(","));
				}
				if (remove_district_name != null && !remove_district_name.equals("")) {
					removeList = Arrays.asList(remove_district_name.split(","));
				}

				if (removeList.size() > 0) {
					for (int i = 0; i < removeList.size(); i++) {
						String hqlUpdate = "delete from EDU_LMS_STATE_DIST_MAP where district_id=:district_id and state_id=:state_id ";
						int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("state_id",state_id)
								.setParameter("district_id",(Integer.parseInt(removeList.get(i)))).executeUpdate();
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				
				if (addList.size() > 0) {
					for (int i = 0; i < addList.size(); i++) {
						
						EDU_LMS_STATE_DIST_MAP obj = new EDU_LMS_STATE_DIST_MAP();
						
						obj.setCreated_by(username);
						obj.setCreated_date(date);
						obj.setState_id(state_id);
						obj.setDistrict_id( Integer.parseInt(addList.get(i)));

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
		return new ModelAndView("redirect:statedistmap_Url");
	}


	
	
	
	@RequestMapping(value = "/getDistFromState_formap", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_STATE_DIST_MAP> getDistFromState_formap(Integer state_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "select district_id from EDU_LMS_STATE_DIST_MAP where state_id=:state_id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("state_id", state_id);
		List<EDU_LMS_STATE_DIST_MAP> list = (List<EDU_LMS_STATE_DIST_MAP>) query.list();
		
		System.err.println("list------>  "+list);
		
		tx.commit();
		sessionHQL.close();
		return list;
	}
}
