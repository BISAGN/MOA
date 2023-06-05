package com.AyushEdu.dao.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface University_Enrollment_Dao {

//	public ArrayList<ArrayList<String>> DataTableUniversityEnrollmentDataList(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType, String university_enrollment_formate) ;
//	
//	public long DataTableuniversityDataTotalCount(String Search, String university_enrollment_formate);
	
	public ArrayList<ArrayList<String>> getUniversityEnrollmentDetails(int userid);

	public List<Map<String, Object>> getAdmissionFeepaidstudentListForunienrollReport(String academic_year,
			String system_id, String degree_id, String institute_id, String verified_status,String Staff_lvl);
	
	public String getMaxUniEnrollmentID(String userid,String staff_lvl);

	public String getUpdateuniversityenrolmentno(String sd_id, String maxUEID, String staff_lvl);

	public String getUpdateuniversityenrolmentnoasaayushid(String sd_idayu, String staff_lvl);

	public String getUpdateuniversityenrolmentnomanualid(String sd_idman, String university_enrollment,
			String staff_lvl);
	
}
