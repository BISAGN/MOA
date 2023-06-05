package com.AyushEdu.dao.TT_Lecture;

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
public class Student_Lecture_DAOImpl implements Student_Lecture_DAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public long DataTableStudentLecturerDataTotalCount(String Search, String orderColunm, String orderType,
			String professional,String start_time,String end_time ,String course_name, String faculty, 
			String topic,String sub_topic, String learning_objective,
			String instructional_method, String sdate, String userId) {

		String SearchValue = GenerateQueryWhereClause_SQL( Search, professional,start_time,end_time,
				course_name, faculty,topic,sub_topic,learning_objective,  instructional_method, sdate,  userId);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by ls.id ASC) as sr_no,ls.id, pm.professional,ls.start_time,ls.end_time,c.course_name,li.login_name as faculty,t.topic,\n"
					+ "lo.b3_learning_obj as learning_objective,im.instructional_method_name as instructional_method, \n"
					+ "TO_CHAR(ls.sdate , 'dd/MON/YYYY') as sdate,TO_CHAR(ls.sdate , 'DD/MM/YYYY') as sd, ls.student_id,st.sub_topic \n"
					+ "from edu_lec_plan_for_student ls\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = ls.professional\n"
					+ "inner join edu_lms_course_mstr c on c.id = ls.course_name\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = ls.topic\n"
					+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = ls.sub_topic\n"
					+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = ls.learning_objective\n"
					+ "inner join edu_lec_instruction_method_mstr im on im.id = ls.instructional_method\n"
					+ "inner join logininformation li on li.userid = ls.faculty\n"
					+ "where  ls.id != 0  \n"
					+  SearchValue + " )ab";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			
			System.err.println("---COUNT Query-->"+q);

			stmt = setQueryWhereClause_SQL(stmt, Search, professional,start_time,end_time,course_name, faculty,topic, sub_topic,learning_objective,  
					 instructional_method, sdate, userId);
		
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
	
	public String GenerateQueryWhereClause_SQL(String Search, String professional,String start_time,String end_time,
			String course_name, String faculty,String topic,String sub_topic ,String learning_objective,  
			String instructional_method,  String sdate, String userId) {

		String SearchValue = "";
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(li.login_name) like ? or  upper(pm.professional) like ? or ls.start_time = ? or ls.end_time = ? or upper(c.course_name) like ? or upper(ls.faculty::CHARACTER VARYING) like ? "
					+ " or upper(t.topic) like ?  or upper(st.sub_topic) like ? or upper(lo.b3_learning_obj) like ?  "
					+ "or upper(im.instructional_method_name) like ?  or TO_CHAR(sdate , 'dd/mm/yyyy') = ? ) ";
		}
		
		if (!professional.equals("0")) {
			SearchValue += " and ls.professional = ? ";
		}
		if (start_time != null && !start_time.equals("")) {
			SearchValue += " and ls.start_time = ? ";
		}
		if (end_time != null && !end_time.equals("")) {
			SearchValue += " and ls.end_time = ? ";
		}
		if ( !course_name.equals("0")) {
			SearchValue += " and ls.course_name = ? ";
		}
		if (!faculty.equals("0")) {
			SearchValue += " and ls.faculty = ? ";
		}
		if (!topic.equals("0")) {
			SearchValue += " and ls.topic = ? ";
		}
		if (sub_topic != null && !sub_topic.equals("0")) {
			SearchValue += " and ls.sub_topic = ? ";
		}
		if (!learning_objective.equals("0")) {
			SearchValue += " and ls.learning_objective = ? ";
		}
		if (!instructional_method.equals("0")) {
			SearchValue += " and ls.instructional_method = ? ";
		}
		if (sdate != null && !sdate.equals("") && !sdate.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(sdate , 'dd/mm/yyyy') = ? ";
	     }
		if (userId != null && !userId.equals("0") && !userId.equals("")) {
			SearchValue += " and ls.student_id = ? ";
		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String professional,String start_time,String end_time, String course_name, String faculty, 
			String topic,String sub_topic, String learning_objective, String instructional_method, String sdate, String userId) {
		int flag = 0;

		try {
			
			if (Search != null && !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag,start_time);
				flag += 1;
				stmt.setString(flag,end_time);
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
				stmt.setString(flag, sdate );
				
			}
			
			if ( !professional.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional));
			}
			if (start_time != null && !start_time.equals("")){
				flag += 1;
				stmt.setString(flag,start_time);
			}
			if (end_time != null && !end_time.equals("")){
				flag += 1;
				stmt.setString(flag,end_time);
			}
			if ( !course_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name));
			}
			if (!faculty.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(faculty));
			}
			if ( !topic.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(topic));
			}
			if ( sub_topic != null && !sub_topic.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(sub_topic));
			}
			if ( !learning_objective.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(learning_objective));
			}
			if ( !instructional_method.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(instructional_method));
			}
			if (sdate != null && !sdate.equals("") && !sdate.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, sdate );
			}
			if ( userId != null && !userId.equals("0") && !userId.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(userId));
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}
	
	public List<Map<String, Object>> DataTableStudentLecturerDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional,String start_time,String end_time, 
			String course_name, String faculty, String topic,String sub_topic,
			String learning_objective, String instructional_method, String sdate, String userId) {

		String SearchValue = GenerateQueryWhereClause_SQL( Search,professional,start_time,end_time,course_name, faculty, topic,sub_topic, learning_objective, 
				instructional_method,  sdate, userId);

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

			if (Search.equals("") && professional.equals("0")  && course_name.equals("0") && topic.equals("0") && faculty.equals("0")  
					&& learning_objective.equals("0") && instructional_method.equals("0") && sdate.equals("0") ) {
				q = "select ROW_NUMBER() OVER(order by ls.id ASC) as sr_no,ls.id, pm.professional,ls.start_time,ls.end_time,c.course_name,li.login_name as faculty,t.topic,\n"
						+ "lo.b3_learning_obj as learning_objective,im.instructional_method_name as instructional_method, \n"
						+ "TO_CHAR(ls.sdate , 'dd/MON/YYYY') as sdate,TO_CHAR(ls.sdate , 'DD/MM/YYYY') as sd, ls.student_id,st.sub_topic, \n"
						+" ls.attended as att,ls.professional as professional_id,ls.course_name as cn,ls.topic as tp,ls.learning_objective as lo,ls.instructional_method as im,ls.faculty as fc,ls.sub_topic as st  \n"
						+ "from edu_lec_plan_for_student ls\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = ls.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = ls.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = ls.topic\n"
						+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = ls.sub_topic\n"
						+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = ls.learning_objective\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = ls.instructional_method\n"
						+ "inner join logininformation li on li.userid = ls.faculty \n"
						+ "where  ls.id != 0 "+ SearchValue
						+ " ORDER BY ls.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			} else {
				
				q = "select ROW_NUMBER() OVER(order by ls.id ASC) as sr_no,ls.id, pm.professional,ls.start_time,ls.end_time,c.course_name,li.login_name as faculty,t.topic,\n"
						+ "lo.b3_learning_obj as learning_objective,im.instructional_method_name as instructional_method, \n"
						+ "TO_CHAR(ls.sdate , 'dd/MON/YYYY') as sdate,TO_CHAR(ls.sdate , 'DD/MM/YYYY') as sd, ls.student_id,st.sub_topic, \n"
						+" ls.attended as att,ls.professional as professional_id,ls.course_name as cn,ls.topic as tp,ls.learning_objective as lo,ls.instructional_method as im,ls.faculty as fc,ls.sub_topic as st  \n"
						+ "from edu_lec_plan_for_student ls\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = ls.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = ls.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = ls.topic\n"
						+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = ls.sub_topic\n"
						+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = ls.learning_objective\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = ls.instructional_method\n"
						+ "inner join logininformation li on li.userid = ls.faculty \n"
						+ "where  ls.id != 0  " 
						+ SearchValue + " ORDER BY ls.id " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, professional,start_time,end_time,course_name,faculty, topic,sub_topic,
					learning_objective, instructional_method, sdate, userId);
			System.err.println("List Query-------"+stmt);
			ResultSet rs = stmt.executeQuery();
         
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("sd") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("sd") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f;
//				+ " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
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

	public ArrayList<ArrayList<String>> getCourse1(String professional) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		System.err.println("============"+userid);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
				q=" select cm.id,cm.course_name from edu_tt_faculty_course_map c \n"
	//					+ "inner join logininformation li on li.userid::character varying  = c.faculty::character varying \n"
						+ "inner join edu_lms_course_mstr cm on cm.id= c.course\n"
						+ "where   c.faculty = ? ";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(professional));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("course_name"));// 0
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
	
	public ArrayList<ArrayList<String>> getfacultydetailsDao1(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
//			q="SELECT li.userid,li.login_name FROM edu_tt_faculty_course_map cmap\n"
//					+ "INNER join logininformation li on cmap.faculty = li.userid\n"
//					+ "where cmap.course = ? \r\n";
			
			q= "SELECT li.userid,li.login_name FROM edu_tt_faculty_course_map cmap\n"
					+ "INNER join logininformation li on cmap.faculty = li.userid\n"
					+ "where cmap.course = ? \r\n";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(course_id));
		
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

	public ArrayList<ArrayList<String>> getTopic_name1(int course_id,String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		System.err.println("============"+course_id);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select tm.topic,tm.id from edu_cc_tb_topics_mstr tm\n"
					+ "where tm.course_id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,course_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("topic"));// 0
				
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
	
	public ArrayList<ArrayList<String>> getSubTopic_name1(int topic_id,String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		System.err.println("============"+topic_id);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select tm.sub_topic,tm.id from edu_cc_tb_sub_topics_mstr tm\n"
					+ "where tm.topic_id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,topic_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("sub_topic"));// 1
				
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
	
	
	public ArrayList<ArrayList<String>> getLearning_Objective1(String topic_id,String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		System.err.println("============"+topic_id);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select lc.id,lc.b3_learning_obj, lc.p_id from edu_cc_tb_t3_learning_object_parent lp\n"
					+ "inner join edu_cc_tb_t3_learning_object_child lc on lc.p_id= lp.id\n"
					+ "where lp.topic_id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(topic_id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("b3_learning_obj"));// 0
				list.add(rs.getString("p_id"));// 0
				
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

	
		///////////////////////////////////////COURSE VALUE FETCH FROM TIME TABLE////////////////////////////////////
		
		
		public ArrayList<ArrayList<String>> LectureDataByDate(int institute_id,String ldate,int professional)  {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		//System.err.println("============"+userid);
		try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		
		q = "select td.course ,td.faculty,td.start_time,td.end_time\n"
		+ "from edu_tt_time_table_details td\n"
		+ "where td.institute_id = ?  and td.professional =? and to_char(td.ldate, 'DD/MM/YYYY') = ? order by start_time";
		
		stmt = conn.prepareStatement(q);
		
		
		
		stmt.setInt(1,institute_id);
		stmt.setInt(2,professional);
		stmt.setString(3, ldate);
		
		System.err.println("FETCH DATA FROM TIME TABLE"+stmt);
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
		
				list.add(rs.getString("start_time"));// 0
				list.add(rs.getString("end_time"));// 1
				list.add(rs.getString("course"));// 2
				list.add(rs.getString("faculty"));// 3
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

		
		///////////////////////////////////////COURSE VALUE FETCH FROM TIME TABLE FOR EDIT SCREEN////////////////////////////////////
				
				
		public ArrayList<ArrayList<String>> LectureDataByDateforEdit(int institute_id,int student_id,String sdate,int professional) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		//System.err.println("============"+userid);
		try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		
		q = "select * from \n"
		+ "edu_lec_plan_for_student td\n"
		+ "where td.institute_id = ? and td.professional = ?  and to_char(td.sdate, 'DD/MM/YYYY') = ? and student_id=? order by start_time";
		
		stmt = conn.prepareStatement(q);
		
		stmt.setInt(1,institute_id);
		stmt.setInt(2,professional);
		stmt.setString(3, sdate);
		stmt.setInt(4, student_id);
		
		
		System.err.println("stmt---Lecture Details--->"+stmt);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("start_time"));// 0
				list.add(rs.getString("end_time"));// 1
				list.add(rs.getString("course_name"));// 2
				list.add(rs.getString("faculty"));// 3
				list.add(rs.getString("topic"));// 4
				list.add(rs.getString("sub_topic"));// 5
				
				list.add(rs.getString("learning_objective"));// 6
				list.add(rs.getString("instructional_method"));// 7
				list.add(rs.getString("id"));// 8
				list.add(rs.getString("attended"));// 9
		
				alist.add(list);
				System.err.println("course====="+list);
				
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

	
			///////////////////////////////////////Total No of Attended Lectures////////////////////////////////////
					
					
			public String TotalAttendedLecture(String student_id)  {
			Connection conn = null;
			String q = "";
			System.err.println("============"+student_id);
			
			String count = "";
			try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
	
			
			q = "select count(*) from edu_lec_plan_for_student where student_id = ? and attended = '1'";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(student_id));
			
			System.err.println("Total No of Attended Lectures--->"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			
			count = rs.getString("count");// 0
//			list.add(rs.getString("end_time"));// 1
//			list.add(rs.getString("course"));// 2
//			list.add(rs.getString("faculty"));// 3
//			alist.add(list);
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
			return count;
} 


			///////////////////////////////////////Total No of Not Attended Lectures////////////////////////////////////
			
			
			public String TotalNotAttendedLecture(String student_id)  {
			Connection conn = null;
			String q = "";
			System.err.println("============"+student_id);
			String count = "";
			try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q = "select count(*) from edu_lec_plan_for_student where student_id = ? and attended = '0'";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(student_id));
			
			System.err.println("Total No of Not Attended Lectures--->"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			
				count = rs.getString("count");// 0
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
			return count;
			} 
			
	///////////////////////////////////////Total No of Lectures////////////////////////////////////
			
			
			public String TotalLecture(String professional,int institute_id)  {
			Connection conn = null;
			String q = "";
			System.err.println("============"+institute_id);
			String count ="";
			try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q = "select count(*) from edu_tt_time_table_details where institute_id =? and professional=?";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,institute_id);
			stmt.setInt(2,Integer.parseInt(professional));
			
			
			System.err.println("Total No of Lectures--->"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				count = rs.getString("count");// 0
			
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
			return count;
			} 
}