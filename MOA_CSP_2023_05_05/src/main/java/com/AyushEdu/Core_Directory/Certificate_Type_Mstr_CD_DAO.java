package com.AyushEdu.Core_Directory;

import java.util.Date;

public interface Certificate_Type_Mstr_CD_DAO {
	
	public void Insert_certi_type_Mstr_Data(int id);
	public Boolean  Update_certi_type_Mstr_Data(int id ,String certi_type
			,int status, String modified_by, Date modified_date);
	public Boolean  Delete_certi_type_Mstr_Data(int id);
}
