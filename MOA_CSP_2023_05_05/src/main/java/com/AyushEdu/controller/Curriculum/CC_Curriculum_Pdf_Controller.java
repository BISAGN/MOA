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
public class CC_Curriculum_Pdf_Controller {

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

	@RequestMapping(value = "admin/CC_Professional_Ayu_Report_Url", method = RequestMethod.GET)
	public ModelAndView CC_Professional_Ayu_Report_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Professional_Ayu_Report_Url", roleid1);		
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
			if(role.toLowerCase().contains("student")) {
				Mmap.put("system", PARDAO.GetSystemdegreeid_fetch(userid));
			}
			if(role.toLowerCase().contains("institute")) {
				Mmap.put("system_name", PARDAO.GetSystem_fetch(userid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CC_Professional_Ayu_Report_Tiles");
	}

	// download pdf
	@RequestMapping(value = "admin/Professional_Ayu_Report_Url_pdf", method = RequestMethod.POST)
	public ModelAndView Professional_Ayu_Report_Url_pdf(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "typeReport", required = false) String typeReport,
			@RequestParam(value = "course_id1", required = false) String course_id, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("CC_Professional_Ayu_Report_Url", roleid1);		
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
			List<String> THr = new ArrayList<String>();
			THr.add("SN");
			THr.add("Name of Practical");
			THr.add("Term");

			// end of random table=========================================

			// Table 1- Course learning outcomes and matched
			// PO========================================

			ArrayList<ArrayList<String>> t1copolink_list = PARDAO.table1_co_po_link(course_id);
			int total1 = t1copolink_list.size();
			List<String> TH1 = new ArrayList<String>();
			TH1.add("SR1\n" + "CO No");
			TH1.add("A1\n" + "Course learning Outcome (CO)\n"
					+ "At the end of the course, the student should be able\n" + "to");
			TH1.add("B1\n" + "Course learning\n" + "Outcome matched with\n" + "program learning\n" + "outcomes..");

			// end of Table 1- Course learning outcomes and matched PO==========================================================

			// Table 2: Contents of Course AyUG-RS=======================================

				List<ArrayList<String>> t2Content_Course_AyUGRS_list = PARDAO.t2Content_Course_AyUGRS_list(course_id);

				int total2 = t2Content_Course_AyUGRS_list.size();
				List<String> TH2 = new ArrayList<String>();
				TH2.add("SN");
				TH2.add("A2\n" + "List of Topics ");
				TH2.add("B2\n" + "Term");
				TH2.add("C2\n" + "Marks");
				TH2.add("D2\n" + "Lecture\n" + "hours");
				TH2.add("E2\n" + "NonLecture\n" + "hours");

				// end of Table 2: Contents of Course


			// Table 3: Learning objectives (Theory) of Course

			List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list = PARDAO
					.table3_Learning_Objectives_Course_AyUGRS(course_id);
			int total3 = t3LearningObject_Course_AyUGRS_list.size();
			List<String> TH3 = new ArrayList<String>();

			TH3.add("A3\n" + "Course\n" + "outcom\n" + "e\n");

			TH3.add("B3\n" + "Learning Objective\n" + "(At the end of the\n" + "session, the students\n"
					+ "should be able to)\n");

			TH3.add("C3\n" + "Domain/\n" + "sub\n");

			TH3.add("D3\n" + "Must to\n" + "know \n" + "desirable\n" + "to\n" + "know/Ni\n" + "ce to\n" + "know\n");

			TH3.add("E3\n" + "Level\n" + "Does/\n" + "Shows\n" + "how/\n" + "Knows\n" + "how/\n" + "Know\n");
			TH3.add("F3\n" + "T-L method\n");
			TH3.add("G3\n" + "Assessment\n");
			TH3.add("H3\n" + "Formati\n" + "ve\n" + "/summa\n" + "tive\n");
			TH3.add("I3\n" + "Te\n" + "rm\n");
			TH3.add("J3\n" + "Integrat\n" + "ion\n");

			// end of Table 3: Learning objectives (Theory) of Course

//======================start Practical- list===========================

			List<ArrayList<String>> tableList_of_practical_list = PARDAO
					.TableList_of_practicalDataTotalCount(course_id);

			int totalLPA = tableList_of_practical_list.size();
			List<String> THLPA = new ArrayList<String>();

			THLPA.add("SN"); // 0
			THLPA.add("Name of Practical");// 1
			THLPA.add("Term"); // 2
			THLPA.add("Activity / Practical Description/ Hours ");// 3

			// ==================== end Practical- list===========================

			// Table 4: Learning objectives (Practical) of AyUG-

			List<ArrayList<String>> t4Learning_Objectiveslist = PARDAO
					.table4_Learning_Objectives_Practical_of_AyUGRS(course_id);

			int total4 = t4Learning_Objectiveslist.size();
			List<String> TH4 = new ArrayList<String>();

			TH4.add("A4\n" + "Course\n" + "outcome");

			TH4.add("B4\n" + "Learning\n" + "Objectiv\n" + "e\n" + "(At the\n" + "end of\n" + "the\n" + "session,\n"
					+ "the\n" + "students\n" + "should\n" + "be able\n" + "to)");

			TH4.add("C4\n" + "Domain/\n" + "s\n" + "ub");

			TH4.add("D4\n" + "Must to\n" + "know/\n" + "desirabl\n" + "e to\n" + "know/N\n" + "ice to\n" + "know");

			TH4.add("E4\n" + "Level\n" + "Does/\n" + "Shows\n" + "how/\n" + "Knows\n" + "how/\n" + "Know");
			TH4.add("F4\n" + "T-L method");
			TH4.add("G4\n" + "Assessment");
			TH4.add("H4\n" + "Form\n" + "ative\n" + "/sum\n" + "mativ\n" + "e");
			TH4.add("I4\n" + "Te\n" + "rm");
			TH4.add("J4\n" + "Integration");

			// end of Table 4: Learning objectives (Practical) of AyUG-

			// Table 5: Non-Lecture Activities Course ============================

			List<ArrayList<String>> table5nlac_list = PARDAO.table5_Non_Lecture_Activities_Course_AyUGRS(course_id);

			List<ArrayList<String>> practhours = PARDAO.practhours(course_id);

			List<String> TH5 = new ArrayList<String>();

			TH5.add("Ser No.\n");
			TH5.add(" List non lecture Teaching-Learning methods");
			TH5.add("No of Activities\n" + "(Values in hours)");

			// end of Table 5: Non-Lecture Activities Course

			// 6 A - Number of Papers and Marks Distribution============================

			List<ArrayList<String>> table6A_number_of_papers_list = PARDAO
					.Table6A_NumberofPapersDataTotalCount(course_id);

			int total6 = table6A_number_of_papers_list.size();
			List<String> TH6A = new ArrayList<String>();

			TH6A.add("S.No.");// 0
			TH6A.add("Subject Code");// 1
			TH6A.add("Papers");// 2
			TH6A.add("Theory");// 3
			TH6A.add("Practical/ \n" + "Clinical");// 4
			TH6A.add("Viva");// 5
			TH6A.add("Electives");// 6
			TH6A.add("IA");// 7
			TH6A.add("Sub Total");// 8
			TH6A.add("Grand Total");// 9
			TH6A.add("Practical/Clinical Assessment");// 10

			// end of 6 A - Number of Papers and Marks Distribution============================

			// 6-B=====================

			ArrayList<ArrayList<String>> table6b_term1list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "I");
			ArrayList<ArrayList<String>> table6b_term2list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "II");
			ArrayList<ArrayList<String>> table6b_term3list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "III");

			// 6-B======================

			// 6 D - Evaluation Methods for Periodical Assessment============================

			List<ArrayList<String>> Table6Dnlac_list = PARDAO
					.Table6D_Evaluation_Methods_For_Periodical_Assessment(course_id);

			List<String> TH6D = new ArrayList<String>();

			TH6D.add("Ser No.\n");
			TH6D.add(" Evaluation Methods for Periodical Assessment");

			// end 6 D - Evaluation Methods for Periodical Assessment===============================

			// Table 6E: Paper Layout===============================

			ArrayList<ArrayList<String>> table_6E1 = PARDAO.table_6E_paper_layout(course_id);
			ArrayList<ArrayList<String>> table_6E2 = PARDAO.table_6E_paper_layout2(course_id);

			List<String> TH6E = new ArrayList<String>();
			List<String> TH6E2 = new ArrayList<String>();

			// end of Table 6E: Paper Layout==========================================================

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

			List<String> TH6G = new ArrayList<String>();

			TH6G.add("A\n" // 0
					+ "Question\n" + "Sr. No \n");
			TH6G.add("B\n" // 1
					+ "Type of Question \n");
			TH6G.add("C\n" // 2
					+ "Question Paper Format\n");
			TH6G.add("Q1 \n"); // 3
			TH6G.add("Multiple choice Questions\n" // 4
					+ "(MCQ)\n\n" + "20 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
					+ "Must know part: 15 MCQ\n" + "Desirable to know: 3 MCQ\n" + "Nice to know: 2 MCQ \n");
			TH6G.add("Q2 \n"); // 5
			TH6G.add(// 6
					"Short answer Questions\n" + "(SAQ)\n" + "Eight Questions\n" + "5 Marks Each\n" + "All compulsory\n"
							+ "Must know part: 7 SAQ\n" + "Desirable to know: 1 SAQ\n" + "Nice to know: Nil");
			TH6G.add("Q3 \n"); // 7
			TH6G.add("Long answer Questions\n" // 8
					+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
					+ "All questions on must know\n" + "No Questions on Nice to know and Desirable to\n" + "know");
			TH6G.add("Q1 \n"); // 9
			TH6G.add("Multiple choice Questions\n" // 10
					+ "(MCQ)\n\n" + "20 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
					+ "Must know part: 15 MCQ\n" + "Desirable to know: 3 MCQ.\n" + "Nice to know: 2 MCQ");
			TH6G.add("Q2 \n"); // 11
			TH6G.add("Short answer Questions\n" // 12
					+ "(SAQ)\n" + "Eight Questions\n" + "5 Marks Each\n" + "All compulsory\n"
					+ "Must know part: 7 SAQ\n" + "Desirable to know: 1 SAQ\n" + "Nice to know: Nil");
			TH6G.add("Q3 \n"); // 13
			TH6G.add("Long answer Questions\n" // 14
					+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
					+ "All questions on must know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

			TH6G.add("Question Sr. No  \n"); // 15
			TH6G.add("B\n" // 16
					+ "Type of Question \n");
			TH6G.add("SET \n"); // 17

			// end of 6 G Question paper Blue print

			// Table 6H - I - Distribution of Practical Exam ===============================

			List<ArrayList<String>> table_6H1 = PARDAO.table_6H_I_Distribution_of_Practical_Exam(course_id);

			List<String> TH6H1 = new ArrayList<String>();

			TH6H1.add("SN");
			TH6H1.add("Heads ");
			TH6H1.add("Marks");

			// end of Table 6H - I - Distribution of Practical  Exam==========================================================

			// 7. Reference and Resourses========================================

			ArrayList<ArrayList<String>> reference_resourses_list = PARDAO.tablereference_resourses(course_id);
			int totalrrlist = reference_resourses_list.size();

			// End 7. Reference and Resourses======================================

			if (list.size() == 0) {
				System.err.println("-------list--------13-"+list);
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				if (typeReport != null && typeReport.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();
						TH.add("sr. no."); // 0
						TH.add("Ayush Id/ABHA No.");// 1
						TH.add("Name Of The Professional With Recent Photograph"); // 2
						TH.add("NRH Enrollment No."); // 3
						TH.add("Father's Name"); // 4
						TH.add("Present Correspondence Address"); // 5
						TH.add("Permanent Address"); // 6
						TH.add("Aadhaar Number"); // 7
						TH.add("Email Address With Mobile No."); // 8
						TH.add("Email Address With Mobile No."); // 9
						TH.add("Date of Birth And Nationality"); // 10
						TH.add("Name Of Medical Degree or Diploma Obtained And Uni"); // 11

						String Heading = "\nSCHEDULE OF CREDIT";
						String username = session.getAttribute("username").toString();
						return new ModelAndView(new DownloadCurriculumPdf("L", TH, TH1, TH2, TH3, THLPA, TH4, TH5, TH6A,
								TH6D, TH6E, TH6E2, TH6G, TH6H1, username, list, total, examination_list,
								t1copolink_list, t2Content_Course_AyUGRS_list, t3LearningObject_Course_AyUGRS_list,
								tableList_of_practical_list, t4Learning_Objectiveslist, table5nlac_list, practhours,
								table6A_number_of_papers_list, table6b_term1list, table6b_term2list, table6b_term3list,
								Table6Dnlac_list, table_6E1, table_6E2, Table6F_IDistribution_of_Theory_Exam_List,
								Table6F_IIDistribution_of_Theory_Exam_List, table6g1_list, table6g2_list, table6g3_list,
								table6g4_list, table6g5_list, table6g6_list, table_6H1, reference_resourses_list,course_id),
								"userList", list);
					}
				}
			}
			// end
		}
		return new ModelAndView("CC_Professional_Ayu_Report_Tiles");
	}
}
