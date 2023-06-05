package com.AyushEdu.controller.Registration.Graduation_NCISM;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.AyushEdu.dao.Registration.Graduation_NCISM.DocumentUploadNCISMDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.PersonaldetailsNCISMDAO;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Document_upload_NCISM_controller {
	
	@Autowired
	DocumentUploadNCISMDao docdao;
	
	@Autowired
	Commondao commandao;

	@Autowired
	PersonaldetailsNCISMDAO da;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	CommonController comMstr = new CommonController();
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@GetMapping(value = "/doc_uploadNcism_Url")
	public ModelAndView doc_uploadNcism_Url(ModelMap model,Principal principal, HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@ModelAttribute("doc_eid") String doc_eid, HttpServletRequest request) {
		Session sessionHQL = this.sessionFactory.openSession();
		String username = principal.getName();
		Long v = 0L;
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
	int user = da.getNcism_Username(username);
			
//			if(doc_eid.equals("") || doc_eid.isEmpty() || doc_eid.equals("0")) {
//			ArrayList<ArrayList<String>> listp=da.get_p_id_Ncism_pers_info_data(user);
//			String abc = listp.get(0).get(0);
//			doc_eid = abc;
//			}
			
//			if(!doc_eid.equals("") && !doc_eid.isEmpty() && !doc_eid.equals("0")) {
//				Query qry = sessionHQL.createQuery("select count(*) from EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL where p_id=:p_id and (academic='12' or academic='13')");
//				qry.setParameter("p_id", Integer.parseInt(doc_eid));
//				 v = (Long) qry.uniqueResult();
//				}
			
		model.addAttribute("userid",da.getNcism_Username(username));
//		int user = da.getUsername(username);
//		ArrayList<ArrayList<String>> list=da.getPersonaldetails(user, session);
	//	String abc = list.get(0).get(10);
		//model.put("list",abc);
		
		String screenurl = "Personal_Details_Url";
		model.put("getDoc_nameList", commandao.getDocumentAtchmantlistbyscreen_url(screenurl));
		model.put("getDoc_typeList", comMstr.getDocumenttypeList(sessionFactory));
		model.addAttribute("msg", msg);
		
		}
	 catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (sessionHQL != null) {
			sessionHQL.close();
		}
	}
		
//		if ((doc_eid == null && doc_eid.equals("") && doc_eid.equals("0")) || (v != null && v != 0 && v != 2)) {
//			
//			if (v != 2) {
//				model.addAttribute("msg", "It is Mandatory To fulfill the Details of 10th and 12th ");
//			}
//			return new ModelAndView("redirect:Edu_Det_Ncism_Url");
//		}else {
			model.addAttribute("doc_eid", doc_eid);
			return new ModelAndView("Doc_Upload_Ncism_Tiles");
