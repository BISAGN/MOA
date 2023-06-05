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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Staff_Info_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Staff_Information_Report_Controller {
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
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	private Clg_Reg_Staff_Info_Report_Dao SIRDao;
	
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Dao CRDao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	
	@RequestMapping(value = "admin/college_staff_info_report_url", method = RequestMethod.GET)
	public ModelAndView college_staff_info_report_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		Mmap.put("getDepartmentListofug", SIRDao.getAllDepartment_list_new_ug());
		Mmap.put("getDepartmentListofpg", SIRDao.getAllDepartment_list_new_pg());
		Mmap.put("msg", msg);
		return new ModelAndView("college_staff_info_report_tiles");
	}
	
	@PostMapping("/getFilterSearch_College_Staff_Info_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_College_Staff_Info_data(HttpSession sessionUserId, int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ug_pg_status, String department_id) {
		
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String ug_status = SIRDao.getAllDepartment_list_new_ug().get(0).get(0);
		String pg_status = SIRDao.getAllDepartment_list_new_pg().get(0).get(0);
		return SIRDao.DataTableSearch_College_Staff_InfoDataList(startPage, pageLength, Search, orderColunm, orderType, ug_pg_status, department_id,
				 role, userid,institute_id,ug_status,pg_status);

	}
	
	@PostMapping("/getTotalSearch_College_Staff_Info_dataCount")
	public @ResponseBody long getTotalSearch_College_Staff_Info_dataCount(HttpSession sessionUserId, String Search, String ug_pg_status, String department_id) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String ug_status = SIRDao.getAllDepartment_list_new_ug().get(0).get(0);
		String pg_status = SIRDao.getAllDepartment_list_new_pg().get(0).get(0);
		return SIRDao.DataTableSearch_College_Staff_InfoDataTotalCount(Search, ug_pg_status, department_id,
				 role, userid,institute_id,ug_status,pg_status);
	}
	
	//-------------------------------------VIEW---------------------------------------------------------------------------------//
	
	
	@RequestMapping(value = "/View_College_Staff_InfoUrl", method = RequestMethod.POST)
	public ModelAndView View_College_Staff_InfoUrl(@RequestParam(value = "clg_staff_info_id", required = false) String id, ModelMap Mmap,
	HttpSession session, @RequestParam(value = "msg", required = false) String msg,
	HttpServletRequest request) {

			Session sessionHQL = this.sessionFactory.openSession();
			Mmap.put("main_view_id", id);
			System.err.println("iddddddddddddddddddddddddddd"+id);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			String role = session.getAttribute("role").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			
			
			List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
					Integer.parseInt(id), role, userid,"clg_reg_teaching_staff_ug","institute_id");
			
					System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
					String p_id = getPidfromInstidReport.get(0).get("id").toString();
			Mmap.put("getPidfromInstidReport", p_id); 

						System.err.println("22/2/23----------------id------>" + id);

							System.err.println("institute_id----------------id------>" + institute_id);

//							CLG_REG_HOSP_ADMINISTRATION viewid = (CLG_REG_HOSP_ADMINISTRATION) sessionHQL
//							.get(CLG_REG_HOSP_ADMINISTRATION.class, Integer.parseInt(p_id));
	
	
//					Mmap.put("View_Infast_redioCMD", viewid);

						List<Map<String, Object>> View_Teaching_Staff1 = SIRDao.View_Teaching_Staff(Integer.parseInt(p_id),
									Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Teaching_Staff_Pg2 = SIRDao.View_Teaching_Staff_Pg(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Non_Teaching_Staff_Info3 = SIRDao.View_Non_Teaching_Staff_Info(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Last_Acadmic_Year_Staff4 = SIRDao.View_Last_Acadmic_Year_Staff(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Staff_Salary_info5 = SIRDao.View_Staff_Salary_info(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Clg_Techer_Prom6 = SIRDao.View_Clg_Techer_Prom(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						List<Map<String, Object>> View_Clg_Staff_Document = SIRDao.View_Clg_Staff_Document(Integer.parseInt(p_id),
								Integer.parseInt(id), role,userid);
						
						
						
//						System.err.println("View_Hospital_Staff_Details2--------------"+View_Hospital_Staff_Details2);
						
						
						Mmap.put("View_Teaching_Staff1", View_Teaching_Staff1);
						Mmap.put("View_Teaching_Staff_Pg2", View_Teaching_Staff_Pg2);
						Mmap.put("View_Non_Teaching_Staff_Info3", View_Non_Teaching_Staff_Info3);
						Mmap.put("View_Last_Acadmic_Year_Staff4", View_Last_Acadmic_Year_Staff4);
						Mmap.put("View_Staff_Salary_info5", View_Staff_Salary_info5);
						Mmap.put("View_Clg_Techer_Prom6", View_Clg_Techer_Prom6);
						Mmap.put("View_Clg_Staff_Document", View_Clg_Staff_Document);
						
						Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
						Mmap.put("inst_id", id);

	Mmap.addAttribute("msg", msg);
	return new ModelAndView("college_staff_info_view");
	}

//=====================================================End VIEW ================================================//
	

}
