package com.AyushEdu.dao.LMS_NCISM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;

@Repository
public class Student_FreeCourseRepot_DaoImpl implements Student_FreeCourseRepot_DAO {

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

	public ArrayList<ArrayList<String>> getFilterstu_dtl_data(int startPage, int pageLength, String Search,
			String start_date, String orderColunm, String orderType, String end_date, String username,
			String coursename, HttpSession sessionUserId) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search, start_date, end_date, username, coursename);
		String access = (String) sessionUserId.getAttribute("roleType");

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "SELECT distinct ROW_NUMBER() OVER(order by username ASC) as sr_no,id,start_date,end_date,username,coursename from edu_lms_uplode_certificate where id!=0 \n"
				+ SearchValue + " ORDER BY sr_no " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search, start_date, end_date, username, coursename);
			ResultSet rs = stmt.executeQuery();
			//System.err.println("ddddddddddddstmet="+stmt);

			int i = 1;
			int countFunction = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				String action = "";
				String f2 = "";

				String Download = "onclick=\" if (confirm('Are You Sure You Want to Download ?') ){download_file("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f2 = "<ul class='buttons-group'><li><a class='main-btn info-btn btn-hover btn-sm downloadOnclick'  title='Download'><i class='lni lni-download' >"
						+ "<input type='hidden' id='downloadid" + countFunction + "' value=" + rs.getString("id")
						+ "> </i></a></li></ul>";

				action = f2;

				alist.add(rs.getString("start_date"));// 0
				alist.add(rs.getString("end_date"));// 1
				alist.add(rs.getString("username"));// 2
				alist.add(rs.getString("coursename"));// 3
				alist.add(action);// 4
				alist.add(rs.getString("id"));// 5
				alist.add(rs.getString("sr_no"));// 6

				i++;
				countFunction += 1;
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

	public long getTotalstu_dtl_dataCount(String Search, String start_date, String username, String end_date,
			String coursename, HttpSession sessionUserId) {
		String SearchValue = GenerateQueryWhereCandiList(Search, start_date, end_date, username, coursename);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,start_date,end_date,username,coursename from edu_lms_uplode_certificate where id !=0 \n"
					+ SearchValue + ")ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, start_date, end_date, username, coursename);

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

	public String GenerateQueryWhereCandiList(String Search, String start_date, String end_date, String username,
			String coursename) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { 
			SearchValue += " and ( upper(username) like ? or upper(start_date) like ? or upper(end_date) like ? "
					+ " or  upper(coursename) like ? ) ";
		}

		if (!username.equals("0") && username != null) {
			SearchValue += " and upper(username) like ? ";

		}

		if (!coursename.equals("0") && coursename != null) {
			SearchValue += " and upper(coursename) like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String start_date,
			String end_date, String username, String coursename) {

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

			if (!username.equals("0") && username != null) {
				flag += 1;
				stmt.setString(flag, "%" + username.toUpperCase() + "%");
			}

			if (!coursename.equals("0") && coursename != null) {
				flag += 1;
				stmt.setString(flag, "%" + coursename.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	public EDU_LMS_FREE_COURSE getolinecourseByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_FREE_COURSE updateid = (EDU_LMS_FREE_COURSE) session.get(EDU_LMS_FREE_COURSE.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	public String getFilePathQueryForDocFile(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select uplode_certificate from edu_lms_uplode_certificate where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("uplode_certificate");
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
