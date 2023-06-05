package com.AyushEdu.controller.Part_one.Masters;

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
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.Last_Year_Teacher_Dtls_DAO;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Last_Year_Teacher_Dtls_Master_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	
	@Autowired
	Last_Year_Teacher_Dtls_DAO tdao;
	
	//==========================================OPEN PAGE==========================================
	@RequestMapping(value = "/LAST_YEAR_TEACHER_DtlsUrl", method = RequestMethod.GET)
	public ModelAndView LAST_YEAR_TEACHER_DtlsUrl (ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	
//		try {
//		if(request.getHeader("Referer") == null ) { 
//	//		 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("LAST_YEAR_TEACHER_DtlsUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
		return new ModelAndView("Teacher_Dtls_Mstr_Tiles");

	}
	

//==========================================SAVE NAME==========================================
@PostMapping(value = "/TeacherDtlsAction")
public ModelAndView TeacherDtlsAction(@Validated @ModelAttribute("TeacherCMD") CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
		RedirectAttributes ra) {

//	if(request.getHeader("Referer") == null ) { 
//	//	 session.invalidate();
//		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//		 return new ModelAndView("redirect:/login");
//	 }
//	String role = session.getAttribute("role").toString();
//	String roleid1 = session.getAttribute("roleid").toString();
//	 Boolean val = roledao.ScreenRedirect("LAST_YEAR_TEACHER_DtlsUrl", roleid1);		
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//	}
	String teacher_name = request.getParameter("teacher_name");
	String status = request.getParameter("status");

	if (teacher_name == null || teacher_name.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter Teacher Name.");
		return new ModelAndView("redirect:LAST_YEAR_TEACHER_DtlsUrl");
	}
	

	if (status == null || status.trim().equals("0")) {
		ra.addAttribute("msg", "Please Select Status.");
		return new ModelAndView("redirect:LAST_YEAR_TEACHER_DtlsUrl");
	}
	if (status == "2" || status.trim().equals("2")) {
		ra.addAttribute("msg", "Only Select Active Status.");
		return new ModelAndView("redirect:LAST_YEAR_TEACHER_DtlsUrl");
	}
	
	
	int id = td.getId() > 0 ? td.getId() : 0;
	Date date = new Date();
	System.out.print("id --------------------- "+id);
	String username = principal.getName();
//	String username1 = principal.getName();
	
//	String system_name = principal.getName();

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR where upper(teacher_dtls)=:teacher_dtls and status=:status and id !=:id")
				.setParameter("teacher_dtls", teacher_name)
				.setParameter("status", Integer.parseInt(status))
				.setParameter("id", id).uniqueResult();
		if (id == 0) {
			td.setTeacher_dtls(teacher_name);	
			td.setCreated_by(username);
			td.setCreated_date(date);
			if (c == 0) {
				sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();
				ra.addAttribute("msg", "Data Saved Successfully.");
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}else {
			td.setTeacher_dtls(teacher_name);
			td.setModified_by(username);
			td.setModified_date(date);
			if (c == 0) {
				td.setId(id);
				
//				String msg = tdao.updatelast_year_teacher_dtls(td);
//				//For Core Directory
//				//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//				if (msg == "Data Updated Successfully") {
//					model.put("msg", msg);
////					model.put("Nmsg", "0");
//				} else {
//					model.put("msg", msg);
////					model.put("Nmsg", "1");
//				}
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
////				model.put("msg", "Data already Exist");
////				model.put("Nmsg", "1");
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

	return new ModelAndView("redirect:LAST_YEAR_TEACHER_DtlsUrl");
}







@PostMapping("/getFilterteacher_data")

public @ResponseBody List<Map<String, Object>> getFilterteacher_data(int startPage, int pageLength,

		String Search, String orderColunm, String orderType, String teacher_name, String status) {


	
	return tdao.DataTablelast_year_teacher_dtlsList(startPage, pageLength, Search, orderColunm, orderType, teacher_name,status);

}

@PostMapping("/getTotalteacher_dataCount")
public @ResponseBody long getTotalteacher_dataCount(HttpSession sessionUserId, String Search, String teacher_name) {
	

	
	return tdao.DataTablelast_year_teacher_dtlsTotalCount(Search, teacher_name);
	
}






//-----edit

@RequestMapping(value = "/Edit_last_year_teacher_dtls_Url", method = RequestMethod.POST)
public ModelAndView Edit_last_year_teacher_dtls_Url(@ModelAttribute("id2") String updateid, HttpSession session,  ModelMap Mmap, Principal principal,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "teacher_dtls", required = false) String teacher_name,
		@RequestParam(value = "status", required = false) String status,
		HttpServletRequest request, HttpSession sessionEdit) {
	String id =request.getParameter("id");
	String userid = session.getAttribute("userId_for_jnlp").toString();
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR updatelast_year_teacher_dtls = tdao.getlast_year_teacher_dtlsByid(Integer.parseInt(updateid));
	Mmap.addAttribute("msg", msg);
	Mmap.put("teacher_dtls", teacher_name);
	Mmap.put("status", status);
	Mmap.put("updatelast_year_teacher_dtls", updatelast_year_teacher_dtls);
	Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	Mmap.put("teacher_dtlsinfo", tdao.getlast_year_teacher_dtlsByid(Integer.parseInt(updateid)));
	
	tx.commit();
	
	sessionHQL.close();

	return new ModelAndView("Teacher_Dtls_Mstr_Tiles");
}





@RequestMapping(value = "/last_year_teacher_dtls_Mstr_Delete_Url", method = RequestMethod.POST)
public ModelAndView last_year_teacher_dtls_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {

	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {

		int app = session.createQuery(
				"update CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
				.setParameter("id", id).setParameter("modified_by", username)
				.setParameter("modified_date", new Date())
				.setParameter("status", 2).executeUpdate();

		
		tx.commit();
		//For Core Directory
		//DM_dirdao.Delete_Uni_Type_Mstr_Data(id); 
		//session.close();
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
	return new ModelAndView("redirect:LAST_YEAR_TEACHER_DtlsUrl");
}
//// -------------------------SEARCH Battalion  -------------------------------------//
//
//@RequestMapping(value = "/admin/getSearch_last_year_teacher_dtls_Master", method = RequestMethod.POST)
//public ModelAndView getSearch_last_year_teacher_dtls_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
//		@RequestParam(value = "msg", required = false) String msg,
//		@RequestParam(value = "Teacher_name1", required = false) String Teacher_name1 ,String Teacher_name,@ModelAttribute("status1") String status)  {
//
// if(request.getHeader("Referer") == null ) { 
////	 session.invalidate();
//	 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//	 return new ModelAndView("redirect:/login");
// }
////String roleid1 = session.getAttribute("roleid").toString();
//// Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
////	if(val == false) {
////		return new ModelAndView("AccessTiles");
////}
//
//		Mmap.put("Teacher_name1", Teacher_name1);
//		Mmap.put("status1", status);
//		Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
//		
//
//	   return new ModelAndView("Teacher_Dtls_Mstr_Tiles","TeacherCMD",new CLG_REG_LAST_YEAR_TEACHER_DTLS_MSTR());
//	   
//}


}	




