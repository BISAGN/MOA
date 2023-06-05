package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Hosp_Staff_Detail.HOSP_EQUIPMENTES_DETAIL;
import com.AyushEdu.Models.Hosp_Staff_Detail.HOSP_EQUIPMENTES_UP_DOCUMENT;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Hospital_Equipments_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Hospital_Equipments_Controller {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Hospital_Equipments_DAO HEDao;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	Commondao commondao;
	
	
	@RequestMapping(value = "admin/hospital_equipments", method = RequestMethod.GET)
	public ModelAndView hospital_equipments(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
         String userid = session.getAttribute("userId_for_jnlp").toString();
		
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		String username = session.getAttribute("roleloginName").toString();
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from HOSP_EQUIPMENTES_DETAIL where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		
		
		Mmap.put("institude", institude);
		
		Mmap.put("getarticaldata", HEDao.GetArticlesdata());
		Mmap.put("dataeqi", HEDao.gethospitalquipment(Integer.parseInt(institude)));
		
		return new ModelAndView("hospital_equipments");
	}
	
	
	
	
	@PostMapping(value = "/Hospital_Equipment_Action")
	 public @ResponseBody String Hospital_Equipment_Action(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "sother_equip", required = false) MultipartFile sother_equip_doc,
			@RequestParam(value = "patho_bio_equip", required = false) MultipartFile patho_bio_equip_doc,
			 HttpServletRequest request,RedirectAttributes ra) throws IOException, ParseException {
	 
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		String sother_equip = "sother_equip";
		String patho_bio_equip = "patho_bio_equip";
		String institute_id = HEDao.getInstitute_id(userid).get(0).get(0);
		String hid_upload_document = request.getParameter("hid_upload_document");
		
		if (!sother_equip_doc.isEmpty()) {
			sother_equip = upload_imagemethod(request,sother_equip_doc,session, sother_equip);
		}
		else {
			sother_equip = request.getParameter("hid_sother_equip");
		}
		
		if (!patho_bio_equip_doc.isEmpty()) {
			patho_bio_equip = upload_imagemethod(request,patho_bio_equip_doc,session, patho_bio_equip);
		}
		else {
			patho_bio_equip = request.getParameter("hid_patho_bio_equip");
		}
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();
		
		try {
			
			ArrayList<ArrayList<String>> list= HEDao.GetArticlesdata();	
			
			for(int i=1;i<=list.size();i++) {
			int h_id=Integer.parseInt(request.getParameter("hosp_art_hid"+i));
			System.out.println("h_id---------------"+h_id);
			
			String h_id2=(request.getParameter("articles"+i));
			System.out.println("h_id2---------------"+h_id2);
			
				String articles = request.getParameter("articles"+i);
				String total_equipments = request.getParameter("total_equipments"+i);
				
				System.out.println("no_of_av_staff---------------"+total_equipments);
				
				if (validation.isOnlyNumber(total_equipments) == false) {
					ra.addAttribute("msg","Total Equipments" +validation.isOnlyNumberMSG);
					return "Total Equipments " +validation.isOnlyNumberMSG;
				}
				
//				if (total_equipments == null || total_equipments.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Total Equipments"+list.get(i).get(1));
//					return "Please Enter Total Equipments"+list.get(i).get(1);
//				}
//				
//				if (sother_equip.equals("")) {
//					ra.addAttribute("msg", "Please Upload If Other Hospital/Operation Theater/Labour/IPD equipment are available");
//					return "Please Upload If Other Hospital/Operation Theater/Labour/IPD equipment are available";
//				}
//				if (patho_bio_equip.equals("")) {
//					ra.addAttribute("msg", "Please Upload list of equipments in Pathology laboratory & Biochemistry laboratory");
//					return "Please Upload list of equipments in Pathology laboratory & Biochemistry laboratory";
//				}
				if(h_id==0) 
				{
					HOSP_EQUIPMENTES_DETAIL rd =new HOSP_EQUIPMENTES_DETAIL();
					
				rd.setArticles(Integer.parseInt(articles));
				rd.setTotal_equipments(Integer.parseInt(total_equipments));
				
				System.out.println("devang----no_of_av_staff-----"+total_equipments);
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInstitute_id(Integer.parseInt(institude));
				
				rd.setCreated_by(userid);
				rd.setCreated_dt(date);
				
				h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
					sessionHQL.flush();
					sessionHQL.clear();
				}
				
				
				
				else {
					
					HOSP_EQUIPMENTES_DETAIL urd = (HOSP_EQUIPMENTES_DETAIL) sessionHQL.get(HOSP_EQUIPMENTES_DETAIL.class,
						(h_id));
			
				urd.setArticles(Integer.parseInt(articles));
				urd.setTotal_equipments(Integer.parseInt(total_equipments));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setModified_by(userid);
				urd.setModified_dt(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
		
				}
			tx1.commit();
			
			Transaction tx = sessionHQL.beginTransaction();
			
			HOSP_EQUIPMENTES_UP_DOCUMENT upload_document_detail =new HOSP_EQUIPMENTES_UP_DOCUMENT();
			
			upload_document_detail.setSother_equip(sother_equip);
			upload_document_detail.setPatho_bio_equip(patho_bio_equip);
			upload_document_detail.setInstitute_id(Integer.parseInt(institute_id));
			upload_document_detail.setCreated_by(username);
			upload_document_detail.setCreated_dt(date);

				if (Integer.parseInt(hid_upload_document) == 0) {
					int hid_upload_document1= (Integer) sessionHQL.save(upload_document_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_upload_document1) ;
			}
				else {

					HOSP_EQUIPMENTES_UP_DOCUMENT upload_document_detail_u = (HOSP_EQUIPMENTES_UP_DOCUMENT) sessionHQL
							.get(HOSP_EQUIPMENTES_UP_DOCUMENT.class, Integer.parseInt(hid_upload_document));
					
					upload_document_detail_u.setSother_equip(sother_equip);
					upload_document_detail_u.setPatho_bio_equip(patho_bio_equip);
					upload_document_detail_u.setModified_by(userid);
					upload_document_detail_u.setModified_dt(date);
					sessionHQL.update(upload_document_detail_u);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					
				}
			
//			data.put("msg", "Data Save As Draft Successfully.");

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return "Data Saved Successfully"; 
	}
	
	
//	@PostMapping(value = "/Hosp_Detl_Upload_Documnet_Action")
//	public @ResponseBody String Hosp_Detl_Upload_Documnet_Action( 
//			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//			@RequestParam(value = "attend_hospitalstaff", required = false) MultipartFile attend_hospitalstaff_doc,
//			@RequestParam(value = "acquittancestaff", required = false) MultipartFile acquittancestaff_doc,
//			@RequestParam(value = "doctor_rosters", required = false) MultipartFile doctor_rosters_doc,
//			@RequestParam(value = "nurse_rosters", required = false) MultipartFile nurse_rosters_doc,
//			MultipartHttpServletRequest mul,RedirectAttributes ra) throws ParseException, IOException {
//
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return  "redirect:/login";
//		 }
//		String role = session.getAttribute("role").toString();
//		String roleid1 = session.getAttribute("roleid").toString();
////		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
////			if(val == false) {
////				return  "AccessTiles";
////		}
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		String attend_hospitalstaff = "attend_hospitalstaff";
//		String acquittancestaff = "acquittancestaff";
//		String doctor_rosters = "doctor_rosters";
//		String nurse_rosters = "nurse_rosters";
//		String hid_upload_document = request.getParameter("hid_upload_document");
//		String institute_id = HSDao.getInstitute_id(userid).get(0).get(0);
//		
//		if (!attend_hospitalstaff_doc.isEmpty()) {
//			attend_hospitalstaff = upload_imagemethod(request,attend_hospitalstaff_doc,session, attend_hospitalstaff);
//		}
//		else {
//			attend_hospitalstaff = request.getParameter("hid_attend_hospitalstaff");
//		}
//		
//		if (!acquittancestaff_doc.isEmpty()) {
//			acquittancestaff = upload_imagemethod(request,acquittancestaff_doc,session, acquittancestaff);
//		}
//		else {
//			acquittancestaff = request.getParameter("hid_acquittancestaff");
//		}
//		
//		if (!doctor_rosters_doc.isEmpty()) {
//			doctor_rosters = upload_imagemethod(request,doctor_rosters_doc,session, doctor_rosters);
//		}
//		else {
//			doctor_rosters = request.getParameter("hid_doctor_rosters");
//		}
//		
//		if (!nurse_rosters_doc.isEmpty()) {
//			nurse_rosters = upload_imagemethod(request,nurse_rosters_doc,session, nurse_rosters);
//		}
//		else {
//			nurse_rosters = request.getParameter("hid_nurse_rosters");
//		}
//		
//		Date date = new Date();
//		String username = principal.getName();
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		
////		if (login_url == null || login_url.trim().equals("")) {
////			ra.addAttribute("msg", "Please Enter Login Url.");
////			return "Please Enter Login Url.";
////		}
////		if (username_college == null || username_college.trim().equals("")) {
////			ra.addAttribute("msg", "Please Enter Username.");
////			return "Please Enter Username.";
////		}
////		if (password == null || password.trim().equals("")) {
////			ra.addAttribute("msg", "Please Enter Password.");
////			return "Please Enter Password.";
////		}
////		if (college_working_hours == null || college_working_hours.trim().equals("")) {
////			ra.addAttribute("msg", "Please Enter College Working Hours.");
////			return "Please Enter College Working Hours.";
////		}
//
//		try {
//			HOSP_STAFF_DETAIL_UP_DOCUMENT upload_document_detail =new HOSP_STAFF_DETAIL_UP_DOCUMENT();
//			
//			upload_document_detail.setAttend_hospitalstaff(attend_hospitalstaff);
//			upload_document_detail.setAcquittancestaff(acquittancestaff);
//			upload_document_detail.setDoctor_rosters(doctor_rosters);
//			upload_document_detail.setDoctor_rosters(doctor_rosters);
//			upload_document_detail.setInstitute_id(Integer.parseInt(institute_id));
//			upload_document_detail.setCreated_by(username);
//			upload_document_detail.setCreated_dt(date);
//			
//				if (Integer.parseInt(hid_upload_document) == 0) {
//					int hid_upload_document1= (Integer) sessionHQL.save(upload_document_detail);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					tx.commit();
//					return String.valueOf(hid_upload_document1) ;
//			}
//				else {
//
//					HOSP_STAFF_DETAIL_UP_DOCUMENT upload_document_detail_u = (HOSP_STAFF_DETAIL_UP_DOCUMENT) sessionHQL
//							.get(HOSP_STAFF_DETAIL_UP_DOCUMENT.class, Integer.parseInt(hid_upload_document));
//					
//					upload_document_detail_u.setAttend_hospitalstaff(attend_hospitalstaff);
//					upload_document_detail_u.setAcquittancestaff(acquittancestaff);
//					upload_document_detail_u.setDoctor_rosters(doctor_rosters);
//					upload_document_detail_u.setDoctor_rosters(doctor_rosters);
//					upload_document_detail_u.setNurse_rosters(nurse_rosters);
//					upload_document_detail_u.setModified_by(userid);
//					upload_document_detail_u.setModified_dt(date);
//					sessionHQL.update(upload_document_detail_u);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					tx.commit();
//					
//				}
//
//		} catch (RuntimeException e) {
//			try {
//
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return  "Data Saved Successfully";
//	}
//	
	//FETCH UPLOAD DOCUMENT DETAILS
	@RequestMapping(value = "admin/getHosp_Equipment_Document_Details", method = RequestMethod.POST)
	public @ResponseBody List<HOSP_EQUIPMENTES_UP_DOCUMENT> getHosp_Equipment_Document_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = HEDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from HOSP_EQUIPMENTES_UP_DOCUMENT where institute_id=:institute_id order by id");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<HOSP_EQUIPMENTES_UP_DOCUMENT> clist = (List<HOSP_EQUIPMENTES_UP_DOCUMENT>) q.list();
	
		tx.commit();
		sessionHQL.close();
		
		return clist;
	}

	
	
 public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
		
		String extension=""; //add line
		String fname = ""; //add line
		
		request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
		
		MultipartFile file = mul;
		
		if (!file.getOriginalFilename().isEmpty()) {
			
			byte[] bytes = file.getBytes();
			String  mnhFilePath = session.getAttribute(id).toString();
			
	        File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();
					
			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j+1);
			}
			java.util.Date date1= new java.util.Date();
			fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
			
			File serverFile = new File(fname);	               
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);	                
			stream.close();

		}else {

			
		}
		return fname;
		
		}
	
	

	

}
