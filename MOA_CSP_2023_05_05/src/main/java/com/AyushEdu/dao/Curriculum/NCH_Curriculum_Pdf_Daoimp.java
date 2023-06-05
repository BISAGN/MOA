package com.AyushEdu.dao.Curriculum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NCH_Curriculum_Pdf_Daoimp implements NCH_Curriculum_Pdf_Dao  {

	@Autowired
	private DataSource dataSource;
	
	 @Override
		public ArrayList<ArrayList<String>> GetSystemdegreeid_fetch(String userid) {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			
			String q = "";
		
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				String qry = "";
		
					q = "select a.system,a.degree,a.semester  from (select ayush_id,aadhar_card,system,degree,semester,email \n"
							+ "from edu_lms_nch_student_details\n"
							+ "UNION ALL\n"
							+ "select ayush_id,aadhar_card,system,degree,semester,email from edu_lms_nch_student_details) a \n"
							+ "inner join edu_lms_system_mstr s on s.id = a.system\n"
							+ "inner join logininformation l on l.email_id = a.email\n"
							+ "inner join edu_lms_degree_mstr m on m.id = a.degree\n"
							+ "where userid = ?";
				
					stmt = conn.prepareStatement(q);
					stmt.setInt(1,Integer.parseInt(userid));
//					System.err.println("------Getdegreeid_fetch----"+stmt);
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {				
					  
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("system")); //0
					alist.add(rs.getString("degree")); //1
					alist.add(rs.getString("semester")); //2
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
					}
				}
			}
			return list;
		}
	
	public List<Map<String, Object>> getstudentdetails_Report_Excel(String course_id) {

	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q="select th.id,th.theoretical_lecture,th.pract_tutor_semi_clinic_post,sm.system_name,dm.degree_name,prm.professional,c.course_name,c.course_code from edu_cc_tb_nch_teaching_hours th\r\n"
					+ "inner join edu_lms_course_mstr c on c.id = th.course_id\r\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id=th.system_id\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=th.degree_id\r\n"
					+ "inner join edu_cc_tb_professional_mstr prm on prm.id=th.professional_id\r\n"
					+ "where th.course_id=? and th.status=1";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("qqqqqqqqqqqqqqqqqqpppppppppppppppppp"+stmt);
//			System.err.println("\n *****-----1-----***** \n"+stmt + "\n *****-----1-----***** \n");
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
	
	
// Program_Outcomes =====================================
		public ArrayList<ArrayList<String>> tblProgram_Outcomes_list(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				
				sql = "select program_outcome from edu_cc_tb_program_outcome_mstr p "
						+ "where status=1 and system_id=45 and course_id=? ";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("==============Program_Outcomes========"+stmt);
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("program_outcome"));// 1
					alist.add(list);
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
			return alist;
		}
		
		
// Course_Outcomes =================================================================================	
		public ArrayList<ArrayList<String>> tblCourse_Outcomes_list(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				
				sql = "select course_outcome from edu_cc_tb_add_course_outcome_mstr a \r\n"
						+ "where status=1 and system_id=45 and course_id=? ";
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("==================Course_Outcomes==========="+stmt);
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("course_outcome"));// 1
					alist.add(list);
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
			return alist;
		}
	
// Teaching Learning Methods ===============================================================================
	
	public ArrayList<ArrayList<String>> tblTeaching_Method_list(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			
			sql = "select teaching_learning_method from edu_cc_tb_nch_teaching_learning_method_parent p\r\n"
					+ "inner join edu_cc_tb_nch_teaching_learning_method_child c on c.p_id= p.id\r\n"
					+ "where p.status=1 and c.status=1 and p.system_id=45 and p.course_id=?";
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("==============tblTeaching_Method_list========"+stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("teaching_learning_method"));// 1
				alist.add(list);
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
		return alist;
	}
	
//Teaching Hours====================================================================================================
	
	
	public ArrayList<ArrayList<String>> Teachinghours(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String q = "";
			PreparedStatement stmt = null;

			q="select ROW_NUMBER() OVER(order by th.id ) as sr_no, th.id,th.theoretical_lecture,th.pract_tutor_semi_clinic_post,sm.system_name,dm.degree_name,prm.professional,c.course_name,c.course_code from edu_cc_tb_nch_teaching_hours th\r\n"
					+ "inner join edu_lms_course_mstr c on c.id = th.course_id\r\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id=th.system_id\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=th.degree_id\r\n"
					+ "inner join edu_cc_tb_professional_mstr prm on prm.id=th.professional_id\r\n"
					+ "where th.course_id=? and th.status=1";

			stmt = conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("-----------Teaching hours-----------" + stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("sr_no"));// 0
				list.add(rs.getString("course_name"));// 1
				list.add(rs.getString("theoretical_lecture"));// 2
				list.add(rs.getString("pract_tutor_semi_clinic_post"));// 3

				alist.add(list);
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
		return alist;
	}
	
//start ------List_of_Topic//==========================================================================================================================

	
		public ArrayList<ArrayList<String>> List_of_Topic_list(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.sub_topic,c.hours,i.term from edu_cc_tb_nch_list_of_topics_parent p\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("\n *****-----5-----***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("sub_topic"));// 2
					list.add(rs.getString("hours"));// 3
					list.add(rs.getString("term"));// 4

					alist.add(list);
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
			return alist;
		}

///end ------List_of_Topic//=======================================================================================================================

	
//Start of Table 2-Learning Objectives (Theory) =============================

		public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {

				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
						+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic,string_agg(lc.learning_outcome,'=') as learning_outcome\r\n"
						+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
						+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
						+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
						+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
						+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
						+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_parent lp on lp.topic_id=p.topic_id\r\n"
						+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lc on lc.p_id=lp.id \r\n"
						+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
						+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and p.system_id=45 and p.topic_id not in(204,205) and p.course_id=? "
						+ " group by p.id,2,3,4,5,6,7,8,9,10,11,12,13,14,15 ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("\n *****-----table2_Learning_Objectives_of_Course_HomUG-----***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("topic"));// 0
					list.add(rs.getString("generic_competency"));// 1
					list.add(rs.getString("subject_area"));// 2
					list.add(rs.getString("rep_und"));// 3
					list.add(rs.getString("specific_competency"));// 4
					list.add(rs.getString("slo_outcome"));// 5
					list.add(rs.getString("domain"));// 6
					list.add(rs.getString("guilberts_level"));// 7
					list.add(rs.getString("scope"));// 8
					list.add(rs.getString("method"));// 9
					list.add(rs.getString("formative"));// 10
					list.add(rs.getString("formative"));// 11
					list.add(rs.getString("int_departments"));// 12
					list.add(rs.getString("learning_outcome"));// 13
					alist.add(list);
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
			return alist;
		}

//End of Table 2-Learning Objectives (Theory) ========================================
			
