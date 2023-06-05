package com.AyushEdu.dao.Registration.Graduation_NCISM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.controller.Exp_Excel.Exp_Excel_Controller;
import com.AyushEdu.dao.AyushId_Directory.AyushId_DirectoryDAO;
import com.AyushEdu.dao.Registration.Admission_ConfirmationDao;


@Repository
public class Admission_Confirmation_NCISM_DaoImpl implements Admission_Confirmation_NCISMDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Admission_ConfirmationDao acd;
	
	@Autowired
	Exp_Excel_Controller exp;
	
	
	@Autowired
	AyushId_Directory_Controller directory;
	
	@Autowired
	AyushId_DirectoryDAO addao;
	
	
////////Admission Confirmation NCISM Table /////////////////

public List<Map<String, Object>> AdmissionConfirmationSearchReport1(String userid,String dob,String name,String aadhar_card,String verified_status) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String qry = "";
	String q = "";

	try {
		System.out.println("userid ::: " +userid);
		System.out.println("dob ::: " +dob);
		System.out.println("name ::: " +name);
		System.out.println("aadhar_card ::: " +aadhar_card);
		System.out.println("verified_status ::: " +verified_status);
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		
		
		if (name != "" && name != null && name != "null" &&  !name.equals("")) {
			qry += " and upper(name) like  ? ";
		}
		
		if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" &&  !dob.equals("")) {
			qry += " and upper(to_char(dob,'DD/MM/YYYY')) like  ? ";
		}
		
		if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" &&  !aadhar_card.equals("")) {
			qry += " and upper(pd.pers_aadhar_no) like  ? ";
		}
		
		
		
		System.err.println("verified_status----------->      "+verified_status);
		if (verified_status != "" && verified_status != null && verified_status != "null" &&  !verified_status.equals("")) {
			qry += " and cast(verified_status as character varying) = ? ";
			
			if (verified_status.equals("-1")) {
				qry +=	"and sd.late_admission_status in (?)";
			}else {
				qry +=	"and sd.late_admission_status in (?,?)";
			}
		}

		System.err.println("qry---after------->  "+qry);
		
		
		if (qry == "") {
			q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id \n"
					+ "from edu_lms_student_details sd\n"
					+ "inner join  edu_ncism_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
					+ "inner join  logininformation li on li.email_id = sd.email \n"
					+ "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id \n"
					+ "and li.institute_id=(select institute_id from logininformation where userid=?) \n"
					+ "where pd.pers_aadhar_no is not null and pd.status = 1  and verified_status = -1 and sd.late_admission_status in (0)";
			
		} else {
			
			q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
					+ "from edu_lms_student_details sd\n"
					+ "inner join  edu_ncism_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
					+ "inner join  logininformation li on li.email_id = sd.email \n"
					+ "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id \n"
					+ "and li.institute_id=(select institute_id from logininformation where userid=?)\n"
					+ "where pd.pers_aadhar_no is not null and pd.status = 1 \n"
					+ qry;
		}
		
		stmt = conn.prepareStatement(q);

		int j = 1;
		
		if (userid != "" && userid != null && userid != "null" &&  !userid.equals("")) {
			stmt.setInt(j,Integer.parseInt(userid));
			j += 1;
		}
		
		if (name != "" && name != null && name != "null" &&  !name.equals("")) {
			stmt.setString(j,"%"+name.toUpperCase()+"%");
			j += 1;
		}
		
		if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" &&  !dob.equals("")) {
			stmt.setString(j,"%"+dob.toUpperCase()+"%");
			j += 1;
		}
		
		if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" &&  !aadhar_card.equals("")) {
			stmt.setString(j,"%"+aadhar_card.toUpperCase()+"%");
			j += 1;
		}
		
		if (verified_status != "" && verified_status != null && verified_status != "null" &&  !verified_status.equals("")) {
			stmt.setString(j, verified_status);
			j += 1;
			
			if (verified_status.equals("-1")) {
				stmt.setInt(j, 0);
				j += 1;
			}else {
				stmt.setInt(j, 0);
				j += 1;
				stmt.setInt(j, 7);
				j += 1;
			}
		}
		System.err.println("//////////////stmt///////////--------->    "+stmt);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();
		int countFunctionview = 1;
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			String chekboxaction="";
			
			String st =	rs.getString(1) ;
			
			String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
			+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> "
					+ "<input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" 
							+ "<input type='hidden' id='p_id"+rs.getObject(1)+"' value='"+rs.getObject(7)+"'>" ;

			
			 String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox; 
	//end
		columns.put("chekboxaction", chekboxaction);
		
		
		
		String f1 = "";
		String ul = "";
		String action = "";
		
		ul += "<ul class='buttons-group mainbtn action daobtn'>";
		f1 = "<li><a class='main-btn dark-btn btn-hover btn-sm veiwOnclick' value='VIEW' title='View Data' >"
				+"<input type='hidden' id='viewID" + countFunctionview + "' value=" + rs.getObject(7)
				+ "><i class='lni lni-eye'></i></a> </li>";

		
		ul +=  f1;
		ul += "</ul>";

		action = ul;
		countFunctionview+=1;
		
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
	System.err.println("list---------->   "+list);
	return list;
}

