package com.AyushEdu.dao.VC_System;

import java.util.List;
import java.util.Map;

public interface Create_MeetingDao {

	public long DataTableCreateMeetingDataTotalCount(String search, String meeting_id, String name, String attendeepw_id,
			String moderatorpw_Id, String welcome, String record, String autostartrecording,
			String allowstartstoprecording);
	
	public List<Map<String, Object>> DataTableCreateMeetingDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String meeting_id, String name, String attendeepw_id, String moderatorpw_id,
			 String welcome, String record, String autostartrecording, String allowstartstoprecording);

}
