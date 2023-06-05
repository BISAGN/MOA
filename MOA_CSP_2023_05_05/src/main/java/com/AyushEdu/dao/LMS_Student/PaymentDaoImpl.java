package com.AyushEdu.dao.LMS_Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PaymentDaoImpl implements PaymentDao {

		@Autowired
		private SessionFactory sessionFactory;
		@Autowired
		private DataSource dataSource;

		public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

		public ArrayList<ArrayList<String>> getsetByModule(String setid) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();

				q=" ";

				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1,Integer.parseInt(setid));
				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("course"));// 1
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
		
		public ArrayList<ArrayList<String>> GetCourse_Set_payment(String p_id,String userid) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			
			String q = "";
		
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				String qry = "";
		
//				q = "select distinct lp.id, s.setname,s.prof_name,STRING_AGG(DISTINCT ec.course_name,',')as p_courses,string_agg(distinct cm2.course_name,',') as course, string_agg(distinct m.module_name,',') as modules,s.prof_name\n"
//						+ "from edu_lms_system_ele_course_sets_link_child c\n"
//						+"inner join edu_lms_system_ele_course_sets_link_parent lp on lp.id=c.p_id\n"
//						+ "inner join edu_lms_set_mstr s on s.id = c.set_id \n"
//						+ "inner join edu_lms_ele_course_mstr ec on ec.id=c.course_id\n"
//						+ "inner join edu_lms_course_mstr cm2 on cm2.id=ec.course_name::int \n"
//						+ "inner join edu_lms_module_mstr m on m.course_id = c.course_id\n"
//						+ "where c.p_id = ? and lp.user_id = ? group by 1,2,3";
				q = "select distinct lp.id, s.setname,s.prof_name,STRING_AGG(DISTINCT ec.course_name,',')as p_courses,\n"
						+ "string_agg(distinct cm2.course_name,',') as course, string_agg(distinct m.module_name,',') as modules,s.prof_name,\n"
						+ "STRING_AGG(DISTINCT cd.course_fee,',')as p_fees\n"
						+ "from edu_lms_system_ele_course_sets_link_child c\n"
						+ "inner join edu_lms_system_ele_course_sets_link_parent lp on lp.id=c.p_id\n"
						+ "inner join edu_lms_set_mstr s on s.id = c.set_id \n"
						+ "inner join edu_lms_ele_course_mstr ec on ec.id=c.course_id\n"
						+ "inner join edu_lms_system_course_duration cd on cd.course_id::text = ec.course_name\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=ec.course_name::int \n"
						+ "inner join edu_lms_module_mstr m on m.course_id = c.course_id\n"
						+ "where c.p_id = ? and lp.user_id = ? group by 1,2,3";
				
//				q = "select distinct lp.id, s.setname,s.prof_name,STRING_AGG(DISTINCT ec.course_name,',')as p_courses,string_agg(distinct cm2.course_name,',') as course, string_agg(distinct m.module_name,',') as modules,s.prof_name,\n"
//						+ "cd.course_fee from edu_lms_system_ele_course_sets_link_child c\n"
//						+ "inner join edu_lms_system_ele_course_sets_link_parent lp on lp.id=c.p_id\n"
//						+ "inner join edu_lms_set_mstr s on s.id = c.set_id \n"
//						+ "inner join edu_lms_ele_course_mstr ec on ec.id=c.course_id\n"
//						+ "inner join edu_lms_system_course_duration cd on cd.course_id::text = ec.course_name\n"
//						+ "inner join edu_lms_course_mstr cm2 on cm2.id=ec.course_name::int \n"
//						+ "inner join edu_lms_module_mstr m on m.course_id = c.course_id\n"
//						+ "where c.p_id = ? and lp.user_id = ? group by 1,2,3,course_fee";
				
					stmt = conn.prepareStatement(q);
					
					stmt.setInt(1,Integer.parseInt(p_id));
					stmt.setInt(2,Integer.parseInt(userid));
					
				    System.err.println("-stmt---System wise Set---10-------"+stmt);
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {				
					  
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("setname")); //0
					alist.add(rs.getString("course")); //1
					alist.add(rs.getString("modules")); //2
					alist.add(rs.getString("prof_name")); //3
					alist.add(rs.getString("id")); //4
					alist.add(rs.getString("p_courses")); //5
					alist.add(rs.getString("p_fees")); //6
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
		
		public ArrayList<ArrayList<String>> GetModule_fetch_payment(String course_id) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				String qry = "";
		
					q = "select m.id, m.module_name, cm2.course_name from edu_lms_module_mstr m \n"
							+ "inner join edu_lms_course_content cc on cc.course_name=m.course_id\n"
							+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name = m.course_id\n"
							+ "inner join edu_lms_course_mstr cm2 on cm2.id=ecm.course_name::int \n"
							+ "where m.course_id=?";
				
					stmt = conn.prepareStatement(q);
					stmt.setInt(1,Integer.parseInt(course_id));
				   
					ResultSet rs = stmt.executeQuery();      
					 System.err.println("-stmt---Course wise Module-------"+stmt);
					while (rs.next()) {				
					  
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id")); //0
					alist.add(rs.getString("module_name")); //1
					alist.add(rs.getString("course_name")); //2
				
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

		public ArrayList<ArrayList<String>> Getcourse_fee(String course_id) {
			
			System.err.println("------coureseee---dao----"+course_id);
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			
			String q = "";
		
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				String qry = "";
				
				
//				q="select  c.course_id\n"
//						+ "from edu_lms_system_ele_course_sets_link_child c\n"
//						+ "where c.p_id = 473"
//				
//				
		
					q = "select course_fee from edu_lms_system_course_duration where course_id = ?";
				
					stmt = conn.prepareStatement(q);
					stmt.setString(1,course_id);
					
				    System.err.println("-stmt---Count------"+stmt);
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {				
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("course_fee")); //0
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
