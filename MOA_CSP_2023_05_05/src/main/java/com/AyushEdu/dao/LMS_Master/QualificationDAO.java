package com.AyushEdu.dao.LMS_Master;


import java.util.ArrayList;

import com.AyushEdu.Models.LMS_Master.recr_qualification_mst;

public interface QualificationDAO {

	
	
	public ArrayList<ArrayList<String>> getReportQualification();
	public String updateQualification(recr_qualification_mst obj);
	public String deleteQualificationUrlAdd(int appid, String username);
}
