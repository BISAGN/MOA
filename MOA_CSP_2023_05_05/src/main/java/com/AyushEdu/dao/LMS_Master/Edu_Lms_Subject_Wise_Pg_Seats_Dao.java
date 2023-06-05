package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_WISE_PG_SEATS;

public interface Edu_Lms_Subject_Wise_Pg_Seats_Dao {

	public EDU_LMS_SUBJECT_WISE_PG_SEATS getsubjectwisepgseats(int id);

	public List<Map<String, Object>> DataTablesubjectdegreepglinkDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_id,String degree, String pg_subject,
			String seat, String status, HttpSession session);

	public long DataTablesubjectdegreepglinkDataListTotalCount(String Search, String institute_id,String degree,
			String pg_subject, String seat, String status, HttpSession session);

	public String updateSubjectWisepgSeats(EDU_LMS_SUBJECT_WISE_PG_SEATS td);
	
	public List<Map<String, Object>> PGDegreeofInst(String inst_id);
	
	public List<Map<String, Object>> PGSubjectsofDegree(String degree);
	
	public List<Map<String, Object>> PGSubjectsofInst(String degree,String inst_id);
}
