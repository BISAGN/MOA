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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_LEVEL_MSTR;

@Repository
public class LevelDAOImpl implements LevelDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTablelevelDataTotalCount(String Search, String level_name, String sequence_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, level_name, sequence_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,level_name,sequence_name,status from edu_lms_level_mstr where status='1'"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, level_name, sequence_name);

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
	public String GenerateQueryWhereClause_SQL(String Search, String level_name, String sequence_name) {
		String SearchValue = "";

		if (Search != null) { // for Input Filter
			SearchValue += " and ( upper(level_name) like ? or upper(sequence_name) like ?)";
		}

		if (!level_name.trim().equals("")) {
			SearchValue += " and upper(level_name) like ? ";
		}
		if (!sequence_name.trim().equals("")) {
			SearchValue += " and upper(sequence_name) like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String level_name,
			String sequence_name) {
		int flag = 0;
		try {

			if (Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!level_name.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + level_name.toUpperCase() + "%");
			}
			if (!sequence_name.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + sequence_name.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTablelevelDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String level_name, String status, String sequence_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, level_name, sequence_name);
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

			if (Search.equals("") && level_name.equals("0") && status == "1") {
				q = "select ROW_NUMBER() OVER(order by level_name ASC) as ser,id,sys\n"
						+ "	public List<Map<String, Object>> DataTablelevelDataList(int startlevel,status from edu_lms_level_mstr where status= '1'  "
						+ " ORDER BY level_name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by level_name ASC) as ser,id,level_name,sequence_name,status from edu_lms_level_mstr where status='" + status + "'"
						+ SearchValue + " ORDER BY level_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, level_name, sequence_name);
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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDLevel' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='LevelId" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteLevel' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DELevelId" + countFunctionDelete
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

	public EDU_LMS_LEVEL_MSTR getlevelByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_LEVEL_MSTR updateid = (EDU_LMS_LEVEL_MSTR) session.get(EDU_LMS_LEVEL_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
}
