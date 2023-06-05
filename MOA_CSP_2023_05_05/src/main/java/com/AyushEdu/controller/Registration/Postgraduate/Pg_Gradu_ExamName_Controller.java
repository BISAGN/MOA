package com.AyushEdu.controller.Registration.Postgraduate;

import java.io.IOException;
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
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Postgraduate.Pg_Gradu_ExamName_Dao;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Pg_Gradu_ExamName_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	 @Autowired Pg_Gradu_ExamName_Dao Edao;
	 //@Autowired ValidationController validation;
	
		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "/pg_gradu_Examname_Url", method = RequestMethod.GET)
	public ModelAndView pg_gradu_Examname_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	{
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("pg_gradu_Examname_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		try {
			String role = session.getAttribute("role").toString();
			
			// String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			Mmap.put("s_name", common.getSystemList(sessionFactory,role));
			 //model.put("d_name", common.getDegreeList(sessionFactory));

			/*
			 * Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			 * Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			 * Mmap.put("getPaperList", common.getPaperList(sessionFactory));
			 * Mmap.put("getQuestionList", common.getQuestionList(sessionFactory));
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("pg_gradu_Examname_Tiles");

	}
	

//tb name : edu_pg_gradu_examname


@PostMapping(value = "/pg_gradu_Examname_Action") 
public ModelAndView pg_gradu_Examname_Action(@Validated @ModelAttribute("pg_gradu_Examname_CMD") EDU_PG_GRADU_EXAMNAME td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, 
	  RedirectAttributes ra) throws IOException {
	
//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		session.invalidate();
		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("pg_gradu_Examname_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
	String name_of_the_exam = request.getParameter("name_of_the_exam");
	String system_id = request.getParameter("system_id");
	String degree_id = request.getParameter("degree_id");
	String status = request.getParameter("status");
	String eid = request.getParameter("id");
	
	
	
	if (name_of_the_exam == null || name_of_the_exam.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter Name Of Exam");
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
	}


	if (system_id == null || system_id.trim().equals("0")) {
		ra.addAttribute("msg", "Please Enter System");
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
	}
	

	if (degree_id == null || degree_id.trim().equals("0")) {
		ra.addAttribute("msg", "Please Enter Degree");
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
	}

/*
	if (validation.maxlengthcheck100(name_of_the_exam) == false) {
		ra.addAttribute("msg","name_of_the_exam "+ validation.MaxlengthcheckMSG100);
		
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
		
	}
	
	if (validation.isOnlyAlphabetNumber(name_of_the_exam) == false) {
		ra.addAttribute("msg","name_of_the_exam "+ validation.isOnlyAlphabetNumberMSG);
		
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
	}
*/
	if (status == null || status.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter Status.");
		return new ModelAndView("redirect:pg_gradu_Examname_Url");
	}
	
	
	int id = td.getId() > 0 ? td.getId() : 0;
	if(!eid.equals("0")) {
		id = Integer.parseInt(eid);
	}
	Date date = new Date();
//	String username = principal.getName();

	//String userid = session.getAttribute("userId_for_jnlp").toString();
	
//	System.err.println("userid---->    "+userid);
	
//	String userid = session.getAttribute("userid").toString();
//	System.out.println();

//System.err.println("img-event-------------"+eventimg);	
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  EDU_PG_GRADU_EXAMNAME where upper(name_of_the_exam)=:name_of_the_exam and system_id=:system_id and degree_id=:degree_id and status=:status and id !=:id")
			
				.setParameter("name_of_the_exam", td.getName_of_the_exam().toUpperCase())
				.setParameter("system_id", td.getSystem_id())
				.setParameter("degree_id", td.getDegree_id())
				.setParameter("status", td.getStatus())
				.setParameter("id", td.getId()).uniqueResult();
		
		if (id == 0) {
			td.setName_of_the_exam(name_of_the_exam);
			td.setSystem_id(Integer.parseInt(system_id));
			td.setDegree_id(Integer.parseInt(degree_id));
			td.setId(id);
			//td.setUserid(Integer.parseInt(userid));
			//td.setCreated_by(userid);
//			td.setCreated_role(role);
			td.setCreated_date(date);
			if (c == 0) {
				sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();
				ra.addAttribute("msg", "Data Saved Successfully.");
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
		
		else {
				td.setName_of_the_exam(name_of_the_exam);
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setModified_date(date);
				
				if (c == 0) {
					td.setId(id);
					String msg = Edao.updateExamtype(td);
					ra.addAttribute("msg", msg);
					} 
					else {
						ra.addAttribute("msg", "Data already Exist.");
					} 
			}
		tx.commit();

	} catch (RuntimeException e) {
		try {

			ra.addAttribute("msg", "roll back transaction");
		} catch (RuntimeException rbe) {
			ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
		}
		throw e;
	}  

	 
	/*catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} */
	
	finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}
	return new ModelAndView("redirect:pg_gradu_Examname_Url");
		
}

@PostMapping("/getFilterexamname_data")
public @ResponseBody List<Map<String, Object>> getFilterexamname_data(int startPage, int pageLength,

		String Search, String orderColunm, String orderType, String name_of_the_exam,String system_id, String degree_id,String status ) {

	return Edao.DataTableExamtypeDataList(startPage, pageLength, Search, orderColunm, orderType,name_of_the_exam, system_id, degree_id, status);

}

@PostMapping("/getTotalexamname_dataCount")
public @ResponseBody long getTotalexamname_dataCount(HttpSession sessionUserId, String Search, String name_of_the_exam,  String system_id,  String degree_id,String status) {
	return Edao.DataTableExamtypeDataTotalCount1(Search, name_of_the_exam,system_id,degree_id,status);
	
}

@RequestMapping(value = "/Edit_pg_gradu_Examname_Url", method = RequestMethod.POST)
public ModelAndView Edit_pg_gradu_Examname_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "name_of_the_exam", required = false) String name_of_the_exam,
		@RequestParam(value = "system_id", required = false) String system_id,
		@RequestParam(value = "degree_id", required = false) String degree_id,
		@RequestParam(value = "status", required = false) String status,
		HttpServletRequest request, HttpSession sessionEdit) {
	
//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		session.invalidate();
		Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = sessionEdit.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("pg_gradu_Examname_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
	
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	EDU_PG_GRADU_EXAMNAME gradu_Examname_Details = Edao.getExamtypetByid(Integer.parseInt(updateid));
	Mmap.addAttribute("msg", msg);
	Mmap.put("name_of_the_exam", name_of_the_exam);
	Mmap.put("system_id", system_id);
	Mmap.put("degree_id", degree_id);
	Mmap.put("status", status);
	
	
	Mmap.put("gradu_Examname_Details", gradu_Examname_Details);
   Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	Mmap.put("Professionalinfo", Edao.getExamtypetByid(Integer.parseInt(updateid)));
	
	tx.commit();
	sessionHQL.close();

	return new ModelAndView("pg_gradu_Examname_Tiles");
}

@RequestMapping(value = "/pg_gradu_Examname_Delete_Url", method = RequestMethod.POST)
public ModelAndView pg_gradu_Examname_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) 
{
	
//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		session.invalidate();
		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session1.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("pg_gradu_Examname_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}

	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {

		int app = session.createQuery(
				"delete from EDU_PG_GRADU_EXAMNAME where id=:id")
				.setParameter("id", id).executeUpdate();

		
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
	return new ModelAndView("redirect:pg_gradu_Examname_Url");
}
}
