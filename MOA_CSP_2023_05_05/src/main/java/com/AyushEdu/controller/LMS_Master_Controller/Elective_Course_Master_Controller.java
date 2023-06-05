package com.AyushEdu.controller.LMS_Master_Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import com.AyushEdu.Core_Directory.Degree_Master_CD_DAO;
import com.AyushEdu.Core_Directory.Elective_Course_Master_CD_DAO;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Elective_Course_Master_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Elective_Course_Master_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CommonController com;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	Elective_Course_Master_CD_DAO  DM_dirdao;
	@Autowired
	ValidationController validation = new ValidationController();
	
	@Autowired
	CommonController common;
	
	@Autowired
	Elective_Course_Master_DAO  Edao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@GetMapping(value = "/elective_course_master_url")
	public ModelAndView elective_course_master_url(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) {
		try {
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("elective_course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		 model.addAttribute("msg", msg);
		 model.put("getDegreeList", common.getDegreeList(sessionFactory));
		 model.put("getCourseList", common.getCourseList(sessionFactory));
		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("elective_course_master_Tiles");
	
}
	
	
	@PostMapping(value = "/Elective_Course_Master_action")	
	public ModelAndView Elective_Course_Master_action(@Validated @ModelAttribute("Elective_Course_Master_cmd") EDU_LMS_ELECTIVE_COURSE_MASTER td,
			BindingResult result,HttpServletRequest request,MultipartHttpServletRequest mul, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("elective_course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Map<String,String> mObj=new HashMap<>();
		
		String course_name = request.getParameter("course_name");
		String status = request.getParameter("status");
		String course_description = request.getParameter("course_description");
		String degree_id = request.getParameter("degree_id");
		String semester_id = request.getParameter("semester_id");
		//start
		String upload_img = gd(request, mul, session,"upload_img");
		String demo_video = gd(request, mul, session,"demo_video");
		//end
		
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (course_name == null || course_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (validation.maxlengthcheck100(course_name) == false) {
			ra.addAttribute("msg","Course Name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:elective_course_master_url");
			
		}

		if (upload_img == null || upload_img.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload Image.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		
		if (semester_id == null || semester_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Semester.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		
		if (demo_video == null || demo_video.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload Demo Video.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		
		if (course_description == null || course_description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Description.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
//		if (validation.isOnlyAlphabetDASH(course_description) == false) {
//			ra.addAttribute("msg", "Course Description " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:elective_course_master_url");
//		}
		if (validation.maxlengthcheck500(course_description) == false) {
			ra.addAttribute("msg","Course Description "+ validation.MaxlengthcheckMSG500);
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:elective_course_master_url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_ELECTIVE_COURSE_MASTER where course_name=:course_name and  "
					+ "degree_id=:degree_id and semester_id=:semester_id  and id !=:id")
//					.setParameter("status", td.getStatus().toUpperCase())
//					.setParameter("upload_img", td.getUpload_img())
					.setParameter("degree_id", td.getDegree_id())
					.setParameter("course_name", td.getCourse_name())
					.setParameter("semester_id", td.getSemester_id())
//					.setParameter("course_description", td.getCourse_description())
//					.setParameter("demo_video", td.getDemo_video())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
//				td.setSemester_id(Integer.parseInt(semester_id));
				td.setDemo_video(demo_video);
				td.setCourse_description(course_description);
				td.setUpload_img(upload_img);
				td.setStatus(status);
				
				td.setCreated_by(username);
				td.setCreated_date(date);
				
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					TB_NOTIFICATION noti = new TB_NOTIFICATION();
					
					List<String> instList = com.getInstListFromSystem((String.valueOf( td.getDegree_id())));
					String allinst = "";
//					System.err.println("INST LIST---1-"+instList);
					for(int i=0;i<instList.size();i++) {
						if(i==0) {
							 allinst = instList.get(i);
						}else {
						 	allinst = allinst+","+instList.get(i);
						}
					}
//					System.err.println("INST LIST---2-"+allinst);
					noti.setCreated_by(username);
					noti.setCreated_date(date);
					noti.setStatus("0");
					noti.setMessage("New Course Publish");
					noti.setFrom_name_send(username);
					noti.setTo_name_sent(allinst+",-1");
					sessionHQL.save(noti);
//					 idd = (int)sessionHQL.save(noti);
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
			//For Core Directory
			DM_dirdao.Insert_Ele_Course_Mstr_Data(idd);
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

		return new ModelAndView("redirect:elective_course_master_url");
	}
	
	
	@PostMapping("/getFilter_Ele_Course_master_data")

	public @ResponseBody List<Map<String, Object>> getFilter_Ele_Course_master_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String course_name,String upload_img, String status, String degree_id, String semester_id, String demo_video) {
		

		return Edao.DataTableEle_Course_masterDataList(startPage, pageLength, Search, orderColunm, orderType, course_name,upload_img, status,degree_id, semester_id, demo_video);

	}

	@PostMapping("/getTotalEle_Course_master_dataCount")

	public @ResponseBody long getTotalEle_Course_master_dataCount(HttpSession sessionUserId, String Search, String course_name,String upload_img,String status,String degree_id,String semester_id,String demo_video) {
		return Edao.DataTableEle_Course_masterDataTotalCount(Search, course_name, upload_img,status,degree_id,semester_id, demo_video);

	}
	
	@RequestMapping(value = "/ElectDemovideoplay")
	public void ElectDemovideoplay(@ModelAttribute("Id") String id,
			@ModelAttribute("fildname") String fildname, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int BUFFER_SIZE = 4096;
		String filePath="";
		
		System.err.println("id--->  "+id);
		
		filePath = Edao.getdemoVideoPath(Integer.parseInt(id));
		System.err.println("FILE_PATH----"+filePath);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();

		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
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

			String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
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
		
	
	
	@PostMapping(value = "/delete_ele_course_mstr_Url")
	public ModelAndView delete_ele_course_mstr_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("elective_course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_LMS_ELECTIVE_COURSE_MASTER set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Ele_Coursee_Mstr_Data(id);  
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}finally {
			
		}
		return new ModelAndView("redirect:elective_course_master_url");
	}
	
	
	@RequestMapping(value = "/Edit_ele_course_mstrUrl")
	public ModelAndView Edit_ele_course_mstrUrl(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg,HttpSession sessionEdit,HttpServletRequest request) {

		if(request.getHeader("Referer") == null ) { 
	//		sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("elective_course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		EDU_LMS_ELECTIVE_COURSE_MASTER Edit_ele_course_mstr_Details = Edao.getEle_CourseByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("Edit_ele_course_mstr_Details", Edit_ele_course_mstr_Details);
		Mmap.put("getCourseList", common.getCourseList(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("edit_elective_course_master_Tiles");
	}
	
	
	@RequestMapping(value = "/Edit_Elective_Course_Master_action", method = RequestMethod.POST)
	public ModelAndView Edit_Elective_Course_Master_action(@ModelAttribute("Edit_Elective_Course_Master_cmd") EDU_LMS_ELECTIVE_COURSE_MASTER rs, BindingResult result,
			HttpServletRequest request,MultipartHttpServletRequest mul, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws IOException {	
		
		String username = session.getAttribute("username").toString();
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("elective_course_master_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		

		int id = Integer.parseInt(request.getParameter("id"));
		
		System.err.println("id---->   "+id);
		String course_name = request.getParameter("course_name").trim();
		String course_description = request.getParameter("course_description").trim();
		String degree_id = request.getParameter("degree_id");
		String semester_id = request.getParameter("semester_id");
		
		//start
		String upload_img = gd(request, mul, session,"upload_img");
		//end
		String upload_img_hid = request.getParameter("upload_img_hid").trim();
		
		String demo_video = gd(request, mul, session,"demo_video");
		String hid_demo_video = request.getParameter("hid_demo_video").trim();
		
		
		System.err.println("hid_demo_video-------->   "+hid_demo_video);
		
		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (course_name == null || course_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (validation.maxlengthcheck100(course_name) == false) {
			ra.addAttribute("msg","Course Name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:elective_course_master_url");
			
		}

		if (upload_img == null || upload_img.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Upload Image.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (semester_id == null || semester_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Semester.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		
		if (course_description == null || course_description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Course Description.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (validation.maxlengthcheck500(course_description) == false) {
			ra.addAttribute("msg","Course Description "+ validation.MaxlengthcheckMSG500);
			return new ModelAndView("redirect:elective_course_master_url");
		}
//		if (validation.isOnlyAlphabetDASH(course_description) == false) {
//			ra.addAttribute("msg", "Course Description " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:elective_course_master_url");
//		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:elective_course_master_url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:elective_course_master_url");
		}

		
		
		if (upload_img.trim().equals("")) {
			upload_img = upload_img_hid;
			}
		try {
//			Query q0 = session1.createQuery(
//					"select count(id) from EDU_LMS_ELECTIVE_COURSE_MASTER where course_name=:course_name and upload_img=:upload_img and status=:status and id !=:id");
//			q0.setParameter("course_name", course_name);
//			
//			q0.setParameter("upload_img", upload_img);
//
//			q0.setParameter("status", status);
//
//			q0.setParameter("id", id);
//
//			Long c = (Long) q0.uniqueResult();
//
//			if (c == 0) {
				String hql = "update EDU_LMS_ELECTIVE_COURSE_MASTER set course_name=:course_name,upload_img=:upload_img,course_description=:course_description,degree_id=:degree_id,semester_id=:semester_id,status=:status,modified_by=:modified_by,modified_date=:modified_date,demo_video=:demo_video"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("course_name", course_name).setParameter("upload_img", upload_img)
						.setParameter("course_description", course_description)
						.setParameter("degree_id", rs.getDegree_id())
						.setParameter("semester_id", rs.getSemester_id())
						.setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("demo_video", demo_video)
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();
				//For Core Directory
				DM_dirdao.Update_Ele_Course_Mstr_Data
				( id,  course_name,course_description, upload_img, degree_id, semester_id, demo_video,  status
						,  username,  new Date());
				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
//			} else {
//				ra.addAttribute("msg", "Data already Exist.");
//			}

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
		
		return new ModelAndView("redirect:elective_course_master_url");
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

@RequestMapping(value = "/MedicalImagePath", method = RequestMethod.GET)
public void MedicalImagePath(@ModelAttribute("i_id") String id,@ModelAttribute("id4") String myImg, ModelMap model,
		HttpServletRequest request, HttpServletResponse response) throws IOException {
	
//	System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

	final int BUFFER_SIZE = 4096;

	String i_id = id;

	
	String filePath = Edao.getImagePath(i_id);

//	System.out.println("chgukhdfguhkdfhgkj---" + filePath);

	model.put("filePath", filePath);

	ServletContext context = request.getSession().getServletContext();

	try {

		if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//			request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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
	}
}
//end

}