//		}
	}
	
	@PostMapping(value = "/Doc_Upload_Ncism_Action")
	public ModelAndView Doc_Upload_Action(
			@Validated @ModelAttribute("doc_uploadNcism_CMD") EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		int id = rd.getId() > 0 ? rd.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String name="";
		String p_id = request.getParameter("p_id");
		
		String signature_hid = request.getParameter("signature_hid");
		String photograph_hid = request.getParameter("photograph_hid");
		
		String court_order_hid = request.getParameter("court_order_hid");
		String late_admission_status = request.getParameter("late_admission_status");
		
		
			try {
				
			MultipartFile file2 = mul.getFile("signatureimg");
			
			if (!file2.getOriginalFilename().isEmpty()) {
				
				if (file2.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				String upload_fileEXt = FilenameUtils.getExtension(file2.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed for signature");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				long filesize = file2.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				String signature = comMstr.fileupload(file2.getBytes(), file2.getOriginalFilename(),
						1, "signature" + "1");
				rd.setSignature(signature);
			}
			
			if (file2.getOriginalFilename().isEmpty()  && !signature_hid.equals("")) {
				
				rd.setSignature(signature_hid);
			}
			
			MultipartFile file3 = mul.getFile("photographimg");
			if (!file3.getOriginalFilename().isEmpty()) {
				
				if (file3.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				String upload_fileEXt = FilenameUtils.getExtension(file3.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed for Photograph");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				long filesize = file3.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				
				
				String photograph = comMstr.fileupload(file3.getBytes(), file3.getOriginalFilename(),
						3, "photograph"+3 + "1");
				rd.setPhotograph(photograph);
			}
			
			if (file3.getOriginalFilename().isEmpty()  && !photograph_hid.equals("")) {
				rd.setPhotograph(photograph_hid);
			}
			
			MultipartFile file4 = mul.getFile("court_order");
			if (!file4.getOriginalFilename().isEmpty()) {
				
				if (file4.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				String upload_fileEXt = FilenameUtils.getExtension(file4.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("pdf")) {
					ra.addAttribute("msg", "Only .pdf file extensions allowed for Court Order");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				long filesize = file4.getSize() / 1024;
				if (filesize > 200) {
					ra.addAttribute("msg", "File size should be 200 kb or less");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				String courtorder = comMstr.fileupload(file4.getBytes(), file4.getOriginalFilename(),
						4, "courtorder");
				rd.setCourt_order(courtorder);
			}
			
	System.err.println("court_order_hid---------->    "+court_order_hid);
			
			if (file4.getOriginalFilename().isEmpty()  && !court_order_hid.equals("0")) {
				rd.setCourt_order(court_order_hid);
			}
			
			if (id == 0) {
				
				if (file2.getOriginalFilename().isEmpty()) {
					ra.addAttribute("msg", "Please Upload Signature");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				if (file3.getOriginalFilename().isEmpty()) {
					ra.addAttribute("msg", "Please Upload Photo");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				if (file4.getOriginalFilename().isEmpty() && late_admission_status.equals("1")) {
					ra.addAttribute("msg", "Please Upload Court Order");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
					
//					for(at = 1; at <= $('#count_hidden_att').val(); at++){
//						if (signature == "" && photograph == "" && signature_hid == "0" && photograph_hid == "0" && $("#upload_document"+at).val() == "" && $("#doc_name"+at).val()=='0' && $("#doc_type"+at).val().trim()=='0') {
//							alert("Empty Data Can Not Be Saved");
//							return false;
//						}
//						
//						if ((signature != "" || photograph != "") && (signature_hid != "0" || photograph_hid != "0")) {
//							if ($("#doc_name"+at).val()=='0' && $("#doc_type"+at).val()=='0' && $("#upload_document"+at).val() == "") {
//								return true;
//							}
//							else {
//								
//								if($("#doc_name"+at).val()=='0'){
//									alert("Please Select Doument Name In "+at+" Row");
//									$("#doc_name"+at).focus();
//									return false;
//								}
//								if($("#doc_type"+at).val()=='0'){
//									alert("Please Select Doument Type In "+at+" Row");
//									$("#doc_type"+at).focus();
//									return false;
//								}
//								
//								if($("#upload_document"+at).val() == ""){
//									alert("Please Upload File "+at+" Row");
//									$("#upload_document"+at).focus();
//									return false;
//								}
//								
//							}
//						}else {
//							if($("#doc_name"+at).val()=='0'){
//								alert("Please Select Doument Name In "+at+" Row");
//								$("#doc_name"+at).focus();
//								return false;
//							}
//							if($("#doc_type"+at).val()=='0'){
//								alert("Please Select Doument Type In "+at+" Row");
//								$("#doc_type"+at).focus();
//								return false;
//							}
//							
//							if($("#upload_document"+at).val() == ""){
//								alert("Please Upload File "+at+" Row");
//								$("#upload_document"+at).focus();
//								return false;
//							}
//						}
//					}
//				}
//				
			
			rd.setReg_id(1);
			rd.setCreated_by(username);
			rd.setCreated_date(date);
			rd.setP_id(Integer.parseInt(p_id));
			
			int sid = (int) sessionHQL.save(rd);
			sessionHQL.flush();
			sessionHQL.clear();
			}
			else {
				
				if((signature_hid.equals("0")  || signature_hid == "") && (file2.getOriginalFilename().isEmpty())) {
					ra.addAttribute("msg", "Please Upload Signature");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				}
				
				if (( photograph_hid.equals("0") || photograph_hid == "") && (file3.getOriginalFilename().isEmpty())) {
					ra.addAttribute("msg", "Please Upload Photo");
					return new ModelAndView("redirect:doc_uploadNcism_Url");
				} 
				if (( court_order_hid.equals("0") || court_order_hid == "") && file4.getOriginalFilename().isEmpty() && late_admission_status.equals("1")) {
					ra.addAttribute("msg", "Please Upload Court Order");
					return new ModelAndView("redirect:doc_uploadUrl");
				}
				
				EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD pda = (EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD)sessionHQL.get(EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD.class, id);
				pda.setModified_by(username);
				pda.setModified_date(date);
				pda.setPhotograph(rd.getPhotograph());
				pda.setSignature(rd.getSignature());
				pda.setCourt_order(rd.getCourt_order());
				
				String msg = getUpdatedocNcism_Details(pda);
			}
			
			int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
			System.out.println("count_hidden_att "+count_hidden_att);
			for(int i=1; i <=count_hidden_att; i++) {
				
				String doc_name = request.getParameter("doc_name"+i);
				String doc_type = request.getParameter("doc_type"+i);
				
				Query q0 = sessionHQL.createQuery("select count(id) from EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD where doc_name=:doc_name and p_id=:p_id");
				q0.setParameter("doc_name", Integer.parseInt(doc_name));
				q0.setParameter("p_id", Integer.parseInt(p_id));
				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
				if(!request.getParameter("doc_name"+i).equals("0")) {
					
					MultipartFile file = mul.getFile("upload_document"+i);
					if (!file.getOriginalFilename().isEmpty()) {
						
						
						if (file.getOriginalFilename().split("[.]").length > 2) {
							ra.addAttribute("msg", "Invalid file extension for Document in row "+i);
							return new ModelAndView("redirect:doc_uploadNcism_Url");
						}
						
						String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
						if (!upload_fileEXt.equals("pdf")) {
							ra.addAttribute("msg", "Only .pdf file extensions allowed for Document in row "+i);
							return new ModelAndView("redirect:doc_uploadNcism_Url");
						}
						
						long filesize = file.getSize() / 1024;
						if (filesize > 200) {
							ra.addAttribute("msg", "File size should be 200 kb or less");
							return new ModelAndView("redirect:doc_uploadNcism_Url");
						}
						
						 name = comMstr.fileupload(file.getBytes(), file.getOriginalFilename(),
								i, "upload_document"+i + "1");
					}
				
				EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD od = new EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD();
				
				//String du = fname;
				od.setReg_id(1);
				od.setDoc_name(Integer.parseInt(doc_name));
				od.setDoc_type(Integer.parseInt(doc_type));
				od.setUpload_document(name);
				
				od.setP_id(Integer.parseInt(p_id));
				od.setCreated_by(username);
				od.setCreated_date(date);
					
				sessionHQL.save(od);
				sessionHQL.flush();
				sessionHQL.clear();
					}
				ra.addAttribute("msg", "Data Saved Successfully.");
				}
				else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
					tx.commit();
					ra.addAttribute("doc_eid", p_id);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
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
		return new ModelAndView("redirect:doc_uploadNcism_Url");
	}
	
	 public String getUpdatedocNcism_Details(EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD obj){
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			 String msg = "";
			 
			 sessionHQL.update(obj);
			 msg = "Data Updated Successfully";
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
//			}
//			catch (Exception e) {
//				msg = "Data Not Updated";
//				tx.rollback();
//			}
//			finally {
//				sessionHQL.close();
//			}
			return msg;
		}
	
	@PostMapping("/getFilterDoc_Ncism_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterDoc_Ncism_data(ModelMap model, Principal principal,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, Integer doc_name,String upload_document,HttpSession sessionUserId) {
		
		String username = principal.getName();
		int userid = da.getNcism_Username(username);

		return docdao.DataTableDocNcism_DataList(startPage, pageLength, Search, orderColunm, orderType,0, userid);

	}

	@PostMapping("/getTotalDoc_Ncism_dataCount")

	public @ResponseBody long getTotalDoc_Ncism_dataCount(ModelMap model, Principal principal,HttpSession sessionUserId, String Search, Integer doc_name) {
		
		String username = principal.getName();
		int userid = da.getNcism_Username(username);

		return docdao.DataTableDocNcism_DataTotalCount(Search,doc_name, userid);

	}
	
	//------------------------------- Used For File Download function  encrypt id ---------------------------
		@SuppressWarnings("null")
		@RequestMapping(value = "/getDownloadPdfUrlfor_Ncism_Doc")
		public ModelAndView getDownloadPdfUrlfor_Ncism_Doc(@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
				
				ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
				throws IOException {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			String url = pageUrl;
			String EXTERNAL_FILE_PATH = "";

			EXTERNAL_FILE_PATH = docdao.getFilePathNcism_Query(Integer.parseInt(doc_id1));
			
			if (EXTERNAL_FILE_PATH != "") {
				File file = null;
				file = new File(EXTERNAL_FILE_PATH);

				System.err.println("file---------->     "+file);
				
				try {
					if (!file.exists()) {
						model.put("msg", "Sorry.The file you are looking for does not exist!");
						return new ModelAndView(url);
					}
					
				} catch (Exception exception) {
					model.put("msg", "Sorry.The file you are looking for does not exist!");
					return new ModelAndView(url);
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
		
		@PostMapping(value = "/delete_Ncism_document")
		public @ResponseBody ModelAndView delete_document(@ModelAttribute("id1") int id,@ModelAttribute("doc_ch_p_id") int p_id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			List<String> liststr = new ArrayList<String>();
			
			String username = session.getAttribute("username").toString();
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "delete from EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD where id=:id";
				 
			@SuppressWarnings({ "rawtypes", "deprecation" })
				int app = sessionHQL.createQuery(hqlUpdate)
				.setParameter("id", id).executeUpdate();
//				.setString("modified_by", username)
//				.setDate("modified_date", new Date()).
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
			model.put("doc_eid",p_id);
			return new ModelAndView("redirect:doc_uploadNcism_Url");
		}

		//image upload common method for Ref Doc Pic
		@SuppressWarnings("null")
		@RequestMapping(value = "/ViewRefImageFileNcism_Download5")
		public void ViewRefImageFileNcism_Download5(@ModelAttribute("kmlId") String id,@ModelAttribute("kmlfildname") String fildname,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
			
			final int BUFFER_SIZE = 4096;
//		    String filePath = c.getRefImageFilePath(Integer.parseInt(id),Integer.parseInt(doc_id));	
			String filePath = "";
			if(fildname.equals("signature")) {
				filePath = docdao.getSignatureImagePath_Ncisam(id);
				System.err.println("getSignatureImagePath----------->   "+filePath);
			}
			
			if(fildname.equals("photograph")) {
				filePath = docdao.getPhotographImagePath_Ncisam(id);
				System.err.println("getPhotographImagePath------------>   "+filePath);
			}
//			String filePath = docdao.getSignatureImagePath(id);
		    model.put("filePath",filePath);
		    ServletContext context = request.getSession().getServletContext();
	       try{
	    	   
	        if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
	        {
	            @SuppressWarnings("deprecation")
				String fullPath =  request.getRealPath("/")+"assets\\db_img\\userphoto.png";
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
		              ServletOutputStream outStream = response.getOutputStream();
		              byte[] buffer = new byte[BUFFER_SIZE];
		              int bytesRead = -1;
		              while ((bytesRead = inputStream.read(buffer)) != -1) {
		                  outStream.write(buffer, 0, bytesRead);
		              }
		              inputStream.close();
		              outStream.close();
	        }
	        else
	        {
	        	    String fullPath =  filePath; 
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
			        ServletOutputStream outStream = response.getOutputStream();
			        byte[] buffer = new byte[BUFFER_SIZE];
			        int bytesRead = -1;
			        while ((bytesRead = inputStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        inputStream.close();
			        outStream.close();
	              }
	        }catch(Exception ex)
	        {
	        	@SuppressWarnings("deprecation")
				String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
	              File downloadFile = new File(fullPath);
	              FileInputStream inputStream = new FileInputStream(downloadFile);
	              String mimeType = context.getMimeType(fullPath);
	              if (mimeType == null) {
	                  mimeType = "application/octet-stream";
	              }
	              response.setContentType(mimeType);
	              response.setContentLength((int) downloadFile.length());
	              String headerKey = "Content-Disposition";
	              String headerValue = String.format("attachment; filename=\"%s\"",
	                      downloadFile.getName());
	              response.setHeader(headerKey, headerValue);
	              ServletOutputStream outStream = response.getOutputStream();
	              byte[] buffer = new byte[BUFFER_SIZE];
	              int bytesRead = -1;
	              while ((bytesRead = inputStream.read(buffer)) != -1) {
	                  outStream.write(buffer, 0, bytesRead);
	              }
	              inputStream.close();
	              outStream.close();
	       }		        	
		}  

		 @RequestMapping(value = "/get_uploded_imgthumb_Ncism_ctrl", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgthumb_Ncism_ctrl(String p_id) {
		    	ArrayList<ArrayList<String>> data = docdao.get_uploded_imgthumb_Ncism_data(p_id);
		    	return data;
		 	}
		
}


