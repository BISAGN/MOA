package com.AyushEdu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.AyushEdu.Models.Role;
import com.AyushEdu.Models.TB_LDAP_MODULE_MASTER;
import com.AyushEdu.Models.TB_LDAP_SCREEN_MASTER;
import com.AyushEdu.Models.TB_LDAP_SUBMODULE_MASTER;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.dao.UserLoginDAO;

public class redirectLogin extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Autowired
	private SessionFactory sessionFactory;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
//---------------parth patel		
		String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
		String capcha =  request.getSession().getAttribute("capcha").toString();
		System.err.println(txtInput+"--"+capcha);
		 if(!txtInput.equals(capcha)){
			 request.getSession().invalidate();
		 response.sendRedirect("/portalsignin");
		 }
//		---------------parth patel		
//		String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
//		String capcha =  request.getSession().getAttribute("capcha").toString();
//		 if(!txtInput.equals(capcha)){
//		 response.sendRedirect("/login");
//		 }else{
		
//		int uId = userLoginDAO.getUserId(authentication.getName());
//		Boolean isAaddhaarverified = userLoginDAO.isaadhaarverified(uId);
//		
//		System.err.println("isAaddhaarverified---- redirect login ---->    "+isAaddhaarverified);
		
//		if (isAaddhaarverified == false) {
//			
//		}
		
		System.out.println("onAuthenticationSuccess");
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	////// rajdip
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
			
//			role1 = role;
		}
		
		request.getSession().setAttribute("role", role1);
		
		System.out.println("role-name ==" + role1);
		System.out.println(authentication.getName());

		int userId = userLoginDAO.getUserId(authentication.getName());
		Role roleList = userLoginDAO.findRole_url(role1);

		List<Role> roleList2 = userLoginDAO.findRole_url2(role1);
		
		
		request.getSession().setAttribute("userId_for_jnlp", userId);
		request.getSession().setAttribute("username", authentication.getName());

//		String RoleUrl = "";
//		
//		if (roleList.getRole_url() != null) {
//			RoleUrl = roleList.getRole_url();
//		}
//		
//		String RoleType = "";
//		
//		if (roleList.getRole_type() != null) {
//			RoleType = roleList.getRole_type();
//		}
//		String Acces_lvl = "";
//		
//		if (roleList.getAccess_lvl() != null) {
//			Acces_lvl = roleList.getAccess_lvl();
//		}
//		String subAcces_lvl = "";
//		if (roleList.getSub_access_lvl() != null) {
//			subAcces_lvl = roleList.getSub_access_lvl();
//		}
//
//		String staff_lvl = "";
//		if (roleList.getStaff_lvl() != null) {
//			staff_lvl = roleList.getStaff_lvl();
//		}
//		String rolename = "";
//		if (roleList.getRole() != null) {
//			rolename = roleList.getRole();
//		}
		
		
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

		String[] convertedRoleUrl = RoleUrl.split(",");
		if (convertedRoleUrl.length > 1 && Arrays.stream(convertedRoleUrl).allMatch(s -> s.equals(convertedRoleUrl[0])) == true) {
			request.getSession().setAttribute("roleUrl", convertedRoleUrl[0]);
		}
		String[] convertedRoleType = RoleType.split(",");
		if (convertedRoleType.length > 1 && Arrays.stream(convertedRoleType).allMatch(s -> s.equals(convertedRoleType[0])) == true) {
			request.getSession().setAttribute("roleType", convertedRoleType[0]);
		}
		String[] convertedAcces_lvl = Acces_lvl.split(",");
		if (convertedAcces_lvl.length > 1 && Arrays.stream(convertedAcces_lvl).allMatch(s -> s.equals(convertedAcces_lvl[0])) == true) {
			request.getSession().setAttribute("roleAccess", convertedAcces_lvl[0]);
		}
		String[] convertedsubAcces_lvl = subAcces_lvl.split(",");
		if (convertedsubAcces_lvl.length > 1 && Arrays.stream(convertedsubAcces_lvl).allMatch(s -> s.equals(convertedsubAcces_lvl[0])) == true) {
			request.getSession().setAttribute("roleSubAccess", convertedsubAcces_lvl[0]);
		}
		String[] convertedstaff_lvl = staff_lvl.split(",");
		if (convertedstaff_lvl.length > 1 && Arrays.stream(convertedstaff_lvl).allMatch(s -> s.equals(convertedstaff_lvl[0])) == true) {
			request.getSession().setAttribute("roleStaff_lvl", convertedstaff_lvl[0]);
		}
		String[] convertedrolename = rolename.split(",");
		if (convertedrolename.length > 1 && Arrays.stream(convertedrolename).allMatch(s -> s.equals(convertedrolename[0])) == true) {
			request.getSession().setAttribute("rolename", convertedrolename[0]);
		}
		
		
