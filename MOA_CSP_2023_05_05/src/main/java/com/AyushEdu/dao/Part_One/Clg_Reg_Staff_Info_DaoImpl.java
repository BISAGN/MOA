package com.AyushEdu.dao.Part_One;

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

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class Clg_Reg_Staff_Info_DaoImpl implements Clg_Reg_Staff_Info_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	
	//GET department names 
		public ArrayList<ArrayList<String>> getAll_ug_Department_name() {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

				q =  "select id,ug_depart from clg_reg_ug_department_mstr where status = 1 order by id\n";
				

				PreparedStatement stmt = conn.prepareStatement(q);
//				 stmt.setInt(1, institute_id);
				System.err.println("---/home/user/17-04-2023/practitioner.backup===="+stmt);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("ug_depart"));// 1

					list.add(alist);
					
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
						e.printStackTrace();
					}
				}
			}
			return list;
		}

		public String getAllDepartment_list_new_ug() {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String TEMP="";
			try {
				
				conn = dataSource.getConnection();

				q =  "select id,ug_depart from clg_reg_ug_department_mstr where status = 1 order by id\n";

				PreparedStatement stmt = conn.prepareStatement(q);
//				 stmt.setInt(1, institute_id);
				ResultSet rs = stmt.executeQuery();
				
			
				
				ArrayList<String> alist = new ArrayList<String>();
				while (rs.next()) {
					
					TEMP+="<tr><td class=\"sr-no\">\n"+
							"<p></p>\n"+
							"</td>\n"+
							"<td>\n"+
							"<p>"+rs.getString("ug_depart")+"</p>\n"+
							"<input type='hidden' id='hid_staff_info"+rs.getString("id")+"' name='hid_staff_info"+rs.getString("id")+"' value='0'>"+
							"</td>\n"
							+"<td colspan='2'>\n"
							+"<div class='input-style-1'>\n"
							+"<input type='text'"
								+"name = 'anatomy_professor_full_time"+rs.getString("id")+"'"
								+"id='anatomy_professor_full_time"+rs.getString("id")+"'"
								+"class='form-control'"
								+"placeholder='Enter Full time' value='0'>" 
						+"</div>"
					+"</td>"
//					+"<td>"
//						+"<div class='input-style-1'>"
//							+"<input type='text'"
//								+"name='anatomy_professor_guest_faculty"+rs.getString("id")+"'"
//								+"id='anatomy_professor_guest_faculty"+rs.getString("id")+"'"
//								+"class='form-control'"
//								+"placeholder='Enter Guest Faculty' value='0'>"
//						+"</div>"
//					+"</td>"
					+"<td colspan='2'>"
						+"<div class='input-style-1'>"
							+"<input type='text'"
								+"name='anatomy_ass_professor_full_time"+rs.getString("id")+"'"
								+"id='anatomy_ass_professor_full_time"+rs.getString("id")+"'"
								+"class='form-control'"
								+"placeholder='Enter Full time' value='0'>"
						+"</div>"
					+"</td>"
//					+"<td>"
//						+"<div class='input-style-1'>"
//							+"<input type='text'"
//								+"name='anatomy_ass_professor_guest_faculty"+rs.getString("id")+"'"
//								+"id='anatomy_ass_professor_guest_faculty"+rs.getString("id")+"'"
//								+"class='form-control'"
//								+"placeholder='Enter Guest Faculty' value='0'>"
//						+"</div>"
//					+"</td>"
					+"<td colspan='2'>"
						+"<div class='input-style-1'>"
							+"<input type='text'"
								+"name='anatomy_lect_professor_full_time"+rs.getString("id")+"'"
								+"id='anatomy_lect_professor_full_time"+rs.getString("id")+"'"
								+"class='form-control'"
								+"placeholder='Enter Full time' value='0'>"
						+"</div>"
					+"</td>"
					+"<td colspan='2'>"
						+"<div class='input-style-1'>"
							+"<input type='text' name='anatomy_total"+rs.getString("id")+"'"
									+"id='anatomy_total"+rs.getString("id")+"'"
//								+"id='anatomy_total' class='form-control"+rs.getString("id")+"'"
										+"class='form-control'"
								+"placeholder='Enter Total' value='0' readonly>"
						+"</div>"
					+"</td>"
				+"</tr>";

				}
//				alist.add(TEMP);//0
//				list.add(alist);
				
				System.err.println("list--->  "+list);
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
						e.printStackTrace();
					}
				}
			}
			
			
			
			return TEMP;
		}

				//GET COURSE FOR UG
				public ArrayList<ArrayList<String>> getAllCourse_PG() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q="select id,subject_name from edu_faculty_subject_mstr where fac_course_id=1 and status=1 \n";
						
					

						PreparedStatement stmt = conn.prepareStatement(q);
						
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id"));// 0
							alist.add(rs.getString("subject_name"));// 1

							list.add(alist);
							
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}
			
			
				public String getAllDepartment_list_new_pg() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					String TEMP="";
					try {
						
						conn = dataSource.getConnection();

						q =  "select id,subject_name from edu_faculty_subject_mstr where fac_course_id=1 and status=1\n";

						PreparedStatement stmt = conn.prepareStatement(q);
//						 stmt.setInt(1, institute_id);
						ResultSet rs = stmt.executeQuery();
						
					
						
						ArrayList<String> alist = new ArrayList<String>();
						while (rs.next()) {
							
							TEMP+="<tr><td class='sr-no'>\n"+
									"<p></p>\n"+
									"</td>\n"+
									"<td>\n"+
									"<p>"+rs.getString("subject_name")+"</p>\n"+
									"<input type='hidden' id='hid_staff_info_pg"+rs.getString("id")+"' name='hid_staff_info_pg"+rs.getString("id")+"' value='0'>"+
									"</td>\n"
									+"<td>\n"
									+"<div class='input-style-1'>\n"
									+"<input type='text'"
									+" name='homoeopathicug_professor_full_time"+rs.getString("id")+"'"
									+" id='homoeopathicug_professor_full_time"+rs.getString("id")+"'"
									+" class='form-control grand'"
									+" placeholder='Total Professor' value='0'>"
							+"</div>"
						+"</td>"
						+"<td>"
							+" <div class='input-style-1'>"
								+"<input type='text'"
									+"name='homoeopathicug_professor_guest_faculty"+rs.getString("id")+"'"
									+"id='homoeopathicug_professor_guest_faculty"+rs.getString("id")+"'"
									+"class='form-control grand'"
									+"placeholder='Total Associate Professors / Readers' value='0'>"
							+"</div>"
						+"</td>"
						+"<td>"
							+"<div class='input-style-1'>"
								+"<input type='text'"
									+"name='ass_professor_lect"+rs.getString("id")+"'"
									+"id='ass_professor_lect"+rs.getString("id")+"'"
									+"class='form-control grand'"
									+"placeholder='Total Assistant Professors / Lecturers' value='0'>"
							+"</div>"
						+"</td>"

						+"<td>"
							+"<div class='input-style-1'>"
								+"<input type='text' name='homoeopathicug_total"+rs.getString("id")+"'"
									+"id='homoeopathicug_total"+rs.getString("id")+"'"
									+"class='form-control '"
									+"placeholder='Enter Total' value='0' readonly>"
							+"</div>"
						+"</td>"
					+"</tr>";

						}
//						alist.add(TEMP);//0
//						list.add(alist);
						
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
								e.printStackTrace();
							}
						}
					}
					
					
					
					return TEMP;
				}
				public ArrayList<ArrayList<String>> getAll_post_name() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select id,post_name from clg_reg_non_teaching_staff_mstr  order by id\n";
						

						PreparedStatement stmt = conn.prepareStatement(q);
