package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_COLLEGE_FINANCIAL_DETAILS;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_LAST_YEAR_EXPENDITURE_EXPENSES;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_LAST_YEAR_PURCHASE_EXPENSES;
import com.AyushEdu.Models.Clg_Reg_Hospital_Details.CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Financial_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Financial_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Financial_Controller {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	@Autowired
	CommonController common = new CommonController();

	@Autowired
	Clg_Reg_College_Financial_Dao FDdao;
	
	@Autowired
	Clg_Reg_College_Financial_Report_Dao FDRdao;

	@Autowired
	Commondao commondao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;

	@RequestMapping(value = "admin/college_financial", method = RequestMethod.GET)
	public ModelAndView college_financial(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
//		if(ibdao.getpid_from_userid(userid).size()!=0) {
//			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//				Mmap.put("msg", "Please Save Basic details First");
//				return new ModelAndView("redirect:basics_information");
//
//			}else {
//				
//				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//			}
//		}else {
//			
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}

		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute = String.valueOf(ea.getInstitute_id());
		System.err.println("--institute_id--" + institute);
		String role = session.getAttribute("role").toString();
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from CLG_REG_COLLEGE_FINANCIAL_DETAILS where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institute))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
			
		Mmap.put("institude", institute);
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Mmap.put("dataparent_finance", FDdao.getAllfinancialdetails(Integer.parseInt(institute)));
		Mmap.put("dataparent_bank", FDdao.getAllbankdetails(Integer.parseInt(institute)));
		Mmap.put("dataparent_expenses", FDdao.getAllExpenses(Integer.parseInt(institute)));
		Mmap.put("dataparent_purchase_expenses", FDdao.getAll_purchase_Expenses(Integer.parseInt(institute)));
		Mmap.put("dataparent_misc_exp_expenses", FDdao.getAll_misc_expen_Expenses(Integer.parseInt(institute)));
		
		
		ArrayList<ArrayList<String>> alist2 = new ArrayList<ArrayList<String>>();
		String strDate = datePickerFormat.format(date); 
		
		String year = strDate.substring(6, 10);
		List a = new ArrayList();
		
			if(ibdao.getdate(Integer.parseInt(institute)).size()!=0) {
			
			Mmap.put("getdate", ibdao.getdate(Integer.parseInt(institute)));
			
		
		for (int i = 0; i < 10; i++) {
			ArrayList<String> list = new ArrayList<String>();

			System.out.println("year"+Math.subtractExact(Integer.parseInt(year), i));
			System.out.println("affilating "+Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)));
			if(Math.subtractExact(Integer.parseInt(year), i) >= Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)) ) {
			list.add(String.valueOf( Math.subtractExact(Integer.parseInt(year), i)));
			alist2.add(list);
			}
		}
			}
		Mmap.put("year",alist2);
		
		
		ArrayList<ArrayList<String>> alist3 = new ArrayList<ArrayList<String>>();
		String expyear = strDate.substring(6, 10);
		List b = new ArrayList();
		
			if(ibdao.getdate(Integer.parseInt(institute)).size()!=0) {
			
			Mmap.put("getdate", ibdao.getdate(Integer.parseInt(institute)));
			
		
		for (int i = 0; i < 10; i++) {
			ArrayList<String> list = new ArrayList<String>();

			System.out.println("expyear "+Math.subtractExact(Integer.parseInt(expyear), i));
			System.out.println("affilating "+Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)));
			if(Math.subtractExact(Integer.parseInt(expyear), i) >= Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)) ) {
			list.add(String.valueOf( Math.subtractExact(Integer.parseInt(expyear), i)));
			alist3.add(list);
			}
			

		}
			}
		Mmap.put("expyear",alist3);
		
		ArrayList<ArrayList<String>> alist4 = new ArrayList<ArrayList<String>>();
		String mis_year = strDate.substring(6, 10);
		List c = new ArrayList();
		
			if(ibdao.getdate(Integer.parseInt(institute)).size()!=0) {
			
			Mmap.put("getdate", ibdao.getdate(Integer.parseInt(institute)));
			
		
		for (int i = 0; i < 10; i++) {
			ArrayList<String> list = new ArrayList<String>();

			System.out.println("mis_year "+Math.subtractExact(Integer.parseInt(mis_year), i));
			System.out.println("affilating "+Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)));
			if(Math.subtractExact(Integer.parseInt(mis_year), i) >= Integer.parseInt(ibdao.getdate(Integer.parseInt(institute)).get(0).get(0)) ) {
			list.add(String.valueOf( Math.subtractExact(Integer.parseInt(mis_year), i)));
			alist4.add(list);
			}
			

		}
		}
		Mmap.put("mis_year",alist4);
		
			
			
			
		return new ModelAndView("college_financial");
	}

	// SAVE Financial Capability DETAILS
	@PostMapping(value = "/College_Financial_Details_Action")
	public @ResponseBody String College_Financial_Details_Action(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,
			@RequestParam(value = "college_acct_statement1", required = false) MultipartFile college_acct_statement,
			MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		 String msg = "";
		CLG_REG_COLLEGE_FINANCIAL_DETAILS pers_p = new CLG_REG_COLLEGE_FINANCIAL_DETAILS();
		
		String college_acct_statement1 = "college_acct_statement";
		String fix_deposite = request.getParameter("fix_deposite");
		String current_acct = request.getParameter("current_acct");
		String saving_acct = request.getParameter("saving_acct");
		String project_cost = request.getParameter("project_cost");
		String capital_cost_land = request.getParameter("capital_cost_land");
		String building_cost = request.getParameter("building_cost");
		String plants_machinery = request.getParameter("plants_machinery");
		String equipments = request.getParameter("equipments");
		String furniture_fixer = request.getParameter("furniture_fixer");
		String preliminary_operative_cost = request.getParameter("preliminary_operative_cost");
		String others = request.getParameter("others");
		String contribution_applicants = request.getParameter("contribution_applicants");
		String grants = request.getParameter("grants");
		String donation = request.getParameter("donation");
		String equity = request.getParameter("equity");
		String term_loan = request.getParameter("term_loan");
		String other_source = request.getParameter("other_source");
		String fee_structure = request.getParameter("fee_structure");
		String hospital_charges = request.getParameter("hospital_charges");
		String annual_revenue = request.getParameter("annual_revenue");
		String operating_expenses = request.getParameter("operating_expenses");
		String depreciation = request.getParameter("depreciation");
		String income_statement = request.getParameter("income_statement");
		String cash_flow_statement = request.getParameter("cash_flow_statement");
		String balance_sheet = request.getParameter("balance_sheet");
		
		String hid_financial_council = request.getParameter("hid_financial_council");
		
//			String institute_id = FDdao.getInstitute_id(Integer.parseInt(userid)).get(0).get(0);
		if (!college_acct_statement.isEmpty()) {
			college_acct_statement1 = upload_imagemethod(request, college_acct_statement, session,
					college_acct_statement1);
		} else {
			college_acct_statement1 = request.getParameter("hid_college_acct_statement1");
		}
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute_id = String.valueOf(ea.getInstitute_id());
		ea.setInstitute_id(Integer.parseInt(institute_id));
		
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (fix_deposite == null || fix_deposite.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Fix Deposits in Financial Details Tab.");
			return "Please Enter Fix Deposits in Financial Details Tab.";
		}
		if (validation.isOnlyNumerLib(fix_deposite) == false) {
			ra.addAttribute("msg","Fix Deposits in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Fix Deposits in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(fix_deposite) == false) {
			ra.addAttribute("msg", "Fix Deposits in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Fix Deposits in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (current_acct == null || current_acct.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Current Account in Financial Details Tab.");
			return "Please Enter Current Account in Financial Details Tab. ";
		}
		if (validation.isOnlyNumerLib(current_acct) == false) {
			ra.addAttribute("msg","Current Account in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Current Account in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(current_acct) == false) {
			ra.addAttribute("msg", "Current Account in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Current Account in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (saving_acct == null || saving_acct.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Saving Account in Financial Details Tab.");
			return "Please Enter Saving Account in Financial Details Tab.";
		}
		if (validation.isOnlyNumerLib(saving_acct) == false) {
			ra.addAttribute("msg","Saving Account in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Saving Account in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(saving_acct) == false) {
			ra.addAttribute("msg", "Saving Account in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Saving Account in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (project_cost == null || project_cost.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Project Cost Proposed Budget for College and Hospital in Financial Details Tab.");
			return "Please Enter Project Cost Proposed Budget for College and Hospital in Financial Details Tab. ";
		}
		if (validation.isOnlyNumerLib(project_cost) == false) {
			ra.addAttribute("msg","Project Cost Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return  "Please Enter Project Cost Proposed Budget for College and Hospital in Financial Details Tab. ";
		}
		if (validation.maxlengthcheck15(project_cost) == false) {
			ra.addAttribute("msg", "Project Cost Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Project Cost Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (capital_cost_land == null || capital_cost_land.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(capital_cost_land) == false) {
			ra.addAttribute("msg","Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(capital_cost_land) == false) {
			ra.addAttribute("msg", "Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Capital Cost Of Land Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (building_cost == null || building_cost.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Building Cost Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Building Cost Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(building_cost) == false) {
			ra.addAttribute("msg","Building Cost Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Building Cost Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(building_cost) == false) {
			ra.addAttribute("msg", "Building Cost Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Building Cost Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (plants_machinery == null || plants_machinery.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(plants_machinery) == false) {
			ra.addAttribute("msg","Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.maxlengthcheck15(plants_machinery) == false) {
			ra.addAttribute("msg", "Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Plants & Machinery Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (equipments == null || equipments.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Equipments Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Equipments Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(equipments) == false) {
			ra.addAttribute("msg","Equipments Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Equipments Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(equipments) == false) {
			ra.addAttribute("msg", "Equipments Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Equipments Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (furniture_fixer == null || furniture_fixer.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(furniture_fixer) == false) {
			ra.addAttribute("msg","Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(furniture_fixer) == false) {
			ra.addAttribute("msg", "Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Furniture & Fixture Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (preliminary_operative_cost == null || preliminary_operative_cost.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Preliminary & Pre Operative Cost Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Preliminary & Pre Operative Cost Proposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(preliminary_operative_cost) == false) {
			ra.addAttribute("msg","Preliminary & Pre Operative Cost Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Preliminary & Pre Operative CostProposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(preliminary_operative_cost) == false) {
			ra.addAttribute("msg", "Preliminary & Pre Operative Cost Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Preliminary & Pre Operative Cost Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (others == null || others.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Other Information Proposed Budget for College and Hospital in Financial Details Tab");
			return "Please Enter Other DetailsProposed Budget for College and Hospital in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(others) == false) {
			ra.addAttribute("msg","Others Information Proposed Budget for College and Hospital in Financial Details Tab" +validation.isOnlyNumerMSGLib);
			return "Others Information Proposed Budget for College and Hospital in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(others) == false) {
			ra.addAttribute("msg", "Others Information Proposed Budget for College and Hospital in Financial Details Tab" + validation.MaxlengthcheckMSG15);
			return "Others Information Proposed Budget for College and Hospital in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		
		if (contribution_applicants == null || contribution_applicants.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Contribution of Applicant Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Contribution of Applicant Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(contribution_applicants) == false) {
			ra.addAttribute("msg","Contribution of Applicant Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Contribution of Applicant Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(contribution_applicants) == false) {
			ra.addAttribute("msg", "Contribution of Applicant Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Contribution of Applicant Means of Financing the Project in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (grants == null || grants.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Grants Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Grants Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(grants) == false) {
			ra.addAttribute("msg","Grants Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Grants  Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(grants) == false) {
			ra.addAttribute("msg", "Grants Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Grants Means of Financing the Project in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (donation == null || donation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Donation Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Donation Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(donation) == false) {
			ra.addAttribute("msg","Donation Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Donation  Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(donation) == false) {
			ra.addAttribute("msg", "Donation Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Donation  Means of Financing the Project in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (equity == null || equity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Equity Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Equity Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(equity) == false) {
			ra.addAttribute("msg","Equity Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Equity  Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(equity) == false) {
			ra.addAttribute("msg", "Equity Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Equity Means of Financing the Project in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (term_loan == null || term_loan.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Term Loan Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Term Loan Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(term_loan) == false) {
			ra.addAttribute("msg","Term Loan Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Term Loan Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(term_loan) == false) {
			ra.addAttribute("msg", "Term Loan Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Term Loan Means of Financing the Project in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (other_source == null || other_source.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Other Sources in Means of Financing the Project in Financial Details Tab ");
			return "Please Enter Other Sources in Means of Financing the Project in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(other_source) == false) {
			ra.addAttribute("msg","Other Sources in Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Other Sources in Means of Financing the Project in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(other_source) == false) {
			ra.addAttribute("msg", "Other Sources Means of Financing the Project in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Other Sources Means of Financing the Project in Financial Details Tab  " +validation.MaxlengthcheckMSG15;
		}
		if (fee_structure == null || fee_structure.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Fee Structure Revenue Generated in Financial Details Tab ");
			return "Please Enter Fee Structure Revenue Generated in Financial Details Tab  ";
		}
		if (validation.maxlengthcheck15(fee_structure) == false) {
			ra.addAttribute("msg", " Fee Structure Revenue Generated in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Fee Structure Revenue Generated in Financial Details Tab  " +validation.MaxlengthcheckMSG15;
		}
		if (validation.isOnlyNumerLib(fee_structure) == false) {
			ra.addAttribute("msg","Fee Structure Revenue Generated in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Fee Structure Revenue Generated in Financial Details Tab  " +validation.isOnlyNumerMSGLib;
		}
		
		if (hospital_charges == null || hospital_charges.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Hospital user Charges Revenue Generated in Financial Details Tab ");
			return "Please Enter Hospital user Charges Revenue Generated in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(hospital_charges) == false) {
			ra.addAttribute("msg","Hospital user Charges Revenue Generated in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Hospital user Charges Revenue Generated in Financial Details Tab  " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(hospital_charges) == false) {
			ra.addAttribute("msg", "Hospital user Charges Revenue Generated in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Hospital user Charges Revenue Generated in Financial Details Tab  " +validation.MaxlengthcheckMSG15;
		}
		
		if (annual_revenue == null || annual_revenue.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab ");
			return "Please Enter Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab  ";
		}
		if (validation.isOnlyNumerLib(annual_revenue) == false) {
			ra.addAttribute("msg","Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab  " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(annual_revenue) == false) {
			ra.addAttribute("msg", "Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Estimated Annual Revenue from Other Sources Revenue Generated in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (operating_expenses == null || operating_expenses.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Operating Expenses Expenditure Incurred in Financial Details Tab ");
			return "Please Enter Operating Expenses Expenditure Incurred in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(operating_expenses) == false) {
			ra.addAttribute("msg","Operating Expenses" +validation.isOnlyNumerMSGLib);
			return "Operating Expenses " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(operating_expenses) == false) {
			ra.addAttribute("msg", "Operating Expenses Expenditure Incurred in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Operating Expenses  Expenditure Incurred in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (depreciation == null || depreciation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Depreciation Expenditure Incurred in Financial Details Tab ");
			return "Please Enter Depreciation Expenditure Incurred in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(depreciation) == false) {
			ra.addAttribute("msg","Depreciation Expenditure Incurred in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Depreciation  Expenditure Incurred in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(depreciation) == false) {
			ra.addAttribute("msg", "Depreciation Expenditure Incurred in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Depreciation  Expenditure Incurred in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
//		if (validation.maxlengthcheck15(depreciation) == false) {
//			ra.addAttribute("msg", "Depreciation" + validation.MaxlengthcheckMSG15);
//			return "Depreciation " +validation.MaxlengthcheckMSG15;
//		}
		if (income_statement == null || income_statement.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Income Statement in Operating Result in Financial Details Tab ");
			return "Please Enter Income Statement in Operating Result in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(income_statement) == false) {
			ra.addAttribute("msg","Income Statement in Operating Result in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Income Statement in Operating Result in Financial Details Tab  " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(income_statement) == false) {
			ra.addAttribute("msg", "Income Statement in Operating Result in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Income Statement in Operating Result in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (cash_flow_statement == null || cash_flow_statement.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Cash Flow Statement in Operating Result in Financial Details Tab ");
			return "Please Enter Cash Flow Statement in Operating Result in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(cash_flow_statement) == false) {
			ra.addAttribute("msg","Cash Flow Statement in Operating Result in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Cash Flow Statement in Operating Result in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(cash_flow_statement) == false) {
			ra.addAttribute("msg", "Cash Flow Statement in Operating Result in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Cash Flow Statement in Operating Result in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (balance_sheet == null || balance_sheet.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Projected Balance Sheets in Operating Result in Financial Details Tab ");
			return "Please Enter Projected Balance Sheets in Operating Result in Financial Details Tab ";
		}
		if (validation.isOnlyNumerLib(balance_sheet) == false) {
			ra.addAttribute("msg","Projected Balance Sheets in Operating Result in Financial Details Tab " +validation.isOnlyNumerMSGLib);
			return "Projected Balance Sheets in Operating Result in Financial Details Tab " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(balance_sheet) == false) {
			ra.addAttribute("msg", "Projected Balance Sheets in Operating Result in Financial Details Tab " + validation.MaxlengthcheckMSG15);
			return "Projected Balance Sheets in Operating Result in Financial Details Tab " +validation.MaxlengthcheckMSG15;
		}
		if (college_acct_statement1.isEmpty()) {
			ra.addAttribute("msg", "Please Upload College account statement  in Operating Result in Financial Details Tab ");
			return "Please Upload College account statement in Operating Result in Financial Details Tab  ";
		}
		try {
			CLG_REG_COLLEGE_FINANCIAL_DETAILS college_financial_detail = new CLG_REG_COLLEGE_FINANCIAL_DETAILS();

			college_financial_detail.setFix_deposite(BigInteger.valueOf(Long.parseLong(fix_deposite)));
			college_financial_detail.setCurrent_acct(BigInteger.valueOf(Long.parseLong(current_acct)));
			college_financial_detail.setSaving_acct(BigInteger.valueOf(Long.parseLong(saving_acct)));
			college_financial_detail.setProject_cost(BigInteger.valueOf(Long.parseLong(project_cost)));
			college_financial_detail.setCapital_cost_land(BigInteger.valueOf(Long.parseLong(capital_cost_land)));
			college_financial_detail.setBuilding_cost(BigInteger.valueOf(Long.parseLong(building_cost)));
			college_financial_detail.setPlants_machinery(BigInteger.valueOf(Long.parseLong(plants_machinery)));
			college_financial_detail.setEquipments(BigInteger.valueOf(Long.parseLong(equipments)));
			college_financial_detail.setFurniture_fixer(BigInteger.valueOf(Long.parseLong(furniture_fixer)));
			college_financial_detail.setPreliminary_operative_cost(BigInteger.valueOf(Long.parseLong(preliminary_operative_cost)));
			college_financial_detail.setOthers(BigInteger.valueOf(Long.parseLong(others)));
			college_financial_detail.setContribution_applicants(BigInteger.valueOf(Long.parseLong(contribution_applicants)));
			college_financial_detail.setGrants(BigInteger.valueOf(Long.parseLong(grants)));
			college_financial_detail.setDonation(BigInteger.valueOf(Long.parseLong(donation)));
			college_financial_detail.setEquity(BigInteger.valueOf(Long.parseLong(equity)));
			college_financial_detail.setTerm_loan(BigInteger.valueOf(Long.parseLong(term_loan)));
			college_financial_detail.setOther_source(BigInteger.valueOf(Long.parseLong(other_source)));
			college_financial_detail.setFee_structure(BigInteger.valueOf(Long.parseLong(fee_structure)));
			college_financial_detail.setHospital_charges(BigInteger.valueOf(Long.parseLong(hospital_charges)));
			college_financial_detail.setAnnual_revenue(BigInteger.valueOf(Long.parseLong(annual_revenue)));
			college_financial_detail.setOperating_expenses(BigInteger.valueOf(Long.parseLong(operating_expenses)));
			college_financial_detail.setDepreciation(BigInteger.valueOf(Long.parseLong(depreciation)));
			college_financial_detail.setIncome_statement(income_statement);
			college_financial_detail.setCash_flow_statement(cash_flow_statement);
			college_financial_detail.setBalance_sheet(balance_sheet);
			college_financial_detail.setCollege_acct_statement(college_acct_statement1);
			college_financial_detail.setS_id(Integer.parseInt(s_id));
			college_financial_detail.setInstitute_id(Integer.parseInt(institute_id));
			college_financial_detail.setCreated_by(Integer.parseInt(userid));
			college_financial_detail.setCreated_date(date);

			if (Integer.parseInt(hid_financial_council) == 0) {
				int hid_financial_council1 = (Integer) sessionHQL.save(college_financial_detail);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Saved Successfully";
				tx.commit();

				return String.valueOf(hid_financial_council1);
			} else {

				CLG_REG_COLLEGE_FINANCIAL_DETAILS college_financial_detail_f = (CLG_REG_COLLEGE_FINANCIAL_DETAILS) sessionHQL
						.get(CLG_REG_COLLEGE_FINANCIAL_DETAILS.class, Integer.parseInt(hid_financial_council));

				college_financial_detail_f.setFix_deposite(BigInteger.valueOf(Long.parseLong(fix_deposite)));
				college_financial_detail_f.setCurrent_acct(BigInteger.valueOf(Long.parseLong(current_acct)));
				college_financial_detail_f.setSaving_acct(BigInteger.valueOf(Long.parseLong(saving_acct)));
				college_financial_detail_f.setProject_cost(BigInteger.valueOf(Long.parseLong(project_cost)));
				college_financial_detail_f.setCapital_cost_land(BigInteger.valueOf(Long.parseLong(capital_cost_land)));
				college_financial_detail_f.setBuilding_cost(BigInteger.valueOf(Long.parseLong(building_cost)));
				college_financial_detail_f.setPlants_machinery(BigInteger.valueOf(Long.parseLong(plants_machinery)));
				college_financial_detail_f.setEquipments(BigInteger.valueOf(Long.parseLong(equipments)));
				college_financial_detail_f.setFurniture_fixer(BigInteger.valueOf(Long.parseLong(furniture_fixer)));
				college_financial_detail_f.setPreliminary_operative_cost(BigInteger.valueOf(Long.parseLong(preliminary_operative_cost)));
				college_financial_detail_f.setOthers(BigInteger.valueOf(Long.parseLong(others)));
				college_financial_detail_f.setContribution_applicants(BigInteger.valueOf(Long.parseLong(contribution_applicants)));
				college_financial_detail_f.setGrants(BigInteger.valueOf(Long.parseLong(grants)));
				college_financial_detail_f.setDonation(BigInteger.valueOf(Long.parseLong(donation)));
				college_financial_detail_f.setEquity(BigInteger.valueOf(Long.parseLong(equity)));
				college_financial_detail_f.setTerm_loan(BigInteger.valueOf(Long.parseLong(term_loan)));
				college_financial_detail_f.setOther_source(BigInteger.valueOf(Long.parseLong(other_source)));
				college_financial_detail_f.setFee_structure(BigInteger.valueOf(Long.parseLong(fee_structure)));
				college_financial_detail_f.setHospital_charges(BigInteger.valueOf(Long.parseLong(hospital_charges)));
				college_financial_detail_f.setAnnual_revenue(BigInteger.valueOf(Long.parseLong(annual_revenue)));
				college_financial_detail_f.setOperating_expenses(BigInteger.valueOf(Long.parseLong(operating_expenses)));
				college_financial_detail_f.setDepreciation(BigInteger.valueOf(Long.parseLong(depreciation)));
				college_financial_detail_f.setIncome_statement(income_statement);
				college_financial_detail_f.setCash_flow_statement(cash_flow_statement);
				college_financial_detail_f.setBalance_sheet(balance_sheet);
				college_financial_detail_f.setCollege_acct_statement(college_acct_statement1);
				college_financial_detail_f.setS_id(Integer.parseInt(s_id));
				college_financial_detail_f.setModified_by(Integer.parseInt(userid));
				college_financial_detail_f.setModified_date(date);
				college_financial_detail_f.setId(Integer.parseInt(hid_financial_council));
				sessionHQL.update(college_financial_detail_f);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Updated Successfully";
				tx.commit();

			}

		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}

	// SAVE College_Bank Account DETAILS
	@PostMapping(value = "/College_BankAccount_Details_Action")
	public @ResponseBody String College_BankAccount_Details_Action(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,MultipartHttpServletRequest mul, RedirectAttributes ra)
			throws ParseException, IOException {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		
		
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		
		CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS pers_p = new CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS();
		
		
		String indno = request.getParameter("indno_library");
		String account_no = request.getParameter("account_no"+indno);
		String bank_name = request.getParameter("bank_name"+indno);
		String ifsc_code = request.getParameter("ifsc_code"+indno);
		String saving_current_acct = request.getParameter("saving_current_acct"+indno);
		String branch_addr = request.getParameter("branch_addr"+indno);
		String p_hid_bankacct = FDdao.getp_idfromuser_id(userid).get(0).get(0);
		String hid_bankdtl = request.getParameter("hid_bankdtl"+indno);
		String hid_bank_audit = request.getParameter("hid_bank_audit"+indno);
		MultipartFile file = mul.getFile("bank_audit"+indno);
		String upload_attachment = upload_imagemethod(request,file, session, hid_bankdtl);
//			String institute_id = FDdao.getInstitute_id(Integer.parseInt(userid)).get(0).get(0);
        String msg = "";
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute_id = String.valueOf(ea.getInstitute_id());
		ea.setInstitute_id(Integer.parseInt(institute_id));
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

//		
		if (account_no == null || account_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Account Number in Bank Accounts Tab");
			return "Please Enter Account Number in Bank Accounts Tab";
		}
		if (validation.maxlengthcheckAccountNo(account_no) == false) {
			ra.addAttribute("msg", "Account Number in Bank Accounts Tab" + validation.MaxlengthcheckMSGAccountNo);
			return "Account Number in Bank Accounts Tab " +validation.MaxlengthcheckMSGAccountNo;
		}
		if (validation.isOnlyNumerLib(account_no) == false) {
			ra.addAttribute("msg","Account Number in Bank Accounts Tab" +validation.isOnlyNumerMSGLib);
			return "Account Number in Bank Accounts Tab" +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck30(account_no) == false) {
			ra.addAttribute("msg", "Account Number in Bank Accounts Tab" + validation.MaxlengthcheckMSG30);
			return "Account Number in Bank Accounts Tab " +validation.MaxlengthcheckMSG30;
		}
		if (bank_name == null || bank_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Bank Name in Bank Accounts Tab");
			return "Please Enter Bank Name in Bank Accounts Tab";
		}
		if (validation.isOnlyAlphabetDASH(bank_name) == false) {
			ra.addAttribute("msg","Bank Name in Bank Accounts Tab" +validation.isOnlyAlphabetMSGDASH);
			return "Bank Name in Bank Accounts Tab" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlengthcheck100(bank_name) == false) {
			ra.addAttribute("msg", "Bank Name in Bank Accounts Tab" + validation.MaxlengthcheckMSG100);
			return "Bank Name in Bank Accounts Tab " +validation.MaxlengthcheckMSG100;
		}
		if (ifsc_code == null || ifsc_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter IFSC code in Bank Accounts Tab");
			return "Please Enter IFSC code in Bank Accounts Tab";
		}
		if (validation.maxlengthcheckP(ifsc_code) == false) {
			ra.addAttribute("msg", " IFSC code in Bank Accounts Tab" + validation.MaxlengthcheckMSGP);
			return " IFSC code in Bank Accounts Tab " +validation.MaxlengthcheckMSGP;
		}
		if (validation.isOnlyAlphabetNumber(ifsc_code) == false) {
			ra.addAttribute("msg","IFSC code in Bank Accounts Tab" +validation.isOnlyAlphabetNumberMSG);
			return "IFSC code in Bank Accounts Tab" +validation.isOnlyAlphabetNumberMSG;
		}
		if (saving_current_acct == null || saving_current_acct.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Saving/Current in Bank Accounts Tab");
			return "Please Select Saving/Current in Bank Accounts Tab";
		}
		if (branch_addr == null || branch_addr.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Branch Address in Bank Accounts Tab");
			return "Please Enter Branch Address in Bank Accounts Tab";
		}
		if (validation.isOnlyAlphabetNumber(branch_addr) == false) {
			ra.addAttribute("msg","Branch Address in Bank Accounts Tab" +validation.isOnlyAlphabetNumberMSG);
			return "Branch Address in Bank Accounts Tab" +validation.isOnlyAlphabetNumberMSG;
		}
		
		if (upload_attachment.isEmpty()) {
			ra.addAttribute("msg", "Please Upload Bank Audit Statement of Account  in Bank Accounts Tab");
			return "Please Upload Bank Audit Statement of Account in Bank Accounts Tab";
		}
		try {
			CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS college_bank_detail = new CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS();
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS where account_no=:account_no and id !=:id")
					.setParameter("account_no", account_no)
					.setParameter("id", id).uniqueResult();
			System.err.println("C--------------"+c);
			if(c > 0) {
				return "Bank Account Number Already Exist";
			}
			
			college_bank_detail.setAccount_no(account_no);
			college_bank_detail.setBank_name(bank_name);
			college_bank_detail.setIfsc_code(ifsc_code);
			college_bank_detail.setSaving_current_acct(Integer.parseInt(saving_current_acct));
			college_bank_detail.setBranch_addr(branch_addr);
			college_bank_detail.setBank_audit(upload_attachment);
			college_bank_detail.setS_id(Integer.parseInt(s_id));
			college_bank_detail.setInstitute_id(Integer.parseInt(institute_id));
			college_bank_detail.setCreated_by(Integer.parseInt(userid));
			college_bank_detail.setCreated_date(date);
			
			if (Integer.parseInt(hid_bankdtl) == 0) {
				college_bank_detail.setP_id(Integer.parseInt(p_hid_bankacct));
				int hid_bankdtl1 = (Integer) sessionHQL.save(college_bank_detail);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Saved Successfully";
				tx.commit();
				return String.valueOf(hid_bankdtl1);
				
			} else {

				CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS college_bank_detail_b = (CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS) sessionHQL
						.get(CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS.class, Integer.parseInt(hid_bankdtl));

				college_bank_detail_b.setAccount_no(account_no);
				college_bank_detail_b.setBank_name(bank_name);
				college_bank_detail_b.setIfsc_code(ifsc_code);
				college_bank_detail_b.setSaving_current_acct(Integer.parseInt(saving_current_acct));
				college_bank_detail_b.setBranch_addr(branch_addr);
				college_bank_detail_b.setBank_audit(upload_attachment);
				college_bank_detail_b.setS_id(Integer.parseInt(s_id));
				college_bank_detail_b.setModified_by(Integer.parseInt(userid));
				college_bank_detail_b.setModified_date(date);
				college_bank_detail_b.setId(Integer.parseInt(hid_bankdtl));
				sessionHQL.update(college_bank_detail_b);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Updated Successfully";
				tx.commit();

			}

		} catch (RuntimeException e) {
			e.printStackTrace();
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
		
		return msg;
		
	}

	// ADD MORE FETCH FOR Bank Account Details
	@RequestMapping(value = "admin/getBank_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS> getBank_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String p_hid_bankacct = FDdao.getp_idfromuser_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS where p_id=:p_id ");

		q.setParameter("p_id", Integer.parseInt(p_hid_bankacct));

		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS> clist = (List<CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS>) q.list();

		tx.commit();
		sessionHQL.close();
		return clist;
	}

	// ADD MORE DELETE FOR LIBRARIAN DETAILS
	@PostMapping(value = "/delete_bank_Details")
	public @ResponseBody String delete_bank_Details(String hid_bankdtl, HttpSession session1) {

		String msg = "";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hqlUpdate = "delete from CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_bankdtl)).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				msg = "Data Deleted Successfully.";
			} else {
				msg = "Data not Deleted.";
			}
		} catch (Exception e) {
			msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
		} finally {

		}
		return msg;
	}

	// SAVE College_Last Financial Year Expenses DETAILS
	@PostMapping(value = "/College_last_financial_expenses_Action")
	public @ResponseBody String College_last_financial_expenses_Action(HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal,MultipartHttpServletRequest mul, RedirectAttributes ra)
			throws ParseException, IOException {
		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "redirect:/login";
		}
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute = String.valueOf(ea.getInstitute_id());
		System.err.println("--institute_id--" + institute);
//		String s_id = session.getAttribute("super_id").toString();
		 String msg = "";
		CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES pers_p = new CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES();

		String indno = request.getParameter("indno_auxi");
		String year = request.getParameter("year"+indno);
		String staff_salary = request.getParameter("staff_salary"+indno);
		String total_salary = request.getParameter("total_salary"+indno);
		String hid_salarystaff = request.getParameter("hid_salarystaff"+indno);
		String institute_id = FDdao.getInstitute_id(userid).get(0).get(0);
		String hid_attachment = request.getParameter("hid_attachment"+indno);
		MultipartFile file = mul.getFile("attachment"+indno);
		String a_upload_attachment = upload_imagemethod(request,file, session, hid_salarystaff);
		
		String hid_salary_statment = request.getParameter("hid_salary_statment"+indno);
		MultipartFile file1 = mul.getFile("salary_statment"+indno);
		String salary_upload_attachment = upload_imagemethod(request,file, session, hid_salarystaff);
		String p_hid_expenses = FDdao.getp_idfromuser_id(userid).get(0).get(0);
		//int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		//String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (year == null || year.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Year of Expenses on Staff in Last Financial Year Expenses");
			return "Please Select Year of Expenses on Staff in Last Financial Year Expenses";
		}
		if (staff_salary == null || staff_salary.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select type of Staff Salary of Expenses on Staff in Last Financial Year Expenses");
			return "Please Select Type of Staff Salary of Expenses on Staff in Last Financial Year Expenses";
		}
		if (total_salary == null || total_salary.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Salary of Expenses on Staff in Last Financial Year Expenses");
			return "Please Enter Total Salary of Expenses on Staff in Last Financial Year Expenses";
		}
		if (validation.isOnlyNumerLib(total_salary) == false) {
			ra.addAttribute("msg"," Total Salary of Expenses on Staff in Last Financial Year Expenses" +validation.isOnlyNumerMSGLib);
			return " Total Salary of Expenses on Staff in Last Financial Year Expenses " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(total_salary) == false) {
			ra.addAttribute("msg", "Total Salary of Expenses on Staff in Last Financial Year Expenses. " + validation.MaxlengthcheckMSG15);
			return "Total Salary of Expenses on Staff in Last Financial Year Expenses " +validation.MaxlengthcheckMSG15;
		}
		if (a_upload_attachment.isEmpty()) {
			ra.addAttribute("msg", "Please Upload Document of Expenses on Staff in Last Financial Year Expenses ");
			return "Please Upload Document of Expenses on Staff in Last Financial Year Expenses ";
		}
		if (salary_upload_attachment.isEmpty()) {
			ra.addAttribute("msg", "Please Upload Salary statement of Teaching, non-teaching and hospital staff Document of Expenses on Staff in Last Financial Year Expenses ");
			return "Please Upload Salary statement of Teaching, non-teaching and hospital staff Document of Expenses on Staff in Last Financial Year Expenses ";
		}
		try {
			CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES financial_expenses_detail = new CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES();
	
			financial_expenses_detail.setYear(year);
			financial_expenses_detail.setStaff_salary(staff_salary);
			financial_expenses_detail.setTotal_salary(BigInteger.valueOf(Long.parseLong(total_salary)));
			financial_expenses_detail.setAttachment(a_upload_attachment);
			financial_expenses_detail.setSalary_statment(salary_upload_attachment);
			financial_expenses_detail.setS_id(Integer.parseInt(s_id));
			financial_expenses_detail.setInstitute_id(Integer.parseInt(institute_id));
			financial_expenses_detail.setCreated_by(Integer.parseInt(userid));
			financial_expenses_detail.setCreated_date(date);
			
			if (Integer.parseInt(hid_salarystaff) == 0) {
				financial_expenses_detail.setP_id(Integer.parseInt(p_hid_expenses));
				int hid_salarystaff1 = (Integer) sessionHQL.save(financial_expenses_detail);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Saved Successfully";
				tx.commit();
				return String.valueOf(hid_salarystaff1);


				
			} else {
				CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES financial_expenses_detail_f = (CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES) sessionHQL
						.get(CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES.class, Integer.parseInt(hid_salarystaff));
				financial_expenses_detail_f.setYear(year);
				financial_expenses_detail_f.setStaff_salary(staff_salary);
				financial_expenses_detail_f.setTotal_salary(BigInteger.valueOf(Long.parseLong(total_salary)));
				financial_expenses_detail_f.setAttachment(a_upload_attachment);
				financial_expenses_detail_f.setSalary_statment(salary_upload_attachment);
				financial_expenses_detail_f.setModified_by(Integer.parseInt(userid));
				financial_expenses_detail_f.setS_id(Integer.parseInt(s_id));
				financial_expenses_detail_f.setModified_date(date);
				sessionHQL.update(financial_expenses_detail_f);
				sessionHQL.flush();
				sessionHQL.clear();
				msg = "Data Updated Successfully";
				tx.commit();
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return msg;
	}
	
	// ADD MORE FETCH FOR Medical Details
			@RequestMapping(value = "admin/getSalary_Details", method = RequestMethod.POST)
			public @ResponseBody List<CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES> getSalary_Details(HttpSession session) {
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String institute_id = FDdao.getInstitute_id(userid).get(0).get(0);
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL.createQuery("from CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES where institute_id=:institute_id ");

				q.setParameter("institute_id", Integer.parseInt(institute_id));

				@SuppressWarnings("unchecked")
				List<CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES> clist = (List<CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES>) q.list();

				tx.commit();
				sessionHQL.close();
				return clist;
			}

			// ADD MORE DELETE FOR Medical Details
			@PostMapping(value = "/Delete_Salary_Details")
			public @ResponseBody String Delete_Salary_Details(String hid_salarystaff, HttpSession session1) {

				String msg = "";

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				try {
					String hqlUpdate = "delete from CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES where id=:id";
					int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_salarystaff)).executeUpdate();
					tx.commit();
					session.close();
					if (app > 0) {
						msg = "Data Deleted Successfully.";
					} else {
						msg = "Data not Deleted.";
					}
				} catch (Exception e) {
					msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
				} finally {

				}
				return msg;
			}
			
			
			// SAVE College_Last Financial Year Expenses DETAILS
			@PostMapping(value = "/College_last_financial_Purchase_expenses_Action")
			public @ResponseBody String College_last_financial_Purchase_expenses_Action(HttpServletRequest request, ModelMap model,
					HttpSession session, Principal principal,MultipartHttpServletRequest mul, RedirectAttributes ra)
					throws ParseException, IOException {
				if (request.getHeader("Referer") == null) {
					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return "redirect:/login";
				}
				String role = session.getAttribute("role").toString();
				String roleid1 = session.getAttribute("roleid").toString();
				DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institute = String.valueOf(ea.getInstitute_id());
				String s_id = session.getAttribute("super_id").toString();
				CLG_REG_LAST_YEAR_PURCHASE_EXPENSES pers_p = new CLG_REG_LAST_YEAR_PURCHASE_EXPENSES();

				String indno1 = request.getParameter("indno_pur");
				String expyear = request.getParameter("expyear"+indno1);
				String purchase = request.getParameter("purchase"+indno1);
				String total_purchase = request.getParameter("total_purchase"+indno1);
				String hid_purchase = request.getParameter("hid_purchase"+indno1);
				String institute_id = FDdao.getInstitute_id(userid).get(0).get(0);
				String hid_pur_attachment = request.getParameter("hid_pur_attachment"+indno1);
				 String msg = "";
				 
				MultipartFile file = mul.getFile("purchase_attachment"+indno1);
				String pur_upload_attachment = upload_imagemethod(request,file, session, hid_purchase);
				
				Date date = new Date();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				if (expyear == null || expyear.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Expense Year in Expenses on Expenditure in Last Financial Year Expenses");
					return "Please Select Expense Year in Expenses on Expenditure in Last Financial Year Expenses";
				}
				if (purchase == null || purchase.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select type of Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses.");
					return "Please Select type of Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses";
				}
				if (total_purchase == null || total_purchase.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses");
					return "Please Enter Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses";
				}
				if (validation.isOnlyNumerLib(total_purchase) == false) {
					ra.addAttribute("msg","Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses" +validation.isOnlyNumerMSGLib);
					return " Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses" +validation.isOnlyNumerMSGLib;
				}
				if (validation.maxlengthcheck15(total_purchase) == false) {
					ra.addAttribute("msg", "Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses" + validation.MaxlengthcheckMSG15);
					return "Total Expenditure Expenses in Expenses on Expenditure in Last Financial Year Expenses " +validation.MaxlengthcheckMSG15;
				}
				if (pur_upload_attachment.isEmpty()) {
					ra.addAttribute("msg", "Please Upload Expenses Document in Expenses on Expenditure in Last Financial Year Expenses");
					return "Please Upload Expenses Document in Expenses on Expenditure in Last Financial Year Expenses";
				}
				
				
				try {
					CLG_REG_LAST_YEAR_PURCHASE_EXPENSES pur_dtls = new CLG_REG_LAST_YEAR_PURCHASE_EXPENSES();
			
					pur_dtls.setExpyear(expyear);
					pur_dtls.setPurchase(purchase);
					pur_dtls.setTotal_purchase(BigInteger.valueOf(Long.parseLong(total_purchase)));
					pur_dtls.setPurchase_attachment(pur_upload_attachment);
					pur_dtls.setS_id(Integer.parseInt(s_id));
					pur_dtls.setInstitute_id(Integer.parseInt(institute_id));
					pur_dtls.setCreated_by(Integer.parseInt(userid));
					pur_dtls.setCreated_date(date);
					
					if (Integer.parseInt(hid_purchase) == 0) {
						int hid_purchase1 = (Integer) sessionHQL.save(pur_dtls);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Saved Successfully";
						tx.commit();
						return String.valueOf(hid_purchase1);


						
					} else {
						CLG_REG_LAST_YEAR_PURCHASE_EXPENSES pur_dtls_f = (CLG_REG_LAST_YEAR_PURCHASE_EXPENSES) sessionHQL
								.get(CLG_REG_LAST_YEAR_PURCHASE_EXPENSES.class, Integer.parseInt(hid_purchase));
						pur_dtls_f.setExpyear(expyear);
						pur_dtls_f.setPurchase(purchase);
						pur_dtls_f.setTotal_purchase(BigInteger.valueOf(Long.parseLong(total_purchase)));
						pur_dtls_f.setPurchase_attachment(pur_upload_attachment);
						pur_dtls_f.setModified_by(Integer.parseInt(userid));
						pur_dtls_f.setS_id(Integer.parseInt(s_id));
						pur_dtls_f.setModified_date(date);
						sessionHQL.update(pur_dtls_f);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Updated Successfully";
						tx.commit();
					}
					
				} catch (RuntimeException e) {
					e.printStackTrace();
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
				return msg;
			}

			// ADD MORE FETCH FOR Medical Details
					@RequestMapping(value = "admin/getPurchase_Details", method = RequestMethod.POST)
					public @ResponseBody List<CLG_REG_LAST_YEAR_PURCHASE_EXPENSES> getPurchase_Details(HttpSession session) {
						String userid = session.getAttribute("userId_for_jnlp").toString();
						String institute_id = FDdao.getInstitute_id(userid).get(0).get(0);
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from CLG_REG_LAST_YEAR_PURCHASE_EXPENSES where institute_id=:institute_id ");

						q.setParameter("institute_id", Integer.parseInt(institute_id));

						@SuppressWarnings("unchecked")
						List<CLG_REG_LAST_YEAR_PURCHASE_EXPENSES> clist = (List<CLG_REG_LAST_YEAR_PURCHASE_EXPENSES>) q.list();

						tx.commit();
						sessionHQL.close();
						return clist;
					}

					// ADD MORE DELETE FOR Medical Details
					@PostMapping(value = "/Delete_Purchase_Details")
					public @ResponseBody String Delete_Purchase_Details(String hid_purchase, HttpSession session1) {

						String msg = "";

						Session session = this.sessionFactory.openSession();
						Transaction tx = session.beginTransaction();
						try {
							String hqlUpdate = "delete from CLG_REG_LAST_YEAR_PURCHASE_EXPENSES where id=:id";
							int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_purchase)).executeUpdate();
							tx.commit();
							session.close();
							if (app > 0) {
								msg = "Data Deleted Successfully.";
							} else {
								msg = "Data not Deleted.";
							}
						} catch (Exception e) {
							msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
						} finally {

						}
						return msg;
					}
					
					
							// SAVE Financial Capability DETAILS
							@PostMapping(value = "/College_Last_Financial_Doc_Details_Action")
							public @ResponseBody String College_Last_Financial_Doc_Details_Action(HttpServletRequest request, ModelMap model,
									HttpSession session, Principal principal,
									@RequestParam(value = "medicine_bill", required = false) MultipartFile med_bill,
									@RequestParam(value = "book_bill", required = false) MultipartFile book,
									@RequestParam(value = "proof_esi", required = false) MultipartFile proof,
									@RequestParam(value = "form_16", required = false) MultipartFile form,
									@RequestParam(value = "proof_pf", required = false) MultipartFile proof_p,
									MultipartHttpServletRequest mul,
									RedirectAttributes ra) throws ParseException, IOException {

								if (request.getHeader("Referer") == null) {
									session.invalidate();
									model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
									return "redirect:/login";
								}
								String role = session.getAttribute("role").toString();
								String roleid1 = session.getAttribute("roleid").toString();
								DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
								String userid = session.getAttribute("userId_for_jnlp").toString();
								String s_id = session.getAttribute("super_id").toString();
								
								 String msg = "";
								 
								CLG_REG_COLLEGE_FINANCIAL_DETAILS pers_p = new CLG_REG_COLLEGE_FINANCIAL_DETAILS();
								
								
								String medicine_bill = "medicine_bill";
								String book_bill = "book_bill";
								String proof_esi = "proof_esi";
								String form_16 = "form_16";
								String proof_pf = "proof_pf";
								String hid_doc_file = request.getParameter("hid_doc_file");
//									String institute_id = FDdao.getInstitute_id(Integer.parseInt(userid)).get(0).get(0);
								if (!med_bill.isEmpty()) {
									medicine_bill = upload_imagemethod(request, med_bill, session,
											medicine_bill);
								} else {
									medicine_bill = request.getParameter("hid_medicine_bill");
								}
								
								
								if (!book.isEmpty()) {
									book_bill = upload_imagemethod(request, book, session,
											book_bill);
								} else {
									book_bill = request.getParameter("hid_book_bill");
								}
								
								if (!proof.isEmpty()) {
									proof_esi = upload_imagemethod(request, proof, session,
											proof_esi);
								} else {
									proof_esi = request.getParameter("hid_proof_esi");
								}
								
								
								if (!form.isEmpty()) {
									form_16 = upload_imagemethod(request, form, session,
											form_16);
								} else {
									form_16 = request.getParameter("hid_form_16");
								}
								
								
								if (!proof_p.isEmpty()) {
									proof_pf = upload_imagemethod(request, proof_p, session,
											proof_pf);
								} else {
									proof_pf = request.getParameter("hid_proof_pf");
								}
								int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
								Date date = new Date();
								UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
								String institute_id = String.valueOf(ea.getInstitute_id());
								ea.setInstitute_id(Integer.parseInt(institute_id));
								String username = principal.getName();
								Session sessionHQL = this.sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();

								
								try {
									CLG_REG_COLLEGE_FINANCIAL_DETAILS doc_file = new CLG_REG_COLLEGE_FINANCIAL_DETAILS();

									
									doc_file.setMedicine_bill(medicine_bill);
									doc_file.setBook_bill(book_bill);
									doc_file.setProof_esi(proof_esi);
									doc_file.setForm_16(form_16);
									doc_file.setProof_pf(proof_pf);
									doc_file.setS_id(Integer.parseInt(s_id));
									doc_file.setInstitute_id(Integer.parseInt(institute_id));
									doc_file.setCreated_by(Integer.parseInt(userid));
									doc_file.setCreated_date(date);

									if (Integer.parseInt(hid_doc_file) == 0) {
										int hid_doc_file1 = (Integer) sessionHQL.save(doc_file);
										sessionHQL.flush();
										sessionHQL.clear();
										msg = "Data Saved Successfully";
										tx.commit();

										return String.valueOf(hid_doc_file1);
									} else {

										CLG_REG_COLLEGE_FINANCIAL_DETAILS doc_file_f = (CLG_REG_COLLEGE_FINANCIAL_DETAILS) sessionHQL
												.get(CLG_REG_COLLEGE_FINANCIAL_DETAILS.class, Integer.parseInt(hid_doc_file));

										
										doc_file_f.setMedicine_bill(medicine_bill);
										doc_file_f.setBook_bill(book_bill);
										doc_file_f.setProof_esi(proof_esi);
										doc_file_f.setForm_16(form_16);
										doc_file_f.setProof_pf(proof_pf);
										doc_file_f.setS_id(Integer.parseInt(s_id));
										doc_file_f.setModified_by(Integer.parseInt(userid));
										doc_file_f.setModified_date(date);
										doc_file_f.setId(Integer.parseInt(hid_doc_file));
										sessionHQL.update(doc_file_f);
										sessionHQL.flush();
										sessionHQL.clear();
										msg = "Data Updated Successfully";
										tx.commit();

									}

								} catch (RuntimeException e) {
									e.printStackTrace();
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
								return msg;
							}
							
	public String upload_imagemethod(HttpServletRequest request, MultipartFile mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul;

		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ id + "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {

		}
		
		return fname;

	}
	
		
}
