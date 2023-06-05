package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_Student_Details_Report_Dao {

	public List<Map<String, Object>> DataTableSearch_Student_DetailsDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String last_student, String year, String internsopd,
			String internsipd, String prescribe, String seminar, String house_job, String no_house_job,
			String migrationcheck, String role, String userid, String institute_id);

	public long DataTableSearch_Student_DetailsDataTotalCount(String search, String last_student, String year,
			String internsopd, String internsipd, String prescribe, String seminar, String house_job,
			String no_house_job, String migrationcheck, String role, String userid, String institute_id);

	public List<Map<String, Object>> getAllinfo_Admitted_Stu_Details_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getStu_Details_Upload_Doc_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getStu_Details_Pass_Stu_View(int id, int inst_id, String role);

}
