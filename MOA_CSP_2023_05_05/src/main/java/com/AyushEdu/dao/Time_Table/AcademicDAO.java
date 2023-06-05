package com.AyushEdu.dao.Time_Table;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS;

public interface AcademicDAO {

	public EDU_TT_ACADEMIC_DETAILS getacademicByid(int id);

	public List<Map<String, Object>> DataTableacademicDataList(int startPage, int pageLength,String search,
			String orderColunm, String orderType, String professional, String academic_details, String term, String exam_type, String exam_serial, String starting_date, String ending_date, int institute_id);

	public long DataTableacademicDataTotalCount(String search, String professional, String academic_details, String term, String exam_type, String exam_serial, String starting_date, String ending_date, int institute_id);

	public List<Map<String, Object>> GetTermFromNoofpart(String role, String inst_id);
}
