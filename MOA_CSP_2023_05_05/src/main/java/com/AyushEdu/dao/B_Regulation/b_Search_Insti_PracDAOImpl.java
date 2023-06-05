package com.AyushEdu.dao.B_Regulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.B_Regulation.TB_EDU_B_REGULATION;
 

@Repository
public class b_Search_Insti_PracDAOImpl implements b_Search_Insti_PracDAO {

	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableEdu_b_Reg_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			String reg_no, String registration_state ,String dob,String date_of_reg) {
		
 
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender,reg_no,registration_state, dob,date_of_reg);
	//	Image image = new ImageIcon(this.getClass().getResource(photo_path)).getImage();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
		 

				
				
				q="select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
						+ "dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
						+ "n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,\n"
						+ " e.father_name, e.status, e.reg_auth, sss.state_name as registration_state   from edu_b_regulation  e \n"
						+ " inner join \n"
						+ " recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ " inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
						+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
						+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
						+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
						+ " inner join edu_practitioner_registration pr on pr.user_id=e.user_id \n"
						+ " inner join edu_lms_state_mstr sss on cast(sss.state_id  as character varying)  = (pr.regisration_state )\n"
 						+ " where e.id!= 0   and e.del_status='0'   "+ SearchValue +" order by id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;	;
				
				
				
			  
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,reg_no,registration_state,   dob,date_of_reg);
		 	 System.err.println("search---institute ----"+stmt);
			ResultSet rs = stmt.executeQuery();
 			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");
 
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";
				String vd = "";
				String vioch = "";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
				
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vd = "<button type='button'"+ VIEWDEGREE+" >VIEW</button>";

						String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vioch = "<button type='button'"+ VIEWIOCH+" >VIEW</button>";

				

				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onclick='checkCKBT()' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
		  
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox;
		    
		     String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
				 
				 columns.put("chekboxaction", chekboxaction);
				  
				//columns.put("action", action);
 				columns.put("vd", vd);
				columns.put("vioch", vioch);
				list.add(columns);
			  	

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String first_name,String institute_status,String gender,String reg_no,String registration_state,
			 String dob,String date_of_reg) {

		String SearchValue = "";
		/// advance search
 
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";
		}
		
		if (institute_status.equals("0")) {
			SearchValue += " and e.status=1 and e.institute_status = ? ";
		}
		if (institute_status.equals("1")) {
			SearchValue += " and e.status=1  and e.institute_status = ? ";
		}
		if (institute_status.equals("2")) {
			SearchValue += "and e.status=2  and e.institute_status = ? ";
		}
		if (!gender.equals(" ") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
		if (!reg_no.equals("") && reg_no != null) {
			SearchValue += " and e.reg_no = ?::integer ";
		}
		if (!registration_state.equals("0") && registration_state != null) {
			SearchValue += " and pr.regisration_state::integer = ?::integer ";
		}
		if (!dob.equals("") && dob != null && !dob.equals("DD/MM/YYY") ) {
			SearchValue += " and to_char(e.dob , 'YYYY-MM-DD') = ? ";
		}
		if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYY") ) {
			SearchValue += " and to_char(e.date_of_reg , 'YYYY-MM-DD') = ? ";
		}
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "  (lower(e.first_name) like ? ) ";
		}
	
		return SearchValue;
	}
	
	@Override
	public long DataTableEdu_b_Reg_masterDataTotalCount(String Search, String first_name, String institute_status,String gender,String reg_no,
			String registration_state,String dob,String date_of_reg) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender,reg_no,registration_state,  dob,date_of_reg);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
	
			q = "select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, dd.district_name as per_district,  ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,\n"
					+ " e.father_name, e.status, e.reg_auth, sss.state_name as registration_state  \n"
					+ " from edu_b_regulation  e inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ " inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
					+ " inner join edu_practitioner_registration pr on pr.user_id=e.user_id \n"
					+ " inner join edu_lms_state_mstr sss on cast (sss.state_id as character varying)  = pr.regisration_state "
					+ " where e.id!= 0 "
					+ " and  e.del_status='0' "+SearchValue+") a  ";
			
					 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender,reg_no,registration_state,  dob,date_of_reg);
			 System.out.println("search count -------------- institute ---"+stmt);
			ResultSet rs = stmt.executeQuery();
		 	
			while (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return (long) total;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String institute_status,String gender,
			String reg_no,String registration_state,String dob,String date_of_reg) {
		int flag = 0;
		try {

			
			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			
			if (institute_status.equals("0")) {
				flag += 1;
				stmt.setInt(flag,0);
			}
			if (institute_status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			if (institute_status.equals("2")) {
				flag += 1;
				stmt.setInt(flag,2);
			}
			
			 
			
			
			if (!gender.equals(" ") && gender != null) {
				flag += 1;
				stmt.setString(flag,  gender);
			}
			if (!reg_no.equals("") && reg_no != null) {
				flag += 1;
				stmt.setString(flag,  reg_no);
			}
			if (!registration_state.equals("0") && registration_state != null) {
				flag += 1;
				stmt.setString(flag,  registration_state);
			}

			if (!dob.equals("") && dob != null && !dob.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag,dob);
			}
			
			if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag,date_of_reg);
			}
			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	public String approve_INS_b_regData(String a,String status) {
		String[] id_list = a.split(":");

		Connection conn = null;
		Integer out = 0;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					Session sessionHQL = this.sessionFactory.openSession();
					TB_EDU_B_REGULATION assetupd =(TB_EDU_B_REGULATION)sessionHQL.get(TB_EDU_B_REGULATION.class, id);
					stmt = conn.prepareStatement(" update edu_b_regulation set institute_status=? ,state_status=0  where id=?");
					//System.err.println("st------"+stmt);
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2, id);
					out = stmt.executeUpdate();
		 }

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		if (out > 0) {
			if(status.equals("1")) {
			return "Approved Successfully";
			}
			else
				return "UnSuccessfully";
		} else {
			if(status.equals("1")) {
				return "Approved not Successfully";
				}
			else
			return "UnSuccessfully";
		}
	}



