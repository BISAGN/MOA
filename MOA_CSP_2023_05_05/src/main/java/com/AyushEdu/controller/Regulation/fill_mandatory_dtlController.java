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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_REGISTRATION_A;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.Regulation.Practitioner_RegistrationDAOIMPL;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class fill_mandatory_dtlController {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Autowired
	private Practitioner_RegistrationDAOIMPL  prdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/fill_mandatory_Url", method = RequestMethod.GET)
	 public ModelAndView fill_mandatory_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request) {
//		---------23-06-2022 Urmik changes
		
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("fill_mandatory_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String username = session.getAttribute("username").toString();
		System.err.println("username---"+username);
		
		
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("fill_mandatory_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String roleid = session.getAttribute("roleid").toString();

		      Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		      
		      Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		       Mmap.put("msg", msg);
		       Mmap.put("username", username);
			// Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		
		 return new ModelAndView("fill_mandatory_dtlTiles");
	 }
	
	@PostMapping(value = "/fill_mandatory_action")
	public ModelAndView fill_mandatory_action(@Validated @ModelAttribute("Practitioner_registration_cmd") REG_NCH_REGISTRATION_A td, BindingResult result,
			HttpServletRequest request,MultipartHttpServletRequest mul, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, @RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException  {
////		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("fill_mandatory_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
	 
		SimpleDateFormat formate= new SimpleDateFormat("dd/mm/yyyy" , Locale.ENGLISH);
	    
//		String name = request.getParameter("name").trim();
		String ayush_id = request.getParameter("ayush_id");
//		String nrh_no=request.getParameter("nrh_no");
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();

		int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
				   .setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
		
//		INT USER_ID = USERLOGINDAO.GETUSERID(AUTHENTICATION.GETNAME());
//		 
	    
		System.err.println("check data----------"+data);
		int id = td.getId() > 0 ? td.getId() : 0;
//		Date date = new Date();
//		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		

 		 try {
 		
			if (id == 0) {
				td.setCreated_by(td.getEmail_id());
				td.setCreated_date(new Date());
//				if (c == 0) {
				 td.setUser_id(data);
				   td.setAayushid(ayush_id);
 					sessionHQL.save(td);
					 sessionHQL.flush();
					sessionHQL.clear();
					 
					
//				} else {
					//ra.addAttribute("msg", "Data already Exist.");
//				}
			}
 
			tx.commit();
			ra.addAttribute("msg", "Your Details Saved Successfully.");
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

		return new ModelAndView("redirect:commonDashboard");
	}
	
	
	

	//-----------------------------23-06-22 urmik autocomplete
	
	@RequestMapping(value = "/getaayushid_Autocomplete", method = RequestMethod.POST)
	public @ResponseBody List<String> getaayushid_Autocomplete(HttpSession sessionA,HttpServletRequest request, String getcolumnname) {
		//String userId = sessionA.getAttribute("userId_for_jnlp").toString();
	System.out.println("colum--->"+getcolumnname);
		List<String> FinalList = new ArrayList<String>();
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(getcolumnname != "" ) {
				q = session.createQuery("select s.ayush_id from EDU_LMS_STUDENT_DETAILS s where ayush_id like :ayush_id").setMaxResults(15);		
		        q.setParameter("ayush_id", "%"+getcolumnname.toUpperCase()+"%");
//		        q.setParameter("system_id",system_id);
//		        q.setParameter("course_id",course_id);
			}
	        @SuppressWarnings("unchecked")
			List<String>  list = (List<String> ) q.list();
	        System.err.println("in 22222"+list);
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
	
	
//	--------------------- 23-06-22 datafetch for autocomplte
	
	@RequestMapping(value = "/getaayushdataautocomplete", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getaayushdataautocomplete(String ayush_id) {
	 System.err.println("--------ayush_id--------"+ayush_id);

    	
	 List<ArrayList<String>> list = prdao.Getaayushid_fetch(ayush_id);	 
		return list;
	}
	

	 @RequestMapping(value = "/getStateDatabyInstitute", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getStateDatabyInstitute(String institute_name, HttpSession session) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = prdao.getstatelistFromInstitute(institute_name,userid);
			
			return list;
		}
	 
	 
	 @RequestMapping(value = "/getInstituteDatabyState", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getInstituteDatabyState(String state_name, HttpSession session) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = prdao.getInstitutelistFromState(state_name,userid);
			
			return list;
		}
	
	
}
