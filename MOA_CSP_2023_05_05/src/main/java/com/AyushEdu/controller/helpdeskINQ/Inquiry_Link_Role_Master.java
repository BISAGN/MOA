package com.AyushEdu.controller.helpdeskINQ;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.Models.helpdeskINQ.HD_INQ_LINK_ROLE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.helpdeskINQ.HD_Inquiry_CategoryDao;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = { "admin", "/", "user" })	
public class Inquiry_Link_Role_Master {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	HD_Inquiry_CategoryDao ICD;
	
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Inquiry_Link_Role_Mster_Url", method = RequestMethod.GET)
	public ModelAndView Inquiry_Link_Role_Mster_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY - POOJA
//			if(request.getHeader("Referer") == null ) { 
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Inquiry_Link_Role_Mster_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String staff_lvl = "";
			String role = session.getAttribute("role").toString();
			
//			String userId = session.getAttribute("userId").toString();
			
			if (role.equals("NCISM_ADMIN")) {
				staff_lvl = "NCISM";
			}
			else if (role.equals("NCH_ADMIN")) {
				staff_lvl = "NCH";
			}
			Mmap.put("getRoleFromStaff_lvl", common.getRoleFromStaff_lvl(sessionFactory, staff_lvl));
		 Mmap.put("msg", msg);
		 Mmap.put("getRoleNameList", getRoleNameList());
		 Mmap.put("getInq_CatList", getInq_CatList());
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//		 Mmap.put("getUserList", getUserList());

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Inquiry_Link_Role_Mster_Tiles");
	}
	
	
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
	
	public List<Role> getInq_CatList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from HD_INQUIRY_CATEGORY_MSTR where status = 1 order by id");
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	/////////////////////////////////////////user///////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "admin/getuser", method = RequestMethod.POST)
	public @ResponseBody List<String> getuser(
			String role_id) {
		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String qry = " select a.userId,a.userName  from UserLogin a,\n"
				+ "UserRole b  \n"
				+ "where b.userId=a.userId and b.roleId =:role_id ";
		
//		String system_qry = " and m.system_id=:system_id ";
//		String degree_qry = " and m.degree_id=:degree_id ";
//		String prof_qry = " and m.professional_id=:professional_id ";
//		
//		if(system_id != null && system_id!="") {
//			qry += system_qry;
//		}
//		if(degree_id != null && degree_id!="") {
//			qry += degree_qry;
//		}
//		if(professional_id != null  && professional_id!="") {
//			qry += prof_qry;
//		}
//		
		Query q = sessionHQL.createQuery(qry);
//		
//		if(system_id != null && system_id!="") {
//			q.setParameter("system_id", Integer.parseInt(system_id));
//		}
//		if(degree_id != null && degree_id!="") {
//			q.setParameter("degree_id", Integer.parseInt(degree_id));
//		}
//		if(professional_id != null  && professional_id!="") {
//			q.setParameter("professional_id", Integer.parseInt(professional_id));
//		}
//		
//		@SuppressWarnings("unchecked")
		q.setParameter("role_id", Integer.parseInt(role_id));
		List<String> clist = (List<String>) q.list();
		
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	@RequestMapping(value = "/getuserform", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getuserform(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String role_id) {
	       return ICD.getuser(role_id);
	}


	@PostMapping(value = "/Inq_Link_Role_Action")
	public ModelAndView Inq_Link_Role_Action(@Validated @ModelAttribute("Inq_Link_Role_CMD") HD_INQ_LINK_ROLE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Inquiry_Link_Role_Mster_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
//		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		String inq_no = request.getParameter("inq_no");
		String role = request.getParameter("role");
		String user = request.getParameter("user");
		String status = request.getParameter("status");
		if (inq_no.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Inquiry Number");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (validation.maxlengthcheck50(inq_no) == false) {
			ra.addAttribute("msg","Inquiry Number "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (validation.isOnlyNumer1to9(inq_no) == false) {
			ra.addAttribute("msg", "Inquiry Number " + validation.isOnlyNumerMSG1to9);
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (role == null || role.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Role.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (user == null || user.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select User.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
//		String system_name = principal.getName();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(*) from HD_INQ_LINK_ROLE_MSTR where inq_no=:inq_no and role=:role and user_id1=:user_id1 and status=:status and id !=:id ")
					  .setParameter("inq_no",inq_no) 
					  .setParameter("role",Integer.parseInt(role)) 
					  .setParameter("user_id1",Integer.parseInt(user))
					  .setParameter("status",Integer.parseInt(status))
					  .setParameter("id", id).uniqueResult();
			

			
			if (id == 0) {
				
				

//				td.setCreated_dt(created_dt);
				if (c == 0) {
					td.setCreated_by(username);
					td.setCreated_dt(date);
					td.setInq_no(inq_no);
					td.setRole(Integer.parseInt(role));
					td.setUser_id1(Integer.parseInt(user));
					td.setStatus(Integer.parseInt(status));
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
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

		return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
	}
	
	
	@PostMapping("/getFilterInq_Cat_datalist")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterInq_Cat_datalist(HttpSession session, int startPage,
			             int pageLength,String Search, String orderColunm, String orderType, String inq_no, String role, String user, String status) {

		return (ArrayList<Map<String, Object>>) ICD.getFilterInq_Cat_datalist(startPage, pageLength, Search,
				orderColunm, orderType, inq_no,  role,  user,  status);

	}

	@PostMapping("/getTotalInq_Cat_dataCount1")
	public @ResponseBody long getTotalInq_Cat_dataCount1(HttpSession sessionUserId, String Search,
			String inq_no, String role, String user ,String status) {

		return ICD.getTotalInq_Cat_dataCount1(Search, inq_no,  role,  user,  status);

	}

	@RequestMapping(value = "/Inquiry_Link_Role_Master_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Inquiry_Link_Role_Master_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

//		if(request.getHeader("Referer") == null ) { 
//	//		 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Inquiry_Link_Role_Mster_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update HD_INQ_LINK_ROLE_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status", 2).executeUpdate(); 

			
			tx.commit();
//			gen_dirdao.Delete_Gender_Master_Data(id);  
			session.close();
			if (app > 0) {
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
		return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
	}
	
	
	@RequestMapping(value = "/Edit_Inquiry_Link_Role_Master_Url1", method = RequestMethod.POST)
	public ModelAndView Edit_Inquiry_Link_Role_Master_Url1(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {
//
//		if(request.getHeader("Referer") == null ) { 
//	//		 sessionEdit.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = sessionEdit.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Inquiry_Link_Role_Mster_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String staff_lvl = "";
		String role = sessionEdit.getAttribute("role").toString();
//		String userId = session.getAttribute("userId").toString();
		
		if (role.equals("NCISM_ADMIN")) {
			staff_lvl = "NCISM";
		}
		else if (role.equals("NCH_ADMIN")) {
			staff_lvl = "NCH";
		}
		Mmap.put("getRoleFromStaff_lvl", common.getRoleFromStaff_lvl(sessionFactory, staff_lvl));
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		HD_INQ_LINK_ROLE_MSTR inq_Mstr = ICD.getInquiry_Link_RoleByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
		Mmap.put("getRoleNameList", getRoleNameList());
		Mmap.put("Inquiry_Mstr", inq_Mstr);
//		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	//	Mmap.put("Enhance_Research_Mstr", ARC.getEnhance_Research_Category_MstrByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Edit_Inquiry_Link_Role_MasterTiles");
	}
	
	
	@RequestMapping(value = "/Edit_Inq_Link_Role_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Inq_Link_Role_Action(@ModelAttribute("Edit_Inq_Link_Role_CMD") HD_INQ_LINK_ROLE_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
	{
		
		
		
//		if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//				
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Inquiry_Link_Role_Mster_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		String username = session.getAttribute("username").toString();
		
		
		String inq_no = request.getParameter("inq_no");
		String role = request.getParameter("role");
		String user = request.getParameter("user");
		String status = request.getParameter("status");
		int id = Integer.parseInt(request.getParameter("id"));
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (inq_no.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Inquiry Number");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (validation.maxlengthcheck50(inq_no) == false) {
			ra.addAttribute("msg","Inquiry Number "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (role == null || role.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Role.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		if (user == null || user.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select User.");
			return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
		}
		

		
		try {
			Query q0 = session1.createQuery(
					"select count(id) from HD_INQ_LINK_ROLE_MSTR where inq_no=:inq_no and role=:role and user_id1=:user_id1  and id !=:id");
			
			q0.setParameter("inq_no", inq_no);
			q0.setParameter("role",Integer.parseInt( role));
			q0.setParameter("user_id1",Integer.parseInt( user));
//			q0.setParameter("status",Integer.parseInt( status));
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();
			
			if (c == 0) {
				String hql = "update HD_INQ_LINK_ROLE_MSTR set inq_no=:inq_no,role=:role,user_id1=:user_id1,modified_by=:modified_by,modified_dt=:modified_dt where id=:id";

				Query query = session1.createQuery(hql).setParameter("inq_no", inq_no)
						.setParameter("role", Integer.parseInt(role))
						.setParameter("user_id1", Integer.parseInt(user))
//						.setParameter("status", Integer.parseInt(status))
						.setParameter("id", id)
						.setParameter("modified_by", username)
						.setParameter("modified_dt", new Date());
					
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
//				//For Core Directory
//				DM_dirdao.Update_Degree_Cate_Mstr_Data( id,  Degree_Category,  Integer.parseInt(status),  username,  new Date());
				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
		
		finally {
			if (session1 != null) {
				session1.close();
			}
		}
		
		return new ModelAndView("redirect:Inquiry_Link_Role_Mster_Url");
	}
	}
	
	
}
	
