package com.AyushEdu.dao.LMS_NCISM;
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
public class StudentDetailsReport_DAOImpl implements StudentDetailsReport_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
//	@SuppressWarnings("null")
//	public String GenerateQueryWhereClause_SQL(String search,String ayush_id,String name,String dob,String email,String mobile_no,String admission_date,String system,String degree,String enrollment_no,String upload_date,String instid,String course_name,String term_id) {
//		String SearchValue = "";
//		if (search != null && !search.equals("")) { // for Input Filter
//
//			SearchValue += " and (upper(lsd.ayush_id) like ? or upper(lsd.name) like ? or "
//					+ "upper(lsd.aadhar_card) like ? or upper(lsd.email) like ? or  \n"
//					+ "upper(lsd.mobile_no) like ? or upper(tm.prof_name) like ? "
//					+ " or upper(sm.system_name) like ? or upper(dm.degree_name) like ? \n"
//					+ "or upper(enrollment_no) like ?  or TO_char(TO_TIMESTAMP(lsd.dob, 'DD/MM/YYYY') , 'DD/MM/YYYY') like ? "
//					+ "or TO_char(TO_TIMESTAMP(lsd.admission_date, 'DD/MM/YYYY') , 'DD/MM/YYYY')like ? or TO_char(TO_TIMESTAMP(lsd.upload_date, 'DD/MM/YYYY') , 'DD/MM/YYYY')like ?)";
//			}
//		/// advance search  or upper(cm.course_name) like ?
//
//		if (!ayush_id.equals("") && ayush_id != null) {
//			SearchValue += " and upper(lsd.ayush_id) like ? ";
//		}
//		if (!name.equals("") && name != null) {
//			SearchValue += " and upper(lsd.name) like ? ";
//		}
//		if (!dob.equals("DD/MM/YYYY") && dob != null && !dob.equals("")) {
//			SearchValue += " and TO_char(TO_TIMESTAMP(lsd.dob, 'DD/MM/YYYY') , 'DD/MM/YYYY') like ?  ";
//		}
//		if (!email.equals("") && email != null) {
//			SearchValue += " and upper(lsd.email) like ? ";
//		}
//		if (!mobile_no.equals("") && mobile_no != null) {
//			SearchValue += " and upper(lsd.mobile_no) like ? ";
//		}
//		if (!admission_date.equals("DD/MM/YYYY") && admission_date != null && !admission_date.equals("")) {
//			SearchValue += "  and TO_char(TO_TIMESTAMP(lsd.admission_date, 'DD/MM/YYYY') , 'DD/MM/YYYY') like ?";
//		}
//		if (!term_id.equals("0")) {
//					SearchValue += " and tm.term::int = ? ";
//				}	
//		if (!system.equals("0")) {
//			SearchValue += " and lsd.system = ? ";
//		}
//		if (!degree.equals("0")) {
//			SearchValue += " and lsd.degree = ? ";
//		}
//		if (!enrollment_no.equals("") && enrollment_no != null) {
//			SearchValue += " and upper(lsd.enrollment_no) like ? ";
//		}
//		if (!upload_date.equals("DD/MM/YYYY") && upload_date != null && !upload_date.equals("")) {
//			SearchValue += "  and TO_char(TO_TIMESTAMP(lsd.upload_date, 'DD/MM/YYYY') , 'DD/MM/YYYY') like ? ";
//		}
//		if (!instid.equals("") && instid != null) {
//			SearchValue += " and institude_userid = (select institute_id from logininformation where userid=?) ";
//		}
//		return SearchValue;
//	}
//	
//	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search,String ayush_id,String name,String dob,String email,String mobile_no, String admission_date,String system,String degree,String enrollment_no,String upload_date,String instid,String course_name,String term_id) {
//		int flag = 0;
//		try {
//			if (search != null && !search.equals("")) {
//
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//				
//				flag += 1;
//				stmt.setString(flag, "%" + search.toUpperCase() + "%");
//			}
//		
//			if (!ayush_id.equals("") && ayush_id != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + ayush_id.toUpperCase() + "%");
//			}
//			if (!name.equals("") && name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + name.toUpperCase() + "%");
//			}
//			if (!dob.equals("DD/MM/YYYY") && dob != null && !dob.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + dob.toUpperCase() + "%");
//			}
//			if (!email.equals("") && email != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + email.toUpperCase() + "%");
//			}
//			if (!mobile_no.equals("") && mobile_no != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + mobile_no.toUpperCase() + "%");
//			}
//			if (!admission_date.equals("DD/MM/YYYY") && admission_date != null && !admission_date.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + admission_date.toUpperCase() + "%");
//			}
//			
//			if (!term_id.equals("0")  && term_id != null ) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(term_id));
//			}	
//			if (!system.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(system));
//			}
//			if (!degree.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(degree));
//			}
//			if (!enrollment_no.equals("") && enrollment_no != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + enrollment_no.toUpperCase() + "%");
//			}
//			if (!upload_date.equals("DD/MM/YYYY") && upload_date != null && !upload_date.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + upload_date.toUpperCase() + "%");
//			}
//			if (!instid.equals("") && instid != null) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(instid));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return stmt;
//	}

	@Override
	public ArrayList<ArrayList<String>> DataTablestudentdetailsDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
			String pg_degree,String pg_subject,String pg_intake){
		System.err.println("type_of_degree===============dao--------------"+type_of_degree);
		String SearchValue = GenerateQueryWhereClause_SQL(search,userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name, 
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);

		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		System.err.println("alist================------------------"+alist);
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
					+ "sm.system_name,dm.degree_name,dm.type_of_degree,to_char(sd.created_date,'DD-Mon-YYYY') as created_date,to_char(sd.fees_date,'DD-Mon-YYYY') as fees_date ,sd.fees_receipt,sd.id as viewid\n"
					+ "FROM edu_lms_student_details sd\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
					+ "--inner join logininformation li on li.email_id=sd.email\n"
					+ "--inner join userroleinformation um on um.user_id=li.userid\n"
					+ "--inner join roleinformation ri ON ri.role_id = um.role_id\n"
					+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
					+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
					+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
					+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
					+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
					+ "where sd.id is not null   " + SearchValue + "  ORDER BY 1  "+ orderType +"  limit "+ pageL +"  OFFSET "+ startPage;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name, 
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);

			System.err.println("\n\n******************************\nNCISM EFORM REPORT stmt---- rs ----->\n"+stmt+"\n******************************\n\n");
			
			ResultSet rs = stmt.executeQuery();
			int countFunction=1;
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
				list.add(rs.getString("type_of_degree"));//18
				list.add(rs.getString("created_date"));//19
				list.add(rs.getString("fees_date"));//20
				
				String vd ="";
				
				if (rs.getString("fees_receipt") != null && !rs.getString("fees_receipt").equals("")) {
					vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-iconic-icon view_fees_receipt'  value='ADD' title='View Data' >\n"
							+ "		<i class='lni lni-eye'>"
							+ "<input type='hidden' id='receiptid"+countFunction+"' value="+rs.getString("viewid")+">"
							+ "</i>View</a> </li></ul>";
					countFunction+=1;
				}else {
					vd="-";
				}
				
				list.add(vd);//21
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
	public long DataTablestudentdetailsTotalCount(String search, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id
			,String type_of_degree,String pg_degree,String pg_subject,String pg_intake ) {
		
		
System.err.println("institute_id-------->   "+institute_id);
		
		String SearchValue = GenerateQueryWhereClause_SQL(search,userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name, 
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
		
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
						+ "FROM edu_lms_student_details sd\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
						+ "--inner join logininformation li on li.email_id=sd.email\n"
						+ "--inner join userroleinformation um on um.user_id=li.userid\n"
						+ "--inner join roleinformation ri ON ri.role_id = um.role_id\n"
						+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
						+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
						+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
						+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
						+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
						+ "where sd.id is not null  "+ SearchValue + ") ab ";
		
			PreparedStatement stmt = conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt, search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name, 
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
			
//			System.err.println("\n\n******************************\nNCISM EFORM REPORT COUNT stmt---- rs ----->\n"+stmt+"\n******************************\n\n");
			
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
			String quota_id,String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
			String pg_degree,String pg_subject,String pg_intake) {
		String SearchValue = "";
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue += " and (upper(ir.institute_name) like ?  or upper(ir.code) like ? or upper(stm.state_name) like ? \n" 
					+"  or upper(name) like ? or upper(last_name) like ? or upper(mother_name) like ?  or upper(father_name) like ? \n"
					+ " or to_char(dob,'DD-MON-YYYY') like ? or upper(email) like ? \n"
					+ " or upper(neet_application_no::text) like ?  or upper(neet_roll_no::text) like ? or upper(neet_rank::text) like ? \n"
					+ " or upper(neet_percentile::text) like ? or upper(neet_marks::text) like ?  or upper(qm.quota) like ?  or upper(cam.counseling_authority) like ?  \n"
					+ " or upper(cas.category) like ? )";
		}
		System.err.println("institute_id-=======tttttttt-------->   "+institute_id);
		if (type_of_degree != null && !type_of_degree.equals("") && !type_of_degree.equals("0")) {
			SearchValue +=  "and dm.type_of_degree = ? ";
			
			if(type_of_degree.equals("16")) {
				if(!pg_degree.equals("0")) {
					SearchValue +=  " and sd.degree = ? ";
				}
				if(!pg_subject.equals("0")) {
					SearchValue +=  " and sd.pg_subject = ? ";
				}
				if(!pg_intake.equals("0")) {
					SearchValue +=  " and sd.intake_id = ? ";
				}
			}
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
		if(role.equals("NCISM") || role.equals("NCH")) {
//			System.err.println("\n\nROLE-------"+role+"\n\n");
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
			
			System.err.println("institute_id-=======-------->   "+institute_id);
			
			if (institute_id != null && !institute_id.equals("0")) {
				SearchValue +=  "and sd.institude_userid = ? ";
			}
		}
//		if (!institute_id.equals("0") && institute_id != null) {
//			SearchValue +=  "and sd.institude_userid = ? ";
//		}
		if (institute_code != null && !institute_code.equals("")) {
			SearchValue +=  "and upper(ir.code) like ? ";
		}
		if (!state_id.equals("0") && state_id != null) {
			SearchValue +=  "and ir.state_id = ? ";
		}
//		if (!university_id.equals("0") && university_id != null) {
//			SearchValue +=  "and sd.university_userid = ? ";
//		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String search, String userid,
			String institute_id,String institute_code,String state_id,
			String name,String last_name,String  mother_name,String father_name, 
			String dob,String email,String neet_application_no,String neet_roll_no,String neet_rank,String neet_percentile,String neet_marks,
			String quota_id,String counselling_authority,String category_id,String role,String system,String university_id,String type_of_degree,
			String pg_degree,String pg_subject,String pg_intake) {
		
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
				
				if(type_of_degree.equals("16")) {
					if(!pg_degree.equals("0")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(pg_degree));
					}
					if(!pg_subject.equals("0")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(pg_subject));
					}
					if(!pg_intake.equals("0")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(pg_intake));
					}
				}
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
			if(role.equals("NCISM") || role.equals("NCH")) {
				flag += 1;
				stmt.setInt(flag, 1);
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
				flag += 1;
				stmt.setInt(flag, 1);
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
			if(role.toLowerCase().contains("university")) {
				flag += 1;
				stmt.setInt(flag, 1);
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
				if (institute_id != null && !institute_id.equals("0")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(institute_id));
				}
			}
