package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
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
import com.AyushEdu.Core_Directory.Set_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Type_Of_Lecture_Master_CD_DAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Set_MasterDAO;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;

import freemarker.core.ParseException;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Set_MasterController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Set_MasterDAO  sdao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Set_Master_CD_DAO  Set_dirdao;
	
	@GetMapping(value = "/setmaster_url")
	public ModelAndView setmaster_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		try {
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("setmaster_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		model.addAttribute("msg", msg);
	      //  model.put("getEducationStatusList", common.getEducationStatusList());
	     //	model.put("list", ddao.getAllData(""));//fetch to table
		model.addAttribute("gettermListforSet", common.gettermListforSet(sessionFactory));
		model.addAttribute("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Set_MasterTiles");
	
}
	
	@PostMapping(value = "/setMaster_action")
	public ModelAndView setMaster_action(@Validated @ModelAttribute("set_cmd") TB_SET_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("setmaster_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String setname = request.getParameter("setname");
		String term_id = request.getParameter("term_id");
		String prof_name = request.getParameter("prof_name");
		String status = request.getParameter("status");

		if (setname == null || setname.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Set Name.");
			return new ModelAndView("redirect:setmaster_url");
		}
		if (validation.maxlengthcheck50(setname) == false) {
			ra.addAttribute("msg", "Set Name " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:setmaster_url");
		}
		if (validation.isOnlyAlphabetDASH(setname) == false) {
			ra.addAttribute("msg", "Set Name " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:setmaster_url");
		}
		if (term_id == null || term_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Term.");
			return new ModelAndView("redirect:setmaster_url");
		}
//		if (prof_name == null || prof_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Profession Name.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.maxlengthcheck50(prof_name) == false) {
//			ra.addAttribute("msg", "Profession Name " + validation.MaxlengthcheckMSG50);
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.isOnlyAlphabetDASH(prof_name) == false) {
//			ra.addAttribute("msg", "Profession Name " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:setmaster_url");
//		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:setmaster_url");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:setmaster_url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_SET_MASTER where upper(status)=:status and upper(setname)=:setname and term_id=:term_id and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("setname", td.getSetname().toUpperCase())
					.setParameter("term_id", td.getTerm_id())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			//For Core Directory
			Set_dirdao.Insert_Set_Master_Data(idd);
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

		return new ModelAndView("redirect:setmaster_url");
	}
	

	@PostMapping("/getFiltersetmaster_data")

	public @ResponseBody List<Map<String, Object>> getFiltersetmaster_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String setname, String status, String term_id,String prof_name) {

		return sdao.DataTablesetmasterDataList(startPage, pageLength, Search, orderColunm, orderType, setname,status, term_id, prof_name);

	}

	@PostMapping("/getTotalsetmaster_dataCount")
	public @ResponseBody long getTotalsetmaster_dataCount(HttpSession sessionUserId, String Search, String setname , String term_id,String prof_name) {
		return sdao.DataTablesetmasterDataTotalCount(Search, setname, term_id,prof_name);
		
	}
	
	@PostMapping(value = "/deletesetmaster_Url")
	public ModelAndView deletesetmaster_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("setmaster_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update TB_SET_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			
			tx.commit();
			Set_dirdao.Delete_Set_Master_Data(id);  
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:setmaster_url");
	}
	
	@RequestMapping(value = "/Edit_setmasterUrl")
	public ModelAndView Edit_setmasterUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {

		if(request.getHeader("Referer") == null ) { 
		//	sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("setmaster_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		TB_SET_MASTER Setname_Details = sdao.getSetmasterByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("Setname_Details", Setname_Details);
		Mmap.put("gettermListforSet", common.gettermListforSet(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		return new ModelAndView("editsetmasterTiles");
	}
	
	
	@RequestMapping(value = "/edit_setmaster_Action", method = RequestMethod.POST)
	public ModelAndView edit_setmaster_Action(@ModelAttribute("edit_SetmasterCMD") TB_SET_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//s	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("setmaster_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String setname = request.getParameter("setname").trim();
		String term_id = request.getParameter("term_id");
		String prof_name = request.getParameter("prof_name").trim();
		String status = request.getParameter("status");
		
		if (setname == null || setname.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Set Name.");
			return new ModelAndView("redirect:setmaster_url");
		}
		if (validation.maxlengthcheck50(setname) == false) {
			ra.addAttribute("msg", "Set Name " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:setmaster_url");
		}
		if (validation.isOnlyAlphabetDASH(setname) == false) {
			ra.addAttribute("msg", "Set Name " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:setmaster_url");
		}
//		if (term_id == null || term_id.equals("")) {
//			ra.addAttribute("msg", "Please Select Profession.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (prof_name == null || prof_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Profession Name.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.maxlengthcheck50(prof_name) == false) {
//			ra.addAttribute("msg", "Profession Name " + validation.MaxlengthcheckMSG50);
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.isOnlyAlphabetDASH(prof_name) == false) {
//			ra.addAttribute("msg", "Profession Name " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:setmaster_url");
//		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:setmaster_url");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:setmaster_url");
		}
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		try {
			Query q0 = session1.createQuery(
					"select count(id) from TB_SET_MASTER where setname=:setname and status=:status and term_id=:term_id and id !=:id");
			q0.setParameter("status", rs.getStatus().toUpperCase());
			q0.setParameter("setname", rs.getSetname().toUpperCase());
			q0.setParameter("term_id", rs.getTerm_id());
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_SET_MASTER set setname=:setname,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("setname", setname)
//						.setParameter("term_id", Integer.parseInt(term_id))
						.setParameter("status", status)
						.setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				Set_dirdao.Update_Set_Master_Data( id, setname, status,username,new Date(),prof_name);
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
		
		return new ModelAndView("redirect:setmaster_url");
	}

}