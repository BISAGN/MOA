package com.AyushEdu.controller.LMS_Master_Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_LINK;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_LINK_COURSE_SET_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_LINK_COURSE_SET_MSTR_CHILD;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.controller.LMS_NCISM.CourseDuration_Ctrl;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Master.Link_Coures_setDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Link_Coures_With_Set_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CourseDuration_Ctrl cdc;

	@Autowired
	CommonController common;

	@Autowired
	Commondao comdao;

	@Autowired
	private ValidationController validation;

	@Autowired
	Link_Coures_setDao link_Coures_setDao;

	@GetMapping(value = "/Link_Coures_With_Set_Url")
	public ModelAndView Link_Coures_With_Set_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			if (request.getHeader("Referer") == null) {
		//		session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}

			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Link_Coures_With_Set_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			model.addAttribute("msg", msg);
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			model.put("getcoursenameList", comdao.getCourseNew());
			model.put("getModulnameList", common.getModulnameList(sessionFactory));
			model.put("getSetListALL", common.getSetListALL(sessionFactory));
			model.put("getSystemList", common.getSystemByNCISM(sessionFactory));
			model.put("gettermList", common.gettermList(sessionFactory));
			model.put("getDegreeList", common.getDegreeList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Link_Coures_With_Set_Tiles");

	}

	@RequestMapping(value = "/getFilter_data", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getFilter_data(int startPage, int pageLength, String Search,
			String system_id, String degree_id, String orderColunm, String orderType, HttpSession sessionUserId,
			Principal principal) {
		return link_Coures_setDao.DataTable_DataList(startPage, pageLength, Search, system_id, degree_id, orderColunm,
				orderType, sessionUserId);

	}

	@RequestMapping(value = "/getTotal_dataCount", method = RequestMethod.POST)
	public @ResponseBody long getTotal_dataCount(HttpSession sessionUserId, String Search, String system_id,
			String degree_id) {
		return link_Coures_setDao.DataTable_DataTotalCount(Search, sessionUserId, system_id, degree_id);
	}

	@RequestMapping(value = "/getcourselistfrolinking", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getdegreelistfrolinking() {
		List<EDU_LMS_ELECTIVE_COURSE_MASTER> list = common.getcoursenameList(sessionFactory);

		return list;
	}

	@RequestMapping(value = "/linkelectivecourseandsetandmodule_action", method = RequestMethod.POST)
	public ModelAndView linkelectivecourseandsetandmodule_action(
			@Validated @ModelAttribute("linkelectivecourseandsetandmodule_CMD") EDU_LMS_LINK_COURSE_SET_MSTR td,
			BindingResult result, MultipartHttpServletRequest mul, HttpServletRequest request, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra) throws IOException {

		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Link_Coures_With_Set_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");

		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String course_video = cdc.gd(request, mul, session, "course_video");
		String description = request.getParameter("description").trim();
		
		
		Long c= 0L;
		int p_id = 0;
				
		if (system_id == null || system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
		}
		if (degree_id == null || degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree.");
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
		}
		if (course_video.equals("") || course_video.equals("0")) {
			ra.addAttribute("msg", "Please Select Course Video.");
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
		}
		if (description.trim() == null || description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Description.");
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
		}
		if (validation.maxlengthcheck500(description) == false) {
			ra.addAttribute("msg", "Description " + validation.MaxlengthcheckMSG500);
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
		}

//		if (validation.isOnlyAlphabetDASH(description) == false) {
//			ra.addAttribute("msg", "Description " + validation.isOnlyAlphabetMSGDASH);
//			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
//		}

		try {

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			//shivali
			String sid = "";
			
			int ch = Integer.parseInt(request.getParameter("count_hidden_cbc"));
			for (int j = 1; j <= ch; j++) {
				 sid = request.getParameter("set_id" + j);
				 
				  c = (Long) sessionHQL.createQuery(
							"select count(lcsm_ch.id) from EDU_LMS_LINK_COURSE_SET_MSTR lcsm , EDU_LMS_LINK_COURSE_SET_MSTR_CHILD lcsm_ch \n"
							+"where lcsm_ch.p_id=lcsm.id and lcsm.system_id=:system_id and lcsm.degree_id=:degree_id and lcsm_ch.set_id=:set_id and lcsm_ch.id !=:id")
							.setParameter("system_id", td.getSystem_id())
							.setParameter("degree_id", td.getDegree_id())
							.setParameter("set_id",Integer.parseInt(sid))
						    .setParameter("id", id).uniqueResult();
				 
			}
			
			
			if (id == 0) {
			EDU_LMS_LINK_COURSE_SET_MSTR add = new EDU_LMS_LINK_COURSE_SET_MSTR();

			add.setSystem_id(Integer.parseInt(request.getParameter("system_id")));
			add.setDegree_id(Integer.parseInt(request.getParameter("degree_id")));
			add.setCourse_video(course_video);
			add.setDescription(request.getParameter("description"));
			add.setCreated_by(username);
			add.setCreated_date(date);
		
			if (c == 0) {
			 p_id = (int) sessionHQL.save(add);
			sessionHQL.flush();
			sessionHQL.clear();
			ra.addAttribute("msg", "Data Saved Successfully.");
		   } else {
			ra.addAttribute("msg", "Data already Exist.");
			return new ModelAndView("redirect:Link_Coures_With_Set_Url");
			
		   }
	     }
			tx.commit();
			
			String data_fh = request.getParameter("data_f");
			int count_hidden = Integer.parseInt(request.getParameter("count_hidden_cbc"));
			for (int j = 1; j <= count_hidden; j++) {

				String set_demo_video1 = cdc.gd(request, mul, session, "set_demo_video" + j);
				String course_id = request.getParameter("course_id" + j);
				String set_id = request.getParameter("set_id" + j);

				String in_course_id_hid_ch_arry[] = request.getParameter("in_course_id_hid_ch" + j).split(",");

				for (int k = 0; k < in_course_id_hid_ch_arry.length; k++) {
					EDU_LMS_LINK_COURSE_SET_MSTR_CHILD addchild = new EDU_LMS_LINK_COURSE_SET_MSTR_CHILD();
					Session sessionHQLchild = this.sessionFactory.openSession();
					Transaction txchild = sessionHQLchild.beginTransaction();
					String set_demo_video = cdc.gd(request, mul, session, "set_demo_video" + j);
					String course_id1 = request.getParameter("in_course_id1");

					if (set_id == null || set_id.equals("0")) {
						ra.addAttribute("msg", "Please Select Set");
						return new ModelAndView("redirect:Link_Coures_With_Set_Url");
					}

					if (course_id1 == null || course_id1.equals("0")) {
						ra.addAttribute("msg", "Please Select Course");
						return new ModelAndView("redirect:Link_Coures_With_Set_Url");
					}
					if (set_demo_video.equals("") || set_demo_video.equals("0")) {
						ra.addAttribute("msg", "Please Select Set Demo Video.");
						return new ModelAndView("redirect:Link_Coures_With_Set_Url");
					}

					addchild.setP_id(p_id);
					addchild.setSet_id(Integer.parseInt(set_id));
					addchild.setCourse_id(Integer.parseInt(in_course_id_hid_ch_arry[k]));
					addchild.setSet_demo_video(set_demo_video1);
					addchild.setCreated_by(username);
					addchild.setCreated_date(date);

					sessionHQLchild.save(addchild);
					sessionHQLchild.flush();
					sessionHQLchild.clear();
					txchild.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
				}
			}

		} catch (RuntimeException e) {
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Could not roll back transaction " + rbe);
			}
			throw e;
		}
		return new ModelAndView("redirect:Link_Coures_With_Set_Url");
	}

}
