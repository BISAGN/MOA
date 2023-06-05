package com.AyushEdu.dao.Degree_recognition_Fellowship_Grant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_OUT_INDIA;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;

public interface Fellow_ship_HOM_out_IndiaDAO {
	

	public DG_REC_FELLOW_SHIP_HOM_OUT_INDIA getfellow_ship_hom_out_indiaByid(int id);


   public	List<Map<String, Object>> DataTableFellow_ship_hom_out_india_Details_DataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String country_id, String university_name,
			String college_id, String abbreviation,String qualification, String validity_period, String digital_code);
   
	public long DataTableFellow_ship_hom_out_india_Details_DataTotalCount(String search, String country_id, String university_name,
			String college_id, String abbreviation, String qualification,String validity_period, String digital_code);
	
	String updateFellow_ship_hom_out_india(DG_REC_FELLOW_SHIP_HOM_OUT_INDIA obj);
	

}
