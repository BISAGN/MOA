package com.AyushEdu.controller.LMS_NCISM;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_NCISM.Free_CourseDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Free_Course_Ctrl {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Free_CourseDao free_CourseDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/Online_Coures_Url", method = RequestMethod.GET)
	 public ModelAndView Online_Coures_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

            try {
			
            	//SECURITY RAHUL
            	
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Online_Coures_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		     Mmap.addAttribute("msg", msg);
            } catch (Exception e) {
    			e.printStackTrace();
    		}
		return new ModelAndView("FreeCourseTiles");
	}
	

   @PostMapping(value = "/OnlineCoures_UrlAction")
	public ModelAndView OnlineCoures_UrlAction(@Validated @ModelAttribute("OnlineCourse_CMD") EDU_LMS_FREE_COURSE td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {

	   if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Online_Coures_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String coursename = request.getParameter("coursename");
		String upload_file = gd(request, mul, session, "upload_file");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String url = request.getParameter("url");
		String description = request.getParameter("description");
		
	
		if (coursename == null || coursename.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Name.");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		
		if (validation.maxlengthcheck(coursename) == false) {
			ra.addAttribute("msg", "Course Name " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (validation.isOnlyAlphabetDASH(coursename) == false) {
			ra.addAttribute("msg", "Course Name " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (upload_file.isEmpty()) {
			ra.addAttribute("msg", "Please Upload File.");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (start_date == null || start_date.trim().equals("") || start_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select Start Date");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (end_date == null || end_date.trim().equals("") || end_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select End Date");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (url == null || url.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Url.");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		
		if (description == null || description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Description.");
			return new ModelAndView("redirect:Online_Coures_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_FREE_COURSE where upper(coursename)=:coursename and upper(url)=:url and id !=:id")
					.setParameter("coursename", td.getCoursename().toUpperCase())
					.setParameter("url", td.getUrl().toUpperCase()).setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				td.setUpload_file(upload_file);
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
		return new ModelAndView("redirect:Online_Coures_Url");
	}
   
   //====================================file uplode=================================================//
   
   public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile(id);
		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {
		}
		return fname;

	}
   //==================================================================================================//
	
	@RequestMapping(value = "/Free_Course_ViewUrl", method = RequestMethod.GET)
	 public ModelAndView Free_Course_ViewUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		try {
			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Free_Course_ViewUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		Mmap.addAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("FreeCourse_ViewTiles");
	}
   
	@RequestMapping(value = "/getFilterFc_url_data", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getFilterAlu_url_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String url,String coursename,String description,HttpSession sessionUserId, Principal principal) {
		String role = sessionUserId.getAttribute("role").toString();
		return free_CourseDao.DataTablefc_urlDataList(startPage, pageLength, Search,orderColunm,coursename, orderType,url,description,sessionUserId,role);

	}
	
	@RequestMapping(value = "/getFilterFc_url_data2", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getFilterAlu_url_data2(String url,String coursename,String description,HttpSession sessionUserId) {
		String role = sessionUserId.getAttribute("role").toString();
		return free_CourseDao.DataTablefc_urlDataList2(coursename,url,description,role);

	}

	@RequestMapping(value = "/getTotalFc_url_dataCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalfc_url_dataCount(HttpSession sessionUserId, String Search,String url,String coursename,String description) {
		return free_CourseDao.DataTablefc_urlDataTotalCount(Search,sessionUserId,url,coursename,description);
	}
	
	
	//======================================edit=================================================================//
	
	@RequestMapping(value = "/Edit_Online_Coures_Url")
	public ModelAndView Edit_Online_Coures_Url(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionEdit) {
             
		try {
			
			if(request.getHeader("Referer") == null ) { 
				sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Online_Coures_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
		EDU_LMS_FREE_COURSE Onlinecourese = free_CourseDao.getolinecourseByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
     	Mmap.put("Onlinecourese", Onlinecourese);
//		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 } catch (Exception e) {
				e.printStackTrace();
			}
		return new ModelAndView("Edit_OnlineCourseTiles");
	}
	
	
	@RequestMapping(value = "/edit_Online_Coures_Action", method = RequestMethod.POST)
	public ModelAndView edit_Online_Coures_Action(@ModelAttribute("edit_OnlinCouresCMD") EDU_LMS_FREE_COURSE rs,BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,MultipartHttpServletRequest mul,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws IOException, ParseException {

		 if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Online_Coures_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String username = session.getAttribute("username").toString();
		int id = Integer.parseInt(request.getParameter("id"));
		String coursename = request.getParameter("coursename");
		String upload_file = gd(request, mul, session, "upload_file");
		String hid_upload_file = request.getParameter("hid_upload_file");
		
		if (upload_file=="") {
			upload_file = hid_upload_file;
		}
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String url = request.getParameter("url");
		String description = request.getParameter("description");
		
		
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		
		if (coursename == null || coursename.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Name.");
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		
		if (validation.maxlengthcheck(coursename) == false) {
			ra.addAttribute("msg", "Course Name " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		if (validation.isOnlyAlphabetDASH(coursename) == false) {
			ra.addAttribute("msg", "Course Name " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		if (upload_file.isEmpty()) {
			ra.addAttribute("msg", "Please Upload File.");
			return new ModelAndView("redirect:Online_Coures_Url");
		}
		if (start_date == null || start_date.trim().equals("") || start_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select Start Date");
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		if (end_date == null || end_date.trim().equals("") || end_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select End Date");
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		if (url == null || url.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Url.");
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		
		if (description == null || description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Description.");
			return new ModelAndView("redirect:Edit_Online_Coures_Url");
		}
		
	
		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_FREE_COURSE where coursename=:coursename and upload_file=:upload_file and start_date=:start_date and end_date=:end_date and url=:url and description=:description and id !=:id");
			q0.setParameter("coursename", coursename);

			q0.setParameter("upload_file", upload_file);
			q0.setParameter("start_date", start_date);
			q0.setParameter("end_date", end_date);
			q0.setParameter("url", url);
			q0.setParameter("description", description);
			

			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_FREE_COURSE set coursename=:coursename,upload_file=:upload_file,start_date=:start_date,end_date=:end_date,"
						+ "url=:url,description=:description, modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("coursename", coursename).setParameter("upload_file", upload_file)
						.setParameter("start_date", start_date).setParameter("end_date", end_date)
						.setParameter("url", url).setParameter("description", description)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
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
		
		return new ModelAndView("redirect:Online_Coures_Url");
	}
	
	//=====================================================================================================//
	
	
	@PostMapping(value = "/deleteonlinecourse_Url")
	public ModelAndView deleteonlinecourse_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		EDU_LMS_FREE_COURSE INF = (EDU_LMS_FREE_COURSE) session.get(EDU_LMS_FREE_COURSE.class, id);

		List<String> liststr = new ArrayList<String>();

		try {

			session.delete(INF);
			tx.commit();
			session.close();
			liststr.add("Data Deleted Successfully.");
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Online_Coures_Url");
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/getDownloadPdfonlineCourse")
	public ModelAndView getDownloadPdfonlineCourse(@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
			
			ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException {


		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";

		EXTERNAL_FILE_PATH = free_CourseDao.getFilePathQueryForDocFile(Integer.parseInt(doc_id1));
		
		if (EXTERNAL_FILE_PATH != "") {
			File file = null;
			file = new File(EXTERNAL_FILE_PATH);
			try {
				if (!file.exists()) {
					
					model.put("msg", "Sorry.The file you are looking for does not exist!");
					return new ModelAndView(url);
				}
			} catch (Exception exception) {
				
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
				//e.printStackTrace();
			}
		} 
		return new ModelAndView(url);
	}
}
