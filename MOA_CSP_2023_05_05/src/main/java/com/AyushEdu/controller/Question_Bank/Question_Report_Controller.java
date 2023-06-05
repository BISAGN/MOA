package com.AyushEdu.controller.Question_Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_SYSTEM_COURSE_DURATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Question_Bank.Question_Report_Dao;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_PARENT;
import com.AyushEdu.Models.QuizBank.EDU_LMS_QUESTION_BAND_CHILD;



@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Question_Report_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	@Autowired
	ValidationController validation;

	@Autowired
	Question_Report_Dao pdao;

	@Autowired
	Commondao cd;
	private Date date;
	
	// ==========================================OPEN PAGE
		// SYSTEM==========================================

		@RequestMapping(value = "/Question_ReportUrl", method = RequestMethod.GET)
		public ModelAndView Question_ReportUrl(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			
			try {
//				SECURITY -- RIDDHI 
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

			return new ModelAndView("Question_ReportTiles");

		}


		
		@PostMapping("/getFilterQuestion_Report_data")
		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String course_id, String set_id, String module_id) {

			return pdao.DataTableQuestion_ReportDataList(startPage, pageLength, Search, orderColunm, orderType,course_id,set_id,module_id
					);
		}

		@PostMapping("/getTotalQuestion_Report_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String orderType,String course_id, String set_id, String module_id) {
			return pdao.DataTableQuestion_ReportDataTotalCount(Search, orderType,course_id,set_id,module_id);

		}

		
//		@RequestMapping(value = "/UploadPaper_Excel", method = RequestMethod.POST)
//		public ModelAndView UploadPaper_Excel(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1
//				) {
//																																						
//			ArrayList<ArrayList<String>> listexport=new ArrayList<ArrayList<String>>();
//			List<String> TH = new ArrayList<String>();
////			TH.add("no.");
//			TH.add("question");
//			TH.add("option_a");
//			TH.add("option_b");
//			TH.add("option_c");
//			TH.add("option_d");
//			TH.add("correct answer:Drop:"+"a,b,c,d");
//			TH.add("difficult_level:Drop:"+"low,medium,high");
//			TH.add("marks");
//			TH.add("time");
////			TH.add("course_id");
////			TH.add("set_id");
////			TH.add("module_id");
//			TH.add("exam_name");
//			
//			
//			String Heading = "\nCope Code";
//			String username = session.getAttribute("username").toString();
//			return new ModelAndView(new GenPaperExcel_Controller("L", TH, Heading, username), "userList", listexport);
//		}
		
		@RequestMapping(value = "/getcourselistby_set2", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_SYSTEM_COURSE_DURATION> getcourselistby_set2(String course_id)  {
			List<EDU_LMS_SYSTEM_COURSE_DURATION> list =  common.getCourselistbySet(sessionFactory,course_id);
		
			return list;
		}
		
		@RequestMapping(value = "/getcourselistby_module2", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_MODULE_MSTR> getcourselistby_module2(String course_id)  {
			List<EDU_LMS_MODULE_MSTR> list =  common.getCourselistbyModule(sessionFactory,course_id);
			return list;
		}



		
	}


