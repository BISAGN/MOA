package com.AyushEdu.dao.Counselling_Institute;

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
import org.hibernate.query.Query;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


@Repository
public class Institute_Fee_Structure_DAOImpl implements Institute_Fee_Structure_DAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	//GET DATA LIST IN DATA TABLE
	public List<Map<String,Object>> DataTableInstitute_Fee_Structure_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String institute_id) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,institute_id);
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
			
			
			q = "select ir.id,ir.institute_name,ir.code,ir.address,ir.upload_image,ir.institute_email,ir.institute_mob_no,ir.country_id,cm.name,\n"
					+ "ir.state_id,sm.state_name,ir.district_id,dm.district_name,ir.university_id,um.university_name,ir.college_unique_id,ir.college_abbr,\n"
					+ "ir.system_id,sym.system_name,ir.no_of_part  \n"
					+ "from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_country_mstr cm on cm.id = ir.country_id\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id = ir.state_id\n"
					+ "inner join edu_lms_district_mstr dm on dm.district_id = ir.district_id\n"
					+ "inner join edu_lms_system_mstr sym on sym.id = ir.system_id\n"
					+ "inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
					+ "where ir.id = ? "+ SearchValue + " limit "
					+ pageL + " OFFSET " + startPage;
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id));
			stmt = setQueryWhereClause_SQL(stmt, Search,institute_id);
			System.err.println("stmt--013->"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction1=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("img",
						"<img class='d-block img5050 imageZomm' alt='No Image' id='myImg" + rs.getString("id")
								+ "' src='MedicalImagePath_Fee?i_id=" + rs.getString("id") + "' onclick='imageView("
								+ rs.getString("id") + ");' />");
				
				String vmp1 ="";
				String view ="";
				
				
				vmp1= "<ul class='buttons-group mainbtn'><li><a class='main-btn info-btn view_details' value='ADD' title='View Details' >\n"
						+ "<input type='hidden' id='viewdetails"+countFunction1+"' value="+rs.getString("id")+">View Other Details</i></a></ul>";
					
				
				view = vmp1;
				
				countFunction1+=1;
				
				columns.put("view",view);
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
	public String GenerateQueryWhereClause_SQL(String Search, String institute_id) {
		String SearchValue = "";
	
		if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and system_name like ? or university_name like ? or cast(code as text) like ? or institute_name like ? or college_abbr like ?"
						+ "or cast(college_unique_id as text) like ? or cast(no_of_part as text) like ? or cast(institute_mob_no as text) like ? or "
						+ "institute_email like ? or name like ? or state_name like ? or district_name like ? or ir.address like ?";
		
				
		}
		
		///advance search

		
		return SearchValue;
	}
	
	
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String institute_id) {
		int flag = 1;
		try {
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				
			}
		

		} catch (Exception e) {
		}

		return stmt;
	}
	
	//GET DATA COUNT IN DATA TABLE
	@Override
	public long DataTableInstitute_Fee_Structure_DataTotalCount(String Search, String institute_id){
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,institute_id);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
				
			q = "select count(*) from (select ir.id,ir.institute_name,ir.code,ir.address,ir.upload_image,ir.institute_email,ir.institute_mob_no,ir.country_id,cm.name,\n"
					+ "ir.state_id,sm.state_name,ir.district_id,dm.district_name,ir.university_id,um.university_name,ir.college_unique_id,ir.college_abbr,\n"
					+ "ir.system_id,sym.system_name,ir.no_of_part  \n"
					+ "from edu_lms_institute_reg ir\n"
					+ "inner join edu_lms_country_mstr cm on cm.id = ir.country_id\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id = ir.state_id\n"
					+ "inner join edu_lms_district_mstr dm on dm.district_id = ir.district_id\n"
					+ "inner join edu_lms_system_mstr sym on sym.id = ir.system_id\n"
					+ "inner join edu_lms_university_mstr um on um.id = ir.university_id\n"
					+ "where ir.id = ? \n"
					+ SearchValue + ") ab";
				
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id));
			stmt = setQueryWhereClause_SQL(stmt, Search,institute_id);

			System.err.println("stmt---->"+stmt);
			
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
	
	
	//	GET INSTITUTE LIST
	public @ResponseBody ArrayList<ArrayList<String>> getInstituteListbySystem(String system_id) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			if(system_id.equals("001")) {
				System.err.println("jvbhdjfvbhhdfvhsfvbsdjfvbsdjfvbskdjfvbsdjfvhb");
			q="select * from edu_lms_institute_reg where id != 0";
				
			}else {
			
			q="select DISTINCT id,institute_name\n"
					+ "from edu_lms_institute_reg\n"
					+ "where status = '1' and system_id = ? order by id";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			if(!(system_id.equals("001"))) {
			stmt.setInt(1,Integer.parseInt(system_id));
			}
			
			System.err.println("stmt------>      "+stmt);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("institute_name"));// 1
				list.add(alist);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
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
	
	//GET INSTITUTE FEE DETAILS
	@Override
	public ArrayList<ArrayList<String>> getFee_Detailslist(String veiw_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select iot.catid,cm.category,iot.feesid,ft.feestype,iot.feesvalue,iot.hostelfacility,iot.libraryfacility\n"
					+ "from co_instituteotherdetail iot\n"
					+ "inner join tb_lms_category_mstr cm on cm.id = iot.catid\n"
					+ "inner join co_feescategorytype ft on ft.ftid = iot.feesid\n"
					+ "where inid = ?";
			
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(veiw_id));
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt======Fees=================="+stmt);
			
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				
				list.add(rs.getString("category"));// 0
				list.add(rs.getString("feestype"));//1
				list.add(rs.getString("feesvalue"));//2
				list.add(rs.getString("hostelfacility"));//3
				list.add(rs.getString("libraryfacility"));//4
				
				alist.add(list);
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
		return alist;
	}
	
	//GET IMAGE PATH
	@Override
	public String getImagePath(String id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select id,upload_image from edu_lms_institute_reg where id = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("upload_image");
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
