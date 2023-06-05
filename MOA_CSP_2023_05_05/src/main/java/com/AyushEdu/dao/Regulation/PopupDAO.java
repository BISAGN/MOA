package com.AyushEdu.dao.Regulation;

import java.util.List;
import java.util.Map;

public interface PopupDAO {
	
	public List<Map<String, Object>> getuni_DegreeDataforPopup(String id,String name_of_institute);
	
	//public List<Map<String, Object>> getDegreeDataforPopup(String id,String institute_name_hid);
	public List<Map<String, Object>> getDegreeDataforPopup(  String Search,
			String orderColunm, String orderType ,String popid,String institute_name_hid);
	
	
	public List<Map<String, Object>> getIOCHDataforPopup(String id);
	public String getFilePathQuery_popup(int id);

}
