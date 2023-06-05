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
public class Degree_Master_CD_DAOimpl implements Degree_Master_CD_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	
	public void Insert_Degree_Mstr_Data(int id){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_degree_mstr (id,degree_name,semester,status,created_by,created_date,modified_by,modified_date,type_of_degree,degree_code)  \n"
	         		+ "SELECT id,degree_name,semester,status,created_by,created_date,modified_by,modified_date,type_of_degree,degree_code\n"
	         		+ "FROM dblink('myconn','Select * from edu_lms_degree_mstr where id="+id+"') AS t(id integer, degree_name character varying,\n"
	         		+ "semester character varying,status character varying,created_by character varying ,\n"
	         		+ " created_date timestamp without time zone, modified_by character varying ,\n"
	         		+ " modified_date timestamp without time zone, type_of_degree integer, degree_code character varying)");   
	         
	           
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
	
	public Boolean  Update_Degree_Mstr_Data(int id, String type_of_degree, String degree_name, String semester, String degree_code, String status
			, String modified_by, Date modified_date){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_degree_mstr\n"
	         		+ "	SET degree_name=?, semester=?, status=?,  modified_by=?, modified_date=?, type_of_degree=?, degree_code=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, degree_name);
	         pstmt.setString(2, semester);
	         pstmt.setString(3, status);
	         pstmt.setString(4, modified_by);
	         pstmt.setDate(5, new java.sql.Date(modified_date.getTime()));
	         pstmt.setInt(6, Integer.parseInt(type_of_degree));
	         pstmt.setString(7, degree_code);
	         pstmt.setInt(8, id);
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
	
	public Boolean  Delete_Degree_Mstr_Data(int id){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_degree_mstr m where id="+id+" ");  
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