//Practical Learning Objectives///================================================================================
				
				
				@Override
				public List<ArrayList<String>> table4_Practical_Learning_Objectives_list(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {
			
						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;
			
						sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_compte,c.subject_area,ru.rep_und,c.specific_compet,t.practical, \r\n"
								+ "c.spec_learn_object,d.domain,gl.guilberts_level,su.scope,tlm.method,fsm.formative,c.integ_horivtspi,t.practical,string_agg(lc.learni_outcome,'=') as learning_outcome\r\n"
								+ "from edu_cc_tb_nch_t4_learning_object_parent p\r\n"
								+ "inner join edu_cc_tb_nch_t4_learning_object_child c on c.p_id=p.id\r\n"
								+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
								+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_knows\r\n"
								+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
								+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.must_know_dknow_nk\r\n"
								+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_mthd_med\r\n"
								+ "inner join edu_cc_tb_nch_practical_mstr t on t.id=p.practical_id\r\n"
								+ "inner join edu_cc_tb_nch_practical_learning_outcome_parent lp on lp.practical_id=p.practical_id\r\n"
								+ "inner join edu_cc_tb_nch_practical_learning_outcome_child lc on lc.p_id=lp.id \r\n"
								+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.sumtive_assessmt where p.status=0 and c.status=0 and lc.status=1\r\n"
								+ "and p.course_id=?\r\n"
								+ "group by p.id,2,3,4,5,6,7,8,9,10,11,12,13,14";

						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
						stmt.setInt(1, Integer.parseInt(course_id));
//						System.err.println("\n *****-----table4_Pract_Learning_Objectives_of_Course_HomUG-----***** \n" + stmt);
						ResultSet rs = stmt.executeQuery();
			
						int i = 0;
						while (rs.next()) {
							i++;
							ArrayList<String> list = new ArrayList<String>();
			
							list.add(rs.getString("practical"));// 0
							list.add(rs.getString("generic_compte"));// 1
							list.add(rs.getString("subject_area"));// 2
							list.add(rs.getString("rep_und"));// 3
							list.add(rs.getString("specific_compet"));// 4
							list.add(rs.getString("spec_learn_object"));// 5
							list.add(rs.getString("domain"));// 6
							list.add(rs.getString("guilberts_level"));// 7
							list.add(rs.getString("scope"));// 8
							list.add(rs.getString("method"));// 9
							list.add(rs.getString("formative"));// 10
							list.add(rs.getString("formative"));// 11
							list.add(rs.getString("integ_horivtspi"));// 12
							list.add(rs.getString("learning_outcome"));// 13
			
							alist.add(list);
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
					return alist;
				}
			
			
//Practical Learning Objectives//======================================================		
	
				
// Non Lecture Teaching Learning methods===============================
				  
				  public ArrayList<ArrayList<String>> Table_Non_Lecture(String course_id) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						try {
							conn = dataSource.getConnection();
							String sql = "";
							PreparedStatement stmt = null;

							
							sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no,non_lecture_tl_method,c.no_of_hours from edu_cc_tb_nch_add_non_lecture_activities_parent p\r\n"
									+ "inner join edu_cc_tb_nch_add_non_lecture_activities_child c on c.p_id= p.id\r\n"
									+ "where p.status=0 and c.status=0 and p.system_id=45 and p.course_id=?";

							stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

							stmt.setInt(1, Integer.parseInt(course_id));
//							System.err.println("====================Table_Non_Lecture================"+stmt);
							ResultSet rs = stmt.executeQuery();

							int i = 0;
							while (rs.next()) {
								
								i++;
								ArrayList<String> list = new ArrayList<String>();
								
								list.add(rs.getString("sr_no"));// 0
								list.add(rs.getString("non_lecture_tl_method"));// 1
								list.add(rs.getString("no_of_hours"));// 2

								alist.add(list);
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
						return alist;
					}
				  

//End  Non Lecture Teaching Learning methods===============================================


//start=====================List_of_Pratical==============================================================================

					public ArrayList<ArrayList<String>> List_of_Pratical_list(String course_id) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						try {

							conn = dataSource.getConnection();
							String sql = "";
							PreparedStatement stmt = null;

							sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.practical,c.sub_practical,c.hours,i.term from edu_cc_tb_nch_list_of_practical_parent p\r\n"
									+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id= p.id\r\n"
									+ "inner join edu_cc_tb_nch_practical_mstr t on t.id= p.practical_id\r\n"
									+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? ";


							stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE);

							stmt.setInt(1, Integer.parseInt(course_id));
//							System.err.println("\n *****-----545645-----***** \n" + stmt);
							ResultSet rs = stmt.executeQuery();

							int i = 0;
							while (rs.next()) {
								i++;
								ArrayList<String> list = new ArrayList<String>();

								list.add(rs.getString("sr_no"));// 0
								list.add(rs.getString("practical"));// 1
								list.add(rs.getString("sub_practical"));// 2
								list.add(rs.getString("hours"));// 3
								list.add(rs.getString("term"));// 4

								alist.add(list);
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
						return alist;
					}

//end of List_of_Pratical =================================================================================================


//start of  Number of Papers and Marks Distribution========================================================================================

	public ArrayList<ArrayList<String>> Number_of_papers_and_Mark_Distribution (String course_id) {
			ArrayList<ArrayList<String>> nplist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			
			sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, cm.course_code,\n"
				+ "(select count(ec.exam_paper) \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as papers,\n"
				+ "(select sum(ec.theory_comp_marks) \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as theory,\n"
				+ "(select epc.practical_marks \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as practical,\n"
				+ "(select epc.viva_marks \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as viva,\n"
				+ "(select epc.elective_marks \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as elective,\n"
				+ "(select epc.ia_marks \n"
				+ "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as ia\n"
				+ "from edu_cc_summary_parent p \n"
				+ "inner join edu_lms_course_mstr cm on cm.id=p.course_id\n"
				+ "where p.course_id=?";
			
					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					stmt.setInt(1, Integer.parseInt(course_id));
					stmt.setInt(2, Integer.parseInt(course_id));
					stmt.setInt(3, Integer.parseInt(course_id));
					stmt.setInt(4, Integer.parseInt(course_id));
					stmt.setInt(5, Integer.parseInt(course_id));
					stmt.setInt(6, Integer.parseInt(course_id));
					stmt.setInt(7, Integer.parseInt(course_id));
					ResultSet rs = stmt.executeQuery();
				
				int i = 0;
				while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("course_code"));// 1
					list.add(rs.getString("papers"));// 2
					list.add(rs.getString("theory"));// 3
					list.add(rs.getString("practical"));// 4
					list.add(rs.getString("viva"));// 5
					list.add(rs.getString("ia"));// 6
					list.add(rs.getString("elective"));// 7
				
				nplist.add(list);
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
				return nplist;
	}


//end of   Number of Papers and Marks Distribution==========================================
	
	
//Paper Layout ==========================================================================================


    public ArrayList<ArrayList<String>> Paper_Layout_List(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;
		
		sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\r\n"
				+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\r\n"
				+ "from edu_cc_tb_paper_layout pl\r\n"
				+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\r\n"
				+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\r\n"
				+ "where pl.status='1' and pl.course_id=? order by pl.paper_id ";
		
		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("==============Paper Layout========"+stmt);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("question_type"));// 1
			list.add(rs.getString("total"));// 2
			alist.add(list);
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
	return alist;
}

//End Paper Layout ==========================================================================
    
    
// 6 F - Distribution_of_Theory_Exam============================================================
 	 public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_DAO(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				
				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
						+ "where tm.course_id= ? and pm.paper like 'PAPER I' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
						+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----15-----**Riddhi-18*** \n"+stmt + "\n *****-----15-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					
					i++;
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("paper"));// 0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("term"));// 2
					list.add(rs.getString("marks"));// 3
					list.add(rs.getString("mcq"));// 4
					list.add(rs.getString("saq"));// 5
					list.add(rs.getString("laq"));// 6

					alist.add(list);
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
			return alist;
		}
 	 
 	 
 	  public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_DAO(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				
				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
						+ "where tm.course_id= ? and pm.paper like 'PAPER II' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
						+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id ";
						

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----16-----*Riddhi-18**** \n"+stmt + "\n *****-----16-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					
					i++;
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("paper"));// 0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("term"));// 2
					list.add(rs.getString("marks"));// 3
					list.add(rs.getString("mcq"));// 4
					list.add(rs.getString("saq"));// 5
					list.add(rs.getString("laq"));// 6

					alist.add(list);
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
			return alist;
		}
 	  
 	  
