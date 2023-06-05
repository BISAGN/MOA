package com.AyushEdu.dao.LMS_Master;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_COURSE_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;

public interface Faculty_Course_MasterDao {
	public EDU_FACULTY_COURSE_MSTR getsystemByid(int id);

	public List<Map<String, Object>> DataTableFaculty_Course_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Faculty_Course,String type_of_degree, String status);

	public long DataTableFaculty_Course_MasterDataTotalCount(String Search, String Faculty_Course,String type_of_degree, String status);
}
