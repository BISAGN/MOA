package com.AyushEdu.dao.Examination;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.Examination.CC_TB_ATTEMPT_MSTR;

public interface Attempt_Dao {
	
	public List<Map<String, Object>> DataTableAttemptDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String attempt, String status);

	public long DataTableAttemptDataTotalCount(String search, String attempt);
	
	public CC_TB_ATTEMPT_MSTR getAttemptByid(int id);

	public String updateAttempt(CC_TB_ATTEMPT_MSTR td);


}
