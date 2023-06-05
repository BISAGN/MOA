package com.AyushEdu.controller.Placement_Mgmt;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestAttribute;
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
import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_REG;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Placement_Management_Reg_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	ValidationController validation;
	
//	@Autowired
//	Placement_reg_DAO rpdao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Placement_Reg_Url", method = RequestMethod.GET)
	public ModelAndView Placement_Reg_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,RedirectAttributes ra) {

		try {
			//SECURITY - POOJA
			
			if (request.getHeader("Referer") == null) {
				ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Placement_Reg_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
				 
			
		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));


	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("placement_reg_Tiles");
	}
	
	
//	@PostMapping(value = "/placement_reg_Action")
//	public ModelAndView placement_reg_Action(@Validated @ModelAttribute("placement_reg_CMD") EDU_PLACEMENT_REG td, BindingResult result,
//			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//			RedirectAttributes ra,MultipartHttpServletRequest mul) throws Exception {
		
		
		@PostMapping(value = "/placement_reg_Action")
		public ModelAndView placement_reg_Action(
				@Validated @ModelAttribute("placement_reg_CMD") EDU_PLACEMENT_REG td,
				BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException, ParseException {

			//SECURITY - POOJA

			if (request.getHeader("Referer") == null) {
				ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Placement_Reg_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
				 
		String role = session.getAttribute("role").toString();

		
		 Date date = new Date();
		 
		 String username = principal.getName();
		 
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	     
			String first_name = request.getParameter("first_name");
			String father_name = request.getParameter("father_name");
			String photo_path = request.getParameter("photo_path");
			
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String mo_no = request.getParameter("mo_no");
			String alt_no = request.getParameter("alt_no");
			String email_id = request.getParameter("email_id").trim();
			String add_line1 = request.getParameter("add_line1");
			String curr_add = request.getParameter("current_add");
			String state = request.getParameter("state");
			String per_district = request.getParameter("per_district");
			String pincode = request.getParameter("pincode");
			String gp_title = request.getParameter("gp_title");
			String fm_name = request.getParameter("fm_name");
			String fm_email = request.getParameter("fm_email");
			String im_name = request.getParameter("im_name");
			String fi_email = request.getParameter("fi_email");
			String im_designation = request.getParameter("im_designation");
			String typeOfDegree = request.getParameter("typeOfDegree");
			String upload_img_hid = request.getParameter("upload_img_hid");
			String job_seekers_hid = request.getParameter("job_seekers_hid");
			String upload_cv_hid = request.getParameter("upload_cv_hid");
			String intership_dur = request.getParameter("intership_dur");
			String intern_hours_to = request.getParameter("intern_hours_to");
			String upload_cv = request.getParameter("upload_cv");
			
			MultipartFile file2 = mul.getFile("photo_path");
			
			//SECURITY-----
			if (file2.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:Create_event_Url");
			}
			if (!file2.getOriginalFilename().isEmpty()) {
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
			if (!file2.getOriginalFilename().isEmpty()) {
				photo_path2 = common.fileupload1(file2.getBytes(), file2.getOriginalFilename(),
						1, "signature" + "1");
				
				System.err.println("signature--------------"+photo_path2);
			}
			if (upload_img_hid.trim().equals("")) {
				
				upload_img_hid=photo_path2;
				
				System.err.println("signature--------------"+photo_path2);
				
			}
			
//			===============FOR FILE
			MultipartFile file28 = mul.getFile("photo_path");
			
			//SECURITY-----
			if (file28.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:Create_event_Url");
			}
			if (!file28.getOriginalFilename().isEmpty()) {
				String upload_fileEXt = FilenameUtils.getExtension(file28.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:company_signup_Url");
				}
				long filesize = file28.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg","File size should be 50 kb or less");
					return new ModelAndView("redirect:company_signup_Url");
				}
			}
			//SECURITY-----
			
			String photo_file420="";
			if (!file28.getOriginalFilename().isEmpty()) {
				photo_file420 = common.fileupload1(file28.getBytes(),file28.getOriginalFilename(),
						1, "signature" + "1");
				
				System.err.println("upload_cv--------------"+photo_file420);
			}
			if (upload_cv_hid.trim().equals("")) {
				
				upload_cv_hid=photo_file420;
				
				System.err.println("upload_cv--------------"+photo_file420);
				
			}
			
			int id = td.getId() > 0 ? td.getId() : 0;
		
		
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date dob3 = null;
			dob3 = format.parse(dob);
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_PLACEMENT_REG where upper(first_name)=:first_name and father_name=:father_name and photo_path=:photo_path and upload_cv=:upload_cv and id !=:id")
					.setParameter("first_name", td.getFirst_name())
					.setParameter("father_name", father_name)
					.setParameter("photo_path", photo_path2)
					.setParameter("upload_cv", photo_file420)
					.setParameter("id", id).uniqueResult();
			if (id == 0) {
				if (state != null && !state.equals("")) {
					td.setState(Integer.parseInt(state));
				}
				
			if (id == 0) {
					if (per_district != null && !per_district.equals("")) {
						td.setPer_district(Integer.parseInt(per_district));
					}
					
					if (photo_path2 != null && !photo_path2.equals("")) {
						td.setPhoto_path((photo_path2));
					}
					
					if (intership_dur != null && !intership_dur.equals("")) {
						td.setIntership_dur((Integer.parseInt(intership_dur)));
					}
	 
					if (photo_file420 != null && !photo_file420.equals("")) {
						td.setUpload_cv((photo_file420));
					}
					
				td.setCreated_by(username);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
//					td.setUpload_cv(upload_cv_hid);
					td.setIntern_hours_to(intern_hours_to);
					td.setJob_seekers(Integer.parseInt(job_seekers_hid));
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

		return new ModelAndView("redirect:Placement_Reg_Url");
	}	
	
	@RequestMapping(value = "/getDistrictOnstate_placement", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstate_placement(String selval) {
		return common.district(sessionFactory, selval);
	}
	
//	@RequestMapping(value = "/getDegreedetails", method = RequestMethod.POST)
//	public @ResponseBody List<Map<String, Object>> getDegreeName(String typeofdegree) {
//		List<Map<String, Object>> list = rpdao.getdegreedetailss(typeofdegree);
//		return list;
//	}
	
}