//end 6 F - Distribution_of_Theory_Exam=====================================================

 	  
 	  
 // Start Question Paper Blue Print ===================================================================
  	  
 	  	public ArrayList<ArrayList<String>> getpaperformatdata_Homeo(String course_id,String d3_desirable_know,String qt,String noofpaper) {
 	  		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
 	  		Connection conn = null;
 	  		String q = "";
 	  		String qs ="";
 	  		try {
 	  			conn = dataSource.getConnection();
 	  			PreparedStatement stmt = null;
 	  			
 	  			if(qt.equals("mcq")) {
 	  				qs+=" and sf.mcq = 1 ";
 	  			}
 	  			if(qt.equals("saq")) {
 	  				qs+=" and sf.saq = 1 ";
 	  			}
 	  			if(qt.equals("laq")) {
 	  				qs+=" and sf.laq = 1 ";
 	  			}
 	  			
 	  			String d3a[] = d3_desirable_know.split(",");
 	  			String d3s = "";
 	  			for(int d=0;d<d3a.length;d++) {
 	  				if(d == 0) {
 	  					d3s += " and (lc.mk_dk = "+d3a[d]+" ";
 	  				}else {
 	  					d3s += " or lc.mk_dk = "+d3a[d]+" ";
 	  				}
 	  			}
 	  			d3s += " ) ";
 	  			q="select tm.topic,pm.paper\r\n"
 	  					+ "from edu_cc_tb_sixf_distribution_of_theory_exam sf\r\n"
 	  					+ "inner join edu_cc_tb_nch_list_of_topics_parent lotp on lotp.topic_id=sf.topic_id and lotp.status=0\r\n"
 	  					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=sf.topic_id\r\n"
 	  					+ "inner join edu_cc_tb_paper_mstr pm on pm.id= lotp.paper_id\r\n"
 	  					+ "inner join edu_cc_tb_nch_theory_learning_object_parent lp on lp.course_id=sf.course_id\r\n"
 	  					+ "inner join edu_cc_tb_nch_theory_learning_object_child lc on lc.p_id=lp.id\r\n"
 	  					+ "where sf.course_id=? and lotp.paper_id=? \r\n"
 	  					+ "and lc.mk_dk!=0 \r\n"
 	  					+ ""+d3s+" "+qs+" group by 1,2 \n";
 	  			
 	  			stmt = conn.prepareStatement(q);
 	  			stmt.setInt(1, Integer.parseInt(course_id));
 	  			stmt.setInt(2, Integer.parseInt(noofpaper));
 	  			System.err.println("stmt------------"+stmt);
 	  			ResultSet rs = stmt.executeQuery();

 	  			while (rs.next()) {
 	  				ArrayList<String> list = new ArrayList<String>();
 	  				
 	  				list.add(rs.getString("topic"));//0
 	  				list.add(rs.getString("paper"));//1 
 	  			
 	  				alist.add(list);
 	  				
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
 	  		return alist;
 	  	}
 	  	  
 	  	  
// End Question Paper Blue Print ============================================================
    

 //6-B===================== Start  Scheme of Assessment (formative and Summative)===============================================================

  public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse(String course_id,String term) {
  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
  	Connection conn = null;
  	String q = "";
  	
  	try {
  		conn = dataSource.getConnection();
  		PreparedStatement stmt = null;
  		
  		
  		q="select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,pm.professional,t.term,p.no_of_exam,e.exam_type,p.id  from edu_cc_tb_link_exam_and_course p\r\n"
  				+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\r\n"
  				+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\r\n"
  				+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id where p.course_id=? and t.term=?  order by p.id asc";
  		
  		stmt = conn.prepareStatement(q);
  		stmt.setInt(1, Integer.parseInt(course_id));
  		stmt.setString(2, term);
//  		System.err.println("Child Table Data==========="+stmt);
  		ResultSet rs = stmt.executeQuery();
  		int ser = 1;
  		String term1 = "";
  		String term2 = "";
  		String term3 = "";
  		ArrayList<String> list = new ArrayList<String>();
  		while (rs.next()) {
  			
  			if(ser == 1) {
  				term1 += rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
  			}
  			if(ser == 2) {
  				term1 += " + " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
  			}
  			
  			list.add(term1);
  			ser++;
  		}
  		alist.add(list);
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
  	return alist;
  }


  //6-B===================== End   Scheme of Assessment (formative and Summative)=================================================================


  
//Distribution of Practical Exam ==========================================================================
    
	  
	public ArrayList<ArrayList<String>> Distribution_of_Practical_Exam(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select ROW_NUMBER() OVER(order by id DESC) as SN, head , mark from edu_cc_tb_6h_distrubution_practical_exam \n "
					+"              where course_id=? and status='1' \n";
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("\n *****-----17-----***** \n"+stmt + "\n *****-----17-----***** \n");
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("head"));// 0
				list.add(rs.getString("mark"));// 1
				alist.add(list);
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
		return alist;
	}
	
    
//End Distribution of Practical Exam =================================================================
    
    
    
 // 6 D - Evaluation Methods for Periodical Assessment=====================================================
	  
 	  public ArrayList<ArrayList<String>> Table_Evaluation_Methods_For_Periodical_Assessment(String course_id) {
 			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
 			Connection conn = null;
 			try {
 				conn = dataSource.getConnection();
 				String sql = "";
 				PreparedStatement stmt = null;

 				
 				sql = "select ROW_NUMBER() OVER(order by e.id DESC) as sr_no,em.evaluation_method_pa\r\n"
 						+ "from edu_cc_tb_evaluation_method_pa e\r\n"
 						+ "inner join edu_cc_tb_evaluation_method_pa_mstr em on  em.id = e.evaluation_method_pa_id\r\n"
 						+ "where e.status='1'and e.system_id=45 and e.course_id=?";

 				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

 				stmt.setInt(1, Integer.parseInt(course_id));
// 				System.err.println("====================Evaluation_Methods================"+stmt);
 				ResultSet rs = stmt.executeQuery();

 				int i = 0;
 				while (rs.next()) {
 					
 					i++;
 					ArrayList<String> list = new ArrayList<String>();
 					
 					list.add(rs.getString("sr_no"));// 0
 					list.add(rs.getString("evaluation_method_pa"));// 1

 					alist.add(list);
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
 			return alist;
 		}
 	  
//End  6 D - Evaluation Methods for Periodical Assessment==================================================
 		
 	  
 	  
 // Reference and Resourses ==============================================================================
 				public ArrayList<ArrayList<String>> tableReference_Resourses(String course_id) {
 					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
 					Connection conn = null;
 					try {
 						conn = dataSource.getConnection();
 						String sql = "";
 						PreparedStatement stmt = null;
 						sql = "select resource from edu_cc_link_reference_resourses_mstr r"
 								+ " where r.status=1 and r.system_id=45 and r.course_id=?";
 						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
 						stmt.setInt(1, Integer.parseInt(course_id));
// 						System.err.println("========Reference_Resourses========="+stmt);
 						ResultSet rs = stmt.executeQuery();
 						int i = 0;
 						while (rs.next()) {
 							i++;
 							ArrayList<String> list = new ArrayList<String>();
 							list.add(rs.getString("resource"));// 0
 							alist.add(list);
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
 					return alist;
 				}
 				
//End  Reference and Resourses =====================================================================
 		
	

@Override
public ArrayList<ArrayList<String>> GetSystem_fetch_NCH(String userid) {
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		String qry = "";

			q = "select s.id from edu_lms_institute_reg i \n"
					+ "inner join  edu_lms_system_mstr s on s.id = i.system_id\n"
					+ "inner join logininformation l on l.institute_id = i.id "
					+ "where l.userid = ?";
		
			stmt = conn.prepareStatement(q);
			stmt.setInt(1,Integer.parseInt(userid));
//			System.err.println("------Getdegreeid_fetch----"+stmt);
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {				
			  
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id")); //0
			
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
			}
		}
	}
	return list;
}

    

public ArrayList<ArrayList<String>> getSubtopicfromTopicidNCH(String topic_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select c.sub_topic\r\n"
				+ "from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id and p.topic_id = ?";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(topic_id));
//		System.err.println("\n *****-----getSubtopicfromTopicidNCH-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sub_topic"));// 0

			alist.add(list);
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
	return alist;
}

//*******************************************************************HARSH******************************************************************************
@Override
public ArrayList<ArrayList<String>> AnatomyTopicTheory(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "\r\n"
				+ "select DISTINCT ROW_NUMBER() OVER(order by pm.paper) as sr_no,t.topic,i.term,sum(hours) as hours,pm.paper\r\n"
				+ "from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id \r\n"
				+ "inner join edu_cc_tb_paper_mstr pm on pm.id= p.paper_id \r\n"
				+ "where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? \r\n"
				+ "group by p.id,2,3,5 order by pm.paper";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----AnatomyTopicTheory-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("paper"));//0
			list.add(rs.getString("sr_no"));//1
			list.add(rs.getString("topic"));//2
			list.add(rs.getString("term"));//3
			list.add(rs.getString("hours"));//4

			alist.add(list);
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
	return alist;
}

@Override
public ArrayList<ArrayList<String>> AnatomyTopicPractical(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id) as sr_no,t.practical,i.term,sum(c.hours::integer) as hours\r\n"
				+ "from edu_cc_tb_nch_list_of_practical_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_nch_practical_mstr t on t.id= p.practical_id\r\n"
				+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id \r\n"
				+ "where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? \r\n"
				+ "group by p.id,2,3";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----AnatomyTopicPractical-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));//0
			list.add(rs.getString("practical"));//1
			list.add(rs.getString("term"));//2
			list.add(rs.getString("hours"));//3

			alist.add(list);
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
	return alist;
}

public ArrayList<ArrayList<String>> List_of_Topic_list_pdf_nch(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.sub_topic,c.hours,i.term from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? ";


		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----5-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("topic"));// 1
			list.add(rs.getString("term"));// 2
			list.add(rs.getString("hours"));// 3
			list.add(rs.getString("sub_topic"));//4

			alist.add(list);
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
	return alist;
}

//start ------List_of_Topic For Psychology BY Parth Rathod*******************************************************************************

public ArrayList<ArrayList<String>> List_of_Topic_listP(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.sub_topic,c.hours,i.term from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? and p.topic_id not in (363,364,365,366,367) ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----5-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("topic"));// 1
			list.add(rs.getString("hours"));// 2
			list.add(rs.getString("sub_topic"));// 3
			alist.add(list);
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
	return alist;
}

//BY PARTH RATHOD-PSYCHOLOGY**************************************************************************************

//start of  Number of Papers and Marks Distribution================================================

public ArrayList<ArrayList<String>> Number_of_papers_and_Mark_DistributionP(String course_id) {
	ArrayList<ArrayList<String>> nplist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select ROW_NUMBER() OVER(order by p.id DESC) as sr_no, cm.course_code,\n"
				+ "(select count(ec.exam_paper) \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as papers,\n"
				+ "(select sum(ec.theory_comp_marks) \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_examination_child ec on ec.p_id=p.id and p.course_id=? ) as theory,\n"
				+ "(select epc.practical_marks \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as practical,\n"
				+ "(select epc.viva_marks \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as viva,\n"
				+ "(select epc.elective_marks \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as elective,\n"
				+ "(select epc.ia_marks \n" + "	  from edu_cc_summary_parent p\n"
				+ "	  inner join edu_cc_summary_exam_pract_comp_child epc on epc.p_id=p.id and p.course_id=? ) as ia\n"
				+ "from edu_cc_summary_parent p \n" + "inner join edu_lms_course_mstr cm on cm.id=p.course_id\n"
				+ "where p.course_id=?";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
		stmt.setInt(2, Integer.parseInt(course_id));
		stmt.setInt(3, Integer.parseInt(course_id));
		stmt.setInt(4, Integer.parseInt(course_id));
		stmt.setInt(5, Integer.parseInt(course_id));
		stmt.setInt(6, Integer.parseInt(course_id));
		stmt.setInt(7, Integer.parseInt(course_id));
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("course_code"));// 1
			list.add(rs.getString("papers"));// 2
			list.add(rs.getString("theory"));// 3
			list.add(rs.getString("practical"));// 4
			list.add(rs.getString("viva"));// 5
			list.add(rs.getString("ia"));// 6

			nplist.add(list);
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
	return nplist;
}

//End of   Number of Papers and Marks Distribution===========================================

//Paper Layout =================================================


public ArrayList<ArrayList<String>> Paper_Layout_ListP(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\r\n"
				+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total,pl.time\r\n"
				+ "from edu_cc_tb_paper_layout pl\r\n" + "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\r\n"
				+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\r\n"
				+ "where pl.status='1' and pl.course_id=? ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("==============Paper Layout========" + stmt);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("question_type"));// 0
			list.add(rs.getString("total"));// 1
			list.add(rs.getString("time"));// 2
			alist.add(list);
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
	return alist;
}
//BY PARTH RATHOD-PSYCHOLOGY=================================================================

