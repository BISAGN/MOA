package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_PROGRESS_OF_INSTITUTION;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_INVESTIGATION;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Other_Hospital_Details_DAO;
import com.AyushEdu.dao.Part_One.Clg_reg_College_Declaration_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Other_Hospital_Details_Controller {

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
	private Clg_Reg_College_Infrastructure_DAO CIDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	Clg_Reg_Other_Hospital_Details_DAO OHDao;
	
	@RequestMapping(value = "admin/otherhospital_detail", method = RequestMethod.GET)
	public ModelAndView college_staff_list(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
//		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		
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
		
		String role = session.getAttribute("role").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		Mmap.put("institude", institude);
		
		
		
		Mmap.put("GetMaintance_Record_Details", OHDao.GetMaintance_Record_Details(institute_id));
		Mmap.put("getLabour_Room_Details", OHDao.getLabour_Room_Details(institute_id));
		Mmap.put("getOperation_Theatre_Details", OHDao.getOperation_Theatre_Details(institute_id));
		Mmap.put("getInvestigation_Details", OHDao.getInvestigation_Details(institute_id));
		Mmap.put("getClinical_Laboratory_Details", OHDao.getClinical_Laboratory_Details(institute_id));
		Mmap.put("GetFunctionality_Details", OHDao.GetFunctionality_Details(institute_id));
		
		return new ModelAndView("otherhospital_detail");
	}
	
	//SAVE MAINTENANCE RECORDS DETAILS
	@PostMapping(value = "/Maintenance_Records_Details_Save_Draft_Action")
	public @ResponseBody String Maintenance_Records_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		
		String central_registration = request.getParameter("central_registration");
		String opd_records = request.getParameter("opd_records");
		String ipd_records = request.getParameter("ipd_records");
		String medical_record_room = request.getParameter("medical_record_room");
		String account_section = request.getParameter("account_section");
		String hid_maintenance_record = request.getParameter("hid_maintenance_record");
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		if (central_registration.equals("0")) {
			ra.addAttribute("msg", "Please Select Central Registration");
			return "Please Select Central Registration";
		}
		if (opd_records.equals("0")) {
			ra.addAttribute("msg", "Please Select OPD Records");
			return "Please Select OPD Records";
		}
		if (ipd_records.equals("0")) {
			ra.addAttribute("msg", "Please Select IPD Records");
			return "Please Select IPD Records";
		}
		if (medical_record_room.equals("0")) {
			ra.addAttribute("msg", "Please Select Medical Record Room");
			return "Please Select Medical Record Room";
		}
		if (account_section.equals("0")) {
			ra.addAttribute("msg", "Please Select Account Section");
			return "Please Select Account Section";
		}
		
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS maintenance_records_detail =new CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS();
			
			maintenance_records_detail.setCentral_registration(Integer.parseInt(central_registration));
			maintenance_records_detail.setOpd_records(Integer.parseInt(opd_records));
			maintenance_records_detail.setIpd_records(Integer.parseInt(ipd_records));
			maintenance_records_detail.setMedical_record_room(Integer.parseInt(medical_record_room));
			maintenance_records_detail.setAccount_section(Integer.parseInt(account_section));
			maintenance_records_detail.setInstitute_id(Integer.parseInt(institute_id));
			maintenance_records_detail.setS_id(Integer.parseInt(s_id));
			maintenance_records_detail.setCreated_by(Integer.parseInt(userid));
			maintenance_records_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_maintenance_record) == 0) {
					int hid_maintenance_record1= (Integer) sessionHQL.save(maintenance_records_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_maintenance_record1) ;
			}
				else {

					CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS maintenance_records_detail_u = (CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS.class, Integer.parseInt(hid_maintenance_record));
					
					maintenance_records_detail_u.setCentral_registration(Integer.parseInt(central_registration));
					maintenance_records_detail_u.setOpd_records(Integer.parseInt(opd_records));
					maintenance_records_detail_u.setIpd_records(Integer.parseInt(ipd_records));
					maintenance_records_detail_u.setMedical_record_room(Integer.parseInt(medical_record_room));
					maintenance_records_detail_u.setAccount_section(Integer.parseInt(account_section));
					maintenance_records_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					maintenance_records_detail_u.setS_id(Integer.parseInt(s_id));
					maintenance_records_detail_u.setModified_by(Integer.parseInt(userid));
					maintenance_records_detail_u.setModified_date(date);
					sessionHQL.update(maintenance_records_detail_u);
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
	
	//SAVE LABOUR ROOM DETAILS
	@PostMapping(value = "/Labour_Room_Details_Save_Draft_Action")
	public @ResponseBody String Labour_Room_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		
		String labour_room = request.getParameter("labour_room");
		String antenatal_room = request.getParameter("antenatal_room");
		String neonatal_care = request.getParameter("neonatal_care");
		String other_facilities = request.getParameter("other_facilities");
		String total_deliveries = request.getParameter("total_deliveries");
		String total_procedures = request.getParameter("total_procedures");
		String hid_labour_room = request.getParameter("hid_labour_room");
		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
		if(labour_room.equals("YES")) {
			if (total_deliveries == null || total_deliveries.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total number of Deliveries conducted in the last calendar year");
				return "Please Enter Total number of Deliveries conducted in the last calendar year";
			}
			if (validation.maxlengthcheck10(total_deliveries) == false) {
				ra.addAttribute("msg", "Total Deliveries" + validation.MaxlengthcheckMSG10);
				return "Total Deliveries " +validation.MaxlengthcheckMSG10;
			}
			if (validation.isOnlyNumber(total_deliveries) == false) {
				ra.addAttribute("msg","Total Deliveries" +validation.isOnlyNumberMSG);
				return "Total Deliveries " +validation.isOnlyNumberMSG;
			}
			if (total_procedures == null || total_procedures.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Number of other procedures conducted in the last calendar year");
				return "Please Enter Number of other procedures conducted in the last calendar year";
			}
			if (validation.maxlengthcheck10(total_procedures) == false) {
				ra.addAttribute("msg", "Total Procedures" + validation.MaxlengthcheckMSG10);
				return "Total Procedures " +validation.MaxlengthcheckMSG10;
			}
			if (validation.isOnlyNumber(total_procedures) == false) {
				ra.addAttribute("msg","Total Procedures" +validation.isOnlyNumberMSG);
				return "Total Procedures " +validation.isOnlyNumberMSG;
			}
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM labour_room_detail =new CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM();
			
			if(labour_room == null) {
				labour_room_detail.setS_id(Integer.parseInt(s_id));
				labour_room_detail.setCreated_by(Integer.parseInt(userid));
				labour_room_detail.setCreated_date(date);
			}else {
			labour_room_detail.setLabour_room(labour_room);
			if(labour_room.equals("YES")) {
				
				labour_room_detail.setAntenatal_room(antenatal_room);
				labour_room_detail.setNeonatal_care(neonatal_care);
				labour_room_detail.setOther_facilities(other_facilities);
				if(!total_deliveries.equals("")) {
					labour_room_detail.setTotal_deliveries(Integer.parseInt(total_deliveries));
				}else {
					labour_room_detail.setTotal_deliveries(0);
				}
				if(!total_procedures.equals("")) {
					labour_room_detail.setTotal_procedures(Integer.parseInt(total_procedures));
				}else {
					labour_room_detail.setTotal_procedures(0);
				}
			}else {
				labour_room_detail.setAntenatal_room("");
				labour_room_detail.setNeonatal_care("");
				labour_room_detail.setOther_facilities("");
				labour_room_detail.setTotal_deliveries(0);
				labour_room_detail.setTotal_procedures(0);
			}
			}
			labour_room_detail.setInstitute_id(Integer.parseInt(institute_id));
			labour_room_detail.setS_id(Integer.parseInt(s_id));
			labour_room_detail.setCreated_by(Integer.parseInt(userid));
			labour_room_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_labour_room) == 0) {
					labour_room_detail.setP_id(Integer.parseInt(p_id));
					int hid_maintenance_record1= (Integer) sessionHQL.save(labour_room_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_maintenance_record1) ;
			}
				else {

					CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM labour_room_detail_u = (CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM.class, Integer.parseInt(hid_labour_room));
					
					if(labour_room == null) {
						labour_room_detail_u.setS_id(Integer.parseInt(s_id));
						labour_room_detail_u.setModified_by(Integer.parseInt(userid));
						labour_room_detail_u.setModified_date(date);
					}else {
					labour_room_detail_u.setLabour_room(labour_room);
					if(labour_room.equals("YES")) {
						labour_room_detail_u.setAntenatal_room(antenatal_room);
						labour_room_detail_u.setNeonatal_care(neonatal_care);
						labour_room_detail_u.setOther_facilities(other_facilities);
						if(!total_deliveries.equals("")) {
							labour_room_detail_u.setTotal_deliveries(Integer.parseInt(total_deliveries));
						}else {
							labour_room_detail_u.setTotal_deliveries(0);
						}
						if(!total_procedures.equals("")) {
							labour_room_detail_u.setTotal_procedures(Integer.parseInt(total_procedures));
						}else {
							labour_room_detail_u.setTotal_procedures(0);
						}
					}else {
						labour_room_detail_u.setAntenatal_room("");
						labour_room_detail_u.setNeonatal_care("");
						labour_room_detail_u.setOther_facilities("");
						labour_room_detail_u.setTotal_deliveries(0);
						labour_room_detail_u.setTotal_procedures(0);
					}
					}
					labour_room_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					labour_room_detail_u.setS_id(Integer.parseInt(s_id));
					labour_room_detail_u.setModified_by(Integer.parseInt(userid));
					labour_room_detail_u.setModified_date(date);
					sessionHQL.update(labour_room_detail_u);
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
	
	
	//SAVE OPERATION THEATRE DETAILS
	@PostMapping(value = "/Operation_Theatre_Details_Save_Draft_Action")
	public @ResponseBody String Operation_Theatre_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		
		String operation_theatre = request.getParameter("operation_theatre");
		String air_condition = request.getParameter("air_condition");
		String pre_operative_room = request.getParameter("pre_operative_room");
		String sterilization_room = request.getParameter("sterilization_room");
		String wash_room = request.getParameter("wash_room");
		String other_facility = request.getParameter("other_facility");
		String fumigation_facility = request.getParameter("fumigation_facility");
		String total_operations = request.getParameter("total_operations");
		String total_minor_procedures = request.getParameter("total_minor_procedures");
		String hid_operation_theatre = request.getParameter("hid_operation_theatre");
		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		
		if(operation_theatre.equals("YES")) {
			if (total_operations == null || total_operations.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Number of operations done in the last calendar year");
				return "Please Enter Total Number of operations done in the last calendar year";
			}
			if (validation.maxlengthcheck10(total_operations) == false) {
				ra.addAttribute("msg", "Total Operations" + validation.MaxlengthcheckMSG10);
				return "Total Operations " +validation.MaxlengthcheckMSG10;
			}
			if (validation.isOnlyNumber(total_operations) == false) {
				ra.addAttribute("msg","Total Operations" +validation.isOnlyNumberMSG);
				return "Total Operations " +validation.isOnlyNumberMSG;
			}
			if (total_minor_procedures == null || total_minor_procedures.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total number of minor procedures done in last calender year");
				return "Please Enter Total number of minor procedures done in last calender year";
			}
			if (validation.maxlengthcheck10(total_minor_procedures) == false) {
				ra.addAttribute("msg", "Total Minor Procedures" + validation.MaxlengthcheckMSG10);
				return "Total Minor Procedures " +validation.MaxlengthcheckMSG10;
			}
			if (validation.isOnlyNumber(total_minor_procedures) == false) {
				ra.addAttribute("msg","Total Minor Procedures" +validation.isOnlyNumberMSG);
				return "Total Minor Procedures " +validation.isOnlyNumberMSG;
			}
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE operation_theatre_detail =new CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE();
			
			if(operation_theatre == null) {
				
				operation_theatre_detail.setS_id(Integer.parseInt(s_id));
				operation_theatre_detail.setCreated_by(Integer.parseInt(userid));
				operation_theatre_detail.setCreated_date(date);
				
			}else {
			operation_theatre_detail.setOperation_theatre(operation_theatre);
			if(operation_theatre.equals("YES")) {
				
				operation_theatre_detail.setAir_condition(air_condition);
				operation_theatre_detail.setPre_operative_room(pre_operative_room);
				operation_theatre_detail.setSterilization_room(sterilization_room);
				operation_theatre_detail.setWash_room(wash_room);
				operation_theatre_detail.setOther_facility(other_facility);
				operation_theatre_detail.setFumigation_facility(fumigation_facility);
				if(!total_operations.equals("")) {
					operation_theatre_detail.setTotal_operations(Integer.parseInt(total_operations));
				}else {
					operation_theatre_detail.setTotal_operations(0);
				}
				if(!total_minor_procedures.equals("")) {
					operation_theatre_detail.setTotal_minor_procedures(Integer.parseInt(total_minor_procedures));
				}else {
					operation_theatre_detail.setTotal_minor_procedures(0);
				}
			}else {
				operation_theatre_detail.setAir_condition("");
				operation_theatre_detail.setPre_operative_room("");
				operation_theatre_detail.setSterilization_room("");
				operation_theatre_detail.setWash_room("");
				operation_theatre_detail.setOther_facility("");
				operation_theatre_detail.setFumigation_facility("");
				operation_theatre_detail.setTotal_operations(0);
				operation_theatre_detail.setTotal_minor_procedures(0);
			}
			}
			operation_theatre_detail.setInstitute_id(Integer.parseInt(institute_id));
			operation_theatre_detail.setS_id(Integer.parseInt(s_id));
			operation_theatre_detail.setCreated_by(Integer.parseInt(userid));
			operation_theatre_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_operation_theatre) == 0) {
					operation_theatre_detail.setP_id(Integer.parseInt(p_id));
					int hid_operation_theatre1= (Integer) sessionHQL.save(operation_theatre_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_operation_theatre1) ;
			}
				else {

					CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE operation_theatre_detail_u = (CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_OPERATION_THEATRE.class, Integer.parseInt(hid_operation_theatre));
					
					if(operation_theatre == null) {
						
						operation_theatre_detail_u.setS_id(Integer.parseInt(s_id));
						operation_theatre_detail_u.setModified_by(Integer.parseInt(userid));
						operation_theatre_detail_u.setModified_date(date);
						
					}else {
					operation_theatre_detail_u.setOperation_theatre(operation_theatre);
					if(operation_theatre.equals("YES")) {
						operation_theatre_detail_u.setAir_condition(air_condition);
						operation_theatre_detail_u.setPre_operative_room(pre_operative_room);
						operation_theatre_detail_u.setSterilization_room(sterilization_room);
						operation_theatre_detail_u.setWash_room(wash_room);
						operation_theatre_detail_u.setOther_facility(other_facility);
						operation_theatre_detail_u.setFumigation_facility(fumigation_facility);
						if(!total_operations.equals("")) {
							operation_theatre_detail_u.setTotal_operations(Integer.parseInt(total_operations));
						}else {
							operation_theatre_detail_u.setTotal_operations(0);
						}
						if(!total_minor_procedures.equals("")) {
							operation_theatre_detail_u.setTotal_minor_procedures(Integer.parseInt(total_minor_procedures));
						}else {
							operation_theatre_detail_u.setTotal_minor_procedures(0);
						}
					}else {
						operation_theatre_detail_u.setAir_condition("");
						operation_theatre_detail_u.setPre_operative_room("");
						operation_theatre_detail_u.setSterilization_room("");
						operation_theatre_detail_u.setWash_room("");
						operation_theatre_detail_u.setOther_facility("");
						operation_theatre_detail_u.setFumigation_facility("");
						operation_theatre_detail_u.setTotal_operations(0);
						operation_theatre_detail_u.setTotal_minor_procedures(0);
					}
					}
					operation_theatre_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					operation_theatre_detail_u.setS_id(Integer.parseInt(s_id));
					operation_theatre_detail_u.setModified_by(Integer.parseInt(userid));
					operation_theatre_detail_u.setModified_date(date);
					sessionHQL.update(operation_theatre_detail_u);
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
	
	
	//SAVE INVESTIGATION DETAILS
	@PostMapping(value = "/Investigation_Details_Save_Draft_Action")
	public @ResponseBody String Investigation_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		
		String total_xray = request.getParameter("total_xray");
		String total_ecg = request.getParameter("total_ecg");
		String total_usg = request.getParameter("total_usg");
		String hid_investigation = request.getParameter("hid_investigation");
		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		if (total_xray == null || total_xray.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total number of X-rays done");
			return "Please Enter Total number of X-rays done";
		}
		if (validation.maxlengthcheck10(total_xray) == false) {
			ra.addAttribute("msg", "Total X-rays" + validation.MaxlengthcheckMSG10);
			return "Total X-rays " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(total_xray) == false) {
			ra.addAttribute("msg","Total X-rays" +validation.isOnlyNumberMSG);
			return "Total X-rays " +validation.isOnlyNumberMSG;
		}
		if (total_ecg == null || total_ecg.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total number of ECG done");
			return "Please Enter Total number of ECG done";
		}
		if (validation.maxlengthcheck10(total_ecg) == false) {
			ra.addAttribute("msg", "Total ECG" + validation.MaxlengthcheckMSG10);
			return "Total ECG " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(total_ecg) == false) {
			ra.addAttribute("msg","Total ECG" +validation.isOnlyNumberMSG);
			return "Total ECG " +validation.isOnlyNumberMSG;
		}
		if (total_usg == null || total_usg.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total number of USG done");
			return "Please Enter Total number of USG done";
		}
		if (validation.maxlengthcheck10(total_usg) == false) {
			ra.addAttribute("msg", "Total USG" + validation.MaxlengthcheckMSG10);
			return "Total USG " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(total_usg) == false) {
			ra.addAttribute("msg","Total USG" +validation.isOnlyNumberMSG);
			return "Total USG " +validation.isOnlyNumberMSG;
		}
		
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_INVESTIGATION investigation_detail =new CLG_REG_OTHER_HOS_DTL_INVESTIGATION();
			
				
			if(!total_xray.equals("")) {	
				investigation_detail.setTotal_xray(Integer.parseInt(total_xray));
			}else {
				investigation_detail.setTotal_xray(0);
			}
			if(!total_ecg.equals("")) {
				investigation_detail.setTotal_ecg(Integer.parseInt(total_ecg));
			}else {
				investigation_detail.setTotal_ecg(0);
			}
			if(!total_usg.equals("")) {
				investigation_detail.setTotal_usg(Integer.parseInt(total_usg));
			}else {
				investigation_detail.setTotal_usg(0);
			}
			investigation_detail.setInstitute_id(Integer.parseInt(institute_id));
			investigation_detail.setS_id(Integer.parseInt(s_id));
			investigation_detail.setCreated_by(Integer.parseInt(userid));
			investigation_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_investigation) == 0) {
					investigation_detail.setP_id(Integer.parseInt(p_id));
					int hid_investigation1= (Integer) sessionHQL.save(investigation_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_investigation1);
			}
				else {

					CLG_REG_OTHER_HOS_DTL_INVESTIGATION investigation_detail_u = (CLG_REG_OTHER_HOS_DTL_INVESTIGATION) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_INVESTIGATION.class, Integer.parseInt(hid_investigation));
					
						
					if(!total_xray.equals("")) {	
						investigation_detail_u.setTotal_xray(Integer.parseInt(total_xray));
					}else {
						investigation_detail_u.setTotal_xray(0);
					}
					if(!total_ecg.equals("")) {
						investigation_detail_u.setTotal_ecg(Integer.parseInt(total_ecg));
					}else {
						investigation_detail_u.setTotal_ecg(0);
					}
					if(!total_usg.equals("")) {
						investigation_detail_u.setTotal_usg(Integer.parseInt(total_usg));
					}else {
						investigation_detail_u.setTotal_usg(0);
					}
					investigation_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					investigation_detail_u.setS_id(Integer.parseInt(s_id));
					investigation_detail_u.setModified_by(Integer.parseInt(userid));
					investigation_detail_u.setModified_date(date);
					sessionHQL.update(investigation_detail_u);
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
	
	
	//SAVE CLINICAL LABORATORY DETAILS
	@PostMapping(value = "/Clinical_Laboratory_Details_Save_Draft_Action")
	public @ResponseBody String Clinical_Laboratory_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String hematological_test = request.getParameter("hematological_test");
		String bio_chemical_test = request.getParameter("bio_chemical_test");
		String serological_test = request.getParameter("serological_test");
		String microbiological_test = request.getParameter("microbiological_test");
		String orthology_test = request.getParameter("orthology_test");
		String total_investigation = request.getParameter("total_investigation");
		String hid_clinical_laboratory = request.getParameter("hid_clinical_laboratory");
		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		if (hematological_test == null || hematological_test.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Hematological Tests");
			return "Please Enter Total Number of Hematological Tests";
		}
		if (validation.maxlengthcheck10(hematological_test) == false) {
			ra.addAttribute("msg", "Total Hematological Tests" + validation.MaxlengthcheckMSG10);
			return "Total Hematological Tests " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(hematological_test) == false) {
			ra.addAttribute("msg","Total Hematological Tests" +validation.isOnlyNumberMSG);
			return "Total Hematological Tests " +validation.isOnlyNumberMSG;
		}
		if (bio_chemical_test == null || bio_chemical_test.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Biochemical Tests");
			return "Please Enter Total Number of Biochemical Tests";
		}
		if (validation.maxlengthcheck10(bio_chemical_test) == false) {
			ra.addAttribute("msg", "Total Bio-Chemical Tests" + validation.MaxlengthcheckMSG10);
			return "Total Bio-Chemical Tests " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(bio_chemical_test) == false) {
			ra.addAttribute("msg","Total Bio-Chemical Tests" +validation.isOnlyNumberMSG);
			return "Total Bio-Chemical Tests " +validation.isOnlyNumberMSG;
		}
		if (serological_test == null || serological_test.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Serological Tests");
			return "Please Enter Total Number of Serological Tests";
		}
		if (validation.maxlengthcheck10(serological_test) == false) {
			ra.addAttribute("msg", "Total Serological Test" + validation.MaxlengthcheckMSG10);
			return "Total Serological Test " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(serological_test) == false) {
			ra.addAttribute("msg","Total Serological Test" +validation.isOnlyNumberMSG);
			return "Total Serological Test " +validation.isOnlyNumberMSG;
		}
		if (microbiological_test == null || microbiological_test.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Microbiological Tests");
			return "Please Enter Total Number of Microbiological Tests";
		}
		if (validation.maxlengthcheck10(microbiological_test) == false) {
			ra.addAttribute("msg", "Total Microbiological Tests" + validation.MaxlengthcheckMSG10);
			return "Total Microbiological Tests " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(microbiological_test) == false) {
			ra.addAttribute("msg","Total Microbiological Tests" +validation.isOnlyNumberMSG);
			return "Total Microbiological Tests " +validation.isOnlyNumberMSG;
		}
		if (orthology_test == null || orthology_test.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Orthology Tests");
			return "Please Enter Total Number of Orthology Tests";
		}
		if (validation.maxlengthcheck10(orthology_test) == false) {
			ra.addAttribute("msg", "Total Orthology Tests" + validation.MaxlengthcheckMSG10);
			return "Total Orthology Tests " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(orthology_test) == false) {
			ra.addAttribute("msg","Total Orthology Tests" +validation.isOnlyNumberMSG);
			return "Total Orthology Tests " +validation.isOnlyNumberMSG;
		}
		if (total_investigation == null || total_investigation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Number of Investigation Tests");
			return "Please Enter Total Number of Investigation Tests";
		}
		if (validation.maxlengthcheck10(total_investigation) == false) {
			ra.addAttribute("msg", "Total Investigation" + validation.MaxlengthcheckMSG10);
			return "Total Investigation " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(total_investigation) == false) {
			ra.addAttribute("msg","Total Investigation" +validation.isOnlyNumberMSG);
			return "Total Investigation " +validation.isOnlyNumberMSG;
		}
		
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY clinical_laboratory_detail =new CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY();
			
				
			if(!hematological_test.equals("")) {	
				clinical_laboratory_detail.setHematological_test(Integer.parseInt(hematological_test));
			}else {
				clinical_laboratory_detail.setHematological_test(0);
			}
			if(!bio_chemical_test.equals("")) {
				clinical_laboratory_detail.setBio_chemical_test(Integer.parseInt(bio_chemical_test));
			}else {
				clinical_laboratory_detail.setBio_chemical_test(0);
			}
			if(!serological_test.equals("")) {
				clinical_laboratory_detail.setSerological_test(Integer.parseInt(serological_test));
			}else {
				clinical_laboratory_detail.setSerological_test(0);
			}
			if(!microbiological_test.equals("")) {	
				clinical_laboratory_detail.setMicrobiological_test(Integer.parseInt(microbiological_test));
			}else {
				clinical_laboratory_detail.setMicrobiological_test(0);
			}
			if(!orthology_test.equals("")) {
				clinical_laboratory_detail.setOrthology_test(Integer.parseInt(orthology_test));
			}else {
				clinical_laboratory_detail.setOrthology_test(0);
			}
			if(!total_investigation.equals("")) {
				clinical_laboratory_detail.setTotal_investigation(Integer.parseInt(total_investigation));
			}else {
				clinical_laboratory_detail.setTotal_investigation(0);
			}
			clinical_laboratory_detail.setInstitute_id(Integer.parseInt(institute_id));
			clinical_laboratory_detail.setS_id(Integer.parseInt(s_id));
			clinical_laboratory_detail.setCreated_by(Integer.parseInt(userid));
			clinical_laboratory_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_clinical_laboratory) == 0) {
					clinical_laboratory_detail.setP_id(Integer.parseInt(p_id));
					int hid_clinical_laboratory1= (Integer) sessionHQL.save(clinical_laboratory_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_clinical_laboratory1);
			}
				else {

					CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY clinical_laboratory_detail_u = (CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_CLINICAL_LABORATORY.class, Integer.parseInt(hid_clinical_laboratory));
					
						
					if(!hematological_test.equals("")) {	
						clinical_laboratory_detail_u.setHematological_test(Integer.parseInt(hematological_test));
					}else {
						clinical_laboratory_detail_u.setHematological_test(0);
					}
					if(!bio_chemical_test.equals("")) {
						clinical_laboratory_detail_u.setBio_chemical_test(Integer.parseInt(bio_chemical_test));
					}else {
						clinical_laboratory_detail_u.setBio_chemical_test(0);
					}
					if(!serological_test.equals("")) {
						clinical_laboratory_detail_u.setSerological_test(Integer.parseInt(serological_test));
					}else {
						clinical_laboratory_detail_u.setSerological_test(0);
					}
					if(!microbiological_test.equals("")) {	
						clinical_laboratory_detail_u.setMicrobiological_test(Integer.parseInt(microbiological_test));
					}else {
						clinical_laboratory_detail_u.setMicrobiological_test(0);
					}
					if(!orthology_test.equals("")) {
						clinical_laboratory_detail_u.setOrthology_test(Integer.parseInt(orthology_test));
					}else {
						clinical_laboratory_detail_u.setOrthology_test(0);
					}
					if(!total_investigation.equals("")) {
						clinical_laboratory_detail_u.setTotal_investigation(Integer.parseInt(total_investigation));
					}else {
						clinical_laboratory_detail_u.setTotal_investigation(0);
					}
					clinical_laboratory_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					clinical_laboratory_detail_u.setS_id(Integer.parseInt(s_id));
					clinical_laboratory_detail_u.setModified_by(Integer.parseInt(userid));
					clinical_laboratory_detail_u.setModified_date(date);
					sessionHQL.update(clinical_laboratory_detail_u);
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
	
	//SAVE VERIFICATION FUNCTIONALITY DETAILS
	@PostMapping(value = "/Functionality_Details_Save_Draft_Action")
	public @ResponseBody String Functionality_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "doc_of_multispecialty_hospital", required = false) MultipartFile multispecialty_hospital_document,
			@RequestParam(value = "ipd_diet_register_doc", required = false) MultipartFile ipd_diet_register_document,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String doc_of_multispecialty_hospital = "doc_of_multispecialty_hospital";
		String ipd_diet_register_doc = "ipd_diet_register_doc";
		
		String name_of_opd = request.getParameter("name_of_opd");
		String name_of_opd_remark = request.getParameter("name_of_opd_remark");
		String opd_case_register = request.getParameter("opd_case_register");
		String opd_case_register_remark = request.getParameter("opd_case_register_remark");
		String opd_medicine = request.getParameter("opd_medicine");
		String opd_medicine_remark = request.getParameter("opd_medicine_remark");
		String case_receipt_opd = request.getParameter("case_receipt_opd");
		String case_receipt_opd_remark = request.getParameter("case_receipt_opd_remark");
		String name_of_ipd = request.getParameter("name_of_ipd");
		String name_of_ipd_remark = request.getParameter("name_of_ipd_remark");
		String ipd_case_sheets = request.getParameter("ipd_case_sheets");
		String ipd_case_sheets_remark = request.getParameter("ipd_case_sheets_remark");
		String discharge_cards = request.getParameter("discharge_cards");
		String discharge_cards_remark = request.getParameter("discharge_cards_remark");
		String ip_medicine = request.getParameter("ip_medicine");
		String ip_medicine_remark = request.getParameter("ip_medicine_remark");
		String nursing_duty_roster = request.getParameter("nursing_duty_roster");
		String nursing_duty_roster_remark = request.getParameter("nursing_duty_roster_remark");
		String doctor_duty_roaster = request.getParameter("doctor_duty_roaster");
		String doctor_duty_roaster_remark = request.getParameter("doctor_duty_roaster_remark");
		String ip_diet_register = request.getParameter("ip_diet_register");
		String ip_diet_register_remark = request.getParameter("ip_diet_register_remark");
		String cash_receipt_ipd = request.getParameter("cash_receipt_ipd");
		String cash_receipt_ipd_remark = request.getParameter("cash_receipt_ipd_remark");
		String name_of_multispecialty_hospital = request.getParameter("name_of_multispecialty_hospital");
		String address_of_multispecialty_hospital = request.getParameter("address_of_multispecialty_hospital");
		String date_of_mou_sign = request.getParameter("date_of_mou_sign");
		String validity_of_mou = request.getParameter("validity_of_mou");
		String area_of_mou = request.getParameter("area_of_mou");
		String investigation_of_hospital = request.getParameter("investigation_of_hospital");
		String training_details = request.getParameter("training_details");
		String hid_verification_functionality = request.getParameter("hid_verification_functionality");
		String p_id = OHDao.getp_idfromuser_id(userid).get(0).get(0);
		
		if (!multispecialty_hospital_document.isEmpty()) {
			doc_of_multispecialty_hospital = upload_imagemethod(request,multispecialty_hospital_document,session, doc_of_multispecialty_hospital);
		}
		else {
			doc_of_multispecialty_hospital = request.getParameter("hid_doc_of_multispecialty_hospital");
		}
		if (!ipd_diet_register_document.isEmpty()) {
			ipd_diet_register_doc = upload_imagemethod(request,ipd_diet_register_document,session, ipd_diet_register_doc);
		}
		else {
			ipd_diet_register_doc = request.getParameter("hid_ipd_diet_register_doc");
		}
		
		
		if (name_of_opd.equals("0")) {
			ra.addAttribute("msg", "Please Select Name of OPD");
			return "Please Select Name of OPD";
		}
		if (name_of_opd_remark == null || name_of_opd_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Name of OPD Remarks");
			return "Please Enter Name of OPD Remarks";
		}
		if (validation.maxlengthcheck100(name_of_opd_remark) == false) {
			ra.addAttribute("msg", "Name of OPD Remarks" + validation.MaxlengthcheckMSG100);
			return "Name of OPD Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(name_of_opd_remark) == false) {
			ra.addAttribute("msg","Name of OPD Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Name of OPD Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (opd_case_register.equals("0")) {
			ra.addAttribute("msg", "Please Select OPD Case Register");
			return "Please Select OPD Case Register";
		}
		if (opd_case_register_remark == null || opd_case_register_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter OPD Case Register Remarks");
			return "Please Enter OPD Case Register Remarks";
		}
		if (validation.maxlengthcheck100(opd_case_register_remark) == false) {
			ra.addAttribute("msg", "OPD Case Register Remarks" + validation.MaxlengthcheckMSG100);
			return "OPD Case Register Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(opd_case_register_remark) == false) {
			ra.addAttribute("msg","OPD Case Register Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "OPD Case Register Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (opd_medicine.equals("0")) {
			ra.addAttribute("msg", "Please Select OPD Medicine");
			return "Please Select OPD Medicine";
		}
		if (opd_medicine_remark == null || opd_medicine_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter OPD Medicine Remarks");
			return "Please Enter OPD Medicine Remarks";
		}
		if (validation.maxlengthcheck100(opd_medicine_remark) == false) {
			ra.addAttribute("msg", "OPD Medicine Remarks " + validation.MaxlengthcheckMSG100);
			return "OPD Medicine Remarks  " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(opd_medicine_remark) == false) {
			ra.addAttribute("msg","OPD Medicine Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "OPD Medicine Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (case_receipt_opd.equals("0")) {
			ra.addAttribute("msg", "Please Select Cash Receipt OPD");
			return "Please Select Cash Receipt OPD";
		}
		if (case_receipt_opd_remark == null || case_receipt_opd_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Cash Receipt OPD Remarks");
			return "Please Enter Cash Receipt OPD Remarks";
		}
		if (validation.maxlengthcheck100(case_receipt_opd_remark) == false) {
			ra.addAttribute("msg", "Cash Receipt OPD Remarks" + validation.MaxlengthcheckMSG100);
			return "Cash Receipt OPD Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(case_receipt_opd_remark) == false) {
			ra.addAttribute("msg","Cash Receipt OPD Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Cash Receipt OPD Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (name_of_ipd.equals("0")) {
			ra.addAttribute("msg", "Please Select Name of IPD");
			return "Please Select Name of IPD";
		}
		if (name_of_ipd_remark == null || name_of_ipd_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Name of IPD Remarks");
			return "Please Enter Name of IPD Remarks";
		}
		if (validation.maxlengthcheck100(name_of_ipd_remark) == false) {
			ra.addAttribute("msg", "Name of IPD Remarks" + validation.MaxlengthcheckMSG100);
			return "Name of IPD Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(name_of_ipd_remark) == false) {
			ra.addAttribute("msg","Name of IPD Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Name of IPD Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (ipd_case_sheets.equals("0")) {
			ra.addAttribute("msg", "Please Select IPD Case Sheets");
			return "Please Select IPD Case Sheets";
		}
		if (ipd_case_sheets_remark == null || ipd_case_sheets_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter IPD Case Sheets Remarks");
			return "Please Enter IPD Case Sheets Remarks";
		}
		if (validation.maxlengthcheck100(ipd_case_sheets_remark) == false) {
			ra.addAttribute("msg", "IPD Case Sheets Remarks" + validation.MaxlengthcheckMSG100);
			return "IPD Case Sheets Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(ipd_case_sheets_remark) == false) {
			ra.addAttribute("msg","IPD Case Sheets Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "IPD Case Sheets Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (discharge_cards.equals("0")) {
			ra.addAttribute("msg", "Please Select Discharge Cards");
			return "Please Select Discharge Cards";
		}
		if (discharge_cards_remark == null || discharge_cards_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Discharge Cards Remarks");
			return "Please Enter Discharge Cards Remarks";
		}
		if (validation.maxlengthcheck100(discharge_cards_remark) == false) {
			ra.addAttribute("msg", "Discharge Cards Remarks" + validation.MaxlengthcheckMSG100);
			return "Discharge Cards Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(discharge_cards_remark) == false) {
			ra.addAttribute("msg","Discharge Cards Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Discharge Cards Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (ip_medicine.equals("0")) {
			ra.addAttribute("msg", "Please Select IP Medicine");
			return "Please Select IP Medicine";
		}
		if (ip_medicine_remark == null || ip_medicine_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter IP Medicine Remarks");
			return "Please Enter IP Medicine Remarks";
		}
		if (validation.maxlengthcheck100(ip_medicine_remark) == false) {
			ra.addAttribute("msg", "IP Medicine Remarks" + validation.MaxlengthcheckMSG100);
			return "IP Medicine Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(ip_medicine_remark) == false) {
			ra.addAttribute("msg","IP Medicine Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "IP Medicine Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (nursing_duty_roster.equals("0")) {
			ra.addAttribute("msg", "Please Select Nursing Duty Roster");
			return "Please Select Nursing Duty Roster";
		}
		if (nursing_duty_roster_remark == null || nursing_duty_roster_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Nursing Duty Roster Remarks");
			return "Please Enter Nursing Duty Roster Remarks";
		}
		if (validation.maxlengthcheck100(nursing_duty_roster_remark) == false) {
			ra.addAttribute("msg", "Nursing Duty Roster Remarks" + validation.MaxlengthcheckMSG100);
			return "Nursing Duty Roster Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(nursing_duty_roster_remark) == false) {
			ra.addAttribute("msg","Nursing Duty Roster Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Nursing Duty Roster Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (doctor_duty_roaster.equals("0")) {
			ra.addAttribute("msg", "Please Select Doctor Duty Roster");
			return "Please Select Doctor Duty Roster";
		}
		if (doctor_duty_roaster_remark == null || doctor_duty_roaster_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Doctor Duty Roster Remarks");
			return "Please Enter Doctor Duty Roster Remarks";
		}
		if (validation.maxlengthcheck100(doctor_duty_roaster_remark) == false) {
			ra.addAttribute("msg", "Doctor Duty Roster Remarks" + validation.MaxlengthcheckMSG100);
			return "Doctor Duty Roster Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(doctor_duty_roaster_remark) == false) {
			ra.addAttribute("msg","Doctor Duty Roster Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Doctor Duty Roster Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (ip_diet_register.equals("0")) {
			ra.addAttribute("msg", "Please Select IP Diet Register");
			return "Please Select IP Diet Register";
		}
		if (ip_diet_register_remark == null || ip_diet_register_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter IP Diet Register Remarks");
			return "Please Enter IP Diet Register Remarks";
		}
		if (validation.maxlengthcheck100(ip_diet_register_remark) == false) {
			ra.addAttribute("msg", "IP Diet Register Remarks" + validation.MaxlengthcheckMSG100);
			return "IP Diet Register Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(ip_diet_register_remark) == false) {
			ra.addAttribute("msg","IP Diet Register Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "IP Diet Register Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (cash_receipt_ipd.equals("0")) {
			ra.addAttribute("msg", "Please Select Cash Receipt for IPD");
			return "Please Select Cash Receipt for IPD";
		}
		if (cash_receipt_ipd_remark == null || cash_receipt_ipd_remark.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Cash Receipt for IPD Remarks");
			return "Please Enter Cash Receipt for IPD Remarks";
		}
		if (validation.maxlengthcheck100(cash_receipt_ipd_remark) == false) {
			ra.addAttribute("msg", "Cash Receipt for IPD Remarks" + validation.MaxlengthcheckMSG100);
			return "Cash Receipt for IPD Remarks " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(cash_receipt_ipd_remark) == false) {
			ra.addAttribute("msg","Cash Receipt for IPD Remarks " +validation.isOnlyAlphabetMSGDASH);
			return "Cash Receipt for IPD Remarks" +validation.isOnlyAlphabetMSGDASH;
		}
		if (name_of_multispecialty_hospital == null || name_of_multispecialty_hospital.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Name of Multispeciality Hospital");
			return "Please Select Name of Multispeciality Hospital";
		}
		if (validation.maxlengthcheck100(name_of_multispecialty_hospital) == false) {
			ra.addAttribute("msg", "Name of Multispeciality Hospital" + validation.MaxlengthcheckMSG100);
			return "Name of Multispeciality Hospital " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(name_of_multispecialty_hospital) == false) {
			ra.addAttribute("msg","Name of Multispeciality Hospital " +validation.isOnlyAlphabetMSGDASH);
			return "Name of Multispeciality Hospital" +validation.isOnlyAlphabetMSGDASH;
		}
		if (address_of_multispecialty_hospital == null || address_of_multispecialty_hospital.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Address of Multispecialty Hospital of Modern Medicine with which the College has MoU");
			return "Please Select Address of Multispecialty Hospital of Modern Medicine with which the College has MoU";
		}
		if (validation.maxlengthcheck100(address_of_multispecialty_hospital) == false) {
			ra.addAttribute("msg", "Address of Multispecialty Hospital" + validation.MaxlengthcheckMSG100);
			return "Address of Multispecialty Hospital " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetNumber(address_of_multispecialty_hospital) == false) {
			ra.addAttribute("msg","Address of Multispecialty Hospital " +validation.isOnlyAlphabetNumberMSG);
			return "Address of Multispecialty Hospital" +validation.isOnlyAlphabetNumberMSG;
		}
		if (date_of_mou_sign == null || date_of_mou_sign.trim().equals("") || date_of_mou_sign.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date of MoU Sign");
			return "Please Enter Date of MoU Sign";
		}
		
		if (validation.isOnlyDateFormat(date_of_mou_sign) == false) {
			ra.addAttribute("msg", "Date of MoU Sign " + validation.isOnlyDateFormatMSG);
			return "" +validation.isOnlyDateFormatMSG;
		}
		if (validity_of_mou == null || validity_of_mou.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Validity of MoU");
			return "Please Enter Validity of MoU";
		}
		if (validation.maxlengthcheck100(validity_of_mou) == false) {
			ra.addAttribute("msg", "Validity of MoU" + validation.MaxlengthcheckMSG100);
			return "Validity of MoU " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetNumber(validity_of_mou) == false) {
			ra.addAttribute("msg","Validity of MoU " +validation.isOnlyAlphabetNumberMSG);
			return "Validity of MoU" +validation.isOnlyAlphabetNumberMSG;
		}
		if (area_of_mou == null || area_of_mou.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area in Which MoU Centered");
			return "Please Enter Area in Which MoU Centered";
		}
		if (validation.maxlengthcheck10(area_of_mou) == false) {
			ra.addAttribute("msg", "Area of MoU" + validation.MaxlengthcheckMSG10);
			return "Area of MoU " +validation.MaxlengthcheckMSG10;
		}
		if (validation.isOnlyNumber(area_of_mou) == false) {
			ra.addAttribute("msg","Area of MoU " +validation.isOnlyNumberMSG);
			return "Area of MoU " +validation.isOnlyNumberMSG;
		}
		if (investigation_of_hospital == null || investigation_of_hospital.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Investigation Attend in the MoU Hospital");
			return "Please Enter Investigation Attend in the MoU Hospital";
		}
		if (validation.maxlengthcheck100(investigation_of_hospital) == false) {
			ra.addAttribute("msg", "Investigation of Hospital" + validation.MaxlengthcheckMSG100);
			return "Investigation of Hospital " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetNumber(investigation_of_hospital) == false) {
			ra.addAttribute("msg","Investigation of Hospital " +validation.isOnlyAlphabetNumberMSG);
			return "Investigation of Hospital" +validation.isOnlyAlphabetNumberMSG;
		}
		if (training_details == null || training_details.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Training Provided for MoU Hospital Classes Taken by the Doctors from MoU Hospital for Student of the Homeopathy");
			return "Please Enter Training Provided for MoU Hospital Classes Taken by the Doctors from MoU Hospital for Student of the Homeopathy";
		}
		if (validation.maxlengthcheck100(training_details) == false) {
			ra.addAttribute("msg", "Training Details" + validation.MaxlengthcheckMSG100);
			return "Training Details " +validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetNumber(training_details) == false) {
			ra.addAttribute("msg","Training Details " +validation.isOnlyAlphabetNumberMSG);
			return "Training Details" +validation.isOnlyAlphabetNumberMSG;
		}
		
		
		
		
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY verification_functionality_detail =new CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY();
			
				
			verification_functionality_detail.setName_of_opd(Integer.parseInt(name_of_opd));
			verification_functionality_detail.setName_of_opd_remark(name_of_opd_remark);
			verification_functionality_detail.setOpd_case_register(Integer.parseInt(opd_case_register));
			verification_functionality_detail.setOpd_case_register_remark(opd_case_register_remark);
			verification_functionality_detail.setOpd_medicine(Integer.parseInt(opd_medicine));
			verification_functionality_detail.setOpd_medicine_remark(opd_medicine_remark);
			verification_functionality_detail.setCase_receipt_opd(Integer.parseInt(case_receipt_opd));
			verification_functionality_detail.setCase_receipt_opd_remark(case_receipt_opd_remark);
			verification_functionality_detail.setName_of_ipd(Integer.parseInt(name_of_ipd));
			verification_functionality_detail.setName_of_ipd_remark(name_of_ipd_remark);
			verification_functionality_detail.setIpd_case_sheets(Integer.parseInt(ipd_case_sheets));
			verification_functionality_detail.setIpd_case_sheets_remark(ipd_case_sheets_remark);
			verification_functionality_detail.setDischarge_cards(Integer.parseInt(discharge_cards));
			verification_functionality_detail.setDischarge_cards_remark(discharge_cards_remark);
			verification_functionality_detail.setIp_medicine(Integer.parseInt(ip_medicine));
			verification_functionality_detail.setIp_medicine_remark(ip_medicine_remark);
			verification_functionality_detail.setNursing_duty_roster(Integer.parseInt(nursing_duty_roster));
			verification_functionality_detail.setNursing_duty_roster_remark(nursing_duty_roster_remark);
			verification_functionality_detail.setDoctor_duty_roaster(Integer.parseInt(doctor_duty_roaster));
			verification_functionality_detail.setDoctor_duty_roaster_remark(doctor_duty_roaster_remark);
			verification_functionality_detail.setIp_diet_register(Integer.parseInt(ip_diet_register));
			verification_functionality_detail.setIp_diet_register_remark(ip_diet_register_remark);
			verification_functionality_detail.setCash_receipt_ipd(Integer.parseInt(cash_receipt_ipd));
			verification_functionality_detail.setCash_receipt_ipd_remark(cash_receipt_ipd_remark);
			verification_functionality_detail.setName_of_multispecialty_hospital(name_of_multispecialty_hospital);
			verification_functionality_detail.setAddress_of_multispecialty_hospital(address_of_multispecialty_hospital);
			if(!date_of_mou_sign.equals("")) {
			verification_functionality_detail.setDate_of_mou_sign(datePickerFormat.parse(date_of_mou_sign));
			}
			verification_functionality_detail.setValidity_of_mou(validity_of_mou);
			verification_functionality_detail.setArea_of_mou(area_of_mou);
			verification_functionality_detail.setInvestigation_of_hospital(investigation_of_hospital);
			verification_functionality_detail.setTraining_details(training_details);
			verification_functionality_detail.setDoc_of_multispecialty_hospital(doc_of_multispecialty_hospital);
			verification_functionality_detail.setIpd_diet_register_doc(ipd_diet_register_doc);
			verification_functionality_detail.setInstitute_id(Integer.parseInt(institute_id));
			verification_functionality_detail.setS_id(Integer.parseInt(s_id));
			verification_functionality_detail.setCreated_by(Integer.parseInt(userid));
			verification_functionality_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_verification_functionality) == 0) {
					verification_functionality_detail.setP_id(Integer.parseInt(p_id));
					int hid_verification_functionality1= (Integer) sessionHQL.save(verification_functionality_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_verification_functionality1);
			}
				else {

					CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY verification_functionality_detail_u = (CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY) sessionHQL
							.get(CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY.class, Integer.parseInt(hid_verification_functionality));
					
						
					verification_functionality_detail_u.setName_of_opd(Integer.parseInt(name_of_opd));
					verification_functionality_detail_u.setName_of_opd_remark(name_of_opd_remark);
					verification_functionality_detail_u.setOpd_case_register(Integer.parseInt(opd_case_register));
					verification_functionality_detail_u.setOpd_case_register_remark(opd_case_register_remark);
					verification_functionality_detail_u.setOpd_medicine(Integer.parseInt(opd_medicine));
					verification_functionality_detail_u.setOpd_medicine_remark(opd_medicine_remark);
					verification_functionality_detail_u.setCase_receipt_opd(Integer.parseInt(case_receipt_opd));
					verification_functionality_detail_u.setCase_receipt_opd_remark(case_receipt_opd_remark);
					verification_functionality_detail_u.setName_of_ipd(Integer.parseInt(name_of_ipd));
					verification_functionality_detail_u.setName_of_ipd_remark(name_of_ipd_remark);
					verification_functionality_detail_u.setIpd_case_sheets(Integer.parseInt(ipd_case_sheets));
					verification_functionality_detail_u.setIpd_case_sheets_remark(ipd_case_sheets_remark);
					verification_functionality_detail_u.setDischarge_cards(Integer.parseInt(discharge_cards));
					verification_functionality_detail_u.setDischarge_cards_remark(discharge_cards_remark);
					verification_functionality_detail_u.setIp_medicine(Integer.parseInt(ip_medicine));
					verification_functionality_detail_u.setIp_medicine_remark(ip_medicine_remark);
					verification_functionality_detail_u.setNursing_duty_roster(Integer.parseInt(nursing_duty_roster));
					verification_functionality_detail_u.setNursing_duty_roster_remark(nursing_duty_roster_remark);
					verification_functionality_detail_u.setDoctor_duty_roaster(Integer.parseInt(doctor_duty_roaster));
					verification_functionality_detail_u.setDoctor_duty_roaster_remark(doctor_duty_roaster_remark);
					verification_functionality_detail_u.setIp_diet_register(Integer.parseInt(ip_diet_register));
					verification_functionality_detail_u.setIp_diet_register_remark(ip_diet_register_remark);
					verification_functionality_detail_u.setCash_receipt_ipd(Integer.parseInt(cash_receipt_ipd));
					verification_functionality_detail_u.setCash_receipt_ipd_remark(cash_receipt_ipd_remark);
					verification_functionality_detail_u.setName_of_multispecialty_hospital(name_of_multispecialty_hospital);
					verification_functionality_detail_u.setAddress_of_multispecialty_hospital(address_of_multispecialty_hospital);
					if(!date_of_mou_sign.equals("")) {
					verification_functionality_detail_u.setDate_of_mou_sign(datePickerFormat.parse(date_of_mou_sign));
					}
					verification_functionality_detail_u.setValidity_of_mou(validity_of_mou);
					verification_functionality_detail_u.setArea_of_mou(area_of_mou);
					verification_functionality_detail_u.setInvestigation_of_hospital(investigation_of_hospital);
					verification_functionality_detail_u.setTraining_details(training_details);
					verification_functionality_detail_u.setDoc_of_multispecialty_hospital(doc_of_multispecialty_hospital);
					verification_functionality_detail_u.setIpd_diet_register_doc(ipd_diet_register_doc);
					verification_functionality_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					verification_functionality_detail_u.setS_id(Integer.parseInt(s_id));
					verification_functionality_detail_u.setModified_by(Integer.parseInt(userid));
					verification_functionality_detail_u.setModified_date(date);
					sessionHQL.update(verification_functionality_detail_u);
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
		return  "Final Submit Sucessfully";
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
