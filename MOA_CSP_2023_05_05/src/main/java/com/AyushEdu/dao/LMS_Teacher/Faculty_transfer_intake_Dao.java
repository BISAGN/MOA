package com.AyushEdu.dao.LMS_Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Faculty_transfer_intake_Dao {
	
	
	
	public List<Map<String, Object>> DataTableFaculty_transfer_DataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid);
	
	public long DataTableFaculty_transfer_DataTotalCount( String Search, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali,String role,String userid);
	
	
	public ArrayList<ArrayList<String>> getuseridfrommainid(int mainid);
	
	public List<Map<String, Object>> TeachercodeAuto(String autoString,String role);
	
	public ArrayList<ArrayList<String>> getuseridforupdateinst(int mainid);



}
