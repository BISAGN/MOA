package com.AyushEdu.dao.LMS_Attendance;
import java.util.List;
import java.util.Map;

public interface Student_AttendanceDAO {
	
	public List<Map<String, Object>> DataTableSearch_Student_AttenDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name,String date);

	public long DataTableSearch_Student_AttenDataTotalCount(String Search,String name,String date);

}
