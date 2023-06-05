package com.AyushEdu.dao.Part_One;

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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Clg_Reg_Add_Staff_Report_DAOImpl implements Clg_Reg_Add_Staff_Report_DAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public ArrayList<ArrayList<String>> DataTableSearch_College_Staff_ListDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no,
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role, String userid,String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, college_staff_list, first_name, date_of_appoinment, nature_of_appoinment,
				emp_id, emp_department, emp_qualification, aadhar_no, 
				guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment,
				guest_emp_id, guest_emp_department, guest_emp_qualification, guest_aadhar_no,
				non_first_name, non_date_of_appoinment, non_nature_of_appoinment,
				non_emp_id, non_emp_department, non_emp_qualification, non_aadhar_no,
				role, userid, institute_id);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";
		String q1 = "";
		System.err.println("college_staff_list================="+college_staff_list);
		try {
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
				q = "select distinct p.*,TO_CHAR(p.date_of_appoinment , 'DD-MM-YYYY') as date_of_appoinment\n"
						+ "from clg_reg_add_staff_teaching_faculty p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}
			
			if(college_staff_list.equals("2")) {
				q = "select distinct p2.*,TO_CHAR(p2.guest_date_of_appoinment , 'DD-MM-YYYY') as guest_date_of_appoinment\n"
						+ "from clg_reg_add_staff_guest_faculty p2\n"
						+ "inner join logininformation l on l.institute_id=p2.institute_id\n" 
						+ "where p2.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}
			
			if(college_staff_list.equals("3")) {
				q = "select distinct p3.*,TO_CHAR(p3.non_date_of_appoinment , 'DD-MM-YYYY') as non_date_of_appoinment\n"
						+ "from clg_reg_add_staff_non_teaching_staff p3\n"
						+ "inner join logininformation l on l.institute_id=p3.institute_id\n" 
						+ "where p3.id!=0 " + SearchValue + " order by id " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}



			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, college_staff_list, first_name, date_of_appoinment, nature_of_appoinment,
					emp_id, emp_department, emp_qualification, aadhar_no,
					guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment,
					guest_emp_id, guest_emp_department, guest_emp_qualification, guest_aadhar_no,
					non_first_name, non_date_of_appoinment, non_nature_of_appoinment,
					non_emp_id, non_emp_department, non_emp_qualification, non_aadhar_no,
					role, userid, institute_id);

			System.err.println("stmt===============list" + stmt);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int j = startPage;
			int countFunction = 1;

			while (rs.next()) {

				

				ArrayList<String> list = new ArrayList<String>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}

				String action = "";
				String f2 = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
						+ rs.getString("id") + "')}else{ return false;}\"";
				f2 ="<li><a class='main-btn dark-btn btn-hover btn-sm viewData' value='ADD'  title='View Data' >"+ //id='id_add_attHospital1'
								"<i class='lni lni-eye'></i></a> </li>"
								+ "<input type='hidden' id='viewId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				ul+= f2;
				 ul+="</ul>";
				 countFunction+=1;
				action = ul;
//				columns.put("action", action);
//			alist.add(rs.getString("id")); //0
				
				if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
				
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("first_name"));// 1
				list.add(rs.getString("date_of_appoinment"));// 2
				list.add(rs.getString("nature_of_appoinment"));// 3
				list.add(rs.getString("emp_id"));// 4
				list.add(rs.getString("emp_department"));// 5
				list.add(rs.getString("emp_qualification"));// 6
				list.add(rs.getString("aadhar_no"));// 7
				list.add(action);// 8
				
				alist.add(list);
				}
				
				if(college_staff_list.equals("2")) {
					
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("guest_first_name"));// 1
					list.add(rs.getString("guest_date_of_appoinment"));// 2
					list.add(rs.getString("guest_nature_of_appoinment"));// 3
					list.add(rs.getString("guest_emp_id"));// 4
					list.add(rs.getString("guest_emp_department"));// 5
					list.add(rs.getString("guest_emp_qualification"));// 6
					list.add(rs.getString("guest_aadhar_no"));// 7
					list.add(action);// 8
					
					alist.add(list);
					}
				
				if(college_staff_list.equals("3")) {
					
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("non_first_name"));// 1
					list.add(rs.getString("non_date_of_appoinment"));// 2
					list.add(rs.getString("non_nature_of_appoinment"));// 3
					list.add(rs.getString("non_emp_id"));// 4
					list.add(rs.getString("non_emp_department"));// 5
					list.add(rs.getString("non_emp_qualification"));// 6
					list.add(rs.getString("non_aadhar_no"));// 7
					list.add(action);// 8
					
					alist.add(list);
					}

