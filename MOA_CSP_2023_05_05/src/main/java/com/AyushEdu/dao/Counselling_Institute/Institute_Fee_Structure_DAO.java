package com.AyushEdu.dao.Counselling_Institute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Institute_Fee_Structure_DAO {

	ArrayList<ArrayList<String>> getInstituteListbySystem(String system_id);

	public List<Map<String, Object>> DataTableInstitute_Fee_Structure_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_id);

	public long DataTableInstitute_Fee_Structure_DataTotalCount(String Search, String institute_id);

	ArrayList<ArrayList<String>> getFee_Detailslist(String veiw_id);

	public String getImagePath(String id);

}
