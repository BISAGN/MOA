package com.AyushEdu.controller.Part_one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class College_Regulation_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

//	@RequestMapping(value = "admin/basics_information", method = RequestMethod.GET)
//	public ModelAndView basics_information(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("basics_information");
//	}
//	
//	@RequestMapping(value = "admin/college_infrastructure", method = RequestMethod.GET)
//	public ModelAndView college_infrastructure(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("college_infrastructure");
//	}
	
//	@RequestMapping(value = "admin/college_department", method = RequestMethod.GET)
//	public ModelAndView college_department(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("college_department");
//	}
//	
//	@RequestMapping(value = "admin/college_financial", method = RequestMethod.GET)
//	public ModelAndView college_financial(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("college_financial");
//	}
	
//	@RequestMapping(value = "admin/student_details", method = RequestMethod.GET)
//	public ModelAndView student_details(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("student_details");
//	}
	
//	@RequestMapping(value = "admin/college_staff_info", method = RequestMethod.GET)
//	public ModelAndView college_staff_info(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//		return new ModelAndView("college_staff_info");
//	}
	
//	@RequestMapping(value = "admin/add_staff", method = RequestMethod.GET)
//	public ModelAndView add_staff(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("add_staff");
//	}

//	@RequestMapping(value = "admin/college_staff_list", method = RequestMethod.GET)
//	public ModelAndView college_staff_list(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("college_staff_list");
//	}
	
//	@RequestMapping(value = "admin/department_equipments", method = RequestMethod.GET)
//	public ModelAndView department_equipments(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("department_equipments");
//	}
	
//	@RequestMapping(value = "admin/hospital_infrastructure", method = RequestMethod.GET)
//	public ModelAndView hospital_infrastructure(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_infrastructure");
//	}
	
//	@RequestMapping(value = "admin/hospital_ipdopd", method = RequestMethod.GET)
//	public ModelAndView hospital_ipdopd(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_ipdopd");
//	}
//	
//	@RequestMapping(value = "admin/otherhospital_detail", method = RequestMethod.GET)
//	public ModelAndView otherhospital_detail(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("otherhospital_detail");
//	}
	
//	@RequestMapping(value = "admin/hospital_staffdetails", method = RequestMethod.GET)
//	public ModelAndView hospital_staffdetails(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_staffdetails");
//	}
	
//	@RequestMapping(value = "admin/hospital_staff_list", method = RequestMethod.GET)
//	public ModelAndView hospital_staff_list(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_staff_list");
//	}

//	@RequestMapping(value = "admin/hospital_equipments", method = RequestMethod.GET)
//	public ModelAndView hospital_equipments(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_equipments");
//	}
	
//	@RequestMapping(value = "admin/declaration", method = RequestMethod.GET)
//	public ModelAndView declaration(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("declaration");
//	}
	
//	VIEW PAGES==============BELOW=========================== 
	
	@RequestMapping(value = "admin/basics_information_view", method = RequestMethod.GET)
	public ModelAndView basics_information_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("basics_information_view");
	}
	
	@RequestMapping(value = "admin/college_infrastructure_view", method = RequestMethod.GET)
	public ModelAndView college_infrastructure_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("college_infrastructure_view");
	}
	
	@RequestMapping(value = "admin/college_department_view", method = RequestMethod.GET)
	public ModelAndView college_department_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		return new ModelAndView("college_department_view");
	}
	
	@RequestMapping(value = "admin/college_financial_view", method = RequestMethod.GET)
	public ModelAndView college_financial_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		return new ModelAndView("college_financial_view");
	}
	
	@RequestMapping(value = "admin/student_details_view", method = RequestMethod.GET)
	public ModelAndView student_details_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		return new ModelAndView("student_details_view");
	}
	
	@RequestMapping(value = "admin/college_staff_info_view", method = RequestMethod.GET)
	public ModelAndView college_staff_info_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		return new ModelAndView("college_staff_info_view");
	}
	
	@RequestMapping(value = "admin/college_staff_list_view", method = RequestMethod.GET)
	public ModelAndView college_staff_list_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("college_staff_list_view");
	}
	
	@RequestMapping(value = "admin/department_equipments_view", method = RequestMethod.GET)
	public ModelAndView department_equipments_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("department_equipments_view");
	}
	
	@RequestMapping(value = "admin/hospital_infrastructure_view", method = RequestMethod.GET)
	public ModelAndView hospital_infrastructure_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("hospital_infrastructure_view");
	}
	
	@RequestMapping(value = "admin/hospital_ipdopd_view", method = RequestMethod.GET)
	public ModelAndView hospital_ipdopd_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("hospital_ipdopd_view");
	}
	
	@RequestMapping(value = "admin/otherhospital_detail_view", method = RequestMethod.GET)
	public ModelAndView otherhospital_detail_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("otherhospital_detail_view");
	}
	
