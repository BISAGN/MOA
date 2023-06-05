package com.AyushEdu.dao.Registration;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;

public interface Std_Pers_Details_Dao {

	public TB_PERSONAL_DETAILS getStudDetViewByid(int id);
	public ArrayList<ArrayList<String>> getEdu_Detail_data(String p_id);
	public ArrayList<ArrayList<String>> get_ayush_idbypid_data(String pid);
	public ArrayList<ArrayList<String>> getuploaded_doc_NCH_data(String p_id);
	public String update_verify_at_final_submit_nch(String p_id,String system_id,HttpSession session);
	public ArrayList<ArrayList<String>> getuploaded_Court_Order_NCH_data(String p_id);

}
