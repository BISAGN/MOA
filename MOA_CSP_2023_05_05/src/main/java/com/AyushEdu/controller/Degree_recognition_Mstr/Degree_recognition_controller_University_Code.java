package com.AyushEdu.controller.Degree_recognition_Mstr;
import java.security.Principal;
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
import com.AyushEdu.Core_Directory.Set_Master_CD_DAO;
import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_UNIVERSITY_CODE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_mstr.Degree_reco_university_code_mstr_Dao;
import com.AyushEdu.dao.LMS_Master.Set_MasterDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })


public class Degree_recognition_controller_University_Code {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Degree_reco_university_code_mstr_Dao  unidao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;

	@GetMapping(value = "/university_code_url") 
	public ModelAndView university_code_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		model.addAttribute("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		//return new ModelAndView("university_code_url");
		
		return new ModelAndView("university_code_Tiles");
		
		
	}
	
	
	@PostMapping(value = "/uni_code_action")
	public ModelAndView uni_code_action(@Validated @ModelAttribute("unicodeaction") DG_REC_UNIVERSITY_CODE_MSTR td, 
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
		String state= request.getParameter("state");
		System.out.println("---state--"+state);
		String name_of_university = request.getParameter("name_of_university");
		String university_id = request.getParameter("university_id");
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
					"select count(id) from  DG_REC_UNIVERSITY_CODE_MSTR where "
					+ "upper(state)=:state and upper(name_of_university)=:name_of_university "
					+ "and upper(university_id)=:university_id and upper(status)=:status and id !=:id" )
					
					.setParameter("state", td.getState().toUpperCase())
					.setParameter("name_of_university", td.getName_of_university().toUpperCase())
					.setParameter("university_id", td.getUniversity_id().toUpperCase())
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

		return new ModelAndView("redirect:university_code_url");
	}
	
	@PostMapping("/getFilteruniversity_code_data")

	public @ResponseBody List<Map<String, Object>> getFilteruniversity_code_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, 
			String state, String name_of_university,String university_id,String status) {

		return unidao.DataTableuniversity_codeDataList(startPage, pageLength, Search, orderColunm, orderType, 
				state, name_of_university, university_id,status);

	}

	@PostMapping("/getTotaluniversity_codeCount")
	public @ResponseBody long getTotaluniversity_codeCount(HttpSession sessionUserId, String Search,
			String state , String name_of_university,String university_id,String status) {
		return unidao.DataTableuniversity_codeDataTotalCount(Search, state, name_of_university,university_id, status);
		
	}


}
