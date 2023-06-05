package com.AyushEdu.dao.Regulation;

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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;

@Repository
public class Search_PractDtlDAOImpl implements Search_PracDtlDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableSeacrh_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender , String dob,String userId,String state_id,String university_id,String role) {
		
	//	System.err.println("-------status "+status);
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender ,dob,userId,state_id,university_id);
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

			
			
			if(role.equals("Student_NCH")) {

									q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address_details1,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
											+ "	 	e.pre_address_details1,dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
											+ "	 to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
											+ "	 e.father_name, e.status,e.reject_remarks,\n"
											+ "	  e.valid_up_to,e.state_reg_no,ss2.state_name as reg_state "
											+ "		 from reg_nch_form_a_p  e "
											+ "	 inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
											+ "	 inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
											+ "	 inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
											+ "	 inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
											+ "	 inner join edu_lms_state_mstr ss on (ss.state_id)  = e.per_state  left join edu_lms_state_mstr ss2 on (ss2.state_id)  = e.reg_state\n"
											+ "	 where e.id!= 0 and e.del_status='0' "+ SearchValue +" order by id " + orderType
											+ " limit " + pageL + " OFFSET " + startPage;
							 }
			if(!role.equals("Student_NCH")) {
				q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address_details1,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
						+ "	 	e.pre_address_details1,dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
						+ "	 to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
						+ "	 e.father_name, e.status,e.reject_remarks,\n"
						+ "	  e.valid_up_to,e.state_reg_no,ss2.state_name as reg_state "
						+ "		 from reg_nch_form_a_p  e "
	 					+ "	 inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "	 inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
						+ "	 inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "	 inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
						+ "	 inner join edu_lms_state_mstr ss on (ss.state_id)  = e.per_state  inner join edu_lms_state_mstr ss2 on (ss2.state_id)  = e.reg_state\n"
						+ "	 where e.id!= 0 and e.del_status='0' "+ SearchValue +" order by id " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;	
			}
			

			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender, dob,userId,state_id,university_id);
		 	 System.err.println("stmt-d--DataTableSeacrh_PracDataList--"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
 			int countFunction=1;
			int countFunction1=1;
			int countFunction2=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("img","<img class='d-block img5050 imageZomm imgcsp' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"'/>"
						+ "<input type='hidden' id='imghid"+countFunction+"' value="+rs.getString("id")+">");
 
				String vd="";
				String vm="";

			
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to View Detail?') ){Pop_Up_Degree('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vm = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye popdg'>"
								+ "<input type='hidden' id='DCounId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";

						columns.put("vm",vm);	
						
//						columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");
			
				
				
//				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//						vd = "<button type='button'"+ VIEWDEGREE+" >VIEW</button>";
//						
//				columns.put("vd",vd);
//						
						
						
						//if(status.equals("1")) {
						
						String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
								+ rs.getString("id") + "') }else{ return false;}\"";
						vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download Data' >\n"
								+ "		<i class='lni lni-download pdfdown'>"
								+ "<input type='hidden' id='DCounpdf"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						//}
//						}
//						<i id="downloadbtn" title="Download"
//								class="fa fa-download" onclick="download_file();"
//								style="color: #359ade;"></i> <input type="hidden"
//								id="file_id" value="${item.file_id}">
								
						columns.put("vd",vd);
						countFunction+=1;
						countFunction1+=1;
						countFunction2+=1;
				 
						
			
				
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
	public long DataTableSeacrh_PracDataTotalCount(String Search, String first_name, String status,String gender, String dob,String userId,String state_id,String university_id,String role) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, status,gender,dob,userId,state_id,university_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			 
		
				q="select count(*)from( "
						+ " select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address_details1,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
						+ "			e.pre_address_details1,dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
						+ "		to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
						+ "		e.father_name, e.status,e.reject_remarks,\n"
						+ "		   e.valid_up_to,e.state_reg_no 					from reg_nch_form_a_p  e 		 			inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "		inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
						+ "		inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "		inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
						+ "		inner join edu_lms_state_mstr ss on (ss.state_id)  = e.per_state\n"
  						+ "		 where e.id!= 0  and e.del_status='0'  "+SearchValue+") a  ";
		
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender, dob,userId,state_id,university_id);
			 System.err.println("stmt---prct dtl Count---"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String first_name, String status,String gender, String dob,String userId,String state_id,String university_id
			) {
		
		String SearchValue = "";
	
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";
			System.err.println("parameter search first_name" + SearchValue);

		}
		
		if (status.equals("0")) {
			SearchValue += " and e.status=1  and e.institute_status  in (-1,0)  and e.state_status  in (-1,0) and e.nrh_status  in (-1,0) ";

		}
		if (status.equals("1")) {
			SearchValue += " and e.status=1  and e.nrh_status = 1 ";

		}
		if (status.equals("2")) {
			SearchValue += " and e.status=2  ";
//			and e.institute_status =2 and e.state_status  =2 and e.nrh_status  =2 
		}
		
		if (!gender.equals("-1") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
//		if (!reg_no.equals("") && reg_no != null) {
//			SearchValue += " and e.reg_no = ?::integer ";
//		}
//		if (!registration_state.equals("0") && registration_state != null) {
//			SearchValue += " and p.regisration_state::integer = ?::integer ";
//		}
//		if (!per_state.equals("0") && per_state != null) {
//			SearchValue += " and e.per_state::integer = ?::integer ";
//		}
//		if (!per_district.equals("") && per_district != null) {
//			SearchValue += " and e.per_district::integer = ?::integer ";
//		}
//		
//		if (!type_of_degree.equals("0") && per_district != null) {
//			SearchValue += " and pc.type_of_degree::integer = ?::integer ";
//		}
//		
//		 if (!degree_name.equals("0") && degree_name != null) {
//			SearchValue += " and pc.degree_name::integer = ?::integer ";
//		}
//		
//		 if (!place_of_working.equals("0") && place_of_working != null) {
//			SearchValue += " and mc.place_of_working::integer = ?::integer ";
//		}
//		if (!registration_for_type.equals("0") && registration_for_type != null) {
//			SearchValue += " and e.registration_for_type::integer = ?::integer ";
//		}
//		if (!dob.equals("") && dob != null) {
//			SearchValue += " and to_char(e.dob , 'YYYY-MM-DD') = ? ";
//		}
		if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
			SearchValue += " and to_char(e.dob , 'DD/MM/YYYY') = ? ";
		}
