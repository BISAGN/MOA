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
public class State_CD_DaoImpl implements State_CD_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	
	public void Insert_State_Mstr_Data(int id){
		
		 System.out.println("Opened database successfully");
		
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
	         
	         
	         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_state_mstr (state_id,state_name,country_id,status,state_abbr,created_by,created_date,modify_by,modify_date)  \n"
	         		+ "SELECT state_id,state_name,country_id,status,state_abbr,created_by,created_date,modify_by,modify_date\n"
	         		+ "FROM dblink('myconn','Select * from edu_lms_state_mstr where state_id="+id+"') AS t(state_id integer, \n"
	         		+ "    state_name character varying, \n"
	         		+ "    country_id integer ,\n"
	         		+ "    created_by character varying, \n"
	         		+ "    created_date timestamp without time zone,\n"
	         		+ "    modify_by character varying, \n"
	         		+ "    modify_date timestamp without time zone,\n"
	         		+ "    status character varying ,\n"
	         		+ "    state_abbr character varying )");   
	         
	         
	 //        pstmt.setInt(1,id);
	           
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
	public Boolean  Update_State_Mstr_Data(int state_id, String state_name,int country_id,String state_abbr, String status
			, String modify_by, Date modify_date){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_state_mstr\n"
	         		+ "	SET state_name=?, country_id=?,status=?,state_abbr=?,  modify_by=?, modify_date=?\n"
	         		+ "	where state_id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	      
	         pstmt.setString(1, state_name);
	         pstmt.setInt(2, country_id);
	         
	         pstmt.setString(3, status);
	         pstmt.setString(4, state_abbr);
	         pstmt.setString(5, modify_by);
	         pstmt.setDate(6, new java.sql.Date(modify_date.getTime()));
	         pstmt.setInt(7, state_id);
	        
	       
	        
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"serving records inserted" +pstmt);  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.err.println("catch  -  "+e);
	        // System.exit(0);
	         return false;
	      }
	      System.out.println("Operation Done Successfully");
	      return true;
	   }
	

	public Boolean  Delete_State_Mstr_Data(int id){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_state_mstr m where state_id="+id+" ");  
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
