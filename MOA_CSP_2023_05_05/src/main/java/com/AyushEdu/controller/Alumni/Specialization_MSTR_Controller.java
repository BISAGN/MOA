package com.AyushEdu.controller.Alumni;

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
import com.AyushEdu.Models.Alumni.EDU_ALUM_SPECIALIZATION_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Specilaization_DAO;
import com.AyushEdu.validation.ValidationController;

@RequestMapping(value = { "admin", "/", "user" })

@Controller
public class Specialization_MSTR_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Specilaization_DAO sdao;

	
	
	@RequestMapping(value = "/specialization_detailsUrl", method = RequestMethod.GET)
	public ModelAndView specialization_detailsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		/*
		 * Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1); if(val
		 * == false) { return new ModelAndView("AccessTiles"); }
		 */
		
		Mmap.put("msg", msg);
		 
		 Mmap.put("getdegreeList", getdegreeList());


	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Specilaization_Tiles");

	}
	
	
	@PostMapping(value = "/Specialization_Action")
	public ModelAndView Specialization_Action(@Validated @ModelAttribute("SpecializationCMD") EDU_ALUM_SPECIALIZATION_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {

		//SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String role = session.getAttribute("role").toString();
		
		 Date date = new Date();
		 
		 String username = principal.getName();
		
	    String degree = request.getParameter("degree");
		String specialization_name = request.getParameter("specialization_name");
		
		if (degree == null || degree.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Degree.");
			return new ModelAndView("redirect:specialization_detailsUrl");
		}
		if (validation.maxlengthcheck100( request.getParameter("degree")) == false) {
			ra.addAttribute("msg","Degree Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:specialization_detailsUrl");	
		}
		if (specialization_name == null || specialization_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Specialization Name.");
			return new ModelAndView("redirect:specialization_detailsUrl");
		}
		if (validation.maxlengthcheck100(specialization_name) == false) {
			ra.addAttribute("msg","Event "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:specialization_detailsUrl");
		}
		if (validation.isOnlyAlphabetDASH(specialization_name) == false) {
			ra.addAttribute("msg","Event "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:specialization_detailsUrl");
		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_ALUM_SPECIALIZATION_MSTR where degree=:degree and specialization_name=:specialization_name")
					 .setParameter("degree",Integer.parseInt(degree)) 
					.setParameter("specialization_name", td.getSpecialization_name())
					.uniqueResult();
			
			if (id == 0) {
				
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
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
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
				
			}
		}

		return new ModelAndView("redirect:specialization_detailsUrl");
	}
	
	@PostMapping("/getFilterspecialization_data")

	public @ResponseBody List<Map<String, Object>> getFilterspecialization_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String degree, String specialization_name) {
		return sdao.DataTableSpecilaizationDataList(startPage, pageLength, Search, orderColunm, orderType, degree, specialization_name);

	}

	@PostMapping("/getTotalspecialization_dataCount")
	public @ResponseBody long getTotalspecialization_dataCount(HttpSession sessionUserId,String Search, String degree, String specialization_name) {
		return sdao.DataTableSpecilaizationDataTotalCount(Search, degree, specialization_name);
		
	}
	
	
	 
	public List<EDU_LMS_DEGREE_MASTER> getdegreeList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_LMS_DEGREE_MASTER where status='1'");
		 
		 List<EDU_LMS_DEGREE_MASTER> DegreeList = q0.list();
	       session.getTransaction().commit();
	       session.close();                
	      return DegreeList;
	}
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
			 @RequestMapping(value = "/admin/getSearch_Specialization", method = RequestMethod.POST)
				public ModelAndView getSearch_Specialization(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "Degree1", required = false) String Degree1 ,String Degree,@ModelAttribute("Specializtion_name1") String Specializtion_name) {
				
				//SECURITY -- RIDDHI 
				 if(request.getHeader("Referer") == null ) { 
//					 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				        Mmap.put("Degree1", Degree);
				        Mmap.put("Specializtion_name1", Specializtion_name);
					   return new ModelAndView("Specilaization_Tiles","SpecializationCMD",new EDU_ALUM_SPECIALIZATION_MSTR());
				}
			 
			//==========================================EDIT Specialization NAME========================================== 	
				
				
				@RequestMapping(value = "/Edit_Specializtion_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Specializtion_Url(@ModelAttribute("id8") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {

					//SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						sessionEdit.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					/*
					 * Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1); if(val ==
					 * false) { return new ModelAndView("AccessTiles"); }
					 */
					String role = sessionEdit.getAttribute("role").toString();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_ALUM_SPECIALIZATION_MSTR specialization_Name = sdao.getSpecializationByid(Integer.parseInt(updateid));
					
					Mmap.addAttribute("msg", msg);
					Mmap.put("degree", getdegreeList());
					
					
					Mmap.put("specialization_Name", specialization_Name);
					
					Mmap.put("specialization_Nameinfo", sdao.getSpecializationByid(Integer.parseInt(updateid)));
					Mmap.put("updateid", updateid);
					 Mmap.put("getdegreeList", getdegreeList());

					tx.commit();
					sessionHQL.close();

					return new ModelAndView("edit_Specialization_Tiles");
				}