//						 stmt.setInt(1, Integer.parseInt(institute_id));
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id"));// 0
							alist.add(rs.getString("post_name"));// 1

							list.add(alist);
							
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}
				public String getAllPost_list() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					String TEMP="";
					try {
						
						conn = dataSource.getConnection();

						q =  "select id,post_name from clg_reg_non_teaching_staff_mstr order by id\n";

						PreparedStatement stmt = conn.prepareStatement(q);
//						stmt.setInt(1, institute_id);
						ResultSet rs = stmt.executeQuery();
						
						
						ArrayList<String> alist = new ArrayList<String>();
						while (rs.next()) {
							
							TEMP+="<tr><td class='sr-no'>\n"+
									"<p></p>\n"+
									"</td>\n"+
									"<td>\n"+
									"<p>"+rs.getString("post_name")+"</p>\n"+
									"<input type='hidden' id='hid_staff_info_nonteaching"+rs.getString("id")+"' name='hid_staff_info_nonteaching"+rs.getString("id")+"' value='0'>"+
									
									"</td>\n"
									
									+"<td>"
										+"<div class='input-style-1'>"
											+"<input type='text' name='admin_staff"+rs.getString("id")+"'"
												+"id='admin_staff"+rs.getString("id")+"'"
												+"class='form-control grand_post'"
												+"placeholder='Total Administrative Staff'>"
												
												//+"<input type='text' id='hid_staff_info_nonteaching"+rs.getString("id")+"' name='hid_staff_info_nonteaching"+rs.getString("id")+"' value='0'>"
												
									
										+"</div>"
									+"</td>"
										
								+"</tr>";

						}
//						alist.add(TEMP);//0
//						list.add(alist);
						
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
								e.printStackTrace();
							}
						}
					}
					
					
					
					return TEMP;
				}
				public ArrayList<ArrayList<String>> getAll_academic_name() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select id,teacher_dtls from clg_reg_last_year_teacher_dtls_mstr  order by id\n";
						

						PreparedStatement stmt = conn.prepareStatement(q);
