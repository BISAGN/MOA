package com.AyushEdu.dao.LMS_Master;

import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;

public interface NationalityDAO {

	public recr_nationality_mst getNationalityByid(int updateid);

	public String UpdateNationalityValue(recr_nationality_mst updateid, String username);

	public String deleteNationalityUrlAdd(int appid, String username);

}
