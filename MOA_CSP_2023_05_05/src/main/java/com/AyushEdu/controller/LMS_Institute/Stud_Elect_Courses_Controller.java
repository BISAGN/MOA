package com.AyushEdu.controller.LMS_Institute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Stud_Elect_Courses_Controller {
	
    @Autowired
	private SessionFactory sessionFactory;
    
    @Autowired
	CommonController common ;
    
	@Autowired
	Stud_Elect_Courses_Dao sdc;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "/Stud_Elect_Courses_Url")
	public ModelAndView Stud_Elect_Courses_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request){
		
		try {
			
			//SECURITY RAHUL
		
//		if(request.getHeader("Referer") == null ) { 
//			// session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Stud_Elect_Courses_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
			String userId = session.getAttribute("userId_for_jnlp").toString();
			
			String system_id = null;
			if(sdc.getsystem_list(userId)!=null && !sdc.getsystem_list(userId).isEmpty()) {	
				
				system_id = sdc.getsystem_list(userId).get(0).get(0);
			}	
		model.addAttribute("msg", msg);
		model.put("getCourse_Duration", sdc.getCourse_Duration(sessionFactory,userId,system_id));
		model.put("getCourse_Start_Date", sdc.getCourse_Start_Date(sessionFactory,system_id));
//		model.put("getcoursenameList", sdc.getcoursenameList_new(sessionFactory,system_id));
		model.put("getcoursenameList", common.getcoursebystudentEnroll(sessionFactory,userId));
		model.put("getSetList", common.getSetList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Stud_Elect_Courses_Tiles");

	}

	
	
	 @RequestMapping(value = "admin/videoChoose_Ele_Course_Stu")
		public void videoChoose_Ele_Course_Stu(@ModelAttribute("i_id") String id, 
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			final int BUFFER_SIZE = 4096;
			String filePath = sdc.getTopicChoose_Ele_Course_Stu(id);
//			System.out.println("filePath----------------"+filePath);
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
	
	 
		@RequestMapping(value = "/getCourses_Description_fetch_new", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getCourses_Description_fetch_new(HttpSession session,String c_id,String course_duration,String course_start_date,String system_id,String degree_id,String term_id) {
			
			
			String userId = session.getAttribute("userId_for_jnlp").toString();
			String system_id1 = sdc.getsystem_list(userId).get(0).get(0);
			String degree_id1 = sdc.getdegree_list(userId).get(0).get(0);
			//change09
			String term_id1="";
			for(int i=0;i<sdc.getterm_list(userId).size();i++) {
				if(i==0) {
					term_id1= sdc.getterm_list(userId).get(i).get(0);
				}else {
					term_id1+=","+ sdc.getterm_list(userId).get(i).get(0);
				}
			}
			System.out.println("term_id1  "+term_id1);

			return sdc.getCourse_Description_fetch_new(userId,c_id,course_duration,course_start_date,system_id1,degree_id1,term_id1);
		}
		
		
		//-------------------My Courses start code--------------------------//
		
		@RequestMapping(value = "/Image_Fetch_Path_Already_Applied_My_Courses", method = RequestMethod.GET)
		public void Image_Fetch_Path_Already_Applied_My_Courses(@ModelAttribute("i_id") String id,@ModelAttribute("id4") String myImg, ModelMap model,
				HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
			
			String userId = session.getAttribute("userId_for_jnlp").toString();
			String system_id = sdc.getsystem_list(userId).get(0).get(0);

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			
			String filePath = sdc.Already_Applied_Path_fetch_list_My_Courses(i_id,userId,system_id);
			
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
		
		@RequestMapping(value = "/getDescriptionfetchAlreadyAppliedMyCourses", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCourses(HttpSession session,String c_id2,String course_duration2,String course_start_date2,String system_id,String degree_id,String term_id) {
			String userId = session.getAttribute("userId_for_jnlp").toString();
			String system_id2 = sdc.getsystem_list(userId).get(0).get(0);
			String degree_id2 = sdc.getdegree_list(userId).get(0).get(0);
			String term_id2 = sdc.getterm_list(userId).get(0).get(0);

			return sdc.getDescriptionfetchAlreadyAppliedMyCourses_List(userId,c_id2,course_duration2,course_start_date2,system_id2,degree_id2,term_id2);
		}

		
		 @RequestMapping(value = "/ExitFromCourseAction", method = RequestMethod.POST)
		 public @ResponseBody ModelAndView ExitFromCourseAction(@ModelAttribute("id2") String id,HttpSession session,RedirectAttributes ra) {
			 
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			 List<String> liststr = new ArrayList<String>();
			 Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
			 try {
				 
					String hqlUpdatechild ="update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD set status='2' where user_id=:userid and id=:id ";
					int appchild = sessionHQL.createQuery(hqlUpdatechild).setInteger("userid",Integer.parseInt(userid)).setInteger("id",Integer.parseInt(id))
							.executeUpdate();
					
					tx.commit();
					if ( appchild > 0) {
						liststr.add("Exit from Course Successfully");
					} else {
						liststr.add("Exit from Course UnSuccessfully");
					}
					ra.addAttribute("msg", liststr.get(0));
				} catch (RuntimeException e) {
					try {
						tx.rollback();
						ra.addAttribute("msg", "Roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			 return new ModelAndView("redirect:Stud_Elect_Courses_Url");
		}
		 
		 
		 @RequestMapping(value = "/getExitCourse", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>> getExitCourse(String course_category) {

				return sdc.getExitCourse_Switch_Duration(course_category);
			}

		 
		 
		 @RequestMapping(value = "/getExitCourse_count", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>> getExitCourse_count(HttpSession session, String set_id) {
				String userId = session.getAttribute("userId_for_jnlp").toString();
				return sdc.getExitCourse_count(userId, set_id);
			}
}
