package com.AyushEdu.controller.Placement_Mgmt;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_COMPANY_REG;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Placement_Management_Company_Signup {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation;
	@Autowired
	Type_of_Degree_MstrDao  TDdao;
//	@Autowired
//	Placement_reg_DAO rpdao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	CommonController common;
	
	@RequestMapping(value = "/company_signup_Url", method = RequestMethod.GET)
	public ModelAndView company_signup_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra) {

		try {
			//SECURITY - POOJA
			
			if(request.getHeader("Referer") == null ) { 
				 ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
				
		Mmap.put("msg", msg);
		Mmap.addAttribute("langugae", "english");
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("degreelist",TDdao.getDataListofdegSysToD("ALL"));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Enterprise_signup_Tiles");
	}
	
	
	@PostMapping(value = "/placement_company_reg_Action")
	public ModelAndView placement_company_reg_Action(@Validated @ModelAttribute("placement_company_reg_CMD") EDU_PLACEMENT_COMPANY_REG td, BindingResult result,
			HttpServletRequest request, ModelMap model,  MultipartHttpServletRequest mul,MultipartFile files,
			RedirectAttributes ra) throws Exception {
		
		if(request.getHeader("Referer") == null ) { 
			 ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		 Date date = new Date();
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String company_name = request.getParameter("company_name");
			String name = request.getParameter("name");
			String email_id = request.getParameter("email_id");
			String mobile_no = request.getParameter("mobile_no");
			String ph_no = request.getParameter("ph_no");
			String address = request.getParameter("address");
			String state = request.getParameter("state");
			String per_district = request.getParameter("per_district").trim();
			String pincode = request.getParameter("pincode");
			String hours_from = request.getParameter("hours_from");
			String hours_to = request.getParameter("hours_to");
			String product = request.getParameter("product");
			String web_url = request.getParameter("web_url");
			String photo_path = request.getParameter("photo_path");
			String photo_path_pic = request.getParameter("photo_path_pic");
			String upload_img_hid = request.getParameter("upload_img_hid");
			String upload_img_pic_hid = request.getParameter("upload_img_pic_hid");
			String notified_hid = request.getParameter("notified_hid");
			
			System.err.println("check notified"+notified_hid);
			MultipartFile file2 = mul.getFile("photo_path");
			
			//SECURITY-----
			if (file2.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:company_signup_Url");
			}
			if (!file2.getOriginalFilename().isEmpty()) {

				 if (file2.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:company_signup_Url");
				}
				
				
				String upload_fileEXt = FilenameUtils.getExtension(file2.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:company_signup_Url");
				}
				long filesize = file2.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg","File size should be 50 kb or less");
					return new ModelAndView("redirect:company_signup_Url");
				}
			}
			//SECURITY-----
			
			String photo_path2="";
			if (file2 != null && !file2.getOriginalFilename().isEmpty() && file2.getOriginalFilename() != "undefined"    ) {
				photo_path2 = common.fileupload1(file2.getBytes(), file2.getOriginalFilename(),
						1, "signature" + "1");
			}
			if (upload_img_hid != null && upload_img_hid.trim().equals("")  ) {
				
				upload_img_hid=photo_path2;
				
				System.err.println("signature--------------"+photo_path2);
				
			}
			
			MultipartFile file4 = mul.getFile("photo_path_pic");
			
			//SECURITY-----
			if (file4.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:company_signup_Url");
			}
			if (!file4.getOriginalFilename().isEmpty()) {
				
				
				
				 if (file4.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:company_signup_Url");
				}
				
				String upload_fileEXt = FilenameUtils.getExtension(file4.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:company_signup_Url");
				}
				long filesize = file4.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg","File size should be 50 kb or less");
					return new ModelAndView("redirect:company_signup_Url");
				}
			}
			//SECURITY-----
			
			String photo_path_pic2="";
			if ( file4 != null && !file4.getOriginalFilename().isEmpty()) {
				photo_path_pic2 = common.fileupload1(file4.getBytes(), file4.getOriginalFilename(),
						1, "signature" + "1");
			}
			if ( upload_img_pic_hid != null &&  upload_img_pic_hid.trim().equals("")) {
				upload_img_pic_hid=photo_path_pic2;
			}
			int id = td.getId() > 0 ? td.getId() : 0;
		
//		String system_name = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_PLACEMENT_COMPANY_REG where upper(company_name)=:company_name and name=:name and photo_path=:photo_path and photo_path_pic=:photo_path_pic and  id !=:id")
					.setParameter("company_name", td.getCompany_name())
					.setParameter("name", name)
					.setParameter("photo_path", photo_path2)
					.setParameter("photo_path_pic", photo_path_pic2)
					.setParameter("id", id).uniqueResult();
			if (id == 0) {
				if (state != null && !state.equals("")) {
					td.setState(Integer.parseInt(state));
				}
			if (id == 0) {
					if (per_district != null && !per_district.equals("")) {
						td.setPer_district(Integer.parseInt(per_district));
					}
//					if (hours_from != null && !hours_from.equals("")) {
//						td.setHours_from(Integer.parseInt(hours_from));
//					}
//					if (hours_to != null && !hours_to.equals("")) {
//						td.setHours_to(Integer.parseInt(hours_to));
//					}
					if (photo_path2 != null && !photo_path2.equals("")) {
						td.setPhoto_path((photo_path2));
					}
					if (photo_path_pic2 != null && !photo_path_pic2.equals("")) {
						td.setPhoto_path_pic((photo_path_pic2));
					}
					 
				td.setCreated_by(name);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
//					td.setPhoto_path((photo_path2));
//					td.setPhoto_path_pic((photo_path_pic2));
					td.setNotified(Integer.parseInt(notified_hid));
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			}
			tx.commit();
			}
		 catch (RuntimeException e) {
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

		return new ModelAndView("redirect:company_signup_Url");
	}	
	
	@RequestMapping(value = "/getDistrictOnPlacementreg", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnPlacementreg(String selval) {
		System.out.println("selval "+selval);
		return common.district(sessionFactory, selval);
	}
	
	@RequestMapping(value = "/Email_DesignUrl", method = RequestMethod.GET)
	public ModelAndView Email_DesignUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra) {
	try {
		
//		SECURITY - POOJA
		
		if(request.getHeader("Referer") == null ) { 
			 ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Email_Design_Tiles");
	}
	
	
}
