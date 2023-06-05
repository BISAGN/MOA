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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Models.Registration.TB_EXPERIENCE_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.TotalExperienceDao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Experience_Controller {
	
	@Autowired
	TotalExperienceDao expdao;
	
	@Autowired
	personaldetailsDAO da;
	
	CommonController comMstr = new CommonController();
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@GetMapping(value = "Total_Exp_Url")
	public ModelAndView Total_Exp_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("tp_eid") String tp_eid, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Total_Exp_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		System.err.println("tp_eid--------INNNNNNNNNNN----------------");
		String username = principal.getName();
		model.addAttribute("userid",da.getUsername(username));
		model.addAttribute("msg", msg);
		System.err.println("tp_eid------------------------"+tp_eid);
		if (tp_eid.equals("0")) {
			return new ModelAndView("redirect:Personal_Details_Url");
		}else {
			model.addAttribute("tp_eid", tp_eid);
			return new ModelAndView("Total_Exp_Tile", "Exp_detcmd", new TB_EXPERIENCE_DETAILS());
		}
	}
	
	@PostMapping(value = "/Exp_detAction")
	public ModelAndView Exp_detAction(
			@Validated @ModelAttribute("Exp_detcmd") TB_EXPERIENCE_DETAILS rs,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException, ParseException {

//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Total_Exp_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		int id = rs.getId() > 0 ? rs.getId() : 0;
		
		Date date = new Date();
		String username = principal.getName();
		int id1 = Integer.parseInt(request.getParameter("id_org"));
//		int id1 = rs.getId();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

		String exp_emp_name = request.getParameter("exp_emp_name");
		String exp_emp_fromdate = request.getParameter("exp_emp_fromdate");
		String exp_emp_todate = request.getParameter("exp_emp_todate");
	//	String exp_emp_document = request.getParameter("exp_emp_document");
		String p_id = request.getParameter("p_id");
		
			if (exp_emp_name == null || exp_emp_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Name of Employer");
			return new ModelAndView("redirect:Total_Exp_Url");
			}
		
			if (exp_emp_fromdate == null || exp_emp_fromdate.trim().equals("") || exp_emp_fromdate.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Select From Date for Period of Employment");
			return new ModelAndView("redirect:Total_Exp_Url");
			}
			
			if (exp_emp_todate == null || exp_emp_todate.trim().equals("") || exp_emp_todate.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Select To Date for Period of Employment");
				return new ModelAndView("redirect:Total_Exp_Url");
				}
		
			String extension=""; //add line
			String fname = ""; //add line
			
			request.getSession().setAttribute("exp_emp_document", "/srv"+ File.separator + "Experience_Document");
			
			MultipartFile file = mul.getFile("exp_emp_document");
			
			//SECURITY-----
			if (file.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg", "Please Upload Document");
				return new ModelAndView("redirect:Total_Exp_Url");
			}
			if (!file.getOriginalFilename().isEmpty()) {
				
				
				 if (file.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:Total_Exp_Url");
				}
				
				
				
				
				String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("pdf")) {
					ra.addAttribute("msg", "Only .pdf file extensions allowed for Document");
					return new ModelAndView("redirect:Total_Exp_Url");
				}
				long filesize = file.getSize() / 1024;
				if (filesize > 200) {
					ra.addAttribute("msg", "File size should be 200 kb or less Document");
					return new ModelAndView("redirect:Total_Exp_Url");
				}
			}
			//SECURITY-----
			
			if (!file.getOriginalFilename().isEmpty()) {
				
				byte[] bytes = file.getBytes();
				String  mnhFilePath = session.getAttribute("exp_emp_document").toString();
				
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
				fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
				
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();
		
			}else {
				rs.setExp_emp_document(request.getParameter("document_hidd"));
			}
			
			try {
				
				Query q0 = sessionHQL.createQuery(
						"select count(id) from TB_EXPERIENCE_DETAILS where upper(exp_emp_name)=:exp_emp_name");
				
				q0.setParameter("exp_emp_name", exp_emp_name.toUpperCase());
				
				Long c = (Long) q0.uniqueResult();
			
			if (id1 == 0) {
				rs.setCreated_by(username);
				rs.setCreated_date(date);
			
				if (c == 0) {
					
					rs.setExp_user_id(01);
					rs.setExp_emp_document(fname);
					rs.setExp_emp_name(exp_emp_name.toUpperCase());
					rs.setExp_emp_fromdate(comMstr.convertStringToDate(exp_emp_fromdate));
					rs.setExp_emp_todate(comMstr.convertStringToDate(exp_emp_todate));
					
					rs.setP_id(Integer.parseInt(p_id));
					
					sessionHQL.save(rs);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
			
			}else {
				ra.addAttribute("msg", "Data already Exist.");
		}
					
			}else {
				rs.setExp_emp_fromdate(comMstr.convertStringToDate(exp_emp_fromdate));
				rs.setExp_emp_todate(comMstr.convertStringToDate(exp_emp_todate));
					rs.setModified_by(username);
					rs.setModified_date(date);
				
					rs.setId(id1);
					String msg = expdao.updateSubCategory(rs);
					if (msg == "Data Updated Successfully") {
						ra.addAttribute("msg", msg);
					} else {
						model.put("msg", msg);
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
		return new ModelAndView("redirect:Total_Exp_Url");
	}
	
	@PostMapping("/getFilterExperience_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterExperience_data(ModelMap model, Principal principal,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,
			String exp_emp_document,HttpSession sessionUserId) {

		String username = principal.getName();
		int userid = da.getUsername(username);
		
		return expdao.DataTableExperienceDataList(startPage, pageLength, Search, orderColunm, orderType,exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);

	}

	@PostMapping("/getTotalExperience_dataCount")

	public @ResponseBody long getTotalExperience_dataCount(ModelMap model, Principal principal,HttpSession sessionUserId, 
			String Search, String exp_emp_name,String exp_emp_fromdate,String exp_emp_todate,String exp_emp_document) {

		String username = principal.getName();
		int userid = da.getUsername(username);
		
		return expdao.DataTableExperienceDataTotalCount(Search,exp_emp_name, exp_emp_fromdate, exp_emp_todate, exp_emp_document,userid);

	}

	///////////////-----------------for date
	
	@PostMapping("/getDate_data")

	public @ResponseBody ArrayList<ArrayList<String>> getDate_data( Principal principal,String exp_emp_fromdate,String exp_emp_todate) {
		
		String username = principal.getName();
		int userid = da.getUsername(username);
		
		return expdao.DataFor_DateDataList( exp_emp_fromdate, exp_emp_todate, userid);
	}
	
	//------------------------------- Used For File Download function  encrypt id ---------------------------
	@SuppressWarnings("null")
	@RequestMapping(value = "/getDownloadPdfUrlforexp_Doc")
	public ModelAndView getDownloadPdfUrlforexp_Doc(@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("document_id1") String document_id1, @ModelAttribute("pageUrl") String pageUrl,
			
			ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException {

//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Total_Exp_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";

		EXTERNAL_FILE_PATH = expdao.getFilePathQuery(Integer.parseInt(document_id1));
		
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
	
	////////////////////////////delete
	
	@PostMapping(value = "/delete_experience")
	public @ResponseBody ModelAndView delete_experience(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		List<String> liststr = new ArrayList<String>();
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Total_Exp_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		
		try {
			Session sessionHQL = this.sessionFactory.openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "delete from TB_EXPERIENCE_DETAILS where id=:id";
			 
		@SuppressWarnings({ "rawtypes", "deprecation" })
			int app = sessionHQL.createQuery(hqlUpdate)
			.setParameter("id", id).executeUpdate();
//			.setString("modified_by", username)
//			.setDate("modified_date", new Date()).
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
		return new ModelAndView("redirect:Total_Exp_Url");
	}
		
		public String currentDateWithTimeStampString() {
			java.util.Date date = new java.util.Date();
			Timestamp ts = new Timestamp(date.getTime());
			return ts.toString().replace("-", "_").replace(":", "_").replace(" ", "_").replace(".", "_");
		}
		
		@RequestMapping(value = "/Policy_View_Url",method= RequestMethod.GET)
		public ModelAndView Policy_View_Url(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpServletRequest request) {
			//int userid = Integer.parseInt(session.getAttribute("userId").toString());
			//String  roleid = session.getAttribute("roleid").toString();

//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//			}
//			model.put("msg", msg);		

			return new ModelAndView("Policy_View_Tile");	
		}
}