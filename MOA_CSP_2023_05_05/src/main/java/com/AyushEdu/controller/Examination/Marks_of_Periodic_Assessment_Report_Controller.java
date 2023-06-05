package com.AyushEdu.controller.Examination;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Examination.Add_Marks_of_PA_Dao;
import com.AyushEdu.dao.Examination.Marks_of_Periodic_Assessment_Report_DAO;
import com.AyushEdu.dao.Examination.Students_Result_DetailsDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Marks_of_Periodic_Assessment_Report_Controller {

	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Marks_of_Periodic_Assessment_Report_DAO AMP;

	@Autowired
	private Commondao cd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Students_Result_DetailsDao SRDDao;

	@RequestMapping(value = "admin/Marks_of_Periodic_Assessment_Report", method = RequestMethod.GET)
	public ModelAndView Marks_of_Periodic_Assessment_Report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Marks_of_Periodic_Assessment_Report", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
			Mmap.put("getAttemptList", common.getAttemptList(sessionFactory));
			Mmap.put("getExam_SerialList", common.getExam_SerialList(sessionFactory));
			String uni_id = SRDDao.getuserid_listby_university(Integer.parseInt(userid)).get(0).get(0);
			Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory, uni_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Add_Marks_of_PA_report_tiles");
	}

	@PostMapping("/getFilter_Marks_perodic_report_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Marks_perodic_report_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String degree_id, String professional_id,
			String term_id, String exam_serial, String exam_type_id, String mon_year, String institute_id,String course_id,
			HttpSession session) throws ParseException {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return AMP.getFilter_Marks_perodic_reports_data(startPage, pageLength, Search, orderColunm, orderType,
				degree_id, professional_id, term_id, exam_serial, exam_type_id, mon_year, institute_id,course_id, role,
				institute_uid);
	}

	@PostMapping("/getTotalEdu_Marks_perodic_report_dataCount")

	public @ResponseBody long getTotalEdu_Marks_perodic_report_dataCount(HttpSession sessionUserId, String Search,
			String degree_id, String professional_id, String term_id, String exam_serial, String exam_type_id,
			String mon_year, String institute_id,String course_id, HttpSession session) throws ParseException {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return AMP.DataTableEdu_Marks_perodic_reports_Count(Search, degree_id, professional_id, term_id, exam_serial,
				exam_type_id, mon_year, institute_id,course_id, role, institute_id1);
	}

	@RequestMapping(value = "/getDegreeFromInstituteExam", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getDegreeFromInstituteExam(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		institute_id = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;

		try {
			list = AMP.getDegreeFromInstituteExam(institute_id, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
