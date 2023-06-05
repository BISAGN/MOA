package com.AyushEdu.dao.Curriculum;
import java.security.Principal;
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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD;

@Repository
public class Define_Topic_wise_Mark_Daoimpl implements Define_Topic_Wise_Mark_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTabledefine_topic_wise_marksDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id, String marks,String topic_name,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id, degree_id,professional_id,course_id,marks,topic_name);
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

//			if(Search.equals("") && subtopic_id.equals("0") &&  topic_id.equals("0") && status=="0") {
				q = "select distinct ROW_NUMBER() OVER(order by sm.system_name ) as ser, p.id,p.system_id,sm.system_name,d.degree_name,cm.course_name,p.marks,p.status,p.degree_id,p.professional_id,\n"
						+ "pf.professional ,p.course_id,string_agg(c.topic_id::character varying, ':') as topic_id,\n"
						+ "string_agg(t.topic, ':') as topic,string_agg(c.id::character varying, ' , ') as cids from edu_cc_tb_define_topic_wise_marks_parent p\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_child c on c.p_id = p.id\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id \n"
						+ "inner join edu_lms_course_mstr cm on cm.id = p.course_id\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = c.topic_id \n"
						+ "inner join edu_cc_tb_professional_mstr pf on pf.id=p.professional_id\r\n"
						+ "where p.status=0 and c.status=0" + sd  + SearchValue 
						+  "group by 2,3,4,5,6,7,8,9,10,11,12 " + "ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;;
//			}else {
//			q = "select p.id,d.degree_name,cm.course_name,p.marks,p.status,\n"
//					+ "string_agg(t.topic, ':') as topic from edu_cc_tb_define_topic_wise_marks_parent p\n"
//					+ "inner join edu_cc_tb_define_topic_wise_marks_child c on c.p_id = p.id\n"
//					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id \n"
//					+ "inner join edu_lms_course_mstr cm on cm.id = p.course_id\n"
//					+ "inner join edu_cc_tb_topics_mstr t on t.id = c.topic_id\n"
//					+ "where p.status='"+ status +"'"  + SearchValue 
//					+ "group by 1,2,3,4,5 ORDER BY p.id " + orderType + " limit "
//					+ pageL + " OFFSET " + startPage;
//			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id,degree_id,professional_id,course_id,marks,topic_name);
			
			System.err.println("---------stmt---------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String f = "";
				String action = "";
				String f1 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				String ids  =rs.getString("id");
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDefineTopic' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apId"+countFunction+"' value='"+ids+"'>"
								+"<input type='hidden' id='appsys"+countFunction+"' value='"+rs.getString("system_id")+"'>"
								+"<input type='hidden' id='apd"+countFunction+"' value='"+rs.getString("degree_id")+"'>"
										+"<input type='hidden' id='apf"+countFunction+"' value='"+rs.getString("professional_id")+"'>"
								+"<input type='hidden' id='apc"+countFunction+"' value='"+rs.getString("course_id")+"'>"
								+"<input type='hidden' id='aptm"+countFunction+"' value='"+rs.getString("marks")+"'>"
								+"<input type='hidden' id='apst"+countFunction+"' value='"+rs.getString("topic_id")+"'>"
								+"<input type='hidden' id='apcids"+countFunction+"' value='"+rs.getString("cids")+"'>"
										+ "</i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+ids+"><i class='lni lni-trash-can'></i></a> </li>";

				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
	public long DataTabledefine_topic_wise_marksDataTotalCount(String Search,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name,String role) {
		// TODO Auto-generated method stub
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,system_id,degree_id, professional_id,course_id,marks,topic_name);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q="select count(*) from (select p.id,p.system_id,sm.system_name,d.degree_name,cm.course_name,p.marks,p.status,p.degree_id,p.professional_id,\n"
					+ "pf.professional,p.course_id,string_agg(c.topic_id::character varying, ':') as topic_id,\n"
					+ "string_agg(t.topic, ':') as topic from edu_cc_tb_define_topic_wise_marks_parent p\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_child c on c.p_id = p.id\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id \n"
					+ "inner join edu_cc_tb_professional_mstr pf on pf.id=p.professional_id\r\n"
					+ "inner join edu_lms_course_mstr cm on cm.id = p.course_id\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = c.topic_id\n"
					+ "where p.status=0 and c.status=0  \n"
					+ sd + SearchValue + "\n"
					+"group by 1,2,3,4,5,6,7,8,9,10,11) ab  ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id,marks,topic_name);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and  (upper(sm.system_name) like ? or ( upper(d.degree_name) like ? or upper(cm.course_name) like ?"
					+ " or cast(p.marks as character varying) like ? or upper(topic) like ? ) )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		
		if (!system_id.trim().equals("0")) {
			SearchValue += " and p.system_id  = ? ";
		}
		if (!degree_id.trim().equals("0"))  {
			SearchValue += " and p.degree_id = ? ";
	     }
		if (!professional_id.trim().equals("0"))  {
			SearchValue += " and p.professional_id = ? ";
	     }
		if (!course_id.trim().equals("0"))  {
			SearchValue += " and p.course_id = ? ";
	     }
		if (!marks.trim().equals(""))  {
			SearchValue += " and p.marks = ? ";
	     }
		if (!topic_name.trim().equals(""))  {
			SearchValue += " and c.topic_id = ? ";
	     }
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String system_id,String degree_id,String professional_id,String course_id, String marks,String topic_name) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, Search+"%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(system_id));
			}
			if (! degree_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(professional_id));
			}
			System.err.println("topi222222222c---"+course_id);
			if (! course_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(course_id));
			}
			if (! marks.equals("") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(marks));
			}
			System.err.println("topic---"+topic_name);
			if (!topic_name.equals("")) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(topic_name));
			}
		} catch (Exception e) {
		}
		return stmt;
	}
	
	
	@Override
	public String updateTopicwiseMarksChild(String id,String topic_id) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update EDU_CC_TB_DEFINE_TOPIC_WISE_MARK_CHILD set topic_id=:topic_id"
					+ " where id=:id ";

			Query query = session1.createQuery(hql).setParameter("id", Integer.parseInt(id))
					.setParameter("topic_id", Integer.parseInt(topic_id.trim()));
			
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}
}