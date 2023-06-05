package com.AyushEdu.controller.Time_Table;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.AyushEdu.Core_Directory.Classroom_CD_DAO;
import com.AyushEdu.Core_Directory.Event_MSTR_CD_DAO;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Time_Table.EDU_TT_EVENT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Time_Table.Event_MSTR_DAO;
import com.AyushEdu.validation.ValidationController;

@RequestMapping(value = { "admin", "/", "user" })

@Controller
public class Event_mstr_Controller {

	

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;


	@Autowired
	Event_MSTR_DAO emdao;
	
	@Autowired
	Event_MSTR_CD_DAO  DM_dirdao;
	
	//==========================================OPEN PAGE EVENT========================================== 
	
	@RequestMapping(value = "/event_mstrUrl", method = RequestMethod.GET)
	public ModelAndView academic_detailsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
		/*
		 * Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1); if(val
		 * == false) { return new ModelAndView("AccessTiles"); }
		 */
		
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Event_Tiles");

	}



//==========================================SAVE EVENT NAME========================================== 	


	@PostMapping(value = "/Event_Master_Action")
	public ModelAndView Event_Master_Action(@Validated @ModelAttribute("Event_MasterCMD") EDU_TT_EVENT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
		String role = session.getAttribute("role").toString();
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		 Date date = new Date();
			 
		 String username = principal.getName();
			 
		 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		     
		String event_name = request.getParameter("event_name");
		String event_date = request.getParameter("event_date");
		int holiday = Integer.parseInt(request.getParameter("holiday"));
		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
				
				
		
		if (event_name == null || event_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Event.");
			return new ModelAndView("redirect:event_mstrUrl");
		}
		
		if (validation.maxlengthcheck100(event_name) == false) {
			ra.addAttribute("msg","Event "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:event_mstrUrl");
		}
		if (validation.isOnlyAlphabet(event_name) == false) {
			ra.addAttribute("msg","Event "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:event_mstrUrl");
		}
		if (event_date.trim().equals("") || event_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select Event Date");
			return new ModelAndView("redirect:event_mstrUrl");
		}
		if (holiday ==0 ) {
			ra.addAttribute("msg", "Please Enter Holiday.");
			return new ModelAndView("redirect:event_mstrUrl");
		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date e_to = null;
			e_to = format.parse(event_date);
			
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_TT_EVENT_MSTR where upper(event_name)=:event_name and event_date=:event_date and holiday=:holiday and id !=:id and institute_id=:institute_id")
					.setParameter("event_name", event_name)
					.setParameter("event_date", e_to)
					.setParameter("holiday", td.getHoliday())
					.setParameter("institute_id",institute_id)
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setEvent_name(event_name);
				td.setEvent_date(e_to);
				td.setHoliday(holiday);
				td.setInstitute_id(institute_id);
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
//				td.setCreated_dt(created_dt);
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
			DM_dirdao.Insert_event_Mstr_Data(idd);
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

		return new ModelAndView("redirect:event_mstrUrl");
	}
	
		
	@PostMapping("/getFilter_Event_MSTR_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Event_MSTR_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String event_name,String event_date, int holiday,HttpSession session) {
			 Session sessiont = sessionFactory.openSession();
				String userid = session.getAttribute("userId_for_jnlp").toString();
				Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
				 List<UserLogin> institute_idList = q1.list();
				 sessiont.close();
				int institute_id = institute_idList.get(0).getInstitute_id();
		return emdao.DataTableEventDataList(startPage, pageLength, Search, orderColunm, orderType, event_name, event_date, holiday, institute_id);

	}

	@PostMapping("/getTotal_Event_MSTR_dataCount")
	public @ResponseBody long getTotal_Event_MSTR_dataCount(HttpSession sessionUserId, String Search, String event_name, String event_date, int holiday,HttpSession session) {
		 Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
		return emdao.DataTableEventDataTotalCount(Search, event_name, event_date, holiday, institute_id);
		
	}
	
	// -------------------------SEARCH Battalion  -------------------------------------//
	 
			 @RequestMapping(value = "/getSearch_Event_Master", method = RequestMethod.POST)
				public ModelAndView getSearch_Event_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "Event_name1", required = false) String Event_name1 ,String Event_name,@ModelAttribute("Event_date1") String Event_date, @ModelAttribute("Holiday1") int Holiday)  {

				 //					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
//				 if(request.getHeader("Referer") == null ) { 
//					 session.invalidate();
//					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/login");
//				 }
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
//				 String  roleid = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid);	

				        Mmap.put("Event_name1", Event_name);
						Mmap.put("Event_date1", Event_date);
						Mmap.put("Holiday1", Holiday);
						
						
						
						

					   return new ModelAndView("Event_Tiles","Event_MasterCMD",new EDU_TT_EVENT_MSTR());
					   
				}
			 
			//==========================================EDIT Academic NAME========================================== 	
				
				
				@RequestMapping(value = "/Edit_Event_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Event_Url(@ModelAttribute("id8") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					/*
					 * if(request.getHeader("Referer") == null ) { sessionEdit.invalidate();
					 * Mmap.put("msg",
					 * "Suspicious Activity Detected,You have been logged out by Administrator");
					 * return new ModelAndView("redirect:/login"); }
					 */
					
					String role = sessionEdit.getAttribute("role").toString();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_TT_EVENT_MSTR event_name = emdao.geteventByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("event_name", common.getAcademicList(sessionFactory));
					Mmap.put("event_date", common.getAcademicList(sessionFactory));
					Mmap.put("holiday", common.getAcademicList(sessionFactory));
					Mmap.put("Event_Name", event_name);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("Event_Nameinfo", emdao.geteventByid(Integer.parseInt(updateid)));
					Mmap.put("updateid", updateid);
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("edit_event_Tiles");
				}
				//edit action
				@RequestMapping(value = "/edit_Event_Action", method = RequestMethod.POST)
				public ModelAndView edit_Event_Action(@ModelAttribute("edit_EventCMD") EDU_TT_EVENT_MSTR rs,
						HttpServletRequest request, ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
					SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					/*
					 * if(request.getHeader("Referer") == null ) { session.invalidate();
					 * model.put("msg",
					 * "Suspicious Activity Detected,You have been logged out by Administrator");
					 * return new ModelAndView("redirect:/login"); }
					 */
//					String roleid1 = session.getAttribute("roleid").toString();
					/*
					 * Boolean val = roledao.ScreenRedirect("Academic_mstrUrl", roleid1); if(val ==
					 * false) { return new ModelAndView("AccessTiles"); }
					 */
						
					String username = session.getAttribute("username").toString();

					int id = Integer.parseInt(request.getParameter("updateid"));
					String event_name= request.getParameter("event_name");
					String event_date = request.getParameter("event_date");
					int holiday = Integer.parseInt(request.getParameter("holiday"));
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					request.getParameter("holiday");
					
					if (event_name == null || event_name.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Event Name");
						return new ModelAndView("redirect:Edit_Event_Url");
					}
					if (validation.maxlengthcheck100(event_name) == false) {
						ra.addAttribute("msg","Event "+ validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Edit_Event_Url");
					}
					if (validation.isOnlyAlphabetDASH(event_name) == false) {
						ra.addAttribute("msg","Event "+ validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:Edit_Event_Url");
					}
					if (event_date.equals("") || event_date.equals("null") || event_date.equals(null)) {
						ra.addAttribute("msg", "Please Enter Event Date.");
						return new ModelAndView("redirect:Edit_Event_Url");
					}
					if (holiday ==0 ) {
						ra.addAttribute("msg", "Please Enter Holiday.");
						return new ModelAndView("redirect:Edit_Event_Url");
					}
					
					
					
					try {
						Query q0 = session1.createQuery(
								"select count(id) from EDU_TT_EVENT_MSTR where event_name=:event_name and event_date=:event_date and holiday=:holiday and id !=:id");
						q0.setParameter("event_name", event_name);
						q0.setParameter("event_date", formate.parse(event_date));
						q0.setParameter("holiday", (holiday));
						q0.setParameter("id", id);

						Long c = (Long) q0.uniqueResult();
						System.err.println("c_____________"+c);
						if (c == 0) {
							String hql = "update EDU_TT_EVENT_MSTR set event_name=:event_name,event_date=:event_date,holiday=:holiday,modified_by=:modified_by,modified_dt=:modified_dt"
									+ " where id=:id";
							
							Query query = session1.createQuery(hql)
									.setParameter("event_name", event_name)
									.setParameter("event_date", formate.parse(event_date))
									.setParameter("holiday",(holiday))
									.setParameter("modified_by", username)
									.setParameter("modified_dt", new Date())
									.setParameter("id", id);
							msg = query.executeUpdate() > 0 ? "1" : "0";
							tx.commit();
							//For Core Directory
							DM_dirdao.Update_event_Mstr_Data( id,  event_name,formate.parse(event_date),  holiday,  username,  new Date());
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
					
					return new ModelAndView("redirect:event_mstrUrl");
				}	
				public List<EDU_TT_EVENT_MSTR> getAcademicList() {
					 Session session = sessionFactory.openSession();
					 Transaction tx = session.beginTransaction();
					 Query q0 = session.createQuery("from EDU_TT_EVENT_MSTR");
					 
					 List<EDU_TT_EVENT_MSTR> AcademicList = (List<EDU_TT_EVENT_MSTR>) q0.list();
				        session.getTransaction().commit();
				        session.close();                
				       return AcademicList;
				}
				
				
				
				
				
			 
			//==========================================DELETE EVENT NAME========================================== 	
			 
			 
				@RequestMapping(value = "/delete_Url9", method = RequestMethod.POST)
				public ModelAndView delete_Url9(@ModelAttribute("id9") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
					
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("event_mstrUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
//					if(request.getHeader("Referer") == null ) { 
//						session1.invalidate();
//						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return new ModelAndView("redirect:/login");
//					 }
//					String roleid1 = session1.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from EDU_TT_EVENT_MSTR where id=:id")
								.setParameter("id", id).executeUpdate();

						
						tx.commit();
						//For Core Directory
						DM_dirdao.Delete_event_Mstr_Data(id); 
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
					return new ModelAndView("redirect:event_mstrUrl");
				}

}