@Override
public ArrayList<ArrayList<String>> ana_practical_topics_list(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id) as sr_no,pp.practical_id,pm.practical,string_agg(pc.sub_practical, ',') as sub_practical,tm.term,string_agg(pc.hours, ',') as hours from edu_cc_tb_nch_list_of_practical_parent pp\r\n"
				+ "inner join edu_cc_tb_nch_list_of_practical_child pc on pc.p_id=pp.id \r\n"
				+ "inner join edu_cc_tb_nch_practical_mstr pm on pm.id=pp.practical_id \r\n"
				+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=pc.term_id\r\n"
				+ "where pp.course_id=? group by 1,2,4 \r\n";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----AnatomyTopicPractical-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));//0
			list.add(rs.getString("practical"));//1
			list.add(rs.getString("sub_practical"));//2
			list.add(rs.getString("term"));//3
			list.add(rs.getString("hours"));//4

			alist.add(list);
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
	return alist;
}

public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_Hom_psychology(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {

		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
				+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic\r\n"
				+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
				+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
				+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
				+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
				+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
				+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
				+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and "
				+ "p.system_id=45 and  p.course_id=? order by topic";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----table2_Learning_Objectives_of_Course_HomUG---Riddhi--***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("topic"));// 0
			list.add(rs.getString("generic_competency"));// 1
			list.add(rs.getString("subject_area"));// 2
			list.add(rs.getString("rep_und"));// 3
			list.add(rs.getString("specific_competency"));// 4
			list.add(rs.getString("slo_outcome"));// 5
			list.add(rs.getString("domain"));// 6
			list.add(rs.getString("guilberts_level"));// 7
			list.add(rs.getString("scope"));// 8
			list.add(rs.getString("method"));// 9
			list.add(rs.getString("formative"));// 10
			list.add(rs.getString("formative"));// 11
			list.add(rs.getString("int_departments"));// 11

			alist.add(list);
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
	return alist;
}


//START RIDDHI-YOGA FOR HEALTH PROMOTION******************************************************************************************

public List<Map<String, Object>> getstudentdetails_YOGA_Report_Excel(String course_id) {

	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q="select sm.system_name,dm.degree_name,prm.professional,c.course_name,\r\n"
					+ "c.course_code from edu_cc_link_system_degree_professional_course sd\r\n"
					+ "inner join edu_lms_course_mstr c on c.id = sd.course_id\r\n"
					+ "inner join edu_lms_system_mstr sm on  sm.id=sd.system_id\r\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree_id\r\n"
					+ "inner join edu_cc_tb_professional_mstr prm on prm.id=sd.professional_id\r\n"
					+ "where sd.course_id=? and sd.status=1";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("qqqqqqqqqqqqqqqqqqpppppppppppppppppp"+stmt);
//			System.err.println("\n *****-----1-----***** \n"+stmt + "\n *****-----1-----***** \n");
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
public ArrayList<ArrayList<String>> List_of_Topic_listYOGA(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id  ) as sr_no,t.topic,c.sub_topic,p.id from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ " where p.status=0 and c.status=0 and t.status=1 and  p.system_id=45 and p.course_id=? order by p.id asc ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----5-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("topic"));// 1
			list.add(rs.getString("sub_topic"));// 2
			alist.add(list);
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
	return alist;
}
//END RIDDHI-YOGA FOR HEALTH PROMOTION***********************************************************************************

//Start Riddhi -Homoeopathic Materia Medica********************************************************************************

public ArrayList<ArrayList<String>> Teachinghours_Homoeopathic(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String q = "";
		PreparedStatement stmt = null;

		q="select ROW_NUMBER() OVER(order by th.id ) as sr_no, th.id,th.theoretical_lecture,th.pract_tutor_semi_clinic_post,sm.system_name,\r\n"
				+ "dm.degree_name,prm.professional,c.course_name,c.course_code,concat(prm.professional,' - ',dm.degree_name) as year from edu_cc_tb_nch_teaching_hours th\r\n"
				+ "inner join edu_lms_course_mstr c on c.id = th.course_id\r\n"
				+ "inner join edu_lms_system_mstr sm on  sm.id=th.system_id\r\n"
				+ "inner join edu_lms_degree_mstr dm on dm.id=th.degree_id\r\n"
				+ "inner join edu_cc_tb_professional_mstr prm on prm.id=th.professional_id \r\n"
				+ "where th.course_id=? and th.status=1";

		stmt = conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("-----------Teachinghours_Homoeopathic----------" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("year"));// 0
			list.add(rs.getString("theoretical_lecture"));// 1
			list.add(rs.getString("pract_tutor_semi_clinic_post"));// 2

			alist.add(list);
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
	return alist;
}

public ArrayList<ArrayList<String>> tblSpecific_Objective_list(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;
		
		sql = "select specific_objective from edu_cc_tb_nch_specific_objective a \r\n"
				+ "where status=1 and system_id=45 and course_id=? ";
		
		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1, Integer.parseInt(course_id));
		System.err.println("==========12========tblSpecific_Objective_list=========="+stmt);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("specific_objective"));// 1
			alist.add(list);
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
	return alist;
}
public ArrayList<ArrayList<String>> List_of_Topic_listHOM(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
//		System.err.println("----getView_T2_data----" + course_id);
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.hours,p.id from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "where p.status=0 and c.status=0 and p.system_id=45 and p.course_id=? order by p.id asc ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****----List_of_Topic_listHOM----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("topic"));// 1
			list.add(rs.getString("hours"));// 2
			alist.add(list);
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
	return alist;
}
public ArrayList<ArrayList<String>> Non_Lecture_Activities_List_HOM(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = " select DISTINCT ROW_NUMBER() OVER(order by p.id  ) as sr_no,c.non_lecture_tl_method,c.no_of_hours,p.id\r\n"
				+ " from edu_cc_tb_nch_add_non_lecture_activities_parent p\r\n"
				+ " inner join edu_cc_tb_nch_add_non_lecture_activities_child c on c.p_id= p.id\r\n"
				+ " where p.status=0 and c.status=0 and  p.system_id=45 and p.course_id=? order by p.id asc";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****----Non_Lecture_Activities_List_HOM-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("non_lecture_tl_method"));// 1
			list.add(rs.getString("no_of_hours"));// 2
			alist.add(list);
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
	return alist;
}
public ArrayList<ArrayList<String>> Paper_Layout_ListHOM(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\r\n"
				+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\r\n"
				+ "from edu_cc_tb_paper_layout pl\r\n" + "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\r\n"
				+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\r\n"
				+ "where pl.status='1' and pl.course_id=? ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("==============Paper Layout========" + stmt);
		ResultSet rs = stmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("question_type"));// 0
			list.add(rs.getString("total"));// 1
			alist.add(list);
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
	return alist;
}

public ArrayList<ArrayList<String>> List_of_Topic_listHOM_medica(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.hours,p.id from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "where p.status=0 and c.status=0 and p.system_id=45 and p.course_id=? and p.topic_id not in(409,413,410) and p.id!=163 order by p.id asc ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****----List_of_Topic_listHOM----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("topic"));// 1
			list.add(rs.getString("hours"));// 2
			alist.add(list);
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
	return alist;
}


//End Riddhi -Homoeopathic Materia Medica*************************************************************************************



//START RIDDHI - Homoeopathic Pharmacy********************************************************************************************
@Override
public ArrayList<ArrayList<String>> Practical_List(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by pr.practical) as sr_no,p.id,pr.practical,c.hours,c.sub_practical,c.demo_perform\r\n"
				+ "from edu_cc_tb_nch_list_of_practical_parent p\r\n"
				+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id=p.id \r\n"
				+ "inner join edu_cc_tb_nch_practical_mstr pr on pr.id=p.practical_id \r\n"
				+ "where p.course_id=? order by p.id asc";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----AnatomyTopicTheory-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("practical"));//0
			list.add(rs.getString("sr_no"));//1
			list.add(rs.getString("sub_practical"));//2
			list.add(rs.getString("hours"));//3
			list.add(rs.getString("demo_perform"));//4

			alist.add(list);
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
	return alist;
}
//END RIDDHI - Homoeopathic Pharmacy*************************************************************************************

//By PARTH RATHOD******************************************************************************** 

@Override
public ArrayList<ArrayList<String>> TheoryWiseTeachingHoursDistributionH(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "	select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.hours,i.term,pm.paper\r\n"
				+ "	from edu_cc_tb_nch_list_of_topics_parent p\r\n"
				+ "	inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
				+ "	inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
				+ "	inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id\r\n"
				+ "	inner join edu_cc_tb_paper_mstr pm on pm.id= p.paper_id \r\n"
				+ "	where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? and p.topic_id not in (414,384,385,386,390,289,388)";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----TheoryWiseTeachingHoursDistributionH-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("paper"));//0
			list.add(rs.getString("sr_no"));//1
			list.add(rs.getString("topic"));//2
			list.add(rs.getString("hours"));//3

			alist.add(list);
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
	return alist;
}

