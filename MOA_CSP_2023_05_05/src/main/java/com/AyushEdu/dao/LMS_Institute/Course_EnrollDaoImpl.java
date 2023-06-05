package com.AyushEdu.dao.LMS_Institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Course_EnrollDaoImpl implements Course_EnrollDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> GetCourse_Set(String system_id, String term_id, String degree_id,
			String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select * from (select s.id,s.setname,s.prof_name,c.set_demo_video,STRING_AGG(DISTINCT ec.id||'_'||cm.course_name::text,',')as courses  \n"
					+ "from edu_lms_link_course_set_mstr p \n"
					+ "inner join  edu_lms_link_course_set_mstr_child c on c.p_id=p.id\n"
					+ "inner join edu_lms_ele_course_mstr ec on ec.id=c.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id = ec.course_name::int	\n"
					+ "inner join edu_lms_set_mstr s on s.id = c.set_id \n"
					+ "inner join edu_lms_system_course_duration sc on sc.course_id = cm.id\n"
					+ "inner join edu_lms_course_content cc on cc.course_name = sc.course_id\n"
					+ "where  p.system_id=? and  s.term_id=? and p.degree_id=?\n"
					+ "and ec.id not in (select course_id from edu_lms_system_ele_course_sets_link_child c \n"
					+ "inner join  edu_lms_system_ele_course_sets_link_parent p on p.id=c.p_id\n"
					+ "where p.user_id=? and c.status ='0' and p.payment_status=1)\n"
					+ "group by s.id, s.setname,c.set_demo_video,s.id order by s.id)a";


//			q="select * from (select s.id,s.setname,s.prof_name,c.set_demo_video,STRING_AGG(DISTINCT ec.id||'_'||cm.course_name::text,',')as courses  \n"
//					+ "from edu_lms_link_course_set_mstr p \n"
//					+ "inner join  edu_lms_link_course_set_mstr_child c on c.p_id=p.id\n"
//					+ "inner join edu_lms_ele_course_mstr ec on ec.id=c.course_id\n"
//					+ "inner join edu_lms_course_mstr cm on cm.id = ec.course_name::int	\n"
//					+ "inner join edu_lms_set_mstr s on s.id = c.set_id \n"
//					+ "inner join edu_lms_system_course_duration sc on sc.course_id = cm.id\n"
//					+ "inner join edu_lms_course_content cc on cc.course_name = sc.course_id\n"
//					+ "where  p.system_id=44 and  s.term_id=4 and p.degree_id=59\n"
//					+ "and ec.id not in (select course_id from edu_lms_system_ele_course_sets_link_child c \n"
//					+ "inner join  edu_lms_system_ele_course_sets_link_parent p on p.id=c.p_id\n"
//					+ "where p.user_id=738 and c.status ='0' and p.payment_status=1)\n"
//					+ "group by s.id, s.setname,c.set_demo_video,s.id order by s.id)a";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));
			stmt.setInt(2, Integer.parseInt(term_id));
			stmt.setInt(3, Integer.parseInt(degree_id));
			stmt.setInt(4, Integer.parseInt(userid));
		
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt====course====="+stmt);
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("setname")); // 0
				alist.add(rs.getString("set_demo_video")); // 1
				alist.add(rs.getString("courses")); // 2
				alist.add(rs.getString("prof_name")); // 3
				alist.add(rs.getString("id")); // 4
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

	public ArrayList<ArrayList<String>> GetModule_fetch(String course_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select m.id, m.module_name, cm.course_name,\n"
					+ "(select count(*) from edu_lms_system_ele_course_sets_link_child c\n"
					+ "inner join edu_lms_system_ele_course_sets_link_parent p on p.id=c.p_id\n"
					+ "where c.status='0' and c.course_id=? and p.payment_status=1 ) as count\n"
					+ "from edu_lms_module_mstr m\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id = m.course_id \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "inner join edu_lms_course_content cc on cc.course_name=cm.id\n"
					+ "left join edu_lms_system_ele_course_sets_link_child p on p.course_id= m.course_id \n"
					+ "where m.course_id=? group by  m.id, m.module_name, cm.course_name";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			stmt.setInt(2, Integer.parseInt(course_id));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("module_name")); // 1
				alist.add(rs.getString("course_name")); // 2
				alist.add(rs.getString("count")); // 3

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

	public ArrayList<ArrayList<String>> GetSummary(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select distinct tc.id,tc.type_of_content,TO_CHAR(cd.start_date,'DD-MON-YYYY') as start_date,\n"
					+ "TO_CHAR(cd.end_date,'DD-MON-YYYY') as end_date,\n"
					+ "(TO_DATE(cd.end_date::text, 'YYYY/MM/DD')-TO_DATE(cd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks,cd.course_fee,cm.course_name \n"
					+ "from edu_lms_course_content cc\n"
					+ "inner join edu_lms_type_of_content_mstr tc on tc.id = cc.type_of_content::int\n"
					+ "inner join edu_lms_system_course_duration cd on cd.course_id = cc.course_name\n"
					+ "inner join edu_lms_ele_course_mstr e on e.course_name::text = cd.course_id::text\n"
					+ "inner join edu_lms_course_mstr cm on cm.id::text=e.course_name::text\n" + "where e.id=?\n"
					+ "GROUP BY tc.id,cc.course_name,cc.type_of_content,tc.type_of_content,cd.start_date,cd.end_date,cd.course_fee,cm.course_name";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("type_of_content")); // 1
				alist.add(rs.getString("start_date")); // 2
				alist.add(rs.getString("end_date")); // 3
				alist.add(rs.getString("weeks")); // 4
				alist.add(rs.getString("course_fee")); // 5
				alist.add(rs.getString("course_name")); // 6
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

	public ArrayList<ArrayList<String>> GetCourse_Description(String userid,String system_id,String degree_id,String p_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select id, description from edu_lms_link_course_set_mstr  \n"
					+ "  where system_id = ? and degree_id = ? and id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));
			stmt.setInt(2, Integer.parseInt(degree_id));
			stmt.setInt(3, Integer.parseInt(p_id));

			System.out.println("stmt abh  "+stmt );
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("description")); // 1
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

	public ArrayList<ArrayList<String>> GetLearn_Count(String system_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select distinct count(user_id) from edu_lms_system_ele_course_sets_link_parent where payment_status= 2 and system_id =?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(system_id));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("count")); // 0
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

