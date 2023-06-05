//package com.AyushEdu.service.Dblink;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DblinkMethodCoreRegistryAddLmsTeacherDetails {
//	@Value("${spring.datasourceCoreReg.url}")
//	private String DRIVER;
//	
//	
//	@Value("${spring.datasourceRepo.username}")
//	private String usernameRepo;
//	
//	
//	@Value("${spring.datasourceRepo.password}")
//	private String passwordRepo;
//	
//	
//	@Value("${spring.datasource.username}")
//	private String username;
//	
//	
//	@Value("${spring.datasource.password}")
//	private String password;
//	
//	@Value("${spring.datasource.hostadd}")
//	private String hostadd;
//	
//	@Value("${spring.datasource.port}")
//	private String port;
//	
//	@Value("${spring.datasource.dbname}")
//	private String dbname;
//
//	
//	@Scheduled(initialDelay = 5000, fixedRate = 3000)
//	public void scheduleFixedRateTask() throws ClassNotFoundException {
//
//		String q = null;
//		Connection conn = null;
//
//
//		try {
//
//			Class.forName("org.postgresql.Driver");
//
//			conn = DriverManager.getConnection(DRIVER,usernameRepo,passwordRepo);
//
//			conn.setAutoCommit(false);
//
//			q = "insert into tb_nch_add_teacher_details (id, first_name, middle_name, last_name, gender, date_of_birth, father_name, mother_name, spouse_name, mobile_no, email, aadhar_no, pan_no, present_add_line1, present_add_line2, present_village, present_state, present_district, present_pincode, present_phn_no, per_add_line1, per_add_line2, per_village, per_state, per_district, per_pincode, per_phn_no, state_reg_no, state_board_name, date_of_reg, central_reg_no, cand_prefix, created_by, created_date, modified_by, modified_date, academic_quali, subject, ayush_id, teacher_code)\n"
//					+ "\n"
//					+ "					(select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_add_teacher_details')\n"
//					+ "	AS t1(id integer, first_name character varying, middle_name character varying, last_name character varying, gender character varying, date_of_birth timestamp without time zone, father_name character varying, mother_name character varying, spouse_name character varying, mobile_no character varying, email character varying, aadhar_no character varying, pan_no character varying, present_add_line1 character varying, present_add_line2 character varying, present_village character varying, present_state integer, present_district integer, present_pincode integer, present_phn_no character varying, per_add_line1 character varying, per_add_line2 character varying, per_village character varying, per_state integer, per_district integer, per_pincode integer, per_phn_no character varying, state_reg_no character varying, state_board_name character varying, date_of_reg timestamp without time zone, central_reg_no character varying, cand_prefix character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, academic_quali character varying, subject character varying, ayush_id character varying, teacher_code character varying)\n"
//					+ "                                   ) as t2)";
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "UPDATE tb_nch_add_teacher_details as ec\n"
//					+ "						SET first_name = dbl.first_name, middle_name = dbl.middle_name, last_name = dbl.last_name, gender = dbl.gender, date_of_birth = dbl.date_of_birth, father_name = dbl.father_name, mother_name = dbl.mother_name, spouse_name = dbl.spouse_name, mobile_no = dbl.mobile_no, email = dbl.email, aadhar_no = dbl.aadhar_no, pan_no = dbl.pan_no, present_add_line1 = dbl.present_add_line1, present_add_line2 = dbl.present_add_line2, present_village = dbl.present_village, present_state = dbl.present_state, present_district = dbl.present_district, present_pincode = dbl.present_pincode, present_phn_no = dbl.present_phn_no, per_add_line1 = dbl.per_add_line1, per_add_line2 = dbl.per_add_line2, per_village = dbl.per_village, per_state = dbl.per_state, per_district = dbl.per_district, per_pincode = dbl.per_pincode, per_phn_no = dbl.per_phn_no, state_reg_no = dbl.state_reg_no, state_board_name = dbl.state_board_name, date_of_reg = dbl.date_of_reg, central_reg_no = dbl.central_reg_no, cand_prefix = dbl.cand_prefix, created_by = dbl.created_by, created_date = dbl.created_date, modified_by = dbl.modified_by, modified_date = dbl.modified_date, academic_quali = dbl.academic_quali, subject = dbl.subject, ayush_id = dbl.ayush_id, teacher_code = dbl.teacher_code\n"
//					+ "						from (select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_add_teacher_details')\n"
//					+ "						t1(id integer, first_name character varying, middle_name character varying, last_name character varying, gender character varying, date_of_birth timestamp without time zone, father_name character varying, mother_name character varying, spouse_name character varying, mobile_no character varying, email character varying, aadhar_no character varying, pan_no character varying, present_add_line1 character varying, present_add_line2 character varying, present_village character varying, present_state integer, present_district integer, present_pincode integer, present_phn_no character varying, per_add_line1 character varying, per_add_line2 character varying, per_village character varying, per_state integer, per_district integer, per_pincode integer, per_phn_no character varying, state_reg_no character varying, state_board_name character varying, date_of_reg timestamp without time zone, central_reg_no character varying, cand_prefix character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, academic_quali character varying, subject character varying, ayush_id character varying, teacher_code character varying))\n"
//					+ "						as tt where tt.id in (select id from tb_nch_add_teacher_details)) as dbl\n"
//					+ "						where ec.id = dbl.id";
//
//			stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "DELETE from tb_nch_add_teacher_details as dc\n"
//					+ "						where dc.id not in ((SELECT t1.id FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_add_teacher_details')\n"
//					+ "						AS t1(id integer, first_name character varying, middle_name character varying, last_name character varying, gender character varying, date_of_birth timestamp without time zone, father_name character varying, mother_name character varying, spouse_name character varying, mobile_no character varying, email character varying, aadhar_no character varying, pan_no character varying, present_add_line1 character varying, present_add_line2 character varying, present_village character varying, present_state integer, present_district integer, present_pincode integer, present_phn_no character varying, per_add_line1 character varying, per_add_line2 character varying, per_village character varying, per_state integer, per_district integer, per_pincode integer, per_phn_no character varying, state_reg_no character varying, state_board_name character varying, date_of_reg timestamp without time zone, central_reg_no character varying, cand_prefix character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, academic_quali character varying, subject character varying, ayush_id character varying, teacher_code character varying))\n"
//					+ "						)";
//
//			stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			
//			stmt.close();
//			conn.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//}
