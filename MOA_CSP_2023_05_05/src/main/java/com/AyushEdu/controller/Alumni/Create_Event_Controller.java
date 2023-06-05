package com.AyushEdu.controller.Alumni;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_EVENT;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_PARTICIPATE;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Alumni_CREATE_EVENT_DAO;
import com.AyushEdu.dao.Alumni.Edit_Profile_Dao;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Create_Event_Controller {

	@Autowired
	 SessionFactory sessionFactory;
	@Autowired
	ValidationController validation;
	@Autowired
	Edit_Profile_Dao TDDAO;
	@Autowired 
	Alumni_CREATE_EVENT_DAO edao;
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@RequestMapping(value = "admin/Create_event_Url", method = RequestMethod.GET)
	public ModelAndView Create_event_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Create_event_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Mmap.put("msg", msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Create_Event_Tiles");
	}


//==========================================SAVE/view//Edit Event========================================== 	

  @PostMapping(value = "/Create_Event_Action") 
public ModelAndView EventAction(@Validated @ModelAttribute("Create_Event_CMD") EDU_ALUM_ALUMNI_EVENT td, BindingResult result,
		HttpServletRequest request, ModelMap model, HttpSession session, 
		RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {
	  
		//SECURITY -- RIDDHI
	  if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Create_event_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

	String title = request.getParameter("title");
	String description = request.getParameter("description");
//	String upload_img = request.getParameter("upload_img_hid");
	String venue = request.getParameter("venue");
	String date_time = request.getParameter("date_time");
	String batch = request.getParameter("batch");
	String eid = request.getParameter("id");
	

//	if (title == null || title.trim().equals("")) {
//		ra.addAttribute("msg", "Please Enter Title");
//		return new ModelAndView("redirect:Create_event_Url");
//	}
//
//	if (validation.maxlengthcheck100(title) == false) {
//		ra.addAttribute("msg","title "+ validation.MaxlengthcheckMSG100);
//		
//		return new ModelAndView("redirect:Create_event_Url");
//		
//	}
	
	if (validation.isOnlyAlphabetNumber(title) == false) {
		ra.addAttribute("msg","Title "+ validation.isOnlyAlphabetNumberMSG);
		return new ModelAndView("redirect:Create_event_Url");
	}

	int id = td.getId() > 0 ? td.getId() : 0;
	if(!eid.equals("0")) {
		id = Integer.parseInt(eid);
	}
	Date date = new Date();

	String userid = session.getAttribute("userId_for_jnlp").toString();

	MultipartFile file = mul.getFile("upload_img");
	
	//SECURITY-----
	if (file.getOriginalFilename().isEmpty()) {
		ra.addAttribute("msg","Please Upload File");
		return new ModelAndView("redirect:Create_event_Url");
	}
	if (!file.getOriginalFilename().isEmpty()) {
		
		 if (file.getOriginalFilename().split("[.]").length > 2) {
			 ra.addAttribute("msg", "Invalid file extension for Document");
				return new ModelAndView("redirect:Create_event_Url");
		}
		
		String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
		if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
			ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
			return new ModelAndView("redirect:Create_event_Url");
		}
		long filesize = file.getSize() / 1024;
		if (filesize > 50) {
			ra.addAttribute("msg","File size should be 50 kb or less");
			return new ModelAndView("redirect:Create_event_Url");
		}
	}
	//SECURITY-----
	String eventimg = common.fileupload(file.getBytes(), file.getOriginalFilename(),1, "eventimg");

	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();

	try {
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  EDU_ALUM_ALUMNI_EVENT where upper(title)=:title and description=:description and venue=:venue and date_time=:date_time and batch=:batch and id !=:id")
				.setParameter("title", td.getTitle().toUpperCase())
				.setParameter("description", td.getDescription().toUpperCase())
				.setParameter("venue", td.getVenue().toUpperCase())
				.setParameter("date_time", td.getDate_time().toUpperCase())
				.setParameter("batch", td.getBatch().toUpperCase())
				.setParameter("id", id).uniqueResult();
		
		if (id == 0) {
			td.setTitle(title);
			td.setDescription(description);
			td.setDescription(description);
			td.setUpload_img(eventimg);
			td.setVenue(venue);
			td.setDate_time(date_time);
			td.setBatch(batch);
			td.setCreated_by(userid);
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
		
		else {
			td.setTitle(title);
			td.setDescription(description);
			td.setDescription(description);
			if(file.getOriginalFilename().equals("")) {
				td.setUpload_img(request.getParameter("upload_img_hid"));
			}else {
				td.setUpload_img(eventimg);
			}
			
			td.setVenue(venue);
			td.setDate_time(date_time);
			td.setBatch(batch);
			td.setModified_by(userid);
			td.setModified_date(date);
			
			if (c == 0) {
				td.setId(id);
				String msg = edao.updateEvent(td);
				ra.addAttribute("msg", msg);
			} else {
				model.put("msg", "Data already Exist");
				ra.addAttribute("msg", "Data already Exist.");
			}
		}
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
	
	return new ModelAndView("redirect:Create_event_Url");
}



//==========================================SAVE/view//Edit//Delete Event========================================== 	



	@PostMapping("/getFiltereventdata")
	public @ResponseBody List<Map<String, Object>> getFiltereventdata(HttpSession session,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String title, String description, String upload_img, String venue, String date_time, String batch) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return edao.DataTableEventDataList(startPage, pageLength, Search, orderColunm, orderType, title, description, upload_img, venue, date_time ,batch,userid);

	}

	@PostMapping("/getTotaleventdataCount")
	public @ResponseBody long getTotaleventdataCount(HttpSession session, String Search, String title, String description, String upload_img, String venue, String date_time, String batch) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return edao.DataTableEventDataTotalCount1(Search, title, description, upload_img, venue, date_time, batch,userid) ;
}


//	@RequestMapping(value = "/Edit_Create_event_Url", method = RequestMethod.POST)
//	public ModelAndView Edit_Create_event_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
//			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, HttpSession sessionEdit) {
//		
////			@RequestParam(value = "title", required = false) String title,
////			@RequestParam(value = "description", required = false) String description,
////			@RequestParam(value = "upload_img", required = false) String upload_img,
////			@RequestParam(value = "venue", required = false) String venue,
////			@RequestParam(value = "date_time", required = false) String date_time,
////			@RequestParam(value = "batch", required = false) String batch,
//			
//			
//		
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		EDU_ALUM_ALUMNI_EVENT Event_Details = edao.getEventByid(Integer.parseInt(updateid));
//		Mmap.addAttribute("msg", msg);
////		Mmap.put("title", title);
////		Mmap.put("description", description);
////		Mmap.put("upload_img", upload_img);
////		Mmap.put("venue", venue);
////		Mmap.put("date_time", date_time);
////		Mmap.put("date_time", date_time);
////		Mmap.put("batch", batch);
//		
//		Mmap.put("Event_Details", Event_Details);
//		Mmap.put("Professionalinfo", edao.getEventByid(Integer.parseInt(updateid)));
//		
//		tx.commit();
//		sessionHQL.close();
//
//		return new ModelAndView("Create_Event_Tiles");
//	}
	
	@RequestMapping(value = "/Create_event_Url_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Create_event_Url_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		//SECURITY -- RIDDHI
		 if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String role = session1.getAttribute("role").toString();
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Create_event_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"delete from EDU_ALUM_ALUMNI_EVENT where id=:id")
					.setParameter("id", id).executeUpdate();

			
			tx.commit();
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
		return new ModelAndView("redirect:Create_event_Url");
	}
	
	@RequestMapping(value = "/admin/alumni_feed", method = RequestMethod.GET)
	public ModelAndView alumni_feed(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		
		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("alumni_feed", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Mmap.put("upcomingevents",edao.Getupcomingeventsdata());
		Mmap.put("Alumnibatch",edao.GetAlumnibatchdata(userid));
		return new ModelAndView("alumni_feed");
	}
	
	@RequestMapping(value = "/admin/alumni_events", method = RequestMethod.GET)
	public ModelAndView alumni_events(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		
		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("alumni_feed", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String event_id = request.getParameter("event_id");
		Mmap.put("event_div",edao.Getevent_divdata(event_id));
		Mmap.put("event_id",event_id);
		return new ModelAndView("alumni_events");
	}
	
//	Integration code
	@PostMapping("/getFeeds")
	public @ResponseBody ArrayList<ArrayList<String>> getFeeds() {
		return edao.getFeedsData();
	}
	
	@PostMapping("/getAlmProfileData")
	public @ResponseBody List<Map<String, Object>> getAlmProfileData(String alm_uid) {
		return TDDAO.GetTechnical_Details_Data(Integer.parseInt(alm_uid));
	}
	



//image code alumni
	
	
	@RequestMapping(value = "/getEventImg", method = RequestMethod.GET)
	public void getEventImg(@ModelAttribute("id") String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;
		
		String filePath = edao.getEventImg(id);
		

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
			
			
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
	
	@RequestMapping(value = "/getFeedImg", method = RequestMethod.GET)
	public void getFeedImg(@ModelAttribute("id") String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;
		
		String filePath = edao.getFeedsImg(id);
		

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
			
			
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
	
	@RequestMapping(value = "/getAlumniProfilephoto", method = RequestMethod.GET)
	public void getAlumniProfilephoto(@ModelAttribute("id") String id, ModelMap model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		

		final int BUFFER_SIZE = 4096;
		
		String filePath = edao.getAlumniPP(id);
		

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
			
			
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
	
	//Count
		@RequestMapping(value = "/getTotaleventinterestedCount", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getTotaleventinterestedCount(String id) {
			List<ArrayList<String>> list = edao.getTotaleventinterestedCount(id);
			return list;
		}
		
		@RequestMapping(value = "/getTotaleventparticipateCount", method = RequestMethod.POST)
		public @ResponseBody List<ArrayList<String>> getTotaleventparticipateCount(String id) {
			List<ArrayList<String>> list = edao.getTotaleventparticipateCount(id);
			return list;
		}
		
		@RequestMapping(value = "admin/getSearch_Event_Master", method = RequestMethod.GET)
		public ModelAndView getSearch_Event_Master(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			try {
				//SECURITY -- RIDDHI
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("getSearch_Event_Master", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				
			Mmap.put("msg", msg);
			Mmap.put("getEvent_List", common.getEvent_List(sessionFactory));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

			return new ModelAndView("Search_Event_participate_Tiles");
		}
		

		@PostMapping("/getFilterSearchEventdata")
		public @ResponseBody ArrayList<ArrayList<String>> getFilterSearchEventdata(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String event_id, String name, String interested, String participating, String event_date) {
			return edao.DataTableSearchEventDataList(startPage, pageLength, Search, orderColunm, orderType, event_id,  name,  interested,  participating,  event_date);
		}

		@PostMapping("/getTotalSearchEventdataCount")
		public @ResponseBody long getTotalSearchEventdataCount(HttpSession sessionUserId, String Search, String event_id, String name, String interested, String participating, String event_date) {
			return edao.DataTableSearchEventDataTotalCount1(Search, event_id,  name,  interested,  participating,  event_date) ;
			
		}
		
		@RequestMapping(value = "/getevent_name", method = RequestMethod.POST)
		public @ResponseBody String getevent_name(SessionFactory sessionFactory,RedirectAttributes ra,EDU_ALUM_ALUMNI_PARTICIPATE td,
				BindingResult result,HttpServletRequest request, ModelMap model, HttpSession Session, Principal principal) {

//			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
//			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Create_event_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			String msg="";
			String event_id = request.getParameter("event_id");
			String cat = request.getParameter("cat");
			
			String userid = Session.getAttribute("userId_for_jnlp").toString();
			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String event = "";
			if (cat.equals("interested")) {
				event = " and interested=:interested ";
			}
			else if (cat.equals("participate")){
				event = " and participate=:participate ";
			}
			
			Query q = sessionHQL.createQuery(
					"select count(id) from EDU_ALUM_ALUMNI_PARTICIPATE where event_id=:event_id and user_id=:user_id" + event);
						q.setParameter("event_id", td.getEvent_id());
						q.setParameter("user_id", Integer.parseInt(userid));
						if (cat.equals("interested")) {
							q.setParameter("interested", 1);
						}
						else if (cat.equals("participate")){
							q.setParameter("participate", 1);
						}
						
						Long c = (Long) q.uniqueResult();
			
				
				if (id == 0) {
					td.setEvent_id(Integer.parseInt(event_id));
					if (cat.equals("interested")) {
						td.setInterested(1);
					}
					else if (cat.equals("participate")){
						td.setParticipate(1);
					}
					td.setUser_id(Integer.parseInt(userid));
					td.setCreated_by(userid);
//					td.setCreated_role(role);
					td.setCreated_date(date);
					if (c == 0) {
						sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						msg="Choice Captured.";
						
					} else {
						msg="Choice had been Captured.";
					}
				} 

			tx.commit();
			sessionHQL.close();
			return msg;
		}
		@RequestMapping(value = "admin/alumni_profile", method = RequestMethod.GET)
		public ModelAndView alumni_profile(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("alumni_profile", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			return new ModelAndView("alumni_profile");
		}
}



