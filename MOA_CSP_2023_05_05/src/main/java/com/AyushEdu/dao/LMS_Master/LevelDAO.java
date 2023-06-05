package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_LEVEL_MSTR;



public interface LevelDAO {

	public EDU_LMS_LEVEL_MSTR getlevelByid(int id);

	public List<Map<String, Object>> DataTablelevelDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String level_name,String status,String sequence_name);

	public long DataTablelevelDataTotalCount(String search, String level_name,String sequence_name);
}
