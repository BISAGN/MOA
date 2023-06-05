package com.AyushEdu.dao.CommonDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Commondao {

	
	public String getTopicVideoPath(int p_id);
	public String getTopicVideoPath2(int p_id,int module);
	public ArrayList<ArrayList<String>> getinstIDfromuserID(int userid);
	public ArrayList<ArrayList<String>> getinstitute_system(int institute_id);
	//HARSH 12-04-2023
	public ArrayList<ArrayList<String>> getstu_DegreeList(int institute_id,String type_of_degree);
	public List<Map<String, Object>> getAllReportListJdbc(String qry, String type);
	public String getdemoVideoPath(int id,int set_id);
	public ArrayList<ArrayList<String>> getteacher_list(String userid);
	public String getTopicVideoPathp_id(int id) ;
	public ArrayList<ArrayList<String>> getCourseNew();
	public ArrayList<ArrayList<String>> getFacultyListForNotification(int userid,String course_id);
	public String getStaffLvlfromRoleid(String role_id);
	public ArrayList<ArrayList<String>> getRolebyStaffLvl(String staff_lvl,String role);
	public ArrayList<ArrayList<String>> getUniversityNchlist();
	public ArrayList<ArrayList<String>> getUniversityByRoletypelist();
	public ArrayList<ArrayList<String>> getCourse_upload_Paper();
	
	
	public ArrayList<ArrayList<String>> getFacultyNchSystemlist();
	
	public ArrayList<ArrayList<String>> getUniversitybyinstitutelist(String institute_id);
	public ArrayList<ArrayList<String>> getCourseForfaculty(int user_id);
//	------------------------------------Start Curriculum------------------------------------------------
	public ArrayList<ArrayList<String>> get_Po_Datalist(String hid); 
//	------------------------------------End Curriculum------------------------------------------------
	public ArrayList<ArrayList<String>> getUniversityNcismlist();
	public ArrayList<ArrayList<String>> getSubjectForpg_graduform(String system_id, String degree_id);
	public String getFilePath_DynemicQueryForDoc(String id, String val, String fildname);
	public List<Map<String, Object>> getDocumentAtchmantlistbyscreen_url(String screen_url);
	public ArrayList<ArrayList<String>> getinstituteNchlist();
	public ArrayList<ArrayList<String>> getinstituteNcismlist();
	
	public List<Map<String,Object>> Contact_UsDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name, String email);
	
	public long DataTotalContact_UsCount(String search, String name, String email);
	
	public List<Map<String,Object>> Feedback_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String type_of_issue, String first_name,String last_name, String email);
	
	public long DataTotalFeedbackCount(String search, String type_of_issue, String first_name,String last_name, String email);
	
	public ArrayList<ArrayList<String>> getDegreeListPG(int institute_id);
	
	public ArrayList<ArrayList<String>> getALLPGDegreeList();
	
	public ArrayList<ArrayList<String>> getALLPGSubjectbyDegree(String degree);
}
