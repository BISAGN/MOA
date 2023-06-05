package com.AyushEdu.controller.Examination;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE;
import com.AyushEdu.Models.Examination.EDU_TB_NO_OF_ATTEMPT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_System_Professional_Degree_Course_Dao;
import com.AyushEdu.dao.Examination.No_Of_Attempt_Mstr_Dao;
//import com.AyushEdu.dao.LMS_Master.DocumentName_Mstr_DAO;
import com.AyushEdu.dao.LMS_Master.Document_Attachments_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class No_Of_Attempt_Mstr_Controller {
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
	No_Of_Attempt_Mstr_Dao sdao;

	@RequestMapping(value = "admin/No_Of_Attempt_Url", method = RequestMethod.GET)
	public ModelAndView No_Of_Attempt_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("No_Of_Attempt_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("No_Of_Attempt_Tiles");
	}

	@RequestMapping(value = "/getDegreeListbysystem2", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem2(String system_name) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = common.getDegreeListbysystem(sessionFactory, system_name);
		return list;
	}
//==========================================SAVE SYSTEM NAME========================================== 	

	@PostMapping(value = "/No_Of_Attempt_Action")
	public ModelAndView No_Of_Attempt_Action(
			@Validated @ModelAttribute("No_Of_Attempt_CMD") EDU_TB_NO_OF_ATTEMPT_MSTR td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("No_Of_Attempt_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String no_of_attempt = request.getParameter("no_of_attempt");
		String status = request.getParameter("status");

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (no_of_attempt == null || no_of_attempt.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter No Of Attempt");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_TB_NO_OF_ATTEMPT_MSTR where system_id=:system_id and degree_id=:degree_id \n"
							+ " and professional_id=:professional_id and no_of_attempt=:no_of_attempt and status=1\n"
							+ " and id!=:id")
					.setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("no_of_attempt", Integer.parseInt(no_of_attempt))
					.setParameter("id", id).uniqueResult();

		
			if (id == 0) {

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
				td.setStatus(1);
				td.setNo_of_attempt(Integer.parseInt(no_of_attempt));

				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
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

		return new ModelAndView("redirect:No_Of_Attempt_Url");
	}

	@PostMapping("/getFilterNo_Of_Attempt_Master_data")
	public @ResponseBody List<Map<String, Object>> getFiltesdpcl_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String no_of_attempt, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTablesysDegProCourselinkDataList(startPage, pageLength, Search, orderColunm, orderType,
				system_id, degree_id, professional_id,no_of_attempt, status);

	}

	@PostMapping("/getTotalNo_Of_Attempt_Master_dataCount")
	public @ResponseBody long getFiltesdpcl(HttpSession sessionUserId, String Search, String system_id,
			String degree_id, String professional_id, String no_of_attempt, String status, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTablesysDegProCourselinkDataListTotalCount(Search, system_id, degree_id, professional_id,
				no_of_attempt, status);

	}

	/////////////////////////////////EDIT Master///////////////////////////////////////////
	@RequestMapping(value = "/Edit_No_Of_Attempt_MasterUrl", method = RequestMethod.POST)
	public ModelAndView Edit_No_Of_Attempt_MasterUrl(String id3, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpSession session,
			HttpServletRequest request, HttpSession sessionEdit) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("No_Of_Attempt_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String role = session.getAttribute("role").toString();
		
				Mmap.addAttribute("msg", msg);
				Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
				Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
				Mmap.put("nod", sdao.getsystemByid(Integer.parseInt(id3)));
				Mmap.put("updateid",id3);
				return new ModelAndView("editNo_Of_Attempt_MasterTiles");
	}

	@RequestMapping(value = "/edit_No_Of_Attempt_Master_action", method = RequestMethod.POST)
	public ModelAndView edit_No_Of_Attempt_Master_action(@ModelAttribute("edit_Attachment_MasterCMD") EDU_TB_NO_OF_ATTEMPT_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
	{
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("No_Of_Attempt_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("updateid")); 
		System.err.println("id================="+id);
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String no_of_attempt = request.getParameter("no_of_attempt");
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
		if (no_of_attempt == null || no_of_attempt.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter No Of Attempt");
			return new ModelAndView("redirect:No_Of_Attempt_Url");
		}
	
		


		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_TB_NO_OF_ATTEMPT_MSTR where system_id=:system_id and degree_id=:degree_id and professional_id=:professional_id and no_of_attempt=:no_of_attempt and id !=:id");
			q0.setParameter("system_id", msg);
			q0.setParameter("system_id", Integer.parseInt(system_id));
			q0.setParameter("degree_id",Integer.parseInt( degree_id));
			q0.setParameter("professional_id", Integer.parseInt(professional_id));
			q0.setParameter("no_of_attempt",Integer.parseInt(no_of_attempt));
			
			
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();
			
			if (c == 0) {
				String hql = "update EDU_TB_NO_OF_ATTEMPT_MSTR set system_id=:system_id ,degree_id=:degree_id,professional_id=:professional_id,no_of_attempt=:no_of_attempt,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

				Query query = session1.createQuery(hql).setParameter("system_id",Integer.parseInt(system_id)).setParameter("degree_id",Integer.parseInt(degree_id)).setParameter("professional_id",Integer.parseInt(professional_id)).setParameter("no_of_attempt",Integer.parseInt(no_of_attempt))
						.setParameter("modified_by", username)
						.setParameter("id", id)
						.setParameter("status", 1)
						.setParameter("modified_date", new Date());
					
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				
				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
		
		finally {
			if (session1 != null) {
				session1.close();
			}
		}
	
		
		
		return new ModelAndView("redirect:No_Of_Attempt_Url");
	}
	}


	// -------------------------SEARCH Battalion  -------------------------------------//

	@RequestMapping(value = "/admin/getSearch_No_Of_Attempt_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_Attachment_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "Attachment", required = false) String Attachment ,String Attachment1)  {
		
		
				Mmap.put("Attachment", Attachment1);
		

				return new ModelAndView("No_Of_Attempt_Tiles","SystemCMD2",new EDU_TB_NO_OF_ATTEMPT_MSTR());
		   
	}
		
	////////////////////////////////delete Attachment//////////////////////////////////////


	@RequestMapping(value = "/delete_No_Of_Attempt_Action", method = RequestMethod.POST)
	public ModelAndView delete_No_Of_Attempt_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("No_Of_Attempt_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
//		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_TB_NO_OF_ATTEMPT_MSTR set modified_by=:modified_by,modified_date=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
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
			
		return new ModelAndView("redirect:No_Of_Attempt_Url");
	}
}
