package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Registration.TB_COLLAGE_MSTR;

@Repository
public class CollageMstrDaoImpl implements CollageMstrDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<ArrayList<String>> DataTablecollageDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String collage_name, String collage_code,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, collage_name, collage_code,status);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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
			
			q = "select distinct ROW_NUMBER() OVER(order by d.id) as sr_no, d.id,d.collage_name,d.collage_code,d.status\n"
					+ "from tb_collage_mstr d where d.id!= 0 " + SearchValue + " ORDER BY d.id " + orderType + " limit " + pageL + " OFFSET "
					+ startPage; // "+orderColunm +"

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,collage_name, collage_code,status);
			
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;
			
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Collage Data ?') )"
						+ "{editData('"+ rs.getString("id") +"','"+rs.getString("collage_name")+"','"+rs.getString("collage_code")+"','"+rs.getString("status")+"')}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Data?') )"
						+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				
				action = f + f2;
				
				list.add(rs.getString("sr_no"));
				list.add(rs.getString("collage_name"));//1
				list.add(rs.getString("collage_code"));//2
				
//				list.add(f1);
				list.add(action);
				
				i++;
					alist.add(list);
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
		
		return alist;
	}
	
	@Override
	public long DataTablecollageDataTotalCount(String Search, String collage_name,String collage_code,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,collage_name,collage_code,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) \n" + "from tb_collage_mstr where id!=0 " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,collage_name, collage_code,status);

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
	
	public String GenerateQueryWhereClause_SQL(String Search, String collage_name,String collage_code,String status) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and (  upper(d.collage_name) like ? "
					+ "  or d.collage_code like ?)";
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String collage_name,String collage_code,String status) {
		
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!collage_name.equals("") && collage_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + collage_name.toUpperCase() + "%");
			}

			if (!status.equals("0") && status != null) {
				flag += 1;
				stmt.setString(flag, "%" + status.toUpperCase() + "%");
			}
			
			if (!collage_code.equals("") && collage_code != null) {
				flag += 1;
				stmt.setString(flag, "%" + collage_code.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public String updateCollagedata(TB_COLLAGE_MSTR obj){
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update TB_COLLAGE_MSTR set collage_name=:collage_name,collage_code=:collage_code,status=:status,modified_by=:modified_by,modified_date=:modified_date where id=:id";		
			
			@SuppressWarnings("rawtypes")
			Query query = sessionHQL.createQuery(hql)
					.setParameter("collage_name",obj.getCollage_name())
					.setParameter("collage_code",obj.getCollage_code())
					.setParameter("status",obj.getStatus())
					.setParameter("modified_by",obj.getModified_by())
					.setParameter("modified_date",obj.getModified_date())
					.setParameter("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	}
}
