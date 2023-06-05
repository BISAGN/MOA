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
import com.AyushEdu.dao.Examination.Report_Dao;
import com.AyushEdu.dao.Examination.Summary_ReportDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Summary_Report_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@Autowired
	Report_Dao srdao;
	
	@Autowired
	private Summary_ReportDao rdao;
	
	@RequestMapping(value = "admin/Summary_Report_URL", method = RequestMethod.GET)
	public ModelAndView Summary_Report_URL(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {

		try {
			//SECURITY ABHISHEK
//			if(request.getHeader("Referer") == null ) { 
//				
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Summary_Report_URL", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String role = session.getAttribute("role").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
			Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
			Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));
			Mmap.put("getTerm", common.getTerm());
			Mmap.put("getinstitute_listbyuniversity", common.getinstitute_listbyuniversity(sessionFactory, userid));
			Mmap.put("getuniversity_listbyState", common.getuniversity_listbyState(sessionFactory, userid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Summary_Report_tiles");
	}
	@RequestMapping(value = "/getuniversity_list_summary", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getuniversity_list_summary( String state_id,HttpSession session) {
		String role = session.getAttribute("role").toString();
	 ArrayList<ArrayList<String>> list = srdao.getuniversity_list(state_id,role);
		return list;
	}
	
	@PostMapping("/getFilter_Summary_Report")
	public @ResponseBody List<Map<String, Object>> getFilter_Summary_Report(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String state_id, String university_id,
			String institute_id, String degree_id, String professional_id, HttpSession session) throws ParseException {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		if(role.toLowerCase().contains("university")){
			university_id = common.getUniIdfromUserid(sessionFactory,userid).get(0);
		}
		return rdao.get_Summary_report(startPage, pageLength, Search, orderColunm, orderType, state_id,
				university_id, institute_id, degree_id, professional_id, role,userid);
	}
	
	@PostMapping("/getTotal_Summary_Report_dataCount")
	public @ResponseBody long getTotal_Summary_Report_dataCount(HttpSession sessionUserId, String Search,
			 String state_id, String university_id,String institute_id, String degree_id, String professional_id, HttpSession session) throws ParseException {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		if(role.toLowerCase().contains("university")){
			university_id = common.getUniIdfromUserid(sessionFactory,userid).get(0);
		}
		return rdao.getTotal_Summary_report_dataCount(Search, state_id,university_id, institute_id, degree_id, professional_id,role);
	}
}