//		int roleid = roleList.getRoleId();
		UserLogin addData = userLoginDAO.findByRoleId(userId);
//			request.getSession().setAttribute("army_no", addData.getArmy_no());
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
//			request.getSession().setAttribute("army_no", addData.getArmy_no());
//		if (roleid != 0) {
//			request.getSession().setAttribute("roleid", roleid);
//		}
		request.getSession().setAttribute("roleloginName", login_name);
		//////////////////

		System.out.println("success 1" + role1);

		String ip = getClientIp(request);
		request.getSession().setAttribute("ip", ip);

		String userAgent = request.getHeader("User-Agent");
		request.getSession().setAttribute("user_agentWithIp", userAgent + "_" + ip);
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
		String menu2 = "";

//			if(!RoleUrl.equals("")) {
//				 menu="<li class='nav-item dropdown dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
//			}
//			
//			for(int mod=0;mod<module.size() ;mod++) {
//				System.out.println("module name == "+module.get(mod).getModule_name());
//				 menu += "<li class='nav-item dropdown dropdown-item show' id='"+module.get(mod).getModule_name() +"_menu'>";	
//					menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module.get(mod).getModule_name()+"</a>";
//					menu += "<ul class='dropdown-menu show' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
//						List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
//						
//						for(int submod=0;submod<submodule.size();submod++){
//							menu += "<li class='dropdown-item dropdown create_search' >";
//								menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
//								menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
//									List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid);
//									for(int scr=0;scr<screen.size();scr++){
//										menu += "<li class='dropdown-item'><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> <i class='fa fa-arrow-circle-o-right'></i>"+screen.get(scr).getScreen_name()+"</a></li>";
//									}
//								menu += "</ul>";
//							menu += "</li>";
//						}
//					menu += "</ul>";
//				menu += "</li>";
//			}
//			

//			for(int mod=0;mod<module.size() ;mod++) {
//				System.out.println("module name == "+module.get(mod).getModule_name());
//				 menu="<li class='nav-item dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
//				 menu += "<li class='nav-item dropdown dropdown-item show' id='"+module.get(mod).getModule_name() +"_menu'>";	
//					menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><i class='fa fa-th-large'></i>"+module.get(mod).getModule_name()+"</a>";
//					menu += "<ul class='dropdown-menu show' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
//						List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
//						for(int submod=0;submod<submodule.size();submod++){
//							menu += "<li class='dropdown-item dropdown create_search hvr-bounce-to-bottom'' >";
//								menu += "<a class='dropdown-toggle' id='Dropdown_"+submodule.get(submod).getId()+"' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' onclick='getsubmodule("+submodule.get(submod).getId()+")'><i class='fa fa-plus-circle'></i>"+submodule.get(submod).getSubmodule_name()+"</a>"; 
//								menu += "<ul class='dropdown-menu scrollbar' aria-labelledby='Dropdown_"+submodule.get(submod).getId()+"' id='Dropdown_"+submodule.get(submod).getId()+"' style='height: 100%;'>";
//									List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),submodule.get(submod).getId(),roleid);
//									for(int scr=0;scr<screen.size();scr++){
//										menu += "<li class='dropdown-item hvr-bounce-to-right'><a href='"+screen.get(scr).getScreen_url()+"' onclick='localStorage.Abandon();'> <i class='fa fa-arrow-circle-o-right'></i>"+screen.get(scr).getScreen_name()+"</a></li>";
//									}
//								menu += "</ul>";
//							menu += "</li>";
//						}
//					menu += "</ul>";
//				menu += "</li>";
//			}
//			

