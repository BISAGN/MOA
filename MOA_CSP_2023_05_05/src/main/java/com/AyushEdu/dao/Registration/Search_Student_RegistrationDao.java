package com.AyushEdu.dao.Registration;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_Student_RegistrationDao {

	public ArrayList<ArrayList<String>> getUniversitylist() ;
	public ArrayList<ArrayList<String>> getinstitutelist(String userid);
	public List<Map<String, Object>> DataTableSearch_Stu_RegDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth);
	public long DataTableSearch_Stu_RegDataTotalCount(String Search,String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth);
	public List<Map<String, Object>> getSearch_Stu_RegDataforPopup(String id);
	public String getFilePathQuery_popup1(int id);
	public ArrayList<ArrayList<String>> get_inst_by_uni_nch_data(String university_id);
	public ArrayList<ArrayList<String>> getStudent_Registration_Report_Excel(String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth)
			throws ParseException;

	
}

