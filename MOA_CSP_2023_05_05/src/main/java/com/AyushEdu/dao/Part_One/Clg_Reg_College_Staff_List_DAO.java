package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;

public interface Clg_Reg_College_Staff_List_DAO {

	public String getFull_time_teacher_details(String institute_id);
	public ArrayList<ArrayList<String>> getAllFull_Time_Teacher_Details(String institute_id);
	public String getGuest_teacher_details(String institute_id);
	public ArrayList<ArrayList<String>> getAllGuest_teacher_details(String institute_id);
	public String getTeaching_Faculty_details(String institute_id);
	public String getNon_Teaching_Faculty_details(String institute_id);
	public ArrayList<ArrayList<String>> getAllNon_Teaching_Faculty_details(String institute_id);
	public String getAttachmentFilePath(String id,String doc_id);
	public ArrayList<ArrayList<String>> GetDocument_Details(String institute_id);
	public ArrayList<ArrayList<String>> GetPrinacipal_Name(String institute_id);
	
}