//		for (int mod = 0; mod < module.size(); mod++) {
//			System.out.println("module name == " + module.get(mod).getModule_name());
////				 menu="<li class='nav-item dropdown-item'><a href='"+RoleUrl+"' class='nav-link dropdown-toggle'>Dashboard</a></li>";
//
//			menu += "<li class='nav-item nav-item-has-children' id='" + module.get(mod).getModule_name() + "_menu'>";
//			menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#"
//					+ module.get(mod).getModule_name().replace(" ", "_") + "' aria-controls='" + module.get(mod).getModule_name().replace(" ", "_")
//					+ "' aria-expanded='false' aria-label='Toggle navigation' href='#' id='Dropdown_"
//					+ module.get(mod).getModule_name() + "'><i class='lni lni-files icon'></i><span class='text'>"
//					+ module.get(mod).getModule_name() + "</span></a>";
//			menu += "<ul id='" + module.get(mod).getModule_name().replace(" ", "_")
//					+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + module.get(mod).getModule_name()
//					+ "' >";
//			List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(), roleid);
//			for (int submod = 0; submod < submodule.size(); submod++) {
//				menu += "<li class='nav-item nav-item-has-children' >";
//				menu += "<a class='collapsed' data-bs-toggle='collapse' data-bs-target='#"
//						+ submodule.get(submod).getSubmodule_name().replace(" ", "_") + "' aria-controls='"
//						+ submodule.get(submod).getSubmodule_name().replace(" ", "_")
//						+ "' aria-expanded='false' aria-label='Toggle navigation' id='Dropdown_"
//						+ submodule.get(submod).getId() + "' onclick='getsubmodule(" + submodule.get(submod).getId()
//						+ ")'><span class='text'>" + submodule.get(submod).getSubmodule_name() + "</span></a>";
//				menu += "<ul id='" + submodule.get(submod).getSubmodule_name().replace(" ", "_")
//						+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + submodule.get(submod).getId()
//						+ "' id='Dropdown_" + submodule.get(submod).getId() + "'>";
//				List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),
//						submodule.get(submod).getId(), roleid);
//				for (int scr = 0; scr < screen.size(); scr++) {
//					menu += "<li class=''><a href='" + screen.get(scr).getScreen_url()
//							+ "' onclick='localStorage.Abandon();'>" + screen.get(scr).getScreen_name() + "</a></li>";
//				}
//				menu += "</ul>";
//				menu += "</li>";
//			}
//			menu += "</ul>";
//			menu += "</li>";
//		}
		
		String OTP="110599";
		String currentpassword = addData.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String OTPPassword = passwordEncoder.encode(OTP);
		
		
		System.err.println("OTPPassword   "+OTPPassword +"   currentpassword    "+ currentpassword);
		
		String hashedPassword = passwordEncoder.encode("Bisag@123");
		System.err.println("check - "+passwordEncoder.matches("Bisag@123", currentpassword)  );

		
//		String role =roleList.getRole();
		
