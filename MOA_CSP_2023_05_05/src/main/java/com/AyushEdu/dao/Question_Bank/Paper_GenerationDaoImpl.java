package com.AyushEdu.dao.Question_Bank;
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
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class Paper_GenerationDaoImpl implements Paper_GenerationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTablePaper_GenDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,HttpSession session,  String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,  system_id, course_id,
				set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks);
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

//				q="select ROW_NUMBER() OVER(order by pg.id ASC) as ser,pg.id,s.system_name,cm2.course_name,sm.setname,m.module_name,pg.total_que,pg.hard,\n"
//						+ "pg.easy,pg.medium,pg.passing_marks,pg.total_marks,pg.exam_name,pg.system_id,pg.course_id,pg.set_id,pg.module_id\n"
//						+ "from edu_lms_paper_generation pg\n"
//						+ "inner join edu_lms_system_mstr s on s.id=pg.system_id\n"
//						+ "inner join edu_lms_ele_course_mstr e on e.id = pg.course_id\n"
//						+ "inner join edu_lms_course_mstr cm2 on cm2.id=e.course_name::int \n"
//						+ "inner join edu_lms_set_mstr sm on sm.id = pg.set_id\n"
//						+ "inner join edu_lms_module_mstr m on m.id = pg.module_id " + SearchValue + " ORDER BY pg.id "
//						+ orderType + " limit " + pageL + " OFFSET " + startPage;
				
				q="select distinct ROW_NUMBER() OVER(order by pg.id ASC) as ser,pg.id,pg.system_id,pg.course_id,pg.set_id,pg.total_que,pg.hard,pg.easy,\n"
						+ "pg.medium,pg.passing_marks,pg.total_marks,pg.exam_name,pg.module_id,sm.system_name,cm.course_name,sm2.setname,m.module_name\n"
						+ "from edu_lms_paper_generation pg \n"
						+ "inner join edu_lms_system_mstr sm on sm.id=pg.system_id\n"
						+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::int=pg.course_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
						+ "inner join edu_lms_set_mstr sm2 on sm2.id = pg.set_id\n"
						+ "inner join edu_lms_module_mstr m on m.id = pg.module_id \n"
						+ "inner join roleinformation r on r.staff_lvl=sm.created_role where pg.id!=0 and r.role=? \n"
						+ SearchValue + " group by pg.id,sm.system_name,cm.course_name,sm2.setname,m.module_name ORDER BY ser " 
						+ orderType + " limit " + pageL + " OFFSET " + startPage;


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, course_id,
					set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks);
			System.err.println("--------stmt---------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";

//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//						+ rs.getString("id") + "','" + rs.getString("module_name") + "','" + rs.getString("status")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
//
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f + " " + f1;
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
	public long DataTablePaper_GenDataTotalCount(String Search, String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, course_id,
				set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
//			q = "select count(*) \n" + " from edu_lms_module_mstr a where id!=0 " + SearchValue;
			
//			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by pg.id ASC) as ser,pg.id,pg.system_id,pg.course_id,pg.set_id,pg.total_que,pg.hard,pg.easy,\n"
//					+ "pg.medium,pg.passing_marks,pg.total_marks,pg.exam_name,pg.module_id,sm.system_name,cm.course_name,sm2.setname,m.module_name\n"
//					+ "from edu_lms_paper_generation pg \n"
//					+ "inner join edu_lms_system_mstr sm on sm.id=pg.system_id\n"
//					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::int=pg.course_id\n"
//					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
//					+ "inner join edu_lms_set_mstr sm2 on sm2.id = pg.set_id\n"
//					+ "inner join edu_lms_module_mstr m on m.id = pg.module_id\n"
//					+ "group by pg.id,sm.system_name,cm.course_name,sm2.setname,m.module_name where pg.id!=0 " +SearchValue+ ")a  ";
			
			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by pg.id ASC) as ser,pg.id,pg.system_id,pg.course_id,pg.set_id,pg.total_que,pg.hard,pg.easy,\n"
					+ "pg.medium,pg.passing_marks,pg.total_marks,pg.exam_name,pg.module_id,sm.system_name,cm.course_name,sm2.setname,m.module_name\n"
					+ "from edu_lms_paper_generation pg \n"
					+ "inner join edu_lms_system_mstr sm on sm.id=pg.system_id\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::int=pg.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "inner join edu_lms_set_mstr sm2 on sm2.id = pg.set_id\n"
					+ "inner join edu_lms_module_mstr m on m.id = pg.module_id \n"
					+ "inner join roleinformation r on r.staff_lvl=sm.created_role where pg.id!=0 and r.role= ? " +SearchValue+ "\n"
					+ " group by pg.id,sm.system_name,cm.course_name,sm2.setname,m.module_name  order by ser )a ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, course_id,
					set_id,module_id,exam_name,total_que,total_marks,easy,medium,hard,passing_marks);

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
	public String GenerateQueryWhereClause_SQL(String Search,String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks) {
		//System.err.println("policy cat --2--"+policycategory);
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(sm.system_name) like ?  or upper(cm.course_name) like ?"
					+ "or upper(sm2.setname) like ? or upper(m.module_name) like ?"
					+ "or pg.total_que::text like ? or  pg.hard::text like ?"
					+ "or pg.easy::text like ? or pg.medium::text like ?"
					+ "or pg.passing_marks::text like ? or  pg.total_marks::text like ? or upper(pg.exam_name) like ? )";
			System.err.println("globalllll search" + SearchValue);

		}
		
		

		/// advance search
