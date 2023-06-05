package com.AyushEdu.dao.Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Regulation_previewDAO {

	public String getImagePath1(String id);

	public List<Map<String, Object>> getDataByUserNameForDraftPreview(int userid);

	//public List<Map<String, Object>> CheckNRHPreview(int data);

	public List<Map<String, Object>> RegAuthPreview(int data);

	public String getUserIdPreview(int data);

	public ArrayList<ArrayList<String>> medicalDataPreview(int data);

	public ArrayList<ArrayList<String>> HospitalDataPreview(int data);

	public List<Map<String, Object>> getdegreedetailssPreview(String DegreeName);

	public List<Map<String, Object>> getayusAbhaDatalistPreview(String userId);

	public List<Map<String, Object>> medicalDataChildAttachmentPreview(int userid);

	public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportPreviewDataList();

	public String getFilePathQuery_popup1(int id);
	

}
