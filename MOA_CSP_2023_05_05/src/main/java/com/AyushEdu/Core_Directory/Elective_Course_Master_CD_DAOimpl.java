package com.AyushEdu.Core_Directory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Elective_Course_Master_CD_DAOimpl implements Elective_Course_Master_CD_DAO {

	@Override
	public void Insert_Ele_Course_Mstr_Data(int id) {
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
	        	/// System.out.println("list----------"+list);
	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_ele_course_mstr (id,course_name,status,created_by,created_date,modified_by,modified_date,upload_img,degree_id,course_description,semester_id,demo_video)  \n"
	         		+ "SELECT id,course_name,status,created_by,created_date,modified_by,modified_date,upload_img,degree_id,course_description,semester_id,demo_video\n"
	         		+ "FROM dblink('myconn','Select * from edu_lms_ele_course_mstr where id="+id+"') AS t( id integer,\n"
	         				+ "    course_name character varying ,\n"
	         				+ "    status character varying ,\n"
	         				+ "    created_by character varying,\n"
	         				+ "    created_date timestamp without time zone,\n"
	         				+ "    modified_by character varying,\n"
	         				+ "    modified_date timestamp without time zone,\n"
	         				+ "    upload_img character varying ,\n"
	         				+ "    degree_id integer,\n"
	         				+ "    course_description character varying ,\n"
	         				+ "    semester_id integer,\n"
	         				+ "    demo_video character varying)");   
	         
	           
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

	@Override
	public Boolean Update_Ele_Course_Mstr_Data(int id,String  course_name,String course_description,String upload_img,String degree_id,String semester_id,String demo_video, String status
		, String modified_by, Date modified_date) {
	
	 
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_ele_course_mstr\n"
	         		+ "	SET course_name=?, upload_img=?, status=?,  modified_by=?, modified_date=?, degree_id=?, course_description=?,semester_id=?,demo_video=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, course_name);
	         pstmt.setString(2, upload_img);
	         pstmt.setString(3, status);
	         pstmt.setString(4, modified_by);
	         pstmt.setDate(5, new java.sql.Date(modified_date.getTime()));
	         pstmt.setInt(6, Integer.parseInt(degree_id));
	         pstmt.setString(7, course_description);
	         pstmt.setInt(8, Integer.parseInt(semester_id));
	         pstmt.setString(9, demo_video);
	         pstmt.setInt(10, id);
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

	@Override
	public Boolean Delete_Ele_Coursee_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_ele_course_mstr m where id="+id+" ");  
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
