package com.AyushEdu.controller.Curriculum_Mstr;
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
import com.AyushEdu.Models.Curriculum_Mstr.EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.Add_Non_Lecture_Activities_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Add_Non_Lacture_Activities_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Add_Non_Lecture_Activities_Mstr_DAO Pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Teaching_learning_method_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Teaching_learning_method_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Teaching_learning_method_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Teaching_learning_method_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/Teaching_learning_methodAction")
		public ModelAndView Teaching_learning_methodAction(@Validated @ModelAttribute("Teaching_learning_methodCMD") EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Teaching_learning_method_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String teaching_learning_method = request.getParameter("teaching_learning_method");
			String status = request.getParameter("status");

			if (teaching_learning_method == null || teaching_learning_method.equals("")) {
				ra.addAttribute("msg", "Please Enter Teaching Learning Method.");
				return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(teaching_learning_method) == false) {
				ra.addAttribute("msg","Teaching Learning Method. "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
			}
			
			  if (validation.isOnlyAlphabetDASH(teaching_learning_method) == false) {
			  ra.addAttribute("msg","Teaching Learning Method. "+ validation.isOnlyAlphabetMSGDASH);
			  return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url"); 
			  }
			  if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
				}
				if (status == "2"  || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
				}
			int id =Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR where upper(teaching_learning_method)=:teaching_learning_method and status=:status and id !=:id")
						.setParameter("teaching_learning_method", td.getTeaching_learning_method().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					td.setTeaching_learning_method(teaching_learning_method);
					td.setCreated_by(userid);
//					td.setCreated_role(role);
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
					td.setTeaching_learning_method(teaching_learning_method.trim());
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Pdao.updateTeaching_learning_method(td);
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
			
			return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
		}
		
		@PostMapping("/getFilterTeaching_learning_method_data")
		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String teaching_learning_method, String status) {
			return Pdao.DataTableTeaching_learning_methodDataList(startPage, pageLength, Search, orderColunm, orderType, teaching_learning_method,status);
		}

		@PostMapping("/getTotalTeaching_learning_method_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String teaching_learning_method,String status) {
			return Pdao.DataTableTeaching_learning_methodDataTotalCount(Search, teaching_learning_method,status);
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_Teaching_learning_method_Mstr_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Teaching_learning_method_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "teaching_learning_method", required = false) String teaching_learning_method,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
					try {
//						SECURITY -- RIDDHI 	
						if(request.getHeader("Referer") == null ) { 
//							sessionEdit.invalidate();
							 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
						String roleid1 = sessionEdit.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Teaching_learning_method_Mstr_Url", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR Teaching_learning_method_Details = Pdao.getTeaching_learning_methodByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("teaching_learning_method", teaching_learning_method);
					Mmap.put("status", status);
					Mmap.put("Teaching_learning_method_Details", Teaching_learning_method_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("Teaching_learning_methodinfo", Pdao.getTeaching_learning_methodByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
					return new ModelAndView("Teaching_learning_method_Tiles");
				}
				
				@RequestMapping(value = "/Teaching_learning_method_Mstr_Delete_Url", method = RequestMethod.POST)
				public ModelAndView Teaching_learning_method_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
//					SECURITY -- RIDDHI 	
					if(request.getHeader("Referer") == null ) { 
//						session1.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Teaching_learning_method_Mstr_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"update EDU_CC_TB_NON_LEC_ACTIVITIES_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", id).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();

						
						tx.commit();
						session.close();
						if (app > 0) {
							System.err.println("check delete"+(app > 0));
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
					return new ModelAndView("redirect:Teaching_learning_method_Mstr_Url");
				}
}
