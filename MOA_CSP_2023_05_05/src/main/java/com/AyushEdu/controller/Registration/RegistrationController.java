package com.AyushEdu.controller.Registration;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Registration.TB_REGISTRATION_DTL;
import com.AyushEdu.dao.HexatoAsciiDAO;
import com.AyushEdu.dao.HexatoAsciiDAOImpl;
import com.AyushEdu.dao.RoleBaseMenuDAO;

@Controller
public class RegistrationController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	CommonController comMstr = new CommonController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private Pattern pattern;
	private Matcher matcher;
	private static final String PASSWORD_PATTERN = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*])(?!.*\\s)(?!.*\\\\).{8,28})";
	public void Registration_otp() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	public boolean validate(final String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();  
	}
	@RequestMapping(value = "/registrationUrl", method = RequestMethod.GET)
	 public ModelAndView registrationUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("registrationUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("RegistrationTiles" , "registrationcmd", new TB_REGISTRATION_DTL());
	 }
	
	//------------ SAVE  for Registration-------------------
	
	@RequestMapping(value = "/registration_Action", method = RequestMethod.POST )
    public ModelAndView registration_Action(@Valid @Validated @ModelAttribute("registrationcmd") TB_REGISTRATION_DTL rt,    		
		   BindingResult result,ModelMap model,HttpServletRequest request,HttpSession session,Principal principal,RedirectAttributes ra)
	{
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = new Date();
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("registrationUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				String userName = request.getParameter("recr_email");
				String pers_aadhar_no11 = request.getParameter("pers_aadhar_no11");
				String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
				String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
				String pers_aadhar_nocom = pers_aadhar_no11 + pers_aadhar_no2 + pers_aadhar_no3;
				String mother_prifix=request.getParameter("mother_prifix");
				String father_prifix=request.getParameter("father_prefix");
				String candidate_prifix=request.getParameter("candidate_prifix");
				String recr_dob = request.getParameter("recr_dob");
				
				if (candidate_prifix == null || candidate_prifix.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Candidate Prifix");
					return new ModelAndView("redirect:registrationUrl");
				}
				if (father_prifix == null || father_prifix.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Father Prifix");
					return new ModelAndView("redirect:registrationUrl");
				}
				
				if (mother_prifix == null || mother_prifix.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Mother Prifix");
					return new ModelAndView("redirect:registrationUrl");
				}
				
				if (recr_dob == null || recr_dob.trim().equals("") || recr_dob.equals("DD/MM/YYYY")) {
						
					ra.addAttribute( "Please Enter The Date Of Birth");
						
					return new ModelAndView("redirect:registrationUrl");
					}
				
				if (pers_aadhar_nocom == null || pers_aadhar_nocom.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Aadhar No.");
					return new ModelAndView("redirect:registrationUrl");
				}

				
				String enckey ="commonPwdEncKeys";
				Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);
					
				String base64EncodedEncryptedAdhar="";
				if(!pers_aadhar_no11.equals("") && !pers_aadhar_no2.equals("") && !pers_aadhar_no3.equals("") ) {
					base64EncodedEncryptedAdhar = new String(Base64.encodeBase64( c.doFinal(pers_aadhar_nocom.getBytes())));
				}
				
		       	Boolean e = checkExistingEmailMethod(userName,pers_aadhar_nocom);
		       	
		    	if(e.equals(false) || e.equals(null)) {
		    		
		    	rt.setRecr_aadhar_no(base64EncodedEncryptedAdhar);					
			        rt.setEntry_dt(new Date());
			        rt.setRecr_father_title(Integer.parseInt(father_prifix));
			        rt.setRecr_mother_title(Integer.parseInt(mother_prifix));
			        rt.setRecr_cadidate_title(Integer.parseInt(candidate_prifix));
			        rt.setCreated_by(request.getParameter("recr_name"));
			        rt.setRecr_dob(comMstr.convertStringToDate(recr_dob));
			  //      rt.setRecr_dob(date);
					rt.setCreated_date(date);
			       
			        Session session1 = this.sessionFactory.openSession();
			        session1.beginTransaction();
			       
			        session1.save(rt);
			        session1.getTransaction().commit();
					session1.close();
					HttpSession session12 = request.getSession();
				    session12.setAttribute("recr_email", request.getParameter("recr_email"));
				    session12.setAttribute("recr_name", request.getParameter("recr_name"));
				    session12.setAttribute("pers_aadhar_nocom", base64EncodedEncryptedAdhar);
				    return new ModelAndView("redirect:otpveriUrl");
				}
				else {
					model.put("msg", "Data Already Exists!");
					model.put("email_txt", request.getParameter("recr_email"));
					model.put("recr_name_text", request.getParameter("recr_name"));
					model.put("recr_father_txt", request.getParameter("recr_father_name"));
					model.put("pers_aadhar_no11_val", request.getParameter("pers_aadhar_no11"));
					model.put("pers_aadhar_no2_val", request.getParameter("pers_aadhar_no2"));
					model.put("pers_aadhar_no3_val", request.getParameter("pers_aadhar_no3"));	
			        return new ModelAndView("redirect:registrationUrl");
			    }
	     			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("msg", "something wrong!");
			return new ModelAndView("redirect:registrationUrl");
		}
	}
	
	 // ----------------  if existing email for save REGISTRATION page --------------------
	@SuppressWarnings("unchecked")
	 public Boolean checkExistingEmailMethod(String userName,String aadhar_no) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<UserLogin> users = null;
		System.out.println("------session----------"+session);
		try {
			
			Query q = session.createQuery("from UserLogin where userName=:userName");
			q.setParameter("userName", userName);
//			q.setParameter("aadhar_no", aadhar_no);
			users = (List<UserLogin>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
       
	 /////////////////////////////////////////////// End Registration////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/forget_passwordUrl")
   public ModelAndView forget_passwordUrl(ModelMap Mmap, HttpSession session,
       @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) { 
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("forget_passwordUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
       return new ModelAndView("forget_passwordTiles");
   }
	
   @RequestMapping(value = "/forget_password_Act")
   @ResponseBody
   public ModelAndView forget_password_Act(HttpServletRequest request,ModelMap model,HttpSession session,
           @RequestParam(value = "recr_email", required = false) String email)throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, 
   		IllegalBlockSizeException, BadPaddingException {	
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("forget_passwordUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
   	if(email.isEmpty()) {
   		model.put("recr_email_lbl","<i class='fa fa-exclamation'></i>&nbsp;Please Enter Email Address");
			return new ModelAndView("forget_passwordTiles");
   	}
   	else {
//	    	try {
//		    	if(!userDAO.findUser(email).getUserName().isEmpty()) {
//					 model.put("msg", "OTP has been Sent to Email");			
//					 model.put("email", userDAO.findUser(email).getUserName());
//					 model.put("candiatename", userDAO.findUser(email).getLogin_name());
//					 model.put("type", "forget");
//					 model.put("testid", "");
//					 session.setAttribute("recr_email", email);
//					 session.setAttribute("recr_name", userDAO.findUser(email).getLogin_name());
//					 session.setAttribute("pers_aadhar_nocom", userDAO.findUser(email).getAadhar_no());					 
//					 return new ModelAndView("redirect:otpveriUrl");
//				}
//				else {			
//					model.put("msg","Invalid Email Address!");
//					return new ModelAndView("redirect:forget_passwordUrl");
//				}
//   		}catch (Exception e) {
//   			model.put("msg","Invalid Email Address!");
//				return new ModelAndView("redirect:forget_passwordUrl");
//			}
		return new ModelAndView("redirect:forget_passwordUrl");

   	}
	}
	
   //////////////////////////////////////////////Start OTP/////////////////////////////////////////////////////////////////////////////////////
	//------------ Open url for otp -------------------
   @RequestMapping(value = "/otpveriUrl")
   public ModelAndView otpveriUrl(ModelMap Mmap, HttpSession session, @RequestParam(value = "type", required = false) String type,
		   @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,HttpServletResponse response,RedirectAttributes ra) {
//   	String recr_emailSession =  request.getSession().getAttribute("recr_email").toString();
//   	String recr_nameSession =  request.getSession().getAttribute("recr_name").toString();
   
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("OTP_verificationTiles", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	   ra.addAttribute("msg", msg);
	   Mmap.put("type1",type);  
   	
	   Mmap.put("cotpSession1","1105");  
   	
       return new ModelAndView("OTP_verificationTiles");
   }
            
   //-------------------------  save for OTP  Verification-------------------------------        
 
   @RequestMapping(value = "/otp_veriAction")
   public ModelAndView otp_veriAction(HttpServletRequest request,ModelMap model,HttpSession session,RedirectAttributes ra)throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException  {
       String pass =  request.getParameter("recr_pwd");
       String re_pass =  request.getParameter("recr_repwd");  
       String recr_email =  request.getSession().getAttribute("recr_email").toString();
   	String recr_name =  request.getSession().getAttribute("recr_name").toString();
   	String pers_aadhar_nocom =  request.getSession().getAttribute("pers_aadhar_nocom").toString();
  
//	SECURITY -- RIDDHI 
	if(request.getHeader("Referer") == null ) { 
//		session.invalidate();
		model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
		return new ModelAndView("redirect:/landingpage");
	 }
	String roleid1 = session.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("OTP_verificationTiles", roleid1);		
		if(val == false) {
			return new ModelAndView("AccessTiles");
	}
		
       int errCount=0;
       if(pass.isEmpty()) {        	
   		model.put("recr_pwd_lbl","<i class='fa fa-exclamation'></i>&nbsp;Please Enter Password");
   		errCount++;
   	}
       
//       if(validate(pass) == false) {
//			model.put("pwdtxt_msg", "<i class='fa fa-exclamation'></i>&nbsp;Current Password Pattern Doesn't Match");		
//			errCount++;
//		}
       
//       if(re_pass.isEmpty()) {
//       	errCount++;
//   		model.put("recr_repwd_lbl","<i class='fa fa-exclamation'></i>&nbsp;Please Enter Confirm Password");    		
//   	}
//       if(validate(re_pass) == false) {
//			model.put("pwdtxt_msg", "<i class='fa fa-exclamation'></i>&nbsp;Current Password Pattern Doesn't Match");		
//			errCount++;
//		}
       if(!pass.equals(re_pass)) {
       	errCount++;
       	model.put("recr_repwd_lbl","<i class='fa fa-exclamation'></i>&nbsp;Password and Confirm Password Didn't Match");    		
       }
       if(errCount > 0)
       {
       	model.put("recr_otp1",request.getParameter("recr_otp"));
			model.put("verifyotpri","pwdquery");
   		model.put("email1",request.getParameter("email1"));
   		model.put("candiatename1",request.getParameter("candiatename1"));
   		model.put("pers_aadhar_nocom1",request.getParameter("pers_aadhar_nocom1"));
   		model.put("type1",request.getParameter("gettype"));  		
			return new ModelAndView("OTP_verificationTiles");
       }
       else {
       	String enckey ="commonPwdEncKeys";
	        Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);
	     //   String base64EncodedEncryptedAadhar = new String(Base64.encodeBase64(c.doFinal(pers_aadhar_nocom.getBytes())));
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           String hashedPassword = passwordEncoder.encode(pass);
         
	         if(request.getParameter("gettype").equals("forget")) {
	        	
		        Session sessionf = this.sessionFactory.openSession();
		        Transaction txf = sessionf.beginTransaction();
		        
		        String hqlf = "update UserLogin set password=:password where userName=:userName";
		        @SuppressWarnings("rawtypes")
		        Query queryf = sessionf.createQuery(hqlf).setParameter("password", hashedPassword).setParameter("userName", recr_email);
		        queryf.executeUpdate();
		        txf.commit();
		        sessionf.close();
		      
	        }
	        else {
	        	Session sessiona = this.sessionFactory.openSession();
	            Transaction txa = sessiona.beginTransaction();
	            String hqla = "update TB_REGISTRATION_DTL set recr_pwd=:recr_pwd where recr_email=:recr_email and recr_name=:recr_name";
	            Query querya = sessiona.createQuery(hqla).setParameter("recr_pwd", hashedPassword).setParameter("recr_email", recr_email).setParameter("recr_name", recr_name);
	            querya.executeUpdate();
	           
		        
		       
		        //------------- final entry in logininformation table-------------
		        UserLogin useridpass =new UserLogin();
		        useridpass.setLogin_name(recr_name);
		        useridpass.setUserName(recr_email);
		        useridpass.setPassword(hashedPassword);
		        //useridpass.setAadhar_no(pers_aadhar_nocom);
		        useridpass.setEnabled(1);
		        useridpass.setAccountNonExpired(1);
		        useridpass.setAccountNonExpired(1);
		        useridpass.setAccountNonExpired(1);
		        int userid=(int) sessiona.save(useridpass);
		      
//		        
		        //------------ users id ----------------------        
		        UserRole roleiduser =new UserRole();                        
		        roleiduser.setUserId(userid);
		        roleiduser.setRoleId(8);
		        sessiona.save(roleiduser);   
		        txa.commit();
		        sessiona.close();
		        //////////////////////////
	     	}
	         
	         ra.addAttribute("msg", "Dear, " + recr_name + 
	            		" Thank You for Registration! Your Request has been Submitted Successfully. You will soon get a response from Your Depatrment Admin On The Register Email Id!");
	     
	 //        model.put("msg", "You will redirect on Login");	        
	        return new ModelAndView("redirect:login");
       }
   }    
   @RequestMapping(value = "/checkOtpEmail")
	@ResponseBody
	public boolean checkOtpEmail(HttpServletRequest request,HttpServletResponse response,@RequestParam String cotp) {
		
		String cotpSession =  "1105"; // error
		System.out.println("--he--"+cotp+"------"+cotpSession);
		
		if(cotp.equals(cotpSession)) {
			return true;
		
		}else {
			return	false;
		}
		
	}
   /////////////////////////////////////END OTP////////////////////////////////////

}
