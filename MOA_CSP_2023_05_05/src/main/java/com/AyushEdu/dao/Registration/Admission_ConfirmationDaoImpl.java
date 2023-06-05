package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Nch_Controller;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Ug_Pg_Fee_Collection_Dao;

@Repository
public class Admission_ConfirmationDaoImpl implements Admission_ConfirmationDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired 
	CommonController common;
	
	@Autowired
	Exp_Excel_Nch_Controller eenchc;
	
	
	 @Autowired
	 Ug_Pg_Fee_Collection_Dao upd;

////////Admission Confirmation Table /////////////////

	public List<Map<String, Object>> AdmissionConfirmationSearchReport(String userid, String dob, String name,
			String aadhar_card, String verified_status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		String q = "";

		try {
			System.out.println("userid ::: " + userid);
			System.out.println("dob ::: " + dob);
			System.out.println("name ::: " + name);
			System.out.println("aadhar_card ::: " + aadhar_card);
			System.out.println("verified_status ::: " + verified_status);

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			System.err.println("verified_status----------->      " + verified_status);

			if (name != "" && name != null && name != "null" && !name.equals("")) {
				qry += " and upper(name) like  ? ";
			}

			if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" && !dob.equals("")) {
				qry += " and upper(to_char(dob,'DD/MM/YYYY')) like  ? ";
			}

			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				qry += " and upper(pd.pers_aadhar_no) like  ? ";
			}

			if (verified_status != "" && verified_status != null && verified_status != "null"
					&& !verified_status.equals("")) {
				qry += " and cast(verified_status as character varying) = ?";

				if (verified_status.equals("-1")) {
					qry += "and sd.late_admission_status in (?)";
				} else {
					qry += "and sd.late_admission_status in (?,?)";
				}
			}

