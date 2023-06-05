package com.AyushEdu.controller.Part_one;

import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_ReportDAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Student_Details_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Institution_Basic_Detais_Report_controller {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;

	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;

	@Autowired
	Commondao commondao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	@Autowired
	Clg_Reg_Student_Details_Report_Dao sdrDao;
	
	@Autowired
	private Clg_Reg_College_Staff_List_ReportDAO CSLRDao;

	// ----------------search-------------------------

	@GetMapping(value = "/Search_Basic_Info_url")
	public ModelAndView Search_Basic_Info_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		model.put("msg", msg);
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		return new ModelAndView("Search_Basic_InfoTiles");
	}

	@PostMapping("/getFilterSearch_Basic_Info_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_Basic_Info_data(HttpSession sessionUserId,
			int startPage, int pageLength, String Search, String orderColunm, String orderType,String inst_code, String inst_state,
			String inst_city, String inst_pincode, String inst_mo_no, String inst_email, String institution_type,
			String managing_body, String management_contact, String name_of_society, String mng_state, String mng_city,
			String mng_mo_no, String mng_email, String s_registration_no, String university_affiliated,String status) {

		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return sibdao.DataTableSearch_Basic_InfoDataList(startPage, pageLength, Search, orderColunm, orderType,
				inst_code,inst_state, inst_city, inst_pincode, inst_mo_no, inst_email, institution_type, managing_body,
				management_contact, name_of_society, mng_state, mng_city, mng_mo_no, mng_email, s_registration_no,
				university_affiliated, role, userid, institute_id,status);

	}


	@PostMapping("/getTotalSearch_Basic_Info_dataCount")
	public @ResponseBody long getTotalSearch_Basic_Info_dataCount(HttpSession sessionUserId, String Search,
			String inst_code,String inst_state, String inst_city, String inst_pincode, String inst_mo_no, String inst_email,
			String institution_type, String managing_body, String management_contact, String name_of_society,
			String mng_state, String mng_city, String mng_mo_no, String mng_email, String s_registration_no,
			String university_affiliated,String status) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return sibdao.DataTableSearch_Basic_InfoDataTotalCount(Search, inst_code, inst_state, inst_city, inst_pincode, inst_mo_no,
				inst_email, institution_type, managing_body, management_contact, name_of_society, mng_state, mng_city,
				mng_mo_no, mng_email, s_registration_no, university_affiliated, role, userid, institute_id,status);
	}

//////////////////View_url

	@RequestMapping(value = "/View_Search_Basic_InfoUrl", method = RequestMethod.POST)
	public ModelAndView View_Search_Basic_InfoUrl(@ModelAttribute("id6") String id,@ModelAttribute("id7") String instid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
System.err.println("institute_id1-----------------------"+institute_id1);
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute_id = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		
//		String inst_idfromuserid = sibdao.getpid_from_useridReport(Integer.parseInt(userid)).get(0).get(0);

		System.err.println("22/2/23----------------id------>" + id);
		System.err.println("22/2/23----------------institute_id------>" + institute_id);
		System.err.println("22/2/23----------------instid------>" + instid);
		System.err.println("22/2/23----------------role------>" + role);
		
		
		
		
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
		Integer.parseInt(id), role, userid,"clg_reg_inst_info_institution_basic_details","inst_id");
		
		System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
		
//		if(getPidfromInstidReport.isEmpty() && role!="NCH") {
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}

		String p_id = getPidfromInstidReport.get(0).get("id").toString();
		Mmap.put("getPidfromInstidReport", p_id); 

		CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS viewid = (CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS) sessionHQL
				.get(CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS.class, Integer.parseInt(p_id));
		
		System.err.println("viewid===666666666666666==========="+viewid);

		CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS viewchid = (CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS) sessionHQL
				.get(CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS.class, Integer.parseInt(p_id));

//CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY viewchid2 = (CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY) sessionHQL.get(CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY.class,
//Integer.parseInt(id));



		System.err.println("institude============----------------" + institute_id);
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//Mmap.put("getMedInstituteName", common.getMedInstituteName(sessionFactory));

		Mmap.put("View_Search_Basic_InfoCMD", viewid);
		System.err.println("viewid=============="+viewid);
		Mmap.put("View_Search_Basic_InfochCMD", viewchid);

		List<Map<String, Object>> View_Search_Basic_Infoch2 = sibdao.getAllinfo_connectivityReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> View_Search_Basic_Infoch3 = sibdao.getAllinfo_police_stReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getInstname = sibdao.getInstnameReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_intake_capacity_Report = sibdao.getAllinfo_intake_capacity_Report(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_intake_capacity_for_PG_Report = sibdao.getAllinfo_intake_capacity_for_PG_Report(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_quali_instReport = sibdao.getAllinfo_quali_instReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_UG_intake_capacity_Report = sibdao.getAllinfo_UG_intake_capacity_Report(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_PG_intake_capacity_Report = sibdao.getAllinfo_PG_intake_capacity_Report(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_dtl_landReport = sibdao.getAllinfo_dtl_landReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllPersdetailsReport = sibdao.getAllPersdetailsReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_inst_dtlReport = sibdao.getAllinfo_inst_dtlReport(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getAllinfo_intake_cap_childView = sibdao.getAllinfo_intake_cap_childView(Integer.parseInt(id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getStu_Details_Upload_Doc_View = sdrDao.getStu_Details_Upload_Doc_View(Integer.parseInt(p_id),
				Integer.parseInt(instid), role);
		List<Map<String, Object>> getClg_Staff_List_Upload_Doc_View = CSLRDao.getClg_Staff_List_Upload_Doc_View(Integer.parseInt(p_id),
				Integer.parseInt(instid), role);
		
//		List<Map<String, Object>> getInstDetailReport = sibdao.getInstDetailReport(Integer.parseInt(id),
//				Integer.parseInt(institute_id), role, userid);

		Mmap.put("View_Search_Basic_Infoch2", View_Search_Basic_Infoch2);
		Mmap.put("View_Search_Basic_Infoch3", View_Search_Basic_Infoch3);
		Mmap.put("getInstname", getInstname);
		Mmap.put("getAllinfo_intake_capacity_Report", getAllinfo_intake_capacity_Report);
		Mmap.put("getAllinfo_intake_capacity_for_PG_Report", getAllinfo_intake_capacity_for_PG_Report);
		Mmap.put("getAllinfo_quali_instReport", getAllinfo_quali_instReport);
		Mmap.put("getAllinfo_UG_intake_capacity_Report", getAllinfo_UG_intake_capacity_Report);
		Mmap.put("getAllinfo_PG_intake_capacity_Report", getAllinfo_PG_intake_capacity_Report);
		Mmap.put("getAllinfo_dtl_landReport", getAllinfo_dtl_landReport);
		Mmap.put("getAllPersdetailsReport", getAllPersdetailsReport);
		Mmap.put("getAllinfo_inst_dtlReport", getAllinfo_inst_dtlReport);
		Mmap.put("getAllinfo_intake_cap_childView", getAllinfo_intake_cap_childView);
		Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("All_Institute_ID", instid); 
		Mmap.put("inst_id", id);
		Mmap.put("getStu_Details_Upload_Doc_View", getStu_Details_Upload_Doc_View);
		Mmap.put("getClg_Staff_List_Upload_Doc_View", getClg_Staff_List_Upload_Doc_View);
//		Mmap.put("getInstDetailReport", getInstDetailReport); 
//		System.err.println("getInstDetailReport==============="+getInstDetailReport);


//Mmap.put("getgenderList", common.getgenderList(sessionFactory));
//Mmap.put("getDocList", common.getDocList(sessionFactory));
//Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
//Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
//Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
//Mmap.put("getregView", tdao.getregistrationViewdata());
//Mmap.put("data", tdao.getAllPersdetails(Integer.parseInt(userid)));
		Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
	//	Mmap.put("getView_idFrom_Institute_idforBasic", CIRDao.getView_idFrom_Institute_idforBasicinfo(Integer.parseInt(institute_id)));
		
		
		
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("basics_information_view");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
