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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR;


@Repository
public class CC_F3_Teaching_Learning_Method_Mstr_DaoImpl implements CC_F3_Teaching_Learning_Method_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public List<Map<String, Object>> DataTableF3_Teaching_Learning_MethodDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String method, String status,String role) {
		// TODO Auto-generated method stub
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, method);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and f.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and f.system_id = 45 ";
			}
			
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && system_id.equals("0") && method.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by method ) as ser,f.id,f.method,f.status,f.system_id,s.system_name\n"
						+ "from edu_cc_tb_f3_teaching_learning_method_mstr f\n"
						+ "inner join edu_lms_system_mstr s on s.id= f.system_id\n"
						+ "where f.id!=0 and f.status='1'" + sd  + SearchValue + " ORDER BY method " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by method ) as ser,f.id,f.method,f.status,f.system_id,s.system_name\n"
					+ "from edu_cc_tb_f3_teaching_learning_method_mstr f\n"
					+ "inner join edu_lms_system_mstr s on s.id= f.system_id where f.status='"+ status +"'" + sd + SearchValue + " ORDER BY method " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id, method);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDF3_Teaching_Learning_Method' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfsys"+countFunction+"' value='"+rs.getString("system_id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("method")+"'>"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value='"+rs.getString("status")+"'></i></a> </li>";
				
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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String system_id,String method) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ? or upper(f.method) like ? ) ";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and f.system_id  = ? ";
		}
		if( method != null && !method.equals("")) {
			SearchValue += " and upper(f.method) like ? ";
		
	     }
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id, String method) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if( system_id != null && !system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (method != null && !method.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+method.toUpperCase()+"%");
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public long DataTableF3_Teaching_Learning_MethodDataTotalCount(String Search,String system_id, String method, String status,String role) {
		// TODO Auto-generated method stub
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, method);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and f.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and f.system_id = 45 ";
			}
			
			conn = dataSource.getConnection();
			
			if(Search.equals("") && system_id.equals("0") && method.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by method ) as ser,f.id,f.method,f.status,f.system_id,s.system_name\n"
					+ "from edu_cc_tb_f3_teaching_learning_method_mstr f\n"
					+ "inner join edu_lms_system_mstr s on s.id= f.system_id\n"
					+ "where f.id!=0 and f.status='1' \n" + sd 
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by method ) as ser,f.id,f.method,f.status,f.system_id,s.system_name\n"
						+ "from edu_cc_tb_f3_teaching_learning_method_mstr f\n"
						+ "inner join edu_lms_system_mstr s on s.id= f.system_id\n"
						+ "where f.id!=0 and f.status='"+ status +"' \n" + sd
						+ SearchValue + ") ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id, method);

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

	@Override
	public String updateF3_Teaching_Learning_Method(CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_F3_TEACHING_LEARNING_METHOD_MSTR set system_id=:system_id, method=:method,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id()).setParameter("method", obj.getMethod()).setParameter("status", (obj. getStatus()))
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
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

