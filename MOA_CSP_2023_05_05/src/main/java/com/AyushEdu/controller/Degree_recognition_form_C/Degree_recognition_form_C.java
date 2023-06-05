package com.AyushEdu.controller.Degree_recognition_form_C;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_form_C.APPEAL_DECLARATION;
import com.AyushEdu.Models.Degree_recognition_form_C.APPEAL_FORM_C;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_form_C {
	
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
	Form_a_ug_Dao UDdao;
	@Autowired
	Gender_DAO sdao;
	@Autowired
	Form_a_ug_Dao Form_a_ug_DAO;
	@Autowired
	Form_b_pg_Dao PDdao;
	@Autowired
	Commondao commondao;
	
	//==========================================OPEN PAGE DEGREE RECOGNITION========================================== 
	
		@RequestMapping(value = "/Appeal_form_C_Url", method = RequestMethod.GET)
		public ModelAndView Appeal_form_C_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			//SECURITY ABHISHEK
					if(request.getHeader("Referer") == null ) { 
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Appeal_form_C_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						Mmap.put("msg", msg);
//						Mmap.put("getUniversityList", getUniversityList());
//						Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
						Mmap.put("getMedUniversitynchName", common.getMedUniversitynchName(sessionFactory));
						Mmap.put("getMedInstitutenchName", common.getMedInstitutenchName(sessionFactory));
//						Mmap.put("getMedicalList", getMedicalList());
						return new ModelAndView("Appeal_form_C_Tiles");
		}
		
		
		
		 @PostMapping(value = "/form_c_appeal_action")
			public ModelAndView form_c_appeal_action(@Validated @ModelAttribute("APPEAL_Form_C_CMD") APPEAL_FORM_C td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
			 
			System.out.println("----in action--formmmm-");
			
			@SuppressWarnings("unused")
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		    String userId = session.getAttribute("userId").toString(); 
			String username = session.getAttribute("username").toString();
			String aggrieved_university_name = request.getParameter("aggrieved_university_name");
			String aggrieved_university_address = request.getParameter("aggrieved_university_address");
			String university_registrar_email_id = request.getParameter("university_registrar_email_id");
			String contact_person_name=request.getParameter("contact_person_name");
			String contact_person_designation =request.getParameter("contact_person_designation");
			String contact_person_mobile_no = request.getParameter("contact_person_mobile_no");
			String contact_person_email_id = request.getParameter("contact_person_email_id");
			String institute_name = request.getParameter("institute_name");
			String nomenclature_of_degree = request.getParameter("nomenclature_of_degree");
			String abbreviation_of_degree = request.getParameter("abbreviation_of_degree");
			String first_application_date = request.getParameter("first_application_date");
			String denial_application_date = request.getParameter("denial_application_date");
			String reason = request.getParameter("reason");
			String prayer_of_the_university = request.getParameter("prayer_of_the_university");
			String document = request.getParameter("document");
			
//			if (academic_file_upload=="") {
//				academic_file_upload = hid_upload_file;
//			}
//			if (permission_from_central_gov=="") {
//				permission_from_central_gov = hid_upload_file2;
//			}
			int id = td.getId() > 0 ? td.getId() : 0;
			
			try {
				Long c = (Long) sessionHQL.createQuery(
						
						"select count(id) from  APPEAL_FORM_C where aggrieved_university_name=:aggrieved_university_name and "
						+ "aggrieved_university_address=:aggrieved_university_address and "
						+ "university_registrar_email_id=:university_registrar_email_id and "
						+ "contact_person_name=:contact_person_name and "
						+ "contact_person_designation=:contact_person_designation and "
						+ "contact_person_mobile_no=:contact_person_mobile_no and "
						+ "institute_name=:institute_name and "
						+ "nomenclature_of_degree=:nomenclature_of_degree and "
						+ "abbreviation_of_degree=:abbreviation_of_degree and " 
						+ "first_application_date=:first_application_date and "
						+ "denial_application_date=:denial_application_date and "
						+ "reason=:reason and "
						+ "prayer_of_the_university=:prayer_of_the_university and "
						+"document=:document and "
						+ "id !=:id")
						
						.setParameter("aggrieved_university_name", td.getAggrieved_university_name())
						.setParameter("aggrieved_university_address", td.getAggrieved_university_address())
						.setParameter("university_registrar_email_id", td.getUniversity_registrar_email_id())
						.setParameter("contact_person_name", td.getContact_person_name())
						.setParameter("contact_person_designation", td.getContact_person_designation())
						.setParameter("contact_person_mobile_no", td.getContact_person_mobile_no())
						.setParameter("institute_name", td.getInstitute_name())
						.setParameter("nomenclature_of_degree", td.getNomenclature_of_degree())
						.setParameter("abbreviation_of_degree", td.getAbbreviation_of_degree())
						.setParameter("first_application_date", td.getModified_date())
						.setParameter("denial_application_date", td.getDenial_application_date())
						.setParameter("reason",td.getReason())
						.setParameter("prayer_of_the_university", td.getPrayer_of_the_university())
						.setParameter("document",td.getDocument())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					
					td.setAggrieved_university_name(aggrieved_university_name);
					td.setAggrieved_university_address(aggrieved_university_address);
					td.setUniversity_registrar_email_id(university_registrar_email_id);
					td.setContact_person_name(contact_person_name);
					td.setContact_person_designation(contact_person_designation);
					td.setContact_person_mobile_no(contact_person_mobile_no);
					td.setInstitute_name(institute_name);
					td.setNomenclature_of_degree(nomenclature_of_degree);
					td.setAbbreviation_of_degree(abbreviation_of_degree);
					td.setFirst_application_date(date);
					td.setDenial_application_date(date);
					td.setReason(reason);
					td.setPrayer_of_the_university(prayer_of_the_university);
					td.setDocument(document);
					td.setCreated_date(date);
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setUnivercity_id(1);
					td.setUniversity_status(1);
//					td.setCouncil_status(1);
					td.setCurrent_month_year(current_mm_yy);
					td.setUser_id(Integer.parseInt(userId));
					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} 
				tx.commit();
			} catch (RuntimeException e) {
				try {
					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:Appeal_form_C_Url");
		}

		 
		
			
			 /////////// Declaration Save ///////////
			 @PostMapping(value = "/appeal_declaration_action")
				public ModelAndView appeal_declaration_action(@Validated @ModelAttribute("Appeal_declarationCMD") APPEAL_DECLARATION td, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						RedirectAttributes ra,MultipartHttpServletRequest mul) throws  IOException, java.text.ParseException {
					
						System.out.println("----in dec-formmmm-");
						DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						@SuppressWarnings("unused")
						DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Date date = new Date();
						
						String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
					    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
					    String current_mm_yy = month+"-"+year;
					        
						String username = session.getAttribute("username").toString();
						System.out.println("--------- username-----------"+username);
						String userId = session.getAttribute("userId").toString();
						System.out.println("--------- userId-----------"+userId);
						String upload_signature = gd(request, mul, session, "upload_signature");
						System.out.println("----upload_signature"+upload_signature);
						String hid_upload_file = request.getParameter("hid_upload_file");
						
						String declaration_date = request.getParameter("declaration_date");
						String current_month_year = request.getParameter("Calendar");
						String university_id = session.getAttribute("university_id").toString();
						
						 Date d_decl = null;
						  
						  if (!declaration_date.trim().equals("") && !declaration_date.equals("DD/MM/YYYY")) {
							  d_decl = format.parse(declaration_date);
						  }
						  if (upload_signature=="") {
							  upload_signature = hid_upload_file;
							}
						int id = td.getId() > 0 ? td.getId() : 0;
						
						try {
							Long c = (Long) sessionHQL.createQuery(
									
//									"select count(id) from  APPEAL_DECLARATION where upload_signature=:upload_signature and "
//									+ "declaration_date=:declaration_date and  id !=:id")
								
									"select count(id) from  APPEAL_DECLARATION where upload_signature=:upload_signature and "
									+ "declaration_date=:declaration_date and  id !=:id")
									
									.setParameter("upload_signature", td.getUpload_signature())
									.setParameter("declaration_date",td.getDeclaration_date())
									.setParameter("id", id).uniqueResult();
							
							if (id == 0) {
								
								System.out.println("------upload_signature--"+upload_signature);
								td.setUpload_signature(upload_signature);
								td.setDeclaration_date(d_decl);
								td.setUser_id(Integer.parseInt(userId));
								td.setCreated_date(date);
								td.setCreated_by(username);
								td.setCreated_date(date);
								td.setUser_id(Integer.parseInt(userId));
								td.setUniversity_id(Integer.parseInt(university_id));
								td.setCurrent_month_year(current_mm_yy);
								td.setUniversity_approved_status(1);
								if (c == 0) {
									sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									ra.addAttribute("msg", "Data Saved Successfully.");
								} else {
									ra.addAttribute("msg", "Data already Exist.");
								}
							} 
							tx.commit();
						} catch (RuntimeException e) {
							try {
								ra.addAttribute("msg", "roll back transaction");
							} catch (RuntimeException rbe) {
								ra.addAttribute("msg", "Couldn t roll back transaction " + rbe);
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						return new ModelAndView("redirect:Appeal_form_C_Url");
					}
			//====================================file uplode=================================================//

				public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
							throws IOException {

						String extension = ""; // add line
						String fname = ""; // add line

						request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

						MultipartFile file = mul.getFile(id);
						if (!file.getOriginalFilename().isEmpty()) {

							byte[] bytes = file.getBytes();
							String mnhFilePath = session.getAttribute(id).toString();

							File dir = new File(mnhFilePath);
							if (!dir.exists())
								dir.mkdirs();
							String filename = file.getOriginalFilename();

							int j = filename.lastIndexOf('.');
							if (j >= 0) {
								extension = filename.substring(j + 1);
							}
							java.util.Date date1 = new java.util.Date();
							fname = dir.getAbsolutePath()
									+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
											.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
									+ "." + extension;

							File serverFile = new File(fname);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();

						} else {
						}
						return fname;

					}
	}
