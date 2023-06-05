package com.AyushEdu.controller.helpdeskINQ;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.helpdeskINQ.HD_INQUIRY_CATEGORY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.helpdeskINQ.HD_Inquiry_CategoryDao;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Inquiry_Category_Master {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private HD_Inquiry_CategoryDao Icdao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/Inquiry_Category_Master_Url", method = RequestMethod.GET)
	public ModelAndView Inquiry_Category_Master_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			
//			//SECURITY - POOJA
//			if(request.getHeader("Referer") == null ) { 
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Inquiry_Category_Master_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Inquiry_Category_Master_Tiles");
	}
	
	//==========================================Save Inquiry Category Master========================================== 	

	
			@PostMapping(value = "/Inq_Cat_Action")
			public ModelAndView Inq_Cat_Action(@Validated @ModelAttribute("Inq_Cat_CMD") HD_INQUIRY_CATEGORY_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, 
					RedirectAttributes ra) {
				
				//SECURITY - POOJA
//				if(request.getHeader("Referer") == null ) { 
//					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//				
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Inquiry_Category_Master_Url", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
				
				String inq_cat = request.getParameter("inq_cat");
				String type = request.getParameter("type");
				String status = request.getParameter("status");
				 Session sessiont = sessionFactory.openSession();
				 String userid = session.getAttribute("userId_for_jnlp").toString();
				 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
//				 List<UserLogin> institute_idList = q1.list();
//				 sessiont.close();
//				 int institute_id = institute_idList.get(0).getInstitute_id(); 
				 
				if (inq_cat == null || inq_cat.equals("")) {
					ra.addAttribute("msg", "Please Enter Inquiry Category.");
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");
				}
				
				if (validation.maxlengthcheck100(inq_cat) == false) {
					ra.addAttribute("msg","Inquiry Category "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");
				}
				
				if (validation.isOnlyAlphabetNumber(inq_cat) == false) {
					ra.addAttribute("msg","Inquiry Category "+ validation.isOnlyAlphabetNumberMSG);
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");
				}
				
				if (type == null || type.trim().equals("")) {
					ra.addAttribute("msg", "Please Select Type.");
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");
				}
				if (validation.maxlengthcheck100( request.getParameter("type")) == false) {
					ra.addAttribute("msg","Type "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");	
				}
				
				if (status == null || status.trim().equals("")) {
					ra.addAttribute("msg", "Please select Status.");
					return new ModelAndView("redirect:Inquiry_Category_Master_Url");
				}

				int id =Integer.parseInt(request.getParameter("id"));
				Date date = new Date();
				System.out.println(userid);			
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(*) from  HD_INQUIRY_CATEGORY_MSTR where upper(inq_cat)=:inq_cat and type=:type and status=:status and id !=:id")
							.setParameter("inq_cat", td.getInq_cat().toUpperCase())
							.setParameter("type", Integer.parseInt(type))
							.setParameter("status", td.getStatus())
							.setParameter("id", id).uniqueResult();
					int idd =0;
					if (id == 0) {
						td.setInq_cat(inq_cat);
						td.setType(Integer.parseInt(type));
						td.setCreated_by(userid);
//						td.setCreated_role(role);
						td.setCreated_dt(date);
						if (c == 0) {
							idd = (int)sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					} else {
						td.setInq_cat(inq_cat);
						td.setInq_cat(type);
						td.setModified_by(userid);
						td.setModified_dt(date);
						if (c == 0) {
							td.setId(id);
							String msg = Icdao.updateInq_Cat(td);
//							DM_dirdao.Update_classroom_Mstr_Data( td.getId(), td.getInq_cat(), td.getType(), td.getStatus(),  userid,  new Date());
//							if (msg == "Data Updated Successfully") {
//								model.put("msg", msg);
//								model.put("Nmsg", "0");
//							} else {
//								model.put("msg", msg);
//								model.put("Nmsg", "1");
//							}
							ra.addAttribute("msg", "Data Updated Successfully.");
						} else {
//							model.put("msg", "Data already Exist");
//							model.put("Nmsg", "1");
							ra.addAttribute("msg", "Data already Exist.");
						}
					}

					tx.commit();
					//For Core Directory
//					DM_dirdao.Insert_classroom_Mstr_Data(idd);
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
				
				return new ModelAndView("redirect:Inquiry_Category_Master_Url");
			}
	
	//----------------------------------DATA TABLE----------------------------------------
			@PostMapping("/getFilterInq_Catdata")
			public @ResponseBody List<Map<String, Object>> getFilterInq_Catdata(int startPage, int pageLength,
					String Search, String orderColunm, String orderType, String inq_cat,String type ,HttpSession session) {
						
				Session sessiont = sessionFactory.openSession();
				String userid = session.getAttribute("userId_for_jnlp").toString();
				return Icdao.DataTableInq_CatDataList(startPage, pageLength, Search, orderColunm, orderType, inq_cat,type);

			}

			@PostMapping("/getTotalInq_Cat_dataCount")
			public @ResponseBody long getTotalInq_Cat_dataCount(HttpSession sessionUserId, String Search, String inq_cat,String type,HttpSession session) {
				Session sessiont = sessionFactory.openSession();
				String userid = session.getAttribute("userId_for_jnlp").toString();
				return Icdao.DataTableInq_CatDataTotalCount(Search, inq_cat,type );
				
			}
			
	//------------------------------------EDIT & DELETE-------------------------------
			
			@RequestMapping(value = "/Edit_Inquiry_Category_Mstr_Url", method = RequestMethod.POST)
			public ModelAndView Edit_Inquiry_Category_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "inq_cat", required = false) String inq_cat,
					@RequestParam(value = "type", required = false) String type,
					@RequestParam(value = "status", required = false) String status,
					HttpServletRequest request, HttpSession sessionEdit) {
				
				//SECURITY - POOJA
//				if(request.getHeader("Referer") == null ) { 
//					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//				
//				String roleid1 = sessionEdit.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
//				
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				HD_INQUIRY_CATEGORY_MSTR Inq_Cat_Details = Icdao.getInq_CatByid(Integer.parseInt(updateid));
				Mmap.addAttribute("msg", msg);
				Mmap.put("inq_cat", inq_cat);
				Mmap.put("type", type);
				Mmap.put("status", status);
				Mmap.put("Inq_Cat_Details", Inq_Cat_Details);
				Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
				Mmap.put("Inq_Catinfo", Icdao.getInq_CatByid(Integer.parseInt(updateid)));
				
				tx.commit();
				sessionHQL.close();

				return new ModelAndView("Inquiry_Category_Master_Tiles");
			}
			
			@RequestMapping(value = "/Inquiry_Category_Mstr_Delete_Url", method = RequestMethod.POST)
			public ModelAndView Inquiry_Category_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
				
				//SECURITY - POOJA
//				if(request.getHeader("Referer") == null ) { 
//					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//				
//				String roleid1 = session1.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}

				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
					
					
		  
					int app = session.createQuery(
							"delete from HD_INQUIRY_CATEGORY_MSTR where id=:id")
							.setParameter("id", id).executeUpdate();
					
					tx.commit();
					//For Core Directory
//					DM_dirdao.Delete_classroom_Mstr_Data(id); 
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
				return new ModelAndView("redirect:Inquiry_Category_Master_Url");
			}
			
}
