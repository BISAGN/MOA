package com.AyushEdu.controller.Alumni;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.AyushEdu.Models.Alumni.EDU_ALUM_REG_ALUMNI_CLG;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Alumni.Edit_Profile_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Edit_Profile_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Edit_Profile_Dao TDDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/Edit_Profile_Url", method = RequestMethod.GET)
	public ModelAndView Edit_Profile_Url(ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			HttpSession session,HttpServletRequest request) {

		try {
			//SECURITY -- RIDDHI
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Edit_Profile_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String userId = session.getAttribute("userId_for_jnlp").toString();
			
			
			List<Map<String, Object>> GetTechnical_Details_Data = TDDAO.GetTechnical_Details_Data(Integer.parseInt(userId)); 
			
			System.err.println("GetTechnical_Details_Data----"+GetTechnical_Details_Data);
			
			
//		List<ArrayList<String>> litechildlist = ANLADAO.getAdd_Non_Lecture_Activities_Child_By_id(id);
		
//		model.put("GetTechnical_Details_Data", TDDAO.GetTechnical_Details_Data(id));
//		model.put("list", GetAdd_Non_Lecture_Activities_Parent_Data);
			model.put("GetTechnical_Details_Data", GetTechnical_Details_Data);
			model.put("getDegreeList", common.getDegreeList(sessionFactory));
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			model.put("sector_id", common.getsector_id(sessionFactory));
			 
		
		model.put("msg", msg);
	  } catch (Exception e) {
			e.printStackTrace();
	  }
		return new ModelAndView("Edit_Profile_Tiles");
	}
	
	@RequestMapping(value = "/getTechnical_DetailsViewdata", method = RequestMethod.POST)
	public @ResponseBody   List<Map<String, Object>> getTechnical_DetailsViewdata(int id) {
//		System.err.println("----course_id-------"+course_id);
		List<Map<String, Object>> list = TDDAO.GetTechnical_Details_Data(id);
		
		System.err.println("id============"+id);
		return list;
	}
	
	
