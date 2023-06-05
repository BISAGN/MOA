package com.AyushEdu.dao.LMS_Attendance;

import java.util.List;
import java.util.Map;

public interface AttendanceDAO {
	public List<Map<String, Object>> DataTableAttendanceDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String staff_name, String atten_date);
	public long DataTableAttendanceDataTotalCount(String Search, String staff_name, String atten_date);
}
