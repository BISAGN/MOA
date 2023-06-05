package com.AyushEdu.controller.Ug_Pg_Fee_Collection;

import java.io.IOException;
import java.security.Principal;
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
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Policy_Model_Master.TB_POLICYCATEGORY_MASTER;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_REGISTRATION_TYPE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Postgraduate.Pg_Gradu_ExamName_Dao;

import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_FEES_COLLECT;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Ug_Pg_Fee_Collection_Dao;
import com.twilio.http.Request;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Ug_Pg_Fee_Collection_ctrl {
	
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;
	
	 @Autowired
	 Ug_Pg_Fee_Collection_Dao upd;
	 
	 @Autowired
		private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@RequestMapping(value = "/ug_pg_fee_collection_Url", method = RequestMethod.GET)
	public ModelAndView ug_pg_fee_collection_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
	{

		try {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("ug_pg_fee_collection_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String role = session.getAttribute("role").toString();
			String userid = session.getAttribute("userId").toString();
			System.err.println("userid==============------------------"+userid);
			String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
				Mmap.put("msg", msg);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				Mmap.put("gettermList",common.gettermList(sessionFactory));
				Mmap.put("DegreeCateList",common.DegreeCateList(sessionFactory)); 
				Mmap.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
				Mmap.put("d_name", common.getDegreeList(sessionFactory));
				Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
				Mmap.put("role", role);
				if(role.toLowerCase().contains("student")) {
					Mmap.put("sturoledata",upd.getStuTypeofDegProf(role,userid));
				}
				if(role.toLowerCase().contains("institute")) {
					Mmap.put("Instno_of_part",upd.InstNoOfPart(inst_id));
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("ug_pg_fee_collection_Tiles");

	}
	
	@PostMapping("/getStudentNameAuto")
	public @ResponseBody List<Map<String, Object>> getStudentNameAuto(String autoString,String degree_cat,HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		String role = session.getAttribute("role").toString();
//		String count = upd.getVerifyStatus(stu_id);
		return upd.StudentDataAuto(autoString,role,degree_cat,instid);
	}
	
	@PostMapping("/getFeesDetails")
	public @ResponseBody List<Map<String, Object>> getFeesDetails(String studentid,HttpSession session, HttpServletRequest request) {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		return upd.Getfeesdetails(studentid,role,instid);
	}
	
	//
	@RequestMapping(value = "/degreefrom_fromybyinstlist_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> degreefrom_fromybyinstlist_ctrl(String type_of_degree,HttpSession session) {
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
	 ArrayList<ArrayList<String>> list = upd.degreefrom_fromybyinstlist_ctrl(type_of_degree,inst_id,role);
//	 System.err.println(" on list-work------------"+list);
		return list;
	}
	
	@RequestMapping(value = "/getSupplydata", method = RequestMethod.POST)
	public @ResponseBody String getSupplydata(String stu_id) {
	 String count = upd.getSupplyData(stu_id);
		return count;
	}
	
	@RequestMapping(value = "/isfeespaid", method = RequestMethod.POST)
	public @ResponseBody String isfeespaid(String stu_id,String prof) {
		String count = upd.isFessPaid(stu_id,prof);
		return count;
	}
	
	@RequestMapping(value = "/isfeesDataAvailable", method = RequestMethod.POST)
	public @ResponseBody String isfeesDataAvailable(String stu_id,String prof,String degree) {
	 String count = upd.CheckFessdataAvlbl(stu_id,prof,degree);
		return count;
	}
	

//@PostMapping(value = "/ug_pg_Feecollect_Action") 
//public ModelAndView ug_pg_Feecollect_Action(@Validated @ModelAttribute("ug_pg_Feecollect_CMD") EDU_LMS_FEES_COLLECT td, BindingResult result,
//		HttpServletRequest request, ModelMap model, HttpSession session, 
//	  RedirectAttributes ra) throws IOException {
//	
//	//System.err.println(" on action-------------");
//	
//	String type_of_degree = request.getParameter("type_of_degree");
//	String degree_name = request.getParameter("degree_name");
//	String term_id = request.getParameter("term_id");
//	String academic_year = request.getParameter("academic_year");
//	String eid = request.getParameter("id");
////	String degree_id = request.getParameter("degree_id");
//	
////	String student_name = request.getParameter("studentId_hid");
//	
//	
//	
////	String degree_name = request.getParameter("degree_name");
//	//degree_name , type_of_degree
//	
//	/*
//	if (name_of_the_exam == null || name_of_the_exam.trim().equals("")) {
//		ra.addAttribute("msg", "Please Enter Name Of Exam");
//		return new ModelAndView("redirect:pg_gradu_Examname_Url");
//	}
//
//
//	if (system_id == null || system_id.trim().equals("0")) {
//		ra.addAttribute("msg", "Please Enter System");
//		return new ModelAndView("redirect:pg_gradu_Examname_Url");
//	}
//	
//
//	if (degree_id == null || degree_id.trim().equals("0")) {
//		ra.addAttribute("msg", "Please Enter Degree");
//		return new ModelAndView("redirect:pg_gradu_Examname_Url");
//	}
//*/
///*
//	if (validation.maxlengthcheck100(name_of_the_exam) == false) {
//		ra.addAttribute("msg","name_of_the_exam "+ validation.MaxlengthcheckMSG100);
//		
//		return new ModelAndView("redirect:pg_gradu_Examname_Url");
//		
//	}
//	
//	if (validation.isOnlyAlphabetNumber(name_of_the_exam) == false) {
//		ra.addAttribute("msg","name_of_the_exam "+ validation.isOnlyAlphabetNumberMSG);
//		
//		return new ModelAndView("redirect:pg_gradu_Examname_Url");
//	}
//*/
//	
//	
//	int id = td.getId() > 0 ? td.getId() : 0;
//	if(!eid.equals("0")) {
//		id = Integer.parseInt(eid);
//	}
//	//Date date = new Date();
////	String username = principal.getName();
//
//	//String userid = session.getAttribute("userId_for_jnlp").toString();
//	
////	System.err.println("userid---->    "+userid);
//	
////	String userid = session.getAttribute("userid").toString();
////	System.out.println();
//
////System.err.println("img-event-------------"+eventimg);	
//	Session sessionHQL = this.sessionFactory.openSession();
//	Transaction tx = sessionHQL.beginTransaction();
//	try {
//		Long c = (Long) sessionHQL.createQuery(
//				"select count(id) from  EDU_LMS_FEES_COLLECT where degree_name=:degree_name and type_of_degree=:type_of_degree and term_id=:term_id and academic_year=:academic_year and id !=:id")
//			
//				.setParameter("degree_name", td.getDegree_name())
//				.setParameter("type_of_degree", td.getType_of_degree())
//				.setParameter("term_id", td.getTerm_id())
//				.setParameter("academic_year", td.getAcademic_year())
////				.setParameter("type_of_degree", td.getType_of_degree())
////				.setParameter("degree_name", td.getDegree_name())
//				.setParameter("id", td.getId()).uniqueResult();
//		
//		if (id == 0) {
//			td.setDegree_name(Integer.parseInt(degree_name));
//			td.setType_of_degree(Integer.parseInt(type_of_degree));
//			td.setTerm_id(Integer.parseInt(term_id));
//			td.setAcademic_year(academic_year);
////			td.setStudent_name(Integer.parseInt(student_name));
//			
//			
////			td.setDegree_name(Integer.parseInt(degree_name));
//			td.setId(id);
//			//td.setUserid(Integer.parseInt(userid));
//			//td.setCreated_by(userid);
////			td.setCreated_role(role);
//			//td.setCreated_date(date);
//			if (c == 0) {
//				sessionHQL.save(td);
//				sessionHQL.flush();
//				sessionHQL.clear();
//				ra.addAttribute("msg", "Data Saved Successfully.");
//			} else {
//				ra.addAttribute("msg", "Data already Exist.");
//			}
//		}
//		
//		else {
//			   /*td.setDegree_id(Integer.parseInt(degree_id));
//				td.setTerm_id(Integer.parseInt(term_id));
//				td.setStudent_name(student_name);
//				td.setAcademic_year(academic_year);
//				td.setModified_date(date);
//				
//				if (c == 0) {
//					td.setId(id);
//					String msg = Fdao.updateExamtype(td);
//					ra.addAttribute("msg", msg);
//					} 
//					else {
//						ra.addAttribute("msg", "Data already Exist.");
//					} */
//			}
//		tx.commit();
//
//	} catch (RuntimeException e) {
//		try {
//
//			ra.addAttribute("msg", "roll back transaction");
//		} catch (RuntimeException rbe) {
//			ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
//		}
//		throw e;
//	}  
//
//	 
//	/*catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} */
//	
//	finally {
//		if (sessionHQL != null) {
//			sessionHQL.close();
//		}
//	}
//	return new ModelAndView("redirect:ug_pg_fee_collection_Url");
//		
//}


//ug_pg_coll_fees for update 
	
	
	
//	@RequestMapping(value = "/ug_pg_coll_fees", method = RequestMethod.POST)
//	public @ResponseBody String ug_pg_coll_fees(String studentId_hid,String type_of_degree,String degree_name,String term_id, 
//			EDU_LMS_FEES_COLLECT td,HttpSession Session,HttpServletRequest request,RedirectAttributes ra) {
//
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		
//		String msg="";
//		String msg2="";
//		
//		int sid = 0;
//		int ru = 0;
//		
//		String userid = Session.getAttribute("userId_for_jnlp").toString();
//		String role = Session.getAttribute("role").toString();
////		String roleid = Session.getAttribute("roleid").toString();
//		
////		String type_of_degree = request.getParameter("type_of_degree");
////		String degree_name = request.getParameter("degree_name");
////		String term_id = request.getParameter("term_id");
////		String academic_year = request.getParameter("academic_year");
//		
//		System.err.println("type_of_degree-"+type_of_degree);
//		System.err.println("degree_name-"+degree_name);
//		System.err.println("term_id-"+term_id);
//		System.err.println("studentId_hid-"+studentId_hid);
//		
//		td.setDegree_name(Integer.parseInt(degree_name));
//		td.setType_of_degree(Integer.parseInt(type_of_degree));
//		td.setTerm_id(Integer.parseInt(term_id));
////		td.setAcademic_year(academic_year);
//		td.setStudent_name(Integer.parseInt(studentId_hid));
//		td.setCreated_by(userid);
//		td.setCreated_date(new Date());
//		sid = (int) sessionHQL.save(td);
//		sessionHQL.flush();
//		sessionHQL.clear();
//		
////		System.err.println("STudent id=================="+studentId_hid);
////		System.err.println("Role=================="+role);
////		System.err.println("RoleID=================="+roleid);
////		System.err.println("USERID=================="+userid);
//		
//		String hql="";
//		
//		if(role.contains("NCISM")) {
////			System.err.println("CHECK---===---===1");
//			 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
//					+ "	 where id=:id ";
//			 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//						.setParameter("id", Integer.parseInt(studentId_hid));
//				msg = query.executeUpdate() > 0 ? "1" : "0";
//			
//		}
//		if(role.contains("NCH")) {
////			System.err.println("CHECK---===---===2");
//			 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
//					+ "	 where id=:id ";
//			 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//						.setParameter("id", Integer.parseInt(studentId_hid));
//				msg = query.executeUpdate() > 0 ? "1" : "0";
//		}
//		
////		Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
////				.setParameter("id", Integer.parseInt(studentId_hid));
////		msg = query.executeUpdate() > 0 ? "1" : "0";
//		
//		
////		if(role.contains("ADM")) {
//			System.err.println("CHECK---===---===3");
//			List<Map<String, Object>> stu_cur_dtl = upd.GetRoleInfoFromStudentId(role,studentId_hid);
//			String stu_cuRole = stu_cur_dtl.get(0).get("role").toString();
//			String stu_curroled = stu_cur_dtl.get(0).get("role_id").toString();
//			String stu_userid = stu_cur_dtl.get(0).get("userid").toString();
//			String updroleid = "";
//			
//			if(stu_cuRole.contains("ADM")) {
//				
//				if(stu_curroled.equals("45")) {
//					updroleid = "25";
//				}
//				if(stu_curroled.equals("46")) {
//					updroleid = "26";
//				}
//				
//				String hql2 = "update UserRole set role_id=:role_id ,modified_on=:modified_on\n"
//						+ "	 where user_id=:user_id ";
//				
//				Query query2 = sessionHQL.createQuery(hql2).setParameter("role_id", Integer.parseInt(updroleid)).setParameter("modified_on", new Date())
//						.setParameter("user_id", Integer.parseInt(stu_userid));
//				msg2 = query2.executeUpdate() > 0 ? "1" : "0";
//				
//				if(msg2.equals("1")) {
//					ru = 1;
//				}
//				
//				System.err.println("msg=========================="+msg+"==="+msg2);
//				
//			}
//			
//			
//			
////		}
//		
//		if(ru == 0) {
////			System.err.println("CHECK---===---===4");
//			if (msg.equals("1") && sid > 0) {
//				msg	= "Payment Done Succefully";
//				tx.commit();
//			} else {
//				msg = "Something Went Wrong !!!";
//			}
//		}
//		
//		if(ru == 1) {
////			System.err.println("CHECK---===---===5");
//			if (msg.equals("1") && msg2.equals("1") && sid > 0) {
//				msg	= "Payment Done Successfully";
//				tx.commit();
//			} else {
//				msg = "Something Went Wrong !!!";
//			}
//		}
//
//		return msg;
//	}

	
	
	
	

@RequestMapping(value = "/ug_pg_coll_fees", method = RequestMethod.POST)
public @ResponseBody String ug_pg_coll_fees(String studentId_hid,String type_of_degree,String degree_name,String term_id, String fullorpart,String no_of_part,
		String ch_tb_ids,EDU_LMS_FEES_COLLECT td,HttpSession Session,HttpServletRequest request,RedirectAttributes ra) {

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	String msg="";
	String msg2="";
	String msg3="";
	
	int sid = 0;
	int ru = 0;
	
	String userid = Session.getAttribute("userId_for_jnlp").toString();
	String role = Session.getAttribute("role").toString();
	String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
	String studentid = request.getParameter("studentId_hid");
	System.err.println("ug_pg_coll_fees-----------------studentid"+studentid);
	
	ArrayList<ArrayList<String>> list = upd.Getlastnoofpart(studentid,role);
	
	System.err.println("ug_pg_coll_fees--------------list"+list.get(0).get(1));
	String part_ser = list.get(0).get(1);
	System.err.println("part_ser==============="+part_ser);
	String nop = list.get(0).get(2);
	System.err.println("nop==============="+nop);
	
	System.err.println("aaaaaaaaaaaaaa"+part_ser.equals(nop));
	
	td.setDegree_name(Integer.parseInt(degree_name));
	td.setType_of_degree(Integer.parseInt(type_of_degree));
	td.setTerm_id(Integer.parseInt(term_id));
	td.setStudent_name(Integer.parseInt(studentId_hid));
	td.setCreated_by(userid);
	td.setCreated_date(new Date());
	sid = (int) sessionHQL.save(td);
	sessionHQL.flush();
	sessionHQL.clear();
	
	String hql="";
	
	String[] chidArr = ch_tb_ids.split(",");
	
	if(fullorpart.equals("1")) {
		
		for(int p=0;p<chidArr.length;p++) {
			 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
						+ "	 where id=:id ";
				 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
							.setParameter("id",Integer.parseInt(chidArr[p]));
				 msg3 = query.executeUpdate() > 0 ? "1" : "0";
		}
		
	}
	if(fullorpart.equals("2")) {
		
		 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
					+ "	 where id=:id ";
			 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
						.setParameter("id",Integer.parseInt(chidArr[0]));
			 msg3 = query.executeUpdate() > 0 ? "1" : "0";
		
	}
	
	if(fullorpart.equals("1")) {
	
		if(role.contains("NCISM")) {
			 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
					+ "	 where id=:id ";
			 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(studentId_hid));
				msg = query.executeUpdate() > 0 ? "1" : "0";
			
		}
		if(role.contains("NCH")) {
			 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
					+ "	 where id=:id ";
			 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(studentId_hid));
				msg = query.executeUpdate() > 0 ? "1" : "0";
		}
	} else if(fullorpart.equals("2")) {
//		if(part_ser.equals(nop)) {
			if(role.contains("NCISM")) {
				 hql = "update EDU_LMS_STUDENT_DETAILS set modified_by=:modified_by,modified_date=:modified_date\n"
						+ "	 where id=:id ";
				 Query query = sessionHQL.createQuery(hql).setParameter("modified_by", userid).setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(studentId_hid));
					msg = query.executeUpdate() > 0 ? "1" : "0";
				
			}
			if(role.contains("NCH")) {
				 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set modified_by=:modified_by,modified_date=:modified_date\n"
						+ "	 where id=:id ";
				 Query query = sessionHQL.createQuery(hql).setParameter("modified_by", userid).setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(studentId_hid));
					msg = query.executeUpdate() > 0 ? "1" : "0";
			}
//		}
			if(part_ser.equals(nop)) {
				if(role.contains("NCISM")) {
					 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
							+ "	 where id=:id ";
					 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(studentId_hid));
						msg = query.executeUpdate() > 0 ? "1" : "0";
					
				}
				if(role.contains("NCH")) {
					 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
							+ "	 where id=:id ";
					 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(studentId_hid));
						msg = query.executeUpdate() > 0 ? "1" : "0";
				}
			}
	}
	
