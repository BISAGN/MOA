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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Type_of_Degree_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	Type_of_Degree_MstrDao  TDdao;
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Type_of_Degree_Mstr_CD_Dao  DM_dirdao;
	
	@GetMapping
	(value = "/type_of_degree_url")
	public ModelAndView type_of_degree_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		try {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("type_of_degree_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			model.put("msg", msg);
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("Type_of_Degree_Tiles");
 }
	
	@PostMapping(value = "/type_of_degree_action")
	public ModelAndView type_of_degree_action(@Validated @ModelAttribute("type_of_degree_cmd") EDU_LMS_TYPE_OF_DEGREE_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("type_of_degree_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Map<String,String> mObj=new HashMap<>();
		
		String type_of_degree = request.getParameter("type_of_degree");
		String status = request.getParameter("status");
	
		if (type_of_degree == null || type_of_degree.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Type of Degree.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (validation.maxlengthcheck100(type_of_degree) == false) {
			ra.addAttribute("msg","Type of Degree "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (validation.isOnlyAlphabetDASH(type_of_degree) == false) {
			ra.addAttribute("msg","Type of Degree "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_TYPE_OF_DEGREE_MASTER where status=:status and type_of_degree=:type_of_degree and id !=:id")
					.setParameter("status", td.getStatus())
					.setParameter("type_of_degree", td.getType_of_degree())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
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
			DM_dirdao.Insert_Type_Of_Degree_Mstr_Data(idd);
			
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
		return new ModelAndView("redirect:type_of_degree_url");
	}
	
	@PostMapping("/getFiltertype_of_degree_data")
	public @ResponseBody List<Map<String, Object>> getFiltertype_of_degree_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String type_of_degree, String status) {
		return TDdao.DataTabletype_of_degreeDataList(startPage, pageLength, Search, orderColunm, orderType, type_of_degree, status);

	}
	@PostMapping("/getTotaltype_of_degree_dataCount")
	public @ResponseBody long getTotaltype_of_degree_dataCount(HttpSession sessionUserId, String Search, String type_of_degree,String status) {
		return TDdao.DataTabletype_of_degreeDataTotalCount(Search,type_of_degree,status);
	}

	@PostMapping(value = "/delete_type_of_degreeurl")
	public ModelAndView delete_type_of_degreeurl(@ModelAttribute("id2") Integer id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("type_of_degree_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_TYPE_OF_DEGREE_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status",2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Type_Of_Degree_Mstr_Data(id); 
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
		return new ModelAndView("redirect:type_of_degree_url");
	}
	
	@RequestMapping(value = "/Edit_type_of_degreeurl")
	public ModelAndView Edit_type_of_degreeurl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {

		 try {
		if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("type_of_degree_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_TYPE_OF_DEGREE_MASTER Type_of_Degree_Details = TDdao.getType_of_DegreeByid(Integer.parseInt(updateid));

		Mmap.addAttribute("msg", msg);
		Mmap.addAttribute("Type_of_Degree_Details", Type_of_Degree_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("Edit_Type_of_Degree_Tiles");
	}
	
	@RequestMapping(value = "/edit_type_of_degree_action", method = RequestMethod.POST)
	public ModelAndView edit_type_of_degree_action(@ModelAttribute("edit_type_of_degree_cmd") EDU_LMS_TYPE_OF_DEGREE_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("type_of_degree_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		String id = request.getParameter("id");
		String type_of_degree = request.getParameter("type_of_degree").trim();
		String status = request.getParameter("status");
	
		if (type_of_degree == null || type_of_degree.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Type of Degree.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (validation.maxlengthcheck100(type_of_degree) == false) {
			ra.addAttribute("msg","Type of Degree "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (validation.isOnlyAlphabetDASH(type_of_degree) == false) {
			ra.addAttribute("msg","Type of Degree "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:type_of_degree_url");
		}
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		try {
			Query<?> q0 = session1.createQuery(
					"select count(id) from EDU_LMS_TYPE_OF_DEGREE_MASTER where type_of_degree=:type_of_degree and status=:status and id !=:id");
			
			q0.setParameter("status", Integer.parseInt(status));
			q0.setParameter("type_of_degree", type_of_degree);

			q0.setParameter("id", Integer.parseInt(id));

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_TYPE_OF_DEGREE_MASTER set type_of_degree=:type_of_degree,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("type_of_degree",type_of_degree)
						.setParameter("status", Integer.parseInt(status))
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				DM_dirdao.Update_Type_Of_Degree_Mstr_Data( Integer.parseInt(id),  type_of_degree,  Integer.parseInt(status),  username,  new Date());
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
		
		return new ModelAndView("redirect:type_of_degree_url");
	}
	@GetMapping
	(value = "/viewlistofdegreeUrl")
	public ModelAndView viewlistofdegreeUrl(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		try {
				model.put("msg", msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("lstofDegSysToD_Tiles");
	}
	
//	@PostMapping("/getListofdegSysToD")
//	public @ResponseBody ArrayList<ArrayList<String>> getListofdegSysToD() {
//		return TDdao.getDataListofdegSysToD();
//	}
}
