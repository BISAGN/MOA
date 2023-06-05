package com.AyushEdu.dao.Curriculum_Mstr;

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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR;

@Repository
public class CC_D3_Scope_Of_Understanding_Mstr_DaoImpl implements CC_D3_Scope_Of_Understanding_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableScopeDataTotalCount(String Search, String scope,String status,String system_id,String role) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, scope,system_id);
		
		int total = 0;
		String q = null;
		String sd = "";
		if(role.contains("NCISM")) {
			sd = " and d.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and d.system_id = 45 ";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			if(Search.equals("") && scope.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by scope ) as ser,d.id,d.scope,d.status,d.system_id,s.system_name\n"
					+ "from edu_cc_tb_d3_scope_understanding_mstr d\n"
					+ "inner join edu_lms_system_mstr s on s.id= d.system_id\n"
					+ " where d.status='1' \n "  + sd
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by scope ) as ser,d.id,d.scope,d.status,d.system_id,s.system_name\n"
						+ "from edu_cc_tb_d3_scope_understanding_mstr d\n"
						+ "inner join edu_lms_system_mstr s on s.id= d.system_id\n"
						+ "where d.id!=0 and d.status='"+ status +"' \n" + sd
						+ SearchValue + ") ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, scope,system_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String scope,String system_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(d.scope) like ? or upper(s.system_name) like ?)";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( scope != null && !scope.equals("")) {
			SearchValue += " and upper(scope) like ? ";
		
	     }
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and (system_id) = ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String scope,String system_id) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (scope != null && !scope.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+scope.toUpperCase()+"%");
			}
			
			if (system_id != null && !system_id.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableScopeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String scope, String status,String system_id ,String role) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, scope,system_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and d.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and d.system_id = 45 ";
		}
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && scope.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by scope ) as ser,d.id,d.scope,d.status,d.system_id,s.system_name\n"
						+ "from edu_cc_tb_d3_scope_understanding_mstr d\n"
						+ "inner join edu_lms_system_mstr s on s.id= d.system_id\n"
						+ " where d.status='1' \n"
						+ " " + sd + SearchValue + " ORDER BY scope " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by scope ) as ser,d.id,d.scope,d.status,d.system_id,s.system_name\n"
					+ "from edu_cc_tb_d3_scope_understanding_mstr d\n"
					+ "inner join edu_lms_system_mstr s on s.id= d.system_id\n"
					+ " where d.status='"+ status +"'" + sd  + SearchValue + " ORDER BY scope " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, scope,system_id);
			System.err.println("stmt"+stmt);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDScope' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdSCOPE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approsAGE"+countFunction+"' value='"+rs.getString("system_id")+"' >"
								+"<input type='hidden' id='approfSCOPE"+countFunction+"' value='"+rs.getString("scope")+"'>"
								+"<input type='hidden' id='apstatusSCOPE"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";
				
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
	
	public CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR getScopeByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR updateid = (CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR) session.get(CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	public String updateScope(CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_D3_SCOPE_OF_UNDERSTANDING_MSTR set system_id=:system_id,scope=:scope,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", (obj. getSystem_id())).setParameter("scope", obj.getScope()).setParameter("status", (obj. getStatus()))
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
