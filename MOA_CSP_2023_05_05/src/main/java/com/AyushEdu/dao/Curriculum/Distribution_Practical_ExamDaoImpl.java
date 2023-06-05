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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROGRAM_OUTCOME_MSTR;

@Repository
public class Distribution_Practical_ExamDaoImpl implements Distribution_Practical_ExamDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTableDistribution_practical_examDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status, String role) {
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and dpe.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and dpe.system_id = 45 ";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				head, mark, status);
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
			if (Search.equals("") && system_id.equals("0") && head.equals("0")  && status == "1") {
			q = " select distinct ROW_NUMBER() OVER(order by sm.system_name ) as ser, dpe.id,dpe.system_id,sm.system_name,dpe.degree_id,dm.degree_name,dpe.professional_id,"
					+ "pm.professional,dpe.course_id,cm.course_name,dpe.head,dpe.mark,dpe.status \n"
					+ "from edu_cc_tb_6h_distrubution_practical_exam dpe\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=dpe.system_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=dpe.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id=dpe.professional_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=dpe.course_id where dpe.status=1" + sd + SearchValue
					+ "ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}
			else {	q = " select distinct ROW_NUMBER() OVER(order by sm.system_name ) as ser, dpe.id,dpe.system_id,sm.system_name,dpe.degree_id,dm.degree_name,dpe.professional_id,"
					+ "pm.professional,dpe.course_id,cm.course_name,dpe.head,dpe.mark,dpe.status \n"
					+ "from edu_cc_tb_6h_distrubution_practical_exam dpe\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=dpe.system_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=dpe.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id=dpe.professional_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=dpe.course_id where dpe.status='" + status + "'" + sd + SearchValue
					+ "ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET " + startPage;}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, head, mark,
					status);
			System.err.println("stmt----Topic---------------------------->  " + stmt);
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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm editOnclick' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value='" + rs.getString("id") + "'>" + "<input type='hidden' id='sysID" + countFunction
						+ "' value='" + rs.getString("system_id") + "'>" + "<input type='hidden' id='degID"
						+ countFunction + "' value='" + rs.getString("degree_id") + "'>"
						+ "<input type='hidden' id='proID" + countFunction + "' value='"
						+ rs.getString("professional_id") + "'>" + "<input type='hidden' id='couID" + countFunction
						+ "' value='" + rs.getString("course_id") + "'>" + "<input type='hidden' id='headID"
						+ countFunction + "' value='" + rs.getString("head") + "'>" + "<input type='hidden' id='markID"
						+ countFunction + "' value='" + rs.getString("mark") + "'>"
						+ "<input type='hidden' id='apstatusAGE" + countFunction + "' value='" + rs.getString("status")
						+ "'></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
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
	public long DataTableDistribution_practical_examDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status, String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				head, mark, status);
		
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and dpe.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and dpe.system_id = 45 ";
		}
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if (Search.equals("") && system_id.equals("0") && head.equals("0")  && status == "1") {
			q = "select count(*) from (select dpe.id,dpe.system_id,sm.system_name,dpe.degree_id,dm.degree_name,dpe.professional_id,"
					+ "pm.professional,dpe.course_id,cm.course_name,dpe.head,dpe.mark,dpe.status \n"
					+ "from edu_cc_tb_6h_distrubution_practical_exam dpe\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=dpe.system_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=dpe.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id=dpe.professional_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=dpe.course_id where dpe.status=1" + sd + SearchValue
					+ ")a where id!=0 ";
			}else {
				q = "select count(*) from (select dpe.id,dpe.system_id,sm.system_name,dpe.degree_id,dm.degree_name,dpe.professional_id,"
						+ "pm.professional,dpe.course_id,cm.course_name,dpe.head,dpe.mark,dpe.status \n"
						+ "from edu_cc_tb_6h_distrubution_practical_exam dpe\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=dpe.system_id\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=dpe.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id=dpe.professional_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=dpe.course_id where dpe.status='" + status + "'" + sd + SearchValue
						+ ")a where id!=0 ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, head, mark,
					status);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String head, String mark, String status) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(sm.system_name) like ?or upper(dm.degree_name) like ? "
					+ "or upper(pm.professional) like ?  or upper(cm.course_name) like ?"
					+ " or upper(head) like ? or cast(mark as character varying) like ?)";
		}
//		/// advance search
		if (!system_id.equals("0")) {
			SearchValue += " and dpe.system_id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and dpe.degree_id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and dpe.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and dpe.course_id =? ";
		}
		if (head != null && !head.equals("0")) {
			SearchValue += " and head like ? ";
		}
		if (!mark.equals("")) {
			SearchValue += " and mark =? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String head, String mark, String status) {
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
				stmt.setString(flag, Search + "%");
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
			if (head != null && !head.equals("0")) {
				flag += 1;
				stmt.setString(flag, "%" + head + "%");
			}
			if (!mark.equals("") && mark != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(mark));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	public ArrayList<ArrayList<String>> getdegreelistbysystem(String system_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select p.id,p.system_id,p.degree_id,p.professional_id,p.course_id from edu_cc_tb_6h_distrubution_practical_exam p\n"
					+ "inner join edu_cc_tb_add_non_lecture_activities_child c on c.p_id=p.id\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_ele_course_mstr ec on ec.id = p.course_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = ec.course_name::int\n" + " where p.id = ? ";

//			q="select id,screen_name from tb_ldap_screen_master where screen_module_id=? \n";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("screen_name"));// 0

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

	@Override
	public String updateDistribution_practical_exam(EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EDU_CC_TB_6H_DISTRIBUTION_PRACTICAL_EXAM set system_id=:system_id,degree_id=:degree_id,"
					+ "professional_id=:professional_id,course_id=:course_id,head=:head,"
					+ "mark=:mark,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					.setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", (obj.getProfessional_id()))
					.setParameter("course_id", (obj.getCourse_id())).setParameter("head", (obj.getHead()))
					.setParameter("mark", (obj.getMark())).setParameter("status", (obj.getStatus()))
					.setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_date", obj.getModified_date()).setParameter("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		} finally {
			session1.close();
		}
		return msg;
	}
}