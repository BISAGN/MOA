package com.AyushEdu.dao.Time_Table;

import java.util.ArrayList;

public interface Academic_Schedule_DAO {

	ArrayList<ArrayList<String>> GetviewDate_Academic_Prof(String professional, int institute_id);

	ArrayList<ArrayList<String>> GetviewDate_Examination_Prof(String professional, int institute_id);

	ArrayList<ArrayList<String>> GetviewDate_Fee_Prof(String professional_id, int institute_id);
	
	ArrayList<ArrayList<String>> GetviewDate_Tran_Curr_Prof(String professional_id, int institute_id);
	
	public ArrayList<ArrayList<String>> GetWorkLoadCalData(String degree,String prof,String role);
	
	public ArrayList<ArrayList<String>> getAddedHoursbyCourseandInst(String course_id,String inst_id);

}