public String reject_INS_b_regData(String a,String status) {
	String[] id_list = a.split(":");

	Connection conn = null;
	Integer out = 0;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				Session sessionHQL = this.sessionFactory.openSession();
				TB_EDU_B_REGULATION assetupd =(TB_EDU_B_REGULATION)sessionHQL.get(TB_EDU_B_REGULATION.class, id);
				stmt = conn.prepareStatement(" update edu_b_regulation set institute_status=? ,status=2  where id=?");
				//System.err.println("st------"+stmt);
				stmt.setInt(1, Integer.parseInt(status));
				stmt.setInt(2, id);
				out = stmt.executeUpdate();
	 }

		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	if (out > 0) {
		    if(status.equals("2")) {
			return "Rejected Successfully";
			}
		else
			return "UnSuccessfully";
	} else {
		   if(status.equals("2")) {
			return "Rejected not Successfully";
			}
		else
		return "UnSuccessfully";
	}
}

//--------------------------------------------download-------------------------

public ArrayList<ArrayList<String>> DataTableEdu_b_Reg_masterDataList()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
    
		q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \\n\"\n"
				+ "					+ \"			 e.per_address,d.district_name as per_district,s.state_name as per_state,e.per_pincode,e.aadhaar_no,e.ph_no,e.mo_no,e.alt_mo_no1,e.email_id,\\n\"\n"
				+ "					+ \"				 e.dob,c.name as nationality,e.degree,e.university,e.month_year,e.reg_no,e.date_of_reg,e.name_reg,e.reg_renew_permanent,\\n\"\n"
				+ "					+ \"                 e.name_of_hospital_teaching,\\n\"\n"
				+ "					+ \"                 e.name_of_patient,e.crh_no,e.cch_no,\\n\"\n"
				+ "					+ \"				 e.nch_no,e.father_name, e.status\\n\"\n"
				+ "					+ \"from edu_b_regulation  e\\n\"\n"
				+ "					+ \"inner join edu_lms_country_mstr c on cast (c.id as character varying)  = e.nationality\\n\"\n"
				+ "					+ \"inner join edu_lms_district_mstr d on cast (d.district_id as character varying)  = e.pre_district\\n\"\n"
				+ "					+ \"inner join edu_lms_state_mstr s on cast (s.state_id as character varying)  = e.pre_state\\n\"\n"
				+ "					+ \"where e.status='0' \"+ SearchValue +\" order by id" + SearchValue;
			
			
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0

				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}

	
	
}
