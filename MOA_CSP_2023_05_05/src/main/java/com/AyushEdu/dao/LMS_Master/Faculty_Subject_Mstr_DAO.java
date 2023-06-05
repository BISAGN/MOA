package com.AyushEdu.dao.LMS_Master;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;

//import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;


public interface Faculty_Subject_Mstr_DAO {
	

	public List<Map<String, Object>> DataTableSubjectDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String fac_course_id, String subject_name, String status);

	public long DataTableSubjectDataTotalCount(String search, String fac_course_id, String subject_name);
	
	public EDU_FACULTY_SUBJECT_MSTR getSubjectByid(int id);

	public String updateFacultySubject(EDU_FACULTY_SUBJECT_MSTR td);




}
