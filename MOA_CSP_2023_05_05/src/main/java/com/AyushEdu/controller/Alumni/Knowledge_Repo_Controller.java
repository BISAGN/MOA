package com.AyushEdu.controller.Alumni;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Knowledge_Repo_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
//	@Autowired
//	Department_Mstr_DAO Ddao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Knowledge_Repo_Url", method = RequestMethod.GET)
	public ModelAndView Knowledge_Repo_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		Mmap.put("msg", msg);
		Mmap.put("getCategoryList", common.getCategoryList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Knowledge_Repo_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	


	@PostMapping(value = "/KnowledgeRepoAction")
	public ModelAndView KnowledgeRepoAction(@Validated @ModelAttribute("KnowledgeRepoCMD") TB_KNOWLEDGE_REPO td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, MultipartHttpServletRequest mul,MultipartFile files,
			RedirectAttributes ra) throws IOException {

		//SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Knowledge_Repo_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String category_id = request.getParameter("category_id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		MultipartFile upload_doc = mul.getFile("upload_doc");
		
		//SECURITY-----
				if (upload_doc.getOriginalFilename().isEmpty()) {
					ra.addAttribute("msg","Please Upload File");
					return new ModelAndView("redirect:Knowledge_Repo_Url");
				}
				if (!upload_doc.getOriginalFilename().isEmpty()) {
					
					
					 if (upload_doc.getOriginalFilename().split("[.]").length > 2) {
						 ra.addAttribute("msg", "Invalid file extension for Document");
							return new ModelAndView("redirect:Knowledge_Repo_Url");
					}
					
					
					String upload_fileEXt = FilenameUtils.getExtension(upload_doc.getOriginalFilename()).toLowerCase();
					if (!upload_fileEXt.equals("pdf")) {
						ra.addAttribute("msg","Please Upload File of .pdf extention");
						return new ModelAndView("redirect:Knowledge_Repo_Url");
					}
					long filesize = upload_doc.getSize() / 1024;
					if (filesize > 200) {
						ra.addAttribute("msg","File size should be 200 kb or less");
						return new ModelAndView("redirect:Knowledge_Repo_Url");
					}
				}
		//SECURITY-----
		
		String photo_path_att = "";
		if (upload_doc != null && !upload_doc.isEmpty()) {
			photo_path_att = common.fileupload(upload_doc.getBytes(), upload_doc.getOriginalFilename(),
					"UploadHardCopy");
		}


		if (category_id == null || category_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Category");
			return new ModelAndView("redirect:Knowledge_Repo_Url");
		}
		
//		if (validation.maxlengthcheck100(category_id) == false) {
//			ra.addAttribute("msg","Category "+ validation.MaxlengthcheckMSG100);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");	
//		}
//		
//		if (validation.isOnlyAlphabetDASH(category_id) == false) {
//			ra.addAttribute("msg","Category "+ validation.isOnlyAlphabetMSGDASH);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");
//		}

		if (title == null || title.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Title");
			return new ModelAndView("redirect:Knowledge_Repo_Url");
		}
		
//		if (validation.maxlengthcheck100(title) == false) {
//			ra.addAttribute("msg","Title "+ validation.MaxlengthcheckMSG100);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");	
//		}
//		
//		if (validation.isOnlyAlphabetDASH(title) == false) {
//			ra.addAttribute("msg","Title "+ validation.isOnlyAlphabetMSGDASH);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");
//		}
		
		if (description == null || description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Description");
			return new ModelAndView("redirect:Knowledge_Repo_Url");
		}
		
//		if (validation.maxlengthcheck100(description) == false) {
//			ra.addAttribute("msg","Description "+ validation.MaxlengthcheckMSG100);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");	
//		}
//		
//		if (validation.isOnlyAlphabetDASH(description) == false) {
//			ra.addAttribute("msg","Description "+ validation.isOnlyAlphabetMSGDASH);
//			
//			return new ModelAndView("redirect:Knowledge_Repo_Url");
//		}
		


		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_KNOWLEDGE_REPO where category_id=:category_id and title=:title and description=:description and id !=:id")
					.setParameter("category_id", td.getCategory_id())
					.setParameter("title", td.getTitle())
					.setParameter("description", td.getDescription())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setUpload_doc(photo_path_att);
				td.setUserid(Integer.parseInt(userid));
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
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
		
		return new ModelAndView("redirect:Knowledge_Repo_Url");
	}
	

	
}