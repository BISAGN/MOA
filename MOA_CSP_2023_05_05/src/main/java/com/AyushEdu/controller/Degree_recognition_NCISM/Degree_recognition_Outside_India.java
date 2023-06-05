package com.AyushEdu.controller.Degree_recognition_NCISM;


import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_OUT_INDIA;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_MEDICAL_QUAL_PG_36B_COURSES;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_OUTSIDE_INDIA_PG_NCISM;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_OUTSIDE_INDIA_UG_NCISM;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_WITHIN_INDIA_UG;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_NCISM.Degree_recognition_Within_inidaDao;
import com.AyushEdu.dao.Degree_recognition_NCISM.Degree_recognition_outside_indiaDao;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_A.Student_admitted_dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_Outside_India{
	
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
	Degree_recognition_outside_indiaDao wDao;
	
	
	@RequestMapping(value = "/Deg_rec_OutsideIndia_Url", method = RequestMethod.GET)
	public ModelAndView Deg_rec_OutsideIndia_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Deg_rec_OutsideIndia_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("getSystemByNCISM",common.getSystemByNCISM(sessionFactory));
		Mmap.put("getCountryName", getCountryName());
//		Mmap.put("getCountryName", common.getCountryName(sessionFactory));
		Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
//		Mmap.put("date_without_time", date_without_time);
//		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
//		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		
		return new ModelAndView("Deg_rec_OutsideIndia_Tiles");

	}
	
	/*------------------------------------SAVE/EDIT UG FORM-----------------------------------*/
	
	@RequestMapping(value = "form36_a_ug_action", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> form36_a_ug_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
		System.err.println("------in action");

				Map<String, String> data = new HashMap<>();
				if(request.getHeader("Referer") == null ){ 
				     data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
				     return data;
				}
				
				String username = session.getAttribute("username").toString();
				String userId = session.getAttribute("userId").toString();
				Date date = new Date();
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				String inst_coures_name = request.getParameter("inst_coures_name");
				String aprrove_cours_name = request.getParameter("aprrove_cours_name");
				String uni_name =request.getParameter("uni_name");
				String country_id =request.getParameter("country_id");
				String state = request.getParameter("state");
				String district = request.getParameter("district");
				String city = request.getParameter("city");
				String uni_address = request.getParameter("uni_address");
				String reg_email_id = request.getParameter("reg_email_id");
				String con_per_name = request.getParameter("con_per_name");
				String con_per_desg = request.getParameter("con_per_desg");
				String con_per_mob_no = request.getParameter("con_per_mob_no");
				String con_per_email_id = request.getParameter("con_per_email_id");
				String madical_stream = request.getParameter("madical_stream");
				String nomlat_degree = request.getParameter("nomlat_degree");
				String abbre_degree = request.getParameter("abbre_degree");
				String y_fir_bat = request.getParameter("y_fir_bat");
				String m_fir_bat = request.getParameter("m_fir_bat");
				String y_fir_bat_std_award = request.getParameter("y_fir_bat_std_award");
				String m_fir_bat_std_award = request.getParameter("m_fir_bat_std_award");
				String condition = request.getParameter("condition");
				String y_last_bat_std_award = request.getParameter("y_last_bat_std_award");
				String m_last_bat_std_award = request.getParameter("m_last_bat_std_award");
				String form_a_ug_id = request.getParameter("form_a_ug_id");
//				System.err.println(form_a_ug_id+"------form_a_ug_idform_a_ug_id");
//				System.err.println(userId+"------userIduserIduserId");
				
				
				try {

						if (form_a_ug_id.equals("0")) {

						DG_REC_OUTSIDE_INDIA_UG_NCISM UG = new DG_REC_OUTSIDE_INDIA_UG_NCISM();
						
						
						UG.setInst_coures_name(inst_coures_name);
						UG.setAprrove_cours_name(aprrove_cours_name);
						UG.setUni_name(uni_name);
						UG.setCountry_id(Integer.parseInt(country_id));
						UG.setState(state);
						UG.setDistrict(district);
						UG.setCity(city);
						UG.setUni_address(uni_address);
						UG.setReg_email_id(reg_email_id);
						UG.setCon_per_name(con_per_name);
						UG.setCon_per_desg(Integer.parseInt(con_per_desg));
						UG.setCon_per_mob_no(con_per_mob_no);
						UG.setCon_per_email_id(con_per_email_id);
						UG.setMadical_stream(Integer.parseInt(madical_stream));
						UG.setNomlat_degree(nomlat_degree);
						UG.setAbbre_degree(abbre_degree);
						UG.setY_fir_bat(y_fir_bat);
						UG.setM_fir_bat(m_fir_bat);
						UG.setY_fir_bat_std_award(y_fir_bat_std_award);
						UG.setM_fir_bat_std_award(m_fir_bat_std_award);
						 if (request.getParameter("condition") != null) {
		       					if (request.getParameter("condition").equalsIgnoreCase("No")) {
		       						UG.setY_last_bat_std_award(y_last_bat_std_award);
		       						UG.setM_last_bat_std_award(m_last_bat_std_award);
		       					}
		       				}
						UG.setInstitute_status(0);
						UG.setUniversity_status(0);
						UG.setNcism_status(0);
						UG.setCreated_by(username);
						UG.setCreated_date(date);
						UG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(UG);
						data.put("form_a_ug_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;
						

					} 
					else {

						String hql = "update DG_REC_OUTSIDE_INDIA_UG_NCISM set modified_by=:modified_by ,modified_date=:modified_date,"
								+ "inst_coures_name=:inst_coures_name "
//								+ "aprrove_cours_name=:aprrove_cours_name,"
//								+ "uni_name=:uni_name,"
//								+ "country_id=:country_id,"
//								+ "state=:state,"
//								+ "district=:district,"
//								+ "city=:city,"
//								+ "uni_address=:uni_address,"
//								+ "reg_email_id=:reg_email_id,"
//								+ "con_per_name=:con_per_name,"
//								+ "con_per_desg=:con_per_desg,"
//								+ "con_per_mob_no=:con_per_mob_no,"
//								+ "con_per_email_id=:con_per_email_id,"
//								+ "madical_stream=:madical_stream,"
//								+ "nomlat_degree=:nomlat_degree"
//								+ ",abbre_degree=:abbre_degree,"
//								+ "y_fir_bat=:y_fir_bat,"
//								+ "m_fir_bat=:m_fir_bat"
//								+",y_fir_bat_std_award=:y_fir_bat_std_award,"
//								+ "m_fir_bat_std_award=:m_fir_bat_std_award,"
//								+ "condition=:condition,"
//								+ "y_last_bat_std_award=:y_last_bat_std_award,"
//								+ "m_last_bat_std_award=:m_last_bat_std_award "
								+ "where  id=:id";

						
						Query query = sessionHQL.createQuery(hql)
								
								.setParameter("modified_date", date)
								.setParameter("modified_by",username)
								.setParameter("inst_coures_name", inst_coures_name)
//								.setParameter("aprrove_cours_name",aprrove_cours_name)
//								.setParameter("uni_name", uni_name)
//								.setParameter("country_id", Integer.parseInt(country_id))
//								.setParameter("state", state)
//								.setParameter("district", district)
//								.setParameter("city", city)
//								.setParameter("uni_address", uni_address)
//								.setParameter("reg_email_id", reg_email_id)
//								.setParameter("con_per_name", con_per_name)
//								.setParameter("con_per_desg", Integer.parseInt(con_per_desg))
//								.setParameter("con_per_mob_no", Integer.parseInt(con_per_mob_no))
//								.setParameter("con_per_email_id", con_per_email_id)
//								.setParameter("madical_stream", Integer.parseInt(madical_stream))
//								.setParameter("nomlat_degree", nomlat_degree)
//								.setParameter("abbre_degree", abbre_degree)
//								.setParameter("y_fir_bat", y_fir_bat)
//								.setParameter("m_fir_bat", m_fir_bat)
//								.setParameter("y_fir_bat_std_award", y_fir_bat_std_award)
//								.setParameter("m_fir_bat_std_award", m_fir_bat_std_award)
//								.setParameter("condition", Integer.parseInt(condition))
//								.setParameter("y_last_bat_std_award", y_last_bat_std_award)
//								.setParameter("m_last_bat_std_award", m_last_bat_std_award)
								.setParameter("id",Integer.parseInt(form_a_ug_id));
								
						int update = query.executeUpdate() > 0 ? 1 : 0;

						if (update > 0 )
							data.put("updated", "Data Updated Successfully");
						else
							data.put("error", "Data Not Updated");
						
						tx.commit();
					}
					

				}
				catch (RuntimeException e) {

					try {

						tx.rollback();

						data.put("error", "Data Not Updated");

					} catch (RuntimeException rbe) {

						data.put("error", "Data Not Updated");

					}

				} 
				finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				return data;
		 }
	
	public List<TB_COUNTRY> getCountryName() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from TB_COUNTRY  where status='1' ");
		 
		 List<TB_COUNTRY> CountryList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return CountryList;
	 }
	
	
	@RequestMapping(value = "/Submit_Approval_Data_institute_UG", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> Submit_Approval_Data_institute_UG(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
                                    
		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
{ 
 
 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
 return data;
}
                                    //System.err.println("-----in submit-----");
                                    
                                    String msg = "";
                                    Session sessionHQL = this.sessionFactory.openSession();
                                    Transaction tx = sessionHQL.beginTransaction();
                            
                                    String userId = session.getAttribute("userId").toString();
                                    //int institute_status = Integer.parseInt(request.getParameter("institute_status"));
                                    
                                    System.err.println(userId+"------userId");
                                    
                                    try {

                                            String hqlUpdate = "update from DG_REC_OUTSIDE_INDIA_UG_NCISM set institute_status=:institute_status where user_id=:user_id";
                                            int app = sessionHQL.createQuery(hqlUpdate)
                                                            .setInteger("institute_status",1)
                                                            .setInteger("user_id", Integer.parseInt(userId))
                                                        .executeUpdate();
                                            
                                            data.put("saved","Data Saved as Draft Successfully.") ;
                    						tx.commit();
                                            sessionHQL.close();
                                            if ((app > 0)) {
                                                    System.err.println("-----in if----"+app+"-----"+msg);
                                                    msg = "1";
                                                   
                                                    
                                            } else {
                                                    msg = "0";
                                            }
                                      } catch (Exception e) {
                                    }
                                    return data;
                            }  
	
	/*------------------------------------SAVE/EDIT PG B FORM-----------------------------------*/
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "form36_b_pg_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form36_b_pg_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
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
				String inst_cod_coures_name = request.getParameter("inst_cod_coures_name");
				String aprrove_courses_name = request.getParameter("aprrove_courses_name");
				String aff_uni_name =request.getParameter("aff_uni_name");
				String university_address = request.getParameter("university_address");
				String registered_email_id = request.getParameter("registered_email_id");
				String con_person_name = request.getParameter("con_person_name");
				String con_person_desg = request.getParameter("con_person_desg");
				String con_person_mob_no = request.getParameter("con_person_mob_no");
				String con_person_email_id = request.getParameter("con_person_email_id");
				String medical_stream = request.getParameter("medical_stream");
				String userId = session.getAttribute("userId").toString();
				String form_b_pg_id = request.getParameter("form_b_pg_id");
				String institute_status = request.getParameter("institute_status");
				
				try {

					if (Integer.parseInt(form_b_pg_id) == 0) {

						DG_REC_OUTSIDE_INDIA_PG_NCISM PG = new DG_REC_OUTSIDE_INDIA_PG_NCISM();
						
						
						PG.setInst_cod_coures_name(inst_cod_coures_name);
						PG.setAprrove_courses_name(aprrove_courses_name);
						PG.setAff_uni_name(aff_uni_name);
						PG.setUniversity_address(university_address);
						PG.setRegistered_email_id(registered_email_id);
						PG.setCon_person_name(con_person_name);
						PG.setCon_person_desg(Integer.parseInt(con_person_desg));
						PG.setCon_person_mob_no(con_person_mob_no);
						PG.setCon_person_email_id(con_person_email_id);
						PG.setMedical_stream(Integer.parseInt(medical_stream));
						PG.setInstitute_status(0);
						PG.setUniversity_status(0);
						PG.setNcism_status(0);
						PG.setCreated_by(username);
						PG.setCreated_date(date);
						PG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(PG);
						data.put("form_b_pg_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;
						

					} 
					else {

						String hql = "update DG_REC_OUTSIDE_INDIA_PG_NCISM set modified_by=:modified_by ,modified_date=:modified_date,"
								+ "inst_cod_coures_name=:inst_cod_coures_name, "
								+ "aprrove_courses_name=:aprrove_courses_name, "
								+ "aff_uni_name=:aff_uni_name, "
								+ "university_address=:university_address, "
								+ "registered_email_id=:registered_email_id, "
								+ "con_person_name=:con_person_name, "
//								+ "con_person_desg=:con_person_desg "
								+ "con_person_mob_no=:con_person_mob_no, "
								+ "con_person_email_id=:con_person_email_id "
//								+ "medical_stream=:medical_stream "
								+ "where  id=:id";

						Query query = sessionHQL.createQuery(hql)
								
								.setParameter("modified_date", date)
								.setParameter("modified_by",username)
								.setParameter("inst_cod_coures_name", inst_cod_coures_name)
								.setParameter("aprrove_courses_name",aprrove_courses_name)
								.setParameter("aff_uni_name", aff_uni_name)
								.setParameter("university_address", university_address)
								.setParameter("registered_email_id", registered_email_id)
								.setParameter("con_person_name", con_person_name)
//								.setParameter("con_person_desg", con_person_desg)
								.setParameter("con_person_mob_no", con_person_mob_no)
								.setParameter("con_person_email_id", con_person_email_id)
//								.setParameter("medical_stream", medical_stream)
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
	@RequestMapping(value = "/Submit_Approval_PGfor_Course", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> Submit_Approval_PGfor_Course(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
                                    
		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
{ 
 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
 return data;
}
                                    //System.err.println("-----in submit-----");
                                    
                                    String msg = "";
                                    Session sessionHQL = this.sessionFactory.openSession();
                                    Transaction tx = sessionHQL.beginTransaction();
                            
                                    String userId = session.getAttribute("userId").toString();
                                    //int institute_status = Integer.parseInt(request.getParameter("institute_status"));
                                    
                                    System.err.println(userId+"------userId");
                                    
                                    try {

                                            String hqlUpdate = "update from DG_REC_OUTSIDE_INDIA_PG_NCISM set institute_status=:institute_status where user_id=:user_id";
                                            int app = sessionHQL.createQuery(hqlUpdate)
                                                            .setInteger("institute_status",1)
                                                            .setInteger("user_id", Integer.parseInt(userId))
                                                        .executeUpdate();
                                            
                                            data.put("saved","Data Saved as Draft Successfully.") ;
                    						tx.commit();
                                            sessionHQL.close();
                                            if ((app > 0)) {
                                                    System.err.println("-----in if----"+app+"-----"+msg);
                                                    msg = "1";
                                                    
                                            } else {
                                                    msg = "0";
                                            }
                                      } catch (Exception e) {
                                    }
                                    return data;
                            } 
	
	/*------------------------------------SAVE/EDIT PG C FORM-----------------------------------*/
	
	
	@RequestMapping(value = "form36_c_pg_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form36_c_pg_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
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
				String pg_subject = request.getParameter("pg_subject");
				String nom_degree = request.getParameter("nom_degree");
				String abb_degree =request.getParameter("abb_degree");
				String m_y_add_fir_bat = request.getParameter("m_y_add_fir_bat");
				String m_y_exam_fir_bat = request.getParameter("m_y_exam_fir_bat");
				String m_y_pro_cer = request.getParameter("m_y_pro_cer");
				String pg_diploma_date = request.getParameter("pg_diploma_date");
				String ref_letter_per = request.getParameter("ref_letter_per");
				String userId = session.getAttribute("userId").toString();
				String form_c_pg_id = request.getParameter("form_c_pg_id");
				String institute_status = request.getParameter("institute_status");
				String university_status = request.getParameter("university_status");
				String ncism_status = request.getParameter("ncism_status");
				System.err.println(form_c_pg_id+"------form_c_pg_idform_c_pg_id");
				System.err.println(userId+"------userIduserIduserId");
				
				Date pg_dip_dt = null;
				
				  if (!pg_diploma_date.trim().equals("") && !pg_diploma_date.equals("DD/MM/YYYY")) {
					  pg_dip_dt = format.parse(pg_diploma_date);
				  }
				
				try { 
				
			

					if (Integer.parseInt(form_c_pg_id) == 0) {

						DG_REC_MEDICAL_QUAL_PG_36B_COURSES PG = new DG_REC_MEDICAL_QUAL_PG_36B_COURSES();
						
						
						PG.setPg_subject(pg_subject);
						PG.setNom_degree(nom_degree);
						PG.setAbb_degree(abb_degree);
						PG.setM_y_add_fir_bat(m_y_add_fir_bat);
						PG.setM_y_exam_fir_bat(m_y_exam_fir_bat);
						PG.setM_y_pro_cer(m_y_pro_cer);
						PG.setPg_diploma_date(pg_dip_dt);
						PG.setRef_letter_per(ref_letter_per);
						PG.setInstitute_status(0);
						PG.setUniversity_status(0);
						PG.setNcism_status(0);
						PG.setCreated_by(username);
						PG.setCreated_date(date);
						PG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(PG);
						data.put("form_c_pg_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;
						

					} 
					else {

						String hql = "update DG_REC_MEDICAL_QUAL_PG_36B_COURSES set modified_by=:modified_by ,modified_date=:modified_date,"
								+ "pg_subject=:pg_subject, "
								+ "nom_degree=:nom_degree, "
								+ "abb_degree=:abb_degree, "
								+ "m_y_add_fir_bat=:m_y_add_fir_bat, "
								+ "m_y_exam_fir_bat=:m_y_exam_fir_bat, "
								+ "m_y_pro_cer=:m_y_pro_cer, "
//								+ "pg_dip_dt=:pg_dip_dt "
								+ "ref_letter_per=:ref_letter_per "
								+ "where id=:id";

						
						Query query = sessionHQL.createQuery(hql)
								
								.setParameter("modified_date", date)
								.setParameter("modified_by",username)
								.setParameter("pg_subject", pg_subject)
								.setParameter("nom_degree",nom_degree)
								.setParameter("abb_degree",abb_degree)
								.setParameter("m_y_add_fir_bat", m_y_add_fir_bat)
								.setParameter("m_y_exam_fir_bat", m_y_exam_fir_bat)
								.setParameter("m_y_pro_cer", m_y_pro_cer)
//								.setParameter("pg_diploma_date", pg_dip_dt)
								.setParameter("ref_letter_per", ref_letter_per)
								.setParameter("id",Integer.parseInt(form_c_pg_id));
								
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
	
	@RequestMapping(value = "/Submit_Approval_PG_C_for_Course", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> Submit_Approval_PG_C_for_Course(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
                                    
		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
{ 
 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
 return data;
}
                                    //System.err.println("-----in submit-----");
                                    
                                    String msg = "";
                                    Session sessionHQL = this.sessionFactory.openSession();
                                    Transaction tx = sessionHQL.beginTransaction();
                            
                                    String userId = session.getAttribute("userId").toString();
                                    //int institute_status = Integer.parseInt(request.getParameter("institute_status"));
                                    
                                    System.err.println(userId+"------userId");
                                    
                                    try {

                                            String hqlUpdate = "update from DG_REC_MEDICAL_QUAL_PG_36B_COURSES set university_status=:university_status where user_id=:user_id";
                                            int app = sessionHQL.createQuery(hqlUpdate)
                                                            .setInteger("university_status",1)
                                                            .setInteger("user_id", Integer.parseInt(userId))
                                                        .executeUpdate();
                                            
                                            data.put("saved","Data Saved as Draft Successfully.") ;
                    						tx.commit();
                                            sessionHQL.close();
                                            if ((app > 0)) {
                                                    System.err.println("-----in if----"+app+"-----"+msg);
                                                    msg = "1";
                                                    
                                            } else {
                                                    msg = "0";
                                            }
                                      } catch (Exception e) {
                                    }
                                    return data;
                            } 
	
	
	/*------------------------------------SAVE/EDIT PG D FORM-----------------------------------*/
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "form36_d_pg_action", method = RequestMethod.POST)

			public @ResponseBody Map<String, String> form36_d_pg_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
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
				String form_d_pg_id = request.getParameter("form_d_pg_id");
				String institute_status = request.getParameter("institute_status");
				String university_status = request.getParameter("university_status");
				String ncism_status = request.getParameter("ncism_status");
				System.err.println(form_d_pg_id+"------form_d_pg_idform_d_pg_id");
				System.err.println(userId+"------userIduserIduserId");
				
				Date per_latter_dt = null;
				
				  if (!permission_lattter_dt.trim().equals("") && !permission_lattter_dt.equals("DD/MM/YYYY")) {
					  per_latter_dt = format.parse(permission_lattter_dt);
				  }
				
				
				try { 
				
			

					if (Integer.parseInt(form_d_pg_id) == 0) {

						DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES PG = new DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES();
						
						
						PG.setPg_dip_subject(pg_dip_subject);
						PG.setNomenclature_dg_dip(nomenclature_dg_dip);
						PG.setAbbreviation_dg_dip(abbreviation_dg_dip);
						PG.setYear_admi_first_batch_dip(year_admi_first_batch_dip);
						PG.setPassing_year_of_finalyear_dip(passing_year_of_finalyear_dip);
						PG.setYear_provisional_certi_dip(year_provisional_certi_dip);
						PG.setPermission_lattter_dt(per_latter_dt);
						PG.setRef_permission_latter(ref_permission_latter);
						PG.setInstitute_status(0);
						PG.setUniversity_status(0);
						PG.setNcism_status(0);
						PG.setCreated_by(username);
						PG.setCreated_date(date);
						PG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(PG);
						data.put("form_d_pg_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;
						

					} 
					else {

						String hql = "update DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES set modified_by=:modified_by ,modified_date=:modified_date,"
								+ "pg_dip_subject=:pg_dip_subject, "
								+ "nomenclature_dg_dip=:nomenclature_dg_dip, "
								+ "abbreviation_dg_dip=:abbreviation_dg_dip, "
								+ "year_admi_first_batch_dip=:year_admi_first_batch_dip, "
								+ "passing_year_of_finalyear_dip=:passing_year_of_finalyear_dip, "
								+ "year_provisional_certi_dip=:year_provisional_certi_dip, "
//								+ "per_latter_dt=:per_latter_dt, "
								+ "ref_permission_latter=:ref_permission_latter "
								+ "where  id=:id";

						
						Query query = sessionHQL.createQuery(hql)
								
								.setParameter("modified_date", date)
								.setParameter("modified_by",username)
								.setParameter("pg_dip_subject", pg_dip_subject)
								.setParameter("nomenclature_dg_dip",nomenclature_dg_dip)
								.setParameter("abbreviation_dg_dip", abbreviation_dg_dip)
								.setParameter("year_admi_first_batch_dip", year_admi_first_batch_dip)
								.setParameter("passing_year_of_finalyear_dip", passing_year_of_finalyear_dip)
								.setParameter("year_provisional_certi_dip", year_provisional_certi_dip)
//								.setParameter("permission_lattter_dt", per_latter_dt)
								.setParameter("ref_permission_latter", ref_permission_latter)
								.setParameter("id",Integer.parseInt(form_d_pg_id));
								
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
	
	@RequestMapping(value = "/Submit_Approval_PG_D_for_Course", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> Submit_Approval_PG_D_for_Course(ModelMap Mmap, HttpSession session, HttpServletRequest request)
                    throws ParseException {
                                    
		Map<String, String> data = new HashMap<>();
		if(request.getHeader("Referer") == null ) 
{ 
 data.put("error","Suspicious Activity Detected,You have been logged out by Administrator") ;
 return data;
}
                                    //System.err.println("-----in submit-----");
                                    
                                    String msg = "";
                                    Session sessionHQL = this.sessionFactory.openSession();
                                    Transaction tx = sessionHQL.beginTransaction();
                            
                                    String userId = session.getAttribute("userId").toString();
                                    //int institute_status = Integer.parseInt(request.getParameter("institute_status"));
                                    
                                    System.err.println(userId+"------userId");
                                    
                                    try {

                                            String hqlUpdate = "update from DG_REC_MEDICAL_QUAL_PG_DIPLOMA_36B_COURSES set university_status=:university_status where user_id=:user_id";
                                            int app = sessionHQL.createQuery(hqlUpdate)
                                                            .setInteger("university_status",1)
                                                            .setInteger("user_id", Integer.parseInt(userId))
                                                        .executeUpdate();
                                            
                                            data.put("saved","Data Saved as Draft Successfully.") ;
                    						tx.commit();
                                            sessionHQL.close();
                                            if ((app > 0)) {
                                                    System.err.println("-----in if----"+app+"-----"+msg);
                                                    msg = "1";
                                                    
                                            } else {
                                                    msg = "0";
                                            }
                                      } catch (Exception e) {
                                    }
                                    return data;
                            } 
	
	
	/*------------------OPEN VIEW PAGE FOR UG FORM--------------------*/

	@RequestMapping(value = "/view_detailfor_36A_Url", method = RequestMethod.GET)
	public ModelAndView view_detailfor_35A_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("view_detailfor_36A_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);

		return new ModelAndView("view_detail_outsideindia_UG_A_Tiles");
	}


	/*------------------POST METHOD FOR UG FORM--------------------*/

	//@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
	//public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//		System.err.println("----in model");
//		System.err.println("--------in controller-------"+hid);
//		ArrayList<ArrayList<String>> list = null;
//		try {
//			list = wDao.getPopup_Data(hid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
	//}

	@PostMapping(value="/getviewdataoutsideindiaug")
	@ResponseBody public List<Map<String, Object>> getviewdataoutsideindiaug(String id) {
		return wDao.getviewdatatoByoutsideindiaugid(id);
	}

	/*------------------POST METHOD FOR UG FORM--------------------*/

	@PostMapping("/getFilter_outsideIndiaForm_A_UG_list")	

		public @ResponseBody List<Map<String, Object>> getFilter_outsideIndiaForm_A_UG_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	   	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return wDao.getFilter_outsideIndiaForm_A_UG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

	   }
		 @PostMapping("/getFilter_outsideIndiaForm_A_UGListCount")	
		 public @ResponseBody long getFilter_outsideIndiaForm_A_UGListCount(HttpSession session ,String Search,int id) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
			return wDao.getFilter_outsideIndiaForm_A_UGListCount(Search,user_id);//university_id
	   }
	
			/*------------------OPEN VIEW PAGE FOR PG FORM--------------------*/

			@RequestMapping(value = "/view_detailfor_36B_Url", method = RequestMethod.GET)
			public ModelAndView view_detailfor_35B_Url(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request) {
				
				String roleid = session.getAttribute("roleid").toString();
				Mmap.put("msg", msg);

				return new ModelAndView("view_detail_outsideindia_PG_B_Tiles");
			}


			/*------------------POST METHOD FOR UG FORM--------------------*/

			//@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
			//public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//				System.err.println("----in model");
//				System.err.println("--------in controller-------"+hid);
//				ArrayList<ArrayList<String>> list = null;
//				try {
//					list = wDao.getPopup_Data(hid);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return list;
			//}

			@PostMapping(value="/getviewdataoutsideindiapg")
			@ResponseBody public List<Map<String, Object>> getviewdataoutsideindiapg(String id) {
				return wDao.getviewdatatoByoutsideindiapgid(id);
			}

			/*------------------POST METHOD FOR UG FORM--------------------*/

			@PostMapping("/getFilter_outsideIndiaForm_B_PG_list")	

				public @ResponseBody List<Map<String, Object>> getFilter_outsideIndiaForm_B_PG_list(HttpSession session,int startPage, int pageLength,
						String Search, String orderColunm, String orderType,int id) throws ParseException {
					 
					 String userId = session.getAttribute("userId").toString();
			   	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
				     int user_id=Integer.parseInt(userId);
				     
				     return wDao.getFilter_outsideIndiaForm_B_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

			   }
				 @PostMapping("/getFilter_outsideIndiaForm_B_PGListCount")	
				 public @ResponseBody long getFilter_outsideIndiaForm_B_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
						 
						 String userId = session.getAttribute("userId").toString();
				     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
				     	 int user_id=Integer.parseInt(userId);
					return wDao.getFilter_outsideIndiaForm_B_PGListCount(Search,user_id);//university_id
			   }
				 
					/*------------------OPEN VIEW PAGE FOR PG FORM--------------------*/

					@RequestMapping(value = "/view_detailfor_36C_Url", method = RequestMethod.GET)
					public ModelAndView view_detailfor_36C_Url(ModelMap Mmap, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg,
							HttpServletRequest request) {
						
						String roleid = session.getAttribute("roleid").toString();
						Mmap.put("msg", msg);

						return new ModelAndView("view_detail_outsideindia_PG_C_Tiles");
					}


					/*------------------POST METHOD FOR UG FORM--------------------*/

					//@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
					//public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//						System.err.println("----in model");
//						System.err.println("--------in controller-------"+hid);
//						ArrayList<ArrayList<String>> list = null;
//						try {
//							list = wDao.getPopup_Data(hid);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						return list;
					//}

					@PostMapping(value="/getviewdataoutsideindiapgmdeical")
					@ResponseBody public List<Map<String, Object>> getviewdataoutsideindiapgmdeical(String id) {
						return wDao.getviewdatatoByoutsideindiapgmedicalid(id);
					}

					/*------------------POST METHOD FOR UG FORM--------------------*/

					@PostMapping("/getFilter_outsideIndiaForm_C_PG_list")	

						public @ResponseBody List<Map<String, Object>> getFilter_outsideIndiaForm_C_PG_list(HttpSession session,int startPage, int pageLength,
								String Search, String orderColunm, String orderType,int id) throws ParseException {
							 
							 String userId = session.getAttribute("userId").toString();
					   	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
						     int user_id=Integer.parseInt(userId);
						     
						     return wDao.getFilter_outsideIndiaForm_C_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

					   }
						 @PostMapping("/getFilter_outsideIndiaForm_C_PGListCount")	
						 public @ResponseBody long getFilter_outsideIndiaForm_C_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
								 
								 String userId = session.getAttribute("userId").toString();
						     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
						     	 int user_id=Integer.parseInt(userId);
							return wDao.getFilter_outsideIndiaForm_C_PGListCount(Search,user_id);//university_id
					   }
						 
						 /*------------------OPEN VIEW PAGE FOR PG D FORM--------------------*/

						 @RequestMapping(value = "/view_detailfor_36D_Url", method = RequestMethod.GET)
						 public ModelAndView view_detailfor_36D_Url(ModelMap Mmap, HttpSession session,
						 		@RequestParam(value = "msg", required = false) String msg,
						 		HttpServletRequest request) {
						 	
						 	String roleid = session.getAttribute("roleid").toString();
						 	Mmap.put("msg", msg);

						 	return new ModelAndView("view_detail_outsideindia_PG_D_Tiles");
						 }


						 /*------------------POST METHOD FOR PG D FORM--------------------*/

						 //@RequestMapping(value = "/view_detail_UG_A_View_Url", method = RequestMethod.POST)
						 //public @ResponseBody ArrayList<ArrayList<String>> view_detail_UG_A_View_Url(String hid) {
//						 	System.err.println("----in model");
//						 	System.err.println("--------in controller-------"+hid);
//						 	ArrayList<ArrayList<String>> list = null;
//						 	try {
//						 		list = wDao.getPopup_Data(hid);
//						 	} catch (Exception e) {
//						 		e.printStackTrace();
//						 	}
//						 	return list;
						 //}

						 @PostMapping(value="/getviewdataoutsideindiapgdiploma")
						 @ResponseBody public List<Map<String, Object>> getviewdataoutsideindiapgdiploma(String id) {
						 	return wDao.getviewdatatoByoutsideindiapgdiplomaid(id);
						 }

						 /*------------------POST METHOD FOR PG D FORM--------------------*/

						 @PostMapping("/getFilter_outsideIndiaForm_D_PG_list")	

						 	public @ResponseBody List<Map<String, Object>> getFilter_outsideIndiaForm_D_PG_list(HttpSession session,int startPage, int pageLength,
						 			String Search, String orderColunm, String orderType,int id) throws ParseException {
						 		 
						 		 String userId = session.getAttribute("userId").toString();
						    	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
						 	     int user_id=Integer.parseInt(userId);
						 	     
						 	     return wDao.getFilter_outsideIndiaForm_D_PG_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

						    }
						 	 @PostMapping("/getFilter_outsideIndiaForm_D_PGListCount")	
						 	 public @ResponseBody long getFilter_outsideIndiaForm_D_PGListCount(HttpSession session ,String Search,int id) throws ParseException {
						 			 
						 			 String userId = session.getAttribute("userId").toString();
						 	     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
						 	     	 int user_id=Integer.parseInt(userId);
						 		return wDao.getFilter_outsideIndiaForm_D_PGListCount(Search,user_id);//university_id
						    }		 
}

