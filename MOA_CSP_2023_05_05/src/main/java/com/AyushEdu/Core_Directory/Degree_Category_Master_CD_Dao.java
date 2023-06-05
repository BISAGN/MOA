package com.AyushEdu.Core_Directory;

import java.util.Date;
public interface Degree_Category_Master_CD_Dao {
	
	public void Insert_Degree_Cate_Mstr_Data(int id);
	public Boolean  Update_Degree_Cate_Mstr_Data(int id, String degree_cate, int status
			, String modified_by, Date modified_date);
	public Boolean  Delete_Degree_Cate_Mstr_Data(int id);
	
}
