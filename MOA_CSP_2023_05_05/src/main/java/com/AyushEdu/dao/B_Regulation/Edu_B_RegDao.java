package com.AyushEdu.dao.B_Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.AyushEdu.Models.B_Regulation.TB_EDU_B_REGULATION;

public interface Edu_B_RegDao {

	public List<Map<String, Object>> getDataByUserNameForDraft(int userid);

	public ArrayList<ArrayList<String>> medicalData(int data);

	public Object HospitalData(int data);

	public List<Map<String, Object>> CheckNRH(int data);

	public List<Map<String, Object>> RegAuth(int data);

	public String getUserId(int data);

	public String getImagePath(String id);

	public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportDataList();

	public List<Map<String, Object>> getdegreedetailsb(String typeofdegree);

	public TB_EDU_B_REGULATION getEdu_b_RegByid(int parseInt);



}