//		if(SearchValue.contains("where")) {
		if (!system_id.trim().equals("0")) {
			SearchValue += " and pg.system_id = ? ";
//			System.err.println("parameter search" + SearchValue);
		}
//		}else {
//			if (!system_id.trim().equals("0")) {
//				SearchValue += " and pg.system_id = ? ";
////				System.err.println("parameter search" + SearchValue);
//			}
			
//		}
		if (!course_id.trim().equals("0")) {
			SearchValue += " and pg.course_id = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		
		if (!set_id.trim().equals("0")) {
			SearchValue += " and pg.set_id = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!module_id.trim().equals("0")) {
			SearchValue += " and pg.module_id = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!exam_name.trim().equals("")) {
			SearchValue += " and upper(pg.exam_name) like ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!total_que.trim().equals("0")) {
			SearchValue += " and pg.total_que = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!total_marks.trim().equals("")) {
			SearchValue += " and pg.total_marks = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!easy.trim().equals("")) {
			SearchValue += " and pg.easy = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!medium.trim().equals("")) {
			SearchValue += " and pg.medium = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!hard.trim().equals("")) {
			SearchValue += " and pg.hard = ? ";
//			System.err.println("parameter search" + SearchValue);

		}
		if (!passing_marks.trim().equals("")) {
			SearchValue += " and pg.passing_marks = ? ";
//			System.err.println("parameter search" + SearchValue);

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id, String course_id,String set_id,String module_id,String exam_name,String total_que,
			String total_marks,String easy,String medium,String hard,String passing_marks) {
		//System.err.println("policy cat ---3-"+policycategory);
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				

			}

			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
//				stmt.setString(flag, "%" + course_id.toUpperCase() + "%");
			}
			if (!set_id.equals("0") && set_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(set_id));
//				stmt.setString(flag, "%" + set_id.toUpperCase() + "%");
			}
			if (!module_id.equals("0") && module_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(module_id));
//				stmt.setString(flag, "%" + module_id.toUpperCase() + "%");
			}
			if (!exam_name.equals("") && exam_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + exam_name.toUpperCase() + "%");
			}
			if (!total_que.equals("0") && total_que != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(total_que));
//				stmt.setString(flag, "%" + total_que.toUpperCase() + "%");
			}
			if (!total_marks.equals("") && total_marks != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(total_marks));
//				stmt.setString(flag, "%" + total_marks.toUpperCase() + "%");
			}
			if (!easy.equals("") && easy != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(easy));
//				stmt.setString(flag, "%" + easy.toUpperCase() + "%");
			}
			if (!medium.equals("") && medium != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(medium));
//				stmt.setString(flag, "%" + medium.toUpperCase() + "%");
			}
			if (!hard.equals("") && hard != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(hard));
//				stmt.setString(flag, "%" + hard.toUpperCase() + "%");
			}
			if (!passing_marks.equals("") && passing_marks != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(passing_marks));
//				stmt.setString(flag, "%" + passing_marks.toUpperCase() + "%");
			}


			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

}
