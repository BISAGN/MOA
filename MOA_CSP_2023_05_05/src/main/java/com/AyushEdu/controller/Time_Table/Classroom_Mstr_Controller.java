package com.AyushEdu.controller.Time_Table;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Core_Directory.Classroom_CD_DAO;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Time_Table.EDU_TT_CLASSROOM_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Time_Table.Classroom_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Classroom_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	Classroom_DAO Cdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@Autowired
	Classroom_CD_DAO  DM_dirdao;
	
	@RequestMapping(value = "admin/Classroom_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Classroom_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Classroom_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/ClassroomAction")
		public ModelAndView ClassroomAction(@Validated @ModelAttribute("ClassroomCMD") EDU_TT_CLASSROOM_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
			
			//SECURITY - POOJA
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			String classroom = request.getParameter("classroom");
			String status = request.getParameter("status");
			 Session sessiont = sessionFactory.openSession();
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			 Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			 int institute_id = institute_idList.get(0).getInstitute_id(); 
			 
			if (classroom == null || classroom.equals("")) {
				ra.addAttribute("msg", "Please Enter Classroom Name.");
				return new ModelAndView("redirect:Classroom_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(classroom) == false) {
				ra.addAttribute("msg","Classroom "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Classroom_Mstr_Url");
			}
			
			if (validation.isOnlyAlphabetNumber(classroom) == false) {
				ra.addAttribute("msg","Classroom "+ validation.isOnlyAlphabetNumberMSG);
				return new ModelAndView("redirect:Classroom_Mstr_Url");
			}
			
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:Classroom_Mstr_Url");
			}

			int id =Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(*) from  EDU_TT_CLASSROOM_MSTR where upper(classroom)=:classroom and status=:status and id !=:id and institute_id=:institute_id")
						.setParameter("classroom", td.getClassroom().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("institute_id", institute_id)
						.setParameter("id", id).uniqueResult();
				int idd =0;
				if (id == 0) {
					td.setClassroom(classroom);
					td.setCreated_by(userid);
//					td.setCreated_role(role);
					td.setCreated_date(date);
					td.setInstitute_id(institute_id);
					if (c == 0) {
						idd = (int)sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setClassroom(classroom);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Cdao.updateClassroom (td);
						DM_dirdao.Update_classroom_Mstr_Data( td.getId(), td.getClassroom(),td.getInstitute_id(), td.getStatus(),  userid,  new Date());
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
				//For Core Directory
				DM_dirdao.Insert_classroom_Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:Classroom_Mstr_Url");
		}
		
		@PostMapping("/getFilterClassroom_data")

		public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String classroom, String status,HttpSession session) {
					
			Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
			return Cdao.DataTableClassroomDataList(startPage, pageLength, Search, orderColunm, orderType, classroom,status,institute_id);

		}

		@PostMapping("/getTotalClassroom_dataCount")
		public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String classroom,HttpSession session) {
			Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
			 List<UserLogin> institute_idList = q1.list();
			 sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
			return Cdao.DataTableClassroomDataTotalCount(Search, classroom, institute_id);
			
		}
		
		//-----edit

				@RequestMapping(value = "/Edit_Classroom_Mstr_Url", method = RequestMethod.POST)
				public ModelAndView Edit_Classroom_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "classroom", required = false) String classroom,
						@RequestParam(value = "status", required = false) String status,
						HttpServletRequest request, HttpSession sessionEdit) {
					
					//SECURITY - POOJA
					if(request.getHeader("Referer") == null ) { 
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					EDU_TT_CLASSROOM_MSTR Classroom_Details = Cdao.getClassroomByid(Integer.parseInt(updateid));
					Mmap.addAttribute("msg", msg);
					Mmap.put("classroom", classroom);
					Mmap.put("status", status);
					Mmap.put("Classroom_Details", Classroom_Details);
					Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
					Mmap.put("Classroominfo", Cdao.getClassroomByid(Integer.parseInt(updateid)));
					
					tx.commit();
					sessionHQL.close();

					return new ModelAndView("Classroom_Tiles");
				}
				
				@RequestMapping(value = "/Classroom_Mstr_Delete_Url", method = RequestMethod.POST)
				public ModelAndView Classroom_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
					
					//SECURITY - POOJA
					if(request.getHeader("Referer") == null ) { 
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					
					String roleid1 = session1.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("Classroom_Mstr_Url", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}

					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
						
						
			  
						int app = session.createQuery(
								"delete from EDU_TT_CLASSROOM_MSTR where id=:id")
								.setParameter("id", id).executeUpdate();
						
						tx.commit();
						//For Core Directory
						DM_dirdao.Delete_classroom_Mstr_Data(id); 
						session.close();
						if (app > 0) {
							System.err.println("check delete"+(app > 0));
							liststr.add("Data Deleted Successfully.");
						} else {
							liststr.add("Data not Deleted.");
						}
						ra.addAttribute("msg", liststr.get(0));
					} catch (Exception e) {
						e.printStackTrace();
						liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
						ra.addAttribute("msg", liststr.get(0));
						
					}
					return new ModelAndView("redirect:Classroom_Mstr_Url");
				}
	//-------------------------------Unused Method----------------------------//			
				public @ResponseBody List<EDU_TT_CLASSROOM_MSTR> getClassroomList(SessionFactory sessionFactory) {
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL.createQuery("from EDU_TT_CLASSROOM_MSTR where status='1' order by id ");

					@SuppressWarnings("unchecked")
					List<EDU_TT_CLASSROOM_MSTR> clist = (List<EDU_TT_CLASSROOM_MSTR>) q.list();
					tx.commit();
					sessionHQL.close();
					return clist;
				}
}
