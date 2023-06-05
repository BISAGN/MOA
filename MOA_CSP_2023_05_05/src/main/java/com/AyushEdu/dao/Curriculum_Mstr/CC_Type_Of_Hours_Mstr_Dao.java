package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_HOURS_MSTR;

public interface CC_Type_Of_Hours_Mstr_Dao {

	public List<Map<String, Object>> DataTableTypeOfHoursDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String type_of_hours, String status);

	public long DataTableTypeOfHoursDataTotalCount(String search, String type_of_hours, String status);

	public String updateType_Of_Hours(CC_TB_TYPE_OF_HOURS_MSTR td);

}
