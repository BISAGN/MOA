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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class College_Departmentcomp_Printer_Avail_Report_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao CRRDao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao clgDAO;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
	// ----------------search-------------------------

	@GetMapping(value = "/Search_College_Department_url")
	public ModelAndView Search_College_Department_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		model.put("msg", msg);
		return new ModelAndView("Search_College_DepartmentTiles");
	}
	
	@PostMapping("/getFilterSearch_College_Department_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_College_Department_data(
			HttpSession sessionUserId, int startPage, int pageLength, String Search, String orderColunm,
			String orderType, String department, String computer, String printer) {

		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return CRRDao.DataTableSearch_College_DepartmentDataList(startPage, pageLength, Search, orderColunm,
				orderType, department, computer, printer, role, userid,
				institute_id);

	}

	@PostMapping("/getTotalSearch_College_Department_dataCount")
	public @ResponseBody long getTotalSearch_College_Department_dataCount(HttpSession sessionUserId, String Search,
			String department, String computer, String printer) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return CRRDao.DataTableSearch_College_DepartmentDataTotalCount(Search, department, computer, printer, role, userid,
				institute_id);
	}
	
	//////////////////View_url

	@RequestMapping(value = "/View_Search_College_DepartmentUrl", method = RequestMethod.POST)
	public ModelAndView View_Search_College_DepartmentUrl(@RequestParam(value = "college_department_id", required = false) String id, ModelMap Mmap,
	HttpSession session, @RequestParam(value = "msg", required = false) String msg,
	HttpServletRequest request) {

		
		System.out.println("college_department_id  "+id);
		Session sessionHQL = this.sessionFactory.openSession();
//		Mmap.put("id6", id);
		Mmap.put("main_view_id", id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.err.println("view main id---------------"+id);
	String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
	String role = session.getAttribute("role").toString();
	
	List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
			Integer.parseInt(id), role, userid,"clg_reg_clg_dept_comp_printer_avail","institute_id");
			
			System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
			String p_id = getPidfromInstidReport.get(0).get("id").toString();
			Mmap.put("getPidfromInstidReport", p_id); 

	System.err.println("22/2/23----------------id------>" + id);
	System.err.println("22/2/23----------------id------>" + p_id);

	CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL viewid = (CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL) sessionHQL
		.get(CLG_REG_CLG_DEPT_COMP_PRINTER_AVAIL.class, Integer.parseInt(id));
	Mmap.put("Search_College_Department_CMD", viewid);

	List<Map<String, Object>> getComp_Printer_Avail_View = CRRDao.getComp_Printer_Avail_View(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> getUG_Depart_From_View = CRRDao.getUG_Depart_From_View(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> getPG_Depart_From_View = CRRDao.getPG_Depart_From_View(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> getDepart_tours_View = CRRDao.getDepart_tours_View(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	List<Map<String, Object>> getDepart_academic_performance_View = CRRDao.getDepart_academic_performance_View(Integer.parseInt(p_id),
			Integer.parseInt(id), role);
	Mmap.put("getComp_Printer_Avail_View", getComp_Printer_Avail_View);
	Mmap.put("getUG_Depart_From_View", getUG_Depart_From_View);
	Mmap.put("getPG_Depart_From_View", getPG_Depart_From_View);
	Mmap.put("getDepart_tours_View", getDepart_tours_View);
	Mmap.put("getDepart_academic_performance_View", getDepart_academic_performance_View);
	
	Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
	Mmap.put("inst_id", id);
	Mmap.put("getDepart_tours_View", getDepart_tours_View);
//	Mmap.put("docdata", CRDao.getHosptal_opd_ipd_UploadDocumentsFetch(id));
	Mmap.addAttribute("msg", msg);
	return new ModelAndView("college_department_view");
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/Documentpreviewclg_dept_view_doc")
	public void Documentpreviewclg_dept_view_doc(@ModelAttribute("kmlId2") String id,@ModelAttribute("val444") String val,
		@ModelAttribute("fildname1") String fildname,
	 ModelMap model ,HttpServletRequest request,HttpServletResponse response,
		HttpSession session) throws IOException{
		
	final int BUFFER_SIZE = 4096;
	System.out.println("fildname==============="+fildname);
			
	//String base64EncodedDcryptedad2 = "";
	//String enckey2 = "commonPwdEncKeys";
	//id = id.replace(" ", "+");
	//base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);

	//String id2 = (base64EncodedDcryptedad2);	

	String filePath = clgDAO.getFilePath_DynemicQueryForDoc_clg_dept_view(id,val,fildname);


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
