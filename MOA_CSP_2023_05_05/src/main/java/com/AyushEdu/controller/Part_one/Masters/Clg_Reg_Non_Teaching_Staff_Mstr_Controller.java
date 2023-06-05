package com.AyushEdu.controller.Part_one.Masters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.System_Master_CD_DAO;
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_NON_TEACHING_STAFF_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.Clg_Reg_Non_Teaching_StaffDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Non_Teaching_Staff_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	System_Master_CD_DAO SM_dirdao;

	@Autowired
	ValidationController validation;

	@Autowired
	Clg_Reg_Non_Teaching_StaffDAO sdao;

	// ==========================================OPEN PAGE
	// SYSTEM==========================================

	@RequestMapping(value = "/Non_Teaching_Staff_Url", method = RequestMethod.GET)
	public ModelAndView Non_Teaching_Staff_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		try {
//
//			if (request.getHeader("Referer") == null) {
////			 session.invalidate();
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("PostUrl", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			Mmap.put("msg", msg);
//			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("clg_non_teaching_staff");

	}

	@PostMapping(value = "/Non_Teaching_StaffAction")
	public ModelAndView Non_Teaching_StaffAction(@Validated @ModelAttribute("Non_Teaching_StaffCMD") CLG_REG_NON_TEACHING_STAFF_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, 
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("University_type_Mstr_Url", roleid1);
		 * if(val == false) { return new ModelAndView("AccessTiles");
		 
		}*/

		String post_name = request.getParameter("post_name");
		String status = request.getParameter("status");

		if (post_name == null || post_name.equals("")) {
			ra.addAttribute("msg", "Please Enter Post Name.");
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		
		if (validation.maxlengthcheck100(post_name) == false) {
			ra.addAttribute("msg","Post name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
			
		}
		
		
		  if (validation.isOnlyAlphabetDASH(post_name) == false) {
		  ra.addAttribute("msg","Post "+ validation.isOnlyAlphabetMSGDASH);
		  
		  return new ModelAndView("redirect:Non_Teaching_Staff_Url"); }
		 
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Post.");
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}

		
		Date date = new Date();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.out.println(userid);			
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = td.getId() > 0 ? td.getId() : 0;
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CLG_REG_NON_TEACHING_STAFF_MSTR where upper(post_name)=:post_name and status=:status and id !=:id")
					.setParameter("post_name", td.getPost_name().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setPost_name(post_name);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setPost_name(post_name);
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = sdao.updateNonTeachingStaff(td);
					//For Core Directory
					//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//					if (msg == "Data Updated Successfully") {
//						model.put("msg", msg);
//						model.put("Nmsg", "0");
//					} else {
//						model.put("msg", msg);
//						model.put("Nmsg", "1");
//					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
//					model.put("msg", "Data already Exist");
//					model.put("Nmsg", "1");
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

			tx.commit();
			//For Core Directory
			//DM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
		
		return new ModelAndView("redirect:Non_Teaching_Staff_Url");
	}
	
	
	@RequestMapping(value = "/Non_Teaching_StaffDeleteUrl", method = RequestMethod.POST)
	public ModelAndView Non_Teaching_StaffDeleteUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CLG_REG_NON_TEACHING_STAFF_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			/* DM_dirdao.Delete_Uni_Type_Mstr_Data(id); */
			session.close();
			if (app > 0) {
				System.err.println("check delete"+(app > 0));
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
		return new ModelAndView("redirect:Non_Teaching_Staff_Url");
	}

	
	

	@PostMapping("/getNonTeachingStaffdata")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String post_name, String status) {

		return sdao.DataTableNonTeachingStaffDataList(startPage, pageLength, Search, orderColunm, orderType, post_name, status);

	}

	@PostMapping("/getTotalNonTeachingStaff_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String post_name) {
		return sdao.DataTableNonTeachingStaffDataTotalCount(Search, post_name);

	}

	/*
	 * @RequestMapping(value = "/Edit_postUrl", method = RequestMethod.POST) public
	 * ModelAndView Edit_postUrl(@ModelAttribute("id2") String updateid, ModelMap
	 * Mmap, Principal principal,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg, HttpServletRequest
	 * request, HttpSession sessionEdit) {
	 * 
	 * if (request.getHeader("Referer") == null) { // sessionEdit.invalidate();
	 * Mmap.put("msg",
	 * "Suspicious Activity Detected,You have been logged out by Administrator");
	 * return new ModelAndView("redirect:/login"); }
	 * 
	 * String roleid1 = sessionEdit.getAttribute("roleid").toString(); Boolean val =
	 * roledao.ScreenRedirect("PostUrl", roleid1); if (val == false) { return new
	 * ModelAndView("AccessTiles"); } String role =
	 * sessionEdit.getAttribute("role").toString(); Session sessionHQL =
	 * this.sessionFactory.openSession(); Transaction tx =
	 * sessionHQL.beginTransaction(); CLG_REG_NON_TEACHING_STAFF_MSTR post_Details =
	 * sdao.getpostByid(Integer.parseInt(updateid)); Mmap.addAttribute("msg", msg);
	 * Mmap.put("post_name", common.getPostList(sessionFactory, role));
	 * Mmap.put("post_Details", post_Details); Mmap.put("ActiveInActiveList",
	 * common.getActiveInActiveList(sessionFactory)); Mmap.put("postinfo",
	 * sdao.getpostByid(Integer.parseInt(updateid)));
	 * 
	 * tx.commit(); sessionHQL.close();
	 * 
	 * return new ModelAndView("editPost_Tiles"); }
	 */

	
	/* edit action */
	  
	@RequestMapping(value = "/edit_post_Action", method = RequestMethod.POST)
	public ModelAndView edit_post_Action(@ModelAttribute("edit_postCMD") CLG_REG_NON_TEACHING_STAFF_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PostUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String post_name = request.getParameter("post_name").trim();
		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		
		if (post_name == null || post_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Post.");
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		if (validation.maxlengthcheck100(post_name) == false) {
			ra.addAttribute("msg","Post "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		if (validation.isOnlyAlphabetDASH(post_name) == false) {
			ra.addAttribute("msg","Post "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Non_Teaching_Staff_Url");
		}
		
		try {
			Query q0 = session1.createQuery(
					"select count(id) from CLG_REG_NON_TEACHING_STAFF_MSTR where post_name=:post_name and status=:status and id !=:id");
			q0.setParameter("post_name", post_name);
			q0.setParameter("status", status);

			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update CLG_REG_NON_TEACHING_STAFF_MSTR set post_name=:post_name,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("post_name", post_name).setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				
				/*
				 * SM_dirdao.Update_System_Mstr_Data(id, system_name, system_abbr, status,
				 * username, new Date());
				 */

				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}

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
		
		return new ModelAndView("redirect:Non_Teaching_Staff_Url");
	}

	// -------------------------SEARCH Battalion
	// -------------------------------------//

	/*
	 * @RequestMapping(value = "/admin/getSearch_Post_Master", method =
	 * RequestMethod.POST) public ModelAndView getSearch_Post_Master(ModelMap
	 * Mmap,HttpSession session,HttpServletRequest request,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg,
	 * 
	 * @RequestParam(value = "Post_name1", required = false) String Post_name1
	 * ,String Post_name,@ModelAttribute("status1") String status) {
	 * 
	 * if(request.getHeader("Referer") == null ) { // session.invalidate();
	 * Mmap.put("msg",
	 * "Suspicious Activity Detected,You have been logged out by Administrator");
	 * return new ModelAndView("redirect:/login"); } String roleid1 =
	 * session.getAttribute("roleid").toString(); Boolean val =
	 * roledao.ScreenRedirect("PostUrl", roleid1); if(val == false) { return new
	 * ModelAndView("AccessTiles"); }
	 * 
	 * Mmap.put("Post_name1", Post_name1); Mmap.put("status1", status);
	 * Mmap.put("getStatusMasterList",
	 * common.getActiveInActiveList(sessionFactory));
	 * 
	 * 
	 * return new ModelAndView("Post_Tiles","SystemCMD",new
	 * CLG_REG_NON_TEACHING_STAFF_MSTR());
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value = "/delete_Url2", method = RequestMethod.POST) public
	 * ModelAndView delete_Url2(@ModelAttribute("id1") int id, BindingResult result,
	 * RedirectAttributes ra, HttpServletRequest request, ModelMap model,
	 * HttpSession session1) {
	 * 
	 * if(request.getHeader("Referer") == null ) { // session1.invalidate();
	 * model.put("msg",
	 * "Suspicious Activity Detected,You have been logged out by Administrator");
	 * return new ModelAndView("redirect:/login"); } String roleid1 =
	 * session1.getAttribute("roleid").toString(); Boolean val =
	 * roledao.ScreenRedirect("SystemUrl", roleid1); if(val == false) { return new
	 * ModelAndView("AccessTiles"); }
	 * 
	 * List<String> liststr = new ArrayList<String>();
	 * 
	 * Session session = this.sessionFactory.openSession(); Transaction tx =
	 * session.beginTransaction();
	 * 
	 * String username = session1.getAttribute("username").toString(); try {
	 * 
	 * int app = session.createQuery(
	 * "update CLG_REG_NON_TEACHING_STAFF_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id"
	 * ) .setParameter("id", id).setParameter("modified_by", username)
	 * .setParameter("modified_dt", new Date()).setParameter("status",
	 * "2").executeUpdate();
	 * 
	 * 
	 * tx.commit(); SM_dirdao.Delete_System_Mstr_Data(id); System.out.println();
	 * session.close(); if (app > 0) { liststr.add("Data Deleted Successfully."); }
	 * else { liststr.add("Data not Deleted."); } ra.addAttribute("msg",
	 * liststr.get(0)); } catch (Exception e) { e.printStackTrace();
	 * liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
	 * ra.addAttribute("msg", liststr.get(0));
	 * 
	 * } return new ModelAndView("redirect:SystemUrl"); }
	 */

}
	  

