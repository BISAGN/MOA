package com.AyushEdu.controller.LMS_Attendance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
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
import com.AyushEdu.dao.LMS_Attendance.Staff_Attendance_ReportDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Staff_Attendance_ReportContoller {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	Staff_Attendance_ReportDao SARdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	

	@GetMapping(value = "/staff_attend_Report_url")
	public ModelAndView staff_attend_Report_url(HttpServletRequest request,ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		
	
		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		String userid = session.getAttribute("userId").toString();
		
		String userid1 = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid1).get(0);
		
		System.err.println("role-----------============="+role);

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("staff_attend_Report_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			int day = date.getDay();
			Calendar calendar = Calendar.getInstance();
			int month = calendar.get(Calendar.MONTH) + 1;
			System.err.println("MONTH--------------------"+month);
			int year = calendar.get(Calendar.YEAR);
			
			Calendar c = Calendar.getInstance();
			String mm = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);

			String mo_yr = String.valueOf(year)+"-"+String.valueOf(month);
			
			if (month > 9) {

				model.put("list", SARdao.staff_attendance_report1(String.valueOf(month), String.valueOf(year),"","",role,role_id,userid,instid));
			}
			if (month < 10) {

				model.put("list", SARdao.staff_attendance_report1("0" + String.valueOf(month), String.valueOf(year),"","",role,role_id,userid,instid));
			}
			model.put("msg", msg);
			model.put("month", month);
			model.put("mo_yr", mo_yr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("staff_Report_New_Tiles");
	}

	@RequestMapping(value = "/search_month_staff_attend", method = RequestMethod.POST)
	public ModelAndView search_month_staff_attend(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, String month1, String name1, String teach_code1) {
		
		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		String userid = session.getAttribute("userId").toString();
		
		String userid1 = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid1).get(0);
		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("staff_attend_Report_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			System.out.println("hi----------------------------------" + month1);
			// String month_jsp = new SimpleDateFormat("MMMM-yyyy").format(month1);
			// model.put("month", month_jsp);
			model.put("list", SARdao.staff_attendance_report1(month1.split("-")[1], month1.split("-")[0],name1,teach_code1,role,role_id,userid,instid));
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
			String month = new SimpleDateFormat("MMMM-yyyy").format(month_year_date);
// String date=day+"";
			model.put("date", lastDay);
			model.put("month", month1.split("-")[1]);
			model.put("name1", name1);
			model.put("teach_code1", teach_code1);
			

// ArrayList<ArrayList<String>> listofemp = attendanceDAO.atdnsreport(emp_name1,
// month1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("staff_Report_New_Tiles");
	}
	
	// ===============================================Staff-print========================================================
			@RequestMapping(value = "/get_StaffAtdncReport", method = RequestMethod.POST)
			public ModelAndView get_StaffAtdncReport(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,

					@RequestParam(value = "date2", required = false) String date, String typeReport) {
				//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("staff_attend_Report_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String role = session.getAttribute("rolename").toString();
				String role_id = session.getAttribute("roleid").toString();
				String userid = session.getAttribute("userId").toString();
				
				String userid1 = session.getAttribute("userId_for_jnlp").toString();
				String instid = common.getInstIdfromUserid( sessionFactory,  userid1).get(0);
				
				
				System.err.println("inside ------"+date.substring(0,4)+"=============="+date.substring(5,7));
				try {
					ArrayList<ArrayList<String>> list = SARdao.staff_attendance_report1(date.substring(5,7),date.substring(0,4),"","",role,role_id,userid,instid);
					if (list.size() == 0) {

						Mmap.put("msg", "Data Not Available.");
						Mmap.put("Nmsg", "1");
					} else {

						String currdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						Date presentDate = null;
						presentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(currdate);

						String username = session.getAttribute("username").toString();
//						Session session1 = this.sessionFactory.openSession();
//						Transaction tx1 = session1.beginTransaction();
//						Query qry = session1.createQuery(
//								"update leave_attendence set print_by=:print_by, print_date=:print_date  where attendance_date=:attendance_date");
//						qry.setParameter("print_by", username);
//						qry.setParameter("print_date", presentDate);
//						qry.setParameter("attendance_date", presentDate);
//						qry.executeUpdate();
//						tx1.commit();
//						session1.close();
//						Mmap.put("list", list);
//						Mmap.put("list.size()", list.size());

						Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
						int day = calendar.get(Calendar.DATE);
						Date date23 = new Date();
						String date1 = new SimpleDateFormat("MMMM-yyyy-dd").format(date23);
						System.err.println("current dateeeeeeeeeeeeeeeeeeee"+date1);
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
								
								
								
//								if (current_time.compareTo(search_time) > 0) {
//									for (int i = 1; i <= lastDay_int; i++) {
//										if (lastDay_int > 9) {
//											TH.add(String.valueOf(i));
//										} else {
//											TH.add("0" + String.valueOf(i));
//										}
//									}
//								} else {
//									for (int i = 1; i <= day; i++) {
//										if (day > 9) {
//											TH.add(String.valueOf(i));
//										} else {
//											TH.add("0" + String.valueOf(i));
//										}
//									}
//								}
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
				return new ModelAndView("staff_Report_New_Tiles");
			}
}
