package com.AyushEdu.controller.Library_mgmt;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Library_mgmt.MemberDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MemberController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MemberDao ldao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	@Autowired
	ValidationController validation;

	private Date date;

	private String city_name;

	private String ayush_id;

	///////////////////////////////// OPEN show all member/////////////////////////////////////////////////////
	@RequestMapping(value = "/show_all_membersUrl", method = RequestMethod.GET)
	public ModelAndView show_all_membersUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("show_all_membersUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		/* Mmap.put("getMedStateName",common.getMedStateName(sessionFactory)); */
		return new ModelAndView("show_all_members_Tiles");

	}

	// ==========================================OPEN Add book page==========================================//
	@GetMapping("/add_memberUrl")
	public ModelAndView addMemberShowPage(@RequestParam(value = "msg", required = false) String msg,ModelMap model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("member", new TB_MEMBER_DTL());
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_memberUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		/* return "addmember"; */
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("gender", getGenderDetails(sessionFactory));
		model.put("systemList", getSystemList());
		model.put("msg", msg);

		return new ModelAndView("add_member_Tiles");

	}

	// ==========================================SAVE add member
	// details==========================================//

	@PostMapping(value = "/add_memberAction")
	public ModelAndView add_memberAction(@Validated @ModelAttribute("BookCMD") TB_MEMBER_DTL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra)
			throws ParseException {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("add_memberUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

		String member_id = request.getParameter("ayush_id");
		String member_type = request.getParameter("member_type");
		String member_name = request.getParameter("member_name");
		String state_name = request.getParameter("state_name");
		String district_name = request.getParameter("member_district_name");
		String city_name = request.getParameter("city_name");
		String member_phone_number = request.getParameter("member_phone_number");
		String member_gender = request.getParameter("member_gender");
		String member_age = request.getParameter("member_age");
		String member_joined_date = request.getParameter("member_joined_date");
		String member_dept = request.getParameter("member_dept");
		
		
		if (member_type == null || member_type.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Member Type");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_id == null || member_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Ayush Id");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isOnlyAlphabetNumber(member_id) == false) {
			ra.addAttribute("msg","Ayush Id "+ validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_name == null || member_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Member Name");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		
		if (validation.isOnlyAlphabet(member_name) == false) {
			ra.addAttribute("msg", "Member Name " + validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.maxlengthcheck100(member_name) == false) {
			ra.addAttribute("msg", "Member Name " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (state_name == null || state_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter State Name");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (district_name == null || district_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select District Name");
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (city_name == null || city_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter City");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isOnlyAlphabet(city_name) == false) {
			ra.addAttribute("msg", "City " + validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.maxlengthcheck100(city_name) == false) {
			ra.addAttribute("msg", "City " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (validation.isOnlyNumerLib(member_phone_number) == false) {
			ra.addAttribute("msg", "Member Phone Number " + validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isValidMobileNo(member_phone_number) == false) {
			ra.addAttribute("msg", "Member Phone Number " + validation.isOnlyMobilenumberMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_gender == null || member_gender.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Gender");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_age == null || member_age.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Member Age");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (Integer.parseInt(member_age) < 17 || Integer.parseInt(member_age) > 100 || member_age == ""
				|| member_age == "0") {
			ra.addAttribute("msg", "Age Should Be Greater Than 17 Years,member_age");
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (validation.isOnlyNumerLib(member_age) == false) {
			ra.addAttribute("msg", "Member Age " + validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_joined_date == null || member_joined_date.trim().equals("")
				|| member_joined_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Member Joined Date");
			return new ModelAndView("redirect:add_memberUrl");
		}
		

		if (validation.isOnlyDateFormat(member_joined_date) == false) {
			ra.addAttribute("msg", "Member Joined Date " + validation.isOnlyDateFormatMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		
		
		if (member_dept == null || member_dept.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Member Dept");
			return new ModelAndView("redirect:add_memberUrl");
		}

		System.err.println("--------------" + member_id);
		int id = td.getId() > 0 ? td.getId() : 0;

		System.err.println("DATA================" + td.toString());
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from TB_MEMBER_DTL where member_id=: member_id and member_type=: member_type and upper(member_name)=:member_name and state_name=:state_name and member_district_name=:member_district_name and upper(city_name)=:city_name "
						+ " and member_phone_number=:member_phone_number and member_gender=:member_gender and member_age=:member_age and member_dept=:member_dept and id !=:id")

				.setParameter("id", td.getId()).setParameter("member_id", member_id.toUpperCase())
				.setParameter("member_type", Integer.parseInt(member_type))
				.setParameter("member_name", member_name.toUpperCase())
				.setParameter("state_name", Integer.parseInt(state_name))
				.setParameter("member_district_name", Integer.parseInt(district_name))
				.setParameter("city_name", city_name.toUpperCase())
				.setParameter("member_phone_number", member_phone_number)
				.setParameter("member_gender", Integer.parseInt(member_gender))
				.setParameter("member_age", Integer.parseInt(member_age))
				.setParameter("member_dept", Integer.parseInt(member_dept)).uniqueResult();

		System.err.println("Long c " + c);

		
		if (id == 0) {
			td.setId(id);
			td.setMember_id(member_id);
			td.setMember_name(member_name);
			td.setMember_type(Integer.parseInt(member_type));
			td.setCity_name(city_name);
			td.setMember_district_name(Integer.parseInt(district_name));
			td.setState_name(Integer.parseInt(state_name));
			td.setMember_phone_number(member_phone_number);
			td.setMember_joined_date(formate.parse(member_joined_date));
			td.setMember_dept(Integer.parseInt(member_dept));
			td.setMember_gender(Integer.parseInt(member_gender));
			td.setMember_age(Integer.parseInt(member_age));
			td.setCreated_by(username);
			td.setCreated_date(new Date());

			if (c == 0) {

				Long count = (Long) sessionHQL.createQuery("select count(id) from TB_MEMBER_DTL").uniqueResult();

//				String member_id ="";
//				if(count == 0) {
//				
//				member_id = Calendar.getInstance().get(Calendar.YEAR) + "000000";
//				
//				}else {
//					Query q1 = sessionHQL.createQuery(
//							"from TB_MEMBER_DTL order by id desc");
//					
//					List<TB_MEMBER_DTL> list = (List<TB_MEMBER_DTL>) q1.list();
//
//					String t = list.get(0).getMember_id();
//					
//					if(t != null &&!t.equals("")) {
//						member_id = Integer.parseInt(list.get(0).getMember_id()) + 1 + "";
//					}
//					
//				}

				sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();
				ra.addAttribute("msg", "Data Saved Successfully.");
			} else {
				ra.addAttribute("msg", "Data already Exist.");
			}
		}

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:show_all_membersUrl");
	}

	@RequestMapping(value = "/getFiltershow_all_member_Master_data", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> DataTableTB_MEMBER_DTLDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String member_name, String member_joined_date) {
		System.err.println("===================" + ldao.DataTableTB_MEMBER_DTLDataList(startPage, pageLength,

				Search, orderColunm, orderType, member_name, member_joined_date));
		return ldao.DataTableTB_MEMBER_DTLDataList(startPage, pageLength, Search, orderColunm, orderType, member_name,
				member_joined_date);

	}

	@PostMapping("/getshow_all_member_MasterCount")
	public @ResponseBody long DataTableTB_MEMBER_DTLTotalCount(String Search, String member_name,
			String member_joined_date, String member_dept, String member_phone_number) {

		return ldao.DataTableTB_MEMBER_DTLTotalCount(Search, member_name, member_joined_date, member_dept,
				member_phone_number);

	}
////////////////////////////////////////AUTOCOMPLETE MEMBER DETAILS///////////////////////////////////////////////////////////

	@PostMapping("/getMemberDetailsAuto")
	public @ResponseBody List<Map<String, Object>> getMemberDetailsAuto(String autoString, HttpSession session) {

		return ldao.MemberDataAuto(autoString);
	}

	// ==========================================DELETE Book
	// details==========================================
	@RequestMapping("/delete_memberUrl")
	public ModelAndView deleteMemberFromList(@Validated @ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		try {
			Session session1 = this.sessionFactory.openSession();

			Transaction tx1 = session1.beginTransaction();
			TB_MEMBER_DTL member = (TB_MEMBER_DTL) session1.get(TB_MEMBER_DTL.class, id);

			session1.delete(member);
			session1.flush();
			session1.clear();

			tx1.commit();
			ra.addAttribute("msg", "Member Record Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			ra.addAttribute("msg", "Something Went Wrong..");
		}

		return new ModelAndView("redirect:show_all_membersUrl");

	}

	@PostMapping("/edit_member_Url")
	public ModelAndView edit_member_Url(ModelMap model, @ModelAttribute("id3") int id, BindingResult result) {
		model.put("members", new TB_MEMBER_DTL());
		model.put("member_cmd", ldao.getMemberDetails(id));
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("gender", getGenderDetails(sessionFactory));
		model.put("systemList", getSystemList());

		return new ModelAndView("edit_member_Tiles");
	}

	@PostMapping("/edit_member_Action")
	public ModelAndView edit_member_Action(@Validated @ModelAttribute("BookCMD") TB_MEMBER_DTL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra)
			throws ParseException {
		System.err.println("hellllllllloooooooooooo");
		String username = session.getAttribute("username").toString();

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		Session sessionHQL = this.sessionFactory.openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String member_type = request.getParameter("member_type");
		String member_id = request.getParameter("ayush_id");
		String member_name = request.getParameter("member_name");
		String state_name = request.getParameter("state_name");
		String district_name = request.getParameter("member_district_name");
		String city_name = request.getParameter("city_name");
		String member_phone_number = request.getParameter("member_phone_number");
		String member_gender = request.getParameter("member_gender");
		String member_age = request.getParameter("member_age");
		String member_joined_date = request.getParameter("member_joined_date");
		String member_dept = request.getParameter("member_dept");

		if (member_type == null || member_type.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Member Type");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_id == null || member_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Ayush Id");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isOnlyAlphabetNumber(member_id) == false) {
			ra.addAttribute("msg","Ayush Id "+ validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_name == null || member_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Member Name");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		
		if (validation.isOnlyAlphabet(member_name) == false) {
			ra.addAttribute("msg", "Member Name " + validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.maxlengthcheck100(member_name) == false) {
			ra.addAttribute("msg", "Member Name " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (state_name == null || state_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter State Name");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (district_name == null || district_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select District Name");
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (city_name == null || city_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter City");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isOnlyAlphabet(city_name) == false) {
			ra.addAttribute("msg", "City " + validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.maxlengthcheck100(city_name) == false) {
			ra.addAttribute("msg", "City " + validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (validation.isOnlyNumerLib(member_phone_number) == false) {
			ra.addAttribute("msg", "Member Phone Number " + validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isValidMobileNo(member_phone_number) == false) {
			ra.addAttribute("msg", "Member Phone Number " + validation.isOnlyMobilenumberMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_gender == null || member_gender.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Gender");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_age == null || member_age.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Member Age");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (Integer.parseInt(member_age) < 17 || Integer.parseInt(member_age) > 100 || member_age == ""
				|| member_age == "0") {
			ra.addAttribute("msg", "Age Should Be Greater Than 17 Years,member_age");
			return new ModelAndView("redirect:add_memberUrl");
		}

		if (validation.isOnlyNumerLib(member_age) == false) {
			ra.addAttribute("msg", "Member Age " + validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_joined_date == null || member_joined_date.trim().equals("") || member_joined_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Member Joined Date");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (validation.isOnlyDateFormat(member_joined_date) == false) {
			ra.addAttribute("msg", "Member Joined Date " + validation.isOnlyDateFormatMSG);
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		if (member_dept == null || member_dept.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Member Dept");
			return new ModelAndView("redirect:add_memberUrl");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;

		if (id != 0) {

			TB_MEMBER_DTL td2 = (TB_MEMBER_DTL) sessionHQL.get(TB_MEMBER_DTL.class, id);

			td2.setMember_name(member_name);
			td2.setCity_name(city_name);
			td2.setMember_district_name(Integer.parseInt(district_name));
			td2.setState_name(Integer.parseInt(state_name));
			td2.setMember_phone_number(member_phone_number);
			td2.setMember_joined_date(formate.parse(member_joined_date));
			td2.setMember_dept(Integer.parseInt(member_dept));
			td2.setMember_gender(Integer.parseInt(member_gender));
			td2.setMember_age(Integer.parseInt(member_age));
			td2.setModified_by(username);
			td2.setModified_date(new Date());
			sessionHQL.update(td2);
			sessionHQL.flush();
			sessionHQL.clear();
			ra.addAttribute("msg", "Data Updated Successfully.");

		} else {
			ra.addAttribute("msg", "Something Went Wrong");

		}

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:show_all_membersUrl");
	}

	/////////////////////////////// OPEN PAGE get member
	/////////////////////////////// details///////////////////////////////////
	@RequestMapping(value = "/Get_member_detailsUrl", method = RequestMethod.GET)
	public ModelAndView Get_member_detailsUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("msg", msg);
		return new ModelAndView("Get_member_details_Tiles");

	}

	///////////////// open page of smart show
	///////////////// borrow//////////////////////////////////////
	@GetMapping("/Smart_show_borrowUrl")
	public ModelAndView Smart_show_borrowUrl(ModelMap model) {
		model.addAttribute("member", new TB_MEMBER_DTL());

		/* return "addmember"; */
		return new ModelAndView("Smart_show_borrow_Tiles");

	}

	@RequestMapping(value = "/Total_historyUrl", method = RequestMethod.GET)
	public ModelAndView Total_historyUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("msg", msg);
		/* Mmap.put("getMedStateName",common.getMedStateName(sessionFactory)); */
		return new ModelAndView("Total_history_Tiles");

	}

	public List<String> getGenderDetails(SessionFactory sessionFactory) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select codevalue,label from T_Domain_Value where domainid='GENDER' order by codevalue");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
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

	@PostMapping("/getDisDetails")
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDisDetails(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam String state_name) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"from EDU_LMS_DISTRICT_MSTR where status='1' and state_id=:state_id order by district_name ")
					.setParameter("state_id", Integer.parseInt(state_name));
			List<EDU_LMS_DISTRICT_MSTR> list = (List<EDU_LMS_DISTRICT_MSTR>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public List<EDU_LMS_SYSTEM_MSTR> getSystemList() {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' ");
			List<EDU_LMS_SYSTEM_MSTR> list = (List<EDU_LMS_SYSTEM_MSTR>) q1.list();
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@PostMapping("/getMemberdetailsbyayushidDetails")
	public @ResponseBody List<Map<String, Object>> getMemberdetailsbyayushidDetails(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam String ayush_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		return ldao.MemberdetailsbyayushidDetailsMethod(ayush_id);
	}

//	public static long numbGen() {
//	    while (true) {
//	        long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
//	        if (String.valueOf(numb).length() == 14)
//	            return numb;
//	    }
//	}

}
