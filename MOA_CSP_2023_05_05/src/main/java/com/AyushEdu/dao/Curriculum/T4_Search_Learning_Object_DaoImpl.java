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
public class T4_Search_Learning_Object_DaoImpl implements T4_Search_Learning_Object_Dao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableT4SearchLearningObjectDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String practical_id,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id,professional_id,course_id,practical_id);
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

			
			q=" select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pc.practical\n"
					+ "from edu_cc_tb_t4_learning_object_parent p\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_list_of_practical_child pc on pc.id=p.practical_id\n"
					+ "where p.status=0 "+ sd+  SearchValue + "ORDER BY "+orderColunm+" " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;


			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id,practical_id);
			System.err.println("stmt--T4 table--------->  "+stmt);
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
				 
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview VIEWdetails' value='ADD' title='View Data' >\n"
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,String professional_id,String course_id,String practical_id) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ " or upper(pm.professional) like ?  or upper(cc.course_name) like ?"
					+ " or upper(pc.practical) like ? )";
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
		if (!practical_id.equals("0")) {
			SearchValue += " and p.practical_id =? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id, String degree_id,String professional_id,String course_id,String practical_id) {
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
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
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
			if (!practical_id.equals("0") && practical_id != null) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(practical_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	@Override
	public long DataTableT4SearchLearningObjectDataTotalCount(String search, String system_id, String degree_id,
			String professional_id, String course_id, String practical_id,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, system_id, degree_id,professional_id,course_id,practical_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			q = "select count(*) from (select distinct p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pc.practical\n"
					+ "from edu_cc_tb_t4_learning_object_parent p\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_list_of_practical_child pc on pc.id=p.practical_id\n"
					+ "where p.status=0 " + sd +SearchValue+ ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, search, system_id, degree_id,professional_id,course_id,practical_id);
			System.err.println("stmt---Topic---------count---->    "+stmt);
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
	public ArrayList<ArrayList<String>> getPopup_Child_LearningDatalist(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select c.id\n"
					+ ",aco.course_outcome,c.b3_learning_obj,dm.domain,su.scope,ru.rep_und,tlm.method,am.assessment_method\n"
					+ ",lop.assessment_type\n"
					+ ",tm.term,c.j3_integration\n"
					+ "from edu_cc_tb_t4_learning_object_parent p\n"
					+ "inner join edu_cc_tb_t4_learning_object_child c on p.id=c.p_id\n"
					+ "inner join edu_cc_tb_add_course_outcome_mstr aco on aco.id=c.a3_couse_outcome\n"
					+ "inner join edu_cc_tb_c3_domain_mstr dm on dm.id = c.c3_domain_sub\n"
					+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id = c.d3_desirable_know\n"
					+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id = c.e3_level_show_know\n"
					+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id = c.f3_t_l_method\n"
					+ "inner join edu_cc_tb_g3_assessment_method_mstr am on am.id = c.g3_assessment\n"
					+ "inner join edu_cc_tb_h3_assessment_type_mstr lop on lop.id = c.h3_formative_summative\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id = c.i3_term\n"
					+ "where p.status=0 and p.id=? ";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("course_outcome"));// 0
				list.add(rs.getString("b3_learning_obj"));//1
				list.add(rs.getString("domain"));//2
				list.add(rs.getString("scope"));//3
				list.add(rs.getString("rep_und"));//4
				list.add(rs.getString("method"));//5
				list.add(rs.getString("assessment_method"));//6
				list.add(rs.getString("assessment_type"));//7
				list.add(rs.getString("term"));//8
				list.add(rs.getString("j3_integration"));//9
				
				alist.add(list);
				System.err.println("listtttttttttttttttt"+list);
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
					
			q=" select * from edu_cc_tb_t4_learning_object_parent\n"
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
				alist.add(rs.getString("practical_id")); //5
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

			sql="select * from edu_cc_tb_t4_learning_object_child\n"
					+ "where status=0 and p_id=? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data==========="+stmt);
			
			String lite_hdid = "";
			String add_more = "";
			String a3_couse_outcome="";
			String b3_learning_obj="";
			String c3_domain_sub = "";
			String d3_desirable_know = "";
			String e3_level_show_know = "";
			String f3_t_l_method = "";
			String g3_assessment = "";
			String h3_formative_summative = "";
			String i3_term = "";
			String j3_integration = "";
			int i = 0;
			
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '" + rs.getString("id")
				+ "' size='1'>"; // 0
				
				a3_couse_outcome = "<select id='a3_couse_outcome"+i+"' name='a3_couse_outcome"+i+"' class = 'form-control'></select>";

				b3_learning_obj =  "<input type='text' id = 'b3_learning_obj" + i + "'  name='b3_learning_obj" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("b3_learning_obj")	+ "' />";
				
				c3_domain_sub = "<select id='c3_domain_sub"+i+"' name='c3_domain_sub"+i+"' class = 'form-control'></select>";
				d3_desirable_know = "<select id='d3_desirable_know"+i+"' name='d3_desirable_know"+i+"' class = 'form-control'></select>";
				e3_level_show_know = "<select id='e3_level_show_know"+i+"' name='e3_level_show_know"+i+"' class = 'form-control'></select>";
				f3_t_l_method = "<select id='f3_t_l_method"+i+"' name='f3_t_l_method"+i+"' class = 'form-control'></select>";
				g3_assessment = "<select id='g3_assessment"+i+"' name='g3_assessment"+i+"' class = 'form-control'></select>";
				h3_formative_summative = "<select id='h3_formative_summative"+i+"' name='h3_formative_summative"+i+"' class = 'form-control'></select>";
				i3_term = "<select id='i3_term"+i+"' name='i3_term"+i+"' class = 'form-control'></select>";
				j3_integration =  "<input type='text' id = 'j3_integration" + i + "'  name='j3_integration" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("j3_integration")	+ "' />";
				
				
				 if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i +"' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}
				
				list.add(rs.getString("id")); // 0
				list.add(rs.getString("a3_couse_outcome"));//1
				list.add(rs.getString("b3_learning_obj"));//2
				list.add(rs.getString("c3_domain_sub"));//3
				list.add(rs.getString("d3_desirable_know"));//4
				list.add(rs.getString("e3_level_show_know"));//5
				list.add(rs.getString("f3_t_l_method"));//6
				list.add(rs.getString("g3_assessment"));//7
				list.add(rs.getString("h3_formative_summative"));//8
				list.add(rs.getString("i3_term"));//9
				list.add(rs.getString("j3_integration"));//10
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

}
