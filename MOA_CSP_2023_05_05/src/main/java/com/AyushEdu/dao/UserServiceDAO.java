package com.AyushEdu.dao;

import java.util.List;

import com.AyushEdu.Models.UserLogin;

public interface UserServiceDAO {

	public  List<String>  getRoleByuserId(String userId);
	public UserLogin findUser(String userName);
	public UserLogin findUserByPhone(String userName);
}
