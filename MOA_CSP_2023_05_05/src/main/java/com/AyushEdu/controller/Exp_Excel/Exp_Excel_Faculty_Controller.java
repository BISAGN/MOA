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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_FACULTY_NCH;
import com.AyushEdu.config.DynamicMailFormatGen;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.config.MailDefine;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Exp_Excel.Faculty_DetailsDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Exp_Excel_Faculty_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Commondao cd;
	@Autowired
	CommonController common;
	@Autowired
	Faculty_DetailsDao fdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	EmailConfig emailsend;

	@Autowired
	AyushId_Directory_Controller dir;

	@Autowired
	ValidationController validation;

	@GetMapping(value = "/exp_excel_faculty_url")
	public ModelAndView exp_excel_nch_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		try {
			// SECURITY ABHISHEK
			if (request.getHeader("Referer") == null) {

				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();

			Boolean val = roledao.ScreenRedirect("exp_excel_faculty_url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();

			String role = session.getAttribute("rolename").toString();

			// String institute_id =
			// cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
			Date date = new Date();
			// model.put("getinstitute_system",
			// cd.getinstitute_system(Integer.parseInt(institute_id)));
			// model.put("getstu_DegreeList",
			// cd.getstu_DegreeList(Integer.parseInt(institute_id)));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Exp_Excel_Faculty_Tiles");
	}

	@RequestMapping(value = "/Exp_Excel_Faculty", method = RequestMethod.POST)
	public ModelAndView Exp_Excel_Faculty(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1) {

		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("exp_excel_faculty_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//		
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> listSystem = cd.getinstitute_system(Integer.parseInt(institute_id));
		ArrayList<ArrayList<String>> listDegree = cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16");

		List<String> TH = new ArrayList<String>();

		TH.add("Name");
//		TH.add("UserId");

		TH.add("Aadhar_Card");
		TH.add("Email");
		TH.add("Mobile_No");
		if (stafflevel1.toUpperCase().equals("NCISM")) {
			TH.add("Teacher_Code");
		}
//		TH.add("Role:Drop:" + "Faculty_NCH");

		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new GenExcel_Contoller("L", TH, Heading, username), "userList", listexport);
	}

	@PostMapping(value = "/Exp_Excel_Faculty_action")
	public ModelAndView Exp_Excel_Faculty_action(@Validated @ModelAttribute("UserLogin_cmd") UserLogin p,
			EDU_LMS_FACULTY_NCH skl, BindingResult result, HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra,
			@RequestParam(value = "u_file1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();

		// check for list empty
		// UserLogin p = new UserLogin();

		System.err.println("hiiiii");

		try {

			int count_duplicate = 0;
			String board_id = "";

			int id = skl.getId() > 0 ? skl.getId() : 0;
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			String date_of_upload = request.getParameter("upload_date");

			if (upload.isEmpty()) {
				ra.addAttribute("msg", "Please Upload File.");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}

			if (!upload.isEmpty()) {
				if (upload.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:exp_excel_faculty_url");
				}
			}

			if (date_of_upload == null || date_of_upload.trim().equals("") || date_of_upload.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter Date.");
				return new ModelAndView("redirect:exp_excel_faculty_url");

			}

			File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));

			String extention = "";
			int z = upload.getOriginalFilename().lastIndexOf('.');
			if (z >= 0) {
				extention = upload.getOriginalFilename().substring(z + 1);
			}

			if (!extention.equals("xls")) {
				ra.addAttribute("msg", "Please Select Excel File");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}

			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row_head = sheet.getRow(0);

			if (!row_head.getCell(0).getStringCellValue().equals("Name")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Name");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}

			if (!row_head.getCell(1).getStringCellValue().equals("Aadhar_Card")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Aadhar Card");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}

			if (!row_head.getCell(2).getStringCellValue().equals("Email")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Email");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}
			if (!row_head.getCell(3).getStringCellValue().equals("Mobile_No")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Mobile No");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}
			if (stafflevel1.toUpperCase().equals("NCISM")) {
				if (!row_head.getCell(4).getStringCellValue().equals("Teacher_Code")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Teacher_Code");
					return new ModelAndView("redirect:exp_excel_faculty_url");
				}
			}

			int increemt = 0;
			String abr = "";
			String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

			Transaction tx = sessionHQL.beginTransaction();
System.err.println("sheet.getLastRowNum()----------------"+sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				String UserName = "";
				String UserId = "";
				String Aadhar_Card = "";
				String Email = "";
				String phone = "";
				String Teacher_code = "";
				String AyushID = "";

				Row row = sheet.getRow(i);
				// System.err.println("-----------7 March 23-----"+row);

//				if (row.getCell(0) == null) {
//					ra.addAttribute("msg", "Please Enter  Value in row 1");
//					return new ModelAndView("redirect:exp_excel_faculty_url");
//				}

				if (stafflevel1.toUpperCase().equals("NCISM")) {

					for (int i1 = 0; i1 <= 4; i1++) {

						Cell cell = row.getCell(i1);

						String varforval = "";
						if (i1 == 0) {
							varforval = "Name";
						}
						if (i1 == 1) {
							varforval = "Aadhar_Card";
						}
						if (i1 == 2) {
							varforval = "Email";
						}
						if (i1 == 3) {
							varforval = "Mobile_No";
						}

						if (stafflevel1.toUpperCase().equals("NCISM")) {

							if (i1 == 4) {
								varforval = "Teacher_Code";
							}
						}

						if (cell == null) {
							ra.addAttribute("msg", "Please Enter " + varforval + " in row " + i + " "
									+ "Please Update then Upload Again");
							return new ModelAndView("redirect:exp_excel_faculty_url");
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
							System.out.println("Name============== "+value);
							if (validation.isOnlyAlphabetNumber(value) == false) {
								ra.addAttribute("msg", "Name " + validation.isOnlyAlphabetNumberMSG);
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {
								skl.setUsername(value);
								p.setLogin_name(value);
								UserName = value;

							}

						}

						if (row_head.getCell(i1).getStringCellValue().equals("Aadhar_Card")) {

							if (value.length() != 12) {
								ra.addAttribute("msg", "Please Enter 12 Digit Aadhaar No. in Row - " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {

								skl.setAadhar_card(value);
								p.setUserName(value);
								p.setAadhar_no(value);
								UserId = value;
								Aadhar_Card = value;
							}
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Email")) {
							// String pattern = "^[a-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
							String pattern = "^(?=.{1,64}@)[a-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
									+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
							List<String> values = new ArrayList<String>();
							values.add(value);
							Pattern ptrn = Pattern.compile(pattern);
							Matcher matcher = ptrn.matcher(values.get(0));
							if (!matcher.matches()) {
								ra.addAttribute("msg", "Please Enter valid Email-id in Row " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							}

							else {
								skl.setEmail(value);
								p.setEmail_id(value);
								Email = value;
							}
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
							if (value.length() != 10) {
								ra.addAttribute("msg", "Please Enter 10 Digit Mobile No. in Row - " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {
								skl.setMobile_no(value);
								p.setMobile_no(value);
								phone = value;
							}
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Teacher_Code")) {
							// if (stafflevel1.toUpperCase().equals("NCISM")){
							skl.setTeacher_code(value);
							Teacher_code = value;
							// }
						}

					}

					String maxAID = "";
					if (i == 1) {
						maxAID = getAyushIDNCISM(userid);
						increemt = Integer.parseInt(maxAID.substring(11));
						abr = maxAID.substring(0, 11);

					} else {
						increemt += 1;
						maxAID = abr;
						maxAID += String.format("%5s", increemt).replace(' ', '0');
					}

					skl.setAyush_id_ncism(maxAID);

					AyushID = maxAID;

				} else {

					for (int i1 = 0; i1 <= 3; i1++) {

						Cell cell = row.getCell(i1);

						String varforval = "";
						if (i1 == 0) {
							varforval = "Name";
						}
						if (i1 == 1) {
							varforval = "Aadhar_Card";
						}
						if (i1 == 2) {
							varforval = "Email";
						}
						if (i1 == 3) {
							varforval = "Mobile_No";
						}

						if (stafflevel1.toUpperCase().equals("NCISM")) {

							if (i1 == 4) {
								varforval = "Teacher_Code";
							}
						}

						if (cell == null) {
							System.err.println("name value--------------------"+"-i-"+i+"-i1-"+i1);
							ra.addAttribute("msg", "Please Enter " + varforval + " in row " + i + " "
									+ "Please Update then Upload Again");
							return new ModelAndView("redirect:exp_excel_faculty_url");
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
							System.err.println("name value--------------------"+value+"-i-"+i+"-i1-"+i1);
							if (validation.isOnlyAlphabetNumber(value) == false) {
								ra.addAttribute("msg", "Name " + validation.isOnlyAlphabetNumberMSG);
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {
							skl.setUsername(value);
							p.setLogin_name(value);
							UserName = value;
							}
						}

						if (row_head.getCell(i1).getStringCellValue().equals("Aadhar_Card")) {
							if (value.length() != 12) {
								ra.addAttribute("msg", "Please Enter 12 Digit Aadhaar No. in Row - " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {
								skl.setAadhar_card(value);
								p.setUserName(value);
								p.setAadhar_no(value);
								UserId = value;
								Aadhar_Card = value;
							}
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Email")) {

							String pattern = "^(?=.{1,64}@)[a-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
									+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
							List<String> values = new ArrayList<String>();
							values.add(value);
							Pattern ptrn = Pattern.compile(pattern);
							Matcher matcher = ptrn.matcher(values.get(0));
							if (!matcher.matches()) {
								ra.addAttribute("msg", "Please Enter valid Email-id in Row " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							}

							else {

								skl.setEmail(value);
								p.setEmail_id(value);
								Email = value;
							}
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
							if (value.length() != 10) {
								ra.addAttribute("msg", "Please Enter 10 Digit Mobile No. in Row - " + i + " "
										+ "Please Update then Upload Again");
								return new ModelAndView("redirect:exp_excel_faculty_url");
							} else {
								skl.setMobile_no(value);
								p.setMobile_no(value);
								phone = value;
							}
						}
					}

				}

				if (!userid.equals("")) {
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String university = String.valueOf(ea.getUniversity_id());
					skl.setUniversity_id(Integer.parseInt(university));
					p.setUniversity_id(Integer.parseInt(university));

					String institude = String.valueOf(ea.getInstitute_id());
					skl.setInstitute_id(Integer.parseInt(institude));
					p.setInstitute_id(Integer.parseInt(institude));

					String state = String.valueOf(ea.getState_id());
					skl.setState_id(Integer.parseInt(state));
					p.setState_id(Integer.parseInt(state));
				}
				// skl.setInstitude_id(Integer.parseInt(userid));
				// skl.setUpload_date(date_of_upload);

				// System.err.println(" skl.getEmail()------------> "+ skl.getEmail());

				Query q0 = sessionHQL.createQuery("select count(*) from EDU_LMS_FACULTY_NCH where email=:email ");
				q0.setParameter("email", Email);

				Query q1 = sessionHQL
						.createQuery("select count(*) from EDU_LMS_FACULTY_NCH where aadhar_card=:aadhar_card ");
				q1.setParameter("aadhar_card", Aadhar_Card);

				Query q2 = sessionHQL
						.createQuery("select count(*) from EDU_LMS_FACULTY_NCH where teacher_code=:teacher_code ");
				q2.setParameter("teacher_code", Teacher_code);

				Query q3 = sessionHQL
						.createQuery("select count(*) from EDU_LMS_FACULTY_NCH where mobile_no=:mobile_no ");
				q3.setParameter("mobile_no", phone);

				Long c = (Long) q0.uniqueResult();

				Long c1 = (Long) q1.uniqueResult();

				Long c2 = (Long) q2.uniqueResult();

				Long c3 = (Long) q3.uniqueResult();

				System.err.println("---------teacher_code------------" + c2);

				if (c == 0 && c1 == 0 && c2 == 0 && c3 == 0) {

					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode("Bisag@123");

					p.setPassword(hashedPassword);
					p.setEnabled(1);
					p.setAccountNonExpired(1);
					p.setAccountNonLocked(1);
					p.setCredentialsNonExpired(1);
//					p.setInstitute_id(Integer.parseInt(userid));
//					p.setCountry_id(Integer.parseInt("5"));
//					p.setState_id(Integer.parseInt("12"));

					p.setCreated_on(datePickerFormat.parse(date_of_upload));
					skl.setCreated_date(datePickerFormat.parse(date_of_upload));
					p.setCreated_by(username);
					skl.setCreated_by(username);
					skl.setStatus(1);
					if (stafflevel1.toUpperCase().equals("NCH")) {
						skl.setRole("Faculty_NCH");
					}
					if (stafflevel1.toUpperCase().equals("NCISM")) {
						skl.setRole("Faculty_NCISM");
					}
					UserRole role_tbl = new UserRole();

					int did = (Integer) sessionHQL.save(p);

					String roleid = "";

					if (stafflevel1.toUpperCase().equals("NCH")) {

						role_tbl.setRoleId(24);
						roleid = "24";
					}
					if (stafflevel1.toUpperCase().equals("NCISM")) {

						role_tbl.setRoleId(23);
						roleid = "23";
					}
					role_tbl.setUserId(did);
					sessionHQL.save(role_tbl);
					skl.setUserid(String.valueOf(p.getUserId()));
					int mid = (int) sessionHQL.save(skl);
					sessionHQL.flush();
					sessionHQL.clear();
					// ra.addAttribute("msg", "Data Save Successfully");

					System.err.println("-------- Directory code Start Here -------");

					if (stafflevel1.toUpperCase().equals("NCISM")) {
						Boolean Directory = dir.SaveAyushId_Directory(AyushID, String.valueOf(did), Aadhar_Card, roleid,
								session);
						System.err.println("Directory----------->    " + Directory);
					}
				} else {

					if (c != 0) {

						ra.addAttribute("msg", "Email id" + "  " + Email + " Already Exist in Row" + " " + i + " "
								+ "Please Update then Upload Again");
						return new ModelAndView("redirect:exp_excel_faculty_url");
					}
					if (c1 != 0) {

						ra.addAttribute("msg", "Aadhar card Number" + " " + Aadhar_Card + " Already Exist in Row" + " "
								+ i + " " + "Please Update then Upload Again");
						return new ModelAndView("redirect:exp_excel_faculty_url");
					}
					if (c2 != 0) {

						ra.addAttribute("msg", "Teacher Code " + " " + Teacher_code + " Already Exist in Row" + " " + i
								+ " " + "Please Update then Upload Again");
						return new ModelAndView("redirect:exp_excel_faculty_url");
					}
					if (c3 != 0) {

						ra.addAttribute("msg", "Mobile Number " + " " + phone + " Already Exist in Row " + " " + i + " "
								+ "Please Update then Upload Again");
						return new ModelAndView("redirect:exp_excel_faculty_url");
					}
				}
				DynamicMailFormatGen DB = new DynamicMailFormatGen();
////			System.err.println("====HET======"+details.get(i).get("email_id").toString());
	//
//				System.out.println("email sent "+p.getEmail_id());
//			MailDefine ml = new MailDefine();
//			DB.setEmail(p.getEmail_id());
//			ml.setRegistration_header_NCH_Facilty();
//			DB.setMainBody(ml.getRegistration_header_NCH_Facilty());
//			DB.setFooter(ml.getRegistration_body_NCH_Facilty());
//			DB.setRole("NCH");
//			DB.setName(p.getLogin_name());
//			DB.setSubject("Registration Successfull");
//			emailsend.SendMailForAyushEdu(request, DB);
				// for mail send
				// SendRegMail(p.getEmail_id());

			}
			tx.commit();
			ra.addAttribute("msg", "Data Save Successfully");

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
		return new ModelAndView("redirect:exp_excel_faculty_url");
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

	@Autowired
	private DataSource dataSource;

	public String getMaxAID(String userid) {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "select to_char(CURRENT_TIMESTAMP,'yy')||'HOM'||ir.college_unique_id||sm.state_abbr||lpad((select case when (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent )='' or (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent ) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent ) end::int+1)::text, 5, '0')as ayushid from logininformation l\n"
					+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id=l.state_id";

//			query = "select Substring(st.state_name,1,2)||Substring(code,1,4)||EXTRACT(YEAR FROM now())||lpad((select case when (select max(Substring(ayush_id,11,14))\n"
//					+ "from edu_lms_nch_student_details)='' or (select max(Substring(ayush_id,11,14))\n"
//					+ "from edu_lms_nch_student_details) is null  then '0' else (select max(Substring(ayush_id,11,14))\n"
//					+ "from edu_lms_nch_student_details) end::int+1)::text, 4, '0')as max from logininformation l\n"
//					+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?   \n"
//					+ "inner join edu_lms_state_mstr st on st.state_id=l.state_id\n"
//					+ "";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(userid));
			System.out.println("stmt " + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("ayushid");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reg_no;
	}

//	----------------------------------------------------Ayush Id Generate for NCISM---------------------------------------------
	public String getAyushIDNCISM(String userid) {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

//			
//				String query="select to_char(CURRENT_TIMESTAMP,'yy')||'AYU'||'0001'||st.state_abbr||lpad((select case when (select max(Substring(ayush_id,12,5))\n"
//						+ "				from tb_nch_add_teacher_details)='' or (select max(Substring(ayush_id,12,5))\n"
//						+ "				from tb_nch_add_teacher_details) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
//						+ "				from tb_nch_add_teacher_details) end::int+1)::text, 5, '0')as max \n"
//						+ "				from logininformation l\n"
//						+ "				inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?\n"
//						+ "				inner join edu_lms_state_mstr st on st.state_id=l.state_id";

//			String query = "select to_char(CURRENT_TIMESTAMP,'yy')||ir.college_unique_id||sm.state_abbr || lpad((select case when (select max(Substring(ayush_id,12,5))\n"
//					+ "									    from tb_nch_add_teacher_details)='' or (select max(Substring(ayush_id,12,5))\n"
//					+ "										    from tb_nch_add_teacher_details) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
//					+ "									    from tb_nch_add_teacher_details) end::int+1)::text, 5, '0')as max  from logininformation li\n"
//					+ "					inner join edu_lms_institute_reg ir on ir.id=li.institute_id and userid = ?\n"
//					+ "					inner join edu_lms_state_mstr sm on sm.state_id = li.state_id";

			String query = "select to_char(CURRENT_TIMESTAMP,'yy')||ir.college_unique_id||sm.state_abbr || lpad((select case when (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent)='' or (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent) end::int+1)::text, 5, '0')as ayushid  from logininformation li\n"
					+ "inner join edu_lms_institute_reg ir on ir.id=li.institute_id and userid = ?\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id = li.state_id";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(userid));
			System.out.println("stmt  " + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("ayushid");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reg_no;
	}

	// For Email Send

//public void SendRegMail(String email) throws ParseException {
//		
//		System.err.println("mail senttttttttttttttttt");
//		MailHTML html = new MailHTML();
//		try {
//			MimeMessage mimeMessage = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				String text = "Your Registration is Successfull ... Thank you for Registrating with us!";
//				String note = "";
//				html.setHTML(text, note);
//				String htmlMsg = html.getHTML();
//				helper.setText(htmlMsg, true);
//				helper.setTo(email);
//				helper.setSubject("MOA Registration is Successfull");
//				/* helper.setFrom("abc@gmail.com"); */
//				mailSender.send(mimeMessage);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//		}
//	}

///////////////////////-------------------Exp Exc Faculty Data Table----------------///////////////////////////////////////////////

	@PostMapping("/getFilterFaculty_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterFaculty_data(ModelMap model, Principal principal,
			int startPage, int pageLength, String Search, String orderColunm, String orderType, String dob,
			String aadhar_card, String email, String mobile_no, String role_type, String upload_date,
			HttpSession session) {

		System.err.println("//////////////////role_type////////" + role_type);
		String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("rolename").toString();
		// int userid = da.getUsername(username);

		return fdao.DataTableFacultyDataList(startPage, pageLength, Search, orderColunm, orderType, userid, dob,
				aadhar_card, email, mobile_no, role_type, upload_date, role);

	}

	@PostMapping("/getTotalFaculty_dataCount")
	public @ResponseBody long getTotalFaculty_dataCount(ModelMap model, Principal principal, HttpSession sessionUserId,
			String Search, String dob, String aadhar_card, String email, String mobile_no, String role_type,
			String upload_date, HttpSession session) {
		String role = session.getAttribute("rolename").toString();
		// System.err.println("----------------->"+upload_date);
		String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		// int userid = da.getUsername(username);

		return fdao.DataTableFacultyDataTotalCount(Search, userid, dob, aadhar_card, email, mobile_no, role_type,
				upload_date, role);

	}

	@RequestMapping(value = "deleteFac_dtlUrl", method = RequestMethod.POST)
	public ModelAndView deleteFac_dtlUrl(@ModelAttribute("deleteId") int id, HttpSession session, ModelMap model) {

		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from EDU_LMS_FACULTY_NCH set status='2',modified_by=:modified_by,modified_date=:modified_date"
				+ " where userid=:userid  ";

		int app2 = sessionHQL.createQuery(hqlUpdate2).setString("userid", String.valueOf(id))
				.setString("modified_by", username).setDate("modified_date", new Date()).executeUpdate();

//		Query query = sessionHQL.createQuery(
//		   //     "select count(*) from LoginClass login where login.emailid=:email and login.password=:password");
//		"select count(*) from  EDU_LMS_TEACHER_DTL where user_id=:user_id");
//		query.setInteger("user_id", id);
//		Long count = (Long)query.uniqueResult();
//		
//		if(count > 0) {
//			
//			
//			String hqlUpdate3 = "update from EDU_LMS_TEACHER_DTL set status='2',modified_by=:modified_by,modified_date=:modified_date"
//					+ " where user_id=:user_id  ";
//			
//			int app3 = sessionHQL.createQuery(hqlUpdate3).setInteger("user_id",id).setString("modified_by", username)
//					.setDate("modified_date", new Date()).executeUpdate();
//			
//			//System.err.println("in count action ");
//			
//		}

		// System.err.println("count----16/3/22---"+count);

		tx.commit();
		sessionHQL.close();
		if (app2 > 0) {
			liststr.add("Delete Successfully.");
		} else {
			liststr.add("Not Delete.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("redirect:exp_excel_faculty_url");
	}

//	public void testUsingSimpleRegex() {
//	    emailAddress = "username@domain.com";
//	    regexPattern = "^(.+)@(\\S+)$";
//	    assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
//	}
	
	@RequestMapping(value = "/EditFac_dtlUrl", method = RequestMethod.POST)
	public ModelAndView EditFac_dtlUrl(String editId, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {
//		System.err.println("editId---"+editId);
				EDU_LMS_FACULTY_NCH Facdata = fdao.getFacultyDataByid(Integer.parseInt(editId));
				Mmap.addAttribute("msg", msg);
				Mmap.put("Facdata", Facdata);
				Mmap.put("editId",editId);
				
				return new ModelAndView("EditFacultyDataTiles");
	}
	
	@RequestMapping(value = "/Edit_FacDetail_Action", method = RequestMethod.POST)
	public ModelAndView Edit_FacDetail_Action(@ModelAttribute("Edit_FacDetailCMD") EDU_LMS_FACULTY_NCH rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
//				
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("EditFac_dtlUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		String msg2 = "";
		String name = request.getParameter("name");
		String aadhaar = request.getParameter("aadhaar");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String teacher_code = request.getParameter("teachercode");
		String id = request.getParameter("editId");
		String facuserid = request.getParameter("facuserid");
		
		String role = session.getAttribute("rolename").toString();
		String username = session.getAttribute("username").toString();
		 
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (name == null || name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Name");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (validation.maxlengthcheck100(name) == false) {
			ra.addAttribute("msg","Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (validation.isOnlyAlphabet(name) == false) {
			ra.addAttribute("msg","Name "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (aadhaar == null || aadhaar.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Aadhaar Number");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (aadhaar.length() != 12) {
			ra.addAttribute("msg", "Please Enter Valid Aadhaar Number");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (email == null || email.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Email");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (mobile == null || mobile.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Mobile no.");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (mobile.length() != 10) {
			ra.addAttribute("msg", "Please Enter Valid Mobile no.");
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		if (validation.isValidMobileNo(mobile) == false) {
			ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
			return new ModelAndView("redirect:exp_excel_faculty_url");
		}
		
		if(role.equals("Principal_NCISM") || role.equals("Institute_NCISM")) {
			if (teacher_code == null || teacher_code.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Teacher Code");
				return new ModelAndView("redirect:exp_excel_faculty_url");
			}
		}
		
		try {
			Long c = (long) 0;
			
			if(role.equals("Principal_NCISM") || role.equals("Institute_NCISM")) {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_LMS_FACULTY_NCH where (username=:username or aadhar_card=:aadhar_card or email=:email or mobile_no=:mobile_no or teacher_code=:teacher_code) and id !=:id");
				q0.setParameter("username",name);
				q0.setParameter("aadhar_card",aadhaar);
				q0.setParameter("email",email);
				q0.setParameter("mobile_no",mobile);
				q0.setParameter("teacher_code",teacher_code);
				q0.setParameter("id",Integer.parseInt(id));
				
	
				 c = (Long) q0.uniqueResult();
			}
			
			if(role.equals("Principal_NCH") || role.equals("Institute_NCH")) {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_LMS_FACULTY_NCH where (username=:username or aadhar_card=:aadhar_card or email=:email or mobile_no=:mobile_no) and id !=:id");
				q0.setParameter("username",name);
				q0.setParameter("aadhar_card",aadhaar);
				q0.setParameter("email",email);
				q0.setParameter("mobile_no",mobile);
				q0.setParameter("id",Integer.parseInt(id));
				
	
				 c = (Long) q0.uniqueResult();
			}
			
			Query q3 = session1
					.createQuery("select count(*) from UserLogin where (username=:username or aadhar_no=:aadhar_no or email_id=:email or mobile_no=:mobile_no) and userid !=:userid ");
			q3.setParameter("username", aadhaar);
			q3.setParameter("aadhar_no", aadhaar);
			q3.setParameter("email", email);
			q3.setParameter("mobile_no", mobile);
			q3.setParameter("userid", Integer.parseInt(facuserid));

			Long c3 = (Long) q3.uniqueResult();
			
			if (c == 0 && c3 == 0) {
				
				if(role.equals("Principal_NCISM") || role.equals("Institute_NCISM")) {
					String hql = "update EDU_LMS_FACULTY_NCH set username=:username,aadhar_card=:aadhar_card,email=:email,mobile_no=:mobile_no,teacher_code=:teacher_code,modified_by=:modified_by,modified_date=:modified_date where id=:id";

					Query query = session1.createQuery(hql)
							.setParameter("username", name)
							.setParameter("aadhar_card", aadhaar)
							.setParameter("email", email)
							.setParameter("mobile_no", mobile)
							.setParameter("teacher_code", teacher_code)
							.setParameter("modified_date", new Date())
							.setParameter("modified_by", username)
							.setParameter("id", Integer.parseInt(id));
					
					msg = query.executeUpdate() > 0 ? "1" : "0";
				}
				
				if(role.equals("Principal_NCH") || role.equals("Institute_NCH")) {
					String hql = "update EDU_LMS_FACULTY_NCH set username=:username,aadhar_card=:aadhar_card,email=:email,mobile_no=:mobile_no,modified_by=:modified_by,modified_date=:modified_date where id=:id";

					Query query = session1.createQuery(hql)
							.setParameter("username", name)
							.setParameter("aadhar_card", aadhaar)
							.setParameter("email", email.toLowerCase())
							.setParameter("mobile_no", mobile)
							.setParameter("modified_date", new Date())
							.setParameter("modified_by", username)
							.setParameter("id", Integer.parseInt(id));
					
					msg = query.executeUpdate() > 0 ? "1" : "0";
				}
				
				
				
				if(msg.equals("1")) {
				
					String hql2 = "update UserLogin set username=:username,login_name=:login_name,aadhar_no=:aadhar_no,email_id=:email,mobile_no=:mobile_no where userid=:userid";
	
					Query query2 = session1.createQuery(hql2)
							.setParameter("username", aadhaar)
							.setParameter("login_name", name)
							.setParameter("aadhar_no", aadhaar)
							.setParameter("email", email.toLowerCase())
							.setParameter("mobile_no", mobile)
							.setParameter("userid", Integer.parseInt(facuserid));
					
					msg2 = query2.executeUpdate() > 0 ? "1" : "0";
					
				}	
				
				
				if (msg.equals("1") && msg2.equals("1")) {
					tx.commit();
					ra.addAttribute("msg", "Data Updated Successfully");
				} else {
					ra.addAttribute("msg", "Data Not Updated");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist");
			}
		}
		
		finally {
			if (session1 != null) {
				session1.close();
			}
		}
		
		return new ModelAndView("redirect:exp_excel_faculty_url");
	}
	

}
