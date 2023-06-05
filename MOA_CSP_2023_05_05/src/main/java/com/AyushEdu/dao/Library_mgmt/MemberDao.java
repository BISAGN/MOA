package com.AyushEdu.dao.Library_mgmt;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_DTL;


public interface MemberDao {
		
		public TB_MEMBER_DTL getsystemByid1(int id);

		public List<Map<String, Object>> DataTableTB_MEMBER_DTLDataList(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String member_name,String member_joined_date);
		
		public long DataTableTB_MEMBER_DTLTotalCount(String Search, String member_name, String member_joined_date, 
				String member_dept, String member_phone_number );
		
		
		public List<Map<String, Object>> getMemberDetails(int id);
		public List<Map<String, Object>> getAyushIdFromUserId(int userid);
		
		public List<Map<String, Object>> MemberDataAuto(String autoString);
		

		
		public List<Map<String, Object>> MemberdetailsbyayushidDetailsMethod(String ayush_id);
		
		
		

}