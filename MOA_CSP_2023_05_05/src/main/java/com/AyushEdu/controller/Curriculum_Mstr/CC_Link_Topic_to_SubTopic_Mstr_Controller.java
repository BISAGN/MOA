package com.AyushEdu.controller.Curriculum_Mstr;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.Link_Topic_to_SubTopic_MstrDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class CC_Link_Topic_to_SubTopic_Mstr_Controller {

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
	Link_Topic_to_SubTopic_MstrDao TSdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/Link_Topic_SubTopic_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Link_Topic_SubTopic_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Link_Topic_SubTopic_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				String role = session.getAttribute("role").toString();	
		 Mmap.put("msg", msg);
		 Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		 Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		 Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		 Mmap.put("getSubTopicList", common.getSubTopicList(sessionFactory));
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Link_Topic_Sub_Topic_Tiles");
	}
	@PostMapping(value = "/link_topic_subtopicmstrAction")
	public ModelAndView link_topic_subtopicmstrAction(@Validated @ModelAttribute("link_topic_subtopicmstrCMD") CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Topic_SubTopic_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String msg="";
		
		Map<String,String> mObj=new HashMap<>();
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String topic_id = request.getParameter("topic_id");
		String status = request.getParameter("status");
		String in_sub_topic_hid[] = request.getParameter("in_sub_topic_hid").split(",");
		String eids[] = request.getParameter("Edit_ids").split(":");
		
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		
		if (topic_id == null || topic_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Topic");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (request.getParameter("in_sub_topic_hid") == null || request.getParameter("in_sub_topic_hid").trim().equals("")) {
			ra.addAttribute("msg", "Please Select Sub Topic");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
		}
		try {
			
			for (int k = 0; k < in_sub_topic_hid.length; k++) {
				
				CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR add = new CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR();
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id "
						+ " and course_id=:course_id and topic_id=:topic_id and subtopic_id=:subtopic_id and status=:status and id !=:id ")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("topic_id", Integer.parseInt(topic_id))
						.setParameter("subtopic_id", Integer.parseInt(in_sub_topic_hid[k]))
						.setParameter("status", 1)
						.setParameter("id", id).uniqueResult();
				if (id == 0) {
					if (c == 0) {
						add.setSystem_id(Integer.parseInt(system_id));
						add.setDegree_id(Integer.parseInt(degree_id));
						add.setProfessional_id(Integer.parseInt(professional_id));
						add.setCourse_id(Integer.parseInt(course_id));
						add.setTopic_id(Integer.parseInt(topic_id));
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setSubtopic_id(Integer.parseInt(in_sub_topic_hid[k]));
						add.setStatus(1);
					
						sessionHQL.save(add);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					}else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}else {
					add.setSystem_id(Integer.parseInt(system_id));
					add.setDegree_id(Integer.parseInt(degree_id));
					add.setProfessional_id(Integer.parseInt(professional_id));
					add.setCourse_id(Integer.parseInt(course_id));
					add.setTopic_id(Integer.parseInt(topic_id));
					add.setSubtopic_id(Integer.parseInt(in_sub_topic_hid[k]));
					add.setModified_by(username);
					add.setModified_date(date);
					if (c == 0) {
						
						if(in_sub_topic_hid.length >= eids.length) {
							
							for(int l=0;l<eids.length;l++) {
								//update
								msg = TSdao.updateTopic_Subtopic(in_sub_topic_hid[l],eids[l],"");
							}
							for(int m=eids.length;m<in_sub_topic_hid.length;m++){
								//save
								add.setCourse_id(Integer.parseInt(course_id));
								add.setTopic_id(Integer.parseInt(topic_id));
								add.setCreated_by(username);
								add.setCreated_date(date);
								add.setSubtopic_id(Integer.parseInt(in_sub_topic_hid[m]));
								add.setStatus(1);
							
								sessionHQL.save(add);
								sessionHQL.flush();
								sessionHQL.clear();
							}
							
						}
						if(in_sub_topic_hid.length < eids.length) {
								//delete					
						}
						ra.addAttribute("msg", msg);
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
		}	
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
	}

	@RequestMapping(value = "/updateSubTopicAction", method = RequestMethod.POST)
	public @ResponseBody String updateSubTopicAction(String course_id,String topic_id,String st_old,String st_new,String status,HttpSession session1,Principal principal,RedirectAttributes ra,HttpServletRequest request )  {

		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		
		String in_sub_topic_hid[] = st_new.split(",");
		String eids[] = st_old.split(":");
		
		if (topic_id == null || topic_id.trim().equals("0")) {
			msg ="Please Select Topic";
			return msg;
		}
		if (st_new.equals("") && st_old.equals("")) {
			msg ="Please Select Sub Topic";
			return msg;
		}
		if (status.equals(null) && status.equals("0")) {
			msg ="Please Select Status";
			return msg;
		}
		if (status.equals("2") && status.equals("2")) {
			msg ="Only Select Active Status.   ";
			return msg;
		}
		if(msg.equals("")) {
			try {
				if(in_sub_topic_hid.length >= eids.length) {
					
					for(int l=0;l<eids.length;l++) {
						//update
						msg = TSdao.updateTopic_Subtopic(in_sub_topic_hid[l],eids[l],"");
					}
					for(int m=eids.length;m<in_sub_topic_hid.length;m++){
						//save
						CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR add = new CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR();
						add.setTopic_id(Integer.parseInt(topic_id));
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setCourse_id(Integer.parseInt(course_id));
						add.setSubtopic_id(Integer.parseInt(in_sub_topic_hid[m]));
						add.setStatus(1);
					
						sessionHQL.save(add);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					
				}
				if(in_sub_topic_hid.length < eids.length) {
						//delete
					for(int k=in_sub_topic_hid.length;k<eids.length;k++) {
						int app = sessionHQL.createQuery(
								"update CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", Integer.parseInt(eids[k])).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();
					}
				}
				
//				msg = "Data Updated Successfully";
				tx.commit();
				ra.addAttribute("msg", "Data Updated Successfully.");
				sessionHQL.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	
	@RequestMapping(value = "/getsubtopiclistby_topic", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_SUB_TOPICS_MSTR> getsubtopiclistby_topic(String topic_id)  {
		List<CC_TB_SUB_TOPICS_MSTR> list =  common.getsubtopic_listbytopic(sessionFactory,topic_id);
		return list;
	}
	
	
	@PostMapping("/getFilterTopic_SubTopicMStr_data")
	public @ResponseBody List<Map<String, Object>> getFilterTopic_SubTopicMStr_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String course_id,String topic_id,String subtopic_id, String status,String system_id,String degree_id,
			String professional_id,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return TSdao.DataTableTopic_SubTopicMStrDataList(startPage, pageLength, Search, orderColunm, orderType, course_id ,topic_id,subtopic_id,status, system_id,degree_id, professional_id, role);
	}

	@PostMapping("/getTotalTopic_SubTopicMStr_dataCount")
	public @ResponseBody long getTotalTopic_SubTopicMStr_dataCount(HttpSession sessionUserId, String Search, String course_id,String topic_id,  String subtopic_id,String status,String system_id,String degree_id,
			String professional_id,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return TSdao.DataTableTopic_SubTopicMStrDataTotalCount(Search,course_id,topic_id, subtopic_id,status, system_id,degree_id, professional_id, role);
	}
	@RequestMapping(value = "/Topic_SubTopic_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Topic_SubTopic_Mstr_Delete_Url(@ModelAttribute("id1") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Link_Topic_SubTopic_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			
			String dids[] = id.split(":"); 
			for(int k=0;k<dids.length;k++) {
				int app = session.createQuery(
						"update CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(dids[k])).setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("status", 2).executeUpdate();

				if (app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
			}
			tx.commit();
			session.close();
			ra.addAttribute("msg", liststr.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Link_Topic_SubTopic_Mstr_Url");
	}
}
