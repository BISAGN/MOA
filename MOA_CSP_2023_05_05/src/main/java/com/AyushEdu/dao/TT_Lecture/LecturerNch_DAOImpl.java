package com.AyushEdu.dao.TT_Lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.TT_Lecture.EDU_LEC_PLAN_NCH_TEMP;

@Repository
public class LecturerNch_DAOImpl implements LecturerNchDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
		
	@Override
	public long DataTableLecturerNchDataTotalCount(String Search, String orderColunm, String orderType,
			String professional, String course_name, String topic_id, String learning_outcome, String lecture_hours,
			String non_lecture_hours, String instructional_method, String academic_year, String fdate, String time,
			String time_rem, String activity_description, String resources, String assessment_method, String userId) {

//		System.err.println("userId ======== "+userId);
		String SearchValue = GenerateQueryWhereClause_SQL( Search, professional,course_name, topic_id, 
				learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
					+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
					+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
					+ "lp.resources, am.assessment_type as assessment_method\n"
					+ "from edu_lec_plan_nch_temp lp\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
					+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
					+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
					+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
					+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where lp.faculty= ? and lp.id != 0"
					+  SearchValue + " )ab";
			

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, professional,course_name, topic_id, 
					learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, fdate, 
					time, time_rem, activity_description, resources, assessment_method, userId);
			
			System.err.println("+++++++++++++++"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String professional, 
			String course_name,String topic_id,String learning_outcome, String lecture_hours, String non_lecture_hours, 
			String instructional_method, String academic_year, String fdate, String time, String time_rem, 
			String activity_description, String resources, String assessment_method, String userId) {
			
		String SearchValue = "";
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( pm.professional like ? or c.course_name like ? or t.topic like ? "
					+ "or lo.learning_outcome like ? or upper(lecture_hours) like ? or upper(non_lecture_hours) like ? "
					+ "or im.instructional_method_name like ? or TO_CHAR(academic_year ,'yyyy') like ? or TO_CHAR(fdate , 'dd/MON/yyyy') like ? or upper(time) like ? "
					+ "or upper(time_rem) like ? or upper(activity_description) like ? \n"
					+ "or upper(resources) like ? or am.assessment_type like ? ) ";
		}
		
		if (professional != null && !professional.equals("0")) {
			SearchValue += " and lp.professional = ? ";
		}

		if (course_name != null && !course_name.equals("0")) {
			SearchValue += " and lp.course_name = ? ";
		}
		if (topic_id != null && !topic_id.equals("0") && !topic_id.equals("")) {
			SearchValue += " and lp.topic_id = ? ";
		}
		if (learning_outcome != null && !learning_outcome.equals("0")) {
			SearchValue += " and lp.learning_outcome = ? ";
		}
		if (lecture_hours != null &&  !lecture_hours.equals("")){
			SearchValue += " and upper(lp.lecture_hours) like ? ";
		}
		if (non_lecture_hours != null  && !non_lecture_hours.equals("")) {
			SearchValue += " and upper(lp.non_lecture_hours) like ? ";
		}
		if (instructional_method != null && !instructional_method.equals("0") && !instructional_method.equals("")) {
			SearchValue += " and lp.instructional_method = ? ";
		}
		if (academic_year != null && !academic_year.equals("") && !academic_year.equals("YYYY")) {
			SearchValue +=  " and TO_CHAR(lp.academic_year , 'yyyy') = ? ";
	    }
		if( fdate != null && !fdate.equals("") && !fdate.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(lp.fdate , 'dd/mm/yyyy') = ? ";
	     }
		if (time != null  && !time.equals("")) {
			SearchValue += " and upper(lp.time) like ? ";
		}
		if (time_rem != null  && !time_rem.equals("")) {
			SearchValue += " and upper(lp.time_rem) like ? ";
		}
		if (activity_description != null && !activity_description.equals("")) {
			SearchValue += " and upper(lp.activity_description) like ? ";
		}
		if (resources != null && !resources.equals("")) {
			SearchValue += " and upper(lp.resources) like ? ";
		}
		if (assessment_method != null && !assessment_method.equals("0")&& !assessment_method.equals(""))  {
			SearchValue += " and lp.assessment_method = ? ";
		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String professional, String course_name,String topic_id,
				String learning_outcome, String lecture_hours, String non_lecture_hours, String instructional_method,
				String academic_year, String fdate, String time, String time_rem, String activity_description, 
				String resources, String assessment_method, String userId) {
		int flag = 0;
		
		
		try {
			
			flag += 1;
			stmt.setInt(flag,Integer.parseInt(userId));
			if (Search != null && !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
			}
			
			if ( professional != null && !professional.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional));
			}
			if ( course_name != null && !course_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name));
			}
			if ( topic_id != null && !topic_id.equals("0") && !topic_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(topic_id));
			}
			if ( learning_outcome != null && !learning_outcome.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(learning_outcome));
			}
			if (lecture_hours != null && !lecture_hours.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + lecture_hours.toUpperCase() + "%");
			}
			if (non_lecture_hours != null && !non_lecture_hours.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + non_lecture_hours.toUpperCase() + "%");
			}
			if ( instructional_method != null && !instructional_method.equals("0") && !instructional_method.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(instructional_method));
			}
			if (academic_year != null && !academic_year.equals("") && !academic_year.equals("YYYY")) {
				flag += 1;
				stmt.setString(flag, academic_year );
			}
			if (fdate != null && !fdate.equals("") && !fdate.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, fdate );
			}
			if (time != null && !time.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + time.toUpperCase() + "%");
			}
			if (time_rem != null && !time_rem.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + time_rem.toUpperCase() + "%");
			}
			if (activity_description != null && !activity_description.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + activity_description.toString().toUpperCase() + "%");
			}
			if (resources != null && !resources.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + resources.toString().toUpperCase() + "%");
			}
			if (assessment_method != null && !assessment_method.equals("0")&& !assessment_method.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(assessment_method));
			}
		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public List<Map<String, Object>> DataTableLecturerNchDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional, 
			String course_name,String topic_id,String learning_outcome, String lecture_hours, String non_lecture_hours, 
			String instructional_method, String academic_year, String fdate, String time, String time_rem, 
			String activity_description, String resources, String assessment_method, String userId) {

		String SearchValue = GenerateQueryWhereClause_SQL( Search,professional,course_name, topic_id, 
				learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId);
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

			if (Search.equals("") && course_name.equals("0") && topic_id.equals("0")
					&& academic_year.equals("0") && professional.equals("0") && learning_outcome.equals("0") && instructional_method.equals("0") && lecture_hours.equals("0")
					&& non_lecture_hours.equals("0") && time.equals("0") && time_rem.equals("0") && fdate.equals("0") && activity_description.equals("0") 
					&& resources.equals("0") && assessment_method.equals("0")) {
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,"
						+ "lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate ,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method\n"
						+ "from edu_lec_plan_nch_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where lp.faculty= ? and lp.id != 0 " + SearchValue
						+ " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			} else {
				
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,"
						+ "lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method\n"
						+ "from edu_lec_plan_nch_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where lp.faculty= ? and lp.id != 0  " 
						+ SearchValue + " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, professional,course_name, topic_id, 
					learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year,
					fdate, time,time_rem, activity_description, resources, assessment_method, userId);
			System.err.println("-------"+stmt);
			ResultSet rs = stmt.executeQuery();
         
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
				
				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ // id='id_add_attHospital1'
						"<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
				columns.put("action", action);

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
	public ArrayList<ArrayList<String>> getLecturerNchDao(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select p.theoretical_lecture as lecture_hours,p.pract_tutor_semi_clinic_post as non_lecture_hours from edu_cc_tb_nch_teaching_hours p\n"
					+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id \r\n"
					+ "where cm.id = ?";
				
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			
			System.err.println("lecture====="+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
//				list.add(rs.getString("id"));// 0
				list.add(rs.getString("lecture_hours"));//1
				list.add(rs.getString("non_lecture_hours"));//2
				
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
	public ArrayList<ArrayList<String>> gettime_duration(String fdate,String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select to_char(sum(end_time::time - start_time::time),'HH')  as total_hour FROM edu_tt_time_table_details t\n"
					+ "where faculty=? and to_char(ldate, 'DD/MM/YYYY') = ?\n";
					
					
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			stmt.setString(2, fdate);
			System.err.println("time_duration====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
//				list.add(rs.getString("id"));// 0
//				list.add(rs.getString("start_time"));//1
//				list.add(rs.getString("end_time"));//2
				list.add(rs.getString("total_hour"));//2
				
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
	public ArrayList<ArrayList<String>> getTopic_name(String course_id,String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		System.err.println("============"+course_id);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select tm.topic,tm.id from edu_cc_tb_topics_mstr tm\n"
					+ "where tm.course_id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(course_id));
			System.err.println("Topic====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("topic"));// 0
				
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
	public ArrayList<ArrayList<String>> getLearning_Objective(String topic_id, String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EDU_LEC_PLAN_NCH_TEMP getlectureByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 EDU_LEC_PLAN_NCH_TEMP updateid = (EDU_LEC_PLAN_NCH_TEMP) session.get(EDU_LEC_PLAN_NCH_TEMP.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	@Override	
	public ArrayList<ArrayList<String>> getPrev_Lec_HRDao(HttpSession session,String professional,String course_name,String topic_id,
			String learning_outcome) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String userid = session.getAttribute("userId").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select sum (time::int) as pre_lecHR from edu_lec_plan_nch_temp where professional=? and course_name=? and topic_id=? and learning_outcome=? and"
					+ " faculty=? ";
					
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(professional));
			stmt.setInt(2, Integer.parseInt(course_name));
			stmt.setInt(3, Integer.parseInt(topic_id));
			stmt.setInt(4, Integer.parseInt(learning_outcome));
			stmt.setInt(5, Integer.parseInt(userid));
			System.err.println("previous_lec==++++++++++++++====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("pre_lecHR"));// 0
//				list.add(rs.getString("lecture_hours"));//1
//				list.add(rs.getString("non_lecture_hours"));//2
				
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
	public ArrayList<ArrayList<String>> getCourse(String professional_id, String system_id, String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		System.err.println("============"+userid);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q=" select cm.id,cm.course_name from edu_tt_faculty_course_map c \n"
					+ "inner join logininformation li on li.userid::character varying  = c.faculty::character varying \n"
					+ "inner join edu_lms_course_mstr cm on cm.id= c.course\n"
					+ "where   li.userid = ? ";
			
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1,Integer.parseInt(userid));

			System.err.println("course====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("course_name"));// 0
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
	public ArrayList<ArrayList<String>> getFaculty(String institute_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		System.err.println("=====+++++++=+++++++====="+institute_id);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select f.id, f.username as faculty from edu_lms_faculty_nch f\n"
					+ " inner join edu_lms_institute_reg ir on ir.id= f.institute_id\n"
					+ " inner join logininformation li on li.login_name = f.username\n"
					+ " where f.institute_id  = ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(institute_id));
			
			System.err.println("faculty====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				
				list.add(rs.getString("id"));//0
				list.add(rs.getString("username"));// 1
				
				
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
	
	public ArrayList<ArrayList<String>> getlearningobj(String courseid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select c.course_name as course,li.login_name as faculty,cm.classroom,trim(To_char(ldate,'DAY')) as dayof from edu_tt_time_table_details ttd\n"
					+ "inner join logininformation li on li.userid = ttd.faculty\n"
					+ "inner join edu_lms_course_mstr c on c.id = ttd.course\n"
					+ "inner join edu_tt_classroom_mstr cm on cm.id::CHARACTER VARYING = ttd.classroom\n"
					+ "where ldate between '2022-09-01' and '2022-09-07'";
			
			stmt = conn.prepareStatement(q);
	
			System.err.println("learning====="+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("faculty"));// 0
				list.add(rs.getString("course"));//1
				list.add(rs.getString("classroom"));//2
				list.add(rs.getString("dayof"));//3
				
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

}
