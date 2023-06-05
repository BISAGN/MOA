package com.AyushEdu.dao.Registration.Graduation_NCISM;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;

public interface Std_Pers_Details_NCISMDao {

	public EDU_NCISM_REG_GRADU_PERSONAL_DTLS getStudDetView_NcismByid(int id);
	public ArrayList<ArrayList<String>> getEdu_Detail_Ncism_data(String p_id);
	public ArrayList<ArrayList<String>> get_ayush_idbypid_Ncism_data(String pid);
	public ArrayList<ArrayList<String>> getuploaded_doc_Ncism_data(String pid);
	public String update_verify_at_final_submit_ncism(String p_id, String system_id, HttpSession sessionEdit);
	public ArrayList<ArrayList<String>> getuploaded_Court_Order_NCISM_data(String p_id);

}
