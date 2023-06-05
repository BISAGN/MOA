package com.AyushEdu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.EDU_CONTACT_DETAILS;
import com.AyushEdu.Models.EDU_TB_FEEDBACK_DETAILS;
import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.TB_LDAP_MODULE_MASTER;
import com.AyushEdu.Models.TB_LDAP_SCREEN_MASTER;
import com.AyushEdu.Models.TB_LDAP_SUBMODULE_MASTER;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Enhancement_of_Research.TB_ENHANCE_RESEARCH_COMMENT_DETAILS;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.controller.Alumni.Alumni_Ventures_Controller;
import com.AyushEdu.dao.HexatoAsciiDAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.UserServiceDAOImpl;
import com.AyushEdu.dao.Enhancement_Of_Research.Advance_Search_DAO;
import com.AyushEdu.dao.Feedback.Feedback_dashboardDao;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.service.UserDetailsServiceImpl;

@Controller
public class LoginController {

	@Autowired
	RoleBaseMenuDAO roleBaseDAO;

	@Autowired
	CommonController common;

	@Autowired
	Type_of_Degree_MstrDao TDdao;
	@Autowired
	private Feedback_dashboardDao fs;
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	AuthenticationManager au;

	@Autowired
	private UserLoginDAO userLoginDAO;

	@Autowired
	private UserServiceDAOImpl uer;

	@Autowired
	Alumni_Ventures_Controller al;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	HexatoAsciiDAO hexa;
	
	
	@Autowired
	EmailConfig emailsend;
	
	@Autowired
	Advance_Search_DAO AER;


	@RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminHomePage");
		return model;
	}

	@RequestMapping(value = "/admin/commonDashboard", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		System.out.println("commonDashboard");
		Mmap.put("msg", msg);
		String role = session.getAttribute("role").toString();
		System.err.println("commonDashboard----role----------------"+role);

//		if (role.contains("Institute") && !role.contains("Student")) {
//			Mmap.put("selected","inst");
//
//		}else if(role.contains("University") && !role.contains("Student")) {
//			Mmap.put("selected","uni");
//
//		}else if(role.contains("NCISM") && !role.contains("Student") ) {
//			Mmap.put("selected","com");
//
//		} else {
			Mmap.put("selected","stud");

//		}
		return new ModelAndView("commanDashboardTiles");
	}
	
	
	
	
	@RequestMapping(value = "/user/userDashboard")
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("userHomePage");
		return model;
	}
	
	/* ################### Layout rootfiles Start here #################### */		    
    
    /* ##Ayush Portal Landing and inner pages layout start (English/Hindi)-Dark Blue theme## */
	
//	 @GetMapping(value = "/landingpage")
//	    public String landingpage(Model model,@RequestParam(value = "msg", required = false) String msg) {
//		 
//	    	model.addAttribute("msg", msg);
//	    	model.addAttribute("marque", TDdao.getMarqueData("Ayush Portal"));
//	    	model.addAttribute("langugae", "english");
//	    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//	    	model.addAttribute("visiter_count",roleBaseDAO.VisitorCounter());
//	        return "landingpage";
//	    }  
	
	@GetMapping(value = "/landingpage")
	public ModelAndView landingpage(Model model, @Valid @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		
		// session.invalidate();
//	 if(error != null){
//			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//		}
		// SECURITY V2
		model.addAttribute("msg", msg);
		model.addAttribute("marque", TDdao.getMarqueData("Ayush Portal"));
		model.addAttribute("langugae", "english");
		model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("ALL"));
		model.addAttribute("visiter_count", roleBaseDAO.VisitorCounter());
		request.getSession().setAttribute("visiter_count", roleBaseDAO.VisitorCounter());
		if (request.getHeader("Referer") == null) {
			session.invalidate();
			return new ModelAndView("landingpage");
		}
		if (msg != null) {

			HttpSession session1 = request.getSession(false);
			SecurityContextHolder.clearContext();
			session1 = request.getSession(false);
			if (session1.equals("Suspicious Activity Detected,You have been logged out by Administrator")) {

				System.err.println("----session1- -");
				session1.invalidate();
			}
			for (Cookie cookie : request.getCookies()) {
				cookie.setMaxAge(0);
			}

		}
//		model.addAttribute("msg", msg);
		
		
		return new ModelAndView("landingpage");
	}
	 
	 @GetMapping(value = "/landingpagehindi")
	    public String landingpagehindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
	    	model.addAttribute("msg", msg);
	    	model.addAttribute("langugae", "hindi");
	    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
	        return "landingpagehindi";
	    } 
    
    @GetMapping(value = "/ayurveda")
    public String ayurveda(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "AyurvedaPage";
    }
    
    @GetMapping(value = "/ayurvedahindi")
    public String ayurvedahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ayurvedahindi";
    }	    
    
    @GetMapping(value = "/unani")
    public String unani(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "unani";
    } 
    
    @GetMapping(value = "/unanihindi")
    public String unanihindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "unanihindi";
    } 
    
    @GetMapping(value = "/siddha")
    public String siddha(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "siddha";
    } 
    
    @GetMapping(value = "/siddhahindi")
    public String siddhahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "siddhahindi";
    } 
    
    @GetMapping(value = "/sowa")
    public String sowa(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "sowa";
    } 
    
    @GetMapping(value = "/sowahindi")
    public String sowahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "sowahindi";
    } 

    @GetMapping(value = "/homoeopathy")
    public String homoeopathy(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "homoeopathy";
    } 
    
    @GetMapping(value = "/homoeopathyhindi")
    public String homoeopathyhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "homoeopathyhindi";
    } 
    
   
    @GetMapping(value = "/aboutmoa")
	public ModelAndView aboutmoa(Model model,HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,HttpSession session
			) {
		
		try {
			
			model.addAttribute("msg", msg);
	    	model.addAttribute("langugae", "english");
	    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		if (request.getHeader("Referer") == null) {
			 return new ModelAndView("redirect:/landingpage");
	}
		HttpSession session1 = request.getSession(false);
		SecurityContextHolder.clearContext();
		session1 = request.getSession(false);
//			        if(session1.equals( "Suspicious Activity Detected,You have been logged out by Administrator")) {

		session1.invalidate();
//			        }
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
	
		return new ModelAndView("MoaPage");
	}
    @GetMapping(value = "/MoaPagehindi")
    public String MoaPagehindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "MoaPagehindi";
    }
    
    @GetMapping(value = "/ncismabout")
    public String ncismabout(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ncismabout";
    } 
    
    @GetMapping(value = "/abouthindincism")
    public String abouthindincism(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "abouthindincism";
    }
    
    @GetMapping(value = "/nchabout")
    public String nchabout(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "nchabout";
    } 
    
    @GetMapping(value = "/abouthindinch")
    public String abouthindinch(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
        return "abouthindinch";
    }	   
    
    @GetMapping(value = "/contactus")
    public String contactus(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "contactpage";
    }
    
  	//shivali_advanceSearch Enhancement_Research portal
  	@GetMapping(value = "/searchpageresult")
  	public String searchpageresult(Model model, @RequestParam(value = "msg", required = false) String msg,
  			@RequestParam(value = "search_box2", required = false) String search_box) {

  		System.err.println("SEARCH BOX------------" + search_box);

  		model.addAttribute("msg", msg);
  		model.addAttribute("langugae", "english");		
  		model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("ALL"));
  		model.addAttribute("size", 30); //Also define into twbsPaginationScript.js -- totalPages: 30
  		model.addAttribute("search_box", search_box);
  		model.addAttribute("getCategorylist", common.getCategorylist(sessionFactory));
  		model.addAttribute("getSearchFieldList", common.getSearchFieldList(sessionFactory));
  		model.addAttribute("getInstituteList", common.getInstituteList(sessionFactory));
  		model.addAttribute("getsysList", common.getsysList(sessionFactory));

  		return "advSp";
  	}	
    
    @GetMapping(value = "/contactpagehindi")
    public String contactpagehindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "contactpagehindi";
    } 	   
     
    
    @GetMapping(value = "/portalsignin")
	public ModelAndView portalsignin(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "msg", required = false) String msg,HttpSession session) {
		
		try {
			model.addAttribute("login_error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			    
			    
			    if (!authentication.getPrincipal().equals("anonymousUser")) {
			        new SecurityContextLogoutHandler().logout(request, response, authentication);
					 return new ModelAndView("redirect:/landingpage");
			    }
				model.addAttribute("msg", msg);
				model.addAttribute("langugae", "english");
				model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("ALL"));
				if (request.getHeader("Referer") == null) {
					
					session.invalidate();
					 return new ModelAndView("redirect:/landingpage");
					
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	//	session.invalidate();
//			        }
		
		return new ModelAndView("portalsignin");
	}
    /// 10-03-2023 
    @PostMapping(value = "/varifyAadharNumber")
   	public @ResponseBody String varifyAadharNumber(HttpServletRequest request,
   			@RequestParam(value = "msg", required = false) String msg,HttpSession session) {
   		
    	String aadhar_number = request.getParameter("aadhar_number");
    	Session sessionHQL = this.sessionFactory.openSession();
    	Boolean varified = (Boolean) sessionHQL.createQuery("select aadhaar_is_verified from UserLogin where aadhar_no=:aadhar_no")
		         .setParameter("aadhar_no", aadhar_number).setMaxResults(1).uniqueResult();
   		return String.valueOf(varified);
   	}
    
    @GetMapping(value = "/ayushknowledge")
    public String ayushknowledge(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ayushknowledge";
    } 
    
    @GetMapping(value = "/ayushknowledgehindi")
    public String ayushknowledgehindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ayushknowledgehindi";
    } 
    
    @GetMapping(value = "/ayushterminology")
    public String ayushterminology(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ayushterminology";
    } 
    
    @GetMapping(value = "/ayushterminologyhindi")
    public String ayushterminologyhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "hindi");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "ayushterminologyhindi";
    } 
    
    @GetMapping(value = "/portalforgotpassword")
    public String portalforgotpassword(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "portalforgotpassword";
    } 
    @GetMapping(value = "/websitepolicies")
    public String websitepolicies(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "websitepolicies";
    }
    
    @GetMapping(value = "/help")
	public String help(Model model, HttpSession session, @RequestParam(value = "msg", required = false) String msg,@RequestParam(value = "inqno", required = false) String inqno) {
		model.addAttribute("msg", msg);
		model.addAttribute("inqno", inqno);
		model.addAttribute("langugae", "english");
		model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("ALL"));
		model.addAttribute("MedStateName", common.getMedStateName(sessionFactory));
		model.addAttribute("getInq_CatList", common.getInq_CatList());
