package com.AyushEdu.controller.Feedback;

import java.util.Date;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Feedback.TB_FEEDBACK_DETAILS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_SUBCATEGORY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Feedback.Feedback_dashboardDao;
import com.vader.sentiment.analyzer.SentimentAnalyzer;
import com.vader.sentiment.analyzer.SentimentPolarities;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class FeedbackForm_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Feedback_dashboardDao fs;
	@Autowired
	CommonController common;
	@RequestMapping(value = "admin/Feedbackform_url", method = RequestMethod.GET)
	public ModelAndView Feedbackform_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Feedbackform_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.addAttribute("msg", msg);
		
		return new ModelAndView("Feedbackform_Tiles");
	}

	@RequestMapping(value = "admin/FacultyFeedbackform_url", method = RequestMethod.GET)
	public ModelAndView FacultyFeedbackform_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FacultyFeedbackform_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("FacultyFeedbackform_Tiles");
	}

	@RequestMapping(value = "admin/CourseFeedbackform_url", method = RequestMethod.GET)
	public ModelAndView CourseFeedbackform_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CourseFeedbackform_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("CourseFeedbackform_Tiles");
	}
	 
	@RequestMapping(value = "/getFeedBackCat", method = RequestMethod.POST)
	public @ResponseBody List<TB_FEEDBACK_CATEGORY_MSTR> getFeedBackCat(HttpServletRequest request) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_FEEDBACK_CATEGORY_MSTR where status = 1 order by id ");
		@SuppressWarnings("unchecked")
		List<TB_FEEDBACK_CATEGORY_MSTR> clist = (List<TB_FEEDBACK_CATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}
	@RequestMapping(value = "/getFeedBackSubCat", method = RequestMethod.POST)
	public @ResponseBody List<TB_FEEDBACK_SUBCATEGORY_MSTR> getFeedBackSubCat(HttpServletRequest request) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_FEEDBACK_SUBCATEGORY_MSTR where status = 1 order by id ");
		@SuppressWarnings("unchecked")
		List<TB_FEEDBACK_SUBCATEGORY_MSTR> clist = (List<TB_FEEDBACK_SUBCATEGORY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();

		return clist;
	}
	
	@RequestMapping(value = "/feedbackUrl", method = RequestMethod.POST)
 	public @ResponseBody List<Map<String, Object>> feedbackUrl(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id,String system_id) {

		String userid = session.getAttribute("userId_for_jnlp").toString();

//		System.err.println("-------------------------" + institute_name);

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from UserLogin where userid=:userid");
		q.setParameter("userid", Integer.parseInt(userid));
		@SuppressWarnings("unchecked")
		List<UserLogin> clist = (List<UserLogin>) q.list();
		
		
		List<Map<String, Object>> dashboardCMD = null;
		Map<String, Object> columns = new LinkedHashMap<String, Object>();

		if (clist.size() > 0 && clist.size() <= 1) {

			if (clist.get(0).getInstitute_id() != 0 && clist.get(0).getUniversity_id() != 0) {
				// INSTITUE

				List<String> list = common.getInstIdfromUserid(sessionFactory, userid);
				if(list.size() > 0) {
					dashboardCMD =  fs.FeedbackDashboardchartDataListByInst(Integer.parseInt(list.get(0)));
//					columns.put("selected","inst");
				}

			} else if (clist.get(0).getInstitute_id() == 0 && clist.get(0).getUniversity_id() != 0) {
				// UNIVERSITY
				List<String> list = common.getUniIdfromUserid(sessionFactory, userid);
				if(list.size() > 0) {
					if( !institute_id.equals("0")  && !institute_id.equals("")) {
					dashboardCMD = fs.FeedbackDashboardchartDataListByUni(Integer.parseInt(list.get(0)),institute_id);
//					columns.put("selected","uni");

					}else {
						dashboardCMD = fs.FeedbackDashboardchartDataListByUni(Integer.parseInt(list.get(0)),"");
//						columns.put("selected","uni");

					}
				}
			} else if (clist.get(0).getInstitute_id() == 0 && clist.get(0).getUniversity_id() == 0) {
				// COMMISION
					dashboardCMD = fs.FeedbackDashboardchartDataListBySystem(system_id);
					
//					columns.put("selected","com");

			}

		}
//		dashboardCMD.add(columns);
		tx.commit();
		sessionHQL.close();

	return dashboardCMD;

	
}
	
	
	@RequestMapping(value = "/feedbackSubCatUrl", method = RequestMethod.POST)
 	public @ResponseBody List<Map<String, Object>> feedbackSubCatUrl(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id,String system_id) {

		String userid = session.getAttribute("userId_for_jnlp").toString();

//		System.err.println("-------------------------" + institute_name);

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from UserLogin where userid=:userid");
		q.setParameter("userid", Integer.parseInt(userid));
		@SuppressWarnings("unchecked")
		List<UserLogin> clist = (List<UserLogin>) q.list();
		String role = session.getAttribute("role").toString();

		
		List<Map<String, Object>> dashboardCMD = null;
		
//		if(role.contains("NCISM") && !role.contains("Student") ) {
//			Mmap.put("selected","com");
//
//		}else if (role.contains("Institute") && !role.contains("NCISM") && !role.contains("Student") && !role.contains("University")) {
//			List<String> list = common.getInstIdfromUserid(sessionFactory, userid);
//				dashboardCMD =  fs.FeedbackDashboardchartDataListByInstSubCat(Integer.parseInt(list.get(0)));
//
//		}else if(role.contains("University") && !role.contains("Institute") && !role.contains("NCISM") && !role.contains("Student")) {
//			List<String> list = common.getUniIdfromUserid(sessionFactory, userid);
//			dashboardCMD = fs.FeedbackDashboardchartDataListByUniSubCat(Integer.parseInt(list.get(0)),institute_id);
//
//		}else {
//			dashboardCMD = fs.FeedbackDashboardchartDataListBySystemSubCat(system_id);
//
//		}
		
		
		Map<String, Object> columns = new LinkedHashMap<String, Object>();

		if (clist.size() > 0 && clist.size() <= 1) {

			if (clist.get(0).getInstitute_id() != 0 && clist.get(0).getUniversity_id() != 0) {
				// INSTITUE

				List<String> list = common.getInstIdfromUserid(sessionFactory, userid);
				if(list.size() > 0) {
					dashboardCMD =  fs.FeedbackDashboardchartDataListByInstSubCat(Integer.parseInt(list.get(0)));
//					columns.put("selected","inst");
				}

			} else if (clist.get(0).getInstitute_id() == 0 && clist.get(0).getUniversity_id() != 0) {
				// UNIVERSITY
				List<String> list = common.getUniIdfromUserid(sessionFactory, userid);
				if(list.size() > 0) {
					if( !institute_id.equals("0")  && !institute_id.equals("")) {
					dashboardCMD = fs.FeedbackDashboardchartDataListByUniSubCat(Integer.parseInt(list.get(0)),institute_id);
//					columns.put("selected","uni");

					}else {
						dashboardCMD = fs.FeedbackDashboardchartDataListByUniSubCat(Integer.parseInt(list.get(0)),"");
//						columns.put("selected","uni");

					}
				}
			} else if (clist.get(0).getInstitute_id() == 0 && clist.get(0).getUniversity_id() == 0) {
				// COMMISION
					dashboardCMD = fs.FeedbackDashboardchartDataListBySystemSubCat(system_id);
					
//					columns.put("selected","com");

			}

		}
		dashboardCMD.add(columns);
		tx.commit();
		sessionHQL.close();

	return dashboardCMD;

	
}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/Feedback_details_save_ctrl", method = RequestMethod.POST)
	public @ResponseBody String Feedback_details_save_ctrl(String user_id,String feedback_for,String feedback_for_sub,String feedback_rating,String feedback_details) {
		
		String msg ="";
		
		
		System.err.println("user_id    >   "+user_id);
		System.err.println("feedback_for-------->   "+feedback_for);
		System.err.println("feedback_rating----->  "+feedback_rating);
		System.err.println("feedback_details----->  "+feedback_details);
		System.err.println("feedback_for_sub----->  "+feedback_for_sub);
		TB_FEEDBACK_DETAILS fd = new TB_FEEDBACK_DETAILS();
		
		
		if (feedback_for.equals("0") || feedback_for =="") {
			msg ="please selct feedback for";
			return  msg;
		}
		if (feedback_for_sub == null || feedback_for_sub.equals("0") || feedback_for =="") {
			fd.setFeedback_subcat(0);
		}else {
			fd.setFeedback_subcat(Integer.parseInt(feedback_for_sub));
		}
		if (feedback_rating.equals("0") || feedback_rating =="") {
			fd.setFeedback_rating(0);
		}else {
			fd.setFeedback_rating(Integer.parseInt(feedback_rating));
		}
		
		
		
		Date date = new Date();
		
		Session sessionHQL = this.sessionFactory.openSession();
		 Transaction tx = sessionHQL.beginTransaction();
			
			
		 final SentimentPolarities sentimentPolarities =
		            SentimentAnalyzer.getScoresFor(feedback_details);
		        System.out.println(sentimentPolarities);
			// SentimentPolarities{positivePolarity=0.437, negativePolarity=0.0, neutralPolarity=0.563, compoundPolarity=0.4767}
		        System.out.println(" ====");
			
				
		 
		if(sentimentPolarities.getCompoundPolarity() > 0) {
			fd.setSentiment(1);

		}else {
			fd.setSentiment(0);

		}
		fd.setUser_id(Integer.parseInt(user_id));
		fd.setFeedback_for(Integer.parseInt(feedback_for));
		
		
		fd.setFeedback_details(feedback_details);
		fd.setCreated_by(user_id);
		fd.setCreated_date(date);

		sessionHQL.save(fd);
		tx.commit();
		sessionHQL.close();
		msg = "Feedback Succesfully Saved";
		
		return msg;
	}
	
