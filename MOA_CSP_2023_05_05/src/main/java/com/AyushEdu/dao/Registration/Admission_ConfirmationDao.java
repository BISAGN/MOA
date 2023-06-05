package com.AyushEdu.dao.Registration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


public interface Admission_ConfirmationDao {
	
	public List<Map<String, Object>> AdmissionConfirmationSearchReport(String userid,String dob,String name,String aadhar_card,String verified_status);

	public String Approve_From_Admission_Confirmation_Student_Data(String id,HttpSession session);

	public String Reject_From_Admission_Confirmation_Student_Data(String a,String username,String userId_reject,HttpSession session);
	
	public String Enable_to_Edit_Admission_Student_Data(String a,String username,HttpSession session);

	public List<Map<String, Object>> Late_AdmissionConfirmationSearchReport(String userid,String dob,String name,String aadhar_card,String verified_status,String late_admission_status,String role);

	public String Forward_Student_To_Commission_NCH_Data(String string,HttpSession session);
	
	
	public String Approve_Student_Admission_By_Commission_NCH_DATA(String id,HttpSession session);

	public String Reject_Approve_Student_Admission_By_Commission_NCH_Data(String id,String reject_remarks,HttpSession session);

	public ArrayList<ArrayList<String>> get_StudentName_by_commReject_DATA(String id);
	
	public String get_StudentRoleupdateatverify(String role, String studentId);
	
	
}