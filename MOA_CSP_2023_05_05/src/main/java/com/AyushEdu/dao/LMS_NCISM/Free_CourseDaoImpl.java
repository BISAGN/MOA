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
public class Free_CourseDaoImpl implements Free_CourseDao {

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

	@Override
	public ArrayList<ArrayList<String>> DataTablefc_urlDataList(int startPage, int pageLength, String Search,
			String orderColunm, String coursename, String orderType, String url, String description,
			HttpSession sessionUserId, String role) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search, coursename, url, description);
		String access = (String) sessionUserId.getAttribute("roleType");

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "select ROW_NUMBER() OVER(order by id ASC) as ser,id,coursename, url,description from edu_lms_free_course where id IS NOT NULL " + SearchValue
					+ "\n order by id " + orderType + " limit \n" + pageL + " OFFSET \n" + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, coursename, url, description);
			ResultSet rs = stmt.executeQuery();
		
			int countFunction = 1;
			int countFunction1 = 1;
			int countFunction2 = 1;
			int i = 1;
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm update'  title='Edit'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='update_id" + countFunction2
						+ "' value=" + rs.getInt("id") + "></i></a></li>";


				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm delete'  title='Delete'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='delete_id" + countFunction1
						+ "' value=" + rs.getString("id") + "></i></a></li>";
//
//				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download ?') ){download_file("
//						+ rs.getInt("id") + ")}else{ return false;}\"";
				f2 = "<li><a class='main-btn info-btn btn-hover btn-sm download'  title='Download'>"
						+ "<i class='lni lni-download'>" + "<input type='hidden' id='download_id" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a></li>";

//				if (role.equals("ADMIN")) {
//					ul += f + " " + f1;
//				} else {
//					ul += f2 + " " + f3;
//				}
				ul += f + " " + f1 + " " + f2;
				ul += "</ul>";
				action = ul;
				countFunction += 1;
				countFunction1 += 1;
				countFunction2 += 1;

				alist.add(rs.getString("coursename"));// 0
				alist.add(rs.getString("description"));// 1
				alist.add("<a href=" + rs.getString("url") + " target='_blank' class=\"\">" + rs.getString("url")
						+ "</a> "); // 2
				alist.add(action);// 3
				alist.add(rs.getString("id"));// 4
				alist.add(rs.getString("ser"));// 5

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

	@Override
	public long DataTablefc_urlDataTotalCount(String Search, HttpSession sessionUserId, String url, String coursename,
			String description) {
		String role = sessionUserId.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, url, coursename, description);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,coursename, url,description from edu_lms_free_course where id IS NOT NULL  "
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, coursename, url, description);
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

	public String GenerateQueryWhereCandiList(String Search, String coursename, String url, String description) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(url) like ? or upper(description) like ?)";

		}

		if (!description.equals("") && description != null) {

			SearchValue += " and name like ? ";
		}

		if (url != "" && url != null) {

			SearchValue += " and name like ? ";
		}
		if (!description.equals("") && description != null) {

			SearchValue += " and name like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String coursename,
			String url, String description) {

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

			if (!coursename.equals("") && coursename != null) {
				flag += 1;
				stmt.setString(flag, "%" + coursename + "%");
			}

			if (!url.equals("") && url != null) {
				flag += 1;
				stmt.setString(flag, "%" + url + "%");
			}
			if (!description.equals("") && description != null) {

				flag += 1;
				stmt.setString(flag, "%" + description + "%");
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
			query = "select upload_file from edu_lms_free_course where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("upload_file");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	@Override
	public ArrayList<ArrayList<String>> DataTablefc_urlDataList2(String coursename, String url, String description,
			String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "select id,coursename, url,description,upload_file from edu_lms_free_course where id IS NOT NULL ";

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();

			int i = 1;
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country ?') )"
						+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o'  " + Update + " title='Edit Data'></i>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Inactive Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download ?') ){download_file("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f2 = "<i class='fa fa-download' " + Download + " title='Download' ></i>";

				String certificate = "onclick=\"  if (confirm('Certificate uplode ?') ){ Pop_Up_History("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f3 = "<i class='fa fa-certificate' " + certificate + " title='certificate_uplode' ></i>";

				if (role.equals("NCISM")) {
					action = f + " " + f1;
				} else {
					action = f + " " + f1 + " " + f2 + " " + f3;
				}
				alist.add(rs.getString("coursename"));// 0
				alist.add(rs.getString("description"));// 1
				alist.add("<a href=" + rs.getString("url") + " class=\"\">" + rs.getString("url") + "</a> "); // 2
				alist.add(action);// 3
				alist.add(rs.getString("id"));// 4
				alist.add(rs.getString("url"));// 5
				alist.add(rs.getString("upload_file"));//6

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

}
