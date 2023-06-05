package com.AyushEdu.config;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorHandler implements ErrorController  {

//   @RequestMapping("/error/{code}")
//public ModelAndView handleError(@PathVariable(value="code") String status,HttpServletRequest request) {
////   Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//syserr
//   if (status != null) {
//       Integer statusCode = Integer.valueOf(status.toString());
//
//       if(statusCode == HttpStatus.NOT_FOUND.value()) {
//           return new ModelAndView("404");
//       }
//       else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
//           return new ModelAndView("405");
//       }
//       else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
//           return new ModelAndView("400");
//       }
//       else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//           return new ModelAndView("redirect:500");
//       }
//       else if(statusCode == HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value()) {
//           return new ModelAndView("505");
//       }
//   }
//   return new ModelAndView("400");
//}
   
   
   
	 @RequestMapping("/error")
	 public ModelAndView handleErrorInLogin(HttpServletRequest request,ModelMap Mmap) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	 try {
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());

	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	     	   Mmap.put("error_code","404");
	     	   if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
	     		   return new ModelAndView("Outer404");
	 		}
	            return new ModelAndView("404");
	        }
	        else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
	     	   Mmap.put("error_code","405");
	     	   if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
	     		   return new ModelAndView("Outer405");
	 		}
	            return new ModelAndView("405");
	        }
	        else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
	     	   Mmap.put("error_code","400");
	     	   if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
	     		   return new ModelAndView("Outer400");
	 		}
	            return new ModelAndView("400");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	     	   Mmap.put("error_code","500");
	     	   if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
	     		   return new ModelAndView("Outer500");
	 		}
	            return new ModelAndView("500");
	        }
	        else if(statusCode == HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value()) {
	     	   Mmap.put("error_code","505");
	     	   if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
	     		   return new ModelAndView("Outer505");
	 		}
	            return new ModelAndView("505");
	        }
	    }
	    
	    
	   
	    
	    
	    
	    Mmap.put("error_code","400");
	    if (request.getSession().getAttribute("userId_for_jnlp") == null || request.getSession().getAttribute("userId_for_jnlp").equals("")) {
  		   return new ModelAndView("Outer400");
		}
	    return new ModelAndView("400");
	    }catch (Exception e) {
	 	   Mmap.put("error_code","404");
	 	   return new ModelAndView("404");
	 }
	 }
   
   

   public String getErrorPath() {
       return "/error";
   }
}
