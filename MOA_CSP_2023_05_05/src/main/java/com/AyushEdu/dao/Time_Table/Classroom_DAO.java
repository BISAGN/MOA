package com.AyushEdu.dao.Time_Table;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Time_Table.EDU_TT_CLASSROOM_MSTR;


public interface Classroom_DAO {
	public List<Map<String, Object>> DataTableClassroomDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String classroom, String status, int institute_id);

	public long DataTableClassroomDataTotalCount(String search, String classroom, int institute_id);
	
	public EDU_TT_CLASSROOM_MSTR getClassroomByid(int id);

	public String updateClassroom(EDU_TT_CLASSROOM_MSTR td);

}
