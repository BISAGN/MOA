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

import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.dao.NotificationDAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class NotificationController {
	
	@Autowired
	NotificationDAO notiDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	


			@RequestMapping(value = "/getnotifilist", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> getnotifilist( 					
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
			
	
			@RequestMapping(value = "/getseeallnotilist", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> getseeallnotilist( 					
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
			
			
			@RequestMapping(value = "/admin/UpdateNotification", method = RequestMethod.POST)
			public @ResponseBody String UpdateNotification(HttpServletRequest request, String id,HttpSession session){

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

			
			
			
			
			@RequestMapping(value = "getansMethod", method = RequestMethod.POST)
			public @ResponseBody String getansMethod(ModelMap model, HttpSession session, HttpServletRequest request,Principal principal,
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
						
						TB_NOTIFICATION nt = new TB_NOTIFICATION();

						nt.setCreated_by(username);
						nt.setCreated_date(date);
						nt.setMessage(ans);
						nt.setTo_name_sent(getnotificationList(id).get(0).getFrom_name_send());
							nt.setFrom_name_send(userId);
					   // nt.setTo_name_receive(userId);
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

			
			@RequestMapping(value = "/getnotificationList", method = RequestMethod.POST)
			public @ResponseBody List<TB_NOTIFICATION> getnotificationList (String from_name_send) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from TB_NOTIFICATION where id=:id");
				
				q.setParameter("id", Integer.parseInt( from_name_send));
				@SuppressWarnings("unchecked")
				List<TB_NOTIFICATION> clist = (List<TB_NOTIFICATION>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
}
