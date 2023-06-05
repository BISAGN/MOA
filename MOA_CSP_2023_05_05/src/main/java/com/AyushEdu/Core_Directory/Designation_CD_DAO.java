package com.AyushEdu.Core_Directory;

import java.util.Date;
public interface Designation_CD_DAO {
	public void Insert_designation_Mstr_Data(int id);
	public Boolean  Update_designation_Mstr_Data(int id, String designation
			,int status, String modified_by, Date modified_date);
	public Boolean  Delete_designation_Mstr_Data(int id);
}
