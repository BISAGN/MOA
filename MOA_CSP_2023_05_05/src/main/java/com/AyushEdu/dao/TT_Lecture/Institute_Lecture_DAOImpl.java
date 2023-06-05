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
public class Institute_Lecture_DAOImpl implements Institute_Lecture_DAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableLecturerInsNchDataTotalCount(String Search, String orderColunm, String orderType,String faculty,
			String professional, String course_name, String topic_id, String learning_outcome, String lecture_hours,
			String non_lecture_hours, String instructional_method, String academic_year, String fdate, String time,
			String time_rem, String activity_description, String resources, String assessment_method, String userId, int institute_id) {

//		System.err.println("userId ======== "+userId);
		String SearchValue = GenerateQueryWhereClause_SQL( Search,faculty, professional,course_name, topic_id, 
				learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
					+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
					+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
					+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
					+ "from edu_lec_plan_nch_temp lp\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
					+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
					+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
					+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
					+ "inner join logininformation li on li.userid = lp.faculty\n"
					+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 )ab";
			

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search,faculty, professional,course_name, topic_id, 
					learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, fdate, 
					time, time_rem, activity_description, resources, assessment_method, userId, institute_id);
			
			System.err.println("+++++++Ins++++++++"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,String faculty, String professional, 
			String course_name,String topic_id,String learning_outcome, String lecture_hours, String non_lecture_hours, 
			String instructional_method, String academic_year, String fdate, String time, String time_rem, 
			String activity_description, String resources, String assessment_method, String userId, int institute_id) {
			
		String SearchValue = "";
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( pm.professional like ? or c.course_name like ? or t.topic like ? "
					+ "or lo.learning_outcome like ? or upper(lecture_hours) like ? or upper(non_lecture_hours) like ? "
					+ "or im.instructional_method_name like ? or TO_CHAR(academic_year ,'yyyy') like ? or TO_CHAR(fdate , 'dd/MON/yyyy') like ? or upper(time) like ? "
					+ "or upper(time_rem) like ? or upper(activity_description) like ? \n"
					+ "or upper(resources) like ? or am.assessment_type like ? or upper(li.login_name) like ? ) ";
		}
		if (faculty != null && !faculty.equals("0")) {
			SearchValue += " and lp.faculty = ? ";
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

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String faculty,String professional, String course_name,String topic_id,
				String learning_outcome, String lecture_hours, String non_lecture_hours, String instructional_method,
				String academic_year, String fdate, String time, String time_rem, String activity_description, 
				String resources, String assessment_method, String userId, int institute_id) {
		int flag = 0;
		
		
		try {
			
			flag += 1;
			stmt.setInt(flag,(institute_id));
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if ( faculty != null && !faculty.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(faculty));
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
	public List<Map<String, Object>> DataTableLecturerInsNchDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String faculty, String professional, 
			String course_name,String topic_id,String learning_outcome, String lecture_hours, String non_lecture_hours, 
			String instructional_method, String academic_year, String fdate, String time, String time_rem, 
			String activity_description, String resources, String assessment_method, String userId, int institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL( Search,faculty,professional,course_name, topic_id, 
				learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);
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
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
						+ "from edu_lec_plan_nch_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join logininformation li on li.userid = lp.faculty\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 " + SearchValue
						+ " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			} else {
				
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.learning_outcome,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
						+ "from edu_lec_plan_nch_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic_id\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lo on lo.id = lp.learning_outcome\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join logininformation li on li.userid = lp.faculty\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 " 
						+ SearchValue + " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,faculty, professional,course_name, topic_id, 
					learning_outcome,  lecture_hours, non_lecture_hours, instructional_method, academic_year,
					fdate, time,time_rem, activity_description, resources, assessment_method, userId, institute_id);
			System.err.println("-----Ins--"+stmt);
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
//=========================================For Ncism Institute Faculty Report=============================
	@Override
	public List<Map<String, Object>> DataTableLecturerInsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String faculty, String professional, String course_name,
			String topic, String learning_objective, String lecture_hours, String non_lecture_hours,
			String instructional_method, String academic_year, String fdate, String time, String time_rem,
			String activity_description, String resources, String assessment_method, String userId, int institute_id) {
		// TODO Auto-generated method stub
		
		String SearchValue1 = GenerateQueryWhereClause_SQL1( search,faculty,professional,course_name, topic, 
				learning_objective,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);
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

			if (search.equals("") && course_name.equals("0") && topic.equals("0")
					&& academic_year.equals("0") && professional.equals("0") && learning_objective.equals("0") && instructional_method.equals("0") && lecture_hours.equals("0")
					&& non_lecture_hours.equals("0") && time.equals("0") && time_rem.equals("0") && fdate.equals("0") && activity_description.equals("0") 
					&& resources.equals("0") && assessment_method.equals("0")) {
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.b3_learning_obj as learning_objective,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
						+ "from edu_lec_plan_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic\n"
						+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = lp.learning_objective\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join logininformation li on li.userid = lp.faculty\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 " + SearchValue1
						+ " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			} else {
				
				q = "select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.b3_learning_obj as learning_objective,lp.lecture_hours,lp.non_lecture_hours,\n"
						+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
						+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
						+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
						+ "from edu_lec_plan_temp lp\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
						+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic\n"
						+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = lp.learning_objective\n"
						+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
						+ "inner join logininformation li on li.userid = lp.faculty\n"
						+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 " 
						+ SearchValue1 + " ORDER BY lp.id " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt, search,faculty, professional,course_name, topic, 
					learning_objective,  lecture_hours, non_lecture_hours, instructional_method, academic_year,
					fdate, time,time_rem, activity_description, resources, assessment_method, userId, institute_id);
			System.err.println("-----Ins--"+stmt);
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
	public long DataTableLecturerInsDataTotalCount(String search, String orderColunm, String orderType, String faculty,
			String professional, String course_name, String topic, String learning_objective, String lecture_hours,
			String non_lecture_hours, String instructional_method, String academic_year, String fdate, String time,
			String time_rem, String activity_description, String resources, String assessment_method, String userId,
			int institute_id) {
		// TODO Auto-generated method stub
		
		
//		System.err.println("userId ======== "+userId);
		String SearchValue1 = GenerateQueryWhereClause_SQL1( search,faculty, professional,course_name, topic, 
				learning_objective,  lecture_hours, non_lecture_hours, instructional_method, academic_year, 
				fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by lp.id ASC) as sr_no,lp.id, pm.professional, c.course_name, t.topic,lo.b3_learning_obj as learning_objective,lp.lecture_hours,lp.non_lecture_hours,\n"
					+ "im.instructional_method_name as instructional_method, TO_CHAR(lp.academic_year , 'yyyy') as academic_year, \n"
					+ "TO_CHAR(lp.fdate , 'dd/MON/YYYY') as fdate,TO_CHAR(lp.fdate , 'DD/MM/YYYY') as fd, lp.time,lp.time_rem, lp.activity_description,\n"
					+ "lp.resources, am.assessment_type as assessment_method, li.login_name as faculty\n"
					+ "from edu_lec_plan_temp lp\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = lp.professional\n"
					+ "inner join edu_lms_course_mstr c on c.id = lp.course_name\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = lp.topic\n"
					+ "inner join edu_cc_tb_t3_learning_object_child lo on lo.id = lp.learning_objective\n"
					+ "inner join edu_lec_instruction_method_mstr im on im.id = lp.instructional_method\n"
					+ "inner join logininformation li on li.userid = lp.faculty\n"
					+ "inner join edu_cc_tb_h3_assessment_type_mstr am on am.id = lp.assessment_method where li.institute_id= ? and lp.id != 0 )ab";
			

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL1(stmt, search,faculty, professional,course_name, topic, 
					learning_objective,  lecture_hours, non_lecture_hours, instructional_method, academic_year, fdate, 
					time, time_rem, activity_description, resources, assessment_method, userId, institute_id);
			
			System.err.println("+++++++Ins++++++++"+stmt);
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
	public String GenerateQueryWhereClause_SQL1(String search,String faculty, String professional, 
			String course_name,String topic,String learning_objective, String lecture_hours, String non_lecture_hours, 
			String instructional_method, String academic_year, String fdate, String time, String time_rem, 
			String activity_description, String resources, String assessment_method, String userId, int institute_id) {
			
		String SearchValue1 = "";
		
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue1 += " and ( pm.professional like ? or c.course_name like ? or t.topic like ? "
					+ "or lo.b3_learning_obj like ? or upper(lecture_hours) like ? or upper(non_lecture_hours) like ? "
					+ "or im.instructional_method_name like ? or TO_CHAR(academic_year ,'yyyy') like ? or TO_CHAR(fdate , 'dd/MON/yyyy') like ? or upper(time) like ? "
					+ "or upper(time_rem) like ? or upper(activity_description) like ? \n"
					+ "or upper(resources) like ? or am.assessment_type like ? or upper(li.login_name) like ? ) ";
		}
		if (faculty != null && !faculty.equals("0")) {
			SearchValue1 += " and lp.faculty = ? ";
		}
		
		if (professional != null && !professional.equals("0")) {
			SearchValue1 += " and lp.professional = ? ";
		}

		if (course_name != null && !course_name.equals("0")) {
			SearchValue1 += " and lp.course_name = ? ";
		}
		if (topic != null && !topic.equals("0") && !topic.equals("")) {
			SearchValue1 += " and lp.topic = ? ";
		}
		if (learning_objective != null && !learning_objective.equals("0")) {
			SearchValue1 += " and lp.learning_objective = ? ";
		}
		if (lecture_hours != null &&  !lecture_hours.equals("")){
			SearchValue1 += " and upper(lp.lecture_hours) like ? ";
		}
		if (non_lecture_hours != null  && !non_lecture_hours.equals("")) {
			SearchValue1 += " and upper(lp.non_lecture_hours) like ? ";
		}
		if (instructional_method != null && !instructional_method.equals("0") && !instructional_method.equals("")) {
			SearchValue1 += " and lp.instructional_method = ? ";
		}
		if (academic_year != null && !academic_year.equals("") && !academic_year.equals("YYYY")) {
			SearchValue1 +=  " and TO_CHAR(lp.academic_year , 'yyyy') = ? ";
	    }
		if( fdate != null && !fdate.equals("") && !fdate.equals("DD/MM/YYYY")) {
			SearchValue1 +=  "and TO_CHAR(lp.fdate , 'dd/mm/yyyy') = ? ";
	     }
		if (time != null  && !time.equals("")) {
			SearchValue1 += " and upper(lp.time) like ? ";
		}
		if (time_rem != null  && !time_rem.equals("")) {
			SearchValue1 += " and upper(lp.time_rem) like ? ";
		}
		if (activity_description != null && !activity_description.equals("")) {
			SearchValue1 += " and upper(lp.activity_description) like ? ";
		}
		if (resources != null && !resources.equals("")) {
			SearchValue1 += " and upper(lp.resources) like ? ";
		}
		if (assessment_method != null && !assessment_method.equals("0")&& !assessment_method.equals(""))  {
			SearchValue1 += " and lp.assessment_method = ? ";
		}
		
		return SearchValue1;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String search, String faculty,String professional, String course_name,String topic,
				String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method,
				String academic_year, String fdate, String time, String time_rem, String activity_description, 
				String resources, String assessment_method, String userId, int institute_id) {
		int flag = 0;
		
		
		try {
			
			flag += 1;
			stmt.setInt(flag,(institute_id));
			if (search != null && !search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
			}
			
			if ( faculty != null && !faculty.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(faculty));
			}
			
			if ( professional != null && !professional.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional));
			}
			if ( course_name != null && !course_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_name));
			}
			if ( topic != null && !topic.equals("0") && !topic.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(topic));
			}
			if ( learning_objective != null && !learning_objective.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(learning_objective));
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
}
