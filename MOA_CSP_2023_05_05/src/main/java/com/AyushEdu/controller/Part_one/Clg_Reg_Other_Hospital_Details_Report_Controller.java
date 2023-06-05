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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hospital_Staff_List_Report_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Other_Hospital_Details_Report_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Staff_Details_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Other_Hospital_Details_Report_Controller {
	
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
	private Clg_Reg_Other_Hospital_Details_Report_DAO HOHDao;
	
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	// ----------------search-------------------------
		
		
//==========================================VIEW===================================================================//
		
		@RequestMapping(value = "/otherhospital_detail_view", method = RequestMethod.POST)
		public ModelAndView otherhospital_detail_view(@RequestParam(value = "other_hos_dtl_id", required = false) String id, ModelMap Mmap,
		HttpSession session, @RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {

				Session sessionHQL = this.sessionFactory.openSession();
				Mmap.put("main_view_id", id);
				System.err.println("iddddddddddddddddddddddddddd"+id);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				String role = session.getAttribute("role").toString();
				
				List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
						Integer.parseInt(institute_id), role, userid,"clg_reg_other_hos_dtl_maintenance_records","institute_id");
				
				if(getPidfromInstidReport.isEmpty()) {
					Mmap.put("msg", "Please Save Other Hospital Details First");
					return new ModelAndView("redirect:otherhospital_detail");
				}
				
						System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
						String p_id = getPidfromInstidReport.get(0).get("id").toString();
				Mmap.put("getPidfromInstidReport", p_id); 

							System.err.println("22/2/23----------------id------>" + id);

								System.err.println("institute_id----------------id------>" + institute_id);

								CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS viewid = (CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS) sessionHQL
								.get(CLG_REG_OTHER_HOS_DTL_MAINTENANCE_RECORDS.class, Integer.parseInt(p_id));
								
								CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM viewid2 = (CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM) sessionHQL
										.get(CLG_REG_OTHER_HOS_DTL_LABOUR_ROOM.class, Integer.parseInt(p_id));
								
								CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY viewid3 = (CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY) sessionHQL
										.get(CLG_REG_OTHER_HOS_DTL_FUNCTIONALITY.class, Integer.parseInt(p_id));
		//
		//
						Mmap.put("View_Other_hosp_maintenanceCMD", viewid);
						Mmap.put("View_Hospital_labour_roomCMD", viewid2);
						Mmap.put("View_Hospital_functionalCMD", viewid3);

							List<Map<String, Object>> View_Other_hosp_maintenance_Staff_Details1 = HOHDao.View_Other_hosp_maintenance_Staff_Details(Integer.parseInt(id),
										Integer.parseInt(institute_id), role,userid);
							List<Map<String, Object>> View_Hospital_labour_room_Details2 = HOHDao.View_Hospital_labour_room_Details(Integer.parseInt(id),
									Integer.parseInt(institute_id), role,userid);
							List<Map<String, Object>> View_Hospital_investigation_Details3 = HOHDao.View_Hospital_investigation_Details(Integer.parseInt(id),
									Integer.parseInt(institute_id), role,userid);
							List<Map<String, Object>> View_Hospital_clinical_laboratory4 = HOHDao.View_Hospital_clinical_laboratory(Integer.parseInt(id),
									Integer.parseInt(institute_id), role,userid);
							List<Map<String, Object>> View_Hospital_dtl_functionality5 = HOHDao.View_Hospital_dtl_functionality(Integer.parseInt(id),
									Integer.parseInt(institute_id), role,userid);
							List<Map<String, Object>> View_Hospital_Operation_theatre_staff_Details6 = HOHDao.View_Hospital_Operation_theatre_staff_Details(Integer.parseInt(id),
									Integer.parseInt(institute_id), role,userid);

							
//							System.err.println("View_Hospital_Staff_Details2--------------"+View_Hospital_Staff_Details2);
							Mmap.put("View_Other_hosp_maintenance_Staff_Details1", View_Other_hosp_maintenance_Staff_Details1);
							Mmap.put("View_Hospital_labour_room_Details2", View_Hospital_labour_room_Details2);
							Mmap.put("View_Hospital_investigation_Details3", View_Hospital_investigation_Details3);
							Mmap.put("View_Hospital_clinical_laboratory4", View_Hospital_clinical_laboratory4);
							Mmap.put("View_Hospital_dtl_functionality5", View_Hospital_dtl_functionality5);
							Mmap.put("View_Hospital_Operation_theatre_staff_Details6", View_Hospital_Operation_theatre_staff_Details6);

							Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
							Mmap.put("inst_id", id);

		Mmap.addAttribute("msg", msg);
		return new ModelAndView("otherhospital_detail_view");
		}
			

}
