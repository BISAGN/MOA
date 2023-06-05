package com.AyushEdu.dao.LMS_Master;

import java.util.ArrayList;

import com.AyushEdu.Models.LMS_Master.Document_mst;


public interface DocumentDAO {
	public ArrayList<ArrayList<String>> search_Documentmaster(String doc1);

	public String updatedoc(Document_mst obj);

}
