package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao {
	
	public List<Map<String, Object>> DataTableSearch_College_DepartmentDataList(int startPage, int pageLength,
			String search, String orderColunm, String orderType, String department, String computer, String printer,
			String role, String userid, String institute_id);

	public long DataTableSearch_College_DepartmentDataTotalCount(String search, String department, String computer,
			String printer, String role, String userid, String institute_id);

	public List<Map<String, Object>> getComp_Printer_Avail_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getUG_Depart_From_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getPG_Depart_From_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getDepart_tours_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getDepart_academic_performance_View(int id, int inst_id, String role);

	
	///documentview
	
	public String getFilePath_DynemicQueryForDoc_clg_dept_view(String id, String val, String fildname);
}
