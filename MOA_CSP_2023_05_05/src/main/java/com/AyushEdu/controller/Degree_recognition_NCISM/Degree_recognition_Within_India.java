package com.AyushEdu.controller.Degree_recognition_NCISM;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_MEDICAL_QUAL_PG_35B_COURSES;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_MEDICAL_QUAL_PG_DIPLOMA_35B_COURSES;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_WITHIN_INDIA_PG;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_WITHIN_INDIA_UG;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_NCISM.Degree_recognition_Within_inidaDao;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_A.Student_admitted_dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_Within_India{
	
	private static final Session HibernateUtil = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	 ValidationController validation;
	@Autowired
    Student_admitted_dao SDdao;
	@Autowired
	Gender_DAO sdao;
	@Autowired
	Form_a_ug_Dao Form_a_ug_DAO;
	@Autowired
	Degree_recognition_Within_inidaDao wDao;
	
	
/*------------------OPEN PAGE FOR UG FORM--------------------*/
	
	@RequestMapping(value = "/Deg_rec_WithinIndia_Url", method = RequestMethod.GET)
	public ModelAndView Deg_rec_WithinIndia_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) 
	{
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Deg_rec_35_ncism_web_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		
		
		
		
//		Mmap.addAttribute("pddata",wDao.getWithinindiaug(Integer.parseInt(userId)));
//		Mmap.addAttribute("pgdata",wDao.getWithinindiapg(Integer.parseInt(userId)));
//		Mmap.addAttribute("mpgdata",wDao.getWithinindiampg(Integer.parseInt(userId)));
//		Mmap.addAttribute("mpgddata",wDao.getWithinindiampgd(Integer.parseInt(userId)));
		Mmap.put("getSystemList",common.getSystemByNCISM(sessionFactory));
		Mmap.put("getSystemByNCISM",common.getSystemByNCISM(sessionFactory));
//		Mmap.put("getCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getUniverCityList", common.getUniverCityList(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("date_without_time", date_without_time);
//		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("getInstituteListForNcism",common.getInstituteListForNcism(sessionFactory));
//		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));

		return new ModelAndView("Deg_rec_WithinIndia_Tiles");
	}

	
	
	
	
/*------------------------------------SAVE/EDIT UG FORM-----------------------------------*/
	
