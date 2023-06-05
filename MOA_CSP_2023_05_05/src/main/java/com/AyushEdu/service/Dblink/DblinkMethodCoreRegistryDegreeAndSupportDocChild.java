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
//
//public class DblinkMethodCoreRegistryDegreeAndSupportDocChild {
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
//			q = "insert into tb_nch_degree_and_support_doc_child (id, doc_name, doc_id, upload_doc, created_by, created_date, modified_by, modified_date, p_id)\n"
//					+ "\n"
//					+ "					(select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_degree_and_support_doc_child')\n"
//					+ "	AS t1(id integer, doc_name character varying, doc_id integer, upload_doc character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, p_id integer)\n"
//					+ "                                   ) as t2)";
//
//			PreparedStatement stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "UPDATE tb_nch_degree_and_support_doc_child as ec\n"
//					+ "						SET doc_name = dbl.doc_name, doc_id = dbl.doc_id, upload_doc = dbl.upload_doc, created_by = dbl.created_by, created_date = dbl.created_date, modified_by = dbl.modified_by, modified_date = dbl.modified_date, p_id = dbl.p_id\n"
//					+ "						from (select * from (SELECT * FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_degree_and_support_doc_child')\n"
//					+ "						t1(id integer, doc_name character varying, doc_id integer, upload_doc character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, p_id integer))\n"
//					+ "						as tt where tt.id in (select id from tb_nch_degree_and_support_doc_child)) as dbl\n"
//					+ "						where ec.id = dbl.id";
//
//			stmt = conn.prepareStatement(q);
//
//			stmt.executeUpdate();
//			conn.commit();
//
//			q = "DELETE from tb_nch_degree_and_support_doc_child as dc\n"
//					+ "						where dc.id not in ((SELECT t1.id FROM dblink('hostaddr="+hostadd+" port="+port+" dbname="+dbname+" user="+username+" password="+password+"','select * from tb_nch_degree_and_support_doc_child')\n"
//					+ "						AS t1(id integer, doc_name character varying, doc_id integer, upload_doc character varying, created_by character varying, created_date timestamp without time zone, modified_by character varying, modified_date timestamp without time zone, p_id integer))\n"
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
