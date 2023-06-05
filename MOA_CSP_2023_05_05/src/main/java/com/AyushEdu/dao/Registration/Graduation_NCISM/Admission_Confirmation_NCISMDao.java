package com.AyushEdu.dao.Registration.Graduation_NCISM;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;


public interface Admission_Confirmation_NCISMDao {
	
	

	public String Approve_From_Admission_Confirmation_Student_Data1(String a,String username,HttpSession session);

	public String Reject_From_Admission_Confirmation_Student_Data1(String a,String username,String userId_reject,HttpSession session);

	public List<Map<String, Object>> AdmissionConfirmationSearchReport1(String userid, String dob, String name,
			String aadhar_card, String verified_status);

	public String Enable_to_Edit_Admission_Student_NCISM_Data(String a,String username,HttpSession session);

	public List<Map<String, Object>> Late_AdmissionConfirmation_NCISMSearchReport(String userid, String dob,
			String name, String aadhar_card, String verified_status, String late_admission_status, String role);

	public String Approve_Student_Admission_By_Commission_NCISM_DATA(String id,HttpSession session);

	public String Reject_Approve_Student_Admission_By_Commission_NCISM_Data(String id,String reject_remarks,HttpSession session);
	
	public String Forward_Student_To_Commission_NCISM_Data(String id,HttpSession session);
	
	public String get_student_userid_by_excel_id_ncism(String id);
	
	public ArrayList<ArrayList<String>> get_StudentName_by_NCISM_Reject_DATA(String id); 

//	public List<Map<String, Object>> DataTableAdmission_ConfirmationDataList(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType,String name,String dob,String email,String aadhar_card);
//	
//	public long DataTableTotalAdmission_ConfirmationTotalCount(String Search, String name,String dob,String email,
//			String aadhar_card);


}

