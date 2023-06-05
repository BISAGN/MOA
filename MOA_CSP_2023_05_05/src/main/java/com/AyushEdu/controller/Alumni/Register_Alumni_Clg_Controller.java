package com.AyushEdu.controller.Alumni;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Alumni.EDU_ALUM_REG_ALUMNI_CLG;
import com.AyushEdu.Models.Alumni.EDU_ALUM_SIGN_UP;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.controller.Exp_Excel.GenExcel_Contoller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Alumni_Exp_ExcelDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Master.DistrictDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Register_Alumni_Clg_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Commondao cd;
	@Autowired
	CommonController common;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	Alumni_Exp_ExcelDAO  EAdao;
	@Autowired
	private DistrictDao Dis_Dao;
	@Autowired
	ValidationController validation;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	EmailConfig e;
	
	@RequestMapping(value = "/admin/clg_Add_Alumni_Url", method = RequestMethod.GET)
	public ModelAndView clg_Add_Alumni_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
			
//		//SECURITY -- RIDDHI 
		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("clg_Add_Alumni_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Mmap.put("msg", msg);
		
		return new ModelAndView("Cld_Add_Alumni_Tiles");

	}
	
	@RequestMapping(value = "/Exp_Excel_clg_reg_alumni", method = RequestMethod.POST)
	public ModelAndView Exp_Excel_clg_reg_alumni(HttpServletRequest request, ModelMap model, HttpSession session, String typeReport1) {
		 
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		List<String> TH = new ArrayList<String>();
		
		TH.add("Name");
		TH.add("Mobile_No");
		TH.add("Email");
		TH.add("Passing_Year");
		TH.add("Batch");
		TH.add("Adhaar_No");
		
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		
		return new ModelAndView(new GenExcel_Contoller("L", TH, Heading, username), "userList", listexport);
		
	}
	
	@PostMapping(value = "/clgRegisterAlumniAction")
	public ModelAndView clgRegisterAlumniAction(@Validated @ModelAttribute("clgRegisterAlumniCmd") EDU_ALUM_REG_ALUMNI_CLG skl,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, @RequestParam(value = "upload_excel1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		//SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("clg_Add_Alumni_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		Session sessionHQL = this.sessionFactory.openSession();
		
		UserLogin p = new UserLogin();

		try {
			
			String excorform = request.getParameter("excorform");
			
			Transaction tx = sessionHQL.beginTransaction();
			
			int id = skl.getId() > 0 ? skl.getId() : 0;
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute="";
			
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			institute = String.valueOf(ea.getInstitute_id());

			if(excorform.equals("1")) {
				
				if (upload.isEmpty()) {
					ra.addAttribute("msg", "Please Upload File.");
					return new ModelAndView("redirect:exp_excel_url");
				}
				
				File file = new File(expcon.fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
				
				String extention="";
				int z = upload.getOriginalFilename().lastIndexOf('.');
				if (z >= 0) {
					extention = upload.getOriginalFilename().substring(z + 1);
				}
				
				if(!extention.equals("xls")) {
					ra.addAttribute("msg", "Please Select Excel File");
					return new ModelAndView("redirect:exp_excel_url");
				}
				
				FileInputStream fis = new FileInputStream(file);
				HSSFWorkbook wb = new HSSFWorkbook(fis);
				HSSFSheet sheet = wb.getSheetAt(0);
				Row row_head = sheet.getRow(0);
				
				if (!row_head.getCell(0).getStringCellValue().equals("Name")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Name");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				if (!row_head.getCell(1).getStringCellValue().equals("Mobile_No")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Mobile No");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				if (!row_head.getCell(2).getStringCellValue().equals("Email")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Email");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				if (!row_head.getCell(3).getStringCellValue().equals("Passing_Year")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Passing Year");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				if (!row_head.getCell(4).getStringCellValue().equals("Batch")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Batch");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				if (sheet.getLastRowNum() == 0) {
					ra.addAttribute("msg", "Please Enter Data in Atleast One Row");
					return new ModelAndView("redirect:clg_Add_Alumni_Url");
				}
				
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					
					Row row = sheet.getRow(i);
					if (row.getCell(0) == null) {
						break;
					}
					
					for (int i1 = 0; i1 <= 5; i1++) {
						
					String varforval = "";
						if(i1==0) {
							varforval = "Name";
						}
						if(i1==1) {
							varforval = "Mo. No.";
						}
						if(i1==2) {
							varforval = "Email";
						}
						if(i1==3) {
							varforval = "Passing Year";
						}
						if(i1==4) {
							varforval = "Batch";
						}
						
						Cell cell = row.getCell(i1);
						if(cell == null) {
							ra.addAttribute("msg", "Please Enter "+varforval+" in row "+i);
							return new ModelAndView("redirect:clg_Add_Alumni_Url");
						}
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
						
						if (row_head.getCell(i1).getStringCellValue().equals("Name")) {
							skl.setAlum_name(value);
							//p.setUserName(value);
							p.setLogin_name(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Adhaar_No")) {
							//skl.setAlum_name(value);
							p.setUserName(value);
							
							//p.setLogin_name(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
							skl.setAlum_mo_no(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Email")) {
							skl.setAlum_email(value);
							p.setEmail_id(value);
							
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Passing_Year")) {
							skl.setAlum_passing_year(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Batch")) {
							skl.setAlum_batch(value);
						}
						if (!userid.equals("")) {
		                	
							skl.setInstitute_id(ea.getInstitute_id());
							p.setInstitute_id(Integer.parseInt(institute));
							
							
							String state = String.valueOf(ea.getState_id());
							p.setState_id(Integer.parseInt(state));
						}
//					    skl.setInstitute_id(Integer.parseInt(userid));
						
						//----------------------for mail send-------------------------------
						
//						SendRegMail(p.getEmail_id());
						
						String subject = "Your Registration For Alumni is Successfull";
						String main_txt = "Your Registration For Alumni is Successfull ... Thank you for Registrating with us!";
						String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
						e.SendMail(request, p.getEmail_id(), roleSubAccess, subject, main_txt, follow_txt, "", "");
					}
				}
			}
			
			if(excorform.equals("2")) {
				
				String alum_name = request.getParameter("alum_name");
				String alum_mo_no = request.getParameter("alum_mo_no");
				String alum_email = request.getParameter("alum_email");
				String passing_year = request.getParameter("passing_year");
				String batch = request.getParameter("batch");
				
				
				skl.setAlum_name(alum_name);
				p.setUserName(alum_name);
				p.setLogin_name(alum_name);
				skl.setAlum_mo_no(alum_mo_no);
				skl.setAlum_email(alum_email);
				p.setEmail_id(alum_email);
				skl.setAlum_passing_year(passing_year);
				skl.setAlum_batch(batch);
				
			}
			
			skl.setCreated_by(Integer.parseInt(userid));
			skl.setCreated_date(new Date());
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode("Bisag@123");
			
			
			p.setPassword(hashedPassword);
			p.setEnabled(1);
			p.setAccountNonExpired(1);
			p.setAccountNonLocked(1);
			p.setCredentialsNonExpired(1);
			p.setCreated_on(new Date());
			p.setCreated_by(userid);
			
			int did = (Integer) sessionHQL.save(p);
			
			skl.setUser_id(did);
			
			int mid = (int) sessionHQL.save(skl);
			
			UserRole role_tbl = new UserRole();
			
//			role-id-alumni-NCH-37
//			role-id-alumni-NCISM-38
			
			Query q1 = sessionHQL.createQuery(
					"select sm.system_name from EDU_LMS_INSTITUTE_REGISTRATION ir,EDU_LMS_SYSTEM_MSTR sm where ir.id=:id and ir.system_id=sm.id");
			q1.setParameter("id", Integer.parseInt(institute));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			String role="";
			if(list.get(0).equals("HOMOEOPATHY")) {
				role = "37";
			}else {
				role = "38";
			}
			role_tbl.setRoleId(Integer.parseInt(role));
			role_tbl.setUserId(did);
			sessionHQL.save(role_tbl);
			
			sessionHQL.flush();
			sessionHQL.clear();
			
			ra.addAttribute("msg", "Data Saved Successfully");
			
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				return new ModelAndView("redirect:clg_Add_Alumni_Url");
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
				return new ModelAndView("redirect:clg_Add_Alumni_Url");
			}
		}
		return new ModelAndView("redirect:clg_Add_Alumni_Url");
	}
	
//	// FOR EMAIL
//		public void SendRegMail(String email) throws ParseException {
////			String code = randomString(5);
//			MailHTML html = new MailHTML();
//			try {
//				MimeMessage mimeMessage = mailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//				try {
//					String text = "Your Registration For Alumni is Successfull ... Thank you for Registrating with us! Now , you can Login with your credentials.";
//					String note = "";
//					html.setHTML(text, note);
//					String htmlMsg = html.getHTML();
//					helper.setText(htmlMsg, true);
//					helper.setTo(email);
//					helper.setSubject("MOA Registration is Successfull");
//					/* helper.setFrom("abc@gmail.com"); */
//					mailSender.send(mimeMessage);
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//			}
//		}
		
		@PostMapping("/getFilter_Exp_Alum_Excel_data")
		public @ResponseBody List<Map<String, Object>> getFilter_Exp_Alum_Excel_data(int startPage, int pageLength,
		String Search,String orderColunm,String orderType) throws ParseException {
			return EAdao.DataTableEdu_Alumni_Exp_excel(startPage, pageLength, Search, orderColunm, orderType );

		}

		@PostMapping("/getTotalEdu_Exp_Alum_Excel_dataCount")

		public @ResponseBody long getTotalEdu_Exp_Alum_Excel_dataCount(HttpSession sessionUserId,String Search ) throws ParseException {
			return EAdao.DataTableEdu_Alumni_Exp_Excel_Count(Search );

		}
		
	
		
		@PostMapping(value = "/Alumni_SignUp_Action") 
		public ModelAndView Alumni_SignUp_Action(@Validated @ModelAttribute("AlumSignupCmd") EDU_ALUM_SIGN_UP td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {
			
			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			
			String institute = request.getParameter("institute");
			String country = request.getParameter("country_id");
			String state = request.getParameter("state_id");
			String abbrivation = request.getParameter("state_abbr");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mob_no = request.getParameter("mob_no");
			String aadhar_no = request.getParameter("aadhar_no");
			String year_leave = request.getParameter("year_leave");
			String address = request.getParameter("address");
			String present_status = request.getParameter("present_status");
			String pre_wrk_plc = request.getParameter("pre_wrk_plc");
			String area_intrst = request.getParameter("area_intrst");
			String nostalgia = request.getParameter("nostalgia");

			MultipartFile file = mul.getFile("intern_certi");
				
	//NAYAN----------
			if (institute.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Institute");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (country.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Country");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
//			if (abbrivation.trim().equals(" ")) {
//				ra.addAttribute("msg", "Please Select Abbrivation");
//				return new ModelAndView("redirect:AlumniSignup_Url");
//			}
			if (abbrivation.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter State Abbreviation.");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.maxlength10(abbrivation) == false) {
				ra.addAttribute("msg","Abbreviation "+ validation.MaxlengthMSG10);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.isOnlyAlphabetNumeric(abbrivation) == false) {
				ra.addAttribute("msg","Abbreviation "+ validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Name");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.isOnlyAlphabet(name) == false) {
				ra.addAttribute("msg", "Code " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.maxlengthcheck30(name) == false) {
				ra.addAttribute("msg", " Name " + validation.MaxlengthcheckMSG30);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Id.");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.maxlengthcheck70(email) == false) {
				ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (mob_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile No.");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
//			if (mob_no == null || mob_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Mobile No.");
//				return new ModelAndView("redirect:AlumniSignup_Url");
//			}
			if (mob_no.length() != 10) {
				ra.addAttribute( "msg" ,  "Please Enter 10 digit Mobile No");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.isOnlyNumer(mob_no) == true) {
				ra.addAttribute("msg", "Mobile No " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (aadhar_no == null || aadhar_no.trim().equals("")) {
				ra.addAttribute( "msg" ,  "Please Enter Aadhar No");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (aadhar_no.length() != 12) {
				ra.addAttribute( "err" ,  "Please Enter 12 digit Aadhar No");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (validation.isOnlyNumer(aadhar_no) == true) {
				ra.addAttribute("msg", "Aadhar No " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (address.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Address");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (present_status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Present Status");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (pre_wrk_plc.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Present Working Place");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (area_intrst.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Area Of Interest");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (nostalgia.trim().equals("")) {
				ra.addAttribute("msg", "Please Nostalgia or Memorable Event");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
	//NAYAN---------
	//SECURITY-----
			if (file.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg","Please Upload File");
				return new ModelAndView("redirect:AlumniSignup_Url");
			}
			if (!file.getOriginalFilename().isEmpty()) {

				 if (file.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:AlumniSignup_Url");
				}
				
				String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("pdf")) {
					ra.addAttribute("msg","Please Upload File of .pdf extention");
					return new ModelAndView("redirect:AlumniSignup_Url");
				}
				long filesize = file.getSize() / 1024;
				if (filesize > 200) {
					ra.addAttribute("msg","File size should be 200 kb or less");
					return new ModelAndView("redirect:AlumniSignup_Url");
				}
			}
	//SECURITY-----	
			
			String intern_certi = common.fileupload(file.getBytes(), file.getOriginalFilename(),1, "intern_certi");

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_ALUM_SIGN_UP where institute=:institute and upper(name)=:name \n"
						+" and email=:email and mob_no=:mob_no and aadhar_no=:aadhar_no \n"
						+" and id !=:id")
						.setParameter("institute",Integer.parseInt(institute))
						.setParameter("name",name.toUpperCase())
						.setParameter("email",email)
						.setParameter("mob_no",mob_no)
						.setParameter("aadhar_no",aadhar_no)
						.setParameter("id", id).uniqueResult();
				if (id == 0) {
					td.setInstitute(Integer.parseInt(institute));
					td.setName(name);
					td.setEmail(email);
					td.setMob_no(mob_no);
					td.setAadhar_no(aadhar_no);
					td.setYear_leave(year_leave);
					td.setAddress(address);
					td.setPre_wrk_plc(pre_wrk_plc);
					td.setPresent_status(present_status);
					td.setArea_intrst(area_intrst);
					td.setNostalgia(nostalgia);
					td.setIntern_certi(intern_certi);
					td.setCreated_by(name);
					td.setCreated_date(date);
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
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:AlumniSignup_Url");
		}
		
		@RequestMapping(value = "/admin/AppAlumSignupReqUrl", method = RequestMethod.GET)
		public ModelAndView AppAlumSignupReqUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) {
				
			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("AppAlumSignupReqUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//				
				Mmap.put("msg", msg);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				
			return new ModelAndView("AppAlumSignupReq_Tiles");

		}
		
		@PostMapping("/getAlumni_Request_data")
		public @ResponseBody List<Map<String, Object>> getAlumni_Request_data(HttpSession Session,int startPage, int pageLength,
		String Search,String orderColunm,String orderType,String status,String name,HttpSession session) throws ParseException {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
			return EAdao.DataTableAlumniApprove_Request(startPage, pageLength, Search, orderColunm, orderType,instid,status,name);        
		}

		@PostMapping("/getTotalAlumni_Request_data_dataCount")
		public @ResponseBody long getTotalAlumni_Request_data_dataCount(String Search,String status,String name,HttpSession session) throws ParseException {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
			return EAdao.DataTableAlumniApprove_Request_Count(Search,instid,status,name);
		}
		
		@RequestMapping(value = "/AppRejAlumReqUrl", method = RequestMethod.POST)
		public ModelAndView AppRejAlumReqUrl(@ModelAttribute("ARId") String id,@RequestParam ("ARTmp") String tmp,
				BindingResult result, RedirectAttributes ra,HttpServletRequest request, ModelMap model, HttpSession session1) throws ParseException {

			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("AppAlumSignupReqUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
				List<String> liststr = new ArrayList<String>();
				String msg="";
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String username = session1.getAttribute("username").toString();
				int app=0;
				int lid=0;
				int uid=0;
				int ctid=0;
				
				String userid = session1.getAttribute("userId_for_jnlp").toString();
				String roleSubAccess = session1.getAttribute("roleSubAccess").toString();


					Query q = sessionHQL.createQuery("from EDU_ALUM_SIGN_UP where id=:id ");
					q.setParameter("id",  Integer.parseInt(id));

					@SuppressWarnings("unchecked")
					List<EDU_ALUM_SIGN_UP> clist = (List<EDU_ALUM_SIGN_UP>) q.list();
				
				if(tmp.equals("1")) {
					app = sessionHQL.createQuery(
							"update EDU_ALUM_SIGN_UP set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("status", 1).executeUpdate();
					msg = "Data Approved Successfully";
					
					UserLogin p = new UserLogin();
					
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode("Bisag@123");
					
					p.setAadhar_no(clist.get(0).getAadhar_no());
					p.setInstitute_id(clist.get(0).getInstitute());
					p.setEmail_id(clist.get(0).getEmail());
					p.setUserName(clist.get(0).getEmail());
					p.setLogin_name(clist.get(0).getName());
					p.setPassword(hashedPassword);
					p.setEnabled(1);
					p.setAccountNonExpired(1);
					p.setAccountNonLocked(1);
					p.setCredentialsNonExpired(1);
					p.setCreated_on(new Date());
					p.setCreated_by(userid);
					
					 lid = (int) sessionHQL.save(p);
					
					UserRole role_tbl = new UserRole();
					
//					role-id-alumni-NCH-37
//					role-id-alumni-NCISM-38
					
					Query q1 = sessionHQL.createQuery(
							"select sm.system_name from EDU_LMS_INSTITUTE_REGISTRATION ir,EDU_LMS_SYSTEM_MSTR sm where ir.id=:id and ir.system_id=sm.id");
					q1.setParameter("id", clist.get(0).getInstitute());
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) q1.list();
					String role="";
					if(list.get(0).equals("HOMOEOPATHY")) {
						role = "37";
					}else {
						role = "38";
					}
					role_tbl.setRoleId(Integer.parseInt(role));
					role_tbl.setUserId(lid);
					 uid = (int) sessionHQL.save(role_tbl);
					 
					 EDU_ALUM_REG_ALUMNI_CLG ct = new EDU_ALUM_REG_ALUMNI_CLG();
					 
					 ct.setAlum_address(clist.get(0).getAddress());
					 ct.setAlum_email(clist.get(0).getEmail());
					 ct.setAlum_mo_no(clist.get(0).getMob_no());
					 ct.setAlum_name(clist.get(0).getName());
					 ct.setUser_id(lid);
					 
					 ctid = (int) sessionHQL.save(ct);
					 
					if (app > 0 && lid > 0 && uid > 0 && ctid > 0) {
						liststr.add(msg);
						String subject = "Your Registration For Alumni is Successfull";
						String main_txt = "Your Registration For Alumni is Successfull ... Thank you for Registrating with us!";
						String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
						e.SendMail(request, clist.get(0).getEmail(), roleSubAccess, subject, main_txt, follow_txt, "", "");
					} else {
						liststr.add("Something went wrong !!!");
					}
					
				}
				if(tmp.equals("2")) {
					app = sessionHQL.createQuery(
							"update EDU_ALUM_SIGN_UP set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("status", 2).executeUpdate();
					msg = "Data Rejected Successfully";
					if (app > 0 ) {
						liststr.add(msg);
					} else {
						liststr.add("Something went wrong !!!");
					}
				}
				
				tx.commit();
				sessionHQL.close();
				
				ra.addAttribute("msg", liststr.get(0));

			return new ModelAndView("redirect:AppAlumSignupReqUrl");
		}
		
		@RequestMapping(value = "/getStateListFormcon2", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getStateListFormcon2(String country_id) {
			//			String contry_id = "";
			return Dis_Dao.getCountry_List(country_id);
		}

}
