package com.AyushEdu.controller.helpdeskINQ;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.helpdeskINQ.HD_Inq_In_Helpdesk_P;
import com.AyushEdu.RBAC.ScreenMstrController;
import com.AyushEdu.dao.helpdeskINQ.HD_DB_HelpDesKDao;
import com.AyushEdu.dao.helpdeskINQ.HD_DB_HelpDesK_ReportDao;
import com.AyushEdu.dao.helpdeskINQ.HD_Inquiry_ReportDao;
@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Db_Helpdesk_Controller {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	
	@Autowired
	CommonController common;
	
	@Autowired
	ScreenMstrController SMC;
	
	@Autowired
	HD_Inquiry_ReportDao DBH;
	
	@Autowired
	HD_DB_HelpDesK_ReportDao HDRD;
	
	@Autowired
	HD_DB_HelpDesKDao HDK;
	
	@RequestMapping(value = "admin/db_helpdesk", method = RequestMethod.GET)
	public ModelAndView db_helpdesk(ModelMap Mmap, HttpSession session,RedirectAttributes ra,
			@RequestParam(value = "msg", required = false)  String msg,@RequestParam(value = "inqno", required = false) String inqno, HttpServletRequest request) {
	Mmap.put("getinnerInq_CatList", common.getinnerInq_CatList());
	Mmap.put("getModuleNameList", SMC.getModuleNameList());
	ra.addAttribute("inqno", inqno);
//	Mmap.put("getSubModuleNameList", SMC.getSubModuleNameList());
		return new ModelAndView("db_helpdesk");
	}
	@RequestMapping(value = "admin/getsubmoduleform", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getsubmoduleform(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String module) {
	       return HDRD.getsubmodule(module);
	}
	
	@RequestMapping(value = "admin/getscreennameform", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getscreennameform(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, String module,String sub_module) {
	       return HDRD.getscreenname( module,sub_module);
	}
  
	
	@PostMapping(value = "admin/Inq_in_db_helpdesk_Action")
	public @ResponseBody List<String> Inq_in_db_helpdesk_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,String module,String sub_module,String screen_name,String inq_cat,String description,String mobile_no,String email) throws ParseException {
		
		HD_Inq_In_Helpdesk_P ECD =new HD_Inq_In_Helpdesk_P();
		
	    String module1 = request.getParameter("module");
		String sub_module1 = request.getParameter("sub_module");
		String screen_name1 = request.getParameter("screen_name");
		String inq_cat1 = request.getParameter("inq_cat");
		String description1 = request.getParameter("description");
		String mobile_no1 = request.getParameter("mobile_no");
		String email1 = request.getParameter("email");
		

//		 String type_of_issue1 = request.getParameter("type_of_issue");
//		String first_name1 = request.getParameter("first_name");
//		String last_name1 = request.getParameter("last_name");
//		String ph_no1= request.getParameter("ph_no");
//		String email1= request.getParameter("email");
//		String your_feedback1= request.getParameter("your_feedback");
		int id = ECD.getId() > 0 ? ECD.getId() : 0;
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String userId = session.getAttribute("userId").toString();
		List<String> msg =  new ArrayList<String>();
		try {
//			EDU_CONTACT_DETAILS pers_detail = (EDU_CONTACT_DETAILS) sessionHQL
//					.get(EDU_CONTACT_DETAILS.class, Integer.parseInt(pers_hid));
			Long c = (Long) sessionHQL.createQuery(
			"select count(id) from  HD_Inq_In_Helpdesk_P where module=:module and sub_module=:sub_module and screen_name=:screen_name "
			+ " and inq_cat =:inq_cat and description=:description and mobile_no=:mobile_no and email=:email and id !=:id")
					  .setParameter("module",Integer.parseInt(module))
					  .setParameter("sub_module",Integer.parseInt(sub_module))
					  .setParameter("screen_name",Integer.parseInt(screen_name))
					  .setParameter("inq_cat",Integer.parseInt(inq_cat)) 
					  .setParameter("description",description) 
					  .setParameter("mobile_no",mobile_no) 
					  .setParameter("email",email) 
			          .setParameter("id", id).uniqueResult();
			
			int state_id = (int) sessionHQL.createQuery(
					"select state_id from UserLogin where userId=:userId ")	 
					          .setParameter("userId",Integer.parseInt (userId)).uniqueResult();
			
			ECD.setModule(Integer.parseInt(module));
			ECD.setSub_module(Integer.parseInt(sub_module));
			ECD.setScreen_name(Integer.parseInt(screen_name));
			ECD.setInq_cat(Integer.parseInt(inq_cat));
			ECD.setDescription(description);
			ECD.setMobile_no(mobile_no);
			ECD.setEmail(email);
			ECD.setState(state_id);
			
//			    ECD.setType_of_issue(Integer.parseInt(type_of_issue));
//			    ECD.setFirst_name(first_name);
//		    	ECD.setLast_name(last_name);
//			    ECD.setPh_no(ph_no);
//				ECD.setEmail(email);
//		    	ECD.setYour_feedback(your_feedback);
//		    	ECD.setCreated_date(date);
		    	
		    	if (id == 0) {
					
		    		ECD.setCreated_by(email);
		    		ECD.setCreated_dt(date);
////					td.setCreated_role(role);
////					td.setCreated_date(date);
		    		ECD.setInq_no(getMax_Inn_INQ_NO());
		    		msg.add(ECD.getInq_no());

		    	if (c == 0) {
					sessionHQL.save(ECD);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
					msg.add("Data Saved Successfully");

		    	}else {
//		    		ECD.setCreated_by(name);
//		    		ECD.setCreated_date(date);
		    		sessionHQL.save(ECD);
					sessionHQL.flush();
					sessionHQL.clear();
					msg.add("Data Saved Successfully");
					ra.addAttribute("msg", "Data Saved Successfully.");
		    	}
		    	}
			    tx.commit();

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
		return  msg;
}
	
	@Autowired
	private DataSource dataSource;	
	public String getMax_Inn_INQ_NO() {

		Connection conn = null;
		String max_inq_no1 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			
			query = "     select 'IQ' || to_char(CURRENT_TIMESTAMP,'yy')|| lpad((case when (select max(Substring(inq_no,5,9))\n"
					+ "					   from hd_inq_in_helpdesk_p)='' or (select max(Substring(inq_no,5,9))\n"
					+ "					   from hd_inq_in_helpdesk_p) is null  then '0' else (select max(Substring(inq_no,5,9))\n"
					+ "					   from hd_inq_in_helpdesk_p) end::int+1)::text, 7, '0') as max_inq_no1 from hd_inq_in_helpdesk_p limit 1 ";
			
			stmt = conn.prepareStatement(query);
			
//			stmt.setInt(1, Integer.parseInt(userid));
			System.err.println("-----max_inq_no1--->   "+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				max_inq_no1 = rs.getString("max_inq_no1");
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return max_inq_no1;
	}

	@PostMapping("/getFilterdb_helpdesklist")
	public @ResponseBody List<Map<String, Object>> getFilterdb_helpdesklist(int startPage, int pageLength,HttpSession session,
			String Search, String orderColunm, String orderType, String inq_no, String per_state, String des,String inq_cat,String module,String sub_module,String screen_name,String status) {

		return HDK.getFilterdb_helpdesklist(startPage, pageLength, Search, orderColunm, orderType, inq_no, inq_cat, per_state,des,module,sub_module,screen_name,  status, session);

	}
	
	@PostMapping("/getTotaldb_helpdeskCount")
	public @ResponseBody long getTotaldb_helpdeskCount(HttpSession sessionUserId, String Search, String inq_no,String inq_cat, String per_state, String des,String module,String sub_module,String screen_name, String status) {
		return HDK.getTotaldb_helpdeskCount(Search, inq_no, inq_cat, per_state, des, module,sub_module,screen_name,status, sessionUserId);

	}
}



