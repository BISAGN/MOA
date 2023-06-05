package com.AyushEdu.controller.Regulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
//import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.recr_nationality_mst;
import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Regulation.REG_NCH_WORKING_PLACE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.controller.Exp_Excel.DownloadInventoryPdf;
import com.AyushEdu.controller.Exp_Excel.DownloadPraViewPrint;
import com.AyushEdu.controller.LMS_Teacher.DownloadPdfprintForm;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.Regulation_ReportDao;
import com.AyushEdu.dao.Regulation.Search_PracDtlDAO;
import com.AyushEdu.dao.Regulation.Search_State_PracDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class search_prac_detailsController {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Search_PracDtlDAO PRdao;

	@Autowired
	CommonController common;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "admin/Search_prac_detailsUrl", method = RequestMethod.GET)
	public ModelAndView Search_prac_detailsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_prac_detailsUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

					
		org.hibernate.Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		String role = session.getAttribute("role").toString();
		

//		System.err.println("username============" + username);
//		System.err.println("role============" + role);
		
		try {
			int user_id = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
//			System.err.println("userId============" + user_id);
			 
			int state_id = (int) sessionHQ.createQuery("select state_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();		
					
//			System.err.println("state_id============" + state_id);
			int university_id = (int) sessionHQ.createQuery("select university_id from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();		
//			System.err.println("university_id============" + university_id);
			
			Mmap.put("msg", msg);
			Mmap.put("userId", user_id);
			Mmap.put("state_id", state_id);
			Mmap.put("university_id", university_id);
			
			Mmap.put("role", role);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));

		return new ModelAndView("Search_prac_details_Tiles");
	}

	@PostMapping("/getFilter_Prac_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Prac_data(HttpSession sessionUserId,int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String first_name, String status, String gender, String dob,
			String userId,String state_id,String university_id) {
		 System.err.println("check user id " + userId);
		 String role = sessionUserId.getAttribute("role").toString();
		 return PRdao.DataTableSeacrh_PracDataList(startPage, pageLength, Search, orderColunm, orderType, first_name,
				status, gender, dob, userId,state_id,university_id,role);

	}

	@PostMapping("/getTotal_Prac_dataCount")
	public @ResponseBody long getTotalState_Prac_dataCount(HttpSession sessionUserId, String Search, String first_name,
			String status, String gender, String dob, String userId,String state_id,String university_id) {
		 String role = sessionUserId.getAttribute("role").toString();
		return PRdao.DataTableSeacrh_PracDataTotalCount(Search, first_name, status, gender, dob, userId,state_id,university_id,role);

	}
	
	//start
	
		@RequestMapping(value = "/Get_Search_Status", method = RequestMethod.POST)
		
		public @ResponseBody  ArrayList<String> Get_Search_Status(String userId) {
			
			return PRdao.data_Search_Status(userId);
		}


