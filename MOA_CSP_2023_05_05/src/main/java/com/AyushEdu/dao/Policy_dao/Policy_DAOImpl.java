package com.AyushEdu.dao.Policy_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;

@Repository
public class Policy_DAOImpl implements Policy_DAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	

	


@Override
	public ArrayList<ArrayList<String>> DataTablePolicyDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String policy, String initial_date, String policy_number,
			String status,String policy_type,String globSearch,HttpSession session) {
		
		String  role = session.getAttribute("role").toString();
//		System.err.println("role----"+role);
		String ls="";
//		if(role.equals("level 1")) {
//			ls = "level_one_status = '0'";
//			if(!status.equals("")) {
//				
//			}
//		}
//		if(role.equals("level 2")) {
//			ls = "level_two_status = '0'";
//		}
//		if(role.equals("level 3")) {
//			ls = "level_three_status = '0'";
//		}
//		if(role.equals("level 4")) {
//			ls = "level_four_status = '0'";
//		}
		
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search, policy, initial_date, policy_number, status,policy_type,globSearch,role);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

//			
//			q = "SELECT ROW_NUMBER() OVER(order by id DESC) as sr_no, policy_title,TO_CHAR(initial_date , 'DD-MON-YYYY') as initial_date ,policy_no, id, policy_type,final_status from tb_policy_initial_draft \n"
//					+ "where id!=0 " 
//					+ SearchValue + "  ORDER BY " + orderColunm + " " + orderType + "  limit     " + pageL + "  OFFSET  " + startPage;
			

			q = "SELECT ROW_NUMBER() OVER(order by pm.id DESC) as sr_no, policy_title,TO_CHAR(initial_date , 'DD-MON-YYYY') as initial_date,\n"
							+ "policy_no, pm.id, td.label as  policy_type,final_status from tb_policy_initial_draft pm\n"
							+ "inner join t_domain_value td ON td.codevalue  = pm.policy_type and domainid='POLICY_TYPE'\n"
							+ "where pm.id!=0 "
							+ SearchValue + "  ORDER BY " + orderColunm + " " + orderType + "  limit     " + pageL + "  OFFSET  " + startPage;


			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search, policy, initial_date, policy_number, status,policy_type,globSearch,role);
			ResultSet rs = stmt.executeQuery();

			int i = 1;
			while (rs.next()) {
				// alist....arrange icon column wise......by ruler
				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				
				
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Policy Draft ?') )"
						+ "{editData('" + rs.getInt("id") + "','" + rs.getString("policy_title") + "','"
						+ rs.getString("initial_date") + "'," + "'" + rs.getString("policy_no")
						+ "')}else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o'  style='width: 100%; text-align: -webkit-center;' " + Update + " title='Edit Data'></i>";

				
				String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Policy Draft ?') )"
						+ "{getDownloadPdfPolicy('" + rs.getInt("id") + "')}else{ return false;}\"";
				f1 = "<i class='fa fa-download' style='width: 100%; text-align: -webkit-center;' " + Download + " title='Downlaod'></i>";


				String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Policy Draft?') ){ViewData("
						+ rs.getInt("id") + "," + status + ",'"+ rs.getString("final_status") +"')}else{ return false;}\"";
				f2 = "<i class='fa fa-eye' style='width: 100%; text-align: -webkit-center;'  " + View + " title='View Data'></i>";

				String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete Policy Draft ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f3 = "<i class='fa fa-trash ' style='width: 100%; text-align: -webkit-center;'  " + Delete + " title='Delete Data'></i>";

				action = f + " " + f1 + " " + " " + f2 + " " + f3;
				

				
				
				alist.add(rs.getString("sr_no")); //0
				alist.add(rs.getString("policy_title"));//1
				alist.add(rs.getString("initial_date"));//2
				alist.add(rs.getString("policy_no"));//3
				alist.add(rs.getString("policy_type"));//4
				
				alist.add(role);//5
				

				if(role.equals("level 1")) {

					alist.add(f); //6
					alist.add(f1);//7
					alist.add(f2);//8
					alist.add(f3);//9
					}
					if(role.equals("level 2")) {

						alist.add("");//6
						alist.add("");//7
						alist.add(f2);//8
						alist.add("");//9
					}
					if(role.equals("level 3")) {

						alist.add("");//6
						alist.add("");//7
						alist.add(f2);//8
						alist.add("");//9
					}
					if(role.equals("level 4")) {

						alist.add("");//6
						alist.add("");//7
						alist.add(f2);//8
						alist.add("");//9
					}
//				alist.add(action);
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

	public long DataTablePolicyDataTotalCount(String Search, String policy, String initial_date, String policy_number,
			String status,String policy_type,String globSearch,HttpSession session) {
		String  role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search, policy, initial_date, policy_number, status,policy_type,globSearch ,role);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
//			q = " select count(*) from (SELECT ROW_NUMBER() OVER(order by id) as sr_no,id, policy_title,TO_CHAR(initial_date , 'DD-MON-YYYY') as initial_date ,\n"
//					+ "policy_no,policy_type from tb_policy_initial_draft where id!=0 " + SearchValue + ") ab  ";
			

q="select count(*) from (SELECT ROW_NUMBER() OVER(order by pm.id DESC) as sr_no, policy_title,TO_CHAR(initial_date , 'DD-MON-YYYY') as initial_date,\n"
					+ "policy_no, pm.id, td.label as  policy_type,final_status from tb_policy_initial_draft pm\n"
					+ "inner join t_domain_value td ON td.codevalue  = pm.policy_type and domainid='POLICY_TYPE'\n"
					+ "where pm.id!=0 "
					+ SearchValue + ") ab  ";
					

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search, policy, initial_date, policy_number, status,policy_type,globSearch,role);
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

	public String GenerateQueryWhereCandiList(String Search, String policy, String initial_date, String policy_number,
			String status,String policy_type,String globSearch,String role) {
		String SearchValue = "";

		
		if (!policy.equals("0") && policy != "0" && policy != null) {

			SearchValue += " and policy_title like ? ";
		}

		if (!initial_date.equals("") && initial_date != "" && !initial_date.equals("DD/MM/YYYY")){

			SearchValue += " and initial_date= ?::TIMESTAMP";
		}

		if (!policy_number.equals("") && policy_number != "") {

			SearchValue += " and policy_no= ?";
		}
		
		if (!policy_type.equals("0") && policy_type != "0") {

			SearchValue += " and policy_type= ?";
		}
		
		
		if(role.equals("level 1")) {
			if(status.equals("0")) {
					SearchValue += " and cast(level_one_status as integer)= ?";
//					SearchValue += " and cast(level_two_status as integer)= ?";
//					SearchValue += " and cast(level_three_status as integer)= ?";
//					SearchValue += " and cast(level_four_status as integer)= ?";
					SearchValue += " and cast(final_status as integer)= ?";
					}
//			if(role.equals("level 2")) {
//				SearchValue += " and cast(level_two_status as integer)= ?";
//			}
//			if(role.equals("level 3")) {
//				SearchValue += " and cast(level_three_status as integer)= ?";
//			}
//			if(role.equals("level 4")) {
//				SearchValue += " and cast(level_four_status as integer)= ?";
//			}
			
			
		
//		if(status.equals("0")) {
//			SearchValue += " and cast(level_one_status as integer)= ?";
//		}
			if(status.equals("1")) {
				SearchValue += " and cast(level_one_status as integer)= ?";
				SearchValue += " and cast(level_two_status as integer)= ?";
			}
			
			if(status.equals("2")) {
				SearchValue += " and cast(level_one_status as integer)= ?";
				SearchValue += " and cast(level_three_status as integer)= ?";
			}
				if(status.equals("3")) {
					SearchValue += " and cast(level_four_status as integer)= ?";
				}
				if(status.equals("4")) {
				
				SearchValue += " and cast(level_one_status as integer)= ?";
				SearchValue += "  and cast(level_two_status as integer)= ?";
				SearchValue += "  and cast(final_status as integer)= ?";
				SearchValue += " and forward_status = ?";
				}
				if(status.equals("5")) {
					SearchValue += " and cast(level_three_status as integer)= ?";
				}
				if(status.equals("6")) {
					SearchValue += " and cast(level_one_status as integer)= ?";
					SearchValue += " and cast(level_four_status as integer)= ?";
					SearchValue += "  and cast(final_status as integer)= ?";
					SearchValue += " and forward_status = ?";
				}
		}
		
		//change status in role........by ruler
	
			if(role.equals("level 2")) {
				if(status.equals("7")) {
				SearchValue += " and cast(level_two_status as integer)= ?";
				}
				if(status.equals("8")) {
					SearchValue += " and cast(level_two_status as integer)= ?";
					}
				if(status.equals("9")) {
					SearchValue += " and cast(level_two_status as integer)= ?";
					}
				}
		
			if(role.equals("level 3")) {
				if(status.equals("10")) {
				SearchValue += " and cast(level_three_status as integer)= ?";
				}
				if(status.equals("11")) {
					SearchValue += " and cast(level_three_status as integer)= ?";
					}
				}
			
			if(role.equals("level 4")) {
				if(status.equals("12")) {
				SearchValue += " and cast(level_four_status as integer)= ?";
				}
				if(status.equals("13")) {
					SearchValue += " and cast(level_four_status as integer)= ?";
					}
				if(status.equals("14")) {
					SearchValue += " and cast(level_four_status as integer)= ?";
					}
				}
		
		
		
			// end
			if (!Search.equals("")) {
				Search = Search.toLowerCase();

				SearchValue += " and ";
				SearchValue += "  (lower(policy_title) like ? or to_char(initial_date,'dd-mon-yyyy') like ? or lower(policy_no) like ? or lower(label) like ? ) ";
			}
			
			if (!globSearch.equals("")) {
				Search = Search.toLowerCase();

				SearchValue += " and ";
				SearchValue += "  (lower(policy_title) like ? or to_char(initial_date,'dd-mon-yyyy') like ? or lower(policy_no) like ?  or lower(label) like ?) ";
			}
	

		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search, String policy,
			String initial_date, String policy_number, String status,String policy_type,String globSearch,String role) {

		int flag = 1;
		try {
			

			if (!policy.equals("0") && policy != "0" && policy != null) {

				stmt.setString(flag,"%"+policy+"%");
				flag += 1;

			}
			if (!initial_date.equals("") && initial_date != "" && !initial_date.equals("DD/MM/YYYY")) {
				stmt.setString(flag, initial_date);
				flag += 1;
			}

			if (!policy_number.equals("") && policy_number != "") {

				stmt.setString(flag, policy_number);
				flag += 1;
			}
			
			if (!policy_type.equals("0") && policy_type != "0") {

				stmt.setString(flag, policy_type);
				flag += 1;
			}
			
			
			if(role.equals("level 1")) {
				
			if (status.equals("0")) {
					stmt.setInt(flag,0);
					flag += 1;
//					stmt.setInt(flag,-1);
//					flag += 1;
//					stmt.setInt(flag,-1);
//					flag += 1;
					stmt.setInt(flag,1);
					flag += 1;
				}
//				if(role.equals("level 2")) {
//					stmt.setInt(flag,0);
//					flag += 1;
//				}
//				if(role.equals("level 3")) {
//					stmt.setInt(flag,0);
//					flag += 1;
//				}
//				if(role.equals("level 4")) {
//					stmt.setInt(flag,0);
//					flag += 1;
//				}
				
			
				if (status.equals("1") ) {
					stmt.setInt(flag,0);
					flag += 1;
					stmt.setInt(flag,1);
					
					flag += 1;
				}
				
				if (status.equals("2")) {
					stmt.setInt(flag,0);
					flag += 1;
					stmt.setInt(flag,1);
					flag += 1;
				}
				if (status.equals("3") ) {
					stmt.setInt(flag, 1);
					
					flag += 1;
				}
				if (status.equals("4") ) {
					
					stmt.setInt(flag, 0);
					flag += 1;
					stmt.setInt(flag, 2);
					flag += 1;
					stmt.setInt(flag, 3);
					flag += 1;
					stmt.setString(flag, "level 2");
					flag += 1;
				}
				if (status.equals("5")) {
					stmt.setInt(flag,2);
					
					flag += 1;
				}
				if (status.equals("6")) {
					stmt.setInt(flag, 0);
					flag += 1;
					stmt.setInt(flag, 2);
					flag += 1;
					stmt.setInt(flag, 3);
					flag += 1;
					stmt.setString(flag, "level 4");
					flag += 1;
				}
			}
				
				
			//change status in role with flag........by ruler
			
					if(role.equals("level 2")) {
						if (status.equals("7")) {
							stmt.setInt(flag,0);
							flag += 1;
						}
						if (status.equals("8")) {
							stmt.setInt(flag,1);
							flag += 1;
						}
						if (status.equals("9")) {
							stmt.setInt(flag,2);
							flag += 1;
						}
			 	
						}
					
					
						if (role.equals("level 3")) {
							if (status.equals("10")) {
								stmt.setInt(flag, 0);
								flag += 1;
							}
							if (status.equals("11")) {
								stmt.setInt(flag, 1);
								flag += 1;
							}
						}
						
						if(role.equals("level 4")) {
							if (status.equals("12")) {
								stmt.setInt(flag,0);
								flag += 1;
							}
							if (status.equals("13")) {
								stmt.setInt(flag,1);
								flag += 1;
							}
							if (status.equals("14")) {
								stmt.setInt(flag,1);
								flag += 1;
							}
				 	
							}
				
				
				//end

			if (!Search.equals("")) {
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


	// rajdip

	@Override
	public ArrayList<ArrayList<String>> getpolicyinformation(int id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q="select pd.p_id,td.label as  policy_type, pid.policy_unique_id, pid.policy_category, pid.policy_sub_category, \n" + 
					"pid.policy_title,TO_CHAR(initial_date , 'dd/MON/yyyy') as initial_date , policy_no, pd.policy_draft_file,\n" + 
					"pid.purpose, pid.scope,pid.level_one_status,pid.level_two_status,pid.level_three_status,pid.level_four_status \n" + 
					"from tb_policy_initial_draft pid\n" + 
					"INNER join tb_policy_document pd on pd.p_id = pid.id\n" + 
					"inner join t_domain_value td ON td.codevalue  = pid.policy_type and domainid='POLICY_TYPE'\n" + 
					"WHERE pid.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("policy_type"));// 0
				alist.add(rs.getString("policy_unique_id"));// 1
				alist.add(rs.getString("policy_category"));// 2
				alist.add(rs.getString("policy_sub_category"));// 3
				alist.add(rs.getString("policy_title"));// 4
				alist.add(rs.getString("initial_date"));// 5
				alist.add(rs.getString("policy_no"));// 6
				alist.add(rs.getString("p_id"));// 7
				alist.add(rs.getString("purpose"));// 8
				alist.add(rs.getString("scope"));// 9
				alist.add(rs.getString("policy_draft_file"));// 10
				alist.add(rs.getString("level_one_status"));// 11
				alist.add(rs.getString("level_two_status"));// 12
				alist.add(rs.getString("level_three_status"));// 13
				alist.add(rs.getString("level_four_status"));// 14
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

	@Override
	public String getFilePathQueryForDoc(String id) {

		String whr = "";
		String q1 = "";
		Connection conn = null;
		String fildname1 = "";
//		if(val.equals("1")) {
//			q1="persdetails";
//		}
//		else if(val.equals("2")){
//			q1="edudetails";
//		}
//		
//		if (fildname.equals("resumedoc")) {
//			fildname1 = "resumedoc";
//		}
//		else if (fildname.equals("identitydoc")) {
//			fildname1 = "identitydoc";
//		}
//		else if (fildname.equals("3")) {
//			fildname1 = "identitydoc";
//		}


		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select policy_draft_file from tb_policy_document where p_id=?";
			// query = query.replace("$fildname", fildname);

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));

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

	public String updateSports(TB_POLICY_INITIAL_DRAFT obj) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// Date date = new Date();
		String msg = "";
		try {
			String hql = "update TB_POLICY_INITIAL_DRAFT set upper(policy_type)=:policy_type and upper(policy_unique_id)=:policy_unique_id \"\n"
					+ "					+ \"and policy_category=:policy_category and policy_sub_category=:policy_sub_category and upper(purpose)=:purpose and upper(scope)=:scope \"\n"
					+ "					+ \"and upper(policy_title)=:policy_title and upper(policy_no)=:policy_no and initial_date=:ini_dt where id=:id";
			@SuppressWarnings("rawtypes")
			Query query = sessionHQL.createQuery(hql).setParameter("policy_type", obj.getPolicy_category())
					.setParameter("policy_unique_id", obj.getPolicy_unique_id())
					.setParameter("policy_category", obj.getPolicy_category()).setParameter("purpose", obj.getPurpose())
					.setParameter("scope", obj.getScope()).setParameter("policy_title", obj.getPolicy_title())
					.setParameter("policy_no", obj.getPolicy_no()).setParameter("initial_date", obj.getInitial_date())
					.setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", new Date())
					.setParameter("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}

	@Override
	public String Policy(TB_POLICY_INITIAL_DRAFT obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

@Override
	public ArrayList<ArrayList<String>> getpolicydocumentandversion(int id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "SELECT version, policy_draft_file FROM tb_policy_document WHERE p_id = ?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("version"));// 0
				alist.add(rs.getString("policy_draft_file"));// 1
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

/////////////////////download/////////mirangi_21_3_22
	public String getFilePathQuery_policy(int id) {

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
	

	@Override
	public Integer getchild_id_by_p_id(int id) {

		Integer whr = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select id from tb_policy_document where p_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getInt("id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	//===========================================POLICY SUB CATEGORY DROP DOWN===========================================
	public List<TB_SUBPOLICYCATEGORY> getPolicylistUrl(String selval) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("from TB_SUBPOLICYCATEGORY where status='1' and policycategory=:policycategory");
			q1.setString("policycategory", selval);
			@SuppressWarnings("unchecked")
			List<TB_SUBPOLICYCATEGORY> list = (List<TB_SUBPOLICYCATEGORY>) q1.list();
			tx.commit();
			
			return list;	
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	
	//===========================================GET POLICY REMARK===========================================
	@Override
	public ArrayList<ArrayList<String>> getPolicyremarkList(int id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q ="select remark from tb_policy_initial_draft WHERE  id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("remark"));// 0

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

	//=========================================UNIQUE ID TO POLICY ID===============================================
	@Override
	public Integer getpolicy_id_by_policy_unique_id(String puid) {

		Integer whr = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select id from tb_policy_initial_draft where policy_unique_id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, puid);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getInt("id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
	
	
}
