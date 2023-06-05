package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;

public interface Module_Mstr_Dao {
	
	public List<Map<String, Object>> DataTablemoduleDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_id, String status,String module_name,String degree_id,String system_id);
	
	public long DataTablemoduleDataTotalCount(String Search,String course_id, String status,String module_name,String degree_id,String system_id);
	
	public EDU_LMS_MODULE_MSTR getmoduleByid(int id);

}
