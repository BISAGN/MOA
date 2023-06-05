package com.AyushEdu.controller.Regulation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
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
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;
import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
import com.AyushEdu.controller.Exp_Excel.Gen_Prac_Excel_Contoller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Regulation.Excel_upload_FormA_practitioner_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Excel_upload_formA_practitioner {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation;
	@Autowired
	Excel_upload_FormA_practitioner_DAO PRFAdao;
	@Autowired
	private Commondao cd;
	
	@RequestMapping(value = "/Excel_upload_formA_practitioner", method = RequestMethod.GET)
	public ModelAndView Excel_upload_formA_practitioner(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("checking userid"+userid);
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			System.err.println("institute idddddd"+institute_id);
			model.put("msg", msg);
			Date date = new Date();
//			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
//			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Excel_upload_practitioner_FormA_tiles");
	}
	
	@PostMapping("/getFilter_Exp_Excel_formaA_practitioner_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Exp_Excel_formaA_practitioner_data(int startPage, int pageLength,
	String Search,String orderColunm,String orderType,String upload_date) throws ParseException {
		return PRFAdao.DataTableEdu_Exp_excel_forma_practitioner(startPage, pageLength, Search, orderColunm, orderType,upload_date);
	}

	@PostMapping("/getTotalEdu_Exp_ExcelformaA_practitioner_dataCount")

	public @ResponseBody long getTotalEdu_Exp_ExcelformaA_practitioner_dataCount(HttpSession sessionUserId,String Search,String upload_date ) throws ParseException {
		return PRFAdao.DataTableEdu_Exp_excel_forma_practitioner_Count(Search,upload_date);
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/Exp_Excel_practitioner_formA_action")
	public ModelAndView Exp_Excel_practitioner_formA_action(@Validated @ModelAttribute("Exp_Excel_practitioner_formA_cmd") REG_NCH_FORM_A_P pformA,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, @RequestParam(value = "u_file1", required = false) MultipartFile upload, 
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Session sessionHQL = this.sessionFactory.openSession();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		// check for list empty
	
		try {

			int count_duplicate = 0;
			String board_id = "";
			int id = pformA.getId() > 0 ? pformA.getId() : 0;
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			
			if (upload.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg", "Please Upload File");
				return new ModelAndView("redirect:exp_excel_practitioner_url");
			}
			if (!upload.getOriginalFilename().isEmpty()) {

				if (upload.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:exp_excel_practitioner_url");
				}

			}
			File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
			FileInputStream fis = new FileInputStream(file);
//			HSSFWorkbook wb = new HSSFWorkbook(fis);
//			HSSFSheet sheet = wb.getSheetAt(0);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row_head = sheet.getRow(0);
			
			String date_of_upload=request.getParameter("upload_date");
			String role_type=request.getParameter("role_type");
			
//			if (!row_head.getCell(0).getStringCellValue().equals("Ayush_id")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Ayush_id");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
 
			int increemt=0;
			String abr="";
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				Transaction tx = sessionHQL.beginTransaction();
				REG_NCH_MED_DEGREE_DTL_A_CH pmUG = new REG_NCH_MED_DEGREE_DTL_A_CH();
				REG_NCH_MED_DEGREE_DTL_A_CH pmPG = new REG_NCH_MED_DEGREE_DTL_A_CH();

				Row row = sheet.getRow(i);
				if (row.getCell(0) == null) {
					break;
				}
//				String maxAID="";
//				if(i==1) {
//					maxAID = getMaxAID(userid);
//					increemt=Integer.parseInt(maxAID.substring(12));
//		
//					abr=maxAID.substring(0,10);
//			
//				}else {
//
//					maxAID=abr+String.format("%5s", increemt).replace(' ', '0');
//					
//				}
				int i1_length=24;
//				if(role_type.equals("25")) {
//					i1_length=10;
//				}
				for (int i1 = 0; i1 <= i1_length; i1++) {

					Cell cell = row.getCell(i1);
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
					
//						skl.setAyush_id(maxAID);
						
					if (row_head.getCell(i1).getStringCellValue().equals("Name")) {
						pformA.setFirst_name(value.toUpperCase());
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Father Name")) {
						pformA.setFather_name(value.toUpperCase());
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Pre State")) {
						Session sessiondd = sessionFactory.openSession();
						Query q1 = sessiondd.createQuery(
								"  from TB_STATE  WHERE state_name=:state_name ");
						q1.setParameter("state_name", value);
						List<TB_STATE> state = q1.list();
						sessiondd.close(); 
						pformA.setPre_state(state.get(0).getState_id());
//						pformA.setPre_state(Integer.parseInt(value));
					}		 				
					if (row_head.getCell(i1).getStringCellValue().equals("Pre District")) {
						Session sessiondd = sessionFactory.openSession();
						Query q1 = sessiondd.createQuery(
								"from EDU_LMS_DISTRICT_MSTR  WHERE district_name=:district_name ");
						q1.setParameter("district_name", value);
						List<EDU_LMS_DISTRICT_MSTR> district = q1.list();
						sessiondd.close(); 
						pformA.setPre_district(district.get(0).getDistrict_id());
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Pre Pincode")) {
						pformA.setPre_pincode(new BigInteger(value));
					}
					 
					if (row_head.getCell(i1).getStringCellValue().equals("Per state")) {
						Session sessiondd = sessionFactory.openSession();
						System.err.println("per state"+value);

						Query q1 = sessiondd.createQuery(
								"from TB_STATE  WHERE state_name=:state_name ");
						q1.setParameter("state_name", value);
						List<TB_STATE> statepre = q1.list();
						sessiondd.close(); 
						pformA.setPer_state(statepre.get(0).getState_id());
						
//						pformA.setPer_state(Integer.parseInt(value));
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Per District")) {
						Session sessiondd = sessionFactory.openSession();
						System.err.println("check district"+value);
						Query q1 = sessiondd.createQuery(
								"from EDU_LMS_DISTRICT_MSTR  WHERE district_name=:district_name ");
						q1.setParameter("district_name", value);
						System.err.println("check district query"+q1);
						List<EDU_LMS_DISTRICT_MSTR> perdistrict = q1.list();
						sessiondd.close(); 
						pformA.setPer_district(perdistrict.get(0).getDistrict_id());
//						pformA.setPer_district(Integer.parseInt(value));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Per Pincode")) {
						System.err.println("chech permenat pincode"+value);
						pformA.setPer_pincode(new BigInteger(value));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Email Id")) {
						pformA.setEmail_id(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Mobile No")) {
						pformA.setMo_no(new BigInteger(value));
					}
					
					
					if (row_head.getCell(i1).getStringCellValue().equals("Date Of Birth")) {
						Date date = (Date) formatter.parse(value);
						System.err.println("check the date"+date);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = (String.format("%2s", cal.get(Calendar.DATE)) + "/" + String.format("%2s", (cal.get(Calendar.MONTH) + 1)) + "/"
								+ String.format("%4s", cal.get(Calendar.YEAR))).replace(" ", "0");

						pformA.setDob(date);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Nationality")) {
						System.err.println("natinalityy"+value);
						Session sessiondd = sessionFactory.openSession();
						Query q1 = sessiondd.createQuery(
								"from recr_nationality_mst  WHERE nationality=:nationality ");
						q1.setParameter("nationality", value);
						List<recr_nationality_mst> nationality = q1.list();
						sessiondd.close(); 
						pformA.setNationality(nationality.get(0).getNationality_id());
//						pformA.setNationality(Integer.parseInt(value));
					}
//					
//					 
//					if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
//						skl.setMobile_no(value);
//						p.setMobile_no(value);
//					}
//					if (row_head.getCell(i1).getStringCellValue().equals("Gender")) {
//						skl.setGender(value);
//					}
//					
//					if (row_head.getCell(i1).getStringCellValue().equals("Aadhar_Card")) {
//						skl.setAadhar_card(value);
//						p.setAadhar_no(value);
//						p.setUserName(value);
//					}
					
					 
					
///////////////////31_05						
					 
					if (row_head.getCell(i1).getStringCellValue().equals("Degree/Under Graduate")) {
						EDU_LMS_DEGREE_MASTER ed = common.getDegree_nametoid(value).get(0);
						String deg = String.valueOf(ed.getId());
						pmUG.setDegree_name(Integer.parseInt(deg));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("University Of Degree")) {
						pmUG.setName_of_institute(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Post Graduate Degree")) {
						EDU_LMS_DEGREE_MASTER ed = common.getDegree_nametoid(value).get(0);
						String deg = String.valueOf(ed.getId());
						pmPG.setDegree_name(Integer.parseInt(deg));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("University Of Post Grad")) {
						pmPG.setName_of_institute(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("State Reg No")) {
						System.err.println("state registration number"+value);
						pformA.setState_reg_no(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Reg Date")) {
						Date date = (Date) formatter.parse(value);

						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = (String.format("%2s", cal.get(Calendar.DATE)) + "/" + String.format("%2s", (cal.get(Calendar.MONTH) + 1)) + "/"
								+ String.format("%4s", cal.get(Calendar.YEAR))).replace(" ", "0");
						System.err.println("check date"+date);
						pformA.setDate_of_reg(date);
					}
					
					
					if (row_head.getCell(i1).getStringCellValue().equals("Reg State")) {
						Session sessiondd = sessionFactory.openSession();
						System.err.println("Reg State"+value);

						Query q1 = sessiondd.createQuery(
								"from TB_STATE  WHERE state_name=:state_name ");
						q1.setParameter("state_name", value);
						List<TB_STATE> statreg = q1.list();
						sessiondd.close(); 
						pformA.setReg_state(statreg.get(0).getState_id());
						
//						pformA.setPer_state(Integer.parseInt(value));
					}
					 
					if (row_head.getCell(i1).getStringCellValue().equals("Ayush Id")) {
						System.err.println("Ayush Id -------"+value);
						pformA.setAyush_id(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("ABHA No")) {
						System.err.println("ABHA No -------"+value);
						pformA.setAbha_no(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("NRH Enrollment No")) {
						System.err.println("NRH -------"+value);
						pformA.setNrh_en_no(value.toUpperCase());
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Aadhaar Number")) {
						System.err.println("enter -------"+value);
						pformA.setAadhaar_no(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Gender")) {
						Session sessiondd = sessionFactory.openSession();

						Query q1 = sessiondd.createQuery(
								"from EDU_LMS_GENDER_MSTR  WHERE gender_name=:gender_name ");
						q1.setParameter("gender_name", value);
						List<EDU_LMS_GENDER_MSTR> genderlist = q1.list();
						sessiondd.close(); 
						pformA.setGender(genderlist.get(0).getId());
						
//						pformA.setPer_state(Integer.parseInt(value));
					}
				 
//                    if (row_head.getCell(i1).getStringCellValue().equals("Semester")) {
//						
//						skl.setSemester(value.split("_")[1]);
//					}
				}
				
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
				
//					skl.setInstitude_id(Integer.parseInt(userid));
//					skl.setUpload_date(date_of_upload);
				pformA.setCreated_date(new Date());
				pformA.setCreated_by(username);
				
				Query q0 = sessionHQL.createQuery("select count(*) from REG_NCH_FORM_A_P where email_id=:email_id ");
				q0.setParameter("email_id", pformA.getEmail_id());  
				Long c = (Long) q0.uniqueResult();
				
				 
				
				Query q1 = sessionHQL.createQuery("select count(*) from REG_NCH_FORM_A_P where aadhaar_no=:aadhaar_no ");
				q1.setParameter("aadhaar_no", pformA.getAadhaar_no());  
				Long c2 = (Long) q1.uniqueResult();
				if(c==0 && c2==0) {
//				if(c==0  ) {
					pformA.setStatus(1);
					pformA.setInstitute_status(1);
					pformA.setClg_status(1);
					pformA.setState_status(1);
					pformA.setNrh_status(0);
					pformA.setPract_type("NEW");
					
					 int formaid = (int)sessionHQL.save(pformA);
					 pmUG.setRegulation_p_id(formaid);
					 pmPG.setRegulation_p_id(formaid);
					 pmUG.setStatus(1);
					 pmPG.setStatus(1);
					 pmUG.setCreated_by(username);
					 pmUG.setCreated_date(new Date());
					 pmPG.setCreated_by(username);
					 pmPG.setCreated_date(new Date());
					 pmUG.setModified_by(username);
					 pmUG.setModified_date(new Date());
					 pmPG.setModified_by(username);
					 pmPG.setModified_date(new Date());
					 
					 sessionHQL.save(pmUG);
					 sessionHQL.save(pmPG);
					 
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode("Bisag@123");
					
//					p.setPassword(hashedPassword);
//					p.setEnabled(1);
//					p.setAccountNonExpired(1);
//					p.setAccountNonLocked(1);
//					p.setCredentialsNonExpired(1);
////					p.setInstitute_id(Integer.parseInt(userid));
////					p.setCountry_id(Integer.parseInt("5"));
////					p.setState_id(Integer.parseInt("12"));
//					p.setCreated_on(new Date());
//					p.setCreated_by(username);
//					UserRole role_tbl = new UserRole();

				
//					int did = (Integer) sessionHQL.save(pm);
//					if(role_type!=null && !role_type.equals("")) {
//						role_tbl.setRoleId(Integer.parseInt(role_type));
//
//					}else {
//						role_tbl.setRoleId(0);
//
//					}
//					role_tbl.setUserId(did);
//					sessionHQL.save(role_tbl);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Save Successfully");
				}
				else if(c2!=0){
					ra.addAttribute("msg", "Aadhar No Is Already Exist");
				}
				else if(c!=0){
					ra.addAttribute("msg", "Email id Already Exist");
				}else {
					ra.addAttribute("msg", "Data Not Save Successfully");
				}
				// for mail send
				//SendRegMail(p.getEmail_id());
				tx.commit();
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	 	return new ModelAndView("redirect:Excel_upload_formA_practitioner");
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
	
	@RequestMapping(value = "/Exp_state_Excel", method = RequestMethod.POST)
	public ModelAndView Exp_state_Excel(HttpServletRequest request, ModelMap model, HttpSession session, String typeReport1) {
		
//		List<recr_category_mst> cat= edu_com.getCategoryNameList();
		
	 
		
		
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
		model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
		
		ArrayList<ArrayList<String>> listSystem =  cd.getinstitute_system(Integer.parseInt(institute_id));
		ArrayList<ArrayList<String>> listDegree =  cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16");
		
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
//		ArrayList<ArrayList<String>> listUni = new ArrayList<ArrayList<String>>();
		 
	 	String UniMst = "";
		String StateMst = "";
		String DistMst = "";
		String DegMst = "";
		String PGDMst = "";
		
		List<String> TH = new ArrayList<String>();
		
		TH.add("Sr No");
		TH.add("Name");
		TH.add("Father Name");
		
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
			TH.add("Pre State:Drop:" + StateMst);
		}
		else {
			TH.add("Pre State:Drop:" + "State not available");
		}			
		
		List<String> getMedDistrict =  getMedDistrict();
		
		for (int i = 0; i < getMedDistrict.size(); i++) {
			if (i == 0) {
				DistMst += getMedDistrict.get(i);

			} else {
				DistMst += "," + getMedDistrict.get(i);
			}
		}
		
//		TH.add("Degree:Drop:" + degree);
		
		TH.add("Pre District:Drop:" + DistMst);
		
		TH.add("Pre Pincode");
		
		TH.add("Per state:Drop:" + StateMst);			
		TH.add("Per District:Drop:" + DistMst);
		TH.add("Per Pincode");
		TH.add("Email Id");
		TH.add("Mobile No");
		TH.add("Date Of Birth");
		
		List<String> getnationality =  getnationality();
		String nationality="";
		
		for (int i = 0; i < getnationality.size(); i++) {
			if (i == 0) {
				nationality +=  getnationality.get(i);

			} else {
				nationality += "," + getnationality.get(i);
			}
		}
		
		TH.add("Nationality:Drop:" + nationality);
		
		List<EDU_LMS_DEGREE_MASTER> getdegreeList =  getUGdegreeList();
		
		for (int i = 0; i < getdegreeList.size(); i++) {
			if (i == 0) {
				DegMst += getdegreeList.get(i).getDegree_name();

			} else {
				DegMst += "," + getdegreeList.get(i).getDegree_name();
			}
		}
		
//		TH.add("Degree:Drop:" + degree);
		
		TH.add("Degree/Under Graduate:Drop:" + DegMst);
//		System.err.println("-----degree--->   "+degree);
//		TH.add("University1");
		
		List<String> listUni =getUniversity();
		
		if(!listUni.isEmpty()) {
			
			for(int i=0; i<listUni.size(); i++ ) {
				if(i==listUni.size()) {
					UniMst+=listUni.get(i);
				}
				else if (i==0) {
					UniMst+=listUni.get(i);
				}
				else {
					UniMst+=","+listUni.get(i);
				}
				
			}
			TH.add("University1:Drop:" + UniMst);
		}
		else {
			TH.add("University1:Drop:" + "University Name not available");
		}
		
		List<EDU_LMS_DEGREE_MASTER> getpgdegreeList =  getPGdegreeList();
		
		for (int i = 0; i < getpgdegreeList.size(); i++) {
			if (i == 0) {
				PGDMst += getpgdegreeList.get(i).getDegree_name();

			} else {
				PGDMst += "," + getpgdegreeList.get(i).getDegree_name();
			}
		}
		
//		TH.add("Degree:Drop:" + degree);
		
		TH.add("Post Graduate Degree:Drop:" + PGDMst);
		
		List<String> listUni1 =getUniversity();
		
		if(!listUni.isEmpty()) {
			
			for(int i=0; i<listUni1.size(); i++ ) {
				if(i==listUni.size()) {
					UniMst+=listUni1.get(i);
				}
				else if (i==0) {
					UniMst+=listUni1.get(i);
				}
				else {
					UniMst+=","+listUni1.get(i);
				}
				
			}
			TH.add("University2:Drop:" + UniMst);
		}
		else {
			TH.add("University2:Drop:" + "University Name not available");
		}
		TH.add("State Reg No");
		TH.add("Reg Date");
		TH.add("Reg State:Drop:" + StateMst);
		TH.add("Ayush Id");
		TH.add("ABHA No");
		TH.add("NRH Enrollment No");
		TH.add("Aadhaar Number");
		
//		TH.add("Degree:Drop:" + degree);
		
		List<String> genderlist =  genderlist();
		String gender="";
		
		for (int i = 0; i < genderlist.size(); i++) {
			if (i == 0) {
				gender +=  genderlist.get(i);

			} else {
				gender += "," + genderlist.get(i);
			}
		}
		
		
		TH.add("Gender:Drop:" + gender);
		

		
		
//		if(!listSystem.isEmpty()) {
//			String Mst="";
//			for(int i=0; i<listSystem.size(); i++ ) {
//				if(i==listSystem.size()) {
//					Mst+=listSystem.get(i).get(1);
//				}
//				else if (i==0) {
//					Mst+=listSystem.get(i).get(1);
//				}
//				else {
//					Mst+=","+listSystem.get(i).get(1);
//				}
//				
//			}
//			TH.add("System:Drop:" + Mst);
//		}
//		else {
//			TH.add("System:Drop:" + "System not available");
//		}
//		if(!listDegree.isEmpty()) {
//			String Mst2="";
//			for(int j=0; j<listDegree.size(); j++ ) {
//				if(j==listDegree.size()) {
//					Mst2+=listDegree.get(j).get(1);
//				}
//				else if (j==0) {
//					Mst2+=listDegree.get(j).get(1);
//				}
//				else {
//					Mst2+=","+listDegree.get(j).get(1);
//				}
//				
//			}
//			TH.add("Degree:Drop:" + Mst2);
//		}
//		else {
//			TH.add("Degree:Drop:" + "Degree not available");
//		}
		
		
//		TH.add("System:Drop:" + "HOMOEOPATHY");			
		
//		TH.add("Enrollment_No");
//		TH.add("upload_date");
//		TH.add("Gender:Drop:" + "Male,Female");
//		TH.add("Semester:Drop:" + "Sem_1,Sem_2,Sem_3,Sem_4,Sem_5,Sem_6,Sem_6,Sem_8,Sem_9,Sem_10,Sem_11,Sem_12,Sem_13,Sem_14,Sem_15");
//		TH.add("Internship_completion_date");
		
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		
		return new ModelAndView(new Gen_Prac_Excel_Contoller("L", TH, Heading, username, UniMst, StateMst, DistMst, DegMst, PGDMst), "userList", listexport);
	}
 
 private List<String> getnationality() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q1 = session.createQuery(
				"select distinct nationality from recr_nationality_mst where status='A'");
	 List<String> nationality = q1.list();
	 tx.commit();
      session.close();
	return nationality;
}
 
 private List<String> getUniversity() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q1 = session.createQuery(
				"select university_name from EDU_LMS_UNIVERSITY_MSTR where status='1'");
	 List<String> getUniversity = q1.list();
	 tx.commit();
      session.close();
	return getUniversity;
}
 
public List<EDU_LMS_DEGREE_MASTER> getUGdegreeList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_LMS_DEGREE_MASTER where status='1' and type_of_degree in (15) ");
	 
	 List<EDU_LMS_DEGREE_MASTER> degree = q0.list();
	 tx.commit();
     session.close();                
     return degree;
}

public List<EDU_LMS_DEGREE_MASTER> getPGdegreeList() {
	 Session session = sessionFactory.openSession();
	 Transaction tx = session.beginTransaction();
	 Query q0 = session.createQuery("from EDU_LMS_DEGREE_MASTER where status='1' and type_of_degree in (16) ");
	 
	 List<EDU_LMS_DEGREE_MASTER> degree = q0.list();
	 tx.commit();
    session.close();                
    return degree;
}

public List<String> getMedDistrict() {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx1 = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery(
				" select d.district_name from EDU_LMS_DISTRICT_MSTR d WHERE d.status='1' ");
		List<String> district = q1.list();
		tx1.commit();
		sessionHQL.close(); 
		return district;
}	

public List<String> genderlist() {
	Session sessionHQL = sessionFactory.openSession();
	Transaction tx1 = sessionHQL.beginTransaction();
		Query q1 = sessionHQL.createQuery(
				"select gender_name from EDU_LMS_GENDER_MSTR  WHERE status='1' ");
		List<String> gender = q1.list();
		tx1.commit();
		sessionHQL.close(); 
		return gender;
}

}
