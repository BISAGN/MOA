package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_LINK_REFERENCE_RESOURCES_MSTR;

public interface Reference_Resours_Dao {

	public EDU_CC_LINK_REFERENCE_RESOURCES_MSTR getreferenceresoursekdata(int id);

	public List<Map<String, Object>> ReferenceResoursesDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String status, String resource, String role);

	public long DataTableReferenceResourcesTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String status, String resource, String role);

	public String updateReferenceResouces(EDU_CC_LINK_REFERENCE_RESOURCES_MSTR td);
}