//		model.addAttribute("getSystemForHomeopathy", common.getSystemForHomeopathy(sessionFactory));
		model.addAttribute("getsysList", common.getsysList(sessionFactory));

		return "help";
	}
    
//    @GetMapping(value = "/feedback")
//    public String feedback(Model model,@RequestParam(value = "msg", required = false) String msg) {
//    	model.addAttribute("msg", msg);
//    	model.addAttribute("langugae", "english");
//    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//        return "feedback";
//    } 
    
    @GetMapping(value = "/termsandcondition")
    public String termsandcondition(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "termsandcondition";
    } 
    
    @GetMapping(value = "/disclaimer")
    public String disclaimer(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "disclaimer";
    } 
    
    @GetMapping(value = "/screenreaderaccess")
    public String screenreaderaccess(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "screenreaderaccess";
    }

 @GetMapping(value = "/sitemap")
    public String sitemap(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	model.addAttribute("langugae", "english");
    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
        return "sitemap";
  }
       
 @GetMapping(value = "/latestupdate")
 public String latestupdate(Model model,@RequestParam(value = "msg", required = false) String msg) {
 	model.addAttribute("msg", msg);
 	model.addAttribute("langugae", "english");
 	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
     return "latestupdate";
}
 
 
 @GetMapping(value = "/ayurvedacourse")
   public String ayurvedacourse(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
       return "ayurvedacourse";
   }
 
 @GetMapping(value = "/homoeopathycourse")
   public String homoeopathycourse(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
       return "homoeopathycourse";
   }
 
 @GetMapping(value = "/unanicourse")
   public String unanicourse(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
       return "unanicourse";
   }
 
 @GetMapping(value = "/siddhacourse")
   public String siddhacourse(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
       return "siddhacourse";
   }
 
 @GetMapping(value = "/sowacourse")
   public String sowacourse(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
       return "sowacourse";
   }
 
 @GetMapping(value = "/ayurvedacoursencism")
   public String ayurvedacoursencism(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
       return "ayurvedacoursencism";
   } 
 
 @GetMapping(value = "/unanicoursencism")
   public String unanicoursencism(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
       return "unanicoursencism";
   } 
 
 @GetMapping(value = "/siddhacoursencism")
   public String siddhacoursencism(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
       return "siddhacoursencism";
   } 
 
 @GetMapping(value = "/sowacoursencism")
   public String sowacoursencism(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
       return "sowacoursencism";
   } 
 
 @GetMapping(value = "/homoeopathycoursench")
   public String homoeopathycoursench(Model model,@RequestParam(value = "msg", required = false) String msg) {
   	model.addAttribute("msg", msg);
   	model.addAttribute("langugae", "english");
   	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
       return "homoeopathycoursench";
   } 





    
    /* ##Ayush Portal Landing and inner pages layout end (English/Hindi)-Dark Blue theme## */	
    
	    
    /* ##NCISM Landing and inner pages layout start (English/Hindi)-Green theme## */
		
		 @GetMapping(value = "/ncismlanding")
		    public ModelAndView ncismlanding(Model model,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		    	
		    	System.err.println("------msg - "+msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		    	model.addAttribute("marque", TDdao.getMarqueData("NCISM Portal"));
		    	if (request.getHeader("Referer") == null) {
		    		 return new ModelAndView("redirect:/landingpage");
				}
		    	model.addAttribute("msg", msg);
		    	HttpSession session1 = request.getSession(false);
				SecurityContextHolder.clearContext();
				session1 = request.getSession(false);
//					        if(session1.equals( "Suspicious Activity Detected,You have been logged out by Administrator")) {

				session1.invalidate();
		    	 return new ModelAndView("ncismlandingpage");
		    }
		    
		    @GetMapping(value = "/ncismlandinghindi")
		    public String ncismlandinghindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismlandinghindi";
		    }		 
			
		    @GetMapping(value = "/ncismayurveda")
		    public String ncismayurveda(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismayurveda";
		    }
		    
		    @GetMapping(value = "/ncismayurvedahindi")
		    public String ncismgayurvedahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismayurvedahindi";
		    }
		    @GetMapping(value = "/ayushprakritinch")
		    public String ayushprakritinch(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "ayushprakritinch";
		    }
		    
		    @GetMapping(value = "/ayushprakritincism")
		    public String ayushprakritincism(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ayushprakritincism";
		    } 
		    
		    @GetMapping(value = "/ayushprakriti")
		    public String ayushprakriti(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "ayushprakriti";
		    } 
		    @GetMapping(value = "/ncismunani")
		    public String ncismunani(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismunani";
		    } 
		    
		    @GetMapping(value = "/ncismunanihindi")
		    public String ncismunanihindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismunanihindi";
		    } 
			  
		    @GetMapping(value = "/ncismsiddha")
		    public String ncismsiddha(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismsiddha";
		    } 
		    
		    @GetMapping(value = "/ncismsiddhahindi")
		    public String ncismsiddhahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismsiddhahindi";
		    } 
		    
		    @GetMapping(value = "/ncismsowa")
		    public String ncismsowa(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismsowa";
		    } 
		    
		    @GetMapping(value = "/ncismsowahindi")
		    public String ncismsowahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismsowahindi";
		    } 
		    
		    @GetMapping(value = "/ncismhomoeopathy")
		    public String ncismhomoeopathy(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismhomoeopathy";
		    } 
		    
		    @GetMapping(value = "/ncismhomoeopathyhindi")
		    public String ncismhomoeopathyhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismhomoeopathyhindi";
		    } 
		    
		    @GetMapping(value = "/aboutncism")
		    public String aboutncism(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "aboutncism";
		    }
		    
		    @GetMapping(value = "/aboutncismhindi")
		    public String aboutncismhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "NCISMPagehindi";
		    } 
		    
		    @GetMapping(value = "/ncismcontact")
		    public String ncismcontact(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismcontact";
		    } 
		    
		    @GetMapping(value = "/ncismcontacthindi")
		    public String ncismcontacthindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismcontacthindi";
		    }
		    
		    @GetMapping(value = "/ayushknowledgencism")
		    public String ayushknowledgencism(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ayushknowledgencism";
		    } 
		    
		    @GetMapping(value = "/ayushknowledgencismhindi")
		    public String ayushknowledgencismhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ayushknowledgencismhindi";
		    } 
		    
		    @GetMapping(value = "/ayushterminologyncism")
		    public String ayushterminologyncism(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ayushterminologyncism";
		    } 
		    
		    @GetMapping(value = "/ayushterminologyncismhindi")
		    public String ayushterminologyncismhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ayushterminologyncismhindi";
		    } 
		    
		    @GetMapping(value = "/ncismtermsandcondition")
		    public String ncismtermsandcondition(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismtermsandcondition";
		    } 
		    
		    @GetMapping(value = "/ncismannualreport")
		    public String ncismannualreport(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismannualreport";
		    } 
		    
		    @GetMapping(value = "/ncismfeedback")
		    public String ncismfeedback(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismfeedback";
		    } 

		    @GetMapping(value = "/ncismscreenreaderaccess")
		    public String ncismscreenreaderaccess(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismscreenreaderaccess";
		    } 

 			@GetMapping(value = "/ncismsitemap")
		    public String ncismsitemap(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismsitemap";
		    } 		    

		    @GetMapping(value = "/ncismlatestupdate")
		    public String ncismlatestupdate(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		        return "ncismlatestupdate";
		    } 
		    
		    /* ##NCISM Landing and inner pages layout end (English/Hindi)-Green theme## */
		    
		    /* ##NCH Landing and inner pages layout start (English/Hindi)-Dark Red theme## */
		    
