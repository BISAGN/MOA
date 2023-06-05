package com.AyushEdu.controller.LMS_Master_Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Query;
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

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Subject_CD_DAO;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.SubjectDAO;

import freemarker.core.ParseException;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Subject_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

//	
	@Autowired
	CommonController common;

	@Autowired
	private SubjectDAO Sub_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Subject_CD_DAO  DM_dirdao;
	
	// -------------------------------Subject For page Open -------------------------------------

		 @RequestMapping(value = "Subject", method = RequestMethod.GET)
		 public ModelAndView Subject(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

//				try {
//					
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
			 String  roleid = session.getAttribute("roleid").toString();
//		        Boolean val = roledao.ScreenRedirect("Subject", roleid);	


			 Mmap.put("msg", msg);
			 Mmap.put("getsysList", common.getsysList( sessionFactory));
			 Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
//		    ArrayList<ArrayList<String>> list = Dis_Dao.search_Subject_name(0,0,"","active");
//			 Mmap.put("list", list);
			 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			 Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
			 
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			 return new ModelAndView("SubjectTiles");
		 }
		 
			// -------------------------------Subject save -------------------------------------

			 @RequestMapping(value = "/SubjectAction",method=RequestMethod.POST)
				public ModelAndView SubjectAction(@ModelAttribute("SubjectCMD") EDU_LMS_SUBJECT_MSTR rm, 
						BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session,
						@RequestParam(value = "msg", required = false) String msg) {
				 
				 if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
			    System.err.println(result.hasErrors());
			    String  roleid = session.getAttribute("roleid").toString();
			    ///Boolean val = roledao.ScreenRedirect("Subject", roleid);	
		 
					int subject_id = rm.getId() > 0 ? rm.getId() : 0;
					
					System.err.println("subject_id------------>  "+subject_id);
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String subject_name = request.getParameter("subject_name").trim();
					String status =rm.getStatus();
					
					System.err.println("dddddddddd"+subject_name);
					 Session sessionHQL = sessionFactory.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					
//					 
								if (rm.getSystem_id() == 0) {
									model.put("msg", "Please Select System.");
									return new ModelAndView("redirect:Subject");
								}
						
								if (rm.getCourse_id() == 0) {
									model.put("msg", "Please Select Course.");
									return new ModelAndView("redirect:Subject");
								}
						
								if (subject_name.equals("") || subject_name.equals("null")
										|| subject_name.equals(null)) {
									model.put("msg", "Please Enter Subject.");
						
									return new ModelAndView("redirect:Subject");
								}
								if (rm.getStatus() == "0" || rm.getStatus().trim().equals("0")) {
									model.addAttribute("msg", "Please Select Status.");
									return new ModelAndView("redirect:Subject");
								}
								if (rm.getStatus() == "2" || rm.getStatus().equals("2")) {
									
									model.put("msg", "Only Select Active Status.");
						
									return new ModelAndView("redirect:Subject");
						
								}

						try{
						
							
							@SuppressWarnings("deprecation")
							Long c = (Long) sessionHQL.createQuery("select count(id) from EDU_LMS_SUBJECT_MSTR where subject_name=:subject_name  and status=:status  and id!=:id")
							.setString("subject_name",subject_name)  
							.setString("status", status)
							.setInteger("id", subject_id)              
							.uniqueResult();
							
							//System.err.println("c------->  "+c);
							int idd =0;
						if (subject_id == 0) {
							rm.setCreated_by(username);
							rm.setCreated_date(date);
							rm.setSubject_name(subject_name);
							if (c == 0) {
								idd = (int)sessionHQL.save(rm);
								sessionHQL.flush();
								sessionHQL.clear();
								model.put("msg", "Data Saved Successfully.");

							} else {
								model.put("msg", "Data already Exist.");
							}
						}
						else {
							
							if (c == 0) {
								//String msg = rankdao.updaterankcode(rm);
								//model.put("msg", msg);
							} else {
								model.put("msg", "Data already Exist.");
							}
						}
						tx.commit();
						//For Core Directory
						DM_dirdao.Insert_subject_Mstr_Data(idd);
					}catch(RuntimeException e){
						e.printStackTrace();
						try{
							tx.rollback();
							model.put("msg", "roll back transaction");
						}catch(RuntimeException rbe){
							model.put("msg", "Couldn�t roll back transaction " + rbe);
						}
						throw e;
					}finally{
						if(sessionHQL!=null){
						   sessionHQL.close();
						}
					}	
					return new ModelAndView("redirect:Subject");
				}
			 
