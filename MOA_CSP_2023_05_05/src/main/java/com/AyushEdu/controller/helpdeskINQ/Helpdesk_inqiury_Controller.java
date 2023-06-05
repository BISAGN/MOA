package com.AyushEdu.controller.helpdeskINQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.helpdeskINQ.HD_INQUIRY_CATEGORY_MSTR;
import com.AyushEdu.Models.helpdeskINQ.HD_Inq_Helpdesk_Child;
import com.AyushEdu.Models.helpdeskINQ.HD_Inq_Helpdesk_Parent;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Helpdesk_inqiury_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ValidationController validation;
	@Autowired
	CommonController common;
	
	@GetMapping(value = "/helpdesk_URL")
	public ModelAndView helpdesk_URL(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
			 Mmap.put("getInq_CatList", getInq_CatList());
			Mmap.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("help_desk_tiles");
	}
	
	@PostMapping(value = "/Helpdesk_Inqiry_Action")
	public ModelAndView Helpdesk_Inqiry_Action(@Validated @ModelAttribute("Helpdesk_Inqiry_CMD") HD_Inq_Helpdesk_Parent td, BindingResult result,
			HttpServletRequest request, ModelMap model,  MultipartHttpServletRequest mul,MultipartFile files,
			RedirectAttributes ra, HttpSession session) throws Exception {

//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
		String state = request.getParameter("state");
		String Inq_Cat = request.getParameter("Inq_Cat");
		String description = request.getParameter("description");
		
		String attachment_hid = request.getParameter("attachment_hid");
		
		String email = request.getParameter("email");
		String mobile_no = request.getParameter("mobile_no");
		String system_id = request.getParameter("system_id");
		String categary_id = request.getParameter("categary_id");
		MultipartFile attachment = mul.getFile("attachment");
		
		String photo_path_att = "";
		if (attachment != null && !attachment.isEmpty()) {
			photo_path_att = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
					"UploadHardCopy1");
		} else {
			photo_path_att = attachment_hid;
		}
		 
//		String date_of_admission2 = request.getParameter("date_of_admission"); 
		
		//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String role = session.getAttribute("role").toString();
