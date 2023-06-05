package com.AyushEdu.dao.LMS_Institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class Link_faculty_Course_DAOIMPL implements Link_faculty_Course_DAO {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	///course duration list
		public ArrayList<ArrayList<String>> get_faculty_name_list_Fetch(SessionFactory sessionFactory,String userId,String institute_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
				q="select lo.userid,login_name from logininformation lo\n"
						+ "inner join userroleinformation us on lo.userid=us.user_id\n"
						+ "inner join roleinformation ro on ro.role_id=us.role_id and role='Faculty_NCISM' and lo.institute_id=?  ";
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1, Integer.parseInt(institute_id));
			
				ResultSet rs = stmt.executeQuery();
			
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("userid"));// 0
					alist.add(rs.getString("login_name"));// 1
					
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
		}

}
