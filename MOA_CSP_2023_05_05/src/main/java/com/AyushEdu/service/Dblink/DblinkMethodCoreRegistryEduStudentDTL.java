//package com.AyushEdu.service.Dblink;
//
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
//public class DblinkMethodCoreRegistryEduStudentDTL {
//	
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
//	
//	
//
//	
//		@Scheduled(initialDelay = 2000, fixedRate = 3000)
//		public void scheduleFixedRateTask() throws ClassNotFoundException {
//
//			String q = null;
//			Connection conn = null;
//
//
//			try {
//
//				Class.forName("org.postgresql.Driver");
//
//				conn = DriverManager.getConnection(DRIVER,
//						usernameRepo,passwordRepo);
//
//				conn.setAutoCommit(false);
//
//				q = "insert into edu_lms_student_details (id, ayush_id, name, dob, aadhar_card, email, mobile_no, created_by, created_date, modified_by, modified_date, institude_id, admission_date, system, degree, enrollment_no, upload_date, gender, semester)\n"
//						+ "\n"
//						+ "					(select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_student_details')\n"
//						+ "	AS t1(id integer,ayush_id character varying,name character varying,dob character varying,aadhar_card character varying , email character varying,mobile_no character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,institude_id character varying,admission_date character varying,system integer, degree integer, enrollment_no character varying, upload_date character varying, gender character varying, semester character varying))\n"
//						+ "as tt where tt.id not in (select id from edu_lms_student_details))";
//
//				PreparedStatement stmt = conn.prepareStatement(q);
//				stmt.executeUpdate();
//				conn.commit();
//
//				q = "UPDATE edu_lms_student_details as ec\n"
//						+ "						SET ayush_id = dbl.ayush_id,name = dbl.name,dob = dbl.dob,aadhar_card = dbl.aadhar_card ,email = dbl.email,mobile_no = dbl.mobile_no,created_by = dbl.created_by ,created_date = dbl.created_date ,modified_by = dbl.modified_by,modified_date = dbl.modified_date, institude_id = dbl.institude_id,admission_date = dbl.admission_date ,system = dbl.system, degree = dbl.degree, enrollment_no = dbl.enrollment_no, upload_date = dbl.upload_date,gender = dbl.gender, semester = dbl.semester\n"
//						+ "						from (select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_student_details')\n"
//						+ "						t1(id integer,ayush_id character varying,name character varying,dob character varying,aadhar_card character varying , email character varying,mobile_no character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,institude_id character varying,admission_date character varying,system integer, degree integer, enrollment_no character varying, upload_date character varying, gender character varying, semester character varying ))\n"
//						+ "						as tt where tt.id in (select id from edu_lms_student_details)) as dbl\n"
//						+ "						where ec.id = dbl.id";
//
//				stmt = conn.prepareStatement(q);
//
//				stmt.executeUpdate();
//				conn.commit();
//
//				q = "DELETE from edu_lms_student_details as dc\n"
//						+ "						where dc.id not in ((SELECT t1.id FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_student_details')\n"
//						+ "						AS t1(id integer,ayush_id character varying,name character varying,dob character varying,aadhar_card character varying , email character varying,mobile_no character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,institude_id character varying,admission_date character varying,system integer, degree integer, enrollment_no character varying, upload_date character varying, gender character varying, semester character varying ))\n"
//						+ "						)";
//
//				stmt = conn.prepareStatement(q);
//
//				stmt.executeUpdate();
//				conn.commit();
//
//				
//				stmt.close();
//				conn.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//		}
//
//}
