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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.NCH_Curriculum_Pdf_Dao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.View_Que_Paper_Blue_PrintDao;
import com.AyushEdu.dao.Curriculum.View_Scheme_of_AssessmentDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class NCH_Curriculum_Pdf {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	@Autowired
	CommonController common;

	@Autowired
	NCH_Curriculum_Pdf_Dao PARDAO1;

	@Autowired
	View_Que_Paper_Blue_PrintDao qpbao;

	@Autowired
	View_Scheme_of_AssessmentDao vsaDAO;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/NCH_Curriculum_Pdf_Url", method = RequestMethod.GET)
	public ModelAndView NCH_Curriculum_Pdf_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {
			String role = session.getAttribute("role").toString();
			String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			Mmap.put("role", role);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, roleStaff_lvl));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			if (role.toLowerCase().contains("student")) {
				Mmap.put("system", PARDAO1.GetSystemdegreeid_fetch(userid));
			}
			if (role.toLowerCase().contains("institute")) {
				Mmap.put("system_name", PARDAO1.GetSystem_fetch_NCH(userid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("NCH_Curriculum_Pdf_Tiles");

	}

	@RequestMapping(value = "admin/NCH_Curriculum_Report_Url_pdf", method = RequestMethod.POST)
	public ModelAndView NCH_Curriculum_Report_Url_pdf(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport", required = false) String typeReport,
			@RequestParam(value = "course_id1", required = false) String course_id, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<Map<String, Object>> nonlecact1 = PARDAO1.getstudentdetails_Report_Excel(course_id);
		List<String> TH1 = new ArrayList<String>();

		String role = session.getAttribute("role").toString();
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nSCHEDULE OF CREDIT";

// Strt Program Outcomes========================================

		ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);
		int totalProgram_Outcomeslist = Program_Outcomes_list.size();

// End Program Outcomes======================================

//  Strt COURSE OUTCOMES========================================

		ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);
		int totalCourse_Outcomeslist = Course_Outcomes_list.size();

// End  COURSE OUTCOMES======================================

//  Strt TEACHING LEARNING METHODS========================================

		ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
		int totalTeaching_Methodlist = Teaching_Method_list.size();

// End TEACHING LEARNING METHODS======================================

/////////////////Teaching hours//////////////////

		List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours(course_id);
		int total1 = Teaching_hour.size();
		List<String> TH = new ArrayList<String>();
		TH.add("Sr No.");
		TH.add("Subject");
		TH.add("Theoretical Lecture");
		TH.add("Practical / Tutorial / Seminar / Clinical Posting");

/////////////////Teaching hous//////////////////

		// Table : Contents of Course ======================================

		List<ArrayList<String>> t2Content_Course_list = PARDAO1.List_of_Topic_list(course_id);

		int total21 = t2Content_Course_list.size();
		List<String> TH4 = new ArrayList<String>();
		TH4.add("Sr.No.");
		TH4.add("Topics");
		TH4.add("Hrs");
		TH4.add("Term");

//end of Table : Contents of Course// ==========================================		

		// Start Learning objectives (Theory) of Course

		List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
				.table2_Learning_Objectives_of_Course_HomUG(course_id);
		int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
		List<String> TH2loc = new ArrayList<String>();
		System.err.println("table2_Learning_Objectives_of_Course_HomUG_list=================="
				+ table2_Learning_Objectives_of_Course_HomUG_list);

		TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

		TH2loc.add("Subject\r\n" + "Area");

		TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
				+ "Knows");

		TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

		TH2loc.add("SLO/\r\n" + "Outcome");
		TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
		TH2loc.add("Guilbert’s\r\n" + "Level");
		TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
		TH2loc.add("T-L\n" + "Methods\n");
		TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
				+ "Vertical/\r\n" + "Spiral");

		// end of Learning objectives (Theory) of Course

		// Table 3: Contents of Course Pratical======================================

		List<ArrayList<String>> t2Content_Course_list1 = PARDAO1.List_of_Pratical_list(course_id);

		int total3 = t2Content_Course_list1.size();
		List<String> TH5 = new ArrayList<String>();
		TH5.add("Sr.No.");
		TH5.add("Topics");
		TH5.add("Hrs");
		TH5.add("Term1");

		// end of Table : Contents of Course Pratical//

		// Start Practical learning objectives

		List<ArrayList<String>> table4_Practical_Learning_Objectives_list = PARDAO1
				.table4_Practical_Learning_Objectives_list(course_id);
		int total4loc = table4_Practical_Learning_Objectives_list.size();
		List<String> TH4loc = new ArrayList<String>();

		TH4loc.add("Generic\r\n" + "Compet\r\n" + "ency");

		TH4loc.add("Subject\r\n" + "Area");

		TH4loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
				+ "Knows");

		TH4loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

		TH4loc.add("SLO/\r\n" + "Outcome");
		TH4loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
		TH4loc.add("Guilbert’s\r\n" + "Level");
		TH4loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
		TH4loc.add("T-L\n" + "Methods\n");
		TH4loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
		TH4loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
		TH4loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
				+ "Vertical/\r\n" + "Spiral");

		// end of Learning objectives (Theory) of Course

// Strt Non Lecture Teaching Learning methods============================

		List<ArrayList<String>> TableNon_Lecture_list = PARDAO1.Table_Non_Lecture(course_id);
		int total2 = TableNon_Lecture_list.size();

		List<String> TH3 = new ArrayList<String>();

		TH3.add("Sr. No");
		TH3.add(" Non Lecture Teaching Learning methods");
		TH3.add(" Time Allotted per Activity \r\n" + "(Hours)");

// end Non Lecture Teaching Learning methods ===============================

// Strt Evaluation Methods for Periodical============================

		List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
				.Table_Evaluation_Methods_For_Periodical_Assessment(course_id);

		List<String> TH2 = new ArrayList<String>();

		TH2.add("Sr. No.\n");
		TH2.add(" Evaluation Methods for Periodical Assessment");

// end Evaluation Methods for Periodical ===============================

//  Number of Papers and Marks Distribution============================

		List<ArrayList<String>> table6_number_of_papers_list = PARDAO1
				.Number_of_papers_and_Mark_Distribution(course_id);

		int total6 = table6_number_of_papers_list.size();
		List<String> TH6 = new ArrayList<String>();

		TH6.add("Sr.No.");// 0
		TH6.add("Course  Code");// 1
		TH6.add("Papers");// 2
		TH6.add("Theory");// 3
		TH6.add("Practical");// 4
		TH6.add("Viva Voce");// 5
		TH6.add("Internal Assessment- Practical");// 6
		TH6.add("Electives Grade Obtained");// 7
		TH6.add("Sub Total");// 8
		TH6.add("Grand Total");// 9

/////////////////////////////////////////////////////////////- Number of Papers and Marks			

		// 6-B===================== Start Scheme of Assessment (formative and Summative)===============================================================

		ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "I");
		ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "II");
		ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "III");

//6-B===================== end  Scheme of Assessment (formative and Summative)===============================================================

		//// Paper Layout ////////////////////////==============================

		ArrayList<ArrayList<String>> Paper_Layout = PARDAO1.Paper_Layout_List(course_id);

		//// End Paper Layout ////////////////////////==============================

		// 6FI-PaperI=========================================

		ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
				.Table6F_IDistribution_of_Theory_Exam_List_DAO(course_id);

		// end 6FI-PaperI===========================

		// 6FI- PaperII=========================================

		ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
				.Table6F_IDistribution_of_Theory_Exam_List_DAO(course_id);

		// end 6FI- PaperII===============================

		// Table 6H - I - Distribution of Practical Exam ===============================

		List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

// Strt Reference and Resourses========================================

		ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
		int totalreference_resourseslist = Reference_Resourses_list.size();

// End Reference and Resourses======================================

		String Heading1 = "\nSCHEDULE OF CREDIT";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new NCH_Download_Curriculum_Pdf("L", TH, TH1, TH2, TH3, TH4, TH5, TH6, TH2loc, TH4loc,
				username, username, nonlecact1, Program_Outcomes_list, Course_Outcomes_list, Teaching_hour,
				Teaching_Method_list, TableNon_Lecture_list, TableEvaluation_Methods_list, t2Content_Course_list,
				t2Content_Course_list1, table6_number_of_papers_list, table2_Learning_Objectives_of_Course_HomUG_list,
				table4_Practical_Learning_Objectives_list, Reference_Resourses_list, table6b_term1list,
				table6b_term2list, table6b_term3list, Paper_Layout, Table6F_IDistribution_of_Theory_Exam_List,
				Table6F_IIDistribution_of_Theory_Exam_List, Distri_Pract_Exam), "userList", Teaching_hour);

	}

