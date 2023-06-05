package com.AyushEdu.dao.LMS_Institute;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_CLASSROOM_MSTR;

public interface ClassroomDAO1 {
	
	public List<Map<String,Object>> DataTableclassroomDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String classroom_name, String block_name, String strength, String status );

	public long DataTableclassroomDataTotalCount(String Search, String classroom_name, String block_name, String strength);
	public EDU_LMS_CLASSROOM_MSTR getclassroomByid(int id);
}
