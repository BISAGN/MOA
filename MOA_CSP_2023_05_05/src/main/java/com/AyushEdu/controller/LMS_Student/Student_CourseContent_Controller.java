package com.AyushEdu.controller.LMS_Student;

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
import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDNT_COURSE_CONTENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Student.Student_Course_Content_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Student_CourseContent_Controller {

	@Autowired
	Student_Course_Content_Dao Cdao;

	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@RequestMapping(value = "/admin/StudentCoursecontentUrl", method = RequestMethod.GET)
	public ModelAndView CoursecontentUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentCoursecontentUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		Mmap.put("msg", msg);
		Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
		Mmap.put("module_name", common.getModuleName(sessionFactory));

		return new ModelAndView("StudentCoursecontentTiles");
	}

	@PostMapping(value = "/StudentCourseAction")
	public ModelAndView Module_action(@Validated @ModelAttribute("StudentcourseCMD") EDU_LMS_STUDNT_COURSE_CONTENT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentCoursecontentUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String course_name = request.getParameter("course_name");
		String module_name = request.getParameter("module_name");
		String ref_video = gd(request, mul, session, "ref_video");
		String upload_file = dg(request, mul, session, "upload_file");
		String role = session.getAttribute("role").toString();

		Date from_date = null;
		if (!request.getParameter("from_date").equals("")) {
			String l[] = request.getParameter("from_date").split("/");
			String from_date_i = l[2] + "-" + l[1] + "-" + l[0];
			from_date = format.parse(from_date_i);
		} else {
			from_date = null;
		}

		try {

			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_STUDNT_COURSE_CONTENT where  upper(course_name)=:course_name and upper(module_name)=:module_name and id !=:id")
					.setParameter("course_name", course_name.toUpperCase())
					.setParameter("module_name", module_name.toUpperCase()).setParameter("id", id).uniqueResult();
			if (id == 0) {
				td.setFrom_date(from_date);
				td.setRef_video(ref_video);
				td.setUpload_file(upload_file);
				td.setCreated_by(username);
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
		return new ModelAndView("redirect:StudentCoursecontentUrl");
	}

	public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "VideoPath");

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

	public String dg(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
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

	@PostMapping("/getFilterStudent_course_data")

	public @ResponseBody ArrayList<Map<String, Object>> getFilterStudent_course_data(HttpSession session, int startPage,
			int pageLength,

			String Search, String orderColunm, String orderType, String course_name, String module_name,
			String ref_video, String upload_file, String app_status) {
		String role = session.getAttribute("role").toString();

		return (ArrayList<Map<String, Object>>) Cdao.Student_Course_nameDataList(startPage, pageLength, Search,
				orderColunm, orderType, course_name, module_name, ref_video, upload_file, app_status, role);

	}

	@PostMapping("/getTotalStudent_course_dataCount")
	public @ResponseBody long getTotalStudent_course_dataCount(HttpSession sessionUserId, String Search,
			String course_name, String module_name, String ref_video, String upload_file, String app_status) {

		String role = sessionUserId.getAttribute("role").toString();
		return Cdao.DataTotalCount(Search, course_name, module_name, ref_video, upload_file, app_status, role);

	}

	@PostMapping(value = "/deleteStudentcourse_Url")
	public ModelAndView deleteStudentcourse_Url(@ModelAttribute("id2") int id, String username) {
		int rowCount = 0;
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String hql = "delete from EDU_LMS_STUDNT_COURSE_CONTENT where id=:id";
			@SuppressWarnings("deprecation")
			Query q = session.createQuery(hql).setInteger("id", id);

			rowCount = q.executeUpdate();

			tx.commit();
			session.close();
		} catch (Exception e) {

		}
		if (rowCount > 0) {
			return new ModelAndView("redirect:StudentCoursecontentUrl");
		} else {
			return new ModelAndView("redirect:StudentCoursecontentUrl");
		}

	}

//Student	course approve 

	@RequestMapping(value = "Student_Course_Approve_url", method = RequestMethod.POST)
	public ModelAndView Student_Course_Approve_url(@ModelAttribute("Acceptid") int id, HttpSession session,
			ModelMap model, TB_COURSE_CONTENT_MSTR obj) {

		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from EDU_LMS_STUDNT_COURSE_CONTENT set app_status='1',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();

		tx.commit();
		sessionHQL.close();
		if (app2 > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Not Approved.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("StudentCoursecontentTiles");
	}

	@RequestMapping(value = "Student_Course_Reject_url", method = RequestMethod.POST)
	public ModelAndView Student_Course_Reject_url(@ModelAttribute("Rejectid") int id, HttpSession session,
			ModelMap model, TB_COURSE_CONTENT_MSTR obj) {

		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date date = new Date();

		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from EDU_LMS_STUDNT_COURSE_CONTENT set app_status='2',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();

		tx.commit();
		sessionHQL.close();
		if (app2 > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Not Reject.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("StudentCoursecontentTiles");

	}

}
