package com.AyushEdu.dao.Ug_Pg_Fee_Collection;


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

import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.controller.Regulation.search_prac_detailsController;

@Repository
public class Ug_Pg_Fee_Payment_Report_DaoImpl implements Ug_Pg_Fee_Payment_Report_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

//Search_fee_payment

@Override
public long DataTableSearch_fee_paymentDataTotalCount(String Search,  String name, String status1,String type_of_degree, String degree_name, String term_id,String inst_id,String role) {

	
	String SearchValue = GenerateQueryWhereClause_SQL(Search,name,status1, type_of_degree, degree_name, term_id,inst_id);
	
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		
		//Query for count page in data-table....by ruler
		//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
		String tb_name = "";
		if (role.contains("NCISM")) {
			tb_name="edu_lms_student_details";
		}
		if (role.contains("NCH")) {
			tb_name="edu_lms_nch_student_details";
		}
		
		q="select count(*) from (select sd.name,r.role as type_of_degree,dm.degree_name,tm.prof_name \n"
				+ "from "+tb_name+" sd\n"
				+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
				+ "inner join logininformation l on sd.email=l.email_id \n"
				+ "inner join userroleinformation ur on ur.user_id=l.userid\n"
				+ "inner join roleinformation r on r.role_id=ur.role_id\n"
				+"inner join edu_lms_term_mstr tm on tm.term=sd.semester \n"
				+ "where sd.id!=0 "
				+ SearchValue + ") ab  ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		System.err.println("stmt---->>>"+stmt);
		stmt = setQueryWhereClause_SQL(stmt, Search, name,status1,type_of_degree, degree_name, term_id,inst_id);

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
public String GenerateQueryWhereClause_SQL(String Search, String name, String status1,String type_of_degree, 
		String degree_name, String term_id,String inst_id) {
	
	String SearchValue = "";
	
	if (Search!=null && !Search.equals("")) { 
		
		// for Input Filter
		
		if(Search.toUpperCase().equals("UG")) {
			SearchValue += " and (  upper(sd.name) like ? or upper(dm.degree_name) like ? or upper(tm.prof_name) like ? or upper(r.role) like ?)";
		}else {
			SearchValue += " and (  upper(sd.name) like ? or upper(dm.degree_name) like ? or upper(tm.prof_name) like ? )";
		}
	}

	///advance search
	
	if(!inst_id.equals("")) {
		SearchValue += " and sd.institude_userid = ? ";
	}
	
	if (!type_of_degree.equals("0")) {
		SearchValue += " and type_of_degree =? ";
	}
	
	if(!degree_name.equals("0")) {
		SearchValue += " and  sd.degree = ?";
	}
	
	if( name != null && !name.equals("")) {
		SearchValue += " and upper(sd.name) like ? ";
	}
	
	if( status1 != null && !status1.equals("")) {
		SearchValue += " and sd.fee_paid_status = ? ";
	}
	
	if( term_id != null && !term_id.equals("0")) {
		SearchValue += " and sd.semester = ? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name, String status1,String type_of_degree, String degree_name,
		String term_id,String inst_id) {
	int flag = 0;
	try {
		if (Search!=null &&  !Search.equals("")) {
			
			if(Search.toUpperCase().equals("UG")) {
				Search = "Student_NCISM";
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}else {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
		}
		
		if(!inst_id.equals("")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(inst_id));
		}
		
		if (!type_of_degree.equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(type_of_degree));
		}
		
		if (!degree_name.equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(degree_name));
		}
		
		if (name != null && !name.equals("")) {
			flag += 1;
			stmt.setString(flag, "%"+name.toUpperCase() + "%");
		}
		
		if( status1 != null && !status1.equals("")) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(status1));
		}
		
		if( term_id != null && !term_id.equals("0")) {
			flag += 1;
			stmt.setString(flag,term_id);
		}

	} catch (Exception e) {
	}

	return stmt;
}

public List<Map<String,Object>> DataTableSearch_fee_paymentDataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, String name, String status1, String type_of_degree, String degree_name, String term_id,String inst_id, String role ) {
	
	
	String SearchValue = GenerateQueryWhereClause_SQL(Search, name,status1,type_of_degree, degree_name, term_id,inst_id);
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

		String tb_name = "";
		if (role.contains("NCISM")) {
			tb_name="edu_lms_student_details";
		}
		if (role.contains("NCH")) {
			tb_name="edu_lms_nch_student_details";
		}

			q = "select sd.name,r.role as type_of_degree,dm.degree_name,tm.prof_name,sd.fee_paid_status \n"
					+ "from "+tb_name+" sd\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ "inner join logininformation l on sd.email=l.email_id\n"
					+ "inner join userroleinformation ur on ur.user_id=l.userid\n"
					+ "inner join roleinformation r on r.role_id=ur.role_id\n"
					+"inner join edu_lms_term_mstr tm on tm.term=sd.semester \n"
					+ "where sd.id!=0 "
					+ SearchValue + " ORDER BY sd.id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;


		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, name,status1,type_of_degree, degree_name, term_id,inst_id);
		System.err.println("Fess Report---stmt--->"+stmt);
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
			
//			 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDepartment' value='ADD' title='Edit Data' >" 
//							+"<i class='lni lni-pencil-alt'>"
//							+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
//							+"<input type='hidden' id='apdepAGE"+countFunction+"' value="+rs.getString("department")+">"
//							+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
//			
//			 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
//					 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";


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

@Override
public List<ArrayList<String>> getug_pg_fee_payment_Report_Excel(String Search,String name, String status1, String type_of_degree, String degree_name, String term_id,String inst_id, String role ) {
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name,status1,type_of_degree, degree_name, term_id,inst_id);
		conn = dataSource.getConnection();
		
		String tb_name = "";
		if (role.contains("NCISM")) {
			tb_name="edu_lms_student_details";
		}
		if (role.contains("NCH")) {
			tb_name="edu_lms_nch_student_details";
		}

		q = "select  sd.name,r.role as type_of_degree,dm.degree_name,tm.prof_name,sd.fee_paid_status  \n"
				+ "from "+tb_name+" sd\n"
				+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
				+ "inner join logininformation l on sd.email=l.email_id\n"
				+ "inner join userroleinformation ur on ur.user_id=l.userid\n"
				+ "inner join roleinformation r on r.role_id=ur.role_id\n"
				+ "inner join edu_lms_term_mstr tm on tm.term=sd.semester \n"
				+ "where sd.id!=0 "
				+ SearchValue;

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, name,status1,type_of_degree, degree_name, term_id,inst_id);
		System.err.println("\n\n stmt===========" + stmt);
		ResultSet rs = stmt.executeQuery();
		
		int i = 1;
		ResultSetMetaData metaData = rs.getMetaData();
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
		
			
			alist.add(String.valueOf(i));// 0
			alist.add(rs.getString("name"));// 1
			alist.add(rs.getString("type_of_degree"));// 2
			alist.add(rs.getString("degree_name"));// 3
			alist.add(rs.getString("prof_name"));// 4
			if(rs.getString("fee_paid_status").equals("0")) {
				alist.add("Pending");//5
			}
			if(rs.getString("fee_paid_status").equals("1")) {
				alist.add("Paid");//5
			}

			list.add(alist);
			
			i++;
			
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

}
	