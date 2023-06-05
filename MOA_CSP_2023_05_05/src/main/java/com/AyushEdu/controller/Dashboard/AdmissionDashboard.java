package com.AyushEdu.controller.Dashboard;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.dao.Dashboard.AdmissionDashboardDao;
import com.AyushEdu.dao.Dashboard.e_fromAdmissionDashboardDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AdmissionDashboard {

	@Autowired
	private CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private AdmissionDashboardDao AD;
	@Autowired
	EmailConfig emailsend;
	
	@RequestMapping(value = "/AdmissionDashboardUrl", method = RequestMethod.GET)
	public ModelAndView AdmissionDashboardUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String role = session.getAttribute("role").toString();
		String user_id = session.getAttribute("userId_for_jnlp").toString();

		if(role.equals("NCISM")) {
			Mmap.put("R", "NCISM");

		}else if(role.equals("NCH")){
		
			Mmap.put("R", "NCH");
		}
		else {
			Mmap.put("RoleDb", AD.getDashboardProfileByrole(user_id,role));
		}

		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
		Mmap.put("getSystemList", list_sys);
		// INST ACCORDING ROLE		
		String dq = "and system_id in (";
		
		for(int i =0 ; i < list_sys.size(); i++) {
			if( i == 0)
			dq += list_sys.get(i).getId();
			else
			dq += ","+list_sys.get(i).getId();
		}
		dq += ")";
		
		if(list_sys.size() == 0)
		dq = "";	
		
		String qry = "";
		List<Map<String, Object>> recordlist = AD.getDashboardProfileByrole(user_id,role);
		String record = "";
		String recordid ="";
		if(recordlist.size() > 0) {
			record = recordlist.get(0).get("pre_data").toString();
			recordid = recordlist.get(0).get("id").toString();
		
		if (record.equals("S")) {
			qry +=" and state_id = "+recordid;
		}
		}
		
		// UNIVERSITY ACCORDING ROLE
		dq = dq.replaceAll("and", "where");
		Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_MSTR  "+dq+" "+qry.replaceAll("and", "where")+" order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist2 = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		
		
		Mmap.put("getUniversityList", clist2);
		// Institute ACCORDING ROLE
		if (record.equals("U")) {
			qry +=" and university_id = "+recordid;
		}
		 dq = dq.replaceAll("where", "and");
		 q = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where status='1' "+qry+" "+dq+" order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		sessionHQL.close();
		
		Mmap.put("getInstituteList", clist);

		return new ModelAndView("AdmissionDashboardTiles");
	}
