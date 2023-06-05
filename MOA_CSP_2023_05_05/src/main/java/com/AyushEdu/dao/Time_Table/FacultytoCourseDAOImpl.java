package com.AyushEdu.dao.Time_Table;

import java.math.BigInteger;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Time_Table.EDU_TT_FACULTY_TO_COURSE_MASTER;
@Repository
public class FacultytoCourseDAOImpl implements FacultytoCourseDAO {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * public void setSessionFactory(SessionFactory sf) { this.sessionFactory = sf;
	 * }
	 */
	
	public long DataTableFacultytoCourseDataTotalCount(String Search, String degree, String professional, String course, String faculty, int institute_id) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, degree, professional, course, faculty, institute_id);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select dm.degree_name,pm.professional,cm.course_name,li.login_name\r\n"
					+ "from edu_tt_faculty_course_map fcm\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=fcm.degree\r\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=fcm.course\r\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id=fcm.professional\r\n"
					+ "inner join logininformation li on li.userid=fcm.faculty  where fcm.institute_id = ?\r\n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, degree, professional, course, faculty, institute_id);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String degree, String professional, String course, String faculty, int institute_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(dm.degree_name) like ? or upper(pm.professional) like ? or upper(cm.course_name) like ?  or upper(li.login_name) like ? )";
				
		}
//		System.err.println("degree----"+degree+"---course---"+course+"---fac---"+faculty);
		///advance search
		if( degree != null && !degree.equals("0")) {
			SearchValue += " and fcm.degree = ? ";
		
	     }
		if( professional != null && !professional.equals("0")) {
			SearchValue += " and fcm.professional = ? ";
		
	     }
		if( course!= null && !course.equals("0")) {
			SearchValue += " and fcm.course = ? ";
		
	     }
		if( faculty != null && !faculty.equals("0")) {
			SearchValue += " and fcm.faculty = ? ";
		
	     }
		

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String degree, String professional,
			String course, String faculty, int institute_id) {
		int flag = 0;
		try {
			flag += 1;
			stmt.setInt(flag,institute_id);
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
//			System.err.println("degree----"+degree+"---course---"+course+"---fac---"+faculty);
			if (degree != null && !degree.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree));
			}
			if (professional != null && !professional.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional));
			}
			if (course != null && !course.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course));
			}
			if (faculty != null && !faculty.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(faculty));
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> DataTableFacultytoCourseDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String degree, String professional, String course, String faculty, int institute_id ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, degree, professional, course, faculty, institute_id);
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

			
					if(Search.equals("") && degree != null && degree.equals("0") && professional != null && professional.equals("0") && course != null && course.equals("0") && faculty != null && faculty.equals("0")) {
						q = "select fcm.id,dm.degree_name,pm.professional,cm.course_name,li.login_name as faculty_name\r\n"
								+ "	from edu_tt_faculty_course_map fcm\r\n"
								+ "	inner join edu_lms_degree_mstr dm on dm.id=fcm.degree\r\n"
								+ "	inner join edu_lms_course_mstr cm on cm.id=fcm.course\r\n"
								+ " inner join edu_cc_tb_professional_mstr pm on pm.id=fcm.professional\r\n"
								+ "	inner join logininformation li on li.userid=fcm.faculty where fcm.institute_id = ?"  
								+ SearchValue + " ORDER BY fcm.id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					}else {
					q = "select fcm.id,dm.degree_name,pm.professional,cm.course_name,li.login_name as faculty_name\r\n"
							+ "	from edu_tt_faculty_course_map fcm\r\n"
							+ "	inner join edu_lms_degree_mstr dm on dm.id=fcm.degree\r\n"
							+ "	inner join edu_lms_course_mstr cm on cm.id=fcm.course\r\n"
							+ " inner join edu_cc_tb_professional_mstr pm on pm.id=fcm.professional\r\n"
							+ "	inner join logininformation li on li.userid=fcm.faculty  where fcm.institute_id = ?"  + SearchValue + " ORDER BY fcm.id " + orderType + " limit "
							+ pageL + " OFFSET " + startPage;
					}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, degree, professional, course, faculty, institute_id);
			System.err.println("stmt-------"+stmt);
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
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm editOnclick' +ADD+ value='ADD' title='Edit Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' +ADD1+ value='ADD' title='Delete Data' >"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
	
	public ArrayList<ArrayList<String>> getFacultyData(String userId) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT li.userid,li.username as login_name \n" + "FROM userroleinformation ur\n"
					+ "inner join edu_lms_faculty_nch li on li.userid::int = ur.user_id and li.institute_id = (select distinct institute_id from logininformation where userid=?)\n"
					+ "inner join roleinformation ri on ri.role_id = ur.role_id and ri.role in ('Faculty_NCISM','Faculty_NCH')";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userId));
			System.err.println("TT=====" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("userid"));// 0
				list.add(rs.getString("login_name"));// 1

				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {System.err.println("e");
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
	 

	public EDU_TT_FACULTY_TO_COURSE_MASTER getFacultytoCourseByid(BigInteger id) {
		System.err.println("DAO-----BIGINT---"+id);
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_TT_FACULTY_TO_COURSE_MASTER updateid = (EDU_TT_FACULTY_TO_COURSE_MASTER) session.get(EDU_TT_FACULTY_TO_COURSE_MASTER.class, id);
//         session.getTransaction().commit();
//         session.close();            
		 
        return updateid;
  }

	
	@Override
	public ArrayList<ArrayList<String>> getCourseDetailsDao(String degree_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select e.id,c.course_name from edu_lms_ele_course_mstr e\r\n"
					+ "inner join edu_lms_course_mstr c on c.id::character varying = e.course_name and c.type_of_content_id = 5 \r\n"
					+ "where degree_id = ? group by 1,2 order by id ASC";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree_id));
			System.err.println("TcsddfT====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("course_name"));//1
				
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

	@Override
	public ArrayList<ArrayList<String>> getFacultyDetailsDao(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select m.id,m.faculty_name from edu_lms_faculty_ele_course_link f\r\n"
					+ "inner join edu_lms_ele_course_mstr c on c.id = f.ele_course_id\r\n"
					+ "inner join edu_lms_faculty_mstr m on m.id = f.faculty_id\r\n"
					+ "where m.course_id = ? \r\n"
					+ "group by 1,2";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(course_id));
			System.err.println("TT====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("faculty_name"));//1
				
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


}
