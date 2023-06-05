package com.AyushEdu.dao.Registration.Postgraduate;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;

public interface Declaration_PG_Dao {

	public EDU_PG_REG_PERSONAL_DETAILS getStudDetView_PG_Byid(int id);
	public ArrayList<ArrayList<String>> getGrdu_pg_Detail_Data(String p_id);
	
	public String genrate_PG_Ayush_id_on_Submit(String userid,HttpSession session);

}
