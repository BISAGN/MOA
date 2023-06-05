package com.AyushEdu.controller.Registration;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Std_Pers_Details_Dao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.twilio.twiml.messaging.Redirect;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Std_Pers_Details_Controller {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	Std_Pers_Details_Dao pdd;
	
	@Autowired
	personaldetailsDAO da;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "Std_pers_details_Url")
	public ModelAndView Std_pers_details_Url(ModelMap model, HttpSession session, Principal principal,
			@ModelAttribute("doc_eid") String doc_eid,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_eid", required = false) String ch_eid, HttpServletRequest request) 
			 {
		Long v = 0L;
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		try {
			
//			System.err.println("date------->   "+ new Date());
			
//			new SimpleDateFormat("YYYYMMDD").format(new Date());
			
//			System.err.println("date----SimpleDateFormat--->   "+ new SimpleDateFormat("YYYYMMdd").format(new Date()));
			
		Session sessionHQL = this.sessionFactory.openSession();
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		TB_PERSONAL_DETAILS disDetails = pdd.getStudDetViewByid(Integer.parseInt(ch_eid));
		
		if(doc_eid.equals("") || doc_eid.isEmpty() || doc_eid.equals("0")) {
			ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data(Integer.parseInt(userid));
			String abc = listp.get(0).get(0);
			doc_eid = abc;
			}
			
			if(!doc_eid.equals("") && !doc_eid.isEmpty() && !doc_eid.equals("0")) {
				Query qry = sessionHQL.createQuery("select count(*) from TB_PRE_EDUCATION_DETAILS where p_id=:p_id and (academic='12' or academic='13')");
				qry.setParameter("p_id", Integer.parseInt(doc_eid));
				 v = (Long) qry.uniqueResult();
				}
	
			
//			if (signature.equals("")|| signature.isEmpty() || signature.equals("0")) {
//				ArrayList<ArrayList<String>> listsign=
//			}
		
//		System.err.println("/*/*/*/*/*/*/*/*-"+ch_eid);
//		String username = principal.getName();
//		model.addAttribute("userid", da.getUsername(username));
//
//		//model.put("getclgNameDataList", comMstr.getclgNameDataList(sessionFactory));
//		model.put("getclgNameDataList", comMstr.getapp_instituteNameList(sessionFactory));
//		
//		
//		model.addAttribute("msg", msg);
//
//		if (ch_eid == "0" || ch_eid.equals("")) {
//			return new ModelAndView("redirect:Personal_Details_Url");
//		} else {
//			model.addAttribute("ch_eid", ch_eid);
//		TB_PERSONAL_DETAILS disDetails = getViewByid(Integer.parseInt(viewid));
//
//		TB_EDU_REGULATION viewid = (TB_EDU_REGULATION) sessionHQL.get(TB_EDU_REGULATION.class, id);
		
		//Mmap.put("Pract_viewCMD", disDetails);
		
		model.put("getgenderList", common.getgenderList(sessionFactory));
		model.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("getcategorylist", common.getcategoryList(sessionFactory));
		model.put("getreligionListdata", common.getreligionList(sessionFactory));
		model.put("getmsList", common.getmaritalstatusList(sessionFactory));
		model.put("Std_dtl_viewCMD", disDetails);
		model.put("getPrefixList", common.getPrefixList(sessionFactory));
		
		model.put("ch_eid", ch_eid);
		
	}
	 catch (Exception e) {
		e.printStackTrace();
	}
		
		
		if ((doc_eid == null && doc_eid.equals("") && doc_eid.equals("0")) || (v != null && v != 0 && v != 2)) {
			
			if (v != 2) {
				model.addAttribute("msg", "It is Mandatory To Fulfill the Details of 10th and 12th ");
			}
			
			return new ModelAndView("redirect:Edu_Det_Url");
		}
else if ((doc_eid == null && doc_eid.equals("") && doc_eid.equals("0")) || (v == 0)) {
			
			if (v != 2) {
				model.addAttribute("msg", "It is Mandatory To Fulfill the Details of 10th and 12th ");
			}
			return new ModelAndView("redirect:Edu_Det_Url");
		
			}
		
		else {
			model.addAttribute("doc_eid", doc_eid);
			model.put("msg", msg);
			return new ModelAndView("Std_pers_details_Tile");
		}
		
//		return new ModelAndView("Std_pers_details_Tile");
//		}
	}
	
	@RequestMapping(value = "/getEdu_Detail_Ctrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getEdu_Detail_Ctrl(String  p_id) {
		return pdd.getEdu_Detail_data(p_id);
	}
	
//	@RequestMapping(value = "/get_uploded_imgthumb_ctrl", method = RequestMethod.POST)
// 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgthumb_ctrl(String p_id) {
//	 
////    	ArrayList<ArrayList<String>> data = docdao.get_uploded_imgthumb_data(p_id);
//    	
//    	return data;
// 	}
	
	@RequestMapping(value = "/Student_Personal_Details_Action")
	public ModelAndView Student_Personal_Details_Action(@ModelAttribute("eid3") String eid3,String status2 , ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,RedirectAttributes ra,
			HttpSession sessionEdit) {

		System.err.println("--------------   "+eid3+"    =================   "+status2);
		
		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String system_id =	request.getParameter("system_id2");
			String verify_status = pdd.update_verify_at_final_submit_nch(eid3,system_id,sessionEdit);
			
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update TB_PERSONAL_DETAILS set status=:status where id=:id";
				 
				 @SuppressWarnings({ "rawtypes", "deprecation" })
				 int app = sessionHQL.createQuery(hqlUpdate)
				.setParameter("id", Integer.parseInt(eid3))
				.setParameter("status", Integer.parseInt(status2)).executeUpdate();
				 
				tx.commit();
				sessionHQL.close();
	
				if (app > 0) {
					ra.addAttribute("msg","Form Submitted Successfully");
					
				} else {
					ra.addAttribute("msg","Form Not Submitted");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			ra.addAttribute("msg","Form Not Submitted");
		}
		ra.addAttribute("ch_eid",eid3);
		return new ModelAndView("redirect:NCH_Std_details_view_Url");
	}
	
	@GetMapping(value = "NCH_Std_details_view_Url")
	public ModelAndView NCH_Std_details_view_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_eid", required = false) String ch_eid, HttpServletRequest request) {

		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			String userid = session.getAttribute("userId_for_jnlp").toString();
			if (ch_eid == "" && ch_eid == "null" && ch_eid.equals("")) {
			ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data(Integer.parseInt(userid));
			String abc = listp.get(0).get(0);
			ch_eid = abc;
			}
			
		TB_PERSONAL_DETAILS disDetails = pdd.getStudDetViewByid(Integer.parseInt(ch_eid));
		model.put("getgenderList", common.getgenderList(sessionFactory));
		model.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("getcategorylist", common.getcategoryList(sessionFactory));
		model.put("getreligionListdata", common.getreligionList(sessionFactory));
		model.put("getmsList", common.getmaritalstatusList(sessionFactory));
		model.put("Std_dtl_viewCMD", disDetails);
		model.put("getPrefixList", common.getPrefixList(sessionFactory));
		model.put("msg", msg);
		model.put("ch_eid", ch_eid);
	}
	 catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("NCH_Std_details_Tile");
	}
	
	 @RequestMapping(value = "/get_ayush_id_bypid_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_bypid_ctrl(String pid) {
	    	ArrayList<ArrayList<String>> data = pdd.get_ayush_idbypid_data(pid);
	    	return data;
	 	}
	
	 @RequestMapping(value = "/getuploaded_doc_NCH_Ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_doc_NCH_Ctrl(String p_id) {
	    	ArrayList<ArrayList<String>> data = pdd.getuploaded_doc_NCH_data(p_id);
	    	return data;
	 	}
		
	 @RequestMapping(value = "/getuploaded_Court_Order_NCH_Ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_Court_Order_NCH_Ctrl(String p_id) {
	    	ArrayList<ArrayList<String>> data = pdd.getuploaded_Court_Order_NCH_data(p_id);
	    	return data;
	 	}
	 
	
}
