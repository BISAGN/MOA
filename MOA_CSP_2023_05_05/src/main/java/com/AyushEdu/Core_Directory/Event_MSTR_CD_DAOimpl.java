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
public class Event_MSTR_CD_DAOimpl implements Event_MSTR_CD_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void Insert_event_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_tt_event_mstr (id,event_name,event_date,holiday,created_by,created_dt,modified_by,modified_dt,institute_id)\n"
	        		 + "SELECT id,event_name,event_date,holiday,created_by,created_dt,modified_by,modified_dt,institute_id\n"
	        		 + "FROM dblink('myconn','Select * from edu_tt_event_mstr where id="+id+"') AS t(  id integer, \n"
	        		 		+ "    event_name character varying,\n"
	        		 		+ "    event_date timestamp without time zone,\n"
	        		 		+ "    holiday integer,\n"
	        		 		+ "    modified_by character varying, \n"
	        		 		+ "    modified_dt timestamp without time zone,\n"
	        		 		+ "    created_by character varying, \n"
	        		 		+ "    created_dt timestamp without time zone,\n"
	        		 		+ "    created_role character varying,\n"
	        		 		+ "    institute_id integer)");   
	         
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
	public Boolean Update_event_Mstr_Data(int id, String event_name,  Date event_date,int holiday, String modified_by, Date modified_dt) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_tt_event_mstr\n"
	         		+ "	SET event_name=?,event_date=?,holiday=?,  modified_by=?, modified_dt=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, event_name);
	         pstmt.setDate(2, new java.sql.Date(event_date.getTime()));
	         pstmt.setInt(3, holiday);
	      
	       
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
	public Boolean Delete_event_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_tt_event_mstr m where id="+id+" ");  
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
