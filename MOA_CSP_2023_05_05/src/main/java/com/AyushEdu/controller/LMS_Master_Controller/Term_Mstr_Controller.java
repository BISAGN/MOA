package com.AyushEdu.controller.LMS_Master_Controller;
import java.security.Principal;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Term_CD_Dao;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TERM_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.TermDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Term_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	@Autowired
	TermDao tdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Term_CD_Dao  DM_dirdao;
	
	
	@RequestMapping(value = "/Term_url", method = RequestMethod.GET)
	public ModelAndView Term_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		 try {	
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Term_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 
		return new ModelAndView("Term_Tiles");
	}
	
	@PostMapping(value = "/TermAction")
	public ModelAndView TermAction(@Validated @ModelAttribute("TermCMD") EDU_LMS_TERM_MASTER td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Term_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String term = request.getParameter("term");
		String prof_name = request.getParameter("prof_name");
		String status = request.getParameter("status");

		if (term == null || term.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Term.");
			return new ModelAndView("redirect:Term_url");
		}
		if (validation.maxlengthcheck1D(term) == false) {
			ra.addAttribute("msg", "Term " + validation.MaxlengthcheckMSG1D);
			return new ModelAndView("redirect:Term_url");
		}
		if (validation.isOnlyNumer(term) == true) {
			ra.addAttribute("msg", "Term " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Term_url");
		}
		if (prof_name == null || prof_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Profession Name.");
			return new ModelAndView("redirect:Term_url");
		}
		if (validation.isOnlyAlphabetNumeric(prof_name) == false) {
			ra.addAttribute("msg", "Profession " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Term_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Term_url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_TERM_MASTER where upper(term)=:term and upper(prof_name)=:prof_name and upper(status)=:status and id !=:id")
					.setParameter("term", td.getTerm().toUpperCase())
					.setParameter("prof_name", td.getProf_name().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setTerm(term);
				td.setProf_name(prof_name);
				td.setCreated_by(username);
				td.setCreated_dt(date);
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
			DM_dirdao.Insert_Term_Mstr_Data(idd);
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
		return new ModelAndView("redirect:Term_url");
	}
	
	@PostMapping("/getFilterterm_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String term, String prof_name, String status) {
		return tdao.DataTabletermDataList(startPage, pageLength, Search, orderColunm, orderType, term, prof_name, status);
	}

	@PostMapping("/getTotalterm_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String term,String prof_name) {
		return tdao.DataTabletermDataTotalCount(Search, term,prof_name);
	}
	
	@RequestMapping(value = "/Edit_termUrl", method = RequestMethod.GET)
	public ModelAndView Edit_termUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {

		try {
			
		if(request.getHeader("Referer") == null ) { 
		//	sessionEdit.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Term_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_TERM_MASTER term_Details = tdao.gettermByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.addAttribute("term_Details", term_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("editTerm_Tiles");
	}
	
	@RequestMapping(value = "/edit_term_Action", method = RequestMethod.POST)
	public ModelAndView edit_term_Action(@ModelAttribute("edit_termCMD") EDU_LMS_TERM_MASTER rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
		//	session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Term_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();

		String id = request.getParameter("id");
		String term = request.getParameter("term").trim();
		String prof_name = request.getParameter("prof_name").trim();
		String status = request.getParameter("status");
	
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		
		if (term == null || term.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Term");
			return new ModelAndView("redirect:Term_url");
		}
		
		if (validation.maxlengthcheck1D(term) == false) {
			ra.addAttribute("msg", "Term " + validation.MaxlengthcheckMSG1D);
			return new ModelAndView("redirect:Term_url");
		}
		if (validation.isOnlyNumer(term) == true) {
			ra.addAttribute("msg", "Term " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:Term_url");
		}
		
		if (prof_name == null || prof_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Profession");
			return new ModelAndView("redirect:Term_url");
		}
		if (validation.isOnlyAlphabetNumeric(prof_name) == false) {
			ra.addAttribute("msg", "Profession " + validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:degree_master_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Term_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Term_url");
		}
		
		try {
			Query<?> q0 = session1.createQuery(
					"select count(id) from EDU_LMS_TERM_MASTER where term=:term and prof_name=:prof_name and status=:status and id !=:id");
			
			q0.setParameter("term", term);
			q0.setParameter("prof_name", prof_name);
			q0.setParameter("status", status);
			q0.setParameter("id", Integer.parseInt(id));

			Long c = (Long) q0.uniqueResult();
			if (c == 0) {
				String hql = "update EDU_LMS_TERM_MASTER set term=:term,prof_name=:prof_name, status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("term",term)
						.setParameter("prof_name",prof_name)
						.setParameter("status",status)
						.setParameter("modified_by", username).setParameter("modified_dt", new Date())
						.setParameter("id", Integer.parseInt(id));
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				DM_dirdao.Update_Term_Mstr_Data( Integer.parseInt(id),  term,  status,prof_name,  username,  new Date());
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
	
		return new ModelAndView("redirect:Term_url");
	}

	@RequestMapping(value = "/deleteterm_Url", method = RequestMethod.POST)
	public ModelAndView deleteterm_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Term_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_TERM_MASTER set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_dt", new Date()).setParameter("status", "2").executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Term_Mstr_Data(id);
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
		return new ModelAndView("redirect:Term_url");
	}
}
