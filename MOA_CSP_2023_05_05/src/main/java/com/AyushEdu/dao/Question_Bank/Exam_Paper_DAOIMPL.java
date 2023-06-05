package com.AyushEdu.dao.Question_Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class Exam_Paper_DAOIMPL implements Exam_Paper_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	

	
	public ArrayList<ArrayList<String>> getquestion(String course_id,String module_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select Distinct p.id,question,String_agg(Distinct c.answer,':') as Answer,String_agg(Distinct  c.correct_ans,',') as correct_ans,\n"
					+ "cm2.course_name,cm2.id as course_id,lsm.setname,lsm.id as set_id  \n"
					+ "from (select * from edu_lms_question_band_parent where course_id =? and module_id =? ) p \n"
					+ "inner join edu_lms_question_band_child c on c.p_id=p.id   \n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::text=p.course_id::text\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=ecm.course_name::int \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=p.set_id\n"
					+ "group by p.id,question,ecm.course_name,ecm.id,lsm.setname,lsm.id,cm2.course_name,cm2.id\n"
					+ "		\n" + "union all\n" + "\n"
					+ "select Distinct p.id,question,String_agg(Distinct c.answer,':'),String_agg(Distinct  c.correct_ans,',') \n"
					+ "as correct_ans,cm2.course_name,cm2.id as course_id,lsm.setname,lsm.id as set_id  from \n"
					+ "(select * from edu_lms_question_band_parent where  course_id =? and module_id =? ) p \n"
					+ "inner join edu_lms_question_band_child c on c.p_id=p.id   \n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::text=p.course_id::text\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=ecm.course_name::int \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=p.set_id\n"
					+ "group by p.id,question,ecm.course_name,ecm.id,lsm.setname,lsm.id,cm2.course_name,cm2.id\n"
					+ "										\n" + "union all \n" + "\n"
					+ "select Distinct p.id,question,String_agg(Distinct c.answer,':'),String_agg(Distinct  c.correct_ans,',') \n"
					+ "as correct_ans,cm2.course_name,cm2.id as course_id,lsm.setname,lsm.id as set_id  from \n"
					+ "(select * from edu_lms_question_band_parent where  course_id =? and module_id =? ) p \n"
					+ "inner join edu_lms_question_band_child c on c.p_id=p.id   \n"
					+ "inner join edu_lms_ele_course_mstr ecm on ecm.course_name::text=p.course_id::text\n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=ecm.course_name::int \n"
					+ "inner join edu_lms_set_mstr lsm on lsm.id=p.set_id\n"
					+ "group by p.id,question,ecm.course_name,ecm.id,lsm.setname,lsm.id,cm2.course_name,cm2.id\n" + "\n"
					+ "limit 20";

			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setInt(1, Integer.parseInt(course_id));
			stmt.setInt(2, Integer.parseInt(module_id));
			stmt.setInt(3, Integer.parseInt(course_id));
			stmt.setInt(4, Integer.parseInt(module_id));
			stmt.setInt(5, Integer.parseInt(course_id));
			stmt.setInt(6, Integer.parseInt(module_id));
			
			System.err.println("stmt----EXM PPR---28--->    "+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("question"));// 0
				alist.add(rs.getString("Answer"));// 1
				alist.add(rs.getString("correct_ans"));// 2
				alist.add(rs.getString("id"));// 3
				alist.add(rs.getString("course_name"));// 4
				alist.add(rs.getString("setname"));// 5
				alist.add(rs.getString("course_id"));// 6
				alist.add(rs.getString("set_id"));// 7

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
	
	
	public ArrayList<ArrayList<String>> getattemptedQuizdata(int userid,String course_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select quiz_id,answer_id FROM public.edu_lms_exam_paper where user_id = ? and course_id = ?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);
			stmt.setInt(2, Integer.parseInt(course_id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("quiz_id"));// 0
				alist.add(rs.getString("answer_id"));// 1

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
	
	public ArrayList<ArrayList<String>> getcorrectanscheck(int quiz_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select c.correct_ans,p.marks FROM edu_lms_question_band_child c \n"
					+ "					inner join edu_lms_question_band_parent p on p.id=c.p_id \n"
					+ "					where p_id = ? group by 1,2";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, quiz_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("correct_ans"));// 0
				alist.add(rs.getString("marks"));// 1

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
	
	public ArrayList<ArrayList<String>> getcoursename() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "	select distinct cm2.course_name from edu_lms_system_ele_course_sets_link_parent slp \n"
					+ "	inner join  edu_lms_ele_course_mstr c on c.course_name=slp.ele_course_id"
					+ " inner join edu_lms_course_mstr cm2 on cm2.id=c.course_name::int \n" + "where user_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(q);
			// stmt.setInt(1, quiz_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

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
	
	
	public ArrayList<ArrayList<String>> getselectedcoursename(int courseid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "SELECT distinct cm2.course_name FROM public.edu_lms_exam_paper pl\n"
					+ "	inner join edu_lms_ele_course_mstr cl on cl.course_name=pl.course_id::text\n"
					+ " inner join edu_lms_course_mstr cm2 on cm2.id=cl.course_name::int \n" + "	where user_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, courseid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

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
	
	public ArrayList<ArrayList<String>> getselectedsetname(int setid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "SELECT distinct cl.setname FROM public.edu_lms_exam_paper pl\n"
					+ "	inner join edu_lms_set_mstr cl on cl.id=pl.set_id\n" + "	where user_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, setid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("setname"));// 0

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
	
	public ArrayList<ArrayList<String>> getSelectedCourseSetbyStudent(String userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select m.ele_course_id as course_id,cm2.course_name as course_name,c.set_id,sm.setname as set_name \n"
					+ "from edu_lms_system_ele_course_sets_link_parent m \n"
					+ "inner join edu_lms_system_ele_course_sets_link_child c on c.p_id=m.id \n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name::int = m.ele_course_id \n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
					+ "inner join edu_lms_set_mstr sm on sm.id = c.set_id \n" + "where m.user_id = ?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("course_id"));// 0
				alist.add(rs.getString("course_name"));// 1
				alist.add(rs.getString("set_id"));// 2
				alist.add(rs.getString("set_name"));// 3

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
	
	
	public ArrayList<ArrayList<String>> getInstLogo(int userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
//			q="select DISTINCT li.userid ,upload_image \n"
//					+ "from edu_lms_institute_reg ir\n"
//					+ "inner join logininformation li on li.institute_id = ir.id\n"
//					+ "WHERE li.userid = (SELECT institute_id from logininformation where userid = ? )";
			
			q="select DISTINCT li.userid ,upload_image \n"
					+ "from edu_lms_institute_reg ir\n"
					+ "inner join logininformation li on li.institute_id = ir.id\n"
					+ "WHERE li.userid =?";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, userid);
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("----->>>>>stmt "+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("upload_image"));// 0
			
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
		
		System.err.println("list---  dao--->   "+list);
		
		return list;
	}
	
	public ArrayList<ArrayList<String>> getStudentCourseListData(String userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select m.ele_course_id as course_id,cm2.course_name as course_name\n"
					+ "from edu_lms_system_ele_course_sets_link_parent m \n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.course_name = m.ele_course_id \n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n" + "where m.user_id = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("course_id"));// 0
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

		
		public ArrayList<ArrayList<String>> getModulelistFromcourse(String course_id,String user_id ) {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
				q="select distinct m.id, m.module_name\n"
						+ "from edu_lms_module_mstr m \n"
						+ "inner join edu_lms_student_course_enroll_sequence ces on ces.module_id=m.id\n"
						+ "inner join edu_lms_ele_course_mstr ecm on ecm.id = m.course_id\n"
						+ "inner join edu_lms_course_content cc on cc.course_name=ecm.course_name::int\n"
						+ "inner join edu_lms_course_mstr cm2 on cm2.id=ecm.course_name::int \n"
						+ " where cm2.id=? and ces.status=1 and  m.id not in (select distinct module_id from edu_lms_exam_paper where user_id=? and course_id=?) ";

				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1,Integer.parseInt(course_id));
				stmt.setInt(2, Integer.parseInt(user_id));
				stmt.setInt(3,Integer.parseInt(course_id));
			
				System.err.println("-----stmt "+stmt);

				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("module_name"));// 1
//					alist.add(rs.getString("course_name"));
					
					
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
		public ArrayList<ArrayList<String>> coursenamelist(String userid,String module_id) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
			q="select distinct cs.course_id, max(sequence) as sequence from edu_lms_student_course_enroll_sequence cs\n"
					+ "where user_id = ? and module_id=?\n"
					+ "group by cs.course_id";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1,Integer.parseInt(userid));
			stmt.setInt(2,Integer.parseInt(module_id));

			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt---->"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("course_id"));// 0
				alist.add(rs.getString("sequence"));// 1
				
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
		public ArrayList<ArrayList<String>> coursenamelistofResult(String userid) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
	/////////////////////------------------shivali change 16_02_2023 ---------------------/////////////////////
//				
//			q="select distinct cs.course_id, max(sequence) as sequence from edu_lms_student_course_enroll_sequence cs\n"
//					+ "where user_id = ? "
//					+ "and module_id=(select module_id from edu_lms_student_course_enroll_sequence where user_id=cs.user_id  and status =1 order by module_id desc limit 1)\n"
//					+ "group by cs.course_id";
			
			q="select distinct cs.course_id, max(sequence) as sequence from edu_lms_student_course_enroll_sequence cs\n"
			+ "where cs.user_id = ? group by cs.course_id";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1,Integer.parseInt(userid));
//			stmt.setInt(2,Integer.parseInt(course_id));

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("course_id"));// 0
				alist.add(rs.getString("sequence"));// 1
				
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
		
		//2ndmethodlistfor course--rndm
		@Override
		public ArrayList<String> method2(String userid, String course_id, String sequence) {
//			ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
			ArrayList<String> alist = new ArrayList<String>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
			q="select distinct cm2.id,upper(cm2.course_name) as course_name from edu_lms_student_course_enroll_sequence cs \n"
					+ "inner join edu_lms_ele_course_mstr cm on cm.id=cs.course_id \n"
					+ "inner join edu_lms_course_mstr cm2 on cm2.id=cm.course_name::int \n"
					+ "where user_id = ? and cs.course_id=? and cs.sequence=? \n"
					+ "and cs.status =1";
			
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1,Integer.parseInt(userid));
			stmt.setInt(2,Integer.parseInt(course_id));
			stmt.setInt(3,Integer.parseInt(sequence));
			System.err.println("-------stmt "+stmt);
			ResultSet rs = stmt.executeQuery();
		
				
			while (rs.next()) {
				
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("course_name"));// 1
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
		return alist;
	}

	@Override
	public ArrayList<String> getaayush_idby_uid(Integer userid) {
		Connection conn = null;
		String q = "";

		ArrayList<String> alist = new ArrayList<String>();

		try {
			conn = dataSource.getConnection();

			q = "select sd.ayush_id from edu_lms_student_details sd\n"
					+ "INNER JOIN logininformation li on li.email_id = sd.email\n" + "WHERE li.userid =?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				alist.add(rs.getString("ayush_id"));// 0

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
		// return null;
		System.err.println("alist" + alist);
		return alist;
	}



		@Override
		public ArrayList<ArrayList<String>> papersolution(String course_id, String module_id, String userid) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();

				q = "\n" + "select DISTINCT\n" + "ep.quiz_id,ep.answer_id,bc.correct_ans\n"
						+ "from edu_lms_exam_paper ep\n"
						+ "inner join edu_lms_question_band_child bc on bc.p_id = ep.quiz_id\n"
						+ "where ep.course_id=? and ep.module_id=? and ep.user_id=?";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(course_id));
				stmt.setInt(2, Integer.parseInt(module_id));
				stmt.setInt(3, Integer.parseInt(userid));

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();

					alist.add(rs.getString("quiz_id"));// 0
					alist.add(rs.getString("answer_id"));// 1
					alist.add(rs.getString("correct_ans"));// 2

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
		public ArrayList<ArrayList<String>> getquestion() {
			// TODO Auto-generated method stub
			return null;
		}

}