//	----------------------------------------download

	@RequestMapping(value = "/getCertificatePDF", method = RequestMethod.POST)
	public ModelAndView getCertificatePDF(@ModelAttribute("id3") String id, ModelMap Mmap, HttpSession session,
			HttpServletRequest request, String typeReport2, String reportname1) {
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_prac_detailsUrl", roleid1);		
				if(val == false && !roleid1.equals("30") && !roleid1.equals("17")) {
					return new ModelAndView("AccessTiles");
			}
			if (typeReport2.equals("pdfL")) {

				List<String> TH = new ArrayList<String>();

				String Heading = "";
				String username = session.getAttribute("username").toString();

				ArrayList<ArrayList<String>> list = PRdao.getdataofcerti(id);
				System.err.println("chcke the list"+list);

				return new ModelAndView(new CertificateDownloadPdf_old("L", list, TH, Heading, username));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Search_prac_details_Tiles");
	}
//	-----------------view

	@RequestMapping(value = "/pract_viewUrl", method = RequestMethod.POST)
	public ModelAndView pract_viewUrl(@ModelAttribute("viewid") String viewid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_prac_detailsUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		Mmap.put("practitionerinfo", PRdao.getviewinformation(Integer.parseInt(viewid)));
		Mmap.put("vid", viewid);

//		Mmap.put("status", status);
//		Mmap.put("policycat", common.getPolicyCategoryList(sessionFactory));
//		Mmap.put("subpolicycat", common.getSubPolicyCategoryList(sessionFactory));
//		Mmap.put("policyremark", pdao.getPolicyremarkList(Integer.parseInt(viewid)));

		return new ModelAndView("search_pract_view_Tiles");

	}

	// -----------------------------viewbutton in datatable
	@RequestMapping(value = "/getView", method = RequestMethod.POST)
	public ModelAndView getView(@ModelAttribute("id7") String viewid, ModelMap Mmap, HttpSession sessionView,
			HttpServletRequest request, String typeReport7, String reportname8,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
			
		String roleid1 = sessionView.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("edu_search_pro_uni_reg_url", roleid1);		
			if(val == false && !roleid1.equals("21") && !roleid1.equals("30")  && !roleid1.equals("17") && !roleid1.equals("27")) {
				return new ModelAndView("AccessTiles");
		}
		String roleid = sessionView.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("getView", roleid);

		  
		REG_NCH_FORM_A_P disDetails = PRdao.getViewByid(Integer.parseInt(viewid));

		 ArrayList<ArrayList<String>> list= PRdao.getdataofview(viewid);
		 Mmap.put("list", list);
		 
		 ArrayList<ArrayList<String>> alist= PRdao.getdataofviewdegree(viewid);
		 Mmap.put("alist", alist);
		 System.err.println("alist00000000000000"+alist);

		Mmap.put("Pract_viewCMD", disDetails);

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

//				Mmap.put("system_id", common.getsysList( sessionFactory));
//				 Mmap.put("course_id", common.getCourseNamelist(sessionFactory));
//				 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
//				 Mmap.put("getCourseNamelist", common.getCourseNamelist(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("msg", msg);

		return new ModelAndView("Search_prac_details_View_Tiles");
	}

	@RequestMapping(value = "/admin/getViewFromHosp", method = RequestMethod.POST)
	public @ResponseBody List<REG_NCH_WORKING_PLACE_DTL_A_CH> getViewFromHosp(String id) {
		System.err.println("iddddddddd" + id);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from REG_NCH_WORKING_PLACE_DTL_A_CH where id=:id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id));
		List<REG_NCH_WORKING_PLACE_DTL_A_CH> list = (List<REG_NCH_WORKING_PLACE_DTL_A_CH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	@RequestMapping(value = "/admin/getViewFromDegree", method = RequestMethod.POST)
	public @ResponseBody List<REG_NCH_MED_DEGREE_DTL_A_CH> getViewFromDegree(String id) {
		System.err.println("iddddddddd" + id);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id));
		List<REG_NCH_MED_DEGREE_DTL_A_CH> list = (List<REG_NCH_MED_DEGREE_DTL_A_CH>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	@RequestMapping(value = "/admin/getViewFromNationality", method = RequestMethod.POST)
	public @ResponseBody List<recr_nationality_mst> getViewFromNationality(String id) {
		System.err.println("iddddddddd" + id);
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "from recr_nationality_mst where nationality_id=:id";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id));
		List<recr_nationality_mst> list = (List<recr_nationality_mst>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	// --------------------------------------------------Image_View------------------------------------------------------

	@RequestMapping(value = "/MedicalImagePath7", method = RequestMethod.GET)
	public void censusIdentityConvertpath(@ModelAttribute("i_id") String id, @ModelAttribute("id9") String myImg,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.err.println("IN IMG ACTION -------" + id);

		final int BUFFER_SIZE = 4096;

		String i_id = id;

		String filePath = PRdao.getImagePath7(i_id);
		System.err.println("IN IMG ACTION -------" + filePath);

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

			// admin//js//img//No_Image.jpg

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
	// end
	
	//start
	
////////////////////////////Download Print  form///////////////////


@RequestMapping(value = "admin/getpractitionerList", method = RequestMethod.POST)
public ModelAndView getpractitionerList(@ModelAttribute("doid1") String viewid, ModelMap Mmap, HttpSession session,
		 @RequestParam(value = "msg", required = false) String msg,
		 @RequestParam(value = "emp_id3", required = false) String emp_id3, 
		 String typeReport,String reportname,Authentication authentication,
		 HttpServletRequest request) {
	 

//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		session.invalidate();
		Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		return new ModelAndView("redirect:/landingpage");
	 }
	
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("edu_search_pro_uni_reg_url", roleid1);		
		if(val == false && !roleid1.equals("21") && !roleid1.equals("30") && !roleid1.equals("17") && !roleid1.equals("27")) {
			return new ModelAndView("AccessTiles");
	}
		
	String roleid = session.getAttribute("roleid").toString();
//	Boolean val = roledao.ScreenRedirect("getpractitionerList", roleid);
	
	
	
	REG_NCH_FORM_A_P disDetails = PRdao.getViewByid(Integer.parseInt(emp_id3));

	 ArrayList<ArrayList<String>> list= PRdao.getdataofview(emp_id3);
	 
	 ArrayList<ArrayList<String>> alist= PRdao.getdataofviewdegree(emp_id3);
	 
		if (typeReport != null && !typeReport.equals("")) {
			

				
				 if (list.size() == 0) {
						Mmap.put("msg", "Data Not Available.");
					} else {
						
						Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
						Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
						Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
						Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
						
						Mmap.put("list", list);
						 Mmap.put("alist", alist);
						Mmap.put("Pract_viewCMD", disDetails);
						
						 System.err.println("listrtrt=========="+list);
						System.err.println("print---serverside-----"+disDetails);
						System.err.println("alist{{{{{{{{{{{{{{{"+alist);
						if (typeReport != null && typeReport.equals("pdfL")) {
							if (list.size() > 0) {
								List<String> TH = new ArrayList<String>();
								TH.add("NRH Enrollment Number");// 0
								TH.add("Name of Professional");// 1
								TH.add("Father's Name ");// 2
								TH.add("Present Correspondence address");// 3
								TH.add("Permanent Address");// 4
								TH.add("E-mail Id");// 5
								TH.add("Date of Birth");// 6
								TH.add("Nationality");// 7
						//		TH.add("Name of Medical Degree or Diploma Obtained and University With The Month And Year if Passing Qualification");// 8
								TH.add("State Registration Number");// 9
								TH.add("Names(s) of The Register(National/State) ");// 10
								TH.add("Name of Hospital or University With Complete Address For Purposes of Teaching or Research or Practice, of Medicine");// 11
								TH.add("Name of Person in University or Hospital Who Will be Responsible For Legal Issues Regarding Patient Care Provided by Doctor Concerned");// 12
								TH.add("Valid Upto");// 13

								String Heading = "\nSCHEDULE OF CREDIT";
								String username = session.getAttribute("username").toString();
								return new ModelAndView(new DownloadPraViewPrint(Heading, TH, username, "L", list, alist  ),"userList",list);
							}
						}
					}
				
			
			//end
 
		}
		
	 return new ModelAndView("redirect:edu_search_pro_uni_reg_url");
}


}
