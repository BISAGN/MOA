package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;


public interface personaldetailsDAO {
	public String UpdateRegid(int reg_id,int lid);
	public ArrayList<ArrayList<String>> getPersonaldetails(int userid,HttpSession session);
	public Integer getUsername(String username);
	public ArrayList<ArrayList<String>> getAllPersdetails(int userid);
	public String getUpdatePerDetails(TB_PERSONAL_DETAILS obj);
	public Integer getUserRegId(String username); 
	public ArrayList<ArrayList<String>> getInstID(int p_id);
	public String getMaxAID();
	 
	 ////////rajdip  29/06
	public ArrayList<ArrayList<String>> getBesicdetails(int userid);
	public ArrayList<ArrayList<String>> get_p_id_pers_info_data(int userid);
	public ArrayList<ArrayList<String>> get_ayush_id_data(String userid);
	public ArrayList<ArrayList<String>> getInstituteLogin(int userid);
}