////Video///////////////	
	public String getTopicVideoPath(String id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			Query q1 = sessionHQL
					.createQuery("select  set_demo_video from EDU_LMS_LINK_COURSE_SET_MSTR_CHILD  where set_id=:id ");

			q1.setParameter("id", Integer.parseInt(id));

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

	public ArrayList<ArrayList<String>> GetCourse_Exit(String system_id, String degree_id, String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select cd.course_switch_duration, DATE_PART('day',age(c.created_date,current_date)) as diff\n"
					+ "from edu_lms_system_course_duration cd\n"
					+ "inner join edu_lms_system_ele_course_sets_link_parent c on c.ele_course_id=cd.course_id\n"
					+ "where  c.user_id = ? and  c.system_id =? and c.degree_id =? \n" + "";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, system_id);
			stmt.setString(2, degree_id);
			stmt.setString(3, userid);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_switch_duration")); // 0
				alist.add(rs.getString("diff")); // 1
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

	public ArrayList<ArrayList<String>> GetlevelofCoursese(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select h.course_name, h.id,ch.level_of_module,ch.module as module_id \n"
					+ "from edu_lms_course_content h\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::int=h.course_name\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
					+ "inner join edu_lms_course_content_child ch on ch.p_id=h.id \n" + "where cm.id=?\n"
					+ "group by h.id,h.course_name,ch.level_of_module,module_id \n" + "order by ch.level_of_module";

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("STMT_SEQUENCE---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_name")); // 0
				alist.add(rs.getString("level_of_module")); // 1
				alist.add(rs.getString("module_id")); // 2
				alist.add(rs.getString("id")); // 3
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

	public String getCoursedemoVideoPath(int id) {
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
	
	public ArrayList<ArrayList<String>> getIfExitCourseWiseSet(String userId,String set_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();

//			q="select distinct count(user_id) as user_id ,set_id,status,course_id ,user_id\n"
//					+ "from edu_lms_system_ele_course_sets_link_child  where user_id=? group by 2,3,4,5 ";
			
//			q="select distinct count(course_id) as count_course_id ,set_id,status,course_id,user_id \n"
//					+ "from edu_lms_system_ele_course_sets_link_child  where user_id=? and status='0' group by 2,3,4,5 ";
			q="select distinct count(csl_ch.course_id) as count_course_id ,csl_ch.set_id,csl_ch.status,csl_ch.course_id,csl_ch.user_id \n"
					+ "from edu_lms_system_ele_course_sets_link_child csl_ch \n"
					+ "inner join edu_lms_system_ele_course_sets_link_parent cslp on cslp.id=csl_ch.p_id\n"
					+ "where csl_ch.user_id=? and csl_ch.status='0'and cslp.payment_status=1 group by 2,3,4,5";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(userId));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("user_id"));// 0
				alist.add(rs.getString("set_id"));// 1
				alist.add(rs.getString("course_id"));// 2
				alist.add(rs.getString("count_course_id"));// 3
				list.add(alist);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
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
