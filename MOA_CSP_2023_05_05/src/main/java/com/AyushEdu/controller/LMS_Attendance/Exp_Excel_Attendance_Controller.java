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
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Attendance.EDU_LMS_FACULTY_ATTENDANCE;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Report;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Attendance.Exp_Excel_Attendance_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Exp_Excel_Attendance_Controller {
	
	@Autowired
	CommonController common;
	@Autowired
	private Commondao cd;
	@Autowired
	Exp_Excel_Controller expcon;
	@Autowired
	Exp_Excel_Attendance_Dao EEADAO;
	@Autowired
	private UserLoginDAO userLoginDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@GetMapping
	(value = "/exp_attendance_excel_url")
	public ModelAndView exp_attendance_excel_url(HttpServletRequest request,ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg ) {
		
		try {
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("exp_attendance_excel_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			model.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("attendance_excel_Tiles");
   }
	
	@RequestMapping(value = "/Att_Exp_Excel", method = RequestMethod.POST)
	public ModelAndView Att_Exp_Excel(HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra,
			String typeReport1,String typeReportdate) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		System.err.println("roleid================"+role.split("_")[1].toString());
//		Role role= userLoginDAO.findRole_url(role1);
		
		List<ArrayList<String>> listofdata = EEADAO.Exp_Excel_attendance_report(role.split("_")[1].toString(), institute_id);
		model.put("listofdata", listofdata);
		
		ArrayList<ArrayList<String>> listexport=new ArrayList<ArrayList<String>>();
//		System.err.println("listexportttttttttttt"+listexport);
		String attendance_date = request.getParameter("typeReportdate");
//		System.err.println("attendance_date========="+attendance_date);
		
		if (attendance_date == null || attendance_date.trim().equals("0") || attendance_date.equals("dd/mm/yyyy")) {
			ra.addAttribute("msg", "Please Select Date");
			return new ModelAndView("redirect:exp_attendance_excel_url");
		}
		if (validation.isOnlyDateFormat(attendance_date) == false) {
			ra.addAttribute("msg", "Date " + validation.isOnlyDateFormatMSG);
			return new ModelAndView("redirect:exp_attendance_excel_url");
		}
		List<String> TH = new ArrayList<String>();
		TH.add("Name");
		TH.add("Ayush Id");
		TH.add("Teacher Code");
		TH.add("Attendance Date");
		TH.add("Attendance:Drop:" + "P,A");
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Exp_Excel_Report("L", TH,listofdata,attendance_date, Heading, username), "userList", listexport);
	}
	
	@PostMapping(value = "/Upload_Att_action")
	public ModelAndView Upload_Att_action(@Validated @ModelAttribute("Upload_Att_cmd") EDU_LMS_FACULTY_ATTENDANCE skl, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,@RequestParam(value = "u_file1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			//save excel start
				if (upload.isEmpty()) {
					ra.addAttribute("msg", "Please Upload Attendance.");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				
				if (!upload.isEmpty()) {
					 if (upload.getOriginalFilename().split("[.]").length > 2) {
						 ra.addAttribute("msg", "Invalid file extension for Document");
							return new ModelAndView("redirect:exp_attendance_excel_url");
					}
				}
				
				
				File file = new File(expcon.fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
				String extention="";
				int z = upload.getOriginalFilename().lastIndexOf('.');
				if (z >= 0) {
					extention = upload.getOriginalFilename().substring(z + 1);
				}
				if(!extention.equals("xls")) {
					ra.addAttribute("msg", "Please Select Excel File");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				FileInputStream fis = new FileInputStream(file);
				HSSFWorkbook wb = new HSSFWorkbook(fis);
				HSSFSheet sheet = wb.getSheetAt(0);
				Row row_head = sheet.getRow(0);
				if (!row_head.getCell(0).getStringCellValue().equals("Name")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Name");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				if (!row_head.getCell(1).getStringCellValue().equals("Ayush Id")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Ayush Id");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				if (!row_head.getCell(2).getStringCellValue().equals("Teacher Code")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Teacher Code");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				if (!row_head.getCell(3).getStringCellValue().equals("Attendance Date")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Attendance Date");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				if (!row_head.getCell(4).getStringCellValue().equals("Attendance")) {
					ra.addAttribute("msg", "Please Enter Correct File Header for Attendance");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				if (sheet.getLastRowNum() == 0) {
					ra.addAttribute("msg", "Please Enter Data in Atleast One Row");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}
				int id = skl.getId() > 0 ? skl.getId() : 0;
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					System.err.println(i);
					Row row = sheet.getRow(i);
					if (row.getCell(0) == null) {
						break;
					}
					for (int i1 = 0; i1 <= 4; i1++) {
					String varforval = "";
						if(i1==0) {
							varforval = "Name";
						}
						if(i1==1) {
							varforval = "Ayush Id";
						}
						if(i1==2) {
							varforval = "Teacher Code";
						}
						if(i1==3) {
							varforval = "Attendance Date";
						}
						if(i1==4) {
							varforval = "Attendance";
						}
						Cell cell = row.getCell(i1);
						if(cell == null) {
							ra.addAttribute("msg", "Please Enter "+varforval+" in row "+i);
							return new ModelAndView("redirect:exp_attendance_excel_url");
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
							
							skl.setName(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Ayush Id")) {
							skl.setAyush_id(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Teacher Code")) {
							skl.setTeacher_code(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Attendance Date")) {
							System.err.println("value-==================="+value);
//								Date date = (Date) formatter.parse(value);
//								Calendar cal = Calendar.getInstance();
//								cal.setTime(date);
//								System.err.println("date================--------------"+date);
//								String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
//										+ cal.get(Calendar.YEAR);
								skl.setAttendance_date(formatter.parse(value));
						}
						if (row_head.getCell(i1).getStringCellValue().equals("Attendance")) {

							skl.setAttendance(value);
						}
						if (!userid.equals("")) {
							skl.setInstitute_id(ea.getInstitute_id());
						}
						Date date = new Date();
						skl.setUserid(Integer.parseInt(userid));
						skl.setCreated_by(username);
						skl.setCreated_date(date);
					}
//					System.err.println("date======rrrrrrrrrr=========="+skl.getAttendance_date());
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_LMS_FACULTY_ATTENDANCE where attendance_date=:attendance_date and ayush_id=:ayush_id \n")
						.setParameter("attendance_date",skl.getAttendance_date())
						.setParameter("ayush_id", skl.getAyush_id())
						.uniqueResult();
					if(c==0) {
						sessionHQL.save(skl);
						sessionHQL.flush();
						sessionHQL.clear();
					}else {
						ra.addAttribute("msg", "Data Already Exist");
						return new ModelAndView("redirect:exp_attendance_excel_url");
					}
				}
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully");
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
		return new ModelAndView("redirect:exp_attendance_excel_url");
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
}