//	@RequestMapping(value = "admin/hospital_staffdetails_view", method = RequestMethod.GET)
//	public ModelAndView hospital_staffdetails_view(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_staffdetails_view");
//	}
//	
//	@RequestMapping(value = "admin/hospital_staff_list_view", method = RequestMethod.GET)
//	public ModelAndView hospital_staff_list_view(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_staff_list_view");
//	}
//	
//	@RequestMapping(value = "admin/hospital_equipments_view", method = RequestMethod.GET)
//	public ModelAndView hospital_equipments_view(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		return new ModelAndView("hospital_equipments_view");
//	}

@RequestMapping(value = "admin/declaration_view", method = RequestMethod.GET)
	public ModelAndView declaration_view(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		return new ModelAndView("declaration_view");
	}

//@RequestMapping(value = "admin/college_staff_info_report_url", method = RequestMethod.GET)
//public ModelAndView college_staff_info_report_url(ModelMap Mmap, HttpSession session,
//		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
//	return new ModelAndView("college_staff_info_report_tiles");
//}

@RequestMapping(value = "admin/college_staff_list_report_url", method = RequestMethod.GET)
public ModelAndView college_staff_list_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("college_staff_list_report_tiles");
}
@RequestMapping(value = "admin/department_equipments_report_url", method = RequestMethod.GET)
public ModelAndView department_equipments_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("department_equipments_report_tiles");
}

@RequestMapping(value = "admin/hospital_ipdopd_report_url", method = RequestMethod.GET)
public ModelAndView hospital_ipdopd_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("hospital_ipdopd_report_tiles");
}
@RequestMapping(value = "admin/otherhospital_detail_report_url", method = RequestMethod.GET)
public ModelAndView otherhospital_detail_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("otherhospital_detail_report_tiles");
}

@RequestMapping(value = "admin/hospital_staffdetails_report_url", method = RequestMethod.GET)
public ModelAndView hospital_staffdetails_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("hospital_staffdetails_report_tiles");
}

@RequestMapping(value = "admin/hospital_staff_list_report_url", method = RequestMethod.GET)
public ModelAndView hospital_staff_list_report_url(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("hospital_staff_list_report_tiles");
}

@RequestMapping(value = "admin/hospital_infrastructure_report", method = RequestMethod.GET)
public ModelAndView hospital_infrastructure_report(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("hospital_infrastructure_reportTiles");
}

@RequestMapping(value = "admin/hospital_equipments_report", method = RequestMethod.GET)
public ModelAndView hospital_equipments_report(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("hospital_equipments_reportTiles");
}
@RequestMapping(value = "admin/declaration_report", method = RequestMethod.GET)
public ModelAndView declaration_report(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("declaration_reportTiles");
}
@RequestMapping(value = "admin/college_recognition_view", method = RequestMethod.GET)
public ModelAndView college_recognition_view(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
	return new ModelAndView("college_recognition_view");
}

	
}
