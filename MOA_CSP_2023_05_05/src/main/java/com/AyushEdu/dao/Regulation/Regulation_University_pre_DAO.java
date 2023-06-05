package com.AyushEdu.dao.Regulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Regulation_University_pre_DAO {
	
	public String getImagePath1(String id);

	public List<Map<String, Object>> getDataByUserNameForDraftPreview(int userid);

	//public List<Map<String, Object>> CheckNRHPreview(int data);

	public List<Map<String, Object>> RegAuthPreview(int data);

	public String getUserIdPreview(int data);

	public ArrayList<ArrayList<String>> medicalDataPreview(int data   ,int d);

	public ArrayList<ArrayList<String>> HospitalDataPreview(int data);

	public List<Map<String, Object>> getdegreedetailssPreview(String DegreeName);

	public List<Map<String, Object>> getayusAbhaDatalistPreview(String userId);

	public List<Map<String, Object>> medicalDataChildAttachmentPreview(int userid);

	public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportPreviewDataList();

	public String getFilePathQuery_popup1(int id);

	
	public String University_Approve_DegreeData(String a ,String status ,String u_id ,int u_id_n, String cp ) ;
	public String University_Reject_DegreeData(String a ,String status ,String u_id) ;
	
	public ArrayList<ArrayList<String>> get_degrrename_Reject_idata(String id) ;
	
	public String getFilePathQueryForDoc(String id);
	
	public ArrayList<ArrayList<String>> getattfilesToPreviewD(String userid,String id);
 
}