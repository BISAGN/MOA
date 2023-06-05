package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_STATE_LOGO_MSTR;

public interface statelogo_Master_DAO {
	
	public long DataTablestatelogoDataTotalCount(String search, String state_id, String state_logo_path, String status);

	public List<Map<String, Object>> DataTablestatelogoDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state_id, String state_logo_path,String status);
	
	public EDU_LMS_STATE_LOGO_MSTR getClassroomByid(int id);

	public String updateStudentLogo(EDU_LMS_STATE_LOGO_MSTR rmd);

	public String getStatelogoImagePath(int id);

}
