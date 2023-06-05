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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class b_Search_State_PracDAOImpl implements b_Search_State_PracDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTable_b_Seacrh_State_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			String reg_no, String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,
			String  registration_for_type,String dob,String date_of_reg) {
		
	//	System.err.println("-------status "+status);
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender,reg_no,registration_state,  per_state,   per_district,  type_of_degree,   degree_name,  
				place_of_working,   registration_for_type,dob,date_of_reg);
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
 

			
			
			q="select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode,\n"
					+ "dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,\n"
					+ " e.father_name, e.status,\n"
					+ " e.reg_auth,\n"
					+ "sss.state_name as registration_state  \n"
					+ "from edu_b_regulation  e inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state\n"
					+ "inner join edu_practitioner_registration pr on pr.user_id=e.user_id inner join edu_lms_state_mstr sss"
					+ " on cast (sss.state_id as character varying)  = pr.regisration_state"+ SearchValue +" order by id " + orderType
		 			 + " limit " + pageL + " OFFSET " + startPage;	
			
			
			

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,reg_no,registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,   registration_for_type,dob,date_of_reg);
 		 	 System.err.println("stmt---urmikkkkkkkkkkkkkkkkkkkkkkkkkkkkkk----"+stmt);
			ResultSet rs = stmt.executeQuery();
             //System.err.println("stmt-----HARSH--MAIN---"+stmt);
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

				

				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onclick='checkCKBT()' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
		  
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox; 
		     //System.err.println("chekboxaction----------"+chekboxaction);
				
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
				
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vd = "<button type='button'"+ VIEWDEGREE+" >VIEW</button>";

						String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vioch = "<button type='button'"+ VIEWIOCH+" >VIEW</button>";
				
				
				action =f+" "+  f1  ;
				 
				columns.put("chekboxaction", chekboxaction);
				columns.put("action", action);
				
				columns.put("vd", vd);
				columns.put("vioch", vioch);
			
				
//				list.add(chekboxaction);
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
	
	@Override
	public long DataTable_b_Seacrh_State_PracDataTotalCount(String Search, String nrh_en_no,String first_name,String institute_status,String gender,String reg_no,
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			String  registration_for_type,String dob,String date_of_reg) {
	 	
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender,reg_no,registration_state,  per_state,   per_district,type_of_degree,   
				degree_name,  place_of_working,   registration_for_type,dob,date_of_reg);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				

			
			q="select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode,\n"
					+ "dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,\n"
					+ " e.father_name, e.status,\n"
					+ " e.reg_auth,\n"
					+ "sss.state_name as registration_state  \n"
					+ "from edu_b_regulation  e inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state\n"
					+ "inner join edu_practitioner_registration pr on pr.user_id=e.user_id inner join edu_lms_state_mstr sss on cast \n"
					+ "	   (sss.state_id as character varying)  = pr.regisration_state"+SearchValue+") a  ";
			
			
			
		 	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender,reg_no,registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,   registration_for_type,dob,date_of_reg);
		 	  System.out.println("stmt-----state--COUNTt ---"+stmt);
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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String first_name,String institute_status,String gender,String reg_no,String registration_state,
			String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,String  registration_for_type,String dob,String date_of_reg) {
		
		String SearchValue = "";
	
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";
			System.err.println("parameter search first_name" + SearchValue);

		}
		
		if (institute_status.equals("0")) {
			SearchValue += " and e.status=1 and e.institute_status = 1  and e.state_status = ? ";

		}
		if (institute_status.equals("1")) {
			SearchValue += " and e.status=1 and e.institute_status = 1  and e.state_status = ? ";

		}
		if (institute_status.equals("2")) {
			SearchValue += " and e.status=2 and e.institute_status = 2  and e.state_status = ? ";

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
		if (!per_state.equals("0") && per_state != null) {
			SearchValue += " and e.per_state::integer = ?::integer ";
		}
		if (per_district.equals("5") && per_district != null) {
			SearchValue += " and e.per_district::integer = ?::integer ";
		}
		
		if (!type_of_degree.equals("0") && per_district != null) {
			SearchValue += " and pc.type_of_degree::integer = ?::integer ";
		}
		
		 if (!degree_name.equals("0") && degree_name != null) {
			SearchValue += " and pc.degree_name::integer = ?::integer ";
		}
		
		 if (!place_of_working.equals("0") && place_of_working != null) {
			SearchValue += " and mc.place_of_working::integer = ?::integer ";
		}
		if (!registration_for_type.equals("0") && registration_for_type != null) {
			SearchValue += " and e.registration_for_type::integer = ?::integer ";
		}
		if (!dob.equals("") && dob != null) {
			SearchValue += " and to_char(e.dob , 'YYYY-MM-DD') = ? ";
		}
		if (!date_of_reg.equals("") && date_of_reg != null ) {
			SearchValue += " and to_char(e.date_of_reg , 'YYYY-MM-DD') = ? ";
		}
		 
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "  (lower(e.first_name) like ? or lower(e.father_name) like ?) ";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String institute_status,String gender, String reg_no,String registration_state
			,String per_state ,String per_district,String type_of_degree,String  degree_name,String place_of_working,String  registration_for_type,String dob,String date_of_reg) {
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
			if (!per_state.equals("0") && per_state != null) {
				flag += 1;
				stmt.setString(flag,  per_state);
			}
			if (per_district.equals("5") && per_district != null) {
				flag += 1;
				stmt.setString(flag,  per_district);
			}
			
			if (!type_of_degree.equals("0") && type_of_degree != null) {
				flag += 1;
				stmt.setString(flag,  type_of_degree);
			}
			if (!degree_name.equals("0") && degree_name != null) {
				flag += 1;
				stmt.setString(flag,  degree_name);
				
			}
			if (!place_of_working.equals("0") && place_of_working != null) {
				flag += 1;
				stmt.setString(flag,place_of_working);
				
			}
			
			if (!registration_for_type.equals("0") && registration_for_type != null) {
				flag += 1;
				stmt.setString(flag,registration_for_type);
				
			}
			if (!dob.equals("") && dob != null) {
				flag += 1;
				stmt.setString(flag,dob);
				
			}
			
			if (!date_of_reg.equals("") && date_of_reg != null) {
				flag += 1;
				stmt.setString(flag,date_of_reg);
				
			}
			
			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	public String approve_b_StatePracData(String a,String status,String username) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";
	 
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement("update edu_b_regulation set state_status=? , nrh_status=0  where id=?");
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2,id);
					//System.err.println("check the statment--"+stmt);
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
	
	public String reject_b_StatePracData(String a,String status,String username) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement(" update edu_b_regulation set state_status=? , status=2 ,  institute_status = 2    where id=?");
					 System.err.println("st---rej---"+stmt);
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2, id);
					//System.err.println("check the statment--"+stmt);
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
//				if(status.equals("1")) {
//				return "Approved Successfully";
//				}
				  if(status.equals("2")) {
					return "Rejected Successfully";
					}
				else
					return "UnSuccessfully";
			} else {
//				if(status.equals("1")) {
//					return "Approved not Successfully";
//					}
				  if(status.equals("2")) {
					return "Rejected not Successfully";
					}
				else
				return "UnSuccessfully";
			}
	}

}
