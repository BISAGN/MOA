package com.AyushEdu.dao.Alumni;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

public interface Knowledge_Repo_DAO {
	
	public List<Map<String, Object>> getSearchDetails(String a);

}
