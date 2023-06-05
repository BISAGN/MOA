package com.AyushEdu.dao.LMS_Institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Link_System_Elective_CourseDAOIMPL implements Link_System_Elective_CourseDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> GetSystemCourse_Degree(String system_id,String degree_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	

			    q=" select ec.id as id,cm.course_name from edu_lms_ele_course_mstr ec \n"
			    		+ "inner join edu_lms_course_mstr cm on cm.id=ec.course_name::int \n"
			    		+"where cm.status='1' and degree_id=?   " ;
			    
			
				stmt = conn.prepareStatement(q);
//				stmt.setString(1,system_id);
				stmt.setInt(1,Integer.parseInt(degree_id));
				
				ResultSet rs = stmt.executeQuery();      
				
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("course_name")); //1
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
				}
			}
		}
		return list;
	}
	
	public ArrayList<ArrayList<String>> getDegreeFromCourse_Checked(String degree_id,String system_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	

//			    q=" select ec.elec_course_id,ecm.course_name from edu_lms_system_ele_course_link ec "
//			    		+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=ec.elec_course_id::int "
//			    		+ "where ec.degree_id=? " ;
			q="select ec.elec_course_id,cm.course_name \n"
					+ "from edu_lms_system_ele_course_link ec \n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=ec.elec_course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name ::int \n"
					+" where ec.degree_id=? and ec.system_id =?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(degree_id));
				stmt.setInt(2,Integer.parseInt(system_id));
				System.err.println("------stmt========getDegreeFromCourse_Checked============== "+stmt);
				ResultSet rs = stmt.executeQuery();  
				
				
				
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("elec_course_id")); //0
				alist.add(rs.getString("course_name")); //1
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
				}
			}
		}
		return list;
	}
	
	
	public ArrayList<ArrayList<String>> DataTableLink_System_Elective_CourseDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String system_id,String degree_id) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search,system_id,degree_id);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select ROW_NUMBER() OVER(order by sm.system_name ASC) as sr_no,sm.system_name,dm.degree_name,ecl.system_id,ecl.degree_id,string_agg(cm.course_name, ', ') as course_name \n"
					+ "from edu_lms_system_ele_course_link ecl\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=ecl.degree_id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=ecl.elec_course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "inner join edu_lms_system_mstr sm on sm.id=ecl.system_id::int " + SearchValue +" \n" 
					+ "group by 2,3,4,5 "
			        + " order by sm.system_name " + orderType 
			        + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,system_id,degree_id);
			System.err.println("stmt=========="+stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 1;
			while (rs.next()) {
				// alist....arrange icon column wise......by ruler
				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				
//				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country ?') )"
//						+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o'  " + Update + " title='Edit Data'></i>";
//
//				String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete Country  ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f3 = "<i class='fa fa-trash '  " + Delete + " title='Delete Data'></i>";
//
//				action = f + " "  + f3;
				
				alist.add(rs.getString("system_name"));//0
				alist.add(rs.getString("degree_name"));//1
				alist.add(rs.getString("course_name"));//2
				alist.add(rs.getString("sr_no"));//3
//				 alist.add("<a href="+rs.getString("url")+" class=\"\">"+rs.getString("url")+"</a> "); //0
			//	alist.add(action);
				

				i++;
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





	public long DataTableLink_System_Elective_CourseDataTotalCount(String Search, HttpSession sessionUserId,String system_id,String degree_id) {
	//	String  role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search,system_id,degree_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();


	q="select count(*) from (select sm.system_name,dm.degree_name,ecl.system_id,ecl.degree_id,string_agg(cm.course_name, ', ') as course_name \n"
		+ "from edu_lms_system_ele_course_link ecl\n"
		+ "inner join edu_lms_degree_mstr dm on dm.id=ecl.degree_id\n"
		+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=ecl.elec_course_id::int\n"
		+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
		+ "inner join edu_lms_system_mstr sm on sm.id=ecl.system_id::int\n" + SearchValue +" \n" 
		+ " group by 1,2,3,4 )ab " ;		

        PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search,system_id,degree_id);
			System.err.println("stmt=========="+stmt);
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
					e.printStackTrace();
				}
			}
		}
		return (long) total;
	}
	 
	
	
	public String GenerateQueryWhereCandiList(String Search,String system_id,String degree_id) {
		String SearchValue = "";
		
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(dm.degree_name) like ? or upper(cm.course_name) like ? )";
//			System.err.println("globalllll search" + SearchValue);
		}


		if (!system_id.equals("0") && system_id != null) {

			SearchValue += " and ecl.system_id = ? ";
		}		
		if (!degree_id.equals("0") && degree_id != null) {

			SearchValue += " and ecl.degree_id = ? ";
		}
//		if (!setname.equals("") && setname != null) {
//
//			SearchValue += " and name like ? ";
//		}
//		if (!module_name.equals("") && module_name != null) {
//
//			SearchValue += " and name like ? ";
//		}
			
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt,String Search,String system_id,String degree_id) {
		int flag = 0;
		try {
			
			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				

			}

			if (!system_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {

				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

}
