package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Category_CD_Dao {
	public void Insert_Category_Mstr_Data(int id);
	public Boolean  Update_Category_Mstr_Data(int id ,String type_of_content
			,String status, String modified_by, Date modified_date);
	public Boolean  Delete_Category_Mstr_Data(int id);
}