public String Approve_From_Admission_Confirmation_Student_Data1(String a,String username,HttpSession session) {
	String[] id_list = a.split(":");
	Connection conn = null;
	int out = 0;
	String q="";
	String freg = "";
	String maxAID = "";
	
 //end
 	try {
 		
 		String role = session.getAttribute("role").toString();
 		
		Session sessionHQL = this.sessionFactory.openSession();
		
		conn = dataSource.getConnection();
		 
		PreparedStatement stmt = null;
		
		String InstuserId = session.getAttribute("userId_for_jnlp").toString();
	 
			for (int i = 0; i < id_list.length; i++) {
				
				
//			Moved to final submit of student
				
//				maxAID = addao.getAyushID(InstuserId,session);
				
				int id = Integer.parseInt(id_list[i]);
				Transaction  tx= sessionHQL.beginTransaction();
				EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, id);
				sd.setVerified_status(0);
				sd.setFee_paid_status(0);
				
//				if ( sd.getAyush_id() == null || sd.getAyush_id().equals("null") || sd.getAyush_id().equals("")) {
//					sd.setAyush_id(maxAID);
//				}
				
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					out = 1;
					
					EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, id);
					
					int status = sd2.getVerified_status();
					
					String userid = get_student_userid_by_excel_id_ncism(sd2.getEmail());
					
					System.err.println("status--->      "+status);
				
					if(status == 0) {
						freg="Admission verified Successfully";
						String roleupdate =  acd.get_StudentRoleupdateatverify(role, String.valueOf(id));
						String notimsg="Your Admission is Verifyed by Institute, You Can Pay Your Fees";
						common.Notification(notimsg, userid, sessionFactory, session);
					}
					else {
						freg="Admission Not verified";
					}
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

	return freg;
	}


public String Reject_From_Admission_Confirmation_Student_Data1(String a,String username,String userId_reject,HttpSession session) {
	String[] id_list = a.split(":");
	Connection conn = null;
	int out = 0;
	String q="";
	
	 
	try {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction  tx= sessionHQL.beginTransaction();
		conn = dataSource.getConnection();
		 
		PreparedStatement stmt = null;
	 
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
			  	
//				EDU_LMS_STUDENT_DETAILS sd =  new EDU_LMS_STUDENT_DETAILS();
				EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, id);
					sd.setId(id);
					sd.setVerified_status(2);
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					out = 1;
					
	EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, id);
					
					int status = sd2.getVerified_status();
					
					String userid = get_student_userid_by_excel_id_ncism(sd2.getEmail());
					
					System.err.println("status--->      "+status);
				
					if(status == 2) {
						q="Admission Added to Pending List Successfully";
						
						String notimsg="Your Admission is Rejected By Commission";
						
						common.Notification(notimsg, userid, sessionFactory, session);
					}
					else {
						q="Admission Not Add to Pending List";
					}
					
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

	return q;
}


