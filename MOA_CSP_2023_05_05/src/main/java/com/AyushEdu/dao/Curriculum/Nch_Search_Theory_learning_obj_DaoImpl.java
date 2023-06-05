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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Nch_Search_Theory_learning_obj_DaoImpl implements Nch_Search_Theory_learning_obj_Dao {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableNCHSearchLearningObjectDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String topic_id,String learn_outme_id, String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id);
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

//			q=" select  c.id,s.system_name,d.degree_name,pm.professional,cc.course_name,tma.topic\n"
//					+ ",aco.course_outcome,c.b3_learning_obj,dm.domain,su.scope,ru.rep_und,tlm.method,am.assessment_method\n"
//					+ ",lop.assessment_type\n"
//					+ ",tm.term,c.j3_integration\n"
//					+ "from edu_cc_tb_t3_learning_object_child c\n"
//					+ "inner join edu_cc_tb_t3_learning_object_parent p on p.id=c.p_id\n"
//					+ "inner join edu_lms_ele_course_mstr ec on ec.id = p.course_id\n"
//					+ "inner join edu_lms_course_mstr cc on cc.id = ec.course_name::int\n"
//					+ "inner join edu_cc_tb_add_course_outcome_mstr aco on aco.id=c.a3_couse_outcome\n"
//					+ "inner join edu_cc_tb_c3_domain_mstr dm on dm.id = c.c3_domain_sub\n"
//					+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id = c.d3_desirable_know\n"
//					+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id = c.e3_level_show_know\n"
//					+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id = c.f3_t_l_method\n"
//					+ "inner join edu_cc_tb_g3_assessment_method_mstr am on am.id = c.g3_assessment\n"
//					+ "inner join edu_cc_tb_h3_assessment_type_mstr lop on lop.id = c.h3_formative_summative\n"
//					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id = c.i3_term\n"
//					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
//					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
//					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
//					+ "inner join edu_cc_tb_topics_mstr tma on tma.id = p.topic_id\n"
//					+ "where c.status=0 "+ SearchValue +" order by p.id " + orderType
//					+ " limit " + pageL + " OFFSET " + startPage;
			
			
			q="  select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,  p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,tma.topic\n" + 
					"from edu_cc_tb_nch_theory_learning_object_parent p\n" + 
					"inner join edu_lms_course_mstr cc on cc.id = p.course_id\n" + 
					"inner join edu_lms_system_mstr s on s.id = p.system_id\n" + 
					"inner join edu_lms_degree_mstr d on d.id = p.degree_id\n" + 
					"inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n" + 
					"inner join edu_cc_tb_topics_mstr tma on tma.id = p.topic_id\n" + 
					" \n" + 
					"where p.status=0 "+ sd + SearchValue +"ORDER BY "+orderColunm+" " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id);
			System.err.println("stmt----TABLE 3 SEARCH PAGE-->  "+stmt);
		//	stmt.setInt(1, Integer.parseInt(system_id));
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction=1;
			int countFunctionDelete=1;
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
				 
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDtopic' value='ADD' title='Edit Data' >" 
					+"<i class='lni lni-pencil-alt'>"
					+ "<input type='hidden' id='topicId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
//				 f = "<li><a class='main-btn dark-btn-outline btn-hover btn-sm VIEWdetails' value='ADD' title='View Data' >\n"
//							+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value="+rs.getString("id")+"></i></a> </li>";
				 
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteTopic' value='ADD' title='Delete Data' >" 
					+"<i class='lni lni-trash-can'>"
					+  "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a></li>";
				 
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value="+rs.getString("id")+"></i></a> </li></ul>";

				 ul+=f + " " + f1;
				 
				 ul+="</ul>";
		
				 countFunction+=1;
				 countFunctionDelete+=1;
				 countview += 1;
					
				 action = ul;
				 columns.put("vd", vd);
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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,String professional_id,String course_id,String topic_id,String learn_outme_id) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ " or upper(pm.professional) like ?  or upper(cc.course_name) like ?"
					+ " or upper(tma.topic) like ?  )";
			//or  cast(num_questions as text) like ?
		}
		/// advance search
		if (!system_id.equals("0")) {
			SearchValue += " and p.system_id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and p.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and p.course_id =? ";
		}
		if (!topic_id.equals("0")) {
			SearchValue += " and p.topic_id =? ";
		}
//		if (!learn_outme_id.equals("0")) {
//			SearchValue += " and p.learn_outme_id =? ";
//		}
		//if (num_questions != null && !num_questions.equals("")) {
		//SearchValue += " and cast (num_questions as text) like ? ";

	//}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id, String degree_id,String professional_id,String course_id,String topic_id,String learn_outme_id) {
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(course_id));
			}
			if (!topic_id.equals("0") && topic_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(topic_id));
			}