//	@RequestMapping(value = "/Edit_ProfileAction", method = RequestMethod.POST)
//	public ModelAndView Edit_ProfileAction(@ModelAttribute("Edit_ProfileCMD") EDU_ALUM_REG_ALUMNI_CLG rs,
//			HttpServletRequest request, ModelMap model, HttpSession session,MultipartHttpServletRequest mul,
//			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException, IOException {
	
	@PostMapping(value = "/Edit_ProfileAction")
	public ModelAndView Edit_ProfileAction(
			@Validated @ModelAttribute("Edit_ProfileCMD") EDU_ALUM_REG_ALUMNI_CLG rs,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,MultipartHttpServletRequest mul) throws IOException {
		
		//SECURITY -- RIDDHI
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Edit_Profile_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		Date date = new Date();

		String id = request.getParameter("id");
		String degree_id = request.getParameter("degree_id").trim();
		String specialization_id = request.getParameter("specialization_id").trim();
		String others = request.getParameter("others");
		String alum_name = request.getParameter("alum_name");
		String alum_address = request.getParameter("alum_address");
		String alum_email = request.getParameter("alum_email");
		String alum_mo_no = request.getParameter("alum_mo_no");
		String alum_passing_year = request.getParameter("alum_passing_year");
		String alum_batch = request.getParameter("alum_batch");
		String alum_insta_id = request.getParameter("alum_insta_id");
		String alum_fb_id = request.getParameter("alum_fb_id");
		String alum_linkdin_id = request.getParameter("alum_linkdin_id");
		String alum_curr_occu = request.getParameter("alum_curr_occu");
		String sector = request.getParameter("sector");
		
//		String alum_photo = gdC(request, mul, session,"alum_photo");
//		//end
		String upload_img_hid = request.getParameter("upload_img_hid").trim();
//		
//		System.err.println("alum_photo"+alum_photo);
//		System.err.println("upload_img_hid"+upload_img_hid);
		
		
		
		

//		if (degree_id.equals("0")) {
//			ra.addAttribute("msg", "Please Select Degree");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (specialization_id.equals("0")) {
//			ra.addAttribute("msg", "Please Select Specialization");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (others == null || others.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Others.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (validation.isOnlyAlphabet(others) == false) {
//			ra.addAttribute("msg", "Others " + validation.isOnlyAlphabetMSG);
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (validation.maxlengthcheck(others) == false) {
//			ra.addAttribute("msg","Others "+ validation.MaxlengthcheckMSG);
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (alum_name == null || alum_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Name.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (validation.isOnlyAlphabet(alum_name) == false) {
//			ra.addAttribute("msg", "Name " + validation.isOnlyAlphabetMSG);
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (validation.maxlengthcheck(alum_name) == false) {
//			ra.addAttribute("msg","Name "+ validation.MaxlengthcheckMSG);
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (alum_address == null || alum_address.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Address.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (alum_email == null || alum_email.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Email.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (alum_mo_no == null || alum_mo_no.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Mobile Number.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
//		if (alum_passing_year == null || alum_passing_year.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Passing Year.");
//			return new ModelAndView("redirect:Edit_Profile_Url");
//		}
		MultipartFile file2 = mul.getFile("alum_photo");
		
		//SECURITY-----
		if (file2.getOriginalFilename().isEmpty()) {
			ra.addAttribute("msg","Please Upload File");
			return new ModelAndView("redirect:Create_event_Url");
		}
		if (!file2.getOriginalFilename().isEmpty()) {
			String upload_fileEXt = FilenameUtils.getExtension(file2.getOriginalFilename()).toLowerCase();
			if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
				ra.addAttribute("msg","Only *.jpg, *.jpeg and *.png file extensions allowed");
				return new ModelAndView("redirect:Edit_Profile_Url");
			}
			long filesize = file2.getSize() / 1024;
			if (filesize > 50) {
				ra.addAttribute("msg","File size should be 50 kb or less");
				return new ModelAndView("redirect:Edit_Profile_Url");
			}
		}
		//SECURITY-----
		
		String prof_photo="";
		
		if (!file2.getOriginalFilename().isEmpty()) {
			prof_photo = common.fileupload(file2.getBytes(), file2.getOriginalFilename(),1, "signature" + "1");
		}
		if (upload_img_hid.trim().equals("")) {
			upload_img_hid=prof_photo;
		}
		
		try {
			
				String hql = "update EDU_ALUM_REG_ALUMNI_CLG set alum_photo=:alum_photo,degree_id=:degree_id,specialization_id=:specialization_id,others=:others,"
						+ "alum_name=:alum_name,alum_address=:alum_address,alum_email=:alum_email,alum_mo_no=:alum_mo_no,alum_passing_year=:alum_passing_year,"
						+ "alum_batch=:alum_batch,alum_insta_id=:alum_insta_id,alum_fb_id=:alum_fb_id,alum_linkdin_id=:alum_linkdin_id,"
						+ "alum_curr_occu=:alum_curr_occu,sector=:sector,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql)
						.setParameter("alum_photo", prof_photo)
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("specialization_id", Integer.parseInt(specialization_id))
						.setParameter("others", others)
						.setParameter("alum_name", alum_name)
						.setParameter("alum_address", alum_address)
						.setParameter("alum_email", alum_email)
						.setParameter("alum_mo_no", alum_mo_no)
						.setParameter("alum_passing_year", alum_passing_year)
						.setParameter("alum_batch", alum_batch)
						.setParameter("alum_insta_id", alum_insta_id)
						.setParameter("alum_fb_id", alum_fb_id)
						.setParameter("alum_linkdin_id", alum_linkdin_id)
						.setParameter("alum_curr_occu", alum_curr_occu)
						.setParameter("sector", Integer.parseInt(sector))
						.setParameter("modified_by", Integer.parseInt(userid))
						.setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
				
						String msg = query.executeUpdate() > 0 ? "Data Updated Succefully" : "Data Not Updated";
						
						ra.addAttribute("msg", msg);
				
				tx.commit();
				ra.addAttribute("msg", "Data Updated Successfully.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				rbe.printStackTrace();
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:Edit_Profile_Url");
	}
	
public String gdC(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
		
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

@RequestMapping(value = "/MedicalImagePathC", method = RequestMethod.GET)
public void MedicalImagePathC(@ModelAttribute("i_id") String id,@ModelAttribute("id4") String myImg, ModelMap model,
		HttpServletRequest request, HttpServletResponse response) throws IOException {
	
//	System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

	final int BUFFER_SIZE = 4096;

	String i_id = id;

	
	String filePath = TDDAO.getImagePathC(i_id);

//	System.out.println("chgukhdfguhkdfhgkj---" + filePath);

	model.put("filePath", filePath);
	System.err.println("filePath"+filePath);

	ServletContext context = request.getSession().getServletContext();

	try {

		if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//			request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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
	}
}
}
