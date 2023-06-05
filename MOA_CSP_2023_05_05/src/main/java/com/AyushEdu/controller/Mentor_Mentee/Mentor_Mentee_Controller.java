package com.AyushEdu.controller.Mentor_Mentee;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Mentor_Mentee.EDU_MEN_MENTOR_MENTEE_REQUEST;
import com.AyushEdu.Models.Mentor_Mentee.EDU_Mentor_Mentee_communication;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Mentor_Mentee.Mentor_Mentee_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Mentor_Mentee_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Mentor_Mentee_DAO mdao;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Search_Mentor_Url", method = RequestMethod.GET)
	public ModelAndView Department_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Mentor_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		

//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		Mmap.put("Alumnibatch",edao.GetAlumnibatchdata(userid));
		
		return new ModelAndView("Search_Mentor_Tiles");
	}
	
	@RequestMapping(value = "/getSearchMentor", method = RequestMethod.POST) 
	public @ResponseBody List<Map<String, Object>> getSearchMentor(HttpSession session,String a) {
		
       
       String userid = session.getAttribute("userId_for_jnlp").toString();
       
       String role = session.getAttribute("role").toString();
       
       String system = mdao.getSystemofStudent(userid,role).get(0).get("system").toString();

//       System.err.println("============="+system);

       if (a != null && !a.trim().equals("")) {
    	   return mdao.getSearchMentorDetails(a,system);
       }else {
    	   return null;
       }
	}

	
	
	@RequestMapping(value = "/getmentor_mentee_name", method = RequestMethod.POST)
	public @ResponseBody String getmentor_mentee_name(SessionFactory sessionFactory,RedirectAttributes ra,EDU_MEN_MENTOR_MENTEE_REQUEST td,BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession Session, Principal principal) {

		
		String msg="";
		String faculty_user_id = request.getParameter("faculty_user_id");
		
		String userid = Session.getAttribute("userId_for_jnlp").toString();
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_MEN_MENTOR_MENTEE_REQUEST where faculty_user_id=:faculty_user_id and student_user_id=:student_user_id  and status=:status and id !=:id")
					.setParameter("faculty_user_id", Integer.parseInt(faculty_user_id))
					.setParameter("student_user_id", Integer.parseInt(userid))
					.setParameter("status", 0)
					.setParameter("id", id).uniqueResult();
				
//			System.err.println("c------"+c+"---id---"+id);
					
			if (id == 0) {
				td.setFaculty_user_id(Integer.parseInt(faculty_user_id));
				td.setStudent_user_id(Integer.parseInt(userid));
				td.setStatus(0);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					msg="Request Sent Successfully.";
					
				} else {
					msg="You have Already Requested to this Mentor";
				}

				
			} 
			tx.commit();
			sessionHQL.close();
			System.err.println("check msg"+msg);
			return msg;	
	
		
		
		
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
	
	}
	

	@PostMapping("/getsearch_Mentor_Mentee_data")
	public @ResponseBody List<Map<String, Object>> getsearch_Mentor_Mentee_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String login_name,HttpSession Session) {
		String stu_user_id = Session.getAttribute("userId_for_jnlp").toString();
		String role_id = Session.getAttribute("roleid").toString();
		return mdao.DataTableMentorDataList(startPage, pageLength, Search, orderColunm, orderType, login_name,stu_user_id,role_id);

	}

	@PostMapping("/getTotalsearch_Mentor_Mentee_dataCount")
	public @ResponseBody long getTotalsearch_Mentor_Mentee_dataCount(HttpSession sessionUserId, String Search, String login_name, String faculty_user_id) {
		String stu_user_id = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return mdao.DataTableMentorDataTotalCount(Search, login_name,faculty_user_id,stu_user_id);
		
	}
	
	@RequestMapping(value = "/getAskQueryMethodforcommunication" , method = RequestMethod.POST)
	public @ResponseBody String getAskQueryMethodforcommunication(String faculty_user_id,
			String student_user_id,String message,String role_id,HttpSession session,@RequestParam(value = "file_input", required = false) MultipartFile file_input) throws ParseException, IOException {
		
		
		System.err.println("ROLE---"+session.getAttribute("roleid").toString());
		
		String to_role_id = role_id;
		 
		String from_role_id = session.getAttribute("roleid").toString();
		
		String msg = "";
		int sid = 0;
		String username = session.getAttribute("username").toString();
		 
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		System.err.println("faculty_user_id-"+faculty_user_id+"\n-student_user_id-"+student_user_id+"\n-message-"+message);
		
		//SECURITY-----
			long filesize = file_input.getSize() / 1024;
			if (filesize > 200) {
				msg = "File size should be 200 kb or less";
				return msg;
			}
		//SECURITY-----
		
		try {
			EDU_Mentor_Mentee_communication sd =  new EDU_Mentor_Mentee_communication();
			
			sd.setCreated_by(username);
			sd.setCreated_date(new Date());
			sd.setFaculty_user_id(Integer.parseInt(faculty_user_id));
			sd.setStudent_user_id(Integer.parseInt(student_user_id));
			sd.setMessage(message);
			sd.setStatus(0);
			sd.setFrom_msg(Integer.parseInt(from_role_id));
			sd.setTo_msg(Integer.parseInt(to_role_id));
			if(!file_input.getOriginalFilename().equals("") && file_input.getOriginalFilename() != null) {
				
				 if (file_input.getOriginalFilename().split("[.]").length > 2) {
						msg = "Invalid file extension for Document";
						return msg;
				}
				
				
				String mmfile = common.fileupload(file_input.getBytes(), file_input.getOriginalFilename(),1, "mmfile");
				sd.setFile(mmfile);
			}
			
			sid = (int)sessionHQL.save(sd);
			sessionHQL.flush();
			sessionHQL.clear();
	
			if(sid > 0) {
				msg="Query Sent Successfully";
			}
			
			tx.commit();
			return msg;
		} catch (RuntimeException e) {
			try {
				msg="roll back transaction";
			} catch (RuntimeException rbe) {
				msg="Couldn't roll back transaction";
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@PostMapping("/getMessagesformenters")
	public @ResponseBody List<Map<String, Object>> getMessagesformenters(HttpSession Session,String faculty_user_id) throws ParseException {
		String student_user_id = Session.getAttribute("userId_for_jnlp").toString();
		String role_id = Session.getAttribute("roleid").toString();
		return mdao.getMesgsformenties(faculty_user_id,student_user_id,role_id);        
	}
	
		//------------------------------- Used For File Download function  encrypt id ---------------------------
				@SuppressWarnings("null")
				@RequestMapping(value = "/getDownloadPdfUrlforMMfile")
				public ModelAndView getDownloadPdfUrlforMMfile(@RequestParam(value = "msg", required = false) String msg,
						@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl1") String pageUrl,
						ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
						throws IOException {

					String url = pageUrl;
					String EXTERNAL_FILE_PATH = "";

					EXTERNAL_FILE_PATH = mdao.getFilePathQuery(Integer.parseInt(doc_id1));
					
					if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
						File file = null;
						file = new File(EXTERNAL_FILE_PATH);
						try {
							if (!file.exists()) {
								model.put("msg", "Sorry.The file you are looking for does not exist!");
								return new ModelAndView("redirect:"+url);
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
							return new ModelAndView("redirect:"+url);
						} catch (FileNotFoundException e) {
							//e.printStackTrace();
						}
					} 
					
					
					else {
						model.put("msg", "Sorry.The file you are looking for does not exist!");
						return new ModelAndView("redirect:"+url);
					}
					return new ModelAndView("redirect:"+url);
				}
				

	
}

	