//			if (!institute_id.equals("0") && institute_id != null) {
//				System.err.println("\n\ninstitute_id---"+institute_id);
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
//			if (!university_id.equals("0") && university_id != null) {
//				flag += 1;
//				stmt.setInt(flag, Integer.parseInt(university_id));
//			}

			} catch (Exception e) {
		}

		return stmt;
	}
	
	
	@Override
	public List<ArrayList<String>> getstudentdetails_Report_Excel(String search, String userid,
			String institute_id, String institute_code, String state_id,
			String name,String last_name,String mother_name, String father_name,
			String dob, String email,String neet_application_no, String neet_roll_no, String neet_rank,
			String neet_percentile, String neet_marks, 
			String quota_id, String counselling_authority,String category_id,String role,String system,String university_id
			,String type_of_degree,String pg_degree,String pg_subject,String pg_intake) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		String SearchValue = GenerateQueryWhereClause_SQL(search,userid,
				institute_id,institute_code,state_id,
				name,last_name, mother_name,father_name, 
				dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
				quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			if(role.contains("NCISM")) {
			q = "select distinct ROW_NUMBER() OVER(order by sd.id) as sr_no, ir.institute_name ,ir.code as inst_code,\n"
					+ "stm.state_name, name,last_name , mother_name,	 father_name, to_char(sd.dob,'DD-MON-YYYY') as dob, sd.email,\n"
					+ "neet_application_no,neet_roll_no,neet_rank,neet_percentile,neet_marks, qm.quota, cam.counseling_authority, cas.category,\n"
					+ "sm.system_name,dm.degree_name,dm.type_of_degree\n"
					+ "FROM edu_lms_student_details sd\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
					+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
					+ "--inner join logininformation li on li.email_id=sd.email\n"
					+ "--inner join userroleinformation um on um.user_id=li.userid\n"
					+ "--inner join roleinformation ri ON ri.role_id = um.role_id\n"
					+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
					+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
					+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
					+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
					+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
					+ "where sd.id is not null "+ SearchValue +" order by sr_no asc";
			}
			if(role.contains("NCH") || role.contains("HOM")) {
				q = "select distinct ROW_NUMBER() OVER(order by sd.id) as sr_no, ir.institute_name ,ir.code as inst_code,\n"
						+ "stm.state_name, name,last_name , mother_name,	 father_name, to_char(sd.dob,'DD-MON-YYYY') as dob, sd.email,\n"
						+ "neet_application_no,neet_roll_no,neet_rank,neet_percentile,neet_marks, qm.quota, cam.counseling_authority, cas.category,\n"
						+ "sm.system_name,dm.degree_name,dm.type_of_degree\n"
						+ "FROM edu_lms_nch_student_details sd\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "inner join edu_lms_type_of_degree_mstr tod on tod.id=dm.type_of_degree\n"
						+ "--inner join logininformation li on li.email_id=sd.email\n"
						+ "--inner join userroleinformation um on um.user_id=li.userid\n"
						+ "--inner join roleinformation ri ON ri.role_id = um.role_id\n"
						+ "inner join  edu_lms_institute_reg ir on ir.id = sd.institude_userid \n"
						+ "inner join edu_lms_state_mstr stm on stm.state_id=ir.state_id\n"
						+ "inner join edu_lms_quota_mstr qm on qm.id=sd.quota\n"
						+ "inner join edu_lms_counseling_authority_mstr cam on cam.id=sd.counc_auth\n"
						+ "inner join  tb_lms_category_mstr cas on cas.id=sd.category\n"
						+ "where sd.id is not null "+ SearchValue +" order by sr_no asc";
			}

			PreparedStatement stmt = conn.prepareStatement(q);
//			if(role.contains("NCH")) {
//			stmt.setInt(1, Integer.parseInt(system));
//			stmt.setInt(2, Integer.parseInt(university_id));
//			stmt.setInt(3, Integer.parseInt(institute_id));
//			}
			stmt = setQueryWhereClause_SQL(stmt, search, userid,
					institute_id,institute_code,state_id,
					name,last_name, mother_name,father_name, 
					dob,email,neet_application_no, neet_roll_no, neet_rank, neet_percentile, neet_marks,
					quota_id,counselling_authority,category_id,role,system,university_id,type_of_degree,pg_degree,pg_subject,pg_intake);
			
			
			
			System.err.println("\n\n stmt===========" + stmt);
			
			
			ResultSet rs = stmt.executeQuery();
			
		
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("sr_no"));// 0
				alist.add(rs.getString("institute_name"));// 1
				alist.add(rs.getString("inst_code"));// 2
				alist.add(rs.getString("state_name"));// 3
				alist.add(rs.getString("name"));// 4
				alist.add(rs.getString("last_name"));// 5
				alist.add(rs.getString("mother_name"));// 6
				alist.add(rs.getString("father_name"));// 7
				alist.add(rs.getString("dob"));// 8
				alist.add(rs.getString("email"));// 9
				alist.add(rs.getString("neet_application_no"));// 10
				alist.add(rs.getString("neet_roll_no"));// 11
				alist.add(rs.getString("neet_rank"));// 12
				alist.add(rs.getString("neet_percentile"));// 13
				alist.add(rs.getString("neet_marks"));// 14
				alist.add(rs.getString("quota"));// 15
				alist.add(rs.getString("counseling_authority"));// 16
				alist.add(rs.getString("category"));// 17
//				alist.add(rs.getString("type_of_degree"));// 18
				

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
	
	
}