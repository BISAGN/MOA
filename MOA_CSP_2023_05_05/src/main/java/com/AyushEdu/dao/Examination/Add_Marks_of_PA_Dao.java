package com.AyushEdu.dao.Examination;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public interface Add_Marks_of_PA_Dao {
	
	public ArrayList<ArrayList<String>> getStudent_Name(String degree_id,String institute_id,String role, String professional_id);
	public ArrayList<ArrayList<String>> getDegreeListFromInstituteExam(String institute_id, String userId, String role) ;
	public ArrayList<ArrayList<String>> getIA_Marks(String degree_id,String professional_id,String course_id,String role);
}