//			27-01-23
			
			
			if (qry == "") {

				q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join  edu_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
						+ "inner join  logininformation li on li.email_id = sd.email \n"
						+ "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id \n"
						+ "and li.institute_id=(select institute_id from logininformation where userid=?) \n"
						+ "where pd.pers_aadhar_no is not null and pd.status = 1 and sd.late_admission_status in (0) and sd.verified_status = -1  ";

			} else {

				q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join  edu_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
						+ "inner join  logininformation li on li.email_id = sd.email\n"
						+ "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id\n"
						+ "and li.institute_id=(select institute_id from logininformation where userid=?)\n"
						+ "where pd.pers_aadhar_no is not null and  pd.status = 1  \n" + qry;
			}

			stmt = conn.prepareStatement(q);

			int j = 1;

			if (userid != "" && userid != null && userid != "null" && !userid.equals("")) {
				stmt.setInt(j, Integer.parseInt(userid));
				j += 1;
			}

			if (name != "" && name != null && name != "null" && !name.equals("")) {
				stmt.setString(j, "%" + name.toUpperCase() + "%");
				j += 1;
			}
			if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" && !dob.equals("")) {
				stmt.setString(j, "%" + dob.toUpperCase() + "%");
				j += 1;
			}
			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				stmt.setString(j, "%" + aadhar_card.toUpperCase() + "%");
				j += 1;
			}
			if (verified_status != "" && verified_status != null && verified_status != "null"
					&& !verified_status.equals("")) {
				stmt.setString(j, verified_status);
				j += 1;

				if (verified_status.equals("-1")) {
					stmt.setInt(j, 0);
					j += 1;
				} else {
					stmt.setInt(j, 0);
					j += 1;
					stmt.setInt(j, 7);
					j += 1;
				}
			}

			System.err.println("STMT----------->    " + stmt);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int countFunctionview = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String chekboxaction = "";

				String st = rs.getString(1);

				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)// 13
						+ "' name='cbox'  /> <input type='hidden' id='nrCHid" + rs.getObject(1) + "' value='" + st
						+ "'> " + "<input type='hidden' id='notif" + rs.getObject(1) + "' value='" + rs.getObject(5)
						+ "'>" + "<input type='hidden' id='p_id" + rs.getObject(1) + "' value='" + rs.getObject(7)
						+ "'>";

				String CheckboxId = "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)// 14
						+ "' value='" + rs.getObject(1) + "'   />";

				chekboxaction += Checkbox;
				// end
				columns.put("chekboxaction", chekboxaction);

				String f1 = "";
				String ul = "";
				String action = "";

				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				f1 = "<li><a class='main-btn dark-btn btn-hover btn-sm veiwOnclick' value='VIEW' title='View Data' >"
						+ "<input type='hidden' id='viewID" + countFunctionview + "' value=" + rs.getObject(7)
						+ "><i class='lni lni-eye'></i></a> </li>";

				ul += f1;
				ul += "</ul>";

				action = ul;
				countFunctionview += 1;

				columns.put("action", action);

				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		System.err.println("list---------->   " + list);

		return list;
	}

	public String Enable_to_Edit_Admission_Student_Data(String a, String username,HttpSession session) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q = "";
		String msg = "";
		int la = 0;

		String query = "";
		
		// end
		try {
			Session sessionHQL = this.sessionFactory.openSession();

			conn = dataSource.getConnection();
			

			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				Transaction tx = sessionHQL.beginTransaction();
//				EDU_LMS_NCH_STUDENT_DETAILS sd =  new EDU_LMS_NCH_STUDENT_DETAILS();
				TB_PERSONAL_DETAILS sd = (TB_PERSONAL_DETAILS) sessionHQL.get(TB_PERSONAL_DETAILS.class, id);
				
				sd.setStatus(0);
				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				msg = "Student Can Change The Details";
				out = 1;
				
				  String eml = sd.getPers_email();
				  query="  select late_admission_status from edu_lms_nch_student_details where email=?  limit 1";
				  
				  PreparedStatement stmt = conn.prepareStatement(query);			
				  stmt.setString(1, eml);
				  ResultSet rs = stmt.executeQuery();
				  while (rs.next()) {
					  la = rs.getInt("late_admission_status");
				  }
				  
				String userid = String.valueOf(sd.getP_id());
				
				String notimsg = "You Can Change The Details of Your Admission";
				
				if (la == 1) {
					notimsg+= ", Please Upload Your Court Order to Upload Documents";
				}
				
				common.Notification(notimsg, userid, sessionFactory, session);
				rs.close();
				stmt.close();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "Student Can Not Change The Details";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return msg;
	}

	public String Approve_From_Admission_Confirmation_Student_Data(String id,HttpSession session) {
		Connection conn = null;
		String msg = "";
		try {
			
			String role = session.getAttribute("role").toString();
			String InstuserId = session.getAttribute("userId_for_jnlp").toString();
			
			
			String maxAID = eenchc.getMaxAID(InstuserId);
			
			System.err.println("role------------>    "+role);
			
			Session sessionHQL = this.sessionFactory.openSession();
			conn = dataSource.getConnection();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));
			sd.setVerified_status(0);
			sd.setFee_paid_status(0);
			
			if ( sd.getAyush_id() == null || sd.getAyush_id().equals("null") || sd.getAyush_id().equals("")) {
				sd.setAyush_id(maxAID);
			}
			
			sessionHQL.update(sd);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();

			EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));

			int status = sd2.getVerified_status();

			System.err.println("status---" + status);
			String userid = get_student_userid_by_excel_id_nch(sd.getEmail());

			if (status == 0) {
				msg = "Admission Verify Successfully";
				
				String roleupdate =  get_StudentRoleupdateatverify(role,id);
				
				String notimsg="Your Admission is Verifyed by Institute, You Can Pay Your Fees";
				common.Notification(notimsg, userid, sessionFactory, session);
				
			} else {
				msg = "Admission Not Verify";
			}

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
		return msg;
	}

	public String Reject_From_Admission_Confirmation_Student_Data(String a, String username, String userId_reject,HttpSession session) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q = "";

		String query = "";
		
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			

			PreparedStatement stmt = null;

			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);

				EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
						.get(EDU_LMS_NCH_STUDENT_DETAILS.class, id);
				sd.setId(id);
				sd.setVerified_status(2);

				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();

				out = 1;
				
				
				conn = dataSource.getConnection();
				
				

				q = "Added to Pending List Successfully";
				
				String userid = get_student_userid_by_excel_id_nch(sd.getEmail());
				
				String notimsg="Your Admission is Added to Pending List by Institute, Please Submit Remaining Documents Before End Date ";
				common.Notification(notimsg, userid, sessionFactory, session);
				
				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			q = "Added to Pending List Failed";

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
//	if (out > 0) {
////			if(status.equals("1")) {
////			return "Approved Successfully";
////			}
//			  if(status.equals("2")) {
//				return "Rejected Successfully";
//				}
//			else
//				return "UnSuccessfully";
//		} else {
////			if(status.equals("1")) {
////				return "Approved not Successfully";
////				}
//			  if(status.equals("2")) {
//				return "Rejected not Successfully";
//				}
//			else
//			return "UnSuccessfully";
//		}
		return q;

	}

