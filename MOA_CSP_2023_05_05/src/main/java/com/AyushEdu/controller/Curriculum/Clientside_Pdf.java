package com.AyushEdu.controller.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.View_Que_Paper_Blue_PrintDao;
import com.AyushEdu.dao.Curriculum.View_Scheme_of_AssessmentDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clientside_Pdf {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	@Autowired
	CommonController common;

	@Autowired
	Professional_Ayu_Report_Dao PARDAO;

	@Autowired
	View_Que_Paper_Blue_PrintDao qpbao;

	@Autowired
	View_Scheme_of_AssessmentDao vsaDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/clientsidePDF", method = RequestMethod.POST)
	public ModelAndView clientsidePDF(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport", required = false) String typeReport,
			@RequestParam(value = "course_id1", required = false) String course_id, HttpServletRequest request) {
//		SECURITY -- RIDDHI 		
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("clientsidePDF", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		if (typeReport != null && !typeReport.equals("")) {

			List<Map<String, Object>> list = PARDAO.DataTableEdu_Reg_Report_masterDataList_pdf(course_id);

			List<Map<String, Object>> examination_list = PARDAO.examination_list(course_id);

			// end of summary and examination============================

			// random table=======================================

			List<ArrayList<String>> randomlist = PARDAO.non_lec_activities(course_id);

			int total = randomlist.size();

			// end of random table=========================================

			// Table 1- Course learning outcomes and matched
			// PO========================================

			ArrayList<ArrayList<String>> t1copolink_list = PARDAO.table1_co_po_link(course_id);

			// end of Table 1- Course learning outcomes and matched
			// PO==========================================================

			// Table 2: Contents of Course AyUG-RS=======================================

			List<ArrayList<String>> t2Content_Course_AyUGRS_list = PARDAO.t2Content_Course_AyUGRS_list(course_id);

			int total2 = t2Content_Course_AyUGRS_list.size();

			// end of Table 2: Contents of Course
			// AyUG-RS===========================================

			// Table 3: Learning objectives (Theory) of Course
			// AyUG-RS============================

			List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list = PARDAO
					.table3_Learning_Objectives_Course_AyUGRS(course_id);
			int total3 = t3LearningObject_Course_AyUGRS_list.size();

			// end of Table 3: Learning objectives (Theory) of Course
			// AyUG-RS===============================

//======================start Practical- list===========================

			List<ArrayList<String>> tableList_of_practical_list = PARDAO
					.TableList_of_practicalDataTotalCount(course_id);

			int totalLPA = tableList_of_practical_list.size();

			// ==================== end Practical- list===========================

			// Table 4: Learning objectives (Practical) of AyUG-
			// RS===========================

			List<ArrayList<String>> t4Learning_Objectiveslist = PARDAO
					.table4_Learning_Objectives_Practical_of_AyUGRS(course_id);

			int total4 = t4Learning_Objectiveslist.size();

			// end of Table 4: Learning objectives (Practical) of AyUG-
			// RS===========================

			// Table 5: Non-Lecture Activities Course AyUG-RS===============================

			List<ArrayList<String>> table5nlac_list = PARDAO.table5_Non_Lecture_Activities_Course_AyUGRS(course_id);


			List<ArrayList<String>> practhours = PARDAO.practhours(course_id);


			// end of Table 5: Non-Lecture Activities Course
			// AyUG-RS===============================

			// 6 A - Number of Papers and Marks Distribution============================

			List<ArrayList<String>> table6A_number_of_papers_list = PARDAO
					.Table6A_NumberofPapersDataTotalCount(course_id);

			int total6 = table6A_number_of_papers_list.size();

			// end of 6 A - Number of Papers and Marks
			// Distribution============================

			// 6-B=====================

			ArrayList<ArrayList<String>> table6b_term1list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "I");
			ArrayList<ArrayList<String>> table6b_term2list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "II");
			ArrayList<ArrayList<String>> table6b_term3list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "III");

			// 6-B======================

			// 6 D - Evaluation Methods for Periodical
			// Assessment============================

			List<ArrayList<String>> Table6Dnlac_list = PARDAO
					.Table6D_Evaluation_Methods_For_Periodical_Assessment(course_id);


			// end 6 D - Evaluation Methods for Periodical
			// Assessment===============================

			// Table 6E: Paper Layout===============================

			ArrayList<ArrayList<String>> table_6E1 = PARDAO.table_6E_paper_layout(course_id);
			ArrayList<ArrayList<String>> table_6E2 = PARDAO.table_6E_paper_layout2(course_id);


			// end of Table 6E: Paper
			// Layout==========================================================

			// 6FI-PaperI=========================================

			ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO
					.Table6F_IDistribution_of_Theory_Exam_List_DAO(course_id);

			// end 6FI-PaperI===========================

			// 6FI- PaperII=========================================

			ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO
					.Table6F_II_Distribution_of_Theory_Exam_List_DAO(course_id);

			// end 6FI- PaperII===============================

			// 6 G Question paper Blue print for AyU-RS====================================

			List<ArrayList<String>> table6g1_list = qpbao.getpaperformatdata(course_id, "5,6,7", "mcq", "PAPER I");
			List<ArrayList<String>> table6g2_list = qpbao.getpaperformatdata(course_id, "5,6", "saq", "PAPER I");
			List<ArrayList<String>> table6g3_list = qpbao.getpaperformatdata(course_id, "5", "laq", "PAPER I");
			List<ArrayList<String>> table6g4_list = qpbao.getpaperformatdata(course_id, "5,6,7", "mcq", "PAPER II");
			List<ArrayList<String>> table6g5_list = qpbao.getpaperformatdata(course_id, "5,6", "saq", "PAPER II");
			List<ArrayList<String>> table6g6_list = qpbao.getpaperformatdata(course_id, "5", "laq", "PAPER II");


			// end of 6 G Question paper Blue print for
			// AyU-RS================================

			// Table 6H - I - Distribution of Practical Exam ===============================

			List<ArrayList<String>> table_6H1 = PARDAO.table_6H_I_Distribution_of_Practical_Exam(course_id);


			// end of Table 6H - I - Distribution of Practical
			// Exam==========================================================

			// 7. Reference and Resourses========================================

			ArrayList<ArrayList<String>> reference_resourses_list = PARDAO.tablereference_resourses(course_id);
			int totalrrlist = reference_resourses_list.size();

			// End 7. Reference and Resourses======================================
			// end
		}
		return new ModelAndView("CC_Professional_Ayu_Report_Tiles");
	}
	
	
	
	
	@RequestMapping(value = "/getT1data", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getT1data(String course_id) {
		return PARDAO.table1_co_po_link(course_id);
	}
	

}
