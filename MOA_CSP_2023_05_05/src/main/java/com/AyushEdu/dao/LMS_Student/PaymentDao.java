package com.AyushEdu.dao.LMS_Student;

import java.util.ArrayList;

public interface PaymentDao {

	public ArrayList<ArrayList<String>> getsetByModule(String setid);
	
	public ArrayList<ArrayList<String>> GetCourse_Set_payment(String p_id,String userid);
	
	public ArrayList<ArrayList<String>> GetModule_fetch_payment(String course_id);
	
	public ArrayList<ArrayList<String>> Getcourse_fee(String course_id);
	
}
