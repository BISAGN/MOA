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
public class Term_CD_Daoimpl implements Term_CD_Dao{

	@Override
	public void Insert_Term_Mstr_Data(int id) {
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
	 	   	 
	         System.out.println("Opened database successfully"+id);
	         
	         while(rs.next()) {
	        	 list.add(rs.getString("dblink_connect"));
	        	/// System.out.println("list----------"+list);
	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_term_mstr (id,term,status,created_by,created_dt,modified_by,modified_dt,prof_name)\n"
	        		 + "SELECT id,term,status,created_by,created_dt,modified_by,modified_dt,prof_name\n"
	        		 + "FROM dblink('myconn','Select * from edu_lms_term_mstr where id="+id+"') AS t( id integer,\n"
	        		 		+ "    term character varying,\n"
	        		 		+ "    status character varying, \n"
	        		 		+ "    created_by character varying ,\n"
	        		 		+ "    created_dt timestamp without time zone,\n"
	        		 		+ "    modified_by character varying,\n"
	        		 		+ "    modified_dt timestamp without time zone,\n"
	        		 		+ "    prof_name character varying )");   
	         
	         System.out.println("query==============="+pstmt);  

	         int i=pstmt.executeUpdate();  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.err.println("catch  -  "+e);
	    	  e.printStackTrace();
	         //System.exit(0);
	      }
	      System.out.println("Operation Done Successfully");
		
	}

	@Override
	public Boolean Update_Term_Mstr_Data(int id, String term, String status,String prof_name, String modified_by,
			Date modified_dt) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_term_mstr\n"
	         		+ "	SET term=?,status=?, prof_name =?, modified_by=?, modified_dt=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, term);
	        
	         pstmt.setString(2, status);
	         pstmt.setString(3, prof_name);
	         pstmt.setString(4, modified_by);
	         pstmt.setDate(5, new java.sql.Date(modified_dt.getTime()));
	        
	         pstmt.setInt(6, id);
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
	public Boolean Delete_Term_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_term_mstr m where id="+id+" ");  
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