public String Enable_to_Edit_Admission_Student_NCISM_Data(String a,String username,HttpSession session) {
	String[] id_list = a.split(":");
	Connection conn = null;
	int out = 0;
	String q="";
	String msg = "";
	
	
	System.err.println("aaaaaa  ---------->    "+a);
	
 //end
 	try {
		Session sessionHQL = this.sessionFactory.openSession();
		
		conn = dataSource.getConnection();
		 
		PreparedStatement stmt = null;
	 
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				Transaction  tx= sessionHQL.beginTransaction();
				EDU_NCISM_REG_GRADU_PERSONAL_DTLS sd = (EDU_NCISM_REG_GRADU_PERSONAL_DTLS) sessionHQL .get(EDU_NCISM_REG_GRADU_PERSONAL_DTLS.class, id);
				
				sd.setStatus(0);
					sessionHQL.update(sd);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					msg="Student Can Change The Details";
					out = 1;
					
					String userid = String.valueOf(sd.getP_id() ) ;
					
					String notimsg="You Can Edit Your Details.";
					
					common.Notification(notimsg, userid, sessionFactory, session);
					
					
				}
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		msg="Student Can Not Change The Details";
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
public List<Map<String, Object>> Late_AdmissionConfirmation_NCISMSearchReport(String userid, String dob, String name,
		String aadhar_card, String verified_status, String late_admission_status, String role) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String qry = "";
	String join_inst = "";
	
	String q = "";

	try {
		System.out.println("userid ::: " +userid);
		System.out.println("dob ::: " +dob);
		System.out.println("name ::: " +name);
		System.out.println("aadhar_card ::: " +aadhar_card);
		System.out.println("verified_status ::: " +verified_status);
		System.out.println("late_admission_status ::: " +late_admission_status);
		System.out.println("role ::: " +role);
		
//		,String role
		
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
	
		System.err.println("verified_status----------->  "+verified_status);
		
		if (role.toUpperCase().equals("INSTITUTE_NCISM")) {
			join_inst=  "inner join  edu_lms_institute_reg ir on ir.id = li.institute_id \n"
					+ "and li.institute_id=(select institute_id from logininformation where userid=?) \n";
		}
		
		if (name != "" && name != null && name != "null" &&  !name.equals("")) {
			qry += " and upper(name) like  ? ";
		}
		
		if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" &&  !dob.equals("")) {
			qry += " and upper(to_char(dob,'DD/MM/YYYY')) like  ? ";
		}
		
		if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" &&  !aadhar_card.equals("")) {
			qry += " and upper(pd.pers_aadhar_no) like  ? ";
		}
		
		if (verified_status != "" && verified_status != null && verified_status != "null" &&  !verified_status.equals("")) {
			qry += " and cast(verified_status as character varying) = ? ";
		}
		
		if (late_admission_status != "" && late_admission_status != null && late_admission_status != "null" &&  !late_admission_status.equals("")) {
			qry += " and cast(late_admission_status as character varying) = ? ";
		}
		
		
		if (qry == "") {
			q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card,verified_status,pd.id as p_id\n"
					+ "from edu_lms_student_details sd\n"
					+ "inner join  edu_ncism_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
					+ "inner join  logininformation li on li.email_id = sd.email \n"
					+ join_inst
					+ "where pd.pers_aadhar_no is not null and pd.status = 1 and sd.verified_status = -1  and sd.late_admission_status = 1 ";
		} else {
			q = "select DISTINCT sd.id, name,to_char(dob,'DD-MON-YYYY') as dob,email,pd.pers_aadhar_no as aadhar_card, verified_status,pd.id as p_id\n"
					+ "from edu_lms_student_details sd\n"
					+ "inner join  edu_ncism_reg_gradu_personal_dtls pd on pd.pers_email = sd.email \n"
					+ "inner join  logininformation li on li.email_id = sd.email\n"
					+join_inst
					+ "where pd.pers_aadhar_no  is not null and  pd.status = 1 \n"
					+ qry;
		}
	
		stmt = conn.prepareStatement(q);
		
		int j = 1;

		if (role.toUpperCase().equals("INSTITUTE_NCISM")) {
			if (userid != "" && userid != null && userid != "null" &&  !userid.equals("")) {
				stmt.setInt(j,Integer.parseInt(userid));
				j += 1;
			}
		}
		
		if (name != "" && name != null && name != "null" &&  !name.equals("")) {
			stmt.setString(j,"%"+name.toUpperCase()+"%");
			j += 1;
		}
		
		if (dob != "" && !dob.equals("DD/MM/YYYY") && dob != null && dob != "null" &&  !dob.equals("")) {
			stmt.setString(j,"%"+dob.toUpperCase()+"%");
			j += 1;
		}
		
		if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" &&  !aadhar_card.equals("")) {
			stmt.setString(j,"%"+aadhar_card.toUpperCase()+"%");
			j += 1;
		}
		
		if (verified_status != "" && verified_status != null && verified_status != "null" &&  !verified_status.equals("")) {
			stmt.setString(j, verified_status);
			j += 1;
		}
		
		if (late_admission_status != "" && late_admission_status != null && late_admission_status != "null" &&  !late_admission_status.equals("")) {
			stmt.setString(j, late_admission_status);
			j += 1;
		}
		
		System.err.println( "stmt-------->   "+stmt  );
		ResultSet rs = stmt.executeQuery();
		
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();
		int countFunctionview = 1;
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			String chekboxaction="";
			
			String st =	rs.getString(1) ;
			
			String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
			+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> "
					+ "<input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" 
			+ "<input type='hidden' id='p_id"+rs.getObject(1)+"' value='"+rs.getObject(7)+"'>" ;
			
			 String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
			 
		     chekboxaction+=Checkbox; 
	//end
		columns.put("chekboxaction", chekboxaction);
		
		String f1 = "";
		String ul = "";
		String action = "";
		
		ul += "<ul class='buttons-group mainbtn action daobtn'>";
		f1 = "<li><a class='main-btn dark-btn btn-hover btn-sm veiwOnclick' value='VIEW' title='View Data'>"
				+"<input type='hidden' id='viewID" + countFunctionview + "' value=" + rs.getObject(7)
				+ "><i class='lni lni-eye'></i></a> </li>";
		ul +=  f1;
		ul += "</ul>";

		action = ul;
		countFunctionview+=1;
		
		columns.put("action", action);
			list.add(columns);
		}
		rs.close();
		stmt.close();
		conn.close();
	
	}catch (SQLException e) {
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
	System.err.println("list---------->   "+list);
	
	return list;
}

