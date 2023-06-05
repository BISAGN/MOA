package com.AyushEdu.controller.helpdeskINQ;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.helpdeskINQ.HD_Inq_Helpdesk_Child;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.helpdeskINQ.HD_Inquiry_CategoryDao;
import com.AyushEdu.dao.helpdeskINQ.HD_Inquiry_ReportDao;
@Controller
@RequestMapping(value = { "admin", "/", "user" })	

public class HelpDesk_Report {
	

//	public void setSessionFactory(SessionFactory sf) {
//		this.sessionFactory = sf;
//	}

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	
	@Autowired
	HD_Inquiry_CategoryDao ICD;
	
	@Autowired
	HD_Inquiry_ReportDao IRD;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "admin/Report_Link_Role_Mster_Url", method = RequestMethod.GET)
	public ModelAndView Report_Link_Role_Mster_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String staff_lvl = "";
		String role = session.getAttribute("role").toString();
//		String userId = session.getAttribute("userId").toString();
		
		if (role.equals("NCISM_ADMIN")) {
			staff_lvl = "NCISM";
		}
		else if (role.equals("NCH_ADMIN")) {
			staff_lvl = "NCH";
		}
		Mmap.put("getRoleFromStaff_lvl", common.getRoleFromStaff_lvl(sessionFactory, staff_lvl));
		
		Mmap.put("msg", msg);
		Mmap.addAttribute("getInq_CatList", common.getInq_CatList());
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		return new ModelAndView("Report_Link_Role_Mster_Tiles");
	}
	
	
	@RequestMapping(value = "/Edit_Report_Link_Role_Mster_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Inquiry_Category_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "note", required = false) String note,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		//SECURITY - POOJA
//		if(request.getHeader("Referer") == null ) { 
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		
//		String roleid1 = sessionEdit.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//		
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		tx.commit();
		sessionHQL.close();

		String staff_lvl = "";
		String role = sessionEdit.getAttribute("role").toString();
//		String userId = session.getAttribute("userId").toString();
		
		if (role.equals("NCISM_ADMIN")) {
			staff_lvl = "NCISM";
		}
		else if (role.equals("NCH_ADMIN")) {
			staff_lvl = "NCH";
		}
		Mmap.put("getRoleFromStaff_lvl", common.getRoleFromStaff_lvl(sessionFactory, staff_lvl));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		return new ModelAndView("Report_Link_Role_Mster_Tiles");
	}
	

	
	@PostMapping("/getFilterInq_Report_datalist")
	public @ResponseBody List<Map<String, Object>> getFilterInq_Report_datalist(int startPage, int pageLength,HttpSession session,
			String Search, String orderColunm, String orderType, String inq_no, String per_state, String des,String inq_cat,String status) {

		return IRD.getFilterInq_Report_datalist(startPage, pageLength, Search, orderColunm, orderType, inq_no, inq_cat, per_state, des, status, session);

	}
	
	@PostMapping("/getTotalInq_Report_dataCount1")
	public @ResponseBody long getTotalInq_Report_dataCount1(HttpSession sessionUserId, String Search, String inq_no,String inq_cat, String per_state, String des, String status) {
		return IRD.getTotalInq_Report_dataCount1(Search, inq_no, inq_cat, per_state, des, status, sessionUserId);

	}
		
	
	@RequestMapping(value = "/getInq_RepDetails", method = RequestMethod.POST)
	public @ResponseBody String getInq_RepDetails(ModelMap Mmap,RedirectAttributes ra, String msg, HttpServletRequest request,String hid,String note,String status,HttpSession httpsession) {
				 Session session = sessionFactory.openSession();
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = session.beginTransaction();
				 String userid = httpsession.getAttribute("userId_for_jnlp").toString();
				 DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				 System.err.println("-------Status----" +status + "------Id-----" +hid + "------Note-------" +note);
				 Query q0 = session.createQuery("update HD_Inq_Helpdesk_Parent set status=:status,modified_by=:modified_by,modified_dt=:modified_dt where id=:id");
							q0.setParameter("status", Integer.parseInt(status));		 	
							q0.setParameter("modified_by", userid);
							q0.setParameter("modified_dt", new Date());
							q0.setParameter("id", Integer.parseInt(hid));
							
							
							HD_Inq_Helpdesk_Child hc = new HD_Inq_Helpdesk_Child();
							
							hc.setNote(note);
							hc.setP_id(Integer.parseInt(hid));
							hc.setStatus(1);
								 			    		 
		 	msg = q0.executeUpdate() > 0 ? "1" : "0";
		 	sessionHQL.save(hc);
		 	session.flush();
			session.clear();
			tx.commit();
	       session.close();                
	      return msg;
	}
	

	@RequestMapping(value = "Inquiry_Link_Role_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Inquiry_Link_Role_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "hidcase", required = false) String hidcase, HttpServletRequest request) {
		
		
		System.err.println("----------hidcase------" +hidcase);
		
		String staff_lvl = "";
		String role = session.getAttribute("role").toString();
//		String userId = session.getAttribute("userId").toString();
		
		if (role.equals("NCISM_ADMIN")) {
			staff_lvl = "NCISM";
		}
		else if (role.equals("NCH_ADMIN")) {
			staff_lvl = "NCH";
		}
		Mmap.put("getRoleFromStaff_lvl", common.getRoleFromStaff_lvl(sessionFactory, staff_lvl));
		Mmap.put("hidcase",hidcase);
		Mmap.put("getRoleNameList", getRoleNameList());
		Mmap.put("getInq_CatList", getInq_CatList());
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 
		return new ModelAndView("Inquiry_Link_Role_Mster_Tiles");
	}
	
	public List<Role> getRoleNameList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Role order by role");
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	public List<Role> getInq_CatList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from HD_INQUIRY_CATEGORY_MSTR where status = 1 order by id");
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	//------------------------------- Used For File Download function  encrypt id ---------------------------
			@SuppressWarnings("null")
			@RequestMapping(value = "/getDownloadPdfUrlforhd_Doc")
			public ModelAndView getDownloadPdfUrlforhd_Doc(@RequestParam(value = "msg", required = false) String msg,
					@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
					
					ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
					throws IOException {
				
//				SECURITY -- RIDDHI 
//				if(request.getHeader("Referer") == null ) { 
////					session.invalidate();
//					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					return new ModelAndView("redirect:/landingpage");
//				 }
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}

				String url = pageUrl;
				String EXTERNAL_FILE_PATH = "";

				EXTERNAL_FILE_PATH = IRD.getFilePathhd_Query(Integer.parseInt(doc_id1));
				
				if (EXTERNAL_FILE_PATH != "") {
					File file = null;
					file = new File(EXTERNAL_FILE_PATH);
					System.err.println("file---------->     "+file);
					
					try {
						if (!file.exists()) {
							model.put("msg", "Sorry.The file you are looking for does not exist!");
							return new ModelAndView(url);
						}
					} catch (Exception exception) {
						model.put("msg", "Sorry.The file you are looking for does not exist!");
						return new ModelAndView(url);
					}

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
					response.setContentLength((int) file.length());
					InputStream inputStream = null;
					try {
						inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
						model.put("msg", "Downloaded Successfully");
						return new ModelAndView(url);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} 
				return new ModelAndView(url);
			}
			
			@RequestMapping(value = "/getInq_Reportchildeditstatus", method = RequestMethod.POST)
			public @ResponseBody List<Map<String,Object>> getInq_Reportchildeditstatus(String hid, String note, String status) {
						               
			      return IRD.getInq_Reportchildeditstatus(hid,note,status);
			}

}