//		    @GetMapping(value = "/nchlanding")
//		      public String nchlanding(Model model,@RequestParam(value = "msg", required = false) String msg) {
//		      	model.addAttribute("msg", msg);
//		      	model.addAttribute("marque", TDdao.getMarqueData("NCH Portal"));
//		      	model.addAttribute("langugae", "english");
//		      	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
//		          return "nchlandingpage";
//		      }
//		
		    @GetMapping(value = "/nchlanding")
			public ModelAndView nchlanding(Model model,HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg,HttpSession session1
					) {
				
				try {
					
			      	model.addAttribute("marque", TDdao.getMarqueData("NCH Portal"));
			      	model.addAttribute("langugae", "english");
			      	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				model.addAttribute("msg", msg);
				if (request.getHeader("Referer") == null) {
					 return new ModelAndView("redirect:/landingpage");
			}
				HttpSession session = request.getSession(false);
				SecurityContextHolder.clearContext();
				session = request.getSession(false);
//					        if(session1.equals( "Suspicious Activity Detected,You have been logged out by Administrator")) {

				session.invalidate();
//					        }
				for (Cookie cookie : request.getCookies()) {
					cookie.setMaxAge(0);
				}
				return new ModelAndView("nchlandingpage");
			}
		    
		    @GetMapping(value = "/nchlandinghindi")
		    public String nchlandinghindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchlandinghindi";
		    }
		    
		    @GetMapping(value = "/nchayurveda")
		    public String ncgayurveda(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchayurveda";
		    }
		    
		    @GetMapping(value = "/nchayurvedahindi")
		    public String ncgayurvedahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchayurvedahindi";
		    }
		    
		    @GetMapping(value = "/nchunani")
		    public String nchunani(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchunani";
		    } 
		    
		    @GetMapping(value = "/nchunanihindi")
		    public String nchunanihindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchunanihindi";
		    } 
		    
		    @GetMapping(value = "/nchsiddha")
		    public String nchsiddha(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchsiddha";
		    } 
		    @GetMapping(value = "/nchsiddhahindi")
		    public String nchsiddhahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchsiddhahindi";
		    } 
		    
		    @GetMapping(value = "/nchsowa")
		    public String nchsowa(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchsowa";
		    } 
		    @GetMapping(value = "/nchsowahindi")
		    public String nchsowahindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchsowahindi";
		    } 
		    
		    @GetMapping(value = "/nchhomoeopathy")
		    public String nchhomoeopathy(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchhomoeopathy";
		    } 
		    @GetMapping(value = "/nchhomoeopathyhindi")
		    public String nchhomoeopathyhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchhomoeopathyhindi";
		    } 
		    
		    @GetMapping(value = "/aboutnch")
		    public String aboutnch(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "NCHPage";
		    }
		    @GetMapping(value = "/aboutnchhindi")
		    public String aboutnchhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "NCHPagehindi";
		    }
		    
		    @GetMapping(value = "/nchcontact")
		    public String nchcontact(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchcontact";
		    }
		    @GetMapping(value = "/nchcontacthindi")
		    public String nchcontacthindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "nchcontacthindi";
		    }
		    
		    @GetMapping(value = "/ayushknowledgench")
		    public String ayushknowledgench(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "ayushknowledgench";
		    } 			    
		    @GetMapping(value = "/ayushknowledgenchhindi")
		    public String ayushknowledgenchhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "ayushknowledgenchhindi";
		    } 
		    
		    @GetMapping(value = "/ayushterminologynch")
		    public String ayushterminologynch(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "ayushterminologynch";
		    } 
		    @GetMapping(value = "/ayushterminologynchhindi")
		    public String ayushterminologynchhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "hindi");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		        return "ayushterminologynchhindi";
		    } 
		    @GetMapping(value = "/nchwebsitepolicies")
		    public String nchwebsitepolicies(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchwebsitepolicies";
		    }
		    
		    @GetMapping(value = "/nchhelp")
		    public String nchhelp(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchhelp";
		    }
		    
		    @GetMapping(value = "/nchtermsandcondition")
		    public String nchtermsandcondition(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchtermsandcondition";
		    }
		    
		    @GetMapping(value = "/nchfeedback")
		    public String nchfeedback(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchfeedback";
		    }

		    @GetMapping(value = "/nchscreenreaderaccess")
		    public String nchscreenreaderaccess(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchscreenreaderaccess";
		    }


		    @GetMapping(value = "/nchsitemap")
		    public String nchsitemap(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchsitemap";
		    }
		    
		    @GetMapping(value = "/nchlatestupdate")
		    public String nchlatestupdate(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "nchlatestupdate";
		   }

		    
		 /* ##NCH Landing and inner pages layout end (English/Hindi)-Dark Red theme## */
    
    /* ################### Layout rootfiles End here #################### */
		    
		    /* ## Ayush Portal User Guide start ## */
		    @GetMapping(value = "/usermanual_main")
		    public String usermanual_main(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "usermanual_main";
		    }

		    @GetMapping(value = "/helptopics")
		    public String helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "helptopics";
		    }
		    
		    @GetMapping(value = "/signin_common_usermanual")
		    public String signin_common_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "signin_common_usermanual";
		    }

		    @GetMapping(value = "/signin_diff_usermanual")
		    public String signin_diff_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		    	model.addAttribute("msg", msg);
		    	model.addAttribute("langugae", "english");
		    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
		        return "signin_diff_usermanual";
		    }

		    /*NCISM userguide start*/
		    @GetMapping(value = "/ncism_usermanual_main")
		    public ModelAndView ncism_usermanual_main(Model model, HttpServletRequest request, HttpServletResponse response,
		    		@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		    	try {
		    		model.addAttribute("msg", msg);
		        	model.addAttribute("langugae", "english");
		        	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		    		if (request.getHeader("Referer") == null) {
		    			session.invalidate();
		    			return new ModelAndView("landingpage");
		    		}
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return new ModelAndView("ncism_usermanual_main");
		    }

		    @GetMapping(value = "/ncism_helptopics")
		    public ModelAndView ncism_helptopics(Model model, HttpServletRequest request, HttpServletResponse response,
		    		@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		    	try {
		    		model.addAttribute("msg", msg);
		        	model.addAttribute("langugae", "english");
		        	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCISM"));
		    		if (request.getHeader("Referer") == null) {
		    			session.invalidate();
		    			return new ModelAndView("landingpage");
		    		}
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return new ModelAndView("ncism_helptopics");
		    }

		    @GetMapping(value = "/ncism_outer_inst_admission_usermanual")
		    public ModelAndView ncism_outer_inst_admission_usermanual(Model model, HttpServletRequest request, HttpServletResponse response,
		    		@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		    	try {
		    		model.addAttribute("msg", msg);
		    		model.addAttribute("langugae", "english");
		    		model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("NCISM"));
		    		if (request.getHeader("Referer") == null) {
		    			session.invalidate();
		    			return new ModelAndView("landingpage");
		    		}
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return new ModelAndView("ncism_outer_inst_admission_usermanual");
		    }


		    /* NCISM userguide end */

		    /*NCH userguide start*/
		    @GetMapping(value = "/nch_usermanual_main")
		    public ModelAndView nch_usermanual_main(Model model, HttpServletRequest request, HttpServletResponse response,
		    		@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		    	try {
		    		model.addAttribute("msg", msg);
		        	model.addAttribute("langugae", "english");
		        	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		    		if (request.getHeader("Referer") == null) {
		    			session.invalidate();
		    			return new ModelAndView("landingpage");
		    		}
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return new ModelAndView("nch_usermanual_main");
		    }
		    @GetMapping(value = "/nch_helptopics")
		    public ModelAndView nch_helptopics(Model model, HttpServletRequest request, HttpServletResponse response,
		    		@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		    	try {
		    		model.addAttribute("msg", msg);
		        	model.addAttribute("langugae", "english");
		        	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("NCH"));
		    		if (request.getHeader("Referer") == null) {
		    			session.invalidate();
		    			return new ModelAndView("landingpage");
		    		}
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return new ModelAndView("nch_helptopics");
		    }

		    /* NCH userguide end */

		    /* ## Ayush Portal User Guide End ## */	

		    /* ## Ayush Portal dashboard - User Guide Start ## */

		    /* NCISM db-userguide start */
		    @GetMapping(value = "admin/ncism_inst_usermanual_main")
		    public ModelAndView ncism_inst_usermanual_main(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("ncism_inst_usermanual_main");
		    }

		    @GetMapping(value = "admin/ncism_inst_helptopics")
		    public ModelAndView ncism_inst_helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("ncism_inst_helptopics");
		    }

		    @GetMapping(value = "admin/ncism_inst_admission_usermanual")
		    public ModelAndView ncism_inst_admission_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("ncism_inst_admission_usermanual");
		    }
		    
		    @GetMapping(value = "admin/ncism_inst_pmulti_ucreate_usermanual")
		    public ModelAndView ncism_inst_pmulti_ucreate_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("ncism_inst_pmulti_ucreate_usermanual");
		    }
		    
		    @GetMapping(value = "admin/ncism_princ_helptopics")
		    public ModelAndView ncism_princ_helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("ncism_princ_helptopics");
		    }
		    /* NCISM db-userguide end */

		    /* NCH db-userguide start */
		    @GetMapping(value = "admin/nch_pract_usermanual_main")
		    public ModelAndView nch_pract_usermanual_main(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_pract_usermanual_main");
		    }	    		  

		    @GetMapping(value = "admin/nch_pract_helptopics")
		    public ModelAndView nch_pract_helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_pract_helptopics");
		    }
		    
		    @GetMapping(value = "admin/nch_inst_usermanual_main")
		    public ModelAndView nch_inst_usermanual_main(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_inst_usermanual_main");
		    }
		    
		    @GetMapping(value = "admin/nch_inst_helptopics")
		    public ModelAndView nch_inst_helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_inst_helptopics");
		    }
		    
		    @GetMapping(value = "admin/nch_facu_usermanual_main")
		    public ModelAndView nch_facu_usermanual_main(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_facu_usermanual_main");
		    }
		    
		    @GetMapping(value = "admin/nch_facu_helptopics")
		    public ModelAndView nch_facu_helptopics(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_facu_helptopics");
		    }
		    
		    @GetMapping(value = "admin/nch_facu_usermanual")
		    public ModelAndView nch_facu_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_facu_usermanual");
		    }
		    
		    @GetMapping(value = "admin/nch_facu_detailupload_usermanual")
		    public ModelAndView nch_facu_detailupload_usermanual(Model model,@RequestParam(value = "msg", required = false) String msg) {
		        return new ModelAndView("nch_facu_detailupload_usermanual");
		    }
		    /* NCH db-userguide end */

		    /* ## Ayush Portal dashboard - User Guide End ## */  
		    

		    /* ## Ayush Portal  - Error Page Start ## */  
		  		    
		  		    @GetMapping(value = "/commonerrorpage")
		  		    public String commonerrorpage(Model model,@RequestParam(value = "msg", required = false) String msg) {
		  		   		model.addAttribute("msg", msg);
		  		        return "commonerrorpage";
		  		    } 
		  		    
		  		    @GetMapping(value = "/reg_errorpage")
		  		    public String reg_errorpage(Model model,@RequestParam(value = "msg", required = false) String msg) {
		  		   		model.addAttribute("msg", msg);
		  		        return "reg_errorpage";
		  		    } 
		  		    
		  		    /* ## Ayush Portal  - Error Page End ## */  
		    
		  		 /* ## Dashboard Helpdesk Start ## */  
		  		    
