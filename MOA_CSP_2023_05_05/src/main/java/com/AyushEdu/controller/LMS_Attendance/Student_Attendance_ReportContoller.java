package com.AyushEdu.controller.LMS_Attendance;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Attendance.EDU_LMS_STUDENT_ATTENDANCE_REPORT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Attendance.Student_Attendance_ReportDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Student_Attendance_ReportContoller {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@Autowired
	Student_Attendance_ReportDao  SARdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	
	
	@GetMapping(value = "/StudentAttUrl")
	public ModelAndView StudentAttUrl(HttpServletRequest request,ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		try {
			
//			Calendar calendar = Calendar.getInstance();             
//	        int month = calendar.get(Calendar.MONTH) + 1;
//	        int year = calendar.get(Calendar.YEAR);
	       
//			int month;
//	        GregorianCalendar date = new GregorianCalendar();      
//	        month = date.get(Calendar.MONTH);
//	        month = month+1;
	        
//	        String m1="";
//	        if(month > 9) {
//	        	 System.err.println("IN 1 month-"+month+"-year-"+year);
//	        	model.put("list", SARdao.student_attendance_report(String.valueOf(month),String.valueOf(year)));
//	        }
//	        if(month < 10) {
//	        	 System.err.println("IN 2 month-"+month+"-year-"+year);
//	        	model.put("list", SARdao.student_attendance_report("0"+String.valueOf(month),String.valueOf(year)));
//	        }
//			SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("StudentAttUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String role = session.getAttribute("rolename").toString();
			String userid = session.getAttribute("userId").toString();
			
			String month="";
			String year="";
			
				Calendar calendar = Calendar.getInstance();
				month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
				year = String.valueOf(calendar.get(Calendar.YEAR));
			
			if(String.valueOf(month).length()==1) {
				month="0"+String.valueOf(month);
			}
			
			model.put("msg", msg);
			model.put("SysDegProf",SARdao.getStudentSysDegProf(userid,role));
			model.put("month", month);
			model.put("mo_yr",year+"-"+month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Student_Att_Report_Tiles");
	}
	
	@PostMapping("/getCourseListofStudent")
	public @ResponseBody List<Map<String, Object>> getCourseListofStudent(String crsid,String system,String degree,String professional, HttpSession session) {
		
		String userid1 = session.getAttribute("userId").toString();

		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		return SARdao.getCourselistofStudent(system,degree,professional);
	}
	
	@RequestMapping(value = "/getStu_ChildUrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getStu_ChildUrl(String crsid,String searchmonth, HttpSession session,ModelMap model) throws ParseException {
		
		String userid1 = session.getAttribute("userId").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		
//		System.err.println("\n\n searchmonth---"+searchmonth);
		
		String month="";
		int year=0;
		if(searchmonth.equals("")) {
			Calendar calendar = Calendar.getInstance();
			month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			year = calendar.get(Calendar.YEAR);
		}else {
			month = searchmonth.split("-")[1];
			year = Integer.parseInt(searchmonth.split("-")[0]);
		}
		if(String.valueOf(month).length()==1) {
			month="0"+String.valueOf(month);
		}
//		System.err.println("\n\n month---"+month);
	 ArrayList<ArrayList<String>> list = SARdao.getPopup_ChildDatalist(userid1,crsid,role,role_id,month, String.valueOf(year),instid);
		return list;
	}
	
	@RequestMapping(value = "/getCountofPA", method = RequestMethod.POST)
	public @ResponseBody   List<Map<String, Object>> getCountofPA(String course_id,String attendance, HttpSession session) {
		String userid1 = session.getAttribute("userId").toString();

		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		
		System.err.println("course_name============"+course_id);
	 System.err.println("userid====================="+userid1);
	 return SARdao.getStudcountPA(course_id,userid1,role,attendance);
	}
	
	
	
	
	
	
	@GetMapping(value = "/student_attend_Report_url")
	public ModelAndView student_attend_Report_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		try {
			Calendar calendar = Calendar.getInstance();             
	        int month = calendar.get(Calendar.MONTH) + 1;
	        int year = calendar.get(Calendar.YEAR);
	       
//			int month;
//	        GregorianCalendar date = new GregorianCalendar();      
//	        month = date.get(Calendar.MONTH);
//	        month = month+1;
	        String m1="";
	        if(month > 9) {
	        	 System.err.println("IN 1 month-"+month+"-year-"+year);
	        	model.put("list", SARdao.student_attendance_report(String.valueOf(month),String.valueOf(year)));
	        }
	        if(month < 10) {
	        	 System.err.println("IN 2 month-"+month+"-year-"+year);
	        	model.put("list", SARdao.student_attendance_report("0"+String.valueOf(month),String.valueOf(year)));
	        }
			model.put("msg", msg);
			model.put("month",month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Student_Report_New_Tiles");
	}
	
	@RequestMapping(value = "/search_month_student_attend", method = RequestMethod.POST)
	public ModelAndView search_month_student_attend(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, String month1,String year,String crsid) {
		try {
System.out.println("hi-----------------xyz-----------------"+month1);
			// String month_jsp = new SimpleDateFormat("MMMM-yyyy").format(month1);
			// model.put("month", month_jsp);
		String userid1 = session.getAttribute("userId").toString();
		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);

			model.put("list", SARdao.getPopup_ChildDatalist(userid1,crsid,role,role_id,String.valueOf(month1), String.valueOf(year),instid));
			model.put("month_year", month1);
//			model.put("month_year", year);
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
			String month = new SimpleDateFormat("MMMM-yyyy").format(month_year_date);
			// String date=day+"";
			model.put("date", lastDay);
			model.put("month", month1.split("-")[1]);

			// ArrayList<ArrayList<String>> listofemp = attendanceDAO.atdnsreport(emp_name1,
			// month1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Student_Report_New_Tiles");
	}
	
	// ===============================================Students-print========================================================
		@RequestMapping(value = "/get_AtdncReport", method = RequestMethod.POST)
		public ModelAndView get_AtdncReport(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "date2", required = false) String date, String typeReport) {
			System.err.println("DATE---+++"+date);
			
			System.err.println("AAAAAAAAAAAAAinside ------"+date.substring(0,4)+"=============="+date.substring(5,7));
			try {
				ArrayList<ArrayList<String>> list = SARdao.student_attendance_report(date.substring(5,7),date.substring(0,4));
				if (list.size() == 0) {

					Mmap.put("msg", "Data Not Available.");
					Mmap.put("Nmsg", "1");
				} else {

					String currdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					Date presentDate = null;
					presentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(currdate);

					String username = session.getAttribute("username").toString();
					String userid = session.getAttribute("userId").toString();
//					Session session1 = this.sessionFactory.openSession();
//					Transaction tx1 = session1.beginTransaction();
//					Query qry = session1.createQuery(
//							"update leave_attendence set print_by=:print_by, print_date=:print_date  where attendance_date=:attendance_date");
//					qry.setParameter("print_by", username);
//					qry.setParameter("print_date", presentDate);
//					qry.setParameter("attendance_date", presentDate);
//					qry.executeUpdate();
//					tx1.commit();
//					session1.close();
//					Mmap.put("list", list);
//					Mmap.put("list.size()", list.size());

					Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
					int day = calendar.get(Calendar.DATE);
					Date date23 = new Date();
					String date1 = new SimpleDateFormat("MMMM-yyyy").format(date23);

					Date today = new Date();
					String current_time = new SimpleDateFormat("yyyy-MM-dd").format(today);
					Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
					int current_day = calendar2.get(Calendar.DATE);

					date += "-" + current_day;

					DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					Date month_year_date = format2.parse(date);

					String search_time = new SimpleDateFormat("yyyy-MM-dd").format(month_year_date);

					calendar.setTime(month_year_date);

					calendar.add(Calendar.MONTH, 1);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					calendar.add(Calendar.DATE, -1);

					Date lastDayOfMonth = calendar.getTime();

					String lastDay = new SimpleDateFormat("dd").format(lastDayOfMonth);
					int lastDay_int = Integer.parseInt(lastDay);
					String month = new SimpleDateFormat("MMMM-yyyy").format(month_year_date);
					if (typeReport != null && typeReport.equals("pdfL")) {
						if (list.size() > 0) {
							List<String> TH = new ArrayList<String>();
							// TH.add("Ser No");
							TH.add("Name");
							
							if (month.contains("January") || month.contains("March") || month.contains("May") || month.contains("July")
									|| month.contains("August") || month.contains("October") || month.contains("December")) {
								for (int i = 1; i < 32; i++) {

									TH.add(String.valueOf(i));
							}

							}else if (month.contains("February")) {
								for (int i = 1; i < 29; i++) {

									TH.add(String.valueOf(i));
							}

							}else {
								for (int i = 1; i < 31; i++) {

									TH.add(String.valueOf(i));
							}

							}
							
							
							
//							if (current_time.compareTo(search_time) > 0) {
//								for (int i = 1; i <= lastDay_int; i++) {
//									if (lastDay_int > 9) {
//										TH.add(String.valueOf(i));
//									} else {
//										TH.add("0" + String.valueOf(i));
//									}
//								}
//							} else {
//								for (int i = 1; i <= day; i++) {
//									if (day > 9) {
//										TH.add(String.valueOf(i));
//									} else {
//										TH.add("0" + String.valueOf(i));
//									}
//								}
//							}
							TH.add("Present Day");

							String Heading = "\n ATTENDANCE SHEET REPORT OF " + month;
							System.out.println("2222222"+list);
							return new ModelAndView(new DownloadPdf2("L", TH, Heading, username,month), "userList", list);
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("Student_Report_New_Tiles");
		}
		
		
		
//		public List<String> getStuList(SessionFactory sessionFactory, String userid,String system_id,String degree_id,String professional_id) {
//
//			Session sessionHQL = sessionFactory.openSession();
//			Transaction tx1 = sessionHQL.beginTransaction();
//			try {
//				Query q1 = sessionHQL.createQuery("select cast(institute_id as text) from UserLogin where userid=:userid");
//				q1.setParameter("userid", Integer.parseInt(userid));
//				List<String> list = (List<String>) q1.list();
//				tx1.commit();
//				return list;
//			} catch (RuntimeException e) {
//				return null;
//			} finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//				}
//			}
//		}
}