//		System.out.println("Dr=======================>>>"+role);
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		if (system_id != null && system_id.equals("0") && system_id != "0") {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:help");
		}
		if (categary_id != null && categary_id.equals("0") && categary_id != "0") {
			ra.addAttribute("msg", "Please Select Category");
			return new ModelAndView("redirect:help");
		}
		if (Inq_Cat != null && Inq_Cat.equals("0") && Inq_Cat != "0") {
			ra.addAttribute("msg", "Please Select Inquiry Category");
			return new ModelAndView("redirect:help");
		}
		if (validation.isOnlyAlphabetDASH(description) == false) {
			ra.addAttribute("msg", "Description " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:help");
		}

		if (description == null || description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Description");
			return new ModelAndView("redirect:help");
		}
		if (mobile_no.equals("0") || mobile_no == null) {
			ra.addAttribute("msg", "Please Enter Mobile Number");
			return new ModelAndView("redirect:help");
		}
		if (email.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Email Id.");
			return new ModelAndView("redirect:help");
		}
		if (validation.maxlengthcheck70(email) == false) {
			ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
			return new ModelAndView("redirect:help");
		}
		if (state != null && state.equals("0") && state != "0") {
			ra.addAttribute("msg", "Please Select State");
			return new ModelAndView("redirect:help");
		}
		 
		
		Date date = new Date();
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String university_id= request.getParameter("university_id");
		
//		===============FOR FILE
		MultipartFile file28 = mul.getFile("attachment");
		
		//SECURITY-----
//		if (file28.getOriginalFilename().isEmpty()) {
//			ra.addAttribute("msg","Please Upload File");
//			return new ModelAndView("redirect:help");
//		}
//		if (!file28.getOriginalFilename().isEmpty()) {
//			String upload_fileEXt = FilenameUtils.getExtension(file28.getOriginalFilename()).toLowerCase();
//			if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
//				ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
//				return new ModelAndView("redirect:help");
//			}
//			long filesize = file28.getSize() / 1024;
//			if (filesize > 50) {
//				ra.addAttribute("msg","File size should be 50 kb or less");
//				return new ModelAndView("redirect:help");
//			}
//		}
		//SECURITY-----
//		String photo_file420="";
//		if (!file28.getOriginalFilename().isEmpty()) {
//			photo_file420 = common.fileupload1(file28.getBytes(),file28.getOriginalFilename(),
//					1, "signature" + "1");
//			
//			System.err.println("upload_cv--------------"+photo_file420);
//		}
//		if (attachment_hid.trim().equals("")) {
//			
//			attachment_hid=photo_file420;
//			
//			System.err.println("upload_cv--------------"+photo_file420);
//			
//		}
 
			int id = td.getId() > 0 ? td.getId() : 0;
			UserLogin p = new UserLogin();
//		String system_name = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  HD_Inq_Helpdesk_Parent where state=:state and Inq_Cat=:Inq_Cat and description=:description   and email=:email and mobile_no=:mobile_no and id !=:id")
						.setParameter("state", td.getState())
						.setParameter("Inq_Cat", td.getInq_Cat())
						.setParameter("description", description)
//						.setParameter("attachment", photo_file420)
						.setParameter("email", email)
						.setParameter("mobile_no", mobile_no)
//						.setParameter("created_dt", date)
						.setParameter("id", id).uniqueResult();
				if (id == 0) {
					if (state != null && !state.equals("")) {
						td.setState(Integer.parseInt(state));
					}
				if (id == 0) {
						if (Inq_Cat != null && !Inq_Cat.equals("")) {
							td.setInq_Cat(Integer.parseInt(Inq_Cat));
						}
						 
						td.setAttachment(photo_path_att);
						 
						
						td.setInq_no(getMaxINQ_NO());
					
						HD_Inq_Helpdesk_Child HD_Child = new HD_Inq_Helpdesk_Child();
						
						int did = (Integer) sessionHQL.save(td);
						HD_Child.setP_id(did);
		 

						HD_Child.setNote(description);
						sessionHQL.save(HD_Child);
//					td.setCreated_by(name);
//					td.setCreated_role(role);
//					td.setCreated_date(date);
					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
						ra.addAttribute("inqno",td.getInq_no());
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
				}
				tx.commit();
				}
			 catch (RuntimeException e) {
				try {

					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		return new ModelAndView("redirect:help");
	}	
	
	
	public List<HD_INQUIRY_CATEGORY_MSTR> getInq_CatList() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from HD_INQUIRY_CATEGORY_MSTR where status = 1 order by id");
		@SuppressWarnings("unchecked")
		List<HD_INQUIRY_CATEGORY_MSTR> list = (List<HD_INQUIRY_CATEGORY_MSTR>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	
	@Autowired
	private DataSource dataSource;	
	
	public String getMaxINQ_NO() {

		Connection conn = null;
		String max_inq_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			
			query = "     select to_char(CURRENT_TIMESTAMP,'yy')|| lpad((case when (select max(Substring(inq_no,3,7))\n"
					+ "					   from hd_inq_helpdesk_p)='' or (select max(Substring(inq_no,3,7))\n"
					+ "					   from hd_inq_helpdesk_p) is null  then '0' else (select max(Substring(inq_no,3,7))\n"
					+ "					   from hd_inq_helpdesk_p) end::int+1)::text, 7, '0') as max_inq_no from hd_inq_helpdesk_p limit 1 ";
			
			stmt = conn.prepareStatement(query);
			
//			stmt.setInt(1, Integer.parseInt(userid));
			System.err.println("-----max_inq_no--->   "+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				max_inq_no = rs.getString("max_inq_no");
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return max_inq_no;
	}
	
	
	@RequestMapping(value = "/getINQ_Status", method = RequestMethod.POST)
	public @ResponseBody List<String> getINQ_Status(String case_id) {
		List<String> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			
			query = "select ch.note,p.status,cat.inq_cat,* from hd_inq_helpdesk_p p\n"
					+ "inner join  hd_inq_helpdesk_child ch on p.id =  ch.p_id\n"
					+ "inner join hd_inq_cat_mstr cat on cat.id = p.inq_cat\n"
					+ "	where p.inq_no = ? ";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, (case_id));
			System.err.println("-----max_inq_no--->   "+stmt);
			ResultSet rs = stmt.executeQuery();

			String status = "";
			String note = "";
			String inq_cat = "";
			
			while (rs.next()) {
				
				note = rs.getString("note");
				status = rs.getString("status");
				inq_cat = rs.getString("inq_cat");
				
			}
			list.add(status);//0
			list.add(note);//1
			list.add(inq_cat);//2
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

}
