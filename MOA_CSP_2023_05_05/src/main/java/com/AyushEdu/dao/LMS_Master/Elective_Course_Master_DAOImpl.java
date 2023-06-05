package com.AyushEdu.dao.LMS_Master;

import java.awt.Image;
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
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;

@Repository
public class Elective_Course_Master_DAOImpl implements Elective_Course_Master_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableEle_Course_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course_name, String upload_img, String status,
			String degree_id, String semester_id, String demo_video) {
		Image image = new ImageIcon(this.getClass().getResource(upload_img)).getImage();
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name, upload_img, status, degree_id,
				semester_id, demo_video);
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

			q = " select ROW_NUMBER() OVER(order by ecm.id ASC) as ser,ecm.id,cm.course_name,ecm.upload_img,ecm.status,dm.degree_name,ecm.demo_video,\n"
					+ "ecm.degree_id, tm.prof_name as profession  \n"
					+ "from edu_lms_ele_course_mstr ecm\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=ecm.degree_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
					+ "inner join edu_lms_term_mstr tm on tm.id=ecm.semester_id\n"
					+ "where ecm.status='1' "
					+ SearchValue + " order by ecm.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, course_name, upload_img, status, degree_id, semester_id,
					demo_video);
			ResultSet rs = stmt.executeQuery();
			System.err.println("-----stmt 12 "+stmt);
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

				columns.put("img",
						"<img class='d-block img5050 imageZomm' alt='No Image' id='myImg" + rs.getString("id")
								+ "' src='MedicalImagePath?i_id=" + rs.getString("id") + "' onclick='imageView("
								+ rs.getString("id") + ");' />");

				String f = "";
				String action = "";
				String f1 = "";
				String dv = "";
				if (rs.getString("demo_video") != null) {
					dv = "<ul class='buttons-group'><li><a class='main-btn active-btn btn-hover btn-sm' onclick='return demovideo("
							+ rs.getString("id") + ");'><i class='bi bi-play-circle'></i></a></li></ul>";
				}

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("course_name") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDElect' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EleId" + countFunction + "' value="
						+ rs.getString("id") + ">" + "<input type='hidden' id='EleName" + countFunction + "' value="
						+ rs.getString("course_name") + ">" + "<input type='hidden' id='EleStatus" + countFunction
						+ "' value=" + rs.getString("status") + "></i></a> </li> ";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteEleOnclick' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'\n"
						"	<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEleId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;

				action = ul;
				columns.put("action", action);
				columns.put("dv", dv);

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
	public long DataTableEle_Course_masterDataTotalCount(String Search, String course_name, String upload_img,
			String status, String degree_id, String semester_id, String demo_video) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course_name, upload_img, status, degree_id,
				semester_id, demo_video);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n"
					+ " from (select ecm.id,cm.course_name,ecm.upload_img,ecm.status,dm.degree_name,ecm.demo_video,\n"
					+ "ecm.degree_id, concat('SEMESTER ', ecm.semester_id) as semester \n"
					+ "from edu_lms_ele_course_mstr ecm\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=ecm.degree_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n" + "where ecm.status='1' "
					+ SearchValue + ") a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, course_name, upload_img, status, degree_id, semester_id,
					demo_video);
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
	public String GenerateQueryWhereClause_SQL(String Search, String course_name, String upload_img, String status,
			String degree_id, String semester_id, String demo_video) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) {
			SearchValue += " and (  upper(cm.course_name) like ? or upper(dm.degree_name) like ? or upper(concat('SEMESTER ', ecm.semester_id)) like ?)";

		}

		if (!course_name.trim().equals("0")) {
			SearchValue += " and ecm.course_name like ? ";

		}

		if (!degree_id.equals("0")) {
			SearchValue += " and ecm.degree_id = ? ";

		}

		if (!semester_id.equals("0")) {
			SearchValue += " and ecm.semester_id = ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course_name,
			String upload_img, String status, String degree_id, String semester_id, String demo_video) {
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

			if (!course_name.equals("0") && course_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + course_name + "%");
			}

			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!semester_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(semester_id));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	public EDU_LMS_ELECTIVE_COURSE_MASTER getEle_CourseByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_ELECTIVE_COURSE_MASTER updateid = (EDU_LMS_ELECTIVE_COURSE_MASTER) session
				.get(EDU_LMS_ELECTIVE_COURSE_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	@Override
	public String getImagePath(String id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select upload_img from edu_lms_ele_course_mstr where id=? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("upload_img");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public String getdemoVideoPath(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("select demo_video from EDU_LMS_ELECTIVE_COURSE_MASTER where id=:id");
			q1.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			String path = list.get(0);
			tx.commit();
			return path;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

}
