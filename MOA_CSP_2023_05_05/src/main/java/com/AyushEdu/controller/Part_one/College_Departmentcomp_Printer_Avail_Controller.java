package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_PG;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_TOURS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_UG;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class College_Departmentcomp_Printer_Avail_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Dao CRDao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@RequestMapping(value = "admin/college_department", method = RequestMethod.GET)
	public ModelAndView basics_information(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		String username = session.getAttribute("roleloginName").toString();
		
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		Mmap.put("institude", institude);
		Mmap.put("getDepartmentList", CRDao.getAllDepartment_name());
		Mmap.put("dataparent", CRDao.getdeptcomputerdetails(Integer.parseInt(institude)));
		Mmap.put("getDepartmentListofug", CRDao.getAllDepartment_list_new_ug());
		Mmap.put("getDepartmentListofpg", CRDao.getAllDepartment_list_new_pg());
		Mmap.put("getDepartmentListofap", CRDao.getAllDepartment_list_new_ap());
		Mmap.put("dataug", CRDao.getugdetails(Integer.parseInt(institude)));
		Mmap.put("dataap", CRDao.getapdetails(Integer.parseInt(institude)));
		Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("dataforinstnc", ibdao.getinstName_Code(Integer.parseInt(institude)));
//		getPg_Details
		
		return new ModelAndView("college_department");
	}
	
	 @PostMapping(value = "/college_department_Action")
	 public @ResponseBody Map<String, String> college_department_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra) throws IOException, ParseException {
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		String s_id = session.getAttribute("super_id").toString();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();
		
		try {
			
			ArrayList<ArrayList<String>> list=CRDao.getAllDepartment_name();	
			
			for(int i=1;i<=list.size();i++) {
			int h_id=Integer.parseInt(request.getParameter("inst_comp_printr_hidden"+i));
//			System.err.println("----------h_id20/04/21---"+h_id);
			String h_id2=request.getParameter("inst_comp_printr_hidden_id"+i);
//			System.err.println("----------h_id2 20/04/21---"+h_id2);
			
				String department = request.getParameter("department"+i);
				String computer = request.getParameter("computer"+i);
				String printer = request.getParameter("printer"+i);
				
				if(h_id == 0) 
				{
					CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL rd =new CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL();
					
				rd.setDepartment(h_id2);
				rd.setComputer(computer);
				rd.setPrinter(printer);
				rd.setS_id(Integer.parseInt(s_id));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInstitute_id(Integer.parseInt(institude));
				
				rd.setCreated_by(userid);
				rd.setCreated_date(date);
				
				h_id = (int) sessionHQL.save(rd);
				
				 data.put("msg", "Save as Draft Successfully");
					sessionHQL.flush();
					sessionHQL.clear();
				}
				
				else {
					CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL urd = (CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL) sessionHQL.get(CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL.class,
						(h_id));
			
				urd.setDepartment(department);
				urd.setComputer(computer);
				urd.setPrinter(printer);
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setModified_by(userid);
				urd.setModified_date(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				
				}
			tx.commit();

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	//SAVE  DETAILS------ug
	 
		@PostMapping(value = "/college_depart_ug_Action")
		public @ResponseBody String college_depart_ug_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) throws ParseException {

			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			ArrayList<ArrayList<String>> department_list_new = CRDao.getAllDepartment_list_new_ug();
			System.err.println("department_list_new-----------------"+department_list_new.size());
			
			CLG_REG_CLG_DEPT_UG pers_p =new CLG_REG_CLG_DEPT_UG();
			
//			String p_hid_ug = CRDao.getp_idfromuser_id((userid)).get(0).get(0);
			
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			
			String tours = request.getParameter("tours");
			String hid_dept_ug_details_t = request.getParameter("hid_dept_ug_details_t");
			int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			Date date = new Date();
			
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			
			
			if (tours == null || tours.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Number of educational tours conducted for teaching & practical purpose in last academic year.");
				return "Please Enter Number of educational tours conducted for teaching & practical purpose in last academic year";
			}
			
			if (validation.isOnlyNumber(tours) == false) {
				ra.addAttribute("msg","Tours" +validation.isOnlyNumberMSG);
				return "Total Tours" +validation.isOnlyNumberMSG;
			}

			try {
			
				Transaction tx1 = sessionHQL.beginTransaction();
				CLG_REG_CLG_DEPT_UG dept_ug_detail =new CLG_REG_CLG_DEPT_UG();
				
				for (int i = 0; i < department_list_new.size(); i++) {
					String department = request.getParameter("department_"+department_list_new.get(i).get(0));
					String computer_printer_avail = request.getParameter("anatomy_computer_printer_"+department_list_new.get(i).get(0));
					String dept_library_books = request.getParameter("anatomy_dept_library_"+department_list_new.get(i).get(0));
					String teacher_training_material = request.getParameter("anatomy_material_"+department_list_new.get(i).get(0));
					String museum_charts = request.getParameter("anatomy_charts_"+department_list_new.get(i).get(0));
					String museum_models = request.getParameter("anatomy_models_"+department_list_new.get(i).get(0));
					String museum_specimens = request.getParameter("anatomy_specimens_"+department_list_new.get(i).get(0));
					String theory = request.getParameter("anatomy_theory_"+department_list_new.get(i).get(0));
					String practical = request.getParameter("anatomy_practical_"+department_list_new.get(i).get(0));
					String tutorial = request.getParameter("anatomy_tutorial_"+department_list_new.get(i).get(0));
					String seminar = request.getParameter("anatomy_seminar_"+department_list_new.get(i).get(0));
					String department_id = request.getParameter("department_id_"+department_list_new.get(i).get(0));
				
					String hid_ug = request.getParameter("hid_ug_"+department_list_new.get(i).get(0));
					System.err.println("hid_ug============"+hid_ug);
					System.err.println("seminar==="+seminar);
					
					if (department == null || department.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Department Name");
						return "Please Enter Department Name";
					}
					if (computer_printer_avail == null || computer_printer_avail.trim().equals("")) {
						ra.addAttribute("msg", "Please Select Computer And Printer Availability");
						return "Please Select Computer And Printer Availability";
					}
					
					if (dept_library_books == null || dept_library_books.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Department Library Book");
						return "Please Enter Total Department Library Book";
					}

					if (validation.isOnlyNumber(dept_library_books) == false) {
						ra.addAttribute("msg", "Department Library Books" +validation.isOnlyNumberMSG);
						return "Department Library Books" +validation.isOnlyNumberMSG;
					}
					
					if (teacher_training_material == null || teacher_training_material.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Teacher Training Material");
						return "Please Enter Total Teacher Training Material";
					}
					
					if (validation.isOnlyNumer(teacher_training_material) == true) {
						ra.addAttribute("msg", "Teacher Training Material" + validation.isOnlyNumerMSG);
						return "Must Be Contain Only Digit in Teacher Training Material";
					}
					
					if (museum_charts == null || museum_charts.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Museum Charts");
						return "Please Enter Total Museum Charts";
					}
					
					if (validation.isOnlyNumber(museum_charts) == false) {
						ra.addAttribute("msg", "Museum Charts" +validation.isOnlyNumberMSG);
						return "Museum Charts" +validation.isOnlyNumberMSG;
					}
					
					if (museum_models == null || museum_models.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Museum Models");
						return "Please Enter Total Museum Models ";
					}
					
					if (validation.isOnlyNumber(museum_models) == false) {
						ra.addAttribute("msg", "Museum Models" +validation.isOnlyNumberMSG);
						return "Museum Models" +validation.isOnlyNumberMSG;
					}
					
					if (museum_specimens == null || museum_specimens.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Museum Specimens");
						return "Please Enter Total Museum Specimens";
					}
					
					
					if (validation.isOnlyNumber(museum_specimens) == false) {
						ra.addAttribute("msg","Museum Specimens" +validation.isOnlyNumberMSG);
						return "Museum Specimens" +validation.isOnlyNumberMSG;
					}
					
					if (theory == null || theory.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Theory");
						return "Please Enter Total Theory";
					}

					if (validation.isOnlyNumber(theory) == false) {
						ra.addAttribute("msg","Theory" +validation.isOnlyNumberMSG);
						return "Theory" +validation.isOnlyNumberMSG;
					}
					
					if (practical == null || practical.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Practical or Clinical");
						return "Please Enter Total Practical or Clinical";
					}
					if (validation.isOnlyNumber(practical) == false) {
						ra.addAttribute("msg","Practical" +validation.isOnlyNumberMSG);
						return "Practical" +validation.isOnlyNumberMSG;
					}
					
					if (tutorial == null || tutorial.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Tutorials Of UG");
						return "Please Enter Total Tutorials Of UG";
					}
					if (validation.isOnlyNumber(tutorial) == false) {
						ra.addAttribute("msg","Tutorial" +validation.isOnlyNumberMSG);
						return "Tutorial" +validation.isOnlyNumberMSG;
					}
					
					if (seminar == null || seminar.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total Seminars ");
						return "Please Enter Total Seminars";
					}
					
					if (validation.isOnlyNumber(seminar) == false) {
						ra.addAttribute("msg","Seminar" +validation.isOnlyNumberMSG);
						return "Seminar" +validation.isOnlyNumberMSG;
					}
					
					dept_ug_detail.setDepartment(department);
					dept_ug_detail.setComputer_printer_avail(computer_printer_avail);
					dept_ug_detail.setDept_library_books(dept_library_books);
					dept_ug_detail.setTeacher_training_material(teacher_training_material);
					dept_ug_detail.setMuseum_charts(museum_charts);
					dept_ug_detail.setMuseum_models(museum_models);
					dept_ug_detail.setMuseum_specimens(museum_specimens);
					dept_ug_detail.setTheory(theory);
					dept_ug_detail.setPractical(practical);
					dept_ug_detail.setTutorial(tutorial);
					dept_ug_detail.setSeminar(seminar);
					dept_ug_detail.setDepartment_id(Integer.parseInt(department_id));
					dept_ug_detail.setInstitute_id(Integer.parseInt(institude));
					dept_ug_detail.setCreated_by(userid);
					dept_ug_detail.setCreated_date(date);
					
					
					if (Integer.parseInt(hid_ug) == 0) {
						int hid_department_area1= (Integer) sessionHQL.save(dept_ug_detail);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					else {
						CLG_REG_CLG_DEPT_UG dept_ug_detail_u = (CLG_REG_CLG_DEPT_UG) sessionHQL
								.get(CLG_REG_CLG_DEPT_UG.class, Integer.parseInt(hid_ug));
						
						dept_ug_detail_u.setDepartment(department);
						dept_ug_detail_u.setComputer_printer_avail(computer_printer_avail);
						dept_ug_detail_u.setDept_library_books(dept_library_books);
						dept_ug_detail_u.setTeacher_training_material(teacher_training_material);
						dept_ug_detail_u.setMuseum_charts(museum_charts);
						dept_ug_detail_u.setMuseum_models(museum_models);
						dept_ug_detail_u.setMuseum_specimens(museum_specimens);
						dept_ug_detail_u.setTheory(theory);
						dept_ug_detail_u.setPractical(practical);
						dept_ug_detail_u.setTutorial(tutorial);
						dept_ug_detail_u.setTutorial(seminar);
						dept_ug_detail_u.setDepartment_id(Integer.parseInt(department_id));
						dept_ug_detail_u.setInstitute_id(Integer.parseInt(institude));
						dept_ug_detail_u.setModified_by(userid);
						dept_ug_detail_u.setModified_date(date);
						sessionHQL.update(dept_ug_detail_u);
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				
				tx1.commit();
				
		
				Transaction tx = sessionHQL.beginTransaction();
				CLG_REG_CLG_DEPT_TOURS dept_ug_details_t =new CLG_REG_CLG_DEPT_TOURS();
				
				dept_ug_details_t.setEdu_tours_teach_pract_purpose_ug(tours);
				dept_ug_details_t.setInstitute_id(Integer.parseInt(institude));
				dept_ug_details_t.setCreated_by((userid));
				dept_ug_details_t.setCreated_date(date);
				
					if (Integer.parseInt(hid_dept_ug_details_t) == 0) {
						int hid_dept_ug_details_t1= (Integer) sessionHQL.save(dept_ug_details_t);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_dept_ug_details_t1) ;
				}
					else {

						CLG_REG_CLG_DEPT_TOURS dept_ug_details_t_u = (CLG_REG_CLG_DEPT_TOURS) sessionHQL
								.get(CLG_REG_CLG_DEPT_TOURS.class, Integer.parseInt(hid_dept_ug_details_t));
						
						dept_ug_details_t_u.setEdu_tours_teach_pract_purpose_ug(tours);
						dept_ug_details_t_u.setInstitute_id(Integer.parseInt(institude));
						dept_ug_details_t_u.setModified_by((userid));
						dept_ug_details_t_u.setModified_date(date);
						sessionHQL.update(dept_ug_details_t_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
					}
					
			} catch (RuntimeException e) {
				try {

					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return  "Data Saved Successfully";
		}

		
		//FETCH UG DETAILS=====
		
		@RequestMapping(value = "admin/getUg_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_CLG_DEPT_UG> getUg_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
//			String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_CLG_DEPT_UG where institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institude));
			@SuppressWarnings("unchecked")
			List<CLG_REG_CLG_DEPT_UG> clist = (List<CLG_REG_CLG_DEPT_UG>) q.list();
		
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
	
		
		@RequestMapping(value = "admin/getcomp_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL> getcomp_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
//			String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL where institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institude));
			@SuppressWarnings("unchecked")
			List<CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL> clist = (List<CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL>) q.list();
		
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		//SAVE COLLEGE DEPARTMENT DETAILS====PG=====
		 
			@PostMapping(value = "/college_depart_pg_Action")
			public @ResponseBody String college_depart_pg_Action( 
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					@RequestParam(value = "edu_tour", required = false) MultipartFile edu_tour_doc,MultipartHttpServletRequest mul,
					RedirectAttributes ra) throws ParseException, IOException {

				String role = session.getAttribute("role").toString();
				String roleid1 = session.getAttribute("roleid").toString();
				
				DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				
				ArrayList<ArrayList<String>> department_list_new_pg = CRDao.getAllDepartment_list_new_pg();
				System.err.println("department_list_new-----------------"+department_list_new_pg.size());
				
				CLG_REG_CLG_DEPT_PG pers_p =new CLG_REG_CLG_DEPT_PG();
				
//				String p_hid_ug = CRDao.getp_idfromuser_id((userid)).get(0).get(0);
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				String tours = request.getParameter("tours_pg");
				String edu_tour = "edu_tour";
				
				String hid_dept_pg_details_t = request.getParameter("hid_dept_pg_details_t");
				
				if (tours == null || tours.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Educational tours conducted for teaching & practical purpose in last academic year");
					return "Please Enter Educational tours conducted for teaching & practical purpose in last academic year";
				}
				
				if((edu_tour.equals("0")  || edu_tour == ""))
				{
					ra.addAttribute("msg", "Please Upload The Education File");
					return "Please Upload The Education File";
				}
				
				int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
				Date date = new Date();
				String username = principal.getName();
				Session sessionHQL = this.sessionFactory.openSession();
				
				try {
				
					Transaction tx1 = sessionHQL.beginTransaction();
					CLG_REG_CLG_DEPT_PG dept_pg_detail =new CLG_REG_CLG_DEPT_PG();
					
					for (int i = 0; i < department_list_new_pg.size(); i++) {
						String subject = request.getParameter("department_"+department_list_new_pg.get(i).get(0));
						String theory = request.getParameter("philosophy_theory_"+department_list_new_pg.get(i).get(0));
						String practical = request.getParameter("philosophy_practical_"+department_list_new_pg.get(i).get(0));
						String seminar = request.getParameter("philosophy_seminars_"+department_list_new_pg.get(i).get(0));
						String case_presentation = request.getParameter("philosophy_presentation_"+department_list_new_pg.get(i).get(0));
						String journal_meeting = request.getParameter("philosophy_meeting_"+department_list_new_pg.get(i).get(0));
						String department_id = request.getParameter("department_id_"+department_list_new_pg.get(i).get(0));
						String hid_pg = request.getParameter("hid_pg_"+department_list_new_pg.get(i).get(0));
						
						System.err.println("hid_pg====20/04/23========"+hid_pg);
						System.err.println("hid_pg_"+department_list_new_pg.get(i).get(0));
						System.err.println("seminar==="+seminar);
						
						if (subject == null || subject.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Subject Name");
							return "Please Enter Subject Name";
						}
						
						if (theory == null || theory.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Theory");
							return "Please Enter Total Theory";
						}
						if (practical == null || practical.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Practical");
							return "Please Enter Total Practical";
						}
						
						if (seminar == null || seminar.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Seminar");
							return "Please Enter Total Seminar";
						}
						
						if (case_presentation == null || case_presentation.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Case Presentation");
							return "Please Enter Total Theory";
						}
						if (journal_meeting == null || journal_meeting.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Total Journal Meeting");
							return "Please Enter Total Journal Meeting";
						}
						
						
						dept_pg_detail.setSubject(subject);
						dept_pg_detail.setTheory(theory);
						dept_pg_detail.setPractical(practical);
						dept_pg_detail.setSeminar(seminar);
						dept_pg_detail.setCase_presentation(case_presentation);
						dept_pg_detail.setJournal_meeting(journal_meeting);
						dept_pg_detail.setDepartment_id(Integer.parseInt(department_id));
						System.err.println("seminar==="+seminar);
						
						dept_pg_detail.setInstitute_id(Integer.parseInt(institude));
					
						dept_pg_detail.setCreated_by(userid);
						dept_pg_detail.setCreated_date(date);
						
						if (Integer.parseInt(hid_pg) == 0) {
//							dept_ug_detail.setP_id(Integer.parseInt(p_hid_ug));
							int hid_department_area1= (Integer) sessionHQL.save(dept_pg_detail);
							sessionHQL.flush();
							sessionHQL.clear();
						}
						else {
							CLG_REG_CLG_DEPT_PG dept_pg_detail_u = (CLG_REG_CLG_DEPT_PG) sessionHQL
									.get(CLG_REG_CLG_DEPT_PG.class, Integer.parseInt(hid_pg));
							
							dept_pg_detail_u.setSubject(subject);
							dept_pg_detail_u.setTheory(theory);
							dept_pg_detail_u.setPractical(practical);
							dept_pg_detail_u.setSeminar(seminar);
							dept_pg_detail_u.setCase_presentation(case_presentation);
							dept_pg_detail_u.setJournal_meeting(journal_meeting);
							dept_pg_detail_u.setModified_by(userid);
							dept_pg_detail_u.setModified_date(date);
							sessionHQL.update(dept_pg_detail_u);
							sessionHQL.flush();
							sessionHQL.clear();
							
						}
					}
					tx1.commit();
				
					Transaction tx = sessionHQL.beginTransaction();
					CLG_REG_CLG_DEPT_TOURS dept_pg_details_t =new CLG_REG_CLG_DEPT_TOURS();
					

					if(!edu_tour_doc.isEmpty()) {
						edu_tour = upload_imagemethod(request, edu_tour_doc, session, edu_tour);
											}
											else {
												edu_tour = request.getParameter("hid_edu_tour");
											}
					
					dept_pg_details_t.setEdu_tours_teaching_pract_purpose_pg(tours);
					dept_pg_details_t.setInstitute_id(Integer.parseInt(institude));
//					dept_pg_details_t.setEdu_tours_pg(edu_tour);
					if(!edu_tour.equals("")) {
						dept_pg_details_t.setEdu_tours_pg(edu_tour);
						}
					
					dept_pg_details_t.setCreated_by((userid));
					dept_pg_details_t.setCreated_date(date);
					
						if (Integer.parseInt(hid_dept_pg_details_t) == 0) {
							dept_pg_details_t.setInstitute_id(id);
							int hid_dept_ug_details_t1= (Integer) sessionHQL.save(dept_pg_details_t);
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							return String.valueOf(hid_dept_ug_details_t1) ;
					}
						else {

							CLG_REG_CLG_DEPT_TOURS dept_pg_details_t_u = (CLG_REG_CLG_DEPT_TOURS) sessionHQL
									.get(CLG_REG_CLG_DEPT_TOURS.class, Integer.parseInt(hid_dept_pg_details_t));
							
							dept_pg_details_t_u.setEdu_tours_teaching_pract_purpose_pg(tours);
//							dept_pg_details_t_u.setEdu_tours_pg(edu_tour);
							if(!edu_tour.equals("")) {
								dept_pg_details_t_u.setEdu_tours_pg(edu_tour);
								}
							dept_pg_details_t_u.setModified_by((userid));
							dept_pg_details_t_u.setModified_date(date);
							sessionHQL.update(dept_pg_details_t_u);
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
						}
					
						
				} catch (RuntimeException e) {
					try {

						ra.addAttribute("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				return  "Data Saved Successfully";
			}
			
			
			//FETCH PG DETAILS=====
			
			@RequestMapping(value = "admin/getPg_Details", method = RequestMethod.POST)
			public @ResponseBody List<CLG_REG_CLG_DEPT_PG> getPg_Details(HttpSession session) {
				String userid = session.getAttribute("userId_for_jnlp").toString();
//				String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("from CLG_REG_CLG_DEPT_PG where institute_id=:institute_id ");
				q.setParameter("institute_id", Integer.parseInt(institude));
				@SuppressWarnings("unchecked")
				List<CLG_REG_CLG_DEPT_PG> clist = (List<CLG_REG_CLG_DEPT_PG>) q.list();
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			// TOURS Details===
			
			@RequestMapping(value = "admin/getTours_Details", method = RequestMethod.POST)
			public @ResponseBody List<CLG_REG_CLG_DEPT_TOURS> getTours_Details(HttpSession session) {
				String userid = session.getAttribute("userId_for_jnlp").toString();
//				String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL 
						.createQuery("from CLG_REG_CLG_DEPT_TOURS where institute_id=:institute_id ");
				
				q.setParameter("institute_id", Integer.parseInt(institude));
				@SuppressWarnings("unchecked")
				List<CLG_REG_CLG_DEPT_TOURS> clist = (List<CLG_REG_CLG_DEPT_TOURS>) q.list();
			
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			//SAVE college department  DETAILS =====Academic Performance  =====
			 
			@PostMapping(value = "/college_depart_aca_per_Action")
			public @ResponseBody String college_depart_aca_per_Action( 
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) throws ParseException {

				String role = session.getAttribute("role").toString();
				String roleid1 = session.getAttribute("roleid").toString();
				
				DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				
				ArrayList<ArrayList<String>> department_list_new_ap = CRDao.getAllDepartment_list_new_ap();
				
				CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE pers_p =new CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE();
				
//				String p_hid_ug = CRDao.getp_idfromuser_id((userid)).get(0).get(0);
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
//				String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
				String msg = "";
				int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
				Date date = new Date();
				String username = principal.getName();
				Session sessionHQL = this.sessionFactory.openSession();
				
				try {
				
					Transaction tx1 = sessionHQL.beginTransaction();
					CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE dept_ap_detail =new CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE();
					
					for (int i = 0; i < department_list_new_ap.size(); i++) {
						String department = request.getParameter("department_"+department_list_new_ap.get(i).get(0));
						String advance_teaching_plan = request.getParameter("anatomy_teaching_plan_"+department_list_new_ap.get(i).get(0));
						String teaching_diary = request.getParameter("anatomy__diary_"+department_list_new_ap.get(i).get(0));
						String journal_practical = request.getParameter("anatomy_journal_"+department_list_new_ap.get(i).get(0));
						String department_id = request.getParameter("department_id_"+department_list_new_ap.get(i).get(0));
						String hid_ap = request.getParameter("hid_ap_"+department_list_new_ap.get(i).get(0));
//						System.err.println("hid_ap===20/04/23========="+hid_ap);
//						System.err.println("hid_ap_"+department_list_new_ap.get(i).get(0));
						
						if (advance_teaching_plan == null || advance_teaching_plan.trim().equals("")) {
							ra.addAttribute("msg", "Please Select Availability Of Advance Teaching Plan");
							return "Please Enter Total Advance Teaching Plan";
						}
						
						if (teaching_diary == null || teaching_diary.trim().equals("")) {
							ra.addAttribute("msg", "Please Select Availability Of Teaching Diary");
							return "Please Select Availability Of Teaching Diary";
						}
						
						if (journal_practical == null || journal_practical.trim().equals("")) {
							ra.addAttribute("msg", "Please Availability Of  Journal Practical");
							return "Please Select Availability Of Journal Practical";
						}
						
						dept_ap_detail.setDepartment(department);
						dept_ap_detail.setAdvance_teaching_plan(advance_teaching_plan);
						dept_ap_detail.setTeaching_diary(teaching_diary);
						dept_ap_detail.setJournal_practical(journal_practical);
						dept_ap_detail.setDepartment_id(Integer.parseInt(department_id));
						
						dept_ap_detail.setInstitute_id(Integer.parseInt(institude));
					
						dept_ap_detail.setCreated_by(userid);
						dept_ap_detail.setCreated_date(date);
						
						if (hid_ap.equals("0")) {
//							dept_ug_detail.setP_id(Integer.parseInt(p_hid_ug));
							
//							int hid_department_area2= (Integer) sessionHQL.save(dept_ap_detail);
							sessionHQL.save(dept_ap_detail);
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Data Saved Successfully";
						}
						else {
							CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE dept_ap_detail_u = (CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE) sessionHQL
									.get(CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE.class, Integer.parseInt(hid_ap));
							
							dept_ap_detail_u.setDepartment(department);
							dept_ap_detail_u.setAdvance_teaching_plan(advance_teaching_plan);
							dept_ap_detail_u.setTeaching_diary(teaching_diary);
							dept_ap_detail_u.setJournal_practical(journal_practical);
						
							dept_ap_detail_u.setModified_by(userid);
							dept_ap_detail_u.setModified_date(date);
							sessionHQL.update(dept_ap_detail_u);
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Data Updated Successfully";
						}
					}
					tx1.commit();
					
						
				} catch (RuntimeException e) {
					try {

						ra.addAttribute("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				return  msg;
			}
			
			
			//FETCH AP DETAILS=====
			
			@RequestMapping(value = "admin/getAp_Details", method = RequestMethod.POST)
			public @ResponseBody List<CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE> getAp_Details(HttpSession session) {
				String userid = session.getAttribute("userId_for_jnlp").toString();
//				String p_hid_ug = CRDao.getp_idfromuser_id(userid).get(0).get(0);
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL 
						.createQuery("from CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE where institute_id=:institute_id ");
				
				q.setParameter("institute_id", Integer.parseInt(institude));
				@SuppressWarnings("unchecked")
				List<CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE> clist = (List<CLG_REG_CLG_DEPT_ACADEMIC_PERFORMANCE>) q.list();
			
				tx.commit();
				sessionHQL.close();
				return clist;
			}
			
			
			public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
				
				String extension=""; //add line
				String fname = ""; //add line
				
				request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
				
				MultipartFile file = mul;
				
				if (!file.getOriginalFilename().isEmpty()) {
					
					byte[] bytes = file.getBytes();
					String  mnhFilePath = session.getAttribute(id).toString();
					
			        File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();
							
					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j+1);
					}
					java.util.Date date1= new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
					
					File serverFile = new File(fname);	               
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();

				}else {

				}
				return fname;
				
				}
}
