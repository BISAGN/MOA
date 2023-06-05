package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_ADMINISTRATION;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_CLINICAL_LABORATORY;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_INFRA;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_IPD;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_KITCHEN_CANTEEN;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_OPD;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_OT;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_OTHER_IPD_DETAIL;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_REHABILATION_UNIT;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_STORES;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_OTHER_INFRA_DETAILS;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hosp_Administration_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Clg_Reg_Hospital_Infrastructure_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;

	@Autowired
	private Clg_Reg_Hosp_Administration_Dao HDao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;

	@RequestMapping(value = "admin/hospital_infrastructure", method = RequestMethod.GET)
	public ModelAndView hospital_infrastructure(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());

		String username = session.getAttribute("roleloginName").toString();
		String role = session.getAttribute("role").toString();
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from CLG_REG_HOSP_INFRA where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		
//		if(ibdao.getpid_from_userid(userid).size()!=0) {
//			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//				Mmap.put("msg", "Please Save Basic details First");
//				return new ModelAndView("redirect:basics_information");
//
//			}else {
//				
//				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//			}
//		}else {
//			
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}
		
		Mmap.put("institude", institude);
		Mmap.put("dataforHA", HDao.getAllDepartmentforHA(Integer.parseInt(institude)));
		Mmap.put("dataforOPD", HDao.getAllDepartmentforOPD());
		Mmap.put("dataforIPD", HDao.getAllDepartmentforIPD());
		Mmap.put("dataforOT", HDao.getAllDepartmentforOT());
		Mmap.put("dataforRU", HDao.getAllDepartmentforRU());
		Mmap.put("dataforCL", HDao.getAllDepartmentforCL());
		Mmap.put("dataforRS", HDao.getAllDepartmentforRS());
		Mmap.put("dataforOI", HDao.getAllDepartmentforOI());
		Mmap.put("dataforHK", HDao.getAllDepartmentforHK());
		Mmap.put("dataforHS", HDao.getAllDepartmentforHS());
		Mmap.put("dataanother", HDao.getAllOtherdetails(Integer.parseInt(institude)));
		Mmap.put("dataanotheripd", HDao.getAllOtherdetailsipd(Integer.parseInt(institude)));
		Mmap.put("dataofstatuatory", HDao.getAllStatuatorydata(Integer.parseInt(institude)));
		
		Mmap.put("getHospital_department_administrative_list", HDao.getHospital_department_administrative_list());
		Mmap.put("getHospital_department_opd_list", HDao.getHospital_department_opd_list());
		Mmap.put("getHospital_department_ipd_list", HDao.getHospital_department_ipd_list());
		Mmap.put("getHospital_department_ot_list", HDao.getHospital_department_ot_list());
		Mmap.put("getHospital_department_ru_list", HDao.getHospital_department_ru_list());
		Mmap.put("getHospital_department_cl_list", HDao.getHospital_department_cl_list());
		Mmap.put("getHospital_department_rs_list", HDao.getHospital_department_rs_list());
		Mmap.put("getHospital_department_hk_list", HDao.getHospital_department_hk_list());
		Mmap.put("getHospital_department_hs_list", HDao.getHospital_department_hs_list());
		Mmap.put("getHospital_department_oi_list", HDao.getHospital_department_oi_list());

		return new ModelAndView("hospital_infrastructure");
	}

	// ===============hospital administative ================

	@PostMapping(value = "/hospital_administrative_Action")
	public @ResponseBody String hospital_administrative_Action(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		String s_id = session.getAttribute("super_id").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_administrative_list();

//		String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);
		// int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_ADMINISTRATION intake_cap_ha = new CLG_REG_HOSP_ADMINISTRATION();
			
			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_ha" + list.get(i).get(0));
				String hospital_department_name = request.getParameter("course_name_ha_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_ha_" + list.get(i).get(0));
				String hid_course_ha = request.getParameter("hid_course_ha_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("admin_constructed_area");
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				
				if (hospital_department_name == null || hospital_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Hospital Department Name");
					return "Please Enter Hospital Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				if (validation.maxlength10(available_area) == false) {
					ra.addAttribute("msg", "Available Area" + validation.MaxlengthMSG10);
					return "Available Area" +validation.MaxlengthMSG10;
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area " +validation.isOnlyNumberMSG;
				}
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}
				
				intake_cap_ha.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_ha.setHospital_department_name(hospital_department_name);
				intake_cap_ha.setAvailable_area(Integer.parseInt(available_area));

				intake_cap_ha.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_ha.setCreated_by((userid));
				intake_cap_ha.setCreated_date(date);
				intake_cap_ha.setInstitute_id(Integer.parseInt(institude));
				intake_cap_ha.setS_id(Integer.parseInt(s_id));

				if (Integer.parseInt(hid_course_ha) == 0) {
					// constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_ha);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_ADMINISTRATION intake_cap_ha_u = (CLG_REG_HOSP_ADMINISTRATION) sessionHQL
							.get(CLG_REG_HOSP_ADMINISTRATION.class, Integer.parseInt(hid_course_ha));

					intake_cap_ha_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_ha_u.setHospital_department_name(hospital_department_name);
					intake_cap_ha_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_ha_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_ha_u.setInstitute_id(Integer.parseInt(institude));
					intake_cap_ha_u.setModified_by((userid));
					intake_cap_ha_u.setModified_date(date);
					sessionHQL.update(intake_cap_ha_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();
			
			Transaction tx3 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_INFRA add_info = new CLG_REG_HOSP_INFRA();

			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			
			
			String statytorycheck = request.getParameter("statytorycheck");
			String hid_add_info = request.getParameter("hid_add_info");

			add_info.setStatutory_requirements_for_hospital(Integer.parseInt(statytorycheck));
			add_info.setInstitute_id(Integer.parseInt(institude));
			add_info.setCreated_by(username);
			add_info.setCreated_date(date);
			
			if (statytorycheck == null || statytorycheck.trim().equals("")) {
				ra.addAttribute("msg", "Please Select College fulfill The Statutory Requirement Services");
				return "Please Select College fulfill The Statutory Requirement Services";
			}
			
			if (Integer.parseInt(hid_add_info) == 0) {
				int hid_add_info1 = (Integer) sessionHQL.save(add_info);
				sessionHQL.flush();
				sessionHQL.clear();
				tx3.commit();
				return String.valueOf(hid_add_info1);
			} else {

				CLG_REG_HOSP_INFRA add_info_u = (CLG_REG_HOSP_INFRA) sessionHQL.get(CLG_REG_HOSP_INFRA.class,
						Integer.parseInt(hid_add_info));

				add_info_u.setStatutory_requirements_for_hospital(Integer.parseInt(statytorycheck));
				add_info_u.setInstitute_id(Integer.parseInt(institude));
				add_info_u.setModified_by(userid);
				add_info_u.setModified_date(date);
				sessionHQL.update(add_info_u);
				sessionHQL.flush();
				sessionHQL.clear();
				tx3.commit();
				}
			}

			catch (RuntimeException e) {
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
		return "Data Saved Successfully";
	}

	// FETCH Administration DETAILS=====

	@RequestMapping(value = "admin/getAdministration_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_ADMINISTRATION> getAdministration_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_ADMINISTRATION where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_ADMINISTRATION> clist = (List<CLG_REG_HOSP_ADMINISTRATION>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	// ===============hospital opd ================

	@PostMapping(value = "/hospital_opd_Action")
	public @ResponseBody String hospital_opd_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_opd_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_OPD intake_cap_opd = new CLG_REG_HOSP_OPD();

			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_opd" + list.get(i).get(0));
				String opd_department_name = request.getParameter("course_name_opd_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_opd_" + list.get(i).get(0));
				String hid_course_opd = request.getParameter("hid_course_opd_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("opd_constructed_area");// total
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				if (validation.maxlength10(available_area) == false) {
					ra.addAttribute("msg", "Available Area" + validation.MaxlengthMSG10);
					return "Available Area" +validation.MaxlengthMSG10;
				}
				
				if (opd_department_name == null || opd_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Department Name");
					return "Please Enter OPD Department Name";
				}
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}
				
				intake_cap_opd.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_opd.setOpd_department_name(opd_department_name);
				intake_cap_opd.setAvailable_area(Integer.parseInt(available_area));

				intake_cap_opd.setTotal_avail_area(Integer.parseInt(total_avail_area));// total
				intake_cap_opd.setCreated_by((userid));
				intake_cap_opd.setCreated_date(date);
				intake_cap_opd.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_opd) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_opd);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_OPD intake_cap_opd_u = (CLG_REG_HOSP_OPD) sessionHQL.get(CLG_REG_HOSP_OPD.class,
							Integer.parseInt(hid_course_opd));
					
					intake_cap_opd_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_opd_u.setOpd_department_name(opd_department_name);
					intake_cap_opd_u.setAvailable_area(Integer.parseInt(available_area));

					intake_cap_opd_u.setTotal_avail_area(Integer.parseInt(total_avail_area));// total
					intake_cap_opd_u.setModified_by((userid));
					intake_cap_opd_u.setModified_date(date);
					sessionHQL.update(intake_cap_opd_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// ===== FETCH OPD DETAILS =====

	@RequestMapping(value = "admin/getOpd_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_OPD> getOpd_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
//			String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_OPD where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_OPD> clist = (List<CLG_REG_HOSP_OPD>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// ===============hospital Ipd ================

	@PostMapping(value = "/hospital_ipd_Action")
	public @ResponseBody String hospital_ipd_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal,@RequestParam(value = "casultyservice_document", required = false) MultipartFile casultyservice_doc,
			@RequestParam(value = "treatment_outcome_ipd_document", required = false) MultipartFile treatment_outcome_ipd_doc,RedirectAttributes ra, HashMap<String, Object> casulty_doc) throws ParseException, IOException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_ipd_list();

//				String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);

		// int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_IPD intake_cap_ipd = new CLG_REG_HOSP_IPD();

			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_ipd" + list.get(i).get(0));
				String ipd_department_name = request.getParameter("course_name_ipd_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_ipd_" + list.get(i).get(0));
				String hid_course_ipd = request.getParameter("hid_course_ipd_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("ipd_constructed_area");
				
				if (ipd_department_name == null || ipd_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Department Name");
					return "Please Enter IPD Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				if (validation.maxlength10(available_area) == false) {
					ra.addAttribute("msg", "Available Area" + validation.MaxlengthMSG10);
					return "Available Area" +validation.MaxlengthMSG10;
				}
				
//				if (validation.maxlength10(available_area) == false) {
//					ra.addAttribute("msg", "Available Area " + validation.MaxlengthMSG10);
//					return "Should Contain Maximum 10 Digits.";
//				}
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}
				
				intake_cap_ipd.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_ipd.setIpd_department_name(ipd_department_name);
				intake_cap_ipd.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_ipd.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_ipd.setCreated_by((userid));
				intake_cap_ipd.setCreated_date(date);
				intake_cap_ipd.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_ipd) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_ipd);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_IPD intake_cap_ipd_u = (CLG_REG_HOSP_IPD) sessionHQL.get(CLG_REG_HOSP_IPD.class,
							Integer.parseInt(hid_course_ipd));

					intake_cap_ipd_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_ipd_u.setIpd_department_name(ipd_department_name);
					intake_cap_ipd_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_ipd_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_ipd_u.setModified_by((userid));
					intake_cap_ipd_u.setModified_date(date);
					sessionHQL.update(intake_cap_ipd_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();
			
			Transaction tx4 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_OTHER_IPD_DETAIL a_d = new CLG_REG_HOSP_OTHER_IPD_DETAIL();

			 {
				int hid_course_another_cs=Integer.parseInt(request.getParameter("another_detail_cs_hidden"));
				String ipdcasualtycheck = request.getParameter("ipdcasualtycheck");
				String ipdtreatmentcheck = request.getParameter("ipdtreatmentcheck");
				String casultyservice_document = "casultyservice_document";
				String treatment_outcome_ipd_document = "treatment_outcome_ipd_document";
				
				if (!casultyservice_doc.isEmpty()) {
					casultyservice_document = upload_imagemethod(request,casultyservice_doc,session, casultyservice_document);
				}
				else {
					casultyservice_document = request.getParameter("hid_casultyservice_document");
				}
				
				if (!treatment_outcome_ipd_doc.isEmpty()) {
					treatment_outcome_ipd_document = upload_imagemethod(request,treatment_outcome_ipd_doc,session, treatment_outcome_ipd_document);
				}
				else {
					treatment_outcome_ipd_document = request.getParameter("hid_treat_outcome_document");
				}
				
				
				UserLogin ea1 = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude1 = String.valueOf(ea1.getInstitute_id());
				
				a_d.setIpd_casulty_service(Integer.parseInt(ipdcasualtycheck));
				a_d.setTreatment_outcome_ipd(Integer.parseInt(ipdtreatmentcheck));
				
				if(!casultyservice_document.equals("")) {
					a_d.setIpd_casulty_document(casultyservice_document);
					}
				
				if(!treatment_outcome_ipd_document.equals("")) {
					a_d.setTreatment_outcome_ipd_document(treatment_outcome_ipd_document);
					}
				
				a_d.setCreated_by((userid));
				a_d.setCreated_date(date);
				a_d.setInstitute_id(Integer.parseInt(institude1));

				if (hid_course_another_cs == 0){
					int hid_course_another2 = (Integer) sessionHQL.save(a_d);
					sessionHQL.flush();
					sessionHQL.clear();
					tx4.commit();
					return String.valueOf(hid_course_another2);
				} else {
					CLG_REG_HOSP_OTHER_IPD_DETAIL  a_d_u = (CLG_REG_HOSP_OTHER_IPD_DETAIL) sessionHQL
							.get(CLG_REG_HOSP_OTHER_IPD_DETAIL.class, (hid_course_another_cs));

					a_d_u.setIpd_casulty_service(Integer.parseInt(ipdcasualtycheck));
					a_d_u.setTreatment_outcome_ipd(Integer.parseInt(ipdtreatmentcheck));
					
					if(!casultyservice_document.equals("")) {
						a_d_u.setIpd_casulty_document(casultyservice_document);
						}
					
					if(!treatment_outcome_ipd_document.equals("")) {
						a_d_u.setTreatment_outcome_ipd_document(treatment_outcome_ipd_document);
						}
					a_d_u.setModified_by((userid));
					a_d_u.setModified_date(date);
					sessionHQL.update(a_d_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx4.commit();
					
				}

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
		return "Data Saved Successfully";
	}

	// FETCH IPD DETAILS=====

	@RequestMapping(value = "admin/getIpd_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_IPD> getIpd_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
//				String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_IPD where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_IPD> clist = (List<CLG_REG_HOSP_IPD>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// ===============hospital Ot ================

	@PostMapping(value = "/hospital_ot_Action")
	public @ResponseBody String hospital_ot_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_ot_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_OT intake_cap_ot = new CLG_REG_HOSP_OT();

			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_ot" + list.get(i).get(0));
				String ot_department_name = request.getParameter("course_name_ot_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_ot_" + list.get(i).get(0));
				String hid_course_ot = request.getParameter("hid_course_ot_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("ot_constructed_area");
				
				if (ot_department_name == null || ot_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OT Department Name");
					return "Please Enter OT Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
//				if (validation.maxlength10(available_area) == false) {
//					ra.addAttribute("msg", "Available Area " + validation.MaxlengthMSG10);
//					return "Should Contain Maximum 10 Digits.";
//					
//				}
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}
				

				intake_cap_ot.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_ot.setOt_department_name(ot_department_name);
				intake_cap_ot.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_ot.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_ot.setCreated_by((userid));
				intake_cap_ot.setCreated_date(date);
				intake_cap_ot.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_ot) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_ot);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_OT intake_cap_ot_u = (CLG_REG_HOSP_OT) sessionHQL.get(CLG_REG_HOSP_OT.class,
							Integer.parseInt(hid_course_ot));

					intake_cap_ot_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_ot_u.setOt_department_name(ot_department_name);
					intake_cap_ot_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_ot_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_ot_u.setModified_by((userid));
					intake_cap_ot_u.setModified_date(date);
					sessionHQL.update(intake_cap_ot_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// FETCH OT DETAILS=====

	@RequestMapping(value = "admin/getOt_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_OT> getOt_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_OT where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_OT> clist = (List<CLG_REG_HOSP_OT>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

//===============hospital rehabilation unit ================

	@PostMapping(value = "/hospital_ru_Action")
	public @ResponseBody String hospital_ru_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_ru_list();

//				String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);

		// int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_REHABILATION_UNIT intake_cap_ru = new CLG_REG_HOSP_REHABILATION_UNIT();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_ru" + list.get(i).get(0));
				String department_name = request.getParameter("course_name_ru_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_ru_" + list.get(i).get(0));
				String hid_course_ru = request.getParameter("hid_course_ru_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("reh_py_area");
				
				if (department_name == null || department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Department Name");
					return "Please Enter Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}

				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());

				intake_cap_ru.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_ru.setDepartment_name(department_name);
				intake_cap_ru.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_ru.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_ru.setCreated_by((userid));
				intake_cap_ru.setCreated_date(date);
				intake_cap_ru.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_ru) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_ru);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_REHABILATION_UNIT intake_cap_ru_u = (CLG_REG_HOSP_REHABILATION_UNIT) sessionHQL
							.get(CLG_REG_HOSP_REHABILATION_UNIT.class, Integer.parseInt(hid_course_ru));

					intake_cap_ru_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_ru_u.setDepartment_name(department_name);
					intake_cap_ru_u.setAvailable_area(Integer.parseInt(available_area));

					intake_cap_ru_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_ru_u.setModified_by((userid));
					intake_cap_ru_u.setModified_date(date);
					sessionHQL.update(intake_cap_ru_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// FETCH REHABILATION DETAILS=====

	@RequestMapping(value = "admin/getRu_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_REHABILATION_UNIT> getRu_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_REHABILATION_UNIT where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_REHABILATION_UNIT> clist = (List<CLG_REG_HOSP_REHABILATION_UNIT>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// ===============hospital clinical laboratory ================

	@PostMapping(value = "/hospital_cl_Action")
	public @ResponseBody String hospital_cl_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_cl_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_CLINICAL_LABORATORY intake_cap_cl = new CLG_REG_HOSP_CLINICAL_LABORATORY();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_cl" + list.get(i).get(0));
				String department_name = request.getParameter("course_name_cl_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_cl_" + list.get(i).get(0));
				String hid_course_cl = request.getParameter("hid_course_cl_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("cl_area");
				
				if (department_name == null || department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Department Name");
					return "Please Enter Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}

				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}

				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());

				intake_cap_cl.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_cl.setDepartment_name(department_name);
				intake_cap_cl.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_cl.setTotal_avail_area(Integer.parseInt(total_avail_area));

				intake_cap_cl.setCreated_by((userid));
				intake_cap_cl.setCreated_date(date);
				intake_cap_cl.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_cl) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_cl);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_CLINICAL_LABORATORY intake_cap_cl_u = (CLG_REG_HOSP_CLINICAL_LABORATORY) sessionHQL
							.get(CLG_REG_HOSP_CLINICAL_LABORATORY.class, Integer.parseInt(hid_course_cl));

					intake_cap_cl_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_cl_u.setDepartment_name(department_name);
					intake_cap_cl_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_cl_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_cl_u.setModified_by((userid));
					intake_cap_cl_u.setModified_date(date);
					sessionHQL.update(intake_cap_cl_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// FETCH Clinical DETAILS=====

	@RequestMapping(value = "admin/getCl_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_CLINICAL_LABORATORY> getCl_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_CLINICAL_LABORATORY where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_CLINICAL_LABORATORY> clist = (List<CLG_REG_HOSP_CLINICAL_LABORATORY>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

//===============hospital Radiology or Sonography ================

	@PostMapping(value = "/hospital_rs_Action")
	public @ResponseBody String hospital_rs_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_rs_list();
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY intake_cap_rs = new CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_rs" + list.get(i).get(0));
				String department_name = request.getParameter("course_name_rs_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_rs_" + list.get(i).get(0));
				String hid_course_rs = request.getParameter("hid_course_rs_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("rs_area");
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				if (department_name == null || department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Department Name");
					return "Please Enter Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}

				intake_cap_rs.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_rs.setDepartment_name(department_name);
				intake_cap_rs.setAvailable_area(Integer.parseInt(available_area));

				intake_cap_rs.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_rs.setCreated_by((userid));
				intake_cap_rs.setCreated_date(date);
				intake_cap_rs.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_rs) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_rs);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY intake_cap_rs_u = (CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY) sessionHQL
							.get(CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY.class, Integer.parseInt(hid_course_rs));

					intake_cap_rs_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_rs_u.setDepartment_name(department_name);
					intake_cap_rs_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_rs_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_rs_u.setModified_by((userid));
					intake_cap_rs_u.setModified_date(date);
					sessionHQL.update(intake_cap_rs_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// FETCH RADIOLOGY SONOGRAPHY DETAILS=====

	@RequestMapping(value = "admin/getRs_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY> getRs_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY> clist = (List<CLG_REG_HOSP_RADIOLOGY_SONOGRAPHY>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

//===============hospital Kitchen canteen================

	@PostMapping(value = "/hospital_hk_Action")
	public @ResponseBody String hospital_hk_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_hk_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_KITCHEN_CANTEEN intake_cap_hk = new CLG_REG_HOSP_KITCHEN_CANTEEN();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_hk" + list.get(i).get(0));
				String kitchen_department_name = request.getParameter("course_name_hk_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_hk_" + list.get(i).get(0));
				String hid_course_hk = request.getParameter("hid_course_hk_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("kitcan_total_area");
				
				if (kitchen_department_name == null || kitchen_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Kitchen Department Name");
					return "Please Enter Kitchen Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}

				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());

				intake_cap_hk.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_hk.setKitchen_department_name(kitchen_department_name);
				intake_cap_hk.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_hk.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_hk.setCreated_by((userid));
				intake_cap_hk.setCreated_date(date);
				intake_cap_hk.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_hk) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_hk);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_KITCHEN_CANTEEN intake_cap_hk_u = (CLG_REG_HOSP_KITCHEN_CANTEEN) sessionHQL
							.get(CLG_REG_HOSP_KITCHEN_CANTEEN.class, Integer.parseInt(hid_course_hk));

					intake_cap_hk_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_hk_u.setKitchen_department_name(kitchen_department_name);
					intake_cap_hk_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_hk_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_hk_u.setModified_by((userid));
					intake_cap_hk_u.setModified_date(date);
					sessionHQL.update(intake_cap_hk_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// FETCH Hospital Kitchen and Canteen DETAILS=====

	@RequestMapping(value = "admin/getHk_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_KITCHEN_CANTEEN> getHk_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_KITCHEN_CANTEEN where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_KITCHEN_CANTEEN> clist = (List<CLG_REG_HOSP_KITCHEN_CANTEEN>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

//===============hospital Store================

	@PostMapping(value = "/hospital_hs_Action")
	public @ResponseBody String hospital_hs_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_hs_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_STORES intake_cap_hs = new CLG_REG_HOSP_STORES();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_hs" + list.get(i).get(0));
				String store_department_name = request.getParameter("course_name_hs_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_hs_" + list.get(i).get(0));
				String hid_course_hs = request.getParameter("hid_course_hs_" + list.get(i).get(0));
				String total_avail_area = request.getParameter("store_total_area");
				String grand_total_avail_area = request.getParameter("hos_grandtotal_area");
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				if (store_department_name == null || store_department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Store Department Name");
					return "Please Enter Store Department Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area");
					return "Please Enter Available Area";
				}
				
				
				if (total_avail_area == null || total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Area");
					return "Please Enter Total Available Area";
				}
				
				if (grand_total_avail_area == null || grand_total_avail_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Grand Total Available Area");
					return "Please Enter Total Grand Available Area";
				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				if (validation.isOnlyNumber(total_avail_area) == false) {
					ra.addAttribute("msg","Total Available Area" +validation.isOnlyNumberMSG);
					return "Total Available Area" +validation.isOnlyNumberMSG;
				}

				intake_cap_hs.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_hs.setStore_department_name(store_department_name);
				intake_cap_hs.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_hs.setTotal_avail_area(Integer.parseInt(total_avail_area));
				intake_cap_hs.setGrand_total_avail_area(Integer.parseInt(grand_total_avail_area));
				intake_cap_hs.setCreated_by((userid));
				intake_cap_hs.setCreated_date(date);
				intake_cap_hs.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_hs) == 0) {
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_hs);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_STORES intake_cap_hs_u = (CLG_REG_HOSP_STORES) sessionHQL
							.get(CLG_REG_HOSP_STORES.class, Integer.parseInt(hid_course_hs));

					intake_cap_hs_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_hs_u.setStore_department_name(store_department_name);
					intake_cap_hs_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_hs_u.setTotal_avail_area(Integer.parseInt(total_avail_area));
					intake_cap_hs_u.setGrand_total_avail_area(Integer.parseInt(grand_total_avail_area));
					intake_cap_hs_u.setModified_by((userid));
					intake_cap_hs_u.setModified_date(date);
					sessionHQL.update(intake_cap_hs_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx1.commit();

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
		return "Data Saved Successfully";
	}

	// ====FETCH Hospital Store DETAILS=====

	@RequestMapping(value = "admin/getHs_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_STORES> getHs_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_HOSP_STORES where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_STORES> clist = (List<CLG_REG_HOSP_STORES>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

//===============hospital Other Infrastructure Details ================

	@PostMapping(value = "/hospital_oi_Action")
		public @ResponseBody String hospital_oi_Action( 
	HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
		@RequestParam(value = "ambulance_document", required = false) MultipartFile ambulance_doc,MultipartHttpServletRequest mul,
		RedirectAttributes ra) throws ParseException, IOException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();

		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> list = HDao.getHospital_department_oi_list();

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();

		try {

			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS intake_cap_oi = new CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS();

			for (int i = 0; i < list.size(); i++) {

				String department_id = request.getParameter("course_id_oi" + list.get(i).get(0));
				String department_name = request.getParameter("course_name_oi_" + list.get(i).get(0));
				String licence_name = request.getParameter("course_name_licence_" + list.get(i).get(0));
				String available_area = request.getParameter("intake_cap_course_oi_" + list.get(i).get(0));
				String hid_course_oi = request.getParameter("hid_course_oi_" + list.get(i).get(0));

				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				if (department_name == null || department_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Department Name");
					return "Please Enter Department Name";
				}
				
				if (licence_name == null || licence_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Licence Name");
					return "Please Enter Licence Name";
				}
				
				if (available_area == null || available_area.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Area ");
					return "Please Enter Available Area";
				}
				
//				if (validation.maxlength10(available_area) == false) {
//					ra.addAttribute("msg", "Available Area " + validation.MaxlengthMSG10);
//					return "Should Contain Maximum 10 Digits.";
//				}
				
				if (validation.isOnlyNumber(available_area) == false) {
					ra.addAttribute("msg","Available Area" +validation.isOnlyNumberMSG);
					return "Available Area" +validation.isOnlyNumberMSG;
				}
				
				intake_cap_oi.setDepartment_id(Integer.parseInt(department_id));
				intake_cap_oi.setDepartment_name(department_name);
				intake_cap_oi.setAvailable_area(Integer.parseInt(available_area));
				intake_cap_oi.setCreated_by((userid));
				intake_cap_oi.setCreated_date(date);
				intake_cap_oi.setStatus(1);
				intake_cap_oi.setInstitute_id(Integer.parseInt(institude));

				if (Integer.parseInt(hid_course_oi) == 0) {
					// constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
					int hid_department_area1 = (Integer) sessionHQL.save(intake_cap_oi);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS intake_cap_oi_u = (CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS) sessionHQL
							.get(CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS.class, Integer.parseInt(hid_course_oi));

					intake_cap_oi_u.setDepartment_id(Integer.parseInt(department_id));
					intake_cap_oi_u.setDepartment_name(department_name);
					intake_cap_oi_u.setAvailable_area(Integer.parseInt(available_area));
					intake_cap_oi_u.setModified_by((userid));
					intake_cap_oi_u.setModified_date(date);
					sessionHQL.update(intake_cap_oi_u);
					sessionHQL.flush();
					sessionHQL.clear();
				}

			}
			tx1.commit();
			
			Transaction tx2 = sessionHQL.beginTransaction();
			CLG_REG_OTHER_INFRA_DETAILS intake_cap_another = new CLG_REG_OTHER_INFRA_DETAILS();
			
			 {
				int hid_course_another=Integer.parseInt(request.getParameter("another_detail_hidden"));
				
				String sittingcheck = request.getParameter("sittingcheck");
				String researchlabcheck = request.getParameter("researchlabcheck");
				String casualtycheck = request.getParameter("casualtycheck");
				String ambulance_document = "ambulance_document";
				String ambulancecheck = request.getParameter("ambulancecheck");
				
				if (!ambulance_doc.isEmpty()) {
					ambulance_document = upload_imagemethod(request,ambulance_doc,session, ambulance_document);
				}
				else {
					ambulance_document = request.getParameter("hid_ambulance_document");
				}
				
				if (ambulancecheck == null || ambulancecheck.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Ambulance Services");
					return "Please Select Ambulance Services";
				}
				if (sittingcheck == null || sittingcheck.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Sitting arrangement for internes/students in Various Out Patient Department Services");
					return "Please Select Sitting arrangement for internees/students in Various Out Patient Department Services";
				}
				if (researchlabcheck == null || researchlabcheck.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Central Research Laboratory Services");
					return "Please Select Central Research Laboratory Services";
				}
				if (casualtycheck == null || casualtycheck.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Casualty Department");
					return "Please Select Casualty Department";
				}
				

				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				
				if (ambulance_document == null || ambulance_document.trim().equals("")) {
					ra.addAttribute("msg", "Please Upload Document In PDF Format.");
					return "Please Upload Document In PDF Format.";
				}
				
				intake_cap_another.setAmbulance_serv(Integer.parseInt(ambulancecheck));
				intake_cap_another.setAmbulance_document(ambulance_document);
				intake_cap_another.setSitting_arrangment(Integer.parseInt(sittingcheck));
				intake_cap_another.setCentral_research_lab(Integer.parseInt(researchlabcheck));
				
				
				if(!ambulance_document.equals("")) {
					intake_cap_another.setAmbulance_document(ambulance_document);
					}
				
				intake_cap_another.setCasualty_dept(Integer.parseInt(casualtycheck));
				intake_cap_another.setCreated_by((userid));
				intake_cap_another.setCreated_date(date);
//				intake_cap_another.setStatus(1);
				intake_cap_another.setInstitute_id(Integer.parseInt(institude));

				if (hid_course_another == 0){
					int hid_course_another1 = (Integer) sessionHQL.save(intake_cap_another);
					sessionHQL.flush();
					sessionHQL.clear();
					tx2.commit();
					return String.valueOf(hid_course_another1);
				} else {
					CLG_REG_OTHER_INFRA_DETAILS intake_cap_another_u = (CLG_REG_OTHER_INFRA_DETAILS) sessionHQL
							.get(CLG_REG_OTHER_INFRA_DETAILS.class, (hid_course_another));

					intake_cap_another_u.setAmbulance_serv(Integer.parseInt(ambulancecheck));
					intake_cap_another_u.setAmbulance_document(ambulance_document);
					intake_cap_another_u.setSitting_arrangment(Integer.parseInt(sittingcheck));
					intake_cap_another_u.setCentral_research_lab(Integer.parseInt(researchlabcheck));
					if(!ambulance_document.equals("")) {
						intake_cap_another_u.setAmbulance_document(ambulance_document);
						}
					
					intake_cap_another_u.setCasualty_dept(Integer.parseInt(casualtycheck));
					intake_cap_another_u.setModified_by((userid));
					intake_cap_another_u.setModified_date(date);
					sessionHQL.update(intake_cap_another_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx2.commit();
				}

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
		return "Data Submit Successfully";
	}
	
	
	// ====FETCH OTHER INFRASTRUCTURE DETAILS=====

	@RequestMapping(value = "admin/getOther_Infrastructure_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS> getOther_Infrastructure_Details(
			HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS where institute_id=:institute_id ");

		q.setParameter("institute_id", Integer.parseInt(institude));
		@SuppressWarnings("unchecked")
		List<CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS> clist = (List<CLG_REG_HOSP_OTHER_INFRASTRUCTURE_DETAILS>) q
				.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
		
		String extension=""; //add line
		String fname = ""; //add line
		
		request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
		
		MultipartFile file = mul;
		
		if (!file.getOriginalFilename().isEmpty()) {
			
			byte[] bytes = file.getBytes();
			String  mnhFilePath = session.getAttribute(id).toString();
			
	        File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();
					
			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j+1);
			}
			java.util.Date date1= new java.util.Date();
			fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
			
			File serverFile = new File(fname);	               
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);	                
			stream.close();
			

		}else {

		}
		return fname;
		
		}


}
