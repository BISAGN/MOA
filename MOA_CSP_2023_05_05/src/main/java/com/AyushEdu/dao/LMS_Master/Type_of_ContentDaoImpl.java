package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_CONTENT_MSTR;

import freemarker.core.ParseException;

@Repository
public class Type_of_ContentDaoImpl implements Type_of_ContentDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTabletype_of_contentDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_content, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_content);
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

			if (Search.equals("") && type_of_content.equals("") && status == "1") {

				q = " select ROW_NUMBER() OVER(order by type_of_content ASC) as ser,id,type_of_content,status \n" + "	from edu_lms_type_of_content_mstr \n"
						+ " where status= '1' \n" + " ORDER BY type_of_content " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by type_of_content ASC) as ser,id,type_of_content,status " + "	 from edu_lms_type_of_content_mstr  " + " where status= '"
						+ status + "'" + SearchValue + " ORDER BY type_of_content " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_content);

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
						+ rs.getString("id") + "','" + rs.getString("type_of_content") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDContent' title='Edit Data'></i>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='ContntId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='tyContnt" + countFunction
						+ "' value=" + rs.getString("type_of_content") + ">" + "<input type='hidden' id='ContntStatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteContent' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEContntId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

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
	public long DataTabletype_of_contentDataTotalCount(String Search, String type_of_content, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_content);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id, type_of_content,status from edu_lms_type_of_content_mstr \n"
					+ " where status='1' " + SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_content);

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
	public String GenerateQueryWhereClause_SQL(String Search, String type_of_content) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(type_of_content) like ? )";
			System.err.println("globalllll search" + SearchValue);
		}

		if (!type_of_content.trim().equals("")) {
			SearchValue += " and upper(type_of_content) like ? ";
			System.err.println("parameter search" + SearchValue);
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String type_of_content) {

		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!type_of_content.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + type_of_content.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public EDU_LMS_TYPE_OF_CONTENT_MSTR gettype_of_contentByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_TYPE_OF_CONTENT_MSTR updateid = (EDU_LMS_TYPE_OF_CONTENT_MSTR) session
				.get(EDU_LMS_TYPE_OF_CONTENT_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
