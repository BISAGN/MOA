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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Department_Equipment_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Department_Equipments_Report_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao CRRDao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Commondao cmdao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_Reg_Department_Equipment_Report_Dao derdao; 
	
//////////////////View_url

	@RequestMapping(value = "/View_Department_Equipment_ReportUrl", method = RequestMethod.POST)
	public ModelAndView View_Department_Equipment_ReportUrl(
			@RequestParam(value = "department_eqp_id", required = false) String id, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("main_view_id", id);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String role = session.getAttribute("role").toString();
		List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
				Integer.parseInt(id), role, userid, "clg_reg_dep_equip_obstetric_and_gynacology", "inst_id");

		if (getPidfromInstidReport.isEmpty()) {
			Mmap.put("msg", "Please Save Department Equipments Details First");
			return new ModelAndView("redirect:department_equipments");
		}

		String p_id = getPidfromInstidReport.get(0).get("id").toString();
		Mmap.put("getPidfromInstidReport", p_id);
		System.err.println("22/2/23----------------id------>" + id);
		System.err.println("institute_id----------------id------>" + institute_id);
//CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY viewid = (CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY) sessionHQL
//.get(CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY.class, Integer.parseInt(p_id));
//Mmap.put("View_Hospital_Equipmentrrrr", viewid);

		List<Map<String, Object>> getObstricView = derdao.getObstricView(Integer.parseInt(id));
		List<Map<String, Object>> getAnatomyView = derdao.getAnatomyView(Integer.parseInt(p_id), Integer.parseInt(id),
				role);
		List<Map<String, Object>> getAllinfo_anatomy_child = derdao.getAllinfo_anatomy_childView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getcommunity_medicineView = derdao.getcommunity_medicineView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getcommunity_medicine_childView = derdao
				.getcommunity_medicine_childView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getForensic_Medicine_ToxicologyEquipView = derdao
				.getForensic_Medicine_ToxicologyEquipView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getForensic_Medicine_ToxicologyActView = derdao
				.getForensic_Medicine_ToxicologyActView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getHomeo_PharmView = derdao.getHomeo_PharmView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getHomeo_Pharm_ChildView = derdao.getHomeo_Pharm_ChildView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getEquip_organon_medicineView = derdao
				.getEquip_organon_medicineView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getpathology_microbiologyView = derdao
				.getpathology_microbiologyView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getPhysiology_BiochemistryView = derdao
				.getPhysiology_BiochemistryView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getBiochemistryView = derdao.getBiochemistryView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getPractice_MedView = derdao.getPractice_MedView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getRepertoryView = derdao.getRepertoryView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		List<Map<String, Object>> getSurgeryView = derdao.getSurgeryView(Integer.parseInt(p_id), Integer.parseInt(id),
				role);
		List<Map<String, Object>> getHMMView = derdao.getHMMView(Integer.parseInt(p_id), Integer.parseInt(id), role);
		List<Map<String, Object>> getPsychView = derdao.getPsychView(Integer.parseInt(p_id), Integer.parseInt(id),
				role);
		List<Map<String, Object>> getPediatricView = derdao.getPediatricView(Integer.parseInt(p_id),
				Integer.parseInt(id), role);
		Mmap.put("Obstric", getObstricView);
		Mmap.put("Anatomy", getAnatomyView);
		Mmap.put("AnatomyChild", getAllinfo_anatomy_child);
		Mmap.put("Community_Medicine", getcommunity_medicineView);
		Mmap.put("Community_Medicine_Child", getcommunity_medicine_childView);
		Mmap.put("Forensic_Medicine_ToxicologyEquip", getForensic_Medicine_ToxicologyEquipView);
		Mmap.put("getForensic_Medicine_ToxicologyAct", getForensic_Medicine_ToxicologyActView);
		Mmap.put("getHomeo_Pharm", getHomeo_PharmView);
		Mmap.put("getHomeo_Pharm_Child", getHomeo_Pharm_ChildView);
		Mmap.put("getEquip_organon_medicine", getEquip_organon_medicineView);
		Mmap.put("pathology_microbiology", getpathology_microbiologyView);
		Mmap.put("Physiology_Biochemistry", getPhysiology_BiochemistryView);
		Mmap.put("Biochemistry", getBiochemistryView);
		Mmap.put("Practice_MedView", getPractice_MedView);
		Mmap.put("getRepertoryView", getRepertoryView);
		Mmap.put("getSurgeryView", getSurgeryView);
		Mmap.put("getHMMView", getHMMView);
		Mmap.put("getPsychView", getPsychView);
		Mmap.put("getPediatricView", getPediatricView);
		Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
		Mmap.put("inst_id", id);

		Mmap.addAttribute("msg", msg);
		return new ModelAndView("department_equipments_view");
	}
	
	