//				//edit action
				@RequestMapping(value = "/edit_Specialization_Action", method = RequestMethod.POST)
				public ModelAndView edit_Specialization_Action(@ModelAttribute("edit_Specialization_CMD") EDU_ALUM_SPECIALIZATION_MSTR rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
				
					//SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}

					/*
					 * Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1); if(val ==
					 * false) { return new ModelAndView("AccessTiles"); }
					 */
						
					String username = session.getAttribute("username").toString();

					int id = Integer.parseInt(request.getParameter("updateid"));
					String degree= request.getParameter("degree");
					String specialization_name= request.getParameter("specialization_name");
					
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
					if (degree == null || degree.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Degree.");
						return new ModelAndView("redirect:specialization_detailsUrl");
					}
					
					if (specialization_name == null || specialization_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Specialization Name.");
						return new ModelAndView("redirect:specialization_detailsUrl");
					}
					if (validation.maxlengthcheck100(specialization_name) == false) {
						ra.addAttribute("msg","Specialization Name "+ validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:specialization_detailsUrl");
					}
					if (validation.isOnlyAlphabetDASH(specialization_name) == false) {
						ra.addAttribute("msg","Specialization Name "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:specialization_detailsUrl");
					}
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_ALUM_SPECIALIZATION_MSTR where degree=:degree and specialization_name=:specialization_name and id !=:id");
						q0.setParameter("degree", Integer.parseInt(degree));

						q0.setParameter("specialization_name", specialization_name);
						
						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();

						if (c == 0) {
							String hql = "update EDU_ALUM_SPECIALIZATION_MSTR set degree=:degree,specialization_name=:specialization_name,modified_by=:modified_by,modified_dt=:modified_dt"
									+ " where id=:id";

							Query query = session1.createQuery(hql)
									.setParameter("degree", Integer.parseInt(degree))
									.setParameter("specialization_name", specialization_name)
									.setParameter("modified_by", username)
									.setParameter("modified_dt", new Date())
									.setParameter("id", id);
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
					
					return new ModelAndView("redirect:specialization_detailsUrl");
				}	
				
				
				//==========================================DELETE Specialization NAME========================================== 	
				 
				 
				@RequestMapping(value = "/delete_Url28", method = RequestMethod.POST)
				public ModelAndView delete_Url28(@ModelAttribute("id28") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
	
					//SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session1.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("specialization_detailsUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from EDU_ALUM_SPECIALIZATION_MSTR where id=:id")
								.setParameter("id", id).executeUpdate();

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
					return new ModelAndView("redirect:specialization_detailsUrl");
				}


}
