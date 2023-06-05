package com.AyushEdu.dao.Part_One.Masters;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_HOSP_INFRA_DEPT_MSTR;



public interface Hospital_Infra_Dept_Mstr_DAO {
	
	public CLG_REG_HOSP_INFRA_DEPT_MSTR getHospitalInfraDeptByid(int id);

	public List<Map<String, Object>> DataTableHospitalInfraDeptDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType,  String hospital_department_name, String status,Integer hos_department_id);

	public long DataTableHospitalInfraDeptDataTotalCount(String search,String status,String hospital_department_name,Integer hos_department_id);
	
	public String updateHospitalInfraDeptDept(CLG_REG_HOSP_INFRA_DEPT_MSTR td);
	
}