@Override
public ArrayList<ArrayList<String>> getPopupNch_Practical_ChildDatalistH(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,p.id, c.p_id,pm.practical,c.sub_practical,t.term,c.hours,c.demo_perform "
				+ "from edu_cc_tb_nch_list_of_practical_parent p\n"
				+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id = p.id\n"
				+ "inner join edu_cc_tb_nch_practical_mstr pm on pm.id = p.practical_id\n"
				+ "inner join edu_cc_tb_i3_term_mstr t on t.id = c.term_id  "
				+ "where p.status=0 and c.status=0 and pm.status=1 and t.status=1 and p.system_id=45 and p.course_id=?";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("Child Table Data======29=====" + stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("practical"));// 0
			list.add(rs.getString("sr_no"));// 1
			list.add(rs.getString("sub_practical"));// 2
			list.add(rs.getString("demo_perform"));// 3
			list.add(rs.getString("hours"));// 4

			alist.add(list);
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
	return alist;
}

//Start of Table 2-Learning Objectives (Theory) of Course HomUG-OM-I=============================

public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_phy(String course_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	try {

		conn = dataSource.getConnection();
		String sql = "";
		PreparedStatement stmt = null;

		sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
				+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic,loc.learning_outcome\r\n"
				+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
				+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
				+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
				+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
				+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
				+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
				+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
				+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment "
				+ "inner join edu_cc_tb_nch_topic_learning_outcome_parent lop on lop.topic_id=p.topic_id \r\n"
				+ "inner join edu_cc_tb_nch_topic_learning_outcome_child loc on loc.p_id= lop.id\n"
				+ "where p.status=0 and c.status=0 and p.system_id=45 and p.course_id=? ";

		stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		stmt.setInt(1, Integer.parseInt(course_id));
//		System.err.println("\n *****-----table2_Learning_Objectives_of_Course_HomUG-----***** \n" + stmt);
		ResultSet rs = stmt.executeQuery();

		int i = 0;
		while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("topic"));// 0
			list.add(rs.getString("generic_competency"));// 1
			list.add(rs.getString("subject_area"));// 2
			list.add(rs.getString("rep_und"));// 3
			list.add(rs.getString("specific_competency"));// 4
			list.add(rs.getString("slo_outcome"));// 5
			list.add(rs.getString("domain"));// 6
			list.add(rs.getString("guilberts_level"));// 7
			list.add(rs.getString("scope"));// 8
			list.add(rs.getString("method"));// 9
			list.add(rs.getString("formative"));// 10
			list.add(rs.getString("formative"));// 11
			list.add(rs.getString("int_departments"));// 12
			list.add(rs.getString("learning_outcome"));// 13

			alist.add(list);
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
	return alist;
}

