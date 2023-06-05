package com.AyushEdu.dao.LMS_Student;

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
public class Students_Marks_ReportDaoImpl implements Students_Marks_ReportDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<Map<String, Object>> DataTableSearch_Stu_MarksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String courses, String set,String userid) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, courses,set,userid);
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
			
			q="select distinct p.course_id,p.set_id,sm.setname,c.course_name \n"
					+ "from edu_lms_question_band_parent p\n"
					+ "inner join edu_lms_set_mstr sm on sm.id=p.set_id\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::text = p.course_id::text\n"
					+ "inner join edu_lms_course_mstr c on c.id = cm.course_name::int\n"
					+ "inner join edu_lms_exam_paper l on l.course_id = p.course_id\n"
					+ "where p.id!=0"
					+ SearchValue + " ORDER BY p.course_id "
					+ orderType + " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL1(stmt, Search,courses,set,userid);
			
			
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countview=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String vd = "";
				String action = "";
				
						vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview VIEWdetails' value='ADD' title='View Data' >\n"
						+ "		<i class='lni lni-eye'> <input type='hidden' id='courses"+countview+"' value="+rs.getString("course_id") +">"
						+"<input type='hidden' id='set"+countview+"' value="+rs.getString("set_id")+">"
						+ "</i></a> </li></ul>";
				
				countview += 1;
				
				action = vd;
				
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
	
	
	
	@Override
	public ArrayList<ArrayList<String>> getStu_Marks_Replist(String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select distinct  ep.course_id,c.course_name from edu_lms_exam_paper ep\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::text = ep.course_id::text\n"
					+ "inner join edu_lms_course_mstr c on c.id = cm.course_name::int\n"
					+ "where ep.user_id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("course_name"));// 1
				list.add(rs.getString("course_id"));// 0
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
	public ArrayList<ArrayList<String>> getStu_Marks_Reportlist(String userid,String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select distinct sm.id,sm.setname from edu_lms_set_mstr sm\n"
					+ "inner join edu_lms_term_mstr t on t.id= sm.term_id\n"
					+ "inner join edu_lms_student_details sd on sd.semester= t.term\n"
					+ "inner join logininformation l on l.institute_id= sd.institude_userid\n"
					+ "inner join edu_lms_link_course_set_mstr_child smc on smc.set_id=sm.id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=smc.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id::text=ecm.course_name::text\n"
					+ "where l.userid=? and cm.id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			stmt.setInt(2, Integer.parseInt(course_id));
			
			
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("setname"));// 1
				
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
//	 change on 23/11/2022
	@Override
	public ArrayList<ArrayList<String>> getPopup_Datalist(String userid,String course_id,String set_id,String degree_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select sum(case when correct_ans=answer_id then marks else '0' end::int)obtain_marks,sum(marks::int)as total_marks,lcm.course_name,sem.setname,mm.module_name\n"
					+ "					from ( select ep.id,question,marks,exam_name,correct_ans ,ep.module_id\n"
					+ "					from  edu_lms_question_band_parent ep \n"
					+ "					 inner join  edu_lms_question_band_child bc on bc.p_id=ep.id \n"
					+ "					 group by ep.id,question,marks,exam_name,correct_ans) q \n"
					+ "					 inner join edu_lms_exam_paper p on p.quiz_id=q.id  and user_id=? \n"
					+ "					 inner join edu_lms_ele_course_mstr cm on cm.course_name::text=p.course_id::text \n"
					+ "					 inner join edu_lms_course_mstr lcm on lcm.id::text=cm.course_name\n"
					+ "					 inner join edu_lms_link_course_set_mstr_child lcs on lcs.course_id=cm.id \n"
					+ "					 inner join edu_lms_set_mstr sem on sem.id=lcs.set_id\n"
					+ "					inner join edu_lms_module_mstr mm on mm.id=q.module_id\n"
					+" 					 where p.course_id=? and sem.id=? and cm.degree_id=? \n"
					+ "					 group by lcm.course_name,sem.setname,mm.module_name";
			
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			stmt.setInt(2, Integer.parseInt(course_id));
			stmt.setInt(3, Integer.parseInt(set_id));
			stmt.setInt(4, Integer.parseInt(degree_id));
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt======mark======================="+stmt);
			
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				
				list.add(rs.getString("course_name"));// 0
				list.add(rs.getString("setname"));//1
				list.add(rs.getString("module_name"));//2
				list.add(rs.getString("obtain_marks"));//3
				list.add(rs.getString("total_marks"));//4
				
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
	public long DataTableSearch_Stu_MarksDataTotalCount(String Search, String courses, String set,String userid) {
		String SearchValue = GenerateQueryWhereClause_SQL1(Search, courses, set,userid);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			
			q="select count(*) from (select distinct p.course_id,p.set_id,sm.setname,c.course_name \n"
					+ "from edu_lms_question_band_parent p\n"
					+ "inner join edu_lms_set_mstr sm on sm.id=p.set_id\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::text = p.course_id::text\n"
					+ "inner join edu_lms_course_mstr c on c.id = cm.course_name::int\n"
					+ "inner join edu_lms_exam_paper l on l.course_id = p.course_id \n"+ "where p.course_id!=0\n"
					+SearchValue+")a";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, Search, courses, set,userid);
			
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
	public String GenerateQueryWhereClause_SQL1(String Search, String courses, String set,String userId ) {
		
		String SearchValue = "";
		
		
		if (Search!=null) { // for Input Filter
			if(!Search.equals("")){
			SearchValue += " and (  upper(c.course_name) like ? or upper(sm.setname) like ?)";
			System.err.println("globalllll search" + SearchValue);
			}

		}

		/// advance search

		if (!userId.trim().equals("") && userId != null) {
			SearchValue += " and l.user_id=? ";
		}
		if (!courses.trim().equals("0")) {
			SearchValue += " and  p.course_id = ? ";
		}
//		if (!courses.equals("") && courses != null) {
//
//			SearchValue += " and name like ? ";
//		}
		if (!set.trim().equals("0")) {
			SearchValue += " and p.set_id = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search, String courses, String set, String userId) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!userId.equals("0") && userId != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(userId));
			}
			if (!courses.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(courses));
			}
			if (!set.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(set));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

}
