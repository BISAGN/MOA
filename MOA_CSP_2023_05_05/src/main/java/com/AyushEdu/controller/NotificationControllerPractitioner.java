package com.AyushEdu.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.TB_NOTIFICATION_PRACTITIONER;
import com.AyushEdu.dao.NotificationDAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class NotificationControllerPractitioner {
	
	@Autowired
	NotificationDAO notiDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	
	//-------------------------------------	 notification start
	
	
//			@RequestMapping(value = "/getnotifilist", method = RequestMethod.POST)
//			public @ResponseBody List<Map<String, Object>> getnotifilist( 					
//					HttpSession session,ModelMap model,HttpServletRequest request,
//					@RequestParam(value = "msg", required = false) String msg) throws SQLException{
//			  
//			 String  role = session.getAttribute("role").toString();
//			 String  userid = session.getAttribute("userId_for_jnlp").toString();
//			 List<Map<String, Object>> list= notiDao.notiboxlist("", "", "", "", "",userid,role,"","","");
//				 System.out.println("list   "+list.size());
//			 String token = UUID.randomUUID().toString();
//				request.getSession().setAttribute("newtoken", token);
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();	
//				columns.put("getnewtoken", token);
//				list.add(columns);
//				
//			 return list;
//			}

			@RequestMapping(value = "/getnotifilistprac", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> getnotifilistprac( 					
					HttpSession session,ModelMap model,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) throws SQLException{
			  
			 String  role = session.getAttribute("role").toString();
			 String  userid = session.getAttribute("userId_for_jnlp").toString();
			 List<Map<String, Object>> list= notiDao.notiboxlist("", "", "", "", "",userid,role,"","","");
				 System.out.println("list   "+list.size());
			 String token = UUID.randomUUID().toString();
				request.getSession().setAttribute("newtoken", token);
				Map<String, Object> columns = new LinkedHashMap<String, Object>();	
				columns.put("getnewtoken", token);
				list.add(columns);
				
			 return list;
			}
			
	
			@RequestMapping(value = "/getseeallnotilistprac", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> getseeallnotilistprac( 					
					HttpSession session,ModelMap model,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) throws SQLException{
			  
			 String  role = session.getAttribute("role").toString();
			 String  userid = session.getAttribute("userId_for_jnlp").toString();
			 List<Map<String, Object>> list= notiDao.notiseeall_list("", "", "", "", "",userid,role,"","","");
				 
			 String token = UUID.randomUUID().toString();
				request.getSession().setAttribute("newtoken", token);
				Map<String, Object> columns = new LinkedHashMap<String, Object>();	
				columns.put("getnewtoken", token);
				list.add(columns);
				
			 return list;
			}
			
/////////////////////////notification_17_3///snehal_meera  */	
			
//			@RequestMapping(value = "/admin/UpdateNotification", method = RequestMethod.POST)
//			public @ResponseBody String UpdateNotification(HttpServletRequest request, String id,HttpSession session){
//
//				Session session0 = this.sessionFactory.openSession();
//				Transaction tx0 = session0.beginTransaction();
//			//	String username = session.getAttribute("userId").toString();
//				 String  role = session.getAttribute("role").toString();
//				
//				try {
//							
//							String hql = "update TB_NOTIFICATION set to_name_receive=:to_name_receive where id=:id";
//							
//							@SuppressWarnings("rawtypes")
//							Query query = session0.createQuery(hql)
//								.setParameter("id", Integer.parseInt(id))
//								.setParameter("to_name_receive", role);
//								 query.executeUpdate();
//									 
//							session0.flush();
//							session0.clear();
//							
//							tx0.commit();
//							return "Data Saved Successfully";
//				} catch (Exception e) {
//					e.printStackTrace();
//					return "Data not Saved";
//				} finally {
//				}
//			}		
			
			
			@RequestMapping(value = "/admin/UpdateNotificationprac", method = RequestMethod.POST)
			public @ResponseBody String UpdateNotificationprac(HttpServletRequest request, String id,HttpSession session){

				Session session0 = this.sessionFactory.openSession();
				Transaction tx0 = session0.beginTransaction();
				String userId = session.getAttribute("userId").toString();
				 String  role = session.getAttribute("role").toString();
				
				try {
							
							String hql = "update TB_NOTIFICATION set to_name_receive=:to_name_receive where id=:id";
							
							@SuppressWarnings("rawtypes")
							Query query = session0.createQuery(hql)
								.setParameter("id", Integer.parseInt(id))
								.setParameter("to_name_receive", userId);
								 query.executeUpdate();
//								 window.location();
									
									 
							session0.flush();
							session0.clear();
							
							tx0.commit();
							return "0";
				} catch (Exception e) {
					e.printStackTrace();
					return "1";
				} finally {
				}
			}		

			
			
			
			
			@RequestMapping(value = "getansMethodprac", method = RequestMethod.POST)
			public @ResponseBody String getansMethodprac(ModelMap model, HttpSession session, HttpServletRequest request,Principal principal,
				@RequestParam(value = "id", required = false) String id,
				@RequestParam(value = "ans", required = false) String ans
				) {

				 	Date date = new Date();
				 	String username = principal.getName();
				 	String userId = session.getAttribute("userId_for_jnlp").toString();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					String msg = "";

					try {
						
						TB_NOTIFICATION_PRACTITIONER nt = new TB_NOTIFICATION_PRACTITIONER();

						nt.setCreated_by(username);
						nt.setCreated_date(date);
						nt.setMessage(ans);
						nt.setTo_name_sent(getnotificationListprac(id).get(0).getFrom_name_send());
							nt.setFrom_name_send(getnotificationListprac(id).get(0).getTo_name_sent());
						nt.setStatus("1");
						
						sessionHQL.save(nt);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
						msg="Query Submmitted Successfully";

					} catch (Exception e) {
						e.printStackTrace();
						msg="Something went wrong !!!";
						tx.rollback();
					}
					return msg;
			}
			
			@RequestMapping(value = "/getnotificationListprac", method = RequestMethod.POST)
			public @ResponseBody List<TB_NOTIFICATION_PRACTITIONER> getnotificationListprac (String from_name_send) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from TB_NOTIFICATION_PRACTITIONER where id=:id");
				
				q.setParameter("id", Integer.parseInt( from_name_send));
				@SuppressWarnings("unchecked")
				List<TB_NOTIFICATION_PRACTITIONER> clist = (List<TB_NOTIFICATION_PRACTITIONER>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
}
