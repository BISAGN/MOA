package com.AyushEdu.controller.Question_Bank;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Query;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.QuizBank.EDU_LMS_EXAM_PAPER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Question_Bank.Exam_Paper_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Exam_PaperController {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	CommonController common;
	
	@Autowired
	Exam_Paper_DAO edao;
	private String userid;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "/Exam_Paper_url")
	public ModelAndView Exam_Paper_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "course_id1", required = false) String course_id,
			 @RequestParam(value = "module_id1", required = false) String module_id,HttpServletRequest request ) {
		
		try {
			
					
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Paper_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		String userid = session.getAttribute("userId_for_jnlp").toString();

		model.put("msg", msg); 
		model.put("course_id", course_id);
		model.put("module_id", module_id);
//		model.put("getquestion", edao.getquestion());
		model.put("getSelectedCourseSetbyStudent", edao.getSelectedCourseSetbyStudent(userid));
		model.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
		model.put("getModuleName", common.getModuleName(sessionFactory));
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		if (module_id==null || module_id.equals("")) {
			list = edao.coursenamelistofResult(userid);
		}
		else {
			list = edao.coursenamelist(userid,module_id);
		}
		
		
		ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
		ArrayList<String> list3 = new ArrayList<String>();
		
		for(int i=0;i<list.size();i++) {
			list3 = edao.method2(userid,list.get(i).get(0),list.get(i).get(1));
			if(!list3.isEmpty()) {
				list2.add(list3);
			}
		}
		model.put("courselist", list2);
		System.out.println("list2  "+list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Exam_Paper_Tiles");
  }
	
	@PostMapping(value = "/Exam_Paper_action")
	public ModelAndView Exam_Paper_action(
			@Validated @ModelAttribute("Exam_Paper_cmd") EDU_LMS_EXAM_PAPER elmp, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra
			) throws IOException, ParseException {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String role = session.getAttribute("role").toString();
			String course_id=request.getParameter("course_id");
			String module_id=request.getParameter("module_id");
			
			ArrayList<ArrayList<String>> list = edao.getquestion(course_id,module_id);

						
		try {
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Paper_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			for(int i =0;i< list.size(); i++) {
				
				String quiz_id=list.get(i).get(3);
//				String course_id = request.getParameter("course_id");
//				String module_id = request.getParameter("module_id");
				String set_id = request.getParameter("set_id");
				String check_ans = request.getParameter("check_"+quiz_id);
				String userid = session.getAttribute("userId_for_jnlp").toString();

				if (course_id == null || course_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Course.");
					return new ModelAndView("redirect:Exam_Paper_url");
				}
				if (module_id == null || module_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Module.");
					return new ModelAndView("redirect:Exam_Paper_url");
				}
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = principal.getName();
				elmp.setExam_name(request.getParameter("exam_name"));
				elmp.setQuiz_id(Integer.parseInt(quiz_id));
				elmp.setAnswer_id(check_ans);
				elmp.setCourse_id(Integer.parseInt(course_id));
				elmp.setModule_id(Integer.parseInt(request.getParameter("module_id")));
				elmp.setUser_id(Integer.parseInt(userid));
				elmp.setCreated_by(username);
				elmp.setCreated_date(date);
				
			    sessionHQL.save(elmp);
				sessionHQL.flush();
				sessionHQL.clear();
				ra.addAttribute("msg", "Data Saved Successfully.");
				ra.addAttribute("module_id", module_id);
				ra.addAttribute("course_id", course_id);
				tx.commit();
				sessionHQL.close();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			
		}
		return new ModelAndView("redirect:Answeer_key_Url");
	}
	
	
	
	
	
	
//	----03-08-22---
	
	@GetMapping(value = "/Answeer_key_Url")
	public ModelAndView Answeer_key_Url( ModelMap model,HttpSession session,HttpServletRequest request,
			 @RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "module_id", required = false) String module_id,
			 @RequestParam(value = "course_id", required = false) String course_id) {
	
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Answeer_key_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			model.put("getquestion", edao.getquestion(course_id,module_id));
			model.put("answer_key", edao.papersolution(course_id,module_id,userid));
			model.put("msg", msg); 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Exam_Result");
  }
	
	
	@PostMapping(value = "/Exam_Result")
	public ModelAndView Exam_Result_action(
			@Validated @ModelAttribute("Exam_Result_cmd") EDU_LMS_EXAM_PAPER elmp, ServletRequest request, ServletRequest session, Principal principal, String quiz_id, String check_ans, ModelMap ra) {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String role = session.getAttribute("role").toString();
			ArrayList<ArrayList<String>> list = edao.getquestion();

		try {
			
			for(int i =0;i< list.size(); i++) {
				
//				String quiz_id=list.get(i).get(3);
				String course_id = request.getParameter("course_id");
				String module_id = request.getParameter("module_id");
			
//				String check_ans = request.getParameter("check_"+quiz_id);
				String userid = session.getAttribute("userId_for_jnlp").toString();

				
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = principal.getName();
				elmp.setExam_name(request.getParameter("exam_name"));
				elmp.setQuiz_id(Integer.parseInt(quiz_id));
				elmp.setAnswer_id(check_ans);
				elmp.setCourse_id(Integer.parseInt(course_id));
				elmp.setModule_id(Integer.parseInt(request.getParameter("module_id")));
				elmp.setUser_id(Integer.parseInt(userid));
				elmp.setCreated_by(username);
				elmp.setCreated_date(date);
				
			    sessionHQL.save(elmp);
				sessionHQL.flush();
				sessionHQL.clear();
				ra.addAttribute("msg", "Data Saved Successfully.");
				tx.commit();
				sessionHQL.close();
			}
		} 
//		catch (RuntimeException e) {
//			e.printStackTrace();
//			try {
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//			ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
//			}
//			throw e;
//		} 
		finally {
			
		}
		return new ModelAndView("redirect:Exam_Result");
	}
	
	
	

	
	@GetMapping(value = "/Exam_result_url")
	public ModelAndView Exam_result_url(@ModelAttribute("id")String id, ModelMap model,HttpSession session,HttpServletRequest request,String course_id, String set_id,
			 @RequestParam(value = "msg", required = false) String msg ) {
	
		try {
			
		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Exam_result_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		model.put("getselectedcoursename", edao.getselectedcoursename(Integer.parseInt(userid)));
		model.put("getselectedsetname", edao.getselectedsetname(Integer.parseInt(userid)));
		ArrayList<ArrayList<String>> list = edao.coursenamelistofResult(userid);
		ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
	
		for(int i=0;i<list.size();i++) {
			
			if (edao.method2(userid,list.get(i).get(0),list.get(i).get(1)).size()!=0) {
			 list2.add(edao.method2(userid,list.get(i).get(0),list.get(i).get(1)));
			}
		}
		model.put("courselist", list2);
		model.put("msg", msg); 
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("Exam_result_Tiles");
  }
	
	@RequestMapping(value = "Exam_result_action", method = RequestMethod.POST)
	public @ResponseBody int Exam_result_action(ModelMap model, HttpSession session, HttpServletRequest request,
			 @RequestParam(value = "course_id", required = false) String course_id) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		int marks = 0;
		ArrayList<ArrayList<String>> list = edao.getattemptedQuizdata(Integer.parseInt(userid), course_id);
		
		for(int i=0;i<list.size();i++) {
			
			ArrayList<ArrayList<String>> list2 = edao.getcorrectanscheck(Integer.parseInt(list.get(i).get(0)));
			
			for(int j=0;j<list2.size();j++) {
				
				if (list.get(i).get(1) != "" && list.get(i).get(1) != null){
				
				if(list.get(i).get(1).equals(list2.get(j).get(0))) {
					marks +=  Integer.parseInt(list2.get(j).get(1));
				}
			  }
			}
		}
		return marks;
	}
	// logo for certi
	
		@RequestMapping(value = "getInstLogoPath", method = RequestMethod.POST)
		public @ResponseBody String getInstLogoPath(ModelMap model, HttpSession session, HttpServletRequest request) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String imgPath  = edao.getInstLogo(Integer.parseInt(userid)).get(0).get(0);
			return imgPath;
		}

		// set name from course name
//			 @RequestMapping(value = "/getsetDatabyCourse", method = RequestMethod.POST)
//				public @ResponseBody   ArrayList<ArrayList<String>> getsetDatabyCourse(String course_id, HttpSession session) {
//				 String userid = session.getAttribute("userId_for_jnlp").toString();
//				 ArrayList<ArrayList<String>> list = edao.getsetlistFromCourse(course_id,userid);
//					return list;
//				}
			 
		// module name from course name
		
		 @RequestMapping(value = "/getModuleListByCourse_data", method = RequestMethod.POST)
			public @ResponseBody   ArrayList<ArrayList<String>> getModuleListByCourse_data(String course_id,HttpSession session) {
				
			String userid = session.getAttribute("userId_for_jnlp").toString();
			   ArrayList<ArrayList<String>> list = edao.getModulelistFromcourse(course_id,userid);
				return list;
			}
			
		// exam name from module name
		 
//		 @RequestMapping(value = "/getExamlistFromModule", method = RequestMethod.POST)
//			public @ResponseBody   ArrayList<ArrayList<String>> getExamlistFromModule(String set_id ,String course_id , String module_id) {
//				
////				System.err.println("------------"+module_id);
//			
//				
//			   ArrayList<ArrayList<String>> list = edao.getExamlistFromModule(set_id, course_id, module_id);
////			   System.err.println("------------"+list);
//				
//				return list;
//			}
		 
		 // ayush_id
		 
		 @RequestMapping(value = "/getaayushidfromuserid", method = RequestMethod.POST)
			public @ResponseBody String getaayushidfromuserid(HttpSession session) throws ParseException {
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			
			  String list = edao.getaayush_idby_uid(Integer.parseInt(userid)).get(0);
				return list;
			}
		 
		 @RequestMapping(value = "/course_exam", method = RequestMethod.POST)
			public @ResponseBody List<Map> courseexam(HttpSession session,String course_id,String module_id) throws ParseException {
			 
			 System.err.println("------course_id "+course_id);
			 String userid = session.getAttribute("userId_for_jnlp").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = "select count(*) from EDU_LMS_EXAM_PAPER p where user_id=:userid and course_id=:course_id and module_id=:module_id";
				Query query = sessionHQL.createQuery(hqlUpdate).setParameter("userid", Integer.parseInt(userid))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("module_id", Integer.parseInt(module_id));
				List<Map> list = (List<Map>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}
		 
		 @RequestMapping(value = "/getquestionByCourse_mod_ctrl", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>>  getquestionByCourse_mod_ctrl(HttpSession session,String course_id,String module_id) throws ParseException {
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			
			  ArrayList<ArrayList<String>> list = edao.getquestion(course_id,module_id);
			 
				return list;
			}
		 
}