//	@Override
//	public List<Map<String, Object>> DataTableAdmission_ConfirmationDataList(int startPage, int pageLength, String Search,
//			String orderColunm, String orderType,String name,String dob,String email,String aadhar_card) {
//		String SearchValue = GenerateQueryWhereClause_SQL1(Search, name,dob,email,aadhar_card);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Connection conn = null;
//		String q = "";
//		try {
//
//			conn = dataSource.getConnection();
//			String pageL = "";
//			if (pageLength == -1) {
//				pageL = "ALL";
//			} else {
//				pageL = String.valueOf(pageLength);
//			}
//			
//			q="select id,name, to_char(dob,'DD-MON-YYYY') as dob,email,aadhar_card from edu_lms_nch_student_details where aadhar_card is not null \n"
//					+ SearchValue + " ORDER BY id "
//					+ orderType + " limit " + pageL + " OFFSET " + startPage;
//			
//			PreparedStatement stmt = conn.prepareStatement(q);
//			
//			stmt = setQueryWhereClause_SQL1(stmt, Search,name,dob,email,aadhar_card);
//			
//			System.err.println("stmt---------->    "+stmt);
//			
//			ResultSet rs = stmt.executeQuery();
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			int j = startPage;
//			
//			int countview=1;
//			
//			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				columns.put("ser", ++j);
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				
//				
//
////				String vd = "";
////				String action = "";
////				
////						vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm VIEWdetails' value='ADD' title='View Data' >\n"
////								+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value="+rs.getString("id")+"></i></a> </li></ul>";
//				
////				countview += 1;
////				
////				action = vd;
////				
////				columns.put("action", action);
//				
//				list.add(columns);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return list;
//	}
//	
//	@Override
//	public long DataTableTotalAdmission_ConfirmationTotalCount(String Search, String name,String dob,String email,String aadhar_card) {
//		String SearchValue = GenerateQueryWhereClause_SQL1(Search, name,dob,email,aadhar_card);
//		int total = 0;
//		String q = null;
//		Connection conn = null;
//		try {
//			conn = dataSource.getConnection();
//				
//			q = "select count(*) from ( select id,name,to_char(dob,'DD-MON-YYYY') as dob,email,aadhar_card from edu_lms_nch_student_details where aadhar_card is not null " +SearchValue+ ")a where id!=0 " ;
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt = setQueryWhereClause_SQL1(stmt, Search,name,dob,email,aadhar_card);
//			System.err.println("---------Statement-----------"+stmt);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				total = rs.getInt(1);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return (long) total;
//	}

//	@SuppressWarnings("null")
//	public String GenerateQueryWhereClause_SQL1(String Search,String name,String dob,String email,String aadhar_card) {
//		
//		String SearchValue = "";
//		if (Search != null && !Search.equals("")) { // for Input Filter
//			SearchValue += " and (upper(name) like ? or to_char(dob,'DD-MON-YYYY') like ?"
//					+ "or upper(email) like ? or upper(aadhar_card) like ?)";
//			System.err.println("globalllll search" + SearchValue);
//		}
//		/// advance search
//		if (!name.trim().equals("")) {
//			SearchValue += " and upper(name) like ? ";
//		}
////		if (!ayush_id.trim().equals("")) {
////			SearchValue += " and upper(sd.ayush_id) like ? ";
////		}
////		if (!gender.trim().equals("0") && gender != null ) {
////			SearchValue += " and pd.pers_gender = ? ";
////		}
//		if (!dob.equals("") && dob != "" && !dob.equals("DD/MM/YYYY")){
//			
//			System.err.println("dob---->    "+dob);
//			
//			SearchValue += " and to_char(dob,'DD/MM/YYYY')= ?";
//		}	
//		
//		if (email != null && !email.equals("")) {
//			SearchValue += " and upper(email) like ? ";
//		}
//		
//		System.err.println("aadhar_card"+aadhar_card);
//		
//		if (aadhar_card != null && !aadhar_card.equals("")) {
//			SearchValue += " and upper(aadhar_card) like ? ";
//		}
//		return SearchValue;
//	}

