package com.AyushEdu.dao.Curriculum;
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

import com.AyushEdu.dao.Curriculum.CC_Nch_Topic_Learning_Outcome_Dao;


@Repository

public class CC_Nch_Topic_Learning_Outcome_DaoImpl implements CC_Nch_Topic_Learning_Outcome_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableLearning_OutcomeTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id, String status,
			String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				topic_id);

		int total = 0;
		String q = null;
		String sd = "";
		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			// Query for count page in data-table....by ruler
			// q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and
			// status='1' " + SearchValue;

				q = "select count(*) from (select distinct p.id,p.status,p.system_id,p.degree_id,\n"
						+ "p.professional_id,p.course_id,p.topic_id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.topic\n"
						+ "from edu_cc_tb_nch_topic_learning_outcome_parent p\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child c on c.p_id = p.id\n"
						+ "inner join edu_lms_system_mstr s on s.id= p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = p.topic_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n" + " where p.status='1' \n "
						+ sd + SearchValue + ") ab  ";
			

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, topic_id
					);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ? or upper(d.degree_name) like ? or upper(pm.professional) like ? "
					+ "or upper(cc.course_name) like ?  or upper(t.topic) like ?)";
			// System.err.println("globalllll search"+SearchValue);
		}

		/// advance search
		
		if (system_id != null && !system_id.equals("0")) {
			SearchValue += " and (p.system_id) = ? ";

		}
		if (degree_id != null && !degree_id.equals("0")) {
			SearchValue += " and (p.degree_id) = ? ";

		}
		if (professional_id != null && !professional_id.equals("0")) {
			SearchValue += " and (p.professional_id) = ? ";

		}
		if (course_id != null && !course_id.equals("0")) {
			SearchValue += " and (p.course_id) = ? ";

		}
		if (topic_id != null && !topic_id.equals("0")) {
			SearchValue += " and (p.topic_id) = ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id,String topic_id) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}

		

			if (system_id != null && !system_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (degree_id != null && !degree_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (professional_id != null && !professional_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (course_id != null && !course_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (topic_id != null && !topic_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(topic_id));
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableLearning_OutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String topic_id, String status, String role) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				topic_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

				q = "select distinct p.id,p.status,p.system_id,p.degree_id,\n"
						+ "p.professional_id,p.course_id,p.topic_id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.topic\n"
						+ "from edu_cc_tb_nch_topic_learning_outcome_parent p\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child c on c.p_id = p.id\n"
						+ "inner join edu_lms_system_mstr s on s.id= p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = p.topic_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n" + " where p.status='1' \n"
						+ " " + sd + SearchValue + " ORDER BY p.id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, topic_id
					);
			System.err.println("learning_outcome=====================>>>>>>>>" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;
			int countview=1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				String vd = "";
				 String ul="";
				 ul+="<ul class='buttons-group mainbtn action daobtn'>"; 
				 
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDTopic_Learning_Outcome' value='ADD' title='Edit Data' >" //id='id_add_attHospital1'
					+"<i class='lni lni-pencil-alt'>"
					+ "<input type='hidden' id='Topic_Learning_OutcomeId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				 
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteTeaching_Learning_Method' value='ADD' title='Delete Data' >" //id='id_add_attHospital1'
					+"<i class='lni lni-trash-can'>"
					+  "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a></li>";
				 
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm VIEWdetails' value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value="+rs.getString("id")+"></i></a> </li></ul>";

				 ul+=f + " " + f1;
				 
				 ul+="</ul>";

				action = ul;
				 columns.put("vd", vd);
				countFunction += 1;
				countFunctionDelete += 1;
				 countview += 1;
				
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
	public ArrayList<ArrayList<String>> GetTopic_Learning_Outcome_ParentData(int id) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
					
						q = "select p.id,p.system_id,p.degree_id,p.professional_id,p.course_id,p.topic_id from edu_cc_tb_nch_topic_learning_outcome_parent p\n"
								+ "inner join edu_cc_tb_nch_topic_learning_outcome_child c on c.p_id=p.id\n"
								+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
								+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
								+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
								+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
							+ " where p.id = ? ";
					
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			System.err.println("---GetTeaching_Learning_Method_ParentData-----------"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("system_id")); //1
				alist.add(rs.getString("degree_id")); //2
				alist.add(rs.getString("professional_id")); //3
				alist.add(rs.getString("course_id")); //4
				alist.add(rs.getString("topic_id")); //4
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
	@Override
	public List<ArrayList<String>> getTopic_Learning_Outcome_Child_By_id(int id) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql="select p.id,ch.id as ch_id,ch.learning_outcome from edu_cc_tb_nch_topic_learning_outcome_parent p\n"
					+ "inner join edu_cc_tb_nch_topic_learning_outcome_child ch on ch.p_id = p.id\n"
					+ "where p.id = ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data==========="+stmt);
			
			String lite_hdid = "";
			String add_more = "";
			String learning_outcome="";
			int i = 0;
			
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '" + rs.getString("ch_id")
				+ "' size='1'>"; // 0
				
				learning_outcome =  "<input type='text' id = 'learning_outcome" + i + "'  name='learning_outcome" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("learning_outcome")	+ "' />";
				
				
				 if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i +"' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}
				
				list.add(rs.getString("ch_id")); // 0
				list.add(rs.getString("learning_outcome"));//1
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
	public ArrayList<ArrayList<String>> getPopup_Topic_Learning_OutcomeDatalist1(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select ROW_NUMBER() OVER(order by c.learning_outcome asc) as sr_no, p.id,c.p_id,c.learning_outcome from edu_cc_tb_nch_topic_learning_outcome_parent p \n"
					+ "inner join edu_cc_tb_nch_topic_learning_outcome_child c on c.p_id = p.id "
					+ "where p.id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("sr_no"));// 0
				list.add(rs.getString("learning_outcome"));// 1
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