//
//@SuppressWarnings("null")
//@RequestMapping(value = "/Documentpreview_Part_One")
//public void Documentpreview_Part_One(@ModelAttribute("kmlId2") String id,@ModelAttribute("val444") String val,
//	@ModelAttribute("fildname1") String fildname,
// ModelMap model ,HttpServletRequest request,HttpServletResponse response,
//	HttpSession session) throws IOException{
//	
//final int BUFFER_SIZE = 4096;
//System.out.println("fildname==============="+fildname);
//		
////String base64EncodedDcryptedad2 = "";
////String enckey2 = "commonPwdEncKeys";
////id = id.replace(" ", "+");
////base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);
//
////String id2 = (base64EncodedDcryptedad2);	
//
//String filePath = derdao.getFilePath_DynemicQueryForDoc_part_one(id,val,fildname);
//
//
//model.put("filePath",filePath);
//
//
//ServletContext context = request.getSession().getServletContext();
//try{
//if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
//{
//    @SuppressWarnings("deprecation")
//	String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
//          File downloadFile = new File(fullPath);
//          FileInputStream inputStream = new FileInputStream(downloadFile);
//          String mimeType = context.getMimeType(fullPath);
//          if (mimeType == null) {
//              mimeType = "application/octet-stream";
//          }
//          response.setContentType(mimeType);
//          response.setContentLength((int) downloadFile.length());
//          String headerKey = "Content-Disposition";
//          String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
//          response.setHeader(headerKey, headerValue);
//          ServletOutputStream outStream = response.getOutputStream();
//          byte[] buffer = new byte[BUFFER_SIZE];
//          int bytesRead = -1;
//          while ((bytesRead = inputStream.read(buffer)) != -1) {
//              outStream.write(buffer, 0, bytesRead);
//          }
//          inputStream.close();
//          outStream.close();
//}
//else
//{
//	
//	    String fullPath =  filePath; 
//        File downloadFile = new File(fullPath);
//        FileInputStream inputStream = new FileInputStream(downloadFile);
//        String mimeType = context.getMimeType(fullPath);
//        if (mimeType == null) {
//            mimeType = "application/octet-stream";
//        }
//        response.setContentType(mimeType);
//        response.setContentLength((int) downloadFile.length());
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
//        response.setHeader(headerKey, headerValue);
//        ServletOutputStream outStream = response.getOutputStream();
//        byte[] buffer = new byte[BUFFER_SIZE];
//        int bytesRead = -1;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//        inputStream.close();
//        outStream.close();
//      }
//}catch(Exception ex)
//{
//	ServletOutputStream outStream = response.getOutputStream();
//
//	int bytesRead = -1;
//
//	try {
//		@SuppressWarnings("deprecation")
//		String fullPath = request.getRealPath("/") + "admin/assets/db_img/noimage.jpeg";
//		File downloadFile = new File(fullPath);
//		FileInputStream inputStream = new FileInputStream(downloadFile);
//		String mimeType = context.getMimeType(fullPath);
//		if (mimeType == null) {
//			mimeType = "application/octet-stream";
//		}
//		response.setContentType(mimeType);
//		response.setContentLength((int) downloadFile.length());
//		String headerKey = "Content-Disposition";
//		String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
//		response.setHeader(headerKey, headerValue);
//
//		byte[] buffer = new byte[BUFFER_SIZE];
//
//		while ((bytesRead = inputStream.read(buffer)) != -1) {
//			outStream.write(buffer, 0, bytesRead);
//		}
//		inputStream.close();
//	} catch (Exception e) {
//
//		byte[] buffer = new byte[BUFFER_SIZE];
//		outStream.write(buffer, 0, bytesRead);
//	}
//
//	outStream.close();
//
//	}		        	
//}

}
