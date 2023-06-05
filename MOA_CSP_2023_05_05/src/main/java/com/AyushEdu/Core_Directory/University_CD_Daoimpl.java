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
public class University_CD_Daoimpl implements University_CD_Dao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void Insert_University_Mstr_Data(int id) {
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
		         
		         PreparedStatement pstmt=c.prepareStatement("insert into edu_lms_university_mstr (id,university_name,university_code,address,country_id,state_id,district_id,city_name,status,created_by,created_date,modified_by,modified_date,university_type,organization_id,system_id)  \n"
		         		+ "SELECT id,university_name,university_code,address,country_id,state_id,district_id,city_name,status,created_by,created_date,modified_by,modified_date,university_type,organization_id,system_id\n"
		         		+ "FROM dblink('myconn','Select * from edu_lms_university_mstr where id="+id+"') AS t(  id integer,\n"
		         				+ "    university_name character varying, \n"
		         				+ "    university_code character varying ,\n"
		         				+ "    address character varying, \n"
		         				+ "    country_id integer,\n"
		         				+ "    state_id integer,\n"
		         				+ "    district_id integer,\n"
		         				+ "    city_name character varying, \n"
		         				+ "    status character varying, \n"
		         				+ "    created_by character varying,\n"
		         				+ "    created_date timestamp without time zone,\n"
		         				+ "    modified_by character varying, \n"
		         				+ "    modified_date timestamp without time zone,\n"
		         				+ "    university_type integer,\n"
		         				+ "    organization_id integer,\n"
		         				+ "    system_id integer)");   
		         
		           
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
	public Boolean Update_University_Mstr_Data(int id, String university_name, String university_code, String address, int country_id,int state_id,int district_id,String city_name,int university_type,int organization_id,int system_id
			,String status, String modified_by, Date modified_date) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("UPDATE edu_lms_university_mstr\n"
	         		+ "	SET university_name=?, university_code=?,address=?,country_id=?,state_id=?,district_id=?,city_name=?,university_type=?,organization_id=?,system_id =?,status=?,  modified_by=?, modified_date=?\n"
	         		+ "	where id=?");  
	         /// ResultSet rs1 =stmt.executeQuery();
	        
	         pstmt.setString(1, university_name);
	         pstmt.setString(2, university_code);
	         pstmt.setString(3, address);
	         pstmt.setInt(4, country_id);
	         pstmt.setInt(5, state_id);
	         pstmt.setInt(6, district_id);
	         pstmt.setString(7, city_name);
	         pstmt.setInt(8, university_type);
	         pstmt.setInt(9, organization_id);
	         pstmt.setInt(10, system_id);
	         pstmt.setString(11, status);
	         pstmt.setString(12, modified_by);
	         pstmt.setDate(13, new java.sql.Date(modified_date.getTime()));
	        
	         pstmt.setInt(14, id);
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
	public Boolean Delete_University_Mstr_Data(int id) {
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
	         
	         PreparedStatement pstmt=c.prepareStatement("delete from edu_lms_university_mstr m where id="+id+" ");  
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


