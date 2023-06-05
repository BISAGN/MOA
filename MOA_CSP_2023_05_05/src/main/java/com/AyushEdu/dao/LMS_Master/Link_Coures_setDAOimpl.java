package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Link_Coures_setDAOimpl implements Link_Coures_setDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> DataTable_DataList(int startPage, int pageLength, String Search,
			String system_id, String degree_id, String orderColunm, String orderType, HttpSession sessionUserId) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search, system_id, degree_id);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();


			q = "select ROW_NUMBER() OVER(order by csp.id ) as sr_no,csp.id as cid, csc.set_id, sm.system_name,dm.degree_name,csc.set_demo_video,lsm.setname, string_agg(cast (cm2.course_name as text), ', ')  as course_name \r\n"
					+ "from edu_lms_link_course_set_mstr csp\r\n"
					+ "inner join edu_lms_link_course_set_mstr_child csc on csc.p_id =csp.id\r\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=csc.course_id\r\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \r\n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=csc.set_id\r\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=csp.system_id\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=csp.degree_id where csp.id !='0'  " + SearchValue
					+ "\r\n" + "GROUP by 2,3,4,5,6,7 \n" + "order by  sr_no " + orderType + "\n limit " + pageL
					+ " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt ----->  " + stmt);
			stmt = setQueryWhereCandiList(stmt, Search, system_id, degree_id);
			ResultSet rs = stmt.executeQuery();

			int countFunction = 1;
			int countFunction1 = 1;

			int i = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				String action = "";
				if (rs.getString("set_demo_video") != null) {
					action = "<ul class='buttons-group'><li><a class='main-btn active-btn btn-hover btn-sm ocVC' ><i class='bi bi-play-circle'>"
							+ "<input type='hidden' id='cId" + countFunction + "' value=" + rs.getString("cid") + ">"
							+ "<input type='hidden' id='SetId" + countFunction1 + "' value=" + rs.getString("set_id")
							+ "></i></a></li></ul>";

				}
				alist.add(rs.getString("system_name"));// 0
				alist.add(rs.getString("degree_name"));// 1
				alist.add(rs.getString("setname"));// 2
				alist.add(rs.getString("course_name"));// 3
				
				alist.add(action);//4
				alist.add(rs.getString("sr_no"));// 5
				i++;
				countFunction += 1;
				countFunction1 += 1;
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

	public long DataTable_DataTotalCount(String Search, HttpSession sessionUserId, String system_id, String degree_id) {
		// String role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, system_id, degree_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select csp.id as cid, csc.set_id, sm.system_name,dm.degree_name,csc.set_demo_video,lsm.setname, string_agg(cast (cm2.course_name as text), ', ')  as course_name \r\n"
					+ "from edu_lms_link_course_set_mstr csp\r\n"
					+ "inner join edu_lms_link_course_set_mstr_child csc on csc.p_id =csp.id\r\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=csc.course_id\r\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \r\n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=csc.set_id\r\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=csp.system_id\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=csp.degree_id where csp.id !='0'  " + SearchValue
					+ "\r\n" + "GROUP by 1,2,3,4,5,6)ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, system_id, degree_id);
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

	public String GenerateQueryWhereCandiList(String Search, String system_id, String degree_id) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(dm.degree_name) like ? or upper(lsm.setname) like ? or upper(cm2.course_name) like ?)";
			System.err.println("globalllll search" + SearchValue);
		}

		if (!system_id.equals("0")) {
			SearchValue += " and csp.system_id = ?";
		}
		if (!degree_id.equals("0")) {

			SearchValue += " and csp.degree_id = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String system_id,
			String degree_id) {

		System.err.println("system_id" + system_id);

		int flag = 0;
		try {

			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0")) {

				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

}
