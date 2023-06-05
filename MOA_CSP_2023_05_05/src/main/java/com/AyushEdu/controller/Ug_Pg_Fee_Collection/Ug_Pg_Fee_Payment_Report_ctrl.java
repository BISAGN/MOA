package com.AyushEdu.controller.Ug_Pg_Fee_Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Ug_Pg_Fee_Collection_Dao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Ug_Pg_Fee_Payment_Report_Dao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Ug_Pg_Fee_Payment_Report_ctrl {
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Ug_Pg_Fee_Payment_Report_Dao feeRDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/Search_ug_pg_fee_payment_Url", method = RequestMethod.GET)
	public ModelAndView Search_ug_pg_fee_payment_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
	//	String userid = session.getAttribute("userId_for_jnlp").toString();
		
		 try {
				//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Search_ug_pg_fee_payment_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		Mmap.put("msg", msg);
		Mmap.put("gettermList",common.gettermList(sessionFactory));
		Mmap.put("gettype_of_degree", common.gettype_of_degree(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("d_name", common.getDegreeList(sessionFactory));
		Mmap.put("DegreeCateList",common.DegreeCateList(sessionFactory)); 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		
		return new ModelAndView("ug_pg_fee_payment_report_Tiles");
	}
	

	@PostMapping("/getFilterSearch_ug_pg_fee_payment_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_ug_pg_fee_payment_data(int startPage, int pageLength,
         String Search, String orderColunm, String orderType, String name, String status1, String type_of_degree,
         String degree_name, String term_id,HttpSession session){
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		String role = session.getAttribute("role").toString();
	return feeRDao.DataTableSearch_fee_paymentDataList(startPage, pageLength, Search, orderColunm, orderType, name, status1, type_of_degree,
			degree_name, term_id,instid,role );

	}
	
	@PostMapping("/getTotalSearch_ug_pg_fee_payment_dataCount")
	public @ResponseBody long getTotalSearch_ug_pg_fee_payment_dataCount(HttpSession sessionUserId, String Search, String name, String status1,String type_of_degree, String degree_name, String term_id ,String inst_id) {
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
		String role = sessionUserId.getAttribute("role").toString();
		return feeRDao.DataTableSearch_fee_paymentDataTotalCount(Search, name, status1, type_of_degree, degree_name, term_id, instid,role);
	}
	
 /*
	@RequestMapping(value = "/degreefrom_fromybyinstlist_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> degreefrom_fromybyinstlist_ctrl(String type_of_degree) {
	 
	 ArrayList<ArrayList<String>> list = feeRDao.degreefrom_fromybyinstlist_ctrl(type_of_degree);
	System.err.println(" on list-work------------"+list);
	
		return list;
	}
	*/
	
	@RequestMapping(value = "/getug_pg_fee_payment_Report_Excel", method = RequestMethod.POST)
	public ModelAndView getug_pg_fee_payment_Report_Excel(ModelMap Mmap,HttpSession session, HttpServletRequest request,RedirectAttributes ra, String Search){
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_ug_pg_fee_payment_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String institute_id1 = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
//		System.err.println("roleid================" + role.split("_")[1].toString());

		String type_of_degree = request.getParameter("type_of_degree1");
		String degree_name = request.getParameter("degree_name1");
		String status1 = request.getParameter("status2");
		String name = request.getParameter("name1");
		String term_id = request.getParameter("term_id1");
		String studentId_hid = request.getParameter("studentId_hid1");
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String inst_id = common.getInstIdfromUserid( sessionFactory,  userId).get(0);
		
		List<ArrayList<String>> listofdata = feeRDao.getug_pg_fee_payment_Report_Excel(Search, name,status1,type_of_degree, degree_name, term_id,inst_id,role);

		int total = listofdata.size();
//		ArrayList<ArrayList<String>> listofdata = Cdao.getCollaboration_category_Report_Excel(collab_cate
//			, role, "");

		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();

		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		TH.add("Student Name");
		TH.add("Type Of Degree");
		TH.add("Degree Name");
		TH.add("Profession");
		TH.add("Fee Paid Status");
		
		if(listofdata.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Search_ug_pg_fee_payment_Url");
		}
		
		String Heading = "\nCope Code";
		
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Ug_Pg_Fee_Payment_Excel_Report("L", TH, listofdata, Heading, username), "userList",
				listexport);
	}

	@RequestMapping(value = "/ug_pg_fee_payment_Report_PDF", method = RequestMethod.POST)
	public ModelAndView ug_pg_fee_payment_Report_PDF(ModelMap Mmap,HttpSession session, HttpServletRequest request,RedirectAttributes ra, String Search){

		
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_ug_pg_fee_payment_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();

		String role = session.getAttribute("role").toString();
		String type_of_degree = request.getParameter("type_of_degree2");
		String degree_name = request.getParameter("degree_name2");
		String term_id = request.getParameter("term_id2");
		String status1 = request.getParameter("status3");
		String name = request.getParameter("name2");
		String studentId_hid = request.getParameter("studentId_hid2");
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String inst_id = common.getInstIdfromUserid( sessionFactory,  userId).get(0);
		

		

		List<ArrayList<String>> nonlecact1 = feeRDao.getug_pg_fee_payment_Report_Excel(Search, name,status1,type_of_degree, degree_name, term_id,inst_id,role);

//				ArrayList<ArrayList<String>> t1copolink2 = Edao.table1_co_po_link(course_id);
//				System.err.println("new_list2-------------"+t1copolink2);

		int total = nonlecact1.size();
		List<String> TH = new ArrayList<String>();

		TH.add("Ser No.");
		TH.add("Student Name");
		TH.add("Type Of Degree");
		TH.add("Degree Name");
		TH.add("Profession");
		TH.add("Fee Paid Status");
	    
		
		if(nonlecact1.size() == 0) {
			ra.addAttribute("msg","Data Not Available");
			return new ModelAndView("redirect:Search_ug_pg_fee_payment_Url");
		}
		
		String Heading = "\nIn Inspection";
		return new ModelAndView(new Ug_Pg_Fee_Payment_PDF_Report("L", TH, Heading, username, total,role), "userList", nonlecact1);

	}
	
 }
 
