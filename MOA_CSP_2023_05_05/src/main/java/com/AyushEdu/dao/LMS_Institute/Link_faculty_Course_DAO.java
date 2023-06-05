package com.AyushEdu.dao.LMS_Institute;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

public interface Link_faculty_Course_DAO {
	
	public ArrayList<ArrayList<String>> get_faculty_name_list_Fetch(SessionFactory sessionFactory, String userId,String institute_id);



}
