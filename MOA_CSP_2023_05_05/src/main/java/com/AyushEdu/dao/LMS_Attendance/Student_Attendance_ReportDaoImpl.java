package com.AyushEdu.dao.LMS_Attendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import freemarker.core.ParseException;

@Repository
public class Student_Attendance_ReportDaoImpl implements Student_Attendance_ReportDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<ArrayList<String>>student_attendance_report(String month,String year) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			DateFormat format2 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
			Date today = new Date();  
			String currdate1 = new SimpleDateFormat("yyyy-MM").format(today);
			Date month_year_date = format2.parse(currdate1);
			String SrMon = new SimpleDateFormat("MM").format(month_year_date);
			int lastDay =0;
			 System.err.println("monthxxx-"+month+"-yearxxx-"+year);
			 if(month.equals("02")){
					lastDay=28;
			 }else if(month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {				
					lastDay=30;
			 }else {
					lastDay=31;
			 }
			 System.err.println("month-"+month+"-year-"+year);
			 String temp="";
			 for (int i=1;i<=lastDay;i++) {
				 if(i==lastDay) {
				 temp+="count(username)filter(where date::date='"+i+"/"+month+"/"+year+"') as _"+i+"\n ";
				 }else {
					 temp+="count(username)filter(where date::date='"+i+"/"+month+"/"+year+"') as _"+i+"\n, ";
				 }
			 }
			q="select lo.username,"+temp+" from logininformation lo left join edu_lms_student_attendance at on at.department=lo.username group by extract(mon from current_timestamp ) ,lo.username";
//					+ "count(username)filter(where date::date='01/"+month+"/2022') as _1,\n"
//					+ "count(username)filter(where date::date='02/"+month+"/2022') as _2,\n"
//					+ "count(username)filter(where date::date='03/"+month+"/2022') as _3,\n"
//					+ "count(username)filter(where date::date='04/"+month+"/2022') as _4,\n"
//					+ "count(username)filter(where date::date='05/"+month+"/2022') as _5,\n"
//					+ "count(username)filter(where date::date='06/"+month+"/2022') as _6, \n"
//					+ "count(username)filter(where date::date='07/"+month+"/2022') as _7, \n"
//					+ "count(username)filter(where date::date='08/"+month+"/2022') as _8, \n"
//					+ "count(username)filter(where date::date='09/"+month+"/2022') as _9, \n"
//					+ "count(username)filter(where date::date='10/"+month+"/2022') as _10, \n"
//					+ "count(username)filter(where date::date='11/"+month+"/2022') as _11, \n"
//					+ "count(username)filter(where date::date='12/"+month+"/2022') as _12, \n"
//					+ "count(username)filter(where date::date='13/"+month+"/2022') as _13, \n"
//					+ "count(username)filter(where date::date='14/"+month+"/2022') as _14, \n"
//					+ "count(username)filter(where date::date='15/"+month+"/2022') as _15, \n"
//					+ "count(username)filter(where date::date='16/"+month+"/2022') as _16, \n"
//					+ "count(username)filter(where date::date='17/"+month+"/2022') as _17, \n"
//					+ "count(username)filter(where date::date='18/"+month+"/2022') as _18, \n"
//					+ "count(username)filter(where date::date='19/"+month+"/2022') as _19, \n"
//					+ "count(username)filter(where date::date='20/"+month+"/2022') as _20, \n"
//					+ "count(username)filter(where date::date='21/"+month+"/2022') as _21, \n"
//					+ "count(username)filter(where date::date='22/"+month+"/2022') as _22, \n"
//					+ "count(username)filter(where date::date='23/"+month+"/2022') as _23, \n"
//					+ "count(username)filter(where date::date='24/"+month+"/2022') as _24, \n"
//					+ "count(username)filter(where date::date='25/"+month+"/2022') as _25, \n"
//					+ "count(username)filter(where date::date='26/"+month+"/2022') as _26, \n"
//					+ "count(username)filter(where date::date='27/"+month+"/2022') as _27, \n"
//					+ "count(username)filter(where date::date='28/"+month+"/2022') as _28,\n";
			
//					if(month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")|| month.equals("02")) {				
//						q = q + "count(username)filter(where date::date='29/"+month+"/2022') as _29 ,\n"
//								+ "count(username)filter(where date::date='30/"+month+"/2022') as _30 \n"
//								+ "from logininformation lo left join edu_lms_student_attendance at \n"
//								+ "on at.name=lo.username \n"
//								+ "group by extract(mon from current_timestamp ) ,lo.username";
//					}
//					if(month.equals("01") || month.equals("03") || month.equals("05") || 
//							month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12")) {
//						q = q   + "count(username)filter(where date::date='29/"+month+"/2022') as _29 ,\n"
//								+ "count(username)filter(where date::date='30/"+month+"/2022') as _30 ,\n"
//								+ "count(username)filter(where date::date='31/"+month+"/2022') as _31 \n"
//								+ "from logininformation lo left join edu_lms_student_attendance at \n"
//								+ "on at.name=lo.username \n"
//								+ "group by extract(mon from current_timestamp ) ,lo.username";
//					}
			
				stmt = conn.prepareStatement(q);
				System.err.println("TMT------>"+stmt);
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int s_count=0;
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("username"));
				for(int i=1; i<=lastDay; i++) {
					if (rs.getString("_"+i).equals("0")) {
						list.add("A");
					}else if (rs.getString("_"+i).equals("1")){
						list.add("P");
						s_count++;
					}
				}
				list.add(s_count+"");
				alist.add(list);
			}			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  catch (java.text.ParseException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.err.println("ALLIISSTT---"+alist);
		return alist;
	}
	
	
	//===============================================Monthly-Print====================================================================
	
		@Override
		public ArrayList<ArrayList<String>>atdnsreport(String print,String month_year) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				
				q="select lo.username,\"+temp+\" from logininformation lo left join edu_lms_student_attendance at on at.department=lo.username group by extract(mon from current_timestamp ) ,lo.username";
				
	stmt = conn.prepareStatement(q);
				
				for(int i=1;i<=31;i++) {
					
					stmt.setString(i, month_year);
				}
				
				Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
				int day = calendar2.get(Calendar.DATE);
				
				 Date today = new Date();  
				 String current_time = new SimpleDateFormat("yyyy-MM-dd").format(today);
				 month_year+="-"+day;
				 DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				 Date month_year_date = null;
				try {
					month_year_date = format2.parse(month_year);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 String search_time = new SimpleDateFormat("yyyy-MM-dd").format(month_year_date);			 

				 
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(today);  

		        calendar.add(Calendar.MONTH, 1);  
		        calendar.set(Calendar.DAY_OF_MONTH, 1);  
		        calendar.add(Calendar.DATE, -1);  

		        String lastDay;
		        String SrMon = new SimpleDateFormat("MM").format(month_year_date);
		        
		    
		        if(SrMon.equals("02")){
					lastDay="28";
				}else if(SrMon.equals("04") || SrMon.equals("06") || SrMon.equals("09") || SrMon.equals("11")) {				
					lastDay="30";
				}else {
					lastDay="31";
				}
		        
				ResultSet rs = stmt.executeQuery();
				System.err.println("STMT_M_A---"+stmt);
				int ser=1;
				float total_paresent=0;
				//int total_absent=0;
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
				//	list.add(String.valueOf(ser));
					list.add(rs.getString("name"));
					
					if(current_time.compareTo(search_time)>0) {
						
						for (int i = 1; i <= 31; i++) {
						if (i > 9) {
							list.add((rs.getString(i) == null) ? "P" : rs.getString(i));

							if (rs.getString(i) == null) {
								total_paresent += 1;
							} else {
								if (rs.getString(i).equals("H")) {
									total_paresent += 1;
								}
								if (rs.getString(i).equals("WFH") || rs.getString(i).equals("CL") || rs.getString(i).equals("EL") || rs.getString(i).equals("T")) {
									total_paresent += +1;
								}
							}

						} else {
							list.add((rs.getString("0" + i) == null) ? "P" : rs.getString("0" + i));
							if (rs.getString(i) == null) {
								total_paresent += 1;
							} else {
								if (rs.getString(i).equals("H")) {
									total_paresent += 1;
								}
								if (rs.getString(i).equals("WFH") || rs.getString(i).equals("CL") || rs.getString(i).equals("EL") || rs.getString(i).equals("T")) {
									total_paresent += +1;
								}
							}

						}

					}
						
//					if (print.equals("")) {
	//
//						for (int j = day + 1; j <= 31; j++) {
	//
//							list.add("");
//						}
	//
//					}
						
					}
					else {
						for (int i = 1; i <= day; i++) {
							if (i > 9) {
								list.add((rs.getString(i) == null) ? "P" : rs.getString(i));

								if (rs.getString(i) == null) {
									total_paresent += 1;
								} else {
									if (rs.getString(i).equals("H")) {
										total_paresent += 1;
									}
									if (rs.getString(i).equals("WFH") || rs.getString(i).equals("CL") || rs.getString(i).equals("EL") || rs.getString(i).equals("T")) {
										total_paresent += +1;
									}

								}

							} else {
								list.add((rs.getString("0" + i) == null) ? "P" : rs.getString("0" + i));
								if (rs.getString(i) == null) {
									total_paresent += 1;
								} else {
									if (rs.getString(i).equals("H")) {
										total_paresent += 1;
									}
									if (rs.getString(i).equals("WFH") || rs.getString(i).equals("CL") || rs.getString(i).equals("EL") || rs.getString(i).equals("T")) {
										total_paresent += +1;
									}
								}

							}

						}
						if (print.equals("")) {

							for (int j = day + 1; j <= 31; j++) {

								list.add("");
							}

						}
				
					}
					
					DecimalFormat format = new DecimalFormat("0.#");
					
					//HARSH-------------------
					
					if(lastDay.equals("28")) {
						
						if(current_time.compareTo(search_time)==0) {
							
							list.add(String.valueOf(format.format(total_paresent+0)));// 9
						}else {
						
							list.add(String.valueOf(format.format(total_paresent-3)));// 9
						}
					
					}else if (lastDay.equals("30")) {
						
						if(current_time.compareTo(search_time)==0) {
							
							list.add(String.valueOf(format.format(total_paresent+0)));// 9
							
						}else {
								list.add(String.valueOf(format.format(total_paresent-1)));
						}
						
					}else {
						list.add(String.valueOf(format.format(total_paresent)));// 9					
					}
					
			
					
					
					total_paresent=0;
					alist.add(list);
					ser+=1;
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
		public List<Map<String, Object>> getCourselistofStudent(String system,String degree,String professional) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("system============----------------"+system);
			try {
				conn = dataSource.getConnection();

				q="select distinct cm.id,course_name\n"
						+ "from  edu_cc_link_system_degree_professional_course m\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=m.course_id\n"
						+ "where m.system_id=? and m.degree_id=? and m.professional_id=? and cm.type_of_content_id=5";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(system));
				stmt.setInt(2,Integer.parseInt(degree));
				stmt.setInt(3,Integer.parseInt(professional));
				System.err.println("StudentAttReport----------->  "+stmt);
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int countFunction=1;
				int countFunctionDelete=1;
				int countview=1;
				
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String vd = "";
					 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview VIEWdetails' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId"+countview+"' value='"+rs.getInt("id")+"'></i></a> </li></ul>";
			
					 countFunction+=1;
					 countFunctionDelete+=1;
					 countview += 1;
						
					 columns.put("vd", vd);
					
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
		public List<Map<String, Object>> getStudcountPA(String course_name,String userid,String role,String attendance) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				
				String tb_name = "";
				if(role.contains("NCISM")) {
					tb_name = " edu_lms_student_details ";
				}
				if(role.contains("NCH")) {
					tb_name = "edu_lms_nch_student_details";
				}

				q="select count(*) as total_present\n"
						+ "from edu_lms_student_attendance_report sr\n"
						+ "inner join  "+tb_name+"  sd on sd.ayush_id=sr.ayush_id\n"
						+ "inner join edu_lms_course_mstr cm on cast(cm.id as text)=sr.course_name\n"
						+ "inner join logininformation l on l.email_id=sd.email\n"
						+ "inner join userroleinformation u on u.user_id=l.userid\n"
						+ "inner join roleinformation r on r.role_id=u.role_id\n"
						+ "where l.userid= ? and sr.course_name= ? and r.role=? and sr.attendance=?";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(userid));
				stmt.setString(2, course_name);
				stmt.setString(3, role);
				stmt.setString(4, attendance);
				System.err.println("getStudcountPA----------->  "+stmt);
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
		
		
		@Override
		public List<Map<String, Object>> getStudentSysDegProf(String userid,String role) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String tb_name = "";
			if(role.contains("NCISM")) {
				tb_name = " edu_lms_student_details ";
			}
			if(role.contains("NCH")) {
				tb_name = "edu_lms_nch_student_details";
			}
			try {
				conn = dataSource.getConnection();

				q="select sm.id as system,dm.id as degree,sd.semester from logininformation l \n"
						+ "inner join "+tb_name+" sd on l.email_id=sd.email\n"
						+ "inner join edu_lms_system_mstr sm on sm.id=sd.system\n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "where l.userid = ?";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(userid));
				System.err.println("getStudentSysDegProf----------->  "+stmt);
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
		
		@Override
		public ArrayList<ArrayList<String>> getPopup_ChildDatalist(String userid,String course_name,String role,String role_id,String month,String year, String instid) throws java.text.ParseException  {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String tb_name = "";
			if(role.contains("NCISM")) {
				tb_name = " edu_lms_student_details ";
			}
			if(role.contains("NCH")) {
				tb_name = "edu_lms_nch_student_details";
			}
			
			DateFormat format2 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
			Date today = new Date();  
			String currdate1 = new SimpleDateFormat("yyyy-MM").format(today);
			Date month_year_date = format2.parse(currdate1);
			String SrMon = new SimpleDateFormat("MM").format(month_year_date);
			int lastDay =0;
			String searchvalue = "";
			 
			 if(month.equals("02")){
					lastDay=28;
			 }else if(month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11")) {				
					lastDay=30;
			 }else {
					lastDay=31;
			 }
			 
			 if(!month.equals("0")){
					searchvalue+=" and to_Char(attendance_date,'mm')='" + month + "'";
//					cast(exam_serial as character varying) like ?
			}
			if(!year.equals("0")){
					searchvalue+=" and to_Char(attendance_date,'yyyy')='" + year + "'";
			}
			
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
//				q="select to_char(attendance_date,'dd-MON-yyyy') as attendance_date,attendance \n"
//						+ "from edu_lms_student_attendance_report sr\n"
//						+ "inner join "+tb_name+" sd on sd.ayush_id=sr.ayush_id\n"
//								+ "inner join edu_lms_course_mstr cm on cast(cm.id as text)=sr.course_name\n"
//						+ "inner join logininformation l on l.aadhar_no=sd.aadhar_card\n"
//						+ "inner join userroleinformation u on u.user_id=l.userid\n"
//						+ "inner join roleinformation r on r.role_id=u.role_id\n"
//						+ "where l.userid= ? and sr.course_name= ? and r.role=?";
				
				
				if(role.contains("NCH")) {
					
					
					 String temp="";
					 for (int i=1;i<=lastDay;i++) {
						 if(i==lastDay) {
							 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
							 		+ "(select attendance_date,attendance from edu_lms_student_attendance_report sr\n"
							 		+ "inner join edu_lms_nch_student_details tdt on tdt.ayush_id=sr.ayush_id\n"
							 		+ "inner join logininformation l1 on l1.email_id=tdt.email\n"
							 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
							 		+ "and l1.userid=l.userid group by attendance_date,attendance ) a\n"
							 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?) e on a.attendance_date = e.event_date ) as _"+i+"\n ";
						 }else {
							 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
							 		+ "(select attendance_date,attendance from edu_lms_student_attendance_report sr\n"
							 		+ "inner join edu_lms_nch_student_details tdt on tdt.ayush_id=sr.ayush_id\n"
							 		+ "inner join logininformation l1 on l1.email_id=tdt.email\n"
							 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
							 		+ "and l1.userid=l.userid group by attendance_date,attendance ) a\n"
							 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?)e on a.attendance_date = e.event_date ) as _"+i+"\n, ";
						 }
					 }
						q = "select distinct " + temp + " from edu_lms_student_attendance_report fm\n"
								+ "inner join edu_lms_nch_student_details td on td.ayush_id=fm.ayush_id\n"
								+ " inner join edu_lms_course_mstr cm on cast(cm.id as text)=fm.course_name\n"
								+ " inner join logininformation l on l.email_id=td.email\n"
								+ "inner join userroleinformation u on u.user_id=l.userid\n"
								+ "inner join roleinformation rl on rl.role_id=u.role_id\n"
								+ "where l.userid= ? and fm.course_name= ? and rl.role=?  " +searchvalue;
						
						
					}
					if(role.contains("NCISM")) {
						
						String temp="";
						 for (int i=1;i<=lastDay;i++) {
							 if(i==lastDay) {
								 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
									 		+ "(select attendance_date,attendance from edu_lms_student_attendance_report sr\n"
									 		+ "inner join edu_lms_student_details tdt on tdt.ayush_id=sr.ayush_id\n"
									 		+ "inner join logininformation l1 on l1.email_id=tdt.email\n"
									 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
									 		+ "and l1.userid=l.userid group by attendance_date,attendance ) a\n"
									 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?) e on a.attendance_date = e.event_date ) as _"+i+"\n ";
							 }else {
								 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
									 		+ "(select attendance_date,attendance from edu_lms_student_attendance_report sr\n"
									 		+ "inner join edu_lms_student_details tdt on tdt.ayush_id=sr.ayush_id\n"
									 		+ "inner join logininformation l1 on l1.email_id=tdt.email\n"
									 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
									 		+ "and l1.userid=l.userid group by attendance_date,attendance ) a\n"
									 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?)e on a.attendance_date = e.event_date ) as _"+i+"\n, ";
							 }
						 }
						 q = "select distinct " + temp + " from edu_lms_student_attendance_report fm\n"
									+ "inner join edu_lms_student_details td on td.ayush_id=fm.ayush_id\n"
									+ " inner join edu_lms_course_mstr cm on cast(cm.id as text)=fm.course_name\n"
									+ " inner join logininformation l on l.email_id=td.email\n"
									+ "inner join userroleinformation u on u.user_id=l.userid\n"
									+ "inner join roleinformation rl on rl.role_id=u.role_id\n"
									+ "where l.userid= ? and fm.course_name= ? and rl.role=?  " +searchvalue;
							
					}
				
				stmt = conn.prepareStatement(q);
				for (int i=1;i<=lastDay;i++) {
				stmt.setInt(i, Integer.parseInt(instid));
				}
				stmt.setInt(lastDay+1, Integer.parseInt(userid));
				stmt.setString(lastDay+2, course_name);
				if (role.equals("Faculty_NCISM")) {
					stmt.setString(lastDay+3, "Student_NCISM");
				} else if (role.equals("Faculty_NCH")) {
					stmt.setString(lastDay+3, "Student_NCH");
				}
				stmt.setString(lastDay+3, role);
				System.err.println("\n\n----------STUDENT-ATTENDANCE-REPORT----------\n"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int p_count=0;
					ArrayList<String> list = new ArrayList<String>();
					
					for(int i=1; i<=lastDay; i++) {
//						System.err.println(i);
						if(rs.getString("_"+i) != "" && rs.getString("_"+i) != null) {
							
							list.add(rs.getString("_"+i));
							if (rs.getString("_"+i).equals("P")) {
								p_count++;
							}
							
						}else {
							list.add("-");
						}
					}
					list.add(String.valueOf(p_count));
					list.add(String.valueOf(month));
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
}
