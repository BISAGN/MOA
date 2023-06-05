package com.AyushEdu.dao.Part_One.Masters;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Part_One.Masters.CLG_REG_NON_TEACHING_STAFF_MSTR;



public interface Clg_Reg_Non_Teaching_StaffDAO {
	
	public CLG_REG_NON_TEACHING_STAFF_MSTR getpostByid(int id);

	public List<Map<String, Object>> DataTableNonTeachingStaffDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String post_name,String status);

	public long DataTableNonTeachingStaffDataTotalCount(String search, String post_name);
	
	public String updateNonTeachingStaff(CLG_REG_NON_TEACHING_STAFF_MSTR td);

	
}
