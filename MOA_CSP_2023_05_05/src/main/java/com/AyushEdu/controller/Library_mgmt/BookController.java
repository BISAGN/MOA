package com.AyushEdu.controller.Library_mgmt;

import java.security.Principal;
import java.sql.SQLSyntaxErrorException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;

import com.AyushEdu.Models.Library_mgmt.TB_BOOK_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Library_mgmt.SmartLibraryDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class BookController {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SmartLibraryDao sld;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	
	@Autowired
	ValidationController validation;
	
	/////////////////////////////////// open page show all books/////////////////////////////////////////
	@RequestMapping(value = "/show_all_booksUrl", method = RequestMethod.GET)
	public ModelAndView show_all_booksUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		// SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {

			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("show_all_booksUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("systemList", getSystemList());
		Mmap.put("msg", msg);
		return new ModelAndView("show_all_books_Tiles");

	}

/////////////////////////////OPEN PAGE BOOK DETAILS///////////////////////

	@RequestMapping(value = "/book_detailsUrl", method = RequestMethod.GET)
	public ModelAndView add_bookUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("book_detailsUrl", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("book_details_Tiles");

	}

/////////////////////////////OPEN PAGE BOOK DETAILS///////////////////////
	@RequestMapping(value = "/get_book_details_Url", method = RequestMethod.GET)
	public ModelAndView get_book_details_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
				if (request.getHeader("Referer") == null) {
					
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("get_book_details_Url", roleid1);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
		Mmap.put("msg", msg);
		return new ModelAndView("get_book_details_Tiles");

	}
/////////////////////////////OPEN PAGE SMART BOOK DETAILS///////////////////////

	@RequestMapping(value = "/smart_book_system_Url", method = RequestMethod.GET)
	public ModelAndView smart_book_system_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if (request.getHeader("Referer") == null) {
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("smart_book_system_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		return new ModelAndView("smart_book_system_Tiles");

	}

///////////////////////////////////OPEN PAGE ADD BOOK///////////////////////////////////////	

	@GetMapping("/add_bookUrl")
	public ModelAndView addMemberShowPage(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		
		//SECURITY ABHISHEK
				if (request.getHeader("Referer") == null) {
					
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("smart_book_system_Url", roleid1);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				Mmap.addAttribute("book", new TB_BOOK_DTL());
		/* return "addmember"; */
		/*
		 * model.put("getMedStateName", common.getMedStateName(sessionFactory));
		 * model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		 */
		return new ModelAndView("add_book_Tiles");

	}

//////////////////////SAVE ADD BOOK DATA//////////////////////

	@PostMapping(value = "/show_all_booksAction")
	public ModelAndView show_all_booksAction(@Validated @ModelAttribute("BookCMD") TB_BOOK_DTL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			String username) throws ParseException {

		System.err.println("hellllllllloooooooooooo");

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

		Date date = new Date();

		String book_number = request.getParameter("book_number");
		String book_name = request.getParameter("book_name");
		String book_author = request.getParameter("book_author");
		String book_price = request.getParameter("book_price");
		String total_book_qty = request.getParameter("total_book_qty");
		String date_of_entered = request.getParameter("date_of_entered");
		String book_dept = request.getParameter("book_dept");
		
		if (book_number == null || book_number.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book No.");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabetNumber(book_number) == false) {
			ra.addAttribute("msg","Book Number "+ validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheckheight(book_number) == false) {
			ra.addAttribute("msg","Book Number "+ validation.MaxlengthcheckMSGforheight);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_name == null || book_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Name");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabet(book_name) == false) {
			ra.addAttribute("msg","Book Name "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck100(book_name) == false) {
			ra.addAttribute("msg","Book Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_author == null || book_author.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Author");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabet(book_author) == false) {
			ra.addAttribute("msg","Book Author "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck100(book_author) == false) {
			ra.addAttribute("msg","Book Author "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_price == null || book_price.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Price");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyNumerLib(book_price) == false) {
			ra.addAttribute("msg","Book Price "+ validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck6(book_price) == false) {
			ra.addAttribute("msg","Book Price "+ validation.MaxlengthcheckMSG6);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (total_book_qty == null || total_book_qty.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Quantity");
			return new ModelAndView("redirect:show_all_booksUrl");
		}

		if (validation.isOnlyNumerLib(total_book_qty) == false) {
			ra.addAttribute("msg", "Book Quantity " + validation.isOnlyNumerMSGLib);
			return new ModelAndView("redirect:show_all_booksUrl");
		}

		if (validation.maxlengthcheckC(total_book_qty) == false) {
			ra.addAttribute("msg","Book Quantity "+ validation.MaxlengthcheckMSGC);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		if (book_dept == null || book_dept.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Book Department");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		

		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		// try {
		Long c = (Long) sessionHQL
				.createQuery("select count(id) from  TB_BOOK_DTL where book_number=:book_number and "
						+ "upper(book_name)=:book_name and upper(book_author)=:book_author and "
						+ "book_price=:book_price and total_book_qty=:total_book_qty and  book_dept=:book_dept")

				.setParameter("book_number", book_number).setParameter("book_name", book_name.toUpperCase())
				.setParameter("book_author", book_author.toUpperCase())
				.setParameter("book_price", Integer.parseInt(book_price))
				.setParameter("total_book_qty", Integer.parseInt(total_book_qty))
				.setParameter("book_dept", Integer.parseInt(book_dept)).uniqueResult();

		if (id == 0) {
			td.setBook_number(book_number);
			td.setBook_name(book_name);
			td.setBook_author(book_author);
			td.setBook_price(Integer.parseInt(book_price));
			td.setTotal_book_qty(Integer.parseInt(total_book_qty));
			td.setDate_of_entered(date);
			td.setBook_dept(Integer.parseInt(book_dept));
			td.setCreated_by(username);
			td.setCreated_date(date);

			System.err.println("------------------->" + date_of_entered);
			if (c == 0) {
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
		return new ModelAndView("redirect:show_all_booksUrl");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@PostMapping("/getFilterall_books_data")
	public @ResponseBody List<Map<String, Object>> getFilterall_books_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String book_number, String book_name, String book_author,
			String book_price, String total_book_qty, String date_of_entered, int book_dept) {
		return sld.DataTableall_booksDataList(startPage, pageLength, Search, orderColunm, orderType, book_number,
				book_name, book_author, book_price, total_book_qty, date_of_entered, book_dept);

	}

	@PostMapping("/getTotalBookMstr_dataCount")
	public @ResponseBody long getTotalBookMstr_dataCount(HttpSession sessionUserId, String Search, String book_number,
			String book_name, String book_author, String book_price, String total_book_qty, String date_of_entered,
			int book_dept) {
		return sld.DataTableall_booksDataTotalCount(Search, book_number, book_name, book_author, book_price,
				total_book_qty, date_of_entered, book_dept);
	}

//////////////////////////////DELETE BOOK//////////////////////////////////////////

	@RequestMapping("/delete_book")
	public ModelAndView deleteBookFromList(@Validated @ModelAttribute("id4") int id, RedirectAttributes ra) {
		try {
			Session session1 = this.sessionFactory.openSession();

			Transaction tx1 = session1.beginTransaction();

			TB_BOOK_DTL book = (TB_BOOK_DTL) session1.get(TB_BOOK_DTL.class, id);

			session1.delete(book);
			session1.flush();
			session1.clear();

			tx1.commit();
			ra.addAttribute("msg", "Book Record Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			ra.addAttribute("msg", "Something Went Wrong");

		}
		return new ModelAndView("redirect:show_all_booksUrl");
	}

	
	//-------------------------------------------------Edit Action----------------------------------------------------//
	
	@PostMapping("/edit_book_Url_Action")
	public ModelAndView edit_book_Url_Action(@Validated @ModelAttribute("BookCMD") TB_BOOK_DTL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra)
			throws ParseException {
		String username = session.getAttribute("username").toString();

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String book_number = request.getParameter("book_number");
		String book_name = request.getParameter("book_name");
		String book_author = request.getParameter("book_author");
		String book_price = request.getParameter("book_price");
		String total_book_qty = request.getParameter("total_book_qty");
		String book_dept = request.getParameter("book_dept");
		
		
		if (book_number == null || book_number.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book No.");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabetNumber(book_number) == false) {
			ra.addAttribute("msg","Book Number "+ validation.isOnlyAlphabetNumberMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheckheight(book_number) == false) {
			ra.addAttribute("msg","Book Number "+ validation.MaxlengthcheckMSGforheight);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_name == null || book_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Name");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabet(book_name) == false) {
			ra.addAttribute("msg","Book Name "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck100(book_name) == false) {
			ra.addAttribute("msg","Book Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_author == null || book_author.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Author");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyAlphabet(book_author) == false) {
			ra.addAttribute("msg","Book Author "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck100(book_author) == false) {
			ra.addAttribute("msg","Book Author "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (book_price == null || book_price.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Price");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.isOnlyNumer(book_price) == false) {
			ra.addAttribute("msg","Book Price "+ validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (validation.maxlengthcheck6(book_price) == false) {
			ra.addAttribute("msg","Book Price "+ validation.MaxlengthcheckMSG6);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		
		if (total_book_qty == null || total_book_qty.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Quantity");
			return new ModelAndView("redirect:show_all_booksUrl");
		}

		if (validation.isOnlyNumer(total_book_qty) == false) {
			ra.addAttribute("msg", "Book Quantity " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:show_all_booksUrl");
		}

		if (validation.maxlengthcheckC(total_book_qty) == false) {
			ra.addAttribute("msg","Book Quantity "+ validation.MaxlengthcheckMSGC);
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		if (book_dept == null || book_dept.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Book Department");
			return new ModelAndView("redirect:show_all_booksUrl");
		}
		

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		if (id != 0) {

			TB_BOOK_DTL td2 = (TB_BOOK_DTL) sessionHQL.get(TB_BOOK_DTL.class, id);
			td2.setBook_number(book_number);
			td2.setBook_name(book_name);
			td2.setBook_author(book_author);
			td2.setBook_price(Integer.parseInt(book_price));
			td2.setTotal_book_qty(Integer.parseInt(total_book_qty));
			td2.setBook_dept(Integer.parseInt(book_dept));
			sessionHQL.update(td2);
			sessionHQL.flush();
			sessionHQL.clear();
			ra.addAttribute("msg", "Data Updated Successfully.");

		} else {
			ra.addAttribute("msg", "Something Went Wrong");

		}

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:show_all_booksUrl");

	}

	@PostMapping("/edit_book_Url")
	public ModelAndView edit_book_Url(ModelMap model, @ModelAttribute("id3") int id, BindingResult result) {
		Session sessionHQL = this.sessionFactory.openSession();

		Transaction tx1 = sessionHQL.beginTransaction();

		TB_BOOK_DTL td2 = (TB_BOOK_DTL) sessionHQL.get(TB_BOOK_DTL.class, id);

		model.put("BookCMD", td2);
		model.put("systemList", getSystemList());

		return new ModelAndView("edit_Book_Details_Tiles");
	}

//================SAVE Smart Show Borrow book  details==================================//
	@PostMapping(value = "/Smart_show_borrowAction")
	public ModelAndView Smart_show_borrowAction(@Validated @ModelAttribute("BookCMD") TB_BOOK_DTL td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, String username, String book_name) {

		System.err.println("hellllllllloooooooooooo");

		DateFormat format = new SimpleDateFormat("dd-mm-yyyy");

		String member_name = request.getParameter("member_name");
		String member_address = request.getParameter("book_name");
		String member_phone_number = request.getParameter("book_date_of_barrow");
		String member_joined_date = request.getParameter("book_date_of_return");

		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		/*
		 * Long c = (Long) sessionHQL.
		 * createQuery("select count(id) from TB_BOOK_DTL where member_name=:member_name and book_name=:book_name and book_date_of_barrow=:book_date_of_barrow and book_date_of_return=:book_date_of_return and id !=:id"
		 * ) .setParameter("id", td.getId()) .setParameter("member_name",
		 * td.getMember_name()) .setParameter("book_name",td.getBook_name())
		 * .setParameter("book_date_of_barrow",td.getBook_date_of_barrow())
		 * .setParameter("book_date_of_return",td.getBook_date_of_return()).uniqueResult
		 * ();
		 */
		/*
		 * if (id == 0) { td.setId(id); td.setMember_name(member_name);
		 * td.setBook_name(book_name); td.setBook_date_of_barrow(date);
		 * td.setBook_date_of_return(date); td.setCreated_by(username);
		 * td.setCreated_date(date); td.setModified_by(username);
		 * td.setModified_date(date); if (c == 0) { sessionHQL.save(td);
		 * sessionHQL.flush(); sessionHQL.clear(); ra.addAttribute("msg",
		 * "Data Saved Successfully."); } else { ra.addAttribute("msg",
		 * "Data already Exist."); } }
		 */

		tx.commit();

		sessionHQL.close();
		return new ModelAndView("redirect:show_all_booksUrl");
	}

	public String showTotalHistory(ModelMap model) {
		model.put("total", sld.getBorrowList("1"));
		return "totalhistory";
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

}
