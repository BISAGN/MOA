package com.AyushEdu.controller.LMS_Teacher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_FACULTY_TRANSFER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Exp_Excel.Faculty_DetailsDao;
import com.AyushEdu.dao.LMS_Teacher.Faculty_transfer_intake_Dao;
import com.AyushEdu.dao.LMS_Teacher.TeacherReportDao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;

@Controller



@RequestMapping(value = { "admin", "/", "user" })
public class Teacher_Transfer_Controller {
	
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	TeacherReportDao tdao;
	
	@Autowired
	Faculty_transfer_intake_Dao ftidao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	Search_Student_RegistrationDao SSRDao;
	
	@Autowired
	Commondao cdao;
	
	@Autowired
	TeacherReportDao trdao;


	
	
	@RequestMapping(value = "/teacher_transfer_url", method = RequestMethod.GET)
	public ModelAndView Teacher_dtl_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			

			String roleid11 = session.getAttribute("roleid").toString();
			System.err.println("roleid1----------"+roleid11);
			String username = session.getAttribute("username").toString();
			System.err.println("username---------"+username);
			
			Mmap.put("msg", msg);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("getUniverCityList", cdao.getUniversityNchlist());
			Mmap.put("getinstitutelist", SSRDao.getinstitutelist(userid));
			Mmap.put("getgenderList", common.getgenderList(sessionFactory));
			Mmap.put("getSubjectList", common.getSubjectList(sessionFactory));
			Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
			Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
			
			String role = session.getAttribute("roleid").toString();
			System.err.println("--------role19jan"+role);
			if (role.equals("19")) {//uni nch
				String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("uni_id------>   "+uni_id);
				Mmap.put("uni_id",uni_id);
				}
				
				if (role.equals("21")) {//inst nch
					String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
					System.err.println("inst_id------>   "+inst_id);
					Mmap.put("inst_id",inst_id);
				}
				if (role.equals("30")) {//inst nch
					String state_id =   common.getStateIdfromUserid(sessionFactory,userid).get(0);
					System.err.println("state_id------>   "+state_id);
					Mmap.put("state_id",state_id);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("rolename").toString();
		
		

		Mmap.addAttribute("msg", msg);
		return new ModelAndView("Teacher_Transfer_Tiles");
		
		}
	
	
	
	
	@PostMapping("/getFilterfaculty_transfer_data")
	public @ResponseBody List<Map<String, Object>> getFilterfaculty_transfer_data(HttpSession sessionUserId, int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali)
			 {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return ftidao.DataTableFaculty_transfer_DataList(startPage, pageLength, Search, orderColunm, orderType, ayush_id,
				teacher_code, name, university_id,institute_id,ug_pg,subject,gender, date_of_birth, experience,state, district, village,othquali, role, userid);
		
	}

	@PostMapping("/DataTablefaculty_transfer_DataTotalCount")
	public @ResponseBody long DataTablefaculty_transfer_DataTotalCount(HttpSession sessionUserId, String Search, String ayush_id, String teacher_code,
			String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return ftidao.DataTableFaculty_transfer_DataTotalCount(Search, ayush_id, teacher_code, name,university_id,institute_id,ug_pg,subject,gender, date_of_birth, experience,state, district, village,othquali,role, userid);
	}
	
	
			@RequestMapping(value = "/faculty_transfer_inst_id" , method = RequestMethod.POST)
			public @ResponseBody List<String> faculty_transfer_inst_id(String chkval,HttpSession sessionUserId,EDU_LMS_FACULTY_TRANSFER ft) throws ParseException{	
				
		//		System.err.println("   a------------>    "+chkval);
								
				String[] id_list = chkval.split(":");
				
				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				
				System.err.println("syserr 1/2/23"+userid);
				ArrayList<String> list = new ArrayList<>();
				String list3="";
				
		
				List<String> list2 = new ArrayList<String>();
				int id1 = 0;
				for (int i = 0; i < id_list.length; i++) {
					id1 = Integer.parseInt(id_list[i]);
					System.err.println("HARSH----------------ID-------------------------"+id1);
			//		list2.add(ACNDao.Enable_to_Edit_Admission_Student_NCISM_Data(String.valueOf(id),"", session));
					String list11 = ftidao.getuseridfrommainid((id1)).get(0).get(0);
					

					list = trdao.getfromdatelogoninfo(Integer.parseInt(list11)).get(0);
					list3 = ftidao.getuseridforupdateinst((id1)).get(0).get(0);
					
				}
				
				
				
				System.err.println("syserr---2/2/22-----"+list3);
				
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);

				
				DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				System.err.println("------1/2/22----userid---"+userid);
				String username = sessionUserId.getAttribute("username").toString();
				
			
				int id = ft.getId() > 0 ? ft.getId() : 0;
				System.err.println("------1/2/23---list"+list);
				
