package com.AyushEdu.dao.Policy_dao_Master;

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

import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;

@Repository
public class SubcategoryDAOImpl implements SubcategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTablesubcategoryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String subcategory, String status,String policycategory ) {
//System.err.println("policy cat ----"+policycategory);
		String SearchValue = GenerateQueryWhereClause_SQL(Search, subcategory,policycategory);
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

			if (Search.equals("") && subcategory.equals("") && status =="1") {
//				q = "select id,pm.policycategory,subcategory,status from tb_subpolicycategory_mstr a"
//						+ "inner join tb_policycategory_mstr pm on pm.id = a.policycategory  where status= '1'  " + " ORDER BY subcategory " + orderType
//						+ " limit " + pageL + " OFFSET " + startPage;
				q=" select a.id,pm.policycategory,a.subcategory,a.status \n"
						+ " from edu_ply_mst_subpolicycategory a\n"
						+ " inner join edu_ply_mst_policycategory pm on pm.id = a.policycategory::integer\n"
						+ " where a.status= '1' \n"
						  + " ORDER BY subcategory " + orderType
							+ " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select a.id,pm.policycategory,a.subcategory,a.status "
						+ "	 from edu_ply_mst_subpolicycategory a "
						+ " inner join edu_ply_mst_policycategory pm on pm.id = a.policycategory::integer"
						+ " where a.status= '"+ status +"'"  + SearchValue + " ORDER BY subcategory "
						+ orderType + " limit " + pageL + " OFFSET " + startPage;
			}


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, subcategory,policycategory);
			ResultSet rs = stmt.executeQuery();
//System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "','" + rs.getString("subcategory") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f + " " + f1;
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
	public long DataTablesubcategoryDataTotalCount(String Search, String subcategory, String status,String policycategory) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, subcategory,policycategory);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			q = "select count(*) \n" + " from edu_ply_mst_subpolicycategory a where id!=0 " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, subcategory,policycategory);

			
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
	public String GenerateQueryWhereClause_SQL(String Search, String subcategory,String policycategory) {
		//System.err.println("policy cat --2--"+policycategory);
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(subcategory) like ? )";
			System.err.println("globalllll search" + SearchValue);

		}

		/// advance search

		if (!subcategory.trim().equals("")) {
			SearchValue += " and upper(subcategory) like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		
		if (!policycategory.trim().equals("")) {
			SearchValue += " and upper(a.policycategory) like ? ";
			System.err.println("parameter search" + SearchValue);

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String subcategory,String policycategory) {
		//System.err.println("policy cat ---3-"+policycategory);
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!subcategory.equals("") && subcategory != null) {
				flag += 1;
				stmt.setString(flag, "%" + subcategory.toUpperCase() + "%");
			}
			
			if (!policycategory.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + policycategory.toUpperCase() + "%");
			}

			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public TB_SUBPOLICYCATEGORY getsubpolicyByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_SUBPOLICYCATEGORY updateid = (TB_SUBPOLICYCATEGORY) session.get(TB_SUBPOLICYCATEGORY.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	@Override
	public TB_SUBPOLICYCATEGORY getSubcategoryByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_SUBPOLICYCATEGORY updateid = (TB_SUBPOLICYCATEGORY) session.get(TB_SUBPOLICYCATEGORY.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
	
	
	//edit policy category ............by ruler
	
	@Override
	public ArrayList<ArrayList<String>> getpolicycategory(int id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "SELECT psub.id,cat.policycategory as catname,psub.subcategory,psub.policycategory,psub.status  FROM public.edu_ply_mst_subpolicycategory psub\n"
					+ "INNER join edu_ply_mst_policycategory cat on cat.id::text = psub.policycategory\n"
					+ "where psub.id= ? \n"
					+ "";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			//System.err.println("---------stmt----" + stmt);

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();


				alist.add(rs.getString("catname"));// 0
				alist.add(rs.getString("policycategory"));// 1
				alist.add(rs.getString("subcategory"));// 2
				alist.add(rs.getString("status"));// 3
				
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
	
	//end

}
