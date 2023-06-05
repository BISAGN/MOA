package com.AyushEdu.controller.Question_Bank;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_SYSTEM_COURSE_DURATION;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_CHILD;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Upload_Paper_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	CommonController common;
	
	@Autowired
	private Commondao cd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping
	(value = "/Upload_Paper_url")
	public ModelAndView Upload_Paper_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Upload_Paper_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			model.put("msg", msg);
			Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			model.put("getCourse_upload_Paper", cd.getCourse_upload_Paper());
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("Upload_PaperTiles");
   }
	
	@PostMapping(value = "/Upload_Paper_action")
	public ModelAndView Upload_Paper_action(@Validated @ModelAttribute("Upload_Paper_cmd") EDU_LMS_QUESTION_BAND_PARENT skl, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,@RequestParam(value = "u_file1", required = false) MultipartFile upload,
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	
		// check for list empty
//		UserLogin p=new UserLogin();
		
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Upload_Paper_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String course_id=request.getParameter("course_id");
			String set_id=request.getParameter("set_id");
			String module_id=request.getParameter("module_id");
			
			
			if (course_id == null || course_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Course");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (set_id == null || set_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Set");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (module_id == null || module_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Module");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (upload.isEmpty()) {
				ra.addAttribute("msg", "Please Upload File.");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			
			if (!upload.getOriginalFilename().isEmpty()) {
				if (upload.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:Upload_Paper_url");
				}
			}
			
			
			
			
		
		int count_duplicate=0;
		String board_id = "";
		int id = skl.getId() > 0 ? skl.getId() : 0;
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fis); 
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row_head = sheet.getRow(0);
		
			if (!row_head.getCell(0).getStringCellValue().equals("question")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Question");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(1).getStringCellValue().equals("option_a")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Option A");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(2).getStringCellValue().equals("option_b")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Option B");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(3).getStringCellValue().equals("option_c")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Option C");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(4).getStringCellValue().equals("option_d")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Option D");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			
			if (!row_head.getCell(5).getStringCellValue().equals("correct answer")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Correct Answer");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(6).getStringCellValue().equals("difficult_level")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Difficult Level");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(7).getStringCellValue().equals("marks")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Marks");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(8).getStringCellValue().equals("time")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Time");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			if (!row_head.getCell(9).getStringCellValue().equals("exam_name")) {
				ra.addAttribute("msg", "Please Enter Correct File Header for Exam Name");
				return new ModelAndView("redirect:Upload_Paper_url");
			}
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Row row = sheet.getRow(i);
				if (row.getCell(0) == null) {
					break;
				}
				String op1="";
				String op2="";
				String op3="";
				String op4="";
				String corre_ans="";
//				String course_id="";
//				String module_id="";
//				String set_id="";
				String exam_name="";
				
				EDU_LMS_QUESTION_BAND_CHILD c = new EDU_LMS_QUESTION_BAND_CHILD();
				
					for (int i1 = 0; i1 < 10; i1++) {
						
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
						case Cell.CELL_TYPE_FORMULA:
							value = String.valueOf(cell.getCellFormula());
							break;
						default:
						}
						
						if (row_head.getCell(i1).getStringCellValue().equals("question")) {
							System.out.println("Question  "+value);
							skl.setQuestion(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("marks")) {
							skl.setMarks(value);
						}
						if (row_head.getCell(i1).getStringCellValue().equals("time")) {
							skl.setTime(value);
						}
						System.out.println("row_head.getCell(i1).getStringCellValue() "+row_head.getCell(i1).getStringCellValue());
						
						if (row_head.getCell(i1).getStringCellValue().equals("difficult_level")) {
//							Low,Medium,High
							if(value.equals("high")) 
								skl.setLevel_id(3);
							else if(value.equals("medium")) 
								skl.setLevel_id(2);
							else if (value.equals("low"))
								skl.setLevel_id(1);
							
						}
						if (row_head.getCell(i1).getStringCellValue().equals("option_a")) {
							op1=value;
							
						}
						if (row_head.getCell(i1).getStringCellValue().equals("option_b")) {
							op2=value;
						}
						if (row_head.getCell(i1).getStringCellValue().equals("option_c")) {
							op3=value;
						}
						if (row_head.getCell(i1).getStringCellValue().equals("option_d")) {
							op4=value;
						}
						if (row_head.getCell(i1).getStringCellValue().equals("correct answer")) {
							corre_ans=value;
							
							System.err.println("cor ans value"+value);
						}
						
//						if (row_head.getCell(i1).getStringCellValue().equals("course_id")) {
//							skl.setCourse_id(Integer.parseInt(value));
//							course_id=value;
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("set_id")) {
//							skl.setSet_id(Integer.parseInt(value));
//							set_id=value;
//						}
//						if (row_head.getCell(i1).getStringCellValue().equals("module_id")) {
//							skl.setModule_id(Integer.parseInt(value));
//							module_id=value;
//							
//						}
						
						if (row_head.getCell(i1).getStringCellValue().equals("exam_name")) {
							skl.setExam_name(value);
							exam_name=value;
						}
					}
					skl.setCourse_id(Integer.parseInt(course_id));
					skl.setSet_id(Integer.parseInt(set_id));
					skl.setModule_id(Integer.parseInt(module_id));
					
					int mid = (int) sessionHQL.save(skl);
//					sessionHQL.flush();
//					sessionHQL.clear();
					tx.commit();
					
					////////////Child save
