package com.AyushEdu.dao.Part_One.Masters;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_CLG_DEPT_AVAILABILITY_MSTR;



public interface Clg_Reg_Clg_Dept_Availability_DAO {
	
	public CLG_REG_CLG_DEPT_AVAILABILITY_MSTR getClgDeptAvailByid(int id);

	public List<Map<String, Object>> DataTableClgDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String department,String status);

	public long DataTableClgDataTotalCount(String search, String department);
	
	public String updateClgDept(CLG_REG_CLG_DEPT_AVAILABILITY_MSTR td);
	
}