package com.AyushEdu.controller.Part_one;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hospital_Staff_List_Report_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Opd_Ipd_Report_DAO;
import com.AyushEdu.dao.Part_One.Hospital_Staff_Details_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Hospital_Opd_Ipd_Report_Controller {
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private Hospital_Opd_Ipd_Report_DAO HOIRDao;
	
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Dao CRDao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
			
//==========================================VIEW===================================================================//
		
		@RequestMapping(value = "/hospital_ipdopd_view", method = RequestMethod.POST)
		public ModelAndView hospital_ipdopd_view(@RequestParam(value = "hos_ipd_id", required = false) String id, ModelMap Mmap,
		HttpSession session, @RequestParam(value = "msg", required = false) String msg,
		HttpServletRequest request) {

				Session sessionHQL = this.sessionFactory.openSession();
				Mmap.put("main_view_id", id);
				System.err.println("iddddddddddddddddddddddddddd"+id);
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
				String role = session.getAttribute("role").toString();
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				
				Mmap.put("getHospital_ipd_patientTableDepart_Fetch", CRDao.getHospital_ipd_patientTableDepart_Fetch(institude));
				Mmap.put("getHospital_Bed_Days_Occupied_TableDepartFetch", CRDao.getHospital_Bed_Days_Occupied_TableDepartFetch(institude));
				
				List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
						Integer.parseInt(id), role, userid,"clg_reg_hos_opd_ipd","institute_id");
				
				if(getPidfromInstidReport.isEmpty()) {
					Mmap.put("msg", "Please Save hospital Details First");
					return new ModelAndView("redirect:hospital_ipdopd");
				}
				
						System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
						String p_id = getPidfromInstidReport.get(0).get("id").toString();
				         Mmap.put("getPidfromInstidReport", p_id); 

							System.err.println("22/2/23----------------id------>" + id);

								System.err.println("institute_id----------------id------>" + institute_id);

//								HOSP_STAFF_DETAIL_UP_DOCUMENT viewid = (HOSP_STAFF_DETAIL_UP_DOCUMENT) sessionHQL
//								.get(HOSP_STAFF_DETAIL_UP_DOCUMENT.class, Integer.parseInt(p_id));
		//
		//
//						Mmap.put("View_Hospital_Equipmentrrrr", viewid);

							List<Map<String, Object>> View_OPD_Patients1 = HOIRDao.View_OPD_Patients(Integer.parseInt(id),
										Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_OPD_Patientssum1 = HOIRDao.View_OPD_Patientssum(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_IPD_Patients2 = HOIRDao.View_IPD_Patients(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_IPD_Patientssum2 = HOIRDao.View_IPD_Patientssum(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Bed_Days_Occupied3 = HOIRDao.View_Bed_Days_Occupied(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Bed_Days_Occupiedsum3 = HOIRDao.View_Bed_Days_Occupiedsum(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
							
							List<Map<String, Object>> View_Beds_Existed4 = HOIRDao.View_Beds_Existed(Integer.parseInt(id),
									Integer.parseInt(id), role,userid);
//							System.err.println("View_Hospital_Staff_Details2--------------"+View_Hospital_Staff_Details2);
							
							
							Mmap.put("View_OPD_Patients1", View_OPD_Patients1);
							Mmap.put("View_OPD_Patientssum1", View_OPD_Patientssum1);
							Mmap.put("View_IPD_Patients2", View_IPD_Patients2);
							Mmap.put("View_IPD_Patientssum2", View_IPD_Patientssum2);
							Mmap.put("View_Bed_Days_Occupied3", View_Bed_Days_Occupied3);
							Mmap.put("View_Bed_Days_Occupiedsum3", View_Bed_Days_Occupiedsum3);
							Mmap.put("View_Beds_Existed4", View_Beds_Existed4);
							Mmap.put("docdata", CRDao.getHosptal_opd_ipd_UploadDocumentsFetch(id));
							Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
							Mmap.put("inst_id", id);


		Mmap.addAttribute("msg", msg);
		return new ModelAndView("hospital_ipdopd_view");
		}
		
		
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/CommonDocPartOne")
		public void CommonDocPartOne(@ModelAttribute("kmlId2") String id,@ModelAttribute("val444") String val,
			@ModelAttribute("fildname1") String fildname,
		 ModelMap model ,HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws IOException{
			
		final int BUFFER_SIZE = 4096;
		System.out.println("fildname==============="+fildname);
		System.out.println("table==============="+val);
		System.out.println("id==============="+id);

		//String base64EncodedDcryptedad2 = "";
		//String enckey2 = "commonPwdEncKeys";
		//id = id.replace(" ", "+");
		//base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);

		//String id2 = (base64EncodedDcryptedad2);	

		String filePath = HOIRDao.getFilePath_DynemicQueryForCommonDocPartOne(id,val,fildname);


		model.put("filePath",filePath);


		ServletContext context = request.getSession().getServletContext();
		try{
		if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
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
		          String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
		        String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			ServletOutputStream outStream = response.getOutputStream();

			int bytesRead = -1;

			try {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/assets/db_img/noimage.jpeg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);

				byte[] buffer = new byte[BUFFER_SIZE];

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
			} catch (Exception e) {

				byte[] buffer = new byte[BUFFER_SIZE];
				outStream.write(buffer, 0, bytesRead);
			}

			outStream.close();

			}		        	
		}
		
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/CommonDocPartOnezip",method = RequestMethod.POST)
		@ResponseBody void CommonDocPartOnezip(@ModelAttribute("kmlId2") String id,@ModelAttribute("val444") String val,
			@ModelAttribute("fildname1") String fildname,
		 ModelMap model ,HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws IOException{
			
		final int BUFFER_SIZE = 4096;
		System.out.println("fildname==============="+fildname);
		System.out.println("table==============="+val);
		System.out.println("id==============="+id);

		//String base64EncodedDcryptedad2 = "";
		//String enckey2 = "commonPwdEncKeys";
		//id = id.replace(" ", "+");
		//base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);

		//String id2 = (base64EncodedDcryptedad2);	

		String filePath = HOIRDao.getFilePath_DynemicQueryForCommonDocPartOne(id,val,fildname);


		model.put("filePath",filePath);


		ServletContext context = request.getSession().getServletContext();
		try{
		if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
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
		          String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
		        String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
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
			ServletOutputStream outStream = response.getOutputStream();

			int bytesRead = -1;

			try {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/assets/db_img/noimage.jpeg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);

				byte[] buffer = new byte[BUFFER_SIZE];

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
			} catch (Exception e) {

				byte[] buffer = new byte[BUFFER_SIZE];
				outStream.write(buffer, 0, bytesRead);
			}

			outStream.close();

			}		        	
		}

}
