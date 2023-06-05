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
import com.AyushEdu.Models.LMS_Master.TB_NCH_CERTIFICATE_TYPE_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Certificate_Type_Mstr_DAO;
import com.AyushEdu.dao.LMS_Master.Registration_Type_DAO;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Certificate_Type_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Certificate_Type_Mstr_DAO ddao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	
	/*
	 * @Autowired TB_NCH_CERTIFICATE_TYPE_MSTR rdao;
	 */
	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Certificate_Type_Url", method = RequestMethod.GET)
	public ModelAndView Certificate_Type_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
		//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Certificate_Type_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Certificate_Type_Tiles");
	}
	
	//==========================================SAVE/view//Edit Certificate========================================== 	


	@PostMapping(value = "/Certificate_type_Action")
	public ModelAndView CertificateAction(@Validated @ModelAttribute("Certificate_type_CMD") TB_NCH_CERTIFICATE_TYPE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, 
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Certificate_Type_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String certi_type = request.getParameter("certi_type");
		String status = request.getParameter("status");

		if (certi_type == null || certi_type.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Certificate Type");
			return new ModelAndView("redirect:Certificate_Type_Url");
		}
	
		if (validation.maxlengthcheck100(certi_type) == false) {
			ra.addAttribute("msg","Certificate Type "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Certificate_Type_Url");
			
		}
		
		if (validation.isOnlyAlphabetDASH(certi_type) == false) {
			ra.addAttribute("msg","Certificate Type "+ validation.isOnlyAlphabetMSGDASH);
			
			return new ModelAndView("redirect:Certificate_Type_Url");
		}
		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Certificate_Type_Url");
		}

		int id =Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
//		String username = principal.getName();

		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		System.err.println("userid---->    "+userid);
		
//		String userid = session.getAttribute("userid").toString();
		System.out.println();

		
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_NCH_CERTIFICATE_TYPE_MSTR where upper(certi_type)=:certi_type and status=:status and id !=:id")
					.setParameter("certi_type", td.getCerti_type().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setCerti_type(certi_type);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} 
			else {
				td.setCerti_type(certi_type);
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = ddao.updateCertificate(td);
					if (msg == "Data Updated Successfully") {
						model.put("msg", msg);
						model.put("Nmsg", "0");
					} else {
						model.put("msg", msg);
						model.put("Nmsg", "1");
					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data already Exist");
					model.put("Nmsg", "1");
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
		
		return new ModelAndView("redirect:Certificate_Type_Url");
	}
	
	@PostMapping("/getFiltercerti_typedata")
	public @ResponseBody List<Map<String, Object>> getFilterCertificate_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String certi_type, String status) {

		return ddao.DataTableCertificateDataList(startPage, pageLength, Search, orderColunm, orderType, certi_type,status);

	}

	@PostMapping("/getTotalcerti_type_dataCount")
	public @ResponseBody long getTotalCertificate_dataCount(HttpSession sessionUserId, String Search, String certi_type) {
		return ddao.DataTableCertificateDataTotalCount1(Search, certi_type);
		
}

	@RequestMapping(value = "/Edit_Certificate_Type_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Certificate_Type_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "certi_type", required = false) String certi_type,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		
	
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_NCH_CERTIFICATE_TYPE_MSTR Professional_Details = ddao.getProfessionalByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("certi_type", certi_type);
		Mmap.put("status", status);
		Mmap.put("Professional_Details", Professional_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("Professionalinfo", ddao.getProfessionalByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Certificate_Type_Tiles");
	}
	
	@RequestMapping(value = "/Certificate_Type_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Certificate_Type_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Certificate_Type_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
//		try {
  
			int app = session.createQuery(
					"update TB_NCH_CERTIFICATE_TYPE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete"+(app > 0));
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
//		} catch (Exception e) {
//			e.printStackTrace();
//			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//			ra.addAttribute("msg", liststr.get(0));
//			
//		}
		return new ModelAndView("redirect:Certificate_Type_Url");
	}
	
}



