package com.AyushEdu.controller.Degree_recognition_Mstr;

import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_COLLEGE_CODE_MSTR;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Degree_recognition_mstr.Degree_reco_college_code_mstr_Dao;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_controller_college_code {
	
	private static final Session HibernateUtil = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	 ValidationController validation;
   @Autowired
   private Degree_reco_college_code_mstr_Dao Ddao;
//	@Autowired
//	Degree_reco_clg_Dao Ddao;
	@Autowired
	Commondao commondao;
		
	@GetMapping(value = "/college_code_url") 
	public ModelAndView college_code_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		model.addAttribute("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		//return new ModelAndView("university_code_url");
		
		return new ModelAndView("college_code_Tiles");
		
		
	}
	
	
	@PostMapping(value = "/CollegeAction_mstr")
	public ModelAndView CollegeAction_mstr(@Validated @ModelAttribute("CollegeCMD") DG_REC_COLLEGE_CODE_MSTR td, 
			BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		
//		
//		if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
		
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("university_code_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
		String name_of_college= request.getParameter("name_of_college");
		System.out.println("---name_of_college--"+name_of_college);
		String state = request.getParameter("state");
		String college_id = request.getParameter("college_id");
		String status = request.getParameter("status");

		

//		if (setname == null || setname.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Set Name.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.maxlengthcheck50(setname) == false) {
//			ra.addAttribute("msg", "Set Name " + validation.MaxlengthcheckMSG50);
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (validation.isOnlyAlphabetDASH(setname) == false) {
//			ra.addAttribute("msg", "Set Name " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		if (term_id == null || term_id.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Select Term.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//		}--------------------------------------------
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
//		}-----------------------------------------------------------------------------------
//		if (status == null || status.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Status.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
//
//		if (status == "2" || status.trim().equals("2")) {
//			ra.addAttribute("msg", "Only Select Active Status.");
//			return new ModelAndView("redirect:setmaster_url");
//		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_COLLEGE_CODE_MSTR where "
					+ "upper(name_of_college)=:name_of_college and upper(state)=:state "
					+ "and upper(college_id)=:college_id and upper(status)=:status and id !=:id" )
					
					.setParameter("name_of_college", td.getName_of_college().toUpperCase())
					.setParameter("state", td.getState().toUpperCase())
					.setParameter("college_id", td.getCollege_id().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("id", id).uniqueResult();
			
			
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
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
			
		} catch (RuntimeException e) {
			try {
				
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:college_code_url");
	}

	@PostMapping("/getFiltercollege_code")
	public @ResponseBody List<Map<String, Object>> getFiltercollege_code(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String name_of_college,String state,String college_id, String status) {
System.err.println("----------getFiltercollege_code");
		return Ddao.DataTablecCollegeDataList(startPage, pageLength, Search, orderColunm, 
				orderType, name_of_college,state,college_id,status);

	}

	@PostMapping("/getFilter_Collegecode_ListCount")
	public @ResponseBody long getFilter_Collegecode_ListCount(HttpSession sessionUserId, String Search,
			String name_of_college,String state,String college_id) {
		return Ddao.DataTableCollegeDataTotalCount(Search, name_of_college,state,college_id);
		
	}

	

}