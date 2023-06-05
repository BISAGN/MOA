package com.AyushEdu.dao.Registration.Graduation_NCISM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Search_Ncism_Student_RegistrationDao {

	public ArrayList<ArrayList<String>> getUniversity_Ncismlist() ;
	public ArrayList<ArrayList<String>> getinstitute_Ncismlist(String userid);
	public List<Map<String, Object>> DataTableSearch_Stu_RegData_NcismList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth);
	public long DataTableSearch_Stu_Reg_NcismDataTotalCount(String Search,String university_id, String institute_id,String name,String ayush_id,String gender,String date_of_birth);
	public List<Map<String, Object>> getSearch_Stu_Reg_NcismDataforPopup(String id);
	public String getFilePathQuery_Ncism_popup1(int id);
	public ArrayList<ArrayList<String>> get_inst_by_uni_ncism_data(String university_id);
	public List<ArrayList<String>> getStudent_Registration_Report_Excel_NCISM(String university_id, String institute_id,
			String name, String ayush_id, String gender, String date_of_birth);
	
}

