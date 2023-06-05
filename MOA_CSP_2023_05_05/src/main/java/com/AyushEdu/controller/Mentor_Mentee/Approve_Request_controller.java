package com.AyushEdu.controller.Mentor_Mentee;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_PARENT;
import com.AyushEdu.Models.Mentor_Mentee.EDU_MEN_MENTOR_MENTEE_REQUEST;
import com.AyushEdu.Models.Mentor_Mentee.EDU_Mentor_Mentee_communication;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Attendance.Exp_Excel_Attendance_Dao;
import com.AyushEdu.dao.Mentor_Mentee.Approve_Request_Dao;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Approve_Request_controller {
	
	
	@Autowired
	private Approve_Request_Dao ARdao;
	
	@Autowired
	Exp_Excel_Attendance_Dao EEADAO;
	
	@Autowired
	private Commondao cd;
	
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	

	@RequestMapping(value = "/approved_request_Url", method = RequestMethod.GET)
	public ModelAndView approved_request_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			
			
	//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("approved_request_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			Mmap.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Approved_request_Tiles");
	}

	@RequestMapping(value = "/Approve_Request_url", method = RequestMethod.POST)
	public ModelAndView Approve_Request_url(@ModelAttribute("Acceptid") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
		

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
  
			int app = session.createQuery(
					"update EDU_MEN_MENTOR_MENTEE_REQUEST set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 1).executeUpdate();

			
			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete"+(app > 0));
				liststr.add("Data Approved Successfully.");
			} else {
				liststr.add("Data not Approve.");
			}
			ra.addAttribute("msg", liststr.get(0));

		return new ModelAndView("redirect:approved_request_Url");
	}
	
	
	@RequestMapping(value = "/Reject_Request__url", method = RequestMethod.POST)
	public ModelAndView Reject_Request__url(@ModelAttribute("Rejectid") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
  
			int app = session.createQuery(
					"update EDU_MEN_MENTOR_MENTEE_REQUEST set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 3).executeUpdate();

			
			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete"+(app > 0));
				liststr.add("Data Rejected Successfully.");
			} else {
				liststr.add("Data not Reject.");
			}
			ra.addAttribute("msg", liststr.get(0));

		return new ModelAndView("redirect:approved_request_Url");
	}
	
	
	@PostMapping("/getSearch_Approve_Request_data")
	public @ResponseBody List<Map<String, Object>> getSearch_Approve_Request_data(HttpSession Session,int startPage, int pageLength,
	String Search,String orderColunm,String orderType) throws ParseException {
		String fac_userid = Session.getAttribute("userId_for_jnlp").toString();
		return ARdao.DataTableApprove_Request(startPage, pageLength, Search, orderColunm, orderType,fac_userid);        
	}

	@PostMapping("/getTotalSearch_Approve_request_dataCount")
	public @ResponseBody long getTotalSearch_Approve_request_dataCount(HttpSession Session,String Search) throws ParseException {
		String fac_userid = Session.getAttribute("userId_for_jnlp").toString();
		return ARdao.DataTableApprove_Request_count(Search,fac_userid);
	}
	
	@PostMapping("/getSearch_Mentee_Request_data")
	public @ResponseBody List<Map<String, Object>> getSearch_Mentee_Request_data(HttpSession Session,int startPage, int pageLength,
	String Search,String orderColunm,String orderType,HttpSession session) throws ParseException {
		String fac_userid2 = Session.getAttribute("userId_for_jnlp").toString();
		String role_id = session.getAttribute("roleid").toString();
		return ARdao.DataTableMentee_Request(startPage, pageLength, Search, orderColunm, orderType,fac_userid2,role_id);        
	}
	
	@PostMapping("/getTotalSearch_Mentee_request_dataCount")
	public @ResponseBody long getTotalSearch_Mentee_request_dataCount(HttpSession Session,String Search) throws ParseException {
		String fac_userid = Session.getAttribute("userId_for_jnlp").toString();
		String role_id = Session.getAttribute("roleid").toString();
		return ARdao.DataTableMentee_Request_count(Search,fac_userid,role_id);
	}
	
	@RequestMapping(value = "/getAskQueryMethodforapprove" , method = RequestMethod.POST)
	public @ResponseBody String getAskQueryMethodforapprove(String faculty_user_id,
			String student_user_id,String message,String role_id,@RequestParam(value = "file_input", required = false) MultipartFile file_input,
			HttpServletRequest request, HttpSession session) throws ParseException, IOException {	
		
		String msg = "";
		int sid = 0;
		String from_role_id = session.getAttribute("roleid").toString();
		String username = session.getAttribute("username").toString();
		String userId = session.getAttribute("userId").toString();
		System.err.println("check the userid"+userId);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.println("faculty_user_id-"+faculty_user_id+"\n-student_user_id-"+student_user_id+"\n-message-");
		
		//SECURITY-----
			long filesize = file_input.getSize() / 1024;
			if (filesize > 200) {
				msg = "File size should be 200 kb or less";
				return msg;
			}
		//SECURITY-----
		
		try {
			EDU_Mentor_Mentee_communication sd =  new EDU_Mentor_Mentee_communication();
			
			sd.setCreated_by(username);
			sd.setCreated_date(new Date());
			sd.setFaculty_user_id(Integer.parseInt(userId));
			sd.setStudent_user_id(Integer.parseInt(student_user_id));
			sd.setMessage(message);
			sd.setFrom_msg(Integer.parseInt(from_role_id));
			sd.setTo_msg(Integer.parseInt(role_id));
			if(!file_input.getOriginalFilename().equals("") && file_input.getOriginalFilename() != null) {
				String mmfile = common.fileupload(file_input.getBytes(), file_input.getOriginalFilename(),1, "mmfile");
				sd.setFile(mmfile);
			}
			sd.setStatus(0);
			
			sid = (int)sessionHQL.save(sd);
			sessionHQL.flush();
			sessionHQL.clear();
	
			if(sid > 0) {
				msg="Query Sent Successfully";
			}
			
			tx.commit();
			return msg;
		} catch (RuntimeException e) {
			try {
				msg="roll back transaction";
			} catch (RuntimeException rbe) {
				msg="Couldn't roll back transaction";
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@PostMapping("/getMessages")
	public @ResponseBody List<Map<String, Object>> getMessages(HttpSession Session,String student_user_id) throws ParseException {
		String fac_userid = Session.getAttribute("userId_for_jnlp").toString();
		return ARdao.getMesgs(fac_userid,student_user_id);        
	}
	
	
	@RequestMapping(value = "/ChangeMessagesStatus" , method = RequestMethod.POST)
	public @ResponseBody String ChangeMessagesStatus(String msgIds,HttpSession session) throws ParseException {	
		 
		String msg = "";
		String username = session.getAttribute("username").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.println("MsgIds---"+msgIds);
		
		String msgidsArr[] = msgIds.split(",");
		try {
			
			for(int i=0;i<msgidsArr.length;i++) {
				EDU_Mentor_Mentee_communication sd =  (EDU_Mentor_Mentee_communication) 
						sessionHQL.get(EDU_Mentor_Mentee_communication.class, Integer.parseInt(msgidsArr[i]));
				
				sd.setStatus(1);
				sd.setModified_by(username);
				sd.setModified_date(new Date());
				
				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
			}
			tx.commit();
			return "Status Updated";
		} catch (RuntimeException e) {
			try {
				msg="roll back transaction";
			} catch (RuntimeException rbe) {
				msg="Couldn't roll back transaction";
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@RequestMapping(value = "/InstStuAsstoFacUrl", method = RequestMethod.GET)
	public ModelAndView InstStuAsstoFacUrl(ModelMap Mmap, HttpSession Session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = Session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("InstStuAsstoFacUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			
			String userid = Session.getAttribute("userId_for_jnlp").toString();
			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			System.err.println("------------institute_id ------------ \n"+institute_id+"\n----------------------------");
			String role = Session.getAttribute("role").toString();
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("msg", msg);
			Mmap.put("faclist",ARdao.getMentorlist(role.split("_")[1].toString(), institute_id));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("InstStuAsstoFac_Tiles");
		
	}
	
	@PostMapping("/getMenteeList")
	public @ResponseBody List<ArrayList<String>> getMenteeList(String prof,String degree,HttpSession Session){
		
		String userid = Session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//		System.err.println("------------institute_id ------------ \n"+institute_id+"\n----------------------------");
		String role = Session.getAttribute("role").toString();
		List<ArrayList<String>> menteelist = ARdao.getStudentlist(role.split("_")[1].toString(), institute_id,prof,degree);
		
		return menteelist; 
		
	}
	
	@PostMapping(value = "/AssStutoFacAction")
	public ModelAndView AssStutoFacAction(
			@Validated @ModelAttribute("AssStutoFacCMD") EDU_MEN_MENTOR_MENTEE_REQUEST td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		String new_mentee = request.getParameter("new_mentee");
		String faculty_user_id = request.getParameter("mentor");
		int check = 0;

		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if(faculty_user_id.equals("0")) {
			ra.addAttribute("msg","Please Select Mentor");
			return new ModelAndView("redirect:InstStuAsstoFacUrl");
		}
		
		if(new_mentee.equals("")) {
			ra.addAttribute("msg","Please Select atleast one Mentee");
			return new ModelAndView("redirect:InstStuAsstoFacUrl");
		}
		
		try {
			
			List<String> newList = new ArrayList<String>();
			if (new_mentee != null && !new_mentee.equals("")) {
				newList = Arrays.asList(new_mentee.split(","));
			}
			
			for(int i=0;i<newList.size();i++) {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_MEN_MENTOR_MENTEE_REQUEST where faculty_user_id=:faculty_user_id and student_user_id=:student_user_id  and status=:status ")
						.setParameter("faculty_user_id", Integer.parseInt(faculty_user_id))
						.setParameter("student_user_id", Integer.parseInt(newList.get(i)))
						.setParameter("status", 0).uniqueResult();
				if (c == 0) {
					
					td.setFaculty_user_id(Integer.parseInt(faculty_user_id));
					td.setStudent_user_id(Integer.parseInt(newList.get(i)));
					td.setStatus(1);
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
				}else {
					check++;
					break;
				}
			}
			
			if(check == 0) {
				ra.addAttribute("msg", "Data Saved Successfully");
				tx.commit();
			}else {
				tx.rollback();
				ra.addAttribute("msg", "Any one or more Student alreday assigned to faculty");
			}
			
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
		return new ModelAndView("redirect:InstStuAsstoFacUrl");
	}

}
