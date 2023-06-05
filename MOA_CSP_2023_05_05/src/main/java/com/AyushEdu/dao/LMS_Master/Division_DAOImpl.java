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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_DIVISION_MSTR;
@Repository
public class Division_DAOImpl implements Division_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	@Override
	public long DataTableDivisionDataTotalCount(String Search, String division_name) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, division_name);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select id,division_name,status  from edu_division_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, division_name);

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
	public String GenerateQueryWhereClause_SQL(String Search, String division_name) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(division_name) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( division_name != null && !division_name.equals("")) {
			SearchValue += " and upper(division_name) like ? ";
		
	     }
//	
//	   if(!SearchValue.contains("and") && !system_name.equals("")){
//			SearchValue += " and upper(system_name) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String division_name) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (division_name != null && !division_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+division_name.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	
	public List<Map<String,Object>> DataTableDivisionDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String division_name, String status ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, division_name);
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

			if(Search.equals("") && division_name.equals("0") && status=="1") {
				q = "select id,division_name,status from edu_division_mstr where status='1'"  + SearchValue + " ORDER BY division_name " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,division_name,status from edu_division_mstr where status='"+ status +"'"  + SearchValue + " ORDER BY division_name " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, division_name);
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
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
	
	public EDU_DIVISION_MSTR getdivisionByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_DIVISION_MSTR updateid = (EDU_DIVISION_MSTR) session.get(EDU_DIVISION_MSTR.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }

}
