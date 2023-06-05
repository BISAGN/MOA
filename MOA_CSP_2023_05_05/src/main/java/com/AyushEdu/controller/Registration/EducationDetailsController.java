package com.AyushEdu.controller.Registration;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
import com.AyushEdu.Models.Registration.TB_PRE_EDUCATION_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.EducationDetailsDao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class EducationDetailsController {
	
	@Autowired
	EducationDetailsDao edao;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	personaldetailsDAO da;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	CommonController comMstr = new CommonController();
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private HashMap<String, Object> doc_path;

	@GetMapping(value = "Edu_Det_Url")
	public ModelAndView Edu_Det_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("eid") String eid,
			HttpServletRequest request) {

		String username = principal.getName();
//		SECURITY -- RIDDHI 
		if (request.getHeader("Referer") == null) {
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		try {
			model.addAttribute("userid", da.getUsername(username));
			model.addAttribute("msg", msg);
			model.put("getAcademicList", comMstr.getEducationAcademicList(sessionFactory));
			model.put("getresultDivisionList", comMstr.getresultDivisionList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (eid.equals("0")) {
			return new ModelAndView("redirect:Personal_Details_Url");
		} else {
			model.addAttribute("tbpdid", eid);
			return new ModelAndView("Edu_Det_Tiles", "Edu_Det_CMD", new TB_PRE_EDUCATION_DETAILS());
		}
	}
	
	@PostMapping(value = "/Edu_Det_Action")
	public ModelAndView Edu_Det_Action(
			@Validated @ModelAttribute("Edu_Det_CMD") TB_PRE_EDUCATION_DETAILS ed,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		int id = ed.getId() > 0 ? ed.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		int id1 = Integer.parseInt(request.getParameter("id_org"));
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		
			String academic = request.getParameter("academic");
			String passing_year = request.getParameter("passing_year");
		//	String institute_name = request.getParameter("institute_name");
			//String total_marks = request.getParameter("total_marks");
			String obtain_marks = request.getParameter("obtain_marks");
			String grade = request.getParameter("grade");
			String p_id = request.getParameter("p_id");
			String board_or_university = request.getParameter("board_or_university");
			String school_or_collage = request.getParameter("school_or_collage");
			String subject = request.getParameter("subject");
			
			String doc_path_hid = request.getParameter("doc_path_hid");
			
			if (academic == null || academic.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Name of Exam");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (board_or_university == null || board_or_university.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Board/University");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.maxlengthcheck70(board_or_university) == false) {
				ra.addAttribute("msg", "Board/University " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.isOnlyAlphabet(board_or_university) == false) {
				ra.addAttribute("msg", "Board/University " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (school_or_collage == null || school_or_collage.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the School/College");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.maxlengthcheck70(school_or_collage) == false) {
				ra.addAttribute("msg", "School/College " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.isOnlyAlphabet(school_or_collage) == false) {
				ra.addAttribute("msg", "School/College " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (subject == null || subject.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Subject");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.maxlengthcheck50(subject) == false) {
				ra.addAttribute("msg", "Subject " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (validation.isOnlyAlphabet(subject) == false) {
				ra.addAttribute("msg", "Subject " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (passing_year == null || passing_year.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Year of Passing");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (obtain_marks == null || obtain_marks.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Percentage of Marks");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
//			if (validation.isOnlyNumer(obtain_marks) == true) {
//				ra.addAttribute("msg", "Percentage of Marks" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_Url");
//			}
			if (validation.maxlengthcheckneetpercentile(obtain_marks) == false) {
				ra.addAttribute("msg", "Percentage of Marks " + validation.MaxlengthcheckMSGneetpercentile5);
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (grade == null || grade.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Division");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
//			if (institute_name == null || institute_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Institute Name");
//			return new ModelAndView("redirect:Edu_Det_Url");
//			}

			String extension=""; //add line
			String fname = ""; //add line
			
			request.getSession().setAttribute("doc_path", "/srv"+ File.separator + "Document");
			
			MultipartFile file = mul.getFile("doc_path");
			
			//SECURITY-----
			
			if (file.getOriginalFilename().isEmpty()  && doc_path_hid.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload File");
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			if (!file.getOriginalFilename().isEmpty()) {
				 if (file.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:Edu_Det_Url");
				}
				 
				String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("pdf")) {
					ra.addAttribute("msg", "Only .pdf file extensions allowed for Document");
					return new ModelAndView("redirect:Edu_Det_Url");
				}
				long filesize = file.getSize() / 1024;
				if (filesize > 200) {
					ra.addAttribute("msg", "File size should be 200 kb or less Document");
					return new ModelAndView("redirect:Edu_Det_Url");
				}
				
				byte[] bytes = file.getBytes();
				String  mnhFilePath = session.getAttribute("doc_path").toString();
				
	            File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();
				System.out.println("dir "+!dir.exists());		
				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j+1);
				}
				java.util.Date date1= new java.util.Date();
				fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", "").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
				
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
				ed.setDoc_path(fname);
			}else {
				ed.setDoc_path(doc_path_hid);
			}
			//SECURITY-----
			try {
				
				if (Integer.parseInt(academic) > 12) {
					Query qry = sessionHQL.createQuery(
							"select count(id) from TB_PRE_EDUCATION_DETAILS where academic=:academic and p_id=:p_id");
					qry.setParameter("academic", "12");
					qry.setParameter("p_id", Integer.parseInt(p_id));
					Long v = (Long) qry.uniqueResult();

					if (v > 0) {
						Query qrypy = sessionHQL.createQuery(
								"select passing_year from TB_PRE_EDUCATION_DETAILS where academic=:academic and p_id=:p_id");
						qrypy.setParameter("academic", "12");
						qrypy.setParameter("p_id", Integer.parseInt(p_id));
						List<TB_PRE_EDUCATION_DETAILS> x = (List<TB_PRE_EDUCATION_DETAILS>) qrypy.list();
						String py = String.valueOf(x.get(0));

						if ((Integer.parseInt(passing_year) - Integer.parseInt(py)) < 2) {
							ra.addAttribute("msg", "Please Enter the Passing Year graterthen 2 year");
							return new ModelAndView("redirect:Edu_Det_Url");
						}
					}
				}
				
				if (id1 == 0) {

				Query q0 = sessionHQL.createQuery(
						"select count(id) from TB_PRE_EDUCATION_DETAILS where upper(academic)=:academic and p_id=:p_id"
//						+ "and passing_year=:passing_year \n"
//						+ "and upper(institute_name)=:institute_name \n"
//								+ "and obtain_marks=:obtain_marks "
//								+ "and total_marks=:total_marks "
//								+ "and upper(grade)=:grade "
						);
				
				q0.setParameter("academic", academic.toUpperCase());
				q0.setParameter("p_id", Integer.parseInt(p_id));
				//q0.setParameter("passing_year", (Integer.parseInt(passing_year)));
				//q0.setParameter("institute_name", institute_name.toUpperCase());
			//	q0.setParameter("obtain_marks", (Integer.parseInt(obtain_marks)));
//				q0.setParameter("total_marks", (Integer.parseInt(total_marks)));
				//q0.setParameter("grade", grade.toUpperCase());
				
				Long c = (Long) q0.uniqueResult();
				
		
				ed.setCreated_by(username);
				ed.setCreated_date(date);
					if (c == 0) {
						
						ed.setDoc_path(fname);
						ed.setAcademic(academic.toUpperCase());
						ed.setPassing_year(Integer.parseInt(request.getParameter("passing_year")));
						//ed.setInstitute_name(institute_name);
						//ed.setTotal_marks(Integer.parseInt(request.getParameter("total_marks")));
						ed.setObtain_marks(request.getParameter("obtain_marks"));
						ed.setGrade(grade);
						ed.setP_id(Integer.parseInt(p_id));
						sessionHQL.save(ed);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
				}else {
						ra.addAttribute("msg", "Data already Exist.");
				}
			}else {
				
					ed.setModified_by(username);
					ed.setModified_date(date);
				
					ed.setId(id1);
					String msg = edao.updateSubCategory(ed);
					if (msg == "Data Updated Successfully") {
						ra.addAttribute("msg", msg);
					//	model.put("msg", msg);
					} else {
						model.put("msg", msg);
					}
			}
					tx.commit();
				//	ra.addAttribute("msg", "Data Saved Successfully.");
			
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
		return new ModelAndView("redirect:Edu_Det_Url");
	}
	

	@PostMapping("/getFilterEducation_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterEducation_data(ModelMap model, Principal principal,
			int startPage, int pageLength,String Search, String orderColunm, String orderType, String academic,Integer passing_year,String institute_name,Integer obtain_marks,Integer total_marks,
			String grade,String doc_path,HttpSession sessionUserId) {
		
			String username = principal.getName();
			
			int userid = da.getUsername(username);

		return edao.DataTableEducationDataList(startPage, pageLength, Search, orderColunm, orderType,academic,0,institute_name,0,
				0,grade,doc_path,userid);

	}

	@PostMapping("/getTotalEducation_dataCount")
	public @ResponseBody long getTotalEducation_dataCount(ModelMap model, Principal principal,
			HttpSession sessionUserId, String Search, String academic,Integer passing_year,
			String institute_name,Integer obtain_marks,Integer total_marks,String grade,String doc_path) {

		String username = principal.getName();
		int userid = da.getUsername(username);
		
		return  edao.DataTableEducationDataTotalCount(Search,academic,passing_year,institute_name,obtain_marks,total_marks,
				grade,doc_path,userid);

	}
	
	//------------------------------- Used For File Download function  encrypt id ---------------------------
			@SuppressWarnings("null")
			@RequestMapping(value = "/getDownloadPdfUrlforeduDoc")
			public ModelAndView getDownloadPdfUrlforeduDoc(@RequestParam(value = "msg", required = false) String msg,
					@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
					
					ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
					throws IOException {

//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				String url = pageUrl;
				String EXTERNAL_FILE_PATH = "";

				EXTERNAL_FILE_PATH = edao.getFilePathQuery(Integer.parseInt(doc_id1));
				
				if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
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
				
				
				else {
					model.put("msg", "Sorry.The file you are looking for does not exist!");
				}
				return new ModelAndView(url);
			}
			
			////////////////////////////delete
			
			@PostMapping(value = "/delete_education")
		//	@RequestMapping(value = "/delete_education", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_education(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Personal_Details_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				List<String> liststr = new ArrayList<String>();
				String username = session.getAttribute("username").toString();
				
				try {
					Session sessionHQL = this.sessionFactory.openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					 String hqlUpdate = "delete from  TB_PRE_EDUCATION_DETAILS where id=:id";
					 
				@SuppressWarnings({ "rawtypes", "deprecation" })
					int app = sessionHQL.createQuery(hqlUpdate)
					.setParameter("id", id).executeUpdate();
//					.setString("modified_by", username)
//					.setDate("modified_date", new Date()).
					tx.commit();
					sessionHQL.close();

					if (app > 0) {
						liststr.add("Delete Successfully.");
					} else {
						liststr.add("Delete Unsuccessfull");
					}
					model.put("msg",liststr.get(0));

				} catch (Exception e) {
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					model.put("msg",liststr.get(0));
				}
				return new ModelAndView("redirect:Edu_Det_Url");
			}
			
			
			
			 @RequestMapping(value = "/geteditEducation_data_ctrl", method = RequestMethod.POST)
			 	public @ResponseBody ArrayList<ArrayList<String>> geteditEducation_data_ctrl(String id) {
			    	ArrayList<ArrayList<String>> data = edao.geteditEducation_data(id);
			    	return data;
			 	}
			 

			 @RequestMapping(value = "/Getedu_chekyear_nch_ctrl", method = RequestMethod.POST)
			 	public @ResponseBody ArrayList<ArrayList<String>> Getedu_chekyear_nch_ctrl(String p_id) {
			    	ArrayList<ArrayList<String>> data = edao.Getedu_chekyear_nch_data(p_id);
			    	return data;
			 	}
			 
			
}
