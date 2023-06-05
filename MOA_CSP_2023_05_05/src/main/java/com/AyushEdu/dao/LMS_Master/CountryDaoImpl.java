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

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;

@Repository
public class CountryDaoImpl implements CountryDao {

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

	public List<Map<String, Object>> DataTableCountryDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String country, String status) {

		String SearchValue = GenerateQueryWhereCandiList(Search, country);
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

			if (Search.equals("") && country.equals("") && status == "1") {

				q = "select ROW_NUMBER() OVER(order by name ASC) as ser,id,name,status from edu_lms_country_mstr where status= '1'  " + SearchValue
						+ " ORDER BY name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by name ASC) as ser,id,name,status from edu_lms_country_mstr where status= '" + status + "'  " + SearchValue
						+ " ORDER BY name " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search, country);

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

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country?') )"
						+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDCountry' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='CounId" + countFunction
						+ "' value=" + rs.getInt("id") + "></i></a> </li>";

				String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete Country ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteCoun' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='DCounId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction++;
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

	public long DataTableCountryDataTotalCount(String Search, HttpSession session, String country) {
		String role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, country);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id,name,status from edu_lms_country_mstr where status= '1'  "
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereCandiList(stmt, Search, country);
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

	public String GenerateQueryWhereCandiList(String Search, String country) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(name) like ? )";

		}
		if (!country.equals("") && country != null) {
			SearchValue += " and upper(name) like ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String country) {

		int flag = 0;
		try {

			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!country.equals("") && country != null) {
				flag += 1;
				stmt.setString(flag, "%" + country.toUpperCase() + "%");
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

	public ArrayList<ArrayList<String>> search_Country_report() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct id,name from edu_lms_country_mstr  " + "where id !=0 ";
			stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				String id = String.valueOf(i++);
				list.add(id);
				list.add(rs.getString("name"));
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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

	public TB_COUNTRY getCountryByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_COUNTRY updateid = (TB_COUNTRY) session.get(TB_COUNTRY.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
