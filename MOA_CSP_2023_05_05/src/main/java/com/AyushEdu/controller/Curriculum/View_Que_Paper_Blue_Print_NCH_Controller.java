package com.AyushEdu.controller.Curriculum;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.View_Que_Paper_Blue_PrintDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class View_Que_Paper_Blue_Print_NCH_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private View_Que_Paper_Blue_PrintDao BPDAO;
	
	@RequestMapping(value = "admin/View_Que_Paper_Blue_Print_NCH_url", method = RequestMethod.GET)
	public ModelAndView View_Que_Paper_Blue_Print_NCH_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
//			if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("View_Que_Paper_Blue_Print_NCH_url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			String role = session.getAttribute("role").toString();

			String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, roleStaff_lvl));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("View_Que_Paper_Blue_Print_NCH_Tiles");
	}
	@RequestMapping(value = "/getView_QuePaperBluePrintNCHviewdata", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getView_QuePaperBluePrintviewdata(String course_id) {
		ArrayList<ArrayList<String>> list = BPDAO.getView_QuePaperBluePrint_viewdata(course_id);
		return list;
	}
	@RequestMapping(value = "/getPaper_Format_NCH_data", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getPaper_Format_NCH_data(String course_id, String mk_dk,
			String qt, String noofpaper) {
		ArrayList<ArrayList<String>> list = BPDAO.getpaperformat_NCH_data(course_id, mk_dk, qt, noofpaper);
		return list;
	}
}
