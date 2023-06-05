package com.AyushEdu.dao;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.ResponseBody;

public interface ChangePasswordDAO {

	public long getUsernamevaliddata(String username);

	public ArrayList<ArrayList<String>> getForgotPassworduserdataList(String aadhaar_no);
	
	public @ResponseBody ArrayList<ArrayList<String>> getForgetPassInstName(String username,String email,String cat);
	
}