//			  		@GetMapping(value = "admin/db_helpdesk")
//				    public ModelAndView db_helpdesk(Model model,@RequestParam(value = "msg", required = false) String msg) {
//				        return new ModelAndView("db_helpdesk");
//				    }
			  		   
			  	/* ## Dashboard Helpdesk End ## */  
		    
    /* ######################################################### Pooja 16-12-2022 - check below controller arrange with proper comment ########################################################## */	    
   
    
	/*
	 * @GetMapping(value = "/landingpage") public String landingpage(Model
	 * model,@RequestParam(value = "msg", required = false) String msg) {
	 * model.addAttribute("msg", msg);
	 * model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL")); return
	 * "landingpage"; }
	 * 
	 * @GetMapping(value = "/portalsignin") public String portalsignin(Model
	 * model,@RequestParam(value = "msg", required = false) String msg) {
	 * model.addAttribute("msg", msg);
	 * model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL")); return
	 * "portalsignin"; }
	 * 
	 * @GetMapping(value = "/portalsignup") public String portalsignup(Model
	 * model,@RequestParam(value = "msg", required = false) String msg) {
	 * model.addAttribute("msg", msg);
	 * model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL")); return
	 * "portalsignup"; }
	 * 
	 * @GetMapping(value = "/portalforgotpassword") public String
	 * portalforgotpassword(Model model,@RequestParam(value = "msg", required =
	 * false) String msg) { model.addAttribute("msg", msg);
	 * model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL")); return
	 * "portalforgotpassword"; }
	 */  
    
    
