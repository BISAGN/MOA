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
import com.AyushEdu.Core_Directory.Designation_CD_DAO;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Designation_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Designation_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Designation_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@Autowired
	Designation_CD_DAO  DM_dirdao;
	
	@RequestMapping(value = "admin/Designation_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Designation_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			
		//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Designation_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Designation_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/DesignationAction")
		public ModelAndView DesignationAction(@Validated @ModelAttribute("DesignationCMD") TB_NCH_DESIGNATION_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {

			String designation = request.getParameter("designation");
			String status = request.getParameter("status");
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Designation_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			

			if (designation == null || designation.equals("")) {
				ra.addAttribute("msg", "Please Enter Designation Name.");
				return new ModelAndView("redirect:Designation_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(designation) == false) {
				ra.addAttribute("msg","Designation "+ validation.MaxlengthcheckMSG100);
				
				return new ModelAndView("redirect:Designation_Mstr_Url");
				
			}
			
			if (validation.isOnlyAlphabetDASH(designation) == false) {
				ra.addAttribute("msg","Designation "+ validation.isOnlyAlphabetMSGDASH);
				
				return new ModelAndView("redirect:Designation_Mstr_Url");
			}
			
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:Designation_Mstr_Url");
			}

			int id =Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  TB_NCH_DESIGNATION_MSTR where upper(designation)=:designation and status=:status and id !=:id")
						.setParameter("designation", td.getDesignation().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				int idd =0;
				if (id == 0) {
					td.setDesignation(designation);
					td.setCreated_by(userid);
//					td.setCreated_role(role);
					td.setCreated_date(date);
					if (c == 0) {
						idd = (int)sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setDesignation(designation);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Pdao.updateDesignation(td);
						DM_dirdao.Update_designation_Mstr_Data( td.getId(), td.getDesignation(), td.getStatus(),  userid,  new Date());
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
				//For Core Directory
				DM_dirdao.Insert_designation_Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:Designation_Mstr_Url");
		}
		
		@PostMapping("/getFilterDesignation_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String designation, String status) {

			return Pdao.DataTableDesignationDataList(startPage, pageLength, Search, orderColunm, orderType, designation,status);

		}

		@PostMapping("/getTotalDesignation_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String designation) {
			return Pdao.DataTableDesignationDataTotalCount(Search, designation);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_Designation_Mstr_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Designation_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "designation", required = false) String designation,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					TB_NCH_DESIGNATION_MSTR Designation_Details = Pdao.getDesignationByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("designation", designation);
					Mmap.put("status", status);
					Mmap.put("Designation_Details", Designation_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("Designationinfo", Pdao.getDesignationByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("Designation_Tiles");
				}
				
				@RequestMapping(value = "/Designation_Mstr_Delete_Url", method = RequestMethod.POST)
				public ModelAndView Designation_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {

					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"update TB_NCH_DESIGNATION_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", id).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();

						
						tx.commit();
						//For Core Directory
						DM_dirdao.Delete_designation_Mstr_Data(id); 
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
					return new ModelAndView("redirect:Designation_Mstr_Url");
				}

}
