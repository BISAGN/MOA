package com.AyushEdu.config;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.AyushEdu.dao.UserLoginDAO;
import com.AyushEdu.dao.UserLoginDAOImpl;



public class CSPNonceFilter extends GenericFilterBean {

	private static final int NONCE_SIZE = 32; //recommended is at least 128 bits/16 bytes
    private static final String CSP_NONCE_ATTRIBUTE = "cspNonce";
 
    private SecureRandom secureRandom = new SecureRandom();
    
    
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
 
        byte[] nonceArray = new byte[NONCE_SIZE];
  
        secureRandom.nextBytes(nonceArray);
 
        String nonce = Base64.getEncoder().encodeToString(nonceArray);
        request.setAttribute(CSP_NONCE_ATTRIBUTE, nonce);
        System.err.println(request.getRequestURL() +" : "+ nonce);
        
        ((HttpServletResponse)response).setHeader("Content-Security-Policy", "frame-ancestors 'self'; default-src 'self'; script-src 'self' "
           		+ "'nonce-"+nonce+"'; object-src 'self'; style-src 'self' 'nonce-"+nonce+"';  base-uri 'self' ;"
           				+ ""
           				+ "font-src 'self' data: ;" + "frame-src 'self'; " + "img-src  blob: 'self' data: https://www.w3.org/2000/svg https://100dayscss.com/codepen/alert.png ; "
           						+ "manifest-src 'self';" + "media-src 'self'");
             chain.doFilter(request, new CSPNonceResponseWrapper(response, nonce));
    }
	
	public static class CSPNonceResponseWrapper extends HttpServletResponseWrapper {
        private String nonce;
 
        public CSPNonceResponseWrapper(HttpServletResponse response, String nonce) {
            super(response);
            this.nonce = nonce;
        }
 
        @Override
        public void setHeader(String name, String value) {
            if (name.equals("Content-Security-Policy") && StringUtils.isNotBlank(value)) {
                super.setHeader(name, value.replace("{nonce}", nonce));
            } else {
                super.setHeader(name, value);
            }
        }
 
        @Override
        public void addHeader(String name, String value) {
            if (name.equals("Content-Security-Policy") && StringUtils.isNotBlank(value)) {
                super.addHeader(name, value.replace("{nonce}", nonce));
            } else {
                super.addHeader(name, value);
            }
        }
    }

}
