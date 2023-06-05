package com.AyushEdu.dao.RBAC;

import java.util.List;
import java.util.Map;

public interface UserActiveInactiveDAO {

public	List<Map<String, Object>> DataTableSearch_User_ActiveDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String role_id, String username, String loginname, String email_id, String enable_id);

public long DataTableSearch_User_ActiveDataTotalCount(String search, String role_id, String username, String loginname, String email_id, String enable_id);

}