@RequestMapping(value = "form35_a_ug_action", method = RequestMethod.POST)
		public @ResponseBody Map<String, String> form35_a_ug_action(ModelMap Mmap, HttpSession session,HttpServletRequest request,
				DG_REC_WITHIN_INDIA_UG ug)throws ParseException {

			Map<String, String> data = new HashMap<>();
			if(request.getHeader("Referer") == null ) 
{ 
     data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
     return data;
}
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String university_name = request.getParameter("university_name");
			String email = request.getParameter("email");
			String contact_name = request.getParameter("contact_name");	
			String contact_designation =request.getParameter("contact_designation");
			String contact_email = request.getParameter("contact_email");
			String contact_mobile = request.getParameter("contact_mobile");
			String abbreviation_degree = request.getParameter("abbreviation_degree");
			String medical_stream = request.getParameter("medical_stream");
			String institute_name = request.getParameter("institute_name");
			String nomenclature_degree = request.getParameter("nomenclature_degree");
			String university_address = request.getParameter("university_address");
			String first_batch_admission = request.getParameter("first_batch_admission");
			String awarded_degree = request.getParameter("awarded_degree");
			String final_year_examination = request.getParameter("final_year_examination");
			String completion_of_internship = request.getParameter("completion_of_internship");
			String provisional_certificate = request.getParameter("provisional_certificate");
			String examinaton_year = request.getParameter("examinaton_year");
			String commencement_dt = request.getParameter("commencement_dt");
			String expected_month_year = request.getParameter("expected_month_year");
			String continued_degree = request.getParameter("continued_degree");
			String last_batch_year = request.getParameter("last_batch_year");
			String userId = session.getAttribute("userId").toString();
			String form_a_ug_id = request.getParameter("form_a_ug_id");
			
			Date commencement_date = null;
				
				  if (!commencement_dt.trim().equals("") && !commencement_dt.equals("DD/MM/YYYY")) {
					  commencement_date = format.parse(commencement_dt);
				  }
				
			try {

				if (Integer.parseInt(form_a_ug_id) == 0) {

					DG_REC_WITHIN_INDIA_UG UG = new DG_REC_WITHIN_INDIA_UG();
					
					UG.setUniversity_name(university_name);
					UG.setUniversity_address(university_address);
					UG.setEmail(email);
					UG.setContact_name(contact_name);
					UG.setContact_designation(contact_designation);
					UG.setContact_mobile(contact_mobile);
					UG.setContact_email(contact_email);
					UG.setMedical_stream(medical_stream);
					UG.setInstitute_name(institute_name);
					UG.setNomenclature_degree(nomenclature_degree);
					UG.setAbbreviation_degree(abbreviation_degree);
					UG.setFirst_batch_admission(first_batch_admission);
					UG.setAwarded_degree(awarded_degree);
					UG.setInstitute_status(0);
					 if (request.getParameter("awarded_degree") != null) {
	       					if (request.getParameter("awarded_degree").equalsIgnoreCase("Yes")) {
	       						UG.setFinal_year_examination(final_year_examination);
	       						UG.setCompletion_of_internship(completion_of_internship);
	       						UG.setProvisional_certificate(provisional_certificate);
	       					}
	       				}
					 if (request.getParameter("awarded_degree") != null) {
	       					if (request.getParameter("awarded_degree").equalsIgnoreCase("No")) {
	       						
	       						UG.setExaminaton_year(examinaton_year);
	       						UG.setCommencement_dt(commencement_date);
	       						UG.setExpected_month_year(expected_month_year);
	       					}
	       				}
					 UG.setContinued_degree(continued_degree);
					 if (request.getParameter("continued_degree") != null) {
	       					if (request.getParameter("continued_degree").equalsIgnoreCase("No")) {
	       						UG.setLast_batch_year(last_batch_year);
	       					}
	       				}
					 
					UG.setCreated_by(username);
					UG.setCreated_date(date);
					UG.setUser_id(Integer.parseInt(userId));
					
					int id = (int) sessionHQL.save(UG);
					data.put("form_a_ug_id", String.valueOf(id));
					
					data.put("saved","Data Saved as Draft Successfully.") ;
					
				}
				else {
					try {
					String hql = "update DG_REC_WITHIN_INDIA_UG set "
							+ "modified_by=:modified_by, "
							+ "modified_date=:modified_date, "
							+ "university_name=:university_name,"
							+ "email=:email,"
							+ "contact_name=:contact_name, "
							+ "contact_designation=:contact_designation, "
							+ "contact_email=:contact_email, "
							+ "contact_mobile=:contact_mobile, "
							+ "abbreviation_degree=:abbreviation_degree, "
							+ "medical_stream=:medical_stream, "
							+ "institute_name=:institute_name, "
							+ "nomenclature_degree=:nomenclature_degree, "
							+ "university_address=:university_address, "
							+ "first_batch_admission=:first_batch_admission, "
							+ "awarded_degree=:awarded_degree, "
							+ "final_year_examination=:final_year_examination, "
							+ "completion_of_internship=:completion_of_internship, "
							+ "provisional_certificate=:provisional_certificate, "
							+ "examinaton_year=:examinaton_year, "
							+ "commencement_dt=:commencement_dt, "
							+ "expected_month_year=:expected_month_year, "
							+ "continued_degree=:continued_degree, "
							+ "last_batch_year=:last_batch_year, "
							+ "institute_status=:institute_status "
							+ "where id=:id";

					Query query = sessionHQL.createQuery(hql)
							.setParameter("modified_date", date)
							.setParameter("modified_by",username)
							.setParameter("university_name", university_name)
							.setParameter("email",email)
							.setParameter("contact_name", contact_name)
							.setParameter("contact_designation",contact_designation)
							.setParameter("contact_email", contact_email)
							.setParameter("contact_mobile", contact_mobile)
							.setParameter("abbreviation_degree", abbreviation_degree)
							.setParameter("medical_stream", medical_stream)
							.setParameter("institute_name", institute_name)
							.setParameter("nomenclature_degree", nomenclature_degree)
							.setParameter("university_address", university_address)
							.setParameter("first_batch_admission", first_batch_admission)
							.setParameter("awarded_degree", awarded_degree)
							.setParameter("final_year_examination", final_year_examination)
							.setParameter("completion_of_internship", completion_of_internship)
							.setParameter("provisional_certificate", provisional_certificate)
							.setParameter("examinaton_year", examinaton_year)
							.setParameter("commencement_dt", commencement_date)
							.setParameter("expected_month_year", expected_month_year)
							.setParameter("continued_degree", continued_degree)
							.setParameter("last_batch_year", last_batch_year)
							.setParameter("institute_status", 0)
							.setParameter("id",Integer.parseInt(form_a_ug_id));
							
					int update = query.executeUpdate() > 0 ? 1 : 0;

					if (update == 1)
						data.put("updated", "Data Updated Successfully");
					else
						data.put("error", "Data Not Updated");
					}catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				tx.commit();

			} catch (RuntimeException e) {

				try {

					tx.rollback();

					data.put("error", "Data Not Updated");

				} catch (RuntimeException rbe) {
					e.printStackTrace();
					data.put("error", "Data Not Updated");

				}

			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				
			return data;
	 }

/*------------------------------FINAL SUBMIT FOR UG--------------------------------*/

@RequestMapping(value = "/Submit_Approval_35Data_institute", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> Submit_Approval_35Data_institute(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                throws ParseException {
				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ) 
			{ 
			data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
			return data;
			}
            
            String msg = "";
            Session sessionHQL = this.sessionFactory.openSession();
            Transaction tx = sessionHQL.beginTransaction();
    
            String userId = session.getAttribute("userId").toString();
            try {
                    String hqlUpdate = "update from DG_REC_WITHIN_INDIA_UG set institute_status=:institute_status where user_id=:user_id";
                    int app = sessionHQL.createQuery(hqlUpdate)
                                    .setInteger("institute_status",1)
                                    .setInteger("user_id", Integer.parseInt(userId))
                                .executeUpdate();
                    
                    data.put("saved","Data Saved as Draft Successfully.") ;
					tx.commit();
                    sessionHQL.close();
                    if ((app > 0)) {
                            msg = "1";
                    } else {
                            msg = "0";
                    }
              } catch (Exception e) {
            }
            return data;
    }  

/*------------------------------------SAVE/EDIT PG FORM-----------------------------------*/

@RequestMapping(value = "form35_b_pg_action", method = RequestMethod.POST)
	  public @ResponseBody Map<String, String> form35_b_pg_action(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			DG_REC_WITHIN_INDIA_PG pg)throws ParseException {

		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
			{ 
			 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
			 return data;
			}
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String aff_university_name = request.getParameter("aff_university_name");
		String reg_email = request.getParameter("reg_email");
		String cont_name = request.getParameter("cont_name");	
		String cont_designation =request.getParameter("cont_designation");
		String cont_email_id = request.getParameter("cont_email_id");
		String medical_system = request.getParameter("medical_system");
		String insti_name = request.getParameter("insti_name");
		String aff_university_address = request.getParameter("aff_university_address");
		String cont_mobile_no = request.getParameter("cont_mobile_no");
		String institute_status = request.getParameter("institute_status");
		String user_id = request.getParameter("user_id");
		String userId = session.getAttribute("userId").toString();
		String form_b_pg_id = request.getParameter("form_b_pg_id");
		
		try {
		System.err.println("=========user_id======="+user_id+"----00000000000000----"+userId);
//			Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  DG_REC_WITHIN_INDIA_PG where user_id=:user_id")
//					.setParameter("user_id",Integer.parseInt(userId))
//					.uniqueResult();
//			if (c == 0) {
				System.err.println("==========ccccccxssssss======"+form_b_pg_id);
			if (Integer.parseInt(form_b_pg_id) == 0) {
				System.err.println("=========222222======="+form_b_pg_id);
				DG_REC_WITHIN_INDIA_PG PG = new DG_REC_WITHIN_INDIA_PG();
				
				PG.setAff_university_name(aff_university_name);
				PG.setAff_university_address(aff_university_address);
				PG.setReg_email(reg_email);
				PG.setCont_name(cont_name);
				PG.setCont_designation(cont_designation);
				PG.setCont_email_id(cont_email_id);
				PG.setMedical_system(medical_system);
				PG.setInsti_name(insti_name);
				PG.setCont_mobile_no(cont_mobile_no);
				PG.setCreated_by(username);
				PG.setInstitute_status(0);
				PG.setCreated_date(date);
				PG.setUser_id(Integer.parseInt(userId));
				
				int id = (int) sessionHQL.save(PG);
				data.put("form_b_pg_id", String.valueOf(id));
				
				data.put("saved","Data Saved as Draft Successfully.") ;
				
			} 
//		}
			else {
				System.err.println("=======ccccc========="+form_b_pg_id);
				String hql = "update DG_REC_WITHIN_INDIA_PG set modified_by=:modified_by,modified_date=:modified_date,"
						+ "aff_university_name=:aff_university_name,aff_university_address=:aff_university_address,reg_email=:reg_email,"
						+ "cont_name=:cont_name,cont_designation=:cont_designation,cont_email_id=:cont_email_id,"
						+ "medical_system=:medical_system,insti_name=:insti_name, "
						+ "cont_mobile_no=:cont_mobile_no,institute_status=:institute_status "
						+ "where id=:id";

				System.err.println("================"+form_b_pg_id);
				Query query = sessionHQL.createQuery(hql)
						.setParameter("modified_date", date)
						.setParameter("modified_by",username)
						.setParameter("aff_university_name", aff_university_name)
						.setParameter("aff_university_address",aff_university_address)
						.setParameter("reg_email", reg_email)
						.setParameter("cont_name",cont_name)
						.setParameter("cont_designation", cont_designation)
						.setParameter("cont_email_id", cont_email_id)
						.setParameter("medical_system", medical_system)
						.setParameter("insti_name", insti_name)
						.setParameter("cont_mobile_no", cont_mobile_no)
						.setParameter("institute_status", 0)
						.setParameter("id",Integer.parseInt(form_b_pg_id));
						
				int update = query.executeUpdate() > 0 ? 1 : 0;

				if (update == 1)
					data.put("updated", "Data Updated Successfully");
				else
					data.put("error", "Data Not Updated");
			}
			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return data;
 }

@RequestMapping(value = "/Submit_Approval_35_From_B", method = RequestMethod.POST)
public @ResponseBody Map<String, String> Submit_Approval_35_From_B(ModelMap Mmap, HttpSession session, HttpServletRequest request)
            throws ParseException {
                            
			Map<String, String> data = new HashMap<>();
			if(request.getHeader("Referer") == null ) 
		{ 
		data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
		return data;
		}
        
        String msg = "";
        Session sessionHQL = this.sessionFactory.openSession();
        Transaction tx = sessionHQL.beginTransaction();

        String userId = session.getAttribute("userId").toString();
        try {
                String hqlUpdate = "update from DG_REC_WITHIN_INDIA_PG set institute_status=:institute_status where user_id=:user_id";
                int app = sessionHQL.createQuery(hqlUpdate)
                                .setInteger("institute_status",1)
                                .setInteger("user_id", Integer.parseInt(userId))
                            .executeUpdate();
                
                data.put("saved","Data Saved as Draft Successfully.") ;
				tx.commit();
                sessionHQL.close();
                if ((app > 0)) {
                        msg = "1";
                } else {
                        msg = "0";
                }
          } catch (Exception e) {
        }
        return data;
} 

/*--------------------- SAVE FOR FORM_PG_MEDICAL -------------------------- */ 

@RequestMapping(value = "form_pg_medical_action", method = RequestMethod.POST)

	public @ResponseBody Map<String, String> form_pg_medical_action(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			DG_REC_MEDICAL_QUAL_PG_35B_COURSES pg)throws ParseException {
	
		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
			{ 
			 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
			 return data;
			}
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String pg_sub = request.getParameter("pg_sub");
		String nom_of_degree = request.getParameter("nom_of_degree");
		String abbr_of_degree = request.getParameter("abbr_of_degree");	
		String year_of_addmission =request.getParameter("year_of_addmission");
		String month_of_passing = request.getParameter("month_of_passing");
		String issue_of_provisional = request.getParameter("issue_of_provisional");
		String date_of_latter = request.getParameter("date_of_latter");
		String ref_of_latter = request.getParameter("ref_of_latter");
		String form_pg_course_id = request.getParameter("form_pg_course_id");
		String institute_status = request.getParameter("institute_status");
		String userId = session.getAttribute("userId").toString();
		System.err.println("----------id---- "+form_pg_course_id);
		
		Date dt_of_latter = null;
		
		  if (!date_of_latter.trim().equals("") && !date_of_latter.equals("DD/MM/YYYY")) {
			  dt_of_latter = format.parse(date_of_latter);
		  }
		try {

			if (Integer.parseInt(form_pg_course_id) == 0) {

				DG_REC_MEDICAL_QUAL_PG_35B_COURSES PGM = new DG_REC_MEDICAL_QUAL_PG_35B_COURSES();
				
				System.err.println("--------kj--------"+pg_sub);
					
				PGM.setPg_sub(pg_sub);
				PGM.setNom_of_degree(nom_of_degree);
				PGM.setAbbr_of_degree(abbr_of_degree);
				PGM.setYear_of_addmission(year_of_addmission);
				PGM.setMonth_of_passing(month_of_passing);
				PGM.setIssue_of_provisional(issue_of_provisional);
				PGM.setDate_of_latter(dt_of_latter);
				PGM.setRef_of_latter(ref_of_latter);
				PGM.setInstitute_status(0);
				PGM.setCreated_by(username);
				PGM.setCreated_date(date);
				PGM.setUser_id(Integer.parseInt(userId));
				
				int id = (int) sessionHQL.save(PGM);
				System.err.println(PGM+"-------pgmmmmmmmmm");
				data.put("form_pg_course_id", String.valueOf(id));
				
				data.put("saved","Data Saved as Draft Successfully.") ;
				
			}
			
			
				else {

				String hql = "update DG_REC_MEDICAL_QUAL_PG_35B_COURSES set modified_by=:modified_by,modified_date=:modified_date,"
						+ "pg_sub=:pg_sub, "
						+ "nom_of_degree=:nom_of_degree, "
						+ "abbr_of_degree=:abbr_of_degree, "
						+ "year_of_addmission=:year_of_addmission, "
						+ "month_of_passing=:month_of_passing, "
						+ "issue_of_provisional=:issue_of_provisional, "
//						+ "dt_of_latter=:dt_of_latter, "
						+ "ref_of_latter=:ref_of_latter "
						+ "where id=:id";

				Query query = sessionHQL.createQuery(hql)
						.setParameter("modified_date", date)
						.setParameter("modified_by",username)
						.setParameter("pg_sub", pg_sub)
						.setParameter("nom_of_degree",nom_of_degree)
						.setParameter("abbr_of_degree", abbr_of_degree)
						.setParameter("year_of_addmission",year_of_addmission)
						.setParameter("month_of_passing", month_of_passing)
						.setParameter("issue_of_provisional", issue_of_provisional)
//						.setParameter("date_of_latter", dt_of_latter)
						.setParameter("ref_of_latter", ref_of_latter)
						.setParameter("id",Integer.parseInt(form_pg_course_id));
						
				int update = query.executeUpdate() > 0 ? 1 : 0;

				if (update == 1)
					data.put("updated", "Data Updated Successfully");
				else
					data.put("error", "Data Not Updated");
			}
			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				data.put("error", "Data Not Updated");

			} catch (RuntimeException rbe) {

				data.put("error", "Data Not Updated");

			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		
		return data;
		}



@RequestMapping(value = "/Submit_Approval_35_From_C", method = RequestMethod.POST)
public @ResponseBody Map<String, String> Submit_Approval_35_From_C(ModelMap Mmap, HttpSession session, HttpServletRequest request)
            throws ParseException {
                            
			Map<String, String> data = new HashMap<>();
			if(request.getHeader("Referer") == null ) 
		{ 
		data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
		return data;
		}
        
        String msg = "";
        Session sessionHQL = this.sessionFactory.openSession();
        Transaction tx = sessionHQL.beginTransaction();

        String userId = session.getAttribute("userId").toString();
        try {
                String hqlUpdate = "update from DG_REC_MEDICAL_QUAL_PG_35B_COURSES set institute_status=:institute_status where user_id=:user_id";
                int app = sessionHQL.createQuery(hqlUpdate)
                                .setInteger("institute_status",1)
                                .setInteger("user_id", Integer.parseInt(userId))
                            .executeUpdate();
                
                data.put("saved","Data Saved as Draft Successfully.") ;
				tx.commit();
                sessionHQL.close();
                if ((app > 0)) {
                        msg = "1";
                } else {
                        msg = "0";
                }
          } catch (Exception e) {
        }
        return data;
} 


/*--------------------- SAVE FOR FORM_PG_DIPLOMA_MEDICAL -------------------------- */ 


	@RequestMapping(value = "form_pg_diploma_medical_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form_pg_diploma_medical_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
		System.err.println("------in action");

				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ) 
	{ 
	     data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
	     return data;
	}
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String pg_dip_subject = request.getParameter("pg_dip_subject");
				String nomenclature_dg_dip = request.getParameter("nomenclature_dg_dip");
				String abbreviation_dg_dip =request.getParameter("abbreviation_dg_dip");
				String year_admi_first_batch_dip = request.getParameter("year_admi_first_batch_dip");
				String passing_year_of_finalyear_dip = request.getParameter("passing_year_of_finalyear_dip");
				String year_provisional_certi_dip = request.getParameter("year_provisional_certi_dip");
				String permission_lattter_dt = request.getParameter("permission_lattter_dt");
				String ref_permission_latter = request.getParameter("ref_permission_latter");
				String userId = session.getAttribute("userId").toString();
				String form_pg_diploma_id = request.getParameter("form_pg_diploma_id");
				String institute_status = request.getParameter("institute_status");
				String university_status = request.getParameter("university_status");
				String ncism_status = request.getParameter("ncism_status");
				System.err.println(form_pg_diploma_id+"------form_pg_diploma_idform_pg_diploma_id");
				System.err.println(userId+"------userIduserIduserId");
				
				Date pr_latter_dt = null;
				
				  if (!permission_lattter_dt.trim().equals("") && !permission_lattter_dt.equals("DD/MM/YYYY")) {
					  pr_latter_dt = format.parse(permission_lattter_dt);
				  }
				
				try { 
				
			

					if (form_pg_diploma_id.equals("0")) {

						DG_REC_MEDICAL_QUAL_PG_DIPLOMA_35B_COURSES PG = new DG_REC_MEDICAL_QUAL_PG_DIPLOMA_35B_COURSES();
						
						PG.setPg_dip_subject(pg_dip_subject);
						PG.setNomenclature_dg_dip(nomenclature_dg_dip);
						PG.setAbbreviation_dg_dip(abbreviation_dg_dip);
						PG.setYear_admi_first_batch_dip(year_admi_first_batch_dip);
						PG.setPassing_year_of_finalyear_dip(passing_year_of_finalyear_dip);
						PG.setYear_provisional_certi_dip(year_provisional_certi_dip);
						PG.setPermission_lattter_dt(pr_latter_dt);
						PG.setRef_permission_latter(ref_permission_latter);
						PG.setInstitute_status(0);
						PG.setUniversity_status(0);
						PG.setNcism_status(0);
						PG.setCreated_by(username);
						PG.setCreated_date(date);
						PG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(PG);
						data.put("form_pg_diploma_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;

					} 
					else {

						
						
						String hql = "update DG_REC_MEDICAL_QUAL_PG_DIPLOMA_35B_COURSES set modified_by=:modified_by ,modified_date=:modified_date,"
								+ "pg_dip_subject=:pg_dip_subject, "
								+ "nomenclature_dg_dip=:nomenclature_dg_dip, "
								+ "abbreviation_dg_dip=:abbreviation_dg_dip, "
								+ "year_admi_first_batch_dip=:year_admi_first_batch_dip, "
								+ "passing_year_of_finalyear_dip=:passing_year_of_finalyear_dip, "
								+ "year_provisional_certi_dip=:year_provisional_certi_dip, "
//								+ "pr_latter_dt=:pr_latter_dt,"
								+ "ref_permission_latter=:ref_permission_latter "
								+ "where id=:id";

						
						Query query = sessionHQL.createQuery(hql)
								.setParameter("modified_date", date)
								.setParameter("modified_by",username)
								.setParameter("pg_dip_subject", pg_dip_subject)
								.setParameter("nomenclature_dg_dip",nomenclature_dg_dip)
								.setParameter("abbreviation_dg_dip", abbreviation_dg_dip)
								.setParameter("year_admi_first_batch_dip", year_admi_first_batch_dip)
								.setParameter("passing_year_of_finalyear_dip", passing_year_of_finalyear_dip)
								.setParameter("year_provisional_certi_dip", year_provisional_certi_dip)
//								.setParameter("permission_lattter_dt", pr_latter_dt)
								.setParameter("ref_permission_latter", ref_permission_latter)
								.setParameter("id",Integer.parseInt(form_pg_diploma_id));
								
						int update = query.executeUpdate() > 0 ? 1 : 0;

						if (update > 0)
							data.put("updated", "Data Updated Successfully");
						else
							data.put("error", "Data Not Updated");
					}
					tx.commit();

				} catch (RuntimeException e) {

					try {

						tx.rollback();

						data.put("error", "Data Not Updated");

					} catch (RuntimeException rbe) {

						data.put("error", "Data Not Updated");

					}

				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				return data;
				
		 }

@RequestMapping(value = "/Submit_Approval_35_From_D", method = RequestMethod.POST)
public @ResponseBody Map<String, String> Submit_Approval_35_From_D(ModelMap Mmap, HttpSession session, HttpServletRequest request)
            throws ParseException {
                            
			Map<String, String> data = new HashMap<>();
			if(request.getHeader("Referer") == null ) 
		{ 
		data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
		return data;
		}
        
        String msg = "";
        Session sessionHQL = this.sessionFactory.openSession();
        Transaction tx = sessionHQL.beginTransaction();

        String userId = session.getAttribute("userId").toString();
        try {
                String hqlUpdate = "update from DG_REC_MEDICAL_QUAL_PG_DIPLOMA_35B_COURSES set institute_status=:institute_status where user_id=:user_id";
                int app = sessionHQL.createQuery(hqlUpdate)
                                .setInteger("institute_status",1)
                                .setInteger("user_id", Integer.parseInt(userId))
                            .executeUpdate();
                
                data.put("saved","Data Saved as Draft Successfully.") ;
				tx.commit();
                sessionHQL.close();
                if ((app > 0)) {
                        msg = "1";
                } else {
                        msg = "0";
                }
          } catch (Exception e) {
        }
        return data;
} 


/*------------------OPEN VIEW PAGE FOR UG FORM--------------------*/

@RequestMapping(value = "/view_detailfor_35A_Url", method = RequestMethod.GET)
public ModelAndView view_detailfor_35A_Url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {
	//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("view_detailfor_35A_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
	String roleid = session.getAttribute("roleid").toString();
	Mmap.put("msg", msg);

	return new ModelAndView("view_detail_UG_A_Tiles");
}


/*------------------POST METHOD FOR UG FORM--------------------*/

//@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
//public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//	System.err.println("----in model");
//	System.err.println("--------in controller-------"+hid);
//	ArrayList<ArrayList<String>> list = null;
//	try {
//		list = wDao.getPopup_Data(hid);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return list;
//}

@PostMapping(value="/getviewdata")
@ResponseBody public List<Map<String, Object>> getviewdata(String id) {
	return wDao.getviewdatatoByid(id);
}

/*------------------POST METHOD FOR UG FORM--------------------*/

@PostMapping("/getFilter_withinIndiaForm_A_UG_list")	

	public @ResponseBody List<Map<String, Object>> getFilter_withinIndiaForm_A_UG_list(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType,int id) throws ParseException {
		 
		 String userId = session.getAttribute("userId").toString();
   	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
	     int user_id=Integer.parseInt(userId);
	     
	     return wDao.getFilter_withinIndiaForm_A_UG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

   }
	 @PostMapping("/getFilter_withinIndiaForm_A_UGListCount")	
	 public @ResponseBody long getFilter_withinIndiaForm_A_UGListCount(HttpSession session ,String Search,int id) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
	     	 int user_id=Integer.parseInt(userId);
		return wDao.getFilter_withinIndiaForm_A_UGListCount(Search,user_id);//university_id
   }  
	 
	 
	 /*------------------OPEN VIEW PAGE FOR PG FORM--------------------*/

	 @RequestMapping(value = "/view_detailfor_35B_Url", method = RequestMethod.GET)
	 public ModelAndView view_detailfor_35B_Url(ModelMap Mmap, HttpSession session,
	 		@RequestParam(value = "msg", required = false) String msg,
	 		HttpServletRequest request) {
	 	
	 	String roleid = session.getAttribute("roleid").toString();
	 	Mmap.put("msg", msg);

	 	return new ModelAndView("view_detail_PG_B_Tiles");
	 }


	 /*------------------POST METHOD FOR PG FORM--------------------*/

	 //@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
	 //public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//	 	System.err.println("----in model");
//	 	System.err.println("--------in controller-------"+hid);
//	 	ArrayList<ArrayList<String>> list = null;
//	 	try {
//	 		list = wDao.getPopup_Data(hid);
//	 	} catch (Exception e) {
//	 		e.printStackTrace();
//	 	}
//	 	return list;
	 //}

	 @PostMapping(value="/getviewdatapg")
	 @ResponseBody public List<Map<String, Object>> getviewdatapg(String id) {
	 	return wDao.getviewdatatoBypgid(id);
	 }

	 /*------------------POST METHOD FOR PG FORM--------------------*/

	 @PostMapping("/getFilter_withinIndiaForm_B_PG_list")	

	 	public @ResponseBody List<Map<String, Object>> getFilter_withinIndiaForm_B_PG_list(HttpSession session,int startPage, int pageLength,
	 			String Search, String orderColunm, String orderType,int id) throws ParseException {
	 		 
	 		 String userId = session.getAttribute("userId").toString();
	    	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
	 	     int user_id=Integer.parseInt(userId);
	 	     
	 	     return wDao.getFilter_withinIndiaForm_B_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

	    }
	 	 @PostMapping("/getFilter_withinIndiaForm_B_PGListCount")	
	 	 public @ResponseBody long getFilter_withinIndiaForm_B_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
	 			 
	 			 String userId = session.getAttribute("userId").toString();
	 	     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
	 	     	 int user_id=Integer.parseInt(userId);
	 		return wDao.getFilter_withinIndiaForm_B_PGListCount(Search,user_id);//university_id
	    }  
	
		 /*------------------OPEN VIEW PAGE FOR PG C FORM--------------------*/

		 @RequestMapping(value = "/view_detailfor_35C_Url", method = RequestMethod.GET)
		 public ModelAndView view_detailfor_35C_Url(ModelMap Mmap, HttpSession session,
		 		@RequestParam(value = "msg", required = false) String msg,
		 		HttpServletRequest request) {
		 	
		 	String roleid = session.getAttribute("roleid").toString();
		 	Mmap.put("msg", msg);

		 	return new ModelAndView("view_detail_PG_Medical_Tiles");
		 }


		 /*------------------POST METHOD FOR PG C FORM--------------------*/

		 //@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
		 //public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//		 	System.err.println("----in model");
//		 	System.err.println("--------in controller-------"+hid);
//		 	ArrayList<ArrayList<String>> list = null;
//		 	try {
//		 		list = wDao.getPopup_Data(hid);
//		 	} catch (Exception e) {
//		 		e.printStackTrace();
//		 	}
//		 	return list;
		 //}

		 @PostMapping(value="/getviewdatapgmdeical")
		 @ResponseBody public List<Map<String, Object>> getviewdatapgmdeical(String id) {
		 	return wDao.getviewdatatoBypgmedicalid(id);
		 }

		 /*------------------POST METHOD FOR PG C FORM--------------------*/

		 @PostMapping("/getFilter_withinIndiaForm_C_PG_list")	

		 	public @ResponseBody List<Map<String, Object>> getFilter_withinIndiaForm_C_PG_list(HttpSession session,int startPage, int pageLength,
		 			String Search, String orderColunm, String orderType,int id) throws ParseException {
		 		 
		 		 String userId = session.getAttribute("userId").toString();
		    	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		 	     int user_id=Integer.parseInt(userId);
		 	     
		 	     return wDao.getFilter_withinIndiaForm_C_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

		    }
		 	 @PostMapping("/getFilter_withinIndiaForm_C_PGListCount")	
		 	 public @ResponseBody long getFilter_withinIndiaForm_C_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
		 			 
		 			 String userId = session.getAttribute("userId").toString();
		 	     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		 	     	 int user_id=Integer.parseInt(userId);
		 		return wDao.getFilter_withinIndiaForm_C_PGListCount(Search,user_id);//university_id
		    }
	 	 
			 /*------------------OPEN VIEW PAGE FOR PG D FORM--------------------*/

			 @RequestMapping(value = "/view_detailfor_35D_Url", method = RequestMethod.GET)
			 public ModelAndView view_detailfor_35D_Url(ModelMap Mmap, HttpSession session,
			 		@RequestParam(value = "msg", required = false) String msg,
			 		HttpServletRequest request) {
			 	
			 	String roleid = session.getAttribute("roleid").toString();
			 	Mmap.put("msg", msg);

			 	return new ModelAndView("view_detail_PG_Diploma_Tiles");
			 }


			 /*------------------POST METHOD FOR PG D FORM--------------------*/

			 //@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
			 //public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//			 	System.err.println("----in model");
//			 	System.err.println("--------in controller-------"+hid);
//			 	ArrayList<ArrayList<String>> list = null;
//			 	try {
//			 		list = wDao.getPopup_Data(hid);
//			 	} catch (Exception e) {
//			 		e.printStackTrace();
//			 	}
//			 	return list;
			 //}

			 @PostMapping(value="/getviewdatapgdiploma")
			 @ResponseBody public List<Map<String, Object>> getviewdatapgdiploma(String id) {
			 	return wDao.getviewdatatoBypgdiplomaid(id);
			 }

			 /*------------------POST METHOD FOR PG D FORM--------------------*/

			 @PostMapping("/getFilter_withinIndiaForm_D_PG_list")	

			 	public @ResponseBody List<Map<String, Object>> getFilter_withinIndiaForm_D_PG_list(HttpSession session,int startPage, int pageLength,
			 			String Search, String orderColunm, String orderType,int id) throws ParseException {
			 		 
			 		 String userId = session.getAttribute("userId").toString();
			    	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			 	     int user_id=Integer.parseInt(userId);
			 	     
			 	     return wDao.getFilter_withinIndiaForm_D_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

			    }
			 	 @PostMapping("/getFilter_withinIndiaForm_D_PGListCount")	
			 	 public @ResponseBody long getFilter_withinIndiaForm_D_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
			 			 
			 			 String userId = session.getAttribute("userId").toString();
			 	     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			 	     	 int user_id=Integer.parseInt(userId);
			 		return wDao.getFilter_withinIndiaForm_D_PGListCount(Search,user_id);//university_id
			    }
		 	 
}
