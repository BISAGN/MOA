package com.AyushEdu.controller.LMS_Attendance;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.AyushEdu.Models.Attendance.EDU_LMS_STUDENT_ATTENDANCE_REPORT;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_DETAILS_A;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Student_Attendance_Report;
import com.AyushEdu.controller.Exp_Excel.GenExcel_Contoller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Attendance.Exp_Excel_Student_Attendance_Dao;
import com.AyushEdu.dao.Mentor_Mentee.Mentor_Mentee_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Excel_Student_AttendanceController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	@Autowired
	private Commondao cd;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	Exp_Excel_Student_Attendance_Dao EESADAO;
	@Autowired
	Mentor_Mentee_DAO mdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@GetMapping(value = "/Excel_student_attend_url")
	public ModelAndView Excel_student_attend_url(HttpServletRequest request,ModelMap model, HttpSession session, String course_id,
			@RequestParam(value = "msg", required = false) String msg) {

		try {
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Excel_student_attend_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			model.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Excel_Student_AttendTiles");
	}

	@RequestMapping(value = "/Student_Atten_Excel", method = RequestMethod.POST)
	public ModelAndView Student_Atten_Excel(HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra, String typeReport1, String typeReportdate, String typeReportcourse, String professional_hid) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String role = session.getAttribute("rolename").toString();
		String role_id = session.getAttribute("roleid").toString();
		
		String attendance_date = request.getParameter("typeReportdate");
		String professional_id = request.getParameter("professional_hid");
		System.err.println("attendance_dateeeeeeeeeeeeeeeeeeeee=========" + attendance_date);
		System.err.println("professional_hid1=========" + professional_id);

		List<ArrayList<String>> listofdata = EESADAO.Exp_Excel_Student_attendance(institute_id, userid,role.split("_")[1].toString(),"",professional_hid);
		model.put("listofdata", listofdata);
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		List<String> TH = new ArrayList<String>();

		
		int id = Integer.parseInt(request.getParameter("typeReportcourse"));
//		System.err.println("course====id=====" + id);
		String course = (String) sessionHQL
				.createQuery("select course_name from EDU_LMS_COURSE_MASTER where id = :id group by course_name" + "")
				.setParameter("id", (id)).setMaxResults(1).uniqueResult();
		model.put("course", course);

//		System.err.println("course ============" + course);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Professional");
			return new ModelAndView("redirect:Excel_student_attend_url");
		}
		
		if (attendance_date == null || attendance_date.trim().equals("0") || attendance_date.equals("dd/mm/yyyy")) {
			ra.addAttribute("msg", "Please Select Date");
			return new ModelAndView("redirect:Excel_student_attend_url");
		}
		if (validation.isOnlyDateFormat(attendance_date) == false) {
			ra.addAttribute("msg", "Date " + validation.isOnlyDateFormatMSG);
			return new ModelAndView("redirect:Excel_student_attend_url");
		}
		if (id == 0) {
			ra.addAttribute("msg", "Please Select Course");
			return new ModelAndView("redirect:Excel_student_attend_url");
		}
		TH.add("NAME");
		TH.add("AYUSH ID");
		TH.add("ATTENDANCE DATE");
		TH.add("ATTENDANCE:Drop:" + "P,A");
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Exp_Excel_Student_Attendance_Report("L", TH, listofdata, attendance_date, course,
				Heading, username), "userList", listexport);
	}
	
	@PostMapping(value = "/Student_Attendance_action")
	public ModelAndView Student_Attendance_action(
			@Validated @ModelAttribute("Student_Attendance_cmd") EDU_LMS_STUDENT_ATTENDANCE_REPORT skl,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,String typeReportcourse,
			RedirectAttributes ra, @RequestParam(value = "u_file1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String attendance_date = request.getParameter("typeReportdate");
		System.err.println("attendance_dateaction=========" + attendance_date);
		
		try {
			// save excel start
			if (upload.isEmpty()) {
				ra.addAttribute("msg", "Please Upload Attendance.");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			
			if (!upload.isEmpty()) {
				 if (upload.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:Excel_student_attend_url");
				}
			}
			
			
			
			File file = new File(expcon.fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));

			String extention = "";
			int z = upload.getOriginalFilename().lastIndexOf('.');
			if (z >= 0) {
				extention = upload.getOriginalFilename().substring(z + 1);
			}
			if (!extention.equals("xls")) {
				ra.addAttribute("msg", "Please Select Excel File");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row_head = sheet.getRow(0);

			if (!row_head.getCell(0).getStringCellValue().equals("NAME")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Name");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			if (!row_head.getCell(1).getStringCellValue().equals("AYUSH ID")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Ayush Id");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			if (!row_head.getCell(2).getStringCellValue().equals("ATTENDANCE DATE")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Attendance Date");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			if (!row_head.getCell(3).getStringCellValue().equals("ATTENDANCE")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Attendance");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			if (sheet.getLastRowNum() == 0) {
				ra.addAttribute("msg", "Please Enter Data in Atleast One Row");
				return new ModelAndView("redirect:Excel_student_attend_url");
			}
			int id = skl.getId() > 0 ? skl.getId() : 0;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row.getCell(0) == null) {
					break;
				}
//				String Attdate = "";
				for (int i1 = 0; i1 <= 3; i1++) {

					String varforval = "";
					if (i1 == 0) {
						varforval = "NAME";
					}
					if (i1 == 1) {
						varforval = "AYUSH ID";
					}
					if (i1 == 2) {
						varforval = "ATTENDANCE DATE";
					}
					if (i1 == 3) {
						varforval = "ATTENDANCE";
					}

					Cell cell = row.getCell(i1);
					if (cell == null) {
						ra.addAttribute("msg", "Please Enter " + varforval + " in row " + i);
						return new ModelAndView("redirect:Excel_student_attend_url");
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
					if (row_head.getCell(i1).getStringCellValue().equals("NAME")) {
						skl.setName(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("AYUSH ID")) {
						skl.setAyush_id(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("ATTENDANCE DATE")) {
						System.err.println("value-===================" + value);
						Date date = (Date) formatter.parse(value);
//						Attdate = value;
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
								+ cal.get(Calendar.YEAR);
						skl.setAttendance_date(date);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("ATTENDANCE")) {

						skl.setAttendance(value);
					}
					if (!userid.equals("")) {
						skl.setInstitute_id(ea.getInstitute_id());
					}
//					System.out.print("aaaaaaaaaaaaaaaa"+wb.getSheetName(0)+ " ");
					 int sheetcount = wb.getNumberOfSheets();
					    System.out.println("sheetcount: "+sheetcount);
					    for(int i11=0; i11<sheetcount;i11++)
					    {
					        System.out.print(wb.getSheetName(i11)+ " ");
					        String course_sheet = "";
							course_sheet=wb.getSheetName(i11);
							ArrayList<ArrayList<String>> getCourseidByCoursenameid = EESADAO.getCourseidByCoursename(course_sheet);
							System.err.println("getCourseidByCoursename============="+getCourseidByCoursenameid.get(0).get(0));
//							skl.setParameter("course_id", getCourseidByCoursename.get(0).get(0));
							skl.setCourse_name(getCourseidByCoursenameid.get(0).get(0));
					    }
					Date date = new Date();
					skl.setCreated_by(username);
					skl.setCreated_date(date);
				
				}
				
//				System.err.println("date======rrrrrrrrrr==========" + attendance_date);
//				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_STUDENT_ATTENDANCE_REPORT where attendance_date=:attendance_date and course_name=:course and ayush_id=:ayush_id \n")
						.setParameter("attendance_date", skl.getAttendance_date()).setParameter("course", skl.getCourse_name()).setParameter("ayush_id", skl.getAyush_id())
						.uniqueResult();
				
				System.err.println("c==="+c);
				
				if (c == 0) {
					sessionHQL.save(skl);
					sessionHQL.flush();
					sessionHQL.clear();
				} else {
					ra.addAttribute("msg", "Data Already Exist");
					return new ModelAndView("redirect:Excel_student_attend_url");
				}
			}
			tx.commit();
			ra.addAttribute("msg", "Data Saved Successfully");

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Excel_student_attend_url");
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

	@GetMapping(value = "/Search_Student_Atten_report_url")
	public ModelAndView Search_Student_Atten_report_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.put("msg", msg);
		return new ModelAndView("Student_Atten_report_Tiles");
	}

	@RequestMapping(value = "/getCourseListbyDate", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_COURSE_MASTER> getCourseListbyDate(String attendance_date,HttpSession session,String system_id) throws ParseException {
		
		String role = session.getAttribute("rolename").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		 Session sessiont = sessionFactory.openSession();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
		
		Query q = sessionHQL.createQuery(
				"select cm.id,cm.course_name from EDU_LMS_COURSE_MASTER cm, EDU_TT_TIME_TABLE_MASTER ttd , \n"
				+ " EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE lc where ttd.course=cm.id and lc.course_id=cm.id \n"
				+"and ttd.ldate=:ldate and lc.system_id=:system_id and ttd.institute_id=:institute_id and ttd.faculty=:faculty\n"
				+"group by cm.id,course_name ");

		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = (Date) formatter.parse(attendance_date);
		ArrayList<ArrayList<String>> systemofstud = EESADAO.systemofstud_excel(userid);
		q.setParameter("system_id", Integer.parseInt(systemofstud.get(0).get(0)));
		q.setParameter("ldate", date);
		q.setParameter("institute_id", institute_id);
		q.setParameter("faculty", Integer.parseInt(userid));
		
//		System.err.println(systemofstud.get(0).get(0)+"---"+date+"---"+institute_id+"---"+userid);
		
		@SuppressWarnings("unchecked")
		List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
}
