package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_ACADEMIC_MSTR;

public interface Academic_Dao {

	
	
	public EDU_ACADEMIC_MSTR getacademicByid(int id);
	
	public List<Map<String, Object>> DataTable_Academic_DataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String academic,String status);
	
	
	public long DataTable_Academic_DataTotalCount(String search, String academic);
	
	public String updateAcademic(EDU_ACADEMIC_MSTR td);
}
