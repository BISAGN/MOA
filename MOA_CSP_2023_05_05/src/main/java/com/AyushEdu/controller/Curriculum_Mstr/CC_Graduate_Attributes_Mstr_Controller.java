package com.AyushEdu.controller.Curriculum_Mstr;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GRADUATE_ATTRIBUTES_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_Graduate_Attributes_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Graduate_Attributes_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CC_Graduate_Attributes_Mstr_Dao GAdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Graduate_Attributes_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Graduate_Attributes_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Graduate_Attributes_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		try {
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Graduate_Attributes_Mstr_Tiles");
	}
	
	//==========================================SAVE/view/Edit Graduate Attributes========================================== 	

	
	@PostMapping(value = "/Graduate_AttributesAction")
	public ModelAndView Graduate_AttributesAction(@Validated @ModelAttribute("Graduate_AttributesCMD") CC_TB_GRADUATE_ATTRIBUTES_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Graduate_Attributes_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String code = request.getParameter("code");
		String graduate_attributes = request.getParameter("graduate_attributes");
		String status = request.getParameter("status");

		if (code == null || code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Code.");
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (validation.Currmaxlengthcheck5Digit(code) == false) {
			ra.addAttribute("msg","Code "+ validation.CurrMaxlengthcheckMSG5Digit);
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (validation.isOnlyAlphabetNumeric(code) == false) {
			ra.addAttribute("msg","code "+ validation.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (graduate_attributes == null || graduate_attributes.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Graduate Attributes.");
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		
		if (validation.isNumerickavi(graduate_attributes) == true) {
			ra.addAttribute("msg", "Enter Valid Graduate Attributes. ");
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		
		if (validation.isAlphabetCDASH(graduate_attributes) == false) {
			ra.addAttribute("msg", "Graduate Attributes " + validation.isAlphabetWSCDASH);
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (validation.maxlengthcheck(graduate_attributes) == false) {
			ra.addAttribute("msg"," Graduate Attributes "+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_GRADUATE_ATTRIBUTES_MSTR where code=:code and upper(graduate_attributes)=:graduate_attributes and status=:status and id !=:id")
					.setParameter("code", td.getCode())
					.setParameter("graduate_attributes", td.getGraduate_attributes().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setCode(code);
				td.setGraduate_attributes(graduate_attributes);
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
//					sessionHQL.save(td);
//					sessionHQL.flush();
//					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setCode(code.trim());
				td.setGraduate_attributes(graduate_attributes.trim());
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = GAdao.updateGraduate_Attributes(td);
					
					ra.addAttribute("msg", "Data Updated Successfully.");
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
		return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
	}
	
	@PostMapping("/getFilterGraduate_Attributes_data")
	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String code, String graduate_attributes, String status) {
		return GAdao.DataTableGraduate_AttributesDataList(startPage, pageLength, Search, orderColunm, orderType,code, graduate_attributes,status);
	}

	@PostMapping("/getTotalGraduate_Attributes_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,String code, String graduate_attributes, String status) {
		return GAdao.DataTableGraduate_AttributesDataTotalCount(Search,code, graduate_attributes,status);
	}
	
	@RequestMapping(value = "/Graduate_Attributes_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Graduate_Attributes_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Graduate_Attributes_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			int app = session.createQuery(
					"update CC_TB_GRADUATE_ATTRIBUTES_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();
			
			tx.commit();
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
		return new ModelAndView("redirect:Graduate_Attributes_Mstr_Url");
	}
}
