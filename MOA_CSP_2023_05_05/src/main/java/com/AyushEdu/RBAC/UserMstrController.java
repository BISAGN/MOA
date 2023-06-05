package com.AyushEdu.RBAC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAOImpl;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.validation.PasswordValidator;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class UserMstrController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private SessionFactory sessionFactory;
	
	ValidationController validation = new ValidationController();
	
	@Autowired
	RoleMstrController roleMstrController;
	@Autowired
	CommonController common;
	@Autowired
	Commondao comdao;
	@Autowired
	UserLoginDAOImpl user;
	
/// open User Master page
	@RequestMapping(value = "/user_mstUrl", method = RequestMethod.GET)
	public ModelAndView user_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		String staff_lvl = comdao.getStaffLvlfromRoleid(roleid);
		String role = session.getAttribute("role").toString();
		// int roleid = roleList.getRoleId();

		if (staff_lvl == null) {
			staff_lvl = "1";
		}

		if (request.getHeader("Referer") == null) {
			// session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		// System.err.println("roleType------>
		// "+session.getAttribute("role_url").toString());

		Mmap.put("msg", msg);
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
//			Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
		Mmap.put("getRoleNameList", comdao.getRolebyStaffLvl(staff_lvl, role));
		Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
		Mmap.put("getCoursePubName", common.getCoursePubName(sessionFactory));
		Mmap.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
		Mmap.put("getUniversityList", comdao.getUniversityByRoletypelist());

		// System.out.println("list "+comdao.getUniversityByRoletypelist(roleid));
		// }
		return new ModelAndView("user_mstTiles");
	}

	// save and update User Master page
		@RequestMapping(value = "/user_mstAction", method = RequestMethod.POST)
		public ModelAndView user_mstAction(@ModelAttribute("user_mstCMD") UserLogin p, HttpServletRequest request,
				ModelMap model, HttpSession session) {
			String username = session.getAttribute("username").toString();
			String pass = request.getParameter("user_password");
			String name = request.getParameter("user_name");
			String re_pass = request.getParameter("user_re_password");
			int roll = Integer.parseInt(request.getParameter("user_role_id"));
			//String army_no = request.getParameter("army_no");
			String role = request.getParameter("user_role_id");
			String mobile_no =request.getParameter("mobile_no");
			String email_id = request.getParameter("email_id");
			String university_id = request.getParameter("university_id");
			String institute_id = request.getParameter("institute_id");
			String course_publisher = request.getParameter("course_pb");
			String state_id1 = request.getParameter("state_id");
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			try {
				boolean pass_valid = validate(pass);
				if (pass_valid == false) {
					model.put("msg", "Password pattern doesn't match.");
					return new ModelAndView("redirect:user_mstUrl");
				}
				if (p.getLogin_name().trim() == "") {
					model.put("msg", "Please Enter User Name.");
					return new ModelAndView("redirect:user_mstUrl");
				} else if (validation.LoginNameLength(p.getLogin_name()) == false) {
					model.put("msg", validation.LoginNameMSG);
					return new ModelAndView("redirect:user_mstUrl");
				}
				if (name.trim() == "" || name.trim().equals("")) {
					model.put("msg", "Please Enter User ID.");
					return new ModelAndView("redirect:user_mstUrl");
				} else if (validation.UserNameLength(name) == false) {
					model.put("msg", validation.UserNameMSG);
					return new ModelAndView("redirect:user_mstUrl");
				}
//				if (army_no == "" || army_no.equals("")) {
//					model.put("msg", "Please Enter Army No.");
//					return new ModelAndView("redirect:user_mstUrl");
//				} else if (validation.ArmyNoLength(army_no) == false) {
//					model.put("msg", validation.ArmyNoMSG);
//					return new ModelAndView("redirect:user_mstUrl");
//				}
				if (pass.trim() == "" || pass.trim().equals("")) {
					model.put("msg", "Please Enter User ID.");
					return new ModelAndView("redirect:user_mstUrl");
				} else if (validation.PasswordLength(pass) == false) {
					model.put("msg", validation.PasswordMSG);
					return new ModelAndView("redirect:user_mstUrl");
				}
				else if(!PasswordValidator.validate(pass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
					model.put("msg",validation.PasswordPatternMSG);
					return new ModelAndView("redirect:changePassword");
				}
				
				if (!pass.trim().equals(re_pass.trim())) {
					model.put("msg", "Password and Re-Password didn't Match");
					return new ModelAndView("redirect:user_mstUrl");
				}
				if ( email_id == "" || email_id.equals("")) {
					model.put("msg", "Please Enter The Email Id");
					return new ModelAndView("redirect:user_mstUrl");
				}
				if (role == "" || role.equals("0")) {
					model.put("msg", "Please Select Role");
					return new ModelAndView("redirect:user_mstUrl");
				}
				
	            if (role == "19" || role.equals("19") ||role == "20" || role.equals("20") || role == "51" || role.equals("51") || role == "52" || role.equals("52")) {
					
					if (university_id.equals("0")) {
					model.put("msg", "Please Select University Name");
					return new ModelAndView("redirect:user_mstUrl");
					}
				}
				if (role == "22" || role.equals("22") || role == "23" || role.equals("23") || role == "24" || role.equals("24")
						|| role == "51" || role.equals("51") || role == "52" || role.equals("52")) {
					
					if (institute_id.equals("0")) {
					model.put("msg", "Please Select College Name");
					return new ModelAndView("redirect:user_mstUrl");
					}
				}

	            if (role == "31" || role.equals("31")) {
					
					if (course_publisher.equals("0")) {
					model.put("msg", "Please Select Course Publisher ");
					return new ModelAndView("redirect:user_mstUrl");
					}
				}
				
				
				if (!roledao.getuserExist(name).equals(false)) {
					model.put("msg", "Data Already Exist");
					return new ModelAndView("redirect:user_mstUrl");
				} else {
					
					List<EDU_LMS_UNIVERSITY_MSTR> state_id= getStateIdbyuniversity(university_id);
					
					String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode(pass);
					System.out.println(hashedPassword);
					p.setPassword(hashedPassword);
					p.setUserName(name);
					p.setEnabled(1);
					p.setAccountNonExpired(1);
					p.setAccountNonLocked(1);
					p.setCredentialsNonExpired(1);
					p.setAc_dc_date(modifydate);
					p.setMobile_no(mobile_no);
					p.setEmail_id(email_id.toLowerCase());
									
					if (university_id != null && !university_id.equals("")) {
						p.setUniversity_id(Integer.parseInt(university_id));
					} else {
						p.setUniversity_id(0);
					}
					if (institute_id != null && !institute_id.equals("")) {
						p.setInstitute_id(Integer.parseInt(institute_id));
					} else {
						p.setInstitute_id(0);
					}
					
					if(role.equals("19") || role.equals("20") || role == "51" || role.equals("51") || role == "52" || role.equals("52")   ) {
					p.setState_id(state_id.get(0).getState_id());
					}else if(role.equals("30")) {
						p.setState_id(Integer.parseInt(state_id1));
					}else {
						p.setState_id(Integer.parseInt(state_id1));	
					}
					
					//p.setArmy_no(army_no);
					p.setCreated_on(new Date());
					p.setCreated_by(username);
				
					if(role.equals("34")) {
					p.setCourse_pub_userid(Integer.parseInt(userid));
					}else {
						p.setCourse_pub_userid(0);
					}
					if(role.equals("67")) {
						p.setState_id(getStateIdbyinstitute(institute_id).get(0).getState_id());
						p.setUniversity_id((getStateIdbyinstitute(institute_id).get(0).getUniversity_id()));
					}
					UserRole role_tbl = new UserRole();
					sessionHQL.beginTransaction();

					int did = (Integer) sessionHQL.save(p);
					role_tbl.setRoleId(roll);
					role_tbl.setUserId(did);
					sessionHQL.save(role_tbl);
					sessionHQL.flush();
					sessionHQL.clear();
					
					if(!course_publisher.equals("0")){
						String hqlUpdatep = "update EDU_LMS_COURSE_PUBLISHER_MSTR set user_id=:user_id where id=:id";
						int app = sessionHQL.createQuery(hqlUpdatep).setParameter("user_id", did).setParameter("id", Integer.parseInt(course_publisher)).executeUpdate();
						System.err.println("login of course publisher is created - "+app);
					}
					
//					String hqlUpdatep = "update EDU_LMS_INSTITUTE_REGISTRATION set status=:status where id=:id";
//					int app = sessionHQL.createQuery(hqlUpdatep).setString("status", "2").setInteger("id", Integer.parseInt(institute_id)).executeUpdate();
					
					sessionHQL.getTransaction().commit();
					sessionHQL.close();
//					if(app > 0) {
//						System.err.println("approved-"+app);
//					}
					//roledao.userinsertdata("insert", did, roll);
					model.put("msg", "Data saved Successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.put("msg", "Data Save not Successfully");
			}
			return new ModelAndView("redirect:user_mstUrl");
		}
	

	String access_lvl1 = "";
	String user_name1 = "";
	
	// open Search User page
	@RequestMapping(value = "/search_user_mstUrl", method = RequestMethod.GET)
	public ModelAndView search_user_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_user_mstUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		} else {
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
			Mmap.put("access_lvl1", access_lvl1);
			Mmap.put("user_name", user_name1);
			return new ModelAndView("search_user_mstTiles");
//		}
	}
	
	// Search User Redirect Url
	@RequestMapping(value = "/search_user_by_role", method = RequestMethod.POST)
	public ModelAndView search_user_by_role(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "access_lvl1", required = false) String access_lvl,
			@RequestParam(value = "user_name", required = false) String user_name) {

		access_lvl1 = access_lvl;
		user_name1 = user_name;
		return new ModelAndView("redirect:search_user_mstUrl");
	}
	
	// Search User Data
//	@RequestMapping(value = "/getUserReportList",method=RequestMethod.GET)
//	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportList(@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
//		String qry = "";
//		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
//		qry = "";
//		if (access_lvl1.equals("All") && !access_lvl1.equals("Username")) {
//			qry += "";
//		}
//		if (!access_lvl1.equals("All") && access_lvl1.equals("Username")) {
//			qry += " and l.username='" + user_name1 + "' ";
//		}
//		DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasUserreport(criterias, qry, roleSubAccess);
//		return DatatablesResponse.build(dataSet, criterias);
//	}
	
	// update User Master Url
	@RequestMapping(value = "/update_user_mstUrl")
	public ModelAndView update_user_mstUrl(ModelMap Mmap, @ModelAttribute("updateid") int updateid,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession session) {
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("AccessTiles");
		}
		
		List<Map<String, Object>> list = roledao.getRole(updateid);
		Mmap.put("getRole", list);
		Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
		Mmap.put("edit_User_MstCMD", roledao.getUserLoginbyid(updateid));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_user_mstTiles");
	}

	// update User Master Action
	@RequestMapping(value = "/edit_User_Mst_Action")
	@ResponseBody
	public ModelAndView edit_User_Mst_Action(@ModelAttribute("edit_User_MstCMD") UserLogin updateid,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		try {
			int roll = Integer.parseInt(request.getParameter("user_role_id"));
			String access_lve1 = request.getParameter("access_lve1");
			String sub_access_lve1 = request.getParameter("sub_access_lve1");
			String roll1 = request.getParameter("user_role_id");
			
			
			
			
			if (updateid.getLogin_name() == "") {
				model.put("msg", "Please Enter User Name.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			boolean pass_valid = validate(request.getParameter("password"));
			if (pass_valid == false) {
				model.put("msg", "Password pattern doesn't match.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			if (request.getParameter("password") == "" || request.getParameter("password").equals("")) {
				model.put("msg", "Please Enter Password.");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			if (!request.getParameter("password").trim().equals(request.getParameter("re_password").trim())) {
				model.put("msg", "Password and Re-Password didn't match");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			if (roll1 == "" || roll1.equals("")) {
				model.put("msg", "Please Select Role");
				model.put("updateid", updateid.getUserId());
				return new ModelAndView("redirect:update_user_mstUrl");
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(updateid.getPassword());
			updateid.setPassword(hashedPassword);
			model.put("updateid", updateid.getUserId());
			model.put("msg", roledao.UpdateUserMst(updateid, roll,access_lve1, sub_access_lve1));
		} catch (Exception e) {

		}
		return new ModelAndView("redirect:search_user_mstUrl");
	}
	
	// Start User Activation or Deactivation Status
	// open User Active/Deactive Status Url
	String status1 = "";
	String login_name1 = "";
	@RequestMapping(value = "/user_statusUrl", method = RequestMethod.GET)
	public ModelAndView user_statusUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_statusUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			Mmap.put("getRoleType", roleMstrController.getRoleType());
			Mmap.put("status1", status1);
			Mmap.put("login_name1", login_name1);
			if (request.getHeader("Referer") == null) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("user_statusTiles");
		}
	}
	// redirect User Active/Deactive Status Url
	@RequestMapping(value = "/search_user_statusReport")
	public ModelAndView search_user_statusReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "login_name1", required = false) String login_name, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("user_statusUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			return new ModelAndView("AccessTiles");
		}
		status1 = status;
		login_name1 = login_name;
		return new ModelAndView("redirect:user_statusUrl");
	}
	// get User Active/Deactive Status Data
//	@RequestMapping(value = "/getUserReportactiveList1")
//	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportactiveList1(
//			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
//		String qry = "";
//		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
//		qry += "";
//		if (status1 != "") {
//			qry += "d.enabled = '" + status1 + "'";
//		}
//		if (login_name1 != "") {
//			qry += "and d.userName = '" + login_name1 + "'";
//		}
//		DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasActiveUserreport(criterias, qry,
//				roleSubAccess, status1);
//		return DatatablesResponse.build(dataSet, criterias);
//	}
	// End User Activation or Deactivation Status
	
	// for User Activation
	@RequestMapping(value = "/ActiveDataURl",method =RequestMethod.POST)
	public ModelAndView ActiveDataURl(ModelMap Mmap, @RequestParam(value = "acid1", required = false) String Activeid,
			@RequestParam(value = "status1", required = false) String status, HttpServletRequest request) {
		Mmap.put("status1", status);
		Mmap.put("login_name1", login_name1);
		Mmap.put("msg", roledao.getActiveData(Activeid));
		return new ModelAndView("redirect:search_user_statusReport");
	}

	// for User Deactivation
	@RequestMapping(value = "/DeactiveDataURl",method=RequestMethod.POST)
	public ModelAndView DeactiveDataURl(ModelMap Mmap,
			@RequestParam(value = "dcid1", required = false) String Deactiveid,
			@RequestParam(value = "status1", required = false) String status) {
		Mmap.put("status1", status);
		Mmap.put("login_name1", login_name1);
		Mmap.put("msg", roledao.getDeactiveData(Deactiveid));
		return new ModelAndView("redirect:search_user_statusReport");
	}
	

	// status of inactive user Url
	@RequestMapping(value = "/status_of_inactive_user_reportUrl", method = RequestMethod.GET)
	public ModelAndView status_of_inactive_user_reportUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request)
			throws ParseException {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("status_of_inactive_user_reportUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.getReportStatusOfInactiveUserList();
			Mmap.put("list", list);
			return new ModelAndView("status_of_inactive_user_reportTiles");
		}
	}
	
//	@RequestMapping(value = "/getStatebyCountry", method = RequestMethod.POST)
//	public @ResponseBody List<TB_STATE> getStatebyCountry(@RequestParam(value = "countryval", required = false) int countryval ) {
//		
//		Session sessionHQL = this.sessionFactory.openSession();
// 		Transaction tx1 = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery(" from TB_STATE where country_id =:country_id and status = '1'  order  by state_name");
//		q.setParameter("country_id", countryval);
//		@SuppressWarnings("unchecked")
//		List<TB_STATE> clist = (List<TB_STATE>) q.list();
//		tx1.commit();
//		sessionHQL.close();
//		return clist;
//	}

	@RequestMapping(value = "/getuniversitylistrole", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getuniversitylistrole(@RequestParam(value = "user_role_id", required = false) int user_role_id ) {
		
		Session sessionHQL = this.sessionFactory.openSession();
 		Transaction tx1 = sessionHQL.beginTransaction();
 		
 		System.err.println("------user_role_id - "+user_role_id) ;
 		
		Query q = sessionHQL.createQuery(" select distinct um.id, um.university_name from EDU_LMS_UNIVERSITY_MSTR um ,EDU_LMS_ORGANIZATION_MSTR om, Role ro where \r\n"
				+ "om.id=um.organization_id and ro.staff_lvl=om.organization and ro.roleId=:role_id order by um.id");
		q.setParameter("role_id", user_role_id);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		tx1.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	@RequestMapping(value = "/getInstitutelistrole", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getInstitutelistrole(@RequestParam(value = "user_role_id", required = false) int user_role_id ) {
		
		Session sessionHQL = this.sessionFactory.openSession();
 		Transaction tx1 = sessionHQL.beginTransaction();
 		 		
		Query q = sessionHQL.createQuery(" select distinct ir.id,ir.institute_name from EDU_LMS_INSTITUTE_REGISTRATION ir,EDU_LMS_UNIVERSITY_MSTR um,EDU_LMS_ORGANIZATION_MSTR om,Role ro\r\n"
				+ "where um.id=ir.university_id and om.id = um.organization_id and ro.staff_lvl=om.organization and ro.roleId=:role_id and ir.app_status='1' and ir.status!='2'");
		q.setParameter("role_id", user_role_id);
		@SuppressWarnings("unchecked")
		List<EDU_LMS_UNIVERSITY_MSTR> clist = (List<EDU_LMS_UNIVERSITY_MSTR>) q.list();
		tx1.commit();
		sessionHQL.close();
		return clist;
	}

	
	
	public List<EDU_LMS_UNIVERSITY_MSTR> getStateIdbyuniversity(String university_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"from EDU_LMS_UNIVERSITY_MSTR where id=:university_id");
			q1.setParameter("university_id",Integer.parseInt(university_id) );
			List<EDU_LMS_UNIVERSITY_MSTR> list = (List<EDU_LMS_UNIVERSITY_MSTR>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	
	////////////////PASSWORD_PATTERN //////////
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*])(?!.*\\s).{8,28})";
	
	public UserMstrController() {
	pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	public boolean validate(final String password) {
	matcher = pattern.matcher(password);
	return matcher.matches();
	}
	
	
//	satff_user_mstUrl
	//abhishek 24/03/23 staff creation======================================================================
		@RequestMapping(value = "/satff_user_mstUrl", method = RequestMethod.GET)
		public ModelAndView satff_user_mstUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			String roleid = session.getAttribute("roleid").toString();
			String staff_lvl = comdao.getStaffLvlfromRoleid(roleid);
			String role = session.getAttribute("role").toString();
			// int roleid = roleList.getRoleId();

			if (staff_lvl == null) {
				staff_lvl = "1";
			}

//			if (request.getHeader("Referer") == null) {
//				// session.invalidate();
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}

//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}

			// System.err.println("roleType------>
			// "+session.getAttribute("role_url").toString());

			Mmap.put("msg", msg);
			Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
//				Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
			Mmap.put("getRoleNameList", comdao.getRolebyStaffLvl(staff_lvl, role));
			Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
			Mmap.put("getCoursePubName", common.getCoursePubName(sessionFactory));
			Mmap.put("getapp_instituteNameList", common.getapp_instituteNameList(sessionFactory));
			Mmap.put("getUniversityList", comdao.getUniversityByRoletypelist());

			// System.out.println("list "+comdao.getUniversityByRoletypelist(roleid));
			// }
			return new ModelAndView("staff_user_mstTiles");
		}
		
		// save and update User Master page
			@RequestMapping(value = "/staff_user_mstAction", method = RequestMethod.POST)
			public ModelAndView staff_user_mstAction(@ModelAttribute("user_mstCMD") UserLogin p, HttpServletRequest request,
					ModelMap model, HttpSession session) {
				String username = session.getAttribute("username").toString();
				String staff_lvl = session.getAttribute("roleStaff_lvl").toString();
				String pass = request.getParameter("user_password");
				String name = request.getParameter("user_name");
				String re_pass = request.getParameter("user_re_password");
				String mobile_no =request.getParameter("mobile_no");
				String email_id = request.getParameter("email_id");
//				int roll = Integer.parseInt(request.getParameter("user_role_id"));
//				//String army_no = request.getParameter("army_no");
//				String role = request.getParameter("user_role_id");
//				String mobile_no =request.getParameter("mobile_no");
//				String email_id = request.getParameter("email_id");
//				String university_id = request.getParameter("university_id");
//				String institute_id = request.getParameter("institute_id");
//				String course_publisher = request.getParameter("course_pb");
//				String state_id1 = request.getParameter("state_id");
				
				String userid = session.getAttribute("userId_for_jnlp").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("user_mstUrl", roleid);
//				if (val == false) {
//					return new ModelAndView("AccessTiles");
//				}
				try {
					boolean pass_valid = validate(pass);
					if (pass_valid == false) {
						model.put("msg", "Password pattern doesn't match.");
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					if (p.getLogin_name().trim() == "") {
						model.put("msg", "Please Enter User Name.");
						return new ModelAndView("redirect:satff_user_mstUrl");
					} else if (validation.LoginNameLength(p.getLogin_name()) == false) {
						model.put("msg", validation.LoginNameMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					if (validation.isOnlyAlphabetNumber(p.getLogin_name()) == false) {
						model.put("msg", validation.isOnlyAlphabetNumberMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					if (name.trim() == "" || name.trim().equals("")) {
						model.put("msg", "Please Enter User ID.");
						return new ModelAndView("redirect:satff_user_mstUrl");
					} else if (validation.UserNameLength(name) == false) {
						model.put("msg", validation.UserNameMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					if (validation.isOnlyAlphabetNumber(name) == false) {
						model.put("msg", validation.isOnlyAlphabetNumberMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
//					if (army_no == "" || army_no.equals("")) {
//						model.put("msg", "Please Enter Army No.");
//						return new ModelAndView("redirect:user_mstUrl");
//					} else if (validation.ArmyNoLength(army_no) == false) {
//						model.put("msg", validation.ArmyNoMSG);
//						return new ModelAndView("redirect:user_mstUrl");
//					}
					if (pass.trim() == "" || pass.trim().equals("")) {
						model.put("msg", "Please Enter User ID.");
						return new ModelAndView("redirect:satff_user_mstUrl");
					} else if (validation.PasswordLength(pass) == false) {
						model.put("msg", validation.PasswordMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					else if(!PasswordValidator.validate(pass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
						model.put("msg",validation.PasswordPatternMSG);
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					
					if (!pass.trim().equals(re_pass.trim())) {
						model.put("msg", "Password and Re-Password didn't Match");
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
					if ( email_id == "" || email_id.equals("")) {
						model.put("msg", "Please Enter The Email Id");
						return new ModelAndView("redirect:satff_user_mstUrl");
					}
//					if (role == "" || role.equals("0")) {
//						model.put("msg", "Please Select Role");
//						return new ModelAndView("redirect:user_mstUrl");
//					}
//					
//		            if (role == "19" || role.equals("19") ||role == "20" || role.equals("20") || role == "51" || role.equals("51") || role == "52" || role.equals("52")) {
//						
//						if (university_id.equals("0")) {
//						model.put("msg", "Please Select University Name");
//						return new ModelAndView("redirect:user_mstUrl");
//						}
//					}
//					if (role == "22" || role.equals("22") || role == "23" || role.equals("23") || role == "24" || role.equals("24")
//							|| role == "51" || role.equals("51") || role == "52" || role.equals("52")) {
//						
//						if (institute_id.equals("0")) {
//						model.put("msg", "Please Select College Name");
//						return new ModelAndView("redirect:user_mstUrl");
//						}
//					}
	//
//		            if (role == "31" || role.equals("31")) {
//						
//						if (course_publisher.equals("0")) {
//						model.put("msg", "Please Select Course Publisher ");
//						return new ModelAndView("redirect:user_mstUrl");
//						}
//					}
					
					
					if (!roledao.getuserExist(name).equals(false)) {
						model.put("msg", "Data Already Exist");
						return new ModelAndView("redirect:satff_user_mstUrl");
					} else {
						
						//List<EDU_LMS_UNIVERSITY_MSTR> state_id= getStateIdbyuniversity(university_id);
						
						String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						String hashedPassword = passwordEncoder.encode(pass);
						System.out.println(hashedPassword);
						p.setPassword(hashedPassword);
						p.setUserName(name);
						p.setEnabled(1);
						p.setAccountNonExpired(1);
						p.setAccountNonLocked(1);
						p.setCredentialsNonExpired(1);
						p.setAc_dc_date(modifydate);
						p.setMobile_no(mobile_no);
						p.setEmail_id(email_id.toLowerCase());
						p.setUniversity_id(user.findByRoleId(Integer.parseInt(userid)).getUniversity_id());
						p.setInstitute_id(user.findByRoleId(Integer.parseInt(userid)).getInstitute_id());
						p.setState_id(user.findByRoleId(Integer.parseInt(userid)).getState_id());
					System.out.println("universit "+user.findByRoleId(Integer.parseInt(userid)).getUniversity_id());
//						if (university_id != null && !university_id.equals("")) {
//							p.setUniversity_id(Integer.parseInt(university_id));
//						} else {
//							p.setUniversity_id(0);
//						}
//						if (institute_id != null && !institute_id.equals("")) {
//							p.setInstitute_id(Integer.parseInt(institute_id));
//						} else {
//							p.setInstitute_id(0);
//						}
						
//						if(role.equals("19") || role.equals("20") || role == "51" || role.equals("51") || role == "52" || role.equals("52")   ) {
//						p.setState_id(state_id.get(0).getState_id());
//						}else if(role.equals("30")) {
//							p.setState_id(Integer.parseInt(state_id1));
//						}else {
//							p.setState_id(Integer.parseInt(state_id1));	
//						}
						
						//p.setArmy_no(army_no);
						p.setCreated_on(new Date());
						p.setCreated_by(username);
					
//						if(role.equals("34")) {
//						p.setCourse_pub_userid(Integer.parseInt(userid));
//						}else {
//							p.setCourse_pub_userid(0);
//						}
//						if(role.equals("67")) {
//							p.setState_id(getStateIdbyinstitute(institute_id).get(0).getState_id());
//							p.setUniversity_id((getStateIdbyinstitute(institute_id).get(0).getUniversity_id()));
//						}
						UserRole role_tbl = new UserRole();
						sessionHQL.beginTransaction();

						int did = (Integer) sessionHQL.save(p);
						System.out.println("did "+did);
						if(staff_lvl.equals("NCISM")) {
							role_tbl.setRoleId(22);

						}else {
							role_tbl.setRoleId(21);

						}
						role_tbl.setUserId(did);
						sessionHQL.save(role_tbl);
						sessionHQL.flush();
						sessionHQL.clear();
						
//						if(!course_publisher.equals("0")){
//							String hqlUpdatep = "update EDU_LMS_COURSE_PUBLISHER_MSTR set user_id=:user_id where id=:id";
//							int app = sessionHQL.createQuery(hqlUpdatep).setParameter("user_id", did).setParameter("id", Integer.parseInt(course_publisher)).executeUpdate();
//							System.err.println("login of course publisher is created - "+app);
//						}
						
//						String hqlUpdatep = "update EDU_LMS_INSTITUTE_REGISTRATION set status=:status where id=:id";
//						int app = sessionHQL.createQuery(hqlUpdatep).setString("status", "2").setInteger("id", Integer.parseInt(institute_id)).executeUpdate();
						
						sessionHQL.getTransaction().commit();
						sessionHQL.close();
//						if(app > 0) {
//							System.err.println("approved-"+app);
//						}
						if(staff_lvl.equals("NCISM")) {
							roledao.userinsertdata("insert", did, 22);

						}else if(staff_lvl.equals("NCH")){
							roledao.userinsertdata("insert", did, 21);

						}
						
						model.put("msg", "Data Saved Successfully");
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.put("msg", "Data Save not Successfully");
				}
				return new ModelAndView("redirect:satff_user_mstUrl");
			}
		
	//abhishek 31/03.23
			
			
			public List<EDU_LMS_INSTITUTE_REGISTRATION> getStateIdbyinstitute(String institute) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx1 = sessionHQL.beginTransaction();
				try {
					Query q1 = sessionHQL.createQuery(
							"from EDU_LMS_INSTITUTE_REGISTRATION where id=:institute");
					q1.setParameter("institute",Integer.parseInt(institute) );
					List<EDU_LMS_INSTITUTE_REGISTRATION> list = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q1.list();
					tx1.commit();
					return list;
				} catch (RuntimeException e) {
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			}
			
			
			
		
}
