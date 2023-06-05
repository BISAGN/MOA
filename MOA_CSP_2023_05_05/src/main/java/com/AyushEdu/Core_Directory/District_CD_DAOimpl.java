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
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
@Repository
public class District_CD_DAOimpl implements District_CD_DAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public void Insert_District_Mstr_Data(int id){
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
	         
		         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_district_mstr (district_id,state_id,country_id,created_by,created_date,modify_by,modify_date,status,district_name)  \n"
		         		+ "SELECT district_id,state_id,country_id,created_by,created_date,modify_by,modify_date,status,district_name\n"
		         		+ "FROM dblink('myconn','Select * from edu_lms_district_mstr where district_id="+id+"') AS t(district_id integer,state_id integer,country_id integer,\n"
		         		+ "created_by character varying,\n"
		         		+ " created_date timestamp without time zone, modify_by character varying ,\n"
		         		+ " modify_date timestamp without time zone,status character varying,district_name character varying)");   
	         
	           
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"records inserted"+pstmt);  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.err.println("catch  -----------  "+e);
	         //System.exit(0);
	      }
	      System.out.println("Operation Done Successfully");
	   }
	public Boolean  Update_District_Mstr_Data(int country_id,int state_id,String status,String district_name,String modify_by,Date modify_date,int id){
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
//	        	 country_id, state_id,status,district_name,username,new Date(),district_name,district_id
//	         }
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_district_mstr\n"
	         		+ "	SET country_id=?, state_id=?,status=?,district_name=?, modify_by=?, modify_date=?,district_id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	         pstmt.setInt(1, country_id);
	         pstmt.setInt(2, state_id);
	         pstmt.setString(3, status);
	         pstmt.setString(4, district_name);
	         pstmt.setString(5, modify_by);
	         pstmt.setDate(6, new java.sql.Date(modify_date.getTime()));
	         pstmt.setInt(7, id);
	         int i=pstmt.executeUpdate();  
	         System.out.println(i+"serving records inserted" +pstmt);  
	     	
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	 System.err.println("--==========================catch - "+e);
	        // System.exit(0);
	         return false;
	      }
	      System.out.println("Operation Done Successfully");
	      return true;
	   }
	public Boolean  Delete_District_Mstr_Data(int id){
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_district_mstr m where district_id="+id+" ");  
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
