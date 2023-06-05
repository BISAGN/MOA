package com.AyushEdu.controller.Part_one.Masters;

import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_HOSP_INFRA_DEPT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.Hospital_Infra_Dept_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Hospital_Infra_Dept_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	//@Autowired
	//Hospital_Infra_Dept_Mstr_DAO_CD_DAO SM_dirdao;
	
	@Autowired
	 ValidationController validation;

	@Autowired
	Hospital_Infra_Dept_Mstr_DAO sdao;
	

	//==========================================OPEN PAGE SYSTEM========================================== 
	
	@RequestMapping(value = "/get_Hospital_Infra_Dept_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView get_Hospital_Infra_Dept_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		try {
//			
//		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("get_Hospital_Infra_Dept_Mstr_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
//		Mmap.put("msg", msg);
//		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		return new ModelAndView("Hospital_Infra_Dept_Mstr_Tiles");

	}

	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/Hospital_Infra_Dept_Action")
		public ModelAndView Hospital_Infra_Dept_Action(@Validated @ModelAttribute("Hospital_Infra_Dept_CMD") CLG_REG_HOSP_INFRA_DEPT_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("get_Hospital_Infra_Dept_Mstr_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}

			String hospital_department_name = request.getParameter("hospital_department_name");
			String hospital_department_id = request.getParameter("hos_department_id");
			String status = request.getParameter("status");

			if (hospital_department_name == null || hospital_department_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Department Name.");
				return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(hospital_department_name) == false) {
				ra.addAttribute("msg","Department "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
			}
			
			
		    if (validation.isOnlyAlphabetDASH(hospital_department_name) == false) {
		    	ra.addAttribute("msg","Department "+ validation.isOnlyAlphabetMSGDASH);
		    	return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url"); 
		    }
			 
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
			}
			
			if (hospital_department_id == null || hospital_department_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Hospital Department.");
				return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
			}

			
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = td.getId() > 0 ? td.getId() : 0;
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  CLG_REG_HOSP_INFRA_DEPT_MSTR where upper(hospital_department_name)=:hospital_department_name and status=:status and id !=:id")
						.setParameter("hospital_department_name", td.getHospital_department_name().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				int idd =0;
				System.out.print("hospital_department_id--------------------"+hospital_department_id);
				if (id == 0) {
					td.setHospital_department_name(hospital_department_name);
					td.setHos_department_id(Integer.parseInt(hospital_department_id));
					td.setCreated_by(userid);
					td.setCreated_date(date);
					if (c == 0) {
						idd = (int)sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setHospital_department_name(hospital_department_name);
					td.setHos_department_id(Integer.parseInt(hospital_department_id));
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = sdao.updateHospitalInfraDeptDept(td);
						//For Core Directory
						//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg",msg);
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
				//For Core Directory
				//SM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
		}
	
	
	
	
	
	@PostMapping("/getFilterHospitalInfraDept_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String hospital_department_name, String status,Integer hos_department_id) {

		return sdao.DataTableHospitalInfraDeptDataList(startPage, pageLength, Search, orderColunm, orderType, hospital_department_name,status,hos_department_id);

	}

	@PostMapping("/getTotalHospitalInfraDept_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String status,String hospital_department_name,Integer hos_department_id) {
		return sdao.DataTableHospitalInfraDeptDataTotalCount(Search,status, hospital_department_name,hos_department_id);
		
	}
	
	@RequestMapping(value = "/hospital_infra_dept_delete_Url", method = RequestMethod.POST)
	public ModelAndView hospital_infra_dept_delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("get_Hospital_Infra_Dept_Mstr_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CLG_REG_HOSP_INFRA_DEPT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//SM_dirdao.Delete_System_Mstr_Data(id);
			System.out.println();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
			
		}
		return new ModelAndView("redirect:get_Hospital_Infra_Dept_Mstr_Url");
	}




	
}
