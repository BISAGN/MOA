package com.AyushEdu.dao.Registration.Graduation_NCISM;

import java.util.ArrayList;

import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;


public interface PersonaldetailsNCISMDAO {
	public String UpdateNcism_Regid(int reg_id,int lid);
//	public ArrayList<ArrayList<String>> getNcism_Personaldetails(int userid,HttpSession session);
	public Integer getNcism_Username(String username);
	public ArrayList<ArrayList<String>> getAllNcism_Persdetails(int userid);
	public String getUpdateNcism_PerDetails(EDU_NCISM_REG_GRADU_PERSONAL_DTLS obj);
	public Integer getUserNcism_RegId(String username); 
	public ArrayList<ArrayList<String>> getNcism_InstID(int p_id);
	public String getNcism_MaxAID();
	 ////////rajdip  29/06
	public ArrayList<ArrayList<String>> getBesicNcism_details(int userid);
	public ArrayList<ArrayList<String>> get_p_id_Ncism_pers_info_data(int userid);
	public ArrayList<ArrayList<String>> get_ayush_id_Ncism_data(String userid);
	public ArrayList<ArrayList<String>> getInstituteNcism_Login(int userid);
}
