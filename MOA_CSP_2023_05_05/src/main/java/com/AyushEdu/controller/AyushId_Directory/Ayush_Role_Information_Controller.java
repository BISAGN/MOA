package com.AyushEdu.controller.AyushId_Directory;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.AyushId_Directory.AYUSH_ROLE_INFORMATION;
import com.AyushEdu.RBAC.RoleMstrController;
import com.AyushEdu.dao.AyushId_Directory.Ayush_Role_InformationDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Ayush_Role_Information_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	
	//@Autowired
	//Commondao comdao;
	
	@Autowired
	Ayush_Role_InformationDAO sdao;
	
	@Autowired
	RoleMstrController roleMstrController;
	
	@Autowired
	 ValidationController validation;


	//==========================================OPEN PAGE SYSTEM========================================== 
	
	
	@RequestMapping(value = "/get_Ayush_Role_Information_Url", method = RequestMethod.GET)
	public ModelAndView get_Ayush_Role_Information_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		//String staff_lvl = comdao.getStaffLvlfromRoleid(roleid);
		String role = session.getAttribute("role").toString();
		
//		try {
//			
//		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("get_Ayush_Role_Information_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
//		Mmap.put("msg", msg);
//		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
		//Mmap.put("getRoleNameList", comdao.getRolebyStaffLvl(staff_lvl, role));
		return new ModelAndView("Ayush_Role_Information_Tiles");

	}

	
	
	

	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/AyushRoleInformationAction")
		public ModelAndView AyushRoleInformationAction(@Validated @ModelAttribute("AyushRoleInformationCMD") AYUSH_ROLE_INFORMATION td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("get_Ayush_Role_Information_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}

			String roleid = request.getParameter("roleid");
			String status = request.getParameter("status");
			
			//System.out.print("role --------------- " +td);
			System.out.print("status ------------- "+status);
			
			if (roleid == "0" || roleid.equals("")) {
				ra.addAttribute("msg", "Please Select role.");
				return new ModelAndView("redirect:get_Ayush_Role_Information_Url");
			}
			 
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:get_Ayush_Role_Information_Url");
			}

			
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = td.getId() > 0 ? td.getId() : 0;
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  AYUSH_ROLE_INFORMATION where roleid=:roleid and id !=:id")
						.setParameter("roleid", td.getRoleid())
						.setParameter("id", id).uniqueResult();
				
				int idd =0;
				if (id == 0) {
					td.setRoleid(Integer.parseInt(roleid));
					td.setCreated_by(userid);
					td.setCreated_date(date);
					if (c == 0) {
						idd = (int)sessionHQL.save(td);
						System.out.print("save ------------------------------------");
						sessionHQL.flush();
						sessionHQL.clear();
						
						ra.addAttribute("msg", "Data Saved Successfully.");
						System.out.print(ra);
					} else {
						ra.addAttribute("msg", "Data already Exist.");
						System.out.print(ra);
					}
				} else {
					td.setRoleid(Integer.parseInt(roleid));
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = sdao.updateAyushRoleInformation(td);
						//For Core Directory
						//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
						if (msg == "Data Updated Successfully") {
							model.put("msg", msg);
							model.put("Nmsg", "0");
						} else {
							model.put("msg", msg);
							model.put("Nmsg", "1");
						}
						ra.addAttribute("msg",msg);
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
						System.out.print(ra);
					}
				}

				tx.commit();
				//For Core Directory
				//SM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:get_Ayush_Role_Information_Url");
		}

		
		
		
		@PostMapping("/getFilterAyushRoleInformation_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, Integer roleid, String status,String role) {

			return sdao.DataTableAyushRoleInformationDataList(startPage, pageLength, Search, orderColunm, orderType, roleid,status,role);

		}

		@PostMapping("/getTotalAyushRoleInformation_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, Integer roleid,String role,String status) {
			return sdao.DataTableAyushRoleInformationDataTotalCount(Search, roleid,role,status);
			
		}
		
		
		@RequestMapping(value = "/ayush_role_information_delete_Url", method = RequestMethod.POST)
		public ModelAndView ayush_role_information_delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			if(request.getHeader("Referer") == null ) { 
			//	session1.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
//			String roleid1 = session1.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("get_Ayush_Role_Information_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update AYUSH_ROLE_INFORMATION set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				
				tx.commit();
				//SM_dirdao.Delete_System_Mstr_Data(id);
				System.out.println();
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
			return new ModelAndView("redirect:get_Ayush_Role_Information_Url");
		}
		
}
