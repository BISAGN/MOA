package com.AyushEdu.controller.Part_one;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_POLICE_STATION_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_REMARKS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Infrastructure_Report_Controller {
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	// ----------------search-------------------------
	 
		 @GetMapping(value = "/Search_College_Infrastructure_url")
			public ModelAndView Search_College_Infrastructure_url(ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

				String userid = session.getAttribute("userId_for_jnlp").toString();
				String role = session.getAttribute("role").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				
				model.put("msg", msg);
				return new ModelAndView("Search_College_InfrastructureTiles");
			}
		 
			@PostMapping("/getFilterSearch_College_Infrastructure_data")
			public @ResponseBody List<Map<String, Object>> getFilterSearch_College_Infrastructure_data(HttpSession sessionUserId, int startPage,
					int pageLength, String Search, String orderColunm, String orderType, String council_check, String website_update_date,
					String username, String biometric_status, String cctv_status) {
				
				String role = sessionUserId.getAttribute("role").toString();
				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				return CIRDao.DataTableSearch_College_InfrastructureDataList(startPage, pageLength, Search, orderColunm, orderType, council_check, website_update_date,
						username, biometric_status, cctv_status, role, userid,institute_id);

			}
			
			@PostMapping("/getTotalSearch_College_Infrastructure_dataCount")
			public @ResponseBody long getTotalSearch_College_Infrastructure_dataCount(HttpSession sessionUserId, String Search, String council_check,
					String website_update_date, String username, String biometric_status, String cctv_status) {
				String role = sessionUserId.getAttribute("role").toString();
				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				return CIRDao.DataTableSearch_College_InfrastructureDataTotalCount(Search, council_check, website_update_date,
						username, biometric_status, cctv_status, role, userid,institute_id);
			}
			
	//////////////////View_url

	@RequestMapping(value = "/View_Search_College_InfrastructureUrl", method = RequestMethod.POST)
	public ModelAndView View_Search_College_InfrastructureUrl(@RequestParam(value = "college_infra_id", required = false) String id,
			ModelMap Mmap,
	HttpSession session, @RequestParam(value = "msg", required = false) String msg,
	HttpServletRequest request) {
System.out.println("college_infra_id  "+id);
	Session sessionHQL = this.sessionFactory.openSession();
//	Mmap.put("id6", id);
	Mmap.put("main_view_id", id);
	String userid = session.getAttribute("userId_for_jnlp").toString();
	System.err.println("view main id---------------"+id);
	
//	String inst_from_main_id = sibdao.getInstitute_id_from_main_id(Integer.parseInt(id)).get(0).get("inst_id").toString();
	
	System.err.println("22/2/23----------------id------>" + id);
	String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
	String role = session.getAttribute("role").toString();
	List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
			Integer.parseInt(id), role, userid,"clg_reg_infra_college_council","institute_id");
			
			System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
			String p_id = getPidfromInstidReport.get(0).get("id").toString();
			Mmap.put("getPidfromInstidReport", p_id); 

	System.err.println("22/2/23----------------id------>" + id);

	CLG_REG_INFRA_COLLEGE_COUNCIL viewid = (CLG_REG_INFRA_COLLEGE_COUNCIL) sessionHQL
		.get(CLG_REG_INFRA_COLLEGE_COUNCIL.class, Integer.parseInt(p_id));

	//CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS viewchid = (CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS) sessionHQL
//		.get(CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS.class, Integer.parseInt(id));

	//CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY viewchid2 = (CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY) sessionHQL.get(CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY.class,
	//Integer.parseInt(id));


	Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
	//Mmap.put("getMedInstituteName", common.getMedInstituteName(sessionFactory));
	Mmap.put("View_College_InfrastractureCMD", viewid);
	//Mmap.put("View_Search_Basic_InfochCMD", viewchid);

	List<Map<String, Object>> View_Search_College_Infrastracturech2 = CIRDao.getClg_central_lib_infoReport(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> View_Search_College_Infrastracturech3 = CIRDao.getDepart_dtlReport(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> View_Search_College_Infrastracturech4 = CIRDao.getClg_central_lib_childinfoReport(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> getClg_camera_locationinfoReport = CIRDao.getClg_camera_locationinfoReport(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	//List<Map<String, Object>> View_Search_Basic_Infoch3 = CIDao
//		.getAllinfo_police_st(Integer.parseInt(institute_id));
	//List<Map<String, Object>> getInstname = CIDao.getInstname(Integer.parseInt(institute_id));
	Mmap.put("View_Search_College_Infrastracturech2", View_Search_College_Infrastracturech2);
	Mmap.put("View_Search_College_Infrastracturech3", View_Search_College_Infrastracturech3);
	Mmap.put("View_Search_College_Infrastracturech4", View_Search_College_Infrastracturech4);
	Mmap.put("getClg_camera_locationinfoReport", getClg_camera_locationinfoReport);
	//Mmap.put("View_Search_Basic_Infoch3", View_Search_Basic_Infoch3);
	//Mmap.put("getInstname", getInstname);

	//Mmap.put("getgenderList", common.getgenderList(sessionFactory));
	//Mmap.put("getDocList", common.getDocList(sessionFactory));
	//Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
	//Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
	//Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
	//Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
	//Mmap.put("getregView", tdao.getregistrationViewdata());
	//Mmap.put("data", tdao.getAllPersdetails(Integer.parseInt(userid)));
	
	Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
	Mmap.put("inst_id", id);
	
//	Mmap.put("cidocdata", CIRDao.getClg_Infra_UploadDocumentsFetch(id));

	Mmap.addAttribute("msg", msg);
	return new ModelAndView("college_infrastructure_view");
	}
	
	
	
	
	//-------------------------------------------------------Police Station Details-----------------------------------	 
	 
	 
	 
	 
	 @PostMapping(value = "/Per_Reg_Form_A_Report_Remarks")
	 public @ResponseBody Map<String, String> Per_Reg_Form_A_Report_Remarks(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra) throws IOException, ParseException {
	 
	//System.err.println("------parent------"+pernt_id);
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			
				
	
			String reemark = request.getParameter("clg_infra_clg_coun_web_dtl_rmk");
			String hid_remarks = request.getParameter("clg_infra_remarks");
			
			
			
							

				
				if(Integer.parseInt(hid_remarks)==0) {
					
					CLG_REG_REMARKS rd =new CLG_REG_REMARKS();
					
					rd.setClg_infra_clg_coun_web_dtl_rmk(reemark);
 					
	

//					rd.setName_of_nearest_police_station(nearest_police);
//					rd.setAddr_line1(police_per_add_line1);
//					rd.setAddr_line2(police_per_add_line2);
//					rd.setState(Integer.parseInt(police_per_state));
//					rd.setCity(police_city);
//					rd.setPincode(Integer.parseInt(police_per_pincode));
//					rd.setPhone_number(BigInteger.valueOf(Long.parseLong(police_phone_no)));
//					rd.setPolice_std_code(Integer.parseInt(police_std));
					
					
		
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
				
			int	h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
//				else {
//				
//					CLG_REG_INST_INFO_POLICE_STATION_DETAILS urd = (CLG_REG_INST_INFO_POLICE_STATION_DETAILS) sessionHQL.get(CLG_REG_INST_INFO_POLICE_STATION_DETAILS.class,
//						(h_id));
//					
//					
//					urd.setName_of_nearest_police_station(nearest_police);
//					urd.setAddr_line1(police_per_add_line1);
//					urd.setAddr_line2(police_per_add_line2);
//					urd.setState(Integer.parseInt(police_per_state));
//					urd.setCity(police_city);
//					urd.setPincode(Integer.parseInt(police_per_pincode));
//					urd.setPhone_number(BigInteger.valueOf(Long.parseLong(police_phone_no)));
//					urd.setPolice_std_code(Integer.parseInt(police_std));
//				
//				
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				String institude = String.valueOf(ea.getInstitute_id());
//				urd.setInst_id(Integer.parseInt(institude));
//				
//				urd.setModified_by((userid));
//				urd.setModified_date(date);
//
//				sessionHQL.update(urd);
//				 data.put("msg", "Draft Update Successfully");
//				sessionHQL.flush();
//				sessionHQL.clear();
//			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	 
//	//SAVE COLLEGE COUNCIL DETAILS
//	@PostMapping(value = "/College_Infrastructure_Remark_Save")
//	public @ResponseBody String College_Infrastructure_Remark_Save( 
//			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//			RedirectAttributes ra) throws ParseException, IOException {
//
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return  "redirect:/login";
//		 }
//		String role = session.getAttribute("role").toString();
//		String roleid1 = session.getAttribute("roleid").toString();
////			 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
////				if(val == false) {
////					return  "AccessTiles";
////			}
//		
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		
//		String clg_infra_clg_coun_web_dtl_rmk = request.getParameter("clg_infra_clg_coun_web_dtl_rmk");
//		String hid_college_infrastructure_remark = request.getParameter("hid_college_infrastructure_remark");
//		
//		
//		Date date = new Date();
//		String username = principal.getName();
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//
//		try {
//			CLG_REG_REMARKS college_infra_remark =new CLG_REG_REMARKS();
//			
//			college_council_detail.setCollege_website(Integer.parseInt(college_website));
//			college_council_detail.setWebsite_update_date(datePickerFormat.parse(website_update_date));
//			college_council_detail.setCctv_status(Integer.parseInt(cctv_status));
//			college_council_detail.setLogin_url(login_url);
//			college_council_detail.setUsername(username_college);
//			college_council_detail.setPassword(password);
//			college_council_detail.setBiometric_status(Integer.parseInt(biometric_status));
//			college_council_detail.setCollege_working_hours(college_working_hours);
//			college_council_detail.setInstitute_id(Integer.parseInt(institute_id));
//			college_council_detail.setS_id(Integer.parseInt(s_id));
//			college_council_detail.setCreated_by(Integer.parseInt(userid));
//			college_council_detail.setCreated_date(date);
//			
//				if (Integer.parseInt(hid_college_council) == 0) {
//					int hid_college_council1= (Integer) sessionHQL.save(college_council_detail);
//					System.err.println("hid_college_council1----------------"+hid_college_council1);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					tx.commit();
//					return String.valueOf(hid_college_council1) ;
//			}
//				else {
//
//					CLG_REG_INFRA_COLLEGE_COUNCIL college_council_detail_u = (CLG_REG_INFRA_COLLEGE_COUNCIL) sessionHQL
//							.get(CLG_REG_INFRA_COLLEGE_COUNCIL.class, Integer.parseInt(hid_college_council));
//					
//					college_council_detail_u.setCouncil_check(Integer.parseInt(council_check));
//					if(!council_document.equals("")) {
//					college_council_detail_u.setCouncil_document(council_document);
//					}
//					college_council_detail_u.setCollege_website(Integer.parseInt(college_website));
//					college_council_detail_u.setWebsite_update_date(datePickerFormat.parse(website_update_date));
//					college_council_detail_u.setCctv_status(Integer.parseInt(cctv_status));
//					college_council_detail_u.setLogin_url(login_url);
//					college_council_detail_u.setUsername(username_college);
//					college_council_detail_u.setPassword(password);
//					college_council_detail_u.setBiometric_status(Integer.parseInt(biometric_status));
//					college_council_detail_u.setCollege_working_hours(college_working_hours);
//					college_council_detail_u.setInstitute_id(Integer.parseInt(institute_id));
//					college_council_detail_u.setS_id(Integer.parseInt(s_id));
//					college_council_detail_u.setModified_by(Integer.parseInt(userid));
//					college_council_detail_u.setModified_date(date);
//					college_council_detail_u.setId(Integer.parseInt(hid_college_council));
//					sessionHQL.update(college_council_detail_u);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					tx.commit();
//					
//				}
//
//		} catch (RuntimeException e) {
//			try {
//
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return  "Data Saved Successfully";
//	}
	
	
	//SAVE COLLEGE COUNCIL DETAILS
		@PostMapping(value = "/College_Infrastructure_Remark_Save")
		public @ResponseBody String College_Infrastructure_Remark_Save( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) throws ParseException, IOException {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return  "redirect:/login";
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//					if(val == false) {
//						return  "AccessTiles";
//				}
			
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			String institute_id = request.getParameter("institute_id");
			
			
			//Basic Institute Information
			String bsc_info_inst_addr_dtl_rmk = request.getParameter("bsc_info_inst_addr_dtl_rmk");
			String bsc_info_mgmt_dtl_rmk = request.getParameter("bsc_info_mgmt_dtl_rmk");
			String bsc_info_basicinfo_dtl_rmk = request.getParameter("bsc_info_basicinfo_dtl_rmk");
			String bsc_info_reg_num_dtl_rmk = request.getParameter("bsc_info_reg_num_dtl_rmk");
			String bsc_info_num_yrs_exp_rmk = request.getParameter("bsc_info_num_yrs_exp_rmk");
			String bsc_info_quali_rmk = request.getParameter("bsc_info_quali_rmk");
			String bsc_info_hoi_addr_rmk = request.getParameter("bsc_info_hoi_addr_rmk");
			String bsc_info_nr_airpo_dtl_rmk = request.getParameter("bsc_info_nr_airpo_dtl_rmk");
			String bsc_info_nr_railwy_dtl_rmk = request.getParameter("bsc_info_nr_railwy_dtl_rmk");
			String bsc_info_other_relevant_dtl_rmk = request.getParameter("bsc_info_other_relevant_dtl_rmk");
			String bsc_info_police_dtl_rmk = request.getParameter("bsc_info_police_dtl_rmk");
			String bsc_info_int_cap_ug_rmk = request.getParameter("bsc_info_int_cap_ug_rmk");
			String bsc_info_int_cap_pg_rmk = request.getParameter("bsc_info_int_cap_pg_rmk");
			String bsc_info_course_int_cap_ug_rmk = request.getParameter("bsc_info_course_int_cap_ug_rmk");
			String bsc_info_course_int_cap_pg_rmk = request.getParameter("bsc_info_course_int_cap_pg_rmk");
			String bsc_info_dtl_of_land_rmk = request.getParameter("bsc_info_dtl_of_land_rmk");
			
			//COLLEGE INFRASTRACTURE
			String clg_infra_clg_coun_web_dtl_rmk = request.getParameter("clg_infra_clg_coun_web_dtl_rmk");
			String clg_infra_cam_loc_rmk = request.getParameter("clg_infra_cam_loc_rmk");
			String clg_infra_progress_inst_rmk = request.getParameter("clg_infra_progress_inst_rmk");
			String clg_infra_details_hostel_rmk = request.getParameter("clg_infra_details_hostel_rmk");
			String clg_infra_details_mess_rmk = request.getParameter("clg_infra_details_mess_rmk");
			String clg_infra_herbal_garden_rmk = request.getParameter("clg_infra_herbal_garden_rmk");
			String clg_infra_additional_info_rmk = request.getParameter("clg_infra_additional_info_rmk");
			String clg_infra_dtl_various_clg_teach_dept_rmk = request.getParameter("clg_infra_dtl_various_clg_teach_dept_rmk");
			String clg_infra_dtl_teach_dept_rmk = request.getParameter("clg_infra_dtl_teach_dept_rmk");
			String clg_infra_no_of_journal_rmk = request.getParameter("clg_infra_no_of_journal_rmk");
			String clg_infra_books_rmk = request.getParameter("clg_infra_books_rmk");
			String clg_infra_lib_dtl_rmk = request.getParameter("clg_infra_lib_dtl_rmk");
			String clg_infra_lib_ass_rmk = request.getParameter("clg_infra_lib_ass_rmk");
			
			//STUDENT DETAILS
			String stu_dtl_info_admit_stude_rmk = request.getParameter("stu_dtl_info_admit_stude_rmk");
			String stu_dtl_upl_doc_rmk = request.getParameter("stu_dtl_upl_doc_rmk");
			String stu_dtl_pass_stu_rmk = request.getParameter("stu_dtl_pass_stu_rmk");
			String stu_dtl_intern_housejod_rmk = request.getParameter("stu_dtl_intern_housejod_rmk");
			
			
			//Department Equipment
			String dep_equip_obstr_gyna_rmk = request.getParameter("dep_equip_obstr_gyna_rmk");
			String dep_equip_anatomy_rmk = request.getParameter("dep_equip_anatomy_rmk");
			String dep_equip_cm_medi_rmk = request.getParameter("dep_equip_cm_medi_rmk");
			String dep_equip_equip_dtl_rmk = request.getParameter("dep_equip_equip_dtl_rmk");
			String dep_equip_acts_regu_rmk = request.getParameter("dep_equip_acts_regu_rmk");
			String dep_equip_hom_phar_rmk = request.getParameter("dep_equip_hom_phar_rmk");
			String dep_equip_organon_medi_rmk = request.getParameter("dep_equip_organon_medi_rmk");
			String dep_equip_patho_micro_rmk = request.getParameter("dep_equip_patho_micro_rmk");
			String dep_equip_physio_biocheme_rmk = request.getParameter("dep_equip_physio_biocheme_rmk");
			String dep_equip_biochemestry_rmk = request.getParameter("dep_equip_biochemestry_rmk");
			String dep_equip_prac_of_medici_rmk = request.getParameter("dep_equip_prac_of_medici_rmk");
			String dep_equip_repetory_rmk = request.getParameter("dep_equip_repetory_rmk");
			String dep_equip_surgery_rmk = request.getParameter("dep_equip_surgery_rmk");
			String dep_equip_hom_mat_medi_rmk = request.getParameter("dep_equip_hom_mat_medi_rmk");
			String dep_equip_psychiatry_rmk = request.getParameter("dep_equip_psychiatry_rmk");
			String dep_equip_pediatrics_rmk = request.getParameter("dep_equip_pediatrics_rmk");
			
			//College Financial
			String clg_fin_financial_capa_rmk = request.getParameter("clg_fin_financial_capa_rmk");
			String clg_fin_budget_clg_hosp_rmk = request.getParameter("clg_fin_budget_clg_hosp_rmk");
			String clg_fin_finan_the_proj_rmk = request.getParameter("clg_fin_finan_the_proj_rmk");
			String clg_fin_revenue_gener_rmk = request.getParameter("clg_fin_revenue_gener_rmk");
			String clg_fin_expend_incurred_rmk = request.getParameter("clg_fin_expend_incurred_rmk");
			String clg_fin_oper_rsult_rmk = request.getParameter("clg_fin_oper_rsult_rmk");
			String clg_fin_bank_account_rmk = request.getParameter("clg_fin_bank_account_rmk");
			String clg_fin_expense_staff_rmk = request.getParameter("clg_fin_expense_staff_rmk");
			String clg_fin_expense_purchase_rmk = request.getParameter("clg_fin_expense_purchase_rmk");
			String clg_fin_misc_expense_rmk = request.getParameter("clg_fin_misc_expense_rmk");
			
			//College Staff Information
			String clg_staff_teaching_staff_ug_rmk = request.getParameter("clg_staff_teaching_staff_ug_rmk");
			String clg_staff_teaching_staff_pg_rmk = request.getParameter("clg_staff_teaching_staff_pg_rmk");
			String clg_staff_non_tching_staff_rmk = request.getParameter("clg_staff_non_tching_staff_rmk");
			String clg_staff_last_aca_yr_rmk = request.getParameter("clg_staff_last_aca_yr_rmk");
			String clg_staff_salary_info_rmk = request.getParameter("clg_staff_salary_info_rmk");
			String clg_staff_tchr_prmotion_rmk = request.getParameter("clg_staff_tchr_prmotion_rmk");
			String clg_staff_upload_doc_rmk = request.getParameter("clg_staff_upload_doc_rmk");
			//Hospital Staff List
			String staff_dtl_medical_staff_rmk = request.getParameter("staff_dtl_medical_staff_rmk");
			String staff_dtl_paramedical_staff_rmk = request.getParameter("staff_dtl_paramedical_staff_rmk");
			String staff_dtl_auxillary_staff_rmk = request.getParameter("staff_dtl_auxillary_staff_rmk");
			
			//College Department
			String clg_dep_comp_printer_avail_rmk = request.getParameter("clg_dep_comp_printer_avail_rmk");
			String clg_dep_ug_rmk = request.getParameter("clg_dep_ug_rmk");
			String clg_dep_pg_rmk = request.getParameter("clg_dep_pg_rmk");
			String clg_dep_acad_performance_rmk = request.getParameter("clg_dep_acad_performance_rmk");
			
			
			//Hospital Infrastructure
			String hosp_infra_hos_admi_rmk = request.getParameter("hosp_infra_hos_admi_rmk");
			String hosp_infra_hos_opd_rmk = request.getParameter("hosp_infra_hos_opd_rmk");
			String hosp_infra_hos_ipd_rmk = request.getParameter("hosp_infra_hos_ipd_rmk");
			String hosp_infra_ot_rmk = request.getParameter("hosp_infra_ot_rmk");
			String hosp_infra_rehab_uni_rmk = request.getParameter("hosp_infra_rehab_uni_rmk");
			String hosp_infra_clini_lab_rmk = request.getParameter("hosp_infra_clini_lab_rmk");
			String hosp_infra_radio_sono_rmk = request.getParameter("hosp_infra_radio_sono_rmk");
			String hosp_infra_hos_kitchen_rmk = request.getParameter("hosp_infra_hos_kitchen_rmk");
			String hosp_infra_store_dtl_rmk = request.getParameter("hosp_infra_store_dtl_rmk");
			String hosp_infra_other_infra_dtl_rmk = request.getParameter("hosp_infra_other_infra_dtl_rmk");
			
			////hospital ipd opd
			String hos_opd_ipd_opd_patients_rmk = request.getParameter("hos_opd_ipd_opd_patients_rmk");
			String hos_opd_ipd_ipd_patients_rmk = request.getParameter("hos_opd_ipd_ipd_patients_rmk");
			String hos_opd_ipd_bed_day_occu_rmk = request.getParameter("hos_opd_ipd_bed_day_occu_rmk");
			String hos_opd_ipd_bed_existed_rmk = request.getParameter("hos_opd_ipd_bed_existed_rmk");
			String hos_opd_ipd_upload_doc_rmk = request.getParameter("hos_opd_ipd_upload_doc_rmk");
			
			/////Other Hospital Details
			String other_hos_dtl_maint_record_rmk = request.getParameter("other_hos_dtl_maint_record_rmk");
			String other_hos_dtl_labour_room_rmk = request.getParameter("other_hos_dtl_labour_room_rmk");
			String other_hos_dtl_ot_rmk = request.getParameter("other_hos_dtl_ot_rmk");
			String other_hos_dtl_investi_rmk = request.getParameter("other_hos_dtl_investi_rmk");
			String other_hos_dtl_clini_lab_rmk = request.getParameter("other_hos_dtl_clini_lab_rmk");
			String other_hos_dtl_verifi_of_func_rmk = request.getParameter("other_hos_dtl_verifi_of_func_rmk");
			String other_hos_dtl_mou_rmk = request.getParameter("other_hos_dtl_mou_rmk");
			
			////Declaration
			String decl_affi_indem_bond_rmk = request.getParameter("decl_affi_indem_bond_rmk");
			String decl_decl_by_princi_rmk = request.getParameter("decl_decl_by_princi_rmk");
			String decl_decl_by_mngt_rmk = request.getParameter("decl_decl_by_mngt_rmk");
			
			
			//Hospital Staff Detail
			String hosp_staff_dtl_hospital_staff_rmk = request.getParameter("hosp_staff_dtl_hospital_staff_rmk");
			String hosp_staff_dtl_upload_doc_rmk = request.getParameter("hosp_staff_dtl_upload_doc_rmk");
			
			//HOSPITAL EQUIPMENT
			String hospi_equip_instru_equip_rmk = request.getParameter("hospi_equip_instru_equip_rmk");
			String hospi_equip_upload_doc_rmk = request.getParameter("hospi_equip_upload_doc_rmk");
					
			//COLLEGE STAFF LIST
			String clg_staff_list_teaching_fac_rmk = request.getParameter("clg_staff_list_teaching_fac_rmk");
			String clg_staff_list_guest_fac_rmk = request.getParameter("clg_staff_list_guest_fac_rmk");
			String clg_staff_list_non_tach_fac_rmk = request.getParameter("clg_staff_list_non_tach_fac_rmk");
			String clg_staff_list_upload_doc_rmk = request.getParameter("clg_staff_list_upload_doc_rmk");
			
			String clg_document_rmk = request.getParameter("clg_document_rmk");
			
			String hid_college_infrastructure_remark = request.getParameter("hid_college_infrastructure_remark");
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				CLG_REG_REMARKS college_infra_remark =new CLG_REG_REMARKS();
				
				
				//Basic Institute Information
				if(bsc_info_inst_addr_dtl_rmk != "" && bsc_info_inst_addr_dtl_rmk != null) {
					college_infra_remark.setBsc_info_inst_addr_dtl_rmk(bsc_info_inst_addr_dtl_rmk);
				}
				if(bsc_info_mgmt_dtl_rmk != "" && bsc_info_mgmt_dtl_rmk != null) {
					college_infra_remark.setBsc_info_mgmt_dtl_rmk(bsc_info_mgmt_dtl_rmk);
				}
				if(bsc_info_basicinfo_dtl_rmk != "" && bsc_info_basicinfo_dtl_rmk != null) {
					college_infra_remark.setBsc_info_basicinfo_dtl_rmk(bsc_info_basicinfo_dtl_rmk);
				}
				if(bsc_info_reg_num_dtl_rmk != "" && bsc_info_reg_num_dtl_rmk != null) {
					college_infra_remark.setBsc_info_reg_num_dtl_rmk(bsc_info_reg_num_dtl_rmk);
				}
				if(bsc_info_num_yrs_exp_rmk != "" && bsc_info_num_yrs_exp_rmk != null) {
					college_infra_remark.setBsc_info_num_yrs_exp_rmk(bsc_info_num_yrs_exp_rmk);
				}
				if(bsc_info_quali_rmk != "" && bsc_info_quali_rmk != null) {
					college_infra_remark.setBsc_info_quali_rmk(bsc_info_quali_rmk);
				}
				if(bsc_info_hoi_addr_rmk != "" && bsc_info_hoi_addr_rmk != null) {
					college_infra_remark.setBsc_info_hoi_addr_rmk(bsc_info_hoi_addr_rmk);
				}
				if(bsc_info_nr_airpo_dtl_rmk != "" && bsc_info_nr_airpo_dtl_rmk != null) {
					college_infra_remark.setBsc_info_nr_airpo_dtl_rmk(bsc_info_nr_airpo_dtl_rmk);
				}
				if(bsc_info_nr_railwy_dtl_rmk != "" && bsc_info_nr_railwy_dtl_rmk != null) {
					college_infra_remark.setBsc_info_nr_railwy_dtl_rmk(bsc_info_nr_railwy_dtl_rmk);
				}
				if(bsc_info_other_relevant_dtl_rmk != "" && bsc_info_other_relevant_dtl_rmk != null) {
					college_infra_remark.setBsc_info_other_relevant_dtl_rmk(bsc_info_other_relevant_dtl_rmk);
				}
				if(bsc_info_police_dtl_rmk != "" && bsc_info_police_dtl_rmk != null) {
					college_infra_remark.setBsc_info_police_dtl_rmk(bsc_info_police_dtl_rmk);
				}
				if(bsc_info_int_cap_ug_rmk != "" && bsc_info_int_cap_ug_rmk != null) {
					college_infra_remark.setBsc_info_int_cap_ug_rmk(bsc_info_int_cap_ug_rmk);
				}
				if(bsc_info_int_cap_pg_rmk != "" && bsc_info_int_cap_pg_rmk != null) {
					college_infra_remark.setBsc_info_int_cap_pg_rmk(bsc_info_int_cap_pg_rmk);
				}
				if(bsc_info_course_int_cap_ug_rmk != "" && bsc_info_course_int_cap_ug_rmk != null) {
					college_infra_remark.setBsc_info_course_int_cap_ug_rmk(bsc_info_course_int_cap_ug_rmk);
				}
				if(bsc_info_course_int_cap_pg_rmk != "" && bsc_info_course_int_cap_pg_rmk != null) {
					college_infra_remark.setBsc_info_course_int_cap_ug_rmk(bsc_info_course_int_cap_pg_rmk);
				}
				if(bsc_info_dtl_of_land_rmk != "" && bsc_info_dtl_of_land_rmk != null) {
					college_infra_remark.setBsc_info_dtl_of_land_rmk(bsc_info_dtl_of_land_rmk);
				}
				
				
				//COLLEGE INFRASTRUCTURE
				if(clg_infra_clg_coun_web_dtl_rmk != "" && clg_infra_clg_coun_web_dtl_rmk != null) {
					college_infra_remark.setClg_infra_clg_coun_web_dtl_rmk(clg_infra_clg_coun_web_dtl_rmk);
				}
				if(clg_infra_cam_loc_rmk != "" && clg_infra_cam_loc_rmk != null) {
					college_infra_remark.setClg_infra_cam_loc_rmk(clg_infra_cam_loc_rmk);
				}
				if(clg_infra_progress_inst_rmk != "" && clg_infra_progress_inst_rmk != null) {
					college_infra_remark.setClg_infra_progress_inst_rmk(clg_infra_progress_inst_rmk);
				}
				if(clg_infra_details_hostel_rmk != "" && clg_infra_details_hostel_rmk != null) {
					college_infra_remark.setClg_infra_details_hostel_rmk(clg_infra_details_hostel_rmk);
				}
				if(clg_infra_details_mess_rmk != "" && clg_infra_details_mess_rmk != null) {
					college_infra_remark.setClg_infra_details_mess_rmk(clg_infra_details_mess_rmk);
				}
				if(clg_infra_herbal_garden_rmk != "" && clg_infra_herbal_garden_rmk != null) {
					college_infra_remark.setClg_infra_herbal_garden_rmk(clg_infra_herbal_garden_rmk);
				}
				if(clg_infra_additional_info_rmk != "" && clg_infra_additional_info_rmk != null) {
					college_infra_remark.setClg_infra_additional_info_rmk(clg_infra_additional_info_rmk);
				}
				if(clg_infra_dtl_various_clg_teach_dept_rmk != "" && clg_infra_dtl_various_clg_teach_dept_rmk != null) {
					college_infra_remark.setClg_infra_dtl_various_clg_teach_dept_rmk(clg_infra_dtl_various_clg_teach_dept_rmk);
				}
				if(clg_infra_dtl_teach_dept_rmk != "" && clg_infra_dtl_teach_dept_rmk != null) {
					college_infra_remark.setClg_infra_dtl_teach_dept_rmk(clg_infra_dtl_teach_dept_rmk);
				}
				if(clg_infra_no_of_journal_rmk != "" && clg_infra_no_of_journal_rmk != null) {
					college_infra_remark.setClg_infra_no_of_journal_rmk(clg_infra_no_of_journal_rmk);
				}
				if(clg_infra_books_rmk != "" && clg_infra_books_rmk != null) {
					college_infra_remark.setClg_infra_books_rmk(clg_infra_books_rmk);
				}
				if(clg_infra_lib_dtl_rmk != "" && clg_infra_lib_dtl_rmk != null) {
					college_infra_remark.setClg_infra_lib_dtl_rmk(clg_infra_lib_dtl_rmk);
				}
				if(clg_infra_lib_ass_rmk != "" && clg_infra_lib_ass_rmk != null) {
					college_infra_remark.setClg_infra_lib_ass_rmk(clg_infra_lib_ass_rmk);
				}
				
				//STUDENT DETAILS
				if(stu_dtl_info_admit_stude_rmk != "" && stu_dtl_info_admit_stude_rmk != null) {
					college_infra_remark.setStu_dtl_info_admit_stude_rmk(stu_dtl_info_admit_stude_rmk);
				}
				if(stu_dtl_upl_doc_rmk != "" && stu_dtl_upl_doc_rmk != null) {
					college_infra_remark.setStu_dtl_upl_doc_rmk(stu_dtl_upl_doc_rmk);
				}
				if(stu_dtl_pass_stu_rmk != "" && stu_dtl_pass_stu_rmk != null) {
					college_infra_remark.setStu_dtl_pass_stu_rmk(stu_dtl_pass_stu_rmk);
				}
				if(stu_dtl_intern_housejod_rmk != "" && stu_dtl_intern_housejod_rmk != null) {
					college_infra_remark.setStu_dtl_intern_housejod_rmk(stu_dtl_intern_housejod_rmk);
				}
				
				
				//Department Equipment
				if(dep_equip_obstr_gyna_rmk != "" && dep_equip_obstr_gyna_rmk != null) {
					college_infra_remark.setDep_equip_obstr_gyna_rmk(dep_equip_obstr_gyna_rmk);
				}
				if(dep_equip_anatomy_rmk != "" && dep_equip_anatomy_rmk != null) {
					college_infra_remark.setDep_equip_anatomy_rmk(dep_equip_anatomy_rmk);
				}
				if(dep_equip_cm_medi_rmk != "" && dep_equip_cm_medi_rmk != null) {
					college_infra_remark.setDep_equip_cm_medi_rmk(dep_equip_cm_medi_rmk);
				}
				if(dep_equip_equip_dtl_rmk != "" && dep_equip_equip_dtl_rmk != null) {
					college_infra_remark.setDep_equip_equip_dtl_rmk(dep_equip_equip_dtl_rmk);
				}
				if(dep_equip_acts_regu_rmk != "" && dep_equip_acts_regu_rmk != null) {
					college_infra_remark.setDep_equip_acts_regu_rmk(dep_equip_acts_regu_rmk);
				}
				if(dep_equip_hom_phar_rmk != "" && dep_equip_hom_phar_rmk != null) {
					college_infra_remark.setDep_equip_hom_phar_rmk(dep_equip_hom_phar_rmk);
				}
				if(dep_equip_organon_medi_rmk != "" && dep_equip_organon_medi_rmk != null) {
					college_infra_remark.setDep_equip_organon_medi_rmk(dep_equip_organon_medi_rmk);
				}
				if(dep_equip_patho_micro_rmk != "" && dep_equip_patho_micro_rmk != null) {
					college_infra_remark.setDep_equip_patho_micro_rmk(dep_equip_patho_micro_rmk);
				}
				if(dep_equip_physio_biocheme_rmk != "" && dep_equip_physio_biocheme_rmk != null) {
					college_infra_remark.setDep_equip_physio_biocheme_rmk(dep_equip_physio_biocheme_rmk);
				}
				if(dep_equip_biochemestry_rmk != "" && dep_equip_biochemestry_rmk != null) {
					college_infra_remark.setDep_equip_biochemestry_rmk(dep_equip_biochemestry_rmk);
				}
				if(dep_equip_prac_of_medici_rmk != "" && dep_equip_prac_of_medici_rmk != null) {
					college_infra_remark.setDep_equip_prac_of_medici_rmk(dep_equip_prac_of_medici_rmk);
				}
				if(dep_equip_repetory_rmk != "" && dep_equip_repetory_rmk != null) {
					college_infra_remark.setDep_equip_repetory_rmk(dep_equip_repetory_rmk);
				}
				if(dep_equip_surgery_rmk != "" && dep_equip_surgery_rmk != null) {
					college_infra_remark.setDep_equip_surgery_rmk(dep_equip_surgery_rmk);
				}
				if(dep_equip_hom_mat_medi_rmk != "" && dep_equip_hom_mat_medi_rmk != null) {
					college_infra_remark.setDep_equip_hom_mat_medi_rmk(dep_equip_hom_mat_medi_rmk);
				}
				if(dep_equip_psychiatry_rmk != "" && dep_equip_psychiatry_rmk != null) {
					college_infra_remark.setDep_equip_psychiatry_rmk(dep_equip_psychiatry_rmk);
				}
				if(dep_equip_pediatrics_rmk != "" && dep_equip_pediatrics_rmk != null) {
					college_infra_remark.setDep_equip_pediatrics_rmk(dep_equip_pediatrics_rmk);
				}
				
				
				//College Financial
				
				if(clg_fin_financial_capa_rmk != "" && clg_fin_financial_capa_rmk != null) {
					college_infra_remark.setClg_fin_financial_capa_rmk(clg_fin_financial_capa_rmk);
					}
				
				if(clg_fin_budget_clg_hosp_rmk != "" && clg_fin_budget_clg_hosp_rmk != null) {
					college_infra_remark.setClg_fin_budget_clg_hosp_rmk(clg_fin_budget_clg_hosp_rmk);
					}
				
				if(clg_fin_finan_the_proj_rmk != "" && clg_fin_finan_the_proj_rmk != null) {
					college_infra_remark.setClg_fin_finan_the_proj_rmk(clg_fin_finan_the_proj_rmk);
					}
				
				if(clg_fin_revenue_gener_rmk != "" && clg_fin_revenue_gener_rmk != null) {
					college_infra_remark.setClg_fin_revenue_gener_rmk(clg_fin_revenue_gener_rmk);
					}
				
				if(clg_fin_expend_incurred_rmk != "" && clg_fin_expend_incurred_rmk != null) {
					college_infra_remark.setClg_fin_expend_incurred_rmk(clg_fin_expend_incurred_rmk);
					}
				
				if(clg_fin_oper_rsult_rmk != "" && clg_fin_oper_rsult_rmk != null) {
					college_infra_remark.setClg_fin_oper_rsult_rmk(clg_fin_oper_rsult_rmk);
					}
				
				
				if(clg_fin_bank_account_rmk != "" && clg_fin_bank_account_rmk != null) {
					college_infra_remark.setClg_fin_bank_account_rmk(clg_fin_bank_account_rmk);
					}
				
				
				if(clg_fin_expense_staff_rmk != "" && clg_fin_expense_staff_rmk != null) {
					college_infra_remark.setClg_fin_expense_staff_rmk(clg_fin_expense_staff_rmk);
					}
				
				
				if(clg_fin_expense_purchase_rmk != "" && clg_fin_expense_purchase_rmk != null) {
					college_infra_remark.setClg_fin_expense_purchase_rmk(clg_fin_expense_purchase_rmk);
					}
				

				if(clg_fin_misc_expense_rmk != "" && clg_fin_misc_expense_rmk != null) {
					college_infra_remark.setClg_fin_misc_expense_rmk(clg_fin_misc_expense_rmk);
					}
				
				
				//College Staff Information
				
				if(clg_staff_teaching_staff_ug_rmk != "" && clg_staff_teaching_staff_ug_rmk != null) {
					college_infra_remark.setClg_staff_teaching_staff_ug_rmk(clg_staff_teaching_staff_ug_rmk);
					}
				if(clg_staff_teaching_staff_pg_rmk != "" && clg_staff_teaching_staff_pg_rmk != null) {
					college_infra_remark.setClg_staff_teaching_staff_pg_rmk(clg_staff_teaching_staff_pg_rmk);
					}
				if(clg_staff_non_tching_staff_rmk != "" && clg_staff_non_tching_staff_rmk != null) {
					college_infra_remark.setClg_staff_non_tching_staff_rmk(clg_staff_non_tching_staff_rmk);
					}
				if(clg_staff_last_aca_yr_rmk != "" && clg_staff_last_aca_yr_rmk != null) {
					college_infra_remark.setClg_staff_last_aca_yr_rmk(clg_staff_last_aca_yr_rmk);
					}
				if(clg_staff_salary_info_rmk != "" && clg_staff_salary_info_rmk != null) {
					college_infra_remark.setClg_staff_salary_info_rmk(clg_staff_salary_info_rmk);
					}
				if(clg_staff_tchr_prmotion_rmk != "" && clg_staff_tchr_prmotion_rmk != null) {
					college_infra_remark.setClg_staff_tchr_prmotion_rmk(clg_staff_tchr_prmotion_rmk);
					}
				if(clg_staff_upload_doc_rmk != "" && clg_staff_upload_doc_rmk != null) {
					college_infra_remark.setClg_staff_upload_doc_rmk(clg_staff_upload_doc_rmk);
					}
				
				//Hospital Staff List
				if(staff_dtl_medical_staff_rmk != "" && staff_dtl_medical_staff_rmk != null) {
					college_infra_remark.setStaff_dtl_medical_staff_rmk(staff_dtl_medical_staff_rmk);
					}
				if(staff_dtl_paramedical_staff_rmk != "" && staff_dtl_paramedical_staff_rmk != null) {
					college_infra_remark.setStaff_dtl_paramedical_staff_rmk(staff_dtl_paramedical_staff_rmk);
					}
				if(staff_dtl_auxillary_staff_rmk != "" && staff_dtl_auxillary_staff_rmk != null) {
					college_infra_remark.setStaff_dtl_auxillary_staff_rmk(staff_dtl_auxillary_staff_rmk);
					}
				
				//College Department
				if(clg_dep_comp_printer_avail_rmk != "" && clg_dep_comp_printer_avail_rmk != null) {
					college_infra_remark.setClg_dep_comp_printer_avail_rmk(clg_dep_comp_printer_avail_rmk);
					}
				if(clg_dep_ug_rmk != "" && clg_dep_ug_rmk != null) {
				college_infra_remark.setClg_dep_ug_rmk(clg_dep_ug_rmk);
				}
				if(clg_dep_pg_rmk != "" && clg_dep_pg_rmk != null) {
					college_infra_remark.setClg_dep_pg_rmk(clg_dep_pg_rmk);
				}
				if(clg_dep_acad_performance_rmk != "" && clg_dep_acad_performance_rmk != null) {
					college_infra_remark.setClg_dep_acad_performance_rmk(clg_dep_acad_performance_rmk);
				}
					
					
				//Hospital Infrastructure
				if(hosp_infra_hos_admi_rmk != "" && hosp_infra_hos_admi_rmk != null) {
					college_infra_remark.setHosp_infra_hos_admi_rmk(hosp_infra_hos_admi_rmk);
					}
				if(hosp_infra_hos_opd_rmk != "" && hosp_infra_hos_opd_rmk != null) {
				college_infra_remark.setHosp_infra_hos_opd_rmk(hosp_infra_hos_opd_rmk);
				}
				if(hosp_infra_hos_ipd_rmk != "" && hosp_infra_hos_ipd_rmk != null) {
					college_infra_remark.setHosp_infra_hos_ipd_rmk(hosp_infra_hos_ipd_rmk);
				}
				if(hosp_infra_ot_rmk != "" && hosp_infra_ot_rmk != null) {
					college_infra_remark.setHosp_infra_ot_rmk(hosp_infra_ot_rmk);
				}
				if(hosp_infra_rehab_uni_rmk != "" && hosp_infra_rehab_uni_rmk != null) {
					college_infra_remark.setHosp_infra_rehab_uni_rmk(hosp_infra_rehab_uni_rmk);
				}
				if(hosp_infra_clini_lab_rmk != "" && hosp_infra_clini_lab_rmk != null) {
					college_infra_remark.setHosp_infra_clini_lab_rmk(hosp_infra_clini_lab_rmk);
				}
				if(hosp_infra_radio_sono_rmk != "" && hosp_infra_radio_sono_rmk != null) {
					college_infra_remark.setHosp_infra_radio_sono_rmk(hosp_infra_radio_sono_rmk);
				}
				if(hosp_infra_hos_kitchen_rmk != "" && hosp_infra_hos_kitchen_rmk != null) {
					college_infra_remark.setHosp_infra_hos_kitchen_rmk(hosp_infra_hos_kitchen_rmk);
				}
				if(hosp_infra_store_dtl_rmk != "" && hosp_infra_store_dtl_rmk != null) {
					college_infra_remark.setHosp_infra_store_dtl_rmk(hosp_infra_store_dtl_rmk);
				}
				if(hosp_infra_other_infra_dtl_rmk != "" && hosp_infra_other_infra_dtl_rmk != null) {
					college_infra_remark.setHosp_infra_other_infra_dtl_rmk(hosp_infra_other_infra_dtl_rmk);
				}
				
				
				/// hospital ipdopd
				if(hos_opd_ipd_opd_patients_rmk != "" && hos_opd_ipd_opd_patients_rmk != null) {
					college_infra_remark.setHos_opd_ipd_opd_patients_rmk(hos_opd_ipd_opd_patients_rmk);
				}
				if(hos_opd_ipd_ipd_patients_rmk != "" && hos_opd_ipd_ipd_patients_rmk != null) {
					college_infra_remark.setHos_opd_ipd_ipd_patients_rmk(hos_opd_ipd_ipd_patients_rmk);
				}
				if(hos_opd_ipd_bed_day_occu_rmk != "" && hos_opd_ipd_bed_day_occu_rmk != null) {
					college_infra_remark.setHos_opd_ipd_bed_day_occu_rmk(hos_opd_ipd_bed_day_occu_rmk);
				}
				if(hos_opd_ipd_bed_existed_rmk != "" && hos_opd_ipd_bed_existed_rmk != null) {
					college_infra_remark.setHos_opd_ipd_bed_existed_rmk(hos_opd_ipd_bed_existed_rmk);
				}
				if(hos_opd_ipd_upload_doc_rmk != "" && hos_opd_ipd_upload_doc_rmk != null) {
					college_infra_remark.setHos_opd_ipd_upload_doc_rmk(hos_opd_ipd_upload_doc_rmk);
				}
				
				////Other Hospital Details
				if(other_hos_dtl_maint_record_rmk != "" && other_hos_dtl_maint_record_rmk != null) {
					college_infra_remark.setOther_hos_dtl_maint_record_rmk(other_hos_dtl_maint_record_rmk);
				}
				if(other_hos_dtl_labour_room_rmk != "" && other_hos_dtl_labour_room_rmk != null) {
					college_infra_remark.setOther_hos_dtl_labour_room_rmk(other_hos_dtl_labour_room_rmk);
				}
				if(other_hos_dtl_ot_rmk != "" && other_hos_dtl_ot_rmk != null) {
					college_infra_remark.setOther_hos_dtl_ot_rmk(other_hos_dtl_ot_rmk);
				}
				if(other_hos_dtl_investi_rmk != "" && other_hos_dtl_investi_rmk != null) {
					college_infra_remark.setOther_hos_dtl_investi_rmk(other_hos_dtl_investi_rmk);
				}
				if(other_hos_dtl_clini_lab_rmk != "" && other_hos_dtl_clini_lab_rmk != null) {
					college_infra_remark.setOther_hos_dtl_clini_lab_rmk(other_hos_dtl_clini_lab_rmk);
				}
				if(other_hos_dtl_verifi_of_func_rmk != "" && other_hos_dtl_verifi_of_func_rmk != null) {
					college_infra_remark.setOther_hos_dtl_verifi_of_func_rmk(other_hos_dtl_verifi_of_func_rmk);
				}
				if(other_hos_dtl_mou_rmk != "" && other_hos_dtl_mou_rmk != null) {
					college_infra_remark.setOther_hos_dtl_mou_rmk(other_hos_dtl_mou_rmk);
				}
				////Declaration
				if(decl_affi_indem_bond_rmk != "" && decl_affi_indem_bond_rmk != null) {
					college_infra_remark.setDecl_affi_indem_bond_rmk(decl_affi_indem_bond_rmk);
				}
				if(decl_decl_by_princi_rmk != "" && decl_decl_by_princi_rmk != null) {
					college_infra_remark.setDecl_decl_by_princi_rmk(decl_decl_by_princi_rmk);
				}
				if(decl_decl_by_mngt_rmk != "" && decl_decl_by_mngt_rmk != null) {
					college_infra_remark.setDecl_decl_by_mngt_rmk(decl_decl_by_mngt_rmk);
				}
				
				//Hospital Staff Detail
				
				if(hosp_staff_dtl_hospital_staff_rmk != "" && hosp_staff_dtl_hospital_staff_rmk != null) {
					college_infra_remark.setHosp_staff_dtl_hospital_staff_rmk(hosp_staff_dtl_hospital_staff_rmk);
				}
				
				if(hosp_staff_dtl_upload_doc_rmk != "" && hosp_staff_dtl_upload_doc_rmk != null) {
					college_infra_remark.setHosp_staff_dtl_upload_doc_rmk(hosp_staff_dtl_upload_doc_rmk);
				}
				
				//Hospital Equipment
				if(hospi_equip_instru_equip_rmk != "" && hospi_equip_instru_equip_rmk != null) {
					college_infra_remark.setStaff_dtl_paramedical_staff_rmk(hospi_equip_instru_equip_rmk);
				}
				
				if(hospi_equip_upload_doc_rmk != "" && hospi_equip_upload_doc_rmk != null) {
					college_infra_remark.setStaff_dtl_auxillary_staff_rmk(hospi_equip_upload_doc_rmk);
				}
				
				//COLLEGE STAFF LIST
				if(clg_staff_list_teaching_fac_rmk != "" && clg_staff_list_teaching_fac_rmk != null) {
					college_infra_remark.setClg_staff_list_teaching_fac_rmk(clg_staff_list_teaching_fac_rmk);
				}
				if(clg_staff_list_guest_fac_rmk != "" && clg_staff_list_guest_fac_rmk != null) {
					college_infra_remark.setClg_staff_list_guest_fac_rmk(clg_staff_list_guest_fac_rmk);
				}
				if(clg_staff_list_non_tach_fac_rmk != "" && clg_staff_list_non_tach_fac_rmk != null) {
					college_infra_remark.setClg_staff_list_non_tach_fac_rmk(clg_staff_list_non_tach_fac_rmk);
				}
				if(clg_staff_list_upload_doc_rmk != "" && clg_staff_list_upload_doc_rmk != null) {
					college_infra_remark.setClg_staff_list_upload_doc_rmk(clg_staff_list_upload_doc_rmk);
				}
				
				if(clg_document_rmk != "" && clg_document_rmk != null) {
					college_infra_remark.setClg_document_rmk(clg_document_rmk);
				}
							
					
					
				college_infra_remark.setInst_id(Integer.parseInt(institute_id));
				college_infra_remark.setCreated_by(userid);
				college_infra_remark.setCreated_date(date);
				
				if (Integer.parseInt(hid_college_infrastructure_remark) == 0) {
					int hid_college_council1= (Integer) sessionHQL.save(college_infra_remark);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_college_council1) ;
			}
				else {

					CLG_REG_REMARKS college_infra_remark_u = (CLG_REG_REMARKS) sessionHQL
							.get(CLG_REG_REMARKS.class, Integer.parseInt(hid_college_infrastructure_remark));
					
					//Basic Institution Information
					
					
					if(bsc_info_inst_addr_dtl_rmk != "" && bsc_info_inst_addr_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_inst_addr_dtl_rmk(bsc_info_inst_addr_dtl_rmk);
					}
					if(bsc_info_mgmt_dtl_rmk != "" && bsc_info_mgmt_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_mgmt_dtl_rmk(bsc_info_mgmt_dtl_rmk);
					}
					if(bsc_info_basicinfo_dtl_rmk != "" && bsc_info_basicinfo_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_basicinfo_dtl_rmk(bsc_info_basicinfo_dtl_rmk);
					}
					if(bsc_info_reg_num_dtl_rmk != "" && bsc_info_reg_num_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_reg_num_dtl_rmk(bsc_info_reg_num_dtl_rmk);
					}
					if(bsc_info_num_yrs_exp_rmk != "" && bsc_info_num_yrs_exp_rmk != null) {
						college_infra_remark_u.setBsc_info_num_yrs_exp_rmk(bsc_info_num_yrs_exp_rmk);
					}
					if(bsc_info_quali_rmk != "" && bsc_info_quali_rmk != null) {
						college_infra_remark_u.setBsc_info_quali_rmk(bsc_info_quali_rmk);
					}
					if(bsc_info_hoi_addr_rmk != "" && bsc_info_hoi_addr_rmk != null) {
						college_infra_remark_u.setBsc_info_hoi_addr_rmk(bsc_info_hoi_addr_rmk);
					}
					if(bsc_info_nr_airpo_dtl_rmk != "" && bsc_info_nr_airpo_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_nr_airpo_dtl_rmk(bsc_info_nr_airpo_dtl_rmk);
					}
					if(bsc_info_nr_railwy_dtl_rmk != "" && bsc_info_nr_railwy_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_nr_railwy_dtl_rmk(bsc_info_nr_railwy_dtl_rmk);
					}
					if(bsc_info_other_relevant_dtl_rmk != "" && bsc_info_other_relevant_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_other_relevant_dtl_rmk(bsc_info_other_relevant_dtl_rmk);
					}
					if(bsc_info_police_dtl_rmk != "" && bsc_info_police_dtl_rmk != null) {
						college_infra_remark_u.setBsc_info_police_dtl_rmk(bsc_info_police_dtl_rmk);
					}
					if(bsc_info_int_cap_ug_rmk != "" && bsc_info_int_cap_ug_rmk != null) {
						college_infra_remark_u.setBsc_info_int_cap_ug_rmk(bsc_info_int_cap_ug_rmk);
					}
					if(bsc_info_int_cap_pg_rmk != "" && bsc_info_int_cap_pg_rmk != null) {
						college_infra_remark_u.setBsc_info_int_cap_pg_rmk(bsc_info_int_cap_pg_rmk);
					}
					if(bsc_info_course_int_cap_ug_rmk != "" && bsc_info_course_int_cap_ug_rmk != null) {
						college_infra_remark_u.setBsc_info_course_int_cap_ug_rmk(bsc_info_course_int_cap_ug_rmk);
					}
					if(bsc_info_course_int_cap_pg_rmk != "" && bsc_info_course_int_cap_pg_rmk != null) {
						college_infra_remark_u.setBsc_info_course_int_cap_ug_rmk(bsc_info_course_int_cap_pg_rmk);
					}
					if(bsc_info_dtl_of_land_rmk != "" && bsc_info_dtl_of_land_rmk != null) {
						college_infra_remark_u.setBsc_info_dtl_of_land_rmk(bsc_info_dtl_of_land_rmk);
					}
					
					//COLLEGE INFRASTRUCTURE
					if(clg_infra_clg_coun_web_dtl_rmk != "" && clg_infra_clg_coun_web_dtl_rmk != null) {
						college_infra_remark_u.setClg_infra_clg_coun_web_dtl_rmk(clg_infra_clg_coun_web_dtl_rmk);
					}
					if(clg_infra_cam_loc_rmk != "" && clg_infra_cam_loc_rmk != null) {
						college_infra_remark_u.setClg_infra_cam_loc_rmk(clg_infra_cam_loc_rmk);
					}
					if(clg_infra_progress_inst_rmk != "" && clg_infra_progress_inst_rmk != null) {
						college_infra_remark_u.setClg_infra_progress_inst_rmk(clg_infra_progress_inst_rmk);
					}
					if(clg_infra_details_hostel_rmk != "" && clg_infra_details_hostel_rmk != null) {
						college_infra_remark_u.setClg_infra_details_hostel_rmk(clg_infra_details_hostel_rmk);
					}
					if(clg_infra_details_mess_rmk != "" && clg_infra_details_mess_rmk != null) {
						college_infra_remark_u.setClg_infra_details_mess_rmk(clg_infra_details_mess_rmk);
					}
					if(clg_infra_herbal_garden_rmk != "" && clg_infra_herbal_garden_rmk != null) {
						college_infra_remark_u.setClg_infra_herbal_garden_rmk(clg_infra_herbal_garden_rmk);
					}
					if(clg_infra_additional_info_rmk != "" && clg_infra_additional_info_rmk != null) {
						college_infra_remark_u.setClg_infra_additional_info_rmk(clg_infra_additional_info_rmk);
					}
					if(clg_infra_dtl_various_clg_teach_dept_rmk != "" && clg_infra_dtl_various_clg_teach_dept_rmk != null) {
						college_infra_remark_u.setClg_infra_dtl_various_clg_teach_dept_rmk(clg_infra_dtl_various_clg_teach_dept_rmk);
					}
					if(clg_infra_dtl_teach_dept_rmk != "" && clg_infra_dtl_teach_dept_rmk != null) {
						college_infra_remark_u.setClg_infra_dtl_teach_dept_rmk(clg_infra_dtl_teach_dept_rmk);
					}
					if(clg_infra_no_of_journal_rmk != "" && clg_infra_no_of_journal_rmk != null) {
						college_infra_remark_u.setClg_infra_no_of_journal_rmk(clg_infra_no_of_journal_rmk);
					}
					if(clg_infra_books_rmk != "" && clg_infra_books_rmk != null) {
						college_infra_remark_u.setClg_infra_books_rmk(clg_infra_books_rmk);
					}
					if(clg_infra_lib_dtl_rmk != "" && clg_infra_lib_dtl_rmk != null) {
						college_infra_remark_u.setClg_infra_lib_dtl_rmk(clg_infra_lib_dtl_rmk);
					}
					if(clg_infra_lib_ass_rmk != "" && clg_infra_lib_ass_rmk != null) {
						college_infra_remark_u.setClg_infra_lib_ass_rmk(clg_infra_lib_ass_rmk);
					}
					
					//STUDENT DETAILS
					if(stu_dtl_info_admit_stude_rmk != "" && stu_dtl_info_admit_stude_rmk != null) {
						college_infra_remark_u.setStu_dtl_info_admit_stude_rmk(stu_dtl_info_admit_stude_rmk);
					}
					if(stu_dtl_upl_doc_rmk != "" && stu_dtl_upl_doc_rmk != null) {
						college_infra_remark_u.setStu_dtl_upl_doc_rmk(stu_dtl_upl_doc_rmk);
					}
					if(stu_dtl_pass_stu_rmk != "" && stu_dtl_pass_stu_rmk != null) {
						college_infra_remark_u.setStu_dtl_pass_stu_rmk(stu_dtl_pass_stu_rmk);
					}
					if(stu_dtl_intern_housejod_rmk != "" && stu_dtl_intern_housejod_rmk != null) {
						college_infra_remark_u.setStu_dtl_intern_housejod_rmk(stu_dtl_intern_housejod_rmk);
					}
						
						
					//Department Equipment
					if(dep_equip_obstr_gyna_rmk != "" && dep_equip_obstr_gyna_rmk != null) {
						college_infra_remark_u.setDep_equip_obstr_gyna_rmk(dep_equip_obstr_gyna_rmk);
					}
					if(dep_equip_anatomy_rmk != "" && dep_equip_anatomy_rmk != null) {
						college_infra_remark_u.setDep_equip_anatomy_rmk(dep_equip_anatomy_rmk);
					}
					if(dep_equip_cm_medi_rmk != "" && dep_equip_cm_medi_rmk != null) {
						college_infra_remark_u.setDep_equip_cm_medi_rmk(dep_equip_cm_medi_rmk);
					}
					if(dep_equip_equip_dtl_rmk != "" && dep_equip_equip_dtl_rmk != null) {
						college_infra_remark_u.setDep_equip_equip_dtl_rmk(dep_equip_equip_dtl_rmk);
					}
					if(dep_equip_acts_regu_rmk != "" && dep_equip_acts_regu_rmk != null) {
						college_infra_remark_u.setDep_equip_acts_regu_rmk(dep_equip_acts_regu_rmk);
					}
					if(dep_equip_hom_phar_rmk != "" && dep_equip_hom_phar_rmk != null) {
						college_infra_remark_u.setDep_equip_hom_phar_rmk(dep_equip_hom_phar_rmk);
					}
					if(dep_equip_organon_medi_rmk != "" && dep_equip_organon_medi_rmk != null) {
						college_infra_remark_u.setDep_equip_organon_medi_rmk(dep_equip_organon_medi_rmk);
					}
					if(dep_equip_patho_micro_rmk != "" && dep_equip_patho_micro_rmk != null) {
						college_infra_remark_u.setDep_equip_patho_micro_rmk(dep_equip_patho_micro_rmk);
					}
					if(dep_equip_physio_biocheme_rmk != "" && dep_equip_physio_biocheme_rmk != null) {
						college_infra_remark_u.setDep_equip_physio_biocheme_rmk(dep_equip_physio_biocheme_rmk);
					}
					if(dep_equip_biochemestry_rmk != "" && dep_equip_biochemestry_rmk != null) {
						college_infra_remark_u.setDep_equip_biochemestry_rmk(dep_equip_biochemestry_rmk);
					}
					if(dep_equip_prac_of_medici_rmk != "" && dep_equip_prac_of_medici_rmk != null) {
						college_infra_remark_u.setDep_equip_prac_of_medici_rmk(dep_equip_prac_of_medici_rmk);
					}
					if(dep_equip_repetory_rmk != "" && dep_equip_repetory_rmk != null) {
						college_infra_remark_u.setDep_equip_repetory_rmk(dep_equip_repetory_rmk);
					}
					if(dep_equip_surgery_rmk != "" && dep_equip_surgery_rmk != null) {
						college_infra_remark_u.setDep_equip_surgery_rmk(dep_equip_surgery_rmk);
					}
					if(dep_equip_hom_mat_medi_rmk != "" && dep_equip_hom_mat_medi_rmk != null) {
						college_infra_remark_u.setDep_equip_hom_mat_medi_rmk(dep_equip_hom_mat_medi_rmk);
					}
					if(dep_equip_psychiatry_rmk != "" && dep_equip_psychiatry_rmk != null) {
						college_infra_remark_u.setDep_equip_psychiatry_rmk(dep_equip_psychiatry_rmk);
					}
					if(dep_equip_pediatrics_rmk != "" && dep_equip_pediatrics_rmk != null) {
						college_infra_remark_u.setDep_equip_pediatrics_rmk(dep_equip_pediatrics_rmk);
					}
					
					//College Financial

					if(clg_fin_financial_capa_rmk != "" && clg_fin_financial_capa_rmk != null) {
						college_infra_remark_u.setClg_fin_financial_capa_rmk(clg_fin_financial_capa_rmk);
						}
					
					if(clg_fin_budget_clg_hosp_rmk != "" && clg_fin_budget_clg_hosp_rmk != null) {
						college_infra_remark_u.setClg_fin_budget_clg_hosp_rmk(clg_fin_budget_clg_hosp_rmk);
						}
					
					if(clg_fin_finan_the_proj_rmk != "" && clg_fin_finan_the_proj_rmk != null) {
						college_infra_remark_u.setClg_fin_finan_the_proj_rmk(clg_fin_finan_the_proj_rmk);
						}
					
					if(clg_fin_revenue_gener_rmk != "" && clg_fin_revenue_gener_rmk != null) {
						college_infra_remark_u.setClg_fin_revenue_gener_rmk(clg_fin_revenue_gener_rmk);
						}
					
					if(clg_fin_expend_incurred_rmk != "" && clg_fin_expend_incurred_rmk != null) {
						college_infra_remark_u.setClg_fin_expend_incurred_rmk(clg_fin_expend_incurred_rmk);
						}
					
					if(clg_fin_oper_rsult_rmk != "" && clg_fin_oper_rsult_rmk != null) {
						college_infra_remark_u.setClg_fin_oper_rsult_rmk(clg_fin_oper_rsult_rmk);
						}
					
					
					if(clg_fin_bank_account_rmk != "" && clg_fin_bank_account_rmk != null) {
						college_infra_remark_u.setClg_fin_bank_account_rmk(clg_fin_bank_account_rmk);
						}
					
					
					if(clg_fin_expense_staff_rmk != "" && clg_fin_expense_staff_rmk != null) {
						college_infra_remark_u.setClg_fin_expense_staff_rmk(clg_fin_expense_staff_rmk);
						}
					
					
					if(clg_fin_expense_purchase_rmk != "" && clg_fin_expense_purchase_rmk != null) {
						college_infra_remark_u.setClg_fin_expense_purchase_rmk(clg_fin_expense_purchase_rmk);
						}
					

					if(clg_fin_misc_expense_rmk != "" && clg_fin_misc_expense_rmk != null) {
						college_infra_remark_u.setClg_fin_misc_expense_rmk(clg_fin_misc_expense_rmk);
						}
					
					
					//College Staff Info
					if(clg_staff_teaching_staff_ug_rmk != "" && clg_staff_teaching_staff_ug_rmk != null) {
						college_infra_remark_u.setClg_staff_teaching_staff_ug_rmk(clg_staff_teaching_staff_ug_rmk);
						}
					if(clg_staff_teaching_staff_pg_rmk != "" && clg_staff_teaching_staff_pg_rmk != null) {
						college_infra_remark_u.setClg_staff_teaching_staff_pg_rmk(clg_staff_teaching_staff_pg_rmk);
						}
					if(clg_staff_non_tching_staff_rmk != "" && clg_staff_non_tching_staff_rmk != null) {
						college_infra_remark_u.setClg_staff_non_tching_staff_rmk(clg_staff_non_tching_staff_rmk);
						}
					if(clg_staff_last_aca_yr_rmk != "" && clg_staff_last_aca_yr_rmk != null) {
						college_infra_remark_u.setClg_staff_last_aca_yr_rmk(clg_staff_last_aca_yr_rmk);
						}
					if(clg_staff_salary_info_rmk != "" && clg_staff_salary_info_rmk != null) {
						college_infra_remark_u.setClg_staff_salary_info_rmk(clg_staff_salary_info_rmk);
						}
					if(clg_staff_tchr_prmotion_rmk != "" && clg_staff_tchr_prmotion_rmk != null) {
						college_infra_remark_u.setClg_staff_tchr_prmotion_rmk(clg_staff_tchr_prmotion_rmk);
						}
					if(clg_staff_upload_doc_rmk != "" && clg_staff_upload_doc_rmk != null) {
						college_infra_remark_u.setClg_staff_upload_doc_rmk(clg_staff_upload_doc_rmk);
						}
					
					//Hospital Staff List
					
					if(staff_dtl_medical_staff_rmk != "" && staff_dtl_medical_staff_rmk != null) {
						college_infra_remark_u.setStaff_dtl_medical_staff_rmk(staff_dtl_medical_staff_rmk);
						}
					if(staff_dtl_paramedical_staff_rmk != "" && staff_dtl_paramedical_staff_rmk != null) {
						college_infra_remark_u.setStaff_dtl_paramedical_staff_rmk(staff_dtl_paramedical_staff_rmk);
						}
					if(staff_dtl_auxillary_staff_rmk != "" && staff_dtl_auxillary_staff_rmk != null) {
						college_infra_remark_u.setStaff_dtl_auxillary_staff_rmk(staff_dtl_auxillary_staff_rmk);
						}
					
					//College Department
					if(clg_dep_comp_printer_avail_rmk != "" && clg_dep_comp_printer_avail_rmk != null) {
					college_infra_remark_u.setClg_dep_comp_printer_avail_rmk(clg_dep_comp_printer_avail_rmk);
					}
					if(clg_dep_ug_rmk != "" && clg_dep_ug_rmk != null) {
						college_infra_remark_u.setClg_dep_ug_rmk(clg_dep_ug_rmk);
					}
					if(clg_dep_pg_rmk != "" && clg_dep_pg_rmk != null) {
						college_infra_remark_u.setClg_dep_pg_rmk(clg_dep_pg_rmk);
					}
					if(clg_dep_acad_performance_rmk != "" && clg_dep_acad_performance_rmk != null) {
						college_infra_remark_u.setClg_dep_acad_performance_rmk(clg_dep_acad_performance_rmk);
					}
					
					
					
				//Hospital Infrastructure
					if(hosp_infra_hos_admi_rmk != "" && hosp_infra_hos_admi_rmk != null) {
					college_infra_remark_u.setHosp_infra_hos_admi_rmk(hosp_infra_hos_admi_rmk);
					}
					if(hosp_infra_hos_opd_rmk != "" && hosp_infra_hos_opd_rmk != null) {
						college_infra_remark_u.setHosp_infra_hos_opd_rmk(hosp_infra_hos_opd_rmk);
					}
					if(hosp_infra_hos_ipd_rmk != "" && hosp_infra_hos_ipd_rmk != null) {
						college_infra_remark_u.setHosp_infra_hos_ipd_rmk(hosp_infra_hos_ipd_rmk);
					}
					if(hosp_infra_ot_rmk != "" && hosp_infra_ot_rmk != null) {
						college_infra_remark_u.setHosp_infra_ot_rmk(hosp_infra_ot_rmk);
					}
					if(hosp_infra_rehab_uni_rmk != "" && hosp_infra_rehab_uni_rmk != null) {
						college_infra_remark_u.setHosp_infra_rehab_uni_rmk(hosp_infra_rehab_uni_rmk);
					}
					if(hosp_infra_clini_lab_rmk != "" && hosp_infra_clini_lab_rmk != null) {
						college_infra_remark_u.setHosp_infra_clini_lab_rmk(hosp_infra_clini_lab_rmk);
					}
					if(hosp_infra_radio_sono_rmk != "" && hosp_infra_radio_sono_rmk != null) {
						college_infra_remark_u.setHosp_infra_radio_sono_rmk(hosp_infra_radio_sono_rmk);
					}
					if(hosp_infra_hos_kitchen_rmk != "" && hosp_infra_hos_kitchen_rmk != null) {
						college_infra_remark_u.setHosp_infra_hos_kitchen_rmk(hosp_infra_hos_kitchen_rmk);
					}
					if(hosp_infra_store_dtl_rmk != "" && hosp_infra_store_dtl_rmk != null) {
						college_infra_remark_u.setHosp_infra_store_dtl_rmk(hosp_infra_store_dtl_rmk);
					}
					if(hosp_infra_other_infra_dtl_rmk != "" && hosp_infra_other_infra_dtl_rmk != null) {
						college_infra_remark_u.setHosp_infra_other_infra_dtl_rmk(hosp_infra_other_infra_dtl_rmk);
					}
					
					
					
					
					///shivali Hospital ipdopd
					if(hos_opd_ipd_opd_patients_rmk != "" && hos_opd_ipd_opd_patients_rmk != null) {
						college_infra_remark_u.setHos_opd_ipd_opd_patients_rmk(hos_opd_ipd_opd_patients_rmk);
					}
					if(hos_opd_ipd_ipd_patients_rmk != "" && hos_opd_ipd_ipd_patients_rmk != null) {
						college_infra_remark_u.setHos_opd_ipd_ipd_patients_rmk(hos_opd_ipd_ipd_patients_rmk);
					}
					if(hos_opd_ipd_bed_day_occu_rmk != "" && hos_opd_ipd_bed_day_occu_rmk != null) {
						college_infra_remark_u.setHos_opd_ipd_bed_day_occu_rmk(hos_opd_ipd_bed_day_occu_rmk);
					}
					if(hos_opd_ipd_bed_existed_rmk != "" && hos_opd_ipd_bed_existed_rmk != null) {
						college_infra_remark_u.setHos_opd_ipd_bed_existed_rmk(hos_opd_ipd_bed_existed_rmk);
					}
					if(hos_opd_ipd_upload_doc_rmk != "" && hos_opd_ipd_upload_doc_rmk != null) {
						college_infra_remark_u.setHos_opd_ipd_upload_doc_rmk(hos_opd_ipd_upload_doc_rmk);
					}
					
				////Other Hospital Details
					if(other_hos_dtl_maint_record_rmk != "" && other_hos_dtl_maint_record_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_maint_record_rmk(other_hos_dtl_maint_record_rmk);
					}
					if(other_hos_dtl_labour_room_rmk != "" && other_hos_dtl_labour_room_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_labour_room_rmk(other_hos_dtl_labour_room_rmk);
					}
					if(other_hos_dtl_ot_rmk != "" && other_hos_dtl_ot_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_ot_rmk(other_hos_dtl_ot_rmk);
					}
					if(other_hos_dtl_investi_rmk != "" && other_hos_dtl_investi_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_investi_rmk(other_hos_dtl_investi_rmk);
					}
					if(other_hos_dtl_clini_lab_rmk != "" && other_hos_dtl_clini_lab_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_clini_lab_rmk(other_hos_dtl_clini_lab_rmk);
					}
					if(other_hos_dtl_verifi_of_func_rmk != "" && other_hos_dtl_verifi_of_func_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_verifi_of_func_rmk(other_hos_dtl_verifi_of_func_rmk);
					}
					if(other_hos_dtl_mou_rmk != "" && other_hos_dtl_mou_rmk != null) {
						college_infra_remark_u.setOther_hos_dtl_mou_rmk(other_hos_dtl_mou_rmk);
					}
				///Declaration
					if(decl_affi_indem_bond_rmk != "" && decl_affi_indem_bond_rmk != null) {
						college_infra_remark_u.setDecl_affi_indem_bond_rmk(decl_affi_indem_bond_rmk);
					}
					if(decl_decl_by_princi_rmk != "" && decl_decl_by_princi_rmk != null) {
						college_infra_remark_u.setDecl_decl_by_princi_rmk(decl_decl_by_princi_rmk);
					}
					if(decl_decl_by_mngt_rmk != "" && decl_decl_by_mngt_rmk != null) {
						college_infra_remark_u.setDecl_decl_by_mngt_rmk(decl_decl_by_mngt_rmk);
					}	
					
					//Hospital Staff Detail
					if(hosp_staff_dtl_hospital_staff_rmk != "" && hosp_staff_dtl_hospital_staff_rmk != null) {
						college_infra_remark_u.setHosp_staff_dtl_hospital_staff_rmk(hosp_staff_dtl_hospital_staff_rmk);
					}
					
					if(hosp_staff_dtl_upload_doc_rmk != "" && hosp_staff_dtl_upload_doc_rmk != null) {
						college_infra_remark_u.setHosp_staff_dtl_upload_doc_rmk(hosp_staff_dtl_upload_doc_rmk);
					}
					
					//Hospital Equipment
					if(hospi_equip_instru_equip_rmk != "" && hospi_equip_instru_equip_rmk != null) {
						college_infra_remark_u.setStaff_dtl_paramedical_staff_rmk(hospi_equip_instru_equip_rmk);
					}
					
					if(hospi_equip_upload_doc_rmk != "" && hospi_equip_upload_doc_rmk != null) {
						college_infra_remark_u.setStaff_dtl_auxillary_staff_rmk(hospi_equip_upload_doc_rmk);
					}
					
					//COLLEGE STAFF LIST
					if(clg_staff_list_teaching_fac_rmk != "" && clg_staff_list_teaching_fac_rmk != null) {
						college_infra_remark_u.setClg_staff_list_teaching_fac_rmk(clg_staff_list_teaching_fac_rmk);
					}
					if(clg_staff_list_guest_fac_rmk != "" && clg_staff_list_guest_fac_rmk != null) {
						college_infra_remark_u.setClg_staff_list_guest_fac_rmk(clg_staff_list_guest_fac_rmk);
					}
					if(clg_staff_list_non_tach_fac_rmk != "" && clg_staff_list_non_tach_fac_rmk != null) {
						college_infra_remark_u.setClg_staff_list_non_tach_fac_rmk(clg_staff_list_non_tach_fac_rmk);
					}
					if(clg_staff_list_upload_doc_rmk != "" && clg_staff_list_upload_doc_rmk != null) {
						college_infra_remark_u.setClg_staff_list_upload_doc_rmk(clg_staff_list_upload_doc_rmk);
					}
						
					if(clg_document_rmk != "" && clg_document_rmk != null) {
						college_infra_remark_u.setClg_document_rmk(clg_document_rmk);
					}	
						
					
					college_infra_remark_u.setInst_id(Integer.parseInt(institute_id));
					college_infra_remark_u.setModified_by(userid);
					college_infra_remark_u.setModified_date(date);
					sessionHQL.update(college_infra_remark_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					
				}

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
		return  "Data Saved Successfully";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