//    @GetMapping(value = "/ptLogin")
//	public String ptLogin(Model model,@RequestParam(value = "msg", required = false) String msg) {
//
//	    	model.addAttribute("msg", msg);
//
//
//		return "ptLogin";
//	}
    
    @GetMapping(value = "/signup")
    public String signup(ModelMap model,HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request 
) {
//    	model.addAttribute("msg", msg);
//    	model.put("getInstituteList", common.getInstituteList(sessionFactory));
//   	 model.put("getInstituteList", common.getInstituteList(sessionFactory));
//   	 model.put("MedStateName", common.getMedStateName(sessionFactory));
//   	 
   	model.addAttribute("msg", msg);
	 model.put("getInstituteList", common.getInstituteList(sessionFactory));
	model.put("MedStateName", common.getMedStateName(sessionFactory));
	model.put("DistrictName", common.getMedDistrictName(sessionFactory));
	 model.put("msg", msg);
        return "signup";
    }
    
    @GetMapping(value = "/institute_registration_urlhindi")
    public String institute_registration_urlhindi(Model model,@RequestParam(value = "msg", required = false) String msg) {
    	model.addAttribute("msg", msg);
    	 model.addAttribute("MedCountryName", common.getMedCountryName(sessionFactory));
		 model.addAttribute("MedStateName", common.getMedStateName(sessionFactory));
		 model.addAttribute("MedDistrictName", common.getMedDistrictName(sessionFactory));
		 model.addAttribute("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 model.addAttribute("getUniversityList", common.getUniversityList(sessionFactory));
    	
        return "institute_registration_urlhindi";
    } 
	
	@RequestMapping(value = {"/nchsignin" }, method = RequestMethod.GET)
	public ModelAndView nchsignin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		System.err.println("login sessin:"+this.sessionFactory);
		
		/*System.out.println("error=="+error);
		System.out.println("msg=="+msg);
		System.out.println("logout=="+logout);*/
		ModelAndView model = new ModelAndView();
		
		if (error != null) {
			System.out.println("Test 1");
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			System.out.println("Test 2");
			if (request.getHeader("Referer") != null) {
				model.addObject("msg", "You are logged out successfully.");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Role = "";
		if (!authentication.getName().equals("anonymousUser")) {
			System.out.println("Test 3");
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String role1 = "";
			for (String role : roles) {
				role1 = role;
			}
			System.out.println(role1);
			Role = role1;
		}
		if (!Role.equals("")) {
			return new ModelAndView("redirect:/admin/commonDashboard");
		} else {
			String layout = "";
			//List<String> msgLayout = roleBaseDAO.getLayoutlist();
			layout += "<h3>";
//			for (int m = 0; m < msgLayout.size(); m++) {
//				if (m == 0) {
//					layout += msgLayout.get(m);
//				} else {
//					layout += " | " + msgLayout.get(m);
//				}
//			}
			layout += "</h3>";
			model.addObject("layout", layout);
		//	model.addObject("capchaImage", createImage(request));
			model.addObject("visiter_count", roleBaseDAO.VisitorCounter());
		//	model.addObject("sub_access_lvl", common.sub_access_lvl(sessionFactory));
			model.addObject("server","");
			model.setViewName("nchsignin");
		}
		return model;
	}
	
	/* ncism signin */
	

	
	

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		System.err.println("login sessin:"+this.sessionFactory);
		
		/*System.out.println("error=="+error);
		System.out.println("msg=="+msg);
		System.out.println("logout=="+logout);*/
		ModelAndView model = new ModelAndView();
		
		if (error != null) {
			System.out.println("Test 1");
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			System.out.println("Test 2");
			if (request.getHeader("Referer") != null) {
				model.addObject("msg", "You are logged out successfully.");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Role = "";
		if (!authentication.getName().equals("anonymousUser")) {
			System.out.println("Test 3");
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String role1 = "";
			for (String role : roles) {
				role1 = role;
			}
			System.out.println(role1);
			Role = role1;
		}
		if (!Role.equals("")) {
			return new ModelAndView("redirect:/admin/commonDashboard");
		} else {
			String layout = "";
			//List<String> msgLayout = roleBaseDAO.getLayoutlist();
			layout += "<h3>";
//			for (int m = 0; m < msgLayout.size(); m++) {
//				if (m == 0) {
//					layout += msgLayout.get(m);
//				} else {
//					layout += " | " + msgLayout.get(m);
//				}
//			}
			layout += "</h3>";
			model.addObject("layout", layout);
		//	model.addObject("capchaImage", createImage(request));
			model.addObject("visiter_count", roleBaseDAO.VisitorCounter());
			model.addObject("server","");
			model.setViewName("login");
		}
		return model;
	}

	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public ModelAndView logoutDo(HttpServletRequest request,HttpServletResponse response){
		System.out.println("logout");
	HttpSession session= request.getSession(false);
	    SecurityContextHolder.clearContext();
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
	        for(Cookie cookie : request.getCookies()) {
	            cookie.setMaxAge(0);
	        }
	        ModelAndView model = new ModelAndView();
	        model.addObject("logout","logout");
			model.setViewName("login");
			
	    return model;
	}
	// customize the error message
	public static String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		System.out.println(exception);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof SessionAuthenticationException) {
				exception.printStackTrace();
			error = "User Already logged in";// exception.getMessage();
		}else if (exception instanceof DisabledException) {
			error = "User is disabled";
		}else {
			error = "Invalid username and password!";
		}
		return error;
	}
		
	// for 403 access denied page
	@RequestMapping(value = "/user/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}

	// Create Capcha Code
	/*@RequestMapping(value = "/capchaCode", method = RequestMethod.POST)
	public @ResponseBody List<String> capchaCode(HttpServletRequest request) {
		List<String> capchaList = new ArrayList<String>();
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomCharacter());

		String capcha = "";
		for (int i = 0; i < capchaList.size(); i++) {
			capcha += capchaList.get(i);
		}
		HttpSession session = request.getSession();
		session.setAttribute("capcha", capcha);
		return capchaList;
	}*/
	
/*	@RequestMapping(value = "/genCapchaCode", method = RequestMethod.POST)
	public @ResponseBody byte[] genCapchaCode(HttpServletRequest request) {
		byte[] image = createImage(request);
		if(!image.equals("")) {
			return image;
		}else {
			return null;
		}
	}*/
	
	//@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/genCapchaCode")
	public @ResponseBody  byte[] genCapchaCode1(HttpServletRequest request) {
		byte[]  image = createImage(request);
		if(!image.toString().equals("")) {
			return image;
		}else {
			return null;
		}
	}
	
	
	@RequestMapping(value = "/checkCapchaCode", method = RequestMethod.POST)
	public @ResponseBody boolean checkCapchaCode(HttpServletRequest request,String iCapcha) {
		String txtInput ="";
		String capcha ="";
		try {
			  txtInput = iCapcha.replaceAll("\\s","").toString();
			  capcha = request.getSession().getAttribute("capcha").toString();
			if(txtInput.equals(capcha)){
				 return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	String captchaString = "";
	private  byte[] createImage(HttpServletRequest request) {
		try {
            Color backgroundColor = Color.white;
            Color borderColor = Color.black;
            Color textColor = Color.black;
            Color circleColor = new Color(190, 160, 150);
            Font textFont = new Font("Verdana", Font.BOLD, 30);
            int charsToPrint = 5;
            int width = 150;
            int height = 50;
            int circlesToDraw = 25;
            float horizMargin = 10.0f;
            double rotationRange = 0.7; 
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);

            // lets make some noisey circles
            g.setColor(circleColor);
            for (int i = 0; i < circlesToDraw; i++) {
                int L = (int) (Math.random() * height / 2.0);
                int X = (int) (Math.random() * width - L);
                int Y = (int) (Math.random() * height - L);
                g.draw3DRect(X, Y, L * 2, L * 2, true);
            }
            g.setColor(textColor);
            g.setFont(textFont);
            FontMetrics fontMetrics = g.getFontMetrics();
            int maxAdvance = fontMetrics.getMaxAdvance();
            int fontHeight = fontMetrics.getHeight();

            // i removed 1 and l and i because there are confusing to users...
            // Z, z, and N also get confusing when rotated
            // this should ideally be done for every language...
            // 0, O and o removed because there are confusing to users...
            // i like controlling the characters though because it helps prevent confusion
            String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
            char[] chars = elegibleChars.toCharArray();
            float spaceForLetters = -horizMargin * 3 + width;
            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
            StringBuffer finalString = new StringBuffer();
            for (int i = 0; i < charsToPrint; i++) {
                double randomValue = Math.random();
                int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
                char characterToShow = chars[randomIndex];
                finalString.append(characterToShow);

                // this is a separate canvas used for the character so that
                // we can rotate it independently
                int charWidth = fontMetrics.charWidth(characterToShow);
                int charDim = Math.max(maxAdvance, fontHeight);
                int halfCharDim = (int) (charDim / 2);
                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
                Graphics2D charGraphics = charImage.createGraphics();
                charGraphics.translate(halfCharDim, halfCharDim);
                double angle = (Math.random() - 0.5) * rotationRange;
                charGraphics.transform(AffineTransform.getRotateInstance(angle));
                charGraphics.translate(-halfCharDim, -halfCharDim);
                charGraphics.setColor(textColor);
                charGraphics.setFont(textFont);
                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
                float x = horizMargin + spacePerChar * (i) - charDim / 2.5f;
                int y = (int) ((height - charDim) / 2);
                g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
                charGraphics.dispose();
            }
            g.setColor(borderColor);
            g.drawRect(0, 0, width - 1, height - 1);
            g.dispose();
            captchaString = finalString.toString();
            
           // return bufferedImage;
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            try {
    			ImageIO.write(bufferedImage, "jpg", baos1);
    			baos1.flush();
    			baos1.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            byte[] imageInByteArray = baos1.toByteArray();
            HttpSession session = request.getSession();
            System.out.println(captchaString);
    		session.setAttribute("capcha", captchaString);
    		return imageInByteArray;
    	} catch (Exception ioe) {
        	byte[] imageInByteArray = null;
            return imageInByteArray;
        }
	}
	
	/*public static String getRandomInteger(int maximum, int minimum) {
		return String.valueOf(((int) (Math.random() * (maximum - minimum))) + minimum);
	}

	public static String getRandomCharacter() {
		String AlphaNumericString = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghjklmnopqrstuvxyz";
		int index = (int) (AlphaNumericString.length() * Math.random());
		return String.valueOf(AlphaNumericString.charAt(index));
	}*/
	
	/*@RequestMapping(value = "/JnlpDashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		System.out.println("JnlpDashboard");
		return new ModelAndView("JnlpDashboardTiles");
	}*/
	
	public String getServerIP() {
		try(final  DatagramSocket s = new DatagramSocket()){
			try {
				s.connect(InetAddress.getByName("8.8.8.8"),10002);
				String hadd = s.getLocalAddress().getHostAddress();
				System.out.println("IP="+hadd);
				if(hadd.equals("152.1.13.51")) {
					return "Server 1";
				}else if(hadd.equals("152.1.13.52")) {
					return "Server 2";
				}else if(hadd.equals("152.1.13.53")) {
					return "Server 3";
				}else {
					return "Unknown Server";
				}
			} catch (UnknownHostException e) {
				return "Unknown Server";
			}
		} catch (SocketException e1) {
			return "Unknown Server";
		}
	}
	@RequestMapping(value = { "/", "/ncismsignin" }, method = RequestMethod.GET)
	public ModelAndView ncismsignin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		System.err.println("login sessin:"+this.sessionFactory);
		
		/*System.out.println("error=="+error);
		System.out.println("msg=="+msg);
		System.out.println("logout=="+logout);*/
		ModelAndView model = new ModelAndView();
		if (error != null) {
			System.out.println("Test 1");
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			System.out.println("Test 2");
			if (request.getHeader("Referer") != null) {
				model.addObject("msg", "You are logged out successfully.");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Role = "";
		if (!authentication.getName().equals("anonymousUser")) {
			System.out.println("Test 3");
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String role1 = "";
			for (String role : roles) {
				role1 = role;
			}
			System.out.println(role1);
			Role = role1;
		}
		if (!Role.equals("")) {
			return new ModelAndView("redirect:/admin/commonDashboard");
		} else {
			String layout = "";
			//List<String> msgLayout = roleBaseDAO.getLayoutlist();
			layout += "<h3>";
//			for (int m = 0; m < msgLayout.size(); m++) {
//				if (m == 0) {
//					layout += msgLayout.get(m);
//				} else {
//					layout += " | " + msgLayout.get(m);
//				}
//			}
			layout += "</h3>";
			model.addObject("layout", layout);
		//	model.addObject("capchaImage", createImage(request));
			model.addObject("visiter_count", roleBaseDAO.VisitorCounter());
			model.addObject("server","");
			model.setViewName("ncismsignin");
		}
		return model;
	}
	// HET

			@GetMapping(value = "/ptLogin")
			public String ptLogin(Model model, @RequestParam(value = "msg", required = false) String msg) {

				model.addAttribute("msg", msg);

				return "ptLogin";
			}

		

			@RequestMapping(value = "/VerifyOTP", method = RequestMethod.POST)
			public @ResponseBody Boolean VerifyOTP(HttpServletRequest request, String aadhar_no, String otpField,String mobile_no,String login_role) {
//				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//				String hashedaadhar_no = passwordEncoder.encode(aadhar_no);
//				Session sessionHQL = sessionFactory.openSession();
//				Transaction tx = sessionHQL.beginTransaction();
//				Query q = sessionHQL.createQuery("from UserLogin where username=:username order by id DESC")
//						.setParameter("username", aadhar_no).setMaxResults(1);
//				
//				System.err.println("========="+mobile_no);
//				@SuppressWarnings("unchecked")
//				List<UserLogin> clist = (List<UserLogin>) q.list();
//				tx.commit();
//				sessionHQL.close();
//
////				System.err.println("========="+mobile_no+"-----------------------------"+clist.get(0));
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
//
//			boolean isPasswordMatches =	encoder.matches(otpField,clist.get(0).getPassword()); 

				try { 
					
				}catch (Exception e) {
					e.printStackTrace();
				}
								
						return true;	
				
////				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
////				boolean isPasswordMatches =	encoder.matches(clist.get(0).getUserName(),"$2a$10$dU.0G91e9Y/MBIFt7z0GkOvI.HV53t0WzLX/Y7IYG3SBf/67Os.2u"); 
		//
		////System.err.println("============="+isPasswordMatches);
//				String hashedPassword = passwordEncoder.encode(otpField);
//				if (clist.get(0).getPassword().equals(hashedPassword)) {
//					return true;
//				} else {
//					return false;
//				}

			}

			public static String genrateOTP1(final int lengthOfOTP) {

				StringBuilder generatedOTP = new StringBuilder();
				SecureRandom secureRandom = new SecureRandom();

				try {

					secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

					for (int i = 0; i < lengthOfOTP; i++) {
						generatedOTP.append(secureRandom.nextInt(9));
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

				return generatedOTP.toString();
			}
			
			@RequestMapping(value = "/genrateOTP", method = RequestMethod.POST)
			public @ResponseBody String genrateOTP(HttpServletRequest request, String aadhar_no,String mobile_no,String practfactnch ) {
//				String OTP = genrateOTP1(6);
				String OTP = "110599";
				
//				if (practfactnch.toUpperCase().equals("25")) {
//					OTP = genrateOTP1(6);
//				}
				

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				request.getSession().setAttribute("login_role", practfactnch);
				String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//				String hashedaadhar_no = passwordEncoder.encode(aadhar_no);
				
			
//					Query query = sessionHQL.createQuery("select count(userId) from UserLogin where username=:username")
//							.setParameter("username", aadhar_no);
//					
//					@SuppressWarnings("unchecked")
//					Long data = (Long) query.uniqueResult();
//					
//				
//					sessionHQL.flush();
//					sessionHQL.clear();
//
//					System.err.println("===data======"+data);
//
//				
//				
//				if(data == 0) {
//				
//				UserLogin p = new UserLogin();
//				
////				System.err.println("======"+aadhar_no+"==========="+aadhar_no.length()+"======="+hashedaadhar_no+"========"+hashedaadhar_no.length());
//
//				p.setUserName(aadhar_no);
////				System.err.println("========"+aadhar_no+"=========="+aadhar_no);
//				//p.setPract_type(Integer.parseInt(pract_type));
//				String hashedPassword = passwordEncoder.encode(OTP);
//				p.setPassword(hashedPassword);
//				p.setEnabled(1);
//				p.setAccountNonExpired(1);
//				p.setAccountNonLocked(1);
//				p.setCredentialsNonExpired(1);
//				p.setAc_dc_date(modifydate);
//				//p.setMobile_no(mobile_no);
//				p.setCreated_on(new Date());
//				UserRole role_tbl = new UserRole();
//				// sessionHQL.beginTransaction();
//
//				int did = (Integer) sessionHQL.save(p);
////				role_tbl.setRoleId(27);
//				role_tbl.setRoleId(Integer.parseInt(practfactnch));
//				role_tbl.setUserId(did);
//				sessionHQL.save(role_tbl);
//				sessionHQL.flush();
//				sessionHQL.clear();
//				}else {
				Query	query = sessionHQL.createQuery("select cast(userId as text) from UserLogin where username=:username")
								.setParameter("username", aadhar_no);
						
						System.err.println("===sss====="+aadhar_no.replace("-", ""));
						@SuppressWarnings("unchecked")
//						int data2 = (int) query.uniqueResult();
						List<String> data2 = (List<String>) query.list();
						
						if(data2.size() == 0) {
							return "User Not Found";
						}
						
	 					sessionHQL.flush();
						sessionHQL.clear();
						
						int uidfromdata2 = Integer.parseInt(data2.get(0));
						
					UserLogin INF= (UserLogin)sessionHQL.get(UserLogin.class,uidfromdata2);
					
//					System.err.println("======"+aadhar_no+"==========="+aadhar_no.length()+"======="+hashedaadhar_no+"========"+hashedaadhar_no.length());

					INF.setUserName(aadhar_no);
//					System.err.println("========"+aadhar_no+"=========="+aadhar_no);

					String hashedPassword = passwordEncoder.encode(OTP);
					String role="";
					
					Query	query1 = sessionHQL.createQuery("select roleId from UserRole where userId=:user_id")
							.setParameter("user_id", uidfromdata2);
					
					@SuppressWarnings("unchecked")
					int role_id = (int) query1.uniqueResult();
					
					if(role_id == 25 || role_id == 26 ||  role_id == 35  || role_id == 40 || role_id == 23 || role_id == 24|| role_id == 27 || role_id == 28 || role_id == 42 || role_id == 44|| role_id == 45|| role_id == 46) {
					INF.setPassword(hashedPassword);
					}
					INF.setEnabled(1);
					INF.setAccountNonExpired(1);
					INF.setAccountNonLocked(1);
					INF.setCredentialsNonExpired(1);
					INF.setAc_dc_date(modifydate);
//					if (!practfactnch.equals("25")) {
//						INF.setMobile_no(mobile_no);
//					}
					INF.setCreated_on(new Date());
					// sessionHQL.beginTransaction();

					sessionHQL.update(INF);
					sessionHQL.flush();
					sessionHQL.clear();
//				}
				
				tx.commit();
				
				if (practfactnch.equals("25")) {
					String text = "Your OTP is "+OTP+" for AYUSH Portal."; 
					try {
						
						System.err.println("OTP------->        "+OTP);
						
//						emailsend.SendMail(request,INF.getEmail_id(),INF.getLogin_name(),"MOA Portal OTP",text,"","","");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				return OTP;
			}
			
			 @GetMapping(value = "admin/db_form")
			    public ModelAndView db_form(Model model,@RequestParam(value = "msg", required = false) String msg) {
//			    	model.addAttribute("msg", msg);
//			    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
			        return new ModelAndView("db_formcontrolTiles");
			    }
			 
			 
//		======================SAVE CONTACT US======================


//				======================SAVE CONTACT US======================
//				    @GetMapping(value = "/contactus")
//				    public String contactus(Model model,@RequestParam(value = "msg", required = false) String msg) {
//				    	model.addAttribute("msg", msg);
//				    	model.addAttribute("langugae", "english");
//				    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//				        return "contactpage";
//				    }
//					 @GetMapping(value = "/contactus")
//					    public String contactus(Model model,@RequestParam(value = "msg", required = false) String msg) {
//					    	model.addAttribute("msg", msg);
//					        return "contactpage";
//					    }
					 
					 @PostMapping(value = "/Contact_USAction")
						public @ResponseBody String Contact_USAction( 
								HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
								RedirectAttributes ra,String name,String email,String subject,String message) throws ParseException {
						 
								
							
							EDU_CONTACT_DETAILS ECD =new EDU_CONTACT_DETAILS();
							
							 String name1 = request.getParameter("name");
							String email1 = request.getParameter("email");
							String subject1 = request.getParameter("subject");
							String message1= request.getParameter("message");
							
							
							int id = ECD.getId() > 0 ? ECD.getId() : 0;
							Date date = new Date();
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							
							try {
//								EDU_CONTACT_DETAILS pers_detail = (EDU_CONTACT_DETAILS) sessionHQL
//										.get(EDU_CONTACT_DETAILS.class, Integer.parseInt(pers_hid));
								Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_CONTACT_DETAILS where upper(name)=:name  and email =:email and subject=:subject and message=:message and id !=:id")
								.setParameter("name", ECD.getName())
								.setParameter("email", ECD.getEmail())
								.setParameter("subject", ECD.getSubject())
								.setParameter("message", ECD.getMessage())
								.setParameter("id", id).uniqueResult();
								
							    	ECD.setName(name);
							    	ECD.setEmail(email);
							    	ECD.setSubject(subject);
							    	ECD.setMessage(message);
//							    	ECD.setCreated_date(date);
							    	
							    	if (id == 0) {
										
							    		ECD.setCreated_by(name);
							    		ECD.setCreated_date(date);
////										td.setCreated_role(role);
////										td.setCreated_date(date);
							    	if (c == 0) {
										sessionHQL.save(ECD);
										sessionHQL.flush();
										sessionHQL.clear();
										ra.addAttribute("msg", "Data Saved Successfully.");
							    	}else {
//							    		ECD.setCreated_by(name);
//							    		ECD.setCreated_date(date);
							    		sessionHQL.save(ECD);
										sessionHQL.flush();
										sessionHQL.clear();
										ra.addAttribute("msg", "Data Saved Successfully.");
							    	}
							    	}
								    tx.commit();

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
							return  "Data Saved Successfully";
		}



		                @GetMapping(value = "/feedback")
					    public String feedback(Model model,@RequestParam(value = "msg", required = false) String msg) {
					    	model.addAttribute("msg", msg);
					    	model.addAttribute("langugae", "english");
					    	model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
					        return "feedback";
					    } 		
					 
					 @PostMapping(value = "/feedbackAction")
						public @ResponseBody String feedbackAction( 
								HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
								RedirectAttributes ra,String type_of_issue,String first_name,String last_name,String ph_no,String email,String your_feedback) throws ParseException {
							
						 EDU_TB_FEEDBACK_DETAILS ECD =new EDU_TB_FEEDBACK_DETAILS();
							
							 String type_of_issue1 = request.getParameter("type_of_issue");
							String first_name1 = request.getParameter("first_name");
							String last_name1 = request.getParameter("last_name");
							String ph_no1= request.getParameter("ph_no");
							String email1= request.getParameter("email");
							String your_feedback1= request.getParameter("your_feedback");
							
							int id = ECD.getId() > 0 ? ECD.getId() : 0;
							Date date = new Date();
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							
							try {
//								EDU_CONTACT_DETAILS pers_detail = (EDU_CONTACT_DETAILS) sessionHQL
//										.get(EDU_CONTACT_DETAILS.class, Integer.parseInt(pers_hid));
								Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_TB_FEEDBACK_DETAILS where type_of_issue=:type_of_issue and upper(first_name)=:first_name and upper(last_name)=:last_name "
								+ "             and email =:email and ph_no=:ph_no and your_feedback=:your_feedback and id !=:id")
								.setParameter("type_of_issue", ECD.getType_of_issue())
								.setParameter("first_name", ECD.getFirst_name())
								.setParameter("last_name", ECD.getLast_name())
								.setParameter("ph_no", ECD.getPh_no())
								.setParameter("email", ECD.getEmail())
								.setParameter("your_feedback", ECD.getYour_feedback())
								.setParameter("id", id).uniqueResult();
								
								    ECD.setType_of_issue(Integer.parseInt(type_of_issue));
								    ECD.setFirst_name(first_name);
							    	ECD.setLast_name(last_name);
								    ECD.setPh_no(ph_no);
									ECD.setEmail(email);
							    	ECD.setYour_feedback(your_feedback);
//							    	ECD.setCreated_date(date);
							    	
							    	if (id == 0) {
										
							    		ECD.setCreated_by(email);
							    		ECD.setCreated_date(date);
////										td.setCreated_role(role);
////										td.setCreated_date(date);
							    	if (c == 0) {
										sessionHQL.save(ECD);
										sessionHQL.flush();
										sessionHQL.clear();
										ra.addAttribute("msg", "Data Saved Successfully.");
							    	}else {
//							    		ECD.setCreated_by(name);
//							    		ECD.setCreated_date(date);
							    		sessionHQL.save(ECD);
										sessionHQL.flush();
										sessionHQL.clear();
										ra.addAttribute("msg", "Data Saved Successfully.");
							    	}
							    	}
								    tx.commit();

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
							return  "Data Saved Successfully";
		}			
			 @GetMapping("/getUserDigilocker_API")
				public String getUserDigilocker_API(HttpSession session, HttpServletRequest request,
						@RequestParam("code") String code) throws org.json.simple.parser.ParseException {
					System.out.println();

					String url = "https://digilocker.meripehchaan.gov.in/public/oauth2/1/token";
					RestTemplate rs = new RestTemplate();
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

					MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
					map.add("code", code);
					
					//BISAG_LIVE
					map.add("redirect_uri", "https://apps.bisag.co.in/BISAG_LIVE/getUserDigilocker_API");
					map.add("client_id", "73E079BB");
					
					//BISAG
//					map.add("redirect_uri", "https://apps.bisag.co.in/BISAG/getUserDigilocker_API");
//					map.add("client_id", "85606FF1");
					
					//AYUSH_EDU
//					map.add("redirect_uri", "https://ayushedu.bisag-n.gov.in/getUserDigilocker_API");
//					map.add("client_id", "85606FF1");
					
					map.add("grant_type", "authorization_code");
					map.add("client_secret", "c5e69e65aa84fb94d8f1");

					HttpEntity<MultiValueMap<String, String>> head = new HttpEntity<MultiValueMap<String, String>>(map, headers);

					ResponseEntity<String> response = rs.postForEntity(url, head, String.class);

					String jsonBody = response.getBody();
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject) parser.parse(jsonBody);
								int user=uer.findUserByPhone(json.get("mobile").toString()).getUserId();
								String username=uer.findUserByPhone(json.get("mobile").toString()).getUserName();
								String pass=uer.findUserByPhone(json.get("mobile").toString()).getPassword();
								System.err.println("res---------------"+username);
								System.err.println("res---------------"+pass);
								

					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, "Bisag@123");
					authToken.setDetails(new WebAuthenticationDetails(request));
					Authentication authentication = au.authenticate(authToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);

					Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

//					String role1 = null;
//					for (String role : roles) {
//						request.getSession().setAttribute("role", role);
//						role1 = role;
//					}
					
					String role1 = "";
					int to=0;
					
					for (String role : roles) {
						System.out.println("role.length() "+roles.size());
						to+=1;
						
						if(roles.size()==to) {
							role1 += role;
						}else {
							role1 += role + ",";
						}
						
//						role1 = role;
					}
					
					
					
					
					System.out.println("role name ==" + role1);
					System.out.println(authentication.getName());

					int userId = userLoginDAO.getUserId(authentication.getName());
					Role roleList = userLoginDAO.findRole_url(role1);
					
					//////////////rajdip add
					List<Role> roleList2 = userLoginDAO.findRole_url2(role1);
					

					request.getSession().setAttribute("userId_for_jnlp", userId);
					request.getSession().setAttribute("username", authentication.getName());

//					String RoleUrl = "";
//					if (roleList.getRole_url() != null) {
//						RoleUrl = roleList.getRole_url();
//					}
//					String RoleType = "";
//					if (roleList.getRole_type() != null) {
//						RoleType = roleList.getRole_type();
//					}
//					String Acces_lvl = "";
//					if (roleList.getAccess_lvl() != null) {
//						Acces_lvl = roleList.getAccess_lvl();
//					}
//					String subAcces_lvl = "";
//					if (roleList.getSub_access_lvl() != null) {
//						subAcces_lvl = roleList.getSub_access_lvl();
//					}
//
//					String staff_lvl = "";
//					if (roleList.getStaff_lvl() != null) {
//						staff_lvl = roleList.getStaff_lvl();
//					}
//					String rolename = "";
//					if (roleList.getRole() != null) {
//						rolename = roleList.getRole();
//					}
					
					
			///////////rajdip  add  
					String RoleUrl = "";
					String RoleType = "";
					String Acces_lvl = "";
					String subAcces_lvl = "";
					String staff_lvl = "";
					String roleid2 = "";
					String rolename="";
					
					for(int i=0;i<roleList2.size();i++) {
						if (roleList2.get(i).getRole_url() != null) {
							if(i==0) {
								RoleUrl += roleList2.get(i).getRole_url();
							}else {
								RoleUrl += ","+roleList2.get(i).getRole_url();
							}
						}
						
						if (roleList2.get(i).getRole_type() != null) {
							
							if(i==0) {
								RoleType += roleList2.get(i).getRole_type();
							}else {
								RoleType += ","+roleList2.get(i).getRole_type();
							}
						}
						
						if (roleList2.get(i).getAccess_lvl() != null) {
							
							if(i==0) {
								Acces_lvl += roleList2.get(i).getAccess_lvl();
							}else {
								Acces_lvl += ","+roleList2.get(i).getAccess_lvl();
							}
						}
						
						if (roleList2.get(i).getSub_access_lvl() != null) {
							
							if(i==0) {
								subAcces_lvl += roleList2.get(i).getSub_access_lvl();
							}else {
								subAcces_lvl += ","+roleList2.get(i).getSub_access_lvl();
							}
						}
						
						if (roleList2.get(i).getStaff_lvl() != null) {
							
							if(i==0) {
								staff_lvl += roleList2.get(i).getStaff_lvl();
							}else {
								staff_lvl += ","+roleList2.get(i).getStaff_lvl();
							}
						}
						
						if (roleList2.get(i).getRoleId() > 0) {
							
							if(i==0) {
								roleid2 += roleList2.get(i).getRoleId();
							}else {
								roleid2 += ","+roleList2.get(i).getRoleId();
							}
						}
					if (roleList2.get(i).getRole() != null) {
									
									if(i==0) {
										rolename += roleList2.get(i).getRole();
									}else {
										rolename += ","+roleList2.get(i).getRole();
									}
								}
						
					}
					
				
					
					System.err.println("RoleUrl--------->   "+RoleUrl);
					System.err.println("RoleType--------->   "+RoleType);
					System.err.println("Acces_lvl--------->   "+Acces_lvl);
					System.err.println("subAcces_lvl--------->   "+subAcces_lvl);
					System.err.println("staff_lvl--------->   "+staff_lvl);
					System.err.println("rolename--------->   "+rolename);
					
			////////////end	
					

					request.getSession().setAttribute("roleSusNo", "");
					request.getSession().setAttribute("roleUrl", RoleUrl);
					request.getSession().setAttribute("roleType", RoleType);
					request.getSession().setAttribute("roleAccess", Acces_lvl);
					request.getSession().setAttribute("roleSubAccess", subAcces_lvl);
					request.getSession().setAttribute("roleStaff_lvl", staff_lvl);
					request.getSession().setAttribute("rolename", rolename);
					
//					int roleid = roleList.getRoleId();
					
					
					
					UserLogin addData = userLoginDAO.findByRoleId(userId);
//												request.getSession().setAttribute("army_no", addData.getArmy_no());
					if (roleid2 != "") {
						request.getSession().setAttribute("roleid", roleid2);
					}
					request.getSession().setAttribute("successValue", "Fail");

					String login_name = "";
					if (addData.getLogin_name() != null) {
						login_name = addData.getLogin_name();
					}

					request.getSession().setAttribute("userId", userId);
					request.getSession().setAttribute("username", addData.getUserName());
					request.getSession().setAttribute("university_id", addData.getUniversity_id());
//												request.getSession().setAttribute("army_no", addData.getArmy_no());
					if (roleid2 != "") {
						request.getSession().setAttribute("roleid", roleid2);
					}
					request.getSession().setAttribute("roleloginName", login_name);
					//////////////////

					System.out.println("success 1" + role1);

//											String ip = getClientIp(request);
//											request.getSession().setAttribute("ip", ip);

					String userAgent = request.getHeader("User-Agent");
//											request.getSession().setAttribute("user_agentWithIp", userAgent + "_" + ip);
					request.getSession().setAttribute("user_agent", userAgent);
//												
					// request.getSession().setAttribute("otpKey", "commonPwdEncKeys");
					request.getSession().setAttribute("KeySpec", "dc0da04af8fee58593442bf834b30739");

					final long fileSizeLimit = 2 * 1024 * 1024;
					request.getSession().setAttribute("fileSizeLimit", fileSizeLimit);

					String curDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
					request.getSession().setAttribute("curDate", curDate);

					request.getSession().setAttribute("regScript", "^[a-zA-Z0-9\\\\[\\\\] \\\\+ \\\\* \\\\-.,/ ~!@#$^&%_]+$");

					request.getSession().setAttribute("helpdeskFilePath", "/srv" + File.separator + "HELP");
					request.getSession().setAttribute("handingTakingOverPath", "/srv" + File.separator + "handingTakingOver");

					List<TB_LDAP_MODULE_MASTER> module = userLoginDAO.getModulelist(roleid2);
					String menu = "";

//												if(!RoleUrl.equals("")) {
//													 menu="<li class='nav-item dropdown dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
//												}
//												
//												for(int mod=0;mod<module.size() ;mod++) {
//													System.out.println("module name == "+module.get(mod).getModule_name());
//													 menu += "<li class='nav-item dropdown dropdown-item show' id='"+module.get(mod).getModule_name() +"_menu'>";	
//														menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module.get(mod).getModule_name()+"</a>";
//														menu += "<ul class='dropdown-menu show' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
//															List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
//															
//															for(int submod=0;submod<submodule.size();submod++){
//																menu += "<li class='dropdown-item dropdown create_search' >";
//																	menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
//																	menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
//																		List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid);
//																		for(int scr=0;scr<screen.size();scr++){
//																			menu += "<li class='dropdown-item'><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> <i class='fa fa-arrow-circle-o-right'></i>"+screen.get(scr).getScreen_name()+"</a></li>";
//																		}
//																	menu += "</ul>";
//																menu += "</li>";
//															}
//														menu += "</ul>";
//													menu += "</li>";
//												}
//												

//												for(int mod=0;mod<module.size() ;mod++) {
//													System.out.println("module name == "+module.get(mod).getModule_name());
//													 menu="<li class='nav-item dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
//													 menu += "<li class='nav-item dropdown dropdown-item show' id='"+module.get(mod).getModule_name() +"_menu'>";	
//														menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module.get(mod).getModule_name()+"</a>";
//														menu += "<ul class='dropdown-menu show' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
//															List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
//															for(int submod=0;submod<submodule.size();submod++){
//																menu += "<li class='dropdown-item dropdown create_search hvr-bounce-to-bottom'' >";
//																	menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
//																	menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
//																		List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid);
//																		for(int scr=0;scr<screen.size();scr++){
//																			menu += "<li class='dropdown-item hvr-bounce-to-right'><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> <i class='fa fa-arrow-circle-o-right'></i>"+screen.get(scr).getScreen_name()+"</a></li>";
//																		}
//																	menu += "</ul>";
//																menu += "</li>";
//															}
//														menu += "</ul>";
//													menu += "</li>";
//												}
//												

//											for (int mod = 0; mod < module.size(); mod++) {
//												System.out.println("module name == " + module.get(mod).getModule_name());
////													 menu="<li class='nav-item dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
					//
//												menu += "<li class='nav-item nav-item-has-children' id='" + module.get(mod).getModule_name() + "_menu'>";
//												menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#"
//														+ module.get(mod).getModule_name().replace(" ", "_") + "' aria-controls='" + module.get(mod).getModule_name().replace(" ", "_")
//														+ "' aria-expanded='false' aria-label='Toggle navigation' href='#' id='Dropdown_"
//														+ module.get(mod).getModule_name() + "'><i class='lni lni-files icon'></i><span class='text'>"
//														+ module.get(mod).getModule_name() + "</span></a>";
//												menu += "<ul id='" + module.get(mod).getModule_name().replace(" ", "_")
//														+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + module.get(mod).getModule_name()
//														+ "' >";
//												List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(), roleid);
//												for (int submod = 0; submod < submodule.size(); submod++) {
//													menu += "<li class='nav-item nav-item-has-children' >";
//													menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#"
//															+ submodule.get(submod).getSubmodule_name().replace(" ", "_") + "' aria-controls='"
//															+ submodule.get(submod).getSubmodule_name().replace(" ", "_")
//															+ "' aria-expanded='false' aria-label='Toggle navigation' id='Dropdown_"
//															+ submodule.get(submod).getId() + "' onclick='getsubmodule(" + submodule.get(submod).getId()
//															+ ")'><span class='text'>" + submodule.get(submod).getSubmodule_name() + "</span></a>";
//													menu += "<ul id='" + submodule.get(submod).getSubmodule_name().replace(" ", "_")
//															+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + submodule.get(submod).getId()
//															+ "' id='Dropdown_" + submodule.get(submod).getId() + "'>";
//													List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),
//															submodule.get(submod).getId(), roleid);
//													for (int scr = 0; scr < screen.size(); scr++) {
//														menu += "<li class=''><a href='" + screen.get(scr).getScreen_url()
//																+ "' onclick='localStorage.Abandon();'>" + screen.get(scr).getScreen_name() + "</a></li>";
//													}
//													menu += "</ul>";
//													menu += "</li>";
//												}
//												menu += "</ul>";
//												menu += "</li>";
//											}
					for (int mod = 0; mod < module.size(); mod++) {
						System.out.println("module name == " + module.get(mod).getModule_name());
//													 menu="<li class='nav-item dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
						menu += "<li class='nav-item nav-item-has-children' id='" + module.get(mod).getModule_name() + "_menu'>";
						menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#Dp_"
								+ module.get(mod).getModule_name().replace(" ", "_") + "' aria-controls='"
								+ module.get(mod).getModule_name().replace(" ", "_")
								+ "' aria-expanded='false' aria-label='Toggle navigation' href='#'  " + "onclick='getmodule(&apos;"
								+ module.get(mod).getModule_name().replace(" ", "_") + "&apos;)'" + " id='Dropdown_"
								+ module.get(mod).getModule_name().replace(" ", "_")
								+ "'><i class='lni lni-files icon'></i><span class='text'>" + module.get(mod).getModule_name()
								+ "</span></a>";
						menu += "<ul id='Dp_" + module.get(mod).getModule_name().replace(" ", "_")
								+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_"
								+ module.get(mod).getModule_name().replace(" ", "_") + "' >";
						List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(), roleid2);
						for (int submod = 0; submod < submodule.size(); submod++) {
							menu += "<li class='nav-item nav-item-has-children' >";
							menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#Dp_"
									+ submodule.get(submod).getId() + "' aria-controls='"
									+ submodule.get(submod).getSubmodule_name().replace(" ", "_")
									+ "' aria-expanded='false' aria-label='Toggle navigation' id='Dropdown_"
									+ submodule.get(submod).getId() + "' onclick='getsubmodule(" + submodule.get(submod).getId()
									+ ")'><span class='text'>" + submodule.get(submod).getSubmodule_name() + "</span></a>";
							menu += "<ul id='Dp_" + submodule.get(submod).getId()
									+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + submodule.get(submod).getId()
									+ "' id='" + submodule.get(submod).getId() + "'>";
							List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),
									submodule.get(submod).getId(), roleid2);
							for (int scr = 0; scr < screen.size(); scr++) {
								System.out.println(
										"upper==========  " + screen.get(scr).getScreen_name().substring(0, 1).toUpperCase()
												+ screen.get(scr).getScreen_name().substring(1));
								String ca = "";
								String[] arrOfStr = screen.get(scr).getScreen_name().split(" ");
								for (int h = 0; h < arrOfStr.length; h++) {
									System.out.println("arrOfStr[h] " + arrOfStr[h]);
									if (arrOfStr[h].trim().length() > 0) {
										if (arrOfStr[h].length() > 1) {
											ca += arrOfStr[h].substring(0, 1).toUpperCase() + arrOfStr[h].substring(1).toLowerCase()
													+ " ";

										} else {
											ca += arrOfStr[h].substring(0, 1).toUpperCase();

										}
									}
								}
								if (!ca.equals("")) {
									menu += "<li class=''><a href='" + screen.get(scr).getScreen_url() + "' id='Dp_scr"
											+ screen.get(scr).getId() + "' onclick='getpagelink(" + screen.get(scr).getId() + ")'>"
											+ ca + "</a></li>";
								}

							}

							menu += "</ul>";
							menu += "</li>";
						}
						menu += "</ul>";
						menu += "</li>";
					}

					request.getSession().setAttribute("menu", menu);
//												
//												// User Login Status
//												
//												
//												Session session1 = HibernateUtil.getSessionFactory().openSession();
//												Transaction tx = session1.beginTransaction();
//												try {
//													HD_TB_BISAG_USER_LOGIN_COUNT_INFO N = new HD_TB_BISAG_USER_LOGIN_COUNT_INFO();
//													N.setDate(new Date());
//													N.setLoginstatus("Active");
//													N.setStatus("1");
//													N.setUserid(userId);
//													session1.save(N);
//													tx.commit();
//												}catch (Exception e) {
//													System.out.println("Exception Logged User :" + e.getMessage());
//													tx.rollback();
//												}finally {
//													session1.close();
//												}
//												// User Login Status
//												

					// User Login Status

					String user_name = addData.getUserName();
					System.err.println("user---" + user_name);
					// int cdd1=cdd.getChangeday().get(0).getChng_pwd_day();
					// Boolean check12 = userLoginDAO.getdataformadatory(user_name);

//															if(check12.equals(true)) {
//																System.err.println("contr if-- true");
//																response.sendRedirect("../admin/fill_mandatory_Url");
//															}else {
//																System.err.println("contr else-- true");
//																response.sendRedirect("../admin/commonDashboard");
					// }
					int user_id = userLoginDAO.getUserId(authentication.getName());

					// int cdd1=cdd.getChangeday().get(0).getChng_pwd_day();
					Boolean check12 = userLoginDAO.getdataformadatory(user_id);

//												if(role1.equals("Practitioner_NCH")) {
////													if(role1.equals("PRACTITIONER")) {
//												if (check12.equals(true)   ) {
//													System.err.println("contr if-- true");
//													response.sendRedirect("../admin/commonDashboard");
//												} else {
//									 				System.err.println("contr else--  ");
//										 			response.sendRedirect("../admin/fill_mandatory_Url");
//									 			}
//											}
//											else {
//												response.sendRedirect("../admin/commonDashboard");
//									 		}

					String OTP = "110599";

//											BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  

					String currentpassword = addData.getPassword();
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String OTPPassword = passwordEncoder.encode(OTP);

					System.err.println("OTPPassword   " + OTPPassword + "   currentpassword    " + currentpassword);

					if (roleid2.contains("37") || roleid2.contains("38")) {
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  EDU_ALUM_REG_ALUMNI_CLG where user_id=:user_id and (degree_id=0 or specialization_id=0 or alum_address='') \n")
								.setParameter("user_id", user_id).uniqueResult();

						if (c == 1) {
							// response.sendRedirect("../admin/Edit_Profile_Url");
							return "redirect:/admin/Edit_Profile_Url";
						} else {
//													response.sendRedirect("../admin/alumni_feed");
							return "redirect:/admin/alumni_feed";
						}

					} else {

//												if (passwordEncoder.matches("110599",currentpassword) && addData.isAadhaar_is_verified() == false) {
//													response.sendRedirect("../admin/changeusernamepass");
//												} else {
						return "redirect:/admin/commonDashboard";
//												}

					}

					// return "redirect:/admin/commonDashboard";
				}
				@RequestMapping(value = "AlumniSignup_Url", method = RequestMethod.GET)
				public ModelAndView AlumniSignup_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg) {
					
					//SECURITY -- RIDDHI 
//					if(request.getHeader("Referer") == null ) { 
////						 session.invalidate();
//						 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return new ModelAndView("redirect:/landingpage");
//					 }
						
					 Mmap.put("msg", msg);
					 Mmap.addAttribute("langugae", "english");
					 Mmap.put("getInstituteList",common.getInstituteList(sessionFactory));
					 Mmap.put("getMedCountryName", common.getMedCountryName( sessionFactory));
					 Mmap.put("state_id", common.getMedStateName(sessionFactory));
					 Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
					return new ModelAndView("Alumni_Signup_Tiles");

				}			
				
//USermanual videos method abhishek
				@RequestMapping(value = "/ayusheducationusermanual")
				public void ayusheducationusermanual(@ModelAttribute("filename") String filename,
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
				
				 //shivali_advanceSearch Enhancement_Research portal	
				@RequestMapping(value = "/getFilterAdvance_Enhance_Research_dataSearch", method = RequestMethod.POST)
				public @ResponseBody List<ArrayList<String>> getFilterAdvance_Enhance_Research_dataSearch(HttpSession session,String category,
						String institute_name,String medicine_system) {
//					String userid = session.getAttribute("userId_for_jnlp").toString();
					List<ArrayList<String>> list = AER.getFilterAdvance_Enhance_Research_dataSearch(session,category,institute_name,medicine_system);
					return list;
				}
				
				@RequestMapping(value = "/globalsearchAER", method = RequestMethod.POST)
				public @ResponseBody List<ArrayList<String>> globalsearchAER(String searchstring) {
//					String userid = session.getAttribute("userId_for_jnlp").toString();
					List<ArrayList<String>> list = AER.getFilterAdvance_Enhance_Research_GlobalSearch(searchstring);
					return list;
				}
				
			//// ==========================comment save=======advanceSearch
				//// Enhancement_Research portal=================================
				@PostMapping(value = "/Comment_Details_SaveAction")
				public @ResponseBody String Comment_Details_SaveAction(HttpServletRequest request, ModelMap model,
						HttpSession session, Principal principal, RedirectAttributes ra, String comment, String p_id)
						throws ParseException {

					TB_ENHANCE_RESEARCH_COMMENT_DETAILS ECD = new TB_ENHANCE_RESEARCH_COMMENT_DETAILS();

//										 String name1 = request.getParameter("name");
//										String email1 = request.getParameter("email");
//										String subject1 = request.getParameter("subject");
					String comment1 = request.getParameter("comment");
					String p_id1 = request.getParameter("p_id");

					int id = ECD.getId() > 0 ? ECD.getId() : 0;
					Date date = new Date();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					try {
//											EDU_CONTACT_DETAILS pers_detail = (EDU_CONTACT_DETAILS) sessionHQL
//													.get(EDU_CONTACT_DETAILS.class, Integer.parseInt(pers_hid));
						Long c = (Long) sessionHQL.createQuery(
								"select count(id) from  TB_ENHANCE_RESEARCH_COMMENT_DETAILS where comment=:comment and p_id=:p_id and id !=:id")
//											.setParameter("name", ECD.getName())
//											.setParameter("email", ECD.getEmail())
//											.setParameter("subject", ECD.getSubject())
								.setParameter("comment", ECD.getComment()).setParameter("p_id", ECD.getP_id())
								.setParameter("id", id).uniqueResult();

//										    	ECD.setName(name);
//										    	ECD.setEmail(email);
						ECD.setComment(comment);
						ECD.setP_id(Integer.parseInt(p_id));
//										    	ECD.setCreated_date(date);

						if (id == 0) {

//										    		ECD.setCreated_by(name);
							ECD.setCreated_date(date);
////													td.setCreated_role(role);
////													td.setCreated_date(date);
							if (c == 0) {
								sessionHQL.save(ECD);
								sessionHQL.flush();
								sessionHQL.clear();
								ra.addAttribute("msg", "Data Saved Successfully.");
							} else {
//										    		ECD.setCreated_by(name);
//										    		ECD.setCreated_date(date);
								sessionHQL.save(ECD);
								sessionHQL.flush();
								sessionHQL.clear();
								ra.addAttribute("msg", "Data Saved Successfully.");
							}
						}
						tx.commit();

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
					return "Data Saved Successfully";
				}

			 
}
