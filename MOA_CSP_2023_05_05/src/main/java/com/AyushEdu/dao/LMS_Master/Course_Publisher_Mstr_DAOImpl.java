package com.AyushEdu.dao.LMS_Master;

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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_PUBLISHER_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;

@Repository
public class Course_Publisher_Mstr_DAOImpl implements Course_Publisher_Mstr_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Map<String, Object>> DataTablecourse_publisherDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, status);
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
			if (status.equals("1")) {
				q = "select distinct ROW_NUMBER() OVER(order by name ASC) as ser,id,name,user_id from edu_lms_course_publisher_mstr where status='1' " + SearchValue
						+ " ORDER BY name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select distinct  distinct ROW_NUMBER() OVER(order by name ASC) as ser,id,name,user_id from edu_lms_course_publisher_mstr where status='2' " + SearchValue
						+ " ORDER BY name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name, status);
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
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDCoursepublisher' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='Course_publisherId"
						+ countFunction + "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteCoursepublisher' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DECoursepubId"
						+ countFunctionDelete + "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;

				action = ul;
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
	public long DataTablecourse_publisherDataTotalCount(String Search, String name, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n"
					+ " from (select id,name,user_id from edu_lms_course_publisher_mstr where status='1' " + SearchValue
					+ ") a where id!=0 ";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, name, status);

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
	public String GenerateQueryWhereClause_SQL(String Search, String name, String status) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name) like ? )";
		}

		if (!name.trim().equals("")) {
			SearchValue += " and name like ? ";
		}

		if (!status.trim().equals("0")) {
			SearchValue += " and status like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name,
			String status) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!name.equals("") && name != null) {
				flag += 1;
				stmt.setString(flag, "%" + name + "%");
			}

			if (!status.equals("0") && status != null) {
				flag += 1;
				stmt.setString(flag, "%" + status.toUpperCase() + "%");
//				  stmt.setInt(flag,Integer.parseInt(status)); 
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public EDU_LMS_COURSE_PUBLISHER_MSTR getCourse_PublisherByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_COURSE_PUBLISHER_MSTR updateid = (EDU_LMS_COURSE_PUBLISHER_MSTR) session
				.get(EDU_LMS_COURSE_PUBLISHER_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