//				// -------------------------SEARCH Subject  -------------------------------------

			 @RequestMapping(value = "/admin/getSearch_Subject_Master", method = RequestMethod.POST)
				public ModelAndView getSearch_Subject_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "system_id1", required = false) int system_id1,
						@RequestParam(value = "course_id1", required = false) int course_id1,
						@RequestParam(value = "Subject_name1", required = false) String Subject_name1 ,
				        @RequestParam(value = "status1", required = false) String status1) 
			 {
				 if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				 String  roleid = session.getAttribute("roleid").toString();
				 //Boolean val = roledao.ScreenRedirect("Subject", roleid);	

//				 ArrayList<ArrayList<String>> list = Dis_Dao.search_Subject_name(system_id1,course_id1,Subject_name1,status1);
				        Mmap.put("system_id1", system_id1);
					    Mmap.put("course_id1", course_id1);
						Mmap.put("Subject_name1", Subject_name1);
						Mmap.put("status1", status1);

						Mmap.put("system_id", common.getsysList( sessionFactory));
						 Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
						 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
						 Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
						
//						System.err.println("list---- "+list);
//							Mmap.put("list", list);
					return new ModelAndView("SubjectTiles");
				}
			 
				@PostMapping("/getFiltersubject")
				public @ResponseBody List<Map<String, Object>> getFiltersubject(int startPage, int pageLength,
						String Search, String orderColunm, String orderType, String system,  String course ,  String subject , String status) {
					return Sub_Dao.search_Subject_name(startPage, pageLength, Search, orderColunm, orderType, system,course,subject,status);
				}
				
				@PostMapping("/getTotalsubject_dataCount")
				public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String system,  String course ,  String subject, String status) {
					return Sub_Dao.DataTablesubjectDataTotalCount(sessionUserId,Search, system,course,subject);
					
				}
				
