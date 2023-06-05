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
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;

@Service
@Repository
public class UniversityDaoImpl implements UniversityDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTableUniversDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String university_name, String university_code, String country_id,
			String state_id, String district_id, String city_name, String status, String university_type,
			String organization_id, String system_id) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, university_name, university_code, country_id,
				state_id, district_id, city_name, status, university_type, organization_id, system_id);
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

//			q = " select ROW_NUMBER() OVER(order by ir.id ASC) as ser,ir.id,ir.university_name,ir.university_code,c.name,ir.country_id, s.state_name, d.district_name,\n"
//					+ "ir.city_name,ir.status,ut.university_type,o.organization,sm.system_name From edu_lms_university_mstr ir\n"
//					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
//					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
//					+ "inner join edu_lms_system_mstr sm on sm.id = cast (ir.system_id as integer)\n"
//					+ "inner join edu_lms_university_type_mstr ut on ut.id = cast (ir.university_type as integer)\n"
//					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer)\n"
//					+ "inner join edu_lms_organization_mstr o on o.id = cast(ir.organization_id as integer) where ir.id!=0 "
//					+ SearchValue + " ORDER BY ir.id " + orderColunm + " \n" + orderType + " limit " + pageL + " OFFSET "
//					+ startPage;
			
			q = " select ROW_NUMBER() OVER(order by ir.id ASC) as ser,ir.id,ir.university_name,ir.university_code,c.name,ir.country_id, s.state_name, d.district_name,\n"
					+ "ir.city_name,ir.status,ut.university_type,o.organization,sm.system_name From edu_lms_university_mstr ir\n"
					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
					+ "inner join edu_lms_system_mstr sm on sm.id = cast (ir.system_id as integer)\n"
					+ "inner join edu_lms_university_type_mstr ut on ut.id = cast (ir.university_type as integer)\n"
					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer)\n"
					+ "inner join edu_lms_organization_mstr o on o.id = cast(ir.organization_id as integer) where ir.id!=0 "
					+ SearchValue + " ORDER BY ir.id "  + orderType + " limit " + pageL + " OFFSET "
					+ startPage;


			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, university_name, university_code, country_id, state_id,
					district_id, city_name, status, university_type, organization_id, system_id);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();

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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDUni' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='UniId" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteUni' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEUniId" + countFunctionDelete
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
	public long DataTableUniversDataTotalCount(String Search, String university_name, String university_code,
			String country_id, String state_id, String district_id, String city_name, String status,
			String university_type, String organization_id, String system_id) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, university_name, university_code, country_id,
				state_id, district_id, city_name, status, university_type, organization_id, system_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ir.id,ir.university_name,ir.university_code,c.name,ir.country_id, s.state_name, d.district_name,\n"
					+ "ir.city_name,ir.status,ut.university_type,o.organization,sm.system_name From edu_lms_university_mstr ir\n"
					+ "inner join edu_lms_country_mstr c on c.id = cast(ir.country_id as integer)\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = cast(ir.state_id as integer)\n"
					+ "inner join edu_lms_system_mstr sm on sm.id = cast (ir.system_id as integer)\n"
					+ "inner join edu_lms_university_type_mstr ut on ut.id = cast (ir.university_type as integer)\n"
					+ "inner join edu_lms_district_mstr d on d.district_id = cast(ir.district_id as integer)\n"
					+ "inner join edu_lms_organization_mstr o on o.id = cast(ir.organization_id as integer) where ir.id!=0 "
					+ SearchValue + ")a";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, university_name, university_code, country_id, state_id,
					district_id, city_name, status, university_type, organization_id, system_id);
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
	public String GenerateQueryWhereClause_SQL(String Search, String university_name, String university_code,
			String country_id, String state_id, String district_id, String city_name, String status,
			String university_type, String organization_id, String system_id) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(ir.university_name) like ? or upper(ir.university_code) like ? or upper(c.name) like ?"
					+ "or upper(s.state_name) like ? or upper(d.district_name) like ?"
					+ "or upper(ir.city_name) like ? or ut.university_type like ? or o.organization like ? or sm.system_name like ?) ";
		}

		if (!university_name.trim().equals("")) {
			SearchValue += " and upper(ir.university_name) like ? ";
		}
		if (!university_code.trim().equals("")) {
			SearchValue += " and upper(ir.university_code) like ? ";
		}
		if (!country_id.trim().equals("0")) {
			SearchValue += " and ir.country_id :: int = ? ";
		}
		if (!state_id.trim().equals("0")) {
			SearchValue += " and ir.state_id :: int = ? ";
		}
		if (!district_id.trim().equals("0")) {
			SearchValue += " and ir.district_id :: int = ? ";
		}
		if (!city_name.trim().equals("")) {
			SearchValue += " and upper(ir.city_name) like ? ";
		}
		if (!university_type.equals("0")) {
			SearchValue += " and ir.university_type :: int = ? ";
		}
		if (!organization_id.equals("0")) {
			SearchValue += " and ir.organization_id :: int = ? ";
		}
		if (!system_id.equals("0")) {
			SearchValue += " and ir.system_id :: int = ? ";
		}
		if (status.equals("1")) {
			SearchValue += " and ir.status :: int = ? ";
		}
		if (status.equals("2")) {
			SearchValue += " and ir.status :: int = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String university_name,
			String university_code, String country_id, String state_id, String district_id, String city_name,
			String status, String university_type, String organization_id, String system_id) {

		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!university_name.equals("") && university_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + university_name.toUpperCase() + "%");
			}
			if (!university_code.equals("") && university_code != null) {
				flag += 1;
				stmt.setString(flag, "%" + university_code.toUpperCase() + "%");
			}
			if (!country_id.equals("0") && country_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(country_id));
			}
			if (!state_id.equals("0") && state_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(state_id));
			}
			if (!district_id.equals("0") && district_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(district_id));
			}
			if (!city_name.equals("") && city_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + city_name.toUpperCase() + "%");
			}
			if (!university_type.equals("0") && university_type != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(university_type));
			}
			if (!organization_id.equals("0") && organization_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(organization_id));
			}
			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}

			if (status.equals("1")) {
				flag += 1;
				stmt.setInt(flag, 1);
			}
			if (status.equals("2")) {
				flag += 1;
				stmt.setInt(flag, 2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@Override
	public EDU_LMS_UNIVERSITY_MSTR getUniversByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_UNIVERSITY_MSTR updateid = (EDU_LMS_UNIVERSITY_MSTR) session.get(EDU_LMS_UNIVERSITY_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
}
