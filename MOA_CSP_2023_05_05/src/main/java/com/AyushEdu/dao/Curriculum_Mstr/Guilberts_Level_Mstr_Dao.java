package com.AyushEdu.dao.Curriculum_Mstr;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GUILBERTS_LEVEL_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_GUILBERTS_LEVEL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_H3_ASSESSMENT_TYPE_MSTR;

public interface Guilberts_Level_Mstr_Dao {
	public List<Map<String, Object>> DataTableGuilberts_LevelDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String guilberts_level, String status,String role);

	public long DataTableGuilberts_LevelDataTotalCount(String Search,String system_id, String guilberts_level, String status,String role);

	public String updateGuilberts_Level(CC_TB_GUILBERTS_LEVEL_MSTR td);
}
