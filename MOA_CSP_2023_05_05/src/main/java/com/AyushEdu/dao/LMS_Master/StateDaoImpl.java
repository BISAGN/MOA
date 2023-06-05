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

import com.AyushEdu.Models.TB_STATE;

@Repository
public class StateDaoImpl implements StateDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public TB_STATE getstateByid(int id) {
		Session sessionHQL = sessionFactory.openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_STATE updateid = (TB_STATE) sessionHQL.get(TB_STATE.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> search_StateReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct s.state_id,s.state_name,c.name from edu_lms_state_mstr s \r\n"
					+ "					 left join edu_lms_country_mstr c on s.country_id = c.id \r\n"
					+ "					order by s.state_id desc";

			stmt = conn.prepareStatement(q);
			int i = 1;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("name"));
				list.add(rs.getString("state_name"));
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

	public List<Map<String, Object>> State_nameDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String state_name, String status, String country_id,
			String state_abbr) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, state_name, country_id, state_abbr);
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

			if (Search.equals("") && state_name.equals("") && status == "1" && state_abbr.equals("")) {

				q = "select ROW_NUMBER() OVER(order by a.state_id ASC) as ser,a.state_id,c.name as country_name,a.state_name,a.status,a.state_abbr from edu_lms_state_mstr a  inner join edu_lms_country_mstr c on c.id = a.country_id where a.status= '1'"
						+ SearchValue + " ORDER BY state_id " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by a.state_id ASC) as ser,a.state_id,c.name as country_name,a.state_name,a.status,a.state_abbr from edu_lms_state_mstr a  inner join edu_lms_country_mstr c on c.id = a.country_id where a.status= '"
						+ status + "'" + SearchValue + " ORDER BY state_id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, state_name, country_id, state_abbr);

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
						+ rs.getInt("state_id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDState' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='StateId" + countFunction
						+ "' value=" + rs.getInt("state_id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getInt("state_id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteState' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='DStateId" + countFunctionDelete
						+ "' value=" + rs.getInt("state_id") + "></i></a> </li>";

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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String state_name, String country_id, String state_abbr) {

		String SearchValue = "";

		if (!state_name.equals("") && state_name != null) {

			SearchValue += " and upper(state_name) like ? ";
		}

		if (!country_id.equals("0") && country_id != null) {

			SearchValue += " and country_id::int = ? ";
		}
		if (!state_abbr.equals("") && state_abbr != null) {

			SearchValue += " and upper(state_abbr) like ? ";
		}

		if (Search != null && !Search.equals("")) {

			SearchValue += " and (  upper(c.name ) like ? or upper(a.state_name) like ? or upper(a.state_abbr) like ? )";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String state_name,
			String country_id, String state_abbr) {

		int flag = 0;
		try {

			if (!state_name.equals("") && state_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + state_name.toUpperCase() + "%");
			}
			if (!country_id.equals("0") && country_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(country_id));

			}
			if (!state_abbr.equals("") && state_abbr != null) {
				flag += 1;
				stmt.setString(flag, "%" + state_abbr.toUpperCase() + "%");
			}

			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	@Override
	public long DataTotalCount(String search, String state_name, String country_id, String state_abbr) {
		String SearchValue = GenerateQueryWhereClause_SQL(search, state_name, country_id, state_abbr);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select a.state_id,c.name as country_name,a.state_name,a.status,a.state_abbr \n"
					+ "from edu_lms_state_mstr a  inner join edu_lms_country_mstr c on c.id = a.country_id where a.status= '1'  "
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, state_name, country_id, state_abbr);

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

	public String GenerateQueryWhereCandiList(String Search, String country_id, String state_name, String state_abbr) {
		String SearchValue = "";

		if (!country_id.equals("") && country_id != null) {

			SearchValue += " and country_id like ? ";
		}

		if (!state_name.equals("") && state_name != null) {

			SearchValue += " and state_name like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String state_name,
			String country_id, String state_abbr) {

		int flag = 0;
		try {

			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!state_name.equals("") && state_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + state_name + "%");
			}
			if (!country_id.equals("") && country_id != null) {
				flag += 1;
				stmt.setString(flag, "%" + country_id + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}

	public boolean checkIsDoubleValue(String Search) {
		return Search.matches("[0-9.]+");
	}

}
