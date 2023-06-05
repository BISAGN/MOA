package com.AyushEdu.controller.Part_one;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_hospital_Infra.CLG_REG_HOSP_ADMINISTRATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hospital_Infrastructure_Report_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hospital_Staff_List_Report_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Opd_Ipd_Report_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Staff_Details_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Hospital_Infrastructure_Report_Controller {
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private Clg_Reg_Hospital_Infrastructure_Report_DAO HOIRDao;
	
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
			
//==========================================VIEW===================================================================//
		
		@RequestMapping(value = "/hospital_infrastructure_view", method = RequestMethod.POST)
		public ModelAndView hospital_infrastructure_view(@RequestParam(value = "hos_infra_id", required = false) String id, ModelMap Mmap,
		HttpSession session, @RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {

				Session sessionHQL = this.sessionFactory.openSession();
				Mmap.put("main_view_id", id);
				System.err.println("iddddddddddddddddddddddddddd"+id);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				String role = session.getAttribute("role").toString();
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				
				List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
						Integer.parseInt(institute_id), role, userid,"clg_reg_hosp_infra","institute_id");
				if(getPidfromInstidReport.isEmpty()) {
					Mmap.put("msg", "Please Save Hospital Infrastructure Details First");
					return new ModelAndView("redirect:hospital_infrastructure");
				}
						System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
						String p_id = getPidfromInstidReport.get(0).get("id").toString();
				        Mmap.put("getPidfromInstidReport", p_id); 

							System.err.println("22/2/23----------------id------>" + id);

								System.err.println("institute_id----------------id------>" + institute_id);

//								CLG_REG_HOSP_ADMINISTRATION viewid = (CLG_REG_HOSP_ADMINISTRATION) sessionHQL
//								.get(CLG_REG_HOSP_ADMINISTRATION.class, Integer.parseInt(p_id));
		
		
//						Mmap.put("View_Infast_redioCMD", viewid);

							List<Map<String, Object>> View_Infast_rede1 = HOIRDao.View_Infast_rede(Integer.parseInt(id),
										Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Administrator1 = HOIRDao.View_Hospital_Administrator(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_OPD2 = HOIRDao.View_Hospital_OPD(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_IPD3 = HOIRDao.View_Hospital_IPD(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_OT4 = HOIRDao.View_Hospital_OT(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Reha_unit5 = HOIRDao.View_Hospital_Reha_unit(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_CL6 = HOIRDao.View_Hospital_CL(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Radiology_Sonography7 = HOIRDao.View_Hospital_Radiology_Sonography(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Kitchen_Canteen8 = HOIRDao.View_Hospital_Kitchen_Canteen(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Hosp_Stores9 = HOIRDao.View_Hospital_Hosp_Stores(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Hospital_Infrastructure_Details10 = HOIRDao.View_Hospital_Infrastructure_Details(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);

							List<Map<String, Object>> View_Hospital_Infra_Details11 = HOIRDao.View_Hospital_Infra_Details(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
//							System.err.println("View_Hospital_Staff_Details2--------------"+View_Hospital_Staff_Details2);
							
							
							Mmap.put("View_Infast_rede1", View_Infast_rede1);
							Mmap.put("View_Hospital_Administrator1", View_Hospital_Administrator1);
							Mmap.put("View_Hospital_OPD2", View_Hospital_OPD2);
							Mmap.put("View_Hospital_IPD3", View_Hospital_IPD3);
							Mmap.put("View_Hospital_OT4", View_Hospital_OT4);
							Mmap.put("View_Hospital_Reha_unit5", View_Hospital_Reha_unit5);
							Mmap.put("View_Hospital_CL6", View_Hospital_CL6);
							Mmap.put("View_Hospital_Radiology_Sonography7", View_Hospital_Radiology_Sonography7);
							Mmap.put("View_Hospital_Kitchen_Canteen8", View_Hospital_Kitchen_Canteen8);
							Mmap.put("View_Hospital_Hosp_Stores9", View_Hospital_Hosp_Stores9);
							Mmap.put("View_Hospital_Infrastructure_Details10", View_Hospital_Infrastructure_Details10);
							Mmap.put("View_Hospital_Infra_Details11", View_Hospital_Infra_Details11);


							Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
							Mmap.put("inst_id", id);
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("hospital_infrastructure_view");
		}
			

}
