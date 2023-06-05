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
public class Type_of_Content_CD_Daoimpl implements Type_of_Content_CD_Dao{
	
		@Autowired
		private SessionFactory sessionFactory;
		@Autowired
		private DataSource dataSource;
		@Override
		public void Insert_Type_Of_Content_Mstr_Data(int id) {
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
		         
		         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_type_of_content_mstr (id,type_of_content,status,created_by,created_date,modified_by,modified_date)\n"
		        		 + "SELECT id,type_of_content,status,created_by,created_date,modified_by,modified_date\n"
		        		 + "FROM dblink('myconn','Select * from edu_lms_type_of_content_mstr where id="+id+"') AS t( id integer,\n"
		        		 		+ "    type_of_content character varying,\n"
		        		 		+ "    status character varying,\n"
		        		 		+ "    created_by character varying, \n"
		        		 		+ "    created_date timestamp without time zone,\n"
		        		 		+ "    modified_by character varying,\n"
		        		 		+ "    modified_date timestamp without time zone)");   
		         
		           
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
		public Boolean Update_Type_Of_Content_Mstr_Data(int id, String type_of_content, String status,
				String modified_by, Date modified_date) {
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
		         
		         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_type_of_content_mstr\n"
		         		+ "	SET type_of_content=?,status=?,  modified_by=?, modified_date=?\n"
		         		+ "	where id=?");  
		         /// ResultSet rs1 =stmt.executeQuery();
		        
		         pstmt.setString(1, type_of_content);
		        
		         pstmt.setString(2, status);
		         pstmt.setString(3, modified_by);
		         pstmt.setDate(4, new java.sql.Date(modified_date.getTime()));
		        
		         pstmt.setInt(5, id);
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
		public Boolean Delete_Type_Of_Content_Mstr_Data(int id) {
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
		         
		         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_type_of_content_mstr m where id="+id+" ");  
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

