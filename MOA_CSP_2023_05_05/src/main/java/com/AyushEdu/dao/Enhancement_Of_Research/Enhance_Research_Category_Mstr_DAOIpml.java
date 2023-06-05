package com.AyushEdu.dao.Enhancement_Of_Research;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_CATEGORY_MSTR;

@Repository
public class Enhance_Research_Category_Mstr_DAOIpml implements Enhance_Research_Category_Mstr_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> Enhance_Research_Category_Mstr_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String category_name, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, category_name, status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			q = " select ROW_NUMBER() OVER(order by id ASC) as ser,id,category_name from tb_enhance_research_category_mstr where status=1 "
					+ SearchValue + " ORDER BY id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;


			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, category_name,  status);
	System.err.println("stmt======================"+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
	
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
	
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm Edit_Enhance_Research_Category_Mstr' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EDIT_ENH_RES_CATE_MSTR_ID" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteEnhance_Research_Category_Mstr' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DLT_ENH_RES_CATE_MSTR_ID" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
	
				ul += f + " " + f1;
				ul += "</ul>";
	
				countFunction += 1;
				countFunctionDelete += 1;
	
				action = ul;
				columns.put("action", action);
				list.add(columns);

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
				}
			}
		}
		return list;
	}

	@Override
	public long DataTotalEnhance_Research_Category_MstrCount(String search, String category_name, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, category_name, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,category_name from tb_enhance_research_category_mstr where status=1 "
					+ SearchValue + ")a";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, category_name, status);
			System.err.println("stmt======================"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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
				}
			}
		}
		return (long) total;
	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,String category_name, String status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(category_name) like ?) ";
		}
		
		if (!category_name.trim().equals("")) {
			SearchValue += " and upper(category_name) like ? ";
		}
		if (!status.equals("0")) {
			SearchValue += " and status = ? ";

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String category_name, String status) {

		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!category_name.equals("") && category_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + category_name.toUpperCase() + "%");
			}
			if (!status.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(status));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	
	public TB_ENHANCE_RESEARCH_CATEGORY_MSTR getEnhance_Research_Category_MstrByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_ENHANCE_RESEARCH_CATEGORY_MSTR updateid = (TB_ENHANCE_RESEARCH_CATEGORY_MSTR) session.get(TB_ENHANCE_RESEARCH_CATEGORY_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}


}
