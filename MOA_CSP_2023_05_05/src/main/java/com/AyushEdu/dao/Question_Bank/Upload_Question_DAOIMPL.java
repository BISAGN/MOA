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
public class Upload_Question_DAOIMPL implements Upload_Question_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	@Override
	public List<Map<String, Object>> DataTableQuestion_BankDataList(HttpSession sessionUserId,int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, course_id,
				set_id, module_id, exam_name, marks, time, type_id, level_id);
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


				q=" select distinct ROW_NUMBER() OVER(order by qbp.id ASC) as sr_no, qbp.id,qbp.question,qbp.marks,qbp.system_id,sm.system_name,qbp.course_id,cm2.course_name,\n"
						+ "qbp.set_id,qbp.level_id,qbp.type_id,qbp.module_id,qbp.exam_name,smb.setname,mm.module_name,qbp.type_id,qbp.level_id,qbp.time,qbc.correct_ans,string_agg(qbc.answer , ', ') as answer\n"
						+ "from edu_lms_question_band_parent qbp\n"
						+ "inner join edu_lms_question_band_child qbc on qbc.p_id=qbp.id\n"
						+ "inner join edu_lms_ele_course_mstr em on em.course_name::int=qbp.course_id\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=em.course_name::int \n"
						+ "inner join edu_lms_system_mstr sm on sm.id=qbp.system_id\n"
						+ "inner join edu_lms_set_mstr smb on smb.id=qbp.set_id\n"
						+ "inner join edu_lms_module_mstr mm on mm.id=qbp.module_id \n"
						+ "inner join roleinformation r on r.staff_lvl=sm.created_role where r.role=? " + SearchValue + "\n"
						+ "group by 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19   ORDER BY qbp.id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			System.err.println("stmt==============="+stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, course_id, set_id, module_id, exam_name, marks, time, type_id, level_id);
			
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
	public long DataTableQuestion_BankDataTotalCount(String Search, String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, course_id,
				set_id, module_id, exam_name, marks, time, type_id, level_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			
			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by qbp.id ASC) as sr_no, qbp.id,qbp.question,qbp.marks,qbp.system_id,sm.system_name,qbp.course_id,cm2.course_name,\n"
					+ "qbp.set_id,qbp.level_id,qbp.type_id,qbp.module_id,qbp.exam_name,smb.setname,mm.module_name,qbp.type_id,qbp.level_id,qbp.time,qbc.correct_ans,string_agg(qbc.answer , ', ') as answer\n"
					+ "from edu_lms_question_band_parent qbp\n"
					+ "inner join edu_lms_question_band_child qbc on qbc.p_id=qbp.id\n"
					+ "inner join edu_lms_ele_course_mstr em on em.course_name::int=qbp.course_id\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=em.course_name::int \n"
					+ "inner join edu_lms_system_mstr sm on sm.id=qbp.system_id\n"
					+ "inner join edu_lms_set_mstr smb on smb.id=qbp.set_id\n"
					+ "inner join edu_lms_module_mstr mm on mm.id=qbp.module_id \n"
					+ "inner join roleinformation r on r.staff_lvl=sm.created_role where r.role=? " +SearchValue+ "\n"
					+ "group by 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 )a " ;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, course_id, set_id, module_id, exam_name, marks, time, type_id, level_id);

			System.err.println("stmt================================"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id) {
		
		String SearchValue = "";
		
		/// advance search
		
		if (!system_id.trim().equals("0")) {
			SearchValue += " and qbp.system_id = ? ";

		}
		if (!course_id.trim().equals("0")) {
			SearchValue += " and qbp.course_id = ? ";

		}
		if (!set_id.trim().equals("0")) {
			SearchValue += " and qbp.set_id = ? ";
		}
		if (!module_id.trim().equals("0")) {
			SearchValue += " and qbp.module_id = ? ";

		}
		
		if (!exam_name.trim().equals("")) {
			SearchValue += " and upper(qbp.exam_name) like ? ";

		}
		
		if (!marks.trim().equals("")) {
			SearchValue += " and upper(qbp.marks) like ? ";

		}
		if (!time.trim().equals("")) {
			SearchValue += " and upper(qbp.time) like ? ";

		}
		
		if (!type_id.trim().equals("0")) {
			SearchValue += " and qbp.type_id = ? ";

		}
		if (!level_id.trim().equals("0")) {
			SearchValue += " and qbp.level_id = ? ";

		}
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(sm.system_name) like ? or upper(cm2.course_name) like ?  or upper(smb.setname) like ?  "
					+ "             or upper(mm.module_name) like ?  or upper(qbp.exam_name) like ?  "
					+ "             or upper(qbp.marks) like ?  or upper(qbp.time) like ? )";

		}


		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,String course_id, String set_id, String module_id,
			String exam_name, String marks, String time, String type_id, String level_id) {
		//System.err.println("policy cat ---3-"+policycategory);
		int flag =1;
		try {
			
			if (!system_id.equals("0") && system_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (!set_id.equals("0") && set_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(set_id));
			}
			if (!module_id.equals("0") && module_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(module_id));
			}
			
			if (!exam_name.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + exam_name.toUpperCase() + "%");
			}
			if (!marks.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + marks.toUpperCase() + "%");
			}
//			if (!time.equals("0") && time != null) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(time));
//			}
			if (!time.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + time.toUpperCase() + "%");
			}
			if (!type_id.equals("0") && type_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(type_id));
			}
			
			if (!level_id.equals("0") && level_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(level_id));
			}
			
			
			
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
