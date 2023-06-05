
package com.AyushEdu.controller.Curriculum;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_PRACTICAL_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_T4_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_T4_LEARNING_OBJECT_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_T4_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_T4_LEARNING_OBJECT_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_LIST_OF_PRACTICAL_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Nch_T4_Learning_Object_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.T4_Search_Learning_Object_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Nch_T4_Learning_Object_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	CommonController common;
	

	@Autowired
	Nch_T4_Learning_Object_Dao NTSLODAO;
	
	@Autowired
	Professional_Ayu_Report_Dao PARDAO;
	
	@Autowired
	ValidationController validation;
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Nch_T4_Learning_Object_Url", method = RequestMethod.GET)
	public ModelAndView Nch_T4_Learning_Object_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		
		String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
//		Mmap.put("getCobyCourse", common.getCobyCourse(sessionFactory));
		Mmap.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
		Mmap.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
		Mmap.put("getMillers_level", common.getMillers_level(sessionFactory));
		Mmap.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
		Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
//		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		Mmap.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
		Mmap.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));
		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		
		
	 }catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Nch_T4_Learning_Object_Tiles");
	}



//@RequestMapping(value = "/getPracticalListby_Course_NCH1", method = RequestMethod.POST)
//public @ResponseBody List<EDU_CC_TB_NCH_LIST_OF_PRACTICAL_PARENT> getPracticalListby_Course_NCH(String course_id)  {
//
//	List<EDU_CC_TB_NCH_LIST_OF_PRACTICAL_PARENT> list =  common.getPracticalListby_Course_NCH(course_id);
//	
//	return list;
//}

	
@PostMapping(value = "/nch_t4_learning_objectAction")
public ModelAndView nch_t4_learning_objectAction(@Validated @ModelAttribute("nch_t4_learning_objectCMD") CC_TB_NCH_T4_LEARNING_OBJECT_PARENT td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
		MultipartHttpServletRequest mul)throws IOException, ParseException {
//	SECURITY -- RIDDHI 	

	if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
	
		
	int id = td.getId() > 0 ? td.getId() : 0;
	System.err.println("----id-------"+id);
	
	Date date = new Date();
	String username = principal.getName();
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	
	String system_id = request.getParameter("system_id");
	String degree_id = request.getParameter("degree_id");
	String professional_id = request.getParameter("professional_id");
	String course_id = request.getParameter("course_id");
	String practical_id = request.getParameter("practical_id");
	String learning_outcome_hid = request.getParameter("learning_outcome_hid");
	
	System.out.println("learning_outcome_hid========================"+learning_outcome_hid);
	if (system_id.equals("0")) {
		ra.addAttribute("msg", "Please Select System");
		return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
	}
	if (degree_id.equals("0")) {
		ra.addAttribute("msg", "Please Select Degree");
		return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
	}
	if (professional_id.equals("0")) {
		ra.addAttribute("msg", "Please Select professional");
		return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
	}
	if (course_id.equals("0")) {
		ra.addAttribute("msg", "Please Select Subject");
		return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
	}
	if (practical_id.equals("0")) {
		ra.addAttribute("msg", "Please Select Practical");
		return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
	}
	if (learning_outcome_hid.equals("0")) {
		ra.addAttribute("msg", "Please Select Learning Outcome");
		return new ModelAndView("redirect:Nch_theory_learning_obj_url");
	}
	try {

//		Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  CC_TB_NCH_T4_LEARNING_OBJECT_PARENT where system_id=:system_id and degree_id=:degree_id \n"
//					+ " and professional_id=:professional_id and course_id=:course_id and  practical_id=:practical_id and status=0\n"
//					+ " and id!=:id")
//				.setParameter("system_id", Integer.parseInt(system_id))
//				.setParameter("degree_id", Integer.parseInt(degree_id))
//				.setParameter("professional_id", Integer.parseInt(professional_id))
//				.setParameter("course_id", Integer.parseInt(course_id))
//				.setParameter("practical_id", Integer.parseInt(practical_id))
//				.setParameter("id", id).uniqueResult();
//		
//		if (id == 0) {
//			if (c == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				td.setPract_learning_obj(learning_outcome_hid);
				int parent_id = (int) sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();
				
				CC_TB_NCH_T4_LEARNING_OBJECT_CHILD od = new CC_TB_NCH_T4_LEARNING_OBJECT_CHILD();
				
				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
				for(int i=1; i <= count_hidden_att; i++) {
					
					String generic_compte = request.getParameter("generic_compte"+i);
					String subject_area = request.getParameter("subject_area"+i);
					String millers_knows = request.getParameter("millers_knows"+i);
					//System.err.println("\"millers_knows\"+i---------->>   "  +"millers_knows"+i);
					String specific_compet = request.getParameter("specific_compet"+i);
					String spec_learn_object = request.getParameter("spec_learn_object"+i);
					String blooms_domain = request.getParameter("blooms_domain"+i);
					String guilberts_level = request.getParameter("guilberts_level"+i);
					String must_know_dknow_nk = request.getParameter("must_know_dknow_nk"+i);
					String tl_mthd_med = request.getParameter("tl_mthd_med"+i);
					String formtive_assessmt = request.getParameter("formtive_assessmt"+i);
					String sumtive_assessmt = request.getParameter("sumtive_assessmt"+i);
					String integ_horivtspi = request.getParameter("integ_horivtspi"+i);
					String nch_term = request.getParameter("nch_term"+i);
					
	
					
					if (generic_compte.equals("")) {
						ra.addAttribute("msg", "Please Enter Generic Competency.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (subject_area.equals("")) {
						ra.addAttribute("msg", "Please Enter Subject Area.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
				 //	if (validation.checkDescriptionLengthHelpdeskLength(b3_learning_obj) == false) {
					//	ra.addAttribute("msg", "Lecture Hours " + validation.DescriptionLengthHelpdeskMSG);
						//return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					//}
					if (millers_knows.equals("0")) {
						ra.addAttribute("msg", "Please Select Millers Knows/Knows how/ Shows how/Does.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (specific_compet.equals("")) {
						ra.addAttribute("msg", "Please Enter Specific Competency.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (spec_learn_object.equals("")) {
						ra.addAttribute("msg", "Please Enter Special learning objectives.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (blooms_domain.equals("0")) {
						ra.addAttribute("msg", "Please Select Blooms Domain.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (guilberts_level.equals("0")) {
						ra.addAttribute("msg", "Please Select Guilberts level.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (must_know_dknow_nk.equals("0")) {
						ra.addAttribute("msg", "Please Select Must know/ Desire to know/ Nice to know.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (tl_mthd_med.equals("0")) {
						ra.addAttribute("msg", "Please Enter TL Method/Media.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
					if (formtive_assessmt.equals("0")) {
						ra.addAttribute("msg", "Please Select Formative Assessment.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
                    if (sumtive_assessmt.equals("0")) {
						ra.addAttribute("msg", "Please Select Summative assessment.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
                    if (integ_horivtspi.equals("")) {
						ra.addAttribute("msg", "Please Enter Integration Horizontal/ Vertical/Spiral.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
                    if (nch_term.equals("0")) {
						ra.addAttribute("msg", "Please Select Term.");
						return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
					}
 
					od.setGeneric_compte(generic_compte);
					od.setSubject_area(subject_area);
					od.setMillers_knows(Integer.parseInt(millers_knows));
					od.setSpecific_compet(specific_compet);
					od.setSpec_learn_object(spec_learn_object);
				 	od.setBlooms_domain(Integer.parseInt(blooms_domain));
				 	od.setGuilberts_level(Integer.parseInt(guilberts_level));
				 	od.setMust_know_dknow_nk(Integer.parseInt(must_know_dknow_nk));
				 	od.setTl_mthd_med(Integer.parseInt(tl_mthd_med));
				 	od.setFormtive_assessmt(Integer.parseInt(formtive_assessmt));
				 	od.setSumtive_assessmt(Integer.parseInt(sumtive_assessmt));
				 	od.setInteg_horivtspi(integ_horivtspi);
				 	od.setNch_term(Integer.parseInt(nch_term));
				 	
				 	od.setP_id(parent_id);
				 	od.setCreated_by(username);
				 	od.setCreated_date(date);
					sessionHQL.save(od);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				
				tx.commit();
				ra.addAttribute("msg", "Data Saved Successfully.");
//			} else {
//				ra.addAttribute("msg", "Data already Exist.");
//			}
//		}
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
	return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
 }


@RequestMapping(value = "admin/Search_Nch_T4_Learning_Object_Url", method = RequestMethod.GET)
public ModelAndView Search_Nch_T4_Learning_Object_Url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

	try {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	
	
	String role = session.getAttribute("role").toString();	
	Mmap.put("msg", msg);
	Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
	Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
	Mmap.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
	Mmap.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
	Mmap.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
	Mmap.put("getMillers_level", common.getMillers_level(sessionFactory));
	Mmap.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
	Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
	Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
	Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
	Mmap.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
	Mmap.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));
	Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
	
	
	
} catch (Exception e) {
	e.printStackTrace();
}
	return new ModelAndView("Nch_Search_T4_Learning_Object_Tiles");
}




 @PostMapping("/getFilterNchT4SearchLearningObject_data")
	public @ResponseBody List<Map<String, Object>> getTotalNchT4SearchLearningObject_dataCount(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String practical_id,HttpSession session) {
	 String role = session.getAttribute("role").toString();	
	 return NTSLODAO.DataTableNchT4SearchLearningObjectDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,professional_id,course_id,practical_id,role);

	}
 
 @PostMapping("/getTotalNchT4SearchLearningObject_dataCount")
	public @ResponseBody long getTotalNchT4SearchLearningObject_dataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,String professional_id,String course_id,String practical_id,HttpSession session) {
	 String role = session.getAttribute("role").toString();
		return NTSLODAO.DataTableNchT4SearchLearningObjectDataTotalCount(Search, system_id, degree_id,professional_id,course_id,practical_id,role);
	}

 @RequestMapping(value = "/NchT4SearchLearningObject_ChildUrl", method = RequestMethod.POST)
 public @ResponseBody   ArrayList<ArrayList<String>> NchT4SearchLearningObject_ChildUrl(String course_id) {
	 ArrayList<ArrayList<String>> list = NTSLODAO.getPopup_NchChild_LearningDatalist(course_id);
		return list;
	}
 
 @RequestMapping(value = "/NchT4SearchLearningObject_ChildUrlpop", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> NchT4SearchLearningObject_ChildUrlpop(String hid) {
	 ArrayList<ArrayList<String>> list = NTSLODAO.getPop_Up_view_child_data(hid);
		return list;
	}

 
 @RequestMapping(value = "/Edit_Nch_T4_Learning_Object_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Nch_T4_Learning_Object_Url(@ModelAttribute("id1") int id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			HttpSession session,HttpServletRequest request) {

		try {	
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
	
		ArrayList<ArrayList<String>> GetLearning_Parent_Data = NTSLODAO.GetNchLearning_Parent_Data(id);
		List<ArrayList<String>> liteLearningchildlist = NTSLODAO.getNchLearning_Child_By_id(id);
		
		String role = session.getAttribute("role").toString();	
			
		model.put("edit_nch_t4_learning_objectCMD", NTSLODAO.GetNchLearning_Parent_Data(id));
		model.put("list", GetLearning_Parent_Data);
		model.put("liteLearningchildlist", liteLearningchildlist);
		model.put("getSystemList", common.getSystemList(sessionFactory,role));		
		model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		//model.put("getTopicList", common.getTopicList(sessionFactory));
		model.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
		model.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
		model.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
		model.put("getMillers_level", common.getMillers_level(sessionFactory));
		model.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
		model.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		model.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
		model.put("geti3_termList", common.geti3_termList(sessionFactory));
		model.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
		model.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));
		model.put("geti3_termList", common.geti3_termList(sessionFactory));
	//	model.put("geti3_termList", common.geti3_termList(sessionFactory));
		
		
		model.put("msg", msg);
		} 
        catch (Exception e) {
			e.printStackTrace();
	  } 
		return new ModelAndView("Edit_Nch_T4_Learning_Object_Tiles");
	}
 
 @RequestMapping(value = "/edit_nch_t4_learning_objectAction", method = RequestMethod.POST)
	public ModelAndView edit_nch_t4_learning_objectAction(@ModelAttribute("edit_nch_t4_learning_objectCMD") CC_TB_NCH_T4_LEARNING_OBJECT_PARENT rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//		SECURITY -- RIDDHI 	

	 if(request.getHeader("Referer") == null ) { 
//		 session.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
		String username = session.getAttribute("username").toString();
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		Date date = new Date();

		String id = request.getParameter("pmid");
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String practical_id = request.getParameter("practical_id");
		String learning_outcome_hid = request.getParameter("learning_outcome_hid");
		
		
		
		if (system_id.equals("0")) {
			
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
		}
		if (practical_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Practical");
			return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
		}
		if (learning_outcome_hid.equals("0")) {
			ra.addAttribute("msg", "Please Select Learning Outcome");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
		int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
		
		for(int i=1; i <= new_count_hidden; i++) {

			String generic_compte = request.getParameter("generic_compte"+i);
			String subject_area = request.getParameter("subject_area"+i);
			String millers_knows = request.getParameter("millers_knows"+i);
			//System.err.println("\"millers_knows\"+i---------->>   "  +"millers_knows"+i);
			String specific_compet = request.getParameter("specific_compet"+i);
			String spec_learn_object = request.getParameter("spec_learn_object"+i);
			String blooms_domain = request.getParameter("blooms_domain"+i);
			String guilberts_level = request.getParameter("guilberts_level"+i);
			String must_know_dknow_nk = request.getParameter("must_know_dknow_nk"+i);
			String tl_mthd_med = request.getParameter("tl_mthd_med"+i);
			String formtive_assessmt = request.getParameter("formtive_assessmt"+i);
			String sumtive_assessmt = request.getParameter("sumtive_assessmt"+i);
			String integ_horivtspi = request.getParameter("integ_horivtspi"+i);
			String nch_term = request.getParameter("nch_term"+i);
			
			
			
			

			if (generic_compte.equals("")) {
				ra.addAttribute("msg", "Please Enter Generic Competency.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (subject_area.equals("")) {
				ra.addAttribute("msg", "Please Enter Subject Area.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
		 //	if (validation.checkDescriptionLengthHelpdeskLength(b3_learning_obj) == false) {
			//	ra.addAttribute("msg", "Lecture Hours " + validation.DescriptionLengthHelpdeskMSG);
				//return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			//}
			if (millers_knows.equals("0")) {
				ra.addAttribute("msg", "Please Select Millers Knows/Knows how/ Shows how/Does.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (specific_compet.equals("")) {
				ra.addAttribute("msg", "Please Enter Specific Competency.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (spec_learn_object.equals("")) {
				ra.addAttribute("msg", "Please Enter Special learning objectives.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (blooms_domain.equals("0")) {
				ra.addAttribute("msg", "Please Select Blooms Domain.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (guilberts_level.equals("0")) {
				ra.addAttribute("msg", "Please Select Guilberts level.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (must_know_dknow_nk.equals("0")) {
				ra.addAttribute("msg", "Please Select Must know/ Desire to know/ Nice to know.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (tl_mthd_med.equals("0")) {
				ra.addAttribute("msg", "Please Enter TL Method/Media.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
			if (formtive_assessmt.equals("0")) {
				ra.addAttribute("msg", "Please Select Formative Assessment.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
            if (sumtive_assessmt.equals("0")) {
				ra.addAttribute("msg", "Please Select Summative assessment.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
            if (integ_horivtspi.equals("")) {
				ra.addAttribute("msg", "Please Enter Integration Horizontal/ Vertical/Spiral.");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
            if (nch_term.equals("0")) {
				ra.addAttribute("msg", "Please Select Term");
				return new ModelAndView("redirect:Nch_T4_Learning_Object_Url");
			}
		}
		
		
		try {
			
				String hql = "update CC_TB_NCH_T4_LEARNING_OBJECT_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,practical_id=:practical_id,pract_learning_obj=:pract_learning_obj,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("professional_id", Integer.parseInt(professional_id))
						.setParameter("course_id", Integer.parseInt(course_id))
						.setParameter("practical_id", Integer.parseInt(practical_id))
						.setParameter("pract_learning_obj", learning_outcome_hid)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
						msg = query.executeUpdate() > 0 ? "1" : "0";
						
				
				
				if ( old_hidden_att <= new_count_hidden) {
					for (int j = 1; j <= old_hidden_att; j++) {
						
						String editid = request.getParameter("eid"+j);
						
						CC_TB_NCH_T4_LEARNING_OBJECT_CHILD add = (CC_TB_NCH_T4_LEARNING_OBJECT_CHILD) session1
								.get(CC_TB_NCH_T4_LEARNING_OBJECT_CHILD.class, Integer.parseInt(editid));
						
						String generic_compte = request.getParameter("generic_compte"+j);
						String subject_area = request.getParameter("subject_area"+j);
						String millers_knows = request.getParameter("millers_knows"+j);
						//System.err.println("\"millers_knows\"+i---------->>   "  +"millers_knows"+j);
						String specific_compet = request.getParameter("specific_compet"+j);
						String spec_learn_object = request.getParameter("spec_learn_object"+j);
						String blooms_domain = request.getParameter("blooms_domain"+j);
						String guilberts_level = request.getParameter("guilberts_level"+j);
						String must_know_dknow_nk = request.getParameter("must_know_dknow_nk"+j);
						String tl_mthd_med = request.getParameter("tl_mthd_med"+j);
						String formtive_assessmt = request.getParameter("formtive_assessmt"+j);
						String sumtive_assessmt = request.getParameter("sumtive_assessmt"+j);
						String integ_horivtspi = request.getParameter("integ_horivtspi"+j);
						String nch_term = request.getParameter("nch_term"+j);
						
				
						add.setGeneric_compte(generic_compte);
						add.setSubject_area(subject_area);
						add.setMillers_knows(Integer.parseInt(millers_knows));
						add.setSpecific_compet(specific_compet);
						add.setSpec_learn_object(spec_learn_object);
						add.setBlooms_domain(Integer.parseInt(blooms_domain));
						add.setGuilberts_level(Integer.parseInt(guilberts_level));
						add.setMust_know_dknow_nk(Integer.parseInt(must_know_dknow_nk));
						add.setTl_mthd_med(Integer.parseInt(tl_mthd_med));
						add.setFormtive_assessmt(Integer.parseInt(formtive_assessmt));
						add.setSumtive_assessmt(Integer.parseInt(sumtive_assessmt));
						add.setInteg_horivtspi(integ_horivtspi);
						add.setNch_term(Integer.parseInt(nch_term));
						
						//add.setP_id(parent_id);
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setId(Integer.parseInt(editid));
						session1.update(add);
						session1.flush();
						session1.clear();
					}
				}
				
				if ( old_hidden_att < new_count_hidden) {
					CC_TB_NCH_T4_LEARNING_OBJECT_CHILD xray = new CC_TB_NCH_T4_LEARNING_OBJECT_CHILD();
					
						for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
							
							String a3_couse_outcome = request.getParameter("a3_couse_outcome"+j);
							
							String generic_compte = request.getParameter("generic_compte"+j);
							String subject_area = request.getParameter("subject_area"+j);
							String millers_knows = request.getParameter("millers_knows"+j);
							//System.err.println("\"millers_knows\"+i---------->>   "  +"millers_knows"+j);
							String specific_compet = request.getParameter("specific_compet"+j);
							String spec_learn_object = request.getParameter("spec_learn_object"+j);
							String blooms_domain = request.getParameter("blooms_domain"+j);
							String guilberts_level = request.getParameter("guilberts_level"+j);
							String must_know_dknow_nk = request.getParameter("must_know_dknow_nk"+j);
							String tl_mthd_med = request.getParameter("tl_mthd_med"+j);
							String formtive_assessmt = request.getParameter("formtive_assessmt"+j);
							String sumtive_assessmt = request.getParameter("sumtive_assessmt"+j);
							String integ_horivtspi = request.getParameter("integ_horivtspi"+j);
							String nch_term = request.getParameter("nch_term"+j);
							
							xray.setGeneric_compte(generic_compte);
							xray.setSubject_area(subject_area);
							xray.setMillers_knows(Integer.parseInt(millers_knows));
							xray.setSpecific_compet(specific_compet);
							xray.setSpec_learn_object(spec_learn_object);
							xray.setBlooms_domain(Integer.parseInt(blooms_domain));
							xray.setGuilberts_level(Integer.parseInt(guilberts_level));
							xray.setMust_know_dknow_nk(Integer.parseInt(must_know_dknow_nk));
							xray.setTl_mthd_med(Integer.parseInt(tl_mthd_med));
							xray.setFormtive_assessmt(Integer.parseInt(formtive_assessmt));
							xray.setSumtive_assessmt(Integer.parseInt(sumtive_assessmt));
							xray.setInteg_horivtspi(integ_horivtspi);
							xray.setNch_term(Integer.parseInt(nch_term));
							
							//add.setP_id(parent_id);
							xray.setCreated_by(username);
							xray.setCreated_date(date);
							xray.setP_id(Integer.parseInt(id));
							session1.save(xray);
							session1.flush();
							session1.clear();
						}
				}
				
				if ( old_hidden_att > new_count_hidden) {
					
					for (int j = new_count_hidden + 1; j <= old_hidden_att; j++) {
						
						String editid = request.getParameter("eid"+j);
						
						CC_TB_NCH_T4_LEARNING_OBJECT_CHILD del = (CC_TB_NCH_T4_LEARNING_OBJECT_CHILD) session1
								.get(CC_TB_NCH_T4_LEARNING_OBJECT_CHILD.class, Integer.parseInt(editid));
						session1.delete(del);
						session1.flush();
						session1.clear();
					}
				}
				tx.commit();
				ra.addAttribute("msg", "Data Updated Successfully.");
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Search_Nch_T4_Learning_Object_Url");
	}
 
 
 @PostMapping(value = "/delete_Nch_T4_Learning_Object_Url")
	public ModelAndView delete_Nch_T4_Learning_Object_Url(@ModelAttribute("id2") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	

	 if(request.getHeader("Referer") == null ) { 
//		 session1.invalidate();
		 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		 return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session1.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("Nch_T4_Learning_Object_Url", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
	
	
		
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();
		
		try {
			int app = session.createQuery(
					"update CC_TB_NCH_T4_LEARNING_OBJECT_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
			
			int app1 = session.createQuery(
					"update CC_TB_NCH_T4_LEARNING_OBJECT_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
					.setParameter("p_id", Integer.parseInt(id)).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0 && app1 > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Nch_T4_Learning_Object_Url");
	}
 
	@RequestMapping(value = "/Nchgetlearn_outListby_Practical", method = RequestMethod.POST)
	public @ResponseBody List<EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT> Nchgetlearn_outListby_Practical(String practical_id) {

		List<EDU_CC_TB_NCH_PRACTICAL_LEARNING_OUTCOME_PARENT> list = common.getlearning_outcome_listbypractical(practical_id);
		System.out.println("list===="+list);
		return list;
		
	}
}

