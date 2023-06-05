package com.AyushEdu.dao.LMS_Institute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;

public interface Institute_Registration_Dao {
	
	public List<Map<String, Object>> DataTableinstitute_regDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_name, String country_id, String state_id,
			String district_id, String app_status,String role);
	

	public long DataTableinstitute_regDataTotalCount(String Search, String institute_name, String country_id,
			String state_id, String district_id, String app_status,String role);
	public List<TB_STATE> getcountrylistUrl(Integer u_id);

	public List<EDU_LMS_DISTRICT_MSTR> getStatelistUrl(Integer u_id);
	
	public ArrayList<ArrayList<String>> getinstitute_system(int id);

	public ArrayList<ArrayList<String>> getSerialCollegeNumber(String system_id);
	public ArrayList<ArrayList<String>> getSyatemAbbr(String system_id);
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversitylistUrl(Integer selval);
	public ArrayList<ArrayList<String>> getCountrylistbyUniversity(int selval); 
	public List<Map<String, Object>> getInstitueDetailsforEmail();
	public List<Map<String, Object>> getStudentDetailsforEmail();
	public List<Map<String, Object>> GetAyushCollegeDataDetails();

	
	
}