@Override
public String Approve_Student_Admission_By_Commission_NCISM_DATA(String id,HttpSession session) {
	Connection conn = null;
	String msg = "";
 	try {
		Session sessionHQL = this.sessionFactory.openSession();
		conn = dataSource.getConnection();
				Transaction  tx= sessionHQL.beginTransaction();
				EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
				sd.setLate_admission_status(7);
				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
		
		EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
		
		int status = sd2.getLate_admission_status();
		
		String userid = get_student_userid_by_excel_id_ncism(sd2.getEmail());
		
		
		System.err.println("status---"+status);
	
		if(status == 7) {
			msg="Admission Approved Successfully";
			
			String notimsg="Your Admission is Approved By Commission";
			common.Notification(notimsg, userid, sessionFactory, session);
			
		}
		else {
			msg="Admission Not Approved";
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
public String Reject_Approve_Student_Admission_By_Commission_NCISM_Data(String id ,String reject_remarks, HttpSession session) {
	Connection conn = null;
	String msg = "";
 	try {
		Session sessionHQL = this.sessionFactory.openSession();
		conn = dataSource.getConnection();
				Transaction  tx= sessionHQL.beginTransaction();
				EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
				sd.setLate_admission_status(2);
				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
		
		EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
		
		int status = sd2.getLate_admission_status();
		
		String userid = get_student_userid_by_excel_id_ncism(sd2.getEmail());
		
		String pid = get_student_to_set_remarksby_ncism(userid,reject_remarks);
		
		
		
		System.err.println("status---"+status);
	
		if(status == 2) {
			msg="Admission Rejected Successfully";
			
			String notimsg="Your Admission is Rejected By Commission";
			
			common.Notification(notimsg, userid, sessionFactory, session);
		}
		else {
			msg="Admission Not Rejected";
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
public String Forward_Student_To_Commission_NCISM_Data(String id,HttpSession session) {
	Connection conn = null;
	String msg = "";
 	try {
 		
		Session sessionHQL = this.sessionFactory.openSession();
		conn = dataSource.getConnection();
				Transaction  tx= sessionHQL.beginTransaction();
				EDU_LMS_STUDENT_DETAILS sd = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
				sd.setLate_admission_status(6);
				sd.setVerified_status(-1);
				sd.setFee_paid_status(0);
				sessionHQL.update(sd);
				sessionHQL.flush();
				sessionHQL.clear();
		tx.commit();
		
		EDU_LMS_STUDENT_DETAILS sd2 = (EDU_LMS_STUDENT_DETAILS) sessionHQL .get(EDU_LMS_STUDENT_DETAILS.class, Integer.parseInt(id));
		
		int status = sd2.getLate_admission_status();
		
		String userid = get_student_userid_by_excel_id_ncism(sd2.getEmail());
		
		System.err.println("status--->>>    "+status);
		
		
		
//		if(status == 1) {
//			msg="Status Updated";
//		}
//		if(status != 0) {
//			msg="Status Not Updated";
//		}
		
		if(status == 6) {
			msg="Forwarded to Commission Successfully";
			
			String notimsg="Your Admission is Forwarded To Commission";
			
			common.Notification(notimsg, userid, sessionFactory, session);
		}
		else {
			msg="Forward to Commission Un-Successful";
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

public String get_student_userid_by_excel_id_ncism(String id) {
	String userid = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sq1 = "select p_id from edu_ncism_reg_gradu_personal_dtls pd \n" + "where  pd.pers_email= ?";
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

public String get_student_to_set_remarksby_ncism(String userid,String reject_remarks) {
	
	String p_id = "";
	Connection conn = null;
	String msg ="";
	
	try {
		conn = dataSource.getConnection();
		String sq1 = "select id from edu_ncism_reg_gradu_personal_dtls pd \n" 
		+ "where  pd.p_id= ?";
		PreparedStatement stmt = conn.prepareStatement(sq1);
		stmt.setInt(1, Integer.parseInt(userid));
		ResultSet rs = stmt.executeQuery();
		String str1 = "";
		while (rs.next()) {
			p_id = rs.getString("id");// 0
		}
		rs.close();
		
		String sq2 = "update edu_ncism_reg_gradu_document_upload set remarks=? where p_id=?";
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

public ArrayList<ArrayList<String>> get_StudentName_by_NCISM_Reject_DATA(String id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
    	
    	String 	sq1="select name from edu_lms_student_details pd where id= ?";
    	
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

}
