package com.AyushEdu.dao.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import com.AyushEdu.Models.Feedback.TB_FEEDBACK_RANDOM_DISPLAY;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;

@Repository
public class Feedback_dashboardImpl implements Feedback_dashboardDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListByInst(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			
			

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.category ,aa.sentiment as sentiment from (select a.sentiment_count as sentiment_count,a.category ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 1,2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "where institute_id = ? \n"
					+ "group by 2,3";

			PreparedStatement stmt = conn.prepareStatement(q);


			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.out.println("stmt-------" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = ====="+stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

					columns.put("age", rs.getString("category"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n));

				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
						columns.put("age", rs.getString("category"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
					else {
						
						columns.put("age", rs.getString("category"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")));

					}
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
		System.out.println("list-------" + list);
		return list;
	}

	@Override
	public void genrateScheduleRandomStudentsForFeedBack() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q2 = sessionHQL.createQuery("from EDU_LMS_INSTITUTE_REGISTRATION group by 1 order by id");
			@SuppressWarnings("unchecked")
			List<EDU_LMS_INSTITUTE_REGISTRATION> clist = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q2.list();

//				System.err.println("------------"+clist);
			sessionHQL.createSQLQuery("truncate table TB_FEEDBACK_RANDOM_DISPLAY").executeUpdate();

			for (int j = 0; j < clist.size(); j++) {
				q = "select a.userid from ((SELECT l.userid,institude_userid FROM edu_lms_student_details sd \n"
						+ "inner join logininformation l on l.aadhar_no=sd.aadhar_card )\n" + "Union all \n"
						+ "(SELECT p.userid,institude_userid FROM edu_lms_nch_student_details d\n"
						+ "inner join logininformation p on p.aadhar_no=d.aadhar_card )) a \n"
						+ "where institude_userid = ? "; // LIMIT 40 HAVE TO INCLUDE IF YOU HAVE TO SEND THIS TO SELECTED STUDENTS

				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("------------" + clist.get(j));

//			String temp = clist.get(j).getId() ;
				stmt.setInt(1, clist.get(j).getId());

//			System.err.println("stmt---MEMBER AUTO--->"+stmt);

				ResultSet rs = stmt.executeQuery();

				ResultSetMetaData metaData = rs.getMetaData();
				System.err.println("qry = ====="+stmt);

				int columnCount = metaData.getColumnCount();

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					list.add(columns);
				}
				Long c ;
				if (list.size() > 0) {
					for (int l = 0; l < list.size(); l++) {

						c = (Long) sessionHQL
								.createQuery("select count(id) from  TB_FEEDBACK_RANDOM_DISPLAY where user_id=:user_id")

								.setParameter("user_id", Integer.parseInt(list.get(l).get("userid").toString()))
								.uniqueResult();

						

						
						

						
						
						if(c == 0) {
							
							TB_FEEDBACK_RANDOM_DISPLAY tb = new TB_FEEDBACK_RANDOM_DISPLAY();
							tb.setUser_id(Integer.parseInt(list.get(l).get("userid").toString()));
							tb.setRandomize_date(new Date());
							tb.setCreated_by("SYSTEM");
							tb.setCreated_date(new Date());
							sessionHQL.save(tb);
							
						}
						
					}
				}

				rs.close();
				stmt.close();

			}
			tx.commit();
			sessionHQL.close();
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

	}
	
	
	
	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListByUni(int Uni_id,String inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			String S = "";
			if(!inst_id.equals("0") && !inst_id.equals("")) {
				S =  " and l.institute_id = ?";
				}

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.category ,aa.sentiment as sentiment from (select a.sentiment_count as sentiment_count,a.category ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 1,2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "where l.university_id= ? "+S+" \n"
					+ "group by 2,3 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			System.err.println("-------aaaaa-----------" + inst_id);
//
			stmt.setInt(1, Uni_id);
			
			if(!inst_id.equals("0") && !inst_id.equals("")) {
			stmt.setInt(2, Integer.parseInt(inst_id));
			}
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = ====="+stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}

				// end

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

					columns.put("age", rs.getString("category"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n) - (Integer.parseInt(count_n)*2));

//					temp += "{" + " 'year':  '" + rs.getString("category") + "'," + "  'positive': " + count_p + ","
//							+ " 'negative': " + count_n + "," + "}, ";
				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
						columns.put("age", rs.getString("category"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive':"
//								+ rs.getString("sentiment_count") + "," + " 'negative' : 0," + "}, ";
					else {
						
						columns.put("age", rs.getString("category"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")) - (Integer.parseInt(rs.getString("sentiment_count"))*2));

//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive': 0,"
//								+ " 'negative':" + rs.getString("sentiment_count") + "," + "}, ";
					}
				}

				list.add(columns);
			}
//			temp = temp.substring(0,temp.length()-2);
//
//			temp += "]";

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
//		System.out.println("list----UNI---" + list);
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListBySystem(String sys_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			String S = "";
			if(!sys_id.equals("0") && !sys_id.equals("")) {
				S =  " where r.system_id  = ?";
				}

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.category ,aa.sentiment as sentiment from (select a.sentiment_count as sentiment_count,a.category ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.category,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_category_mstr m on m.id = f.feedback_for\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 1,2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "  "+S+" \n"
					+ "group by 2,3 ";

			PreparedStatement stmt = conn.prepareStatement(q);

//
			
			if(!sys_id.equals("0") && !sys_id.equals("")) {
			stmt.setInt(1, Integer.parseInt(sys_id));
			}
			ResultSet rs = stmt.executeQuery();
System.err.println("qry = ====="+stmt);
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}

				// end

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

//					columns.put("age", rs.getString("subcategory"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n) - (Integer.parseInt(count_n)*2));

//					temp += "{" + " 'year':  '" + rs.getString("category") + "'," + "  'positive': " + count_p + ","
//							+ " 'negative': " + count_n + "," + "}, ";
				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
//						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive':"
//								+ rs.getString("sentiment_count") + "," + " 'negative' : 0," + "}, ";
					else {
						
//						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")) - (Integer.parseInt(rs.getString("sentiment_count"))*2));

//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive': 0,"
//								+ " 'negative':" + rs.getString("sentiment_count") + "," + "}, ";
					}
				}
				list.add(columns);
			}
//			temp = temp.substring(0,temp.length()-2);
//
//			temp += "]";

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
//		System.out.println("list----UNI---" + list);
		return list;
	}

	@Override
	public String getStudentDetailsByUserId(String user_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String ret = "";

		try {

			
			

			conn = dataSource.getConnection();

			q = "select count(*)::integer as exist from (select * from (select institude_userid from edu_lms_student_details\n"
					+ "UNION ALL\n"
					+ "select institude_userid from edu_lms_nch_student_details ) a group by 1 ) m  \n"
					+ "inner join logininformation l on l.institute_id = m.institude_userid\n"
					+ "where userid::character varying = ?";

			PreparedStatement stmt = conn.prepareStatement(q);


			stmt.setString(1,user_id);
			ResultSet rs = stmt.executeQuery();
			System.out.println("stmt-------" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				ret = rs.getString("exist");


				


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
		System.out.println("list-------" + list);
		return ret;
	}

	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListByInstSubCat(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			
			

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.subcategory ,aa.sentiment as sentiment from (select a.sentiment_count as sentiment_count,a.subcategory ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 1,2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "where institute_id = ? \n"
					+ "group by 2,3";

			PreparedStatement stmt = conn.prepareStatement(q);


			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.out.println("stmt-------" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = ====="+stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

					columns.put("age", rs.getString("subcategory"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n) - (Integer.parseInt(count_n)*2));

//					temp += "{" + " 'year':  '" + rs.getString("category") + "'," + "  'positive': " + count_p + ","
//							+ " 'negative': " + count_n + "," + "}, ";
				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive':"
//								+ rs.getString("sentiment_count") + "," + " 'negative' : 0," + "}, ";
					else {
						
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")) - (Integer.parseInt(rs.getString("sentiment_count"))*2));

//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive': 0,"
//								+ " 'negative':" + rs.getString("sentiment_count") + "," + "}, ";
					}
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
		System.out.println("list-------" + list);
		return list;
	}

	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListByUniSubCat(int Uni_id, String inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			String S = "";
			if(!inst_id.equals("0") && !inst_id.equals("")) {
				S =  " and l.institute_id = ?";
				}

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.subcategory ,aa.sentiment as sentiment from (select a.sentiment_count as sentiment_count,a.subcategory ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 1,2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "where l.university_id= ? "+S+" \n"
					+ "group by 2,3 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			System.err.println("-------aaaaa-----------" + inst_id);
//
			stmt.setInt(1, Uni_id);
			
			if(!inst_id.equals("0") && !inst_id.equals("")) {
			stmt.setInt(2, Integer.parseInt(inst_id));
			}
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			System.err.println("qry = ====="+stmt);

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}

				// end

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

					columns.put("age", rs.getString("subcategory"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n) - (Integer.parseInt(count_n)*2));

//					temp += "{" + " 'year':  '" + rs.getString("category") + "'," + "  'positive': " + count_p + ","
//							+ " 'negative': " + count_n + "," + "}, ";
				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive':"
//								+ rs.getString("sentiment_count") + "," + " 'negative' : 0," + "}, ";
					else {
						
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")) - (Integer.parseInt(rs.getString("sentiment_count"))*2));

//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive': 0,"
//								+ " 'negative':" + rs.getString("sentiment_count") + "," + "}, ";
					}
				}

				list.add(columns);
			}
//			temp = temp.substring(0,temp.length()-2);
//
//			temp += "]";

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
//		System.out.println("list----UNI---" + list);
		return list;
	}

	@Override
	public List<Map<String, Object>> FeedbackDashboardchartDataListBySystemSubCat(String sys_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String temp = "[";

		try {

			String S = "";
			if(!sys_id.equals("0") && !sys_id.equals("")) {
				S =  " where r.system_id  = ?";
				}

			conn = dataSource.getConnection();

			q = "select count(aa.sentiment_count) as sentiment_count,aa.subcategory ,aa.sentiment as sentiment from (select string_agg(a.sentiment_count,',') as sentiment_count,a.subcategory ,a.sentiment as sentiment,user_id  from(\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'P' as sentiment,f.user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment > 0 group by 2,4 \n"
					+ "UNION ALL\n"
					+ "select count(sentiment)::character varying as sentiment_count,m.subcategory,'N' as sentiment,user_id from tb_feedback_details f\n"
					+ "inner join tb_feedback_subcategory_mstr m on m.id = f.feedback_subcat\n"
					+ "where sentiment = 0 group by 2,4) a\n"
					+ "group by 2,3,4) aa \n"
					+ "inner join logininformation l on l.userid = aa.user_id\n"
					+ "inner join edu_lms_institute_reg r on r.id = l.institute_id\n"
					+ "  "+S+" \n"
					+ "group by 2,3";

			PreparedStatement stmt = conn.prepareStatement(q);

//
			
			if(!sys_id.equals("0") && !sys_id.equals("")) {
			stmt.setInt(1, Integer.parseInt(sys_id));
			}
			ResultSet rs = stmt.executeQuery();
System.err.println("qry = ====="+stmt);
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = 1;

			int countFunction = 1;
			int countFunctionDelete = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}

				// end

				if (rs.getString("sentiment_count").contains(",")) {
					String count_p = rs.getString("sentiment_count").split(",")[0];
					String count_n = rs.getString("sentiment_count").split(",")[1];

					columns.put("age", rs.getString("subcategory"));
					columns.put("positive", Integer.parseInt(count_p));
					columns.put("negative", Integer.parseInt(count_n) - (Integer.parseInt(count_n)*2));

//					temp += "{" + " 'year':  '" + rs.getString("category") + "'," + "  'positive': " + count_p + ","
//							+ " 'negative': " + count_n + "," + "}, ";
				} else {
					
					
					if (rs.getString("sentiment").trim().equals("P")) {
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", Integer.parseInt(rs.getString("sentiment_count")));
						columns.put("negative", 0);

					}
//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive':"
//								+ rs.getString("sentiment_count") + "," + " 'negative' : 0," + "}, ";
					else {
						
						columns.put("age", rs.getString("subcategory"));
						columns.put("positive", 0);
						columns.put("negative", Integer.parseInt(rs.getString("sentiment_count")) - (Integer.parseInt(rs.getString("sentiment_count"))*2));

//						temp += "{" + " 'year': '" + rs.getString("category") + "'," + "  'positive': 0,"
//								+ " 'negative':" + rs.getString("sentiment_count") + "," + "}, ";
					}
				}
				list.add(columns);
			}
//			temp = temp.substring(0,temp.length()-2);
//
//			temp += "]";

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
//		System.out.println("list----UNI---" + list);
		return list;
	}
	

}
