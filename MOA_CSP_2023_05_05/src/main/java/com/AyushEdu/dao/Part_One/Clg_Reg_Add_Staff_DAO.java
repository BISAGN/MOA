package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;

public interface Clg_Reg_Add_Staff_DAO {
	
	public ArrayList<ArrayList<String>> getTeaching_Faculty_Details(int institute_id);
	
	public ArrayList<ArrayList<String>> getGuest_Faculty_Details(int institute_id);
	
	public ArrayList<ArrayList<String>> getNon_Teaching_Staff_Details(int institute_id);

}
