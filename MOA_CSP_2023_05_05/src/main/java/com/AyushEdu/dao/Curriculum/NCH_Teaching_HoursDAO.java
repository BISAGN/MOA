package com.AyushEdu.dao.Curriculum;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_TEACHING_HOURS;

public interface NCH_Teaching_HoursDAO {

	public List<Map<String, Object>> NCH_Teaching_HoursDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String theoretical_lecture, String pract_tutor_semi_clinic_post, String role);

	public long DataTableNCH_Teaching_HoursTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id,String theoretical_lecture, String pract_tutor_semi_clinic_post, String role);
	
	public String updateNCH_Teaching_Hours(EDU_CC_TB_NCH_TEACHING_HOURS td);
}
