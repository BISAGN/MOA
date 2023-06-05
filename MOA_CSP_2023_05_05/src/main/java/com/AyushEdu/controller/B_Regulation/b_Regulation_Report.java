package com.AyushEdu.controller.B_Regulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.controller.Exp_Excel.DownloadInventoryPdf;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.B_Regulation.b_Regulation_ReportDao;
import com.AyushEdu.dao.Regulation.Regulation_ReportDao;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class b_Regulation_Report {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	b_Regulation_ReportDao  BRRdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/b_Regulation_Report_Url", method = RequestMethod.GET)
	 public ModelAndView b_Regulation_Report_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request) {
		 
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("b_Regulation_Report_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			Calendar calendar = Calendar.getInstance();
			 Mmap.put("msg", msg);
			 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		

      
		 Mmap.put("msg", msg);

		 return new ModelAndView("b_regulation_report_reg_Tiles");
	 }
	
	//download pdf
		@RequestMapping(value = "admin/b_Regulation_Report_Url_pdf", method = RequestMethod.POST)
		 public ModelAndView b_Regulation_Report_Url_pdf(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg, String typeReport,
				 HttpServletRequest request) {
			 
//			SECURITY -- RIDDHI 
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("b_regulation_report_reg_Tiles", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			
			
			//start new pdf
				if (typeReport != null && !typeReport.equals("")) {
//						List<Map<String, Object>> list1 =RRdao.DataTableEdu_Reg_Report_masterDataList(0, -1, "", "1", "asc", "","", "");
						 ArrayList<ArrayList<String>> list=BRRdao.DataTable_b_Edu_Reg_Report_masterDataList_pdf();
						 System.err.println("list=========="+list);
						 if (list.size() == 0) {
								Mmap.put("msg", "Data Not Available.");
							} else {
								Mmap.put("list", list);

								if (typeReport != null && typeReport.equals("pdfL")) {
									if (list.size() > 0) {
										List<String> TH = new ArrayList<String>();
										TH.add("Ser No"); //1
										TH.add("NRH Enrollment No"); //2
										TH.add("Name Of The Professional With Recent Photograph"); //3
										TH.add("Father's Name"); //4
										TH.add("Present Correspondence Address"); //5
										TH.add("Permanent Address"); //6
					//					TH.add("Aadhaar Number"); //7
										TH.add("Email Address"); //8
										TH.add("Date of Birth And Nationality"); //9
					//					TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //10
										TH.add("Registration Particulars: 1.Registration Number 2.Date of Registration 3.Name Of The Register(National/State)"); //11
					//					TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //12
					//					TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //13
										String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
										String Heading = "\nSCHEDULE OF CREDIT";
										String username = session.getAttribute("username").toString();
										return new ModelAndView(new DownloadInventoryPdf("L", TH, Heading, username,foot),"userList",list);
									}
								}
							}
						
					
					//end
	      
				}
			 return new ModelAndView("b_regulation_report_reg_Tiles");
		 }
		
		@PostMapping("/getFilter_b_Reg_Report_data")

		public @ResponseBody List<Map<String, Object>> getFilter_b_Edu_Reg_Report_master_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String status,String per_state,String from_date,String to_date) throws ParseException {
			System.err.println("check nrh number"+nrh_en_no);

			return BRRdao.DataTable_b_Edu_Reg_Report_masterDataList(startPage, pageLength, Search, orderColunm, orderType, nrh_en_no,first_name, status,per_state,from_date,to_date);

		}

		@PostMapping("/getTotal_b_Edu_Reg_Report_dataCount")

		public @ResponseBody long getTotal_b_Edu_Reg_Report_master_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String status,String per_state,String from_date,String to_date) throws ParseException {
			return BRRdao.DataTable_b_Edu_Reg_Report_masterDataTotalCount(Search, nrh_en_no, first_name,status,per_state,from_date,to_date);

		}
		
		
		//--------------------------------------------------Image_View------------------------------------------------------

		@RequestMapping(value = "/MedicalImagePath3", method = RequestMethod.GET)
		public void censusIdentityConvertpath(@ModelAttribute("i_id") String id,@ModelAttribute("id6") String myImg, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws IOException {
			

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			
			String filePath = BRRdao.getImagePath1(i_id);


			model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

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
//						request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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


		//------------------------pdf---------------------------------
		//start pdf
		
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/kmlFileDownload4442")
		public void kmlFileDownload4442(@ModelAttribute("kmlFileId455") String id, 
				@ModelAttribute("fildname1") String fildname, ModelMap model, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws IOException {

			final int BUFFER_SIZE = 4096;
	System.err.println("INSIDE");

			String filePath = BRRdao.getFilePathQueryForDocFile(id,fildname);

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
		
		//end
	
}
