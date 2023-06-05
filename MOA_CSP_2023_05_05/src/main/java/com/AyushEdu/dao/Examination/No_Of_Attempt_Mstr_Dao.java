package com.AyushEdu.dao.Examination;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Examination.EDU_TB_NO_OF_ATTEMPT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;



public interface No_Of_Attempt_Mstr_Dao {
	public EDU_TB_NO_OF_ATTEMPT_MSTR getsystemByid(int id);
	public EDU_LMS_SYSTEM_MSTR getsystemByid1(int id);
	public EDU_LMS_DEGREE_MASTER getsystemByid2(int id);
	public CC_TB_PROFESSIONAL_MSTR getsystemByid3(int id);

	List<Map<String, Object>> DataTablesysDegProCourselinkDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String no_of_attempt, String status);

	long DataTablesysDegProCourselinkDataListTotalCount(String search, String system_id, String degree_id,
			String professional_id, String no_of_attempt, String status);

}
