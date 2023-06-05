package com.AyushEdu.dao.Examination;
import java.util.ArrayList;
import java.util.List;

public interface Supplementary_Univer_Exam_Dao {

	public List<ArrayList<String>> getstu_Supplementary_declare_data(String system_id,String degree_id,String professional_id,String institute_id,String role,String uni_id);
	
	public List<ArrayList<String>>currentTerm(int stuId,String role);
}
