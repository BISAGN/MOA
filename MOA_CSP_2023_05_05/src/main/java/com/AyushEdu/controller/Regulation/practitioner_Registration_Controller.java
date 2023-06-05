package com.AyushEdu.controller.Regulation;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Regulation.REG_NCH_REGISTRATION_A;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Practitioner_RegistrationDAO;
import com.AyushEdu.dao.Regulation.Practitioner_RegistrationDAO;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class practitioner_Registration_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	 
	@Autowired
	private Practitioner_RegistrationDAO  prdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	CommonController common;
	
	
//	@GetMapping(value = "/Practitioner_registrationUrl")
//	public ModelAndView Practitioner_registrationUrl(ModelMap model,HttpSession session,
//			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
//		
////		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
////			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
////			 return new ModelAndView("redirect:/login");
////		 }
////		String roleid1 = session.getAttribute("roleid").toString();
////		 Boolean val = roledao.ScreenRedirect("institute_registration_url", roleid1);		
////			if(val == false) {
////				return new ModelAndView("AccessTiles");
////		}
//		 model.addAttribute("msg", msg);
////		 model.put("MedCountryName", common.getMedCountryName(sessionFactory));
//	 model.put("getInstituteList", common.getInstituteList(sessionFactory));
//	 model.put("getInstituteList", common.getInstituteList(sessionFactory));
//	 model.put("MedStateName", common.getMedStateName(sessionFactory));
////		 model.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
////		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
////		 
//		return new ModelAndView("Practisioner_RegistrationTiles");
//	
//}

	
	@PostMapping(value = "/Practitioner_registration_action")
	public ModelAndView Practitioner_registration_action(@Validated @ModelAttribute("Practitioner_registration_cmd") REG_NCH_REGISTRATION_A td, BindingResult result,
			HttpServletRequest request,MultipartHttpServletRequest mul, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException  {
		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Practitioner_registrationUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
 
System.err.println("IN SAVE ACTION URMIK");
		SimpleDateFormat formate= new SimpleDateFormat("dd/mm/yyyy" , Locale.ENGLISH);
	    
		String name = request.getParameter("name").trim();
		String ayush_id = request.getParameter("ayush_id");
		String nrh_no=request.getParameter("nrh_no");
		 
		if (name == null || name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Your Name");
			return new ModelAndView("redirect:signup");
		}
	    
		System.err.println("check aayush id-----------"+ayush_id);
		int id = td.getId() > 0 ? td.getId() : 0;
//		Date date = new Date();
//		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		

		 try {
//			Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  TB_EDU_REGULATION where upper(nrh_en_no)=:nrh_en_no and upper(first_name)=:first_name  and id !=:id")
//					.setParameter("nrh_en_no", td.getNrh_en_no().toUpperCase())
//					.setParameter("first_name", td.getFirst_name().toUpperCase()).setParameter("father_name", td.getFather_name().toUpperCase()).setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setCreated_by(td.getEmail_id());
				td.setCreated_date(new Date());
//				if (c == 0) {
				td.setStatus(0);
				td.setAayushid(ayush_id);
				//td.setNrh_no(nrh_no);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Registration Sent Successfully.");
//				} else {
					//ra.addAttribute("msg", "Data already Exist.");
//				}
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

		return new ModelAndView("redirect:landingpage");
	}
	
	
//	@RequestMapping(value = "/getaayushid_Autocomplete", method = RequestMethod.POST)
//	public @ResponseBody List<String> getaayushid_Autocomplete(HttpSession sessionA,HttpServletRequest request, String getcolumnname) {
//		//String userId = sessionA.getAttribute("userId_for_jnlp").toString();
//	System.out.println("colum--->"+getcolumnname);
//		List<String> FinalList = new ArrayList<String>();
//		try {
//			Session session = this.sessionFactory.openSession();
//			Transaction tx = session.beginTransaction();
//			Query q = null;
//			if(getcolumnname != "" ) {
//				q = session.createQuery("select s.ayush_id from EDU_LMS_STUDENT_DETAILS s where ayush_id like :ayush_id").setMaxResults(15);		
//		        q.setParameter("ayush_id", "%"+getcolumnname.toUpperCase()+"%");
////		        q.setParameter("system_id",system_id);
////		        q.setParameter("course_id",course_id);
//			}
//	        @SuppressWarnings("unchecked")
//			List<String>  list = (List<String> ) q.list();
//	        System.err.println("in 22222"+list);
//			tx.commit();
//			session.close();
//			String enckey = common.getAlphaNumericString();
//			Cipher c = null;
//			
//			c = common.EncryptionSHA256Algo(sessionA, enckey);
//			
//			for (int i = 0; i < list.size(); i++) {
//				byte[] encCode = null;
//				try {
//					encCode = c.doFinal(list.get(i).getBytes());
//				} catch (IllegalBlockSizeException | BadPaddingException e) {
//					e.printStackTrace();
//				}
//				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
//				FinalList.add(base64EncodedEncryptedCode);
//			}
//			FinalList.add(enckey + "4bsjyg==");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return FinalList;
//	}
//	
	
//	---------------------datafetch for autocomplte
	
//	@RequestMapping(value = "/getaayushdataautocomplete", method = RequestMethod.POST)
//	public @ResponseBody List<ArrayList<String>> getaayushdataautocomplete(String ayush_id) {
//	 System.err.println("--------ayush_id--------"+ayush_id);
//
//    	
//	 List<ArrayList<String>> list = prdao.Getaayushid_fetch(ayush_id);	 
//		return list;
//	}
	
	
//	-----------------nrh_no_data_fetch
	

	@RequestMapping(value = "/nrh_no_autocomplete_data_fetchaaa", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> nrh_no_autocomplete_data_fetchaaa(String nrh_no) {
		System.err.println("--------check nrh_no----contooooo----"+nrh_no);
	 List<ArrayList<String>> list = prdao.Getnrhno_fetch(nrh_no);	 
		return list;
	}
	
//	-----end
//	----------------------------------nrh_no autocomplete
	
	
	@RequestMapping(value = "/getnrhid_Autocomplete", method = RequestMethod.POST)
	public @ResponseBody List<String> getnrhid_Autocomplete(HttpSession sessionA,HttpServletRequest request, String getcolumnname) {
		//String userId = sessionA.getAttribute("userId_for_jnlp").toString();
	System.out.println("colum--->"+getcolumnname);
	 
		List<String> FinalList = new ArrayList<String>();
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(getcolumnname != "" ) {
				q = session.createQuery("select r.nrh_en_no from TB_EDU_REGULATION r where status=1 and nrh_en_no like :nrh_en_no").setMaxResults(15);	
		        q.setParameter("nrh_en_no", "%"+getcolumnname.toUpperCase()+"%");
		        System.err.println("check query"+q);
//		        q.setParameter("system_id",system_id);
//		        q.setParameter("course_id",course_id);
			}
	        @SuppressWarnings("unchecked")
			List<String>  list = (List<String> ) q.list();
	     //   System.err.println("in nrhhhhh nooo"+list);
			tx.commit();
			session.close();
			String enckey = common.getAlphaNumericString();
			Cipher c = null;
			
			c = common.EncryptionSHA256Algo(sessionA, enckey);
			
			for (int i = 0; i < list.size(); i++) {
				byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FinalList;
	}

	 
	
	@RequestMapping(value = "/ayus_abha_nrh_data_fetch", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> ayus_abha_nrh_data_fetch(String ayus) {
	 List<ArrayList<String>> list = prdao.Getayus_abha_arh_data_fetch(ayus);	 
		return list;
	}
	
	
	//===urmik
	
	@RequestMapping(value = "/newdatavalidfetch", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> newdatavalidfetch(String newusername) {
	 List<ArrayList<String>> list = prdao.Getnewdatavalidfetch(newusername);	 
		return list;
	}
	
	
}
