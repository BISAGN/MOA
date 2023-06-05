package com.AyushEdu.Core_Directory;

import java.util.Date;
public interface Term_CD_Dao {
	
	public void Insert_Term_Mstr_Data(int id);
	public Boolean  Update_Term_Mstr_Data(int id, String term
		,	String status,String	prof_name, String modified_by, Date modified_date);
	public Boolean  Delete_Term_Mstr_Data(int id);
	
}
