package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Hosp_Administration_Dao {
	
	public ArrayList<ArrayList<String>> getHospital_department_administrative_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_opd_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_ipd_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_ot_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_ru_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_cl_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_rs_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_hk_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_hs_list();
	
	public ArrayList<ArrayList<String>> getHospital_department_oi_list();	
	
	public List<Map<String,Object>> getAllOtherdetails(int institute_id);
		
	public List<Map<String,Object>> getAllOtherdetailsipd(int institute_id);
	
	public List<Map<String,Object>> getAllStatuatorydata(int institute_id);
	
	
	public String getAllDepartmentforHA(int institute_id);
	
	public String getAllDepartmentforOPD();
	
	public String getAllDepartmentforIPD();
	
	public String getAllDepartmentforOT();
	
	public String getAllDepartmentforRU();
	
	public String getAllDepartmentforCL();
	
	public String getAllDepartmentforRS();
	
	public String getAllDepartmentforHK();
	
	public String getAllDepartmentforHS();
	
	public String getAllDepartmentforOI();

}
