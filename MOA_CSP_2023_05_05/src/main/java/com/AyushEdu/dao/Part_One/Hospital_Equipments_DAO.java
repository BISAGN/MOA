package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Hospital_Equipments_DAO {
	
	public ArrayList<ArrayList<String>> GetArticlesdata();
	public List<Map<String,Object>> gethospitalquipment(int institute_id);
	public ArrayList<ArrayList<String>> getInstitute_id(String user_id);

}
