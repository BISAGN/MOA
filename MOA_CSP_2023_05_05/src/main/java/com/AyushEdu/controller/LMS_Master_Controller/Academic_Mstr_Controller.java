package com.AyushEdu.controller.LMS_Master_Controller;

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
import com.AyushEdu.Core_Directory.Academic_CD_Dao;
import com.AyushEdu.Core_Directory.Category_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_ACADEMIC_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Academic_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Academic_Mstr_Controller {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Academic_Dao Acmdao;
	@Autowired
	Academic_CD_Dao  DM_dirdao;
	
	@RequestMapping(value = "/Academic_Url", method = RequestMethod.GET)
	public ModelAndView Academic_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		try {
			
			
			//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			 
			 Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}

			return new ModelAndView("Academic_Tiles");
		}
	
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/academic_Action")
		public ModelAndView academic_Action(@Validated @ModelAttribute("academic_CMD") EDU_ACADEMIC_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {
			
			String academic = request.getParameter("academic");
			String status = request.getParameter("status");
			
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			

			if (academic == null || academic.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Academic Master");
				return new ModelAndView("redirect:Academic_Url");
			}
			
			if (validation.maxlengthcheck100(academic) == false) {
				ra.addAttribute("msg","Academic "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Academic_Url");
			}
			if (validation.isOnlyAlphabetNumeric(academic) == false) {
				ra.addAttribute("msg","Academic "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Academic_Url");
			}
			
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:Academic_Url");
			}

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_ACADEMIC_MSTR where upper(academic)=:academic  and status =:status and id !=:id")
						.setParameter("academic", td.getAcademic().toUpperCase())
						.setParameter("status",Integer.parseInt( status))
						.setParameter("id", id).uniqueResult();
				
				int idd =0;
					if (id == 0) {
						td.setAcademic(academic);
						td.setCreated_by(userid);
//						td.setCreated_role(role);
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
						td.setAcademic(academic);
						td.setModified_by(userid);
						td.setModified_date(date);
						if (c == 0) {
							td.setId(id);
							String msg = Acmdao.updateAcademic(td);
//							if (msg == "Data Updated Successfully") {
//								model.put("msg", msg);
//								model.put("Nmsg", "0");
//							} else {
//								model.put("msg", msg);
//								model.put("Nmsg", "1");
//							}
							ra.addAttribute("msg", "Data Updated Successfully.");
						} else {
//							model.put("msg", "Data already Exist");
//							model.put("Nmsg", "1");
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					tx.commit();
					//For Core Directory
					DM_dirdao.Insert_Academic_Mstr_Data(idd);
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
				
				return new ModelAndView("redirect:Academic_Url");
			}
			
			
		
		@PostMapping("/getFilter_Academic_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String academic, String status) {

			return Acmdao.DataTable_Academic_DataList(startPage, pageLength, Search, orderColunm, orderType, academic,status);

		}

		@PostMapping("/getTotal_Academic_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String academic) {
			return Acmdao.DataTable_Academic_DataTotalCount(Search, academic);
			
		}
		
			@RequestMapping(value = "/Edit_academic_MstrUrl", method = RequestMethod.POST)
			public ModelAndView Edit_academic_MstrUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "academic", required = false) String academic,
					@RequestParam(value = "status", required = false) String status,
					HttpServletRequest request, HttpSession sessionEdit) {

				if(request.getHeader("Referer") == null ) { 
				//	sessionEdit.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				
				String roleid1 = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_ACADEMIC_MSTR academic_Details = Acmdao.getacademicByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);	
				Mmap.put("academic", academic);
				Mmap.put("status", status);
				Mmap.put("academicinfo", Acmdao.getacademicByid(Integer.parseInt(updateid)));
				Mmap.addAttribute("academic_Details", academic_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				tx.commit();
				sessionHQL.close();

				return new ModelAndView("Academic_Tiles");
			}
//////////////////////////////////////////////edit action
			
			
			@RequestMapping(value = "/edit_Academic_ActionMstr", method = RequestMethod.POST)
			public ModelAndView edit_Academic_ActionMstr(@ModelAttribute("edit_AcademicCMD") EDU_ACADEMIC_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra)
					throws ParseException {

				if (request.getHeader("Referer") == null) {
			//		session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/login");
				}
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String academic = request.getParameter("academic").trim();
				String status = request.getParameter("status");

				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();

				if (academic == null || academic.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Academicy");
					return new ModelAndView("redirect:Academic_Url");
				}
				if (validation.maxlengthcheck100(academic) == false) {
					ra.addAttribute("msg", " Academic " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Academic_Url");
				}
				if (validation.isOnlyAlphabetNumeric(academic) == false) {
					ra.addAttribute("msg", " Academic " + validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Academic_Url");
				}

				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:Academic_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Academic_Url");
				}

				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_ACADEMIC_MSTR where academic=:academic  and status=:status and id !=:id");
					q0.setParameter("academic", academic);

					q0.setParameter("status", Integer.parseInt(status));

					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						String hql = "update EDU_ACADEMIC_MSTR set academic=:academic,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
								+ " where id=:id";

						Query query = session1.createQuery(hql).setParameter("academic", academic)
								.setParameter("status", Integer.parseInt(status)).setParameter("modified_by", username)
								.setParameter("modified_dt", new Date()).setParameter("id", id);
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						//For Core Directory
						DM_dirdao.Update_Academic_Mstr_Data( id,  academic,  status,  username,  new Date());
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

				return new ModelAndView("redirect:Academic_Url");
			}
		
//-------------------------SEARCH Battalion  -------------------------------------//

@RequestMapping(value = "/admin/getSearch_AcademicMstr", method = RequestMethod.POST)
	public ModelAndView getSearch_AcademicMstr(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "academic", required = false) String academic ,String academic1,@ModelAttribute("status1") String status)  {
	

	if (request.getHeader("Referer") == null) {
		//		session.invalidate();
		Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/login");
			}
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			Mmap.put("academic",academic);
			Mmap.put("status", status);
			Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			

		   return new ModelAndView("Academic_Tiles","academic_CMD",new EDU_ACADEMIC_MSTR());
		   
	}
//=============================================DELETE

@RequestMapping(value = "/delete_Academic_Url", method = RequestMethod.POST)
	public ModelAndView delete_Academic_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Academic_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
 
			int app = session.createQuery(
					"update EDU_ACADEMIC_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status",2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Academic_Mstr_Data(id); 
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
		return new ModelAndView("redirect:Academic_Url");
	}

			

}
