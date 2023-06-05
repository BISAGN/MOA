package com.AyushEdu.controller.Enhancement_Of_Research;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Enhancement_Of_Research.Advance_Search_DAO;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Advance_Search_Controller {
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ValidationController validation;
	@Autowired
	private Advance_Search_DAO AER;
	
	@RequestMapping(value = "/Advance_Search_Url", method = RequestMethod.GET)
	public ModelAndView Advance_Search_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Feedback_Report_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		 String role = session.getAttribute("role").toString();
		Mmap.put("msg", msg);
		Mmap.put("getsysList",common.getsysList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("getCategorylist", common.getCategorylist(sessionFactory));
		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
//		Mmap.put("getSearchFieldList", common.getSearchFieldList(sessionFactory));
		
		
		
		return new ModelAndView("Advance_Search_Tiles");
	}
	
	@PostMapping(value = "/Advanced_SearchAction")
	public ModelAndView Advanced_SearchAction(
			@Validated @ModelAttribute("Advanced_SearchCMD") TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS CD,
			 BindingResult result, HttpServletRequest request,MultipartHttpServletRequest mul,
			ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra) throws IOException, ParseException {
		
//		  if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		  }
//		  String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		  }
			
	     Date date = new Date();
	     String username = principal.getName();
	
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		String medicine_system = request.getParameter("medicine_system");
		String category = request.getParameter("category");
		String search_field = request.getParameter("search_field");
		String title = request.getParameter("title");
		String hyperlink = request.getParameter("hyperlink");
		String desc_content = request.getParameter("desc_content");
		String status = request.getParameter("status");
		String institute_name = request.getParameter("institute_name");
		String author_name = request.getParameter("author_name");
		String journal_name = request.getParameter("journal_name");
		String abstract_content = request.getParameter("abstract_content");
		
		String upload_paper = gd(request, mul, session,"upload_paper");
		
		if (medicine_system.equals("0") ) {
			ra.addAttribute("msg", "Please Select Medicine System");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (category.equals("0") ) {
			ra.addAttribute("msg", "Please Select Category");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (institute_name.equals("0") ) {
			ra.addAttribute("msg", "Please Select Institute Name");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (author_name.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Author Name");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck100(author_name) == false) {
			ra.addAttribute("msg","Author Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (journal_name.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Journal Name");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck100(journal_name) == false) {
			ra.addAttribute("msg","Journal Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
//		if (search_field.equals("0") ) {
//			ra.addAttribute("msg", "Please Select Search Field");
//			return new ModelAndView("redirect:Advance_Search_Url");
//			}
		if (title.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Title");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck200(title) == false) {
			ra.addAttribute("msg","Title "+ validation.MaxlengthcheckMSG200);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (hyperlink.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter hyperlink");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck200(hyperlink) == false) {
			ra.addAttribute("msg","Hyperlink "+ validation.MaxlengthcheckMSG200);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (upload_paper == null || upload_paper.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload Paper.");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (abstract_content.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Abstract");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck(abstract_content) == false) {
			ra.addAttribute("msg","Abstract "+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (desc_content.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Content");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck(desc_content) == false) {
			ra.addAttribute("msg","Content "+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		
		
			int id = CD.getId() > 0 ? CD.getId() : 0;
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS where upper(title)=:title and medicine_system=:medicine_system and category=:category and search_field=:search_field "
						+ "and institute_name=:institute_name and author_name=:author_name and upper(hyperlink)=:hyperlink and "
						+ "journal_name=:journal_name and abstract_content=:abstract_content and upper(desc_content)=:desc_content and upload_paper=:upload_paper and status=:status and id !=:id")
						.setParameter("medicine_system", CD.getMedicine_system())
						.setParameter("category", CD.getCategory())
						.setParameter("search_field", 0)
						.setParameter("institute_name", CD.getInstitute_name())
						.setParameter("author_name", CD.getAuthor_name().toUpperCase())
						.setParameter("journal_name", CD.getJournal_name().toUpperCase())
						.setParameter("title", CD.getTitle().toUpperCase())
						.setParameter("abstract_content", CD.getAbstract_content().toUpperCase())
						.setParameter("desc_content", CD.getDesc_content().toUpperCase())
						.setParameter("hyperlink", CD.getHyperlink().toUpperCase())
						.setParameter("upload_paper", CD.getUpload_paper())
						.setParameter("status", CD.getStatus())
						.setParameter("id", id).uniqueResult();
				
//				int idd =0;
				if (id == 0) {
					CD.setUpload_paper(upload_paper);
					CD.setCreated_by(username);
					CD.setCreated_date(date);
					if (c == 0) {
						 id = (int)sessionHQL.save(CD);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");

					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
				tx.commit();
				//For Core Directory
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
			return new ModelAndView("redirect:Advance_Search_Url");
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

	@PostMapping("/getFilterSearch_Advance_Enhance_Research_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterSearch_Advance_Enhance_Research_data(HttpSession session, int startPage,
			             int pageLength,String Search, String orderColunm, String orderType, String medicine_system, String category,
			             String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {
//		String search_field,//search_field,
		return (ArrayList<Map<String, Object>>) AER.Advance_Enhance_Research_DataList(startPage, pageLength, Search,orderColunm, orderType, 
				 medicine_system, category, institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);
		
	}

	@PostMapping("/getTotalSearch_Advance_Enhance_ResearchCount")
	public @ResponseBody long getTotalSearch_Advance_Enhance_ResearchCount(HttpSession sessionUserId, String Search,
			String medicine_system, String category,String institute_name, String author_name, String journal_name, String title,String hyperlink, String abstract_content, String desc_content,String status) {
//		 String search_field, search_field,

		return AER.DataTotalAdvance_Enhance_ResearchCount(Search, medicine_system, category,
	              institute_name, author_name, journal_name, title, hyperlink, abstract_content, desc_content, status);

	}	
	
	
	

	@RequestMapping(value = "/Edit_Advanced_SearchUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Advanced_SearchUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {
//
//		if(request.getHeader("Referer") == null ) { 
//	//		 sessionEdit.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = sessionEdit.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		String role = sessionEdit.getAttribute("role").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS Advance_Search_Details = AER.getAdvance_SearchByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("getsysList",common.getsysList(sessionFactory));
		Mmap.put("Advance_Search_Details", Advance_Search_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
//		Mmap.put("advanced", AER.getAdvance_SearchByid(Integer.parseInt(updateid)));
		Mmap.put("getCategorylist", common.getCategorylist(sessionFactory));
//		Mmap.put("getSearchFieldList", common.getSearchFieldList(sessionFactory));
		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));

		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Edit_Advance_Search_Tiles");
	}
	
	//edit action
	@RequestMapping(value = "/EditAdvanced_SearchAction", method = RequestMethod.POST)
	public ModelAndView EditAdvanced_SearchAction(@ModelAttribute("EditAdvanced_SearchCMD") TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS rs,
			HttpServletRequest request,MultipartHttpServletRequest mul,ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws IOException, ParseException {

//		if(request.getHeader("Referer") == null ) { 
//		//	 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		String username = session.getAttribute("username").toString();

		int id = Integer.parseInt(request.getParameter("id"));
		String medicine_system = request.getParameter("medicine_system");
		String category = request.getParameter("category");
		String institute_name = request.getParameter("institute_name");
		String author_name = request.getParameter("author_name");
		String journal_name = request.getParameter("journal_name");
//		String search_field = request.getParameter("search_field");
		String title = request.getParameter("title");
		String hyperlink = request.getParameter("hyperlink");
		String abstract_content = request.getParameter("abstract_content");
		String desc_content = request.getParameter("desc_content");
		String status = request.getParameter("status");
		
//		String upload_paper = gd(request, mul, session,"upload_paper");

		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		
		if (medicine_system.equals("0") ) {
			ra.addAttribute("msg", "Please Select Medicine System");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (category.equals("0") ) {
			ra.addAttribute("msg", "Please Select Category");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (institute_name.equals("0") ) {
			ra.addAttribute("msg", "Please Select Institute Name");
			return new ModelAndView("redirect:Advance_Search_Url");
			}
		if (author_name.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Author Name");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck100(author_name) == false) {
			ra.addAttribute("msg","Author Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (journal_name.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Journal Name");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck100(journal_name) == false) {
			ra.addAttribute("msg","Journal Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
//		if (search_field.equals("0") ) {
//			ra.addAttribute("msg", "Please Select Search Field");
//			return new ModelAndView("redirect:Advance_Search_Url");
//			}
		if (title.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Title");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck200(title) == false) {
			ra.addAttribute("msg","Title "+ validation.MaxlengthcheckMSG200);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (hyperlink.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter hyperlink");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck200(hyperlink) == false) {
			ra.addAttribute("msg","Hyperlink "+ validation.MaxlengthcheckMSG200);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
//		if (upload_paper == null || upload_paper.trim().equals("")) {
//			ra.addAttribute("msg", "Please Upload Paper.");
//			return new ModelAndView("redirect:Advance_Search_Url");
//		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (abstract_content.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Abstract");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck(abstract_content) == false) {
			ra.addAttribute("msg","Abstract "+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (desc_content.trim().equals("") ) {
			ra.addAttribute("msg", "Please Enter Content");
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		if (validation.maxlengthcheck(desc_content) == false) {
			ra.addAttribute("msg","Content "+ validation.MaxlengthcheckMSG);
			return new ModelAndView("redirect:Advance_Search_Url");
		}
		
		try {
			Query q0 = session1.createQuery("select count(id) from  TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS where upper(title)=:title and medicine_system=:medicine_system and category=:category "
					+ " and institute_name=:institute_name and upper(author_name)=:author_name and upper(journal_name)=:journal_name and search_field=:search_field \n"
					+ " and upper(hyperlink)=:hyperlink and upper(abstract_content)=:abstract_content and upper(desc_content)=:desc_content and status=:status and id !=:id ");
			q0.setParameter("medicine_system", Integer.parseInt(medicine_system));
			q0.setParameter("category", Integer.parseInt(category));
			q0.setParameter("institute_name", Integer.parseInt(institute_name));
			q0.setParameter("author_name", author_name);
			q0.setParameter("journal_name", journal_name);
			q0.setParameter("search_field",0);
			q0.setParameter("title", title);
			q0.setParameter("hyperlink", hyperlink);
			q0.setParameter("abstract_content", abstract_content);
			q0.setParameter("desc_content", desc_content);
//			q0.setParameter("upload_paper", upload_paper);   //and upload_paper=:upload_paper 
			q0.setParameter("status", Integer.parseInt(status));
			q0.setParameter("id", id);


			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS set medicine_system=:medicine_system,category=:category,institute_name=:institute_name,"
						+ "search_field=:search_field,author_name=:author_name,journal_name=:journal_name,title=:title,hyperlink=:hyperlink,abstract_content=:abstract_content, "
						+ "desc_content=:desc_content,status=:status,modified_by=:modified_by,modified_date=:modified_date "
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("medicine_system", Integer.parseInt(medicine_system))
						.setParameter("category", Integer.parseInt(category))
						.setParameter("institute_name", Integer.parseInt(institute_name))
						.setParameter("search_field", 0)
						.setParameter("author_name", author_name)
						.setParameter("journal_name", journal_name)
						.setParameter("title", title)
						.setParameter("hyperlink", hyperlink)
						.setParameter("abstract_content", abstract_content)
						.setParameter("desc_content", desc_content)
//						.setParameter("upload_paper", upload_paper) ///,upload_paper=:upload_paper
						.setParameter("status", Integer.parseInt(status))
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
//				gen_dirdao.Update_Gender_Master_Data( id,gender_name,status,new Date(),username);
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
		
		return new ModelAndView("redirect:Advance_Search_Url");
	}
	
	@RequestMapping(value = "/delete_Advanced_SearchUrl", method = RequestMethod.POST)
	public ModelAndView delete_Advanced_SearchUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

//		if(request.getHeader("Referer") == null ) { 
//	//		 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Advance_Search_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update TB_ENHANCE_RESEARCH_ADVANCE_SEARCH_DETAILS set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			
			tx.commit();
//			gen_dirdao.Delete_Gender_Master_Data(id);  
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
		return new ModelAndView("redirect:Advance_Search_Url");
	}
	
	
	@RequestMapping(value = "/getInstituteBy_systemList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getInstituteBy_systemList(String medicine_system)  {
		return AER.getInstituteBy_systemList(medicine_system);
	}
}
