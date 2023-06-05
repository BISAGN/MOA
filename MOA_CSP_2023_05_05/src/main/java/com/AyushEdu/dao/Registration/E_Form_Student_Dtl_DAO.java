package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

public interface E_Form_Student_Dtl_DAO {

	public ArrayList<ArrayList<String>> E_Form_Student_DtlData(String role, String institute_id);
	
	public ArrayList<ArrayList<String>>FilledDataofStudents(String inst_id,String role);
	
	public ArrayList<ArrayList<String>>getSeatcountsbyPGSubject(String degree,String subject,String inst_id);
	
	public ArrayList<ArrayList<String>>E_Form_Student_DtlDataPG(String role,String id);
	
	public ArrayList<ArrayList<String>>FilledDataofStudentsPG(String inst_id,String role,String subject, String intake_id);
	
	public ArrayList<ArrayList<String>>getPGDashboardstatusbySubject(String inst_id,String subject);

}
