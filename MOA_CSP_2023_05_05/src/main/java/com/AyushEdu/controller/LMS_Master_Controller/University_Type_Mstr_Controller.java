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
import com.AyushEdu.Core_Directory.Term_CD_Dao;
import com.AyushEdu.Core_Directory.University_Type_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_TYPE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.University_type_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class University_Type_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	
	@Autowired
	University_Type_CD_Dao  DM_dirdao;
	
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	University_type_Mstr_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/University_type_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView University_type_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
		//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("University_type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("University_Type_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/University_typeAction")
		public ModelAndView University_typeAction(@Validated @ModelAttribute("University_typeCMD") EDU_LMS_UNIVERSITY_TYPE_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("University_type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String university_type = request.getParameter("university_type");
			String status = request.getParameter("status");

			if (university_type == null || university_type.equals("")) {
				ra.addAttribute("msg", "Please Enter University_type Name.");
				return new ModelAndView("redirect:University_type_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(university_type) == false) {
				ra.addAttribute("msg","University Type "+ validation.MaxlengthcheckMSG100);
				
				return new ModelAndView("redirect:University_type_Mstr_Url");
				
			}
			
			
			  if (validation.isOnlyAlphabetDASH(university_type) == false) {
			  ra.addAttribute("msg","University Type "+ validation.isOnlyAlphabetMSGDASH);
			  
			  return new ModelAndView("redirect:University_type_Mstr_Url"); }
			 
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:University_type_Mstr_Url");
			}

			
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = td.getId() > 0 ? td.getId() : 0;
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_UNIVERSITY_TYPE_MSTR where upper(university_type)=:university_type and status=:status and id !=:id")
						.setParameter("university_type", td.getUniversity_type().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				int idd =0;
				if (id == 0) {
					td.setUniversity_type(university_type);
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
					td.setUniversity_type(university_type);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Pdao.updateUniversity_type(td);
						//For Core Directory
						//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
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
				DM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:University_type_Mstr_Url");
		}
		
		@PostMapping("/getFilterUniversity_type_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String university_type, String status) {

			return Pdao.DataTableUniversity_typeDataList(startPage, pageLength, Search, orderColunm, orderType, university_type,status);

		}

		@PostMapping("/getTotalUniversity_type_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String university_type) {
			return Pdao.DataTableUniversity_typeDataTotalCount(Search, university_type);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_University_type_Mstr_Url", method = RequestMethod.POST)
				public ModelAndView Edit_University_type_Mstr_Url(@ModelAttribute("id2") String updateid, HttpSession session,  ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "university_type", required = false) String university_type,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
					String id =request.getParameter("id");
					String userid = session.getAttribute("userId_for_jnlp").toString();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_LMS_UNIVERSITY_TYPE_MSTR University_type_Details = Pdao.getUniversity_typeByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("university_type", university_type);
					Mmap.put("status", status);
					Mmap.put("University_type_Details", University_type_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("University_typeinfo", Pdao.getUniversity_typeByid(Integer.parseInt(updateid)));
					
					tx.commit();
					
					sessionHQL.close();

					return new ModelAndView("University_type_Tiles");
				}
				
				@RequestMapping(value = "/University_type_Mstr_Delete_Url", method = RequestMethod.POST)
				public ModelAndView University_type_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {

					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"update EDU_LMS_UNIVERSITY_TYPE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", id).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();

						
						tx.commit();
						//For Core Directory
						DM_dirdao.Delete_Uni_Type_Mstr_Data(id); 
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
					return new ModelAndView("redirect:University_type_Mstr_Url");
				}

}
