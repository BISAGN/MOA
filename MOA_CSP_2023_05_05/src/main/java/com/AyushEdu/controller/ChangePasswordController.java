package com.AyushEdu.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.helpdesk.Tb_HTover;
import com.AyushEdu.dao.ChangePasswordDAO;
import com.AyushEdu.dao.HexatoAsciiDAO;
import com.AyushEdu.dao.HexatoAsciiDAOImpl;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.UserLoginDAOImpl;
import com.AyushEdu.validation.DateWithTimeStampController;
import com.AyushEdu.validation.PasswordValidator;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ChangePasswordController {

	UserLoginDAO userlogin =  new UserLoginDAOImpl();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private ChangePasswordDAO cpd;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping(value = "/changePassword",method= RequestMethod.GET)
	public ModelAndView changePassword(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		System.err.println("------change");
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("changePassword", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if(request.getHeader("Referer") == null ) {
//			msg = "";
//		}
		model.put("msg", msg);		
		model.put("userDetails", userlogin.findByRoleId2(sessionFactory,userid));
		return new ModelAndView("changePasswordTile");	
	}
	 
	@RequestMapping(value = "/changePassword_Action",method=RequestMethod.POST)
	public ModelAndView changePassword_Action(HttpServletRequest request,ModelMap model,HttpSession session) {
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		UserLogin userDetails = userlogin.findByRoleId2(sessionFactory,userid);
		String old_pass =  request.getParameter("old_pass");
		String newpass = request.getParameter("new_pass");
		String c_pass  =  request.getParameter("c_password");
		
		Session sessionHQL = this.sessionFactory.openSession();
		if(old_pass == "" || old_pass.equals("")) {
			model.put("msg", "Please Enter Old Password.");		
			return new ModelAndView("redirect:changePassword");
		}
		else if(validation.PasswordLength(old_pass) == false){
	 		model.put("msg",validation.PasswordMSG);
			return new ModelAndView("redirect:changePassword");
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(old_pass.trim(), userDetails.getPassword())) {
			model.put("msg", "Old Password is not Correct");		
			return new ModelAndView("redirect:changePassword");
		}
		
		if(newpass == "" || newpass.equals("")) {
			model.put("msg", "Please Enter New Password.");		
			return new ModelAndView("redirect:changePassword");
		}
		else if(validation.PasswordLength(newpass) == false){ // Check New Password Pattern length 
	 		model.put("msg",validation.PasswordMSG);
			return new ModelAndView("redirect:changePassword");
		}
		else if(!PasswordValidator.validate(newpass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
			model.put("msg",validation.PasswordPatternMSG);
			return new ModelAndView("redirect:changePassword");
		}
		
		if(c_pass == "" || c_pass.equals("")) {
			model.put("msg", "Please Enter New Password.");		
			return new ModelAndView("redirect:changePassword");
		}
		else if(validation.PasswordLength(c_pass) == false){
	 		model.put("msg",validation.PasswordMSG);
			return new ModelAndView("redirect:changePassword");
		}
		
		if(!newpass.trim().equals(c_pass.trim())) {			
			model.put("msg", "Passwords do not match.");		
			return new ModelAndView("redirect:changePassword");
		}
		else {
			String hashedOldPassword = passwordEncoder.encode(newpass.trim());
			userDetails.setPassword(hashedOldPassword);				
			sessionHQL.beginTransaction();
			sessionHQL.saveOrUpdate(userDetails);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			model.put("msg", "Password Changed Successfully");
			
//			session.invalidate();
			return new ModelAndView("redirect:/landingpage");
		}
	
	}

	@RequestMapping(value = "/handingTakingOver",method= RequestMethod.GET)
	public ModelAndView handingTakingOver(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpServletRequest request) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("handingTakingOver", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);		
		model.put("userDetails", userlogin.findByRoleId(userid));
		return new ModelAndView("handingTakingOverTiles");	
	}
	
	@RequestMapping(value = "/handingTakingOver_Action",method=RequestMethod.POST)
	public ModelAndView handingTakingOver_Action(HttpServletRequest request,ModelMap model,HttpSession session,
			@RequestParam(value = "auth_letter1", required = false) MultipartFile auth_letter) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String handingTakingOverPath = session.getAttribute("handingTakingOverPath").toString();
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("handingTakingOver", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		UserLogin userDetails = userlogin.findByRoleId(userid);
		
		String ht_type = request.getParameter("ht_type");
		String ht_reason = request.getParameter("ht_reason");
	//	String to_army_no = request.getParameter("to_army_no");
		int flag = 0;
		Session sessionHQL = this.sessionFactory.openSession();
		if(ht_type == "" || ht_type.equals("")) {
			model.put("msg", "Please select H/T Type");		
			return new ModelAndView("redirect:handingTakingOver");
		}
		if(ht_reason == "" || ht_reason.equals("")) {
			model.put("msg", "Please select Reason");		
			return new ModelAndView("redirect:handingTakingOver");
		}
//		if(to_army_no == "" || to_army_no.equals("")) {			
//			model.put("msg", "Please enter Army No");		
//			return new ModelAndView("redirect:handingTakingOver");
//		}
		else {
			Tb_HTover ht = new Tb_HTover();
			sessionHQL.beginTransaction();
			ht.setHt_type(ht_type);
			ht.setHt_reason(ht_reason);
			//ht.setTo_army_no(to_army_no);
			//ht.setFrom_army_no(userDetails.getArmy_no());
			ht.setFrom_userid(userDetails.getUserId());
			ht.setFrom_username(userDetails.getUserName());
			
			String fname = "";
			if (!auth_letter.isEmpty()) {
				// code modify by Paresh on 05/05/2020
				String extension = "";
				try {
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = auth_letter.getBytes();
					File dir = new File(handingTakingOverPath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = auth_letter.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i+1);
					}
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_HT."+extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();
					ht.setAuth_letter(fname);
				}
				catch (Exception e) {
		       }
			} 
			flag = (int) sessionHQL.save(ht);
			
			String newpass = "Test@123";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(newpass);
			userDetails.setPassword(hashedPassword);	
			//userDetails.setArmy_no(to_army_no);
			sessionHQL.saveOrUpdate(userDetails);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		if(flag > 0) {
			HttpSession sess = request.getSession(false);
			sess.invalidate();
			return new ModelAndView("redirect:/login");
		}else {
			model.put("msg", "Error! Please Retry");		
			return new ModelAndView("redirect:handingTakingOver");
		}
	}
	
	@RequestMapping(value = "/my_profile",method= RequestMethod.GET)
	public ModelAndView my_profile(@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,HttpSession session,ModelMap Mmap) {
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("AccessTiles");
//		}
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		Mmap.put("mp", roledao.getUserLoginbyid(userid));
		return new ModelAndView("myProfileTiles");
	}	
	
//	@RequestMapping(value = "/ForgotPassword_Action",method=RequestMethod.POST)
//	public ModelAndView ForgotPassword_Action(HttpServletRequest request,ModelMap model,RedirectAttributes ra,HttpSession session) {
//		
//		System.err.println("hiiiiiiiiiiiiiii");
//		String aadhaar_no = request.getParameter("aadhaar_no");
//		
//		String hql ="select count(t.id) from AYUSH_ID_DIRECTORY_PARENT t WHERE t.aadhaar_no=:aadhaar_no";
//		Session sessionHQL= this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL.createQuery(hql);
//		q.setParameter("aadhaar_no",aadhaar_no);
//		@SuppressWarnings("unchecked")
//		Long count = (Long) q.list().get(0);
//		
//		System.err.println("count---->   "+count);
//		
//		if (count == 0) {
//					
//			ra.addAttribute("msg", "your aadhaar number is not associated with any account");
//				}
//		
//		if (count > 0) {
//			String hql2 ="select x.user_id from AYUSH_ID_DIRECTORY_PARENT x WHERE x.aadhaar_no=:aadhaar_no";
//			Query q2 = sessionHQL.createQuery(hql2);
//			q2.setParameter("aadhaar_no",aadhaar_no);
//			@SuppressWarnings("unchecked")
////			Long userid = (Long) q2.list().get(0).to;
//			String userid = (String) q2.list().get(0).toString();
//			
////			List<String> list = (List<String>) q2.list();
////			String userid = list.get(0);
//			System.err.println("userid----->    "+userid);
//		}
//		
//		tx.commit();
//		sessionHQL.close();
//		
//		return new ModelAndView("redirect:forgotpasswordncism");
//	}
	
	 @SuppressWarnings("unchecked")
		@RequestMapping(value = "/ForgotPassword_ctrl", method = RequestMethod.POST)
		 	public @ResponseBody  ArrayList<ArrayList<String>> ForgotPassword_ctrl(String aadhaar_no) {
		 ArrayList<ArrayList<String>> udata = null;
		 try { 
			 udata = cpd.getForgotPassworduserdataList(aadhaar_no);  
		 }catch (Exception e) {
				e.printStackTrace();
			} 
				return udata;
	 }
	 
	 @SuppressWarnings("unchecked")
		@RequestMapping(value = "/forgotpassUsernameEmail", method = RequestMethod.POST)
		 	public @ResponseBody  ArrayList<ArrayList<String>> forgotpassUsernameEmail(String username,String email,String cat) {
		 ArrayList<ArrayList<String>> udata = null;
		 try { 
			 udata = cpd.getForgetPassInstName(username,email,cat);  
		 }catch (Exception e) {
				e.printStackTrace();
			} 
				return udata;
	 }

		@PostMapping(value = "/changepassbyforgot_Url")
		public @ResponseBody ModelAndView changepassbyforgot_Url(@ModelAttribute("user_id4") int u_id,BindingResult result, HttpServletRequest request,
				HttpSession session,HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,RedirectAttributes ra,
				@RequestParam(value = "new_pass4", required = false) String newpass, Authentication authentication) {
			
			
			//---------------parth patel		
			String txtInput = request.getParameter("txtInput1").replaceAll("\\s", "").toString();
			String capcha =  request.getSession().getAttribute("capcha").toString();
			System.err.println(txtInput+"--"+capcha);
			 if(!txtInput.equals(capcha)){
				 ra.addAttribute("msg", "Captcha Validation failed!");
				 return new ModelAndView("redirect:portalforgotpassword");
			 }
//			---------------parth patel	
			List<String> liststr = new ArrayList<String>();
			
//			String username = session.getAttribute("username").toString();
			
			try {
				
				System.err.println("u_id----------->   "+ u_id);
				System.err.println("newpass----------->   "+ newpass);
				
				String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$";
				List<String> values = new ArrayList<String>();
				values.add(newpass); 
				Pattern ptrn = Pattern.compile(pattern);
				  Matcher matcher = ptrn.matcher(values.get(0));
				  if(!matcher.matches()){
					  ra.addAttribute("msg", "New Password does not matches to pattern");
					  return new ModelAndView("redirect:portalforgotpassword");
				  }
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String newPassword = passwordEncoder.encode(newpass);
				
				Session sessionHQL = this.sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate = "update UserLogin set password=:password where userId=:userId";
				 
			@SuppressWarnings({ "rawtypes", "deprecation" })
				int app = sessionHQL.createQuery(hqlUpdate)
				.setParameter("password", newPassword)
				.setParameter("userId", u_id).executeUpdate();
			
//			.setString("modified_by", username)
//				.setDate("modified_date", new Date()).
				tx.commit();
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Password Reset Successfully");
				} else {
					liststr.add("Password Reset Unsuccessfull");
				}
				ra.addAttribute("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT RESET THIS DATA BECAUSE IT IS ALREADY IN USED.");
				ra.addAttribute("msg",liststr.get(0));
			}
			
			return new ModelAndView("redirect:landingpage");
		}
		
		
		@RequestMapping(value = "/changeusernamepass",method= RequestMethod.GET)
		public ModelAndView changeusernamepass(ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpServletRequest request) {
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("changeusernamepass", roleid);	
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if(request.getHeader("Referer") == null ) {
//				msg = "";
//			}
			
			model.put("msg", msg);		
			model.put("userDetails", userlogin.findByRoleId2(sessionFactory,userid));
			return new ModelAndView("changeusernamepass_Tiles");	
		}
		
//		
		@RequestMapping(value = "/changeusernamePassword_Action",method=RequestMethod.POST)
		public ModelAndView changeusernamePassword_Action(HttpServletRequest request,ModelMap model,HttpSession session) {
			
//			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			int userid = Integer.parseInt(session.getAttribute("userId").toString());
			
			System.err.println("userid-------  sesssion     -->   "+  session.getAttribute("userId").toString());
			
//			UserLogin userDetails =    userlogin.findByRoleId2(sessionFactory,userid);
			
			
			String username =  request.getParameter("username");
			String newpass = request.getParameter("new_pass");
			String c_pass  =  request.getParameter("c_password");
			
			Session sessionHQL = this.sessionFactory.openSession();
//			if(old_pass == "" || old_pass.equals("")) {
//				model.put("msg", "Please Enter Old Password.");		
//				return new ModelAndView("redirect:changePassword");
//			}
//			else if(validation.PasswordLength(old_pass) == false){
//		 		model.put("msg",validation.PasswordMSG);
//				return new ModelAndView("redirect:changePassword");
//			}
			
			UserLogin INF = (UserLogin) sessionHQL.get(UserLogin.class, userid);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
//			if(!passwordEncoder.matches(old_pass.trim(), userDetails.getPassword())) {
//				model.put("msg", "Old Password is not Correct");		
//				return new ModelAndView("redirect:changePassword");
//			}
			
			if(newpass == "" || newpass.equals("")) {
				model.put("msg", "Please Enter New Password.");		
				return new ModelAndView("redirect:changePassword");
			}
			else if(validation.PasswordLength(newpass) == false){ // Check New Password Pattern length 
		 		model.put("msg",validation.PasswordMSG);
				return new ModelAndView("redirect:changePassword");
			}
			else if(!PasswordValidator.validate(newpass)) { // Check New Password Pattern "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,28})"
				model.put("msg",validation.PasswordPatternMSG);
				return new ModelAndView("redirect:changePassword");
			}
			
			if(c_pass == "" || c_pass.equals("")) {
				model.put("msg", "Please Enter New Password.");		
				return new ModelAndView("redirect:changePassword");
			}
			else if(validation.PasswordLength(c_pass) == false){
		 		model.put("msg",validation.PasswordMSG);
				return new ModelAndView("redirect:changePassword");
			}
			
			if(!newpass.trim().equals(c_pass.trim())) {			
				model.put("msg", "Passwords do not match.");		
				return new ModelAndView("redirect:changePassword");
			}
			else {
				String hashedOldPassword = passwordEncoder.encode(newpass.trim());
				INF.setUserName(username);
				INF.setPassword(hashedOldPassword);		
				INF.setAadhaar_is_verified(true);
				sessionHQL.beginTransaction();
				sessionHQL.saveOrUpdate(INF);
				sessionHQL.getTransaction().commit();
				sessionHQL.close();
				model.put("msg", "Password Changed Successfully");
			}
			return new ModelAndView("redirect:changePassword");
		} 



		
		
//		newusernamevalidfetch_ctrl
		
		@RequestMapping(value = "/newusernamevalidfetch_ctrl", method = RequestMethod.POST)
		public @ResponseBody long newusernamevalidfetch_ctrl(String newusername) {
			return cpd.getUsernamevaliddata(newusername);
		}
		
		//user guide dashboard videos as per login
				@RequestMapping(value = "/db_ayusheducationusermanual")
				public void db_ayusheducationusermanual(@ModelAttribute("filename") String filename,
						 ModelMap model, HttpServletRequest request,
						HttpServletResponse response) throws IOException {
					final int BUFFER_SIZE = 4096;
					String filePath = "";
					filePath ="/srv"+filename;						
					model.put("filePath", filePath);
					ServletContext context = request.getSession().getServletContext();
			
					try {
						
							String fullPath = filePath;
							File file = null;
							file=new File(filePath);
							System.out.println("fullPath "+fullPath);
							File downloadFile = new File(fullPath);
							System.out.println("downloadFile "+downloadFile);
							FileInputStream inputStream = new FileInputStream(downloadFile);
						//	System.out.println("inputStream "+inputStream);
							String mimeType = context.getMimeType(fullPath);
							if (mimeType == null) {
								mimeType = "application/octet-stream";
							}
							response.setContentType(mimeType);
							response.setContentLength((int) downloadFile.length());
//							String headerKey = "Content-Disposition";
//							String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
							response.setHeader("Content-Security-Policy", String.format("upgrade-insecure-requests"));
							//response.setHeader(headerKey, headerValue);
							OutputStream outStream = response.getOutputStream();
							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;
							response.setContentLength((int) file.length());
//							InputStream inputStream = null;
//							try {
//								inputStream = new BufferedInputStream(new FileInputStream(file));
//								FileCopyUtils.copy(inputStream, response.getOutputStream());
//							} catch (FileNotFoundException e) {
//								e.printStackTrace();
//							}
							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}
							inputStream.close();
							outStream.close();
					
					} catch (Exception ex) {
						ex.printStackTrace();
						String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
						OutputStream outStream = response.getOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int bytesRead = -1;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, bytesRead);
						}
						inputStream.close();
						outStream.close();
					}
				}
		
		
}