//	Kavita - ANATOMY*********************************************************

	@RequestMapping(value = "admin/AnatomyPdfDownload", method = RequestMethod.POST)
	public ModelAndView AnatomyPdfDownload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport2", required = false) String typeReport,
			@RequestParam(value = "course_id2", required = false) String course_id, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_Report_Excel(course_id);

		ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);

		ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

		List<String> TH = new ArrayList<String>();
		TH.add("Sr. No.");
		TH.add("Subject");
		TH.add("Theoretical Lecture");
		TH.add("Practical / Tutorial / Seminar / Clinical Posting");

		List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours(course_id);

		List<String> TH2 = new ArrayList<String>();
		TH2.add("Sr. No.");
		TH2.add("A\nList of Topics");
		TH2.add("B\nTerm");
		TH2.add("c\nTeaching Hours");

		List<ArrayList<String>> Teaching_hours_theory = PARDAO1.AnatomyTopicTheory(course_id);

		List<ArrayList<String>> Teaching_hours_practical = PARDAO1.AnatomyTopicPractical(course_id);

		List<String> TH3 = new ArrayList<String>();
		TH3.add("Sr.No.");
		TH3.add("Topics");
		TH3.add("Hrs");
		TH3.add("Term");

		List<ArrayList<String>> topic_subtopic = PARDAO1.List_of_Topic_list(course_id);
// Strt TEACHING LEARNING METHODS========================================

		ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
		int totalTeaching_Methodlist = Teaching_Method_list.size();

//End TEACHING LEARNING METHODS======================================

// Table 3: Contents of Course Pratical======================================

		List<ArrayList<String>> t2Content_Course_list1 = PARDAO1.List_of_Pratical_list(course_id);

		int total3 = t2Content_Course_list1.size();
		List<String> TH5 = new ArrayList<String>();
		TH5.add("Sr.No");
		TH5.add("Topics");
		TH5.add("Hrs");
		TH5.add("Term");

//end of Table : Contents of Course Pratical// ==========================================

// Start Learning objectives (Theory) of Course

		List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
				.table2_Learning_Objectives_of_Course_HomUG(course_id);
		int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
		List<String> TH2loc = new ArrayList<String>();

		TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");
		TH2loc.add("Subject\r\n" + "Area");
		TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
				+ "Knows");
		TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");
		TH2loc.add("SLO/\r\n" + "Outcome");
		TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
		TH2loc.add("Guilbert’s\r\n" + "Level");
		TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
		TH2loc.add("T-L\n" + "Methods\n");
		TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
				+ "Vertical/\r\n" + "Spiral");

// end of Learning objectives (Theory) of Course

// Start Practical learning objectives===============================================

		List<ArrayList<String>> table4_Practical_Learning_Objectives_list = PARDAO1
				.table4_Practical_Learning_Objectives_list(course_id);
		int total4loc = table4_Practical_Learning_Objectives_list.size();
		List<String> TH4loc = new ArrayList<String>();

		TH4loc.add("Generic\r\n" + "Compet\r\n" + "ency");

		TH4loc.add("Subject\r\n" + "Area");

		TH4loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
				+ "Knows");

		TH4loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

		TH4loc.add("SLO/\r\n" + "Outcome");
		TH4loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
		TH4loc.add("Guilbert’s\r\n" + "Level");
		TH4loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
		TH4loc.add("T-L\n" + "Methods\n");
		TH4loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
		TH4loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
		TH4loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
				+ "Vertical/\r\n" + "Spiral");

// end of Learning objectives (Theory) of  Course=============================================

// Strt Non Lecture Teaching Learning methods============================

		List<ArrayList<String>> TableNon_Lecture_list = PARDAO1.Table_Non_Lecture(course_id);
		int total2 = TableNon_Lecture_list.size();

		List<String> TH4 = new ArrayList<String>();

		TH4.add("Sr.No");
		TH4.add(" Non Lecture Teaching Learning methods");
		TH4.add(" Time Allotted per Activity \r\n" + "(Hours)");

		// end Non Lecture Teaching Learning methods ===============================

		/// Number of Papers and Marks Distribution============================

		List<ArrayList<String>> table6_number_of_papers_list = PARDAO1
				.Number_of_papers_and_Mark_Distribution(course_id);

		int total6 = table6_number_of_papers_list.size();
		List<String> TH6 = new ArrayList<String>();

		TH6.add("Sr.No.");// 0
		TH6.add("Course  Code");// 1
		TH6.add("Papers");// 2
		TH6.add("Theory");// 3
		TH6.add("Practical");// 4
		TH6.add("Viva Voce");// 5
		TH6.add("Internal Assessment- Practical");// 6
		TH6.add("Electives Grade Obtained");// 7
		TH6.add("Sub Total");// 8
		TH6.add("Grand Total");// 9

/////////////////// - END 8(A)Number of Papers and
		
// 6-B===================== Start Scheme of Assessment (formative and Summative)===============================================================

		ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "I");
		ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "II");
		ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "III");

// 6-B===================== end Scheme of Assessment (formative and Summative)===============================================================
		
// Strt Evaluation Methods for Periodical============================

		List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
				.Table_Evaluation_Methods_For_Periodical_Assessment(course_id);

		List<String> TH7 = new ArrayList<String>();

		TH7.add("Sr.No\n");
		TH7.add(" Evaluation Criteria");

// end Evaluation Methods for Periodical ===============================

