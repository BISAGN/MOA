package com.AyushEdu.controller.Part_one;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_COLLEGE_FINANCIAL_DETAILS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Financial_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Financial_Report_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	@Autowired
	CommonController common = new CommonController();

	@Autowired
	Clg_Reg_College_Financial_Report_Dao FDRdao;

	@Autowired
	Commondao commondao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	// ----------------search-------------------------

		@GetMapping(value = "/Search_College_Financial_url")
		public ModelAndView Search_College_Financial_url(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			String userid = session.getAttribute("userId_for_jnlp").toString();
			String role = session.getAttribute("role").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
			return new ModelAndView("Search_College_FinancialTiles");
		}

		@PostMapping("/getFilterSearch_College_Financial_data")
		public @ResponseBody List<Map<String, Object>> getFilterSearch_College_Financial_data(HttpSession sessionUserId,
				int startPage, int pageLength, String Search, String orderColunm, String orderType, String fix_deposite,
				String current_acct, String saving_acct, String project_cost, String income_statement) {

			String role = sessionUserId.getAttribute("role").toString();
			String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			return FDRdao.DataTableSearch_College_FinancialDataList(startPage, pageLength, Search, orderColunm, orderType,
					fix_deposite, current_acct, saving_acct, project_cost, income_statement, role, userid, institute_id);

		}

		@PostMapping("/getTotalSearch_College_Financial_dataCount")
		public @ResponseBody long getTotalSearch_College_Financial_dataCount(HttpSession sessionUserId, String Search,
				String fix_deposite, String current_acct, String saving_acct, String project_cost,
				String income_statement) {
			String role = sessionUserId.getAttribute("role").toString();
			String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			return FDRdao.DataTableSearch_College_FinancialDataTotalCount(Search, fix_deposite, current_acct, saving_acct,
					project_cost, income_statement, role, userid, institute_id);
		}
		
		//////////////////View_url

		@RequestMapping(value = "/View_Search_College_FinancialUrl", method = RequestMethod.POST)
		public ModelAndView View_Search_College_FinancialUrl(@ModelAttribute("college_financial_id") String id, ModelMap Mmap,
		HttpSession session, @RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
				Integer.parseInt(id), role, userid,"clg_reg_inst_info_institution_basic_details","inst_id");
				
				System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
				String p_id = getPidfromInstidReport.get(0).get("id").toString();
				Mmap.put("getPidfromInstidReport", p_id); 
//
//		System.err.println("22/2/23----------------id------>" + id);

		CLG_REG_COLLEGE_FINANCIAL_DETAILS viewid = (CLG_REG_COLLEGE_FINANCIAL_DETAILS) sessionHQL
			.get(CLG_REG_COLLEGE_FINANCIAL_DETAILS.class, Integer.parseInt(p_id));

		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		//Mmap.put("getMedInstituteName", common.getMedInstituteName(sessionFactory));
		Mmap.put("Search_College_Financial_CMD", viewid);

		List<Map<String, Object>> getAllfinancialdetailsReport = FDRdao.getAllfinancialdetailsReport(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getAllbankdetailsReport = FDRdao.getAllbankdetailsReport(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getAllExpensesReport = FDRdao.getAllExpensesReport(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getLastYearPurchaseExp = FDRdao.getLastYearPurchaseExp(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getLastYearExpenditureExp = FDRdao.getLastYearExpenditureExp(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		//List<Map<String, Object>> getInstname = CIDao.getInstname(Integer.parseInt(institute_id));
		Mmap.put("getAllfinancialdetailsReport", getAllfinancialdetailsReport);
		Mmap.put("getAllbankdetailsReport", getAllbankdetailsReport);
		Mmap.put("getAllExpensesReport", getAllExpensesReport);
		Mmap.put("getLastYearPurchaseExp", getLastYearPurchaseExp);
		Mmap.put("getLastYearExpenditureExp", getLastYearExpenditureExp);
		
		Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
		Mmap.put("inst_id", id);


		Mmap.addAttribute("msg", msg);
		return new ModelAndView("college_financial_view");
		}

}
