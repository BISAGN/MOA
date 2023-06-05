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
public class Search_NCH_PracforUserDAOImpl implements Search_NCH_PracforUserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableSeacrh_NCH_PracforUserDataList(int startPage, int pageLength,	String Search, 
			String orderColunm, String orderType, String nrh_en_no,String first_name ,String per_state, String registration_state) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,nrh_en_no,first_name, per_state, registration_state);
	//	Image image = new ImageIcon(this.getClass().getResource(photo_path)).getImage();
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
			
 		
			q="select er.id, er.first_name, er.nrh_en_no,s.state_name as per_state,s2.state_name as regisration_state from reg_nch_form_a_p er\n"
					+ " -- INNER JOIN reg_nch_registration_a epr on epr.user_id = er.user_id \n"
					+ "INNER join edu_lms_state_mstr s on  s.state_id    = er.pre_state\n"
					+ "INNER join edu_lms_state_mstr s2 on  s2.state_id    = er.per_state \n"
					+ "WHERE er.status ='1'  and er.nrh_status = '1' or er.nrh_en_no  != 'null' \n"
 					+ SearchValue +" order by er.id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;	

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no, first_name,  per_state ,   registration_state
					);
		 	// System.err.println("stmt---janki----"+stmt);
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----nch--MAIN---"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("img","<img class='d-block img5050 imageZomm imgcsp' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"&id5="+""+"'/>"
						+ "<input type='hidden' id='imghid"+countFunction+"' value="+rs.getString("id")+">");
 
//				String f = "";
//				String action = "";
//				String f1 = "";
//				String chekboxaction="";
//
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
//						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
//
//				
//
//				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
//				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
//		  
//		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
//				+ "' value='" + rs.getObject(1) + "'   />";
//		     chekboxaction+=Checkbox;
//		     System.err.println("chekboxaction----------"+chekboxaction);
//				
//				
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
//				
//				
//				action =f+" "+  f1  ;
//				 
//				columns.put("chekboxaction", chekboxaction);
//				columns.put("action", action);
			
				
//				list.add(chekboxaction);
				countFunction+=1;
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
	
	public long DataTableSeacrh_NCH_PracforUserDataTotalCount( String Search, String nrh_en_no, String first_name, String per_state, String registration_state) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, nrh_en_no, first_name, per_state, registration_state);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
//			q = "select count(*) \n" + " from(select er.id, er.first_name, er.nrh_en_no,s.state_name as per_state,s2.state_name as regisration_state from reg_nch_form_a_p er\n"
//					+ "INNER JOIN reg_nch_registration_a epr on epr.user_id = er.user_id \n"
//					+ "INNER join edu_lms_state_mstr s on s.state_id   = er.pre_state\n"
//					+ "INNER join edu_lms_state_mstr s2 on cast (s2.state_id as character varying)  = epr.regisration_state \n"
//					+ "WHERE er.status ='1' and er.nrh_status = '1' \n"+SearchValue+") a  ";
//			
			q = "select count(*) \n" + " from(select er.id, er.first_name, er.nrh_en_no,s.state_name as per_state,s2.state_name as regisration_state from reg_nch_form_a_p er\n"
					+ "			 -- INNER JOIN reg_nch_registration_a epr on epr.user_id = er.user_id \n"
					+ "			INNER join edu_lms_state_mstr s on  s.state_id    = er.pre_state\n"
					+ "			INNER join edu_lms_state_mstr s2 on  s2.state_id    = er.per_state \n"
					+ "			WHERE er.status ='1'  and er.nrh_status = '1' or er.nrh_en_no  != 'null'  " +SearchValue+") a     ";
			
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,  per_state ,registration_state);
			
			ResultSet rs = stmt.executeQuery();
//			 System.err.println("stmt-----HARSH--COUNT---"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String nrh_en_no,String first_name ,String per_state, String registration_state) {
		
		String SearchValue = "";
	
		if (!nrh_en_no.trim().equals("")) {
			SearchValue += " and upper(er.nrh_en_no) like ? ";
			System.err.println("parameter search nrh_en_no" + SearchValue);
		}
		
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(er.first_name) like ? ";
			System.err.println("parameter search first_name" + SearchValue);
		}
	
		if (!registration_state.equals("0") && registration_state != null) {
			SearchValue += " and epr.regisration_state::integer = ?::integer ";
		}
		if (!per_state.equals("0") && per_state != null) {
			SearchValue += " and er.per_state::integer = ?::integer ";
		}
		 
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			
			
			SearchValue += "  (lower(er.first_name) like ? or lower(er.nrh_en_no) like ? or lower(s.state_name) like ? or lower(s2.state_name) like ?) ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String nrh_en_no,String first_name ,String per_state, String registration_state) {
		int flag = 0;
		try {
			
			if (!nrh_en_no.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + nrh_en_no.toUpperCase() + "%");
			}

			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			
			if (!registration_state.equals("0") && registration_state != null) {
				flag += 1;
				stmt.setString(flag,  registration_state);
			}
			if (!per_state.equals("0") && per_state != null) {
				flag += 1;
				stmt.setString(flag,  per_state);
			}
			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}


	
	
	
	
	
	
	
}
