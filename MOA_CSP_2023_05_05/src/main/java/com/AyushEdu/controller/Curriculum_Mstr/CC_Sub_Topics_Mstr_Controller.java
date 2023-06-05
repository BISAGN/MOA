package com.AyushEdu.controller.Curriculum_Mstr;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_Sub_Topics_MstrDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_Sub_Topics_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CC_Sub_Topics_MstrDao STMdao;

	@RequestMapping(value = "admin/Sub_Topic_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Sub_Topic_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Sub_Topic_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		try {
			String role = session.getAttribute("role").toString();	
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
    	 Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		 Mmap.put("CourseList", common.getMainCourseList(sessionFactory));
		 Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Sub_Topic_Tiles");
	}
	
	@PostMapping(value = "/sub_topicmstrAction")
	public ModelAndView sub_topicmstrAction(@Validated @ModelAttribute("sub_topicmstrCMD") CC_TB_SUB_TOPICS_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Sub_Topic_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String topic_id = request.getParameter("topic_id");
		String sub_topic = request.getParameter("sub_topic");
		String status = request.getParameter("status");
		
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject.");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}

		if (topic_id == null || topic_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Topic.");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (sub_topic == null || sub_topic.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Sub Topic.");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (validation.isNumerickavi(sub_topic) == true) {
			ra.addAttribute("msg", "Enter Valid Sub Topic. ");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (validation.isAlphabetCDASH(sub_topic) == false) {
			ra.addAttribute("msg", "Sub Topic " + validation.isAlphabetWSCDASH);
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (validation.checkDescriptionLengthHelpdeskLength(sub_topic) == false) {
			ra.addAttribute("msg","Sub Topic "+ validation.DescriptionLengthHelpdeskMSG);
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_SUB_TOPICS_MSTR where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id and course_id=:course_id and topic_id=:topic_id and upper(sub_topic)=:sub_topic and status=:status and id !=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", td.getCourse_id())
					.setParameter("topic_id", td.getTopic_id())
					.setParameter("sub_topic", td.getSub_topic().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setCourse_id(Integer.parseInt(course_id));
				td.setTopic_id(Integer.parseInt(topic_id));
				td.setSub_topic(sub_topic.trim());
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = STMdao.updateSub_topic(td);
					
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
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
		return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
	}
	
	@PostMapping("/getFilterSub_TopicMstr_data")
	public @ResponseBody List<Map<String, Object>> getFilterSub_TopicMstr_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String course_id ,String topic_id,String sub_topic, String status,String system_id, String degree_id,
			String professional_id,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return STMdao.DataTableSub_TopicMstrDataList(startPage, pageLength, Search, orderColunm, orderType,course_id, topic_id,sub_topic,status, system_id,degree_id, professional_id, role);

	}

	@PostMapping("/getTotalSub_TopicMstr_dataCount")
	public @ResponseBody long getTotalSub_TopicMstr_dataCount(HttpSession sessionUserId, String Search,String course_id,String topic_id,  String sub_topic,String status,String system_id,String degree_id,
			String professional_id,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return STMdao.DataTableSub_TopicMstrDataTotalCount(Search,course_id,topic_id, sub_topic,status, system_id,degree_id, professional_id,role);
	}
	
	@RequestMapping(value = "/Sub_Topic_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Sub_Topic_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Sub_Topic_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			int app = session.createQuery(
					"update CC_TB_SUB_TOPICS_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete"+(app > 0));
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Sub_Topic_Mstr_Url");
	}
}
