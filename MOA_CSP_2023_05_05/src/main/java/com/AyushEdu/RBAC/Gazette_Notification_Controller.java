package com.AyushEdu.RBAC;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_GAZETTE_NOTIFICATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Gazette_Notification_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CommonController com;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	
	@Autowired
	ValidationController validation = new ValidationController();
	
	@Autowired
	CommonController common;
	
	@RequestMapping(value = "/gazette_notification_url", method = RequestMethod.GET)
	public ModelAndView gazette_notification_url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 try {	
				if(request.getHeader("Referer") == null ) { 
					 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("gazette_notification_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		 String role = session.getAttribute("role").toString();
		 Mmap.put("msg", msg);
//		 Mmap.put("getSystemList",common.getSystemListbyrole(sessionFactory,role));
//		 Mmap.put("gettermList",common.gettermList(sessionFactory));
	//		Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
		}		 
		return new ModelAndView("Gazette_Notification_Tiles");
	}
	

	@PostMapping(value = "/Gazette_Notification_Action")	
	public ModelAndView Gazette_Notification_Action(@Validated @ModelAttribute("Gazette_Notification_cmd") TB_GAZETTE_NOTIFICATION td,
			BindingResult result,HttpServletRequest request,MultipartHttpServletRequest mul, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {
		
		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("gazette_notification_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String hyperlink = request.getParameter("hyperlink");
		String from_date = request.getParameter("from_date");
		String to_date = request.getParameter("to_date");
		String message = request.getParameter("message");
		String common_cb = request.getParameter("common_cb");
		String nch_cb = request.getParameter("nch_cb");
		String ncism_cb = request.getParameter("ncism_cb");
		String file_upload = gd(request, mul, session,"file_upload");
		
		String portal = "";
		
		if(common_cb != null) {
			portal += common_cb+",";
		}
		if(nch_cb != null) {
			portal += nch_cb+",";
		}
		if(ncism_cb != null) {
			portal += ncism_cb;
		}

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if (file_upload == null || file_upload.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload File.");
			return new ModelAndView("redirect:gazette_notification_url");
		}
		if (hyperlink == null || hyperlink.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Hyperlink.");
			return new ModelAndView("redirect:gazette_notification_url");
		}
		if (from_date.trim().equals("") || from_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select From Date");
			return new ModelAndView("redirect:gazette_notification_url");
		}
		if (to_date.trim().equals("") ||  to_date.trim().equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select To Date");
			return new ModelAndView("redirect:gazette_notification_url");
		}
		if (message == null || message.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Message.");
			return new ModelAndView("redirect:gazette_notification_url");
		}
		
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_GAZETTE_NOTIFICATION where message=:message and "
					+ "hyperlink=:hyperlink and from_date=:from_date and to_date=:to_date and id !=:id")
					.setParameter("message", td.getMessage())
					.setParameter("hyperlink", td.getHyperlink())
//					.setParameter("upload_file", td.getFile_upload())
					.setParameter("from_date", td.getFrom_date())
					.setParameter("to_date", td.getTo_date())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setFile_upload(file_upload);
				td.setHyperlink(hyperlink);
				td.setFrom_date(format.parse(from_date));
				td.setTo_date(format.parse(to_date));
				td.setMessage(message);
				td.setPortal(portal);
				td.setCreated_by(Integer.parseInt(userid));
				td.setCreated_date(date);
				
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.clear();
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
		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:gazette_notification_url");
	}
	
	
	//start
public String gd(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
		
		System.err.println("id----"+id);
	
	String extension=""; //add line
	String fname = ""; //add line
	
	request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
	
	MultipartFile file = mul.getFile(id);
	if (!file.getOriginalFilename().isEmpty()) {
		
		byte[] bytes = file.getBytes();
		String  mnhFilePath = session.getAttribute(id).toString();
		
        File dir = new File(mnhFilePath);
		if (!dir.exists())
			dir.mkdirs();
		String filename = file.getOriginalFilename();
				
		int j = filename.lastIndexOf('.');
		if (j >= 0) {
			extension = filename.substring(j+1);
		}
		java.util.Date date1= new java.util.Date();
		fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
		
		File serverFile = new File(fname);	               
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);	                
		stream.close();

	}else {

		
	}
	return fname;
	
	}

}