//--------------------------------Start Tanvi Teaching Hours for report and case ----------------------------------


		public ArrayList<ArrayList<String>> TeachinghoursReportandCase(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
			conn = dataSource.getConnection();
			String q = "";
			PreparedStatement stmt = null;
			
			q="select ROW_NUMBER() OVER(order by th.id ) as sr_no, th.id,th.theoretical_lecture,th.pract_tutor_semi_clinic_post,sm.system_name,dm.degree_name,prm.professional,c.course_name,c.course_code from edu_cc_tb_nch_teaching_hours th\r\n"
			+ "inner join edu_lms_course_mstr c on c.id = th.course_id\r\n"
			+ "inner join edu_lms_system_mstr sm on  sm.id=th.system_id\r\n"
			+ "inner join edu_lms_degree_mstr dm on dm.id=th.degree_id\r\n"
			+ "inner join edu_cc_tb_professional_mstr prm on prm.id=th.professional_id\r\n"
			+ "where th.course_id=? and th.status=1";
			
			stmt = conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("-----------Teaching hours Report and Case-----------" + stmt);
			ResultSet rs = stmt.executeQuery();
			
			int i = 0;
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(rs.getString("course_name"));// 0
			list.add(rs.getString("theoretical_lecture"));// 1
			list.add(rs.getString("pract_tutor_semi_clinic_post"));// 2
			list.add(rs.getString("theoretical_lecture"));// 3
			
			
			alist.add(list);
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
			return alist;
		}
		public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_ReportandCase(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {

				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
						+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic\r\n"
						+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
						+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
						+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
						+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
						+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
						+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
						+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
						+ "left join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and "
						+ "p.system_id=45 and p.course_id=? ";


				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("\n *****-----table2_Learning_Objectives_of_Course_ReportandCase-----***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("topic"));// 0
					list.add(rs.getString("generic_competency"));// 1
					list.add(rs.getString("subject_area"));// 2
					list.add(rs.getString("rep_und"));// 3
					list.add(rs.getString("specific_competency"));// 4
					list.add(rs.getString("slo_outcome"));// 5
					list.add(rs.getString("domain"));// 6
					list.add(rs.getString("guilberts_level"));// 7
					list.add(rs.getString("scope"));// 8
					list.add(rs.getString("method"));// 9
					list.add(rs.getString("formative"));// 10
					list.add(rs.getString("formative"));// 11
					list.add(rs.getString("int_departments"));// 11

					alist.add(list);
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
			return alist;
		}
		
		public ArrayList<ArrayList<String>> List_of_Topic_listReportCase(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.hours,p.id,STRING_AGG(st.sub_topic, '<==>') sub_topics\r\n"
						+ "	from edu_cc_tb_nch_list_of_topics_parent p\r\n"
						+ "	inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
						+ "	inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
						+ "	inner join edu_cc_tb_sub_topics_mstr st on st.topic_id= t.id\r\n"
						+ "	where p.status=0 and c.status=0 and p.system_id=45 and p.course_id=? group by 2,3,4 order by p.id asc ";
				
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****----List_of_Topic_listReportCase---***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("sub_topics"));// 2
					list.add(rs.getString("hours"));// 3
					alist.add(list);
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
			return alist;
		}
		
		public ArrayList<ArrayList<String>> Practical_listReportandCase(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
			conn = dataSource.getConnection();
			String q = "";
			PreparedStatement stmt = null;
			
			q="select distinct ROW_NUMBER() OVER(order by p.id ) as sr_no,pm.practical,c.sub_practical,c.demo_perform from edu_cc_tb_nch_list_of_practical_parent p\r\n"
					+ "inner join edu_cc_tb_nch_list_of_practical_child c on c.p_id= p.id\r\n"
					+ "inner join edu_cc_tb_nch_practical_mstr pm on pm.id=p.practical_id where p.course_id=?";
			
			stmt = conn.prepareStatement(q, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("----------Practical_listReportandCase----------" + stmt);
			ResultSet rs = stmt.executeQuery();
			
			int i = 0;
			while (rs.next()) {
			i++;
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(rs.getString("sr_no"));// 0
			list.add(rs.getString("practical"));// 1
			list.add(rs.getString("sub_practical"));// 2
			list.add(rs.getString("demo_perform"));// 3
			
			
			alist.add(list);
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
			return alist;
		}

//--------------------------------------End Tanvi Teaching Hours for report and case
		
////////////////Paper Layout  for Organon/////////////// ========================

		
		public ArrayList<ArrayList<String>> Paper_Layout_ListOrganon(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,\r\n"
						+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total,pl.time\r\n"
						+ "from edu_cc_tb_paper_layout pl\r\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\r\n"
						+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\r\n"
						+ "where pl.status='1' and pl.course_id=? ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("==============Paper Layout=by org=======" + stmt);
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("question_type"));// 0
					list.add(rs.getString("marks_questions"));// 1
					list.add(rs.getString("time"));// 2
					alist.add(list);
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
			return alist;
		}
		
		
//Paper Layout  for Organon=============================================================

		public ArrayList<ArrayList<String>> List_of_Topic_list_pdf_Organon(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
//				System.err.println("----getView_T2_data----" + course_id);
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.topic,c.sub_topic,c.hours,i.term from edu_cc_tb_nch_list_of_topics_parent p\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
						+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=?"
						+ " and p.topic_id not in (379,310,361,312,313)";


				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n ***13**-----5-----***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("sr_no"));// 0
					list.add(rs.getString("topic"));// 1
					list.add(rs.getString("term"));// 2
					list.add(rs.getString("hours"));// 3
					list.add(rs.getString("sub_topic"));// 4

					alist.add(list);
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
			return alist;
		}
		
		public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_HomUG_Medica(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {

				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
						+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic\r\n"
						+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
						+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
						+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
						+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
						+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
						+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
						+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
						+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
						+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and "
						+ "p.system_id=45 and  p.topic_id not in(409,410) and p.course_id=? order by topic";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
//				System.err.println("\n *****-----table2_Learning_Objectives_of_Course_HomUG---Riddhi--***** \n" + stmt);
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					i++;
					ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("topic"));// 0
					list.add(rs.getString("generic_competency"));// 1
					list.add(rs.getString("subject_area"));// 2
					list.add(rs.getString("rep_und"));// 3
					list.add(rs.getString("specific_competency"));// 4
					list.add(rs.getString("slo_outcome"));// 5
					list.add(rs.getString("domain"));// 6
					list.add(rs.getString("guilberts_level"));// 7
					list.add(rs.getString("scope"));// 8
					list.add(rs.getString("method"));// 9
					list.add(rs.getString("formative"));// 10
					list.add(rs.getString("formative"));// 11
					list.add(rs.getString("int_departments"));// 11

					alist.add(list);
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
			return alist;
		}
		
// 6 F I- Distribution_of_Theory_Exam========================================================
	  	 public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Medica_DAO(String course_id) {
	 			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	 			Connection conn = null;
	 			try {
	 				conn = dataSource.getConnection();
	 				String sql = "";
	 				PreparedStatement stmt = null;

	 				
	 				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\r\n"
	 						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
	 						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
	 						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.id = tm.id\r\n"
	 						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
	 						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
	 						+ "where tm.course_id= ? and pm.paper like 'PAPER I' and dte.status=0 group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id";

	 				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

	 				stmt.setInt(1, Integer.parseInt(course_id));
//	 				System.err.println("\n *****-----15-----***** \n"+stmt + "\n *****-----15-----***** \n");
	 				ResultSet rs = stmt.executeQuery();

	 				int i = 0;
	 				while (rs.next()) {
	 					
	 					i++;
	 					ArrayList<String> list = new ArrayList<String>();
	 					
	 					list.add(rs.getString("topic"));// 0
	 					list.add(rs.getString("term"));// 1
	 					list.add(rs.getString("marks"));// 2
	 					list.add(rs.getString("mcq"));// 3
	 					list.add(rs.getString("saq"));// 4
	 					list.add(rs.getString("laq"));// 5

	 					alist.add(list);
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
	 			return alist;
	 		}
	  	 
	  	 
// 6 F I- Distribution_of_Theory_Exam==========================================
	 	
// 6 F II- Distribution_of_Theory_Exam=====================================================
	  	 public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Medica_DAO(String course_id) {
	 			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	 			Connection conn = null;
	 			try {
	 				conn = dataSource.getConnection();
	 				String sql = "";
	 				PreparedStatement stmt = null;

	 				
	 				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\r\n"
	 						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
	 						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
	 						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.id = tm.id\r\n"
	 						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
	 						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
	 						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
	 						+ "where tm.course_id= ? and pm.paper like 'PAPER II' and dte.status=0 group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id";

	 				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

	 				stmt.setInt(1, Integer.parseInt(course_id));
//	 				System.err.println("\n *****-----15-----***** \n"+stmt + "\n *****-----15-----***** \n");
	 				ResultSet rs = stmt.executeQuery();

	 				int i = 0;
	 				while (rs.next()) {
	 					
	 					i++;
	 					ArrayList<String> list = new ArrayList<String>();
	 					
	 					list.add(rs.getString("topic"));// 0
	 					list.add(rs.getString("term"));// 1
	 					list.add(rs.getString("marks"));// 2
	 					list.add(rs.getString("mcq"));// 3
	 					list.add(rs.getString("saq"));// 4
	 					list.add(rs.getString("laq"));// 5

	 					alist.add(list);
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
	 			return alist;
	 		}

public ArrayList<ArrayList<String>> getpaperformatdata_Homeo_Medica(String course_id,String d3_desirable_know,String qt,String noofpaper) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qs ="";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		if(qt.equals("mcq")) {
			qs+=" and sf.mcq = 1 ";
		}
		if(qt.equals("saq")) {
			qs+=" and sf.saq = 1 ";
		}
		if(qt.equals("laq")) {
			qs+=" and sf.laq = 1 ";
		}
		
		String d3a[] = d3_desirable_know.split(",");
		String d3s = "";
		for(int d=0;d<d3a.length;d++) {
			if(d == 0) {
				d3s += " and (ttc.mk_dk = "+d3a[d]+" ";
			}else {
				d3s += " or ttc.mk_dk = "+d3a[d]+" ";
			}
		}
		d3s += " ) ";
		q="select distinct tm.topic,pm.paper,ttc.mk_dk\n"
				+ "from edu_cc_tb_sixf_distribution_of_theory_exam sf \n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_parent ttp on ttp.topic_id=sf.topic_id\n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_child ttc on ttc.p_id=ttp.id\n"
				+ "inner join edu_cc_tb_topics_mstr tm on tm.id=sf.topic_id\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_parent lop on lop.topic_id=sf.topic_id\n"
				+ "inner join edu_cc_tb_paper_mstr pm on pm.id= lop.paper_id\n"
				+ "where sf.course_id=? and paper='PAPER I' and ttp.status=0 and ttc.mk_dk!=0 "+d3s+" "+qs+" group by 1,2,3\n";
		
		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(course_id));
		System.err.println("stmt-----Riddhi-------"+stmt);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(rs.getString("topic"));//0
			list.add(rs.getString("paper"));//1 
		
			alist.add(list);
			
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
	return alist;
}

public ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List_Organon(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			
			sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
					+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.id = tm.id\r\n"
					+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
					+ "where tm.course_id= ? and pm.paper like 'PAPER I' and dte.status=0 group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("\n *****---Table6F_IDistribution_of_Theory_Exam_List_Organon--15--by Org1---***** \n"+stmt + "\n *****-----15--by Org1----***** \n");
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				
				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("topic"));// 0
				list.add(rs.getString("term"));// 1
				list.add(rs.getString("marks"));// 2
				list.add(rs.getString("mcq"));// 3
				list.add(rs.getString("saq"));// 4
				list.add(rs.getString("laq"));// 5
				list.add(rs.getString("paper"));// 6

				alist.add(list);
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
		return alist;
	}
// 6 F I- Distribution_of_Theory_Exam=========================================================

// 6 F II- Distribution_of_Theory_Exam===================================================
public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Organon(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			
			sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\r\n"
					+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.id = tm.id\r\n"
					+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
					+ "where dte.course_id= ? and pm.paper like 'PAPER II' and dte.status=0 and dte.topic_id not in (313)\r\n"
					+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("\n **Table6F_IIDistribution_of_Theory_Exam_List_Organon-------------***--10---15--  by Org  2---***** \n"+stmt + "\n *****-----15--by Org--2-***** \n");
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				
				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("topic"));// 0
				list.add(rs.getString("term"));// 1
				list.add(rs.getString("marks"));// 2
				list.add(rs.getString("mcq"));// 3
				list.add(rs.getString("saq"));// 4
				list.add(rs.getString("laq"));// 5

				alist.add(list);
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
		return alist;
	}

			public ArrayList<ArrayList<String>> getpaperformatdata_Homeo1(String course_id,String d3_desirable_know,String qt,String noofpaper) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String qs ="";
			try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(qt.equals("mcq")) {
			qs+=" and sf.mcq = 1 ";
			}
			if(qt.equals("saq")) {
			qs+=" and sf.saq = 1 ";
			}
			if(qt.equals("laq")) {
			qs+=" and sf.laq = 1 ";
			}
			
			String d3a[] = d3_desirable_know.split(",");
			String d3s = "";
			for(int d=0;d<d3a.length;d++) {
			if(d == 0) {
				d3s += " and (ttc.mk_dk = "+d3a[d]+" ";
			}else {
				d3s += " or ttc.mk_dk = "+d3a[d]+" ";
			}
			}
			d3s += " ) ";
			q="select distinct tm.topic,pm.paper,ttc.mk_dk\n"
				+ "from edu_cc_tb_sixf_distribution_of_theory_exam sf \n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_parent ttp on ttp.topic_id=sf.topic_id\n"
				+ "inner join edu_cc_tb_nch_theory_learning_object_child ttc on ttc.p_id=ttp.id\n"
				+ "inner join edu_cc_tb_topics_mstr tm on tm.id=sf.topic_id\n"
				+ "inner join edu_cc_tb_nch_list_of_topics_parent lop on lop.topic_id=sf.topic_id\n"
				+ "inner join edu_cc_tb_paper_mstr pm on pm.id= lop.paper_id\n"
				+ "where ttp.course_id=? and paper='PAPER I' and ttc.mk_dk!=0 "+d3s+" "+qs+" group by 1,2,3\n";
			
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(course_id));
//			System.err.println("stmt-----Blue Print  for Org-------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			ArrayList<String> list = new ArrayList<String>();
			
			list.add(rs.getString("topic"));//0
			list.add(rs.getString("paper"));//1 
			
			alist.add(list);
			
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
			return alist;
			}

//6 D - Evaluation Methods for Periodical Assessment===============================

public ArrayList<ArrayList<String>> Table_Evaluation_Methods_For_Periodical_AssessmentED(String course_id) {
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
Connection conn = null;
try {
conn = dataSource.getConnection();
String sql = "";
PreparedStatement stmt = null;


sql = " select DISTINCT ROW_NUMBER() OVER(order by e.id ) as sr_no,em.evaluation_method_pa\r\n"
		+ "from edu_cc_tb_evaluation_method_pa e\r\n"
		+ "inner join edu_cc_tb_evaluation_method_pa_mstr em on  em.id = e.evaluation_method_pa_id\r\n"
		+ "where e.status='1'and e.system_id=45 and e.course_id=?";

stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

stmt.setInt(1, Integer.parseInt(course_id));
//System.err.println("====================Evaluation_Methods===by ORG============="+stmt);
ResultSet rs = stmt.executeQuery();

int i = 0;
while (rs.next()) {
	
	i++;
	ArrayList<String> list = new ArrayList<String>();
	
	list.add(rs.getString("sr_no"));// 0
	list.add(rs.getString("evaluation_method_pa"));// 1

	alist.add(list);
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
return alist;
}

//End  6 D - Evaluation Methods for Periodical Assessment===============================
	
//6-B===================== Start  Scheme of Assessment (formative and Summative)===============================================================

public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse_ana(String course_id,String term) {
//	System.err.println("-------NCH--------"+role);
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		
		q="select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.term,p.no_of_exam,e.exam_type,p.id,concat(pm.professional,'-',d.degree_name) as year \r\n"
				+ "from edu_cc_tb_link_exam_and_course p\r\n"
				+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\r\n"
				+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\r\n"
				+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\r\n"
				+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id where p.course_id=? and t.term=? order by p.id asc";
		
		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(course_id));
		stmt.setString(2, term);
//		System.err.println("Child Table Data==========="+stmt);
		ResultSet rs = stmt.executeQuery();
		int ser = 1;
		String term1 = "";
		String term2 = "";
		String term3 = "";
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			
			if(ser == 1) {
				term1 += rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
			}
			if(ser == 2) {
				term1 += " + " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
			}
			list.add(term1);
			ser++;
				list.add(rs.getString("year"));// 0
		}
		alist.add(list);
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
	return alist;
}

