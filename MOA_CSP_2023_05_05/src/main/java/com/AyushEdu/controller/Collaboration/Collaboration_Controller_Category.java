package com.AyushEdu.controller.Collaboration;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
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
import com.AyushEdu.Models.Collaboration.TB_COL_ORG_CATEGORY;
import com.AyushEdu.controller.Examination.Student_Excel_Report;
import com.AyushEdu.controller.Examination.Student_PDF_Report;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Collaboration.Collaboration_category_DAO;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Collaboration_Controller_Category {

	@Autowired
	private SessionFactory sessionFactory;

	
	  @Autowired Collaboration_category_DAO Cdao;
	  
//	  @Autowired Professional_Ayu_Report_Dao PARDAO;
	  
	  
	  @Autowired ValidationController validation;
	  
	  public void setSessionFactory(SessionFactory sf) { this.sessionFactory = sf;
	  }
	  
	 

	/*
	 * @Autowired TB_COL_ORG_CATEGORY Cdao;
	 */

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	

	/* Collboration Category post */
	
	@RequestMapping(value = "admin/Collaboration_category_Url", method = RequestMethod.GET)
	public ModelAndView Collaboration_category_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			/*
			 * Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			 * Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			 * Mmap.put("getPaperList", common.getPaperList(sessionFactory));
			 * Mmap.put("getQuestionList", common.getQuestionList(sessionFactory));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Collaboration_category_Tiles");

	}


//==========================================SAVE/view//Edit Collaboration Category ========================================== 	

@PostMapping(value = "/Collaboration_category_Action") 
public ModelAndView CollaborationcategoryAction(@Validated @ModelAttribute("Collaboration_category_CMD") TB_COL_ORG_CATEGORY td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, 
	  RedirectAttributes ra) throws IOException {

//	SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
	String collab_cate = request.getParameter("collab_cate");
	String status = request.getParameter("status");
	String eid = request.getParameter("id");

if (collab_cate == null || collab_cate.trim().equals("")) {
	ra.addAttribute("msg", "Please Enter Collaboration Category");
	return new ModelAndView("redirect:Collaboration_category_Url");
}
if (validation.isOnlyAlphabetDASH(collab_cate) == false) {
	ra.addAttribute("msg", "Collaboration Category " + validation.isOnlyAlphabetMSGDASH);
	return new ModelAndView("redirect:Collaboration_category_Url");
}
//
//if (validation.maxlengthcheck100(title) == false) {
//	ra.addAttribute("msg","title "+ validation.MaxlengthcheckMSG100);
//	
//	return new ModelAndView("redirect:Collaboration_category_Url");
//	
//}

//if (validation.isOnlyAlphabetNumber(collab_cate) == false) {
//	ra.addAttribute("msg","collab_cate "+ validation.isOnlyAlphabetNumberMSG);
//	
//	return new ModelAndView("redirect:Collaboration_category_Url");
//}

	int id = td.getId() > 0 ? td.getId() : 0;
	if (!eid.equals("0")) {
		id = Integer.parseInt(eid);
	}
	Date date = new Date();
//String username = principal.getName();

	String userid = session.getAttribute("userId_for_jnlp").toString();

//System.err.println("userid---->    "+userid);

//String userid = session.getAttribute("userid").toString();
//System.out.println();

//System.err.println("img-event-------------"+eventimg);	
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  TB_COL_ORG_CATEGORY where upper(collab_cate)=:collab_cate and status=:status and id !=:id")
				.setParameter("collab_cate", td.getCollab_cate().toUpperCase())
				.setParameter("status", td.getStatus()).setParameter("id", id).uniqueResult();

		if (id == 0) {
			td.setCollab_cate(collab_cate);
			td.setCreated_by(userid);
//		td.setCreated_role(role);
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
		td.setCollab_cate(collab_cate);
		td.setModified_by(userid);
		td.setModified_date(date);
		
		System.err.println("gbhyu  outside");
		if (c == 0) {
			
			System.err.println("gbhyu  inside");				
			td.setId(id);
			String msg = Cdao.updateCollaborationcategory(td);
			
			  if (msg == "Data Updated Successfully") { model.put("msg", msg);
			  model.put("Nmsg", "0"); } else { model.put("msg", msg); model.put("Nmsg",
			 "1"); }
			 
			 
			ra.addAttribute("msg", "Data Updated Successfully");
		} else {
			model.put("msg", "Data already Exist");
			model.put("Nmsg", "1");
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
	} finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}

	return new ModelAndView("redirect:Collaboration_category_Url");
}



@PostMapping("/getFilterCollaboration_categorydata")
public @ResponseBody List<Map<String, Object>> getFilterCollaboration_categorydata(int startPage, int pageLength,

	String Search, String orderColunm, String orderType, String collab_cate, String status) {

return Cdao.DataTableCollaborationcategoryDataList(startPage, pageLength, Search, orderColunm, orderType, collab_cate,status);

}

@PostMapping("/getTotalCollaboration_categorydataCount")
public @ResponseBody long getTotalCollaboration_categorydataCount(HttpSession sessionUserId, String Search, String collab_cate,String status) {
return Cdao.DataTableCollaborationcategoryDataTotalCount1(Search, collab_cate,status) ;


}
@RequestMapping(value = "/Edit_Collaboration_category_Url", method = RequestMethod.POST)
public ModelAndView Edit_Collaboration_category_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "collab_cate", required = false) String collab_cate,
		@RequestParam(value = "status", required = false) String status,
		HttpServletRequest request, HttpSession sessionEdit) {
	
//	SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 sessionEdit.invalidate();
		 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = sessionEdit.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	TB_COL_ORG_CATEGORY Collaborationcategory_Details = Cdao.getCollaborationcategorytByid(Integer.parseInt(updateid));
	Mmap.addAttribute("msg",msg);
	Mmap.put("collab_cate", collab_cate);
	Mmap.put("status", status);
	
	
	Mmap.put("Collaborationcategory_Details", Collaborationcategory_Details);
   Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
	Mmap.put("Professionalinfo", Cdao.getCollaborationcategorytByid(Integer.parseInt(updateid)));
	
	tx.commit();
	sessionHQL.close();

	return new ModelAndView("Collaboration_category_Tiles");
}

@RequestMapping(value = "/Collaboration_category_Delete_Url", method = RequestMethod.POST)
public ModelAndView Collaboration_category_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {

//	SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 session1.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session1.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
	try {

		int app = session.createQuery(
				"delete from TB_COL_ORG_CATEGORY where id=:id")
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
	return new ModelAndView("redirect:Collaboration_category_Url");
}
@RequestMapping(value = "/getCollaboration_category_Report_Excel", method = RequestMethod.POST)
public ModelAndView getCollaboration_category_Report_Excel(HttpSession session, HttpServletRequest request,ModelMap model)
		throws ParseException {
//	SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
//	String userid = session.getAttribute("userId_for_jnlp").toString();
//	String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
	String role = session.getAttribute("role").toString();
//	System.err.println("roleid================" + role.split("_")[1].toString());

	String collab_cate = request.getParameter("collab_cate1");
	
	
	String userId = session.getAttribute("userId_for_jnlp").toString();
	
	List<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate, role, "");


//	ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//		, role, "");

	ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

	List<String> TH = new ArrayList<String>();

	TH.add("ser No.");
	TH.add("Collaboration Category");
	
	String Heading = "\nCope Code";
	
	String username = session.getAttribute("username").toString();
	return new ModelAndView(new Collaboration_Excel_Report("L", TH, listofdata, Heading, username), "userList",
			listexport);
}

@RequestMapping(value = "/Collaboration_category_Report_PDF", method = RequestMethod.POST)
public ModelAndView Collaboration_category_Report_PDF(HttpSession session, HttpServletRequest request,String role,RedirectAttributes ra,ModelMap model)
		throws ParseException {
//	SECURITY -- RIDDHI 
	 if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Collaboration_category_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
	String username = session.getAttribute("username").toString();

	String role1 = session.getAttribute("role").toString();
	String collab_cate = request.getParameter("collab_cate2");
	
	
	String userId = session.getAttribute("userId_for_jnlp").toString();
	

	

	List<ArrayList<String>> nonlecact1 = Cdao.getCollaboration_category_Report_Excel(collab_cate, role1, "");

//			ArrayList<ArrayList<String>> t1copolink2 = Edao.table1_co_po_link(course_id);
//			System.err.println("new_list2-------------"+t1copolink2);

	int total = nonlecact1.size();
	List<String> TH = new ArrayList<String>();

	TH.add("Ser No.");
	TH.add("Collaboration Category");
	
    
	
	if(nonlecact1.size() == 0) {
		ra.addAttribute("msg","Data Not Available");
		return new ModelAndView("redirect:Collaboration_category_Url");
	}
	
	String Heading = "\nIn Inspection";
	return new ModelAndView(new Collaboration_PDF_Report("L", TH, Heading, username, total,role1), "userList", nonlecact1);

}
}


