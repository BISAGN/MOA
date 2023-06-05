package com.AyushEdu.dao.LMS_Master;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_PREFIX_MSTR;

@Repository
public class Prefix_Mstr_DAOImpl implements Prefix_Mstr_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTablePrefixDataTotalCount(String Search, String prefix) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, prefix);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select id,prefix,status  from tb_nch_prefix_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, prefix);

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
	public String GenerateQueryWhereClause_SQL(String Search, String prefix) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(prefix) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( prefix != null && !prefix.equals("")) {
			SearchValue += " and upper(prefix) like ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String prefix) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (prefix != null && !prefix.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+prefix.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTablePrefixDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String prefix, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, prefix);
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

			if(Search.equals("") && prefix.equals("0") && status=="1") {
				q = "select id,prefix,status from tb_nch_prefix_mstr where status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,prefix,status from tb_nch_prefix_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, prefix);
//			System.err.println("stmt--->"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDPrefix' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value="+rs.getString("prefix")+">"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
	
	public TB_NCH_PREFIX_MSTR getPrefixByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_NCH_PREFIX_MSTR updateid = (TB_NCH_PREFIX_MSTR) session.get(TB_NCH_PREFIX_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updatePrefix(TB_NCH_PREFIX_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update TB_NCH_PREFIX_MSTR set prefix=:prefix,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			 Query query = session1.createQuery(hql).setParameter("prefix", obj.getPrefix()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
}