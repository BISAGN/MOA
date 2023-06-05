package com.AyushEdu.controller.AyushId_Directory;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.RBAC.RoleMstrController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.AyushEdu.Models.AyushId_Directory.AYUSH_ID_DIRECTORY_PARENT;
import com.AyushEdu.Models.AyushId_Directory.AYUSH_ID_ENTITY_DIRECTORY_CHILD;
import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;
import com.AyushEdu.dao.AyushId_Directory.AyushId_DirectoryDAO;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AyushId_Directory_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	AyushId_DirectoryDAO addao;

	@Autowired
	CommonController common;

	@Autowired
	RoleMstrController roleMstrController;


	@RequestMapping(value = "/SearchAyushDirectoryUrl", method = RequestMethod.GET)
	public ModelAndView SearchAyushDirectoryUrl(ModelMap Mmap, HttpSession session,
									  @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//TODO: remove return after adding menu
		//try {
		//  System.out.println(request.toString());
//            if(request.getHeader("Referer") == null ) {
////			 session.invalidate();
//                Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//                return new ModelAndView("redirect:/landingpage");
//            }
//            String roleid1 = session.getAttribute("roleid").toString();
//            Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);
//            if(val == false) {
//                return new ModelAndView("AccessTiles");
//            }

//            Mmap.put("msg", msg);
//            Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		Mmap.put("msg", msg);
		Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
		return new ModelAndView("Search_Ayush_Id_Tiles");

	}

	public boolean SaveAyushId_Directory(String ayush_id, String user_id, String aadhaar_no, String role_id,HttpSession session){
		
		BigInteger userid = BigInteger.ZERO;
		userid=userid.add(BigInteger.valueOf(Long.parseLong(user_id)));
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		    String msg = "";
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String Rolestatus = "";
				 
		try{
			
			
			String temp_id = (String) sessionHQL.createQuery(
					"select ayush_id from  AYUSH_ID_DIRECTORY_PARENT pr where upper(aadhaar_no)=:aadhaar_no")
					.setParameter("aadhaar_no", aadhaar_no.toUpperCase())
					.uniqueResult();
			
			if (temp_id != "" && temp_id != null && temp_id != "null" && !temp_id.equals("") && temp_id.substring(4, 8).equals("TEMP")) {
				//update query
				int app = sessionHQL.createQuery(
						"update AYUSH_ID_DIRECTORY_PARENT set ayush_id=:ayush_id where ayush_id=:ayush_id")
						.setParameter("ayush_id", ayush_id).executeUpdate();
			}
			
			int TRoll=0;
			
			Long c = (Long) sessionHQL.createQuery("select count(pr.id) from  AYUSH_ID_DIRECTORY_PARENT pr where aadhaar_no=:aadhaar_no")
					.setParameter("aadhaar_no", aadhaar_no)
					.uniqueResult();
			
				if(c==0) {
					
				AYUSH_ID_DIRECTORY_PARENT pr = new AYUSH_ID_DIRECTORY_PARENT();
				pr.setAyush_id(ayush_id);
				pr.setAadhaar_no(aadhaar_no);
				pr.setUser_id(userid);
				pr.setCreated_date(date);	
				pr.setCreated_by(username);
				BigInteger p_id = (BigInteger)sessionHQL.save(pr);
				AYUSH_ID_ENTITY_DIRECTORY_CHILD ch = new AYUSH_ID_ENTITY_DIRECTORY_CHILD();
				ch.setP_id(p_id);
				ch.setRole_id(Integer.parseInt(role_id));
				ch.setCreated_date(date);
				ch.setCreated_by(username);
				sessionHQL.save(ch);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				
			}else {
				
				 TRoll = (int) sessionHQL.createQuery(
						"select ach.role_id from AYUSH_ID_DIRECTORY_PARENT pr,AYUSH_ID_ENTITY_DIRECTORY_CHILD ach where pr.id=ach.p_id and aadhaar_no=:aadhaar_no order by ach.id DESC")
						.setParameter("aadhaar_no", aadhaar_no)
						.getSingleResult();
				 
				Rolestatus =  addao.getAyushRoleStatus(String.valueOf(TRoll));
			}
				if(c>0 && TRoll!=Integer.parseInt(role_id) && Rolestatus.toUpperCase().equals("U") ) {
					Query query = sessionHQL.createQuery("select id from AYUSH_ID_DIRECTORY_PARENT where user_id=:user_id")
							.setParameter("user_id", userid);
					@SuppressWarnings("unchecked")
					BigInteger p_id = (BigInteger) query.uniqueResult();
					
					Query query2 = sessionHQL.createQuery("select id from AYUSH_ID_ENTITY_DIRECTORY_CHILD where p_id=:p_id")
							.setParameter("p_id", p_id);
					@SuppressWarnings("unchecked")
					BigInteger child_id = (BigInteger) query2.uniqueResult();
					
					AYUSH_ID_ENTITY_DIRECTORY_CHILD pda = (AYUSH_ID_ENTITY_DIRECTORY_CHILD) sessionHQL.get(AYUSH_ID_ENTITY_DIRECTORY_CHILD.class,child_id);
					pda.setRole_id(Integer.parseInt(role_id));
					pda.setModified_date(date);
					pda.setModified_by(username);
					sessionHQL.update(pda);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
				}
				
				else if(c>0 && TRoll!=Integer.parseInt(role_id)  && Rolestatus.toUpperCase().equals("N") ) {
				Query query = sessionHQL.createQuery("select id from AYUSH_ID_DIRECTORY_PARENT where user_id=:user_id")
						.setParameter("user_id", userid);
				@SuppressWarnings("unchecked")
				BigInteger p_id = (BigInteger) query.uniqueResult();
				
				AYUSH_ID_ENTITY_DIRECTORY_CHILD ch = new AYUSH_ID_ENTITY_DIRECTORY_CHILD();
				ch.setP_id(p_id);
				ch.setRole_id(Integer.parseInt(role_id));
				ch.setCreated_date(date);
				ch.setCreated_by(username);
				sessionHQL.save(ch);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				
			}
		}
		catch (Exception e) {
			tx.rollback();
			return false;
		}
		finally {
			sessionHQL.close();
		}
		return true;
	}

	@PostMapping("/getFilterAyushIdDirectory_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

																			  String Search, String orderColunm, String orderType, String aadhar_no, String ayush_id , String user_id , String user_role_id , String user_name , String login_name , String email_id  ) {

		return addao.DataTableAyushIdDirectoryList(startPage, pageLength, Search, orderColunm, orderType, aadhar_no,ayush_id , user_id , user_role_id , user_name , login_name , email_id);

	}

	@PostMapping("/getTotalAyushIdDirectory_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,  String aadhar_no, String ayush_id , String user_id , String user_role_id ,  String user_name , String login_name , String email_id  ) {
		return addao.DataTableAyushIdDirectoryTotalCount(Search, aadhar_no , ayush_id , user_id , user_role_id , user_name , login_name , email_id);

	}

}