//			if (!learn_outme_id.equals("0") && learn_outme_id != null) {
//				flag += 1;
//				stmt.setInt(flag,Integer.parseInt(learn_outme_id));
//			}
			//if (num_questions != null && !num_questions.equals("")) {
				//flag += 1;
				//stmt.setString(flag, "%" + Integer.parseInt(num_questions) + "%");
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public long DataTableNCHSearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String topic_id, String learn_outme_id,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			q = "select count(*) from ( select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,  p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,tma.topic\n" + 
					"from edu_cc_tb_nch_theory_learning_object_parent p\n" + 
					"inner join edu_lms_course_mstr cc on cc.id = p.course_id\n" + 
					"inner join edu_lms_system_mstr s on s.id = p.system_id\n" + 
					"inner join edu_lms_degree_mstr d on d.id = p.degree_id\n" + 
					"inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n" + 
					"inner join edu_cc_tb_topics_mstr tma on tma.id = p.topic_id\n" + 
					" \n" + 
					"where p.status=0   " + sd +SearchValue+ ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id);
			System.err.println("stmt---Table 3---------count---->    "+stmt);
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

	@Override
	public ArrayList<ArrayList<String>> getPopup_LearningDatalist(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select c.id,c.generic_competency, c.subject_area,r.rep_und,c.specific_competency,c.slo_outcome,\n"
					+ "d.domain,g.guilberts_level,s.scope,m.method,f.formative,f.formative,c.int_departments\n"
					+ "from edu_cc_tb_nch_theory_learning_object_parent p\n"
					+ "inner join edu_cc_tb_nch_theory_learning_object_child c on p.id=c.p_id\n"
					+ "inner join edu_cc_tb_e3_representation_understanding_mstr r on r.id = c.millers_level\n"
					+ "inner join edu_cc_tb_c3_domain_mstr d on d.id = c.blooms_domain\n"
					+ "inner join edu_cc_tb_guilberts_level_mstr g on g.id = c.guilberts_level\n"
					+ "inner join edu_cc_tb_d3_scope_understanding_mstr s on s.id = c.mk_dk\n"
					+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr m on m.id = c.tl_methods\n"
					+ "inner join edu_cc_tb_nch_formative_and_summative_mstr f on f.id = c.form_assessment\n"
					+ "where p.id=? and p.status=0";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id")); // 0
//				list.add(rs.getString("topic"));//0
				list.add(rs.getString("generic_competency"));//1
				list.add(rs.getString("subject_area"));//2
				list.add(rs.getString("rep_und"));//3
				list.add(rs.getString("specific_competency"));//4
				list.add(rs.getString("slo_outcome"));//5
				list.add(rs.getString("domain"));//6
				list.add(rs.getString("guilberts_level"));//7
				list.add(rs.getString("scope"));//8
				list.add(rs.getString("method"));//9
				list.add(rs.getString("formative"));//10
				list.add(rs.getString("formative"));//11
				list.add(rs.getString("int_departments"));//12
				alist.add(list);
				
				System.err.println("listtttttttttt------------------------tttttt"+list);
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
	
	public ArrayList<ArrayList<String>> GetLearning_Parent_Data(int id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
					
			q=" select * from edu_cc_tb_nch_theory_learning_object_parent\n"
					+ "where status=0 and id=? ";
					
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			System.err.println("STMT---EDIT---PARENT---"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); //0
				alist.add(rs.getString("system_id")); //1
				alist.add(rs.getString("degree_id")); //2
				alist.add(rs.getString("professional_id")); //3
				alist.add(rs.getString("course_id")); //4
				alist.add(rs.getString("topic_id")); //5
				alist.add(rs.getString("learn_outme_id")); //6
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
	
	public ArrayList<ArrayList<String>> getLearning_Child_By_id(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql="select * from edu_cc_tb_nch_theory_learning_object_child\n"
					+ "where status=0 and p_id=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data==========="+stmt);
			
			String lite_hdid = "";
			String add_more = "";
			String generic_competency="";
			String subject_area="";
			String millers_level = "";
			String specific_competency = "";
			String slo_outcome = "";
			String blooms_domain = "";
			String guilberts_level = "";
			String mk_dk = "";
			String tl_methods = "";
			String form_assessment = "";
			String summ_assessment = "";
			String int_departments = "";
			
			int i = 0;
			
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '" + rs.getString("id")
				+ "' size='1'>"; // 0
				
				generic_competency = "<input type='text'  id='generic_competency" + i + "' name='generic_competency"+i+"' "
						+ "class = 'form-control' value = '" + rs.getString("generic_competency") + "'/>";

				subject_area =  "<input type='text' id = 'subject_area" + i + "'  name='subject_area" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("subject_area")	+ "' />";
				
				millers_level = "<select id='millers_level"+i+"' name='millers_level"+i+"' class = 'form-control'></select>";
				specific_competency = "<input type='text' id='specific_competency"+i+"' name='specific_competency"+i+"'"
						+ " class = 'form-control'  value = '" + rs.getString("specific_competency")+ "'/>";
				slo_outcome = "<input type='text' id='slo_outcome"+i+"' name='slo_outcome"+i+"' class = 'form-control'"
						+ "class = 'form-control'  value = '" + rs.getString("slo_outcome")+ "'/>";
				blooms_domain = "<select id='blooms_domain"+i+"' name='blooms_domain"+i+"' class = 'form-control'></select>";
				guilberts_level = "<select id='guilberts_level"+i+"' name='guilberts_level"+i+"' class = 'form-control'></select>";
				mk_dk = "<select id='mk_dk"+i+"' name='mk_dk"+i+"' class = 'form-control'></select>";
				tl_methods = "<select id='tl_methods"+i+"' name='tl_methods"+i+"' class = 'form-control'></select>";
				form_assessment = "<select id='form_assessment"+i+"' name='form_assessment"+i+"' class = 'form-control'></select>";
				summ_assessment = "<select id='summ_assessment"+i+"' name='summ_assessment"+i+"' class = 'form-control'></select>";
				int_departments =  "<input type='text' id = 'int_departments" + i + "'  name='int_departments" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("int_departments")	+ "' />";
				
				
				 if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i +"' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}
				
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("generic_competency"));//1
				list.add(rs.getString("subject_area"));//2
				list.add(rs.getString("millers_level"));//3
				list.add(rs.getString("specific_competency"));//4
				list.add(rs.getString("slo_outcome"));//5
				list.add(rs.getString("blooms_domain"));//6
				list.add(rs.getString("guilberts_level"));//7
				list.add(rs.getString("mk_dk"));//8
				list.add(rs.getString("tl_methods"));//9
				list.add(rs.getString("form_assessment"));//10
				list.add(rs.getString("summ_assessment"));//11
				list.add(rs.getString("int_departments"));//12
				alist.add(list);
				System.err.println("lllllllllllllllllllddddddddddddd"+alist);
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

	public ArrayList<ArrayList<String>> NCH_theoryLearning_Objectives(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			
			sql = "select c.id,tm.topic, c.generic_competency, c.subject_area,r.rep_und,c.specific_competency,c.slo_outcome,\n"
					+ "d.domain,g.guilberts_level,s.scope,m.method,f.formative,f.formative,c.int_departments,l.learning_outcome\n"
					+ "from edu_cc_tb_nch_theory_learning_object_parent p\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=p.topic_id\n"
					+ "inner join edu_cc_tb_nch_theory_learning_object_child c on p.id=c.p_id\n"
					+ "inner join edu_cc_tb_e3_representation_understanding_mstr r on r.id = c.millers_level\n"
					+ "inner join edu_cc_tb_c3_domain_mstr d on d.id = c.blooms_domain\n"
					+ "inner join edu_cc_tb_guilberts_level_mstr g on g.id = c.guilberts_level\n"
					+ "inner join edu_cc_tb_d3_scope_understanding_mstr s on s.id = c.mk_dk\n"
					+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr m on m.id = c.tl_methods\n"
					+ "inner join edu_cc_tb_nch_formative_and_summative_mstr f on f.id = c.form_assessment\n"
					+ "inner join edu_cc_tb_nch_learning_outcome l on l.id = p.id\n"
					+ "where p.course_id=? and p.status=0 \n";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("\n *****-----6-----***** \n"+stmt + "\n *****-----6-----***** \n");
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
//				list.add(rs.getString("id")); // 0
//				list.add(rs.getString("sr_no"));// 0
				list.add(rs.getString("topic"));//1
				list.add(rs.getString("learning_outcome"));//2
				list.add(rs.getString("generic_competency"));//2
				list.add(rs.getString("subject_area"));//3
				list.add(rs.getString("rep_und"));//4
				list.add(rs.getString("specific_competency"));//5
				list.add(rs.getString("slo_outcome"));//6
				list.add(rs.getString("domain"));//7
				list.add(rs.getString("guilberts_level"));//8
				list.add(rs.getString("scope"));//9
				list.add(rs.getString("method"));//10
				list.add(rs.getString("formative"));//11
				list.add(rs.getString("formative"));//12
				list.add(rs.getString("int_departments"));//13
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