//// Paper Layout ////////////////////////==============================

		List<ArrayList<String>> Paper_Layout = PARDAO1 .Paper_Layout_List_ana(course_id);

		int total = Paper_Layout.size();
        List<String> tHQ62 = new ArrayList<String>();
		
        tHQ62.add("Question Types\n");
        tHQ62.add(" Marks");
        tHQ62.add(" Time");
		
		 // Question paper Blue print for ====================================

		List<ArrayList<String>> table61_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "1");
		List<ArrayList<String>> table62_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "1");
		List<ArrayList<String>> table63_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "1");
		List<ArrayList<String>> table64_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "2");
		List<ArrayList<String>> table65_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "2");
		List<ArrayList<String>> table66_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "2");

		List<String> THQ6 = new ArrayList<String>();

		THQ6.add("A\n" // 0
				+ "Question\n" + "Sr. No \n");
		THQ6.add("B\n" // 1
				+ "Type of Question \n");
		THQ6.add("C\n" // 2
				+ "Question Paper Format\n");
		THQ6.add("Q1 \n"); // 3
		THQ6.add("Multiple choice Questions\n" // 4
				+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
				+ "Must know part: 7 MCQ\n" + "Desirable to know: 2 MCQ\n" + "Nice to know: 1 MCQ \n");
		THQ6.add("Q2 \n"); // 5
		THQ6.add(
				"Short answer Questions\n" + "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
						+ "Must know part: 10 SAQ\n" + "Desirable to know: Nil SAQ\n" + "Nice to know: Nil");//6
		THQ6.add("Q3 \n"); // 7
		THQ6.add("Long answer Questions\n" // 8
				+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
				+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");
		THQ6.add("Q1 \n"); // 9
		THQ6.add("Multiple choice Questions\n" // 10
				+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
				+ "Must know part: 7 MCQ\n" + "Desirable to know: 2 MCQ.\n" + "Nice to know: 1 MCQ");
		THQ6.add("Q2 \n"); // 11
		THQ6.add("Short answer Questions\n" // 12
				+ "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
				+ "Must know part: 7 SAQ\n" + "Desirable to know: 3 SAQ\n" + "Nice to know: 1 SAQ");
		THQ6.add("Q3 \n"); // 13
		THQ6.add("Long answer Questions\n" // 14
				+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
				+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

		THQ6.add("A\n" // 15
				+ "Question\n" + "Sr. No \n");
		THQ6.add("B\n" // 16
				+ "Type of Question \n");
		THQ6.add("C\n" // 17
				+ "Question Paper Format\n");

// end of Question paper Blue print for================================

// 6FI-PaperI=========================================

				ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
						.Table6F_IDistribution_of_Theory_Exam_List_DAO(course_id);

// end 6FI-PaperI===========================
				
// 6FI- PaperII=========================================

				ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
						.Table6F_II_Distribution_of_Theory_Exam_List_DAO(course_id);

// end 6FI- PaperII===============================
				
// 6FI- PaperI=======Theme==================================

				ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Theme = (ArrayList<ArrayList<String>>) PARDAO1
						.Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAO(course_id);

// end 6FI- PaperI==========Theme=====================
				
// 6FI- PaperII=======Theme==================================

				ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_II_Theme = (ArrayList<ArrayList<String>>) PARDAO1
						.Table6F_II_Distribution_of_Theory_Exam_List__ThemeDAO(course_id);

// end 6FI- PaperII==========Theme=====================
		

// Table 6H - I - Distribution of Practical Exam ===============================
				
		List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

//// Table 6H - I - Distribution of Practical Exam =END==============================;

		// Start 8-d===================== Start Scheme of Assessment (formative )======================

		ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
		ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
		ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

		// End 8-D===================== End Scheme of Assessment (formative)=================
		
		
// Strt Reference and Resourses========================================

		ArrayList<ArrayList<String>> Reference_Resourses_listA = PARDAO1.tableReference_Resourses(course_id);
		int totalreference_resourseslist = Reference_Resourses_listA.size();

// End Reference and Resourses======================================
		
		List<Map<String, Object>> nonlecact1 = PARDAO1.getstudentdetails_Report_Excel(course_id);
		return new ModelAndView(new AnatomyPdfDownloadClass(sysdegprofcorsnamecode, Program_Outcomes_list,
				Course_Outcomes_list, TH, Teaching_hour, TH2, Teaching_hours_theory, Teaching_hours_practical, TH3,
				topic_subtopic, Teaching_Method_list, t2Content_Course_list1, TH5,
				table2_Learning_Objectives_of_Course_HomUG_list, TH2loc, table4_Practical_Learning_Objectives_list,
				TH4loc, TableNon_Lecture_list, TH4, table6_number_of_papers_list, TH6, table6b_term1list,
				table6b_term2list, table6b_term3list, TableEvaluation_Methods_list, TH7, Paper_Layout,tHQ62, table61_list,table62_list,table63_list,
				table64_list,table65_list,table66_list,THQ6 , Table6F_IDistribution_of_Theory_Exam_List, Table6F_IIDistribution_of_Theory_Exam_List,
				Table6F_IDistribution_of_Theory_Exam_List_Theme,Table6F_IDistribution_of_Theory_Exam_List_II_Theme,
				Distri_Pract_Exam,table6d_term1list,table6d_term2list,table6d_term3list, Reference_Resourses_listA, nonlecact1));
	}

//	Shivali-Organon_of_Medicine_Homoeopathicphilosophy_Fundamentals_of_PsychologyPdfDownload_Controller**********************************************

	@RequestMapping(value = "admin/Organon_of_Medicine_Homoeopathicphilosophy_Fundamentals_of_PsychologyPdfDownload_url", method = RequestMethod.POST)
	public ModelAndView Organon_of_Medicine_Homoeopathicphilosophy_Fundamentals_of_PsychologyPdfDownload_url(
			ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport3", required = false) String typeReport,
			@RequestParam(value = "course_id3", required = false) String course_id, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<Map<String, Object>> nonlecact1 = PARDAO1.getstudentdetails_Report_Excel(course_id);
		List<String> TH1 = new ArrayList<String>();

		String role = session.getAttribute("role").toString();
		String username1 = session.getAttribute("username").toString();
		String Heading = "\nSCHEDULE OF CREDIT";

      // Strt Program Outcomes========================================

		ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);
		int totalProgram_Outcomeslist = Program_Outcomes_list.size();

      // End Program Outcomes======================================

      //  Strt COURSE OUTCOMES========================================

		ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);
		int totalCourse_Outcomeslist = Course_Outcomes_list.size();

      // End  COURSE OUTCOMES======================================

      //Strt  Specific_Objective_list ===========================		
		
		ArrayList<ArrayList<String>> Specific_Objective_list = PARDAO1.tblSpecific_Objective_list(course_id);
		
       // End Specific_Objective_list	================	
		
       //  Strt TEACHING LEARNING METHODS========================================

		ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
		int totalTeaching_Methodlist = Teaching_Method_list.size();

        // End TEACHING LEARNING METHODS======================================

       /////////////////Teaching hours//////////////////

		List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours_Homoeopathic(course_id);
		int total1 = Teaching_hour.size();
		List<String> TH = new ArrayList<String>();
		TH.add("Year");
		TH.add("Teaching hours- Lectures");
		TH.add("Teaching hours- Non-lectures");
		

        /////////////////Teaching hous//////////////////

		// Table : Contents of Course ======THEORY================================

		List<ArrayList<String>> t2Content_Course_list = PARDAO1.List_of_Topic_list_pdf_Organon(course_id);

		int total21 = t2Content_Course_list.size();
		List<String> TH4 = new ArrayList<String>();
		TH4.add("Sr.No.");
		TH4.add("Topics");
		TH4.add("Term");
		TH4.add("Lecture-Hrs");
		TH4.add("Non-Lectures-Hrs");
		// Table : Contents of Course ======THEORY=======END========================

        //end of Table : Contents of Course// ==========================================		

		// Start Table 2-Learning Objectives (Theory) of Course HomUG-OM-I==========================

				List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
						.table2_Learning_Objectives_of_Course_HomUG_Medica(course_id);
				int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
				List<String> TH2loc = new ArrayList<String>();

				TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

				TH2loc.add("Subject\r\n" + "Area");

				TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
						+ "Knows");

				TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

				TH2loc.add("SLO/\r\n" + "Outcome");
				TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
				TH2loc.add("Guilbert’s\r\n" + "Level");
				TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
				TH2loc.add("T-L\n" + "Methods\n");
				TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
				TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
				TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
						+ "Vertical/\r\n" + "Spiral");

               // Strt Non Lecture Teaching Learning methods============================

		List<ArrayList<String>> TableNon_Lecture_list = PARDAO1.Table_Non_Lecture(course_id);
		int total2 = TableNon_Lecture_list.size();

		List<String> TH3 = new ArrayList<String>();

		TH3.add("Sr. No");
		TH3.add(" Non Lecture Teaching Learning methods");
		TH3.add(" Time Allotted per Activity \r\n" + "(Hours)");
		
         // end Non Lecture Teaching Learning methods ==============================
		
         // Start 8(A)Number of Papers and Marks Distribution============================

				List<ArrayList<String>> table6_number_of_papers_listP = PARDAO1
						.Number_of_papers_and_Mark_DistributionP(course_id);

				int total6 = table6_number_of_papers_listP.size();
				List<String> TH6 = new ArrayList<String>();

				TH6.add("Sr.No.");// 0
				TH6.add("Course  Code");// 1
				TH6.add("Papers");// 2
				TH6.add("Theory");// 3
				TH6.add("Practical");// 4
				TH6.add("Viva Voce");// 5
				TH6.add("Internal Assessment- Practical");// 6
				TH6.add("Grand Total");// 7

				/////////////////// - END 8(A)Number of Papers and

				// Start 8-d===================== Start Scheme of Assessment (formative and  Summative)======================

				ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "I");
				ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "II");
				ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "III");

				// End 8-D===================== End Scheme of Assessment (formative and  Summative)=================
				

				// Start Evaluation Methods for Periodical============================

				List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
						.Table_Evaluation_Methods_For_Periodical_AssessmentED(course_id);

				List<String> TH8 = new ArrayList<String>();

				TH8.add("Sr. No\n");
				TH8.add(" Evaluation Dimensions");

				// End Evaluation Methods for Periodical ===============================

				// Start 8-d===================== Start Scheme of Assessment (formative )======================

				ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
				ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
				ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

				// End 8-D===================== End Scheme of Assessment (formative)=================
				
		//// Start Paper Layout ////////////////////////==============================

		ArrayList<ArrayList<String>> Paper_LayoutP = PARDAO1.Paper_Layout_ListOrganon(course_id);
		List<String> TH10 = new ArrayList<String>();
		
		TH10.add("Question Types\n");
		TH10.add(" Marks");
		TH10.add(" Time");
		//// End Paper Layout ////////////////////////==============================
		
		// Start 6FI-PaperI=========================================


		ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
				.Table6F_IDistribution_of_Theory_Exam_List_Organon(course_id);
		
       // End 6FI-PaperI=========================================
		
		// 6FI- PaperII=========================================

					ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
							.Table6F_IIDistribution_of_Theory_Exam_List_Organon(course_id);

			// end 6FI- PaperII===============================
		
		 // Question paper Blue print for ====================================

			List<ArrayList<String>> table61_list = PARDAO1.getpaperformatdata_Homeo1(course_id, "16,17,18", "mcq", "PAPER I");
			List<ArrayList<String>> table62_list = PARDAO1.getpaperformatdata_Homeo1(course_id, "16,17", "saq", "PAPER I");
			List<ArrayList<String>> table63_list = PARDAO1.getpaperformatdata_Homeo1(course_id, "16", "laq", "PAPER I");

			List<String> THQ6 = new ArrayList<String>();

			THQ6.add("A\n" // 0
					+ "Question Serial Number\n" + "Sr. No \n");
			THQ6.add("B\n" // 1
					+ "Type of Question\n");
			THQ6.add("C\n" // 2
					+ "Question Paper Format");
			THQ6.add("Q1 \n"); // 3
			THQ6.add("Multiple choice Questions\n" // 4
					+ "(MCQ)\n\n" + "5 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
					+ "Must know part: 3 MCQ\n" + "Desirable to know: 2 MCQ\n" + "Nice to know: 1 MCQ \n");
			THQ6.add("Q2 \n"); // 5
			THQ6.add(
					"Short answer Questions\n" + "(SAQ)\n" + "5 Questions\n" + "5 Marks Each\n" + "All compulsory\n"
							+ "Must know part:5 SAQ\n" + "Desirable to know: Nil\n" + "Nice to know: Nil");
			THQ6.add("Q3 \n"); // 6
			THQ6.add("Long answer Questions\n" // 7
					+ "(LAQ)\n" + "Two Questions\n" + "10 marks each\n" + "All compulsory\n"
					+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");


			// end of Question paper Blue print for================================
		
		// Start 8 G -Distribution of Practical Exam ===============================

				List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

				// End of 8 G -Distribution of Practical

				// Start Reference and Resourses========================================

				ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
				int totalreference_resourseslist = Reference_Resourses_list.size();

				// End Reference and Resourses======================================

		String Heading1 = "\nSCHEDULE OF CREDIT";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new Org_of_Med_Homphil_Fund_of_PsycPdfDownload_Controller("L", TH, TH1, TH3, TH4,
				TH2loc, username, username, nonlecact1, Program_Outcomes_list, Course_Outcomes_list,Specific_Objective_list,Teaching_hour, 
				Teaching_Method_list, TableNon_Lecture_list, t2Content_Course_list,
				table2_Learning_Objectives_of_Course_HomUG_list,TH6, table6_number_of_papers_listP,
				table6b_term1list, table6b_term2list, table6b_term3list,TH8,TableEvaluation_Methods_list,Paper_LayoutP,TH10,THQ6, Table6F_IDistribution_of_Theory_Exam_List,
				Table6F_IIDistribution_of_Theory_Exam_List,table61_list,table62_list,table63_list,THQ6,Distri_Pract_Exam,table6d_term1list,table6d_term2list,table6d_term3list,Reference_Resourses_list), "userList", Teaching_hour);
	}
	