//		System.err.println("=====role - "+role);
		

		int submoduleindex=0;
		int screenindex=0;
		int moduleindex=0;
		for (int mod = 0; mod < module.size(); mod++) {
			if(module.get(mod).getModule_name().contains("DASHBOARD")) {
				menu += "<li class='nav-item nav-item-has-children' id='" + module.get(mod).getModule_name() + "_menu'>";
				menu += "<a class='collapsed module_csp' data-bs-toggle='collapse' data-bs-target='#Dp_" 
						+ module.get(mod).getModule_name().replace(" ", "_") + "' aria-controls='" + module.get(mod).getModule_name().replace(" ", "_")
						+ "' aria-expanded='false' aria-label='Toggle navigation' href='#'  "
						+""
						+ " id='Dropdown_"
						+ module.get(mod).getModule_name().replace(" ", "_") + "'><i class='lni lni-files icon'></i><span class='text'>"
						+ module.get(mod).getModule_name() + "</span><input type='hidden' id='module"+moduleindex+"' value='"+module.get(mod).getModule_name().replace(" ", "_")+"'></a>";
				menu += "<ul id='Dp_"+ module.get(mod).getModule_name().replace(" ", "_")
						+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + module.get(mod).getModule_name().replace(" ", "_")
						+ "' >";
				moduleindex++;
//				onclick='getsubmodule("+ submodule.get(submod).getId()
//						+ ")
				List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(), roleid2);
				for (int submod = 0; submod < submodule.size(); submod++) {
					
					menu += "<li class='nav-item nav-item-has-children' >";
					menu += "<a class='collapsed submodule_csp' data-bs-toggle='collapse' data-bs-target='#Dp_"
							+ submodule.get(submod).getId() + "' aria-controls='"
							+ submodule.get(submod).getSubmodule_name().replace(" ", "_")
							+ "' aria-expanded='false' aria-label='Toggle navigation' id='Dropdown_"
							+ submodule.get(submod).getId() + "'><span class='text'>" + submodule.get(submod).getSubmodule_name() + "</span><input type='hidden' id='submodule"+submoduleindex+"' value='"+submodule.get(submod).getId()+"'></a>";
					menu += "<ul id='Dp_" + submodule.get(submod).getId()
							+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + submodule.get(submod).getId()
							+ "' id='" + submodule.get(submod).getId() + "'>";
					submoduleindex++;
					List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),
							submodule.get(submod).getId(), roleid2);
//					onclick='getpagelink("+screen.get(scr).getId()+")
					for (int scr = 0; scr < screen.size(); scr++) {
						String ca="";
						String[] arrOfStr=screen.get(scr).getScreen_name().split(" ");
						for (int h = 0; h < arrOfStr.length; h++) {
							if(arrOfStr[h].trim().length()>0) {
							if(arrOfStr[h].length()>1) {
								ca+=arrOfStr[h].substring(0, 1).toUpperCase()+arrOfStr[h].substring(1).toLowerCase()+" ";

							}else {
								ca+=arrOfStr[h].substring(0, 1).toUpperCase();

							}
							}
						}
						if(!ca.equals("")) {
						menu += "<li class=''><a class='screen_csp' href='" + screen.get(scr).getScreen_url()
								+ "' id='Dp_scr"+screen.get(scr).getId()+"'>" +ca + "</a><input type='hidden' id='screen"+screenindex+"' value='"+screen.get(scr).getId()+"'></li>";
						screenindex++;
						}
						
					}
					
					menu += "</ul>";
					menu += "</li>";
					
				}
				menu += "</ul>";
				menu += "</li>";
				menu += "<span class=\"divider\">\r\n"
						+ "		          <hr>\r\n"
						+ "		        </span>";
				
				}
		}
		