//6-B===================== End   Scheme of Assessment (formative and Summative)=================================================================

////////////////Paper Layout /////////////// ========================

public ArrayList<ArrayList<String>> Paper_Layout_List_ana(String course_id) {
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
Connection conn = null;
try {
	conn = dataSource.getConnection();
	String sql = "";
	PreparedStatement stmt = null;
	
	sql = "select ROW_NUMBER() OVER(order by pl.id asc) as sr_no,pl.id,pl.num_questions,pl.marks_questions,pm.paper,\r\n"
			+ "qm.question_type,pl.status,(pl.num_questions * pl.marks_questions) as total\r\n"
			+ "from edu_cc_tb_paper_layout pl\r\n"
			+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\r\n"
			+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\r\n"
			+ "where pl.status='1' and pl.course_id=? order by pl.paper_id ";
	
	
	stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	stmt.setInt(1, Integer.parseInt(course_id));
//	System.err.println("==============Paper Layout========"+stmt);
	ResultSet rs = stmt.executeQuery();
	int i = 0;
	while (rs.next()) {
		i++;
		ArrayList<String> list = new ArrayList<String>();
		list.add(rs.getString("paper"));// 0
		list.add(rs.getString("sr_no"));// 0
		list.add(rs.getString("question_type"));// 1
		list.add(rs.getString("total"));// 2
		alist.add(list);
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
return alist;
}
//6 F II- Distribution_of_Theory_Exam===============================
	 public ArrayList<ArrayList<String>> Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAO(String course_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
					+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
					+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
					+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
					+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
					+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
					+ "where tm.course_id= ? and pm.paper like 'PAPER I' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
					+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id ";
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, Integer.parseInt(course_id));
			System.err.println("\n *****-----15---Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAO--***** \n"+stmt + "\n *****-----15-----***** \n");
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				
				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("topic"));// 0
				list.add(rs.getString("term"));// 1
				list.add(rs.getString("marks"));// 2
				list.add(rs.getString("mcq"));// 3
				list.add(rs.getString("saq"));// 4
				list.add(rs.getString("laq"));// 5
				list.add(rs.getString("paper"));//6

				alist.add(list);
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
		return alist;
	} 
	 public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List__ThemeDAO(String course_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;

				
				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
						+ "where tm.course_id= ? and pm.paper like 'PAPER II' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
						+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				stmt.setInt(1, Integer.parseInt(course_id));
				System.err.println("\n *****-----15----Table6F_II_Distribution_of_Theory_Exam_List__ThemeDAO-***** \n"+stmt + "\n *****-----15-----***** \n");
				ResultSet rs = stmt.executeQuery();

				int i = 0;
				while (rs.next()) {
					
					i++;
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("topic"));// 0
					list.add(rs.getString("term"));// 1
					list.add(rs.getString("marks"));// 2
					list.add(rs.getString("mcq"));// 3
					list.add(rs.getString("saq"));// 4
					list.add(rs.getString("laq"));// 5
					list.add(rs.getString("paper"));//6

					alist.add(list);
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
			return alist;
		}

	 