//End	Shivali-Organon_of_Medicine_Homoeopathicphilosophy_Fundamentals_of_PsychologyPdfDownload_Controller

// 	PARTH-PSYCHOLOGY *****************************************************************************

	@RequestMapping(value = "admin/NCHPsychologyPdfDownload", method = RequestMethod.POST)
	public ModelAndView NCHPsychologyPdfDownload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport4", required = false) String typeReport,
			@RequestParam(value = "course_id4", required = false) String course_id, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_Report_Excel(course_id);

		ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

		// Start Table 2-Learning Objectives (Theory) of Course

		List<ArrayList<String>> table2_Learning_Objectives_of_Psychology = PARDAO1
				.table2_Learning_Objectives_of_Course_Hom_psychology(course_id);
		int total2loc = table2_Learning_Objectives_of_Psychology.size();
		List<String> TH2loc = new ArrayList<String>();
		System.err.println("table2_Learning_Objectives_of_Psychology=================="
				+ table2_Learning_Objectives_of_Psychology);

		TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

		TH2loc.add("Subject\r\n" + "Area");

		TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
				+ "Knows");

		TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

		TH2loc.add("SLO/\r\n" + "Outcome");
		TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
		TH2loc.add("Guilbert’s\r\n" + "Level");
		TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
		TH2loc.add("T-L\n" + "Methods\n");
		TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
		TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
				+ "Vertical/\r\n" + "Spiral");

		// End of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I

		// Start TEACHING LEARNING METHODS========================================

		ArrayList<ArrayList<String>> Teaching_Method_list1 = PARDAO1.tblTeaching_Method_list(course_id);
		int totalTeaching_Methodlist = Teaching_Method_list1.size();

		// End TEACHING LEARNING METHODS======================================

		///////////////// Start Teaching hours//////////////////

//		List_of_Topic_list
		List<ArrayList<String>> List_of_Topic_listP = PARDAO1.List_of_Topic_listP(course_id);
		int total1 = List_of_Topic_listP.size();
		List<String> TH = new ArrayList<String>();
		TH.add("Sr No.");
		TH.add("Topic");
		TH.add("No of lectures");
		TH.add("Non-lectures");

		///////////////// End Teaching hous//////////////////

		// Start 8(A)Number of Papers and Marks Distribution============================

		List<ArrayList<String>> table6_number_of_papers_listP = PARDAO1
				.Number_of_papers_and_Mark_DistributionP(course_id);

		int total6 = table6_number_of_papers_listP.size();
		List<String> TH6 = new ArrayList<String>();

		TH6.add("Sr.No.");// 0
		TH6.add("Course  Code");// 1
		TH6.add("Papers");// 2
		TH6.add("Theory");// 3
		TH6.add("Practical");// 4
		TH6.add("Viva Voce");// 5
		TH6.add("Internal Assessment- Practical");// 6
		TH6.add("Grand Total");// 7

		/////////////////// - END 8(A)Number of Papers and  Marks/////////////////////////////

		// Start 8-B===================== Start Scheme of Assessment (formative and
		// Summative)======================

		ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "I");
		ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "II");
		ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse(course_id, "III");

		// End 8-B===================== End Scheme of Assessment (formative and  Summative)=================

		// Start Evaluation Methods for Periodical============================

		List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
				.Table_Evaluation_Methods_For_Periodical_Assessment(course_id);

		List<String> TH2 = new ArrayList<String>();

		TH2.add("Sr. No.\n");
		TH2.add(" Evaluation Methods for Periodical Assessment");

		// End Evaluation Methods for Periodical ===============================
		
		// Start 8-d===================== Start Scheme of Assessment (formative )======================

		ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
		ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
		ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

		// End 8-D===================== End Scheme of Assessment (formative)=================

	//// Start Paper Layout ////////////////////////==============================

			ArrayList<ArrayList<String>> Paper_LayoutP = PARDAO1.Paper_Layout_ListP(course_id);
			
			List<String> THQ50 = new ArrayList<String>();
			
			THQ50.add("Question Types\n");
			THQ50.add(" Marks");
			THQ50.add(" Time");

			//// End Paper Layout ////////////////////////==============================


		// Start 6FI- PaperII=========================================

		ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
				.Table6F_IIDistribution_of_Theory_Exam_List_Psycology(course_id);

		// End 6FI- PaperII===============================
         
///////////////////////// START OF BLUE PRINT /////////////////////////////////
		
		List<ArrayList<String>> table61_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "1");
		List<ArrayList<String>> table62_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "1");
		List<ArrayList<String>> table63_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "1");

		List<String> THQ60 = new ArrayList<String>();

		THQ60.add("\n" // 0
				+ "Question\n" + "Sr. No \n");
		THQ60.add("\n" // 1
				+ "Type of Question \n");
		THQ60.add("\n" // 2
				+ "Question Paper Format\n" );
		THQ60.add("Q1 \n"); // 3
		THQ60.add("Multiple choice Questions\n" // 4
				+ "(MCQ)\n\n" + "5 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
				+ "Must know part: 3 MCQ\n" + "Desirable to know: 1 MCQ\n" + "Nice to know: 1 MCQ \n");
		THQ60.add("Q2 \n"); // 5
		THQ60.add(
				"Short answer Questions\n" + "(SAQ)\n" + "5 Questions\n" + "5 Marks Each\n" + "All compulsory\n"
						+ "Must know part: 3 SAQ\n" + "Desirable to know: 1 SAQ\n" + "Nice to know:1");
		THQ60.add("Q3 \n"); // 6
		THQ60.add("Long answer Questions\n" // 7
				+ "(LAQ)\n" + "2 Questions\n" + "10 marks each\n" + "All compulsory\n"
				+ "Must know part: 2 LAQ");
		
