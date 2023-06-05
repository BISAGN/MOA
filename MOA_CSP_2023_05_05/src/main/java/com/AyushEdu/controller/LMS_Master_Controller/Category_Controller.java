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
import com.AyushEdu.Core_Directory.Category_CD_Dao;
import com.AyushEdu.Core_Directory.Term_CD_Dao;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.CategoryDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Category_Controller {

	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	CategoryDao Cdao;
	
	@Autowired
	Category_CD_Dao  DM_dirdao;
	
	//==========================================OPEN PAGE GENDER========================================== 
	
		@RequestMapping(value = "/Category_Url", method = RequestMethod.GET)
		public ModelAndView Category_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Category_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			 Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			return new ModelAndView("Category_Tiles");
		}
		
		//==========================================SAVE SYSTEM NAME========================================== 	

		
			@PostMapping(value = "/categoryAction")
			public ModelAndView categoryAction(@Validated @ModelAttribute("categoryCMD") EDU_LMS_CATEGORY_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) {

				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
					String roleid1 = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Category_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				//String roleid1 = session.getAttribute("roleid").toString();
				 //Boolean val = roledao.ScreenRedirect("Category_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String category = request.getParameter("category");
				String status = request.getParameter("status");

				if (category == null || category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Category.");
					return new ModelAndView("redirect:Category_Url");
				}
				if (validation.maxlengthcheck100(category) == false) {
					ra.addAttribute("msg","Category "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Category_Url");
				}
				if (validation.isOnlyAlphabetDASH(category) == false) {
					ra.addAttribute("msg","Category "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Category_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Status.");
					return new ModelAndView("redirect:Category_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Category_Url");
				}

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				String username = principal.getName();
//				String system_name = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_LMS_CATEGORY_MSTR where upper(category)=:category and upper(status)=:status and id !=:id")
							.setParameter("category", td.getCategory().toUpperCase())
							.setParameter("status", td.getStatus().toUpperCase())
							.setParameter("id", id).uniqueResult();
					int idd =0;
					if (id == 0) {
						td.setCategory(category);
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
					DM_dirdao.Insert_Category_Mstr_Data(idd);
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
				return new ModelAndView("redirect:Category_Url");
			}
			
			@PostMapping("/getFilterCategory_data")
			public @ResponseBody List<Map<String, Object>> getFilterCategory_data(int startPage, int pageLength,
					String Search, String orderColunm, String orderType, String category, String status) {
 
				return Cdao.DataTableCategoryDataList1(startPage, pageLength, Search, orderColunm, orderType, category,status);

			}

			@PostMapping("/getTotalCategory_dataCount")
			public @ResponseBody long getTotalCategory_dataCount(HttpSession sessionUserId, String Search, String category) {
				
				return Cdao.DataTableCategoryDataTotalCount1(Search, category);
			}
			
			@RequestMapping(value = "/delete_categoryUrl", method = RequestMethod.POST)
			public ModelAndView delete_categoryUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {

				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update EDU_LMS_CATEGORY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

					
					tx.commit();
					//For Core Directory
					DM_dirdao.Delete_Category_Mstr_Data(id);  
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
				return new ModelAndView("redirect:Category_Url");
			}
			
			//-----edit

			@RequestMapping(value = "/Edit_categoryUrl", method = RequestMethod.POST)
			public ModelAndView Edit_categoryUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				String role = sessionEdit.getAttribute("role").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_LMS_CATEGORY_MSTR Category_Details = Cdao.getCategoryByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
				Mmap.put("system_name", common.getSystemList(sessionFactory,role));
				Mmap.put("Category_Details", Category_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				tx.commit();
				sessionHQL.close();
				return new ModelAndView("EditCategory_Tiles");
			}
			//edit action
			@RequestMapping(value = "/Edit_categoryAction", method = RequestMethod.POST)
			public ModelAndView Edit_categoryAction(@ModelAttribute("Edit_categoryCMD") EDU_LMS_CATEGORY_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
				
				
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Category_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
				

				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String category = request.getParameter("category").trim();
				String status = request.getParameter("status");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (category == null || category.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Category.");
					return new ModelAndView("redirect:Category_Url");
				}
				if (validation.maxlengthcheck100(category) == false) {
					ra.addAttribute("msg","Category "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Category_Url");
				}
				if (validation.isOnlyAlphabetDASH(category) == false) {
					ra.addAttribute("msg","Category "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Category_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Status.");
					return new ModelAndView("redirect:Category_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:module_url");
				}
				
				try {
					Query q0 = session1.createQuery(
							"select count(id) from EDU_LMS_CATEGORY_MSTR where category=:category and status=:status and id !=:id");
					q0.setParameter("category", category);
					q0.setParameter("status", status);
					q0.setParameter("id", id);
					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						String hql = "update EDU_LMS_CATEGORY_MSTR set category=:category,status=:status,modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";

						Query query = session1.createQuery(hql).setParameter("category", category).setParameter("status", status)
								.setParameter("modified_by", username).setParameter("modified_date", new Date())
								.setParameter("id", id);
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						//For Core Directory
						DM_dirdao.Update_Category_Mstr_Data( id,  category,  status,  username,  new Date());
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
				return new ModelAndView("redirect:Category_Url");
			}
}
