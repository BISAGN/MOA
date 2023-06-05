package com.AyushEdu.controller.LMS_Master_Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import com.AyushEdu.Core_Directory.State_CD_Dao;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.StateDao;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.TB_STATE;

import freemarker.core.ParseException;



@Controller
@RequestMapping(value = {"admin","/","user"})

public class State_Controller {
	@Autowired
	StateDao Cdao;
	
	@Autowired
	private StateDao State_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	State_CD_Dao  SM_dirdao;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@Autowired
	ValidationController validation;
	@Autowired
	CommonController common;
	
	 @RequestMapping(value = "/admin/State", method = RequestMethod.GET)
	 public ModelAndView State(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 try {
				if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("State", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

		 Mmap.put("msg", msg);		 

		 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("country_id", common.getMedCountryName( sessionFactory));
	
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 return new ModelAndView("StateTiles");
	 }
	 
	 @RequestMapping(value = "/StateAction",method=RequestMethod.POST)
		public ModelAndView StateAction(@ModelAttribute("StateCMD") TB_STATE st,
				 @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra) {
			
		 
		 
		 System.out.println("innnnnn====");
		 
		 if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

				int state_id = st.getState_id() > 0 ? st.getState_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String state_name = request.getParameter("state_name").trim();
				String state_abbr = request.getParameter("state_abbr").trim();
				
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 

						if (st.getCountry_id() == 0 || st.getCountry_id() == null || st.getCountry_id().equals(null)) {
							ra.addAttribute("msg", "Please Select Country.");
							return new ModelAndView("redirect:State");
						}
				
						if (state_name.equals("") || state_name.equals("null") || state_name.equals(null)) {
							ra.addAttribute("msg", "Please Enter State.");
							return new ModelAndView("redirect:State");
						}
						if (validation.maxlengthcheck50(state_name) == false) {
							ra.addAttribute("msg", "State " + validation.MaxlengthcheckMSG50);
							return new ModelAndView("redirect:State");
						}
						if (validation.isOnlyAlphabetDASH(state_name) == false) {
							ra.addAttribute("msg", "State " + validation.isOnlyAlphabetMSGDASH);
							return new ModelAndView("redirect:State");
						}
						if (state_abbr.equals("") || state_abbr.equals("null") || state_abbr.equals(null)) {
							ra.addAttribute("msg", "Please Enter Abbreviation.");
							return new ModelAndView("redirect:State");
						}
						
						if (validation.maxlengthcheck50(state_abbr) == false) {
							ra.addAttribute("msg", "Abbreviation " + validation.MaxlengthcheckMSG50);
							return new ModelAndView("redirect:State");
						}
						if (validation.isOnlyAlphabetDASH(state_abbr) == false) {
							ra.addAttribute("msg", "Abbrivation " + validation.isOnlyAlphabetMSGDASH);
							return new ModelAndView("redirect:State");
						}
						if (st.getStatus() == "inactive" || st.getStatus().equals("inactive")) {
							ra.addAttribute("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:State");
				
						}
				
				try{
					
					Query q0 = sessionHQL.createQuery("select count(state_id) from TB_STATE where state_name=:state_name and status=:status and state_id !=:state_id and state_abbr=:state_abbr");
					q0.setParameter("state_name", st.getState_name());  
					q0.setParameter("status", st.getStatus());
					q0.setParameter("state_abbr", st.getState_abbr()); 
					q0.setParameter("state_id", state_id); 
					
					
					Long c = (Long) q0.uniqueResult();
					int idd =0;
					if (state_id == 0) {
						st.setCreated_by(username);
						st.setCreated_date(date);
						st.setState_name(state_name);
						st.setState_abbr(state_abbr);
						if (c == 0) {
							idd = (int)sessionHQL.save(st);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					else {
						
						if (c == 0) {
						
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					tx.commit();
					//For Core Directory
					SM_dirdao.Insert_State_Mstr_Data(idd);
				}catch(RuntimeException e){
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:State");
			}
	 @RequestMapping(value = "/admin/getSearch_State_Master" , method = RequestMethod.POST)
		public ModelAndView getSearch_State_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "country_id1", required = false) int country_id1,
				@RequestParam(value = "State_name1", required = false) String State_name1,
				@RequestParam(value = "status1", required = false) String status1)
		{
		
		 if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		         Mmap.put("country_id1", country_id1);
				 Mmap.put("State_name1", State_name1);
				 Mmap.put("status1", status1);
				 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("country_id", common.getMedCountryName( sessionFactory));
					
			return new ModelAndView("StateTiles","StateCMD",new TB_STATE());
		}
	 
	 @RequestMapping(value = "/Edit_State", method = RequestMethod.POST)
		public ModelAndView Edit_State(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		
		 if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("State", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		 		TB_STATE stateDetails = State_dao.getstateByid(Integer.parseInt(updateid));
				Mmap.put("Edit_StateCMD", stateDetails);
				Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("country_id", common.getMedCountryName( sessionFactory));
				Mmap.put("msg", msg);
			return new ModelAndView("EditStateTiles");
		}
		
		
		@RequestMapping(value = "/Edit_State_Action", method = RequestMethod.POST)
		public ModelAndView Edit_State_Action(@ModelAttribute("Edit_StateCMD") TB_STATE rs,
				HttpServletRequest request,ModelMap model, HttpSession session,RedirectAttributes ra,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			
			 if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("State", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String username = session.getAttribute("username").toString();
			
			String state_id = request.getParameter("state_id");
			String country_id = request.getParameter("country_id");
			String state_name = request.getParameter("state_name").trim();
			String status = request.getParameter("status");
			String state_abbr = request.getParameter("state_abbr");
			
			if (rs.getCountry_id() == 0 || rs.getCountry_id() == null || rs.getCountry_id().equals(null)) {
				ra.addAttribute("msg", "Please Select Country.");
				return new ModelAndView("redirect:State");
			}
	
			if (state_name.equals("") || state_name.equals("null") || state_name.equals(null)) {
				ra.addAttribute("msg", "Please Enter State.");
				return new ModelAndView("redirect:State");
			}
			if (validation.maxlengthcheck50(state_name) == false) {
				ra.addAttribute("msg", "State " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:State");
			}
			if (validation.isOnlyAlphabetDASH(state_name) == false) {
				ra.addAttribute("msg", "State " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:State");
			}
			
			if (state_abbr.equals("") || state_abbr.equals("null") || state_abbr.equals(null)) {
				ra.addAttribute("msg", "Please Enter Abbreviation.");
				return new ModelAndView("redirect:State");
			}
			if (validation.maxlengthcheck50(state_abbr) == false) {
				ra.addAttribute("msg", "Abbreviation " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:State");
			}
			if (validation.isOnlyAlphabetDASH(state_abbr) == false) {
				ra.addAttribute("msg", "Abbrivation " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:State");
			}
	
			if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	
				ra.addAttribute("msg", "Only Select Active Status.");
	
				return new ModelAndView("redirect:State");
	
			}
			
				
				Session session1 = this.sessionFactory.openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(state_id) from TB_STATE where country_id=:country_id and state_name=:state_name and status=:status and state_id !=:state_id and state_abbr=:state_abbr");
						q0.setParameter("state_name", state_name); 
						q0.setParameter("status", status); 
						q0.setParameter("country_id", Integer.parseInt(country_id));
						q0.setParameter("state_id", Integer.parseInt(state_id)); 
						q0.setParameter("state_abbr", state_abbr); 
					
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_STATE set country_id=:country_id,state_name=:state_name,status=:status,modify_by=:modify_by,modify_date=:modify_date,state_abbr=:state_abbr"
										+ " where state_id=:state_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  .setParameter("country_id",Integer.parseInt(country_id))
					    			  	.setParameter("state_name",state_name)
					    			  	.setParameter("state_abbr",state_abbr)
					    			  	.setParameter("status",status)
										.setParameter("modify_by", username)
										.setParameter("modify_date", new Date())
										.setParameter("state_id",Integer.parseInt(state_id));
										
					                    msg = query.executeUpdate() > 0 ? "1" :"0";
					                    tx.commit(); 
					                  //For Core Directory
					                    SM_dirdao.Update_State_Mstr_Data(Integer.parseInt(state_id),  state_name,  Integer.parseInt(country_id),  state_abbr,  status,  username,  new Date());
					                    
					                    if(msg == "1") {
					                    	 ra.addAttribute("msg", "Data Updated Successfully.");
					                    }
					                    else {
					                    	ra.addAttribute("msg", "Data Not Updated.");
					                    }
						}
						else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					
				  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      ra.addAttribute("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		                      ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:State");
		}
		
		
		@PostMapping("/getFilterstate_data")

		public @ResponseBody ArrayList<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String state, String status, String country_id,
				String state_abbr) {

			return (ArrayList<Map<String, Object>>) Cdao.State_nameDataList(startPage, pageLength, Search, orderColunm,
					orderType, state, status, country_id, state_abbr);
		}

		@PostMapping("/getTotalstate_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
				String state,String country_id,String state_abbr) {
			return Cdao.DataTotalCount(Search, state,country_id, state_abbr);

		}
		
		@RequestMapping(value = "/delete_State", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_State(@ModelAttribute("id1") int state_id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,RedirectAttributes ra,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("State", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_STATE set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where state_id=:state_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate)
						 .setParameter("status","2")
						.setParameter("modify_by", username)
						.setParameter("modify_date", new Date())
						.setParameter("state_id",state_id)
						.executeUpdate();
				tx.commit();
				//For Core Directory
				SM_dirdao.Delete_State_Mstr_Data(state_id);  
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				ra.addAttribute("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				ra.addAttribute("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:State");
		}


}