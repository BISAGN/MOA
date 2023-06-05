package com.AyushEdu.Core_Directory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.velocity.runtime.directive.Parse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Course_Master_CD_DAOimpl implements Course_Master_CD_DAO {
	
		@Autowired
		private SessionFactory sessionFactory;
		@Autowired
		private DataSource dataSource;
		
		public void Insert_Course_Mstr_Data(int id){
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
		         
		         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_course_mstr (id,course_name,status,created_by,created_date,modified_by,modified_date,course_code,type_of_content_id)  \n"
		         		+ "SELECT id,course_name,status,created_by,created_date,modified_by,modified_date,course_code,type_of_content_id\n"
		         		+ "FROM dblink('myconn','Select * from edu_lms_course_mstr where id="+id+"') AS t(id integer, course_name character varying,\n"
		         		+ "status character varying,created_by character varying ,\n"
		         		+ " created_date timestamp without time zone, modified_by character varying ,\n"
		         		+ " modified_date timestamp without time zone, course_code character varying, type_of_content_id integer)");   
		         
		           
		         int i=pstmt.executeUpdate();  
		         System.out.println(i+"records inserted"+pstmt);  
		     	
		         c.commit();
		         c.close();
		      } catch ( Exception e ) {
		    	  System.err.println("catch  -  "+e);
		         //System.exit(0);
		      }
		      System.out.println("Operation Done Successfully");
		   }
		
		public Boolean  Update_Course_Mstr_Data(int id,String course_name, String status,String modified_by, Date modified_date, String course_code, int type_of_content_id){
		      Connection c = null;
		      ArrayList<String> list = new ArrayList<String>();
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
		            "postgres", "postgres");
		         c.setAutoCommit(false);
//		         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp')");  
//		         ResultSet rs=stmt.executeQuery();
//		 	   	 
//		         System.out.println("Opened database successfully");
//		         
//		         while(rs.next()) {
//		        	 list.add(rs.getString("dblink_connect"));
//		        	 ///System.out.println("list----------"+list);
//		        	 
//		         }
		         
		         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_course_mstr\n"
		         		+ "	SET course_name=?,status=?, modified_by=?, modified_date=?,course_code=?,type_of_content_id=?\n"
		         		+ "	where id=?");  
		         /// ResultSet rs1 =stmt.executeQuery();
		        
		         pstmt.setString(1, course_name);
		         pstmt.setString(2, status);
		         pstmt.setString(3, modified_by);
		         pstmt.setDate(4, new java.sql.Date(modified_date.getTime()));
		         pstmt.setString(5, course_code);
		         pstmt.setInt(6,type_of_content_id);
		         pstmt.setInt(7, id);
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
		public Boolean  Delete_Courser_Mstr_Data(int id){
		      Connection c = null;
		      ArrayList<String> list = new ArrayList<String>();
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://192.168.15.143:5435/Ayush_Edu_Core_Directory",
		            "postgres", "postgres");
		         c.setAutoCommit(false);
//		         PreparedStatement stmt=c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp')");  
//		         ResultSet rs=stmt.executeQuery();
//		 	   	 
//		         System.out.println("Opened database successfully");
//		         
//		         while(rs.next()) {
//		        	 list.add(rs.getString("dblink_connect"));
//		        	 ///System.out.println("list----------"+list);
//		        	 
//		         }
		         
		         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_course_mstr m where id="+id+" ");  
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
