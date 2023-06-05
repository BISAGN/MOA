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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDuration_DaoImpl implements CourseDuration_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

/////////////////////Riddhi

	public ArrayList<ArrayList<String>> getCourse_End_Date(SessionFactory sessionFactory) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			// q=" SELECT DISTINCT to_char(start_date,'DD-MON-YYYY') as start_date from
			// edu_lms_system_course_duration order by start_date ";

			q = "SELECT  DISTINCT  to_char(scd.end_date,'DD-MON-YYYY') as end_date \n"
					+ "from edu_lms_system_course_duration scd \n" + "order by end_date  ";

			PreparedStatement stmt = conn.prepareStatement(q);
			// stmt.setString(1, system_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("end_date"));// 0

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

	/// course duration list
	public ArrayList<ArrayList<String>> getCourse_Duration(SessionFactory sessionFactory, String userId) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "SELECT  DISTINCT  (TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks \n"
					+ "from edu_lms_system_course_duration scd \n"
					+ "inner join edu_lms_student_details sd on sd.system=scd.system_id order by weeks ";
			PreparedStatement stmt = conn.prepareStatement(q);


			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("weeks"));// 0

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

	/// course start date list
	public ArrayList<ArrayList<String>> getCourse_Start_Date(SessionFactory sessionFactory) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//						q=" SELECT  DISTINCT  to_char(start_date,'DD-MON-YYYY') as start_date from edu_lms_system_course_duration order by start_date  ";

			q = "SELECT  DISTINCT  to_char(scd.start_date,'DD-MON-YYYY') as start_date \n"
					+ "from edu_lms_system_course_duration scd \n"
					+ "inner join edu_lms_student_details sd on sd.system=scd.system_id \n" + "order by start_date  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("start_date"));// 0

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

	/// course category list
	public ArrayList<ArrayList<String>> getcoursenameList_new(SessionFactory sessionFactory) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//			 			q=" SELECT  DISTINCT  (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks from edu_lms_system_course_duration  order by weeks ";

			q = "select ecl.elec_course_id,cm.course_name from edu_lms_system_ele_course_link ecl\n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=ecl.elec_course_id::int";

			PreparedStatement stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("elec_course_id"));// 0
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

	// -------systemlist get method
	public ArrayList<ArrayList<String>> getsystem_list(String userId) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//					System.err.println("-----user_id "+userId);
		try {
			conn = dataSource.getConnection();

			q = " select system from logininformation lo inner join edu_lms_student_details st  on lo.email_id=st.email where lo.userid=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userId));
//						System.out.println("stmt--------getsystem_list----shivali----------->"+stmt);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("system"));// 0

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

	public ArrayList<ArrayList<String>> getCourses_System_degree_term_Fetch(String system_id, String degree_id,
			String term_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "SELECT DISTINCT lcm.course_name,cm.course_name as id FROM public.edu_lms_link_course_set_mstr p \n"
					+ "inner join edu_lms_link_course_set_mstr_child c on c.p_id=p.id\n"
					+ "inner join edu_lms_set_mstr m on m.id=c.set_id\n"
					+ "inner join edu_lms_ele_course_mstr  cm on cm.id::text= c.course_id::text\n"
					+ "inner join  edu_lms_course_content co on co.course_name::text=cm.course_name\n"
					+ "inner join edu_lms_course_mstr lcm on lcm.id=cm.course_name::int\n"
					+ "where term_id=? and p.degree_id=? and p.system_id=? ";
				

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(term_id));
			stmt.setInt(2, Integer.parseInt(degree_id));
			stmt.setInt(3, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();
System.err.println("------>> stmt "+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 1

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

/////////////////////courses////////////////////////

	public String getTopicChoose_Ele_Course_Stu_Search(String id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.println("----------id" + id);
//try {
		Query q1 = sessionHQL.createQuery("select course_video from EDU_LMS_LINK_COURSE_SET_MSTR where id=:id ");
		q1.setParameter("id", Integer.parseInt(id));
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		System.err.println("LIST SHIVALI---" + list);
		String path = list.get(0);
		tx.commit();
		return path;
//} catch (RuntimeException e) {
//return null;
//} finally {
//if (sessionHQL != null) {
//sessionHQL.close();
//}
//}

	}

	public ArrayList<ArrayList<String>> getCourse_Description_fetch_new_Search(String course_category,
			String course_duration, String course_start_date) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL_New_Search(course_category, course_duration,
				course_start_date);
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "select distinct String_agg(distinct setname,',') as setname ,m.term_id,p.course_video,p.description,p.id,to_char(cd.start_date,'DD-MON-YYYY')as start_date \n"
					+ "from edu_lms_set_mstr m\n" + "inner join edu_lms_link_course_set_mstr_child c on c.set_id=m.id\n"
					+ "inner join edu_lms_link_course_set_mstr p on p.id=c.p_id\n"
					+ "inner join (select * from logininformation lo \n"
					+ "inner join edu_lms_student_details sd on lo.email_id=sd.email ) lo on lo.semester>=m.term_id::text \n"
					+ "inner join edu_lms_degree_mstr dg on dg.id=lo.degree\n"
					+ "inner join edu_lms_system_course_duration cd on cd.degree_id=p.degree_id " + SearchValue
//+ "where lo.userid=? and p.system_id=? and p.degree_id=? " 
					+ "group by 2,3,4,5,6 ";

//+ SearchValue 

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_New_Search(stmt, course_category, course_duration, course_start_date);
//stmt.setInt(1,Integer.parseInt(userId));
//stmt.setInt(2,Integer.parseInt(system_id1));
//stmt.setInt(3,Integer.parseInt(degree_id1));

			System.err.println("stmt-Courses---17---" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("description"));// 1
				alist.add(rs.getString("start_date"));// 2
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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL_New_Search(String course_category, String course_duration,
			String course_start_date) {

		String SearchValue = "";

/// advance search

//if (!userId.trim().equals("0")) {
//	SearchValue += " where lo.userid =? ";
//
//}

//if (!system_id1.trim().equals("0")) {
//	SearchValue += " and p.system_id =? ";
//
//}
//if (!degree_id1.trim().equals("0")) {
//	SearchValue += " and p.degree_id =? ";
//
//}
//if (!term_id1.trim().equals("0")) {
//	SearchValue += " and ( m.term_id =? or m.term_id=2)";
//
//}
//if (!course_category.trim().equals("0")) {
//SearchValue += " and cd.course_id =? ";
//
//}
		if (!course_duration.trim().equals("0")) {
			SearchValue += " and (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";

		}

		if (!course_start_date.trim().equals("0")) {
			SearchValue += " and to_char(cd.start_date,'DD-MON-YYYY')=? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_New_Search(PreparedStatement stmt, String course_category,
			String course_duration, String course_start_date) {
		int flag = 0;
		try {
//
//	if (!userId.trim().equals("0")) {
//		flag += 1;
//      //stmt.setString(flag,userId);
//		stmt.setInt(flag, Integer.parseInt(userId));
//	}
//	if (!system_id1.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(system_id1));
//	}
//	if (!degree_id1.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(degree_id1));
//	}
//	if (!term_id1.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(term_id1));
//	}

//if (!course_category.trim().equals("0")) {
//flag += 1;
//stmt.setInt(flag,Integer.parseInt(course_category));
//}

			if (!course_duration.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, course_duration);
			}

			if (!course_start_date.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, course_start_date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

///------------------MyCourses----------------------------------------------///

	@Override
	public String Already_Applied_Path_fetch_list_My_Courses_Search(String id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;

			query = "select upload_img from edu_lms_ele_course_mstr where id=? ";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			//stmt.setInt(1,Integer.parseInt(userId));

			System.err.println("stmt--------img path----shivali-----Already Applied---------" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("upload_img");// 0
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses_List_Search(String course_duration2, String course_start_date2,String system_id2,String degree_id2,String term_id2,String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL_My_Courses_Search(course_duration2,
				course_start_date2,system_id2, degree_id2, term_id2);
		Connection conn = null;
		String q = "";
		String where = "";

		try {
			conn = dataSource.getConnection();
//            q="select distinct cm.id,c.id as childid,c.course_id,p.id as p_id,cm2.course_name,cm.upload_img,cm.course_description,to_char(cd.start_date,'DD-MON-YYYY')as start_date,\n"
//            		+ "        		m.setname,m.prof_name,c.course_id,count(c.user_id) as u_id\n"
//            		+ "        		from edu_lms_system_ele_course_sets_link_parent p\n"
//            		+ "        		inner join edu_lms_system_ele_course_sets_link_child c on c.p_id=p.id\n"
//            		+ "        		inner join edu_lms_ele_course_mstr cm on cm.id=c.course_id \n"
//            		+ "        		inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
//            		+ "        		inner join edu_lms_system_course_duration cd on cd.course_id=cm2.id \n"
//            		+ "        		inner join edu_lms_set_mstr m on m.id=c.set_id "+ SearchValue +  " group by 1,2,3,4,5,6,7,8,9,10";
			
			q="select distinct cm.id,cm2.course_name,cm.upload_img,cd.system_id,cd.degree_id,cd.term_id,cm.course_description,to_char(cd.start_date,'DD-MON-YYYY')as start_date,\n"
					+ "m.setname,m.prof_name,coalesce(counts,0) as u_id from edu_lms_system_course_duration cd \n"
					+ "inner join edu_lms_ele_course_mstr cm on (cm.course_name=cd.course_id::text and cm.degree_id=cd.degree_id)\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id = cd.course_id \n"
					+ "inner join edu_lms_link_course_set_mstr_child smc on smc.course_id = cm.id\n"
					+ "inner join edu_lms_set_mstr m on m.id=smc.set_id\n"
					+"inner join edu_lms_system_mstr sm on sm.id=cd.system_id\n"
					+ "left join (select c.system_id,c.course_id,count(c.user_id) as counts from edu_lms_system_ele_course_sets_link_child c group by c.system_id,c.course_id) c\n"
					+ "on c.course_id::text=cm.id::text where sm.created_role=? "+ SearchValue +"";
            




			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, role);
			stmt = setQueryWhereClause_SQL_My_Courses_Search(stmt,course_duration2,
					course_start_date2, system_id2, degree_id2, term_id2);
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt============"+stmt);

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("setname"));// 1
				alist.add(rs.getString("course_name"));// 2
				alist.add(rs.getString("course_description"));// 3
				alist.add(rs.getString("start_date"));// 4
				alist.add(rs.getString("u_id"));// 5
              //alist.add(rs.getString("upload_img"));// 5
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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL_My_Courses_Search(String course_duration2,
			String course_start_date2,String system_id2,String degree_id2,String term_id2) {

		String SearchValue = "";
		String where = "";
/// advance search

//if (!userId.trim().equals("0")) {
//	SearchValue += " where p.user_id =? ";
//
//}
//if (!system_id2.trim().equals("0")) {
//	SearchValue += " and p.system_id =? ";
//
//}
//if (!degree_id2.trim().equals("0")) {
//	SearchValue += " and p.degree_id =? ";
//
//}
//if (!term_id2.trim().equals("0")) {
//	SearchValue += " and cd.term_id =? ";
//
//}
//if (!course_category.trim().equals("0")) {
//SearchValue += " and cd.course_id =? ";
//
//}
		
		System.err.println("========system_id2 "+system_id2);
		if (!course_duration2.trim().equals("0")) {
			if(SearchValue.contains("where")) {
			  SearchValue += " and (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";
			}
			else {
			  SearchValue += " where (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";
			}
		}

		if (!course_start_date2.trim().equals("0")) {
			if(SearchValue.contains("where")) {
			   SearchValue += " and to_char(cd.start_date,'DD-MON-YYYY')=? ";
			}
			else {
				SearchValue += " where to_char(cd.start_date,'DD-MON-YYYY')=? ";
			}
		}
		
		if (!system_id2.trim().equals("0")) {
			if(SearchValue.contains("where")) {
			SearchValue += " and cd.system_id =? ";
			}
			else {
				SearchValue += " where cd.system_id =? ";
			}
		}
		if (!degree_id2.trim().equals("0")) {
			if(SearchValue.contains("where")) {
			SearchValue += " and cd.degree_id =? ";
			}
			else {
				SearchValue += " where cd.degree_id =? ";
			}

		}
		if (!term_id2.trim().equals("0")) {
			if(SearchValue.contains("where")) {
			SearchValue += " and cd.term_id =? ";
			}
			else {
		    SearchValue += " where cd.term_id =? ";
			}

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_My_Courses_Search(PreparedStatement stmt,
			String course_duration2, String course_start_date2, String system_id2,String degree_id2,String term_id2) {
		int flag = 0;
		try {

//	if (!userId.trim().equals("0")) {
//		flag += 1;
////stmt.setString(flag,userId);
//		stmt.setInt(flag, Integer.parseInt(userId));
//	}
//	if (!system_id2.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(system_id2));
//	}
//	if (!degree_id2.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(degree_id2));
//	}
//	if (!term_id2.trim().equals("0")) {
//		flag += 1;
//		stmt.setInt(flag, Integer.parseInt(term_id2));
//	}

//if (!course_category.trim().equals("0")) {
//flag += 1;
//stmt.setInt(flag,Integer.parseInt(course_category));
//}

			if (!course_duration2.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, course_duration2);
			}

			if (!course_start_date2.trim().equals("0")) {
				flag += 1;
				stmt.setString(flag, course_start_date2);
			}
			
			if (!system_id2.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id2));
			}
			if (!degree_id2.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id2));
			}
			if (!term_id2.trim().equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(term_id2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
//	public ArrayList<ArrayList<String>> GetLearn_Count1(String system_id) {
//		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		
//		String q = "";
//	
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			
//			String qry = "";
//	
//				q = "select distinct count(user_id) as u_id,count(ele_course_id)as c_id from edu_lms_system_ele_course_sets_link_parent where system_id =?";
//			
//				stmt = conn.prepareStatement(q);
//				stmt.setInt(1,Integer.parseInt(system_id));
//				
//			    System.err.println("-stmt---Count------"+stmt);
//				ResultSet rs = stmt.executeQuery();      
//				while (rs.next()) {				
//				ArrayList<String> alist = new ArrayList<String>();
//				alist.add(rs.getString("u_id")); //0
//				alist.add(rs.getString("c_id")); //1
//				list.add(alist);
//		      }
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return list;
//	}

}
