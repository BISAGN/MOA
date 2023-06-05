package com.AyushEdu.controller.Registration.Postgraduate;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_PRE_EDUCATION_DETAILS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_GRADU_DTLS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_GRADU_SEMWISE_DTLS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.Postgraduate.GraduationDetails_PG_Dao;
import com.AyushEdu.dao.Registration.Postgraduate.Personaldetails_PG_DAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class GraduationDetails_PGController {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Personaldetails_PG_DAO da;
	
	@Autowired
	private GraduationDetails_PG_Dao gddao;
	
	@Autowired
	private Commondao comda;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@Autowired
	private CommonController com;
	
	@GetMapping(value = "/Graduation_Det_PG_Url")
	public ModelAndView Graduation_Det_PG_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false)  String msg,@ModelAttribute("eid") String eid, HttpServletRequest request) {
		
		String username = principal.getName();
		int user = da.getUsername_pg(username);
		
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
			
			String roleid = session.getAttribute("roleid").toString();
			String staff_lvl = comda.getStaffLvlfromRoleid(roleid);
			
			ArrayList<ArrayList<String>> data = da.get_ayush_id_data_pg(String.valueOf(user),staff_lvl);
			String sys_id = data.get(0).get(5);
			String deg_id = data.get(0).get(6);
		
		ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data_pg(user);
		
		String p_id = listp.get(0).get(0);
		
		ArrayList<ArrayList<String>> list1 = gddao.getaddmoredata1(p_id);
		ArrayList<ArrayList<String>> list2 = gddao.getaddmoredata2(p_id);
//		List<EDU_LMS_UNIVERSITY_MSTR> unilist= com.getuniversitylistbyStafflevel(sessionFactory,staff_lvl);
		
		model.put("msg", msg);
		model.put("edit_gradu_det", list1);
		model.put("edit_gradu_semwise_det", list2);
		model.put("getuniversitylist",  com.getuniversitylistbyStafflevel(sessionFactory,staff_lvl));
		model.put("getname_of_examList", com.getnameofexam_pg_graduformList(sessionFactory,sys_id,deg_id));
		model.put("getSubjectList", comda.getSubjectForpg_graduform(sys_id,"59"));
	
//		model.addAttribute("msg", msg);
		
	}
	 catch (Exception e) {
		e.printStackTrace();
	}		
		
		if (eid.equals("0")) {
			return new ModelAndView("redirect:Personal_Details_PG_Url");
		}else {
			model.addAttribute("tbpdid", eid);
			return new ModelAndView("Graduation_Det_PG_Tiles","Graduation_Det_PG_CMD", new EDU_PG_REG_GRADU_DTLS());
		}
	}
	
	@PostMapping(value = "/Graduation_Det_PG_Action")
	public ModelAndView Graduation_Det_PG_Action(
			@Validated @ModelAttribute("Graduation_Det_PG_CMD") EDU_PG_REG_GRADU_DTLS td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		
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
			
		String username = principal.getName();
		Date date = new Date();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		int errorsubject=0;
		int errornoe=0;
		
		try {
		
		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
		String p_id = request.getParameter("p_id");
		
			int temp = 0;
			if ( old_hidden_att <= new_count_hidden) {
				for (int j = 1; j <= old_hidden_att; j++) {
					
					String editid = request.getParameter("editid"+j);
					
					EDU_PG_REG_GRADU_DTLS add = (EDU_PG_REG_GRADU_DTLS) sessionHQL
							.get(EDU_PG_REG_GRADU_DTLS.class, Integer.parseInt(editid));
					
					String name_of_exam = request.getParameter("name_of_exam"+j);
					String month_year = request.getParameter("month_year"+j);
					String university = request.getParameter("university"+j);
					String no_of_attempts = request.getParameter("no_of_attempts"+j);
					String universityother = request.getParameter("universityother"+j);

					
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from EDU_PG_REG_GRADU_DTLS where name_of_exam=:name_of_exam and p_id=:p_id and id !=:id")
							.setParameter("name_of_exam", name_of_exam)
							.setParameter("p_id", Integer.parseInt(p_id))
							.setParameter("id", Integer.parseInt(editid))
							.uniqueResult();
					
					
				
					add.setMonth_year(month_year);
					add.setUniversity(university);
					add.setNo_of_attempts(no_of_attempts);
					add.setUniversityother(universityother);
					add.setCreated_by(username);
					add.setCreated_date(date);
					add.setId(Integer.parseInt(editid));
					
					
					
					if (c == 0) {
						add.setName_of_exam(name_of_exam);
						sessionHQL.update(add);
						sessionHQL.flush();
						sessionHQL.clear();
					}else {
						errornoe+=1;
					}
					
					
				

				}
			}
			
			if (old_hidden_att < new_count_hidden) {
				EDU_PG_REG_GRADU_DTLS xray = new EDU_PG_REG_GRADU_DTLS();
					for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
						String name_of_exam = request.getParameter("name_of_exam"+j);
						String month_year = request.getParameter("month_year"+j);
						String university = request.getParameter("university"+j);
						String no_of_attempts = request.getParameter("no_of_attempts"+j);
						String universityother = request.getParameter("universityother"+j);
							
						System.err.println("universityother-------"+universityother);
						
						
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from EDU_PG_REG_GRADU_DTLS where name_of_exam=:name_of_exam and p_id=:p_id")
								.setParameter("name_of_exam", name_of_exam)
								.setParameter("p_id", Integer.parseInt(p_id))
								.uniqueResult();
						
						
						xray.setName_of_exam(name_of_exam);
						xray.setMonth_year(month_year);
						xray.setUniversity(university);
						xray.setNo_of_attempts(no_of_attempts);
						xray.setUniversityother(universityother);
						xray.setCreated_by(username);
						xray.setCreated_date(date);
						xray.setP_id(Integer.parseInt(p_id));
						
						if (c == 0) {
							sessionHQL.save(xray);
							sessionHQL.flush();
							sessionHQL.clear();
						}else {
							errornoe+=1;
						}

					}
			}

			if ( old_hidden_att > new_count_hidden) {
				
				for (int j = new_count_hidden + 1; j <= old_hidden_att; j++) {
					String editid = request.getParameter("editid"+j);
					
					EDU_PG_REG_GRADU_DTLS del = (EDU_PG_REG_GRADU_DTLS) sessionHQL
							.get(EDU_PG_REG_GRADU_DTLS.class, Integer.parseInt(editid));
					sessionHQL.delete(del);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}

			/////////////////////////////////////
			
//			if (editid.equals("0")) {
//				td.setName_of_exam(name_of_exam);
//				td.setMonth_year(month_year);
//				td.setUniversity(university);
//				td.setNo_of_attempts(no_of_attempts);
//				td.setCreated_by(username);
//				td.setCreated_date(date);
//				int sid = (int) sessionHQL.save(td);
//				sessionHQL.flush();
//				sessionHQL.clear();
//					} else {
//						EDU_PG_REG_GRADU_DTLS pda = (EDU_PG_REG_GRADU_DTLS)sessionHQL.get(EDU_PG_REG_GRADU_DTLS.class, Integer.parseInt(editid));
//						pda.setName_of_exam(name_of_exam);
//						pda.setMonth_year(month_year);
//						pda.setUniversity(university);
//						pda.setNo_of_attempts(no_of_attempts);
//						pda.setModified_by(username);
//						pda.setModified_date(date);
//						sessionHQL.save(pda);
//						sessionHQL.flush();
//						sessionHQL.clear();
//					}
//		}
			///////////////  indepth data save
//			subject1
			int new_count_indepth_hidden = Integer.parseInt(request.getParameter("new_count_indepth_hidden"));
			int old_count_indepth_hidden = Integer.parseInt(request.getParameter("old_count_indepth_hidden"));
			
			
			if (old_count_indepth_hidden <= new_count_indepth_hidden) {
				
				
				for (int j = 1; j <= old_count_indepth_hidden; j++) {
					
					String semwiseeditid = request.getParameter("semwiseeditid"+j);
					
					EDU_PG_REG_GRADU_SEMWISE_DTLS semwiseadd = (EDU_PG_REG_GRADU_SEMWISE_DTLS) sessionHQL
							.get(EDU_PG_REG_GRADU_SEMWISE_DTLS.class, Integer.parseInt(semwiseeditid));
					
					String subject = request.getParameter("subject"+j);
					String semwise_no_of_atmpts = request.getParameter("semwise_no_of_atmpts"+j);
					String maxmark_y1_ = request.getParameter("maxmark_y1_"+j);
					String obtainmark_y1_ = request.getParameter("obtainmark_y1_"+j);
					String maxmark_y2_ = request.getParameter("maxmark_y2_"+j);
					String obtainmark_y2_ = request.getParameter("obtainmark_y2_"+j);
					String maxmark_y3_ = request.getParameter("maxmark_y3_"+j);
					String obtainmark_y3_ = request.getParameter("obtainmark_y3_"+j);
					String maxmark_y4_ = request.getParameter("maxmark_y4_"+j);
					String obtainmark_y4_ = request.getParameter("obtainmark_y4_"+j);
					String percentage_of_marks = request.getParameter("percentage_of_marks"+j);
					String remarks = request.getParameter("remarks"+j);
					
					
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from EDU_PG_REG_GRADU_SEMWISE_DTLS where subject=:subject and p_id=:p_id and id !=:id")
							.setParameter("subject", subject)
							.setParameter("p_id", Integer.parseInt(p_id))
							.setParameter("id", Integer.parseInt(semwiseeditid))
							.uniqueResult();
					
					
					System.err.println("c--update--->     "+ c);
					
					semwiseadd.setSemwise_no_of_atmpts(semwise_no_of_atmpts);
					semwiseadd.setMaxmark_y1_(Integer.parseInt(maxmark_y1_));
					semwiseadd.setObtainmark_y1_(Integer.parseInt(obtainmark_y1_));
					semwiseadd.setMaxmark_y2_(Integer.parseInt(maxmark_y2_));
					semwiseadd.setObtainmark_y2_(Integer.parseInt(obtainmark_y2_));
					semwiseadd.setMaxmark_y3_(Integer.parseInt(maxmark_y3_));
					semwiseadd.setObtainmark_y3_(Integer.parseInt(obtainmark_y3_));
					semwiseadd.setMaxmark_y4_(Integer.parseInt(maxmark_y4_));
					semwiseadd.setObtainmark_y4_(Integer.parseInt(obtainmark_y4_));
					semwiseadd.setPercentage_of_marks(percentage_of_marks);
					semwiseadd.setRemarks(remarks);
					semwiseadd.setModified_by(username);
					semwiseadd.setModified_date(date);
					semwiseadd.setId(Integer.parseInt(semwiseeditid));
					
					if (c == 0) {
						semwiseadd.setSubject(subject);
						sessionHQL.update(semwiseadd);
						sessionHQL.flush();
						sessionHQL.clear();
					}else {
						errorsubject+=1;
					}
				}
			}
			
			if (old_count_indepth_hidden < new_count_indepth_hidden) {
				
				
				EDU_PG_REG_GRADU_SEMWISE_DTLS semwise = new EDU_PG_REG_GRADU_SEMWISE_DTLS();
				
					for (int j = old_count_indepth_hidden  + 1; j <= new_count_indepth_hidden; j++) {
						
						String subject = request.getParameter("subject"+j);
						String semwise_no_of_atmpts = request.getParameter("semwise_no_of_atmpts"+j);
						String maxmark_y1_ = request.getParameter("maxmark_y1_"+j);
						String obtainmark_y1_ = request.getParameter("obtainmark_y1_"+j);
						String maxmark_y2_ = request.getParameter("maxmark_y2_"+j);
						String obtainmark_y2_ = request.getParameter("obtainmark_y2_"+j);
						String maxmark_y3_ = request.getParameter("maxmark_y3_"+j);
						String obtainmark_y3_ = request.getParameter("obtainmark_y3_"+j);
						String maxmark_y4_ = request.getParameter("maxmark_y4_"+j);
						String obtainmark_y4_ = request.getParameter("obtainmark_y4_"+j);
						
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from EDU_PG_REG_GRADU_SEMWISE_DTLS where subject=:subject and p_id=:p_id")
								.setParameter("subject", subject)
								.setParameter("p_id", Integer.parseInt(p_id))
								.uniqueResult();
						
						String percentage_of_marks = request.getParameter("percentage_of_marks"+j);
						String remarks = request.getParameter("remarks"+j);
						
						semwise.setSubject(subject);
						semwise.setSemwise_no_of_atmpts(semwise_no_of_atmpts);
						semwise.setMaxmark_y1_(Integer.parseInt(maxmark_y1_));
						semwise.setObtainmark_y1_(Integer.parseInt(obtainmark_y1_));
						semwise.setMaxmark_y2_(Integer.parseInt(maxmark_y2_));
						semwise.setObtainmark_y2_(Integer.parseInt(obtainmark_y2_));
						semwise.setMaxmark_y3_(Integer.parseInt(maxmark_y3_));
						semwise.setObtainmark_y3_(Integer.parseInt(obtainmark_y3_));
						semwise.setMaxmark_y4_(Integer.parseInt(maxmark_y4_));
						semwise.setObtainmark_y4_(Integer.parseInt(obtainmark_y4_));
						semwise.setPercentage_of_marks(percentage_of_marks);
						semwise.setRemarks(remarks);
						semwise.setCreated_by(username);
						semwise.setCreated_date(date);
						semwise.setP_id(Integer.parseInt(p_id));
						
						if (c == 0) {
							sessionHQL.save(semwise);
							sessionHQL.flush();
							sessionHQL.clear();
						}else {
							errorsubject+=1;
						}
					}
			}
			if ( old_count_indepth_hidden > new_count_indepth_hidden) {
				for (int j = new_count_indepth_hidden + 1; j <= old_count_indepth_hidden; j++) {
					String semwiseeditid = request.getParameter("semwiseeditid"+j);
					EDU_PG_REG_GRADU_SEMWISE_DTLS del = (EDU_PG_REG_GRADU_SEMWISE_DTLS) sessionHQL
							.get(EDU_PG_REG_GRADU_SEMWISE_DTLS.class, Integer.parseInt(semwiseeditid));
					sessionHQL.delete(del);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx.commit();
		if (errornoe > 0) {
			ra.addAttribute("msg","Name of Exam Data Already Exists");
		}
		else if (errorsubject > 0) {
			ra.addAttribute("msg","Subject Data Already Exists");
		}else {
			ra.addAttribute("msg","Data saved Successfully");
		}
	} catch (RuntimeException e) {
		e.printStackTrace();
		try {
			tx.rollback();
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
		
		return new ModelAndView("redirect:Graduation_Det_PG_Url");
	}
	
	@PostMapping("/getFilterGraduation_PG_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterGraduation_PG_data(ModelMap model, Principal principal,
			int startPage, int pageLength,String Search, String orderColunm, String orderType, String name_of_exam,String month_year,String university,String no_of_attempts) {
		
			String username = principal.getName();
			int userid = da.getUsername_pg(username);

		return gddao.DataTableGraduation_PGDataList(startPage, pageLength, Search, orderColunm, orderType,name_of_exam,month_year,university,no_of_attempts);
	}
	
//	getTotalGraduation_PG_dataCount
	
	@PostMapping("/getTotalGraduation_PG_dataCount")
	public @ResponseBody long getTotalGraduation_PG_dataCount(ModelMap model, Principal principal,
			HttpSession sessionUserId, String Search, String name_of_exam,String month_year,String university,String no_of_attempts) {

		String username = principal.getName();
		int userid = da.getUsername_pg(username);
		
		return  gddao.DataTableGraduation_PGDataTotalCount(Search,name_of_exam,month_year,university,no_of_attempts);

	}
	
	
	
	

}
