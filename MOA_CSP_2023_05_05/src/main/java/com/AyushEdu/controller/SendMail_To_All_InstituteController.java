package com.AyushEdu.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.config.DynamicMailFormatGen;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.config.MailDefine;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Institute_Registration_Dao;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;

@Controller
public class SendMail_To_All_InstituteController {

	@Autowired
	RoleBaseMenuDAO roleBaseDAO;

	@Autowired
	CommonController common;

	@Autowired
	Type_of_Degree_MstrDao TDdao;
	
	@Autowired
	Institute_Registration_Dao IRdao;
	
	
	
	@Autowired
	EmailConfig emailsend;
	
	@GetMapping(value = "/SendMail_To_All_Institute_Url")
	public ModelAndView SendMail_To_All_Institute_Url(Model model, @Valid @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		
		// session.invalidate();
//	 if(error != null){
//			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//		}
		// SECURITY V2
//		model.addAttribute("msg", msg);
//		model.addAttribute("marque", TDdao.getMarqueData("Ayush Portal"));
//		model.addAttribute("langugae", "english");
//		model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("ALL"));
//		model.addAttribute("visiter_count", roleBaseDAO.VisitorCounter());
		
		
	return new ModelAndView("SendMail_To_All_Institute_Tiles");
	
	}
	
	@RequestMapping(value = "/checkEmailInstitute", method = RequestMethod.GET)
	public @ResponseBody String checkEmailInstitute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) throws ParseException {
		
		
			List<Map<String, Object>> details = IRdao.getInstitueDetailsforEmail();
			System.err.println("====HET======"+details+"]]]]]]]]]]]]"+details.size()+ "]]]]]]]]]]]"+details.get(0).get("system_id"));
			
			for(int i = 0 ; i < details.size(); i++) {
				
				
				if(details.get(i).get("system_id").toString().equals("44") || details.get(i).get("system_id").toString().equals("46") || details.get(i).get("system_id").toString().equals("47") || details.get(i).get("system_id").toString().equals("48"))
				{
					DynamicMailFormatGen DB = new DynamicMailFormatGen();
					System.err.println("====HET======"+details.get(i).get("email_id").toString());

					MailDefine ml = new MailDefine();
					DB.setEmail(details.get(i).get("email_id").toString());
					ml.setRegistration_header_NCISM_institute(details.get(i).get("email_id").toString(),
							"Bisag@123");
					DB.setMainBody(ml.getRegistration_header_NCISM_institute());
					DB.setFooter(ml.getRegistration_body_NCISM_institute());
					DB.setRole("NCISM");
					DB.setName(details.get(i).get("institute_name").toString());
					DB.setSubject("Registration Successfull");
//					emailsend.SendMailNew(request, DB);
				}
				if(details.get(i).get("system_id").toString().equals("45")) {
					DynamicMailFormatGen DB = new DynamicMailFormatGen();
					System.err.println("====HET======"+details.get(i).get("email_id").toString());

					MailDefine ml = new MailDefine();
					DB.setEmail(details.get(i).get("email_id").toString());
					ml.setRegistration_header_NCH_institute(details.get(i).get("email_id").toString(),
							"Bisag@123");
					DB.setMainBody(ml.getRegistration_header_NCH_institute());
					DB.setFooter(ml.getRegistration_body_NCH_institute());
					DB.setRole("NCH");
					DB.setName(details.get(i).get("institute_name").toString());
					DB.setSubject("Registration Successfull");
//					emailsend.SendMailNew(request, DB);
				}
			}
			
			

		return "okay";
	}
	
	@RequestMapping(value = "/checkEmailStudent", method = RequestMethod.GET)
	public @ResponseBody String checkEmailStudent(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) throws ParseException {
		
		
			List<Map<String, Object>> details = IRdao.getStudentDetailsforEmail();
			System.err.println("====HET======"+details+"]]]]]]]]]]]]"+details.size()+ "]]]]]]]]]]]"+details.get(0).get("system_id"));
			
			for(int i = 0 ; i < details.size(); i++) {
//				
//				DynamicMailFormatGen DB = new DynamicMailFormatGen();
////				System.err.println("====HET======"+details.get(i).get("email_id").toString());
//
//				MailDefine ml = new MailDefine();
//				DB.setEmail("meetsuthar08@gmail.com");
//				ml.setRegistration_header_NCISM_student("Dr.V.H. Dave Homoeopathic Medical College and Smt. S.I. Patel Ipcowala Homoeopathic Hospital","meetsuthar08+"+i+"@gmail.com");
//				DB.setMainBody(ml.getRegistration_header_NCISM_student());
//				DB.setFooter(ml.getRegistration_body_NCISM_student());
//				DB.setRole("NCISM");
//				DB.setName("BISAG");
//				DB.setSubject("Registration Successfull");
//			emailsend.SendMailNew(request, DB);
//
//			}
//			
//			
//			
//for(int i = 0 ; i < 1; i++) {
//				
//				DynamicMailFormatGen DB = new DynamicMailFormatGen();
////				System.err.println("====HET======"+details.get(i).get("email_id").toString());
//
//				MailDefine ml = new MailDefine();
//				DB.setEmail("meetsuthar08@gmail.com");
//				ml.setRegistration_header_NCISM_institute("Dr.V.H. Dave Homoeopathic Medical College and Smt. S.I. Patel Ipcowala Homoeopathic Hospital","Bisag@123");
//				DB.setMainBody(ml.getRegistration_header_NCISM_institute());
//				DB.setFooter(ml.getRegistration_body_NCISM_institute());
//				DB.setRole("NCISM");
//				DB.setName("BISAG");
//				DB.setSubject("Registration Successfull");
//			emailsend.SendMailNew(request, DB);
//
//			}
			
				
				if(details.get(i).get("system_id").toString().equals("44") || details.get(i).get("system_id").toString().equals("46") || details.get(i).get("system_id").toString().equals("47") || details.get(i).get("system_id").toString().equals("48"))
				{
					DynamicMailFormatGen DB = new DynamicMailFormatGen();
					System.err.println("====HET======"+details.get(i).get("email").toString());

					MailDefine ml = new MailDefine();
					DB.setEmail(details.get(i).get("email").toString());
					ml.setRegistration_header_NCISM_student(details.get(i).get("institute_name").toString(),details.get(i).get("email").toString());
					DB.setMainBody(ml.getRegistration_header_NCISM_institute());
					DB.setFooter(ml.getRegistration_body_NCISM_institute());
					DB.setRole("NCISM");
					DB.setName(details.get(i).get("name").toString());
					DB.setSubject("Registration Successfull");
//					emailsend.SendMailNew(request, DB);
				}
				if(details.get(i).get("system_id").toString().equals("45")) {
					DynamicMailFormatGen DB = new DynamicMailFormatGen();
					System.err.println("====HET======"+details.get(i).get("email").toString());

					MailDefine ml = new MailDefine();
					DB.setEmail(details.get(i).get("email").toString());
					ml.setRegistration_header_NCH_student(details.get(i).get("institute_name").toString(),details.get(i).get("email").toString());
					DB.setMainBody(ml.getRegistration_header_NCH_institute());
					DB.setFooter(ml.getRegistration_body_NCH_institute());
					DB.setRole("NCH");
					DB.setName(details.get(i).get("name").toString());
					DB.setSubject("Registration Successfull");
//					emailsend.SendMailNew(request, DB);
				}
			}
			
			

		return "okay";
	}
	
}
 
	
	

