package com.AyushEdu.dao.Time_Table;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS_MSTR;

public interface Academic_MSTR_DAO {

	
	public EDU_TT_ACADEMIC_DETAILS_MSTR getacademicByid(int id);

	public List<Map<String, Object>> DataTableAcademic_Details_DataList(int startPage, int pageLength,String Search,
			String orderColunm, String orderType, String academic_details_name, String refer_code);

	public long DataTableAcademic_Details_DataTotalCount(String Search, String academic_details_name, String refer_code);
}
