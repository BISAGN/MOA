package com.AyushEdu.dao.Regulation;

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
public class PopupDAOImpl implements PopupDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> getuni_DegreeDataforPopup(String id ,String name_of_institute) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			

	
			q="SELECT distinct c.id,c.regulation_p_id,\n"
					+ "tdm.type_of_degree,m.degree_name,\n"
					+ " mm.university_name as name_of_institute,month_and_year_of_degree, "
					+ " CASE WHEN c.status = 1 THEN 'Verified'  "
					+ "  ELSE 'Not Verified' "
					+ "  END AS status  "
					+ "FROM public.reg_nch_form_a_p r\n"
					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
					+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
					+" inner join edu_lms_university_mstr mm on mm.id=c.name_of_institute::integer "
 					+ "where c.regulation_p_id=? and c.name_of_institute=? ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(id));
			stmt.setString(2, (name_of_institute));
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt----university pop up--MAIN---"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				columns.put("file_id",id);
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
//	@Override
//	public List<Map<String, Object>> getDegreeDataforPopup(String id ,String institute_name_hid ) {
//		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Connection conn = null;
//		String q = "";
//		try {
//
//			conn = dataSource.getConnection();
//			String pageL = "";
//			
// 
//			
//			q="SELECT distinct c.id,c.regulation_p_id,\n"
//					+ "tdm.type_of_degree,m.degree_name,\n"
//					+ " mm.university_name as name_of_institute,month_and_year_of_degree, "
//					+ " CASE WHEN c.status = 1 THEN 'Verified'  "
//					+ "  ELSE 'Not Verified' "
//					+ "  END AS status  "
//					+ "FROM public.reg_nch_form_a_p r\n"
//					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
//					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
//					+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
//					+" inner join edu_lms_university_mstr mm on mm.id=c.name_of_institute::integer "
// 					+ "where c.regulation_p_id=?  ";
//			 
//			
//			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1,Integer.parseInt(id));
//			ResultSet rs = stmt.executeQuery();
//             System.err.println("stmt----popup --ddddddddddddddddddddddd---"+stmt);
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			while (rs.next()) {
//				
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				columns.put("file_id",id);
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
	
	
	@Override
	public List<Map<String, Object>> getDegreeDataforPopup(  String Search,
			String orderColunm, String orderType, String popid,String institute_name_hid) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,popid,institute_name_hid );
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		
		try {

			conn = dataSource.getConnection();
			String pageL = "";
//			if (pageLength == -1) {
//				pageL = "ALL";
//			} else {
//				pageL = String.valueOf(pageLength);
//			}
			
  
 
//c.regulation_p_id=? 
		 q="SELECT distinct c.id,c.regulation_p_id,\n"
					+ "tdm.type_of_degree,m.degree_name,\n"
					+ " mm.university_name as name_of_institute,month_and_year_of_degree, "
					+ " CASE WHEN c.status = 1 THEN 'Verified'  "
					+ "  ELSE 'Not Verified' "
					+ "  END AS status  "
					+ " FROM public.reg_nch_form_a_p r\n"
					+ " inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
					+ " inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
					+ " inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
					+ " inner join edu_lms_university_mstr mm on mm.id=c.name_of_institute::integer "
						+ " where c.id!=0     "+ SearchValue +" order by c.id desc"  ;
			
	 		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, popid,institute_name_hid);
 		 	 System.err.println("stmt---state pop up---"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			//int j = startPage;
			
		 
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				//columns.put("ser", ++j);
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
	
	
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String popid,String institute_name_hid) {
		
		String SearchValue = "";
	
		if (!popid.trim().equals("")) {
			SearchValue += "and c.regulation_p_id = ? ";

		}
		if (!institute_name_hid.equals("0")  ) {
			SearchValue += " and c.name_of_institute = ? ";
		}
	  
//		if (Search != null && !Search.equals("")) {
//			Search = Search.toLowerCase();
//			SearchValue += " and ";
//			SearchValue += "  (lower(e.regulation_p_id) like ? or lower(e.father_name) like ?) ";
//		}
		return SearchValue;
	}
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String popid,String institute_name_hid) {
		int flag = 0;
		try {

			if (!popid.equals("") && popid != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(popid));
			}
			//type 0
			if (!institute_name_hid.equals("0")  ) {
				flag += 1;
				stmt.setString(flag,institute_name_hid);
			}
			  
//			 
//			if (Search != null && !Search.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////
	
	@Override
	public List<Map<String, Object>> getIOCHDataforPopup(String id) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			
 		
			q= "SELECT distinct pw.place_of_working_practitioner,place_of_working_name,\n"
					+ "landline,mobile_no,email,h.name_of_res_p as authority_type,sm.state_name,dm.district_name  , \n"
					+ "to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,\n"
					+ "to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to,hos_address1,hos_address2,hos_address3,name_of_res_p \n"
					+ "FROM public.reg_nch_form_a_p r \n"
					+ " inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id\n"
					+ "inner join edu_lms_regulation_place_of_working_mstr pw on pw.id=h.place_of_working \n"
					+ " inner join edu_lms_state_mstr sm on sm.state_id=h.hos_state \n"
					+ "inner join edu_lms_district_mstr dm on dm.district_id=h.hos_district \n"
					+ "where h.regulation_p_id=?";
			
//			q="SELECT distinct pw.place_of_working_practitioner,place_of_working_name,\n"
//					+ "landline,mobile_no,email,om.name_of_res_owner as authority_type,sm.state_name,dm.district_name  , \n"
//					+ "to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,\n"
//					+ "to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to,hos_address1,hos_address2,hos_address3,name_of_res_p \n"
//					+ "FROM public.reg_nch_form_a_p r \n"
//					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id \n"
//					+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id \n"
//					+ "inner join edu_lms_regulation_place_of_working_mstr pw on pw.id=h.place_of_working \n"
//					+ "inner join edu_lms_regulation_name_of_res_owner_mstr om on om.id=h.authority_type \n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id=h.hos_state \n"
//					+ "inner join edu_lms_district_mstr dm on dm.district_id=h.hos_district \n"
//					+ "where h.regulation_p_id=?";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(id));
			
			
            System.err.println("stmt-----statmenttt---30-MAIN---"+stmt);
			ResultSet rs = stmt.executeQuery();

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
	
	
/////////////////////download/////////Rajdip 07_06_2022
	public String getFilePathQuery_popup(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select STRING_AGG ( attachment, ',' ) as attachment  from reg_nch_med_degree_dtl_a_sub_ch where parent_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
				System.err.println("getFilePath----dao --"+stmt);
			while (rs.next()) {
				whr = rs.getString("attachment");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

}
