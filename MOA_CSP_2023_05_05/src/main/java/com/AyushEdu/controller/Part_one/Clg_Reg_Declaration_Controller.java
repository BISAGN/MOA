package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_DECLARATION;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_TEACHER;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_DAO;
import com.AyushEdu.dao.Part_One.Clg_reg_College_Declaration_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Declaration_Controller {

	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private Clg_Reg_College_Infrastructure_DAO CIDao;
	
	@Autowired
	Clg_reg_College_Declaration_DAO DDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	private Clg_Reg_College_Staff_List_DAO CSLDao;
	
	@RequestMapping(value = "admin/declaration", method = RequestMethod.GET)
	public ModelAndView college_staff_list(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String role = session.getAttribute("role").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_COLLEGE_DECLARATION where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		Mmap.put("institude", institude);
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		
//		if(ibdao.getpid_from_userid(userid).size()!=0) {
//			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//				Mmap.put("msg", "Please Save Basic details First");
//				return new ModelAndView("redirect:basics_information");
//
//			}else {
//				
//				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//			}
//		}else {
//			
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}
		
		Mmap.put("GetDocument_Details", DDao.GetDocument_Details(institute_id));
		Mmap.put("GetPrinacipal_Name", CSLDao.GetPrinacipal_Name(institute_id));
		
		return new ModelAndView("declaration");
	}
	
	
	//SAVE UPLOAD DOCUMENT DETAILS
	@PostMapping(value = "/Declaration_Document_Details_Save_Draft_Action")
	public @ResponseBody String Declaration_Document_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "notarizedundertaking", required = false) MultipartFile notarizedundertaking_doc,
			@RequestParam(value = "notarizedaffidavit", required = false) MultipartFile notarizedaffidavit_doc,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws IOException, ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		CLG_REG_COLLEGE_DECLARATION pers_p =new CLG_REG_COLLEGE_DECLARATION();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		String hid_upload_doc = request.getParameter("hid_upload_doc");
		String notarizedundertaking = "notarizedundertaking";
		String notarizedaffidavit = "notarizedaffidavit";
		
		
		if (!notarizedundertaking_doc.isEmpty()) {
			notarizedundertaking = upload_imagemethod(request,notarizedundertaking_doc,session, notarizedundertaking);
		}
		else {
			notarizedundertaking = request.getParameter("hid_notarizedundertaking");
		}
		
		if (!notarizedaffidavit_doc.isEmpty()) {
			notarizedaffidavit = upload_imagemethod(request,notarizedaffidavit_doc,session, notarizedaffidavit);
		}
		else {
			notarizedaffidavit = request.getParameter("hid_notarizedaffidavit");
		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_DECLARATION upload_doc_detail =new CLG_REG_COLLEGE_DECLARATION();
				
				upload_doc_detail.setNotarizedundertaking(notarizedundertaking);
				upload_doc_detail.setNotarizedaffidavit(notarizedaffidavit);
				upload_doc_detail.setInstitute_id(Integer.parseInt(institute_id));
				upload_doc_detail.setS_id(Integer.parseInt(s_id));
				upload_doc_detail.setCreated_by(Integer.parseInt(userid));
				upload_doc_detail.setCreated_date(date);
				
				if (Integer.parseInt(hid_upload_doc) == 0) {
					int hid_document_detail1= (Integer) sessionHQL.save(upload_doc_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_document_detail1) ;
				}
				else {
					CLG_REG_COLLEGE_DECLARATION upload_doc_detail_u = (CLG_REG_COLLEGE_DECLARATION) sessionHQL
							.get(CLG_REG_COLLEGE_DECLARATION.class, Integer.parseInt(hid_upload_doc));
					
					upload_doc_detail_u.setNotarizedundertaking(notarizedundertaking);
					upload_doc_detail_u.setNotarizedaffidavit(notarizedaffidavit);
					upload_doc_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					upload_doc_detail_u.setS_id(Integer.parseInt(s_id));
					upload_doc_detail_u.setModified_by(Integer.parseInt(userid));
					upload_doc_detail_u.setModified_date(date);
					sessionHQL.update(upload_doc_detail_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					
				}
			
				
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
		return  "Data Saved Successfully";
	}
	
	//SAVE DECLARATION CHECK
	@PostMapping(value = "/Pricipal_Declaration_check_Save_Draft_Action")
	public @ResponseBody String Pricipal_Declaration_check_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		String hid_principal_declaration = request.getParameter("hid_principal_declaration");
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_DECLARATION principal_declaration =new CLG_REG_COLLEGE_DECLARATION();
				
			principal_declaration.setPrin_declaration(1);
			principal_declaration.setInstitute_id(Integer.parseInt(institute_id));
			principal_declaration.setS_id(Integer.parseInt(s_id));
			principal_declaration.setCreated_by(Integer.parseInt(userid));
			principal_declaration.setCreated_date(date);
				
				if (Integer.parseInt(hid_principal_declaration) == 0) {
					int hid_declaration_detail1= (Integer) sessionHQL.save(principal_declaration);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_declaration_detail1) ;
				}
				else {
					CLG_REG_COLLEGE_DECLARATION principal_declaration_u = (CLG_REG_COLLEGE_DECLARATION) sessionHQL
							.get(CLG_REG_COLLEGE_DECLARATION.class, Integer.parseInt(hid_principal_declaration));
					
					principal_declaration_u.setPrin_declaration(1);
					principal_declaration_u.setInstitute_id(Integer.parseInt(institute_id));
					principal_declaration_u.setS_id(Integer.parseInt(s_id));
					principal_declaration_u.setModified_by(Integer.parseInt(userid));
					principal_declaration_u.setModified_date(date);
					sessionHQL.update(principal_declaration_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					
				}
			
				
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
		return  "Data Saved Successfully";
	}
	
	//SAVE DECLARATION CHECK
	@PostMapping(value = "/Manegment_Declaration_check_Save_Draft_Action")
	public @ResponseBody String Manegment_Declaration_check_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		String hid_management_declaration = request.getParameter("hid_management_declaration");
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_DECLARATION managment_declaration =new CLG_REG_COLLEGE_DECLARATION();
				
			managment_declaration.setMange_declaration(1);
			managment_declaration.setInstitute_id(Integer.parseInt(institute_id));
			managment_declaration.setS_id(Integer.parseInt(s_id));
			managment_declaration.setCreated_by(Integer.parseInt(userid));
			managment_declaration.setCreated_date(date);
				
				if (Integer.parseInt(hid_management_declaration) == 0) {
					int hid_declaration_detail1= (Integer) sessionHQL.save(managment_declaration);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_declaration_detail1) ;
				}
				else {
					CLG_REG_COLLEGE_DECLARATION managment_declaration_u = (CLG_REG_COLLEGE_DECLARATION) sessionHQL
							.get(CLG_REG_COLLEGE_DECLARATION.class, Integer.parseInt(hid_management_declaration));
					
					managment_declaration_u.setMange_declaration(1);
					managment_declaration_u.setInstitute_id(Integer.parseInt(institute_id));
					managment_declaration_u.setS_id(Integer.parseInt(s_id));
					managment_declaration_u.setModified_by(Integer.parseInt(userid));
					managment_declaration_u.setModified_date(date);
					sessionHQL.update(managment_declaration_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					
				}
			
				
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
		return  "Final Submit Sucessfully";
	}

	
	
	public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
		
		String extension=""; //add line
		String fname = ""; //add line
		
		request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
		
		MultipartFile file = mul;
		
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
			fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
			
			File serverFile = new File(fname);	               
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);	                
			stream.close();

		}else {

			
		}
		return fname;
		
		}
	
	
	@RequestMapping(value = "/AttachmentFilePath_declaration", method = RequestMethod.GET)
	public void AttachmentFilePath_declaration(@ModelAttribute("i_id") String id, @ModelAttribute("doc_id") String doc_id,ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
//		System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

		final int BUFFER_SIZE = 4096;

		String i_id = id;
		
		String filePath = DDao.getAttachmentFilePath(i_id,doc_id);

//		System.out.println("chgukhdfguhkdfhgkj---" + filePath);

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//				request.getRealPath("/") + "/srv/Document/No_Image.jpg";

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			} else {

				String fullPath = filePath;

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			}

		} catch (Exception ex) {

			System.out.println(ex);
			
		//	admin//js//img//No_Image.jpg
			
			
			
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
			File downloadFile = new File(fullPath);

			FileInputStream inputStream = new FileInputStream(downloadFile);

			String mimeType = context.getMimeType(fullPath);

			if (mimeType == null) {

				mimeType = "application/octet-stream";

			}

			response.setContentType(mimeType);

			response.setContentLength((int) downloadFile.length());

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];

			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {

				outStream.write(buffer, 0, bytesRead);

			}

			inputStream.close();

			outStream.close();
		}
	}

}
