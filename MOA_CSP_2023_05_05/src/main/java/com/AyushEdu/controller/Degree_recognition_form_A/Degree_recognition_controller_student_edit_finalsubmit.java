package com.AyushEdu.controller.Degree_recognition_form_A;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_INTERN_STUDENT_COURSE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_FROM;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_SUB_CHILD;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_TRACK_STATUS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Student_admitted_dao;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_recognition_controller_student_edit_finalsubmit {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	Student_admitted_dao SDdao;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	@Autowired
	Form_b_pg_Dao PDdao;


	// ==========================================OPEN PAGE Student DataTable And FianlSubmit==========================================

	@RequestMapping(value = "/StudentEdit_Url", method = RequestMethod.GET)
	public ModelAndView StudentEdit_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());

		return new ModelAndView("Student_edit_finalsubmit_Tiles");
	}

	// -------------------------------------------View table Form B Admitted Student-------------------------------------------

	 @PostMapping("/getFilter_Admitted_Student")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Admitted_Student_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String institute_status,String student_name,
				String date_of_admission, String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	    	 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return SDdao.getFilter_Admitted_Student_list(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,
		    		 institute_status,student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);

	    }
		 @PostMapping("/getFilter_Admitted_StudentListCount")	
		 public @ResponseBody long getFilter_Admitted_StudentListCount(HttpSession session ,String Search,int id,String institute_status,String student_name,
					String date_of_admission, String rank,String marks,String all_india,String state,String admission_authority,String court_order,
					String date_of_enroll_university,String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		     	 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
		     	 
			return SDdao.getFilter_Admitted_StudentListCount(Search,university_id,user_id,institute_status,student_name,date_of_admission,rank,marks,all_india,state,admission_authority,
					court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
	    } 
	// -------------------------------------------View table UG Form C-------------------------------------------

		 @PostMapping("/getFilter_Hospital_Attached")	
			
			public @ResponseBody List<Map<String, Object>> getFilter_Hospital_Attached_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,int id,String institute_status,String name_homoeopathic_medical_clg,
					String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
					String designation,String qualification,String fulltime_parttime,String remarks_form_c) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		    	 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     int user_id=Integer.parseInt(userId);
			     
			     return SDdao.getFilter_Hospital_Attached_list(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
							mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);

		    }
			 @PostMapping("/getFilter_Hospital_AttachedListCount")	
			 public @ResponseBody long getFilter_Hospital_AttachedListCount(HttpSession session ,String Search,int id,String institute_status,String name_homoeopathic_medical_clg,
						String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
						String designation,String qualification,String fulltime_parttime,String remarks_form_c) throws ParseException {
					 
					 String userId = session.getAttribute("userId").toString();
			     	 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     	 int user_id=Integer.parseInt(userId);
			     	 
				return SDdao.getFilter_Hospital_AttachedListCount(Search,university_id,user_id,institute_status,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
		    }

	// ==========================================OPEN PAGE UG Hospital Edit Data And Update==========================================

	@RequestMapping(value = "/UG_Hospital_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView UG_Hospital_Edit_Update_Url(@ModelAttribute("hid") String hid, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		System.err.println("hospiId------------" + hid);
		Mmap.put("hid", hid);
		Mmap.put("ug_hospital_detail", SDdao.getughospByid(Integer.parseInt(hid)));
		Mmap.put("msg", msg);
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));

		return new ModelAndView("ug_hospital_edit_update_Tiles");
	}

	/////////// edit UG Hospital //////////////

	@RequestMapping(value = "/edit_ug_hospital_Action", method = RequestMethod.POST)
	public ModelAndView edit_ug_hospital_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("hid");

		String name_homoeopathic_medical_clg = request.getParameter("name_homoeopathic_medical_clg");
		String attached_homoeopath_hospital = request.getParameter("attached_homoeopath_hospital");
		String super_speciality_hospital = request.getParameter("super_speciality_hospital");
		String mou_date = request.getParameter("mou_date");
