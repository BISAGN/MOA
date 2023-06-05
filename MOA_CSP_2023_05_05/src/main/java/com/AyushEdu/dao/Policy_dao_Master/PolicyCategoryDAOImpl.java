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

import com.AyushEdu.Models.Policy_Model_Master.TB_POLICYCATEGORY_MASTER;



@Repository
public class PolicyCategoryDAOImpl implements PolicyCategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTablepolicycategoryDataTotalCount(String Search, String policycategory) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, policycategory);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			q = "select count(*) \n" + " from edu_ply_mst_policycategory where id!=0 " + SearchValue;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, policycategory);

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
	public String GenerateQueryWhereClause_SQL(String Search, String policycategory) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(policycategory) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if(SearchValue.contains("and") && !policycategory.equals("")) {
			SearchValue += " and upper(policycategory) like ? ";
		
	     }
	
	   if(!SearchValue.contains("and") && !policycategory.equals("")){
			SearchValue += " and upper(policycategory) like ? ";
			System.err.println("parameter search"+SearchValue);
		
	
	    }


		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String policycategory) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (!policycategory.equals("") && policycategory != null) {
				flag += 1;
				stmt.setString(flag,"%"+policycategory.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	
	public TB_POLICYCATEGORY_MASTER getPolicycategoryByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_POLICYCATEGORY_MASTER updateid = (TB_POLICYCATEGORY_MASTER) session.get(TB_POLICYCATEGORY_MASTER.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }



	public List<Map<String,Object>> DataTablepolicycategoryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String policycategory, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, policycategory);
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

			if(Search.equals("") && policycategory.equals("0") && status=="1") {
				q = "select id,pol\n"
						+ "	public List<Map<String, Object>> DataTablepolicycategoryDataList(int starticycategory,status from edu_ply_mst_policycategory where status= '1'  "  + " ORDER BY policycategory " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,policycategory,status from edu_ply_mst_policycategory where status='"+ status +"'"  + SearchValue + " ORDER BY policycategory " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, policycategory);
			ResultSet rs = stmt.executeQuery();
			

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

				
			
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"','"+ rs.getString("policycategory") +"','"+ rs.getString("status") +"') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o'  " + ADD + " title='Edit Data'></i>";
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f+" "+f1;
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
	
	
	public TB_POLICYCATEGORY_MASTER getcategorypolicyByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_POLICYCATEGORY_MASTER updateid = (TB_POLICYCATEGORY_MASTER) session.get(TB_POLICYCATEGORY_MASTER.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }

}
