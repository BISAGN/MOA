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
import com.AyushEdu.Core_Directory.Type_Of_Lecture_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_CONTENT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Type_of_ContentDao;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class TypeofContent_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	CommonController common;
	@Autowired
	 ValidationController validation;
	@Autowired
	Type_of_ContentDao  Mdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Type_Of_Lecture_Master_CD_DAO  Lect_dirdao;
	
	// ------------------------------- For page Open -------------------------------------
	@GetMapping
	(value = "/typeofcontent_url")
	public ModelAndView typeofcontent_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request ) {
		try {
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("typeofcontent_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			model.put("msg", msg);
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("typeofcontentTiles");
 }
	@PostMapping(value = "/TypeofContent_action")
	public ModelAndView TypeofContent_action(@Validated @ModelAttribute("TypeofContent_cmd") EDU_LMS_TYPE_OF_CONTENT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("typeofcontent_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Map<String,String> mObj=new HashMap<>();
		
		String type_of_content = request.getParameter("type_of_content");
		String status = request.getParameter("status");

		
		if (type_of_content == null || type_of_content.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Type of Lecture.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (validation.maxlengthcheck100(type_of_content) == false) {
			ra.addAttribute("msg","Type of Lecture "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (validation.isOnlyAlphabetDASH(type_of_content) == false) {
			ra.addAttribute("msg","Type of Lecture "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_TYPE_OF_CONTENT_MSTR where upper(status)=:status and upper(type_of_content)=:type_of_content  and id !=:id")
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("type_of_content", td.getType_of_content().toUpperCase())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");

				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			//For Core Directory
			Lect_dirdao.Insert_Type_Of_Lecture_Master_Data(idd);
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
		return new ModelAndView("redirect:typeofcontent_url");
	}
	
	@PostMapping("/getFiltertype_of_content_data")
	public @ResponseBody List<Map<String, Object>> getFiltertype_of_content_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String type_of_content, String status) {
		return Mdao.DataTabletype_of_contentDataList(startPage, pageLength, Search, orderColunm, orderType, type_of_content, status);

	}
	@PostMapping("/getTotaltype_of_content_dataCount")
	public @ResponseBody long getTotaltype_of_content_dataCount(HttpSession sessionUserId, String Search, String type_of_content,String status) {
		return Mdao.DataTabletype_of_contentDataTotalCount(Search, type_of_content,status);
	}
	
	@RequestMapping(value = "/Edit_type_of_content_Url")
	public ModelAndView Edit_type_of_content_Url(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, 
			HttpSession sessionEdit) {

		try {
		if(request.getHeader("Referer") == null ) { 
		//	sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("typeofcontent_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_TYPE_OF_CONTENT_MSTR type_of_contentdetails = Mdao.gettype_of_contentByid(Integer.parseInt(updateid));

		Mmap.addAttribute("msg", msg);
		Mmap.addAttribute("type_of_contentdetails", type_of_contentdetails);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("edittype_of_contentTiles");
	}
	
	@RequestMapping(value = "/edit_TypeofContent_action", method = RequestMethod.POST)
	public ModelAndView edit_TypeofContent_action(@ModelAttribute("edit_TypeofContent_cmd") EDU_LMS_TYPE_OF_CONTENT_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//	session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("typeofcontent_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String type_of_content = request.getParameter("type_of_content").trim();
		String status = request.getParameter("status");
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (type_of_content == null || type_of_content.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Type of Lecture.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (validation.maxlengthcheck100(type_of_content) == false) {
			ra.addAttribute("msg","Type of Lecture "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (validation.isOnlyAlphabetDASH(type_of_content) == false) {
			ra.addAttribute("msg","Type of Lecture "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:typeofcontent_url");
		}
		
		try {
			Query<?> q0 = session1.createQuery(
					"select count(id) from EDU_LMS_TYPE_OF_CONTENT_MSTR where type_of_content=:type_of_content and status=:status  and id !=:id");
			q0.setParameter("type_of_content", type_of_content);
			q0.setParameter("status", status);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_TYPE_OF_CONTENT_MSTR set type_of_content=:type_of_content,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("type_of_content", type_of_content).setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				Lect_dirdao.Update_Type_Of_Lecture_Master_Data( id,type_of_content,status,username,new Date());
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
		
		return new ModelAndView("redirect:typeofcontent_url");
	}
	
	@PostMapping(value = "/deletetype_of_content_Url")
	public ModelAndView deletetype_of_content_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("typeofcontent_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_TYPE_OF_CONTENT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			tx.commit();
			Lect_dirdao.Delete_Type_Of_Lecture_Master_Data(id);  
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
		return new ModelAndView("redirect:typeofcontent_url");
	}
}