//	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search,String name,String dob,String email,String aadhar_card) {
//		int flag = 0;
//		try {
//			if (Search != null && !Search.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");				
//			}
//			
////			if (!name.equals("0") && name != null) {
////				flag += 1;
////				stmt.setInt(flag, Integer.parseInt(university_id));
////			}
////			if (!institute_id.equals("0") && institute_id != null) {
////				flag += 1;
////				stmt.setInt(flag, Integer.parseInt(institute_id) );
////			}
//			if (!name.equals("") && name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + name.toUpperCase() + "%");
//			}
////			if (!ayush_id.equals("") && ayush_id != null) {
////				flag += 1;
////				stmt.setString(flag, "%" + ayush_id.toUpperCase() + "%");
////			}
////			if (!gender.equals("0") && gender != null) {
////				flag += 1;
////				stmt.setString(flag,  gender);
////			}
//			if (!dob.equals("") && dob != "" && !dob.equals("DD/MM/YYYY")) {
//				flag += 1;
//				stmt.setString(flag, dob);
//			}
//			if (email != null && !email.equals("")  ) {
//				flag += 1;
//				stmt.setString(flag, "%" + email.toUpperCase() + "%");
//			}
//			
//			if (aadhar_card != null && !aadhar_card.equals("")) {
//				flag += 1;
//				stmt.setString(flag, "%" + aadhar_card.toUpperCase() + "%");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return stmt;
//	}

	@Override
	public List<Map<String, Object>> Late_AdmissionConfirmationSearchReport(String userid, String dob, String name,
			String aadhar_card, String verified_status, String late_admission_status, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		String join_inst = "";

		String q = "";

		try {
			System.out.println("userid ::: " + userid);
			System.out.println("dob ::: " + dob);
			System.out.println("name ::: " + name);
			System.out.println("aadhar_card ::: " + aadhar_card);
			System.out.println("verified_status ::: " + verified_status);
			System.out.println("late_admission_status ::: " + late_admission_status);
			System.out.println("role ::: " + role);

//					,String role

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			System.err.println("verified_status----------->  " + verified_status);

			if (role.toUpperCase().equals("INSTITUTE_NCH")) {
				join_inst = "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id \n"
						+ "and li.institute_id=(select institute_id from logininformation where userid=?) \n";
			}

			if (name != "" && name != null && name != "null" && !name.equals("")) {
				qry += " and upper(name) like  ? ";
			}

			if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" && !dob.equals("")) {
				qry += " and upper(to_char(dob,'DD/MM/YYYY')) like  ? ";
			}

			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				qry += " and upper(pd.pers_aadhar_no) like  ? ";
			}

			if (verified_status != "" && verified_status != null && verified_status != "null"
					&& !verified_status.equals("")) {
				qry += " and cast(verified_status as character varying) = ? ";
			}

			if (late_admission_status != "" && late_admission_status != null && late_admission_status != "null"
					&& !late_admission_status.equals("")) {
				qry += " and cast(late_admission_status as character varying) = ? ";
			}

			
