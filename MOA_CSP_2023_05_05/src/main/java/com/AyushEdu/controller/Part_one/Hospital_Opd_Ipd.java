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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_DETAILS_OF_LAND;
import com.AyushEdu.Models.Clg_Reg_hos_ipd_opd.CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL;
import com.AyushEdu.Models.Clg_Reg_hos_ipd_opd.CLG_REG_HOS_BED_EXISTED_TBL;
import com.AyushEdu.Models.Clg_Reg_hos_ipd_opd.CLG_REG_HOS_IPD_OPD;
import com.AyushEdu.Models.Clg_Reg_hos_ipd_opd.CLG_REG_HOS_IPD_PATIENTS_TBL;
import com.AyushEdu.Models.Clg_Reg_hos_ipd_opd.CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Hospital_Opd_Ipd {
	private static final String String = null;
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	private Clg_Reg_College_Infrastructure_DAO CIDao;
	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;

	@Autowired
	Commondao commondao;
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Dao CRDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;

	@RequestMapping(value = "hospital_ipdopd", method = RequestMethod.GET)
	public ModelAndView hospital_ipdopd(ModelMap Mmap, HttpSession session,
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
					.createQuery("select id from CLG_REG_HOS_IPD_OPD where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		Mmap.put("institude", institude);
		
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

		Mmap.put("getHosptal_opd_ipd_TableDepartment", CRDao.getHosptal_opd_ipd_TableDepartment());
		Mmap.put("getHospital_ipd_TableDepartment", CRDao.getHospital_ipd_TableDepartment());
		Mmap.put("getHospital_Bed_Days_Occupied_TableDepart", CRDao.getHospital_Bed_Days_Occupied_TableDepart());
		Mmap.put("getHospital_Bed_ExistedDepart", CRDao.getHospital_Bed_ExistedDepart());
		
		Mmap.put("getHosptal_opd_ipd_TableDepartmentFetch", CRDao.getHosptal_opd_ipd_TableDepartmentFetch(institude));
		Mmap.put("getHospital_ipd_patientTableDepart_Fetch", CRDao.getHospital_ipd_patientTableDepart_Fetch(institude));
		Mmap.put("getHospital_Bed_Days_Occupied_TableDepartFetch", CRDao.getHospital_Bed_Days_Occupied_TableDepartFetch(institude));
		Mmap.put("getHospital_Bed_ExistedDepartFetch", CRDao.getHospital_Bed_ExistedDepartFetch(institude));
		Mmap.put("getHosptal_opd_ipd_listDepartment", CRDao.getHosptal_opd_ipd_listDepartment());
		Mmap.put("getHosptal_opd_ipd_UploadDocumentsFetch", CRDao.getHosptal_opd_ipd_UploadDocumentsFetch(institude));
		//Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("dataforinstnc", ibdao.getinstName_Code(Integer.parseInt(institude)));
//		CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS Edit_Opd_Ipd_Upload_Doc_Details = CRDao.getHospital_Doc_upload(institude);
		
//		Mmap.put("getHosptal_opd_ipd_Upload_DocFetch", CRDao.getHosptal_opd_ipd_Upload_DocFetch(institude));
//		List<Map<String,Object>>list = CRDao.getHosptal_opd_ipd_Upload_DocFetch(institude);
//		Mmap.put("getHosptal_opd_ipd_Upload_DocFetch", CRDao.getHosptal_opd_ipd_Upload_DocFetch(institude));
		return new ModelAndView("hospital_ipdopd");
	}

	// SAVE Opd DETAILS
	@PostMapping(value = "/Hospital_ipd_opd_departAction")
	public @ResponseBody String Hospital_ipd_opd_departAction(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

//			if (request.getHeader("Referer") == null) {
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return "redirect:/login";
//			}
		String msg="";
		String role = session.getAttribute("role").toString();
//		System.out.println("role================="+role);
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		int p_id=Integer.parseInt(request.getParameter("ipd_opd_hidden"));
		try {

			CLG_REG_HOS_IPD_OPD pers_p = new CLG_REG_HOS_IPD_OPD();
			ArrayList<ArrayList<String>> list = CRDao.getHosptal_opd_ipd_listDepartment();
			for (int i = 0; i < list.size(); i++) {
//				System.out.println("i");
				
				
//				String hid_staff_info = request.getParameter("gm_hidden"+list.get(i).get(0));
//				String department_name = list.get(i).get(1);
//				String department_id = list.get(i).get(0);
				String gm_janopd = request.getParameter("gm_janopd" + list.get(i).get(0));
				String gm_febopd = request.getParameter("gm_febopd" + list.get(i).get(0));
				String gm_marchopd = request.getParameter("gm_marchopd" + list.get(i).get(0));
				String gm_aprilopd = request.getParameter("gm_aprilopd" + list.get(i).get(0));
				String gm_mayopd = request.getParameter("gm_mayopd" + list.get(i).get(0));
				String gm_juneopd = request.getParameter("gm_juneopd" + list.get(i).get(0));
				String gm_julyopd = request.getParameter("gm_julyopd" + list.get(i).get(0));
				String gm_augopd = request.getParameter("gm_augopd" + list.get(i).get(0));
				String gm_septopd = request.getParameter("gm_septopd" + list.get(i).get(0));

				String gm_octopd = request.getParameter("gm_octopd" + list.get(i).get(0));
				String gm_novopd = request.getParameter("gm_novopd" + list.get(i).get(0));
				String gm_decopd = request.getParameter("gm_decopd" + list.get(i).get(0));
				String gm_hidden = request.getParameter("gm_hidden" + list.get(i).get(0));
				
				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
				
				
				if (gm_janopd == null || gm_janopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients January Month"+list.get(i).get(1));
					return "Please Enter OPD Patients January Month"+list.get(i).get(1);
				}
				if (gm_febopd == null || gm_febopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients February Month"+list.get(i).get(1));
					return "Please Enter OPD Patients February Month"+list.get(i).get(1);
				}
				if (gm_marchopd == null || gm_marchopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients March Month"+list.get(i).get(1));
					return "Please Enter OPD Patients March Month"+list.get(i).get(1);
				}
				if (gm_aprilopd == null || gm_aprilopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients April Month"+list.get(i).get(1));
					return "Please Enter OPD Patients April Month"+list.get(i).get(1);
				}
				if (gm_mayopd == null || gm_mayopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients May Month"+list.get(i).get(1));
					return "Please Enter OPD Patients May Month"+list.get(i).get(1);
				}
				if (gm_juneopd == null || gm_juneopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients June Month"+list.get(i).get(1));
					return "Please Enter OPD Patients June Month"+list.get(i).get(1);
				}
				if (gm_julyopd == null || gm_julyopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients July Month"+list.get(i).get(1));
					return "Please Enter OPD Patients July Month"+list.get(i).get(1);
				}
				if (gm_augopd == null || gm_augopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients August Month"+list.get(i).get(1));
					return "Please Enter OPD Patients August Month"+list.get(i).get(1);
				}
				if (gm_septopd == null || gm_septopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients September Month"+list.get(i).get(1));
					return "Please Enter OPD Patients September Month"+list.get(i).get(1);
				}
				if (gm_octopd == null || gm_octopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients October Month"+list.get(i).get(1));
					return "Please Enter OPD Patients October Month"+list.get(i).get(1);
				}
				if (gm_novopd == null || gm_novopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients November Month"+list.get(i).get(1));
					return "Please Enter OPD Patients November Month"+list.get(i).get(1);
				}
				if (gm_decopd == null || gm_decopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter OPD Patients December Month"+list.get(i).get(1));
					return "Please Enter OPD Patients December Month"+list.get(i).get(1);
				}
				
				
//				String gm_janopd_mon = request.getParameter("gm_janopd_mon" + list.get(i).get(0));
//				String gm_febopd_mon = request.getParameter("gm_febopd_mon" + list.get(i).get(0));
//				String gm_marchopd_mon = request.getParameter("gm_marchopd_mon" + list.get(i).get(0));
//				String gm_aprilopd_mon = request.getParameter("gm_aprilopd_mon" + list.get(i).get(0));
//				String gm_mayopd_mon = request.getParameter("gm_mayopd_mon" + list.get(i).get(0));
//				String gm_juneopd_mon = request.getParameter("gm_juneopd_mon" + list.get(i).get(0));
//				String gm_julyopd_mon = request.getParameter("gm_julyopd_mon" + list.get(i).get(0));
//				String gm_augopd_mon = request.getParameter("gm_augopd_mon" + list.get(i).get(0));
//				String gm_septopd_mon = request.getParameter("gm_septopd_mon" + list.get(i).get(0));
//				String gm_octopd_mon = request.getParameter("gm_octopd_mon" + list.get(i).get(0));
//				String gm_novopd_mon = request.getParameter("gm_novopd_mon" + list.get(i).get(0));
//				String gm_decopd_mon = request.getParameter("gm_decopd_mon" + list.get(i).get(0));
//				
//				String grand_totalopd = request.getParameter("grand_totalopd" + list.get(i).get(0));
				String total_wardsopd = request.getParameter("total_wardsopd");
				
				if (total_wardsopd == null || total_wardsopd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Wards");
					return "Please Enter Total Wards";
				}
				pers_p.setP_id(1);
				pers_p.setSp_id(i);
				pers_p.setDep_id(Integer.parseInt(list.get(i).get(0)));
				pers_p.setJan(gm_janopd.equals("") ? 0 : Integer.parseInt(gm_janopd));
				pers_p.setFeb(gm_febopd.equals("") ? 0 : Integer.parseInt(gm_febopd));
				pers_p.setMar(gm_marchopd.equals("") ? 0 : Integer.parseInt(gm_marchopd));
				pers_p.setAppr(gm_aprilopd.equals("") ? 0 : Integer.parseInt(gm_aprilopd));
				pers_p.setMay(gm_mayopd.equals("") ? 0 : Integer.parseInt(gm_mayopd));
				pers_p.setJun(gm_juneopd.equals("") ? 0 : Integer.parseInt(gm_juneopd));
				pers_p.setJuly(gm_julyopd.equals("") ? 0 : Integer.parseInt(gm_julyopd));
				pers_p.setAug(gm_augopd.equals("") ? 0 : Integer.parseInt(gm_augopd));
				pers_p.setSep(gm_septopd.equals("") ? 0 : Integer.parseInt(gm_septopd));
				pers_p.setOct(gm_octopd.equals("") ? 0 : Integer.parseInt(gm_octopd));
				pers_p.setNov(gm_novopd.equals("") ? 0 : Integer.parseInt(gm_novopd));
				pers_p.setDec(gm_decopd.equals("") ? 0 : Integer.parseInt(gm_decopd));
				pers_p.setStatus(1);
//				pers_p.setTotal_wardsopd(Integer.parseInt(total_wardsopd));
//				pers_p.setTotal_wardsopd(Integer.parseInt(total_wardsopd));
				pers_p.setInstitute_id(Integer.parseInt(institute_id));
				pers_p.setTotal_wardsopd(Integer.parseInt(total_wardsopd));
				pers_p.setCreated_by(username);
				pers_p.setCreated_date(new Date());
//				if (Integer.parseInt(gm_hidden) == 0) {
				if (gm_hidden.equals("0")) {
					 sessionHQL.save(pers_p);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Saved Successfully";
				} else {
					CLG_REG_HOS_IPD_OPD pers_p1 = (CLG_REG_HOS_IPD_OPD) sessionHQL.get(CLG_REG_HOS_IPD_OPD.class,
							Integer.parseInt(gm_hidden));
					

					pers_p1.setP_id(1);
					pers_p1.setSp_id(i);
					pers_p1.setDep_id(Integer.parseInt(list.get(i).get(0)));
					pers_p1.setJan(gm_janopd.equals("") ? 0 : Integer.parseInt(gm_janopd));
					pers_p1.setFeb(gm_febopd.equals("") ? 0 : Integer.parseInt(gm_febopd));
					pers_p1.setMar(gm_marchopd.equals("") ? 0 : Integer.parseInt(gm_marchopd));
					pers_p1.setAppr(gm_aprilopd.equals("") ? 0 : Integer.parseInt(gm_aprilopd));
					pers_p1.setMay(gm_mayopd.equals("") ? 0 : Integer.parseInt(gm_mayopd));
					pers_p1.setJun(gm_juneopd.equals("") ? 0 : Integer.parseInt(gm_juneopd));
					pers_p1.setJuly(gm_julyopd.equals("") ? 0 : Integer.parseInt(gm_julyopd));
					pers_p1.setAug(gm_augopd.equals("") ? 0 : Integer.parseInt(gm_augopd));
					pers_p1.setSep(gm_septopd.equals("") ? 0 : Integer.parseInt(gm_septopd));
					pers_p1.setOct(gm_octopd.equals("") ? 0 : Integer.parseInt(gm_octopd));
					pers_p1.setNov(gm_novopd.equals("") ? 0 : Integer.parseInt(gm_novopd));
					pers_p1.setDec(gm_decopd.equals("") ? 0 : Integer.parseInt(gm_decopd));
					pers_p1.setStatus(1);
//					pers_p1.setInstitute_id(Integer.parseInt(institute_id));
					pers_p1.setTotal_wardsopd(Integer.parseInt(total_wardsopd));
					pers_p1.setModified_by(username);
					pers_p1.setModified_date(new Date());
					sessionHQL.update(pers_p1);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Updated Successfully";
				}

			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
	
	
	
	@PostMapping(value = "/Hospital_ipd_patients_formAction")
	public @ResponseBody String Hospital_ipd_patients_formAction(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

//			if (request.getHeader("Referer") == null) {
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return "redirect:/login";
//			}
		String msg = "";
		String role = session.getAttribute("role").toString();
//		System.out.println("role================="+role);
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

		CLG_REG_HOS_IPD_PATIENTS_TBL ipd = new CLG_REG_HOS_IPD_PATIENTS_TBL();
			ArrayList<ArrayList<String>> list = CRDao.getHosptal_opd_ipd_listDepartment();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("i");
//				String hid_staff_info = request.getParameter("gm_hidden"+list.get(i).get(0));
//				String department_name = list.get(i).get(1);
//				String department_id = list.get(i).get(0);
				String gm_janipd = request.getParameter("gm_janipd" + list.get(i).get(0));
				String gm_febipd = request.getParameter("gm_febipd" + list.get(i).get(0));
				String gm_marchipd = request.getParameter("gm_marchipd" + list.get(i).get(0));
				String gm_aprilipd = request.getParameter("gm_aprilipd" + list.get(i).get(0));
				String gm_mayipd = request.getParameter("gm_mayipd" + list.get(i).get(0));
				String gm_juneipd = request.getParameter("gm_juneipd" + list.get(i).get(0));
				String gm_julyipd = request.getParameter("gm_julyipd" + list.get(i).get(0));
				String gm_augipd = request.getParameter("gm_augipd" + list.get(i).get(0));
				String gm_septipd = request.getParameter("gm_septipd" + list.get(i).get(0));

				String gm_octipd = request.getParameter("gm_octipd" + list.get(i).get(0));
				String gm_novipd = request.getParameter("gm_novipd" + list.get(i).get(0));
				String gm_decipd = request.getParameter("gm_decipd" + list.get(i).get(0));
				String gm_ipd_hidden = request.getParameter("gm_ipd_hidden" + list.get(i).get(0));
				
				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);

				if (gm_janipd == null || gm_janipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients January Month"+list.get(i).get(1));
					return "Please Enter IPD Patients January Month"+list.get(i).get(1);
				}
				if (gm_febipd == null || gm_febipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients February Month"+list.get(i).get(1));
					return "Please Enter IPD Patients February Month"+list.get(i).get(1);
				}
				if (gm_marchipd == null || gm_marchipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients March Month"+list.get(i).get(1));
					return "Please Enter IPD Patients March Month"+list.get(i).get(1);
				}
				if (gm_aprilipd == null || gm_aprilipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients April Month"+list.get(i).get(1));
					return "Please Enter IPD Patients April Month"+list.get(i).get(1);
				}
				if (gm_mayipd == null || gm_mayipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients May Month"+list.get(i).get(1));
					return "Please Enter IPD Patients May Month"+list.get(i).get(1);
				}
				if (gm_juneipd == null || gm_juneipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients June Month"+list.get(i).get(1));
					return "Please Enter IPD Patients June Month"+list.get(i).get(1);
				}
				if (gm_julyipd == null || gm_julyipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients July Month"+list.get(i).get(1));
					return "Please Enter IPD Patients July Month"+list.get(i).get(1);
				}
				if (gm_augipd == null || gm_augipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients August Month"+list.get(i).get(1));
					return "Please Enter IPD Patients August Month"+list.get(i).get(1);
				}
				if (gm_septipd == null || gm_septipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients September Month"+list.get(i).get(1));
					return "Please Enter IPD Patients September Month"+list.get(i).get(1);
				}
				if (gm_octipd == null || gm_octipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients October Month"+list.get(i).get(1));
					return "Please Enter IPD Patients October Month"+list.get(i).get(1);
				}
				if (gm_novipd == null || gm_novipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients November Month"+list.get(i).get(1));
					return "Please Enter IPD Patients November Month"+list.get(i).get(1);
				}
				if (gm_decipd == null || gm_decipd.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter IPD Patients December Month"+list.get(i).get(1));
					return "Please Enter IPD Patients December Month"+list.get(i).get(1);
				}
				
//				String gm_janopd_mon = request.getParameter("gm_janopd_mon" + list.get(i).get(0));
//				String gm_febopd_mon = request.getParameter("gm_febopd_mon" + list.get(i).get(0));
//				String gm_marchopd_mon = request.getParameter("gm_marchopd_mon" + list.get(i).get(0));
//				String gm_aprilopd_mon = request.getParameter("gm_aprilopd_mon" + list.get(i).get(0));
//				String gm_mayopd_mon = request.getParameter("gm_mayopd_mon" + list.get(i).get(0));
//				String gm_juneopd_mon = request.getParameter("gm_juneopd_mon" + list.get(i).get(0));
//				String gm_julyopd_mon = request.getParameter("gm_julyopd_mon" + list.get(i).get(0));
//				String gm_augopd_mon = request.getParameter("gm_augopd_mon" + list.get(i).get(0));
//				String gm_septopd_mon = request.getParameter("gm_septopd_mon" + list.get(i).get(0));
//				String gm_octopd_mon = request.getParameter("gm_octopd_mon" + list.get(i).get(0));
//				String gm_novopd_mon = request.getParameter("gm_novopd_mon" + list.get(i).get(0));
//				String gm_decopd_mon = request.getParameter("gm_decopd_mon" + list.get(i).get(0));
//				
//				String grand_totalopd = request.getParameter("grand_totalopd" + list.get(i).get(0));
//				String total_wardsopd = request.getParameter("total_wardsopd");
				ipd.setP_id(1);
				ipd.setSp_id(i);
				ipd.setDep_id(Integer.parseInt(list.get(i).get(0)));
				ipd.setJan(gm_janipd.equals("") ? 0 : Integer.parseInt(gm_janipd));
				ipd.setFeb(gm_febipd.equals("") ? 0 : Integer.parseInt(gm_febipd));
				ipd.setMar(gm_marchipd.equals("") ? 0 : Integer.parseInt(gm_marchipd));
				ipd.setAppr(gm_aprilipd.equals("") ? 0 : Integer.parseInt(gm_aprilipd));
				ipd.setMay(gm_mayipd.equals("") ? 0 : Integer.parseInt(gm_mayipd));
				ipd.setJun(gm_juneipd.equals("") ? 0 : Integer.parseInt(gm_juneipd));
				ipd.setJuly(gm_julyipd.equals("") ? 0 : Integer.parseInt(gm_julyipd));
				ipd.setAug(gm_augipd.equals("") ? 0 : Integer.parseInt(gm_augipd));
				ipd.setSep(gm_septipd.equals("") ? 0 : Integer.parseInt(gm_septipd));
				ipd.setOct(gm_octipd.equals("") ? 0 : Integer.parseInt(gm_octipd));
				ipd.setNov(gm_novipd.equals("") ? 0 : Integer.parseInt(gm_novipd));
				ipd.setDec(gm_decipd.equals("") ? 0 : Integer.parseInt(gm_decipd));
				ipd.setStatus(1);
				ipd.setInstitute_id(Integer.parseInt(institute_id));
				ipd.setCreated_by(username);
				ipd.setCreated_date(new Date());
				if (gm_ipd_hidden.equals("0")) {
					 sessionHQL.save(ipd);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Saved Successfully";
				} else {
					CLG_REG_HOS_IPD_PATIENTS_TBL ipd1 = (CLG_REG_HOS_IPD_PATIENTS_TBL) sessionHQL.get(CLG_REG_HOS_IPD_PATIENTS_TBL.class,
							Integer.parseInt(gm_ipd_hidden));
					

					ipd1.setP_id(1);
					ipd1.setSp_id(i);
					ipd1.setDep_id(Integer.parseInt(list.get(i).get(0)));
					ipd1.setJan(gm_janipd.equals("") ? 0 : Integer.parseInt(gm_janipd));
					ipd1.setFeb(gm_febipd.equals("") ? 0 : Integer.parseInt(gm_febipd));
					ipd1.setMar(gm_marchipd.equals("") ? 0 : Integer.parseInt(gm_marchipd));
					ipd1.setAppr(gm_aprilipd.equals("") ? 0 : Integer.parseInt(gm_aprilipd));
					ipd1.setMay(gm_mayipd.equals("") ? 0 : Integer.parseInt(gm_mayipd));
					ipd1.setJun(gm_juneipd.equals("") ? 0 : Integer.parseInt(gm_juneipd));
					ipd1.setJuly(gm_julyipd.equals("") ? 0 : Integer.parseInt(gm_julyipd));
					ipd1.setAug(gm_augipd.equals("") ? 0 : Integer.parseInt(gm_augipd));
					ipd1.setSep(gm_septipd.equals("") ? 0 : Integer.parseInt(gm_septipd));
					ipd1.setOct(gm_octipd.equals("") ? 0 : Integer.parseInt(gm_octipd));
					ipd1.setNov(gm_novipd.equals("") ? 0 : Integer.parseInt(gm_novipd));
					ipd1.setDec(gm_decipd.equals("") ? 0 : Integer.parseInt(gm_decipd));
					ipd1.setStatus(1);
					ipd1.setInstitute_id(Integer.parseInt(institute_id));
					ipd1.setModified_by(username);
					ipd1.setModified_date(new Date());
					sessionHQL.update(ipd1);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Updated Successfully";
				}

			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
	@PostMapping(value = "/Bed_Days_Occupied_FormAction")
	public @ResponseBody String Bed_Days_Occupied_FormAction(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

//			if (request.getHeader("Referer") == null) {
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return "redirect:/login";
//			}
		String msg = "";
		String role = session.getAttribute("role").toString();
//		System.out.println("role================="+role);
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL bdo = new CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL();
			ArrayList<ArrayList<String>> list = CRDao.getHosptal_opd_ipd_listDepartment();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("i");
//				String hid_staff_info = request.getParameter("gm_hidden"+list.get(i).get(0));
//				String department_name = list.get(i).get(1);
//				String department_id = list.get(i).get(0);
				String gm_janbdo = request.getParameter("gm_janbdo" + list.get(i).get(0));
				String gm_febbdo = request.getParameter("gm_febbdo" + list.get(i).get(0));
				String gm_marchbdo = request.getParameter("gm_marchbdo" + list.get(i).get(0));
				String gm_aprilbdo = request.getParameter("gm_aprilbdo" + list.get(i).get(0));
				String gm_maybdo = request.getParameter("gm_maybdo" + list.get(i).get(0));
				String gm_junebdo = request.getParameter("gm_junebdo" + list.get(i).get(0));
				String gm_julybdo = request.getParameter("gm_julybdo" + list.get(i).get(0));
				String gm_augbdo = request.getParameter("gm_augbdo" + list.get(i).get(0));
				String gm_septbdo = request.getParameter("gm_septbdo" + list.get(i).get(0));

				String gm_octbdo = request.getParameter("gm_octbdo" + list.get(i).get(0));
				String gm_novbdo = request.getParameter("gm_novbdo" + list.get(i).get(0));
				String gm_decbdo = request.getParameter("gm_decbdo" + list.get(i).get(0));
				String gm_bdo_hidden = request.getParameter("gm_bdo_hidden" + list.get(i).get(0));
				
				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);

				
				if (gm_janbdo == null || gm_janbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied January Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied January Month"+list.get(i).get(1);
				}
				if (gm_febbdo == null || gm_febbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied February Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied February Month"+list.get(i).get(1);
				}
				if (gm_marchbdo == null || gm_marchbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied March Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied March Month"+list.get(i).get(1);
				}
				if (gm_aprilbdo == null || gm_aprilbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied April Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied April Month"+list.get(i).get(1);
				}
				if (gm_maybdo == null || gm_maybdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied May Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied May Month"+list.get(i).get(1);
				}
				if (gm_junebdo == null || gm_junebdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied June Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied June Month"+list.get(i).get(1);
				}
				if (gm_julybdo == null || gm_julybdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied July Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied July Month"+list.get(i).get(1);
				}
				if (gm_augbdo == null || gm_augbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied August Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied August Month"+list.get(i).get(1);
				}
				if (gm_septbdo == null || gm_septbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied September Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied September Month"+list.get(i).get(1);
				}
				if (gm_octbdo == null || gm_octbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied October Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied October Month"+list.get(i).get(1);
				}
				if (gm_novbdo == null || gm_novbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied November Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied November Month"+list.get(i).get(1);
				}
				if (gm_decbdo == null || gm_decbdo.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Bed Days Occupied December Month"+list.get(i).get(1));
					return "Please Enter Bed Days Occupied December Month"+list.get(i).get(1);
				}
				
//				String gm_janopd_mon = request.getParameter("gm_janopd_mon" + list.get(i).get(0));
//				String gm_febopd_mon = request.getParameter("gm_febopd_mon" + list.get(i).get(0));
//				String gm_marchopd_mon = request.getParameter("gm_marchopd_mon" + list.get(i).get(0));
//				String gm_aprilopd_mon = request.getParameter("gm_aprilopd_mon" + list.get(i).get(0));
//				String gm_mayopd_mon = request.getParameter("gm_mayopd_mon" + list.get(i).get(0));
//				String gm_juneopd_mon = request.getParameter("gm_juneopd_mon" + list.get(i).get(0));
//				String gm_julyopd_mon = request.getParameter("gm_julyopd_mon" + list.get(i).get(0));
//				String gm_augopd_mon = request.getParameter("gm_augopd_mon" + list.get(i).get(0));
//				String gm_septopd_mon = request.getParameter("gm_septopd_mon" + list.get(i).get(0));
//				String gm_octopd_mon = request.getParameter("gm_octopd_mon" + list.get(i).get(0));
//				String gm_novopd_mon = request.getParameter("gm_novopd_mon" + list.get(i).get(0));
//				String gm_decopd_mon = request.getParameter("gm_decopd_mon" + list.get(i).get(0));
//				
//				String grand_totalopd = request.getParameter("grand_totalopd" + list.get(i).get(0));
//				String total_wardsopd = request.getParameter("total_wardsopd");
				bdo.setP_id(1);
				bdo.setSp_id(i);
				bdo.setDep_id(Integer.parseInt(list.get(i).get(0)));
				bdo.setJan(gm_janbdo.equals("") ? 0 : Integer.parseInt(gm_janbdo));
				bdo.setFeb(gm_febbdo.equals("") ? 0 : Integer.parseInt(gm_febbdo));
				bdo.setMar(gm_marchbdo.equals("") ? 0 : Integer.parseInt(gm_marchbdo));
				bdo.setAppr(gm_aprilbdo.equals("") ? 0 : Integer.parseInt(gm_aprilbdo));
				bdo.setMay(gm_maybdo.equals("") ? 0 : Integer.parseInt(gm_maybdo));
				bdo.setJun(gm_junebdo.equals("") ? 0 : Integer.parseInt(gm_junebdo));
				bdo.setJuly(gm_julybdo.equals("") ? 0 : Integer.parseInt(gm_julybdo));
				bdo.setAug(gm_augbdo.equals("") ? 0 : Integer.parseInt(gm_augbdo));
				bdo.setSep(gm_septbdo.equals("") ? 0 : Integer.parseInt(gm_septbdo));
				bdo.setOct(gm_octbdo.equals("") ? 0 : Integer.parseInt(gm_octbdo));
				bdo.setNov(gm_novbdo.equals("") ? 0 : Integer.parseInt(gm_novbdo));
				bdo.setDec(gm_decbdo.equals("") ? 0 : Integer.parseInt(gm_decbdo));
				bdo.setStatus(1);
				bdo.setInstitute_id(Integer.parseInt(institute_id));
				bdo.setCreated_by(username);
				bdo.setCreated_date(new Date());
				if (gm_bdo_hidden.equals("0")) {
					 sessionHQL.save(bdo);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Saved Successfully";
				} else {
					CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL bdo1 = (CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL) sessionHQL.get(CLG_REG_HOS_BED_DAYS_OCCUPIED_TBL.class,
							Integer.parseInt(gm_bdo_hidden));
					

					bdo1.setP_id(1);
					bdo1.setSp_id(i);
					bdo1.setDep_id(Integer.parseInt(list.get(i).get(0)));
					bdo1.setJan(gm_janbdo.equals("") ? 0 : Integer.parseInt(gm_janbdo));
					bdo1.setFeb(gm_febbdo.equals("") ? 0 : Integer.parseInt(gm_febbdo));
					bdo1.setMar(gm_marchbdo.equals("") ? 0 : Integer.parseInt(gm_marchbdo));
					bdo1.setAppr(gm_aprilbdo.equals("") ? 0 : Integer.parseInt(gm_aprilbdo));
					bdo1.setMay(gm_maybdo.equals("") ? 0 : Integer.parseInt(gm_maybdo));
					bdo1.setJun(gm_junebdo.equals("") ? 0 : Integer.parseInt(gm_junebdo));
					bdo1.setJuly(gm_julybdo.equals("") ? 0 : Integer.parseInt(gm_julybdo));
					bdo1.setAug(gm_augbdo.equals("") ? 0 : Integer.parseInt(gm_augbdo));
					bdo1.setSep(gm_septbdo.equals("") ? 0 : Integer.parseInt(gm_septbdo));
					bdo1.setOct(gm_octbdo.equals("") ? 0 : Integer.parseInt(gm_octbdo));
					bdo1.setNov(gm_novbdo.equals("") ? 0 : Integer.parseInt(gm_novbdo));
					bdo1.setDec(gm_decbdo.equals("") ? 0 : Integer.parseInt(gm_decbdo));
					bdo1.setStatus(1);
					bdo1.setInstitute_id(Integer.parseInt(institute_id));
					bdo1.setModified_by(username);
					bdo1.setModified_date(new Date());
					sessionHQL.update(bdo1);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Updated Successfully";
				}

			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
	
	@PostMapping(value = "/Bed_Existed_FormAction")
	public @ResponseBody String Bed_Existed_FormAction(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

//			if (request.getHeader("Referer") == null) {
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return "redirect:/login";
//			}
		String msg = "";

		String role = session.getAttribute("role").toString();
//		System.out.println("role================="+role);
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			CLG_REG_HOS_BED_EXISTED_TBL bo = new CLG_REG_HOS_BED_EXISTED_TBL();
			ArrayList<ArrayList<String>> list = CRDao.getHosptal_opd_ipd_listDepartment();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("i");
//				String hid_staff_info = request.getParameter("gm_hidden"+list.get(i).get(0));
//				String department_name = list.get(i).get(1);
//				String department_id = list.get(i).get(0);
				String gm_existbed = request.getParameter("gm_existbed" + list.get(i).get(0));
				String gm_addionalbed = request.getParameter("gm_addionalbed" + list.get(i).get(0));
				String gm_totalbed = request.getParameter("gm_totalbed" + list.get(i).get(0));
				String grand_totalexisted = request.getParameter("grand_totalexisted");
				String bed_occupancy = request.getParameter("bed_occupancy");
				String gm_bo_hidden = request.getParameter("gm_bo_hidden" + list.get(i).get(0));
				
				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);

				
				if (gm_existbed == null || gm_existbed.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Existing Bed For UG"+list.get(i).get(1));
					return "Please Enter Existing Bed For UG"+list.get(i).get(1);
				}
				if (gm_addionalbed == null || gm_addionalbed.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Additional Bed for Existing PG"+list.get(i).get(1));
					return "Please Enter Additional Bed for Existing PG"+list.get(i).get(1);
				}
				if (gm_totalbed == null || gm_totalbed.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Beds"+list.get(i).get(1));
					return "Please Enter Total Available Beds"+list.get(i).get(1);
				}
				
				
				
				if (grand_totalexisted == null || grand_totalexisted.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Beds");
					return "Please Enter Total Available Beds";
				}
				if (bed_occupancy == null || bed_occupancy.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Available Beds");
					return "Please Enter Total Available Beds";
				}
//				String gm_janopd_mon = request.getParameter("gm_janopd_mon" + list.get(i).get(0));
//				String gm_febopd_mon = request.getParameter("gm_febopd_mon" + list.get(i).get(0));
//				String gm_marchopd_mon = request.getParameter("gm_marchopd_mon" + list.get(i).get(0));
//				String gm_aprilopd_mon = request.getParameter("gm_aprilopd_mon" + list.get(i).get(0));
//				String gm_mayopd_mon = request.getParameter("gm_mayopd_mon" + list.get(i).get(0));
//				String gm_juneopd_mon = request.getParameter("gm_juneopd_mon" + list.get(i).get(0));
//				String gm_julyopd_mon = request.getParameter("gm_julyopd_mon" + list.get(i).get(0));
//				String gm_augopd_mon = request.getParameter("gm_augopd_mon" + list.get(i).get(0));
//				String gm_septopd_mon = request.getParameter("gm_septopd_mon" + list.get(i).get(0));
//				String gm_octopd_mon = request.getParameter("gm_octopd_mon" + list.get(i).get(0));
//				String gm_novopd_mon = request.getParameter("gm_novopd_mon" + list.get(i).get(0));
//				String gm_decopd_mon = request.getParameter("gm_decopd_mon" + list.get(i).get(0));
//				
//				String grand_totalopd = request.getParameter("grand_totalopd" + list.get(i).get(0));
//				String total_wardsopd = request.getParameter("total_wardsopd");
				bo.setP_id(1);
				bo.setSp_id(i);
				bo.setDep_id(Integer.parseInt(list.get(i).get(0)));
				bo.setExistbed(gm_existbed.equals("") ? 0 : Integer.parseInt(gm_existbed));
				bo.setAddionalbed(gm_addionalbed.equals("") ? 0 : Integer.parseInt(gm_addionalbed));
				bo.setTotalbed(gm_totalbed.equals("") ? 0 : Integer.parseInt(gm_totalbed));
				bo.setGrand_totalexisted(Integer.parseInt(grand_totalexisted));
				bo.setBed_occupancy(Float.parseFloat(bed_occupancy));
				bo.setStatus(1);
				bo.setInstitute_id(Integer.parseInt(institute_id));
				bo.setCreated_by(username);
				bo.setCreated_date(new Date());
				if (gm_bo_hidden.equals("0")) {
					 sessionHQL.save(bo);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Saved Successfully";
 
				} else {
					CLG_REG_HOS_BED_EXISTED_TBL bo1 = (CLG_REG_HOS_BED_EXISTED_TBL) sessionHQL.get(CLG_REG_HOS_BED_EXISTED_TBL.class,
							Integer.parseInt(gm_bo_hidden));
					

					bo1.setP_id(1);
					bo1.setSp_id(i);
					bo1.setDep_id(Integer.parseInt(list.get(i).get(0)));
					bo1.setExistbed(gm_existbed.equals("") ? 0 : Integer.parseInt(gm_existbed));
					bo1.setAddionalbed(gm_addionalbed.equals("") ? 0 : Integer.parseInt(gm_addionalbed));
					bo1.setTotalbed(gm_totalbed.equals("") ? 0 : Integer.parseInt(gm_totalbed));
					bo1.setGrand_totalexisted(Integer.parseInt(grand_totalexisted));
					bo1.setBed_occupancy(Float.parseFloat(bed_occupancy));
					bo1.setStatus(1);
					bo1.setInstitute_id(Integer.parseInt(institute_id));
					bo1.setModified_by(username);
					bo1.setModified_date(new Date());
					sessionHQL.update(bo1);
					sessionHQL.flush();
					sessionHQL.clear();
					msg = "Data Updated Successfully";

				}

			}
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
	
//	@PostMapping(value = "/Hos_Upload_Document_FormAction")
//	public @ResponseBody String Hos_Upload_Document_FormAction(HttpServletRequest request, ModelMap model,
//			HttpSession session, Principal principal,
//			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
//			RedirectAttributes ra) throws ParseException, IOException {
//
////			if (request.getHeader("Referer") == null) {
////				session.invalidate();
////				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
////				return "redirect:/login";
////			}
//		String role = session.getAttribute("role").toString();
////		System.out.println("role================="+role);
//		String roleid1 = session.getAttribute("roleid").toString();
//		Date date = new Date();
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String username = principal.getName();
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		 String msg= "";
//		try {
//           
//			String upload_doc_hid=request.getParameter("upload_doc_hid");
//			String support_doc = gd(request, mul, session,"support_doc");
//			String xray_usg_opdipd = gd(request, mul, session,"xray_usg_opdipd");
//			String register_opdipd = gd(request, mul, session,"register_opdipd");
//			String medi_stock_opdipd = gd(request, mul, session,"medi_stock_opdipd");
//			String last_inveopdipd = gd(request, mul, session,"last_inveopdipd");
//			
////				String support_doc = request.getParameter("support_doc");
////				String xray_usg_opdipd = request.getParameter("xray_usg_opdipd");
////				String register_opdipd = request.getParameter("register_opdipd");
////				String medi_stock_opdipd = request.getParameter("medi_stock_opdipd");
////				String last_inveopdipd = request.getParameter("last_inveopdipd");
//				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
//
//				
////				String support_doc_u = gd(request, mul, session,"support_doc_hid");
////				String xray_usg_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
////				String register_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
////				String medi_stock_opdipd_u = gd(request, mul, session,"register_opdipd_hid");
////				String last_inveopdipd_u = gd(request, mul, session,"medi_stock_opdipd");	
////				if(upload_doc_hid.equals("0")){
//				if (support_doc.equals("")) {
//					ra.addAttribute("msg", "Please Upload Support Documents");
//					return "Please Upload Support Documents";
//				}
//				if (xray_usg_opdipd.equals("")) {
//					ra.addAttribute("msg", "Please Upload X-ray/USG Register of last two calendar year");
//					return "Please Upload X-ray/USG Register of last two calendar year";
//				}
//				if (register_opdipd.equals("")) {
//					ra.addAttribute("msg", "Please Upload OPD and IPD Register of last two calendar year");
//					return "Please Upload OPD and IPD Register of last two calendar year";
//				}
//				if (medi_stock_opdipd.equals("")) {
//					ra.addAttribute("msg", "Please Upload Medicine Stock register OPD and IPD of last two calendar year");
//					return "Please Upload Medicine Stock register OPD and IPD of last two calendar year";
//				}
//				if (last_inveopdipd.equals("")) {
//					ra.addAttribute("msg", "Please Upload Lab Investigation register for OPD and IPD of last two calendar year");
//					return "Please Upload Lab Investigation register for OPD and IPD of last two calendar year";
//				}
////				}
////				if(upload_doc_hid.equals("0")) {
//					if(upload_doc_hid.equals("0")) {
//						
//					
//					CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS rd =new CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS();
//					
//					rd.setSupport_doc(support_doc);
//					rd.setXray_usg_opdipd(xray_usg_opdipd);
//					rd.setRegister_opdipd(register_opdipd);
//					rd.setMedi_stock_opdipd(medi_stock_opdipd);
//					rd.setLast_inveopdipd(last_inveopdipd);
//					rd.setP_id(1);
//					rd.setSp_id(1);
//					rd.setDep_id(0);
//					rd.setStatus(1);
//					rd.setInstitute_id(Integer.parseInt(institute_id));
//				
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				rd.setInstitute_id(Integer.parseInt(institute_id));
////				rd.setP_id(p_id);
//				
//				rd.setCreated_by((userid));
//				rd.setCreated_date(date);
//			
//			   sessionHQL.save(rd);
//				msg = "Data Saved Successfully";
//				sessionHQL.flush();
//				sessionHQL.clear();
//			}
//				else {
//				
//					CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS urd = (CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS) 
//							sessionHQL.get(CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS.class,Integer.parseInt(upload_doc_hid));
////					System.err.println("support_doc_hid------------"+request.getParameter("support_doc_hid"));
//					
////					String support_doc_u=request.getParameter("support_doc_hid");
////					String xray_usg_opdipd_u=request.getParameter("xray_usg_opdipd_hid");
////					String register_opdipd_u=request.getParameter("register_opdipd");
////					String medi_stock_opdipd_u=request.getParameter("medi_stock_opdipd_hid");
////					String last_inveopdipd_u=request.getParameter("last_inveopdipd_hid");
//					
////					String support_doc_u = gd(request, mul, session,"support_doc_hid");
////					String xray_usg_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
////					String register_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
////					String medi_stock_opdipd_u = gd(request, mul, session,"register_opdipd_hid");
////					String last_inveopdipd_u = gd(request, mul, session,"medi_stock_opdipd");
//					
////					urd.setSupport_doc(support_doc_u);
////					urd.setXray_usg_opdipd(xray_usg_opdipd_u);
////					urd.setRegister_opdipd(register_opdipd_u);
////					urd.setMedi_stock_opdipd(medi_stock_opdipd_u);
////					urd.setLast_inveopdipd(last_inveopdipd_u);
//					
//					urd.setSupport_doc(support_doc);
//					urd.setXray_usg_opdipd(xray_usg_opdipd);
//					urd.setRegister_opdipd(register_opdipd);
//					urd.setMedi_stock_opdipd(medi_stock_opdipd);
//					urd.setLast_inveopdipd(last_inveopdipd);
//					urd.setP_id(1);
//					urd.setSp_id(1);
//					urd.setDep_id(0);
//					urd.setStatus(1);
//					urd.setInstitute_id(Integer.parseInt(institute_id));
//				
//				
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				urd.setInstitute_id(Integer.parseInt(institute_id));
//				
//				urd.setModified_by((userid));
//				urd.setModified_date(date);
//
//				sessionHQL.update(urd);
//				msg = "Data Updated Successfully";
//				sessionHQL.flush();
//				sessionHQL.clear();
//			}
//
//
//			tx.commit();
//		} catch (RuntimeException e) {
//			e.printStackTrace();
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
//		return msg;
//	}
	
	
	@PostMapping(value = "/Hos_Upload_Document_FormAction")
	public @ResponseBody String Hos_Upload_Document_FormAction(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) MultipartFile council_doc, MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

//			if (request.getHeader("Referer") == null) {
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return "redirect:/login";
//			}
		String role = session.getAttribute("role").toString();
//		System.out.println("role================="+role);
		String roleid1 = session.getAttribute("roleid").toString();
		Date date = new Date();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			int upload_doc_hid=Integer.parseInt(request.getParameter("upload_doc_hid"));
			String add_diet_ecg_doc = gd(request, mul, session,"add_diet_ecg_doc");
			String xray_usg_opdipd = gd(request, mul, session,"xray_usg_opdipd");
			String register_opdipd = gd(request, mul, session,"register_opdipd");
			String medi_stock_opdipd = gd(request, mul, session,"medi_stock_opdipd");
			String last_inveopdipd = gd(request, mul, session,"last_inveopdipd");
			
//				String support_doc = request.getParameter("support_doc");
//				String xray_usg_opdipd = request.getParameter("xray_usg_opdipd");
//				String register_opdipd = request.getParameter("register_opdipd");
//				String medi_stock_opdipd = request.getParameter("medi_stock_opdipd");
//				String last_inveopdipd = request.getParameter("last_inveopdipd");
				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);

				
//				String support_doc_u = gd(request, mul, session,"support_doc_hid");
//				String xray_usg_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
//				String register_opdipd_u = gd(request, mul, session,"xray_usg_opdipd_hid");
//				String medi_stock_opdipd_u = gd(request, mul, session,"register_opdipd_hid");
//				String last_inveopdipd_u = gd(request, mul, session,"medi_stock_opdipd");	

				if (add_diet_ecg_doc.equals("")) {
					ra.addAttribute("msg", "Please Upload Add Diet ECG Documents");
					return "Please Upload Add Diet ECG Documents";
				}
				if (xray_usg_opdipd.equals("")) {
					ra.addAttribute("msg", "Please Upload X-ray/USG Register of last two calendar year");
					return "Please Upload X-ray/USG Register of last two calendar year";
				}
				if (register_opdipd.equals("")) {
					ra.addAttribute("msg", "Please Upload OPD and IPD Register of last two calendar year");
					return "Please Upload OPD and IPD Register of last two calendar year";
				}
				if (medi_stock_opdipd.equals("")) {
					ra.addAttribute("msg", "Please Upload Medicine Stock register OPD and IPD of last two calendar year");
					return "Please Upload Medicine Stock register OPD and IPD of last two calendar year";
				}
				if (last_inveopdipd.equals("")) {
					ra.addAttribute("msg", "Please Upload Lab Investigation register for OPD and IPD of last two calendar year");
					return "Please Upload Lab Investigation register for OPD and IPD of last two calendar year";
				}
				if(upload_doc_hid==0) {
					
					CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS rd =new CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS();
					
					rd.setAdd_diet_ecg_doc(add_diet_ecg_doc);
					rd.setXray_usg_opdipd(xray_usg_opdipd);
					rd.setRegister_opdipd(register_opdipd);
					rd.setMedi_stock_opdipd(medi_stock_opdipd);
					rd.setLast_inveopdipd(last_inveopdipd);
					rd.setP_id(1);
					rd.setSp_id(1);
					rd.setDep_id(0);
					rd.setStatus(1);
					rd.setInstitute_id(Integer.parseInt(institute_id));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				rd.setInstitute_id(Integer.parseInt(institute_id));
//				rd.setP_id(p_id);
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
			
				
				upload_doc_hid = (int) sessionHQL.save(rd);
				ra.addAttribute("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
				
					CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS urd = (CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS) sessionHQL.get(CLG_REG_HOS_OPD_IPD_UPLOAD_DOCUMENTS.class,
						(upload_doc_hid));
					
					
					urd.setAdd_diet_ecg_doc(add_diet_ecg_doc);
					urd.setXray_usg_opdipd(xray_usg_opdipd);
					urd.setRegister_opdipd(register_opdipd);
					urd.setMedi_stock_opdipd(medi_stock_opdipd);
					urd.setLast_inveopdipd(last_inveopdipd);
					urd.setP_id(1);
					urd.setSp_id(1);
					urd.setDep_id(0);
					urd.setStatus(1);
					urd.setInstitute_id(Integer.parseInt(institute_id));
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				urd.setInstitute_id(Integer.parseInt(institute_id));
				
				urd.setModified_by((userid));
				urd.setModified_date(date);

				sessionHQL.update(urd);
				ra.addAttribute("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}


			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
	 
	 
	 public String gd(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
			
			System.err.println("id----"+id);
		
		String extension=""; //add line
		String fname = ""; //add line
		
		request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
		
		MultipartFile file = mul.getFile(id);
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
			fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
			
			File serverFile = new File(fname);	               
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);	                
			stream.close();

		}else {

			
		}
		return fname;
		
		}

}
