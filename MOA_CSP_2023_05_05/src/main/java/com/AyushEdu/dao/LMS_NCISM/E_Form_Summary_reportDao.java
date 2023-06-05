package com.AyushEdu.dao.LMS_NCISM;

import java.util.ArrayList;

public interface E_Form_Summary_reportDao {
	public ArrayList<ArrayList<String>> getEformSummaryReport(String institute1,String category1,String authority1,String quota1,
			String role,String roleStaff_lvl);
	public ArrayList<ArrayList<String>> getEformSummaryVacantReport(String institute1,String category1,String authority1,String quota1,String role,String roleStaff_lvl);
	
	public ArrayList<ArrayList<String>> getEformSummaryReportPG(String institute1,String category1,String authority1,String quota1,
			String role,String roleStaff_lvl,String degree1,String subject1,String intake1);
	
	
	public ArrayList<ArrayList<String>> getEformSummaryVacantReportPG(String institute1, String category1, String authority1,
			String quota1,String role,String roleStaff_lvl,String degree1,String subject1,String intake1);

	
}
