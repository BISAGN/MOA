package com.AyushEdu.controller.Regulation;

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

import org.hibernate.Session;
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
import com.AyushEdu.dao.Regulation.Regulation_ReportDao;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Regulation_Report {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Regulation_ReportDao  RRdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	
	@RequestMapping(value = "admin/Regulation_Report_Url", method = RequestMethod.GET)
	 public ModelAndView Regulation_Report_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request) {
		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		 
			int data = (int) sessionHQ.createQuery("select state_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			
			int data1 = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			System.err.println("data1---------" + data1);
			Mmap.put("institute_id", data1);
			
		
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Regulation_Report_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				 
			 Mmap.put("state_id", data);
			 System.err.println("check the state"+data);
			Calendar calendar = Calendar.getInstance();
			 Mmap.put("msg", msg);
			 Mmap.put("getgenderList", common.getgenderList(sessionFactory));
			 Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
			 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		
		//start new pdf
//				if (typeReport != null && !typeReport.equals("")) {
//					// List<Map<String, Object>> list1 =RRdao.DataTableEdu_Reg_Report_masterDataList(0, -1, "", "1", "asc", "","", "");
//					 ArrayList<ArrayList<String>> list=RRdao.DataTableEdu_Reg_Report_masterDataList_pdf();
//					
//					 if (list.size() == 0) {
//							Mmap.put("msg", "Data Not Available.");
//						} else {
//							Mmap.put("list", list);
//
//							if (typeReport != null && typeReport.equals("pdfL")) {
//								if (list.size() > 0) {
//									List<String> TH = new ArrayList<String>();
//									TH.add("Ser No"); //1
//									TH.add("NRH Enrollment No"); //2
//									TH.add("Name Of The Professional With Recent Photograph"); //3
//									TH.add("Father's Name"); //4
//									TH.add("Present Correspondence Address"); //5
//									TH.add("Permanent Address"); //6
//									TH.add("Aadhaar Number"); //7
//									TH.add("Phone,Fax And Mobile Number With Email Address"); //8
//									TH.add("Date of Birth And Nationality"); //9
//									TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //10
//									TH.add("Registration Particulars: 1.Registration Number 2.Date of Registration 3.Name Of The Register(National/State) 4.Whether The Registration Is Renewable Or Permanent"); //11
//									TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //12
//									TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //13
//
//									String Heading = "\nSCHEDULE OF CREDIT";
//									String username = session.getAttribute("username").toString();
//									return new ModelAndView(new DownloadInventoryPdf("L", TH, Heading, username),"userList",list);
//								}
//							}
//						}
//					
//				}
				//end
       
		 Mmap.put("msg", msg);
//		 Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
//		 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		 
//	    ArrayList<ArrayList<String>> list = Dis_Dao.search_District_name(0,0,"","active");
//		 Mmap.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
	    }
		 return new ModelAndView("regulation_report_reg_Tiles");
	 }
	//download pdf
	@RequestMapping(value = "admin/Regulation_Report_Url_pdf", method = RequestMethod.POST)
	 public ModelAndView Regulation_Report_Url_pdf(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, String typeReport,
			 HttpServletRequest request) throws ParseException {
		 

		String Search = request.getParameter("Search4");
		String nrh_en_no = request.getParameter("nrh_en_no1");
		String first_name = request.getParameter("first_name1");
		String status = request.getParameter("status1");
		
		String per_state = request.getParameter("per_state1");
		
		String from_date = request.getParameter("from_date1");
		String to_date = request.getParameter("to_date1");
		String gender = request.getParameter("gender1");
		
		String state_reg_no = request.getParameter("state_reg_no1");
		String dob = request.getParameter("dob1");
		String institute_name = request.getParameter("institute_name1");
		String type_status  = request.getParameter("type_status1");
		
		
		//start new pdf
			if (typeReport != null && !typeReport.equals("")) {
//				--04-03-2023
					 ArrayList<ArrayList<String>> list=RRdao.DataTableEdu_Reg_Report_masterDataList_pdf(Search,nrh_en_no,first_name,status,per_state,from_date,to_date 
								,gender ,state_reg_no ,dob ,institute_name ,type_status);
					 System.err.println("list=========="+list);
					 if (list.size() == 0) {
							Mmap.put("msg", "Data Not Available.");
						} else {
							Mmap.put("list", list);

							if (typeReport != null && typeReport.equals("pdfL")) {
								if (list.size() > 0) {
									List<String> TH = new ArrayList<String>();
//									TH.add("Ser No"); //1
//									TH.add("Ayush Id/ABHA No.");
//									TH.add("Name Of The Professional With Recent Photograph"); //3
//									TH.add("NRH Enrollment No."); //2
//									TH.add("Father's Name"); //4
//									TH.add("Present Correspondence Address"); //5
//									TH.add("Permanent Address"); //6
//				//					TH.add("Aadhaar Number"); //7
//									TH.add("Email Address With Mobile No."); //8
//									TH.add("Date of Birth And Nationality"); //9
//									TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //10
//									TH.add("Registration Number,Date,State"); //11
//									TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //12
//									TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //13

									
									
									
									TH.add("Ser No"); //1
 									TH.add("Name Of The Professional With Recent Photograph"); //2
 									TH.add("Father's Name"); //3
									TH.add("Present Correspondence Address"); //4
									TH.add("Permanent Address"); //5
 									TH.add("Email Address With Mobile No."); //6
									TH.add("Date of Birth And Nationality"); //7
									TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //8
									TH.add("Registration Number,Date,State"); //9
									TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //10
									TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //11
// 									TH.add("Ayush Id/ABHA No.");//12
//									TH.add("NRH Enrollment No."); //13
									String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
									String Heading = "\nSCHEDULE OF CREDIT";
									String username = session.getAttribute("username").toString();
									return new ModelAndView(new DownloadInventoryPdf("L", TH, Heading, username,foot),"userList",list);
								}
							}
						}
					
				
				//end
      
			}
			return new ModelAndView("redirect:Regulation_Report_Url");
		// return new ModelAndView("regulation_report_reg_Tiles");
	 }
	
	
	
	@PostMapping("/getFilter_Reg_Report_data")

	public @ResponseBody List<Map<String, Object>> getFilter_Reg_Report_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String status,String per_state,String from_date,String to_date ,String gender ,
			String state_reg_no ,String dob  ,String institute_name ,String type_status) throws ParseException {
	  
		return RRdao.DataTableEdu_Reg_Report_masterDataList(startPage, pageLength, Search, orderColunm, orderType, nrh_en_no,first_name, status,per_state,from_date,to_date ,
				gender ,state_reg_no  ,dob ,institute_name ,type_status);

	}

	@PostMapping("/getTotalEdu_Reg_Report_dataCount")

	public @ResponseBody long getTotalEdu_Reg_Report_dataCount(HttpSession sessionUserId, String Search, String nrh_en_no,String first_name,String status,
			String per_state,String from_date,String to_date ,String gender ,String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException {
		return RRdao.DataTableEdu_Reg_Report_masterDataTotalCount(Search, nrh_en_no, first_name,status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);

	}
	
	//--------------------------------------------------Image_View------------------------------------------------------

	@RequestMapping(value = "/MedicalImagePath2", method = RequestMethod.GET)
	public void censusIdentityConvertpath(@ModelAttribute("i_id") String id,@ModelAttribute("id6") String myImg, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		

		final int BUFFER_SIZE = 4096;

		String i_id = id;

		
		String filePath = RRdao.getImagePath1(i_id);


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

			System.out.println(ex);
			
		//	admin//js//img//No_Image.jpg
			
			
			
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

	
	
	//------------------------pdf---------------------------------
	//start pdf
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownload4441")
	public void kmlFileDownload4441(@ModelAttribute("kmlFileId455") String id, 
			@ModelAttribute("fildname1") String fildname, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;
     System.err.println("INSIDE");

		String filePath = RRdao.getFilePathQueryForDocFile(id,fildname);

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
	
	@RequestMapping(value = "/Regulation_Report_Excel", method = RequestMethod.POST)
public ModelAndView Regulation_Report_Excel(HttpSession session, HttpServletRequest request)
		throws ParseException {
 
	String Search = request.getParameter("Search2");
	String nrh_en_no = request.getParameter("nrh_en_no2");
	String first_name = request.getParameter("first_name2");
	String status = request.getParameter("status2");
	
	String per_state = request.getParameter("per_state2");
	
	String from_date = request.getParameter("from_date2");
	String to_date = request.getParameter("to_date2");
	String gender = request.getParameter("gender2");
	
	String state_reg_no = request.getParameter("state_reg_no2");
	String dob = request.getParameter("dob2");
	String institute_name = request.getParameter("institute_name2");
	String type_status  = request.getParameter("type_status2");
	List<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
	
	
	list=RRdao.getRegulation_Report_Excel(Search,nrh_en_no,first_name,status,per_state,from_date,to_date 
			,gender ,state_reg_no ,dob ,institute_name ,type_status);		

//	ArrayList<ArrayList<String>> non = new ArrayList<ArrayList<String>>();

	int total = list.size();
	List<String> TH = new ArrayList<String>();

	TH.add("Ser No"); //1
		TH.add("Name"); //2
		TH.add("Father's Name"); //3
	TH.add("Present Correspondence Address"); //4
	TH.add("Permanent Address"); //5
		TH.add("Email Address With Mobile No."); //6
	TH.add("Date of Birth And Nationality"); //7
	TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //8
	TH.add("Registration Number,Date,State"); //9
	TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //10
	TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //11
		TH.add("Ayush Id/ABHA No.");//12
	TH.add("NRH Enrollment No."); //13
	
	String Heading = "\nIn Inspection";
	
	String username = session.getAttribute("username").toString();
	return new ModelAndView(new Regulation_Report_Excel("L", TH, list, Heading, username), "userList",
			list);
}

}