//			27-01-23
			
			
			if (qry == "") {
				q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join  edu_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
						+ "inner join  logininformation li on li.email_id = sd.email \n" + join_inst
						+ "where pd.pers_aadhar_no is not null and pd.status = 1 and sd.verified_status = -1  and sd.late_admission_status = 1 ";
			} else {
				q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
						+ "from edu_lms_nch_student_details sd\n"
						+ "inner join  edu_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
						+ "inner join  logininformation li on li.email_id = sd.email\n" + join_inst
						+ "where pd.pers_aadhar_no is not null and  pd.status = 1 \n" + qry;
			}

			stmt = conn.prepareStatement(q);

			int j = 1;

			if (role.toUpperCase().equals("INSTITUTE_NCH")) {
				if (userid != "" && userid != null && userid != "null" && !userid.equals("")) {
					stmt.setInt(j, Integer.parseInt(userid));
					j += 1;
				}
			}

			if (name != "" && name != null && name != "null" && !name.equals("")) {
				stmt.setString(j, "%" + name.toUpperCase() + "%");
				j += 1;
			}

			if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" && !dob.equals("")) {
				stmt.setString(j, "%" + dob.toUpperCase() + "%");
				j += 1;
			}

			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				stmt.setString(j, "%" + aadhar_card.toUpperCase() + "%");
				j += 1;
			}

			if (verified_status != "" && verified_status != null && verified_status != "null"
					&& !verified_status.equals("")) {
				stmt.setString(j, verified_status);
				j += 1;
			}

			if (late_admission_status != "" && late_admission_status != null && late_admission_status != "null"
					&& !late_admission_status.equals("")) {
				stmt.setString(j, late_admission_status);
				j += 1;
			}

			System.err.println("stmt-------->   " + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int countFunctionview = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String chekboxaction = "";

				String st = rs.getString(1);

				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)// 13
						+ "' name='cbox'  /> <input type='hidden' id='nrCHid" + rs.getObject(1) + "' value='" + st
						+ "'> " + "<input type='hidden' id='notif" + rs.getObject(1) + "' value='" + rs.getObject(5)
						+ "'>" + "<input type='hidden' id='p_id" + rs.getObject(1) + "' value='" + rs.getObject(7)
						+ "'>";

				String CheckboxId = "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)// 14
						+ "' value='" + rs.getObject(1) + "'   />";

				chekboxaction += Checkbox;
				// end
				columns.put("chekboxaction", chekboxaction);

				String f1 = "";
				String ul = "";
				String action = "";

				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				f1 = "<li><a class='main-btn dark-btn btn-hover btn-sm veiwOnclick' value='VIEW' title='View Data'>"
						+ "<input type='hidden' id='viewID" + countFunctionview + "' value=" + rs.getObject(7)
						+ "><i class='lni lni-eye'></i></a> </li>";
				ul += f1;
				ul += "</ul>";

				action = ul;
				countFunctionview += 1;

				columns.put("action", action);
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.err.println("list---------->   " + list);

		return list;
	}

	public String Forward_Student_To_Commission_NCH_Data(String id,HttpSession session) {
		Connection conn = null;
		String msg = "";
		try {

			Session sessionHQL = this.sessionFactory.openSession();
			conn = dataSource.getConnection();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));
			sd.setLate_admission_status(6);
			sd.setVerified_status(-1);
			sd.setFee_paid_status(0);
			sessionHQL.update(sd);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();

			EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));

			int status = sd2.getLate_admission_status();

			System.err.println("status--->>>    " + status);
			String userid = get_student_userid_by_excel_id_nch(sd2.getEmail());

//		if(status == 1) {
//			msg="Status Updated";
//		}
//		if(status != 0) {
//			msg="Status Not Updated";
//		}

			if (status == 6) {
				msg = "Forwarded to Commission Successfully";
				
				
				
				String notimsg="Your Admission is Forwarded To Commission by Institute for Approval";
				common.Notification(notimsg, userid, sessionFactory, session);
				
			} else {
				msg = "Forward to Commission Un-Successful";
			}

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
		return msg;
	}

	@Override
	public String Approve_Student_Admission_By_Commission_NCH_DATA(String id,HttpSession session) {
		Connection conn = null;
		String msg = "";
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			conn = dataSource.getConnection();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));
			sd.setLate_admission_status(7);
			sessionHQL.update(sd);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();

			EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));
			
			int status = sd2.getLate_admission_status();
			String userid = get_student_userid_by_excel_id_nch(sd2.getEmail());
			
			System.err.println("status---" + status);

			if (status == 7) {
				msg = "Admission Approved Successfully";
				
				String notimsg="Your Admission is Approved by Commission";
				common.Notification(notimsg, userid, sessionFactory, session);
				
			} else {
				msg = "Admission Not Approved";
			}

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
		return msg;
	}

	@Override
	public String Reject_Approve_Student_Admission_By_Commission_NCH_Data(String id,String reject_remarks,HttpSession session) {
		Connection conn = null;
		String msg = "";
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			conn = dataSource.getConnection();
			Transaction tx = sessionHQL.beginTransaction();
			
			System.err.println("reject_remarks----->   "+reject_remarks);
			
			EDU_LMS_NCH_STUDENT_DETAILS sd = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));
			sd.setLate_admission_status(2);
			sessionHQL.update(sd);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();

			EDU_LMS_NCH_STUDENT_DETAILS sd2 = (EDU_LMS_NCH_STUDENT_DETAILS) sessionHQL
					.get(EDU_LMS_NCH_STUDENT_DETAILS.class, Integer.parseInt(id));

			int status = sd2.getLate_admission_status();
			
			String userid = get_student_userid_by_excel_id_nch(sd2.getEmail());
			
			String pid = get_student_to_set_remarksby_nch(userid,reject_remarks);
			
			
