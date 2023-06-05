package com.AyushEdu.dao.Degree_recognition_Fellowship_Grant;

import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR;

public interface Coding_Courses_HomoeopathyDAO {
	
	public DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR getcode_course_homByid(int id);

   public	List<Map<String, Object>> DataTableCoding_Courses_Hom_Details_DataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String sr_no, String qualification, String code);

	public long DataTableCoding_Courses_Hom_Details_DataTotalCount(String search, String sr_no, String qualification, String code);

}
