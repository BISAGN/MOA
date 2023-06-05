package com.AyushEdu.dao.Part_One;

import java.util.List;
import java.util.Map;

public interface Clg_Reg_College_Staff_List_ReportDAO {

	public List<Map<String, Object>> getClg_Staff_List_Teacher_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getClg_Staff_List_Guest_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getClg_Staff_List_Non_Teaching_View(int id, int inst_id, String role);

	public List<Map<String, Object>> getClg_Staff_List_Upload_Doc_View(int id, int institute_id, String role);

}
