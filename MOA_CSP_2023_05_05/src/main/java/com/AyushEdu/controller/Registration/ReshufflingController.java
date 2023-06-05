package com.AyushEdu.controller.Registration;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.Registration.TB_REGISTRATION_DTL;
import com.AyushEdu.Models.Registration.TB_RESHUFFLING_DTL;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.personaldetailsDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ReshufflingController {

	CommonController comMstr = new CommonController();

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	personaldetailsDAO da;
	
	@Autowired
	private RoleBaseMenuDAO roledao;

	@GetMapping(value = "Reshuffling_Url")
	public ModelAndView Reshuffling_Url(ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_eid", required = false) String ch_eid, HttpServletRequest request) {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reshuffling_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		System.err.println("/*/*/*/*/*/*/*/*-"+ch_eid);
		String username = principal.getName();
		model.addAttribute("userid", da.getUsername(username));

		//model.put("getclgNameDataList", comMstr.getclgNameDataList(sessionFactory));
		model.put("getclgNameDataList", comMstr.getapp_instituteNameList(sessionFactory));
		
		
		model.addAttribute("msg", msg);

		if (ch_eid == "0" || ch_eid.equals("")) {
			return new ModelAndView("redirect:Personal_Details_Url");
		} else {
			model.addAttribute("ch_eid", ch_eid);
			return new ModelAndView("Reshuff_Tile");
		}
	}

	@PostMapping(value = "/Reshuff_Action")
	public ModelAndView Reshuff_Action(@Validated @ModelAttribute("Reshuff_cmd") TB_RESHUFFLING_DTL rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Reshuffling_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String ch_eid = request.getParameter("ch_eid");
		System.err.println(ch_eid);
		int id = rd.getId() > 0 ? rd.getId() : 0;

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		// DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		String add_collage_name = request.getParameter("add_collage_name");
		String remove_collage_name = request.getParameter("remove_collage_name");
		String old_collage_name = request.getParameter("old_collage_name");
		String new_collage_name = request.getParameter("new_collage_name");

		System.err.println("add--" + add_collage_name + "-remove-" + remove_collage_name + "-old-" + old_collage_name
				+ "-new-" + new_collage_name);

		String value = request.getParameter("value");
		System.err.println("value------->  " + value);
		
		String p_id = request.getParameter("p_id");

		try {
//				if (old_collage_name != null && !old_collage_name.equals("")) {
//					List<String> oldclglist = new ArrayList<String>();
//					oldclglist = Arrays.asList(old_collage_name.split(","));
//					for (int i = 0; i < oldclglist.size(); i++) {
//					String hqlUpdate = "update TB_RESHUFFLING_DTL set value=:value where p_id=:p_id";
//						int app = sessionHQL.createQuery(hqlUpdate)
//								.setParameter("p_id", Integer.parseInt(p_id))
//								.setParameter("value",oldclglist.get(i)).executeUpdate();
//						sessionHQL.flush();
//						sessionHQL.clear();
//					}
//				}

			List<String> newList = new ArrayList<String>();
			System.out.println("symp list==" + Arrays.asList(add_collage_name.split(",")));
			if (new_collage_name != null && !new_collage_name.equals("")) {
				newList = Arrays.asList(new_collage_name.split(","));
			}

			List<String> addList = new ArrayList<String>();
			List<String> removeList = new ArrayList<String>();
			System.out.println("symp list==" + Arrays.asList(add_collage_name.split(",")));
			if (add_collage_name != null && !add_collage_name.equals("")) {
				addList = Arrays.asList(add_collage_name.split(","));
			}
			if (remove_collage_name != null && !remove_collage_name.equals("")) {
				removeList = Arrays.asList(remove_collage_name.split(","));
			}

			if (removeList.size() > 0) {
				for (int i = 0; i < removeList.size(); i++) {
					String hqlUpdate = "delete from TB_RESHUFFLING_DTL where p_id=:p_id and value=:value";
					int app = sessionHQL.createQuery(hqlUpdate).setParameter("p_id", Integer.parseInt(p_id))
							.setParameter("value", removeList.get(i)).executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}

			if (addList.size() > 0) {
				for (int i = 0; i < addList.size(); i++) {
					TB_RESHUFFLING_DTL obj = new TB_RESHUFFLING_DTL();
					obj.setCreated_by(username);
					obj.setCreated_date(date);
					obj.setP_id(Integer.parseInt(p_id));
					obj.setValue(addList.get(i));
					obj.setDraft_status("0");
					int s_id2 = (int) sessionHQL.save(obj);
					model.put("s_id", s_id2);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			tx.commit();

//			 old_collage_name = new_collage_name;
//					List<String> newclglist = new ArrayList<String>();
//					newclglist = Arrays.asList(new_collage_name.split(","));
//					System.err.println("old_collage_name  final--->  "+newclglist);

			ra.addAttribute("ch_eid", ch_eid);
			ra.addAttribute("msg", "Data Saved Successfully.");
			// ra.addAttribute("old_clg", newclglist);

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
		return new ModelAndView("redirect:Reshuffling_Url");
	}

	@RequestMapping(value = "/getSelectList", method = RequestMethod.POST)
	public @ResponseBody List<String> getSelectList(int p_id, HttpSession session) {

		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL.createQuery("from TB_RESHUFFLING_DTL where p_id=:p_id and value != '' ")
				.setParameter("p_id", p_id);

		List<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<TB_RESHUFFLING_DTL> clist = (List<TB_RESHUFFLING_DTL>) q.list();
		tx.commit();
		sessionHQL.close();
		
		System.err.println("size()----->   "+  clist.size());
		
		
		
		//System.err.println("clist.get(0).getDraft_status()"+clist.get(0).getDraft_status());
		if(clist.size() > 0) {
		list.add(clist.get(0).getDraft_status());
		}
		
		for (int i = 0; i < clist.size(); i++) {
			list.add(clist.get(i).getValue());
			
		}

		System.err.println("list--------->  "+list);

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getResultofclg_selected", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getResultofclg_selected(int p_id,HttpSession session) {
		
		
		ArrayList<ArrayList<String>> list = da.getInstID(p_id);
		
		String instId = list.get(0).get(0);		
		
		Session sessionhq = this.sessionFactory.openSession();
		Transaction tx = sessionhq.beginTransaction();
		
		TB_REGISTRATION_DTL TRD = (TB_REGISTRATION_DTL) sessionhq.get(TB_REGISTRATION_DTL.class, p_id);
		
		tx.commit();
		sessionhq.close();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction txHQL = sessionHQL.beginTransaction();
		
		Long c = (Long) sessionHQL.createQuery(
				"select count(id) from  EDU_LMS_STUDENT_DETAILS where upper(email)=:email and institude_id=:institude_id")
				.setParameter("email",TRD.getRecr_email().toUpperCase())
				.setParameter("institude_id", instId)
				.uniqueResult();
		
		EDU_LMS_STUDENT_DETAILS elsd = new EDU_LMS_STUDENT_DETAILS();
		
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		
		String maxAID = da.getMaxAID();
		int newn = Integer.parseInt(maxAID);
		newn++;
		String abc = String.format("%6s", newn).replace(' ', '0');
		abc = "AU"+abc;
			
		if (c == 0) {
			elsd.setName(TRD.getRecr_name());
//			elsd.setDob(TRD.getRecr_dob().toString());
			elsd.setAadhar_card(TRD.getRecr_aadhar_no());
			elsd.setEmail(TRD.getRecr_email());
			elsd.setMobile_no(TRD.getRecr_mobileno());
			//elsd.setInstitude_id(instId);
			elsd.setCreated_by(username);
			elsd.setCreated_date(date);
			elsd.setAyush_id(abc);

			sessionHQL.save(elsd);
			sessionHQL.flush();
			sessionHQL.clear();
		}
		txHQL.commit();
		return da.getInstID(p_id);
		
	}
	
	 @SuppressWarnings("unchecked")
		@RequestMapping(value = "/finalsubmitclglist", method = RequestMethod.POST)
		 	public @ResponseBody String finalsubmitclglist(int p_id,HttpSession session) {
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = "update TB_RESHUFFLING_DTL set draft_status=:draft_status where p_id=:p_id";
				int app = sessionHQL.createQuery(hqlUpdate)
						.setParameter("p_id", p_id)
						.setParameter("draft_status","1").executeUpdate();

				System.err.println(app);
				tx.commit();
				sessionHQL.close();
				return null;
		 	}
}
