package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Clg_Reg_Hospital_Staff_Dtls_Dao {
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id);

	public List<Map<String,Object>> getAllhospitaldetails(int institute_id);

	public List<Map<String,Object>> getAllhospital_Paramedical_details(int institute_id);

	public Object getAllhospital_auxillarystaff_details(int institute_id);

	public ArrayList<ArrayList<String>> getInstitute_id(String user_id);
	
	public List<Map<String,Object>> getMedicalPostList();
	public List<Map<String,Object>> getParaMedicalPostList();
	public List<Map<String,Object>> getAuxillaryPostList(); 

}
