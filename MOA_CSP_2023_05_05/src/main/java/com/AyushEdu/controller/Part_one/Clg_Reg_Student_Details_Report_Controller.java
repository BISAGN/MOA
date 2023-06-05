package com.AyushEdu.controller.Part_one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_STUDENT_DTL_ADMITTED_STUDENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Student_Details_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Student_Details_Report_Controller {
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
	Clg_Reg_Student_Details_Report_Dao sdrDao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	// ----------------search-------------------------
	 
	 @GetMapping(value = "/Search_Student_Details_url")
		public ModelAndView Search_Student_Details_url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			String userid = session.getAttribute("userId_for_jnlp").toString();
			String role = session.getAttribute("role").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = new Date();
			String strDate = datePickerFormat.format(date); 
			String year = strDate.substring(6, 10);
			List a = new ArrayList();
			for (int i = 0; i < 5; i++) {
				a.add(Math.subtractExact(Integer.parseInt(year), i));
			}
			
			System.err.println("admitted_year=============="+a);

			model.put("year", a);
			model.put("msg", msg);
			return new ModelAndView("Search_Student_DetailsTiles");
		}
	 
		@PostMapping("/getFilterSearch_Student_Details_data")
		public @ResponseBody List<Map<String, Object>> getFilterSearch_Student_Details_data(HttpSession sessionUserId, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, String last_student, String year,String internsopd,String internsipd, 
				String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck) {
			
			String role = sessionUserId.getAttribute("role").toString();
			String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			return sdrDao.DataTableSearch_Student_DetailsDataList(startPage, pageLength, Search, orderColunm, orderType, last_student, year, internsopd, internsipd, 
					prescribe, seminar, house_job, no_house_job, migrationcheck, 
					 role, userid,institute_id);

		}
		
		@PostMapping("/getTotalSearch_Student_Details_dataCount")
		public @ResponseBody long getTotalSearch_Student_Details_dataCount(HttpSession sessionUserId, String Search, String last_student, String year,
				String internsopd,String internsipd, String prescribe,String seminar,String house_job,String no_house_job,String migrationcheck) {
			String role = sessionUserId.getAttribute("role").toString();
			String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			return sdrDao.DataTableSearch_Student_DetailsDataTotalCount(Search, last_student, year, internsopd, internsipd, 
					prescribe, seminar, house_job, no_house_job, migrationcheck, 
					 role, userid,institute_id);
		}
		
		//////////////////View_url

		@RequestMapping(value = "/View_Search_Student_DetailsUrl", method = RequestMethod.POST)
		public ModelAndView View_Search_Student_DetailsUrl(@RequestParam(value = "student_dtl_id", required = false) String id, ModelMap Mmap,
		HttpSession session, @RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
				Integer.parseInt(id), role, userid,"clg_reg_student_dtl_admitted_student","institute_id");
				
				System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
				String p_id = getPidfromInstidReport.get(0).get("id").toString();
				Mmap.put("getPidfromInstidReport", p_id); 

		System.err.println("22/2/23----------------id------>" + id);

		CLG_REG_STUDENT_DTL_ADMITTED_STUDENT viewid = (CLG_REG_STUDENT_DTL_ADMITTED_STUDENT) sessionHQL
			.get(CLG_REG_STUDENT_DTL_ADMITTED_STUDENT.class, Integer.parseInt(p_id));

		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("View_Student_Details_CMD", viewid);

		List<Map<String, Object>> getAllinfo_Admitted_Stu_Details_View = sdrDao.getAllinfo_Admitted_Stu_Details_View(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getStu_Details_Upload_Doc_View = sdrDao.getStu_Details_Upload_Doc_View(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getStu_Details_Pass_Stu_View = sdrDao.getStu_Details_Pass_Stu_View(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		Mmap.put("getAllinfo_Admitted_Stu_Details_View", getAllinfo_Admitted_Stu_Details_View);
		Mmap.put("getStu_Details_Upload_Doc_View", getStu_Details_Upload_Doc_View);
		Mmap.put("getStu_Details_Pass_Stu_View", getStu_Details_Pass_Stu_View);
		
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String strDate = datePickerFormat.format(date); 
		String year = strDate.substring(6, 10);
		List a = new ArrayList();
		for (int i = 0; i < 5; i++) {
			a.add(Math.subtractExact(Integer.parseInt(year), i));
		}
		
		System.err.println("admitted_year=============="+a);

		Mmap.put("year", a);
		
		Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
		Mmap.put("inst_id", id);

		Mmap.addAttribute("msg", msg);
		return new ModelAndView("student_details_view");
		}


}
