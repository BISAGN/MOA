package com.AyushEdu.dao.Alumni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class Alumni_Exp_ExcelDaoImpl implements Alumni_Exp_ExcelDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableEdu_Alumni_Exp_excel(int startPage, int pageLength, String Search, String orderColunm,
			String orderType ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
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
			
//			q="select e.id,e.alum_name,e.alum_mo_no,e.alum_email,e.alum_passing_year,e.alum_batch from edu_alum_register_alumni_clg e\n"
//					+ "  group by 1,2,3,4,5,6\n"
//					+ " where e.id!= 0  \n"
//					 +SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10,11,12 order by id " + orderType
//		                + " limit " + pageL + " OFFSET " + startPage;
//			
			q="select e.id,e.alum_name,e.alum_mo_no,e.alum_email,e.alum_passing_year,e.alum_batch from edu_alum_register_alumni_clg e \n"
			  + " where e.id!= 0  \n"
			  +SearchValue+ " group by 1,2,3,4,5,6 order by id " + orderType
              + " limit " + pageL + " OFFSET " + startPage;		
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search );
			System.err.println("check the statment"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
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
	public long DataTableEdu_Alumni_Exp_Excel_Count(String Search ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search );
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q="  select count (*) from (select e.id,e.alum_name,e.alum_mo_no,e.alum_email,e.alum_passing_year,e.alum_batch from edu_alum_register_alumni_clg e \n"
						+ " "+SearchValue+" group by 1,2,3,4,5,6 ) a ";
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search );
			
			
			System.err.println("check the statment"+stmt);

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
	public String GenerateQueryWhereClause_SQL(String Search ) throws ParseException {
		String SearchValue = "";
 
		if (Search!=null && !Search.equals("")) { 
			System.err.println("enter or not");
			SearchValue += " and (upper(e.alum_name) like ?"
					+ " or upper(e.alum_mo_no) like ?  "
					+ " or e.alum_email like ?  "
					+ " or upper(e.alum_passing_year) like ? "
					+ " or upper(e.alum_batch) like ?  "
					+ ")";
		}
				
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search ) {
		int flag = 1;
		try {
 
			if (Search!=null &&  !Search.equals("")) {
				System.err.println("INSIDE=-====");
//				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	@Override
	public List<Map<String, Object>> DataTableAlumniApprove_Request(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String InstId,String status,String name) {
		
		
			String SearchValue = GenerateQueryWhereClause_SQL_AAR(Search,InstId,status,name);
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
				
				q = " select ir.institute_name,a.*\n"
						+ "from edu_alum_sign_up a\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=a.institute where a.id!=0"
						+ "" + SearchValue  + " ORDER BY a.id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
		
		
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL_AAR(stmt, Search,InstId,status,name);
				
				System.err.println("AppAlumniReqSTMT---"+stmt);
				
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
					
					 f ="<li><a class='main-btn success-btn btn-hover btn-sm ADDApprove_Request' value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
									"<i class='lni lni-checkmark'>"
									+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
					
					 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm ADDReject_Request' value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
							 "<input type='hidden' id='reIdAGE"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-close'></i></a> </li>";

					
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
	public long DataTableAlumniApprove_Request_Count(String Search,String InstId,String status,String name) {
		
		String SearchValue = GenerateQueryWhereClause_SQL_AAR(Search,InstId,status,name);
			
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				q="select count(*) from  (select ir.institute_name,a.*\n"
						+ "from edu_alum_sign_up a\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=a.institute where a.id!=0 "+ SearchValue + ")a";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL_AAR(stmt,Search,InstId,status,name);
				System.err.println("Count-AppAlumniReqSTMT---" +stmt);

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
	public String GenerateQueryWhereClause_SQL_AAR(String Search,String InstId,String status,String name ) {
		String SearchValue = "";
 
		if (Search!=null && !Search.equals("")) { 
			SearchValue += " and ( upper(a.name) like ? )";
		}
		if(!InstId.equals("")) {
			SearchValue += " and a.institute=?";
		}
		if(!status.equals("")) {
			SearchValue += " and a.status=?";
		}
		if(!name.equals("")) {
			SearchValue += " and upper(a.name) like ?";
		}
				
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_AAR(PreparedStatement stmt,String Search,String InstId,String status,String name ) {
		int flag = 0;
		try {
 
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
			}
			if(!InstId.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(InstId));
			}
			if(!status.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(status));
			}
			if(!name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name.toUpperCase()+"%");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

}
