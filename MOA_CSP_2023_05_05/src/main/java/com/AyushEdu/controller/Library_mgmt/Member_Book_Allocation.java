package com.AyushEdu.controller.Library_mgmt;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.Library_mgmt.TB_BOOK_DTL;
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_BOOK_REQ;
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Library_mgmt.MemberDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Member_Book_Allocation {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private MemberDao mm;

	@RequestMapping(value = "/Member_Book_Request", method = RequestMethod.GET)
	public ModelAndView Member_Book_Request(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		// SECURITY ABHISHEK
				if (request.getHeader("Referer") == null) {

					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Member_Book_Request", roleid1);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
		Mmap.put("msg", msg);
		Mmap.put("BookList", getBookInfo());

		return new ModelAndView("Member_Book_Request_Tiles");

	}

	@PostMapping(value = "/Member_Book_Req_Action")
	public ModelAndView Member_Book_Req_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			Principal principal, RedirectAttributes ra) throws ParseException {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Member_Book_Request", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

		String member_id = request.getParameter("member_id");
		String multiSelect_Book = request.getParameter("multiSelect_Book");
		String book_charges = request.getParameter("book_charges");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (request.getParameter("multiSelect_Book") == null || request.getParameter("multiSelect_Book").trim().equals("")) {
			ra.addAttribute("msg", "Please Select Book");
			return new ModelAndView("redirect:Member_Book_Request");
		}
		Long c = (Long) sessionHQL
				.createQuery(
						"select count(id) from TB_MEMBER_BOOK_REQ where member_id=:member_id and return_status = 1")
				.setParameter("member_id", member_id).uniqueResult();

				//.setParameter("member_id", member_id.toUpperCase()).uniqueResult();
				
		System.err.println("=============" + mm.getAyushIdFromUserId(Integer.parseInt(userid)) + userid);
		if (c == 0) {
			System.err.println("hellllllllloooooooooooo" + multiSelect_Book);

			TB_MEMBER_BOOK_REQ TB = new TB_MEMBER_BOOK_REQ();
			if(mm.getAyushIdFromUserId(Integer.parseInt(userid)) != null )
			TB.setMember_id(mm.getAyushIdFromUserId(Integer.parseInt(userid)).get(0).get("ayush_id").toString());
			TB.setBook_select(multiSelect_Book);
			TB.setBook_charges(book_charges);
			TB.setBook_req_date(new Date());
			TB.setCreated_by(username);
			TB.setCreated_date(new Date());
			TB.setReturn_status(1);
			sessionHQL.save(TB);
			sessionHQL.flush();
			sessionHQL.clear();
			ra.addAttribute("msg", "Paid Successfully.");
		} else {
			ra.addAttribute("msg", "Book Already Requested/Not Returned.");
		}

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:Member_Book_Request");
	}

	@PostMapping("/getMemberDetails")
	public @ResponseBody List<TB_MEMBER_DTL> getMemberDetails(ModelMap model, String member_id) {

		Session session1 = this.sessionFactory.openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("from TB_MEMBER_DTL where upper(member_id)=:member_id order by id")
				.setParameter("member_id", member_id.toUpperCase());

		@SuppressWarnings("unchecked")

		List<TB_MEMBER_DTL> list = (List<TB_MEMBER_DTL>) q1.list();

		return list;
	}

	@PostMapping("/getBookPrice")
	public @ResponseBody List<TB_BOOK_DTL> getBookPrice(ModelMap model, String book_id) {

		Session session1 = this.sessionFactory.openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("from TB_BOOK_DTL where id=:id order by id").setParameter("id",
				Integer.parseInt(book_id));

		@SuppressWarnings("unchecked")

		List<TB_BOOK_DTL> list = (List<TB_BOOK_DTL>) q1.list();

		return list;
	}

	public List<TB_BOOK_DTL> getBookInfo() {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery("from TB_BOOK_DTL where total_book_qty > 0");
			@SuppressWarnings("unchecked")
			List<TB_BOOK_DTL> list = (List<TB_BOOK_DTL>) q1.list();
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

///////////////////////////payment of books open//////////////////	

	@RequestMapping(value = "/payment_req", method = RequestMethod.POST)
	public ModelAndView payment_req(@ModelAttribute("id9") String id, BindingResult result, ModelMap Mmap,
			HttpSession session, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "book_list1", required = false) String book_list1,
			@RequestParam(value = "book_price1", required = false) String book_price1, HttpServletRequest request, RedirectAttributes ra) {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Member_Book_Request", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("systemList", getSystemList());
		Mmap.put("msg", msg);

//		System.err.println("----------------" + request.getParameter("multiSelect_Book"));
		if (book_list1 == null || book_list1.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Book");
			return new ModelAndView("redirect:Member_Book_Request");
		}
		Mmap.put("book_list", book_list1);
		Mmap.put("book_price", book_price1);
		Mmap.put("BookList", getBookInfo());

		Session session1 = this.sessionFactory.openSession();

		Transaction tx1 = session1.beginTransaction();

		Query q1 = session1.createQuery("from TB_MEMBER_DTL where upper(member_id)=:member_id order by id")
				.setParameter("member_id", id.toUpperCase());

		@SuppressWarnings("unchecked")

		List<TB_MEMBER_DTL> list = (List<TB_MEMBER_DTL>) q1.list();
		Mmap.put("LibCMD", list);

		return new ModelAndView("Payment_of_Book_Tiles");

	}
////
//	@PostMapping(value = "/Payment_of_Book_Action")
//	public ModelAndView Payment_of_Book_Action(@Validated @ModelAttribute("PayBookCMD") String td, BindingResult result,
//			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
//			String username) {
//
//		System.err.println("hellllllllloooooooooooo");
//
//		Date date = new Date();
//
//		String member_id = request.getParameter("member_id");
//		String member_name = request.getParameter("member_name");
//		String member_dept = request.getParameter("member_dept");
//		String member_joined_date = request.getParameter("member_joined_date");
//		String book_name = request.getParameter("book_name");
//		String book_purchased_date = request.getParameter("book_purchased_date");
//		int book_price = Integer.parseInt(request.getParameter("book_price"));
//
//		int id = td.getId() > 0 ? td.getId() : 0;
//
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//
//////try {
////Long c = (Long) sessionHQL.createQuery(
////"select count(id) from  TB_MEMBER_BOOK_REQ where book_number=:book_number and " +
////"upper(book_name)=:book_name and upper(book_author)=:book_author and " +
////"book_price=:book_price and total_book_qty=:total_book_qty and date_of_entered=:date_of_entered and book_dept=:book_dept and id !=:id")
////.setParameter("id", td.getId())
////.setParameter("book_number", td.getBook_number())
////.setParameter("book_name", td.getBook_number().toUpperCase())
////.setParameter("book_author",td.getBook_author())
////.setParameter("book_price", td.getBook_price())
////.setParameter("total_book_qty", td.getTotal_book_qty())
////.setParameter("date_of_entered", td.getDate_of_entered())
////.setParameter("book_dept", td.getBook_dept())
////.uniqueResult();
////
////if (id == 0) {
////td.setBook_number(book_number);
////td.setBook_name(book_name);
////td.setBook_author(book_author);
////td.setBook_price(book_price);
////td.setTotal_book_qty(total_book_qty);
////td.setDate_of_entered(date);
////td.setBook_dept(book_dept);
////td.setCreated_by("Hiral");
////td.setCreated_date(date);
////td.setModified_by(username);
////td.setModified_date(date);
////if (c == 0) {
////sessionHQL.save(td);
////sessionHQL.flush();
////sessionHQL.clear();
////ra.addAttribute("msg", "Data Saved Successfully.");
////} else {
////ra.addAttribute("msg", "Data already Exist.");
////}
////}
//
//		tx.commit();
//
//		sessionHQL.close();
//		return new ModelAndView("redirect:Payment_of_BookUrl");
//
//	}

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

}