//		onclick='getmodule(&apos;"+module.get(mod).getModule_name().replace(" ", "_")+"&apos;)'
		for (int mod = 0; mod < module.size(); mod++) {
			System.out.println();
			if(!module.get(mod).getModule_name().contains("DASHBOARD")) {
			menu += "<li class='nav-item nav-item-has-children' id='" + module.get(mod).getModule_name() + "_menu'>";
			menu += "<a class='collapsed module_csp' data-bs-toggle='collapse' data-bs-target='#Dp_" 
					+ module.get(mod).getModule_name().replace(" ", "_") + "' aria-controls='" + module.get(mod).getModule_name().replace(" ", "_")
					+ "' aria-expanded='false' aria-label='Toggle navigation' href='#'  "
					+""
					+ " id='Dropdown_"
					+ module.get(mod).getModule_name().replace(" ", "_") + "'><i class='lni lni-files icon'></i><span class='text'>"
					+ module.get(mod).getModule_name() + "</span><input type='hidden' id='module"+moduleindex+"' value='"+module.get(mod).getModule_name().replace(" ", "_")+"'></a>";
			menu += "<ul id='Dp_"+ module.get(mod).getModule_name().replace(" ", "_")
					+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + module.get(mod).getModule_name().replace(" ", "_")
					+ "' >";
			moduleindex++;
//			onclick='getsubmodule("+ submodule.get(submod).getId()
//					+ ")
			List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(), roleid2);
			for (int submod = 0; submod < submodule.size(); submod++) {
				
				menu += "<li class='nav-item nav-item-has-children' >";
				menu += "<a class='collapsed submodule_csp' data-bs-toggle='collapse' data-bs-target='#Dp_"
						+ submodule.get(submod).getId() + "' aria-controls='"
						+ submodule.get(submod).getSubmodule_name().replace(" ", "_")
						+ "' aria-expanded='false' aria-label='Toggle navigation' id='Dropdown_"
						+ submodule.get(submod).getId() + "'><span class='text'>" + submodule.get(submod).getSubmodule_name() + "</span><input type='hidden' id='submodule"+submoduleindex+"' value='"+submodule.get(submod).getId()+"'></a>";
				menu += "<ul id='Dp_" + submodule.get(submod).getId()
						+ "' class='collapse dropdown-nav' aria-labelledby='Dropdown_" + submodule.get(submod).getId()
						+ "' id='" + submodule.get(submod).getId() + "'>";
				submoduleindex++;
				List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(),
						submodule.get(submod).getId(), roleid2);
//				onclick='getpagelink("+screen.get(scr).getId()+")
				for (int scr = 0; scr < screen.size(); scr++) {
					String ca="";
					String[] arrOfStr=screen.get(scr).getScreen_name().split(" ");
					for (int h = 0; h < arrOfStr.length; h++) {
						if(arrOfStr[h].trim().length()>0) {
						if(arrOfStr[h].length()>1) {
							ca+=arrOfStr[h].substring(0, 1).toUpperCase()+arrOfStr[h].substring(1).toLowerCase()+" ";

						}else {
							ca+=arrOfStr[h].substring(0, 1).toUpperCase();

						}
						}
					}
					if(!ca.equals("")) {
					menu += "<li class=''><a class='screen_csp' href='" + screen.get(scr).getScreen_url()
							+ "' id='Dp_scr"+screen.get(scr).getId()+"'>" +ca + "</a><input type='hidden' id='screen"+screenindex+"' value='"+screen.get(scr).getId()+"'></li>";
					screenindex++;
					}
				}
				
				menu += "</ul>";
				menu += "</li>";
			}
			menu += "</ul>";
			menu += "</li>";
			}
		}
	
		if ((rolename.toLowerCase().contains("institute") || rolename.toLowerCase().contains("principal") || rolename.toLowerCase().contains("university") || rolename.toLowerCase().contains("moa") 
				|| rolename.equals("NCISM") || rolename.equals("NCH") || rolename.toLowerCase().contains("state_council") 
				|| rolename.toLowerCase().contains("ayu_board") || rolename.toLowerCase().contains("uni_board") || rolename.toLowerCase().contains("sid_board") || rolename.toLowerCase().contains("swr_board")
				|| rolename.toLowerCase().contains("hom_board") || rolename.toLowerCase().contains("councillor") || rolename.toLowerCase().contains("tpo") || rolename.toLowerCase().contains("course_publisher") 
				|| rolename.toLowerCase().contains("course_publisher_staff"))
				&& passwordEncoder.matches("Bisag@123", currentpassword)) {
			request.getSession().setAttribute("menu", "");
			request.getSession().setAttribute("menu2", "");
			}else {
				request.getSession().setAttribute("menu", menu);
				request.getSession().setAttribute("menu2", menu2);
			}

		
//			// User Login Status
//			
//			
//			Session session1 = HibernateUtil.getSessionFactory().openSession();
//			Transaction tx = session1.beginTransaction();
//			try {
//				HD_TB_BISAG_USER_LOGIN_COUNT_INFO N = new HD_TB_BISAG_USER_LOGIN_COUNT_INFO();
//				N.setDate(new Date());
//				N.setLoginstatus("Active");
//				N.setStatus("1");
//				N.setUserid(userId);
//				session1.save(N);
//				tx.commit();
//			}catch (Exception e) {
//				System.out.println("Exception Logged User :" + e.getMessage());
//				tx.rollback();
//			}finally {
//				session1.close();
//			}
//			// User Login Status
//			
		// User Login Status

		String user_name = addData.getUserName();
		System.err.println("user---" + user_name);
		// int cdd1=cdd.getChangeday().get(0).getChng_pwd_day();
		// Boolean check12 = userLoginDAO.getdataformadatory(user_name);

