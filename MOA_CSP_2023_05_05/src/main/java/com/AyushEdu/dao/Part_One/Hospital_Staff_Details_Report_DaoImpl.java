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

@Repository
public class Hospital_Staff_Details_Report_DaoImpl implements Hospital_Staff_Details_Report_Dao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
//	@Override
//	public List<Map<String, Object>> DataTableSearch_Hospital_Equipment_ReportDataList( int startPage,
//			int pageLength, String Search, String orderColunm, String orderType,String articles,String instId,String role,String userid,String institute_id) {
//
//		String SearchValue = GenerateQueryWhereClause_SQL(Search, articles,instId, role, userid, institute_id);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		System.err.println("institute_id-------------------------9999999999999999"+institute_id);
//		Connection conn = null;
//		String q = "";
//		String q1 = "";
//		try {
//			conn = dataSource.getConnection();
//			String pageL = "";
//			if (pageLength == -1) {
//				pageL = "ALL";
//			} else {
//				pageL = String.valueOf(pageLength);
//			}
//
////			q = "select distinct p.*,ir.institute_name,ca.articles as a2 from hosp_equipments_details p\n"
////					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
////					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
////					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
////					+ "where p.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
////					+ " OFFSET " + startPage;
//			
////			q = "select distinct p.institute_id,ir.institute_name,String_agg(ca.articles, ',') as articles,"
////					+ "String_agg(cast(p.total_equipments as varchar), ',') as total_equipments from hosp_equipments_details p\n"
////					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
////					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
////					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
////					+ "where p.id!=0 group by 1,2 " + SearchValue + " order by p.institute_id " + orderType + " limit " + pageL
////					+ " OFFSET " + startPage;
//			
//			q = "select distinct p.institute_id,ir.institute_name,String_agg(distinct ca.articles||': '||total_equipments,'<br>') as articles\n"
//					+ "from hosp_equipments_details p\n"
//					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
//					+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//					+ "where p.id!=0 " + SearchValue + " group by p.institute_id,2 order by p.institute_id " + orderType + " limit " + pageL
//					+ " OFFSET " + startPage;
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt = setQueryWhereClause_SQL(stmt, Search, articles,instId, role, userid, institute_id);
//
//			System.err.println("stmt===============" + stmt);
//
//			ResultSet rs = stmt.executeQuery();
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//
//			int j = startPage;
//			int countFunction = 1;
//
//			while (rs.next()) {
//
//				ArrayList<String> alist = new ArrayList<String>();
//
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//
//				String action = "";
//				String f2 = "";
//				
//				String ul="";
//				ul+="<ul class='buttons-group mainbtn action daobtn'>";
//				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
//						+ rs.getString("institute_id") + "')}else{ return false;}\"";
//				f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
//								"<i class='lni lni-eye'></i></a> </li>"
//								+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("institute_id")+"></i></a> </li>";
//				ul+= f2;
//				 ul+="</ul>";
//				 countFunction+=1;
//				action = ul;
//				columns.put("action", action);
////			alist.add(rs.getString("id")); //0
//
//				list.add(columns);
//
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return list;
//	}

