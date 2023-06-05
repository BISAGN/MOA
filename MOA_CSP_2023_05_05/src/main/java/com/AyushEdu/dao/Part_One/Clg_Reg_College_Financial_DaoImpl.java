package com.AyushEdu.dao.Part_One;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class Clg_Reg_College_Financial_DaoImpl implements Clg_Reg_College_Financial_Dao {

	@Autowired
	private DataSource dataSource;
	public List<Map<String,Object>> getAllfinancialdetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq1="select id as mainid,* from clg_reg_college_financial_details where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---finance--"+stmt);
	        ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	
	public List<Map<String,Object>> getAllbankdetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq1="select id as mainid,* from clg_reg_college_bank_account_details where institute_id=? order by id\n";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---Bank-"+stmt);
	        ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET INSTITUTE_ID
		public ArrayList<ArrayList<String>> getInstitute_id(String user_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

				q =  "select institute_id from logininformation where userid = ? ";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(user_id));
				System.err.println("stmt=====INSTITUTE ID========"+stmt);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("institute_id"));// 0

					list.add(alist);
					
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
						e.printStackTrace();
					}
				}
			}
			return list;
		}
	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select cc.id,cc.institute_id \n"
					+ "from logininformation li\n"
					+ "inner join clg_reg_college_financial_details cc on cc.institute_id = li.institute_id\n"
					+ "where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====P ID=of Finance==tb====="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("institute_id"));// 1

				list.add(alist);
				
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}	
	
public List<Map<String,Object>> getAllExpenses(int institute_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//    			+ "where user_id = ?";
    	String sq1="select id as mainid,year,staff_salary,total_salary,attachment,salary_statment from clg_reg_last_financial_year_expenses where institute_id=? order by id\n";
    	
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, institute_id);
        ResultSet rs = stmt.executeQuery();  
        System.err.println("STMT--Expenses-"+stmt);
        ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			list.add(columns);
		}
		rs.close();
		stmt.close();
		conn.close();
   }catch(SQLException e){
	   e.printStackTrace();
   }        
   return list;
}


public List<Map<String,Object>> getAll_purchase_Expenses(int institute_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//    			+ "where user_id = ?";
    	String sq1="select id as mainid,expyear,purchase,purchase_attachment,total_purchase from clg_reg_last_year_purchase_expenses where institute_id=? order by id";
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, institute_id);
        ResultSet rs = stmt.executeQuery();  
        System.err.println("STMT--Expenses-"+stmt);
        ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			list.add(columns);
		}
		rs.close();
		stmt.close();
		conn.close();
   }catch(SQLException e){
	   e.printStackTrace();
   }        
   return list;
}
public List<Map<String,Object>> getAll_misc_expen_Expenses(int institute_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//    			+ "where user_id = ?";
    	String sq1="select id as mainid,mis_year,misce_expenditure,total_expenditure,mis_attachment "
    			+ "from clg_reg_last_year_expenditure_expenses where institute_id=?  order by id\n";
    	
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, institute_id);
        ResultSet rs = stmt.executeQuery();  
        System.err.println("STMT--Expenses-"+stmt);
        ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			list.add(columns);
		}
		rs.close();
		stmt.close();
		conn.close();
   }catch(SQLException e){
	   e.printStackTrace();
   }        
   return list;
}
}
