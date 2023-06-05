package com.AyushEdu.controller.LMS_Mobile_API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.dao.ChangePasswordDAO;

@Service
@Repository
public class LMS_Student_daoImpl  implements LMS_Student_Dao  {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	public ArrayList<ArrayList<String>> getRole(String userId){
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q="select *  from roleinformation ri INNER JOIN userroleinformation li ON li.role_id = ri.role_id "
					+ "WHERE li.user_id = "+userId+"";
			PreparedStatement stmt = conn.prepareStatement(q); 
			System.err.println("stmt---"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("role"));// 0
				alist.add(rs.getString("role_id"));//1
				list.add(alist);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
}}
