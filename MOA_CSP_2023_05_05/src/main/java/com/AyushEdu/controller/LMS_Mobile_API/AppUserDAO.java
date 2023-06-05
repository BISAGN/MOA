package com.AyushEdu.controller.LMS_Mobile_API;

import javax.sql.DataSource;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.AyushEdu.Models.UserLogin;
 
@Repository
public class AppUserDAO extends JdbcDaoSupport {
	 
    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public UserLogin findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.BASE_SQL + " where u.username = ? ";
 
        Object[] params = new Object[] { userName };
        AppUserMapper mapper = new AppUserMapper();
        try {
        	UserLogin userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
}
