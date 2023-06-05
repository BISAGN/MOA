package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;


public interface Department_Mstr_DAO {
	

	public List<Map<String, Object>> DataTableDepartmentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String department, String status);

	public long DataTableDepartmentDataTotalCount(String search, String department);
	
	public TB_NCH_DEPARTMENT_MSTR getDepartmentByid(int id);

	public String updateDepartment(TB_NCH_DEPARTMENT_MSTR td);




}
