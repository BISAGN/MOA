package com.AyushEdu.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver slr = new SessionLocaleResolver();
//		slr.setDefaultLocale(Locale.US);
//		return slr;
//	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/admin/**").addResourceLocations("/admin/").setCachePeriod(31556926);
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/").setCachePeriod(31556926);
		registry.addResourceHandler("/login_file/**").addResourceLocations("/admin/login_file/").setCachePeriod(31556926);
		registry.addResourceHandler("/join_Meeting/**").addResourceLocations("/join_Meeting/").setCachePeriod(31556926);

		registry.addResourceHandler("/js/**").addResourceLocations("/admin/js/").setCachePeriod(31556926);
	}
	
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");
//	    mailSender.setPort(587);
//	    
////	    mailSender.setUsername("otp.bisag@gmail.com");
//	    mailSender.setUsername("bisagn001@gmail.com");
//	    mailSender.setPassword("123Bisag#");
//	    
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	    
//	    return mailSender;
//	}

	
		@Bean
		public JavaMailSender getJavaMailSender() { 
		    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		    mailSender.setHost("smtp.gmail.com");
		    mailSender.setPort(587);
		    mailSender.setUsername("ayushgrid04@gmail.com");
		    mailSender.setPassword("qpdubsdkjtelddyd");
//		    mailSender.setHost("10.194.81.69");
//		    mailSender.setPort(8080);
//		    mailSender.setUsername("ayushgrid04@gmail.com");
//		    mailSender.setPassword("qpdubsdkjtelddyd");
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		    
		    return mailSender;
		}
	
}
