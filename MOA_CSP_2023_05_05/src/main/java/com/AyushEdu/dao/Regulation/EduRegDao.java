package com.AyushEdu.dao.Regulation;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;

public interface EduRegDao {
 
	
	//----het
		public List<Map<String, Object>> getDataByUserNameForDraft(int userid);
		public ArrayList<ArrayList<String>> medicalData(int data);
		public List<Map<String, Object>> medicalDataChildAttachment(int data);
		public ArrayList<ArrayList<String>> HospitalData(int data);
		//public List<Map<String, Object>> CheckNRH(int data);
		public List<Map<String, Object>> RegAuth(int data);
		public String getUserId(int data);
		public REG_NCH_FORM_A_P getEdu_RegByid(int parseInt);
		String getImagePath(String id);
		public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportDataList();
		
//		------------------22/06/2022 urmik
		public List<Map<String, Object>> getdegreedetailss(String DegreeName);
		
		
		public List<Map<String, Object>> getayusAbhaDatalist(String userId) ;
		
		//--download individual attachment 
		public String getIndivisualAttachmentPath(int id) ;
		
		public String get_AttachmentIndividual(int id);
		
		public List<Map<String, Object>> getayusAbhaDatalistStudent(String userId) ;
		
		
		
		
}
