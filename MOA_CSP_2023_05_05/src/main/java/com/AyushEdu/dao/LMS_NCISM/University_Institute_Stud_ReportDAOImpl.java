package com.AyushEdu.dao.LMS_NCISM;

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
public class University_Institute_Stud_ReportDAOImpl implements University_Institute_Stud_ReportDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> getInstituteListFromUniversity(String userId) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select ir.id,ir.institute_name from edu_lms_institute_reg ir\n"
					+ "inner join logininformation lo on lo.university_id=ir.university_id\n"
					+ "where ir.status='1' and userid=? ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1, Integer.parseInt(userId));

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

	public ArrayList<ArrayList<String>> getDegreeListFromInstitute(String institute_id, String userId, String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM")) {
				qry = "where lo.userid=?";
			} else {
				qry = "where lo.institute_id=?";
			}

			q = "select distinct dm.id,dm.degree_name from edu_lms_degree_mstr dm \n"
					+ " inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
					+ " inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;

			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM")) {
				stmt.setObject(1, Integer.parseInt(userId));
			} else {
				stmt.setObject(1, Integer.parseInt(institute_id));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("degree_name"));// 1

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

	public ArrayList<ArrayList<String>> getCoursesListFromDegree(String degree_id, String userId) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct ecm.id as id,cm.course_name from edu_lms_system_ele_course_link sel\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=sel.elec_course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id::text=ecm.course_name\n"
					+ "where sel.degree_id=? order by course_name";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setObject(1, Integer.parseInt(degree_id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 1

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

	public ArrayList<ArrayList<String>> getModuleListFromCourses(String course_id, String userId) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select m.id,m.module_name from edu_lms_module_mstr m\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=m.course_id\n" + "where m.course_id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setObject(1, Integer.parseInt(course_id));
			// stmt.setObject(2,Integer.parseInt(userId));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("module_name"));// 1

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

	public List<Map<String, Object>> DataTableUniversityInstituteStudReportDetailsDataList(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String userId, String degree_id,
			String course_id, String module_id, String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, userId, degree_id, course_id, module_id, role);
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

			q = " select distinct ROW_NUMBER() OVER(order by sd.id ASC) as sr_no, sd.id,sd.ayush_id,sd.name,sd.mobile_no,sd.gender,ir.institute_name,\n"
					+ " string_agg(distinct cm.course_name::text,',') as course_name ,dm.degree_name,li.userid,li.institute_id,gm.gender_name \n"
					+ " from logininformation li  \n"
					+ " inner join edu_lms_exam_paper ep on ep.user_id=li.userid\n"
					+ " inner join edu_lms_student_details sd on sd.aadhar_card=li.username\n"
					+ " inner join edu_lms_institute_reg ir on ir.id=li.institute_id\n"
					+ " inner join edu_lms_ele_course_mstr ecm on ecm.course_name::text=ep.course_id::text\n"
					+ " inner join edu_lms_course_mstr cm on cm.id::text=ecm.course_name::text\n"
					+ " inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ " inner join edu_lms_gender_mstr gm on gm.id=sd.gender::int\n"
					+ " where sd.id !=0  " + SearchValue
					+ " group by sd.id,sd.ayush_id,sd.name,sd.mobile_no,sd.gender,ir.institute_name,dm.degree_name,li.userid,li.institute_id,gm.gender_name \n"
					+ "ORDER BY  sd.id \n" + orderType + " limit " + pageL + " OFFSET " + startPage + " ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, userId, degree_id, course_id, module_id, role);
			ResultSet rs = stmt.executeQuery();
			System.err.println("----stmt 02 "+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String popup = "";
				String action = "";

				String ADD = "onclick=\" Pop_Up_History1('" + rs.getString("id") + "','" + rs.getString("institute_id")
						+ "','" + rs.getString("course_name") + "','" + rs.getString("userid") + "') \"";
				popup = "<ul class='buttons-group' data-toggle='modal' data-target='.bd-example-modal-lg'><li><a class='main-btn dark-btn btn-hover btn-sm btnview View_Poppup' value='VIEW RESULT'  title='VIEW RESULT' >"
						+ "<i class='lni lni-eye'>" + "<input type='hidden' id='poppup_Id" + countFunction + "' value="
						+ rs.getString("id") + ">" + "<input type='hidden' id='institute_id" + countFunction
						+ "' value=" + rs.getString("institute_id") + ">" + "<input type='hidden' id='course_name"
						+ countFunction + "' value=" + rs.getString("course_name") + ">"
						+ "<input type='hidden' id='userid" + countFunction + "' value=" + rs.getString("userid")
						+ "></i></a></li></ul>";

				countFunction += 1;
				action = popup;
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
	public long DataTableUniversityInstituteStudReportDetailsDataTotalCount(String Search, String userId,
			String degree_id, String course_id, String module_id, String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, userId, degree_id, course_id, module_id, role);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct sd.id,sd.ayush_id,sd.name,sd.mobile_no,sd.gender,ir.institute_name,string_agg(distinct cm.course_name::text,',') as course_name ,dm.degree_name,li.userid,li.institute_id,gm.gender_name "
					+ " from logininformation li  \n"
					+ " inner join edu_lms_exam_paper ep on ep.user_id=li.userid\n"
					+ " inner join edu_lms_student_details sd on sd.aadhar_card=li.username\n"
					+ " inner join edu_lms_institute_reg ir on ir.id=li.institute_id\n"
					+ " inner join edu_lms_ele_course_mstr ecm on ecm.course_name::text=ep.course_id::text\n"
					+ " inner join edu_lms_course_mstr cm on cm.id::text=ecm.course_name::text\n"
					+ " inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n" 
					+" inner join edu_lms_gender_mstr gm on gm.id=sd.gender::int \n"
					+ " where sd.id !=0 " + SearchValue
					+ " group by sd.id,sd.ayush_id,sd.name,sd.mobile_no,sd.gender,ir.institute_name,dm.degree_name,li.userid,li.institute_id,gm.gender_name ) ab";

			PreparedStatement stmt = conn.prepareStatement(q);
			// stmt.setInt(1,Integer.parseInt(institute_id));
			stmt = setQueryWhereClause_SQL(stmt, Search, userId, degree_id, course_id, module_id, role);

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
	public String GenerateQueryWhereClause_SQL(String Search, String userId, String degree_id, String course_id,
			String module_id, String role) {

		String SearchValue = "";

		System.err.println("---rl" + role);

		if (role.trim().equals("Institute_NCISM")) {
			if (!userId.equals("")) {
				SearchValue += " and sd.institude_id = ? ";
			}
		} else {
			if (!userId.equals("")) {
				SearchValue += " and sd.university_userid = (select distinct university_id from logininformation where userid = ? ) ";
			}
		}

		if (!degree_id.trim().equals("0")) {
			SearchValue += " and sd.degree = ? ";
		}
		if (!course_id.trim().equals("0")) {
			SearchValue += " and ecm.id  = ? ";
		}

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sd.ayush_id) like ? or upper(sd.name) like ? or upper(sd.mobile_no) like ? or upper(gm.gender_name ) like ? or upper(cm.course_name) like ? \n"
					+ "or upper(ir.institute_name) like ? or upper(dm.degree_name) like ?  )";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String userId,
			String degree_id, String course_id, String module_id, String role) {

		int flag = 0;
		try {

			if (!userId.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(userId));
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!course_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));

			}

			if (Search != null && !Search.equals("")) { // for Input Filter

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

	
	//change on 23/11/2022
	public ArrayList<ArrayList<String>> getPopup_Data(String userId,String degree_id,String term_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select sum(case when correct_ans=answer_id then marks else '0' end::int)obtain_marks,sum(marks::int)as total_marks,lcm.course_name,sem.setname\n"
					+ "from ( select ep.id,question,marks,exam_name,correct_ans \n"
					+ "from  edu_lms_question_band_parent ep \n"
					+ " inner join  edu_lms_question_band_child bc on bc.p_id=ep.id \n"
					+ " group by ep.id,question,marks,exam_name,correct_ans) q inner join edu_lms_exam_paper p on p.quiz_id=q.id  and user_id=? \n"
					+ " inner join edu_lms_ele_course_mstr cm on cm.course_name::text=p.course_id::text \n"
					+ " inner join edu_lms_course_mstr lcm on lcm.id::text=cm.course_name\n"
					+ " inner join edu_lms_link_course_set_mstr_child lcs on lcs.course_id=cm.id \n"
					+ " inner join edu_lms_set_mstr sem on sem.id=lcs.set_id\n"
					+ " where cm.degree_id=? and sem.term_id =?\n"
					+ " group by lcm.course_name,sem.setname ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setObject(1, Integer.parseInt(userId));
			stmt.setInt(2, Integer.parseInt(degree_id));
			stmt.setInt(3, Integer.parseInt(term_id));
			ResultSet rs = stmt.executeQuery();
			
			System.err.println("------------stmt poppup marksheet "+stmt);

			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
//				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("setname"));// 1
				alist.add(rs.getString("course_name"));// 2
				alist.add(rs.getString("obtain_marks"));// 3
				alist.add(rs.getString("total_marks"));// 4

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

}
