package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import com.AyushEdu.Core_Directory.Module_Mstr_CD_Dao;
import com.AyushEdu.Core_Directory.University_CD_Dao;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.UniversityDao;
import com.AyushEdu.dao.LMS_Teacher.TeacherDetailsDao;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class University_mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	UniversityDao udao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	University_CD_Dao  DM_dirdao;
	
	@RequestMapping(value = "/University_Url", method = RequestMethod.GET)
	 public ModelAndView University_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		try {
			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		String role = session.getAttribute("role").toString();
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getUniversityTypelList", common.getUniversityTypelList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("s_name", common.getsysList(sessionFactory));
		Mmap.addAttribute("msg", msg);
		Mmap.put("getOrganizationList", common.getOrganizationList(sessionFactory));
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("Uni_Tiles");
	}
	
	@PostMapping(value = "/Univers_action")
	public ModelAndView Univers_action(@Validated @ModelAttribute("Univers_cmd") EDU_LMS_UNIVERSITY_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Map<String,String> mObj=new HashMap<>();
		
		String university_name = request.getParameter("university_name");
		String university_code = request.getParameter("university_code");
		String university_type = request.getParameter("university_type");
		String organization_id = request.getParameter("organization_id");
		String system_id = request.getParameter("system_id");
		String country_id = request.getParameter("country_id");
		String state_id = request.getParameter("state_id");
		String district_id = request.getParameter("district_id");
		String city_name = request.getParameter("city_name");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		
		
		if (university_name == null || university_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter University.");
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.maxlengthcheck100(university_name) == false) {
			ra.addAttribute("msg","University "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.isOnlyAlphabetDASH(university_name) == false) {
				ra.addAttribute("msg","University "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:University_Url");
		}
		
		if (university_code == null || university_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter University Code.");
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.maxlengthcheck50(university_code) == false) {
			ra.addAttribute("msg","University Code "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.isOnlyAlphabetNumeric(university_code) == false) {
			ra.addAttribute("msg", "University Code " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:University_Url");
		}
//		if (validation.isOnlyAlphabetNumber(university_code) == false) {
//			ra.addAttribute("msg","University Code "+ validation.isOnlyAlphabetNumberMSG);
//			return new ModelAndView("redirect:University_Url");
//	    }
		if (university_type == null || university_type.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select University Type.");
			return new ModelAndView("redirect:University_Url");
		}
		if (organization_id == null || organization_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Organization.");
			return new ModelAndView("redirect:University_Url");
		}
		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:University_Url");
		}
		if (country_id == null || country_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Country.");
			return new ModelAndView("redirect:University_Url");
		}
		if (state_id == null || state_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select State.");
			return new ModelAndView("redirect:University_Url");
		}
		if (district_id == null || district_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select District.");
			return new ModelAndView("redirect:University_Url");
		}
		if (city_name == null || city_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter City.");
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.maxlengthcheck50(city_name) == false) {
			ra.addAttribute("msg","City "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.isOnlyAlphabetDASH(city_name) == false) {
			ra.addAttribute("msg","City "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:University_Url");
		}
		if (address == null || address.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Address.");
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.isOnlyAlphabetNumeric(address) == false) {
			ra.addAttribute("msg", "Address " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:University_Url");
		}
		if (validation.maxlengthcheck100(address) == false) {
			ra.addAttribute("msg","Address "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:University_Url");
		}
//		if (validation.isOnlyAlphabetNumber(address) == false) {
//			ra.addAttribute("msg","Address "+ validation.isOnlyAlphabetNumberMSG);
//			return new ModelAndView("redirect:University_Url");
//	    }
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:University_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:University_Url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_UNIVERSITY_MSTR where upper(university_name)=:university_name and upper(university_code)=:university_code and "
					+ "university_type=:university_type and organization_id=:organization_id and system_id=:system_id and country_id=:country_id and state_id=:state_id"
					+ " and district_id=:district_id and upper(city_name)=:city_name and upper(address)=:address and "
					+ "upper(status)=:status and id !=:id")
//					"select count(id) from EDU_LMS_UNIVERSITY_MSTR where upper(university_name)=:university_name and upper(university_code)=:university_code and"
//					+ " country_id=:country_id \n"
//					+ "	and state_id=:state_id and district_id=:district_id and upper(address)=:address \n"
//					+ "	and upper(city_name)=:city_name and upper(status)=:status ")
					
					.setParameter("university_name", td.getUniversity_name().toUpperCase())
					.setParameter("university_code", td.getUniversity_code().toUpperCase())
					.setParameter("university_type", td.getUniversity_type())
					.setParameter("organization_id", td.getOrganization_id())
					.setParameter("system_id", td.getSystem_id())
					.setParameter("country_id", td.getCountry_id())
					.setParameter("state_id", td.getState_id())
					.setParameter("district_id", td.getDistrict_id())
					.setParameter("city_name", td.getCity_name().toUpperCase())
					.setParameter("address", td.getAddress().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase())
					
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setUniversity_type(Integer.parseInt(university_type));
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");

				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			//For Core Directory
			DM_dirdao.Insert_University_Mstr_Data(idd);
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
		return new ModelAndView("redirect:University_Url");
	}
	
	@PostMapping("/getFilterUniversitydata")
	public @ResponseBody List<Map<String, Object>> getFilterUniversitydata(int startPage, int pageLength,String Search, String orderColunm, String orderType, 
			String university_name,String university_code, String country_id,String state_id,String district_id,String city_name, String status, String university_type, String organization_id, String system_id) {
		return udao.DataTableUniversDataList(startPage, pageLength, Search, orderColunm, orderType, university_name, university_code, country_id,state_id,district_id, city_name, status, university_type, organization_id, system_id);
	}
	@PostMapping("/getTotalUniversity_dataCount")
	public @ResponseBody long getTotalUniversity_dataCount(HttpSession sessionUserId, String Search, String university_name,String university_code, 
			String country_id,String state_id,String district_id,String city_name, String status, String university_type, String organization_id, String system_id) {
		return udao.DataTableUniversDataTotalCount(Search,university_name, university_code, country_id,state_id,district_id, city_name, status,  university_type, organization_id, system_id);
	}
	
////////////Edit_url
	
			@RequestMapping(value = "/Edit_UniverUrl")
			public ModelAndView Edit_UniverUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, HttpSession sessionEdit,HttpServletRequest request) {

				if(request.getHeader("Referer") == null ) { 
			//		sessionEdit.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				EDU_LMS_UNIVERSITY_MSTR Univers_dtl = udao.getUniversByid(Integer.parseInt(updateid));
				String role = sessionEdit.getAttribute("role").toString();
				Mmap.addAttribute("Univers_dtl", Univers_dtl);
				Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
				Mmap.put("getUniversityTypelList", common.getUniversityTypelList(sessionFactory));
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				Mmap.put("s_name", common.getSystemList(sessionFactory,role));
				Mmap.addAttribute("msg", msg);
				Mmap.put("getOrganizationList", common.getOrganizationList(sessionFactory));
				return new ModelAndView("EditUni_Tiles");
			}

/////////////////////////Edit
			
			@RequestMapping(value = "/Edit_UniversAction", method = RequestMethod.POST)
			public ModelAndView Edit_UniversAction(@ModelAttribute("EditUnivers_cmd") EDU_LMS_UNIVERSITY_MSTR us,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

				if(request.getHeader("Referer") == null ) { 
				//	session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String username = session.getAttribute("username").toString();

				String id = request.getParameter("id");
				String university_name = request.getParameter("university_name").trim();
				String university_code = request.getParameter("university_code").trim();
				String university_type = request.getParameter("university_type");
				String organization_id = request.getParameter("organization_id");
				String system_id = request.getParameter("system_id");
				String country_id = request.getParameter("country_id");
				String state_id = request.getParameter("state_id");
				String district_id = request.getParameter("district_id");
				String city_name = request.getParameter("city_name").trim();
				String address = request.getParameter("address").trim();
				String status = request.getParameter("status");
				
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (university_name == null || university_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter University.");
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.maxlengthcheck100(university_name) == false) {
					ra.addAttribute("msg","University "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.isOnlyAlphabetDASH(university_name) == false) {
					ra.addAttribute("msg","University "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:University_Url");
				}
				if (university_code == null || university_code.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter University Code.");
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.maxlengthcheck50(university_code) == false) {
					ra.addAttribute("msg","University Code "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.maxlengthcheck50(university_code) == false) {
					ra.addAttribute("msg","University Code "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:University_Url");
				}
				if (university_type == null || university_type.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select University Type.");
					return new ModelAndView("redirect:University_Url");
				}
				if (organization_id == null || organization_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Organization.");
					return new ModelAndView("redirect:University_Url");
				}
				if (system_id == null || system_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select System.");
					return new ModelAndView("redirect:University_Url");
				}
				if (country_id == null || country_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Country.");
					return new ModelAndView("redirect:University_Url");
				}
				if (state_id == null || state_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select State.");
					return new ModelAndView("redirect:University_Url");
				}
				if (district_id == null || district_id.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select District.");
					return new ModelAndView("redirect:University_Url");
				}
				if (city_name == null || city_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter City.");
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.maxlengthcheck50(city_name) == false) {
					ra.addAttribute("msg","City "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.isOnlyAlphabetDASH(city_name) == false) {
					ra.addAttribute("msg","City "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:University_Url");
				}
				if (address == null || address.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Address.");
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.isOnlyAlphabetNumeric(address) == false) {
					ra.addAttribute("msg", "Address " + validation.isOnlyAlphabetNumberMSG);
					return new ModelAndView("redirect:University_Url");
				}
				if (validation.maxlengthcheck100(address) == false) {
					ra.addAttribute("msg","Address "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:University_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:University_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:University_Url");
				}
				
				try {
					Query q0 = session1.createQuery(
						"  select count(id) from EDU_LMS_UNIVERSITY_MSTR where university_name=:university_name and university_code=:university_code"
						+ " and university_type=:university_type and organization_id=:organization_id and system_id=:system_id and "
						+ " country_id=:country_id and state_id=:state_id and district_id=:district_id and city_name=:city_name "
						+ " and address=:address and status=:status "
						+ " and  id !=:id");
					
					q0.setParameter("university_name", university_name);
					q0.setParameter("university_code", university_code);
					q0.setParameter("university_type", Integer.parseInt(university_type));
					q0.setParameter("organization_id", Integer.parseInt(organization_id));
					q0.setParameter("system_id", Integer.parseInt(system_id));
					q0.setParameter("country_id", Integer.parseInt(country_id));
					q0.setParameter("state_id", Integer.parseInt(state_id));
					q0.setParameter("district_id", Integer.parseInt(district_id));
					q0.setParameter("city_name", city_name);
					q0.setParameter("address", address);
					q0.setParameter("status", status);
					q0.setParameter("id", Integer.parseInt(id));

					Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update EDU_LMS_UNIVERSITY_MSTR set university_name=:university_name,university_code=:university_code, "
							    + " university_type=:university_type , organization_id=:organization_id , system_id=:system_id,"
								+ "country_id=:country_id,state_id=:state_id,district_id=:district_id,city_name=:city_name,address=:address,status=:status, "
								+ "modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";

					Query query = session1.createQuery(hql)
							.setParameter("university_name", university_name)
							.setParameter("university_code", university_code)
							.setParameter("university_type", Integer.parseInt(university_type))
							.setParameter("organization_id", Integer.parseInt(organization_id))
							.setParameter("system_id", Integer.parseInt(system_id))
							.setParameter("country_id", Integer.parseInt(country_id))
							.setParameter("state_id", Integer.parseInt(state_id))
							.setParameter("district_id", Integer.parseInt(district_id))
							.setParameter("city_name", city_name)
							.setParameter("address", address)
							.setParameter("status", status)
							.setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(id));
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();

					//For Core Directory
					DM_dirdao.Update_University_Mstr_Data(Integer.parseInt(id),  university_name,  university_code,  address,  Integer.parseInt(country_id),  Integer.parseInt(state_id), Integer.parseInt(district_id), city_name, Integer.parseInt(university_type),  Integer.parseInt(organization_id), Integer.parseInt(system_id)
							 ,  status,  username,  new Date());
					if (msg.equals("1")) {
	                                  
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						ra.addAttribute("msg", "Data Not Updated.");
					}
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}

				} catch (RuntimeException e) {
					try {
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;

				} finally {
					if (session1 != null) {
						session1.close();
					}
				}
				
				return new ModelAndView("redirect:University_Url");
			}
			
			
////////////Delete
	
	@PostMapping(value = "/deleteUni_Url")
	public ModelAndView deleteUni_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_UNIVERSITY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_University_Mstr_Data(id); 
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
		return new ModelAndView("redirect:University_Url");
	}

	
}
