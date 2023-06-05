package com.AyushEdu.dao.Curriculum;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface T6C_View_Int_Marks_Dao {
	
	public ArrayList<ArrayList<String>> getMarksbyCoursedata(String course_id);

}
