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
public class CertificateDaoImpl implements CertificateDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public ArrayList<ArrayList<String>> getpercentage(int userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//		q="select  (sum(case when answer_id=correct then marks else '0' end::int)*100/sum(marks::int)) as percentage\n"
//		+ "from (select * from edu_lms_exam_paper where user_id=? and exam_name=?) p \n"
//		+ "inner join (select p.id,String_agg(distinct correct_ans,',') as correct,marks from edu_lms_question_band_parent p inner join edu_lms_question_band_child c on c.p_id=p.id \n"
//		+ "group by p.id) c on c.id=p.quiz_id ";	
			
			
			q="select  (sum(case when answer_id=correct then marks else '0' end::int)*100/sum(marks::int)) as percentage\n"
					+ "from (select * from edu_lms_exam_paper where user_id=? ) p \n"
					+ "inner join (select p.id,String_agg(distinct correct_ans,',') as correct,marks from edu_lms_question_band_parent p inner join edu_lms_question_band_child c on c.p_id=p.id \n"
					+ "group by p.id) c on c.id=p.quiz_id ";


			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, userid);
//			stmt.setString(2,String.valueOf(exam_name));
			System.out.println("stmt----------percentage-------------->"+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("percentage"));// 0
			
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
	
	
	public ArrayList<ArrayList<String>> getcredit(int userid, int course_id) {
		System.err.println("course_id----------"+course_id);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

//				q="select m.*,min(point) as credit,\n"
//						+ "DATE_PART('day', m.modified_date::timestamp - start_date::timestamp)::int from (select ls.course_id,max(sequence),max(modified_date) as modified_date from edu_lms_student_course_enroll_sequence \n"
//						+ "					  ls where status='1' and user_id=? and course_id=?\n"
//						+ "group by course_id ) m\n"
//						+ "inner join edu_lms_system_course_duration cd on cd.course_id=m.course_id::text\n"
//						+ "inner join edu_credit_mstr ec on ec.course_id=m.course_id::text and m.modified_date between start_date and end_date\n"
//						+ "group by m.course_id,m.modified_date,m.max,cd.start_date\n"
//						+ "having min(no_of_days::int)>DATE_PART('day', m.modified_date::timestamp - start_date::timestamp)::int";
			
			q="  select sum(case when time_count >5 then 1 else '0' end) as credit from (select course_id ,module_id,sum(time::float) time_count from edu_lms_student_course_enroll_sequence  where user_id=?  \n"
					+ "  group by course_id ,module_id)m group by course_id order by  credit desc limit 3";

			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, userid);
	//		stmt.setString(2,String.valueOf(course_id));
//			stmt.setInt(2,course_id);

			System.out.println("stmt-----------credit--------------->"+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("credit"));
				
			
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
	
	
	//user- student list
	
//	public ArrayList<ArrayList<String>> getuserList(int userid, int module_id ,String exam_name) {
//
//		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		try {
//			conn = dataSource.getConnection();
//
////		q= "select distinct ROW_NUMBER() OVER(order by es.course_id asc) as sr_no, es.course_id,es.time,\n"
////				+ "(select  (sum(case when answer_id=correct then marks else '0' end::int)) as percentage\n"
////				+ "from (select * from edu_lms_exam_paper where user_id=? and exam_name=?) p \n"
////				+ "inner join (select p.id,String_agg(distinct correct_ans,',') as correct,marks from edu_lms_question_band_parent p \n"
////				+ "inner join edu_lms_question_band_child c on c.p_id=p.id group by p.id) c on c.id=p.quiz_id) as marks\n"
////				+ ",SUM (marks::int) AS Total_marks,\n"
////				+ "(select  (sum(case when answer_id=correct then marks else '0' end::int)*100/sum(marks::int)) as percentage\n"
////				+ "from (select * from edu_lms_exam_paper where user_id=? and exam_name=?) p \n"
////				+ "inner join (select p.id,String_agg(distinct correct_ans,',') as correct,marks from edu_lms_question_band_parent p \n"
////				+ "inner join edu_lms_question_band_child c on c.p_id=p.id group by p.id) c on c.id=p.quiz_id) \n"
////				+ "from edu_lms_student_course_enroll_sequence es\n"
////				+ "inner join edu_lms_question_band_parent p on p.course_id=es.course_id\n"
////				+ "WHERE p.module_id = ? and user_id=?\n"
////				+ "group by 2,3\n"
////				+ "";
//		
//		
//		
////		q=" select count(quiz_id) as total,sum(results) as obtain, (((sum(results))*100)/count(quiz_id)) as percentage\n"
////				+ " from (select DISTINCT  ep.quiz_id, (case when ep.answer_id=bc.correct_ans then marks else '0' end::int) as results   \n"
////				+ " from edu_lms_question_band_parent bp\n"
////				+ " inner join edu_lms_exam_paper ep on ep.quiz_id=bp.id\n"
////				+ " inner join  edu_lms_question_band_child bc on bc.p_id=ep.quiz_id\n"
////				+ " where user_id=244 and ep.exam_name='43718') x";
//
//		
////		q="select count(quiz_id) as total,sum(results) as obtain, (((sum(results))*100)/count(quiz_id)) as percentage,x.course_name\n"
////				+ " from (select DISTINCT  ep.quiz_id, (case when ep.answer_id=bc.correct_ans then marks else '0' end::int) as results , cm.course_name \n"
////				+ " from edu_lms_question_band_parent bp\n"
////				+ " inner join edu_lms_exam_paper ep on ep.quiz_id=bp.id\n"
////				+ " inner join  edu_lms_question_band_child bc on bc.p_id=ep.quiz_id\n"
////				+ " inner join edu_lms_ele_course_mstr cm on cm.id=ep.course_id\n"
////				+ "\n"
////				+ " where ep.user_id=? and ep.exam_name=?) x\n"
////				+ " group by x.course_name";
//			
//			q = "select sum(case when correct_ans=answer_id then marks else '0' end::int)obtain_marks,sum(marks::int)as total_marks,cm2.course_name  from ( select ep.id,question,marks,exam_name,correct_ans from  edu_lms_question_band_parent ep \n"
//					+ " inner join  edu_lms_question_band_child bc on bc.p_id=ep.id \n"
//					+ " group by ep.id,question,marks,exam_name,correct_ans) q inner join edu_lms_exam_paper p on p.quiz_id=q.id  and user_id=? \n"
//					+ "  inner join edu_lms_ele_course_mstr cm on cm.id=p.course_id \n"
//					+ "  inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int\n"
//					+ "  group by cm2.course_name ";
//		
//		
//			PreparedStatement stmt = conn.prepareStatement(q); 
//			stmt.setInt(1, userid);
////			stmt.setString(2, exam_name);
////			stmt.setInt(3,userid);
////			stmt.setString(4,exam_name);
////			stmt.setInt(5, module_id);
////			stmt.setInt(6, userid);
//			
//			System.out.println("stmt-----------sl--------------->"+stmt);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while (rs.next()) {
//				ArrayList<String> alist = new ArrayList<String>();
//				
////				alist.add(rs.getString("sr_no")); // 0
//				alist.add(rs.getString("course_name")); //1
////				alist.add(rs.getString("time")); //2
//				alist.add(rs.getString("obtain_marks")); //3
//				alist.add(rs.getString("total_marks")); //4
////				alist.add(rs.getString("credit")); //5
//				list.add(alist);
//			}
//			
//			rs.close();
//			stmt.close();
//			conn.close();
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return list;
//	}

	
	
	
}
