package com.AyushEdu.controller.Curriculum_Mstr;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_EXAM_TYPE_MSTR_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Exam_Type_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CC_EXAM_TYPE_MSTR_DAO Edao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Exam_Type_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Exam_Type_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Exam_Type_Tiles");
	}
	
	// ==========================================SAVE/view//Edit
		// Professional==========================================

		@PostMapping(value = "/ExamtypeAction")
		public ModelAndView ExamtypeAction(@Validated @ModelAttribute("ExamtypeCMD") CC_TB_EXAM_TYPE_MSTR td,
				BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,Principal principal,
				RedirectAttributes ra) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String userId = session.getAttribute("userId").toString();
			String exam_type = request.getParameter("exam_type");
			String status = request.getParameter("status");

			if (exam_type == null || exam_type.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Exam Type.");
				return new ModelAndView("redirect:Exam_Type_Mstr_Url");
			}
			if (validation.maxlengthcheck(exam_type) == false) {
				ra.addAttribute("msg", "Exam Type." + validation.MaxlengthcheckMSG);
				return new ModelAndView("redirect:Exam_Type_Mstr_Url");
			}

			if (validation.isOnlyAlphabet(exam_type) == false) {
				ra.addAttribute("msg","Exam Type "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Exam_Type_Mstr_Url");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:Exam_Type_Mstr_Url");
			}
			if (status == null || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Exam_Type_Mstr_Url");
			}
			int id = Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			String username = principal.getName();
//			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  CC_TB_EXAM_TYPE_MSTR where exam_type=:exam_type and status=:status and id !=:id")
						.setParameter("exam_type", td.getExam_type())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();

				if (id == 0) {
					td.setCreated_by(username);
					td.setCreated_date(date);
					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setExam_type(exam_type.trim());
					td.setModified_by(username);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Edao.updateExamtype(td);
						if (msg == "Data Updated Successfully") {
							model.put("msg", msg);
							model.put("Nmsg", "0");
						} else {
							model.put("msg", msg);
							model.put("Nmsg", "1");
						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						model.put("msg", "Data already Exist");
						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
			}
				catch (RuntimeException e) {
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

			return new ModelAndView("redirect:Exam_Type_Mstr_Url");
		}

		@PostMapping("/getFilterExamtype_data")
		public @ResponseBody List getFilterRegistration_data(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String exam_type, String status) {
			System.err.println("IN ");
			return Edao.DataTableExamtypeDataList(startPage, pageLength, Search, orderColunm, orderType, exam_type,
					status);
		}

		@PostMapping("/getTotalExamtype_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
				String exam_type,String status) {
			return Edao.DataTableExamtypeDataTotalCount1(Search, exam_type,status);

		}

		// -----edit

		@RequestMapping(value = "/Edit_Exam_Type_Mstr_Url", method = RequestMethod.POST)
		public ModelAndView Edit_Exam_Type_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				Principal principal, @RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "exam_type", required = false) String exam_type,
				@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
				HttpSession sessionEdit) {
			try {	
//				SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			CC_TB_EXAM_TYPE_MSTR Exam_type_Details = Edao.getExamtypeByid(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);
//			Mmap.put("empl_type", empl_type);
			Mmap.put("status", status);
			Mmap.put("Exam_type_Details", Exam_type_Details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			Mmap.put("Examtypeinfo", Edao.getExamtypeByid(Integer.parseInt(updateid)));

			tx.commit();
			sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("Exam_Type_Tiles");
		}

		@RequestMapping(value = "/Exam_Type_Mstr_Delete_Url", method = RequestMethod.POST)
		public ModelAndView Exam_Type_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
				RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Exam_Type_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String userid = session1.getAttribute("userId").toString();
			String username = session1.getAttribute("username").toString();
			try {

				int app = session.createQuery(
						"update CC_TB_EXAM_TYPE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				tx.commit();
				session.close();
				if (app > 0) {
					System.err.println("check delete" + (app > 0));
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
			return new ModelAndView("redirect:Exam_Type_Mstr_Url");
		}
	}
