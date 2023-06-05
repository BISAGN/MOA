package com.AyushEdu.dao.AyushId_Directory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.AyushId_Directory.AYUSH_ROLE_INFORMATION;

public interface Ayush_Role_InformationDAO {
	
	public List<Map<String, Object>> DataTableAyushRoleInformationDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, Integer roleid,String status,String role);

	public long DataTableAyushRoleInformationDataTotalCount(String search, Integer roleid,String role,String status);
	
	public String updateAyushRoleInformation(AYUSH_ROLE_INFORMATION td);
}