//				// -------------------------Edit Subject For page Open -------------------------------------

				 @RequestMapping(value = "Edit_Subject",method=RequestMethod.POST)
					public ModelAndView Edit_Subject(@ModelAttribute("id2") String updateid,ModelMap Mmap,
							HttpSession sessionEdit,HttpServletRequest request,
							@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					 if(request.getHeader("Referer") == null ) { 
						 sessionEdit.invalidate();
						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					 String  roleid = sessionEdit.getAttribute("roleid").toString();
					// Boolean val = roledao.ScreenRedirect("Subject", roleid);	
						EDU_LMS_SUBJECT_MSTR disDetails = Sub_Dao.getSubjectByid(Integer.parseInt(updateid));
							Mmap.put("Edit_SubjectCMD", disDetails);
							Mmap.put("system_id", common.getsysList( sessionFactory));
							 Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
							 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
							 Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
							Mmap.put("msg", msg);
						return new ModelAndView("Edit_SubjectTiles");
					}
				 
//					// -------------------------Edit Subject_Action -------------------------------------

					
					@RequestMapping(value = "/Edit_Subject_Action", method = RequestMethod.POST)
					public ModelAndView Edit_Subject_Action(@ModelAttribute("Edit_SubjectCMD") EDU_LMS_SUBJECT_MSTR rs,
							HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
						 if(request.getHeader("Referer") == null ) { 
					//		 session.invalidate();
							 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
						String roleid1 = session.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
						 String  roleid = session.getAttribute("roleid").toString();
						 //Boolean val = roledao.ScreenRedirect("Subject", roleid);	
						String username = session.getAttribute("username").toString();

						int id = Integer.parseInt(request.getParameter("id"));
						int system_id = Integer.parseInt(request.getParameter("system_id"));
						int course_id = Integer.parseInt(request.getParameter("course_id"));
						String subject_name = request.getParameter("subject_name").trim();
						String status = request.getParameter("status");
						
								if (rs.getSystem_id() == 0) {
									EDU_LMS_SUBJECT_MSTR disDetails = Sub_Dao.getSubjectByid((id));
									model.put("Edit_SubjectCMD", disDetails);

									model.put("system_id", common.getsysList( sessionFactory));
									model.put("course_id", common.getCourseNamelist(sessionFactory));
									model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
									model.put("msg", "Please Select System");
									//model.put("id2", id);
									return new ModelAndView("Edit_SubjectTiles");
								}
						
							  if (rs.getCourse_id() == 0) {
								  EDU_LMS_SUBJECT_MSTR disDetails = Sub_Dao.getSubjectByid((id));
									model.put("Edit_SubjectCMD", disDetails);

									model.put("system_id", common.getsysList( sessionFactory));
									model.put("course_id", common.getCourseNamelist(sessionFactory));
									model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
									model.put("getMedcourseName", common.getCourseNamelist(sessionFactory));
									
									model.put("msg", "Please Select Course.");
//									model.put("id2", id);
									return new ModelAndView("Edit_SubjectTiles");
								}
						
							 if (subject_name.equals("") || subject_name.equals("null")
										|| subject_name.equals(null)) {
								 EDU_LMS_SUBJECT_MSTR disDetails = Sub_Dao.getSubjectByid((id));
									model.put("Edit_SubjectCMD", disDetails);
									model.put("system_id", common.getsysList( sessionFactory));
									model.put("course_id", common.getCourseNamelist(sessionFactory));
									model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
									model.put("msg", "Please Enter Subject.");
									//model.put("id2", id);
									return new ModelAndView("Edit_SubjectTiles");
								}
							 if (rs.getStatus() == "2" || rs.getStatus().equals("2")) {
								 EDU_LMS_SUBJECT_MSTR disDetails = Sub_Dao.getSubjectByid((id));
									model.put("Edit_SubjectCMD", disDetails);
									model.put("system_id", common.getsysList( sessionFactory));
									model.put("course_id", common.getCourseNamelist(sessionFactory));
									model.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
									model.put("msg", "Only Select Active Status.");
									return new ModelAndView("Edit_SubjectTiles");
						
								}

						
						
						Session session1 = sessionFactory.getSessionFactory().openSession();
				        Transaction tx = session1.beginTransaction();
							 try {
								
								 Query q0 = session1.createQuery("select count(id) from EDU_LMS_SUBJECT_MSTR where system_id=:system_id and "
								 		+ "course_id=:course_id and subject_name=:subject_name  and status=:status and id !=:id");
								 
									q0.setParameter("subject_name", subject_name); 
									q0.setParameter("system_id", rs.getSystem_id());
									q0.setParameter("course_id", course_id);
									q0.setParameter("status", status); 
									q0.setParameter("id", rs.getId()); 
									Long c = (Long) q0.uniqueResult();
									
									if(c==0) {
										 String hql = "update EDU_LMS_SUBJECT_MSTR set system_id=:system_id,course_id=:course_id,subject_name=:subject_name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
													+ " where id=:id";
								                                   
								    	  Query query = session1.createQuery(hql)
								    			  .setInteger("system_id",rs.getSystem_id())
								    			  .setInteger("course_id",course_id)
								    			  .setString("status",status)
								    			  	.setString("subject_name",subject_name)
													.setString("modified_by", username)
													.setDate("modified_date", new Date())
													.setInteger("id",id);
								                    msg = query.executeUpdate() > 0 ? "1" :"0";
								                    tx.commit(); 
								                  //For Core Directory
								    				DM_dirdao.Update_subject_Mstr_Data( id, system_id, course_id,subject_name, status,  username,  new Date());
								                    
								                    if(msg == "1") {
								                    	 model.put("msg", "Data Updated Successfully.");
								                    }
								                    else {
								                    	model.put("msg", "Data Not Updated.");
								                    }
									}
									else {
										model.put("msg", "Data already Exist.");
									}
								
							  }catch(RuntimeException e){
					              try{
					                      tx.rollback();
					                      model.put("msg", "roll back transaction");
					              }catch(RuntimeException rbe){
					                      model.put("msg", "Couldn�t roll back transaction " + rbe);
					              }
					              throw e;
					             
							}finally{
								if(session1!=null){
									session1.close();
								}
							}
						return new ModelAndView("redirect:Subject");
					}
				 
					
				 // -------------------------Delete Subject  -------------------------------------

				
					
					@RequestMapping(value = "/delete_Subject", method = RequestMethod.POST)
					public @ResponseBody ModelAndView delete_Subject(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
							HttpSession sessionA, ModelMap model,
							@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
						if(request.getHeader("Referer") == null ) { 
				//			 session.invalidate();
							 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
						String roleid1 = session.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Subject", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
						
						 String  roleid = session.getAttribute("roleid").toString();
							ArrayList<String> liststr = new ArrayList<String>();
						
						String username = session.getAttribute("username").toString();
						
						try {
							 Session sessionHQL = sessionFactory.getSessionFactory().openSession();
							 Transaction tx = sessionHQL.beginTransaction();
							 
							 String hqlUpdate = "update EDU_LMS_SUBJECT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status"
													+ " where id=:id";
							
							 int app = sessionHQL.createQuery(hqlUpdate).setString("status","2")
									.setString("modified_by", username)
									.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
							tx.commit();
							//For Core Directory
							DM_dirdao.Delete_subject_Mstr_Data(id);
							sessionHQL.close();

							if (app > 0) {
								liststr.add("Delete Successfully.");
							} else {
								liststr.add("Delete Unsuccessfully.");
							}
							model.put("msg",liststr.get(0));

						} catch (Exception e) {
							liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
							model.put("msg",liststr.get(0));
						}
						return new ModelAndView("redirect:Subject");
					}
}