//	@Scheduled(initialDelay = 5000, fixedRate = 3000)
	@RequestMapping(value = "/selectFeedBackRandom", method = RequestMethod.GET)
	public ModelAndView selectFeedBackRandom() {
	
		fs.genrateScheduleRandomStudentsForFeedBack();
		
		return new ModelAndView("redirect:commonDashboard");
	}
	
	@RequestMapping(value = "/getInstituteListForUniDashboard", method = RequestMethod.POST)
	public @ResponseBody List<UserLogin> getInstituteListForUniDashboard(HttpServletRequest request, HttpSession session) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();

		return  common.getinstitute_listbyuniversity(sessionFactory,userid);
	}
	
	@RequestMapping(value = "/getInstituteListForComDashboard", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getInstituteListForComDashboard(HttpServletRequest request, HttpSession session) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();

		return  getSystemList();
	}
	
	
	@RequestMapping(value = "/checkFeedBackAvail", method = RequestMethod.POST)
	public @ResponseBody String checkFeedBackAvail(HttpServletRequest request, HttpSession session) {
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Date date = new Date();
		
		Session sessionHQL = this.sessionFactory.openSession();
		 Transaction tx = sessionHQL.beginTransaction();
			
	Long c = (Long) sessionHQL
					.createQuery("select count(id) from  TB_FEEDBACK_RANDOM_DISPLAY where user_id=:user_id")

					.setParameter("user_id", Integer.parseInt(userid))
					.uniqueResult();
		 
		if(c == 0) {
			return "false";
		}else {
			return "true";

		}
//		return msg;
	}
	
	public List<EDU_LMS_SYSTEM_MSTR> getSystemList() {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"from EDU_LMS_SYSTEM_MSTR where status='1' ");
			List<EDU_LMS_SYSTEM_MSTR> list = (List<EDU_LMS_SYSTEM_MSTR>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	

	
	

}
