package com.AyushEdu.RBAC;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.lucene.analysis.ar.ArabicStemFilterFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.TB_LDAP_ROLE_TYPE;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class RoleMstrController {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CommonController common;
	
	

	ValidationController validation = new ValidationController();
	
	// open Role master page
	@RequestMapping(value = "/role_mstUrl", method = RequestMethod.GET)
	public ModelAndView role_mstUrl(ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		if(request.getHeader("Referer") == null ) { 
	//		 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("role_mstUrl", roleid1);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			List<Map<String, Object>> list = roledao.RoleSearchReport();
			Mmap.put("list", list);
			Mmap.put("getRoleType", getRoleType());
			Mmap.put("getsysList", common.getsysList(sessionFactory));
		
			
		
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("role_mstTiles");
		//}
	}
	
	// save and update role master
	@RequestMapping(value = "/role_mstAction", method = RequestMethod.POST)
	public ModelAndView role_mstAction(@ModelAttribute("role_mstCMD") Role p,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		
		
			if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
						
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("role_mstUrl", roleid1);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		
		String msg = "";
		
		Session sessionHQL = this.sessionFactory.openSession();
		String roleid = session.getAttribute("roleid").toString();
		//Boolean val = roledao.ScreenRedirect("role_mstUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		int role_id = 0;
		if (request.getParameter("sub_lvl_text") == "") {
			role_id = 0;
		} else {
			role_id = Integer.parseInt(request.getParameter("sub_lvl_text"));
		}
		try {
			if (p.getRole().equals("")) {
				model.put("msg", "Please Enter Role Name");
				return new ModelAndView("redirect:role_mstUrl");
			} else if (validation.RoleLength(p.getRole()) == false) {
				model.put("msg", validation.RoleMSG);
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("role_type").equals("0")) {
				model.put("msg", "Please Select Role Type");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("access_lvl").equals("")) {
				model.put("msg", "Please Select Access Level");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if (request.getParameter("access_lvl").equals("")) {
				model.put("msg", "Please Select Access Level");
				return new ModelAndView("redirect:role_mstUrl");
			}
			if ((!p.getAccess_lvl().equals("Unit") && p.getAccess_lvl().toString() != "Unit") && !p.getAccess_lvl().equals("Depot") ) {
				if (request.getParameter("formation_se").equals("")) {
					model.put("msg", "Please Select Sub Access Level");
					return new ModelAndView("redirect:role_mstUrl");
				}
			}
			if (!roledao.getroleExist(p.getRole()).equals(false)) {
				model.put("msg", "Data Already Exist");
				return new ModelAndView("redirect:role_mstUrl");
			}
			p.setRole_url("commonDashboard");
			
			String sub_access_lvl = p.getSub_access_lvl();
			
			String formation_se = request.getParameter("formation_se");
			String system_id =  request.getParameter("system_name");
			System.out.println("====system_id - "+system_id);
			
			System.out.println("sub_access_lvl+============================"+formation_se);
			
			
			p.setSub_access_lvl(formation_se);
			p.setSystem_id(Integer.parseInt(system_id));
//			String stf_lvl = p.getStaff_lvl();
//			stf_lvl = stf_lvl.substring(0,stf_lvl.length()-1);
//			p.setStaff_lvl(stf_lvl);
			if (role_id == 0) {
			sessionHQL.beginTransaction();
			sessionHQL.save(p);
			sessionHQL.getTransaction().commit();
			msg = "Data saved Successfully";
		}else {
				Transaction tx = sessionHQL.beginTransaction();
				String hql = "UPDATE Role SET role=:role,role_type=:role_type,access_lvl=:access_lvl,sub_access_lvl=:sub_access_lvl,staff_lvl=:staff_lvl,system_id=:system_id WHERE roleId=:role_id ";
				Query query = sessionHQL.createQuery(hql).setString("role", p.getRole())
						.setString("role_type",p.getRole_type())
						.setString("staff_lvl", p.getStaff_lvl())
						.setString("access_lvl", p.getAccess_lvl())
						.setString("sub_access_lvl",p.getSub_access_lvl())
						.setInteger("system_id",p.getSystem_id())
						.setInteger("role_id", role_id);
				int rowCount = query.executeUpdate();
				
				if(rowCount > 0) {
					msg = "Data Updated Successfully";
				}else {
					msg = "Data Not Updated";
				}
				
				tx.commit();
			}
			model.put("msg",msg);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msg", "Data not saved");
		} finally {
			sessionHQL.close();
		}
		return new ModelAndView("redirect:role_mstUrl");
	}
	// report for Screen Master
		@RequestMapping(value = "/role_report")
		public ModelAndView role_report(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				@RequestParam(value = "role", required = false) String role,
				@RequestParam(value = "role_type", required = false) String role_type,
				@RequestParam(value = "access_lvl", required = false) String access_lvl,
				@RequestParam(value = "formation_se", required = false) String formation_se,
				@RequestParam(value = "staff_lvl", required = false) String staff_lvl,
				@RequestParam(value = "staff_se", required = false) String staff_se,
				HttpServletRequest request) {

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val1 = roledao.ScreenRedirect("screen_mstUrl", roleid1);
			if (val1 == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
			}

			Mmap.put("role1", role);
			Mmap.put("role_type1", role_type);
			Mmap.put("getRoleType", getRoleType());
			
			Mmap.put("access_lvl1", access_lvl);
			Mmap.put("formation_se1", formation_se);
			Mmap.put("staff_lvl1", staff_lvl);
			Mmap.put("staff_se1", staff_se);
			List<Map<String, Object>> list = roledao.RoleSearchReport();
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available");
			} else {
				Mmap.put("list", list);
			}
			Mmap.put("list.size()", list.size());
			return new ModelAndView("role_mstTiles");
		}
	// Role list
	public List<Role> getRoleNameList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Role order by role");
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	// Role Type list
	public List<TB_LDAP_ROLE_TYPE> getRoleType() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_ROLE_TYPE  ");
		@SuppressWarnings("unchecked")
		List<TB_LDAP_ROLE_TYPE> list = (List<TB_LDAP_ROLE_TYPE>) q.list();
		tx.commit();
		session.close();
		return list;
	}
}