//						 stmt.setInt(1, Integer.parseInt(institute_id));
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id"));// 0
							alist.add(rs.getString("teacher_dtls"));// 1

							list.add(alist);
							
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}
				public String getAllacademic_list() {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					String TEMP="";
					try {
						
						conn = dataSource.getConnection();

						q =  "select id,teacher_dtls from clg_reg_last_year_teacher_dtls_mstr order by id\n";

						PreparedStatement stmt = conn.prepareStatement(q);
//						stmt.setInt(1, institute_id);
						ResultSet rs = stmt.executeQuery();
						
						
						ArrayList<String> alist = new ArrayList<String>();
						while (rs.next()) {
							
							TEMP+="<tr><td class='sr-no'>\n"+
									"<p></p>\n"+
									"</td>\n"+
									"<td>\n"+
									"<p>"+rs.getString("teacher_dtls")+"</p>\n"+
									"<input type='hidden' id='hid_staff_info_academic"+rs.getString("id")+"' name='hid_staff_info_academic"+rs.getString("id")+"' value='0'>"+
									"</td>\n"
									+"<td>"
										+"<div class='input-style-1'>"
											+"<input type='text' name='ret-professer"+rs.getString("id")+"'"
												+"id='ret-professer"+rs.getString("id")+"'"
												+ "class='form-control'"
												+"placeholder='Total Retired Professors' value='0'>"
										+"</div>"
									+"</td>"
									+"<td>"
										+"<div class='input-style-1'>"
											+"<input type='text' name='ret-reader"+rs.getString("id")+"'"
												+" id='ret-reader"+rs.getString("id")+"'"
											+"class='form-control'"
												+"placeholder='Total Associate Professors / Readers' value='0'>"
										+"</div>"
									+"</td>"
									+"<td>"
										+"<div class='input-style-1'>"
											+"<input type='text' name='ret-lecturers"+rs.getString("id")+"'"
												+"id='ret-lecturers"+rs.getString("id")+"'"
											+"class='form-control'"
												+"placeholder='Total Assistant Professors/ Lecturers' value='0'>"
										+"</div>"
									+"</td>"
									+"<td>"
										+"<div class='input-style-1'>"
											+"<input type='text' name='retired_total"+rs.getString("id")+"'"
												+"id='retired_total"+rs.getString("id")+"'"
											+"class='form-control'"
												+"placeholder='Total Retired Teacher' value='0' readonly>"
										+"</div>"
									+"</td>"
								+"</tr>";

						}
//						alist.add(TEMP);//0
//						list.add(alist);
						
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
								e.printStackTrace();
							}
						}
					}
					
					
					
					return TEMP;
				}
				//GET INSTITUTE_ID
				public ArrayList<ArrayList<String>> getInstitute_id(String user_id) {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select institute_id from logininformation where userid = ? ";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, Integer.parseInt(user_id));
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("institute_id"));// 0

							list.add(alist);
							
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}

				//GET DOCUMENT ATTACHMENT DETAILS
				public ArrayList<ArrayList<String>> getAllSalary_Details(int institute_id) {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();

						q = "select id,bankpaycheck,bankpayremarks,gpfdeductcheck,gpfdeductremarks,\n"
								+ "cchnormscheck,cchnormsremarks,staff_payscale,staff_payscale_remarks,\n"
								+ "payscalegradepay,payscalegradepay_remarks,ass_pro_pay,ass_pro_pay_remarks,\n"
								+ "lectass_pro_pay,lectass_pro_pay_remarks,bankpay_attachment,gpfdeduct_attachment,cchnorms_attachment,\n"
								+ "staff_payscale_attachment,payscalegradepay_attachment,ass_pro_payattachment,lectass_pro_payattachment\n"
								+ "from clg_reg_staff_salary_information where institute_id = ?";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, institute_id);
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id"));// 0
							alist.add(rs.getString("bankpaycheck"));// 1
							alist.add(rs.getString("bankpayremarks"));// 2
							alist.add(rs.getString("gpfdeductcheck"));//3
							alist.add(rs.getString("gpfdeductremarks"));// 4
							alist.add(rs.getString("cchnormscheck"));// 5
							alist.add(rs.getString("cchnormsremarks"));//6
							alist.add(rs.getString("staff_payscale"));// 7
							alist.add(rs.getString("staff_payscale_remarks"));// 8
							alist.add(rs.getString("payscalegradepay"));//9
							alist.add(rs.getString("payscalegradepay_remarks"));// 10
							alist.add(rs.getString("ass_pro_pay"));// 11
							alist.add(rs.getString("ass_pro_pay_remarks"));//12
							alist.add(rs.getString("lectass_pro_pay"));// 13
							alist.add(rs.getString("lectass_pro_pay_remarks"));// 14
							alist.add(rs.getString("bankpay_attachment"));// 15
							alist.add(rs.getString("gpfdeduct_attachment"));// 16
							alist.add(rs.getString("cchnorms_attachment"));// 17
							alist.add(rs.getString("staff_payscale_attachment"));// 18
							alist.add(rs.getString("payscalegradepay_attachment"));// 19
							alist.add(rs.getString("ass_pro_payattachment"));// 20
							alist.add(rs.getString("lectass_pro_payattachment"));// 21
							
					
							list.add(alist);
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}
				//GET P_ID
				public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {
						
						conn = dataSource.getConnection();

						q =  "select cc.id,cc.institute_id \n"
								+ "from logininformation li\n"
								+ "inner join clg_reg_teaching_staff_ug cc on cc.institute_id = li.institute_id\n"
								+ "where userid = ? ";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, Integer.parseInt(user_id));
						ResultSet rs = stmt.executeQuery();
						
						while (rs.next()) {
							ArrayList<String> alist = new ArrayList<String>();
							alist.add(rs.getString("id"));// 0
							alist.add(rs.getString("institute_id"));// 1

							list.add(alist);
							
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
								e.printStackTrace();
							}
						}
					}
					return list;
				}	
				
				public List<Map<String,Object>> getAlluploaddocumentdetails(int institute_id) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				    Connection conn = null;
				    try{          
				    	conn = dataSource.getConnection();
				//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
				//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
				//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//				    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//				    			+ "where user_id = ?";
				    	String sq1="select id as mainid,* from clg_reg_staff_upload_document_info where institute_id=?";
				    	
				        PreparedStatement stmt = conn.prepareStatement(sq1);
				        stmt.setInt(1, institute_id);
				        ResultSet rs = stmt.executeQuery();  
				        ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							list.add(columns);
						}
						rs.close();
						stmt.close();
						conn.close();
				   }catch(SQLException e){
					   e.printStackTrace();
				   }        
				   return list;
				}
				
				
				public List<Map<String,Object>> getAllteacherpromotiondetails(int institute_id) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				    Connection conn = null;
				    try{          
				    	conn = dataSource.getConnection();
				//        String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
				//        		+ "inner join tb_nch_teacher_exp_child tc on tc.p_id=td.id \n"
				//        		+ "inner join tb_nch_degree_and_support_doc_child sdc on sdc.p_id=td.id where user_id = ?";      
//				    	String sq1="select  td.id as mainid,* from tb_nch_add_teacher_details td \n"
//				    			+ "where user_id = ?";
				    	String sq1="select id as mainid,* from clg_reg_college_teacher_promotion where institute_id=?";
				    	
				        PreparedStatement stmt = conn.prepareStatement(sq1);
				        stmt.setInt(1, institute_id);
				        ResultSet rs = stmt.executeQuery();  
				        ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							list.add(columns);
						}
						rs.close();
						stmt.close();
						conn.close();
				   }catch(SQLException e){
					   e.printStackTrace();
				   }        
				   return list;
				}
				
				//GET MEDICAL POST LIST
				public List<Map<String,Object>> getDesignationList() {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				    Connection conn = null;
				    try{          
				    	conn = dataSource.getConnection();
				    
				    	String sq1="select * from tb_nch_designation_mstr where status = 1 order by id";
				    	
				        PreparedStatement stmt = conn.prepareStatement(sq1);
				        ResultSet rs = stmt.executeQuery();  
				        System.err.println("===========-getDesignationList========================-"+stmt);
				        ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							list.add(columns);
						}
						rs.close();
						stmt.close();
						conn.close();
				   }catch(SQLException e){
					   e.printStackTrace();
				   }        
				   return list;
				}

				@Override
				public ArrayList<ArrayList<String>> getAllDepartment_list_ug_new() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public ArrayList<ArrayList<String>> getAll_pg_Department_name() {
					// TODO Auto-generated method stub
					return null;
				}
				
}
