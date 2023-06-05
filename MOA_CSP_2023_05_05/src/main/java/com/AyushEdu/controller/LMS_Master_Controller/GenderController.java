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
import com.AyushEdu.Core_Directory.Gender_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Gender_DAO;
import com.AyushEdu.dao.LMS_Master.SystemDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class GenderController {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@Autowired
	 ValidationController validation;

	@Autowired
	Gender_DAO sdao;
	
	@Autowired
	Gender_Master_CD_DAO gen_dirdao;
	
	//==========================================OPEN PAGE GENDER========================================== 
	
	@RequestMapping(value = "/Gender_Url", method = RequestMethod.GET)
	public ModelAndView Gender_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
	 try {	
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Gender_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	 	} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Gender_Tiles");

	}
	
	//==========================================SAVE SYSTEM NAME========================================== 	

	
		@PostMapping(value = "/GenderAction")
		public ModelAndView GenderAction(@Validated @ModelAttribute("GenderCMD") EDU_LMS_GENDER_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {

			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String gender_name = request.getParameter("gender_name");
			String status = request.getParameter("status");

			if (gender_name == null || gender_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Gender.");
				return new ModelAndView("redirect:Gender_Url");
			}
			
			if (validation.maxlengthcheck100(gender_name) == false) {
				ra.addAttribute("msg","Gender "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Gender_Url");
			}
			
			if (validation.isOnlyAlphabetDASH(gender_name) == false) {
				ra.addAttribute("msg","Gender "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Gender_Url");
			}
			
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Gender_Url");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Gender_Url");
			}

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
//			String system_name = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_GENDER_MSTR where upper(gender_name)=:gender_name and upper(status)=:status and id !=:id")
						.setParameter("gender_name", td.getGender_name().toUpperCase())
						.setParameter("status", td.getStatus().toUpperCase())
						.setParameter("id", id).uniqueResult();
				
				int idd =0;
				if (id == 0) {
					td.setGender_name(gender_name);
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

				tx.commit();
				//For Core Directory
				gen_dirdao.Insert_Gender_Master_Data(idd);
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

			return new ModelAndView("redirect:Gender_Url");
		}
		
		@PostMapping("/getFilterGender_data")
		public @ResponseBody List<Map<String, Object>> getFilterGender_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String gender_name, String status) {

			return sdao.DataTableGenderDataList1(startPage, pageLength, Search, orderColunm, orderType, gender_name,status);

		}

		@PostMapping("/getTotalGender_dataCount")
		public @ResponseBody long getTotalGender_dataCount(HttpSession sessionUserId, String Search, String gender_name) {
			return sdao.DataTableGenderDataTotalCount1(Search, gender_name);
			
		}
		
		@RequestMapping(value = "/delete_genderUrl", method = RequestMethod.POST)
		public ModelAndView delete_genderUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			if(request.getHeader("Referer") == null ) { 
		//		 session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Gender_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update EDU_LMS_GENDER_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

				
				tx.commit();
				gen_dirdao.Delete_Gender_Master_Data(id);  
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
			return new ModelAndView("redirect:Gender_Url");
		}
		
		
		//-----edit

				@RequestMapping(value = "/Edit_genderUrl", method = RequestMethod.POST)
				public ModelAndView Edit_genderUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {

					if(request.getHeader("Referer") == null ) { 
				//		 sessionEdit.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Gender_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					String role = sessionEdit.getAttribute("role").toString();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_LMS_GENDER_MSTR Gender_Details = sdao.getGenderByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("system_name", common.getSystemList(sessionFactory,role));
					Mmap.put("Gender_Details", Gender_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("systeminfo", sdao.getGenderByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("EditGender_Tiles");
				}
				//edit action
				@RequestMapping(value = "/Edit_GenderAction", method = RequestMethod.POST)
				public ModelAndView Edit_GenderAction(@ModelAttribute("Edit_GenderCMD") EDU_LMS_GENDER_MSTR rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

					if(request.getHeader("Referer") == null ) { 
					//	 session.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Gender_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					String username = session.getAttribute("username").toString();

					int id = Integer.parseInt(request.getParameter("id"));
					String gender_name = request.getParameter("gender_name").trim();
					String status = request.getParameter("status");
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					if (gender_name == null || gender_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Gender.");
						return new ModelAndView("redirect:Gender_Url");
					}
					
					if (validation.maxlengthcheck100(gender_name) == false) {
						ra.addAttribute("msg","Gender "+ validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Gender_Url");
					}
					
					if (validation.isOnlyAlphabetDASH(gender_name) == false) {
						ra.addAttribute("msg","Gender "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Gender_Url");
					}
					
					if (status == null || status.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Status.");
						return new ModelAndView("redirect:Gender_Url");
					}
					if (status == "2" || status.trim().equals("2")) {
						ra.addAttribute("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Gender_Url");
					}
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_LMS_GENDER_MSTR where gender_name=:gender_name and status=:status and id !=:id");
						q0.setParameter("gender_name", gender_name);

						q0.setParameter("status", status);

						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c == 0) {
							String hql = "update EDU_LMS_GENDER_MSTR set gender_name=:gender_name,status=:status,modified_by=:modified_by,modified_date=:modified_date"
									+ " where id=:id";

							Query query = session1.createQuery(hql).setParameter("gender_name", gender_name).setParameter("status", status)
									.setParameter("modified_by", username).setParameter("modified_date", new Date())
									.setParameter("id", id);
							msg = query.executeUpdate() > 0 ? "1" : "0";
							tx.commit();
							//For Core Directory
							gen_dirdao.Update_Gender_Master_Data( id,gender_name,status,new Date(),username);
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
					
					return new ModelAndView("redirect:Gender_Url");
				}


}