//	if(role.contains("ADM")) {
//	-----------
//		List<Map<String, Object>> stu_cur_dtl = upd.GetRoleInfoFromStudentId(role,studentId_hid);
//		String stu_curRole = stu_cur_dtl.get(0).get("role").toString();
//		String stu_curroled = stu_cur_dtl.get(0).get("role_id").toString();
//		String stu_userid = stu_cur_dtl.get(0).get("userid").toString();
//		String updroleid = "";
//		if(stu_curRole.contains("ADM")) {
//			if(stu_curroled.equals("45")) {
//				updroleid = "25";
//			}
//			if(stu_curroled.equals("46")) {
//				updroleid = "26";
//			}
//			
//			String hql2 = "update UserRole set role_id=:role_id ,modified_on=:modified_on\n"
//					+ "	 where user_id=:user_id ";
//			
//			Query query2 = sessionHQL.createQuery(hql2).setParameter("role_id", Integer.parseInt(updroleid)).setParameter("modified_on", new Date())
//					.setParameter("user_id", Integer.parseInt(stu_userid));
//			msg2 = query2.executeUpdate() > 0 ? "1" : "0";
//			
//			if(msg2.equals("1")) {
//				ru = 1;
//			}
//		}
//	---------------------
//	}
	
	if(ru == 0) {
		if (msg.equals("1") && sid > 0 && msg3.equals("1")) {
			msg	= "Payment Done Successfully";
			tx.commit();
		} else {
			msg = "Something Went Wrong !!!";
		}
	}
	
	if(ru == 1) {
		if (msg.equals("1") && sid > 0 && msg3.equals("1")) {
			msg	= "Payment Done Successfully";
			tx.commit();
		} else {
			msg = "Something Went Wrong !!!";
		}
	}

	return msg;
}

@RequestMapping(value = "/getVerifiedStatus", method = RequestMethod.POST)
public @ResponseBody ArrayList<ArrayList<String>> getVerifiedStatus(String stu_id,HttpSession Session) {
	String role = Session.getAttribute("role").toString();
 ArrayList<ArrayList<String>> list = upd.getVerifyStatus(stu_id,role);
 System.err.println("getVerifiedStatus Dao"+list);
	return list;
}
 
          
/*--Fess Part And Full -- */

//@RequestMapping(value = "/payfees_part", method = RequestMethod.POST)
//public @ResponseBody   List<Map<String, Object>> payfees_part(String id,HttpSession session,HttpServletRequest request) {
//	
//	String role = session.getAttribute("role").toString();
//	String userid = session.getAttribute("userId_for_jnlp").toString();
//	String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
//	String studentid = request.getParameter("stu_hid");
// List<Map<String, Object>> list = upd.Getfeesdetails(studentid,role,inst_id);
//	return list;
//}



}

