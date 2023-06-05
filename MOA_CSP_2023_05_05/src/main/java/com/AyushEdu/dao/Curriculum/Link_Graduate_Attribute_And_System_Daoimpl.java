package com.AyushEdu.dao.Curriculum;
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
public class Link_Graduate_Attribute_And_System_Daoimpl implements Link_Graduate_Attribute_And_System_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public List<Map<String, Object>> DataTableGraduatelinksystemDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String system_name,String degree_name,String graduate_attribute, String status,String role){
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, system_name,degree_name,graduate_attribute,status);
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

			if(search.equals("") && system_name=="0" && graduate_attribute=="0" && status=="1") {
				q="select distinct ROW_NUMBER() OVER(order by sm.system_name ) as ser, p.id,  sm.id as system_id,sm.system_name,dm.id as degree_id,dm.degree_name,am.id as graduate_id, concat(am.code,'-',am.graduate_attributes) as graduate_attributes,p.status from edu_cc_tb_link_graduate_attribute_and_system p\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=p.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr am on am.id=p.graduate_attribute\n"
						+ " where p.status=1  " + sd + SearchValue +" ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
				q="select distinct ROW_NUMBER() OVER(order by sm.system_name ) as ser, p.id,  sm.id as system_id,sm.system_name,dm.id as degree_id,dm.degree_name,am.id as graduate_id, concat(am.code,'-',am.graduate_attributes) as graduate_attributes,p.status from edu_cc_tb_link_graduate_attribute_and_system p\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=p.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr am on am.id=p.graduate_attribute\n"
						+ " where p.status='" + status + "'" + sd + SearchValue +" ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, system_name,degree_name,graduate_attribute,status);
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
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDC3_graatt' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apId"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='appsys"+countFunction+"' value="+rs.getString("system_id")+">"
										+"<input type='hidden' id='appdegree"+countFunction+"' value="+rs.getString("degree_id")+">"
								+"<input type='hidden' id='appatt"+countFunction+"' value="+rs.getString("graduate_id")+">"
								+"<input type='hidden' id='apstatus"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deletegraatt' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID1"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
	
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
	
	public long DataTableGraduatelinksystemTotalCount(String search, String system_name,String degree_name,String graduate_attribute,String status,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(search, system_name,degree_name,graduate_attribute,status);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(search.equals("") && system_name=="0" && graduate_attribute=="0" && status=="1") {
				
			q="select count(*) from (select p.id,  sm.id as system_id,sm.system_name,dm.id as degree_id,dm.degree_name,am.id as graduate_id, concat(am.code,'-',am.graduate_attributes) as graduate_attributes,p.status from edu_cc_tb_link_graduate_attribute_and_system p\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=p.system\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree\n"
					+ "inner join edu_cc_tb_graduate_attributes_mstr am on am.id=p.graduate_attribute\n"
					+ " where p.status=1 "+ sd +SearchValue+" )ab ";
			}else {
				q="select count(*) from (select p.id,  sm.id as system_id,sm.system_name,dm.id as degree_id,dm.degree_name,am.id as graduate_id, concat(am.code,'-',am.graduate_attributes) as graduate_attributes,p.status from edu_cc_tb_link_graduate_attribute_and_system p\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=p.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=p.degree\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr am on am.id=p.graduate_attribute\n"
						+ " where p.status='"+ status +"' "+ sd +SearchValue+" )ab ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, system_name,degree_name,graduate_attribute,status);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_name,String degree_name,String graduate_attribute,String status ) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(dm.degree_name) like ? or upper(concat(am.code,'-',am.graduate_attributes)) like ? )";
		}
//		
		///advance search

		if (!system_name.trim().equals("0")) {
			SearchValue += " and p.system  = ? ";
		}
		if (!degree_name.trim().equals("0")) {
			SearchValue += " and p.degree  = ? ";
		}
		if (!graduate_attribute.trim().equals("0")) {
			SearchValue += " and p.graduate_attribute::int = ? ";
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_name,String degree_name,String graduate_attribute,String status) {
		int flag = 0;
		
		try {
	
		if (!system_name.equals("0") && system_name != null) {
			flag += 1;
			stmt.setInt(flag,  Integer.parseInt(system_name));
		}
		if (!degree_name.equals("0") && degree_name != null) {
			flag += 1;
			stmt.setInt(flag,  Integer.parseInt(degree_name));
		}
		if (!graduate_attribute.equals("0") && graduate_attribute != null) {
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(graduate_attribute));
		}
		if (Search != null && !Search.equals("")) {

			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
		}
		} catch (Exception e) {
			e.printStackTrace();
	  }
		return stmt;
	}
}
