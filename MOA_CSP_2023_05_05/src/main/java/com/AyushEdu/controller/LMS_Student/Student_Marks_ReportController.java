package com.AyushEdu.controller.LMS_Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;
import com.AyushEdu.dao.LMS_Student.Students_Marks_ReportDao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Student_Marks_ReportController {

	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Students_Marks_ReportDao SMRdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Stud_Elect_Courses_Dao sdc;
	
	@RequestMapping(value = "/Students_Marks_ReportUrl", method = RequestMethod.GET)
	public ModelAndView Students_Marks_ReportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
		
			//SECURITY RAHUL
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Students_Marks_ReportUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		Mmap.put("msg", msg);
		Mmap.put("getStu_Marks_Replist", SMRdao.getStu_Marks_Replist(userid));
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("Students_Marks_Report_Tiles");
	}
	
	@PostMapping("/getFilterSearch_Stu_Marks_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_Stu_Marks_data(int startPage, int pageLength,HttpSession session,

			String Search, String orderColunm, String orderType, String courses, String set) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return SMRdao.DataTableSearch_Stu_MarksDataList(startPage, pageLength, Search, orderColunm, orderType, courses, set,userid);

	}
	@PostMapping("/getTotalSearch_Stu_Marks_dataCount")
	public @ResponseBody long getTotalSearch_Stu_Marks_dataCount(HttpSession sessionUserId, String Search,String courses, String set,HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		return SMRdao.DataTableSearch_Stu_MarksDataTotalCount(Search, courses, set,userid);
	}

	
	 @RequestMapping(value = "/getSetnamedataFromcourse_name", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getSetnamedataFromcourse_name(String course_name, HttpSession session) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 ArrayList<ArrayList<String>> list = SMRdao.getStu_Marks_Reportlist(userid,course_name);
			return list;
		}
	 
//	 @RequestMapping(value = "/Students_MarksheetUrl", method = RequestMethod.POST)
//		public @ResponseBody   ArrayList<ArrayList<String>> Students_MarksheetUrl(String course_name, HttpSession session,String setname) {
//		 String userid = session.getAttribute("userId_for_jnlp").toString();
//		 ArrayList<ArrayList<String>> list = SMRdao.getPopup_Datalist(userid,course_name,setname);
////		 System.err.println("-----2408-------list"+list);
//			return list;
//		}
	 
//	 change on 23/11/2022
	 @RequestMapping(value = "/Students_MarksheetUrl", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> Students_MarksheetUrl(String course_name, HttpSession session,String setname) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
			String degree_id = sdc.getdegree_list(userid).get(0).get(0);
		 ArrayList<ArrayList<String>> list = SMRdao.getPopup_Datalist(userid,course_name,setname,degree_id);
			return list;
		}
	
	
	
}
