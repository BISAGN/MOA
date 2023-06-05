package com.AyushEdu.dao.LMS_Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface View_Course_Content_Dao {
	
	public List<Map<String, Object>> DataTableviewcourse_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name,String module_name,String type_content,String level_of_module,String userid,HttpSession session);
	

	public long DataTableviewcourse_DataTotalCount(String Search,String course_name,String module_name,String type_content,String level_of_module,String userid);
	public  List<ArrayList<String>> getdataForSeqVal(String course_id,String lm,String userid);

	//HET CHANGES
	public  List<ArrayList<String>> getdataForSeqVal2(String course_id,String lm,String userid,String module_id);
	public ArrayList<ArrayList<String>> getcourselistFromtypeofcontent(String type_content,String user_id );
	
	public ArrayList<ArrayList<String>> getmodulelistFromtcourse(String course_name,String user_id );
	public ArrayList<ArrayList<String>> GetExamPaper(String courseid,String module,String userid);
	
	public ArrayList<ArrayList<String>> getcourselistForViewCourse(String userid);
	
	//shivali
	 public ArrayList<ArrayList<String>> getlevel_of_modulebyModule(String module_name,String user_id );
		

}
