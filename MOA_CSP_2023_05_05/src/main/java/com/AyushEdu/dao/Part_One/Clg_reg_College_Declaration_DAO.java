package com.AyushEdu.dao.Part_One;

import java.util.ArrayList;

public interface Clg_reg_College_Declaration_DAO {

	public ArrayList<ArrayList<String>> GetDocument_Details(String institute_id);
	public String getAttachmentFilePath(String id,String doc_id);
	
}
