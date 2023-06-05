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
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.Question_type_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Question_Type_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	 @Autowired 
	 Question_type_Mstr_DAO qdao;

    @Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Question_Type_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Question_Type_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Question_Type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Question_Type_Mstr_Tiles");
	}

//	==========================================SAVE/view//Edit Question_Type========================================== 	


	@PostMapping(value = "/QuestionAction")
	public ModelAndView QuestionAction(@Validated @ModelAttribute("QuestionCMD") EDU_CC_TB_QUESTION_TYPE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Question_Type_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String question_type = request.getParameter("question_type");
		String status = request.getParameter("status");

		if (question_type == null || question_type.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Question Type.");
			return new ModelAndView("redirect:Question_Type_Mstr_Url");
		}
		
		if (validation.maxlengthcheck(question_type) == false) {
			ra.addAttribute("msg","Question Type."+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Question_Type_Mstr_Url");
		}
		
		if (validation.isOnlyAlphabet(question_type) == false) {
			ra.addAttribute("msg","Question Type"+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:Question_Type_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Question_Type_Mstr_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Question_Type_Mstr_Url");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		
		System.err.println("----id "+id);
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_QUESTION_TYPE_MSTR where upper(question_type)=:question_type and status=:status and id !=:id")
					.setParameter("question_type",td.getQuestion_type().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setQuestion_type(question_type);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
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
				  else { td.setQuestion_type(question_type); 
				  td.setModified_by(userid);
				  td.setModified_date(date);
				  if (c == 0) { 
					  td.setId(id); String msg =qdao.updatequestion_type(td); 
					  if (msg == "Data Updated Successfully") { 
				  model.put("msg", msg);  model.put("Nmsg", "0");  
				  } else { //
				  model.put("msg", msg);  
				  model.put("Nmsg", "1");  
				  } 
					  ra.addAttribute("msg","Data Updated Successfully."); 
					  } else { 
					  model.put("msg","Data already Exist");  
					  model.put("Nmsg", "1"); 
					  ra.addAttribute("msg","Data already Exist.");
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
		
		return new ModelAndView("redirect:Question_Type_Mstr_Url");
	}
	

@PostMapping("/getFilterquestion_type_data")
public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
		String Search, String orderColunm, String orderType, String question_type, String status) {
	return qdao.DataTableQuestionDataList(startPage, pageLength, Search, orderColunm, orderType, question_type,status);

}

@PostMapping("/getTotalquestion_type_dataCount")
public @ResponseBody long getTotalQuestion_dataCount(HttpSession sessionUserId, String Search, String question_type,String status) {
	return qdao.DataTableQuestionDataTotalCount(Search, question_type,status);
	
}

@RequestMapping(value = "/Edit_Question_Type_Mstr_Url", method = RequestMethod.POST)
public ModelAndView Edit_Question_Type_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "question_type", required = false) String question_type,
		@RequestParam(value = "status", required = false) String status,
		HttpServletRequest request, HttpSession sessionEdit) {
//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		sessionEdit.invalidate();
		 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = sessionEdit.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Question_Type_Mstr_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	EDU_CC_TB_QUESTION_TYPE_MSTR Department_Details = qdao.getQuestionByid(Integer.parseInt(updateid));
	Mmap.addAttribute("msg", msg);
	Mmap.put("question_type", question_type);
	Mmap.put("status", status);
	Mmap.put("Department_Details", Department_Details);
	Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	Mmap.put("Departmentinfo", qdao.getQuestionByid(Integer.parseInt(updateid)));
	
	tx.commit();
	sessionHQL.close();

	return new ModelAndView("Question_Type_Mstr_Tiles");
}


@RequestMapping(value = "/Question_Type_Mstr_Delete_Url", method = RequestMethod.POST)
public ModelAndView Question_Type_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {

	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Question_Type_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		int app = session.createQuery(
				"update EDU_CC_TB_QUESTION_TYPE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
				.setParameter("id", id).setParameter("modified_by", username)
				.setParameter("modified_date", new Date())
				.setParameter("status", 2).executeUpdate();

		
		tx.commit();
		session.close();
		if (app > 0) {
			System.err.println("check delete"+(app > 0));
			liststr.add("Data Deleted Successfully.");
		} else {
			liststr.add("Data not Deleted.");		}
		ra.addAttribute("msg", liststr.get(0));
	} catch (Exception e) {
		e.printStackTrace();
		liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
		ra.addAttribute("msg", liststr.get(0));
	
	}
	return new ModelAndView("redirect:Question_Type_Mstr_Url");
}
}
