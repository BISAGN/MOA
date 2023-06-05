package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Professional_Ayu_Report_Dao {

	public List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataList_pdf( String course_id);
	
	public List<Map<String, Object>> examination_list(String course_id);

	public List<ArrayList<String>> non_lec_activities(String course_id);

	public ArrayList<ArrayList<String>> table1_co_po_link(String course_id);

	public List<ArrayList<String>> TableList_of_practicalDataTotalCount(String course_id);
	
	public List<ArrayList<String>> table3_Learning_Objectives_Course_AyUGRS(String course_id);
	
	public List<ArrayList<String>> table4_Learning_Objectives_Practical_of_AyUGRS(String course_id);

	public List<ArrayList<String>> t2Content_Course_AyUGRS_list(String course_id);

	public List<ArrayList<String>> table5_Non_Lecture_Activities_Course_AyUGRS(String course_id);
	
	public List<ArrayList<String>> practhours(String course_id);

	public List<ArrayList<String>> Table6A_NumberofPapersDataTotalCount(String course_id);

	public List<ArrayList<String>> Table6D_Evaluation_Methods_For_Periodical_Assessment(String course_id);
	
	public ArrayList<ArrayList<String>> table_6E_paper_layout(String course_id);

	public ArrayList<ArrayList<String>> table_6E_paper_layout2(String course_id);
	
	public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_DAO(String course_id);

	public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_DAO(String course_id);
	
	public List<ArrayList<String>> table_6H_I_Distribution_of_Practical_Exam(String course_id);

	public ArrayList<ArrayList<String>> tablereference_resourses(String course_id);
	
//	public ArrayList<ArrayList<String>> getCoursedata(String course_id) ;
	
	public ArrayList<ArrayList<String>> GetSystemdegreeid_fetch(String userid);
	
	public ArrayList<ArrayList<String>> GetSystem_fetch(String userid);
	
}
