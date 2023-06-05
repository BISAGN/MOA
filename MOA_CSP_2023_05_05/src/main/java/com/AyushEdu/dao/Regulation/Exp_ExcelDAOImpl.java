package com.AyushEdu.dao.Regulation;

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
public class Exp_ExcelDAOImpl implements Exp_ExcelDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableEdu_Exp_excel(int startPage, int pageLength, String Search, String orderColunm,
			String orderType,String role_type,String upload_date ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,role_type, upload_date);
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
			
//			q="  select e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date, \n"
//					+ "e.enrollment_no ,e.gender,ss.system_name as system,sg.degree_name as degree,to_char(e.created_date,'DD/MM/YYYY') as upload_date from reg_nch_details_a e \n"
//					+ " inner join edu_lms_system_mstr ss on (ss.id)  = e.system\n"
//					+ "  inner join edu_lms_degree_mstr sg on (sg.id)  = e.degree\n"
//					+ " where e.id!= 0  \n"
//					 +SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10,11,12  order by id " + orderType
//		                + " limit " + pageL + " OFFSET " + startPage;
			
			q=" select e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date, \n"
					+ "e.enrollment_no ,e.gender,ss.system_name as system,sg.degree_name as degree,to_char(e.created_date,'DD/MM/YYYY') as upload_date,ld.role_id ,li.userid\n"
					+ "from reg_nch_details_a e \n"
					+ " inner join logininformation li on (li.aadhar_no)  = e.aadhar_card\n"
					+ " inner join userroleinformation ld on (ld.user_id)  = li.userid\n"
					+ " inner join edu_lms_system_mstr ss on (ss.id)  = e.system\n"
					+ "  inner join edu_lms_degree_mstr sg on (sg.id)  = e.degree\n"
					+ " where e.id!= 0  \n" 
					 +SearchValue+ "group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14  order by id " + orderType
	                + " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,role_type, upload_date );
			System.err.println("check the statment"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction2=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String vm1 ="";
				if (rs.getString("role_id").equals("25")) {
					vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye popdegreep'>"
							+ "<input type='hidden' id='InstiaccessId"+countFunction2+"' value="+rs.getString("userid")+"></i></a> </li></ul>";
					countFunction2+=1;
					
				}
				
				columns.put("vm1",vm1);	
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
	public long DataTableEdu_Exp_Excel_Count(String Search,String role_type,String upload_date ) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,role_type,upload_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
//			q="select count(*)  from (select "
//					+"	e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date,to_char(e.created_date,'DD/MM/YYYY') as upload_date, \n"
//					+ " e.system,e.degree,e.enrollment_no,e.gender from reg_nch_details_a e \n"
//						+ " "+SearchValue+" group by 1,2,3,4,5,6,7,8,9,10,11,12 ) a ";
		
					q="select count(*)  from (select e.id,e.name,e.dob,e.aadhar_card,e.email,e.mobile_no,e.admission_date, \n"
							+ "e.enrollment_no ,e.gender,ss.system_name as system,sg.degree_name as degree,to_char(e.created_date,'DD/MM/YYYY') as upload_date,ld.role_id ,li.userid\n"
							+ "from reg_nch_details_a e \n"
							+ " inner join logininformation li on (li.aadhar_no)  = e.aadhar_card\n"
							+ " inner join userroleinformation ld on (ld.user_id)  = li.userid\n"
							+ " inner join edu_lms_system_mstr ss on (ss.id)  = e.system\n"
							+ "  inner join edu_lms_degree_mstr sg on (sg.id)  = e.degree \n"
						+ " "+SearchValue+"  group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14  ) a ";
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,role_type,upload_date );
			
			
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
	public String GenerateQueryWhereClause_SQL(String Search,String role_type,String upload_date ) throws ParseException {
		String SearchValue = "";
 
		if (Search!=null && !Search.equals("")) { 
			System.err.println("enter or not");
			SearchValue += " and (upper(e.name) like ?"
					+ " or upper(e.dob) like ?  "
					+ " or upper(e.aadhar_card) like ? "
					+ " or e.email like ?  "
					+ " or upper(e.mobile_no) like ?  "
					+ " or upper(e.admission_date) like ?  "
					+ " or upper(e.enrollment_no) like ?  "
					+ " or upper(e.gender) like ?  "
//					+ " or upper(e.admission_date) like ?  "
					+ ")";
		}
		

		if (role_type != null && !role_type.equals("0") ) {
			SearchValue += " and ld.role_id = ? ";
		}

		if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(e.created_date , 'dd/mm/yyyy') = ? ";
	     }
				
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String role_type,String upload_date ) {
		int flag = 0;
		try {
 
			if (Search!=null &&  !Search.equals("")) {
				System.err.println("INSIDE=-====");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if (role_type != null && !role_type.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(role_type));
			}
			if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, upload_date );
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

  
}
