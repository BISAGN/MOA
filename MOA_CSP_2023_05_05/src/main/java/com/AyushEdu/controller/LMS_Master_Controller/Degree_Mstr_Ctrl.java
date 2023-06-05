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
import com.AyushEdu.Core_Directory.Degree_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Degree_Master_DAO;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Degree_Mstr_Ctrl {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	@Autowired
	ValidationController validation;
	@Autowired
	Degree_Master_DAO  dmdao;
	
	@Autowired
	Degree_Master_CD_DAO  DM_dirdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "/degree_master_url")
	public ModelAndView degree_master_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		
		 try {
			 
			//SECURITY RAHUL
			 
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("degree_master_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
		 model.addAttribute("msg", msg);
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 model.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
		model.put("gettermListforSet", common.gettermListforSet(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("degree_master_Tiles");
}
	
	@PostMapping(value = "/Degree_Master_action")	public ModelAndView Degree_Master_action(@Validated @ModelAttribute("Degree_Master_cmd") EDU_LMS_DEGREE_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("degree_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String type_of_degree = request.getParameter("type_of_degree");
		String degree_name = request.getParameter("degree_name");
		String semester = request.getParameter("semester");
		String degree_code = request.getParameter("degree_code");
		String status = request.getParameter("status");
		
		if (type_of_degree == null || type_of_degree.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Degree.");
			return new ModelAndView("redirect:degree_master_url");
		}
		
		if (degree_name == null || degree_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Degree.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.maxlengthcheck50(degree_name) == false) {
			ra.addAttribute("msg", "Degree " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:District");
		}
		if (validation.isOnlyAlphabetDASH(degree_name) == false) {
			ra.addAttribute("msg", "Degree " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (semester == null || semester.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Semester.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.maxlengthcheck2(semester) == false) {
			ra.addAttribute("msg", "Semester Name " + validation.MaxlengthcheckMSG2);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.isOnlyNumer(semester) == true) {
			ra.addAttribute("msg", "Semester Name " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (degree_code == null || degree_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Degree Code.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.isOnlyAlphabetNumeric(degree_code) == false) {
			ra.addAttribute("msg", "Degree " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:degree_master_url");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:degree_master_url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_DEGREE_MASTER where upper(status)=:status and upper(degree_name)=:degree_name and semester=:semester and degree_code=:degree_code and type_of_degree=:type_of_degree and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("degree_name", td.getDegree_name().toUpperCase())
					.setParameter("semester", td.getSemester().toUpperCase())
					.setParameter("degree_code", td.getDegree_code().toUpperCase())
					.setParameter("type_of_degree", td.getType_of_degree())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				
				
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
			} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			//For Core Directory
			DM_dirdao.Insert_Degree_Mstr_Data(idd);
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

		return new ModelAndView("redirect:degree_master_url");
	}
	
	@PostMapping("/getFilter_degree_master_data")

	public @ResponseBody List<Map<String, Object>> getFilter_degree_master_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType,String type_of_degree, String degree_name,String semester,String degree_code, String status) {

		return dmdao.DataTableDegree_masterDataList(startPage, pageLength, Search, orderColunm, orderType, type_of_degree,degree_name, semester,degree_code, status);
	}

	@PostMapping("/getTotal_degree_master_dataCount")
	public @ResponseBody long getTotal_degree_master_dataCount(HttpSession sessionUserId, String Search, String type_of_degree,String degree_name,String semester,String degree_code,String status) {
		return dmdao.DataTableDegree_masterDataTotalCount(Search, type_of_degree,degree_name, semester,degree_code,status);
	}
		
	
	@PostMapping(value = "/delete_Degree_mstr_Url")
	public ModelAndView delete_Degree_mstr_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("degree_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
 
			int app = session.createQuery(
					"update EDU_LMS_DEGREE_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();
			
			
		
			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Degree_Mstr_Data(id);  
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
		}finally {
			
		}
		return new ModelAndView("redirect:degree_master_url");
	}
	
	
	
	@RequestMapping(value = "/Edit_Degree_mstrUrl")
	public ModelAndView Edit_Degree_mstrUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {

		if(request.getHeader("Referer") == null ) { 
			// sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("degree_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_DEGREE_MASTER Edit_degree_master_details = dmdao.get_degreeByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("Edit_degree_master_details", Edit_degree_master_details);
		Mmap.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("edit_degree_master_Tiles");
	}
		
	@RequestMapping(value = "/Edit_Degree_Master_action", method = RequestMethod.POST)
	public ModelAndView Edit_Degree_Master_action(@ModelAttribute("Edit_Degree_Master_cmd") EDU_LMS_DEGREE_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("degree_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String type_of_degree = request.getParameter("type_of_degree");
		String degree_name = request.getParameter("degree_name").trim();
		String semester = request.getParameter("semester").trim();
		String degree_code = request.getParameter("degree_code").trim();
		String status = request.getParameter("status");
		
		if (type_of_degree == null || type_of_degree.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Degree.");
			return new ModelAndView("redirect:degree_master_url");
		}
		
		if (degree_name == null || degree_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Degree.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.maxlengthcheck50(degree_name) == false) {
			ra.addAttribute("msg", "Degree " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:District");
		}
		if (validation.isOnlyAlphabetDASH(degree_name) == false) {
			ra.addAttribute("msg", "Degree " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (semester == null || semester.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Semester.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.maxlengthcheck2(semester) == false) {
			ra.addAttribute("msg", "Semester Name " + validation.MaxlengthcheckMSG2);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.isOnlyNumer(semester) == true) {
			ra.addAttribute("msg", "Semester Name " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		
		if (degree_code == null || degree_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Degree Dode.");
			return new ModelAndView("redirect:degree_master_url");
		}
		if (validation.isOnlyAlphabetNumeric(degree_code) == false) {
			ra.addAttribute("msg", "Degree " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:degree_master_url");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:degree_master_url");
		}
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_DEGREE_MASTER where type_of_degree=:type_of_degree and degree_name=:degree_name and semester=:semester and degree_code=:degree_code and status=:status and id !=:id");
			q0.setParameter("type_of_degree", Integer.parseInt(type_of_degree));
			q0.setParameter("degree_name", degree_name);
			q0.setParameter("semester", semester);
			q0.setParameter("degree_code", degree_code);
			q0.setParameter("status", status);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_DEGREE_MASTER set type_of_degree=:type_of_degree, degree_name=:degree_name,semester=:semester,degree_code=:degree_code,status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
						+ " where id=:id";

				Query query = session1.createQuery(hql)
						.setParameter("type_of_degree", Integer.parseInt(type_of_degree))
						.setParameter("degree_name", degree_name)
						.setParameter("semester", semester)
						.setParameter("degree_code", degree_code)
						.setParameter("status", status)
						.setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				
				//For Core Directory
				DM_dirdao.Update_Degree_Mstr_Data( id,  type_of_degree,  degree_name,  semester,  degree_code,  status,  username,  new Date());

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
		return new ModelAndView("redirect:degree_master_url");
	}
	
}
