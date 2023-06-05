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
//public class DblinkMethodCoreDirectoryEduCourseMSTR {
//
//	@Value("${spring.datasourceCoreDirect.url}")
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
//			q = "insert into edu_lms_ele_course_mstr (id,course_name,status,created_by,created_date,modified_by,modified_date,upload_img,degree_id,course_description,semester_id)\r\n"
//					+ "					(select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_ele_course_mstr')\r\n"
//					+ "  AS t1(id integer, course_name character varying,status character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,upload_img character varying,degree_id integer,course_description character varying,semester_id integer))\r\n"
//					+ "					as tt where tt.id NOT in (select id from edu_lms_ele_course_mstr)) \r\n"
//					+ "";
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "UPDATE edu_lms_ele_course_mstr as ec\r\n"
//					+ "	SET course_name = dbl.course_name ,status = dbl.status ,created_by = dbl.created_by ,created_date = dbl.created_date ,modified_by = dbl.modified_by,modified_date = dbl.modified_date ,upload_img = dbl.upload_img ,  degree_id = dbl.degree_id,course_description = dbl.course_description ,semester_id = dbl.semester_id \r\n"
//					+ "	from (select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_ele_course_mstr')\r\n"
//					+ " AS t1(id integer, course_name character varying,status character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,upload_img character varying,degree_id integer,course_description character varying,semester_id integer))\r\n"
//					+ "					as tt where tt.id NOT in (select id from edu_lms_ele_course_mstr)) dbl\r\n"
//					+ " where ec.id = dbl.id";
//
//			stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "DELETE from edu_lms_ele_course_mstr as dc\r\n"
//					+ "where dc.id not in ((SELECT t1.id FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from edu_lms_ele_course_mstr')\r\n"
//					+ "AS t1(id integer, course_name character varying,status character varying,created_by character varying,created_date timestamp without time zone,modified_by character varying,modified_date timestamp without time zone,upload_img character varying,degree_id integer,course_description character varying,semester_id integer ))\r\n"
//					+ ")";
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
//
//}