//Start of Table 2-Learning Objectives (Theory) =============================

			public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Psychology(String course_id) {
				ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				try {

					conn = dataSource.getConnection();
					String sql = "";
					PreparedStatement stmt = null;

					sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
							+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic,string_agg(lc.learning_outcome,'=') as learning_outcome\r\n"
							+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
							+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
							+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
							+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
							+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
							+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
							+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
							+ "left join edu_cc_tb_nch_topic_learning_outcome_parent lp on lp.topic_id=p.topic_id\r\n"
							+ "left join edu_cc_tb_nch_topic_learning_outcome_child lc on lc.p_id=lp.id \r\n"
							+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
							+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and p.course_id=? "
							+ " group by p.id,2,3,4,5,6,7,8,9,10,11,12,13,14,15 ";


					stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

					stmt.setInt(1, Integer.parseInt(course_id));
//					System.err.println("\n *****-----table2_Learning_Objectives_of_Psychology-----***** \n" + stmt);
					ResultSet rs = stmt.executeQuery();

					int i = 0;
					while (rs.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();

						list.add(rs.getString("topic"));// 0
						list.add(rs.getString("generic_competency"));// 1
						list.add(rs.getString("subject_area"));// 2
						list.add(rs.getString("rep_und"));// 3
						list.add(rs.getString("specific_competency"));// 4
						list.add(rs.getString("slo_outcome"));// 5
						list.add(rs.getString("domain"));// 6
						list.add(rs.getString("guilberts_level"));// 7
						list.add(rs.getString("scope"));// 8
						list.add(rs.getString("method"));// 9
						list.add(rs.getString("formative"));// 10
						list.add(rs.getString("formative"));// 11
						list.add(rs.getString("int_departments"));// 12
						list.add(rs.getString("learning_outcome"));// 13

						alist.add(list);
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
				return alist;
			}


//End of Table 2-Learning Objectives (Theory) ======================
			//Start of Table 2-Learning Objectives (Theory) =============================

				public ArrayList<ArrayList<String>> table2_Learning_Objectives_of_Course_Pharmacy(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {

						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;

						sql = "select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,c.generic_competency,c.subject_area,ru.rep_und,c.specific_competency,\r\n"
								+ "c.slo_outcome,d.domain,gl.guilberts_level,su.scope,c.tl_methods,fsm.formative,ru.rep_und,c.int_departments,tlm.method,t.topic,string_agg(lc.learning_outcome,'=') as learning_outcome\r\n"
								+ "from edu_cc_tb_nch_theory_learning_object_parent p\r\n"
								+ "inner join edu_cc_tb_nch_theory_learning_object_child c on c.p_id=p.id\r\n"
								+ "inner join edu_cc_tb_c3_domain_mstr d on d.id=c.blooms_domain\r\n"
								+ "inner join edu_cc_tb_e3_representation_understanding_mstr ru on ru.id=c.millers_level\r\n"
								+ "inner join edu_cc_tb_guilberts_level_mstr gl on gl.id=c.guilberts_level\r\n"
								+ "inner join edu_cc_tb_d3_scope_understanding_mstr su on su.id=c.mk_dk\r\n"
								+ "inner join edu_cc_tb_topics_mstr t on t.id=p.topic_id\r\n"
								+ "inner join edu_cc_tb_nch_topic_learning_outcome_parent lp on lp.topic_id=p.topic_id\r\n"
								+ "inner join edu_cc_tb_nch_topic_learning_outcome_child lc on lc.p_id=lp.id \r\n"
								+ "inner join edu_cc_tb_f3_teaching_learning_method_mstr tlm on tlm.id=c.tl_methods\r\n"
								+ "inner join edu_cc_tb_nch_formative_and_summative_mstr fsm on fsm.id=c.summ_assessment where p.status=0 and c.status=0 and p.system_id=45  and p.course_id=? "
								+ " group by p.id,2,3,4,5,6,7,8,9,10,11,12,13,14,15 order by topic";


						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						stmt.setInt(1, Integer.parseInt(course_id));
//						System.err.println("\n *****-----table2_Learning_Objectives_of_Course_Pharmacy----***** \n" + stmt);
						ResultSet rs = stmt.executeQuery();

						int i = 0;
						while (rs.next()) {
							i++;
							ArrayList<String> list = new ArrayList<String>();

							list.add(rs.getString("topic"));// 0
							list.add(rs.getString("generic_competency"));// 1
							list.add(rs.getString("subject_area"));// 2
							list.add(rs.getString("rep_und"));// 3
							list.add(rs.getString("specific_competency"));// 4
							list.add(rs.getString("slo_outcome"));// 5
							list.add(rs.getString("domain"));// 6
							list.add(rs.getString("guilberts_level"));// 7
							list.add(rs.getString("scope"));// 8
							list.add(rs.getString("method"));// 9
							list.add(rs.getString("formative"));// 10
							list.add(rs.getString("formative"));// 11
							list.add(rs.getString("int_departments"));// 12
							list.add(rs.getString("learning_outcome"));// 13

							alist.add(list);
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
					return alist;
				}
				//6-B===================== Start  Scheme of Assessment (formative and Summative)===============================================================

				public ArrayList<ArrayList<String>> get6BSchemeViewdatabyCourse_Pharmacy(String course_id,String term) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						
						
						q="select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.term,p.no_of_exam,e.exam_type,p.id,concat(pm.professional,'-',d.degree_name) as year \r\n"
								+ "from edu_cc_tb_link_exam_and_course p\r\n"
								+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\r\n"
								+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\r\n"
								+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\r\n"
								+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id where p.course_id=? and t.term=? order by p.id asc";
						
						stmt = conn.prepareStatement(q);
						stmt.setInt(1, Integer.parseInt(course_id));
						stmt.setString(2, term);
//						System.err.println("Child Table Data=======111===="+stmt);
						ResultSet rs = stmt.executeQuery();
						int ser = 1;
						String term1 = "";
						String term2 = "";
						String term3 = "";
						ArrayList<String> list = new ArrayList<String>();
						while (rs.next()) {
							
							if(ser == 1) {
								term1 += rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
							}
							if(ser == 2) {
								term1 += " + " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
							}
							list.add(term1);
							ser++;
								list.add(rs.getString("year"));// 0
						}
						alist.add(list);
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
					return alist;
				}

				//6-B===================== End   Scheme of Assessment (formative and Summative)==================

				
	//-------------Start Theaching Hours Theory of pharmacy
				public ArrayList<ArrayList<String>> List_of_Topic_list_Pharma(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;

						sql = "select DISTINCT t.topic,c.sub_topic,c.hours,i.term from edu_cc_tb_nch_list_of_topics_parent p\r\n"
								+ "inner join edu_cc_tb_nch_list_of_topics_child c on c.p_id= p.id\r\n"
								+ "inner join edu_cc_tb_topics_mstr t on t.id= p.topic_id\r\n"
								+ "inner join edu_cc_tb_i3_term_mstr i on i.id= c.term_id where p.status=0 and c.status=0 and t.status=1 and i.status=1 and p.system_id=45 and p.course_id=? ";


						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						stmt.setInt(1, Integer.parseInt(course_id));
						System.err.println("\n *****-12--Pharma ----***** \n" + stmt);
						ResultSet rs = stmt.executeQuery();

						int i = 0;
						int ser = 1;
						while (rs.next()) {
							i++;
							ArrayList<String> list = new ArrayList<String>();

							list.add(String.valueOf(ser));// 0
							list.add(rs.getString("topic"));// 1
							list.add(rs.getString("sub_topic"));// 2
							list.add(rs.getString("hours"));// 3
							list.add(rs.getString("term"));// 4

							alist.add(list);
							
							ser++;
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
					return alist;
				}

				//-------------End Theaching Hours Theory of Oharmacy

				
				public ArrayList<ArrayList<String>> Table6F_I_Distribution_of_Theory_Exam_List_Pharmacy(String course_id) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						String sql = "";
						PreparedStatement stmt = null;

						sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
								+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
								+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
								+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
								+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
								+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
								+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
								+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
								+ "where tm.course_id= ? and pm.paper like 'PAPER I' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
								+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id order by topic";
						
						stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

						stmt.setInt(1, Integer.parseInt(course_id));
						System.err.println("\n *****-----15---Table6F_I_Distribution_of_Theory_Exam_List__ThemeDAOpHARMA--***** \n"+stmt + "\n *****-----15-----***** \n");
						ResultSet rs = stmt.executeQuery();

						int i = 0;
						while (rs.next()) {
							
							i++;
							ArrayList<String> list = new ArrayList<String>();
							
							list.add(rs.getString("topic"));// 0
							list.add(rs.getString("term"));// 1
							list.add(rs.getString("marks"));// 2
							list.add(rs.getString("mcq"));// 3
							list.add(rs.getString("saq"));// 4
							list.add(rs.getString("laq"));// 5
							list.add(rs.getString("paper"));//6

							alist.add(list);
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
					return alist;
				} 

				 public ArrayList<ArrayList<String>> Table6F_II_Distribution_of_Theory_Exam_List_Pharmacy(String course_id) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						try {
							conn = dataSource.getConnection();
							String sql = "";
							PreparedStatement stmt = null;

							
							sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks,pm.paper\r\n"
									+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
									+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
									+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
									+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
									+ "inner join edu_cc_tb_nch_list_of_topics_child tc  on tc.p_id = tp.id\r\n"
									+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
									+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
									+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
									+ "where tm.course_id= ? and pm.paper like 'PAPER II' and dte.status=0 and twmc.status=0 and tp.status=0\r\n"
									+ "group by topic,mcq,saq,laq,term,marks,dte.id,tm.id,tp.id,pm.id,tc.id,itm.id,twmc.id,twmp.id ";

							stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

							stmt.setInt(1, Integer.parseInt(course_id));
//							System.err.println("\n *****-----15-----***** \n"+stmt + "\n *****-----15-----***** \n");
							ResultSet rs = stmt.executeQuery();

							int i = 0;
							while (rs.next()) {
								
								i++;
								ArrayList<String> list = new ArrayList<String>();
								
								list.add(rs.getString("topic"));// 0
								list.add(rs.getString("term"));// 1
								list.add(rs.getString("marks"));// 2
								list.add(rs.getString("mcq"));// 3
								list.add(rs.getString("saq"));// 4
								list.add(rs.getString("laq"));// 5
								list.add(rs.getString("paper"));//6

								alist.add(list);
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
						return alist;
					}
											
											
															// 6 F II- Distribution_of_Theory_Exam=====================================================
				  	 public ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List_Psycology(String course_id) {
				 			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				 			Connection conn = null;
				 			try {
				 				conn = dataSource.getConnection();
				 				String sql = "";
				 				PreparedStatement stmt = null;

				 				
				 				sql="select distinct tm.topic,dte.mcq,dte.saq,dte.laq,itm.term,twmp.marks\r\n"
				 						+ "from edu_cc_tb_sixf_distribution_of_theory_exam dte\r\n"
				 						+ "inner join edu_cc_tb_topics_mstr tm on tm.id=dte.topic_id\r\n"
				 						+ "inner join edu_cc_tb_nch_list_of_topics_parent tp on tp.topic_id=dte.topic_id\r\n"
				 						+ "inner join edu_cc_tb_paper_mstr pm on pm.id=tp.paper_id\r\n"
				 						+ "inner join edu_cc_tb_nch_list_of_topics_child tc on tc.p_id = tp.id\r\n"
				 						+ "inner join edu_cc_tb_i3_term_mstr itm on itm.id=tc.term_id\r\n"
				 						+ "inner join edu_cc_tb_define_topic_wise_marks_child twmc on twmc.topic_id=dte.topic_id\r\n"
				 						+ "inner join edu_cc_tb_define_topic_wise_marks_parent twmp on twmp.id=twmc.p_id\r\n"
				 						+ "where tm.course_id= ? and dte.status=0 ";

				 				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

				 				stmt.setInt(1, Integer.parseInt(course_id));
				 				System.err.println("\n *****-----15-Psycology***** \n"+stmt + "\n *****-----15-----***** \n");
				 				ResultSet rs = stmt.executeQuery();

				 				int i = 0;
				 				while (rs.next()) {
				 					
				 					i++;
				 					ArrayList<String> list = new ArrayList<String>();
				 					
				 					list.add(rs.getString("topic"));// 0
				 					list.add(rs.getString("term"));// 1
				 					list.add(rs.getString("marks"));// 2
				 					list.add(rs.getString("mcq"));// 3
				 					list.add(rs.getString("saq"));// 4
				 					list.add(rs.getString("laq"));// 5

				 					alist.add(list);
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
				 			return alist;
				 		}

///	table2_Learning_Objectives_of_Course_Pharmacy---------------------    order by topic";
		
				  	 
				  	public ArrayList<ArrayList<String>> Non_Lecture_Activities_List_Pharmacy(String course_id) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						try {
							conn = dataSource.getConnection();
							String sql = "";
							PreparedStatement stmt = null;

							sql = " select DISTINCT c.non_lecture_tl_method,c.no_of_hours,p.id\r\n"
									+ " from edu_cc_tb_nch_add_non_lecture_activities_parent p\r\n"
									+ " inner join edu_cc_tb_nch_add_non_lecture_activities_child c on c.p_id= p.id\r\n"
									+ " where p.status=0 and c.status=0 and  p.system_id=45 and p.course_id=? order by p.id asc";

							stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

							stmt.setInt(1, Integer.parseInt(course_id));
//							System.err.println("\n *****----Non_Lecture_Activities_List_HOM-----***** \n" + stmt);
							ResultSet rs = stmt.executeQuery();

							int i = 0;
							while (rs.next()) {
								i++;
								ArrayList<String> list = new ArrayList<String>();

								list.add(rs.getString("non_lecture_tl_method"));// 1
								alist.add(list);
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
						return alist;
					}
				  	
			public ArrayList<ArrayList<String>> get6DSchemeViewdatabyCourse(String course_id,String term) {
				  		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
				  		Connection conn = null;
				  		String q = "";
				  		
				  		try {
				  			conn = dataSource.getConnection();
				  			PreparedStatement stmt = null;
				  			
				  			
				  			q="select DISTINCT ROW_NUMBER() OVER(order by p.id ) as sr_no,t.term,p.no_of_exam,e.exam_type,p.id,concat(pm.professional,'-',d.degree_name) as year \r\n"
				  					+ "from edu_cc_tb_link_exam_and_course p\r\n"
				  					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\r\n"
				  					+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\r\n"
				  					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\r\n"
				  					+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id where p.course_id=? and t.term=? order by p.id asc";
				  			
				  			stmt = conn.prepareStatement(q);
							stmt.setInt(1, Integer.parseInt(course_id));
							stmt.setString(2, term);
							System.err.println("Child Table Data=======12===="+stmt);
							ResultSet rs = stmt.executeQuery();
							int ser = 1;
							String term1 = "";
							String term2 = "";
							String term3 = "";
							ArrayList<String> list = new ArrayList<String>();
							while (rs.next()) {
								
								if(ser == 1) {
									term1 += rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
								}
								if(ser == 2) {
									term1 += " + " + rs.getString("no_of_exam") + "- " + rs.getString("exam_type");
								}
								list.add(term1);
								ser++;
									list.add(rs.getString("year"));// 0
							}
							alist.add(list);
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
				  		return alist;
				  	}
				
}