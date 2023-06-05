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
public class Module_Mstr_CD_Daoimpl implements Module_Mstr_CD_Dao{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	@Override
	public void Insert_Module_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_module_mstr (id,module_name,status,created_by,created_date,modified_by,modified_date,system_id,degree_id,course_id)  \n"
	         		+ "SELECT id,module_name,status,created_by,created_date,modified_by,modified_date,system_id,degree_id,course_id\n"
	         		+ "FROM dblink('myconn','Select * from edu_lms_module_mstr where id="+id+"') AS t(id integer,\n"
	         				+ "    module_name character varying,\n"
	         				+ "    status character varying,\n"
	         				+ "    created_by character varying, \n"
	         				+ "    created_date timestamp without time zone,\n"
	         				+ "    modified_by character varying, \n"
	         				+ "    modified_date timestamp without time zone,\n"
	         				+ "    system_id integer,\n"
	         				+ "    degree_id integer,\n"
	         				+ "    course_id integer)");   
	         
	           
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
	public Boolean Update_Module_Mstr_Data(int id, String module_name, int system_id, int degree_id, int course_id, String status
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_module_mstr\n"
	         		+ "	SET module_name=?, system_id=?,degree_id=?,course_id=?, status=?,  modified_by=?, modified_date=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, module_name);
	         pstmt.setInt(2, system_id);
	         pstmt.setInt(3, degree_id);
	         pstmt.setInt(4, course_id);
	         pstmt.setString(5, status);
	         pstmt.setString(6, modified_by);
	         pstmt.setDate(7, new java.sql.Date(modified_date.getTime()));
	        
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
	@Override
	public Boolean Delete_Module_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_module_mstr m where id="+id+" ");  
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


