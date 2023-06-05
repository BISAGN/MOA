package com.AyushEdu.dao.AyushId_Directory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AyushId_DirectoryDAO {
	
	public String getAyushRoleStatus(String roleid);
	public String getAyushID(String userid,HttpSession session);

    List<Map<String, Object>> DataTableAyushIdDirectoryList(int startPage, int pageLength, String search, String orderColunm, String orderType, String aadharNo, String ayushId, String userId, String roleId ,String user_name , String login_name , String email_id  );

	long DataTableAyushIdDirectoryTotalCount(String search, String aadharNo, String ayushId, String userId, String roleId,  String user_name , String login_name , String email_id );
}
