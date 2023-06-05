package com.AyushEdu.controller.Degree_recognition_form_B;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_TRACK_STATUS;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINER_LIST_PG_COURSE_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_FORM_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_TRACK_STATUS;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_TEACHING_STAFF_B;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_recognition_contoller_datatable_pg {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Form_b_pg_Dao PDdao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;

	// ==========================================OPEN PAGE Student DataTable And
	// FianlSubmit==========================================

	@RequestMapping(value = "/PG_Datatable_url", method = RequestMethod.GET)
	public ModelAndView UG_Datatable_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		Mmap.put("msg", msg);

		return new ModelAndView("Degree_rec_Datatable_Tiles_pg");
	}

	// -------------------------------------------View table PG Form
		// A-------------------------------------------

		@PostMapping("/getFilter_PG")

		public @ResponseBody List<Map<String, Object>> getFilter_PG_list(HttpSession session, int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String id, String university_approved_status,
				String council_approved_status,String university_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
				String country,String state,String district,String city,String postal_address,String email,String website,
				String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
				String remarks,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_PG_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
					university_approved_status, council_approved_status,university_status,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);

		}

		@PostMapping("/getFilter_PGListCount")
		public @ResponseBody long getFilter_PGListCount(HttpSession session, String Search, String id,
				String university_approved_status, String council_approved_status,String university_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
				String country,String state,String district,String city,String postal_address,String email,String website,
				String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
				String remarks,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_PGListCount(Search, university_id, university_approved_status, council_approved_status,university_status,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);
		}

		// -------------------------------------------View table UG Form
		// C-------------------------------------------

		@PostMapping("/getFilter_Teaching_staff")

		public @ResponseBody List<Map<String, Object>> getFilter_Teaching_staff_list(HttpSession session, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, String id,
				String university_approved_status, String council_approved_status,String university_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_Teaching_staff_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
					university_approved_status, council_approved_status,university_status, name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);

		}

		@PostMapping("/getFilter_Teaching_staffListCount")
		public @ResponseBody long getFilter_Teaching_staffListCount(HttpSession session, String Search, String id,
				String university_approved_status, String council_approved_status,String university_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_Teaching_staffListCount(Search, university_id, university_approved_status,
					council_approved_status,university_status, name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
		}

//			//-------------------------------------------View table UG Form E-------------------------------------------

		@PostMapping("/getFilter_Examiners_pg")

		public @ResponseBody List<Map<String, Object>> getFilter_Examiners_pg_list(HttpSession session, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, String id,
				String university_approved_status, String council_approved_status,String university_status,String subject,String name_of_examiners,
				String date_of_examination,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_Examiners_pg_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
					university_approved_status, council_approved_status,university_status,subject, name_of_examiners, date_of_examination, reject_remarks);

		}

		@PostMapping("/getFilter_Examiners_pgListCount")
		public @ResponseBody long getFilter_Examiners_pgListCount(HttpSession session, String Search, String id,
				String university_approved_status, String council_approved_status,String university_status,String subject,String name_of_examiners,
				String date_of_examination,String reject_remarks) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String user_id = (userId);

			return PDdao.getFilter_Examiners_pgListCount(Search, university_id, university_approved_status,
					council_approved_status,university_status,subject, name_of_examiners, date_of_examination, reject_remarks);
		}

	// ==========================================OPEN PAGE PG COUSES Edit Data And
	// Update==========================================

	@RequestMapping(value = "/PG_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView PG_Edit_Update_Url(@ModelAttribute("eid") String eid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		// System.err.println("EID------------"+eid);
		Mmap.put("eid", eid);
		Mmap.put("pg_detail", PDdao.getpgByid(Integer.parseInt(eid)));
		Mmap.put("msg", msg);
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));

		return new ModelAndView("pg_edit_update_Tiles");
	}

	/////////// edit PG COUSES //////////////

	@RequestMapping(value = "/edit_pg_Action", method = RequestMethod.POST)
	public ModelAndView edit_pg_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String id = request.getParameter("eid");
		System.out.println("---eid" + id);
		String username = session.getAttribute("username").toString();
		String name_of_applicant_university = request.getParameter("name_of_applicant_university");
		String postgraduate_course = request.getParameter("postgraduate_course");
		String abbre_postgraduate_course = request.getParameter("abbre_postgraduate_course");
		String academic_session = request.getParameter("academic_session");
		String name_of_college = request.getParameter("name_of_college");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String district = request.getParameter("district");
		String city = request.getParameter("city");
		String postal_address = request.getParameter("postal_address");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String academic_year_applied_for = request.getParameter("academic_year_applied_for");
		String num_of_student_admitted = request.getParameter("num_of_student_admitted");
		// String permission_from_central_gov =
		// request.getParameter("permission_from_central_gov");
		String admission_intake = request.getParameter("admission_intake");
		String remarks = request.getParameter("remarks");

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_PG_FORM_B set " + "modified_by=:modified_by, "
					+ "modified_date=:modified_date, " + "name_of_applicant_university=:name_of_applicant_university, "
					+ "postgraduate_course=:postgraduate_course, "
					+ "abbre_postgraduate_course=:abbre_postgraduate_course, " + "academic_session=:academic_session, "
					+ "name_of_college=:name_of_college, " + "country=:country, " + "state=:state, "
					+ "district=:district, " + "city=:city, " + "postal_address=:postal_address, " + "email=:email, "
					+ "website=:website, " + "academic_year_applied_for=:academic_year_applied_for, "
					+ "admission_intake=:admission_intake, " + "num_of_student_admitted=:num_of_student_admitted, "
					+ "remarks=:remarks " + "where id=:id ";

			Query query = session1.createQuery(hql)
					.setParameter("name_of_applicant_university", name_of_applicant_university)
					.setParameter("postgraduate_course", postgraduate_course)
					.setParameter("abbre_postgraduate_course", abbre_postgraduate_course)
					.setParameter("academic_session", academic_session).setParameter("name_of_college", name_of_college)
					.setParameter("country", country).setParameter("state", state).setParameter("district", district)
					.setParameter("city", city).setParameter("postal_address", postal_address)
					.setParameter("email", email).setParameter("website", website)
					.setParameter("academic_year_applied_for", academic_year_applied_for)
					.setParameter("admission_intake", Integer.parseInt(admission_intake))
					.setParameter("num_of_student_admitted", Integer.parseInt(num_of_student_admitted))
					.setParameter("remarks", remarks).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:PG_Datatable_url");
	}

	// ==========================================OPEN PAGE PG FORM C TEACHING Edit
	// Data And Update==========================================

	@RequestMapping(value = "/PG_Teaching_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView PG_Teaching_Edit_Update_Url(@ModelAttribute("hid") String hid, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("hid", hid);
		System.err.println("--hid---" + hid);
		Mmap.put("pg_teaching_detail", PDdao.getpgteachingByid(Integer.parseInt(hid)));
		Mmap.put("msg", msg);
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());

		return new ModelAndView("pg_teaching_edit_update_Tiles");
	}

	/////////// edit Teaching COUSES //////////////

	@RequestMapping(value = "/edit_pg_teaching_Action", method = RequestMethod.POST)
	public ModelAndView edit_pg_teaching_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String id = request.getParameter("hid");
		System.out.println("---hid" + id);
		String username = session.getAttribute("username").toString();
		String name_of_college_pg = request.getParameter("name_of_college_pg");
		String name_of_teaching_staff = request.getParameter("name_of_teaching_staff");
		String phone = request.getParameter("phone");
		String email_id = request.getParameter("email_id");
		String designation = request.getParameter("designation");
		String department = request.getParameter("department");
		String registration_no = request.getParameter("registration_no");
		String date_of_reg = request.getParameter("date_of_reg");
		String date_of_birth = request.getParameter("date_of_birth");
		String qualification_awarding_authority = request.getParameter("qualification_awarding_authority");
		String year_of_award = request.getParameter("year_of_award");
		String date_of_appointment = request.getParameter("date_of_appointment");
		String fulltime_parttime = request.getParameter("fulltime_parttime");
		String post_teaching = request.getParameter("post_teaching");
		String exp_from = request.getParameter("exp_from");
		String exp_to = request.getParameter("exp_to");
		String total_teaching_exp_in_year = request.getParameter("total_teaching_exp_in_year");

		Date dt_reg = null;
		Date dt_birth = null;
		Date dt_aapoin = null;
		Date dt_efrom = null;
		Date dt_eto = null;

		if (!date_of_reg.trim().equals("") && !date_of_reg.equals("DD/MM/YYYY")) {
			dt_reg = format.parse(date_of_reg);
		}
		if (!date_of_birth.trim().equals("") && !date_of_birth.equals("DD/MM/YYYY")) {
			dt_birth = format.parse(date_of_birth);
		}
		if (!date_of_appointment.trim().equals("") && !date_of_appointment.equals("DD/MM/YYYY")) {
			dt_aapoin = format.parse(date_of_appointment);
		}
		if (!exp_from.trim().equals("") && !exp_from.equals("DD/MM/YYYY")) {
			dt_efrom = format.parse(exp_from);
		}
		if (!exp_to.trim().equals("") && !exp_to.equals("DD/MM/YYYY")) {
			dt_eto = format.parse(exp_to);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_TEACHING_STAFF_B set " + "modified_by=:modified_by, "
					+ "modified_date=:modified_date, " + "name_of_college_pg=:name_of_college_pg, "
					+ "name_of_teaching_staff=:name_of_teaching_staff, " + "phone=:phone, " + "email_id=:email_id, "
					+ "designation=:designation, " + "department=:department, " + "registration_no=:registration_no, "
					+ "date_of_reg=:date_of_reg, " + "date_of_birth=:date_of_birth, "
					+ "qualification_awarding_authority=:qualification_awarding_authority, "
					+ "year_of_award=:year_of_award, " + "date_of_appointment=:date_of_appointment, "
					+ "fulltime_parttime=:fulltime_parttime, " + "post_teaching=:post_teaching, "
					+ "exp_from=:exp_from, " + "exp_to=:exp_to, "
					+ "total_teaching_exp_in_year=:total_teaching_exp_in_year " + "where id=:id ";

			Query query = session1.createQuery(hql).setParameter("name_of_college_pg", name_of_college_pg)
					.setParameter("name_of_teaching_staff", name_of_teaching_staff)
					.setParameter("phone", Integer.parseInt(phone)).setParameter("email_id", email_id)
					.setParameter("designation", designation).setParameter("department", department)
					.setParameter("registration_no", Integer.parseInt(registration_no))
					.setParameter("date_of_reg", dt_reg).setParameter("date_of_birth", dt_birth)
					.setParameter("qualification_awarding_authority", qualification_awarding_authority)
					.setParameter("year_of_award", year_of_award).setParameter("date_of_appointment", dt_aapoin)
					.setParameter("fulltime_parttime", fulltime_parttime).setParameter("post_teaching", post_teaching)
					.setParameter("exp_from", dt_efrom).setParameter("exp_to", dt_eto)
					.setParameter("total_teaching_exp_in_year", total_teaching_exp_in_year)
					.setParameter("modified_by", username).setParameter("modified_date", new Date())
					.setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:PG_Datatable_url");
	}

	// ==========================================OPEN PAGE PG FORM F Examiners Edit
	// Data And Update==========================================

	@RequestMapping(value = "/PG_Examiners_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView PG_Examiners_Edit_Update_Url(@ModelAttribute("exmid") String exmid, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("exmid", exmid);

		Mmap.put("pg_examiners_detail", PDdao.getpgexaminersByid(Integer.parseInt(exmid)));
		Mmap.put("msg", msg);

		return new ModelAndView("pg_examiners_edit_update_Tiles");
	}

