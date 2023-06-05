package com.AyushEdu.dao.Curriculum_Mstr;
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

import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;

@Repository
public class Link_Topic_to_SubTopic_MstrDaoImpl implements Link_Topic_to_SubTopic_MstrDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableTopic_SubTopicMStrDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String course_id,String topic_id, String subtopic_id, String status,String system_id ,String degree_id,
			String professional_id,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,course_id, topic_id,subtopic_id,system_id,degree_id, professional_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			 String sd = "";
				
				if(role.contains("NCISM")) {
					sd = " and l.system_id != 45 ";
				}
				if(role.contains("NCH")) {
					sd = " and l.system_id = 45 ";
				}
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if(Search.equals("") && course_id.equals("0") && subtopic_id.equals("0") &&  topic_id.equals("0") && status=="0") {
				q = "select distinct ROW_NUMBER() OVER(order by l.course_id ) as ser,string_agg(l.id::character varying, ':') as id, \n"
						+ "t.topic,l.course_id,l.topic_id, l.system_id,s.system_name, d.degree_name,pm.professional,l.degree_id,l.professional_id,\n"
						+ "string_agg(l.subtopic_id::character varying, ':') as sub_topicid ,\n"
						+ "string_agg(st.sub_topic, ':') as sub_topictxt,\n"
						+ "l.status,cm.course_name\n"
						+ "from edu_cc_tb_link_topic_to_subtopic_mstr l\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = l.topic_id\n"
						+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = l.subtopic_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id = l.course_id\n"
						+ "inner join edu_lms_system_mstr s on s.id= l.system_id \n"
						+ "inner join edu_lms_degree_mstr d on d.id = l.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = l.professional_id \n"
						+ "where l.status='1'"  + SearchValue + sd
						+  "group by 3,4,5,6,7,8,9,10,11,14,15" + "ORDER BY "+orderColunm+" " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
				q = "select distinct ROW_NUMBER() OVER(order by l.course_id ) as ser,string_agg(l.id::character varying, ':') as id, \n"
						+ "t.topic,l.course_id,l.topic_id, l.system_id,s.system_name, d.degree_name,pm.professional,l.degree_id,l.professional_id,\n"
						+ "string_agg(l.subtopic_id::character varying, ':') as sub_topicid ,\n"
						+ "string_agg(st.sub_topic, ':') as sub_topictxt,\n"
						+ "l.status,cm.course_name\n"
						+ "from edu_cc_tb_link_topic_to_subtopic_mstr l\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = l.topic_id\n"
						+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = l.subtopic_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id = l.course_id\n"
						+ "inner join edu_lms_system_mstr s on s.id= l.system_id \n"
						+ "inner join edu_lms_degree_mstr d on d.id = l.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = l.professional_id \n"
						+ "where l.status='"+ status +"'"  + SearchValue + sd
						+ "group by 3,4,5,6,7,8,9,10,11,14,15" + "ORDER BY "+orderColunm+" "  + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, course_id,topic_id,subtopic_id,system_id,degree_id, professional_id);
			
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
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDSubTopic' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apId"+countFunction+"' value="+ids+">"
								+"<input type='hidden' id='approsAGE"+countFunction+"' value='"+rs.getString("system_id")+"' >"
								+"<input type='hidden' id='hprodegree"+countFunction+"' value='"+rs.getString("degree_id")+"' >"
								+"<input type='hidden' id='hidprofessional"+countFunction+"' value='"+rs.getString("professional_id")+"' >"
								+"<input type='hidden' id='apco"+countFunction+"' value='"+rs.getString("course_id")+"'>"
								+"<input type='hidden' id='apt"+countFunction+"' value="+rs.getString("topic_id")+">"
								+"<input type='hidden' id='apst"+countFunction+"' value="+rs.getString("sub_topicid")+">"
								+"<input type='hidden' id='apstatus"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
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
	public long DataTableTopic_SubTopicMStrDataTotalCount(String Search,String course_id,String topic_id, String subtopic_id,String status,String system_id ,String degree_id,
			String professional_id,String role) {
		// TODO Auto-generated method stub
		String SearchValue = GenerateQueryWhereClause_SQL(Search,course_id, topic_id, subtopic_id,system_id,degree_id, professional_id);
		
		int total = 0;
		String q = null;
		String sd = "";
		if(role.contains("NCISM")) {
			sd = " and l.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and l.system_id = 45 ";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(Search.equals("") && course_id.equals("0") && status=="1") {
			q="select count(*) from (select distinct ROW_NUMBER() OVER(order by l.course_id ) as ser,string_agg(l.id::character varying, ':') as id, \n"
					+ "t.topic,l.course_id,l.topic_id, l.system_id,s.system_name, d.degree_name,pm.professional,l.degree_id,l.professional_id,\n"
					+ "string_agg(l.subtopic_id::character varying, ':') as sub_topicid ,\n"
					+ "string_agg(st.sub_topic, ':') as sub_topictxt,\n"
					+ "l.status,cm.course_name\n"
					+ "from edu_cc_tb_link_topic_to_subtopic_mstr l\n"
					+ "inner join edu_cc_tb_topics_mstr t on t.id = l.topic_id\n"
					+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = l.subtopic_id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id = l.course_id\n"
					+ "inner join edu_lms_system_mstr s on s.id= l.system_id \n"
					+ "inner join edu_lms_degree_mstr d on d.id = l.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = l.professional_id \n"
					+ "where l.status='1' \n" + sd
					+ SearchValue + "\n"
					+"group by 3,4,5,6,7,8,9,10,11,14,15) ab  ";
			}else {
				q="select count(*) from (select distinct ROW_NUMBER() OVER(order by l.course_id ) as ser,string_agg(l.id::character varying, ':') as id, \n"
						+ "t.topic,l.course_id,l.topic_id, l.system_id,s.system_name, d.degree_name,pm.professional,l.degree_id,l.professional_id,\n"
						+ "string_agg(l.subtopic_id::character varying, ':') as sub_topicid ,\n"
						+ "string_agg(st.sub_topic, ':') as sub_topictxt,\n"
						+ "l.status,cm.course_name\n"
						+ "from edu_cc_tb_link_topic_to_subtopic_mstr l\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id = l.topic_id\n"
						+ "inner join edu_cc_tb_sub_topics_mstr st on st.id = l.subtopic_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id = l.course_id\n"
						+ "inner join edu_lms_system_mstr s on s.id= l.system_id \n"
						+ "inner join edu_lms_degree_mstr d on d.id = l.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = l.professional_id \n"
						+ "where l.status='"+ status +"'\n" + sd
						+ SearchValue + "\n"
						+"group by 3,4,5,6,7,8,9,10,11,14,15) ab  ";
			}
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, course_id,topic_id,subtopic_id,system_id,degree_id, professional_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String course_id,String topic_id,String subtopic_id,String system_id,String degree_id,
			String professional_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += "  and ( upper(t.topic) like ? or upper(st.sub_topic) like ? or upper(cm.course_name) like ? or upper(s.system_name) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if (!course_id.trim().equals("0"))  {
			SearchValue += " and l.course_id = ? ";
	     }
		
		if (!topic_id.trim().equals("0"))  {
			SearchValue += " and l.topic_id = ? ";
	     }
		if (!subtopic_id.equals("0") && !subtopic_id.equals(""))  {
			SearchValue += " and l.subtopic_id = ? ";
	     }
		if( system_id != null && !system_id.equals("0")) {
			SearchValue += " and (l.system_id) = ? ";
	     }
		if( degree_id != null && !degree_id.equals("0")) {
			SearchValue += " and (l.degree_id) = ? ";
		
	     }
		if( professional_id != null && !professional_id.equals("0")) {
			SearchValue += " and (l.professional_id) = ? ";
		
	     }
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String course_id,String topic_id,String subtopic_id,String system_id,String degree_id,
			String professional_id) {
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
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!course_id.equals("0") && !course_id.equals("")) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(course_id));
			}
			if (! topic_id.equals("0") ) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(topic_id));
			}
			if (!subtopic_id.equals("0") && !subtopic_id.equals("")) {
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(subtopic_id));
			}
			if (system_id != null && !system_id.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (degree_id != null && !degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			if (professional_id != null && !professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional_id));
			}
		} catch (Exception e) {
		}
		return stmt;
	}
	
	@Override
	public String updateTopic_Subtopic(String st,String id,String topic_id) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR set subtopic_id=:sub_topic,status=:status"
					+ " where id=:id ";

			Query query = session1.createQuery(hql).setParameter("id", Integer.parseInt(id))
					.setParameter("sub_topic", Integer.parseInt(st))
//					.setParameter("topic_id", Integer.parseInt(topic_id))
					.setParameter("status", 1);
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
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
