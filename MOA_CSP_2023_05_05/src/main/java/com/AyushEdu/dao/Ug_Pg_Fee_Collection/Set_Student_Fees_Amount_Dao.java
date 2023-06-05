package com.AyushEdu.dao.Ug_Pg_Fee_Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Set_Student_Fees_Amount_Dao {

	public List<Map<String, Object>> DataTable_CMEAttend_DataList(String userId,String degree_name,String prof_id,String inst_id,String role);

	public List<Map<String, Object>> getPopup_FeesChildDatalist(String studentid, String role, String inst_id);

}
