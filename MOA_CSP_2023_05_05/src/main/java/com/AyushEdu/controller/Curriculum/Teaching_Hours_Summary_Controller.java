package com.AyushEdu.controller.Curriculum;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_EXAMINATION_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_SUMMARY_TEACH_HRS_CHILD;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.Teaching_Hours_Summary_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Teaching_Hours_Summary_Controller {

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
	
	
	@RequestMapping(value = "/teach_hrs_summary_Url", method = RequestMethod.GET)
	public ModelAndView teach_hrs_summary_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("teach_hrs_summary_Url", roleid1);		
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

		return new ModelAndView("teach_hrs_summary_Tiles");
	}
	
	
	
	@PostMapping(value = "/teach_hrs_summary_action")
	public ModelAndView teach_hrs_summary_action(@Validated @ModelAttribute("teach_hrs_summary_CMD") EDU_CC_SUMMARY_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("teach_hrs_summary_Url", roleid1);		
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
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}

			if (degree_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Degree.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}

			if (professional_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Professional.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}

			if (course_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Subject.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			int count_hidden_cbc = Integer.parseInt(request.getParameter("count_hidden_cbc"));
			int teach_hrs_old_count = Integer.parseInt(request.getParameter("teach_hrs_old_count"));
			
			for(int i=1; i <=count_hidden_cbc; i++) {
				
				String hours_type = request.getParameter("hours_type"+i);
				String lecture_type = request.getParameter("lecture_type"+i);
				String paper = request.getParameter("paper"+i);
				String no_of_hours = request.getParameter("no_of_hours"+i);
				
				if (hours_type.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Hours Type.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				if (lecture_type.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Lecture Type.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				if (paper.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Paper.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				if (no_of_hours == null || no_of_hours.trim().equals("") || no_of_hours.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter  No of Hours.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				
				if (validation.isOnlyNumer(no_of_hours) == true) {
					ra.addAttribute("msg", "No of Hours " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				
				
				if (validation.maxlengthcheckneetmark(no_of_hours.trim()) == false) {
					ra.addAttribute("msg","No of Hours "+ validation.MaxlengthcheckMSGneetmark3);
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
			}
			
			int count_hidden_exam_sum = Integer.parseInt(request.getParameter("count_hidden_exam_sum"));
			int exam_sum_old_count = Integer.parseInt(request.getParameter("exam_sum_old_count"));
			
			for(int j=1; j <=count_hidden_exam_sum; j++) {
				
				String exam_paper = request.getParameter("exam_paper"+j);
				String theory_comp_marks = request.getParameter("theory_comp_marks"+j);
				
				
				if (exam_paper.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Paper.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				
				if (theory_comp_marks == null || theory_comp_marks.trim().equals("") || theory_comp_marks.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				if (validation.isOnlyNumer(theory_comp_marks) == true) {
					ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				}
				
				if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
					ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
					return new ModelAndView("redirect:teach_hrs_summary_Url");
				
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
			
			
			
			
			if (  practical_marks == null || practical_marks.trim().equals("") || practical_marks.trim().equals("0") && pract_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Practical Marks.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if ( viva_marks == null || viva_marks.trim().equals("") || viva_marks.trim().equals("0") && viva_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Viva Marks.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (elective_marks == null || elective_marks.trim().equals("") || elective_marks.trim().equals("0") && ele_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter Elective Marks.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (ia_marks == null || ia_marks.trim().equals("") || ia_marks.trim().equals("0") && ia_cb_hid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter IA Marks.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (pa_marks == null || pa_marks.trim().equals("") || pa_marks.trim().equals("0") && paid.trim().equals("1")) {
				ra.addAttribute("msg", "Please Enter PA Marks.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			
			
		String data_fetch = request.getParameter("data_fetch");
				
		if(data_fetch.equals("0")) {
		
				td.setStatus(0);
				td.setCreated_by(username);
				td.setCreated_date(date);
				
					int p_id = (int) sessionHQL.save(td);
					sessionHQL.flush(); 
					sessionHQL.clear();
					
					for(int i=1; i <=count_hidden_cbc; i++) {
						
						String hours_type = request.getParameter("hours_type"+i);
						String lecture_type = request.getParameter("lecture_type"+i);
						String paper = request.getParameter("paper"+i);
						String no_of_hours = request.getParameter("no_of_hours"+i);
						
						
						EDU_CC_SUMMARY_TEACH_HRS_CHILD teachchild = new EDU_CC_SUMMARY_TEACH_HRS_CHILD();
						
						teachchild.setHours_type(Integer.parseInt(hours_type));
						teachchild.setLecture_type(Integer.parseInt(lecture_type));
						teachchild.setPaper(Integer.parseInt(paper));
						teachchild.setNo_of_hours(Integer.parseInt(no_of_hours));
						teachchild.setP_id(p_id);
						teachchild.setStatus(0);
						teachchild.setCreated_by(username);
						teachchild.setCreated_date(date);
							
						
						 sessionHQL.save(teachchild);
						 sessionHQL.flush();
						 sessionHQL.clear();
						 
					}
					
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
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Course");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		
//		
		for(int i=1; i <= count_hidden_cbc; i++) {

			String hours_type = request.getParameter("hours_type"+i);
			String lecture_type = request.getParameter("lecture_type"+i);
			String paper = request.getParameter("paper"+i);
			String no_of_hours = request.getParameter("no_of_hours"+i);
			
			if (hours_type.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Hours Type.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (lecture_type.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Lecture Type.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (paper.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Paper.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (no_of_hours == null || no_of_hours.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter  No of Hours.");
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (validation.isOnlyNumer(no_of_hours) == true) {
				ra.addAttribute("msg", "No of Hours " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
			
			if (validation.maxlengthcheckneetmark(no_of_hours.trim()) == false) {
				ra.addAttribute("msg","No of Hours "+ validation.MaxlengthcheckMSGneetmark3);
				return new ModelAndView("redirect:teach_hrs_summary_Url");
			}
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
						
						//============================================child1=================================================
				
				if ( teach_hrs_old_count <= count_hidden_cbc) {
					
					for (int j = 1; j <= teach_hrs_old_count; j++) {
						
						String teach_hrs_editid = request.getParameter("teach_hrs_editid"+j);
						System.err.println("teach_hrs_editid"+teach_hrs_editid);
						EDU_CC_SUMMARY_TEACH_HRS_CHILD add = (EDU_CC_SUMMARY_TEACH_HRS_CHILD) sessionHQL
								.get(EDU_CC_SUMMARY_TEACH_HRS_CHILD.class, Integer.parseInt(teach_hrs_editid));
						
						String hours_type = request.getParameter("hours_type"+j);
						String lecture_type = request.getParameter("lecture_type"+j);
						String paper = request.getParameter("paper"+j);
						String no_of_hours = request.getParameter("no_of_hours"+j);
						add.setHours_type(Integer.parseInt(hours_type));
					 	add.setLecture_type(Integer.parseInt(lecture_type));
					 	add.setPaper(Integer.parseInt(paper));
					 	add.setNo_of_hours(Integer.parseInt(no_of_hours));
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setId(Integer.parseInt(teach_hrs_editid));
						add.setStatus(0);
						sessionHQL.update(add);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					
					
//					for(int j=1; j <=count_hidden_exam_sum; j++) {
//						
//						String exam_paper = request.getParameter("exam_paper"+j);
//						String theory_comp_marks = request.getParameter("theory_comp_marks"+j);
//						
//						
//						if (exam_paper.trim().equals("0")) {
//							ra.addAttribute("msg", "Please Select Paper.");
//							return new ModelAndView("redirect:teach_hrs_summary_Url");
//						}
//						
//						
//						if (theory_comp_marks == null || theory_comp_marks.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
//							return new ModelAndView("redirect:teach_hrs_summary_Url");
//						}
//						
//						if (validation.isOnlyNumer(theory_comp_marks) == true) {
//							ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
//							return new ModelAndView("redirect:teach_hrs_summary_Url");
//						}
//						
//						if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
//							ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
//							return new ModelAndView("redirect:teach_hrs_summary_Url");
//						
//						}
//						
//					}
				}
//				
				if ( teach_hrs_old_count < count_hidden_cbc) {
					EDU_CC_SUMMARY_TEACH_HRS_CHILD xray = new EDU_CC_SUMMARY_TEACH_HRS_CHILD();
					
						for (int j = teach_hrs_old_count  + 1; j <= count_hidden_cbc; j++) {
							
							String hours_type = request.getParameter("hours_type"+j);
							String lecture_type = request.getParameter("lecture_type"+j);
							String paper = request.getParameter("paper"+j);
							String no_of_hours = request.getParameter("no_of_hours"+j);
							xray.setHours_type(Integer.parseInt(hours_type));
						 	xray.setLecture_type(Integer.parseInt(lecture_type));
						 	xray.setPaper(Integer.parseInt(paper));
						 	xray.setNo_of_hours(Integer.parseInt(no_of_hours));
						 	xray.setStatus(0);
							xray.setCreated_by(username);
							xray.setCreated_date(date);
							xray.setP_id(Integer.parseInt(parent_id));
							sessionHQL.save(xray);
							sessionHQL.flush();
							sessionHQL.clear();
						}
				}
				
				if ( teach_hrs_old_count > count_hidden_cbc) {
					
					for (int j = count_hidden_cbc + 1; j <= teach_hrs_old_count; j++) {
						
						String teach_hrs_editid = request.getParameter("id"+j);
						
						EDU_CC_SUMMARY_TEACH_HRS_CHILD del = (EDU_CC_SUMMARY_TEACH_HRS_CHILD) sessionHQL
								.get(EDU_CC_SUMMARY_TEACH_HRS_CHILD.class, Integer.parseInt(teach_hrs_editid));
						sessionHQL.delete(del);
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
	
				
				//============================================child2===================================================
				
				for(int k=1; k <=count_hidden_exam_sum; k++) {
					
					String exam_paper = request.getParameter("exam_paper"+k);
					String theory_comp_marks = request.getParameter("theory_comp_marks"+k);
					
					
					if (exam_paper.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Paper.");
						return new ModelAndView("redirect:teach_hrs_summary_Url");
					}
					
					
					if (theory_comp_marks == null || theory_comp_marks.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
						return new ModelAndView("redirect:teach_hrs_summary_Url");
					}
					
					if (validation.isOnlyNumer(theory_comp_marks) == true) {
						ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
						return new ModelAndView("redirect:teach_hrs_summary_Url");
					}
					
					if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
						ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
						return new ModelAndView("redirect:teach_hrs_summary_Url");
					
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
						
						if ( exam_sum_old_count < count_hidden_exam_sum) {
							EDU_CC_SUMMARY_EXAMINATION_CHILD xray1 = new EDU_CC_SUMMARY_EXAMINATION_CHILD();
							
								for (int l = exam_sum_old_count  + 1; l <= count_hidden_exam_sum; l++) {
									
									String exam_paper = request.getParameter("exam_paper"+l);
									String theory_comp_marks = request.getParameter("theory_comp_marks"+l);
									xray1.setExam_paper(Integer.parseInt(exam_paper));
									xray1.setTheory_comp_marks(Integer.parseInt(theory_comp_marks));
									xray1.setStatus(0);
									xray1.setCreated_by(username);
									xray1.setCreated_date(date);
									xray1.setP_id(Integer.parseInt(parent_id));
									sessionHQL.save(xray1);
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
		
						
							
//							String exam_paper = request.getParameter("exam_paper"+k);
//							String theory_comp_marks = request.getParameter("theory_comp_marks"+k);
//							
//							
//							if (exam_paper.trim().equals("0")) {
//								ra.addAttribute("msg", "Please Select Paper.");
//								return new ModelAndView("redirect:teach_hrs_summary_Url");
//							}
//							
//							
//							if (theory_comp_marks == null || theory_comp_marks.trim().equals("")) {
//								ra.addAttribute("msg", "Please Enter Theory Comp Marks.");
//								return new ModelAndView("redirect:teach_hrs_summary_Url");
//							}
//							
//							if (validation.isOnlyNumer(theory_comp_marks) == true) {
//								ra.addAttribute("msg", "Theory Comp Marks " + validation.isOnlyNumerMSG);
//								return new ModelAndView("redirect:teach_hrs_summary_Url");
//							}
//							
//							if (validation.maxlengthcheckneetmark(theory_comp_marks) == false) {
//								ra.addAttribute("msg","Theory Comp Marks "+ validation.MaxlengthcheckMSGneetmark3);
//								return new ModelAndView("redirect:teach_hrs_summary_Url");
//							
//							}
							
						
//						String hql12 = "update EDU_CC_SUMMARY_EXAMINATION_PRACT_COMP_CHILD set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
//								+ " where id=:id";
//
//						Query query12 = sessionHQL.createQuery(hql12).setParameter("system_id", Integer.parseInt(system_id))
//								.setParameter("degree_id", Integer.parseInt(degree_id))
//								.setParameter("professional_id", Integer.parseInt(professional_id))
//								.setParameter("course_id", Integer.parseInt(course_id))
//								.setParameter("modified_by", username).setParameter("modified_date", new Date())
//								.setParameter("id", Integer.parseInt(parent_id));
//								msg = query12.executeUpdate() > 0 ? "1" : "0";
						
								
//								if ( exam_sum_old_count <= count_hidden_exam_sum) {
//									for (int l = 1; l <= exam_sum_old_count; l++) {
						

										
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
//									}
//								}
						
		
		//
					 
					 ra.addAttribute("msg", "Data Updated Successfully.");
	}
	tx.commit(); 
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
		return new ModelAndView("redirect:teach_hrs_summary_Url");
	}
	
	@RequestMapping(value = "/getfetchsaveddata_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getfetchsaveddata_ctrl(String system_id,String degree_id,String professional_id,String course_id) {
	 ArrayList<ArrayList<String>> list = thsd.getfetchsaveddata_list(system_id,degree_id,professional_id,course_id);
		return list;
	}
	
	@RequestMapping(value = "/getfetchteach_hrssaveddata_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getfetchteach_hrssaveddata_ctrl(String p_id) {
	 ArrayList<ArrayList<String>> list = thsd.getfetchteach_hrssaveddata_list(p_id);
		return list;
	}

	@RequestMapping(value = "/getfetchexam_sumsaveddata_ctrl", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getfetchexam_sumsaveddata_ctrl(String p_id) {
	 ArrayList<ArrayList<String>> list = thsd.getfetchfetchexam_sumsaveddata_list(p_id);
		return list;
	}
	
	@RequestMapping(value = "/View_summary_Url", method = RequestMethod.GET)
	public ModelAndView View_summary_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("teach_hrs_summary_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();	
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("view_summary_Tiles");
	}
	
	@RequestMapping(value = "/getViewSummaryTH_data", method = RequestMethod.POST)
	public @ResponseBody   List<Map<String, Object>> getViewSummaryTH_data(String course_id) {
		List<Map<String, Object>> ViewSummaryTH = PARDAO.DataTableEdu_Reg_Report_masterDataList_pdf(course_id);
		return ViewSummaryTH;
	}
	@RequestMapping(value = "/getViewExami_SummaryTH_data", method = RequestMethod.POST)
	public @ResponseBody   List<Map<String, Object>> getViewExami_SummaryTH_data(String course_id) {
		List<Map<String, Object>> ViewSummaryTH = PARDAO.examination_list(course_id);
		return ViewSummaryTH;
	}
}
