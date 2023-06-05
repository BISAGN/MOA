package com.AyushEdu.dao.LMS_Institute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Stud_Elect_Courses_DaoImpl implements Stud_Elect_Courses_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	///course duration list
	public ArrayList<ArrayList<String>> getCourse_Duration(SessionFactory sessionFactory,String userId,String system_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//			
//			q="SELECT  DISTINCT  (TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks \n"
//					+ "from edu_lms_system_course_duration scd \n"
//					+ "inner join edu_lms_student_details sd on sd.system=scd.system_id where scd.system_id=? order by weeks ";
			
			q="SELECT  DISTINCT  (TO_DATE(scd.end_date::text, 'YYYY/MM/DD')-TO_DATE(scd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks \n"
					+ "					from edu_lms_system_course_duration scd \n"
					+ "					inner join edu_lms_ele_course_mstr cm on cm.course_name::text = scd.course_id::text\n"
					+ "					inner join edu_lms_system_ele_course_sets_link_child slc on (slc.system_id = scd.system_id and slc.course_id=cm.id)\n"
					+ "					inner join edu_lms_student_details sd on sd.system=scd.system_id where scd.system_id=? order by weeks\n"
					+ "					";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(system_id));
		//	stmt.setInt(2, Integer.parseInt(userId));

			ResultSet rs = stmt.executeQuery();
			
			System.err.println("----27------stmt "+stmt);
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("weeks"));// 0
				
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

	///course start date list
	     public ArrayList<ArrayList<String>> getCourse_Start_Date(SessionFactory sessionFactory,String system_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();

				 q="SELECT  DISTINCT  to_char(scd.start_date,'DD-MON-YYYY') as start_date \n"
				 		+ "from edu_lms_system_course_duration scd \n"
				 		+ "inner join edu_lms_student_details sd on sd.system=scd.system_id where scd.system_id=? \n"
				 		+ "order by start_date  ";
				
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1, Integer.parseInt(system_id));

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("start_date"));// 0
					
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
	     
	   ///course category list
	 	public ArrayList<ArrayList<String>> getcoursenameList_new(SessionFactory sessionFactory,String system_id) {

	 		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	 		Connection conn = null;
	 		String q = "";
	 		try {
	 			conn = dataSource.getConnection();

	 			q="select ecl.elec_course_id,cm.course_name from edu_lms_system_ele_course_link ecl\n"
	 					+ " inner join edu_lms_ele_course_mstr ecm on ecm.id=ecl.elec_course_id\n"
	 					+ " inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int where system_id=? ";
	 			
	 			PreparedStatement stmt = conn.prepareStatement(q); 
	 			stmt.setInt(1, Integer.parseInt(system_id));
	 		

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
	     
	   //getCourse_wise_images_fetch shivali Upcoming
			@Override
			public String getCourse_wise_images_fetch_list(String id,String system_id) {
				String whr="";
				Connection conn = null;
				try {	
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
			 		String query = null;
//					query="select upload_img from edu_lms_ele_course_mstr Where status ='1' and id=?" ;
			 		if(id == "0") {
			 			query="select ecm.upload_img,ecm.course_description,cd.start_date::date from edu_lms_ele_course_mstr ecm\n"
			 					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
				 				+ "inner join edu_lms_system_course_duration cd on cd.course_id=cm.id\n"
				 				+ "Where ecm.status ='1' and cd.start_date > current_timestamp ";
			 		}else{
			 		    query="select ecm.upload_img,ecm.course_description,cd.start_date::date from edu_lms_ele_course_mstr ecm\n"
			 		    		+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int \n"
			 				+ "inner join edu_lms_system_course_duration cd on cd.course_id=cm.id\n"
			 				+ "Where ecm.status ='1' and ecm.id=? and cd.start_date > current_timestamp ";
			 		}
					stmt = conn.prepareStatement(query);
					stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
		 	           whr=rs.getString("upload_img");             	
		 	        }
		 		    rs.close();
		 	    	stmt.close();
		 			conn.close();
		     	} catch (SQLException e) {
		     			e.printStackTrace();
		     	}	
				return whr;
			}
		
		//getCourse_wise_Description_fetch shivali Upcoming
		public ArrayList<ArrayList<String>> getCourse_Description_fetch_list(String userId,String course_category,String course_duration,String course_start_date, String system_id,String institute_id) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			String SearchValue = GenerateQueryWhereClause_SQL(course_category, course_duration,course_start_date,system_id,institute_id,userId);
			Connection conn = null;
			String q = "";
			try {
				System.out.println("1-"+course_category+"-2-"+course_duration+"-3-"+course_start_date);
				conn = dataSource.getConnection();
				
				q="(select distinct cm.id,cm.course_name,em.course_description,upload_img,to_char(dd.start_date,'DD-MON-YYYY')as start_date from logininformation lo\n"
						+ "inner join edu_lms_student_details st on st.name=lo.username\n"
						+ "inner join logininformation lo2 on lo2.userid=lo.institute_id\n"
						+ "inner join edu_lms_system_degree_map_inst ls on ls.institute_id=lo2.institute_id\n"
						+ "inner join edu_lms_ele_course_mstr em on st.degree=em.degree_id and st.semester=em.semester_id::text\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=em.course_name::int \n"
						+ "inner join edu_lms_course_content cc on cc.course_name=em.id\n"
						+ "inner join edu_lms_system_course_duration dd on dd.course_id=em.id\n"
						+ "where em.status ='1' and cc.app_status='1' and cc.system_name=? \n"
						+ "and lo.userid= ? and em.id not in (select ele_course_id from edu_lms_system_ele_course_sets_link_parent slp where slp.user_id=? and status='0')  "+  SearchValue+" ) ";
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt = setQueryWhereClause_SQL(stmt,course_category, course_duration,course_start_date,system_id,institute_id,userId);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("course_description"));// 0
					alist.add(rs.getString("id"));//1
					alist.add(rs.getString("course_name"));//2
					alist.add(rs.getString("start_date"));//3
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
		public String GenerateQueryWhereClause_SQL(String course_category, String course_duration,String course_start_date,String system_id,String institute_id,String userId) {
			
			String SearchValue = "";

			/// advance search

			if (!course_category.trim().equals("0")) {
				SearchValue += " and em.id =? ";
			}
			if (!course_duration.trim().equals("0")) {
				SearchValue += " and (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";
			}
			if (!course_start_date.trim().equals("0")) {
				SearchValue += " and to_char(start_date,'DD-MON-YYYY')=? ";
			}
			return SearchValue;
		}

		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String course_category, String course_duration,String course_start_date,String system_id,String institute_id,String userId) {
			int flag = 0;
			try {
				
				if (!system_id.trim().equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(system_id));
				}
				if (!userId.trim().equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(userId));
				}
				if (!userId.trim().equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(userId));
				}
				if (!course_category.trim().equals("0")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(course_category));
				}
				if (!course_duration.trim().equals("0")) {
					flag += 1;
					stmt.setString(flag,course_duration);
				}
				if (!course_start_date.trim().equals("0")) {
					flag += 1;
					stmt.setString(flag,course_start_date);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	
//-------------------------END UPCOMING FILTER----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//		
		


//---------------------------------------systemlist get method--------------------------------------------------///

public ArrayList<ArrayList<String>> getsystem_list(String userId) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q=" select st.system from logininformation lo inner join edu_lms_student_details st  on lo.email_id=st.email where lo.userid=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q); 
		stmt.setInt(1, Integer.parseInt(userId));

		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("system"));// 0
			
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

