package com.AyushEdu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.AyushEdu.controller.redirectLogin;
import com.AyushEdu.service.UserDetailsServiceImpl;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
  
  
    @Autowired
    private VideoService service; 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	try {
//    	---------------------------parth patel	
    		http.headers()
            .xssProtection();
//            .and()
//            .contentSecurityPolicy("script-src 'self'");
    		
//        	---------------------------parth patel	
    		
    		
    	 http.headers();
    	 http.cors().and().csrf();
//    	 http.cors().and().csrf().disable();
//    	 http.headers().contentSecurityPolicy("script-src 'self' 'nonce-r02122i021210p215a12455l12411'; style-src-elem 'self'; "
//     	 		+ "object-src self; style-src 'unsafe-inline'");
//    	 http.headers().contentSecurityPolicy("script-src 'self' 'nonce-{nonce}'; style-src-elem 'self'; "
//     	 		+ "object-src self; style-src 'unsafe-inline'"); 
    	//http.addFilterBefore(new CSPNonceFilter(), HeaderWriterFilter.class);   
//    	 http.addFilterBefore(new CSPNonceFilter(), HeaderWriterFilter.class).headers().
//    	 contentSecurityPolicy("X-Content-Security-Policy,'script-src 'self' 'nonce-{nonce} ;'" + "default-src  'self';" + "script-src  'self' 'nonce-{nonce}' ;"
// 				+ "style-src 'self' 'nonce-{nonce}' ;" + "object-src 'none'; base-uri 'self';" + "connect-src 'self';"
// 				+ "font-src 'self' data: ;" + "frame-src 'self'; " + "img-src  'self' 'unsafe-inline';" // TO DO
// 				+ "manifest-src 'self';" + "media-src 'self';");    
//    	 http.addFilterAt(new CSPNonceFilter(), HeaderWriterFilter.class);  

//    	 http.headers() .contentSecurityPolicy("default-src  'self';" + "script-src  'self' 'nonce-{nonce}' ;"
//  				+ "style-src 'self' 'nonce-{nonce}' ;" + "object-src 'none'; base-uri 'self';" + "connect-src 'self';"
//  				+ "font-src 'self' data: ;" + "frame-src 'self'; " + "img-src  'self' 'unsafe-inline';" // TO DO
//  				+ "manifest-src 'self';" + "media-src 'self';");
  
    	 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    	 .maximumSessions(1).expiredUrl("/landingpage?logout")
    	 .maxSessionsPreventsLogin(true)
    	 .and()
    	 .invalidSessionUrl("/landingpage?logout").sessionFixation().migrateSession();
    	 http.authorizeRequests().antMatchers( "/logout","/getStateUrl","/getDistrictUrl").permitAll();
    	 http.authorizeRequests().antMatchers( "/assets/**", "/landingpage", "/login/**", "/fpasswordUrl", "/about","/portalsignin" ,"/portalsignup" ,
    			 "/portalforgotpassword","/admin", "/contactus","/admin/**","/registrationUrl","/alumni_registrationUrl","/alumni_registration_Action",
    			 "/institute_registration_url","/institute_registration_url_action" ,"/ncism" ,"/nch","/moa" ,"/trainingandwebcast" ,"/ayurveda",
    			 "/Practitioner_registration_action","/getaayushid_Autocomplete","/getnrhid_Autocomplete" ,"/aboutncism" ,"/aboutnch","/aboutmoa" ,
    			 "/ayurveda" ,"/ncismlanding" ,"/ncismlandinghindi" ,"/nchlanding" ,"/nchlandinghindi" ,"/landingpagehindi" ,"/homoeopathy" ,
    			 "/unani" ,"/siddha" ,"/sowa" ,"/ncismsignup" ,"/ncismsignin" ,"/nchsignin" ,"/nchsignup" ,"/ncismhomoeopathy" ,"/ncismunani" ,
    			 "/ncismsiddha" , "/ncismsowa" ,"/ncismayurveda" ,"/ncismcontact" ,"/nchhomoeopathy" ,"/nchunani" ,"/nchsiddha" ,"/nchsowa" ,
    			 "/nchayurveda" , "/nchcontact" ,"/ncismabout" ,"/nchabout" ,"/ayushterminology" ,"/ayushknowledge" ,"/ayushterminologyncism" ,
    			 "/ayushknowledgencism" ,"/ayushterminologynch" ,"/ayushknowledgench" ,"/forgotpasswordncism" ,"/forgotpasswordnch" ,"/feedback",
    			 "/aboutnchhindi" ,"/nchsigninhindi" ,"/nchsignuphindi" ,"/nchhomoeopathyhindi" ,"/forgotpasswordnchhindi" ,"/nchunanihindi" ,
    			 "/nchsiddhahindi" ,"/nchsowahindi" ,"/nchayurvedahindi" ,"/ayushterminologynchhindi" ,
    			 "/ayushknowledgenchhindi" ,"/nchcontacthindi" ,"/nchabouthindi" ,"/aboutnchhindi" ,"/ncismsigninhindi" ,
    			 "/ncismsignuphindi" ,"/ncismhomoeopathyhindi" ,"/ncismunanihindi" ,"/ncismsiddhahindi" ,"/ncismsowahindi" ,"/ncismayurvedahindi" ,
    			 "/ayushterminologyncismhindi" ,"/ayushknowledgencismhindi" ,
    			 "/ncismcontacthindi" ,"/ncismabouthindi" ,"/aboutncismhindi" ,"/MoaPagehindi" ,"/ayurvedahindi" ,"/contactpagehindi" ,
    			 "/homoeopathyhindi" ,"/unanihindi" ,"/siddhahindi" ,"/sowahindi" ,"/abouthindincism" ,"/abouthindinch" ,"/ayushterminologyhindi" ,
    			 "/ayushknowledgehindi" ,"/forgotpasswordncism" ,"/usermanual","/ncism_admissionguide",
    			 "/forgotpasswordnch" ,"/institute_registration_urlhindi","/getaayushdataautocomplete", "/nrh_no_autocomplete_data_fetchaaa",
    			 "/getaayushid_Autocomplete","/getnrhid_Autocomplete","/ayus_abha_nrh_data_fetch","/newdatavalidfetch","/Search_NCH_forUser_Url",
    			 "/forgotpasswordncismhindi","/institute_registration_nch_url",
    			 "/institute_registration_nch_action","/getFilter_NCH_Prac_foruserdata","/getTotalNCH_Prac_forUserdataCount","/MedicalImagePath1",
    			 "/getFilter_NCH_Prac_foruserdata","/getTotalNCH_Prac_forUserdataCount","/MedicalImagePath1","/company_signup_Url",
    			 "/getUniversitylistbySystem","/getCountrylistbyUniversity","/ayurvedacourse","/homoeopathycourse" ,"/unanicourse" ,
    			 "/siddhacourse" ,"/sowacourse","/ayurvedacoursencism" ,"/unanicoursencism" ,"/siddhacoursencism" ,"/sowacoursencism",
    			 "/homoeopathycoursench","/introguide",
    			 "/placement_company_reg_Action","/search_placement_Reg_Url","/getSearch_placement_reg_data","/getTotalSearch_placement_reg_dataCount",
    			 "getDistrictOnstate_search_placement_report_student","/MedicalImagePathplaceforstu_reg","/getDistrictOnPlacementreg",
    			 "/getDistrictOnPlacementreg","/MedicalImagePathplace1","/MedicalImagePathplace3","/getSearch_placement_reg_dataCount",
    			 "/AlumniSignup_Url","/Alumni_SignUp_Action","/websitepolicies","/help","/feedback","/termsandcondition","/disclaimer",
    			 "/nchwebsitepolicies","/nchhelp","/nchtermsandcondition","/nchfeedback","/ncismtermsandcondition","/ncismannualreport",
    			 "/ncismfeedback","/screenreaderaccess","/ncismscreenreaderaccess","/nchscreenreaderaccess","/sitemap","/ncismsitemap","/nchsitemap",
    			 "/latestupdate","/ncismlatestupdate","/nchlatestupdate","/signup_practitionner_Url","/practitionner_signup_Action",
    			 "/SendMail_To_All_Institute_Url","/checkEmailInstitute","/checkEmailStudent","/forgotpassUsernameEmail","/feedbackAction",
    			 "/usermanual_main","/helptopics","/ncism_usermanual_main","/ncism_helptopics","/ncism_outer_inst_admission_usermanual",
    			 "/nch_usermanual_main","/nch_helptopics","/nch_inst_usermanual_main","/nch_inst_helptopics","/AyushCollegeData","/nch_facu_usermanual_main","/nch_facu_helptopics","/Helpdesk_Inqiry_Action","/getINQ_Status","/Comment_Details_SaveAction","/signin_common_usermanual","/signin_diff_usermanual").permitAll();
    	 
         http.authorizeRequests().antMatchers( "/auth/login_check?targetUrl","/checkCapchaCode","/ptLogin","/genrateOTP","/VerifyOTP",
        		 "/genrateAadhaarOTP","/isaadhaarverified","/ForgotPassword_ctrl","/changepassbyforgot_Url","/search_placement_Reg_Url",
        		 "/ViewCollabration","/getallCollabData","/Ayushcoreregistries","/Ayushcoredirectories","/error/**","/InstituteInformation",
        		 "/Contact_USAction","/join_Meeting/**","/getMeeting_Link","/getInstituteList","/getFilterInstitute_Fee_Structure_data",
        		 "/getTotalInstitute_Fee_Structure_dataCount","/Institute_All_Fee_Details","/MedicalImagePath_Fee","/getStateListFormcon2",
        		 "/getUniversitylistbySystemforpractitioner","/get_inst_by_uni_nch_ctrl_practitioneer","/helptopics","/searchpageresult",
        		 "/getFilterAdvance_Enhance_Research_dataSearch","/globalsearchAER").permitAll();
         
         http.authorizeRequests().antMatchers( "/genCapchaCode","/getUserDigilocker_API/**","/ayushprakriti","/ayushprakritincism","/ayushprakritinch",
        		 "/varifyAadharNumber","/ayusheducationusermanual/**","/commonerrorpage","/reg_errorpage").permitAll();
       
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests()
        		.and().formLogin()
        		.usernameParameter("username")
        		.passwordParameter("password")        
        		.loginProcessingUrl("/auth/login_check") 
				 .loginPage("/login") 
        		.loginPage("/landingpage")
                .successHandler(redirectLogin())
                .failureUrl("/portalsignin?error=true");
//    	@Bean
//        public CSPNonceFilter CSPNonceFilter1(){
//            return new CSPNonceFilter();
//        }
                http

                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").logoutSuccessUrl("/landingpage");
                http.sessionManagement( ).maximumSessions(1). maxSessionsPreventsLogin(true);
                http.sessionManagement( ).sessionFixation( ).migrateSession( )
                        .sessionAuthenticationStrategy( registerSessionAuthStr( ) );
             
       
    }catch (Exception e) {
		e.printStackTrace();
	} 
    }
    @Bean
    public SessionRegistry sessionRegistry( ) {
        SessionRegistry sessionRegistry = new SessionRegistryImpl( );
        return sessionRegistry;
    }
    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthStr( ) {
        return new RegisterSessionAuthenticationStrategy( sessionRegistry( ) );
    }
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    @Bean
    public AuthenticationSuccessHandler redirectLogin(){
        return new redirectLogin();
    }
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
}
	@Bean
	public AuthenticationManager authenticationmanagerBean() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
    public CSPNonceFilter CSPNonceFilter1(){
        return new CSPNonceFilter();
    }

	
		@Bean
	    public RouterFunction<ServerResponse> router(){
	        return RouterFunctions.route()
	                .GET("fun-ep/video/{title}", this::videoHandler)
	                .build();
	    }

	    private Mono<ServerResponse> videoHandler(ServerRequest serverRequest){
	        String title = serverRequest.pathVariable("title");
	        return ServerResponse.ok()
	                .contentType(MediaType.valueOf("video/mp4"))
	                .body(this.service.getVideo(title), Resource.class);
	    }
	
}