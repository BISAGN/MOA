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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.controller.Exp_Excel.DownloadInventoryPdf;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.EduRegDao;
import com.AyushEdu.dao.Regulation.Regulation_ReportDaoAct;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class edu_act_sus_userController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Regulation_ReportDaoAct  RRdao;
	
	@Autowired
	EduRegDao  Rdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/edu_act_sus_userUrl", method = RequestMethod.GET)
	 public ModelAndView edu_act_sus_userUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("edu_act_sus_userUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 Calendar calendar = Calendar.getInstance();             
		 Mmap.put("msg", msg);
		 Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		  Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
	}
		 return new ModelAndView("edu_act_sus_user_Tiles");
	 }
	
	//download pdf
		@RequestMapping(value = "admin/Regulation_Report_Url_pdfAct", method = RequestMethod.POST)
		 public ModelAndView Regulation_Report_Url_pdf(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg, String typeReport,
				 HttpServletRequest request) {
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
			//start new pdf
				if (typeReport != null && !typeReport.equals("")) {
//						List<Map<String, Object>> list1 =RRdao.DataTableEdu_Reg_Report_masterDataList(0, -1, "", "1", "asc", "","", "");
						 ArrayList<ArrayList<String>> list=RRdao.DataTableEdu_Reg_Report_masterDataList_pdfAct();
//						
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
										TH.add("Aadhaar Number"); //7
										TH.add("Phone,Fax And Mobile Number With Email Address"); //8
										TH.add("Date of Birth And Nationality"); //9
										TH.add("Name Of Medical Degree or Diploma Obtained And University With The Month And Year Of Passing Qualification"); //10
										TH.add("Registration Particulars: 1.Registration Number 2.Date of Registration 3.Name Of The Register(National/State) 4.Whether The Registration Is Renewable Or Permanent"); //11
										TH.add("Name Of Hospital Or Institute With Complete Address For Purpose Of Teaching Or Reserach Or Practice Of Medicine"); //12
										TH.add("Name Of Person In Institution Or Hospital With Will Be Responsible For Legal Issues Regarding Patient Can Provided By Doctor Concerned"); //13

										String Heading = "\nSCHEDULE OF CREDIT";
										String username = session.getAttribute("username").toString();
										String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
										return new ModelAndView(new DownloadInventoryPdf("L", TH, Heading, username,foot),"userList",list);
									}
								}
							}
					//end
				}
				 return new ModelAndView("Regulation_Report_Url");
			// return new ModelAndView("regulation_report_reg_Tiles");
		 }
		
		@PostMapping("/getFilter_Reg_Report_dataAct")

		public @ResponseBody List<Map<String, Object>> getFilter_Edu_Reg_Report_master_dataAct(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String nrh_en_no,String first_name, String pre_state,
				String from_date,String to_date, String status,String institute_name  ) throws ParseException {
			 
			return RRdao.DataTableEdu_Reg_Report_masterDataListAct(startPage, pageLength, Search, orderColunm, orderType, nrh_en_no,first_name,pre_state,
					from_date,to_date, status,institute_name);
		}

	@PostMapping("/getTotalEdu_Reg_Report_dataCountAct")

		public @ResponseBody long getTotalEdu_Reg_Report_master_dataCountAct(HttpSession sessionUserId, String Search, String nrh_en_no,
				String first_name,String pre_state,String from_date,String to_date,String status,String institute_name) throws ParseException {
			return RRdao.DataTableEdu_Reg_Report_masterDataTotalCountAct(Search, nrh_en_no, first_name,pre_state,from_date,to_date,status,institute_name);
		}
		
		@RequestMapping(value = "/suspend_FromNCH_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> suspend_FromNCH_Data(String a,String sus_upto,String status,HttpSession session) throws ParseException {	
			SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
			String username = session.getAttribute("username").toString();

			String valid_upto1 = sus_upto;
//			Date dob = null;
//			if (!upto.equals("DD/MMM/YYYY") && !upto.equals("")) {
//				dob = formate.parse(upto);
//			}
			
			//start by lucifer

			String state_reg_no ="";
			String[] id_list = a.split(":");
			String[] id_list2 = sus_upto.split(":");

			List<String> list2 = new ArrayList<String>();
			int id = 0;
			String date;
			for (int i = 0; i < id_list.length; i++) {
				id = Integer.parseInt(id_list[i]);
				date = id_list2[i];
				list2.add(RRdao.suspend_NCHPracData(Integer.toString(id),date,status,username));
				//end
			}
			return list2;
		}
		
		//--------------------------------------------------Image_View------------------------------------------------------

		@RequestMapping(value = "/MedicalImagePath2Act", method = RequestMethod.GET)
		public void censusIdentityConvertpath(@ModelAttribute("i_id") String id,@ModelAttribute("id6") String myImg, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws IOException {

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			
			String filePath = RRdao.getImagePath1Act(i_id);

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
		@RequestMapping(value = "/kmlFileDownload4441Act")
		public void kmlFileDownload4441Act(@ModelAttribute("kmlFileId455") String id, 
				@ModelAttribute("fildname1") String fildname, ModelMap model, HttpServletRequest request,
				HttpServletResponse response, HttpSession session) throws IOException {

			final int BUFFER_SIZE = 4096;

			String filePath = RRdao.getFilePathQueryForDocFileAct(id,fildname);

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
		//Active Inactive Suspend
	  	
		@RequestMapping(value = "/Active_User_Data" , method = RequestMethod.POST)
		public @ResponseBody List<String> Active_User_Data(String a,String status,String sus_upto,String sus_reason,HttpSession session,  RedirectAttributes rd) throws ParseException {	
			String sus_no = session.getAttribute("roleSusNo").toString();

			String username = session.getAttribute("username").toString();
			List<String> list2 = new ArrayList<String>();
			
			if (sus_upto != null && sus_upto.contains(":")) {

				if (sus_reason != null && sus_reason.contains(":")) {

					String[] dt = sus_upto.split(":");

					sus_reason = sus_reason.replace(":",":|");

					String[] reason = sus_reason.split(":");

					
					System.err.println("==================");
					for (int i = 0; i < dt.length; i++) {

						if (dt[i] == null || dt[i].equals("") || dt[i].equals("//")) {
							System.err.println("inside===" + dt[i]);
							list2.add("Please Select Date for Active/Inactive/Suspend Upto");
							return list2;
						}

					}

					for (int i = 0; i < reason.length; i++) {
						System.err.println("reason[i]-----------" + reason[i]);
						if (reason[i] == null || reason[i].equals("|") || reason[i].trim().equals("|")) {
							System.err.println("reason[i]-----ifffff------" + reason[i]);
							list2.add("Please Enter Reason for Active/Inactive/Suspend");
							return list2;
						}

					}
				}
			}
			list2.add(RRdao.active_inact_user(a,status,sus_upto,sus_reason));
			return list2;
		}
}
