package com.AyushEdu.dao.Part_One;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.dao.Mentor_Mentee.Approve_Request_Dao;

@Repository
public class Clg_reg_College_Declaration_DAOImpl implements Clg_reg_College_Declaration_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	

//GET NON TEACHING FACULTY DETAILS
public ArrayList<ArrayList<String>> GetDocument_Details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,s_id,institute_id,notarizedundertaking,notarizedaffidavit,prin_declaration,mange_declaration\n"
				+ "from clg_reg_college_declaration where institute_id = ? ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("institute_id"));// 1
			alist.add(rs.getString("s_id"));// 2
			alist.add(rs.getString("notarizedundertaking"));// 3
			alist.add(rs.getString("notarizedaffidavit"));// 4
			alist.add(rs.getString("prin_declaration"));// 5
			alist.add(rs.getString("mange_declaration"));// 6

			list.add(alist);
			
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
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


public String getAttachmentFilePath(String id,String doc_id){
	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		System.err.println("ID------------================"+id);
		query = "select notarizedundertaking,notarizedaffidavit from clg_reg_college_declaration where id = ? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		System.err.println("IMAGE PATH===="+stmt);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
				if(Integer.parseInt(doc_id) == 1) {
				whr = rs.getString("notarizedundertaking");
				}
				if(Integer.parseInt(doc_id) == 2) {
					whr = rs.getString("notarizedaffidavit");
				}
				
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
}

}