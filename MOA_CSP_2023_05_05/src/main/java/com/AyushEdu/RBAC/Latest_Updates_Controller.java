package com.AyushEdu.RBAC;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.AyushEdu.Models.TB_LATESTUPDATES;
import com.AyushEdu.dao.Latest_UpdatesDao;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Latest_Updates_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;

	@Autowired
	Latest_UpdatesDao hdao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	
	private Date date;
	// ==========================================OPEN PAGE Latest_UpdatesUrl==========================================//

	@RequestMapping(value = "/Latest_UpdatesUrl", method = RequestMethod.GET)
		
		 	public ModelAndView Latest_UpdatesUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		if(request.getHeader("Referer") == null ) { 
//			//		 session.invalidate();
//					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//						
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Latest_UpdatesUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
//			
			  Mmap.put("msg", msg);
			  Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			return new ModelAndView("Latest_UpdatesTiles");

		}

	//==========================================SAVE Latest_UpdatesUrl ========================================== 	

	
	@PostMapping(value = "/Latest_UpdatesUrlaction")
	public ModelAndView Latest_UpdatesUrlaction(@Validated @ModelAttribute("updatecmd") TB_LATESTUPDATES td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,  String username ) throws ParseException  {
		
		
//		if(request.getHeader("Referer") == null ) { 
//			//		 session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//						
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Latest_UpdatesUrl", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
		
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		String updates = request.getParameter("updates");
		String url = request.getParameter("url");
		String from_date = request.getParameter("from_date");
		String to_date = request.getParameter("to_date");
		String ayushPort = request.getParameter("checkbox-1");
		String nchPort = request.getParameter("checkbox-2");
		String ncismPort = request.getParameter("checkbox-3");
		String status = request.getParameter("status");
		
		if(ayushPort == null && ncismPort == null && nchPort == null) {
			ra.addAttribute("msg", "Please Select atleast one Port.");
			return new ModelAndView("redirect:Latest_UpdatesUrl");
		}
		
		String updatesfor = "";
		if(ayushPort != null) {
			if(updatesfor.equals(""))updatesfor +=ayushPort;
			else updatesfor +=","+ ayushPort;
			
		}
//		if(ayushPort == null &&  ncismPort == null && nchPort != null) {
//			updatesfor +=""+ nchPort;
//		}
//		if(ayushPort == null && ncismPort != null && nchPort == null) {
//			updatesfor += ""+ncismPort;
//		}
		if(nchPort != null) {
			if(updatesfor.equals(""))updatesfor +=nchPort;
			else updatesfor +=","+ nchPort;
			
		}
		
		if(ncismPort != null) {
			
			if(updatesfor.equals(""))updatesfor +=ncismPort;
			else updatesfor +=","+ ncismPort;
		}
		
//		if(  ncismPort == null && nchPort == null && ayushPort != null) {
//			updatesfor += ayushPort;
//		}
		if (updates == null || updates.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter updates.");
			return new ModelAndView("redirect:Latest_UpdatesUrl");
		}
//		if (validation.isOnlyAlphabetDASH(updates) == false) {
//			ra.addAttribute("msg","updates "+ validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:Latest_UpdatesUrl");
//		}
		if (url == null || url.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter URL.");
			return new ModelAndView("redirect:Latest_UpdatesUrl");
		}
//		if (validation.maxlengthcheck50(updates) == false) {
//			ra.addAttribute("msg","updates "+ validation.MaxlengthcheckMSG50);
//			return new ModelAndView("redirect:Latest_UpdatesUrl");
//		}
	
		if (from_date == null || from_date.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select From Date.");
			return new ModelAndView("redirect:Latest_UpdatesUrl");
		}
		if (to_date == null || to_date.trim().equals("0")) {
			ra.addAttribute("msg", "Please Selcet To Date.");
			return new ModelAndView("redirect:Latest_UpdatesUrl");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

//		try {
			Long c = (Long) sessionHQL.createQuery("select count(id) from  TB_LATESTUPDATES where upper(updates)=:updates and url=:url and from_date=:from_date and to_date=:to_date and updates_for=:updates_for and status=:status and id!=:id ")
					.setParameter("updates",updates.toUpperCase())
					.setParameter("url",url.toUpperCase())
					.setParameter("from_date",formate.parse(from_date))
					.setParameter("to_date",formate.parse(to_date))
					.setParameter("updates_for",updatesfor)
					.setParameter("status",Integer.parseInt(status))
					.setParameter("id",id)
					.uniqueResult();
		if(id == 0) {
			if (c == 0) {
				td.setUpdates(updates);
				td.setUrl(url);
				td.setFrom_date(formate.parse(from_date));
				td.setTo_date(formate.parse(to_date));
				td.setUpdates_for(updatesfor);
		
				td.setStatus(1);
				
				td.setCreated_by(username);
				td.setCreated_date(date);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
			}else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
			tx.commit();

			
//			 } catch (RuntimeException e) { 
//				 try {
//					 ra.addAttribute("msg", "roll back transaction"); 
//				 }catch (RuntimeException rbe) { 
//					 ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe); 
//				}throw e; 
//				
//			 } finally { 
//				 if (sessionHQL != null) { 
//					 sessionHQL.close(); 
//					 } 
//			}

			sessionHQL.close();
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}

/////////////////////////////////for DATA TABLE LIST AND COUNT /////////////////////////////////////////////
@PostMapping("/getFilterLatest_Updates_data")

	public @ResponseBody List<Map<String, Object>> DataTableLatest_UpdatesDataList(int startPage, int pageLength, String Search,
		String orderColunm, String orderType,String updates,String from_date,String to_date,String url,String ayu_port,String nch_port,String ncism_port,String status)
{
		System.err.println("--ccc----updates----aa-----"+updates);
		return hdao.DataTableLatest_UpdatesDataList(startPage, pageLength, Search, orderColunm, orderType,updates,from_date,to_date, url,ayu_port,nch_port,ncism_port,status);

}

@PostMapping("/getTotalLatest_Updates_dataCount")
public @ResponseBody long DataTableLatest_UpdatesDataTotalCount(HttpSession sessionUserId, String Search, String updates,String from_date,String to_date,String url,String ayu_port,String nch_port,String ncism_port,String status) {
		System.err.println("--ccc----updates---------"+updates);
		return hdao.DataTableLatest_UpdatesDataTotalCount(Search, updates,from_date,to_date, url,ayu_port,nch_port,ncism_port,status);
	}

/////////////////////////////////EDIT Latest_Updates///////////////////////////////////////////
@RequestMapping(value = "/Edit_Latest_UpdatesUrl", method = RequestMethod.POST)
public ModelAndView Edit_Latest_UpdatesUrl(String id3, ModelMap Mmap,
		@RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request, HttpSession sessionEdit) {
	System.out.println("welcome=====================================");
//			Session sessionHQL = this.sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
	TB_LATESTUPDATES Latest_Updates_details = hdao.getsystemByid(Integer.parseInt(id3));
			Mmap.addAttribute("msg", msg);
			System.out.println("Latest_Updates_details "+Latest_Updates_details.getId());
			
			Mmap.put("Latest_Updates_details", Latest_Updates_details);
			Mmap.put("Latest_Updatesinfo", hdao.getsystemByid(Integer.parseInt(id3)));
			Mmap.put("updateid",id3);
//			tx.commit();
//			sessionHQL.close();
		
			return new ModelAndView("editLatest_UpdatesTiles");
}

@RequestMapping(value = "/edit_Latest_Updates_action", method = RequestMethod.POST)
public ModelAndView edit_Latest_Updates_action(@ModelAttribute("edit_Latest_UpdatesCMD") TB_LATESTUPDATES rs,
		HttpServletRequest request, ModelMap model, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
{
//	
//	if(request.getHeader("Referer") == null ) { 
//		//		 session.invalidate();
//		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//					
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Latest_UpdatesUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
	
	
	
	DateFormat formate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//	SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
	String username = session.getAttribute("username").toString();
	int id = Integer.parseInt(request.getParameter("updateid")); 
	String updates = request.getParameter("updates");
	String from_date = request.getParameter("from_date");
	String to_date = request.getParameter("to_date");
	String url = request.getParameter("url");
	String ayushPort = request.getParameter("checkbox-1");
	String nchPort = request.getParameter("checkbox-2");
	String ncismPort = request.getParameter("checkbox-3");
	String status = request.getParameter("status");
	
	System.err.println(ayushPort+"-"+nchPort+"-"+ncismPort);
	
	if(ayushPort == null && ncismPort == null && nchPort == null) {
		ra.addAttribute("msg", "Please Select atleast one Port.");
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}
	
	String updatesfor = "";
	if(ayushPort != null) {
		if(updatesfor.equals("")) {
			updatesfor += ayushPort;
		}else {
			updatesfor += ","+ayushPort;
		}
		
	}
	if(nchPort != null) {
		if(updatesfor.equals("")) {
			updatesfor += nchPort;
		}else {
			updatesfor +=","+ nchPort;
		}
	}
	if(ncismPort != null) {
		if(updatesfor.equals("")) {
			updatesfor += ncismPort;
		}else {
			updatesfor += ","+ncismPort;
		}
	}
	
	System.err.println(updatesfor);

	Session session1 = this.sessionFactory.openSession();
	Transaction tx = session1.beginTransaction();
	
	if (updates == null || updates.trim().equals("")) {
		ra.addAttribute("msg", "Please Enter updates.");
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}
//	if (validation.maxlengthcheck50(updates) == false) {
//		ra.addAttribute("msg","updates "+ validation.MaxlengthcheckMSG50);
//		return new ModelAndView("redirect:Latest_UpdatesUrl");
//	}
//	if (validation.isOnlyAlphabetDASH(updates) == false) {
//		ra.addAttribute("msg","updates "+ validation.isOnlyAlphabetMSGDASH);
//		return new ModelAndView("redirect:Latest_UpdatesUrl");
//	}
	
	if (updates == null || updates.trim().equals("0")) {
		ra.addAttribute("msg", "Please enter updates.");
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}
	if (from_date == null || from_date.trim().equals("0")) {
		ra.addAttribute("msg", "Please Select From Date.");
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}
	if (to_date == null || to_date.trim().equals("0")) {
		ra.addAttribute("msg", "Please Selcet To Date.");
		return new ModelAndView("redirect:Latest_UpdatesUrl");
	}
//	try {
		Query q0 = session1.createQuery(
				"select count(id) from TB_LATESTUPDATES where updates=:updates and url=:url and  id !=:id");
		q0.setParameter("updates", updates);
		q0.setParameter("url", url);
		
		q0.setParameter("id", id);

		Long c = (Long) q0.uniqueResult();
		
		if (c == 0) {
			String hql = "update TB_LATESTUPDATES set updates=:updates ,from_date=:from_date,to_date=:to_date,url=:url,updates_for=:updates_for,status=:status, modified_by=:modified_by , modified_date=:modified_date where id=:id";

			Query query = session1.createQuery(hql).setParameter("updates", updates)
					.setParameter("from_date",formate.parse(from_date))
					.setParameter("to_date", formate.parse(to_date))
					.setParameter("url", url)
					.setParameter("updates_for", updatesfor)
					.setParameter("modified_by", username)
					.setParameter("id", id)
					.setParameter("status", 1)
					.setParameter("modified_date", new Date());
				
			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
			
			if (msg.equals("1")) {
				ra.addAttribute("msg", "Data Updated Successfully.");
			} else {
				ra.addAttribute("msg", "Data Not Updated.");
			}
		} else {
			ra.addAttribute("msg", "Data already Exist.");
		}
//	}
//	
//	finally {
//		if (session1 != null) {
//			session1.close();
//		}
//	}
	
	return new ModelAndView("redirect:Latest_UpdatesUrl");
}
}


// -------------------------SEARCH Battalion  -------------------------------------//

@RequestMapping(value = "/admin/getSearch_Latest_Updates", method = RequestMethod.POST)
public ModelAndView getSearch_Latest_Updates(ModelMap Mmap,HttpSession session,HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "updates", required = false) String updates ,String updates1)  {
	
	
			Mmap.put("updates", updates1);
	

			return new ModelAndView("Latest_UpdatesTiles","SystemCMD2",new TB_LATESTUPDATES());
	   
}
	
////////////////////////////////delete updates//////////////////////////////////////


@RequestMapping(value = "/delete_Latest_Updates_Action", method = RequestMethod.POST)
public ModelAndView delete_Latest_Updates_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
		HttpServletRequest request, ModelMap model, HttpSession session1) {

	if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Latest_UpdatesUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		
	List<String> liststr = new ArrayList<String>();

	Session session = this.sessionFactory.openSession();
	Transaction tx = session.beginTransaction();

	String username = session1.getAttribute("username").toString();
//	try {

		int app = session.createQuery(
				"update TB_LATESTUPDATES set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
				.setParameter("id", id).setParameter("modified_by", username)
				.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

		
		tx.commit();
		session.close();
		if (app > 0) {
			liststr.add("Data Deleted Successfully.");
		} else {
			liststr.add("Data not Deleted.");
		}
		ra.addAttribute("msg", liststr.get(0));
//	} 
//	catch (Exception e) {
//		e.printStackTrace();
//		liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//		ra.addAttribute("msg", liststr.get(0));
		
//	}
		
	return new ModelAndView("redirect:Latest_UpdatesUrl");
}
}