				Date other_date_of_reg_date=null;
				
			//	if(!other_date_of_reg.equals("DD/MM/YYYY") && !other_date_of_reg.equals("")) {
					other_date_of_reg_date=datePickerFormat.parse(list.get(0));
					
					Integer p_id=null;
					
					p_id=Integer.parseInt(list.get(1));
				
				
				String institute = String.valueOf(ea.getInstitute_id());
				String university = String.valueOf(ea.getUniversity_id());
				String state = String.valueOf(ea.getState_id());
				
				
				
				
				List<String> liststr = new ArrayList<String>();
				System.err.println("in action called rejection report");
				System.err.println("in action called rejection report"+id);
				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				
				
				int app = session.createQuery(
				"update EDU_LMS_FACULTY_TRANSFER set status=:status,to_date=:to_date where p_id=:p_id")
				.setParameter("status",2)
				.setParameter("to_date", new Date())
				.setParameter("p_id", id1)
				.executeUpdate();
				
				int app1 = session.createQuery(
						"update EDU_LMS_TEACHER_DTL set institute_id=:institute_id,university_id=:university_id where id=:id")
						.setParameter("institute_id",Integer.parseInt(institute))
						.setParameter("university_id", Integer.parseInt(university))
						.setParameter("id", id1)
						.executeUpdate();
				
				int app2 = session.createQuery(
						"update EDU_LMS_FACULTY_NCH set institute_id=:institute_id,university_id=:university_id where userid=:userid")
						.setParameter("institute_id",Integer.parseInt(institute))
						.setParameter("university_id", Integer.parseInt(university))
						.setParameter("userid", (list3))
						.executeUpdate();
				
				int app3 = session.createQuery(
						"update UserLogin set institute_id=:institute_id,university_id=:university_id where userid=:userid")
						.setParameter("institute_id",Integer.parseInt(institute))
						.setParameter("university_id", Integer.parseInt(university))
						.setParameter("userid", Integer.parseInt(list3))
						.executeUpdate();
				
				
				
				ft.setInstitute_id(Integer.parseInt(institute));
				ft.setUniversity_id(Integer.parseInt(university));
				ft.setState_id(Integer.parseInt(state));
				ft.setUser_id(Integer.parseInt(userid));
				ft.setFrom_date(other_date_of_reg_date);
				ft.setTo_date(new Date());
				ft.setStatus(1);
				ft.setCreated_by(username);
				ft.setCreated_date(new Date());
				ft.setP_id(id1);
				session.save(ft);// 2
				
				
				tx.commit();
				session.close();
				
			
				return list2;
			}
	
			
//			AUTO COMPLETE ON FULL NAME
			@PostMapping("/getTeachercodeAuto")
			public @ResponseBody List<Map<String, Object>> getTeachercodeAuto(String autoString, HttpSession session) {
				String role = session.getAttribute("role").toString();
				return ftidao.TeachercodeAuto(autoString, role);
			}

	
	
//	 @RequestMapping(value = "/get_detail_from_teacher_code", method = RequestMethod.POST)
//		public @ResponseBody   ArrayList<ArrayList<String>> get_detail_from_teacher_code(String teacher_code,String val,HttpSession session) {
//		 
//		 ArrayList<ArrayList<String>> list=new ArrayList<>();
//		
//		 String userid = session.getAttribute("userId_for_jnlp").toString();
//		 
//		 ArrayList<ArrayList<String>> list1=tdao.getdatafromteacher_code(teacher_code);
//		 
//		 System.err.println("list----27/01/23-----"+list1);
//		 
//	
//
//	
//			return list;
//		}
	

			

}
