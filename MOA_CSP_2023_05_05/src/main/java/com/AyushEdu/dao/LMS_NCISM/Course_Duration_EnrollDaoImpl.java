package com.AyushEdu.dao.LMS_NCISM;
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
public class Course_Duration_EnrollDaoImpl implements Course_Duration_EnrollDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> GetCourse_Set(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select distinct sm.id,sm.setname  from edu_lms_link_course_set_mstr cs\n"
						+ "inner join edu_lms_set_mstr sm on sm.id = cs.set_id where cs.course_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				
			    System.err.println("-stmt---shra -----------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("setname")); //1
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
	
	public ArrayList<ArrayList<String>> GetModule_fetch(String set,String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select m.id,m.module_name  from edu_lms_link_course_set_mstr cs\n"
						+ "inner join edu_lms_module_mstr m on m.id::text = cs.module_id where cs.set_id=? and cs.course_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(set.trim()));
				stmt.setInt(2,Integer.parseInt(course_id));
			   
				ResultSet rs = stmt.executeQuery();      
				 System.err.println("-stmt---shra -1----------"+stmt);
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("module_name")); //1
			
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
	
			q = "select tc.id,tc.type_of_content,TO_CHAR(cd.start_date,'DD-MON-YYYY') as start_date,TO_CHAR(cd.end_date,'DD-MON-YYYY') as end_date, (TO_DATE(cd.end_date::text, 'YYYY/MM/DD')-TO_DATE(cd.start_date::text, 'YYYY/MM/DD'))/7||' weeks' as weeks "
					+ "from edu_lms_course_content cc\n"
					+ "inner join edu_lms_type_of_content_mstr tc on tc.id = cc.type_of_content\n"
					+ "inner join edu_lms_system_course_duration cd on cd.course_id::int = cc.course_name\n"
					+ " where cc.course_name=? GROUP BY tc.id,cc.course_name,cc.type_of_content,tc.type_of_content,cd.start_date,cd.end_date\n"
					+ "";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				
			    System.err.println("-stmt---shra -2----Riddhi------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("type_of_content")); //1
				alist.add(rs.getString("start_date")); //2
				alist.add(rs.getString("end_date")); //3
				alist.add(rs.getString("weeks")); //4
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
	
	public ArrayList<ArrayList<String>> GetCourse_Description(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select distinct cm2.id, cm.course_description from edu_lms_course_content cc\n"
						+ "inner join edu_lms_ele_course_mstr cm on cm.course_name= cc.course_name::int\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
						+ "where cc.course_name::int=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("course_description")); //1
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
	
	public ArrayList<ArrayList<String>> GetCourse_Title(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select distinct  cm2.course_name,cc.system_name from edu_lms_course_content cc\n"
						+ "inner join edu_lms_ele_course_mstr cm on cm.id= cc.course_name::int\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
						+ "where cc.course_name::int=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_name")); //0
				alist.add(rs.getString("system_name")); //1
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
	
	public ArrayList<ArrayList<String>> GetLearn_Count(String course_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select distinct count(user_id) from edu_lms_system_ele_course_sets_link_parent where ele_course_id::int =?";
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(course_id));
				
			    System.err.println("-stmt---shra ---Riddhi333-444-------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("count")); //0
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
	public String getTopicVideoPath1(String id ) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.println("----------id"+id);
		try {
			Query q1 = sessionHQL
					.createQuery("select  demo_video from EDU_LMS_SYSTEM_COURSE_DURATION  where cast(course_id as integer)=:id ");
//			select  demo_video from edu_lms_system_course_duration where course_id::int = 24
			
			q1.setParameter("id",Integer.parseInt(id));
			
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			String path=list.get(0);
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
	
	
//	public ArrayList<ArrayList<String>> GetCredit_Point(String course_id) {
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
//			q = "select distinct no_of_days,point  from (select * from edu_lms_system_course_duration where course_id=?) d inner join edu_credit_mstr c on c.course=d.cd_uniq_id";
//
//			
//				stmt = conn.prepareStatement(q);
//				stmt.setString(1,course_id);
//				
//			    System.err.println("-stmt---shra -555123----------"+stmt);
//				ResultSet rs = stmt.executeQuery();      
//				while (rs.next()) {				
//				  
//				ArrayList<String> alist = new ArrayList<String>();
////				alist.add(rs.getString("id")); //0
//				alist.add(rs.getString("no_of_days")); //1
//				alist.add(rs.getString("point")); //2
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
	


	
	public ArrayList<ArrayList<String>> GetSetModule_Fetch(String course_id,String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "select set_id from  edu_lms_system_ele_course_sets_link_parent where ele_course_id=? and  user_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,course_id);
				stmt.setString(2,userid);
				
			    System.err.println("-stmt---FOR BTNS---Riddhi333-444---exit----"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("set_id")); //0
//				alist.add(rs.getString("set_id")); //1
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
				
				q=" select h.course_name,\n"
						+ "h.id,h.level_of_course,h.level_of_module,h.module_name as module_id from edu_lms_course_content h\n"
						+ "inner join edu_lms_ele_course_mstr cm on cm.id=h.course_name::int\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
						+ "where h.course_name=? \n"
						+ "group by h.id,h.course_name,h.level_of_course,h.level_of_module,module_id \n"
						+ "order by h.level_of_course,h.level_of_module";
			
				stmt = conn.prepareStatement(q);
				
				stmt.setString(1,course_id);
//				System.err.println("STMT_SEQUENCE---"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_name")); //0
				alist.add(rs.getString("level_of_course")); //1
				alist.add(rs.getString("level_of_module")); //2
				alist.add(rs.getString("module_id")); //3
				alist.add(rs.getString("id")); //4
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
	
}