///////////////////////// END OF BLUE PRINT /////////////////////////////////
		
		// Start 8 G -Distribution of Practical Exam ===============================

		List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

		// End of 8 G -Distribution of Practical

		// Start Reference and Resourses========================================

		ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
		int totalreference_resourseslist = Reference_Resourses_list.size();

		// End Reference and Resourses======================================

		return new ModelAndView(new NCHPsychologyPdfDownloadClass(sysdegprofcorsnamecode, Course_Outcomes_list, TH2loc,
				table2_Learning_Objectives_of_Psychology, Teaching_Method_list1, TH, List_of_Topic_listP, TH6,
				table6_number_of_papers_listP, table6b_term1list, table6b_term2list, table6b_term3list, TH2,
				TableEvaluation_Methods_list,table6d_term1list,table6d_term2list,table6d_term3list, Paper_LayoutP, THQ50,
				Table6F_IIDistribution_of_Theory_Exam_List,table61_list,table62_list,table63_list,Distri_Pract_Exam, THQ60,Reference_Resourses_list) );
	}

//	START RIDDHI-YOGA FOR HEALTH PROMOTION*************************************************************

	@RequestMapping(value = "admin/NCHYogaHealthPromotionPdfDownload", method = RequestMethod.POST)
	public ModelAndView NCHYogaHealthPromotionPdfDownload(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport5", required = false) String typeReport,
			@RequestParam(value = "course_id5", required = false) String course_id, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_YOGA_Report_Excel(course_id);

		///////////////// Start List Of Topic //////////////////

//		List_of_Topic_list
		List<ArrayList<String>> List_of_Topic_listYOGA = PARDAO1.List_of_Topic_listYOGA(course_id);
		int total1 = List_of_Topic_listYOGA.size();
		List<String> TH = new ArrayList<String>();
		TH.add("Sr No.");
		TH.add("TOPIC");
		TH.add("CLASS");

		///////////////// End List Of Topic //////////////////

		return new ModelAndView(
				new NCHYogaHealthPromotionPdfDownloadClass(sysdegprofcorsnamecode, TH, List_of_Topic_listYOGA));

	}
//	END RIDDHI-YOGA FOR HEALTH PROMOTION**********************************************

	// START RIDDHI - Homoeopathic Materia Medica

				@RequestMapping(value = "admin/NCHHomoeopathicMateriaMedicaPdfDownload", method = RequestMethod.POST)
				public ModelAndView NCHHomoeopathicMateriaMedicaPdfDownload(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "typeReport6", required = false) String typeReport,
						@RequestParam(value = "course_id6", required = false) String course_id, HttpServletRequest request) {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_Report_Excel(course_id);

//					Strt	Program_Outcomes

					ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);

//					End	Program_Outcomes

//					Strt	COURSE OUTCOMES

					ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

//					End	COURSE OUTCOMES

//						Strt	COURSE OUTCOMES

					ArrayList<ArrayList<String>> Specific_Objective_list = PARDAO1.tblSpecific_Objective_list(course_id);

//						End	COURSE OUTCOMES

					/////////// Strt Teaching hours//////////////////

					List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours_Homoeopathic(course_id);
					int total1 = Teaching_hour.size();
					List<String> TH = new ArrayList<String>();
					TH.add("Year");
					TH.add("Teaching hours- Lectures");
					TH.add("Teaching hours- Non-lectures");

					/////////// End Teaching hous//////////////////

//					Strt	List_of_Topic_list
					List<ArrayList<String>> List_of_Topic_listHOM = PARDAO1.List_of_Topic_listHOM_medica(course_id);
					int total = List_of_Topic_listHOM.size();
					List<String> TH1 = new ArrayList<String>();
					TH1.add("Sr No.");
					TH1.add("List of Topics");
					TH1.add("Hours");

					////////// End End List Of Topic //////////////////

//						Strt	Non-Lecture Activities (Practical)
					List<ArrayList<String>> Non_Lecture_Activities_List_HOM = PARDAO1.Non_Lecture_Activities_List_HOM(course_id);
					int totalHom = Non_Lecture_Activities_List_HOM.size();
					List<String> TH2 = new ArrayList<String>();
					TH2.add("Sr No.");
					TH2.add("Non Lecture Teaching Learning Methods");
					TH2.add("Time Allotted per Activity\r\n"
							+ "(Hours)");

					/////// End Non-Lecture Activities (Practical) //////////////////

					// Strt TEACHING LEARNING METHODS========================================

					ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
					int totalTeaching_Methodlist = Teaching_Method_list.size();

					// End TEACHING LEARNING METHODS======================================

					// Start Table 2-Learning Objectives (Theory) of Course
					// HomUG-OM-I==========================

					List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
							.table2_Learning_Objectives_of_Course_HomUG_Medica(course_id);
					int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
					List<String> TH2loc = new ArrayList<String>();

					TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

					TH2loc.add("Subject\r\n" + "Area");

					TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
							+ "Knows");

					TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

					TH2loc.add("SLO/\r\n" + "Outcome");
					TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
					TH2loc.add("Guilbert’s\r\n" + "Level");
					TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
					TH2loc.add("T-L\n" + "Methods\n");
					TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
							+ "Vertical/\r\n" + "Spiral");

					// Start Assessment Summary===========================

					List<ArrayList<String>> table6_number_of_papers_listP = PARDAO1
							.Number_of_papers_and_Mark_DistributionP(course_id);

					int total6 = table6_number_of_papers_listP.size();
					List<String> TH6 = new ArrayList<String>();

					TH6.add("Sr.No.");// 0
					TH6.add("Course  Code");// 1
					TH6.add("Papers");// 2
					TH6.add("Theory");// 3
					TH6.add("Practical");// 4
					TH6.add("Viva Voce");// 5
					TH6.add("Internal Assessment- Practical");// 6
					TH6.add("Grand Total");// 7

					/////////////////// - END Assessment Summary/////////////////////////////

//					// Start 8-B===================== Start Scheme of Assessment (formative and Summative)======================
//
//					ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "I");
//					ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "II");
//					ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse_ana(course_id, "III");
//
//					// End 8-B===================== End Scheme of Assessment (formative and Summative)=================

					// Start 8-d===================== Start Scheme of Assessment (formative )======================

					ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
					ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
					ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

					// End 8-D===================== End Scheme of Assessment (formative)=================
					
					// Start Evaluation Methods for Periodical============================

					List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
							.Table_Evaluation_Methods_For_Periodical_Assessment(course_id);

					List<String> TH8 = new ArrayList<String>();

					TH8.add("Sr. No.\n");
					TH8.add(" Evaluation Methods for Periodical Assessment");

					// End Evaluation Methods for Periodical ===============================

				//// Start Paper Layout ////////////////////////==============================

					ArrayList<ArrayList<String>> Paper_LayoutHOM = PARDAO1.Paper_Layout_ListHOM(course_id);
					
					List<String> THM50 = new ArrayList<String>();
					
					THM50.add("Question Types\n");//0
					THM50.add(" Marks");//1

					//// End Paper Layout ////////////////////////==============================

					// Start 8 G -Distribution of Practical Exam ===============================

					List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

					// End of 8 G -Distribution of Practical
					
					// Start 6FI-PaperI=========================================

							ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
									.Table6F_IDistribution_of_Theory_Exam_List_Medica_DAO(course_id);
					// End 6FI-PaperI=========================================
							
					// 6FI- PaperII=========================================

					ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
							.Table6F_IIDistribution_of_Theory_Exam_List_Medica_DAO(course_id);

					// end 6FI- PaperII===============================

					
					 // Question paper Blue print for ====================================

					List<ArrayList<String>> table61_list = PARDAO1.getpaperformatdata_Homeo_Medica(course_id, "16,17,18", "mcq", "PAPER I");
					List<ArrayList<String>> table62_list = PARDAO1.getpaperformatdata_Homeo_Medica(course_id, "16,17", "saq", "PAPER I");
					List<ArrayList<String>> table63_list = PARDAO1.getpaperformatdata_Homeo_Medica(course_id, "16", "laq", "PAPER I");

					List<String> THQ6 = new ArrayList<String>();

					THQ6.add("A\n" // 0
							+ "Question\n" + "Sr. No \n");
					THQ6.add("B\n" // 1
							+ "Type of Question \n");
					THQ6.add("C\n" // 2
							+ "Question Paper Format\n");
					THQ6.add("Q1 \n"); // 3
					THQ6.add("Multiple choice Questions\n" // 4
							+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
							+ "Must know part: 6 MCQ\n" + "Desirable to know: 2 MCQ\n" + "Nice to know: 2 MCQ \n");
					THQ6.add("Q2 \n"); // 5
					THQ6.add(
							"Short answer Questions\n" + "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
									+ "Must know part: 10 SAQ\n" + "Desirable to know: 2 SAQ\n" + "Nice to know: 1 SAQ");
					THQ6.add("Q3 \n"); // 6
					THQ6.add("Long answer Questions\n" // 7
							+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
							+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

					// end of Question paper Blue print for================================
					
					// Start Reference and Resourses========================================

					ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
					int totalreference_resourseslist = Reference_Resourses_list.size();

					// End Reference and Resourses======================================

					// End of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
					String username = session.getAttribute("username").toString();
					return new ModelAndView(new NCHHomoeopathicMateriaMedicaPdfDownload("L", username, username,
							sysdegprofcorsnamecode, Program_Outcomes_list, Course_Outcomes_list, Specific_Objective_list, TH,
							Teaching_hour, TH1, List_of_Topic_listHOM, TH2, Non_Lecture_Activities_List_HOM, Teaching_Method_list,
							TH2loc, table2_Learning_Objectives_of_Course_HomUG_list, TH6, table6_number_of_papers_listP,
							table6d_term1list,table6d_term2list,table6d_term3list, TH8, TableEvaluation_Methods_list,
							THM50,Paper_LayoutHOM, Distri_Pract_Exam,Table6F_IDistribution_of_Theory_Exam_List,Table6F_IIDistribution_of_Theory_Exam_List,
							table61_list,table62_list,table63_list,THQ6 ,
							Reference_Resourses_list), "userList", Teaching_hour);

				}
