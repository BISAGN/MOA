package com.AyushEdu.dao.Time_Table;

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

@Repository
public class TIME_TABLE_DAOImpl implements TIME_TABLE_DAO{
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	//==========================================Week wise Time_Table==========================================
	
	public ArrayList<ArrayList<String>> getweeklytimetable(String sdate, String degree, String professional, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
//			q="select c.course_name as course,li.login_name as faculty,cm.classroom,trim(To_char(ldate,'DAY')) as dayof from edu_tt_time_table_details ttd\n"
//					+ "inner join logininformation li on li.userid = ttd.faculty\n"
//					+ "inner join edu_lms_course_mstr c on c.id = ttd.course\n"
//					+ "inner join edu_tt_classroom_mstr cm on cm.id::CHARACTER VARYING = ttd.classroom\n"
//					+ "where ldate between ?::timestamp and ?::timestamp + interval '6' day ";
			q="select STRING_AGG ((case\n"
					+ "					when extra_class_status = 1 and lec_type = '0' and ttd.exam_type!=0 and ttd.exam_serial!=0 then '<span class=\"Extra_class_lable\">[Periodic Exam]</span>'\n"
					+ "when extra_class_status = 1 then '<span class=\"Extra_class_lable\">[Extra Class]</span>'\n"
					+ "					 else '' end  )\n"
					+ "|| '</br>' ||c.course_name || '</br>(' || li.login_name  || ')</br>Class: ' || cm.classroom || '</br>' || ttd.start_time || ' - ' || ttd.end_time,'||' ORDER BY ttd.start_time ) as ttdetails,trim(To_char(ldate,'DAY')) as dayof from edu_tt_time_table_details ttd\n"
					+ "inner join logininformation li on li.userid = ttd.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttd.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id = ttd.classroom\n"
					+ "where degree = ? and professional = ? and ttd.institute_id = ? and TO_DATE(to_char(ldate,'dd/mm/yyyy'),'dd/mm/yyyy') between TO_DATE(?,'dd/mm/yyyy') and (to_date(?,'dd/mm/yyyy') + interval '6' day) \n"
					+ "group by trim(To_char(ldate,'DAY'))";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(professional));
			stmt.setInt(3, institute_id);
			stmt.setString(4, sdate);
			stmt.setString(5, sdate);
			System.err.println("Week wise Time_Table====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("ttdetails"));// 0
//				list.add(rs.getString("course"));//1
//				list.add(rs.getString("classroom"));//2
				list.add(rs.getString("dayof"));//3
				
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
	
	public ArrayList<ArrayList<String>> getlayouttimetableDao(String degree, String professional, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select STRING_AGG ((case\n"
					+ "when extra_class_status = 1 then '<span class=\"Extra_class_lable\">[Extra Class]</span>' else '' end  )\n"
					+ "|| '</br>' ||c.course_name || '</br>(' || li.login_name  || ')</br>Class: ' || cm.classroom || '</br>' || ttl.start_time || ' - ' || ttl.end_time || '-ttlid-' || ttl.id,'||' order by ttl.start_time) as ttdetails, upper(ttday) as ttday\n"
					+ "from edu_tt_time_table_layout ttl\n"
					+ "inner join logininformation li on li.userid = ttl.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttl.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id = ttl.classroom\n"
					+ "where ttl.degree = ? and ttl.professional = ? and ttl.institute_id = ? group by ttl.ttday";
			
			stmt = conn.prepareStatement(q);
	
//			System.err.println("TcsddfT====="+stmt);
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(professional));
			stmt.setInt(3, institute_id);
			System.err.println("TcsddfT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("ttdetails"));// 0
				list.add(rs.getString("ttday"));//1
				
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
	
	//==========================================Day wise Time_Table========================================== 
	
	public ArrayList<ArrayList<String>> getDailyTimetable(String ldate, String degree, String professional,int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
			q="select STRING_AGG ((case\n"
					+ "					when extra_class_status = 1 and lec_type = '0' and ttd.exam_type!=0 and ttd.exam_serial!=0 then '<span class=\"Extra_class_lable\">[Periodic Exam]</span>'\n"
					+ "when extra_class_status = 1 then '<span class=\"Extra_class_lable\">[Extra Class]</span>'\n"
					+ "					 else '' end  )\n"
					+ "|| '</br>' ||c.course_name || '</br>(' || li.login_name  || ')</br>Class: ' || cm.classroom || '</br>' || ttd.start_time || ' - ' || ttd.end_time,'||' ORDER BY ttd.start_time ) as ttdetails,trim(To_char(ldate,'DAY')) as dayof from edu_tt_time_table_details ttd\n"
					+ "inner join logininformation li on li.userid = ttd.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttd.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id = ttd.classroom\n"
					+ "where degree = ? and professional = ? and ttd.institute_id = ? and TO_DATE(to_char(ldate,'dd/mm/yyyy'),'dd/mm/yyyy') = TO_DATE(?,'dd/mm/yyyy') \n"
					+ "group by trim(To_char(ldate,'DAY'))";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(professional));
			stmt.setInt(3, institute_id);
			stmt.setString(4, ldate);
			System.err.println("TcsddfssT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("ttdetails"));// 0
//				list.add(rs.getString("course"));//1
//				list.add(rs.getString("classroom"));//2
				list.add(rs.getString("dayof"));//3
				
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
	
	public ArrayList<ArrayList<String>> getDailyttlayoutDao(String degree, String professional, String day, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select STRING_AGG (c.course_name || '</br>(' || li.login_name  || ')</br>Class: ' || cm.classroom || '</br>' || ttl.start_time || ' - ' || ttl.end_time || '-ttlid-' || ttl.id,'||' ORDER BY ttl.start_time) as ttdetails, upper(ttday) as ttday\n"
					+ "from edu_tt_time_table_layout ttl\n"
					+ "inner join logininformation li on li.userid = ttl.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttl.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id = ttl.classroom\n"
					+ "where ttl.degree = ? and ttl.professional = ? and ttl.institute_id =? and ttl.ttday = ?  group by ttl.ttday";
			
			stmt = conn.prepareStatement(q);
	
//			System.err.println("TcsddfT====="+stmt);
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(professional));
			stmt.setInt(3, institute_id);
			stmt.setString(4, day);
			System.err.println("TcsddfT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("ttdetails"));// 0
				list.add(rs.getString("ttday"));//1
				
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
	
	//==========================================Others Time_Table========================================== 
	
	public ArrayList<ArrayList<String>> getWeeklyTimetable(String sdate, String professional, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select STRING_AGG (c.course_name || '</br>(' || li.login_name  || ')</br>Class: ' || cm.classroom || '</br>' || ttd.start_time || ' - ' || ttd.end_time,'||' ORDER BY ttd.start_time ) as ttdetails,trim(To_char(ldate,'DAY')) as dayof from edu_tt_time_table_details ttd\n"
					+ "inner join logininformation li on li.userid = ttd.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttd.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id = ttd.classroom\n"
					+ "where professional = ? and ttd.institute_id = ? and TO_DATE(to_char(ldate,'dd/mm/yyyy'),'dd/mm/yyyy') between TO_DATE(?,'dd/mm/yyyy') and (to_date(?,'dd/mm/yyyy') + interval '6' day) \n"
					+ "group by trim(To_char(ldate,'DAY'))";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(professional));
			stmt.setInt(2, institute_id);
			stmt.setString(3, sdate);
			stmt.setString(4, sdate);
			System.err.println("TcsddfssT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("ttdetails"));// 0
//				list.add(rs.getString("course"));//1
//				list.add(rs.getString("classroom"));//2
				list.add(rs.getString("dayof"));//3
				
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
	
	public ArrayList<ArrayList<String>> getcoursedetailsDao(String degree_id) {
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
	
	public ArrayList<ArrayList<String>> getcoursehours( String degree, String professional) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select c.course_name,(select distinct sum(lecture_hours + non_lecture_hours) as total\n"
					+ "from edu_cc_tb_list_of_topics_parent p\n"
					+ "inner join edu_cc_tb_list_of_topics_child c on c.p_id=p.id\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=c.topic_id\n"
					+ "where p.course_id=50 group by c.p_id,p.course_id)-to_char(end_time::time - start_time::time,'HH')::integer * count(course) as lo_hours FROM edu_tt_time_table_details t\n"
					+ "inner join edu_lms_course_mstr c on c.id = t.course and c.type_of_content_id = 5\n"
					+ "where degree = 59 and professional = 15\n"
					+ "group by course,t.start_time, t.end_time,c.course_name";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(professional));
			System.err.println("TcsddfT====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
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
	
	public ArrayList<ArrayList<String>> getfacultydetailsDao(String course_id, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="SELECT li.userid,li.login_name FROM edu_tt_faculty_course_map cmap\n"
					+ "INNER join logininformation li on cmap.faculty = li.userid\n"
					+ "where cmap.course = ? and cmap.institute_id = ?\r\n"
					+ "";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(course_id));
			stmt.setInt(2,institute_id);
			System.err.println("TT====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("userid"));// 0
				list.add(rs.getString("login_name"));//1
				
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
	
	public ArrayList<ArrayList<String>> getLecturedataDao(String lec_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select * from edu_tt_time_table_layout where id = ? ";
			
			stmt = conn.prepareStatement(q);
				
			stmt.setInt(1, Integer.parseInt(lec_id));
			System.err.println("TfT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("start_time"));// 0
				list.add(rs.getString("end_time"));//1				
				list.add(rs.getString("course"));//2
				list.add(rs.getString("faculty"));//3
				list.add(rs.getString("classroom"));//4
				list.add(rs.getString("lec_type"));//5
				
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
	
	//---------------------------------------Model Delete Method---------------------------------//
//	
//	public ArrayList<ArrayList<String>> getDelLecdataDao(String lec_id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			
//			
//			
//			q="delete from edu_tt_time_table_layout where id = ? ";
//			
//			stmt = conn.prepareStatement(q);
//				
//			stmt.setInt(1, Integer.parseInt(lec_id));
//			System.err.println("TfT====="+stmt);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) { 
//				ArrayList<String> list = new ArrayList<String>();
//				
//				list.add(rs.getString("start_time"));// 0
//				list.add(rs.getString("end_time"));//1				
//				list.add(rs.getString("course"));//2
//				list.add(rs.getString("faculty"));//3
//				list.add(rs.getString("classroom"));//4
//				list.add(rs.getString("lec_type"));//5
//				
//				alist.add(list);
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
//		return alist;
//	}
	
    //----------------------------------------datatables-----------------------------------------//
	
	@Override
	public long getTimetableListCount(String Search, int institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			q = "select count(*) \n" + " from  (select prof.professional,ttl.professional,deg.degree_name,ttl.degree   from edu_tt_time_table_layout ttl\n"
					+ " inner join edu_cc_tb_professional_mstr prof on prof.id = ttl.professional\n"
					+ "  inner join edu_lms_degree_mstr deg on deg.id = ttl.degree\n"
					+ "where ttl.institute_id = ? "+ SearchValue +"  group by prof.professional,ttl.professional,deg.degree_name,ttl.degree "+") m";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id);

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
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(classroom_name) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(block_name) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(strength) like ? )";
			System.err.println("globalllll search"+SearchValue);
		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, int institute_id) {
		int flag = 0;
		try {
			flag += 1;
			stmt.setInt(flag, institute_id);
		
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String,Object>> getTimetableList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, int institute_id) {
		
		
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


				q="  select prof.professional,ttl.professional as prof_id,deg.degree_name,ttl.degree as deg_id  from edu_tt_time_table_layout ttl\n"
						+ " inner join edu_cc_tb_professional_mstr prof on prof.id = ttl.professional\n"
						+ "  inner join edu_lms_degree_mstr deg on deg.id = ttl.degree\n"
						+ "  where ttl.institute_id = ? "+ SearchValue +"  group by prof.professional,ttl.professional,deg.degree_name,ttl.degree order by prof.professional " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, institute_id);
			ResultSet rs = stmt.executeQuery();
			

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
				
			
				String f = "";
				String action = "";
				String f1 = "";

				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("deg_id") +"','"+ rs.getString("prof_id") +"') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o'  " + ADD + " title='Edit Data'></i>";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDClassroom' value='ADD' title='Edit Data' >" 
							+"<i class='lni lni-pencil-alt'>"
							+"<input type='hidden' id='apdeg_id"+countFunction+"' value="+rs.getString("deg_id")+">"
							+"<input type='hidden' id='approf_id"+countFunction+"' value="+rs.getString("prof_id")+"></i></a> </li>";
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"+ rs.getString("prof_id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				 ul+=f + " " + f1 ;
					ul+="</ul>";
					
					action = ul;
				countFunction+=1;
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
	
	//===============================List from Academic=======================
	
//	public ArrayList<ArrayList<String>> getweeklyAcademicList(String sdate, String professional, int institute_id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			
//			q=" select trim(To_char(UNNEST(days),'DAY'))  as academic_days from ( select (select ARRAY_AGG(ldate)  from generate_series(TO_DATE(?,'dd/mm/yyyy'), \n"
//					+ "  (to_date(?,'dd/mm/yyyy') + interval '6' day) , '1 day'::interval) ldate where ldate between starting_date and ending_date) as days\n"
//					+ "   from edu_tt_academic_details where academic_details = '004' and professional = ? and institute_id = ?) as iq";
//			
//			stmt = conn.prepareStatement(q);
//			stmt.setString(1, sdate);
//			stmt.setString(2, sdate);
//			stmt.setInt(3, Integer.parseInt(professional));
//			stmt.setInt(4, institute_id);
//			System.err.println("getweeklyAcademicList---===---====="+stmt);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list = new ArrayList<String>();
//				
//				list.add(rs.getString("academic_days"));// 0
////				list.add(rs.getString("course"));//1
////				list.add(rs.getString("classroom"));//2
//				
//				alist.add(list);
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
//		return alist;
//	}
	
	
	public ArrayList<ArrayList<String>> getweeklyExamList(String sdate, String professional, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q=" select trim(To_char(UNNEST(days),'DAY'))  as exam_days from ( select (select ARRAY_AGG(ldate)  from generate_series(TO_DATE(?,'dd/mm/yyyy'), \n"
					+ "  (to_date(?,'dd/mm/yyyy') + interval '6' day) , '1 day'::interval) ldate where ldate between starting_date and ending_date) as days\n"
					+ "   from edu_tt_academic_details where academic_details = '002' and professional = ? and institute_id = ? and exam_type!=8) as iq";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, sdate);
			stmt.setString(2, sdate);
			stmt.setInt(3, Integer.parseInt(professional));
			stmt.setInt(4, institute_id);
			System.err.println("getweeklyExamList---===---====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("exam_days"));// 0
//				list.add(rs.getString("course"));//1
//				list.add(rs.getString("classroom"));//2
				
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
	//-----------------------------Nayan
	public ArrayList<ArrayList<String>> getweeklyEventList(String event_date, int institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			System.err.println("-----institute "+institute_id);

			q=" select trim(To_char(UNNEST(days),'DAY'))  as holiday,event_name as event \n"
					+ "from ( select (select ARRAY_AGG(ldate)  from generate_series(TO_DATE(?,'dd/mm/yyyy'), \n"
					+ "  (to_date(?,'dd/mm/yyyy') + interval '6' day) , '1 day'::interval) ldate where ldate = event_date and institute_id = ?) as days,event_name\n"
					+ "   from edu_tt_event_mstr ) as iq";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, event_date);
			stmt.setString(2, event_date);
			stmt.setInt(3, institute_id);
			System.err.println("TcsddfssT====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("holiday"));// 0
				list.add(rs.getString("event"));//1
//				list.add(rs.getString("classroom"));//2
				
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

	
	//---------------------------------------Hiral-------------------------------------------------------//
	
	//-------------------------------ValidateTimeForLayout----------------------------------------------//
	
	
	public long ValidateTimeForLayout(String day, String degree, String professional,
					String start_time, String end_time, int institute_id,String hid){
			Connection conn = null;
			String q = "";
			String qry = "";
			
			int flag = 0;
	
			
			
			long total= 0;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				if (hid != null && !hid.equals("0")) {
					qry+= " and id != ? ";
				}
				
				q="select count(*) as count from EDU_TT_TIME_TABLE_LAYOUT where ttday=? and degree=? and professional=? and "
						+ "((?::time between start_time::time and end_time::time - interval '1 minute') or (?::time between start_time::time + interval '1 minute' and end_time::time))"
						+ " and institute_id=?" + qry;
				
				System.err.println("--------q----" +q);
				stmt = conn.prepareStatement(q);
				stmt.setString(1, day);//0
				stmt.setInt(2, Integer.parseInt(degree));//1
				stmt.setInt(3, Integer.parseInt(professional));//2
				stmt.setString(4, start_time);//3
				stmt.setString(5, end_time);//4
				stmt.setInt(6, institute_id);//5stmt.setInt(7, Integer.parseInt(hid));//6
				
				if (hid != null && !hid.equals("0")) {
					stmt.setInt(7, Integer.parseInt(hid));
				}
				
				
				System.err.println("ValidateTimeForLayout STMT====="+stmt);
				ResultSet rs = stmt.executeQuery();
				System.err.println("ValidateTimeForLayout STMT====="+stmt);
				while (rs.next()) {
					total = rs.getLong("count");
					System.err.println("ValidateTimeForLayout count STMT====="+total);
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
	
	
	//-------------------------------ValidateTimeForExtraClass----------------------------------------------//
	
			public long ValidateTimeForExtraClass(String ldt, String degree, String professional,
					String start_time, String end_time, int institute_id){
			Connection conn = null;
			String q = "";
			
			long total= 0;
			
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				q="select count(*) as count from edu_tt_time_table_details where ldate=?::timestamp and degree=? and professional=? and "
						+ "((?::time between start_time::time and end_time::time - interval '1 minute') or (?::time between start_time::time + interval '1 minute' and end_time::time))"
						+ " and institute_id=?" ;
				
				
				System.err.println("---ValidateTimeForExtraClass---"+q);
					
				stmt = conn.prepareStatement(q);
				stmt.setString(1, ldt);//0
				System.err.println(ldt);
				stmt.setInt(2, Integer.parseInt(degree));//1
				stmt.setInt(3, Integer.parseInt(professional));//2
				stmt.setString(4, start_time);//3
				stmt.setString(5, end_time);//4
				stmt.setInt(6, institute_id);//5
				
				System.err.println("ValidateTimeForExtraClass STMT====="+stmt);
				ResultSet rs = stmt.executeQuery();
				System.err.println("ValidateTimeForExtraClass STMT====="+stmt);
				while (rs.next()) {
					total = rs.getLong("count");
					System.err.println("ValidateTimeForExtraClass count STMT====="+total);
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
			
			public ArrayList<ArrayList<String>> getweeklyTransitionalList(String sdate, String professional, int institute_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					
					q=" select trim(To_char(UNNEST(days),'DAY'))  as exam_days from ( select (select ARRAY_AGG(ldate)  from generate_series(TO_DATE(?,'dd/mm/yyyy'), \n"
							+ "  (to_date(?,'dd/mm/yyyy') + interval '6' day) , '1 day'::interval) ldate where ldate between starting_date and ending_date) as days\n"
							+ "   from edu_tt_academic_details where academic_details = '004' and professional = ? and institute_id = ?) as iq";
					
					stmt = conn.prepareStatement(q);
					stmt.setString(1, sdate);
					stmt.setString(2, sdate);
					stmt.setInt(3, Integer.parseInt(professional));
					stmt.setInt(4, institute_id);
					System.err.println("getweeklyTransitionalList---===---====="+stmt);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						
						list.add(rs.getString("exam_days"));// 0
//						list.add(rs.getString("course"));//1
//						list.add(rs.getString("classroom"));//2
						
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
