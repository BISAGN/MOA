package com.AyushEdu.controller.Curriculum;
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
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GRADUATE_ATTRIBUTES_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Link_Graduate_Attribute_And_System_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_GA_And_System_Controller {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Link_Graduate_Attribute_And_System_Dao Sdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/link_graduate_attribute_system_url", method = RequestMethod.GET)
	public ModelAndView link_graduate_attribute_system_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("link_graduate_attribute_system_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		String role = session.getAttribute("role").toString();		
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
//		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("getGraduateattribute", common.getGraduateattribute(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Link_Graduate_Attribute_And_System");
	}
	
	@PostMapping(value = "/Link_Graduate_Attribute_And_System_Action")
	public ModelAndView Link_Graduate_Attribute_And_System_Action(@Validated @ModelAttribute("Link_Graduate_Attribute_System_CMD") CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_graduate_attribute_system_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String system = request.getParameter("system_id");
		String degree = request.getParameter("degree_id");
		String graduate = request.getParameter("graduate_id");
		String status = request.getParameter("status");

		if (system == null || system.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:link_graduate_attribute_system_url");
		}
		if (degree == null || degree.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:link_graduate_attribute_system_url");
		}
		if (graduate == null || graduate.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Graduate Attribute.");
			return new ModelAndView("redirect:link_graduate_attribute_system_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:link_graduate_attribute_system_url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:link_graduate_attribute_system_url");
		}	
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM where system=:system and degree=:degree and graduate_attribute=:graduate_attribute and  status=:status and created_by=:created_by and created_date=:created_date and id !=:id")
					.setParameter("system", td.getSystem())
					.setParameter("degree", td.getDegree())
					.setParameter("graduate_attribute", td.getGraduate_attribute())
					.setParameter("status", td.getStatus())
					.setParameter("created_by", td.getCreated_by())
					.setParameter("created_date", td.getCreated_date())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setSystem(Integer.parseInt(system));
				td.setDegree(Integer.parseInt(degree));
				td.setGraduate_attribute(Integer.parseInt(graduate));
				td.setStatus(Integer.parseInt(status));
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
			} else {
				td.setSystem(Integer.parseInt(system));
				td.setDegree(Integer.parseInt(degree));
				td.setGraduate_attribute(Integer.parseInt(graduate));
				td.setStatus(Integer.parseInt(status));
				td.setModified_by(username);
				td.setModified_date(date);
				
				
				if (c == 0) {
					td.setId(id);
					sessionHQL.update(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.  update ");
				}
			}

			tx.commit();

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:link_graduate_attribute_system_url");
	}
	
	@PostMapping("/getFilter_graduate_attribute_system_mapping_data")

	public @ResponseBody List<Map<String, Object>> getFilter_graduate_attribute_system_mapping_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_name,String degree_name,String graduate_attribute, String status,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Sdao.DataTableGraduatelinksystemDataList(startPage, pageLength, Search, orderColunm, orderType, system_name,degree_name,graduate_attribute,status,role);

	}

	@PostMapping("/getTotal_graduate_attribute_system_mapping_data")
	public @ResponseBody long getTotal_graduate_attribute_system_mapping_data(HttpSession sessionUserId, String Search, String system_name,String degree_name,String graduate_attribute,String status,HttpSession session) {
		String role = session.getAttribute("role").toString();
		return Sdao.DataTableGraduatelinksystemTotalCount(Search, system_name,degree_name,graduate_attribute,status,role);
	}
	
	
	@RequestMapping(value = "/Addgraduateatt", method = RequestMethod.POST)
	public @ResponseBody   String Addgraduateatt(String Addgrad_att,String Addcode,Principal principal,RedirectAttributes ra) {
		
		Date date = new Date();
		String username = principal.getName();
		String result="";
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		CC_TB_GRADUATE_ATTRIBUTES_MSTR gam = new CC_TB_GRADUATE_ATTRIBUTES_MSTR();
		
		if (Addgrad_att == null || Addgrad_att.trim().equals("")) {
			result ="Please Enter Add Graduate Attribute";
			return result;
		}
		if (validation.maxlengthcheck(Addgrad_att) == false) {
			ra.addAttribute("msg","Add Graduate Attribute "+ validation.MaxlengthcheckMSG);
			return result;
		}
		if (Addcode == null || Addcode.trim().equals("")) {
			result ="Please Enter Code";
			return result;
		}
		if (validation.maxlengthcheck5Digit(Addcode) == false) {
			ra.addAttribute("msg","Code "+ validation.MaxlengthcheckMSG5Digit);
			return result;
		}
		if (validation.isOnlyAlphabetNumeric(Addcode) == false) {
			ra.addAttribute("msg","Code "+ validation.isOnlyAlphabetNumericMSG);
			return result;
		}
		gam.setGraduate_attributes(Addgrad_att);
		gam.setCode(Addcode);
		gam.setStatus(1);
		gam.setCreated_by(username);
		gam.setCreated_date(date);
		
		int id = (int) sessionHQL.save(gam);
		sessionHQL.flush();
		sessionHQL.clear();
		
		tx.commit();
	 
		if(id>0) {
			result="1";
		}else {
			result="0";
		}

		return result;
	}
	
	
	@RequestMapping(value = "/Link_Graatt_Sys_Delete_Url", method = RequestMethod.POST)
	public ModelAndView C3_Domain_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_graduate_attribute_system_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:link_graduate_attribute_system_url");
	}

}
