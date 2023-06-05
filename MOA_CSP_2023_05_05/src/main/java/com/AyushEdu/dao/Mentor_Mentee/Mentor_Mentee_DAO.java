package com.AyushEdu.dao.Mentor_Mentee;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

public interface Mentor_Mentee_DAO {
	
	public List<Map<String, Object>> getSystemofStudent(String userid,String role);
	
	public List<Map<String, Object>> getSearchMentorDetails(String a,String system);

	public List<Map<String, Object>> DataTableMentorDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String login_name, String faculty_user_id,String to_msg_uid);

	public long DataTableMentorDataTotalCount(String search, String login_name, String faculty_user_id,String stu_user_id);
	
//	public String getmentrorstomenteeforsave(String a,String username,String userId,String faculty_user_id);
	
	public List<Map<String, Object>> getMesgsformenties(String facUserId,String student_userid,String role_id);
	
	public String getFilePathQuery(int id);
	
	

}