//					END RIDDHI - Homoeopathic Materia Medica

				// START RIDDHI - Homoeopathic Pharmacy

				@RequestMapping(value = "admin/NCHHomoeopathicPharmacyPdfDownload", method = RequestMethod.POST)
				public ModelAndView NCHHomoeopathicPharmacyPdfDownload(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "typeReport8", required = false) String typeReport,
						@RequestParam(value = "course_id8", required = false) String course_id, HttpServletRequest request) {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
						
					List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_Report_Excel(course_id);

//						Strt	Program_Outcomes

					ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);

//						End	Program_Outcomes

//						Strt	COURSE OUTCOMES

					ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

//						End	COURSE OUTCOMES

					/////////// Strt Teaching hours//////////////////

					List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours(course_id);
					int total1 = Teaching_hour.size();
					List<String> TH = new ArrayList<String>();
					TH.add("Sr No.");
					TH.add("Subject");
					TH.add("Theoretical Lecture");
					TH.add("Practical / Tutorial / Seminar / Clinical Posting");

					/////////// End Teaching hous//////////////////
					
					List<String> TH3 = new ArrayList<String>();
					TH3.add("Sr.No.");
					TH3.add("Topics");
					TH3.add("Hrs");
					TH3.add("Term");

					List<ArrayList<String>> topic_subtopic = PARDAO1.List_of_Topic_list_Pharma(course_id);

//						Strt	List_of_Topic_list
					List<ArrayList<String>> List_of_Topic_listHOM = PARDAO1.List_of_Topic_listHOM(course_id);
					int total = List_of_Topic_listHOM.size();
					List<String> TH1 = new ArrayList<String>();
					TH1.add("Sr No.");
					TH1.add("List of Topics");
					TH1.add("Hours");

					////////// End End List Of Topic //////////////////

//							Strt	List_of_practical_list		
					List<ArrayList<String>> Practical_List = PARDAO1.Practical_List(course_id);

					List<String> TH2 = new ArrayList<String>();
					TH2.add("Sr No");
					TH2.add("Homoeopathic Pharmacy Practicals");
					TH2.add("Teaching Hours");
					TH2.add("Peyton’s criteria 4 step assessment");

					List<ArrayList<String>> Teaching_hours_theory = PARDAO1.AnatomyTopicTheory(course_id);


					// Strt TEACHING LEARNING METHODS========================================

					ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
					int totalTeaching_Methodlist = Teaching_Method_list.size();

					// End TEACHING LEARNING METHODS======================================

					// Start Learning objectives (Theory) of Course

					List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
							.table2_Learning_Objectives_of_Course_Pharmacy(course_id);
					int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
					List<String> TH2loc = new ArrayList<String>();

					TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");
					TH2loc.add("Subject\r\n" + "Area");
					TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
							+ "Knows");
					TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");
					TH2loc.add("SLO/\r\n" + "Outcome");
					TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
					TH2loc.add("Guilbert’s\r\n" + "Level");
					TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
					TH2loc.add("T-L\n" + "Methods\n");
					TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
							+ "Vertical/\r\n" + "Spiral");

			// end of Learning objectives (Theory) of Course
					
//					Strt Non-Lecture Activities

				ArrayList<ArrayList<String>> Non_Lecture_Activities_List = PARDAO1.Non_Lecture_Activities_List_Pharmacy(course_id);

//					End	Non-Lecture Activities

					
					// Start Assessment Summary===========================

					List<ArrayList<String>> table6_number_of_papers_listP = PARDAO1
							.Number_of_papers_and_Mark_DistributionP(course_id);

					int total6 = table6_number_of_papers_listP.size();
					List<String> TH6 = new ArrayList<String>();

					TH6.add("Sr.No.");// 0
					TH6.add("Course  Code");// 1
					TH6.add("Papers");// 2
					TH6.add("Theory");// 3
					TH6.add("Practical");// 4
					TH6.add("Viva Voce");// 5
					TH6.add("Internal Assessment- Practical");// 6
					TH6.add("Grand Total");// 7

					/////////////////// - END Assessment Summary/////////////////////////////

					// Start 8-B===================== Start Scheme of Assessment (formative and  Summative)======================
					
					ArrayList<ArrayList<String>> table6b_term1list = PARDAO1.get6BSchemeViewdatabyCourse_Pharmacy(course_id, "I");
					ArrayList<ArrayList<String>> table6b_term2list = PARDAO1.get6BSchemeViewdatabyCourse_Pharmacy(course_id, "II");
					ArrayList<ArrayList<String>> table6b_term3list = PARDAO1.get6BSchemeViewdatabyCourse_Pharmacy(course_id, "III");

					// End 8-B===================== End Scheme of Assessment (formative and Summative)=================
					
					// Start 8-d===================== Start Scheme of Assessment (formative )======================

					ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
					ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
					ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

					// End 8-D===================== End Scheme of Assessment (formative)=================

					// Start Evaluation Methods for Periodical============================

					List<ArrayList<String>> TableEvaluation_Methods_list = PARDAO1
							.Table_Evaluation_Methods_For_Periodical_Assessment(course_id);

					List<String> TH8 = new ArrayList<String>();

					TH8.add("Sr. No.\n");
					TH8.add(" Evaluation Methods for Periodical Assessment");

					// End Evaluation Methods for Periodical ===============================

				//// Start Paper Layout ////////////////////////==============================

					ArrayList<ArrayList<String>> Paper_LayoutHOM = PARDAO1.Paper_Layout_ListP(course_id);
					
					List<String> THPh50 = new ArrayList<String>();
					
					THPh50.add("Question Types\n");//0
					THPh50.add(" Marks");//1
					THPh50.add(" Time ");//1

					//// End Paper Layout ////////////////////////==============================
					
					// Start 6FI-PaperI=========================================

					ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Theme = (ArrayList<ArrayList<String>>) PARDAO1
							.Table6F_I_Distribution_of_Theory_Exam_List_Pharmacy(course_id);
			// End 6FI-PaperI=========================================
					
			// 6FI- PaperII=========================================

			ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
					.Table6F_II_Distribution_of_Theory_Exam_List_Pharmacy(course_id);

			// end 6FI- PaperII===============================

			// Question paper Blue print for ====================================

			List<ArrayList<String>> table61_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "1");
			List<ArrayList<String>> table62_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "1");
			List<ArrayList<String>> table63_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "1");

			List<String> THQ6 = new ArrayList<String>();

			THQ6.add("A\n" // 0
					+ "Question\n" + "Sr. No \n");
			THQ6.add("B\n" // 1
					+ "Type of Question \n");
			THQ6.add("C\n" // 2
					+ "Question Paper Format\n");
			THQ6.add("Q1 \n"); // 3
			THQ6.add("Multiple choice Questions\n" // 4
					+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
					+ "Must know part: 6 MCQ\n" + "Desirable to know: 2 MCQ\n" + "Nice to know: 2 MCQ \n");
			THQ6.add("Q2 \n"); // 5
			THQ6.add(
					"Short answer Questions\n" + "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
							+ "Must know part: 10 SAQ\n" + "Desirable to know: Nil\n" + "Nice to know: Nil");
			THQ6.add("Q3 \n"); // 6
			THQ6.add("Long answer Questions\n" // 7
					+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
					+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

			// end of Question paper Blue print for================================

					// Start 8 G -Distribution of Practical Exam ===============================

					List<ArrayList<String>> Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);

					// End of 8 G -Distribution of Practical

					// Start Reference and Resourses========================================

					ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
					int totalreference_resourseslist = Reference_Resourses_list.size();

					// End Reference and Resourses======================================

					// End of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I
					String username = session.getAttribute("username").toString();
					return new ModelAndView(
							new NCHHomoeopathicPharmacyPdfDownload("L", username, username, sysdegprofcorsnamecode,
									Program_Outcomes_list, Course_Outcomes_list, TH, Teaching_hour, TH2,TH3,topic_subtopic, Practical_List,
									Teaching_Method_list, TH2loc,table2_Learning_Objectives_of_Course_HomUG_list,Non_Lecture_Activities_List, TH6,
									table6_number_of_papers_listP, table6b_term1list, table6b_term2list, table6b_term3list,table6d_term1list,table6d_term2list,table6d_term3list, TH8,
									TableEvaluation_Methods_list, THPh50,Paper_LayoutHOM, Table6F_IDistribution_of_Theory_Exam_List_Theme,
									Table6F_IIDistribution_of_Theory_Exam_List,table61_list,table62_list,table63_list,THQ6 ,
									Distri_Pract_Exam, Reference_Resourses_list),
									"userList", Teaching_hour);

				}
