package com.AyushEdu.dao.LMS_NCISM;

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

import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;

@Repository
public class CourseContentDetailDAOImpl implements CourseContentDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public long DataTableCourseContentDetailDataTotalCount(String Search, String system_name, String degree,
			String course_name, String setname, String module_name, String start_date, String end_date,
			String type_of_content,String role) {
		System.err.println("role=====d==" +role);
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_name, degree, course_name, setname,
				module_name, start_date, end_date, type_of_content);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (SELECT distinct sp.id,sm.system_name,dm.degree_name, cm.course_name,lsm.setname,c.Module_name,scc.type_of_content,ccc.module,lsm.id,\n"
					+ "lm1.level_name as level_of_module,TO_CHAR(scd.start_date,'DD-MON-YYYY') as start_date,TO_CHAR(scd.end_date,'DD-MON-YYYY')as end_date,scd.course_fee,scd.course_switch_duration,(TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as duration \n"
					+ "FROM public.edu_lms_course_content sp\n"
					+ "inner join edu_lms_course_content_child ccc on ccc.p_id=sp.id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sp.degree_name \n"
					+ "inner join edu_lms_ele_course_mstr p on p.course_name=sp.course_name::text \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=p.course_name::int \n"
					+ "inner join edu_lms_module_mstr c on c.id=ccc.module \n"
					+ "inner join edu_lms_system_course_duration scd on scd.course_id=sp.course_name::int\n"
					+ "inner join edu_lms_type_of_content_mstr scc on scc.id=sp.type_of_content ::int \n"
					+ "inner join edu_lms_level_mstr lm1 on lm1.id=ccc.level_of_module\n"
					+ "inner join edu_lms_link_course_set_mstr csm on csm.system_id=sp.system_name\n"
					+ "inner join (select *,elmt.course_name as course_new_id from edu_lms_link_course_set_mstr_child cch\n"
					+ "inner join edu_lms_ele_course_mstr elmt on cch.course_id=elmt.id)  cch on cch.course_new_id=sp.course_name::text\n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=cch.set_id\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=scd.system_id::int\n" + "where sp.id != 0 and sm.created_role=? \n"
					+ SearchValue
					+ "  group by cm.course_name, sp.id,sm.system_name,dm.degree_name, p.course_name,ccc.module,lsm.setname,c.Module_name,lsm.id,scc.type_of_content,\n"
					+ "ccc.level_of_module,lm1.level_name ,scd.start_date,scd.end_date,scd.course_fee,scd.course_switch_duration,ccc.video_duration,lsm.setname) ab ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_name, degree, course_name, setname, module_name,
					start_date, end_date, type_of_content);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_name, String degree, String course_name,
			String setname, String module_name, String start_date, String end_date, String type_of_content) {

		String SearchValue = "";

		if (!system_name.trim().equals("0")) {
			SearchValue += " and scd.system_id::int = ? ";
		}
		if (!degree.trim().equals("0")) {
			SearchValue += " and sp.degree_name::int = ? ";
		}
		if (!course_name.trim().equals("0")) {
			SearchValue += " and c.course_id::int  = ? ";
		}
		if (!setname.trim().equals("0")) {
			SearchValue += " and lsm.id = ? ";
		}
		if (!module_name.trim().equals("0")) {
			SearchValue += " and ccc.module::int  = ? ";
		}

		if (!start_date.equals("") && start_date != "" && !start_date.equals("DD/MM/YYYY")) {
			SearchValue += " and TO_CHAR(scd.start_date,'DD/MM/YYYY') = ?";
		}
		if (!end_date.equals("") && end_date != "" && !end_date.equals("DD/MM/YYYY")) {
			SearchValue += " and TO_CHAR(scd.end_date,'DD/MM/YYYY')= ?";
		}
		if (!type_of_content.trim().equals("0")) {
			SearchValue += " and sp.type_of_content  = ? ";
		}

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(sm.system_name) like ? or upper(dm.degree_name) like ? or upper(cm.course_name) like ? or upper(lsm.setname) like ? or upper(c.module_name) like ? \n"
					+ "or upper(scc.type_of_content) like ? or  upper(TO_CHAR(scd.start_date,'DD-MON-YYYY'))  like ? or upper(TO_CHAR(scd.end_date,'DD-MON-YYYY')) like ? \n"
					+ "or upper((TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks') like ? or upper(scd.course_switch_duration) like ? or upper(scd.course_fee) like ? \n "
					+ "or upper(lm1.level_name) like ?)";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_name,
			String degree, String course_name, String setname, String module_name, String start_date, String end_date,
			String type_of_content) {

		int flag = 1;
		try {

			if (!system_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_name));
			}
			if (!degree.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree));
			}

			if (!course_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_name));

			}
			if (!setname.equals("0")) {
				flag += 1;

				stmt.setInt(flag, Integer.parseInt(setname));

			}
			if (!module_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(module_name));

			}
			if (!start_date.equals("") && start_date != "" && !start_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, start_date);

			}
			if (!end_date.equals("") && end_date != "" && !end_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, end_date);

			}
			if (!type_of_content.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(type_of_content));
			}

			if (Search != null && !Search.equals("")) { // for Input Filter

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

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableCourseContentDetailDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_name, String degree, String course_name, String setname,
			String module_name, String start_date, String end_date, String type_of_content,String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_name, degree, course_name, setname,
				module_name, start_date, end_date, type_of_content);
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

			q = "SELECT distinct ROW_NUMBER() OVER(order by sp.id) as sr_no, sp.id,sm.system_name,dm.degree_name, cm.course_name,lsm.setname,c.Module_name,scc.type_of_content,ccc.module,lsm.id,\n"
					+ "lm1.level_name as level_of_module,TO_CHAR(scd.start_date,'DD-MON-YYYY') as start_date,TO_CHAR(scd.end_date,'DD-MON-YYYY')as end_date,scd.course_fee,scd.course_switch_duration,(TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as duration \n"
					+ "FROM public.edu_lms_course_content sp\n"
					+ "inner join edu_lms_course_content_child ccc on ccc.p_id=sp.id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sp.degree_name \n"
					+ "inner join edu_lms_ele_course_mstr p on p.course_name::int=sp.course_name\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=p.course_name::int \n"
					+ "inner join edu_lms_module_mstr c on c.id=ccc.module \n"
					+ "inner join edu_lms_system_course_duration scd on scd.course_id=sp.course_name::int\n"
					+ "inner join edu_lms_type_of_content_mstr scc on scc.id=sp.type_of_content ::int \n"
					+ "inner join edu_lms_level_mstr lm1 on lm1.id=ccc.level_of_module\n"
					+ "inner join edu_lms_link_course_set_mstr csm on csm.system_id=sp.system_name\n"
					+ "inner join  (select *,elmt.course_name as course_new_id from edu_lms_link_course_set_mstr_child cch\n"
					+ "inner join edu_lms_ele_course_mstr elmt on cch.course_id=elmt.id)  cch on cch.course_new_id=sp.course_name::text \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=cch.set_id\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=scd.system_id::int\n" + "where sp.id !=0 and sm.created_role=? "
					+ SearchValue
					+ " group by  sp.id,sm.system_name,cm.course_name,dm.degree_name, p.course_name,ccc.module,lsm.setname,c.Module_name,lsm.id,scc.type_of_content,\n"
					+ "ccc.level_of_module,lm1.level_name ,scd.start_date,scd.end_date,scd.course_fee,scd.course_switch_duration,ccc.video_duration,lsm.setname order by sr_no \n"
					+ orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_name, degree, course_name, setname, module_name,
					start_date, end_date, type_of_content);
			ResultSet rs = stmt.executeQuery();
			System.err.println("----stlt" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

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

	public TB_COURSE_CONTENT_MSTR getCourseContentDetailByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_COURSE_CONTENT_MSTR updateid = (TB_COURSE_CONTENT_MSTR) session.get(TB_COURSE_CONTENT_MSTR.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	public ArrayList<ArrayList<String>> getsetlistByCourse(String id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct csm.set_id,sm.setname from edu_lms_link_course_set_mstr csm\n"
					+ "inner join edu_lms_set_mstr sm on sm.id=csm.set_id\n" + "where course_id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("set_id"));// 0
				alist.add(rs.getString("setname"));// 1

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

	public ArrayList<ArrayList<String>> getModuleListBySet(String id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct lcs.module_id,lm.module_name from edu_lms_link_course_set_mstr lcs\n"
					+ "inner join edu_lms_module_mstr lm on lm.id::text=lcs.module_id::text\n" + "where set_id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("module_id"));// 0
				alist.add(rs.getString("module_name"));// 1

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

	public ArrayList<ArrayList<String>> getModulelistFromset(String id, String id1) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct csm.module_id,c.module_name from edu_lms_link_course_set_mstr csm\n"
					+ "inner join edu_lms_module_mstr c on c.id::text=csm.module_id where csm.course_id=? and csm.set_id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(id1));
			stmt.setInt(2, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("module_id"));// 0
				alist.add(rs.getString("module_name"));// 1

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

	public ArrayList<ArrayList<String>> getDegreelistFromsystem(String id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct csm.degree_id,c.degree_name from edu_lms_system_ele_course_link csm\n"
					+ "inner join edu_lms_degree_mstr c on c.id::int =csm.degree_id::int where csm.system_id::int = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("degree_id"));// 0
				alist.add(rs.getString("degree_name"));// 1

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

	public ArrayList<ArrayList<String>> getSetlistFromDegree(String id, String id1) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct csmc.set_id,c.setname from edu_lms_link_course_set_mstr_child csmc\n"
					+ "inner join edu_lms_link_course_set_mstr csm on csm.id=csmc.p_id \n"
					+ "inner join edu_lms_set_mstr c on c.id::int =csmc.set_id::int where csm.system_id::int = ? and csm.degree_id::int = ? order by c.setname";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1, Integer.parseInt(id));
			stmt.setObject(2, Integer.parseInt(id1));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("set_id"));// 0
				alist.add(rs.getString("setname"));// 1

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

	public ArrayList<ArrayList<String>> getCourselistFromSet(String id, String id1, String id2) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct cm.id,cm.course_name from edu_lms_link_course_set_mstr_child sec\n"
					+ "inner join edu_lms_system_ele_course_link csmc on csmc.elec_course_id=sec.course_id\n"
					+ "inner join edu_lms_ele_course_mstr c on c.id::int =sec.course_id::int \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=c.course_name::int \n"
					+ " where csmc.system_id::int = ? and csmc.degree_id::int = ? and sec.set_id::int=? order by cm.course_name";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1, Integer.parseInt(id));
			stmt.setObject(2, Integer.parseInt(id1));
			stmt.setObject(3, Integer.parseInt(id2));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 1

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

	public ArrayList<ArrayList<String>> getCourselistFromSet(String id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct m.id, m.module_name\n" + "from edu_lms_module_mstr m \n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id = m.course_id \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "inner join edu_lms_course_content cc on cc.course_name=cm.id \n" + "where cm.id=?";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setObject(1, Integer.parseInt(id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("module_name"));// 1

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
