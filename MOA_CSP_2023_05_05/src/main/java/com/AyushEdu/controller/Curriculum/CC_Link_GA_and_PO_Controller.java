package com.AyushEdu.controller.Curriculum;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_LINK_GA_AND_PO;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_LINK_TOPIC_TO_SUBTOPIC_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Link_ga_po_dao;
import com.AyushEdu.dao.Curriculum_Mstr.Link_Topic_to_SubTopic_MstrDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Link_GA_and_PO_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Link_ga_po_dao ldao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/link_ga_and_po_url", method = RequestMethod.GET)
	public ModelAndView link_ga_and_po_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_ga_and_po_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		try {
		String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Link_Graduate_Attribute_And_Program_Outcome_tiles");
	}
	
	@PostMapping(value = "/link_ga_and_po_action")
	public ModelAndView link_ga_and_po_action(@Validated @ModelAttribute("link_ga_and_po_CMD") EDU_CC_TB_LINK_GA_AND_PO td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_ga_and_po_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String msg="";
		Map<String,String> mObj=new HashMap<>();
		
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String graduateattribute_id = request.getParameter("graduateattribute_id");
		String status = request.getParameter("status");
	
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String userId = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (graduateattribute_id == null || graduateattribute_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Graduate Attribute");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (request.getParameter("in_program_hid_ch") == null || request.getParameter("in_program_hid_ch").trim().equals("")) {
			ra.addAttribute("msg", "Please Select Program Outcome");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:link_ga_and_po_url");
		}	
		try {

			String in_program_hid_ch[] = request.getParameter("in_program_hid_ch").split(",");
			String eids[] = request.getParameter("Edit_ids").split(":");
			
			for (int k = 0; k < in_program_hid_ch.length; k++) {
				
				EDU_CC_TB_LINK_GA_AND_PO add = new EDU_CC_TB_LINK_GA_AND_PO();
				
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_CC_TB_LINK_GA_AND_PO where system_id=:system_id and degree_id=:degree_id and graduateattribute_id=:graduateattribute_id and programoutcome_id=:programoutcome_id and status=:status and id !=:id")
						.setParameter("system_id", Integer.parseInt(system_id))
						.setParameter("degree_id", Integer.parseInt(degree_id))
						.setParameter("graduateattribute_id", Integer.parseInt(graduateattribute_id))
						.setParameter("programoutcome_id", Integer.parseInt(in_program_hid_ch[k]))
						.setParameter("status", 1)
						.setParameter("id", id).uniqueResult();
				if (id == 0) {
					if (c == 0) {
						add.setSystem_id(Integer.parseInt(system_id));
						add.setDegree_id(Integer.parseInt(degree_id));
						add.setGraduateattribute_id(Integer.parseInt(graduateattribute_id));
						add.setCreated_by(username);
						add.setCreated_date(date);
						add.setProgramoutcome_id(Integer.parseInt(in_program_hid_ch[k]));
						add.setStatus(1);
					
						sessionHQL.save(add);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					}else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
					else {
					add.setSystem_id(Integer.parseInt(system_id));
					add.setDegree_id(Integer.parseInt(degree_id));
					add.setGraduateattribute_id(Integer.parseInt(graduateattribute_id));
					add.setProgramoutcome_id(Integer.parseInt(in_program_hid_ch[k]));
					add.setModified_by(username);
					add.setModified_date(date);
					if (c == 0) {
						
						if(in_program_hid_ch.length >= eids.length) {
							for(int l=0;l<eids.length;l++) {
								//update
								msg = ldao.updatega_po(in_program_hid_ch[l],eids[l],"","");
							}
							for(int m=eids.length;m<in_program_hid_ch.length;m++){
								
								//save
								add.setSystem_id(Integer.parseInt(system_id));
								add.setDegree_id(Integer.parseInt(degree_id));
								add.setGraduateattribute_id(Integer.parseInt(graduateattribute_id));
								add.setProgramoutcome_id(Integer.parseInt(in_program_hid_ch[k]));
								add.setCreated_by(username);
								add.setCreated_date(date);
								add.setStatus(1);
							
								sessionHQL.save(add);
								sessionHQL.flush();
								sessionHQL.clear();
							}
						}
						if(in_program_hid_ch.length < eids.length) {
						}
						ra.addAttribute("msg", msg);
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}
		}	
			tx.commit();
		} catch (RuntimeException e) {
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:link_ga_and_po_url");
	}
	
	@RequestMapping(value = "/update_Ga_Po_Action", method = RequestMethod.POST)
	public @ResponseBody String update_Ga_Po_Action(String system_id,String degree_id,String graduateattribute_id,
			String sd_old,String sd_new,HttpSession session1,Principal principal,RedirectAttributes ra,HttpServletRequest request )  {
		
//		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("link_ga_and_po_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		
		String in_program_hid_ch[] = sd_new.split(",");
		String eids[] = sd_old.split(":");
		
		if (system_id == null || system_id.trim().equals("0")) {
			msg ="Please Select System";
			return msg;
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			msg ="Please Select Degree";
			return msg;
		}
		
		if (graduateattribute_id == null || graduateattribute_id.trim().equals("0")) {
			msg ="Please Select Graduate Attribute";
			return msg;
		}
//		if (request.getParameter("in_program_hid_ch") == null || request.getParameter("in_program_hid_ch").trim().equals("")) {
//			msg ="Please Select Program Outcome";
//			return msg;
//		}
		
		if(msg.equals("")) {
			try {
				if(in_program_hid_ch.length >= eids.length) {
					
					for(int l=0;l<eids.length;l++) {
						//update
						msg = ldao.updatega_po(in_program_hid_ch[l],eids[l],graduateattribute_id ,degree_id);
					}
					for(int m=eids.length;m<in_program_hid_ch.length;m++){
						//save
						EDU_CC_TB_LINK_GA_AND_PO add = new EDU_CC_TB_LINK_GA_AND_PO();
						add.setSystem_id(Integer.parseInt(system_id));
						add.setDegree_id(Integer.parseInt(degree_id));
						add.setGraduateattribute_id(Integer.parseInt(graduateattribute_id));
						add.setProgramoutcome_id(Integer.parseInt(in_program_hid_ch[m]));
						add.setStatus(1);
						add.setCreated_by(username);
						add.setCreated_date(date);
					
						sessionHQL.save(add);
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}
				if(in_program_hid_ch.length < eids.length) {
						//delete
					for(int k=in_program_hid_ch.length;k<eids.length;k++) {
						int app = sessionHQL.createQuery(
								"update EDU_CC_TB_LINK_GA_AND_PO set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
								.setParameter("id", Integer.parseInt(eids[k])).setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("status", 2).executeUpdate();
					}
				}
				msg = "Data Updated Successfully";
				tx.commit();
				sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
//	@RequestMapping(value = "/getGAby_Degree", method = RequestMethod.POST)
//	public @ResponseBody List<CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM> getGAby_Degree(String degree_id)  {
//		List<CC_TB_LINK_GRADUATE_ATTRIBUTE_AND_SYSTEM> list =  common.getGA_by_Degree(sessionFactory,degree_id);
//		return list;
//	}
	
//	@RequestMapping(value = "/getPOlistby_Degree", method = RequestMethod.POST)
//	public @ResponseBody List<CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE> getPOlistby_Degree(String degree_id)  {
//		List<CC_TB_LINK_PROGRAM_OUTCOME_AND_SYSTEM_AND_DEGREE> list =  common.getPO_listby_Degree(degree_id);
//		return list;
//	}

	@PostMapping("/getFilterGa_Po_Data_data")
	public @ResponseBody List<Map<String, Object>> getFilterGa_Po_Data_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,String system_id, String degree_id,String graduateattribute_id,String programoutcome_id,HttpSession session, String status) {
		String role = session.getAttribute("role").toString();
		return ldao.DataTableGa_PoDataList(startPage, pageLength, Search, orderColunm, orderType,system_id, degree_id,graduateattribute_id,programoutcome_id,role,status);
	}

	@PostMapping("/getTotalGa_Po_Data_dataCount")
	public @ResponseBody long getTotalGa_Po_Data_dataCount(HttpSession sessionUserId, String Search,String system_id,String degree_id,String graduateattribute_id,String programoutcome_id,HttpSession session,String status) {
		String role = session.getAttribute("role").toString();
		return ldao.DataTableGa_Po_DataTotalCount(Search, system_id,degree_id, graduateattribute_id,programoutcome_id,role ,status);
	}
	
	@RequestMapping(value = "/Ga_Po_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Ga_Po_Delete_Url(@ModelAttribute("id1") String id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			 session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("link_ga_and_po_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			
			String dids[] = id.split(":"); 
			for(int k=0;k<dids.length;k++) {
				int app = session.createQuery(
						"update EDU_CC_TB_LINK_GA_AND_PO set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(dids[k])).setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("status", 2).executeUpdate();

				if (app > 0) {
					System.err.println("check delete"+(app > 0));
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
			}
			tx.commit();
			session.close();
			ra.addAttribute("msg", liststr.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:link_ga_and_po_url");
	}
}