package com.AyushEdu.controller.LMS_Institute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.dao.LMS_Institute.Institute_Registration_Dao;

@Controller
public class TestInstitute {
	@Autowired
	Institute_Registration_Dao rd; 
	
	@GetMapping(value = "/AyushCollegeData")
	public @ResponseBody List<Map<String, Object>> AyushCollegeData() {
		List<Map<String, Object>> list = rd.GetAyushCollegeDataDetails();
		return list;
	}
}
