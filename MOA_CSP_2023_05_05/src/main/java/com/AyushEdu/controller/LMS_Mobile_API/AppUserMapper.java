package com.AyushEdu.controller.LMS_Mobile_API;

import java.sql.ResultSet;
import java.sql.SQLException;
 

import org.springframework.jdbc.core.RowMapper;

import com.AyushEdu.Models.UserLogin;

 
public class AppUserMapper implements RowMapper<UserLogin> {
 
    public static final String BASE_SQL //
            = "Select u.userid, u.username, u.password ,u.login_name From logininformation u ";
 
    @Override
    public UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        int userId = rs.getInt("userid");
        String userName = rs.getString("username");
        String encrytedPassword = rs.getString("password");
        String login_name = rs.getString("login_name");
 
        return new UserLogin(userId, userName, encrytedPassword,login_name);
    }
 
}
