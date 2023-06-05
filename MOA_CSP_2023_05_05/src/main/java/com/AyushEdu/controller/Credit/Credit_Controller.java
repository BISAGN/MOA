package com.AyushEdu.controller.Credit;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.Query;
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
import com.AyushEdu.Models.LMS_Credit.EDU_CREDIT_MSTR;
import com.AyushEdu.dao.LMS_Credit.CreditDao;
@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Credit_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}
	@Autowired
	CommonController common;
	@Autowired
	private CreditDao CDDAO;
	
	@RequestMapping(value = "/CreditUrl" , method = RequestMethod.GET)
	public ModelAndView CreditUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,@ModelAttribute("id1") String id, HttpServletRequest request) {
		try {
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList",common.getSystemList(sessionFactory,role) );
			Mmap.put("cid", id);
			Mmap.put("sid", id);
//			Mmap.put("getcoursenameList",common.getcoursenameList(sessionFactory) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("CreditTiles");
	}
	 
	@RequestMapping(value = "/getCourse_Autocomplete", method = RequestMethod.POST)
	public @ResponseBody List<String> getCourse_Autocomplete(HttpSession sessionA,HttpServletRequest request, String getcolumnname) {
		String userId = sessionA.getAttribute("userId_for_jnlp").toString();
	
		List<String> FinalList = new ArrayList<String>();
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(getcolumnname != "" ) {
				q = session.createQuery("select distinct cast(cd_uniq_id as text)  from EDU_LMS_SYSTEM_COURSE_DURATION where upper(cd_uniq_id) like:getcolumnname order by cast(cd_uniq_id as text)").setMaxResults(15);		
		        q.setParameter("getcolumnname", "%"+getcolumnname.toUpperCase()+"%");
		       
			}
			else {
				q = session.createQuery("select distinct cast(cd_uniq_id as text) from EDU_LMS_SYSTEM_COURSE_DURATION  order by cast(cd_uniq_id as text)").setMaxResults(15);
				
			}
	        List<String>  list = (List<String> ) q.list();
	        System.err.println("in 22222"+list);
			tx.commit();
			session.close();
			String enckey = getAlphaNumericString();
			Cipher c = null;
			
			c = EncryptionSHA256Algo(sessionA, enckey);
			
			for (int i = 0; i < list.size(); i++) {
				byte[] encCode = null;
				try {
					encCode = c.doFinal(list.get(i).getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
				FinalList.add(base64EncodedEncryptedCode);
			}
			FinalList.add(enckey + "4bsjyg==");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FinalList;
	}
	 
		@SuppressWarnings({ "unchecked", "deprecation" })
		@RequestMapping(value = "/getCourse_Autocomplete1",method=RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getCourse_Autocomplete1(HttpSession sessionU,String getcolumnname) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			//Session session = HibernateUtil.getSessionFactory().openSession();
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(getcolumnname != "") {
				q = session.createQuery("select distinct '' as dummy,cd_uniq_id  from EDU_LMS_SYSTEM_COURSE_DURATION where upper(cd_uniq_id) like:getcolumnname  order by cd_uniq_id").setMaxResults(15);		
		        q.setParameter("getcolumnname", "%"+getcolumnname.toUpperCase()+"%");
			}
			else {
				q = session.createQuery("select distinct '' as dummy,cd_uniq_id from EDU_LMS_SYSTEM_COURSE_DURATION  order by cd_uniq_id").setMaxResults(15);
			}
	        List<Object[]>  list = (List<Object[]> ) q.list();
			tx.commit();
			session.close();
			return getDDLCommonList(sessionU,list);
		}
	 
		public String getAlphaNumericString() 
	    { 
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 
	        StringBuilder sb = new StringBuilder(16); 
	        for (int i = 0; i < 16; i++) { 
	            int index = (int)(AlphaNumericString.length() * Math.random());  
	            sb.append(AlphaNumericString.charAt(index)); 
	        } 
	        return sb.toString(); 
	    } 
		
		public Cipher EncryptionSHA256Algo(HttpSession session,String enckey)
		{
		    try
		    {
		     	//String KeySpec = session.getAttribute("KeySpec").toString();
		    	String KeySpec="dc0da04af8fee58593442bf834b30739";
		        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		        KeySpec spec = new PBEKeySpec(enckey.toCharArray(), hex(KeySpec),1000,256);
		        SecretKey tmp = factory.generateSecret(spec);
		        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		        cipher.init(Cipher.ENCRYPT_MODE, secretKey,  new IvParameterSpec(hex(KeySpec)));
		        return cipher;
		     }
		    catch (Exception e)
		    {
		        System.out.println("Error while encrypting: " + e.toString());
		    }
		    return null;
		}
		
		public static byte[] hex(String str) {
			try {
	            return Hex.decodeHex(str.toCharArray());
	        }
	        catch (DecoderException e) {
	            throw new IllegalStateException(e);
	        }
	    }
		public @ResponseBody List<Map<String, Object>> getDDLCommonList(HttpSession sessionUserId,List<Object[]>  list) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String enckey = getAlphaNumericString();
			Cipher c = EncryptionSHA256Algo(sessionUserId,enckey);
			List<Map<String, Object>> FinalList = new ArrayList<Map<String, Object>>();

			for(Object[] listObject: list){
		    	String columnName = (String)listObject[1].toString();
		   		String columnCode = (String)listObject[0].toString();
		   		System.err.println(columnName);
		   		System.err.println(columnCode);
		   		byte[] enccolumnName = c.doFinal(columnName.getBytes());
			    String base64EncodedEncryptedcolumnName = new String(Base64.encodeBase64(enccolumnName));
			    
			    byte[] enccolumnCode = c.doFinal(columnCode.getBytes());
			    String base64EncodedEncryptedcolumnCode = new String(Base64.encodeBase64(enccolumnCode));
		   		
			    Map<String, Object> EncList = new LinkedHashMap<String, Object>();
		   		EncList.put("columnName2",base64EncodedEncryptedcolumnName);
			    EncList.put("columnCode2",base64EncodedEncryptedcolumnCode);
			    System.err.println("columnName : "+ base64EncodedEncryptedcolumnName );
		   		System.err.println("columnCode : "+base64EncodedEncryptedcolumnCode);
			    FinalList.add(EncList);
		   	}
			//Enc Key Append Last value of List
			Map<String, Object> EncKeyList = new LinkedHashMap<String, Object>();
			String a1=enckey+"4bsjyg==";
		    if(list.size() != 0) {
		    	EncKeyList.put("columnName1",a1);
		    	EncKeyList.put("columnCode1","gDKfjjU+/PZ6k4WWTJB1IA==");
		    	System.err.println("columnName1 "+a1);
		    	System.err.println("columnCode1 "+"gDKfjjU+/PZ6k4WWTJB1IA==");
		    }
		    FinalList.add(EncKeyList);
			return FinalList;
		}
		
		@PostMapping(value = "/creditAction")
		public ModelAndView creditAction(
				@Validated @ModelAttribute("creditCMD") EDU_CREDIT_MSTR rd,
				BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) throws IOException {

			int id = rd.getId() > 0 ? rd.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
				
				for(int i=1; i <= count_hidden_att; i++) {
//					String system_id = request.getParameter("system_id"+i);
//					String course_id = request.getParameter("course_id"+i);
					String course = request.getParameter("course"+i);
					String no_of_days = request.getParameter("no_of_days"+i);
					String max_duration = request.getParameter("max_duration"+i);
					String point = request.getParameter("point"+i);
					
//					if (system_id == null || system_id.trim().equals("0")) {
//						ra.addAttribute("msg", "Please Select System Name.");
//						return new ModelAndView("redirect:CreditUrl");
//					}
//					if (course_id == null || course_id.trim().equals("0")) {
//						ra.addAttribute("msg", "Please Select Course Name.");
//						return new ModelAndView("redirect:CreditUrl");
//					}
					if (course == null || course.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Course Code.");
						return new ModelAndView("redirect:CreditUrl");
					}
					if (no_of_days == null || no_of_days.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter No of Days.");
						return new ModelAndView("redirect:CreditUrl");
					}
					if (point == null || point.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Point.");
						return new ModelAndView("redirect:CreditUrl");
					}
					
					rd.setSystem_id("0");
					rd.setCourse_id("0");
					rd.setCourse(course);
					rd.setNo_of_days(no_of_days);
					rd.setMax_duration(max_duration);
					rd.setPoint(point);
					rd.setCreated_by(username);
					rd.setCreated_date(date);
						
					sessionHQL.save(rd);
					sessionHQL.flush();
					sessionHQL.clear();
				}
					tx.commit();
					ra.addAttribute("msg", "Data Saved Successfully.");
				
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
			return new ModelAndView("redirect:CreditUrl");
		}
		
		 @RequestMapping(value = "/getTotalDuration", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getTotalDuration(String course) {
		    	
			 List<ArrayList<String>> list = CDDAO.GetTotalDuration(course);	 
				return list;
			}
		 
		 @RequestMapping(value = "/getSystemCourse_fetch", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getSystemCourse_fetch(String system_id1,String course_id1) {
			 
		    	
			 List<ArrayList<String>> list = CDDAO.GetSystemCourse_fetch(system_id1,course_id1);	 
				return list;
			}
		 
		 @PostMapping("/getFilterCredit_data")
			public @ResponseBody List<Map<String, Object>> getFilterCredit_data(int startPage, int pageLength,
					String Search, String orderColunm, String orderType, String course, String max_duration,String no_of_days,String point) {
			 System.err.println("--------max_duration--------"+max_duration);
			 System.err.println("--------course_id--------"+course);
				return CDDAO.DataTableCreditDataList(startPage, pageLength, Search, orderColunm, orderType, course, max_duration,no_of_days,point);

			}
			@PostMapping("/getTotalCredit_dataCount")
			public @ResponseBody long getTotalCredit_dataCount(HttpSession sessionUserId, String Search, String course, String max_duration,String no_of_days,String point) {
		
				
				 System.err.println("--------max_duration-count-------"+max_duration);
				 System.err.println("--------course_count--------"+course);
				return CDDAO.DataTableCreditDataTotalCount(Search, course, max_duration,no_of_days,point);
			}
			
}
