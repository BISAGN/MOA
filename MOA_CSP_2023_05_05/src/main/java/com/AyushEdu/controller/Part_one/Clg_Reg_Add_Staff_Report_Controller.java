package com.AyushEdu.controller.Part_one;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_ADD_STAFF_TEACHING_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Add_Staff_Report_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_ReportDAO;
import com.AyushEdu.dao.Part_One.Clg_reg_College_Declaration_Report_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Add_Staff_Report_Controller {

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
	private Clg_Reg_Add_Staff_Report_DAO ASRDao;

	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;

	@Autowired
	private Clg_Reg_College_Staff_List_ReportDAO CSLRDao;
	
	@Autowired
	Clg_reg_College_Declaration_Report_DAO cdrdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;

	// ----------------search-------------------------

	@GetMapping(value = "/Add_Staff_Report_url")
	public ModelAndView Add_Staff_Report_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);

		model.put("msg", msg);
		return new ModelAndView("Add_Staff_ReportTiles");
	}

	@PostMapping("/getFilterSearch_College_Staff_List_data")
	public @ResponseBody ArrayList<ArrayList<String>> getFilterSearch_College_Staff_List_data(HttpSession sessionUserId,
			int startPage, int pageLength, String Search, String orderColunm, String orderType,
			String college_staff_list, String first_name, String date_of_appoinment, String nature_of_appoinment,
			String emp_id, String emp_department, String emp_qualification, String aadhar_no, String guest_first_name,
			String guest_date_of_appoinment, String guest_nature_of_appoinment, String guest_emp_id,
			String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no, String non_first_name,
			String non_date_of_appoinment, String non_nature_of_appoinment, String non_emp_id,
			String non_emp_department, String non_emp_qualification, String non_aadhar_no) {

		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return ASRDao.DataTableSearch_College_Staff_ListDataList(startPage, pageLength, Search, orderColunm, orderType,
				college_staff_list, first_name, date_of_appoinment, nature_of_appoinment, emp_id, emp_department,
				emp_qualification, aadhar_no, guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment,
				guest_emp_id, guest_emp_department, guest_emp_qualification, guest_aadhar_no, non_first_name,
				non_date_of_appoinment, non_nature_of_appoinment, non_emp_id, non_emp_department, non_emp_qualification,
				non_aadhar_no, role, userid, institute_id);

	}

	@PostMapping("/getTotalSearch_College_Staff_List_dataCount")
	public @ResponseBody long getTotalSearch_College_Staff_List_dataCount(HttpSession sessionUserId, String Search,
			String college_staff_list, String first_name, String date_of_appoinment, String nature_of_appoinment,
			String emp_id, String emp_department, String emp_qualification, String aadhar_no, String guest_first_name,
			String guest_date_of_appoinment, String guest_nature_of_appoinment, String guest_emp_id,
			String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no, String non_first_name,
			String non_date_of_appoinment, String non_nature_of_appoinment, String non_emp_id,
			String non_emp_department, String non_emp_qualification, String non_aadhar_no) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return ASRDao.DataTableSearch_College_Staff_ListDataTotalCount(Search, college_staff_list, first_name,
				date_of_appoinment, nature_of_appoinment, emp_id, emp_department, emp_qualification, aadhar_no,
				guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment, guest_emp_id,
				guest_emp_department, guest_emp_qualification, guest_aadhar_no, non_first_name, non_date_of_appoinment,
				non_nature_of_appoinment, non_emp_id, non_emp_department, non_emp_qualification, non_aadhar_no, role,
				userid, institute_id);
	}

	////////////////// View_url

	@RequestMapping(value = "/View_Search_Add_Staff_InfoUrl", method = RequestMethod.POST)
	public ModelAndView View_Search_Add_Staff_InfoUrl(@RequestParam(value = "basic_clg_staff_list_id", required = false) String id, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
				Integer.parseInt(institute_id), role, userid, "clg_reg_add_staff_teaching_faculty", "institute_id");
		String p_id = getPidfromInstidReport.get(0).get("id").toString();
		Mmap.put("getPidfromInstidReport", p_id);

		CLG_REG_ADD_STAFF_TEACHING_FACULTY viewid = (CLG_REG_ADD_STAFF_TEACHING_FACULTY) sessionHQL
				.get(CLG_REG_ADD_STAFF_TEACHING_FACULTY.class, Integer.parseInt(id));
		Mmap.put("Add_Staff_Report_CMD", viewid);

		List<Map<String, Object>> getClg_Staff_List_Teacher_View = CSLRDao
				.getClg_Staff_List_Teacher_View(Integer.parseInt(id), Integer.parseInt(id), role);
		List<Map<String, Object>> getClg_Staff_List_Guest_View = CSLRDao
				.getClg_Staff_List_Guest_View(Integer.parseInt(id), Integer.parseInt(id), role);
		List<Map<String, Object>> getClg_Staff_List_Non_Teaching_View = CSLRDao
				.getClg_Staff_List_Non_Teaching_View(Integer.parseInt(id), Integer.parseInt(id), role);
		List<Map<String, Object>> GetPrinacipal_Name = cdrdao.GetPrinacipal_Name(Integer.parseInt(id),
				Integer.parseInt(id), role);
		Mmap.put("getClg_Staff_List_Teacher_View", getClg_Staff_List_Teacher_View);
		Mmap.put("getClg_Staff_List_Guest_View", getClg_Staff_List_Guest_View);
		Mmap.put("getClg_Staff_List_Non_Teaching_View", getClg_Staff_List_Non_Teaching_View);
		Mmap.put("Prinacipal_NameView", GetPrinacipal_Name);
		
		Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
		Mmap.put("inst_id", id);
		
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("college_staff_list_view");
	}

}
