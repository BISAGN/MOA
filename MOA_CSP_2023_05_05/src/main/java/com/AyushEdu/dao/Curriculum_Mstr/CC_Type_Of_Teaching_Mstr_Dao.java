package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_TEACHING_MSTR;

public interface CC_Type_Of_Teaching_Mstr_Dao {

	public List<Map<String, Object>> DataTableTypeOfTeachingDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String type_of_teaching, String status);

	public long DataTableTypeOfTeachingDataTotalCount(String search, String type_of_teaching,String status);

	public String updateType_Of_Teaching(CC_TB_TYPE_OF_TEACHING_MSTR td);


}
