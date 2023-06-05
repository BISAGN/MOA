package com.AyushEdu.controller.Policy_controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.AyushEdu.dao.Policy_dao.Policy_DAO;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.Policy.TB_POLICY_DOCUMENT;
import com.AyushEdu.Models.Policy.TB_POLICY_INITIAL_DRAFT;
import com.AyushEdu.Models.Policy_Model_Master.TB_SUBPOLICYCATEGORY;


@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Policy_controller {

	@Autowired
	private SessionFactory sessionFactory;

	CommonController common = new CommonController();

	
	@Autowired
	Policy_DAO pdao;

	
	//==========================================OPEN PAGE POLICY CREATION========================================== 
	
	@RequestMapping(value = "/Policy_CreationUrl", method = RequestMethod.GET)
	public ModelAndView Policy_CreationUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("msg", msg);
		Mmap.put("policycat", common.getPolicyCategoryList(sessionFactory));
		Mmap.put("subpolicycat", common.getSubPolicyCategoryList(sessionFactory));
		
		String forward_draft = "";
			forward_draft = " TO EXECUTIVE COMMITTEE";
			Mmap.put("forwarded", forward_draft);
			
		return new ModelAndView("Policy_Creation_Tiles");

	}

	//==========================================SAVE POLICY CREATION========================================== 	
	@PostMapping(value = "/policy_creation_Action")
	public ModelAndView policy_creation_Action(
			@Validated @ModelAttribute("policy_creationCMD") TB_POLICY_INITIAL_DRAFT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul) throws IOException, ParseException {

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		String role = session.getAttribute("role").toString();

		try {
			

			String policy_type = request.getParameter("policy_type");
			String policy_unique_id = request.getParameter("policy_unique_id");
			String draft_file = gd(request, mul, session, "policy_draft_file");
			String policy_category = request.getParameter("policy_category");
			String policy_sub_category = request.getParameter("policy_sub_category");
			String purpose = request.getParameter("purpose");
			String scope = request.getParameter("scope");
			String policy_title = request.getParameter("policy_title");
			String policy_no = request.getParameter("policy_no");
			
			

			if (policy_category.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Category.");
				return new ModelAndView("redirect:Policy_CreationUrl");	
			}
			if (policy_sub_category.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Policy SubCategory");
				return new ModelAndView("redirect:Policy_CreationUrl");

			}

			if (purpose.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Policy Purpose");
				return new ModelAndView("redirect:Policy_CreationUrl");
			}

			if (scope.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Policy Scope");
				return new ModelAndView("redirect:Policy_CreationUrl");
			}
			if (policy_title.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Policy Title");
				return new ModelAndView("redirect:Policy_CreationUrl");
			}
			
			if (request.getParameter("initial_date").equals("")) {
				ra.addAttribute("msg", "Please Enter Initial Date");
				return new ModelAndView("redirect:Policy_CreationUrl");
			
			}

			Date ini_dt = null;
			if (!request.getParameter("initial_date").equals("")) {
				String l[] = request.getParameter("initial_date").split("/");
				String initial_date = l[2]+"-"+l[1]+"-"+l[0];
				ini_dt = format.parse(initial_date);
			} else {
				ini_dt = null;
			}
			
			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_POLICY_INITIAL_DRAFT where upper(policy_type)=:policy_type and upper(policy_unique_id)=:policy_unique_id "
							+ "and policy_category=:policy_category and policy_sub_category=:policy_sub_category and upper(purpose)=:purpose and upper(scope)=:scope "
							+ "and upper(policy_title)=:policy_title and upper(policy_no)=:policy_no and initial_date=:ini_dt ");
			q0.setParameter("policy_type", policy_type.toUpperCase());
			q0.setParameter("policy_unique_id", policy_unique_id.toUpperCase());
			q0.setParameter("policy_category", (Integer.parseInt(policy_category)));
			q0.setParameter("policy_sub_category", (Integer.parseInt(policy_sub_category)));
			q0.setParameter("purpose", purpose.toUpperCase());
			q0.setParameter("scope", scope.toUpperCase());
			q0.setParameter("policy_title", policy_title.toUpperCase());
			q0.setParameter("policy_no", policy_no.toUpperCase());
			q0.setParameter("ini_dt", ini_dt);

			Long c = (Long) q0.uniqueResult();
			Query q2 = sessionHQL.createQuery(
					"select count(id) from TB_POLICY_INITIAL_DRAFT where upper(policy_no)=:policy_no ");
			
			q2.setParameter("policy_no", policy_no.toUpperCase());
			
			Long c2 = (Long) q2.uniqueResult();
			
			
			if (id == 0 || c2 == 1) { 
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {

			
						td.setPolicy_type(policy_type);
						td.setPolicy_unique_id(policy_unique_id);
						td.setPolicy_category(Integer.parseInt(policy_category));
						td.setPolicy_sub_category(Integer.parseInt(policy_sub_category));

					td.setPurpose(purpose);
					td.setScope(scope);
					td.setPolicy_title(policy_title);
					td.setPolicy_no(policy_no);
					td.setInitial_date(ini_dt);
					td.setLevel_one_status("0");
					td.setLevel_two_status("-1");
					td.setLevel_three_status("-1");
					td.setLevel_four_status("-1");
					td.setForward_status(role);
					td.setFinal_status(1);
					String forward_draft = request.getParameter("forward_draft"); 
					if(forward_draft != null ) {
						if (td.getLevel_two_status().equals("-1")) {
							td.setLevel_one_status("1");
							td.setLevel_two_status("0");
							td.setLevel_three_status(td.getLevel_three_status());
							td.setLevel_four_status(td.getLevel_four_status());
							td.setForward_status(role);
						} else {
							td.setLevel_one_status("1");
							td.setLevel_two_status(td.getLevel_two_status());
							td.setLevel_three_status(td.getLevel_three_status());
							td.setLevel_four_status("0");
							td.setForward_status(role);
						}
					}

					int p_id = (int) sessionHQL.save(td);
		
					sessionHQL.flush();
					sessionHQL.clear();

					TB_POLICY_DOCUMENT add = new TB_POLICY_DOCUMENT();

					add.setPolicy_draft_file(draft_file);
					add.setVersion("1");
					add.setP_id(p_id);
					add.setCreated_by(username);
					add.setCreated_date(date);

					sessionHQL.save(add);
					sessionHQL.flush();
					sessionHQL.clear();
					
					if(p_id > 0) {
						TB_NOTIFICATION nt2 = new TB_NOTIFICATION();
						
						nt2.setFrom_name_send(role);
						if (td.getLevel_two_status().equals("0")) {
							nt2.setTo_name_sent("level 2");
						}else {
							nt2.setTo_name_sent("level 4");
						}
						
						nt2.setMessage(username   +  " " + "Forward" + "<b> "+" " +policy_title+" </b>" + " On "+"   "+"   ");
						nt2.setUrl_id("viewForm");
						nt2.setUrl_value("viewid:" + p_id);
						nt2.setCreated_date(date);
						sessionHQL.save(nt2);
						
					if(forward_draft != null ) {
						ra.addAttribute("msg"," Policy Ayush ID : "+policy_no+" " +"  "+" Initiated And Forwarded Successfully  " );
					}else {
						ra.addAttribute("msg","Policy Ayush Id : "+policy_no+"  Initiated Successfully" );
						ra.addAttribute("policy_no",policy_no );
					}
				}
					

				} else {

					ra.addAttribute("msg", "Data already Exist.");

				}
				tx.commit();
			}else {
				ra.addAttribute("msg", "Data already Exist.");
				
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return new ModelAndView("redirect:PolicysearchUrl");
	}

	//==========================================PDF VIEW FOR POLICY========================================== 	
	
	public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile(id);
		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute(id).toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {
		}
		return fname;

	}

	
	//==========================================OPEN PAGE POLICY SEARCH========================================== 	
	
	@RequestMapping(value = "/PolicysearchUrl", method = RequestMethod.GET)
	public ModelAndView Fetch_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,	@RequestParam(value = "policy_no", required = false) String policy_no, HttpServletRequest request) {
		String role = session.getAttribute("role").toString();
		Mmap.put("policy_no",policy_no );
		Mmap.put("role", role);
		Mmap.put("s_msg", msg);
		Mmap.put("PoicyList",common.getPolicy(sessionFactory) );
		Mmap.put("DraftStatusList",common.getDraftStatusList(role,sessionFactory ) );
		Mmap.put("policytypeList",common.getpolicytypeList(sessionFactory));

		if(role.equals("level 1")){
			Mmap.put("s_status", "0");
			}else if(role.equals("level 2")) { 
				Mmap.put("s_status", "7");
			}else if(role.equals("level 3")) { 
				Mmap.put("s_status", "10");
			}else if(role.equals("level 4")) { 
				Mmap.put("s_status", "12");
			}

		
		return new ModelAndView("Policy_Search_Tiles");

	}
	
	//==========================================STATUS POLICY SEARCH========================================== 	
	@RequestMapping(value = "/policy_search_url", method = RequestMethod.POST)
	public ModelAndView policy_search_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "s_status", required = false) String s_status,
			@RequestParam(value = "s_policy", required = false) String s_policy,
			@RequestParam(value = "s_ini_date", required = false) String s_ini_date,
			@RequestParam(value = "s_policyno", required = false) String s_policyno,
			@RequestParam(value = "s_policytype", required = false) String s_policytype,HttpServletRequest request) {
		String role = session.getAttribute("role").toString();
		Mmap.put("PoicyList",common.getPolicy(sessionFactory) );
		Mmap.put("role", role);
		Mmap.put("s_status", s_status);
		Mmap.put("s_policy", s_policy);
		Mmap.put("s_ini_date", s_ini_date);
		Mmap.put("s_policyno", s_policyno);
		Mmap.put("s_policytype", s_policytype);
		Mmap.put("DraftStatusList",common.getDraftStatusList(role,sessionFactory ) );
		Mmap.put("policytypeList",common.getpolicytypeList(sessionFactory));

		return new ModelAndView("Policy_Search_Tiles");

	}

	//==========================================DATA TABLE POLICY SEARCH========================================== 
	

	@RequestMapping(value = "/getPolicy_data", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getPolicy_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String policy, String initial_date, String policy_number,
			String status,String policy_type,String globSearch, HttpSession sessionUserId, Principal principal) {
		return pdao.DataTablePolicyDataList(startPage, pageLength, Search, orderColunm, orderType, policy, initial_date,
				policy_number, status,policy_type,globSearch, sessionUserId);

	}

	@RequestMapping(value = "/getPolicy_dataCount", method = RequestMethod.POST)
	public @ResponseBody long getPolicy_dataCount(HttpSession sessionUserId, String Search, String policy,
			String initial_date, String policy_number, String status,String policy_type,String globSearch) {
		return pdao.DataTablePolicyDataTotalCount(Search, policy, initial_date, policy_number, status,policy_type, globSearch,sessionUserId);
	}


	//==========================================OPEN PAGE POLICY VIEW========================================== 
	
	@RequestMapping(value = "/Policy_ViewUrl", method = RequestMethod.POST)
	public ModelAndView Policy_ViewUrl(@ModelAttribute("viewid") String viewid, TB_POLICY_INITIAL_DRAFT td,
			BindingResult result, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "status1", required = false) String status, HttpServletRequest request) {

		Mmap.put("policyinfo", pdao.getpolicyinformation(Integer.parseInt(viewid)));
		Mmap.put("vid", viewid);
		Mmap.put("status", status);
		Mmap.put("policycat", common.getPolicyCategoryList(sessionFactory));
		Mmap.put("subpolicycat", common.getSubPolicyCategoryList(sessionFactory));
		Mmap.put("policyremark", pdao.getPolicyremarkList(Integer.parseInt(viewid)));

		return new ModelAndView("Policy_View_Tiles");

	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/kmlFileDownload44")
	public void kmlFileDownload44(@ModelAttribute("kmlId2") String id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;


		String filePath = pdao.getFilePathQueryForDoc(id);

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {

				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			@SuppressWarnings("deprecation")
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}

	}
	
	//========================================== POLICY APPROVE ========================================== 
	
	@RequestMapping(value = "Approve_policy", method = RequestMethod.POST)
	public ModelAndView Approve_policy(ModelMap model, HttpSession session, HttpServletRequest request,
			TB_POLICY_INITIAL_DRAFT obj, @RequestParam(value = "id7", required = false) String id, String category) {

		String msg = "";
		String username = session.getAttribute("username").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String tb_namep = "";

//				tb_namep = "TB_POLICY_INITIAL_DRAFT";

		String role = session.getAttribute("role").toString();

		TB_POLICY_INITIAL_DRAFT EE2 = (TB_POLICY_INITIAL_DRAFT) sessionHQL.get(TB_POLICY_INITIAL_DRAFT.class,
				Integer.parseInt(id));
		 Date date = new Date();
		try {
			
			TB_NOTIFICATION nt2 = new TB_NOTIFICATION();
			int app = 0;
			if (role.equals("level 1")) {
				

				if (EE2.getLevel_two_status().equals("-1")) {
					tb_namep = "level 2";
					String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set level_one_status=:level_one_status,level_two_status=:level_two_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status  where id=:id";
					app = sessionHQL.createQuery(hqlUpdatep).setString("level_one_status", "1")
							.setString("level_two_status", "0").setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("final_status", 1).setString("forward_status", role)
							.setInteger("id", Integer.parseInt(id)).executeUpdate();

				} else if (EE2.getLevel_two_status().equals("1") || EE2.getLevel_two_status().equals("2")) {
					tb_namep = "level 4";
					String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set level_one_status=:level_one_status,level_four_status=:level_four_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status  where id=:id";
					app = sessionHQL.createQuery(hqlUpdatep).setString("level_one_status", "1")
							.setString("level_four_status", "0").setString("modified_by", username)
							.setDate("modified_date", new Date()).setInteger("final_status", 1).setString("forward_status", role)
							.setInteger("id", Integer.parseInt(id)).executeUpdate();

				}

			}
			if (role.equals("level 2")) {
				tb_namep = "level 3";
				String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set level_two_status=:level_two_status,level_three_status=:level_three_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status  where id=:id";
				app = sessionHQL.createQuery(hqlUpdatep).setString("level_two_status", "1")
						.setString("level_three_status", "0").setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("final_status", 1).setString("forward_status", role)
						.setInteger("id", Integer.parseInt(id)).executeUpdate();

			}
			if (role.equals("level 3")) {
				tb_namep = "level 1";
				String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set level_three_status=:level_three_status,level_one_status=:level_one_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status  where id=:id";
				app = sessionHQL.createQuery(hqlUpdatep).setString("level_three_status", "1")
						.setString("level_one_status", "0").setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("final_status", 1).setString("forward_status", role)
						.setInteger("id", Integer.parseInt(id)).executeUpdate();

			}
			if (role.equals("level 4")) {
				tb_namep = "level 1";
				String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set level_four_status=:level_four_status,level_one_status=:level_one_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status  where id=:id";
				app = sessionHQL.createQuery(hqlUpdatep).setString("level_four_status", "1")
						.setString("level_one_status", "1").setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("final_status", 2).setString("forward_status", role)
						.setInteger("id", Integer.parseInt(id)).executeUpdate();

			}


			nt2.setFrom_name_send(role);
			nt2.setTo_name_sent(tb_namep);
			
			if (app > 0 && role.equals("level 1")) {
			nt2.setMessage(username   +  " " + "Forward" + "<b> "+" " +EE2.getPolicy_title()+" </b>" + " On "+"   "+"   ");
			}else if(app > 0 && role.equals("level 2") || role.equals("level 3") || role.equals("level 4")){
				nt2.setMessage(username   +  " " + "Approved" + "<b> "+" " +EE2.getPolicy_title()+" </b>" + " On "+"   "+"   ");
			}
			
			nt2.setUrl_id("viewForm");
			nt2.setUrl_value("viewid:" + id);
			nt2.setCreated_date(date);
			sessionHQL.save(nt2);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			sessionHQL.close();
			if (app > 0 && role.equals("level 1")) {
				
				msg = "Data Forward Successfully.";
			} else if(app > 0 && role.equals("level 2") || role.equals("level 3") || role.equals("level 4")){
				
				msg = "Data Approve Successfully.";
			}else {
				msg="Unable to forward data";
			}

		} catch (Exception e) {
			msg = "Unable To Approve.";
			tx.rollback();
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:PolicysearchUrl");

	}

//	//////////////////  DELETE MARITAL STATUS   ///////////////////////////////

	@PostMapping(value = "/delete_Url")
	public ModelAndView delete_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		TB_POLICY_INITIAL_DRAFT INF = (TB_POLICY_INITIAL_DRAFT) session.get(TB_POLICY_INITIAL_DRAFT.class, id);

		List<String> liststr = new ArrayList<String>();

		try {

			session.delete(INF);
			tx.commit();
			session.close();
			liststr.add("Data Deleted Successfully.");
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:PolicysearchUrl");
	}

	
	//================================OPEN PAGE FOR EDIT POLICY DRAFT================================
	
	@RequestMapping(value = "/edit_Policy_Url", method = RequestMethod.POST)
	public ModelAndView edit_Policy_Url(@ModelAttribute("editid") String editid, ModelMap model, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionEdit) {

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_POLICY_INITIAL_DRAFT EE2 = (TB_POLICY_INITIAL_DRAFT) sessionHQL.get(TB_POLICY_INITIAL_DRAFT.class,
				Integer.parseInt(editid));

		Integer child_id = pdao.getchild_id_by_p_id(Integer.parseInt(editid));
		TB_POLICY_DOCUMENT TPD = (TB_POLICY_DOCUMENT) sessionHQL.get(TB_POLICY_DOCUMENT.class, child_id);
		String policy_draft_file = TPD.getPolicy_draft_file();
		
			String forword = "";
					
			if (EE2.getLevel_two_status().equals("-1")) {
				forword = " TO EXECUTIVE COMMITTEE";
			} else {
				forword = " TO MINISTRY OF AYUSH";
			}
		EE2.setPurpose(EE2.getPurpose().replace("\r\n", " ").replace("\n", " ").replace(";", " ").replace("'", " ").replace(":", "").replace("-", ""));
		EE2.setScope(EE2.getScope().replace("\r\n", " ").replace("\n", " ").replace(";", " ").replace("'", " ").replace(":", "").replace("-", ""));
		
		
		
		model.put("editcmd", EE2);
		model.put("hidden_draft", policy_draft_file);
		model.put("id_org", editid);
		model.put("policycat", common.getPolicyCategoryList(sessionFactory));
		model.put("subpolicycat", common.getSubPolicyCategoryList(sessionFactory));
		model.put("forword", forword);
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Edit_Policy_Tiles");
	}

	//================================EDIT POLICY DRAFT================================

	@RequestMapping(value = "/edit_policy_Action", method = RequestMethod.POST)
	 public @ResponseBody String edit_policy_Action(@Validated @ModelAttribute("editid") TB_POLICY_INITIAL_DRAFT td2,
				BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException{

		String purpose_i = request.getParameter("purpose");
		String scope_i = request.getParameter("scope");
		String policy_title_i = request.getParameter("policy_title");
		String initial_date_i = request.getParameter("initial_date");
		System.err.println("sdfsdfsdfasd"+purpose_i);
		
		
		
		if (purpose_i.trim().equals("")) {
			return "purpose";
			}
		
		if (scope_i.trim().equals("")) {
				return "scope";
			}
		
		if (policy_title_i.trim().equals("")) {
				return "policy_title";
			}
		
		if (initial_date_i.trim().equals("")) {
				return "initial_date";
			}
		
		String role = session.getAttribute("role").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
		String username = principal.getName();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String editid = request.getParameter("id_org");
		Integer child_id = pdao.getchild_id_by_p_id(Integer.parseInt(editid));

		TB_POLICY_DOCUMENT TPD = (TB_POLICY_DOCUMENT) sessionHQL.get(TB_POLICY_DOCUMENT.class, child_id);
		String policy_draft_file = TPD.getPolicy_draft_file();

//		-------file
		String extension = ""; // add line
		String fname = ""; // add line
		String draft_file = "";
		String version = "";
		String status_l2 = "";
		String status_l4 = "";
		String forward_draft = "";

		request.getSession().setAttribute("policy_draft_file", "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile("policy_draft_file");
		
		//SECURITY-----
		if (file.getOriginalFilename().isEmpty()) {
			return "Please Upload File";
		}
		if (!file.getOriginalFilename().isEmpty()) {
			String upload_fileEXt = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
			if (!upload_fileEXt.equals("pdf")) {
				return "Only .pdf file extensions allowed";
			}
			long filesize = file.getSize() / 1024;
			if (filesize > 200) {
				return "File size should be 200 kb or less";
			}
		}
		//SECURITY-----

		if (!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			String mnhFilePath = session.getAttribute("policy_draft_file").toString();

			File dir = new File(mnhFilePath);
			if (!dir.exists())
				dir.mkdirs();
			String filename = file.getOriginalFilename();

			int j = filename.lastIndexOf('.');
			if (j >= 0) {
				extension = filename.substring(j + 1);
			}
			java.util.Date date1 = new java.util.Date();
			fname = dir.getAbsolutePath()
					+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;

			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			draft_file = fname;
		} else {

			draft_file = policy_draft_file;

		}

		

			Date date = new Date();

			TB_POLICY_INITIAL_DRAFT EE2 = (TB_POLICY_INITIAL_DRAFT) sessionHQL.get(TB_POLICY_INITIAL_DRAFT.class,
					Integer.parseInt(editid));

			model.put("editcmd", EE2);

			String policy_type = td2.getPolicy_type();
			String policy_unique_id = td2.getPolicy_unique_id();
			// String draft_file = gd(request,, sessionEdit,"policy_draft_file");
			// System.err.println("draft_file---"+draft_file);
			int policy_category = td2.getPolicy_category();
			int policy_sub_category = td2.getPolicy_sub_category();
			String purpose = td2.getPurpose();
			String scope = td2.getScope();
			String policy_title = td2.getPolicy_title();
			String policy_no = td2.getPolicy_no();

			Date ini_dt = null;
			if (!request.getParameter("initial_date").equals("")) {
				String l[] = request.getParameter("initial_date").split("/");
				String initial_date = l[2] + "-" + l[1] + "-" + l[0];
				ini_dt = format.parse(initial_date);
			} else {
				ini_dt = EE2.getInitial_date();
			}

			///// code to save new entry

			TB_POLICY_INITIAL_DRAFT pid_new = new TB_POLICY_INITIAL_DRAFT();

			pid_new.setCreated_by(username);
			pid_new.setCreated_date(new Date());
			pid_new.setInitial_date(EE2.getInitial_date());
			pid_new.setPolicy_category(policy_category);
			pid_new.setPolicy_sub_category(policy_sub_category);
			pid_new.setPolicy_unique_id(EE2.getPolicy_unique_id());
			pid_new.setPurpose(purpose);
			pid_new.setScope(scope);
			pid_new.setPolicy_title(policy_title);
			pid_new.setPolicy_no(policy_no);
			pid_new.setPolicy_type(policy_type);
			pid_new.setForward_status(role);
			pid_new.setLevel_one_status(EE2.getLevel_one_status());
			pid_new.setFinal_status(1);

///////////////////////////////

			if (EE2.getLevel_two_status().equals("2") && EE2.getForward_status().equals("level 2")) {

				status_l2 = "2";
				status_l4 = EE2.getLevel_four_status();
				
				pid_new.setLevel_two_status(status_l2);
				pid_new.setLevel_three_status(EE2.getLevel_three_status());
				pid_new.setLevel_four_status(EE2.getLevel_four_status());

				version = String.valueOf(Integer.parseInt(TPD.getVersion()) + 1);

			}
			if (EE2.getLevel_four_status().equals("2") && EE2.getForward_status().equals("level 4")) {

				status_l4 = "2";
				status_l2 = EE2.getLevel_two_status();
				
				pid_new.setLevel_two_status(EE2.getLevel_two_status());
				pid_new.setLevel_three_status(EE2.getLevel_three_status());
				pid_new.setLevel_four_status(status_l4);

				version = String.valueOf(Integer.parseInt(TPD.getVersion()) + 1);

			}
			//////////// new entry end

			if (EE2.getLevel_one_status().equals("-1") || EE2.getLevel_one_status().equals("0")) {

				if (policy_type.equals("new")) {
					EE2.setPolicy_type(policy_type);
					EE2.setPolicy_unique_id(policy_unique_id);
					EE2.setPolicy_category(policy_category);
					EE2.setPolicy_sub_category(policy_sub_category);
				} else {
					EE2.setPolicy_type(policy_type);
				}
				EE2.setModified_by(username);
				EE2.setModified_date(date);
				EE2.setPurpose(purpose);
				EE2.setScope(scope);
				EE2.setForward_status(role);

				EE2.setPolicy_title(request.getParameter("policy_title"));

				EE2.setPolicy_no(policy_no);
				EE2.setInitial_date(ini_dt);

				forward_draft = request.getParameter("forward_draft");
				if (forward_draft != null) {
					if (EE2.getLevel_two_status().equals("-1")) {
						EE2.setLevel_one_status("1");
						EE2.setLevel_two_status("0");
						EE2.setLevel_three_status(EE2.getLevel_three_status());
						EE2.setLevel_four_status(EE2.getLevel_four_status());

					} else {
						EE2.setLevel_one_status("1");
						EE2.setLevel_two_status(EE2.getLevel_two_status());
						EE2.setLevel_three_status(EE2.getLevel_three_status());
						EE2.setLevel_four_status("0");

						pid_new.setLevel_one_status("1");
						pid_new.setLevel_two_status(EE2.getLevel_two_status());
						pid_new.setLevel_three_status(EE2.getLevel_three_status());
						pid_new.setLevel_four_status("0");

					}
				}
			}
			/////////////////////////////////////////////////////////

			int pd = 0;
			if (EE2.getFinal_status() == 3) {


				int did = (Integer) saveFunction(pid_new);

				TB_POLICY_DOCUMENT doc_pid = new TB_POLICY_DOCUMENT();

				doc_pid.setVersion(version);
				doc_pid.setP_id(did);
				doc_pid.setCreated_by(username);
				doc_pid.setCreated_date(new Date());
				doc_pid.setPolicy_draft_file(draft_file);
				
				pd = (Integer) saveFunction(doc_pid);

				if(did>0) {
					Session sessionup = this.sessionFactory.openSession();
					TB_POLICY_INITIAL_DRAFT EE3 = (TB_POLICY_INITIAL_DRAFT) sessionup.get(TB_POLICY_INITIAL_DRAFT.class, Integer.parseInt(editid));
					EE3.setFinal_status(4);
					sessionup.beginTransaction();
					sessionup.update(EE3);
					sessionup.getTransaction().commit();
					sessionup.close();
					}

				
			}

			int app3 = 0;
			if (EE2.getFinal_status() != 3) {

				String hql3 = "update TB_POLICY_DOCUMENT set policy_draft_file=:policy_draft_file, \n"
						+ "modified_by=:modified_by,modified_date=:modified_date where p_id=:p_id";
				app3 = sessionHQL.createQuery(hql3).setString("policy_draft_file", draft_file)
						.setString("modified_by", username).setTimestamp("modified_date", date)
						.setInteger("p_id", Integer.parseInt(editid)).executeUpdate();

				sessionHQL.update(EE2);

				sessionHQL.flush();
				sessionHQL.clear();

				tx.commit();

			}

			if (app3 > 0 || pd > 0) {

				if (forward_draft != null) {

					ra.addAttribute("msg", "Data Forwarded Successfully");

				} else {

					ra.addAttribute("msg", "Data Updated Successfully");
				}
			}

			TB_NOTIFICATION nt = new TB_NOTIFICATION();

			nt.setFrom_name_send(role);
			if (EE2.getLevel_two_status().equals("0")) {
				nt.setTo_name_sent("level 2");
			} else {
				nt.setTo_name_sent("level 4");
			}
			Session sessionHQL2 = this.sessionFactory.openSession();
			Transaction tx2 = sessionHQL.beginTransaction();
//					nt.setMessage(role + " Updated Draft please take approriate action on it");
		//	nt.setMessage("You Have Updated <b>" + " " + EE2.getPolicy_title() + " </b>" + " From " + username + " On ");
			nt.setMessage(  username + " " + "Forward" + "<b>" + " " + EE2.getPolicy_title() + " </b>"    + " On "+"  "+"   ");
			nt.setUrl_id("viewForm");
			nt.setUrl_value("viewid:" + editid);
			nt.setCreated_date(date);
			sessionHQL2.save(nt);
			sessionHQL2.flush();
			sessionHQL2.clear();

			tx2.commit();

		} catch (Exception e) {

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return  "Update Successfully";
	}
	
			public int saveFunction(Object obj) {
				Session session1 = this.sessionFactory.openSession();
				session1.beginTransaction();
				int id = (Integer) session1.save(obj);
				session1.getTransaction().commit();
				session1.close();
				return id;
			}
	
			//===================================REJECT POLICY DRAFT===================================

		@RequestMapping(value = "getRejectPolicyMethod", method = RequestMethod.POST)
			public @ResponseBody String RejectPolicyMethod(ModelMap model, HttpSession session, HttpServletRequest request,
					@RequestParam(value = "reject_id", required = false) String id,
					@RequestParam(value = "remark", required = false) String remark) {

				String msg_out = "";
				String role = session.getAttribute("role").toString();
				
				Date date = new Date();
				String status_l1 = "";
				String status_l2 = "";
				String status_l3 = "";
				String status_l4 = "";

				String username = session.getAttribute("username").toString();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {

					TB_POLICY_INITIAL_DRAFT pid_old = (TB_POLICY_INITIAL_DRAFT) sessionHQL.get(TB_POLICY_INITIAL_DRAFT.class,
							Integer.parseInt(id));
					
					TB_NOTIFICATION nt2 = new TB_NOTIFICATION();


		if (role.equals("level 2")) {
			status_l1 = "0";
			status_l2 = "2";
		
			status_l4 = pid_old.getLevel_four_status();
		}


		if (role.equals("level 4")) {
			
			status_l1 = "0";
			status_l2 = pid_old.getLevel_two_status();
			status_l4 = "2";
			
		}

	
		int app = 0;
//		
//			String hqlUpdatep = "update TB_POLICY_INITIAL_DRAFT set Level_one_status=:Level_one_status,level_two_status=:level_two_status,level_four_status=:level_four_status,modified_by=:modified_by,modified_date=:modified_date,final_status=:final_status,forward_status=:forward_status,remark=:remark  where id=:id";
//			app = sessionHQL.createQuery(hqlUpdatep).setString("Level_one_status", status_l1).setString("level_two_status", status_l2)
//					.setString("level_four_status", status_l4).setString("modified_by", username)
//					.setDate("modified_date", new Date()).setInteger("final_status", 3).setString("forward_status", role)
//					.setInteger("id", Integer.parseInt(id)).setString("remark", remark).executeUpdate();
//			
			
			
		pid_old.setLevel_one_status(status_l1);
		pid_old.setLevel_two_status(status_l2);
		pid_old.setLevel_four_status(status_l4);
		pid_old.setModified_date(new Date());
		pid_old.setModified_by(username);
		pid_old.setFinal_status(3);
		pid_old.setForward_status(role);
		pid_old.setRemark(remark);
		sessionHQL.update(pid_old);
		
		sessionHQL.flush();
		sessionHQL.clear();
		msg_out = "Policy Rejected "+pid_old.getPolicy_no()+ " Successfully ";
		
		
			nt2.setFrom_name_send(role);
			nt2.setTo_name_sent("level 1");
//			nt2.setMessage("You Have Rejected <b> "+" " +pid_old.getPolicy_title()+" </b>"+" From "+ username + " On ");
			nt2.setMessage(  username + " " + "Rejected" + "<b>" + " " + pid_old.getPolicy_title() + " </b>"    + " On "+"  "+"   ");
			nt2.setUrl_id("viewForm");
			nt2.setUrl_value("viewid:"+ id);
			nt2.setCreated_date(date);
			sessionHQL.save(nt2);
			
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					sessionHQL.close();
//					if (app > 0) {
//						msg_out = "0";
//					} else {
//						msg_out = "1";
//						tx.rollback();
//					}

				} catch (Exception e) {
					e.printStackTrace();
					msg_out = "Policy Not Rejected Successfully ";
					
					tx.rollback();
				}
				model.put("msg", msg_out);
				return msg_out;

			}



			
//------------------------------- Used For File Download function  encrypt id -----mirangi_21_3_22----------------------
			@SuppressWarnings("null")
			@RequestMapping(value = "/getDownloadPdfUrlforeduDoc_policy")
			public ModelAndView getDownloadPdfUrlforeduDoc_policy(@RequestParam(value = "msg", required = false) String msg,
					@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
					
					ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
					throws IOException {


				String url = pageUrl;
				String EXTERNAL_FILE_PATH = "";

				EXTERNAL_FILE_PATH = pdao.getFilePathQuery_policy(Integer.parseInt(doc_id1));
				
				if (EXTERNAL_FILE_PATH != "") {
					File file = null;
					file = new File(EXTERNAL_FILE_PATH);
					try {
						if (!file.exists()) {
							
							model.put("msg", "Sorry.The file you are looking for does not exist!");
							return new ModelAndView(url);
						}
					} catch (Exception exception) {
						
					}

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
					if (mimeType == null) {
						mimeType = "application/octet-stream";
					}
					response.setContentType(mimeType);
					response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
					response.setContentLength((int) file.length());
					InputStream inputStream = null;
					try {
						inputStream = new BufferedInputStream(new FileInputStream(file));
						FileCopyUtils.copy(inputStream, response.getOutputStream());
						model.put("msg", "Downloaded Successfully");
						return new ModelAndView(url);
					} catch (FileNotFoundException e) {
						//e.printStackTrace();
					}
				} 
				return new ModelAndView(url);
			}

			//=========================POLICY SUB CATEGORY DROPDOWN=====================================
			@RequestMapping(value = "/getSubpolicyUrl", method = RequestMethod.POST)
			public @ResponseBody List<TB_SUBPOLICYCATEGORY>  getSubUrinelistUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "selval", required = false) String selval,
					HttpServletRequest request)  {		
				
				List<TB_SUBPOLICYCATEGORY> list = pdao.getPolicylistUrl(selval);

				return list;
			}
			
			
			//==============================================AUTO-GENERATE POLICY AYUSH ID====================================================//


			@RequestMapping(value = "policyGid", method = RequestMethod.POST)
						public @ResponseBody String policyGid(ModelMap model, HttpSession session, HttpServletRequest request,String policy_category) {


			               
							Calendar cal = Calendar.getInstance();
							int CurrentYear = cal.get(Calendar.YEAR);
						        
							String unique = "";
				
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							String mid = "";
//						try {
										                
							 Query q2 = sessionHQL.createQuery("select policy_unique_id from TB_POLICY_INITIAL_DRAFT a\n"
				                		+ "where cast(policy_category as string)=:pc \n"
				                		+ " order by policy_unique_id desc").setString("pc",policy_category);
									 @SuppressWarnings("unchecked")
									 List<String> list2 = (List<String>) q2.list();	
									 if(!list2.isEmpty()) {
										 mid = list2.get(0).trim();
									 }else {
										 mid = "0000/00/0000";
									 }
									  
									  
									  String[] sp = mid.split("/");
									
								 
									 
									 if((mid!=null || !mid.equals("")) && (!policy_category.equals(""))) {
										 
										 unique = CurrentYear+"/"+policy_category+"/"+String.format("%04d", Integer.parseInt(sp[2])+1);

									 }
									 else {
										 unique = CurrentYear+"/"+policy_category+"/"+String.format("%04d", 0);

										 // 0000
										 
									 }
							   
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
								sessionHQL.close();
								

//							} 
//							catch (Exception e) {
//								
//								tx.rollback();
//							}
							return unique;

						}
//======================================OLD POLICY DATA FETCH=========================================
			@RequestMapping(value = "getoldpolicydatawherepu_id", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>>  getoldpolicydatawherepu_id(String puid)
				{
				
				Integer policy_id = pdao.getpolicy_id_by_policy_unique_id(puid);
				
				ArrayList<ArrayList<String>> list = pdao.getpolicyinformation(policy_id);
				
				return list;
				}



}
