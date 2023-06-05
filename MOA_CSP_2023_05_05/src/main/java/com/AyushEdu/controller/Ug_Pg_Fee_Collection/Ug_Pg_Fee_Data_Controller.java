package com.AyushEdu.controller.Ug_Pg_Fee_Collection;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_INSTITUTE_FEES_DATA;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Fees_Data_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Ug_Pg_Fee_Data_Controller {
	@Autowired
	CommonController common;

	@Autowired
	Commondao commondao;

	@Autowired
	ValidationController validation;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	Fees_Data_Dao fdDao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	private Date date;

	@RequestMapping(value = "/Fees_Data_Url", method = RequestMethod.GET)
	public ModelAndView Fees_Data_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String role = session.getAttribute("role").toString();
		String userId = session.getAttribute("userId").toString();
//		String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);

		try {
			if (request.getHeader("Referer") == null) {
				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Fees_Data_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);
			Mmap.put("gettermList", common.gettermListFee(sessionFactory));
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("d_name", common.getDegreeList(sessionFactory));
			Mmap.put("list", fdDao.DataTable_CMEAttend_DataList(userId, "0", "0", "0", "0", role));
			Mmap.put("list_count", fdDao.DataTable_CMEAttend_DataList_count(userId, "0", "0", "0", "0", role));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Fees_Data_Tiles");
	}

	@PostMapping(value = "/Fees_Data_Action1")
	public ModelAndView Fees_Data_Action1(@Validated @ModelAttribute("ug_pg_FeesData_CMD") EDU_INSTITUTE_FEES_DATA td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {

		String role = session.getAttribute("role").toString();
		String userId = session.getAttribute("userId").toString();
		int id = td.getId() > 0 ? td.getId() : 0;
		String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Fees_Data_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
//		String system = fdDao.getSystemListFromInstituteExam(institute_id1, userId, role).get(0).get(0);
		String system = request.getParameter("system_id");
		String degree = request.getParameter("degree_name");
		String prof_id = request.getParameter("prof_id");
		String nor = request.getParameter("no_of_record_hid");
		int parent_id = 0;
		int child_id = 0;
//		String status = request.getParameter("status");
		if (system.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (degree.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		if (prof_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Fees_Data_Url");
		}
		try {
			if (id == 0) {
//					Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  EDU_INSTITUTE_FEES_DATA where total_fees=:total_fees and paid_fees=:paid_fees  ")
//					//.setParameter("id", td.getId())
//					.setParameter("total_fees",Integer.parseInt(total_fees))
//					.setParameter("paid_fees",Integer.parseInt(paid_fees))
//				    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
//					.uniqueResult();
//		
////			int idd =0;	
//			if (c == 0) {

				for (int i = 1; i <= Integer.parseInt(nor); i++) {

					String stu_id = request.getParameter("stu_hid" + i);
					String part = request.getParameter("no_of_part_hid" + i);
					String total_fees = request.getParameter("total_fees" + i);
					String paid_fees = request.getParameter("paid_fees" + i);
					System.err.println("paid_fees========" + paid_fees);
					String stu_name = request.getParameter("stu_name" + i);
//					double fees = Math.round(Double.parseDouble(total_fees)/Double.parseDouble(part));

					if (paid_fees == null || paid_fees.trim().equals("") || paid_fees.trim().equals("0")) {
						ra.addAttribute("msg", "Please Enter Paid Fees Amount.");
						return new ModelAndView("redirect:Fees_Data_Url");
					}
					if (validation.isOnlyNumer(paid_fees) == true) {
						ra.addAttribute("msg", "Paid Fees Amount " + validation.isOnlyNumerMSG);
						return new ModelAndView("redirect:Fees_Data_Url");
					}
//						Long c = (Long) sessionHQL.createQuery(
//								"select count(id) from  EDU_INSTITUTE_FEES_DATA where paid_fees=:paid_fees  ")
//								//.setParameter("id", td.getId())
//								.setParameter("paid_fees",Integer.parseInt(paid_fees))
//							    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
//								.uniqueResult();
//						
//						if(c == 0) {
					td.setStud_id(Integer.parseInt(stu_id));
					td.setInstitute_id(Integer.parseInt(institute_id1));
					td.setDegree_id(Integer.parseInt(degree));
					td.setProf_id(Integer.parseInt(prof_id));
					td.setSystem_id(Integer.parseInt(system));
					td.setTotal_fees(Double.parseDouble(total_fees));
					td.setPaid_fees(Integer.parseInt(paid_fees));
					td.setCreated_by(username);
					td.setCreated_date(date);
					parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
//						}else {
//							ra.addAttribute("msg", " Paid Fees Amount already exists. ");
//							return new ModelAndView("redirect:Fees_Data_Url");
//						}
					String hql = "";
					String msg = "";
//					String studentid = request.getParameter("studentId_hid");

					if (role.contains("NCISM")) {
						hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
								+ "	 where id=:id ";
						Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1)
								.setParameter("modified_by", userId).setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(stu_id));
						msg = query.executeUpdate() > 0 ? "1" : "0";
					}
					if (role.contains("NCH")) {
						hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
								+ "	 where id=:id ";
						Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1)
								.setParameter("modified_by", userId).setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(stu_id));
						msg = query.executeUpdate() > 0 ? "1" : "0";
					}
				}
				if (parent_id > 0) {
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
				}
			}
		} catch (RuntimeException e) {
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Fees_Data_Url");
	}

//		@PostMapping(value = "/Fees_Data_Action1")
//		public ModelAndView Fees_Data_Action1(@Validated @ModelAttribute("ug_pg_FeesData_CMD") EDU_INSTITUTE_FEES_DATA td, BindingResult result,
//				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//				RedirectAttributes ra,  String username )  {
//			
//			 String userId = session.getAttribute("userId").toString();
//			String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
//			
//			String system = request.getParameter("system_id");
//			String degree = request.getParameter("degree_name");
//			String prof_id= request.getParameter("prof_id");
//			String stu_id = request.getParameter("stu_hid");
//			String total_fees = request.getParameter("total_fees");
//			String paid_fees = request.getParameter("paid_fees");
//	//	
//
//
//	
//			
//			
//			//int id = td.getId() > 0 ? td.getId() : 0;
//
//			
//
//			Session sessionHQL = this.sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//
//			//try {
//				Long c = (Long) sessionHQL.createQuery(
//						"select count(id) from  EDU_INSTITUTE_FEES_DATA where total_fees=:total_fees and paid_fees=:paid_fees  ")
//						//.setParameter("id", td.getId())
//						.setParameter("total_fees",Integer.parseInt(total_fees))
//						.setParameter("paid_fees",Integer.parseInt(paid_fees))
//					    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
//						.uniqueResult();
//			
////				int idd =0;	
//				if (c == 0) {
//				
//				
//					td.setStud_id(Integer.parseInt(stu_id));
//					td.setInstitute_id(Integer.parseInt(institute_id1));
//					td.setDegree_id(Integer.parseInt(degree));
//					td.setProf_id(Integer.parseInt(prof_id));
//					td.setSystem_id(Integer.parseInt(system));
//					td.setTotal_fees(Double.parseDouble(total_fees));
//					td.setPaid_fees(Integer.parseInt(paid_fees));
//					td.setCreated_by(username);
//					td.setCreated_date(date);
//					
//					td.setModified_by(username);
//					td.setModified_date(date);
////					if (c == 0) {
//					sessionHQL.save(td);
//						sessionHQL.flush();
//						sessionHQL.clear();
//						ra.addAttribute("msg", "Data Saved Successfully.");
////					} 
////					
//				}else {
//				ra.addAttribute("msg", "Data already Exist.");
//		}
//
//				tx.commit();
//				//For Core Directory
////				DM_dirdao.Insert_Attachment_Mstr_Data(idd);
//				/*
//				 * } catch (RuntimeException e) { try {
//				 * 
//				 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
//				 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
//				 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
//				 */
//
//				sessionHQL.close();
//			return new ModelAndView("redirect:Attachment_MasterUrl");
//		}

	@RequestMapping(value = "/getSystemListFromInstituteExam", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getSystemListFromInstituteExam(HttpSession session,
			String institute_id) {
		String userId = session.getAttribute("userId_for_jnlp").toString();
		String institute_id1 = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		ArrayList<ArrayList<String>> list = null;
		try {
			list = fdDao.getSystemListFromInstituteExam(institute_id1, userId, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getDegreeListbysystem11", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem1(String system_name) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = common.getDegreeListbysystem(sessionFactory, system_name);
		return list;
	}

	@RequestMapping(value = "/Search_fees_data_Url", method = RequestMethod.POST)
	public ModelAndView Search_fees_data_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "system_id1", required = false) String system_id,
			@RequestParam(value = "degree_name1", required = false) String degree_name,
			@RequestParam(value = "prof_id1", required = false) String prof_id,
			@RequestParam(value = "url1", required = false) String url1) {

		String userId = session.getAttribute("userId").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
		System.err.println("institute_id========---------------"+institute_id);
		String role = session.getAttribute("role").toString();

		try {
			if (request.getHeader("Referer") == null) {
				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Fees_Data_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);
			Mmap.put("gettermList", common.gettermListFee(sessionFactory));
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("d_name", common.getDegreeList(sessionFactory));
			Mmap.put("system_id1", system_id);
			Mmap.put("degree_name1", degree_name);
			Mmap.put("prof_id1", prof_id);
			Mmap.put("institute_id", institute_id);
			Mmap.put("url1", url1);
			Mmap.put("list",
					fdDao.DataTable_CMEAttend_DataList(userId, system_id, degree_name, prof_id, institute_id, role));
			Mmap.put("list_count", fdDao.DataTable_CMEAttend_DataList_count(userId, system_id, degree_name, prof_id,
					institute_id, role));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Fees_Data_Tiles");
	}
}
