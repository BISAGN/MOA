package com.AyushEdu.controller.LMS_Master_Controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Core_Directory.District_CD_DAO;
import com.AyushEdu.Core_Directory.Document_Attachments_CD_DAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.DistrictDao;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class District_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	CommonController common;

	@Autowired
	private DistrictDao Dis_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	ValidationController validation;
	
	 @Autowired
	 District_CD_DAO Dis_dirdao;

	// -------------------------------District For page Open -------------------------------------

	 @RequestMapping(value = "admin/District", method = RequestMethod.GET)
	 public ModelAndView District(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 try {
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("District", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
		 Mmap.put("msg", msg);
		 Mmap.put("getMedCountryName", common.getMedCountryName( sessionFactory));
		 Mmap.put("state_id", common.getMedStateName(sessionFactory));
//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 return new ModelAndView("DistrictTiles");
	 }
	 
		// -------------------------------District save -------------------------------------

	 @RequestMapping(value = "/DistrictAction",method=RequestMethod.POST)
		public ModelAndView DistrictAction(@ModelAttribute("DistrictCMD") EDU_LMS_DISTRICT_MSTR rm, @RequestParam(value = "msg", required = false) String msg,
				BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra) {
			
		 if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	 
				int district_id = rm.getDistrict_id() > 0 ? rm.getDistrict_id() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String district_name = request.getParameter("district_name").trim();
				
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				 
							if (rm.getCountry_id() == 0) {
								ra.addAttribute("msg", "Please Select Country.");
								return new ModelAndView("redirect:District");
							}
					
							if (rm.getState_id() == 0) {
								ra.addAttribute("msg", "Please Select State.");
								return new ModelAndView("redirect:District");
							}
					
							if (district_name.equals("") || district_name.equals("null") || district_name.equals(null)) {
								ra.addAttribute("msg", "Please Enter District.");
								return new ModelAndView("redirect:District");
							}
							if (validation.maxlengthcheck50(district_name) == false) {
								ra.addAttribute("msg", "District " + validation.MaxlengthcheckMSG50);
								return new ModelAndView("redirect:District");
							}
							if (validation.isOnlyAlphabetDASH(district_name) == false) {
								ra.addAttribute("msg", "District " + validation.isOnlyAlphabetMSGDASH);
								return new ModelAndView("redirect:District");
							}
							if (rm.getStatus() == null || rm.getStatus().trim().equals("0")) {
								ra.addAttribute("msg", "Please Select Status.");
								return new ModelAndView("redirect:District");
							}
							if (rm.getStatus() == "2" || rm.getStatus().equals("2")) {
								ra.addAttribute("msg", "Only Select Active Status.");
								return new ModelAndView("redirect:District");
							}

					try{
					
					Query q0 = sessionHQL.createQuery("select count(district_id) from EDU_LMS_DISTRICT_MSTR where district_name=:district_name  and status=:status  and district_id !=:district_id");
					q0.setParameter("district_name", rm.getDistrict_name());  
					q0.setParameter("status", rm.getStatus());
					
					q0.setParameter("district_id", district_id); 
					Long c = (Long) q0.uniqueResult();
					int idd =0;
					if (district_id == 0) {
						rm.setCreated_by(username);
						rm.setCreated_date(date);
						rm.setDistrict_name(district_name);
						if (c == 0) {
							 idd = (int)sessionHQL.save(rm);
							sessionHQL.save(rm);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					else {
						
						if (c == 0) {
							//String msg = rankdao.updaterankcode(rm);
							//ra.addAttribute("msg", msg);
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					tx.commit();
					Dis_dirdao.Insert_District_Mstr_Data(idd);
				}catch(RuntimeException e){
					e.printStackTrace();
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:District");
			}
	 
	 
	 
//		// -------------------------SEARCH District  -------------------------------------

	 @RequestMapping(value = "/admin/getSearch_District_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_District_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "country_id1", required = false) int country_id1,
				@RequestParam(value = "state_id1", required = false) int state_id1,
				@RequestParam(value = "District_name1", required = false) String District_name1 ,
		        @RequestParam(value = "status1", required = false) String status1) 
	 {
		 
		 if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		   }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
//		 ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(country_id1,state_id1,District_name1,status1);
		        Mmap.put("country_id1", country_id1);
			    Mmap.put("state_id1", state_id1);
				Mmap.put("District_name1", District_name1);
				Mmap.put("status1", status1);

				Mmap.put("country_id", common.getMedCountryName( sessionFactory));
				 Mmap.put("state_id", common.getMedStateName(sessionFactory));
				 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
				
			return new ModelAndView("DistrictTiles","DistrictCMD",new EDU_LMS_DISTRICT_MSTR());
		}
	 
//		// -------------------------Edit District For page Open -------------------------------------

	 @RequestMapping(value = "/Edit_District",method=RequestMethod.POST)
		public ModelAndView Edit_District(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		
		 if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("District", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
			EDU_LMS_DISTRICT_MSTR disDetails = Dis_Dao.getDistrictByid(Integer.parseInt(updateid));
				Mmap.put("Edit_DistrictCMD", disDetails);
				Mmap.put("country_id", common.getMedCountryName( sessionFactory));
				 Mmap.put("state_id", common.getMedStateName(sessionFactory));
				 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_DistrictTiles");
		}
		
//		// -------------------------Edit District_Action -------------------------------------

		
		@RequestMapping(value = "/Edit_District_Action", method = RequestMethod.POST)
		public ModelAndView Edit_District_Action(@ModelAttribute("Edit_DistrictCMD") EDU_LMS_DISTRICT_MSTR rs,
				HttpServletRequest request,ModelMap model, HttpSession session,RedirectAttributes ra,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("District", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String username = session.getAttribute("username").toString();
//			int id = Integer.parseInt(request.getParameter("id"));
			int id = Integer.parseInt(request.getParameter("id"));
			System.err.println("-----id--- "+id);
			
			int state_id = Integer.parseInt(request.getParameter("state_id"));
			String district_name = request.getParameter("district_name").trim();
			String status = request.getParameter("status");
//			int id = Integer.parseInt(request.getParameter("district_id"));
			int country_id = Integer.parseInt(request.getParameter("country_id"));
			
					if (rs.getCountry_id() == 0) {
						EDU_LMS_DISTRICT_MSTR disDetails = Dis_Dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);

						model.put("country_id", common.getMedCountryName( sessionFactory));
						model.put("state_id", common.getMedStateName(sessionFactory));
						model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
						model.put("msg", "Please Select Country");
						//model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
			
				  if (rs.getState_id() == 0) {
					  EDU_LMS_DISTRICT_MSTR disDetails = Dis_Dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);

						model.put("country_id", common.getMedCountryName( sessionFactory));
						model.put("state_id", common.getMedStateName(sessionFactory));
						model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
						model.put("getMedStateName", common.getMedStateName(sessionFactory));
						
						model.put("msg", "Please Select State.");
//						model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
			
				 if (district_name.equals("") || district_name.equals("null")
							|| district_name.equals(null)) {
					 EDU_LMS_DISTRICT_MSTR disDetails = Dis_Dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", common.getMedCountryName( sessionFactory));
						model.put("state_id", common.getMedStateName(sessionFactory));
						model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
						model.put("msg", "Please Enter District.");
						//model.put("id2", id);
						return new ModelAndView("Edit_DistrictTiles");
					}
				 if (rs.getStatus() == "2" || rs.getStatus().equals("2")) {
					 EDU_LMS_DISTRICT_MSTR disDetails = Dis_Dao.getDistrictByid((id));
						model.put("Edit_DistrictCMD", disDetails);
						model.put("country_id", common.getMedCountryName( sessionFactory));
						model.put("state_id", common.getMedStateName(sessionFactory));
						model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("Edit_DistrictTiles");
			
					}
			
				 if (rs.getCountry_id() == 0) {
						ra.addAttribute("msg", "Please Select Country.");
						return new ModelAndView("redirect:District");
					}
			
					if (rs.getState_id() == 0) {
						ra.addAttribute("msg", "Please Select State.");
						return new ModelAndView("redirect:District");
					}
			
					if (district_name.equals("") || district_name.equals("null") || district_name.equals(null)) {
						ra.addAttribute("msg", "Please Enter District.");
						return new ModelAndView("redirect:District");
					}
					if (validation.maxlengthcheck50(district_name) == false) {
						ra.addAttribute("msg", "District " + validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:District");
					}
					if (validation.isOnlyAlphabetDASH(district_name) == false) {
						ra.addAttribute("msg", "District " + validation.isOnlyAlphabetMSGDASH);
						return new ModelAndView("redirect:District");
					}
					if (rs.getStatus() == null || rs.getStatus().trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Status.");
						return new ModelAndView("redirect:District");
					}
					if (rs.getStatus() == "2" || rs.getStatus().equals("2")) {
						ra.addAttribute("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:District");
					}
					
			Session session1 = this.sessionFactory.openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(district_id) from EDU_LMS_DISTRICT_MSTR where country_id=:country_id and "
					 		+ "state_id=:state_id and district_name=:district_name  and status=:status and district_id !=:district_id");
					 
						q0.setParameter("district_name", district_name); 
						q0.setParameter("country_id", rs.getCountry_id());
						q0.setParameter("state_id", state_id);
						q0.setParameter("status", status); 
						q0.setParameter("district_id", rs.getDistrict_id()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update EDU_LMS_DISTRICT_MSTR set country_id=:country_id,state_id=:state_id,district_name=:district_name,modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where district_id=:district_id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  .setParameter("country_id",rs.getCountry_id())
					    			  .setParameter("state_id",state_id)
					    			  .setParameter("status",status)
					    			  .setParameter("district_name",district_name)
					    			  .setParameter("modify_by", username)
					    			  .setParameter("modify_date", new Date())
					    			  .setParameter("district_id",id);
					                    msg = query.executeUpdate() > 0 ? "1" :"0";
					                    tx.commit(); 
					                  //For Core Directory
					                    Dis_dirdao.Update_District_Mstr_Data(country_id, state_id,status,district_name,username,new Date(),id);  
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
			return new ModelAndView("redirect:District");
		}

		
		
	 // -------------------------Delete District  -------------------------------------

	
		
		@RequestMapping(value = "/delete_District", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_District(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,RedirectAttributes ra,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("District", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

				ArrayList<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
			
			try {
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update EDU_LMS_DISTRICT_MSTR set modify_by=:modify_by,modify_date=:modify_date,status=:status"
										+ " where district_id=:district_id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setParameter("status","2")
						.setParameter("modify_by", username)
						.setParameter("modify_date", new Date()).setParameter("district_id", id).executeUpdate();
				tx.commit();
				//For Directory
				Dis_dirdao.Delete_District_Mstr_Data(id); 
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete Unsuccessfully.");
				}
				ra.addAttribute("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				ra.addAttribute("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:District");
		}
		
		@PostMapping("/getFilterdistrict")
		public @ResponseBody List<Map<String, Object>> getFilterdistrict(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String country,  String state ,  String district , String status) {
			return Dis_Dao.search_District_name(startPage, pageLength, Search, orderColunm, orderType, country,state,district,status);
		}
		
		@PostMapping("/getTotaldistrict_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String country,  String state ,  String district, String status) {
			return Dis_Dao.DataTabledistrictDataTotalCount(sessionUserId,Search, country,state,district);
			
		}
		
		@RequestMapping(value = "/getStateListFormcon1", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getStateListFormcon1(String country_id) {
			//			String contry_id = "";
			return Dis_Dao.getCountry_List(country_id);
		}

}
