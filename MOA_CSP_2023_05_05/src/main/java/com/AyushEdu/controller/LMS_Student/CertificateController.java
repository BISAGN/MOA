package com.AyushEdu.controller.LMS_Student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.PDF.AdmitCardDownloadPdf_old;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Student.CertificateDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CertificateController {
	
	
	@Autowired
	private CertificateDao sdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Certificate_Final_Url", method = RequestMethod.GET)
	public ModelAndView Certificate_Final_Url(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Certificate_Final_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		
		 Mmap.put("msg", msg);
	
		return new ModelAndView("Certificate_Tiles");
	}
	
	
		@RequestMapping(value = "/getExamBoardAdmitCardPDF", method = RequestMethod.POST)
		public ModelAndView getExamBoardAdmitCardPDF(@ModelAttribute("ayush_id1") String ayush_id,@ModelAttribute("id1") String course_name,@ModelAttribute("id2") String  set_name ,
				@ModelAttribute("id3") String  logo_img_path,@ModelAttribute("exam_id2") String  exam_id,@ModelAttribute("course_id2") String  course_id,@ModelAttribute("module_id2") String  module_id, ModelMap Mmap, HttpSession session,HttpServletRequest request, String typeReport, String begin_date1, String reportname1) {
				try {
					
					

					
//				System.err.println("course_name----------------"+course_name);
//				System.err.println("set_name----------------"+set_name);
//				System.err.println("course_id----------------"+course_id);
//				System.err.println("exam_id----------------"+exam_id);
//				System.err.println("ayush_id----------------"+ayush_id);
//				System.err.println("report_name1----------------"+begin_date1);
					
					
				String userId = session.getAttribute("userId").toString();
				ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
				ArrayList<String> list2 = new ArrayList<String>();
				
				String count =sdao.getpercentage(Integer.parseInt(userId)).get(0).get(0);
				String credit =sdao.getcredit(Integer.parseInt(userId), Integer.parseInt(course_id)).get(0).get(0);
				
				
					System.out.println("logo  ========= "+logo_img_path);
					if(reportname1.equals("1")) {
						
						if ( typeReport.equals("pdfL")) {
							//if (list.size() > 0) {

//										list2.add("1");
//										list2.add("basic of microbiolgy");
////										list2.add("7.2");
//										list2.add("15");
//										list2.add("20");
////										list2.add("75");
//										list1.add(list2);
								String Heading = "";
								String username = session.getAttribute("roleloginName").toString();
								//String ayush_id1 =  session.getAttribute("ayush_id").toString();
								
								//String course_name1 = session.getAttribute("course_name").toString();
						//		String course_name1 = request.getAttribute("course_name").toString();
							//	System.err.println("course_name1--->  "+course_name);
								//String course_name = request.getAttribute("course_name").toString();
							//	System.err.println("course_name--->  "+course_name);
							
								

									List<String> TH = new ArrayList<String>();
									TH.add("Course");// 0
									TH.add("Exam Marks");// 1
									TH.add("Total Marks");// 2
									
									return new ModelAndView(new AdmitCardDownloadPdf_old("L", TH, Heading,ayush_id, username,course_name, logo_img_path,count,credit));
//			return new ModelAndView(new AdmitCardDownloadPdf_old("L", TH, Heading,ayush_id, username,course_name, logo_img_path,count,credit),"userList",list);

							//}
						}
					}
					
					
					
					} catch (Exception e) {
					e.printStackTrace();
				}

				return new ModelAndView("Exam_result_Tiles");
			}
		
		
		//---getuserlistdata fetch::---
		 
		 
//		 @RequestMapping(value = "/getUserListstatus", method = RequestMethod.POST)
//			public @ResponseBody   ArrayList<ArrayList<String>> getUserListstatus(int module_id,String exam_name,HttpSession session) {
//			 
//			 String userid = session.getAttribute("userId_for_jnlp").toString();
//			 ArrayList<ArrayList<String>> list = sdao.getuserList(Integer.parseInt(userid),module_id, exam_name);
//			
//			 System.err.println("list"+list.get(0));
//			 
//				return list;
//			}
		 
		
}


