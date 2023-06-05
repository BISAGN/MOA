package com.AyushEdu.controller.Exp_Excel;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;
import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.controller.LMS_NCISM.Admission_Student_Report_PDF;
import com.AyushEdu.controller.LMS_NCISM.Studentdetails_Report_Excell_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Exp_Excel.Student_DetailsDao;
import com.AyushEdu.dao.LMS_NCISM.StudentDetailsReport_DAO;
import com.AyushEdu.dao.Registration.E_Form_Student_Dtl_DAO;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Exp_Excel_Nch_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Commondao cd;
	@Autowired
	CommonController common;
	@Autowired
	Student_DetailsDao sdao;
	@Autowired
	E_Form_Student_Dtl_DAO edao;
	@Autowired
	EmailConfig emailsend;
	@Autowired
	Search_Student_RegistrationDao SSRnchDao;
	@Autowired
	Search_Ncism_Student_RegistrationDao SSRncismDao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	UserLoginDAO userLoginDAO;
	@Autowired
	StudentDetailsReport_DAO  sddao;

	
	

	@GetMapping(value = "/exp_excel_nch_url")
	public ModelAndView exp_excel_nch_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		try {
			
			//SECURITY ABHISHEK
			if (request.getHeader("Referer") == null) {
				
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15"));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			
//			----------------------------------------------------------------------------------------------
			
			String role = session.getAttribute("rolename").toString();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("userid========================="+userid);
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
//			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15"));
			model.put("list", edao.E_Form_Student_DtlData(role,institute_id));
//			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			model.put("id", institute_id);

			model.put("getMedStateName", common.getMedStateName(sessionFactory));
			model.put("getcategorylist", common.getcategoryList(sessionFactory));
			model.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			model.put("getQuotaList", common.getQuotaList(sessionFactory));
			model.put("getInsCodeListbyInsName", common.getInsCodeListbyInsName(sessionFactory,institute_id));
//			model.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
			
			 String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
				if (Staff_lvl.toUpperCase().equals("NCH")) {
					model.put("getapp_instituteNameList", SSRnchDao.getinstitutelist(userid));
				}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
					model.put("getapp_instituteNameList", SSRncismDao.getinstitute_Ncismlist(userid));
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Exp_Excel_Nch_Tiles");
	}

//	@RequestMapping(value = "/Exp_Excel_Nch", method = RequestMethod.POST)
//	public ModelAndView Exp_Excel_Nch(HttpServletRequest request, ModelMap model, HttpSession session, String typeReport1) {
//		
////		try {
//		
//		//SECURITY ABHISHEK
//		if (request.getHeader("Referer") == null) {
//			
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//		
//		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
//		model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id)));
//		
//		ArrayList<ArrayList<String>> listSystem =  cd.getinstitute_system(Integer.parseInt(institute_id));
//		
//		ArrayList<ArrayList<String>> listDegree =  cd.getstu_DegreeList(Integer.parseInt(institute_id));
//		
//		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
//		
//		List<EDU_LMS_TERM_MASTER> listterm = common.getTerm();
//		
//		List<EDU_LMS_GENDER_MSTR> listgender = common.getGender();
//		
//		List<String> TH = new ArrayList<String>();
//		
//		
//		
//		
////		TH.add("Ayush_id");
//		TH.add("Name");
//		TH.add("System:Drop:" + "HOMOEOPATHY");
//		TH.add("Degree:Drop:" + "BHMS");
//		if(!listterm.isEmpty()) {
//			String Mst2="";
//			for(int j=0; j<listterm.size(); j++ ) {
//				if(j==listterm.size()) {
//					Mst2+=listterm.get(j).getTerm();
//				}
//				else if (j==0) {
//					Mst2+=listterm.get(j).getTerm();
//				}
//				else {
//					Mst2+=","+listterm.get(j).getTerm();
//				}
//				
//			}
//			TH.add("Profession:Drop:" + Mst2);
//		}
//		else {
//			TH.add("Profession:Drop:" + "Profession not available");
//		}
//		TH.add("Enrollment_No");
//		TH.add("Date_of_Birth");
//		TH.add("Aadhar_Card");
//		TH.add("Email");
//		TH.add("Mobile_No");
//		if(!listgender.isEmpty()) {
//			String Mst3="";
//			for(int j=0; j<listgender.size(); j++ ) {
//				
//				System.err.println("j---->   "+j);
//				
//				
//				if(j==listgender.size()) {
//					Mst3+=listgender.get(j).getGender_name();
//				}
//				else if (j==0) {
//					Mst3+=listgender.get(j).getGender_name();
//				}
//				else {
//					Mst3+=","+listgender.get(j).getGender_name();
//				}
//				
//			}
//			TH.add("Gender:Drop:" + Mst3);
//		}
//		else {
//			TH.add("Gender:Drop:" + "Gender not available");
//		}
//		TH.add("Admission_Date");
//		TH.add("NEET_roll_no");
//		TH.add("NEET_application_no");
//		TH.add("NEET_rank");
//		TH.add("NEET_marks");
//		TH.add("NEET_percentile");
//		
//		String Heading = "\nCope Code";
//		String username = session.getAttribute("username").toString();
//		
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		
//		return new ModelAndView(new GenExcel_Contoller("L", TH, Heading, username), "userList", listexport);
//	}

//	@PostMapping(value = "/Exp_Excel_Nch_action")
//	public ModelAndView Exp_Excel_Nch_action(@Validated @ModelAttribute("Exp_Excel_Nch_cmd") EDU_LMS_NCH_STUDENT_DETAILS skl,
//			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//			RedirectAttributes ra, @RequestParam(value = "u_file1", required = false) MultipartFile upload,
//			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
//		
//		
//			//SECURITY ABHISHEK
//			if (request.getHeader("Referer") == null) {
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//		
//		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//		Session sessionHQL = this.sessionFactory.openSession();
//		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
//		
//		// check for list empty
//		UserLogin p = new UserLogin();
//
//		try {
//
//			int count_duplicate = 0;
//			String board_id = "";
//			int id = skl.getId() > 0 ? skl.getId() : 0;
//			String username = session.getAttribute("username").toString();
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//
//			File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
//			FileInputStream fis = new FileInputStream(file);
//			HSSFWorkbook wb = new HSSFWorkbook(fis);
//			HSSFSheet sheet = wb.getSheetAt(0);
//			Row row_head = sheet.getRow(0);
//			
//			String date_of_upload = request.getParameter("upload_date");
//			String role_type = request.getParameter("role_type");
//			if (role_type.trim().equals("0")) {
//				ra.addAttribute("msg", "Please select UG/PG.");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//			
//
//			if (!row_head.getCell(0).getStringCellValue().equals("Name")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Name");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//			
//				if (!row_head.getCell(1).getStringCellValue().equals("System")) {
//			ra.addAttribute("msg", "Please Enter Correct File Header for System");
//			return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//			
//				if (!row_head.getCell(2).getStringCellValue().equals("Degree")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Degree");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(3).getStringCellValue().equals("Profession")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Profession");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				
//				if (!row_head.getCell(4).getStringCellValue().equals("Enrollment_No")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Enrollment_No");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(5).getStringCellValue().equals("Date_of_Birth")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Date of Birth");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(6).getStringCellValue().equals("Aadhar_Card")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Aadhar Card");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(7).getStringCellValue().equals("Email")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Email");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(8).getStringCellValue().equals("Mobile_No")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Mobile No");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(9).getStringCellValue().equals("Gender")) {
//			ra.addAttribute("msg", "Please Enter Correct File Header for Gender");
//			return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				if (!row_head.getCell(10).getStringCellValue().equals("Admission_Date")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Admission_Date");
//				return new ModelAndView("redirect:exp_excel_nch_url");
//			}
//				
//				if (!row_head.getCell(11).getStringCellValue().equals("NEET_roll_no")) {
//					ra.addAttribute("msg", "Please Enter Correct File Header for NEET_roll_no");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//				
//				if (!row_head.getCell(12).getStringCellValue().equals("NEET_application_no")) {
//					ra.addAttribute("msg", "Please Enter Correct File Header for NEET_application_no");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//				
//				if (!row_head.getCell(13).getStringCellValue().equals("NEET_rank")) {
//					ra.addAttribute("msg", "Please Enter Correct File Header for NEET_rank");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//				
//				if (!row_head.getCell(14).getStringCellValue().equals("NEET_marks")) {
//					ra.addAttribute("msg", "Please Enter Correct File Header for NEET_marks");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//				
//				if (!row_head.getCell(15).getStringCellValue().equals("NEET_percentile")) {
//					ra.addAttribute("msg", "Please Enter Correct File Header for NEET_percentile");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//			
//
//			int increemt=0;
//			String abr="";
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//				
//				Transaction tx = sessionHQL.beginTransaction();
//
//				Row row = sheet.getRow(i);
//				if (row.getCell(0) == null) {
//					break;
//				}
//
//				for (int i1 = 0; i1 <= 15; i1++) {
//
//					Cell cell = row.getCell(i1);
//					System.err.println("cell===================="+cell);
//					String value = "";
//					switch (cell.getCellType()) {
//					case Cell.CELL_TYPE_STRING:
//						value = cell.getStringCellValue();
//						break;
//					case Cell.CELL_TYPE_NUMERIC:
//						if (HSSFDateUtil.isCellDateFormatted(cell)) {
//							value = String.valueOf(cell.getDateCellValue());
//						} else {
//							value = String.valueOf((long) cell.getNumericCellValue());
//						}
//						break;
//					case Cell.CELL_TYPE_BOOLEAN:
//						value = String.valueOf(cell.getBooleanCellValue());
//						break;
//					default:
//					}
//					
////					if (reg_no != null)
////					{
////						int newn = Integer.parseInt(reg_no);
////						newn++;
////						String abc = String.format("%10s", newn).replace(' ', '0');
////						 testold = depart + abc;
////						rt.setRecr_reg_no(testold);
////
////					} else {
////						testold="i0000000001";
////						rt.setRecr_reg_no(testold);
////					}
////					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Name")) {
//						skl.setName(value);
//						p.setLogin_name(value);
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Date_of_Birth")) {
//						Date date = (Date) formatter.parse(value);
//						Calendar cal = Calendar.getInstance();
//						cal.setTime(date);
//						String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
//								+ cal.get(Calendar.YEAR);
//						skl.setDob(date);
//					}
//					
//					if (row_head.getCell(i1).getStringCellValue().equals("Email")) {
//						skl.setEmail(value);
//						p.setEmail_id(value);
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
//						skl.setMobile_no(value);
//						p.setMobile_no(value);
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Gender")) {
//						EDU_LMS_GENDER_MSTR gn = common.getGender_nametoid(value).get(0);
//						String gen = String.valueOf(gn.getId());
//						skl.setGender(gen);
////						skl.setGender(value);
//					}
//					
//					if (row_head.getCell(i1).getStringCellValue().equals("Admission_Date")) {
//						Date date = (Date) formatter.parse(value);
//						Calendar cal = Calendar.getInstance();
//						cal.setTime(date);
//						String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
//								+ cal.get(Calendar.YEAR);
//						
//						skl.setAdmission_date(date);
//					}
//				
//					if (row_head.getCell(i1).getStringCellValue().equals("System")) {
//						EDU_LMS_SYSTEM_MSTR es = common.getSystem_nametoid(value).get(0);
//						String sys = String.valueOf(es.getId());
//						skl.setSystem(Integer.parseInt(sys));
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Degree")) {
//						EDU_LMS_DEGREE_MASTER ed = common.getDegree_nametoid(value).get(0);
//						String deg = String.valueOf(ed.getId());
//						skl.setDegree(Integer.parseInt(deg));
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Enrollment_No")) {
//						skl.setEnrollment_no(value);
//					}
//					
//					if (row_head.getCell(i1).getStringCellValue().equals("Aadhar_Card")) {
//						skl.setAadhar_card(value);
//						p.setUserName(value);
//						p.setAadhar_no(value);
//					}
//					
//                    if (row_head.getCell(i1).getStringCellValue().equals("Profession")) {
//						skl.setSemester(value);
//					}
//                    
//                    if (row_head.getCell(i1).getStringCellValue().equals("NEET_roll_no")) {
//						skl.setNeet_roll_no(value);
//					}
//                    
//                    if (row_head.getCell(i1).getStringCellValue().equals("NEET_application_no")) {
//						skl.setNeet_application_no(value);
//					}
//                    
//                    
//                    if (row_head.getCell(i1).getStringCellValue().equals("NEET_rank")) {
//						skl.setNeet_rank(Integer.parseInt(value));
//					}
//                    
//                    if (row_head.getCell(i1).getStringCellValue().equals("NEET_marks")) {
//						skl.setNeet_marks(Integer.parseInt(value));
//					}
//                    if (row_head.getCell(i1).getStringCellValue().equals("NEET_percentile")) {
//						skl.setNeet_percentile(value);
//					}
//				}
//				
//				String maxAID="";
//				if(i==1) {
//					maxAID = getMaxAID(userid);
//					System.err.println("maxAID--------->     "+maxAID);
//					
//					increemt=Integer.parseInt(maxAID.substring(10));
//					abr=maxAID.substring(0,10);
//				}else {
//					increemt+=1;
//					maxAID=abr+String.format("%5s", increemt).replace(' ', '0');
//				}
//					skl.setAyush_id(maxAID);
//				
//				if (!userid.equals("")) {
//                	UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//                	String university = String.valueOf(ea.getUniversity_id());
//					skl.setUniversity_userid(Integer.parseInt(university));
//					
//					String institude = String.valueOf(ea.getInstitute_id());
//					skl.setInstitude_userid(Integer.parseInt(institude));
//					p.setInstitute_id(Integer.parseInt(institude));
//					p.setUniversity_id(Integer.parseInt(university));
//					
//					String state = String.valueOf(ea.getState_id());
//					p.setState_id(Integer.parseInt(state));
//				}
////					skl.setInstitude_id(Integer.parseInt(userid));
//					skl.setUpload_date(new Date());
//					skl.setVerified_status(-1);
//					skl.setCreated_date(new Date());
//					skl.setCreated_by(username);
//					
////				Query q0 = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where email=:email ");
////				q0.setParameter("email", skl.getEmail());
////				Long c = (Long) q0.uniqueResult();
//				Query q0 = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where email=:email or aadhar_card=:aadhar_card ");
//				q0.setParameter("email", skl.getEmail()).setParameter("aadhar_card", skl.getAadhar_card());
//				Long c = (Long) q0.uniqueResult();
//
//				if(c==0) {
//					
//					int mid = (int) sessionHQL.save(skl);
//					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//					String hashedPassword = passwordEncoder.encode("Bisag@123");
//					
//					p.setPassword(hashedPassword);
//					p.setEnabled(1);
//					p.setAccountNonExpired(1);
//					p.setAccountNonLocked(1);
//					p.setCredentialsNonExpired(1);
////					p.setInstitute_id(Integer.parseInt(userid));
////					p.setCountry_id(Integer.parseInt("5"));
////					p.setState_id(Integer.parseInt("12"));
//					
//					p.setCreated_on(new Date());
//					p.setCreated_by(username);
//					UserRole role_tbl = new UserRole();
//
//					int did = (Integer) sessionHQL.save(p);
//					role_tbl.setRoleId(Integer.parseInt(role_type));
//					role_tbl.setUserId(did);
//					sessionHQL.save(role_tbl);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					
////					tushar
//					ra.addAttribute("msg", "Data Saved Successfully");
//					
//				}else
//				{
//					ra.addAttribute("msg", "Email id Already Exist");
//					return new ModelAndView("redirect:exp_excel_nch_url");
//				}
//
//				// for mail send
////				SendRegMail(p.getEmail_id());
//				emailsend.SendMail(request,p.getEmail_id(),roleSubAccess,"MOA Portal Account Created","Your Account has been created Successfully.Now you can login with your Adharcard Number on AYUSH Portal.","","","");
//				
//				tx.commit();
//			}
//			
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			try {
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				e.printStackTrace();
//				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return new ModelAndView("redirect:exp_excel_nch_url");
//	}

////////////////////////-------------------Exp Exc NCH Data Table----------------///////////////////////////////////////////////
	
	@GetMapping(value = "/NchStudentDetailsReport_Url")
	public ModelAndView NchStudentDetailsReport_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,RedirectAttributes ra) {
		
		try {
			
			//SECURITY ABHISHEK
//			if (request.getHeader("Referer") == null) {
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		model.put("msg", msg);
		Date date = new Date();
		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
		model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
		model.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
		model.put("date", date1);

//		----------------------------------------------------------------------------------------------
		String role = session.getAttribute("roleid").toString();
		
		String rolename = session.getAttribute("role").toString();
		
//		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.err.println("userid========================="+userid);
//		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		model.put("msg", msg);
//		Date date = new Date();
		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
		model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
//		model.put("list", edao.E_Form_Student_DtlData(role,institute_id));
//		String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
//		model.put("date", date1);
//		model.put("id", institute_id);

		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getcategorylist", common.getcategoryList(sessionFactory));
		model.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
		model.put("getQuotaList", common.getQuotaList(sessionFactory));
		model.put("getInsCodeListbyInsName", common.getInsCodeListbyInsName(sessionFactory,institute_id));
		
//		model.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
		
//		 String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
//			if (Staff_lvl.toUpperCase().equals("NCH")) {
			model.put("getUniverCityList", cd.getUniversityNchlist());
			model.put("getapp_instituteNameList", cd.getinstituteNchlist());
//			}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
//				model.put("getUniverCityList", cd.getUniversityNcismlist());
//				model.put("getapp_instituteNameList", cd.getinstituteNcismlist());
//			}
			
			model.put("getSystemForAll", common.getSystemForAll(sessionFactory));
			if (role.equals("19")) {//uni nch
				String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
				model.put("uni_id",uni_id);
			}
			
			if (role.equals("21")) {//inst nch
				String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
				model.put("uni_id",uni_id);
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id------>   "+inst_id);
				model.put("inst_id",inst_id);
			}
			
			if (role.equals("65") || role.equals("17")) {// HOM_BOARD or NCH
				Role roleList = userLoginDAO.findRole_url(rolename);
				model.put("system_id",String.valueOf(roleList.getSystem_id()));
			}

	} catch (RuntimeException e) {
		try {

			ra.addAttribute("msg", "roll back transaction");
		} catch (RuntimeException rbe) {
			ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
		}
		
		throw e;
		
	} 
		return new ModelAndView("NchStudentDetailsReport_Tiles");
	}
	
	@PostMapping("/getFilterStudent_data")
	
	public @ResponseBody ArrayList<ArrayList<String>> getFilterStudent_data(ModelMap model, Principal principal,HttpSession session,
			int startPage, int pageLength,String Search, String orderColunm, String orderType,
			String institute_id, String institute_code,String state_id,
			String name,String last_name,String mother_name,String father_name,
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority, String category_id,
			String system,String university_id,String type_of_degree) {
		
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String role = session.getAttribute("role").toString();
//				String username = principal.getName();
	
	return sdao.DataTableStudentDataList(startPage, pageLength, Search, orderColunm, orderType,userid,
			institute_id,  institute_code, state_id,
			name,last_name, mother_name,father_name,dob,email,
			neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks, quota_id,
			 counselling_authority,  category_id,role,system,university_id,type_of_degree);
	}

	@PostMapping("/getTotalStudent_dataCount")
	public @ResponseBody long getTotalStudent_dataCount(ModelMap model, Principal principal,
			HttpSession sessionUserId, String Search,String institute_id, String institute_code,String state_id,
			String name,String last_name,String mother_name,String father_name,
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority, String category_id,
			String system,String university_id,String type_of_degree) {
	
	//	System.err.println("----------------->"+upload_date);
	String username = principal.getName();
	String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
	String role = sessionUserId.getAttribute("role").toString();
	
	return  sdao.DataTableStudentDataTotalCount(Search, userid,
			institute_id,institute_code,state_id,
			name,last_name, mother_name,father_name,
			 dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
			 quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree);
	}
	
	public String fileupload(byte[] b, String name, int id, String type) {
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "TEMPEXCEL");
			// File dir = new File("/srv/BRO_REC_Documents/"+id);
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			fname = dir.getAbsolutePath() + File.separator + "tempinterview" + "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}

	// FOR EMAIL
//	public void SendRegMail(String email) throws ParseException {
////		String code = randomString(5);
//		MailHTML html = new MailHTML();
//		try {
//			MimeMessage mimeMessage = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				String text = "Your Account has been created by your institute,Now you can login with your Adharcard Number in AYUSH Portal.";
//				String note = "";
//				html.setHTML(text, note);
//				String htmlMsg = html.getHTML();
//				helper.setText(htmlMsg, true);
//				helper.setTo(email);
//				helper.setSubject("MOA Portal Account Created");
//				/* helper.setFrom("abc@gmail.com"); */
//				mailSender.send(mimeMessage);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//		}
//	}
	
	@Autowired
	private DataSource dataSource;	
	
	public String getMaxAID(String userid) {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "select to_char(CURRENT_TIMESTAMP,'yy')||code||'HOM'||lpad((select case when (select max(Substring(ayush_id,11,5))\n"
					+ "					from edu_lms_nch_student_details where length(ayush_id)>14)='' or (select max(Substring(ayush_id,11,5))\n"
					+ "					from edu_lms_nch_student_details where length(ayush_id)>14) is null  then '0' else (select max(Substring(ayush_id,11,5))\n"
					+ "					from edu_lms_nch_student_details where length(ayush_id)>14) end::int+1)::text, 5, '0')as max from logininformation l\n"
					+ "					inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?\n"
					+ "					inner join edu_lms_state_mstr st on st.state_id=l.state_id";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, Integer.parseInt(userid));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("max");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reg_no;
	}
	
	
	
	@RequestMapping(value = "/Exp_Excel_Nch", method = RequestMethod.POST)
	public ModelAndView Exp_Excel_Nch(HttpServletRequest request, ModelMap model, HttpSession session, String typeReport1) {
		
		List<String> TH = new ArrayList<String>();
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		String InstMst="";
		String StateMst="";
		String QuotaMst="";
		String CAMst="";
		String CatMst="";
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		
		try {
		
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		
		
		
		ArrayList<ArrayList<String>> listInst = new ArrayList<ArrayList<String>>();
		
		String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
		if (Staff_lvl.toUpperCase().equals("NCH")) {
			listInst = SSRnchDao.getinstitutelist(userid);
		}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
			listInst = SSRncismDao.getinstitute_Ncismlist(userid);
		}
		
		if(!listInst.isEmpty()) {
			
			for(int i=0; i<listInst.size(); i++ ) {
				if(i==listInst.size()) {
					InstMst+=listInst.get(i).get(1);
				}
				else if (i==0) {
					InstMst+=listInst.get(i).get(1);
				}
				else {
					InstMst+=","+listInst.get(i).get(1);
				}
				
			}
			TH.add("Institute Name:Drop:" + InstMst);
		}
		else {
			TH.add("Institute Name:Drop:" + "Institute Name not available");
		}

		TH.add("Institute ID");
		
		List<TB_STATE> liststate = common.getMedStateName2(sessionFactory);
//		System.err.println("\n\n----------"+String.valueOf(liststate.get(0).getState_name())+"\n\n");
		if(!liststate.isEmpty()) {
			
			for(int i=0; i<liststate.size(); i++ ) {
				if(i==liststate.size()) {
					StateMst+=liststate.get(i).getState_name();
				}
				else if (i==0) {
					StateMst+=liststate.get(i).getState_name();
				}
				else {
					StateMst+=","+liststate.get(i).getState_name();
				}
				
			}
			TH.add("State:Drop:" + StateMst);
		}
		else {
			TH.add("State:Drop:" + "State not available");
		}
		
		TH.add("Candidate's Name");
		TH.add("Last Name");
		TH.add("Mother's Name");
		TH.add("Father's Name");
		TH.add("Email ID");
		TH.add("DOB");
		TH.add("NEET Application No");
		TH.add("NEET Roll No");
		TH.add("ALL INDIA NEET Rank");
		TH.add("NEET Percentile");
		TH.add("Marks Obtained");
		
		List<EDU_LMS_QUOTA_MSTR> ListQuota = common.getQuotaList(sessionFactory);
		
		if(!ListQuota.isEmpty()) {
			
			for(int i=0; i<ListQuota.size(); i++ ) {
				if(i==ListQuota.size()) {
					QuotaMst+=ListQuota.get(i).getQuota();
				}
				else if (i==0) {
					QuotaMst+=ListQuota.get(i).getQuota();
				}
				else {
					QuotaMst+=","+ListQuota.get(i).getQuota();
				}
				
			}
			TH.add("Quota:Drop:" + QuotaMst);
		}
		else {
			TH.add("Quota:Drop:" + "Quota not available");
		}
		
		List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> ListcouncAuth = common.getCounselingAuthoList(sessionFactory);
		
		if(!ListcouncAuth.isEmpty()) {
			
			for(int i=0; i<ListcouncAuth.size(); i++ ) {
				if(i==ListcouncAuth.size()) {
					CAMst+=ListcouncAuth.get(i).getCounseling_authority();
				}
				else if (i==0) {
					CAMst+=ListcouncAuth.get(i).getCounseling_authority();
				}
				else {
					CAMst+=","+ListcouncAuth.get(i).getCounseling_authority();
				}
				
			}
			TH.add("Counseling Authority:Drop:" + CAMst);
		}
		else {
			TH.add("Counseling Authority:Drop:" + "Counseling Authority not available");
		}
		
		List<EDU_LMS_CATEGORY_MSTR> Listcategory = common.getcategoryList(sessionFactory);
		
		if(!Listcategory.isEmpty()) {
			
			for(int i=0; i<Listcategory.size(); i++ ) {
				if(i==Listcategory.size()) {
					CatMst+=Listcategory.get(i).getCategory();
				}
				else if (i==0) {
					CatMst+=Listcategory.get(i).getCategory();
				}
				else {
					CatMst+=","+Listcategory.get(i).getCategory();
				}
				
			}
			TH.add("Category:Drop:" + CatMst);
		}
		else {
			TH.add("Category:Drop:" + "Category not available");
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return new ModelAndView(new E_Form_Gen_Excel("L", TH, Heading, username,InstMst,StateMst,QuotaMst,CAMst,CatMst), "userList", listexport);
	}
	
	@PostMapping(value = "/Exp_Excel_Nch_action")
	public ModelAndView Exp_Excel_Nch_action(@Validated @ModelAttribute("Exp_Excel_Nch_cmd") EDU_LMS_NCH_STUDENT_DETAILS skl,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, @RequestParam(value = "u_file1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("exp_excel_nch_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
	
//	System.err.println("\n\n********** HELLO ***********\n\n");
	
	DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	Session sessionHQL = this.sessionFactory.openSession();
	String roleSubAccess = session.getAttribute("roleSubAccess").toString();		

	UserLogin p = new UserLogin();

	try {
		
		String date_of_upload=request.getParameter("upload_date");
		
		String role_type = request.getParameter("role_type");
		
		String degree = request.getParameter("degree"); 
		
		if (role_type.trim().equals("0")) {
			ra.addAttribute("msg", "Please select UG/PG.");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		
		if (upload.isEmpty()) {
			ra.addAttribute("msg", "Please Upload File.");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		
		if (!upload.isEmpty()) {
			 if (upload.getOriginalFilename().split("[.]").length > 2) {
				 ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:exp_excel_nch_url");
			}
		}
		

		if (date_of_upload == null || date_of_upload.trim().equals("") || date_of_upload.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date.");
			return new ModelAndView("redirect:exp_excel_nch_url");
			
		}
		
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();

		File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
		
		String extention="";
		int z = upload.getOriginalFilename().lastIndexOf('.');
		if (z >= 0) {
			extention = upload.getOriginalFilename().substring(z + 1);
		}
		
		if(!extention.equals("xls")) {
			ra.addAttribute("msg", "Please Select Excel File");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);
		Row row_head = sheet.getRow(0);
		
		if (!row_head.getCell(0).getStringCellValue().equals("Institute Name")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Institute Name");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(1).getStringCellValue().equals("Institute ID")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Institute ID");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(2).getStringCellValue().equals("State")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for State");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(3).getStringCellValue().equals("Candidate's Name")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Candidate's Name");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(4).getStringCellValue().equals("Last Name")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Last Name");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(5).getStringCellValue().equals("Mother's Name")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Mother's Name");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(6).getStringCellValue().equals("Father's Name")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Father's Name");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(7).getStringCellValue().equals("Email ID")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Email ID");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(8).getStringCellValue().equals("DOB")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for DOB");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(9).getStringCellValue().equals("NEET Application No")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for NEET Application No");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(10).getStringCellValue().equals("NEET Roll No")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for NEET Roll No");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(11).getStringCellValue().equals("ALL INDIA NEET Rank")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for ALL INDIA NEET Rank");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(12).getStringCellValue().equals("NEET Percentile")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for NEET Percentile");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(13).getStringCellValue().equals("Marks Obtained")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Marks Obtained");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(14).getStringCellValue().equals("Quota")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Quota");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(15).getStringCellValue().equals("Counseling Authority")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Counseling Authority");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}
		if (!row_head.getCell(16).getStringCellValue().equals("Category")) {
			ra.addAttribute("msg", "Please Enter Correct File Header for Category");
			return new ModelAndView("redirect:exp_excel_nch_url");
		}

		int increemt=0;
		String abr="";

		Transaction tx = sessionHQL.beginTransaction();
	
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			Row row = sheet.getRow(i);
			if (row.getCell(0) == null) {
				ra.addAttribute("msg", "Please Enter Institute Name in row 1");
				return new ModelAndView("redirect:exp_excel_nch_url");
			}
			
			
			String inid = "";
			String inname = "";
			String stateid = "";
			//04_02_2023 added last name in excel export and save
			for (int i1 = 0; i1 <= 16; i1++) {
				
				Cell cell = row.getCell(i1);
				
				String varforval = "";
				if(i1==0) {
					varforval = "Institute Name";
				}
				if(i1==1) {
					varforval = "Institute ID";
				}
				if(i1==2) {
					varforval = "State";
				}
				if(i1==3) {
					varforval = "Candidate's Name";
				}
				if(i1==4) {
					varforval = "Last Name";
				}
				if(i1==5) {
					varforval = "Mother's Name";
				}
				if(i1==6) {
					varforval = "Father's Name";
				}
				if(i1==7) {
					varforval = "Email ID";
				}
				if(i1==8) {
					varforval = "DOB";
				}
				if(i1==9) {
					varforval = "NEET Application No";
				}
				if(i1==10) {
					varforval = "NEET Roll No";
				}
				if(i1==11) {
					varforval = "ALL INDIA NEET Rank";
				}
				if(i1==12) {
					varforval = "NEET Percentile";
//					CellStyle style = wb.createCellStyle();
//				    style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
//				    cell.setCellStyle(style);
				}
				if(i1==13) {
					varforval = "Marks Obtained";
				}
				if(i1==14) {
					varforval = "Quota";
				}
				if(i1==15) {
					varforval = "Counseling Authority";
				}
				if(i1==16) {
					varforval = "Category";
				}
			
				
				if(cell == null) {
					ra.addAttribute("msg", "Please Enter "+varforval+" in row "+i);
					return new ModelAndView("redirect:exp_excel_nch_url");
				}
				
				DataFormatter fmt = new DataFormatter();
				
				String value = "";
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						value = String.valueOf(cell.getDateCellValue());
					} else {
						value = String.valueOf((long) cell.getNumericCellValue());
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				default:
				}
				