//			/////////// edit Examiners  //////////////

	@RequestMapping(value = "/edit_pg_examiners_Action", method = RequestMethod.POST)
	public ModelAndView edit_pg_examiners_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("PG_Datatable_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String id = request.getParameter("exmid");
		System.out.println("---exmid" + id);

		String username = session.getAttribute("username").toString();
		String subject = request.getParameter("subject");
		String name_of_examiners = request.getParameter("name_of_examiners");
		String date_of_examination = request.getParameter("date_of_examination");

		Date dt_ex = null;

		if (!date_of_examination.trim().equals("") && !date_of_examination.equals("DD/MM/YYYY")) {
			dt_ex = format.parse(date_of_examination);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_EXAMINER_LIST_PG_COURSE_B set " + "modified_by=:modified_by, "
					+ "modified_date=:modified_date, " + "subject=:subject, " + "name_of_examiners=:name_of_examiners, "
					+ "date_of_examination=:date_of_examination " + "where id=:id ";

			Query query = session1.createQuery(hql).setParameter("subject", subject)
					.setParameter("name_of_examiners", name_of_examiners).setParameter("date_of_examination", dt_ex)
					.setParameter("modified_by", username).setParameter("modified_date", new Date())
					.setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";

			tx.commit();

			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:PG_Datatable_url");
	}
	// --------------------------------------------Submit for
	// approval-----University------------------------------------

	@RequestMapping(value = "/Submit_Approval_Data_university_pg", method = RequestMethod.POST)
	public @ResponseBody String Submit_Approval_Data_university_pg(ModelMap Mmap, HttpSession session,
			HttpServletRequest request, String current_month_year,
			@RequestParam(value = "apg_hid", required = false) int apg_hid,
			@RequestParam(value = "bpg_hid", required = false) int bpg_hid,
			@RequestParam(value = "cpg_hid", required = false) int cpg_hid) throws ParseException {

		String msg = "";
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String user_id = session.getAttribute("userId").toString();
		String university_id = session.getAttribute("university_id").toString();
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		String current_mm_yy = month + "-" + year;

		String month_year = request.getParameter("Calendar");

		try {

			if (apg_hid > 0) {
				String hqlUpdate = "update from DG_REC_PG_FORM_B set university_approved_status=:university_approved_status where user_id=:user_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("university_approved_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();

				Query q = sessionHQL.createQuery(
						"from DG_REC_PG_FORM_B where user_id=:user_id and university_approved_status=:university_approved_status")
						.setInteger("university_approved_status", 1).setInteger("user_id", Integer.parseInt(user_id));
				@SuppressWarnings("unchecked")

				List<DG_REC_PG_FORM_B> clist = (List<DG_REC_PG_FORM_B>) q.list();

				clist.forEach((n) -> {
					DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
					trx.setTbl_id(n.getId());
					trx.setCommi_status(0);
					trx.setUni_status(1);
					trx.setInstitute_id(n.getUser_id());
					trx.setUniversity_id(n.getUniversity_id());
					trx.setMonth_year(n.getCurrent_month_year());
					sessionHQL.save(trx);
					sessionHQL.flush();
					sessionHQL.clear();
				});
			}
			if (bpg_hid > 0) {
				String hqlUpdate = "update from DG_REC_TEACHING_STAFF_B set university_approved_status=:university_approved_status where user_id=:user_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("university_approved_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();

				Query q = sessionHQL.createQuery(
						"from DG_REC_TEACHING_STAFF_B where user_id=:user_id and university_approved_status=:university_approved_status")
						.setInteger("university_approved_status", 1).setInteger("user_id", Integer.parseInt(user_id));
				@SuppressWarnings("unchecked")

				List<DG_REC_TEACHING_STAFF_B> clist = (List<DG_REC_TEACHING_STAFF_B>) q.list();

				clist.forEach((n) -> {
					DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
					trx.setTbl_id(n.getId());
					trx.setCommi_status(0);
					trx.setUni_status(1);
					trx.setInstitute_id(n.getUser_id());
					trx.setUniversity_id(n.getUniversity_id());
					trx.setMonth_year(n.getCurrent_month_year());
					sessionHQL.save(trx);
					sessionHQL.flush();
					sessionHQL.clear();
				});
			}
			if (cpg_hid > 0) {
				String hqlUpdate = "update from DG_REC_EXAMINER_LIST_PG_COURSE_B set university_approved_status=:university_approved_status where user_id=:user_id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("university_approved_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();

				Query q = sessionHQL.createQuery(
						"from DG_REC_EXAMINER_LIST_PG_COURSE_B where user_id=:user_id and university_approved_status=:university_approved_status")
						.setInteger("university_approved_status", 1).setInteger("user_id", Integer.parseInt(user_id));
				@SuppressWarnings("unchecked")

				List<DG_REC_EXAMINER_LIST_PG_COURSE_B> clist = (List<DG_REC_EXAMINER_LIST_PG_COURSE_B>) q.list();

				clist.forEach((n) -> {
					DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
					trx.setTbl_id(n.getId());
					trx.setCommi_status(0);
					trx.setUni_status(1);
					trx.setInstitute_id(n.getUser_id());
					trx.setUniversity_id(n.getUniversity_id());
					trx.setMonth_year(n.getCurrent_month_year());
					sessionHQL.save(trx);
					sessionHQL.flush();
					sessionHQL.clear();
				});
			}
			
			if ((apg_hid > 0) || (bpg_hid > 0) || (cpg_hid > 0) ) {
				msg = "Data Submitted Successfully";
				tx.commit();
			} else {
				msg = "Something Went Wrong !!!";
			}
			sessionHQL.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ------email----------

//							String subject = "YourRegistrationForAlumniisSuccessfull";
//							String main_txt = " You have received this valid data from the Institute. "
//									         + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//							String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//							e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");

		// ------end email----------

		
	//----notification---------
	
//	String uni_id = String.valueOf(common.getAllInfoLogin(sessionFactory,user_id).get(0).getUniversity_id());
//	System.err.println("UNIVERSITY ID PRANAV----------------------"+uni_id); 
//	String userid = UDdao.getNCH_user_id(uni_id).get(0).get(0);
//	String notimsg=" You have received this valid data from the University. "
//		       + "This Details has been Successfully Approved by University.So ,Check it and give the approval.";
//     common.Notification(notimsg, "419",sessionFactory, session);
//     System.err.println(notimsg+"-------notimsg");
     
   //----end notification---------
	return msg;
}
}
