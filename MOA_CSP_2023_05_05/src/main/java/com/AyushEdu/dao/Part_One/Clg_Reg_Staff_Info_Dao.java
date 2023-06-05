package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Staff_Info_Dao {
	
	public ArrayList<ArrayList<String>> getAll_ug_Department_name();

	public ArrayList<ArrayList<String>> getAllDepartment_list_ug_new();
//	
	public String getAllDepartment_list_new_ug();

	
	public ArrayList<ArrayList<String>> getAll_pg_Department_name();

	public String getAllDepartment_list_new_pg();
	public ArrayList<ArrayList<String>> getAllCourse_PG();
	public ArrayList<ArrayList<String>> getAll_post_name();

	public String getAllPost_list();

	public  ArrayList<ArrayList<String>> getAll_academic_name();
	public String getAllacademic_list();

//	public LinkedHashMap<String, Object> getInstitute_id(String userid);
	public ArrayList<ArrayList<String>> getInstitute_id(String user_id);

	public ArrayList<ArrayList<String>> getAllSalary_Details(int institute_id);

	public ArrayList<ArrayList<String>> getp_idfromuser_id(String userid);

	public List<Map<String,Object>> getAlluploaddocumentdetails(int institute_id);

	public List<Map<String,Object>> getAllteacherpromotiondetails(int institute_id);
	
	public List<Map<String,Object>> getDesignationList();

}
