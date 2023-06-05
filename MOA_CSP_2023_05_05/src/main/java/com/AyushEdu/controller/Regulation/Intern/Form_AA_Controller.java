package com.AyushEdu.controller.Regulation.Intern;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_LDAP_SCREEN_MASTER;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_AA;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.CountryDao;
import com.AyushEdu.dao.Regulation.EduRegDao;
import com.AyushEdu.dao.Regulation.Intern.FormaaDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Form_AA_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	EduRegDao Rdao;
	

	@Autowired
	FormaaDao formaa_dao;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/form_aa_Url", method = RequestMethod.GET)
	public ModelAndView form_aa_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		//SECURITY - POOJA
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("form_aa_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		 		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		Mmap.put("username", username);
		
		try {
			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
 			Mmap.put("userId", data);
			
 			int reg_state = (int) sessionHQ.createQuery("select reg_state from REG_NCH_FORM_A_P where user_id=:data")
					.setParameter("data", data).setMaxResults(1).uniqueResult();
 			Mmap.put("reg_state", reg_state);
 			Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
			
 			
 			int pay_status = (int) sessionHQ.createQuery("select pay_status from REG_NCH_FORM_AA where user_id=:data")
					.setParameter("data", data).setMaxResults(1).uniqueResult();
 			Mmap.put("pay_status", pay_status);
 			
 			
 			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		 
		return new ModelAndView("form_aa_Tiles");
	}

	 
	
	 @RequestMapping(value = "/form_aaAction",method=RequestMethod.POST)
		public ModelAndView form_aaAction(@ModelAttribute("form_aaCMD") REG_NCH_FORM_AA com,
				HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra, @RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException  {
			
		//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("form_aa_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 	 
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				String reg_state = request.getParameter("reg_state").trim();
				String to_state = request.getParameter("to_state");
				String address = request.getParameter("address");
				String user_id = request.getParameter("userId");
				
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

				
				String from_date = request.getParameter("from_date");
				String to_date = request.getParameter("to_date");
				
				  Date f_dt = null;
				  Date t_dt = null;
				if (!from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
						f_dt = format.parse(from_date);
				  }
				if (!to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY")) {
					t_dt = format.parse(to_date);
				}
				
				
				
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
//				 	if (name.equals("") || name.equals("null")|| name.equals(null)) {
//				 		model.put("msg", "Please Enter Country.");
//				 		return new ModelAndView("redirect:Country");
//					}
				 	if (reg_state == null || reg_state.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Registration State.");
						return new ModelAndView("redirect:form_aa_Url");
					}
				 	if (to_state == null || to_state.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select To State.");
						return new ModelAndView("redirect:form_aa_Url");
					}
					if (address == "2" || address.trim().equals("2")) {
						ra.addAttribute("msg", "Enter Address.");
						return new ModelAndView("redirect:form_aa_Url");
					}
				 
				try{
					
//					Query q0 = sessionHQL.createQuery("select count(id) from TB_COUNTRY where name=:name and status=:status and id !=:id");
//					q0.setParameter("name", com.getName());  
//					q0.setParameter("status", com.getStatus());
//					q0.setParameter("id", id);  
//					Long c = (Long) q0.uniqueResult();
			 
					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_date(date);
						com.setReg_state(Integer.parseInt(reg_state));
						com.setTo_state(Integer.parseInt(to_state));
						com.setAddress(address);
						com.setFrom_date(f_dt);
						com.setTo_date(t_dt);
						com.setUser_id(Integer.parseInt(user_id));
						
						com.setCreated_date(new Date());
						com.setCreated_by(username);
						//if (c == 0) {
							sessionHQL.save(com);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

//						} else {
//							ra.addAttribute("msg", "Data already Exist.");
//						}
					}
				
					tx.commit();
					
				}catch(RuntimeException e){
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:form_aa_Url");
			}



	 @PostMapping("/getFilterformaa_data")
		public @ResponseBody List<Map<String, Object>> getFilterformaa_data(int startPage, int pageLength,String Search, 
				String orderColunm, String orderType, String reg_state, String to_state, String address , String from_date, String to_date) {
		     
			return formaa_dao.DataTableFormaaDataList(startPage, pageLength, Search, orderColunm, orderType,  reg_state,  to_state,  address , from_date, to_date);

		}
		
//		@RequestMapping(value = "/getFiltercountry_data", method = RequestMethod.POST)
//		public @ResponseBody ArrayList<ArrayList<String>> getFiltercountry_data(int startPage, int pageLength, String Search,
//				String orderColunm, String orderType,String country,String status,HttpSession sessionUserId, Principal principal) {
//			
//			return Country_dao.DataTableCountryDataList(startPage, pageLength, Search,orderColunm, orderType,country,status,sessionUserId);
//
//		}

		@RequestMapping(value = "/getTotalform_aa_dataCount", method = RequestMethod.POST)
		public @ResponseBody long getTotalform_aa_dataCount(HttpSession sessionUserId, String Search, String reg_state, String to_state, String address , String from_date, String to_date) {
			return formaa_dao.DataTableFormaaDataTotalCount(Search,sessionUserId, reg_state,  to_state,  address , from_date, to_date);
		}
		
		@RequestMapping(value = "formaapaymentStatusUrl", method = RequestMethod.POST)
		public ModelAndView formaapaymentStatusUrl(@ModelAttribute("a") int user_id, 
				@ModelAttribute("status") String pay_status, HttpSession session,
				ModelMap model, REG_NCH_FORM_AA obj,HttpServletRequest request) {
			
//			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 
			List<String> liststr = new ArrayList<String>();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
	 		Date date = new Date();
	 		String username = session.getAttribute("username").toString();
			String hqlUpdate2 = "update from REG_NCH_FORM_AA set pay_status='1',modified_by=:modified_by,modified_date=:modified_date"
					+ " where user_id=:user_id  ";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setParameter("user_id", user_id)
					.setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.executeUpdate();

			 
		 

			tx.commit();
			sessionHQL.close();
			if (app2 > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Not Approved.");
			}
			model.put("msg", liststr.get(0));

			return new ModelAndView("form_aa_Tiles");
		}
	 
}
