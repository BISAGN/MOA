package com.AyushEdu.controller.Question_Bank;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_CHILD;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Question_Bank.Upload_Question_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Upload_QuestionController {
	
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
	private Upload_Question_DAO UQD;
	
	@Autowired
	ValidationController validation;
	

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/QuestionUploadUrl" , method = RequestMethod.GET)
	public ModelAndView QuestionUploadUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("QuestionUploadUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			Mmap.put("getSystemList", cd.getinstitute_system(Integer.parseInt(institute_id)));

			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
			Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory));
//			Mmap.put("getselectedcoursename", edao.getselectedcoursename(Integer.parseInt(userid)));
			Mmap.put("getSetList",common.getSetList(sessionFactory));
			Mmap.put("getModuleList",common.getModuleList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("QueUploadTiles");
	}

	@RequestMapping(value = "/question_act", method = RequestMethod.POST)
	 public ModelAndView question_act(@ModelAttribute("Mpet_cmd") EDU_LMS_QUESTION_BAND_PARENT que_mst, BindingResult result,RedirectAttributes ra,
				@RequestParam(value = "fig_a", required = false) MultipartFile fig_a,
			@RequestParam(value = "fig_b", required = false) MultipartFile fig_b,
			@RequestParam(value = "fig_c", required = false) MultipartFile fig_c,
			@RequestParam(value = "fig_d", required = false) MultipartFile fig_d,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request,	 ModelMap model,HttpSession session)throws IOException, ParseException {
		
		try {
			
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("QuestionUploadUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			int id = Integer.parseInt(request.getParameter("id"));
			Date date = new Date();

			String username = session.getAttribute("username").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			String system_id= request.getParameter("system_id");
			String course_id=request.getParameter("course_id");
			String set_id= request.getParameter("set_id");
			String level_id=request.getParameter("level_id");
			String type_id=request.getParameter("type_id");
			String question=request.getParameter("question");
			String marks=request.getParameter("marks");
			String time=request.getParameter("time");
			
			String module_id=request.getParameter("module_id");
			String exam_name=request.getParameter("exam_name");
//			String status=request.getParameter("status");
			
			if (system_id == null || system_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select System.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (course_id == null || course_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Course.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (set_id == null || set_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Set.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (module_id == null || module_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Module.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}

			if (exam_name == null || exam_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Exam Name.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (validation.minlength10(exam_name) == true) {
				ra.addAttribute("msg", "Exam_name " + validation.MinlengthMSG10);
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			
			if (marks == null || marks.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Marks.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			
			if (validation.isOnlyNumer(marks) == true) {
				ra.addAttribute("msg", "Marks " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (validation.maxlengthcheck2(marks) == false) {
				ra.addAttribute("msg", "Marks " + validation.MaxlengthcheckMSG2);
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
//			if (validation.isOnlyNumer(marks) == true) {
//				ra.addAttribute("msg", "Marks " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:QuestionUploadUrl");
//			}
			if (time == null || time.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Time.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (validation.maxlengthcheck5Digit1(time) == false) {
				ra.addAttribute("msg", "Time " + validation.MaxlengthcheckMSG5Digit1);
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			
			if (level_id == null || level_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Difficulty Level.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			if (type_id == null || type_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Type.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}
			
			if (question == null || question.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Question.");
				return new ModelAndView("redirect:QuestionUploadUrl");
			}

			
			try {

				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from EDU_LMS_QUESTION_BAND_PARENT where system_id=:system_id and course_id=:course_id and "
						+ "set_id=:set_id and level_id=:level_id and type_id=:type_id and question=:question and marks=:marks and time=:time and module_id=:module_id and "
						+ " exam_name=:exam_name and id!=:id")
						
						.setParameter("system_id", que_mst.getSystem_id())
						.setParameter("course_id", que_mst.getCourse_id())
						.setParameter("set_id", que_mst.getSet_id())
						.setParameter("level_id", que_mst.getLevel_id())
						.setParameter("type_id", que_mst.getType_id())
						.setParameter("question", que_mst.getQuestion())
						.setParameter("marks", que_mst.getMarks())
						.setParameter("time", que_mst.getTime())
						.setParameter("module_id", que_mst.getModule_id())
						.setParameter("exam_name", que_mst.getExam_name())
						.setParameter("id", id).uniqueResult();

				if (id == 0) {
					que_mst.setCreated_by(username);
					que_mst.setCreated_date(date);
//					que_mst.setType_id(type_id);
					que_mst.setStatus("A");
					if (c == 0) {
						
//						String que_upload_a="";
//						if(!que_upload.isEmpty()) {
//							que_upload_a= fileupload(que_upload.getBytes(), que_upload.getOriginalFilename(), id,
//									"FigureD");
//							
//							if (que_upload_a != "") {
//								que_mst.setQuestion_upload(que_upload_a);
//							}
//						}
						
						String answer1 = request.getParameter("answer1");
						
						int bmgmtid = common.saveFunction(que_mst,sessionFactory);
						
						String count_img=request.getParameter("count_img");
						String correct_ans=request.getParameter("correct_ans");
					
						for (int i=1;i<=Integer.parseInt(count_img);i++) {
							String member = request.getParameter("answer"+i);
							Session sessionHQL1 = this.sessionFactory.openSession();
							Transaction tx1 = sessionHQL1.beginTransaction();
							
							if (member == null || member.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Answer "+i);
								return new ModelAndView("redirect:QuestionUploadUrl");
							}
							if (validation.maxlengthcheck_50(member) == false) {
								ra.addAttribute("msg", "Answer " + validation.MaxlengthcheckMSG_50);
								return new ModelAndView("redirect:QuestionUploadUrl");
							}
							
							EDU_LMS_QUESTION_BAND_CHILD bmgmtch = new EDU_LMS_QUESTION_BAND_CHILD();
//							String member = request.getParameter("answer"+i);
							
//							if(correct_ans.equals("answer"+i)) {
								bmgmtch.setCorrect_ans(request.getParameter(request.getParameter("correct_ans")));
//							}
//							else {
//								bmgmtch.setCorrect_ans("0");
//							}
							bmgmtch.setAnswer(member);
							bmgmtch.setP_id(bmgmtid);
							bmgmtch.setCreated_by(username);
							bmgmtch.setCreated_date(date);
							
							sessionHQL1.save(bmgmtch);
							sessionHQL.flush();
							sessionHQL.clear();
							tx1.commit();
						}
//						sessionHQL.save(que_mst);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Save Successfully");

					} else {
						
						model.put("msg", "Data already Exist.");
					}
				} 
//				else {
//
//					que_mst.setModified_by(username);
//					que_mst.setModified_date(date);
//					if (c == 0) {
//						String msg = Quiz_dao.updateque(que_mst);
//						model.put("msg", msg);
//					} else {
//						model.put("msg", "Data already Exist.");
//					}
//				}
				tx.commit();
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:QuestionUploadUrl");
	}
	
	// ====================== Used For File Upload function
			public String fileupload(byte[] b, String name, int id, String type) {
				String extension = "";
				String fname = "";
				try {
					byte[] bytes = b; // file.getBytes();
					// Creating the directory to store file
					//String rootPath = System.getProperty("catalina.home");
//					File dir = new File(rootPath + File.separator + "CRPF_Document//" + id);
					 File dir = new File("/srv/CRPF_Document/"+id);
					if (!dir.exists())
						dir.mkdirs();

					String filename = name; // file.getOriginalFilename();

					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}

					fname = dir.getAbsolutePath() + File.separator + "" + type + "__" + currentDateWithTimeStampString() + "."
							+ extension;
					
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				return fname;
			}
			public String currentDateWithTimeStampString() {
				java.util.Date date = new java.util.Date();
				Timestamp ts = new Timestamp(date.getTime());
				return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
			}
			
		@SuppressWarnings("deprecation")
		@PostMapping(value = "/admin/getSystemlistFromCourse")
		public @ResponseBody List<Map> getSystemlistFromCourse( String system_id) {
		
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				try {
					
					Query q1=sessionHQL.createQuery("select cast(ec.elec_course_id as text),cast(elc.course_name as text) from EDU_LMS_SYSTEM_ELE_COURSE_LINK ec,"
							+ "EDU_LMS_ELECTIVE_COURSE_MASTER elc where  cast(elc.id as text) = cast(ec.elec_course_id as text) and ec.system_id=:id");
					
					q1.setString("id",system_id);
					@SuppressWarnings("unchecked")
					List<Map> list = (List<Map>) q1.list();
					tx.commit();
					
					return list;

				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
		@SuppressWarnings("deprecation")
		@PostMapping(value = "/admin/getSetlistFromCourse")
		public @ResponseBody List<Map> getSetlistFromCourse( String course_id) {
			System.err.println("-------sysytem"+course_id);
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				try {
					
					Query q1=sessionHQL.createQuery("select distinct cast(cs.set_id as text),cast(c.setname as text) from  EDU_LMS_LINK_COURSE_SET_MSTR cs,\n"
							+ "TB_SET_MASTER c where cast(c.id as text) = cast(cs.set_id as text) and cs.course_id=:id");
					
					q1.setInteger("id",Integer.parseInt(course_id));
					@SuppressWarnings("unchecked")
					List<Map> list = (List<Map>) q1.list();
					
					tx.commit();
					
					return list;

				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
		
////////////////MEERA_24_05
		@SuppressWarnings("deprecation")
		@PostMapping(value = "/admin/getModulelistFromSet")
		public @ResponseBody List<Map> getModulelistFromSet(String set_id,String course_id) {
			System.err.println("-------sysytem"+set_id);
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				try {
					
					Query q1=sessionHQL.createQuery("select distinct cast(cs.module_id as text),cast(m.module_name as text) from  EDU_LMS_LINK_COURSE_SET_MSTR cs,\n"
							+ "EDU_LMS_MODULE_MSTR m where cast(m.id as text) = cast(cs.module_id as text) and cs.course_id=:course_id and cs.set_id=:id");
					
					q1.setInteger("id",Integer.parseInt(set_id));
					q1.setInteger("course_id",Integer.parseInt(course_id));
					@SuppressWarnings("unchecked")
					List<Map> list = (List<Map>) q1.list();
					System.err.println("--set---------------"+list);
					tx.commit();
					
					return list;

				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
		
		
		///=============filter search===================================
		
		@PostMapping("/getFilterQuestion_Bank_data")
		public @ResponseBody List<Map<String, Object>> getFilterQuestion_Bank_data(HttpSession sessionUserId,int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String system_id,String course_id, String set_id, String module_id,
				String exam_name, String marks, String time,String type_id, String level_id) {
			String role = sessionUserId.getAttribute("role").toString();

			return UQD.DataTableQuestion_BankDataList(sessionUserId,startPage, pageLength, Search, orderColunm, orderType, system_id, course_id,
					set_id, module_id, exam_name, marks, time, type_id,level_id,role);
			

		}
		@PostMapping("/getTotalQuestion_Bank__dataCount")
		public @ResponseBody long getTotalQuestion_Bank__dataCount(HttpSession sessionUserId, String Search, String system_id,String course_id, String set_id, String module_id,
				String exam_name, String marks, String time,String type_id, String level_id) {
			String role = sessionUserId.getAttribute("role").toString();

			return UQD.DataTableQuestion_BankDataTotalCount(Search, system_id, course_id,
					set_id, module_id, exam_name, marks, time, type_id, level_id,role);
		}
			
}
