package com.AyushEdu.controller.Collaboration;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
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
import com.AyushEdu.Models.Collaboration.TB_COL_ORG_COLB;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Collaboration.Collaboration_colb_DAO;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Collaboration_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Type_of_Degree_MstrDao  TDdao;

	
	 @Autowired Collaboration_colb_DAO Adao;
	 @Autowired ValidationController validation;
	
	  public void setSessionFactory(SessionFactory sf) 
	  { this.sessionFactory = sf;
	  }
	 


//	O
//	  @Autowired TB_COL_ORG_COLB Adao;
	 

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	

	@RequestMapping(value = "admin/Collaboration_Url", method = RequestMethod.GET)
	public ModelAndView Collaboration_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	{

		try {
//			SECURITY -- RIDDHI 
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Collaboration_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			Mmap.put("CollaborationIntypeList", common.CollaborationIntypeList(sessionFactory));
     		Mmap.put("CollaborationInsectorList", common.CollaborationInsectorList(sessionFactory));
     		Mmap.put("CollaborationIncategoryList", common.CollaborationIncategoryList(sessionFactory));

			/*
			 * Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			 * Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			 * Mmap.put("getPaperList", common.getPaperList(sessionFactory));
			 * Mmap.put("getQuestionList", common.getQuestionList(sessionFactory));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Collabration_Tiles");

	}
	
	/* Collboration Search post 
	
	@RequestMapping(value = "admin/Collaboration_search_Url", method = RequestMethod.GET)
	public ModelAndView Collaboration_search_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			/*
			 * Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			 * Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			 * Mmap.put("getPaperList", common.getPaperList(sessionFactory));
			 * Mmap.put("getQuestionList", common.getQuestionList(sessionFactory));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Collabration_search_Tiles");

	}
	*/

	//ViewCollabration


	@RequestMapping(value = "/ViewCollabration", method = RequestMethod.GET)
		public ModelAndView ViewCollabration(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			try {
//				SECURITY -- RIDDHI 
				 if(request.getHeader("Referer") == null ) { 
//					 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Collaboration_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				Mmap.put("msg", msg);
				Mmap.addAttribute("langugae", "english");
				Mmap.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//				Mmap.put("COLAB_DATA",Adao.DataOfCollab());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("collab_before_login_Tiles");
		}
		
		@PostMapping("/getallCollabData")
		public @ResponseBody ArrayList<ArrayList<String>> getallCollabData() {
			return Adao.DataOfCollab();
		}
		
	

@PostMapping(value = "/Collaborationcolb_Action") 
public ModelAndView CollaborationcolbAction(@Validated @ModelAttribute("Collaborationcolb_CMD") TB_COL_ORG_COLB td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, 
	  RedirectAttributes ra) throws IOException {
//				SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
	String collaborationtype = request.getParameter("collaborationtype");
	String collaborationsector = request.getParameter("collaborationsector");
	String collaborationcategory = request.getParameter("collaborationcategory");
	String collaborationtitle = request.getParameter("collaborationtitle");
	String collaborationdescription = request.getParameter("collaborationdescription");
	String from_date = request.getParameter("from_date");
	String to_date = request.getParameter("to_date");
	//String collaborationsign = request.getParameter("collaborationsign");
     DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	String eid = request.getParameter("id");
	

	if (collaborationtype == null || collaborationtype.trim().equals("0")) {
		ra.addAttribute("msg", "Please Select Collaboration Type");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	

	if (collaborationsector == null || collaborationsector.trim().equals("0")) {
		ra.addAttribute("msg", "Please Select  Sector");
		return new ModelAndView("redirect:Collaboration_Url");
	}

	if (collaborationcategory == null || collaborationcategory.trim().equals("0")) {
		ra.addAttribute("msg", "Please Select  Category");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	
	if (collaborationtitle == null || collaborationtitle.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter Title");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	if (validation.isOnlyAlphabetDASH(collaborationtitle) == false) {
		ra.addAttribute("msg", "Title " + validation.isOnlyAlphabetMSGDASH);
		return new ModelAndView("redirect:Collaboration_Url");
	}
	if (collaborationdescription == null || collaborationdescription.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter Description");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	
	if (validation.isAlphabetCDASH(collaborationdescription) == false) {
		ra.addAttribute("msg", "Description " + validation.isAlphabetWSCDASH);
		return new ModelAndView("redirect:Collaboration_Url");
		}
	if (from_date == null || from_date.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter From Date");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	 
	if (to_date == null || to_date.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter To Date");
		return new ModelAndView("redirect:Collaboration_Url");
	}
	
//	if (collaborationsign == null || collaborationsign.trim().equals("")) {
//		ra.addAttribute("msg", "Please Enter Collaboration Sign");
//		return new ModelAndView("redirect:Collaboration_Url");
//	}
	
/*
	if (validation.maxlengthcheck100(collaborationtype) == false) {
		ra.addAttribute("msg","collaborationtype "+ validation.MaxlengthcheckMSG100);
		
		return new ModelAndView("redirect:Collaboration_Url");
		
	}
	
	if (validation.isOnlyAlphabetNumber(collaborationtype) == false) {
		ra.addAttribute("msg","collaboration_type "+ validation.isOnlyAlphabetNumberMSG);
		
		return new ModelAndView("redirect:Collaboration_Url");
	}
*/
	
	
	int id = td.getId() > 0 ? td.getId() : 0;
	if(!eid.equals("0")) {
		id = Integer.parseInt(eid);
	}
	Date date = new Date();
//	String username = principal.getName();

	String userid = session.getAttribute("userId_for_jnlp").toString();
	
//	System.err.println("userid---->    "+userid);
	
//	String userid = session.getAttribute("userid").toString();
//	System.out.println();

//System.err.println("img-event-------------"+eventimg);	
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  TB_COL_ORG_COLB where upper(collaborationtype)=:collaborationtype and collaborationsector=:collaborationsector and collaborationcategory=:collaborationcategory "
				+ "and collaborationtitle=:collaborationtitle and collaborationdescription=:collaborationdescription and from_date=:from_date and to_date=:to_date and id !=:id")
				.setParameter("collaborationtype", td.getCollaborationtype().toUpperCase())
				.setParameter("collaborationsector", td.getCollaborationsector().toUpperCase())
				.setParameter("collaborationcategory", td.getCollaborationcategory().toUpperCase())
				.setParameter("collaborationtitle", td.getCollaborationtitle().toUpperCase())
				.setParameter("collaborationdescription", td.getCollaborationdescription().toUpperCase())
				.setParameter("from_date", td.getFrom_date())
				.setParameter("to_date", td.getTo_date())
//				.setParameter("collaborationsign", td.getCollaborationsign().toUpperCase()) and collaborationsign=:collaborationsign
				.setParameter("id", td.getId()).uniqueResult();
		
		if (id == 0) {
			td.setCollaborationtype(collaborationtype);
			td.setCollaborationsector(collaborationsector);
			td.setCollaborationcategory(collaborationcategory);
			td.setCollaborationtitle(collaborationtitle);
			td.setCollaborationdescription(collaborationdescription);
		    //td.setCollaborationsign(collaborationsign);
			td.setFrom_date(format.parse(from_date));
			td.setTo_date(format.parse(to_date));
			td.setId(id);
			td.setUserid(Integer.parseInt(userid));
			td.setCreated_by(userid);
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
			td.setCollaborationtype(collaborationtype);
			td.setCollaborationsector(collaborationsector);
			td.setCollaborationcategory(collaborationcategory);
			td.setCollaborationtitle(collaborationtitle);
			td.setCollaborationdescription(collaborationdescription);
			td.setFrom_date(format.parse(from_date));
			td.setTo_date(format.parse(to_date));
			td.setModified_by(userid);
			td.setModified_date(date);
			
			if (c == 0) {
				
				td.setId(id);
				String msg = Adao.updateCollaborationcolb(td);
				 
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
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}
	
	return new ModelAndView("redirect:Collaboration_Url");
}


@PostMapping("/getFilterCollaboration_colbdata")
public @ResponseBody List<Map<String, Object>> getFilterCollaboration_colbdata(int startPage, int pageLength,

	String Search, String orderColunm, String orderType, String collaborationtype, String collaborationsector,  
	String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date) {

return Adao.DataTableCollaborationcolbDataList(startPage, pageLength, Search, orderColunm, orderType,collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date);

}

@PostMapping("/getTotalCollaboration_colbdataCount")
public @ResponseBody long getTotalCollaboration_colbdataCount(HttpSession sessionUserId, String Search, String collaborationtype, String collaborationsector,  
		String collaborationcategory, String collaborationtitle, String collaborationdescription,String from_date, String to_date) {
return Adao.DataTableCollaborationcolbDataTotalCount1(Search, collaborationtype,collaborationsector,collaborationcategory,collaborationtitle,collaborationdescription,from_date,to_date) ;


}

@RequestMapping(value = "/Edit_Collaboration_Url", method = RequestMethod.POST)
public ModelAndView Edit_Collaboration_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "collaborationtype", required = false) String collaborationtype,
		@RequestParam(value = "collaborationsector", required = false) String collaborationsector,
		@RequestParam(value = "collaborationcategory", required = false) String collaborationcategory,
		@RequestParam(value = "collaborationtitle", required = false) String collaborationtitle,
		@RequestParam(value = "collaborationdescription", required = false) String collaborationdescription,
		@RequestParam(value = "from_date", required = false) String from_date,
		@RequestParam(value = "to_date", required = false) String to_date,
		HttpServletRequest request, HttpSession sessionEdit) {
	
		//	SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Collaboration_Url", roleid1);		
			if(val == false) {
			return new ModelAndView("AccessTiles");
			}
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	TB_COL_ORG_COLB Collaborationcolb_Details = Adao.getCollaborationcolbtByid(Integer.parseInt(updateid));
	Mmap.addAttribute("msg", msg);
	Mmap.put("collaborationtype", collaborationtype);
	Mmap.put("collaborationsector", collaborationsector);
	Mmap.put("collaborationcategory", collaborationcategory);
	Mmap.put("collaborationtitle", collaborationtitle);
	Mmap.put("collaborationdescription", collaborationdescription);
	Mmap.put("from_date", from_date);
	Mmap.put("to_date", to_date);
	Mmap.put("Collaborationcolb_Details", Collaborationcolb_Details);
   Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	Mmap.put("Professionalinfo", Adao.getCollaborationcolbtByid(Integer.parseInt(updateid)));
	
	tx.commit();
	sessionHQL.close();

	return new ModelAndView("Collaboration_Tiles");
}


@RequestMapping(value = "/Collaboration_Delete_Url", method = RequestMethod.POST)
public ModelAndView Collaboration_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {
//	SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Collaboration_Url", roleid1);		
			if(val == false) {
			return new ModelAndView("AccessTiles");
			}
	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {

		int app = session.createQuery(
				"delete from TB_COL_ORG_COLB where id=:id")
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
	return new ModelAndView("redirect:Collaboration_Url");
}

}