//			pid
			
			

			System.err.println("status---" + status);

			if (status == 2) {
				msg = "Admission Rejected Successfully";
				
				String notimsg="Your Admission is Reject by Commission";
				common.Notification(notimsg, userid, sessionFactory, session);
				
			} else {
				msg = "Admission Not Rejected";
			}

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
		return msg;
	}

	public String get_student_userid_by_excel_id_nch(String id) {
		String userid = "";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			String sq1 = "select p_id from edu_reg_gradu_personal_dtls pd \n" 
			+ "where  pd.pers_email= ?";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			while (rs.next()) {
				userid = rs.getString("p_id");// 0
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.err.println("uid-------->   " + userid);

		return userid;

	}
	
	
	public String get_student_to_set_remarksby_nch(String userid,String reject_remarks) {
		
		String p_id = "";
		Connection conn = null;
		String msg ="";
		
		try {
			conn = dataSource.getConnection();
			String sq1 = "select id from edu_reg_gradu_personal_dtls pd \n" 
			+ "where  pd.p_id= ?";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setInt(1, Integer.parseInt(userid));
			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			while (rs.next()) {
				p_id = rs.getString("id");// 0
			}
			rs.close();
			
			String sq2 = "update edu_reg_gradu_document_upload set remarks=? where p_id=?";
					PreparedStatement stmt2 = conn.prepareStatement(sq2);
					stmt2.setString(1, reject_remarks);
					stmt2.setInt(2, Integer.parseInt(p_id));
					msg = stmt2.executeUpdate() > 0 ? "1" : "0";
					String str2 = "";
			stmt.close();
			
			System.err.println("stmt---->   "+stmt2);
			System.err.println("msg-------->    "+msg);
			
			stmt2.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("uid-------->   " + p_id);
		return p_id;

	}

	public ArrayList<ArrayList<String>> get_StudentName_by_commReject_DATA(String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String 	sq1="select name from edu_lms_nch_student_details pd where id= ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(id));
	       
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        while(rs.next()){
	        	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));// 0
					alist.add(list);                           	  
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }       
	    
	    System.err.println("alist-------->   "+alist);
	   return alist;
	}
	
	

	
	
	public String get_StudentRoleupdateatverify(String role, String studentId) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
	    
	    String  msg2 ="";
	    try{
	    	
	    	Session sessionHQL = this.sessionFactory.openSession();
			conn = dataSource.getConnection();
			Transaction tx = sessionHQL.beginTransaction();
	    	
	    	List<Map<String, Object>> stu_cur_dtl = upd.GetRoleInfoFromStudentId(role,studentId);
	    	String stu_curRole = stu_cur_dtl.get(0).get("role").toString();
	    	String stu_curroled = stu_cur_dtl.get(0).get("role_id").toString();
	    	String stu_userid = stu_cur_dtl.get(0).get("userid").toString();
	    	String updroleid = "";
	    	
	    	System.err.println("role--------->   "+role);
	    	System.err.println("studentId--------->   "+studentId);
	    	System.err.println("stu_curRole--------->   "+stu_curRole);
	    	System.err.println("stu_curroled--------->   "+stu_curroled);
	    	System.err.println("stu_userid--------->   "+stu_userid);
	    	
 	if(stu_curRole.contains("ADM")) {
	    		
	    		if(stu_curroled.equals("45")) {
	    			updroleid = "25";
	    		}
	    		if(stu_curroled.equals("46")) {
	    			updroleid = "26";
	    		}
	    		
	    		String hql2 = "update UserRole set role_id=:role_id ,modified_on=:modified_on\n"
	    				+ "	 where user_id=:user_id ";
	    		
	    		Query query2 = sessionHQL.createQuery(hql2).setParameter("role_id", Integer.parseInt(updroleid)).setParameter("modified_on", new Date())
	    				.setParameter("user_id", Integer.parseInt(stu_userid));
	    		msg2 = query2.executeUpdate() > 0 ? "1" : "0";
	    		
				sessionHQL.flush();
				sessionHQL.clear();
	    	}
	        
 			tx.commit();
 	
 	
	        conn.close();
	   }catch(SQLException e){
		   e.printStackTrace();
	   }       
	    
	   return msg2;
	}
	
	

	

}
