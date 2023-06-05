package com.AyushEdu.dao.Part_One.Masters;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_HOS_DEPT_MSTR;

public interface Hospital_Dept_DAO {

	public List<Map<String, Object>> DataTableHospitalDepartmentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String department_name,String status);

	public long DataTableHospitalDepartmentDataTotalCount(String search, String department_name);
	
	public String updateHospitalDepartment(CLG_REG_HOS_DEPT_MSTR td);


}
