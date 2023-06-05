package com.AyushEdu.dao.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_QUESTION_TYPE_MSTR;
import com.AyushEdu.Models.Registration.EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR;

public interface Admission_Academic_ScheduleDao {
	
	 public ArrayList<ArrayList<String>> getSystemID(String role);
	 
	 
		public List<Map<String, Object>> DataTableacademicscheduleDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String academic_year,String start_date, String end_date,String system_name, String status,String userid);
	 
	 public long DataTableacademicscheduleDataTotalCount(String Search, String academic_year,String system_name,String start_date,String end_date,String status,String userid);

	public String updateAcademic_year(EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR td);
	 

}
