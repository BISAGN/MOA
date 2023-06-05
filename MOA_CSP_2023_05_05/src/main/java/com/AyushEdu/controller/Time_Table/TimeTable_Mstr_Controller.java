package com.AyushEdu.controller.Time_Table;

import java.math.BigInteger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_TEACHING_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS_MSTR;
import com.AyushEdu.Models.Time_Table.EDU_TT_CLASSROOM_MSTR;
import com.AyushEdu.Models.Time_Table.EDU_TT_TIME_TABLE_LAYOUT;
import com.AyushEdu.Models.Time_Table.EDU_TT_TIME_TABLE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Examination.View_Internal_assessment_MarksDao;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.Time_Table.TIME_TABLE_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TimeTable_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	TIME_TABLE_DAO ttdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@Autowired
	Sys_Deg_Map_Inst_DAO  dmdao;
	
	@Autowired
	View_Internal_assessment_MarksDao IAM;
	
	//-----------------------------------------Time Table---------------------------------------//
	
	@RequestMapping(value = "admin/TimeTable", method = RequestMethod.GET)
	public ModelAndView TimeTable(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("TimeTable", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			 Session sessiont = sessionFactory.openSession();
			 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			 int institute_id = institute_idList.get(0).getInstitute_id();
			 Mmap.put("system", dmdao.Getsytemid_fetch(userid));
			 Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			 Mmap.put("getdegreeList", getdegreeList());
			 Mmap.put("getProfessionalList", getProfessionalList());
			 Mmap.put("getClassroomList", getClassroomList(institute_id));
			 Mmap.put("getAcmDetList", getAcmDetList());
			 Mmap.put("getAcademicList", getAcademicList(institute_id));
			 Mmap.put("gettype_of_teachingList", gettype_of_teachingList());
			 Mmap.put("getExam_Type", common.getExam_Type(sessionFactory));
			 Mmap.put("getExam_SerialList", common.getExam_SerialList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("TimeTable_Tiles");
	}

	//----------------------------------------View Time Table------------------------------------//
	
	
	@RequestMapping(value = "/ViewTimeTable", method = RequestMethod.GET)
	public ModelAndView ViewTimeTable(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("ViewTimeTable", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("ViewTimeTable", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("Current_Prof", IAM.Getdegreeid_fetch(userid));
			Mmap.put("msg", msg);
			Mmap.put("getProfessionalList", getProfessionalList());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("ViewTimeTable_Tiles");
	}



//==========================================SAVE/view//Edit Time_Table==========================================//	

@RequestMapping(value = "/getTime_TableDetails", method = RequestMethod.POST)
public @ResponseBody String getTime_TableDetails(
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, 
		RedirectAttributes ra,String day, String degree, String course, String professional,
		String faculty, String classroom, String start_time, String end_time, String lec_type,String ldt, String hid, String radiobt, Principal principal,HttpSession httpsession) {
		String username = principal.getName();
		Date date = new Date();
		try {System.err.println("ldt----"+ldt+" "+"hid------"+hid+ " "+"radiobt----"+radiobt);
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session session2 = sessionFactory.openSession();
		String old_start_time = "";
		String old_end_time = "";
		msg = "";
		if (!hid.equals("0") && !hid.equals("")) {
			EDU_TT_TIME_TABLE_LAYOUT old_lat = (EDU_TT_TIME_TABLE_LAYOUT) session2.get(EDU_TT_TIME_TABLE_LAYOUT.class,
					Integer.parseInt(hid));
			old_start_time = old_lat.getStart_time();
			old_end_time = old_lat.getEnd_time();
		}
		
		//--------------------------------------------------Hiral------------------------------------------------//
		
		//----------------------------------------Validate Time For Layout---------------------------------------//
		
		
		Session sessionQ = this.sessionFactory.openSession();
		if (hid.equals("0")) {
			
			System.err.println("-------->validationtime----------");
		long t1 = ttdao.ValidateTimeForLayout(day, degree, professional, start_time, end_time, institute_id,hid);
		if(t1 > 0) {
			return "Data Already Exist";	
		} 
		
		
		}else {
			long t2 = ttdao.ValidateTimeForLayout(day, degree, professional, start_time, end_time, institute_id,hid);
			
			if(t2 > 0) {
				return "Data Already Exist";	
			}
		}
		
		//////////
		
			if (radiobt.equals("single")) {
				Date dt = sdf.parse(ldt);
				Transaction tx = session2.beginTransaction();
				String hql = "update EDU_TT_TIME_TABLE_MASTER set course=:course,faculty=:faculty,classroom=:classroom,start_time=:start_time,end_time=:end_time,lec_type=:lec_type,modified_by=:modified_by,modified_date=:modified_date where ldate=:ldate and degree=:degree and professional=:professional and start_time=:old_start_time and end_time=:old_end_time and institute_id=:institute_id";

				Query query = session2.createQuery(hql)
						.setParameter("degree", Integer.parseInt(degree))
						.setParameter("course", Integer.parseInt(course))
						.setParameter("faculty", Integer.parseInt(faculty))
						.setParameter("professional", Integer.parseInt(professional))
						.setParameter("classroom", Integer.parseInt(classroom))
						.setParameter("start_time", start_time)
						.setParameter("end_time", end_time)
						.setParameter("lec_type", lec_type)
						.setParameter("ldate", dt)
						.setParameter("modified_by", username)
						.setParameter("institute_id", institute_id)
						.setParameter("modified_date", new Date());
				if (!hid.equals("0") && !hid.equals("")) {
					query .setParameter("old_start_time",old_start_time)
					  .setParameter("old_end_time", old_end_time);
				}else {
					query .setParameter("old_start_time",start_time)
					  .setParameter("old_end_time", end_time);
				}
				msg = query.executeUpdate() > 0 ? "1" : "0";
				session2.flush();
				session2.clear();
				tx.commit();
				
			}else {
				List<Date> AcademicList= getacademicList( professional,institute_id);
				System.err.println("-----------"+AcademicList.get(1) + " size ");
				if (AcademicList.get(0)==null || AcademicList.get(1)==null) {
					return "Please Add Proper Data In Academic Details";	
				}
				Date s_date=AcademicList.get(0);
				Date e_date=AcademicList.get(1);
				System.err.println("s_date----"+s_date+ " "+"e_date-----"+e_date);
				Date dt = sdf.parse("01/09/2022");
				Date enddt = sdf.parse("23/09/2022");
				dt = s_date;
				enddt = e_date;
				HashMap<String, Date> map = new HashMap<>();
				map.put("day"+dt.getDay(), dt);
				for (int i = 1; i < 7; i++) {
					 Calendar cal = Calendar.getInstance();
				        cal.setTime(dt);
				        cal.add(Calendar.DATE, i); //minus number would decrement the days
				        System.err.println("date - "+cal.getTime()); 
				         map.put("day"+cal.getTime().getDay(), cal.getTime());
				}
				
				
				List<Date> list=new ArrayList<Date>();
				if (day.equals("monday")) {
					Date dm = map.get("day1");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}
				
				}
				if (day.equals("tuesday")) {
					Date dm = map.get("day2");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("wednesday")) {
					Date dm = map.get("day3");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("thursday")) {
					Date dm = map.get("day4");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("friday")) {
					Date dm = map.get("day5");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("saturday")) {
					Date dm = map.get("day6");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("sunday")) {
					Date dm = map.get("day0");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				
				System.err.println("--ttday----"+day+"-----"+degree+"-----"+professional+"-0-------"+start_time+"------"+end_time);
				Session session1 = this.sessionFactory.openSession();
				
				System.err.println("-------->validationtime----------");
				long s = ttdao.ValidateTimeForLayout(day, degree, professional, start_time, end_time, institute_id,hid); 
				
				if(s > 0) {
					return "Data Already Exist";	
				}
//				Long s = (Long) session1.createQuery("select count(*) from EDU_TT_TIME_TABLE_LAYOUT where ttday=:ttday and degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and institute_id=:institute_id")
//						  .setParameter("ttday", day)
//						  .setParameter("degree", Integer.parseInt(degree))
//						  .setParameter("professional", Integer.parseInt(professional))
//						  .setParameter("start_time",start_time)
//						  .setParameter("institute_id", institute_id)
//						  .setParameter("end_time", end_time).uniqueResult();
				System.err.println("--s----"+s);
				if (hid.equals("0")) {
					System.err.println("-if--"+s);
					Transaction tx = session1.beginTransaction();
					EDU_TT_TIME_TABLE_LAYOUT tt = new EDU_TT_TIME_TABLE_LAYOUT();
	
					tt.setClassroom(Integer.parseInt(classroom));
					tt.setDegree(Integer.parseInt(degree));
					tt.setCourse(Integer.parseInt(course));
					tt.setFaculty(Integer.parseInt(faculty));
					tt.setProfessional(Integer.parseInt(professional));
					tt.setStart_time(start_time);
					tt.setEnd_time(end_time);
					tt.setLec_type(lec_type);
					tt.setCreated_by(username); 
					tt.setCreated_date(date);
					tt.setTtday(day);
					tt.setInstitute_id(institute_id);
						
					session1.save(tt);
					session1.flush();
					session1.clear();
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else{
					System.err.println("-else--"+s);
					Transaction tx = session1.beginTransaction();
					String hql = "update EDU_TT_TIME_TABLE_LAYOUT set course=:course,faculty=:faculty,classroom=:classroom,start_time=:start_time,end_time=:end_time,lec_type=:lec_type,modified_by=:modified_by,modified_date=:modified_date where  ttday=:ttday and degree=:degree and professional=:professional and institute_id=:institute_id";
						hql+=" and id=:id";
	
					Query query = session1.createQuery(hql)
							.setParameter("degree", Integer.parseInt(degree))
							.setParameter("course", Integer.parseInt(course))
							.setParameter("faculty", Integer.parseInt(faculty))
							.setParameter("professional", Integer.parseInt(professional))
							.setParameter("classroom", Integer.parseInt(classroom))
							.setParameter("start_time", start_time)
							.setParameter("end_time", end_time)
							.setParameter("lec_type", lec_type)
							.setParameter("ttday", day)
							.setParameter("institute_id", institute_id)
							.setParameter("modified_by", username)
							.setParameter("modified_date", new Date());
						query.setParameter("id", Integer.parseInt(hid));
					
					msg = query.executeUpdate() > 0 ? "1" : "0";
					session1.flush();
					session1.clear();
					tx.commit();
					if (msg.equals("1")) {
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						ra.addAttribute("msg", "Data Not Updated.");
					}
				} 
				
			for(int i =0; i<list.size(); i++) {
				Session sessionHQL = this.sessionFactory.openSession();
				
				Long d = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_EVENT_MSTR where event_date=:ldate and institute_id=:institute_id")
						.setParameter("institute_id", institute_id)
						  .setParameter("ldate", list.get(i)).uniqueResult();
				
				Long ex = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_ACADEMIC_DETAILS where  professional=:professional and :ldate between starting_date and ending_date and academic_details='002' and institute_id=:institute_id and exam_type!=8")
						  .setParameter("ldate", list.get(i))  .setParameter("professional", Integer.parseInt(professional)).setParameter("institute_id", institute_id). uniqueResult();
				System.err.println("ex - "+ex);
				
				Query queryc =  sessionHQL.createQuery("select count(*) from EDU_TT_TIME_TABLE_MASTER where ldate=:ldate and degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and institute_id=:institute_id");
						queryc.setParameter("ldate", list.get(i))
						  .setParameter("degree", Integer.parseInt(degree))
						  .setParameter("institute_id", institute_id)
						  .setParameter("professional", Integer.parseInt(professional));
				if (!hid.equals("0") && !hid.equals("")) {
					queryc .setParameter("start_time",old_start_time)
					  .setParameter("end_time", old_end_time);
				}else {
					queryc .setParameter("start_time",start_time)
					  .setParameter("end_time", end_time);
				}
				System.err.println("--ldate----"+list.get(i)+"--hid----"+hid+"-----"+old_start_time+"-----"+old_end_time+"-0-------"+start_time+"------"+end_time);
						 
						  Long c =(Long) queryc.uniqueResult();
				
				
				System.err.println(list.get(i)+"--"+c );
					if (d == 0 && ex == 0) {
					if (c == 0) {
						Transaction tx = sessionHQL.beginTransaction();
						EDU_TT_TIME_TABLE_MASTER tt = new EDU_TT_TIME_TABLE_MASTER();
	
						tt.setClassroom(Integer.parseInt(classroom));
						tt.setDegree(Integer.parseInt(degree));
						tt.setCourse(Integer.parseInt(course));
						tt.setFaculty(Integer.parseInt(faculty));
						tt.setProfessional(Integer.parseInt(professional));
						tt.setStart_time(start_time);
						tt.setEnd_time(end_time);
						tt.setLec_type(lec_type);
						tt.setLdate(list.get(i));
						tt.setCreated_by(username);
						tt.setCreated_date(date);
						tt.setInstitute_id(institute_id);
							
						sessionHQL.save(tt);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
							Transaction tx = sessionHQL.beginTransaction();
							String hql = "update EDU_TT_TIME_TABLE_MASTER set course=:course,faculty=:faculty,classroom=:classroom,start_time=:start_time,end_time=:end_time,lec_type=:lec_type,modified_by=:modified_by,modified_date=:modified_date where ldate=:ldate and degree=:degree and professional=:professional and start_time=:old_start_time and end_time=:old_end_time and institute_id=:institute_id";
	
							Query query = sessionHQL.createQuery(hql)
									.setParameter("degree", Integer.parseInt(degree))
									.setParameter("course", Integer.parseInt(course))
									.setParameter("faculty", Integer.parseInt(faculty))
									.setParameter("professional", Integer.parseInt(professional))
									.setParameter("classroom", Integer.parseInt(classroom))
									.setParameter("start_time", start_time)
									.setParameter("end_time", end_time)
									.setParameter("lec_type", lec_type)
									.setParameter("ldate", list.get(i))
									.setParameter("institute_id", institute_id)
									.setParameter("modified_by", username)
									.setParameter("modified_date", new Date());
							if (!hid.equals("0") && !hid.equals("")) {
								query .setParameter("old_start_time",old_start_time)
								  .setParameter("old_end_time", old_end_time);
							}else {
								query .setParameter("old_start_time",start_time)
								  .setParameter("old_end_time", end_time);
							}
							msg = query.executeUpdate() > 0 ? "1" : "0";
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							if (msg.equals("1")) {
								ra.addAttribute("msg", "Data Updated Successfully.");
							} else {
								ra.addAttribute("msg", "Data Not Updated.");
							}
					}
					}
			}
			}
	        			
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_LMS_COURSE_MASTER where id=:id").setParameter("id", Integer.parseInt(course));
		 List<EDU_LMS_COURSE_MASTER> courseList = q0.list();
		 Query q1 = session.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(faculty));
		 List<UserLogin> facultyList = q1.list();
		 Query q2 = session.createQuery("from EDU_TT_CLASSROOM_MSTR where id=:id").setParameter("id", Integer.parseInt(classroom));
		 List<EDU_TT_CLASSROOM_MSTR> ClassroomList = q2.list();
		 
	     session.getTransaction().commit();
	     session.close(); 
		
       return ( courseList.get(0).getCourse_name() +"</br>("+ facultyList.get(0).getLogin_name() + ")</br>class:"+ ClassroomList.get(0).getClassroom());
       
		}


	//----------------------------------------Extra Class-------------------------------------------//

@RequestMapping(value = "/getExTime_TableDetails", method = RequestMethod.POST)
public @ResponseBody String getExTime_TableDetails(
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, 
		RedirectAttributes ra,String day, String degree, String course, String professional, String exam_type, String exam_serial,
		String faculty, String classroom, String start_time, String end_time, String lec_type, String ldt, String hid, String radiobt, String radiobt2, Principal principal,HttpSession httpsession) throws ParseException{
	
		
	
		String username = principal.getName();
		Date date = new Date();
		try {
		System.err.println("ldt----"+ldt+" "+"hid------"+hid+ " "+"radiobt----"+radiobt);
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Session session2 = sessionFactory.openSession();
		String old_start_time = "";
		String old_end_time = "";
		msg = "";
		if (!hid.equals("0") && !hid.equals("")) {
			EDU_TT_TIME_TABLE_MASTER old_lat = (EDU_TT_TIME_TABLE_MASTER) session2.get(EDU_TT_TIME_TABLE_MASTER.class,
					Integer.parseInt(hid));
			old_start_time = old_lat.getStart_time();
			old_end_time = old_lat.getEnd_time();
		}		
		
		//--------------------------------------------------Hiral------------------------------------------------//
		
		//----------------------------------------Validate Time For Extra-class---------------------------------------//
			
			if (radiobt.equals("single_day")) {
				Date dt = sdf.parse(ldt);
				Session session1 = this.sessionFactory.openSession();
				/////////
				if (radiobt2.equals("periodic_exam")) {
				
				
				long t = ttdao.ValidateTimeForExtraClass(ldt, degree, professional, start_time, end_time, institute_id);
		//---------------------------------
//				List<Date> list=new ArrayList<Date>();
//				for(int i =0; i<list.size(); i++) {
//				Session sessionHQL = this.sessionFactory.openSession();
//				Long et = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_ACADEMIC_DETAILS where  professional=:professional and :ldate between starting_date and ending_date and academic_details='004' and institute_id=:institute_id")
//						  .setParameter("ldate", dt)  
//						  .setParameter("professional", Integer.parseInt(professional))
//						  .setParameter("institute_id", institute_id). uniqueResult();
//				System.err.println("et - "+et);
//				}
		//-----------------------------------
				System.err.println("-Periodic_Exam-t----"+t);
				
				if (t == 0) {
					
					List<Date> AcademicList= getacademicList_For_PA( professional,institute_id,exam_serial);
					System.err.println("-----------"+AcademicList.get(1) + " size ");
					if (AcademicList.get(0)==null || AcademicList.get(1)==null) {
						return "Please Add Proper Data In Academic Details";	
					}
					Date s_date=AcademicList.get(0);
					Date e_date=AcademicList.get(1);
					System.err.println("s_date----"+s_date+ " "+"e_date-----"+e_date);
//					Date pa_date = sdf.parse(ldt);
				    Date d = new Date();
				    String currDt = sdf.format(d);


				    if((dt.after(s_date) && (dt.before(e_date))) || (currDt.equals(sdf.format(s_date)) ||currDt.equals(sdf.format(e_date)))){
				 
					System.err.println("---Periodic_Exam ---if--"+t);
					Transaction tx = session1.beginTransaction();
					EDU_TT_TIME_TABLE_MASTER tt = new EDU_TT_TIME_TABLE_MASTER();
	
					tt.setClassroom(Integer.parseInt(classroom));
					tt.setDegree(Integer.parseInt(degree));
					tt.setCourse(Integer.parseInt(course));
					tt.setFaculty(Integer.parseInt(faculty));
					tt.setProfessional(Integer.parseInt(professional));
					tt.setStart_time(start_time);
					tt.setEnd_time(end_time);
					tt.setLec_type("0");
					tt.setExam_type(Integer.parseInt(exam_type));
					tt.setExam_serial(Integer.parseInt(exam_serial));
					tt.setCreated_by(username);
					tt.setCreated_date(date);
					tt.setLdate(dt);
					tt.setInstitute_id(institute_id);
					tt.setExtra_class_status(1);	
					session1.save(tt);
					session1.flush();
					session1.clear();
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.....");
					
				    }else{
						return "Date is not Proper As Compare To Academic Details";
			        }
				} else{				
					return "Please Update the Data First";
				}				
				}
				if(radiobt2.equals("extra_class")){
					
					long m = ttdao.ValidateTimeForExtraClass(ldt, degree, professional, start_time, end_time, institute_id);
			
					
					
					System.err.println("-Periodic_Exam-t----"+m);
					
					if (m == 0) {
						System.err.println("---Periodic_Exam ---if--"+m);
						Transaction tx = session1.beginTransaction();
						EDU_TT_TIME_TABLE_MASTER tt = new EDU_TT_TIME_TABLE_MASTER();
		
						tt.setClassroom(Integer.parseInt(classroom));
						tt.setDegree(Integer.parseInt(degree));
						tt.setCourse(Integer.parseInt(course));
						tt.setFaculty(Integer.parseInt(faculty));
						tt.setProfessional(Integer.parseInt(professional));
						tt.setStart_time(start_time);
						tt.setEnd_time(end_time);
						tt.setLec_type(lec_type);
						tt.setCreated_by(username);
						tt.setCreated_date(date);
						tt.setLdate(dt);
						tt.setInstitute_id(institute_id);
						tt.setExtra_class_status(1);	
						session1.save(tt);
						session1.flush();
						session1.clear();
						tx.commit();
						ra.addAttribute("msg", "Data Saved Successfully.....");
					} else{				
						return "Please Update the Data First";
					}
				}
				///////////
				
			}else {
				List<Date> AcademicList= getacademicList( professional,institute_id);
				System.err.println("-----------"+AcademicList.get(1) + " size ");
				if (AcademicList.get(0)==null || AcademicList.get(1)==null) {
					return "Please Add Proper Data In Academic Details";	
				}
				
				Date s_date=AcademicList.get(0);
				Date e_date=AcademicList.get(1);
				System.err.println("s_date----"+s_date+ " "+"e_date-----"+e_date);
				Date dt = sdf.parse("01/09/2022");
				Date enddt = sdf.parse("23/09/2022");
				dt = s_date;
				enddt = e_date;
				HashMap<String, Date> map = new HashMap<>();
				map.put("day"+dt.getDay(), dt);
				for (int i = 1; i < 7; i++) {
					 Calendar cal = Calendar.getInstance();
				        cal.setTime(dt);
				        cal.add(Calendar.DATE, i); //minus number would decrement the days
				        System.err.println("date - "+cal.getTime()); 
				         map.put("day"+cal.getTime().getDay(), cal.getTime());
				}
				List<Date> list=new ArrayList<Date>();
				if (day.equals("monday")) {
					Date dm = map.get("day1");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}
				}
				if (day.equals("tuesday")) {
					Date dm = map.get("day2");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("wednesday")) {
					Date dm = map.get("day3");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("thursday")) {
					Date dm = map.get("day4");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("friday")) {
					Date dm = map.get("day5");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("saturday")) {
					Date dm = map.get("day6");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				if (day.equals("sunday")) {
					Date dm = map.get("day0");
					list.add(dm);
					while(dm.before(enddt)) {
						 Calendar cal = Calendar.getInstance();
					        cal.setTime(dm);
					        cal.add(Calendar.DATE, 7); //minus number would decrement the days
					        System.err.println("date in- "+cal.getTime()); 
					        dm = cal.getTime();
					        list.add(dm);
					}			
				}
				
				System.err.println("--ttday----"+day+"-----"+degree+"-----"+professional+"-0-------"+start_time+"------"+end_time);
				Session session1 = this.sessionFactory.openSession();
					
				System.err.println("-------->validationtime----------");
				long s = ttdao.ValidateTimeForLayout(day, degree, professional, start_time, end_time, institute_id,hid); 
				
				if(s > 0) {
					return "Data Already Exist";	
				}
			
				
//				Long s = (Long) session1.createQuery("select count(*) from EDU_TT_TIME_TABLE_LAYOUT where ttday=:ttday and degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and institute_id=:institute_id")
//						  .setParameter("ttday", day)
//						  .setParameter("degree", Integer.parseInt(degree))
//						  .setParameter("professional", Integer.parseInt(professional))
//						  .setParameter("start_time",start_time)
//						  .setParameter("institute_id", institute_id)
//						  .setParameter("end_time", end_time).uniqueResult();
				System.err.println("--s----"+s);
				if (s == 0) {
					System.err.println("-if--"+s);
					Transaction tx = session1.beginTransaction();
					EDU_TT_TIME_TABLE_LAYOUT tt = new EDU_TT_TIME_TABLE_LAYOUT();
	
					tt.setClassroom(Integer.parseInt(classroom));
					tt.setDegree(Integer.parseInt(degree));
					tt.setCourse(Integer.parseInt(course));
					tt.setFaculty(Integer.parseInt(faculty));
					tt.setProfessional(Integer.parseInt(professional));
					tt.setStart_time(start_time);
					tt.setEnd_time(end_time);
					tt.setLec_type(lec_type);
					tt.setCreated_by(username); 
					tt.setCreated_date(date);
					tt.setTtday(day);
					tt.setInstitute_id(institute_id);
					tt.setExtra_class_status(1);
						
					session1.save(tt);
					session1.flush();
					session1.clear();
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
				}else {
					ra.addAttribute("msg", "Data Not Saved.");
				}
				
			for(int i =0; i<list.size(); i++) {
				Session sessionHQL = this.sessionFactory.openSession();
				
				Long d = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_EVENT_MSTR where event_date=:ldate and institute_id=:institute_id")
						.setParameter("institute_id", institute_id)
						  .setParameter("ldate", list.get(i)).uniqueResult();
				
				Long ex = (Long) sessionHQL.createQuery("select count(*) from EDU_TT_ACADEMIC_DETAILS where  professional=:professional and :ldate between starting_date and ending_date and academic_details='002' and institute_id=:institute_id and exam_type!=8")
						  .setParameter("ldate", list.get(i))  .setParameter("professional", Integer.parseInt(professional)).setParameter("institute_id", institute_id). uniqueResult();
				System.err.println("ex - "+ex);
				
				Query queryc =  sessionHQL.createQuery("select count(*) from EDU_TT_TIME_TABLE_MASTER where ldate=:ldate and degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and institute_id=:institute_id");
						queryc.setParameter("ldate", list.get(i))
						  .setParameter("degree", Integer.parseInt(degree))
						  .setParameter("institute_id", institute_id)
						  .setParameter("professional", Integer.parseInt(professional));
				if (!hid.equals("0") && !hid.equals("")) {
					queryc .setParameter("start_time",old_start_time)
					  .setParameter("end_time", old_end_time);
				}else {
					queryc .setParameter("start_time",start_time)
					  .setParameter("end_time", end_time);
				}
				System.err.println("--ldate----"+list.get(i)+"--hid----"+hid+"-----"+old_start_time+"-----"+old_end_time+"-0-------"+start_time+"------"+end_time);
						 
						  Long c =(Long) queryc.uniqueResult();
				System.err.println(list.get(i)+"--"+c );
					if (d == 0 && ex == 0) {
					if (c == 0) {
						Transaction tx = sessionHQL.beginTransaction();
						EDU_TT_TIME_TABLE_MASTER tt = new EDU_TT_TIME_TABLE_MASTER();
	
						tt.setClassroom(Integer.parseInt(classroom));
						tt.setDegree(Integer.parseInt(degree));
						tt.setCourse(Integer.parseInt(course));
						tt.setFaculty(Integer.parseInt(faculty));
						tt.setProfessional(Integer.parseInt(professional));
						tt.setStart_time(start_time);
						tt.setEnd_time(end_time);
						tt.setLec_type(lec_type);
						tt.setLdate(list.get(i));
						tt.setCreated_by(username);
						tt.setCreated_date(date);
						tt.setInstitute_id(institute_id);
						tt.setExtra_class_status(1);
							
						sessionHQL.save(tt);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						ra.addAttribute("msg", "Data Saved Successfully..!..");
					}
					}
			}
			}
	        			
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      return "Data Saved Successfully.";
      
		}

public List<EDU_LMS_DEGREE_MASTER> getdegreeList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_LMS_DEGREE_MASTER where status='1'");
	 
	 List<EDU_LMS_DEGREE_MASTER> DegreeList = q0.list();
       session.getTransaction().commit();
       session.close();                
      return DegreeList;
}

public List<Date> getacademicList(String professional,int institute_id) {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("select max(ending_date) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and academic_details='001' and institute_id=:institute_id ");
	 q0.setParameter("professional", Integer.parseInt(professional))
	 .setParameter("institute_id", institute_id);
	 Date max = (Date) q0.uniqueResult();
	 
	 Query q1 = session.createQuery("select min(starting_date) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and academic_details='001' and institute_id=:institute_id ");
	 q1.setParameter("professional", Integer.parseInt(professional))
	 .setParameter("institute_id", institute_id);
	 Date min = (Date) q1.uniqueResult();
	 List<Date> AcademicDAte = new ArrayList<Date>();
	 AcademicDAte.add(min);
	 AcademicDAte.add(max);
	 
      session.getTransaction().commit();
      session.close();                
     return AcademicDAte;
}

public List<Date> getacademicList_For_PA(String professional,int institute_id,String exam_serial) {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("select max(ending_date) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and academic_details='002' and institute_id=:institute_id and exam_serial=:exam_serial and exam_type=8");
	 q0.setParameter("professional", Integer.parseInt(professional))
	 .setParameter("exam_serial", Integer.parseInt(exam_serial))
	 .setParameter("institute_id", institute_id);
	 Date max = (Date) q0.uniqueResult();
	 
	 Query q1 = session.createQuery("select min(starting_date) from EDU_TT_ACADEMIC_DETAILS where professional=:professional and academic_details='002' and institute_id=:institute_id and exam_serial=:exam_serial and exam_type=8");
	 q1.setParameter("professional", Integer.parseInt(professional))
	 .setParameter("exam_serial", Integer.parseInt(exam_serial))
	 .setParameter("institute_id", institute_id);
	 Date min = (Date) q1.uniqueResult();
	 List<Date> AcademicDAte = new ArrayList<Date>();
	 AcademicDAte.add(min);
	 AcademicDAte.add(max);
	 
     session.getTransaction().commit();
     session.close();                
    return AcademicDAte;
}

public List<CC_TB_PROFESSIONAL_MSTR> getProfessionalList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1' order by id");
	 
	 List<CC_TB_PROFESSIONAL_MSTR> DegreeList = q0.list();
      session.getTransaction().commit();
      session.close();                
     return DegreeList;
}
public List<EDU_TT_ACADEMIC_DETAILS_MSTR> getAcmDetList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_TT_ACADEMIC_DETAILS_MSTR order by refer_code");
	 
	 List<EDU_TT_ACADEMIC_DETAILS_MSTR> AcmDetList = (List<EDU_TT_ACADEMIC_DETAILS_MSTR>) q0.list();
       session.getTransaction().commit();
       session.close();                
      return AcmDetList;
}

@SuppressWarnings("deprecation")
public List<EDU_TT_ACADEMIC_DETAILS_MSTR> getAcademicList(int institute_id) {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_TT_ACADEMIC_DETAILS_MSTR ");
//	 	q0.setParameter("institute_id", institute_id);
	 	
	 @SuppressWarnings("unchecked")
	 List<EDU_TT_ACADEMIC_DETAILS_MSTR> AcademicList = (List<EDU_TT_ACADEMIC_DETAILS_MSTR>) q0.list();
        session.getTransaction().commit();
        session.close();                
       return AcademicList;
}

public List<EDU_TT_CLASSROOM_MSTR> getClassroomList(int institute_id) {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_TT_CLASSROOM_MSTR where status='1' and institute_id=:institute_id");
	 	q0.setParameter("institute_id", institute_id);
	 
	 List<EDU_TT_CLASSROOM_MSTR> ClassroomList = q0.list();
     session.getTransaction().commit();
     session.close();                
    return ClassroomList;
}

@PostMapping("/getTimetableList")
public @ResponseBody List<Map<String, Object>> getTimetableList(int startPage, int pageLength,
		String Search, String orderColunm, String orderType, HttpSession httpsession) {
		 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
	return ttdao.getTimetableList(startPage, pageLength, Search, orderColunm, orderType, institute_id);

}

@PostMapping("/getTimetableListCount")
public @ResponseBody long getTimetableListCount( String Search, HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
	return ttdao.getTimetableListCount(Search, institute_id);

}

@RequestMapping(value = "/getweeklytimetable", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getweeklytimetable(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String degree,String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getweeklytimetable(sdate, degree, professional, institute_id);
}
@RequestMapping(value = "/getWeeklyTimetable", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getWeeklyTimetable(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getWeeklyTimetable(sdate, professional, institute_id);
}

@RequestMapping(value = "/getDailyTimetable", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getDailyTimetable(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String degree,String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getDailyTimetable(sdate, degree, professional,institute_id);
}

@RequestMapping(value = "/getcoursedetails", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getcoursedetails(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String degree_id) {
       return ttdao.getcoursedetailsDao(degree_id);

}

@RequestMapping(value = "/getcoursehours", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getcoursehours(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String degree, String professional) {
       return ttdao.getcoursehours(degree, professional);
}

@RequestMapping(value = "/getfacutlydetails", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getfacutlydetails(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String course_id,HttpSession httpsession) {
		Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id(); 
       return ttdao.getfacultydetailsDao(course_id,institute_id);

}

@RequestMapping(value = "/getlayouttimetable", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getlayouttimetable(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String degree, String professional,HttpSession httpsession) {
	    Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getlayouttimetableDao(degree,professional,institute_id);
}

@RequestMapping(value = "/getDailyttlayout", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getDailyttlayout(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String day,String degree, String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id")
				 .setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getDailyttlayoutDao(degree,professional,day,institute_id);
}

@RequestMapping(value = "/getLecturedatadtl", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getLecturedata(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String lec_id) {
       return ttdao.getLecturedataDao(lec_id);
}

@RequestMapping(value = "/getDelLecdatadtl", method = RequestMethod.POST)
public @ResponseBody String getDelLecdatadtl(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String lec_id) {
	
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_TT_TIME_TABLE_LAYOUT where id =:id ");	 
	 		q0.setParameter("id" , Integer.parseInt( lec_id));
	 
	 List<EDU_TT_TIME_TABLE_LAYOUT> layoutTTList =   q0.list();
	 
	 Query qdel = session.createQuery("delete from EDU_TT_TIME_TABLE_LAYOUT where id =:id ");	 
		 qdel.setParameter("id" ,  Integer.parseInt( lec_id));
		 
		 qdel.executeUpdate();
	 
	 
	 Query qdeldtl = session.createQuery("delete from EDU_TT_TIME_TABLE_MASTER where degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and course=:course and faculty=:faculty and classroom=:classroom and lec_type=:lec_type and institute_id=:institute_id");	 
			 qdeldtl.setParameter("degree" , layoutTTList.get(0).getDegree());
			 qdeldtl.setParameter("professional" , layoutTTList.get(0).getProfessional());
			 qdeldtl.setParameter("start_time" , layoutTTList.get(0).getStart_time());
			 qdeldtl.setParameter("end_time" , layoutTTList.get(0).getEnd_time());
			 qdeldtl.setParameter("course" , layoutTTList.get(0).getCourse());
			 qdeldtl.setParameter("faculty" , layoutTTList.get(0).getFaculty());
			 qdeldtl.setParameter("classroom" , layoutTTList.get(0).getClassroom());
			 qdeldtl.setParameter("lec_type" , layoutTTList.get(0).getLec_type());
			 qdeldtl.setParameter("institute_id" , layoutTTList.get(0).getInstitute_id());
	 
	 qdeldtl.executeUpdate();
	 
	 
	 
   tx.commit();
   session.close(); 
   return "Data Deleted Successfully";
       
}

@RequestMapping(value = "/getweeklyExamList", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getweeklyExamList(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getweeklyExamList(sdate,  professional, institute_id);
}

//@RequestMapping(value = "/getweeklyAcademicList", method = RequestMethod.POST)
//public @ResponseBody ArrayList<ArrayList<String>> getweeklyAcademicList(ModelMap Mmap,
//		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String professional,HttpSession httpsession) {
//	 Session sessiont = sessionFactory.openSession();
//		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
//		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
//		 List<UserLogin> institute_idList = q1.list();
//		 sessiont.close();
//		 int institute_id = institute_idList.get(0).getInstitute_id();
//       return ttdao.getweeklyAcademicList(sdate,  professional, institute_id);
//} 

public List<CC_TB_TYPE_OF_TEACHING_MSTR> gettype_of_teachingList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from CC_TB_TYPE_OF_TEACHING_MSTR where status='1' ");
	 
	 List<CC_TB_TYPE_OF_TEACHING_MSTR> Type_of_TeachingList = q0.list();
   session.getTransaction().commit();
   session.close();                
  return Type_of_TeachingList;
}

@RequestMapping(value = "/getweeklyEventList", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getweeklyEventList(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String event_date,HttpSession httpsession) {
	     Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getweeklyEventList(event_date, institute_id);
}


	   @RequestMapping(value = "/getExtraClassData", method = RequestMethod.POST)
       public @ResponseBody List<EDU_TT_TIME_TABLE_MASTER> getExtraClassData(String degree, String professional,String ecstart_time, String ecend_time, String ec_date,HttpSession httpsession) throws ParseException {
    		 Session session = sessionFactory.openSession();
    		 Transaction tx = session.beginTransaction();
    		 Session sessiont = sessionFactory.openSession();
    		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
    		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
    		 List<UserLogin> institute_idList = q1.list();
    		 sessiont.close();
    		 int institute_id = institute_idList.get(0).getInstitute_id();
    		 DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    		 Query q0 = session.createQuery("from EDU_TT_TIME_TABLE_MASTER where degree=:degree and professional=:professional and ldate=:ldate and start_time=:start_time and end_time=:end_time and extra_class_status = '1' and institute_id=:institute_id");
    		 			q0.setParameter("degree", Integer.parseInt(degree));
    		 			q0.setParameter("professional", Integer.parseInt(professional));
    		 			q0.setParameter("start_time", ecstart_time);
    		 			q0.setParameter("end_time", ecend_time);
    		 			q0.setParameter("ldate",sdf.parse(ec_date));
    		 			q0.setParameter("institute_id", institute_id);
    		 			    		 
    		 List<EDU_TT_TIME_TABLE_MASTER> ExtraClassDataList = q0.list();
    	       session.getTransaction().commit();
    	       session.close();                
    	      return ExtraClassDataList;
    	}
       
       @RequestMapping(value = "/getExtraClassDataUpdate", method = RequestMethod.POST)
       public @ResponseBody String getExtraClassDataUpdate(String degree, String professional,String msg,String course,String faculty,String classroom,String lec_type,String exam_type,String exam_serial,String ecstart_time, String ecend_time, String ec_date,HttpSession httpsession) throws ParseException {
    		 Session session = sessionFactory.openSession();
    		 Transaction tx = session.beginTransaction();
    		 Session sessiont = sessionFactory.openSession();
    		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
    		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
    		 List<UserLogin> institute_idList = q1.list();
    		 sessiont.close();
    		 int institute_id = institute_idList.get(0).getInstitute_id();
    		 DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    		 System.err.println("-------course----" +course + "------faculty-----" +faculty + "------classroom-------" +classroom);
    		 Query q0 = session.createQuery("update EDU_TT_TIME_TABLE_MASTER set course=:course,faculty=:faculty,classroom=:classroom,lec_type=:lec_type,modified_by=:modified_by,modified_date=:modified_date,exam_type=:exam_type,exam_serial=:exam_serial where ldate=:ldate and degree=:degree and professional=:professional and start_time=:start_time and end_time=:end_time and institute_id=:institute_id");
    		 			q0.setParameter("degree", Integer.parseInt(degree));
    		 			q0.setParameter("professional", Integer.parseInt(professional));
    		 			q0.setParameter("course", Integer.parseInt(course));
    		 			q0.setParameter("faculty", Integer.parseInt(faculty));
    		 			q0.setParameter("classroom", Integer.parseInt(classroom));
    		 			q0.setParameter("lec_type", lec_type);
    		 			q0.setParameter("exam_type", exam_type);
    		 			q0.setParameter("exam_serial", exam_serial);
    		 			q0.setParameter("start_time", ecstart_time);
    		 			q0.setParameter("end_time", ecend_time);
    		 			q0.setParameter("ldate",sdf.parse(ec_date));
    		 			q0.setParameter("institute_id", institute_id);
    		 			q0.setParameter("modified_by", userid);
						q0.setParameter("modified_date", new Date());
    		 			    		 
    		 	msg = q0.executeUpdate() > 0 ? "1" : "0";
    		 	session.flush();
				session.clear();
				tx.commit();
    	       session.close();                
    	      return msg;
    	}
       

@RequestMapping(value = "/getweeklyTransitionalList", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getweeklyTransitionalList(ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String sdate,String professional,HttpSession httpsession) {
	 Session sessiont = sessionFactory.openSession();
		 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		 List<UserLogin> institute_idList = q1.list();
		 sessiont.close();
		 int institute_id = institute_idList.get(0).getInstitute_id();
       return ttdao.getweeklyTransitionalList(sdate,  professional, institute_id);
}

//-------------------------Delete Battalion  -------------------------------------//

@RequestMapping(value = "/TTDelete_Url2", method = RequestMethod.POST)
public ModelAndView TTDelete_Url2(@ModelAttribute("id7") BigInteger id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {

//	SECURITY -- RIDDHI 
//	if(request.getHeader("Referer") == null ) { 
////		session.invalidate();
//		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//		return new ModelAndView("redirect:/landingpage");
//	 }
//	String roleid1 = session1.getAttribute("roleid").toString();
//	 Boolean val = roledao.ScreenRedirect("Faculty_to_CourseUrl", roleid1);		
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//	}
	/*
	 * if(request.getHeader("Referer") == null ) { session1.invalidate();
	 * model.put("msg",
	 * "Suspicious Activity Detected,You have been logged out by Administrator");
	 * return new ModelAndView("redirect:/login"); }
	 */
//	String roleid1 = session1.getAttribute("roleid").toString();
	/*
	 * Boolean val = roledao.ScreenRedirect("TimeTableUrl", roleid1); if(val ==
	 * false) { return new ModelAndView("AccessTiles"); }
	 */
		
	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {

		int app = session.createQuery(
				"delete from EDU_TT_TIME_TABLE_LAYOUT where id=:id")
				.setParameter("id", id).executeUpdate();

		
		tx.commit();
		session.close();
		if (app > 0) {
			liststr.add("Data Deleted Successfully.");
		} else {
			liststr.add("Data not Deleted.");
		}
		ra.addAttribute("msg", liststr.get(0));
	} catch (Exception e) {
		e.printStackTrace();
		liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
		ra.addAttribute("msg", liststr.get(0));
		
	}
	return new ModelAndView("redirect:TimeTable");
}

       
       
}

