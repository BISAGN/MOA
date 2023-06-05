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
import com.AyushEdu.Core_Directory.Degree_Category_Master_CD_Dao;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Degree_Category_MasterDao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_Category_Master_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Degree_Category_MasterDao hdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	

	@Autowired
	Degree_Category_Master_CD_Dao  DM_dirdao;
	
	private Date date;
	// ==========================================OPEN PAGE Degree_Category_MasterUrl==========================================//

		@RequestMapping(value = "/Degree_Category_MasterUrl", method = RequestMethod.GET)
			
			 	public ModelAndView Degree_Category_MasterUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Degree_Category_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
				  Mmap.put("msg", msg);
				  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				return new ModelAndView("Degree_Category_MasterTiles");

			}

		//==========================================SAVE Degree_Category_MasterUrl ========================================== 	

		
		@PostMapping(value = "/Degree_Category_masterUrlaction")
		public ModelAndView Degree_Category_masterUrlaction(@Validated @ModelAttribute("hms3") EDU_LMS_DEGREE_CATE_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra,  String username  )  {
			
			
			
			
			
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Degree_Category_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			

			
			
			System.err.println("hellllllllloooooooooooo");
			String Degree_Category = request.getParameter("Degree_Category");
			String status = request.getParameter("status");
	//	


			if (Degree_Category == null || Degree_Category.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Degree_Category.");
				return new ModelAndView("redirect:Degree_Category_MasterUrl");
			}
			
			
			if (validation.isOnlyAlphabetDASH(Degree_Category) == false) {
				ra.addAttribute("msg","Degree Category "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Degree_Category_MasterUrl");
			}
			
			if (Degree_Category == null || Degree_Category.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Degree Category.");
				return new ModelAndView("redirect:Degree_Category_MasterUrl");
			}
			if (validation.maxlengthcheck50(Degree_Category) == false) {
				ra.addAttribute("msg","Degree Category "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Degree_Category_MasterUrl");
			}
			
			
			
			//int id = td.getId() > 0 ? td.getId() : 0;

			

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			//try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_DEGREE_CATE_MSTR where upper(degree_cate)=:degree_cate and status=:status ")
						//.setParameter("id", td.getId())
						.setParameter("degree_cate",Degree_Category.toUpperCase())
						.setParameter("status",Integer.parseInt(status))
					    //.setParameter("university_name", td.getUniversity_name().toUpperCase())
						.uniqueResult();
			
				int idd =0;	
				if (c == 0) {
					td.setDegree_cate(Degree_Category);
					td.setStatus(1);
					
					td.setCreated_by(username);
					td.setCreated_date(date);
					td.setModified_by(username);
					td.setModified_date(date);
//					if (c == 0) {
					idd = (int)sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
//					} 
//					
				}else {
				ra.addAttribute("msg", "Data already Exist.");
		}

				tx.commit();
				//For Core Directory
				DM_dirdao.Insert_Degree_Cate_Mstr_Data(idd);
				/*
				 * } catch (RuntimeException e) { try {
				 * 
				 * ra.addAttribute("msg", "roll back transaction"); } catch (RuntimeException
				 * rbe) { ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); }
				 * throw e; } finally { if (sessionHQL != null) { sessionHQL.close(); } }
				 */

				sessionHQL.close();
			return new ModelAndView("redirect:Degree_Category_MasterUrl");
		}

	/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
	@PostMapping("/getFilterDegree_Category_Master_data")

		public @ResponseBody List<Map<String, Object>> DataTableDegree_Category_MasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String Degree_Category,String status)
	{
			System.err.println("--ccc----Degree_Category----aa-----"+Degree_Category);
			return hdao.DataTableDegree_Category_MasterDataList(startPage, pageLength, Search, orderColunm, orderType,Degree_Category,status);

	}

	@PostMapping("/getTotalDegree_Category_Master_dataCount")
	public @ResponseBody long DataTableDegree_Category_MasterDataTotalCount(HttpSession sessionUserId, String Search, String Degree_Category,String status) {
			System.err.println("--ccc----Degree_Category---------"+Degree_Category);
			return hdao.DataTableDegree_Category_MasterDataTotalCount(Search, Degree_Category,status);
		}

	/////////////////////////////////EDIT Degree_Category_Master///////////////////////////////////////////
	@RequestMapping(value = "/Edit_Degree_Category_MasterUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Degree_Category_MasterUrl(String id3, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {
		System.out.println("welcome=====================================");
//				Session sessionHQL = this.sessionFactory.openSession();
//				Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_DEGREE_CATE_MSTR Degree_Category_Master_details = hdao.getsystemByid(Integer.parseInt(id3));
				Mmap.addAttribute("msg", msg);
				 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				System.out.println("Degree_Category_Master_details "+Degree_Category_Master_details.getId());
				Mmap.put("Degree_Category_Master_details", Degree_Category_Master_details);
				Mmap.put("Degree_Category_Masterinfo", hdao.getsystemByid(Integer.parseInt(id3)));
				Mmap.put("updateid",id3);
//				tx.commit();
//				sessionHQL.close();
			
				return new ModelAndView("editDegree_Category_MasterTiles");
	}

	@RequestMapping(value = "/edit_Degree_Category_Master_action", method = RequestMethod.POST)
	public ModelAndView edit_Degree_Category_Master_action(@ModelAttribute("edit_Degree_Category_MasterCMD") EDU_LMS_DEGREE_CATE_MSTR rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
	{
		
		
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Degree_Category_MasterUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		
		
		
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("updateid")); 
		String Degree_Category = request.getParameter("Degree_Category");
		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (Degree_Category == null || Degree_Category.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Degree Category.");
			return new ModelAndView("redirect:Degree_Category_MasterUrl");
		}
		if (validation.maxlengthcheck50(Degree_Category) == false) {
			ra.addAttribute("msg","Degree Category "+ validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Degree_Category_MasterUrl");
		}
		if (validation.isOnlyAlphabetDASH(Degree_Category) == false) {
			ra.addAttribute("msg","Degree Category "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Degree_Category_MasterUrl");
		}
		
		if (Degree_Category == null || Degree_Category.trim().equals("0")) {
			ra.addAttribute("msg", "Please enter Degree Category.");
			return new ModelAndView("redirect:Degree_Category_MasterUrl");
		}
		
		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_DEGREE_CATE_MSTR where degree_cate=:degree_cate and status=:status and id !=:id");
			q0.setParameter("degree_cate", msg);
			
			q0.setParameter("degree_cate", Degree_Category);
			q0.setParameter("status",Integer.parseInt( status));
			
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();
			
			if (c == 0) {
				String hql = "update EDU_LMS_DEGREE_CATE_MSTR set degree_cate=:degree_cate ,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

				Query query = session1.createQuery(hql).setParameter("degree_cate", Degree_Category)
						.setParameter("modified_by", username)
						.setParameter("id", id)
						.setParameter("status", Integer.parseInt( status))
						.setParameter("modified_date", new Date());
					
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				DM_dirdao.Update_Degree_Cate_Mstr_Data( id,  Degree_Category,  Integer.parseInt(status),  username,  new Date());
				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
		
		finally {
			if (session1 != null) {
				session1.close();
			}
		}
		
		return new ModelAndView("redirect:Degree_Category_MasterUrl");
	}
	}


	// -------------------------SEARCH Battalion  -------------------------------------//

	@RequestMapping(value = "/admin/getSearch_Degree_Category_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_Degree_Category_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "Degree_Category", required = false) String Degree_Category ,String Degree_Category1)  {
		
		if(request.getHeader("Referer") == null ) { 
			//	session1.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Degree_Category_MasterUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				Mmap.put("Degree_Category", Degree_Category1);
		

				return new ModelAndView("Degree_Category_MasterTiles","SystemCMD2",new EDU_LMS_DEGREE_CATE_MSTR());
		   
	}
		
	////////////////////////////////delete Degree_Category//////////////////////////////////////

//	@RequestMapping(value = "/delete_Degree_Category_Action", method = RequestMethod.POST)
//	public ModelAndView delete_Degree_Category_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
//			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		
//				List<String> liststr = new ArrayList<String>();
//			
//				Session session = this.sessionFactory.openSession();
//				Transaction tx = session.beginTransaction();
//			
//				String username = session1.getAttribute("username").toString();
//				
//
//				EDU_LMS_DEGREE_CATE_MSTR INF = (EDU_LMS_DEGREE_CATE_MSTR) session.get(EDU_LMS_DEGREE_CATE_MSTR.class, id);
//
//				session.delete(INF);
//				session.flush();
//				session.clear();
//				
//				tx.commit();
//				session.close();
////				if (app > 0) {
//					liststr.add("Data Deleted Successfully.");
////				} else {
////					liststr.add("Data not Deleted.");
////				}
//				ra.addAttribute("msg", liststr.get(0));
//			/*} catch (Exception e) {
//				e.printStackTrace();
//				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//				ra.addAttribute("msg", liststr.get(0));
//				
//			}*/
//			return new ModelAndView("redirect:Degree_Category_MasterUrl");
//		}
	@RequestMapping(value = "/delete_Degree_Category_Action", method = RequestMethod.POST)
	public ModelAndView delete_Degree_Category_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Degree_Category_MasterUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
//		try {
  
			int app = session.createQuery(
					"update EDU_LMS_DEGREE_CATE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Degree_Cate_Mstr_Data(id); 
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//			ra.addAttribute("msg", liststr.get(0));
			
//		}
			
		return new ModelAndView("redirect:Degree_Category_MasterUrl");
	}

	}
		




