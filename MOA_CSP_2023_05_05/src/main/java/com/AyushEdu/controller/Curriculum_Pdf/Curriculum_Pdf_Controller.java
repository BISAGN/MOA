package com.AyushEdu.controller.Curriculum_Pdf;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_EXAM_TYPE_MSTR;
//import com.AyushEdu.controller.Exp_Excel.Examtype_PDF_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Pdf.T_1_PDF_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Curriculum_Pdf_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

    @Autowired
    T_1_PDF_DAO Edao;

	@Autowired
	CommonController common;

		@RequestMapping(value = "admin/Genrate_CC_Pdf_Url", method = RequestMethod.GET)
		public ModelAndView Genrate_CC_Pdf_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			try {

				Mmap.put("msg", msg);
				Mmap.put("getCourseList", common.getMainCourseList(sessionFactory));

			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ModelAndView("New_Tiles");
		}
			
				//download pdf

			@RequestMapping(value = "/Gen_CC_pdf_action", method = RequestMethod.POST)
			public ModelAndView Gen_CC_pdf_action(@RequestParam(value = "course_id1", required = false) String course_id,
					Authentication authentication, HttpSession session, HttpServletRequest request) {

						String username = session.getAttribute("username").toString();

						List<ArrayList<String>> nonlecact1 = Edao.non_lec_activities(course_id);
						
						ArrayList<ArrayList<String>> t1copolink2 = Edao.table1_co_po_link(course_id);
						System.err.println("new_list2-------------"+t1copolink2);
						
						int total = nonlecact1.size();
						List<String> TH = new ArrayList<String>();
						
						int total2 = t1copolink2.size();
						List<String> TH2 = new ArrayList<String>();
						
						TH.add("SN");
						TH.add("Name of Practical");
						TH.add("Term");
						
						TH2.add("SR1\n"
								+ "CO No");
						TH2.add("A1\n"
								+ "Course learning Outcome (CO) AyUG RS\n"
								+ "At the end of the course AyUG RS, the student should be able\n"
								+ "to");
						TH2.add("B1\n"
								+ "Course learning\n"
								+ "Outcome matched with\n"
								+ "program learning\n"
								+ "outcomes..");

						String Heading = "\nIn Inspection";
						return new ModelAndView(new T_1_Pdf_Controller("L", TH,TH2, Heading, username, nonlecact1,t1copolink2, total),
								"userList", nonlecact1);
					
		}
}
		
