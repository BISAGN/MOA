package com.AyushEdu.controller.LMS_Attendance;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.Attendance.TB_ATTENDANCE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Attendance.AttendanceDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Attendance_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	AttendanceDAO  Attdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	
	@RequestMapping(value = "admin/Attendance_Url", method = RequestMethod.GET)
	 public ModelAndView Attendance_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		// SECURITY ABHISHEK
				if (request.getHeader("Referer") == null) {

					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Attendance_Url", roleid1);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}

		 Mmap.put("msg", msg);
//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);
		 return new ModelAndView("attendance_Tiles");
	 }
	
	@PostMapping(value = "/AttendanceAction")
	public ModelAndView AttendanceAction(@Validated @ModelAttribute("AttendanceCMD") TB_ATTENDANCE_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Attendance_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		
		String staff_name = request.getParameter("staff_name");
		String userid = request.getParameter("userid");
		String atten_date = request.getParameter("atten_date");

//		if (course_id == null || course_id.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Select Course Name.");
//			return new ModelAndView("redirect:module_url");
//		}
//		if (module_name == null || module_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Module Name.");
//			return new ModelAndView("redirect:module_url");
//		}
//		if (status == null || status.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Status.");
//			return new ModelAndView("redirect:module_url");
//		}
//		if (status == "2" || status.trim().equals("2")) {
//			ra.addAttribute("msg", "Only Select Active Status.");
//			return new ModelAndView("redirect:module_url");
//		}
//		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		return new ModelAndView("redirect:Attendance_Url");
	}
	
	
	@PostMapping("/getFilterAttendance_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String staff_name, String atten_date) {
		return Attdao.DataTableAttendanceDataList(startPage, pageLength, Search, orderColunm, orderType, staff_name, atten_date);

	}

	@PostMapping("/getTotalAttendance_dataCount")

	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String staff_name,String atten_date) {
		return Attdao.DataTableAttendanceDataTotalCount(Search, staff_name,atten_date);

	}
	
}
