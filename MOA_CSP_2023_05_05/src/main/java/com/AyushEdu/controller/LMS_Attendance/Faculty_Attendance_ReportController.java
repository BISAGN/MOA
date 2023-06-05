package com.AyushEdu.controller.LMS_Attendance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Attendance.Faculty_Attendance_ReportDao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Faculty_Attendance_ReportController {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	Faculty_Attendance_ReportDao FARdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@GetMapping(value = "/Faculty_Attend_Report_url")
	public ModelAndView Faculty_Attend_Report_url(HttpServletRequest request,ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
		String role = session.getAttribute("rolename").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
//		System.err.println("\n \n userid---------------"+userid+"\n \n");

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Faculty_Attend_Report_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String TCorAID = "";
			
			if(role.contains("NCH")) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("select ayush_id from EDU_LMS_TEACHER_DTL where user_id=:userid");
				q.setParameter("userid", Integer.parseInt(userid));
				@SuppressWarnings("unchecked")
				List<String> clist = (List<String>) q.list();
				TCorAID = clist.get(0).toString();
				tx.commit();
				sessionHQL.close();
			}
			if(role.contains("NCISM")) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("select teacher_code from EDU_LMS_FACULTY_NCH where userid=:userid ");
				q.setParameter("userid", userid);
				@SuppressWarnings("unchecked")
				List<String> clist = (List<String>) q.list();
				TCorAID = clist.get(0).toString();
				tx.commit();
				sessionHQL.close();
			}
			
//			System.err.println("\n \n TCorAID---------------"+TCorAID+"\n \n");
			
			Calendar calendar = Calendar.getInstance();
			int month = calendar.get(Calendar.MONTH) + 1;
			int year = calendar.get(Calendar.YEAR);
			String mo_yr = String.valueOf(year)+"-"+String.valueOf(month);
			if (month > 9) {
				model.put("list", FARdao.faculty_attendance_report(String.valueOf(month), String.valueOf(year),TCorAID,role,instid));
			}
			if (month < 10) {
				model.put("list", FARdao.faculty_attendance_report("0" + String.valueOf(month), String.valueOf(year),TCorAID,role,instid));
			}
			model.put("msg", msg);
			model.put("month", month);
			model.put("mo_yr", mo_yr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Faculty_Report_Tiles");
	}

	@RequestMapping(value = "/search_month_Faculty_Attend", method = RequestMethod.POST)
	public ModelAndView search_month_Faculty_Attend(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String month1, String name1, String teach_code1) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Faculty_Attend_Report_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String role = session.getAttribute("rolename").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		
		try {
			
			String TCorAID = "";
			
			if(role.contains("NCH")) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("select ayush_id from EDU_LMS_TEACHER_DTL where user_id=:userid");
				q.setParameter("userid", Integer.parseInt(userid));
				@SuppressWarnings("unchecked")
				List<String> clist = (List<String>) q.list();
				TCorAID = clist.get(0).toString();
				tx.commit();
				sessionHQL.close();
			}
			if(role.contains("NCISM")) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("select teacher_code from EDU_LMS_FACULTY_NCH where userid=:userid ");
				q.setParameter("userid",userid);
				@SuppressWarnings("unchecked")
				List<String> clist = (List<String>) q.list();
				TCorAID = clist.get(0).toString();
				tx.commit();
				sessionHQL.close();
			}
			
			model.put("list", FARdao.faculty_attendance_report(month1.split("-")[1], month1.split("-")[0],TCorAID,role,instid));
			model.put("month_year", month1);
			
			Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
			int day = calendar2.get(Calendar.DATE);
			month1 += "-" + day;
			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date month_year_date = format2.parse(month1);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(month_year_date);

			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);

			Date lastDayOfMonth = calendar.getTime();

			String lastDay = new SimpleDateFormat("dd").format(lastDayOfMonth);
			model.put("date", lastDay);
			model.put("month", month1.split("-")[1]);
			model.put("name1", name1);
			model.put("teach_code1", teach_code1);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Faculty_Report_Tiles");
	}

}
