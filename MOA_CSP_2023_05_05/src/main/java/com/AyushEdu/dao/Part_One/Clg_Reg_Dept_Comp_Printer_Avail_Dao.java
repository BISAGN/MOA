package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Dept_Comp_Printer_Avail_Dao {

	
	public ArrayList<ArrayList<String>> getAllDepartment_name();
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ug();	//ug details
	
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_pg();
	public ArrayList<ArrayList<String>> getAllDepartment_list_new_ap();

	public List<Map<String,Object>> getugdetails(int institute_id);

	public List<Map<String,Object>> getapdetails(int institute_id);	//academic perfomance details
	
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id);
	
	
	public List<Map<String,Object>> getdeptcomputerdetails(int institute_id);
	public ArrayList<ArrayList<String>> getHosptal_opd_ipd_listDepartment() ;
	
	public String getHosptal_opd_ipd_TableDepartment() ;
	public String getHospital_ipd_TableDepartment() ;
	public String getHospital_Bed_Days_Occupied_TableDepart();
	public String getHospital_Bed_ExistedDepart();
	
	public String getHosptal_opd_ipd_TableDepartmentFetch(String institute_id);
	public String getHospital_ipd_patientTableDepart_Fetch(String institute_id);
	public String getHospital_Bed_Days_Occupied_TableDepartFetch(String institute_id);
	public String getHospital_Bed_ExistedDepartFetch(String institute_id);
	public ArrayList<ArrayList<String>> getHosptal_opd_ipd_UploadDocumentsFetch(String institute_id);

	
}