//----------------degreelist get method==================================

public ArrayList<ArrayList<String>> getdegree_list(String userId) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q=" select st.degree from logininformation lo inner join edu_lms_student_details st  on lo.email_id=st.email where lo.userid=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q); 
		stmt.setInt(1, Integer.parseInt(userId));

		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("degree"));// 0
			
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

//----------------term list get method==================================

public ArrayList<ArrayList<String>> getterm_list(String userId) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

//		q=" select sm.term_id from edu_lms_set_mstr sm \n"
//				+ "inner join (select * from logininformation lo \n"
//				+ "inner join edu_lms_student_details sd on lo.email_id=sd.email ) lo on lo.semester>=sm.term_id::text where lo.userid=? ";
		//change09
		q="select  sm.term_id from edu_lms_set_mstr sm \n"
				+ "inner join edu_lms_term_mstr tm on tm.id=sm.term_id\n"
				+ "inner join (select * from logininformation lo \n"
				+ "inner join edu_lms_student_details sd on lo.email_id=sd.email ) lo \n"
				+ "on lo.semester>=tm.term::text where lo.userid=? ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		
		stmt.setInt(1, Integer.parseInt(userId));
		System.out.println("stmt temr "+stmt);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("term_id"));// 0
			
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

public String getTopicChoose_Ele_Course_Stu(String id) {
	Session sessionHQL = sessionFactory.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	System.err.println("----------id" + id);
try {
	Query q1 = sessionHQL.createQuery("select course_video from EDU_LMS_LINK_COURSE_SET_MSTR where id=:id ");
	q1.setParameter("id", Integer.parseInt(id));
	System.out.println("q1 abh================= "+q1);
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

public ArrayList<ArrayList<String>> getCourse_Description_fetch_new(String userId, String course_category,
		String course_duration, String course_start_date, String system_id1, String degree_id1, String term_id1) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	String SearchValue = GenerateQueryWhereClause_SQL_New(userId, course_category, course_duration, course_start_date,
			system_id1, degree_id1, term_id1);
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();

//		q = "select distinct String_agg(distinct prof_name,',') as prof_name,String_agg(distinct setname,',') as setname,m.term_id,p.course_video,p.description,p.id,to_char(cd.start_date,'DD-MON-YYYY')as start_date \n"
//				+ "from edu_lms_set_mstr m\n" + "inner join edu_lms_link_course_set_mstr_child c on c.set_id=m.id\n"
//				+ "inner join edu_lms_link_course_set_mstr p on p.id=c.p_id\n"
//				+ "inner join edu_lms_term_mstr tm on tm.id=m.term_id\n"
//				+ "inner join (select * from logininformation lo \n"
//				+ "inner join edu_lms_student_details sd on lo.email_id=sd.email ) lo on lo.semester<=tm.term::text \n"
//				+ "inner join edu_lms_degree_mstr dg on dg.id=lo.degree\n"
//				+ "inner join edu_lms_system_course_duration cd on cd.degree_id=p.degree_id " + SearchValue
////+ "where lo.userid=? and p.system_id=? and p.degree_id=? " 
//				+ "group by 3,4,5,6,7";

		 q="select distinct String_agg(distinct tm.prof_name,',') as prof_name,String_agg(distinct setname,',') as setname,m.term_id,p.course_video,p.description,p.id \n"
		  		  + "from edu_lms_set_mstr m\n"
		  		  + "inner join edu_lms_link_course_set_mstr_child c on c.set_id=m.id\n"
		  		  + "inner join edu_lms_link_course_set_mstr p on p.id=c.p_id\n"
		  		  + "inner join edu_lms_term_mstr tm on tm.id=m.term_id\n"
		  		  + "inner join (select * from logininformation lo \n"
		  		  + "inner join edu_lms_student_details sd on lo.email_id=sd.email ) lo on lo.semester>=tm.term::text \n"
		  		  + "inner join edu_lms_degree_mstr dg on dg.id=lo.degree\n"
		  		  + "inner join edu_lms_system_course_duration cd on cd.degree_id=p.degree_id  and lo.userid =?  and p.system_id =?  and p.degree_id =?  and m.term_id in het group by 3,4,5,6";
		 String term="(";
			for(int i=0;i<term_id1.split(",").length;i++) {
				if(i==0) {
					term+="'"+term_id1.split(",")[i]+"'";
				}else {
					term+=","+"'"+term_id1.split(",")[i]+"'";
				}
			}
			term+=")";
			q=q.replaceAll("het",term);
			//System.out.println("q "+q.replaceAll("het",term));
		 PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_New(stmt, userId, course_category, course_duration, course_start_date,
				system_id1, degree_id1, term_id1);
		System.out.println("stmt abh================= "+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();

			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("description"));// 1
			alist.add(rs.getString("prof_name"));// 2
			alist.add(rs.getString("term_id"));// 3
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
public String GenerateQueryWhereClause_SQL_New(String userId, String course_category, String course_duration,
		String course_start_date, String system_id1, String degree_id1, String term_id1) {

	String SearchValue = "";

/// advance search

	if (!userId.trim().equals("0")) {
		SearchValue += " and lo.userid =? ";
	}
	if (!system_id1.trim().equals("0")) {
		SearchValue += " and p.system_id =? ";
	}
	if (!degree_id1.trim().equals("0")) {
		SearchValue += " and p.degree_id =? ";

	}
	if (!term_id1.trim().equals("0")) {
		
		SearchValue += "and m.term_id in";
	}
	
	if (!course_duration.trim().equals("0")) {
		SearchValue += " and (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";
	}
	if (!course_start_date.trim().equals("0")) {
		SearchValue += " and to_char(cd.start_date,'DD-MON-YYYY')=? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_New(PreparedStatement stmt, String userId, String course_category,
		String course_duration, String course_start_date, String system_id1, String degree_id1, String term_id1) {
	int flag = 0;
	try {

		if (!userId.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(userId));
		}
		if (!system_id1.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(system_id1));
		}
		if (!degree_id1.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(degree_id1));
		}
		
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
public String Already_Applied_Path_fetch_list_My_Courses(String id, String userId, String system_id) {
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

public ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses_List(String userId,
		String course_category2, String course_duration2, String course_start_date2, String system_id2,
		String degree_id2, String term_id2) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	String SearchValue = GenerateQueryWhereClause_SQL_My_Courses(userId, course_category2, course_duration2,
			course_start_date2, system_id2, degree_id2, term_id2);
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
        q= "select distinct cm.id,c.id as childid,c.course_id,p.id as p_id,cm2.course_name,cm.upload_img,cm.course_description,to_char(cd.start_date,'DD-MON-YYYY')as start_date,\n"
        		+ "m.setname,m.prof_name,c.course_id\n"
        		+ "from edu_lms_system_ele_course_sets_link_parent p\n"
        		+ "inner join edu_lms_system_ele_course_sets_link_child c on c.p_id=p.id\n"
        		+ "inner join edu_lms_ele_course_mstr cm on cm.id=c.course_id \n"
        		+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
        		+ "inner join edu_lms_system_course_duration cd on cd.course_id=cm2.id \n"
        		+ "inner join edu_lms_set_mstr m on m.id=c.set_id where c.status='0' and p.payment_status=1" + SearchValue + " ";
        


		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_My_Courses(stmt, userId, course_category2, course_duration2, course_start_date2,
			   system_id2, degree_id2, term_id2);
//stmt.setInt(1,Integer.parseInt(userId));
//stmt.setInt(2,Integer.parseInt(system_id1));
//stmt.setInt(3,Integer.parseInt(degree_id1));

		System.err.println("stmt------------shivali-----MyCourses---06---" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
            
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("setname"));// 1
			alist.add(rs.getString("course_name"));// 2
			alist.add(rs.getString("course_description"));// 3
			alist.add(rs.getString("start_date"));// 4
			alist.add(rs.getString("p_id"));// 5
            alist.add(rs.getString("childid"));// 6
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
public String GenerateQueryWhereClause_SQL_My_Courses(String userId, String course_category2, String course_duration2,
		String course_start_date2, String system_id2, String degree_id2, String term_id2) {

	String SearchValue = "";

/// advance search

	if (!userId.trim().equals("0")) {
		SearchValue += " and p.user_id =? ";

	}
	if (!system_id2.trim().equals("0")) {
		SearchValue += " and p.system_id =? ";
	}
	if (!degree_id2.trim().equals("0")) {
		SearchValue += " and p.degree_id =? ";
	}
	if (!term_id2.trim().equals("0")) {
		SearchValue += " and cd.term_id =? ";
	}
	if (!course_category2.trim().equals("0")) {
	SearchValue += " and cm2.id =? ";
	}
	if (!course_duration2.trim().equals("0")) {
		SearchValue += " and (TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD'))/7||' weeks'=? ";
	}
	if (!course_start_date2.trim().equals("0")) {
		SearchValue += " and to_char(cd.start_date,'DD-MON-YYYY')=? ";
	}
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_My_Courses(PreparedStatement stmt, String userId,
		String course_category2, String course_duration2, String course_start_date2, String system_id2,
		String degree_id2, String term_id2) {
	int flag = 0;
	try {

		if (!userId.trim().equals("0")) {
			flag += 1;
			stmt.setInt(flag, Integer.parseInt(userId));
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
		if (!course_category2.trim().equals("0")) {
		    flag += 1;
		    stmt.setInt(flag,Integer.parseInt(course_category2));
		}
		if (!course_duration2.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, course_duration2);
		}
		if (!course_start_date2.trim().equals("0")) {
			flag += 1;
			stmt.setString(flag, course_start_date2);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
  }


public ArrayList<ArrayList<String>> getExitCourse_Switch_Duration(String course_category) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select distinct slc.created_date,cd.course_switch_duration,cm.id,cm.course_name,current_date as c_date,slc.set_id,slc.user_id \n"
					+ "from edu_lms_system_ele_course_sets_link_child slc\n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=slc.course_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
					+ "inner join edu_lms_system_course_duration cd on cd.course_id=ecm.course_name::int where slc.id=? ";
			
//			q="select distinct count(slc.course_id) as count_course_id,slc.created_date,cd.course_switch_duration,cm.id,cm.course_name,current_date as c_date,slc.set_id,slc.user_id \n"
//					+ "from edu_lms_system_ele_course_sets_link_child slc\n"
//					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=slc.course_id\n"
//					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
//					+ "inner join edu_lms_system_course_duration cd on cd.course_id=ecm.course_name::int where slc.id=? group by 2,3,4,5,6,7,8";
//			
//			q = " select distinct count(slc.course_id) as count_course_id,slc.created_date,cd.course_switch_duration,cm.id,cm.course_name,current_date as c_date,slc.set_id,slc.user_id \n"
//					+ "from edu_lms_system_ele_course_sets_link_child slc\n"
//					+ "inner join edu_lms_system_ele_course_sets_link_parent p on p.id=slc.p_id\n"
//					+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=slc.course_id\n"
//					+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
//					+ "inner join edu_lms_system_course_duration cd on cd.course_id=ecm.course_name::int where slc.p_id=589 and slc.set_id=16 group by 2,3,4,5,6,7,8";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			
			
			
			
			stmt.setInt(1, Integer.parseInt(course_category));
		
			
			System.err.println("fhg--------->   "+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_switch_duration"));// 0
				alist.add(rs.getString("created_date"));// 1
				alist.add(rs.getString("c_date"));// 2
				alist.add(rs.getString("set_id"));// 3
				alist.add(rs.getString("user_id"));// 4
//				alist.add(rs.getString("count_course_id"));// 5
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

public ArrayList<ArrayList<String>> getExitCourse_count(String userId, String set_id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q="select distinct count(slc.course_id) as count_course_id,sm.setname,slc.set_id \n"
				+ "from edu_lms_system_ele_course_sets_link_child slc\n"
				+ "inner join edu_lms_system_ele_course_sets_link_parent p on p.id=slc.p_id\n"
				+ "inner join edu_lms_set_mstr sm on sm.id=slc.set_id\n"
				+ "where slc.user_id=? and slc.set_id=? and slc.status='0' and p.payment_status='1' group by sm.setname,slc.set_id ";
		
//		q="select distinct count(slc.course_id) as count_course_id,slc.created_date,cd.course_switch_duration,cm.id,cm.course_name,current_date as c_date,slc.set_id,slc.user_id \n"
//				+ "from edu_lms_system_ele_course_sets_link_child slc\n"
//				+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=slc.course_id\n"
//				+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
//				+ "inner join edu_lms_system_course_duration cd on cd.course_id=ecm.course_name::int where slc.id=? group by 2,3,4,5,6,7,8";
//		
//		q = " select distinct count(slc.course_id) as count_course_id,slc.created_date,cd.course_switch_duration,cm.id,cm.course_name,current_date as c_date,slc.set_id,slc.user_id \n"
//				+ "from edu_lms_system_ele_course_sets_link_child slc\n"
//				+ "inner join edu_lms_system_ele_course_sets_link_parent p on p.id=slc.p_id\n"
//				+ "inner join edu_lms_ele_course_mstr ecm on ecm.id=slc.course_id\n"
//				+ "inner join edu_lms_course_mstr cm on cm.id=ecm.course_name::int\n"
//				+ "inner join edu_lms_system_course_duration cd on cd.course_id=ecm.course_name::int where slc.p_id=589 and slc.set_id=16 group by 2,3,4,5,6,7,8";
		
		PreparedStatement stmt = conn.prepareStatement(q); 
		stmt.setInt(1, Integer.parseInt(userId));
		stmt.setInt(2, Integer.parseInt(set_id));
	

		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("count_course_id"));// 0
			alist.add(rs.getString("setname"));// 1
			alist.add(rs.getString("set_id"));// 2
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

//public ArrayList<ArrayList<String>> CheckEnroll_Is(String user_id) {
//
//	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//	Connection conn = null;
//	String q = "";
//	try {
//		conn = dataSource.getConnection();
//
//		q="select distinct count(user_id) as count_user_id ,set_id,status,course_id,user_id \n"
//				+ "from edu_lms_system_ele_course_sets_link_child  where user_id=? and status='2' group by 2,3,4,5  ";
//		
//		PreparedStatement stmt = conn.prepareStatement(q); 
//		stmt.setInt(1, Integer.parseInt(user_id));
//	
//
//		ResultSet rs = stmt.executeQuery();
//		
//		while (rs.next()) {
//			ArrayList<String> alist = new ArrayList<String>();
//			alist.add(rs.getString("count_user_id"));// 0
//			list.add(alist);
//		}
//		rs.close();
//		stmt.close();
//		conn.close();
//	}
//	catch (SQLException e) {
//		e.printStackTrace();
//	} finally {
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	return list;
//}

}
