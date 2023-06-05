
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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;

@Repository
public class DistrictDaoImpl implements DistrictDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Map<String, Object>> search_District_name(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String country, String state, String district, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, country, state, district);
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

			if (Search.equals("") && district.equals("") && status == "1") {

				q = " select ROW_NUMBER() OVER(order by d.district_name ASC) as ser,d.district_id as id,d.district_name,b.name,e.state_name,d.status from edu_lms_district_mstr d left join edu_lms_country_mstr b on b.id = d.country_id left join edu_lms_state_mstr e on e.state_id = d.state_id where d.status='1' "
						+ SearchValue + " order by  d.district_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			} else {
				q = " select ROW_NUMBER() OVER(order by d.district_name ASC) as ser,d.district_id as id,d.district_name,b.name,e.state_name,d.status from edu_lms_district_mstr d left join edu_lms_country_mstr b on b.id = d.country_id left join edu_lms_state_mstr e on e.state_id = d.state_id where d.status='"
						+ status + "' " + SearchValue + " order by  d.district_name " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, country, state, district);

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
						+ rs.getString("id") + "','" + rs.getString("district_name") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDDistrict' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='DistrictId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='DistName" + countFunction
						+ "' value=" + rs.getString("district_name") + ">" + "<input type='hidden' id='DistStatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteDist' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEDistId" + countFunctionDelete
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

	public long DataTabledistrictDataTotalCount(HttpSession sessionUserId, String Search, String country, String state,
			String district) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, country, state, district);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select d.district_id as id,d.district_name,b.name,e.state_name,d.status from edu_lms_district_mstr d left join edu_lms_country_mstr b on b.id = d.country_id left join edu_lms_state_mstr e on e.state_id = d.state_id where d.status='1'  "
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, country, state, district);
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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String country, String state, String district) {

		String SearchValue = "";

		if (!Search.equals("") && Search != null) { // for Input Filter
			SearchValue += " and ( upper(b.name) like ? or upper(state_name) like ? or upper(district_name) like ?)";
		}

		if (!country.equals("0") && country != "0") {
			SearchValue += " and d.country_id = ? ";
		}
		if (!state.equals("0") && state != "0") {
			SearchValue += " and d.state_id = ? ";
		}
		if (!district.trim().equals("")) {
			SearchValue += " and upper(d.district_name) like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String country,
			String state, String district) {
		int flag = 0;
		try {
			if (!Search.equals("") && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!country.equals("0") && country != "0") {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(country));
			
			}
			if (!state.equals("0") && state != "0") {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(state));
			}
			if (!district.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + district.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public EDU_LMS_DISTRICT_MSTR getDistrictByid(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		EDU_LMS_DISTRICT_MSTR updateid = (EDU_LMS_DISTRICT_MSTR) sessionHQL.get(EDU_LMS_DISTRICT_MSTR.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> getCountry_List(String country_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = " select sm.state_id,sm.country_id,sm.state_name\r\n" + "from edu_lms_state_mstr sm \r\n"
					+ "inner join edu_lms_country_mstr cm on cm.id=sm.country_id  \r\n"
					+ " where sm.status='1' and sm.country_id=?  ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(country_id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("state_id"));// 0
				alist.add(rs.getString("state_name"));// 1

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
