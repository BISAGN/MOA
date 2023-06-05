package com.AyushEdu.controller.Registration.Postgraduate;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Postgraduate.Declaration_PG_Dao;
import com.AyushEdu.dao.Registration.Postgraduate.Personaldetails_PG_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Declaration_PGController {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	Declaration_PG_Dao pdd;
	
	
	@Autowired
	Personaldetails_PG_DAO da;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "Declaration_PG_Url")
	public ModelAndView Declaration_PG_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_eid", required = false) String ch_eid, HttpServletRequest request) {
		Session sessionHQL = this.sessionFactory.openSession();
		String username = principal.getName();
		Long v = 0L;
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		try {String userid = session.getAttribute("userId_for_jnlp").toString();
			
//			if(ch_eid.equals("") || ch_eid.isEmpty() || ch_eid.equals("0")) {
//			ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data_pg(Integer.parseInt(userid));
//			String abc = listp.get(0).get(0);
//			ch_eid = abc;
//			}
//			if(!ch_eid.equals("") && !ch_eid.isEmpty() && !ch_eid.equals("0")) {
//				Query qry = sessionHQL.createQuery("select count(*) from TB_PRE_EDUCATION_DETAILS where p_id=:p_id and (academic='12' or academic='13')");
//				qry.setParameter("p_id", Integer.parseInt(ch_eid));
//				 v = (Long) qry.uniqueResult();
//				}
		
		EDU_PG_REG_PERSONAL_DETAILS disDetails = pdd.getStudDetView_PG_Byid(Integer.parseInt(ch_eid));
		model.put("getgenderList", common.getgenderList(sessionFactory));
		model.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("getcategorylist", common.getcategoryList(sessionFactory));
		model.put("getreligionListdata", common.getreligionList(sessionFactory));
		model.put("getmsList", common.getmaritalstatusList(sessionFactory));
		model.put("Std_dtl_viewCMD", disDetails);
		model.put("msg", msg);
	}
	 catch (Exception e) {
		e.printStackTrace();
	}
		
//if ((ch_eid == null && ch_eid.equals("") && ch_eid.equals("0")) || (v != null && v != 0 && v != 2)) {
//			
//			if (v != 2) {
//				model.addAttribute("msg", "It is Mandatory To Fulfill the Details of 10th and 12th ");
//			}
//			
//			return new ModelAndView("redirect:Edu_Det_Url");
//		}else {
			model.addAttribute("ch_eid", ch_eid);
			return new ModelAndView("declaration_PG_Tiles");
//			}
		}
	
	@RequestMapping(value = "/getGrdu_pg_Detail_Ctrl", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getGrdu_pg_Detail_Ctrl(String  p_id) {
		return pdd.getGrdu_pg_Detail_Data(p_id);
	}
	
	@RequestMapping(value = "/Student_Personal_Pg_Details_Action")
	public ModelAndView Student_Personal_Pg_Details_Action(@ModelAttribute("eid3") String eid3,String status2 , ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,RedirectAttributes ra,
			HttpSession sessionEdit) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		try {
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update EDU_PG_REG_PERSONAL_DETAILS set status=:status where id=:id";
				 
				 @SuppressWarnings({ "rawtypes", "deprecation" })
				 int app = sessionHQL.createQuery(hqlUpdate)
				.setParameter("id", Integer.parseInt(eid3))
				.setParameter("status",1).executeUpdate();
			 
				if (app > 0) {
					EDU_PG_REG_PERSONAL_DETAILS pd = (EDU_PG_REG_PERSONAL_DETAILS) sessionHQL .get(EDU_PG_REG_PERSONAL_DETAILS.class, Integer.parseInt(eid3));
					
					String userid = String.valueOf(pd.getP_id());
					
					String ayuid_status = pdd.genrate_PG_Ayush_id_on_Submit(userid,sessionEdit);
					
					System.err.println("ayuid_status----->   "+ayuid_status);
					
					if (ayuid_status.toUpperCase().equals("DONE")) {
						ra.addAttribute("msg","Data Updated Successfully.");
						tx.commit();
					}else {
						tx.rollback();
						ra.addAttribute("msg","Data Not Updated");
						
//						ch_eid
						ra.addAttribute("ch_eid",eid3);
						return new ModelAndView("redirect:Declaration_PG_Url");
					}
				} else {
					tx.rollback();
					ra.addAttribute("msg","Data Not Updated");
					ra.addAttribute("eid3",eid3);
					return new ModelAndView("redirect:Declaration_PG_Url");
				}
				sessionHQL.close();
		} catch (Exception e) {
			e.printStackTrace();
			
			ra.addAttribute("msg","Data Not Updated");
			return new ModelAndView("redirect:Declaration_PG_Url");
		}
		
		return new ModelAndView("redirect:PG_Std_details_view_Url");
	}
	
	@RequestMapping(value = "/PG_Std_details_view_Url",method = RequestMethod.GET)
	public ModelAndView PG_Std_details_view_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_eid", required = false) String ch_eid, HttpServletRequest request) {

		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String userid = session.getAttribute("userId_for_jnlp").toString();
			ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data_pg(Integer.parseInt(userid));
			String abc = listp.get(0).get(0);
			ch_eid = abc;
			
			EDU_PG_REG_PERSONAL_DETAILS disDetails = pdd.getStudDetView_PG_Byid(Integer.parseInt(ch_eid));
			model.put("getgenderList", common.getgenderList(sessionFactory));
			model.put("getMedCountryName", common.getMedCountryName(sessionFactory));
			model.put("getMedStateName", common.getMedStateName(sessionFactory));
			model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
			model.put("getcategorylist", common.getcategoryList(sessionFactory));
			model.put("getreligionListdata", common.getreligionList(sessionFactory));
			model.put("getmsList", common.getmaritalstatusList(sessionFactory));
			model.put("Std_dtl_viewCMD", disDetails);
			model.put("msg", msg);
		
	}
	 catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("PG_Std_details_Tile");
	}
	
	
}
