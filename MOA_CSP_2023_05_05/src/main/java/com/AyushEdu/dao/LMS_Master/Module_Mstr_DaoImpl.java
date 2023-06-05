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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;

@Repository
public class Module_Mstr_DaoImpl implements Module_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTablemoduleDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_id, String status, String module_name, String degree_id,
			String system_id) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_id, module_name, degree_id, system_id);
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

			if (Search.equals("") && module_name.equals("") && status == "1") {

				q = "select ROW_NUMBER() OVER(order by a.id ASC) as ser,a.id,sys.system_name,deg.degree_name,cm.course_name,a.module_name,a.status	 \n"
						+ "from edu_lms_module_mstr a  \n"
						+ "inner join edu_lms_ele_course_mstr pm on pm.id = a.course_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=pm.course_name::int \n"
						+ "inner join edu_lms_degree_mstr deg on deg.id = a.degree_id\n"
						+ "inner join edu_lms_system_mstr sys on sys.id = a.system_id\n"

						+ " where a.status= '1' \n" + " ORDER BY a.id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by a.id ASC) as ser,a.id,sys.system_name,deg.degree_name,cm.course_name,a.module_name,a.status	 \n"
						+ "from edu_lms_module_mstr a  \n"
						+ "inner join edu_lms_ele_course_mstr pm on pm.id = a.course_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=pm.course_name::int \n"
						+ "inner join edu_lms_degree_mstr deg on deg.id = a.degree_id\n"
						+ "inner join edu_lms_system_mstr sys on sys.id = a.system_id\n" + " where a.status= '" + status
						+ "'" + SearchValue + " ORDER BY a.id " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, course_id, module_name, degree_id, system_id);
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

				// -----start
				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				// -----end

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "','" + rs.getString("module_name") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDModule' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='ModId" + countFunction + "' value="
						+ rs.getString("id") + ">" + "<input type='hidden' id='ModName" + countFunction + "' value="
						+ rs.getString("module_name") + ">" + "<input type='hidden' id='ModStatus" + countFunction
						+ "' value=" + rs.getString("status") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteModule' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='DModId" + countFunctionDelete
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
	public long DataTablemoduleDataTotalCount(String Search, String course_id, String status, String module_name,
			String degree_id, String system_id) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_id, module_name, degree_id, system_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select a.id,sys.system_name,deg.degree_name,cm.course_name,a.module_name,a.status\n"
					+ "from edu_lms_module_mstr a  \n"
					+ "inner join edu_lms_ele_course_mstr pm on pm.id = a.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=pm.course_name::int \n"
					+ "inner join edu_lms_degree_mstr deg on deg.id = a.degree_id\n"
					+ "inner join edu_lms_system_mstr sys on sys.id = a.system_id\n" + " where a.status='1' "
					+ SearchValue + ")a  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, course_id, module_name, degree_id, system_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String course_id, String module_name, String degree_id,
			String system_id) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(sys.system_name) like ? or upper(cm.course_name) like ? or upper(deg.degree_name) like ? or upper(a.module_name) like ?)";
			System.err.println("globalllll search" + SearchValue);

		}

		if (!course_id.trim().equals("0")) {
			SearchValue += " and course_id = ? ";

		}
		if (!module_name.trim().equals("")) {
			SearchValue += " and upper(module_name) like ? ";
		}

		if (!degree_id.equals("0")) {
			SearchValue += " and a.degree_id = ? ";
		}

		if (!system_id.equals("0")) {
			SearchValue += " and a.system_id = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course_id,
			String module_name, String degree_id, String system_id) {

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

			}

			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}

			if (!module_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + module_name.toUpperCase() + "%");
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}

			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public EDU_LMS_MODULE_MSTR getmoduleByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_MODULE_MSTR updateid = (EDU_LMS_MODULE_MSTR) session.get(EDU_LMS_MODULE_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
