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
import com.AyushEdu.Core_Directory.Marital_Status_CD_DAO;
import com.AyushEdu.Models.LMS_Master.TB_LMS_MARITAL_STATUS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Marital_StatusDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Marital_Status_Controller {

	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	 ValidationController validation;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Marital_StatusDao MSdao;
	
	@Autowired
	Marital_Status_CD_DAO MS_dirdao;
	
	//==========================================OPEN PAGE GENDER========================================== 
	
			@RequestMapping(value = "/Marital_Status_Url", method = RequestMethod.GET)
			public ModelAndView Marital_Status_Url(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				try {
				if(request.getHeader("Referer") == null ) { 
			//		 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Marital_Status_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				 Mmap.put("msg", msg);
				 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
				return new ModelAndView("Marital_Status_Tiles");
			}
			
			//==========================================SAVE SYSTEM NAME========================================== 	

			
			@PostMapping(value = "/marital_statusAction")
			public ModelAndView marital_statusAction(@Validated @ModelAttribute("marital_statusCMD") TB_LMS_MARITAL_STATUS_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) {

				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Marital_Status_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String marital_status = request.getParameter("marital_status");
				String status = request.getParameter("status");

				if (marital_status == null || marital_status.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Marital Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (validation.maxlengthcheck100(marital_status) == false) {
					ra.addAttribute("msg","Marital Status "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (validation.isOnlyAlphabetDASH(marital_status) == false) {
					ra.addAttribute("msg","Marital Status  "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				String username = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  TB_LMS_MARITAL_STATUS_MSTR where upper(marital_status)=:marital_status and upper(status)=:status and id !=:id")
							.setParameter("marital_status", td.getMarital_status().toUpperCase())
							.setParameter("status", td.getStatus().toUpperCase())
							.setParameter("id", id).uniqueResult();
					int idd =0;
					if (id == 0) {
						td.setMarital_status(marital_status);
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
					MS_dirdao.Insert_Marital_Status_Data(idd);
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
				return new ModelAndView("redirect:Marital_Status_Url");
			}
			
			@PostMapping("/getFiltermarital_status_data")
			public @ResponseBody List<Map<String, Object>> getFiltermarital_status_data(int startPage, int pageLength,
					String Search, String orderColunm, String orderType, String marital_status, String status) {
 
				return MSdao.DataTablemarital_statusDataList1(startPage, pageLength, Search, orderColunm, orderType, marital_status,status);

			}

			@PostMapping("/getTotalmarital_status_dataCount")
			public @ResponseBody long getTotalmarital_status_dataCount(HttpSession sessionUserId, String Search, String marital_status) {
				
				return MSdao.DataTablemarital_statusDataTotalCount1(Search, marital_status);
			}
			
			@RequestMapping(value = "/delete_marital_statusUrl", method = RequestMethod.POST)
			public ModelAndView delete_marital_statusUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
				if(request.getHeader("Referer") == null ) { 
				//	 session1.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = session1.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Marital_Status_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update TB_LMS_MARITAL_STATUS_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
							.setParameter("id", id).setParameter("modified_by", username)
							.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

					tx.commit();
					MS_dirdao.Delete_Marital_Status_Data(id);  
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
				return new ModelAndView("redirect:Marital_Status_Url");
			}
			
			//-----edit

			@RequestMapping(value = "/Edit_marital_statusUrl", method = RequestMethod.POST)
			public ModelAndView Edit_marital_statusUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				if(request.getHeader("Referer") == null ) { 
			//		sessionEdit.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				String roleid1 = sessionEdit.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Marital_Status_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				TB_LMS_MARITAL_STATUS_MSTR marital_status_Details = MSdao.getmarital_statusByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
//				Mmap.put("system_name", common.getSystemList(sessionFactory));
				Mmap.put("marital_status_Details", marital_status_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				tx.commit();
				sessionHQL.close();
				return new ModelAndView("Editmarital_status_Tiles");
			}
			//edit action
			@RequestMapping(value = "/Edit_marital_statusAction", method = RequestMethod.POST)
			public ModelAndView Edit_marital_statusAction(@ModelAttribute("Edit_marital_statusCMD") TB_LMS_MARITAL_STATUS_MSTR rs,
					HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

				String username = session.getAttribute("username").toString();
				int id = Integer.parseInt(request.getParameter("id"));
				String marital_status = request.getParameter("marital_status").trim();
				String status = request.getParameter("status");
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				if (marital_status == null || marital_status.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Marital Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (validation.maxlengthcheck100(marital_status) == false) {
					ra.addAttribute("msg","Marital Status "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (validation.isOnlyAlphabetDASH(marital_status) == false) {
					ra.addAttribute("msg","Marital Status "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:Marital_Status_Url");
				}
				
				try {
					Query q0 = session1.createQuery(
							"select count(id) from TB_LMS_MARITAL_STATUS_MSTR where marital_status=:marital_status and status=:status and id !=:id");
					q0.setParameter("marital_status", marital_status);
					q0.setParameter("status", status);
					q0.setParameter("id", id);
					Long c = (Long) q0.uniqueResult();

					if (c == 0) {
						String hql = "update TB_LMS_MARITAL_STATUS_MSTR set marital_status=:marital_status,status=:status,modified_by=:modified_by,modified_date=:modified_date"
								+ " where id=:id";

						Query query = session1.createQuery(hql).setParameter("marital_status", marital_status).setParameter("status", status)
								.setParameter("modified_by", username).setParameter("modified_date", new Date())
								.setParameter("id", id);
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						//For Core Directory
						MS_dirdao.Update_Marital_Status_Data(id,marital_status,status,username,new Date());
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
				return new ModelAndView("redirect:Marital_Status_Url");
			}
}
