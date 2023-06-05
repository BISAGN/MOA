package com.AyushEdu.dao.Curriculum;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Nch_T4_Learning_Object_DaoImpl implements Nch_T4_Learning_Object_Dao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTableNchT4SearchLearningObjectDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				practical_id);
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

			q = " select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pc.practical\n"
					+ "from edu_cc_tb_nch_t4_learning_object_parent p\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pc on pc.id=p.practical_id\n" + "where p.status=0 "
					+ sd + SearchValue + "ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET "
					+ startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,
					practical_id);
			System.err.println("stmt--T4 table--------->  " + stmt);
			// stmt.setInt(1, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction = 1;
			int countFunctionDelete = 1;
			int countview = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				String vd = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDtopic' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='topicId" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";
				// f = "<li><a class='main-btn dark-btn-outline btn-hover btn-sm VIEWdetails'
				// value='ADD' title='View Data' >\n"
				// + " <i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"'
				// value="+rs.getString("id")+"></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteTopic' value='ADD' title='Delete Data' >"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='deleteID" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a></li>";

				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
						+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId" + countview + "' value="
						+ rs.getString("id") + "></i></a> </li></ul>";

				ul += f + " " + f1;

				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;
				countview += 1;

				action = ul;
				columns.put("vd", vd);
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ " or upper(pm.professional) like ?  or upper(cc.course_name) like ?"
					+ " or upper(pc.practical) like ? )";
		}
		/// advance search
		if (!system_id.equals("0")) {
			SearchValue += " and p.system_id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and p.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and p.course_id =? ";
		}
		if (!practical_id.equals("0")) {
			SearchValue += " and p.practical_id =? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String practical_id) {
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
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (!practical_id.equals("0") && practical_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(practical_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public long DataTableNchT4SearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(search, system_id, degree_id, professional_id, course_id,
				practical_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pc.practical\n"
					+ "from edu_cc_tb_nch_t4_learning_object_parent p\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pc on pc.id=p.practical_id\n" + "where p.status=0 "
					+ sd + SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, system_id, degree_id, professional_id, course_id,
					practical_id);
			System.err.println("stmt---Topic---------count---->    " + stmt);
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

	@Override
	public ArrayList<ArrayList<String>> getPopup_NchChild_LearningDatalist(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select c.generic_compte,c.subject_area,c.specific_compet,c.spec_learn_object,c.integ_horivtspi,\n"
					+ "ru.rep_und,dm.domain,gl.guilberts_level,sm.scope,tlm.method,fum.formative,tm.practical as practical,tem.term from edu_cc_tb_nch_t4_learning_object_parent p\n"
					+ "inner join edu_cc_tb_nch_t4_learning_object_child c on c.p_id = p.id\n"
					+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on c.millers_knows=ru.id\n"
					+ "inner join edu_cc_tb_c3_domain_mstr dm on dm.id=c.blooms_domain\n"
					+ "inner join edu_cc_tb_guilberts_level_mstr gl on c.guilberts_level=gl.id\n"
					+ "inner join edu_cc_tb_d3_scope_understanding_mstr sm on c.must_know_dknow_nk=sm.id\n"
					+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on c.tl_mthd_med=tlm.id\n"
					+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fum on c.formtive_assessmt=fum.id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr tm on tm.id=p.practical_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr tem on tem.id = c.nch_term\n"
					+ "where p.status=0 and p.course_id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("Child Table Data====riddhi==29=====" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("practical"));// 0
				list.add(rs.getString("generic_compte"));// 1
				list.add(rs.getString("subject_area"));// 2
				list.add(rs.getString("rep_und"));// 3
				list.add(rs.getString("specific_compet"));// 4
				list.add(rs.getString("spec_learn_object"));// 5
				list.add(rs.getString("domain"));// 6
				list.add(rs.getString("guilberts_level"));// 7
				list.add(rs.getString("scope"));// 8
				list.add(rs.getString("method"));// 9
				list.add(rs.getString("formative"));// 10
				list.add(rs.getString("formative"));// 11
				list.add(rs.getString("integ_horivtspi"));// 12
				list.add(rs.getString("term"));// 13

				alist.add(list);
				System.err.println("listtttttttttttttttt" + list);
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

	public ArrayList<ArrayList<String>> GetNchLearning_Parent_Data(int id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = " select * from edu_cc_tb_nch_t4_learning_object_parent\n" + "where status=0 and id=? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			System.err.println("STMT---EDIT---PARENT---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("system_id")); // 1
				alist.add(rs.getString("degree_id")); // 2
				alist.add(rs.getString("professional_id")); // 3
				alist.add(rs.getString("course_id")); // 4
				alist.add(rs.getString("practical_id")); // 5
				alist.add(rs.getString("pract_learning_obj")); // 6
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
				}
			}
		}
		return list;
	}

	public ArrayList<ArrayList<String>> getNchLearning_Child_By_id(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select * from edu_cc_tb_nch_t4_learning_object_child\n" + "where status=0 and p_id=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data===========" + stmt);

			String lite_hdid = "";
			String add_more = "";
			String generic_compte = "";
			String subject_area = "";
			String millers_knows = "";
			String specific_compet = "";
			String spec_learn_object = "";
			String blooms_domain = "";
			String guilberts_level = "";
			String must_know_dknow_nk = "";
			String tl_mthd_med = "";
			String formtive_assessmt = "";
			String sumtive_assessmt = "";
			String integ_horivtspi = "";
			String nch_term = "";

			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '"
						+ rs.getString("id") + "' size='1'>"; // 0

				generic_compte = "<input type='text' id = 'generic_compte" + i + "'  name='generic_compte" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("generic_compte") + "' />";

				subject_area = "<input type='text' id = 'subject_area" + i + "'  name='subject_area" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("subject_area") + "' />";

				millers_knows = "<select id='millers_knows" + i + "' name='millers_knows" + i
						+ "' class = 'form-control'></select>";

				specific_compet = "<input type='text' id = 'specific_compet" + i + "'  name='specific_compet" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("specific_compet") + "' />";

				spec_learn_object = "<input type='text' id = 'spec_learn_object" + i + "'  name='spec_learn_object" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("spec_learn_object") + "' />";

				blooms_domain = "<select id='blooms_domain" + i + "' name='blooms_domain" + i
						+ "' class = 'form-control'></select>";

				guilberts_level = "<select id='guilberts_level" + i + "' name='guilberts_level" + i
						+ "' class = 'form-control'></select>";

				must_know_dknow_nk = "<select id='must_know_dknow_nk" + i + "' name='must_know_dknow_nk" + i
						+ "' class = 'form-control'></select>";

				tl_mthd_med = "<select id='tl_mthd_med" + i + "' name='tl_mthd_med" + i
						+ "' class = 'form-control'></select>";

				formtive_assessmt = "<select id='formtive_assessmt" + i + "' name='formtive_assessmt" + i
						+ "' class = 'form-control'></select>";

				sumtive_assessmt = "<select id='sumtive_assessmt" + i + "' name='sumtive_assessmt" + i
						+ "' class = 'form-control'></select>";

				integ_horivtspi = "<input type='text' id = 'integ_horivtspi" + i + "'  name='integ_horivtspi" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("integ_horivtspi") + "' />";

				nch_term = "<select id='nch_term" + i + "' name='nch_term" + i + "' class = 'form-control'></select>";

				if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i
							+ "' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}

				list.add(rs.getString("id")); // 0
				list.add(rs.getString("generic_compte"));// 1
				list.add(rs.getString("subject_area"));// 2
				list.add(rs.getString("millers_knows"));// 3
				list.add(rs.getString("specific_compet"));// 4
				list.add(rs.getString("spec_learn_object"));// 5
				list.add(rs.getString("blooms_domain"));// 6
				list.add(rs.getString("guilberts_level"));// 7
				list.add(rs.getString("must_know_dknow_nk"));// 8
				list.add(rs.getString("tl_mthd_med"));// 9
				list.add(rs.getString("formtive_assessmt"));// 10
				list.add(rs.getString("sumtive_assessmt"));// 11
				list.add(rs.getString("integ_horivtspi"));// 12
				list.add(rs.getString("nch_term"));// 13
				alist.add(list);
				// System.err.println("lllllllllllllllllllddddddddddddd"+alist);
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
	public ArrayList<ArrayList<String>> getPop_Up_view_child_data(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select c.generic_compte,c.subject_area,c.specific_compet,c.spec_learn_object,c.integ_horivtspi,\n"
					+ "	ru.rep_und,dm.domain,gl.guilberts_level,sm.scope,tlm.method,fum.formative,tm.practical as practical,tem.term from edu_cc_tb_nch_t4_learning_object_parent p\n"
					+ "	inner join edu_cc_tb_nch_t4_learning_object_child c on c.p_id = p.id\n"
					+ "	inner join edu_cc_tb_e3_representation_understanding_mstr ru on c.millers_knows=ru.id\n"
					+ "	inner join edu_cc_tb_c3_domain_mstr dm on dm.id=c.blooms_domain\n"
					+ "	inner join edu_cc_tb_guilberts_level_mstr gl on c.guilberts_level=gl.id\n"
					+ "	inner join edu_cc_tb_d3_scope_understanding_mstr sm on c.must_know_dknow_nk=sm.id\n"
					+ "	inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on c.tl_mthd_med=tlm.id\n"
					+ "	inner join edu_cc_tb_nch_formative_and_summative_mstr fum on c.formtive_assessmt=fum.id\n"
					+ "	inner join edu_cc_tb_nch_practical_mstr tm on tm.id=p.practical_id\n"
					+ "	inner join edu_cc_tb_i3_term_mstr tem on tem.id = c.nch_term\n"
					+ "	where p.status=0 and c.p_id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29=====" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

//				list.add(rs.getString("practical"));// 0
				list.add(rs.getString("generic_compte"));// 1
				list.add(rs.getString("subject_area"));// 2
				list.add(rs.getString("rep_und"));// 3
				list.add(rs.getString("specific_compet"));// 4
				list.add(rs.getString("spec_learn_object"));// 5
				list.add(rs.getString("domain"));// 6
				list.add(rs.getString("guilberts_level"));// 7
				list.add(rs.getString("scope"));// 8
				list.add(rs.getString("method"));// 9
				list.add(rs.getString("formative"));// 10
				list.add(rs.getString("formative"));// 11
				list.add(rs.getString("integ_horivtspi"));// 12
				list.add(rs.getString("term"));// 13

				alist.add(list);
				System.err.println("listtttttttttttttttt" + list);
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

}
