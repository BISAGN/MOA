package com.AyushEdu.dao.Exp_Excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Registration.TB_PRE_EDUCATION_DETAILS;



@Service
@Repository
public class Student_DetailsDaoImpl implements Student_DetailsDao {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public ArrayList<ArrayList<String>> DataTableStudentDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree) {
				
		
		String SearchValue = GenerateQueryWhereClause_SQL(search,userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name, 
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree);
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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
			
			q="select distinct ROW_NUMBER() OVER(order by sd.id) as sr_no, ir.institute_name as institute_id,ir.code as inst_code,\n"
					+ "stm.state_name, name,last_name , mother_name,	 father_name, to_char(sd.dob,'DD-MON-YYYY') as dob, sd.email,\n"
					+ "neet_application_no,neet_roll_no,neet_rank,neet_percentile,neet_marks, qm.quota, cam.counseling_authority, cas.category,\n"
					+ "sm.system_name,dm.degree_name,dm.type_of_degree\n"
					+ "FROM edu_lms_nch_student_details sd\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
					+ "inner join logininformation li on li.email_id=sd.email\n"
					+ "inner join userroleinformation um on um.user_id=li.userid\n"
					+ "inner join roleinformation ri ON ri.role_id = um.role_id\n"
					+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
					+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
					+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
					+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
					+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
					+ "where sd.id is not null    " + SearchValue + "  ORDER BY 1  "+ orderType +"  limit "+ pageL +"  OFFSET "+ startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name, 
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree);

			System.err.println("\n\n******************************\nNCH EFORM REPORT stmt---- rs ----->\n"+stmt+"\n******************************\n\n");
			
			ResultSet rs = stmt.executeQuery();
			
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("sr_no"));//0
				list.add(rs.getString("institute_id"));//1
				list.add(rs.getString("inst_code"));//2
				list.add(rs.getString("state_name"));//3
				list.add(rs.getString("name"));//4
				list.add(rs.getString("last_name"));//5
				list.add(rs.getString("mother_name"));//6
				list.add(rs.getString("father_name"));//7
				list.add(rs.getString("dob"));//8
				list.add(rs.getString("email"));//9
				list.add(rs.getString("neet_application_no"));//10
				list.add(rs.getString("neet_roll_no"));//11
				list.add(rs.getString("neet_rank"));//12
				list.add(rs.getString("neet_percentile"));//13
				list.add(rs.getString("neet_marks"));//14
				list.add(rs.getString("quota"));//15
				list.add(rs.getString("counseling_authority"));//16
				list.add(rs.getString("category"));//17
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
	public long DataTableStudentDataTotalCount(String search, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree)  {

		String SearchValue = GenerateQueryWhereClause_SQL(search,userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name, 
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree);
		
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q= "select count(*) from (\n"
						+ " select distinct ROW_NUMBER() OVER(order by sd.id) as sr_no, ir.institute_name as institute_id,ir.code as inst_code,\n"
						+ "stm.state_name, name,last_name , mother_name,	 father_name, to_char(sd.dob,'DD-MON-YYYY') as dob, sd.email,\n"
						+ "neet_application_no,neet_roll_no,neet_rank,neet_percentile,neet_marks, qm.quota, cam.counseling_authority, cas.category,\n"
						+ "sm.system_name,dm.degree_name,dm.type_of_degree\n"
						+ "FROM edu_lms_nch_student_details sd\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
						+ "inner join logininformation li on li.email_id=sd.email\n"
						+ "inner join userroleinformation um on um.user_id=li.userid\n"
						+ "inner join roleinformation ri ON ri.role_id = um.role_id\n"
						+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
						+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
						+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
						+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
						+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
						+ "where sd.id is not null   "+ SearchValue + ") ab ";
		
			PreparedStatement stmt = conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt, search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name, 
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree);
			
