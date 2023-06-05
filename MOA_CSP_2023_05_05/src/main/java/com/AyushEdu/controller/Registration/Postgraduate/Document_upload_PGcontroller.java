package com.AyushEdu.controller.Registration.Postgraduate;

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
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_DOCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_OTHER_DOC_UPLOAD;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Postgraduate.DocumentUpload_PGDao;
import com.AyushEdu.dao.Registration.Postgraduate.Personaldetails_PG_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Document_upload_PGcontroller {
	
	@Autowired
	DocumentUpload_PGDao docdao;

	@Autowired
	Personaldetails_PG_DAO da;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	CommonController comMstr = new CommonController();
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@GetMapping(value = "/doc_upload_PGUrl")
	public ModelAndView doc_upload_PGUrl(ModelMap model,Principal principal, HttpSession session,@RequestParam(value = "msg", required = false) String msg,
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
			 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
	int user = da.getUsername_pg(username);
			
//			if(doc_eid.equals("") || doc_eid.isEmpty() || doc_eid.equals("0")) {
//			ArrayList<ArrayList<String>> listp=da.get_p_id_pers_info_data_pg(user);
//			String abc = listp.get(0).get(0);
//			doc_eid = abc;
//			}
//			
//			if(!doc_eid.equals("") && !doc_eid.isEmpty() && !doc_eid.equals("0")) {
//				Query qry = sessionHQL.createQuery("select count(*) from TB_PRE_EDUCATION_DETAILS where p_id=:p_id and (academic='1' or academic='2')");
//				qry.setParameter("p_id", Integer.parseInt(doc_eid));
//				 v = (Long) qry.uniqueResult();
//				}
			
		model.addAttribute("userid",da.getUsername_pg(username));

		model.put("getDoc_nameList", comMstr.getDoc_nameList(sessionFactory));
		model.put("getDoc_name_pg_List", comMstr.getDoc_name_pgList(sessionFactory));
		
		
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
//				model.addAttribute("msg", "Is It Mandatory To Enter 10th and 12th Details");
//			}
//			
//			return new ModelAndView("redirect:Edu_Det_Url");
//		}else {
			model.addAttribute("doc_eid", doc_eid);
			return new ModelAndView("Documnet_Upload_PG_Tiles");
//		}
		
		
		
		
	}
	
	@PostMapping(value = "/Doc_Upload_PG_Action")
	public ModelAndView Doc_Upload_PG_Action(
			@Validated @ModelAttribute("doc_upload_PGCMD") EDU_PG_REG_DOCUMENT_UPLOAD rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
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
		
			try {
				
			MultipartFile file2 = mul.getFile("signatureimg");
			
			//SECURITY-----
			
			if (!file2.getOriginalFilename().isEmpty()) {
				 if (file2.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				String upload_fileEXt = FilenameUtils.getExtension(file2.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed for Signature");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				long filesize = file2.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
			}
			//SECURITY-----
			if (!file2.getOriginalFilename().isEmpty()) {
				String signature = comMstr.fileupload(file2.getBytes(), file2.getOriginalFilename(),1, "signature" + "1");
				rd.setSignature(signature);
			}
			
			if (file2.getOriginalFilename().isEmpty()  && !signature_hid.equals("")) {
				rd.setSignature(signature_hid);
			}
			
			MultipartFile file3 = mul.getFile("photographimg");
			//SECURITY-----
		
			if (!file3.getOriginalFilename().isEmpty()) {
				 if (file3.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				String upload_fileEXt = FilenameUtils.getExtension(file3.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed for Photograph");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				long filesize = file3.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
			}
			//SECURITY-----
			if (!file3.getOriginalFilename().isEmpty()) {
				String photograph = comMstr.fileupload(file3.getBytes(), file3.getOriginalFilename(),
						3, "photograph"+3 + "1");
				rd.setPhotograph(photograph);
			}
			if (file3.getOriginalFilename().isEmpty()  && !photograph_hid.equals("")) {
				rd.setPhotograph(photograph_hid);
			}
			if (id == 0) {
				
				
				
				if (file2.getOriginalFilename().isEmpty()) {
					ra.addAttribute("msg", " Please Upload Signature");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				if (file3.getOriginalFilename().isEmpty()) {
					ra.addAttribute("msg", "Please Upload Photograph");
					return new ModelAndView("redirect:doc_upload_PGUrl");
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
			
			rd.setCreated_by(username);
			rd.setCreated_date(date);
			rd.setP_id(Integer.parseInt(p_id));
			
			int sid = (int) sessionHQL.save(rd);
			sessionHQL.flush();
			sessionHQL.clear();
			}
			else {
				
				
				if((signature_hid.equals("0")  || signature_hid == "") && (file2.getOriginalFilename().isEmpty())) {
					ra.addAttribute("msg", "Please Upload else  Signature");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				}
				
				if (( photograph_hid.equals("0") || photograph_hid == "") && (file3.getOriginalFilename().isEmpty())) {
					ra.addAttribute("msg", "Please Upload Photograph");
					return new ModelAndView("redirect:doc_upload_PGUrl");
				} 
				
				EDU_PG_REG_DOCUMENT_UPLOAD pda = (EDU_PG_REG_DOCUMENT_UPLOAD)sessionHQL.get(EDU_PG_REG_DOCUMENT_UPLOAD.class, id);
				pda.setModified_by(username);
				pda.setModified_date(date);
				pda.setPhotograph(rd.getPhotograph());
				pda.setSignature(rd.getSignature());
				
				String msg = getUpdatedocDetails(pda);
			}
			
			int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
			System.out.println("count_hidden_att "+count_hidden_att);
			for(int i=1; i <=count_hidden_att; i++) {
				
				String doc_name = request.getParameter("doc_name"+i);
				
				Query q0 = sessionHQL.createQuery("select count(id) from EDU_PG_REG_OTHER_DOC_UPLOAD where doc_name=:doc_name and p_id=:p_id");
				q0.setParameter("doc_name", doc_name);
				q0.setParameter("p_id", Integer.parseInt(p_id));
				Long c = (Long) q0.uniqueResult();
				
				if (c == 0) {
				if(!request.getParameter("doc_name"+i).equals("0")) {
					
					MultipartFile file = mul.getFile("upload_document"+i);
					
					//SECURITY-----
					if (file.getOriginalFilename().isEmpty()) {
						ra.addAttribute("msg", "Please Upload Document in row "+ i);
						return new ModelAndView("redirect:doc_upload_PGUrl");
					}
					if (!file.getOriginalFilename().isEmpty()) {
						

						 if (file.getOriginalFilename().split("[.]").length > 2) {
							 ra.addAttribute("msg", "Invalid file extension for Document");
								return new ModelAndView("redirect:doc_upload_PGUrl");
						}
						
						
						
						String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
						if (!upload_fileEXt.equals("pdf")) {
							ra.addAttribute("msg", "Only .pdf file extensions allowed for Document in row "+ i);
							return new ModelAndView("redirect:doc_upload_PGUrl");
						}
						long filesize = file.getSize() / 1024;
						if (filesize > 200) {
							ra.addAttribute("msg", "File size should be 200 kb or less in row "+ i);
							return new ModelAndView("redirect:doc_upload_PGUrl");
						}
					}
					//SECURITY-----
					
					if (!file.getOriginalFilename().isEmpty()) {
						 name = comMstr.fileupload(file.getBytes(), file.getOriginalFilename(),
								i, "upload_document"+i + "1");
					}
				
				EDU_PG_REG_OTHER_DOC_UPLOAD od = new EDU_PG_REG_OTHER_DOC_UPLOAD();
				
				//String du = fname;
				od.setDoc_name(doc_name);
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
		return new ModelAndView("redirect:doc_upload_PGUrl");
	}
	
	
	 public String getUpdatedocDetails(EDU_PG_REG_DOCUMENT_UPLOAD obj){
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
	
	
	
	
	@PostMapping("/getFilterDoc_PG_data")

	public @ResponseBody ArrayList<ArrayList<String>> getFilterDoc_PG_data(ModelMap model, Principal principal,int startPage, int pageLength,
			String Search, String orderColunm, String orderType, Integer doc_name,String upload_document,HttpSession sessionUserId) {
		
		String username = principal.getName();
		int userid = da.getUsername_pg(username);

		return docdao.DataTableDocData_PGList(startPage, pageLength, Search, orderColunm, orderType,0, userid);

	}

	@PostMapping("/getTotalDoc_PG_dataCount")

	public @ResponseBody long getTotalDoc_PG_dataCount(ModelMap model, Principal principal,HttpSession sessionUserId, String Search, Integer doc_name) {
		
		String username = principal.getName();
		int userid = da.getUsername_pg(username);

		return docdao.DataTableDocData_PGTotalCount(Search,doc_name, userid);

	}
	
	
	
	
	
	//------------------------------- Used For File Download function  encrypt id ---------------------------
		@SuppressWarnings("null")
		@RequestMapping(value = "/getDownloadPdfUrlfor_PG_Doc")
		public ModelAndView getDownloadPdfUrlfor_PG_Doc(@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
				
				ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
				throws IOException {

			String url = pageUrl;
			String EXTERNAL_FILE_PATH = "";

			EXTERNAL_FILE_PATH = docdao.getFilePathQuery_PG(Integer.parseInt(doc_id1));
			
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
		
		@PostMapping(value = "/delete_PG_document")
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
			 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "delete from EDU_PG_REG_OTHER_DOC_UPLOAD where id=:id";
				 
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
			return new ModelAndView("redirect:doc_upload_PGUrl");
		}

		//image upload common method for Ref Doc Pic
		@SuppressWarnings("null")
		@RequestMapping(value = "/ViewRefImage_PGFileDownload5")
		public void ViewRefImage_PGFileDownload5(@ModelAttribute("kmlId") String id,@ModelAttribute("kmlfildname") String fildname,ModelMap model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
			
			final int BUFFER_SIZE = 4096;
//		    String filePath = c.getRefImageFilePath(Integer.parseInt(id),Integer.parseInt(doc_id));	
			String filePath = "";
			if(fildname.equals("signature")) {
				filePath = docdao.getSignatureImagePath_PG(id);
			}
			if(fildname.equals("photograph")) {
				filePath = docdao.getPhotographImagePath_PG(id);
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

		 @RequestMapping(value = "/get_uploded_imgthumb_PG_ctrl", method = RequestMethod.POST)
		 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgthumb_PG_ctrl(String p_id) {
		    	ArrayList<ArrayList<String>> data = docdao.get_uploded_imgthumb_data_PG(p_id);
		    	return data;
		 	}
		
}


