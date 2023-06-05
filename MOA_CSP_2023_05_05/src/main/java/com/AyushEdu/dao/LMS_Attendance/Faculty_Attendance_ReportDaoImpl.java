package com.AyushEdu.dao.LMS_Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Faculty_Attendance_ReportDaoImpl implements Faculty_Attendance_ReportDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<ArrayList<String>>faculty_attendance_report(String month,String year,String teach_code_or_ayush_id,String role,String instid) {
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
//			if(!name.equals("")){
//				searchvalue+=" and upper(fm.name)='" + name.toUpperCase() + "'";
//			}
//			if(!teach_code.equals("")){
//				searchvalue+=" and upper(fm.teacher_code)='" + teach_code.toUpperCase() + "'";
//			}
			
			if(role.contains("NCH")) {
				
			
			 String temp="";
			 for (int i=1;i<=lastDay;i++) {
				 if(i==lastDay) {
					 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
					 		+ "(select attendance_date,attendance from edu_lms_faculty_attendance fa\n"
					 		+ "inner join tb_nch_add_teacher_details tdt on tdt.ayush_id=fa.ayush_id\n"
					 		+ "inner join logininformation l1 on l1.userid=tdt.user_id\n"
					 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
					 		+ "and l1.userid=l.userid group by name,attendance_date,attendance ) a\n"
					 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?) e on a.attendance_date = e.event_date ) as _"+i+"\n ";
				 }else {
					 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
					 		+ "(select attendance_date,attendance from edu_lms_faculty_attendance fa\n"
					 		+ "inner join tb_nch_add_teacher_details tdt on tdt.ayush_id=fa.ayush_id\n"
					 		+ "inner join logininformation l1 on l1.userid=tdt.user_id\n"
					 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
					 		+ "and l1.userid=l.userid group by name,attendance_date,attendance ) a\n"
					 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?)e on a.attendance_date = e.event_date ) as _"+i+"\n, ";
				 }
			 }
				q = "select distinct l.userid,concat(td.first_name,' ',td.middle_name,' ',td.last_name) as name,td.teacher_code, " + temp + " from edu_lms_faculty_attendance fm\n"
						+ "inner join tb_nch_add_teacher_details td on td.ayush_id=fm.ayush_id\n"
						+ "inner join userroleinformation u on u.user_id=td.user_id\n"
						+ "inner join roleinformation rl on rl.role_id=u.role_id\n"
						+ "inner join logininformation l on l.userid=td.user_id where fm.id!=0 and rl.role=? and fm.ayush_id=?  " +searchvalue;
				
				
			}
			if(role.contains("NCISM")) {
				
				String temp="";
				 for (int i=1;i<=lastDay;i++) {
					 if(i==lastDay) {
						 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
						 		+ "(select attendance_date,attendance from edu_lms_faculty_attendance fa\n"
						 		+ "  inner join edu_lms_faculty_nch elfn on elfn.teacher_code=fa.teacher_code\n"
						 		+ "inner join logininformation l1 on l1.userid=elfn.userid::int\n"
						 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
						 		+ "and l1.userid=l.userid group by name,attendance_date,attendance ) a\n"
						 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?) e on a.attendance_date = e.event_date ) as _"+i+"\n ";
					 }else {
						 temp+="(select distinct case when a.attendance_date::date = e.event_date::date then e.event_name else a.attendance end from\n"
						 		+ "(select attendance_date,attendance from edu_lms_faculty_attendance fa\n"
						 		+ "inner join edu_lms_faculty_nch elfn on elfn.teacher_code=fa.teacher_code\n"
						 		+ "inner join logininformation l1 on l1.userid=elfn.userid::int\n"
						 		+ "where extract(day from attendance_date::date)='"+i+"' and to_Char(attendance_date,'yyyy-mm')='"+year+"-"+month+"'\n"
						 		+ "and l1.userid=l.userid group by name,attendance_date,attendance ) a\n"
						 		+ "left join (select * from edu_tt_event_mstr where event_date = '"+year+"-"+month+"-"+i+"' and institute_id=?)e on a.attendance_date = e.event_date ) as _"+i+"\n, ";
					 }
				 }
					q = "select distinct l.userid,lfn.username,lfn.teacher_code, " + temp + " from edu_lms_faculty_attendance fm\n"
							+ "inner join edu_lms_faculty_nch lfn on lfn.teacher_code= fm.teacher_code\n"
							+ "inner join userroleinformation u on u.user_id=lfn.userid::int\n"
							+ "inner join roleinformation rl on rl.role_id=u.role_id\n"
							+ "inner join logininformation l on l.userid=lfn.userid::int where fm.id!=0 and rl.role=? and fm.teacher_code=? " +searchvalue;
					
			}

//				System.err.println("q======================" + q);
			
				
//				stmt.setInt(1, Integer.parseInt(role_id));
				stmt = conn.prepareStatement(q);
				for (int i=1;i<=lastDay;i++) {
					stmt.setInt(i, Integer.parseInt(instid));
				}
				if (role.contains("NCISM")) {
					stmt.setString(lastDay+1, "Faculty_NCISM");
					stmt.setString(lastDay+2, teach_code_or_ayush_id);
				} else if (role.contains("NCH")) {
					stmt.setString(lastDay+1, "Faculty_NCH");
					stmt.setString(lastDay+2, teach_code_or_ayush_id);
				}
				System.err.println("quey print------>"+stmt);
//				System.err.println("daooooooooooooooooooooooo"+role);
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int p_count=0;
				ArrayList<String> list = new ArrayList<String>();
				if(role.contains("NCH")) {
				list.add(rs.getString("name"));
				list.add(rs.getString("teacher_code"));
				} if(role.contains("NCISM")){
					list.add(rs.getString("username"));
					list.add(rs.getString("teacher_code"));
				}
				
				for(int i=1; i<=lastDay; i++) {
					
					if(rs.getString("_"+i) != "" && rs.getString("_"+i) != null) {
						
						list.add(rs.getString("_"+i));
						if (rs.getString("_"+i).equals("P")) {
							p_count++;
						}
					}else {
						list.add("-");
					}
				}
				System.err.println("list============---------------=============="+list);
				list.add(p_count+"");
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
//		System.err.println("Alist--"+alist);
		return alist;
	}
}