//		if (!date_of_reg.equals("") && date_of_reg != null ) {
//			SearchValue += " and to_char(e.date_of_reg , 'YYYY-MM-DD') = ? ";
//		}
		
		
		
		
		if (!userId.equals("") && userId != null && !userId.equals("0") ) {
			SearchValue += " and e.user_id = ?::integer ";
		}

		if (!state_id.equals("null") && !state_id.equals("") && state_id != null && !state_id.equals("0") ) {
			SearchValue += " and r.regisration_state = ? ";
		}
		if (!university_id.equals("") && university_id != null && !university_id.equals("0") ) {
			SearchValue += " and r.institute_name = ? ";
		}
		
		
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "  (lower(e.first_name) like ? or lower(e.father_name) like ?) ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String status,String gender, String dob,
			String userId,String state_id,String university_id) {
		int flag = 0;
		try {

			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			
//			if (status.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag,0);
//			}
//			if (status.equals("1")) {
//				flag += 1;
//				stmt.setInt(flag,1);
//			}
//			if (status.equals("2")) {
//				flag += 1;
//				stmt.setInt(flag,2);
//			}
//			
			if (!gender.equals("-1") && gender != null) {
				flag += 1;
				stmt.setString(flag,  gender);
			}
//			if (!reg_no.equals("") && reg_no != null) {
//				flag += 1;
//				stmt.setString(flag,  reg_no);
//			}
//			if (!registration_state.equals("0") && registration_state != null) {
//				flag += 1;
//				stmt.setString(flag,  registration_state);
//			}
//			if (!per_state.equals("0") && per_state != null) {
//				flag += 1;
//				stmt.setString(flag,  per_state);
//			}
//			if (!per_district.equals("") && per_district != null) {
//				flag += 1;
//				stmt.setString(flag,  per_district);
//			}
//			
//			if (!type_of_degree.equals("0") && type_of_degree != null) {
//				flag += 1;
//				stmt.setString(flag,  type_of_degree);
//			}
//			if (!degree_name.equals("0") && degree_name != null) {
//				flag += 1;
//				stmt.setString(flag,  degree_name);
//				
//			}
//			if (!place_of_working.equals("0") && place_of_working != null) {
//				flag += 1;
//				stmt.setString(flag,place_of_working);
//				
//			}
//			
//			if (!registration_for_type.equals("0") && registration_for_type != null) {
//				flag += 1;
//				stmt.setString(flag,registration_for_type);
//				
//			}
//			if (!dob.equals("") && dob != null) {
//				flag += 1;
//				stmt.setString(flag,dob);
//				
//			}
			if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
				flag += 1;
				stmt.setString(flag,dob);
				
			}
			
//			if (!date_of_reg.equals("") && date_of_reg != null) {
//				flag += 1;
//				stmt.setString(flag,date_of_reg);
//			}
			
			
			if (!userId.equals("") && userId != null  && !userId.equals("0")) {
				flag += 1;
				stmt.setString(flag,userId);
			}
			

			if (!state_id.equals("null") && !state_id.equals("") && state_id != null && !state_id.equals("0") ) {
				flag += 1;
				stmt.setString(flag,state_id);
			}
			if (!university_id.equals("") && university_id != null && !university_id.equals("0") ) {
				flag += 1;
				stmt.setString(flag,university_id);
			}
			
			
			if (Search != null && !Search.equals("") ) {
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
	

	public ArrayList<ArrayList<String>> getdataofcerti(String id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();		
			
//			q="select d.pre_address_details1,d.pre_address_details2,d.pre_address_details3,d.first_name,d.father_name,d.nrh_en_no,\n"
//					+ "d1.degree_name,dm.district_name,sm.state_name,d.pre_pincode,d.photo_path,d1.degree_name\n"
//					+ "from reg_nch_form_a_p d\n"
//					+ "inner join edu_lms_state_mstr sm on sm.state_id = d.pre_state::int\n"
//					+ "inner join edu_lms_district_mstr dm on dm.district_id = d.pre_district::int\n"
//					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = d.id\n"
//					+ "inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name\n"
//					+ "where d.id=?";
			
			
//			q="select  pre_address_details1, pre_address_details2, pre_address_details3, first_name, father_name, nrh_en_no, "
//					+ " degree_name, district_name,  state_name, pre_pincode , photo_path, degree_name,state_reg_no "
//					+ "from reg_nch_form_a_national_record_a d\n"
// 					+ "where  p_id=?";
			
//			===30/12/2022 URMIK STATE_LOGO
			
		 q="select  d.pre_address_details1, d.pre_address_details2, d.pre_address_details3, d.first_name, d.father_name, d.nrh_en_no,  \n"
		 		+ " d.degree_name, d.district_name,  d.state_name, d.pre_pincode , d.photo_path, d.degree_name,d.state_reg_no,sts.state_logo  \n"
		 		+ " from reg_nch_form_a_national_record_a d inner join edu_lms_state_mstr sd on sd.state_name=d.state_name\n"
		 		+ " left join edu_lms_statelogo_mstr sts on sts.state_id=sd.state_id\n"
		 		+ "where  p_id=?";
			
			System.out.println("q-----Urmik------certi data--------------->"+q);
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(id));

			System.out.println("stmt-----Urmik------certi data--------------->"+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("first_name"));//0
				alist.add(rs.getString("father_name"));//1
				alist.add(rs.getString("nrh_en_no"));//2
				alist.add(rs.getString("degree_name"));//3
				alist.add(rs.getString("pre_address_details1")+"  "+rs.getString("pre_address_details2")+"  "+rs.getString("pre_address_details3")+"  "+rs.getString("district_name")+"   "+rs.getString("state_name")+"   "+rs.getString("pre_pincode"));//4
				alist.add(rs.getString("photo_path"));//5
				alist.add(rs.getString("state_reg_no"));//6
				alist.add(rs.getString("state_logo"));//7
				
				
				list.add(alist);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public REG_NCH_FORM_A_P getViewByid(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		REG_NCH_FORM_A_P viewid = (REG_NCH_FORM_A_P) sessionHQL.get(REG_NCH_FORM_A_P.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return viewid;
	}
	
	public ArrayList<ArrayList<String>> getdataofview(String id) {
//		 System.err.println("view--dao------"+id);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
 
			q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address_details1,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode,\n"
					+ "e.per_address_details1,dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.mo_no,e.mo_no,e.alt_mo_no1,e.email_id,e.state_reg_no,\n"
					+ "to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.degree,e.valid_up_to,\n"
					+ "e.father_name, e.status,\n"
 					+ "--string_agg(mdc.name_of_institute,' | ') as name_of_institute,string_agg(mdc.month_and_year_of_degree,' | ') as month_and_year_of_degree,string_agg(dm.degree_name,' | ') as degree_name,\n"
					+ "string_agg(hc.name_of_res_p,' , ') as name_of_res_p,string_agg(hc.place_of_working_name ||' '||\n"
					+ "hc.hos_address1 ||' '|| hc.hos_address2 ||' '|| hc.hos_address3,' , ') as hos_name_add\n"
					+ "from reg_nch_form_a_p  e\n"
					+ "inner join recr_nationality_mst n on n.nationality_id   = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on d.district_id  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on s.state_id   = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on dd.district_id   = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on ss.state_id  = e.per_state\n"
					+ "--inner join reg_nch_med_degree_dtl_a_ch mdc on mdc.regulation_p_id=e.id\n"
					+ "left join reg_nch_working_place_dtl_a_ch hc on hc.regulation_p_id=e.id\n"
					+ "--inner join edu_lms_degree_mstr dm on dm.id=mdc.degree_name \n"
					+ "where e.id!= 0 and e.del_status='0' and e.id=? group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25 order by id  ";


			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(id));

			System.err.println("stmt---------view------------>"+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();

				alist.add(rs.getString("nrh_en_no"));//0
				alist.add(rs.getString("first_name"));
				alist.add(rs.getString("father_name"));//0
				alist.add(rs.getString("pre_address_details1")+"   "+rs.getString("pre_state")+"   "+rs.getString("pre_district")+"   "+rs.getString("pre_pincode"));//1
				alist.add(rs.getString("per_address_details1")+"   "+rs.getString("per_state")+"   "+rs.getString("per_district")+"   "+rs.getString("per_pincode"));//1
				alist.add(rs.getString("email_id"));//0
				alist.add(rs.getString("dob"));//0
 
				alist.add(rs.getString("nationality"));//0
//				alist.add(rs.getString("degree_name")+" , "+rs.getString("name_of_institute")+" , "+rs.getString("month_and_year_of_degree"));//1
		//		alist.add(rs.getString("degree_name")+" , "+rs.getString("name_of_institute")+" , "+rs.getString("month_and_year_of_degree"));//1
				alist.add(rs.getString("state_reg_no"));//2
				alist.add(rs.getString("per_state"));//3
				alist.add(rs.getString("hos_name_add"));//4
				alist.add(rs.getString("name_of_res_p"));//5
				alist.add(rs.getString("valid_up_to"));//6
				alist.add(rs.getString("photo_path"));//14
				list.add(alist);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	// show degree in view page
	
	public ArrayList<ArrayList<String>> getdataofviewdegree(String id) {
//		 System.err.println("view--dao------"+id);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

 
			 
//			q="select string_agg(dm.degree_name ||' '||  mdc.name_of_institute ||' '|| mdc.month_and_year_of_degree,' , ')   as degree_uni_my\n"
//					+ "from reg_nch_med_degree_dtl_a_ch mdc\n"
//					+ "inner join edu_lms_degree_mstr dm on dm.id=mdc.degree_name \n"
//					+ "where mdc.regulation_p_id!= 0 and mdc.regulation_p_id=?";

			q= " select  string_agg(dm.degree_name ||' '||  dx.type_of_degree ||' '|| mdc.month_and_year_of_degree,' , ')   as degree_uni_my\n"
					+ "from reg_nch_med_degree_dtl_a_ch mdc\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=mdc.degree_name \n"
					+ "inner join edu_lms_type_of_degree_mstr dx on dx.id= mdc.type_of_degree::integer\n"
					+ "where mdc.regulation_p_id!= 0 and mdc.regulation_p_id= ? ";
			PreparedStatement stmt = conn.prepareStatement(q); 
			stmt.setInt(1, Integer.parseInt(id));

			System.out.println("stmt----degree view--------------->"+stmt);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				System.err.println("alist++++++++++++++++++++"+alist);

				alist.add(rs.getString("degree_uni_my"));//1
				list.add(alist);
				System.err.println("alist++++++++++++++++++++"+alist);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
//	  -----------------------------View_Img Path---------------------------------------------------------
	  
	  @Override
		public String getImagePath7(String id) {
			String whr="";
			Connection conn = null;
			try {	
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select photo_path from reg_nch_form_a_p where id=? ";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	           whr=rs.getString("photo_path");             	
	 	        }
	 		    rs.close();
	 	    	stmt.close();
	 			conn.close();
	     	} catch (SQLException e) {
	     			e.printStackTrace();
	     	}	
			return whr;
		}
	  
  //start
	  
	  
	  @Override
		public ArrayList<String> data_Search_Status(String userId) {
						
				ArrayList<String> alist = new ArrayList<String>();		
				Connection conn = null;
				String q="";
			
				try{
						
						conn = dataSource.getConnection();			 
						PreparedStatement stmt=null;
//								
//						q="SELECT  pid.status, pid.institute_status, pid.state_status, pid.nrh_status ,r.institute_name,r.regisration_state "
//								+ "FROM reg_nch_form_a_p pid  "
//								+ " inner join reg_nch_registration_a r on  (r.user_id)  = pid.user_id "
//								+ "WHERE pid.user_id = ?";
						
						q= "SELECT  pid.status, pid.institute_status, pid.state_status, pid.nrh_status,pid.per_state ,pid.clg_status  \n"
						   + "	 FROM reg_nch_form_a_p pid \n"
							+ "WHERE pid.user_id = ?";
						
						stmt=conn.prepareStatement(q);
						
						 stmt.setInt(1, Integer.parseInt(userId));
						 
						 					  
						 ResultSet rs = stmt.executeQuery();  
						 System.err.println("track _status--"+stmt);
						 while (rs.next()) {
						    	  ArrayList<String> list = new ArrayList<String>();
						    	  list.add(rs.getString("status")); //0 
						    	  list.add(rs.getString("institute_status")); //1
						    	  list.add(rs.getString("state_status")); // 2
						    	  list.add(rs.getString("nrh_status")); // 3 
//						    	  list.add(rs.getString("institute_name")); //  
						    	  list.add(rs.getString("per_state")); //4
						    	  list.add(rs.getString("clg_status")); //5
						    	 alist.addAll(list);	
			 	        }
				      rs.close();
				      stmt.close();
				      conn.close();
				   }catch (SQLException e) {
						
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
