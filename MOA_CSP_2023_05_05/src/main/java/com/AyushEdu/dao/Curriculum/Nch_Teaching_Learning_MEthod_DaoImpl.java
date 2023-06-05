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
public class Nch_Teaching_Learning_MEthod_DaoImpl implements Nch_Teaching_Learning_Method_Dao {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableTeaching_Learning_MethodDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id,professional_id,course_id);
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

			q="select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name\n"
					+ "from edu_cc_tb_nch_teaching_learning_method_parent p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
					+ "where p.status=1"+ sd + SearchValue +"ORDER BY "+orderColunm+" "+ orderType
					+ " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id);
			System.err.println("stmt----Teaching_Learning_Method---------------------------->  "+stmt);
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
				 
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDTeaching_Learning_Method' value='ADD' title='Edit Data' >" //id='id_add_attHospital1'
					+"<i class='lni lni-pencil-alt'>"
					+ "<input type='hidden' id='Teaching_Learning_MethodId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				 
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteTeaching_Learning_Method' value='ADD' title='Delete Data' >" //id='id_add_attHospital1'
					+"<i class='lni lni-trash-can'>"
					+  "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"></i></a></li>";
				 
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm VIEWdetails' value='ADD' title='View Data' >\n"
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,String professional_id,String course_id) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ "or upper(pm.professional) like ?  or upper(cc.course_name) like ? )";
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
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id, String degree_id,String professional_id,String course_id) {
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	@Override
	public long DataTableTeaching_Learning_MethodDataTotalCount(String Search, String system_id, String degree_id,String professional_id,String course_id,String role) {
		
		String sd = "";
		
		if(role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if(role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id,professional_id,course_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
			q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name\n"
					+ "from edu_cc_tb_nch_teaching_learning_method_parent p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
					+ "where p.status=1 " + sd +SearchValue+ ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id,professional_id,course_id);
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
	
	public ArrayList<ArrayList<String>> getPopup_TeachingChildDatalist(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select ROW_NUMBER() OVER(order by c.teaching_learning_method asc) as sr_no, p.id,c.p_id,c.teaching_learning_method from edu_cc_tb_nch_teaching_learning_method_parent p \n"
					+ "inner join edu_cc_tb_nch_teaching_learning_method_child c on c.p_id = p.id\n"
					+ " where p.course_id = ? ";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("sr_no"));// 0
				list.add(rs.getString("teaching_learning_method"));// 1
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
	public ArrayList<ArrayList<String>> getPopup_TeachingChildDatalist1(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select ROW_NUMBER() OVER(order by c.teaching_learning_method asc) as sr_no, p.id,c.p_id,c.teaching_learning_method from edu_cc_tb_nch_teaching_learning_method_parent p \n"
					+ "inner join edu_cc_tb_nch_teaching_learning_method_child c on c.p_id = p.id "
					+ "where p.id= ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("sr_no"));// 0
				list.add(rs.getString("teaching_learning_method"));// 1
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
	public ArrayList<ArrayList<String>> GetTeaching_Learning_Method_ParentData(int id) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
					
						q = "select p.id,p.system_id,p.degree_id,p.professional_id,p.course_id from edu_cc_tb_nch_teaching_learning_method_parent p\n"
								+ "inner join edu_cc_tb_nch_teaching_learning_method_child c on c.p_id=p.id\n"
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
	public List<ArrayList<String>> getTeaching_Learning_Method_Child_By_id(int id) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql="select p.id,ch.id as ch_id,ch.teaching_learning_method from edu_cc_tb_nch_teaching_learning_method_parent p\n"
					+ "inner join edu_cc_tb_nch_teaching_learning_method_child ch on ch.p_id = p.id\n"
					+ "where p.id = ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data==========="+stmt);
			
			String lite_hdid = "";
			String add_more = "";
			String teaching_learning_method="";
			int i = 0;
			
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '" + rs.getString("ch_id")
				+ "' size='1'>"; // 0
				
				teaching_learning_method =  "<input type='text' id = 'teaching_learning_method" + i + "'  name='teaching_learning_method" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("teaching_learning_method")	+ "' />";
				
				
				 if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i +"' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}
				
				list.add(rs.getString("ch_id")); // 0
				list.add(rs.getString("teaching_learning_method"));//1
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