//				list.add(columns);

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

	public long DataTableSearch_College_Staff_ListDataTotalCount(String Search, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no, 
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role,
			String userid, String institute_id) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, college_staff_list, first_name, date_of_appoinment, nature_of_appoinment,
				emp_id, emp_department, emp_qualification, aadhar_no,
				guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment,
				guest_emp_id, guest_emp_department, guest_emp_qualification, guest_aadhar_no,
				non_first_name, non_date_of_appoinment, non_nature_of_appoinment,
				non_emp_id, non_emp_department, non_emp_qualification, non_aadhar_no,
				role, userid, institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
				q = "select count(*) from (select p.*,TO_CHAR(p.date_of_appoinment , 'DD-MM-YYYY') as date_of_appoinment\n"
						+ "from clg_reg_add_staff_teaching_faculty p\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p.id!=0 and p.institute_id=" + institute_id
						+ " and l.userid= " + userid + "  " + SearchValue + ")ab";
			}
			if(college_staff_list.equals("2")) {
				q = "select count(*) from (select p2.*,TO_CHAR(p2.guest_date_of_appoinment , 'DD-MM-YYYY') as guest_date_of_appoinment\n"
						+ "from clg_reg_add_staff_guest_faculty p2\n"
						+ "inner join logininformation l on l.institute_id=p2.institute_id\n" 
						+ "where p2.id!=0 and p2.institute_id=" + institute_id
						+ " and l.userid= " + userid + "  " + SearchValue + ")ab";
			}
			if(college_staff_list.equals("3")) {
				q = "select count(*) from (select p3.*,TO_CHAR(p3.non_date_of_appoinment , 'DD-MM-YYYY') as non_date_of_appoinment\n"
						+ "from clg_reg_add_staff_teaching_faculty p3\n"
						+ "inner join logininformation l on l.institute_id=p.institute_id\n" 
						+ "where p3.id!=0 and p3.institute_id=" + institute_id
						+ " and l.userid= " + userid + "  " + SearchValue + ")ab";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, college_staff_list, first_name, date_of_appoinment, nature_of_appoinment,
					emp_id, emp_department, emp_qualification,aadhar_no,
					guest_first_name, guest_date_of_appoinment, guest_nature_of_appoinment,
					guest_emp_id, guest_emp_department, guest_emp_qualification, guest_aadhar_no,
					non_first_name, non_date_of_appoinment, non_nature_of_appoinment,
					non_emp_id, non_emp_department, non_emp_qualification, non_aadhar_no,
					role, userid, institute_id);
			System.err.println("stmt===============count" + stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no,
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role, String userid,
			String institute_id) {

		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
			SearchValue += " and upper(p.first_name) like ? or ( TO_CHAR(p.date_of_appoinment , 'DD-MM-YYYY') like ? or upper(p.nature_of_appoinment) like ?"
					+ "or upper(p.emp_id) like ? or upper(p.emp_department) like ? or upper(p.emp_qualification) like ? "
					+ " or upper(p.aadhar_no) like ?"
					+ ") ";
			}
			if(college_staff_list.equals("2")) {
				SearchValue += " and upper(p2.guest_first_name) like ? or ( TO_CHAR(p2.guest_date_of_appoinment , 'DD-MM-YYYY') like ? "
						+ "or upper(p2.guest_nature_of_appoinment) like ?"
						+ "or upper(p2.guest_emp_id) like ? or upper(p2.guest_emp_department) like ? or upper(p2.guest_emp_qualification) like ? "
						+ " or upper(p2.guest_aadhar_no) like ?"
						+ ") ";
			}
			if(college_staff_list.equals("3")) {
				SearchValue += " and upper(p3.non_first_name) like ? or ( TO_CHAR(p3.non_date_of_appoinment , 'DD-MM-YYYY') like ? "
						+ "or upper(p3.non_nature_of_appoinment) like ?"
						+ "or upper(p3.non_emp_id) like ? or upper(p3.non_emp_department) like ? or upper(p3.non_emp_qualification) like ? "
						+ " or upper(p3.non_aadhar_no) like ?"
						+ ") ";
			}
		}

		/// advance search

		if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
			if (!first_name.trim().equals("")) {
				SearchValue += " and p.first_name like ? "; // 1
			}
			if (!date_of_appoinment.equals("") && date_of_appoinment != "" && !date_of_appoinment.equals("DD/MM/YYYY")){
				SearchValue += " and to_char(p.date_of_appoinment,'DD/MM/YYYY')=?"; //2
			}
			if (!nature_of_appoinment.trim().equals("")) {
				SearchValue += " and p.nature_of_appoinment like ? "; // 3
			}
			if (!emp_id.trim().equals("")) {
				SearchValue += " and p.emp_id like ? "; // 4
			}
			if (!emp_department.trim().equals("")) {
				SearchValue += " and p.emp_department like ? "; // 5
			}
			if (!emp_qualification.trim().equals("")) {
				SearchValue += " and p.emp_qualification like ? "; // 6
			}
			if (!aadhar_no.trim().equals("")) {
				SearchValue += " and p.aadhar_no like ? "; // 7
			}
		}
		if(college_staff_list.equals("2")) {
			if (!guest_first_name.trim().equals("")) {
				SearchValue += " and p2.guest_first_name like ? "; // 1
			}
			if (!guest_date_of_appoinment.equals("") && guest_date_of_appoinment != "" && !guest_date_of_appoinment.equals("DD/MM/YYYY")){
				SearchValue += " and to_char(p2.guest_date_of_appoinment,'DD/MM/YYYY')=?"; //2
			}
			if (!guest_nature_of_appoinment.trim().equals("")) {
				SearchValue += " and p2.guest_nature_of_appoinment like ? "; // 3
			}
			if (!guest_emp_id.trim().equals("")) {
				SearchValue += " and p2.guest_emp_id like ? "; // 4
			}
			if (!guest_emp_department.trim().equals("")) {
				SearchValue += " and p2.guest_emp_department like ? "; // 5
			}
			if (!guest_emp_qualification.trim().equals("")) {
				SearchValue += " and p2.guest_emp_qualification like ? "; // 6
			}
			if (!guest_aadhar_no.trim().equals("")) {
				SearchValue += " and p2.guest_aadhar_no like ? "; // 7
			}
		}
		if(college_staff_list.equals("3")) {
			if (!non_first_name.trim().equals("")) {
				SearchValue += " and p3.non_first_name like ? "; // 1
			}
			if (!non_date_of_appoinment.equals("") && non_date_of_appoinment != "" && !non_date_of_appoinment.equals("DD/MM/YYYY")){
				SearchValue += " and to_char(p3.non_date_of_appoinment,'DD/MM/YYYY')=?"; //2
			}
			if (!non_nature_of_appoinment.trim().equals("")) {
				SearchValue += " and p3.non_nature_of_appoinment like ? "; // 3
			}
			if (!non_emp_id.trim().equals("")) {
				SearchValue += " and p3.non_emp_id like ? "; // 4
			}
			if (!non_emp_department.trim().equals("")) {
				SearchValue += " and p3.non_emp_department like ? "; // 5
			}
			if (!non_emp_qualification.trim().equals("")) {
				SearchValue += " and p3.non_emp_qualification like ? "; // 6
			}
			if (!non_aadhar_no.trim().equals("")) {
				SearchValue += " and p3.non_aadhar_no like ? "; // 7
			}
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String college_staff_list, String first_name, String date_of_appoinment,
			String nature_of_appoinment, String emp_id, String emp_department, String emp_qualification, String aadhar_no, 
			String guest_first_name, String guest_date_of_appoinment,
			String guest_nature_of_appoinment, String guest_emp_id, String guest_emp_department, String guest_emp_qualification, String guest_aadhar_no,
			String non_first_name, String non_date_of_appoinment,
			String non_nature_of_appoinment, String non_emp_id, String non_emp_department, String non_emp_qualification, String non_aadhar_no,
			String role,
			String userid, String institute_id) {

		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
				if(college_staff_list.equals("0") || college_staff_list.equals("1")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 6
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 7
				}
				if(college_staff_list.equals("2")) {
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 6
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 7

				}
				if(college_staff_list.equals("3")) {
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 1
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 2
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 3
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 4
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 5
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 6
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%"); // 7

				}
			}
			if(college_staff_list.equals("0") || college_staff_list.equals("1")) {
				if (!first_name.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + first_name + "%" ); // 1
				}
				if (!date_of_appoinment.equals("") && date_of_appoinment != "" && !date_of_appoinment.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_appoinment); //2
				}
				if (!nature_of_appoinment.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + nature_of_appoinment + "%" ); // 3
				}
				if (!emp_id.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + emp_id + "%" ); // 4
				}
				if (!emp_department.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + emp_department + "%" ); // 5
				}
				if (!emp_qualification.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + emp_qualification + "%" ); // 6
				}
				if (!aadhar_no.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + aadhar_no + "%" ); // 7
				}
			}
			if(college_staff_list.equals("2")) {
				if (!guest_first_name.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_first_name + "%" ); // 1
				}
				if (!guest_date_of_appoinment.equals("") && guest_date_of_appoinment != "" && !guest_date_of_appoinment.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, guest_date_of_appoinment); //2
				}
				if (!guest_nature_of_appoinment.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_nature_of_appoinment + "%" ); // 3
				}
				if (!guest_emp_id.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_emp_id + "%" ); // 4
				}
				if (!guest_emp_department.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_emp_department + "%" ); // 5
				}
				if (!guest_emp_qualification.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_emp_qualification + "%" ); // 6
				}
				if (!guest_aadhar_no.trim().equals("")) {
					flag += 1;
					stmt.setString(flag,"%" + guest_aadhar_no + "%" ); // 7
				}
			}
				if(college_staff_list.equals("3")) {
					if (!non_first_name.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + non_first_name + "%" ); // 1
					}
					if (!non_date_of_appoinment.equals("") && non_date_of_appoinment != "" && !non_date_of_appoinment.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, non_date_of_appoinment); //2
					}
					if (!non_nature_of_appoinment.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + non_nature_of_appoinment + "%" ); // 3
					}
					if (!non_emp_id.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + non_emp_id + "%" ); // 4
					}
					if (!non_emp_department.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + non_emp_department + "%" ); // 5
					}
					if (!non_emp_qualification.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + non_emp_qualification + "%" ); // 6
					}
					if (!guest_aadhar_no.trim().equals("")) {
						flag += 1;
						stmt.setString(flag,"%" + guest_aadhar_no + "%" ); // 7
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
}
