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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_C3_DOMAIN_MSTR;

@Repository
public class CC_C3_Domain_Mstr_DaoImpl implements CC_C3_Domain_Mstr_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public long DataTableC3_DomainDataTotalCount(String Search, String domain,String status, String system_id,String role) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, domain,system_id);
		
		int total = 0;
		String q = null;
		
        String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and c.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and c.system_id = 45 ";
		}
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			if(Search.equals("") && domain.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by domain ) as ser,c.id,c.domain,c.status,c.system_id,s.system_name\n"
					+ "from edu_cc_tb_c3_domain_mstr c\n"
					+ "inner join edu_lms_system_mstr s on s.id= c.system_id\n"
					+ "where c.id!=0 and c.status='1' \n" + sd
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by domain ) as ser,c.id,c.domain,c.status,c.system_id,s.system_name\n"
						+ "from edu_cc_tb_c3_domain_mstr c\n"
						+ "inner join edu_lms_system_mstr s on s.id= c.system_id\n"
						+ "where c.id!=0 and c.status='"+ status +"' \n" + sd
						+ SearchValue + ") ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, domain,system_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String domain,String system_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(c.domain) like ? or upper(s.system_name) like ?) ";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		
		
		if( domain != null && !domain.equals("")) {
			SearchValue += " and upper(domain) like ? ";
		
	     }
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and (system_id) = ? ";
		
	     }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String domain, String system_id) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (domain != null && !domain.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+domain.toUpperCase()+"%");
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
	
	public List<Map<String,Object>> DataTableC3_DomainDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String domain, String status, String system_id ,String role) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, domain,system_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and c.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and c.system_id = 45 ";
			}
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && domain.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by domain ) as ser,c.id,c.domain,c.status,c.system_id,s.system_name\n"
						+ "from edu_cc_tb_c3_domain_mstr c\n"
						+ "inner join edu_lms_system_mstr s on s.id= c.system_id\n"
						+ "where c.id!=0 and c.status='1'" + sd + SearchValue + " ORDER BY domain " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by domain ) as ser,c.id,c.domain,c.status,c.system_id,s.system_name\n"
					+ "from edu_cc_tb_c3_domain_mstr c\n"
					+ "inner join edu_lms_system_mstr s on s.id= c.system_id\n"
					+ " where c.status='"+ status +"'" + sd  + SearchValue + " ORDER BY domain " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, domain,system_id);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDC3_Domain' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approsAGE"+countFunction+"' value='"+rs.getString("system_id")+"' >"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("domain")+"' >"
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

	public String updateC3_Domain(CC_TB_C3_DOMAIN_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_C3_DOMAIN_MSTR set system_id=:system_id,domain=:domain,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", (obj. getSystem_id())).setParameter("domain", obj.getDomain()).setParameter("status", (obj. getStatus()))
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
