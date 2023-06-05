package com.AyushEdu.controller.Policy_controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.Policy_dao.Track_Policy_StatusDAO;




@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Track_Policy_Status_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common;
//	@Autowired
//	private SessionFactory sessionFactory2;

	@Autowired
	Track_Policy_StatusDAO tdao;
	
	//==========================================OPEN PAGE TRACK POLICY STATUS========================================== 
	
		@RequestMapping(value = "/Track_Policy_StatusUrl", method = RequestMethod.GET)
		public ModelAndView Track_Policy_StatusUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

			Mmap.put("msg", msg);
			//Mmap.put("GetCategoryData",tdao.GetCategoryData());
			Mmap.put("policycat", common.getPolicyCategoryList(sessionFactory));
			Mmap.put("subpolicycat", common.getSubPolicyCategoryList(sessionFactory));
			Mmap.put("policytypeList",common.getpolicytypeList(sessionFactory));
			return new ModelAndView("Track_Policy_StatusTiles");

		}
		
		//==========================================DATA TABLE TRACK POLICY STATUS========================================== 
		@RequestMapping(value = "/getTackPolicyStatusdata", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getTackPolicyStatusdata(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,  String policy_number,String category, String sub_category,String policy_type,
				String globSearch, HttpSession sessionUserId, Principal principal) {
			return tdao.DataTableTrackPolicyStatusDataList(startPage, pageLength, Search, orderColunm, orderType,policy_number,category,sub_category,policy_type,globSearch, sessionUserId);

		}

		@RequestMapping(value = "/getTackPolicyStatusCount", method = RequestMethod.POST)
		public @ResponseBody long getTackPolicyStatusCount(HttpSession sessionUserId, String Search, String policy_number,String category, String sub_category,String policy_type,String globSearch) {
			return tdao.DataTableTrackPolicyStatusTotalCount(Search, policy_number,category,sub_category,policy_type, globSearch,sessionUserId);
		}
		

		@RequestMapping(value = "/Get_policy_number_Search_Status", method = RequestMethod.POST)
		public @ResponseBody  ArrayList<String> Get_policy_number_Search_Status(String policy_number) {
			return tdao.data_policy_number_Search_Status(policy_number);
		}

		
		//========================================== DOWNLOAD POLICY ========================================== 

		@SuppressWarnings("null")
		@RequestMapping(value = "/getDownloadPdfUrlforeduDoc_policy_Track")
		public ModelAndView getDownloadPdfUrlforeduDoc_policy_Track(@RequestParam(value = "msg", required = false) String msg,
				@ModelAttribute("doc_id1") String doc_id1, @ModelAttribute("pageUrl") String pageUrl,
				
				ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
				throws IOException {


			String url = pageUrl;
			String EXTERNAL_FILE_PATH = "";

			EXTERNAL_FILE_PATH = tdao.getFilePathQuery_Track_policy(Integer.parseInt(doc_id1));
			
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

		//==========================autocomplete===============================================
				//for Policy Number

@RequestMapping(value = "/getpolicynoAuto", method = RequestMethod.POST) 
						public @ResponseBody List<String> getpolicynoAuto(String a,String policy_category,String policy_sub_category,String policy_type) {
							
						        Session session = this.sessionFactory.openSession();
								Transaction tx = session.beginTransaction();
						       String qry ="";
						       if(policy_category == null || policy_category == "") 
						       {
						    	   policy_category ="0";
						       }
						       if(policy_sub_category == null || policy_sub_category == "") 
						       {
						    	   policy_sub_category ="0";
						       }
						       if(policy_type == null || policy_type == "") 
						       {
						    	   policy_type ="0";
						       }
						      

						       if (!policy_category.equals("0")) {
						    	   qry += "and policy_category=:cat";
								}
						       
						       if  (!policy_sub_category.equals("0")) {
									qry += "and policy_sub_category=:subcat";
								}
						       if  (!policy_type.equals("0")) {
									qry += "and policy_type=:pt";
								} 

						                Query q = session.createQuery("select distinct policy_no from TB_POLICY_INITIAL_DRAFT where lower(policy_no) like : a "+qry+"\n");
						               
						                	Integer pc = Integer.parseInt(policy_category);
									       Integer psc = Integer.parseInt(policy_sub_category);
									      
						                	 q.setParameter("a",a.toLowerCase()+'%');
						                	 
						                	 if  (!policy_category.equals("0")) {
												 q.setParameter("cat",pc);
											}  
						                	 if  (!policy_sub_category.equals("0")) {
												 
												 q.setParameter("subcat",psc);
											}
											if  (!policy_type.equals("0")) {
												 q.setParameter("pt",policy_type);
											}
											
						        @SuppressWarnings("unchecked")
						        List<String> list = (List<String>) q.list();
						        
						        tx.commit();
						        session.close();

						        return list;
						}

				
				// for Policy Category
				@RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)
				@ResponseBody public ArrayList<ArrayList<String>> getCategoryList() {

					ArrayList<ArrayList<String>> list =tdao.GetCategoryData();
					return list;
				}
				
				// for Sub Policy Category
				@RequestMapping(value = "/getSubCategoryList", method = RequestMethod.POST)
				@ResponseBody public ArrayList<ArrayList<String>> getSubCategoryList(int policy_category) {

					ArrayList<ArrayList<String>> list =tdao.GetSubCategoryData(policy_category);
					return list;
				}
					

				//==========================autocomplete===============================================
				//for approved Policy Number 
						@RequestMapping(value = "/getaprovepolicynoAuto", method = RequestMethod.POST) 
						public @ResponseBody List<String> getaprovepolicynoAuto(String a) {
							
						        Session session = this.sessionFactory.openSession();
								Transaction tx = session.beginTransaction();
						       
						                Query q = session.createQuery("select distinct policy_no from TB_POLICY_INITIAL_DRAFT where final_status='2' and lower(policy_no) like : a ");
						                q.setParameter("a",a.toLowerCase()+'%');
						        
						        @SuppressWarnings("unchecked")
						        List<String> list = (List<String>) q.list();
						    
						        tx.commit();
						        session.close();

						        return list;
						}

}