//		String copy_of_mou = request.getParameter("copy_of_mou");
		String name_of_hospital_staff = request.getParameter("name_of_hospital_staff");
		String designation = request.getParameter("designation");
		String qualification = request.getParameter("qualification");
		String fulltime_parttime = request.getParameter("fulltime_parttime");
		String remarks_form_c = request.getParameter("remarks_form_c");

		Date dt_mou = null;

		if (!mou_date.trim().equals("") && !mou_date.equals("DD/MM/YYYY")) {
			dt_mou = format.parse(mou_date);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_HOSPITAL_ATTACHED set " + "modified_by=:modified_by, "
					+ "modified_date=:modified_date, "
					+ "name_homoeopathic_medical_clg=:name_homoeopathic_medical_clg, "
					+ "attached_homoeopath_hospital=:attached_homoeopath_hospital, "
					+ "super_speciality_hospital=:super_speciality_hospital, " + "mou_date=:mou_date, "
//					+ "copy_of_mou=:copy_of_mou, " 
					+ "name_of_hospital_staff=:name_of_hospital_staff, "
					+ "designation=:designation, " + "qualification=:qualification, "
					+ "fulltime_parttime=:fulltime_parttime, " + "remarks_form_c=:remarks_form_c " + "where id=:id ";

			Query query = session1.createQuery(hql)
					.setParameter("name_homoeopathic_medical_clg", name_homoeopathic_medical_clg)
					.setParameter("attached_homoeopath_hospital", attached_homoeopath_hospital)
					.setParameter("super_speciality_hospital", super_speciality_hospital)
					.setParameter("mou_date", dt_mou)
//					.setParameter("copy_of_mou", copy_of_mou)
					.setParameter("name_of_hospital_staff", name_of_hospital_staff)
					.setParameter("designation", designation).setParameter("qualification", qualification)
					.setParameter("fulltime_parttime", fulltime_parttime).setParameter("remarks_form_c", remarks_form_c)
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
		return new ModelAndView("redirect:StudentEdit_Url");
	}

	//-------------------------------------------View table Form D Migrated Student To-------------------------------------------

		@PostMapping("/getFilter_Migrated_Student")

		public @ResponseBody List<Map<String, Object>> getFilter_Migrated_Student_list(HttpSession session, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, int id, String institute_status,String name_of_inst,String student_name_to_migrated,
				String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d)
				throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Migrated_Student_list(startPage, pageLength, Search, orderColunm, orderType,
					university_id, user_id, institute_status,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);

		}

		@PostMapping("/getFilter_Migrated_StudentListCount")
		public @ResponseBody long getFilter_Migrated_StudentListCount(HttpSession session, String Search, int id,
				String institute_status,String name_of_inst,String student_name_to_migrated,
				String migrated_dt_to,String professional_year_migrated,String university_enrollment_number,String remarks_form_d) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Migrated_StudentListCount(Search, university_id, user_id, institute_status,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
					university_enrollment_number, remarks_form_d);

		}

		// -------------------------------------------View table Form D Migrated Student From -------------------------------------------

		@PostMapping("/getFilter_Migrated_Student_From")

		public @ResponseBody List<Map<String, Object>> getFilter_Migrated_Student_From_list(HttpSession session,
				int startPage, int pageLength, String Search, String orderColunm, String orderType, int id,
				String institute_status,String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
				String university_enroll_num,String remarks_migrated) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Migrated_Student_From_list(startPage, pageLength, Search, orderColunm, orderType,
					university_id, user_id, institute_status,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);

		}

		@PostMapping("/getFilter_Migrated_Student_FromListCount")
		public @ResponseBody long getFilter_Migrated_Student_FromListCount(HttpSession session, String Search, int id,
				String institute_status,String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
				String university_enroll_num,String remarks_migrated) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Migrated_Student_FromListCount(Search, university_id, user_id, institute_status,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
					university_enroll_num, remarks_migrated);

		}
		// -------------------------------------------------View table Form F Intern Student ----------------------------------

		@PostMapping("/getFilter_Intern_Student")
		public @ResponseBody List<Map<String, Object>> getFilter_Intern_Student_list(HttpSession session, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, int id, String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f)
				throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Intern_Student_list(startPage, pageLength, Search, orderColunm, orderType, university_id,
					user_id, institute_status,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
		}

		@PostMapping("/getFilter_Intern_StudentListCount")
		public @ResponseBody long getFilter_Intern_StudentListCount(HttpSession session, String Search, int id,
				String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
				String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
				String remark_form_f) throws ParseException {

			String userId = session.getAttribute("userId").toString();
			int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			int user_id = Integer.parseInt(userId);

			return SDdao.getFilter_Intern_StudentListCount(Search, university_id, user_id, institute_status,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
					year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
		}
	// ==========================================OPEN PAGE Student Admitted Edit Data And Update==========================================

	@RequestMapping(value = "/Student_Edit_Update_Url", method = RequestMethod.POST)
	public ModelAndView Student_Edit_Update_Url(@ModelAttribute("eid") String eid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		System.err.println("EID------------" + eid);
		Mmap.put("eid", eid);
		Mmap.put("admitted_detail", SDdao.getadmittedByid(Integer.parseInt(eid)));
		Mmap.put("msg", msg);

		return new ModelAndView("Student_edit_update_Tiles");
	}

	/////////// edit action Admitted Student //////////////

	@RequestMapping(value = "/edit_admittedstudent_Action", method = RequestMethod.POST)
	public ModelAndView edit_admittedstudent_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("eid");
		String student_name = request.getParameter("student_name");
		String neet_rank = request.getParameter("neet_rank");
		String rank = request.getParameter("rank");
		String marks = request.getParameter("marks");
		String all_india = request.getParameter("all_india");
		String state = request.getParameter("state");
		String management_quota = request.getParameter("management_quota");
		String admission_authority = request.getParameter("admission_authority");
		String court_order = request.getParameter("court_order");
		String uni_enroll_number = request.getParameter("uni_enroll_number");
		String remarks_form_b = request.getParameter("remarks_form_b");
		String date_of_admission = request.getParameter("date_of_admission");
		String date_of_enroll_university = request.getParameter("date_of_enroll_university");
		String date_of_intern_compl = request.getParameter("date_of_intern_compl");

		Date dt_adm = null;
		Date dt_university = null;
		Date dt_completion = null;

		if (!date_of_admission.trim().equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
			dt_adm = format.parse(date_of_admission);
		}
		if (!date_of_enroll_university.trim().equals("") && !date_of_enroll_university.equals("DD/MM/YYYY")) {
			dt_university = format.parse(date_of_enroll_university);
		}
		if (!date_of_intern_compl.trim().equals("") && !date_of_intern_compl.equals("DD/MM/YYYY")) {
			dt_completion = format.parse(date_of_intern_compl);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_ADMITTED_STUDENT set modified_by=:modified_by,modified_date=:modified_date,student_name=:student_name,"
					+ "date_of_admission=:date_of_admission,neet_rank=:neet_rank,"
					+ "rank=:rank,marks=:marks,all_india=:all_india,state=:state,management_quota=:management_quota,admission_authority=:admission_authority,"
					+ "court_order=:court_order,date_of_enroll_university=:date_of_enroll_university,uni_enroll_number=:uni_enroll_number,"
					+ "date_of_intern_compl=:date_of_intern_compl,remarks_form_b=:remarks_form_b where id=:id";

			Query query = session1.createQuery(hql).setParameter("student_name", student_name)
					.setParameter("date_of_admission", dt_adm).setParameter("rank", Integer.parseInt(rank))
					.setParameter("neet_rank",neet_rank)
					.setParameter("marks", Integer.parseInt(marks)).setParameter("all_india", all_india)
					.setParameter("state", state)
					.setParameter("management_quota",management_quota)
					.setParameter("admission_authority", admission_authority)
					.setParameter("court_order", court_order).setParameter("date_of_enroll_university", dt_university)
					.setParameter("uni_enroll_number", uni_enroll_number)
					.setParameter("date_of_intern_compl", dt_completion).setParameter("remarks_form_b", remarks_form_b)
					.setParameter("modified_by", username).setParameter("modified_date", new Date())
					.setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();

			if (msg.equals("1")) {
				System.err.println("----Updated---"+msg);
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:StudentEdit_Url");
	}

	///////////////////////////////////////// Migrated To Open Model //////////////////////////////////////////////

	@PostMapping(value = "/getmigrateddata")
	@ResponseBody
	public List<Map<String, Object>> getmigrateddata(String id) {
		return SDdao.getmigratedtoByid(id);
	}

	///////////////////////// edit action Migrated To //////////////////////

	@RequestMapping(value = "/edit_migratedstudent_Action", method = RequestMethod.POST)
	@ResponseBody
	public String edit_migratedstudent_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		if (request.getHeader("Referer") == null) {
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "";
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("mig_id");
		String name_of_inst = request.getParameter("name_of_institution");
		String student_name_to_migrated = request.getParameter("name_of_students_migrated");
		String migrated_dt_to = request.getParameter("dt_of_migration");
		String professional_year_migrated = request.getParameter("professional_year");
		String university_enrollment_number = request.getParameter("university_enroll_num");
		String remarks_form_d = request.getParameter("remarks_migrated");

		Date dm = null;

		if (!migrated_dt_to.trim().equals("") && !migrated_dt_to.equals("DD/MM/YYYY")) {
			dm = format.parse(migrated_dt_to);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_MIGRATED_STUDENT_SUB_CHILD set modified_by=:modified_by,modified_date=:modified_date,name_of_inst=:name_of_inst,"
					+ "student_name_to_migrated=:student_name_to_migrated,"
					+ "migrated_dt_to=:migrated_dt_to,professional_year_migrated=:professional_year_migrated,"
					+ "university_enrollment_number=:university_enrollment_number,remarks_form_d=:remarks_form_d "
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("name_of_inst", name_of_inst)
					.setParameter("student_name_to_migrated", student_name_to_migrated)
					.setParameter("migrated_dt_to", dm)
					.setParameter("professional_year_migrated", professional_year_migrated)
					.setParameter("university_enrollment_number", university_enrollment_number)
					.setParameter("remarks_form_d", remarks_form_d).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	///////////////////////////////////////// Migrated From Open Model //////////////////////////////////////////////

	@PostMapping(value = "/getmigrateddatafrom")
	@ResponseBody
	public List<Map<String, Object>> getmigrateddatafrom(String id) {
		return SDdao.getmigratedfromByid(id);
	}

	///////////////////////// edit action Migrated From //////////////////////

	@RequestMapping(value = "/edit_migratedstudentfrom_Action", method = RequestMethod.POST)
	@ResponseBody
	public String edit_migratedstudentfrom_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		if (request.getHeader("Referer") == null) {
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "";
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("migf_id");
		String name_of_institution = request.getParameter("name_of_institution");
		String name_of_students_migrated = request.getParameter("name_of_students_migrated");
		String dt_of_migration = request.getParameter("dt_of_migration");
		String professional_year = request.getParameter("professional_year");
		String university_enroll_num = request.getParameter("university_enroll_num");
		String remarks_migrated = request.getParameter("remarks_migrated");

		Date dmf = null;

		if (!dt_of_migration.trim().equals("") && !dt_of_migration.equals("DD/MM/YYYY")) {
			dmf = format.parse(dt_of_migration);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_MIGRATED_STUDENT_FROM set modified_by=:modified_by,modified_date=:modified_date,name_of_institution=:name_of_institution,"
					+ "name_of_students_migrated=:name_of_students_migrated,"
					+ "dt_of_migration=:dt_of_migration,professional_year=:professional_year,"
					+ "university_enroll_num=:university_enroll_num,remarks_migrated=:remarks_migrated "
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("name_of_institution", name_of_institution)
					.setParameter("name_of_students_migrated", name_of_students_migrated)
					.setParameter("dt_of_migration", dmf).setParameter("professional_year", professional_year)
					.setParameter("university_enroll_num", university_enroll_num)
					.setParameter("remarks_migrated", remarks_migrated).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("id", Integer.parseInt(id));
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	// ==========================================OPEN PAGE Student Intern Edit Data	And Update==========================================

	@RequestMapping(value = "/Student_Intern_Edit_Url", method = RequestMethod.POST)

	public ModelAndView Student_Intern_Edit_Url(@ModelAttribute("insid") String insid, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		System.err.println("insid------------" + insid);
		Mmap.put("insid", insid);
		Mmap.put("intern_detail", SDdao.getinternByid(Integer.parseInt(insid)));
		Mmap.put("msg", msg);

		return new ModelAndView("Student_intern_edit_Tiles");
	}

	/////////// edit action Admitted Student //////////////

	@RequestMapping(value = "/edit_internstudent_Action", method = RequestMethod.POST)
	public ModelAndView edit_internstudent_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("StudentEdit_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		String id = request.getParameter("insid");
		System.err.println("insid---HARSH---------" + id);
		String name_of_students = request.getParameter("name_of_students");
		String year_of_admission = request.getParameter("year_of_admission");
		String date_of_result_final_year = request.getParameter("date_of_result_final_year");
		int provisional_reg_no = Integer.parseInt(request.getParameter("provisional_reg_no"));
		String year_of_provisional_reg = request.getParameter("year_of_provisional_reg");
		String date_of_starting_internship = request.getParameter("date_of_starting_internship");
		String date_of_completion_internship = request.getParameter("date_of_completion_internship");
		String remark_form_f = request.getParameter("remark_form_f");

		Date dt_res = null;
		Date dt_sta = null;
		Date dt_completion = null;

		if (!date_of_result_final_year.trim().equals("") && !date_of_result_final_year.equals("DD/MM/YYYY")) {
			dt_res = format.parse(date_of_result_final_year);
		}
		if (!date_of_starting_internship.trim().equals("") && !date_of_starting_internship.equals("DD/MM/YYYY")) {
			dt_sta = format.parse(date_of_starting_internship);
		}
		if (!date_of_completion_internship.trim().equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
			dt_completion = format.parse(date_of_completion_internship);
		}

		try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			String hql = "update DG_REC_INTERN_STUDENT_COURSE set modified_by=:modified_by,modified_date=:modified_date,name_of_students=:name_of_students,"
					+ "year_of_admission=:year_of_admission,"
					+ "date_of_result_final_year=:date_of_result_final_year,provisional_reg_no=:provisional_reg_no,"
					+ "year_of_provisional_reg=:year_of_provisional_reg,date_of_starting_internship=:date_of_starting_internship,date_of_completion_internship=:date_of_completion_internship,remark_form_f=:remark_form_f "
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("name_of_students", name_of_students)
					.setParameter("year_of_admission", year_of_admission)
					.setParameter("date_of_result_final_year", dt_res)
					.setParameter("provisional_reg_no", provisional_reg_no)
					.setParameter("year_of_provisional_reg", year_of_provisional_reg)
					.setParameter("date_of_starting_internship", dt_sta)
					.setParameter("date_of_completion_internship", dt_completion)
					.setParameter("remark_form_f", remark_form_f).setParameter("modified_by", username)
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
		return new ModelAndView("redirect:StudentEdit_Url");
	}

	// --------------------------------------------Submit for approval-----institute------------------------------------

		@RequestMapping(value = "/Submit_Approval_Data_institute", method = RequestMethod.POST)
		public @ResponseBody String Submit_Approval_Data_institute(ModelMap Mmap, HttpSession session,
				HttpServletRequest request,String current_month_year,
				@RequestParam(value = "ssa_hid", required = false) int ssa_hid,
				@RequestParam(value = "ssmt_hid", required = false) int ssmt_hid,
				@RequestParam(value = "ssmf_hid", required = false) int ssmf_hid,
				@RequestParam(value = "ssi_hid", required = false) int ssi_hid,
				String id) throws ParseException {

			// System.err.println("-----in submit-----");

			String msg = "";
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

//			String userId = session.getAttribute("userId").toString();
			String user_id = session.getAttribute("userId").toString();
			String university_id = session.getAttribute("university_id").toString();
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
			try {
				
				Query q2 = sessionHQL.createQuery("from DG_REC_TRACK_STATUS");
				         @SuppressWarnings("unchecked")
				         
				         List<DG_REC_TRACK_STATUS> clist_t = (List<DG_REC_TRACK_STATUS>) q2.list();
				         String count_data = "";
				         for(int i = 0 ; i < clist_t.size(); i++) {
				        	 count_data += String.valueOf(clist_t.get(i).getId()) +",";
				        	 }
				         System.err.println("COUNT ++++++++"+count_data);
				if(ssa_hid > 0) 
				{
					String hqlUpdate = "update from DG_REC_ADMITTED_STUDENT set inst_status=:inst_status where user_id=:user_id and inst_status=0";
					int app = sessionHQL.createQuery(hqlUpdate)
							  .setInteger("inst_status", 1)
						      .setInteger("user_id", Integer.parseInt(user_id))
						      .executeUpdate();
			
				Query q = sessionHQL.createQuery("from DG_REC_ADMITTED_STUDENT where user_id=:user_id and inst_status=:inst_status and cast(id as string) not in (:data)")
						.setInteger("inst_status", 1)
				        .setInteger("user_id", Integer.parseInt(user_id)).setParameter("data", count_data.substring(0,count_data.length()-1));
				         @SuppressWarnings("unchecked")
				         
				         List<DG_REC_ADMITTED_STUDENT> clist = (List<DG_REC_ADMITTED_STUDENT>) q.list();
				
							clist.forEach((n) -> {
								System.err.println("FIRST ==========="+n.toString());
								DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
								trx.setTbl_id(n.getId());
								trx.setCommi_status(0);
								trx.setInst_status(1);
								trx.setUni_status(0);
								trx.setInstitute_id(n.getUser_id());
								trx.setUniversity_id(n.getUniversity_id());
								trx.setMonth_year(n.getCurrent_month_year());
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
							});
			}
				
			if(ssmt_hid > 0) 
			{
				String hqlUpdate2 = "update from DG_REC_MIGRATED_STUDENT_SUB_CHILD set inst_status=:inst_status where user_id=:user_id and inst_status=0";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("inst_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
				
				Query q = sessionHQL.createQuery("from DG_REC_MIGRATED_STUDENT_SUB_CHILD where user_id=:user_id and inst_status=:inst_status and cast(id as string) not in (:data)")
						.setInteger("inst_status", 1)
				        .setInteger("user_id", Integer.parseInt(user_id)).setParameter("data", count_data.substring(0,count_data.length()-1));
				         @SuppressWarnings("unchecked")
				         
				         List<DG_REC_MIGRATED_STUDENT_SUB_CHILD> clist = (List<DG_REC_MIGRATED_STUDENT_SUB_CHILD>) q.list();
				
							clist.forEach((n) -> {
								System.err.println("Third ==========="+n.toString());
								DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
								trx.setTbl_id(n.getId());
								trx.setCommi_status(0);
								trx.setInst_status(1);
								trx.setUni_status(0);
								trx.setInstitute_id(n.getUser_id());
								trx.setUniversity_id(n.getUniversity_id());
								trx.setMonth_year(n.getCurrent_month_year());
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
							});
	             
			}
			if(ssmf_hid > 0) 
			{
				String hqlUpdate3 = "update from DG_REC_MIGRATED_STUDENT_FROM set inst_status=:inst_status where user_id=:user_id and inst_status=0";
				int app3 = sessionHQL.createQuery(hqlUpdate3).setInteger("inst_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
				
				Query q = sessionHQL.createQuery("from DG_REC_MIGRATED_STUDENT_FROM  where user_id=:user_id and inst_status=:inst_status and cast(id as string) not in (:data)")
						.setInteger("inst_status", 1)
				        .setInteger("user_id", Integer.parseInt(user_id)).setParameter("data", count_data.substring(0,count_data.length()-1));
				         @SuppressWarnings("unchecked")
				         
				         List<DG_REC_MIGRATED_STUDENT_FROM> clist = (List<DG_REC_MIGRATED_STUDENT_FROM>) q.list();
				
							clist.forEach((n) -> {
								System.err.println("Four ==========="+n.toString());
								DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
								trx.setTbl_id(n.getId());
								trx.setCommi_status(0);
								trx.setInst_status(1);
								trx.setUni_status(0);
								trx.setInstitute_id(n.getUser_id());
								trx.setUniversity_id(n.getUniversity_id());
								trx.setMonth_year(n.getCurrent_month_year());
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
							});
			}
			
			if(ssi_hid > 0) 
			{
				String hqlUpdate4 = "update from DG_REC_INTERN_STUDENT_COURSE set inst_status=:inst_status where user_id=:user_id and inst_status=0";
				int app4 = sessionHQL.createQuery(hqlUpdate4).setInteger("inst_status", 1)
						.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
				
				Query q = sessionHQL.createQuery("from DG_REC_INTERN_STUDENT_COURSE  where user_id=:user_id and inst_status=:inst_status and cast(id as string) not in (:data)")
						.setInteger("inst_status", 1)
				        .setInteger("user_id", Integer.parseInt(user_id)).setParameter("data", count_data.substring(0,count_data.length()-1));
				         @SuppressWarnings("unchecked")
				         
				         List<DG_REC_INTERN_STUDENT_COURSE> clist = (List<DG_REC_INTERN_STUDENT_COURSE>) q.list();
				
							clist.forEach((n) -> {
								System.err.println("Five ==========="+n.toString());
								DG_REC_TRACK_STATUS trx = new DG_REC_TRACK_STATUS();
								trx.setTbl_id(n.getId());
								trx.setCommi_status(0);
								trx.setInst_status(1);
								trx.setUni_status(0);
								trx.setInstitute_id(n.getUser_id());
								trx.setUniversity_id(n.getUniversity_id());
								trx.setMonth_year(n.getCurrent_month_year());
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
							});
			}
			
			if ((ssa_hid > 0) ||  (ssmt_hid > 0) || (ssmf_hid > 0) || (ssi_hid > 0)) {
			msg = "Data Submitted Successfully";

	                //------email----------

//					String subject = "YourRegistrationForAlumniisSuccessfull";
//					String main_txt = " You have received this valid data from the Institute. "
//										 + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//					String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//					e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");

					// ------end email----------

					tx.commit();
				} else {
					msg = "Something Went Wrong !!!";
				}
				sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//----notification---------
			
			String uni_id = String.valueOf(common.getAllInfoLogin(sessionFactory,user_id).get(0).getUniversity_id());
			String userid = SDdao.getUni_user_id(uni_id).get(0).get(0);
			String notimsg=" You have received this valid data from the Institute. "
				       + "This Details has been Successfully Approved by Institute.So ,Check it and give the approval.";
		     common.Notification(notimsg, userid,sessionFactory, session);
		     
		   //----end notification---------
		     
			return msg;
		}

	//------------------------------Submit for Re-approval Rejected Data---institute----Admitted--------------------------------

		@RequestMapping(value = "/Submit_Approval_Reject_Data_institute_admitted", method = RequestMethod.POST)
		public @ResponseBody String Submit_Approval_Reject_Data_institute_admitted(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {

			String msg = "";
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String userId = session.getAttribute("userId").toString();

			try {

				String hqlUpdate = "update from DG_REC_ADMITTED_STUDENT set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
				int app = sessionHQL.createQuery(hqlUpdate)
						.setInteger("inst_status", 1)
						.setInteger("university_approved_status", 0)
						.setInteger("council_approved_status", 0)
						.setInteger("user_id", Integer.parseInt(userId))
						.executeUpdate();

				if ((app > 0)) {
					System.err.println("-----in if----" + app);
					msg = "Data Submitted Successfully";
					tx.commit();
				} else {
					msg = "Something Went Wrong !!!";
				}
				sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return msg;
		}
		
		//------------------------------Submit for Re-approval Rejected Data---institute----Hospital--------------------------------

				@RequestMapping(value = "/Submit_Approval_Reject_Data_institute_hospital", method = RequestMethod.POST)
				public @ResponseBody String Submit_Approval_Reject_Data_institute_hospital(ModelMap Mmap, HttpSession session,
						HttpServletRequest request) throws ParseException {

					String msg = "";
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					String userId = session.getAttribute("userId").toString();

					try {

						String hqlUpdate1 = "update from DG_REC_HOSPITAL_ATTACHED set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("university_approved_status", 0)
								.setInteger("council_approved_status", 0)
								.setInteger("user_id", Integer.parseInt(userId))
								.executeUpdate();

						if ((app1 > 0)) {
							
							msg = "Data Submitted Successfully";
							tx.commit();
						} else {
							msg = "Something Went Wrong !!!";
						}
						sessionHQL.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return msg;
				}
				
				//------------------------------Submit for Re-approval Rejected Data---institute----Migrated To--------------------------------

				@RequestMapping(value = "/Submit_Approval_Reject_Data_institute_migto", method = RequestMethod.POST)
				public @ResponseBody String Submit_Approval_Reject_Data_institute_migto(ModelMap Mmap, HttpSession session,
						HttpServletRequest request) throws ParseException {

					String msg = "";
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					String userId = session.getAttribute("userId").toString();

					try {

						String hqlUpdate2 = "update from DG_REC_MIGRATED_STUDENT_SUB_CHILD set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
						int app2 = sessionHQL.createQuery(hqlUpdate2)
								.setInteger("inst_status", 1)
								.setInteger("university_approved_status", 0)				
								.setInteger("council_approved_status", 0)
								.setInteger("user_id", Integer.parseInt(userId))
								.executeUpdate();

						if ((app2 > 0)) {
							
							msg = "Data Submitted Successfully";
							tx.commit();
						} else {
							msg = "Something Went Wrong !!!";
						}
						sessionHQL.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return msg;
				}
				
				//------------------------------Submit for Re-approval Rejected Data---institute----Migrated From--------------------------------

				@RequestMapping(value = "/Submit_Approval_Reject_Data_institute_migfrom", method = RequestMethod.POST)
				public @ResponseBody String Submit_Approval_Reject_Data_institute_migfrom(ModelMap Mmap, HttpSession session,
						HttpServletRequest request) throws ParseException {

					String msg = "";
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					String userId = session.getAttribute("userId").toString();

					try {

						String hqlUpdate3 = "update from DG_REC_MIGRATED_STUDENT_FROM set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
						int app3 = sessionHQL.createQuery(hqlUpdate3)
								.setInteger("inst_status", 1)
								.setInteger("university_approved_status", 0)
								.setInteger("council_approved_status", 0)
								.setInteger("user_id", Integer.parseInt(userId))
								.executeUpdate();

						if ((app3 > 0)) {
							
							msg = "Data Submitted Successfully";
							tx.commit();
						} else {
							msg = "Something Went Wrong !!!";
						}
						sessionHQL.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return msg;
				}
//--------------------------------------------Submit for approval Rejected Data---Intern------------------------------------

	@RequestMapping(value = "/Submit_Approval_Reject_Data_institute_intern", method = RequestMethod.POST)
	public @ResponseBody String Submit_Approval_Reject_Data_institute_intern(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {

		String msg = "";
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String userId = session.getAttribute("userId").toString();

		try {

			String hqlUpdate4 = "update from DG_REC_INTERN_STUDENT_COURSE set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
			int app4 = sessionHQL.createQuery(hqlUpdate4)
					.setInteger("inst_status", 1)
					.setInteger("university_approved_status", 0)
					.setInteger("council_approved_status", 0)
					.setInteger("user_id", Integer.parseInt(userId))
					.executeUpdate();

			if ((app4 > 0)) {
				
				msg = "Data Submitted Successfully";
				tx.commit();
			} else {
				msg = "Something Went Wrong !!!";
			}
			sessionHQL.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	

	//DOWNLOAD DOCUMENT PDF
		@RequestMapping(value = "/getDownloadUrladmitted")
		public void getDownloadUrladmitted(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
				String upload_docformA, ModelMap model, HttpServletRequest request, HttpSession session,
				HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;
			System.err.println("upload_docformA==========="+upload_docformA);
			String filePath = SDdao.getFilePathQueryForDocAttFormA(upload_docformA);
			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());
					
					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}

					response.setContentType(mimeType);

					response.setContentLength((int) downloadFile.length());

					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {

						outStream.write(buffer, 0, bytesRead);

					}

					inputStream.close();

					outStream.close();

				} else {

					String fullPath = filePath;

					File downloadFile = new File(fullPath);

					FileInputStream inputStream = new FileInputStream(downloadFile);

					String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());

					if (mimeType == null) {

						mimeType = "application/octet-stream";

					}
					
					response.setContentType(mimeType);
					response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + downloadFile.getName() + "\""));
					response.setContentLength((int) downloadFile.length());
					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];

					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {

						outStream.write(buffer, 0, bytesRead);

					}

					inputStream.close();

					outStream.close();

				}

			} catch (Exception ex) {

				System.out.println(ex);
				
				
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();
			}
		}
}
