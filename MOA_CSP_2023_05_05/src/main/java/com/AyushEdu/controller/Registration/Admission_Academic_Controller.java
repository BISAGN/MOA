package com.AyushEdu.controller.Registration;

import java.security.Principal;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Registration.EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Admission_Academic_ScheduleDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Admission_Academic_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Admission_Academic_ScheduleDao adao;
	
	@Autowired
	CommonController common;
	
	
	@RequestMapping(value = "/Admission_Academic_schedule_Url", method = RequestMethod.GET)
	public ModelAndView Admission_Academic_schedule_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		if(request.getHeader("Referer") == null ) { 
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Admission_Academic_schedule_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			String role = session.getAttribute("roleStaff_lvl").toString();
//			Mmap.put("data", adao.getSystemID((role)));
			Mmap.put("data", common.getsysList(sessionFactory));
			
		
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
			
			return new ModelAndView("Admission_Academic_Schedule_Tiles");
	}
	
	@PostMapping(value = "/AcademicScheduleAction")
	public ModelAndView AcademicScheduleAction(@Validated @ModelAttribute("AcademicScheduleCMD") EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		
		
		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Admission_Academic_schedule_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String academic_year = request.getParameter("academic_year");
		String system = request.getParameter("system_id");
		String start_date = request.getParameter("start_date");

		String end_date = request.getParameter("end_date");
		String status = request.getParameter("status");
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		DateFormat yrformt = new SimpleDateFormat("yyyy", Locale.ENGLISH);
		
//		System.err.println("\n\nstart_date---"+format.parse(start_date)+"\nend_date---"+format.parse(end_date));
//		System.err.println("\n\nTDstart_date---"+td.getStart_date()+"\nTDend_date---"+td.getEnd_date());
		

		if (academic_year == null || academic_year.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Academic year");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
		
		if (system == null || system.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter System Name");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
		
		if (start_date == null || start_date.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Start Date");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
		
		if (end_date == null || end_date.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter End Date");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
		
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			String ay = td.getAcademic_year().toUpperCase();
			
			   String yr =   ay.substring(0,4);
			
			   System.err.println("yr---->      "+yr    +"  td.getSystem_id()=====>>>  "+td.getSystem_id());
			   
			Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR where to_char(to_date(academic_year,'YYYY-MM'),'YYYY')=:academic_year and system_id =:system_id and status=:status")
					.setParameter("academic_year", yr)
					.setParameter("system_id", td.getSystem_id())
					.setParameter("status", "1")
					.uniqueResult();
		
			System.err.println("c----------->   "+c);
			
			td.setAcademic_year(academic_year);
			td.setSystem_id(system);
			td.setStart_date(format.parse(start_date));
			td.setEnd_date(format.parse(end_date));
			
				if (id == 0) {
				
					td.setCreated_by(userid);
					td.setCreated_dt(date);

					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
 					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setModified_by(userid);
					td.setModified_dt(date);
					td.setId(id);
					
						String msg = adao.updateAcademic_year(td);
						if (msg == "Data Updated Successfully") {
							model.put("msg", msg);
							model.put("Nmsg", "0");
						} else {
							model.put("msg", msg);
							model.put("Nmsg", "1");
						}
						ra.addAttribute("msg", "Data Updated Successfully.");
				}
				tx.commit();

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
			
			return new ModelAndView("redirect:Admission_Academic_schedule_Url");
		}
	
	
	@PostMapping("/getFilteracademic_schedule_data")
	public @ResponseBody List<Map<String,Object>> DataTableacademicschedulesheetdata(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String academic_year,String system_name,String start_date,String end_date,String status,String userid) {
//		System.err.println("check 1-->"+academic_year+"check 2-->"+start_date);
		
		return adao.DataTableacademicscheduleDataList(startPage, pageLength, Search, orderColunm, orderType,academic_year,system_name, start_date,end_date,status, userid);
	}

	@PostMapping("/getTotalacademicschedule_dataCount")

	public @ResponseBody long getTotalcollageMstr_dataCount(HttpSession sessionUserId, String Search, String academic_year,String system_name,String start_date,String end_date,String status,String userid) {
	return adao.DataTableacademicscheduleDataTotalCount(Search, academic_year,system_name, start_date,end_date, status, userid);

	}
	
	
	@RequestMapping(value = "/Edit_Academic_schedule_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Academic_schedule_Url(@ModelAttribute("id6") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "academic_year", required = false) String academic_year,
			@RequestParam(value = "system_name", required = false) String system_name,
			@RequestParam(value = "start_date", required = false) String start_date,
			@RequestParam(value = "end_date", required = false) String end_date,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		if(request.getHeader("Referer") == null ) { 
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Admission_Academic_schedule_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
//		System.err.println("inactioncall  update");
		
//		System.err.println("academic_year=================="+academic_year);
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Mmap.addAttribute("msg", msg);
		Mmap.put("academic_year", academic_year);
		Mmap.put("system_name", system_name);
		Mmap.put("start_date", start_date);
		Mmap.put("end_date", end_date);
		Mmap.put("status", status);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Admission_Academic_Schedule_Tiles");
	}
	
	@PostMapping(value = "/delete_admission_date_Url")
	public ModelAndView delete_admission_date_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
		
		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Admission_Academic_schedule_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		
		try {
  
			int app = session.createQuery(
					"update EDU_REG_ADMISSION_ACADEMIC_SCHEDULE_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status", "2").executeUpdate();

			
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Admission_Academic_schedule_Url");
	}
	

}
