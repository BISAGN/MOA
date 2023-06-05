package com.AyushEdu.controller.Institute_NCH;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.twilio.twiml.messaging.Redirect;

@Controller
//@RequestMapping(value = { "admin", "/", "user" })
public class Institute_Registration_NCH {
	
	@Autowired
	//@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Commondao Commondao;
	
	
	@GetMapping(value = "/institute_registration_nch_url")
	public ModelAndView institute_registration_nch_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra  ) {
		//SECURITY ABHISHEK
				if (request.getHeader("Referer") == null) {
					
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("institute_registration_nch_url", roleid1);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				
				String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
				String capcha =  request.getSession().getAttribute("capcha").toString();
				System.err.println(txtInput+"--"+capcha);
				 if(!txtInput.equals(capcha)){
					 ra.addAttribute("msg","Enter valid Captcha!");
				 return new ModelAndView("redirect:/institute_registration_nch_url");
				 }
				
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("institute_registration_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		 model.put("msg", msg);
		// model.put("getUniversity_nchList", common.getUniversity_nchList(sessionFactory));
		 
		 model.put("getUniversity_nchList", Commondao.getUniversityNchlist());
		 model.put("MedCountryName", common.getMedCountryName(sessionFactory));
		 model.put("MedStateName", common.getMedStateName(sessionFactory));
		 model.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("Institute_Registration_Nch_Tiles");
	
}
	
	@PostMapping(value = "/institute_registration_nch_action")
	public ModelAndView institute_registration_nch_action(
			@Validated @ModelAttribute("inst_reg_nch_CMD") EDU_LMS_INSTITUTE_REGISTRATION td, BindingResult result,
			HttpServletRequest request, ModelMap model,  RedirectAttributes ra,HttpSession session, MultipartHttpServletRequest mul
			) throws IOException, ParseException {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("institute_registration_nch_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
//		String username = principal.getName();
//		System.out.println("username "+username);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//String role = session.getAttribute("role").toString();
		//System.err.println("-----role "+role);

		try {

			String institute_name = request.getParameter("institute_name");
			String institute_email = request.getParameter("institute_email");
			String institute_mob_no = request.getParameter("institute_mob_no");
			String university_id = request.getParameter("university_id");
			String country_id = request.getParameter("country_id");
			
			String state_id = request.getParameter("state_id");
			String district_id = request.getParameter("district_id");
			String code = request.getParameter("code");
			String address = request.getParameter("address");
			String upload_image = dm(request, mul, session, "upload_image");
			MultipartFile upload_image_FILE = mul.getFile("upload_image");
		
			String status = request.getParameter("status");
			
//			System.err.println("upload_image----->   "+upload_image);

			if (institute_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Institute Name.");
				return new ModelAndView("redirect:institute_registration_nch_url");	
			}
			
			if (institute_email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Id.");
				return new ModelAndView("redirect:institute_registration_nch_url");	
			}
			if (institute_mob_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile No.");
				return new ModelAndView("redirect:institute_registration_nch_url");	
			}
			
			if (university_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select University Name");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}
			
			
			if (country_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Country Name");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}

			if (state_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State Name");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}

			if (district_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District Name");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}
			if (code.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Code");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}
			
//			if (app_status.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Approval Status");
//				return new ModelAndView("redirect:institute_registration_url");
//			}
			
			if (address.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Address");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}
			//SECURITY
			if (upload_image_FILE.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:Create_event_Url");
			}
			if (!upload_image_FILE.getOriginalFilename().isEmpty()) {
				
				if (upload_image_FILE.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:Create_event_Url");
				}
				
				
				String upload_fileEXt = FilenameUtils.getExtension(upload_image_FILE.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:institute_registration_url");
				}
				long filesize = upload_image_FILE.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg","File size should be 50 kb or less");
					return new ModelAndView("redirect:institute_registration_url");
				}
				//String file_ext_name= upload_image_FILE.getName().split(".").toString();
				if(upload_image_FILE.getName().split(".").length > 2) {
					ra.addAttribute("msg","Improper File Extention");
					return new ModelAndView("redirect:institute_registration_url");
				}
			}
			
			if (status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Enter Status");
				return new ModelAndView("redirect:institute_registration_nch_url");
			}
			
			Query q0 = sessionHQL.createQuery(
					"select count(id) from EDU_LMS_INSTITUTE_REGISTRATION where upper(institute_name)=:institute_name and country_id=:country_id "
							+ "and state_id=:state_id and district_id=:district_id and upper(code)=:code and upper(address)=:address "
							+ " and upper(status)=:status and university_id=:university_id"); //and id !=:id
			
			q0.setParameter("institute_name", institute_name.toUpperCase());
			q0.setParameter("university_id", Integer.parseInt(university_id));
			q0.setParameter("country_id", Integer.parseInt(country_id));
			q0.setParameter("state_id", Integer.parseInt(state_id));
			q0.setParameter("district_id", Integer.parseInt(district_id));
			q0.setParameter("code", code.toUpperCase());
			q0.setParameter("address", address.toUpperCase());
//			q0.setParameter("upload_image", upload_image.toUpperCase());
			q0.setParameter("status", status);
	//		q0.setParameter("id", id).uniqueResult();

//			if(role.equals("ADMIN")) {
//				td.setApp_status("1");
//			}else {
			//}
			Long c = (Long) q0.uniqueResult();
			
			if (id == 0 ) { 
				td.setApp_status("0");
				td.setCreated_by(institute_name);
				td.setUpload_image(upload_image);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
//					 for mail send
				//	SendRegMail(td.getInstitute_email());
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
				tx.commit();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return new ModelAndView("redirect:nchsignin");
	}
	
	
	
	//==========================================image view========================================== 	
	public String dm(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {
		
		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Image");
		MultipartFile file = mul.getFile(id);
		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {
		}
		return fname;

	}
	
	
	

}
