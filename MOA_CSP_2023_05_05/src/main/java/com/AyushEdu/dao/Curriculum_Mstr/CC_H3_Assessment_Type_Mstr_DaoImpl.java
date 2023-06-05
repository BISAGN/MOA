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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_H3_ASSESSMENT_TYPE_MSTR;


@Repository
public class CC_H3_Assessment_Type_Mstr_DaoImpl implements CC_H3_Assessment_Type_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableH3_Assessment_TypeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String system_id, String assessment_type, String status,String role) {
		// TODO Auto-generated method stub
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id,assessment_type);
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

			if(Search.equals("") && assessment_type.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by assessment_type ) as ser,h.id,h.assessment_type,h.status,h.system_id,s.system_name \n"
						+ "from edu_cc_tb_h3_assessment_type_mstr h\n"
						+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.status='1'" + sd  + SearchValue + " ORDER BY assessment_type " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by assessment_type ) as ser,h.id,h.assessment_type,h.status,h.system_id,s.system_name \n"
					+ "from edu_cc_tb_h3_assessment_type_mstr h\n"
					+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.status='"+ status +"'"  + sd  + SearchValue + " ORDER BY assessment_type " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,system_id, assessment_type);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDH3_Assessment_Type' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfsys"+countFunction+"' value='"+rs.getString("system_id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("assessment_type")+"'>"
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
	public String GenerateQueryWhereClause_SQL(String Search,String system_id, String assessment_type) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(assessment_type) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and h.system_id  = ? ";
		}
		if( assessment_type != null && !assessment_type.equals("")) {
			SearchValue += " and upper(assessment_type) like ? ";
		
	     }
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id, String assessment_type) {
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
			if (assessment_type != null && !assessment_type.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+assessment_type.toUpperCase()+"%");
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public long DataTableH3_Assessment_TypeDataTotalCount(String Search,String system_id, String assessment_type, String status,String role) {
		// TODO Auto-generated method stub
String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, assessment_type);
		
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
			if(Search.equals("") && assessment_type.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by assessment_type ) as ser,h.id,h.assessment_type,h.status,h.system_id,s.system_name \n"
					+ "from edu_cc_tb_h3_assessment_type_mstr h\n"
					+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.id!=0 and h.status='1' \n" + sd 
					+ SearchValue + ") ab  ";
			}
			else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by assessment_type ) as ser,h.id,h.assessment_type,h.status,h.system_id,s.system_name \n"
						+ "from edu_cc_tb_h3_assessment_type_mstr h\n"
						+ "inner join edu_lms_system_mstr s on s.id= h.system_id where h.id!=0 and h.status='"+ status +"' \n" + sd 
						+ SearchValue + ") ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id,assessment_type);

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
	public String updateH3_Assessment_Type(CC_TB_H3_ASSESSMENT_TYPE_MSTR obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_H3_ASSESSMENT_TYPE_MSTR set system_id=:system_id, assessment_type=:assessment_type,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id()).setParameter("assessment_type", obj.getAssessment_type()).setParameter("status", (obj. getStatus()))
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
