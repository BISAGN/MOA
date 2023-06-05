package com.AyushEdu.dao.Registration.Postgraduate;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;


public interface Personaldetails_PG_DAO {
	
	public String UpdateRegid_pg(int reg_id,int lid);
	public ArrayList<ArrayList<String>> getPersonaldetails_pg(int userid,HttpSession session);
	public Integer getUsername_pg(String username);
	 public ArrayList<ArrayList<String>> getAllPersdetails_pg(int userid);
	 public String getUpdatePerDetails_pg(EDU_PG_REG_PERSONAL_DETAILS obj);
	 public Integer getUserRegId_pg(String username); 
	 public ArrayList<ArrayList<String>> getInstID_pg(int p_id);
	 public String getMaxAID_pg();
	 
	 ////////rajdip  29/06
	 
	public ArrayList<ArrayList<String>> getBesicdetails_pg(int userid, String staff_lvl);
	public ArrayList<ArrayList<String>> get_p_id_pers_info_data_pg(int userid);
	public ArrayList<ArrayList<String>> get_ayush_id_data_pg(String userid,String staff_lvl);
}
