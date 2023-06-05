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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR;


@Repository
public class Formative_And_Summative_Mstr_Daoimpl implements Formative_And_Summative_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableFormative_And_SummativeDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String formative, String status,
			String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id,formative,status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and h.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and h.system_id = 45 ";
			}
			
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && formative.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by formative ) as ser,h.id,h.formative,h.status,h.system_id,s.system_name \n"
						+ "from edu_cc_tb_nch_formative_and_summative_mstr h\n"
						+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.status='1'" + sd  + SearchValue + " ORDER BY formative " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by formative ) as ser,h.id,h.formative,h.status,h.system_id,s.system_name \n"
					+ "from edu_cc_tb_nch_formative_and_summative_mstr h\n"
					+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.status='"+ status +"'"  + sd  + SearchValue + " ORDER BY formative " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id, formative,status);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDFormative_And_Summative' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfsys"+countFunction+"' value='"+rs.getString("system_id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("formative")+"'>"
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
	public String GenerateQueryWhereClause_SQL(String Search,String system_id, String formative,String status) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(formative) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and h.system_id  = ? ";
		}
		if( formative != null && !formative.equals("")) {
			SearchValue += " and upper(formative) like ? ";
		
	     }
	
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id, String formative,String status) {
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
			if (formative != null && !formative.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+formative.toUpperCase()+"%");
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}
	@Override
	public long DataTableFormative_And_SummativeDataTotalCount(String Search, String system_id, String formative,
			String status, String role) {
String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, formative,status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			
			String sd = "";
			
			if(role.contains("NCISM")) {
				sd = " and h.system_id != 45 ";
			}
			if(role.contains("NCH")) {
				sd = " and h.system_id = 45 ";
			}
			conn = dataSource.getConnection();
			if(Search.equals("") && formative.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by formative ) as ser,h.id,h.formative,h.status,h.system_id,s.system_name \n"
					+ "from edu_cc_tb_nch_formative_and_summative_mstr h\n"
					+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.id!=0 and h.status='1' \n" + sd 
					+ SearchValue + ") ab  ";
			}
			else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by formative ) as ser,h.id,h.formative,h.status,h.system_id,s.system_name \n"
						+ "from edu_cc_tb_nch_formative_and_summative_mstr h\n"
						+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.id!=0 and h.status='"+ status +"' \n" + sd 
						+ SearchValue + ") ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id,formative,status);

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
	public String updateFormative_And_Summative(CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR td) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_FORMATIVE_AND_SUMMATIVE_MSTR set system_id=:system_id, formative=:formative,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", td.getSystem_id()).setParameter("formative", td.getFormative()).setParameter("status", (td. getStatus()))
					.setParameter("modified_by", td.getModified_by()).setParameter("modified_date", td.getModified_date())
					.setParameter("id", td.getId());
			
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


