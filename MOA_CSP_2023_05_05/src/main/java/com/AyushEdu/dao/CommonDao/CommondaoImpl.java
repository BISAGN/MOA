package com.AyushEdu.dao.CommonDao;

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

@Repository
public class CommondaoImpl implements Commondao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	// HET CHANGES
	@Override

	public String getTopicVideoPath(int p_id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("SELECT ref_video FROM EDU_LMS_COURSE_CONTENT_CHILD where id=:id");
			q1.setParameter("id", p_id);
			System.err.println("--------id "+p_id);
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
	
	
	public String getTopicVideoPath2(int p_id,int module) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("SELECT ref_video FROM EDU_LMS_COURSE_CONTENT_CHILD where p_id=:id and module=:module");
			q1.setParameter("id", p_id);
			q1.setParameter("module", module);
			System.err.println("--------id "+p_id);
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
	public String getTopicVideoPathp_id(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("SELECT ref_video FROM EDU_LMS_COURSE_CONTENT_CHILD where p_id=:id");
			q1.setParameter("id", id);
			System.err.println("--------id "+id);
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

	public ArrayList<ArrayList<String>> getinstIDfromuserID(int userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct institute_id from logininformation where userid=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("institute_id"));// 0

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

	public ArrayList<ArrayList<String>> getinstitute_system(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct ls.id, ls.system_name from edu_lms_institute_reg li\n"
					+ "inner join edu_lms_system_mstr ls on ls.id= li.system_id::int where li.id=? and ls.status='1'";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("system_name"));// 1

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
	public ArrayList<ArrayList<String>> getstu_DegreeList(int institute_id,String type_of_degree) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			String sv = "";
			if(type_of_degree.contains(",")) {
				sv = " (dm.type_of_degree = ? or dm.type_of_degree = ?) ";
			}else {
				sv = " dm.type_of_degree = ? ";
			}
			
			conn = dataSource.getConnection();

			q = "select distinct dm.id,dm.degree_name from edu_lms_system_degree_map_inst sdm\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id = sdm.degree_id::int and "+sv+" where sdm.institute_id::int=? and sdm.status='1'\n"
					+ "";

			PreparedStatement stmt = conn.prepareStatement(q);
			if(type_of_degree.contains(",")){
				stmt.setInt(1, Integer.parseInt(type_of_degree.split(",")[0]));
				stmt.setInt(2, Integer.parseInt(type_of_degree.split(",")[1]));
				stmt.setInt(3, institute_id);
			}else{
				stmt.setInt(1, Integer.parseInt(type_of_degree));
				stmt.setInt(2, institute_id);
			}
			
			
			
			System.err.println("\n\n---getstu_DegreeList---"+stmt+"\n\n");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
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


	// ------------------------------- Jdbc query execute for Report
	// List---------------------------
	public List<Map<String, Object>> getAllReportListJdbc(String qry, String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String Delete = "onclick=\"  if (confirm('Are you sure you want to Delete?') ){deleteData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to Update?') ){editData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String f = "";
				if (!type.isEmpty()) {
					String[] typeArr = type.split("-");
					for (int j = 0; j < typeArr.length; j++) {
						if (typeArr[j].equals("edit"))
							f += updateButton;
						else if (typeArr[j].equals("delete"))
							f += deleteButton;
					}
				} else {
					f += updateButton;
					f += deleteButton;
				}

				columns.put(metaData.getColumnLabel(1), f);
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

	public String getdemoVideoPath(int id, int set_id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"SELECT set_demo_video FROM EDU_LMS_LINK_COURSE_SET_MSTR_CHILD where p_id=:id and set_id=:set_id");
			q1.setParameter("id", id).setParameter("set_id", set_id);

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

	public ArrayList<ArrayList<String>> getteacher_list(String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = " select userid,login_name from logininformation l \n"
					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
					+ "inner join roleinformation ro on ro.role_id=ul.role_id \n"
					+ "and role='Faculty_NCISM' and institute_id=(select institute_id from logininformation where userid=?)";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			System.err.println("---getteacher_list-----------" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("userid"));// 0
				alist.add(rs.getString("login_name"));// 1
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
	
	public ArrayList<ArrayList<String>> getCourseNew() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "\n"
					+ "select distinct ecm.id,cm.course_name from edu_lms_ele_course_mstr ecm\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int where cm.status='1' order by cm.course_name";

			PreparedStatement stmt = conn.prepareStatement(q);
			//stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 0

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

	public ArrayList<ArrayList<String>> getFacultyListForNotification(int userid,String course_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select userid from (SELECT * FROM public.logininformation lo\n"
					+ "inner join userroleinformation ro on ro.user_id=lo.userid\n"
					+ "inner join roleinformation r on r.role_id =ro.role_id and role='Faculty_NCISM' \n"
					+ "and institute_id=(SELECT institute_id FROM public.logininformation where userid=?)) m\n"
					+ "inner join edu_lms_faculty_ele_course_link fl on fl.faculty_id=m.userid and fl.ele_course_id::text=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);
			stmt.setString(2, course_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("userid"));// 0
			

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
	
	public String getStaffLvlfromRoleid(String role_id) {
		Connection conn = null;
		String q = "";
		String staff_lvl = "";
		try {
			conn = dataSource.getConnection();
			q = "select staff_lvl from roleinformation where role_id=? ";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(role_id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				staff_lvl = rs.getString("staff_lvl");
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
		return staff_lvl;
	}
	
	public ArrayList<ArrayList<String>> getRolebyStaffLvl(String staff_lvl,String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			System.err.println("----role= = "+role);
			
			if(staff_lvl.equals("1") || staff_lvl.equals("ALL")) {
				q = "select role_id,role from roleinformation ";
			}if(role.toLowerCase().contains("institute")) { //04_02_2023
//				q = "select role_id,role from roleinformation where staff_lvl=? and sub_access_lvl='TPO' or sub_access_lvl='Head of Department' \n"
//						+ "or sub_access_lvl='Councillor'";
				if(staff_lvl.contains("NCH")) {
					q = "select role_id,role from roleinformation where staff_lvl='NCH' and (sub_access_lvl='TPO' or sub_access_lvl='Head of Department' \n"
							+ "or sub_access_lvl='Councillor')";
					}
					if(staff_lvl.contains("NCISM")) {
						q = "select role_id,role from roleinformation where staff_lvl='NCISM' and (sub_access_lvl='TPO' or sub_access_lvl='Head of Department' \n"
								+ "or sub_access_lvl='Councillor')";
						}
			}else if(role.equals("BISAG")) {
				q = "select role_id,role from roleinformation";
			}else {
				q = "select role_id,role from roleinformation where staff_lvl=? ";
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			ResultSet rs = null;
			if(staff_lvl.equals("1") || staff_lvl.equals("ALL")) {
				 rs = stmt.executeQuery();
			}
			if(role.toLowerCase().contains("institute")){ //04_02_2023
//				stmt.setString(1, staff_lvl);
				 rs = stmt.executeQuery();
			}else if(role.equals("BISAG")) {
				 rs = stmt.executeQuery();
			}else {
				stmt.setString(1, staff_lvl);
				 rs = stmt.executeQuery();
			}
			
			System.err.println("\n\nROLE STM-=-=-=-=-"+stmt+"\n\n");
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("role_id"));// 0
				alist.add(rs.getString("role"));// 1
			
				System.err.println("-------list"+list);
				
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
	public ArrayList<ArrayList<String>> getUniversityNchlist() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select DISTINCT l.university_id,um.university_name\n"
					+ "from logininformation l \n"
					+ "inner join edu_lms_university_mstr um on um.id =l.university_id\n"
					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
					+ "inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("university_id"));// 0
				list.add(rs.getString("university_name"));// 1
				alist.add(list);
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
	public ArrayList<ArrayList<String>> getUniversityByRoletypelist() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
//			
//			q="select DISTINCT  \n"
//					+ "um.id,um.university_name\n"
//					+ "from logininformation l\n"
//					+ "inner join edu_lms_university_mstr um on um.id =l.university_id\n"
//					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
//					+ "inner join roleinformation ro on ro.role_id=ul.role_id and ro.staff_lvl=? and ro.sub_access_lvl=?";
			
			q="select id,university_name from edu_lms_university_mstr where organization_id='1' and status='1'";

			
			stmt = conn.prepareStatement(q);
//			stmt.setString(1, staff_lvl);
//			stmt.setString(2, "University");
			
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("university_name"));// 1
				alist.add(list);
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

	public ArrayList<ArrayList<String>> getCourse_upload_Paper() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct cm.id,cm.course_name from edu_lms_system_course_duration cd \n"
					+ "inner join edu_lms_course_content cc on cc.course_name=cd.course_id\n"
					+ "inner join edu_lms_ele_course_mstr ec on ec.course_name=cd.course_id::text\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=cd.course_id order by cm.course_name";

			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt.setInt(1, institute_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				
				alist.add(rs.getString("course_name"));// 0
				alist.add(rs.getString("id"));// 1

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
	public ArrayList<ArrayList<String>> getFacultyNchSystemlist() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
//			q="select l.university_id,um.university_name\n"
//					+ "from logininformation l \n"
//					+ "inner join edu_lms_university_mstr um on um.id =l.university_id\n"
//					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
//					+ "inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";
			
			q="SELECT sm.id,sm.department from tb_nch_department_mstr sm\n"
					+ " WHERE  status = 1";
			

			stmt = conn.prepareStatement(q);
			
		

			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("department"));// 1
				alist.add(list);
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
	public ArrayList<ArrayList<String>> getUniversitybyinstitutelist(String institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select DISTINCT um.id,university_name from edu_lms_university_mstr um\n"
					+ "inner join edu_lms_institute_reg ir on ir.university_id =um.id and um.status='1'\n"
					+ "where ir.id =?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(institute_id) );
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("university_name"));// 1
				alist.add(list);
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
	
	public ArrayList<ArrayList<String>> getCourseForfaculty(int user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select distinct ecm.id,cm.course_name from edu_lms_ele_course_mstr ecm\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "inner join edu_lms_system_ele_course_link ecl on ecl.elec_course_id = ecm.id\n"
					+ "where cm.status='1' and ecl.system_id=(select distinct system_id from edu_lms_institute_reg ir\n"
					+ "inner join logininformation lo on lo.institute_id=ir.id\n"
					+ "where userid=?) order by cm.course_name";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, user_id);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 0

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
//	------------------------------------Start Curriculum------------------------------------------------

@Override
	public ArrayList<ArrayList<String>> get_Po_Datalist(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q="select concat(po.code,'-',po.program_outcome) as program_outcome "
					+ "from edu_cc_tb_program_outcome_mstr po\n"
					+ "where po.id=?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			
			System.err.println("=================="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("program_outcome"));// 0
				
				
				alist.add(list);
				System.err.println("listt"+list);
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
//	------------------------------------End Curriculum------------------------------------------------

@Override
	public ArrayList<ArrayList<String>> getUniversityNcismlist() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select DISTINCT l.university_id,um.university_name\n"
					+ "from logininformation l \n"
					+ "inner join edu_lms_university_mstr um on um.id =l.university_id\n"
					+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
					+ "inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCISM'";

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("university_id"));// 0
				list.add(rs.getString("university_name"));// 1
				alist.add(list);
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
public ArrayList<ArrayList<String>> getSubjectForpg_graduform(String system_id, String degree_id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

//		q="SELECT DISTINCT cm.id, cm.course_name FROM edu_cc_link_system_degree_professional_course lsdpc\n"
//				+ "inner join edu_lms_course_mstr cm on cm.id = lsdpc.course_id\n"
//				+ "WHERE lsdpc.system_id =? and lsdpc.degree_id=?";

		q="SELECT DISTINCT cm.id, cm.course_name FROM edu_cc_link_system_degree_professional_course lsdpc\n"
				+ "inner join edu_lms_course_mstr cm on cm.id = lsdpc.course_id\n"
				+ "WHERE lsdpc.system_id =? and lsdpc.status=6";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(system_id));
//		stmt.setInt(2, Integer.parseInt(degree_id));
		
		System.err.println("stmt------>    "+stmt);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();

			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("course_name"));// 0

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
public String getFilePath_DynemicQueryForDoc(String id, String val, String fildname) {
	
	
	System.err.println("id   "+ id + "  val "+val +" fildname "+ fildname);
	
	String whr = "";
	String q1="";
	Connection conn = null;
	String fildname1="";

	if(val.equals("1")) {
		q1="edu_ncism_reg_gradu_other_doc_upload";
	}
	else if(val.equals("2")){
		q1="edu_ncism_reg_gradu_pre_edu_dtls_tbl";
	}
	else if(val.equals("3")){
		q1="edu_reg_gradu_pre_edu_dtls_tbl";
	}
	else if(val.equals("4")){
		q1="edu_reg_gradu_other_doc_upload";
	}
	else if(val.equals("5")){
		q1="edu_reg_gradu_document_upload";
	}
	else if(val.equals("6")){
		q1="edu_ncism_reg_gradu_document_upload";
	}
	else if(val.equals("7")){
		q1="edu_lms_student_details";
	}else if(val.equals("8")){
		q1="clg_reg_dept_equipment_organon_medicine";
	}

	
	if (fildname.equals("1")) {
		fildname1 = "upload_document";
	}
	else if (fildname.equals("2")) {
		fildname1 = "doc_path";
	}
	else if (fildname.equals("3")) {
		fildname1 = "court_order";
	}
	else if (fildname.equals("4")) {
		fildname1 = "fees_receipt";
	}else if (fildname.equals("5")) {
		fildname1 = "photo_path";
	}
	
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		query = "select $fildname from "+q1+" where id=?";
		query = query.replace("$fildname", fildname1);
		
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		
		System.out.println("stmt=========>      "+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			whr = rs.getString(fildname1);
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	System.err.println("whr-------->      "+whr);
	
	return whr;
	
}

@Override
public List<Map<String, Object>> getDocumentAtchmantlistbyscreen_url(String screen_url) {
	
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {

		conn = dataSource.getConnection();
		String pageL = "";

			q = " select dam.id,dam.doc_name from edu_doc_attachments_mstr dam\n"
					+ " inner join tb_ldap_screen_master sm on sm.id = dam.screen_id where sm.screen_url=?";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		 stmt.setString(1, screen_url);
		ResultSet rs = stmt.executeQuery();
     
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
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


//////////////////////////   rajdip    change



public ArrayList<ArrayList<String>> getinstituteNchlist() {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
//		q="select DISTINCT l.university_id,um.university_name\n"
//				+ "from logininformation l \n"
//				+ "inner join edu_lms_university_mstr um on um.id =l.university_id\n"
//				+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
//				+ "inner join roleinformation ro on ro.role_id=ul.role_id and role='University_NCH'";
		
		q="select DISTINCT ir.id,ir.institute_name\n"
				+ "from logininformation l \n"
				+ "inner join edu_lms_institute_reg ir on ir.id = l.institute_id and status= '1' and app_status= '1'\n"
				+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
				+ "inner join roleinformation ro on ro.role_id=ul.role_id 	and role='Institute_NCH' ";

		stmt = conn.prepareStatement(q);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("institute_name"));// 1
			alist.add(list);
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

public ArrayList<ArrayList<String>> getinstituteNcismlist() {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		q="select DISTINCT ir.id,ir.institute_name\n"
				+ "from logininformation l \n"
				+ "inner join edu_lms_institute_reg ir on ir.id = l.institute_id and status= '1' and app_status= '1'\n"
				+ "inner join userroleinformation ul on ul.user_id=l.userid\n"
				+ "inner join roleinformation ro on ro.role_id=ul.role_id 	and (role='Institute_NCISM' or role='Principal_NCISM') ";

		stmt = conn.prepareStatement(q);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("id"));// 0
			list.add(rs.getString("institute_name"));// 1
			alist.add(list);
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

////////////////////////rajdip    change  end

///Contact Us Report shivali
@Override
public List<Map<String, Object>> Contact_UsDataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, String name, String email) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, name, email);
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

		q = " select ROW_NUMBER() OVER(order by id ASC) as ser,name,email,subject,message from edu_contact_details where id!=0 "
				+ SearchValue + " ORDER BY id "  + orderType + " limit " + pageL + " OFFSET "
				+ startPage;


		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, name, email);
System.err.println("stmt======================"+stmt);
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

//			String f = "";
//			String action = "";
//			String f1 = "";
//
//			String ul = "";
//			ul += "<ul class='buttons-group mainbtn action daobtn'>";
//
//			String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDUni' title='Edit Data'>"
//					+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='UniId" + countFunction
//					+ "' value=" + rs.getString("id") + "></i></a> </li>";
//
//			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteUni' title='Delete Data'>"
//					+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEUniId" + countFunctionDelete
//					+ "' value=" + rs.getString("id") + "></i></a> </li>";
//
//			ul += f + " " + f1;
//			ul += "</ul>";
//
//			countFunction += 1;
//			countFunctionDelete += 1;
//
//			action = ul;
//			columns.put("action", action);
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
public long DataTotalContact_UsCount(String Search, String name, String email) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, name, email);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		q = "select count(*) from (select name,email,subject,message from edu_contact_details where id!=0 "
				+ SearchValue + ")a";
		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, name, email);
		System.err.println("stmt======================"+stmt);
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
public String GenerateQueryWhereClause_SQL(String Search, String name, String email) {

	String SearchValue = "";
	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and (upper(name) like ? or upper(email) like ? or upper(subject) like ? or upper(message) like ?) ";
	}

	if (!name.trim().equals("")) {
		SearchValue += " and upper(name) like ? ";
	}
	if (!email.trim().equals("")) {
		SearchValue += " and upper(email) like ? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String name, String email) {

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

		if (!name.equals("") && name != null) {
			flag += 1;
			stmt.setString(flag, "%" + name.toUpperCase() + "%");
		}
		if (!email.equals("") && email != null) {
			flag += 1;
			stmt.setString(flag, "%" + email.toUpperCase() + "%");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
}

///Feedback Report shivali
@Override
public List<Map<String, Object>> Feedback_DataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType, String type_of_issue, String first_name,String last_name, String email) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_issue,  first_name, last_name,  email);
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

		q = " select ROW_NUMBER() OVER(order by id ASC) as ser,id,type_of_issue,first_name,last_name,email,ph_no,your_feedback from edu_tb_feedback_details where id!=0 "
				+ SearchValue + " ORDER BY id "  + orderType + " limit " + pageL + " OFFSET "
				+ startPage;


		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search,  type_of_issue,  first_name, last_name,  email);
System.err.println("stmt======================"+stmt);
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

//			String f = "";
//			String action = "";
//			String f1 = "";
//
//			String ul = "";
//			ul += "<ul class='buttons-group mainbtn action daobtn'>";
//
//			String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDUni' title='Edit Data'>"
//					+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='UniId" + countFunction
//					+ "' value=" + rs.getString("id") + "></i></a> </li>";
//
//			String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
//			f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteUni' title='Delete Data'>"
//					+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEUniId" + countFunctionDelete
//					+ "' value=" + rs.getString("id") + "></i></a> </li>";
//
//			ul += f + " " + f1;
//			ul += "</ul>";
//
//			countFunction += 1;
//			countFunctionDelete += 1;
//
//			action = ul;
//			columns.put("action", action);
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
public long DataTotalFeedbackCount(String Search, String type_of_issue, String first_name,String last_name, String email) {
	String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_issue,  first_name, last_name,  email);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();

		q = "select count(*) from (select id,type_of_issue,first_name,last_name,email,ph_no,your_feedback from edu_tb_feedback_details where id!=0 "
				+ SearchValue + ")a";
		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL(stmt, Search, type_of_issue,  first_name, last_name,  email);
		System.err.println("stmt======================"+stmt);
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
public String GenerateQueryWhereClause_SQL(String Search,String type_of_issue, String first_name,String last_name, String email) {

	String SearchValue = "";
	if (Search != null && !Search.equals("")) { // for Input Filter
		SearchValue += " and ( upper(first_name) like ? or upper(last_name) like ? or upper(email) like ? or cast(type_of_issue as text) like ? or upper(ph_no) like ? or upper(your_feedback) like ? ) ";
	}
	if (!type_of_issue.equals("0")) {
		SearchValue += " and type_of_issue = ? ";

	}
	if (!first_name.trim().equals("")) {
		SearchValue += " and upper(first_name) like ? ";
	}
	if (!last_name.trim().equals("")) {
		SearchValue += " and upper(last_name) like ? ";
	}
	if (!email.trim().equals("")) {
		SearchValue += " and upper(email) like ? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String type_of_issue, String first_name,String last_name, String email) {

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
		}
		if (!type_of_issue.equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(type_of_issue));
		}
		if (!first_name.equals("") && first_name != null) {
			flag += 1;
			stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
		}
		if (!last_name.equals("") && last_name != null) {
			flag += 1;
			stmt.setString(flag, "%" + last_name.toUpperCase() + "%");
		}
		if (!email.equals("") && email != null) {
			flag += 1;
			stmt.setString(flag, "%" + email.toUpperCase() + "%");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
}

@Override
public ArrayList<ArrayList<String>> getDegreeListPG(int institute_id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q = "select distinct dm.id,dm.degree_name from edu_lms_system_degree_map_inst sdm\n"
				+ "inner join edu_lms_degree_mstr dm on dm.id = sdm.degree_id::int where sdm.institute_id::int=? and sdm.status='1' and dm.type_of_degree=16\n";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, institute_id);
System.err.println("\n\n---getstu_DegreeListPG---"+stmt+"\n\n");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();

			alist.add(rs.getString("id"));// 0
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

@Override
public ArrayList<ArrayList<String>> getALLPGDegreeList() {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q = "select distinct dm.id,dm.degree_name from edu_lms_system_degree_map_inst sdm\n"
				+ "inner join edu_lms_degree_mstr dm on dm.id = sdm.degree_id::int where sdm.status='1' and dm.type_of_degree=16 and sdm.system_id != 45 order by dm.id \n";

		PreparedStatement stmt = conn.prepareStatement(q);
System.err.println("\n\n---getALLPGDegreeList---"+stmt+"\n\n");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();

			alist.add(rs.getString("id"));// 0
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

@Override
public ArrayList<ArrayList<String>> getALLPGSubjectbyDegree(String degree) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q = " select cm.id,cm.course_name\n"
				+ " from edu_cc_link_system_degree_professional_course lm\n"
				+ " inner join edu_lms_course_mstr cm on cm.id=lm.course_id\n"
				+ " where lm.degree_id = ?";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(degree));
System.err.println("\n\n---getALLPGDegreeList---"+stmt+"\n\n");
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


}
