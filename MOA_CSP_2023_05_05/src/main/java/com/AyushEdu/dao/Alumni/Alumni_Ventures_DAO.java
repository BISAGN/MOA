package com.AyushEdu.dao.Alumni;

import java.util.List;

import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_POST;

public interface Alumni_Ventures_DAO {

	public void SaveAlumVenturesData(EDU_ALUM_ALUMNI_POST alum_ven, String actiontpe);
	public List<EDU_ALUM_ALUMNI_POST> Loadalum_ventureData(int userid, String data);
	public List<EDU_ALUM_ALUMNI_POST> LoadalumVenturesDataForCount(int userid);
	public EDU_ALUM_ALUMNI_POST GetAlumVenturesDataByID(int id) ;
	public List CheckTitleNameExist(String title) ;
}
