package com.AyushEdu.controller.Degree_recognition_NCISM;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_MEDICAL_QUA_ALL_STATE35;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_OUTSIDE_INDIA_UG_NCISM;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_WITHIN_INDIA_PG;
import com.AyushEdu.Models.Degree_recognition_NCISM.DG_REC_WITHIN_INDIA_UG;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_EXAMINERS_APPOINTED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_INTERN_STUDENT_COURSE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_FROM;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_SUB_CHILD;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_FORM_A;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_UG_STUDENT_LINK;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_NCISM.Degree_recognition_outside_indiaDao;
import com.AyushEdu.dao.Degree_recognition_NCISM.Degree_recognition_section_35_all_stateDao;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_A.Student_admitted_dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_Section_35 {
	
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
	Degree_recognition_section_35_all_stateDao wDao;
	
	@RequestMapping(value = "/Deg_rec_35_allstate_Url", method = RequestMethod.GET)
	public ModelAndView Deg_rec_35_allstate_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Deg_rec_35_allstate_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		String roleid = session.getAttribute("roleid").toString();
		Mmap.put("msg", msg);
		LocalDate date_without_time = LocalDate.now();
		
//		Mmap.put("getSystemList",common.getSystemByNCISM(sessionFactory));
////		Mmap.put("getCountryName", common.getMedCountryName(sessionFactory));
//		Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));
//		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
//		Mmap.put("date_without_time", date_without_time);
////		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
////		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getInstituteListForNcism",common.getInstituteListForNcism(sessionFactory));
		Mmap.put("getUniverCityList", common.getUniverCityList(sessionFactory));

		return new ModelAndView("Deg_rec_sec35_all_state_Tiles");
	}
	

/*------------------------------------SAVE/EDIT MEDICAL QUA ALL STATE FORM-----------------------------------*/

	@RequestMapping(value = "form35_section_all_state_action", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> form35_section_all_state_action(ModelMap Mmap, HttpSession session,HttpServletRequest request)throws ParseException {
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
				
				String file_no = request.getParameter("file_no");
				String file_date = request.getParameter("file_date");
				String all_university_name =request.getParameter("all_university_name");
				String college_name =request.getParameter("college_name");
				String medical_qua = request.getParameter("medical_qua");
				String medical_abbrv = request.getParameter("medical_abbrv");
				String sequence_code = request.getParameter("sequence_code");
				String all_state = request.getParameter("all_state");
				String velidity_period = request.getParameter("velidity_period");
				String form_section_35_state_id = request.getParameter("form_section_35_state_id");
//				System.err.println(form_a_ug_id+"------form_a_ug_idform_a_ug_id");
//				System.err.println(userId+"------userIduserIduserId");
				
				
//				try {

						if (form_section_35_state_id.equals("0")) {

							DG_REC_MEDICAL_QUA_ALL_STATE35 UG = new DG_REC_MEDICAL_QUA_ALL_STATE35();
						
						UG.setFile_no(file_no);
						UG.setFile_date(date);
						UG.setAll_state(all_state);
						UG.setAll_university_name(all_university_name);
						UG.setCollege_name(college_name);
						UG.setMedical_qua(medical_qua);
						UG.setMedical_abbrv(medical_abbrv);
						UG.setSequence_code(sequence_code);
						UG.setVelidity_period(date);
						UG.setStatus(0);
						UG.setCreated_by(username);
						UG.setCreated_date(date);
						UG.setUser_id(Integer.parseInt(userId));
						
						int id = (int) sessionHQL.save(UG);
						data.put("form_section_35_state_id", String.valueOf(id));
						
						data.put("saved","Data Saved as Draft Successfully.") ;
						

					} 
//					else {
//
//						String hql = "update DG_REC_OUTSIDE_INDIA_UG_NCISM set modified_by=:modified_by ,modified_date=:modified_date,"
//								+ "inst_coures_name=:inst_coures_name "
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
//								+ "where  id=:id";
//
//						
//						Query query = sessionHQL.createQuery(hql)
//								
//								.setParameter("modified_date", date)
//								.setParameter("modified_by",username)
//								.setParameter("inst_coures_name", inst_coures_name)
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
//								.setParameter("id",Integer.parseInt(form_a_ug_id));
//								
//						int update = query.executeUpdate() > 0 ? 1 : 0;
//
//						if (update > 0 )
//							data.put("updated", "Data Updated Successfully");
//						else
//							data.put("error", "Data Not Updated");
//						
						tx.commit();
//					}
//					
//
//				}
//				catch (RuntimeException e) {
//
//					try {
//
//						tx.rollback();
//
//						data.put("error", "Data Not Updated");
//
//					} catch (RuntimeException rbe) {
//
//						data.put("error", "Data Not Updated");
//
//					}
//
//				} 
//				finally {
//					if (sessionHQL != null) {
//						sessionHQL.close();
//					}
//				}
				return data;
		 }
	
	
	@RequestMapping(value = "/Submit_Approval_Data_Section_35_State_", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> Submit_Approval_Data_Section_35_State_(ModelMap Mmap, HttpSession session, HttpServletRequest request)
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

                                            String hqlUpdate = "update from DG_REC_MEDICAL_QUA_ALL_STATE35 set status=:status where user_id=:user_id";
                                            int app = sessionHQL.createQuery(hqlUpdate)
                                                            .setInteger("status",1)
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

	@PostMapping(value="/getviewdatasectionallstate35")
	@ResponseBody public List<Map<String, Object>> getviewdatasectionallstate35(String id) {
		return wDao.getviewdatatoBysectionallstate35id(id);
	}

	/*------------------POST METHOD FOR UG FORM--------------------*/

	@PostMapping("/getFilter_section_35_all_state_list")	

		public @ResponseBody List<Map<String, Object>> getFilter_section_35_all_state_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	   	     //int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return wDao.getFilter_section_35_all_state_list(startPage, pageLength, Search, orderColunm, orderType,user_id);//university_id

	   }
		 @PostMapping("/getFilter_section_35_all_state_Count")	
		 public @ResponseBody long getFilter_section_35_all_state_Count(HttpSession session ,String Search,int id) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		     	// int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
			return wDao.getFilter_section_35_all_state_Count(Search,user_id);//university_id
	   }
	


@RequestMapping(value = "/Deg_rec_35_ncism_web_Url", method = RequestMethod.GET)
public ModelAndView Deg_rec_35_ncism_web_Url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
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
	
//	Mmap.put("getSystemList",common.getSystemByNCISM(sessionFactory));
////	Mmap.put("getCountryName", common.getMedCountryName(sessionFactory));
//	Mmap.put("getUniversityList", common.getUniversityList(sessionFactory));
//	Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
//	Mmap.put("date_without_time", date_without_time);
////	Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
//	Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
////	Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));

	return new ModelAndView("Deg_rec_sec35_ncism_web_Tiles");
}

}
