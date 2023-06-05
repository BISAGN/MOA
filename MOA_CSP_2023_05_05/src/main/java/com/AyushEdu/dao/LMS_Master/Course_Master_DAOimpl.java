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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;

@Repository
public class Course_Master_DAOimpl implements Course_Master_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTable_Course_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String type_of_content_id, String course_name, String status, String course_code) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,type_of_content_id, course_name, status, course_code);
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
			
			if(Search.equals("") && course_name.equals("") &&  course_name.equals("") && status=="1") {
				q = "select ROW_NUMBER() OVER(order by course_name ASC) as ser,cm.id,toc.type_of_content,cm.course_name,cm.course_code,cm.status\n"
						+ "	from edu_lms_course_mstr cm\n"
						+ "	inner join edu_lms_type_of_content_mstr toc on toc.id= cm.type_of_content_id where cm.status='1'"  + SearchValue
						+ " ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select ROW_NUMBER() OVER(order by course_name ASC) as ser,cm.id,toc.type_of_content,cm.course_name,cm.course_code,cm.status\n"
					+ "	from edu_lms_course_mstr cm\n"
					+ "	inner join edu_lms_type_of_content_mstr toc on toc.id= cm.type_of_content_id \n"
					+ "where cm.status='"+ status +"'"  + SearchValue +" ORDER BY "+orderColunm+" " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}


			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt-----hhhhh-----" + stmt);
			stmt = setQueryWhereClause_SQL(stmt, Search,type_of_content_id, course_name, status, course_code);
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

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','"+ rs.getString("type_of_content") + "','" + rs.getString("course_name") + "','" + rs.getString("course_code")
						+ "','" + rs.getString("status") + "') }else{ return false;}\"";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDcourse' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='CourseId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='contentId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='CourseName" + countFunction
						+ "' value=" + rs.getString("course_name") + ">" + "<input type='hidden' id='CourseCode"
						+ countFunction + "' value=" + rs.getString("course_code") + ">"
						+ "<input type='hidden' id='CourseStatus" + countFunction + "' value=" + rs.getString("status")
						+ "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteCourse' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='deleteID" + countFunctionDelete
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
	public long DataTable_Course_masterDataTotalCount(String Search,String type_of_content_id, String course_name, String status,
			String course_code) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,type_of_content_id, course_name, status, course_code);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n"
					+ " from (select ROW_NUMBER() OVER(order by course_name ASC) as ser,cm.id,toc.type_of_content,cm.course_name,cm.course_code,cm.status\n"
					+ "from edu_lms_course_mstr cm\n"
					+ "inner join edu_lms_type_of_content_mstr toc on toc.id= cm.type_of_content_id \n"
					+ "where cm.status='1' "
					+ SearchValue + ") a where id!=0 ";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,type_of_content_id, course_name, status, course_code);
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
	public String GenerateQueryWhereClause_SQL(String Search,String type_of_content_id, String course_name, String status, String course_code) {
		String SearchValue = "";
		if (Search != null) { // for Input Filter
			
			if (!Search.equals("")) {
				SearchValue += " and (  upper(cm.course_name) like ? or upper(cm.course_code) like ? or upper(toc.type_of_content) like ? )";
				System.err.println("globalllll search" + SearchValue);
			}
		}

		if (!type_of_content_id.equals("0")) {
			SearchValue += " and type_of_content_id = ? ";

		}

		if (!SearchValue.contains("and") && !course_name.equals("")) {
			SearchValue += " and upper(cm.course_name) like ? ";
			System.err.println("parameter search" + SearchValue);

		}

		if (!course_code.trim().equals("")) {
			SearchValue += " and upper(cm.course_code) like ? ";
			System.err.println("parameter search" + SearchValue);
		}

		if (!SearchValue.contains("and") && !course_code.equals("")) {
			SearchValue += " and upper(course_code) like ? ";
			System.err.println("parameter search" + SearchValue);

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String type_of_content_id, String course_name,
			String status, String course_code) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			if (!type_of_content_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(type_of_content_id));
			}

			if (!course_name.equals("") && course_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + course_name.toUpperCase() + "%");
			}

			if (!course_code.equals("") && course_code != null) {
				flag += 1;
				stmt.setString(flag, "%" + course_code.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	public EDU_LMS_COURSE_MASTER get_CourseByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_COURSE_MASTER updateid = (EDU_LMS_COURSE_MASTER) session.get(EDU_LMS_COURSE_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
