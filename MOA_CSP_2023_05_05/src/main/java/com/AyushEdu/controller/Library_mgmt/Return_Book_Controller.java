package com.AyushEdu.controller.Library_mgmt;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
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
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_BOOK_REQ;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Library_mgmt.Return_BookDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Return_Book_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	@Autowired
	ValidationController validation;

	@Autowired
	Return_BookDao asrdao;
	private Date date;

	@RequestMapping(value = "/Return_BookUrl", method = RequestMethod.GET)
	public ModelAndView Approved_Student_Room_RequestUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("msg", msg);

		return new ModelAndView("Return_BookTiles");

	}

	@PostMapping("/return_book_req")
	public ModelAndView return_book_req(@Validated @ModelAttribute("id3") int id3, HttpServletRequest request,
			ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra) throws ParseException {
	
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();


		if (id3 != 0) {

			TB_MEMBER_BOOK_REQ td2 = (TB_MEMBER_BOOK_REQ) sessionHQL.get(TB_MEMBER_BOOK_REQ.class, id3);

			td2.setReturn_status(0);
			td2.setBook_select("");
			td2.setBook_charges("");
			sessionHQL.update(td2);
			sessionHQL.flush();
			sessionHQL.clear();
			ra.addAttribute("msg", "Books Returned Successfully.");

		} else {
			ra.addAttribute("msg", "Something Went Wrong");

		}

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:Return_BookUrl");
	}

	@PostMapping("/getFilterReturn_Book_data")
	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String hostel_id, String room_no, String no_of_beds,
			String rent, String registration_no) {

		return asrdao.DataTableReturn_BookDataList(startPage, pageLength, Search, orderColunm, orderType, hostel_id,
				room_no, no_of_beds, rent, registration_no);
	}

	@PostMapping("/getTotalReturn_Book_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String hostel_id,
			String room_no, String no_of_beds, String rent, String registration_no) {
		return asrdao.DataTableReturn_BookDataTotalCount(Search, hostel_id, room_no, no_of_beds, rent, registration_no);

	}

}
