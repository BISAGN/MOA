package com.AyushEdu.controller.Regulation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Regulation_previewDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Regulation_Preview_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Regulation_previewDAO RPdao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
//	-----------------preview

//	@RequestMapping(value = "/pract_previewUrl", method = RequestMethod.POST)
//	public ModelAndView pract_previewUrl(@ModelAttribute("previewid") String viewid, ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		
		
		@RequestMapping(value = "admin/pract_previewUrl", method = RequestMethod.GET)
		public ModelAndView pract_previewUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val1 = roledao.ScreenRedirect("Practitioner_Form_A_URL", roleid1);		
			 Boolean val2 = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
			 Boolean val3 = roledao.ScreenRedirect("Regulation_Url", roleid1);		
				if(val1 == false && val2 == false && val3 == false) {
					return new ModelAndView("AccessTiles");
			}
		
		Calendar calendar = Calendar.getInstance();
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		// System.err.println(common.getMedNationality(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
//		 Mmap.put("MedStateName", common.getInstituteList(sessionFactory));

		// HET CHANGES
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();

		try {
			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			System.err.println("data---------" + data);
			Mmap.put("userId", data);
			if (data != 0) {
				Mmap.put("setDataCMD", RPdao.getDataByUserNameForDraftPreview(data));
				Mmap.put("setAddMoreMedicalCMD", RPdao.medicalDataPreview(data));
				Mmap.put("setAddMoreHospCMD", RPdao.HospitalDataPreview(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", RPdao.medicalDataChildAttachmentPreview(data));
				//Mmap.put("CheckNRH", RPdao.CheckNRHPreview(data));
				Mmap.put("setRegAuth", RPdao.RegAuthPreview(data));
				String pid = RPdao.getUserIdPreview(data);
				Mmap.put("p_id", pid);

//				--20_06_2022 urmik
				System.out.println("p iddd--jjjjjjjjjjj------=" + pid);

				// Mmap.put("p_id", pid);

				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);

					Mmap.put("parentid", data2);

					Mmap.put("valid_dt", INF.getValid_up_to());

					if (INF.getStatus() == 0) {
						Mmap.put("hid", "2");

					}
//					--------------04-07-22 latest
					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
						Mmap.put("hid", "1");
					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1
							&& INF.getNrh_status() == 1) {
						Mmap.put("hid", "3");

					} else {
						Mmap.put("hid", "0");
					}

//					Mmap.put("p_id", pid);

				} else {
					Mmap.put("p_id", "0");
				}

			} else {
				Mmap.put("setData", "0");
				Mmap.put("hid", "0");
				Mmap.put("p_id", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));

		// HET CHANGES

//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);

//		Mmap.put("pvid", previewid);

		return new ModelAndView("pract_preview_Tiles");

	}
		
