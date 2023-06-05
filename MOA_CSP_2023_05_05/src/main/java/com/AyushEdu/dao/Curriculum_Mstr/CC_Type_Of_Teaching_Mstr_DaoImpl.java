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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TYPE_OF_TEACHING_MSTR;


@Repository
public class CC_Type_Of_Teaching_Mstr_DaoImpl implements CC_Type_Of_Teaching_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	

	public long DataTableTypeOfTeachingDataTotalCount(String Search, String type_of_teaching, String status) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_teaching);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			if(Search.equals("") && type_of_teaching.equals("0") && status=="1") {
			q="select count(*) from (select id,type_of_teaching,status  from edu_cc_tb_type_of_teaching_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}else {
				q="select count(*) from (select id,type_of_teaching,status  from edu_cc_tb_type_of_teaching_mstr where id!=0 and status='"+ status +"'\n"
						+ SearchValue + ") ab  ";
			}
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_teaching);

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
	public String GenerateQueryWhereClause_SQL(String Search, String type_of_teaching) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(type_of_teaching) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( type_of_teaching != null && !type_of_teaching.equals("")) {
			SearchValue += " and upper(type_of_teaching) like ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !type_of_teaching.equals("")){
//			SearchValue += " and upper(type_of_teaching) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String type_of_teaching) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (type_of_teaching != null && !type_of_teaching.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+type_of_teaching.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableTypeOfTeachingDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_teaching, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_teaching);
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

			if(Search.equals("") && type_of_teaching.equals("0") && status=="1") {
				q = "select distinct ROW_NUMBER() OVER(order by type_of_teaching ) as ser, id,type_of_teaching,status from edu_cc_tb_type_of_teaching_mstr where status='1'"  + SearchValue + " ORDER BY "+orderColunm+" "+ orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select distinct ROW_NUMBER() OVER(order by type_of_teaching ) as ser, id,type_of_teaching,status from edu_cc_tb_type_of_teaching_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY "+orderColunm+" " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_teaching);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDType_Of_Teaching' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value='"+rs.getString("type_of_teaching")+"'>"
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
	
	public String updateType_Of_Teaching(CC_TB_TYPE_OF_TEACHING_MSTR obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_TYPE_OF_TEACHING_MSTR set type_of_teaching=:type_of_teaching,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("type_of_teaching", obj.getType_of_teaching()).setParameter("status", (obj. getStatus()))
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