//					for (int j1 = 1; j1 <= 5; j1++) {
						
						Session sessionHQL1 = this.sessionFactory.openSession();
						Transaction tx1 = sessionHQL1.beginTransaction();
						c.setAnswer(op1);
						if(corre_ans.equals("a")) {
						c.setCorrect_ans(op1);
						}
						if(corre_ans.equals("b")) {
							c.setCorrect_ans(op2);
							}
						if(corre_ans.equals("c")) {
							c.setCorrect_ans(op3);
							}
						if(corre_ans.equals("d")) {
							c.setCorrect_ans(op4);
							}
						c.setP_id(mid);
						sessionHQL1.save(c);
						sessionHQL1.flush();
						sessionHQL1.clear();
						tx1.commit();
						
						Session sessionHQL2 = this.sessionFactory.openSession();
						Transaction tx2 = sessionHQL2.beginTransaction();
						c.setAnswer(op2);
						if(corre_ans.equals("a")) {
						c.setCorrect_ans(op1);
						}
						if(corre_ans.equals("b")) {
							c.setCorrect_ans(op2);
							}
						if(corre_ans.equals("c")) {
							c.setCorrect_ans(op3);
							}
						if(corre_ans.equals("d")) {
							c.setCorrect_ans(op4);
							}
						c.setP_id(mid);
						sessionHQL2.save(c);
						sessionHQL2.flush();
						sessionHQL2.clear();
						tx2.commit();
						
						Session sessionHQL3 = this.sessionFactory.openSession();
						Transaction tx3 = sessionHQL3.beginTransaction();
						c.setAnswer(op3);
						if(corre_ans.equals("a")) {
							
							
						c.setCorrect_ans(op1);
						}
						if(corre_ans.equals("b")) {
							c.setCorrect_ans(op2);
							}
						
						System.err.println("corre_ans"+corre_ans);
						if(corre_ans.equals("c")) {
							c.setCorrect_ans(op3);
							}
						if(corre_ans.equals("d")) {
							c.setCorrect_ans(op4);
							}
						c.setP_id(mid);
						sessionHQL3.save(c);
						sessionHQL3.flush();
						sessionHQL3.clear();
						tx3.commit();
						
						Session sessionHQL4 = this.sessionFactory.openSession();
						Transaction tx4 = sessionHQL4.beginTransaction();
						c.setAnswer(op4);
						if(corre_ans.equals("a")) {
						c.setCorrect_ans(op1);
						System.err.println(op1);
						
						}
						if(corre_ans.equals("b")) {
							c.setCorrect_ans(op2);
							System.err.println(op2);
							}
						if(corre_ans.equals("c")) {
							c.setCorrect_ans(op3);
							System.err.println(op3);
							}
						if(corre_ans.equals("d")) {
							c.setCorrect_ans(op4);
							System.err.println(op4);
							}
						c.setP_id(mid);
						sessionHQL4.save(c);
						sessionHQL4.flush();
						sessionHQL4.clear();
						tx4.commit();
					
						ra.addAttribute("msg", "Data Save Successfully");
//						Cell cell = row.getCell(j1);
//						
//						String value = "";
//						switch (cell.getCellType()) {
//						case Cell.CELL_TYPE_STRING:
//							value = cell.getStringCellValue();
//							break;
//						case Cell.CELL_TYPE_NUMERIC:
//							if (HSSFDateUtil.isCellDateFormatted(cell)) {
//								value = String.valueOf(cell.getDateCellValue());
//							} else {
//								value = String.valueOf((long) cell.getNumericCellValue());
//							}
//							break;
//						case Cell.CELL_TYPE_BOOLEAN:
//							value = String.valueOf(cell.getBooleanCellValue());
//							break;
//						default:
//						}
//						
//						if (row_head.getCell(j1).getStringCellValue().equals("answer")) {
//							c.setAnswer(value);
//						}
//						if (row_head.getCell(j1).getStringCellValue().equals("correct_ans")) {
//							c.setCorrect_ans(value);
//						}
//						
////						if (row_head.getCell(i1).getStringCellValue().equals("correct_ans")) {
////							c.setCorrect_ans(value);
////							
////						}
//						int did = (Integer) sessionHQL.save(c);
////						sessionHQL.save(c);
//						sessionHQL.flush();
//						sessionHQL.clear();
//						ra.addAttribute("msg", "Data Save Successfully");
//						
//					}
//					tx.commit();
///////////////////////////////////////////
					
			}
//			tx.commit();
			System.out.println("headwer "+row_head.getCell(1).getStringCellValue());
		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
		}
		return new ModelAndView("redirect:Upload_Paper_url");
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
	
	@RequestMapping(value = "/UploadPaper_Excel", method = RequestMethod.POST)
	public ModelAndView UploadPaper_Excel(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1
			) {
																																					
		ArrayList<ArrayList<String>> listexport=new ArrayList<ArrayList<String>>();
		List<String> TH = new ArrayList<String>();
//		TH.add("no.");
		TH.add("question");
		TH.add("option_a");
		TH.add("option_b");
		TH.add("option_c");
		TH.add("option_d");
		TH.add("correct answer:Drop:"+"a,b,c,d");
		TH.add("difficult_level:Drop:"+"low,medium,high");
		TH.add("marks");
		TH.add("time");
//		TH.add("course_id");
//		TH.add("set_id");
//		TH.add("module_id");
		TH.add("exam_name");
		
		
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new GenPaperExcel_Controller("L", TH, Heading, username), "userList", listexport);
	}
	
	@RequestMapping(value = "/getcourselistby_set", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_COURSE_DURATION> getcourselistby_set(String course_id)  {
		List<EDU_LMS_SYSTEM_COURSE_DURATION> list =  common.getCourselistbySet(sessionFactory,course_id);
	
		return list;
	}
	
	@RequestMapping(value = "/getcourselistby_module", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_MODULE_MSTR> getcourselistby_module(String course_id)  {
		List<EDU_LMS_MODULE_MSTR> list =  common.getCourselistbyModule(sessionFactory,course_id);
		return list;
	}

}