//						if(check12.equals(true)) {
//							System.err.println("contr if-- true");
//							response.sendRedirect("../admin/fill_mandatory_Url");
//						}else {
//							System.err.println("contr else-- true");
//							response.sendRedirect("../admin/commonDashboard");
		// }
		int user_id = userLoginDAO.getUserId(authentication.getName());

		// int cdd1=cdd.getChangeday().get(0).getChng_pwd_day();
		Boolean check12 = userLoginDAO.getdataformadatory(user_id);
		 
//			if(role1.equals("Practitioner_NCH")) {
////				if(role1.equals("PRACTITIONER")) {
//			if (check12.equals(true)   ) {
//				System.err.println("contr if-- true");
//				response.sendRedirect("../admin/commonDashboard");
//			} else {
// 				System.err.println("contr else--  ");
//	 			response.sendRedirect("../admin/fill_mandatory_Url");
// 			}
//		}
//		else {
//			response.sendRedirect("../admin/commonDashboard");
// 		}
		
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
//		String currentpassword = addData.getPassword();
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String OTPPassword = passwordEncoder.encode(OTP);
//		
//		System.err.println("OTPPassword   "+OTPPassword +"   currentpassword    "+ currentpassword);
//		
//		String hashedPassword = passwordEncoder.encode("Bisag@123");
//		System.err.println("check - "+passwordEncoder.matches("Bisag@123", currentpassword)  );

		
//		System.out.println("------bisag pass "+hashedPassword);
		
		if(roleid2.contains("37") || roleid2.contains("38")) {
		
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_ALUM_REG_ALUMNI_CLG where user_id=:user_id and (degree_id=0 or specialization_id=0 or alum_address='') \n")
				.setParameter("user_id", user_id)
				.uniqueResult();
		
			if(c==1) {
				response.sendRedirect("../admin/Edit_Profile_Url");
			} else {
				response.sendRedirect("../admin/alumni_feed");
			}
			
		}else if((rolename.toLowerCase().contains("institute") || rolename.toLowerCase().contains("principal") || rolename.toLowerCase().contains("university") || rolename.toLowerCase().contains("moa") 
				|| rolename.equals("NCISM") || rolename.equals("NCH") || rolename.toLowerCase().contains("state_council") 
				|| rolename.toLowerCase().contains("ayu_board") || rolename.toLowerCase().contains("uni_board") || rolename.toLowerCase().contains("sid_board") || rolename.toLowerCase().contains("swr_board")
				|| rolename.toLowerCase().contains("hom_board") || rolename.toLowerCase().contains("councillor") || rolename.toLowerCase().contains("tpo") || rolename.toLowerCase().contains("course_publisher") 
				|| rolename.toLowerCase().contains("course_publisher_staff"))
				&& passwordEncoder.matches("Bisag@123", currentpassword)) {
			response.sendRedirect("../admin/changePassword");

		}else if(rolename.equals("NCISM") || rolename.equals("Institute_NCH") || rolename.equals("Institute_NCISM") || rolename.equals("University_NCH") || rolename.equals("University_NCISM")
				|| rolename.equals("State_Council_NCISM") || rolename.equals("AYU_BOARD") || rolename.equals("UNI_BOARD") || rolename.equals("HOM_BOARD") || rolename.equals("SID_BOARD") || rolename.equals("SWR_BOARD") ) { // 09_02_2023
			response.sendRedirect("../admin/e_fromAdmissionDashboardUrl");
		}else{

//			if (passwordEncoder.matches("110599",currentpassword) && addData.isAadhaar_is_verified() == false) {
//				response.sendRedirect("../admin/changeusernamepass");
//			} else {
				response.sendRedirect("../admin/commonDashboard");
//			}
		}
//		response.sendRedirect("../admin/commonDashboard");
	
	}		
//			
//			
//			
//			System.out.println("Call commonDashboard");
//			response.sendRedirect("../admin/commonDashboard");
		// }
	//}

	private static String getClientIp(HttpServletRequest request) {
		System.out.println(request.getRemoteAddr() + "==" + request.getRequestURI());
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
}