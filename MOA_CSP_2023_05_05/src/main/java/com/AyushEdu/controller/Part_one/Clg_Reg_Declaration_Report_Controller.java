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
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_reg_College_Declaration_Report_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Declaration_Report_Controller {
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
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	Clg_reg_College_Declaration_Report_DAO cdrdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
//////////////////View_url

@RequestMapping(value = "/Declaration_ViewUrl", method = RequestMethod.POST)
public ModelAndView Declaration_ViewUrl(@RequestParam(value = "declaration_id", required = false) String id, ModelMap Mmap,
HttpSession session, @RequestParam(value = "msg", required = false) String msg,
HttpServletRequest request) {
Session sessionHQL = this.sessionFactory.openSession();
Mmap.put("main_view_id", id);
String userid = session.getAttribute("userId_for_jnlp").toString();
String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
String role = session.getAttribute("role").toString();
List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
Integer.parseInt(institute_id), role, userid,"clg_reg_college_declaration","institute_id");

if(getPidfromInstidReport.isEmpty()) {
	Mmap.put("msg", "Please Save Declaration Details First");
	return new ModelAndView("redirect:declaration");
}

String p_id = getPidfromInstidReport.get(0).get("id").toString();
Mmap.put("getPidfromInstidReport", p_id); 
System.err.println("22/2/23----------------id------>" + id);
System.err.println("institute_id----------------id------>" + institute_id);
//CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY viewid = (CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY) sessionHQL
//.get(CLG_REG_DEP_EQUIP_OBSTETRIC_AND_GYNACOLOGY.class, Integer.parseInt(p_id));
//Mmap.put("View_Hospital_Equipmentrrrr", viewid);

List<Map<String, Object>> GetPrinacipal_Name = cdrdao.GetPrinacipal_Name(Integer.parseInt(id),
Integer.parseInt(id), role);
Mmap.put("Prinacipal_NameView", GetPrinacipal_Name);
Mmap.put("login_name", session.getAttribute("roleloginName").toString());
Mmap.put("getMedInstituteName", common.getMedInstituteName(sessionFactory));
Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
Mmap.put("inst_id", id);
Mmap.put("Dec_docdata", cdrdao.getDeclaration_UploadDocumentsFetch(id));

Mmap.addAttribute("msg", msg);
return new ModelAndView("declaration_view");
}



}
