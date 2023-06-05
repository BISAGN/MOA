package com.AyushEdu.controller.Part_one;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_TEACHER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_Report_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_ReportDAO;
import com.AyushEdu.dao.Part_One.Clg_reg_College_Declaration_Report_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Report_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Staff_List_Report_Controller {
	
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
	Institution_Basic_Details_Report_Dao sibdao;
	
	@Autowired
	private Clg_Reg_College_Staff_List_ReportDAO CSLRDao;
	
	@Autowired
	Clg_reg_College_Declaration_Report_DAO cdrdao;
	
	@Autowired
	Clg_Reg_College_Infrastructure_Report_Dao CIRDao;
	
//////////////////View_url

@RequestMapping(value = "/College_Staff_List_ViewUrl", method = RequestMethod.POST)
public ModelAndView College_Staff_List_ViewUrl(@RequestParam(value = "basic_clg_staff_list_id", required = false) String id, ModelMap Mmap,
HttpSession session, @RequestParam(value = "msg", required = false) String msg,
HttpServletRequest request) {

Session sessionHQL = this.sessionFactory.openSession();
Mmap.put("main_view_id", id);
String userid = session.getAttribute("userId_for_jnlp").toString();
String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
String role = session.getAttribute("role").toString();
List<Map<String, Object>> getPidfromInstidReport = sibdao.getPidfromInstidReport(Integer.parseInt(id),
Integer.parseInt(institute_id), role, userid,"clg_reg_college_staff_list_teacher","institute_id");

System.err.println("getPidfromInstidReport=========================="+getPidfromInstidReport);
String p_id = getPidfromInstidReport.get(0).get("id").toString();
Mmap.put("getPidfromInstidReport", p_id); 

System.err.println("22/2/23----------------id------>" + id);

System.err.println("institute_id----------------id------>" + institute_id);

CLG_REG_COLLEGE_STAFF_LIST_TEACHER viewid = (CLG_REG_COLLEGE_STAFF_LIST_TEACHER) sessionHQL
.get(CLG_REG_COLLEGE_STAFF_LIST_TEACHER.class, Integer.parseInt(p_id));


Mmap.put("college_staff_list_viewCMD", viewid);
//Mmap.put("View_Search_Basic_InfochCMD", viewchid);

List<Map<String, Object>> getClg_Staff_List_Teacher_View = CSLRDao.getClg_Staff_List_Teacher_View(Integer.parseInt(p_id),
Integer.parseInt(id), role);
List<Map<String, Object>> getClg_Staff_List_Guest_View = CSLRDao.getClg_Staff_List_Guest_View(Integer.parseInt(p_id),
Integer.parseInt(id), role);
List<Map<String, Object>> getClg_Staff_List_Non_Teaching_View = CSLRDao.getClg_Staff_List_Non_Teaching_View(Integer.parseInt(p_id),
Integer.parseInt(id), role);
List<Map<String, Object>> getClg_Staff_List_Upload_Doc_View = CSLRDao.getClg_Staff_List_Upload_Doc_View(Integer.parseInt(p_id),
Integer.parseInt(id), role);
List<Map<String, Object>> GetPrinacipal_Name = cdrdao.GetPrinacipal_Name(Integer.parseInt(p_id),
		Integer.parseInt(id), role);
Mmap.put("getClg_Staff_List_Teacher_View", getClg_Staff_List_Teacher_View);
Mmap.put("getClg_Staff_List_Guest_View", getClg_Staff_List_Guest_View);
Mmap.put("getClg_Staff_List_Non_Teaching_View", getClg_Staff_List_Non_Teaching_View);
Mmap.put("getClg_Staff_List_Upload_Doc_View", getClg_Staff_List_Upload_Doc_View);
Mmap.put("Prinacipal_NameView", GetPrinacipal_Name);



Mmap.put("getView_idFrom_Institute_id", CIRDao.getView_idFrom_Institute_id(Integer.parseInt(id)));
Mmap.put("inst_id", id);

Mmap.addAttribute("msg", msg);
return new ModelAndView("college_staff_list_view");
}

//@RequestMapping(value = "/saveDraft_staff_list_remark_detais_Data" , method = RequestMethod.POST)
//public @ResponseBody List<String> saveDraft_staff_list_remark_detais_Data(String clg_staff_list_teaching_fac_rmk,HttpSession session) throws ParseException {	
//	System.err.println("dao------------cont");
//	String username = session.getAttribute("username").toString();
////	String[] id_list = a.split(":");
////	String[] id_list2 = upto2.split(":");
////	String[] tempSt2 = tempSt.split(",");
//	List<String> list2 = new ArrayList<String>();
//	int id = 0;
////	System.out.println("id_list============= "+id_list);
//	String date;
//	
////	for (int i = 0; i < id_list.length; i++) {
////		id = Integer.parseInt(id_list[i]);
////		date = id_list2[i];
////		System.out.println("id_list============= "+id_list[i]);
////		System.out.println("remarks======================== "+tempSt2[i]);
////		list2.add(CSLRDao.reject_StatePracData(Integer.toString(id),status,username,tempSt2[i]));
//	list2.add(CSLRDao.saveDraftStaffListRemarkDetaisData(clg_staff_list_teaching_fac_rmk));
////	}
//	return list2;
//}


}
