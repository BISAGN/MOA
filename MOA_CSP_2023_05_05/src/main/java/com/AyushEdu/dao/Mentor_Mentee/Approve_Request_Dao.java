package com.AyushEdu.dao.Mentor_Mentee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Approve_Request_Dao {
	
	
	public List<Map<String, Object>> DataTableApprove_Request(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String facUserId );

	public long DataTableApprove_Request_count(String Search,String facUserId );
	
	public List<Map<String, Object>> DataTableMentee_Request(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String facUserId2,String role_id);

	public long DataTableMentee_Request_count(String Search,String facUserId,String rol_id);

	
	public List<Map<String, Object>> getMesgs(String facUserId,String student_userid);
	
	public ArrayList<ArrayList<String>>getStudentlist(String role,String institute_id,String prof,String degree);
	
	public ArrayList<ArrayList<String>>getMentorlist(String role,String institute_id);

}
