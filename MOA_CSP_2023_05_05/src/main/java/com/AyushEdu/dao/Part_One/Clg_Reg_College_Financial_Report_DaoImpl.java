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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class Clg_Reg_College_Financial_Report_DaoImpl implements Clg_Reg_College_Financial_Report_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String,Object>> getAllfinancialdetailsReport(int id, int institute_id,String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
	//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
	//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//	    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//	    			+ "where user_id = ?";
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
	
	@Override
	public List<Map<String,Object>> getAllbankdetailsReport(int id, int institute_id,String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
	//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
	//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//	    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//	    			+ "where user_id = ?";
	    	String sq1="select id as mainid,* from clg_reg_college_bank_account_details where institute_id=?";
	    	
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
	public List<Map<String,Object>> getAlluploaddocumentdetailsReport(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
	//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
	//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//	    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//	    			+ "where user_id = ?";
	    	String sq1="select id as mainid,* from clg_reg_college_upload_document where institute_id=?";
	    	
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
	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_idReport(String user_id) {

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
	

@Override
public List<Map<String, Object>> DataTableSearch_College_FinancialDataList(int startPage, int pageLength,
		String Search, String orderColunm, String orderType, String fix_deposite, String current_acct, String saving_acct,
		String project_cost, String income_statement, String role, String userid,
		String institute_id) {

	String SearchValue = GenerateQueryWhereClause_SQL(Search, fix_deposite, current_acct, saving_acct, project_cost, income_statement, role, userid,
			institute_id);
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	Connection conn = null;
	String q = "";
	String q1 = "";
	try {
		conn = dataSource.getConnection();
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		q = "select distinct p.id,p.fix_deposite,p.current_acct,p.saving_acct,p.project_cost,p.income_statement\n"
				+ "from clg_reg_college_financial_details p\n"
				+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
				+ "where p.id!=0 "
//				+ "and p.institute_id=" + institute_id
//				+ " and l.userid=" + userid + "  " 
				+ SearchValue + " order by id " + orderType + " limit " + pageL
				+ " OFFSET " + startPage;

		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, fix_deposite, current_acct, saving_acct, project_cost, income_statement, role, userid,
				institute_id);

		System.err.println("stmt===============list" + stmt);

		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		int j = startPage;
		int countFunction = 1;

		while (rs.next()) {

			ArrayList<String> alist = new ArrayList<String>();

			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("ser", ++j);
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

		String f = "";
		String action = "";
		String f1 = "";
		String f2 = "";
		
		String ul="";
		ul+="<ul class='buttons-group mainbtn action daobtn'>";
		String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
				+ rs.getString("id") + "')}else{ return false;}\"";
		f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
						"<i class='lni lni-eye'></i></a> </li>"
						+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
		ul+=f + " " + f1 + " " + " " + f2;
		 ul+="</ul>";
		 countFunction+=1;
		action = ul;
		columns.put("action", action);

//		alist.add(rs.getString("id")); //0

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

public long DataTableSearch_College_FinancialDataTotalCount(String Search, String fix_deposite, String current_acct, String saving_acct,
		String project_cost, String income_statement, String role,
		String userid, String institute_id) {

	String SearchValue = GenerateQueryWhereClause_SQL(Search, fix_deposite, current_acct, saving_acct, project_cost, income_statement, role, userid,
			institute_id);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		q = "select count(*) from (select distinct p.id,p.fix_deposite,p.current_acct,p.saving_acct,p.project_cost,p.income_statement\n"
				+ "from clg_reg_college_financial_details p\n"
				+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
				+ "where p.id!=0 "
//				+ " and p.institute_id=" + institute_id
//				+ " and l.userid= " + userid + "  " 
				+ SearchValue + ")ab";

		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, fix_deposite, current_acct, saving_acct, project_cost, income_statement, role, userid,
				institute_id);
		System.err.println("stmt===============count" + stmt);
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
public String GenerateQueryWhereClause_SQL(String Search, String fix_deposite, String current_acct, String saving_acct,
		String project_cost, String income_statement, String role, String userid,
		String institute_id) {

	String SearchValue = "";

	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and (cast(p.fix_deposite as varchar) like ? or cast(p.current_acct as varchar) like ?  or cast(p.saving_acct as varchar) like ?"
				+ "or cast(p.project_cost as varchar) like ?  or upper(p.income_statement) like ?"
				+ ") ";
	}

	/// advance search

	if (!fix_deposite.trim().equals("")) {
		SearchValue += " and p.fix_deposite = ? "; // 1
	}
	if (!current_acct.trim().equals("")) {
		SearchValue += " and p.current_acct = ? "; // 2
	}
	if (!saving_acct.trim().equals("")) {
		SearchValue += " and p.saving_acct = ? "; // 3
	}
	if (!project_cost.trim().equals("")) {
		SearchValue += " and p.project_cost = ? "; // 4
	}
	if (!income_statement.trim().equals("")) {
		SearchValue += " and p.income_statement like ? "; // 5
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String fix_deposite, String current_acct, String saving_acct,
		String project_cost, String income_statement, String role, String userid,
		String institute_id) {

	int flag = 0;
	try {

		if (Search != null && !Search.equals("")) {

			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
		}
		if (!fix_deposite.trim().equals("")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(fix_deposite)); // 1
		}
		if (!current_acct.trim().equals("")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(current_acct)); // 2
		}
		if (!saving_acct.trim().equals("")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(saving_acct)); // 3
		}
		if (!project_cost.trim().equals("")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(project_cost)); //4
		}
		if (!income_statement.trim().equals("")) {
			flag += 1;
			stmt.setString(flag,"%" + income_statement + "%" ); // 5
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	return stmt;
}

@Override
public List<Map<String,Object>> getAllExpensesReport(int id, int institute_id,String role) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//    			+ "where user_id = ?";
    	String sq1="select id as mainid,* from clg_reg_last_financial_year_expenses where institute_id=?";
    	
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

@Override
public List<Map<String, Object>> getLastYearPurchaseExp(int id, int inst_id, String role) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		String sq2 = "select * from clg_reg_last_year_purchase_expenses ic where institute_id=?";

		PreparedStatement stmt = conn.prepareStatement(sq2);
		stmt.setInt(1, inst_id);
		ResultSet rs = stmt.executeQuery();
		System.err.println("STMT-----" + stmt);
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
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public List<Map<String, Object>> getLastYearExpenditureExp(int id, int inst_id, String role) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		String sq2 = "select * from clg_reg_last_year_expenditure_expenses ic where institute_id=?";

		PreparedStatement stmt = conn.prepareStatement(sq2);
		stmt.setInt(1, inst_id);
		ResultSet rs = stmt.executeQuery();
		System.err.println("STMT-----" + stmt);
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
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}


}
