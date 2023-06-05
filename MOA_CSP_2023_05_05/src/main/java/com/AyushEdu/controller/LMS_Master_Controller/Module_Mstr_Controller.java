package com.AyushEdu.controller.LMS_Master_Controller;
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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Degree_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Module_Mstr_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Module_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Module_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	Module_Mstr_Dao  Mdao;
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Module_Mstr_CD_Dao  DM_dirdao;
	
	@GetMapping
	(value = "/module_url")
	public ModelAndView module_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		try {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
				
			model.put("msg", msg);
			model.put("s_name", common.getsysList(sessionFactory));
			model.put("d_name", common.getDegreeList(sessionFactory));
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			model.put("getcoursenameList",common.getcoursenameList(sessionFactory) );
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("ModuleTiles");
 }
	
	@PostMapping(value = "/Module_action")
	public ModelAndView Module_action(@Validated @ModelAttribute("Module_cmd") EDU_LMS_MODULE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
	//		 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Map<String,String> mObj=new HashMap<>();
		
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		Integer course_id = Integer.parseInt(request.getParameter("course_id"));
		String module_name = request.getParameter("module_name");
		String status = request.getParameter("status");

		
		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:module_url");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:module_url");
		}
		if (course_id == null || course_id == 0) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:module_url");
		}
		if (module_name == null || module_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Module.");
			return new ModelAndView("redirect:module_url");
		}
		if (validation.maxlengthcheck100(module_name) == false) {
			ra.addAttribute("msg", "Module " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:module_url");
		}
		if (validation.isOnlyAlphabetNumeric(module_name) == false) {
			ra.addAttribute("msg", "Module " + validation.isOnlyAlphabetNumericMSG);
			return new ModelAndView("redirect:module_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:module_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:module_url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_MODULE_MSTR where upper(status)=:status and course_id=:course_id and upper(module_name)=:module_name and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("course_id", td.getCourse_id())
					.setParameter("module_name", td.getModule_name().toUpperCase())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");

				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			//For Core Directory
			DM_dirdao.Insert_Module_Mstr_Data(idd);
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
		return new ModelAndView("redirect:module_url");
	}
	
	@PostMapping("/getFiltermodule_data")
	public @ResponseBody List<Map<String, Object>> getFiltermodule_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String course_id, String status,String module_name,String degree_id,String system_id) {
		return Mdao.DataTablemoduleDataList(startPage, pageLength, Search, orderColunm, orderType, course_id, status,module_name,degree_id,system_id);

	}
	@PostMapping("/getTotalmodule_dataCount")
	public @ResponseBody long getTotalmodule_dataCount(HttpSession sessionUserId, String Search, String course_id,String status,String module_name,String degree_id,String system_id) {
		return Mdao.DataTablemoduleDataTotalCount(Search, course_id,status,module_name,degree_id,system_id);
	}
	
	@PostMapping(value = "/deletemodule_Url")
	public ModelAndView deletemodule_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_MODULE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Module_Mstr_Data(id); 
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:module_url");
	}
	
	//-----edit
		@RequestMapping(value = "/Edit_moduleUrl")
		public ModelAndView Edit_moduleUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
				HttpSession sessionEdit) {

			if(request.getHeader("Referer") == null ) { 
			//	sessionEdit.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			EDU_LMS_MODULE_MSTR Module_Details = Mdao.getmoduleByid(Integer.parseInt(updateid));
			String role = sessionEdit.getAttribute("role").toString();

			Mmap.addAttribute("msg", msg);
			Mmap.addAttribute("Module_Details", Module_Details);
			Mmap.put("course", common.getcoursenameList(sessionFactory));
			Mmap.put("s_name", common.getSystemList(sessionFactory,role));
			Mmap.put("d_name", common.getDegreeList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory) );	
			return new ModelAndView("editmoduleTiles");
		}
		
		@RequestMapping(value = "/edit_module_Action", method = RequestMethod.POST)
		public ModelAndView edit_module_Action(@ModelAttribute("edit_moduleCMD") EDU_LMS_MODULE_MSTR rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

			if(request.getHeader("Referer") == null ) { 
			//	session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("module_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String username = session.getAttribute("username").toString();

		//	int id = Integer.parseInt(request.getParameter("id"));
			String id = request.getParameter("id");
			String course_id = request.getParameter("course_id").trim();
			String system_id = request.getParameter("system_id").trim();
			String degree_id = request.getParameter("degree_id").trim();
			String status = request.getParameter("status");
			String module_name = request.getParameter("module_name").trim();
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			if (course_id == null || course_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Course.");
				return new ModelAndView("redirect:module_url");
			}
			if (system_id == null || system_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select System.");
				return new ModelAndView("redirect:module_url");
			}
			if (degree_id == null || degree_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Degree.");
				return new ModelAndView("redirect:module_url");
			}
			if (module_name == null || module_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Module.");
				return new ModelAndView("redirect:module_url");
			}
			if (validation.maxlengthcheck100(module_name) == false) {
				ra.addAttribute("msg", "Module " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:module_url");
			}
			if (validation.isOnlyAlphabetNumeric(module_name) == false) {
				ra.addAttribute("msg", "Module " + validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:module_url");
			}

			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:module_url");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:module_url");
			}
			
			try {
				Query<?> q0 = session1.createQuery(
						"select count(id) from EDU_LMS_MODULE_MSTR where course_id=:course_id and system_id=:system_id and degree_id=:degree_id and status=:status and module_name=:module_name and id !=:id");
				q0.setParameter("course_id", Integer.parseInt(course_id));
				
				q0.setParameter("system_id", Integer.parseInt(system_id));
				
				q0.setParameter("degree_id",  Integer.parseInt(degree_id));
				
				q0.setParameter("status", status);
				
				q0.setParameter("module_name", module_name);

				q0.setParameter("id", Integer.parseInt(id));

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update EDU_LMS_MODULE_MSTR set course_id=:course_id,system_id=:system_id,degree_id=:degree_id,status=:status,module_name=:module_name,modified_by=:modified_by,modified_date=:modified_date"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("course_id", Integer.parseInt(course_id))
							.setParameter("system_id", Integer.parseInt(system_id))
							.setParameter("degree_id", Integer.parseInt(degree_id))
							.setParameter("status", status)
							.setParameter("module_name", module_name)
							.setParameter("modified_by", username).setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(id));
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();

					//For Core Directory
					DM_dirdao.Update_Module_Mstr_Data( Integer.parseInt( id),  module_name,  Integer.parseInt(system_id),Integer.parseInt(degree_id), Integer.parseInt(course_id) ,  status,  username,  new Date());
					if (msg.equals("1")) {
	                                  
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						ra.addAttribute("msg", "Data Not Updated.");
					}
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}

			} catch (RuntimeException e) {
				try {
					tx.rollback();
					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;

			} finally {
				if (session1 != null) {
					session1.close();
				}
			}
			
			return new ModelAndView("redirect:module_url");
		}
		
		@RequestMapping(value = "/getdegreelistby_system", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegreelistby_system(String system_name)  {
			List<EDU_LMS_SYS_DEG_MAP_MASTER> list =  common.getDegreeListbysystem(sessionFactory,system_name);
			return list;
		}
		
	
}
