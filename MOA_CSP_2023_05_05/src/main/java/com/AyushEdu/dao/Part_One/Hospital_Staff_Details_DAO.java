package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Hospital_Staff_Details_DAO {
	
	public ArrayList<ArrayList<String>> Gethospostdata();
	public List<Map<String,Object>> gethospitalstaffdetails(int institute_id);
	public ArrayList<ArrayList<String>> getInstitute_id(String user_id);
	public ArrayList<ArrayList<String>> GetTypedata();
}
