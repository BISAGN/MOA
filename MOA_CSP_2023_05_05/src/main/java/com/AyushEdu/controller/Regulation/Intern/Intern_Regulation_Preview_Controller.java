package com.AyushEdu.controller.Regulation.Intern;

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
public class Intern_Regulation_Preview_Controller {
	
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
 
		@RequestMapping(value = "admin/stupract_previewUrl", method = RequestMethod.GET)
		public ModelAndView stupract_previewUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
				if(val == false && !roleid1.equals("21")) {
					return new ModelAndView("AccessTiles");
			}
		
		Calendar calendar = Calendar.getInstance();
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
 		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
  
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();

		try {
//			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
//					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			
				int data =  Integer.parseInt(request.getParameter("id7"));
				username = (String) sessionHQ.createQuery("select userName from UserLogin where userId=:id")
						.setParameter("id", data).setMaxResults(1).uniqueResult();
				Mmap.put("username", username);
				Mmap.put("userId", data);
			System.err.println("data---------" + data+" username---------" + username);
			if (data != 0) {
				Mmap.put("setDataCMD", RPdao.getDataByUserNameForDraftPreview(data));
				Mmap.put("setAddMoreMedicalCMD", RPdao.medicalDataPreview(data));
			//	Mmap.put("setAddMoreHospCMD", RPdao.HospitalDataPreview(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", RPdao.medicalDataChildAttachmentPreview(data));
				//Mmap.put("CheckNRH", RPdao.CheckNRHPreview(data));
				//Mmap.put("setRegAuth", RPdao.RegAuthPreview(data));
				String pid = RPdao.getUserIdPreview(data);
				Mmap.put("p_id", pid);
   				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
 					Mmap.put("parentid", data2);
 					Mmap.put("valid_dt", INF.getValid_up_to());
 					if (INF.getStatus() == 0) {
						Mmap.put("hid", "2");
 					}
					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
						Mmap.put("hid", "1");
					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1
							&& INF.getNrh_status() == 1) {
						Mmap.put("hid", "3");

					} else {
						Mmap.put("hid", "0");
					}
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

		return new ModelAndView("intern_pract_preview_Tiles");

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
		
		 
		
		
		 
		 
		
		
		
		

}
