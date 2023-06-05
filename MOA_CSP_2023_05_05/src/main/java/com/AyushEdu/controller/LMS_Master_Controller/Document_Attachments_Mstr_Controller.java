package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
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
import com.AyushEdu.Core_Directory.Document_Attachments_CD_DAO;
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
//import com.AyushEdu.dao.LMS_Master.DocumentName_Mstr_DAO;
import com.AyushEdu.dao.LMS_Master.Document_Attachments_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Document_Attachments_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	  @Autowired
	  Document_Attachments_Mstr_DAO Ddao;
	 
	  @Autowired
	  Document_Attachments_CD_DAO Doc_dirdao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Document_Attachments_Url", method = RequestMethod.GET)
	public ModelAndView Document_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
	//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Document_Attachments_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
		Mmap.put("msg", msg);
		//Mmap.put("getScreenList", common.getScreenList(sessionFactory));
		//Mmap.put("getScreenSubModuleList", common.getScreenSubModuleList(sessionFactory));
		Mmap.put("getScreenModuleList", common.getScreenModuleList(sessionFactory));
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Document_Attachments_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	


	@PostMapping(value = "/DocumentAction")
	public ModelAndView DocumentAction(@Validated @ModelAttribute("DocumentCMD") EDU_DOC_ATTACHMENTS_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Document_Attachments_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String screen_module_id = request.getParameter("screen_module_id");
		String screen_submodule_id = request.getParameter("screen_submodule_id");
		String screen_id = request.getParameter("screen_id");
		String doc_name = request.getParameter("doc_name");
//		String screen_id = request.getParameter("screen_module_id");
//		String screen_id = request.getParameter("screen_module_id");
		String status = request.getParameter("status");

		if (doc_name == null || doc_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Document Name");
			return new ModelAndView("redirect:Document_Attachments_Url");
		}
		
		if (validation.maxlengthcheck100(doc_name) == false) {
			ra.addAttribute("msg","Document Name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Document_Attachments_Url");
			
		}
		
		if (validation.isOnlyAlphabetDASH(doc_name) == false) {
			ra.addAttribute("msg","Document Name"+ validation.isOnlyAlphabetMSGDASH);
			
			return new ModelAndView("redirect:Document_Attachments_Url");
		}
		
		if (screen_module_id == null || screen_module_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Screen Module.");
			return new ModelAndView("redirect:Document_Attachments_Url");
		}

		if (screen_submodule_id == null || screen_submodule_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Screen SubModule.");
			return new ModelAndView("redirect:Document_Attachments_Url");
		}
		if (screen_id == null || screen_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Screen Name.");
			return new ModelAndView("redirect:Document_Attachments_Url");
		}
		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Document_Attachments_Url");
		}

		int id =Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_DOC_ATTACHMENTS_MSTR where upper(doc_name)=:doc_name and screen_module_id=:screen_module_id and screen_submodule_id=:screen_submodule_id and screen_id=:screen_id and status=:status and id !=:id")
					.setParameter("doc_name", td.getDoc_name().toUpperCase())
					.setParameter("screen_module_id", td.getScreen_module_id())
					.setParameter("screen_submodule_id", td.getScreen_submodule_id())
					.setParameter("screen_id", td.getScreen_id())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setDoc_name(doc_name);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}  else {
				td.setDoc_name(doc_name);
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Ddao.updateDocument(td);
//					if (msg == "Data Updated Successfully") {
//						model.put("msg", msg);
//						model.put("Nmsg", "0");
//					} else {
//						model.put("msg", msg);
//						model.put("Nmsg", "1");
//					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
//					model.put("msg", "Data already Exist");
//					model.put("Nmsg", "1");
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			if (id == 0) {
			//For Core Directory
			Doc_dirdao.Insert_Document_Attachments_Data(idd);
			}
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
		
		return new ModelAndView("redirect:Document_Attachments_Url");
	}
	
	
	@PostMapping("/getFilterDocument_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String screen_module_id,String screen_submodule_id, String screen_id,String doc_name, String status) {

		return Ddao.DataTableDocumentDataList(startPage, pageLength, Search, orderColunm, orderType, screen_module_id,screen_submodule_id, screen_id,doc_name,status);

	}

	@PostMapping("/getTotalDocument_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String screen_module_id,String screen_submodule_id, String screen_id, String doc_name,String status) {
		return Ddao.DataTableDocumentDataTotalCount(Search, screen_module_id,screen_submodule_id, screen_id,doc_name,status);
		
	}

	

	@RequestMapping(value = "/Edit_Document_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Document_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screen_module_id", required = false) String screen_module_id,
			@RequestParam(value = "screen_submodule_id", required = false) String screen_submodule_id,
			@RequestParam(value = "screen_id", required = false) String screen_id,
			@RequestParam(value = "doc_name", required = false) String doc_name,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_DOC_ATTACHMENTS_MSTR Document_Details = Ddao.getDocumentByid(Integer.parseInt(updateid));
		System.err.println("screen_submodule_id========================="+screen_submodule_id);
		Mmap.addAttribute("msg", msg);
		Mmap.put("screen_module_id", screen_module_id);
		Mmap.put("screen_submodule_id", screen_submodule_id);
		Mmap.put("screen_id", screen_id);
		Mmap.put("doc_name", doc_name);
		Mmap.put("status", status);
		Mmap.put("Document_Details", Document_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("Documentinfo", Ddao.getDocumentByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Document_Attachments_Tiles");
	}
	
	@RequestMapping(value = "/Document_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Document_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_DOC_ATTACHMENTS_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			Doc_dirdao.Delete_Document_Attachments_Data(id); 
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
		return new ModelAndView("redirect:Document_Attachments_Url");
	}

	@RequestMapping(value = "/getsubmodule_name_FromScreen_Module", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getsubmodule_name_FromScreen_Module(String screen_module_id) {
		
//	String userid = session.getAttribute("userId_for_jnlp").toString();
	   ArrayList<ArrayList<String>> list = Ddao.getsubmodule_name_FromScreen_Module(screen_module_id);
		return list;
	}
	 
	 @RequestMapping(value = "/getScreen_NameFromScreen_SubModule", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getScreen_NameFromScreen_SubModule(String screen_submodule_id) {
			
//		String userid = session.getAttribute("userId_for_jnlp").toString();
		   ArrayList<ArrayList<String>> list = Ddao.getScreen_NameFromScreen_SubModule(screen_submodule_id);
			return list;
		}
	 
//	 @RequestMapping(value = "/getScreen_SubModuleFromScreen_Module", method = RequestMethod.POST)
//		public @ResponseBody   ArrayList<ArrayList<String>> getScreen_SubModuleFromScreen_Module(String screen_module_id) {
//			
////		String userid = session.getAttribute("userId_for_jnlp").toString();
//		   ArrayList<ArrayList<String>> list = Ddao.getScreen_SubModuleFromScreen_Module(screen_module_id);
//			return list;
//		}
//		
	 

}