//		--------------07-05-22
		public String fileuploadmultipart1(long index, String desc, String gs_no, byte[] b, String name) {
			String extension = "";
			String fname = "";

			try {
				byte[] bytes = b; // file.getBytes();
				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				String rootPath = "srv/Document";
				File dir = new File(rootPath + File.separator);
				System.err.println("dir---" + dir);
				// File dir = new File("E://mining_documents");
				if (!dir.exists())
					dir.mkdirs();

				String filename = name; // file.getOriginalFilename();

				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}

				// Create the file on server
				java.util.Date date = new java.util.Date();
				System.err.println("index---" + index);
				fname = dir.getAbsolutePath() + File.separator
						+ ("Ayush" + index + "-" + new Timestamp(date.getTime())).toString().replace(":", "").toString()
								.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
						+ "." + extension;
				System.err.println("fname--final---" + fname);
				File serverFile = new File(fname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				System.out.println("Error");
			}
			return fname;
		}
		
		//------------------------------------------------Edit--Image_View------------------------------------------------------

		@RequestMapping(value = "/MedicalImagePath11", method = RequestMethod.GET)
		public void censusIdentityConvertpath(@ModelAttribute("i_id") String id, @ModelAttribute("id5") String myImg,
				ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			String filePath = RPdao.getImagePath1(i_id);
	 System.err.println("filePath==="+filePath);
			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

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

				// System.out.println(ex);

				// admin//js//img//No_Image.jpg

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
		
		
		//-----------------------------Excel-----------------------------------

		@RequestMapping(value = "/Excel_Auth_Posted_query1", method = RequestMethod.POST)
		public ModelAndView Excel_Auth_Posted_query1(HttpServletRequest request, ModelMap model, HttpSession session,
				String typeReport1, @RequestParam(value = "msg", required = false) String msg) {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			
	//  String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Auth_and_Posted_Strength", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
	// 
			ArrayList<ArrayList<String>> Excel = RPdao.pdf_getAuth_and_Posted_StrenghReportPreviewDataList();

			List<String> TH = new ArrayList<String>();
			TH.add("Ser No");
			TH.add("NRH Enrollment No");
			TH.add("First Name");
			TH.add("Gender");
			TH.add("Photo Path");
			TH.add("Father Name");
			TH.add("Present Address");
			TH.add("Present District");
			TH.add("Present State");
			TH.add("Present Pincode");
			TH.add("Permanent Address");
			TH.add("Permanent District");
			TH.add("Permanent State");
			TH.add("Permanent Pincode");
			TH.add("Aadhaar No");
			TH.add("Fax No");
			TH.add("Mo. No");
			TH.add("Alternate Mo. No");
			TH.add("Email Id");
			TH.add("Date Of Birth");
			TH.add("Nationality");
			TH.add("Name Of Medical Degree");
			TH.add("Name of University");
			TH.add("Month And Year Of Passing");
			TH.add("Registration No.");
			TH.add("Date Of Registration");
			TH.add("Name Of The Register");
			TH.add("Registration Renewable Or Permanent");
			TH.add("Name Of Hospital Or Institute With Address");
			TH.add("Name Of Patient");
			TH.add("CRH No");
			TH.add("CCH No");
			TH.add("NCH No");

			String Heading = "\nAuth and Posted Strength Search Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}

		@RequestMapping(value = "/getDegreedetailsPreview", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getDegreedetailsPreview(String typeofdegree) {

			List<Map<String, Object>> list = RPdao.getdegreedetailssPreview(typeofdegree);
			System.err.println("------list 22_06 ====  " + list);
			return list;
		}

		@RequestMapping(value = "/getDistrictOnstatePreview", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstatePreview(String selval) {
		 System.err.println("INSIDE");
			return common.district(sessionFactory, selval);

		}

		@RequestMapping(value = "/getayusAbhaDatalistPreview", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getayusAbhaDatalist(String userId) {

			List<Map<String, Object>> list = RPdao.getayusAbhaDatalistPreview(userId);
			System.err.println("------list 22_06 ====  " + list);
			return list;
		}
		
		
		
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/kmlFileDownload699")
		public void kmlFileDownload444(@ModelAttribute("kmlId2") int id, ModelMap model, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws IOException {

			final int BUFFER_SIZE = 4096;


			String filePath = RPdao.getFilePathQuery_popup1(id);

			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();
			try {
				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
					@SuppressWarnings("deprecation")
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
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
					ServletOutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outStream.close();
				}
			} catch (Exception ex) {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
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

		}
		
		@RequestMapping(value = "/getFilePath2", method = RequestMethod.POST)
		public @ResponseBody String getFilePath2(HttpSession session,HttpServletRequest request,String id) {

			 
			String filePath = RPdao.getFilePathQuery_popup1(Integer.parseInt(id));
 
			return filePath;
		}
		
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/kmlFileDownloadDemo2")
		public void kmlFileDownloadDemo2(@ModelAttribute("path") String filePath, ModelMap model, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws IOException {

			final int BUFFER_SIZE = 4096;

 
			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();
			try {
				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
					@SuppressWarnings("deprecation")
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
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
					ServletOutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outStream.close();
				}
			} catch (Exception ex) {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
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

		}

		
		
		
		

}
