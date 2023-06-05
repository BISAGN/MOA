package com.AyushEdu.dao.Policy_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Track_Policy_StatusDAOImpl implements Track_Policy_StatusDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	

@Override
	public ArrayList<ArrayList<String>> DataTableTrackPolicyStatusDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String policy_number,String category, String sub_category,String policy_type,
String globSearch,HttpSession session) {
		
		String  role = session.getAttribute("role").toString();
		String ls="";

		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search, policy_number, category,sub_category,policy_type,globSearch,role);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		

		try {
			conn = dataSource.getConnection();

			q = "SELECT ROW_NUMBER() OVER(order by b.id) as sr_no,b.id, b.policy_title,TO_CHAR(b.initial_date , 'DD-MON-YYYY') as initial_date,"
					+ "b.forward_status as level,b.policy_no, td.label as  policy_type, pc.policycategory, psc.subcategory, pd.version ,b.level_one_status, b.level_two_status, b.level_three_status, b.level_four_status,b.final_status \n"
					+ "from tb_policy_initial_draft b\n" 
					+"INNER join tb_policy_document pd on pd.p_id = b.id\n" 
					+ " INNER join edu_ply_mst_policycategory pc on pc.id = b.policy_category\n"
					+ " INNER join edu_ply_mst_subpolicycategory psc on psc.id = b.policy_sub_category\n"
					+ " INNER join t_domain_value td ON td.codevalue  = b.policy_type and domainid='POLICY_TYPE'\n"
					+"where b.id!=0" 
					+ SearchValue + "  ORDER BY " + orderColunm + " " + orderType + "  limit     " + pageL + "  OFFSET  " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,policy_number,category,sub_category, policy_type,globSearch,role);
			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";


				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Policy Draft ?') )"
						+ "{getDownloadPdfTrackPolicy('" + rs.getInt("id") + "')}else{ return false;}\"";
				f1 = "<i class='fa fa-download' " + Download + " title='Downlaod' style='width: 100%; text-align: -webkit-center;'></i>";


				action =  f1 ;
				
				
				String version = "V " + rs.getString("version");
				
				String Status="";
				if(rs.getInt("final_status") == 1) {
					 Status = "IN PROGRESS ";
				}else if (rs.getInt("final_status") == 2) {
					 Status = "APPROVED ";
				}else if(rs.getInt("final_status") == 3 || rs.getInt("final_status") == 4){
					 Status = "REJECTED ";
				}
				
				String level="";
				if(rs.getString("level_one_status").equals("0")) {
					level = "MOA ENTITY";
				}else if(rs.getString("level_two_status").equals("0")) {
					level = "EXECUTIVE COMMITTEE";
				}else if(rs.getString("level_three_status").equals("0")) {
					level = "INDUSTRY EXPERTS";
				}else if(rs.getString("level_four_status").equals("0")) {
					level = "MOA";
				}else if(rs.getString("level_four_status").equals("1")) {
					level = "PUBLISHED";
				}
				
				alist.add(rs.getString("sr_no"));
				alist.add(rs.getString("policycategory"));
				alist.add(rs.getString("subcategory"));
				alist.add(rs.getString("policy_type"));
				alist.add(rs.getString("policy_title"));
				alist.add(rs.getString("initial_date"));
				alist.add(rs.getString("policy_no"));
				
				alist.add(version);
				alist.add(level);
				alist.add(Status);
				alist.add(action);
				i++;
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public long DataTableTrackPolicyStatusTotalCount(String Search, String policy_number,String category, String sub_category,String policy_type,String globSearch,HttpSession session) {
		String  role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, policy_number,category,sub_category, policy_type,globSearch ,role);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = " select count(*) from (SELECT ROW_NUMBER() OVER(order by b.id) as sr_no,b.id, b.policy_title,TO_CHAR(b.initial_date , 'DD-MON-YYYY') as initial_date,"
					+ "b.forward_status as level,td.label as  policy_type,b.policy_no, pd.version,b.final_status from tb_policy_initial_draft b"
					+ " INNER join tb_policy_document  pd on pd.p_id = b.id"
					+ " INNER join edu_ply_mst_policycategory pc on pc.id = b.policy_category"
					+ " INNER join edu_ply_mst_subpolicycategory psc on psc.id = b.policy_sub_category"
					+ " INNER join t_domain_value td ON td.codevalue  = b.policy_type and domainid='POLICY_TYPE'\n"
					+ " where b.id!=0 " + SearchValue + ") ab";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search, policy_number,category,sub_category, policy_type,globSearch,role);
		
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
					e.printStackTrace();
				}
			}
		}
		return (long) total;

	}

	public String GenerateQueryWhereCandiList(String Search,String policy_number,String category,String sub_category,String policy_type,String globSearch,String role) {
		String SearchValue = "";
		
		if (!category.equals("0") && category != "" && category != null) {
			
			SearchValue += " and policy_category::int = ? ";
		}

			if (!sub_category.equals("0") && sub_category != "0") {

				SearchValue += " and policy_sub_category::int= ?";
			}

			if (!policy_type.equals("0") && policy_type != "0") {

				SearchValue += " and b.policy_type::int= ?";
			}
			
			if (!policy_number.equals("") && policy_number != "") {

				SearchValue += " and policy_no= ?";
			}
			
			
			if (!Search.equals("")) {
				Search = Search.toLowerCase();

				SearchValue += " and ";
				SearchValue += "  (lower(b.policy_no) like ? or lower(b.policy_title) like ? or lower(td.label) like ? or lower(pd.version) like ? "
						+ "or lower(pc.policycategory) like ? or to_char(initial_date,'dd-mon-yyyy') like ? or lower(psc.subcategory) like  ?)"	;
			}
			 
		if (!globSearch.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "   (lower(b.policy_no) like ? or lower(b.policy_title) like ? or lower(td.label) like ? or lower(pd.version) like ? \n"
					+ "	or lower(pc.policycategory) like ? or to_char(initial_date,'dd-mon-yyyy') like ? or lower(psc.subcategory) like  ?)";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search,String policy_number,String category, String sub_category,String policy_type,String globSearch,String role) {

		int flag = 1;
		try {

				if (!category.equals("0") && category != "" && category != null) {
					
					stmt.setInt(flag, Integer.parseInt(category));
					flag += 1;
				}
			
				if (!sub_category.equals("0") && sub_category != "0") {
					stmt.setInt(flag, Integer.parseInt(sub_category));
					flag += 1;
				}
				

				if (!policy_type.equals("0") && policy_type != "0") {
					stmt.setInt(flag, Integer.parseInt(policy_type));
					flag += 1;
				}

				if (!policy_number.equals("") && policy_number != "") {

					stmt.setString(flag, policy_number);
					flag += 1;
				}

				if (!Search.equals("")) {
					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;

					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;

					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;

					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;
					
					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;
					
					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;

					stmt.setString(flag, "%" + Search.toLowerCase() + "%");
					flag += 1;
					
				}
			
			
			if (!globSearch.equals("")) {
				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;

				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;

				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;
				
				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;

				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;

				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;
				
				stmt.setString(flag, "%" + globSearch.toLowerCase() + "%");
				flag += 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}

	public boolean checkIsDoubleValue(String Search) {
		return Search.matches("[0-9.]+");
	}
	
	
	//=====================DOWNLOAD POLICY=============================
	public String getFilePathQuery_Track_policy(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select policy_draft_file from tb_policy_document where p_id = ?";
			

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("policy_draft_file");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
	
	public ArrayList<ArrayList<String>> GetCategoryData() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
		    q = "SELECT dblink_connect('myconn'\n" + 
		    		"       ,'hostaddr=192.168.15.179 dbname=Ayush_Edu_Repo user=postgres password=postgres')"; 
			stmt=conn.prepareStatement(q);  
			ResultSet rs = stmt.executeQuery();
			String check = "";
			while (rs.next()) {
				check = rs.getString("dblink_connect");
			}
			 rs.close();
			  stmt=null;
			if(!check.equals("OK")) {
		stmt=conn.prepareStatement("create extension dblink");  
		        stmt.executeUpdate();
			}  	 
		  rs.close();
		  stmt=null;
			q = "SELECT distinct a.policy_category,b.policycategory FROM public.tb_policy_initial_draft a\n" + 
					"inner join dblink('dbname=Ayush_Edu_Repo', 'SELECT id,policycategory FROM public.tb_policycategory_mstr\n" + 
					"ORDER BY id ASC ') as\n" + 
					"b(id integer,policycategory character varying) on b.id = a.policy_category\n" + 
					"ORDER BY a.policy_category  ASC ";

			stmt = conn.prepareStatement(q);
		//	stmt.setInt(1, Integer.parseInt(id));
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("policycategory"));  //category name
				list.add(rs1.getString("policy_category"));//id

				alist.add(list);
			}
			rs1.close();
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
	
	public ArrayList<ArrayList<String>> GetSubCategoryData(int policy_category) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
//		 Connection c = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
		    q = "SELECT dblink_connect('myconn'\n" + 
		    		"       ,'hostaddr=192.168.15.179 dbname=Ayush_Edu_Repo user=postgres password=postgres')"; 
			stmt=conn.prepareStatement(q);  
			ResultSet rs = stmt.executeQuery();
			String check = "";
			while (rs.next()) {
				check = rs.getString("dblink_connect");
			}
			 rs.close();
			  stmt=null;
			if(!check.equals("OK")) {
		stmt=conn.prepareStatement("create extension dblink");  
		        stmt.executeUpdate();
			}  	 
		  rs.close();
		  stmt=null;
			q = "\n" + 
					"SELECT b.subcategory FROM public.tb_policy_initial_draft a\n" + 
					"inner join dblink('dbname=Ayush_Edu_Repo', 'SELECT id,subcategory FROM public.tb_subpolicycategory_mstr  ') as\n" + 
					"b(id integer,subcategory character varying) on b.id = a.policy_sub_category where policy_category= ?\n" + 
					"ORDER BY a.policy_category  ASC \n" + 
					"\n" + 
					" ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, policy_category);
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("subcategory"));  //sub category name

				alist.add(list);
			}
			rs1.close();
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
	public ArrayList<String> data_policy_number_Search_Status(String p_no) {
					
			ArrayList<String> alist = new ArrayList<String>();		
			Connection conn = null;
			String q="";
		
			try{
					
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
							
					q="SELECT  pid.level_one_status, pid.level_two_status, pid.level_three_status, pid.level_four_status, pid.final_status\n"
							+ "FROM tb_policy_initial_draft pid\n"
							+ "INNER JOIN tb_policy_document tpd on tpd.p_id = pid.id\n"
							+ "WHERE pid.policy_no = ? and (SELECT max (pd.version) from tb_policy_initial_draft  td\n"
							+ "INNER JOIN tb_policy_document pd on pd.p_id = td.id\n"
							+ "WHERE td.policy_no = ? ) = tpd.version";
					
					 stmt=conn.prepareStatement(q);
					 stmt.setString(1, p_no);
					 stmt.setString(2, p_no);
					 					  
					 ResultSet rs = stmt.executeQuery();  
					 while (rs.next()) {
					    	  ArrayList<String> list = new ArrayList<String>();
					    	  list.add(rs.getString("level_one_status"));
					    	  list.add(rs.getString("level_two_status"));
					    	  list.add(rs.getString("level_three_status"));
					    	  list.add(rs.getString("level_four_status"));
					    	 alist.addAll(list);	
		 	        }
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch (SQLException e) {
					
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
