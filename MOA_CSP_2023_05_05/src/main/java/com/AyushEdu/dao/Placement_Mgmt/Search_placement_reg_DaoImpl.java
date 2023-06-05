package com.AyushEdu.dao.Placement_Mgmt;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_PAPER_LAYOUT;

//import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

@Repository
public class Search_placement_reg_DaoImpl implements Search_placement_reg_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableSearch_placement_reg_count(String Search)  {

		String SearchValue = GenerateQueryWhereClause_SQL(Search);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q="select count(*)  from (select e.first_name,e.gp_title as project,e.mo_no,e.alt_no,e.email_id, \n"
					+ "concat(e.add_line1,',',ss.state_name,',',sd.district_name,',',e.pincode) as address \n"
					+ "from edu_placement_reg e\n"
					+ "inner join edu_lms_state_mstr ss on (ss.state_id)  = e.state  \n"
					+ "inner join edu_lms_district_mstr sd on (sd.district_id)  = e.per_district where job_seekers='1'"	+ SearchValue + ") e";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			System.err.println("stmt-cardsssssssssssss-countttttttttttt--" + stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search) {
		
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(e.first_name) like ?" 
					+ " or upper(e.gp_title) like ?  "
					+ " or e.mo_no like ? "
					+ " or e.alt_no like ? "
					+ " or (e.email_id) like ? " 
					+ " or  cast(state_name as character varying)  like ? "
					+ "  or cast(district_name as character varying)   like ? )  " ;
		}

		/// advance search

//		if (!first_name.trim().equals("")) {  
//			SearchValue += " and  (first_name) like  upper(?)  ";
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search+ "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");	
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase()  + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase()  + "%");		
				
				
			 
			}
//
//			
//			if (!first_name.equals("") && first_name != null) {
//				flag += 1;
//				stmt.setString(flag,  "%" + first_name.toUpperCase() + "%" );
//			}
//
//	
//			if (!father_name.equals("") &&  father_name!= null && !father_name.trim().equals("")) {  
//				flag += 1;
//				stmt.setString(flag,  "%" + father_name.toUpperCase() + "%" );
//			}
//
//			
//			if (!mo_no.equals("") && mo_no != null && !mo_no.trim().equals("")) {
//				flag += 1;
//				stmt.setString(flag,  "%" + mo_no.toUpperCase() + "%" );
//			}
////			
//			
//			if (!state.equals("0") && state != null && !state.trim().equals(" ")) {
//				flag += 1;
//				stmt.setString(flag,  state);
//			}
//			
//			System.err.println("per_district==="+per_district);
//			
//			if ( per_district != null && !per_district.equals("0") &&   !per_district.trim().equals(" ")) {
//				flag += 1;
//				stmt.setString(flag,  per_district);
//			}
////
//			if (!gp_title.equals("") && gp_title != null && !gp_title.trim().equals("")) {
//				flag += 1;
//				stmt.setString(flag,  "%" + gp_title.toUpperCase() + "%" );
//			}
////			
//			if (!fm_name.equals("") && fm_name != null && !fm_name.trim().equals("")){
//				flag += 1;
//				stmt.setString(flag,  "%" + fm_name.toUpperCase() + "%" );
//			}
////			
//			if (!im_name.equals("") && im_name != null && !im_name.trim().equals("")){
//				flag += 1;
//				stmt.setString(flag,  "%" + im_name.toUpperCase() + "%" );
//			}
				

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableSearch_placement_reg(int startPage, int pageLength,
			String Search,String orderColunm,String orderType) {

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

			q="select e.first_name,e.gp_title as project,e.mo_no,e.alt_no,e.email_id,\n"
					+ "concat(e.add_line1,',',ss.state_name,',',sd.district_name,',',e.pincode) as address\n"
					+ "from edu_placement_reg e\n"
					+ "inner join edu_lms_state_mstr ss on (ss.state_id)  = e.state \n"
					+ "inner join edu_lms_district_mstr sd on (sd.district_id)  = e.per_district where job_seekers='1'"	
					+SearchValue+ " order by e.id " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
			
			System.err.println("urmikkkkk card--"+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String col1 = "<td>"
				+"<div class='card-style'>"
				+"<ul class='custom-datatable-detail'>"
				+"<li class='d-flex'>"
				+"<div class='custom-datatable-data texthighlight1'>"
				+"<span>"+rs.getString("first_name")+"</span>"
				+"</div>"
				+"</li>"
				+"<li class='d-flex texthighlight2'>"
				+"<div class='custom-datatable-icon'>"
				+"<i class='bi bi-building'></i>"
				+"</div>"
				+"<div class='custom-datatable-data'>"
				+"<span>"+rs.getString("project")+"</span>"
				+"</div>"
				+"</li>"
				+"<li class='d-flex'>"
				+"<div class='custom-datatable-icon'>"
				+"<i class='bi bi-phone'></i>"
				+"</div>"
				+"<div class='custom-datatable-data'>"
				+"<span>"+rs.getString("mo_no")+",</span>"
				+"<span>"+rs.getString("alt_no")+"</span>"
				+"</div>"
				+"</li>"
				+"<li class='d-flex'>"
				+"<div class='custom-datatable-icon'>"
				+"<i class='bi bi-envelope'></i>"
				+"</div>"
				+"<div class='custom-datatable-data'>"
				+"<span>"+rs.getString("email_id")+"</span>"
				+"</div>"
				+"</li>"
				+"<li class='d-flex'>"
				+"<div class='custom-datatable-icon'>"
				+"<i class='bi bi-geo-alt'></i>"
				+"</div>"
				+"<div class='custom-datatable-data'>"
				+"<span>"+rs.getString("address")+"</span>"
				+"</div>"
				+"</li>"
				+"</ul>"
				+"</div>"
				+"</td>";
				
				columns.put("col1",col1);
				
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
	
//	  -----------------------------View_Img Path---------------------------------------------------------
	  
	  @Override
		public String getImagePath(String id) {
			String whr="";
			Connection conn = null;
			System.err.println("check id in dao"+id);
			try {	
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select photo_path from edu_placement_reg where id=? ";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	           whr=rs.getString("photo_path"); 
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