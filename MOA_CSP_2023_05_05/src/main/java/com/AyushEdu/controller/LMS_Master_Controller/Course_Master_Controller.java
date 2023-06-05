package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Core_Directory.Course_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Degree_Master_CD_DAO;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Course_Master_DAO;
import com.AyushEdu.validation.ValidationController;

import freemarker.core.ParseException;



@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Course_Master_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	CommonController common;
	
	@Autowired
	Course_Master_DAO Cdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Course_Master_CD_DAO  CM_dirdao;
	
	
	@GetMapping(value = "/course_master_url")
	public ModelAndView course_master_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		
		 try {	
			 
			//SECURITY RAHUL
			 
			 
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("course_master_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		
		 model.addAttribute("msg", msg);
		 model.put("TypeOfcontent", common.getTypeOfcontent(sessionFactory));
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 
		return new ModelAndView("course_master_Tiles");
	
}
	
	@PostMapping(value = "/Course_Master_action")	
	public ModelAndView Course_Master_action(@Validated @ModelAttribute("Course_Master_cmd") EDU_LMS_COURSE_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String type_of_content_id = request.getParameter("type_of_content_id");
		String course_name = request.getParameter("course_name");
		String course_code = request.getParameter("course_code");
		String status = request.getParameter("status");
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		if (type_of_content_id == null || type_of_content_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Type Of Lecture.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (validation.maxlengthcheck100(course_name) == false) {
			ra.addAttribute("msg", "Course Name " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:course_master_url");
		}
//		if (validation.isOnlyAlphabetDASH(course_name) == false) {
//			ra.addAttribute("msg","Course Name "+ validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:course_master_url");
//		}
		if (validation.isAlphabetCDASH(course_name) == false) {
			ra.addAttribute("msg","Course Name "+ validation.isAlphabetWSCDASH);
			return new ModelAndView("redirect:course_master_url");
		}
		
		if (course_code == null || course_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Code.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (validation.maxlengthcheck15(course_code) == false) {
			ra.addAttribute("msg", "Course Code " + validation.MaxlengthcheckMSG15);
			return new ModelAndView("redirect:course_master_url");
		}
//		if (validation.isOnlyAlphabetNumeric(course_code) == false) {
//			ra.addAttribute("msg", "Course Code " + validation.isOnlyAlphabetNumberMSG);
//			return new ModelAndView("redirect:course_master_url");
//		}
//		
		if (validation.isAlphabetCDASH(course_code) == false) {
			ra.addAttribute("msg", "Course Code " + validation.isAlphabetWSCDASH);
			return new ModelAndView("redirect:course_master_url");
		}
		
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:course_master_url");
		}
	
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_COURSE_MASTER where type_of_content_id=:type_of_content_id and upper(status)=:status and upper(course_name)=:course_name and upper(course_code)=:course_code and id !=:id")
					.setParameter("type_of_content_id", td.getType_of_content_id())
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("course_name", td.getCourse_name().toUpperCase())
					.setParameter("course_code", td.getCourse_code().toUpperCase()).setParameter("id", id).uniqueResult();
			System.err.println("id checkk"+id);
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
//				else {
//					td.setModified_by(username);
//					td.setModified_date(date);
//					if (c == 0) {
//						
//						String msg = ddao.UpdateName(td);
//						 ra.addAttribute("msg", msg);
//					} else {
//						 ra.addAttribute("msg", "Data already Exist.");
//					}
//				}
			tx.commit();
			//For Core Directory
			CM_dirdao.Insert_Course_Mstr_Data(idd);
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

		return new ModelAndView("redirect:course_master_url");
	}
	
	@PostMapping("/getFilter_Course_master_data")

	public @ResponseBody List<Map<String, Object>> getFilter_Course_master_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType,String course_name, String type_of_content_id, String status, String course_code) {

		return Cdao.DataTable_Course_masterDataList(startPage, pageLength, Search, orderColunm, orderType,type_of_content_id, course_name, status, course_code);

	}

	@PostMapping("/getTotal_Course_master_dataCount")

	public @ResponseBody long getTotal_Course_master_dataCount(HttpSession sessionUserId, String Search,String type_of_content_id, String course_name,String status,String course_code) {
		return Cdao.DataTable_Course_masterDataTotalCount(Search,type_of_content_id, course_name, status, course_code);

	}
	
	
	@PostMapping(value = "/delete_course_mstr_Url")
	public ModelAndView delete_course_mstr_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_COURSE_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			
			tx.commit();
			CM_dirdao.Delete_Courser_Mstr_Data(id);  
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
		}finally {
			
		}
		return new ModelAndView("redirect:course_master_url");
	}
	
	@RequestMapping(value = "/Edit_course_mstrUrl")
	public ModelAndView Edit_course_mstrUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {

		 try {	
			 
		if(request.getHeader("Referer") == null ) { 
		//	 sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_COURSE_MASTER Edit_course_mstr_Details = Cdao.get_CourseByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("Edit_course_mstr_Details", Edit_course_mstr_Details);
		Mmap.put("TypeOfcontent", common.getTypeOfcontent(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("edit_course_master_Tiles");
	}
	
	@RequestMapping(value = "/Edit_Course_Master_action", method = RequestMethod.POST)
	public ModelAndView Edit_Course_Master_action(@ModelAttribute("Edit_Course_Master_cmd") EDU_LMS_COURSE_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		int type_of_content_id = Integer.parseInt(request.getParameter("type_of_content_id"));
		String course_name = request.getParameter("course_name").trim();
		String course_code = request.getParameter("course_code").trim();
		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (type_of_content_id == 0) {
			ra.addAttribute("msg", "Please Select Type of Lecture.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (course_name == null || course_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Name.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (validation.maxlengthcheck100(course_name) == false) {
			ra.addAttribute("msg", "Course Name " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:course_master_url");
		}
	
//		if (validation.isOnlyAlphabetDASH(course_name) == false) {
//			ra.addAttribute("msg","Course Name "+ validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:course_master_url");
//		}
		
		if (course_code == null || course_code.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Code.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (validation.maxlengthcheck15(course_code) == false) {
			ra.addAttribute("msg", "Course Code " + validation.MaxlengthcheckMSG15);
			return new ModelAndView("redirect:course_master_url");
		}
//		if (validation.isOnlyAlphabetNumeric(course_code) == false) {
//			ra.addAttribute("msg", "Course Code " + validation.isOnlyAlphabetNumberMSG);
//			return new ModelAndView("redirect:course_master_url");
//		}
		
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:course_master_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:course_master_url");
		}
		
		
		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_COURSE_MASTER where type_of_content_id=:type_of_content_id and course_name=:course_name and course_code=:course_code and status=:status and id !=:id");
			q0.setParameter("type_of_content_id", type_of_content_id);
			
			q0.setParameter("course_name", course_name);
			
			q0.setParameter("course_code", course_code);

			q0.setParameter("status", status);

			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_COURSE_MASTER set type_of_content_id=:type_of_content_id,course_name=:course_name,course_code=:course_code,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("type_of_content_id", type_of_content_id).setParameter("course_name", course_name)
						.setParameter("course_code", course_code).setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				CM_dirdao.Update_Course_Mstr_Data( id,course_name,status, username,new Date(),course_code,type_of_content_id);

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
		
		return new ModelAndView("redirect:course_master_url");
	}
}