//			System.err.println("\n\n******************************\nNCH EFORM REPORT COUNT stmt---- rs ----->\n"+stmt+"\n******************************\n\n");
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


	public String GenerateQueryWhereClause_SQL (String search,String userid,
			String institute_id,String institute_code,String state_id,
			String name,String last_name,String  mother_name,String father_name, 
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree) {
		String SearchValue = "";
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue += " and (upper(ir.institute_name) like ?  or upper(ir.code) like ? or upper(stm.state_name) like ? \n" 
					+"  or upper(name) like ? or upper(last_name) like ? or upper(mother_name) like ?  or upper(father_name) like ? \n"
					+ " or to_char(dob,'DD-MON-YYYY') like ? or upper(email) like ? \n"
					+ " or upper(neet_application_no::text) like ?  or upper(neet_roll_no::text) like ? or upper(neet_rank::text) like ? \n"
					+ " or upper(neet_percentile::text) like ? or upper(neet_marks::text) like ?  or upper(qm.quota) like ?  or upper(cam.counseling_authority) like ?  \n"
					+ " or upper(cas.category) like ? )";
		}
		if (type_of_degree != null && !type_of_degree.equals("") && !type_of_degree.equals("0")) {
			SearchValue +=  "and dm.type_of_degree = ? ";
		}

		if (name != null && !name.equals("")) {
			SearchValue +=  "and upper(sd.name) like ? ";
		}
		if (last_name != null && !last_name.equals("")) {
			SearchValue +=  "and upper(sd.last_name) like ? ";
		}
		if (mother_name != null && !mother_name.equals("")) {
			SearchValue +=  "and upper(sd.mother_name) like ? ";
		}
		if (father_name != null && !father_name.equals("")) {
			SearchValue +=  "and upper(sd.father_name) like ? ";
		}
		if (dob != null && !dob.equals("") && !dob.equals("DD/MM/YYYY")) {
			SearchValue +=  "and TO_CHAR(sd.dob , 'dd/mm/yyyy') = ? ";
	    }
		if (email != null && !email.equals("")) {
			SearchValue +=  "and upper(sd.email) like ? ";
	    }
		if (neet_application_no != null && !neet_application_no.equals("")) {
			SearchValue +=  "and upper(sd.neet_application_no::text) like ? ";
		}
		if (neet_roll_no != null && !neet_roll_no.equals("")) {
			SearchValue +=  "and upper(sd.neet_roll_no::text) like ? ";
		}
		if (neet_rank != null && !neet_rank.equals("")) {
			SearchValue +=  "and upper(sd.neet_rank::text) like ? ";
		}
		if (neet_percentile != null && !neet_percentile.equals("")) {
			SearchValue +=  "and upper(sd.neet_percentile::text) like ? ";
		}
		if (neet_marks != null && !neet_marks.equals("")) {
			SearchValue +=  "and upper(sd.neet_marks::text) like ? ";
		}
		if (!quota_id.equals("0") && quota_id != null) {
			SearchValue +=  "and sd.quota = ? ";
		}
		if (!counselling_authority.equals("0") && counselling_authority != null) {
			SearchValue +=  "and sd.counc_auth = ? ";
		}
		if (!category_id.equals("0") && category_id != null) {
			SearchValue +=  "and sd.category = ? ";
		}
		
		if(role.equals("NCH")) {
			System.err.println("\n\nROLE-------"+role+"\n\n");
			
			SearchValue +=  "and ir.dashboard_status = ? ";
			
			if (!system.equals("0") && system != null) {
				SearchValue +=  "and sd.system = ? ";
			}
			if (!university_id.equals("0") && university_id != null) {
				SearchValue +=  "and ir.university_id = ? ";
			}
			if (!institute_id.equals("0") && institute_id != null) {
				SearchValue +=  "and sd.institude_userid = ? ";
			}
		}
		if(role.toLowerCase().contains("board")) {
			
			SearchValue +=  "and ir.dashboard_status = ? ";
			
			if (!university_id.equals("0") && university_id != null) {
				SearchValue +=  "and ir.university_id = ? ";
			}
			if (!institute_id.equals("0") && institute_id != null) {
				SearchValue +=  "and sd.institude_userid = ? ";
			}
		}
		if(role.toLowerCase().contains("university")) {
			
			SearchValue +=  "and ir.dashboard_status = ? ";
			
			if (!university_id.equals("0") && university_id != null) {
				SearchValue +=  "and ir.university_id = ? ";
			}
			if (!institute_id.equals("0") && institute_id != null) {
				SearchValue +=  "and sd.institude_userid = ? ";
			}
		}
		if(role.toLowerCase().contains("institute")) {
			if (!institute_id.equals("0") && institute_id != null) {
				SearchValue +=  "and sd.institude_userid = ? ";
			}
		}
		if (institute_code != null && !institute_code.equals("")) {
			SearchValue +=  "and upper(ir.code) like ? ";
		}
		if (!state_id.equals("0") && state_id != null) {
			SearchValue +=  "and ir.state_id = ? ";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String search, String userid,
			String institute_id,String institute_code,String state_id,
			String name,String last_name,String  mother_name,String father_name, 
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree) {
		
		int flag = 0;
		try {
			if (search != null && !search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				
			}
			if (type_of_degree != null && !type_of_degree.equals("") && !type_of_degree.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(type_of_degree));
			}
			
			if (name != null && !name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + name.toUpperCase() + "%");
			}
			if (last_name != null && !last_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + last_name.toUpperCase() + "%");
			}
			if (mother_name != null && !mother_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + mother_name.toUpperCase() + "%");
			}
			if (father_name != null && !father_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + father_name.toUpperCase() + "%");
			}
			if (dob != null && !dob.equals("") && !dob.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag,dob);
		    }
			if (email != null && !email.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + email.toUpperCase() + "%");
		    }
			if (neet_application_no != null && !neet_application_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + neet_application_no.toUpperCase() + "%");
			}
			if (neet_roll_no != null && !neet_roll_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + neet_roll_no.toUpperCase() + "%");
			}
			if (neet_rank != null && !neet_rank.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + neet_rank.toUpperCase() + "%");
			}
			if (neet_percentile != null && !neet_percentile.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + neet_percentile.toUpperCase() + "%");
			}
			if (neet_marks != null && !neet_marks.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + neet_marks.toUpperCase() + "%");
			}
			if (!quota_id.equals("0") && quota_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(quota_id));
			}
			if (!counselling_authority.equals("0") && counselling_authority != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(counselling_authority));
			}
			if (!category_id.equals("0") && category_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(category_id));
			}
			if(role.equals("NCH")) {
				
				//For Set Dashboard Status
				flag += 1;
				stmt.setInt(flag,1);
				
				if (!system.equals("0") && system != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(system));
				}
				if (!university_id.equals("0") && university_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(university_id));
				}
				if (!institute_id.equals("0") && institute_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(institute_id));
				}
			}
			if(role.toLowerCase().contains("board")) {
				
				//For Set Dashboard Status
				flag += 1;
				stmt.setInt(flag,1);
				
				if (!university_id.equals("0") && university_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(university_id));
				}
				if (!institute_id.equals("0") && institute_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(institute_id));
				}
			}
			if(role.toLowerCase().contains("university")) {
				
				//For Set Dashboard Status
				flag += 1;
				stmt.setInt(flag,1);
				
				if (!university_id.equals("0") && university_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(university_id));
				}
				if (!institute_id.equals("0") && institute_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(institute_id));
				}
			}
			if(role.toLowerCase().contains("institute")) {
				if (!institute_id.equals("0") && institute_id != null) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(institute_id));
				}
			}
//			if (!institute_id.equals("0") && institute_id != null) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(institute_id));
//			}
			if (institute_code != null && !institute_code.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + institute_code.toUpperCase() + "%");
			}
			if (!state_id.equals("0") && state_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(state_id));
			}

			} catch (Exception e) {
		}

		return stmt;
	}

}	