//				System.err.println("\n\n"+cell.getCellType()+"---\n\n"); 
				
				if (row_head.getCell(i1).getStringCellValue().equals("Institute Name")) {
					inname = value;
					EDU_LMS_INSTITUTE_REGISTRATION ir = common.getInstid_fromname(inname).get(0);
					inid = String.valueOf(ir.getId());
					skl.setInstitude_userid(Integer.parseInt(inid));
					p.setInstitute_id(Integer.parseInt(inid));
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Institute ID")) {
					skl.setInst_code(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("State")) {
					TB_STATE sd = common.getstateid_fromname(value).get(0);
					String sid = String.valueOf(sd.getState_id());
					stateid = sid;
					skl.setState(Integer.parseInt(sid));
					p.setState_id(Integer.parseInt(sid));
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Candidate's Name")) {
					skl.setName(value);
					p.setLogin_name(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Las Name")) {
					skl.setLast_name(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Mother's Name")) {
					skl.setMother_name(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Father's Name")) {
					skl.setFather_name(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Email ID")) {
					skl.setEmail(value);
					p.setEmail_id(value);
					p.setUserName(value);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("DOB")) {
					Date date = (Date) formatter.parse(value);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					skl.setDob(date);
				}
				if (row_head.getCell(i1).getStringCellValue().equals("NEET Application No")) {
					if (value.length() != 12) {
						ra.addAttribute("msg", "Please Enter 12 Digit NEET Application No. in Row - "+i);
						return new ModelAndView("redirect:exp_excel_nch_url");
					}else {
						skl.setNeet_application_no(value);
					}
				}
				if (row_head.getCell(i1).getStringCellValue().equals("NEET Roll No")) {
					if (value.length() != 10) {
						ra.addAttribute("msg", "Please Enter 10 Digit NEET Roll No. in Row - "+i);
						return new ModelAndView("redirect:exp_excel_nch_url");
					}else {
						skl.setNeet_roll_no(value);
					}
				}
				if (row_head.getCell(i1).getStringCellValue().equals("ALL INDIA NEET Rank")) {
					if (value.length() > 7) {
						ra.addAttribute("msg", "Please Enter Maximum 7 Digit for ALL INDIA NEET Rank in Row - "+i);
						return new ModelAndView("redirect:exp_excel_nch_url");
					}else {
						skl.setNeet_rank(Integer.parseInt(value));
					}
				}
				
				if (row_head.getCell(i1).getStringCellValue().equals("NEET Percentile")) {
					
					String valueAsSeenInExcel = fmt.formatCellValue(cell).toString();
					
					if(valueAsSeenInExcel.length() > 5) {
						ra.addAttribute("msg", "Please Enter 2 digit and 2 decimal for NEET Percentile in Row - "+i);
						return new ModelAndView("redirect:exp_excel_url");
					}else if(Double.parseDouble(valueAsSeenInExcel) > 100){
						ra.addAttribute("msg", "Please Enter value less than or equal to 100 for NEET Percentile in Row - "+i);
						return new ModelAndView("redirect:exp_excel_url");
					}else {
						skl.setNeet_percentile(valueAsSeenInExcel);
					}
					
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Marks Obtained")) {
					if(value.length() > 3) {
						ra.addAttribute("msg", "Please Enter Maximum 3 digits for Marks Obtained in Row - "+i);
						return new ModelAndView("redirect:exp_excel_nch_url");
					}else if(Integer.parseInt(value) > 720) {
						ra.addAttribute("msg", "Please Enter Marks Obtained less than or equal to 720 in Row - "+i);
						return new ModelAndView("redirect:exp_excel_nch_url");
					}else {
						skl.setNeet_marks(Integer.parseInt(value));
					}
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Quota")) {
					List<EDU_LMS_QUOTA_MSTR> qu = common.getQuotaFromId(value);
					skl.setQuota(qu.get(0).getId());
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Counseling Authority")) {
					List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> ca = common.getCoAuIDfromName(value);
					skl.setCounc_auth(ca.get(0).getId());
				}
				if (row_head.getCell(i1).getStringCellValue().equals("Category")) {
					List<EDU_LMS_CATEGORY_MSTR> cat = common.getcatIDfromName(value);
					skl.setCategory(cat.get(0).getId());
				}
					skl.setSemester("1");
                
			}
			String maxAID="";
			if(i==1){
				maxAID = getMaxAID(userid);
				increemt=Integer.parseInt(maxAID.substring(11));
				abr=maxAID.substring(0,11);
			}else{
				increemt+=1;
				maxAID=abr;
				maxAID+=String.format("%5s", increemt).replace(' ', '0');
			}
				skl.setAyush_id(maxAID);

				EDU_LMS_INSTITUTE_REGISTRATION ir = common.getInstid_fromname(inname).get(0);
				
				skl.setDegree(Integer.parseInt(degree));
				skl.setSystem(ir.getSystem_id());
				
				String university = String.valueOf(ir.getUniversity_id());
            	
				skl.setUniversity_userid(Integer.parseInt(university));
				
//				skl.setInstitude_userid(Integer.parseInt(inid));
				p.setInstitute_id(Integer.parseInt(inid));
				p.setUniversity_id(Integer.parseInt(university));
				
				p.setState_id(Integer.parseInt(stateid));
			
				skl.setUpload_date(new Date());
				skl.setVerified_status(-1);
				
				sessionHQL.save(skl); //Student dtl table save
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode("Bisag@123");
				
				p.setPassword(hashedPassword);
				p.setEnabled(1);
				p.setAccountNonExpired(1);
				p.setAccountNonLocked(1);
				p.setCredentialsNonExpired(1);
				p.setCreated_on(new Date());
				p.setCreated_by(username);

				int did = (Integer) sessionHQL.save(p); //login table save
				
				UserRole role_tbl = new UserRole();
				
				role_tbl.setRoleId(Integer.parseInt(role_type));
				role_tbl.setUserId(did);
				
				sessionHQL.save(role_tbl);//role table save
			
				sessionHQL.flush();
				sessionHQL.clear();
			
//			 FOR MAIL SEND
			emailsend.SendMail(request,p.getEmail_id(),roleSubAccess,"MOA Portal Account Created","Your Account has been created Successfully.Now you can login with your Email on AYUSH Portal.","","","");
		          	
		}
		
		tx.commit();
		ra.addAttribute("msg", "Data Save Successfully");
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:exp_excel_nch_url");
	}
	
	 @RequestMapping(value = "/get_inst_by_uni_nch_excel_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody List<UserLogin> get_inst_by_uni_nch_excel_ctrl(String university_id) {
	    	List<UserLogin> data = common.getinstitute_listbyuniversity_exam(sessionFactory,university_id);
	    	return data;
	 	}
	 
	 
	 @RequestMapping(value = "/getstudentdetails_NCH_Report_Excel", method = RequestMethod.POST)
		public ModelAndView getstudentdetails_NCH_Report_Excel(HttpSession session,HttpSession sessionUserId, HttpServletRequest request,ModelMap model, String Search,RedirectAttributes ra)
				throws ParseException {
//			SECURITY -- RIDDHI 
//			 if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("NchStudentDetailsReport_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			String role = session.getAttribute("role").toString();
//			System.err.println("roleid================" + role.split("_")[1].toString());
//			String userid2 = sessionUserId.getAttribute("userId_for_jnlp").toString();
//			String name_of_institute = request.getParameter("name_of_institute1");
			String institute_id = request.getParameter("institute_id1");
			String institute_code = request.getParameter("institute_code1");
			String state_id = request.getParameter("state_id1");
			String name = request.getParameter("name1");
			String last_name = request.getParameter("last_name1");
			String mother_name = request.getParameter("mother_name1");
			String father_name = request.getParameter("father_name1");
			String dob = request.getParameter("dob1");
			String email = request.getParameter("email1");
			String system = request.getParameter("system1");
//			String degree = request.getParameter("degree1");
			String neet_application_no = request.getParameter("neet_application_no1");
			String neet_roll_no = request.getParameter("neet_roll_no1");
			String neet_rank = request.getParameter("neet_rank1");
			String neet_percentile = request.getParameter("neet_percentile1");
			String neet_marks = request.getParameter("neet_marks1");
			String quota_id = request.getParameter("quota_id1");
			String counselling_authority = request.getParameter("counselling_authority1");
			String category_id = request.getParameter("category_id1");
			String university_id = request.getParameter("university_id1");
			String type_of_degree = request.getParameter("type_of_degree1");
			String pg_degree = request.getParameter("pg_degree1");
			String pg_subject = request.getParameter("pg_subject1");
			String pg_intake = request.getParameter("pg_intake1");
			
			
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String role = session.getAttribute("role").toString();
			
			List<ArrayList<String>> listofdata = sddao.getstudentdetails_Report_Excel(Search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name,
					 dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					 quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
			
			int total = listofdata.size();

			if(listofdata.size() == 0) {
				ra.addAttribute("msg","Data Not Available");
				return new ModelAndView("redirect:NchStudentDetailsReport_Url");
			}

//			ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//				, role, "");

			ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

			List<String> TH = new ArrayList<String>();

			TH.add("ser No.");
			TH.add("Name of Institute");
			TH.add("Institute Id");
			TH.add("State");
			TH.add("First Name");
			TH.add("Last Name");
			TH.add("Mother's Name");
			TH.add("Father's Name");
			TH.add("Date Of Birth");
			TH.add("Email Id");
			TH.add("NEET Application No");
			TH.add("NEET Roll No");
			TH.add("ALL INDIA NEET Rank");
			TH.add("NEET Percentile");
			TH.add("Marks Obtained");
			TH.add("Quota");
			TH.add("Counselling Authority");
			TH.add("Category");
			
			String Heading = "\nCope Code";
			
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Studentdetails_Report_Excell_Report("L", TH, listofdata, Heading, username), "userList",
					listexport);
		}
	
	 @RequestMapping(value = "/Admission_Student_Details_NCH_PDF", method = RequestMethod.POST)
		public ModelAndView Admission_Student_Details_NCH_PDF(String Search,HttpSession session,HttpSession sessionUserId, HttpServletRequest request,ModelMap Mmap,RedirectAttributes ra)
				throws ParseException {
			
				
			
			//SECURITY ABHISHEK
//			if(request.getHeader("Referer") == null ) { 
//				
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("StudentDetailsReport_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				 String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				 String role = sessionUserId.getAttribute("role").toString();
				String institute_id = request.getParameter("institute_id2");
				String institute_code = request.getParameter("institute_code2");
				String state_id = request.getParameter("state_id2");
				String name = request.getParameter("name2");
				String last_name = request.getParameter("last_name2");
				String mother_name = request.getParameter("mother_name2");
				String father_name = request.getParameter("father_name2");
				String dob = request.getParameter("dob2");
				String email = request.getParameter("email2");
				String system = request.getParameter("system2");
//				String degree = request.getParameter("degree2");
				String neet_application_no = request.getParameter("neet_application_no2");
				String neet_roll_no = request.getParameter("neet_roll_no2");
				String neet_rank = request.getParameter("neet_rank2");
				String neet_percentile = request.getParameter("neet_percentile2");
				String neet_marks = request.getParameter("neet_marks2");
				String quota_id = request.getParameter("quota_id2");
				String counselling_authority = request.getParameter("counselling_authority2");
				String category_id = request.getParameter("category_id2");
				String university_id = request.getParameter("university_id2");
				String type_of_degree = request.getParameter("type_of_degree2");
				String pg_degree = request.getParameter("pg_degree2");
				String pg_subject = request.getParameter("pg_subject2");
				String pg_intake = request.getParameter("pg_intake2");
				
				List<ArrayList<String>> nonlecact1 = sddao.getstudentdetails_Report_Excel(Search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name,
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
				System.err.println("nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1nonlecact1"+nonlecact1);

					int total = nonlecact1.size();
					List<String> TH1 = new ArrayList<String>();

			TH1.add("Ser No.");
			TH1.add("Name of Institute");
			TH1.add("Institute Id");
			TH1.add("State");
			TH1.add("First Name");
			TH1.add("Last Name");
			TH1.add("Mother's Name");
			TH1.add("Father's Name");
			TH1.add("Date of Birth");
			TH1.add("Eamil Id");
			TH1.add("NEEt Application No.");
			TH1.add("NEEt Roll No.");
			TH1.add("All India NEEt Rank");
			TH1.add("NEEt Percentile");
			TH1.add("Marks Obtained");
			TH1.add("Quota");
			TH1.add("Counselling Authority");
			TH1.add("Category");
	        
			
			if(nonlecact1.size() == 0) {
				ra.addAttribute("msg","Data Not Available");
				return new ModelAndView("redirect:NchStudentDetailsReport_Url");
			}
			
	      String Heading = "\nCope Code";
			
			String username1 = session.getAttribute("username").toString();
			
			return new ModelAndView(new Admission_Student_Report_PDF("L", TH1, Heading, total, username1), "userList", nonlecact1);
			
		}
		
	
	
}
