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
public class NCH_list_of_Practical_DaoImpl implements NCH_list_of_Practical_Dao {
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTableNCH_PracticalDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id,String practical_id, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,practical_id);
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

			q = "select distinct ROW_NUMBER() OVER(order by s.system_name )  as ser,p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pr.practical from edu_cc_tb_nch_list_of_practical_parent p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pr on pr.id = p.practical_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int where p.status=0" + sd
					+ SearchValue + " order by p.id " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,practical_id);
			System.err.println("stmt----practical------01111111---------------------->  " + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction = 1;
			int countFunctionDelete = 1;
			int countview = 1;

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

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDpractical' value='ADD' title='Edit Data' >" // id='id_add_attHospital1'
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='practicalId" + countFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deletepractical' value='ADD' title='Delete Data' >" // id='id_add_attHospital1'
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='deleteID" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a></li>";

				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview VIEWdetails' value='ADD' title='View Data' >\n"
						+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId" + countview + "' value="
						+ rs.getString("id") + "></i></a> </li></ul>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;
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

	@Override
	public long DataTableNCH_PracticalDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id,String practical_id,  String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,practical_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,pr.practical from edu_cc_tb_nch_list_of_practical_parent p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pr on pr.id = p.practical_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int where p.status=0 " + sd
					+ SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,practical_id);
			System.err.println("stmt---practical--02222222222-------count---->    " + stmt);
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
			String professional_id, String course_id,String practical_id) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(s.system_name) like ?or upper(d.degree_name) like ? "
					+ "or upper(pm.professional) like ?  or upper(cc.course_name) like ? or upper(pr.practical) like ?)";
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

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id,String practical_id) {
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

			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (!practical_id.equals("0") && practical_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(practical_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	@Override
	public ArrayList<ArrayList<String>> getPopupNch_Practical_ChildDatalist(String hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select p.id, c.p_id,pm.practical,c.sub_practical,t.term,c.hours,c.demo_perform from edu_cc_tb_nch_list_of_practical_parent p\n"
					+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id = p.id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pm on pm.id = p.practical_id\n"
					+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id  where p.id = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			System.err.println("Child Table Data======29=====" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("practical"));// 0
				list.add(rs.getString("sub_practical"));// 0
				list.add(rs.getString("term"));// 1
				list.add(rs.getString("hours"));// 2
				list.add(rs.getString("demo_perform"));// 3

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

	public ArrayList<ArrayList<String>> GetPractical_Parent_Data(int id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select p.id,p.system_id,p.degree_id,p.professional_id,p.course_id,p.practical_id from edu_cc_tb_nch_list_of_practical_parent p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pr on pr.id = p.practical_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int where p.id = ? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("------stmttttttt"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("system_id")); // 1
				alist.add(rs.getString("degree_id")); // 2
				alist.add(rs.getString("professional_id")); // 3
				alist.add(rs.getString("course_id")); // 4
				alist.add(rs.getString("practical_id")); // 5
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

	public ArrayList<ArrayList<String>> getPractical_Child_By_id(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "select p.id,c.id as ch_id,c.sub_practical,c.term_id,c.hours,c.demo_perform from edu_cc_tb_nch_list_of_practical_parent p\n"
					+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id = p.id\n"
					+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id \n"
					+ "where p.id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			System.err.println("Child Table Data===========" + stmt);

			String lite_hdid = "";
			String add_more = "";
			String sub_practical = "";
			String term = "";
			String hours = "";
			String demo_perform = "";
			
			int i = 0;

			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				lite_hdid = "<input type='hidden' id = 'lite_hdid" + i + "' name='lite_hdid" + i + "' value = '"
						+ rs.getString("ch_id") + "' size='1'>"; // 0

				
				sub_practical = "<input type='text' id = 'sub_practical" + i + "'  name='sub_practical" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("sub_practical") + "' />";

				term = "<select id='term_id" + i + "' name='term_id" + i + "' class = 'form-control'></select>";

				hours = "<input type='text' id = 'hours" + i + "'  name='hours" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("hours") + "' />";
				
				demo_perform = "<input type='text' id = 'demo_perform" + i + "'  name='demo_perform" + i
						+ "'  class = 'form-control'  value = '" + rs.getString("demo_perform") + "' />";

				if (rs.isLast()) {
					add_more = "<a class='btn btn-success btn-sm' value = 'ADD' title = 'ADD' id = 'id_add_lite" + i
							+ "' onclick='formopen_att(" + i + ");'><i class='fa fa-plus'></i></a>";
				}

				list.add(rs.getString("ch_id")); // 0
				list.add(rs.getString("sub_practical"));// 1
				list.add(rs.getString("term_id"));// 2
				list.add(rs.getString("hours"));// 3
				list.add(rs.getString("demo_perform"));// 4

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
	  public ArrayList<ArrayList<String>> TableNCH_List_of_practicalDataTotalCount(String course_id) {
			ArrayList<ArrayList<String>> plist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

//				sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, p.practical,t.term\n"
//						+ "from edu_cc_tb_list_of_practical_child p \n"
//						+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
//						+ "where p.course_id=?";
				
				
				sql="select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,pm.practical,c.sub_practical,t.term,c.hours,c.demo_perform\n"
						+ "from edu_cc_tb_nch_list_of_practical_parent p\n"
						+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id = p.id\n"
						+ "inner join edu_cc_tb_nch_practical_mstr pm on pm.id = p.practical_id\n"
						+ "left join edu_cc_tb_i3_term_mstr t on t.id = c.term_id\n"
						+ " where p.course_id = ?  and c.status=0" ;
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----7-----***** \n"+stmt + "\n *****-----7-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("practical"));// 1
					list.add(rs.getString("sub_practical"));// 1
					list.add(rs.getString("term"));// 2
					list.add(rs.getString("hours"));// 3
					list.add(rs.getString("demo_perform"));// 4
					plist.add(list);
//					System.err.println("plist--------"+plist);
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
			return plist;
		}
	  public ArrayList<ArrayList<String>> Nch_practhours(String course_id) {
			ArrayList<ArrayList<String>> T5list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

						sql = "select no_of_hours \n"
						+ "from edu_cc_summary_teach_hrs_child c\n"
						+ "inner join edu_cc_summary_parent p on p.id=c.P_id\n"
						+ "where p.course_id=? and c.hours_type=3 and lecture_type=5";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----10-----***** \n"+stmt + "\n *****-----10-----***** \n");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("no_of_hours"));// 0
					T5list.add(list);
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
			return T5list;
		}
	  
}
