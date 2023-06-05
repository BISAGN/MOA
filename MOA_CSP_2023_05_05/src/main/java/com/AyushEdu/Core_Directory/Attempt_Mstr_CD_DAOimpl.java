package com.AyushEdu.Core_Directory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.TB_LMS_RELIGION_MSTR;

@Repository
public class Attempt_Mstr_CD_DAOimpl implements Attempt_Mstr_CD_DAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public void Insert_Attempt_Master_Data(int id){
	      Connection c = null;
	      ArrayList<String> list = new ArrayList<String>();
	      try {
	    	  
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
	         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','ayush_core_directory')");  
	         ResultSet rs=stmt.executeQuery();
	 	   	 
	         System.out.println("Opened database successfully");
	         
	         while(rs.next()) {
	        	 list.add(rs.getString("dblink_connect"));
	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_exam_tb_atttempt_mstr (id,attempt,status,created_by,created_date,modified_by,modified_date)\n"
	         		+ "SELECT id,attempt,status,created_by,created_date,modified_by,modified_date\n"
	         		+ "FROM dblink('myconn','Select * from edu_exam_tb_atttempt_mstr where id="+id+"') AS t(id integer,attempt character varying,\n"
	         		+ "status integer,created_by character varying ,\n"
	         		+ " created_date timestamp without time zone, modified_by character varying ,\n"
	         		+ " modified_date timestamp without time zone)");   
	         
	           
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"records inserted"+pstmt);  
	         System.err.println("----------------pstmt"+pstmt);
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.err.println("--------------catch  -  "+e);
	         //System.exit(0);
	      }
	      System.out.println("Operation Done Successfully");
	   }
	
	public Boolean  Update_Attempt_Master_Data(int id,String attempt, int status,String modified_by, Date modified_date){
	      Connection c = null;
	      ArrayList<String> list = new ArrayList<String>();
	      try {
	    	  Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
//	         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp')");  
//	         ResultSet rs=stmt.executeQuery();
//	 	   	 
//	         System.out.println("Opened database successfully");
//	         
//	         while(rs.next()) {
//	        	 list.add(rs.getString("dblink_connect"));
//	        	 ///System.out.println("list----------"+list);
//	        	 
//	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_exam_tb_atttempt_mstr\n"
	         		+ "	SET attempt=?,status=?, modified_by=?, modified_date=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1,attempt);
	         pstmt.setInt(2, status);
	         pstmt.setString(3, modified_by);
	         pstmt.setDate(4, new java.sql.Date(modified_date.getTime()));
	         pstmt.setInt(5, id);
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"serving records inserted" +pstmt);  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	 System.err.println("--catch - "+e);
	        // System.exit(0);
	         return false;
	      }
	      System.out.println("Operation Done Successfully");
	      return true;
	   }
	public Boolean  Delete_Attempt_Master_Data(int id){
	      Connection c = null;
	      ArrayList<String> list = new ArrayList<String>();
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
//	         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp')");  
//	         ResultSet rs=stmt.executeQuery();
//	 	   	 
//	         System.out.println("Opened database successfully");
//	         
//	         while(rs.next()) {
//	        	 list.add(rs.getString("dblink_connect"));
//	        	 ///System.out.println("list----------"+list);
//	        	 
//	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_exam_tb_atttempt_mstr m where id="+id+" ");  
	         /// ResultSet rs1 =stmt.executeQuery();
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"serving records inserted" +pstmt);  
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	 
	        // System.exit(0);
	         return false;
	      }
	      System.out.println("Operation Done Successfully");
	      return true;
	   }

	

}
