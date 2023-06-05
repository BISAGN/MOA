package com.AyushEdu.controller.Curriculum;


import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_EXAMINATION_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.Teaching_Hours_Summary_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Nch_Examination_Summary_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Teaching_Hours_Summary_Dao thsd;
	
	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	
	@RequestMapping(value = "/Nch_Examination_SummUrl", method = RequestMethod.GET)
	public ModelAndView Nch_Examination_SummUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_Examination_SummUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getpaperList", common.getpaperList(sessionFactory));
		Mmap.put("getCCTermList", common.getCCTermList(sessionFactory));
		Mmap.put("getTypeofTeachingList", common.getTypeofTeachingList(sessionFactory));
		Mmap.put("getTypeofHoursList", common.getTypeofHoursList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("NchExamination_SummaryTiles");
	}
	

	@PostMapping(value = "/Nch_Examination_Summaryaction")
	public ModelAndView Nch_Examination_Summaryaction(@Validated @ModelAttribute("Nch_Examination_SummaryCMD") EDU_CC_SUMMARY_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nch_Examination_SummUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();	
		
try {
			
			String system_id = request.getParameter("system_id");
			String degree_id = request.getParameter("degree_id");
			String professional_id = request.getParameter("professional_id");
			String course_id = request.getParameter("course_id");
			
			
			if (system_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select System.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}

			if (degree_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Degree.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}

			if (professional_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Professional.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}

			if (course_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Subject.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			int count_hidden_exam_sum = Integer.parseInt(request.getParameter("count_hidden_exam_sum"));
			int exam_sum_old_count = Integer.parseInt(request.getParameter("exam_sum_old_count"));
			
			for(int j=1; j <=count_hidden_exam_sum; j++) {
				
				String exam_paper = request.getParameter("exam_paper"+j);
				String theory_comp_marks = request.getParameter("theory_comp_marks"+j);
				
				
				if (exam_paper.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Paper.");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				
				
				if (theory_comp_marks == null || theory_comp_marks.trim().equals("") || theory_comp_marks.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				
				if (validation.isOnlyNumer(theory_comp_marks) == true) {
					ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				
				if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
					ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				
				}
				
			}
	
			
			String pract_cb_hid = request.getParameter("pract_cb_hid");
			String practical_marks = request.getParameter("practical_marks");
			
			String viva_cb_hid = request.getParameter("viva_cb_hid");
			String viva_marks = request.getParameter("viva_marks");
			
			String ele_cb_hid = request.getParameter("ele_cb_hid");
			String elective_marks = request.getParameter("elective_marks");
			
			String ia_cb_hid = request.getParameter("ia_cb_hid");
			String ia_marks = request.getParameter("ia_marks");
			
			String paid = request.getParameter("paid");
			String pa_marks = request.getParameter("pa_marks");
			System.err.println("practical_marks"+practical_marks);
			
			
			if (  practical_marks == null || practical_marks.trim().equals("") || practical_marks.trim().equals("0") && pract_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Practical Marks.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			if ( viva_marks == null || viva_marks.trim().equals("") || viva_marks.trim().equals("0") && viva_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Viva Marks.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			if (elective_marks == null || elective_marks.trim().equals("") || elective_marks.trim().equals("0") && ele_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Elective Marks.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			if (ia_marks == null || ia_marks.trim().equals("") || ia_marks.trim().equals("0") && ia_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter IA Marks.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			if (pa_marks == null || pa_marks.trim().equals("") || pa_marks.trim().equals("0") && paid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter PA Marks.");
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
			
			
			String data_fetch = request.getParameter("data_fetch");
			
			if(data_fetch.equals("0")) {
				
				td.setStatus(0);
				td.setCreated_by(username);
				td.setCreated_date(date);
				
					int p_id = (int) sessionHQL.save(td);
					sessionHQL.flush(); 
					sessionHQL.clear();
					
                for(int j=1; j <=count_hidden_exam_sum; j++) {
						
						String exam_paper = request.getParameter("exam_paper"+j);
						String theory_comp_marks = request.getParameter("theory_comp_marks"+j);
						
						EDU_CC_SUMMARY_EXAMINATION_CHILD examchild1 = new EDU_CC_SUMMARY_EXAMINATION_CHILD();
						
						examchild1.setExam_paper(Integer.parseInt(exam_paper));
						examchild1.setTheory_comp_marks(Integer.parseInt(theory_comp_marks));
						examchild1.setP_id(p_id); 
						examchild1.setStatus(0);
						examchild1.setCreated_by(username);
						examchild1.setCreated_date(date);
						
						sessionHQL.save(examchild1);
						sessionHQL.flush();
						sessionHQL.clear();
						
					}
                
                EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD examchild2 = new EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD();
				
				examchild2.setPract_cb(Integer.parseInt(pract_cb_hid));
				examchild2.setPractical_marks(Integer.parseInt(practical_marks));
				examchild2.setViva_cb(Integer.parseInt(viva_cb_hid));
				examchild2.setViva_marks(Integer.parseInt(viva_marks));
				examchild2.setEle_cb(Integer.parseInt(ele_cb_hid));
				examchild2.setElective_marks(Integer.parseInt(elective_marks));
				examchild2.setIa_cb(Integer.parseInt(ia_cb_hid));
				examchild2.setIa_marks(Integer.parseInt(ia_marks));
				examchild2.setPa_cb(Integer.parseInt(paid));
				examchild2.setPa_marks(Integer.parseInt(pa_marks));
				examchild2.setP_id(p_id); 
				examchild2.setStatus(0);
				examchild2.setCreated_by(username);
				examchild2.setCreated_date(date);
				
				sessionHQL.save(examchild2);
				sessionHQL.flush();
				sessionHQL.clear();
				
				ra.addAttribute("msg", "Data Saved Successfully.");

}
			
			
			if(data_fetch.equals("1")) {
				//Update Code

				//==============================================main table=======================================================
				
				if (system_id.equals("0")) {
					ra.addAttribute("msg", "Please Select System");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				if (degree_id.equals("0")) {
					ra.addAttribute("msg", "Please Select Degree");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				if (professional_id.equals("0")) {
					ra.addAttribute("msg", "Please Select professional");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				if (course_id.equals("0")) {
					ra.addAttribute("msg", "Please Select Course");
					return new ModelAndView("redirect:Nch_Examination_SummUrl");
				}
				
				String parent_id=request.getParameter("parent_id");
				
				String hql = "update EDU_CC_SUMMARY_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = sessionHQL.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id",Integer.parseInt(parent_id));
						msg = query.executeUpdate() > 0 ? "1" : "0";
						
						
						//============================================child2===================================================
						
						for(int k=1; k <=count_hidden_exam_sum; k++) {
							
							String exam_paper = request.getParameter("exam_paper"+k);
							String theory_comp_marks = request.getParameter("theory_comp_marks"+k);
							
							
							if (exam_paper.trim().equals("0")) {
								ra.addAttribute("msg", "Please Select Paper.");
								return new ModelAndView("redirect:Nch_Examination_SummUrl");
							}
							
							
							if (theory_comp_marks == null || theory_comp_marks.trim().equals("")) {
								ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
								return new ModelAndView("redirect:Nch_Examination_SummUrl");
							}
							
							if (validation.isOnlyNumer(theory_comp_marks) == true) {
								ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
								return new ModelAndView("redirect:Nch_Examination_SummUrl");
							}
							
							if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
								ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
								return new ModelAndView("redirect:Nch_Examination_SummUrl");
							
							}
							
						}
						
						
						String hql1 = "update EDU_CC_SUMMARY_EXAMINATION_CHILD set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";

						Query query1 = sessionHQL.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
								.setParameter("degree_id", Integer.parseInt(degree_id))
								.setParameter("professional_id", Integer.parseInt(professional_id))
								.setParameter("course_id", Integer.parseInt(course_id))
								.setParameter("modified_by", username).setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(parent_id));
								msg = query1.executeUpdate() > 0 ? "1" : "0";
								
								if ( exam_sum_old_count <= count_hidden_exam_sum) {
									for (int l = 1; l <= exam_sum_old_count; l++) {
										
										String exm_smry_editid = request.getParameter("exm_smry_editid"+l);
										
										EDU_CC_SUMMARY_EXAMINATION_CHILD add1 = (EDU_CC_SUMMARY_EXAMINATION_CHILD) sessionHQL
												.get(EDU_CC_SUMMARY_EXAMINATION_CHILD.class, Integer.parseInt(exm_smry_editid));
										
										String exam_paper = request.getParameter("exam_paper"+l);
										String theory_comp_marks = request.getParameter("theory_comp_marks"+l);
										add1.setExam_paper(Integer.parseInt(exam_paper));
									 	add1.setTheory_comp_marks(Integer.parseInt(theory_comp_marks));
									 	add1.setStatus(0);
										add1.setCreated_by(username);
										add1.setCreated_date(date);
										add1.setId(Integer.parseInt(exm_smry_editid));
										sessionHQL.update(add1);
										sessionHQL.flush();
										sessionHQL.clear();
									}
								}
								
								if ( exam_sum_old_count > count_hidden_exam_sum) {
									
									for (int j = count_hidden_exam_sum + 1; j <= exam_sum_old_count; j++) {
										
										String exm_smry_editid = request.getParameter("id"+j);
										
										EDU_CC_SUMMARY_EXAMINATION_CHILD del1 = (EDU_CC_SUMMARY_EXAMINATION_CHILD) sessionHQL
												.get(EDU_CC_SUMMARY_EXAMINATION_CHILD.class, Integer.parseInt(exm_smry_editid));
										sessionHQL.delete(del1);
										sessionHQL.flush();
										sessionHQL.clear();
									}
								}
								
								
					//============================================child3===================================================

								String cid = request.getParameter("cid");
								System.err.println("cid---------------"+cid);
								
								EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD add2 = (EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD) sessionHQL
										.get(EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD.class, Integer.parseInt(cid));
								
								add2.setPract_cb(Integer.parseInt(pract_cb_hid));
								add2.setPractical_marks(Integer.parseInt(practical_marks));
								add2.setViva_cb(Integer.parseInt(viva_cb_hid));
								add2.setViva_marks(Integer.parseInt(viva_marks));
								add2.setEle_cb(Integer.parseInt(ele_cb_hid));
								add2.setElective_marks(Integer.parseInt(elective_marks));
								add2.setIa_cb(Integer.parseInt(ia_cb_hid));
								add2.setIa_marks(Integer.parseInt(ia_marks));
								add2.setPa_cb(Integer.parseInt(paid));
								add2.setPa_marks(Integer.parseInt(pa_marks));
								add2.setCreated_by(username);
								add2.setCreated_date(date);
								add2.setId(Integer.parseInt(cid));
								sessionHQL.update(add2);
								sessionHQL.flush();
								sessionHQL.clear();
								
								
								 ra.addAttribute("msg", "Data Updated Successfully.");
			}
			tx.commit(); 
				} catch (RuntimeException e) {
					e.printStackTrace();
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
				return new ModelAndView("redirect:Nch_Examination_SummUrl");
			}
	
	@RequestMapping(value = "/getfetchNchsaveddata_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getfetchNchsaveddata_ctrl(String system_id,String degree_id,String professional_id,String course_id) {
	 ArrayList<ArrayList<String>> list = thsd.getfetchsaveddata_list(system_id,degree_id,professional_id,course_id);
		return list;
	}
	

	@RequestMapping(value = "/getfetchNchexam_sumsaveddata_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getfetchNchexam_sumsaveddata_ctrl(String p_id) {
	 ArrayList<ArrayList<String>> list = thsd.getfetchfetchexam_sumsaveddata_list(p_id);
		return list;
	}
//	
//	@RequestMapping(value = "/View_summary_Url", method = RequestMethod.GET)
//	public ModelAndView View_summary_Url(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		try {
////			SECURITY -- RIDDHI 	
////			if(request.getHeader("Referer") == null ) { 
//////				 session.invalidate();
////				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
////				 return new ModelAndView("redirect:/landingpage");
////			 }
////			String roleid1 = session.getAttribute("roleid").toString();
////			 Boolean val = roledao.ScreenRedirect("Nch_Examination_SummUrl", roleid1);		
////				if(val == false) {
////					return new ModelAndView("AccessTiles");
////			}
//			String role = session.getAttribute("role").toString();	
//			Mmap.put("msg", msg);
//			Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
//			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView("view_summary_Tiles");
//	}
	
//	@RequestMapping(value = "/getViewSummaryTH_data", method = RequestMethod.POST)
//	public @ResponseBody   List<Map<String, Object>> getViewSummaryTH_data(String course_id) {
//		List<Map<String, Object>> ViewSummaryTH = PARDAO.DataTableEdu_Reg_Report_masterDataList_pdf(course_id);
//		return ViewSummaryTH;
//	}
//	@RequestMapping(value = "/getViewExami_SummaryTH_data", method = RequestMethod.POST)
//	public @ResponseBody   List<Map<String, Object>> getViewExami_SummaryTH_data(String course_id) {
//		List<Map<String, Object>> ViewSummaryTH = PARDAO.examination_list(course_id);
//		return ViewSummaryTH;
//	}
}
