package com.AyushEdu.controller.LMS_Master_Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_STATE_LOGO_MSTR;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_DOC_UPLOAD_MSTR;
import com.AyushEdu.Models.Time_Table.EDU_TT_CLASSROOM_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.DistrictDao;
import com.AyushEdu.dao.LMS_Master.statelogo_Master_DAO;
import com.AyushEdu.dao.Time_Table.Classroom_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class State_Logo_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	CommonController common;
	
	@Autowired
	statelogo_Master_DAO Cdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	ValidationController validation;
	
	 @RequestMapping(value = "admin/statelogo_mstr", method = RequestMethod.GET)
	 public ModelAndView statelogo_mstr(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 try {
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("District", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
		 Mmap.put("msg", msg);
		 Mmap.put("getMedCountryName", common.getMedCountryName( sessionFactory));
		 Mmap.put("state_id", common.getMedStateName(sessionFactory));
//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		 
		 } catch (Exception e) {
				e.printStackTrace();
			}
		 return new ModelAndView("StatelogoTiles");
	 }
	 
	 @RequestMapping(value = "/StateLogoAction",method=RequestMethod.POST)
		public ModelAndView StateLogoAction(@ModelAttribute("StatelogomstrCMD") EDU_LMS_STATE_LOGO_MSTR rmd, 
				BindingResult result,HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException,ParseException {
			
		 if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("statelogo_mstr", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	 
				int id = rmd.getId() > 0 ? rmd.getId() : 0;
				
				System.err.println("\n\n id------------------------------"+id);
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String state_id = request.getParameter("state_id").trim();
				String upload_img_hid = request.getParameter("upload_img_hid").trim();
				
				MultipartFile file2 = mul.getFile("state_logo_path");
				String photo_path2="";
				if (!file2.getOriginalFilename().isEmpty()) {
					
					 if (file2.getOriginalFilename().split("[.]").length > 2) {
						 ra.addAttribute("msg", "Invalid file extension for Document");
							return new ModelAndView("redirect:statelogo_mstr");
					}
					
					
					photo_path2 = common.fileupload76(file2.getBytes(), file2.getOriginalFilename(),1, "signature" + "1");
					upload_img_hid=photo_path2;
				}
				
				 Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
							 
							if (rmd.getState_id() == 0) {
								ra.addAttribute("msg", "Please Select State.");
								return new ModelAndView("redirect:statelogo_mstr");
							}
							 
							if (rmd.getStatus() == null || rmd.getStatus().trim().equals("0")) {
								ra.addAttribute("msg", "Please Select Status.");
								return new ModelAndView("redirect:statelogo_mstr");
							}
							if (rmd.getStatus() == "2" || rmd.getStatus().equals("2")) {
								ra.addAttribute("msg", "Only Select Active Status.");
								return new ModelAndView("redirect:statelogo_mstr");
							}

					try{
					
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from EDU_LMS_STATE_LOGO_MSTR where state_id=:state_id and status=:status  and id !=:id")
								.setParameter("state_id", rmd.getState_id())
								.setParameter("status", rmd.getStatus())
								.setParameter("id", id).uniqueResult();

					if (id == 0) {
						if (state_id != null && !state_id.equals("")) {
							rmd.setState_id(Integer.parseInt(state_id));
						}
						
						if (photo_path2 != null && !photo_path2.equals("")) {
							rmd.setState_logo((photo_path2));
						}
						rmd.setCreated_by(username);
						rmd.setCreated_date(date);
						if (c == 0) {
							sessionHQL.save(rmd);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");

						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					} else {
						rmd.setState_id(Integer.parseInt(state_id));
						rmd.setModified_by(username);
						rmd.setModified_date(date);
						rmd.setState_logo(upload_img_hid);
						if (c == 0) {
							rmd.setId(id);
							String msg = Cdao.updateStudentLogo(rmd);
							ra.addAttribute("msg","Data Updated Succesfully.");
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}
					tx.commit();
				}catch(RuntimeException e){
					e.printStackTrace();
					try{
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:statelogo_mstr");
			}
	
		@PostMapping("/getFilterstatelogo_data")

		public @ResponseBody List<Map<String, Object>> getFilterstatelogo_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String state_id,String state_logo_path, String status,HttpSession session) {
					
//			System.err.println(Search+"======================================================");
			return Cdao.DataTablestatelogoDataList(startPage, pageLength, Search, orderColunm, orderType, state_id,state_logo_path,status);

		}

		@PostMapping("/getTotalstatelogo_dataCount")
		public @ResponseBody long getTotalstatelogo_dataCount( String search, String state_id,String state_logo_path, String status,HttpSession session,HttpSession sessionUserId) {
			
			return Cdao.DataTablestatelogoDataTotalCount(search, state_id,state_logo_path,status);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_StateLogo_MasterUrl", method = RequestMethod.POST)
				public ModelAndView Edit_StateLogo_MasterUrl(@ModelAttribute("id3") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "state_id", required = false) String state_id,
						@RequestParam(value = "state_logo_path", required = false) String state_logo_path,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_LMS_STATE_LOGO_MSTR studentlogo_Details = Cdao.getClassroomByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("state_id", state_id);
					Mmap.put("state_logo_path", state_logo_path);
					Mmap.put("status", status);
					Mmap.put("studentlogo_Details", studentlogo_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("studentlogoinfo", Cdao.getClassroomByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("StatelogoTiles");
				}
				@RequestMapping(value = "/admin/getSearch_StateLogo_Master", method = RequestMethod.POST)
				public ModelAndView getSearch_StateLogo_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "state_id", required = false) String state_id ,String state_id1)  {
					
					
							Mmap.put("state_id", state_id1);
					

							return new ModelAndView("Attachment_MasterTiles","SystemCMD2",new EDU_PG_DOC_UPLOAD_MSTR());
					   
				}
				@RequestMapping(value = "/delete_StateLogo_Action", method = RequestMethod.POST)
				public ModelAndView delete_StateLogo_Action(@ModelAttribute("id4") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
					
					
					if(request.getHeader("Referer") == null ) { 
						//	 session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							 return new ModelAndView("redirect:/landingpage");
						 }
						
						String roleid1 = session1.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("statelogo_mstr", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}

					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from EDU_LMS_STATE_LOGO_MSTR where id=:id")
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
					return new ModelAndView("redirect:statelogo_mstr");
				}
				@RequestMapping(value = "/View_StateLogo", method = RequestMethod.GET)
				public void View_StateLogo(@ModelAttribute("i_id") int id, ModelMap model,
						HttpServletRequest request, HttpServletResponse response) throws IOException {
					

					final int BUFFER_SIZE = 4096;

					int i_id = id;

					
					String filePath = Cdao.getStatelogoImagePath(i_id);


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
}
