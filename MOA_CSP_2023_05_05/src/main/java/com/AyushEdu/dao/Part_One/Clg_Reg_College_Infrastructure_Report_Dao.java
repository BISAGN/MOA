package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_College_Infrastructure_Report_Dao {

	public List<Map<String, Object>> DataTableSearch_College_InfrastructureDataList(int startPage, int pageLength,
			String search, String orderColunm, String orderType, String council_check, String website_update_date,
			String username, String biometric_status, String cctv_status, String role, String userid,
			String institute_id);

	public long DataTableSearch_College_InfrastructureDataTotalCount(String search, String council_check,
			String website_update_date, String username, String biometric_status, String cctv_status, String role,
			String userid, String institute_id);

	public List<Map<String, Object>> getClg_central_lib_infoReport(int id, int institute_id,String role);

	public List<Map<String, Object>> getDepart_dtlReport(int id, int institute_id,String role);

	public List<Map<String, Object>> getClg_central_lib_childinfoReport(int id, int institute_id, String role);

	public List<Map<String, Object>> getView_idFrom_Institute_id(int institute_id);

	public List<Map<String, Object>> getClg_camera_locationinfoReport(int id, int institute_id, String role);

}
