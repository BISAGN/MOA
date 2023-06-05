package com.AyushEdu.dao.Examination;

import java.util.ArrayList;

public interface View_Internal_assessment_MarksDao {

	public ArrayList<ArrayList<String>> GetviewInternal_ass_marks(String course_id, String professional_id,
			String userid, String exam_type, String exam_seral, String role);

	public ArrayList<ArrayList<String>> Getdegreeid_fetch(String userid);
}
