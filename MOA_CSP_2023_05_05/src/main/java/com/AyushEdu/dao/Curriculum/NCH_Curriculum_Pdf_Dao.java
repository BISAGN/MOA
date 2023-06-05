package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface NCH_Curriculum_Pdf_Dao {

//	Object DataTableEdu_Reg_Report_masterDataList_pdf1(String userid);
	public ArrayList<ArrayList<String>> GetSystemdegreeid_fetch(String userid);
	public List<Map<String, Object>> getstudentdetails_Report_Excel(String course_id);
	public ArrayList<ArrayList<String>> Teachinghours(String course_id);
	public ArrayList<ArrayList<String>> tblProgram_Outcomes_list(String course_id);
	public ArrayList<ArrayList<String>> tblCourse_Outcomes_list(String course_id);
	
	public List<ArrayList<String>> Table_Evaluation_Methods_For_Periodical_Assessment(String course_id);
	
	public ArrayList<ArrayList<String>> tableReference_Resourses(String course_id);
	
	public ArrayList<ArrayList<String>> tblTeaching_Method_list(String course_id);
	
	public List<ArrayList<String>> Table_Non_Lecture(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Topic_list(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Pratical_list(String course_id);
	
    public ArrayList<ArrayList<String>> Number_of_papers_and_Mark_Distribution(String course_id);
    
    public ArrayList<ArrayList<String>> GetSystem_fetch_NCH(String userid);
    
    public List<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG(String course_id);
    
	public ArrayList<ArrayList<String>> Paper_Layout_List(String course_id);
	
	public List<ArrayList<String>> Distribution_of_Practical_Exam(String course_id);
	
	public ArrayList<ArrayList<String>> getSubtopicfromTopicidNCH(String topic_id);
	
	 public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse(String course_id,String term);
	 
	public List<ArrayList<String>> table4_Practical_Learning_Objectives_list(String course_id);
	
	public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_DAO(String course_id);
	
	public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_DAO(String course_id);
	
	public ArrayList<ArrayList<String>> AnatomyTopicTheory(String course_id);
	
	public ArrayList<ArrayList<String>> AnatomyTopicPractical(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Topic_list_pdf_nch(String course_id);
	
	public List<ArrayList<String>> List_of_Topic_listP(String course_id);
	
	public List<ArrayList<String>> Number_of_papers_and_Mark_DistributionP(String course_id);
	public ArrayList<ArrayList<String>> Paper_Layout_ListP(String course_id);
	
	public ArrayList<ArrayList<String>> ana_practical_topics_list(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Topic_listYOGA(String course_id);
	
	public List<Map<String, Object>> getstudentdetails_YOGA_Report_Excel(String course_id);
	
	public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_Hom_psychology(String course_id);
	
	//Start Riddhi -Homoeopathic Materia Medica
	public ArrayList<ArrayList<String>> Teachinghours_Homoeopathic(String course_id);
	
	public ArrayList<ArrayList<String>> tblSpecific_Objective_list(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Topic_listHOM(String course_id);
	
	public ArrayList<ArrayList<String>> Non_Lecture_Activities_List_HOM(String course_id);
	
	public ArrayList<ArrayList<String>> Paper_Layout_ListHOM(String course_id);
	
	public ArrayList<ArrayList<String>> List_of_Topic_listHOM_medica(String course_id);
	
	public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_Medica(String course_id);
	
	public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Medica_DAO(String course_id) ;
	 
	public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Medica_DAO(String course_id) ;

	public ArrayList<ArrayList<String>> getpaperformatdata_Homeo_Medica(String course_id,String d3_desirable_know,String qt,String noofpaper);
		
	//End Riddhi -Homoeopathic Materia Medica
		
	//START RIDDHI - Homoeopathic Pharmacy	
	public ArrayList<ArrayList<String>> Practical_List(String course_id);
	
	public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_phy(String course_id);
	
	public ArrayList<ArrayList<String>> Non_Lecture_Activities_List_Pharmacy(String course_id);
	//END RIDDHI - Homoeopathic Pharmacy
		
	public ArrayList<ArrayList<String>> TheoryWiseTeachingHoursDistributionH(String course_id);
	public List<ArrayList<String>> getPopupNch_Practical_ChildDatalistH(String course_id);
	
	public ArrayList<ArrayList<String>> getpaperformatdata_Homeo(String course_id,String d3_desirable_know,String qt,String noofpaper);
		
	// Start Tanvi
		
	public ArrayList<ArrayList<String>> TeachinghoursReportandCase(String course_id); 
	public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_ReportandCase(String course_id);
	public ArrayList<ArrayList<String>> List_of_Topic_listReportCase(String course_id);
	public ArrayList<ArrayList<String>> Practical_listReportandCase(String course_id);
	public ArrayList<ArrayList<String>> Paper_Layout_ListOrganon(String course_id);
	public List<ArrayList<String>> List_of_Topic_list_pdf_Organon(String course_id);

	public List<ArrayList<String>> getpaperformatdata_Homeo1(String course_id,String d3_desirable_know,String qt,String noofpaper);
	public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Organon(String course_id);
	public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Organon(String course_id);
	public List<ArrayList<String>> Table_Evaluation_Methods_For_Periodical_AssessmentED(String course_id);
		
	//End   TANVI
		
	public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse_ana(String course_id,String term);
	public ArrayList<ArrayList<String>> Paper_Layout_List_ana(String course_id);
	public ArrayList<ArrayList<String>> Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAO(String course_id);
	public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List__ThemeDAO(String course_id);
	
	public List<ArrayList<String>> table2_Learning_Objectives_of_Psychology(String course_id);
	
	public ArrayList<ArrayList<String>> Table6F_I_Distribution_of_Theory_Exam_List_Pharmacy(String course_id);
	public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_Pharmacy(String course_id);
	public List<ArrayList<String>> table2_Learning_Objectives_of_Course_Pharmacy(String course_id);
	public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse_Pharmacy(String course_id,String term);
	public List<ArrayList<String>> List_of_Topic_list_Pharma(String course_id); 							 
  
		
	public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Psycology(String course_id); 

	
	public ArrayList<ArrayList<String>> get6DSchemeViewdatabyCourse(String course_id,String term) ;
		
		
}

