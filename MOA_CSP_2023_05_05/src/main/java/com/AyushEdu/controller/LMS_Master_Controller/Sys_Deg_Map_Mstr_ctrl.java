package com.AyushEdu.controller.LMS_Master_Controller;

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
import com.AyushEdu.Core_Directory.System_Degree_Mapping_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Sys_Deg_Map_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Sys_Deg_Map_Mstr_ctrl {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Sys_Deg_Map_Mstr_DAO  dmdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	System_Degree_Mapping_Master_CD_DAO  SL_dirdao;
	
	@GetMapping(value = "/System_Degree_Mapping_Master_Url")
	public ModelAndView System_Degree_Mapping_Master_Url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		 try {
				if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_Master_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		String role = session.getAttribute("role").toString();
		 model.addAttribute("msg", msg);
		 model.put("s_name", common.getsysList(sessionFactory));
		 model.put("d_name", common.getDegreeList(sessionFactory));
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("system_degree_mapping_master_Tiles");
}
	
	@PostMapping(value = "/System_Degree_Mapping_Master_action")	public ModelAndView Degree_Master_action(@Validated @ModelAttribute("System_Degree_Mapping_Master_cmd") EDU_LMS_SYS_DEG_MAP_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_Master_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String system_name = request.getParameter("system_name");
		String degree_name = request.getParameter("degree_name");
		String status = request.getParameter("status");
		
		if (system_name == null || system_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (degree_name == null || degree_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status :");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_SYS_DEG_MAP_MASTER where upper(status)=:status and degree_name=:degree_name and system_name=:system_name and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("degree_name", td.getDegree_name())
					.setParameter("system_name", td.getSystem_name())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setCreated_by(username);
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
			}
//				else {
//					td.setModified_by(username);
//					td.setModified_date(date);
//					if (c == 0) {
//						
//						String msg = ddao.UpdateName(td);
//						 ra.addAttribute("msg", msg);
//					} else {
//						 ra.addAttribute("msg", "Data already Exist.");
//					}
//				}
			tx.commit();
			//For Core Directory
			SL_dirdao.Insert_System_Degree_Mapping_Mstr_Data(idd);
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

		return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
	}
	
	
	@PostMapping("/getFilter_system_degree_mapping_data")

	public @ResponseBody List<Map<String, Object>> getFilter_system_degree_mapping_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType,String system_name, String degree_name, String status) {

		return dmdao.DataTablesystem_degree_mappingDataList(startPage, pageLength, Search, orderColunm, orderType,system_name, degree_name,  status);
	}

	@PostMapping("/getTotal_system_degree_mapping_dataCount")
	public @ResponseBody long getTotal_system_degree_mapping_dataCount(HttpSession sessionUserId, String Search,String system_name, String degree_name,String status) {
		return dmdao.DataTablesystem_degree_mappingDataTotalCount(Search,system_name, degree_name, status);
	}
	
	@PostMapping(value = "/delete_System_Degree_Mapping_Master_Url")
	public ModelAndView delete_System_Degree_Mapping_Master_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_Master_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
 
			int app = session.createQuery(
					"update EDU_LMS_SYS_DEG_MAP_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();
			
			tx.commit();
			SL_dirdao.Delete_System_Degree_Mapping_Mstr_Data(id);  
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
		}finally {
			
		}
		return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
	}
	
	@RequestMapping(value = "/Edit_System_Degree_Mapping_Master_Url")
	public ModelAndView Edit_System_Degree_Mapping_Master_Url(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
			HttpSession sessionEdit) {

		try {
		if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_Master_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_SYS_DEG_MAP_MASTER  Edit_system_degree_mapping_details= dmdao.get_system_degree_mappingByid(Integer.parseInt(updateid));
		String role = sessionEdit.getAttribute("role").toString();
		Mmap.put("s_name", common.getSystemList(sessionFactory,role));
		 Mmap.put("d_name", common.getDegreeList(sessionFactory));
		
		Mmap.addAttribute("msg", msg);
		Mmap.put("Edit_system_degree_mapping_details", Edit_system_degree_mapping_details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("edit_system_degree_mapping_master_Tiles");
	}
		
	@RequestMapping(value = "/Edit_System_Degree_Mapping_Master_action", method = RequestMethod.POST)
	public ModelAndView Edit_System_Degree_Mapping_Master_action(@ModelAttribute("Edit_System_Degree_Mapping_Master_cmd") EDU_LMS_SYS_DEG_MAP_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_Master_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String degree_name = request.getParameter("degree_name").trim();
		String system_name = request.getParameter("system_name").trim();
		String status = request.getParameter("status");
		
		if (system_name == null || system_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (degree_name == null || degree_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status :");
			return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
		}
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		try {
			
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_SYS_DEG_MAP_MASTER where degree_name=:degree_name and system_name=:system_name and status=:status and id !=:id");
			q0.setParameter("degree_name", Integer.parseInt(degree_name));
			q0.setParameter("system_name", Integer.parseInt(system_name));
			q0.setParameter("status", status);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_SYS_DEG_MAP_MASTER set degree_name=:degree_name,system_name=:system_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("degree_name", Integer.parseInt(degree_name)).setParameter("system_name", Integer.parseInt(system_name)).setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				SL_dirdao.Update_System_Degree_Mapping_Mstr_Data( id,Integer.parseInt(degree_name),Integer.parseInt(system_name),status,username,new Date());

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
		
		return new ModelAndView("redirect:System_Degree_Mapping_Master_Url");
	}
	
}