//						END RIDDHI - Homoeopathic Pharmacy

	// PARTH-Human physiology & Biochemistry

				@RequestMapping(value = "admin/NCHHumanPhysiologyBiochemistryPdfDownload", method = RequestMethod.POST)
				public ModelAndView NCHHumanPhysiologyBiochemistryPdfDownload(ModelMap Mmap, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "typeReport7", required = false) String typeReport,
						@RequestParam(value = "course_id7", required = false) String course_id, HttpServletRequest request) {

//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_YOGA_Report_Excel(course_id);

					ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);

					ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

					///////////////// Teaching hours//////////////////

					List<ArrayList<String>> Teaching_hour = PARDAO1.Teachinghours(course_id);
					int total1 = Teaching_hour.size();
					List<String> TH = new ArrayList<String>();
					TH.add("Sr No.");
					TH.add("Subject");
					TH.add("Theoretical Lecture");
					TH.add("Practical / Tutorial / Seminar / Clinical Posting");

					///////////////// Teaching hours//////////////////

					// Start Theory Wise Teaching Hours Distribution===========================

					List<ArrayList<String>> TheoryWiseTeachingHoursDistributionH = PARDAO1
							.TheoryWiseTeachingHoursDistributionH(course_id);

					int total2 = TheoryWiseTeachingHoursDistributionH.size();
					List<String> TH2 = new ArrayList<String>();
					TH2.add("Sr. No");
					TH2.add("List of System");
					TH2.add("Teaching Hours");

					// End Theory Wise Teaching Hours Distribution==============================

					// Start List Of Practical

					List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH = PARDAO1
							.getPopupNch_Practical_ChildDatalistH(course_id);
					List<String> THP = new ArrayList<String>();
					THP.add("Sr. No");
					THP.add("Practical");
					THP.add("Demonstration/ Performance");
					THP.add("Number of Teaching Hours");

					// End List Of Practical

					// Start List Of Practical

					List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH1 = PARDAO1
							.getPopupNch_Practical_ChildDatalistH(course_id);
					List<String> THPC = new ArrayList<String>();
					THPC.add("Sr. No");
					THPC.add("Practical");
					THPC.add("Demonstration/ Performance");

					// End List Of Practical

					// Start TEACHING LEARNING METHODS========================================

					ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
					int totalTeaching_Methodlist = Teaching_Method_list.size();

					// End TEACHING LEARNING METHODS======================================
					
					
					// Start Learning objectives (Theory) of Course
					// ============================

					List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
							.table2_Learning_Objectives_of_Course_HomUG(course_id);
					int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
					List<String> TH2loc = new ArrayList<String>();
					System.err.println("table2_Learning_Objectives_of_Course_HomUG_list=================="
							+ table2_Learning_Objectives_of_Course_HomUG_list);

					TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

					TH2loc.add("Subject\r\n" + "Area");

					TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
							+ "Knows");

					TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

					TH2loc.add("SLO/\r\n" + "Outcome");
					TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
					TH2loc.add("Guilbert’s\r\n" + "Level");
					TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
					TH2loc.add("T-L\n" + "Methods\n");
					TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
							+ "Vertical/\r\n" + "Spiral");

					// end of Learning objectives (Theory) of Course

					// Start Practical learning objectives===============================================

						List<ArrayList<String>> table4_Practical_Learning_Objectives_list = PARDAO1
								.table4_Practical_Learning_Objectives_list(course_id);
						int total4loc = table4_Practical_Learning_Objectives_list.size();
						List<String> TH4loc = new ArrayList<String>();

						TH4loc.add("Generic\r\n" + "Compet\r\n" + "ency");

						TH4loc.add("Subject\r\n" + "Area");

						TH4loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
								+ "Knows");

						TH4loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

						TH4loc.add("SLO/\r\n" + "Outcome");
						TH4loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
						TH4loc.add("Guilbert’s\r\n" + "Level");
						TH4loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
						TH4loc.add("T-L\n" + "Methods\n");
						TH4loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
						TH4loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
						TH4loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
								+ "Vertical/\r\n" + "Spiral");

							// end of Learning objectives (Theory) of  Course=============================================
							
							// Distribution Of Theory Exam -PaperI=========================================

							ArrayList<ArrayList<String>> TablePhysio_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
									.Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAO(course_id);

							// end  Distribution Of Theory Exam -PaperI===========================

							//  Distribution Of Theory Exam - PaperII=========================================

							ArrayList<ArrayList<String>> TablePhysio_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO1
									.Table6F_II_Distribution_of_Theory_Exam_List__ThemeDAO(course_id);
							
							// end  Distribution Of Theory Exam -PaperII===========================
							
							// Question paper Blue print for ====================================

							List<ArrayList<String>> Ptable61_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "1");
							List<ArrayList<String>> Ptable62_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "1");
							List<ArrayList<String>> Ptable63_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "1");
							List<ArrayList<String>> Ptable64_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17,18", "mcq", "2");
							List<ArrayList<String>> Ptable65_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16,17", "saq", "2");
							List<ArrayList<String>> Ptable66_list = PARDAO1.getpaperformatdata_Homeo(course_id, "16", "laq", "2");

							List<String> THQ6 = new ArrayList<String>();

							THQ6.add("A\n" // 0
									+ "Question\n" + "Sr. No \n");
							THQ6.add("B\n" // 1
									+ "Type of Question \n");
							THQ6.add("C\n" // 2
									+ "Question Paper Format\n");
							THQ6.add("Q1 \n"); // 3
							THQ6.add("Multiple choice Questions\n" // 4
									+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
									+ "Must know part: 7 MCQ\n" + "Desirable to know: 2 MCQ\n" + "Nice to know: 1 MCQ \n");
							THQ6.add("Q2 \n"); // 5
							THQ6.add(
									"Short answer Questions\n" + "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
											+ "Must know part: 10 SAQ\n" + "Desirable to know: Nil SAQ\n" + "Nice to know: Nil");//6
							THQ6.add("Q3 \n"); // 7
							THQ6.add("Long answer Questions\n" // 8
									+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
									+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");
							THQ6.add("Q1 \n"); // 9
							THQ6.add("Multiple choice Questions\n" // 10
									+ "(MCQ)\n\n" + "10 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
									+ "Must know part: 7 MCQ\n" + "Desirable to know: 2 MCQ.\n" + "Nice to know: 1 MCQ");
							THQ6.add("Q2 \n"); // 11
							THQ6.add("Short answer Questions\n" // 12
									+ "(SAQ)\n" + "ten Questions\n" + "5 Marks Each\n" + "All compulsory\n"
									+ "Must know part: 7 SAQ\n" + "Desirable to know: 3 SAQ\n" + "Nice to know: 1 SAQ");
							THQ6.add("Q3 \n"); // 13
							THQ6.add("Long answer Questions\n" // 14
									+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
									+ "All questions on must to know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

							THQ6.add("A\n" // 15
									+ "Question\n" + "Sr. No \n");
							THQ6.add("B\n" // 16
									+ "Type of Question \n");
							THQ6.add("C\n" // 17
									+ "Question Paper Format\n");
							

					// end of Question paper Blue print for================================
							
							// Start  Distribution of Practical Exam ===============================

							List<ArrayList<String>> Physio_Distri_Pract_Exam = PARDAO1.Distribution_of_Practical_Exam(course_id);
							
							// End  Distribution of Practical Exam ===============================
							// Start 8-d===================== Start Scheme of Assessment (formative )======================

							ArrayList<ArrayList<String>> table6d_term1list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "I");
							ArrayList<ArrayList<String>> table6d_term2list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "II");
							ArrayList<ArrayList<String>> table6d_term3list = PARDAO1.get6DSchemeViewdatabyCourse(course_id, "III");

							// End 8-D===================== End Scheme of Assessment (formative)=================
							
							// Strt Reference and Resourses========================================

							ArrayList<ArrayList<String>> Physio_Reference_Resourses_listA = PARDAO1.tableReference_Resourses(course_id);
							int totalreference_resourseslist = Physio_Reference_Resourses_listA.size();
							
							// End Reference and Resourses========================================
					
					return new ModelAndView(new NCHHumanPhysiologyBiochemistryPdfDownloadClass(sysdegprofcorsnamecode,
							Program_Outcomes_list, Course_Outcomes_list, TH, Teaching_hour, TH2,
							TheoryWiseTeachingHoursDistributionH, THP, getPopupNch_Practical_ChildDatalistH, THPC,
							getPopupNch_Practical_ChildDatalistH1, Teaching_Method_list,table2_Learning_Objectives_of_Course_HomUG_list, 
							TH2loc,table4_Practical_Learning_Objectives_list,TH4loc,TablePhysio_IDistribution_of_Theory_Exam_List
							,TablePhysio_IIDistribution_of_Theory_Exam_List,Ptable61_list,Ptable62_list,Ptable63_list,
							Ptable64_list,Ptable65_list,Ptable66_list,THQ6,Physio_Distri_Pract_Exam,table6d_term1list,table6d_term2list,table6d_term3list,Physio_Reference_Resourses_listA));

				}