//Riddhi
	@RequestMapping(value = "/AdmissionDashboard_FacultyUrl", method = RequestMethod.GET)
	public ModelAndView AdmissionDashboard_FacultyUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String role = session.getAttribute("role").toString();
		String user_id = session.getAttribute("userId_for_jnlp").toString();

		if(role.equals("NCISM")) {
			Mmap.put("R", "NCISM");

		}else if(role.equals("NCH")){
		
			Mmap.put("R", "NCH");

		}
		else {
			Mmap.put("RoleDb", AD.getDashboardProfileByrole(user_id,role));

		}

		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
		Mmap.put("getSystemList", list_sys);


		// INST ACCORDING ROLE		
		String dq = "and system_id in (";
		
		for(int i =0 ; i < list_sys.size(); i++) {
			if( i == 0)
			dq += list_sys.get(i).getId();
			else
			dq += ","+list_sys.get(i).getId();
		}
		
		dq += ")";
		
		if(list_sys.size() == 0)
		dq = "";	
		
		String qry = "";
		List<Map<String, Object>> recordlist = AD.getDashboardProfileByrole(user_id,role);
		String record = "";
		String recordid ="";
		if(recordlist.size() > 0) {
			record = recordlist.get(0).get("pre_data").toString();
			recordid = recordlist.get(0).get("id").toString();
		
		if (record.equals("S")) {
			qry +=" and state_id = "+recordid;
		}
		}
		
		// UNIVERSITY ACCORDING ROLE
		dq = dq.replaceAll("and", "where");
		Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_MSTR  "+dq+" "+qry.replaceAll("and", "where")+" order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist2 = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		
		
		Mmap.put("getUniversityList", clist2);
		// Institute ACCORDING ROLE
		if (record.equals("U")) {
			qry +=" and university_id = "+recordid;
		}
		 dq = dq.replaceAll("where", "and");
		 q = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where status='1' "+qry+" "+dq+" order by id ");

		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		sessionHQL.close();
		
		Mmap.put("getInstituteList", clist);

		return new ModelAndView("AdmissionDashboard_FacultyTiles");
	}

	@RequestMapping(value = "/getDashBoardDataMainReload", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDashBoardDataMainReload(HttpServletRequest request,
			HttpSession session, String obj) {
		String role = session.getAttribute("role").toString();
		
		
		String p_id = "";
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if(obj == null || obj.equals("")) {
			Query q = sessionHQL.createQuery(
					"select cast(r.system_id as string) from Role r \n"
					+ "where r.role=:role");
			q.setParameter("role", role);
			List<String> clist = (List<String>) q.list();

			p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
			System.err.println("+++++++"+clist.get(0));
			obj = clist.get(0);
		}
		role = session.getAttribute("roleStaff_lvl").toString();

		
		return AD.getAdmissionSeatDetails(obj,role,p_id);

	}
	//Riddhi
	@RequestMapping(value = "/getDashBoardDataMainReload_Faculty", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDashBoardDataMainReload_Faculty(HttpServletRequest request,
			HttpSession session, String obj) {
		String role = session.getAttribute("role").toString();
		
		
		String p_id = "";
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
//		if(obj == null || obj.equals("")) {
//			Query q = sessionHQL.createQuery(
//					"select cast(r.system_id as string) from Role r \n"
//					+ "where r.role=:role");
//			q.setParameter("role", role);
//			List<String> clist = (List<String>) q.list();
//
//			p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//			System.err.println("+++++++"+clist.get(0));
//			obj = clist.get(0);
//		}
		role = session.getAttribute("roleStaff_lvl").toString();

		
		return AD.getAdmissionSeatDetails_Faculty(role);

	}
	
	@RequestMapping(value = "/getDashBoardDataReload", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDashBoardDataReload(HttpServletRequest request,
			HttpSession session, String data, String obj) {
		String role = session.getAttribute("role").toString();
//		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
		String p_id = "";
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String dq = "";
		p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//		if(obj == null || obj.equals("")) {

		if(obj != null && !obj.equals("") && !obj.equals("0"))
			dq = "and system_id in ("+obj+")";
		else {
			Query q = sessionHQL.createQuery(
					"select cast(r.system_id as string) from Role r \n"
					+ "where r.role=:role");
			System.err.println("------DD----"+obj);
			q.setParameter("role", role);
			List<String> clist = (List<String>) q.list();

			obj = clist.get(0);

		}
		
//		}
		
		role = session.getAttribute("roleStaff_lvl").toString();
		if(obj.equals(""))
			dq = "";
		List<Map<String, Object>> ms = null;
		if (data.split(",")[0].equals("S")) {
			ms = AD.AdmissionDashboardTableList(role,dq,data.split(",")[1],"S",p_id);
		}
		else if (data.split(",")[0].equals("U")) {
			ms = AD.AdmissionDashboardTableList(role,dq,data.split(",")[1],"U",p_id);
		}
		else if (data.split(",")[0].equals("I")) {
			ms = AD.AdmissionDashboardTableList(role,dq,data.split(",")[1],"I",p_id);

		}

		return ms;

	}

	@RequestMapping(value = "/getDashBoardDataReload2", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDashBoardDataReload2(HttpServletRequest request,
			HttpSession session,String data, String obj) {
		String role = session.getAttribute("role").toString();
//		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);

//		String dq = "and system_id in ("+obj+")";		
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		System.err.println("------DD----"+obj);
		String p_id = "";
		String dq = "";
		p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//		if(obj == null || obj.equals("")) {

		if(obj != null && !obj.equals("") && !obj.equals("0"))
			dq = "and system_id in ("+obj+")";
		else {
			Query q = sessionHQL.createQuery(
					"select cast(r.system_id as string) from Role r \n"
					+ "where r.role=:role");
			q.setParameter("role", role);
			List<String> clist = (List<String>) q.list();

			obj = clist.get(0);

		}
		
//		}
		
		if(obj.equals(""))
			dq = "";
		List<Map<String, Object>> ms = null;
		if (data.split(",")[0].equals("S")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data.split(",")[1],"S",p_id);
			ms = AD.AdmissionDashboardchartDataListByState2(role,dq,obj,p_id,data.split(",")[1],"S");
		}
		else if (data.split(",")[0].equals("U")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data.split(",")[1],"U",p_id);
			ms = AD.AdmissionDashboardchartDataListByState2(role,dq,obj,p_id,data.split(",")[1],"U");
		}
		else if (data.split(",")[0].equals("I")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data.split(",")[1],"I",p_id);
			ms = AD.AdmissionDashboardchartDataListByState2(role,dq,obj,p_id,data.split(",")[1],"I");

		}
		return ms;

	}

	@RequestMapping(value = "/getDashBoardDataReload3", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDashBoardDataReload3(HttpServletRequest request,
			HttpSession session, String obj) {
		String role = session.getAttribute("role").toString();
		String p_id = "";
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		if(obj == null || obj.equals("") || obj.equals("0")) {
			Query q = sessionHQL.createQuery(
					"select cast(r.system_id as string) from Role r \n"
					+ "where r.role=:role");
			q.setParameter("role", role);
			List<String> clist = (List<String>) q.list();
			p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
			obj = clist.get(0);

		}
		return AD.AdmissionDashboardchartDataListByState3(role,obj,p_id.replaceAll("university_id", "i.university_id"));

	}
	// ----------------------------------KARAN-----------------------------------------

	@RequestMapping(value = "admin/AdmissionDashboardUrl2", method = RequestMethod.GET)
	public ModelAndView e_formAdmissionDashboardUrl2(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("getStateName", common.getMedStateName(sessionFactory));
//		Mmap.put("getMedStateCounts", AD.AdmissionDashboardchartDataListByState3());
		return new ModelAndView("AdmissionDashboardTiles2");

	}

	@RequestMapping(value = "admin/getListOfSeatsAccordingToRole", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getListOfSeatsAccordingToRole(HttpSession session,String data, String type) {
		String role = session.getAttribute("role").toString();
		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);

		String dq = "and system_id in (";
		
		for(int i =0 ; i < list_sys.size(); i++) {
			if( i == 0)
			dq += list_sys.get(i).getId();
			else
			dq += ","+list_sys.get(i).getId();
		}
		
		dq += ")";
		
		if(list_sys.size() == 0)
			dq = "";
		
		List<Map<String, Object>> ms = null;
//		if (type.equals("S")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data,"S");
//		}
//		else if (type.equals("U")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data,"U");
//		}
//		else if (type.equals("I")) {
//			ms = AD.e_formAdmissionDashboardTableList(role,dq,data,"I");
//
//		}
		
		return ms;

	}


	@RequestMapping(value = "admin/callDefaultSelectDashboard", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> callDefaultSelectDashboard(HttpSession session) {
		String role = session.getAttribute("role").toString();
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
//		String user_id = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery(
//				"select cast(r.system_id as string) from Role r \n"
//				+ "where r.role=:role");
//		q.setParameter("role", role);
//		@SuppressWarnings("unchecked")
//		List<String> clist = (List<String>) q.list();
		
//		if(obj == null || obj.equals("")) {
//			p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//			obj = clist.get(0);
//
//		}
		String p_id = "";
		// INST ACCORDING ROLE		
//		if(clist.size() > 0 ) {
			
		
			
//			dq = "and system_id in ("+clist.get(0);
		
//		for(int i =0 ; i < list_sys.size(); i++) {
//			if( i == 0)
//			dq += list_sys.get(i).getId();
//			else
//			dq += ","+list_sys.get(i).getId();
//		}
			p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//		dq += ")";
		String user_id = session.getAttribute("userId_for_jnlp").toString();
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		return 	AD.callDefaultSelectDashboard(p_id,role,user_id,roleStaff_lvl);

//		}
//		else {
//			
//		return null;
////		for(int i =0 ; i < clist.size(); i++) {
////			if( i == 0)
////			dq += clist.get(i);
////			else
////			dq += ","+clist.get(i);
////		}
////		
////		dq += ")";
////		if(list_sys.size() == 0 && clist.size() == 0)
////			dq = "";
//		}
//		return null;
		
		
		 

	}

	
	
	@RequestMapping(value = "/getUniverCityListBystate", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniverCityListBystate(String state_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from EDU_LMS_UNIVERSITY_MSTR where state_id=:state_id order by id ")
				.setParameter("state_id", Integer.parseInt(state_id));

		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}

	@RequestMapping(value = "/getInstituteListByuniversity", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteListByuniversity(String university_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION where university_id=:university_id order by id ")
				.setParameter("university_id", Integer.parseInt(university_id));

		@SuppressWarnings("unchecked")
		List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q.list();
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	/////////////////////////parth changes /////////
	
//	@RequestMapping(value = "/getUniversityFromState", method = RequestMethod.POST)
//	public @ResponseBody List<Map<String, Object>> getUniversityFromState(HttpServletRequest request,
//			HttpSession session, String StateId) {
//		String role = session.getAttribute("role").toString();
//		String p_id = "";
//		
//		return AD.e_formgetAdmissionSeatDetails(obj,role,p_id);
//
//	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Seat_Detail_Report_EXCEL", method = RequestMethod.POST)
	public  ModelAndView Seat_Detail_Report_EXCEL(HttpServletRequest request,
			HttpSession session, String data1, String obj1) {
//		String role = session.getAttribute("role").toString();
		String data  = data1;
				String obj = obj1;
////		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
//		String p_id = "";
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery("select cast(sm.id as string) from EDU_LMS_SYSTEM_MSTR sm ,Role r \n"
//				+ "where r.staff_lvl=sm.created_role  and sm.status='1' and  r.role=:role order by sm.system_name ");
//		System.err.println("------DD----" + obj);
//		q.setParameter("role", role);
	//
//		String dq = "";
//		List<String> clist = (List<String>) q.list();
//		p_id = AD.getNonSelectedList(role, session.getAttribute("userId_for_jnlp").toString());
	//
//		if (obj != null && !obj.equals(""))
//			dq = "and system_id in (" + obj + ")";
//		else
//			obj = clist.get(0);
		
		//////////
		String role = session.getAttribute("role").toString();
//		List<EDU_LMS_SYSTEM_MSTR> list_sys = common.getSystemList(sessionFactory,role);
		String p_id = "";
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String dq = "";
		p_id = AD.getNonSelectedList(role,session.getAttribute("userId_for_jnlp").toString());
//		if(obj == null || obj.equals("")) {

		if(obj != null && !obj.equals("") && !obj.equals("0"))
			dq = "and system_id in ("+obj+")";
		else {
			Query q = sessionHQL.createQuery(
					"select cast(r.system_id as string) from Role r \n"
					+ "where r.role=:role");
			System.err.println("------DD----"+obj);
			q.setParameter("role", role);
			List<String> clist = (List<String>) q.list();

			obj = clist.get(0);

		}
		
//		}
		
		role = session.getAttribute("roleStaff_lvl").toString();


		if (obj.equals(""))
			dq = "";
		List<Map<String, Object>> ms = null;
		if (data.split(",")[0].equals("S")) {
			ms = AD.AdmissionDashboardTableList(role, dq, data.split(",")[1], "S", p_id);
		} else if (data.split(",")[0].equals("U")) {
			ms = AD.AdmissionDashboardTableList(role, dq, data.split(",")[1], "U", p_id);
		} else if (data.split(",")[0].equals("I")) {
			ms = AD.AdmissionDashboardTableList(role, dq, data.split(",")[1], "I", p_id);

		}

		System.err.println("ms - "+ms);
		
		
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		TH.add("Name");
		TH.add("Available Seats");
		TH.add("Alloted Seats");
		TH.add("Total Seats");

		String Heading = "\nCope Code";
		

		return  new ModelAndView(new SeatDetail_Excel("L", TH,ms, Heading), "userList", "");
		

	}
	
	
	
	
}
