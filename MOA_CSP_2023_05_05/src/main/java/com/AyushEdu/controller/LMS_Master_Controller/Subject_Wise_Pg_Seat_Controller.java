package com.AyushEdu.controller.LMS_Master_Controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.query.Query;
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
import com.AyushEdu.Core_Directory.District_CD_DAO;
import com.AyushEdu.Core_Directory.Document_Attachments_CD_DAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum_Mstr.CC_System_Professional_Degree_Course_Dao;
import com.AyushEdu.dao.LMS_Master.DistrictDao;
import com.AyushEdu.dao.LMS_Master.Edu_Lms_Subject_Wise_Pg_Seats_Dao;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_WISE_PG_SEATS;

import freemarker.core.ParseException;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Subject_Wise_Pg_Seat_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	CommonController common;

//	@Autowired
//	private DistrictDao Dis_Dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Edu_Lms_Subject_Wise_Pg_Seats_Dao sdao;

	// ------------------------------- For page Open -------------------------------------

	 @RequestMapping(value = "admin/Subjects_Wise_Pg_Seat_MasterUrl", method = RequestMethod.GET)
	 public ModelAndView Subjects_Wise_Pg_Seat_MasterUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 try {
//				if(request.getHeader("Referer") == null ) { 
//				//	 session.invalidate();
//					 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//					 return new ModelAndView("redirect:/landingpage");
//				 }
//				
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("District", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
					
		 Mmap.put("msg", msg);
		 Mmap.put("getInstituteList", common.getInstituteList( sessionFactory));
		 Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
//		 Mmap.put("list", list);
		 

		 } catch (Exception e) {
				e.printStackTrace();
			}
		 return new ModelAndView("Subjects_Wise_Pg_SeatTiles");
	 }
	 
		// ------------------------------- save -------------------------------------

	 @PostMapping(value = "/SubjectwisepgseatAction")
		public ModelAndView SubjectwisepgseatAction(
				@Validated @ModelAttribute("SubjectwisepgseatCMD") EDU_LMS_SUBJECT_WISE_PG_SEATS td,
				BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String institute_id = request.getParameter("institute_id");
			String pg_subject = request.getParameter("pg_subject");
			String degree = request.getParameter("degree");
			String seat = request.getParameter("seat");
			String status = request.getParameter("status");

			if (institute_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Institute Name");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (degree.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (pg_subject.equals("0")) {
				ra.addAttribute("msg", "Please Select PG Subject");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			
			if (seat == null || seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Seat.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
//			if (validation.maxlengthcheck1D(seat) == false) {
//				ra.addAttribute("msg", "seat " + validation.MaxlengthcheckMSG1D);
//				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
//			}
			if (validation.isOnlyNumer(seat) == true) {
				ra.addAttribute("msg", "seat " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}

			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {

				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LMS_SUBJECT_WISE_PG_SEATS where institute_id=:institute_id and degree=:degree and pg_subject=:pg_subject \n"
								+ "  and status=1\n"
								+ " and id!=:id")
						.setParameter("institute_id", Integer.parseInt(institute_id))
						.setParameter("pg_subject", Integer.parseInt(pg_subject))
						.setParameter("degree", Integer.parseInt(degree))
						.setParameter("id", id).uniqueResult();

				String eid = request.getParameter("eid");

				if (!eid.equals("0")) {
					id = Integer.parseInt(eid);
				}

				if (id == 0) {

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
				} else {
					td.setInstitute_id(Integer.parseInt(institute_id));
					td.setPg_subject(Integer.parseInt(pg_subject));
					td.setSeat(Integer.parseInt(seat));
					td.setStatus(Integer.parseInt(status));

					td.setModify_by(username);
					td.setModify_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = sdao.updateSubjectWisepgSeats(td);
						ra.addAttribute("msg", msg);
					} else {
						ra.addAttribute("msg", "Data already Exist.");
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

			return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
		}
	 
	 
	 
//		// -------------------------SEARCH District  -------------------------------------

	 @PostMapping("/getFilteswps_data")
		public @ResponseBody List<Map<String, Object>> getFilteswps_data(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, String institute_id, String pg_subject,String degree,
				String seat, String status, HttpSession session) {
			return sdao.DataTablesubjectdegreepglinkDataList(startPage, pageLength, Search, orderColunm, orderType,
					institute_id,degree, pg_subject, seat, status, session);

		}

		@PostMapping("/getFilteswps_dataCount")
		public @ResponseBody long getFilteswps_dataCount(HttpSession sessionUserId, String Search, String institute_id,
				String degree, String pg_subject, String seat, String status, HttpSession session) {
			return sdao.DataTablesubjectdegreepglinkDataListTotalCount(Search, institute_id,degree, pg_subject, seat, status, session);

		}
	 
	 	@PostMapping("/getPGDegreeofInst")
		public @ResponseBody List<Map<String, Object>> getPGSubjectofInst(String inst_id) {
			return sdao.PGDegreeofInst(inst_id);

		}
	 	@PostMapping("/getPGSubjectsofDegree")
		public @ResponseBody List<Map<String, Object>> getPGSubjectsofDegree(String degree) {
			return sdao.PGSubjectsofDegree(degree);

		}
	 
	 
	 
	 
	//-----edit

		@RequestMapping(value = "/EditSubjects_Wise_Pg_Seat_MasterUrl", method = RequestMethod.POST)
		public ModelAndView EditSubjects_Wise_Pg_Seat_MasterUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				Principal principal, @RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "institute_id", required = false) String institute_id,
				@RequestParam(value = "pg_subject", required = false) String pg_subject,
				@RequestParam(value = "seat", required = false) String seat,
				@RequestParam(value = "degree", required = false) String degree,
				@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
				HttpSession sessionEdit) {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			

			String role = sessionEdit.getAttribute("role").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_SUBJECT_WISE_PG_SEATS system_Details = sdao
					.getsubjectwisepgseats(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);
			 Mmap.put("getInstituteList", common.getInstituteList( sessionFactory));
			Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
			tx.commit();
			sessionHQL.close();

			return new ModelAndView("Subjects_Wise_Pg_SeatTiles");
		}

		// edit action
		@RequestMapping(value = "/edit_swps_Action", method = RequestMethod.POST)
		public ModelAndView edit_swps_Action(
				@ModelAttribute("edit_swpsCMD") EDU_LMS_SUBJECT_WISE_PG_SEATS rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String username = session.getAttribute("username").toString();

			String institute_id = request.getParameter("institute_id");
			String pg_subject = request.getParameter("pg_subject");
			String seat = request.getParameter("seat");
			String degree = request.getParameter("degree");
			String status = request.getParameter("status");

			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();

			if (institute_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Institute Name");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (degree.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (pg_subject.equals("0")) {
				ra.addAttribute("msg", "Please Select PG Subject");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			
			if (seat == null || seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Seat.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (validation.maxlengthcheck1D(seat) == false) {
				ra.addAttribute("msg", "seat " + validation.MaxlengthcheckMSG1D);
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (validation.isOnlyNumer(seat) == true) {
				ra.addAttribute("msg", "seat " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
			}

			try {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_LMS_SUBJECT_WISE_PG_SEATS where institute_id=:institute_id and pg_subject=:pg_subject and seat=:seat and degree=:degree and status=:status and id !=:id");
				q0.setParameter("institute_id", institute_id);
				q0.setParameter("pg_subject", pg_subject);

				q0.setParameter("seat", seat);

				q0.setParameter("status", status);

//							q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update EDU_LMS_SUBJECT_WISE_PG_SEATS set institute_id=:institute_id,pg_subject=:pg_subject,degree=:degree,seat=:seat,modify_by=:modify_by,modify_dt=:modify_dt"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("institute_id", institute_id)
							.setParameter("status", status).setParameter("pg_subject", pg_subject)
							.setParameter("seat", seat).setParameter("degree", degree).setParameter("modify_by", username)
							.setParameter("modify_dt", new Date());
//										.setParameter("id", id);
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

			return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
		}

		// -------------------------SEARCH Battalion
		// -------------------------------------//


		@RequestMapping(value = "/delete_swps_Url", method = RequestMethod.POST)
		public ModelAndView delete_swps_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("System_Professional_Degree_Course_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {

				int app = session.createQuery(
						"update EDU_LMS_SUBJECT_WISE_PG_SEATS set modify_by=:modify_by,modify_date=:modify_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modify_by", username)
						.setParameter("modify_date", new Date()).setParameter("status", 2).executeUpdate();

				tx.commit();
				session.close();
				if (app > 0) {
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
			return new ModelAndView("redirect:Subjects_Wise_Pg_Seat_MasterUrl");
		}

}