//			PARTH-Human physiology & Biochemistry
	
	//-------------------------------------------------Start Tanvi  - Homoeopathic Repertory And Case Taking
	
		@RequestMapping(value = "admin/NCHHomoeopathicReportandCaseTakingPdfDownload", method = RequestMethod.POST)
		public ModelAndView NCHHomoeopathicReportandCaseTakingPdfDownload(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "typeReport9", required = false) String typeReport,
				@RequestParam(value = "course_id9", required = false) String course_id, HttpServletRequest request) {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NCH_Curriculum_Pdf_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<Map<String, Object>> sysdegprofcorsnamecode = PARDAO1.getstudentdetails_Report_Excel(course_id);
			
//			Strt	Program_Outcomes

			ArrayList<ArrayList<String>> Program_Outcomes_list = PARDAO1.tblProgram_Outcomes_list(course_id);

//			End	Program_Outcomes
			
//			Strt	COURSE OUTCOMES

			ArrayList<ArrayList<String>> Course_Outcomes_list = PARDAO1.tblCourse_Outcomes_list(course_id);

//			End	COURSE OUTCOMES
			
	//---------------------Start Teaching hours---------------------

			List<ArrayList<String>> Teaching_hour = PARDAO1.TeachinghoursReportandCase(course_id);
			int total1 = Teaching_hour.size();
			List<String> TH = new ArrayList<String>();
			TH.add("Course Name");
			TH.add("Lectures");
			TH.add("Non-Lectures");
			TH.add("Total");

	//-----------------------End Teaching hours-------------------
			
			
//			Start	List_of_Topic_list
			List<ArrayList<String>> List_of_Topic_listHOM = PARDAO1.List_of_Topic_listReportCase(course_id);
			int total = List_of_Topic_listHOM.size();
			List<String> TH1 = new ArrayList<String>();
			TH1.add("Sr No.");
			TH1.add("List of Topics");
			TH1.add("Lecture Hours");

	//------------------End End List Of Topic------------------------
			
	// Start TEACHING LEARNING METHODS========================================

					ArrayList<ArrayList<String>> Teaching_Method_list = PARDAO1.tblTeaching_Method_list(course_id);
					int totalTeaching_Methodlist = Teaching_Method_list.size();

	// End TEACHING LEARNING METHODS======================================
					
					
	// Start Table 2-Learning Objectives (Theory) of Course HomUG-OM-I==========================

					List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_list = PARDAO1
							.table2_Learning_Objectives_of_Course_ReportandCase(course_id);
					int total2loc = table2_Learning_Objectives_of_Course_HomUG_list.size();
					List<String> TH2loc = new ArrayList<String>();
					System.err.println("table2_Learning_Objectives_of_Course_ReportandCase=================="
							+ table2_Learning_Objectives_of_Course_HomUG_list);

					TH2loc.add("Generic\r\n" + "Compet\r\n" + "ency");

					TH2loc.add("Subject\r\n" + "Area");

					TH2loc.add("Millers\r\n" + "Level:\r\n" + "Does/Sh\r\n" + "ows\r\n" + "how/\r\n" + "Knows\r\n" + "how/\r\n"
							+ "Knows");

					TH2loc.add("Specific\r\n" + "Compete\r\n" + "ncy");

					TH2loc.add("SLO/\r\n" + "Outcome");
					TH2loc.add("Bloo\r\n" + "ms\r\n" + "Doma\r\n" + "in");
					TH2loc.add("Guilbert’s\r\n" + "Level");
					TH2loc.add("Must" + "Know/" + "Desira\r\n" + "ble to\r\n" + "know/\r\n" + "nice\r\n" + "to\r\n" + "know");
					TH2loc.add("T-L\n" + "Methods\n");
					TH2loc.add("Formati\r\n" + "ve\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Summa\r\n" + "tive\r\n" + "Assess\r\n" + "ment");
					TH2loc.add("Integrati\r\n" + "on\r\n" + "Departm\r\n" + "ents-\r\n" + "Horizont\r\n" + "al/\r\n"
							+ "Vertical/\r\n" + "Spiral");
					
					
	//------------------------------- End Table 2-Learning Objectives (Theory) of Course------
					
	//---------------------Start List Of Practicals------

					List<ArrayList<String>> list_of_practical = PARDAO1.Practical_listReportandCase(course_id);
					int total2 = list_of_practical.size();
					List<String> TH2 = new ArrayList<String>();
					TH2.add("Sr. No");
					TH2.add("Name of Topic");
					TH2.add("Activity/ Practical");
					TH2.add("TL Method");

	//-------------------------End List Of Practicals-----
					
					
	//---------------------Start Reference and Resourses========================================

					ArrayList<ArrayList<String>> Reference_Resourses_list = PARDAO1.tableReference_Resourses(course_id);
					int totalreference_resourseslist = Reference_Resourses_list.size();

	//-----------------------End Reference and Resourses======================================

			String username = session.getAttribute("username").toString();
			return new ModelAndView(new NCHHomoeopathicReportandCaseTakingPdfDownload("L", username, username,
					sysdegprofcorsnamecode, Program_Outcomes_list, Course_Outcomes_list,TH, Teaching_hour, TH1, List_of_Topic_listHOM, Teaching_Method_list,TH2loc,table2_Learning_Objectives_of_Course_HomUG_list,TH2,list_of_practical,Reference_Resourses_list));

		}
	//---------------------END Tanvi - Homoeopathic Repertory And Case Taking

}