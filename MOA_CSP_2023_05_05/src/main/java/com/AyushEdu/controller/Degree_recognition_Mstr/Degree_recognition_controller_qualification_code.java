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
import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_QUALIFICTATION_CODE_mstr;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_mstr.Degree_reco_qualification_code_mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_recognition_controller_qualification_code {
	
	@Autowired
	private SessionFactory sessionFactory;
	
//	@Autowired
//	private RoleBaseMenuDAO roledao;
//	
	@Autowired
	private Degree_reco_qualification_code_mstr_Dao quaDao;

	@Autowired
	CommonController common;
	
	
	@Autowired
	ValidationController validation;

	@GetMapping(value = "/qualification_code_url") 
	public ModelAndView state_code_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		
		model.addAttribute("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("Qualification_Code_Tiles");
		
	}
	
	@PostMapping(value = "/qualificationMaster_action")
	public ModelAndView qualificationMaster_action(@Validated @ModelAttribute("qual_cmd") 
	DG_REC_QUALIFICTATION_CODE_mstr td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		System.out.println(request + "-----request-----");
//		if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("qualification_code_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		String qualification_name = request.getParameter("qualification_name");
		String codes = request.getParameter("codes");
		int status = Integer.parseInt(request.getParameter("status"));

//		if (qualification_name == null || qualification_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Set Name.");
//			return new ModelAndView("redirect:qualification_code_url");
//		}
//		if (validation.maxlengthcheck50(qualification_name) == false) {
//			ra.addAttribute("msg", "Set Name " + validation.MaxlengthcheckMSG50);
//			return new ModelAndView("redirect:qualification_code_url");
//		}
//		if (validation.isOnlyAlphabetDASH(qualification_name) == false) {
//			ra.addAttribute("msg", "Set Name " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:qualification_code_url");
//		}
//		if (id == null || id.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Select Term.");
//			System.out.println(request + "-----request-----");
//			return new ModelAndView("redirect:qualification_code_url");
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
//		if (status == null || status.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Status.");
//			return new ModelAndView("redirect:qualification_code_urls");
//		}
//
//		if (status == "2" || status.trim().equals("2")) {
//			ra.addAttribute("msg", "Only Select Active Status.");
//			return new ModelAndView("redirect:qualification_code_url");
//		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_QUALIFICTATION_CODE_mstr where "
					+ "status=:status and upper(qualification_name)=:qualification_name "
					+ "and codes=:codes and id !=:id")
					.setParameter("status", td.getStatus())
					.setParameter("qualification_name", td.getQualification_name().toUpperCase())
					.setParameter("codes", td.getCodes())
					.setParameter("id", id).uniqueResult();
			
//			int idd =0;
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

		return new ModelAndView("redirect:qualification_code_url");
	}
	
	@PostMapping("/getFilterqualification_code_data")
	public @ResponseBody List<Map<String, Object>> getFilterqualification_code_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String qualification_name,String codes, String status) {
System.err.println("----------getFilterqualification_code_data");
		return quaDao.DataTableQualificationDataList(startPage, pageLength, Search, orderColunm, 
				orderType, qualification_name,codes,status);

	}

	@PostMapping("/getTotalqualification_codeCount")
	public @ResponseBody long getTotalqualification_codeCount(HttpSession sessionUserId, String Search,
			String qualification_name,String codes) {
		return quaDao.DataTableQualificationDataTotalCount(Search, qualification_name,codes);
		
	}
}
