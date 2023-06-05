package com.AyushEdu.controller.LMS_Institute;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYS_DEG_MAP_INST;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Inst_Sys_Deg_Map_ctrl {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	@Autowired
	private Commondao cd;
	
	@Autowired
	Sys_Deg_Map_Inst_DAO  dmdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@GetMapping(value = "/System_Degree_Mapping_InstUrl")
	public ModelAndView System_Degree_Mapping_InstUrl(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			RedirectAttributes ra) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		try {
			
			//SECURITY RAHUL

//			if (request.getHeader("Referer") == null) {
//			//	session.invalidate();
//				model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_InstUrl", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}

			model.addAttribute("msg", msg);
			model.put("institute_id", institute_id);
			model.put("d_name", common.getDegreeList(sessionFactory));
			model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			model.put("system", dmdao.Getsytemid_fetch(userid));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("400");
		}
		return new ModelAndView("system_degree_mapping_inst_Tiles");
	}

	@PostMapping(value = "/System_Degree_Mapping_Inst_action")
	public ModelAndView Degree_Master_action(
			@Validated @ModelAttribute("System_Degree_Mapping_Inst_cmd") EDU_LMS_SYS_DEG_MAP_INST td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

//		if (request.getHeader("Referer") == null) {
//		//	session.invalidate();
//			model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		}
//		String roleid1 = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_InstUrl", roleid1);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}

		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String system_id = request.getParameter("system_hid");
		String degree_id = request.getParameter("degree_id");
		String status = request.getParameter("status");

		System.out.println("----systemid "+system_id);
		
		if (degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Status");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status :");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_SYS_DEG_MAP_INST where upper(status)=:status and system_id=:system_id and degree_id=:degree_id and institute_id=:institute_id  and id !=:id")
					.setParameter("status",status).setParameter("system_id", Integer.parseInt(system_id))
					.setParameter("degree_id", Integer.parseInt(degree_id)).setParameter("institute_id",Integer.parseInt(institute_id))
					.setParameter("id", id).uniqueResult();

			if (id == 0) {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setInstitute_id(Integer.parseInt(institute_id));
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}
//				else {
//					td.setModified_by(username);
//					td.setModified_date(date);
//					if (c == 0) {
//						
//						String msg = ddao.UpdateName(td);
//						 ra.addAttribute("msg", msg);
//					} else {
//						 ra.addAttribute("msg", "Data already Exist.");
//					}
//				}
			tx.commit();
		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
	}



	@PostMapping("/getFilter_system_degree_mapping_instdata")

	public @ResponseBody List<Map<String, Object>> getFilter_system_degree_mapping_instdata(int startPage,
			int pageLength,

			String Search, String orderColunm, String orderType, String degree_id, String status,
			HttpSession sessionUserId) {
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return dmdao.DataTablesystem_degree_mapping_instDataList(startPage, pageLength, Search, orderColunm, orderType,
				 institute_id,degree_id, status);
	}

	@PostMapping("/getTotal_system_degree_mapping_instdataCount")
	public @ResponseBody long getTotal_system_degree_mapping_instdataCount(HttpSession sessionUserId, String Search,
			String degree_id, String status) {
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return dmdao.DataTablesystem_degree_mapping_instDataTotalCount(Search,institute_id,degree_id, status);
	}

	@PostMapping(value = "/delete_System_Degree_Mapping_Inst_Url")
	public ModelAndView delete_System_Degree_Mapping_Inst_Url(@ModelAttribute("id2") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		if (request.getHeader("Referer") == null) {
	//		session1.invalidate();
			model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_InstUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EDU_LMS_SYS_DEG_MAP_INST set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", "2").executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		} finally {

		}
		return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
	}

	@RequestMapping(value = "/Edit_System_Degree_Mapping_Inst_Url")
	public ModelAndView Edit_System_Degree_Mapping_Inst_Url(@ModelAttribute("id1") String updateid, ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpSession sessionEdit,
			HttpServletRequest request, RedirectAttributes ra) {

		try {
			String userid = sessionEdit.getAttribute("userId_for_jnlp").toString();
			Mmap.put("system", dmdao.Getsytemid_fetch(userid));
			if (request.getHeader("Referer") == null) {
			//	sessionEdit.invalidate();
				Mmap.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_InstUrl", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			EDU_LMS_SYS_DEG_MAP_INST Edit_system_degree_mapping_inst_details = dmdao
					.get_system_degree_mapping_instByid(Integer.parseInt(updateid));

			Mmap.put("d_name", common.getDegreeList(sessionFactory));

			Mmap.addAttribute("msg", msg);
			Mmap.put("Edit_system_degree_mapping_inst_details", Edit_system_degree_mapping_inst_details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("edit_system_degree_mapping_Inst_Tiles");
	}

	@RequestMapping(value = "/Edit_System_Degree_Mapping_Inst_action", method = RequestMethod.POST)
	public ModelAndView Edit_System_Degree_Mapping_Inst_action(
			@ModelAttribute("Edit_System_Degree_Mapping_Inst_cmd") EDU_LMS_SYS_DEG_MAP_INST rs,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("System_Degree_Mapping_InstUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		String username = session.getAttribute("username").toString();

		String hid = request.getParameter("id");
		int id = 0;
		if (hid != "" || hid != null) {
			id = Integer.parseInt(hid);
		}

		String degree_id1 = request.getParameter("degree_id");
		int degree_id = 0;
		if (degree_id1 != "" || degree_id1 != null) {
			degree_id = Integer.parseInt(degree_id1);
		}

		String system_id1 = request.getParameter("system_hid");

		int system_id = 0;
		if (system_id1 != "" || system_id1 != null) {
			system_id = Integer.parseInt(system_id1);
		}

		String status = request.getParameter("status");
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		if (degree_id == 0) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Status");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}

		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status :");
			return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
		}

		try {
			Query q0 = session1.createQuery(
					"select count(id) from EDU_LMS_SYS_DEG_MAP_INST where system_id=:system_id and degree_id=:degree_id  and status=:status and id !=:id");
			q0.setParameter("degree_id", degree_id);
			q0.setParameter("system_id", system_id);
			q0.setParameter("status", status);
			q0.setParameter("id", id);

			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update EDU_LMS_SYS_DEG_MAP_INST set system_id=:system_id,degree_id=:degree_id,status=:status,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setParameter("degree_id", degree_id)
						.setParameter("system_id", system_id).setParameter("status", status)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.setParameter("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg.equals("1")) {
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					ra.addAttribute("msg", "Data Not Updated.");
				}
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:System_Degree_Mapping_InstUrl");
	}

	// DROPDOWN=====================================
	@RequestMapping(value = "/getdegrebysysidlist", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getdegrebysysidlist(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) String selval, HttpServletRequest request) {
		List<EDU_LMS_SYS_DEG_MAP_MASTER> list = null;
		try {
			list = dmdao.getdegrebysysidlistdata(selval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getGenerate_sytem_id", method = RequestMethod.POST)
	public @ResponseBody List<ArrayList<String>> getGenerate_sytem_id(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		List<ArrayList<String>> list = dmdao.Getsytemid_fetch(userid);
		return list;
	}
}