//	public long DataTableSearch_Hospital_Equipment_ReportDataTotalCount(String Search, String articles,String instId, String role,
//			String userid, String institute_id) {
//
//		String SearchValue = GenerateQueryWhereClause_SQL(Search, articles,instId, role, userid, institute_id);
//		int total = 0;
//		String q = null;
//		Connection conn = null;
//		try {
//			conn = dataSource.getConnection();
//
////			q = "select count(*) from (select distinct p.*,ir.institute_name,ca.articles as a2 from hosp_equipments_details p\n"
////					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
////					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
////					+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
////					+ "where p.id!=0 and p.institute_id=" + institute_id
////					+ " and l.userid= " + userid + "  " + SearchValue + ")ab";
//			
////			q = "select count(*) from (select distinct p.institute_id,ir.institute_name,String_agg(distinct ca.articles||': '||total_equipments,'<br>') as articles\n"
////					+ "from hosp_equipments_details p\n"
////					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
////					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
////					+ "inner join logininformation l on l.institute_id=p.institute_id\n"
////					+ "where p.id!=0 group by p.institute_id,2   " + SearchValue + ")ab";
//			
//			q = "select count(*) from (select distinct p.institute_id,ir.institute_name,String_agg(distinct ca.articles||': '||total_equipments,'<br>') as articles\n"
//					+ "from hosp_equipments_details p\n"
//					+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//					+ "inner join edu_lms_institute_reg ir on ir.id=p.institute_id\n"
//					+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//					+ "where p.id!=0 " + SearchValue + "group by p.institute_id,2)ab";
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt = setQueryWhereClause_SQL(stmt, Search,articles,instId, role, userid, institute_id);
//			System.err.println("stmt===============count" + stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				total = rs.getInt(1);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return (long) total;
//	}
//
//	@SuppressWarnings("null")
//	public String GenerateQueryWhereClause_SQL(String Search, String articles,String instId, String role,String  userid,String  institute_id) {
//
//		String SearchValue = "";
//
//		if (Search != null && !Search.equals("")) { // for Input Filter
//			SearchValue += " and ( cast(upper(ca.articles) as varchar) like ?"
//					+ ") ";
//		}
//
//		/// advance search
//		
////		if (articles != null  && articles != ""  &&  !articles.trim().equals("0") && !articles.trim().equals("undefined") && articles != "0") {
////			SearchValue += " and p.articles = ? "; // 1
////		}
//		
//		if (instId != null  && instId != ""  &&  !instId.trim().equals("0") && !instId.trim().equals("undefined") && instId != "0") {
//			SearchValue += " and p.institute_id = ? "; // 2
//		}
//		
//
//		return SearchValue;
//	}
//
//	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String articles,String instId, String role,String  userid,String  institute_id) {
//
//		int flag = 0;
//		try {
//
//			if (Search != null && !Search.equals("")) {
//
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
//			}
//
//			
//			System.out.println("articles------>    "+articles);
//			
////			if (articles != null  && articles != ""  &&  !articles.trim().equals("0") && !articles.trim().equals("undefined")  && articles != "0") {
////				flag += 1;
////				stmt.setInt(flag,Integer.parseInt(articles)); // 1
////			}
//			
//			if (instId != null  && instId != ""  &&  !instId.trim().equals("0") && !instId.trim().equals("undefined")  && instId != "0") {
//				flag += 1;
//				stmt.setInt(flag,Integer.parseInt(instId)); // 2
//			}
//
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return stmt;
//	}
	
	@Override
	public List<Map<String, Object>> View_Hospital_Staff_Details(int id, int institute_id, String role, String userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			// String sq2="select ic.id as info_conn_mainid,* from
			// clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
//	    	if(role.equals("NCH")) {

			sq2 = "select distinct p.*,ca.post,ta.type from hosp_staff_details p\n"
					+ "					inner join hosp_post_mstr ca on ca.id=p.post\n"
					+ "					inner join hosp_type_mstr ta on ta.id=p.type\n"
					+ "					inner join logininformation l on l.institute_id=p.institute_id\n"
					+ "					where p.institute_id = ?";
//	    	}
	    	
//	    	if(role.equals("Institute_NCH")) {
//	    		sq2 = "select distinct p.*,ca.articles from hosp_equipments_details p\n"
//						+ "inner join hosp_articles_mstr ca on ca.id=p.articles\n"
//						+ "inner join logininformation l on l.institute_id=p.institute_id\n"
//						+ "where p.institute_id=?";
//	    	}

			PreparedStatement stmt = conn.prepareStatement(sq2);

	        if(role.equals("NCH")) {
	        	stmt.setInt(1, id);
	        }
	        if(role.equals("Institute_NCH")) {
			stmt.setInt(1, institute_id);
//			stmt.setString(2, userid);
	        }
			System.err.println("stmttttt" + stmt);
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
	public List<Map<String, Object>> getHosptal_staff_detl_UploadDocumentsFetch(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sq2 = null;
		try {
			conn = dataSource.getConnection();

			sq2 = "select * from hosp_staff_details_upload_document\n"
					+ "	where institute_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, Integer.parseInt(id));
			System.err.println("stmttttt" + stmt);
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
